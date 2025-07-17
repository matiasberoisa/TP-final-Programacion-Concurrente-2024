package Tests.ejemplos;

public class Auto {
    private String nombre;
    private String color;

    public Auto(String nombre, String color)

    {
        this.nombre = nombre;
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String mostrar() {
        return " " + " Nombre : " + this.nombre + " Color : " + this.color;
    }

}
