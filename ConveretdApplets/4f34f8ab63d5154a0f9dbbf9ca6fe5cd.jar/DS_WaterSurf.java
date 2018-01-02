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

public class DS_WaterSurf extends Applet implements Runnable
{
    Thread _fld0;
    int _fld3;
    Font _fld5;
    int _fld6;
    int _fld7;
    int[] _fld8;
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
    boolean _fld2e;
    boolean _fld3e;
    boolean _fld4e;
    boolean _fld5e;
    int _fld6e;
    int[] _fld7e;
    int[] _fld8e;
    int _fld9e;
    int[] _fld0f;
    int[] _fld1f;
    int _fld2f;
    int _fld3f;
    int _fld4f;
    int _fld5f;
    String _fld6f;
    URL _fld7f;
    String _fld8f;
    String _fld9f;
    URL _fld0g;
    boolean _fld1g;
    byte[] _fld2g;
    byte[] _fld3g;
    String[] _fld4g;
    Color[] _fld5g;
    Color[] _fld6g;
    Font[] _fld7g;
    FontMetrics[] _fld8g;
    String[] _fld9g;
    int[] _fld0h;
    int[] _fld1h;
    
    public String getAppletInfo() {
        return "DS WaterSurf v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
    }
    
    public void init() {
        this._mth5();
        this.showStatus("Please wait ...");
        this._fld7b = this.getFontMetrics(this._fld5).stringWidth(this._fld8b);
        this._fld6 = this.size().width;
        this._fld7 = this.size().height;
        this._mth6();
        this._fld9 = new int[this._fld6 * this._fld7];
        this._fld0b = new MemoryImageSource(this._fld6, this._fld7, this._fld9, 0, this._fld6);
        this._fld3c = this.createImage(this._fld6, this._fld7);
        this._fld4c = this._fld3c.getGraphics();
        this._mth9b();
        this._mth2c();
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
        this._mth8b();
        if (this._mth9()) {
            this._mth7();
            this._mth4b();
            if (this._fld8d == -16777216) {
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
        this.showStatus(this._fld1e);
        System.gc();
        final Graphics graphics = this.getGraphics();
        this._fld3b = System.currentTimeMillis();
        while (this._fld0 != null) {
            if (this._fld3 == 1) {
                this._mth5b();
            }
            this._mth3(graphics);
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
    
    void _mth3(final Graphics graphics) {
        final int n = this._fld6 >> 1;
        final int n2 = this._fld7 >> 1;
        if (this._fld3 == 0) {
            graphics.drawString("Error ...", 10, 10);
            return;
        }
        if (this._fld1b != null) {
            if (this._fld6b == 0) {
                this._fld4c.drawImage(this._fld1b, 0, 0, this);
            }
            else if (this._fld6b == 1) {
                this._fld4c.drawImage(this._fld1b, 0, 0, this);
                this._mth1c(this._fld4c);
            }
            else if (this._fld6b == 2) {
                this._fld4c.drawImage(this._fld1b, 0, 0, this);
                this._fld4c.drawImage(this._fld5c, this._fld6c, this._fld7c, this);
            }
            else {
                this._fld4c.drawImage(this._fld1b, 0, 0, this);
                this._mth1c(this._fld4c);
                this._fld4c.drawImage(this._fld5c, this._fld6c, this._fld7c, this);
            }
        }
        if (this._fld3e && !this._fld1g) {
            this._fld4c.setColor(Color.white);
            this._fld4c.drawLine(n - 64, n2 - 8, n + 64, n2 - 8);
            this._fld4c.drawLine(n - 64, n2 + 8, n + 64, n2 + 8);
            this._fld4c.drawLine(n - 64, n2 - 8, n - 64, n2 + 8);
            this._fld4c.drawLine(n + 64, n2 - 8, n + 64, n2 + 8);
            this._fld4c.setColor(Color.blue);
            this._fld4c.fillRect(n - 63, n2 - 7, 127, 15);
            this._fld4c.setFont(this._fld5);
            this._fld4c.setColor(Color.yellow);
            this._fld4c.drawString(this._fld8b, n - (this._fld7b >> 1), n2 + 5);
        }
        graphics.drawImage(this._fld3c, 0, 0, this);
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this._fld9f);
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
        this._fld3e = true;
        return this._fld5e = true;
    }
    
    void _mth5() {
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
    
    void _mth6() {
        this._fld7d = 1;
        for (int i = 0; i < 11; ++i) {
            if (this._fld5b.charAt(i) == this._fld4b.charAt(i) || this._fld6d == 0) {
                while (true) {
                    this.showStatus(this._fld9b);
                }
            }
            else {}
        }
        this._fld1g = false;
    }
    
    void _mth7() {
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
    
    int[] _mth8(final int[] array, final int n, final int n2, final int[] array2, final int n3, final int n4) {
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
        this._fld8 = new int[this._fld6 * this._fld7];
        if (width != this._fld6 || height != this._fld7) {
            final int[] array = new int[width * height];
            if (!this._mth1b(mth0b, array, width, height)) {
                return false;
            }
            this._fld8 = this._mth8(this._fld8, this._fld6, this._fld7, array, width, height);
        }
        else if (!this._mth1b(mth0b, this._fld8, this._fld6, this._fld7)) {
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
        String parameter = this.getParameter("density");
        if (parameter == null) {
            parameter = "4";
        }
        this._fld9e = Integer.valueOf(parameter);
        this._fld9e = ((this._fld9e >= 1) ? ((this._fld9e <= 5) ? this._fld9e : 5) : 1);
        ++this._fld9e;
        final String parameter2 = this.getParameter("interactive");
        if (parameter2 == null) {
            this._fld2e = true;
        }
        else if (parameter2.equalsIgnoreCase("YES")) {
            this._fld2e = true;
        }
        else {
            this._fld2e = false;
        }
        String parameter3 = this.getParameter("speedx");
        if (parameter3 == null) {
            parameter3 = "1";
        }
        this._fld4f = Integer.valueOf(parameter3);
        this._fld4f = ((this._fld4f >= 1) ? ((this._fld4f <= 4) ? this._fld4f : 4) : 1);
        String parameter4 = this.getParameter("speedy");
        if (parameter4 == null) {
            parameter4 = "2";
        }
        this._fld5f = Integer.valueOf(parameter4);
        this._fld5f = ((this._fld5f >= 1) ? ((this._fld5f <= 4) ? this._fld5f : 4) : 1);
        this._fld9f = this._fld4b;
        final String parameter5 = this.getParameter("regkey");
        if (parameter5 != null) {
            this._fld6f = parameter5;
            final String parameter6 = this.getParameter("reglink");
            if (parameter6 != null) {
                try {
                    this._fld7f = new URL("http://" + parameter6);
                }
                catch (MalformedURLException ex) {
                    this._fld7f = null;
                }
                final String parameter7 = this.getParameter("regtarget");
                if (parameter7 != null) {
                    this._fld8f = parameter7;
                }
            }
            final String parameter8 = this.getParameter("regstatusmsg");
            if (parameter8 != null) {
                this._fld9f = parameter8;
            }
        }
    }
    
    void _mth4b() {
        this._fld6e = 0;
        this._fld7e = new int[this._fld6 * this._fld7 + this._fld6];
        this._fld8e = new int[this._fld6 * this._fld7 + this._fld6];
        final int n = this._fld6 >> 1;
        final int n2 = this._fld7 >> 1;
        final int n3 = n - (n >> 3);
        final int n4 = n2 - (n2 >> 3);
        for (int i = 0; i < 256; ++i) {
            this._fld0f[i] = (int)(Math.cos(0.02454369260617026 * i) * n3);
            this._fld1f[i] = (int)(Math.sin(0.02454369260617026 * i) * n4);
        }
    }
    
    void _mth5b() {
        if (this._fld6e == 0) {
            this._mth7b(this._fld7e);
            this._mth6b(this._fld7e, this._fld8e);
        }
        else {
            this._mth7b(this._fld8e);
            this._mth6b(this._fld8e, this._fld7e);
        }
        this._fld1b = this.createImage(this._fld0b);
    }
    
    void _mth6b(final int[] array, final int[] array2) {
        final int n = this._fld6 * this._fld7;
        final int n2 = n - this._fld6;
        final int n3 = this._fld6 << 1;
        for (int i = 0; i < this._fld6; ++i) {
            this._fld9[i] = this._fld8[i];
            array[i] = 0;
        }
        for (int j = this._fld6; j < n2; ++j) {
            final int n4 = ((array2[j] - array2[j + 2] >> 3) + (array2[j] - array2[j + n3]) >> 3) * this._fld6 + j;
            this._fld9[j] = this._fld8[(n4 <= 0) ? (-n4 % n) : (n4 % n)];
            array[j] = (array2[j - 1] + array2[j + 1] + array2[j - this._fld6] + array2[j + this._fld6] >> 1) - array[j];
            final int n5 = j;
            array[n5] -= array[j] >> this._fld9e;
        }
        for (int k = 1; k < this._fld7 - 1; ++k) {
            final int n6 = k * this._fld6;
            final int n7 = n6 + this._fld6 - 1;
            this._fld9[n6] = this._fld8[n6];
            array[n6] = 0;
            this._fld9[n7] = this._fld8[n7];
            array[n7] = 0;
        }
        for (int l = n2; l < n; ++l) {
            this._fld9[l] = this._fld8[l];
            array[l] = 0;
        }
        this._fld6e ^= 0x1;
    }
    
    void _mth7b(final int[] array) {
        final int n = this._fld6 >> 1;
        final int n2 = this._fld7 >> 1;
        this._fld2f = (this._fld2f + this._fld4f) % 256;
        this._fld3f = (this._fld3f + this._fld5f) % 256;
        this._fld4e = true;
        int fld9d;
        int fld0e;
        if (this._fld2e) {
            if (!this._fld3e) {
                fld9d = n + this._fld0f[this._fld2f];
                fld0e = n2 + this._fld1f[this._fld3f];
            }
            else {
                fld9d = this._fld9d;
                fld0e = this._fld0e;
                if (!this._fld5e) {
                    this._fld4e = false;
                }
            }
        }
        else {
            fld9d = n + this._fld0f[this._fld2f];
            fld0e = n2 + this._fld1f[this._fld3f];
        }
        if (this._fld4e) {
            array[this._fld6 * fld0e + fld9d] = 400;
            final int n3 = this._fld6 * (fld0e - 1) + fld9d;
            final int n4 = this._fld6 * (fld0e + 1) + fld9d;
            final int n5 = this._fld6 * fld0e + fld9d - this._fld6;
            final int n6 = this._fld6 * fld0e + fld9d + this._fld6;
            final int n7 = 200;
            array[n5] = (array[n6] = n7);
            array[n3] = (array[n4] = n7);
        }
        this._fld5e = false;
    }
    
    void _mth8b() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this._fld6f.length() > 9) {
            final int n = this._fld6f.length() - 9;
            final int n2 = n + 9;
            this._fld2g = new byte[n];
            this._fld6f.getBytes(1, n + 1, this._fld2g, 0);
            this._fld3g = new byte[n2];
            this._fld6f.getBytes(0, n2, this._fld3g, 0);
            int n3 = n % 7;
            final int n4 = n % 3;
            for (int i = 0; i < n; ++i) {
                final byte b = this._fld2g[i];
                final byte b2 = (byte)(b + n3);
                if (b >= 48 && b <= 57) {
                    this._fld2g[i] = ((b2 <= 57) ? b2 : ((byte)(b2 - 10)));
                }
                else if (b >= 65 && b <= 90) {
                    this._fld2g[i] = ((b2 <= 90) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b >= 97 && b <= 122) {
                    this._fld2g[i] = ((b2 <= 122) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b == 42) {
                    this._fld2g[i] = 45;
                }
                else if (b == 45) {
                    this._fld2g[i] = 46;
                }
                n3 = (n3 + n4) % 7;
            }
            int n5 = n % 7;
            final int n6 = n % 3;
            for (int j = 0; j < n; ++j) {
                final byte b3 = this._fld2g[j];
                final byte b4 = (byte)(b3 - n5);
                if (b3 >= 48 && b3 <= 57) {
                    this._fld3g[j + 1] = ((b4 >= 48) ? b4 : ((byte)(b4 + 10)));
                }
                else if (b3 >= 65 && b3 <= 90) {
                    this._fld3g[j + 1] = ((b4 >= 65) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 >= 97 && b3 <= 122) {
                    this._fld3g[j + 1] = ((b4 >= 97) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 == 45) {
                    this._fld3g[j + 1] = 42;
                }
                else if (b3 == 46) {
                    this._fld3g[j + 1] = 45;
                }
                n5 = (n5 + n6) % 7;
            }
            final byte[] array = new byte[n];
            if (n > 4) {
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld2g[k + 4];
                }
            }
            if (this._fld3g[0] == this._fld3g[n >> 1] && this._fld3g[1 + n] == this._fld3g[1] && this._fld3g[1 + n + 1] == this._fld3g[n >> 1] && this._fld3g[1 + n + 2] == (byte)(97 + n5) && this._fld3g[1 + n + 3] == 45 && this._fld3g[1 + n + 4] == (byte)(122 - n6) && this._fld3g[1 + n + 5] == (byte)(110 + n5) && this._fld3g[1 + n + 6] == this._fld3g[1] && this._fld3g[1 + n + 7] == this._fld3g[n] && (host.equalsIgnoreCase(new String(this._fld2g, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this._fld1g = true;
            }
        }
        try {
            this._fld0g = new URL("http://" + this._fld8b);
        }
        catch (MalformedURLException ex) {
            this._fld0g = null;
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld1g) {
            this.getAppletContext().showDocument(this._fld0g, "_blank");
        }
        else if (this._fld7f != null) {
            if (this._fld8f != null) {
                this.getAppletContext().showDocument(this._fld7f, this._fld8f);
            }
            else {
                this.getAppletContext().showDocument(this._fld7f);
            }
        }
        return true;
    }
    
    void _mth9b() {
        int fld1d = 0;
        do {
            ++fld1d;
        } while (this.getParameter("overtext" + fld1d) != null);
        if (--fld1d > 0) {
            this._fld1c = true;
            this._fld1d = fld1d;
            this._fld4g = new String[this._fld1d];
            this._fld5g = new Color[this._fld1d];
            this._fld6g = new Color[this._fld1d];
            this._fld7g = new Font[this._fld1d];
            this._fld8g = new FontMetrics[this._fld1d];
            this._fld9g = new String[this._fld1d];
            this._fld0h = new int[this._fld1d];
            this._fld1h = new int[this._fld1d];
            for (int i = 0; i < this._fld1d; ++i) {
                this._fld4g[i] = this.getParameter("overtext" + String.valueOf(i + 1));
                this._fld5g[i] = this._mth2b("overtextcol" + String.valueOf(i + 1), new Color(16777215));
                this._fld6g[i] = this._mth2b("overtextcols" + String.valueOf(i + 1), new Color(0));
                final String parameter = this.getParameter("overtexty" + String.valueOf(i + 1));
                if (parameter == null) {
                    this._fld1h[i] = 10;
                }
                else {
                    this._fld1h[i] = Integer.parseInt(parameter);
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
                this._fld7g[i] = new Font(parameter2, n, int1);
                this._fld8g[i] = this._fld4c.getFontMetrics(this._fld7g[i]);
                this._fld9g[i] = this.getParameter("overTextType" + String.valueOf(i + 1));
                if (this._fld9g[i] == null) {
                    this._fld9g[i] = "scrollleft";
                }
                final String parameter5 = this.getParameter("overtextspeed" + String.valueOf(i + 1));
                if (parameter5 == null) {
                    this._fld0h[i] = 2;
                }
                else {
                    this._fld0h[i] = Integer.valueOf(parameter5);
                    if (this._fld0h[i] < 1 || this._fld0h[i] > 4) {
                        this._fld0h[i] = 2;
                    }
                }
            }
            this._mth0c();
        }
    }
    
    void _mth0c() {
        this._fld4d = this._fld8g[this._fld0d].stringWidth(this._fld4g[this._fld0d]);
        this._fld5d = this._fld8g[this._fld0d].getHeight();
        if (this._fld9g[this._fld0d].equalsIgnoreCase("scrolldown")) {
            this._fld2d = this._fld6 - this._fld4d >> 1;
            this._fld3d = 0;
            return;
        }
        if (this._fld9g[this._fld0d].equalsIgnoreCase("scrollup")) {
            this._fld2d = this._fld6 - this._fld4d >> 1;
            this._fld3d = this._fld7 + this._fld5d;
            return;
        }
        if (this._fld9g[this._fld0d].equalsIgnoreCase("scrollright")) {
            this._fld2d = -this._fld4d;
            this._fld3d = this._fld1h[this._fld0d] + (this._fld5d >> 1) + (this._fld5d >> 3);
            return;
        }
        this._fld2d = this._fld6;
        this._fld3d = this._fld1h[this._fld0d] + (this._fld5d >> 1) + (this._fld5d >> 3);
    }
    
    void _mth1c(final Graphics graphics) {
        graphics.setFont(this._fld7g[this._fld0d]);
        graphics.setColor(this._fld6g[this._fld0d]);
        graphics.drawString(this._fld4g[this._fld0d], this._fld2d + 1, this._fld3d + 1);
        graphics.setColor(this._fld5g[this._fld0d]);
        graphics.drawString(this._fld4g[this._fld0d], this._fld2d, this._fld3d);
        if (this._fld9g[this._fld0d].equalsIgnoreCase("scrolldown")) {
            this._fld3d += this._fld0h[this._fld0d];
        }
        else if (this._fld9g[this._fld0d].equalsIgnoreCase("scrollup")) {
            this._fld3d -= this._fld0h[this._fld0d];
        }
        else if (this._fld9g[this._fld0d].equalsIgnoreCase("scrollright")) {
            this._fld2d += this._fld0h[this._fld0d];
        }
        else {
            this._fld2d -= this._fld0h[this._fld0d];
        }
        if (this._fld3d > this._fld7 + this._fld5d || this._fld3d < -this._fld5d || this._fld2d > this._fld6 || this._fld2d < -this._fld4d) {
            ++this._fld0d;
            if (this._fld0d >= this._fld1d) {
                this._fld0d = 0;
            }
            this._mth0c();
        }
    }
    
    void _mth2c() {
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
                this._fld6c = (this._fld6 >> 1) - (this._fld8c >> 1);
            }
            else {
                this._fld6c = Integer.valueOf(parameter2);
            }
            final String parameter3 = this.getParameter("OverImageY");
            if (parameter3 == null) {
                this._fld7c = (this._fld7 >> 1) - (this._fld9c >> 1);
                return;
            }
            this._fld7c = Integer.valueOf(parameter3);
        }
    }
    
    public DS_WaterSurf() {
        this._fld5 = new Font("Helvetica", 1, 12);
        this._fld4b = "Applet by Dario Sciacca";
        this._fld5b = "dario@dseffects.com";
        this._fld8b = "www.dseffects.com";
        this._fld9b = "Don't remove Dario Sciacca's credits line";
        this._fld0c = this._fld4b + " (" + this._fld8b + ")";
        this._fld1c = false;
        this._fld2c = false;
        this._fld1e = "WaterSurf started";
        this._fld3e = false;
        this._fld5e = false;
        this._fld0f = new int[256];
        this._fld1f = new int[256];
        this._fld6f = "";
        this._fld8f = "_blank";
        this._fld9f = "Applet by Dario Sciacca";
        this._fld1g = false;
    }
}
