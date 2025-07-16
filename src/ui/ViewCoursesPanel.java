/*package ui;

import db.DBConnection;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class ViewCoursesPanel extends JPanel {
    private JTable table;
    private JTextField enrollField;
    private JButton enrollButton;
    private Student student;
    private DashboardFrame parentFrame;

    public ViewCoursesPanel(Student student, DashboardFrame parentFrame) {
        this.student = student;
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());

        JLabel heading = new JLabel("Available Courses", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 18));
        add(heading, BorderLayout.NORTH);

        table = new JTable();
        loadCourses();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        enrollField = new JTextField(15);
        enrollField.setEnabled(false);
        enrollButton = new JButton("Enroll");
        enrollButton.setEnabled(false);

        enrollButton.addActionListener(e -> enrollInCourse());

        bottomPanel.add(new JLabel("Enroll Course Code:"));
        bottomPanel.add(enrollField);
        bottomPanel.add(enrollButton);

        add(bottomPanel, BorderLayout.SOUTH);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1 && table.getValueAt(selectedRow, 3).equals("Not Enrolled")) {
                    String courseCode = (String) table.getValueAt(selectedRow, 1);
                    enrollField.setText(courseCode);
                    enrollField.setEnabled(true);
                    enrollButton.setEnabled(true);
                } else {
                    enrollField.setText("");
                    enrollField.setEnabled(false);
                    enrollButton.setEnabled(false);
                }
            }
        });
    }

    private void loadCourses() {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT c.course_code, c.course_name, " +
                         "CASE WHEN e.course_code IS NULL THEN 'Not Enrolled' ELSE 'Enrolled' END AS status " +
                         "FROM courses c LEFT JOIN (" +
                         "    SELECT course_code FROM enrollments WHERE roll_no = ?" +
                         ") e ON c.course_code = e.course_code";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, student.getRollNo());
            ResultSet rs = ps.executeQuery();

            Vector<String> columns = new Vector<>();
            columns.add("S.No");
            columns.add("Course Code");
            columns.add("Course Name");
            columns.add("Status");

            Vector<Vector<Object>> data = new Vector<>();
            int count = 1;
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(count++);
                row.add(rs.getString("course_code"));
                row.add(rs.getString("course_name"));
                row.add(rs.getString("status"));
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

    private void enrollInCourse() {
        String courseCode = enrollField.getText();
        if (courseCode.isEmpty()) return;

        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO enrollments (roll_no, course_code) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, student.getRollNo());
            ps.setString(2, courseCode);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Enrollment successful!");
            parentFrame.refreshMyCourses(student);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Already Enrolled or Error: " + e.getMessage());
        }
    }
}*/

package ui;

import db.DBConnection;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class ViewCoursesPanel extends JPanel {
    private JTable table;
    private JTextField enrollField;
    private JButton enrollButton;
    private Student student;
    private DashboardFrame parentFrame;

    public ViewCoursesPanel(Student student, DashboardFrame parentFrame) {
        this.student = student;
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        JLabel heading = new JLabel("Available Courses", JLabel.CENTER);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 18));
        heading.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(heading, BorderLayout.NORTH);

        table = new JTable();
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        loadCourses();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        bottomPanel.setBackground(Color.WHITE);

        JLabel enrollLabel = new JLabel("Enroll Course Code:");
        enrollLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        enrollField = new JTextField(15);
        enrollField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        enrollField.setEnabled(false);

        enrollButton = new JButton("Enroll");
        enrollButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        enrollButton.setFocusPainted(false);
        enrollButton.setEnabled(false);
        enrollButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        enrollButton.addActionListener(e -> enrollInCourse());

        bottomPanel.add(enrollLabel);
        bottomPanel.add(enrollField);
        bottomPanel.add(enrollButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // Disable enroll for already enrolled courses
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1 && "Not Enrolled".equals(table.getValueAt(selectedRow, 3))) {
                    String courseCode = (String) table.getValueAt(selectedRow, 1);
                    enrollField.setText(courseCode);
                    enrollField.setEnabled(true);
                    enrollButton.setEnabled(true);
                } else {
                    enrollField.setText("");
                    enrollField.setEnabled(false);
                    enrollButton.setEnabled(false);
                }
            }
        });
    }

    private void loadCourses() {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT c.course_code, c.course_name, " +
                         "CASE WHEN e.course_code IS NULL THEN 'Not Enrolled' ELSE 'Enrolled' END AS status " +
                         "FROM courses c LEFT JOIN (" +
                         "    SELECT course_code FROM enrollments WHERE roll_no = ?" +
                         ") e ON c.course_code = e.course_code";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, student.getRollNo());
            ResultSet rs = ps.executeQuery();

            Vector<String> columns = new Vector<>();
            columns.add("S.No");
            columns.add("Course Code");
            columns.add("Course Name");
            columns.add("Status");

            Vector<Vector<Object>> data = new Vector<>();
            int count = 1;
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(count++);
                row.add(rs.getString("course_code"));
                row.add(rs.getString("course_name"));
                row.add(rs.getString("status"));
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

    private void enrollInCourse() {
        String courseCode = enrollField.getText();
        if (courseCode.isEmpty()) return;

        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO enrollments (roll_no, course_code) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, student.getRollNo());
            ps.setString(2, courseCode);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Enrollment successful!");
            parentFrame.refreshMyCourses(student);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Already Enrolled or Error: " + e.getMessage());
        }
    }
}

