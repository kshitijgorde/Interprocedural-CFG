import java.awt.Cursor;
import java.awt.image.ImageProducer;
import java.awt.image.PixelGrabber;
import java.net.MalformedURLException;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class DS_FireMenu extends Applet implements Runnable
{
    Thread _fld0;
    int _fld2;
    Font _fld3;
    int _fld4;
    int _fld5;
    int[] _fld6;
    int[] _fld7;
    MemoryImageSource _fld8;
    Image _fld9;
    int _fld0b;
    long _fld1b;
    String _fld2b;
    String _fld3b;
    int _fld4b;
    int _fld5b;
    String _fld6b;
    String _fld7b;
    String _fld8b;
    Image _fld9b;
    Graphics _fld0c;
    int _fld1c;
    int _fld2c;
    int _fld3c;
    int _fld4c;
    int _fld5c;
    int _fld6c;
    boolean _fld7c;
    String _fld8c;
    Color _fld9c;
    Color _fld0d;
    int _fld1d;
    int _fld2d;
    int _fld3d;
    String _fld4d;
    int[] _fld5d;
    int _fld6d;
    int _fld7d;
    int[] _fld8d;
    boolean _fld9d;
    boolean _fld0e;
    int[] _fld1e;
    int[] fb;
    int[] _fld2e;
    int[] _fld3e;
    int[] _fld4e;
    int _fld5e;
    int _fld6e;
    boolean _fld7e;
    int _fld8e;
    int[] _fld9e;
    int[] _fld0f;
    String _fld1f;
    URL _fld2f;
    boolean _fld3f;
    boolean _fld4f;
    byte[] _fld5f;
    byte[] _fld6f;
    String[] _fld7f;
    Color _fld8f;
    Font _fld9f;
    FontMetrics _fld0g;
    int _fld1g;
    int _fld2g;
    String[] _fld3g;
    String[] _fld4g;
    URL[] _fld5g;
    int _fld6g;
    int _fld7g;
    
    public DS_FireMenu() {
        this._fld2 = 0;
        this._fld3 = new Font("Helvetica", 1, 12);
        this._fld0b = 0;
        this._fld2b = "Applet by Dario Sciacca";
        this._fld3b = "dario@dseffects.com";
        this._fld4b = 0;
        this._fld6b = "www.dseffects.com";
        this._fld7b = "Don't remove Dario Sciacca's credits line";
        this._fld8b = this._fld2b + " (" + this._fld6b + ")";
        this._fld7c = false;
        this._fld8c = "";
        this._fld4d = "FireMenu started";
        this._fld9d = false;
        this._fld0e = false;
        this._fld7e = true;
        this._fld8e = 0;
        this._fld1f = "";
        this._fld3f = false;
        this._fld4f = false;
        this._fld1g = 1;
    }
    
    public String getAppletInfo() {
        return "DS FireMenu v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
    }
    
    public void init() {
        this._mth3();
        this.showStatus("Please wait ...");
        this._fld5b = this.getFontMetrics(this._fld3).stringWidth(this._fld6b);
        this._fld4 = this.size().width;
        this._fld5 = this.size().height;
    }
    
    public void run() {
        if (!this._fld7c) {
            this._fld8c = this.getParameter("loadtext");
            this._fld9c = this._mth0b("loadbgcolor", new Color(0));
            this._fld0d = this._mth0b("loadtextcolor", new Color(16777215));
            final String parameter = this.getParameter("regkey");
            if (parameter != null) {
                this._fld1f = parameter;
            }
            final Graphics graphics = this.getGraphics();
            graphics.setColor(this._fld9c);
            graphics.fillRect(0, 0, this._fld4, this._fld5);
            graphics.setColor(this._fld0d);
            graphics.drawString(this._fld8c, (this._fld4 >> 1) - (this.getFontMetrics(this._fld3).stringWidth(this._fld8c) >> 1), (this._fld5 >> 1) + 5);
            this._mth4();
            this._fld7 = new int[this._fld4 * this._fld5];
            this._fld8 = new MemoryImageSource(this._fld4, this._fld5, this._fld7, 0, this._fld4);
            this._fld9b = this.createImage(this._fld4, this._fld5);
            this._fld0c = this._fld9b.getGraphics();
            this._fld6 = new int[this._fld4 * this._fld5];
            final int n = 0xFF000000 | this._fld9c.getRGB();
            for (int i = 0; i < this._fld4 * this._fld5; ++i) {
                this._fld6[i] = n;
            }
            this._mth7();
            this._mth2c();
            this._mth3c();
            this._mth1b();
            this._mth5();
            this._mth2b();
            if (this._fld6c == -16777216) {
                this._fld2 = 1;
            }
            this._fld7c = true;
        }
        this.showStatus(this._fld4d);
        System.gc();
        final Graphics graphics2 = this.getGraphics();
        this._fld1b = System.currentTimeMillis();
        while (this._fld0 != null) {
            if (this._fld2 == 1) {
                this._mth3b();
            }
            this.paint(graphics2);
            this._mth0();
            if (this._fld0b++ > 10) {
                System.gc();
                this._fld0b = 0;
            }
        }
    }
    
    public void start() {
        (this._fld0 = new Thread(this)).start();
    }
    
    public void stop() {
        if (this._fld0 != null) {
            this._fld0.stop();
            this._fld0 = null;
        }
    }
    
    synchronized void _mth0() {
        Thread.yield();
        this.getToolkit().sync();
        final long n = 10L - (System.currentTimeMillis() - this._fld1b);
        if (n > 0L) {
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
        }
        else {
            try {
                Thread.sleep(1L);
            }
            catch (InterruptedException ex2) {}
        }
        this._fld1b = System.currentTimeMillis();
    }
    
    public void update(final Graphics graphics) {
    }
    
    public void paint(final Graphics graphics) {
        if (!this._fld7c) {
            return;
        }
        final int n = this._fld4 >> 1;
        int n2;
        if (this._fld1d < 200) {
            n2 = 10;
        }
        else if (this._fld1d < 400) {
            n2 = this._fld5 >> 1;
        }
        else {
            n2 = this._fld5 - 10;
        }
        ++this._fld1d;
        this._fld1d %= 600;
        if (this._fld2 == 0) {
            graphics.drawString("Error ...", 10, 10);
        }
        else {
            if (this._fld9 != null) {
                this._fld0c.drawImage(this._fld9, 0, 0, this);
            }
            if (this._fld9d && !this._fld3f) {
                this._fld0c.setColor(Color.white);
                this._fld0c.drawLine(n - 64, n2 - 8, n + 64, n2 - 8);
                this._fld0c.drawLine(n - 64, n2 + 8, n + 64, n2 + 8);
                this._fld0c.drawLine(n - 64, n2 - 8, n - 64, n2 + 8);
                this._fld0c.drawLine(n + 64, n2 - 8, n + 64, n2 + 8);
                this._fld0c.setColor(Color.blue);
                this._fld0c.fillRect(n - 63, n2 - 7, 127, 15);
                this._fld0c.setFont(this._fld3);
                this._fld0c.setColor(Color.yellow);
                this._fld0c.drawString(this._fld6b, n - (this._fld5b >> 1), n2 + 5);
                if (this._fld2d > n - 64 && this._fld2d < n + 64 && this._fld3d > n2 - 8 && this._fld3d < n2 + 8) {
                    this._fld4f = true;
                    this.showStatus(this._fld6b);
                }
                else {
                    this._fld4f = false;
                }
            }
            graphics.drawImage(this._fld9b, 0, 0, this);
        }
    }
    
    private final void _mth2() {
        while (true) {
            this.showStatus(this._fld7b);
        }
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this._fld4g[this._fld6g]);
        return this._fld9d = true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this._fld9d = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int fld2d, final int fld3d) {
        this.showStatus(this._fld4g[this._fld6g]);
        this._fld2d = fld2d;
        this._fld3d = fld3d;
        return this._fld9d = true;
    }
    
    void _mth3() {
        final String parameter = this.getParameter("credits");
        if (parameter != null) {
            if (!parameter.equals(this._fld8b)) {
                this._mth2();
            }
        }
        else {
            this._mth2();
        }
        this._fld4c = 1;
    }
    
    void _mth4() {
        this._fld5c = 1;
        for (int i = 0; i < 11; ++i) {
            if (this._fld3b.charAt(i) == this._fld2b.charAt(i) || this._fld4c == 0) {
                this._mth2();
            }
        }
        this._fld3f = false;
    }
    
    void _mth5() {
        if (this._fld4c == 0 || this._fld5c == 0) {
            this._mth2();
        }
        for (int i = 0; i < 17; ++i) {
            if (this._fld3b.charAt(i) == this._fld6b.charAt(i)) {
                this._mth2();
            }
        }
        this._fld6c = -16777216;
        if (this._fld2b.charAt(1) != 'p' || this._fld2b.charAt(7) != 'b' || this._fld2b.charAt(21) != 'c' || this._fld2b.charAt(17) != 'c' || this._fld2b.charAt(12) != 'r' || this._fld2b.charAt(11) != 'a') {
            this._mth2();
        }
    }
    
    int[] _mth6(final int[] array, final int n, final int n2, final int[] array2, final int n3, final int n4) {
        int n5 = 0;
        final double n6 = n3 / n;
        final double n7 = n4 / n2;
        for (int i = 0; i < n2; ++i) {
            final int n8 = (int)(i * n7);
            for (int j = 0; j < n; ++j) {
                array[n5++] = array2[n8 * n3 + (int)(j * n6)];
            }
        }
        return array;
    }
    
    boolean _mth7() {
        final String parameter = this.getParameter("image");
        if (parameter != null) {
            final Image mth8 = this._mth8(parameter);
            if (mth8 == null) {
                this.showStatus("Error loading image ");
                return false;
            }
            final int width = mth8.getWidth(this);
            final int height = mth8.getHeight(this);
            if (width != this._fld4 || height != this._fld5) {
                final int[] array = new int[width * height];
                if (!this._mth9(mth8, array, width, height)) {
                    return false;
                }
                this._fld6 = this._mth6(this._fld6, this._fld4, this._fld5, array, width, height);
            }
            else if (!this._mth9(mth8, this._fld6, this._fld4, this._fld5)) {
                return false;
            }
            mth8.flush();
            System.gc();
        }
        return true;
    }
    
    Image _mth8(final String s) {
        this.showStatus("Loading Image ...");
        final MediaTracker mediaTracker = new MediaTracker(this);
        URL url = null;
        try {
            url = new URL(this.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {}
        Image image;
        try {
            image = this.getImage(url);
            mediaTracker.addImage(image, 1);
            mediaTracker.waitForID(1);
        }
        catch (InterruptedException ex2) {
            image = null;
        }
        if (mediaTracker.isErrorID(1)) {
            image = null;
        }
        return image;
    }
    
    boolean _mth9(final Image image, final int[] array, final int n, final int n2) {
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, n, n2, array, 0, n);
        try {
            if (!pixelGrabber.grabPixels()) {
                this.showStatus("Image error");
                return false;
            }
        }
        catch (InterruptedException ex) {
            this.showStatus("Image error");
            return false;
        }
        if ((pixelGrabber.status() & 0x80) != 0x0) {
            this.showStatus("Image error");
            return false;
        }
        return true;
    }
    
    Color _mth0b(final String s, final Color color) {
        final String parameter = this.getParameter(s);
        Color color2;
        if (parameter != null && parameter.length() == 6) {
            try {
                color2 = new Color(Integer.parseInt(parameter, 16));
            }
            catch (NumberFormatException ex) {
                color2 = color;
            }
        }
        else {
            color2 = color;
        }
        return color2;
    }
    
    void _mth1b() {
        String parameter = this.getParameter("intensity");
        if (parameter == null) {
            parameter = "1";
        }
        this._fld5e = Integer.valueOf(parameter);
        this._fld5e = ((this._fld5e >= 1) ? ((this._fld5e <= 8) ? this._fld5e : 8) : 1);
        this._fld5e += 12;
    }
    
    void _mth2b() {
        if (this._fld2g == 0) {
            this._fld6d = this._fld5 / this._fld1c;
            this._fld7d = this._fld4;
        }
        else {
            this._fld6d = this._fld5;
            this._fld7d = this._fld4 / this._fld1c;
        }
        final int n = this._fld4 * this._fld5;
        this._fld1e = new int[n];
        this._fld5d = new int[n];
        this._mth4c();
        if (!this._fld7e) {
            this._fld6 = new int[this._fld4 * this._fld5];
            for (int i = 0; i < this._fld4 * this._fld5; ++i) {
                this._fld6[i] = this._fld6e;
            }
        }
        this._fld8d = new int[this._fld4 * this._fld5];
        for (int j = 0; j < this._fld4 * this._fld5; ++j) {
            if (this._fld1e[j] == 255) {
                this._fld8d[j] = this._fld7g;
            }
            else {
                this._fld8d[j] = this._fld6[j];
            }
        }
        this._fld4e = new int[this._fld1c];
        for (int k = 0; k < this._fld1c; ++k) {
            this._fld4e[k] = 0;
        }
        this._mth6b();
        for (int l = 0; l < 256 * this._fld1c; ++l) {
            this._fld0f[l] = this._fld9e[l % 256];
        }
        this._fld3e = new int[this._fld4 * this._fld5];
        if (this._fld4 * this._fld5 > 65536) {
            this._fld2e = new int[this._fld4 * this._fld5];
        }
        else {
            this._fld2e = new int[65536];
        }
        this.fb = new int[this._fld4 * this._fld5];
        this._mth5b();
    }
    
    void _mth3b() {
        this._mth5c();
        final int n = this._fld4 * this._fld5;
        int n2 = 0;
        if (this._fld2g == 0) {
            for (int i = 0; i < this._fld1c; ++i) {
                final int n3 = i * this._fld6d;
                for (int n4 = (i + 1) * this._fld6d, j = n3; j < n4; ++j) {
                    for (int k = 0; k < this._fld4; ++k, ++n2) {
                        final int n5 = this._fld1e[n2] + (this._fld3e[n2] * this.fb[(n2 + this._fld8e) % n] >> 6);
                        final int n6 = ((n5 <= 255) ? n5 : 255) + i * 256;
                        final int n7 = this._fld0f[n6] >> 16 & 0xFF;
                        final int n8 = this._fld0f[n6] >> 8 & 0xFF;
                        final int n9 = this._fld0f[n6] & 0xFF;
                        final int n10 = this._fld8d[n2] >> 16 & 0xFF;
                        final int n11 = this._fld8d[n2] >> 8 & 0xFF;
                        final int n12 = this._fld8d[n2] & 0xFF;
                        int n13 = n7 + n10;
                        int n14 = n8 + n11;
                        int n15 = n9 + n12;
                        if (n13 > 255) {
                            n13 = 255;
                        }
                        if (n14 > 255) {
                            n14 = 255;
                        }
                        if (n15 > 255) {
                            n15 = 255;
                        }
                        this._fld7[n2] = (0xFF000000 | n13 << 16 | n14 << 8 | n15);
                    }
                }
            }
        }
        else {
            for (int l = 0; l < this._fld1c; ++l) {
                final int n16 = l * this._fld7d;
                final int n17 = (l + 1) * this._fld7d;
                int n18 = n16;
                for (int n19 = 0; n19 < this._fld5; ++n19) {
                    for (int n20 = n16; n20 < n17; ++n20, ++n18) {
                        final int n21 = this._fld1e[n18] + (this._fld3e[n18] * this.fb[(n18 + this._fld8e) % n] >> 6);
                        final int n22 = ((n21 <= 255) ? n21 : 255) + l * 256;
                        final int n23 = this._fld0f[n22] >> 16 & 0xFF;
                        final int n24 = this._fld0f[n22] >> 8 & 0xFF;
                        final int n25 = this._fld0f[n22] & 0xFF;
                        final int n26 = this._fld8d[n18] >> 16 & 0xFF;
                        final int n27 = this._fld8d[n18] >> 8 & 0xFF;
                        final int n28 = this._fld8d[n18] & 0xFF;
                        int n29 = n23 + n26;
                        int n30 = n24 + n27;
                        int n31 = n25 + n28;
                        if (n29 > 255) {
                            n29 = 255;
                        }
                        if (n30 > 255) {
                            n30 = 255;
                        }
                        if (n31 > 255) {
                            n31 = 255;
                        }
                        this._fld7[n18] = (0xFF000000 | n29 << 16 | n30 << 8 | n31);
                    }
                    n18 += this._fld4 - this._fld7d;
                }
            }
        }
        this._fld8e = (this._fld8e + this._fld4) % n;
        this._fld9 = this.createImage(this._fld8);
    }
    
    void _mth4b(final int[] array) {
        for (int i = 1; i < this._fld5 - 1; ++i) {
            final int n = (i - 1) * this._fld4;
            final int n2 = (i + 1) * this._fld4;
            final int n3 = i * this._fld4;
            for (int j = 1; j < this._fld4 - 1; ++j) {
                final int n4 = j - 1;
                final int n5 = j + 1;
                array[i * this._fld4 + j] = array[n4 + n] + array[n4 + n2] + array[n5 + n] + array[n5 + n2] + array[j + n] + array[j + n2] + array[n4 + n3] + array[n5 + n3] >> 3;
            }
        }
    }
    
    void _mth5b() {
        for (int i = 0; i < this._fld4 * this._fld5; ++i) {
            this._fld2e[i] = (this._fld3e[i] = this._fld1e[i]);
        }
        this._mth9b(this._fld2e, this._fld3e);
        for (int j = 0; j < this._fld4 * this._fld5; ++j) {
            final int[] fld1e = this._fld1e;
            final int n = j;
            fld1e[n] >>= 2;
            this._fld3e[j] = this._fld2e[j];
        }
        final int n2 = this._fld4 * this._fld5;
        final int n3 = n2 - this._fld4;
        for (int k = 0; k < this._fld4; ++k) {
            this._fld3e[k] = 0;
        }
        for (int l = 1; l < this._fld5 - 1; ++l) {
            final int n4 = l * this._fld4;
            final int n5 = n4 + this._fld4;
            this._fld3e[n4] = 0;
            this._fld3e[n5] = 0;
        }
        for (int n6 = n3; n6 < n2; ++n6) {
            this._fld3e[n6] = 0;
        }
        this._mth4b(this._fld3e);
        this._mth4b(this._fld3e);
        this._mth1c(this._fld2e, 2, 2);
        this.fb = this._mth6(this.fb, this._fld4, this._fld5, this._fld2e, 256, 256);
    }
    
    void _mth6b() {
        this._fld9e = new int[256];
        this._fld0f = new int[256 * this._fld1c];
        this._mth7b(0, 64, 0, 255, 0, 0, 0, 0);
        this._mth7b(64, 128, 255, 255, 0, 255, 0, 0);
        this._mth7b(128, 256, 255, 255, 255, 255, 0, 255);
    }
    
    void _mth7b(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        final int n9 = n2 - n;
        final int n10 = n4 - n3;
        final int n11 = n6 - n5;
        final int n12 = n8 - n7;
        for (int i = 0; i < n9; ++i) {
            this._fld9e[n + i] = (0xFF000000 | n3 + i * n10 / n9 << 16 | n5 + i * n11 / n9 << 8 | n7 + i * n12 / n9);
        }
    }
    
    void _mth8b(final int n, final int n2, final int[] array, final int[] array2) {
        for (int i = 0; i < 256; ++i) {
            array[i + n * 256] = array2[i * n2 >> 8];
        }
    }
    
    void _mth9b(final int[] array, final int[] array2) {
        for (int i = 0; i < this._fld4; ++i) {
            int n;
            for (int j = this._fld5 - 1; j >= 0; j -= n) {
                n = (int)(Math.random() * this._fld5e);
                for (int k = 0; k < n; ++k) {
                    if (j - k >= 0) {
                        array[(j - k) * this._fld4 + i] = array2[j * this._fld4 + i] + array[(j - k) * this._fld4 + i] >> 1;
                    }
                }
            }
        }
    }
    
    int _mth0c(final int n, final int n2) {
        int n3 = n + (int)(Math.random() * n2) - n2 / 2;
        if (n3 < 0) {
            n3 = 0;
        }
        if (n3 > 255) {
            n3 = 255;
        }
        return n3;
    }
    
    void _mth1c(final int[] array, final int n, final int n2) {
        int n3;
        for (int i = 256; i > 1; i = n3) {
            n3 = i >> 1;
            final int n4 = i * n + n2;
            for (int j = 0; j < 256; j += i) {
                final int n5 = j + i & 0xFF;
                for (int k = 0; k < 256; k += i) {
                    final int n6 = k + i & 0xFF;
                    final int n7 = j + n3;
                    final int n8 = k + n3;
                    final int n9 = array[(j << 8) + k];
                    final int n10 = array[(n5 << 8) + k];
                    final int n11 = array[(j << 8) + n6];
                    final int n12 = array[(n5 << 8) + n6];
                    array[(n7 << 8) + n8] = this._mth0c(n9 + n10 + n11 + n12 >> 2, n4);
                    if (j == 0) {
                        array[(n7 << 8) + k] = this._mth0c(n9 + n10 >> 1, n4);
                    }
                    if (k == 0) {
                        array[(j << 8) + n8] = this._mth0c(n9 + n11 >> 1, n4);
                    }
                    array[(n5 << 8) + n8] = this._mth0c(n10 + n12 >> 1, n4);
                    array[(n7 << 8) + n6] = this._mth0c(n11 + n12 >> 1, n4);
                }
            }
        }
    }
    
    void _mth2c() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this._fld1f.length() > 9) {
            final int n = this._fld1f.length() - 9;
            final int n2 = n + 9;
            this._fld5f = new byte[n];
            this._fld1f.getBytes(1, n + 1, this._fld5f, 0);
            this._fld6f = new byte[n2];
            this._fld1f.getBytes(0, n2, this._fld6f, 0);
            int n3 = n % 7;
            final int n4 = n % 3;
            for (int i = 0; i < n; ++i) {
                final byte b = this._fld5f[i];
                final byte b2 = (byte)(b + n3);
                if (b >= 48 && b <= 57) {
                    this._fld5f[i] = ((b2 <= 57) ? b2 : ((byte)(b2 - 10)));
                }
                else if (b >= 65 && b <= 90) {
                    this._fld5f[i] = ((b2 <= 90) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b >= 97 && b <= 122) {
                    this._fld5f[i] = ((b2 <= 122) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b == 42) {
                    this._fld5f[i] = 45;
                }
                else if (b == 45) {
                    this._fld5f[i] = 46;
                }
                n3 = (n3 + n4) % 7;
            }
            int n5 = n % 7;
            final int n6 = n % 3;
            for (int j = 0; j < n; ++j) {
                final byte b3 = this._fld5f[j];
                final byte b4 = (byte)(b3 - n5);
                if (b3 >= 48 && b3 <= 57) {
                    this._fld6f[j + 1] = ((b4 >= 48) ? b4 : ((byte)(b4 + 10)));
                }
                else if (b3 >= 65 && b3 <= 90) {
                    this._fld6f[j + 1] = ((b4 >= 65) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 >= 97 && b3 <= 122) {
                    this._fld6f[j + 1] = ((b4 >= 97) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 == 45) {
                    this._fld6f[j + 1] = 42;
                }
                else if (b3 == 46) {
                    this._fld6f[j + 1] = 45;
                }
                n5 = (n5 + n6) % 7;
            }
            byte[] array;
            if (n > 4) {
                array = new byte[n - 4];
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld5f[k + 4];
                }
            }
            else {
                array = new byte[] { 0 };
            }
            if (this._fld6f[0] == this._fld6f[n >> 1] && this._fld6f[1 + n] == this._fld6f[1] && this._fld6f[1 + n + 1] == this._fld6f[n >> 1] && this._fld6f[1 + n + 2] == (byte)(97 + n5) && this._fld6f[1 + n + 3] == 45 && this._fld6f[1 + n + 4] == (byte)(122 - n6) && this._fld6f[1 + n + 5] == (byte)(110 + n5) && this._fld6f[1 + n + 6] == this._fld6f[1] && this._fld6f[1 + n + 7] == this._fld6f[n] && (host.equalsIgnoreCase(new String(this._fld5f, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this._fld3f = true;
            }
        }
        try {
            this._fld2f = new URL("http://" + this._fld6b);
        }
        catch (MalformedURLException ex) {
            this._fld2f = null;
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld3f && this._fld4f) {
            this.getAppletContext().showDocument(this._fld2f, "_blank");
        }
        else if (this._fld5g[this._fld6g] != null) {
            if (this._fld3f && this._fld3g[this._fld6g] != null) {
                this.getAppletContext().showDocument(this._fld5g[this._fld6g], this._fld3g[this._fld6g]);
            }
            else {
                this.getAppletContext().showDocument(this._fld5g[this._fld6g], "_blank");
            }
        }
        return true;
    }
    
    void _mth3c() {
        int fld1c = 0;
        do {
            ++fld1c;
        } while (this.getParameter("text" + fld1c) != null);
        if (--fld1c == 0 || fld1c == 1) {
            this._fld1c = 2;
        }
        else if (fld1c > 1) {
            this._fld1c = fld1c;
        }
        this._fld7f = new String[this._fld1c];
        this._fld5g = new URL[this._fld1c];
        this._fld3g = new String[this._fld1c];
        this._fld4g = new String[this._fld1c];
        for (int i = 0; i < this._fld1c; ++i) {
            this._fld7f[i] = this.getParameter("text" + String.valueOf(i + 1));
            if (this._fld7f[i] == null) {
                this._fld7f[i] = new String("Text" + String.valueOf(i + 1));
            }
            if (this._fld7f[i].length() > this._fld1g) {
                this._fld1g = this._fld7f[i].length();
            }
            final String parameter = this.getParameter("link" + String.valueOf(i + 1));
            try {
                this._fld5g[i] = new URL("http://" + parameter);
            }
            catch (MalformedURLException ex) {
                this._fld5g[i] = null;
            }
            final String parameter2 = this.getParameter("regtarget" + String.valueOf(i + 1));
            if (parameter2 != null && this._fld3f) {
                this._fld3g[i] = parameter2;
            }
            else {
                this._fld3g[i] = "_blank";
            }
            final String parameter3 = this.getParameter("regstatusmsg" + String.valueOf(i + 1));
            if (parameter3 != null && this._fld3f) {
                this._fld4g[i] = parameter3;
            }
            else {
                this._fld4g[i] = this._fld2b;
            }
        }
        final String parameter4 = this.getParameter("menudir");
        if (parameter4 == null) {
            this._fld2g = 0;
        }
        else if (parameter4.equalsIgnoreCase("vertical")) {
            this._fld2g = 0;
        }
        else {
            this._fld2g = 1;
        }
        this._fld8f = this._mth0b("seltextcol", new Color(16711680));
        this._fld7g = (0xFF000000 | this._fld8f.getRGB());
        String parameter5 = this.getParameter("textfont");
        if (parameter5 == null) {
            parameter5 = "Helvetica";
        }
        final String parameter6 = this.getParameter("TextStyle");
        int n;
        if (parameter6 == null) {
            n = 0;
        }
        else if (parameter6.equalsIgnoreCase("PLAIN")) {
            n = 0;
        }
        else if (parameter6.equalsIgnoreCase("BOLD")) {
            n = 1;
        }
        else if (parameter6.equalsIgnoreCase("ITALIC")) {
            n = 2;
        }
        else if (parameter6.equalsIgnoreCase("BOLD ITALIC")) {
            n = 3;
        }
        else {
            n = 0;
        }
        int n2;
        if (this._fld2g == 0) {
            n2 = this._fld4 / (this._fld1g + 2) * 2;
            final int n3 = this._fld5 / (this._fld1c << 1);
            if (n3 < n2) {
                n2 = n3;
            }
        }
        else {
            n2 = this._fld4 / this._fld1c / (this._fld1g + 1) * 2;
        }
        this._fld9f = new Font(parameter5, n, n2);
        this._fld0g = this._fld0c.getFontMetrics(this._fld9f);
    }
    
    void _mth4c() {
        final MemoryImageSource memoryImageSource = new MemoryImageSource(this._fld4, this._fld5, this._fld1e, 0, this._fld4);
        final Image image = this.createImage(this._fld4, this._fld5);
        final Graphics graphics = image.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this._fld4, this._fld5);
        graphics.setColor(Color.white);
        graphics.setFont(this._fld9f);
        for (int i = 0; i < this._fld1c; ++i) {
            this._fld2c = this._fld0g.stringWidth(this._fld7f[i]);
            this._fld3c = this._fld0g.getHeight();
            final int n = (this._fld3c >> 1) + (this._fld3c >> 3);
            graphics.setFont(this._fld9f);
            if (this._fld2g == 0) {
                graphics.drawString(this._fld7f[i], (this._fld4 >> 1) - (this._fld2c >> 1), (this._fld5 / this._fld1c >> 2) + this._fld5 / this._fld1c * i + n);
            }
            else {
                graphics.drawString(this._fld7f[i], this._fld4 / this._fld1c * i + (this._fld4 / this._fld1c >> 1) - (this._fld2c >> 1), (this._fld5 >> 1) + (n >> 1));
            }
        }
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this._fld4, this._fld5, this._fld1e, 0, this._fld4);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        for (int j = 0; j < this._fld4 * this._fld5; ++j) {
            if ((this._fld1e[j] & 0xFF) == 0xFF) {
                this._fld1e[j] = 255;
            }
            else {
                this._fld1e[j] = 0;
            }
        }
    }
    
    void _mth5c() {
        for (int i = 0; i < this._fld1c; ++i) {
            if (this._fld2g == 0) {
                int fld5 = this._fld5 / this._fld1c * ((i + 1) % this._fld1c);
                if (i == this._fld1c - 1) {
                    fld5 = this._fld5;
                }
                final int n = this._fld5 / this._fld1c * i;
                if (this._fld9d && this._fld3d >= n && this._fld3d < fld5) {
                    this._fld6g = i;
                    this.setCursor(Cursor.getPredefinedCursor(12));
                    final int[] fld4e = this._fld4e;
                    final int n2 = i;
                    fld4e[n2] -= 8;
                    if (this._fld4e[i] < 0) {
                        this._fld4e[i] = 0;
                    }
                    this._mth8b(this._fld6g, this._fld4e[i], this._fld0f, this._fld9e);
                }
                else {
                    final int[] fld4e2 = this._fld4e;
                    final int n3 = i;
                    fld4e2[n3] += 8;
                    if (this._fld4e[i] > 255) {
                        this._fld4e[i] = 255;
                    }
                    this._mth8b(i, this._fld4e[i], this._fld0f, this._fld9e);
                }
            }
            else {
                int fld6 = this._fld4 / this._fld1c * ((i + 1) % this._fld1c);
                if (i == this._fld1c - 1) {
                    fld6 = this._fld4;
                }
                final int n4 = this._fld4 / this._fld1c * i;
                if (this._fld9d && this._fld2d >= n4 && this._fld2d < fld6) {
                    this._fld6g = i;
                    this.setCursor(Cursor.getPredefinedCursor(12));
                    final int[] fld4e3 = this._fld4e;
                    final int n5 = i;
                    fld4e3[n5] -= 8;
                    if (this._fld4e[i] < 0) {
                        this._fld4e[i] = 0;
                    }
                    this._mth8b(this._fld6g, this._fld4e[i], this._fld0f, this._fld9e);
                }
                else {
                    final int[] fld4e4 = this._fld4e;
                    final int n6 = i;
                    fld4e4[n6] += 8;
                    if (this._fld4e[i] > 255) {
                        this._fld4e[i] = 255;
                    }
                    this._mth8b(i, this._fld4e[i], this._fld0f, this._fld9e);
                }
            }
        }
    }
}
