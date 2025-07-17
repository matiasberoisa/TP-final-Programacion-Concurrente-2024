package Tests.ejemplos;

import java.util.concurrent.Exchanger;

public class ejemploExchanger {
    public static void main(String[] args) {

        Exchanger<Auto> exchanger = new Exchanger<Auto>();

        Auto A1 = new Auto("Ferrari.", "rojo");
        Auto A2 = new Auto("Porche. ", "blanco");

        System.out.println("La concesionaria 1 antes del intercambio tiene el auto : " + A1.mostrar());
        System.out.println("La concesionaria 2 antes del intercambio tiene el auto : " + A2.mostrar());

        Concesionaria concesionaria1 = new Concesionaria(exchanger, A1, 1);
        Concesionaria concesionaria2 = new Concesionaria(exchanger, A2, 2);

        concesionaria1.start();
        concesionaria2.start();
    }
}
