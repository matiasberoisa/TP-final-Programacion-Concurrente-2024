package Clase.Objetos;

import java.util.concurrent.*;

public class Tren {
    private BlockingQueue<String> filaEspera;
    private BlockingQueue<String> tren;
    private BlockingQueue<String> salida;
    private boolean abierto = true;

    public Tren() {
        filaEspera = new LinkedBlockingQueue<>();
        tren = new ArrayBlockingQueue<>(10);
        salida = new ArrayBlockingQueue<>(10);
    }

    public boolean atraccionAbierta() {
        return abierto;
    }

    public void cerrar() {
        abierto = false;
    }

    public void notificarCierre() throws InterruptedException {
        while (filaEspera.size() != 0) {
            for (int i = 0; i < tren.remainingCapacity(); i++) {
                tren.put("");
            }
        }
    }

    //////////////////// metodos del visitante ////////////////////

    public void hacerFila(String nombre) throws InterruptedException {
        if (abierto) {
            filaEspera.put(nombre);
        }
    }

    public void subirse() throws InterruptedException {
        tren.take();
        filaEspera.take();
    }

    public void bajarse() throws InterruptedException {
        salida.take();

    }

    //////////////////// metodos del conductor ////////////////////

    public void dejarSubir() {
        try {
            tren.put("");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void dejarBajar() {
        try {
            salida.put("");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int cantidadVisitantesAdentro() {
        return tren.remainingCapacity();
    }

    public int visitantesSalida() {
        return salida.remainingCapacity();
    }

    public int visitantesEspera() {
        return filaEspera.size();
    }
}
