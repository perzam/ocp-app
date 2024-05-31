package com.example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        // Obtener variables de entorno
        String dbServiceName = System.getenv("DB_SERVICE_NAME");
        String dbUser = System.getenv("DB_USER");
        String dbPassword = System.getenv("DB_PASSWORD");
        String dbName = System.getenv("DB_NAME");

        PrintWriter out = response.getWriter();
        Connection connection = null;
        Statement statement = null;

        try {
            // Registrar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Crear la conexión a la base de datos
            String url = "jdbc:mysql://" + dbServiceName + ":3306/" + dbName + "?useSSL=false&allowPublicKeyRetrieval=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            connection = DriverManager.getConnection(url, dbUser, dbPassword);

            // Crear el statement y ejecutar la consulta
            statement = connection.createStatement();
            String query = "SELECT * FROM employees"; // Usar la tabla "employees"
            ResultSet resultSet = statement.executeQuery(query);

            // Mostrar los resultados
            out.println("<html><body>");
            out.println("<h2>Contenido de la Tabla Employees:</h2>");
            out.println("<table border='1'><tr><th>ID</th><th>Nombre</th><th>Posición</th><th>Salario</th></tr>");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");
                double salary = resultSet.getDouble("salary");
                out.println("<tr><td>" + id + "</td><td>" + name + "</td><td>" + position + "</td><td>" + salary + "</td></tr>");
            }

            out.println("</table>");
            out.println("</body></html>");

        } catch (Exception e) {
            e.printStackTrace(out);
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace(out);
            }
        }
    }
}

