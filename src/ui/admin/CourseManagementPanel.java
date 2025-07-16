// CourseManagementPanel.java
package ui.admin;

import db.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.Vector;

public class CourseManagementPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;

    public CourseManagementPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        JLabel heading = new JLabel("Manage Courses", JLabel.CENTER);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 20));
        heading.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(heading, BorderLayout.NORTH);

        table = new JTable();
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        loadCourses();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(Color.WHITE);

        JButton addBtn = new JButton("Add");
        //JButton editBtn = new JButton("Edit");
        JButton deleteBtn = new JButton("Delete");

        addBtn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        //editBtn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        deleteBtn.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        addBtn.addActionListener(this::handleAdd);
        //editBtn.addActionListener(this::handleEdit);
        deleteBtn.addActionListener(this::handleDelete);

        controlPanel.add(addBtn);
        //controlPanel.add(editBtn);
        controlPanel.add(deleteBtn);

        add(controlPanel, BorderLayout.SOUTH);
    }

    private void loadCourses() {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM courses";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            Vector<String> columns = new Vector<>();
            columns.add("Course Code");
            columns.add("Course Name");

            Vector<Vector<Object>> data = new Vector<>();
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getString("course_code"));
                row.add(rs.getString("course_name"));
                data.add(row);
            }

            model = new DefaultTableModel(data, columns) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            table.setModel(model);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleAdd(ActionEvent e) {
        JTextField codeField = new JTextField();
        JTextField nameField = new JTextField();

        Object[] message = {
                "Course Code:", codeField,
                "Course Name:", nameField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Course", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String code = codeField.getText();
            String name = nameField.getText();
            try (Connection con = DBConnection.getConnection()) {
                String sql = "INSERT INTO courses (course_code, course_name) VALUES (?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, code);
                ps.setString(2, name);
                ps.executeUpdate();
                loadCourses();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    private void handleDelete(ActionEvent e) {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a course to delete.");
            return;
        }

        String code = (String) model.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete course " + code + "?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection con = DBConnection.getConnection()) {
                
                PreparedStatement ps1 = con.prepareStatement("DELETE FROM enrollments WHERE course_code = ?");
                ps1.setString(1, code);
                ps1.executeUpdate();

                PreparedStatement ps2 = con.prepareStatement("DELETE FROM courses WHERE course_code = ?");
                ps2.setString(1, code);
                ps2.executeUpdate();
                loadCourses();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }
}
