import java.awt.font.TextLayout;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class WatcherReadout
{
    int x;
    int y;
    int w;
    int h;
    Color color;
    String contents;
    boolean isLarge;
    static final Font smallFont;
    static final Font bigFont;
    
    WatcherReadout(final boolean b) {
        this.w = 40;
        this.h = 14;
        this.color = new Color(100, 60, 20);
        this.contents = "123";
        this.isLarge = false;
        this.beLarge(b);
    }
    
    void beLarge(final boolean isLarge) {
        if (this.isLarge == isLarge) {
            return;
        }
        this.isLarge = isLarge;
        this.w = (this.isLarge ? 50 : 40);
        this.h = (this.isLarge ? 23 : 14);
    }
    
    void adjustWidth(final Graphics graphics) {
        this.w = Math.max(this.w, stringWidth(this.contents, this.isLarge ? WatcherReadout.bigFont : WatcherReadout.smallFont, graphics) + 12);
    }
    
    void paint(final Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(this.x + 2, this.y, this.w - 4, 1);
        graphics.fillRect(this.x + 2, this.y + this.h - 1, this.w - 4, 1);
        graphics.fillRect(this.x, this.y + 2, 1, this.h - 4);
        graphics.fillRect(this.x + this.w - 1, this.y + 2, 1, this.h - 4);
        graphics.fillRect(this.x + 1, this.y + 1, 1, 1);
        graphics.fillRect(this.x + this.w - 2, this.y + 1, 1, 1);
        graphics.fillRect(this.x + 1, this.y + this.h - 2, 1, 1);
        graphics.fillRect(this.x + this.w - 2, this.y + this.h - 2, 1, 1);
        graphics.setColor(darker(darker(this.color)));
        graphics.fillRect(this.x + 2, this.y + 1, this.w - 4, 1);
        graphics.fillRect(this.x + 1, this.y + 2, 1, this.h - 4);
        graphics.setColor(darker(this.color));
        graphics.fillRect(this.x + 2, this.y + 2, this.w - 4, 1);
        graphics.fillRect(this.x + 2, this.y + this.h - 2, this.w - 4, 1);
        graphics.fillRect(this.x + 2, this.y + 2, 1, this.h - 3);
        graphics.fillRect(this.x + this.w - 2, this.y + 2, 1, this.h - 4);
        graphics.setColor(this.color);
        graphics.fillRect(this.x + 3, this.y + 3, this.w - 5, this.h - 5);
        final Font font = this.isLarge ? WatcherReadout.bigFont : WatcherReadout.smallFont;
        final int n = this.isLarge ? 17 : 11;
        graphics.setColor(Color.WHITE);
        graphics.setFont(font);
        graphics.drawString(this.contents, this.x + (this.w - stringWidth(this.contents, font, graphics)) / 2 - 1, this.y + n);
    }
    
    public static int stringWidth(final String s, final Font font, final Graphics graphics) {
        if (s.length() == 0) {
            return 0;
        }
        return (int)new TextLayout(s, font, ((Graphics2D)graphics).getFontRenderContext()).getBounds().getBounds().getWidth();
    }
    
    public static Color darker(final Color color) {
        final double n = 0.8333;
        return new Color((int)(n * color.getRed()), (int)(n * color.getGreen()), (int)(n * color.getBlue()));
    }
    
    static {
        smallFont = new Font("Arial Unicode MS", 1, 10);
        bigFont = new Font("Arial Unicode MS", 1, 14);
    }
}
