package vistas;

import javax.swing.*;

import clasespoo.Cd;
import dao.CdDAO;
import dao.GeneradorCodigoMediateca;
import java.awt.*;

public class VistaAgregarCd extends JPanel {

    private JTextField inputTitulo = new JTextField(10);
    private JTextField inputArtista = new JTextField(10);
    private JTextField inputGenero = new JTextField(10);
    private JTextField inputDuracion = new JTextField(10);
    private JTextField inputCanciones = new JTextField(10);
    private JTextField inputUnidades = new JTextField(10);
    private JButton btnGuardar = new JButton("Agregar nuevo CD");

    public VistaAgregarCd() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // ===== Título =====
        JLabel titulo = new JLabel("Agregar CD");
        titulo.setFont(new Font("Cambria", Font.BOLD, 16));
        JPanel divTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        divTitulo.setBackground(Color.WHITE);
        divTitulo.add(titulo);
        add(divTitulo, BorderLayout.NORTH);

        // ===== Formulario =====
        JPanel divFormulario = new JPanel(new GridLayout(3, 3, 20, 15));
        divFormulario.setBackground(Color.WHITE);
        divFormulario.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // ===== Fila 1 =====
        JPanel div1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        div1.setBackground(Color.WHITE);
        div1.add(new JLabel("Título:"));
        div1.add(inputTitulo);

        JPanel div2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        div2.setBackground(Color.WHITE);
        div2.add(new JLabel("Artista:"));
        div2.add(inputArtista);

        divFormulario.add(div1);
        divFormulario.add(div2);

        // ===== Fila 2 =====
        JPanel div3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        div3.setBackground(Color.WHITE);
        div3.add(new JLabel("Genero:"));
        div3.add(inputGenero);

        JPanel div4 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        div4.setBackground(Color.WHITE);
        div4.add(new JLabel("Duracion:"));
        div4.add(inputDuracion);

        divFormulario.add(div3);
        divFormulario.add(div4);

        // ===== Fila 3 =====
        JPanel div5 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        div5.setBackground(Color.WHITE);
        div5.add(new JLabel("Canciones:"));
        div5.add(inputCanciones);
        divFormulario.add(div5);

        // ===== Fila 4 =====

        JPanel div7 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        div7.setBackground(Color.WHITE);
        div7.add(new JLabel("Unidades:"));
        div7.add(inputUnidades);
        divFormulario.add(div7);
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
        CdDAO instanciaCd = new CdDAO();
        btnGuardar.addActionListener(e -> {
            String tituloCancion = inputTitulo.getText().trim();
            String artista = inputArtista.getText().trim();
            String genero = inputGenero.getText().trim();
            String duracion = inputDuracion.getText().trim(); // HH:mm:ss
            String canciones = inputCanciones.getText().trim();
            String unidades = inputUnidades.getText().trim();

            if (tituloCancion.isEmpty() || artista.isEmpty() || genero.isEmpty() ||
                    duracion.isEmpty() || canciones.isEmpty() || unidades.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Campos vacíos.");
                return;
            }
            if (!duracion.matches("\\d{2}:[0-5]\\d:[0-5]\\d")) {
                JOptionPane.showMessageDialog(null, "Usa HH:mm:ss (ej. 01:15:00)");
                return;
            }

            int cancion, unidad;
            try {
                cancion = Integer.parseInt(canciones);
                unidad = Integer.parseInt(unidades);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Números inválidos.");
                return;
            }

            String codigo = new GeneradorCodigoMediateca().nuevoCodigo("CD");
            Cd cd = new Cd(codigo, tituloCancion, artista, genero, duracion, cancion, "CD", unidad);

            try {
                instanciaCd.ingresarCd(cd);
                JOptionPane.showMessageDialog(null, "CD agregado.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

    }
}
