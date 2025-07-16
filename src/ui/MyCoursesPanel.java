/*package ui;

import db.DBConnection;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class MyCoursesPanel extends JPanel {
    private JTable table;
    private Student student;

    public MyCoursesPanel(Student student, DashboardFrame parentFrame) {
        this.student = student;
        setLayout(new BorderLayout());

        JLabel heading = new JLabel("My Enrolled Courses", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 18));
        add(heading, BorderLayout.NORTH);

        table = new JTable();
        loadMyCourses();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("← Back");
        backButton.addActionListener(e -> parentFrame.repaint());
        JPanel topLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topLeft.add(backButton);
        add(topLeft, BorderLayout.WEST);
    }

    private void loadMyCourses() {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT c.course_code, c.course_name FROM courses c " +
                         "JOIN enrollments e ON c.course_code = e.course_code " +
                         "WHERE e.roll_no = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, student.getRollNo());
            ResultSet rs = ps.executeQuery();

            Vector<String> columns = new Vector<>();
            columns.add("S.No");
            columns.add("Course Code");
            columns.add("Course Name");

            Vector<Vector<Object>> data = new Vector<>();
            int count = 1;
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(count++);
                row.add(rs.getString("course_code"));
                row.add(rs.getString("course_name"));
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
}*/

package ui;

import db.DBConnection;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class MyCoursesPanel extends JPanel {
    private JTable table;
    private Student student;

    public MyCoursesPanel(Student student, DashboardFrame parentFrame) {
        this.student = student;
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        JLabel heading = new JLabel("My Enrolled Courses", JLabel.CENTER);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 18));
        heading.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(heading, BorderLayout.NORTH);

        table = new JTable();
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        loadMyCourses();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadMyCourses() {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT c.course_code, c.course_name FROM courses c " +
                         "JOIN enrollments e ON c.course_code = e.course_code " +
                         "WHERE e.roll_no = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, student.getRollNo());
            ResultSet rs = ps.executeQuery();

            Vector<String> columns = new Vector<>();
            columns.add("S.No");
            columns.add("Course Code");
            columns.add("Course Name");

            Vector<Vector<Object>> data = new Vector<>();
            int count = 1;
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(count++);
                row.add(rs.getString("course_code"));
                row.add(rs.getString("course_name"));
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
}

