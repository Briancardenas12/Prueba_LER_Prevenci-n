<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" 
              crossorigin="anonymous"> 

        <script src="https://kit.fontawesome.com/3556f97ea7.js" crossorigin="anonymous"></script>


        <title>EDITAR PERSONA</title>
    </head>
    <body>
        <jsp:include page="../componentes/cabecero.jsp" />

        <form action="${pageContext.request.contextPath}/ServletControlador?accion=modificar&idPersona=${persona.idPersona}"
              method="POST" class="was-validated">
            <jsp:include page="../componentes/botonesNavegacionEdicion.jsp" /> 
            <section id="details"> 
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <div class="card">
                                <div class="card-header">
                                    <h4>Editar Persona</h4>
                                </div>
                                <div class="card-body">
                                    <div class="form-group">
                                        <label for="nombres">Nombres</label>
                                        <input type="text" class="form-control" name="nombres" required value="${persona.nombres}">
                                    </div>
                                    <div class="form-group">
                                        <label for="apellidos">Apellido</label>
                                        <input type="text" class="form-control" name="apellidos" required value="${persona.apellidos}">
                                    </div><!-- comment -->
                                    <div class="form-group">
                                        <label for="cedula">Cedula</label>
                                        <input type="text" class="form-control" name="cedula" required value="${persona.cedula}">
                                    </div>
                                    <div class="form-group">
                                        <label for="fecha_nacimiento">Fecha Nacimiento</label>
                                        <input type="date" class="form-control" name="fecha_nacimiento" required value="${persona.fechaNacimiento}">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </form>
    </body>
</html>

