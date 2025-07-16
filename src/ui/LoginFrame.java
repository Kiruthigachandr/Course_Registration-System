/*package ui;

import db.DBConnection;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginFrame extends JFrame {
    private JTextField rollNoField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Login");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        JLabel rollLabel = new JLabel("Roll Number:");
        rollNoField = new JTextField(15);
        JLabel passLabel = new JLabel("Password:");
        passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(e -> authenticate());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.gridx = 0; gbc.gridy = 0; add(rollLabel, gbc);
        gbc.gridx = 1; add(rollNoField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; add(passLabel, gbc);
        gbc.gridx = 1; add(passwordField, gbc);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; add(loginButton, gbc);

        setVisible(true);
    }

    private void authenticate() {
        String roll = rollNoField.getText();
        String pass = new String(passwordField.getPassword());

        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM students WHERE roll_no = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, roll);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Student student = new Student(
                    rs.getString("roll_no"),
                    rs.getString("name"),
                    rs.getString("branch"),
                    rs.getInt("semester"),
                    rs.getString("campus"),
                    rs.getString("regulations")
                );
                dispose();
                new DashboardFrame(student);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}*/

package ui;

import db.DBConnection;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginFrame extends JFrame {
    private JTextField rollNoField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Student Login");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.WHITE);

        JLabel title = new JLabel("Course Registration Portal", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));

        JLabel rollLabel = new JLabel("Roll Number:");
        rollLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        rollNoField = new JTextField(20);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        loginButton.setFocusPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(e -> authenticate());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        add(rollLabel, gbc); gbc.gridx = 1;
        add(rollNoField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(passLabel, gbc); gbc.gridx = 1;
        add(passwordField, gbc);

        gbc.gridy++; gbc.gridx = 0; gbc.gridwidth = 2;
        add(loginButton, gbc);

        setVisible(true);
    }

    private void authenticate() {
        String roll = rollNoField.getText();
        String pass = new String(passwordField.getPassword());

        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM students WHERE roll_no = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, roll);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Student student = new Student(
                        rs.getString("roll_no"),
                        rs.getString("name"),
                        rs.getString("branch"),
                        rs.getInt("semester"),
                        rs.getString("campus"),
                        rs.getString("regulations") );
                dispose();
                new DashboardFrame(student);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

