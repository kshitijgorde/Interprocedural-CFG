import java.awt.FontMetrics;
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
    
    ionlink(final Applet app, final Graphics g, final int x, final int y) {
        this(app, g, x, y, y - 25);
    }
    
    ionlink(final Applet app, final Graphics g, final int x, final int y, final int vpos) {
        this.MFONT = new Font("Arial", 1, 11);
        this.screenx = x;
        this.screeny = y;
        this.fullversion = false;
        final ion version = new ion(app.getDocumentBase().getHost(), app.getParameter("id_key"));
        if (!(this.fullversion = version.isFull())) {
            this.mainlink = "http://www.6sense.com/applets/";
            this.maintext = "6sense.com Applets";
        }
        else {
            this.maintext = app.getParameter("maintext");
            this.mainlink = app.getParameter("mainlink");
        }
        this.setcolor(Color.white, Color.gray, new Color(50, 50, 150), new Color(150, 50, 50));
        this.setsize(g, 1, vpos);
    }
    
    public void draw(final Graphics g) {
        if (this.maintext != null) {
            g.setColor(Color.black);
            g.fillRect(this.xpos + 2, this.ypos + 2, this.width, 15);
            g.setColor(this.bgcolor);
            g.fillRect(this.xpos + 1, this.ypos + 1, this.width - 1, 14);
            g.setColor(this.bdcolor);
            g.drawRect(this.xpos, this.ypos, this.width, 15);
            if (!this.over) {
                g.setColor(this.tcolor);
            }
            else {
                g.setColor(this.acolor);
            }
            g.setFont(this.MFONT);
            g.drawString(this.maintext, this.xpos + 6, this.ypos + 12);
        }
    }
    
    public String getLink() {
        if (this.mainlink != null) {
            return this.mainlink;
        }
        return "";
    }
    
    public boolean isOver(int x, int y) {
        x -= this.xpos;
        y -= this.ypos;
        return this.over = (this.maintext != null && x > 0 && y > 0 && x < this.width && y < this.height);
    }
    
    public void setcenter(final Graphics g, final int y) {
        this.setsize(g, 1, y);
    }
    
    public void setcolor(final Color bg, final Color bd, final Color tx, final Color al) {
        this.bgcolor = bg;
        this.bdcolor = bd;
        this.tcolor = tx;
        this.acolor = al;
    }
    
    public void setright(final Graphics g, final int y) {
        this.setsize(g, 0, y);
    }
    
    private void setsize(final Graphics g, final int type, final int y) {
        if (this.maintext != null) {
            g.setFont(this.MFONT);
            final FontMetrics fm = g.getFontMetrics();
            this.width = fm.stringWidth(this.maintext) + 10;
            switch (type) {
                case 1: {
                    this.xpos = (this.screenx >> 1) - (this.width >> 1);
                    this.ypos = y;
                    break;
                }
                default: {
                    this.xpos = this.screenx - this.width - 6;
                    this.ypos = y;
                    break;
                }
            }
            this.height = 15;
        }
    }
    
    public void setxy(final int x, final int y) {
        this.xpos = x;
        this.ypos = y;
    }
}
