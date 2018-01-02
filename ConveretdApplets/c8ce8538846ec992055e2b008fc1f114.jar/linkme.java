import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class linkme
{
    private final Font MFONT;
    private String MAINTEXT;
    private String MAINLINK;
    private int GLSTART;
    private int GLWIDTH;
    private int GLYSIZE;
    private int width;
    private Color bgcolor;
    private Color bdcolor;
    private Color tcolor;
    private Color acolor;
    private boolean over;
    boolean linkowner;
    boolean fullversion;
    
    linkme(final Graphics graphics, final int width, final String maintext, final String mainlink, final String maintext2, final String mainlink2, final boolean fullversion) {
        this.MFONT = new Font("Arial", 1, 11);
        this.MAINTEXT = maintext;
        this.MAINLINK = mainlink;
        this.over = false;
        this.linkowner = false;
        this.width = width;
        this.fullversion = fullversion;
        if (fullversion) {
            this.MAINTEXT = null;
            if (mainlink2 != null) {
                this.MAINLINK = mainlink2;
                if (maintext2 != null) {
                    this.MAINTEXT = maintext2;
                }
                else {
                    this.MAINTEXT = this.MAINLINK;
                }
            }
        }
        this.setcolor(Color.white, Color.gray, new Color(50, 50, 150), new Color(150, 50, 50));
        this.setright(graphics, 6);
    }
    
    public void draw(final Graphics graphics) {
        if (this.linkowner && this.MAINTEXT != null) {
            graphics.setColor(Color.black);
            graphics.fillRect(this.GLSTART + 2, this.GLYSIZE + 2, this.GLWIDTH, 15);
            graphics.setColor(this.bgcolor);
            graphics.fillRect(this.GLSTART + 1, this.GLYSIZE + 1, this.GLWIDTH - 1, 14);
            graphics.setColor(this.bdcolor);
            graphics.drawRect(this.GLSTART, this.GLYSIZE, this.GLWIDTH, 15);
            if (!this.over) {
                graphics.setColor(this.tcolor);
            }
            else {
                graphics.setColor(this.acolor);
            }
            graphics.setFont(this.MFONT);
            graphics.drawString(this.MAINTEXT, this.GLSTART + 6, this.GLYSIZE + 12);
        }
    }
    
    public String getLink() {
        if (this.MAINLINK != null) {
            return this.MAINLINK;
        }
        return "";
    }
    
    public boolean isOver(int n, int n2) {
        n -= this.GLSTART;
        n2 -= this.GLYSIZE;
        return this.over = (this.MAINTEXT != null && n > 0 && n2 > 0 && n < this.GLWIDTH && n2 < 15);
    }
    
    public void setcenter(final Graphics graphics, final int n) {
        this.setsize(graphics, 1, n);
    }
    
    public void setcolor(final Color bgcolor, final Color bdcolor, final Color tcolor, final Color acolor) {
        this.bgcolor = bgcolor;
        this.bdcolor = bdcolor;
        this.tcolor = tcolor;
        this.acolor = acolor;
    }
    
    public void setright(final Graphics graphics, final int n) {
        this.setsize(graphics, 0, n);
    }
    
    private void setsize(final Graphics graphics, final int n, final int n2) {
        if (this.MAINTEXT != null) {
            graphics.setFont(this.MFONT);
            this.GLWIDTH = graphics.getFontMetrics().stringWidth(this.MAINTEXT) + 10;
            switch (n) {
                case 1: {
                    this.GLSTART = this.width / 2 - this.GLWIDTH / 2;
                    this.GLYSIZE = n2;
                    break;
                }
                default: {
                    this.GLSTART = this.width - this.GLWIDTH - 6;
                    this.GLYSIZE = n2;
                    break;
                }
            }
        }
    }
    
    public void setxy(final int glstart, final int glysize) {
        this.GLSTART = glstart;
        this.GLYSIZE = glysize;
    }
}
