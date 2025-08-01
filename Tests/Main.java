package Tests;

import Clase.*;
import Clase.Hilos.*;

public class Main {
    public static void main(String[] args) {
        Parque elParque = new Parque();
        for (int j = 1; j < 11; j++) {
            Visitante unVisitante = new Visitante("" + j, elParque);
            Thread hiloVisitante = new Thread(unVisitante);
            hiloVisitante.start();
        }
        /*
         * Empleado unEmpleado = new Empleado(0, elParque, "realidadVirtual");
         * Thread hiloEmpleado = new Thread(unEmpleado);
         * hiloEmpleado.start();
         */
    }
}
