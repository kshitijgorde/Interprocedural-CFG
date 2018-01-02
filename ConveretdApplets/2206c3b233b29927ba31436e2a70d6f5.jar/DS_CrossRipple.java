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

public class DS_CrossRipple extends Applet implements Runnable
{
    Thread _fld0;
    int _fld1;
    Font _fld2;
    int _fld3;
    int _fld4;
    int[] _fld5;
    int[] _fld6;
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
    int _fld1e;
    boolean _fld2e;
    int _fld3e;
    int _fld4e;
    int _fld5e;
    int _fld6e;
    int[] _fld7e;
    int[] _fld8e;
    int _fld9e;
    int _fld0f;
    int _fld1f;
    int[] _fld2f;
    int _fld3f;
    int _fld4f;
    int _fld5f;
    int[] _fld6f;
    int[] _fld7f;
    int _fld8f;
    String _fld9f;
    URL[] _fld0g;
    String[] _fld1g;
    String[] _fld2g;
    URL _fld3g;
    boolean _fld4g;
    byte[] _fld5g;
    byte[] _fld6g;
    String[] _fld7g;
    Color[] _fld8g;
    Color[] _fld9g;
    Font[] _fld0h;
    FontMetrics[] _fld1h;
    String[] _fld2h;
    int[] _fld3h;
    int[] _fld4h;
    
    public String getAppletInfo() {
        return "DS CrossRipple v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
    }
    
    public void init() {
        this._mth2();
        this.showStatus("Please wait ...");
        this._fld6b = this.getFontMetrics(this._fld2).stringWidth(this._fld7b);
        this._fld3 = this.size().width;
        this._fld4 = this.size().height;
        this._mth3();
        this._fld8 = new int[this._fld3 * this._fld4];
        this._fld9 = new MemoryImageSource(this._fld3, this._fld4, this._fld8, 0, this._fld3);
        this._fld2c = this.createImage(this._fld3, this._fld4);
        this._fld3c = this._fld2c.getGraphics();
        this._mth7b();
        this._mth0c();
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
        if (this._mth6()) {
            this._mth0b();
            this._mth6b();
            this._mth4();
            this._mth1b();
            if (this._fld7d == -16777216) {
                this._fld1 = 1;
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
            if (this._fld1 == 1) {
                this._mth4b();
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
        final int n = this._fld3 >> 1;
        final int n2 = this._fld4 >> 1;
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
                this._mth9b(this._fld3c);
            }
            else if (this._fld5b == 2) {
                this._fld3c.drawImage(this._fld0b, 0, 0, this);
                this._fld3c.drawImage(this._fld4c, this._fld5c, this._fld6c, this);
            }
            else {
                this._fld3c.drawImage(this._fld0b, 0, 0, this);
                this._mth9b(this._fld3c);
                this._fld3c.drawImage(this._fld4c, this._fld5c, this._fld6c, this);
            }
        }
        if (this._fld2e && !this._fld4g) {
            this._fld3c.setColor(Color.white);
            this._fld3c.drawLine(n - 64, n2 - 8, n + 64, n2 - 8);
            this._fld3c.drawLine(n - 64, n2 + 8, n + 64, n2 + 8);
            this._fld3c.drawLine(n - 64, n2 - 8, n - 64, n2 + 8);
            this._fld3c.drawLine(n + 64, n2 - 8, n + 64, n2 + 8);
            this._fld3c.setColor(Color.blue);
            this._fld3c.fillRect(n - 63, n2 - 7, 127, 15);
            this._fld3c.setFont(this._fld2);
            this._fld3c.setColor(Color.yellow);
            this._fld3c.drawString(this._fld7b, n - (this._fld6b >> 1), n2 + 5);
        }
        graphics.drawImage(this._fld2c, 0, 0, this);
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this._fld2g[this._fld4e]);
        return this._fld2e = true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this._fld2e = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int fld8d, final int fld9d) {
        this._fld8d = fld8d;
        this._fld9d = fld9d;
        this._fld2e = true;
        this.showStatus(this._fld2g[this._fld4e]);
        return true;
    }
    
