// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.swing.filetree;

import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;
import java.awt.Font;
import jmaster.util.swing.GUIHelper;
import javax.swing.Icon;

public class FileLengthIcon implements Icon
{
    private static final String D = "fileLengthIcon";
    protected GUIHelper G;
    protected int F;
    protected int H;
    protected long I;
    protected Font A;
    protected Color B;
    protected int E;
    protected int C;
    
    public FileLengthIcon() {
        (this.G = GUIHelper.getInstance()).injectProperties(this, "fileLengthIcon");
    }
    
    public void setIconHeight(final int h) {
        this.H = h;
    }
    
    public void setIconWidth(final int f) {
        this.F = f;
    }
    
    public long getFileLength() {
        return this.I;
    }
    
    public void setFileLength(final long i) {
        this.I = i;
    }
    
    public Color getColor() {
        return this.B;
    }
    
    public void setColor(final Color b) {
        this.B = b;
    }
    
    public Font getFont() {
        return this.A;
    }
    
    public void setFont(final Font a) {
        this.A = a;
    }
    
    public int getOffsetX() {
        return this.E;
    }
    
    public void setOffsetX(final int e) {
        this.E = e;
    }
    
    public int getOffsetY() {
        return this.C;
    }
    
    public void setOffsetY(final int c) {
        this.C = c;
    }
    
    public int getIconHeight() {
        return this.H;
    }
    
    public int getIconWidth() {
        return this.F;
    }
    
    public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
        final Font font = graphics.getFont();
        final Color color = graphics.getColor();
        graphics.setFont(this.A);
        graphics.setColor(this.B);
        graphics.drawString(GUIHelper.getInstance().getLengthFormatted(this.I), n + this.E, n2 + this.C);
        graphics.setFont(font);
        graphics.setColor(color);
    }
}
