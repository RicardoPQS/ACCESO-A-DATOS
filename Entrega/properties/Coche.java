package properties;

public class Coche {
    private String matricula;
    private double kilometraje;
    private double precio;

    public Coche(String matricula, double kilometraje, double precio) {
        this.matricula = matricula;
        this.kilometraje = kilometraje;
        this.precio = precio;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(double kilometraje) {
        this.kilometraje = kilometraje;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Método para devolver la representación CSV del objeto Coche
    public String aCSV() {
        // Reemplazamos cualquier coma en la matrícula para evitar problemas en el CSV
        return matricula.replace(",", "") + "," + kilometraje + "," + precio;
    }

    @Override
    public String toString() {
        return "matricula: " + matricula + "; km: " + kilometraje + "; precio: " + precio + "€";
    }

    // Método estático para convertir una línea CSV en un objeto Coche
    public static Coche paraCSV(String linea) {
        String[] valores = linea.split(",");
        return new Coche(valores[0], Double.parseDouble(valores[1]), Double.parseDouble(valores[2]));
    }
}
