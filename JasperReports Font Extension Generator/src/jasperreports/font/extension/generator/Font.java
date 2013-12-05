package jasperreports.font.extension.generator;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Font {
    
    private String fontFilePath, fileName, fontName, family, type;
    private boolean completed;
    
    public Font(String fontFilePath) {
        this.fontFilePath = fontFilePath;
        extractFileNameFromPath();
        completed = false;
    }
    
    private void extractFileNameFromPath() {
        Path filePath = Paths.get(fontFilePath);
        fileName = filePath.getFileName().toString();
    }
    
    public void setFontName(String fontName) {
        this.fontName = fontName;
    }
    
    public void setFamily(String family) {
        this.family = family;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public String getFontName() {
        return fontName;
    }
    
    public String getFamily() {
        return family;
    }
    
    public String getType() {
        return type;
    }
    
    public String getFilePath() {
        return fontFilePath;
    }
}
