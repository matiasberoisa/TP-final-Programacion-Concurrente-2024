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

    public synchronized boolean buscarMesa() throws InterruptedException {
        Random random = new Random();
        boolean disponible = false, decideEsperar = random.nextBoolean(), entro = false;
        int contador = 0;
        capacidadActual++;
        if (capacidadActual <= capacidadMax) {
            while (!disponible && contador < mesas.length) {
                if (mesas[contador].mesaDisponible()) {
                    disponible = true;
                    entro = true;
                } else {
                    contador++;
                }
            }
        }
        if (disponible && decideEsperar) {
            wait();
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

    public synchronized void usarMesa(int i) {
        mesas[i].entrarMesa();
    }

    public synchronized void dejarMesa(int i) {
        mesas[i].dejarMesa();
        capacidadActual--;
        notify();
    }

}
