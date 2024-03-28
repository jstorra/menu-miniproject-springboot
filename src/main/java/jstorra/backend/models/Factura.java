package jstorra.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jstorra.backend.models.dtos.FacturaDTO;

import java.time.LocalDate;

@Entity
@Table(name = "facturas")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha_compra", nullable = false)
    private LocalDate fechaCompra;

    @Column(name = "total", nullable = false)
    private double total;

    @Column(name = "tipo_pago", nullable = false)
    private String tipoPago;

    @ManyToOne
    @JoinColumn(name = "plato_id")
    @JsonIgnore
    private Plato plato;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    private Cliente cliente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public Plato getPlato() {
        return plato;
    }

    public void setPlato(Plato plato) {
        this.plato = plato;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public FacturaDTO toDTO() {
        FacturaDTO dto = new FacturaDTO();
        dto.setId(this.getId());
        dto.setFechaCompra(this.getFechaCompra());
        dto.setTotal(this.getTotal());
        dto.setTipoPago(this.getTipoPago());
        dto.setPlato(this.getPlato().toDTO());
        dto.setCliente(this.getCliente().toDTO());
        return dto;
    }
}
