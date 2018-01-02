import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Frame;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ImageZoom extends Applet implements Runnable
{
    Thread runner;
    Image pic;
    Image theCanvas;
    int zoom;
    int Height;
    int Width;
    int xpos;
    int ypos;
    int level;
    int speed;
    int xMove;
    int yMove;
    int cursorX;
    int cursorY;
    boolean preload;
    Object theFrame;
    
    public ImageZoom() {
        this.runner = null;
        this.zoom = 1;
        this.level = 0;
        this.speed = 6;
        this.xMove = 0;
        this.yMove = 0;
        this.cursorX = -5000;
        this.cursorY = -5000;
        this.preload = false;
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 501: {
                if (event.x > this.size().width - 108 && event.x < this.size().width - 30 && event.y > this.size().height - 19) {
                    try {
                        this.getAppletContext().showDocument(new URL("http://www.vivaorange.com"), "_blank");
                    }
                    catch (MalformedURLException ex) {}
                    break;
                }
                ++this.zoom;
                this.xpos -= this.Width / 2;
                this.ypos -= this.Height / 2;
                if (this.zoom > this.level) {
                    this.zoom = 1;
                    this.xpos = 0;
                    this.ypos = 0;
                    break;
                }
                break;
            }
            case 503: {
                if (event.x > this.size().width - 108 && event.x < this.size().width - 30 && event.y > this.size().height - 19) {
                    if (this.theFrame != null) {
                        ((Frame)this.theFrame).setCursor(12);
                    }
                }
                else if (this.theFrame != null) {
                    ((Frame)this.theFrame).setCursor(0);
                }
                this.cursorX = event.x - 20;
                this.cursorY = event.y - 20;
                if (event.x > 4 * this.Width / 5) {
                    this.xMove = -this.Width / ((11 - this.speed) * 10);
                }
                else if (event.x < this.Width / 5) {
                    this.xMove = this.Width / ((11 - this.speed) * 10);
                }
                else {
                    this.xMove = 0;
                }
                if (event.y > 4 * this.Height / 5) {
                    this.yMove = -this.Height / ((11 - this.speed) * 10);
                    break;
                }
                if (event.y < this.Height / 5) {
                    this.yMove = this.Height / ((11 - this.speed) * 10);
                    break;
                }
                this.yMove = 0;
                break;
            }
            case 505: {
                this.cursorX = -5000;
                this.cursorY = -5000;
                this.xMove = 0;
                this.yMove = 0;
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void init() {
        String parameter;
        try {
            parameter = this.getParameter("IMAGE");
        }
        catch (NullPointerException ex) {
            parameter = "ABC.gif";
        }
        try {
            this.level = Integer.parseInt(this.getParameter("ZoomLevel"));
            if (this.level < 1) {
                this.level = 5;
            }
        }
        catch (NumberFormatException ex2) {
            this.level = 5;
        }
        catch (NullPointerException ex3) {}
        try {
            this.speed = Integer.parseInt(this.getParameter("PanSpeed"));
            if (this.speed < 1 || this.speed > 10) {
                this.speed = 6;
            }
        }
        catch (NumberFormatException ex4) {
            this.speed = 6;
        }
        catch (NullPointerException ex5) {}
        try {
            this.pic = (parameter.toLowerCase().startsWith("http") ? this.getImage(new URL(parameter)) : this.getImage(this.getDocumentBase(), parameter));
        }
        catch (MalformedURLException ex6) {}
        try {
            this.preload = this.getParameter("preload").equals("ON");
        }
        catch (NullPointerException ex7) {}
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(this.pic, 1);
        try {
            mediaTracker.waitForID(1);
        }
        catch (InterruptedException ex8) {
            System.out.println("Cannot load Image");
        }
        this.Width = this.size().width;
        this.Height = this.size().height;
        this.theCanvas = this.createImage(this.Width, this.Height);
        if (this.preload) {
            for (int i = 2; i <= this.level; ++i) {
                this.theCanvas.getGraphics().drawImage(this.pic, this.xpos, this.ypos, this.Width * i, this.Height * i, this);
            }
        }
        this.xpos = 0;
        this.ypos = 0;
        this.theFrame = null;
        this.theFrame = this.getParent();
        while (!(this.theFrame instanceof Frame) && this.theFrame != null) {
            this.theFrame = ((Component)this.theFrame).getParent();
        }
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.theCanvas, 0, 0, this);
    }
    
    public void run() {
        while (true) {
            this.repaint();
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void setXPos() {
        this.xpos += this.xMove;
        if (this.xpos > 0) {
            this.xpos = 0;
        }
        else if (this.xpos < -(this.Width * (this.zoom - 1))) {
            this.xpos = -this.Width * (this.zoom - 1);
        }
    }
    
    public void setYPos() {
        this.ypos += this.yMove;
        if (this.ypos > 0) {
            this.ypos = 0;
        }
        else if (this.ypos < -(this.Height * (this.zoom - 1))) {
            this.ypos = -this.Height * (this.zoom - 1);
        }
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
        }
        this.runner = null;
    }
    
    public void update(final Graphics graphics) {
        this.setXPos();
        this.setYPos();
        final Graphics graphics2 = this.theCanvas.getGraphics();
        graphics2.drawImage(this.pic, this.xpos, this.ypos, this.Width * this.zoom, this.Height * this.zoom, this);
        graphics2.setColor(Color.orange);
        graphics2.drawOval(this.cursorX + 4, this.cursorY + 4, 20, 20);
        graphics2.drawOval(this.cursorX + 3, this.cursorY + 3, 22, 22);
        graphics2.drawOval(this.cursorX + 2, this.cursorY + 2, 24, 24);
        graphics2.drawOval(this.cursorX + 1, this.cursorY + 1, 26, 26);
        graphics2.setFont(new Font("Helvetica", 1, 20));
        graphics2.drawString("+", this.cursorX + 8, this.cursorY + 21);
        graphics2.setColor(Color.blue);
        graphics2.setFont(new Font("TimesRoman", 2, 14));
        graphics2.drawString("www.vivaorange.com", this.size().width - 122, this.size().height - 9);
        graphics2.dispose();
        this.paint(graphics);
    }
}
