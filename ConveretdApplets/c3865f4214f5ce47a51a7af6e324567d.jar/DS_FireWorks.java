import java.awt.image.ImageProducer;
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

public class DS_FireWorks extends Applet implements Runnable
{
    Thread _fld0;
    int _fld1;
    Font _fld2;
    int _fld3;
    int _fld4;
    int[] _fld5;
    MemoryImageSource _fld6;
    Image _fld7;
    int _fld8;
    long _fld9;
    String _fld0b;
    String _fld1b;
    int _fld2b;
    int _fld3b;
    String _fld4b;
    String _fld5b;
    String _fld6b;
    boolean _fld7b;
    boolean _fld8b;
    Image _fld9b;
    Graphics _fld0c;
    Image _fld1c;
    int _fld2c;
    int _fld3c;
    int _fld4c;
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
    String _fld7d;
    boolean _fld8d;
    boolean _fld9d;
    int _fld0e;
    double _fld1e;
    double _fld2e;
    int _fld3e;
    int _fld4e;
    int _fld5e;
    double _fld6e;
    double _fld7e;
    int _fld8e;
    boolean _fld9e;
    double _fld0f;
    double _fld1f;
    int _fld2f;
    int _fld3f;
    int _fld4f;
    double _fld5f;
    double _fld6f;
    int _fld7f;
    boolean _fld8f;
    double _fld9f;
    double _fld0g;
    int _fld1g;
    int _fld2g;
    int _fld3g;
    double _fld4g;
    double _fld5g;
    int _fld6g;
    boolean _fld7g;
    int _fld8g;
    int _fld9g;
    int _fld0h;
    int _fld1h;
    int _fld2h;
    float[] _fld3h;
    float[] _fld4h;
    float[] _fld5h;
    float[] _fld6h;
    int[] _fld7h;
    int[] _fld8h;
    int[] _fld9h;
    float[] _fld0i;
    float[] _fld1i;
    float[] _fld2i;
    float[] _fld3i;
    int[] _fld4i;
    int[] _fld5i;
    int[] _fld6i;
    float[] _fld7i;
    float[] _fld8i;
    float[] _fld9i;
    float[] _fld0j;
    int[] _fld1j;
    int[] _fld2j;
    int[] _fld3j;
    double _fld4j;
    String _fld5j;
    URL _fld6j;
    String _fld7j;
    String _fld8j;
    URL _fld9j;
    boolean _fld0k;
    byte[] _fld1k;
    byte[] _fld2k;
    String[] _fld3k;
    Color[] _fld4k;
    Color[] _fld5k;
    Font[] _fld6k;
    FontMetrics[] _fld7k;
    String[] _fld8k;
    int[] _fld9k;
    int[] _fld0l;
    
    public String getAppletInfo() {
        return "DS FireWorks v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
    }
    
