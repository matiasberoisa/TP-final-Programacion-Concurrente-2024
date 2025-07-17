package Tests.ejemplos;

import java.util.concurrent.Exchanger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Concesionaria extends Thread {
    private Exchanger<Auto> exchanger;
    private Auto auto;
    private int nro;

    public Concesionaria(Exchanger exchanger, Auto auto1, int num) {
        this.exchanger = exchanger;
        this.auto = auto1;
        this.nro = num;
    }

    public void run() {
        try {
            this.auto = exchanger.exchange(this.auto);
        } catch (InterruptedException ex) {
            Logger.getLogger(Concesionaria.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.err.println("La concesionaria " + nro + " después del intercambio tiene : " + auto.mostrar());
    }
}
