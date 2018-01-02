import java.awt.Cursor;
import java.awt.event.MouseMotionAdapter;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Glitzer3 extends Applet implements Runnable
{
    Thread Faden;
    Image offscreenImage;
    Graphics osg;
    Image BGImage;
    MediaTracker tracker;
    int AppletW;
    int AppletH;
    int BildW;
    int BildH;
    boolean busy;
    int Zustand;
    boolean Auszeit;
    boolean mousePressed;
    boolean hover;
    int mouseX;
    int mouseY;
    int mouseX2;
    int mouseY2;
    int mouseXOld;
    int mouseYOld;
    double t;
    double dT;
    double[] x;
    double[] y;
    double[] vX;
    double[] vY;
    double[] P;
    double[] w;
    boolean[] active;
    int[] type;
    Color[] Farbe;
    int nFarben;
    int iFarbe;
    int nPartikel;
    boolean Mausdynamik;
    double Streuung;
    double Ageing;
    int Frequency;
    double Gravity;
    String imageFilename;
    double Brightness;
    
    public Glitzer3() {
        this.busy = true;
        this.Zustand = 0;
        this.Auszeit = false;
        this.mousePressed = false;
        this.hover = false;
        this.mouseX = 0;
        this.mouseY = 0;
        this.mouseX2 = 0;
        this.mouseY2 = 0;
        this.mouseXOld = 0;
        this.mouseYOld = 0;
        this.t = 0.0;
        this.dT = 0.05;
        this.nFarben = 256;
        this.iFarbe = 0;
        this.nPartikel = 2000;
        this.Mausdynamik = true;
        this.Streuung = 35.0;
        this.Ageing = 0.97;
        this.Frequency = 20;
        this.Gravity = 1.0;
    }
    
    public void init() {
        this.AppletW = this.size().width;
        this.AppletH = this.size().height;
        this.nPartikel = Integer.parseInt(this.getParameter("nPartikel"));
        this.Streuung = Integer.parseInt(this.getParameter("Streuung"));
        if (this.getParameter("Mausdynamik").equals("y")) {
            this.Mausdynamik = true;
        }
        else {
            this.Mausdynamik = false;
        }
        this.Ageing = 1.0 - 0.001 * Integer.parseInt(this.getParameter("Ageing"));
        this.Frequency = Integer.parseInt(this.getParameter("Frequency"));
        this.Gravity = 0.1 * Integer.parseInt(this.getParameter("Gravity"));
        this.imageFilename = this.getParameter("BGImage");
        if (this.imageFilename.equals("NONE") || this.imageFilename.equals("none")) {
            this.imageFilename = null;
        }
        this.Brightness = 0.03 * Integer.parseInt(this.getParameter("Brightness"));
        this.x = new double[this.nPartikel];
        this.y = new double[this.nPartikel];
        this.vX = new double[this.nPartikel];
        this.vY = new double[this.nPartikel];
        this.w = new double[this.nPartikel];
        this.P = new double[this.nPartikel];
        this.active = new boolean[this.nPartikel];
        this.type = new int[this.nPartikel];
        for (int i = 0; i < this.nPartikel; ++i) {
            this.active[i] = false;
        }
        this.Farbe = new Color[this.nFarben];
        for (int j = 0; j < this.nFarben; ++j) {
            int n = j;
            if (n > 255) {
                n = 255;
            }
            int n2 = (int)(2.0 * j);
            if (n2 > 255) {
                n2 = 255;
            }
            int n3 = (int)(4.0 * j);
            if (n3 > 255) {
                n3 = 255;
            }
            this.Farbe[j] = new Color(n3, n2, n);
        }
        this.addMouseListener(new MouseEventHandler());
        this.addMouseMotionListener(new MouseMotionEventHandler());
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
            this.busy = false;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.Zustand == 2) {
            if (this.BGImage == null) {
                this.osg.setColor(Color.black);
                this.osg.fillRect(0, 0, this.AppletW, this.AppletH);
            }
            else {
                this.osg.drawImage(this.BGImage, 0, 0, this);
            }
            this.osg.setColor(new Color(150, 150, 150));
            this.osg.drawString("more applets on this link:", 10, this.AppletH - 25);
            if (this.mouseY > this.AppletH - 24 && this.mouseX < 120) {
                this.osg.setColor(Color.white);
            }
            this.osg.drawString("http://www.eigelb.at", 10, this.AppletH - 10);
            if (!this.Auszeit) {
                for (int i = 0; i < this.nPartikel; ++i) {
                    if (this.active[i]) {
                        final double n = this.P[i] * (1.5 + Math.sin(this.t * this.w[i]));
                        final int n2 = (int)this.x[i];
                        final int n3 = (int)this.y[i];
                        if (n > 1.5) {
                            final int n4 = (int)(n - 0.5);
                            this.iFarbe = (int)(40.0 * n);
                            if (this.iFarbe >= this.nFarben) {
                                this.iFarbe = this.nFarben - 1;
                            }
                            this.osg.setColor(this.Farbe[this.iFarbe]);
                            if (this.type[i] == 0) {
                                this.osg.fillRect(n2 - n4, n3, 2 * n4, 1);
                                this.osg.fillRect(n2, n3 - n4, 1, 2 * n4);
                            }
                            else {
                                this.osg.drawLine(n2 - n4, n3 - n4, n2 + n4, n3 + n4);
                                this.osg.drawLine(n2 + n4, n3 - n4, n2 - n4, n3 + n4);
                            }
                        }
                        this.iFarbe = (int)(100.0 * n);
                        if (this.iFarbe >= this.nFarben) {
                            this.iFarbe = this.nFarben - 1;
                        }
                        this.osg.setColor(this.Farbe[this.iFarbe]);
                        this.osg.fillRect(n2, n3, 1, 1);
                    }
                }
            }
            graphics.drawImage(this.offscreenImage, 0, 0, this);
        }
        else if (this.Zustand == 1) {
            graphics.drawImage(this.offscreenImage, 0, 0, this);
        }
    }
    
    public void run() {
        this.offscreenImage = this.createImage(this.AppletW, this.AppletH);
        this.osg = this.offscreenImage.getGraphics();
        this.Zustand = 1;
        if (this.imageFilename != null) {
            this.tracker = new MediaTracker(this);
            this.BGImage = this.getImage(this.getDocumentBase(), this.imageFilename);
            this.tracker.addImage(this.BGImage, 0);
            this.tracker.checkID(0, true);
            this.osg.setColor(Color.black);
            this.osg.fillRect(0, 0, this.AppletW, this.AppletH);
            this.osg.setColor(Color.white);
            this.osg.drawString("loading image...", 10, 25);
            this.repaint();
            while (!this.tracker.checkAll()) {
                try {
                    Thread.sleep(50L);
                }
                catch (InterruptedException ex) {}
            }
        }
        this.Zustand = 2;
        while (this.busy) {
            this.t += this.dT;
            int n = 0;
            if (!this.Auszeit || this.mousePressed) {
                this.Auszeit = true;
                for (int i = 0; i < this.nPartikel; ++i) {
                    if (n < this.Frequency && !this.active[i] && this.mousePressed) {
                        final double n2 = 1.0 * n / this.Frequency;
                        this.x[i] = this.mouseXOld + (int)(n2 * (this.mouseX - this.mouseXOld)) + (int)(0.1 * this.Streuung * (Math.random() - 0.5));
                        this.y[i] = this.mouseYOld + (int)(n2 * (this.mouseY - this.mouseYOld)) + (int)(0.1 * this.Streuung * (Math.random() - 0.5));
                        this.vX[i] = this.Streuung * (Math.random() - 0.5);
                        this.vY[i] = this.Streuung * (Math.random() - 0.5);
                        if (this.Mausdynamik) {
                            final double[] vx = this.vX;
                            final int n3 = i;
                            vx[n3] += 3 * (this.mouseX - this.mouseXOld);
                            final double[] vy = this.vY;
                            final int n4 = i;
                            vy[n4] += 3 * (this.mouseY - this.mouseYOld);
                        }
                        this.P[i] = this.Brightness * (0.25 + Math.random());
                        this.w[i] = 15.0 * Math.random();
                        this.type[i] = (int)(1.5 * Math.random());
                        this.active[i] = true;
                        ++n;
                        this.Auszeit = false;
                    }
                    else if (this.active[i]) {
                        this.Auszeit = false;
                        final double[] vy2 = this.vY;
                        final int n5 = i;
                        vy2[n5] += this.Gravity;
                        final double[] vx2 = this.vX;
                        final int n6 = i;
                        vx2[n6] *= 0.97;
                        final double[] vy3 = this.vY;
                        final int n7 = i;
                        vy3[n7] *= 0.97;
                        final double[] x = this.x;
                        final int n8 = i;
                        x[n8] += this.dT * this.vX[i];
                        final double[] y = this.y;
                        final int n9 = i;
                        y[n9] += this.dT * this.vY[i];
                        final double[] p = this.P;
                        final int n10 = i;
                        p[n10] *= this.Ageing;
                        if (this.y[i] > this.AppletH || this.x[i] < 0.0 || this.x[i] > this.AppletW || this.P[i] < 0.4) {
                            this.active[i] = false;
                        }
                    }
                }
            }
            this.mouseXOld = this.mouseX;
            this.mouseYOld = this.mouseY;
            this.mouseX = this.mouseX2;
            this.mouseY = this.mouseY2;
            this.repaint();
            try {
                Thread.sleep(20L);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    class MouseEventHandler extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            Glitzer3.this.mousePressed = true;
            if (Glitzer3.this.mouseY2 > Glitzer3.this.AppletH - 24 && Glitzer3.this.mouseX2 < 120) {
                Glitzer3.this.mousePressed = false;
                try {
                    Glitzer3.this.getAppletContext().showDocument(new URL("http://www.eigelb.at"), "_blank");
                }
                catch (MalformedURLException ex) {}
            }
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            Glitzer3.this.mousePressed = false;
        }
    }
    
    class MouseMotionEventHandler extends MouseMotionAdapter
    {
        public void mouseMoved(final MouseEvent mouseEvent) {
            this.mouseDragged(mouseEvent);
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            Glitzer3.this.mouseX2 = mouseEvent.getX();
            Glitzer3.this.mouseY2 = mouseEvent.getY();
            if (Glitzer3.this.mouseY2 > Glitzer3.this.AppletH - 24 && Glitzer3.this.mouseX2 < 120) {
                if (!Glitzer3.this.hover) {
                    Glitzer3.this.setCursor(new Cursor(12));
                    Glitzer3.this.hover = true;
                }
            }
            else if (Glitzer3.this.hover) {
                Glitzer3.this.setCursor(new Cursor(0));
                Glitzer3.this.hover = false;
            }
        }
    }
}
