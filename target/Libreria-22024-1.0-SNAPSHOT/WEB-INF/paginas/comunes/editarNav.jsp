<section id="actions" class="py-4 mb-4 bg-light">
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <a href="index.jsp" class="btn btn-default btn-block">
                    <i class="fas fa-arrow-left"></i>
                    Regresar a Inicio
                </a>
            </div>
            <div class="col-md-2 offset-2">
                <button type="submit" class="btn btn-success btn-block">
                    <i class="fas fa-check"></i>
                    Guardar
                </button>
            </div>
            <div class="col-md-2">
                <a href="${pageContext.request.contextPath}/servletControlador?accion=eliminar&codigo=${producto.codigo}" class="btn btn-danger btn-block">
                    <i class="fas fa-trash"></i>
                    Eliminar Item
                </a>
            </div>

        </div>
    </div>
</section>
