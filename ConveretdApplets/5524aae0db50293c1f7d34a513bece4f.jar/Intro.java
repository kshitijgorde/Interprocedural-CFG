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
import java.util.Random;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Intro extends Applet implements Runnable
{
    Thread Faden;
    Image offscreenImage;
    Graphics offscreenGraphics;
    MediaTracker tracker;
    Image[] Bild;
    int AppletW;
    int AppletH;
    int[] Pixel;
    int[] PixelXA;
    int[] PixelYA;
    int[] PixelXE;
    int[] PixelYE;
    int[] PixelRA;
    int[] PixelGA;
    int[] PixelBA;
    int[] PixelRE;
    int[] PixelGE;
    int[] PixelBE;
    int[] PixelX;
    int[] PixelY;
    int[] PixelR;
    int[] PixelG;
    int[] PixelB;
    int PixelNA;
    int PixelNE;
    int PixelN;
    Random rnd;
    boolean ready;
    int BildI;
    int BGColor_R;
    int BGColor_G;
    int BGColor_B;
    int TColor_R;
    int TColor_G;
    int TColor_B;
    Color LoadingBGColor;
    Color LoadingFGColor;
    boolean mousePressed;
    int mouseX;
    int mouseY;
    int nSteps;
    int nPixelMax;
    char RandomOrder;
    int BildN;
    String BildName;
    String BildExtension;
    int WaitTime;
    
    public Intro() {
        this.PixelNA = 0;
        this.PixelNE = 0;
        this.ready = false;
        this.BildI = 0;
        this.mousePressed = false;
        this.mouseX = 0;
        this.mouseY = 0;
        this.WaitTime = 2000;
    }
    
    public void init() {
        this.nPixelMax = Integer.parseInt(this.getParameter("NumberOfPixelsMax"));
        this.BGColor_R = Integer.parseInt(this.getParameter("BackgroundRed"));
        this.BGColor_G = Integer.parseInt(this.getParameter("BackgroundGreen"));
        this.BGColor_B = Integer.parseInt(this.getParameter("BackgroundBlue"));
        this.TColor_R = Integer.parseInt(this.getParameter("TransparentRed"));
        this.TColor_G = Integer.parseInt(this.getParameter("TransparentGreen"));
        this.TColor_B = Integer.parseInt(this.getParameter("TransparentBlue"));
        this.LoadingBGColor = new Color(Integer.parseInt(this.getParameter("LoadBGRed")), Integer.parseInt(this.getParameter("LoadBGGreen")), Integer.parseInt(this.getParameter("LoadBGBlue")));
        this.LoadingFGColor = new Color(Integer.parseInt(this.getParameter("LoadFGRed")), Integer.parseInt(this.getParameter("LoadFGGreen")), Integer.parseInt(this.getParameter("LoadFGBlue")));
        this.RandomOrder = this.getParameter("RandomOrder").charAt(0);
        this.BildN = Integer.parseInt(this.getParameter("NumberOfImages"));
        this.BildName = this.getParameter("ImagesFilename");
        this.BildExtension = this.getParameter("ImagesExtension");
        this.nSteps = Integer.parseInt(this.getParameter("NumberOfSteps"));
        this.WaitTime = Integer.parseInt(this.getParameter("Delay"));
        this.AppletW = this.size().width;
        this.AppletH = this.size().height;
        this.Pixel = new int[this.AppletW * this.AppletH];
        this.PixelXA = new int[this.nPixelMax];
        this.PixelYA = new int[this.nPixelMax];
        this.PixelXE = new int[this.nPixelMax];
        this.PixelYE = new int[this.nPixelMax];
        this.PixelRA = new int[this.nPixelMax];
        this.PixelGA = new int[this.nPixelMax];
        this.PixelBA = new int[this.nPixelMax];
        this.PixelRE = new int[this.nPixelMax];
        this.PixelGE = new int[this.nPixelMax];
        this.PixelBE = new int[this.nPixelMax];
        this.PixelX = new int[this.nPixelMax];
        this.PixelY = new int[this.nPixelMax];
        this.PixelR = new int[this.AppletW * this.AppletH];
        this.PixelG = new int[this.AppletW * this.AppletH];
        this.PixelB = new int[this.AppletW * this.AppletH];
        this.rnd = new Random();
        this.Bild = new Image[this.BildN];
        this.tracker = new MediaTracker(this);
        for (int i = 0; i < this.BildN; ++i) {
            this.Bild[i] = this.getImage(this.getDocumentBase(), this.BildName + (i + 1) + this.BildExtension);
            this.tracker.addImage(this.Bild[i], i);
            this.tracker.checkID(i, true);
        }
        this.addMouseListener(new MouseEventHandler());
        this.addMouseMotionListener(new MouseMotionEventHandler());
    }
    
    public void paint(final Graphics graphics) {
        if (this.ready) {
            this.offscreenGraphics.setColor(new Color(this.BGColor_R, this.BGColor_G, this.BGColor_B));
            this.offscreenGraphics.fillRect(0, 0, this.AppletW, this.AppletH);
            for (int i = this.PixelN - 1; i >= 0; --i) {
                this.offscreenGraphics.setColor(new Color(this.PixelR[i], this.PixelG[i], this.PixelB[i]));
                this.offscreenGraphics.drawLine(this.PixelX[i], this.PixelY[i], this.PixelX[i], this.PixelY[i]);
            }
            this.offscreenGraphics.setColor(Color.black);
            this.offscreenGraphics.fillRect(0, 0, 85, 18);
            if (this.mouseY < 18 && this.mouseX < 85) {
                this.offscreenGraphics.setColor(Color.white);
            }
            else {
                this.offscreenGraphics.setColor(new Color(200, 200, 200));
            }
            this.offscreenGraphics.drawRect(0, 0, 85, 18);
            this.offscreenGraphics.drawString("www.eigelb.at", 3, 13);
            graphics.drawImage(this.offscreenImage, 0, 0, this);
        }
        else {
            graphics.setColor(this.LoadingBGColor);
            graphics.fillRect(0, 0, this.AppletW, this.AppletH);
            graphics.setColor(this.LoadingFGColor);
            graphics.drawString("Loading images...", 20, 30);
        }
    }
    
    public void run() {
        final ColorModel rgBdefault = ColorModel.getRGBdefault();
        this.offscreenImage = this.createImage(this.AppletW, this.AppletH);
        this.offscreenGraphics = this.offscreenImage.getGraphics();
        while (!this.tracker.checkAll()) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
        }
        final PixelGrabber pixelGrabber = new PixelGrabber(this.Bild[0], 0, 0, this.AppletW, this.AppletH, this.Pixel, 0, this.AppletW);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex2) {
            System.err.println("interrupted waiting for pixels!");
            return;
        }
        for (int i = 0; i < this.AppletW * this.AppletH; ++i) {
            this.PixelR[i] = rgBdefault.getRed(this.Pixel[i]);
            this.PixelG[i] = rgBdefault.getGreen(this.Pixel[i]);
            this.PixelB[i] = rgBdefault.getBlue(this.Pixel[i]);
        }
        this.PixelNE = 0;
        for (int j = 0; j < this.AppletW * this.AppletH; ++j) {
            if (this.PixelR[j] != this.TColor_R || this.PixelG[j] != this.TColor_G || this.PixelB[j] != this.TColor_B) {
                this.PixelXE[this.PixelNE] = j % this.AppletW;
                this.PixelYE[this.PixelNE] = j / this.AppletW;
                this.PixelRE[this.PixelNE] = this.PixelR[j];
                this.PixelGE[this.PixelNE] = this.PixelG[j];
                this.PixelBE[this.PixelNE] = this.PixelB[j];
                ++this.PixelNE;
            }
        }
        this.PixelNA = this.PixelNE;
        this.PixelN = this.PixelNA;
        for (int k = 0; k < this.PixelNA; ++k) {
            this.PixelXA[k] = (int)(this.AppletW * this.rnd.nextDouble());
            this.PixelYA[k] = (int)(this.AppletH * this.rnd.nextDouble());
            this.PixelRA[k] = this.BGColor_R;
            this.PixelGA[k] = this.BGColor_G;
            this.PixelBA[k] = this.BGColor_B;
        }
        this.ready = true;
        while (true) {
            for (int l = 0; l <= this.nSteps; ++l) {
                final double n = -0.5 * (Math.cos(3.141592653589793 * (1.0 * l / this.nSteps)) - 1.0);
                final double n2 = 1.0 - n;
                for (int n3 = 0; n3 < this.PixelN; ++n3) {
                    this.PixelX[n3] = (int)(n2 * this.PixelXA[n3] + n * this.PixelXE[n3]);
                    this.PixelY[n3] = (int)(n2 * this.PixelYA[n3] + n * this.PixelYE[n3]);
                    this.PixelR[n3] = (int)(n2 * this.PixelRA[n3] + n * this.PixelRE[n3]);
                    this.PixelG[n3] = (int)(n2 * this.PixelGA[n3] + n * this.PixelGE[n3]);
                    this.PixelB[n3] = (int)(n2 * this.PixelBA[n3] + n * this.PixelBE[n3]);
                }
                this.repaint();
                try {
                    Thread.sleep(20L);
                }
                catch (InterruptedException ex3) {}
            }
            for (int n4 = 0; n4 < this.WaitTime; ++n4) {
                this.repaint();
                try {
                    Thread.sleep(20L);
                }
                catch (InterruptedException ex4) {}
            }
            for (int n5 = 0; n5 < this.PixelNE; ++n5) {
                this.PixelXA[n5] = this.PixelXE[n5];
                this.PixelYA[n5] = this.PixelYE[n5];
                this.PixelRA[n5] = this.PixelRE[n5];
                this.PixelGA[n5] = this.PixelGE[n5];
                this.PixelBA[n5] = this.PixelBE[n5];
            }
            this.PixelNA = this.PixelNE;
            if (this.RandomOrder == 'y') {
                int bildI;
                for (bildI = (int)(this.BildN * this.rnd.nextDouble()); bildI == this.BildI; bildI = (int)(this.BildN * this.rnd.nextDouble())) {}
                this.BildI = bildI;
            }
            else {
                ++this.BildI;
                this.BildI %= this.BildN;
            }
            final PixelGrabber pixelGrabber2 = new PixelGrabber(this.Bild[this.BildI], 0, 0, this.AppletW, this.AppletH, this.Pixel, 0, this.AppletW);
            try {
                pixelGrabber2.grabPixels();
            }
            catch (InterruptedException ex5) {
                System.err.println("interrupted waiting for pixels!");
                return;
            }
            for (int n6 = 0; n6 < this.AppletW * this.AppletH; ++n6) {
                this.PixelR[n6] = rgBdefault.getRed(this.Pixel[n6]);
                this.PixelG[n6] = rgBdefault.getGreen(this.Pixel[n6]);
                this.PixelB[n6] = rgBdefault.getBlue(this.Pixel[n6]);
            }
            this.PixelNE = 0;
            for (int n7 = 0; n7 < this.AppletW * this.AppletH; ++n7) {
                if (this.PixelR[n7] != this.TColor_R || this.PixelG[n7] != this.TColor_G || this.PixelB[n7] != this.TColor_B) {
                    this.PixelXE[this.PixelNE] = n7 % this.AppletW;
                    this.PixelYE[this.PixelNE] = n7 / this.AppletW;
                    this.PixelRE[this.PixelNE] = this.PixelR[n7];
                    this.PixelGE[this.PixelNE] = this.PixelG[n7];
                    this.PixelBE[this.PixelNE] = this.PixelB[n7];
                    ++this.PixelNE;
                }
            }
            if (this.PixelNE > this.PixelNA) {
                for (int pixelNA = this.PixelNA; pixelNA < this.PixelNE; ++pixelNA) {
                    this.PixelXA[pixelNA] = (int)(this.AppletW * this.rnd.nextDouble());
                    this.PixelYA[pixelNA] = (int)(this.AppletH * this.rnd.nextDouble());
                    this.PixelRA[pixelNA] = this.BGColor_R;
                    this.PixelGA[pixelNA] = this.BGColor_G;
                    this.PixelBA[pixelNA] = this.BGColor_B;
                }
                this.PixelN = this.PixelNE;
            }
            else {
                for (int pixelNE = this.PixelNE; pixelNE < this.PixelNA; ++pixelNE) {
                    this.PixelXE[pixelNE] = (int)(this.AppletW * this.rnd.nextDouble());
                    this.PixelYE[pixelNE] = (int)(this.AppletH * this.rnd.nextDouble());
                    this.PixelRE[pixelNE] = this.BGColor_R;
                    this.PixelGE[pixelNE] = this.BGColor_G;
                    this.PixelBE[pixelNE] = this.BGColor_B;
                }
                this.PixelN = this.PixelNA;
            }
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
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    class MouseEventHandler extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            Intro.this.mouseX = mouseEvent.getX();
            Intro.this.mouseY = mouseEvent.getY();
            Intro.this.mousePressed = true;
            if (Intro.this.mouseY < 18 && Intro.this.mouseX < 85) {
                Intro.this.mousePressed = false;
                try {
                    Intro.this.getAppletContext().showDocument(new URL("http://www.eigelb.at"), "_blank");
                }
                catch (MalformedURLException ex) {}
            }
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            Intro.this.mousePressed = false;
        }
    }
    
    class MouseMotionEventHandler extends MouseMotionAdapter
    {
        public void mouseMoved(final MouseEvent mouseEvent) {
            Intro.this.mouseX = mouseEvent.getX();
            Intro.this.mouseY = mouseEvent.getY();
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            Intro.this.mouseX = mouseEvent.getX();
            Intro.this.mouseY = mouseEvent.getY();
        }
    }
}
