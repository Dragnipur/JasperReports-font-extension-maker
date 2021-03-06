/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jasperreports.font.extension.generator;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Pieter
 */
public class FontSettingsPanel extends javax.swing.JPanel {

    private DefaultTableModel tableModel;
    private Fonts fonts;

    public FontSettingsPanel() {
        initComponents();
        setupTableModel();
    }

    private void setupTableModel() {
        tableModel = new DefaultTableModel();
        fontTable.setModel(tableModel);
        setupTableColumns();
    }

    private void setupTableColumns() {
        tableModel.addColumn("filename");
        tableModel.addColumn("family");
        tableModel.addColumn("type");
        setupTypeComboBox();
    }

    private void setupTypeComboBox() {
        TableColumn typeColumn = fontTable.getColumnModel().getColumn(2);

        JComboBox comboBox = new JComboBox();
        comboBox.addItem("Normal");
        comboBox.addItem("Italic");
        comboBox.addItem("Bold");
        comboBox.addItem("Bold Italic");

        typeColumn.setCellEditor(new DefaultCellEditor(comboBox));
    }

    private void setupTableRows() {
        int fontCount = fonts.getFontCount();

        for (int i = 0; i < fontCount; i++) {
            Font font = fonts.getFont(i);
            String fileName = font.getFileName();
            String fontFamily = font.getFamily();
            String fontType = font.getType();
            addNewTableRow(fileName, fontFamily, fontType);
        }
    }

    private void addNewTableRow(String fileName, String fontFamily, String fontType) {
        String[] row = new String[3];
        row[0] = fileName;
        row[1] = fontFamily;
        row[2] = fontType;
        tableModel.addRow(row);
    }

    public void setFonts(Fonts fonts) {
        this.fonts = fonts;
        tableModel.setRowCount(0);
        setupTableRows();
    }

    public Fonts getFonts() {
        fillFonts();
        return fonts;
    }

    private void fillFonts() {
        int rowCount = tableModel.getRowCount();

        for (int row = 0; row < rowCount; row++) {
            Font font = fonts.getFont(row);
            font = setFontData(font, row);
            fonts.setFont(row, font);
        }
    }

    private Font setFontData(Font font, int row) {
        int filledFields = 0;
        for (int column = 1; column <= 2; column++) {
            if (tableModel.getValueAt(row, column) != null) {
                String value = tableModel.getValueAt(row, column).toString();
                font = setFontValue(font, value, column);
                filledFields++;
            } else {
                font = setFontValue(font, "", column);
            }
        }

        if (filledFields == 3) {
            font.setCompleted(true);
        } else {
            font.setCompleted(false);
        }

        return font;
    }

    private Font setFontValue(Font font, String value, int column) {
        switch (column) {
            case 1:
                font.setFamily(value);
                break;
            case 2:
                font.setType(value);
                break;
        }
        return font;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tableScrollPane = new javax.swing.JScrollPane();
        fontTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        fontTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableScrollPane.setViewportView(fontTable);

        jLabel1.setText("Each family needs atleast a \"Normal\" font. if you don't add a normal font the jar will break your main program!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tableScrollPane)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable fontTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane tableScrollPane;
    // End of variables declaration//GEN-END:variables
}
