package Clase.Objetos;

import java.util.concurrent.locks.*;

public class Espectaculo {
    private int capacidad, tamañoGrupo, tamaño, tamañoActual;
    private Lock lock;
    private Condition grupoEspera, grupoSalida;
    private Condition[] grupos = new Condition[4];
    private boolean abierto = true;

    public Espectaculo() {
        capacidad = 20;
        tamañoGrupo = 0;
        tamañoActual = 0;
        lock = new ReentrantLock();
        grupoEspera = lock.newCondition();
        grupoSalida = lock.newCondition();
        for (int i = 0; i < grupos.length; i++) {
            grupos[i] = lock.newCondition();
        }
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
                if (tamaño > capacidad) {
                    while (tamaño > capacidad && abierto) {
                        grupoEspera.await();
                    }
                } else {
                    tamañoGrupo++;
                    entro = true;
                    int numGrupo = tamañoGrupo % 4; // segun el tamaño del grupo actual, se ubica en un grupo
                    if (tamañoGrupo == capacidad) {
                        ultimo = true;
                    }
                    while (tamañoGrupo < capacidad) {
                        grupos[numGrupo].await();
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

    public void habilitarEntrada(int i) {
        lock.lock();
        grupos[i].signalAll();
        lock.unlock();
    }

    public void habilitarSalida() {
        lock.lock();
        tamañoGrupo = 0;
        tamaño = 0;
        tamañoActual = 0;
        grupoEspera.signalAll();
        System.out.println("/////INICIA NUEVO SHOW/////");
        lock.unlock();
    }

}
