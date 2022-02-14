
package controlador;

import modelo.Devolucion;
import modelo.DevolucionDAO;
import vista.DevolucionFrm;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CtrlDevolucion implements ActionListener {

    //Importar
    DevolucionFrm frm = new DevolucionFrm();
    Devolucion devolucion = new Devolucion();
    DevolucionDAO devolucionDAO = new DevolucionDAO();
    DefaultTableModel ModeloTabla = new DefaultTableModel();

    //Variables globales
    private int Id;
    private String Fecha;
    private String Motivo;
    private String Tipo;
    private int Factura;
    private int PQRS;
    

    public CtrlDevolucion(DevolucionFrm frm) {
        frm.setVisible(true);
        agregarEventos();
        listarTabla();
    }
    

    private void listarTabla() {
        String[] titulos = new String[]{"Id_devolucion", "fecha_devolucion", "motivo_devolucion", "tipo_devolucion", "id_factura", "id_PQRS"};
        ModeloTabla = new DefaultTableModel(titulos, 0);
        List<Devolucion> listaDevolucion = devolucionDAO.listar();
        for (Devolucion devolucion : listaDevolucion) {
            ModeloTabla.addRow(new Object[]{devolucion.getIdDevolucion(), devolucion.getFechaDevolucion(), devolucion.getMotivoDevolucion(), devolucion.getTipoDevolucion(), devolucion.getIdFactura(), devolucion.getIdPQRS()});

        }
        frm.getTblTabla().setModel(ModeloTabla);
        frm.getTblTabla().setPreferredSize(new Dimension(350, ModeloTabla.getRowCount() * 16));
    }

    private void agregar() {
        try {
            if (validarDatos()) {
                if (cargarDatos()) {
                    Devolucion devolucion = new Devolucion(Id,Fecha, Motivo, Tipo, Factura, PQRS);
                    devolucionDAO.agregarDevolucion(devolucion);
                    JOptionPane.showMessageDialog(null, "registro exitoso");
                    limpiarCampos();
                }
            }

        } catch (Exception ex) {
            System.out.println("Error metodo agregar en el controlador" + ex);
        } finally {
            listarTabla();
        }

    }

    private void buscar() {
        try {
            if (cargar()) {
                if ("".equals(frm.getTxtID().getText())) {
                    JOptionPane.showMessageDialog(null, "Campo Buscar Vacio");
                } else {
                    Devolucion devolucion = new Devolucion(Id);
                    devolucionDAO.buscarDevolucion(Id);
                    String[] titulos = new String[]{"Id_devolucion", "fecha_devolucion", "motivo_devolucion", "tipo_devolucion", "id_factura", "id_PQRS"};
                    ModeloTabla = new DefaultTableModel(titulos, 0);
                    List<Devolucion> listaDevolucion = devolucionDAO.buscarDevolucion(Id);
                    if (listaDevolucion.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "id no Encotrado");
                    } else {

                        for (Devolucion devolucion2 : listaDevolucion) {
                            ModeloTabla.addRow(new Object[]{devolucion2.getIdDevolucion(), devolucion2.getFechaDevolucion(), devolucion2.getMotivoDevolucion(), devolucion2.getTipoDevolucion(), devolucion2.getIdFactura(), devolucion2.getIdPQRS()});
                        }
                        frm.getTblTabla().setModel(ModeloTabla);
                        frm.getTblTabla().setPreferredSize(new Dimension(350, ModeloTabla.getRowCount() * 16));

                        JOptionPane.showMessageDialog(null, "busqueda exitosa");

                    }

                }
            }
        } catch (Exception e) {
            System.out.println("Error metodo seleccionar controlador" + e);

        }

    }

    private void actualizar() {
        try {
            if (validarDatos()) {
                if (cargarDatos()) {
                    Devolucion devolucion = new Devolucion(Id, Fecha, Motivo, Tipo, Factura, PQRS);
                    devolucionDAO.actualizarDevolucion(devolucion);
                    JOptionPane.showMessageDialog(null, "actualizacion exitosa");
                    limpiarCampos();
                }
            }
        } catch (HeadlessException ex) {
            System.out.println("Error metodo actualizar controlador" + ex);
        } finally {
            listarTabla();
        }

    }

    private void eliminar() {
        try {
            if (cargar()) {
                Devolucion devolucion = new Devolucion(Id);
                devolucionDAO.eliminarDevolucion(devolucion);
                JOptionPane.showMessageDialog(null, "eliminacion exitosa");
                limpiarCampos();
            }
        } catch (HeadlessException ex) {
            System.out.println("Error metodo eliminar controlador" + ex);
        } finally {
            listarTabla();
        }

    }

    private void llenarCampos(MouseEvent e) {
        try {
            JTable target = (JTable) e.getSource();
            frm.getTxtID().setText(frm.getTblTabla().getModel().getValueAt(target.getSelectedRow(), 0).toString());
            frm.getTxtFecha().setText(frm.getTblTabla().getModel().getValueAt(target.getSelectedRow(), 1).toString());
            frm.getTxtMotivo().setText(frm.getTblTabla().getModel().getValueAt(target.getSelectedRow(), 2).toString());
            frm.getTxtTipo().setText(frm.getTblTabla().getModel().getValueAt(target.getSelectedRow(), 3).toString());
            frm.getTxtFactura().setText(frm.getTblTabla().getModel().getValueAt(target.getSelectedRow(), 4).toString());
            frm.getTxtPQRS().setText(frm.getTblTabla().getModel().getValueAt(target.getSelectedRow(), 5).toString());
        } catch (Exception ex) {
            System.out.println("Error al llenar " + ex);
        }
    }

    private boolean validarDatos() {
        if ("".equals(frm.getTxtFecha().getText()) || "".equals(frm.getTxtMotivo().getText()) || "".equals(frm.getTxtTipo()) || "".equals(frm.getTxtFactura()) || "".equals(frm.getTxtPQRS())) {
            JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void limpiarCampos() {
        frm.getTxtID().setText("");
        frm.getTxtFecha().setText("");
        frm.getTxtMotivo().setText("");
        frm.getTxtTipo().setText("");
        frm.getTxtFactura().setText("");
        frm.getTxtPQRS().setText("");

    }

    private boolean cargar() {
        try {
            Id = Integer.parseInt(frm.getTxtID().getText());

            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "el campo Id debe ser texto", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error al cargar datos numericos " + ex);
            return false;
        }
    }

    // metodo 3 en 1 1. carga variables globales 2. convierte los valores 3. valida que los campos sean numericos.
    private boolean cargarDatos() {
        try {
            Id = Integer.parseInt(frm.getTxtID().getText());
            Fecha = frm.getTxtFecha().getText();
            Motivo = frm.getTxtMotivo().getText();
            Tipo = frm.getTxtTipo().getText();
            Factura = Integer.parseInt(frm.getTxtFactura().getText());
            PQRS = Integer.parseInt(frm.getTxtPQRS().getText());
            
            return true;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Los campos id, valor, cat deben ser numericos", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error al cargar datos" + ex);
            return false;
        }
    }

    private void agregarEventos(){
        frm.getBtnCrear().addActionListener(this);
        frm.getTbnBuscar().addActionListener(this);
        frm.getBtnLimpiar().addActionListener(this);
        frm.getBtnActualizar().addActionListener(this);
        frm.getBtnEliminar().addActionListener(this);

        frm.getTblTabla().addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent ex) {
                limpiarCampos();
                llenarCampos(ex);
                listarTabla();
                
            }
        });
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == frm.getBtnCrear()) {
            agregar();
        }
        if (ae.getSource() == frm.getTbnBuscar()) {
            buscar();
        }
        if (ae.getSource() == frm.getBtnActualizar()) {
            actualizar();
        }
        if (ae.getSource() == frm.getBtnEliminar()) {
            eliminar();
        }
        if (ae.getSource() == frm.getBtnLimpiar()) {
            limpiarCampos();

        }
    }
}

    
    



