package Clase.Objetos;

import java.util.concurrent.locks.*;

public class Espectaculo {
    private int capacidad, tamañoGrupo, tamaño, limiteGrupo, tamañoActual;
    private Lock lock;
    private Condition grupoEspera, grupoSalida, grupoEntrada;
    private boolean abierto = true, showEnCurso = false;

    public Espectaculo() {
        capacidad = 20;
        tamañoGrupo = 0;
        limiteGrupo = 5;
        tamañoActual = 0;
        lock = new ReentrantLock();
        grupoEspera = lock.newCondition();
        grupoSalida = lock.newCondition();
        grupoEntrada = lock.newCondition();
    }

    public boolean atraccionAbierta() {
        return abierto;
    }

    public void cerrar() {
        abierto = false;
    }

    public void notificarCierre() {
        lock.lock();
        grupoEspera.signalAll();
        lock.unlock();
    }

    //////////////////// metodos del visitante ////////////////////

    public boolean entrarEspectaculo() {
        boolean entro = false, ultimo = false;
        try {
            lock.lock();
            while (!entro && abierto) {
                tamaño++;
                if ((tamaño > capacidad) || showEnCurso) {
                    while (((tamaño > capacidad) || showEnCurso) && abierto) {
                        grupoEspera.await();
                    }
                } else {
                    tamañoGrupo++;
                    if (tamañoGrupo <= limiteGrupo) {
                        entro = true;
                        if (tamañoGrupo == limiteGrupo) {
                            ultimo = true;
                            grupoEntrada.signalAll();
                        }
                        while (tamañoGrupo < limiteGrupo) {
                            grupoEntrada.await();
                        }
                    }

                }
            }
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
        return ultimo;
    }

    public boolean sentarse() {
        boolean ultimo = false;
        try {
            lock.lock();
            tamañoActual++;
            if (tamañoActual < capacidad) {
                while (tamañoActual < capacidad) {
                    grupoSalida.await();
                }
            } else {
                showEnCurso = true;
                if (tamañoActual == capacidad) {
                    System.out.println("/////EMPIEZA LA FUNCION/////");
                    ultimo = true;
                    grupoSalida.signalAll();
                }
            }
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
        return ultimo;
    }

    public void habilitarEntrada() {
        lock.lock();
        tamañoGrupo = 0;
        tamaño = 0;
        grupoEspera.signalAll();
        lock.unlock();
    }

    public void habilitarSalida() {
        lock.lock();
        tamañoGrupo = 0;
        tamaño = 0;
        tamañoActual = 0;
        showEnCurso = false;
        grupoEspera.signalAll();
        System.out.println("/////INICIA NUEVO SHOW/////");
        lock.unlock();
    }

}
