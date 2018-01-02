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
    
    ionlink(final Applet applet, final Graphics g, final int i, final int j) {
        this(applet, g, i, j, j - 25);
    }
    
    ionlink(final Applet applet, final Graphics g, final int i, final int j, final int k) {
        this.MFONT = new Font("Arial", 1, 11);
        this.screenx = i;
        this.screeny = j;
        this.fullversion = false;
        final ion ion1 = new ion(applet.getDocumentBase().toString(), applet.getParameter("id_key"));
        if (!(this.fullversion = ion1.isFull())) {
            this.mainlink = "http://membres.lycos.fr/droles2cochonnes/";
            this.maintext = "Cobayes et Hamsters (c)";
        }
        else {
            this.maintext = applet.getParameter("maintext");
            this.mainlink = applet.getParameter("mainlink");
        }
        this.setcolor(Color.white, Color.gray, new Color(50, 50, 150), new Color(150, 50, 50));
        this.setsize(g, 1, k);
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
    
    public boolean isOver(int i, int j) {
        i -= this.xpos;
        j -= this.ypos;
        return this.over = (this.maintext != null && i > 0 && j > 0 && i < this.width && j < this.height);
    }
    
    public void setcenter(final Graphics g, final int i) {
        this.setsize(g, 1, i);
    }
    
    public void setcolor(final Color color, final Color color1, final Color color2, final Color color3) {
        this.bgcolor = color;
        this.bdcolor = color1;
        this.tcolor = color2;
        this.acolor = color3;
    }
    
    public void setright(final Graphics g, final int i) {
        this.setsize(g, 0, i);
    }
    
    private void setsize(final Graphics g, final int i, final int j) {
        if (this.maintext != null) {
            g.setFont(this.MFONT);
            final FontMetrics fontmetrics = g.getFontMetrics();
            this.width = fontmetrics.stringWidth(this.maintext) + 10;
            switch (i) {
                case 1: {
                    this.xpos = (this.screenx >> 1) - (this.width >> 1);
                    this.ypos = j;
                    break;
                }
                default: {
                    this.xpos = this.screenx - this.width - 6;
                    this.ypos = j;
                    break;
                }
            }
            this.height = 15;
        }
    }
    
    public void setxy(final int i, final int j) {
        this.xpos = i;
        this.ypos = j;
    }
}
