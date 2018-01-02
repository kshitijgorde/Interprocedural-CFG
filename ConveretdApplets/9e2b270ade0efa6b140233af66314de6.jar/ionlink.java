import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

class ionlink
{
    private final Font MFONT;
    private boolean fullversion;
    private String maintext;
    private String mainlink;
    private int screenx;
    private int screeny;
    private int xpos;
    private int ypos;
    private int width;
    private int height;
    private Color bgcolor;
    private Color bdcolor;
    private Color tcolor;
    private Color acolor;
    private boolean over;
    
    ionlink(final Applet applet, final Graphics graphics, final int n, final int n2) {
        this(applet, graphics, n, n2, n2 - 25);
    }
    
    ionlink(final Applet applet, final Graphics graphics, final int screenx, final int screeny, final int n) {
        this.MFONT = new Font("Arial", 1, 11);
        this.screenx = screenx;
        this.screeny = screeny;
        this.fullversion = false;
        if (!(this.fullversion = new ion(applet.getDocumentBase().getHost(), applet.getParameter("id_key")).isFull())) {
            this.mainlink = "http://www.6sense.com/applets/";
            this.maintext = "6sense.com Applets";
        }
        else {
            this.maintext = applet.getParameter("maintext");
            this.mainlink = applet.getParameter("mainlink");
        }
        this.setcolor(Color.white, Color.gray, new Color(50, 50, 150), new Color(150, 50, 50));
        this.setsize(graphics, 1, n);
    }
    
    public void draw(final Graphics graphics) {
        if (this.maintext != null) {
            graphics.setColor(Color.black);
            graphics.fillRect(this.xpos + 2, this.ypos + 2, this.width, 15);
            graphics.setColor(this.bgcolor);
            graphics.fillRect(this.xpos + 1, this.ypos + 1, this.width - 1, 14);
            graphics.setColor(this.bdcolor);
            graphics.drawRect(this.xpos, this.ypos, this.width, 15);
            if (!this.over) {
                graphics.setColor(this.tcolor);
            }
            else {
                graphics.setColor(this.acolor);
            }
            graphics.setFont(this.MFONT);
            graphics.drawString(this.maintext, this.xpos + 6, this.ypos + 12);
        }
    }
    
    public String getLink() {
        if (this.mainlink != null) {
            return this.mainlink;
        }
        return "";
    }
    
    public boolean isOver(int n, int n2) {
        n -= this.xpos;
        n2 -= this.ypos;
        return this.over = (this.maintext != null && n > 0 && n2 > 0 && n < this.width && n2 < this.height);
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
        if (this.maintext != null) {
            graphics.setFont(this.MFONT);
            this.width = graphics.getFontMetrics().stringWidth(this.maintext) + 10;
            switch (n) {
                case 1: {
                    this.xpos = (this.screenx >> 1) - (this.width >> 1);
                    this.ypos = n2;
                    break;
                }
                default: {
                    this.xpos = this.screenx - this.width - 6;
                    this.ypos = n2;
                    break;
                }
            }
            this.height = 15;
        }
    }
    
    public void setxy(final int xpos, final int ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
    }
}
