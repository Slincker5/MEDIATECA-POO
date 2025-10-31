package vistas;

import javax.swing.*;
import java.awt.*;

public class VistaMain extends JPanel {
    private JLabel titulo;

    // botones del menu
    private JButton btnInicio = new JButton("Inicio");
    private JButton btnRevista = new JButton("Revistas");
    private JButton btnLibro = new JButton("Libros");
    private JButton btnCd = new JButton("CDs");
    private JButton btnDvs = new JButton("DVDs");

    // Jpanels o divs para mi
    private JPanel divTitulo = new JPanel();
    private JPanel divMenu = new JPanel();
    private JPanel contenidoDinamico = new JPanel();

    public VistaMain() {
        // Layout contenedor Padre
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // componente titulo
        divTitulo.setLayout(new FlowLayout(FlowLayout.CENTER));
        titulo = new JLabel("MEDIATECA VIRTUAL");
        titulo.setForeground(Color.BLACK);
        titulo.setFont(new Font("Cambria", Font.BOLD, 18));
        divTitulo.add(titulo);
        add(divTitulo);

        // Componente menu
        divMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        divMenu.add(btnInicio);
        divMenu.add(btnRevista);
        divMenu.add(btnLibro);
        divMenu.add(btnCd);
        divMenu.add(btnDvs);
        this.estilizarBoton(btnInicio);
        this.estilizarBoton(btnRevista);
        this.estilizarBoton(btnLibro);
        this.estilizarBoton(btnCd);
        this.estilizarBoton(btnDvs);
        add(divMenu);

        // ===== Contenido dinámico =====
        contenidoDinamico.setLayout(new BorderLayout());
        VistaInicio divMain = new VistaInicio();
        contenidoDinamico.add(divMain, BorderLayout.CENTER);
        add(contenidoDinamico);

        // Cambios de vista segun el boton
        btnInicio.addActionListener(e -> {
            VistaInicio inicio = new VistaInicio();
            contenidoDinamico.removeAll();
            contenidoDinamico.add(inicio, BorderLayout.CENTER);
            contenidoDinamico.revalidate();
            contenidoDinamico.repaint();
        });
        btnRevista.addActionListener(e -> {
            VistaRevista revista = new VistaRevista();
            contenidoDinamico.removeAll();
            contenidoDinamico.add(revista, BorderLayout.CENTER);
            contenidoDinamico.revalidate();
            contenidoDinamico.repaint();
        });
        btnLibro.addActionListener(e -> {
            VistaLibro libro = new VistaLibro();
            contenidoDinamico.removeAll();
            contenidoDinamico.add(libro, BorderLayout.CENTER);
            contenidoDinamico.revalidate();
            contenidoDinamico.repaint();
        });
        btnCd.addActionListener(e -> {
            VistaCd cd = new VistaCd();
            contenidoDinamico.removeAll();
            contenidoDinamico.add(cd, BorderLayout.CENTER);
            contenidoDinamico.revalidate();
            contenidoDinamico.repaint();
        });
    }

    // funcion para quitar estilo por defecto a mis botones menu
    private void estilizarBoton(JButton boton) {
    boton.setContentAreaFilled(false);
    boton.setBorderPainted(false);
    boton.setFocusPainted(false);
    boton.setFont(new Font("Cambria", Font.BOLD, 14));
    boton.setForeground(Color.decode("#0078D7"));
}
}
