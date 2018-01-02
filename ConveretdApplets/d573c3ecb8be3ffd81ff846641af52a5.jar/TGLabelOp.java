import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.image.ImageObserver;

// 
// Decompiled by Procyon v0.5.30
// 

class TGLabelOp implements ImageObserver, TGGraphicsOp
{
    private Color color;
    private Font font;
    private String text;
    private TGPoint where;
    
    public TGLabelOp(final String text, final TGPoint where, final Font font, final Color color) {
        this.color = color;
        this.font = font;
        this.text = text;
        this.where = where;
    }
    
    public Rectangle doIt(final Image image) {
        final int width = image.getWidth(this);
        if (width < 0) {
            return null;
        }
        final int canvasX = this.where.canvasX(width);
        final int height = image.getHeight(this);
        if (height < 0) {
            return null;
        }
        final int canvasY = this.where.canvasY(height);
        final Graphics graphics = image.getGraphics();
        graphics.setColor(this.color);
        graphics.setFont(this.font);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int n = canvasX;
        final int stringWidth = fontMetrics.stringWidth(this.text);
        final int height2 = fontMetrics.getHeight();
        final int n2 = canvasY - (height2 - fontMetrics.getDescent());
        graphics.setClip(n, n2, stringWidth, height2);
        graphics.drawString(this.text, canvasX, canvasY);
        graphics.dispose();
        return new Rectangle(n, n2, stringWidth, height2);
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        System.out.println("TGLabelOp.imageUpdate: got here!");
        return true;
    }
}
