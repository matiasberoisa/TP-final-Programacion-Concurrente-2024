package Tests;

import Clase.Hilos.Empleado;

public class Main {
    public static void main(String[] args) {
        Empleado unEmpleado = new Empleado(0, null, "manejadorTiempo");
        unEmpleado.run();
    }
}
