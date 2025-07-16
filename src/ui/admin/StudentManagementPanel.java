package ui.admin;

import db.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class StudentManagementPanel extends JPanel {
    private JTable table;

    public StudentManagementPanel() {
        setLayout(new BorderLayout());
        JLabel heading = new JLabel("Student Management", JLabel.CENTER);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(heading, BorderLayout.NORTH);

        table = new JTable();
        refreshTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton deleteBtn = new JButton("Delete Selected Student");
        deleteBtn.addActionListener(e -> deleteSelectedStudent());
        add(deleteBtn, BorderLayout.SOUTH);
    }

    private void refreshTable() {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT roll_no, name, branch, semester, campus, regulations FROM students";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            Vector<String> columns = new Vector<>();
            columns.add("Roll No");
            columns.add("Name");
            columns.add("Branch");
            columns.add("Semester");
            columns.add("Campus");
            columns.add("Regulations");

            Vector<Vector<Object>> data = new Vector<>();
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getString("roll_no"));
                row.add(rs.getString("name"));
                row.add(rs.getString("branch"));
                row.add(rs.getInt("semester"));
                row.add(rs.getString("campus"));
                row.add(rs.getString("regulations"));
                data.add(row);
            }

            DefaultTableModel model = new DefaultTableModel(data, columns) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            table.setModel(model);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteSelectedStudent() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        String rollNo = (String) table.getValueAt(row, 0);

        try (Connection con = DBConnection.getConnection()) {
            // First delete enrollments
            PreparedStatement deleteEnrollments = con.prepareStatement("DELETE FROM enrollments WHERE roll_no = ?");
            deleteEnrollments.setString(1, rollNo);
            deleteEnrollments.executeUpdate();

            // Then delete student
            PreparedStatement deleteStudent = con.prepareStatement("DELETE FROM students WHERE roll_no = ?");
            deleteStudent.setString(1, rollNo);
            deleteStudent.executeUpdate();

            JOptionPane.showMessageDialog(this, "Student deleted successfully");
            refreshTable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error deleting student: " + e.getMessage());
        }
    }
}
