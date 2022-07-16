package data;

import static data.Conexion.close;
import static data.Conexion.getConexion;
import entity.Productos;
import java.sql.*;
import java.util.*;

public class LibreriaDAO {
    private static final String SQL_CREATE = "INSERT INTO productos(item, modelo,marca, precio, stock) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_READ = "SELECT * FROM productos";
    private static final String SQL_UPDATE_PRECIO = "UPDATE productos SET precio = ? WHERE codigo = ?";
    private static final String SQL_UPDATE_STOCK = "UPDATE productos SET stock = ? WHERE codigo = ?";
    private static final String SQL_UPDATE= "UPDATE productos SET item = ?, modelo = ?, marca = ?, precio = ?, stock = ? WHERE codigo = ?";
    private static final String SQL_DELETE = "DELETE FROM productos WHERE codigo = ?";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM productos WHERE codigo = ?";

    public List<Productos> findAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Productos producto = null;
        List<Productos> productos = new ArrayList();

        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_READ);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int codigo = rs.getInt(1);
                String item = rs.getString(2);
                String modelo = rs.getString(3);
                String marca = rs.getString(4);
                double precio = rs.getDouble(5);
                int stock = rs.getInt(6);

                producto = new Productos(codigo, item, modelo ,marca ,precio, stock);

                productos.add(producto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }

        return productos;
    }
    
    public Productos findItemById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Productos producto = null;
        
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()){
                int codigo = rs.getInt(1);
                String item = rs.getString(2);
                String modelo = rs.getString(3);
                String marca = rs.getString(4);
                double precio = rs.getDouble(5);
                int stock = rs.getInt(6);

                producto = new Productos(codigo, item ,modelo, marca,precio,stock);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return producto;
    }

    public int create(Productos producto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_CREATE);
            stmt.setString(1, producto.getItem());
            stmt.setString(2, producto.getModelo());
            stmt.setString(3, producto.getMarca());
            stmt.setDouble(4, producto.getPrecio());
            stmt.setInt(5, producto.getStock());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public int updatePrecio(Productos producto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE_PRECIO);
            stmt.setDouble(1, producto.getPrecio());
            stmt.setInt(2, producto.getCodigo());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public int updateCopias(Productos producto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE_STOCK);
            stmt.setInt(1, producto.getStock());
            stmt.setInt(2, producto.getCodigo());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    public int update(Productos producto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, producto.getItem());
            stmt.setString(2, producto.getModelo());
            stmt.setString(3, producto.getMarca());
            stmt.setDouble(4, producto.getPrecio());
            stmt.setInt(5, producto.getStock());
            stmt.setInt(6, producto.getCodigo());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public int delete(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    
}
