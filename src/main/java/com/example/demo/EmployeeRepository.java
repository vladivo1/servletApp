package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmployeeRepository {

    private static final String URL_POSTGRES = "jdbc:postgresql://localhost:5432/employee";
    private static final String USER_POSTGRES = "postgres";
    private static final String PASSWORD_POSTGRES = "postgres";
    private static final String SQL_SAVE = "insert into users(name,email,country) values (?,?,?)";
    private static final String SQL_UPDATE = "update users set name=?,email=?,country=? where id=?";
    private static final String SQL_DELETE = "update users set status=true where id=?";
    private static final String SQL_GET_EMPLOYEE_BY_ID = "select * from users where id=?";
    private static final String SQL_GET_ALL_EMPLOYEE = "select * from users";

    public static Connection getConnection() {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL_POSTGRES, USER_POSTGRES, PASSWORD_POSTGRES);
            if (connection != null) {
                System.out.println("Connected to the PostgreSQL server successfully.");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static int save(Employee employee) {

        int status = 0;
        Connection connection = null;

        try {
            connection = EmployeeRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SAVE);
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setString(3, employee.getCountry());

            status = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return status;
    }

    public static int update(Employee employee) {

        int status = 0;
        Connection connection = null;

        try {

            connection = EmployeeRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE);
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setString(3, employee.getCountry());
            ps.setInt(4, employee.getId());

            status = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return status;
    }

    public static int delete(int id) {

        int status = 0;
        Connection connection = null;

        try {
            connection = EmployeeRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_DELETE);
            ps.setInt(1, id);
            status = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return status;
    }

    public static Employee getEmployeeById(int id) {

        Employee employee = new Employee();
        Connection connection = null;

        try {
            connection = EmployeeRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_GET_EMPLOYEE_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                employee.setId(rs.getInt(1));
                employee.setName(rs.getString(2));
                employee.setEmail(rs.getString(3));
                employee.setCountry(rs.getString(4));
                employee.setStatus(rs.getBoolean(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employee;
    }

    public static List<Employee> getAllEmployees() {

        List<Employee> listEmployees = new ArrayList<>();
        Connection connection = null;

        try {
            connection = EmployeeRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_GET_ALL_EMPLOYEE);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Employee employee = new Employee();

                employee.setId(rs.getInt(1));
                employee.setName(rs.getString(2));
                employee.setEmail(rs.getString(3));
                employee.setCountry(rs.getString(4));
                employee.setStatus(rs.getBoolean(5));

                listEmployees.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listEmployees;
    }
}
