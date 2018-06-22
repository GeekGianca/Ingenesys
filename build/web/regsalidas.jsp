<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Tecnico"%>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="model.Impresora"%>
<%@page import="model.TipoPapel"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Registrar Salidas</title>

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
            ArrayList<Tecnico> tecnico = (ArrayList)request.getSession().getAttribute("listaTecnicos");
            ArrayList<Impresora> impresora = (ArrayList)request.getSession().getAttribute("listaImpresoras");
            ArrayList<TipoPapel> tipopapel = (ArrayList)request.getSession().getAttribute("listaPapeles");
        %>
        <!-- Page Content -->
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <form action="regsalida.do" method="POST">
                        <div class="form-group">
                            <label for="tipoSalida">Tipo Salida</label>
                            <input type="text" class="form-control is-invalid" name="tipoSalida" id="tipoSalida" required>
                            <div class="invalid-feedback">
                                Es necesario el tipo de salida
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="cantidadSalida">Cantidad Salida</label>
                            <input type="text" class="form-control" name="cantidadSalida" id="cantidadSalida" required>
                        </div>
                        <div class="form-group">
                            <label for="codigoTecnico">Codigo Tecnico</label>
                            <select class="form-control" name="codigoTecnico" id="codigoTecnico" required>
                                <%
                                    if(tecnico.size() > 0){
                                        for(Tecnico tec:tecnico){
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
                            <label for="codigoImpresora">Codigo Impresora</label>
                            <select class="form-control" name="codigoImpresora" id="codigoImpresora" required>
                                <%
                                    if(impresora.size() > 0){
                                        for(Impresora imp:impresora){
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
                            <label for="codigoPapel">Codigo Tipo Papel</label>
                            <select class="form-control" name="codigoPapel" id="codigoPapel" required>
                                <%
                                    if(tipopapel.size() > 0){
                                        for(TipoPapel tp:tipopapel){
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
                            <td><input class="btn btn-danger" type="reset" value="Limpiar"/></td>
                            <td><input class="btn btn-success" type="submit" value="Registrar"/></td>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Bootstrap core JavaScript -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    </body>

</html>
