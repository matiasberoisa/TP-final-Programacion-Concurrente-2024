package Tests;

import Clase.*;
import Clase.Hilos.*;

public class Main {
    public static void main(String[] args) {
        Parque elParque = new Parque(2);
        for (int j = 0; j < 2; j++) {
            Empleado unEmpleado = new Empleado(j, elParque, "juegosPremio", j);
            Thread hiloEmpleado = new Thread(unEmpleado);
            hiloEmpleado.start();
        }
        for (int j = 1; j <= 6; j++) {
            Visitante unVisitante = new Visitante("" + j, elParque);
            Thread hiloVisitante = new Thread(unVisitante);
            hiloVisitante.start();
        }
        /*
         * Empleado unEmpleado = new Empleado(0, elParque, "espectaculo");
         * Thread hiloEmpleado = new Thread(unEmpleado);
         * hiloEmpleado.start();
         */
    }
}
