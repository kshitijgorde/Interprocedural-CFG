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

public class DS_BumpMap extends Applet implements Runnable
{
    Thread _fld0;
    int _fld3;
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
    String _fld0e;
    short[] _fld1e;
    short[] _fld2e;
    short[] _fld3e;
    int[] _fld4e;
    int lx;
    int ly;
    int[] _fld5e;
    int[] _fld6e;
    int _fld7e;
    int _fld8e;
    int _fld9e;
    int _fld0f;
    int _fld1f;
    boolean _fld2f;
    boolean _fld3f;
    int[] _fld4f;
    int _fld5f;
    int _fld6f;
    String _fld7f;
    URL _fld8f;
    String _fld9f;
    String _fld0g;
    URL _fld1g;
    boolean _fld2g;
    byte[] _fld3g;
    byte[] _fld4g;
    byte[] _fld5g;
    String[] _fld6g;
    Color[] _fld7g;
    Color[] _fld8g;
    Font[] _fld9g;
    FontMetrics[] _fld0h;
    String[] _fld1h;
    int[] _fld2h;
    int[] _fld3h;
    
    public String getAppletInfo() {
        return "DS BumpMap v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
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
        this._mth9b();
        this._mth2c();
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
        this._mth2b();
        this._mth8b();
        if (this._mth8()) {
            this._mth6();
            this._mth3b();
            if (this._fld7d == -16777216) {
                this._fld3 = 1;
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
        this.showStatus(this._fld0e);
        System.gc();
        final Graphics graphics = this.getGraphics();
        this._fld2b = System.currentTimeMillis();
        while (this._fld0 != null) {
            if (this._fld3 == 1) {
                this._mth4b();
            }
            this._mth3(graphics);
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
    
    void _mth3(final Graphics graphics) {
        final int n = this._fld5 >> 1;
        final int n2 = this._fld6 >> 1;
        if (this._fld3 == 0) {
            graphics.drawString("Error ...", 10, 10);
            return;
        }
        if (this._fld0b != null) {
            if (this._fld5b == 0) {
                if (!this._fld2g) {
                    this._fld3c.drawImage(this._fld0b, 0, 0, this);
                }
                else {
                    graphics.drawImage(this._fld0b, 0, 0, this);
                }
            }
            else if (this._fld5b == 1) {
                this._fld3c.drawImage(this._fld0b, 0, 0, this);
                this._mth1c(this._fld3c);
            }
            else if (this._fld5b == 2) {
                this._fld3c.drawImage(this._fld0b, 0, 0, this);
                this._fld3c.drawImage(this._fld4c, this._fld5c, this._fld6c, this);
            }
            else {
                this._fld3c.drawImage(this._fld0b, 0, 0, this);
                this._mth1c(this._fld3c);
                this._fld3c.drawImage(this._fld4c, this._fld5c, this._fld6c, this);
            }
        }
        if (this._fld3f && !this._fld2g) {
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
        this.showStatus(this._fld0g);
        return this._fld3f = true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this._fld3f = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int fld8d, final int fld9d) {
        this._fld8d = fld8d;
        this._fld9d = fld9d;
        return this._fld3f = true;
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
        this._fld2g = false;
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
        final Image mth9 = this._mth9(this.getParameter("image"));
        if (mth9 == null) {
            this.showStatus("Error loading image ");
            return false;
        }
        final int width = mth9.getWidth(this);
        final int height = mth9.getHeight(this);
        this._fld7 = new int[this._fld5 * this._fld6];
        if (width != this._fld5 || height != this._fld6) {
            final int[] array = new int[width * height];
            if (!this._mth0b(mth9, array, width, height)) {
                return false;
            }
            this._fld7 = this._mth7(this._fld7, this._fld5, this._fld6, array, width, height);
        }
        else if (!this._mth0b(mth9, this._fld7, this._fld5, this._fld6)) {
            return false;
        }
        mth9.flush();
        System.gc();
        return true;
    }
    
    Image _mth9(final String s) {
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
    
    boolean _mth0b(final Image image, final int[] array, final int n, final int n2) {
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
    
    Color _mth1b(final String s, final Color color) {
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
    
    void _mth2b() {
        String parameter = this.getParameter("diameter");
        if (parameter == null) {
            parameter = "150";
        }
        this._fld5f = Integer.valueOf(parameter);
        this._fld5f = ((this._fld5f >= 128) ? ((this._fld5f <= 256) ? this._fld5f : 256) : 128);
        String parameter2 = this.getParameter("palette");
        if (parameter2 == null) {
            parameter2 = "2";
        }
        this._fld1f = Integer.valueOf(parameter2);
        this._fld1f = ((this._fld1f >= 1) ? ((this._fld1f <= 8) ? this._fld1f : 8) : 1);
        final String parameter3 = this.getParameter("interactive");
        if (parameter3 == null) {
            this._fld2f = true;
        }
        else if (parameter3.equalsIgnoreCase("no")) {
            this._fld2f = false;
        }
        else {
            this._fld2f = true;
        }
        String parameter4 = this.getParameter("speedx");
        if (parameter4 == null) {
            parameter4 = "1";
        }
        this._fld9e = Integer.valueOf(parameter4);
        this._fld9e = ((this._fld9e >= 1) ? ((this._fld9e <= 4) ? this._fld9e : 4) : 1);
        String parameter5 = this.getParameter("speedy");
        if (parameter5 == null) {
            parameter5 = "2";
        }
        this._fld0f = Integer.valueOf(parameter5);
        this._fld0f = ((this._fld0f >= 1) ? ((this._fld0f <= 4) ? this._fld0f : 4) : 1);
        this._fld0g = this._fld3b;
        final String parameter6 = this.getParameter("regkey");
        if (parameter6 != null) {
            this._fld7f = parameter6;
            final String parameter7 = this.getParameter("reglink");
            if (parameter7 != null) {
                try {
                    this._fld8f = new URL("http://" + parameter7);
                }
                catch (MalformedURLException ex) {
                    this._fld8f = null;
                }
                final String parameter8 = this.getParameter("regtarget");
                if (parameter8 != null) {
                    this._fld9f = parameter8;
                }
            }
            final String parameter9 = this.getParameter("regstatusmsg");
            if (parameter9 != null) {
                this._fld0g = parameter9;
            }
        }
    }
    
    void _mth3b() {
        this._fld6f = this._fld5f >> 1;
        this._fld1e = new short[this._fld5f * this._fld5f];
        this._fld2e = new short[this._fld5 * this._fld6];
        this._fld3e = new short[this._fld5 * this._fld6];
        this._fld4e = new int[this._fld5 * this._fld6];
        for (int i = 0; i < this._fld5 * this._fld6; ++i) {
            final int n = this._fld7[i];
            this._fld4e[i] = (int)Math.round(0.2125 * (n >> 16 & 0xFF) + 0.7154 * (n >> 8 & 0xFF) + 0.0721 * (n & 0xFF));
        }
        final int n2 = this._fld5 >> 1;
        final int n3 = this._fld6 >> 1;
        final int n4 = n2;
        this.lx = n4;
        this._fld8d = n4;
        final int n5 = n3;
        this.ly = n5;
        this._fld9d = n5;
        this._mth6b();
        final int n6 = n2 - (n2 >> 3);
        final int n7 = n3 - (n3 >> 3);
        for (int j = 0; j < 256; ++j) {
            this._fld5e[j] = (int)(Math.cos(0.02454369260617026 * j) * n6);
            this._fld6e[j] = (int)(Math.sin(0.02454369260617026 * j) * n7);
        }
        int n8 = this._fld5 + 1;
        for (int k = 0; k < this._fld6 - 1; ++k) {
            for (int l = 1; l < this._fld5; ++l) {
                this._fld2e[n8] = (short)(this._fld4e[n8] - this._fld4e[n8 - 1]);
                this._fld3e[n8] = (short)(this._fld4e[n8] - this._fld4e[n8 - this._fld5]);
                ++n8;
            }
            ++n8;
        }
        switch (this._fld1f) {
            case 1: {
                this._mth5b(0, this._fld5f - 1, 0, 0, 0, 0, 0, 255);
            }
            case 2: {
                this._mth5b(0, this._fld5f - 1, 0, 0, 0, 255, 0, 0);
            }
            case 3: {
                this._mth5b(0, this._fld5f - 1, 0, 255, 0, 0, 0, 0);
            }
            case 4: {
                this._mth5b(0, this._fld5f - 1, 0, 255, 0, 255, 0, 0);
            }
            case 5: {
                this._mth5b(0, this._fld5f - 1, 0, 255, 0, 0, 0, 255);
            }
            case 6: {
                this._mth5b(0, this._fld5f - 1, 0, 0, 0, 255, 0, 255);
            }
            case 7: {
                this._mth5b(0, this._fld5f - 1, 0, 255, 0, 255, 0, 255);
            }
            case 8: {
                this._mth5b(0, this._fld5f - 1, 0, 127, 0, 127, 0, 255);
            }
            default: {}
        }
    }
    
    void _mth4b() {
        this._fld7e = (this._fld7e + this._fld9e) % 256;
        this._fld8e = (this._fld8e + this._fld0f) % 256;
        if (this._fld2f) {
            if (!this._fld3f) {
                this.lx = (this._fld5 >> 1) + this._fld5e[this._fld7e];
                this.ly = (this._fld6 >> 1) + this._fld6e[this._fld8e];
            }
            else {
                this.lx = this._fld8d;
                this.ly = this._fld9d;
            }
        }
        else {
            this.lx = (this._fld5 >> 1) + this._fld5e[this._fld7e];
            this.ly = (this._fld6 >> 1) + this._fld6e[this._fld8e];
        }
        this._mth7b();
        this._fld0b = this.createImage(this._fld9);
    }
    
    void _mth5b(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        final int n9 = n2 - n;
        final int n10 = n4 - n3;
        final int n11 = n6 - n5;
        final int n12 = n8 - n7;
        for (int i = 0; i <= n9; ++i) {
            this._fld4f[n + i] = (0xFF000000 | n3 + i * n10 / n9 << 16 | n5 + i * n11 / n9 << 8 | n7 + i * n12 / n9);
        }
    }
    
    void _mth6b() {
        final int n = this._fld5f - 1;
        for (int i = 0; i < this._fld5f; ++i) {
            for (int j = 0; j < this._fld5f; ++j) {
                double sqrt = (this._fld6f - j) * (this._fld6f - j) + (this._fld6f - i) * (this._fld6f - i);
                if (Math.abs(sqrt) > 1.0) {
                    sqrt = Math.sqrt(sqrt);
                }
                int n2 = (int)(2.4 * sqrt);
                if (n2 < 0) {
                    n2 = 0;
                }
                if (n2 > n) {
                    n2 = n;
                }
                this._fld1e[i * this._fld5f + j] = (short)(n - n2);
            }
        }
    }
    
    void _mth7b() {
        this.lx += this._fld6f;
        this.ly += this._fld6f;
        int n = 0;
        final int n2 = this._fld5f - 1;
        for (int i = 0; i < this._fld6; ++i) {
            for (int j = 0; j < this._fld5; ++j) {
                final short n3 = this._fld2e[n];
                final short n4 = this._fld3e[n];
                int n5 = n3 - (j - this.lx);
                int n6 = n4 - (i - this.ly);
                if (n5 > n2 || n5 < 0) {
                    n5 = n2;
                }
                if (n6 > n2 || n6 < 0) {
                    n6 = n2;
                }
                this._fld8[n] = this._fld4f[this._fld1e[n5 + n6 * this._fld5f]];
                ++n;
            }
        }
    }
    
    void _mth8b() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this._fld7f.length() > 9) {
            final int n = this._fld7f.length() - 9;
            final int n2 = n + 9;
            this._fld3g = new byte[n];
            this._fld7f.getBytes(1, n + 1, this._fld3g, 0);
            this._fld4g = new byte[n2];
            this._fld7f.getBytes(0, n2, this._fld4g, 0);
            int n3 = n % 7;
            final int n4 = n % 3;
            for (int i = 0; i < n; ++i) {
                final byte b = this._fld3g[i];
                final byte b2 = (byte)(b + n3);
                if (b >= 48 && b <= 57) {
                    this._fld3g[i] = ((b2 <= 57) ? b2 : ((byte)(b2 - 10)));
                }
                else if (b >= 65 && b <= 90) {
                    this._fld3g[i] = ((b2 <= 90) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b >= 97 && b <= 122) {
                    this._fld3g[i] = ((b2 <= 122) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b == 42) {
                    this._fld3g[i] = 45;
                }
                else if (b == 45) {
                    this._fld3g[i] = 46;
                }
                n3 = (n3 + n4) % 7;
            }
            int n5 = n % 7;
            final int n6 = n % 3;
            for (int j = 0; j < n; ++j) {
                final byte b3 = this._fld3g[j];
                final byte b4 = (byte)(b3 - n5);
                if (b3 >= 48 && b3 <= 57) {
                    this._fld4g[j + 1] = ((b4 >= 48) ? b4 : ((byte)(b4 + 10)));
                }
                else if (b3 >= 65 && b3 <= 90) {
                    this._fld4g[j + 1] = ((b4 >= 65) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 >= 97 && b3 <= 122) {
                    this._fld4g[j + 1] = ((b4 >= 97) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 == 45) {
                    this._fld4g[j + 1] = 42;
                }
                else if (b3 == 46) {
                    this._fld4g[j + 1] = 45;
                }
                n5 = (n5 + n6) % 7;
            }
            if (n > 4) {
                this._fld5g = new byte[n - 4];
                for (int k = 0; k < n - 4; ++k) {
                    this._fld5g[k] = this._fld3g[k + 4];
                }
            }
            if (this._fld4g[0] == this._fld4g[n >> 1] && this._fld4g[1 + n] == this._fld4g[1] && this._fld4g[1 + n + 1] == this._fld4g[n >> 1] && this._fld4g[1 + n + 2] == (byte)(97 + n5) && this._fld4g[1 + n + 3] == 45 && this._fld4g[1 + n + 4] == (byte)(122 - n6) && this._fld4g[1 + n + 5] == (byte)(110 + n5) && this._fld4g[1 + n + 6] == this._fld4g[1] && this._fld4g[1 + n + 7] == this._fld4g[n] && (host.equalsIgnoreCase(new String(this._fld3g, 0)) || host.equalsIgnoreCase(new String(this._fld5g, 0)))) {
                this._fld2g = true;
            }
        }
        try {
            this._fld1g = new URL("http://" + this._fld7b);
        }
        catch (MalformedURLException ex) {
            this._fld1g = null;
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld2g) {
            this.getAppletContext().showDocument(this._fld1g, "_blank");
        }
        else if (this._fld8f != null) {
            if (this._fld9f != null) {
                this.getAppletContext().showDocument(this._fld8f, this._fld9f);
            }
            else {
                this.getAppletContext().showDocument(this._fld8f);
            }
        }
        return true;
    }
    
    void _mth9b() {
        int fld0d = 0;
        do {
            ++fld0d;
        } while (this.getParameter("overtext" + fld0d) != null);
        if (--fld0d > 0) {
            this._fld0c = true;
            this._fld0d = fld0d;
            this._fld6g = new String[this._fld0d];
            this._fld7g = new Color[this._fld0d];
            this._fld8g = new Color[this._fld0d];
            this._fld9g = new Font[this._fld0d];
            this._fld0h = new FontMetrics[this._fld0d];
            this._fld1h = new String[this._fld0d];
            this._fld2h = new int[this._fld0d];
            this._fld3h = new int[this._fld0d];
            for (int i = 0; i < this._fld0d; ++i) {
                this._fld6g[i] = this.getParameter("overtext" + String.valueOf(i + 1));
                this._fld7g[i] = this._mth1b("overtextcol" + String.valueOf(i + 1), new Color(16777215));
                this._fld8g[i] = this._mth1b("overtextcols" + String.valueOf(i + 1), new Color(0));
                final String parameter = this.getParameter("overtexty" + String.valueOf(i + 1));
                if (parameter == null) {
                    this._fld3h[i] = 10;
                }
                else {
                    this._fld3h[i] = Integer.parseInt(parameter);
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
                this._fld9g[i] = new Font(parameter2, n, int1);
                this._fld0h[i] = this._fld3c.getFontMetrics(this._fld9g[i]);
                this._fld1h[i] = this.getParameter("overTextType" + String.valueOf(i + 1));
                if (this._fld1h[i] == null) {
                    this._fld1h[i] = "scrollleft";
                }
                final String parameter5 = this.getParameter("overtextspeed" + String.valueOf(i + 1));
                if (parameter5 == null) {
                    this._fld2h[i] = 2;
                }
                else {
                    this._fld2h[i] = Integer.valueOf(parameter5);
                    if (this._fld2h[i] < 1 || this._fld2h[i] > 4) {
                        this._fld2h[i] = 2;
                    }
                }
            }
            this._mth0c();
        }
    }
    
    void _mth0c() {
        this._fld3d = this._fld0h[this._fld9c].stringWidth(this._fld6g[this._fld9c]);
        this._fld4d = this._fld0h[this._fld9c].getHeight();
        if (this._fld1h[this._fld9c].equalsIgnoreCase("scrolldown")) {
            this._fld1d = this._fld5 - this._fld3d >> 1;
            this._fld2d = 0;
            return;
        }
        if (this._fld1h[this._fld9c].equalsIgnoreCase("scrollup")) {
            this._fld1d = this._fld5 - this._fld3d >> 1;
            this._fld2d = this._fld6 + this._fld4d;
            return;
        }
        if (this._fld1h[this._fld9c].equalsIgnoreCase("scrollright")) {
            this._fld1d = -this._fld3d;
            this._fld2d = this._fld3h[this._fld9c] + (this._fld4d >> 1) + (this._fld4d >> 3);
            return;
        }
        this._fld1d = this._fld5;
        this._fld2d = this._fld3h[this._fld9c] + (this._fld4d >> 1) + (this._fld4d >> 3);
    }
    
    void _mth1c(final Graphics graphics) {
        graphics.setFont(this._fld9g[this._fld9c]);
        graphics.setColor(this._fld8g[this._fld9c]);
        graphics.drawString(this._fld6g[this._fld9c], this._fld1d + 1, this._fld2d + 1);
        graphics.setColor(this._fld7g[this._fld9c]);
        graphics.drawString(this._fld6g[this._fld9c], this._fld1d, this._fld2d);
        if (this._fld1h[this._fld9c].equalsIgnoreCase("scrolldown")) {
            this._fld2d += this._fld2h[this._fld9c];
        }
        else if (this._fld1h[this._fld9c].equalsIgnoreCase("scrollup")) {
            this._fld2d -= this._fld2h[this._fld9c];
        }
        else if (this._fld1h[this._fld9c].equalsIgnoreCase("scrollright")) {
            this._fld1d += this._fld2h[this._fld9c];
        }
        else {
            this._fld1d -= this._fld2h[this._fld9c];
        }
        if (this._fld2d > this._fld6 + this._fld4d || this._fld2d < -this._fld4d || this._fld1d > this._fld5 || this._fld1d < -this._fld3d) {
            ++this._fld9c;
            if (this._fld9c >= this._fld0d) {
                this._fld9c = 0;
            }
            this._mth0c();
        }
    }
    
    void _mth2c() {
        final String parameter = this.getParameter("OverImage");
        if (parameter != null) {
            this._fld4c = this._mth9(parameter);
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
    
    public DS_BumpMap() {
        this._fld4 = new Font("Helvetica", 1, 12);
        this._fld3b = "Applet by Dario Sciacca";
        this._fld4b = "dario@dseffects.com";
        this._fld7b = "www.dseffects.com";
        this._fld8b = "Don't remove Dario Sciacca's credits line";
        this._fld9b = this._fld3b + " (" + this._fld7b + ")";
        this._fld0c = false;
        this._fld1c = false;
        this._fld0e = "BumpMap started";
        this._fld5e = new int[256];
        this._fld6e = new int[256];
        this._fld3f = false;
        this._fld4f = new int[256];
        this._fld7f = "";
        this._fld9f = "_blank";
        this._fld0g = "Applet by Dario Sciacca";
        this._fld2g = false;
    }
}