    public void init() {
        this._mth2();
        this.showStatus("Please wait ...");
        this._fld3b = this.getFontMetrics(this._fld2).stringWidth(this._fld4b);
        this._fld3 = this.size().width;
        this._fld4 = this.size().height;
        this._mth3();
        this._fld5 = new int[this._fld3 * this._fld4];
        this._fld6 = new MemoryImageSource(this._fld3, this._fld4, this._fld5, 0, this._fld3);
        this._fld9b = this.createImage(this._fld3, this._fld4);
        this._fld0c = this._fld9b.getGraphics();
        this._mth9b();
        this._mth2c();
        if (!this._fld7b && !this._fld8b) {
            this._fld2b = 0;
        }
        else if (this._fld7b && !this._fld8b) {
            this._fld2b = 1;
        }
        else if (!this._fld7b && this._fld8b) {
            this._fld2b = 2;
        }
        else {
            this._fld2b = 3;
        }
        this._mth7();
        this._mth8b();
        this._mth4();
        this._mth8();
        if (this._fld4d == -16777216) {
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
        this.showStatus(this._fld7d);
        System.gc();
        final Graphics graphics = this.getGraphics();
        this._fld9 = System.currentTimeMillis();
        while (this._fld0 != null) {
            if (this._fld1 == 1) {
                this._mth9();
            }
            this._mth1(graphics);
            this._mth0();
            if (this._fld8++ > 10) {
                System.gc();
                this._fld8 = 0;
            }
        }
    }
    
    synchronized void _mth0() {
        Thread.yield();
        this.getToolkit().sync();
        final long n = 10L - (System.currentTimeMillis() - this._fld9);
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
        this._fld9 = System.currentTimeMillis();
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
        if (this._fld7 != null) {
            if (this._fld2b == 0) {
                this._fld0c.drawImage(this._fld7, 0, 0, this);
            }
            else if (this._fld2b == 1) {
                this._fld0c.drawImage(this._fld7, 0, 0, this);
                this._mth1c(this._fld0c);
            }
            else if (this._fld2b == 2) {
                this._fld0c.drawImage(this._fld7, 0, 0, this);
                this._fld0c.drawImage(this._fld1c, this._fld2c, this._fld3c, this);
            }
            else {
                this._fld0c.drawImage(this._fld7, 0, 0, this);
                this._mth1c(this._fld0c);
                this._fld0c.drawImage(this._fld1c, this._fld2c, this._fld3c, this);
            }
        }
        if (this._fld9d && !this._fld0k) {
            this._fld0c.setColor(Color.white);
            this._fld0c.drawLine(n - 64, n2 - 8, n + 64, n2 - 8);
            this._fld0c.drawLine(n - 64, n2 + 8, n + 64, n2 + 8);
            this._fld0c.drawLine(n - 64, n2 - 8, n - 64, n2 + 8);
            this._fld0c.drawLine(n + 64, n2 - 8, n + 64, n2 + 8);
            this._fld0c.setColor(Color.blue);
            this._fld0c.fillRect(n - 63, n2 - 7, 127, 15);
            this._fld0c.setFont(this._fld2);
            this._fld0c.setColor(Color.yellow);
            this._fld0c.drawString(this._fld4b, n - (this._fld3b >> 1), n2 + 5);
        }
        graphics.drawImage(this._fld9b, 0, 0, this);
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this._fld8j);
        return this._fld9d = true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this._fld9d = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int fld5d, final int fld6d) {
        this._fld5d = fld5d;
        this._fld6d = fld6d;
        return this._fld9d = true;
    }
    
    void _mth2() {
        final String parameter = this.getParameter("credits");
        if (parameter != null) {
            if (parameter.equals(this._fld6b)) {
                this._fld2d = 1;
                return;
            }
            while (true) {
                this.showStatus(this._fld5b);
            }
        }
        else {
            while (true) {
                this.showStatus(this._fld5b);
            }
        }
    }
    
    void _mth3() {
        this._fld3d = 1;
        for (int i = 0; i < 11; ++i) {
            if (this._fld1b.charAt(i) == this._fld0b.charAt(i) || this._fld2d == 0) {
                while (true) {
                    this.showStatus(this._fld5b);
                }
            }
            else {}
        }
        this._fld0k = false;
    }
    
    void _mth4() {
        if (this._fld2d == 0 || this._fld3d == 0) {
            while (true) {
                this.showStatus(this._fld5b);
            }
        }
        else {
            for (int i = 0; i < 17; ++i) {
                if (this._fld1b.charAt(i) == this._fld4b.charAt(i)) {
                    while (true) {
                        this.showStatus(this._fld5b);
                    }
                }
                else {}
            }
            this._fld4d = -16777216;
            if (this._fld0b.charAt(1) == 'p' && this._fld0b.charAt(7) == 'b' && this._fld0b.charAt(21) == 'c' && this._fld0b.charAt(17) == 'c' && this._fld0b.charAt(12) == 'r' && this._fld0b.charAt(11) == 'a') {
                return;
            }
            while (true) {
                this.showStatus(this._fld5b);
            }
        }
    }
    
