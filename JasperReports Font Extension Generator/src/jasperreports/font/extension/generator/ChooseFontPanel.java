/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jasperreports.font.extension.generator;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Pieter
 */
public class ChooseFontPanel extends javax.swing.JPanel {

    private JFileChooser fileChooser;
    private DefaultListModel listModel;
    private Fonts fonts;

    public ChooseFontPanel() {
        initComponents();
        listModel = new DefaultListModel();
        fontOverview.setModel(listModel);
        fonts = new Fonts();

        addFontOverviewKeyListener();

    }

    private void handleFileInput(int result) {
        if (result == JFileChooser.APPROVE_OPTION) {
            File[] fontFiles = fileChooser.getSelectedFiles();
            addFontsToOverview(fontFiles);
        }
    }

    private void addFontsToOverview(File[] fontFiles) {
        for (int i = 0; i < fontFiles.length; i++) {
            String filePath = fontFiles[i].getAbsolutePath();

            if (!fonts.fontAlreadyAdded(filePath)) {
                listModel.addElement(filePath);
                fonts.addFont(filePath);
            }
        }
    }

    private void addFontOverviewKeyListener() {
        KeyAdapter keyAdapter = setupKeyAdapter();
        fontOverview.addKeyListener(keyAdapter);
    }

    private KeyAdapter setupKeyAdapter() {
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent key) {

                if (key.getKeyCode() == KeyEvent.VK_DELETE) {
                    int[] selectedFonts = fontOverview.getSelectedIndices();
                    removeSelectedFonts(selectedFonts);
                }
            }
        };
        return keyAdapter;
    }

    private void removeSelectedFonts(int[] selectedFonts) {
        int fontCount = selectedFonts.length;

        for (int listLocation = fontCount - 1; listLocation >= 0; listLocation--) {
            int location = selectedFonts[listLocation];
            listModel.remove(location);
            fonts.removeFont(location);
        }
    }

    public Fonts getFonts() {
        return fonts;
    }

    public void setFonts(Fonts fonts) {
        System.out.println("test");
        this.fonts = fonts;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        selectFonts = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        fontOverview = new javax.swing.JList();

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setMaximumSize(null);
        setMinimumSize(null);
        setPreferredSize(new java.awt.Dimension(700, 500));

        selectFonts.setText("Select your fonts");
        selectFonts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectFontsActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(fontOverview);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectFonts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selectFonts, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void selectFontsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectFontsActionPerformed
        FileFilter fileFilter = setupFileFilter();
        int result = showFileChooser(fileFilter);
        handleFileInput(result);
    }//GEN-LAST:event_selectFontsActionPerformed

    private int showFileChooser(FileFilter fileFilter) {
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(fileFilter);
        fileChooser.setMultiSelectionEnabled(true);
        int result = fileChooser.showOpenDialog(this);
        return result;
    }

    private FileFilter setupFileFilter() {
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File file) {

                if (file.isDirectory()) {
                    return true;
                } else if (isFontFile(file)) {
                    return true;
                } else {
                    return false;
                }
            }

            public boolean isFontFile(File file) {
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

        return fileFilter;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList fontOverview;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton selectFonts;
    // End of variables declaration//GEN-END:variables
}
