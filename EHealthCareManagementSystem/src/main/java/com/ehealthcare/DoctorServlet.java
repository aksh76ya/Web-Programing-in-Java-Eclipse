package com.ehealthcare;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
@WebServlet("/registerDoctor")
public class DoctorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String specialization = request.getParameter("specialization");
        String phone = request.getParameter("phone");

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ehealthcare_db", "root", "Pooji@2005");

            String sql = "INSERT INTO doctors (name, specialization, phone) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, specialization);
            statement.setString(3, phone);
            statement.executeUpdate();

            connection.close();
            response.sendRedirect("index.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}