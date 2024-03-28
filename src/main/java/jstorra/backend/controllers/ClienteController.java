package jstorra.backend.controllers;

import jstorra.backend.models.Cliente;
import jstorra.backend.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clientes")
@CrossOrigin("*")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @GetMapping
    public List<Cliente> getAllPlatos() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable Object id) {
        return clienteService.getClienteById(id);
    }

    @PostMapping
    public Map<Object, Object> saveCliente(@RequestBody Cliente cliente) {
        return clienteService.saveCliente(cliente);
    }

    @PutMapping("/{id}")
    public Map<Object, Object> updateCliente(@PathVariable Object id, @RequestBody Cliente cliente) {
        return clienteService.updateCliente(id, cliente);
    }

    @DeleteMapping("/{id}")
    public Map<Object, Object> deleteCliente(@PathVariable Object id) {
        return clienteService.deleteCliente(id);
    }
}
