import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Confetti extends Applet implements Runnable
{
    private Thread m_snow;
    private int[][] flakes;
    private Image offscreenImage;
    private String m_back;
    private Image backdrop;
    private Graphics offscreenGraphics;
    private Image frame;
    private int[] colors;
    private Color[] cl;
    private String[] cNames;
    private int xSize;
    private int ySize;
    private int pixSize;
    private int amount;
    private int delay;
    private int xMin;
    private int xMax;
    private int yMin;
    private int yMax;
    
    public void stop() {
        if (this.m_snow != null) {
            this.m_snow.stop();
            this.m_snow = null;
        }
    }
    
    public void paint(final Graphics g) {
        g.drawImage(this.offscreenImage, 0, 0, this);
    }
    
    public void destroy() {
        this.offscreenGraphics.dispose();
    }
    
    public void newimage() {
        this.offscreenGraphics.drawImage(this.backdrop, 0, 0, this.xSize, this.ySize, null);
        this.offscreenGraphics.setColor(Color.red);
        int onCl = 0;
        for (int i = 0; i < this.amount; ++i) {
            final int xDif = this.xMax - this.xMin;
            final int yDif = this.yMax - this.yMin;
            double xV = Math.random() * xDif * 2.0 - xDif;
            if (xV < 0.0) {
                xV -= this.xMin;
            }
            else {
                xV += this.xMin;
            }
            final int[] array = this.flakes[i];
            final int n = 0;
            array[n] += (int)xV;
            final int[] array2 = this.flakes[i];
            final int n2 = 1;
            array2[n2] += (int)(Math.random() * yDif) + this.yMin;
            if (this.flakes[i][0] < 0) {
                this.flakes[i][0] = this.xSize;
            }
            if (this.flakes[i][0] > this.xSize - 1) {
                this.flakes[i][0] = 0;
            }
            if (this.flakes[i][1] > this.ySize - 1) {
                this.flakes[i][1] = 0;
                this.flakes[i][0] = (int)(Math.random() * this.xSize);
            }
            while (i > this.colors[onCl]) {
                ++onCl;
                this.offscreenGraphics.setColor(this.cl[onCl]);
            }
            this.offscreenGraphics.fillRect(this.flakes[i][0], this.flakes[i][1], this.pixSize, this.pixSize);
        }
        if (this.frame != null) {
            this.offscreenGraphics.drawImage(this.frame, 0, 0, this.xSize, this.ySize, null);
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public Confetti() {
        this.m_back = "none";
        this.cl = new Color[] { Color.red, Color.green, Color.blue, Color.yellow, Color.cyan, Color.magenta, Color.orange, Color.pink, Color.white, Color.lightGray, Color.gray, Color.darkGray, Color.black };
        this.cNames = new String[] { "red", "green", "blue", "yellow", "cyan", "magenta", "orange", "pink", "white", "lightGray", "gray", "darkGray", "black" };
    }
    
    public void start() {
        if (this.m_snow == null) {
            (this.m_snow = new Thread(this)).start();
        }
    }
    
    public String getAppletInfo() {
        return "Name: Confetti\r\n" + "Author: amIbleeding.com\r\n" + "Created with Microsoft Visual J++ Version 1.1";
    }
    
    public void run() {
        final MediaTracker mt = new MediaTracker(this);
        mt.addImage(this.backdrop, 0);
        if (this.frame != null) {
            mt.addImage(this.frame, 0);
        }
        try {
            mt.waitForAll();
        }
        catch (Exception ex) {}
        while (true) {
            try {
                final long nw = System.currentTimeMillis();
                this.newimage();
                this.repaint();
                long tm = this.delay - (System.currentTimeMillis() - nw);
                if (tm < 10L) {
                    tm = 10L;
                }
                Thread.sleep(tm);
                this.newimage();
            }
            catch (InterruptedException e) {
                this.stop();
            }
        }
    }
    
    public void init() {
        this.colors = new int[13];
        this.amount = 0;
        int t = 0;
        do {
            final String param = this.getParameter(this.cNames[t]);
            if (param != null) {
                this.colors[t] = Integer.parseInt(param);
            }
        } while (++t < 13);
        String param = this.getParameter("pixSize");
        if (param != null) {
            this.pixSize = Integer.parseInt(param);
        }
        else {
            this.pixSize = 2;
        }
        t = 0;
        do {
            final int tmp = this.colors[t];
            final int[] colors = this.colors;
            final int n = t;
            colors[n] += this.amount;
            this.amount += tmp;
        } while (++t < 13);
        this.flakes = new int[this.amount][2];
        param = this.getParameter("back");
        if (param != null) {
            this.m_back = param;
        }
        param = this.getParameter("frame");
        if (param != null) {
            this.frame = this.getImage(this.getCodeBase(), param);
        }
        else {
            this.frame = null;
        }
        param = this.getParameter("delay");
        if (param != null) {
            this.delay = Integer.parseInt(param);
        }
        else {
            this.delay = 100;
        }
        param = this.getParameter("xMin");
        if (param != null) {
            this.xMin = Integer.parseInt(param);
        }
        else {
            this.xMin = 0;
        }
        param = this.getParameter("xMax");
        if (param != null) {
            this.xMax = Integer.parseInt(param);
        }
        else {
            this.delay = 5;
        }
        param = this.getParameter("yMin");
        if (param != null) {
            this.yMin = Integer.parseInt(param);
        }
        else {
            this.yMin = 0;
        }
        param = this.getParameter("yMax");
        if (param != null) {
            this.yMax = Integer.parseInt(param);
        }
        else {
            this.yMax = 5;
        }
        this.xSize = this.size().width;
        this.ySize = this.size().height;
        this.backdrop = this.getImage(this.getCodeBase(), this.m_back);
        for (int i = 0; i < this.amount; ++i) {
            this.flakes[i][0] = (int)(Math.random() * this.xSize);
            this.flakes[i][1] = (int)(Math.random() * this.ySize);
        }
        this.offscreenImage = this.createImage(this.xSize, this.ySize);
        this.offscreenGraphics = this.offscreenImage.getGraphics();
        System.out.println("Confetti (C)2000 amIbleeding.com");
    }
}
