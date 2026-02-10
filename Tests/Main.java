package Tests;

import java.util.Random;

import Clase.*;
import Clase.Hilos.*;

public class Main {
    public static void main(String[] args) {
        Random unRandom = new Random();
        int numEmpleadosJuegos = unRandom.nextInt(3, 5), numEmpleadosRv = unRandom.nextInt(3, 5);
        Parque elParque = new Parque(numEmpleadosJuegos, numEmpleadosRv);

        /////////////////// creacion de todos los visitantes ///////////////////

        for (int j = 1; j <= 100; j++) {
            Visitante unVisitante = new Visitante("" + j, elParque);
            Thread hiloVisitante = new Thread(unVisitante);
            hiloVisitante.start();
        }

        ////////// creacion del empleado manejador del tiempo //////////

        Empleado elEmpleado = new Empleado(0, elParque, "manejadorTiempo");
        Thread hiloEmpleadoTiempo = new Thread(elEmpleado);
        hiloEmpleadoTiempo.start();

        ////////// creacion del conductor del tren //////////

        Conductor conductor = new Conductor(elParque);
        Thread hiloConductor = new Thread(conductor);
        hiloConductor.start();

        ////////// creacion de los empleados en los juegos de premio //////////

        for (int j = 0; j < numEmpleadosJuegos; j++) {
            Empleado unEmpleado = new Empleado(j, elParque, "juegosPremio", j);
            Thread hiloEmpleado = new Thread(unEmpleado);
            hiloEmpleado.start();
        }

        ////////// creacion de los empleados en la realidad virtual //////////

        for (int j = 0; j < numEmpleadosRv; j++) {
            Empleado unEmpleado = new Empleado(j, elParque, "realidadVirtual");
            Thread hiloEmpleado = new Thread(unEmpleado);
            hiloEmpleado.start();
        }

    }
}
