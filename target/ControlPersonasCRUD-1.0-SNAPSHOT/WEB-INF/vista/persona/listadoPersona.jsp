<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_MX" />

<section id="estudiantes"> <!-- REVISAR -->
    <div class="container">
        <div class="row">

            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de Personas</h4>
                    </div>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>Nombre completo</th>
                                <th>Cedula</th>
                                <th>Fecha</th>
                                <th></th>
                            </tr>

                        </thead>
                        <tbody>

                            <c:forEach var="persona" items="${personas}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${persona.nombres}  ${persona.apellidos}</td>

                                    <td>${persona.cedula} </td>
                                    <td>${persona.fechaNacimiento} </td>
                                    <td>
        <a href="${pageContext.request.contextPath}/ServletControlador?accion=editar&idPersona=${persona.idPersona}"
                                           class ="btn btn-secondary">
                                            <i class="fas fa-angle-double-right"></i> Editar

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
</div>


</section>
<jsp:include page="agregarPersona.jsp" />
