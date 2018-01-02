import java.awt.image.ImageProducer;
import java.awt.image.PixelGrabber;
import java.net.MalformedURLException;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Color;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class DS_FadeToCube extends Applet implements Runnable
{
    Thread _fld0;
    int _fld4;
    Font _fld5;
    int _fld6;
    int _fld7;
    int[] _fld8;
    int[] _fld9;
    int[] _fld0b;
    MemoryImageSource _fld1b;
    Image _fld2b;
    int _fld3b;
    long _fld4b;
    String _fld5b;
    String _fld6b;
    int _fld7b;
    int _fld8b;
    String _fld9b;
    String _fld0c;
    String _fld1c;
    boolean _fld2c;
    boolean _fld3c;
    Image _fld4c;
    Graphics _fld5c;
    Image _fld6c;
    int _fld7c;
    int _fld8c;
    int _fld9c;
    int _fld0d;
    int _fld1d;
    int _fld2d;
    int _fld3d;
    int _fld4d;
    int _fld5d;
    int _fld6d;
    int _fld7d;
    int _fld8d;
    int _fld9d;
    int _fld0e;
    int _fld1e;
    boolean _fld2e;
    String _fld3e;
    int _fld4e;
    boolean _fld5e;
    int _fld6e;
    int _fld7e;
    int _fld8e;
    int _fld9e;
    int _fld0f;
    int[] _fld1f;
    int _fld2f;
    int _fld3f;
    int _fld4f;
    int[] _fld5f;
    int _fld6f;
    int _fld7f;
    int[] xp;
    int[] _fld8f;
    int[] _fld9f;
    double _fld0g;
    double _fld1g;
    double _fld2g;
    double _fld3g;
    double _fld4g;
    double _fld5g;
    double _fld6g;
    double _fld7g;
    double _fld8g;
    double _fld9g;
    double _fld0h;
    double _fld1h;
    double _fld2h;
    double _fld3h;
    double _fld4h;
    double _fld5h;
    double _fld6h;
    double _fld7h;
    double _fld8h;
    double _fld9h;
    double _fld0i;
    double _fld1i;
    double _fld2i;
    double _fld3i;
    double _fld4i;
    double _fld5i;
    double _fld6i;
    double _fld7i;
    double _fld8i;
    double _fld9i;
    int[] _fld0j;
    int[] _fld1j;
    int[] _fld2j;
    int _fld3j;
    int _fld4j;
    int[] _fld5j;
    double _fld6j;
    double _fld7j;
    int _fld8j;
    int _fld9j;
    int _fld0k;
    String _fld1k;
    URL[] _fld2k;
    String[] _fld3k;
    String[] _fld4k;
    URL _fld5k;
    boolean _fld6k;
    byte[] _fld7k;
    byte[] _fld8k;
    String[] _fld9k;
    Color[] _fld0l;
    Color[] _fld1l;
    Font[] _fld2l;
    FontMetrics[] _fld3l;
    String[] _fld4l;
    int[] _fld5l;
    int[] _fld6l;
    
    public String getAppletInfo() {
        return "DS FadeToCube v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
    }
    
    public void init() {
        this._mth5();
        this.showStatus("Please wait ...");
        this._fld8b = this.getFontMetrics(this._fld5).stringWidth(this._fld9b);
        this._fld6 = this.size().width;
        this._fld7 = this.size().height;
        this._mth6();
        this._fld0b = new int[this._fld6 * this._fld7];
        this._fld1b = new MemoryImageSource(this._fld6, this._fld7, this._fld0b, 0, this._fld6);
        this._fld4c = this.createImage(this._fld6, this._fld7);
        this._fld5c = this._fld4c.getGraphics();
        this._mth6c();
        this._mth9c();
        if (!this._fld2c && !this._fld3c) {
            this._fld7b = 0;
        }
        else if (this._fld2c && !this._fld3c) {
            this._fld7b = 1;
        }
        else if (!this._fld2c && this._fld3c) {
            this._fld7b = 2;
        }
        else {
            this._fld7b = 3;
        }
        this._fld9 = new int[this._fld6 * this._fld7];
        if (this._fld6 > 256 && this._fld7 > 256) {
            this._fld6f = 256;
        }
        else {
            this._fld6f = 128;
        }
        this._mth4b();
        for (int i = 0; i < this._fld6 * this._fld7; ++i) {
            this._fld9[i] = this._fld7f;
        }
        this._mth9();
        if (this._mth0b()) {
            this._mth5c();
            this._mth7();
            this._mth5b();
            if (this._fld9d == -16777216) {
                this._fld4 = 1;
            }
            return;
        }
        while (true) {
            this.showStatus("Error loading image ");
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
    
    public void run() {
        this.showStatus(this._fld3e);
        System.gc();
        final Graphics graphics = this.getGraphics();
        this._fld4b = System.currentTimeMillis();
        while (this._fld0 != null) {
            if (this._fld4 == 1) {
                this._mth7b();
            }
            this._mth4(graphics);
            this._mth0();
            if (this._fld3b++ > 10) {
                System.gc();
                this._fld3b = 0;
            }
        }
    }
    
    synchronized void _mth0() {
        Thread.yield();
        this.getToolkit().sync();
        final long n = 10L - (System.currentTimeMillis() - this._fld4b);
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
        this._fld4b = System.currentTimeMillis();
    }
    
    public void update(final Graphics graphics) {
    }
    
    void _mth4(final Graphics graphics) {
        final int n = this._fld6 >> 1;
        final int n2 = this._fld7 >> 1;
        if (this._fld4 == 0) {
            graphics.drawString("Error ...", 10, 10);
            return;
        }
        if (this._fld2b != null) {
            if (this._fld7b == 0) {
                this._fld5c.drawImage(this._fld2b, 0, 0, this);
            }
            else if (this._fld7b == 1) {
                this._fld5c.drawImage(this._fld2b, 0, 0, this);
                this._mth8c(this._fld5c);
            }
            else if (this._fld7b == 2) {
                this._fld5c.drawImage(this._fld2b, 0, 0, this);
                this._fld5c.drawImage(this._fld6c, this._fld7c, this._fld8c, this);
            }
            else {
                this._fld5c.drawImage(this._fld2b, 0, 0, this);
                this._mth8c(this._fld5c);
                this._fld5c.drawImage(this._fld6c, this._fld7c, this._fld8c, this);
            }
        }
        if (this._fld5e && !this._fld6k) {
            this._fld5c.setColor(Color.white);
            this._fld5c.drawLine(n - 64, n2 - 8, n + 64, n2 - 8);
            this._fld5c.drawLine(n - 64, n2 + 8, n + 64, n2 + 8);
            this._fld5c.drawLine(n - 64, n2 - 8, n - 64, n2 + 8);
            this._fld5c.drawLine(n + 64, n2 - 8, n + 64, n2 + 8);
            this._fld5c.setColor(Color.blue);
            this._fld5c.fillRect(n - 63, n2 - 7, 127, 15);
            this._fld5c.setFont(this._fld5);
            this._fld5c.setColor(Color.yellow);
            this._fld5c.drawString(this._fld9b, n - (this._fld8b >> 1), n2 + 5);
        }
        graphics.drawImage(this._fld4c, 0, 0, this);
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this._fld4k[this._fld7e]);
        return this._fld5e = true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this._fld5e = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int fld0e, final int fld1e) {
        this._fld0e = fld0e;
        this._fld1e = fld1e;
        this._fld5e = true;
        this.showStatus(this._fld4k[this._fld7e]);
        return true;
    }
    
    void _mth5() {
        final String parameter = this.getParameter("credits");
        if (parameter != null) {
            if (parameter.equals(this._fld1c)) {
                this._fld7d = 1;
                return;
            }
            while (true) {
                this.showStatus(this._fld0c);
            }
        }
        else {
            while (true) {
                this.showStatus(this._fld0c);
            }
        }
    }
    
    void _mth6() {
        this._fld8d = 1;
        for (int i = 0; i < 11; ++i) {
            if (this._fld6b.charAt(i) == this._fld5b.charAt(i) || this._fld7d == 0) {
                while (true) {
                    this.showStatus(this._fld0c);
                }
            }
            else {}
        }
        this._fld6k = false;
    }
    
    void _mth7() {
        if (this._fld7d == 0 || this._fld8d == 0) {
            while (true) {
                this.showStatus(this._fld0c);
            }
        }
        else {
            for (int i = 0; i < 17; ++i) {
                if (this._fld6b.charAt(i) == this._fld9b.charAt(i)) {
                    while (true) {
                        this.showStatus(this._fld0c);
                    }
                }
                else {}
            }
            this._fld9d = -16777216;
            if (this._fld5b.charAt(1) == 'p' && this._fld5b.charAt(7) == 'b' && this._fld5b.charAt(21) == 'c' && this._fld5b.charAt(17) == 'c' && this._fld5b.charAt(12) == 'r' && this._fld5b.charAt(11) == 'a') {
                return;
            }
            while (true) {
                this.showStatus(this._fld0c);
            }
        }
    }
    
    int[] _mth8(final int n, final int[] array, final int n2, final int n3, final int[] array2, final int n4, final int n5) {
        int n6 = n;
        final double n7 = n4 / n2;
        final double n8 = n5 / n3;
        for (int i = 0; i < n3; ++i) {
            final int n9 = (int)(i * n8);
            for (int j = 0; j < n2; ++j) {
                array[n6++] = array2[n9 * n4 + (int)(j * n7)];
            }
        }
        return array;
    }
    
    boolean _mth9() {
        final String parameter = this.getParameter("bgimage");
        if (parameter != null) {
            final Image mth1b = this._mth1b(parameter, 0);
            final int width = mth1b.getWidth(this);
            final int height = mth1b.getHeight(this);
            if (width != this._fld6 || height != this._fld7) {
                final int[] array = new int[width * height];
                if (!this._mth2b(0, mth1b, array, width, height)) {
                    return false;
                }
                this._fld9 = this._mth8(0, this._fld9, this._fld6, this._fld7, array, width, height);
            }
            else if (!this._mth2b(0, mth1b, this._fld9, this._fld6, this._fld7)) {
                return false;
            }
            mth1b.flush();
            System.gc();
            return true;
        }
        return false;
    }
    
    boolean _mth0b() {
        this._fld6e = 1;
        while (this.getParameter("image" + String.valueOf(this._fld6e)) != null) {
            ++this._fld6e;
        }
        --this._fld6e;
        if (this._fld6e >= 1) {
            final String[] array = new String[this._fld6e];
            this._fld8 = new int[this._fld6f * this._fld6f * this._fld6e];
            final Image[] array2 = new Image[this._fld6e];
            array2[0] = null;
            for (int i = 0; i < this._fld6e; ++i) {
                array[i] = this.getParameter("image" + String.valueOf(i + 1));
            }
            int n = 0;
            final int n2 = this._fld6f * this._fld6f;
            for (int j = 0; j < this._fld6e; ++j) {
                array2[j] = this._mth1b(array[j], j + 1);
                if (array2[j] == null) {
                    this.showStatus("Error loading image ");
                    return false;
                }
                final int width = array2[j].getWidth(this);
                final int height = array2[j].getHeight(this);
                if (width != this._fld6f || height != this._fld6f) {
                    final int[] array3 = new int[width * height];
                    if (!this._mth2b(0, array2[j], array3, width, height)) {
                        return false;
                    }
                    this._fld8 = this._mth8(n, this._fld8, this._fld6f, this._fld6f, array3, width, height);
                }
                else if (!this._mth2b(n, array2[j], this._fld8, this._fld6f, this._fld6f)) {
                    return false;
                }
                this._fld2k = new URL[this._fld6e];
                this._fld3k = new String[this._fld6e];
                this._fld4k = new String[this._fld6e];
                array2[j].flush();
                array2[j] = null;
                System.gc();
                n += n2;
            }
            return true;
        }
        while (true) {
            this.showStatus("Error, at least 1 images required");
        }
    }
    
    Image _mth1b(final String s, final int n) {
        this.showStatus("Loading Images ...");
        final MediaTracker mediaTracker = new MediaTracker(this);
        URL url = null;
        try {
            url = new URL(this.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {}
        Image image;
        try {
            image = this.getImage(url);
            mediaTracker.addImage(image, n);
            mediaTracker.waitForID(n);
        }
        catch (InterruptedException ex2) {
            image = null;
        }
        if (mediaTracker.isErrorID(n)) {
            image = null;
        }
        return image;
    }
    
    boolean _mth2b(final int n, final Image image, final int[] array, final int n2, final int n3) {
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, n2, n3, array, n, n2);
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
    
    Color _mth3b(final String s, final Color color) {
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
    
    void _mth4b() {
        String parameter = this.getParameter("speed");
        if (parameter == null) {
            parameter = "2";
        }
        this._fld9e = Integer.valueOf(parameter);
        this._fld9e = ((this._fld9e >= 1) ? ((this._fld9e <= 8) ? this._fld9e : 8) : 1);
        this._fld7f = this._mth3b("bgcolor", new Color(0)).getRGB();
        String parameter2 = this.getParameter("distance");
        if (parameter2 == null) {
            parameter2 = "2";
        }
        this._fld0k = Integer.valueOf(parameter2);
        this._fld0k = ((this._fld0k >= 1) ? ((this._fld0k <= 3) ? this._fld0k : 3) : 1);
        String parameter3 = this.getParameter("speedx");
        if (parameter3 == null) {
            parameter3 = "3";
        }
        this._fld8j = Integer.valueOf(parameter3);
        this._fld8j = ((this._fld8j >= -4) ? ((this._fld8j <= 4) ? this._fld8j : 4) : -4);
        this._fld6j = -(this._fld8j / 50.0);
        String parameter4 = this.getParameter("speedy");
        if (parameter4 == null) {
            parameter4 = "2";
        }
        this._fld9j = Integer.valueOf(parameter4);
        this._fld9j = ((this._fld9j >= -4) ? ((this._fld9j <= 4) ? this._fld9j : 4) : -4);
        this._fld7j = -(this._fld9j / 50.0);
        String parameter5 = this.getParameter("pause");
        if (parameter5 == null) {
            parameter5 = "1";
        }
        this._fld4f = Integer.valueOf(parameter5);
        this._fld4f = ((this._fld4f >= 0) ? ((this._fld4f <= 8) ? this._fld4f : 8) : 0);
        this._fld4f *= 50;
        final String parameter6 = this.getParameter("interactive");
        if (parameter6 == null) {
            this._fld4e = 1;
        }
        else if (parameter6.equalsIgnoreCase("IN")) {
            this._fld4e = 0;
        }
        else if (parameter6.equalsIgnoreCase("OUT")) {
            this._fld4e = 1;
        }
        else {
            this._fld4e = 2;
        }
        for (int i = 1; i <= this._fld6e; ++i) {
            this._fld4k[i - 1] = this._fld5b;
        }
        final String parameter7 = this.getParameter("regkey");
        if (parameter7 != null) {
            this._fld1k = parameter7;
            for (int j = 1; j <= this._fld6e; ++j) {
                final String parameter8 = this.getParameter("reglink" + j);
                if (parameter8 != null) {
                    try {
                        this._fld2k[j - 1] = new URL("http://" + parameter8);
                    }
                    catch (MalformedURLException ex) {
                        this._fld2k[j - 1] = null;
                    }
                    final String parameter9 = this.getParameter("regtarget" + j);
                    if (parameter9 != null) {
                        this._fld3k[j - 1] = parameter9;
                    }
                }
                final String parameter10 = this.getParameter("regstatusmsg" + j);
                if (parameter10 != null) {
                    this._fld4k[j - 1] = parameter10;
                }
            }
        }
    }
    
    void _mth5b() {
        this._fld0f = 0;
        this._fld7e = 0;
        this._fld5f = new int[this._fld6 * this._fld7];
        this._fld4f *= this._fld9e;
        this._fld2f = this._fld4f + this._fld4f + (this._fld4f << 1) + 510;
        this._fld3f = this._fld2f >> 1;
        this._fld1f = new int[this._fld2f];
        for (int i = 0; i < this._fld4f; ++i) {
            this._fld1f[i] = 0;
        }
        for (int j = this._fld4f; j < 255 + this._fld4f; ++j) {
            this._fld1f[j] = j - this._fld4f;
        }
        for (int k = 0; k < this._fld4f + this._fld4f; ++k) {
            this._fld1f[this._fld4f + 255 + k] = 255;
        }
        for (int l = 0; l < 255; ++l) {
            this._fld1f[this._fld4f + this._fld4f + this._fld4f + 255 + l] = 255 - l;
        }
        for (int n = 0; n < this._fld4f; ++n) {
            this._fld1f[this._fld4f + this._fld4f + this._fld4f + 255 + 255 + n] = 0;
        }
        this._mth3c();
        this._fld5j = new int[this._fld6f * this._fld6f];
        for (int n2 = 0; n2 < this._fld6f * this._fld6f; ++n2) {
            this._fld5j[n2] = (this._fld5j[n2] = this._fld8[n2]);
        }
        this._fld8e = 0;
    }
    
    void _mth6b() {
        if (this._fld0f > 0) {
            if (this._fld0f < this._fld3f) {
                this._fld0f += this._fld9e;
                return;
            }
            this._fld0f -= this._fld9e;
        }
    }
    
    void _mth7b() {
        if (this._fld4e == 0) {
            if (this._fld5e) {
                this._fld0f += this._fld9e;
            }
            else {
                this._mth6b();
            }
        }
        else if (this._fld4e == 1) {
            if (this._fld5e) {
                this._mth6b();
            }
            else {
                this._fld0f += this._fld9e;
            }
        }
        else {
            this._fld0f += this._fld9e;
        }
        final int n = this._fld6f * this._fld6f;
        final int n2 = this._fld6f * this._fld6f * this._fld6e;
        if (this._fld0f >= this._fld2f) {
            this._fld0f = 0;
            this._fld7e = (this._fld7e + 1) % this._fld6e;
            this._fld8e = (this._fld8e + n) % n2;
            for (int i = 0; i < this._fld6f * this._fld6f; ++i) {
                this._fld5j[i] = this._fld8[i + this._fld8e];
            }
        }
        this._mth4c();
        this._mth8b(this._fld1f[this._fld0f], this._fld0b, this._fld9, this._fld5f);
        this._fld2b = this.createImage(this._fld1b);
    }
    
    void _mth8b(final int n, final int[] array, final int[] array2, final int[] array3) {
        for (int n2 = this._fld6 * this._fld7, i = 0; i < n2; ++i) {
            final int n3 = array2[i] >> 16 & 0xFF;
            final int n4 = n3 + (((array3[i] >> 16 & 0xFF) - n3) * n >> 8);
            final int n5 = array2[i] >> 8 & 0xFF;
            final int n6 = n5 + (((array3[i] >> 8 & 0xFF) - n5) * n >> 8);
            final int n7 = array2[i] & 0xFF;
            array[i] = (0xFF000000 | n4 << 16 | n6 << 8 | n7 + (((array3[i] & 0xFF) - n7) * n >> 8));
        }
    }
    
    void _mth9b(final int n, final int n2, final int n3, final int n4) {
        final double n5 = n * this._fld1i + n2 * this._fld2i + n3 * this._fld3i;
        final double n6 = n * this._fld4i + n2 * this._fld5i + n3 * this._fld6i;
        final double n7 = n * this._fld7i + n2 * this._fld8i + n3 * this._fld9i;
        this._fld0j[n4] = (int)(n5 * this._fld3g / (n7 + this._fld3g) * this._fld4g) + this._fld3j;
        this._fld1j[n4] = (int)(n6 * this._fld3g / (n7 + this._fld3g) * this._fld4g) + this._fld4j;
        this._fld2j[n4] = (int)n7;
    }
    
    void _mth0c(final int[] array, int n, int n2, final int n3, int n4, int n5, int n6, int n7) {
        if (n > n2) {
            final int n8 = n;
            n = n2;
            n2 = n8;
            final int n9 = n4;
            n4 = n6;
            n6 = n9;
            final int n10 = n5;
            n5 = n7;
            n7 = n10;
        }
        final int n11 = n2 - n + 1;
        final int n12;
        if ((n12 = n) < 1) {
            n = 1;
        }
        if (n2 > this._fld6 - 1) {
            n2 = this._fld6 - 1;
        }
        final int n13 = n2 - n + 1;
        if (n13 < 1) {
            return;
        }
        final int n14 = (n6 - n4 << 8) / n11;
        final int n15 = (n7 - n5 << 8) / n11;
        int n16 = (n4 << 8) + (n - n12) * n14;
        int n17 = (n5 << 8) + (n - n12) * n15;
        int n18 = n3 * this._fld6 + n;
        for (int i = 0; i < n13; ++i) {
            final int n19 = array[(n17 >> 8) * this._fld6f + (n16 >> 8)];
            this._fld5f[n18++] = (0xFF000000 | (n19 >> 16 & 0xFF) << 16 | (n19 >> 8 & 0xFF) << 8 | (n19 & 0xFF));
            n16 += n14;
            n17 += n15;
        }
    }
    
    void _mth1c(final int[] array, int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8) {
        if (n4 < n2) {
            final int n9 = n2;
            n2 = n4;
            n4 = n9;
            final int n10 = n;
            n = n3;
            n3 = n10;
            final int n11 = n6;
            n6 = n8;
            n8 = n11;
            final int n12 = n5;
            n5 = n7;
            n7 = n12;
        }
        final int n13 = n4 - n2 + 1;
        final int n14 = (n3 - n << 16) / n13;
        int n15 = n << 16;
        final int n16 = (n7 - n5 << 16) / n13;
        int n17 = n5 << 16;
        final int n18 = (n8 - n6 << 16) / n13;
        int n19 = n6 << 16;
        if (n4 > this._fld7 - 1) {
            n4 = this._fld7 - 1;
        }
        for (int i = n2; i < n4; ++i) {
            if (this.xp[i] == 10000) {
                if (i > 1) {
                    this.xp[i] = n15 >> 16;
                    this._fld8f[i] = n17 >> 16;
                    this._fld9f[i] = n19 >> 16;
                }
            }
            else if (i > 1) {
                this._mth0c(array, this.xp[i], n15 >> 16, i, this._fld8f[i], this._fld9f[i], n17 >> 16, n19 >> 16);
                this.xp[i] = 10000;
            }
            n15 += n14;
            n17 += n16;
            n19 += n18;
        }
    }
    
    void _mth2c(final int[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final int n11, final int n12, final int n13, final int n14, final int n15, final int n16) {
        this._mth1c(array, n, n2, n3, n4, n9, n10, n11, n12);
        this._mth1c(array, n3, n4, n5, n6, n11, n12, n13, n14);
        this._mth1c(array, n5, n6, n7, n8, n13, n14, n15, n16);
        this._mth1c(array, n7, n8, n, n2, n15, n16, n9, n10);
    }
    
    void _mth3c() {
        this.xp = new int[this._fld7];
        this._fld8f = new int[this._fld7];
        this._fld9f = new int[this._fld7];
        this._fld0j = new int[10];
        this._fld1j = new int[10];
        this._fld2j = new int[10];
        this._fld3j = this._fld6 >> 1;
        this._fld4j = this._fld7 >> 1;
        for (int i = 0; i < this._fld7; ++i) {
            this.xp[i] = 10000;
        }
        this._fld3g = 1650.0;
        this._fld4g = Math.min(this._fld6, this._fld7) / 280.0 - this._fld0k * (Math.min(this._fld6, this._fld7) / 1200.0);
        this._fld3j = this._fld6 >> 1;
        this._fld4j = this._fld7 >> 1;
    }
    
    void _mth4c() {
        this._fld5g = Math.sin(this._fld0g);
        this._fld6g = Math.sin(this._fld1g);
        this._fld7g = Math.sin(this._fld2g);
        this._fld8g = Math.cos(this._fld0g);
        this._fld9g = Math.cos(this._fld1g);
        this._fld0h = Math.cos(this._fld2g);
        this._fld1h = this._fld5g * this._fld6g;
        this._fld2h = this._fld1h * this._fld0h;
        this._fld3h = this._fld1h * this._fld7g;
        this._fld4h = this._fld8g * this._fld7g;
        this._fld5h = this._fld8g * this._fld0h;
        this._fld6h = this._fld8g * this._fld6g;
        this._fld7h = this._fld6h * this._fld0h;
        this._fld8h = this._fld6h * this._fld7g;
        this._fld9h = this._fld5g * this._fld7g;
        this._fld0i = this._fld5g * this._fld0h;
        this._fld1i = this._fld9g * this._fld0h;
        this._fld2i = this._fld2h - this._fld4h;
        this._fld3i = this._fld7h + this._fld9h;
        this._fld4i = this._fld9g * this._fld7g;
        this._fld5i = this._fld3h + this._fld5h;
        this._fld6i = this._fld8h - this._fld0i;
        this._fld7i = -this._fld6g;
        this._fld8i = this._fld5g * this._fld9g;
        this._fld9i = this._fld8g * this._fld9g;
        this._mth9b(-100, 100, 100, 1);
        this._mth9b(100, 100, 100, 2);
        this._mth9b(100, -100, 100, 3);
        this._mth9b(-100, -100, 100, 4);
        this._mth9b(-100, -100, -100, 5);
        this._mth9b(100, -100, -100, 6);
        this._mth9b(100, 100, -100, 7);
        this._mth9b(-100, 100, -100, 8);
        this._fld1g += this._fld6j;
        this._fld0g += this._fld7j;
        for (int i = 0; i < this._fld6 * this._fld7; ++i) {
            this._fld5f[i] = this._fld9[i];
        }
        final int n = this._fld6f - 2;
        final int n2 = this._fld0j[1];
        final int n3 = this._fld1j[1];
        if ((n3 - this._fld1j[3]) * (this._fld0j[2] - n2) - (n2 - this._fld0j[3]) * (this._fld1j[2] - n3) < 0) {
            this._mth2c(this._fld5j, this._fld0j[2], this._fld1j[2], this._fld0j[1], this._fld1j[1], this._fld0j[4], this._fld1j[4], this._fld0j[3], this._fld1j[3], 0, n, n, n, n, 0, 0, 0);
        }
        final int n4 = this._fld0j[5];
        final int n5 = this._fld1j[5];
        if ((n5 - this._fld1j[7]) * (this._fld0j[6] - n4) - (n4 - this._fld0j[7]) * (this._fld1j[6] - n5) < 0) {
            this._mth2c(this._fld5j, this._fld0j[8], this._fld1j[8], this._fld0j[7], this._fld1j[7], this._fld0j[6], this._fld1j[6], this._fld0j[5], this._fld1j[5], 0, n, n, n, n, 0, 0, 0);
        }
        final int n6 = this._fld0j[8];
        final int n7 = this._fld1j[8];
        if ((n7 - this._fld1j[2]) * (this._fld0j[7] - n6) - (n6 - this._fld0j[2]) * (this._fld1j[7] - n7) < 0) {
            this._mth2c(this._fld5j, this._fld0j[7], this._fld1j[7], this._fld0j[8], this._fld1j[8], this._fld0j[1], this._fld1j[1], this._fld0j[2], this._fld1j[2], 0, n, n, n, n, 0, 0, 0);
        }
        final int n8 = this._fld0j[2];
        final int n9 = this._fld1j[2];
        if ((n9 - this._fld1j[6]) * (this._fld0j[7] - n8) - (n8 - this._fld0j[6]) * (this._fld1j[7] - n9) < 0) {
            this._mth2c(this._fld5j, this._fld0j[7], this._fld1j[7], this._fld0j[2], this._fld1j[2], this._fld0j[3], this._fld1j[3], this._fld0j[6], this._fld1j[6], 0, n, n, n, n, 0, 0, 0);
        }
        final int n10 = this._fld0j[4];
        final int n11 = this._fld1j[4];
        if ((n11 - this._fld1j[6]) * (this._fld0j[3] - n10) - (n10 - this._fld0j[6]) * (this._fld1j[3] - n11) < 0) {
            this._mth2c(this._fld5j, this._fld0j[3], this._fld1j[3], this._fld0j[4], this._fld1j[4], this._fld0j[5], this._fld1j[5], this._fld0j[6], this._fld1j[6], 0, n, n, n, n, 0, 0, 0);
        }
        final int n12 = this._fld0j[8];
        final int n13 = this._fld1j[8];
        if ((n13 - this._fld1j[4]) * (this._fld0j[1] - n12) - (n12 - this._fld0j[4]) * (this._fld1j[1] - n13) < 0) {
            this._mth2c(this._fld5j, this._fld0j[1], this._fld1j[1], this._fld0j[8], this._fld1j[8], this._fld0j[5], this._fld1j[5], this._fld0j[4], this._fld1j[4], 0, n, n, n, n, 0, 0, 0);
        }
    }
    
    void _mth5c() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this._fld1k.length() > 9) {
            final int n = this._fld1k.length() - 9;
            final int n2 = n + 9;
            this._fld7k = new byte[n];
            this._fld1k.getBytes(1, n + 1, this._fld7k, 0);
            this._fld8k = new byte[n2];
            this._fld1k.getBytes(0, n2, this._fld8k, 0);
            int n3 = n % 7;
            final int n4 = n % 3;
            for (int i = 0; i < n; ++i) {
                final byte b = this._fld7k[i];
                final byte b2 = (byte)(b + n3);
                if (b >= 48 && b <= 57) {
                    this._fld7k[i] = ((b2 <= 57) ? b2 : ((byte)(b2 - 10)));
                }
                else if (b >= 65 && b <= 90) {
                    this._fld7k[i] = ((b2 <= 90) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b >= 97 && b <= 122) {
                    this._fld7k[i] = ((b2 <= 122) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b == 42) {
                    this._fld7k[i] = 45;
                }
                else if (b == 45) {
                    this._fld7k[i] = 46;
                }
                n3 = (n3 + n4) % 7;
            }
            int n5 = n % 7;
            final int n6 = n % 3;
            for (int j = 0; j < n; ++j) {
                final byte b3 = this._fld7k[j];
                final byte b4 = (byte)(b3 - n5);
                if (b3 >= 48 && b3 <= 57) {
                    this._fld8k[j + 1] = ((b4 >= 48) ? b4 : ((byte)(b4 + 10)));
                }
                else if (b3 >= 65 && b3 <= 90) {
                    this._fld8k[j + 1] = ((b4 >= 65) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 >= 97 && b3 <= 122) {
                    this._fld8k[j + 1] = ((b4 >= 97) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 == 45) {
                    this._fld8k[j + 1] = 42;
                }
                else if (b3 == 46) {
                    this._fld8k[j + 1] = 45;
                }
                n5 = (n5 + n6) % 7;
            }
            byte[] array;
            if (n > 4) {
                array = new byte[n - 4];
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld7k[k + 4];
                }
            }
            else {
                array = new byte[] { 0 };
            }
            if (this._fld8k[0] == this._fld8k[n >> 1] && this._fld8k[1 + n] == this._fld8k[1] && this._fld8k[1 + n + 1] == this._fld8k[n >> 1] && this._fld8k[1 + n + 2] == (byte)(97 + n5) && this._fld8k[1 + n + 3] == 45 && this._fld8k[1 + n + 4] == (byte)(122 - n6) && this._fld8k[1 + n + 5] == (byte)(110 + n5) && this._fld8k[1 + n + 6] == this._fld8k[1] && this._fld8k[1 + n + 7] == this._fld8k[n] && (host.equalsIgnoreCase(new String(this._fld7k, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this._fld6k = true;
            }
        }
        try {
            this._fld5k = new URL("http://" + this._fld9b);
        }
        catch (MalformedURLException ex) {
            this._fld5k = null;
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld6k) {
            this.getAppletContext().showDocument(this._fld5k, "_blank");
        }
        else if (this._fld2k[this._fld7e] != null) {
            if (this._fld3k[this._fld7e] != null) {
                this.getAppletContext().showDocument(this._fld2k[this._fld7e], this._fld3k[this._fld7e]);
            }
            else {
                this.getAppletContext().showDocument(this._fld2k[this._fld7e]);
            }
        }
        return true;
    }
    
    void _mth6c() {
        int fld2d = 0;
        do {
            ++fld2d;
        } while (this.getParameter("overtext" + fld2d) != null);
        if (--fld2d > 0) {
            this._fld2c = true;
            this._fld2d = fld2d;
            this._fld9k = new String[this._fld2d];
            this._fld0l = new Color[this._fld2d];
            this._fld1l = new Color[this._fld2d];
            this._fld2l = new Font[this._fld2d];
            this._fld3l = new FontMetrics[this._fld2d];
            this._fld4l = new String[this._fld2d];
            this._fld5l = new int[this._fld2d];
            this._fld6l = new int[this._fld2d];
            for (int i = 0; i < this._fld2d; ++i) {
                this._fld9k[i] = this.getParameter("overtext" + String.valueOf(i + 1));
                this._fld0l[i] = this._mth3b("overtextcol" + String.valueOf(i + 1), new Color(16777215));
                this._fld1l[i] = this._mth3b("overtextcols" + String.valueOf(i + 1), new Color(0));
                final String parameter = this.getParameter("overtexty" + String.valueOf(i + 1));
                if (parameter == null) {
                    this._fld6l[i] = 10;
                }
                else {
                    this._fld6l[i] = Integer.parseInt(parameter);
                }
                String parameter2 = this.getParameter("overTextFont" + String.valueOf(i + 1));
                if (parameter2 == null) {
                    parameter2 = "Helvetica";
                }
                final String parameter3 = this.getParameter("overTextStyle" + String.valueOf(i + 1));
                int n;
                if (parameter3 == null) {
                    n = 0;
                }
                else if (parameter3.equalsIgnoreCase("PLAIN")) {
                    n = 0;
                }
                else if (parameter3.equalsIgnoreCase("BOLD")) {
                    n = 1;
                }
                else if (parameter3.equalsIgnoreCase("ITALIC")) {
                    n = 2;
                }
                else if (parameter3.equalsIgnoreCase("BOLD ITALIC")) {
                    n = 3;
                }
                else {
                    n = 0;
                }
                final String parameter4 = this.getParameter("overTextSize" + String.valueOf(i + 1));
                int int1;
                if (parameter4 == null) {
                    int1 = 24;
                }
                else {
                    int1 = Integer.parseInt(parameter4);
                }
                this._fld2l[i] = new Font(parameter2, n, int1);
                this._fld3l[i] = this._fld5c.getFontMetrics(this._fld2l[i]);
                this._fld4l[i] = this.getParameter("overTextType" + String.valueOf(i + 1));
                if (this._fld4l[i] == null) {
                    this._fld4l[i] = "scrollleft";
                }
                final String parameter5 = this.getParameter("overtextspeed" + String.valueOf(i + 1));
                if (parameter5 == null) {
                    this._fld5l[i] = 2;
                }
                else {
                    this._fld5l[i] = Integer.valueOf(parameter5);
                    if (this._fld5l[i] < 1 || this._fld5l[i] > 4) {
                        this._fld5l[i] = 2;
                    }
                }
            }
            this._mth7c();
        }
    }
    
    void _mth7c() {
        this._fld5d = this._fld3l[this._fld1d].stringWidth(this._fld9k[this._fld1d]);
        this._fld6d = this._fld3l[this._fld1d].getHeight();
        if (this._fld4l[this._fld1d].equalsIgnoreCase("scrolldown")) {
            this._fld3d = this._fld6 - this._fld5d >> 1;
            this._fld4d = 0;
            return;
        }
        if (this._fld4l[this._fld1d].equalsIgnoreCase("scrollup")) {
            this._fld3d = this._fld6 - this._fld5d >> 1;
            this._fld4d = this._fld7 + this._fld6d;
            return;
        }
        if (this._fld4l[this._fld1d].equalsIgnoreCase("scrollright")) {
            this._fld3d = -this._fld5d;
            this._fld4d = this._fld6l[this._fld1d] + (this._fld6d >> 1) + (this._fld6d >> 3);
            return;
        }
        this._fld3d = this._fld6;
        this._fld4d = this._fld6l[this._fld1d] + (this._fld6d >> 1) + (this._fld6d >> 3);
    }
    
    void _mth8c(final Graphics graphics) {
        graphics.setFont(this._fld2l[this._fld1d]);
        graphics.setColor(this._fld1l[this._fld1d]);
        graphics.drawString(this._fld9k[this._fld1d], this._fld3d + 1, this._fld4d + 1);
        graphics.setColor(this._fld0l[this._fld1d]);
        graphics.drawString(this._fld9k[this._fld1d], this._fld3d, this._fld4d);
        if (this._fld4l[this._fld1d].equalsIgnoreCase("scrolldown")) {
            this._fld4d += this._fld5l[this._fld1d];
        }
        else if (this._fld4l[this._fld1d].equalsIgnoreCase("scrollup")) {
            this._fld4d -= this._fld5l[this._fld1d];
        }
        else if (this._fld4l[this._fld1d].equalsIgnoreCase("scrollright")) {
            this._fld3d += this._fld5l[this._fld1d];
        }
        else {
            this._fld3d -= this._fld5l[this._fld1d];
        }
        if (this._fld4d > this._fld7 + this._fld6d || this._fld4d < -this._fld6d || this._fld3d > this._fld6 || this._fld3d < -this._fld5d) {
            ++this._fld1d;
            if (this._fld1d >= this._fld2d) {
                this._fld1d = 0;
            }
            this._mth7c();
        }
    }
    
    void _mth9c() {
        final String parameter = this.getParameter("OverImage");
        if (parameter != null) {
            this._fld6c = this._mth1b(parameter, 0);
        }
        if (this._fld6c != null) {
            this._fld3c = true;
            this._fld9c = this._fld6c.getWidth(this);
            this._fld0d = this._fld6c.getHeight(this);
            final String parameter2 = this.getParameter("OverImageX");
            if (parameter2 == null) {
                this._fld7c = (this._fld6 >> 1) - (this._fld9c >> 1);
            }
            else {
                this._fld7c = Integer.valueOf(parameter2);
            }
            final String parameter3 = this.getParameter("OverImageY");
            if (parameter3 == null) {
                this._fld8c = (this._fld7 >> 1) - (this._fld0d >> 1);
                return;
            }
            this._fld8c = Integer.valueOf(parameter3);
        }
    }
    
    public DS_FadeToCube() {
        this._fld5 = new Font("Helvetica", 1, 12);
        this._fld5b = "Applet by Dario Sciacca";
        this._fld6b = "dario@dseffects.com";
        this._fld9b = "www.dseffects.com";
        this._fld0c = "Don't remove Dario Sciacca's credits line";
        this._fld1c = this._fld5b + " (" + this._fld9b + ")";
        this._fld2c = false;
        this._fld3c = false;
        this._fld2e = true;
        this._fld3e = "FadeToCube started";
        this._fld5e = false;
        this._fld1k = "";
        this._fld6k = false;
    }
}
