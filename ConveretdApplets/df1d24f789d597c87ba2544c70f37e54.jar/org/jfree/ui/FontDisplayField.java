// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.ui;

import java.util.ResourceBundle;
import java.awt.Font;
import javax.swing.JTextField;

public class FontDisplayField extends JTextField
{
    private Font displayFont;
    protected static ResourceBundle localizationResources;
    
    public FontDisplayField(final Font displayFont) {
        super("");
        this.setDisplayFont(displayFont);
        this.setEnabled(false);
    }
    
    public Font getDisplayFont() {
        return this.displayFont;
    }
    
    public void setDisplayFont(final Font displayFont) {
        this.displayFont = displayFont;
        this.setText(this.fontToString(this.displayFont));
    }
    
    private String fontToString(final Font font) {
        if (font != null) {
            return font.getFontName() + ", " + font.getSize();
        }
        return FontDisplayField.localizationResources.getString("No_Font_Selected");
    }
    
    static {
        FontDisplayField.localizationResources = ResourceBundle.getBundle("org.jfree.ui.LocalizationBundle");
    }
}
