package controller;

import accesoadatos.ClienteDao;
import accesoadatos.ClienteImplementado;
import accesoadatos.DependenciaDao;
import accesoadatos.DependenciaImplementado;
import accesoadatos.EntradaDao;
import accesoadatos.EntradaImplementado;
import accesoadatos.ImpresoraDao;
import accesoadatos.ImpresoraImplementada;
import accesoadatos.SalidaDao;
import accesoadatos.SalidaImplementada;
import accesoadatos.SedeDao;
import accesoadatos.SedeImplementado;
import accesoadatos.TecnicoDao;
import accesoadatos.TecnicoImplementado;
import accesoadatos.TipoPapelDao;
import accesoadatos.TipoPapelImplementado;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Ciudad;
import model.Cliente;
import model.Dependencia;
import model.Entrada;
import model.Impresora;
import model.Salida;
import model.Sede;
import model.Tecnico;
import model.TipoPapel;

public class SvtController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getRequestURL().substring(request.getRequestURL().lastIndexOf("/") + 1);
        if (accion.equals("regcliente.do")) {//Registra un cliente
            System.out.println("registrando...");
            registrarCliente(request, response);
        } else if (accion.equals("ircliente.do")) {//Lleva a la pagina de registro de un cliente
            iracliente(request, response);
        } else if (accion.equals("ressede.do")) {//Lleva a la pagina de registro de una sede
            ressede(request, response);
        } else if (accion.equals("regsede.do")) {//Registra una sede
            regsede(request, response);
        } else if (accion.equals("irdependencia.do")) {//Lleva a la pagina de registro de dependencia
            dependencia(request, response);
        } else if (accion.equals("regdependencia.do")) {//Registra una dependencia
            regdependencia(request, response);
        } else if (accion.equals("regentrada.do")) {//Registra una entrada
            regentrada(request, response);
        } else if (accion.equals("irentradas.do")) {//Lleva a la pagina de registro de entradas
            irentrada(request, response);
        } else if (accion.equals("irimpresora.do")) {//Lleva a la pagina de registro de impresora
            irimpresora(request, response);
        } else if (accion.equals("regimpresora.do")) {//Registra una impresora
            regimpresora(request, response);
        } else if (accion.equals("irtecnico.do")) {//Lleva a la pagina del registro de un tecnico
            irtecnico(request, response);
        } else if (accion.equals("regtecnico.do")) {//Registrar un tecnico
            regtecnico(request, response);
        } else if (accion.equals("tipospapel.do")) {//Redirije a la pagina de tipo papel
            tipopapel(request, response);
        } else if (accion.equals("irsalidas.do")) {//Redirije a la pagina de Registro de salida
            salidas(request, response);
        } else if (accion.equals("regsalida.do")) {//Registra una salida
            regsalidas(request, response);
        } else if (accion.equals("eliminarcliente.do")) {//Elimina a un cliente
            eliminacliente(request, response);
        } else if (accion.equals("editarcliente.do")) {//Edita a un cliente
            editacliente(request, response);
        } else if (accion.equals("eliminardependencia.do")) {
            eliminardependencia(request, response);
        } else if (accion.equals("editardependencia.do")) {
            editardependencia(request, response);
        } else if (accion.equals("eliminarentrada.do")) {
            eliminarentrada(request, response);
        } else if (accion.equals("editarentrada.do")) {
            editarentrada(request, response);
        } else if (accion.equals("eliminarimpresora.do")) {
            eliminarimpresora(request, response);
        } else if (accion.equals("editarimpresora.do")) {
            editarimpresora(request, response);
        } else if (accion.equals("eliminarsalida.do")) {
            eliminarsalidas(request, response);
        } else if (accion.equals("editarsalida.do")) {
            editarsalidas(request, response);
        } else if (accion.equals("eliminarsede.do")) {
            eliminarsede(request, response);
        } else if (accion.equals("editarsede.do")) {
            editarsede(request, response);
        } else if (accion.equals("eliminartecnico.do")) {
            eliminartecnico(request, response);
        } else if (accion.equals("editartecnico.do")) {
            editartecnico(request, response);
        } else if (accion.equals("eliminartp.do")) {//Elimina tipos de papel
            eliminartp(request, response);
        } else if (accion.equals("editartipopapel.do")) {//Elimina tipos de papel
            editartp(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Inicio---------------------_>----");
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void registrarCliente(HttpServletRequest request, HttpServletResponse response) {
        try {
            ClienteDao cli = new ClienteImplementado();

            Ciudad ciudad = new Ciudad(
                    Integer.parseInt(request.getParameter("codigoCiudad")),
                    request.getParameter("nombreCiudad")
            );

            Cliente cliente = new Cliente(Integer.parseInt(
                    request.getParameter("codigoCliente")),
                    request.getParameter("nombreCliente"),
                    Integer.parseInt(request.getParameter("codigoCiudad")),
                    Integer.parseInt(request.getParameter("codigoSede"))
            );
            System.out.println(cliente.toString());
            System.out.println(ciudad.toString());

            boolean guardo = cli.guardar(cliente, ciudad);
            if (!guardo) {
                request.getSession().setAttribute("error", "Verifique los datos\nno se pudo guardar");
            } else {
                request.getSession().setAttribute("alert", "Registro guardado");
            }
            response.sendRedirect("regcliente.jsp");
        } catch (NumberFormatException e) {
            Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, e);
            request.getSession().setAttribute("alert", "No se pudo registrar en la base de datos\n" + e.getMessage());
            try {
                response.sendRedirect("regcliente.jsp");
            } catch (IOException ex) {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void iracliente(HttpServletRequest request, HttpServletResponse response) {
        try {
            SedeDao sedeDao = new SedeImplementado();
            List<Sede> sede = sedeDao.lista();
            request.getSession().setAttribute("listaSede", sede);
            response.sendRedirect("regcliente.jsp");
        } catch (IOException ex) {
            Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ressede(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect("regsede.jsp");
        } catch (IOException ex) {
            try {
                response.sendRedirect("index.jsp");
            } catch (IOException ex1) {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void regsede(HttpServletRequest request, HttpServletResponse response) {
        try {
            SedeDao sedeDao = new SedeImplementado();
            Sede sede = new Sede(
                    Integer.parseInt(request.getParameter("codigoSede")),
                    request.getParameter("nombreSede")
            );
            boolean guardo = sedeDao.guardar(sede);
            if (!guardo) {
                request.getSession().setAttribute("error", "Verifique los datos\nno se pudo guardar");
            } else {
                request.getSession().setAttribute("alert", "Registro guardado");
            }
            response.sendRedirect("regsede.jsp");
        } catch (IOException | NumberFormatException e) {
            Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, e);
            request.getSession().setAttribute("alert", "No se pudo registrar en la base de datos\n" + e.getMessage());
            try {
                response.sendRedirect("regsede.jsp");
            } catch (IOException ex) {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void dependencia(HttpServletRequest request, HttpServletResponse response) {
        try {
            SedeDao sedeDao = new SedeImplementado();
            List<Sede> sede = sedeDao.lista();
            request.getSession().setAttribute("listaSede", sede);
            response.sendRedirect("regdependencia.jsp");
        } catch (IOException ex) {
            Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void regdependencia(HttpServletRequest request, HttpServletResponse response) {
        try {
            DependenciaDao depDao = new DependenciaImplementado();

            Dependencia dep = new Dependencia(
                    Integer.parseInt(request.getParameter("codigoDependencia")),
                    request.getParameter("nombreDependencia"),
                    Integer.parseInt(request.getParameter("codigoSede"))
            );
            boolean guardo = depDao.guardar(dep);
            if (!guardo) {
                request.getSession().setAttribute("error", "Verifique los datos\nno se pudo guardar");
            } else {
                request.getSession().setAttribute("alert", "Registro guardado");
            }
            response.sendRedirect("regdependencia.jsp");
        } catch (IOException | NumberFormatException e) {
            Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, e);
            request.getSession().setAttribute("alert", "No se pudo registrar en la base de datos\n" + e.getMessage());
            try {
                response.sendRedirect("regdependencia.jsp");
            } catch (IOException ex) {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void regentrada(HttpServletRequest request, HttpServletResponse response) {
        try {
            EntradaDao entDao = new EntradaImplementado();
            Entrada ent = new Entrada(
                    Integer.parseInt(request.getParameter("codigoEntrada")),
                    "",
                    request.getParameter("tipoEntrada"),
                    Integer.parseInt(request.getParameter("cantidadEntrada")),
                    Integer.parseInt(request.getParameter("codigoTecnico"))
            );
            boolean guardo = entDao.guardar(ent);
            if (!guardo) {
                request.getSession().setAttribute("error", "Verifique los datos\nno se pudo guardar");
            } else {
                request.getSession().setAttribute("alert", "Registro guardado");
            }
            response.sendRedirect("regentrada.jsp");
        } catch (IOException | NumberFormatException e) {
            Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, e);
            request.getSession().setAttribute("alert", "No se pudo registrar en la base de datos\n" + e.getMessage());
            try {
                response.sendRedirect("regentrada.jsp");
            } catch (IOException ex) {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void irentrada(HttpServletRequest request, HttpServletResponse response) {
        try {
            TecnicoDao tecDao = new TecnicoImplementado();
            List<Tecnico> tec = tecDao.lista();
            request.getSession().setAttribute("listaTecnicos", tec);
            response.sendRedirect("regentrada.jsp");
        } catch (IOException ex) {
            Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void irimpresora(HttpServletRequest request, HttpServletResponse response) {
        try {
            DependenciaDao depDao = new DependenciaImplementado();
            List<Dependencia> dep = depDao.lista();
            request.getSession().setAttribute("listaDependencias", dep);
            response.sendRedirect("regimpresora.jsp");
        } catch (IOException ex) {
            Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void regimpresora(HttpServletRequest request, HttpServletResponse response) {
        try {
            Impresora imp = new Impresora(
                    Integer.parseInt(request.getParameter("codigoImpresora")),
                    request.getParameter("nombreImpresora"),
                    Integer.parseInt(request.getParameter("codigoDependencia"))
            );
            ImpresoraDao impDao = new ImpresoraImplementada();
            boolean guardo = impDao.guardar(imp);
            if (!guardo) {
                request.getSession().setAttribute("error", "Verifique los datos\nno se pudo guardar");
            } else {
                request.getSession().setAttribute("alert", "Registro guardado");
            }
            response.sendRedirect("regimpresora.jsp");
        } catch (IOException | NumberFormatException e) {
            Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, e);
            request.getSession().setAttribute("alert", "No se pudo registrar en la base de datos\n" + e.getMessage());
            try {
                response.sendRedirect("regimpresora.jsp");
            } catch (IOException ex) {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void irtecnico(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect("regtecnico.jsp");
        } catch (IOException ex) {
            try {
                response.sendRedirect("index.jsp");
            } catch (IOException ex1) {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void regtecnico(HttpServletRequest request, HttpServletResponse response) {
        try {
            Tecnico tec = new Tecnico(
                    Integer.parseInt(request.getParameter("codigoTecnico")),
                    request.getParameter("nombreTecnico"),
                    request.getParameter("cargoTecnico"),
                    request.getParameter("telefonoTecnico")
            );
            TecnicoDao tecDao = new TecnicoImplementado();
            boolean guardo = tecDao.guardar(tec);
            if (!guardo) {
                request.getSession().setAttribute("error", "Verifique los datos\nno se pudo guardar");
            } else {
                request.getSession().setAttribute("alert", "Registro guardado");
            }
            response.sendRedirect("regtecnico.jsp");
        } catch (IOException | NumberFormatException e) {
            Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, e);
            request.getSession().setAttribute("alert", "No se pudo registrar en la base de datos\n" + e.getMessage());
            try {
                response.sendRedirect("regtecnico.jsp");
            } catch (IOException ex) {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void tipopapel(HttpServletRequest request, HttpServletResponse response) {
        try {
            EntradaDao entDao = new EntradaImplementado();
            List<Entrada> listaEntrada = entDao.lista();
            TipoPapelDao tpDao = new TipoPapelImplementado();
            List<TipoPapel> tpapel = tpDao.lista();
            request.getSession().setAttribute("listaTipopapel", tpapel);
            request.getSession().setAttribute("listaEntradas", listaEntrada);
            response.sendRedirect("tipopapel.jsp");
        } catch (IOException ex) {
            Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void salidas(HttpServletRequest request, HttpServletResponse response) {
        try {
            TecnicoDao tecDao = new TecnicoImplementado();
            List<Tecnico> listaTec = tecDao.lista();
            ImpresoraDao impDao = new ImpresoraImplementada();
            List<Impresora> listaImp = impDao.lista();
            TipoPapelDao tpDao = new TipoPapelImplementado();
            List<TipoPapel> listaTp = tpDao.lista();
            request.getSession().setAttribute("listaTecnicos", listaTec);
            request.getSession().setAttribute("listaImpresoras", listaImp);
            request.getSession().setAttribute("listaPapeles", listaTp);
            response.sendRedirect("regsalidas.jsp");
        } catch (IOException e) {
            Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void regsalidas(HttpServletRequest request, HttpServletResponse response) {
        try {
            SalidaDao salidaDao = new SalidaImplementada();
            Salida salida = new Salida(
                    null,
                    null,
                    request.getParameter("tipoSalida"),
                    Integer.parseInt(request.getParameter("cantidadSalida")),
                    Integer.parseInt(request.getParameter("codigoTecnico")),
                    Integer.parseInt(request.getParameter("codigoImpresora")),
                    request.getParameter("codigoTecnico")
            );
            boolean guardo = salidaDao.guardar(salida);
            if (!guardo) {
                request.getSession().setAttribute("error", "Hubo un error al guardar");
            } else {
                request.getSession().setAttribute("alert", "Registro de salida guardado");
            }
            response.sendRedirect("regsalidas.jsp");
        } catch (IOException | NumberFormatException e) {
            Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, e);
            request.getSession().setAttribute("alert", "No se pudo registrar en la base de datos\n" + e.getMessage());
            try {
                response.sendRedirect("regsalidas.jsp");
            } catch (IOException ex) {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void eliminacliente(HttpServletRequest request, HttpServletResponse response) {
        try {
            ClienteDao cliDao = new ClienteImplementado();
            int codigoCliente = Integer.parseInt(request.getParameter("codigoCliente"));
            boolean elimino = cliDao.eliminar(codigoCliente);
            if (elimino) {
                request.getSession().setAttribute("alert", "Se elimino el <code>Cliente con codigo: " + codigoCliente+"</code>");

            } else {
                request.getSession().setAttribute("error", "No se pudo eliminar al <code>Cliente con codigo: " + codigoCliente+"</code>");
            }
            response.sendRedirect("index.jsp");
        } catch (IOException | NumberFormatException e) {
            try {
                request.getSession().setAttribute("error", "Ha ocurrido un error interno\n<code>" + e.getMessage()+"</code>");
                response.sendRedirect("index.jsp");
            } catch (IOException ex) {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void editacliente(HttpServletRequest request, HttpServletResponse response) {
        try {
            ClienteDao cliDao = new ClienteImplementado();
            int codigoCli = Integer.parseInt(request.getParameter("codigoCliente"));
            System.out.println("Codigo Cliente: " + codigoCli);
            Cliente selectCli = cliDao.seleccionar(codigoCli);
            if (selectCli != null) {
                int codSede = Integer.parseInt(request.getParameter("codigoSede"));
                int codCiudad = request.getParameter("codigoCiudad").equals("") ? selectCli.getCodigoCiudad() : Integer.parseInt(request.getParameter("codigoCiudad"));
                System.out.println("Codigo Ciudad: " + codCiudad);
                System.out.println("Codigo Sede: " + codSede);
                boolean actualizo = cliDao.editar(codigoCli, codCiudad, codSede);
                if (actualizo) {
                    request.getSession().setAttribute("alert", "El <code>Cliente " + selectCli.getNombreCliente() + "</code> Se actualizo correctamente");
                } else {
                    request.getSession().setAttribute("error", "No se pudo actualizar el <code>Cliente " + selectCli.getNombreCliente()+"</code>");
                }
                response.sendRedirect("index.jsp");
            }
            //Cliente cli = new Cliente(0, nombreCliente, 0, 0);
        } catch (NumberFormatException | IOException e) {
            try {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, e);
                request.getSession().setAttribute("error", "Ha ocurrido un error\n<code>" + e.getMessage()+"</code>");
                response.sendRedirect("index.jsp");
            } catch (IOException ex) {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void eliminardependencia(HttpServletRequest request, HttpServletResponse response) {
        try {
            DependenciaDao depDao = new DependenciaImplementado();
            int codigoDep = Integer.parseInt(request.getParameter("codigoDependencia"));
            boolean elimino = depDao.eliminar(codigoDep);
            if (elimino) {
                request.getSession().setAttribute("alert", "Se elimino la <code>Dependencia con codigo: " + codigoDep+"</code>");

            } else {
                request.getSession().setAttribute("error", "No se pudo eliminar la <code>Dependencia con codigo: " + codigoDep+"</code>");
            }
            response.sendRedirect("index.jsp");
        } catch (IOException | NumberFormatException e) {
            try {
                request.getSession().setAttribute("error", "Ha ocurrido un error interno\n<code>" + e.getMessage()+"</code>");
                response.sendRedirect("index.jsp");
            } catch (IOException ex) {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void editardependencia(HttpServletRequest request, HttpServletResponse response) {
        try {
            DependenciaDao depDao = new DependenciaImplementado();
            int codigo = Integer.parseInt(request.getParameter("codigoDependencia"));
            String nombre = request.getParameter("nombreDependencia");
            boolean actualizo = depDao.editar(codigo, nombre);
            if (actualizo) {
                request.getSession().setAttribute("alert", "La <code>Dependencia " + codigo + "</code> Se actualizo correctamente");
            } else {
                request.getSession().setAttribute("error", "No se pudo actualizar la <code>Dependencia " + codigo+"</code>");
            }
            response.sendRedirect("index.jsp");
        } catch (IOException | NumberFormatException e) {
            try {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, e);
                request.getSession().setAttribute("error", "Ha ocurrido un error\n<code>" + e.getMessage()+"</code>");
                response.sendRedirect("index.jsp");
            } catch (IOException ex) {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void eliminarentrada(HttpServletRequest request, HttpServletResponse response) {
        try {
            EntradaDao entDao = new EntradaImplementado();
            int codigoEntrada = Integer.parseInt(request.getParameter("codigoEntrada"));
            boolean elimino = entDao.eliminar(codigoEntrada);
            if (elimino) {
                request.getSession().setAttribute("alert", "Se elimino la <code>Entrada con codigo: " + codigoEntrada+"</code>");

            } else {
                request.getSession().setAttribute("error", "No se pudo eliminar <code>Entrada con codigo: " + codigoEntrada+"</code>");
            }
            response.sendRedirect("index.jsp");
        } catch (IOException | NumberFormatException e) {
            try {
                request.getSession().setAttribute("error", "Ha ocurrido un error interno\n<code>" + e.getMessage()+"</code>");
                response.sendRedirect("index.jsp");
            } catch (IOException ex) {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void editarentrada(HttpServletRequest request, HttpServletResponse response) {
        try {
            EntradaDao entDao = new EntradaImplementado();
            Entrada ent = new Entrada(
                    Integer.parseInt(request.getParameter("codigoEntrada")),
                    request.getParameter("fechaEntrada"),
                    request.getParameter("tipoEntrada"),
                    Integer.parseInt(request.getParameter("cantidadEntrada")),
                    Integer.parseInt(request.getParameter("codigoTecnico"))
            );
            boolean actualizo = entDao.editar(ent);
            if (actualizo) {
                request.getSession().setAttribute("alert", "La <code>Entrada " + ent.getCodigoEntrada() + "</code> Se actualizo correctamente");
            } else {
                request.getSession().setAttribute("error", "No se pudo actualizar la <code>Entrada " + ent.getCodigoEntrada()+"</code>");
            }
            response.sendRedirect("index.jsp");
        } catch (IOException | NumberFormatException e) {
            try {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, e);
                request.getSession().setAttribute("error", "Ha ocurrido un error\n<code>" + e.getMessage()+"</code>");
                response.sendRedirect("index.jsp");
            } catch (IOException ex) {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void eliminarimpresora(HttpServletRequest request, HttpServletResponse response) {
        try {
            ImpresoraDao impDao = new ImpresoraImplementada();
            int codigoImpresora = Integer.parseInt(request.getParameter("codigoImpresora"));
            boolean elimino = impDao.eliminar(codigoImpresora);
            if (elimino) {
                request.getSession().setAttribute("alert", "Se elimino la <code>Impresora con codigo: " + codigoImpresora+"</code>");

            } else {
                request.getSession().setAttribute("error", "No se pudo eliminar <code>Impresora con codigo: " + codigoImpresora+"</code>");
            }
            response.sendRedirect("index.jsp");
        } catch (NumberFormatException | IOException e) {
            try {
                request.getSession().setAttribute("error", "Ha ocurrido un error interno\n<code>" + e.getMessage()+"</code>");
                response.sendRedirect("index.jsp");
            } catch (IOException ex) {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void editarimpresora(HttpServletRequest request, HttpServletResponse response) {
        try {
            ImpresoraDao impDao = new ImpresoraImplementada();
            Impresora imp = new Impresora(
                    Integer.parseInt(request.getParameter("codigoImpresora")),
                    request.getParameter("nombreImpresora"),
                    Integer.parseInt(request.getParameter("codigoDependencia"))
            );
            boolean actualizo = impDao.editar(imp.getNombreImpresora(), imp.getCodigoImpresora(), imp.getCodigoDependencia());
            if (actualizo) {
                request.getSession().setAttribute("alert", "La <code>Impresora " + imp.getCodigoImpresora() + "</code> Se actualizo correctamente");
            } else {
                request.getSession().setAttribute("error", "No se pudo actualizar la <code>Impresora " + imp.getCodigoImpresora()+"</code>");
            }
            response.sendRedirect("index.jsp");
        } catch (NumberFormatException | IOException e) {
            try {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, e);
                request.getSession().setAttribute("error", "Ha ocurrido un error\n<code>" + e.getMessage()+"</code>");
                response.sendRedirect("index.jsp");
            } catch (IOException ex) {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void eliminarsalidas(HttpServletRequest request, HttpServletResponse response) {
        try {
            SalidaDao salDao = new SalidaImplementada();
            String codigoSalida = request.getParameter("codigoSalida");
            boolean elimino = salDao.eliminar(codigoSalida);
            if (elimino) {
                request.getSession().setAttribute("alert", "Se elimino la <code>Salida con codigo: " + codigoSalida+"</code>");

            } else {
                request.getSession().setAttribute("error", "No se pudo eliminar <code>salida con codigo: " + codigoSalida+"</code>");
            }
            response.sendRedirect("index.jsp");
        } catch (NumberFormatException | IOException e) {
            try {
                request.getSession().setAttribute("error", "Ha ocurrido un error interno\n<code>" + e.getMessage()+"</code>");
                response.sendRedirect("index.jsp");
            } catch (IOException ex) {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void editarsalidas(HttpServletRequest request, HttpServletResponse response) {
        try {
            SalidaDao salidaDao = new SalidaImplementada();
            Salida salida = new Salida(
                    request.getParameter("codigoSalida"),
                    request.getParameter("fechaSalida"),
                    request.getParameter("tipoSalida"),
                    Integer.parseInt(request.getParameter("cantidadSalida")),
                    Integer.parseInt(request.getParameter("codigoTecnico")),
                    Integer.parseInt(request.getParameter("codigoImpresora")),
                    request.getParameter("codigoPapel")
            );
            System.out.println(salida.toString());
            boolean actualizo = salidaDao.editar(salida);
            if (actualizo) {
                request.getSession().setAttribute("alert", "La <code>Salida: " + salida.getCodigoImpresora() + "</code> Se actualizo correctamente");
            } else {
                request.getSession().setAttribute("error", "No se pudo actualizar la <code>Salida: " + salida.getCodigoImpresora() + "</code>");
            }
            response.sendRedirect("index.jsp");
        } catch (NumberFormatException | IOException e) {
            try {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, e);
                request.getSession().setAttribute("error", "Ha ocurrido un error\n<code>" + e.getMessage() + "</code>");
                response.sendRedirect("index.jsp");
            } catch (IOException ex) {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void eliminarsede(HttpServletRequest request, HttpServletResponse response) {
        try {
            SedeDao sedeDao = new SedeImplementado();
            int codigoSede = Integer.parseInt(request.getParameter("codigoSede"));
            boolean elimino = sedeDao.eliminar(codigoSede);
            if (elimino) {
                request.getSession().setAttribute("alert", "Se elimino la <code>Sede con codigo: " + codigoSede + "</code>");

            } else {
                request.getSession().setAttribute("error", "No se pudo eliminar <code>Sede con codigo: " + codigoSede + "</code>");
            }
            response.sendRedirect("index.jsp");
        } catch (NumberFormatException | IOException e) {
            try {
                request.getSession().setAttribute("error", "Ha ocurrido un error interno\n<code>" + e.getMessage()+"</code>");
                response.sendRedirect("index.jsp");
            } catch (IOException ex) {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void editarsede(HttpServletRequest request, HttpServletResponse response) {
        try {
            SedeDao sedeDao = new SedeImplementado();
            int codigoSede = Integer.parseInt(request.getParameter("codSede"));
            String nombreSede = request.getParameter("nombreSede");
            boolean actualizo = sedeDao.editar(codigoSede, nombreSede);
            if (actualizo) {
                request.getSession().setAttribute("alert", "La <code>Sede: " + codigoSede + "</code> Se actualizo correctamente");
            } else {
                request.getSession().setAttribute("error", "No se pudo actualizar la <code>Sede: " + codigoSede + "</code>");
            }
            response.sendRedirect("index.jsp");
        } catch (NumberFormatException | IOException e) {
            try {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, e);
                request.getSession().setAttribute("error", "Ha ocurrido un error\n<code>" + e.getMessage() + "</code>");
                response.sendRedirect("index.jsp");
            } catch (IOException ex) {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void eliminartecnico(HttpServletRequest request, HttpServletResponse response) {
        try {
            TecnicoDao tecDao = new TecnicoImplementado();
            int codigoTecnico = Integer.parseInt(request.getParameter("codigoTecnico"));
            boolean elimino = tecDao.eliminar(codigoTecnico);
            if (elimino) {
                request.getSession().setAttribute("alert", "Se elimino el <code>Tecnico con codigo: " + codigoTecnico + "</code>");

            } else {
                request.getSession().setAttribute("error", "No se pudo eliminar <code>Tecnico con codigo: " + codigoTecnico + "</code>");
            }
            response.sendRedirect("index.jsp");
        } catch (NumberFormatException | IOException e) {
            try {
                request.getSession().setAttribute("error", "Ha ocurrido un error interno\n<code>" + e.getMessage() + "</code>");
                response.sendRedirect("index.jsp");
            } catch (IOException ex) {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void editartecnico(HttpServletRequest request, HttpServletResponse response) {

        try {
            TecnicoDao tecDao = new TecnicoImplementado();
            Tecnico tec = new Tecnico(
                    Integer.parseInt(request.getParameter("codTecnico")),
                    request.getParameter("nombreTecnico"),
                    request.getParameter("cargoTecnico"),
                    request.getParameter("telefono")
            );
            boolean actualizo = tecDao.editar(tec);
            if (actualizo) {
                request.getSession().setAttribute("alert", "El <code>Tecnico: " + tec.getCodigoTecnico() + "</code> Se actualizo correctamente");
            } else {
                request.getSession().setAttribute("error", "No se pudo actualizar el <code>Tecnico: " + tec.getCodigoTecnico() + "</code>");
            }
            response.sendRedirect("index.jsp");
        } catch (NumberFormatException | IOException e) {
            try {
                request.getSession().setAttribute("error", "Ha ocurrido un error interno\n<code>" + e.getMessage() + "</code>");
                response.sendRedirect("index.jsp");
            } catch (IOException ex) {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void eliminartp(HttpServletRequest request, HttpServletResponse response) {
        try{
            TipoPapelDao tpDao = new TipoPapelImplementado();
            String codigoTp = request.getParameter("codigoPapel");
            boolean elimino = tpDao.eliminar(codigoTp);
            if (elimino) {
                request.getSession().setAttribute("alert", "Se elimino <code>Tipo Papel con codigo: " + codigoTp + "</code>");

            } else {
                request.getSession().setAttribute("error", "No se pudo eliminar <code>Tipo Papel con codigo: " + codigoTp + "</code>");
            }
            response.sendRedirect("index.jsp");
        }catch (NumberFormatException | IOException e) {
            try {
                request.getSession().setAttribute("error", "Ha ocurrido un error interno\n<code>" + e.getMessage() + "</code>");
                response.sendRedirect("index.jsp");
            } catch (IOException ex) {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void editartp(HttpServletRequest request, HttpServletResponse response) {

        try {
            TipoPapelDao tpDao = new TipoPapelImplementado();
            String codigoTp = request.getParameter("codigoPapel");
            String nombrePapel = request.getParameter("nombrePapel");
            int codigoEntrada = Integer.parseInt(request.getParameter("codigoEntrada"));
            boolean actualizo = tpDao.editar(codigoTp, nombrePapel, codigoEntrada);
            if (actualizo) {
                request.getSession().setAttribute("alert", "El <code>Tipo Papel: " + codigoTp + "</code> Se actualizo correctamente");
            } else {
                request.getSession().setAttribute("error", "No se pudo actualizar el <code>Tipo Papel: " + codigoTp + "</code>");
            }
            response.sendRedirect("tipopapel.jsp");
        } catch (NumberFormatException | IOException e) {
            try {
                request.getSession().setAttribute("error", "Ha ocurrido un error interno\n<code>" + e.getMessage() + "</code>");
                response.sendRedirect("tipopapel.jsp");
            } catch (IOException ex) {
                Logger.getLogger(SvtController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
