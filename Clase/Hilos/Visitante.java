package Clase.Hilos;

import java.util.Random;

import Clase.Parque;

public class Visitante implements Runnable {
    private int numVisitante;
    private Parque elParque;

    public void run() {
        Random unRandom = new Random();
        int numeroAtraccion = unRandom.nextInt(1, 6);
        int[] contadorAtracciones = new int[6];
        while (true) {// verificar condicion de tiempo
            switch (numeroAtraccion) {
                case 1: // montaña rusa
                    if (contadorAtracciones[0] == 0) {
                        contadorAtracciones[0]++;
                        System.out.println("el visitante N° " + numVisitante + "quiere subirse a la montaña rusa");
                        elParque.subirMontañaRusa();
                        elParque.bajarMontañaRusa();
                        System.out.println("el visitante N° " + numVisitante + "se baja de la montaña rusa");
                    }
                    break;
                case 2: // area de juegos de premio
                    if (contadorAtracciones[1] == 0) {
                        contadorAtracciones[1]++;
                    }
                    break;
                case 3: // comedor
                    if (contadorAtracciones[2] == 0) {
                        contadorAtracciones[2]++;
                    }
                    break;
                case 4: // tren
                    if (contadorAtracciones[3] == 0) {
                        contadorAtracciones[3]++;
                    }
                    break;
                case 5: // espectaculo
                    if (contadorAtracciones[4] == 0) {
                        contadorAtracciones[4]++;
                    }
                    break;
                case 6: // realidad virtual
                    if (contadorAtracciones[5] == 0) {
                        contadorAtracciones[5]++;
                    }
                    break;
            }

        }
    }
}
