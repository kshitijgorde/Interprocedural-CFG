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

public class DS_FadeToFilter extends Applet implements Runnable
{
    Thread _fld1;
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
    int _fld1e;
    boolean _fld2e;
    int[] _fld3e;
    int[] _fld4e;
    int _fld5e;
    int _fld6e;
    int _fld7e;
    int _fld8e;
    int _fld9e;
    int _fld0f;
    String _fld1f;
    URL _fld2f;
    String _fld3f;
    String _fld4f;
    URL _fld5f;
    boolean _fld6f;
    byte[] _fld7f;
    byte[] _fld8f;
    String[] _fld9f;
    Color[] _fld0g;
    Color[] _fld1g;
    Font[] _fld2g;
    FontMetrics[] _fld3g;
    String[] _fld4g;
    int[] _fld5g;
    int[] _fld6g;
    
    public String getAppletInfo() {
        return "DS FadeToFilter v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
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
        this._mth3c();
        this._mth6c();
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
        this._mth2c();
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
        (this._fld1 = new Thread(this)).start();
    }
    
    public void stop() {
        if (this._fld1 != null) {
            this._fld1.stop();
            this._fld1 = null;
        }
    }
    
    public void run() {
        this.showStatus(this._fld0e);
        System.gc();
        final Graphics graphics = this.getGraphics();
        this._fld2b = System.currentTimeMillis();
        while (this._fld1 != null) {
            if (this._fld3 == 1) {
                this._mth5b();
            }
            this._mth3(graphics);
            this._mth1();
            if (this._fld1b++ > 10) {
                System.gc();
                this._fld1b = 0;
            }
        }
    }
    
