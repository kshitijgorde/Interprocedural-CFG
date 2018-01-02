// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import java.awt.Color;

public class TextAttribute
{
    public boolean isActive;
    public String dText;
    public String linkText;
    public String fg;
    public String bg;
    public Color foreground;
    public Color background;
    
    public TextAttribute() {
        this.dText = null;
        this.linkText = null;
        this.fg = null;
        this.bg = null;
        this.foreground = null;
        this.background = null;
        this.isActive = false;
    }
    
    public String toString() {
        return "isActive=" + this.isActive + ",dText=" + this.dText + ",linkText=" + this.linkText;
    }
}
