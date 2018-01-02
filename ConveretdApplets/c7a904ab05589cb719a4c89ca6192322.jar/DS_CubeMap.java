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

public class DS_CubeMap extends Applet implements Runnable
{
    Thread _fld0;
    int _fld1;
    Font _fld4;
    int _fld5;
    int _fld6;
    int[] _fld7;
    int[] _fld8;
    MemoryImageSource _fld9;
    Image _fld0b;
    int _fld1b;
    long _fld2b;
    String _fld3b;
    String _fld4b;
    int _fld5b;
    int _fld6b;
    String _fld7b;
    String _fld8b;
    String _fld9b;
    boolean _fld0c;
    boolean _fld1c;
    Image _fld2c;
    Graphics _fld3c;
    Image _fld4c;
    int _fld5c;
    int _fld6c;
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
    String _fld1e;
    boolean _fld2e;
    boolean _fld3e;
    int _fld4e;
    int _fld5e;
    int[] xp;
    int[] _fld6e;
    int[] _fld7e;
    double _fld8e;
    double _fld9e;
    double _fld0f;
    double _fld1f;
    double _fld2f;
    double _fld3f;
    double _fld4f;
    double _fld5f;
    double _fld6f;
    double _fld7f;
    double _fld8f;
    double _fld9f;
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
    int[] _fld8h;
    int[] _fld9h;
    int[] _fld0i;
    int _fld1i;
    int _fld2i;
    int[] _fld3i;
    int[] _fld4i;
    int[] _fld5i;
    int[] _fld6i;
    int[] _fld7i;
    int[] _fld8i;
    double _fld9i;
    double _fld0j;
    int _fld1j;
    int _fld2j;
    int _fld3j;
    String _fld4j;
    URL _fld5j;
    String _fld6j;
    String _fld7j;
    URL _fld8j;
    boolean _fld9j;
    byte[] _fld0k;
    byte[] _fld1k;
    String[] _fld2k;
    Color[] _fld3k;
    Color[] _fld4k;
    Font[] _fld5k;
    FontMetrics[] _fld6k;
    String[] _fld7k;
    int[] _fld8k;
    int[] _fld9k;
    
    public String getAppletInfo() {
        return "DS CubeMap v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
    }
    
