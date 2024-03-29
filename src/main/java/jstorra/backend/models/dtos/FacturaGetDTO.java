package jstorra.backend.models.dtos;

import java.time.LocalDate;

public class FacturaGetDTO {
    private Integer id;
    private LocalDate fechaCompra;
    private double total;
    private String tipoPago;
    private PlatoDTO plato;
    private ClienteDTO cliente;

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

    public PlatoDTO getPlato() {
        return plato;
    }

    public void setPlato(PlatoDTO plato) {
        this.plato = plato;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
}
