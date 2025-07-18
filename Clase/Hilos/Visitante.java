package Clase.Hilos;

import java.util.Random;

import Clase.Parque;

public class Visitante implements Runnable {
    private String numVisitante;
    private Parque elParque;

    public void run() {
        Random unRandom = new Random();
        int numeroAtraccion;
        boolean decideSalir = false;
        try {
            while (elParque.tiempoActual() < 19) {// verificar condicion de tiempo
                numeroAtraccion = unRandom.nextInt(1, 6);
                switch (numeroAtraccion) {
                    case 1: // autito chocador
                        System.out
                                .println(
                                        "el visitante N° " + numVisitante + " quiere subirse a los autitos chocadores");
                        elParque.subirAutitoChocador();
                        Thread.sleep(5000);
                        elParque.bajarAutitoChocador();
                        System.out.println("el visitante N° " + numVisitante + " se baja de los autitos chocadores");
                        break;
                    case 2: // area de juegos de premio

                        break;
                    case 3: // comedor
                        System.out.println("el visitante N° " + numVisitante + " se dirige al comedor");
                        boolean mesaDisponible = elParque.esperarMesaDisponible();
                        if (mesaDisponible) {
                            System.out.println("el visitante N° " + numVisitante + " encuentra una mesa disponible");
                            int mesa = elParque.buscarMesa();
                            System.out.println("el visitante N° " + numVisitante + " se sienta en la mesa " + mesa);
                            Thread.sleep(unRandom.nextInt(3, 8) * 1000);
                            System.out.println("el visitante N° " + numVisitante + " deja la mesa " + mesa);
                            elParque.dejarMesa(mesa);
                        } else {
                            System.out
                                    .println("el visitante N° " + numVisitante + " no encontro mesa y decide irse");
                        }
                        break;
                    case 4: // tren
                        elParque.hacerFilaDelTren(numVisitante);
                        Thread.sleep(5000);
                        System.out.println("el visitante N° " + numVisitante + " se baja del tren");

                        break;
                    case 5: // espectaculo

                        break;
                    case 6: // realidad virtual
                        elParque.entrarRealidadVirtual();
                        Thread.sleep(5000);
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
