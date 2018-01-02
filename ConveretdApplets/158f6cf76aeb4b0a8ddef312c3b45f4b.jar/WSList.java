import java.awt.Graphics;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

class WSList
{
    public int x;
    public int y;
    public int width;
    public int height;
    public int scroll;
    private Font font;
    private FontMetrics fm;
    private Color fgColor;
    private Color bgColor;
    private Color bdColor;
    private Color fdColor;
    
    public WSList(final int width, final int height, final Font font) {
        this.fgColor = Color.black;
        this.bgColor = Color.white;
        this.bdColor = Color.black;
        this.fdColor = Color.darkGray;
        this.x = 0;
        this.y = 0;
        this.width = width;
        this.height = height;
        this.scroll = 0;
        this.font = font;
    }
    
    public void setColors(final Color fgColor, final Color bgColor, final Color bdColor, final Color fdColor) {
        this.fgColor = fgColor;
        this.bgColor = bgColor;
        this.bdColor = bdColor;
        this.fdColor = fdColor;
    }
    
    public void draw(final Graphics graphics) {
        graphics.setColor(this.bgColor);
        graphics.fillRect(this.x, this.y, this.width, this.height);
        graphics.setFont(this.font);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        this.scroll = Math.min(Math.max(this.scroll, 0), Math.max(WordSearch.words.size() - this.height / fontMetrics.getHeight(), 0));
        final int n = this.x + fontMetrics.getMaxAdvance() / 2;
        for (int n2 = this.y + fontMetrics.getHeight(), scroll = this.scroll; scroll < WordSearch.words.size() && n2 < this.y + this.height; n2 += fontMetrics.getHeight(), ++scroll) {
            final WSWord wsWord = WordSearch.words.elementAt(scroll);
            if (wsWord.found) {
                graphics.setColor(this.fdColor);
            }
            else {
                graphics.setColor(this.fgColor);
            }
            graphics.drawString(wsWord.list, n, n2);
        }
        graphics.setColor(this.bdColor);
        graphics.drawRect(this.x, this.y, this.width - 1, this.height - 1);
    }
}
