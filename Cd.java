import java.sql.Time;


public class Cd extends MaterialAudioVisual{
    protected String artista;
    protected String genero;
    protected Time duracion;
    protected Integer canciones;

    public Cd(String codigo, String titulo, String artista, String genero, String duracion, Integer canciones, String tipo, Integer unidades){
        super(codigo, titulo, tipo, unidades);
        this.artista = artista;
        this.genero = genero;
        this.duracion = Time.valueOf(duracion);
        this.canciones = canciones;
    }

    // mis getters
    public String getArtista(){
        return this.artista;
    }

    public String getGenero(){
        return this.genero;
    }

    public Time getDuracion(){
        return this.duracion;
    }

    public Integer getCanciones(){
        return this.canciones;
    }

    // mis setters
    public void setArtista(String artista){
        this.artista = artista;
    }

    public void setGenero(String genero){
        this.genero = genero;
    }

    public void setDuracion(String duracion){
        this.duracion = Time.valueOf(duracion);
    }

    public void setCanciones(Integer canciones){
        this.canciones = canciones;
    }

}
