/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jasperreports.font.extension.generator;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Pieter
 */
public class MainFrame {

    private JFrame frame;
    private JPanel contentPanel, buttonPanel;
    private ChooseFontPanel chooseFontPanel;
    private FontSettingsPanel fontSettingsPanel;
    private GenerateExtensionPanel generateExtensionPanel;
    private JButton previousButton, nextButton;
    private int currentPanel;
    private Fonts fonts;

    public MainFrame() {
        setupFrame();
        setupCardLayout();
        setupButtonPanel();
        currentPanel = 1;

        frame.add(contentPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.PAGE_END);

        frame.pack();
        frame.setVisible(true);
    }

    private void setupFrame() {
        frame = new JFrame();
        frame.setSize(700, 500);
        frame.setTitle("JasperReports Font Extension Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    private void setupCardLayout() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new CardLayout());

        chooseFontPanel = new ChooseFontPanel();
        fontSettingsPanel = new FontSettingsPanel();
        generateExtensionPanel = new GenerateExtensionPanel();

        contentPanel.add(chooseFontPanel);
        contentPanel.add(fontSettingsPanel);
        contentPanel.add(generateExtensionPanel);
    }

    private void setupButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(8, 8, 8, 8));
        buttonPanel.setLayout(new BorderLayout());

        previousButton = new JButton("Previous");
        nextButton = new JButton("Next");

        buttonPanel.add(previousButton, BorderLayout.LINE_START);
        buttonPanel.add(nextButton, BorderLayout.LINE_END);

        previousButton.addActionListener(getPreviousActionListener());
        nextButton.addActionListener(getNextActionListener());

        setupButtonVisibility();
    }

    private ActionListener getNextActionListener() {
        ActionListener nextActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                cardLayout.next(contentPanel);

                int previousPanel = currentPanel;
                currentPanel++;

                setupButtonVisibility();
                passFontData(previousPanel, currentPanel);

            }
        };
        return nextActionListener;
    }

    private ActionListener getPreviousActionListener() {
        ActionListener previousActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                cardLayout.previous(contentPanel);
                int previousPanel = currentPanel;
                currentPanel--;

                setupButtonVisibility();
                passFontData(previousPanel, currentPanel);
            }
        };
        return previousActionListener;
    }

    private void setupButtonVisibility() {
        switch (currentPanel) {
            case 1:
                previousButton.setVisible(false);
                break;
            case 2:
                previousButton.setVisible(true);
                nextButton.setVisible(true);
                break;
            case 3:
                nextButton.setVisible(false);
                break;
        }
    }

    private void passFontData(int previousPanel, int currentPanel) {
        if (previousPanel < currentPanel) {
            passFontDataFurther();
        } else {
            passFontDataBack();
        }
    }

    private void passFontDataFurther() {
        switch (currentPanel) {
            case 2:
                fonts = chooseFontPanel.getFonts();
                fontSettingsPanel.setFonts(fonts);
                break;
            case 3:
                fonts = fontSettingsPanel.getFonts();
                generateExtensionPanel.setFonts(fonts);
                break;
        }
    }

    private void passFontDataBack() {
        switch (currentPanel) {
            case 1:
                fonts = fontSettingsPanel.getFonts();
                chooseFontPanel.setFonts(fonts);
                break;
            case 2:
                fontSettingsPanel.setFonts(fonts);
                break;
        }
    }
}