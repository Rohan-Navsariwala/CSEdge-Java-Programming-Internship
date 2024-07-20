package com.quizapp.ui;

import com.quizapp.QuizApp;
import com.quizapp.utils.DatabaseManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage {
    private JPanel panel;
    private JTextField nameField;
    private JButton startQuizButton;
    private JComboBox<String> subjectComboBox;
    private int userId;

    public HomePage(QuizApp quizApp) {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(0xffffff)); // Light gray background
        panel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Add padding to the panel

        // Title Label
        JLabel titleLabel = new JLabel("QUIZ APP", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28)); // Increased font size for title
        titleLabel.setPreferredSize(new Dimension(300, 50));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(new Color(0x2C3E50)); // Navy color
        panel.add(titleLabel);

        // Padding
        panel.add(Box.createRigidArea(new Dimension(0, 30))); // Increased padding

        // Input Panel for Name
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        inputPanel.setBackground(panel.getBackground());
        inputPanel.setMaximumSize(new Dimension(400, 40)); // Set maximum size
        inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(new Color(0x2C3E50)); // Navy color
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        inputPanel.add(nameLabel);
        inputPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Padding between label and field
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200, 30)); // Adjusted size
        nameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0x2C3E50)), 
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        )); // Subtle border with padding
        inputPanel.add(nameField);
        panel.add(inputPanel);

        // Padding
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Subject Selection
        JPanel subjectPanel = new JPanel();
        subjectPanel.setLayout(new BoxLayout(subjectPanel, BoxLayout.X_AXIS));
        subjectPanel.setBackground(panel.getBackground());
        subjectPanel.setMaximumSize(new Dimension(400, 40)); // Set maximum size
        subjectPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel subjectLabel = new JLabel("Subject:");
        subjectLabel.setForeground(new Color(0x2C3E50)); // Navy color
        subjectLabel.setFont(new Font("Arial", Font.BOLD, 16));
        subjectPanel.add(subjectLabel);
        subjectPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Padding between label and combo box
        String[] subjects = {"Java", "PHP", "JavaScript", "DSA"};
        subjectComboBox = new JComboBox<>(subjects);
        subjectComboBox.setPreferredSize(new Dimension(200, 30));
        subjectComboBox.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0x2C3E50)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        )); // Subtle border with padding
        subjectPanel.add(subjectComboBox);
        panel.add(subjectPanel);

        // Padding
        panel.add(Box.createRigidArea(new Dimension(0, 30))); // Increased padding

        // Start Button
        startQuizButton = new JButton("Start Quiz");
        startQuizButton.setPreferredSize(new Dimension(200, 40));
        startQuizButton.setBackground(new Color(0xC0392B)); // Blue color
        startQuizButton.setForeground(Color.WHITE); // White text
        startQuizButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding inside the button
        startQuizButton.setFocusPainted(false); // Remove focus border
        startQuizButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startQuizButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand
        startQuizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Please enter your name.");
                    return;
                }
                userId = DatabaseManager.addUser(name);
                String subject = (String) subjectComboBox.getSelectedItem();
                quizApp.showQuizPage(subject, userId);
            }
        });
        panel.add(startQuizButton);
    }

    public JPanel getPanel() {
        return panel;
    }
}
