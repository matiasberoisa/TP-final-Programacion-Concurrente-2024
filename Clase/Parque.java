package Clase;

import Clase.Objetos.*;

public class Parque {
    private Comedor elComedor;
    private Espectaculo elEspectaculo;
    private MontañaRusa laMontañaRusa;
    private JuegosDePremio losJuegosDePremio;
    private RV realidadVirtual;
    private Tren elTren;
    private Boolean abierto = false;

    public void abrirParque() {
        abierto = true;
    }

    public void cerrarParque() {
        abierto = false;
    }

    public void subirMontañaRusa() {
        laMontañaRusa.subirse();
    }

    public void bajarMontañaRusa() {
        laMontañaRusa.bajarse();
    }

}
