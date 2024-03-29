package jstorra.backend.services;

import jstorra.backend.exceptions.*;
import jstorra.backend.models.Cliente;
import jstorra.backend.models.Factura;
import jstorra.backend.models.Plato;
import jstorra.backend.models.dtos.FacturaGetDTO;
import jstorra.backend.models.dtos.FacturaPostDTO;
import jstorra.backend.repositories.ClienteRepository;
import jstorra.backend.repositories.FacturaRepository;
import jstorra.backend.repositories.PlatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class FacturaService {
    @Autowired
    FacturaRepository facturaRepository;
    @Autowired
    PlatoRepository platoRepository;
    @Autowired
    ClienteRepository clienteRepository;

    public List<FacturaGetDTO> getAllFacturas() {
        return facturaRepository.findAll().stream().map(Factura::toDTO).toList();
    }

    public FacturaGetDTO getFacturaById(Object id) {
        try {
            int parsedId = Integer.parseInt(id.toString());
            return facturaRepository.findById(parsedId)
                    .map(Factura::toDTO)
                    .orElseThrow(() -> new FacturaNotFoundException("Factura con ID " + parsedId + " no existe."));
        } catch (NumberFormatException e) {
            throw new InvalidNumberFormatException("El parametro ingresado no tiene un formato válido.");
        }
    }

    public Map<Object, Object> saveFactura(FacturaPostDTO facturaPostDTO) {
        Factura factura = new Factura();

        facturaRepository.save(fromDTO(factura, facturaPostDTO));

        return new LinkedHashMap<>() {{
            put("message", "La factura ha sido registrada.");
        }};
    }

    public Map<Object, Object> updateFactura(Object id, FacturaPostDTO facturaPostDTO) {
        try {
            int parsedId = Integer.parseInt(id.toString());

            Factura facturaToUpdate = facturaRepository.findById(parsedId).orElseThrow(() -> new FacturaNotFoundException("Factura con ID " + parsedId + " no existe."));

            facturaRepository.save(fromDTO(facturaToUpdate, facturaPostDTO));

            return new LinkedHashMap<>() {{
                put("message", "La factura ha sido actualizada.");
            }};
        } catch (NumberFormatException e) {
            throw new InvalidNumberFormatException("El parametro ingresado no tiene un formato válido.");
        }
    }

    public Map<Object, Object> deleteFactura(Object id) {
        try {
            int parsedId = Integer.parseInt(id.toString());

            Factura factura = facturaRepository.findById(parsedId).orElseThrow(() -> new FacturaNotFoundException("Factura con ID " + parsedId + " no existe."));

            facturaRepository.deleteById(parsedId);

            return new LinkedHashMap<>() {{
                put("message", "La factura ha sido eliminada.");
            }};
        } catch (NumberFormatException e) {
            throw new InvalidNumberFormatException("El parametro ingresado no tiene un formato válido.");
        }
    }

    public Factura fromDTO(Factura factura, FacturaPostDTO facturaPostDTO) {
        factura.setFechaCompra(facturaPostDTO.getFechaCompra());
        factura.setTotal(facturaPostDTO.getTotal());
        factura.setTipoPago(facturaPostDTO.getTipoPago());

        Plato plato = platoRepository.findById(facturaPostDTO.getPlatoId()).
                orElseThrow(() -> new PlatoNotFoundException("Plato con ID " + facturaPostDTO.getPlatoId() + " no existe."));

        factura.setPlato(plato);

        Cliente cliente = clienteRepository.findById(facturaPostDTO.getClienteId()).
                orElseThrow(() -> new ClienteNotFoundException("Cliente con ID " + facturaPostDTO.getClienteId() + " no existe."));

        factura.setCliente(cliente);

        return factura;
    }
}
