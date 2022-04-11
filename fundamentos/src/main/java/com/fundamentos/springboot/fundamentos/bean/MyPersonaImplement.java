package com.fundamentos.springboot.fundamentos.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MyPersonaImplement implements MyPersona{
    private String nombre = "Juan";
    private String apellido = "Perez";
    private Date fechanac = new Date(0);
    private int edad;

    @Override
    public String getNombre() {
        return nombre;
    }
    @Override
    public String getApellido() {
        return apellido;
    }
    @Override
    public int getEdad() {
        return edad;
    }
    @Override
    public void setEdad(int edad) {
        this.edad = edad;
    }
    @Override
    public int calcularEdad() {
        GregorianCalendar gcNac = new GregorianCalendar();
        gcNac.setTime(fechanac);
        GregorianCalendar ahora = new GregorianCalendar();
        int anios = ahora.get(Calendar.YEAR)-gcNac.get(Calendar.YEAR);
        if(gcNac.get(Calendar.DAY_OF_YEAR)>ahora.get(Calendar.DAY_OF_YEAR))
            anios--;
        return anios;
    }
}
