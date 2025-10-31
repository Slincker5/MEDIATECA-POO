package vistas;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;

import clasespoo.Material;
import dao.Buscador;

public class VistaInicio extends JPanel {

    private JPanel mostrarElementos = new JPanel();

    public VistaInicio() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // ===== Contenedor tabla =====
        mostrarElementos.setLayout(new BorderLayout());
        mostrarElementos.setPreferredSize(new Dimension(700, 250));
        mostrarElementos.setBackground(Color.WHITE);

        // ===== Datos =====
        Buscador busqueda = new Buscador();
        ArrayList<Material> obtenerMaterial = busqueda.obtenerUtimoMaterial();

        String[] columnas = { "Título", "Tipo", "Unidades" };
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Material m : obtenerMaterial) {
            modelo.addRow(new Object[] { m.getTitulo(), m.getTipo(), m.getUnidades() });
        }

        // ===== Tabla =====
        JTable tabla = new JTable(modelo);
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
        mostrarElementos.add(scroll);
        add(mostrarElementos);

        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                System.out.println(tabla.getValueAt(tabla.getSelectedRow(), 0));
            }
        });

    }
}
