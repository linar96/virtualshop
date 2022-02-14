
package modelo;

import Utilidades.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DevolucionDAO {
    Conexion conexion = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List listar() {
        String sql = "select * from devolucion_productos";
        List<Devolucion> lista = new ArrayList<>();
        try {
            con = conexion.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Devolucion devolucion_productos = new Devolucion();
                devolucion_productos.setIdDevolucion(rs.getInt(1));
                devolucion_productos.setFechaDevolucion(rs.getString(2));
                devolucion_productos.setMotivoDevolucion(rs.getString(3));
                devolucion_productos.setTipoDevolucion(rs.getString(4));
                devolucion_productos.setIdFactura(rs.getInt(5));
                devolucion_productos.setIdPQRS(rs.getInt(6));
               
            }
        } catch (SQLException ex) {
            System.out.println("error al listar: " + ex);
        }
        return lista;
    }// fin metodo listar
    
    public List buscarDevolucion(int buscar) {
        
        String sql="select * from devolucion_productos WHERE Id_devolucion ="+buscar;
            List<Devolucion> lista = new ArrayList<>();
        try {
            con = conexion.Conectar();
            // permite consultar el producto correspondiente al ID ingresado como parametro 
           
            
            con = conexion.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
            
            Devolucion devolucion_productos = new Devolucion();
                devolucion_productos.setIdDevolucion(rs.getInt(1));
                devolucion_productos.setFechaDevolucion(rs.getString(2));
                devolucion_productos.setMotivoDevolucion(rs.getString(3));
                devolucion_productos.setTipoDevolucion(rs.getString(4));
                devolucion_productos.setIdFactura(rs.getInt(5));
                devolucion_productos.setIdPQRS(rs.getInt(6));
                lista.add(devolucion_productos);
                }
            
        }catch (SQLException ex) {
            System.out.println("error al obtenerProducto: " + ex);
        }
        return lista;
}
    
    public int agregarDevolucion(Devolucion devolucion_productos) {

        int registros = 0;
        try {
            con = conexion.Conectar();
            ps = con.prepareStatement("insert into devolucion_productos(Id_devolucion, fecha_devolucion, motivo_devolucion, tipo_devolucion, id_factura, id_PQRS) values (?, ?, ?, ?, ?, ?) ");
            ps.setInt(1, devolucion_productos.getIdDevolucion());
            ps.setString(2, devolucion_productos.getFechaDevolucion());
            ps.setString(3, devolucion_productos.getMotivoDevolucion()); 
            ps.setString(4, devolucion_productos.getTipoDevolucion());
            ps.setInt(5, devolucion_productos.getIdFactura());
            ps.setInt(6, devolucion_productos.getIdPQRS());
            registros = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error al agregar: " + ex);
        } finally {

        }
        return registros;
    }
    
    public void actualizarDevolucion(Devolucion devolucion_productos) {
        String sql_update="UPDATE devolucion_productos SET fecha_devolucion = ?, motivo_devolucion = ?, tipo_devolucion = ?, id_factura = ?, id_PQRS = ? WHERE Id_ devolucion = ?";
        try {
            con = conexion.Conectar();
            ps = con.prepareStatement(sql_update);
            ps.setString(1, devolucion_productos.getFechaDevolucion());
            ps.setString(2, devolucion_productos.getMotivoDevolucion());   
            ps.setString(3, devolucion_productos.getTipoDevolucion());
            ps.setInt(4, devolucion_productos.getIdFactura());
            ps.setInt(5, devolucion_productos.getIdPQRS());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error al actualizar: " + ex);
        }
        
        
        
        
    }
    
    public void eliminarDevolucion(Devolucion devolucion_productos) {
                
       String sql_update="DELETE FROM devolucion_productos WHERE Id_devolucion = ?";
        try {
            con = conexion.Conectar();
            ps = con.prepareStatement(sql_update);
            ps.setInt(1, devolucion_productos.getIdDevolucion());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error al eliminar: " + ex);
        }
    }
}
