public class MaterialAudioVisual extends Material {
    protected final String codigo;

    public MaterialAudioVisual(String codigo, String titulo, String tipo, Integer unidades) {
        super(titulo, tipo, unidades);
        this.codigo = codigo;
    }

    public String getCodigo() {
        return this.codigo;
    }
}
