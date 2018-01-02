import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.applet.Applet;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class foreimage
{
    private Image img;
    private int width;
    private int height;
    private int imgxsize;
    private int imgysize;
    private int xpos;
    private int ypos;
    private int xmove;
    private int ymove;
    private boolean exist;
    private boolean isMove;
    
    foreimage(final Applet applet, final int n) {
        this.xpos = 0;
        this.ypos = 0;
        this.exist = false;
        this.xmove = 0;
        this.ymove = 0;
        this.isMove = false;
        if (n > 0) {
            final String parameter = applet.getParameter("fgimage" + n);
            if (parameter != null) {
                this.exist = true;
                final MediaTracker mediaTracker = new MediaTracker(applet);
                System.err.println("Forground: " + applet.getCodeBase() + parameter);
                try {
                    mediaTracker.addImage(this.img = applet.getImage(applet.getCodeBase(), parameter), 0);
                    mediaTracker.waitForID(0);
                }
                catch (InterruptedException ex) {
                    this.img = null;
                    this.exist = false;
                }
                if (this.exist) {
                    final Dimension size = applet.getSize();
                    this.width = size.width;
                    this.height = size.height;
                    this.imgxsize = this.img.getWidth(null);
                    this.imgysize = this.img.getHeight(null);
                    final String parameter2 = applet.getParameter("fgxpos" + n);
                    if (parameter2 != null) {
                        this.xpos = Integer.parseInt(parameter2, 10);
                    }
                    final String parameter3 = applet.getParameter("fgypos" + n);
                    if (parameter3 != null) {
                        this.ypos = Integer.parseInt(parameter3, 10);
                    }
                    final String parameter4 = applet.getParameter("fgxmove" + n);
                    if (parameter4 != null) {
                        this.xmove = Integer.parseInt(parameter4, 10);
                    }
                    final String parameter5 = applet.getParameter("fgymove" + n);
                    if (parameter5 != null) {
                        this.ymove = Integer.parseInt(parameter5, 10);
                    }
                    if (this.xmove != 0 || this.ymove != 0) {
                        this.isMove = true;
                    }
                }
                System.err.println(String.valueOf(n) + ": (" + this.xpos + "," + this.ypos + ") " + this.img + " " + this.exist);
            }
        }
    }
    
    public void draw(final Graphics graphics) {
        if (this.exist) {
            graphics.drawImage(this.img, this.xpos, this.ypos, null);
        }
        this.move();
    }
    
    private void move() {
        if (this.isMove) {
            this.xpos += this.xmove;
            this.ypos += this.ymove;
            if (this.xpos > this.width) {
                this.xpos = -this.imgxsize;
            }
            else if (this.xpos < -this.imgxsize) {
                this.xpos = this.width;
            }
            if (this.ypos > this.height) {
                this.ypos = -this.imgysize;
            }
            else if (this.ypos < -this.imgysize) {
                this.ypos = this.height;
            }
        }
    }
    
    public void move(final int n, final int n2) {
        this.xpos += n;
        this.ypos += n2;
    }
    
    public void setxy(final int xpos, final int ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
    }
}
