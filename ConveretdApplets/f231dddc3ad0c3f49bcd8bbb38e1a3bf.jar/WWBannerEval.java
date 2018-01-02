import java.awt.Component;
import java.awt.Event;
import java.net.MalformedURLException;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.Image;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class WWBannerEval extends Applet implements Runnable
{
    static int BdhBlf;
    URL bDhBlf;
    URL[] BDhBlf;
    Image[] bdHBlf;
    int[] BdHBlf;
    String[] bDHBlf;
    int[] BDHBlf;
    int bdhbLf;
    int BdhbLf;
    int bDhbLf;
    int BDhbLf;
    int bdHbLf;
    int BdHbLf;
    Thread bDHbLf;
    MediaTracker BDHbLf;
    int bdhBLf;
    int BdhBLf;
    int bDhBLf;
    int BDhBLf;
    boolean bdHBLf;
    short[] BdHBLf;
    short[] bDHBLf;
    short[] BDHBLf;
    short[] bdhblF;
    short[] BdhblF;
    short[] bDhblF;
    boolean BDhblF;
    boolean bdHblF;
    boolean BdHblF;
    int bDHblF;
    int[] BDHblF;
    int bdhBlF;
    int BdhBlF;
    int bDhBlF;
    private Image Lt;
    boolean BDhBlF;
    boolean bdHBlF;
    static int BdHBlF;
    boolean bDHBlF;
    boolean BDHBlF;
    boolean bdhbLF;
    String BdhbLF;
    String bDhbLF;
    String BDhbLF;
    String bdHbLF;
    
    public void start() {
        WWBannerEval.BdhBlf = 0;
        if (this.bDHbLf == null) {
            (this.bDHbLf = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.bDHbLf != null) {
            this.bDHbLf.stop();
            this.bDHbLf = null;
        }
    }
    
    private void lt(final int[] array, final int[] array2, final int n, final int n2, final int n3) {
        final int n4 = 0;
        final int n5 = 0;
        final int n6 = n4;
        final int n7 = n - 1;
        final int n8 = n2 - 1;
        final boolean b = true;
        int i = 0;
        final int n9 = n8;
        while (i < n2) {
            for (int j = 0; j < n; ++j) {
                if (j >= n6 && j <= n7 && i >= n5 && i <= n9) {
                    int n10 = j - j % n3;
                    int n11 = i - i % n3;
                    if (n10 < 0) {
                        n10 = 0;
                    }
                    if (n11 < 0) {
                        n11 = 0;
                    }
                    final int n12 = (array[n11 * n + n10] & 0xFF0000) >> 16;
                    if (!b) {
                        array2[i * n + j] = this.zzzh(n12, n12, n12);
                    }
                    else {
                        array2[i * n + j] = this.zzzh(n12, (array[n11 * n + n10] & 0xFF00) >> 8, array[n11 * n + n10] & 0xFF);
                    }
                }
                else {
                    array2[i * n + j] = array[i * n + j];
                }
            }
            ++i;
        }
    }
    
    public int zzzh(int n, int n2, int n3) {
        if (n < 0) {
            n = 0;
        }
        if (n > 255) {
            n = 255;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        if (n2 > 255) {
            n2 = 255;
        }
        if (n3 < 0) {
            n3 = 0;
        }
        if (n3 > 255) {
            n3 = 255;
        }
        return new Color(n, n2, n3).getRGB();
    }
    
    public void update(final Graphics graphics) {
        graphics.clipRect(this.BdhbLf, this.BDhbLf, this.bdhbLf, this.bDhbLf);
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.bdHBlF && this.Lt != null) {
            graphics.drawImage(this.Lt, 0, 0, this.BdhBLf, this.bdhBLf, this);
            return;
        }
        if (this.BDHbLf.checkID(this.bdHbLf)) {
            graphics.drawImage(this.bdHBlf[this.bdHbLf], 0, 0, this.BdhBLf, this.bdhBLf, this);
            if (this.bdHBLf) {
                graphics.drawString("http://www.wyka-warzecha.com", 10, 10);
            }
            return;
        }
        if (this.bdHbLf == 0) {
            graphics.drawString("Loading images -- Please wait...", 10, 10);
            graphics.drawString("(C) 2000 Wyka-Warzecha Enterprises", 10, 25);
            graphics.drawString("http://www.wyka-warzecha.com", 10, 40);
            if (!this.bdhbLF) {
                this.bdhbLF = true;
                try {
                    Thread.sleep(2000L);
                }
                catch (InterruptedException ex) {}
            }
        }
    }
    
    void bdhblf() {
        final boolean bdhbLf = false;
        this.BDhbLf = 0;
        this.BdhbLf = (bdhbLf ? 1 : 0);
        final int bdhBLf = this.BdhBLf;
        int i = 0;
        this.bdhbLf = bdhBLf;
        while (i <= this.bdhBLf) {
            this.bDhbLf = i;
            this.repaint();
            this.bdhBlf(5);
            ++i;
        }
        this.BdHblf();
    }
    
    void Bdhblf() {
        this.BdhbLf = 0;
        this.bDhbLf = this.bdhBLf;
        this.bdhbLf = this.BdhBLf;
        for (int i = this.bdhBLf; i >= 0; --i) {
            this.BDhbLf = i;
            this.repaint();
            this.bdhBlf(5);
        }
        this.BdHblf();
    }
    
    void bDhblf() {
        this.BdhbLf = 0;
        final int bdhBLf = this.bdhBLf;
        this.BDhbLf = 0;
        int i = 0;
        this.bDhbLf = bdhBLf;
        while (i <= this.BdhBLf) {
            this.bdhbLf = i;
            this.repaint();
            this.bdhBlf(5);
            i += 4;
        }
        this.BdHblf();
    }
    
    void BDhblf() {
        this.bdhbLf = this.BdhBLf;
        final int bdhBLf = this.bdhBLf;
        this.BDhbLf = 0;
        this.bDhbLf = bdhBLf;
        for (int i = this.BdhBLf; i >= 0; i -= 4) {
            this.BdhbLf = i;
            this.repaint();
            this.bdhBlf(5);
        }
        this.BdHblf();
    }
    
    void bdHblf() {
        this.BdhbLf = this.BdhBLf / 2;
        this.bdhbLf = this.BdhBLf / 2;
        this.BDhbLf = this.bdhBLf / 2;
        this.bDhbLf = this.bdhBLf / 2;
        this.bDhBLf = this.BdhBLf / 40;
        final int bDhBLf = this.bdhBLf / 40;
        int i = 1;
        this.BDhBLf = bDhBLf;
        while (i <= 20) {
            this.BdhbLf -= this.bDhBLf;
            this.bdhbLf = this.bDhBLf * i * 2;
            this.BDhbLf -= this.BDhBLf;
            this.bDhbLf = this.BDhBLf * i * 2;
            this.repaint();
            this.bdhBlf(5);
            ++i;
        }
        final boolean bdhbLf = false;
        this.BDhbLf = 0;
        this.BdhbLf = (bdhbLf ? 1 : 0);
        this.bdhbLf = this.BdhBLf;
        this.bDhbLf = this.bdhBLf;
        this.repaint();
        this.BdHblf();
    }
    
    void BdHblf() {
        try {
            Thread.sleep(this.BDHBlf[this.bdHbLf]);
        }
        catch (InterruptedException ex) {
            this.showStatus("Thread sleep error!");
        }
    }
    
    void bDHblf(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {
            this.showStatus("Thread sleep error!");
        }
    }
    
    void BDHblf() {
        final int[] zzzf = this.zzzf(this.bdHBlf[this.bdHbLf], false);
        final int[] zzzf2 = this.zzzf(this.bdHBlf[this.bdHbLf], false);
        this.bdHBlF = true;
        int i = 50;
        final int[] array = zzzf2;
        while (i > 0) {
            this.lt(zzzf, array, this.BdhBLf, this.bdhBLf, i);
            this.Lt = this.createImage(new MemoryImageSource(this.BdhBLf, this.bdhBLf, array, 0, this.BdhBLf));
            this.repaint();
            --i;
        }
        this.BdHblf();
        this.bdHBlF = false;
    }
    
    public int[] zzzf(final Image image, final boolean b) {
        final int width = image.getWidth(this);
        final int height = image.getHeight(this);
        final int[] array = new int[width * height];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, array, 0, width);
        try {
            if (!pixelGrabber.grabPixels(60000L)) {
                System.err.println("Could not grab pixels from image before quantum expired.");
                return array;
            }
        }
        catch (InterruptedException ex) {
            System.err.println("Grab interrupted.");
        }
        if (b) {
            for (int i = 0; i < array.length; ++i) {
                final int[] array2 = array;
                final int n = i;
                array2[n] &= 0xFF;
            }
        }
        return array;
    }
    
    public Image zzzk(final int[] array, final int n, final int n2) {
        final int[] array2 = new int[array.length];
        int i = 0;
        final int[] array3 = array2;
        while (i < array.length) {
            final int n3 = array[i];
            array3[i] = (0xFF000000 | n3 << 16 | n3 << 8 | (array3[i] = n3));
            ++i;
        }
        return this.createImage(new MemoryImageSource(n, n2, array3, 0, n));
    }
    
    void bdhBlf(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public void run() {
        if (this.BdHbLf == 0) {
            final boolean bDhblF = true;
            this.bdHblF = true;
            this.BDhblF = bDhblF;
            this.BDHblF = new int[this.BdhBLf * this.bdhBLf];
            if (!this.getParameter("copyright").equals("<a href=http://www.wyka-warzecha.com>Wyka-Warzecha</a>")) {
                this.bdHBLf = true;
            }
            else {
                this.bdHBLf = false;
            }
            final String s = new String();
            this.bdhBLf = this.size().height;
            final int width = this.size().width;
            int i = 0;
            this.BdhBLf = width;
            while (i < 101) {
                final String string = "gif" + (i + 1);
                final String string2 = "link" + (i + 1);
                final String string3 = "wait" + (i + 1);
                final String string4 = "targetwindow" + (i + 1);
                final String string5 = "effect" + (i + 1);
                this.getParameter(string);
                this.bdHBlf[i] = this.getImage(this.getCodeBase(), this.getParameter(string));
                try {
                    this.BDhBlf[i] = new URL(this.getParameter(string2));
                    if (null != null) {}
                }
                catch (MalformedURLException ex) {
                    try {
                        this.BDhBlf[i] = new URL(this.getDocumentBase(), this.getParameter(string2));
                        if (null == null) {}
                    }
                    catch (MalformedURLException ex2) {}
                }
                final String parameter = this.getParameter(string5);
                if (parameter == null) {
                    this.BdHBlf[i] = -1;
                    if (null != null) {}
                }
                else {
                    this.BdHBlf[i] = Integer.parseInt(parameter);
                    System.out.println("Got fx" + parameter);
                }
                final String parameter2 = this.getParameter(string3);
                if (parameter2 == null) {
                    this.BDHBlf[i] = 0;
                }
                else {
                    this.BDHBlf[i] = Integer.parseInt(parameter2);
                }
                this.bDHBlf[i] = this.getParameter(string4);
                if (this.bDHBlf[i] == null) {
                    this.bDHBlf[i] = "_blank";
                }
                if (this.bdHBlf[i] != null && this.BDhBlf[i] != null && this.BDHBlf[i] != 0) {
                    ++this.BdHbLf;
                    this.BDHbLf.addImage(this.bdHBlf[i], i);
                }
                else {
                    i = 101;
                }
                ++i;
            }
            final int bdhBLf = this.BdhBLf;
            this.BdhbLf = 0;
            this.bdhbLf = bdhBLf;
            final int bdhBLf2 = this.bdhBLf;
            this.BDhbLf = 0;
            this.bDhbLf = bdhBLf2;
            if (this.BDHBlF) {
                final String bDhbLF = "Hey Idiot. This is COPYRIGHT. Yeah. CAN YOU SPELL THAT? *c*o*p*y*r*i*g*h*t*";
                this.bdHbLF = "That means. you should NOT be viewing this, or 'decompiling' it you moron. How about getting a life? Or write your own program.";
                this.BDhbLF = bDhbLF;
            }
        }
        this.bdHbLf = 0;
        this.repaint();
        try {
            this.BDHbLf.waitForID(0);
        }
        catch (InterruptedException ex3) {}
        this.bdHblf();
        try {
            this.BDHbLf.waitForAll();
        }
        catch (InterruptedException ex4) {}
        while (true) {
            ++this.bdHbLf;
            if (this.bdHbLf == this.BdHbLf) {
                this.bdHbLf = 0;
            }
            if (this.BdHBlf[this.bdHbLf] == -1) {
                WWBannerEval.BdHBlF = (WWBannerEval.BdHBlF + 1) % 11;
                WWBannerEval.BdhBlf = WWBannerEval.BdHBlF;
                if (null != null) {}
            }
            else {
                WWBannerEval.BdhBlf = this.BdHBlf[this.bdHbLf];
            }
            switch (WWBannerEval.BdhBlf) {
                case 0: {
                    this.bdhblf();
                    if (null == null) {
                        continue;
                    }
                    continue;
                }
                case 1: {
                    this.BDhblf();
                    continue;
                }
                case 2: {
                    this.BDHblf();
                    continue;
                }
                case 3: {
                    this.bdHblf();
                    continue;
                }
                case 4: {
                    this.bDhblf();
                    continue;
                }
                case 5: {
                    this.Bdhblf();
                    continue;
                }
            }
        }
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.getAppletContext().showStatus("" + this.BDhBlf[this.bdHbLf]);
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (event.shiftDown() && event.controlDown()) {
            try {
                this.bDhBlf = new URL("http://www.wyka-warzecha.com/");
            }
            catch (MalformedURLException ex) {}
            this.getAppletContext().showDocument(this.bDhBlf, "_blank");
        }
        else {
            this.getAppletContext().showDocument(this.BDhBlf[this.bdHbLf], this.bDHBlf[this.bdHbLf]);
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (event.shiftDown() && event.controlDown()) {
            this.getAppletContext().showStatus("Connecting to http://www.wyka-warzecha.com/");
        }
        else {
            this.getAppletContext().showStatus("Connecting to " + this.BDhBlf[this.bdHbLf]);
        }
        return true;
    }
    
    public WWBannerEval() {
        final int bdhBlF = 10;
        this.BdhBlF = 20;
        this.bDhBlF = 1;
        this.BDhBlF = false;
        this.bdHBlF = false;
        this.BDHBlF = true;
        this.bdhbLF = false;
        this.bdhBlF = bdhBlF;
        this.bdHBlf = new Image[101];
        this.BDhBlf = new URL[101];
        this.bDHBlf = new String[101];
        this.BDHBlf = new int[101];
        this.BdHBlf = new int[101];
        this.BDHbLf = new MediaTracker(this);
    }
}
