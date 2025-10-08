import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public Login() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(20, 20, 80, 25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(100, 20, 150, 25);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(20, 60, 80, 25);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 60, 150, 25);
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100, 100, 100, 25);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        setVisible(true);
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT role FROM users WHERE username=? AND password=?")) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                JOptionPane.showMessageDialog(this, "Login Successful as " + role);

                if ("admin".equals(role)) {
                    new AdminDashboard();
                } else {
                    new StudentDashboard();
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}

