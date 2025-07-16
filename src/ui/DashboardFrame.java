/*package ui;

import model.Student;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public DashboardFrame(Student student) {
        setTitle("Student Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Main layout setup
        setLayout(new BorderLayout());

        // Top Student Details Panel
        JPanel studentInfoPanel = createStudentInfoPanel(student);
        add(studentInfoPanel, BorderLayout.NORTH);

        // CardLayout Panel for tabs
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        ViewCoursesPanel viewCoursesPanel = new ViewCoursesPanel(student, this);
        MyCoursesPanel myCoursesPanel = new MyCoursesPanel(student, this);

        mainPanel.add(viewCoursesPanel, "VIEW");
        mainPanel.add(myCoursesPanel, "MY");

        add(mainPanel, BorderLayout.CENTER);

        // Bottom Navigation Tabs
        JPanel bottomNav = new JPanel();
        JButton viewCoursesBtn = new JButton("View Courses");
        JButton myCoursesBtn = new JButton("My Courses");

        viewCoursesBtn.addActionListener(e -> cardLayout.show(mainPanel, "VIEW"));
        myCoursesBtn.addActionListener(e -> cardLayout.show(mainPanel, "MY"));

        bottomNav.add(viewCoursesBtn);
        bottomNav.add(myCoursesBtn);

        add(bottomNav, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createStudentInfoPanel(Student student) {
        JPanel panel = new JPanel(new GridLayout(3, 4, 20, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Roll Number:"));
        panel.add(new JTextField(student.getRollNo()));
        panel.add(new JLabel("Name:"));
        panel.add(new JTextField(student.getName()));

        panel.add(new JLabel("Branch:"));
        panel.add(new JTextField(student.getBranch()));
        panel.add(new JLabel("Semester:"));
        panel.add(new JTextField(String.valueOf(student.getSemester())));

        panel.add(new JLabel("Campus:"));
        panel.add(new JTextField(student.getCampus()));
        panel.add(new JLabel("Regulations:"));
        panel.add(new JTextField(student.getRegulations()));

        for (Component c : panel.getComponents()) {
            if (c instanceof JTextField) {
                ((JTextField) c).setEditable(false);
            }
        }

        return panel;
    }

    public void refreshMyCourses(Student student) {
        mainPanel.removeAll();
        mainPanel.add(new ViewCoursesPanel(student, this), "VIEW");
        mainPanel.add(new MyCoursesPanel(student, this), "MY");
        cardLayout.show(mainPanel, "MY");
    }
}*/

package ui;

import model.Student;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public DashboardFrame(Student student) {
        setTitle("Student Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel studentInfoPanel = createStudentInfoPanel(student);
        add(studentInfoPanel, BorderLayout.NORTH);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new ViewCoursesPanel(student, this), "VIEW");
        mainPanel.add(new MyCoursesPanel(student, this), "MY");

        add(mainPanel, BorderLayout.CENTER);

        JPanel bottomNav = new JPanel();
        bottomNav.setBackground(Color.WHITE);

        JButton viewCoursesBtn = new JButton("View Courses");
        JButton myCoursesBtn = new JButton("My Courses");

        viewCoursesBtn.setFocusPainted(false);
        myCoursesBtn.setFocusPainted(false);
        viewCoursesBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        myCoursesBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        viewCoursesBtn.addActionListener(e -> cardLayout.show(mainPanel, "VIEW"));
        myCoursesBtn.addActionListener(e -> cardLayout.show(mainPanel, "MY"));

        bottomNav.add(viewCoursesBtn);
        bottomNav.add(myCoursesBtn);

        add(bottomNav, BorderLayout.SOUTH);
        setVisible(true);
    }

    private JPanel createStudentInfoPanel(Student student) {
        JPanel panel = new JPanel(new GridLayout(3, 4, 20, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField roll = new JTextField(student.getRollNo());
        JTextField name = new JTextField(student.getName());
        JTextField branch = new JTextField(student.getBranch());
        JTextField sem = new JTextField(String.valueOf(student.getSemester()));
        JTextField campus = new JTextField(student.getCampus());
        JTextField reg = new JTextField(student.getRegulations());

        JTextField[] fields = {roll, name, branch, sem, campus, reg};
        for (JTextField tf : fields) {
            tf.setEditable(false);
            tf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        }

        panel.add(new JLabel("Roll Number:")); panel.add(roll);
        panel.add(new JLabel("Name:")); panel.add(name);
        panel.add(new JLabel("Branch:")); panel.add(branch);
        panel.add(new JLabel("Semester:")); panel.add(sem);
        panel.add(new JLabel("Campus:")); panel.add(campus);
        panel.add(new JLabel("Regulations:")); panel.add(reg);

        return panel;
    }

    public void refreshMyCourses(Student student) {
        mainPanel.removeAll();
        mainPanel.add(new ViewCoursesPanel(student, this), "VIEW");
        mainPanel.add(new MyCoursesPanel(student, this), "MY");
        cardLayout.show(mainPanel, "MY");
    }
}





