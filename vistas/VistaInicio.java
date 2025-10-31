package vistas;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;

import clasespoo.Material;
import dao.Buscador;

public class VistaInicio extends JPanel {

    private JPanel mostrarElementos = new JPanel();
    private JPanel divBuscador = new JPanel();

    // elementos
    private JTextField inputBuscar = new JTextField(20);
    private JButton btnBuscar = new JButton("Buscar");

    // guardar el modelo y tabla
    private DefaultTableModel modelo; // <-- guardamos el modelo
    private JTable tabla; // <-- guardamos la tabla

    public VistaInicio() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // ===== Contenedor tabla =====
        mostrarElementos.setLayout(new BorderLayout());
        mostrarElementos.setPreferredSize(new Dimension(700, 250));
        mostrarElementos.setBackground(Color.WHITE);

        // contenedor Buscador Jpanel
        divBuscador.setLayout(new FlowLayout(FlowLayout.CENTER));
        divBuscador.add(new JLabel("Buscar multimedia:"));
        divBuscador.add(inputBuscar);
        divBuscador.add(btnBuscar);
        btnBuscar.setBorderPainted(false);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setFont(new Font("Cambria", Font.BOLD, 14));
        btnBuscar.setBackground(Color.decode("#555555"));
        btnBuscar.setForeground(Color.decode("#ffffff"));

        // ===== Datos =====
        Buscador busqueda = new Buscador();
        ArrayList<Material> obtenerMaterial = busqueda.obtenerUtimoMaterial();

        String[] columnas = { "Título", "Tipo", "Unidades" };
        modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Material m : obtenerMaterial) {
            modelo.addRow(new Object[] { m.getTitulo(), m.getTipo(), m.getUnidades() });
        }

        // ===== Tabla =====
        tabla = new JTable(modelo);
        tabla.setFillsViewportHeight(true);
        tabla.setRowHeight(32);
        tabla.setShowGrid(false);
        tabla.setBackground(Color.WHITE);
        tabla.setFont(new Font("Cambria", Font.PLAIN, 14));
        tabla.setIntercellSpacing(new Dimension(10, 0));

        // ===== Encabezado =====
        JTableHeader header = tabla.getTableHeader();
        header.setBackground(Color.decode("#252525"));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Cambria", Font.BOLD, 14));
        header.setPreferredSize(new Dimension(header.getWidth(), 40));

        // ===== ScrollPane =====
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.getViewport().setBackground(Color.WHITE);

        // ===== Agregar componentes =====
        add(mostrarElementos);
        mostrarElementos.add(scroll, BorderLayout.CENTER);
        mostrarElementos.add(divBuscador, BorderLayout.NORTH);

        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                System.out.println(tabla.getValueAt(tabla.getSelectedRow(), 0));
            }
        });

        btnBuscar.addActionListener(e -> {
            
        String texto = inputBuscar.getText().trim();
        Buscador buscar = new Buscador();
        ArrayList<Material> busquedaRealizada = new ArrayList<Material>();

        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(null,"Ocurrió un error al guardar los datos.","Error",JOptionPane.ERROR_MESSAGE);
            busquedaRealizada = buscar.obtenerUtimoMaterial();
        } else {
            busquedaRealizada = buscar.buscador(texto);
        }

        modelo.setRowCount(0); // limpia la tabla

        for (Material m : busquedaRealizada) {
            modelo.addRow(new Object[]{m.getTitulo(), m.getTipo(), m.getUnidades()});
        }
        });
    }
    
}
