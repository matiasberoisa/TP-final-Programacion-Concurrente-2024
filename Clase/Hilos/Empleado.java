package Clase.Hilos;

import Clase.Parque;

public class Empleado implements Runnable {
    private int numEmpleado;
    private Parque elParque;
    private String tipoTrabajo;

    public Empleado(int num, Parque elP, String tt) {
        numEmpleado = num;
        elParque = elP;
        tipoTrabajo = tt;
    }

    public void run() {
        try {
            System.out.println("el empleado " + numEmpleado + " inicia el trabajo en " + tipoTrabajo);
            if (tipoTrabajo.equals("autitoChocador")) {
                while (elParque.tiempoActual() < 19) {
                    if (elParque.tiempoActual() == 19) {

                    }
                }
            }
            if (tipoTrabajo.equals("juegosPremio")) {
                while (elParque.tiempoActual() < 19) {

                }
            }
            if (tipoTrabajo.equals("espectaculo")) {
                while (elParque.tiempoActual() < 19) {

                }
            }
            if (tipoTrabajo.equals("realidadVirtual")) {
                while (elParque.tiempoActual() < 19) {
                    elParque.darEquipo();
                    elParque.esperarEquipo();
                }
            }
            if (tipoTrabajo.equals("manejadorTiempo")) {
                System.out.println("el empleado abre el parque");
                cerrarParque();
            }
        } catch (Exception e) {
        }
    }

    public void cerrarParque() {
        int horas = 9;
        System.out.println("se abre el parque");
        while (horas < 23) {
            System.out.println("son las " + horas + ":00");
            elParque.registrarTiempo(horas);
            if (horas == 18) {
                elParque.cerrarParque();
                System.out.println("el parque ha cerrado, no se permiten mas ingresantes");
            }
            if (horas == 19) {
                elParque.cerrarActividades();
                System.out.println("los juegos han cerrado");
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            horas++;
        }
    }

}
