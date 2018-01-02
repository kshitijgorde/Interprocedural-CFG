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

public class DS_PlasmaImage extends Applet implements Runnable
{
    Thread _fld0;
    int _fld2;
    Font _fld3;
    int _fld5;
    int _fld6;
    int[] _fld7;
    int[] _fld9;
    MemoryImageSource _fld0b;
    Image _fld1b;
    int _fld2b;
    long _fld3b;
    String _fld4b;
    String _fld5b;
    int _fld6b;
    int _fld7b;
    String _fld8b;
    String _fld9b;
    String _fld0c;
    boolean _fld1c;
    boolean _fld2c;
    Image _fld3c;
    Graphics _fld4c;
    Image _fld5c;
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
    int _fld2e;
    boolean _fld3e;
    int _fld4e;
    int[] _fld5e;
    int[] _fld6e;
    int[] _fld7e;
    int[] _fld8e;
    int[] _fld9e;
    int[] _fld0f;
    int[] tr;
    int[] tg;
    int[] tb;
    int _fld1f;
    int[] _fld2f;
    int _fld3f;
    int _fld4f;
    int _fld5f;
    int _fld6f;
    int _fld7f;
    int _fld8f;
    int _fld9f;
    int _fld0g;
    int _fld1g;
    String _fld2g;
    URL _fld3g;
    String _fld4g;
    String _fld5g;
    URL _fld6g;
    boolean _fld7g;
    byte[] _fld8g;
    byte[] _fld9g;
    String[] _fld0h;
    Color[] _fld1h;
    Color[] _fld2h;
    Font[] _fld3h;
    FontMetrics[] _fld4h;
    String[] _fld5h;
    int[] _fld6h;
    int[] _fld7h;
    
    public String getAppletInfo() {
        return "DS PlasmaImage v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
    }
    
