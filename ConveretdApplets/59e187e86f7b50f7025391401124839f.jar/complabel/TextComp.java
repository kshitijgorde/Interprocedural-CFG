// 
// Decompiled by Procyon v0.5.30
// 

package complabel;

import java.awt.Color;
import java.awt.Font;

class TextComp
{
    public String text;
    public Font font;
    public Color fgColor;
    public Color bgColor;
    public int width;
    public int ascent;
    public int height;
    public int xpos;
    
    public TextComp(final String text, final Font font, final Color fgColor, final Color bgColor) {
        this.text = text;
        this.font = font;
        this.fgColor = fgColor;
        this.bgColor = bgColor;
    }
    
    public String toString() {
        return this.text + ":" + this.font.toString() + ":" + ((this.fgColor != null) ? "c:" : ":") + ((this.bgColor != null) ? "d:" : ":");
    }
}
