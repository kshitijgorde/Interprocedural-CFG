import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class FuncTable
{
    public static final String s_nil = "";
    public int n_entries;
    public int x0;
    public int y0;
    public int xsize;
    public int ysize;
    public int ydelta;
    String headline;
    String[] tab;
    Graphics g;
    Font def_font;
    Font tab_font;
    static final Color menuHighlight;
    
    FuncTable(final int n_entries, final String headline, final int x0, final int y0, final int ydelta) {
        this.g = this.g;
        this.n_entries = n_entries;
        this.x0 = x0;
        this.y0 = y0;
        this.ydelta = ydelta;
        this.headline = headline;
        this.tab = new String[n_entries];
        this.ysize = (n_entries + 1) * ydelta;
        this.xsize = 0;
        this.tab_font = null;
        this.def_font = null;
    }
    
    public void setFonts(final Font def_font, final Font tab_font) {
        this.def_font = def_font;
        this.tab_font = tab_font;
    }
    
    public void setEntry(final int n, final String s) {
        if (n >= 0 && n < this.n_entries) {
            this.tab[n] = s;
        }
    }
    
    public String getHeadline() {
        return this.headline;
    }
    
    public String getEntry(final int n) {
        if (n >= 0 && n < this.n_entries) {
            return this.tab[n];
        }
        return "";
    }
    
    private int yEntry(final int n) {
        return this.y0 + (n + 2) * this.ydelta;
    }
    
    private void drawEntry(final Graphics graphics, final int n) {
        if (n >= 0 && n < this.n_entries) {
            graphics.setColor(FuncTable.menuHighlight);
            graphics.fillRect(this.x0 + 1, this.yEntry(n) - this.ydelta + 1, this.xsize - 2, this.ydelta - 2);
            graphics.setColor(Color.black);
            graphics.drawString(this.tab[n], this.x0 + 2, this.yEntry(n) - 2);
        }
    }
    
    public void drawHighlightEntry(final Graphics graphics, final int n) {
        if (this.tab_font != null) {
            graphics.setFont(this.tab_font);
        }
        if (n >= 0 && n < this.n_entries) {
            graphics.setColor(Color.white);
            graphics.fillRect(this.x0 + 1, this.yEntry(n) - this.ydelta + 1, this.xsize - 2, this.ydelta - 2);
            graphics.setColor(Color.black);
            graphics.drawString(this.tab[n], this.x0 + 2, this.yEntry(n) - 2);
        }
        if (this.def_font != null) {
            graphics.setFont(this.def_font);
        }
    }
    
    public void drawFuncTable(final Graphics g) {
        this.g = g;
        if (this.tab_font != null) {
            g.setFont(this.tab_font);
        }
        if (this.xsize == 0) {
            this.xsize = g.getFontMetrics().stringWidth(this.headline) + 2;
        }
        g.setColor(FuncTable.menuHighlight);
        g.fillRect(this.x0, this.y0, this.xsize, this.ysize);
        g.setColor(Color.black);
        g.drawRect(this.x0, this.y0, this.xsize, this.ysize);
        g.drawLine(this.x0, this.y0 + this.ydelta - 1, this.x0 + this.xsize - 1, this.y0 + this.ydelta - 1);
        g.drawString(this.headline, this.x0 + 2, this.y0 + this.ydelta - 2);
        for (int i = 0; i < this.n_entries; ++i) {
            this.drawEntry(g, i);
        }
        if (this.def_font != null) {
            g.setFont(this.def_font);
        }
    }
    
    public void draw(final Graphics graphics) {
        this.drawFuncTable(graphics);
    }
    
    public int getClickedEntry(final int n, final int n2) {
        if (n < this.x0 || n > this.x0 + this.xsize) {
            return -2;
        }
        if (n2 < this.y0 || n2 > this.y0 + this.ysize) {
            return -2;
        }
        return (int)Math.floor((n2 - this.y0) / this.ydelta) - 1;
    }
    
    static {
        menuHighlight = new Color(183, 183, 183);
    }
}
