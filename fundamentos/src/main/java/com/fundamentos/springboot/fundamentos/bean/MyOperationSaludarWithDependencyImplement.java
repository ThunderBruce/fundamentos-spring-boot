package com.fundamentos.springboot.fundamentos.bean;

public class MyOperationSaludarWithDependencyImplement implements MyOperationSaludarWithDependency {
    private MyPersona myPersona;

    public MyOperationSaludarWithDependencyImplement(MyPersona myPersona) {
        this.myPersona = myPersona;
    }
    @Override
    public void saludar() {
        myPersona.setEdad(myPersona.calcularEdad());
        System.out.println("Hola, me llamo "+myPersona.getNombre()+" "+myPersona.getApellido()+", y tengo "+myPersona.getEdad()+" anyos");
    }
}
