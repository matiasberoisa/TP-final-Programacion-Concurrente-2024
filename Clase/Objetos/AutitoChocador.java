package Clase.Objetos;

public class AutitoChocador {
    private int limite, cantidad;
    private boolean puedeSubir = true, abierto = true;

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

    public synchronized void notificarCierre() {
        notifyAll();
    }

    //////////////////// metodos del visitante ////////////////////

    public synchronized boolean subirse() throws InterruptedException {
        boolean entro = false, ultimo = false;
        while (!entro && abierto) {
            cantidad++;
            if ((cantidad > limite) || !puedeSubir) {
                while (((cantidad > limite) || !puedeSubir) && abierto) {
                    wait();
                }
            } else {
                if (cantidad <= limite) {
                    entro = true;
                    if (cantidad == limite) {
                        puedeSubir = false;
                        ultimo = true;
                        notifyAll();
                    } else {
                        while (cantidad < limite && abierto) {
                            wait();
                        }
                    }
                }
            }
        }
        return ultimo;
    }

    public synchronized void bajarse() throws InterruptedException {
        cantidad = 0;
        notifyAll();
    }

    public synchronized void habilitarSubida() {
        puedeSubir = true;
    }
}
