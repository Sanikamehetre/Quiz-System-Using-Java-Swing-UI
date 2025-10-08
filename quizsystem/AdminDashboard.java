import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AdminDashboard extends JFrame {
    private JTextField questionField, option1Field, option2Field, option3Field, option4Field, answerField;

    public AdminDashboard() {
        setTitle("Admin Panel - Add Questions");
        setSize(400, 400);
        setLayout(null);

        JLabel questionLabel = new JLabel("Question:");
        questionLabel.setBounds(20, 20, 100, 25);
        add(questionLabel);

        questionField = new JTextField();
        questionField.setBounds(120, 20, 250, 25);
        add(questionField);

        option1Field = new JTextField();
        option1Field.setBounds(120, 50, 250, 25);
        add(option1Field);

        option2Field = new JTextField();
        option2Field.setBounds(120, 80, 250, 25);
        add(option2Field);

        option3Field = new JTextField();
        option3Field.setBounds(120, 110, 250, 25);
        add(option3Field);

        option4Field = new JTextField();
        option4Field.setBounds(120, 140, 250, 25);
        add(option4Field);

        answerField = new JTextField();
        answerField.setBounds(120, 170, 50, 25);
        add(answerField);

        JButton addButton = new JButton("Add Question");
        addButton.setBounds(120, 200, 150, 30);
        add(addButton);

        addButton.addActionListener(e -> addQuestion());

        setVisible(true);
    }

    private void addQuestion() {
        String question = questionField.getText();
        String option1 = option1Field.getText();
        String option2 = option2Field.getText();
        String option3 = option3Field.getText();
        String option4 = option4Field.getText();
        int answer = Integer.parseInt(answerField.getText());

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO questions (question, option1, option2, option3, option4, answer) VALUES (?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, question);
            stmt.setString(2, option1);
            stmt.setString(3, option2);
            stmt.setString(4, option3);
            stmt.setString(5, option4);
            stmt.setInt(6, answer);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Question Added Successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
