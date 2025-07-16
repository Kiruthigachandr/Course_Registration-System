import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            System.err.println("Failed to initialize FlatLaf");
        }

        SwingUtilities.invokeLater(() -> {
            String[] options = {"Student Login", "Admin Login"};
            int choice = JOptionPane.showOptionDialog(
                null,
                "Select Login Type",
                "Login Portal",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
            );

            if (choice == 1) {
                new ui.admin.AdminLoginFrame();
            } else {
                new ui.LoginFrame();
            }
        });
    }
}

