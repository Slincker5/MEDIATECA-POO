package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import clasespoo.Revista;
import dao.RevistaDAO;

public class VistaRevista extends JPanel {
    private JPanel divTitulo = new JPanel();
    private JPanel divMain = new JPanel();
    private JPanel divAcciones = new JPanel();

    // compos que deseo obtener

    private String codigo;
    // elementos de los divs
    private JButton btnAgregarRevista = new JButton("Agregar Revista");
    private JButton btnEditarRevista = new JButton("Editar Revista");
    private JButton btnEliminarRevista = new JButton("Eliminar Revista");

    private JLabel titulo = new JLabel("Revistas disponibles");

    public VistaRevista() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        divTitulo.setLayout(new BorderLayout());
        divTitulo.setBorder(BorderFactory.createEmptyBorder(0, 10, 15, 10));
        titulo.setFont(new Font("Cambria", Font.BOLD, 16));
        titulo.setForeground(Color.BLACK);
        divTitulo.add(titulo, BorderLayout.WEST);
        divTitulo.add(btnAgregarRevista, BorderLayout.EAST);

        divMain.setLayout(new BorderLayout());
        divMain.setPreferredSize(new Dimension(740, 300));
        divMain.setBackground(Color.RED);

        divAcciones.setLayout(new FlowLayout(FlowLayout.CENTER));
        divAcciones.add(btnEditarRevista);
        divAcciones.add(btnEliminarRevista);

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
            modelo.addRow(new Object[] {
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

        divMain.add(divTitulo, BorderLayout.NORTH);
        divMain.add(tablaConScroll, BorderLayout.CENTER);
        add(divMain);

        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                codigo = tabla.getValueAt(tabla.getSelectedRow(), 0).toString();
                divMain.add(divAcciones, BorderLayout.SOUTH);
                divMain.revalidate();
                divMain.repaint();
            }
        });

        // eventos de los botones del Jpanel acciones

        btnEliminarRevista.addActionListener(e -> {
            int respuesta = JOptionPane.showConfirmDialog(
                    null,
                    "¿Seguro que deseas eliminar esta revista?",
                    "Confirmar acción",
                    JOptionPane.YES_NO_OPTION);

            if (respuesta == JOptionPane.YES_OPTION) {
                RevistaDAO eliminar = new RevistaDAO();
                eliminar.eliminarRevista(codigo);
                JOptionPane.showMessageDialog(null, "Se borró con éxito " + codigo);
                divMain.removeAll();
                divMain.add(new VistaRevista()); // o VistaInicio según el caso
                divMain.revalidate();
                divMain.repaint();
            }
        });

        btnAgregarRevista.addActionListener(e -> {
            divMain.removeAll();
            VistaAgregarRevista agregar = new VistaAgregarRevista();
            divMain.add(agregar);
            divMain.revalidate();
            divMain.repaint();
        });

        btnEditarRevista.addActionListener(e -> {
            divMain.removeAll();
            VistaEditarRevista editar = new VistaEditarRevista(codigo);
            divMain.add(editar);
            divMain.revalidate();
            divMain.repaint();
        });

    }
}
