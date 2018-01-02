import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class DrawableTextBox extends Drawable
{
    private static final int INSET = 4;
    private int height;
    private int width;
    private String[] lines;
    
    public DrawableTextBox(final int height, final int width) {
        this.height = height + 8;
        this.width = width + 8;
    }
    
    public void draw(final Graphics g) {
        g.setColor(Color.white);
        g.fillRect(super.x, super.y, this.width, this.height);
        g.setColor(Color.black);
        g.drawRect(super.x, super.y, this.width, this.height);
        final int drawx = super.x + 4;
        int drawy = super.y + 4;
        final Font font = Screen.screen.getFont();
        g.setFont(font);
        final FontMetrics fontMetrics = Screen.screen.getFontMetrics(font);
        g.setFont(font);
        for (int line = 0; line < this.lines.length; ++line) {
            g.drawString(this.lines[line], drawx, drawy + fontMetrics.getMaxAscent());
            drawy += fontMetrics.getHeight();
        }
    }
    
    public void setText(final String[] lines) {
        this.lines = lines;
    }
}
