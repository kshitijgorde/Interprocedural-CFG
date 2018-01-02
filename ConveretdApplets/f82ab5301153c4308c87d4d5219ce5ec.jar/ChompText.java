import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ChompText extends Applet implements Runnable
{
    String text;
    int textX;
    int textY;
    int bob;
    int Xbob;
    int pause;
    boolean t;
    byte sven;
    Color bgColor;
    Color textColor;
    Thread runner;
    Image chomp;
    Image workspace;
    Graphics offscreen;
    
    public ChompText() {
        this.bob = 100;
        this.pause = 2000;
        this.t = false;
        this.sven = 0;
        this.bgColor = Color.white;
        this.textColor = Color.black;
    }
    
    public void init() {
        this.showStatus("Loading...");
        this.workspace = this.createImage(this.getSize().width, this.getSize().height);
        this.offscreen = this.workspace.getGraphics();
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(this.chomp = this.getImage(this.getCodeBase(), "chompani.gif"), 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        final String parameter = this.getParameter("text");
        this.text = ((parameter != null) ? parameter : "Text");
        final String parameter2 = this.getParameter("pause");
        if (parameter2 != null) {
            this.pause = Integer.parseInt(parameter2);
        }
        final String parameter3 = this.getParameter("bgcolor");
        if (parameter3 != null) {
            this.bgColor = new Color(Integer.parseInt(parameter3, 16));
        }
        final String parameter4 = this.getParameter("textcolor");
        if (parameter4 != null) {
            this.textColor = new Color(Integer.parseInt(parameter4, 16));
        }
        final Font font = new Font("Helvetica", 1, 25);
        this.offscreen.setFont(font);
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        this.textX = this.getSize().width / 2 - fontMetrics.stringWidth(this.text) / 2;
        this.textY = this.getSize().height / 2 + fontMetrics.getHeight() / 3;
    }
    
    public void paint(final Graphics graphics) {
        if (this.textX - this.bob - 5 < this.getSize().width) {
            if (this.sven == 0) {
                this.Xbob = 0;
                this.sven = 1;
            }
            else {
                this.Xbob = this.textX - this.bob;
            }
            this.offscreen.setColor(this.bgColor);
            this.offscreen.fillRect(0, 0, this.getSize().width, this.getSize().height);
            this.offscreen.setColor(this.textColor);
            this.offscreen.drawString(this.text, this.textX, this.textY);
            this.offscreen.setColor(this.bgColor);
            this.offscreen.fillRect(0, this.textY - 34, this.Xbob + 25, 49);
            this.offscreen.drawImage(this.chomp, this.Xbob, this.textY - 34, this);
            graphics.drawImage(this.workspace, 0, 0, this);
            --this.bob;
        }
        else {
            this.bob = 60;
            this.sven = 0;
            try {
                Thread.sleep(this.pause);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void run() {
        while (true) {
            this.repaint();
            try {
                Thread.sleep(12L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
        this.showStatus("ChompText applet from virtig01.cjb.net");
    }
}
