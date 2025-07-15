package Clase.Objetos;

public class MontañaRusa {
    private int limite = 5;
    private int cantidad = 0;
    private boolean puedeSubir = true;

    public synchronized void subirse() {
        if (puedeSubir) {
            if (cantidad < limite) {
                cantidad++;
                System.out
                        .println("el visitante N° " + Thread.currentThread().getName() + "se subio a la montaña rusa");
                while (cantidad < limite) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else if (cantidad == limite) {
                puedeSubir = false;
                notifyAll();
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
