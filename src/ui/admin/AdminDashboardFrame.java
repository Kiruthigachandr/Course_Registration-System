package ui.admin;

import javax.swing.*;
import java.awt.*;

public class AdminDashboardFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public AdminDashboardFrame() {
        setTitle("Admin Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // CardLayout for switching between panels
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.add(new CourseManagementPanel(), "COURSES");
        mainPanel.add(new StudentManagementPanel(), "STUDENTS");

        // Navigation bar
        JPanel navBar = new JPanel();
        JButton courseBtn = new JButton("Manage Courses");
        JButton studentBtn = new JButton("Manage Students");

        courseBtn.addActionListener(e -> cardLayout.show(mainPanel, "COURSES"));
        studentBtn.addActionListener(e -> cardLayout.show(mainPanel, "STUDENTS"));

        navBar.add(courseBtn);
        navBar.add(studentBtn);

        add(navBar, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}