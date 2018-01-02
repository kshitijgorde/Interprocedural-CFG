import java.awt.event.MouseMotionAdapter;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Grappa extends Applet implements Runnable
{
    Thread Faden;
    Image offscreenImage;
    Graphics osg;
    Random rnd;
    int AppletW;
    int AppletH;
    boolean busy;
    int Zustand;
    boolean mousePressed;
    int mouseX;
    int mouseY;
    SmiSchirm_c SS;
    
    public Grappa() {
        this.rnd = new Random();
        this.busy = true;
        this.Zustand = 0;
        this.mousePressed = false;
        this.mouseX = 0;
        this.mouseY = 0;
    }
    
    public void init() {
        this.AppletW = this.size().width;
        this.AppletH = this.size().height;
        this.offscreenImage = this.createImage(this.AppletW, this.AppletH);
        this.osg = this.offscreenImage.getGraphics();
        this.Zustand = 1;
        (this.SS = new SmiSchirm_c(this.AppletW, this.AppletH)).setRealtime(true);
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
        if (this.Zustand >= 1) {
            this.osg.setColor(Color.white);
            this.osg.fillRect(10, 10, 85, 18);
            if (this.mouseY < 33 && this.mouseX < 100) {
                this.osg.setColor(Color.black);
            }
            else {
                this.osg.setColor(new Color(100, 100, 100));
            }
            this.osg.drawRect(10, 10, 85, 18);
            this.osg.drawString("www.eigelb.at", 13, 23);
            graphics.drawImage(this.offscreenImage, 0, 0, this);
        }
    }
    
    public void run() {
        final int n = 50;
        final double[] array = new double[n];
        final double[] array2 = new double[n];
        final double[] array3 = new double[n];
        final double[] array4 = new double[n];
        final double[] array5 = new double[n];
        final double[] array6 = new double[n];
        final int[] array7 = new int[n];
        final int[] array8 = new int[n];
        final int[] array9 = new int[n];
        for (int i = 0; i < n; ++i) {
            array2[i] = (array[i] = 0.0);
            array4[i] = (array3[i] = 0.0);
            array5[i] = (this.rnd.nextDouble() + this.rnd.nextDouble() + this.rnd.nextDouble() + this.rnd.nextDouble()) / 4.0;
            array6[i] = (this.rnd.nextDouble() + this.rnd.nextDouble() + this.rnd.nextDouble() + this.rnd.nextDouble()) / 4.0;
            array7[i] = 40 + (int)(40.0 * this.rnd.nextDouble());
            array8[i] = 100 + (int)(155.0 * this.rnd.nextDouble());
            array9[i] = 100 + (int)(155.0 * this.rnd.nextDouble());
        }
        this.SS.fill(0, 0, 0);
        final double n2 = 0.017;
        while (this.busy) {
            for (int j = 0; j < 10; ++j) {
                for (int k = 0; k < n; ++k) {
                    final double n3 = this.mouseX - array[k];
                    final double n4 = this.mouseY - array2[k];
                    final double[] array10 = array3;
                    final int n5 = k;
                    array10[n5] += n2 * n3 * array5[k];
                    final double[] array11 = array4;
                    final int n6 = k;
                    array11[n6] += n2 * n4 * array5[k];
                    final double[] array12 = array3;
                    final int n7 = k;
                    array12[n7] *= 0.98 + 0.015 * array6[k];
                    final double[] array13 = array4;
                    final int n8 = k;
                    array13[n8] *= 0.98 + 0.015 * array6[k];
                    final double[] array14 = array;
                    final int n9 = k;
                    array14[n9] += n2 * array3[k];
                    final double[] array15 = array2;
                    final int n10 = k;
                    array15[n10] += n2 * array4[k];
                    this.SS.drawPixel(array[k], array2[k], array7[k], array8[k], array9[k], 0.1);
                }
            }
            if (this.mousePressed) {
                this.mousePressed = false;
                this.SS.fill(0, 0, 0);
                this.SS.update();
                this.repaint();
            }
            this.repaint();
            try {
                Thread.sleep(20L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    class SmiSchirm_c
    {
        protected int[][] Rot;
        protected int[][] Gruen;
        protected int[][] Blau;
        protected int w;
        protected int h;
        protected int x;
        protected int y;
        boolean realtime;
        double fatLineQuality;
        
        public SmiSchirm_c(final int w, final int h) {
            this.realtime = false;
            this.fatLineQuality = 1.0;
            this.w = w;
            this.h = h;
            this.Rot = new int[this.w][this.h];
            this.Gruen = new int[this.w][this.h];
            this.Blau = new int[this.w][this.h];
        }
        
        public void fill(final int n, final int n2, final int n3) {
            Grappa.this.osg.setColor(new Color(n, n2, n3));
            Grappa.this.osg.fillRect(0, 0, Grappa.this.AppletW, Grappa.this.AppletH);
            this.y = 0;
            while (this.y < this.h) {
                this.x = 0;
                while (this.x < this.w) {
                    this.Rot[this.x][this.y] = n;
                    this.Gruen[this.x][this.y] = n2;
                    this.Blau[this.x][this.y] = n3;
                    ++this.x;
                }
                ++this.y;
            }
        }
        
        public void update() {
            this.y = 0;
            while (this.y < this.h) {
                this.x = 0;
                while (this.x < this.w) {
                    Grappa.this.osg.setColor(new Color(this.Rot[this.x][this.y] / 256, this.Gruen[this.x][this.y] / 256, this.Blau[this.x][this.y] / 256));
                    Grappa.this.osg.drawLine(this.x, this.y, this.x, this.y);
                    ++this.x;
                }
                ++this.y;
            }
        }
        
        public void setRealtime(final boolean realtime) {
            this.realtime = realtime;
        }
        
        public void drawPixel(final double n, final double n2, final int n3, final int n4, final int n5, final double n6) {
            if (n > 0.0 && n < Grappa.this.AppletW - 1 && n2 > 0.0 && n2 < Grappa.this.AppletH - 1) {
                final int n7 = (int)n;
                final int n8 = n7 + 1;
                final int n9 = (int)n2;
                final int n10 = n9 + 1;
                final double n11 = n8 - n;
                final double n12 = 1.0 - n11;
                final double n13 = n10 - n2;
                final double n14 = 1.0 - n13;
                final double n15 = 256.0 * n11 * n13 * n6;
                this.Rot[n7][n9] += (int)(n15 * n3);
                if (this.Rot[n7][n9] > 65535) {
                    this.Rot[n7][n9] = 65535;
                }
                this.Gruen[n7][n9] += (int)(n15 * n4);
                if (this.Gruen[n7][n9] > 65535) {
                    this.Gruen[n7][n9] = 65535;
                }
                this.Blau[n7][n9] += (int)(n15 * n5);
                if (this.Blau[n7][n9] > 65535) {
                    this.Blau[n7][n9] = 65535;
                }
                final double n16 = 256.0 * n12 * n13 * n6;
                this.Rot[n8][n9] += (int)(n16 * n3);
                if (this.Rot[n8][n9] > 65535) {
                    this.Rot[n8][n9] = 65535;
                }
                this.Gruen[n8][n9] += (int)(n16 * n4);
                if (this.Gruen[n8][n9] > 65535) {
                    this.Gruen[n8][n9] = 65535;
                }
                this.Blau[n8][n9] += (int)(n16 * n5);
                if (this.Blau[n8][n9] > 65535) {
                    this.Blau[n8][n9] = 65535;
                }
                final double n17 = 256.0 * n11 * n14 * n6;
                this.Rot[n7][n10] += (int)(n17 * n3);
                if (this.Rot[n7][n10] > 65535) {
                    this.Rot[n7][n10] = 65535;
                }
                this.Gruen[n7][n10] += (int)(n17 * n4);
                if (this.Gruen[n7][n10] > 65535) {
                    this.Gruen[n7][n10] = 65535;
                }
                this.Blau[n7][n10] += (int)(n17 * n5);
                if (this.Blau[n7][n10] > 65535) {
                    this.Blau[n7][n10] = 65535;
                }
                final double n18 = 256.0 * n12 * n14 * n6;
                this.Rot[n8][n10] += (int)(n18 * n3);
                if (this.Rot[n8][n10] > 65535) {
                    this.Rot[n8][n10] = 65535;
                }
                this.Gruen[n8][n10] += (int)(n18 * n4);
                if (this.Gruen[n8][n10] > 65535) {
                    this.Gruen[n8][n10] = 65535;
                }
                this.Blau[n8][n10] += (int)(n18 * n5);
                if (this.Blau[n8][n10] > 65535) {
                    this.Blau[n8][n10] = 65535;
                }
                if (this.realtime) {
                    Grappa.this.osg.setColor(new Color(this.Rot[n7][n9] / 256, this.Gruen[n7][n9] / 256, this.Blau[n7][n9] / 256));
                    Grappa.this.osg.drawLine(n7, n9, n7, n9);
                    Grappa.this.osg.setColor(new Color(this.Rot[n8][n9] / 256, this.Gruen[n8][n9] / 256, this.Blau[n8][n9] / 256));
                    Grappa.this.osg.drawLine(n8, n9, n8, n9);
                    Grappa.this.osg.setColor(new Color(this.Rot[n7][n10] / 256, this.Gruen[n7][n10] / 256, this.Blau[n7][n10] / 256));
                    Grappa.this.osg.drawLine(n7, n10, n7, n10);
                    Grappa.this.osg.setColor(new Color(this.Rot[n8][n10] / 256, this.Gruen[n8][n10] / 256, this.Blau[n8][n10] / 256));
                    Grappa.this.osg.drawLine(n8, n10, n8, n10);
                }
            }
        }
    }
    
    class MouseEventHandler extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            Grappa.this.mouseX = mouseEvent.getX();
            Grappa.this.mouseY = mouseEvent.getY();
            Grappa.this.mousePressed = true;
            if (Grappa.this.mouseY < 33 && Grappa.this.mouseX < 100) {
                Grappa.this.mousePressed = false;
                try {
                    Grappa.this.getAppletContext().showDocument(new URL("http://www.eigelb.at"), "_blank");
                }
                catch (MalformedURLException ex) {}
            }
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            Grappa.this.mousePressed = false;
        }
    }
    
    class MouseMotionEventHandler extends MouseMotionAdapter
    {
        public void mouseMoved(final MouseEvent mouseEvent) {
            Grappa.this.mouseX = mouseEvent.getX();
            Grappa.this.mouseY = mouseEvent.getY();
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            Grappa.this.mouseX = mouseEvent.getX();
            Grappa.this.mouseY = mouseEvent.getY();
        }
    }
}
