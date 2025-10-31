package clasespoo;
public class Libro extends MateriaEscrito {
    protected String autor;
    protected Integer paginas;
    protected String editorial;
    protected String isbn;
    protected Integer lanzamiento;

    public Libro(String codigo, String titulo, String autor, Integer paginas, String editorial, String isbn,
            Integer lanzamiento, String tipo,
            Integer unidades) {
        super(codigo, titulo, tipo, unidades);
        this.autor = autor;
        this.paginas = paginas;
        this.editorial = editorial;
        this.isbn = isbn;
        this.lanzamiento = lanzamiento;
    }

    // mis getters
    public String getAutor() {
        return autor;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getIsbn() {
        return isbn;
    }

    public Integer getLanzamiento() {
        return lanzamiento;
    }

    // mis setters

    public void setAutor(String autor){
        this.autor = autor;
    }

    public void setPaginas(Integer paginas){
        this.paginas = paginas;
    }

    public void setEditorial(String editorial){
        this.editorial = editorial;
    }

    public void setIsbn(String isbn){
        this.isbn = isbn;
    }

    public void setLanzamiento(Integer lanzamiento){
        this.lanzamiento = lanzamiento;
    }
}
