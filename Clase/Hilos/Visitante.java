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
        boolean decideSalir = false, entraUltimo;
        try {
            while (elParque.tiempoActual() < 19) {
                numeroAtraccion = unRandom.nextInt(1, 6);
                // un switch con cada atraccion para que el visitante pueda acceder
                switch (numeroAtraccion) {
                    // todos los juegos estan abiertos hasta las 19, luego de eso se cierran y no se
                    // pueden usar
                    case 1: // entra a los autitos chocadores
                        if (elParque.autitosAbierto()) {
                            entraUltimo = elParque.subirAutitoChocador();
                            if (entraUltimo) { // si es el ultimo en entrar, despierta a todos los hilos y avisa que
                                               // inician
                                               // los autitos
                                System.out.println(
                                        "el visitante N° " + numVisitante
                                                + " fue el ultimo del grupo, habilita al resto a entrar");
                                System.out.println("/////INICIAN LOS AUTITOS/////");
                                Thread.sleep(3000);
                                System.out.println("/////TERMINAN LOS AUTITOS/////");
                                System.out
                                        .println("el visitante N° " + numVisitante + " habilita al resto a subir");
                                Thread.sleep(2000);
                                System.out.println("/////SUBIDA HABILITADA/////");
                                elParque.habilitarSubida();
                                elParque.bajarAutitoChocador();
                            } else { // si no es el ultimo, espera que inicien
                                System.out
                                        .println(
                                                "el visitante N° " + numVisitante + " se sube al autito chocador");
                                Thread.sleep(5000);
                                System.out
                                        .println("el visitante N° " + numVisitante
                                                + " se baja de los autitos chocadores");
                            }
                        }
                        break;
                    case 2: // entra al area de juegos de premio
                        if (elParque.juegosAbierto()) {
                            System.out.println("el visitante N° " + numVisitante + " se acerca a los juegos de premio");
                            int fila = elParque.entrarFila();
                            System.out.println("el visitante N° " + numVisitante + " entra en la fila: " + fila);
                            Thread.sleep(3000);
                            String ticket = elParque.cambiarPremio(fila, numVisitante);
                            if (ticket.equals("cerrado")) {
                                System.out.println("el parque cerro, no se puede cambiar el premio");
                            } else {
                                System.out.println(ticket + " al visitante N° " + numVisitante);
                            }
                        }
                        break;
                    case 3: // entra al comedor
                        if (elParque.comedorAbierto()) {
                            System.out.println("el visitante N° " + numVisitante + " se dirige al comedor");
                            Thread.sleep(2000);
                            boolean mesaDisponible = elParque.esperarMesaDisponible(numVisitante); // comedor.buscarMesa()
                            if (!mesaDisponible) {
                                System.out
                                        .println("el visitante N° " + numVisitante + " encuentra una mesa disponible");
                                int mesa = elParque.buscarMesa(); // comedor.encontrarMesa()
                                elParque.usarMesa(mesa);
                                System.out.println("el visitante N° " + numVisitante + " se sienta en la mesa " + mesa);
                                Thread.sleep(unRandom.nextInt(3, 8) * 1000);
                                elParque.dejarMesa(mesa);
                                System.out.println("el visitante N° " + numVisitante + " deja la mesa " + mesa);
                            } else {
                                System.out
                                        .println("el visitante N° " + numVisitante +
                                                " no encontro mesa y decide irse");
                            }
                        }
                        break;
                    case 4: // entra al tren
                        if (elParque.trenAbierto()) {
                            elParque.hacerFilaDelTren(numVisitante);
                            System.out.println("el visitante " + numVisitante + " entra a la fila de espera");
                            Thread.sleep(5000);
                            elParque.subirse();
                            System.out.println("el visitante " + numVisitante + " se sube al tren");
                            Thread.sleep(5000);
                            elParque.bajarse();
                            System.out.println("el visitante N° " + numVisitante + " se baja del tren");
                            Thread.sleep(5000);
                        }
                        break;
                    case 5: // entra al espectaculo
                        if (elParque.espectaculoAbierto()) {
                            entraUltimo = elParque.entrarEspectaculo();
                            if (entraUltimo) {
                                System.out.println(
                                        "el visitante N° " + numVisitante
                                                + " fue el ultimo del grupo, habilita al resto a entrar");
                                Thread.sleep(3000);
                                elParque.habilitarEntrada();
                            } else {
                                System.out.println("el visitante N° " + numVisitante + " entra al espectaculo");
                            }
                            boolean saleUltimo = elParque.sentarse();
                            Thread.sleep(3000);
                            if (saleUltimo) {
                                System.out.println(
                                        "el visitante N° " + numVisitante
                                                + " fue el ultimo en salir, habilita al resto a entrar");
                                Thread.sleep(3000);
                                elParque.habilitarSalida();
                            } else {
                                System.out.println("el visitante N° " + numVisitante + " sale al espectaculo");
                            }
                        }
                        break;
                    case 6: // entra a la realidad virtual
                        if (elParque.RVAbierto()) {
                            System.out.println("el visitante N° " + numVisitante + " quiere entrar a la RV");
                            elParque.entrarRealidadVirtual();
                            System.out.println(
                                    "el visitante N° " + numVisitante + " toma los elementos y se mete a la RV");
                            Thread.sleep(5000);
                            System.out.println(
                                    "el visitante N° " + numVisitante + " devuelve los elementos y se va de la RV");
                            elParque.salirRealidadVirtual();
                            Thread.sleep(5000);
                        }
                        break;
                }

            }
            // cuando se cierran los juegos, el visitante puede retirarse del parque o
            // quedarse hasta las 23 horas
            System.out.println("el visitante N° " + numVisitante + " decide pasear un tiempo por el parque");
            Thread.sleep(3000);
            while (elParque.tiempoActual() < 23 && !decideSalir) {
                decideSalir = unRandom.nextBoolean();
            }
            System.out.println("el visitante N° " + numVisitante + " se va del parque");
        } catch (Exception e) {
        }

    }
}
