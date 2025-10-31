package ventanas;

import javax.swing.*;

public class Ventana extends JFrame {
    public Ventana(String titulo, int ancho, int alto) {
        setTitle(titulo);
        setSize(ancho, alto);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
    }
}
