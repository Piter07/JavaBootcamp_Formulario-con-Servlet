package com.roshka.bootcamp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(urlPatterns = "/listaConsulta")
public class SelectConsulta extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) {
        ServletContext context = config.getServletContext();

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.
                    getConnection(context.getInitParameter("dbUrl"),
                            context.getInitParameter("dbUser"),
                            context.getInitParameter("dbPassword"));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("  select prov.nombre, CAST(sum(f.cantidad) AS DECIMAL(12, 2)) as cantidad\n" +
                    "  from proveedor as prov\n" +
                    "  join producto as prod\n" +
                    "  on prod.proveedor_id = prov.id\n" +
                    "  join factura_detalle as f\n" +
                    "  on f.producto_id = prod.id\n" +
                    "  group by prov.id \n" +
                    "  order by cantidad desc; ");

            String texto = consultarClientes(rs);

            stmt.close();
            rs.close();

            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            out.println(texto);
        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher rd = req.getRequestDispatcher("mensajeError.html");
            rd.include(req, res);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("  select prov.nombre, CAST(sum(f.cantidad) AS DECIMAL(12, 2)) as cantidad\n" +
                    "  from proveedor as prov\n" +
                    "  join producto as prod\n" +
                    "  on prod.proveedor_id = prov.id\n" +
                    "  join factura_detalle as f\n" +
                    "  on f.producto_id = prod.id\n" +
                    "  group by prov.id \n" +
                    "  order by cantidad desc; ");

            String texto = consultarClientes(rs);

            stmt.close();
            rs.close();

            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            out.println(texto);
        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher rd = req.getRequestDispatcher("mensajeError.html");
            rd.include(req, res);
        }
    }

    private String consultarClientes(ResultSet res) throws SQLException {
        String texto = "<!DOCTYPE html>\n" +
                "<html lang=\"es\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Clientes</title>\n" +
                "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor\" crossorigin=\"anonymous\">\n" +
                "    <link rel=\"stylesheet\" href=\"estilos/style.css\">" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>Lista de clientes</h1>\n" +
                "\n" +
                "    <table class=\"table\">\n" +
                "        <thead>\n" +
                "          <tr>\n" +
                "            <th scope=\"col\">Nombre</th>\n" +
                "            <th scope=\"col\">Cantidad</th>\n" +
                "          </tr>\n" +
                "        </thead>\n" +
                "        <tbody>\n";

        while(res.next()) {
            texto += "          <tr>\n" +
                    "            <th scope=\"row\">" + res.getString("nombre") + "</th>\n" +
                    "            <td>" + res.getString("cantidad") + "</td>\n" +
                    "          </tr>\n";
        }
        texto += "        </tbody>\n" +
                "      </table>\n" +
                "</body>\n" +
                "</html>";

        //System.out.println(texto);

        return texto;
    }

    @Override
    public void destroy() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
