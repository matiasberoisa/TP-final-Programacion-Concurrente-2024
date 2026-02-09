package Clase.Objetos;

import java.util.Random;
import java.util.concurrent.*;

public class JuegosDePremio {
    private boolean abierto = true;
    private Exchanger<String>[] exchanger;
    private Semaphore[] semaforos;
    private Semaphore mutex;
    private int encargados, visitantes;
    private Random random = new Random();

    public JuegosDePremio(int en) {
        encargados = en;
        visitantes = 0;
        semaforos = new Semaphore[encargados];
        exchanger = new Exchanger[encargados];
        for (int i = 0; i < encargados; i++) {
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
            exchanger[i].exchange("cerrado");
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
        int contador = 0;
        while (!encontrado && contador < semaforos.length) {
            System.out.println(contador);
            if (!semaforos[contador].tryAcquire()) {
                encontrado = true;
            } else {
                contador++;
            }
        }
        if (!encontrado) {
            contador = random.nextInt(0, encargados);
        }
        mutex.release();
        semaforos[contador].acquire();
        return contador;
    }

    public String cambiarPremio(int i, String ficha) throws InterruptedException {
        String ticket = exchanger[i].exchange(ficha);
        if (!ticket.equals("cerrado")) {
            ticket = exchanger[i].exchange(null);
        }
        visitantes--;
        semaforos[i].release();
        return ticket;
    }

}
