import java.applet.AppletContext;
import java.net.MalformedURLException;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.awt.Color;
import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class zLightMessage extends Applet implements Runnable
{
    Thread mythread;
    Image offI;
    Graphics offG;
    int xpos;
    Font font;
    String bscolor;
    String tscolor;
    String lscolor;
    String text;
    StringTokenizer bc;
    StringTokenizer tc;
    StringTokenizer lc;
    Color bcolor;
    Color tcolor;
    Color lcolor;
    URL url;
    
    public void init() {
        this.bscolor = this.getParameter("backgroundcolor");
        this.bc = new StringTokenizer(this.bscolor, ",");
        this.setBackground(this.bcolor = new Color(Integer.parseInt(this.bc.nextToken()), Integer.parseInt(this.bc.nextToken()), Integer.parseInt(this.bc.nextToken())));
        this.tscolor = this.getParameter("textcolor");
        this.tc = new StringTokenizer(this.tscolor, ",");
        this.tcolor = new Color(Integer.parseInt(this.tc.nextToken()), Integer.parseInt(this.tc.nextToken()), Integer.parseInt(this.tc.nextToken()));
        this.lscolor = this.getParameter("lightcolor");
        this.lc = new StringTokenizer(this.lscolor, ",");
        this.lcolor = new Color(Integer.parseInt(this.lc.nextToken()), Integer.parseInt(this.lc.nextToken()), Integer.parseInt(this.lc.nextToken()));
        this.text = this.getParameter("text");
        this.offI = this.createImage(this.size().width, this.size().height);
        this.offG = this.offI.getGraphics();
        this.font = new Font("TimesRoman", 1, 24);
    }
    
    public void paint(final Graphics g) {
        g.drawImage(this.offI, 0, 0, this);
    }
    
    public void start() {
        (this.mythread = new Thread(this)).start();
    }
    
    public void run() {
        while (true) {
            this.light();
        }
    }
    
    public void stop() {
        this.mythread.stop();
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void light() {
        while (this.xpos < this.size().width - 50) {
            try {
                Thread.sleep(10L);
                this.offG.clearRect(0, 0, this.size().width, this.size().height);
                this.offG.setClip(this.xpos, 0, 50, this.size().height);
                this.offG.setColor(this.lcolor);
                this.offG.fillOval(this.xpos, 1, 50, this.size().height - 2);
                this.offG.setFont(this.font);
                this.offG.setColor(this.tcolor);
                this.offG.drawString(this.text, 15, 30);
                ++this.xpos;
                this.repaint();
            }
            catch (Exception ex) {}
        }
        while (this.xpos > 0) {
            try {
                Thread.sleep(10L);
                this.offG.clearRect(0, 0, this.size().width, this.size().height);
                this.offG.setClip(this.xpos, 0, 50, this.size().height);
                this.offG.setColor(this.lcolor);
                this.offG.fillOval(this.xpos, 1, 50, this.size().height - 2);
                this.offG.setFont(this.font);
                this.offG.setColor(this.tcolor);
                this.offG.drawString(this.text, 15, 30);
                --this.xpos;
                this.repaint();
            }
            catch (Exception ex2) {}
        }
    }
    
    public boolean mouseDown(final Event evt, final int x, final int y) {
        try {
            this.url = new URL("http://geocities.com/zmei666/applets.htm");
            final AppletContext context = this.getAppletContext();
            context.showDocument(this.url, "_blank");
        }
        catch (MalformedURLException ex) {}
        return true;
    }
    
    public boolean mouseMove(final Event evt, final int x, final int y) {
        this.showStatus("Applet from Plamen Gelev-Unregistered");
        return true;
    }
}
