public class Material {
    protected String titulo;
    protected final String tipo;
    protected Integer unidades;

    Material(String titulo, String tipo, Integer unidades) {
        this.titulo = titulo;
        this.tipo = tipo;
        this.unidades = unidades;
    }


    // mis getters
    public String getTitulo() {
        return this.titulo;
    }

    public String getTipo() {
        return this.tipo;
    }

    public Integer getUnidades() {
        return this.unidades;
    }

    // mis setters

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public void setUnidades(Integer unidades){
        this.unidades = unidades;
    }
}
