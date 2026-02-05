package Clase.Objetos;

import java.util.concurrent.locks.*;

public class Espectaculo {
    private int capacidad, tamaño, tamañoGrupo, limiteGrupo, tamañoActual;
    private Lock lock;
    private Condition grupoEntrada, grupoSalida, grupo1, grupo2, grupo3, grupo4;
    private boolean abierto = true;

    public Espectaculo() {
        capacidad = 10;
        tamaño = 0;
        tamañoGrupo = 0;
        limiteGrupo = 5;
        tamañoActual = 0;
        lock = new ReentrantLock();
        grupoEntrada = lock.newCondition();
        grupoSalida = lock.newCondition();
        grupo1 = lock.newCondition();
        grupo2 = lock.newCondition();
        grupo3 = lock.newCondition();
        grupo4 = lock.newCondition();
    }

    public boolean atraccionAbierta() {
        return abierto;
    }

    public void cerrar() {
        abierto = false;
    }

    public void entrarEspectaculo() {
        try {
            boolean entro = false;
            tamaño++;
            while (tamaño > capacidad) {
                grupoEntrada.await();
            }
            tamaño--;
            tamañoGrupo++;
            while (!entro) {
                while (tamañoGrupo < limiteGrupo) {
                    grupo1.await();
                }
                while (tamañoGrupo < limiteGrupo + 5) {
                    grupo2.await();
                }
                while (tamañoGrupo < limiteGrupo + 10) {
                    grupo3.await();
                }
                while (tamañoGrupo < limiteGrupo + 15) {
                    grupo4.await();
                }
                entro = true;
            }
            tamañoGrupo--;
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }

    }

    public void habilitarGrupo(int grupo) {
        switch (grupo) {
            case 1:
                grupo1.signalAll();
                break;
            case 2:
                grupo2.signalAll();
                break;
            case 3:
                grupo3.signalAll();
                break;
            case 4:
                grupo4.signalAll();
                break;
        }
    }

    public void sentarse() {
        try {
            lock.lock();
            tamañoActual++;
            while (tamañoActual < capacidad) {
                grupoSalida.await();
            }
            tamañoActual--;
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public void terminarShow() {
        grupoSalida.signalAll();
        grupoEntrada.signalAll();
    }
}
