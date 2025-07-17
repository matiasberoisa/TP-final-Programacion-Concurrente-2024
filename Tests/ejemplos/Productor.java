package Tests.ejemplos;

import java.util.concurrent.BlockingQueue;

public class Productor implements Runnable {
    private BlockingQueue<String> cola;
    private String nombre;

    public Productor(BlockingQueue cola, String nom) {
        this.cola = cola;
        nombre = nom;
    }

    public void producir() throws InterruptedException {
        // Produce por recurso
        cola.put("recurso " + (cola.size() + 1) + " de " + nombre);
        System.out.println("El productor " + nombre + " produjo");
    }

    // run()
    public void run() {
        try {
            producir();
        } catch (InterruptedException ex) {
        }
    }

}
