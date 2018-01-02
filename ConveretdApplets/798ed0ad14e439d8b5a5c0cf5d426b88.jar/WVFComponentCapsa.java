import java.awt.Component;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Point;
import java.awt.Cursor;

// 
// Decompiled by Procyon v0.5.30
// 

public class WVFComponentCapsa implements BoxComponent
{
    float I;
    final int black = 0;
    final int default_colors = 1;
    final int getBlue = 3;
    int getBoxScripting;
    String Z;
    private FontUtils fontUtils;
    private ResourceProvider resourceProvider;
    
    public final void repaint() {
    }
    
    public final void setCursor(final Cursor cursor) {
    }
    
    public final void setCaret(final BoxPosition boxPosition) {
    }
    
    public final BoxPosition getCaret() {
        return null;
    }
    
    public final Point getOrigin() {
        return new Point(0, 0);
    }
    
    public final Font getDefaultFont(final int n, final AbstractBox abstractBox) {
        return this.fontUtils.getCurrentFont(n, abstractBox);
    }
    
    public final FontMetrics getFontMetrics(final Font font) {
        return new WVFFontMetrics(font, this.Z, this.I, this.resourceProvider);
    }
    
    public final WVFGraphics createGraphics(final int n, final int n2) {
        return new WVFGraphics(n, n2, this);
    }
    
    public final FontUtils getFontUtils() {
        return this.fontUtils;
    }
    
    public final ResourceProvider getResourceProvider() {
        if (this.resourceProvider == null) {
            this.resourceProvider = new ResourceProvider();
        }
        return this.resourceProvider;
    }
    
    public final void setResourceProvider(final ResourceProvider resourceProvider) {
        this.resourceProvider = resourceProvider;
    }
    
    public final boolean isDesign() {
        return false;
    }
    
    public final void setColor(final Graphics graphics, final Color color) {
        switch (this.getBoxScripting) {
            case 3: {
                graphics.setColor(Color.black);
            }
            case 0: {
                graphics.setColor(color);
            }
            case 1: {
                final int n = (color.getBlue() + color.getGreen() + color.getRed()) / 3;
                graphics.setColor(new Color(n, n, n));
            }
            default: {}
        }
    }
    
    public WVFComponentCapsa(final String z, final float i) {
        this.I = 10.0f;
        this.getBoxScripting = 0;
        this.fontUtils = new FontUtils();
        this.Z = z;
        this.I = i;
        final Font defaultFont = this.getDefaultFont(4, null);
        this.getFontUtils().setCurrentFont(4, new Font(defaultFont.getName(), 0, defaultFont.getSize()));
    }
    
    public final Component getComponent() {
        return null;
    }
    
    public final BoxComponent[] getPointer() {
        return new BoxComponent[] { this };
    }
    
    public final AbstractBox parse(final String s) {
        return Formula.staticParse(s);
    }
    
    public final BoxScripting getBoxScripting() {
        return new BoxScripting(1);
    }
    
    public final String script(final AbstractBox abstractBox) {
        final BoxScripting boxScripting = this.getBoxScripting();
        abstractBox.script(boxScripting);
        return boxScripting.toString();
    }
    
    public final Color getColor(final int n, final AbstractBox abstractBox) {
        if (n == 13 && abstractBox != null) {
            return null;
        }
        return AbstractBox.default_colors[n];
    }
    
    public final int getDPI() {
        return 72;
    }
    
    public final boolean getMathRTL() {
        return false;
    }
    
    public final boolean isDisplayStyle() {
        return true;
    }
}
