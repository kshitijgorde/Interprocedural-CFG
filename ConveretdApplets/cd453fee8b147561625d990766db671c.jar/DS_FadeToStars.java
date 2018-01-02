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

public class DS_FadeToStars extends Applet implements Runnable
{
    Thread _fld0;
    int _fld3;
    Font _fld4;
    int _fld5;
    int _fld6;
    int[] _fld7;
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
    int _fld2e;
    boolean _fld3e;
    int _fld4e;
    int _fld5e;
    int _fld6e;
    int _fld7e;
    int _fld8e;
    int[] _fld9e;
    int _fld0f;
    int _fld1f;
    int _fld2f;
    int[] _fld3f;
    int _fld4f;
    int _fld5f;
    int _fld6f;
    int _fld7f;
    int _fld8f;
    int _fld9f;
    int[] _fld0g;
    int[] _fld1g;
    int[] _fld2g;
    String _fld3g;
    URL[] _fld4g;
    String[] _fld5g;
    String[] _fld6g;
    URL _fld7g;
    boolean _fld8g;
    byte[] _fld9g;
    byte[] _fld0h;
    String[] _fld1h;
    Color[] _fld2h;
    Color[] _fld3h;
    Font[] _fld4h;
    FontMetrics[] _fld5h;
    String[] _fld6h;
    int[] _fld7h;
    int[] _fld8h;
    
    public String getAppletInfo() {
        return "DS FadeToStars v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
    }
    
