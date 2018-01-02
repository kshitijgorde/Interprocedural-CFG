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

public class Rain extends Applet implements Runnable
{
    private Thread m_snow;
    private Image offscreenImage;
    private String m_back;
    private Image backdrop;
    private Graphics offscreenGraphics;
    private Image frame;
    private int xSize;
    private int ySize;
    private int[] colors;
    private int amount;
    private Color[] cl;
    private String[] cNames;
    private int delay;
    private int angle;
    
    public String getAppletInfo() {
        return "Name: Confetti\r\nAuthor: amIbleeding.com\r\nCreated with Microsoft Visual J++ Version 1.1";
    }
    
    public void init() {
        this.colors = new int[5];
        this.amount = 0;
        for (int t = 0; t < 5; ++t) {
            final String param = this.getParameter(this.cNames[t]);
            if (param != null) {
                this.colors[t] = Integer.parseInt(param);
            }
        }
        for (int t2 = 0; t2 < 5; ++t2) {
            final int tmp = this.colors[t2];
            final int[] colors = this.colors;
            final int n = t2;
            colors[n] += this.amount;
            this.amount += tmp;
        }
        String param = this.getParameter("back");
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
        param = this.getParameter("angle");
        if (param != null) {
            this.angle = Integer.parseInt(param);
        }
        else {
            this.angle = 0;
        }
        this.xSize = this.size().width;
        this.ySize = this.size().height;
        this.backdrop = this.getImage(this.getCodeBase(), this.m_back);
        this.offscreenImage = this.createImage(this.xSize, this.ySize);
        this.offscreenGraphics = this.offscreenImage.getGraphics();
        System.out.println("Confetti (C)2000 amIbleeding.com");
    }
    
    public void newimage() {
        this.offscreenGraphics.drawImage(this.backdrop, 0, 0, this.xSize, this.ySize, null);
        this.offscreenGraphics.setColor(Color.white);
        int onCl = 0;
        for (int i = 0; i < this.amount; ++i) {
            while (i >= this.colors[onCl]) {
                ++onCl;
                this.offscreenGraphics.setColor(this.cl[onCl]);
            }
            final int x1 = (int)(Math.random() * this.xSize);
            final int y1 = (int)(Math.random() * this.ySize);
            this.offscreenGraphics.drawLine(x1, y1, x1 + 2 * this.angle, y1 + 4);
        }
        if (this.frame != null) {
            this.offscreenGraphics.drawImage(this.frame, 0, 0, this.xSize, this.ySize, null);
        }
    }
    
    public void destroy() {
        this.offscreenGraphics.dispose();
    }
    
    public void paint(final Graphics g) {
        g.drawImage(this.offscreenImage, 0, 0, this);
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void start() {
        if (this.m_snow == null) {
            (this.m_snow = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.m_snow != null) {
            this.m_snow.stop();
            this.m_snow = null;
        }
    }
    
    public void run() {
        final MediaTracker mt = new MediaTracker(this);
        mt.addImage(this.backdrop, 0);
        if (this.frame != null) {
            mt.addImage(this.frame, 0);
        }
    Label_0045:
        while (true) {
            try {
                mt.waitForAll();
                break Label_0045;
            }
            catch (Exception ex) {
                break Label_0045;
            }
            break Label_0045;
            while (true) {
                try {
                    while (true) {
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
                }
                catch (InterruptedException ex2) {
                    this.stop();
                    continue;
                }
                continue Label_0045;
            }
            break;
        }
    }
    
    public Rain() {
        this.m_back = "none";
        this.cl = new Color[] { Color.white, Color.lightGray, Color.gray, Color.darkGray, Color.black };
        this.cNames = new String[] { "white", "lightGray", "gray", "darkGray", "black" };
    }
}
