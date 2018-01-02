import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseAdapter;
import java.awt.image.ImageObserver;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class LineEffects extends Applet implements Runnable
{
    Thread Faden;
    Image offscreenImage;
    Graphics offscreenGraphics;
    int width;
    int height;
    boolean mousePressed;
    int mouseX;
    int mouseY;
    Color FarbeBG;
    double[] xR;
    int[] yM;
    int nR;
    int nothingDoCount;
    int nothingDoLimit;
    int x0S;
    int y0S;
    int dxS;
    int dyS;
    int nxS;
    int nyS;
    Color FarbeStriche;
    int xrT;
    int yrT;
    Color FarbeText;
    double t;
    
    public LineEffects() {
        this.mousePressed = false;
        this.mouseX = 0;
        this.mouseY = 0;
        this.FarbeBG = new Color(240, 240, 240);
        this.nR = 34;
        this.nothingDoCount = 0;
        this.nothingDoLimit = 50;
        this.x0S = 80;
        this.y0S = 60;
        this.dxS = 10;
        this.dyS = 10;
        this.nxS = 36;
        this.nyS = 36;
        this.FarbeStriche = new Color(0, 0, 0);
        this.xrT = -30;
        this.yrT = 5;
        this.FarbeText = new Color(80, 80, 80);
        this.t = 0.0;
    }
    
    public void init() {
        this.xR = new double[this.nR];
        this.yM = new int[this.nR];
        this.width = this.size().width;
        this.height = this.size().height;
        this.addMouseListener(new MouseEventHandler());
        this.addMouseMotionListener(new MouseMotionEventHandler());
        for (int i = 0; i < this.nR; ++i) {
            this.xR[i] = 0.0;
            this.yM[i] = this.height - 1;
        }
        this.repaint();
    }
    
    public void start() {
        if (this.Faden == null) {
            (this.Faden = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.Faden != null) {
            this.Faden.stop();
            this.Faden = null;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.offscreenImage == null) {
            this.offscreenImage = this.createImage(this.size().width, this.size().height);
            this.offscreenGraphics = this.offscreenImage.getGraphics();
        }
        this.offscreenGraphics.setColor(this.FarbeBG);
        this.offscreenGraphics.fillRect(0, 0, this.width, this.height);
        if (this.nothingDoCount < this.nothingDoLimit) {
            final double n = 1.0 - 1.0 * this.nothingDoCount / this.nothingDoLimit;
            this.offscreenGraphics.setColor(new Color(240 - (int)(60.0 * n), 240 - (int)(30.0 * n), 240));
            for (int i = 0; i < this.nR; ++i) {
                this.offscreenGraphics.fillRect((int)(1.0 * i / this.nR * this.width), this.yM[i], this.width / this.nR + 1, this.height - this.yM[i]);
            }
        }
        this.offscreenGraphics.setColor(new Color(150, 150, 150));
        this.offscreenGraphics.drawString("line effects - move pointer to change parameters ", 40, this.height - 24);
        this.offscreenGraphics.drawString("more applets on www.students.fh-vorarlberg.ac.at/im99x06", 40, this.height - 8);
        this.offscreenGraphics.setColor(this.FarbeStriche);
        for (int j = 0; j < this.nxS; ++j) {
            for (int k = 0; k < this.nyS; ++k) {
                final double n2 = this.x0S + j * this.dxS + 20.0 * this.xR[0] * Math.cos(this.xR[1] * this.t + this.xR[2] * j + this.xR[3] * k) + 20.0 * this.xR[4] * Math.cos(this.xR[5] * this.t + this.xR[6] * j + this.xR[7] * k);
                final double n3 = this.y0S + k * this.dyS + 20.0 * this.xR[8] * Math.cos(this.xR[9] * this.t + this.xR[10] * j + this.xR[11] * k) + 20.0 * this.xR[12] * Math.cos(this.xR[13] * this.t + this.xR[14] * j + this.xR[15] * k);
                final double n4 = 60.0 * this.xR[16] * Math.cos(this.xR[17] * this.t + this.xR[18] * j + this.xR[19] * k) + 60.0 * this.xR[20] * Math.cos(this.xR[21] * this.t + this.xR[22] * j + this.xR[23] * k);
                final double n5 = this.xR[24] * this.t + this.xR[25] * Math.cos(this.xR[26] * this.t + this.xR[27] * j + this.xR[28] * k) + this.xR[29] * Math.cos(this.xR[30] * this.t + this.xR[31] * j + this.xR[32] * k);
                this.offscreenGraphics.drawLine((int)n2, (int)n3, (int)(n2 + Math.cos(n5) * n4), (int)(n3 - Math.sin(n5) * n4));
            }
        }
        graphics.drawImage(this.offscreenImage, 0, 0, this);
    }
    
    public void run() {
        while (true) {
            this.t += 0.3 * this.xR[33];
            final int n = (int)(1.0 * this.mouseX / this.width * this.nR);
            this.xR[n] = Math.pow(10.0, (1.0 * this.height - this.mouseY) / this.height * 3.0 - 2.0);
            this.yM[n] = this.mouseY;
            ++this.nothingDoCount;
            this.repaint();
            try {
                Thread.sleep(20L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    class MouseEventHandler extends MouseAdapter
    {
    }
    
    class MouseMotionEventHandler extends MouseMotionAdapter
    {
        public void mouseMoved(final MouseEvent mouseEvent) {
            LineEffects.this.nothingDoCount = 0;
            LineEffects.this.mouseX = mouseEvent.getX();
            LineEffects.this.mouseY = mouseEvent.getY();
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            LineEffects.this.nothingDoCount = 0;
            LineEffects.this.mouseX = mouseEvent.getX();
            LineEffects.this.mouseY = mouseEvent.getY();
        }
    }
}
