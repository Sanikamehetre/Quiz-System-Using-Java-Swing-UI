import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class StudentDashboard extends JFrame {
    private JLabel questionLabel;
    private JButton[] optionButtons = new JButton[4];
    private int questionIndex = 0;
    private int score = 0;

    public StudentDashboard() {
        setTitle("Take Quiz");
        setSize(400, 300);
        setLayout(null);

        questionLabel = new JLabel("Loading Question...");
        questionLabel.setBounds(20, 20, 350, 25);
        add(questionLabel);

        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JButton();
            optionButtons[i].setBounds(20, 50 + (i * 40), 350, 30);
            add(optionButtons[i]);

            int finalI = i;
            optionButtons[i].addActionListener(e -> checkAnswer(finalI + 1));
        }

        loadQuestion();
        setVisible(true);
    }

    private void loadQuestion() {
        // Fetch and display questions from DB (implement SQL SELECT query here)
    }

    private void checkAnswer(int chosenAnswer) {
        // Compare chosenAnswer with DB answer, update score
    }
}
