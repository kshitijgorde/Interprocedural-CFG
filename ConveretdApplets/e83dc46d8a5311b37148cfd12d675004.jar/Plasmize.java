import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Plasmize extends Applet implements Runnable
{
    Image a200;
    Image a201;
    Image[] a179;
    int a202;
    int a203;
    int a204;
    Thread a46;
    int a38;
    int[][] a205;
    boolean a35;
    String a100;
    String target;
    
    int a77(final String s, final int n) {
        if (this.getParameter(s) != null && !this.getParameter(s).equals("")) {
            return Integer.parseInt(this.getParameter(s));
        }
        return n;
    }
    
    public void init() {
        this.a200 = this.getImage(this.getDocumentBase(), this.getParameter("image1"));
        this.a201 = this.getImage(this.getDocumentBase(), this.getParameter("image2"));
        this.a204 = this.a77("delay", 80);
        this.a100 = this.getParameter("link");
        this.target = this.getParameter("target");
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(this.a200, 0);
        mediaTracker.addImage(this.a201, 1);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        this.a202 = this.a77("levels", 6);
        this.a179 = new Image[this.a202];
        for (int i = 0; i < this.a202; ++i) {
            this.a179[i] = this.createImage(this.size().width, this.size().height);
        }
        this.a206();
        System.out.println("FadeScroll http://www.javabase.co.uk/");
        final String host = this.getDocumentBase().getHost();
        if (host == null || host.equals("localhost") || host.equals("127.0.0.1") || host.equals("")) {
            this.a35 = false;
        }
        if (this.getParameter("key") != null) {
            final String a84 = this.a84(host);
            String s;
            for (s = this.getParameter("key"); this.a35 && s.indexOf(32) > 0; s = s.substring(s.indexOf(32) + 1, s.length())) {
                this.a35 = !s.substring(0, s.indexOf(32)).equals(a84);
            }
            if (this.a35) {
                this.a35 = !s.equals(a84);
            }
            if (this.a35 && this.getParameter("host") != null && this.getParameter("host").length() > 4 && host.indexOf(this.getParameter("host")) > -1 && this.getParameter("key").equals(this.a84(this.getParameter("host")))) {
                this.a35 = false;
            }
        }
        if (this.a35) {
            this.target = "http://www.javabase.fsnet.co.uk/";
        }
    }
    
    protected String a84(final String s) {
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            charArray[i] = (char)((charArray[i] + i * 7) % 26 + 97);
        }
        return new String(charArray);
    }
    
    private void a206() {
        final int width = this.size().width;
        final int height = this.size().height;
        final int[] array = new int[width * height];
        final int[] array2 = new int[width * height];
        final int[] array3 = new int[width * height];
        final PixelGrabber pixelGrabber = new PixelGrabber(this.a200, 0, 0, width, height, array, 0, width);
        final PixelGrabber pixelGrabber2 = new PixelGrabber(this.a201, 0, 0, width, height, array2, 0, width);
        try {
            pixelGrabber.grabPixels();
            pixelGrabber2.grabPixels();
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
            this.stop();
        }
        this.a203 = 256;
        this.a205 = this.a218(width, height, 256);
        for (int i = 0; i < this.a202; ++i) {
            for (int j = 0; j < width; ++j) {
                for (int k = 0; k < height; ++k) {
                    float n = this.a205[j][k] / 128.0f + 2.0f * i / this.a202;
                    if (n > 2.0f) {
                        n -= 2.0f;
                    }
                    if (n < 0.0f) {
                        n += 2.0f;
                    }
                    if (n > 1.0f) {
                        n = 2.0f - n;
                    }
                    array3[j + k * width] = this.a213(array[j + k * width], array2[j + k * width], n);
                }
            }
            this.a179[i] = this.createImage(new MemoryImageSource(width, height, ColorModel.getRGBdefault(), array3, 0, width));
            this.getGraphics().drawImage(this.a179[i], 0, 0, this);
        }
    }
    
    protected int a24(final int n, final int n2) {
        return 0xFF000000 | ((n & 0xFF0000) + (n2 & 0xFF0000) >> 1 & 0xFF0000) | ((n & 0xFF00) + (n2 & 0xFF00) >> 1 & 0xFF00) | ((n & 0xFF) + (n2 & 0xFF) >> 1 & 0xFF);
    }
    
    protected int a213(final int n, final int n2, final float n3) {
        final float n4 = 1.0f - n3;
        return 0xFF000000 | ((int)(n3 * (n & 0xFF0000) + n4 * (n2 & 0xFF0000)) & 0xFF0000) | ((int)(n3 * (n & 0xFF00) + n4 * (n2 & 0xFF00)) & 0xFF00) | ((int)(n3 * (n & 0xFF) + n4 * (n2 & 0xFF)) & 0xFF);
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        if (this.a179[this.a38] != null) {
            graphics.drawImage(this.a179[this.a38], 0, 0, this);
        }
    }
    
    public void start() {
        if (this.a46 == null) {
            (this.a46 = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.a46 != null) {
            this.a46.stop();
            this.a46 = null;
        }
    }
    
    public void run() {
        while (this.a46 != null) {
            try {
                Thread.sleep(this.a204);
            }
            catch (InterruptedException ex) {}
            this.getGraphics().drawImage(this.a179[this.a38], 0, 0, this);
            this.a38 = (this.a38 + 1) % this.a202;
            Thread.yield();
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        URL url = null;
        try {
            url = new URL("http://www.javabase.fsnet.co.uk/");
        }
        catch (MalformedURLException ex) {}
        if (url != null) {
            this.getAppletContext().showDocument(url);
        }
        return true;
    }
    
    int a215(final int n) {
        if (n < 0) {
            return 0;
        }
        if (n < this.size().width) {
            return n;
        }
        return this.size().width - 1;
    }
    
    int a216(final int n) {
        if (n < 0) {
            return 0;
        }
        if (n < this.size().height) {
            return n;
        }
        return this.size().height - 1;
    }
    
    int[][] a218(final int n, final int n2, final int n3) {
        int i;
        for (i = 1; i < ((n < n2) ? n2 : n); i *= 2) {}
        final int[][] array = new int[i + 1][i + 1];
        array[0][0] = (int)(Math.random() * n3);
        array[i][0] = (int)(Math.random() * n3);
        array[0][i] = (int)(Math.random() * n3);
        array[i][i] = (int)(Math.random() * n3);
        this.a219(array, i / 2, i, n3 / 2);
        return array;
    }
    
    void a219(final int[][] array, final int n, final int n2, final int n3) {
        for (int i = n; i < n2; i += n * 2) {
            for (int j = n; j < n2; j += n * 2) {
                array[i][j] = (array[i - n][j + n] + array[i + n][j + n] + array[i - n][j - n] + array[i + n][j - n]) / 4;
                final int[] array2 = array[i];
                final int n4 = j;
                array2[n4] += (int)(2 * n3 * Math.random() - n3);
                array[i][j] = Math.abs(array[i][j] % this.a203);
            }
        }
        for (int k = 0; k < n2; k += n * 2) {
            for (int l = n; l < n2; l += n * 2) {
                array[k][l] = (array[(k - n + n2) % n2][l] + array[(k + n) % n2][l]) / 2;
                final int[] array3 = array[k];
                final int n5 = l;
                array3[n5] += (int)(Math.random() * n3 * 2.0 - n3);
                final int[] array4 = array[k];
                final int n6 = l;
                array4[n6] %= this.a203;
                array[k][l] = Math.abs(array[k][l] % this.a203);
            }
        }
        for (int n7 = n; n7 < n2; n7 += n * 2) {
            for (int n8 = 0; n8 < n2; n8 += n * 2) {
                array[n7][n8] = (array[n7][(n8 - n + n2) % n2] + array[n7][(n8 + n) % n2]) / 2;
                final int[] array5 = array[n7];
                final int n9 = n8;
                array5[n9] += (int)(Math.random() * n3 * 2.0 - n3);
                final int[] array6 = array[n7];
                final int n10 = n8;
                array6[n10] %= this.a203;
                array[n7][n8] = Math.abs(array[n7][n8] % this.a203);
            }
        }
        if (n != 1) {
            this.a219(array, n / 2, n2, (n3 - 1 < 0) ? 0 : ((int)(n3 * 0.7)));
        }
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        URL url = null;
        try {
            url = new URL(this.a100);
        }
        catch (MalformedURLException ex) {
            try {
                url = new URL(this.getDocumentBase(), this.a100);
            }
            catch (MalformedURLException ex2) {}
        }
        if (url != null) {
            if (this.target != null && this.target != "") {
                this.getAppletContext().showDocument(url, this.target);
            }
            else {
                this.getAppletContext().showDocument(url);
            }
        }
        return true;
    }
    
    public Plasmize() {
        this.a35 = true;
    }
}
