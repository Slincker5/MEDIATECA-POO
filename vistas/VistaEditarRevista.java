package vistas;

import javax.swing.*;
import clasespoo.Revista;
import dao.RevistaDAO;
import java.awt.*;

public class VistaEditarRevista extends JPanel {

    private JTextField inputTitulo = new JTextField(15);
    private JTextField inputEditorial = new JTextField(15);
    private JTextField inputPeriodo = new JTextField(15);
    private JTextField inputAnio = new JTextField(15);
    private JTextField inputUnidades = new JTextField(15);
    private JButton btnGuardar = new JButton("Guardar cambios");

    protected String codigo;

    public VistaEditarRevista(String codigo) {
        this.codigo = codigo;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // ===== Título =====
        JLabel titulo = new JLabel("Editando revista " + codigo);
        titulo.setFont(new Font("Cambria", Font.BOLD, 16));
        JPanel divTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        divTitulo.setBackground(Color.WHITE);
        divTitulo.add(titulo);
        add(divTitulo, BorderLayout.NORTH);

        // ===== Formulario =====
        JPanel divFormulario = new JPanel(new GridLayout(3, 2, 20, 15));
        divFormulario.setBackground(Color.WHITE);
        divFormulario.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // ===== Fila 1 =====
        JPanel div1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        div1.setBackground(Color.WHITE);
        div1.add(new JLabel("Título:"));
        div1.add(inputTitulo);

        JPanel div2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        div2.setBackground(Color.WHITE);
        div2.add(new JLabel("Editorial:"));
        div2.add(inputEditorial);

        divFormulario.add(div1);
        divFormulario.add(div2);

        // ===== Fila 2 =====
        JPanel div3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        div3.setBackground(Color.WHITE);
        div3.add(new JLabel("Periodo:"));
        div3.add(inputPeriodo);

        JPanel div4 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        div4.setBackground(Color.WHITE);
        div4.add(new JLabel("Año:"));
        div4.add(inputAnio);

        divFormulario.add(div3);
        divFormulario.add(div4);

        // ===== Fila 3 =====
        JPanel div6 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        div6.setBackground(Color.WHITE);
        div6.add(new JLabel("Unidades:"));
        div6.add(inputUnidades);
        divFormulario.add(div6);

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
        RevistaDAO instanciaRevista = new RevistaDAO();
        Revista datos = instanciaRevista.obtenerRevista(codigo);
        if (datos != null) {
            inputTitulo.setText(datos.getTitulo());
            inputEditorial.setText(datos.getEditorial());
            inputPeriodo.setText(datos.getPeriodicidad());
            inputAnio.setText(datos.getFecha());
            inputUnidades.setText(String.valueOf(datos.getUnidades()));
        }

        btnGuardar.addActionListener(e -> {
            datos.setTitulo(inputTitulo.getText());
            datos.setEditorial(inputEditorial.getText());
            datos.setPeriodicidad(inputPeriodo.getText());
            datos.setFecha(inputAnio.getText());
            datos.setUnidades(Integer.parseInt(inputUnidades.getText()));
            instanciaRevista.editarRevista(datos);
            JOptionPane.showMessageDialog(null, codigo + " - se actualizo");
        });
    }
}
