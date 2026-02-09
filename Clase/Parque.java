package Clase;

import java.util.Random;

import Clase.Objetos.*;

public class Parque {
    private Comedor elComedor; // cyclic barrier
    private Espectaculo elEspectaculo; // locks con condiciones
    private AutitoChocador elAutito; // monitores
    private JuegosDePremio losJuegosDePremio; // exchanger
    private RV realidadVirtual; // semaforos genericos X
    private Tren elTren; // blocking queue
    private Boolean abierto = false;
    private int tiempoActual = 0;
    private Random unRandom = new Random();

    public Parque(int em) {
        elComedor = new Comedor(2);
        elEspectaculo = new Espectaculo();
        elAutito = new AutitoChocador(10);
        losJuegosDePremio = new JuegosDePremio(unRandom.nextInt(em));
        realidadVirtual = new RV(unRandom.nextInt(2, 5), unRandom.nextInt(2, 5) * 2, unRandom.nextInt(2, 5));
        elTren = new Tren();
    }

    public void cerrarActividades() {
        elComedor.cerrar();
        elEspectaculo.cerrar();
        elAutito.cerrar();
        losJuegosDePremio.cerrar();
        realidadVirtual.cerrar();
        elTren.cerrar();
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

    // metodos de autito chocador

    public boolean subirAutitoChocador() throws InterruptedException {
        return elAutito.subirse();
    }

    public void bajarAutitoChocador() throws InterruptedException {
        elAutito.bajarse();
    }

    public void habilitarSubida() {
        elAutito.habilitarSubida();
    }

    // metodos de realidad virtual

    public void entrarRealidadVirtual() {
        realidadVirtual.tomarEquipo();
    }

    public void salirRealidadVirtual() {
        realidadVirtual.devolverEquipo();
    }

    public void darEquipo() {
        realidadVirtual.darEquipo();
    }

    public void esperarEquipo() {
        realidadVirtual.esperarEquipo();
    }

    // metodos del comedor

    public boolean esperarMesaDisponible(String numVisitante) {
        boolean entroComedor = false;
        try {
            elComedor.buscarMesa(numVisitante);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return entroComedor;
    }

    public int buscarMesa() {
        return elComedor.encontrarMesa();
    }

    public void usarMesa(int i) {
        elComedor.usarMesa(i);
    }

    public void dejarMesa(int mesaUsada) {
        elComedor.dejarMesa(mesaUsada);
    }

    // metodos del tren

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

    public int cantidadVisitantesAdentro() {
        return elTren.cantidadVisitantesAdentro();
    }

    public void subirse() throws InterruptedException {
        elTren.subirse();
    }

    public void bajarse() throws InterruptedException {
        elTren.bajarse();
    }

    public void liberarSalida() {
        elTren.dejarBajar();
    }

    // metodos del espectaculo

    public boolean entrarEspectaculo() {
        return elEspectaculo.entrarEspectaculo();
    }

    public boolean sentarse() {
        return elEspectaculo.sentarse();
    }

    public void habilitarSalida() {
        elEspectaculo.habilitarSalida();
    }

    public void habilitarEntrada() {
        elEspectaculo.habilitarEntrada();
    }

    // metodos del tiempo

    public void registrarTiempo(int ti) {
        tiempoActual = ti;
    }

    public int tiempoActual() {
        return tiempoActual;
    }
}
