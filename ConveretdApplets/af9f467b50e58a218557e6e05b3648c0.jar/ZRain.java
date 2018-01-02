import java.net.MalformedURLException;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.net.URL;
import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ZRain extends Applet implements Runnable
{
    Image offI;
    Image image;
    Graphics offG;
    Thread mythread;
    Color color;
    Font font;
    int xpos;
    int ypos;
    int ys;
    int dir;
    String col;
    String text;
    String f;
    String u;
    String i;
    StringTokenizer st;
    URL url;
    MediaTracker mt;
    
    public void init() {
        this.offI = this.createImage(this.size().width, this.size().height);
        this.offG = this.offI.getGraphics();
        this.f = this.getParameter("font");
        this.font = new Font("TimesRoman", 1, Integer.parseInt(this.f));
        this.offG.setFont(this.font);
        this.i = this.getParameter("backgroundimage");
        this.image = this.getImage(this.getCodeBase(), this.i);
        this.ys = 23;
        this.col = this.getParameter("textcolor");
        this.st = new StringTokenizer(this.col, ";");
        this.color = new Color(Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()));
        this.text = this.getParameter("text");
        this.u = this.getParameter("link");
        (this.mt = new MediaTracker(this)).addImage(this.image, 0);
    }
    
    public void paint(final Graphics graphics) {
        if (this.mt.checkAll(true)) {
            this.offG.setColor(Color.black);
            graphics.drawImage(this.offI, 0, 0, this);
        }
        else {
            graphics.drawString("Loading image...", 40, this.size().height / 2);
        }
    }
    
    public void start() {
        (this.mythread = new Thread(this)).start();
    }
    
    public void run() {
        while (true) {
            this.change();
        }
    }
    
    public void stop() {
        this.mythread.stop();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void change() {
    Label_0003:
        while (true) {
            break Label_0003;
            while (true) {
                try {
                    while (true) {
                        Thread.sleep(20L);
                        this.offG.clearRect(0, 0, this.size().width, this.size().height);
                        this.offG.drawImage(this.image, 0, 0, this.size().width, this.size().height, this);
                        if (this.dir == 0) {
                            ++this.ys;
                        }
                        if (this.ys > this.size().height - 2) {
                            this.dir = 1;
                        }
                        if (this.dir == 1) {
                            --this.ys;
                        }
                        if (this.ys < 23) {
                            this.dir = 0;
                        }
                        this.offG.setColor(this.color);
                        this.offG.drawString(this.text, 40, this.ys);
                        for (int i = 0; i < 200; ++i) {
                            this.xpos = (int)(Math.random() * this.size().width - 5.0);
                            this.ypos = (int)(Math.random() * this.size().height - 5.0);
                            this.offG.setColor(Color.gray);
                            this.offG.drawLine(this.xpos, this.ypos, this.xpos + 5, this.ypos + 5);
                        }
                        this.repaint();
                    }
                }
                catch (InterruptedException ex) {
                    continue;
                }
                continue Label_0003;
            }
            break;
        }
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.showStatus("Unregisered version-Applet from Plamen Gelev");
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.setCursor(new Cursor(12));
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.setCursor(new Cursor(0));
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        try {
            this.url = new URL(this.u);
            this.getAppletContext().showDocument(this.url);
        }
        catch (MalformedURLException ex) {}
        return true;
    }
}
