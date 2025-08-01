package Clase.Hilos;

import java.util.Random;

import Clase.Parque;

public class Visitante implements Runnable {
    private String numVisitante;
    private Parque elParque;

    public Visitante(String num, Parque elP) {
        numVisitante = num;
        elParque = elP;
    }

    public void run() {
        Random unRandom = new Random();
        int numeroAtraccion;
        boolean decideSalir = false;

        try {
            while (elParque.tiempoActual() < 19) {// verificar condicion de tiempo
                // numeroAtraccion = unRandom.nextInt(1, 6);
                numeroAtraccion = 3;
                switch (numeroAtraccion) {
                    case 1: // autito chocador
                        elParque.subirAutitoChocador();
                        System.out
                                .println(
                                        "el visitante N° " + numVisitante + " se sube al autito chocador");
                        Thread.sleep(5000);
                        elParque.bajarAutitoChocador();
                        System.out.println("el visitante N° " + numVisitante + " se baja de los autitos chocadores");
                        break;
                    case 2: // area de juegos de premio

                        break;
                    case 3: // comedor
                        System.out.println("el visitante N° " + numVisitante + " se dirige al comedor");
                        Thread.sleep(2000);
                        // boolean mesaDisponible = elParque.esperarMesaDisponible();
                        elParque.esperarMesaDisponible();
                        System.out.println("el visitante N° " + numVisitante + " encuentra una mesa disponible");
                        int mesa = elParque.buscarMesa();
                        System.out.println("el visitante N° " + numVisitante + " se sienta en la mesa " + mesa);
                        elParque.usarMesa(mesa);
                        Thread.sleep(unRandom.nextInt(3, 8) * 1000);
                        System.out.println("el visitante N° " + numVisitante + " deja la mesa " + mesa);
                        elParque.dejarMesa(mesa);
                        /*
                         * if (mesaDisponible) {
                         * 
                         * } else {
                         * System.out
                         * .println("el visitante N° " + numVisitante +
                         * " no encontro mesa y decide irse");
                         * }
                         */
                        break;
                    case 4: // tren
                        elParque.hacerFilaDelTren(numVisitante);
                        Thread.sleep(5000);
                        System.out.println("el visitante N° " + numVisitante + " se baja del tren");

                        break;
                    case 5: // espectaculo

                        break;
                    case 6: // realidad virtual
                        System.out.println("el visitante N° " + numVisitante + " quiere entrar a la RV");
                        elParque.entrarRealidadVirtual();
                        System.out.println("el visitante N° " + numVisitante + " toma los elementos y se mete a la RV");
                        Thread.sleep(5000);
                        System.out.println(
                                "el visitante N° " + numVisitante + " devuelve los elementos y se va de la RV");
                        elParque.salirRealidadVirtual();
                        break;
                }

            }
            System.out.println("el visitante N° " + numVisitante + " decide pasear un tiempo por el parque");
            while (elParque.tiempoActual() < 23 && !decideSalir) {
                decideSalir = unRandom.nextBoolean();
            }
            System.out.println("el visitante N° " + numVisitante + " se va del parque");
        } catch (Exception e) {
        }

    }
}
