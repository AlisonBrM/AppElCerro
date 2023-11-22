/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.entity;

/**
 *
 * @author Alison Martinez
 */
public class FormasDePago {
    private String numTrajeta;
    private String codTarjeta;
    private String fechaVencimiento;
    private String numeroCuenta;
    private String nombreCuenta;

    public FormasDePago(String numTrajeta, String codTarjeta, String fechaVencimiento) {
        this.numTrajeta = numTrajeta;
        this.codTarjeta = codTarjeta;
        this.fechaVencimiento = fechaVencimiento;
    }

    public FormasDePago(String numeroCuenta, String nombreCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.nombreCuenta = nombreCuenta;
    }

    public FormasDePago() {
    }

    /**
     * @return the numTrajeta
     */
    public String getNumTrajeta() {
        return numTrajeta;
    }

    /**
     * @param numTrajeta the numTrajeta to set
     */
    public void setNumTrajeta(String numTrajeta) {
        this.numTrajeta = numTrajeta;
    }

    /**
     * @return the codTarjeta
     */
    public String getCodTarjeta() {
        return codTarjeta;
    }

    /**
     * @param codTarjeta the codTarjeta to set
     */
    public void setCodTarjeta(String codTarjeta) {
        this.codTarjeta = codTarjeta;
    }

    /**
     * @return the fechaVencimiento
     */
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * @param fechaVencimiento the fechaVencimiento to set
     */
    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * @return the numeroCuenta
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * @param numeroCuenta the numeroCuenta to set
     */
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    /**
     * @return the nombreCuenta
     */
    public String getNombreCuenta() {
        return nombreCuenta;
    }

    /**
     * @param nombreCuenta the nombreCuenta to set
     */
    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }
    
    
    
}
