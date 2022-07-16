package web;

import data.LibreriaDAO;
import entity.Productos;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/servletControlador")
public class ServletControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        if (accion != null){
            switch (accion){
                case "editar":
                    editarProducto(req, res);
                    break;
                case "eliminar":
                    eliminarProducto(req,res);
                    break;
                default:
                    accionDefault(req, res);
                    break;
            }
        } else {
            accionDefault(req, res);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String accion = req.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    guardarProducto(req, res);
                    break;
                case "modificar":
                    modificarProducto(req, res);
                    break;
                default:
                    accionDefault(req, res);
                    break;

            }
        }
    }

    private void accionDefault(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Productos> productos = new LibreriaDAO().findAll();
        HttpSession sesion = req.getSession();
        sesion.setAttribute("user", productos);
        sesion.setAttribute("stock", calcularCant(productos));
        sesion.setAttribute("precioTotal", calcularPrecio(productos));
        sesion.setAttribute("cantidad", calcularCantidad(productos));
        //req.getRequestDispatcher("libros.jsp").forward(req, res);
        res.sendRedirect("items.jsp");
    }

    private void guardarProducto(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String item = req.getParameter("item");
        String modelo = req.getParameter("modelo");
        String marca = req.getParameter("marca");
        double precio = Double.parseDouble(req.getParameter("precio"));
        int stock = Integer.parseInt(req.getParameter("stock"));

        Productos producto = new Productos(item, modelo, marca, precio, stock);
        int regMod = new LibreriaDAO().create(producto);
        System.out.println("Insertados: " + regMod);
        accionDefault(req, res);
    }

    private void editarProducto(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int codigo = Integer.parseInt(req.getParameter("codigo"));
        Productos lib = new LibreriaDAO().findItemById(codigo);
        req.setAttribute("producto", lib);
        req.getRequestDispatcher("/WEB-INF/paginas/operaciones/editar.jsp").forward(req, res);
    }

    private void modificarProducto(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String item = req.getParameter("item");
        String modelo = req.getParameter("modelo");
        String marca = req.getParameter("marca");        
        double precio = Double.parseDouble(req.getParameter("precio"));
        int stock = Integer.parseInt(req.getParameter("stock"));

        int codigo = Integer.parseInt(req.getParameter("codigo"));

        Productos lib = new Productos (codigo, item, modelo, marca, precio, stock);

        int regMod = new LibreriaDAO().update(lib);

        System.out.println("SE ACTUALIZARON: " + regMod + " REGISTROS");

        accionDefault(req, res);
    }
    
    private void eliminarProducto(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int codigo = Integer.parseInt(req.getParameter("codigo"));
        
        int regDel = new LibreriaDAO().delete(codigo);
        
        System.out.println("REGISTROS ELIMINADOS: "+ regDel);
        
        accionDefault(req, res);
    }

    private int calcularCant(List<Productos> lista) {
        int cantidad = 0;
        for (int i = 0; i < lista.size(); i++) {
            cantidad += lista.get(i).getStock();
        }
        return cantidad;
    }

    private double calcularPrecio(List<Productos> lista) {
        double precio = 0;
        for (int i = 0; i < lista.size(); i++) {
            precio += (lista.get(i).getStock() * lista.get(i).getPrecio());
        }
        return precio;
    }
    private int calcularCantidad(List<Productos> lista) {    
        int cant = lista.size();
        return cant;
    }

}
