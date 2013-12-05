package jasperreports.font.extension.generator;

/**
 *
 * @author Pieter
 */
public class ExtensionGenerator {

    private Fonts fonts;

    public ExtensionGenerator(Fonts fonts) {
        this.fonts = fonts;
    }
    
    public boolean setupExtensionFolders() {
        return true;
    }
    
    public boolean createManifest() {
        return true;
    }
    
    public boolean importFontFiles() {
        return true;
    }
    
    public boolean createPropertiesFile() {
        return true;
    }
    
    public boolean createFontsXml() {
        return true;
    }
    
    public String generateJar() {
        return "filepath";
    }
}
