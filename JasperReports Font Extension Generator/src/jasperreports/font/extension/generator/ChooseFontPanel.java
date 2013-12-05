/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jasperreports.font.extension.generator;

import jasperreports.font.extension.generator.util.FileDragDropListener;
import java.awt.Cursor;
import java.awt.dnd.DropTarget;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Pieter
 */
public class ChooseFontPanel extends javax.swing.JPanel {
    
    private JFileChooser fileChooser;
    
    public ChooseFontPanel() {
        initComponents();
        FileDragDropListener dragDropListener = new FileDragDropListener();
        DropTarget dropTarget = new DropTarget(fileDropPanel, dragDropListener);
        fileDropPanel.addMouseListener(new FileUploadMouseListener());
    }

    private void handleFileInput(int result) {
        if (result == JFileChooser.APPROVE_OPTION) {
            File[] files = fileChooser.getSelectedFiles();
            
            for(int i = 0; i < files.length; i++) {
                String filePath = files[i].getAbsolutePath();
            }
        }
    }

    class FileUploadMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            FileFilter fileFilter = new FileFilter() {
                @Override
                public boolean accept(File file) {

                    if (file.isDirectory()) {
                        return true;
                    }

                    String[] acceptedFileExtensions = {"otf", "TTF", "OTF", "ttf"};
                    String fileName = file.getName();

                    for (int i = 0; i < acceptedFileExtensions.length; i++) {
                        String extension = acceptedFileExtensions[i];
                        if (fileName.endsWith(extension) == true) {
                            return true;
                        }
                    }
                    return false;
                }

                @Override
                public String getDescription() {
                    return "Open font files";
                }
            };

            fileChooser = new JFileChooser();
            fileChooser.setFileFilter(fileFilter);
            fileChooser.setMultiSelectionEnabled(true);
            int result = fileChooser.showOpenDialog(fileDropPanel);
            handleFileInput(result);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //do nothing
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //do nothing
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileDropPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(700, 300));

        fileDropPanel.setBackground(new java.awt.Color(255, 255, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Select your font files or drag them in!");

        javax.swing.GroupLayout fileDropPanelLayout = new javax.swing.GroupLayout(fileDropPanel);
        fileDropPanel.setLayout(fileDropPanelLayout);
        fileDropPanelLayout.setHorizontalGroup(
            fileDropPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fileDropPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(223, 223, 223))
        );
        fileDropPanelLayout.setVerticalGroup(
            fileDropPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fileDropPanelLayout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(jLabel1)
                .addContainerGap(132, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(89, Short.MAX_VALUE)
                .addComponent(fileDropPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fileDropPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel fileDropPanel;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