    void _mth2() {
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
    
    void _mth3() {
        this._fld6d = 1;
        for (int i = 0; i < 11; ++i) {
            if (this._fld4b.charAt(i) == this._fld3b.charAt(i) || this._fld5d == 0) {
                while (true) {
                    this.showStatus(this._fld8b);
                }
            }
            else {}
        }
        this._fld4g = false;
    }
    
    void _mth4() {
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
    
    int[] _mth5(final int n, final int[] array, final int n2, final int n3, final int[] array2, final int n4, final int n5) {
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
    
    boolean _mth6() {
        this._fld3e = 1;
        while (this.getParameter("image" + String.valueOf(this._fld3e)) != null) {
            ++this._fld3e;
        }
        --this._fld3e;
        if (this._fld3e >= 2) {
            final String[] array = new String[this._fld3e];
            this._fld5 = new int[this._fld3 * this._fld4 * this._fld3e];
            final Image[] array2 = new Image[this._fld3e];
            array2[0] = null;
            for (int i = 0; i < this._fld3e; ++i) {
                array[i] = this.getParameter("image" + String.valueOf(i + 1));
            }
            int n = 0;
            final int n2 = this._fld3 * this._fld4;
            for (int j = 0; j < this._fld3e; ++j) {
                array2[j] = this._mth7(array[j], j + 1);
                if (array2[j] == null) {
                    this.showStatus("Error loading image ");
                    return false;
                }
                final int width = array2[j].getWidth(this);
                final int height = array2[j].getHeight(this);
                if (width != this._fld3 || height != this._fld4) {
                    final int[] array3 = new int[width * height];
                    if (!this._mth8(0, array2[j], array3, width, height)) {
                        return false;
                    }
                    this._fld5 = this._mth5(n, this._fld5, this._fld3, this._fld4, array3, width, height);
                }
                else if (!this._mth8(n, array2[j], this._fld5, this._fld3, this._fld4)) {
                    return false;
                }
                this._fld0g = new URL[this._fld3e];
                this._fld1g = new String[this._fld3e];
                this._fld2g = new String[this._fld3e];
                array2[j].flush();
                array2[j] = null;
                System.gc();
                n += n2;
            }
            return true;
        }
        while (true) {
            this.showStatus("Error, at least 2 images required");
        }
    }
    
    Image _mth7(final String s, final int n) {
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
    
    boolean _mth8(final int n, final Image image, final int[] array, final int n2, final int n3) {
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
    
    Color _mth9(final String s, final Color color) {
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
    
    void _mth0b() {
        String parameter = this.getParameter("amplitude");
        if (parameter == null) {
            parameter = "30";
        }
        this._fld9e = Integer.valueOf(parameter);
        this._fld9e = ((this._fld9e >= 10) ? ((this._fld9e <= 60) ? this._fld9e : 60) : 10);
        String parameter2 = this.getParameter("frequency");
        if (parameter2 == null) {
            parameter2 = "20";
        }
        this._fld0f = Integer.valueOf(parameter2);
        this._fld0f = ((this._fld0f >= 10) ? ((this._fld0f <= 30) ? this._fld0f : 30) : 10);
        String parameter3 = this.getParameter("pause");
        if (parameter3 == null) {
            parameter3 = "50";
        }
        this._fld5f = Integer.valueOf(parameter3);
        this._fld5f = ((this._fld5f >= 0) ? ((this._fld5f <= 1000) ? this._fld5f : 1000) : 0);
        final String parameter4 = this.getParameter("interactive");
        if (parameter4 == null) {
            this._fld1e = 1;
        }
        else if (parameter4.equalsIgnoreCase("IN")) {
            this._fld1e = 0;
        }
        else if (parameter4.equalsIgnoreCase("OUT")) {
            this._fld1e = 1;
        }
        else {
            this._fld1e = 2;
        }
        for (int i = 1; i <= this._fld3e; ++i) {
            this._fld2g[i - 1] = this._fld3b;
        }
        final String parameter5 = this.getParameter("regkey");
        if (parameter5 != null) {
            this._fld9f = parameter5;
            for (int j = 1; j <= this._fld3e; ++j) {
                final String parameter6 = this.getParameter("reglink" + j);
                if (parameter6 != null) {
                    try {
                        this._fld0g[j - 1] = new URL("http://" + parameter6);
                    }
                    catch (MalformedURLException ex) {
                        this._fld0g[j - 1] = null;
                    }
                    final String parameter7 = this.getParameter("regtarget" + j);
                    if (parameter7 != null) {
                        this._fld1g[j - 1] = parameter7;
                    }
                }
                final String parameter8 = this.getParameter("regstatusmsg" + j);
                if (parameter8 != null) {
                    this._fld2g[j - 1] = parameter8;
                }
            }
        }
    }
    
    void _mth1b() {
        this._fld1f = 0;
        this._fld4e = 0;
        this._fld6f = new int[this._fld3 * this._fld4];
        this._fld7e = new int[this._fld3 * this._fld4];
        this._fld8e = new int[this._fld4];
        for (int i = 0; i < this._fld4; ++i) {
            for (int j = 0; j < this._fld3; ++j) {
                this._fld7e[i * this._fld3 + j] = (int)Math.sqrt(j * j + i * i);
            }
        }
        this._fld3f = (this._fld5f << 1) + (this._fld9e << 1);
        this._fld4f = this._fld3f >> 1;
        this._fld2f = new int[this._fld3f];
        this._fld7f = new int[this._fld3f];
        this._fld8f = this._fld9e << 1;
        for (int k = 0; k < this._fld5f; ++k) {
            this._fld2f[k] = 0;
        }
        for (int l = this._fld5f; l < this._fld9e + this._fld5f; ++l) {
            this._fld2f[l] = l - this._fld5f;
        }
        for (int n = 0; n < this._fld9e; ++n) {
            this._fld2f[this._fld5f + this._fld9e + n] = this._fld9e - n;
        }
        for (int n2 = 0; n2 < this._fld5f; ++n2) {
            this._fld2f[this._fld5f + this._fld9e + this._fld9e + n2] = 0;
        }
        for (int n3 = 0; n3 < this._fld5f; ++n3) {
            this._fld7f[n3] = 0;
        }
        for (int fld5f = this._fld5f; fld5f < this._fld3f - this._fld5f; ++fld5f) {
            this._fld7f[fld5f] = fld5f - this._fld5f;
        }
        for (int n4 = 0; n4 < this._fld5f; ++n4) {
            this._fld7f[this._fld5f + this._fld9e + this._fld9e + n4] = this._fld3f - this._fld5f - this._fld5f - 1;
        }
        this._fld6 = new int[this._fld3 * this._fld4];
        this._fld7 = new int[this._fld3 * this._fld4];
        for (int n5 = 0; n5 < this._fld3 * this._fld4; ++n5) {
            this._fld6[n5] = this._fld5[n5];
            this._fld7[n5] = this._fld5[n5 + this._fld3 * this._fld4];
        }
        this._fld5e = 0;
        this._fld6e = this._fld3 * this._fld4;
    }
    
    void _mth2b(final int[] array, final int[] array2) {
        final int n = this._fld3 >> 1;
        final int n2 = this._fld4 >> 1;
        int n3 = 0;
        for (int i = 0; i < this._fld4; ++i) {
            for (int j = 0; j < this._fld3; ++j) {
                final int n4 = this._fld8e[this._fld7e[Math.abs(i - n2) * this._fld3 + Math.abs(j - n)]];
                final int n5 = j;
                int n6 = i + n4;
                if (n6 > this._fld4 - 1) {
                    n6 = this._fld4 - 1;
                }
                if (n6 < 0) {
                    n6 = 0;
                }
                array[n3++] = array2[n6 * this._fld3 + n5];
            }
        }
    }
    
    void _mth3b() {
        if (this._fld1f > 0) {
            if (this._fld1f < this._fld3f) {
                --this._fld1f;
                return;
            }
            ++this._fld1f;
        }
    }
    
    void _mth4b() {
        if (this._fld1e == 0) {
            if (this._fld2e) {
                ++this._fld1f;
            }
            else {
                this._mth3b();
            }
        }
        else if (this._fld1e == 1) {
            if (this._fld2e) {
                this._mth3b();
            }
            else {
                ++this._fld1f;
            }
        }
        else {
            ++this._fld1f;
        }
        final int n = this._fld3 * this._fld4;
        final int n2 = this._fld3 * this._fld4 * this._fld3e;
        if (this._fld1f >= this._fld3f) {
            this._fld1f = 0;
            this._fld4e = (this._fld4e + 1) % this._fld3e;
            this._fld5e = (this._fld5e + n) % n2;
            this._fld6e = (this._fld6e + n) % n2;
            for (int i = 0; i < this._fld3 * this._fld4; ++i) {
                this._fld6[i] = this._fld5[i + this._fld5e];
                this._fld7[i] = this._fld5[i + this._fld6e];
            }
        }
        final int n3 = this._fld2f[this._fld1f];
        for (int j = 0; j < this._fld4; ++j) {
            this._fld8e[j] = (int)(n3 * Math.sin(this._fld0f * (j - this._fld1f) * 3.141592653589793 / 180.0));
        }
        this._mth5b(this._fld7f[this._fld1f] * 255 / this._fld8f, this._fld6f, this._fld6, this._fld7);
        this._mth2b(this._fld8, this._fld6f);
        this._fld0b = this.createImage(this._fld9);
    }
    
    void _mth5b(final int n, final int[] array, final int[] array2, final int[] array3) {
        for (int n2 = this._fld3 * this._fld4, i = 0; i < n2; ++i) {
            final int n3 = array2[i] >> 16 & 0xFF;
            final int n4 = n3 + (((array3[i] >> 16 & 0xFF) - n3) * n >> 8);
            final int n5 = array2[i] >> 8 & 0xFF;
            final int n6 = n5 + (((array3[i] >> 8 & 0xFF) - n5) * n >> 8);
            final int n7 = array2[i] & 0xFF;
            array[i] = (0xFF000000 | n4 << 16 | n6 << 8 | n7 + (((array3[i] & 0xFF) - n7) * n >> 8));
        }
    }
    
    void _mth6b() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this._fld9f.length() > 9) {
            final int n = this._fld9f.length() - 9;
            final int n2 = n + 9;
            this._fld5g = new byte[n];
            this._fld9f.getBytes(1, n + 1, this._fld5g, 0);
            this._fld6g = new byte[n2];
            this._fld9f.getBytes(0, n2, this._fld6g, 0);
            int n3 = n % 7;
            final int n4 = n % 3;
            for (int i = 0; i < n; ++i) {
                final byte b = this._fld5g[i];
                final byte b2 = (byte)(b + n3);
                if (b >= 48 && b <= 57) {
                    this._fld5g[i] = ((b2 <= 57) ? b2 : ((byte)(b2 - 10)));
                }
                else if (b >= 65 && b <= 90) {
                    this._fld5g[i] = ((b2 <= 90) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b >= 97 && b <= 122) {
                    this._fld5g[i] = ((b2 <= 122) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b == 42) {
                    this._fld5g[i] = 45;
                }
                else if (b == 45) {
                    this._fld5g[i] = 46;
                }
                n3 = (n3 + n4) % 7;
            }
            int n5 = n % 7;
            final int n6 = n % 3;
            for (int j = 0; j < n; ++j) {
                final byte b3 = this._fld5g[j];
                final byte b4 = (byte)(b3 - n5);
                if (b3 >= 48 && b3 <= 57) {
                    this._fld6g[j + 1] = ((b4 >= 48) ? b4 : ((byte)(b4 + 10)));
                }
                else if (b3 >= 65 && b3 <= 90) {
                    this._fld6g[j + 1] = ((b4 >= 65) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 >= 97 && b3 <= 122) {
                    this._fld6g[j + 1] = ((b4 >= 97) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 == 45) {
                    this._fld6g[j + 1] = 42;
                }
                else if (b3 == 46) {
                    this._fld6g[j + 1] = 45;
                }
                n5 = (n5 + n6) % 7;
            }
            final byte[] array = new byte[n];
            if (n > 4) {
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld5g[k + 4];
                }
            }
            if (this._fld6g[0] == this._fld6g[n >> 1] && this._fld6g[1 + n] == this._fld6g[1] && this._fld6g[1 + n + 1] == this._fld6g[n >> 1] && this._fld6g[1 + n + 2] == (byte)(97 + n5) && this._fld6g[1 + n + 3] == 45 && this._fld6g[1 + n + 4] == (byte)(122 - n6) && this._fld6g[1 + n + 5] == (byte)(110 + n5) && this._fld6g[1 + n + 6] == this._fld6g[1] && this._fld6g[1 + n + 7] == this._fld6g[n] && (host.equalsIgnoreCase(new String(this._fld5g, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this._fld4g = true;
            }
        }
        try {
            this._fld3g = new URL("http://" + this._fld7b);
        }
        catch (MalformedURLException ex) {
            this._fld3g = null;
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld4g) {
            this.getAppletContext().showDocument(this._fld3g, "_blank");
        }
        else if (this._fld0g[this._fld4e] != null) {
            if (this._fld1g[this._fld4e] != null) {
                this.getAppletContext().showDocument(this._fld0g[this._fld4e], this._fld1g[this._fld4e]);
            }
            else {
                this.getAppletContext().showDocument(this._fld0g[this._fld4e]);
            }
        }
        return true;
    }
    
    void _mth7b() {
        int fld0d = 0;
        do {
            ++fld0d;
        } while (this.getParameter("overtext" + fld0d) != null);
        if (--fld0d > 0) {
            this._fld0c = true;
            this._fld0d = fld0d;
            this._fld7g = new String[this._fld0d];
            this._fld8g = new Color[this._fld0d];
            this._fld9g = new Color[this._fld0d];
            this._fld0h = new Font[this._fld0d];
            this._fld1h = new FontMetrics[this._fld0d];
            this._fld2h = new String[this._fld0d];
            this._fld3h = new int[this._fld0d];
            this._fld4h = new int[this._fld0d];
            for (int i = 0; i < this._fld0d; ++i) {
                this._fld7g[i] = this.getParameter("overtext" + String.valueOf(i + 1));
                this._fld8g[i] = this._mth9("overtextcol" + String.valueOf(i + 1), new Color(16777215));
                this._fld9g[i] = this._mth9("overtextcols" + String.valueOf(i + 1), new Color(0));
                final String parameter = this.getParameter("overtexty" + String.valueOf(i + 1));
                if (parameter == null) {
                    this._fld4h[i] = 10;
                }
                else {
                    this._fld4h[i] = Integer.parseInt(parameter);
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
                this._fld0h[i] = new Font(parameter2, n, int1);
                this._fld1h[i] = this._fld3c.getFontMetrics(this._fld0h[i]);
                this._fld2h[i] = this.getParameter("overTextType" + String.valueOf(i + 1));
                if (this._fld2h[i] == null) {
                    this._fld2h[i] = "scrollleft";
                }
                final String parameter5 = this.getParameter("overtextspeed" + String.valueOf(i + 1));
                if (parameter5 == null) {
                    this._fld3h[i] = 2;
                }
                else {
                    this._fld3h[i] = Integer.valueOf(parameter5);
                    if (this._fld3h[i] < 1 || this._fld3h[i] > 4) {
                        this._fld3h[i] = 2;
                    }
                }
            }
            this._mth8b();
        }
    }
    
    void _mth8b() {
        this._fld3d = this._fld1h[this._fld9c].stringWidth(this._fld7g[this._fld9c]);
        this._fld4d = this._fld1h[this._fld9c].getHeight();
        if (this._fld2h[this._fld9c].equalsIgnoreCase("scrolldown")) {
            this._fld1d = this._fld3 - this._fld3d >> 1;
            this._fld2d = 0;
            return;
        }
        if (this._fld2h[this._fld9c].equalsIgnoreCase("scrollup")) {
            this._fld1d = this._fld3 - this._fld3d >> 1;
            this._fld2d = this._fld4 + this._fld4d;
            return;
        }
        if (this._fld2h[this._fld9c].equalsIgnoreCase("scrollright")) {
            this._fld1d = -this._fld3d;
            this._fld2d = this._fld4h[this._fld9c] + (this._fld4d >> 1) + (this._fld4d >> 3);
            return;
        }
        this._fld1d = this._fld3;
        this._fld2d = this._fld4h[this._fld9c] + (this._fld4d >> 1) + (this._fld4d >> 3);
    }
    
    void _mth9b(final Graphics graphics) {
        graphics.setFont(this._fld0h[this._fld9c]);
        graphics.setColor(this._fld9g[this._fld9c]);
        graphics.drawString(this._fld7g[this._fld9c], this._fld1d + 1, this._fld2d + 1);
        graphics.setColor(this._fld8g[this._fld9c]);
        graphics.drawString(this._fld7g[this._fld9c], this._fld1d, this._fld2d);
        if (this._fld2h[this._fld9c].equalsIgnoreCase("scrolldown")) {
            this._fld2d += this._fld3h[this._fld9c];
        }
        else if (this._fld2h[this._fld9c].equalsIgnoreCase("scrollup")) {
            this._fld2d -= this._fld3h[this._fld9c];
        }
        else if (this._fld2h[this._fld9c].equalsIgnoreCase("scrollright")) {
            this._fld1d += this._fld3h[this._fld9c];
        }
        else {
            this._fld1d -= this._fld3h[this._fld9c];
        }
        if (this._fld2d > this._fld4 + this._fld4d || this._fld2d < -this._fld4d || this._fld1d > this._fld3 || this._fld1d < -this._fld3d) {
            ++this._fld9c;
            if (this._fld9c >= this._fld0d) {
                this._fld9c = 0;
            }
            this._mth8b();
        }
    }
    
    void _mth0c() {
        final String parameter = this.getParameter("OverImage");
        if (parameter != null) {
            this._fld4c = this._mth7(parameter, 0);
        }
        if (this._fld4c != null) {
            this._fld1c = true;
            this._fld7c = this._fld4c.getWidth(this);
            this._fld8c = this._fld4c.getHeight(this);
            final String parameter2 = this.getParameter("OverImageX");
            if (parameter2 == null) {
                this._fld5c = (this._fld3 >> 1) - (this._fld7c >> 1);
            }
            else {
                this._fld5c = Integer.valueOf(parameter2);
            }
            final String parameter3 = this.getParameter("OverImageY");
            if (parameter3 == null) {
                this._fld6c = (this._fld4 >> 1) - (this._fld8c >> 1);
                return;
            }
            this._fld6c = Integer.valueOf(parameter3);
        }
    }
    
    public DS_CrossRipple() {
        this._fld2 = new Font("Helvetica", 1, 12);
        this._fld3b = "Applet by Dario Sciacca";
        this._fld4b = "dario@dseffects.com";
        this._fld7b = "www.dseffects.com";
        this._fld8b = "Don't remove Dario Sciacca's credits line";
        this._fld9b = this._fld3b + " (" + this._fld7b + ")";
        this._fld0c = false;
        this._fld1c = false;
        this._fld0e = "CrossRipple started";
        this._fld2e = false;
        this._fld9f = "";
        this._fld4g = false;
    }
}
