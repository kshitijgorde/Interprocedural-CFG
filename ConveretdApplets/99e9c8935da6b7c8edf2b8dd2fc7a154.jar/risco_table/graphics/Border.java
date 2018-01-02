// 
// Decompiled by Procyon v0.5.30
// 

package risco_table.graphics;

import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Color;

public class Border
{
    protected Color background;
    protected Color foreground;
    protected Insets insets;
    private static Insets myInsets;
    
    static {
        Border.myInsets = new Insets(0, 0, 0, 0);
    }
    
    public Border() {
        this(Color.black, Color.lightGray);
    }
    
    public Border(final Color foreground, final Color background) {
        this.foreground = foreground;
        this.background = background;
        this.setInsets();
    }
    
    public Color getBackground() {
        return this.background;
    }
    
    public Color getForeground() {
        return this.foreground;
    }
    
    public Insets getInsets() {
        return this.insets;
    }
    
    public void paint(final Graphics g, final int x, final int y, final int width, final int height) {
    }
    
    public void setBackground(final Color background) {
        this.background = background;
    }
    
    public void setForeground(final Color foreground) {
        this.foreground = foreground;
    }
    
    protected void setInsets() {
        this.insets = Border.myInsets;
    }
}
