package jstorra.backend.services;

import jstorra.backend.exceptions.*;
import jstorra.backend.models.Cliente;
import jstorra.backend.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Cliente getClienteById(Object id) {
        try {
            int parsedId = Integer.parseInt(id.toString());
            return clienteRepository.findById(parsedId).
                    orElseThrow(() -> new ClienteNotFoundException("Cliente con ID " + parsedId + " no existe."));
        } catch (NumberFormatException e) {
            throw new InvalidNumberFormatException("El parametro ingresado no tiene un formato válido.");
        }
    }

    public Map<Object, Object> saveCliente(Cliente cliente) {
        try {
            clienteRepository.save(cliente);
            return new LinkedHashMap<>() {{
                put("message", "El cliente ha sido registrado.");
            }};
        } catch (Exception e) {
            throw new ClienteDuplicateException("Ya existe un cliente con datos similares en su DNI o Email");
        }
    }

    public Map<Object, Object> updateCliente(Object id, Cliente cliente) {
        try {
            int parsedId = Integer.parseInt(id.toString());

            Cliente clienteToUpdate = clienteRepository.findById(parsedId).orElseThrow(() -> new ClienteNotFoundException("Cliente con ID " + parsedId + " no existe."));

            clienteToUpdate.setDni(cliente.getDni());
            clienteToUpdate.setNombre(cliente.getNombre());
            clienteToUpdate.setTelefono(cliente.getTelefono());
            clienteToUpdate.setCorreo(cliente.getCorreo());

            saveCliente(clienteToUpdate);

            return new LinkedHashMap<>() {{
                put("message", "El cliente ha sido actualizado.");
            }};
        } catch (NumberFormatException e) {
            throw new InvalidNumberFormatException("El parametro ingresado no tiene un formato válido.");
        }
    }

    public Map<Object, Object> deleteCliente(Object id) {
        try {
            int parsedId = Integer.parseInt(id.toString());

            Cliente cliente = clienteRepository.findById(parsedId).orElseThrow(() -> new ClienteNotFoundException("Cliente con ID " + parsedId + " no existe."));

            clienteRepository.deleteById(parsedId);

            return new LinkedHashMap<>() {{
                put("message", "El cliente ha sido eliminado.");
            }};
        } catch (NumberFormatException e) {
            throw new InvalidNumberFormatException("El parametro ingresado no tiene un formato válido.");
        }
    }
}
