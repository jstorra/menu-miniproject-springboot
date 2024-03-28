package jstorra.backend.services;

import jstorra.backend.models.Factura;
import jstorra.backend.models.dtos.FacturaDTO;
import jstorra.backend.repositories.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaService {
    @Autowired
    FacturaRepository facturaRepository;

    public List<FacturaDTO> getAllFacturas() {
        return facturaRepository.findAll().stream().map(Factura::toDTO).toList();
    }
}
