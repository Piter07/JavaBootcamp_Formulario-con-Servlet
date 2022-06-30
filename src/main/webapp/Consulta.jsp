<!DOCTYPE html>
    <html lang="es">
    <head>
       <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Clientes</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous\">
                <link rel="stylesheet" href="estilos/style.css">
    </head>
    <body>

        <h1>Top Proveedor de Productos</h1>
        <%@ page import="com.roshka.bootcamp.bd.Consulta4,com.roshka.bootcamp.bd.ListaConsulta4 , java.util.List,java.util.Iterator" %>
        <table class="table">
          <thead>
              <tr>
                <th scope="col">Nombre</th>
                <th scope="col">Cantidad</th>
              </tr>
          </thead>
          <tbody>
           <%
             ListaConsulta4 consult = new ListaConsulta4();
             List<Consulta4> list = consult.listar();
             Iterator<Consulta4> iter =  list.iterator();
             Consulta4 con = null;
             while(iter.hasNext()){
                   con = iter.next();
           %>
           <tr>
                  <th scope="row"> <%= con.getNombre()%> </th>
                  <td> <%= con.getCantidad()%> </td>
           </tr>
           <% } %>
        </tbody>
      </table>
    </body>
</html>
