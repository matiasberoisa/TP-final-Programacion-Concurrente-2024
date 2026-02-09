package Clase.Objetos;

import java.util.Random;
import java.util.concurrent.*;

public class JuegosDePremio {
    private boolean abierto = true;
    private Exchanger<String>[] exchanger;
    private Semaphore[] semaforos;
    private Semaphore mutex;
    private int encargados;
    private int visitantes;
    private Random random = new Random();

    public JuegosDePremio(int en) {
        encargados = en;
        visitantes = 0;
        semaforos = new Semaphore[encargados];
        exchanger = new Exchanger[encargados];
        for (int i = 0; i < encargados; i++) {
            semaforos[i] = new Semaphore(1);
            exchanger[i] = new Exchanger<>();
        }
        mutex = new Semaphore(1);
    }

    public boolean atraccionAbierta() {
        return abierto;
    }

    public void cerrar() {
        abierto = false;
    }

    public void tomarFicha(int i) throws InterruptedException {
        String ficha = exchanger[i].exchange(""), resultado = "";
        System.out.println("el encargado " + i + " recibe la ficha: " + ficha);
        if (!abierto) {
            exchanger[i].exchange("atraccion cerrada");
        } else {
            int premio = random.nextInt(1, 150);
            resultado = "el encargado " + i + " otorga el premio: " + premio;
            if (premio > 100) {
                resultado += ", premio grande";
            } else if (premio > 50) {
                resultado += ", premio mediano";
            } else {
                resultado += ", premio chico";
            }
            System.out.println(resultado);
            exchanger[i].exchange(resultado);
        }

    }

    public int getVisitantes() {
        return visitantes;
    }

    public int entrarFila() throws InterruptedException {
        mutex.acquire();
        visitantes++;
        boolean encontrado = false;
        int contador = 0, encargadoLibre = 0;
        while (!encontrado && contador < encargados) {
            if (semaforos[contador].tryAcquire()) {
                encontrado = true;
                encargadoLibre = contador;
            } else {
                contador++;
            }
        }
        return encargadoLibre;
    }

    public void cambiarPremio(int i) throws InterruptedException {
        mutex.release();
        if (i == 0) {
            i = random.nextInt(encargados);
        }
        semaforos[i].acquire();

    }

}