    Image _mth5(final String s) {
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
    
    Color _mth6(final String s, final Color color) {
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
    
    void _mth7() {
        String parameter = this.getParameter("particles");
        if (parameter == null) {
            parameter = "200";
        }
        this._fld2h = Integer.valueOf(parameter);
        this._fld2h = ((this._fld2h >= 100) ? ((this._fld2h <= 500) ? this._fld2h : 500) : 100);
        final String parameter2 = this.getParameter("gravity");
        if (parameter2 == null) {
            this._fld4j = 0.0;
        }
        else if (parameter2.equalsIgnoreCase("no")) {
            this._fld4j = 0.0;
        }
        else {
            this._fld4j = 0.1;
        }
        final String parameter3 = this.getParameter("interactive");
        if (parameter3 == null) {
            this._fld8d = true;
        }
        else if (parameter3.equalsIgnoreCase("no")) {
            this._fld8d = false;
        }
        else {
            this._fld8d = true;
        }
        this._fld8j = this._fld0b;
        final String parameter4 = this.getParameter("regkey");
        if (parameter4 != null) {
            this._fld5j = parameter4;
            final String parameter5 = this.getParameter("reglink");
            if (parameter5 != null) {
                try {
                    this._fld6j = new URL("http://" + parameter5);
                }
                catch (MalformedURLException ex) {
                    this._fld6j = null;
                }
                final String parameter6 = this.getParameter("regtarget");
                if (parameter6 != null) {
                    this._fld7j = parameter6;
                }
            }
            final String parameter7 = this.getParameter("regstatusmsg");
            if (parameter7 != null) {
                this._fld8j = parameter7;
            }
        }
    }
    
    void _mth8() {
        this._fld3h = new float[this._fld2h];
        this._fld4h = new float[this._fld2h];
        this._fld5h = new float[this._fld2h];
        this._fld6h = new float[this._fld2h];
        this._fld7h = new int[this._fld2h];
        this._fld8h = new int[this._fld2h];
        this._fld9h = new int[this._fld2h];
        this._fld0i = new float[this._fld2h];
        this._fld1i = new float[this._fld2h];
        this._fld2i = new float[this._fld2h];
        this._fld3i = new float[this._fld2h];
        this._fld4i = new int[this._fld2h];
        this._fld5i = new int[this._fld2h];
        this._fld6i = new int[this._fld2h];
        this._fld7i = new float[this._fld2h];
        this._fld8i = new float[this._fld2h];
        this._fld9i = new float[this._fld2h];
        this._fld0j = new float[this._fld2h];
        this._fld1j = new int[this._fld2h];
        this._fld2j = new int[this._fld2h];
        this._fld3j = new int[this._fld2h];
    }
    
    void _mth9() {
        if (this._fld8d && this._fld9d) {
            this._fld3e = 0;
            this._fld4e = 0;
            this._fld5e = 0;
            this._fld2f = 0;
            this._fld3f = 0;
            this._fld4f = 0;
            this._fld1g = 0;
            this._fld2g = 0;
            this._fld3g = 0;
        }
        for (int i = 0; i < this._fld3 * this._fld4; ++i) {
            this._fld5[i] = -16777216;
        }
        if (!this._fld9e) {
            this._mth3b();
        }
        else {
            this._fld8g -= 6;
            if (this._fld8g >= 0) {
                this._mth1b(this._fld3h, this._fld4h, this._fld5h, this._fld6h, this._fld7h, this._fld8h, this._fld9h, this._fld8g);
            }
            else {
                this._fld8g = 255;
                this._mth2b();
            }
        }
        if (!this._fld8f) {
            this._mth5b();
        }
        else {
            this._fld9g -= 4;
            if (this._fld9g >= 0) {
                this._mth1b(this._fld0i, this._fld1i, this._fld2i, this._fld3i, this._fld4i, this._fld5i, this._fld6i, this._fld9g);
            }
            else {
                this._fld9g = 255;
                this._mth4b();
            }
        }
        if (!this._fld7g) {
            this._mth7b();
        }
        else {
            this._fld0h -= 8;
            if (this._fld0h >= 0) {
                this._mth1b(this._fld7i, this._fld8i, this._fld9i, this._fld0j, this._fld1j, this._fld2j, this._fld3j, this._fld0h);
            }
            else {
                this._fld0h = 255;
                this._mth6b();
            }
        }
        this._fld7 = this.createImage(this._fld6);
    }
    
    void _mth0b(final float[] array, final float[] array2, final float[] array3, final float[] array4, final int[] array5, final int[] array6, final int[] array7, final double n, final double n2, final int n3, final int n4, final int n5) {
        final float n6 = (float)n;
        final float n7 = (float)n2;
        final float n8 = this._fld1h / 2;
        for (int i = 0; i < this._fld2h; ++i) {
            array[i] = n6;
            array2[i] = n7;
            array3[i] = (float)(Math.random() * this._fld1h - n8);
            array4[i] = (float)(Math.random() * this._fld1h - n8);
            final float n9 = (float)(Math.random() * this._fld1h);
            final float n10 = array3[i];
            final float n11 = array4[i];
            final double sqrt = Math.sqrt(n10 * n10 + n11 * n11);
            double n12;
            if (sqrt == 0.0) {
                n12 = 0.0;
            }
            else {
                n12 = 1.0 / sqrt;
            }
            final int n13 = i;
            array3[n13] *= (float)(n12 * n9);
            final int n14 = i;
            array4[n14] *= (float)(n12 * n9);
            final int n15 = n3 + (int)(Math.random() * 64.0) - 32;
            final int n16 = n4 + (int)(Math.random() * 64.0) - 32;
            final int n17 = n5 + (int)(Math.random() * 64.0) - 32;
            if (n15 < 0) {
                array5[i] = 0;
            }
            else if (n15 > 255) {
                array5[i] = 255;
            }
            else {
                array5[i] = n15;
            }
            if (n16 < 0) {
                array6[i] = 0;
            }
            else if (n16 > 255) {
                array6[i] = 255;
            }
            else {
                array6[i] = n16;
            }
            if (n17 < 0) {
                array7[i] = 0;
            }
            else if (n17 > 255) {
                array7[i] = 255;
            }
            else {
                array7[i] = n17;
            }
        }
    }
    
    void _mth1b(final float[] array, final float[] array2, final float[] array3, final float[] array4, final int[] array5, final int[] array6, final int[] array7, final int n) {
        for (int i = 0; i < this._fld2h; ++i) {
            final int n2 = (int)array[i];
            final int n3 = (int)array2[i];
            if (n2 >= 0 && n2 < this._fld3 && n3 >= 0 && n3 < this._fld4) {
                this._fld5[n3 * this._fld3 + n2] = (0xFF000000 | array5[i] * n >> 8 << 16 | array6[i] * n >> 8 << 8 | array7[i] * n >> 8);
            }
            final int n4 = i;
            array[n4] += array3[i];
            final int n5 = i;
            array2[n5] += array4[i];
            final int n6 = i;
            array4[n6] += (float)this._fld4j;
        }
    }
    
    void _mth2b() {
        this._fld3e = (int)(Math.random() * 128.0 + 127.0);
        this._fld4e = (int)(Math.random() * 128.0 + 127.0);
        this._fld5e = (int)(Math.random() * 128.0 + 127.0);
        this._fld1e = this._fld3 >> 1;
        this._fld2e = this._fld4 - 1;
        this._fld7e = 0.0;
        this._fld6e = (Math.random() - 0.5) * this._fld0e;
        this._fld8e = (int)(Math.random() * (this._fld4 >> 2) + (this._fld4 >> 1));
        this._fld9e = false;
    }
    
    void _mth3b() {
        this._fld8e -= this._fld0e;
        this._fld7e -= 0.5;
        this._fld1e += this._fld6e;
        this._fld2e += this._fld7e;
        if (this._fld8e < 0 || this._fld1e < 0.0 || this._fld2e < 0.0 || this._fld1e >= this._fld3 || this._fld2e >= this._fld4) {
            this._fld9e = true;
            this._mth0b(this._fld3h, this._fld4h, this._fld5h, this._fld6h, this._fld7h, this._fld8h, this._fld9h, this._fld1e, this._fld2e, this._fld3e, this._fld4e, this._fld5e);
            this._fld8g = 255;
            return;
        }
        this._fld5[((int)this._fld2e - 1) * this._fld3 + (int)this._fld1e] = (0xFF000000 | this._fld3e << 16 | this._fld4e << 8 | this._fld5e);
        this._fld5[(int)this._fld2e * this._fld3 + (int)this._fld1e] = (0xFF000000 | this._fld3e << 16 | this._fld4e << 8 | this._fld5e);
    }
    
    void _mth4b() {
        this._fld2f = (int)(Math.random() * 128.0 + 127.0);
        this._fld3f = (int)(Math.random() * 128.0 + 127.0);
        this._fld4f = (int)(Math.random() * 128.0 + 127.0);
        this._fld0f = this._fld3 >> 1;
        this._fld1f = this._fld4 - 1;
        this._fld6f = 0.0;
        this._fld5f = (Math.random() - 0.5) * this._fld0e;
        this._fld7f = (int)(Math.random() * (this._fld4 >> 2) + (this._fld4 >> 1));
        this._fld8f = false;
    }
    
    void _mth5b() {
        this._fld7f -= this._fld0e;
        this._fld6f -= 0.5;
        this._fld0f += this._fld5f;
        this._fld1f += this._fld6f;
        if (this._fld7f < 0 || this._fld0f < 0.0 || this._fld1f < 0.0 || this._fld0f >= this._fld3 || this._fld1f >= this._fld4) {
            this._fld8f = true;
            this._mth0b(this._fld0i, this._fld1i, this._fld2i, this._fld3i, this._fld4i, this._fld5i, this._fld6i, this._fld0f, this._fld1f, this._fld2f, this._fld3f, this._fld4f);
            this._fld9g = 255;
            return;
        }
        this._fld5[((int)this._fld1f - 1) * this._fld3 + (int)this._fld0f] = (0xFF000000 | this._fld2f << 16 | this._fld3f << 8 | this._fld4f);
        this._fld5[(int)this._fld1f * this._fld3 + (int)this._fld0f] = (0xFF000000 | this._fld2f << 16 | this._fld3f << 8 | this._fld4f);
    }
    
    void _mth6b() {
        this._fld1g = (int)(Math.random() * 128.0 + 127.0);
        this._fld2g = (int)(Math.random() * 128.0 + 127.0);
        this._fld3g = (int)(Math.random() * 128.0 + 127.0);
        this._fld9f = this._fld3 >> 1;
        this._fld0g = this._fld4 - 1;
        this._fld5g = 0.0;
        this._fld4g = (Math.random() - 0.5) * this._fld0e;
        this._fld6g = (int)(Math.random() * (this._fld4 >> 2) + (this._fld4 >> 1));
        this._fld7g = false;
    }
    
    void _mth7b() {
        this._fld6g -= this._fld0e;
        this._fld5g -= 0.5;
        this._fld9f += this._fld4g;
        this._fld0g += this._fld5g;
        if (this._fld6g < 0 || this._fld9f < 0.0 || this._fld0g < 0.0 || this._fld9f >= this._fld3 || this._fld0g >= this._fld4) {
            this._fld7g = true;
            this._mth0b(this._fld7i, this._fld8i, this._fld9i, this._fld0j, this._fld1j, this._fld2j, this._fld3j, this._fld9f, this._fld0g, this._fld1g, this._fld2g, this._fld3g);
            this._fld0h = 255;
            return;
        }
        this._fld5[((int)this._fld0g - 1) * this._fld3 + (int)this._fld9f] = (0xFF000000 | this._fld1g << 16 | this._fld2g << 8 | this._fld3g);
        this._fld5[(int)this._fld0g * this._fld3 + (int)this._fld9f] = (0xFF000000 | this._fld1g << 16 | this._fld2g << 8 | this._fld3g);
    }
    
    void _mth8b() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this._fld5j.length() > 9) {
            final int n = this._fld5j.length() - 9;
            final int n2 = n + 9;
            this._fld1k = new byte[n];
            this._fld5j.getBytes(1, n + 1, this._fld1k, 0);
            this._fld2k = new byte[n2];
            this._fld5j.getBytes(0, n2, this._fld2k, 0);
            int n3 = n % 7;
            final int n4 = n % 3;
            for (int i = 0; i < n; ++i) {
                final byte b = this._fld1k[i];
                final byte b2 = (byte)(b + n3);
                if (b >= 48 && b <= 57) {
                    this._fld1k[i] = ((b2 <= 57) ? b2 : ((byte)(b2 - 10)));
                }
                else if (b >= 65 && b <= 90) {
                    this._fld1k[i] = ((b2 <= 90) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b >= 97 && b <= 122) {
                    this._fld1k[i] = ((b2 <= 122) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b == 42) {
                    this._fld1k[i] = 45;
                }
                else if (b == 45) {
                    this._fld1k[i] = 46;
                }
                n3 = (n3 + n4) % 7;
            }
            int n5 = n % 7;
            final int n6 = n % 3;
            for (int j = 0; j < n; ++j) {
                final byte b3 = this._fld1k[j];
                final byte b4 = (byte)(b3 - n5);
                if (b3 >= 48 && b3 <= 57) {
                    this._fld2k[j + 1] = ((b4 >= 48) ? b4 : ((byte)(b4 + 10)));
                }
                else if (b3 >= 65 && b3 <= 90) {
                    this._fld2k[j + 1] = ((b4 >= 65) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 >= 97 && b3 <= 122) {
                    this._fld2k[j + 1] = ((b4 >= 97) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 == 45) {
                    this._fld2k[j + 1] = 42;
                }
                else if (b3 == 46) {
                    this._fld2k[j + 1] = 45;
                }
                n5 = (n5 + n6) % 7;
            }
            byte[] array;
            if (n > 4) {
                array = new byte[n - 4];
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld1k[k + 4];
                }
            }
            else {
                array = new byte[] { 0 };
            }
            if (this._fld2k[0] == this._fld2k[n >> 1] && this._fld2k[1 + n] == this._fld2k[1] && this._fld2k[1 + n + 1] == this._fld2k[n >> 1] && this._fld2k[1 + n + 2] == (byte)(97 + n5) && this._fld2k[1 + n + 3] == 45 && this._fld2k[1 + n + 4] == (byte)(122 - n6) && this._fld2k[1 + n + 5] == (byte)(110 + n5) && this._fld2k[1 + n + 6] == this._fld2k[1] && this._fld2k[1 + n + 7] == this._fld2k[n] && (host.equalsIgnoreCase(new String(this._fld1k, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this._fld0k = true;
            }
        }
        try {
            this._fld9j = new URL("http://" + this._fld4b);
        }
        catch (MalformedURLException ex) {
            this._fld9j = null;
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld0k) {
            this.getAppletContext().showDocument(this._fld9j, "_blank");
        }
        else if (this._fld6j != null) {
            if (this._fld7j != null) {
                this.getAppletContext().showDocument(this._fld6j, this._fld7j);
            }
            else {
                this.getAppletContext().showDocument(this._fld6j);
            }
        }
        return true;
    }
    
    void _mth9b() {
        int fld7c = 0;
        do {
            ++fld7c;
        } while (this.getParameter("overtext" + fld7c) != null);
        if (--fld7c > 0) {
            this._fld7b = true;
            this._fld7c = fld7c;
            this._fld3k = new String[this._fld7c];
            this._fld4k = new Color[this._fld7c];
            this._fld5k = new Color[this._fld7c];
            this._fld6k = new Font[this._fld7c];
            this._fld7k = new FontMetrics[this._fld7c];
            this._fld8k = new String[this._fld7c];
            this._fld9k = new int[this._fld7c];
            this._fld0l = new int[this._fld7c];
            for (int i = 0; i < this._fld7c; ++i) {
                this._fld3k[i] = this.getParameter("overtext" + String.valueOf(i + 1));
                this._fld4k[i] = this._mth6("overtextcol" + String.valueOf(i + 1), new Color(16777215));
                this._fld5k[i] = this._mth6("overtextcols" + String.valueOf(i + 1), new Color(0));
                final String parameter = this.getParameter("overtexty" + String.valueOf(i + 1));
                if (parameter == null) {
                    this._fld0l[i] = 10;
                }
                else {
                    this._fld0l[i] = Integer.parseInt(parameter);
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
                this._fld6k[i] = new Font(parameter2, n, int1);
                this._fld7k[i] = this._fld0c.getFontMetrics(this._fld6k[i]);
                this._fld8k[i] = this.getParameter("overTextType" + String.valueOf(i + 1));
                if (this._fld8k[i] == null) {
                    this._fld8k[i] = "scrollleft";
                }
                final String parameter5 = this.getParameter("overtextspeed" + String.valueOf(i + 1));
                if (parameter5 == null) {
                    this._fld9k[i] = 2;
                }
                else {
                    this._fld9k[i] = Integer.valueOf(parameter5);
                    if (this._fld9k[i] < 1 || this._fld9k[i] > 4) {
                        this._fld9k[i] = 2;
                    }
                }
            }
            this._mth0c();
        }
    }
    
    void _mth0c() {
        this._fld0d = this._fld7k[this._fld6c].stringWidth(this._fld3k[this._fld6c]);
        this._fld1d = this._fld7k[this._fld6c].getHeight();
        if (this._fld8k[this._fld6c].equalsIgnoreCase("scrolldown")) {
            this._fld8c = this._fld3 - this._fld0d >> 1;
            this._fld9c = 0;
            return;
        }
        if (this._fld8k[this._fld6c].equalsIgnoreCase("scrollup")) {
            this._fld8c = this._fld3 - this._fld0d >> 1;
            this._fld9c = this._fld4 + this._fld1d;
            return;
        }
        if (this._fld8k[this._fld6c].equalsIgnoreCase("scrollright")) {
            this._fld8c = -this._fld0d;
            this._fld9c = this._fld0l[this._fld6c] + (this._fld1d >> 1) + (this._fld1d >> 3);
            return;
        }
        this._fld8c = this._fld3;
        this._fld9c = this._fld0l[this._fld6c] + (this._fld1d >> 1) + (this._fld1d >> 3);
    }
    
    void _mth1c(final Graphics graphics) {
        graphics.setFont(this._fld6k[this._fld6c]);
        graphics.setColor(this._fld5k[this._fld6c]);
        graphics.drawString(this._fld3k[this._fld6c], this._fld8c + 1, this._fld9c + 1);
        graphics.setColor(this._fld4k[this._fld6c]);
        graphics.drawString(this._fld3k[this._fld6c], this._fld8c, this._fld9c);
        if (this._fld8k[this._fld6c].equalsIgnoreCase("scrolldown")) {
            this._fld9c += this._fld9k[this._fld6c];
        }
        else if (this._fld8k[this._fld6c].equalsIgnoreCase("scrollup")) {
            this._fld9c -= this._fld9k[this._fld6c];
        }
        else if (this._fld8k[this._fld6c].equalsIgnoreCase("scrollright")) {
            this._fld8c += this._fld9k[this._fld6c];
        }
        else {
            this._fld8c -= this._fld9k[this._fld6c];
        }
        if (this._fld9c > this._fld4 + this._fld1d || this._fld9c < -this._fld1d || this._fld8c > this._fld3 || this._fld8c < -this._fld0d) {
            ++this._fld6c;
            if (this._fld6c >= this._fld7c) {
                this._fld6c = 0;
            }
            this._mth0c();
        }
    }
    
    void _mth2c() {
        final String parameter = this.getParameter("OverImage");
        if (parameter != null) {
            this._fld1c = this._mth5(parameter);
        }
        if (this._fld1c != null) {
            this._fld8b = true;
            this._fld4c = this._fld1c.getWidth(this);
            this._fld5c = this._fld1c.getHeight(this);
            final String parameter2 = this.getParameter("OverImageX");
            if (parameter2 == null) {
                this._fld2c = (this._fld3 >> 1) - (this._fld4c >> 1);
            }
            else {
                this._fld2c = Integer.valueOf(parameter2);
            }
            final String parameter3 = this.getParameter("OverImageY");
            if (parameter3 == null) {
                this._fld3c = (this._fld4 >> 1) - (this._fld5c >> 1);
                return;
            }
            this._fld3c = Integer.valueOf(parameter3);
        }
    }
    
    public DS_FireWorks() {
        this._fld2 = new Font("Helvetica", 1, 12);
        this._fld0b = "Applet by Dario Sciacca";
        this._fld1b = "dario@dseffects.com";
        this._fld4b = "www.dseffects.com";
        this._fld5b = "Don't remove Dario Sciacca's credits line";
        this._fld6b = this._fld0b + " (" + this._fld4b + ")";
        this._fld7b = false;
        this._fld8b = false;
        this._fld7d = "FireWorks started";
        this._fld9d = false;
        this._fld0e = 6;
        this._fld9e = true;
        this._fld8f = true;
        this._fld7g = true;
        this._fld1h = 2;
        this._fld5j = "";
        this._fld7j = "_blank";
        this._fld8j = "Applet by Dario Sciacca";
        this._fld0k = false;
    }
}
