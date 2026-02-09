package Clase.Objetos;

import java.util.Random;

public class Comedor {
    private Mesa[] mesas;
    private int capacidadMax;
    private int capacidadActual;
    private boolean abierto = true;

    public Comedor(int cantidad) {
        capacidadMax = cantidad * 4;
        capacidadActual = 0;
        mesas = new Mesa[cantidad];
        for (int i = 0; i < mesas.length; i++) {
            mesas[i] = new Mesa();
        }
    }

    public boolean atraccionAbierta() {
        return abierto;
    }

    public void cerrar() {
        abierto = false;
    }

    public void notificarCierre() {
        for (int i = 0; i < mesas.length; i++) {
            mesas[i].cerrarComedor();
        }
    }

    //////////////////// metodos del visitante ////////////////////

    public synchronized boolean buscarMesa(String numVisitante) throws InterruptedException {
        Random random = new Random();
        boolean disponible = false, decideEsperar = random.nextBoolean(), entro = false;
        int contador = 0;
        if (capacidadActual < capacidadMax) {
            capacidadActual++;
            while (!disponible && contador < mesas.length) {
                if (mesas[contador].mesaDisponible()) {
                    disponible = true;
                    entro = true;
                } else {
                    contador++;
                }
            }
        }
        if (!disponible && decideEsperar) {
            while (!disponible && abierto) {
                System.out.println("el visitante " + numVisitante + " decide esperar");
                wait();
            }
            entro = true;
        }
        return entro;
    }

    public synchronized int encontrarMesa() {
        int contador = 0;
        boolean mesaDisponible = false;
        while (!mesaDisponible) {
            if (mesas[contador].mesaDisponible()) {
                mesaDisponible = true;
            } else {
                contador++;
            }
        }
        return contador;
    }

    public void usarMesa(int i) {
        mesas[i].entrarMesa();
    }

    public void dejarMesa(int i) {
        capacidadActual--;
        mesas[i].dejarMesa();
        synchronized (this) {
            this.notifyAll();
        }
    }

}
