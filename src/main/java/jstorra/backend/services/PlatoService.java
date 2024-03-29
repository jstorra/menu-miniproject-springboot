package jstorra.backend.services;

import jstorra.backend.exceptions.InvalidNumberFormatException;
import jstorra.backend.exceptions.PlatoDuplicateException;
import jstorra.backend.exceptions.PlatoNotFoundException;
import jstorra.backend.models.Plato;
import jstorra.backend.repositories.PlatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlatoService {
    @Autowired
    PlatoRepository platoRepository;

    public List<Plato> getAllPlatos() {
        return platoRepository.findAll();
    }

    public Plato getPlatoById(Object id) {
        try {
            int parsedId = Integer.parseInt(id.toString());
            return platoRepository.findById(parsedId).orElseThrow(() -> new PlatoNotFoundException("Plato con ID " + parsedId + " no existe."));
        } catch (NumberFormatException e) {
            throw new InvalidNumberFormatException("El parametro ingresado no tiene un formato válido.");
        }
    }

    public Map<Object, Object> savePlato(Plato plato) {
        try {
            platoRepository.save(plato);
            return new LinkedHashMap<>() {{
                put("message", "El plato ha sido registrado.");
            }};
        } catch (Exception e) {
            throw new PlatoDuplicateException("Ya existe un plato con el mismo nombre");
        }
    }

    public Map<Object, Object> updatePlato(Object id, Plato plato) {
        try {
            int parsedId = Integer.parseInt(id.toString());

            Plato platoToUpdate = platoRepository.findById(parsedId).orElseThrow(() -> new PlatoNotFoundException("Plato con ID " + parsedId + " no existe."));

            platoToUpdate.setNombre(plato.getNombre());
            platoToUpdate.setDescripcion(plato.getDescripcion());
            platoToUpdate.setPrecio(plato.getPrecio());
            platoToUpdate.setImagen(plato.getImagen());

            savePlato(platoToUpdate);

            return new LinkedHashMap<>() {{
                put("message", "El plato ha sido actualizado.");
            }};
        } catch (NumberFormatException e) {
            throw new InvalidNumberFormatException("El parametro ingresado no tiene un formato válido.");
        }
    }

    public Map<Object, Object> deletePlato(Object id) {
        try {
            int parsedId = Integer.parseInt(id.toString());

            Plato plato = platoRepository.findById(parsedId).orElseThrow(() -> new PlatoNotFoundException("Plato con ID " + parsedId + " no existe."));

            platoRepository.deleteById(parsedId);

            return new LinkedHashMap<>() {{
                put("message", "El plato ha sido eliminado.");
            }};
        } catch (NumberFormatException e) {
            throw new InvalidNumberFormatException("El parametro ingresado no tiene un formato válido.");
        }
    }
}
