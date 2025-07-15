package Clase.Objetos;

import java.util.concurrent.Semaphore;

public class RV {
    private Semaphore semaforoBase;
    private Semaphore semaforoManopla;
    private Semaphore semaforoVisor;
    private Semaphore mutex;
    private Semaphore semaforoEncargado;

    public RV(int base, int manopla, int visor) {
        mutex = new Semaphore(1);
        semaforoBase = new Semaphore(base);
        semaforoManopla = new Semaphore(manopla);
        semaforoVisor = new Semaphore(visor);
        semaforoEncargado = new Semaphore(0);
    }

    public void tomarEquipo() {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void devolverEquipo() {
        semaforoBase.release();
        semaforoManopla.release(2);
        semaforoVisor.release();
        semaforoEncargado.release();
    }

    public void esperarEquipo() {
        try {
            semaforoEncargado.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void darEquipo() {
        try {
            semaforoBase.acquire();
            semaforoManopla.acquire(2);
            semaforoVisor.acquire();
            mutex.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
