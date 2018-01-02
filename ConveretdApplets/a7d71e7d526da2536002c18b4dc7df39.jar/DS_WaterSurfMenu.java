import java.awt.Event;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.image.ImageProducer;
import java.awt.Component;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
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

public class DS_WaterSurfMenu extends Applet implements Runnable
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
    Image _fld1c;
    Graphics _fld2c;
    int _fld3c;
    int _fld4c;
    int _fld5c;
    int _fld6c;
    int _fld7c;
    int _fld8c;
    boolean _fld9c;
    String _fld0d;
    Color _fld1d;
    Color _fld2d;
    int _fld3d;
    int _fld4d;
    int _fld5d;
    String _fld6d;
    boolean _fld7d;
    boolean _fld8d;
    boolean _fld9d;
    int _fld0e;
    int[] _fld1e;
    int[] _fld2e;
    int _fld3e;
    int[] _fld4e;
    int[] _fld5e;
    int _fld6e;
    int _fld7e;
    int _fld8e;
    int _fld9e;
    String _fld0f;
    URL _fld1f;
    boolean _fld2f;
    boolean _fld3f;
    byte[] _fld4f;
    byte[] _fld5f;
    String[] _fld6f;
    Color _fld7f;
    Color _fld8f;
    Font _fld9f;
    FontMetrics _fld0g;
    int _fld1g;
    int _fld2g;
    boolean _fld3g;
    String[] _fld4g;
    String[] _fld5g;
    URL[] _fld6g;
    int _fld7g;
    
    public DS_WaterSurfMenu() {
        this._fld3 = 0;
        this._fld5 = new Font("Helvetica", 1, 12);
        this._fld2b = 0;
        this._fld4b = "Applet by Dario Sciacca";
        this._fld5b = "dario@dseffects.com";
        this._fld6b = 0;
        this._fld8b = "www.dseffects.com";
        this._fld9b = "Don't remove Dario Sciacca's credits line";
        this._fld0c = String.valueOf(this._fld4b) + " (" + this._fld8b + ")";
        this._fld9c = false;
        this._fld0d = "";
        this._fld6d = "WaterSurfMenu started";
        this._fld7d = false;
        this._fld9d = false;
        this._fld4e = new int[256];
        this._fld5e = new int[256];
        this._fld6e = 0;
        this._fld7e = 0;
        this._fld0f = "";
        this._fld2f = false;
        this._fld3f = false;
        this._fld1g = 1;
    }
    
    void _mth0() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this._fld0f.length() > 9) {
            final int n = this._fld0f.length() - 9;
            final int n2 = n + 9;
            this._fld4f = new byte[n];
            this._fld0f.getBytes(1, n + 1, this._fld4f, 0);
            this._fld5f = new byte[n2];
            this._fld0f.getBytes(0, n2, this._fld5f, 0);
            int n3 = n % 7;
            final int n4 = n % 3;
            for (int i = 0; i < n; ++i) {
                final byte b = this._fld4f[i];
                final byte b2 = (byte)(b + n3);
                if (b >= 48 && b <= 57) {
                    this._fld4f[i] = ((b2 <= 57) ? b2 : ((byte)(b2 - 10)));
                }
                else if (b >= 65 && b <= 90) {
                    this._fld4f[i] = ((b2 <= 90) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b >= 97 && b <= 122) {
                    this._fld4f[i] = ((b2 <= 122) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b == 42) {
                    this._fld4f[i] = 45;
                }
                else if (b == 45) {
                    this._fld4f[i] = 46;
                }
                n3 = (n3 + n4) % 7;
            }
            int n5 = n % 7;
            final int n6 = n % 3;
            for (int j = 0; j < n; ++j) {
                final byte b3 = this._fld4f[j];
                final byte b4 = (byte)(b3 - n5);
                if (b3 >= 48 && b3 <= 57) {
                    this._fld5f[j + 1] = ((b4 >= 48) ? b4 : ((byte)(b4 + 10)));
                }
                else if (b3 >= 65 && b3 <= 90) {
                    this._fld5f[j + 1] = ((b4 >= 65) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 >= 97 && b3 <= 122) {
                    this._fld5f[j + 1] = ((b4 >= 97) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 == 45) {
                    this._fld5f[j + 1] = 42;
                }
                else if (b3 == 46) {
                    this._fld5f[j + 1] = 45;
                }
                n5 = (n5 + n6) % 7;
            }
            byte[] array;
            if (n > 4) {
                array = new byte[n - 4];
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld4f[k + 4];
                }
            }
            else {
                array = new byte[] { 0 };
            }
            if (this._fld5f[0] == this._fld5f[n >> 2] && this._fld5f[1 + n] == this._fld5f[n >> 1] && this._fld5f[1 + n + 1] == this._fld5f[n >> 1] && this._fld5f[1 + n + 2] == (byte)(112 + n6) && this._fld5f[1 + n + 3] == 45 && this._fld5f[1 + n + 4] == (byte)(112 - n5) && this._fld5f[1 + n + 5] == (byte)(110 + n6) && this._fld5f[1 + n + 6] == this._fld5f[n] && this._fld5f[1 + n + 7] == this._fld5f[n >> 1] && (host.equalsIgnoreCase(new String(this._fld4f, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this._fld2f = true;
            }
        }
        try {
            this._fld1f = new URL("http://" + this._fld8b);
        }
        catch (MalformedURLException ex) {
            this._fld1f = null;
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
    
    void _mth0c(final int[] array, final int[] array2) {
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
            array[n5] -= array[j] >> this._fld3e;
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
        this._fld0e ^= 0x1;
    }
    
    void _mth1b() {
        if (this._fld0e == 0) {
            this._mth9b(this._fld1e);
            this._mth0c(this._fld1e, this._fld2e);
        }
        else {
            this._mth9b(this._fld2e);
            this._mth0c(this._fld2e, this._fld1e);
        }
        this._fld1b = this.createImage(this._fld0b);
    }
    
    void _mth2b(final Graphics graphics) {
        for (int i = 0; i < this._fld3c; ++i) {
            this._fld4c = this._fld0g.stringWidth(this._fld6f[i]);
            this._fld5c = this._fld0g.getHeight();
            final int n = (this._fld5c >> 1) + (this._fld5c >> 3);
            graphics.setFont(this._fld9f);
            if (this._fld2g == 0) {
                if (this._fld3g) {
                    graphics.setColor(new Color(0));
                    graphics.drawString(this._fld6f[i], (this._fld6 >> 1) - (this._fld4c >> 1) + 1, (this._fld7 / this._fld3c >> 2) + this._fld7 / this._fld3c * i + n + 1);
                }
                int fld7 = this._fld7 / this._fld3c * ((i + 1) % this._fld3c);
                if (i == this._fld3c - 1) {
                    fld7 = this._fld7;
                }
                final int n2 = this._fld7 / this._fld3c * i;
                if (this._fld7d && this._fld5d >= n2 && this._fld5d < fld7) {
                    this._fld7g = i;
                    graphics.setColor(this._fld7f);
                    this.setCursor(Cursor.getPredefinedCursor(12));
                }
                else {
                    graphics.setColor(this._fld8f);
                }
                graphics.drawString(this._fld6f[i], (this._fld6 >> 1) - (this._fld4c >> 1), (this._fld7 / this._fld3c >> 2) + this._fld7 / this._fld3c * i + n);
            }
            else {
                if (this._fld3g) {
                    graphics.setColor(new Color(0));
                    graphics.drawString(this._fld6f[i], this._fld6 / this._fld3c * i + (this._fld6 / this._fld3c >> 1) - (this._fld4c >> 1) + 1, (this._fld7 >> 1) + (n >> 1) + 1);
                }
                int fld8 = this._fld6 / this._fld3c * ((i + 1) % this._fld3c);
                if (i == this._fld3c - 1) {
                    fld8 = this._fld6;
                }
                final int n3 = this._fld6 / this._fld3c * i;
                if (this._fld7d && this._fld4d >= n3 && this._fld4d < fld8) {
                    this._fld7g = i;
                    graphics.setColor(this._fld7f);
                    this.setCursor(Cursor.getPredefinedCursor(12));
                }
                else {
                    graphics.setColor(this._fld8f);
                }
                graphics.drawString(this._fld6f[i], this._fld6 / this._fld3c * i + (this._fld6 / this._fld3c >> 1) - (this._fld4c >> 1), (this._fld7 >> 1) + (n >> 1));
            }
        }
    }
    
    private final void _mth3() {
        while (true) {
            this.showStatus(this._fld9b);
        }
    }
    
    void _mth3b() {
        final String parameter = this.getParameter("credits");
        if (parameter != null) {
            if (!parameter.equals(this._fld0c)) {
                this._mth3();
            }
        }
        else {
            this._mth3();
        }
        this._fld6c = 1;
    }
    
    void _mth4b() {
        this._fld7c = 1;
        for (int i = 0; i < 11; ++i) {
            if (this._fld5b.charAt(i) == this._fld4b.charAt(i) || this._fld6c == 0) {
                this._mth3();
            }
        }
        this._fld2f = false;
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
        if (this._fld6c == 0 || this._fld7c == 0) {
            this._mth3();
        }
        for (int i = 0; i < 17; ++i) {
            if (this._fld5b.charAt(i) == this._fld8b.charAt(i)) {
                this._mth3();
            }
        }
        this._fld8c = -16777216;
        if (this._fld4b.charAt(1) != 'p' || this._fld4b.charAt(7) != 'b' || this._fld4b.charAt(21) != 'c' || this._fld4b.charAt(17) != 'c' || this._fld4b.charAt(12) != 'r' || this._fld4b.charAt(11) != 'a') {
            this._mth3();
        }
    }
    
    void _mth6() {
        String parameter = this.getParameter("density");
        if (parameter == null) {
            parameter = "4";
        }
        this._fld3e = Integer.valueOf(parameter);
        this._fld3e = ((this._fld3e >= 1) ? ((this._fld3e <= 5) ? this._fld3e : 5) : 1);
        ++this._fld3e;
        String parameter2 = this.getParameter("speedx");
        if (parameter2 == null) {
            parameter2 = "1";
        }
        this._fld8e = Integer.valueOf(parameter2);
        this._fld8e = ((this._fld8e >= 1) ? ((this._fld8e <= 4) ? this._fld8e : 4) : 1);
        String parameter3 = this.getParameter("speedy");
        if (parameter3 == null) {
            parameter3 = "2";
        }
        this._fld9e = Integer.valueOf(parameter3);
        this._fld9e = ((this._fld9e >= 1) ? ((this._fld9e <= 4) ? this._fld9e : 4) : 1);
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
        int fld3c = 0;
        do {
            ++fld3c;
        } while (this.getParameter("text" + fld3c) != null);
        if (--fld3c == 0 || fld3c == 1) {
            this._fld3c = 2;
        }
        else if (fld3c > 1) {
            this._fld3c = fld3c;
        }
        this._fld6f = new String[this._fld3c];
        this._fld6g = new URL[this._fld3c];
        this._fld4g = new String[this._fld3c];
        this._fld5g = new String[this._fld3c];
        for (int i = 0; i < this._fld3c; ++i) {
            this._fld6f[i] = this.getParameter("text" + String.valueOf(i + 1));
            if (this._fld6f[i] == null) {
                this._fld6f[i] = new String("Text" + String.valueOf(i + 1));
            }
            if (this._fld6f[i].length() > this._fld1g) {
                this._fld1g = this._fld6f[i].length();
            }
            final String parameter = this.getParameter("link" + String.valueOf(i + 1));
            try {
                this._fld6g[i] = new URL("http://" + parameter);
            }
            catch (MalformedURLException ex) {
                this._fld6g[i] = null;
            }
            final String parameter2 = this.getParameter("regtarget" + String.valueOf(i + 1));
            if (parameter2 != null && this._fld2f) {
                this._fld4g[i] = parameter2;
            }
            else {
                this._fld4g[i] = "_blank";
            }
            final String parameter3 = this.getParameter("regstatusmsg" + String.valueOf(i + 1));
            if (parameter3 != null && this._fld2f) {
                this._fld5g[i] = parameter3;
            }
            else {
                this._fld5g[i] = this._fld4b;
            }
        }
        final String parameter4 = this.getParameter("menudir");
        if (parameter4 == null) {
            this._fld2g = 0;
        }
        else if (parameter4.equalsIgnoreCase("vertical")) {
            this._fld2g = 0;
        }
        else {
            this._fld2g = 1;
        }
        final String parameter5 = this.getParameter("textshadow");
        if (parameter5 == null) {
            this._fld3g = false;
        }
        else if (parameter5.equalsIgnoreCase("yes")) {
            this._fld3g = true;
        }
        else {
            this._fld3g = false;
        }
        this._fld7f = this._mth5("seltextcol", new Color(16711680));
        this._fld8f = this._mth5("unseltextcol", new Color(16776960));
        String parameter6 = this.getParameter("textfont");
        if (parameter6 == null) {
            parameter6 = "Helvetica";
        }
        final String parameter7 = this.getParameter("TextStyle");
        int n;
        if (parameter7 == null) {
            n = 0;
        }
        else if (parameter7.equalsIgnoreCase("PLAIN")) {
            n = 0;
        }
        else if (parameter7.equalsIgnoreCase("BOLD")) {
            n = 1;
        }
        else if (parameter7.equalsIgnoreCase("ITALIC")) {
            n = 2;
        }
        else if (parameter7.equalsIgnoreCase("BOLD ITALIC")) {
            n = 3;
        }
        else {
            n = 0;
        }
        int n2;
        if (this._fld2g == 0) {
            n2 = this._fld6 / (this._fld1g + 2) * 2;
            final int n3 = this._fld7 / (this._fld3c << 1);
            if (n3 < n2) {
                n2 = n3;
            }
        }
        else {
            n2 = this._fld6 / this._fld3c / (this._fld1g + 1) * 2;
        }
        this._fld9f = new Font(parameter6, n, n2);
        this._fld0g = this._fld2c.getFontMetrics(this._fld9f);
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
            if (width != this._fld6 || height != this._fld7) {
                final int[] array = new int[width * height];
                if (!this._mth8(mth0b, array, width, height)) {
                    return false;
                }
                this._fld8 = this._mth6b(this._fld8, this._fld6, this._fld7, array, width, height);
            }
            else if (!this._mth8(mth0b, this._fld8, this._fld6, this._fld7)) {
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
    
    void _mth9() {
        this._fld0e = 0;
        this._fld1e = new int[this._fld6 * this._fld7 + this._fld6];
        this._fld2e = new int[this._fld6 * this._fld7 + this._fld6];
        final int n = this._fld6 >> 1;
        final int n2 = this._fld7 >> 1;
        final int n3 = n - (n >> 3);
        final int n4 = n2 - (n2 >> 3);
        for (int i = 0; i < 256; ++i) {
            this._fld4e[i] = (int)(Math.cos(0.02454369260617026 * i) * n3);
            this._fld5e[i] = (int)(Math.sin(0.02454369260617026 * i) * n4);
        }
    }
    
    void _mth9b(final int[] array) {
        final int n = this._fld6 >> 1;
        final int n2 = this._fld7 >> 1;
        this._fld6e = (this._fld6e + this._fld8e) % 256;
        this._fld7e = (this._fld7e + this._fld9e) % 256;
        this._fld8d = true;
        final int n3 = n + this._fld4e[this._fld6e];
        final int n4 = n2 + this._fld5e[this._fld7e];
        if (this._fld8d) {
            array[this._fld6 * n4 + n3] = 400;
            final int n5 = this._fld6 * (n4 - 1) + n3;
            final int n6 = this._fld6 * (n4 + 1) + n3;
            final int n7 = this._fld6 * n4 + n3 - this._fld6;
            final int n8 = this._fld6 * n4 + n3 + this._fld6;
            final int n9 = 200;
            array[n7] = (array[n8] = n9);
            array[n5] = (array[n6] = n9);
        }
        this._fld9d = false;
    }
    
    public String getAppletInfo() {
        return "DS WaterSurfMenu v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
    }
    
    public void init() {
        this._mth3b();
        this.showStatus("Please wait ...");
        this._fld7b = this.getFontMetrics(this._fld5).stringWidth(this._fld8b);
        this._fld6 = this.size().width;
        this._fld7 = this.size().height;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld2f && this._fld3f) {
            this.getAppletContext().showDocument(this._fld1f, "_blank");
        }
        else if (this._fld6g[this._fld7g] != null) {
            if (this._fld2f && this._fld4g[this._fld7g] != null) {
                this.getAppletContext().showDocument(this._fld6g[this._fld7g], this._fld4g[this._fld7g]);
            }
            else {
                this.getAppletContext().showDocument(this._fld6g[this._fld7g], "_blank");
            }
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this._fld5g[this._fld7g]);
        return this._fld7d = true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this._fld7d = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int fld4d, final int fld5d) {
        this.showStatus(this._fld5g[this._fld7g]);
        this._fld4d = fld4d;
        this._fld5d = fld5d;
        return this._fld7d = true;
    }
    
    public void paint(final Graphics graphics) {
        if (!this._fld9c) {
            return;
        }
        final int n = this._fld6 >> 1;
        int n2;
        if (this._fld3d < 200) {
            n2 = 10;
        }
        else if (this._fld3d < 400) {
            n2 = this._fld7 >> 1;
        }
        else {
            n2 = this._fld7 - 10;
        }
        ++this._fld3d;
        this._fld3d %= 600;
        if (this._fld3 == 0) {
            graphics.drawString("Error ...", 10, 10);
        }
        else {
            if (this._fld1b != null) {
                this._fld2c.drawImage(this._fld1b, 0, 0, this);
                this._mth2b(this._fld2c);
            }
            if (this._fld7d && !this._fld2f) {
                this._fld2c.setColor(Color.white);
                this._fld2c.drawLine(n - 64, n2 - 8, n + 64, n2 - 8);
                this._fld2c.drawLine(n - 64, n2 + 8, n + 64, n2 + 8);
                this._fld2c.drawLine(n - 64, n2 - 8, n - 64, n2 + 8);
                this._fld2c.drawLine(n + 64, n2 - 8, n + 64, n2 + 8);
                this._fld2c.setColor(Color.blue);
                this._fld2c.fillRect(n - 63, n2 - 7, 127, 15);
                this._fld2c.setFont(this._fld5);
                this._fld2c.setColor(Color.yellow);
                this._fld2c.drawString(this._fld8b, n - (this._fld7b >> 1), n2 + 5);
                if (this._fld4d > n - 64 && this._fld4d < n + 64 && this._fld5d > n2 - 8 && this._fld5d < n2 + 8) {
                    this._fld3f = true;
                    this.showStatus(this._fld8b);
                }
                else {
                    this._fld3f = false;
                }
            }
            graphics.drawImage(this._fld1c, 0, 0, this);
        }
    }
    
    public void run() {
        if (!this._fld9c) {
            this._fld0d = this.getParameter("loadtext");
            this._fld1d = this._mth5("loadbgcolor", new Color(0));
            this._fld2d = this._mth5("loadtextcolor", new Color(16777215));
            final String parameter = this.getParameter("regkey");
            if (parameter != null) {
                this._fld0f = parameter;
            }
            final Graphics graphics = this.getGraphics();
            graphics.setColor(this._fld1d);
            graphics.fillRect(0, 0, this._fld6, this._fld7);
            graphics.setColor(this._fld2d);
            graphics.drawString(this._fld0d, (this._fld6 >> 1) - (this.getFontMetrics(this._fld5).stringWidth(this._fld0d) >> 1), (this._fld7 >> 1) + 5);
            this._mth4b();
            this._fld9 = new int[this._fld6 * this._fld7];
            this._fld0b = new MemoryImageSource(this._fld6, this._fld7, this._fld9, 0, this._fld6);
            this._fld1c = this.createImage(this._fld6, this._fld7);
            this._fld2c = this._fld1c.getGraphics();
            this._fld8 = new int[this._fld6 * this._fld7];
            final int n = 0xFF000000 | this._fld1d.getRGB();
            for (int i = 0; i < this._fld6 * this._fld7; ++i) {
                this._fld8[i] = n;
            }
            this._mth7b();
            this._mth0();
            this._mth7();
            this._mth6();
            this._mth5b();
            this._mth9();
            if (this._fld8c == -16777216) {
                this._fld3 = 1;
            }
            this._fld9c = true;
        }
        this.showStatus(this._fld6d);
        System.gc();
        final Graphics graphics2 = this.getGraphics();
        this._fld3b = System.currentTimeMillis();
        while (this._fld0 != null) {
            if (this._fld3 == 1) {
                this._mth1b();
            }
            this.paint(graphics2);
            this._mth8b();
            if (this._fld2b++ > 10) {
                System.gc();
                this._fld2b = 0;
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
