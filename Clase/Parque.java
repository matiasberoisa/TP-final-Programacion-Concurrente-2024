package Clase;

import java.util.Random;

import Clase.Objetos.*;

public class Parque {
    private Comedor elComedor; // cyclic barrier
    private Espectaculo elEspectaculo; // locks con condiciones
    private AutitoChocador elAutito; // monitores
    private JuegosDePremio losJuegosDePremio; // exchanger
    private RV realidadVirtual; // semaforos genericos
    private Tren elTren; // blocking queue
    private Boolean abierto = false;
    private int tiempoActual = 0;
    private Random unRandom = new Random();
    private int contVisitantes;

    public Parque(int emJuegos, int emRv) {
        elComedor = new Comedor(unRandom.nextInt(2, 5));
        elEspectaculo = new Espectaculo();
        elAutito = new AutitoChocador(10);
        losJuegosDePremio = new JuegosDePremio(emJuegos);
        realidadVirtual = new RV(unRandom.nextInt(emRv, 5), unRandom.nextInt(emRv, 5) * 2, unRandom.nextInt(emRv, 5));
        elTren = new Tren();
    }

    public synchronized void ingresarParque() {
        contVisitantes++;
    }

    public synchronized void salirParque() {
        contVisitantes--;
    }

    public int cantidadVisitantes() {
        return contVisitantes;
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

    public void retirarVisitantes() {
        try {
            elEspectaculo.notificarCierre();
            elAutito.notificarCierre();
            elTren.notificarCierre();
            elComedor.notificarCierre();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //////////////////// metodos de autito chocador ////////////////////

    public boolean autitosAbierto() {
        return elAutito.atraccionAbierta();
    }

    public boolean subirAutitoChocador() throws InterruptedException {
        return elAutito.subirse();
    }

    public void bajarAutitoChocador() throws InterruptedException {
        elAutito.bajarse();
    }

    public void habilitarSubida() {
        elAutito.habilitarSubida();
    }

    ///////////////////// metodos de juegos de premio ////////////////////

    public boolean juegosAbierto() {
        return losJuegosDePremio.atraccionAbierta();
    }

    public void tomarFicha(int i) throws InterruptedException {
        losJuegosDePremio.tomarFicha(i);
    }

    public int entrarFila() throws InterruptedException {
        return losJuegosDePremio.entrarFila();
    }

    public String cambiarPremio(int i, String ficha) throws InterruptedException {
        return losJuegosDePremio.cambiarPremio(i, ficha);
    }

    //////////////////// metodos de realidad virtual ////////////////////

    public boolean RVAbierto() {
        return realidadVirtual.atraccionAbierta();
    }

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

    ///////////////////// metodos del comedor ////////////////////

    public boolean comedorAbierto() {
        return elComedor.atraccionAbierta();
    }

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

    //////////////////// metodos del tren ////////////////////

    public boolean trenAbierto() {
        return elTren.atraccionAbierta();
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

    //////////////////// metodos del espectaculo ////////////////////

    public boolean espectaculoAbierto() {
        return elEspectaculo.atraccionAbierta();
    }

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

    //////////////////// metodos del tiempo ////////////////////

    public void registrarTiempo(int ti) {
        tiempoActual = ti;
    }

    public int tiempoActual() {
        return tiempoActual;
    }
}
