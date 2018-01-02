import java.awt.event.MouseMotionAdapter;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
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

public class TypoToolMotion11 extends Applet implements Runnable
{
    Thread Faden;
    Image offscreenImage;
    Graphics offscreenGraphics;
    int Anz;
    int xm;
    int ym;
    double[] xPos;
    double[] yPos;
    double[] vX;
    double[] vY;
    char[] Zeichen;
    Color[] Farbe;
    double t;
    double dt;
    String Satz;
    int width;
    int height;
    boolean mousePressed;
    int mouseX;
    int mouseY;
    double red;
    double green;
    double blue;
    int redi;
    int greeni;
    int bluei;
    
    public TypoToolMotion11() {
        this.Anz = 1024;
        this.t = 0.0;
        this.dt = 1.5;
        this.mousePressed = false;
        this.mouseX = 0;
        this.mouseY = 0;
    }
    
    public void init() {
        this.Anz = Integer.parseInt(this.getParameter("nChars"));
        this.dt = 0.1 * Integer.parseInt(this.getParameter("speed"));
        this.Satz = this.getParameter("text");
        this.redi = Integer.parseInt(this.getParameter("red"));
        this.red = 0.01 * this.redi;
        this.greeni = Integer.parseInt(this.getParameter("green"));
        this.green = 0.01 * this.greeni;
        this.bluei = Integer.parseInt(this.getParameter("blue"));
        this.blue = 0.01 * this.bluei;
        this.xPos = new double[this.Anz];
        this.yPos = new double[this.Anz];
        this.vX = new double[this.Anz];
        this.vY = new double[this.Anz];
        this.Zeichen = new char[this.Anz];
        this.Farbe = new Color[this.Anz];
        this.width = this.size().width;
        this.height = this.size().height;
        this.addMouseListener(new MouseEventHandler());
        this.addMouseMotionListener(new MouseMotionEventHandler());
        for (int i = 0; i < this.Anz; ++i) {
            this.Zeichen[i] = (char)(65 + (int)(Math.random() * 60.0));
            this.xPos[i] = (int)(this.width * Math.random());
            this.yPos[i] = (int)(this.height * Math.random());
            this.vX[i] = 0.0;
            this.vY[i] = 0.0;
        }
        this.xm = (int)(0.5 * this.width);
        this.ym = (int)(0.5 * this.height);
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
        this.offscreenGraphics.setColor(Color.black);
        this.offscreenGraphics.fillRect(0, 0, this.width, this.height);
        for (int i = 0; i < this.Anz; ++i) {
            this.offscreenGraphics.setColor(this.Farbe[i]);
            this.offscreenGraphics.drawString("" + this.Zeichen[i], (int)this.xPos[i], (int)this.yPos[i]);
        }
        this.offscreenGraphics.setColor(new Color(200, 200, 200));
        this.offscreenGraphics.drawString(this.Satz, 10, 20);
        this.offscreenGraphics.setColor(new Color(200, 200, 200));
        this.offscreenGraphics.drawString("more applets on this link:", 10, this.height - 24);
        if (this.mouseY > this.height - 22 && this.mouseY < this.height - 7 && this.mouseX < 100) {
            this.offscreenGraphics.setColor(Color.white);
        }
        this.offscreenGraphics.drawString("www.eigelb.at", 10, this.height - 10);
        graphics.drawImage(this.offscreenImage, 0, 0, this);
    }
    
    public void run() {
        while (true) {
            this.t += this.dt;
            for (int i = 0; i < this.Anz; ++i) {
                if (this.mousePressed) {
                    final double atan2 = Math.atan2(this.yPos[i] - this.mouseY, this.xPos[i] - this.mouseX);
                    final double cos = Math.cos(4.0 * atan2);
                    final double[] vx = this.vX;
                    final int n = i;
                    vx[n] += cos * Math.cos(atan2);
                    final double[] vy = this.vY;
                    final int n2 = i;
                    vy[n2] += cos * Math.sin(atan2);
                }
                final double[] vx2 = this.vX;
                final int n3 = i;
                vx2[n3] *= 0.9;
                final double[] vy2 = this.vY;
                final int n4 = i;
                vy2[n4] *= 0.9;
                final double[] xPos = this.xPos;
                final int n5 = i;
                xPos[n5] += this.dt * this.vX[i];
                final double[] yPos = this.yPos;
                final int n6 = i;
                yPos[n6] += this.dt * this.vY[i];
                if (this.xPos[i] > this.width) {
                    final double[] xPos2 = this.xPos;
                    final int n7 = i;
                    xPos2[n7] -= this.width;
                }
                if (this.xPos[i] < 0.0) {
                    final double[] xPos3 = this.xPos;
                    final int n8 = i;
                    xPos3[n8] += this.width;
                }
                if (this.yPos[i] > this.height) {
                    final double[] yPos2 = this.yPos;
                    final int n9 = i;
                    yPos2[n9] -= this.height;
                }
                if (this.yPos[i] < 0.0) {
                    final double[] yPos3 = this.yPos;
                    final int n10 = i;
                    yPos3[n10] += this.height;
                }
                int n11 = (int)(100.0 + 20.0 * Math.sqrt(this.vX[i] * this.vX[i] + this.vY[i] * this.vY[i]));
                if (n11 > 255) {
                    n11 = 255;
                }
                if (n11 < 0) {
                    n11 = 0;
                }
                int n12 = (int)(this.red * n11);
                if (n12 > 255) {
                    n12 = 255;
                }
                int n13 = (int)(this.green * n11);
                if (n13 > 255) {
                    n13 = 255;
                }
                int n14 = (int)(this.blue * n11);
                if (n14 > 255) {
                    n14 = 255;
                }
                this.Farbe[i] = new Color(n12, n13, n14);
            }
            this.repaint();
            try {
                Thread.sleep(5L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    class MouseEventHandler extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            TypoToolMotion11.this.mouseX = mouseEvent.getX();
            TypoToolMotion11.this.mouseY = mouseEvent.getY();
            TypoToolMotion11.this.mousePressed = true;
            if (TypoToolMotion11.this.mouseY > TypoToolMotion11.this.height - 22 && TypoToolMotion11.this.mouseY < TypoToolMotion11.this.height - 7 && TypoToolMotion11.this.mouseX < 100) {
                TypoToolMotion11.this.mousePressed = false;
                try {
                    TypoToolMotion11.this.getAppletContext().showDocument(new URL("http://www.eigelb.at"), "_blank");
                }
                catch (MalformedURLException ex) {}
            }
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            TypoToolMotion11.this.mousePressed = false;
        }
    }
    
    class MouseMotionEventHandler extends MouseMotionAdapter
    {
        public void mouseDragged(final MouseEvent mouseEvent) {
            TypoToolMotion11.this.mouseX = mouseEvent.getX();
            TypoToolMotion11.this.mouseY = mouseEvent.getY();
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
            TypoToolMotion11.this.mouseX = mouseEvent.getX();
            TypoToolMotion11.this.mouseY = mouseEvent.getY();
        }
    }
}
