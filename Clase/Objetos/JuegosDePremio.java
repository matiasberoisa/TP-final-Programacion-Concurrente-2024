package Clase.Objetos;

public class JuegosDePremio {
    private boolean abierto = true;

    public boolean atraccionAbierta() {
        return abierto;
    }

    public void cerrar() {
        abierto = false;
    }
}
