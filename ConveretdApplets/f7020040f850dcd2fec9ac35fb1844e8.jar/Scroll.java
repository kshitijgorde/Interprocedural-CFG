import java.awt.image.ImageObserver;
import java.awt.Event;
import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Scroll extends Applet implements Runnable
{
    Thread scroll;
    String text;
    int scrollX;
    int textLength;
    Image workspace;
    Graphics offscreen;
    Color lightBlue;
    Color darkBlue;
    Color baggrund;
    Font current;
    
    public void init() {
        final String parameter = this.getParameter("Text");
        if (parameter != null) {
            this.text = parameter;
        }
        this.textLength = this.text.length() * 6;
        final String parameter2 = this.getParameter("ScrollFarveRGB");
        if (parameter2 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter2, ",");
            this.lightBlue = new Color(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
            this.darkBlue = this.lightBlue.darker();
            this.darkBlue = this.darkBlue.darker();
        }
        final String parameter3 = this.getParameter("BaggrundsFarveRGB");
        if (parameter3 != null) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(parameter3, ",");
            this.baggrund = new Color(Integer.parseInt(stringTokenizer2.nextToken()), Integer.parseInt(stringTokenizer2.nextToken()), Integer.parseInt(stringTokenizer2.nextToken()));
        }
        this.setBackground(this.baggrund);
        this.workspace = this.createImage(600, 18);
        this.offscreen = this.workspace.getGraphics();
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 504: {
                this.showStatus("Scroll Applet - wizax.cd@get2net.dk");
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    final void musInd() {
        this.showStatus("Scroll Applet - wizax.cd@get2net.dk");
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.workspace, 0, 0, this);
    }
    
    public void start() {
        if (this.scroll == null) {
            (this.scroll = new Thread(this)).start();
        }
    }
    
    public void run() {
        while (true) {
            --this.scrollX;
            if (this.scrollX < -this.textLength) {
                this.scrollX = 601;
            }
            this.offscreen.setFont(this.current);
            this.offscreen.setColor(this.baggrund);
            this.offscreen.fillRect(0, 0, 600, 18);
            this.offscreen.setColor(this.darkBlue);
            this.offscreen.drawString(this.text, this.scrollX + 3, 14);
            this.offscreen.drawString(this.text, this.scrollX + 2, 13);
            this.offscreen.drawString(this.text, this.scrollX + 1, 12);
            this.offscreen.setColor(this.lightBlue);
            this.offscreen.drawString(this.text, this.scrollX, 11);
            this.repaint();
            this.vent(14);
        }
    }
    
    public void stop() {
        if (this.scroll != null) {
            this.scroll.stop();
            this.scroll = null;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    final void vent(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public Scroll() {
        this.text = "Ingen text i HTML...";
        this.scrollX = 601;
        this.lightBlue = new Color(140, 140, 255);
        this.darkBlue = new Color(20, 25, 165);
        this.baggrund = new Color(0, 0, 0);
        this.current = new Font("Helvetica", 1, 11);
    }
}
