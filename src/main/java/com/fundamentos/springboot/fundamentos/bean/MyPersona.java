package com.fundamentos.springboot.fundamentos.bean;

import java.util.Date;

public interface MyPersona {
    String getNombre();
    String getApellido();
    int getEdad();
    void setEdad(int edad);
    int calcularEdad();
}
