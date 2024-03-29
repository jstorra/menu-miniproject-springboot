package jstorra.backend.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jstorra.backend.models.Plato;
import jstorra.backend.services.PlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/platos")
@CrossOrigin("*")
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "bearerAuth")
public class PlatoController {
    @Autowired
    PlatoService platoService;

    @GetMapping
    public List<Plato> getAllPlatos() {
        return platoService.getAllPlatos();
    }

    @GetMapping("/{id}")
    public Plato getPlatoById(@PathVariable Object id) {
        return platoService.getPlatoById(id);
    }

    @PostMapping
    public Map<Object, Object> savePlato(@RequestBody Plato plato) {
        return platoService.savePlato(plato);
    }

    @PutMapping("/{id}")
    public Map<Object, Object> updatePlato(@PathVariable Object id, @RequestBody Plato plato) {
        return platoService.updatePlato(id, plato);
    }

    @DeleteMapping("/{id}")
    public Map<Object, Object> deletePlato(@PathVariable Object id) {
        return platoService.deletePlato(id);
    }
}
