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

public class DS_Explode extends Applet implements Runnable
{
    Thread _fld1;
    int _fld3;
    Font _fld4;
    int _fld5;
    int _fld6;
    int[] _fld7;
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
    String _fld2e;
    int _fld3e;
    boolean _fld4e;
    int _fld5e;
    int _fld6e;
    int _fld7e;
    int _fld8e;
    float[] px;
    float[] py;
    float[] _fld9e;
    float[] _fld0f;
    double _fld1f;
    int _fld2f;
    int _fld3f;
    boolean _fld4f;
    int _fld5f;
    int _fld6f;
    int _fld7f;
    String _fld8f;
    URL[] _fld9f;
    String[] _fld0g;
    String[] _fld1g;
    URL _fld2g;
    boolean _fld3g;
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
        return "DS Explode v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
    }
    
    public void init() {
        this._mth4();
        this.showStatus("Please wait ...");
        this._fld8b = this.getFontMetrics(this._fld4).stringWidth(this._fld9b);
        this._fld5 = this.size().width;
        this._fld6 = this.size().height;
        this._mth5();
        this._fld0b = new int[this._fld5 * this._fld6];
        this._fld1b = new MemoryImageSource(this._fld5, this._fld6, this._fld0b, 0, this._fld5);
        this._fld4c = this.createImage(this._fld5, this._fld6);
        this._fld5c = this._fld4c.getGraphics();
        this._mth9b();
        this._mth2c();
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
        if (this._mth8()) {
            this._mth2b();
            this._mth8b();
            this._mth6();
            this._mth3b();
            if (this._fld9d == -16777216) {
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
        this.showStatus(this._fld2e);
        System.gc();
        final Graphics graphics = this.getGraphics();
        this._fld4b = System.currentTimeMillis();
        while (this._fld1 != null) {
            if (this._fld3 == 1) {
                this._mth4b();
            }
            this._mth3(graphics);
            this._mth1();
            if (this._fld3b++ > 10) {
                System.gc();
                this._fld3b = 0;
            }
        }
    }
    
    synchronized void _mth1() {
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
    
    void _mth3(final Graphics graphics) {
        final int n = this._fld5 >> 1;
        final int n2 = this._fld6 >> 1;
        if (this._fld3 == 0) {
            graphics.drawString("Error ...", 10, 10);
            return;
        }
        if (this._fld2b != null) {
            if (this._fld7b == 0) {
                this._fld5c.drawImage(this._fld2b, 0, 0, this);
            }
            else if (this._fld7b == 1) {
                this._fld5c.drawImage(this._fld2b, 0, 0, this);
                this._mth1c(this._fld5c);
            }
            else if (this._fld7b == 2) {
                this._fld5c.drawImage(this._fld2b, 0, 0, this);
                this._fld5c.drawImage(this._fld6c, this._fld7c, this._fld8c, this);
            }
            else {
                this._fld5c.drawImage(this._fld2b, 0, 0, this);
                this._mth1c(this._fld5c);
                this._fld5c.drawImage(this._fld6c, this._fld7c, this._fld8c, this);
            }
        }
        if (this._fld4e && !this._fld3g) {
            this._fld5c.setColor(Color.white);
            this._fld5c.drawLine(n - 64, n2 - 8, n + 64, n2 - 8);
            this._fld5c.drawLine(n - 64, n2 + 8, n + 64, n2 + 8);
            this._fld5c.drawLine(n - 64, n2 - 8, n - 64, n2 + 8);
            this._fld5c.drawLine(n + 64, n2 - 8, n + 64, n2 + 8);
            this._fld5c.setColor(Color.blue);
            this._fld5c.fillRect(n - 63, n2 - 7, 127, 15);
            this._fld5c.setFont(this._fld4);
            this._fld5c.setColor(Color.yellow);
            this._fld5c.drawString(this._fld9b, n - (this._fld8b >> 1), n2 + 5);
        }
        graphics.drawImage(this._fld4c, 0, 0, this);
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this._fld1g[this._fld6e]);
        return this._fld4e = true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this._fld4e = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int fld0e, final int fld1e) {
        this._fld0e = fld0e;
        this._fld1e = fld1e;
        this._fld4e = true;
        this.showStatus(this._fld1g[this._fld6e]);
        return true;
    }
    
    void _mth4() {
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
    
    void _mth5() {
        this._fld8d = 1;
        for (int i = 0; i < 11; ++i) {
            if (this._fld6b.charAt(i) == this._fld5b.charAt(i) || this._fld7d == 0) {
                while (true) {
                    this.showStatus(this._fld0c);
                }
            }
            else {}
        }
        this._fld3g = false;
    }
    
    void _mth6() {
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
    
    int[] _mth7(final int n, final int[] array, final int n2, final int n3, final int[] array2, final int n4, final int n5) {
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
    
    boolean _mth8() {
        this._fld5e = 1;
        while (this.getParameter("image" + String.valueOf(this._fld5e)) != null) {
            ++this._fld5e;
        }
        --this._fld5e;
        if (this._fld5e >= 2) {
            final String[] array = new String[this._fld5e];
            this._fld7 = new int[this._fld5 * this._fld6 * this._fld5e];
            final Image[] array2 = new Image[this._fld5e];
            array2[0] = null;
            for (int i = 0; i < this._fld5e; ++i) {
                array[i] = this.getParameter("image" + String.valueOf(i + 1));
            }
            int n = 0;
            final int n2 = this._fld5 * this._fld6;
            for (int j = 0; j < this._fld5e; ++j) {
                array2[j] = this._mth9(array[j], j + 1);
                if (array2[j] == null) {
                    this.showStatus("Error loading image ");
                    return false;
                }
                final int width = array2[j].getWidth(this);
                final int height = array2[j].getHeight(this);
                if (width != this._fld5 || height != this._fld6) {
                    final int[] array3 = new int[width * height];
                    if (!this._mth0b(0, array2[j], array3, width, height)) {
                        return false;
                    }
                    this._fld7 = this._mth7(n, this._fld7, this._fld5, this._fld6, array3, width, height);
                }
                else if (!this._mth0b(n, array2[j], this._fld7, this._fld5, this._fld6)) {
                    return false;
                }
                this._fld9f = new URL[this._fld5e];
                this._fld0g = new String[this._fld5e];
                this._fld1g = new String[this._fld5e];
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
    
    Image _mth9(final String s, final int n) {
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
    
    boolean _mth0b(final int n, final Image image, final int[] array, final int n2, final int n3) {
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
        this._fld5f = Integer.valueOf(parameter);
        this._fld5f = ((this._fld5f >= 1) ? ((this._fld5f <= 8) ? this._fld5f : 8) : 1);
        String parameter2 = this.getParameter("pause");
        if (parameter2 == null) {
            parameter2 = "0";
        }
        this._fld6f = Integer.valueOf(parameter2);
        this._fld6f = ((this._fld6f >= 0) ? ((this._fld6f <= 1000) ? this._fld6f : 1000) : 0);
        final String parameter3 = this.getParameter("interactive");
        if (parameter3 == null) {
            this._fld3e = 2;
        }
        else if (parameter3.equalsIgnoreCase("IN")) {
            this._fld3e = 0;
        }
        else if (parameter3.equalsIgnoreCase("OUT")) {
            this._fld3e = 1;
        }
        else {
            this._fld3e = 2;
        }
        for (int i = 1; i <= this._fld5e; ++i) {
            this._fld1g[i - 1] = this._fld5b;
        }
        final String parameter4 = this.getParameter("regkey");
        if (parameter4 != null) {
            this._fld8f = parameter4;
            for (int j = 1; j <= this._fld5e; ++j) {
                final String parameter5 = this.getParameter("reglink" + j);
                if (parameter5 != null) {
                    try {
                        this._fld9f[j - 1] = new URL("http://" + parameter5);
                    }
                    catch (MalformedURLException ex) {
                        this._fld9f[j - 1] = null;
                    }
                    final String parameter6 = this.getParameter("regtarget" + j);
                    if (parameter6 != null) {
                        this._fld0g[j - 1] = parameter6;
                    }
                }
                final String parameter7 = this.getParameter("regstatusmsg" + j);
                if (parameter7 != null) {
                    this._fld1g[j - 1] = parameter7;
                }
            }
        }
    }
    
    void _mth3b() {
        this._fld7f = 0;
        this._fld6e = 0;
        this._fld1f = 1.0 / (17 - this._fld5f * 2);
        this._fld3f = this._fld5 * this._fld6;
        this.px = new float[this._fld3f];
        this.py = new float[this._fld3f];
        this._fld9e = new float[this._fld3f];
        this._fld0f = new float[this._fld3f];
        this._fld8 = new int[this._fld5 * this._fld6];
        this._fld9 = new int[this._fld5 * this._fld6];
        if (this._fld3e == 2) {
            this._fld4f = true;
        }
        else {
            this._fld4f = false;
        }
        final int n = this._fld5 * this._fld6;
        this._fld2f = this._fld3f;
        this._fld7e = n * this._fld5e - n;
        this._fld8e = 0;
        for (int i = 0; i < n; ++i) {
            this._fld8[i] = this._fld7[i + this._fld7e];
            this._fld9[i] = this._fld7[i];
        }
    }
    
    void _mth4b() {
        if (this._fld3e == 0) {
            if (this._fld4e) {
                this._fld4f = true;
            }
            else {
                this._fld4f = false;
            }
        }
        else if (this._fld3e == 1) {
            if (this._fld4e) {
                this._fld4f = false;
            }
            else {
                this._fld4f = true;
            }
        }
        else {
            this._fld4f = true;
        }
        final int n = this._fld5 * this._fld6;
        final int n2 = this._fld5 * this._fld6 * this._fld5e;
        if (this._fld2f == this._fld3f && this._fld4f) {
            if (this._fld7f < this._fld6f) {
                ++this._fld7f;
            }
            else {
                this._fld7e = (this._fld7e + n) % n2;
                this._fld8e = (this._fld8e + n) % n2;
                for (int i = 0; i < this._fld5 * this._fld6; ++i) {
                    this._fld8[i] = this._fld7[i + this._fld7e];
                    this._fld9[i] = this._fld7[i + this._fld8e];
                }
                this._fld7f = 0;
                this._mth5b();
            }
        }
        for (int j = 0; j < n; ++j) {
            this._fld0b[j] = this._fld9[j];
        }
        this._mth6b(this._fld8);
        this._mth7b();
        this._fld2b = this.createImage(this._fld1b);
    }
    
    void _mth5b() {
        for (int i = 0; i < this._fld6; ++i) {
            for (int j = 0; j < this._fld5; ++j) {
                final int n = i * this._fld5 + j;
                this.px[n] = j;
                this.py[n] = i;
                this._fld9e[n] = -((this._fld5 >> 1) - j >> 1) + 2 - (float)(Math.random() * 5.0);
                this._fld0f[n] = -((this._fld6 >> 1) - i >> 1) + 4 - (float)(Math.random() * 9.0);
                final float n2 = (float)(Math.random() * 5.0);
                final float n3 = this._fld9e[n];
                final float n4 = this._fld0f[n];
                final double sqrt = Math.sqrt(n3 * n3 + n4 * n4);
                double n5;
                if (sqrt == 0.0) {
                    n5 = 0.0;
                }
                else {
                    n5 = 1.0 / sqrt;
                }
                final float[] fld9e = this._fld9e;
                final int n6 = n;
                fld9e[n6] *= (float)(n5 * n2);
                final float[] fld0f = this._fld0f;
                final int n7 = n;
                fld0f[n7] *= (float)(n5 * n2);
            }
        }
    }
    
    void _mth6b(final int[] array) {
        for (int i = 0; i < this._fld6; ++i) {
            for (int j = 0; j < this._fld5; ++j) {
                final int n = (int)this.px[i * this._fld5 + j];
                final int n2 = (int)this.py[i * this._fld5 + j];
                if (n >= 0 && n < this._fld5 && n2 >= 0 && n2 < this._fld6) {
                    this._fld0b[n2 * this._fld5 + n] = array[i * this._fld5 + j];
                }
            }
        }
    }
    
    void _mth7b() {
        this._fld2f = 0;
        for (int i = 0; i < this._fld3f; ++i) {
            final float[] px = this.px;
            final int n = i;
            px[n] += this._fld9e[i];
            final float[] py = this.py;
            final int n2 = i;
            py[n2] += this._fld0f[i];
            if (this.py[i] < 0.0f) {
                this.py[i] = 0.0f;
                final float[] fld9e = this._fld9e;
                final int n3 = i;
                fld9e[n3] /= 4.0f;
                this._fld0f[i] = -this._fld0f[i] / 2.0f;
            }
            else {
                final float[] fld0f = this._fld0f;
                final int n4 = i;
                fld0f[n4] += (float)this._fld1f;
            }
            if (this.py[i] > this._fld6) {
                ++this._fld2f;
            }
        }
    }
    
    void _mth8b() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this._fld8f.length() > 9) {
            final int n = this._fld8f.length() - 9;
            final int n2 = n + 9;
            this._fld4g = new byte[n];
            this._fld8f.getBytes(1, n + 1, this._fld4g, 0);
            this._fld5g = new byte[n2];
            this._fld8f.getBytes(0, n2, this._fld5g, 0);
            int n3 = n % 7;
            final int n4 = n % 3;
            for (int i = 0; i < n; ++i) {
                final byte b = this._fld4g[i];
                final byte b2 = (byte)(b + n3);
                if (b >= 48 && b <= 57) {
                    this._fld4g[i] = ((b2 <= 57) ? b2 : ((byte)(b2 - 10)));
                }
                else if (b >= 65 && b <= 90) {
                    this._fld4g[i] = ((b2 <= 90) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b >= 97 && b <= 122) {
                    this._fld4g[i] = ((b2 <= 122) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b == 42) {
                    this._fld4g[i] = 45;
                }
                else if (b == 45) {
                    this._fld4g[i] = 46;
                }
                n3 = (n3 + n4) % 7;
            }
            int n5 = n % 7;
            final int n6 = n % 3;
            for (int j = 0; j < n; ++j) {
                final byte b3 = this._fld4g[j];
                final byte b4 = (byte)(b3 - n5);
                if (b3 >= 48 && b3 <= 57) {
                    this._fld5g[j + 1] = ((b4 >= 48) ? b4 : ((byte)(b4 + 10)));
                }
                else if (b3 >= 65 && b3 <= 90) {
                    this._fld5g[j + 1] = ((b4 >= 65) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 >= 97 && b3 <= 122) {
                    this._fld5g[j + 1] = ((b4 >= 97) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 == 45) {
                    this._fld5g[j + 1] = 42;
                }
                else if (b3 == 46) {
                    this._fld5g[j + 1] = 45;
                }
                n5 = (n5 + n6) % 7;
            }
            final byte[] array = new byte[n];
            if (n > 4) {
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld4g[k + 4];
                }
            }
            if (this._fld5g[0] == this._fld5g[n >> 1] && this._fld5g[1 + n] == this._fld5g[1] && this._fld5g[1 + n + 1] == this._fld5g[n >> 1] && this._fld5g[1 + n + 2] == (byte)(97 + n5) && this._fld5g[1 + n + 3] == 45 && this._fld5g[1 + n + 4] == (byte)(122 - n6) && this._fld5g[1 + n + 5] == (byte)(110 + n5) && this._fld5g[1 + n + 6] == this._fld5g[1] && this._fld5g[1 + n + 7] == this._fld5g[n] && (host.equalsIgnoreCase(new String(this._fld4g, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this._fld3g = true;
            }
        }
        try {
            this._fld2g = new URL("http://" + this._fld9b);
        }
        catch (MalformedURLException ex) {
            this._fld2g = null;
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld3g) {
            this.getAppletContext().showDocument(this._fld2g, "_blank");
        }
        else if (this._fld9f[this._fld6e] != null) {
            if (this._fld0g[this._fld6e] != null) {
                this.getAppletContext().showDocument(this._fld9f[this._fld6e], this._fld0g[this._fld6e]);
            }
            else {
                this.getAppletContext().showDocument(this._fld9f[this._fld6e]);
            }
        }
        return true;
    }
    
    void _mth9b() {
        int fld2d = 0;
        do {
            ++fld2d;
        } while (this.getParameter("overtext" + fld2d) != null);
        if (--fld2d > 0) {
            this._fld2c = true;
            this._fld2d = fld2d;
            this._fld6g = new String[this._fld2d];
            this._fld7g = new Color[this._fld2d];
            this._fld8g = new Color[this._fld2d];
            this._fld9g = new Font[this._fld2d];
            this._fld0h = new FontMetrics[this._fld2d];
            this._fld1h = new String[this._fld2d];
            this._fld2h = new int[this._fld2d];
            this._fld3h = new int[this._fld2d];
            for (int i = 0; i < this._fld2d; ++i) {
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
                this._fld0h[i] = this._fld5c.getFontMetrics(this._fld9g[i]);
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
        this._fld5d = this._fld0h[this._fld1d].stringWidth(this._fld6g[this._fld1d]);
        this._fld6d = this._fld0h[this._fld1d].getHeight();
        if (this._fld1h[this._fld1d].equalsIgnoreCase("scrolldown")) {
            this._fld3d = this._fld5 - this._fld5d >> 1;
            this._fld4d = 0;
            return;
        }
        if (this._fld1h[this._fld1d].equalsIgnoreCase("scrollup")) {
            this._fld3d = this._fld5 - this._fld5d >> 1;
            this._fld4d = this._fld6 + this._fld6d;
            return;
        }
        if (this._fld1h[this._fld1d].equalsIgnoreCase("scrollright")) {
            this._fld3d = -this._fld5d;
            this._fld4d = this._fld3h[this._fld1d] + (this._fld6d >> 1) + (this._fld6d >> 3);
            return;
        }
        this._fld3d = this._fld5;
        this._fld4d = this._fld3h[this._fld1d] + (this._fld6d >> 1) + (this._fld6d >> 3);
    }
    
    void _mth1c(final Graphics graphics) {
        graphics.setFont(this._fld9g[this._fld1d]);
        graphics.setColor(this._fld8g[this._fld1d]);
        graphics.drawString(this._fld6g[this._fld1d], this._fld3d + 1, this._fld4d + 1);
        graphics.setColor(this._fld7g[this._fld1d]);
        graphics.drawString(this._fld6g[this._fld1d], this._fld3d, this._fld4d);
        if (this._fld1h[this._fld1d].equalsIgnoreCase("scrolldown")) {
            this._fld4d += this._fld2h[this._fld1d];
        }
        else if (this._fld1h[this._fld1d].equalsIgnoreCase("scrollup")) {
            this._fld4d -= this._fld2h[this._fld1d];
        }
        else if (this._fld1h[this._fld1d].equalsIgnoreCase("scrollright")) {
            this._fld3d += this._fld2h[this._fld1d];
        }
        else {
            this._fld3d -= this._fld2h[this._fld1d];
        }
        if (this._fld4d > this._fld6 + this._fld6d || this._fld4d < -this._fld6d || this._fld3d > this._fld5 || this._fld3d < -this._fld5d) {
            ++this._fld1d;
            if (this._fld1d >= this._fld2d) {
                this._fld1d = 0;
            }
            this._mth0c();
        }
    }
    
    void _mth2c() {
        final String parameter = this.getParameter("OverImage");
        if (parameter != null) {
            this._fld6c = this._mth9(parameter, 0);
        }
        if (this._fld6c != null) {
            this._fld3c = true;
            this._fld9c = this._fld6c.getWidth(this);
            this._fld0d = this._fld6c.getHeight(this);
            final String parameter2 = this.getParameter("OverImageX");
            if (parameter2 == null) {
                this._fld7c = (this._fld5 >> 1) - (this._fld9c >> 1);
            }
            else {
                this._fld7c = Integer.valueOf(parameter2);
            }
            final String parameter3 = this.getParameter("OverImageY");
            if (parameter3 == null) {
                this._fld8c = (this._fld6 >> 1) - (this._fld0d >> 1);
                return;
            }
            this._fld8c = Integer.valueOf(parameter3);
        }
    }
    
    public DS_Explode() {
        this._fld4 = new Font("Helvetica", 1, 12);
        this._fld5b = "Applet by Dario Sciacca";
        this._fld6b = "dario@dseffects.com";
        this._fld9b = "www.dseffects.com";
        this._fld0c = "Don't remove Dario Sciacca's credits line";
        this._fld1c = this._fld5b + " (" + this._fld9b + ")";
        this._fld2c = false;
        this._fld3c = false;
        this._fld2e = "Explode started";
        this._fld4e = false;
        this._fld8f = "";
        this._fld3g = false;
    }
}
