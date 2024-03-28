package jstorra.backend.controllers;

import jstorra.backend.models.dtos.FacturaDTO;
import jstorra.backend.services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/facturas")
@CrossOrigin("*")
public class FacturaController {
    @Autowired
    FacturaService facturaService;

    @GetMapping
    public List<FacturaDTO> getAllFacturas() {
        return facturaService.getAllFacturas();
    }
}
