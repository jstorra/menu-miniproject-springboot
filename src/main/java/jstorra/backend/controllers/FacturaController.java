package jstorra.backend.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jstorra.backend.models.dtos.FacturaGetDTO;
import jstorra.backend.models.dtos.FacturaPostDTO;
import jstorra.backend.services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/facturas")
@CrossOrigin("*")
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "bearerAuth")
public class FacturaController {
    @Autowired
    FacturaService facturaService;

    @GetMapping
    public List<FacturaGetDTO> getAllFacturas() {
        return facturaService.getAllFacturas();
    }

    @GetMapping("/{id}")
    public FacturaGetDTO getFacturaById(@PathVariable Object id) {
        return facturaService.getFacturaById(id);
    }

    @PostMapping
    public Map<Object, Object> saveFactura(@RequestBody FacturaPostDTO facturaPostDTO) {
        return facturaService.saveFactura(facturaPostDTO);
    }

    @PutMapping("/{id}")
    public Map<Object, Object> updateFactura(@PathVariable Object id, @RequestBody FacturaPostDTO facturaPostDTO) {
        return facturaService.updateFactura(id, facturaPostDTO);
    }

    @DeleteMapping("/{id}")
    public Map<Object, Object> deleteFactura(@PathVariable Object id) {
        return facturaService.deleteFactura(id);
    }
}
