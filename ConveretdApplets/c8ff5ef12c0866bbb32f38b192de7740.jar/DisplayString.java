import java.awt.Font;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class DisplayString
{
    private String stringText;
    private Color stringColor;
    private Font stringFont;
    
    public DisplayString() {
        this.stringText = "";
        this.stringColor = new Color(0);
        this.stringFont = new Font("Arial", 0, 12);
    }
    
    public DisplayString(final String stringText) {
        this.stringText = stringText;
        this.stringColor = new Color(0);
        this.stringFont = new Font("Arial", 0, 12);
    }
    
    public DisplayString(final String stringText, final Color stringColor) {
        this.stringText = stringText;
        this.stringColor = stringColor;
        this.stringFont = new Font("Arial", 0, 12);
    }
    
    public DisplayString(final String stringText, final Font stringFont) {
        this.stringText = stringText;
        this.stringColor = new Color(0);
        this.stringFont = stringFont;
    }
    
    public DisplayString(final String stringText, final Color stringColor, final Font stringFont) {
        this.stringText = stringText;
        this.stringColor = stringColor;
        this.stringFont = stringFont;
    }
    
    public DisplayString(final String stringText, final Font stringFont, final Color stringColor) {
        this.stringText = stringText;
        this.stringColor = stringColor;
        this.stringFont = stringFont;
    }
    
    public void setString(final String stringText) {
        this.stringText = stringText;
    }
    
    public String getString() {
        return this.stringText;
    }
    
    public void setColor(final Color stringColor) {
        this.stringColor = stringColor;
    }
    
    public Color getColor() {
        return this.stringColor;
    }
    
    public void setFont(final Font stringFont) {
        this.stringFont = stringFont;
    }
    
    public Font getFont() {
        return this.stringFont;
    }
}
