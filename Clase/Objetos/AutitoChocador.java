package Clase.Objetos;

public class AutitoChocador {
    private int limite;
    private int cantidad;
    private boolean puedeSubir = true;
    private boolean abierto = true;

    public AutitoChocador(int cant) {
        limite = cant * 2;
        cantidad = 0;
    }

    public boolean atraccionAbierta() {
        return abierto;
    }

    public void cerrar() {
        abierto = false;
    }

    public synchronized void subirse() throws InterruptedException {
        if (puedeSubir) {
            cantidad++;
            if (cantidad == limite) {
                puedeSubir = false;
                notifyAll();
            }
            while (cantidad <= limite) {
                wait();
            }
        }
    }

    public synchronized void bajarse() {
        cantidad--;
        if (cantidad == 0) {
            puedeSubir = true;
        }
    }
}
