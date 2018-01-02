import java.awt.event.MouseMotionAdapter;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.PixelGrabber;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Smif extends Applet implements Runnable
{
    Thread Faden;
    Image offscreenImage;
    Graphics osg;
    MediaTracker tracker;
    int AppletW;
    int AppletH;
    boolean busy;
    int mouseX;
    int mouseY;
    boolean mousePressed;
    int Zustand;
    Smif_c Smif_o;
    int delay;
    int lossyness;
    int nSquaresMax;
    double noise;
    double linesNumber;
    double linesInten;
    boolean lines;
    boolean filled;
    boolean overwrite;
    Color clearColor;
    String image;
    
    public Smif() {
        this.busy = true;
        this.Zustand = 0;
    }
    
    public void init() {
        this.AppletW = this.size().width;
        this.AppletH = this.size().height;
        this.clearColor = new Color(Integer.parseInt(this.getParameter("clearColor").substring(0, 2), 16), Integer.parseInt(this.getParameter("clearColor").substring(2, 4), 16), Integer.parseInt(this.getParameter("clearColor").substring(4, 6), 16));
        this.delay = Integer.parseInt(this.getParameter("delay"));
        this.noise = 0.01 * Integer.parseInt(this.getParameter("noise"));
        if (this.getParameter("lines").equals("y")) {
            this.lines = true;
        }
        else {
            this.lines = false;
        }
        this.linesNumber = 0.01 * Integer.parseInt(this.getParameter("linesNumber"));
        this.linesInten = 0.1 * Integer.parseInt(this.getParameter("linesInten"));
        if (this.getParameter("filled").equals("y")) {
            this.filled = true;
        }
        else {
            this.filled = false;
        }
        if (this.getParameter("overwrite").equals("y")) {
            this.overwrite = true;
        }
        else {
            this.overwrite = false;
        }
        this.lossyness = Integer.parseInt(this.getParameter("lossyness"));
        this.nSquaresMax = Integer.parseInt(this.getParameter("nSquaresMax"));
        this.image = this.getParameter("image");
        this.tracker = new MediaTracker(this);
        this.offscreenImage = this.createImage(this.AppletW, this.AppletH);
        (this.osg = this.offscreenImage.getGraphics()).setColor(Color.white);
        this.osg.fillRect(0, 0, this.AppletW, this.AppletH);
        (this.Smif_o = new Smif_c()).init(this.AppletW, this.AppletH);
        this.Smif_o.loadImage(this.image);
        this.Zustand = 1;
        this.addMouseListener(new MouseEventHandler());
        this.addMouseMotionListener(new MouseMotionEventHandler());
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
        this.Smif_o.greyscale = false;
        this.Smif_o.lossy = true;
        this.Smif_o.lossynessMax = this.lossyness;
        this.Smif_o.encode(false);
        while (this.busy) {
            this.Smif_o.animate();
            this.repaint();
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    void showError() {
        while (true) {
            this.osg.setColor(Color.black);
            this.osg.fillRect(0, 0, this.AppletW, this.AppletH);
            this.osg.setColor(Color.white);
            this.osg.drawString("Not enough Squares!", 20, 30);
            this.osg.drawString("Increase the nSquareMax-parameter!", 20, 50);
            this.repaint();
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
        }
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
    
    class Smif_c
    {
        int w;
        int h;
        int[] Pixel;
        short[][] PixelR;
        short[][] PixelG;
        short[][] PixelB;
        Image Bild;
        int byteCounter;
        int nSubdivisions;
        int nAreas;
        boolean greyscale;
        boolean lossy;
        int lossynessMax;
        int lossyness;
        int[] rX;
        int[] rY;
        int[] rW;
        int[] rH;
        Color[] rColor;
        int rNMax;
        int rN;
        
        Smif_c() {
            this.byteCounter = 0;
            this.nSubdivisions = 0;
            this.nAreas = 0;
            this.greyscale = false;
            this.lossy = false;
            this.rNMax = Smif.this.nSquaresMax;
            this.rN = 0;
        }
        
        void Smif_c() {
        }
        
        void init(final int w, final int h) {
            this.w = w;
            this.h = h;
            this.Pixel = new int[this.w * this.h];
            this.PixelR = new short[this.w][this.h];
            this.PixelG = new short[this.w][this.h];
            this.PixelB = new short[this.w][this.h];
            this.rX = new int[this.rNMax];
            this.rY = new int[this.rNMax];
            this.rW = new int[this.rNMax];
            this.rH = new int[this.rNMax];
            this.rColor = new Color[this.rNMax];
        }
        
        void animate() {
            if (!Smif.this.overwrite) {
                Smif.this.osg.setColor(Smif.this.clearColor);
                Smif.this.osg.fillRect(0, 0, Smif.this.AppletW, Smif.this.AppletH);
            }
            for (int i = 0; i < this.rN; ++i) {
                final int n = (int)((Smif.this.noise * this.rW[i] + Smif.this.noise * 6.0) * (Math.random() - 0.5));
                final int n2 = (int)((Smif.this.noise * this.rH[i] + Smif.this.noise * 6.0) * (Math.random() - 0.5));
                Smif.this.osg.setColor(this.rColor[i]);
                if (Smif.this.filled) {
                    Smif.this.osg.fillRect(this.rX[i] + n, this.rY[i] + n2, this.rW[i], this.rH[i]);
                }
                else {
                    Smif.this.osg.drawRect(this.rX[i] + n, this.rY[i] + n2, this.rW[i], this.rH[i]);
                }
                if (Smif.this.lines) {
                    if (Math.random() < Smif.this.linesNumber) {
                        final int n3 = (int)(Smif.this.linesInten * this.rW[i] * Math.random());
                        Smif.this.osg.drawLine(this.rX[i] - n3, this.rY[i] + n2, this.rX[i] + n3, this.rY[i] + n2);
                    }
                    if (Math.random() < Smif.this.linesNumber) {
                        final int n4 = (int)(Smif.this.linesInten * this.rH[i] * Math.random());
                        Smif.this.osg.drawLine(this.rX[i] + n, this.rY[i] - n4, this.rX[i] + n, this.rY[i] + n4);
                    }
                }
            }
        }
        
        void encode(final boolean b) {
            this.encodeSquare(this.rN = 0, 0, this.w, this.h, b);
            System.out.println("Bytes needed: " + this.byteCounter);
            System.out.println("Areas filled: " + this.nAreas);
            System.out.println("Subdivisions needed: " + this.nSubdivisions);
        }
        
        void encodeSquare(final int n, final int n2, final int n3, final int n4, final boolean b) {
            boolean b2 = true;
            int n5 = 0;
            int n6 = 0;
            int n7 = this.PixelR[n][n2];
            if (!this.greyscale) {
                n5 = this.PixelG[n][n2];
                n6 = this.PixelB[n][n2];
            }
            if (n3 > 1) {
                for (int i = n; i < n + n3; ++i) {
                    for (int j = n2; j < n2 + n4; ++j) {
                        if (this.greyscale) {
                            if (this.PixelR[i][j] != n7) {
                                b2 = false;
                                i = n + n3;
                                j = n2 + n4;
                            }
                        }
                        else if (this.PixelR[i][j] != n7 || this.PixelG[i][j] != n5 || this.PixelB[i][j] != n6) {
                            b2 = false;
                            i = n + n3;
                            j = n2 + n4;
                        }
                    }
                }
                if (this.lossy) {
                    short n8 = 0;
                    short n9 = 0;
                    short n10 = 0;
                    for (int k = n; k < n + n3; ++k) {
                        for (int l = n2; l < n2 + n4; ++l) {
                            n8 += this.PixelR[k][l];
                            n9 += this.PixelG[k][l];
                            n10 += this.PixelB[k][l];
                        }
                    }
                    n7 = n8 / (n3 * n4);
                    n5 = n9 / (n3 * n4);
                    n6 = n10 / (n3 * n4);
                    if (n7 < 0) {
                        n7 = 0;
                    }
                    if (n7 > 255) {
                        n7 = 255;
                    }
                    if (n5 < 0) {
                        n5 = 0;
                    }
                    if (n5 > 255) {
                        n5 = 255;
                    }
                    if (n6 < 0) {
                        n6 = 0;
                    }
                    if (n6 > 255) {
                        n6 = 255;
                    }
                    this.lossyness = 0;
                    for (int n11 = n; n11 < n + n3; ++n11) {
                        for (int n12 = n2; n12 < n2 + n4; ++n12) {
                            if (this.greyscale) {
                                this.lossyness += 3 * Math.abs(this.PixelR[n11][n12] - n7);
                            }
                            else {
                                this.lossyness += Math.abs(this.PixelR[n11][n12] - n7);
                                this.lossyness += Math.abs(this.PixelG[n11][n12] - n5);
                                this.lossyness += Math.abs(this.PixelB[n11][n12] - n6);
                            }
                        }
                    }
                    this.lossyness /= 3 * n3 * n4;
                    this.lossyness *= (int)(1.0 + 3.0E-4 * n3 * n4);
                    b2 = (this.lossyness <= this.lossynessMax);
                }
            }
            else {
                b2 = true;
            }
            if (b2) {
                if (this.greyscale) {
                    ++this.byteCounter;
                }
                else {
                    this.byteCounter += 3;
                }
                ++this.nAreas;
                if (b) {
                    if (this.greyscale) {
                        Smif.this.osg.setColor(new Color(n7, n7, n7));
                    }
                    else {
                        Smif.this.osg.setColor(new Color(n7, n5, n6));
                    }
                }
                if (this.rN < this.rNMax) {
                    this.rX[this.rN] = n;
                    this.rY[this.rN] = n2;
                    this.rW[this.rN] = n3;
                    this.rH[this.rN] = n4;
                    if (this.greyscale) {
                        this.rColor[this.rN] = new Color(n7, n7, n7);
                    }
                    else {
                        this.rColor[this.rN] = new Color(n7, n5, n6);
                    }
                    ++this.rN;
                }
                else {
                    System.out.println("Fehler: Zu viele Quadrate!");
                    Smif.this.showError();
                }
                if (b) {
                    Smif.this.osg.fillRect(n, n2, n3, n4);
                    Smif.this.repaint();
                }
            }
            else if (n3 >= 2) {
                ++this.byteCounter;
                ++this.nSubdivisions;
                this.encodeSquare(n, n2, n3 / 2, n4 / 2, b);
                this.encodeSquare(n + n3 / 2, n2, n3 / 2, n4 / 2, b);
                this.encodeSquare(n, n2 + n4 / 2, n3 / 2, n4 / 2, b);
                this.encodeSquare(n + n3 / 2, n2 + n4 / 2, n3 / 2, n4 / 2, b);
            }
            else {
                System.out.println("Fehler! (Blockbreite ist " + this.w + ")");
            }
        }
        
        void drawImageByPixels() {
            for (int i = 0; i < this.w; ++i) {
                for (int j = 0; j < this.h; ++j) {
                    Smif.this.osg.setColor(new Color(this.PixelR[i][j], this.PixelG[i][j], this.PixelB[i][j]));
                    Smif.this.osg.drawLine(i, j, i, j);
                }
                Smif.this.repaint();
            }
        }
        
        void loadImage(final String s) {
            this.Bild = Smif.this.getImage(Smif.this.getDocumentBase(), s);
            Smif.this.tracker.addImage(this.Bild, 0);
            Smif.this.tracker.checkID(0, true);
            while (!Smif.this.tracker.checkAll()) {
                try {
                    final Smif this$0 = Smif.this;
                    Thread.sleep(50L);
                }
                catch (InterruptedException ex) {}
            }
            final ColorModel rgBdefault = ColorModel.getRGBdefault();
            final PixelGrabber pixelGrabber = new PixelGrabber(this.Bild, 0, 0, this.w, this.h, this.Pixel, 0, this.w);
            try {
                pixelGrabber.grabPixels();
            }
            catch (InterruptedException ex2) {
                System.err.println("interrupted waiting for pixels!");
                return;
            }
            for (int i = 0; i < this.w * this.h; ++i) {
                this.PixelR[i % this.w][i / this.w] = (short)rgBdefault.getRed(this.Pixel[i]);
                this.PixelG[i % this.w][i / this.w] = (short)rgBdefault.getGreen(this.Pixel[i]);
                this.PixelB[i % this.w][i / this.w] = (short)rgBdefault.getBlue(this.Pixel[i]);
            }
        }
    }
    
    class MouseEventHandler extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            Smif.this.mouseX = mouseEvent.getX();
            Smif.this.mouseY = mouseEvent.getY();
            Smif.this.mousePressed = true;
            if (Smif.this.mouseY < 33 && Smif.this.mouseX < 100) {
                Smif.this.mousePressed = false;
                try {
                    Smif.this.getAppletContext().showDocument(new URL("http://www.eigelb.at"), "_blank");
                }
                catch (MalformedURLException ex) {}
            }
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            Smif.this.mousePressed = false;
        }
    }
    
    class MouseMotionEventHandler extends MouseMotionAdapter
    {
        public void mouseMoved(final MouseEvent mouseEvent) {
            Smif.this.mouseX = mouseEvent.getX();
            Smif.this.mouseY = mouseEvent.getY();
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            Smif.this.mouseX = mouseEvent.getX();
            Smif.this.mouseY = mouseEvent.getY();
        }
    }
}
