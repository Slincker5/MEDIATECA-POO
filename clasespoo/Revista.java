package clasespoo;
public class Revista extends MateriaEscrito {
    protected String editorial;
    protected String periodicidad;
    protected String fecha;

    public Revista(String codigo, String titulo, String editorial, String periodicidad, String fecha, String tipo,
            Integer unidades) {
        super(codigo, titulo, tipo, unidades);
        this.editorial = editorial;
        this.periodicidad = periodicidad;
        this.fecha = fecha;
    }

    // mis getters
    public String getEditorial(){
        return this.editorial;
    }

    public String getPeriodicidad(){
        return this.periodicidad;
    }

    public String getFecha(){
        return this.fecha;
    }

    // mis setters

    public void setEditorial(String editorial){
        this.editorial = editorial;
    }

    public void setPeriodicidad(String periodicidad){
        this.periodicidad = periodicidad;
    }

    public void setFecha(String fecha){
        this.fecha = fecha;
    }
}
