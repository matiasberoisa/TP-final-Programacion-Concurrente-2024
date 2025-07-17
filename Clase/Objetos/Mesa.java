package Clase.Objetos;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Mesa {
    private CyclicBarrier barreraEntrar;
    private CyclicBarrier barreraSalir;
    private Boolean mesaDisponible;

    public Mesa() {
        barreraEntrar = new CyclicBarrier(4);
        barreraSalir = new CyclicBarrier(4);
        mesaDisponible = true;
    }

    public void entrarMesa() {
        try {
            barreraEntrar.await();
            mesaDisponible = false;
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

    }

    public void dejarMesa() {
        try {
            barreraSalir.await();
            mesaDisponible = true;
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public boolean mesaDisponible() {
        return this.mesaDisponible;
    }
}
