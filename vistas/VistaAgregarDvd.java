package vistas;

import javax.swing.*;

import clasespoo.Dvd;
import dao.DvdDAO;
import dao.GeneradorCodigoMediateca;
import java.awt.*;

public class VistaAgregarDvd extends JPanel {

    private JTextField inputTitulo = new JTextField(10);
    private JTextField inputDirector = new JTextField(10);
    private JTextField inputDuracion = new JTextField(10);
    private JTextField inputGenero = new JTextField(10);
    private JTextField inputUnidades = new JTextField(10);
    private JButton btnGuardar = new JButton("Agregar nuevo DVD");

    public VistaAgregarDvd() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // ===== Título =====
        JLabel titulo = new JLabel("Agregar DVD");
        titulo.setFont(new Font("Cambria", Font.BOLD, 16));
        JPanel divTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        divTitulo.setBackground(Color.WHITE);
        divTitulo.add(titulo);
        add(divTitulo, BorderLayout.NORTH);

        // ===== Formulario =====
        JPanel divFormulario = new JPanel(new GridLayout(2, 2, 20, 15));
        divFormulario.setBackground(Color.WHITE);
        divFormulario.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // ===== Fila 1 =====
        JPanel div1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        div1.setBackground(Color.WHITE);
        div1.add(new JLabel("Título:"));
        div1.add(inputTitulo);

        JPanel div2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        div2.setBackground(Color.WHITE);
        div2.add(new JLabel("Director:"));
        div2.add(inputDirector);

        divFormulario.add(div1);
        divFormulario.add(div2);

        // ===== Fila 2 =====
        JPanel div3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        div3.setBackground(Color.WHITE);
        div3.add(new JLabel("Duracion:"));
        div3.add(inputDuracion);

        JPanel div4 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        div4.setBackground(Color.WHITE);
        div4.add(new JLabel("Genero:"));
        div4.add(inputGenero);

        divFormulario.add(div3);
        divFormulario.add(div4);

        // ===== Fila 4 =====
        JPanel div5 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        div5.setBackground(Color.WHITE);
        div5.add(new JLabel("Unidades:"));
        div5.add(inputUnidades);
        divFormulario.add(div5);
        add(divFormulario, BorderLayout.CENTER);

        // ===== Botón Guardar =====
        btnGuardar.setBackground(Color.decode("#0078D7"));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Cambria", Font.BOLD, 14));
        btnGuardar.setFocusPainted(false);

        JPanel divBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        divBoton.setBackground(Color.WHITE);
        divBoton.add(btnGuardar);
        add(divBoton, BorderLayout.SOUTH);

        // ===== Obtener datos desde DAO =====
        DvdDAO instanciaDvd = new DvdDAO();
        btnGuardar.addActionListener(e -> {
            String tituloDvd = inputTitulo.getText().trim();
            String director = inputDirector.getText().trim();
            String duracion = inputDuracion.getText().trim();
            String genero = inputGenero.getText().trim();
            String unidades = inputUnidades.getText().trim();

            if (tituloDvd.isEmpty() || director.isEmpty() || genero.isEmpty() ||
                    duracion.isEmpty() || unidades.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ningun campo debe estar vacio.");
                return;
            }
            if (!duracion.matches("\\d{2}:[0-5]\\d:[0-5]\\d")) {
                JOptionPane.showMessageDialog(null, "Usa HH:mm:ss (ej. 01:15:00)");
                return;
            }

            int unidad;
            try {
                unidad = Integer.parseInt(unidades);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Números inválidos.");
                return;
            }

            String codigo = new GeneradorCodigoMediateca().nuevoCodigo("DVD");
            Dvd dvd = new Dvd(codigo, tituloDvd, director, duracion, genero, "DVD", unidad);

            try {
                instanciaDvd.ingresarDvd(dvd);
                JOptionPane.showMessageDialog(null, "DVD agregado.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

    }
}
