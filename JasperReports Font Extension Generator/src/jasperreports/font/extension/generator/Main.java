/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jasperreports.font.extension.generator;

import javax.swing.JFrame;

/**
 *
 * @author Pieter
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(700, 500);
        frame.setTitle("JasperReports Font Extension Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);  
        ChooseFontPanel contentPane = new ChooseFontPanel();
        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }
}
