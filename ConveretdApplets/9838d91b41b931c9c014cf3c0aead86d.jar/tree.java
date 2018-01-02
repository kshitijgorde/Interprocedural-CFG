import java.awt.Event;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.MediaTracker;
import java.applet.AudioClip;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class tree extends Applet implements Runnable
{
    Image tree;
    Image screen;
    boolean music;
    Image snow;
    FontMetrics fm;
    Font myFont;
    int fontsize;
    AudioClip sound;
    int msg;
    MediaTracker track;
    Thread test;
    String file;
    String[] message;
    int height;
    int width;
    int[] x;
    int[] y;
    int temp;
    double fly;
    int l;
    int i;
    boolean backdrawn;
    
    public tree() {
        this.music = true;
        this.msg = 0;
        this.file = "tree.gif";
        this.message = new String[10];
        this.x = new int[100];
        this.y = new int[100];
        this.temp = 0;
        this.backdrawn = false;
    }
    
    public void background() {
        this.l = 0;
        while (this.l < 30) {
            this.x[this.l] = (int)(Math.random() * 100.0) + 20;
            this.y[this.l] = (int)(Math.random() * 100.0);
            ++this.l;
        }
        if (this.music) {
            this.sound.loop();
        }
        final Graphics graphics = this.screen.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this.width, this.height);
        graphics.drawImage(this.tree, 0, 0, this);
        this.backdrawn = true;
        this.update();
    }
    
    public void init() {
        this.track = new MediaTracker(this);
        this.tree = this.getImage(this.getDocumentBase(), this.file);
        this.track.addImage(this.tree, 0);
        this.snow = this.getImage(this.getDocumentBase(), "snow.gif");
        this.track.addImage(this.snow, 1);
        this.sound = this.getAudioClip(this.getDocumentBase(), "sleigh.au");
        this.setBackground(Color.black);
        if (this.screen == null) {
            this.screen = this.createImage(this.size().width, this.size().height);
        }
        this.width = this.size().width;
        this.height = this.size().height;
        this.message[0] = "Happy Holidays!";
        this.message[1] = "From JavaBoutique";
        this.message[2] = "Click Image to stop sounds!!";
        this.message[3] = "Happy Solstice!";
        this.message[4] = "Send us your feedback!!";
        this.myFont = new Font("Arial", 1, 10);
        this.fm = this.getFontMetrics(this.myFont);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.music) {
            this.music = true;
            this.sound.loop();
            return true;
        }
        if (this.music) {
            this.music = false;
            this.sound.stop();
            return true;
        }
        return true;
    }
    
    public void run() {
        try {
            this.track.waitForAll();
        }
        catch (InterruptedException ex) {}
        while (true) {
            if (!this.backdrawn) {
                this.background();
            }
            if (this.backdrawn) {
                this.snow();
            }
            this.update();
            try {
                Thread.sleep(120L);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void snow() {
        ++this.temp;
        this.l = 0;
        while (this.l < 30) {
            final int[] y = this.y;
            final int l = this.l;
            y[l] += 9;
            if (this.y[this.l] > 100) {
                this.y[this.l] = 0;
            }
            ++this.l;
        }
        if (this.temp > 5) {
            ++this.msg;
            if (this.msg > 4) {
                this.msg = 0;
            }
            this.temp = 0;
        }
    }
    
    public synchronized void start() {
        if (this.test == null) {
            (this.test = new Thread(this)).start();
        }
    }
    
    public synchronized void stop() {
        if (this.test != null) {
            this.sound.stop();
            this.test.stop();
            this.test = null;
        }
    }
    
    public void update() {
        final Graphics graphics = this.screen.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this.width, this.height);
        graphics.setColor(Color.yellow);
        graphics.drawImage(this.tree, this.width / 2 - 35, 0, this);
        this.fontsize = this.fm.stringWidth(this.message[this.msg]);
        graphics.setFont(this.myFont);
        graphics.drawString(this.message[this.msg], this.width / 2 - this.fontsize / 2, this.height - 5);
        this.l = 0;
        while (this.l < 30) {
            graphics.drawImage(this.snow, this.x[this.l], this.y[this.l], this);
            ++this.l;
        }
        final Graphics graphics2 = this.getGraphics();
        graphics2.drawImage(this.screen, 0, 0, this);
        graphics2.dispose();
    }
}
