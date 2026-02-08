package Clase.Hilos;

import Clase.Parque;

public class Conductor implements Runnable {
    private Parque elParque;

    public Conductor(Parque elP) {
        elParque = elP;

    }

    public void run() {
        int asientosDisponibles = 0, minutos;
        try {
            while (true) {
                minutos = 0;
                System.out.println("el conductor habilita el tren para subir");
                for (int i = 0; i < 10; i++) {
                    elParque.habilitarTren();
                }
                while (minutos < 5 || asientosDisponibles < 10) {
                    asientosDisponibles = elParque.cantidadVisitantesAdentro();
                    minutos++;
                    Thread.sleep(1000);
                }
                System.out.println("SALE EL TREN");
                Thread.sleep(5000);
                System.out.println("LLEGA EL TREN");
                System.out.println("el conductor habilita el tren para bajar");
                for (int i = 0; i < 10; i++) {
                    elParque.liberarSalida();
                }
                Thread.sleep(5000);
            }
        } catch (Exception e) {
        }
    }
}
