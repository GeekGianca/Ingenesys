<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Cliente"%>
<%@page import="model.Sede"%>
<%@page import="model.Dependencia"%>
<%@page import="model.Entrada"%>
<%@page import="model.Impresora"%>
<%@page import="model.Salida"%>
<%@page import="model.Tecnico"%>
<%@page import="model.TipoPapel"%>
<%@page import="accesoadatos.ClienteDao"%>
<%@page import="accesoadatos.SedeDao"%>
<%@page import="accesoadatos.DependenciaDao"%>
<%@page import="accesoadatos.EntradaDao"%>
<%@page import="accesoadatos.ImpresoraDao"%>
<%@page import="accesoadatos.SalidaDao"%>
<%@page import="accesoadatos.TecnicoDao"%>
<%@page import="accesoadatos.TipoPapelDao"%>
<%@page import="accesoadatos.ClienteImplementado"%>
<%@page import="accesoadatos.SedeImplementado"%>
<%@page import="accesoadatos.DependenciaImplementado"%>
<%@page import="accesoadatos.EntradaImplementado"%>
<%@page import="accesoadatos.ImpresoraImplementada"%>
<%@page import="accesoadatos.SalidaImplementada"%>
<%@page import="accesoadatos.TecnicoImplementado"%>
<%@page import="accesoadatos.TipoPapelImplementado"%>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">

   <head>

      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <meta name="description" content="">
      <meta name="author" content="">

      <title>INGENESYS</title>

      <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

      <link href="css/simple-sidebar.css" rel="stylesheet">

   </head>

   <body>
      <%
          SedeDao sedeDao = new SedeImplementado();
          List<Sede> sede = sedeDao.lista();
          
          DependenciaDao depeDao = new DependenciaImplementado();
          List<Dependencia> dependencias = depeDao.lista();
          
          EntradaDao entDao = new EntradaImplementado();
          List<Entrada> entradas = entDao.lista();
          
          ImpresoraDao impDao = new ImpresoraImplementada();
          List<Impresora> impresoras = impDao.lista();
          
          SalidaDao SalidaDao = new SalidaImplementada();
          List<Salida> salidas = SalidaDao.lista();
          
          TecnicoDao tecDao = new TecnicoImplementado();
          List<Tecnico> tecnicos = tecDao.lista();
          
          TipoPapelDao tpDao = new TipoPapelImplementado();
          List<TipoPapel> papeles = tpDao.lista();
          
          ClienteDao cDao = new ClienteImplementado();
          List<Cliente> clientes = cDao.lista();
      %>
      <div id="wrapper">

         <!-- Sidebar -->
         <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
               <li class="sidebar-brand">
                  <a>
                     INGENESYS
                  </a>
               </li>
               <li>
                  <a href="ircliente.do">Registrar Cliente</a>
               </li>
               <li>
                  <a href="ressede.do">Registrar Sede</a>
               </li>
               <li>
                  <a href="irdependencia.do">Registrar Dependencia</a>
               </li>
               <li>
                  <a href="irimpresora.do">Registrar Impresora</a>
               </li>
               <li>
                  <a href="irtecnico.do">Registrar Tecnico</a>
               </li>
               <li>
                  <a href="tipospapel.do">Tipos de papel</a>
               </li>
               <li>
                  <a href="irentradas.do">Entradas</a>
               </li>
               <li>
                  <a href="irsalidas.do">Salidas</a>
               </li>
            </ul>
         </div>
         <!-- /#sidebar-wrapper -->
         <!-- Page Content -->
         <div id="page-content-wrapper">
            <div class="container-fluid">
               <p class="h1"><em>Inicio Ingenesys</em></p>
               <a href="#menu-toggle" class="btn btn-success" id="menu-toggle">Menu de Registro</a>
               <!--Content-->
               <h1></h1>
               <!--Muestra si elimino o no un registro-->
               <%
                   int codCliente = 0;
                   String defaultNombre = "";
                   if(request.getSession().getAttribute("alert") != null){
                       String msg = (String)request.getSession().getAttribute("alert");
                       if(msg.length() > 0){
               %>
               <div class="alert alert-primary" role="alert">
                  <%=msg%>
               </div>
               <%
                       }
                   }
                   request.getSession().removeAttribute("alert");
               %>
               <%
                   if(request.getSession().getAttribute("error") != null){
                       String msg = (String)request.getSession().getAttribute("error");
                       if(msg.length() > 0){
               %>
               <div class="alert alert-danger" role="alert">
                  <%=msg%>
               </div>
               <%
                       }
                   }
                   request.getSession().removeAttribute("error");
               %>
               <!--Fin de la linea-->

               <!--Tabla Clientes-->
               <h1></h1>
               <!--Boton modal-->
               <p>
                  <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#clienteTabla" aria-expanded="false" aria-controls="clienteTabla">
                     Registros Clientes
                  </button>
               </p>
               <!--Fin boton modal-->
               <div class="collapse" id="clienteTabla">
                  <div class="card text-center">
                     <div class="card-header">
                        <p class="h3"><em><strong>Clientes</strong></em></p>
                     </div>
                     <div class="card-body">
                        <table class="table table-dark">
                           <thead>
                              <tr>
                                 <th scope="col">Codigo</th>
                                 <th scope="col">Nombre</th>
                                 <th scope="col">Codigo Ciudad</th>
                                 <th scope="col">Codigo Sede</th>
                                 <th scope="col">Opcion</th>
                              </tr>
                           </thead>
                           <tbody>
                              <%
                                  if(clientes.size() > 0){
                                      for(Cliente cli:clientes){
                              %>
                              <tr>
                                 <th scope="row"><%=cli.getCodigoCliente()%></th>
                                 <td><%=cli.getNombreCliente()%></td>
                                 <td><%=cli.getCodigoCiudad()%></td>
                                 <td><%=cli.getCodigoSede()%></td>
                                 <td>
                                    <div>
                                       <!--<a style="text-decoration: none; color: white;" href="editarcliente.do?codigoCliente=\<%=cli.getCodigoCliente()%>"><button class="btn btn-primary">Editar</button></a>-->
                                       <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editar" data-city="<%=cli.getCodigoCiudad()%>" data-name="<%=cli.getNombreCliente()%>" data-code="<%=cli.getCodigoCliente()%>">Editar</button>
                                       <a style="text-decoration: none; color: white;" href="eliminarcliente.do?codigoCliente=<%=cli.getCodigoCliente()%>"><button class="btn btn-danger">Eliminar</button></a>
                                    </div>
                                 </td>
                              </tr>
                              <%
                                      }
                                  }
                              %>
                           </tbody>
                        </table>
                     </div>
                     <div class="card-footer text-muted">
                        Clientes registrados <code><%=clientes.size()%></code>
                     </div>
                  </div>
               </div>
               <!--Modal Editar Cliente-->
               <div class="modal fade" id="editar" tabindex="-1" role="dialog" aria-labelledby="editar" aria-hidden="true">
                  <div class="modal-dialog" role="document">
                     <div class="modal-content">
                        <div class="modal-header">
                           <h5 class="modal-title" id="editarLabel">Cliente a modificar</h5>
                           <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                              <span aria-hidden="true">&times;</span>
                           </button>
                        </div>
                        <div class="modal-body">
                           <form action="editarcliente.do" method="POST">
                              <div class="form-group">
                                 <label for="codigoCliente" class="col-form-label">Codigo Cliente</label>
                                 <input type="number" class="form-control" name="codigoCliente" id="codigoCliente" value="<%=codCliente%>" readonly>
                              </div>
                              <div class="form-group">
                                 <label for="nombreCliente" class="col-form-label">Nombre Cliente</label>
                                 <input type="text" class="form-control" value="<%=defaultNombre%>" name="nombreCliente" id="nombreCliente" required>
                              </div>
                              <div class="form-group">
                                 <label for="codigoCiudad" class="col-form-label">Codigo Ciudad</label>
                                 <input type="number" class="form-control" name="codigoCiudad" id="codigoCiudad">
                              </div>
                              <div class="form-group">
                                 <label for="codigoSede" class="col-form-label">Codigo Sede</label>
                                 <select class="form-control" name="codigoSede" id="codigoSede" required>
                                    <%
                                        if(sede.size() > 0){
                                            for(Sede sed:sede){
                                    %>
                                    <option><%=sed.getCodigoSede()%></option>
                                    <%
                                            }
                                        }else{
                                    %>
                                    <option></option>
                                    <%
                                        }
                                    %>
                                 </select>
                              </div>
                              <div class="form-group">
                                 <button type="submit" class="btn btn-success">Actualizar</button>
                              </div>
                           </form>
                        </div>
                        <!--<div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                        </div>-->
                     </div>
                  </div>
               </div>
               <!--Fin Modal Editar Cliente-->

               <!--Tabla dependencias-->
               <h1></h1>
               <!--Boton modal-->
               <p>
                  <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#dependenciaTabla" aria-expanded="false" aria-controls="dependenciaTabla">
                     Registros Dependencias
                  </button>
               </p>
               <!--Fin boton modal-->
               <div class="collapse" id="dependenciaTabla">
                  <div class="card text-center">
                     <div class="card-header">
                        <p class="h3"><em><strong>Dependencias</strong></em></p>
                     </div>
                     <div class="card-body">
                        <table class="table table-dark">
                           <thead>
                              <tr>
                                 <th scope="col">Codigo</th>
                                 <th scope="col">Nombre Dependencia</th>
                                 <th scope="col">Codigo Sede</th>
                                 <th scope="col">Opcion</th>
                              </tr>
                           </thead>
                           <tbody>
                              <%
                                  if(dependencias.size() > 0){
                                      for(Dependencia dep:dependencias){
                              %>
                              <tr>
                                 <th scope="row"><%=dep.getCodigoDependencia()%></th>
                                 <td><%=dep.getNombreDependencia()%></td>
                                 <td><%=dep.getCodigoSede()%></td>
                                 <td>
                                    <div>                                        
                                       <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editarDep" data-code="<%=dep.getCodigoDependencia()%>" data-name="<%=dep.getNombreDependencia()%>">Editar</button>
                                       <a style="text-decoration: none; color: white;" href="eliminardependencia.do?codigoDependencia=<%=dep.getCodigoDependencia()%>"><button class="btn btn-danger">Eliminar</button></a>
                                    </div>
                                 </td>
                              </tr>
                              <%
                                      }
                                  }
                              %>
                           </tbody>
                        </table>
                     </div>
                     <div class="card-footer text-muted">
                        Dependencias registradas <code><%=dependencias.size()%></code>
                     </div>
                  </div>
               </div>
               <!--Modal Editar Dependencia-->
               <div class="modal fade" id="editarDep" tabindex="-1" role="dialog" aria-labelledby="editarDepLabel" aria-hidden="true">
                  <div class="modal-dialog" role="document">
                     <div class="modal-content">
                        <div class="modal-header">
                           <h5 class="modal-title" id="editarDepLabel">Editar Dependencia</h5>
                           <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                              <span aria-hidden="true">&times;</span>
                           </button>
                        </div>
                        <div class="modal-body">
                           <form action="editardependencia.do" method="POST">
                              <div class="form-group">
                                 <label for="codigoDependencia" class="col-form-label">Codigo Dependencia</label>
                                 <input type="text" class="form-control" name="codigoDependencia" id="codigoDependencia" readonly>
                              </div>
                              <div class="form-group">
                                 <label for="nombreDependencia" class="col-form-label">Nombre Dependencia</label>
                                 <input type="text" class="form-control" name="nombreDependencia" id="nombreDependencia" required>
                              </div>
                              <div class="form-group">
                                 <label for="codigoSede" class="col-form-label">Codigo Sede</label>
                                 <select class="form-control" name="codigoSede" id="codigoSede" required>
                                    <%
                                        if(sede.size() > 0){
                                            for(Sede sed:sede){
                                    %>
                                    <option><%=sed.getCodigoSede()%></option>
                                    <%
                                            }
                                        }else{
                                    %>
                                    <option></option>
                                    <%
                                        }
                                    %>
                                 </select>
                              </div>
                              <div class="form-group">
                                 <button type="submit" class="btn btn-success">Actualizar</button>
                              </div>
                           </form>
                        </div>
                        <!--<div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                        </div>-->
                     </div>
                  </div>
               </div>
               <!--Fin Modal Editar Dependencia-->

               <!--Tabla Entradas-->
               <h1></h1>
               <!--Boton modal-->
               <p>
                  <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#entradaTabla" aria-expanded="false" aria-controls="entradaTabla">
                     Registros Entradas
                  </button>
               </p>
               <!--Fin boton modal-->
               <div class="collapse" id="entradaTabla">
                  <div class="card text-center">
                     <div class="card-header">
                        <p class="h3"><em><strong>Entradas</strong></em></p>
                     </div>
                     <div class="card-body">
                        <table class="table table-dark">
                           <thead>
                              <tr>
                                 <th scope="col">Codigo</th>
                                 <th scope="col">Fecha</th>
                                 <th scope="col">Tipo</th>
                                 <th scope="col">Cantidad</th>
                                 <th scope="col">Codigo Tecnico</th>
                                 <th scope="col">Opcion</th>
                              </tr>
                           </thead>
                           <tbody>
                              <%
                                  if(entradas.size() > 0){
                                      for(Entrada ent:entradas){
                              %>
                              <tr>
                                 <th scope="row"><%=ent.getCodigoEntrada()%></th>
                                 <td><%=ent.getFechaEntrada()%></td>
                                 <td><%=ent.getTipoEntrada()%></td>
                                 <td><%=ent.getCantidadEntrada()%></td>
                                 <td><%=ent.getCodigoTecnico()%></td>
                                 <td>
                                    <div>
                                       <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editarEnt" data-code="<%=ent.getCodigoEntrada()%>" data-date="<%=ent.getFechaEntrada()%>" data-type="<%=ent.getTipoEntrada()%>" data-count="<%=ent.getCantidadEntrada()%>">Editar</button>
                                       <a style="text-decoration: none; color: white;" href="eliminarentrada.do?codigoEntrada=<%=ent.getCodigoEntrada()%>"><button class="btn btn-danger">Eliminar</button></a>
                                    </div>
                                 </td>
                              </tr>
                              <%
                                      }
                                  }
                              %>
                           </tbody>
                        </table>
                     </div>
                     <div class="card-footer text-muted">
                        Entradas registradas <code><%=entradas.size()%></code>
                     </div>
                  </div>
               </div>
               <!--Modal Editar Entradas-->
               <div class="modal fade" id="editarEnt" tabindex="-1" role="dialog" aria-labelledby="editarEntLabel" aria-hidden="true">
                  <div class="modal-dialog" role="document">
                     <div class="modal-content">
                        <div class="modal-header">
                           <h5 class="modal-title" id="editarEntLabel">Editar Entrada</h5>
                           <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                              <span aria-hidden="true">&times;</span>
                           </button>
                        </div>
                        <div class="modal-body">
                           <form action="editarentrada.do" method="POST">
                              <div class="form-group">
                                 <label for="codigoEntrada" class="col-form-label">Codigo Entrada</label>
                                 <input type="text" class="form-control" name="codigoEntrada" id="codigoEntrada" readonly>
                              </div>
                              <div class="form-group">
                                 <label for="fechaEntrada" class="col-form-label">Fecha Entradadate</label>
                                 <input type="text" class="form-control" name="fechaEntrada" id="fechaEntrada" required>
                              </div>
                              <div class="form-group">
                                 <label for="tipoEntrada" class="col-form-label">Tipo Entrada</label>
                                 <input type="text" class="form-control" name="tipoEntrada" id="tipoEntrada" required>
                              </div>
                              <div class="form-group">
                                 <label for="cantidadEntrada" class="col-form-label">Cantidad Entrada</label>
                                 <input type="text" class="form-control" name="cantidadEntrada" id="cantidadEntrada" required>
                              </div>
                              <div class="form-group">
                                 <label for="codigoTecnico" class="col-form-label">Codigo Tecnico</label>
                                 <select class="form-control" name="codigoTecnico" id="codigoTecnico" required>
                                    <%
                                        if(tecnicos.size() > 0){
                                            for(Tecnico tec:tecnicos){
                                    %>
                                    <option><%=tec.getCodigoTecnico()%></option>
                                    <%
                                            }
                                        }else{
                                    %>
                                    <option></option>
                                    <%
                                        }
                                    %>
                                 </select>
                              </div>
                              <div class="form-group">
                                 <button type="submit" class="btn btn-success">Actualizar</button>
                              </div>
                           </form>
                        </div>
                        <!--<div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                        </div>-->
                     </div>
                  </div>
               </div>
               <!--Fin Modal Editar Entradas-->

               <!--Tabla Impresoras-->
               <h1></h1>
               <!--Boton modal-->
               <p>
                  <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#impresoraTabla" aria-expanded="false" aria-controls="impresoraTabla">
                     Registros Impresoras
                  </button>
               </p>
               <!--Fin boton modal-->
               <div class="collapse" id="impresoraTabla">
                  <div class="card text-center">
                     <div class="card-header">
                        <p class="h3"><em><strong>Impresoras</strong></em></p>
                     </div>
                     <div class="card-body">
                        <table class="table table-dark">
                           <thead>
                              <tr>
                                 <th scope="col">Codigo</th>
                                 <th scope="col">Nombre</th>
                                 <th scope="col">Codigo Dependencia</th>
                                 <th scope="col">Opcion</th>
                              </tr>
                           </thead>
                           <tbody>
                              <%
                                  if(impresoras.size() > 0){
                                      for(Impresora imp:impresoras){
                              %>
                              <tr>
                                 <th scope="row"><%=imp.getCodigoImpresora()%></th>
                                 <td><%=imp.getNombreImpresora()%></td>
                                 <td><%=imp.getCodigoDependencia()%></td>
                                 <td>
                                    <div>
                                       <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editarImp" data-code="<%=imp.getCodigoImpresora()%>" data-name="<%=imp.getNombreImpresora()%>">Editar</button>
                                       <a style="text-decoration: none; color: white;" href="eliminarimpresora.do?codigoImpresora=<%=imp.getCodigoImpresora()%>"><button class="btn btn-danger">Eliminar</button></a>
                                    </div>
                                 </td>
                              </tr>
                              <%
                                      }
                                  }
                              %>
                           </tbody>
                        </table>
                     </div>
                     <div class="card-footer text-muted">
                        Impresoras registradas <code><%=impresoras.size()%></code>
                     </div>
                  </div>
               </div>
               <!--Modal Editar Impresoras-->
               <div class="modal fade" id="editarImp" tabindex="-1" role="dialog" aria-labelledby="editarImpLabel" aria-hidden="true">
                  <div class="modal-dialog" role="document">
                     <div class="modal-content">
                        <div class="modal-header">
                           <h5 class="modal-title" id="editarImpLabel">Editar Impresora</h5>
                           <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                              <span aria-hidden="true">&times;</span>
                           </button>
                        </div>
                        <div class="modal-body">
                           <form action="editarimpresora.do" method="POST">
                              <div class="form-group">
                                 <label for="codigoImpresora" class="col-form-label">Codigo Impresora</label>
                                 <input type="text" class="form-control" name="codigoImpresora" id="codigoImpresora" readonly>
                              </div>
                              <div class="form-group">
                                 <label for="nombreImpresora" class="col-form-label">Nombre Impresora</label>
                                 <input type="text" class="form-control" name="nombreImpresora" id="nombreImpresora" required>
                              </div>
                              <div class="form-group">
                                 <label for="codigoDependencia" class="col-form-label">Codigo Dependencia</label>
                                 <select class="form-control" name="codigoDependencia" id="codigoDependencia" required>
                                    <%
                                        if(dependencias.size() > 0){
                                            for(Dependencia dep:dependencias){
                                    %>
                                    <option><%=dep.getCodigoDependencia()%></option>
                                    <%
                                            }
                                        }else{
                                    %>
                                    <option></option>
                                    <%
                                        }
                                    %>
                                 </select>
                              </div>
                              <div class="form-group">
                                 <button type="submit" class="btn btn-success">Actualizar</button>
                              </div>
                           </form>
                        </div>
                        <!--<div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                        </div>-->
                     </div>
                  </div>
               </div>
               <!--Fin Modal Editar Impresoras-->

               <!--Tabla Salidas-->
               <h1></h1>
               <!--Boton modal-->
               <p>
                  <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#salidaTabla" aria-expanded="false" aria-controls="salidaTabla">
                     Registros Salidas
                  </button>
               </p>
               <!--Fin boton modal-->
               <div class="collapse" id="salidaTabla">
                  <div class="card text-center">
                     <div class="card-header">
                        <p class="h3"><em><strong>Salidas</strong></em></p>
                     </div>
                     <div class="card-body">
                        <table class="table table-dark">
                           <thead>
                              <tr>
                                 <th scope="col">Codigo</th>
                                 <th scope="col">Fecha</th>
                                 <th scope="col">Tipo</th>
                                 <th scope="col">Cantidad</th>
                                 <th scope="col">Codigo Tecnico</th>
                                 <th scope="col">Codigo Impresora</th>
                                 <th scope="col">Codigo Papel</th>
                                 <th scope="col">Opcion</th>
                              </tr>
                           </thead>
                           <tbody>
                              <%
                                  if(salidas.size() > 0){
                                      for(Salida sd:salidas){
                              %>
                              <tr>
                                 <th scope="row"><%=sd.getCodigoSalida()%></th>
                                 <td><%=sd.getFechaSalida()%></td>
                                 <td><%=sd.getTipoSalida()%></td>
                                 <td><%=sd.getCantidadSalida()%></td>
                                 <td><%=sd.getCodigoTecnico()%></td>
                                 <td><%=sd.getCodigoImpresora()%></td>
                                 <td><%=sd.getCodigoPapel()%></td>
                                 <td>
                                    <div>
                                       <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editarSd" data-code="<%=sd.getCodigoSalida()%>" data-date="<%=sd.getFechaSalida()%>" data-type="<%=sd.getTipoSalida()%>" data-count="<%=sd.getCantidadSalida()%>">Editar</button>
                                       <a style="text-decoration: none; color: white;" href="eliminarsalida.do?codigoSalida=<%=sd.getCodigoSalida()%>"><button class="btn btn-danger">Eliminar</button></a>
                                    </div>
                                 </td>
                              </tr>
                              <%
                                      }
                                  }
                              %>
                           </tbody>
                        </table>
                     </div>
                     <div class="card-footer text-muted">
                        Salidas registradas <code><%=salidas.size()%></code>
                     </div>
                  </div>
               </div>
               <!--Modal Editar Salidas-->
               <div class="modal fade" id="editarSd" tabindex="-1" role="dialog" aria-labelledby="editarSdLabel" aria-hidden="true">
                  <div class="modal-dialog" role="document">
                     <div class="modal-content">
                        <div class="modal-header">
                           <h5 class="modal-title" id="editarSdLabel">Editar Salida</h5>
                           <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                              <span aria-hidden="true">&times;</span>
                           </button>
                        </div>
                        <div class="modal-body">
                           <form action="editarsalida.do" method="POST">
                              <div class="form-group">
                                 <label for="codigoSalida" class="col-form-label">Codigo Salida</label>
                                 <input type="text" class="form-control" name="codigoSalida" id="codigoSalida" readonly>
                              </div>
                              <div class="form-group">
                                 <label for="fechaSalida" class="col-form-label">Fecha Salida</label>
                                 <input type="text" class="form-control" name="fechaSalida" id="fechaSalida" required>
                              </div>
                              <div class="form-group">
                                 <label for="tipoSalida" class="col-form-label">Tipo Salida</label>
                                 <input type="text" class="form-control" name="tipoSalida" id="tipoSalida" required>
                              </div>
                              <div class="form-group">
                                 <label for="cantidadSalida" class="col-form-label">Cantidad Salida</label>
                                 <input type="text" class="form-control" name="cantidadSalida" id="cantidadSalida" required>
                              </div>
                              <div class="form-group">
                                 <label for="codigoTecnico" class="col-form-label">Codigo Tecnico</label>
                                 <select class="form-control" name="codigoTecnico" id="codigoTecnico" required>
                                    <%
                                        if(tecnicos.size() > 0){
                                            for(Tecnico tec:tecnicos){
                                    %>
                                    <option><%=tec.getCodigoTecnico()%></option>
                                    <%
                                            }
                                        }else{
                                    %>
                                    <option></option>
                                    <%
                                        }
                                    %>
                                 </select>
                              </div>
                              <div class="form-group">
                                 <label for="codigoImpresora" class="col-form-label">Codigo Impresora</label>
                                 <select class="form-control" name="codigoImpresora" id="codigoImpresora" required>
                                    <%
                                        if(impresoras.size() > 0){
                                            for(Impresora imp:impresoras){
                                    %>
                                    <option><%=imp.getCodigoImpresora()%></option>
                                    <%
                                            }
                                        }else{
                                    %>
                                    <option></option>
                                    <%
                                        }
                                    %>
                                 </select>
                              </div>
                              <div class="form-group">
                                 <label for="codigoPapel" class="col-form-label">Codigo Tipo Papel</label>
                                 <select class="form-control" name="codigoPapel" id="codigoPapel" required>
                                    <%
                                        if(papeles.size() > 0){
                                            for(TipoPapel tp:papeles){
                                    %>
                                    <option><%=tp.getCodigoTipopapel()%></option>
                                    <%
                                            }
                                        }else{
                                    %>
                                    <option></option>
                                    <%
                                        }
                                    %>
                                 </select>
                              </div>
                              <div class="form-group">
                                 <button type="submit" class="btn btn-success">Actualizar</button>
                              </div>
                           </form>
                        </div>
                        <!--<div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                        </div>-->
                     </div>
                  </div>
               </div>
               <!--Fin Modal Editar Salidas-->

               <!--Tabla Sede-->
               <h1></h1>
               <!--Boton modal-->
               <p>
                  <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#sedeTabla" aria-expanded="false" aria-controls="sedeTabla">
                     Registros Sedes
                  </button>
               </p>
               <!--Fin boton modal-->
               <div class="collapse" id="sedeTabla">
                  <div class="card text-center">
                     <div class="card-header">
                        <p class="h3"><em><strong>Sedes</strong></em></p>
                     </div>
                     <div class="card-body">
                        <table class="table table-dark">
                           <thead>
                              <tr>
                                 <th scope="col">Codigo</th>
                                 <th scope="col">Nombre</th>
                                 <th scope="col">Opcion</th>
                              </tr>
                           </thead>
                           <tbody>
                              <%
                                  if(sede.size() > 0){
                                      for(Sede s:sede){
                              %>
                              <tr>
                                 <th scope="row"><%=s.getCodigoSede()%></th>
                                 <td><%=s.getNombreSede()%></td>
                                 <td>
                                    <div>
                                       <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editarSede" data-code="<%=s.getCodigoSede()%>" data-name="<%=s.getNombreSede()%>">Editar</button>
                                       <a style="text-decoration: none; color: white;" href="eliminarsede.do?codigoSede=<%=s.getCodigoSede()%>"><button class="btn btn-danger">Eliminar</button></a>
                                    </div>
                                 </td>
                              </tr>
                              <%
                                      }
                                  }
                              %>
                           </tbody>
                        </table>
                     </div>
                     <div class="card-footer text-muted">
                        Sedes registradas <code><%=sede.size()%></code>
                     </div>
                  </div>
               </div>
               <!--Modal Editar Sede-->
               <div class="modal fade" id="editarSede" tabindex="-1" role="dialog" aria-labelledby="editarSedeLabel" aria-hidden="true">
                  <div class="modal-dialog" role="document">
                     <div class="modal-content">
                        <div class="modal-header">
                           <h5 class="modal-title" id="editarSedeLabel">Editar Sede</h5>
                           <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                              <span aria-hidden="true">&times;</span>
                           </button>
                        </div>
                        <div class="modal-body">
                           <form action="editarsede.do" method="POST">
                              <div class="form-group">
                                 <label for="codSede" class="col-form-label">Codigo Sede</label>
                                 <input type="number" class="form-control" name="codSede" id="codSede" readonly>
                              </div>
                              <div class="form-group">
                                 <label for="nombreSede" class="col-form-label">Nombre Sede</label>
                                 <input type="text" class="form-control" name="nombreSede" id="nombreSede" required>
                              </div>
                              <div class="form-group">
                                 <button type="submit" class="btn btn-success">Actualizar</button>
                              </div>
                           </form>
                        </div>
                        <!--<div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                        </div>-->
                     </div>
                  </div>
               </div>
               <!--Fin Modal Editar Sede-->

               <!--Tabla Tecnico-->
               <h1></h1>
               <!--Boton modal-->
               <p>
                  <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#tecnicoTabla" aria-expanded="false" aria-controls="tecnicoTabla">
                     Registros Tecnicos
                  </button>
               </p>
               <!--Fin boton modal-->
               <div class="collapse" id="tecnicoTabla">
                  <div class="card text-center">
                     <div class="card-header">
                        <p class="h3"><em><strong>Tecnicos</strong></em></p>
                     </div>
                     <div class="card-body">
                        <table class="table table-dark">
                           <thead>
                              <tr>
                                 <th scope="col">Codigo</th>
                                 <th scope="col">Nombre</th>
                                 <th scope="col">Cargo</th>
                                 <th scope="col">Telefono</th>
                                 <th scope="col">Opcion</th>
                              </tr>
                           </thead>
                           <tbody>
                              <%
                                  if(tecnicos.size() > 0){
                                      for(Tecnico tec:tecnicos){
                              %>
                              <tr>
                                 <th scope="row"><%=tec.getCodigoTecnico()%></th>
                                 <th scope="row"><%=tec.getNombreTecnico()%></th>
                                 <th scope="row"><%=tec.getCargoTecnico()%></th>
                                 <th scope="row"><%=tec.getTelefono()%></th>
                                 <td>
                                    <div>
                                       <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editarTec" data-code="<%=tec.getCodigoTecnico()%>" data-name="<%=tec.getNombreTecnico()%>" data-emp="<%=tec.getCargoTecnico()%>" data-ph="<%=tec.getTelefono()%>">Editar</button>
                                       <a style="text-decoration: none; color: white;" href="eliminartecnico.do?codigoTecnico=<%=tec.getCodigoTecnico()%>"><button class="btn btn-danger">Eliminar</button></a>
                                    </div>
                                 </td>
                              </tr>
                              <%
                                      }
                                  }
                              %>
                           </tbody>
                        </table>
                     </div>
                     <div class="card-footer text-muted">
                        Tecnicos registrados <code><%=tecnicos.size()%></code>
                     </div>
                  </div>
               </div>
               <!--Modal Editar Tecnico-->
               <div class="modal fade" id="editarTec" tabindex="-1" role="dialog" aria-labelledby="editarTecLabel" aria-hidden="true">
                  <div class="modal-dialog" role="document">
                     <div class="modal-content">
                        <div class="modal-header">
                           <h5 class="modal-title" id="editarTecLabel">Editar Tecnico</h5>
                           <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                              <span aria-hidden="true">&times;</span>
                           </button>
                        </div>
                        <div class="modal-body">
                           <form action="editartecnico.do" method="POST">
                              <div class="form-group">
                                 <label for="codTecnico" class="col-form-label">Codigo Tecnico</label>
                                 <input type="text" class="form-control" name="codTecnico" id="codTecnico" readonly>
                              </div>
                              <div class="form-group">
                                 <label for="nombreTecnico" class="col-form-label">Nombre Tecnico</label>
                                 <input type="text" class="form-control" name="nombreTecnico" id="nombreTecnico" required>
                              </div>
                              <div class="form-group">
                                 <label for="cargoTecnico" class="col-form-label">Cargo Tecnico</label>
                                 <input type="text" class="form-control" name="cargoTecnico" id="cargoTecnico" required>
                              </div>
                              <div class="form-group">
                                 <label for="telefono" class="col-form-label">Telefono</label>
                                 <input type="text" class="form-control" name="telefono" id="telefono" required>
                              </div>
                              <div class="form-group">
                                 <button type="submit" class="btn btn-success">Actualizar</button>
                              </div>
                           </form>
                        </div>
                        <!--<div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                        </div>-->
                     </div>
                  </div>
               </div>
               <!--Fin Modal Editar Tecnico-->

               <!--Boton Tipo papel-->
               <a href="tipospapel.do" style="text-decoration: none; color: white;">
                  <button class="btn btn-primary" type="button">
                     Tipo Papel
                  </button>
               </a>
               <!--Fin boton -->
            </div>
         </div>
         <!-- /#page-content-wrapper -->

      </div>
      <!-- /#wrapper -->

      <!-- Bootstrap core JavaScript -->
      <script src="vendor/jquery/jquery.min.js"></script>
      <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

      <!-- Menu Toggle Script -->
      <script>
          $("#menu-toggle").click(function (e) {
              e.preventDefault();
              $("#wrapper").toggleClass("toggled");
          });
      </script>

      <!--Modal Toggle Script Cliente-->
      <script>
          $('#editar').on('show.bs.modal', function (event) {
              console.log("Entro al script cliente");
              var button = $(event.relatedTarget); // Button that triggered the modal
              var code = button.data('code'); // Extract info from data-* attributes
              var name = button.data('name');
              var city = button.data('city');
              // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
              // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
              var modal = $(this);
              modal.find('.modal-title').text('Editar cliente: ' + code);
              document.getElementById("codigoCliente").value = "" + code + "";
              document.getElementById("nombreCliente").value = "" + name + "";
              document.getElementById("codigoCiudad").value = "" + city + "";
              //modal.find('.modal-body input').val(recipient);
          });
      </script>

      <!--Modal Toggle Script Dependencia-->
      <script>
          $('#editarDep').on('show.bs.modal', function (event) {
              console.log("Entro al script");
              var button = $(event.relatedTarget); // Button that triggered the modal
              var codeDep = button.data('code'); // Extract info from data-* attributes
              var nameDep = button.data('name');
              console.log(codeDep);
              console.log(nameDep);
              // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
              // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
              var modal = $(this);
              modal.find('.modal-title').text('Editar Dependencia: ' + codeDep);
              document.getElementById("codigoDependencia").value = "" + codeDep + "";
              document.getElementById("nombreDependencia").value = "" + nameDep + "";
              //modal.find('.modal-body input').val(recipient);
          });
      </script>

      <!--Modal Toggle Script Entrada-->
      <script>
          $('#editarEnt').on('show.bs.modal', function (event) {
              console.log("Entro al script");
              var button = $(event.relatedTarget); // Button that triggered the modal
              var codeEnt = button.data('code'); // Extract info from data-* attributes
              var nameEnt = button.data('date');
              var typeEnt = button.data('type');
              var cantEnt = button.data('count');
              console.log(codeEnt);
              console.log(nameEnt);
              console.log(typeEnt);
              console.log(cantEnt);
              // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
              // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
              var modal = $(this);
              modal.find('.modal-title').text('Editar Entrada: ' + codeEnt);
              document.getElementById("codigoEntrada").value = "" + codeEnt + "";
              document.getElementById("fechaEntrada").value = "" + nameEnt + "";
              document.getElementById("tipoEntrada").value = "" + typeEnt + "";
              document.getElementById("cantidadEntrada").value = "" + cantEnt + "";
              //modal.find('.modal-body input').val(recipient);
          });
      </script>

      <!--Modal Toggle Script Impresora-->
      <script>
          $('#editarImp').on('show.bs.modal', function (event) {
              console.log("Entro al script");
              var button = $(event.relatedTarget); // Button that triggered the modal
              var code = button.data('code'); // Extract info from data-* attributes
              var name = button.data('name');
              console.log(code);
              console.log(name);
              // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
              // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
              var modal = $(this);
              modal.find('.modal-title').text('Editar Impresora: ' + code);
              document.getElementById("codigoImpresora").value = "" + code + "";
              document.getElementById("nombreImpresora").value = "" + name + "";
              //modal.find('.modal-body input').val(recipient);
          });
      </script>

      <!--Modal Toggle Script Salida-->
      <script>
          $('#editarSd').on('show.bs.modal', function (event) {
              console.log("Entro al script");
              var button = $(event.relatedTarget); // Button that triggered the modal
              var codeSd = button.data('code'); // Extract info from data-* attributes
              var dateSd = button.data('date');
              var typeSd = button.data('type');
              var countSd = button.data('count');
              console.log(codeSd);
              console.log(dateSd);
              console.log(typeSd);
              console.log(countSd);
              // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
              // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
              var modal = $(this);
              modal.find('.modal-title').text('Editar Salida: ' + codeSd);
              document.getElementById("codigoSalida").value = "" + codeSd + "";
              document.getElementById("fechaSalida").value = "" + dateSd + "";
              document.getElementById("tipoSalida").value = "" + typeSd + "";
              document.getElementById("cantidadSalida").value = "" + countSd + "";
              //modal.find('.modal-body input').val(recipient);
          });
      </script>

      <!--Modal Toggle Script Sede-->
      <script>
          $('#editarSede').on('show.bs.modal', function (event) {
              console.log("Entro al script");
              var button = $(event.relatedTarget); // Button that triggered the modal
              var codeSede = button.data('code'); // Extract info from data-* attributes
              var nameSede = button.data('name');
              console.log(codeSede);
              console.log(nameSede);
              // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
              // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
              var modal = $(this);
              modal.find('.modal-title').text('Editar Sede: ' + codeSede);
              document.getElementById("codSede").value = "" + codeSede + "";
              document.getElementById("nombreSede").value = "" + nameSede + "";
              //modal.find('.modal-body input').val(recipient);
          });
      </script>

      <!--Modal Toggle Script Tecnico-->
      <script>
          $('#editarTec').on('show.bs.modal', function (event) {
              console.log("Entro al script");
              var button = $(event.relatedTarget); // Button that triggered the modal
              var codeTec = button.data('code'); // Extract info from data-* attributes
              var nameTec = button.data('name');
              var employ = button.data('emp');
              var phone = button.data('ph');
              console.log(codeTec);
              console.log(nameTec);
              console.log(employ);
              console.log(phone);
              // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
              // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
              var modal = $(this);
              modal.find('.modal-title').text('Editar Tecnico: ' + codeTec);
              document.getElementById("codTecnico").value = "" + codeTec + "";
              document.getElementById("nombreTecnico").value = "" + nameTec + "";
              document.getElementById("cargoTecnico").value = "" + employ + "";
              document.getElementById("telefono").value = "" + phone + "";
              //modal.find('.modal-body input').val(recipient);
          });
      </script>
   </body>

</html>
