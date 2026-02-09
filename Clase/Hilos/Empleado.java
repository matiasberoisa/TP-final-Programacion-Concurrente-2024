package Clase.Hilos;

import Clase.Parque;

public class Empleado implements Runnable {
    private int numEmpleado;
    private Parque elParque;
    private String tipoTrabajo;
    private int numMesa;

    public Empleado(int num, Parque elP, String tt) {
        numEmpleado = num;
        elParque = elP;
        tipoTrabajo = tt;
    }

    public Empleado(int num, Parque elP, String tt, int nn) {
        numEmpleado = num;
        elParque = elP;
        tipoTrabajo = tt;
        numMesa = nn;
    }

    public void run() {
        try {
            if (tipoTrabajo.equals("juegosPremio")) {
                System.out.println("el empleado " + numEmpleado + " inicia el trabajo en " + tipoTrabajo +
                        " puesto: " + numMesa);
                while (elParque.tiempoActual() < 23) {
                    elParque.tomarFicha(numMesa);
                }
            }
            if (tipoTrabajo.equals("realidadVirtual")) {
                while (elParque.tiempoActual() < 23) {
                    elParque.darEquipo();
                    elParque.esperarEquipo();
                }
            }
            if (tipoTrabajo.equals("manejadorTiempo")) {
                System.out.println("el empleado abre el parque");
                cerrarParque();
                System.out.println("cantidad visitantes dentro: " + elParque.cantidadVisitantes());
                System.out.println("el empleado cierra el parque");
            }
        } catch (Exception e) {
        }
    }

    public void cerrarParque() {
        int horas = 9;
        System.out.println("se abre el parque");
        while (horas <= 23) {
            System.out.println("son las " + horas + ":00");
            System.out.println("cantidad visitantes dentro: " + elParque.cantidadVisitantes());
            elParque.registrarTiempo(horas);
            if (horas == 18) {
                elParque.cerrarParque();
                System.out.println("el parque ha cerrado, no se permiten mas ingresantes");
            }
            if (horas == 19) {
                elParque.cerrarActividades();
                System.out.println("los juegos han cerrado");
            }
            if (horas > 19) {
                elParque.retirarVisitantes();
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            horas++;
        }

    }

}
