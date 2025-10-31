package vistas;

import javax.swing.*;

import clasespoo.Libro;
import dao.GeneradorCodigoMediateca;
import dao.LibroDAO;
import java.awt.*;

public class VistaAgregarLibro extends JPanel {

    private JTextField inputTitulo = new JTextField(10);
    private JTextField inputAutor = new JTextField(10);
    private JTextField inputPaginas = new JTextField(10);
    private JTextField inputEditorial = new JTextField(10);
    private JTextField inputIsbn = new JTextField(10);
    private JTextField inputLanzamiento = new JTextField(10);
    private JTextField inputUnidades = new JTextField(10);
    private JButton btnGuardar = new JButton("Agregar nuevo libro");

    public VistaAgregarLibro() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // ===== Título =====
        JLabel titulo = new JLabel("Agregar Libro");
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
        div2.add(new JLabel("Autor:"));
        div2.add(inputAutor);

        divFormulario.add(div1);
        divFormulario.add(div2);

        // ===== Fila 2 =====
        JPanel div3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        div3.setBackground(Color.WHITE);
        div3.add(new JLabel("Paginas:"));
        div3.add(inputPaginas);

        JPanel div4 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        div4.setBackground(Color.WHITE);
        div4.add(new JLabel("Editorial:"));
        div4.add(inputEditorial);

        divFormulario.add(div3);
        divFormulario.add(div4);

        // ===== Fila 3 =====
        JPanel div5 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        div5.setBackground(Color.WHITE);
        div5.add(new JLabel("ISBN:"));
        div5.add(inputIsbn);
        divFormulario.add(div5);

        JPanel div6 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        div6.setBackground(Color.WHITE);
        div6.add(new JLabel("Lanzamiento:"));
        div6.add(inputLanzamiento);
        divFormulario.add(div6);

        // ===== Fila 4 =====

        JPanel div8 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        div8.setBackground(Color.WHITE);
        div8.add(new JLabel("Unidades:"));
        div8.add(inputUnidades);
        divFormulario.add(div8);
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
        LibroDAO instanciaLibro = new LibroDAO();
        btnGuardar.addActionListener(e -> {
            GeneradorCodigoMediateca instanciaCodigo = new GeneradorCodigoMediateca();
            String codigo = instanciaCodigo.nuevoCodigo("LIBRO");
            Libro miLibro = new Libro(codigo, inputTitulo.getText(), inputAutor.getText(), Integer.parseInt(inputPaginas.getText()), inputEditorial.getText(), inputIsbn.getText(), Integer.parseInt(inputLanzamiento.getText()), "LIBRO",  Integer.parseInt(inputUnidades.getText()));
            instanciaLibro.ingresarLibro(miLibro);
            JOptionPane.showMessageDialog(null,"Nuevo libro agregada con exito.");
        });
    }
}
