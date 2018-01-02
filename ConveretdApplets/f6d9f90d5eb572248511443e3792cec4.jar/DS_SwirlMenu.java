import java.awt.Event;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.net.MalformedURLException;
import java.awt.image.ImageProducer;
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

public class DS_SwirlMenu extends Applet implements Runnable
{
    Thread _fld0;
    int _fld1;
    Font _fld2;
    int _fld4;
    int _fld5;
    int[] _fld6;
    int[] _fld7;
    MemoryImageSource _fld8;
    Image _fld9;
    int _fld0b;
    long _fld1b;
    String _fld2b;
    String _fld3b;
    int _fld4b;
    int _fld5b;
    String _fld6b;
    String _fld7b;
    String _fld8b;
    Image _fld9b;
    Graphics _fld0c;
    int _fld1c;
    int _fld2c;
    int _fld3c;
    int _fld4c;
    int _fld5c;
    int _fld6c;
    boolean _fld7c;
    String _fld8c;
    Color _fld9c;
    Color _fld0d;
    int _fld1d;
    int _fld2d;
    int _fld3d;
    String _fld4d;
    boolean _fld5d;
    int[] _fld6d;
    int[] _fld7d;
    int[] _fld8d;
    int _fld9d;
    int _fld0e;
    int _fld1e;
    int[] _fld2e;
    int _fld3e;
    int _fld4e;
    double _fld5e;
    double _fld6e;
    double[] _fld7e;
    double[] _fld8e;
    double[] _fld9e;
    double[] _fld0f;
    int _fld1f;
    int _fld2f;
    String _fld3f;
    URL _fld4f;
    boolean _fld5f;
    boolean _fld6f;
    byte[] _fld7f;
    byte[] _fld8f;
    String[] _fld9f;
    Color _fld0g;
    Color _fld1g;
    Font _fld2g;
    FontMetrics _fld3g;
    int _fld4g;
    int _fld5g;
    String[] _fld6g;
    String[] _fld7g;
    URL[] _fld8g;
    int _fld9g;
    int _fld0h;
    int _fld1h;
    
    public DS_SwirlMenu() {
        this._fld1 = 0;
        this._fld2 = new Font("Helvetica", 1, 12);
        this._fld0b = 0;
        this._fld2b = "Applet by Dario Sciacca";
        this._fld3b = "dario@dseffects.com";
        this._fld4b = 0;
        this._fld6b = "www.dseffects.com";
        this._fld7b = "Don't remove Dario Sciacca's credits line";
        this._fld8b = String.valueOf(this._fld2b) + " (" + this._fld6b + ")";
        this._fld7c = false;
        this._fld8c = "";
        this._fld4d = "SwirlMenu started";
        this._fld5d = false;
        this._fld5e = 0.0;
        this._fld3f = "";
        this._fld5f = false;
        this._fld6f = false;
        this._fld4g = 1;
    }
    
    void _mth0(final int n) {
        if (this._fld2e[n] > 0) {
            if (this._fld2e[n] < this._fld0e) {
                final int[] fld2e = this._fld2e;
                fld2e[n] -= this._fld2f;
            }
            else {
                final int[] fld2e2 = this._fld2e;
                fld2e2[n] += this._fld2f;
            }
        }
    }
    
    void _mth0b() {
        this._mth1b();
        for (int n = this._fld4 * this._fld5, i = 0; i < n; ++i) {
            final int n2 = this._fld7d[i];
            if (n2 == 16711935) {
                this._fld7[i] = this._fld1h;
            }
            else if (n2 == 16777215) {
                this._fld7[i] = this._fld0h;
            }
            else {
                this._fld7[i] = this._fld6[i];
            }
        }
        this._fld9 = this.createImage(this._fld8);
    }
    
