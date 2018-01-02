import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class TitleBarTrial extends Canvas
{
    private String title;
    private Image icon;
    private Font font;
    private int height;
    private int width;
    private int fontBaseline;
    
    public TitleBarTrial(final String title, final Font font, final int width, final int height) {
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        this.setBackground(Color.lightGray);
        this.title = title;
        this.setFont(this.font = font);
        this.width = width;
        this.height = height;
        this.fontBaseline = height - fontMetrics.getAscent() + fontMetrics.getDescent();
        this.resize(width, height);
    }
    
    public TitleBarTrial(final String title, final Font font, final Image icon, final int width, final int height) {
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        this.setBackground(Color.lightGray);
        this.icon = icon;
        this.title = title;
        this.setFont(this.font = font);
        this.width = width;
        this.height = height;
        this.fontBaseline = height - fontMetrics.getAscent() + fontMetrics.getDescent();
        this.resize(width, height);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.lightGray);
        graphics.clearRect(0, 0, this.width, this.height);
        graphics.draw3DRect(1, 1, this.width - 3, this.height - 3, true);
        graphics.setColor(Color.blue.darker().darker());
        graphics.fillRect(4, 4, this.width - 8, this.height - 7);
        if (this.icon != null) {
            graphics.drawImage(this.icon, 7, 6, this);
        }
        if (this.title != null) {
            graphics.setColor(Color.white);
            if (this.icon != null) {
                graphics.drawString(this.title, this.icon.getWidth(this) + 10, this.fontBaseline);
            }
            else {
                graphics.drawString(this.title, 10, this.fontBaseline);
            }
        }
    }
}
