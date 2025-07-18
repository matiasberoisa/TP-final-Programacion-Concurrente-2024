package Clase.Objetos;

import java.util.concurrent.*;

public class Tren {
    private boolean estaAbierto;
    private BlockingQueue<String> filaEspera;
    private BlockingQueue<String> tren;
    private boolean abierto = true;

    public Tren() {
        estaAbierto = true;
        filaEspera = new LinkedBlockingQueue<>();
        tren = new ArrayBlockingQueue<>(10);
    }

    public boolean atraccionAbierta() {
        return abierto;
    }

    public void cerrar() {
        abierto = false;
    }

    public void hacerFila(String nombre) throws InterruptedException {
        if (estaAbierto) {
            filaEspera.put(nombre);
            System.out.println("el visitante " + nombre + " entra a la fila de espera");
            tren.take();
            filaEspera.take();
            System.out.println("el visitante " + nombre + " se sube al tren");
        }
    }

    public void dejarSubir() {
        try {
            tren.put("");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int cantidadVisitantesAdentro() {
        return tren.remainingCapacity();
    }
}
