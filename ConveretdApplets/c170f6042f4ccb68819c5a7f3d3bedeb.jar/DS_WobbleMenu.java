import java.awt.Event;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.image.ImageProducer;
import java.net.MalformedURLException;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.FontMetrics;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class DS_WobbleMenu extends Applet implements Runnable
{
    Thread _fld0;
    int _fld1;
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
    Image _fld0c;
    Graphics _fld1c;
    int _fld2c;
    int _fld3c;
    int _fld4c;
    int _fld5c;
    int _fld6c;
    int _fld7c;
    boolean _fld8c;
    String _fld9c;
    Color _fld0d;
    Color _fld1d;
    int _fld2d;
    int _fld3d;
    int _fld4d;
    String _fld5d;
    boolean _fld6d;
    int[] _fld7d;
    int[] _fld8d;
    int[] _fld9d;
    int[] _fld0e;
    int _fld1e;
    int _fld2e;
    int _fld3e;
    int _fld4e;
    int[] _fld5e;
    int _fld6e;
    String _fld7e;
    URL _fld8e;
    boolean _fld9e;
    boolean _fld0f;
    byte[] _fld1f;
    byte[] _fld2f;
    String[] _fld3f;
    Color _fld4f;
    Color _fld5f;
    Font _fld6f;
    FontMetrics _fld7f;
    int _fld8f;
    int _fld9f;
    String[] _fld0g;
    String[] _fld1g;
    URL[] _fld2g;
    int _fld3g;
    int _fld4g;
    int _fld5g;
    
    public DS_WobbleMenu() {
        this._fld1 = 0;
        this._fld4 = new Font("Helvetica", 1, 12);
        this._fld1b = 0;
        this._fld3b = "Applet by Dario Sciacca";
        this._fld4b = "dario@dseffects.com";
        this._fld5b = 0;
        this._fld7b = "www.dseffects.com";
        this._fld8b = "Don't remove Dario Sciacca's credits line";
        this._fld9b = String.valueOf(this._fld3b) + " (" + this._fld7b + ")";
        this._fld8c = false;
        this._fld9c = "";
        this._fld5d = "WobbleMenu started";
        this._fld6d = false;
        this._fld7e = "";
        this._fld9e = false;
        this._fld0f = false;
        this._fld8f = 1;
    }
    
    void _mth0(final int n) {
        if (this._fld5e[n] > 0) {
            if (this._fld5e[n] < this._fld2e) {
                final int[] fld5e = this._fld5e;
                --fld5e[n];
            }
            else {
                final int[] fld5e2 = this._fld5e;
                ++fld5e2[n];
            }
        }
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
    
    void _mth0c(final int n, final int n2, final int[] array, final int[] array2) {
        if (this._fld9f == 0) {
            final int n3 = n * this._fld6e;
            final int n4 = (n + 1) * this._fld6e;
            int n5 = 0;
            int n6 = n * (this._fld6e * this._fld5);
            for (int i = n3; i < n4; ++i) {
                final int n7 = this._fld9d[n5++];
                for (int j = 0; j < this._fld5; ++j) {
                    final int n8 = j + n7;
                    if (n8 >= 0 && n8 < this._fld5) {
                        array2[n6] = (array[i * this._fld5 + n8] | n2);
                    }
                    ++n6;
                }
            }
        }
        else {
            final int n9 = this._fld5 / this._fld2c;
            final int n10 = n * n9;
            final int n11 = (n + 1) * n9;
            int n12 = 0;
            int n13 = n10;
            for (int k = 0; k < this._fld6; ++k) {
                final int n14 = this._fld9d[n12++];
                for (int l = n10; l < n11; ++l) {
                    final int n15 = l + n14;
                    if (n15 >= n10 && n15 < n11) {
                        array2[n13] = (array[k * this._fld5 + n15] | n2);
                    }
                    ++n13;
                }
                n13 += this._fld5 - n9;
            }
        }
    }
    
    void _mth1() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this._fld7e.length() > 9) {
            final int n = this._fld7e.length() - 9;
            final int n2 = n + 9;
            this._fld1f = new byte[n];
            this._fld7e.getBytes(1, n + 1, this._fld1f, 0);
            this._fld2f = new byte[n2];
            this._fld7e.getBytes(0, n2, this._fld2f, 0);
            int n3 = n % 7;
            final int n4 = n % 3;
            for (int i = 0; i < n; ++i) {
                final byte b = this._fld1f[i];
                final byte b2 = (byte)(b + n3);
                if (b >= 48 && b <= 57) {
                    this._fld1f[i] = ((b2 <= 57) ? b2 : ((byte)(b2 - 10)));
                }
                else if (b >= 65 && b <= 90) {
                    this._fld1f[i] = ((b2 <= 90) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b >= 97 && b <= 122) {
                    this._fld1f[i] = ((b2 <= 122) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b == 42) {
                    this._fld1f[i] = 45;
                }
                else if (b == 45) {
                    this._fld1f[i] = 46;
                }
                n3 = (n3 + n4) % 7;
            }
            int n5 = n % 7;
            final int n6 = n % 3;
            for (int j = 0; j < n; ++j) {
                final byte b3 = this._fld1f[j];
                final byte b4 = (byte)(b3 - n5);
                if (b3 >= 48 && b3 <= 57) {
                    this._fld2f[j + 1] = ((b4 >= 48) ? b4 : ((byte)(b4 + 10)));
                }
                else if (b3 >= 65 && b3 <= 90) {
                    this._fld2f[j + 1] = ((b4 >= 65) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 >= 97 && b3 <= 122) {
                    this._fld2f[j + 1] = ((b4 >= 97) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 == 45) {
                    this._fld2f[j + 1] = 42;
                }
                else if (b3 == 46) {
                    this._fld2f[j + 1] = 45;
                }
                n5 = (n5 + n6) % 7;
            }
            byte[] array;
            if (n > 4) {
                array = new byte[n - 4];
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld1f[k + 4];
                }
            }
            else {
                array = new byte[] { 0 };
            }
            if (this._fld2f[0] == this._fld2f[n >> 2] && this._fld2f[1 + n] == this._fld2f[n >> 1] && this._fld2f[1 + n + 1] == this._fld2f[n >> 1] && this._fld2f[1 + n + 2] == (byte)(112 + n6) && this._fld2f[1 + n + 3] == 45 && this._fld2f[1 + n + 4] == (byte)(112 - n5) && this._fld2f[1 + n + 5] == (byte)(110 + n6) && this._fld2f[1 + n + 6] == this._fld2f[n] && this._fld2f[1 + n + 7] == this._fld2f[n >> 1] && (host.equalsIgnoreCase(new String(this._fld1f, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this._fld9e = true;
            }
        }
        try {
            this._fld8e = new URL("http://" + this._fld7b);
        }
        catch (MalformedURLException ex) {
            this._fld8e = null;
        }
    }
    
    void _mth1b() {
        this._mth2b();
        for (int n = this._fld5 * this._fld6, i = 0; i < n; ++i) {
            final int n2 = this._fld8d[i];
            if (n2 == 16711935) {
                this._fld8[i] = this._fld5g;
            }
            else if (n2 == 16777215) {
                this._fld8[i] = this._fld4g;
            }
            else {
                this._fld8[i] = this._fld7[i];
            }
        }
        this._fld0b = this.createImage(this._fld9);
    }
    
    void _mth2b() {
        for (int i = 0; i < this._fld2c; ++i) {
            int n3;
            if (this._fld9f == 0) {
                int fld6 = this._fld6 / this._fld2c * ((i + 1) % this._fld2c);
                if (i == this._fld2c - 1) {
                    fld6 = this._fld6;
                }
                final int n = this._fld6 / this._fld2c * i;
                if (this._fld6d && this._fld4d >= n && this._fld4d < fld6) {
                    this._fld3g = i;
                    this.setCursor(Cursor.getPredefinedCursor(12));
                    final int[] fld5e = this._fld5e;
                    final int n2 = i;
                    ++fld5e[n2];
                    n3 = 16776960;
                }
                else {
                    this._mth0(i);
                    n3 = 16711680;
                }
            }
            else {
                int fld7 = this._fld5 / this._fld2c * ((i + 1) % this._fld2c);
                if (i == this._fld2c - 1) {
                    fld7 = this._fld5;
                }
                final int n4 = this._fld5 / this._fld2c * i;
                if (this._fld6d && this._fld3d >= n4 && this._fld3d < fld7) {
                    this._fld3g = i;
                    this.setCursor(Cursor.getPredefinedCursor(12));
                    final int[] fld5e2 = this._fld5e;
                    final int n5 = i;
                    ++fld5e2[n5];
                    n3 = 16776960;
                }
                else {
                    this._mth0(i);
                    n3 = 16711680;
                }
            }
            this._fld5e[i] %= this._fld1e;
            final int n6 = this._fld0e[this._fld5e[i]];
            for (int j = 0; j < this._fld6e; ++j) {
                this._fld9d[j] = (int)(n6 * Math.sin(this._fld4e * (j - this._fld5e[i]) * 3.141592653589793 / 180.0));
            }
            this._mth0c(i, n3, this._fld7d, this._fld8d);
        }
    }
    
    void _mth3b() {
        final String parameter = this.getParameter("credits");
        if (parameter != null) {
            if (!parameter.equals(this._fld9b)) {
                this._mth4();
            }
        }
        else {
            this._mth4();
        }
        this._fld5c = 1;
    }
    
    private final void _mth4() {
        while (true) {
            this.showStatus(this._fld8b);
        }
    }
    
    void _mth4b() {
        this._fld6c = 1;
        for (int i = 0; i < 11; ++i) {
            if (this._fld4b.charAt(i) == this._fld3b.charAt(i) || this._fld5c == 0) {
                this._mth4();
            }
        }
        this._fld9e = false;
    }
    
    Color _mth5(final String s, final Color color) {
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
    
    void _mth5b() {
        if (this._fld5c == 0 || this._fld6c == 0) {
            this._mth4();
        }
        for (int i = 0; i < 17; ++i) {
            if (this._fld4b.charAt(i) == this._fld7b.charAt(i)) {
                this._mth4();
            }
        }
        this._fld7c = -16777216;
        if (this._fld3b.charAt(1) != 'p' || this._fld3b.charAt(7) != 'b' || this._fld3b.charAt(21) != 'c' || this._fld3b.charAt(17) != 'c' || this._fld3b.charAt(12) != 'r' || this._fld3b.charAt(11) != 'a') {
            this._mth4();
        }
    }
    
    void _mth6() {
        String parameter = this.getParameter("amplitude");
        if (parameter == null) {
            parameter = "3";
        }
        this._fld3e = Integer.valueOf(parameter);
        this._fld3e = ((this._fld3e >= 1) ? ((this._fld3e <= 6) ? this._fld3e : 6) : 1);
        this._fld3e *= 10;
        String parameter2 = this.getParameter("frequency");
        if (parameter2 == null) {
            parameter2 = "2";
        }
        this._fld4e = Integer.valueOf(parameter2);
        this._fld4e = ((this._fld4e >= 1) ? ((this._fld4e <= 4) ? this._fld4e : 4) : 1);
        this._fld4e *= 5;
    }
    
    int[] _mth6b(final int[] array, final int n, final int n2, final int[] array2, final int n3, final int n4) {
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
    
    void _mth7() {
        int fld2c = 0;
        do {
            ++fld2c;
        } while (this.getParameter("text" + fld2c) != null);
        if (--fld2c == 0 || fld2c == 1) {
            this._fld2c = 2;
        }
        else if (fld2c > 1) {
            this._fld2c = fld2c;
        }
        this._fld3f = new String[this._fld2c];
        this._fld2g = new URL[this._fld2c];
        this._fld0g = new String[this._fld2c];
        this._fld1g = new String[this._fld2c];
        for (int i = 0; i < this._fld2c; ++i) {
            this._fld3f[i] = this.getParameter("text" + String.valueOf(i + 1));
            if (this._fld3f[i] == null) {
                this._fld3f[i] = new String("Text" + String.valueOf(i + 1));
            }
            if (this._fld3f[i].length() > this._fld8f) {
                this._fld8f = this._fld3f[i].length();
            }
            final String parameter = this.getParameter("link" + String.valueOf(i + 1));
            try {
                this._fld2g[i] = new URL("http://" + parameter);
            }
            catch (MalformedURLException ex) {
                this._fld2g[i] = null;
            }
            final String parameter2 = this.getParameter("regtarget" + String.valueOf(i + 1));
            if (parameter2 != null && this._fld9e) {
                this._fld0g[i] = parameter2;
            }
            else {
                this._fld0g[i] = "_blank";
            }
            final String parameter3 = this.getParameter("regstatusmsg" + String.valueOf(i + 1));
            if (parameter3 != null && this._fld9e) {
                this._fld1g[i] = parameter3;
            }
            else {
                this._fld1g[i] = this._fld3b;
            }
        }
        final String parameter4 = this.getParameter("menudir");
        if (parameter4 == null) {
            this._fld9f = 0;
        }
        else if (parameter4.equalsIgnoreCase("vertical")) {
            this._fld9f = 0;
        }
        else {
            this._fld9f = 1;
        }
        this._fld4f = this._mth5("seltextcol", new Color(16711680));
        this._fld5f = this._mth5("unseltextcol", new Color(16776960));
        this._fld4g = (0xFF000000 | this._fld4f.getRGB());
        this._fld5g = (0xFF000000 | this._fld5f.getRGB());
        String parameter5 = this.getParameter("textfont");
        if (parameter5 == null) {
            parameter5 = "Helvetica";
        }
        final String parameter6 = this.getParameter("TextStyle");
        int n;
        if (parameter6 == null) {
            n = 0;
        }
        else if (parameter6.equalsIgnoreCase("PLAIN")) {
            n = 0;
        }
        else if (parameter6.equalsIgnoreCase("BOLD")) {
            n = 1;
        }
        else if (parameter6.equalsIgnoreCase("ITALIC")) {
            n = 2;
        }
        else if (parameter6.equalsIgnoreCase("BOLD ITALIC")) {
            n = 3;
        }
        else {
            n = 0;
        }
        int n2;
        if (this._fld9f == 0) {
            n2 = this._fld5 / (this._fld8f + 2) * 2;
            final int n3 = this._fld6 / (this._fld2c << 1);
            if (n3 < n2) {
                n2 = n3;
            }
        }
        else {
            n2 = this._fld5 / this._fld2c / (this._fld8f + 1) * 2;
        }
        this._fld6f = new Font(parameter5, n, n2);
        this._fld7f = this._fld1c.getFontMetrics(this._fld6f);
    }
    
    boolean _mth7b() {
        final String parameter = this.getParameter("image");
        if (parameter != null) {
            final Image mth0b = this._mth0b(parameter);
            if (mth0b == null) {
                this.showStatus("Error loading image ");
                return false;
            }
            final int width = mth0b.getWidth(this);
            final int height = mth0b.getHeight(this);
            if (width != this._fld5 || height != this._fld6) {
                final int[] array = new int[width * height];
                if (!this._mth8(mth0b, array, width, height)) {
                    return false;
                }
                this._fld7 = this._mth6b(this._fld7, this._fld5, this._fld6, array, width, height);
            }
            else if (!this._mth8(mth0b, this._fld7, this._fld5, this._fld6)) {
                return false;
            }
            mth0b.flush();
            System.gc();
        }
        return true;
    }
    
    boolean _mth8(final Image image, final int[] array, final int n, final int n2) {
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
    
    synchronized void _mth8b() {
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
    
    void _mth9() {
        if (this._fld9f == 0) {
            this._fld6e = this._fld6 / this._fld2c;
        }
        else {
            this._fld6e = this._fld6;
        }
        final int n = this._fld5 * this._fld6;
        this._fld7d = new int[n];
        this._fld8d = new int[n];
        this._fld3e = this._fld6e >> 1;
        this._fld1e = this._fld3e << 1;
        this._fld2e = this._fld1e >> 1;
        this._fld0e = new int[this._fld1e];
        for (int i = 0; i < this._fld3e; ++i) {
            this._fld0e[i] = i;
        }
        for (int j = 0; j < this._fld3e; ++j) {
            this._fld0e[this._fld3e + j] = this._fld3e - j;
        }
        this._mth9b();
        this._fld9d = new int[this._fld6e];
        this._fld5e = new int[this._fld2c];
    }
    
    void _mth9b() {
        final MemoryImageSource memoryImageSource = new MemoryImageSource(this._fld5, this._fld6, this._fld7d, 0, this._fld5);
        final Image image = this.createImage(this._fld5, this._fld6);
        final Graphics graphics = image.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this._fld5, this._fld6);
        graphics.setColor(Color.white);
        graphics.setFont(this._fld6f);
        for (int i = 0; i < this._fld2c; ++i) {
            this._fld3c = this._fld7f.stringWidth(this._fld3f[i]);
            this._fld4c = this._fld7f.getHeight();
            final int n = (this._fld4c >> 1) + (this._fld4c >> 3);
            graphics.setFont(this._fld6f);
            if (this._fld9f == 0) {
                graphics.drawString(this._fld3f[i], (this._fld5 >> 1) - (this._fld3c >> 1), (this._fld6 / this._fld2c >> 2) + this._fld6 / this._fld2c * i + n);
            }
            else {
                graphics.drawString(this._fld3f[i], this._fld5 / this._fld2c * i + (this._fld5 / this._fld2c >> 1) - (this._fld3c >> 1), (this._fld6 >> 1) + (n >> 1));
            }
        }
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this._fld5, this._fld6, this._fld7d, 0, this._fld5);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        for (int j = 0; j < this._fld5 * this._fld6; ++j) {
            if ((this._fld7d[j] & 0xFF) == 0xFF) {
                this._fld7d[j] = 255;
            }
            else {
                this._fld7d[j] = 0;
            }
        }
    }
    
    public String getAppletInfo() {
        return "DS WobbleMenu v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
    }
    
    public void init() {
        this._mth3b();
        this.showStatus("Please wait ...");
        this._fld6b = this.getFontMetrics(this._fld4).stringWidth(this._fld7b);
        this._fld5 = this.size().width;
        this._fld6 = this.size().height;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld9e && this._fld0f) {
            this.getAppletContext().showDocument(this._fld8e, "_blank");
        }
        else if (this._fld2g[this._fld3g] != null) {
            if (this._fld9e && this._fld0g[this._fld3g] != null) {
                this.getAppletContext().showDocument(this._fld2g[this._fld3g], this._fld0g[this._fld3g]);
            }
            else {
                this.getAppletContext().showDocument(this._fld2g[this._fld3g], "_blank");
            }
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this._fld1g[this._fld3g]);
        return this._fld6d = true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this._fld6d = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int fld3d, final int fld4d) {
        this.showStatus(this._fld1g[this._fld3g]);
        this._fld3d = fld3d;
        this._fld4d = fld4d;
        return this._fld6d = true;
    }
    
    public void paint(final Graphics graphics) {
        if (!this._fld8c) {
            return;
        }
        final int n = this._fld5 >> 1;
        int n2;
        if (this._fld2d < 200) {
            n2 = 10;
        }
        else if (this._fld2d < 400) {
            n2 = this._fld6 >> 1;
        }
        else {
            n2 = this._fld6 - 10;
        }
        ++this._fld2d;
        this._fld2d %= 600;
        if (this._fld1 == 0) {
            graphics.drawString("Error ...", 10, 10);
        }
        else {
            if (this._fld0b != null) {
                this._fld1c.drawImage(this._fld0b, 0, 0, this);
            }
            if (this._fld6d && !this._fld9e) {
                this._fld1c.setColor(Color.white);
                this._fld1c.drawLine(n - 64, n2 - 8, n + 64, n2 - 8);
                this._fld1c.drawLine(n - 64, n2 + 8, n + 64, n2 + 8);
                this._fld1c.drawLine(n - 64, n2 - 8, n - 64, n2 + 8);
                this._fld1c.drawLine(n + 64, n2 - 8, n + 64, n2 + 8);
                this._fld1c.setColor(Color.blue);
                this._fld1c.fillRect(n - 63, n2 - 7, 127, 15);
                this._fld1c.setFont(this._fld4);
                this._fld1c.setColor(Color.yellow);
                this._fld1c.drawString(this._fld7b, n - (this._fld6b >> 1), n2 + 5);
                if (this._fld3d > n - 64 && this._fld3d < n + 64 && this._fld4d > n2 - 8 && this._fld4d < n2 + 8) {
                    this._fld0f = true;
                    this.showStatus(this._fld7b);
                }
                else {
                    this._fld0f = false;
                }
            }
            graphics.drawImage(this._fld0c, 0, 0, this);
        }
    }
    
    public void run() {
        if (!this._fld8c) {
            this._fld9c = this.getParameter("loadtext");
            this._fld0d = this._mth5("loadbgcolor", new Color(0));
            this._fld1d = this._mth5("loadtextcolor", new Color(16777215));
            final String parameter = this.getParameter("regkey");
            if (parameter != null) {
                this._fld7e = parameter;
            }
            final Graphics graphics = this.getGraphics();
            graphics.setColor(this._fld0d);
            graphics.fillRect(0, 0, this._fld5, this._fld6);
            graphics.setColor(this._fld1d);
            graphics.drawString(this._fld9c, (this._fld5 >> 1) - (this.getFontMetrics(this._fld4).stringWidth(this._fld9c) >> 1), (this._fld6 >> 1) + 5);
            this._mth4b();
            this._fld8 = new int[this._fld5 * this._fld6];
            this._fld9 = new MemoryImageSource(this._fld5, this._fld6, this._fld8, 0, this._fld5);
            this._fld0c = this.createImage(this._fld5, this._fld6);
            this._fld1c = this._fld0c.getGraphics();
            this._fld7 = new int[this._fld5 * this._fld6];
            final int n = 0xFF000000 | this._fld0d.getRGB();
            for (int i = 0; i < this._fld5 * this._fld6; ++i) {
                this._fld7[i] = n;
            }
            this._mth7b();
            this._mth1();
            this._mth7();
            this._mth6();
            this._mth5b();
            this._mth9();
            if (this._fld7c == -16777216) {
                this._fld1 = 1;
            }
            this._fld8c = true;
        }
        this.showStatus(this._fld5d);
        System.gc();
        final Graphics graphics2 = this.getGraphics();
        this._fld2b = System.currentTimeMillis();
        while (this._fld0 != null) {
            if (this._fld1 == 1) {
                this._mth1b();
            }
            this.paint(graphics2);
            this._mth8b();
            if (this._fld1b++ > 10) {
                System.gc();
                this._fld1b = 0;
            }
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
    
    public void update(final Graphics graphics) {
    }
}
