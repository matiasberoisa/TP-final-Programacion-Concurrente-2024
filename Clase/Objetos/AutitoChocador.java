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

    public synchronized boolean subirse() throws InterruptedException {
        boolean entro = false, ultimo = false;
        while (!entro) {
            cantidad++;
            if ((cantidad > limite) || !puedeSubir) {
                while ((cantidad > limite) || !puedeSubir) {
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
                        while (cantidad < limite) {
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
