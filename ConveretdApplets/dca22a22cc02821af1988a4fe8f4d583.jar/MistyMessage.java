import java.awt.Cursor;
import java.awt.event.MouseMotionAdapter;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.PixelGrabber;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MistyMessage extends Applet implements Runnable
{
    Thread Faden;
    Image img;
    Graphics gfx;
    MemoryImageSource mis;
    byte[] reds;
    byte[] greens;
    byte[] blues;
    IndexColorModel icm;
    byte[] buffer;
    boolean[][] fontMatrix;
    int fontW;
    int fontH;
    boolean hover;
    String[] Text;
    int nTextsMax;
    int nTexts;
    int delay_appear;
    int delay_wait;
    int delay_dissappear;
    int delay_waitblank;
    boolean looptext;
    int Laufweite;
    int AppletW;
    int AppletH;
    boolean busy;
    int mouseX;
    int mouseY;
    boolean mousePressed;
    
    public MistyMessage() {
        this.reds = new byte[256];
        this.greens = new byte[256];
        this.blues = new byte[256];
        this.fontW = 9;
        this.fontH = 9;
        this.hover = false;
        this.nTextsMax = 10;
        this.Laufweite = 25;
        this.busy = true;
    }
    
    public void init() {
        this.AppletW = this.size().width;
        this.AppletH = this.size().height;
        this.gfx = this.getGraphics();
        this.buffer = new byte[this.AppletW * this.AppletH];
        final String parameter = this.getParameter("colorscheme");
        int n;
        int n2;
        int n3;
        if (parameter.equals("orange")) {
            n = 5;
            n2 = 3;
            n3 = 1;
        }
        else if (parameter.equals("green")) {
            n = 2;
            n2 = 4;
            n3 = 2;
        }
        else if (parameter.equals("blue")) {
            n = 2;
            n2 = 3;
            n3 = 5;
        }
        else {
            n = 2;
            n2 = 2;
            n3 = 2;
        }
        this.delay_appear = Integer.parseInt(this.getParameter("delay_appear"));
        this.delay_wait = Integer.parseInt(this.getParameter("delay_wait"));
        this.delay_dissappear = Integer.parseInt(this.getParameter("delay_dissappear"));
        this.delay_waitblank = Integer.parseInt(this.getParameter("delay_waitblank"));
        if (this.getParameter("looptext").equals("yes")) {
            this.looptext = true;
        }
        else {
            this.looptext = false;
        }
        this.Text = new String[10];
        for (int i = 0; i < this.nTextsMax; ++i) {
            final String parameter2 = this.getParameter("Text" + (i + 1));
            if (parameter2 != null) {
                this.Text[this.nTexts] = parameter2;
                ++this.nTexts;
            }
        }
        for (int j = 0; j < 256; ++j) {
            int n4 = n * j;
            if (n4 > 255) {
                n4 = 255;
            }
            this.reds[j] = (byte)n4;
            int n5 = n2 * j;
            if (n5 > 255) {
                n5 = 255;
            }
            this.greens[j] = (byte)n5;
            int n6 = n3 * j;
            if (n6 > 255) {
                n6 = 255;
            }
            this.blues[j] = (byte)n6;
        }
        this.icm = new IndexColorModel(8, 256, this.reds, this.greens, this.blues);
        this.fontMatrix = new boolean[256 * this.fontW][this.fontH];
        (this.mis = new MemoryImageSource(this.AppletW, this.AppletH, this.icm, this.buffer, 0, this.AppletW)).setAnimated(true);
        this.img = this.createImage(this.mis);
        System.out.println("w=" + this.AppletW + ", h=" + this.AppletH);
        for (int k = 0; k < this.AppletW; ++k) {
            for (int l = 0; l < this.AppletH; ++l) {
                this.buffer[l * this.AppletW + k] = 0;
            }
        }
        this.gettaFont();
        this.addMouseListener(new MouseEventHandler());
        this.addMouseMotionListener(new MouseMotionEventHandler());
    }
    
    public void run() {
        do {
            for (int i = 0; i < this.nTexts; ++i) {
                this.drawChars("www.eigelb.at", 10, 10, (byte)80);
                for (double n = 0.0; n < 1.0; n += 0.005) {
                    System.out.println("t=" + n);
                    final int n2 = (int)(6.0 + 70.0 * ((Math.cos(n * 3.141592653589793) + 1.0) / 2.0) * ((Math.cos(n * 3.141592653589793) + 1.0) / 2.0));
                    int n3 = (this.AppletW - this.Text[i].length() * this.Laufweite) / 2;
                    final int n4 = 110;
                    for (int j = 0; j < this.Text[i].length(); ++j) {
                        this.drawChar(this.Text[i].charAt(j), n3 + (int)(n2 * (Math.random() - 0.5)), n4 + (int)(n2 * (Math.random() - 0.5)), 4 + (int)(0.1 * n2 * Math.random()));
                        n3 += this.Laufweite;
                    }
                    this.mis.newPixels();
                    this.gfx.drawImage(this.img, 0, 0, this);
                    try {
                        Thread.sleep(this.delay_appear);
                    }
                    catch (InterruptedException ex) {}
                }
                try {
                    Thread.sleep(this.delay_wait);
                }
                catch (InterruptedException ex2) {}
                for (int k = 0; k < 80; ++k) {
                    for (int l = 0; l < this.AppletW; ++l) {
                        for (int n5 = 0; n5 < this.AppletH; ++n5) {
                            this.subPixel(l, n5);
                        }
                    }
                    this.drawChars("www.eigelb.at", 10, 10, (byte)80);
                    this.mis.newPixels();
                    this.gfx.drawImage(this.img, 0, 0, this);
                    try {
                        Thread.sleep(this.delay_dissappear);
                    }
                    catch (InterruptedException ex3) {}
                }
                if (this.delay_waitblank > 0) {
                    try {
                        Thread.sleep(this.delay_waitblank);
                    }
                    catch (InterruptedException ex4) {}
                }
            }
        } while (this.looptext);
        while (this.busy) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex5) {}
        }
    }
    
    public void drawChar(final char c, final int n, final int n2, final int n3) {
        final int n4 = this.fontW / 2;
        for (int i = 0; i < this.fontW; ++i) {
            for (int j = 0; j < this.fontH; ++j) {
                if (this.fontMatrix[this.fontW * c + i][j]) {
                    for (int k = 0; k < n3; ++k) {
                        for (int l = 0; l < n3; ++l) {
                            this.drawPixel(n + (i - n4) * n3 + k, n2 + (j - n4) * n3 + l);
                        }
                    }
                }
            }
        }
    }
    
    public void drawChar2(final char c, final int n, final int n2, final byte b) {
        final int n3 = this.fontW / 2;
        for (int i = 0; i < this.fontW; ++i) {
            for (int j = 0; j < this.fontH; ++j) {
                if (this.fontMatrix[this.fontW * c + i][j]) {
                    this.drawPixel2(n + i - n3, n2 + j - n3, b);
                }
            }
        }
    }
    
    public void drawChars(final String s, final int n, final int n2, final byte b) {
        for (int i = 0; i < s.length(); ++i) {
            this.drawChar2(s.charAt(i), n + i * this.fontW, n2, b);
        }
    }
    
    public void drawPixel(final int n, final int n2) {
        if (n >= 0 && n < this.AppletW && n2 >= 0 && n2 < this.AppletH && this.buffer[n2 * this.AppletW + n] < 255) {
            final byte[] buffer = this.buffer;
            final int n3 = n2 * this.AppletW + n;
            ++buffer[n3];
        }
    }
    
    public void drawPixel2(final int n, final int n2, final byte b) {
        if (n >= 0 && n < this.AppletW && n2 >= 0 && n2 < this.AppletH) {
            this.buffer[n2 * this.AppletW + n] = b;
        }
    }
    
    public void subPixel(final int n, final int n2) {
        if (n >= 0 && n < this.AppletW && n2 >= 0 && n2 < this.AppletH && this.buffer[n2 * this.AppletW + n] != 0) {
            final byte[] buffer = this.buffer;
            final int n3 = n2 * this.AppletW + n;
            --buffer[n3];
            if (this.buffer[n2 * this.AppletW + n] != 0) {
                final byte[] buffer2 = this.buffer;
                final int n4 = n2 * this.AppletW + n;
                --buffer2[n4];
            }
        }
    }
    
    public void gettaFont() {
        final int n = 256 * this.fontW;
        final Image image = this.createImage(n, this.fontH);
        final Graphics graphics = image.getGraphics();
        graphics.setFont(new Font("Monospaced", 0, 11));
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, n, this.AppletH);
        graphics.setColor(Color.white);
        for (int i = 0; i < 256; ++i) {
            graphics.drawString("" + (char)i, this.fontW * i, 7);
        }
        final int[] array = new int[n * this.fontH];
        final ColorModel rgBdefault = ColorModel.getRGBdefault();
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, n, this.fontH, array, 0, n);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            System.err.println("interrupted waiting for pixels!");
            return;
        }
        for (int j = 0; j < n * this.fontH; ++j) {
            if (rgBdefault.getRed(array[j]) > 128) {
                this.fontMatrix[j % n][j / n] = true;
            }
            else {
                this.fontMatrix[j % n][j / n] = false;
            }
        }
    }
    
    public void update(final Graphics graphics) {
    }
    
    public void paint(final Graphics graphics) {
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
    
    class MouseEventHandler extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            if (mouseEvent.getY() < 20 && mouseEvent.getX() < 125) {
                MistyMessage.this.mousePressed = false;
                try {
                    MistyMessage.this.getAppletContext().showDocument(new URL("http://www.eigelb.at"), "_blank");
                }
                catch (MalformedURLException ex) {}
            }
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            MistyMessage.this.mousePressed = false;
        }
    }
    
    class MouseMotionEventHandler extends MouseMotionAdapter
    {
        public void mouseMoved(final MouseEvent mouseEvent) {
            if (mouseEvent.getY() < 20 && mouseEvent.getX() < 125) {
                if (!MistyMessage.this.hover) {
                    MistyMessage.this.setCursor(new Cursor(12));
                    MistyMessage.this.hover = true;
                }
            }
            else if (MistyMessage.this.hover) {
                MistyMessage.this.setCursor(new Cursor(0));
                MistyMessage.this.hover = false;
            }
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            MistyMessage.this.mouseX = mouseEvent.getX();
            MistyMessage.this.mouseY = mouseEvent.getY();
        }
    }
}
