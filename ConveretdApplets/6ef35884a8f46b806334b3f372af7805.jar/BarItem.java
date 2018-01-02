import java.net.MalformedURLException;
import java.net.URL;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

class BarItem
{
    int x;
    int y;
    int maxX;
    int maxY;
    String comm;
    static Font CommFont;
    static Font font;
    static Color BuCol;
    static Color TCol;
    static Color HiCol;
    
    public BarItem() {
        final boolean b = false;
        this.maxY = (b ? 1 : 0);
        this.maxX = (b ? 1 : 0);
        this.y = (b ? 1 : 0);
        this.x = (b ? 1 : 0);
    }
    
    public BarItem(final int x, final int y, final int maxX, final int maxY) {
        this.x = x;
        this.y = y;
        this.maxX = maxX;
        this.maxY = maxY;
    }
    
    public void setFonts(final String s, final int n, final int n2, final String s2, final int n3, final int n4) {
        BarItem.font = new Font(s, n2, n);
        BarItem.CommFont = new Font(s2, n4, n3);
    }
    
    public void setColors(final Color buCol, final Color hiCol, final Color tCol) {
        BarItem.BuCol = buCol;
        BarItem.TCol = tCol;
        BarItem.HiCol = hiCol;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getHeight() {
        return this.maxY - this.y;
    }
    
    public int getWidth() {
        return this.maxX - this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public String getStat() {
        return this.comm;
    }
    
    public String getComment() {
        return this.comm;
    }
    
    public void Show(final Graphics graphics) {
        graphics.setColor(BarItem.BuCol);
        graphics.fillRect(this.x, this.y, this.maxX - this.x, this.maxY - this.y);
    }
    
    public void mouseOn(final int n, final int n2, final int n3, final Graphics graphics) {
        graphics.setFont(BarItem.CommFont);
        final FontMetrics fontMetrics = graphics.getFontMetrics(BarItem.CommFont);
        final int n4 = fontMetrics.stringWidth(this.comm) + 10;
        int n5;
        if (this.x + n4 + (this.maxX - this.x) / 2 > n2) {
            n5 = n2 - n4 - 7;
        }
        else {
            n5 = this.x + (this.maxX - this.x) / 2;
        }
        final int n6 = n4 + 2;
        final int n7 = n - fontMetrics.getHeight() - 3;
        final int n8 = fontMetrics.getHeight() + 1;
        graphics.setColor(Color.black);
        graphics.drawRect(n5 - 1, n7 - 1, n6 + 1, n8 + 1);
        graphics.setColor(BarItem.HiCol);
        graphics.fillRect(n5, n7, n6, n8);
        graphics.setColor(BarItem.TCol);
        graphics.drawString(this.comm, n5 + 5, n7 + 3 * fontMetrics.getHeight() / 4 + 2);
    }
    
    public void mouseUp(final Graphics graphics) {
    }
    
    public void mouseDown(final Graphics graphics) {
    }
    
    public URL clicked() {
        try {
            return new URL("http://www.consultcom.com/");
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    public boolean isYours(final int n, final int n2) {
        return n > this.x && n < this.maxX && n2 > this.y && n2 < this.maxY;
    }
}
