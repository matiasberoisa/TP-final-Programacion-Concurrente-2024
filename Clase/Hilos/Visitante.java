package Clase.Hilos;

import java.util.Random;

import Clase.Parque;

public class Visitante implements Runnable {
    private String numVisitante;
    private Parque elParque;

    public void run() {
        Random unRandom = new Random();
        int numeroAtraccion = unRandom.nextInt(1, 6);
        int[] contadorAtracciones = new int[6];
        try {
            while (true) {// verificar condicion de tiempo
                switch (numeroAtraccion) {
                    case 1: // montaña rusa
                        if (contadorAtracciones[0] == 0) {
                            contadorAtracciones[0]++;
                            System.out.println("el visitante N° " + numVisitante + "quiere subirse a la montaña rusa");
                            elParque.subirAutitoChocador();
                            Thread.sleep(5000);
                            elParque.bajarAutitoChocador();
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
                            System.out.println("el visitante N° " + numVisitante + "se dirige al comedor");
                            boolean mesaDisponible = elParque.esperarMesaDisponible();
                            if (mesaDisponible) {
                                System.out.println("el visitante N° " + numVisitante + "encuentra una mesa disponible");
                                int mesa = elParque.buscarMesa();
                                System.out.println("el visitante N° " + numVisitante + "se sienta en la mesa " + mesa);
                                Thread.sleep(unRandom.nextInt(3, 8) * 1000);
                                System.out.println("el visitante N° " + numVisitante + "deja la mesa " + mesa);
                                elParque.dejarMesa(mesa);
                            } else {
                                System.out
                                        .println("el visitante N° " + numVisitante + "no encontro mesa y decide irse");
                            }
                        }
                        break;
                    case 4: // tren
                        if (contadorAtracciones[3] == 0) {
                            contadorAtracciones[3]++;
                            elParque.hacerFilaDelTren(numVisitante);
                            Thread.sleep(5000);
                            System.out.println("el visitante N° " + numVisitante + "se baja del tren");
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
                            elParque.entrarRealidadVirtual();
                            Thread.sleep(5000);
                            elParque.salirRealidadVirtual();
                        }
                        break;
                }

            }
        } catch (Exception e) {
        }

    }
}
