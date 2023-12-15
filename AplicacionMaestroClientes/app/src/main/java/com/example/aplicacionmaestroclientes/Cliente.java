package com.example.aplicacionmaestroclientes;

import java.io.Serializable;

public class Cliente implements Serializable {
    private String rut;
    private String razon;
    private String correo;
    private int credito;

    public Cliente(){}

    public Cliente(String rut, String razon, String correo, int credito) {
        this.rut = rut;
        this.razon = razon;
        this.correo = correo;
        this.credito = credito;
    }

    public Cliente(String rut, String razon, String correo) {
        this(rut, razon, correo, 10000);
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }
}