    public void init() {
        this._mth3();
        this.showStatus("Please wait ...");
        this._fld7b = this.getFontMetrics(this._fld3).stringWidth(this._fld8b);
        this._fld5 = this.size().width;
        this._fld6 = this.size().height;
        this._mth5();
        this._fld9 = new int[this._fld5 * this._fld6];
        this._fld0b = new MemoryImageSource(this._fld5, this._fld6, this._fld9, 0, this._fld5);
        this._fld3c = this.createImage(this._fld5, this._fld6);
        this._fld4c = this._fld3c.getGraphics();
        this._mth1c();
        this._mth4c();
        if (!this._fld1c && !this._fld2c) {
            this._fld6b = 0;
        }
        else if (this._fld1c && !this._fld2c) {
            this._fld6b = 1;
        }
        else if (!this._fld1c && this._fld2c) {
            this._fld6b = 2;
        }
        else {
            this._fld6b = 3;
        }
        this._mth3b();
        this._mth0c();
        if (this._mth9()) {
            this._mth6();
            this._mth4b();
            if (this._fld8d == -16777216) {
                this._fld2 = 1;
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
        this.showStatus(this._fld1e);
        System.gc();
        final Graphics graphics = this.getGraphics();
        this._fld3b = System.currentTimeMillis();
        while (this._fld0 != null) {
            if (this._fld2 == 1) {
                this._mth6b();
            }
            this._mth2(graphics);
            this._mth0();
            if (this._fld2b++ > 10) {
                System.gc();
                this._fld2b = 0;
            }
        }
    }
    
    synchronized void _mth0() {
        Thread.yield();
        this.getToolkit().sync();
        final long n = 10L - (System.currentTimeMillis() - this._fld3b);
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
        this._fld3b = System.currentTimeMillis();
    }
    
    public void update(final Graphics graphics) {
    }
    
    void _mth2(final Graphics graphics) {
        final int n = this._fld5 >> 1;
        final int n2 = this._fld6 >> 1;
        if (this._fld2 == 0) {
            graphics.drawString("Error ...", 10, 10);
            return;
        }
        if (this._fld1b != null) {
            if (this._fld6b == 0) {
                this._fld4c.drawImage(this._fld1b, 0, 0, this);
            }
            else if (this._fld6b == 1) {
                this._fld4c.drawImage(this._fld1b, 0, 0, this);
                this._mth3c(this._fld4c);
            }
            else if (this._fld6b == 2) {
                this._fld4c.drawImage(this._fld1b, 0, 0, this);
                this._fld4c.drawImage(this._fld5c, this._fld6c, this._fld7c, this);
            }
            else {
                this._fld4c.drawImage(this._fld1b, 0, 0, this);
                this._mth3c(this._fld4c);
                this._fld4c.drawImage(this._fld5c, this._fld6c, this._fld7c, this);
            }
        }
        if (this._fld3e && !this._fld7g) {
            this._fld4c.setColor(Color.white);
            this._fld4c.drawLine(n - 64, n2 - 8, n + 64, n2 - 8);
            this._fld4c.drawLine(n - 64, n2 + 8, n + 64, n2 + 8);
            this._fld4c.drawLine(n - 64, n2 - 8, n - 64, n2 + 8);
            this._fld4c.drawLine(n + 64, n2 - 8, n + 64, n2 + 8);
            this._fld4c.setColor(Color.blue);
            this._fld4c.fillRect(n - 63, n2 - 7, 127, 15);
            this._fld4c.setFont(this._fld3);
            this._fld4c.setColor(Color.yellow);
            this._fld4c.drawString(this._fld8b, n - (this._fld7b >> 1), n2 + 5);
        }
        graphics.drawImage(this._fld3c, 0, 0, this);
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this._fld5g);
        return this._fld3e = true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this._fld3e = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int fld9d, final int fld0e) {
        this._fld9d = fld9d;
        this._fld0e = fld0e;
        return this._fld3e = true;
    }
    
    void _mth3() {
        final String parameter = this.getParameter("credits");
        if (parameter != null) {
            if (parameter.equals(this._fld0c)) {
                this._fld6d = 1;
                return;
            }
            while (true) {
                this.showStatus(this._fld9b);
            }
        }
        else {
            while (true) {
                this.showStatus(this._fld9b);
            }
        }
    }
    
    void _mth5() {
        this._fld7d = 1;
        for (int i = 0; i < 11; ++i) {
            if (this._fld5b.charAt(i) == this._fld4b.charAt(i) || this._fld6d == 0) {
                while (true) {
                    this.showStatus(this._fld9b);
                }
            }
            else {}
        }
        this._fld7g = false;
    }
    
    void _mth6() {
        if (this._fld6d == 0 || this._fld7d == 0) {
            while (true) {
                this.showStatus(this._fld9b);
            }
        }
        else {
            for (int i = 0; i < 17; ++i) {
                if (this._fld5b.charAt(i) == this._fld8b.charAt(i)) {
                    while (true) {
                        this.showStatus(this._fld9b);
                    }
                }
                else {}
            }
            this._fld8d = -16777216;
            if (this._fld4b.charAt(1) == 'p' && this._fld4b.charAt(7) == 'b' && this._fld4b.charAt(21) == 'c' && this._fld4b.charAt(17) == 'c' && this._fld4b.charAt(12) == 'r' && this._fld4b.charAt(11) == 'a') {
                return;
            }
            while (true) {
                this.showStatus(this._fld9b);
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
    
    boolean _mth9() {
        final Image mth0b = this._mth0b(this.getParameter("image"));
        if (mth0b == null) {
            this.showStatus("Error loading image ");
            return false;
        }
        final int width = mth0b.getWidth(this);
        final int height = mth0b.getHeight(this);
        this._fld7 = new int[this._fld5 * this._fld6];
        if (width != this._fld5 || height != this._fld6) {
            final int[] array = new int[width * height];
            if (!this._mth1b(mth0b, array, width, height)) {
                return false;
            }
            this._fld7 = this._mth7(this._fld7, this._fld5, this._fld6, array, width, height);
        }
        else if (!this._mth1b(mth0b, this._fld7, this._fld5, this._fld6)) {
            return false;
        }
        mth0b.flush();
        System.gc();
        return true;
    }
    
    Image _mth0b(final String s) {
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
    
    boolean _mth1b(final Image image, final int[] array, final int n, final int n2) {
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
    
    Color _mth2b(final String s, final Color color) {
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
    
    void _mth3b() {
        String parameter = this.getParameter("plasmasize");
        if (parameter == null) {
            parameter = "1";
        }
        this._fld7f = Integer.valueOf(parameter);
        this._fld7f = ((this._fld7f >= 1) ? ((this._fld7f <= 4) ? this._fld7f : 4) : 1);
        this._fld7f *= 512;
        String parameter2 = this.getParameter("plasma1");
        if (parameter2 == null) {
            parameter2 = "8";
        }
        this._fld8f = Integer.valueOf(parameter2);
        this._fld8f = ((this._fld8f >= -8) ? ((this._fld8f <= 8) ? this._fld8f : 8) : -8);
        String parameter3 = this.getParameter("plasma2");
        if (parameter3 == null) {
            parameter3 = "-2";
        }
        this._fld9f = Integer.valueOf(parameter3);
        this._fld9f = ((this._fld9f >= -8) ? ((this._fld9f <= 8) ? this._fld9f : 8) : -8);
        String parameter4 = this.getParameter("plasma3");
        if (parameter4 == null) {
            parameter4 = "4";
        }
        this._fld0g = Integer.valueOf(parameter4);
        this._fld0g = ((this._fld0g >= -8) ? ((this._fld0g <= 8) ? this._fld0g : 8) : -8);
        String parameter5 = this.getParameter("plasma4");
        if (parameter5 == null) {
            parameter5 = "4";
        }
        this._fld1g = Integer.valueOf(parameter5);
        this._fld1g = ((this._fld1g >= -8) ? ((this._fld1g <= 8) ? this._fld1g : 8) : -8);
        final String parameter6 = this.getParameter("interactive");
        if (parameter6 == null) {
            this._fld2e = 1;
        }
        else if (parameter6.equalsIgnoreCase("IN")) {
            this._fld2e = 0;
        }
        else if (parameter6.equalsIgnoreCase("OUT")) {
            this._fld2e = 1;
        }
        else {
            this._fld2e = 2;
        }
        this._fld5g = this._fld4b;
        final String parameter7 = this.getParameter("regkey");
        if (parameter7 != null) {
            this._fld2g = parameter7;
            final String parameter8 = this.getParameter("reglink");
            if (parameter8 != null) {
                try {
                    this._fld3g = new URL("http://" + parameter8);
                }
                catch (MalformedURLException ex) {
                    this._fld3g = null;
                }
                final String parameter9 = this.getParameter("regtarget");
                if (parameter9 != null) {
                    this._fld4g = parameter9;
                }
            }
            final String parameter10 = this.getParameter("regstatusmsg");
            if (parameter10 != null) {
                this._fld5g = parameter10;
            }
        }
    }
    
    void _mth4b() {
        this._fld1f = 256;
        this._fld5e = new int[this._fld1f];
        this._fld6e = new int[this._fld1f];
        this._fld7e = new int[this._fld1f];
        this._fld8e = new int[this._fld1f];
        this._fld9e = new int[this._fld1f];
        this._fld0f = new int[this._fld1f];
        this._mth7b();
        this._fld4e = 0;
        if (this._fld2e == 2) {
            for (int i = 0; i < this._fld1f; ++i) {
                this._fld5e[i] = this._fld8e[i];
                this._fld6e[i] = this._fld9e[i];
                this._fld7e[i] = this._fld0f[i];
            }
        }
        else {
            for (int j = 0; j < this._fld1f; ++j) {
                this._fld5e[j] = 0;
                this._fld6e[j] = 0;
                this._fld7e[j] = 0;
            }
        }
        this.tr = new int[this._fld5 * this._fld6];
        this.tg = new int[this._fld5 * this._fld6];
        this.tb = new int[this._fld5 * this._fld6];
        for (int k = 0; k < this._fld5 * this._fld6; ++k) {
            final int n = this._fld7[k];
            this.tr[k] = (n >> 16 & 0xFF);
            this.tg[k] = (n >> 8 & 0xFF);
            this.tb[k] = (n & 0xFF);
        }
        this._fld2f = new int[this._fld7f];
        this._fld3f = 0;
        this._fld4f = 0;
        this._fld5f = 0;
        this._fld6f = 0;
        for (int l = 0; l < this._fld7f; ++l) {
            this._fld2f[l] = (int)((Math.sin(l * 3.141592653589793 / (this._fld7f >> 1)) + 1.0) * 31.0);
        }
    }
    
    void _mth5b() {
        this._fld3f = (this._fld3f + this._fld8f + this._fld7f) % this._fld7f;
        this._fld4f = (this._fld4f + this._fld9f + this._fld7f) % this._fld7f;
        this._fld5f = (this._fld5f + this._fld0g + this._fld7f) % this._fld7f;
        this._fld6f = (this._fld6f + this._fld1g + this._fld7f) % this._fld7f;
        int n = 0;
        for (int i = 0; i < this._fld6; ++i) {
            for (int j = 0; j < this._fld5; ++j) {
                final int n2 = this._fld2f[(this._fld3f + (i << 1)) % this._fld7f] + this._fld2f[(this._fld4f + (j << 2)) % this._fld7f] + this._fld2f[(this._fld5f + (j << 1)) % this._fld7f] + this._fld2f[(this._fld6f + (i << 2)) % this._fld7f];
                if (n2 == 0) {
                    this._fld9[n] = this._fld7[n];
                }
                else {
                    final int n3 = this.tr[n] + this._fld5e[n2];
                    final int n4 = this.tg[n] + this._fld6e[n2];
                    final int n5 = this.tb[n] + this._fld7e[n2];
                    this._fld9[n] = (0xFF000000 | ((n3 <= 255) ? n3 : 255) << 16 | ((n4 <= 255) ? n4 : 255) << 8 | ((n5 <= 255) ? n5 : 255));
                }
                ++n;
            }
        }
    }
    
    void _mth6b() {
        if (this._fld2e == 0) {
            if (this._fld3e) {
                this._fld4e += 4;
                if (this._fld4e > this._fld1f) {
                    this._fld4e = this._fld1f;
                }
                else {
                    this._mth9b(this._fld4e, this._fld5e, this._fld6e, this._fld7e, this._fld8e, this._fld9e, this._fld0f);
                }
            }
            else {
                this._fld4e -= 4;
                if (this._fld4e < 0) {
                    this._fld4e = 0;
                }
                else {
                    this._mth9b(this._fld4e, this._fld5e, this._fld6e, this._fld7e, this._fld8e, this._fld9e, this._fld0f);
                }
            }
        }
        else if (this._fld2e == 1) {
            if (this._fld3e) {
                this._fld4e -= 4;
                if (this._fld4e < 0) {
                    this._fld4e = 0;
                }
                else {
                    this._mth9b(this._fld4e, this._fld5e, this._fld6e, this._fld7e, this._fld8e, this._fld9e, this._fld0f);
                }
            }
            else {
                this._fld4e += 4;
                if (this._fld4e > this._fld1f) {
                    this._fld4e = this._fld1f;
                }
                else {
                    this._mth9b(this._fld4e, this._fld5e, this._fld6e, this._fld7e, this._fld8e, this._fld9e, this._fld0f);
                }
            }
        }
        this._mth5b();
        this._fld1b = this.createImage(this._fld0b);
    }
    
    void _mth7b() {
        this._mth8b(0, 64, 0, 0, 0, 0, 0, 127);
        this._mth8b(64, 96, 0, 0, 0, 127, 127, 127);
        this._mth8b(96, 128, 0, 0, 127, 127, 127, 0);
        this._mth8b(128, 160, 0, 127, 127, 127, 0, 0);
        this._mth8b(160, 192, 127, 127, 127, 0, 0, 0);
        this._mth8b(192, 255, 127, 0, 0, 0, 0, 0);
    }
    
    void _mth8b(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        final int n9 = n2 - n;
        final int n10 = n4 - n3;
        final int n11 = n6 - n5;
        final int n12 = n8 - n7;
        for (int i = 0; i < n9; ++i) {
            this._fld8e[n + i] = n3 + i * n10 / n9;
            this._fld9e[n + i] = n5 + i * n11 / n9;
            this._fld0f[n + i] = n7 + i * n12 / n9;
        }
    }
    
    void _mth9b(final int n, final int[] array, final int[] array2, final int[] array3, final int[] array4, final int[] array5, final int[] array6) {
        for (int i = 0; i < this._fld1f; ++i) {
            array[i] = array4[i * n >> 8];
            array2[i] = array5[i * n >> 8];
            array3[i] = array6[i * n >> 8];
        }
    }
    
    void _mth0c() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this._fld2g.length() > 9) {
            final int n = this._fld2g.length() - 9;
            final int n2 = n + 9;
            this._fld8g = new byte[n];
            this._fld2g.getBytes(1, n + 1, this._fld8g, 0);
            this._fld9g = new byte[n2];
            this._fld2g.getBytes(0, n2, this._fld9g, 0);
            int n3 = n % 7;
            final int n4 = n % 3;
            for (int i = 0; i < n; ++i) {
                final byte b = this._fld8g[i];
                final byte b2 = (byte)(b + n3);
                if (b >= 48 && b <= 57) {
                    this._fld8g[i] = ((b2 <= 57) ? b2 : ((byte)(b2 - 10)));
                }
                else if (b >= 65 && b <= 90) {
                    this._fld8g[i] = ((b2 <= 90) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b >= 97 && b <= 122) {
                    this._fld8g[i] = ((b2 <= 122) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b == 42) {
                    this._fld8g[i] = 45;
                }
                else if (b == 45) {
                    this._fld8g[i] = 46;
                }
                n3 = (n3 + n4) % 7;
            }
            int n5 = n % 7;
            final int n6 = n % 3;
            for (int j = 0; j < n; ++j) {
                final byte b3 = this._fld8g[j];
                final byte b4 = (byte)(b3 - n5);
                if (b3 >= 48 && b3 <= 57) {
                    this._fld9g[j + 1] = ((b4 >= 48) ? b4 : ((byte)(b4 + 10)));
                }
                else if (b3 >= 65 && b3 <= 90) {
                    this._fld9g[j + 1] = ((b4 >= 65) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 >= 97 && b3 <= 122) {
                    this._fld9g[j + 1] = ((b4 >= 97) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 == 45) {
                    this._fld9g[j + 1] = 42;
                }
                else if (b3 == 46) {
                    this._fld9g[j + 1] = 45;
                }
                n5 = (n5 + n6) % 7;
            }
            final byte[] array = new byte[n];
            if (n > 4) {
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld8g[k + 4];
                }
            }
            if (this._fld9g[0] == this._fld9g[n >> 1] && this._fld9g[1 + n] == this._fld9g[1] && this._fld9g[1 + n + 1] == this._fld9g[n >> 1] && this._fld9g[1 + n + 2] == (byte)(97 + n5) && this._fld9g[1 + n + 3] == 45 && this._fld9g[1 + n + 4] == (byte)(122 - n6) && this._fld9g[1 + n + 5] == (byte)(110 + n5) && this._fld9g[1 + n + 6] == this._fld9g[1] && this._fld9g[1 + n + 7] == this._fld9g[n] && (host.equalsIgnoreCase(new String(this._fld8g, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this._fld7g = true;
            }
        }
        try {
            this._fld6g = new URL("http://" + this._fld8b);
        }
        catch (MalformedURLException ex) {
            this._fld6g = null;
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld7g) {
            this.getAppletContext().showDocument(this._fld6g, "_blank");
        }
        else if (this._fld3g != null) {
            if (this._fld4g != null) {
                this.getAppletContext().showDocument(this._fld3g, this._fld4g);
            }
            else {
                this.getAppletContext().showDocument(this._fld3g);
            }
        }
        return true;
    }
    
    void _mth1c() {
        int fld1d = 0;
        do {
            ++fld1d;
        } while (this.getParameter("overtext" + fld1d) != null);
        if (--fld1d > 0) {
            this._fld1c = true;
            this._fld1d = fld1d;
            this._fld0h = new String[this._fld1d];
            this._fld1h = new Color[this._fld1d];
            this._fld2h = new Color[this._fld1d];
            this._fld3h = new Font[this._fld1d];
            this._fld4h = new FontMetrics[this._fld1d];
            this._fld5h = new String[this._fld1d];
            this._fld6h = new int[this._fld1d];
            this._fld7h = new int[this._fld1d];
            for (int i = 0; i < this._fld1d; ++i) {
                this._fld0h[i] = this.getParameter("overtext" + String.valueOf(i + 1));
                this._fld1h[i] = this._mth2b("overtextcol" + String.valueOf(i + 1), new Color(16777215));
                this._fld2h[i] = this._mth2b("overtextcols" + String.valueOf(i + 1), new Color(0));
                final String parameter = this.getParameter("overtexty" + String.valueOf(i + 1));
                if (parameter == null) {
                    this._fld7h[i] = 10;
                }
                else {
                    this._fld7h[i] = Integer.parseInt(parameter);
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
                this._fld3h[i] = new Font(parameter2, n, int1);
                this._fld4h[i] = this._fld4c.getFontMetrics(this._fld3h[i]);
                this._fld5h[i] = this.getParameter("overTextType" + String.valueOf(i + 1));
                if (this._fld5h[i] == null) {
                    this._fld5h[i] = "scrollleft";
                }
                final String parameter5 = this.getParameter("overtextspeed" + String.valueOf(i + 1));
                if (parameter5 == null) {
                    this._fld6h[i] = 2;
                }
                else {
                    this._fld6h[i] = Integer.valueOf(parameter5);
                    if (this._fld6h[i] < 1 || this._fld6h[i] > 4) {
                        this._fld6h[i] = 2;
                    }
                }
            }
            this._mth2c();
        }
    }
    
    void _mth2c() {
        this._fld4d = this._fld4h[this._fld0d].stringWidth(this._fld0h[this._fld0d]);
        this._fld5d = this._fld4h[this._fld0d].getHeight();
        if (this._fld5h[this._fld0d].equalsIgnoreCase("scrolldown")) {
            this._fld2d = this._fld5 - this._fld4d >> 1;
            this._fld3d = 0;
            return;
        }
        if (this._fld5h[this._fld0d].equalsIgnoreCase("scrollup")) {
            this._fld2d = this._fld5 - this._fld4d >> 1;
            this._fld3d = this._fld6 + this._fld5d;
            return;
        }
        if (this._fld5h[this._fld0d].equalsIgnoreCase("scrollright")) {
            this._fld2d = -this._fld4d;
            this._fld3d = this._fld7h[this._fld0d] + (this._fld5d >> 1) + (this._fld5d >> 3);
            return;
        }
        this._fld2d = this._fld5;
        this._fld3d = this._fld7h[this._fld0d] + (this._fld5d >> 1) + (this._fld5d >> 3);
    }
    
    void _mth3c(final Graphics graphics) {
        graphics.setFont(this._fld3h[this._fld0d]);
        graphics.setColor(this._fld2h[this._fld0d]);
        graphics.drawString(this._fld0h[this._fld0d], this._fld2d + 1, this._fld3d + 1);
        graphics.setColor(this._fld1h[this._fld0d]);
        graphics.drawString(this._fld0h[this._fld0d], this._fld2d, this._fld3d);
        if (this._fld5h[this._fld0d].equalsIgnoreCase("scrolldown")) {
            this._fld3d += this._fld6h[this._fld0d];
        }
        else if (this._fld5h[this._fld0d].equalsIgnoreCase("scrollup")) {
            this._fld3d -= this._fld6h[this._fld0d];
        }
        else if (this._fld5h[this._fld0d].equalsIgnoreCase("scrollright")) {
            this._fld2d += this._fld6h[this._fld0d];
        }
        else {
            this._fld2d -= this._fld6h[this._fld0d];
        }
        if (this._fld3d > this._fld6 + this._fld5d || this._fld3d < -this._fld5d || this._fld2d > this._fld5 || this._fld2d < -this._fld4d) {
            ++this._fld0d;
            if (this._fld0d >= this._fld1d) {
                this._fld0d = 0;
            }
            this._mth2c();
        }
    }
    
    void _mth4c() {
        final String parameter = this.getParameter("OverImage");
        if (parameter != null) {
            this._fld5c = this._mth0b(parameter);
        }
        if (this._fld5c != null) {
            this._fld2c = true;
            this._fld8c = this._fld5c.getWidth(this);
            this._fld9c = this._fld5c.getHeight(this);
            final String parameter2 = this.getParameter("OverImageX");
            if (parameter2 == null) {
                this._fld6c = (this._fld5 >> 1) - (this._fld8c >> 1);
            }
            else {
                this._fld6c = Integer.valueOf(parameter2);
            }
            final String parameter3 = this.getParameter("OverImageY");
            if (parameter3 == null) {
                this._fld7c = (this._fld6 >> 1) - (this._fld9c >> 1);
                return;
            }
            this._fld7c = Integer.valueOf(parameter3);
        }
    }
    
    public DS_PlasmaImage() {
        this._fld3 = new Font("Helvetica", 1, 12);
        this._fld4b = "Applet by Dario Sciacca";
        this._fld5b = "dario@dseffects.com";
        this._fld8b = "www.dseffects.com";
        this._fld9b = "Don't remove Dario Sciacca's credits line";
        this._fld0c = this._fld4b + " (" + this._fld8b + ")";
        this._fld1c = false;
        this._fld2c = false;
        this._fld1e = "PlasmaImage started";
        this._fld3e = false;
        this._fld2g = "";
        this._fld4g = "_blank";
        this._fld5g = "Applet by Dario Sciacca";
        this._fld7g = false;
    }
}
