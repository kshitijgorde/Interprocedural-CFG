import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

class WSButton
{
    public int x;
    public int y;
    public int width;
    public int height;
    private String text;
    private Font font;
    private Polygon polygon;
    private Color fgColor;
    private Color bgColor;
    private Color bdColor;
    
    public WSButton(final String text, final Font font, final int width, final int height) {
        this.fgColor = Color.black;
        this.bgColor = Color.lightGray;
        this.bdColor = Color.black;
        this.text = text;
        this.font = font;
        this.x = 0;
        this.y = 0;
        this.width = width;
        this.height = height;
    }
    
    public WSButton(final Polygon polygon, final int width, final int height) {
        this.fgColor = Color.black;
        this.bgColor = Color.lightGray;
        this.bdColor = Color.black;
        this.polygon = polygon;
        this.x = 0;
        this.y = 0;
        this.width = width;
        this.height = height;
    }
    
    public void setColors(final Color fgColor, final Color bgColor, final Color bdColor) {
        this.fgColor = fgColor;
        this.bgColor = bgColor;
        this.bdColor = bdColor;
    }
    
    public boolean inside(final int n, final int n2) {
        return n > this.x && n < this.x + this.width && n2 > this.y && n2 < this.y + this.height;
    }
    
    public void draw(final Graphics graphics) {
        graphics.setColor(this.bgColor);
        graphics.fillRect(this.x, this.y, this.width, this.height);
        graphics.setColor(this.bdColor);
        graphics.drawRect(this.x, this.y, this.width - 1, this.height - 1);
        graphics.setColor(this.fgColor);
        if (this.text != null) {
            graphics.setFont(this.font);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            graphics.drawString(this.text, this.x + (this.width - fontMetrics.stringWidth(this.text)) / 2, this.y + (this.height + fontMetrics.getAscent()) / 2);
        }
        if (this.polygon != null) {
            final int n = this.x + this.width / 2;
            final int n2 = this.y + this.height / 2;
            graphics.translate(n, n2);
            graphics.fillPolygon(this.polygon);
            graphics.translate(-n, -n2);
        }
    }
}
