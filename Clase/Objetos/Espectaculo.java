package Clase.Objetos;

import java.util.concurrent.locks.*;

public class Espectaculo {
    private int capacidad, tamañoGrupo, tamaño, limiteGrupo, tamañoActual;
    private Lock lock;
    private Condition grupoEntrada, grupoSalida, grupo;
    private boolean abierto = true, showEnCurso = false;

    public Espectaculo() {
        capacidad = 20;
        tamañoGrupo = 0;
        limiteGrupo = 5;
        tamañoActual = 0;
        lock = new ReentrantLock();
        grupoEntrada = lock.newCondition();
        grupoSalida = lock.newCondition();
        grupo = lock.newCondition();
    }

    public boolean atraccionAbierta() {
        return abierto;
    }

    public void cerrar() {
        abierto = false;
    }

    public boolean entrarEspectaculo() {
        boolean entro = false, ultimo = false;
        try {
            lock.lock();
            while (!entro) {
                tamaño++;
                if ((tamaño > capacidad) || showEnCurso) {
                    while ((tamaño > capacidad) || showEnCurso) {
                        grupoEntrada.await();
                    }
                } else {
                    tamañoGrupo++;
                    if (tamañoGrupo <= limiteGrupo) {
                        entro = true;
                        if (tamañoGrupo == limiteGrupo) {
                            ultimo = true;
                            grupo.signalAll();
                        }
                        while (tamañoGrupo < limiteGrupo) {
                            grupo.await();
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
        grupoEntrada.signalAll();
        lock.unlock();
    }

    public void habilitarSalida() {
        lock.lock();
        tamañoGrupo = 0;
        tamaño = 0;
        tamañoActual = 0;
        showEnCurso = false;
        grupoEntrada.signalAll();
        System.out.println("/////INICIA NUEVO SHOW/////");
        lock.unlock();
    }

}
