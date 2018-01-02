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

public class DS_XBlurText extends Applet implements Runnable
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
    int _fld1e;
    boolean _fld2e;
    boolean _fld3e;
    Font[] _fld4e;
    String[] _fld5e;
    int _fld6e;
    int _fld7e;
    int[] _fld8e;
    int[] _fld9e;
    int _fld0f;
    int _fld1f;
    int[] _fld2f;
    int _fld3f;
    int _fld4f;
    int _fld5f;
    int _fld6f;
    int[] _fld7f;
    int _fld8f;
    int _fld9f;
    String _fld0g;
    URL[] _fld1g;
    String[] _fld2g;
    String[] _fld3g;
    URL _fld4g;
    boolean _fld5g;
    byte[] _fld6g;
    byte[] _fld7g;
    String[] _fld8g;
    Color[] _fld9g;
    Color[] _fld0h;
    Font[] _fld1h;
    FontMetrics[] _fld2h;
    String[] _fld3h;
    int[] _fld4h;
    int[] _fld5h;
    
    public String getAppletInfo() {
        return "DS XBlurText v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
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
        this._mth8b();
        this._mth1c();
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
        this._mth1b();
        this._mth7b();
        this._mth6();
        this._mth2b();
        if (this._fld7d == -16777216) {
            this._fld3 = 1;
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
                this._fld3c.drawImage(this._fld0b, 0, 0, this);
            }
            else if (this._fld5b == 1) {
                this._fld3c.drawImage(this._fld0b, 0, 0, this);
                this._mth0c(this._fld3c);
            }
            else if (this._fld5b == 2) {
                this._fld3c.drawImage(this._fld0b, 0, 0, this);
                this._fld3c.drawImage(this._fld4c, this._fld5c, this._fld6c, this);
            }
            else {
                this._fld3c.drawImage(this._fld0b, 0, 0, this);
                this._mth0c(this._fld3c);
                this._fld3c.drawImage(this._fld4c, this._fld5c, this._fld6c, this);
            }
        }
        if (this._fld2e && !this._fld5g) {
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
        this.showStatus(this._fld3g[this._fld1f]);
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
        this.showStatus(this._fld3g[this._fld1f]);
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
        this._fld5g = false;
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
    
    Image _mth8(final String s) {
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
    
    boolean _mth9(final Image image, final int[] array, final int n, final int n2) {
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
    
    Color _mth0b(final String s, final Color color) {
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
    
    void _mth1b() {
        this._fld0f = 1;
        while (this.getParameter("text" + String.valueOf(this._fld0f)) != null) {
            ++this._fld0f;
        }
        --this._fld0f;
        if (this._fld0f == 0) {
            this._fld0f = 1;
        }
        this._fld4e = new Font[this._fld0f];
        this._fld5e = new String[this._fld0f];
        this._fld8e = new int[this._fld0f];
        for (int i = 1; i < this._fld0f + 1; ++i) {
            final String parameter = this.getParameter("text" + i);
            if (parameter == null) {
                this._fld5e[i - 1] = "DS effects";
            }
            else {
                this._fld5e[i - 1] = parameter;
            }
            String parameter2 = this.getParameter("textfont" + i);
            if (parameter2 == null) {
                parameter2 = "Helvetica";
            }
            final String parameter3 = this.getParameter("textstyle" + i);
            int n;
            if (parameter3 == null) {
                n = 1;
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
                n = 1;
            }
            final String parameter4 = this.getParameter("textsize" + i);
            int int1;
            if (parameter4 == null) {
                int1 = 42;
            }
            else {
                int1 = Integer.parseInt(parameter4);
            }
            this._fld4e[i - 1] = new Font(parameter2, n, int1);
            this._fld8e[i - 1] = this._mth0b("textcolor" + i, new Color(16711680)).getRGB();
        }
        String parameter5 = this.getParameter("speed");
        if (parameter5 == null) {
            parameter5 = "2";
        }
        this._fld4f = Integer.valueOf(parameter5);
        this._fld4f = ((this._fld4f >= 1) ? ((this._fld4f <= 8) ? this._fld4f : 8) : 1);
        String parameter6 = this.getParameter("pause");
        if (parameter6 == null) {
            parameter6 = "1";
        }
        this._fld5f = Integer.valueOf(parameter6);
        this._fld5f = ((this._fld5f >= 0) ? ((this._fld5f <= 8) ? this._fld5f : 8) : 0);
        this._fld5f *= 50;
        final String parameter7 = this.getParameter("interactive");
        if (parameter7 == null) {
            this._fld1e = 0;
        }
        else if (parameter7.equalsIgnoreCase("YES")) {
            this._fld1e = 0;
        }
        else {
            this._fld1e = 1;
        }
        this._fld1g = new URL[this._fld0f];
        this._fld2g = new String[this._fld0f];
        this._fld3g = new String[this._fld0f];
        for (int j = 1; j <= this._fld0f; ++j) {
            this._fld3g[j - 1] = this._fld3b;
        }
        final String parameter8 = this.getParameter("regkey");
        if (parameter8 != null) {
            this._fld0g = parameter8;
            for (int k = 1; k <= this._fld0f; ++k) {
                final String parameter9 = this.getParameter("reglink" + k);
                if (parameter9 != null) {
                    try {
                        this._fld1g[k - 1] = new URL("http://" + parameter9);
                    }
                    catch (MalformedURLException ex) {
                        this._fld1g[k - 1] = null;
                    }
                    final String parameter10 = this.getParameter("regtarget" + k);
                    if (parameter10 != null) {
                        this._fld2g[k - 1] = parameter10;
                    }
                }
                final String parameter11 = this.getParameter("regstatusmsg" + k);
                if (parameter11 != null) {
                    this._fld3g[k - 1] = parameter11;
                }
            }
        }
    }
    
    void _mth2b() {
        final int n = this._fld5 * this._fld6;
        this._fld9e = new int[n];
        this._fld2f = new int[n];
        this._mth5b();
        this._fld3f = 0;
        this._fld6f = 0;
        final int n2 = this._fld5 - (this._fld5 >> 2);
        this._fld5f *= this._fld4f;
        this._fld8f = this._fld5f + n2 << 1;
        this._fld9f = this._fld8f >> 1;
        this._fld7f = new int[this._fld8f];
        for (int i = 0; i < this._fld5f; ++i) {
            this._fld7f[i] = 0;
        }
        for (int j = this._fld5f; j < n2 + this._fld5f; ++j) {
            this._fld7f[j] = j - this._fld5f;
        }
        for (int k = 0; k < n2; ++k) {
            this._fld7f[this._fld5f + n2 + k] = n2 - k;
        }
        for (int l = 0; l < this._fld5f; ++l) {
            this._fld7f[this._fld5f + n2 + n2 + l] = 0;
        }
    }
    
    void _mth3b() {
        if (this._fld3f < this._fld9f) {
            this._fld3f -= this._fld4f;
            this._fld6f -= this._fld4f;
            if (this._fld3f < 0) {
                this._fld3f = 0;
            }
            if (this._fld6f > this._fld9f) {
                this._fld6f = this._fld9f;
            }
        }
        else {
            this._fld3f += this._fld4f;
            this._fld6f += this._fld4f;
            if (this._fld3f > this._fld8f) {
                this._fld3f = this._fld8f;
            }
            if (this._fld6f > this._fld9f) {
                this._fld6f = this._fld9f;
            }
        }
    }
    
    void _mth4b() {
        if (this._fld1e == 0) {
            if (this._fld2e) {
                this._mth3b();
            }
            else {
                this._fld3f += this._fld4f;
                this._fld6f += this._fld4f;
            }
        }
        else {
            this._fld3f += this._fld4f;
            this._fld6f += this._fld4f;
        }
        final int n = this._fld5 * this._fld6;
        if (this._fld3f > this._fld8f - this._fld4f) {
            this._fld3f = 0;
        }
        if (this._fld6f > this._fld9f - this._fld4f) {
            this._fld6f = -this._fld9f;
            this._fld1f = (this._fld1f + 1) % this._fld0f;
            this._mth5b();
        }
        this._mth6b(this._fld7f[this._fld3f], this._fld9e, this._fld2f);
        for (int i = 0; i < n; ++i) {
            final int n2 = this._fld2f[i];
            if (n2 != -16777216) {
                this._fld8[i] = n2;
            }
            else {
                this._fld8[i] = -16777216;
            }
        }
        this._fld0b = this.createImage(this._fld9);
    }
    
    void _mth5b() {
        new MemoryImageSource(this._fld5, this._fld6, this._fld9e, 0, this._fld5);
        final Image image = this.createImage(this._fld5, this._fld6);
        final Graphics graphics = image.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this._fld5, this._fld6);
        graphics.setColor(Color.white);
        graphics.setFont(this._fld4e[this._fld1f]);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this._fld4e[this._fld1f]);
        final int stringWidth = fontMetrics.stringWidth(this._fld5e[this._fld1f]);
        final int height = fontMetrics.getHeight();
        final int n = (height >> 1) + (height >> 3);
        this._fld6e = (this._fld5 >> 1) - (stringWidth >> 1);
        this._fld7e = (this._fld6 >> 1) - (n >> 1);
        graphics.drawString(this._fld5e[this._fld1f], this._fld6e, this._fld7e + n);
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this._fld5, this._fld6, this._fld9e, 0, this._fld5);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        for (int i = 0; i < this._fld5 * this._fld6; ++i) {
            if ((this._fld9e[i] & 0xFF) == 0xFF) {
                this._fld9e[i] = this._fld8e[this._fld1f];
            }
            else {
                this._fld9e[i] = 0;
            }
        }
    }
    
    void _mth6b(int n, final int[] array, final int[] array2) {
        if (n == 0) {
            n = 1;
        }
        final int n2 = 65536 / (n << 1);
        for (int i = 0; i < this._fld6; ++i) {
            int n3 = 0;
            int n4 = 0;
            int n5 = 0;
            final int n6 = i * this._fld5;
            for (int j = 0; j < n; ++j) {
                final int n7 = array[j + n6];
                n5 += (n7 >> 16 & 0xFF);
                n4 += (n7 >> 8 & 0xFF);
                n3 += (n7 & 0xFF);
            }
            final int n8 = array[n6];
            int n9 = n5 + (n8 >> 16 & 0xFF) * n;
            int n10 = n4 + (n8 >> 8 & 0xFF) * n;
            int n11 = n3 + (n8 & 0xFF) * n;
            for (int k = 0; k < this._fld5; ++k) {
                int n13;
                int n14;
                int n15;
                if (k - n >= 0) {
                    final int n12 = array[k - n + n6];
                    n13 = n9 - (n12 >> 16 & 0xFF);
                    n14 = n10 - (n12 >> 8 & 0xFF);
                    n15 = n11 - (n12 & 0xFF);
                }
                else {
                    final int n16 = array[n6];
                    n13 = n9 - (n16 >> 16 & 0xFF);
                    n14 = n10 - (n16 >> 8 & 0xFF);
                    n15 = n11 - (n16 & 0xFF);
                }
                if (k + n < this._fld5) {
                    final int n17 = array[k + n + n6];
                    n9 = n13 + (n17 >> 16 & 0xFF);
                    n10 = n14 + (n17 >> 8 & 0xFF);
                    n11 = n15 + (n17 & 0xFF);
                }
                else {
                    final int n18 = array[this._fld5 - 1 + n6];
                    n9 = n13 + (n18 >> 16 & 0xFF);
                    n10 = n14 + (n18 >> 8 & 0xFF);
                    n11 = n15 + (n18 & 0xFF);
                }
                this._fld2f[k + n6] = (0xFF000000 | (n9 * n2 & 0xFF0000) | (n10 * n2 >> 8 & 0xFF00) | n11 * n2 >> 16);
            }
        }
    }
    
    void _mth7b() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this._fld0g.length() > 9) {
            final int n = this._fld0g.length() - 9;
            final int n2 = n + 9;
            this._fld6g = new byte[n];
            this._fld0g.getBytes(1, n + 1, this._fld6g, 0);
            this._fld7g = new byte[n2];
            this._fld0g.getBytes(0, n2, this._fld7g, 0);
            int n3 = n % 7;
            final int n4 = n % 3;
            for (int i = 0; i < n; ++i) {
                final byte b = this._fld6g[i];
                final byte b2 = (byte)(b + n3);
                if (b >= 48 && b <= 57) {
                    this._fld6g[i] = ((b2 <= 57) ? b2 : ((byte)(b2 - 10)));
                }
                else if (b >= 65 && b <= 90) {
                    this._fld6g[i] = ((b2 <= 90) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b >= 97 && b <= 122) {
                    this._fld6g[i] = ((b2 <= 122) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b == 42) {
                    this._fld6g[i] = 45;
                }
                else if (b == 45) {
                    this._fld6g[i] = 46;
                }
                n3 = (n3 + n4) % 7;
            }
            int n5 = n % 7;
            final int n6 = n % 3;
            for (int j = 0; j < n; ++j) {
                final byte b3 = this._fld6g[j];
                final byte b4 = (byte)(b3 - n5);
                if (b3 >= 48 && b3 <= 57) {
                    this._fld7g[j + 1] = ((b4 >= 48) ? b4 : ((byte)(b4 + 10)));
                }
                else if (b3 >= 65 && b3 <= 90) {
                    this._fld7g[j + 1] = ((b4 >= 65) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 >= 97 && b3 <= 122) {
                    this._fld7g[j + 1] = ((b4 >= 97) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 == 45) {
                    this._fld7g[j + 1] = 42;
                }
                else if (b3 == 46) {
                    this._fld7g[j + 1] = 45;
                }
                n5 = (n5 + n6) % 7;
            }
            byte[] array;
            if (n > 4) {
                array = new byte[n - 4];
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld6g[k + 4];
                }
            }
            else {
                array = new byte[] { 0 };
            }
            if (this._fld7g[0] == this._fld7g[n >> 1] && this._fld7g[1 + n] == this._fld7g[1] && this._fld7g[1 + n + 1] == this._fld7g[n >> 1] && this._fld7g[1 + n + 2] == (byte)(97 + n5) && this._fld7g[1 + n + 3] == 45 && this._fld7g[1 + n + 4] == (byte)(122 - n6) && this._fld7g[1 + n + 5] == (byte)(110 + n5) && this._fld7g[1 + n + 6] == this._fld7g[1] && this._fld7g[1 + n + 7] == this._fld7g[n] && (host.equalsIgnoreCase(new String(this._fld6g, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this._fld5g = true;
            }
        }
        try {
            this._fld4g = new URL("http://" + this._fld7b);
        }
        catch (MalformedURLException ex) {
            this._fld4g = null;
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld5g) {
            this.getAppletContext().showDocument(this._fld4g, "_blank");
        }
        else if (this._fld1g[this._fld1f] != null) {
            if (this._fld2g[this._fld1f] != null) {
                this.getAppletContext().showDocument(this._fld1g[this._fld1f], this._fld2g[this._fld1f]);
            }
            else {
                this.getAppletContext().showDocument(this._fld1g[this._fld1f]);
            }
        }
        return true;
    }
    
    void _mth8b() {
        int fld0d = 0;
        do {
            ++fld0d;
        } while (this.getParameter("overtext" + fld0d) != null);
        if (--fld0d > 0) {
            this._fld0c = true;
            this._fld0d = fld0d;
            this._fld8g = new String[this._fld0d];
            this._fld9g = new Color[this._fld0d];
            this._fld0h = new Color[this._fld0d];
            this._fld1h = new Font[this._fld0d];
            this._fld2h = new FontMetrics[this._fld0d];
            this._fld3h = new String[this._fld0d];
            this._fld4h = new int[this._fld0d];
            this._fld5h = new int[this._fld0d];
            for (int i = 0; i < this._fld0d; ++i) {
                this._fld8g[i] = this.getParameter("overtext" + String.valueOf(i + 1));
                this._fld9g[i] = this._mth0b("overtextcol" + String.valueOf(i + 1), new Color(16777215));
                this._fld0h[i] = this._mth0b("overtextcols" + String.valueOf(i + 1), new Color(0));
                final String parameter = this.getParameter("overtexty" + String.valueOf(i + 1));
                if (parameter == null) {
                    this._fld5h[i] = 10;
                }
                else {
                    this._fld5h[i] = Integer.parseInt(parameter);
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
                this._fld1h[i] = new Font(parameter2, n, int1);
                this._fld2h[i] = this._fld3c.getFontMetrics(this._fld1h[i]);
                this._fld3h[i] = this.getParameter("overTextType" + String.valueOf(i + 1));
                if (this._fld3h[i] == null) {
                    this._fld3h[i] = "scrollleft";
                }
                final String parameter5 = this.getParameter("overtextspeed" + String.valueOf(i + 1));
                if (parameter5 == null) {
                    this._fld4h[i] = 2;
                }
                else {
                    this._fld4h[i] = Integer.valueOf(parameter5);
                    if (this._fld4h[i] < 1 || this._fld4h[i] > 4) {
                        this._fld4h[i] = 2;
                    }
                }
            }
            this._mth9b();
        }
    }
    
    void _mth9b() {
        this._fld3d = this._fld2h[this._fld9c].stringWidth(this._fld8g[this._fld9c]);
        this._fld4d = this._fld2h[this._fld9c].getHeight();
        if (this._fld3h[this._fld9c].equalsIgnoreCase("scrolldown")) {
            this._fld1d = this._fld5 - this._fld3d >> 1;
            this._fld2d = 0;
            return;
        }
        if (this._fld3h[this._fld9c].equalsIgnoreCase("scrollup")) {
            this._fld1d = this._fld5 - this._fld3d >> 1;
            this._fld2d = this._fld6 + this._fld4d;
            return;
        }
        if (this._fld3h[this._fld9c].equalsIgnoreCase("scrollright")) {
            this._fld1d = -this._fld3d;
            this._fld2d = this._fld5h[this._fld9c] + (this._fld4d >> 1) + (this._fld4d >> 3);
            return;
        }
        this._fld1d = this._fld5;
        this._fld2d = this._fld5h[this._fld9c] + (this._fld4d >> 1) + (this._fld4d >> 3);
    }
    
    void _mth0c(final Graphics graphics) {
        graphics.setFont(this._fld1h[this._fld9c]);
        graphics.setColor(this._fld0h[this._fld9c]);
        graphics.drawString(this._fld8g[this._fld9c], this._fld1d + 1, this._fld2d + 1);
        graphics.setColor(this._fld9g[this._fld9c]);
        graphics.drawString(this._fld8g[this._fld9c], this._fld1d, this._fld2d);
        if (this._fld3h[this._fld9c].equalsIgnoreCase("scrolldown")) {
            this._fld2d += this._fld4h[this._fld9c];
        }
        else if (this._fld3h[this._fld9c].equalsIgnoreCase("scrollup")) {
            this._fld2d -= this._fld4h[this._fld9c];
        }
        else if (this._fld3h[this._fld9c].equalsIgnoreCase("scrollright")) {
            this._fld1d += this._fld4h[this._fld9c];
        }
        else {
            this._fld1d -= this._fld4h[this._fld9c];
        }
        if (this._fld2d > this._fld6 + this._fld4d || this._fld2d < -this._fld4d || this._fld1d > this._fld5 || this._fld1d < -this._fld3d) {
            ++this._fld9c;
            if (this._fld9c >= this._fld0d) {
                this._fld9c = 0;
            }
            this._mth9b();
        }
    }
    
    void _mth1c() {
        final String parameter = this.getParameter("OverImage");
        if (parameter != null) {
            this._fld4c = this._mth8(parameter);
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
    
    public DS_XBlurText() {
        this._fld4 = new Font("Helvetica", 1, 12);
        this._fld3b = "Applet by Dario Sciacca";
        this._fld4b = "dario@dseffects.com";
        this._fld7b = "www.dseffects.com";
        this._fld8b = "Don't remove Dario Sciacca's credits line";
        this._fld9b = this._fld3b + " (" + this._fld7b + ")";
        this._fld0c = false;
        this._fld1c = false;
        this._fld0e = "XBlurText started";
        this._fld2e = false;
        this._fld3e = true;
        this._fld0g = "";
        this._fld5g = false;
    }
}