    public void init() {
        this._mth4();
        this.showStatus("Please wait ...");
        this._fld6b = this.getFontMetrics(this._fld4).stringWidth(this._fld7b);
        this._fld5 = this.size().width;
        this._fld6 = this.size().height;
        this._mth5();
        this._fld8 = new int[this._fld5 * this._fld6];
        this._fld9 = new MemoryImageSource(this._fld5, this._fld6, this._fld8, 0, this._fld5);
        this._fld2c = this.createImage(this._fld5, this._fld6);
        this._fld3c = this._fld2c.getGraphics();
        this._mth2c();
        this._mth5c();
        if (!this._fld0c && !this._fld1c) {
            this._fld5b = 0;
        }
        else if (this._fld0c && !this._fld1c) {
            this._fld5b = 1;
        }
        else if (!this._fld0c && this._fld1c) {
            this._fld5b = 2;
        }
        else {
            this._fld5b = 3;
        }
        this._mth4b();
        this._mth1c();
        this._fld7 = new int[this._fld5 * this._fld6];
        if (!this._mth8()) {
            for (int i = 0; i < this._fld5 * this._fld6; ++i) {
                this._fld7[i] = this._fld5e;
            }
        }
        this._mth9();
        this._mth6();
        this._mth9b();
        if (this._fld7d == -16777216) {
            this._fld1 = 1;
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
        this.showStatus(this._fld1e);
        System.gc();
        final Graphics graphics = this.getGraphics();
        this._fld2b = System.currentTimeMillis();
        while (this._fld0 != null) {
            if (this._fld1 == 1) {
                this._mth0c();
            }
            this._mth1(graphics);
            this._mth0();
            if (this._fld1b++ > 10) {
                System.gc();
                this._fld1b = 0;
            }
        }
    }
    
    synchronized void _mth0() {
        Thread.yield();
        this.getToolkit().sync();
        final long n = 10L - (System.currentTimeMillis() - this._fld2b);
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
        this._fld2b = System.currentTimeMillis();
    }
    
    public void update(final Graphics graphics) {
    }
    
    void _mth1(final Graphics graphics) {
        final int n = this._fld5 >> 1;
        final int n2 = this._fld6 >> 1;
        if (this._fld1 == 0) {
            graphics.drawString("Error ...", 10, 10);
            return;
        }
        if (this._fld0b != null) {
            if (this._fld5b == 0) {
                this._fld3c.drawImage(this._fld0b, 0, 0, this);
            }
            else if (this._fld5b == 1) {
                this._fld3c.drawImage(this._fld0b, 0, 0, this);
                this._mth4c(this._fld3c);
            }
            else if (this._fld5b == 2) {
                this._fld3c.drawImage(this._fld0b, 0, 0, this);
                this._fld3c.drawImage(this._fld4c, this._fld5c, this._fld6c, this);
            }
            else {
                this._fld3c.drawImage(this._fld0b, 0, 0, this);
                this._mth4c(this._fld3c);
                this._fld3c.drawImage(this._fld4c, this._fld5c, this._fld6c, this);
            }
        }
        if (this._fld3e && !this._fld9j) {
            this._fld3c.setColor(Color.white);
            this._fld3c.drawLine(n - 64, n2 - 8, n + 64, n2 - 8);
            this._fld3c.drawLine(n - 64, n2 + 8, n + 64, n2 + 8);
            this._fld3c.drawLine(n - 64, n2 - 8, n - 64, n2 + 8);
            this._fld3c.drawLine(n + 64, n2 - 8, n + 64, n2 + 8);
            this._fld3c.setColor(Color.blue);
            this._fld3c.fillRect(n - 63, n2 - 7, 127, 15);
            this._fld3c.setFont(this._fld4);
            this._fld3c.setColor(Color.yellow);
            this._fld3c.drawString(this._fld7b, n - (this._fld6b >> 1), n2 + 5);
        }
        graphics.drawImage(this._fld2c, 0, 0, this);
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this._fld7j);
        return this._fld3e = true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this._fld3e = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int fld8d, final int fld9d) {
        if (this._fld2e) {
            if (this._fld8d != fld8d) {
                this._fld9e -= 0.017453292519943295 * (fld8d - this._fld8d);
            }
            else if (this._fld9d != fld9d) {
                this._fld8e -= 0.017453292519943295 * (fld9d - this._fld9d);
            }
            this._fld8d = fld8d;
            this._fld9d = fld9d;
        }
        return this._fld3e = true;
    }
    
    void _mth4() {
        final String parameter = this.getParameter("credits");
        if (parameter != null) {
            if (parameter.equals(this._fld9b)) {
                this._fld5d = 1;
                return;
            }
            while (true) {
                this.showStatus(this._fld8b);
            }
        }
        else {
            while (true) {
                this.showStatus(this._fld8b);
            }
        }
    }
    
    void _mth5() {
        this._fld6d = 1;
        for (int i = 0; i < 11; ++i) {
            if (this._fld4b.charAt(i) == this._fld3b.charAt(i) || this._fld5d == 0) {
                while (true) {
                    this.showStatus(this._fld8b);
                }
            }
            else {}
        }
        this._fld9j = false;
    }
    
    void _mth6() {
        if (this._fld5d == 0 || this._fld6d == 0) {
            while (true) {
                this.showStatus(this._fld8b);
            }
        }
        else {
            for (int i = 0; i < 17; ++i) {
                if (this._fld4b.charAt(i) == this._fld7b.charAt(i)) {
                    while (true) {
                        this.showStatus(this._fld8b);
                    }
                }
                else {}
            }
            this._fld7d = -16777216;
            if (this._fld3b.charAt(1) == 'p' && this._fld3b.charAt(7) == 'b' && this._fld3b.charAt(21) == 'c' && this._fld3b.charAt(17) == 'c' && this._fld3b.charAt(12) == 'r' && this._fld3b.charAt(11) == 'a') {
                return;
            }
            while (true) {
                this.showStatus(this._fld8b);
            }
        }
    }
    
