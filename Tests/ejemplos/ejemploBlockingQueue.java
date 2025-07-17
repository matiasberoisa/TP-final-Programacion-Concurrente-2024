package Tests.ejemplos;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class ejemploBlockingQueue {
    public static void main(String[] args) {
        int opcion = 1;
        Scanner dato = new Scanner(System.in);
        while (opcion != 0) {
            // Elije 1- ArrayBlockingQueue, 2- LinkedBlockingQueue, 3- SynchronousQueue
            System.out.println(
                    "Ingrese 1 para probar ArrayBlockingQueue\n"
                            + "Ingrese 2 para probar LinkedBlockingQueue\n"
                            + "Ingrese 3 para probar BlockinQueue\n"
                            + "Ingrese 0 para salir\n");
            opcion = dato.nextInt();
            switch (opcion) {
                case (1):
                    BlockingQueue cola1 = new ArrayBlockingQueue(4);
                    for (int i = 0; i <= 5; i++) {
                        Productor p1 = new Productor(cola1, "p" + i);
                        Thread hilo = new Thread(p1);
                        hilo.start();
                        Consumidor c1 = new Consumidor(cola1, "c" + i);
                        Thread hilo2 = new Thread(c1);
                        hilo2.start();
                    }
                    break;
                case (2):
                    BlockingQueue cola2 = new LinkedBlockingQueue(2);
                    for (int i = 0; i <= 5; i++) {
                        Productor p1 = new Productor(cola2, "p" + i);
                        Thread hilo = new Thread(p1);
                        hilo.start();
                        Consumidor c1 = new Consumidor(cola2, "c" + i);
                        Thread hilo2 = new Thread(c1);
                        hilo2.start();
                    }
                    break;
                case (3):
                    BlockingQueue cola3 = new SynchronousQueue();
                    for (int i = 0; i <= 5; i++) {
                        Productor p1 = new Productor(cola3, "p" + i);
                        Thread hilo = new Thread(p1);
                        hilo.start();
                        Consumidor c1 = new Consumidor(cola3, "c" + i);
                        Thread hilo2 = new Thread(c1);
                        hilo2.start();
                    }
                    break;
                case (0):
                    break;
                default:
                    System.out.println("Número no válido\n");
                    break;
            }
        }
    }
}