    void _mth1() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this._fld3f.length() > 9) {
            final int n = this._fld3f.length() - 9;
            final int n2 = n + 9;
            this._fld7f = new byte[n];
            this._fld3f.getBytes(1, n + 1, this._fld7f, 0);
            this._fld8f = new byte[n2];
            this._fld3f.getBytes(0, n2, this._fld8f, 0);
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
            byte[] array;
            if (n > 4) {
                array = new byte[n - 4];
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld7f[k + 4];
                }
            }
            else {
                array = new byte[] { 0 };
            }
            if (this._fld8f[0] == this._fld8f[n >> 2] && this._fld8f[1 + n] == this._fld8f[n >> 1] && this._fld8f[1 + n + 1] == this._fld8f[n >> 1] && this._fld8f[1 + n + 2] == (byte)(112 + n6) && this._fld8f[1 + n + 3] == 45 && this._fld8f[1 + n + 4] == (byte)(112 - n5) && this._fld8f[1 + n + 5] == (byte)(110 + n6) && this._fld8f[1 + n + 6] == this._fld8f[n] && this._fld8f[1 + n + 7] == this._fld8f[n >> 1] && (host.equalsIgnoreCase(new String(this._fld7f, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this._fld5f = true;
            }
        }
        try {
            this._fld4f = new URL("http://" + this._fld6b);
        }
        catch (MalformedURLException ex) {
            this._fld4f = null;
        }
    }
    
    void _mth1b() {
        for (int i = 0; i < this._fld1c; ++i) {
            int n3;
            if (this._fld5g == 0) {
                int fld5 = this._fld5 / this._fld1c * ((i + 1) % this._fld1c);
                if (i == this._fld1c - 1) {
                    fld5 = this._fld5;
                }
                final int n = this._fld5 / this._fld1c * i;
                if (this._fld5d && this._fld3d >= n && this._fld3d < fld5) {
                    this._fld9g = i;
                    this.setCursor(Cursor.getPredefinedCursor(12));
                    final int[] fld2e = this._fld2e;
                    final int n2 = i;
                    fld2e[n2] += this._fld2f;
                    n3 = 16776960;
                }
                else {
                    this._mth0(i);
                    n3 = 16711680;
                }
            }
            else {
                int fld6 = this._fld4 / this._fld1c * ((i + 1) % this._fld1c);
                if (i == this._fld1c - 1) {
                    fld6 = this._fld4;
                }
                final int n4 = this._fld4 / this._fld1c * i;
                if (this._fld5d && this._fld2d >= n4 && this._fld2d < fld6) {
                    this._fld9g = i;
                    this.setCursor(Cursor.getPredefinedCursor(12));
                    final int[] fld2e2 = this._fld2e;
                    final int n5 = i;
                    fld2e2[n5] += this._fld2f;
                    n3 = 16776960;
                }
                else {
                    this._mth0(i);
                    n3 = 16711680;
                }
            }
            this._fld2e[i] %= this._fld9d;
            this._fld5e = this._fld8d[this._fld2e[i]];
            this._mth9b(i, n3, this._fld6d, this._fld7d);
        }
    }
    
    private final void _mth2() {
        while (true) {
            this.showStatus(this._fld7b);
        }
    }
    
    void _mth2b() {
        final String parameter = this.getParameter("credits");
        if (parameter != null) {
            if (!parameter.equals(this._fld8b)) {
                this._mth2();
            }
        }
        else {
            this._mth2();
        }
        this._fld4c = 1;
    }
    
    void _mth3b() {
        this._fld5c = 1;
        for (int i = 0; i < 11; ++i) {
            if (this._fld3b.charAt(i) == this._fld2b.charAt(i) || this._fld4c == 0) {
                this._mth2();
            }
        }
        this._fld5f = false;
    }
    
    Color _mth4(final String s, final Color color) {
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
    
    void _mth4b() {
        if (this._fld4c == 0 || this._fld5c == 0) {
            this._mth2();
        }
        for (int i = 0; i < 17; ++i) {
            if (this._fld3b.charAt(i) == this._fld6b.charAt(i)) {
                this._mth2();
            }
        }
        this._fld6c = -16777216;
        if (this._fld2b.charAt(1) != 'p' || this._fld2b.charAt(7) != 'b' || this._fld2b.charAt(21) != 'c' || this._fld2b.charAt(17) != 'c' || this._fld2b.charAt(12) != 'r' || this._fld2b.charAt(11) != 'a') {
            this._mth2();
        }
    }
    
    void _mth5() {
        String parameter = this.getParameter("speed");
        if (parameter == null) {
            parameter = "3";
        }
        this._fld2f = Integer.valueOf(parameter);
        this._fld2f = ((this._fld2f >= 1) ? ((this._fld2f <= 4) ? this._fld2f : 4) : 1);
        this._fld2f <<= 2;
        String parameter2 = this.getParameter("angle");
        if (parameter2 == null) {
            parameter2 = "720";
        }
        this._fld1f = Integer.valueOf(parameter2);
        this._fld1f = ((this._fld1f >= 360) ? ((this._fld1f <= 720) ? this._fld1f : 720) : 360);
    }
    
    int[] _mth5b(final int[] array, final int n, final int n2, final int[] array2, final int n3, final int n4) {
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
    
    void _mth6() {
        int fld1c = 0;
        do {
            ++fld1c;
        } while (this.getParameter("text" + fld1c) != null);
        if (--fld1c == 0 || fld1c == 1) {
            this._fld1c = 2;
        }
        else if (fld1c > 1) {
            this._fld1c = fld1c;
        }
        this._fld9f = new String[this._fld1c];
        this._fld8g = new URL[this._fld1c];
        this._fld6g = new String[this._fld1c];
        this._fld7g = new String[this._fld1c];
        for (int i = 0; i < this._fld1c; ++i) {
            this._fld9f[i] = this.getParameter("text" + String.valueOf(i + 1));
            if (this._fld9f[i] == null) {
                this._fld9f[i] = new String("Text" + String.valueOf(i + 1));
            }
            if (this._fld9f[i].length() > this._fld4g) {
                this._fld4g = this._fld9f[i].length();
            }
            final String parameter = this.getParameter("link" + String.valueOf(i + 1));
            try {
                this._fld8g[i] = new URL("http://" + parameter);
            }
            catch (MalformedURLException ex) {
                this._fld8g[i] = null;
            }
            final String parameter2 = this.getParameter("regtarget" + String.valueOf(i + 1));
            if (parameter2 != null && this._fld5f) {
                this._fld6g[i] = parameter2;
            }
            else {
                this._fld6g[i] = "_blank";
            }
            final String parameter3 = this.getParameter("regstatusmsg" + String.valueOf(i + 1));
            if (parameter3 != null && this._fld5f) {
                this._fld7g[i] = parameter3;
            }
            else {
                this._fld7g[i] = this._fld2b;
            }
        }
        final String parameter4 = this.getParameter("menudir");
        if (parameter4 == null) {
            this._fld5g = 0;
        }
        else if (parameter4.equalsIgnoreCase("vertical")) {
            this._fld5g = 0;
        }
        else {
            this._fld5g = 1;
        }
        this._fld0g = this._mth4("seltextcol", new Color(16711680));
        this._fld1g = this._mth4("unseltextcol", new Color(16776960));
        this._fld0h = (0xFF000000 | this._fld0g.getRGB());
        this._fld1h = (0xFF000000 | this._fld1g.getRGB());
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
        if (this._fld5g == 0) {
            n2 = this._fld4 / (this._fld4g + 2) * 2;
            final int n3 = this._fld5 / (this._fld1c << 1);
            if (n3 < n2) {
                n2 = n3;
            }
        }
        else {
            n2 = this._fld4 / this._fld1c / (this._fld4g + 1) * 2;
        }
        this._fld2g = new Font(parameter5, n, n2);
        this._fld3g = this._fld0c.getFontMetrics(this._fld2g);
    }
    
    boolean _mth6b() {
        final String parameter = this.getParameter("image");
        if (parameter != null) {
            final Image mth9 = this._mth9(parameter);
            if (mth9 == null) {
                this.showStatus("Error loading image ");
                return false;
            }
            final int width = mth9.getWidth(this);
            final int height = mth9.getHeight(this);
            if (width != this._fld4 || height != this._fld5) {
                final int[] array = new int[width * height];
                if (!this._mth7(mth9, array, width, height)) {
                    return false;
                }
                this._fld6 = this._mth5b(this._fld6, this._fld4, this._fld5, array, width, height);
            }
            else if (!this._mth7(mth9, this._fld6, this._fld4, this._fld5)) {
                return false;
            }
            mth9.flush();
            System.gc();
        }
        return true;
    }
    
    boolean _mth7(final Image image, final int[] array, final int n, final int n2) {
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
    
    synchronized void _mth7b() {
        Thread.yield();
        this.getToolkit().sync();
        final long n = 10L - (System.currentTimeMillis() - this._fld1b);
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
        this._fld1b = System.currentTimeMillis();
    }
    
    void _mth8() {
        if (this._fld5g == 0) {
            this._fld3e = this._fld5 / this._fld1c;
            this._fld4e = this._fld4;
        }
        else {
            this._fld3e = this._fld5;
            this._fld4e = this._fld4 / this._fld1c;
        }
        final int n = this._fld4 * this._fld5;
        this._fld6d = new int[n];
        this._fld7d = new int[n];
        this._fld1e = this._fld1f;
        this._fld9d = this._fld1e << 1;
        this._fld0e = this._fld9d >> 1;
        this._fld8d = new int[this._fld9d];
        for (int i = 0; i < this._fld1e; ++i) {
            this._fld8d[i] = i;
        }
        for (int j = 0; j < this._fld1e; ++j) {
            this._fld8d[this._fld1e + j] = this._fld1e - j;
        }
        this._mth8b();
        this._fld7e = new double[this._fld4e * this._fld3e];
        this._fld8e = new double[this._fld4e * this._fld3e];
        this._fld9e = new double[this._fld4e * this._fld3e];
        this._fld0f = new double[this._fld4e * this._fld3e];
        final double n2 = this._fld4e >> 1;
        final double n3 = this._fld3e >> 1;
        this._fld6e = Math.max(n2, n3);
        for (int k = 0; k < this._fld3e; ++k) {
            for (int l = 0; l < this._fld4e; ++l) {
                final int n4 = k * this._fld4e + l;
                this._fld7e[n4] = l - n2;
                this._fld8e[n4] = k - n3;
                this._fld9e[n4] = this._fld7e[n4] * this._fld7e[n4] + this._fld8e[n4] * this._fld8e[n4];
                this._fld0f[n4] = 1.0 - Math.sqrt(this._fld9e[n4]) / this._fld6e;
                final double[] fld0f = this._fld0f;
                final int n5 = n4;
                fld0f[n5] *= this._fld0f[n4];
            }
        }
        this._fld2e = new int[this._fld1c];
    }
    
    void _mth8b() {
        final MemoryImageSource memoryImageSource = new MemoryImageSource(this._fld4, this._fld5, this._fld6d, 0, this._fld4);
        final Image image = this.createImage(this._fld4, this._fld5);
        final Graphics graphics = image.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this._fld4, this._fld5);
        graphics.setColor(Color.white);
        graphics.setFont(this._fld2g);
        for (int i = 0; i < this._fld1c; ++i) {
            this._fld2c = this._fld3g.stringWidth(this._fld9f[i]);
            this._fld3c = this._fld3g.getHeight();
            final int n = (this._fld3c >> 1) + (this._fld3c >> 3);
            graphics.setFont(this._fld2g);
            if (this._fld5g == 0) {
                graphics.drawString(this._fld9f[i], (this._fld4 >> 1) - (this._fld2c >> 1), (this._fld5 / this._fld1c >> 2) + this._fld5 / this._fld1c * i + n);
            }
            else {
                graphics.drawString(this._fld9f[i], this._fld4 / this._fld1c * i + (this._fld4 / this._fld1c >> 1) - (this._fld2c >> 1), (this._fld5 >> 1) + (n >> 1));
            }
        }
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this._fld4, this._fld5, this._fld6d, 0, this._fld4);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        for (int j = 0; j < this._fld4 * this._fld5; ++j) {
            if ((this._fld6d[j] & 0xFF) == 0xFF) {
                this._fld6d[j] = 255;
            }
            else {
                this._fld6d[j] = 0;
            }
        }
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
    
    void _mth9b(final int n, final int n2, final int[] array, final int[] array2) {
        final double n3 = this._fld6e * this._fld6e;
        final double n4 = this._fld5e * 2.0 * 3.141592653589793 / 360.0;
        if (this._fld5g == 0) {
            final int n5 = n * this._fld3e;
            final int n6 = (n + 1) * this._fld3e;
            int n7 = n * (this._fld3e * this._fld4);
            int n8 = 0;
            final int n9 = (this._fld4 >> 1) + this._fld4;
            final int n10 = (this._fld3e >> 1) + this._fld3e;
            for (int i = n5; i < n6; ++i) {
                for (int j = 0; j < this._fld4; ++j) {
                    if (this._fld9e[n8] >= n3) {
                        array2[n7] = (array[n7] | n2);
                    }
                    else {
                        final double sin = Math.sin(n4 * this._fld0f[n8]);
                        final double cos = Math.cos(n4 * this._fld0f[n8]);
                        final double n11 = this._fld7e[n8];
                        final double n12 = this._fld8e[n8];
                        array2[n7] = (array[(n5 + ((int)(sin * n11 + cos * n12) + n10) % this._fld3e) * this._fld4 + ((int)(cos * n11 - sin * n12) + n9) % this._fld4] | n2);
                    }
                    ++n7;
                    ++n8;
                }
            }
        }
        else {
            final int n13 = this._fld4 / this._fld1c;
            final int n14 = n * n13;
            final int n15 = (n + 1) * n13;
            int n16 = n14;
            int n17 = 0;
            final int n18 = (n13 >> 1) + n13;
            final int n19 = (this._fld5 >> 1) + this._fld5;
            for (int k = 0; k < this._fld5; ++k) {
                for (int l = n14; l < n15; ++l) {
                    if (this._fld9e[n17] >= n3) {
                        array2[n16] = (array[n16] | n2);
                    }
                    else {
                        final double sin2 = Math.sin(n4 * this._fld0f[n17]);
                        final double cos2 = Math.cos(n4 * this._fld0f[n17]);
                        final double n20 = this._fld7e[n17];
                        final double n21 = this._fld8e[n17];
                        array2[n16] = (array[((int)(sin2 * n20 + cos2 * n21) + n19) % this._fld5 * this._fld4 + (n14 + ((int)(cos2 * n20 - sin2 * n21) + n18) % n13)] | n2);
                    }
                    ++n16;
                    ++n17;
                }
                n16 += this._fld4 - n13;
            }
        }
    }
    
    public String getAppletInfo() {
        return "DS SwirlMenu v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
    }
    
    public void init() {
        this._mth2b();
        this.showStatus("Please wait ...");
        this._fld5b = this.getFontMetrics(this._fld2).stringWidth(this._fld6b);
        this._fld4 = this.size().width;
        this._fld5 = this.size().height;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld5f && this._fld6f) {
            this.getAppletContext().showDocument(this._fld4f, "_blank");
        }
        else if (this._fld8g[this._fld9g] != null) {
            if (this._fld5f && this._fld6g[this._fld9g] != null) {
                this.getAppletContext().showDocument(this._fld8g[this._fld9g], this._fld6g[this._fld9g]);
            }
            else {
                this.getAppletContext().showDocument(this._fld8g[this._fld9g], "_blank");
            }
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this._fld7g[this._fld9g]);
        return this._fld5d = true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this._fld5d = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int fld2d, final int fld3d) {
        this.showStatus(this._fld7g[this._fld9g]);
        this._fld2d = fld2d;
        this._fld3d = fld3d;
        return this._fld5d = true;
    }
    
    public void paint(final Graphics graphics) {
        if (!this._fld7c) {
            return;
        }
        final int n = this._fld4 >> 1;
        int n2;
        if (this._fld1d < 200) {
            n2 = 10;
        }
        else if (this._fld1d < 400) {
            n2 = this._fld5 >> 1;
        }
        else {
            n2 = this._fld5 - 10;
        }
        ++this._fld1d;
        this._fld1d %= 600;
        if (this._fld1 == 0) {
            graphics.drawString("Error ...", 10, 10);
        }
        else {
            if (this._fld9 != null) {
                this._fld0c.drawImage(this._fld9, 0, 0, this);
            }
            if (this._fld5d && !this._fld5f) {
                this._fld0c.setColor(Color.white);
                this._fld0c.drawLine(n - 64, n2 - 8, n + 64, n2 - 8);
                this._fld0c.drawLine(n - 64, n2 + 8, n + 64, n2 + 8);
                this._fld0c.drawLine(n - 64, n2 - 8, n - 64, n2 + 8);
                this._fld0c.drawLine(n + 64, n2 - 8, n + 64, n2 + 8);
                this._fld0c.setColor(Color.blue);
                this._fld0c.fillRect(n - 63, n2 - 7, 127, 15);
                this._fld0c.setFont(this._fld2);
                this._fld0c.setColor(Color.yellow);
                this._fld0c.drawString(this._fld6b, n - (this._fld5b >> 1), n2 + 5);
                if (this._fld2d > n - 64 && this._fld2d < n + 64 && this._fld3d > n2 - 8 && this._fld3d < n2 + 8) {
                    this._fld6f = true;
                    this.showStatus(this._fld6b);
                }
                else {
                    this._fld6f = false;
                }
            }
            graphics.drawImage(this._fld9b, 0, 0, this);
        }
    }
    
    public void run() {
        if (!this._fld7c) {
            this._fld8c = this.getParameter("loadtext");
            this._fld9c = this._mth4("loadbgcolor", new Color(0));
            this._fld0d = this._mth4("loadtextcolor", new Color(16777215));
            final String parameter = this.getParameter("regkey");
            if (parameter != null) {
                this._fld3f = parameter;
            }
            final Graphics graphics = this.getGraphics();
            graphics.setColor(this._fld9c);
            graphics.fillRect(0, 0, this._fld4, this._fld5);
            graphics.setColor(this._fld0d);
            graphics.drawString(this._fld8c, (this._fld4 >> 1) - (this.getFontMetrics(this._fld2).stringWidth(this._fld8c) >> 1), (this._fld5 >> 1) + 5);
            this._mth3b();
            this._fld7 = new int[this._fld4 * this._fld5];
            this._fld8 = new MemoryImageSource(this._fld4, this._fld5, this._fld7, 0, this._fld4);
            this._fld9b = this.createImage(this._fld4, this._fld5);
            this._fld0c = this._fld9b.getGraphics();
            this._fld6 = new int[this._fld4 * this._fld5];
            final int n = 0xFF000000 | this._fld9c.getRGB();
            for (int i = 0; i < this._fld4 * this._fld5; ++i) {
                this._fld6[i] = n;
            }
            this._mth6b();
            this._mth1();
            this._mth6();
            this._mth5();
            this._mth4b();
            this._mth8();
            if (this._fld6c == -16777216) {
                this._fld1 = 1;
            }
            this._fld7c = true;
        }
        this.showStatus(this._fld4d);
        System.gc();
        final Graphics graphics2 = this.getGraphics();
        this._fld1b = System.currentTimeMillis();
        while (this._fld0 != null) {
            if (this._fld1 == 1) {
                this._mth0b();
            }
            this.paint(graphics2);
            this._mth7b();
            if (this._fld0b++ > 10) {
                System.gc();
                this._fld0b = 0;
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
