/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jasperreports.font.extension.generator;

/**
 *
 * @author Pieter
 */
public class GenerateExtensionPanel extends javax.swing.JPanel {

    private Fonts fonts;

    public GenerateExtensionPanel() {
        initComponents();
        explorer.setVisible(false);
    }

    public void setFonts(Fonts fonts) {
        this.fonts = fonts;
    }

    public class generatorThread implements Runnable {

        @Override
        public void run() {
            log.append("Start generating extension...\n");
            ExtensionGenerator extensionGenerator = new ExtensionGenerator(fonts);
            log.append("Setting up font families...\n");
            extensionGenerator.setupFontFamilies();
            log.append("DONE\n");
            log.append("Creating required folders...\n");
            extensionGenerator.setupExtensionFolders();
            log.append("DONE\n");
            log.append("Creating manifest...\n");
            //extensionGenerator.setupManifest();
            log.append("DONE\n");
            log.append("Importing font files...\n");
            extensionGenerator.importFontFiles();
            log.append("DONE\n");
            log.append("Creating properties file...\n");
            extensionGenerator.createPropertiesFile();
            log.append("DONE\n");
            log.append("Creating fonts.xml...\n");
            extensionGenerator.createFontsXml();
            log.append("DONE\n");
            log.append("Generating jar...\n");
            extensionGenerator.generateJar();
            log.append("DONE\n");
            log.append("Font extension successfully generated!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        generate = new javax.swing.JButton();
        logScroll = new javax.swing.JScrollPane();
        log = new javax.swing.JTextArea();
        explorer = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(700, 500));
        setPreferredSize(new java.awt.Dimension(700, 500));

        generate.setText("Generate font extension!");
        generate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateActionPerformed(evt);
            }
        });

        log.setEditable(false);
        log.setColumns(20);
        log.setRows(5);
        logScroll.setViewportView(log);

        explorer.setText("Show extension in explorer");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logScroll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                    .addComponent(generate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(explorer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generate, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(explorer, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void generateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateActionPerformed
        Thread generatorThread = new Thread(new generatorThread());
        generatorThread.start();
    }//GEN-LAST:event_generateActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton explorer;
    private javax.swing.JButton generate;
    private javax.swing.JTextArea log;
    private javax.swing.JScrollPane logScroll;
    // End of variables declaration//GEN-END:variables
}
