package Clase.Hilos;

import Clase.Objetos.Tren;

public class Conductor implements Runnable {
    private Tren elTren;

    public Conductor(Tren t) {
        elTren = t;
    }

    public void run() {
        int visitantesAdentro = 0, minutos = 0;
        while (true) {
            for (int i = 0; i < 10; i++) {
                System.out.println("el conductor habilita el tren para subir");
                elTren.dejarSubir();
            }
            while (minutos < 5 || visitantesAdentro > 0) {
                visitantesAdentro = elTren.cantidadVisitantesAdentro();
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
