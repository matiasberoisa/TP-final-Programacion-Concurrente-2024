package Clase;

import Clase.Objetos.*;

public class Parque {
    private Comedor elComedor; // cyclic barrier
    private Espectaculo elEspectaculo; // locks con condiciones
    private AutitoChocador elAutito; // monitores
    private JuegosDePremio losJuegosDePremio; // exchanger
    private RV realidadVirtual; // semaforos genericos
    private Tren elTren; // blocking queue
    private Boolean abierto = false;

    public Parque() {
        elComedor = new Comedor(2);
        elEspectaculo = new Espectaculo();
        elAutito = new AutitoChocador(0);
        losJuegosDePremio = new JuegosDePremio();
        realidadVirtual = new RV(0, 0, 0);
        elTren = new Tren();
    }

    public void abrirParque() {
        abierto = true;
    }

    public void cerrarParque() {
        abierto = false;
    }

    public boolean ParqueAbierto() {
        return abierto;
    }

    public void subirAutitoChocador() {
        try {
            elAutito.subirse();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void bajarAutitoChocador() {
        elAutito.bajarse();
    }

    public void entrarRealidadVirtual() {
        realidadVirtual.tomarEquipo();
    }

    public void salirRealidadVirtual() {
        realidadVirtual.darEquipo();
    }

    public void darEquipo() {
        realidadVirtual.darEquipo();
    }

    public void esperarEquipo() {
        realidadVirtual.esperarEquipo();
    }

    // metodos del comedor

    public boolean esperarMesaDisponible() {
        boolean entroComedor = false;
        try {
            entroComedor = elComedor.buscarMesa();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return entroComedor;
    }

    public int buscarMesa() {
        return elComedor.encontrarMesa();
    }

    public void dejarMesa(int mesaUsada) {
        elComedor.dejarMesa(mesaUsada);
    }

    public void hacerFilaDelTren(String nombre) {
        try {
            elTren.hacerFila(nombre);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void habilitarTren() {
        elTren.dejarSubir();
    }

    public void entrarEspectaculo() {
        elEspectaculo.ingresarEspectaculo();
    }

    public void hacerFilaEspectaculo() {
        elEspectaculo.hacerFila();
    }

    public void sentarse() {
        elEspectaculo.sentarse();
    }

    public void salirEspectaculo() {
        elEspectaculo.salirEspectaculo();
    }

    public void iniciarNuevoShow() {
        elEspectaculo.terminarShow();
    }
}
