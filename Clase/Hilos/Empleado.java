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
            System.out.println("el empleado " + numEmpleado + "inicia el trabajo en " + tipoTrabajo);
            if (tipoTrabajo.equals("autitoChocador")) {
                while (true) {

                }
            }
            if (tipoTrabajo.equals("juegosPremio")) {
                while (true) {

                }
            }
            if (tipoTrabajo.equals("espectaculo")) {
                while (true) {

                }
            }
            if (tipoTrabajo.equals("realidadVirtual")) {
                while (true) {
                    elParque.darEquipo();
                    elParque.esperarEquipo();
                }
            }
        } catch (Exception e) {
        }
    }
}
