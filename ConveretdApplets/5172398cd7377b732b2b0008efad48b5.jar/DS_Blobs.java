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

public class DS_Blobs extends Applet implements Runnable
{
    Thread _fld0;
    int _fld1;
    Font _fld2;
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
    int _fld1e;
    boolean _fld2e;
    int _fld3e;
    int[] _fld4e;
    int[] _fld5e;
    int[] _fld6e;
    int[] _fld7e;
    int[] _fld8e;
    int[] _fld9e;
    int[] tr;
    int[] tg;
    int[] tb;
    int _fld0f;
    int _fld1f;
    double _fld2f;
    int[] _fld3f;
    int _fld4f;
    int _fld5f;
    double _fld6f;
    String _fld7f;
    URL _fld8f;
    String _fld9f;
    String _fld0g;
    URL _fld1g;
    boolean _fld2g;
    byte[] _fld3g;
    byte[] _fld4g;
    String[] _fld5g;
    Color[] _fld6g;
    Color[] _fld7g;
    Font[] _fld8g;
    FontMetrics[] _fld9g;
    String[] _fld0h;
    int[] _fld1h;
    int[] _fld2h;
    
    public String getAppletInfo() {
        return "DS Blobs v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
    }
    
    public void init() {
        this._mth2();
        this.showStatus("Please wait ...");
        this._fld6b = this.getFontMetrics(this._fld2).stringWidth(this._fld7b);
        this._fld5 = this.size().width;
        this._fld6 = this.size().height;
        this._mth5();
        this._fld8 = new int[this._fld5 * this._fld6];
        this._fld9 = new MemoryImageSource(this._fld5, this._fld6, this._fld8, 0, this._fld5);
        this._fld2c = this.createImage(this._fld5, this._fld6);
        this._fld3c = this._fld2c.getGraphics();
        this._mth0c();
        this._mth3c();
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
        this._mth9b();
        if (this._mth8()) {
            this._mth6();
            this._mth3b();
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
                this._mth5b();
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
                this._mth2c(this._fld3c);
            }
            else if (this._fld5b == 2) {
                this._fld3c.drawImage(this._fld0b, 0, 0, this);
                this._fld3c.drawImage(this._fld4c, this._fld5c, this._fld6c, this);
            }
            else {
                this._fld3c.drawImage(this._fld0b, 0, 0, this);
                this._mth2c(this._fld3c);
                this._fld3c.drawImage(this._fld4c, this._fld5c, this._fld6c, this);
            }
        }
        if (this._fld2e && !this._fld2g) {
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
        this.showStatus(this._fld0g);
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
        return this._fld2e = true;
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
        String parameter = this.getParameter("blobsize");
        if (parameter == null) {
            parameter = "3";
        }
        this._fld5f = Integer.valueOf(parameter);
        this._fld5f = ((this._fld5f >= 1) ? ((this._fld5f <= 4) ? this._fld5f : 4) : 1);
        String parameter2 = this.getParameter("speed");
        if (parameter2 == null) {
            parameter2 = "4";
        }
        this._fld4f = Integer.valueOf(parameter2);
        this._fld4f = ((this._fld4f >= 1) ? ((this._fld4f <= 8) ? this._fld4f : 8) : 1);
        this._fld6f = this._fld4f / 100.0;
        String parameter3 = this.getParameter("palette");
        if (parameter3 == null) {
            parameter3 = "3";
        }
        this._fld1f = Integer.valueOf(parameter3);
        this._fld1f = ((this._fld1f >= 1) ? ((this._fld1f <= 8) ? this._fld1f : 8) : 1);
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
        this._fld0g = this._fld3b;
        final String parameter5 = this.getParameter("regkey");
        if (parameter5 != null) {
            this._fld7f = parameter5;
            final String parameter6 = this.getParameter("reglink");
            if (parameter6 != null) {
                try {
                    this._fld8f = new URL("http://" + parameter6);
                }
                catch (MalformedURLException ex) {
                    this._fld8f = null;
                }
                final String parameter7 = this.getParameter("regtarget");
                if (parameter7 != null) {
                    this._fld9f = parameter7;
                }
            }
            final String parameter8 = this.getParameter("regstatusmsg");
            if (parameter8 != null) {
                this._fld0g = parameter8;
            }
        }
    }
    
