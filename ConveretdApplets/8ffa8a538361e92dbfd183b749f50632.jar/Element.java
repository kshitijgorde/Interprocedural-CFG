import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class Element
{
    String name;
    Color nameColor;
    Color vertexColor;
    Color edgeColor;
    Color faceColor;
    boolean dragable;
    int dimension;
    int align;
    static final int CENTRAL = 0;
    static final int LEFT = 1;
    static final int RIGHT = 2;
    static final int ABOVE = 3;
    static final int BELOW = 4;
    
    public void drawString(final int n, final int n2, final Graphics graphics, final Dimension dimension) {
        graphics.setColor(this.nameColor);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int stringWidth = fontMetrics.stringWidth(this.name);
        final int height = fontMetrics.getHeight();
        switch (this.align) {
            case 1: {
                graphics.drawString(this.name, n - stringWidth - 6, n2 + height / 2 - 4);
            }
            case 2: {
                graphics.drawString(this.name, n + 2, n2 + height / 2 - 4);
            }
            case 3: {
                graphics.drawString(this.name, n - stringWidth / 2, n2 - height / 2 + 4);
            }
            case 4: {
                graphics.drawString(this.name, n - stringWidth / 2, n2 + height / 2 + 6);
            }
            default: {
                final int n3 = (n - dimension.width / 2) * dimension.height;
                final int n4 = (n2 - dimension.height / 2) * dimension.width;
                if (n4 > n3) {
                    if (n4 >= -n3) {
                        graphics.drawString(this.name, n - stringWidth / 2, n2 + height / 2 + 6);
                        return;
                    }
                    graphics.drawString(this.name, n - stringWidth - 6, n2 + height / 2 - 4);
                    return;
                }
                else {
                    if (n4 >= -n3) {
                        graphics.drawString(this.name, n + 2, n2 + height / 2 - 4);
                        return;
                    }
                    graphics.drawString(this.name, n - stringWidth / 2, n2 - height / 2 + 4);
                    return;
                }
                break;
            }
        }
    }
    
    boolean inClass(final String s) {
        try {
            final Class<? extends Element> class1 = this.getClass();
            final Class<?> forName = Class.forName(s);
            if (!class1.equals(forName) && !class1.getSuperclass().equals(forName)) {
                return false;
            }
        }
        catch (ClassNotFoundException ex) {
            return false;
        }
        return true;
    }
    
    protected void reset() {
        this.update();
    }
    
    protected void update() {
    }
    
    protected void translate(final double n, final double n2) {
    }
    
    protected boolean drag(final double n, final double n2) {
        return false;
    }
    
    protected void rotate(final PointElement pointElement, final double n, final double n2) {
    }
    
    protected boolean defined() {
        return false;
    }
    
    protected void drawName(final Graphics graphics, final Dimension dimension) {
    }
    
    protected void drawFace(final Graphics graphics) {
    }
    
    protected void drawEdge(final Graphics graphics) {
    }
    
    protected void drawVertex(final Graphics graphics) {
    }
    
    public Element() {
        this.dragable = false;
    }
}
