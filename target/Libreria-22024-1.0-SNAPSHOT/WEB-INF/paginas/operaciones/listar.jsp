<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="es_AR"/>
<section id="libros">
    <div class="container">
        <div class="row">
             <div class="col-md-4 ">
                <div class="card text-center bg-warning text-white mb-3">
                    <div class="card-body">
                        <h3>Cantidad de Articulos</h3>
                       <h4 class="display-5">
                            ${cantidad}
                        </h4>
                    </div>
                </div>
            </div>
             <div class="col-md-4">
                <div class="card text-center bg-danger text-white mb-3">
                    <div class="card-body">
                        <h3>Stock de productos</h3>
                        <h4 class="display-5">
                            ${stock}
                        </h4>
                    </div>
                </div>
             </div>
          <div class="col-md-4 ">
                <div class="card text-center bg-success text-white mb-3">
                    <div class="card-body">
                        <h3>Precio Total de Items</h3>
                        <h4 class="display-5">                            
                            <fmt:formatNumber value="${precioTotal}" type="currency"/>
                        </h4>
                    </div>
                </div>
            </div>
                       
        </div>
        <div class="row">
            <div class="col-md-8 offset-2">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de Hardware</h4>
                    </div>
                    <table class="table table-striped" id="example">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>Producto</th>
                                <th>Modelo</th>
                                <th>Marca</th>
                                <th>Precio</th>
                                <th>Stock</th>
                                <th>Accion</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="producto" items="${user}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${producto.item}</td>
                                    <td>${producto.modelo}</td>
                                    <td>${producto.marca}</td>
                                    <td><fmt:formatNumber value="${producto.precio}" type="currency"/></td>
                                    <td>${producto.stock}</td>
                                    <!-- COMPLETAR HREF CUANDO ESTE TERMINADO JSP EDITAR -->
                                    <td>
                                        <a href="${pageContext.request.contextPath}/servletControlador?accion=editar&codigo=${producto.codigo}" 
                                           class="btn btn-warning">
                                            <i class="fas fa-pencil"></i>
                                            Editar
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
           
        </div>
    </div>
</section>

<jsp:include page="/WEB-INF/paginas/operaciones/agregar.jsp"/>