/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jasperreports.font.extension.generator;

import java.util.ArrayList;

/**
 *
 * @author Pieter
 */
public class Fonts {
    private ArrayList<Font> fonts;
    
    public Fonts() {
        fonts = new ArrayList<>();
    }
    
    public void addFont(String filePath) {
        fonts.add(new Font(filePath));
    }
    
    public Font getFont(int key) {
        return fonts.get(key);
    }
    
    public void setFont(int key, Font font) {
        fonts.set(key, font);
    }
    
    public void removeFont(int key) {
        fonts.remove(key);
    }
    
    public int getFontCount() {
        return fonts.size();
    }
}
