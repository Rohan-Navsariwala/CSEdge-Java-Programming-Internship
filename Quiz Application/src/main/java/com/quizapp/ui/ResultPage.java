package com.quizapp.ui;

import com.quizapp.QuizApp;
import com.quizapp.utils.DatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ResultPage {
    private JPanel panel;
    private JLabel resultLabel;
    private JButton homeButton;
    private JTextArea scoreDetailsArea;

    public ResultPage(QuizApp quizApp, int score) {
        panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0xffffff));

        resultLabel = new JLabel("Your score is: " + score);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 24));
        resultLabel.setForeground(new Color(0x2C3E50));
        resultLabel.setHorizontalAlignment(JLabel.CENTER);
        resultLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panel.add(resultLabel, BorderLayout.NORTH);

        scoreDetailsArea = new JTextArea(10, 30);
        scoreDetailsArea.setFont(new Font("Arial", Font.PLAIN, 16));
        scoreDetailsArea.setForeground(new Color(0x2C3E50));
        scoreDetailsArea.setBackground(new Color(0xffffff));
        scoreDetailsArea.setEditable(false);
        scoreDetailsArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(new JScrollPane(scoreDetailsArea), BorderLayout.CENTER);

        homeButton = new JButton("Go to Home");
        homeButton.setPreferredSize(new Dimension(150, 40));
        homeButton.setFont(new Font("Arial", Font.BOLD, 16));
        homeButton.setBackground(new Color(0xC0392B));
        homeButton.setForeground(Color.WHITE);
        homeButton.setBorderPainted(false);
        homeButton.setFocusPainted(false);
        homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quizApp.showHomePage();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0xffffff));
        buttonPanel.add(homeButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Load and display the scores from the database
        displayScores();
    }

    private void displayScores() {
        List<com.quizapp.models.Score> scores = DatabaseManager.getScores();
        StringBuilder sb = new StringBuilder();
        sb.append("User Name\tSubject\tScore\n");
        sb.append("-------------------------------\n");
        for (com.quizapp.models.Score score : scores) {
            sb.append(score.getUserName()).append("\t")
              .append(score.getSubject()).append("\t")
              .append(score.getScore()).append("\n");
        }
        scoreDetailsArea.setText(sb.toString());
    }

    public JPanel getPanel() {
        return panel;
    }
}