    void _mth3b() {
        this._fld0f = 256;
        this._fld4e = new int[this._fld0f];
        this._fld5e = new int[this._fld0f];
        this._fld6e = new int[this._fld0f];
        this._fld7e = new int[this._fld0f];
        this._fld8e = new int[this._fld0f];
        this._fld9e = new int[this._fld0f];
        this._mth6b();
        this._fld3e = 0;
        if (this._fld1e == 2) {
            for (int i = 0; i < this._fld0f; ++i) {
                this._fld4e[i] = this._fld7e[i];
                this._fld5e[i] = this._fld8e[i];
                this._fld6e[i] = this._fld9e[i];
            }
        }
        else {
            for (int j = 0; j < this._fld0f; ++j) {
                this._fld4e[j] = 0;
                this._fld5e[j] = 0;
                this._fld6e[j] = 0;
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
        this._fld2f = 0.0;
        this._fld3f = new int[256];
        for (int l = 0; l < 256; ++l) {
            this._fld3f[l] = (int)(this._fld5f * 21 * Math.exp(-0.05 * l));
        }
    }
    
    void _mth4b() {
        this._fld2f += this._fld6f;
        final int n = (int)((Math.sin(this._fld2f + 1.2) * 0.4 + 0.5) * this._fld5);
        final int n2 = (int)((Math.cos(this._fld2f * 1.2 + 1.8) * 0.4 + 0.5) * this._fld6);
        final int n3 = (int)((Math.sin(this._fld2f * 1.6 + 2.6) * 0.4 + 0.5) * this._fld5);
        final int n4 = (int)((Math.cos(this._fld2f * 1.8 + 3.8) * 0.4 + 0.5) * this._fld6);
        final int n5 = (int)((Math.sin(this._fld2f * 2.2 + 4.4) * 0.4 + 0.5) * this._fld5);
        final int n6 = (int)((Math.cos(this._fld2f * 2.6 + 4.8) * 0.4 + 0.5) * this._fld6);
        int n7 = 0;
        for (int i = 0; i < this._fld6; ++i) {
            for (int j = 0; j < this._fld5; ++j) {
                final int n8 = (j - n) * (j - n) + (i - n2) * (i - n2) >> 7;
                final int n9 = (j - n3) * (j - n3) + (i - n4) * (i - n4) >> 7;
                final int n10 = (j - n5) * (j - n5) + (i - n6) * (i - n6) >> 7;
                final int n11 = ((n8 > 255) ? 0 : this._fld3f[n8]) + ((n9 > 255) ? 0 : this._fld3f[n9]) + ((n10 > 255) ? 0 : this._fld3f[n10]);
                if (n11 == 0) {
                    this._fld8[n7] = this._fld7[n7];
                }
                else {
                    final int n12 = this.tr[n7] + this._fld4e[n11];
                    final int n13 = this.tg[n7] + this._fld5e[n11];
                    final int n14 = this.tb[n7] + this._fld6e[n11];
                    this._fld8[n7] = (0xFF000000 | ((n12 <= 255) ? n12 : 255) << 16 | ((n13 <= 255) ? n13 : 255) << 8 | ((n14 <= 255) ? n14 : 255));
                }
                ++n7;
            }
        }
    }
    
    void _mth5b() {
        if (this._fld1e == 0) {
            if (this._fld2e) {
                this._fld3e += 4;
                if (this._fld3e > this._fld0f) {
                    this._fld3e = this._fld0f;
                }
                else {
                    this._mth8b(this._fld3e, this._fld4e, this._fld5e, this._fld6e, this._fld7e, this._fld8e, this._fld9e);
                }
            }
            else {
                this._fld3e -= 4;
                if (this._fld3e < 0) {
                    this._fld3e = 0;
                }
                else {
                    this._mth8b(this._fld3e, this._fld4e, this._fld5e, this._fld6e, this._fld7e, this._fld8e, this._fld9e);
                }
            }
        }
        else if (this._fld1e == 1) {
            if (this._fld2e) {
                this._fld3e -= 4;
                if (this._fld3e < 0) {
                    this._fld3e = 0;
                }
                else {
                    this._mth8b(this._fld3e, this._fld4e, this._fld5e, this._fld6e, this._fld7e, this._fld8e, this._fld9e);
                }
            }
            else {
                this._fld3e += 4;
                if (this._fld3e > this._fld0f) {
                    this._fld3e = this._fld0f;
                }
                else {
                    this._mth8b(this._fld3e, this._fld4e, this._fld5e, this._fld6e, this._fld7e, this._fld8e, this._fld9e);
                }
            }
        }
        this._mth4b();
        this._fld0b = this.createImage(this._fld9);
    }
    
    void _mth6b() {
        switch (this._fld1f) {
            case 1: {
                this._mth7b(0, 255, 0, 0, 0, 0, 0, 255);
            }
            case 2: {
                this._mth7b(0, 255, 0, 0, 0, 255, 0, 0);
            }
            case 3: {
                this._mth7b(0, 255, 0, 255, 0, 0, 0, 0);
            }
            case 4: {
                this._mth7b(0, 255, 0, 255, 0, 255, 0, 0);
            }
            case 5: {
                this._mth7b(0, 255, 0, 255, 0, 0, 0, 255);
            }
            case 6: {
                this._mth7b(0, 255, 0, 0, 0, 255, 0, 255);
            }
            case 7: {
                this._mth7b(0, 255, 0, 255, 0, 255, 0, 255);
            }
            case 8: {
                this._mth7b(0, 255, 0, 127, 0, 127, 0, 255);
            }
            default: {}
        }
    }
    
    void _mth7b(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        final int n9 = n2 - n;
        final int n10 = n4 - n3;
        final int n11 = n6 - n5;
        final int n12 = n8 - n7;
        for (int i = 0; i < n9; ++i) {
            this._fld7e[n + i] = n3 + i * n10 / n9;
            this._fld8e[n + i] = n5 + i * n11 / n9;
            this._fld9e[n + i] = n7 + i * n12 / n9;
        }
    }
    
    void _mth8b(final int n, final int[] array, final int[] array2, final int[] array3, final int[] array4, final int[] array5, final int[] array6) {
        for (int i = 0; i < this._fld0f; ++i) {
            array[i] = array4[i * n >> 8];
            array2[i] = array5[i * n >> 8];
            array3[i] = array6[i * n >> 8];
        }
    }
    
    void _mth9b() {
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
            byte[] array;
            if (n > 4) {
                array = new byte[n - 4];
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld3g[k + 4];
                }
            }
            else {
                array = new byte[] { 0 };
            }
            if (this._fld4g[0] == this._fld4g[n >> 1] && this._fld4g[1 + n] == this._fld4g[1] && this._fld4g[1 + n + 1] == this._fld4g[n >> 1] && this._fld4g[1 + n + 2] == (byte)(97 + n5) && this._fld4g[1 + n + 3] == 45 && this._fld4g[1 + n + 4] == (byte)(122 - n6) && this._fld4g[1 + n + 5] == (byte)(110 + n5) && this._fld4g[1 + n + 6] == this._fld4g[1] && this._fld4g[1 + n + 7] == this._fld4g[n] && (host.equalsIgnoreCase(new String(this._fld3g, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
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
    
    void _mth0c() {
        int fld0d = 0;
        do {
            ++fld0d;
        } while (this.getParameter("overtext" + fld0d) != null);
        if (--fld0d > 0) {
            this._fld0c = true;
            this._fld0d = fld0d;
            this._fld5g = new String[this._fld0d];
            this._fld6g = new Color[this._fld0d];
            this._fld7g = new Color[this._fld0d];
            this._fld8g = new Font[this._fld0d];
            this._fld9g = new FontMetrics[this._fld0d];
            this._fld0h = new String[this._fld0d];
            this._fld1h = new int[this._fld0d];
            this._fld2h = new int[this._fld0d];
            for (int i = 0; i < this._fld0d; ++i) {
                this._fld5g[i] = this.getParameter("overtext" + String.valueOf(i + 1));
                this._fld6g[i] = this._mth1b("overtextcol" + String.valueOf(i + 1), new Color(16777215));
                this._fld7g[i] = this._mth1b("overtextcols" + String.valueOf(i + 1), new Color(0));
                final String parameter = this.getParameter("overtexty" + String.valueOf(i + 1));
                if (parameter == null) {
                    this._fld2h[i] = 10;
                }
                else {
                    this._fld2h[i] = Integer.parseInt(parameter);
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
                this._fld8g[i] = new Font(parameter2, n, int1);
                this._fld9g[i] = this._fld3c.getFontMetrics(this._fld8g[i]);
                this._fld0h[i] = this.getParameter("overTextType" + String.valueOf(i + 1));
                if (this._fld0h[i] == null) {
                    this._fld0h[i] = "scrollleft";
                }
                final String parameter5 = this.getParameter("overtextspeed" + String.valueOf(i + 1));
                if (parameter5 == null) {
                    this._fld1h[i] = 2;
                }
                else {
                    this._fld1h[i] = Integer.valueOf(parameter5);
                    if (this._fld1h[i] < 1 || this._fld1h[i] > 4) {
                        this._fld1h[i] = 2;
                    }
                }
            }
            this._mth1c();
        }
    }
    
    void _mth1c() {
        this._fld3d = this._fld9g[this._fld9c].stringWidth(this._fld5g[this._fld9c]);
        this._fld4d = this._fld9g[this._fld9c].getHeight();
        if (this._fld0h[this._fld9c].equalsIgnoreCase("scrolldown")) {
            this._fld1d = this._fld5 - this._fld3d >> 1;
            this._fld2d = 0;
            return;
        }
        if (this._fld0h[this._fld9c].equalsIgnoreCase("scrollup")) {
            this._fld1d = this._fld5 - this._fld3d >> 1;
            this._fld2d = this._fld6 + this._fld4d;
            return;
        }
        if (this._fld0h[this._fld9c].equalsIgnoreCase("scrollright")) {
            this._fld1d = -this._fld3d;
            this._fld2d = this._fld2h[this._fld9c] + (this._fld4d >> 1) + (this._fld4d >> 3);
            return;
        }
        this._fld1d = this._fld5;
        this._fld2d = this._fld2h[this._fld9c] + (this._fld4d >> 1) + (this._fld4d >> 3);
    }
    
    void _mth2c(final Graphics graphics) {
        graphics.setFont(this._fld8g[this._fld9c]);
        graphics.setColor(this._fld7g[this._fld9c]);
        graphics.drawString(this._fld5g[this._fld9c], this._fld1d + 1, this._fld2d + 1);
        graphics.setColor(this._fld6g[this._fld9c]);
        graphics.drawString(this._fld5g[this._fld9c], this._fld1d, this._fld2d);
        if (this._fld0h[this._fld9c].equalsIgnoreCase("scrolldown")) {
            this._fld2d += this._fld1h[this._fld9c];
        }
        else if (this._fld0h[this._fld9c].equalsIgnoreCase("scrollup")) {
            this._fld2d -= this._fld1h[this._fld9c];
        }
        else if (this._fld0h[this._fld9c].equalsIgnoreCase("scrollright")) {
            this._fld1d += this._fld1h[this._fld9c];
        }
        else {
            this._fld1d -= this._fld1h[this._fld9c];
        }
        if (this._fld2d > this._fld6 + this._fld4d || this._fld2d < -this._fld4d || this._fld1d > this._fld5 || this._fld1d < -this._fld3d) {
            ++this._fld9c;
            if (this._fld9c >= this._fld0d) {
                this._fld9c = 0;
            }
            this._mth1c();
        }
    }
    
    void _mth3c() {
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
    
    public DS_Blobs() {
        this._fld2 = new Font("Helvetica", 1, 12);
        this._fld3b = "Applet by Dario Sciacca";
        this._fld4b = "dario@dseffects.com";
        this._fld7b = "www.dseffects.com";
        this._fld8b = "Don't remove Dario Sciacca's credits line";
        this._fld9b = this._fld3b + " (" + this._fld7b + ")";
        this._fld0c = false;
        this._fld1c = false;
        this._fld0e = "Blobs started";
        this._fld2e = false;
        this._fld7f = "";
        this._fld9f = "_blank";
        this._fld0g = "Applet by Dario Sciacca";
        this._fld2g = false;
    }
}
