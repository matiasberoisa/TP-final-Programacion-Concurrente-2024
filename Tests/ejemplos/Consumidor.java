package Tests.ejemplos;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Consumidor implements Runnable {
    private BlockingQueue<String> cola;
    private String nombre;
    private String recurso;

    public Consumidor(BlockingQueue cola, String nom) {
        this.cola = cola;
        nombre = nom;
    }

    public void consumir() throws InterruptedException {
        // Consume x recurso, si no hay recurso disponible espera durante 1000
        // milisegundos
        recurso = cola.poll(1000, TimeUnit.MILLISECONDS);
    }

    // run()
    public void run() {
        try {
            consumir();
            System.out.println("el consumidor " + nombre + " consumió el recurso: " + recurso);
        } catch (InterruptedException ex) {

        }
    }
}
