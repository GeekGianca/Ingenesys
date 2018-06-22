<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.TipoPapel"%>
<%@page import="model.Entrada"%>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">

   <head>

      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <meta name="description" content="">
      <meta name="author" content="">

      <title>Registros de tipos de papel</title>

      <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

      <style>
         body {
             padding-top: 54px;
         }
         @media (min-width: 992px) {
             body {
                 padding-top: 56px;
             }
         }
      </style>

   </head>

   <body>
      <%
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
          request.getSession().removeAttribute("alert");
      %>
      <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
         <div class="container">
            <a class="navbar-brand" href="regsalidas.jsp">Ingenesys</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
               <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
               <ul class="navbar-nav ml-auto">
                  <li class="nav-item active">
                     <a class="nav-link" href="index.jsp">Regresar
                        <span class="sr-only">(current)</span>
                     </a>
                  </li>
                  <li>
                     <div class="btn-group">
                        <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                           Opcion
                        </button>
                        <div class="dropdown-menu">
                           <a class="dropdown-item" href="ressede.do">Sede</a>
                           <a class="dropdown-item" href="irdependencia.do">Dependencias</a>
                           <a class="dropdown-item" href="ircliente.do">Clientes</a>
                           <a class="dropdown-item" href="irimpresora.do">Impresoras</a>
                           <a class="dropdown-item" href="irtecnico.do">Tecnico</a>
                           <a class="dropdown-item" href="irentradas.do">Entradas</a>
                           <div class="dropdown-divider"></div>
                           <a class="dropdown-item" href="tipospapel.do">Tipos papel</a>
                        </div>
                     </div>
                  </li>
               </ul>
            </div>
         </div>
      </nav>
      <%
          ArrayList<TipoPapel> tipopapel = (ArrayList)request.getSession().getAttribute("listaTipopapel");
          ArrayList<Entrada> entradas = (ArrayList)request.getSession().getAttribute("listaEntradas");
      %>
      <!-- Page Content -->
      <div class="container">
         <div class="row">
            <div class="col-lg-12">
               <br>
               <div class="card">
                  <div class="card-header">
                     Tipos de Papel
                  </div>
                  <div class="card-body">
                     <table class="table table-striped">
                        <thead>
                           <tr>
                              <th scope="col">Codigo</th>
                              <th scope="col">Nombre</th>
                              <th scope="col">Codigo Entrada</th>
                              <th scope="col">Opcion</th>
                           </tr>
                        </thead>
                        <tbody>
                           <%
                               if(tipopapel.size() > 0){
                                   for(TipoPapel tp:tipopapel){
                           %>
                           <tr>
                              <th scope="row"><%=tp.getCodigoTipopapel()%></th>
                              <td><%=tp.getNombreTipopapel()%></td>
                              <td><%=tp.getCodigoEntrada()%></td>
                              <td>
                                 <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editar" data-code="<%=tp.getCodigoTipopapel()%>" data-type="<%=tp.getNombreTipopapel()%>">Editar</button>
                                 <a style="text-decoration: none; color: white;" href="eliminartp.do?codigoPapel=<%=tp.getCodigoTipopapel()%>"><button class="btn btn-danger">Eliminar</button></a>
                              </td>
                           </tr>
                           <%
                                   }
                               }
                           %>
                        </tbody>
                     </table>
                  </div>
               </div>
               <div>
                  <p><em>Los campos de tipo de papel se agregan automaticamente por medio de un trigger al insertar una entrada</em></p>
               </div>
               <!--Modal Editar Tipo Papel-->
               <div class="modal fade" id="editar" tabindex="-1" role="dialog" aria-labelledby="editarLabel" aria-hidden="true">
                  <div class="modal-dialog" role="document">
                     <div class="modal-content">
                        <div class="modal-header">
                           <h5 class="modal-title" id="editarLabel">Editar Tipo Papel</h5>
                           <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                              <span aria-hidden="true">&times;</span>
                           </button>
                        </div>
                        <div class="modal-body">
                           <form action="editartipopapel.do" method="POST">
                              <div class="form-group">
                                 <label for="codigoPapel" class="col-form-label">Codigo Tipo Papel</label>
                                 <input type="text" class="form-control" name="codigoPapel" id="codigoPapel" readonly>
                              </div>
                              <div class="form-group">
                                 <label for="nombrePapel" class="col-form-label">Nombre Papel</label>
                                 <input type="text" class="form-control" name="nombrePapel" id="nombrePapel" required>
                              </div>
                              <div class="form-group">
                                 <label for="codigoEntrada" class="col-form-label">Codigo Entrada</label>
                                 <select class="form-control" name="codigoEntrada" id="codigoEntrada" required>
                                    <%
                                        if(entradas.size() > 0){
                                            for(Entrada e:entradas){
                                    %>
                                    <option><%=e.getCodigoEntrada()%></option>
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
               <!--Fin Modal Editar Tecnico-->
            </div>
         </div>
      </div>

      <!-- Bootstrap core JavaScript -->
      <script src="vendor/jquery/jquery.min.js"></script>
      <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

      <!--Modal Toggle Script tipo papel-->
      <script>
          $('#editar').on('show.bs.modal', function (event) {
              console.log("Entro al script");
              var button = $(event.relatedTarget); // Button that triggered the modal
              var code = button.data('code'); // Extract info from data-* attributes
              var name = button.data('type');
              console.log(code);
              console.log(name);
              // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
              // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
              var modal = $(this);
              modal.find('.modal-title').text('Editar Tecnico: ' + code);
              document.getElementById("codigoPapel").value = "" + code + "";
              document.getElementById("nombrePapel").value = "" + name + "";
              //modal.find('.modal-body input').val(recipient);
          });
      </script>

   </body>

</html>

