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
import javax.swing.JFileChooser;

/**
 *
 * @author Pieter
 */
public class ChooseFontPanel extends javax.swing.JPanel {

    /**
     * Creates new form guiPanel
     */
    public ChooseFontPanel() {
        initComponents();

        FileDragDropListener dragDropListener = new FileDragDropListener();
        DropTarget dropTarget = new DropTarget(FileDropPanel, dragDropListener);
        FileDropPanel.addMouseListener(new FileUploadMouseListener());

    }

    class FileUploadMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            final JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(FileDropPanel);
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
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

        dragAndDropPanel1 = new jasperreports.font.extension.generator.DragAndDropPanel();
        FileDropPanel = new jasperreports.font.extension.generator.DragAndDropPanel();

        setPreferredSize(new java.awt.Dimension(700, 300));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(FileDropPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(104, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(FileDropPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private jasperreports.font.extension.generator.DragAndDropPanel FileDropPanel;
    private jasperreports.font.extension.generator.DragAndDropPanel dragAndDropPanel1;
    // End of variables declaration//GEN-END:variables
}
