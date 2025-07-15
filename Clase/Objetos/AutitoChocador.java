package Clase.Objetos;

public class AutitoChocador {
    private int limite;
    private int cantidadAuto;
    private int cantidad;
    private boolean puedeSubir = true;

    public AutitoChocador(int cantidad) {
        cantidadAuto = cantidad;
        limite = cantidad * 2;
        cantidad = 1;
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
        cantidadAuto--;
        if (cantidadAuto == 1) {
            puedeSubir = true;
        }
    }
}
