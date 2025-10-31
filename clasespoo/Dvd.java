package clasespoo;
import java.sql.Time;

public class Dvd extends MaterialAudioVisual {
    protected String director;
    protected Time duracion;
    protected String genero;
    protected Integer canciones;

    public Dvd(String codigo, String titulo, String director, String duracion, String genero, String tipo,
            Integer unidades) {
        super(codigo, titulo, tipo, unidades);
        this.director = director;
        this.duracion = Time.valueOf(duracion);
        this.genero = genero;
    }

    // mis getters
    public String getDirector() {
        return this.director;
    }

    public Time getDuracion() {
        return this.duracion;
    }

    public String getGenero() {
        return this.genero;
    }

    // mis setters
    public void setDirector(String director) {
        this.director = director;
    }

    public void setDuracion(String duracion) {
        this.duracion = Time.valueOf(duracion);
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

}
