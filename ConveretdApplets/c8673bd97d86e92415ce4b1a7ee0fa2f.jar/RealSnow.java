import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class RealSnow extends Applet implements Runnable
{
    private Thread animThread;
    private Graphics buffG;
    private Image buff;
    private Image bgimage;
    private Dimension dim;
    private boolean running;
    private int delay;
    private Wind[] winds;
    private Snow[] flakes;
    private int numberOfFlakes;
    private int numberOfWinds;
    private int nsbug;
    
    public RealSnow() {
        this.delay = 40;
        this.numberOfFlakes = 500;
        this.numberOfWinds = 0;
    }
    
    public void destroy() {
        System.out.println("Destroy called, bye bye");
        this.running = false;
        this.animThread = null;
    }
    
    private void dodgeFlakes() {
        for (int i = 0; i < this.getNumberOfWinds(); ++i) {
            final Wind wind = this.winds[i];
            for (int j = 0; j < this.getNumberOfFlakes(); ++j) {
                if (this.flakes[j].getDepth() == wind.getDepth() && this.flakes[j].isAlive() && wind.isAlive() && wind.getXco() < this.flakes[j].getXco() && wind.getXco() + wind.getWidth() > this.flakes[j].getXco() && wind.getYco() < this.flakes[j].getYco() && wind.getYco() + wind.getHeight() > this.flakes[j].getYco()) {
                    this.flakes[j].dodge(wind.getSign() * wind.getStrength());
                }
            }
        }
    }
    
    private int getNumberOfFlakes() {
        return this.numberOfFlakes;
    }
    
    private int getNumberOfWinds() {
        return this.numberOfWinds;
    }
    
    public void init() {
        this.setBackground(Color.black);
        this.dim = this.getSize();
        this.buff = this.createImage(this.dim.width, this.dim.height);
        this.buffG = this.buff.getGraphics();
        final String parameter = this.getParameter("Background");
        this.numberOfFlakes = Integer.parseInt(this.getParameter("NumberOfFlakes"));
        this.numberOfWinds = Integer.parseInt(this.getParameter("Turbulence"));
        this.bgimage = this.getImage(this.getDocumentBase(), parameter);
        this.initWind();
        this.initSnow();
        System.out.println("Starting life and struggling to survive");
    }
    
    private void initSnow() {
        this.flakes = new Snow[this.getNumberOfFlakes()];
        for (int i = 0; i < this.getNumberOfFlakes(); ++i) {
            this.flakes[i] = new Snow(this.dim.width, this.dim.height);
        }
    }
    
    private void initWind() {
        this.winds = new Wind[this.getNumberOfWinds()];
        for (int i = 0; i < this.getNumberOfWinds(); ++i) {
            this.winds[i] = new Wind(this.dim.width, this.dim.height);
        }
    }
    
    private void makeFlakes() {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < this.getNumberOfFlakes(); ++j) {
                if (!this.flakes[j].isAlive()) {
                    this.flakes[j].newFlake();
                    break;
                }
            }
        }
    }
    
    private void makeWind() {
        for (int i = 0; i < this.getNumberOfWinds(); ++i) {
            if (!this.winds[i].isAlive()) {
                this.winds[i].newWind();
            }
            else {
                this.winds[i].move();
            }
        }
    }
    
    private void moveSnowFlakes() {
        for (int i = 0; i < this.getNumberOfFlakes(); ++i) {
            this.flakes[i].move();
        }
    }
    
    public void paint(final Graphics graphics) {
        this.buffG.setColor(Color.black);
        this.buffG.fillRect(0, 0, this.dim.width, this.dim.height);
        this.buffG.drawImage(this.bgimage, 0, 0, this);
        this.buffG.setColor(Color.white);
        for (int i = 0; i < this.getNumberOfFlakes(); ++i) {
            if (this.flakes[i].isAlive()) {
                this.buffG.drawRect(this.flakes[i].getXco(), this.flakes[i].getYco(), this.flakes[i].getSize(), this.flakes[i].getSize());
            }
        }
        graphics.drawImage(this.buff, 0, 0, this);
    }
    
    public void run() {
        while (this.running) {
            try {
                ++this.nsbug;
                this.makeFlakes();
                this.makeWind();
                this.dodgeFlakes();
                this.moveSnowFlakes();
                this.repaint();
                Thread.sleep(this.delay);
            }
            catch (Exception ex) {
                System.out.println("Thread Mesg: " + ex);
            }
        }
    }
    
    public void start() {
        System.out.println("Started");
        this.nsbug = 0;
        if (this.animThread == null) {
            this.running = true;
            (this.animThread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.nsbug < 30) {
            System.out.println("Early kill by NS, ignoring (" + this.nsbug + ")");
        }
        else {
            System.out.println("Stop called, ending (" + this.nsbug + ")");
            this.running = false;
            this.animThread = null;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
