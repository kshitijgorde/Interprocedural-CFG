import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class EditorNameStripe
{
    private static final Color BG_COLOR;
    private static final Color FG_COLOR;
    private static final int LEFT_GAP = 10;
    private static final String PREFIX = "Editor: ";
    private int height;
    private int leftX;
    private int prefixLeftX;
    private int textOffset;
    private int topY;
    private int width;
    private String fileName;
    private String text;
    
    public EditorNameStripe() {
        this.fileName = "NoName";
        this.text = "Editor: " + this.fileName;
        this.textOffset = -1;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(this.leftX, this.topY, this.width, this.height);
    }
    
    public Dimension getSize() {
        return new Dimension(this.width, this.height);
    }
    
    public void paint(final Graphics graphics) {
        if (this.textOffset < 0) {
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            final int height = fontMetrics.getHeight();
            this.textOffset = (this.height - height) / 2 + height - fontMetrics.getMaxDescent();
            this.prefixLeftX = this.leftX + 10;
        }
        graphics.setColor(EditorNameStripe.BG_COLOR);
        graphics.fillRect(this.leftX, this.topY, this.width, this.height);
        graphics.setColor(EditorNameStripe.FG_COLOR);
        graphics.drawString(this.text, this.prefixLeftX, this.topY + this.textOffset);
    }
    
    public void setBounds(final int leftX, final int topY, final int width, final int height) {
        this.leftX = leftX;
        this.topY = topY;
        this.width = width;
        this.height = height;
    }
    
    public void setFileName(final String fileName) {
        this.fileName = fileName;
        this.text = "Editor: " + fileName;
    }
    
    public void setLocation(final int leftX, final int topY) {
        this.leftX = leftX;
        this.topY = topY;
    }
    
    static {
        BG_COLOR = Color.black;
        FG_COLOR = Color.white;
    }
}