    public void init() {
        this._mth4();
        this.showStatus("Please wait ...");
        this._fld7b = this.getFontMetrics(this._fld4).stringWidth(this._fld8b);
        this._fld5 = this.size().width;
        this._fld6 = this.size().height;
        this._mth5();
        this._fld9 = new int[this._fld5 * this._fld6];
        this._fld0b = new MemoryImageSource(this._fld5, this._fld6, this._fld9, 0, this._fld5);
        this._fld3c = this.createImage(this._fld5, this._fld6);
        this._fld4c = this._fld3c.getGraphics();
        this._mth0c();
        this._mth3c();
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
        if (this._mth8()) {
            this._mth2b();
            this._mth9b();
            this._mth6();
            this._mth3b();
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
        final int n = this._fld5 >> 1;
        final int n2 = this._fld6 >> 1;
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
                this._mth2c(this._fld4c);
            }
            else if (this._fld6b == 2) {
                this._fld4c.drawImage(this._fld1b, 0, 0, this);
                this._fld4c.drawImage(this._fld5c, this._fld6c, this._fld7c, this);
            }
            else {
                this._fld4c.drawImage(this._fld1b, 0, 0, this);
                this._mth2c(this._fld4c);
                this._fld4c.drawImage(this._fld5c, this._fld6c, this._fld7c, this);
            }
        }
        if (this._fld3e && !this._fld8g) {
            this._fld4c.setColor(Color.white);
            this._fld4c.drawLine(n - 64, n2 - 8, n + 64, n2 - 8);
            this._fld4c.drawLine(n - 64, n2 + 8, n + 64, n2 + 8);
            this._fld4c.drawLine(n - 64, n2 - 8, n - 64, n2 + 8);
            this._fld4c.drawLine(n + 64, n2 - 8, n + 64, n2 + 8);
            this._fld4c.setColor(Color.blue);
            this._fld4c.fillRect(n - 63, n2 - 7, 127, 15);
            this._fld4c.setFont(this._fld4);
            this._fld4c.setColor(Color.yellow);
            this._fld4c.drawString(this._fld8b, n - (this._fld7b >> 1), n2 + 5);
        }
        graphics.drawImage(this._fld3c, 0, 0, this);
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this._fld6g[this._fld5e]);
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
        this.showStatus(this._fld6g[this._fld5e]);
        return true;
    }
    
    void _mth4() {
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
        this._fld8g = false;
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
        this._fld4e = 1;
        while (this.getParameter("image" + String.valueOf(this._fld4e)) != null) {
            ++this._fld4e;
        }
        --this._fld4e;
        if (this._fld4e >= 1) {
            final String[] array = new String[this._fld4e];
            this._fld7 = new int[this._fld5 * this._fld6 * this._fld4e];
            final Image[] array2 = new Image[this._fld4e];
            array2[0] = null;
            for (int i = 0; i < this._fld4e; ++i) {
                array[i] = this.getParameter("image" + String.valueOf(i + 1));
            }
            int n = 0;
            final int n2 = this._fld5 * this._fld6;
            for (int j = 0; j < this._fld4e; ++j) {
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
                this._fld4g = new URL[this._fld4e];
                this._fld5g = new String[this._fld4e];
                this._fld6g = new String[this._fld4e];
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
        this._fld7e = Integer.valueOf(parameter);
        this._fld7e = ((this._fld7e >= 1) ? ((this._fld7e <= 8) ? this._fld7e : 8) : 1);
        String parameter2 = this.getParameter("numstars");
        if (parameter2 == null) {
            parameter2 = "1000";
        }
        this._fld4f = Integer.valueOf(parameter2);
        this._fld4f = ((this._fld4f >= 100) ? ((this._fld4f <= 2000) ? this._fld4f : 2000) : 100);
        final String parameter3 = this.getParameter("direction");
        if (parameter3 == null) {
            this._fld8f = 0;
            this._fld9f = 2;
        }
        else if (parameter3.equalsIgnoreCase("RIGHT")) {
            this._fld8f = 1;
            this._fld9f = 2;
        }
        else if (parameter3.equalsIgnoreCase("UP")) {
            this._fld8f = 2;
            this._fld9f = 1;
        }
        else if (parameter3.equalsIgnoreCase("DOWN")) {
            this._fld8f = 2;
            this._fld9f = 0;
        }
        else {
            this._fld8f = 0;
            this._fld9f = 2;
        }
        String parameter4 = this.getParameter("pause");
        if (parameter4 == null) {
            parameter4 = "1";
        }
        this._fld2f = Integer.valueOf(parameter4);
        this._fld2f = ((this._fld2f >= 0) ? ((this._fld2f <= 8) ? this._fld2f : 8) : 0);
        this._fld2f *= 50;
        final String parameter5 = this.getParameter("interactive");
        if (parameter5 == null) {
            this._fld2e = 1;
        }
        else if (parameter5.equalsIgnoreCase("IN")) {
            this._fld2e = 0;
        }
        else if (parameter5.equalsIgnoreCase("OUT")) {
            this._fld2e = 1;
        }
        else {
            this._fld2e = 2;
        }
        for (int i = 1; i <= this._fld4e; ++i) {
            this._fld6g[i - 1] = this._fld4b;
        }
        final String parameter6 = this.getParameter("regkey");
        if (parameter6 != null) {
            this._fld3g = parameter6;
            for (int j = 1; j <= this._fld4e; ++j) {
                final String parameter7 = this.getParameter("reglink" + j);
                if (parameter7 != null) {
                    try {
                        this._fld4g[j - 1] = new URL("http://" + parameter7);
                    }
                    catch (MalformedURLException ex) {
                        this._fld4g[j - 1] = null;
                    }
                    final String parameter8 = this.getParameter("regtarget" + j);
                    if (parameter8 != null) {
                        this._fld5g[j - 1] = parameter8;
                    }
                }
                final String parameter9 = this.getParameter("regstatusmsg" + j);
                if (parameter9 != null) {
                    this._fld6g[j - 1] = parameter9;
                }
            }
        }
    }
    
    void _mth3b() {
        this._fld8e = 0;
        this._fld5e = 0;
        this._fld3f = new int[this._fld5 * this._fld6];
        this._mth7b();
        this._fld2f *= this._fld7e;
        this._fld0f = this._fld2f + this._fld2f + (this._fld2f << 1) + 510;
        this._fld1f = this._fld0f >> 1;
        this._fld9e = new int[this._fld0f];
        for (int i = 0; i < this._fld2f; ++i) {
            this._fld9e[i] = 0;
        }
        for (int j = this._fld2f; j < 255 + this._fld2f; ++j) {
            this._fld9e[j] = j - this._fld2f;
        }
        for (int k = 0; k < this._fld2f + this._fld2f; ++k) {
            this._fld9e[this._fld2f + 255 + k] = 255;
        }
        for (int l = 0; l < 255; ++l) {
            this._fld9e[this._fld2f + this._fld2f + this._fld2f + 255 + l] = 255 - l;
        }
        for (int n = 0; n < this._fld2f; ++n) {
            this._fld9e[this._fld2f + this._fld2f + this._fld2f + 255 + 255 + n] = 0;
        }
        this._fld8 = new int[this._fld5 * this._fld6];
        for (int n2 = 0; n2 < this._fld5 * this._fld6; ++n2) {
            this._fld8[n2] = this._fld7[n2];
        }
        this._fld6e = 0;
    }
    
    void _mth4b() {
        if (this._fld8e > 0) {
            if (this._fld8e < this._fld1f) {
                this._fld8e += this._fld7e;
                return;
            }
            this._fld8e -= this._fld7e;
        }
    }
    
    void _mth5b() {
        if (this._fld2e == 0) {
            if (this._fld3e) {
                this._fld8e += this._fld7e;
            }
            else {
                this._mth4b();
            }
        }
        else if (this._fld2e == 1) {
            if (this._fld3e) {
                this._mth4b();
            }
            else {
                this._fld8e += this._fld7e;
            }
        }
        else {
            this._fld8e += this._fld7e;
        }
        final int n = this._fld5 * this._fld6;
        final int n2 = this._fld5 * this._fld6 * this._fld4e;
        if (this._fld8e >= this._fld0f) {
            this._fld8e = 0;
            this._fld5e = (this._fld5e + 1) % this._fld4e;
            this._fld6e = (this._fld6e + n) % n2;
            for (int i = 0; i < this._fld5 * this._fld6; ++i) {
                this._fld8[i] = this._fld7[i + this._fld6e];
            }
        }
        this._mth8b();
        this._mth6b(this._fld9e[this._fld8e], this._fld9, this._fld3f, this._fld8);
        this._fld1b = this.createImage(this._fld0b);
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
    
    void _mth7b() {
        this._fld0g = new int[this._fld4f];
        this._fld1g = new int[this._fld4f];
        this._fld2g = new int[this._fld4f];
        for (int i = 0; i < this._fld4f; ++i) {
            this._fld0g[i] = (int)(Math.random() * this._fld5);
            this._fld1g[i] = (int)(Math.random() * this._fld6);
            this._fld2g[i] = (0xFF000000 | (int)(Math.random() * 255.0) * 65793);
        }
        this._fld5f = 1;
    }
    
    void _mth8b() {
        for (int n = this._fld5 * this._fld6, i = 0; i < n; ++i) {
            this._fld3f[i] = -16777216;
        }
        if (this._fld8f == 0) {
            this._fld6f = -this._fld5f;
        }
        else if (this._fld8f == 1) {
            this._fld6f = this._fld5f;
        }
        else {
            this._fld6f = 0;
        }
        if (this._fld9f == 0) {
            this._fld7f = -this._fld5f;
        }
        else if (this._fld9f == 1) {
            this._fld7f = this._fld5f;
        }
        else {
            this._fld7f = 0;
        }
        final int fld6f = this._fld6f;
        final int fld7f = this._fld7f;
        final int n2 = this._fld6f + this._fld6f;
        final int n3 = this._fld7f + this._fld7f;
        final int n4 = this._fld6f + this._fld6f + this._fld6f;
        final int n5 = this._fld7f + this._fld7f + this._fld7f;
        final int n6 = this._fld6f + this._fld6f + this._fld6f + this._fld6f;
        final int n7 = this._fld7f + this._fld7f + this._fld7f + this._fld7f;
        for (int j = 0; j < this._fld4f >> 2; ++j) {
            this._fld0g[j] = (this._fld0g[j] + fld6f + this._fld5) % this._fld5;
            this._fld1g[j] = (this._fld1g[j] + fld7f + this._fld6) % this._fld6;
            this._fld3f[this._fld1g[j] * this._fld5 + this._fld0g[j]] = this._fld2g[j];
        }
        for (int k = this._fld4f >> 2; k < this._fld4f >> 1; ++k) {
            this._fld0g[k] = (this._fld0g[k] + n2 + this._fld5) % this._fld5;
            this._fld1g[k] = (this._fld1g[k] + n3 + this._fld6) % this._fld6;
            this._fld3f[this._fld1g[k] * this._fld5 + this._fld0g[k]] = this._fld2g[k];
        }
        for (int l = this._fld4f >> 1; l < this._fld4f - (this._fld4f >> 2); ++l) {
            this._fld0g[l] = (this._fld0g[l] + n4 + this._fld5) % this._fld5;
            this._fld1g[l] = (this._fld1g[l] + n5 + this._fld6) % this._fld6;
            this._fld3f[this._fld1g[l] * this._fld5 + this._fld0g[l]] = this._fld2g[l];
        }
        for (int n8 = this._fld4f - (this._fld4f >> 2); n8 < this._fld4f; ++n8) {
            this._fld0g[n8] = (this._fld0g[n8] + n6 + this._fld5) % this._fld5;
            this._fld1g[n8] = (this._fld1g[n8] + n7 + this._fld6) % this._fld6;
            this._fld3f[this._fld1g[n8] * this._fld5 + this._fld0g[n8]] = this._fld2g[n8];
        }
    }
    
    void _mth9b() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this._fld3g.length() > 9) {
            final int n = this._fld3g.length() - 9;
            final int n2 = n + 9;
            this._fld9g = new byte[n];
            this._fld3g.getBytes(1, n + 1, this._fld9g, 0);
            this._fld0h = new byte[n2];
            this._fld3g.getBytes(0, n2, this._fld0h, 0);
            int n3 = n % 7;
            final int n4 = n % 3;
            for (int i = 0; i < n; ++i) {
                final byte b = this._fld9g[i];
                final byte b2 = (byte)(b + n3);
                if (b >= 48 && b <= 57) {
                    this._fld9g[i] = ((b2 <= 57) ? b2 : ((byte)(b2 - 10)));
                }
                else if (b >= 65 && b <= 90) {
                    this._fld9g[i] = ((b2 <= 90) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b >= 97 && b <= 122) {
                    this._fld9g[i] = ((b2 <= 122) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b == 42) {
                    this._fld9g[i] = 45;
                }
                else if (b == 45) {
                    this._fld9g[i] = 46;
                }
                n3 = (n3 + n4) % 7;
            }
            int n5 = n % 7;
            final int n6 = n % 3;
            for (int j = 0; j < n; ++j) {
                final byte b3 = this._fld9g[j];
                final byte b4 = (byte)(b3 - n5);
                if (b3 >= 48 && b3 <= 57) {
                    this._fld0h[j + 1] = ((b4 >= 48) ? b4 : ((byte)(b4 + 10)));
                }
                else if (b3 >= 65 && b3 <= 90) {
                    this._fld0h[j + 1] = ((b4 >= 65) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 >= 97 && b3 <= 122) {
                    this._fld0h[j + 1] = ((b4 >= 97) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 == 45) {
                    this._fld0h[j + 1] = 42;
                }
                else if (b3 == 46) {
                    this._fld0h[j + 1] = 45;
                }
                n5 = (n5 + n6) % 7;
            }
            byte[] array;
            if (n > 4) {
                array = new byte[n - 4];
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld9g[k + 4];
                }
            }
            else {
                array = new byte[] { 0 };
            }
            if (this._fld0h[0] == this._fld0h[n >> 1] && this._fld0h[1 + n] == this._fld0h[1] && this._fld0h[1 + n + 1] == this._fld0h[n >> 1] && this._fld0h[1 + n + 2] == (byte)(97 + n5) && this._fld0h[1 + n + 3] == 45 && this._fld0h[1 + n + 4] == (byte)(122 - n6) && this._fld0h[1 + n + 5] == (byte)(110 + n5) && this._fld0h[1 + n + 6] == this._fld0h[1] && this._fld0h[1 + n + 7] == this._fld0h[n] && (host.equalsIgnoreCase(new String(this._fld9g, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this._fld8g = true;
            }
        }
        try {
            this._fld7g = new URL("http://" + this._fld8b);
        }
        catch (MalformedURLException ex) {
            this._fld7g = null;
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld8g) {
            this.getAppletContext().showDocument(this._fld7g, "_blank");
        }
        else if (this._fld4g[this._fld5e] != null) {
            if (this._fld5g[this._fld5e] != null) {
                this.getAppletContext().showDocument(this._fld4g[this._fld5e], this._fld5g[this._fld5e]);
            }
            else {
                this.getAppletContext().showDocument(this._fld4g[this._fld5e]);
            }
        }
        return true;
    }
    
    void _mth0c() {
        int fld1d = 0;
        do {
            ++fld1d;
        } while (this.getParameter("overtext" + fld1d) != null);
        if (--fld1d > 0) {
            this._fld1c = true;
            this._fld1d = fld1d;
            this._fld1h = new String[this._fld1d];
            this._fld2h = new Color[this._fld1d];
            this._fld3h = new Color[this._fld1d];
            this._fld4h = new Font[this._fld1d];
            this._fld5h = new FontMetrics[this._fld1d];
            this._fld6h = new String[this._fld1d];
            this._fld7h = new int[this._fld1d];
            this._fld8h = new int[this._fld1d];
            for (int i = 0; i < this._fld1d; ++i) {
                this._fld1h[i] = this.getParameter("overtext" + String.valueOf(i + 1));
                this._fld2h[i] = this._mth1b("overtextcol" + String.valueOf(i + 1), new Color(16777215));
                this._fld3h[i] = this._mth1b("overtextcols" + String.valueOf(i + 1), new Color(0));
                final String parameter = this.getParameter("overtexty" + String.valueOf(i + 1));
                if (parameter == null) {
                    this._fld8h[i] = 10;
                }
                else {
                    this._fld8h[i] = Integer.parseInt(parameter);
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
                this._fld4h[i] = new Font(parameter2, n, int1);
                this._fld5h[i] = this._fld4c.getFontMetrics(this._fld4h[i]);
                this._fld6h[i] = this.getParameter("overTextType" + String.valueOf(i + 1));
                if (this._fld6h[i] == null) {
                    this._fld6h[i] = "scrollleft";
                }
                final String parameter5 = this.getParameter("overtextspeed" + String.valueOf(i + 1));
                if (parameter5 == null) {
                    this._fld7h[i] = 2;
                }
                else {
                    this._fld7h[i] = Integer.valueOf(parameter5);
                    if (this._fld7h[i] < 1 || this._fld7h[i] > 4) {
                        this._fld7h[i] = 2;
                    }
                }
            }
            this._mth1c();
        }
    }
    
    void _mth1c() {
        this._fld4d = this._fld5h[this._fld0d].stringWidth(this._fld1h[this._fld0d]);
        this._fld5d = this._fld5h[this._fld0d].getHeight();
        if (this._fld6h[this._fld0d].equalsIgnoreCase("scrolldown")) {
            this._fld2d = this._fld5 - this._fld4d >> 1;
            this._fld3d = 0;
            return;
        }
        if (this._fld6h[this._fld0d].equalsIgnoreCase("scrollup")) {
            this._fld2d = this._fld5 - this._fld4d >> 1;
            this._fld3d = this._fld6 + this._fld5d;
            return;
        }
        if (this._fld6h[this._fld0d].equalsIgnoreCase("scrollright")) {
            this._fld2d = -this._fld4d;
            this._fld3d = this._fld8h[this._fld0d] + (this._fld5d >> 1) + (this._fld5d >> 3);
            return;
        }
        this._fld2d = this._fld5;
        this._fld3d = this._fld8h[this._fld0d] + (this._fld5d >> 1) + (this._fld5d >> 3);
    }
    
    void _mth2c(final Graphics graphics) {
        graphics.setFont(this._fld4h[this._fld0d]);
        graphics.setColor(this._fld3h[this._fld0d]);
        graphics.drawString(this._fld1h[this._fld0d], this._fld2d + 1, this._fld3d + 1);
        graphics.setColor(this._fld2h[this._fld0d]);
        graphics.drawString(this._fld1h[this._fld0d], this._fld2d, this._fld3d);
        if (this._fld6h[this._fld0d].equalsIgnoreCase("scrolldown")) {
            this._fld3d += this._fld7h[this._fld0d];
        }
        else if (this._fld6h[this._fld0d].equalsIgnoreCase("scrollup")) {
            this._fld3d -= this._fld7h[this._fld0d];
        }
        else if (this._fld6h[this._fld0d].equalsIgnoreCase("scrollright")) {
            this._fld2d += this._fld7h[this._fld0d];
        }
        else {
            this._fld2d -= this._fld7h[this._fld0d];
        }
        if (this._fld3d > this._fld6 + this._fld5d || this._fld3d < -this._fld5d || this._fld2d > this._fld5 || this._fld2d < -this._fld4d) {
            ++this._fld0d;
            if (this._fld0d >= this._fld1d) {
                this._fld0d = 0;
            }
            this._mth1c();
        }
    }
    
    void _mth3c() {
        final String parameter = this.getParameter("OverImage");
        if (parameter != null) {
            this._fld5c = this._mth9(parameter, 0);
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
    
    public DS_FadeToStars() {
        this._fld4 = new Font("Helvetica", 1, 12);
        this._fld4b = "Applet by Dario Sciacca";
        this._fld5b = "dario@dseffects.com";
        this._fld8b = "www.dseffects.com";
        this._fld9b = "Don't remove Dario Sciacca's credits line";
        this._fld0c = this._fld4b + " (" + this._fld8b + ")";
        this._fld1c = false;
        this._fld2c = false;
        this._fld1e = "FadeToStars started";
        this._fld3e = false;
        this._fld3g = "";
        this._fld8g = false;
    }
}
