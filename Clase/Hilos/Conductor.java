package Clase.Hilos;

import Clase.Parque;

public class Conductor implements Runnable {
    private Parque elParque;

    public Conductor(Parque elP) {
        elParque = elP;

    }

    public void run() {
        int visitantesAdentro = 0, minutos = 0;
        while (true) {
            System.out.println("el conductor habilita el tren para subir");
            for (int i = 0; i < 10; i++) {
                elParque.habilitarTren();
            }
            while (minutos < 5 || visitantesAdentro > 0) {
                visitantesAdentro = elParque.cantidadVisitantesAdentro();
                minutos++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("SALE EL TREN");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("LLEGA EL TREN");
            System.out.println("el conductor habilita el tren para bajar");
        }
    }
}
