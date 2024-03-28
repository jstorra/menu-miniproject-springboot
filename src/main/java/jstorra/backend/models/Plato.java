package jstorra.backend.models;

import jakarta.persistence.*;
import jstorra.backend.models.dtos.PlatoDTO;

import java.util.List;

@Entity
@Table(name = "platos")
public class Plato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "precio", nullable = false)
    private double precio;

    @Column(name = "imagen", nullable = false)
    private String imagen;

    @OneToMany(mappedBy = "plato", cascade = CascadeType.ALL)
    private List<Factura> facturas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public PlatoDTO toDTO() {
        PlatoDTO dto = new PlatoDTO();
        dto.setId(this.getId());
        dto.setNombre(this.getNombre());
        dto.setDescripcion(this.getDescripcion());
        dto.setPrecio(this.getPrecio());
        dto.setImagen(this.getImagen());
        return dto;
    }
}