    synchronized void _mth1() {
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
                this._fld3c.drawImage(this._fld0b, 0, 0, this);
            }
            else if (this._fld5b == 1) {
                this._fld3c.drawImage(this._fld0b, 0, 0, this);
                this._mth5c(this._fld3c);
            }
            else if (this._fld5b == 2) {
                this._fld3c.drawImage(this._fld0b, 0, 0, this);
                this._fld3c.drawImage(this._fld4c, this._fld5c, this._fld6c, this);
            }
            else {
                this._fld3c.drawImage(this._fld0b, 0, 0, this);
                this._mth5c(this._fld3c);
                this._fld3c.drawImage(this._fld4c, this._fld5c, this._fld6c, this);
            }
        }
        if (this._fld2e && !this._fld6f) {
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
        this.showStatus(this._fld4f);
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
        this._fld6f = false;
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
        String parameter = this.getParameter("speed");
        if (parameter == null) {
            parameter = "2";
        }
        this._fld8e = Integer.valueOf(parameter);
        this._fld8e = ((this._fld8e >= 1) ? ((this._fld8e <= 8) ? this._fld8e : 8) : 1);
        String parameter2 = this.getParameter("pause");
        if (parameter2 == null) {
            parameter2 = "0";
        }
        this._fld9e = Integer.valueOf(parameter2);
        this._fld9e = ((this._fld9e >= 0) ? ((this._fld9e <= 1000) ? this._fld9e : 1000) : 0);
        final String parameter3 = this.getParameter("filter");
        if (parameter3 == null) {
            this._fld0f = 0;
        }
        else if (parameter3.equalsIgnoreCase("grey")) {
            this._fld0f = 1;
        }
        else if (parameter3.equalsIgnoreCase("blackwhite")) {
            this._fld0f = 2;
        }
        else if (parameter3.equalsIgnoreCase("oil")) {
            this._fld0f = 3;
        }
        else if (parameter3.equalsIgnoreCase("lineart")) {
            this._fld0f = 4;
        }
        else {
            this._fld0f = 0;
        }
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
        this._fld4f = this._fld3b;
        final String parameter5 = this.getParameter("regkey");
        if (parameter5 != null) {
            this._fld1f = parameter5;
            final String parameter6 = this.getParameter("reglink");
            if (parameter6 != null) {
                try {
                    this._fld2f = new URL("http://" + parameter6);
                }
                catch (MalformedURLException ex) {
                    this._fld2f = null;
                }
                final String parameter7 = this.getParameter("regtarget");
                if (parameter7 != null) {
                    this._fld3f = parameter7;
                }
            }
            final String parameter8 = this.getParameter("regstatusmsg");
            if (parameter8 != null) {
                this._fld4f = parameter8;
            }
        }
    }
    
    void _mth3b() {
        this._fld5e = 0;
        this._fld3e = new int[this._fld5 * this._fld6];
        this._fld9e *= this._fld8e;
        this._fld6e = (this._fld9e << 1) + 510;
        this._fld7e = this._fld6e >> 1;
        this._fld4e = new int[this._fld6e];
        for (int i = 0; i < this._fld9e; ++i) {
            this._fld4e[i] = 0;
        }
        for (int j = this._fld9e; j < 255 + this._fld9e; ++j) {
            this._fld4e[j] = j - this._fld9e;
        }
        for (int k = 0; k < 255; ++k) {
            this._fld4e[this._fld9e + 255 + k] = 255 - k;
        }
        for (int l = 0; l < this._fld9e; ++l) {
            this._fld4e[this._fld9e + 255 + 255 + l] = 0;
        }
        switch (this._fld0f) {
            case 0: {
                this._mth9b(this._fld3e, this._fld7);
            }
            case 1: {
                this._mth0c(this._fld3e, this._fld7);
            }
            case 2: {
                this._mth8b(this._fld3e, this._fld7);
            }
            case 3: {
                this._mth7b(4, this._fld3e, this._fld7);
            }
            case 4: {
                this._mth1c(4, this._fld3e, this._fld7);
            }
            default: {}
        }
    }
    
    void _mth4b() {
        if (this._fld5e > 0) {
            if (this._fld5e < this._fld7e) {
                this._fld5e -= this._fld8e;
                return;
            }
            this._fld5e += this._fld8e;
        }
    }
    
    void _mth5b() {
        if (this._fld1e == 0) {
            if (this._fld2e) {
                this._fld5e += this._fld8e;
            }
            else {
                this._mth4b();
            }
        }
        else if (this._fld1e == 1) {
            if (this._fld2e) {
                this._mth4b();
            }
            else {
                this._fld5e += this._fld8e;
            }
        }
        else {
            this._fld5e += this._fld8e;
        }
        this._fld5e = (this._fld5e + this._fld6e) % this._fld6e;
        this._mth6b(this._fld4e[this._fld5e], this._fld8, this._fld7, this._fld3e);
        this._fld0b = this.createImage(this._fld9);
    }
    
    void _mth6b(final int n, final int[] array, final int[] array2, final int[] array3) {
        for (int n2 = this._fld5 * this._fld6, i = 0; i < n2; ++i) {
            final int n3 = array2[i] >> 16 & 0xFF;
            final int n4 = n3 + (((array3[i] >> 16 & 0xFF) - n3) * n >> 8);
            final int n5 = array2[i] >> 8 & 0xFF;
            final int n6 = n5 + (((array3[i] >> 8 & 0xFF) - n5) * n >> 8);
            final int n7 = array2[i] & 0xFF;
            array[i] = (0xFF000000 | n4 << 16 | n6 << 8 | n7 + (((array3[i] & 0xFF) - n7) * n >> 8));
        }
    }
    
    void _mth7b(final int n, final int[] array, final int[] array2) {
        final int[] array3 = new int[256];
        final int[] array4 = new int[256];
        final int[] array5 = new int[256];
        int n2 = 0;
        for (int i = 0; i < this._fld6; ++i) {
            for (int j = 0; j < this._fld5; ++j) {
                for (int k = 0; k < 256; ++k) {
                    final int[] array6 = array3;
                    final int n3 = k;
                    final int[] array7 = array4;
                    final int n4 = k;
                    final int[] array8 = array5;
                    final int n5 = k;
                    final boolean b = false;
                    array8[n5] = (b ? 1 : 0);
                    array6[n3] = (array7[n4] = (b ? 1 : 0));
                }
                for (int l = -n; l <= n; ++l) {
                    final int n6 = i + l;
                    if (n6 >= 0 && n6 < this._fld6) {
                        final int n7 = n6 * this._fld5;
                        for (int n8 = -n; n8 <= n; ++n8) {
                            final int n9 = j + n8;
                            if (n9 >= 0 && n9 < this._fld5) {
                                final int n10 = array2[n7 + n9];
                                final int[] array9 = array3;
                                final int n11 = n10 >> 16 & 0xFF;
                                ++array9[n11];
                                final int[] array10 = array4;
                                final int n12 = n10 >> 8 & 0xFF;
                                ++array10[n12];
                                final int[] array11 = array5;
                                final int n13 = n10 & 0xFF;
                                ++array11[n13];
                            }
                        }
                    }
                }
                int n14 = 0;
                int n15 = 0;
                int n16 = 0;
                for (int n17 = 1; n17 < 256; ++n17) {
                    if (array3[n17] > array3[n14]) {
                        n14 = n17;
                    }
                    if (array4[n17] > array4[n15]) {
                        n15 = n17;
                    }
                    if (array5[n17] > array5[n16]) {
                        n16 = n17;
                    }
                }
                array[n2++] = (0xFF000000 | n14 << 16 | n15 << 8 | n16);
            }
        }
    }
    
    void _mth8b(final int[] array, final int[] array2) {
        for (int i = 0; i < this._fld6; ++i) {
            for (int j = 0; j < this._fld5; ++j) {
                final int n = array2[i * this._fld5 + j];
                if (3 * (n >> 16 & 0xFF) + 2 * (n & 0xFF) + 5 * (n >> 8 & 0xFF) <= 1270) {
                    array[i * this._fld5 + j] = -16777216;
                }
                else {
                    array[i * this._fld5 + j] = -1;
                }
            }
        }
    }
    
    void _mth9b(final int[] array, final int[] array2) {
        for (int n = this._fld5 * this._fld6, i = 0; i < n; ++i) {
            array[i] = (0xFF000000 | (array2[i] ^ 0xFFFFFF));
        }
    }
    
    void _mth0c(final int[] array, final int[] array2) {
        for (int n = this._fld5 * this._fld6, i = 0; i < n; ++i) {
            final int n2 = array2[i];
            final int n3 = (int)(0.2125 * ((n2 & 0xFF0000) >> 16) + 0.7154 * ((n2 & 0xFF00) >> 8) + 0.0721 * (n2 & 0xFF));
            array[i] = (0xFF000000 | (n3 << 16 | n3 << 8 | n3));
        }
    }
    
    void _mth1c(final int n, final int[] array, final int[] array2) {
        for (int i = 0; i < this._fld5; ++i) {
            array2[i] = -16777216;
        }
        for (int n2 = this._fld5 * this._fld6, j = this._fld5; j < n2; ++j) {
            final int n3 = array2[j - 1];
            final int n4 = (n3 & 0xFF0000) >> 16;
            final int n5 = (n3 & 0xFF00) >> 8;
            final int n6 = n3 & 0xFF;
            final int n7 = array2[j - this._fld5];
            final int n8 = (n7 & 0xFF0000) >> 16;
            final int n9 = (n7 & 0xFF00) >> 8;
            final int n10 = n7 & 0xFF;
            final int n11 = array2[j];
            final int n12 = (n11 & 0xFF0000) >> 16;
            final int n13 = (n11 & 0xFF00) >> 8;
            final int n14 = n11 & 0xFF;
            array[j] = (0xFF000000 | Math.min((Math.abs(n8 - n12) + Math.abs(n4 - n12)) * n, 255) << 16 | Math.min((Math.abs(n9 - n13) + Math.abs(n5 - n13)) * n, 255) << 8 | Math.min((Math.abs(n10 - n14) + Math.abs(n6 - n14)) * n, 255));
        }
    }
    
    void _mth2c() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this._fld1f.length() > 9) {
            final int n = this._fld1f.length() - 9;
            final int n2 = n + 9;
            this._fld7f = new byte[n];
            this._fld1f.getBytes(1, n + 1, this._fld7f, 0);
            this._fld8f = new byte[n2];
            this._fld1f.getBytes(0, n2, this._fld8f, 0);
            int n3 = n % 7;
            final int n4 = n % 3;
            for (int i = 0; i < n; ++i) {
                final byte b = this._fld7f[i];
                final byte b2 = (byte)(b + n3);
                if (b >= 48 && b <= 57) {
                    this._fld7f[i] = ((b2 <= 57) ? b2 : ((byte)(b2 - 10)));
                }
                else if (b >= 65 && b <= 90) {
                    this._fld7f[i] = ((b2 <= 90) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b >= 97 && b <= 122) {
                    this._fld7f[i] = ((b2 <= 122) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b == 42) {
                    this._fld7f[i] = 45;
                }
                else if (b == 45) {
                    this._fld7f[i] = 46;
                }
                n3 = (n3 + n4) % 7;
            }
            int n5 = n % 7;
            final int n6 = n % 3;
            for (int j = 0; j < n; ++j) {
                final byte b3 = this._fld7f[j];
                final byte b4 = (byte)(b3 - n5);
                if (b3 >= 48 && b3 <= 57) {
                    this._fld8f[j + 1] = ((b4 >= 48) ? b4 : ((byte)(b4 + 10)));
                }
                else if (b3 >= 65 && b3 <= 90) {
                    this._fld8f[j + 1] = ((b4 >= 65) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 >= 97 && b3 <= 122) {
                    this._fld8f[j + 1] = ((b4 >= 97) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 == 45) {
                    this._fld8f[j + 1] = 42;
                }
                else if (b3 == 46) {
                    this._fld8f[j + 1] = 45;
                }
                n5 = (n5 + n6) % 7;
            }
            final byte[] array = new byte[n];
            if (n > 4) {
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld7f[k + 4];
                }
            }
            if (this._fld8f[0] == this._fld8f[n >> 1] && this._fld8f[1 + n] == this._fld8f[1] && this._fld8f[1 + n + 1] == this._fld8f[n >> 1] && this._fld8f[1 + n + 2] == (byte)(97 + n5) && this._fld8f[1 + n + 3] == 45 && this._fld8f[1 + n + 4] == (byte)(122 - n6) && this._fld8f[1 + n + 5] == (byte)(110 + n5) && this._fld8f[1 + n + 6] == this._fld8f[1] && this._fld8f[1 + n + 7] == this._fld8f[n] && (host.equalsIgnoreCase(new String(this._fld7f, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this._fld6f = true;
            }
        }
        try {
            this._fld5f = new URL("http://" + this._fld7b);
        }
        catch (MalformedURLException ex) {
            this._fld5f = null;
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld6f) {
            this.getAppletContext().showDocument(this._fld5f, "_blank");
        }
        else if (this._fld2f != null) {
            if (this._fld3f != null) {
                this.getAppletContext().showDocument(this._fld2f, this._fld3f);
            }
            else {
                this.getAppletContext().showDocument(this._fld2f);
            }
        }
        return true;
    }
    
    void _mth3c() {
        int fld0d = 0;
        do {
            ++fld0d;
        } while (this.getParameter("overtext" + fld0d) != null);
        if (--fld0d > 0) {
            this._fld0c = true;
            this._fld0d = fld0d;
            this._fld9f = new String[this._fld0d];
            this._fld0g = new Color[this._fld0d];
            this._fld1g = new Color[this._fld0d];
            this._fld2g = new Font[this._fld0d];
            this._fld3g = new FontMetrics[this._fld0d];
            this._fld4g = new String[this._fld0d];
            this._fld5g = new int[this._fld0d];
            this._fld6g = new int[this._fld0d];
            for (int i = 0; i < this._fld0d; ++i) {
                this._fld9f[i] = this.getParameter("overtext" + String.valueOf(i + 1));
                this._fld0g[i] = this._mth1b("overtextcol" + String.valueOf(i + 1), new Color(16777215));
                this._fld1g[i] = this._mth1b("overtextcols" + String.valueOf(i + 1), new Color(0));
                final String parameter = this.getParameter("overtexty" + String.valueOf(i + 1));
                if (parameter == null) {
                    this._fld6g[i] = 10;
                }
                else {
                    this._fld6g[i] = Integer.parseInt(parameter);
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
                this._fld2g[i] = new Font(parameter2, n, int1);
                this._fld3g[i] = this._fld3c.getFontMetrics(this._fld2g[i]);
                this._fld4g[i] = this.getParameter("overTextType" + String.valueOf(i + 1));
                if (this._fld4g[i] == null) {
                    this._fld4g[i] = "scrollleft";
                }
                final String parameter5 = this.getParameter("overtextspeed" + String.valueOf(i + 1));
                if (parameter5 == null) {
                    this._fld5g[i] = 2;
                }
                else {
                    this._fld5g[i] = Integer.valueOf(parameter5);
                    if (this._fld5g[i] < 1 || this._fld5g[i] > 4) {
                        this._fld5g[i] = 2;
                    }
                }
            }
            this._mth4c();
        }
    }
    
    void _mth4c() {
        this._fld3d = this._fld3g[this._fld9c].stringWidth(this._fld9f[this._fld9c]);
        this._fld4d = this._fld3g[this._fld9c].getHeight();
        if (this._fld4g[this._fld9c].equalsIgnoreCase("scrolldown")) {
            this._fld1d = this._fld5 - this._fld3d >> 1;
            this._fld2d = 0;
            return;
        }
        if (this._fld4g[this._fld9c].equalsIgnoreCase("scrollup")) {
            this._fld1d = this._fld5 - this._fld3d >> 1;
            this._fld2d = this._fld6 + this._fld4d;
            return;
        }
        if (this._fld4g[this._fld9c].equalsIgnoreCase("scrollright")) {
            this._fld1d = -this._fld3d;
            this._fld2d = this._fld6g[this._fld9c] + (this._fld4d >> 1) + (this._fld4d >> 3);
            return;
        }
        this._fld1d = this._fld5;
        this._fld2d = this._fld6g[this._fld9c] + (this._fld4d >> 1) + (this._fld4d >> 3);
    }
    
    void _mth5c(final Graphics graphics) {
        graphics.setFont(this._fld2g[this._fld9c]);
        graphics.setColor(this._fld1g[this._fld9c]);
        graphics.drawString(this._fld9f[this._fld9c], this._fld1d + 1, this._fld2d + 1);
        graphics.setColor(this._fld0g[this._fld9c]);
        graphics.drawString(this._fld9f[this._fld9c], this._fld1d, this._fld2d);
        if (this._fld4g[this._fld9c].equalsIgnoreCase("scrolldown")) {
            this._fld2d += this._fld5g[this._fld9c];
        }
        else if (this._fld4g[this._fld9c].equalsIgnoreCase("scrollup")) {
            this._fld2d -= this._fld5g[this._fld9c];
        }
        else if (this._fld4g[this._fld9c].equalsIgnoreCase("scrollright")) {
            this._fld1d += this._fld5g[this._fld9c];
        }
        else {
            this._fld1d -= this._fld5g[this._fld9c];
        }
        if (this._fld2d > this._fld6 + this._fld4d || this._fld2d < -this._fld4d || this._fld1d > this._fld5 || this._fld1d < -this._fld3d) {
            ++this._fld9c;
            if (this._fld9c >= this._fld0d) {
                this._fld9c = 0;
            }
            this._mth4c();
        }
    }
    
    void _mth6c() {
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
    
    public DS_FadeToFilter() {
        this._fld4 = new Font("Helvetica", 1, 12);
        this._fld3b = "Applet by Dario Sciacca";
        this._fld4b = "dario@dseffects.com";
        this._fld7b = "www.dseffects.com";
        this._fld8b = "Don't remove Dario Sciacca's credits line";
        this._fld9b = this._fld3b + " (" + this._fld7b + ")";
        this._fld0c = false;
        this._fld1c = false;
        this._fld0e = "FadeToFilter started";
        this._fld2e = false;
        this._fld1f = "";
        this._fld3f = "_blank";
        this._fld4f = "Applet by Dario Sciacca";
        this._fld6f = false;
    }
}
