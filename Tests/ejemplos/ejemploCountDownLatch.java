package Tests.ejemplos;

import java.util.concurrent.CountDownLatch;

public class ejemploCountDownLatch {
    public static void main(String[] args) {
        int numeroHilos = 10; // Se crean 10 hilos y un CountDownLatch con valor 1
        final CountDownLatch contadorInicial = new CountDownLatch(1);

        for (int i = 0; i < numeroHilos; i++) {
            Thread hilo = new Thread() {
                public void run() {
                    try {
                        contadorInicial.await();
                        System.out.println(this.getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            hilo.setName(i + "");
            hilo.start();
        }
        try { // comienzan los hilos
            contadorInicial.countDown();
            System.out.println("iniciaron los hilos");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
