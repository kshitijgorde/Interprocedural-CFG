import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Component;
import java.net.URL;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Cursor;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class colormenu extends Applet implements Runnable
{
    Cursor cursor;
    Thread runner;
    Image offimage;
    Graphics off;
    String[] text;
    int antal;
    Font font;
    URL[] links;
    int f1;
    int f2;
    int f3;
    int f4;
    int f5;
    int f6;
    int b1;
    int b2;
    int b3;
    int b4;
    int b5;
    int b6;
    int b7;
    int b8;
    int b9;
    String tfarve;
    String target;
    boolean firsttime;
    int Width;
    int Height;
    int over;
    int bf;
    int border;
    
    public colormenu() {
        this.antal = 0;
        this.target = "_self";
        this.firsttime = true;
        this.over = -1;
        this.bf = 0;
        this.border = 0;
    }
    
    public Frame getFrame(Component parent) {
        while (parent != null && !(parent instanceof Frame)) {
            parent = parent.getParent();
        }
        return (Frame)parent;
    }
    
    public void init() {
        this.tfarve = this.getParameter("selection_color");
        this.b7 = Integer.parseInt(this.tfarve.substring(0, 3));
        this.b8 = Integer.parseInt(this.tfarve.substring(4, 7));
        this.b9 = Integer.parseInt(this.tfarve.substring(8, 11));
        this.tfarve = this.getParameter("text_color");
        this.f4 = Integer.parseInt(this.tfarve.substring(0, 3));
        this.f5 = Integer.parseInt(this.tfarve.substring(4, 7));
        this.f6 = Integer.parseInt(this.tfarve.substring(8, 11));
        this.tfarve = this.getParameter("background_color");
        this.f1 = Integer.parseInt(this.tfarve.substring(0, 3));
        this.f2 = Integer.parseInt(this.tfarve.substring(4, 7));
        this.f3 = Integer.parseInt(this.tfarve.substring(8, 11));
        this.tfarve = this.getParameter("border_color");
        this.b1 = Integer.parseInt(this.tfarve.substring(0, 1));
        this.b2 = Integer.parseInt(this.tfarve.substring(2, 3));
        this.b3 = Integer.parseInt(this.tfarve.substring(4, 5));
        this.tfarve = this.getParameter("fade_color");
        this.b4 = Integer.parseInt(this.tfarve.substring(0, 1));
        this.b5 = Integer.parseInt(this.tfarve.substring(2, 3));
        this.b6 = Integer.parseInt(this.tfarve.substring(4, 5));
        this.tfarve = this.getParameter("fontface");
        this.font = new Font(this.tfarve, 0, Integer.parseInt(this.getParameter("fontsize")));
        if (this.getParameter("target") != null) {
            this.target = this.getParameter("target");
        }
        for (int n = 1; this.getParameter("text" + n) != null; ++n) {
            ++this.antal;
        }
        this.text = new String[this.antal];
        this.links = new URL[this.antal];
        for (int i = 0; i < this.antal; ++i) {
            try {
                this.links[i] = new URL(this.getCodeBase(), this.getParameter("link" + (i + 1)));
            }
            catch (Exception ex) {}
            this.text[i] = this.getParameter("text" + (i + 1));
        }
        this.Width = this.size().width;
        this.Height = this.size().height;
        this.offimage = this.createImage(this.Width, this.Height);
        (this.off = this.offimage.getGraphics()).setFont(this.font);
        this.off.setColor(new Color(this.f1, this.f2, this.f3));
        this.off.fillRect(0, 0, this.Width, this.Height);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        for (int i = 0; i < this.antal; ++i) {
            if (n2 > 30 * i && n2 < 30 + 30 * i) {
                this.getAppletContext().showDocument(this.links[i], this.target);
            }
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.over = -1;
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        for (int i = 0; i < this.antal; ++i) {
            if (n2 > 30 * i && n2 < 30 + 30 * i) {
                this.over = i;
                return true;
            }
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.offimage, 0, 0, this);
    }
    
    public void run() {
        while (true) {
            for (int i = 0; i < this.antal; ++i) {
                if (this.firsttime) {
                    this.off.setColor(new Color(190 * this.b1, 190 * this.b2, 190 * this.b3));
                    this.off.drawRect(0, i * 30, this.Width, 30);
                    this.off.setColor(new Color(110 * this.b1, 110 * this.b2, 110 * this.b3));
                    this.off.drawRect(1, 1 + i * 30, this.Width - 2, 28);
                    this.off.setColor(new Color(70 * this.b1, 70 * this.b2, 70 * this.b3));
                    this.off.drawRect(2, 2 + i * 30, this.Width - 4, 26);
                    for (int j = 0; j < this.Width - 4; j += 2) {
                        this.off.setColor(new Color((int)(this.b4 * j / 1.2), (int)(this.b5 * j / 1.2), (int)(this.b6 * j / 1.2)));
                        this.off.drawLine(3 + j, 4 + i * 30, 3 + j, 3 + i * 30 + 23);
                    }
                    this.off.setColor(new Color(this.f4, this.f5, this.f6));
                    this.off.drawString(this.text[i], 8, 21 + i * 30);
                }
                this.off.setColor(new Color(this.f1, this.f2, this.f3));
                this.off.drawRect(3, 3 + i * 30, this.Width - 6, 24);
                if (i == this.over) {
                    this.off.setColor(new Color(this.b7, this.b8, this.b9));
                    this.off.drawRect(3, 3 + i * 30, this.Width - 6, 24);
                }
            }
            this.firsttime = false;
            this.repaint();
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {
                this.showStatus("Error " + ex);
            }
        }
    }
    
    public void start() {
        this.getFrame(this).setCursor(12);
        (this.runner = new Thread(this)).start();
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
            this.runner = null;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
