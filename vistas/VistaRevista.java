package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import clasespoo.Revista;
import dao.RevistaDAO;

public class VistaRevista extends JPanel {
    private JPanel divTitulo = new JPanel();
    private JPanel mostrarElementos = new JPanel();
    private JLabel titulo = new JLabel("Revistas disponibles");

    public VistaRevista() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        divTitulo.setLayout(new FlowLayout(FlowLayout.CENTER));
        divTitulo.setPreferredSize(new Dimension(700, 50));
        titulo.setFont(new Font("Cambria", Font.BOLD, 16));
        titulo.setForeground(Color.BLACK);
        divTitulo.add(titulo);

        mostrarElementos.setLayout(new BorderLayout());
        mostrarElementos.setPreferredSize(new Dimension(700, 300));
        mostrarElementos.setBackground(Color.RED);

        RevistaDAO busqueda = new RevistaDAO();
        ArrayList<Revista> obtenerRevista = busqueda.obtenerRevistas();

        String[] columnas = { "CODIGO", "TITULO", "EDITORIAL", "PERIODO", "FECHA", "TIPO", "UNIDADES" };
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Revista m : obtenerRevista) {
            modelo.addRow(new Object[]{
                m.getCodigo(),
                m.getTitulo(),
                m.getEditorial(),
                m.getPeriodicidad(),
                m.getFecha(),
                m.getTipo(),
                m.getUnidades()
            });
        }

        JTable tabla = new JTable(modelo);
        tabla.setFillsViewportHeight(true);
        tabla.setRowHeight(32);
        tabla.setShowGrid(false);
        tabla.setBackground(Color.WHITE);
        tabla.setFont(new Font("Cambria", Font.PLAIN, 14));
        tabla.setIntercellSpacing(new Dimension(10, 0));

        JTableHeader header = tabla.getTableHeader();
        header.setBackground(Color.decode("#252525"));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Cambria", Font.BOLD, 14));
        header.setPreferredSize(new Dimension(header.getWidth(), 40));

        JScrollPane tablaConScroll = new JScrollPane(tabla);
        tablaConScroll.getViewport().setBackground(Color.WHITE);

        mostrarElementos.add(divTitulo, BorderLayout.NORTH);
        mostrarElementos.add(tablaConScroll, BorderLayout.CENTER);
        add(mostrarElementos);

        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                System.out.println(tabla.getValueAt(tabla.getSelectedRow(), 0));
            }
        });
    }
}
