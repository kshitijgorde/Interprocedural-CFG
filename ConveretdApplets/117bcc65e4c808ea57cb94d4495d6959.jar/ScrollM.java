import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Point;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ScrollM extends Applet implements Runnable
{
    String scrolltxt;
    Color bgcolor;
    Color textcolor;
    Thread task;
    Image offscreenImage;
    Graphics offscreenGfx;
    Font theFont;
    FontMetrics fm;
    int stringheight;
    int stringwidth;
    int xpos;
    int ypos;
    boolean movestring;
    boolean moveleft;
    int speedleft;
    int speedright;
    int pixleft;
    int pixright;
    Point start;
    Point end;
    int fontsize;
    String fontname;
    
    public void init() {
        this.fontname = this.getParameter("fontname");
        if (this.fontname == null) {
            this.fontname = new String("Verdana");
        }
        final String parameter = this.getParameter("fontsize");
        if (parameter != null) {
            this.fontsize = Integer.valueOf(parameter);
        }
        this.theFont = new Font(this.fontname, 0, this.fontsize);
        this.fm = this.getFontMetrics(this.theFont);
        this.offscreenImage = this.createImage(this.size().width, this.size().height);
        this.offscreenGfx = this.offscreenImage.getGraphics();
        this.scrolltxt = this.getParameter("text");
        if (this.scrolltxt == null) {
            this.scrolltxt = new String("Error, Please refer to the doc file");
        }
        this.stringheight = this.fm.getHeight();
        this.stringwidth = this.fm.stringWidth(this.scrolltxt);
        this.ypos = (this.size().height + this.stringheight / 2) / 2;
        this.movestring = true;
        final String parameter2 = this.getParameter("msleft");
        if (parameter2 != null) {
            this.speedleft = Integer.valueOf(parameter2);
        }
        final String parameter3 = this.getParameter("msright");
        if (parameter3 != null) {
            this.speedright = Integer.valueOf(parameter3);
        }
        final String parameter4 = this.getParameter("pixleft");
        if (parameter4 != null) {
            this.pixleft = Integer.valueOf(parameter4);
        }
        final String parameter5 = this.getParameter("pixright");
        if (parameter5 != null) {
            this.pixright = Integer.valueOf(parameter5);
        }
        final String parameter6 = this.getParameter("textcolor");
        if (parameter6 != null) {
            this.textcolor = new Color(Integer.parseInt(parameter6, 16));
        }
        final String parameter7 = this.getParameter("bgcolor");
        if (parameter7 != null) {
            this.bgcolor = new Color(Integer.parseInt(parameter7, 16));
        }
        this.setBackground(this.bgcolor);
    }
    
    public void start() {
        (this.task = new Thread(this)).start();
    }
    
    public void stop() {
        if (this.task != null) {
            this.task.stop();
            this.task = null;
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.movestring = false;
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.movestring = true;
        this.repaint();
        return true;
    }
    
    public void run() {
        while (true) {
            this.xpos = this.size().width;
            while (this.xpos > -this.stringwidth) {
                this.moveleft = true;
                this.repaint();
                try {
                    Thread.sleep(this.speedleft);
                }
                catch (InterruptedException ex) {}
                this.xpos -= this.pixleft;
            }
            this.xpos = -this.stringwidth;
            while (this.xpos <= this.size().width) {
                this.moveleft = false;
                this.repaint();
                try {
                    Thread.sleep(this.speedright);
                }
                catch (InterruptedException ex2) {}
                this.xpos += this.pixright;
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.offscreenGfx.setColor(this.getBackground());
        this.offscreenGfx.fillRect(0, 0, this.size().width, this.size().height);
        this.offscreenGfx.setFont(this.theFont);
        this.offscreenGfx.setColor(this.textcolor);
        if (this.movestring) {
            this.offscreenGfx.drawString(this.scrolltxt, this.xpos, this.ypos);
        }
        else if (!this.movestring) {
            if (this.moveleft) {
                ++this.xpos;
                this.offscreenGfx.drawString(this.scrolltxt, this.xpos, this.ypos);
            }
            else if (!this.moveleft) {
                --this.xpos;
                this.offscreenGfx.drawString(this.scrolltxt, this.xpos, this.ypos);
            }
        }
        graphics.drawImage(this.offscreenImage, 0, 0, this);
    }
    
    public ScrollM() {
        this.bgcolor = new Color(16777215);
        this.textcolor = new Color(0);
        this.speedleft = 20;
        this.speedright = 20;
        this.pixleft = 1;
        this.pixright = 1;
        this.fontsize = 10;
    }
}
