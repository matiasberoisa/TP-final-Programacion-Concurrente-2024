package Clase.Objetos;

import java.util.concurrent.locks.*;

public class Espectaculo {
    private int capacidad, tamaño, tamañoGrupo, tamañoEspera, tamañoActual;
    private Lock lock;
    private Condition grupoEntrada, grupoSalida, grupoEspera;
    private boolean abierto = true;

    public Espectaculo() {
        capacidad = 20;
        tamaño = 1;
        tamañoGrupo = 1;
        tamañoEspera = 20;
        tamañoActual = 1;
        lock = new ReentrantLock();
        grupoEntrada = lock.newCondition();
        grupoSalida = lock.newCondition();
        grupoEspera = lock.newCondition();
    }

    public boolean atraccionAbierta() {
        return abierto;
    }

    public void cerrar() {
        abierto = false;
    }

    public void hacerFila() {
        try {
            lock.lock();
            tamañoActual++;
            while (tamañoActual >= tamañoEspera) {
                grupoEspera.await();
            }
            tamañoActual--;
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public void ingresarEspectaculo() {
        try {
            lock.lock();
            tamañoGrupo++;
            if (tamañoGrupo == 5) {
                tamañoGrupo = 1;
                grupoEntrada.signalAll();
            } else {
                while (tamañoGrupo < 5) {
                    grupoEntrada.await();
                }
            }
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public void sentarse() {
        try {
            lock.lock();
            tamaño++;
            while (tamaño <= capacidad) {
                grupoSalida.await();
            }
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public void salirEspectaculo() {
        tamaño--;
        if (tamaño == 1) {
            grupoEspera.signalAll();
        }
    }

    public void terminarShow() {
        grupoSalida.signalAll();
    }
}
