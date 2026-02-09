package Tests;

import Clase.*;
import Clase.Hilos.*;

public class Main {
    public static void main(String[] args) {
        Parque elParque = new Parque(2);
        for (int j = 1; j <= 20; j++) {
            Visitante unVisitante = new Visitante("" + j, elParque);
            Thread hiloVisitante = new Thread(unVisitante);
            hiloVisitante.start();
        }
        Conductor unEmpleado = new Conductor(elParque);
        Thread hiloEmpleado = new Thread(unEmpleado);
        hiloEmpleado.start();
        /*
         * Empleado unEmpleado = new Empleado(0, elParque, "espectaculo");
         * Thread hiloEmpleado = new Thread(unEmpleado);
         * hiloEmpleado.start();
         */
    }
}
