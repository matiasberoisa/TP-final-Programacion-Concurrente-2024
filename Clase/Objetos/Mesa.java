package Clase.Objetos;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Mesa {
    private CyclicBarrier barreraEntrar;
    private CyclicBarrier barreraSalir;
    private Boolean mesaDisponible;
    private boolean abierto = true;

    public Mesa() {
        barreraEntrar = new CyclicBarrier(4, () -> {
            // Este código se ejecuta cuando todos los hilos llegan a la barrera
            System.out.println("se sientan todos en la mesa");
        });
        barreraSalir = new CyclicBarrier(4, () -> {
            // Este código se ejecuta cuando todos los hilos llegan a la barrera
            System.out.println("se levantan todos en la mesa");
        });
        mesaDisponible = true;
    }

    public boolean atraccionAbierta() {
        return abierto;
    }

    public void cerrar() {
        abierto = false;
    }

    public void cerrarComedor() {
        barreraSalir.reset();
    }

    //////////////////// metodos del visitante ////////////////////

    public void entrarMesa() {
        try {
            barreraEntrar.await();
            synchronized (this) {
                mesaDisponible = false;
            }
        } catch (InterruptedException | BrokenBarrierException e) {

        }

    }

    public void dejarMesa() {
        try {
            barreraSalir.await();
            synchronized (this) {
                mesaDisponible = true;
            }
        } catch (InterruptedException | BrokenBarrierException e) {

        }
    }

    public synchronized boolean mesaDisponible() {
        return this.mesaDisponible;
    }
}
