package Clase.Objetos;

import java.util.Random;

public class Comedor {
    private int cantMesas;
    private Mesa[] mesas;
    private int capacidadMax;
    private int capacidadActual;

    public Comedor(int cantidad) {
        cantMesas = cantidad;
        capacidadMax = cantidad * 4;
        mesas = new Mesa[cantMesas];
        for (int i = 0; i < mesas.length; i++) {
            mesas[i] = new Mesa();
        }
    }

    public synchronized boolean buscarMesa() throws InterruptedException {
        Random random = new Random();
        boolean disponible = false, decideEsperar = random.nextBoolean(), entro = false;
        int contador = 0;
        if (capacidadActual < capacidadMax && decideEsperar) {
            while (!disponible) {
                if (mesas[contador].mesaDisponible()) {
                    disponible = true;
                } else {
                    contador++;
                }
            }
            if (disponible && decideEsperar) {
                wait();
                entro = true;
            }
        }
        return entro;
    }

    public synchronized int encontrarMesa() {
        int contador = 0;
        while (mesas[contador].mesaDisponible()) {
            contador++;
        }
        mesas[contador].entrarMesa();
        capacidadActual++;
        return contador;
    }

    public void dejarMesa(int i) {
        mesas[i].dejarMesa();
        capacidadActual--;
        notify();
    }

}