    int[] _mth7(final int[] array, final int n, final int n2, final int[] array2, final int n3, final int n4) {
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
    
    boolean _mth8() {
        final String parameter = this.getParameter("bgimage");
        if (parameter != null) {
            final Image mth1b = this._mth1b(parameter, 0);
            final int width = mth1b.getWidth(this);
            final int height = mth1b.getHeight(this);
            if (width != this._fld5 || height != this._fld6) {
                final int[] array = new int[width * height];
                if (!this._mth2b(mth1b, array, width, height)) {
                    return false;
                }
                this._fld7 = this._mth7(this._fld7, this._fld5, this._fld6, array, width, height);
            }
            else if (!this._mth2b(mth1b, this._fld7, this._fld5, this._fld6)) {
                return false;
            }
            mth1b.flush();
            System.gc();
            return true;
        }
        this.showStatus("Error loading image ");
        return false;
    }
    
    boolean _mth9() {
        this._fld0e = 1;
        while (this.getParameter("image" + String.valueOf(this._fld0e)) != null) {
            ++this._fld0e;
        }
        --this._fld0e;
        if (this._fld0e >= 1) {
            final String[] array = new String[this._fld0e];
            final Image[] array2 = new Image[this._fld0e];
            array2[0] = null;
            for (int i = 0; i < this._fld0e; ++i) {
                array[i] = this.getParameter("image" + String.valueOf(i + 1));
            }
            for (int j = 0; j < this._fld0e; ++j) {
                array2[j] = this._mth1b(array[j], j + 1);
                if (array2[j] == null) {
                    this.showStatus("Error loading image ");
                    return false;
                }
                if (j == 0) {
                    this._fld3i = new int[this._fld4e * this._fld4e];
                    if (!this._mth0b(array2[j], j, this._fld3i)) {
                        return false;
                    }
                }
                else if (j == 1) {
                    this._fld4i = new int[this._fld4e * this._fld4e];
                    if (!this._mth0b(array2[j], j, this._fld4i)) {
                        return false;
                    }
                }
                else if (j == 2) {
                    this._fld5i = new int[this._fld4e * this._fld4e];
                    if (!this._mth0b(array2[j], j, this._fld5i)) {
                        return false;
                    }
                }
                else if (j == 3) {
                    this._fld6i = new int[this._fld4e * this._fld4e];
                    if (!this._mth0b(array2[j], j, this._fld6i)) {
                        return false;
                    }
                }
                else if (j == 4) {
                    this._fld7i = new int[this._fld4e * this._fld4e];
                    if (!this._mth0b(array2[j], j, this._fld7i)) {
                        return false;
                    }
                }
                else if (j == 5) {
                    this._fld8i = new int[this._fld4e * this._fld4e];
                    if (!this._mth0b(array2[j], j, this._fld8i)) {
                        return false;
                    }
                }
            }
            return true;
        }
        while (true) {
            this.showStatus("Error, at least 1 image required");
        }
    }
    
    boolean _mth0b(final Image image, final int n, int[] mth7) {
        final int width = image.getWidth(this);
        final int height = image.getHeight(this);
        if (width != this._fld4e || height != this._fld4e) {
            final int[] array = new int[width * height];
            if (!this._mth2b(image, array, width, height)) {
                return false;
            }
            mth7 = this._mth7(mth7, this._fld4e, this._fld4e, array, width, height);
        }
        else if (!this._mth2b(image, mth7, this._fld4e, this._fld4e)) {
            return false;
        }
        image.flush();
        System.gc();
        return true;
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
    
    boolean _mth2b(final Image image, final int[] array, final int n, final int n2) {
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
        if (this._fld5 > 256 && this._fld6 > 256) {
            this._fld4e = 256;
        }
        else {
            this._fld4e = 128;
        }
        final String parameter = this.getParameter("interactive");
        if (parameter == null) {
            this._fld2e = true;
        }
        else if (parameter.equalsIgnoreCase("no")) {
            this._fld2e = false;
        }
        else {
            this._fld2e = true;
        }
        this._fld5e = this._mth3b("bgcolor", new Color(0)).getRGB();
        String parameter2 = this.getParameter("distance");
        if (parameter2 == null) {
            parameter2 = "2";
        }
        this._fld3j = Integer.valueOf(parameter2);
        this._fld3j = ((this._fld3j >= 1) ? ((this._fld3j <= 3) ? this._fld3j : 3) : 1);
        String parameter3 = this.getParameter("speedx");
        if (parameter3 == null) {
            parameter3 = "3";
        }
        this._fld1j = Integer.valueOf(parameter3);
        this._fld1j = ((this._fld1j >= -4) ? ((this._fld1j <= 4) ? this._fld1j : 4) : -4);
        this._fld9i = -(this._fld1j / 50.0);
        String parameter4 = this.getParameter("speedy");
        if (parameter4 == null) {
            parameter4 = "2";
        }
        this._fld2j = Integer.valueOf(parameter4);
        this._fld2j = ((this._fld2j >= -4) ? ((this._fld2j <= 4) ? this._fld2j : 4) : -4);
        this._fld0j = -(this._fld2j / 50.0);
        this._fld7j = this._fld3b;
        final String parameter5 = this.getParameter("regkey");
        if (parameter5 != null) {
            this._fld4j = parameter5;
            final String parameter6 = this.getParameter("reglink");
            if (parameter6 != null) {
                try {
                    this._fld5j = new URL("http://" + parameter6);
                }
                catch (MalformedURLException ex) {
                    this._fld5j = null;
                }
                final String parameter7 = this.getParameter("regtarget");
                if (parameter7 != null) {
                    this._fld6j = parameter7;
                }
            }
            final String parameter8 = this.getParameter("regstatusmsg");
            if (parameter8 != null) {
                this._fld7j = parameter8;
            }
        }
    }
    
    void _mth5b(final int n, final int n2, final int n3, final int n4) {
        final double n5 = n * this._fld9g + n2 * this._fld0h + n3 * this._fld1h;
        final double n6 = n * this._fld2h + n2 * this._fld3h + n3 * this._fld4h;
        final double n7 = n * this._fld5h + n2 * this._fld6h + n3 * this._fld7h;
        this._fld8h[n4] = (int)(n5 * this._fld1f / (n7 + this._fld1f) * this._fld2f) + this._fld1i;
        this._fld9h[n4] = (int)(n6 * this._fld1f / (n7 + this._fld1f) * this._fld2f) + this._fld2i;
        this._fld0i[n4] = (int)n7;
    }
    
    void _mth6b(final int[] array, int n, int n2, final int n3, int n4, int n5, int n6, int n7) {
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
        if (n2 > this._fld5 - 1) {
            n2 = this._fld5 - 1;
        }
        final int n13 = n2 - n + 1;
        if (n13 < 1) {
            return;
        }
        final int n14 = (n6 - n4 << 8) / n11;
        final int n15 = (n7 - n5 << 8) / n11;
        int n16 = (n4 << 8) + (n - n12) * n14;
        int n17 = (n5 << 8) + (n - n12) * n15;
        int n18 = n3 * this._fld5 + n;
        for (int i = 0; i < n13; ++i) {
            final int n19 = array[(n17 >> 8) * this._fld4e + (n16 >> 8)];
            this._fld8[n18++] = (0xFF000000 | (n19 >> 16 & 0xFF) << 16 | (n19 >> 8 & 0xFF) << 8 | (n19 & 0xFF));
            n16 += n14;
            n17 += n15;
        }
    }
    
    void _mth7b(final int[] array, int n, int n2, int n3, int n4, int n5, int n6, int n7, int n8) {
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
        if (n4 > this._fld6 - 1) {
            n4 = this._fld6 - 1;
        }
        for (int i = n2; i < n4; ++i) {
            if (this.xp[i] == 10000) {
                if (i > 1) {
                    this.xp[i] = n15 >> 16;
                    this._fld6e[i] = n17 >> 16;
                    this._fld7e[i] = n19 >> 16;
                }
            }
            else if (i > 1) {
                this._mth6b(array, this.xp[i], n15 >> 16, i, this._fld6e[i], this._fld7e[i], n17 >> 16, n19 >> 16);
                this.xp[i] = 10000;
            }
            n15 += n14;
            n17 += n16;
            n19 += n18;
        }
    }
    
    void _mth8b(final int[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final int n11, final int n12, final int n13, final int n14, final int n15, final int n16) {
        this._mth7b(array, n, n2, n3, n4, n9, n10, n11, n12);
        this._mth7b(array, n3, n4, n5, n6, n11, n12, n13, n14);
        this._mth7b(array, n5, n6, n7, n8, n13, n14, n15, n16);
        this._mth7b(array, n7, n8, n, n2, n15, n16, n9, n10);
    }
    
    void _mth9b() {
        this.xp = new int[this._fld6];
        this._fld6e = new int[this._fld6];
        this._fld7e = new int[this._fld6];
        this._fld8h = new int[10];
        this._fld9h = new int[10];
        this._fld0i = new int[10];
        this._fld1i = this._fld5 >> 1;
        this._fld2i = this._fld6 >> 1;
        for (int i = 0; i < this._fld6; ++i) {
            this.xp[i] = 10000;
        }
        this._fld1f = 1650.0;
        this._fld2f = Math.min(this._fld5, this._fld6) / 280.0 - this._fld3j * (Math.min(this._fld5, this._fld6) / 1200.0);
        this._fld1i = this._fld5 >> 1;
        this._fld2i = this._fld6 >> 1;
    }
    
    void _mth0c() {
        this._fld3f = Math.sin(this._fld8e);
        this._fld4f = Math.sin(this._fld9e);
        this._fld5f = Math.sin(this._fld0f);
        this._fld6f = Math.cos(this._fld8e);
        this._fld7f = Math.cos(this._fld9e);
        this._fld8f = Math.cos(this._fld0f);
        this._fld9f = this._fld3f * this._fld4f;
        this._fld0g = this._fld9f * this._fld8f;
        this._fld1g = this._fld9f * this._fld5f;
        this._fld2g = this._fld6f * this._fld5f;
        this._fld3g = this._fld6f * this._fld8f;
        this._fld4g = this._fld6f * this._fld4f;
        this._fld5g = this._fld4g * this._fld8f;
        this._fld6g = this._fld4g * this._fld5f;
        this._fld7g = this._fld3f * this._fld5f;
        this._fld8g = this._fld3f * this._fld8f;
        this._fld9g = this._fld7f * this._fld8f;
        this._fld0h = this._fld0g - this._fld2g;
        this._fld1h = this._fld5g + this._fld7g;
        this._fld2h = this._fld7f * this._fld5f;
        this._fld3h = this._fld1g + this._fld3g;
        this._fld4h = this._fld6g - this._fld8g;
        this._fld5h = -this._fld4f;
        this._fld6h = this._fld3f * this._fld7f;
        this._fld7h = this._fld6f * this._fld7f;
        this._mth5b(-100, 100, 100, 1);
        this._mth5b(100, 100, 100, 2);
        this._mth5b(100, -100, 100, 3);
        this._mth5b(-100, -100, 100, 4);
        this._mth5b(-100, -100, -100, 5);
        this._mth5b(100, -100, -100, 6);
        this._mth5b(100, 100, -100, 7);
        this._mth5b(-100, 100, -100, 8);
        if (!this._fld3e || !this._fld2e) {
            this._fld9e += this._fld9i;
            this._fld8e += this._fld0j;
        }
        for (int i = 0; i < this._fld5 * this._fld6; ++i) {
            this._fld8[i] = this._fld7[i];
        }
        final int n = this._fld4e - 2;
        final int n2 = this._fld8h[1];
        final int n3 = this._fld9h[1];
        if ((n3 - this._fld9h[3]) * (this._fld8h[2] - n2) - (n2 - this._fld8h[3]) * (this._fld9h[2] - n3) < 0) {
            this._mth8b(this._fld3i, this._fld8h[2], this._fld9h[2], this._fld8h[1], this._fld9h[1], this._fld8h[4], this._fld9h[4], this._fld8h[3], this._fld9h[3], 0, n, n, n, n, 0, 0, 0);
        }
        final int n4 = this._fld8h[5];
        final int n5 = this._fld9h[5];
        if ((n5 - this._fld9h[7]) * (this._fld8h[6] - n4) - (n4 - this._fld8h[7]) * (this._fld9h[6] - n5) < 0) {
            if (this._fld0e > 1) {
                this._mth8b(this._fld4i, this._fld8h[8], this._fld9h[8], this._fld8h[7], this._fld9h[7], this._fld8h[6], this._fld9h[6], this._fld8h[5], this._fld9h[5], 0, n, n, n, n, 0, 0, 0);
            }
            else {
                this._mth8b(this._fld3i, this._fld8h[8], this._fld9h[8], this._fld8h[7], this._fld9h[7], this._fld8h[6], this._fld9h[6], this._fld8h[5], this._fld9h[5], 0, n, n, n, n, 0, 0, 0);
            }
        }
        final int n6 = this._fld8h[8];
        final int n7 = this._fld9h[8];
        if ((n7 - this._fld9h[2]) * (this._fld8h[7] - n6) - (n6 - this._fld8h[2]) * (this._fld9h[7] - n7) < 0) {
            if (this._fld0e > 2) {
                this._mth8b(this._fld5i, this._fld8h[7], this._fld9h[7], this._fld8h[8], this._fld9h[8], this._fld8h[1], this._fld9h[1], this._fld8h[2], this._fld9h[2], 0, n, n, n, n, 0, 0, 0);
            }
            else {
                this._mth8b(this._fld3i, this._fld8h[7], this._fld9h[7], this._fld8h[8], this._fld9h[8], this._fld8h[1], this._fld9h[1], this._fld8h[2], this._fld9h[2], 0, n, n, n, n, 0, 0, 0);
            }
        }
        final int n8 = this._fld8h[2];
        final int n9 = this._fld9h[2];
        if ((n9 - this._fld9h[6]) * (this._fld8h[7] - n8) - (n8 - this._fld8h[6]) * (this._fld9h[7] - n9) < 0) {
            if (this._fld0e > 3) {
                this._mth8b(this._fld6i, this._fld8h[7], this._fld9h[7], this._fld8h[2], this._fld9h[2], this._fld8h[3], this._fld9h[3], this._fld8h[6], this._fld9h[6], 0, n, n, n, n, 0, 0, 0);
            }
            else {
                this._mth8b(this._fld3i, this._fld8h[7], this._fld9h[7], this._fld8h[2], this._fld9h[2], this._fld8h[3], this._fld9h[3], this._fld8h[6], this._fld9h[6], 0, n, n, n, n, 0, 0, 0);
            }
        }
        final int n10 = this._fld8h[4];
        final int n11 = this._fld9h[4];
        if ((n11 - this._fld9h[6]) * (this._fld8h[3] - n10) - (n10 - this._fld8h[6]) * (this._fld9h[3] - n11) < 0) {
            if (this._fld0e > 4) {
                this._mth8b(this._fld7i, this._fld8h[3], this._fld9h[3], this._fld8h[4], this._fld9h[4], this._fld8h[5], this._fld9h[5], this._fld8h[6], this._fld9h[6], 0, n, n, n, n, 0, 0, 0);
            }
            else {
                this._mth8b(this._fld3i, this._fld8h[3], this._fld9h[3], this._fld8h[4], this._fld9h[4], this._fld8h[5], this._fld9h[5], this._fld8h[6], this._fld9h[6], 0, n, n, n, n, 0, 0, 0);
            }
        }
        final int n12 = this._fld8h[8];
        final int n13 = this._fld9h[8];
        if ((n13 - this._fld9h[4]) * (this._fld8h[1] - n12) - (n12 - this._fld8h[4]) * (this._fld9h[1] - n13) < 0) {
            if (this._fld0e > 5) {
                this._mth8b(this._fld8i, this._fld8h[1], this._fld9h[1], this._fld8h[8], this._fld9h[8], this._fld8h[5], this._fld9h[5], this._fld8h[4], this._fld9h[4], 0, n, n, n, n, 0, 0, 0);
            }
            else {
                this._mth8b(this._fld3i, this._fld8h[1], this._fld9h[1], this._fld8h[8], this._fld9h[8], this._fld8h[5], this._fld9h[5], this._fld8h[4], this._fld9h[4], 0, n, n, n, n, 0, 0, 0);
            }
        }
        this._fld0b = this.createImage(this._fld9);
    }
    
    void _mth1c() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this._fld4j.length() > 9) {
            final int n = this._fld4j.length() - 9;
            final int n2 = n + 9;
            this._fld0k = new byte[n];
            this._fld4j.getBytes(1, n + 1, this._fld0k, 0);
            this._fld1k = new byte[n2];
            this._fld4j.getBytes(0, n2, this._fld1k, 0);
            int n3 = n % 7;
            final int n4 = n % 3;
            for (int i = 0; i < n; ++i) {
                final byte b = this._fld0k[i];
                final byte b2 = (byte)(b + n3);
                if (b >= 48 && b <= 57) {
                    this._fld0k[i] = ((b2 <= 57) ? b2 : ((byte)(b2 - 10)));
                }
                else if (b >= 65 && b <= 90) {
                    this._fld0k[i] = ((b2 <= 90) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b >= 97 && b <= 122) {
                    this._fld0k[i] = ((b2 <= 122) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b == 42) {
                    this._fld0k[i] = 45;
                }
                else if (b == 45) {
                    this._fld0k[i] = 46;
                }
                n3 = (n3 + n4) % 7;
            }
            int n5 = n % 7;
            final int n6 = n % 3;
            for (int j = 0; j < n; ++j) {
                final byte b3 = this._fld0k[j];
                final byte b4 = (byte)(b3 - n5);
                if (b3 >= 48 && b3 <= 57) {
                    this._fld1k[j + 1] = ((b4 >= 48) ? b4 : ((byte)(b4 + 10)));
                }
                else if (b3 >= 65 && b3 <= 90) {
                    this._fld1k[j + 1] = ((b4 >= 65) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 >= 97 && b3 <= 122) {
                    this._fld1k[j + 1] = ((b4 >= 97) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 == 45) {
                    this._fld1k[j + 1] = 42;
                }
                else if (b3 == 46) {
                    this._fld1k[j + 1] = 45;
                }
                n5 = (n5 + n6) % 7;
            }
            byte[] array;
            if (n > 4) {
                array = new byte[n - 4];
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld0k[k + 4];
                }
            }
            else {
                array = new byte[] { 0 };
            }
            if (this._fld1k[0] == this._fld1k[n >> 1] && this._fld1k[1 + n] == this._fld1k[1] && this._fld1k[1 + n + 1] == this._fld1k[n >> 1] && this._fld1k[1 + n + 2] == (byte)(97 + n5) && this._fld1k[1 + n + 3] == 45 && this._fld1k[1 + n + 4] == (byte)(122 - n6) && this._fld1k[1 + n + 5] == (byte)(110 + n5) && this._fld1k[1 + n + 6] == this._fld1k[1] && this._fld1k[1 + n + 7] == this._fld1k[n] && (host.equalsIgnoreCase(new String(this._fld0k, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this._fld9j = true;
            }
        }
        try {
            this._fld8j = new URL("http://" + this._fld7b);
        }
        catch (MalformedURLException ex) {
            this._fld8j = null;
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld9j) {
            this.getAppletContext().showDocument(this._fld8j, "_blank");
        }
        else if (this._fld5j != null) {
            if (this._fld6j != null) {
                this.getAppletContext().showDocument(this._fld5j, this._fld6j);
            }
            else {
                this.getAppletContext().showDocument(this._fld5j);
            }
        }
        return true;
    }
    
    void _mth2c() {
        int fld0d = 0;
        do {
            ++fld0d;
        } while (this.getParameter("overtext" + fld0d) != null);
        if (--fld0d > 0) {
            this._fld0c = true;
            this._fld0d = fld0d;
            this._fld2k = new String[this._fld0d];
            this._fld3k = new Color[this._fld0d];
            this._fld4k = new Color[this._fld0d];
            this._fld5k = new Font[this._fld0d];
            this._fld6k = new FontMetrics[this._fld0d];
            this._fld7k = new String[this._fld0d];
            this._fld8k = new int[this._fld0d];
            this._fld9k = new int[this._fld0d];
            for (int i = 0; i < this._fld0d; ++i) {
                this._fld2k[i] = this.getParameter("overtext" + String.valueOf(i + 1));
                this._fld3k[i] = this._mth3b("overtextcol" + String.valueOf(i + 1), new Color(16777215));
                this._fld4k[i] = this._mth3b("overtextcols" + String.valueOf(i + 1), new Color(0));
                final String parameter = this.getParameter("overtexty" + String.valueOf(i + 1));
                if (parameter == null) {
                    this._fld9k[i] = 10;
                }
                else {
                    this._fld9k[i] = Integer.parseInt(parameter);
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
                this._fld5k[i] = new Font(parameter2, n, int1);
                this._fld6k[i] = this._fld3c.getFontMetrics(this._fld5k[i]);
                this._fld7k[i] = this.getParameter("overTextType" + String.valueOf(i + 1));
                if (this._fld7k[i] == null) {
                    this._fld7k[i] = "scrollleft";
                }
                final String parameter5 = this.getParameter("overtextspeed" + String.valueOf(i + 1));
                if (parameter5 == null) {
                    this._fld8k[i] = 2;
                }
                else {
                    this._fld8k[i] = Integer.valueOf(parameter5);
                    if (this._fld8k[i] < 1 || this._fld8k[i] > 4) {
                        this._fld8k[i] = 2;
                    }
                }
            }
            this._mth3c();
        }
    }
    
    void _mth3c() {
        this._fld3d = this._fld6k[this._fld9c].stringWidth(this._fld2k[this._fld9c]);
        this._fld4d = this._fld6k[this._fld9c].getHeight();
        if (this._fld7k[this._fld9c].equalsIgnoreCase("scrolldown")) {
            this._fld1d = this._fld5 - this._fld3d >> 1;
            this._fld2d = 0;
            return;
        }
        if (this._fld7k[this._fld9c].equalsIgnoreCase("scrollup")) {
            this._fld1d = this._fld5 - this._fld3d >> 1;
            this._fld2d = this._fld6 + this._fld4d;
            return;
        }
        if (this._fld7k[this._fld9c].equalsIgnoreCase("scrollright")) {
            this._fld1d = -this._fld3d;
            this._fld2d = this._fld9k[this._fld9c] + (this._fld4d >> 1) + (this._fld4d >> 3);
            return;
        }
        this._fld1d = this._fld5;
        this._fld2d = this._fld9k[this._fld9c] + (this._fld4d >> 1) + (this._fld4d >> 3);
    }
    
    void _mth4c(final Graphics graphics) {
        graphics.setFont(this._fld5k[this._fld9c]);
        graphics.setColor(this._fld4k[this._fld9c]);
        graphics.drawString(this._fld2k[this._fld9c], this._fld1d + 1, this._fld2d + 1);
        graphics.setColor(this._fld3k[this._fld9c]);
        graphics.drawString(this._fld2k[this._fld9c], this._fld1d, this._fld2d);
        if (this._fld7k[this._fld9c].equalsIgnoreCase("scrolldown")) {
            this._fld2d += this._fld8k[this._fld9c];
        }
        else if (this._fld7k[this._fld9c].equalsIgnoreCase("scrollup")) {
            this._fld2d -= this._fld8k[this._fld9c];
        }
        else if (this._fld7k[this._fld9c].equalsIgnoreCase("scrollright")) {
            this._fld1d += this._fld8k[this._fld9c];
        }
        else {
            this._fld1d -= this._fld8k[this._fld9c];
        }
        if (this._fld2d > this._fld6 + this._fld4d || this._fld2d < -this._fld4d || this._fld1d > this._fld5 || this._fld1d < -this._fld3d) {
            ++this._fld9c;
            if (this._fld9c >= this._fld0d) {
                this._fld9c = 0;
            }
            this._mth3c();
        }
    }
    
    void _mth5c() {
        final String parameter = this.getParameter("OverImage");
        if (parameter != null) {
            this._fld4c = this._mth1b(parameter, 0);
        }
        if (this._fld4c != null) {
            this._fld1c = true;
            this._fld7c = this._fld4c.getWidth(this);
            this._fld8c = this._fld4c.getHeight(this);
            final String parameter2 = this.getParameter("OverImageX");
            if (parameter2 == null) {
                this._fld5c = (this._fld5 >> 1) - (this._fld7c >> 1);
            }
            else {
                this._fld5c = Integer.valueOf(parameter2);
            }
            final String parameter3 = this.getParameter("OverImageY");
            if (parameter3 == null) {
                this._fld6c = (this._fld6 >> 1) - (this._fld8c >> 1);
                return;
            }
            this._fld6c = Integer.valueOf(parameter3);
        }
    }
    
    public DS_CubeMap() {
        this._fld4 = new Font("Helvetica", 1, 12);
        this._fld3b = "Applet by Dario Sciacca";
        this._fld4b = "dario@dseffects.com";
        this._fld7b = "www.dseffects.com";
        this._fld8b = "Don't remove Dario Sciacca's credits line";
        this._fld9b = this._fld3b + " (" + this._fld7b + ")";
        this._fld0c = false;
        this._fld1c = false;
        this._fld1e = "CubeMap started";
        this._fld3e = false;
        this._fld4j = "";
        this._fld6j = "_blank";
        this._fld7j = "Applet by Dario Sciacca";
        this._fld9j = false;
    }
}
