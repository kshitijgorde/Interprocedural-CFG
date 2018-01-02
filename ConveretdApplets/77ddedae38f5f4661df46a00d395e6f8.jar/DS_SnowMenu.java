import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
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

public class DS_SnowMenu extends Applet implements Runnable
{
    Thread _fld0;
    int _fld1;
    Font _fld3;
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
    int _fld6d;
    int _fld7d;
    int _fld8d;
    int[] _fld9d;
    int[] _fld0e;
    int[] _fld1e;
    int[] _fld2e;
    boolean[] _fld3e;
    String _fld4e;
    URL _fld5e;
    boolean _fld6e;
    boolean _fld7e;
    byte[] _fld8e;
    byte[] _fld9e;
    String[] _fld0f;
    Color _fld1f;
    Color _fld2f;
    Font _fld3f;
    FontMetrics _fld4f;
    int _fld5f;
    int _fld6f;
    boolean _fld7f;
    String[] _fld8f;
    String[] _fld9f;
    URL[] _fld0g;
    int _fld1g;
    
    public DS_SnowMenu() {
        this._fld1 = 0;
        this._fld3 = new Font("Helvetica", 1, 12);
        this._fld0b = 0;
        this._fld2b = "Applet by Dario Sciacca";
        this._fld3b = "dario@dseffects.com";
        this._fld4b = 0;
        this._fld6b = "www.dseffects.com";
        this._fld7b = "Don't remove Dario Sciacca's credits line";
        this._fld8b = String.valueOf(this._fld2b) + " (" + this._fld6b + ")";
        this._fld7c = false;
        this._fld8c = "";
        this._fld4d = "SnowMenu started";
        this._fld5d = false;
        this._fld4e = "";
        this._fld6e = false;
        this._fld7e = false;
        this._fld5f = 1;
    }
    
    void _mth0() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this._fld4e.length() > 9) {
            final int n = this._fld4e.length() - 9;
            final int n2 = n + 9;
            this._fld8e = new byte[n];
            this._fld4e.getBytes(1, n + 1, this._fld8e, 0);
            this._fld9e = new byte[n2];
            this._fld4e.getBytes(0, n2, this._fld9e, 0);
            int n3 = n % 7;
            final int n4 = n % 3;
            for (int i = 0; i < n; ++i) {
                final byte b = this._fld8e[i];
                final byte b2 = (byte)(b + n3);
                if (b >= 48 && b <= 57) {
                    this._fld8e[i] = ((b2 <= 57) ? b2 : ((byte)(b2 - 10)));
                }
                else if (b >= 65 && b <= 90) {
                    this._fld8e[i] = ((b2 <= 90) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b >= 97 && b <= 122) {
                    this._fld8e[i] = ((b2 <= 122) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b == 42) {
                    this._fld8e[i] = 45;
                }
                else if (b == 45) {
                    this._fld8e[i] = 46;
                }
                n3 = (n3 + n4) % 7;
            }
            int n5 = n % 7;
            final int n6 = n % 3;
            for (int j = 0; j < n; ++j) {
                final byte b3 = this._fld8e[j];
                final byte b4 = (byte)(b3 - n5);
                if (b3 >= 48 && b3 <= 57) {
                    this._fld9e[j + 1] = ((b4 >= 48) ? b4 : ((byte)(b4 + 10)));
                }
                else if (b3 >= 65 && b3 <= 90) {
                    this._fld9e[j + 1] = ((b4 >= 65) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 >= 97 && b3 <= 122) {
                    this._fld9e[j + 1] = ((b4 >= 97) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 == 45) {
                    this._fld9e[j + 1] = 42;
                }
                else if (b3 == 46) {
                    this._fld9e[j + 1] = 45;
                }
                n5 = (n5 + n6) % 7;
            }
            byte[] array;
            if (n > 4) {
                array = new byte[n - 4];
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld8e[k + 4];
                }
            }
            else {
                array = new byte[] { 0 };
            }
            if (this._fld9e[0] == this._fld9e[n >> 2] && this._fld9e[1 + n] == this._fld9e[n >> 1] && this._fld9e[1 + n + 1] == this._fld9e[n >> 1] && this._fld9e[1 + n + 2] == (byte)(112 + n6) && this._fld9e[1 + n + 3] == 45 && this._fld9e[1 + n + 4] == (byte)(112 - n5) && this._fld9e[1 + n + 5] == (byte)(110 + n6) && this._fld9e[1 + n + 6] == this._fld9e[n] && this._fld9e[1 + n + 7] == this._fld9e[n >> 1] && (host.equalsIgnoreCase(new String(this._fld8e, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this._fld6e = true;
            }
        }
        try {
            this._fld5e = new URL("http://" + this._fld6b);
        }
        catch (MalformedURLException ex) {
            this._fld5e = null;
        }
    }
    
    void _mth0b(final Graphics graphics) {
        for (int i = 0; i < this._fld1c; ++i) {
            this._fld2c = this._fld4f.stringWidth(this._fld0f[i]);
            this._fld3c = this._fld4f.getHeight();
            final int n = (this._fld3c >> 1) + (this._fld3c >> 3);
            graphics.setFont(this._fld3f);
            if (this._fld6f == 0) {
                if (this._fld7f) {
                    graphics.setColor(new Color(0));
                    graphics.drawString(this._fld0f[i], (this._fld4 >> 1) - (this._fld2c >> 1) + 1, (this._fld5 / this._fld1c >> 2) + this._fld5 / this._fld1c * i + n + 1);
                }
                int fld5 = this._fld5 / this._fld1c * ((i + 1) % this._fld1c);
                if (i == this._fld1c - 1) {
                    fld5 = this._fld5;
                }
                final int n2 = this._fld5 / this._fld1c * i;
                if (this._fld5d && this._fld3d >= n2 && this._fld3d < fld5) {
                    this._fld1g = i;
                    graphics.setColor(this._fld1f);
                    this.setCursor(Cursor.getPredefinedCursor(12));
                }
                else {
                    graphics.setColor(this._fld2f);
                }
                graphics.drawString(this._fld0f[i], (this._fld4 >> 1) - (this._fld2c >> 1), (this._fld5 / this._fld1c >> 2) + this._fld5 / this._fld1c * i + n);
            }
            else {
                if (this._fld7f) {
                    graphics.setColor(new Color(0));
                    graphics.drawString(this._fld0f[i], this._fld4 / this._fld1c * i + (this._fld4 / this._fld1c >> 1) - (this._fld2c >> 1) + 1, (this._fld5 >> 1) + (n >> 1) + 1);
                }
                int fld6 = this._fld4 / this._fld1c * ((i + 1) % this._fld1c);
                if (i == this._fld1c - 1) {
                    fld6 = this._fld4;
                }
                final int n3 = this._fld4 / this._fld1c * i;
                if (this._fld5d && this._fld2d >= n3 && this._fld2d < fld6) {
                    this._fld1g = i;
                    graphics.setColor(this._fld1f);
                    this.setCursor(Cursor.getPredefinedCursor(12));
                }
                else {
                    graphics.setColor(this._fld2f);
                }
                graphics.drawString(this._fld0f[i], this._fld4 / this._fld1c * i + (this._fld4 / this._fld1c >> 1) - (this._fld2c >> 1), (this._fld5 >> 1) + (n >> 1));
            }
        }
    }
    
    private final void _mth1() {
        while (true) {
            this.showStatus(this._fld7b);
        }
    }
    
    void _mth1b() {
        final String parameter = this.getParameter("credits");
        if (parameter != null) {
            if (!parameter.equals(this._fld8b)) {
                this._mth1();
            }
        }
        else {
            this._mth1();
        }
        this._fld4c = 1;
    }
    
    void _mth2b() {
        this._fld5c = 1;
        for (int i = 0; i < 11; ++i) {
            if (this._fld3b.charAt(i) == this._fld2b.charAt(i) || this._fld4c == 0) {
                this._mth1();
            }
        }
        this._fld6e = false;
    }
    
    Color _mth3(final String s, final Color color) {
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
    
    void _mth3b() {
        if (this._fld4c == 0 || this._fld5c == 0) {
            this._mth1();
        }
        for (int i = 0; i < 17; ++i) {
            if (this._fld3b.charAt(i) == this._fld6b.charAt(i)) {
                this._mth1();
            }
        }
        this._fld6c = -16777216;
        if (this._fld2b.charAt(1) != 'p' || this._fld2b.charAt(7) != 'b' || this._fld2b.charAt(21) != 'c' || this._fld2b.charAt(17) != 'c' || this._fld2b.charAt(12) != 'r' || this._fld2b.charAt(11) != 'a') {
            this._mth1();
        }
    }
    
    void _mth4() {
        String parameter = this.getParameter("numFlakes");
        if (parameter == null) {
            parameter = "1000";
        }
        this._fld6d = Integer.valueOf(parameter);
        this._fld6d = ((this._fld6d >= 100) ? ((this._fld6d <= 2000) ? this._fld6d : 2000) : 100);
        String parameter2 = this.getParameter("speed");
        if (parameter2 == null) {
            parameter2 = "2";
        }
        this._fld7d = Integer.valueOf(parameter2);
        this._fld7d = ((this._fld7d >= 1) ? ((this._fld7d <= 4) ? this._fld7d : 4) : 1);
    }
    
    int[] _mth4b(final int[] array, final int n, final int n2, final int[] array2, final int n3, final int n4) {
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
    
    void _mth5() {
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
        this._fld0f = new String[this._fld1c];
        this._fld0g = new URL[this._fld1c];
        this._fld8f = new String[this._fld1c];
        this._fld9f = new String[this._fld1c];
        for (int i = 0; i < this._fld1c; ++i) {
            this._fld0f[i] = this.getParameter("text" + String.valueOf(i + 1));
            if (this._fld0f[i] == null) {
                this._fld0f[i] = new String("Text" + String.valueOf(i + 1));
            }
            if (this._fld0f[i].length() > this._fld5f) {
                this._fld5f = this._fld0f[i].length();
            }
            final String parameter = this.getParameter("link" + String.valueOf(i + 1));
            try {
                this._fld0g[i] = new URL("http://" + parameter);
            }
            catch (MalformedURLException ex) {
                this._fld0g[i] = null;
            }
            final String parameter2 = this.getParameter("regtarget" + String.valueOf(i + 1));
            if (parameter2 != null && this._fld6e) {
                this._fld8f[i] = parameter2;
            }
            else {
                this._fld8f[i] = "_blank";
            }
            final String parameter3 = this.getParameter("regstatusmsg" + String.valueOf(i + 1));
            if (parameter3 != null && this._fld6e) {
                this._fld9f[i] = parameter3;
            }
            else {
                this._fld9f[i] = this._fld2b;
            }
        }
        final String parameter4 = this.getParameter("menudir");
        if (parameter4 == null) {
            this._fld6f = 0;
        }
        else if (parameter4.equalsIgnoreCase("vertical")) {
            this._fld6f = 0;
        }
        else {
            this._fld6f = 1;
        }
        final String parameter5 = this.getParameter("textshadow");
        if (parameter5 == null) {
            this._fld7f = false;
        }
        else if (parameter5.equalsIgnoreCase("yes")) {
            this._fld7f = true;
        }
        else {
            this._fld7f = false;
        }
        this._fld1f = this._mth3("seltextcol", new Color(16711680));
        this._fld2f = this._mth3("unseltextcol", new Color(16776960));
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
        if (this._fld6f == 0) {
            n2 = this._fld4 / (this._fld5f + 2) * 2;
            final int n3 = this._fld5 / (this._fld1c << 1);
            if (n3 < n2) {
                n2 = n3;
            }
        }
        else {
            n2 = this._fld4 / this._fld1c / (this._fld5f + 1) * 2;
        }
        this._fld3f = new Font(parameter6, n, n2);
        this._fld4f = this._fld0c.getFontMetrics(this._fld3f);
    }
    
    boolean _mth5b() {
        final String parameter = this.getParameter("image");
        if (parameter != null) {
            final Image mth8 = this._mth8(parameter);
            if (mth8 == null) {
                this.showStatus("Error loading image ");
                return false;
            }
            final int width = mth8.getWidth(this);
            final int height = mth8.getHeight(this);
            if (width != this._fld4 || height != this._fld5) {
                final int[] array = new int[width * height];
                if (!this._mth6(mth8, array, width, height)) {
                    return false;
                }
                this._fld6 = this._mth4b(this._fld6, this._fld4, this._fld5, array, width, height);
            }
            else if (!this._mth6(mth8, this._fld6, this._fld4, this._fld5)) {
                return false;
            }
            mth8.flush();
            System.gc();
        }
        return true;
    }
    
    boolean _mth6(final Image image, final int[] array, final int n, final int n2) {
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
    
    synchronized void _mth6b() {
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
    
    void _mth7() {
        this._fld8d = 0;
        this._fld9d = new int[this._fld6d];
        this._fld1e = new int[this._fld6d];
        this._fld0e = new int[this._fld6d];
        this._fld2e = new int[this._fld6d];
        this._fld3e = new boolean[this._fld6d];
        for (int i = 0; i < this._fld6d; ++i) {
            this._fld1e[i] = (int)((Math.random() - 0.5) * this._fld7d);
            this._fld9d[i] = (int)(Math.random() * this._fld4);
            this._fld0e[i] = (int)(Math.random() * this._fld5);
            this._fld2e[i] = (0xFF000000 | (154 + (int)(Math.random() * 100.0)) * 65793);
            this._fld3e[i] = false;
        }
    }
    
    void _mth7b() {
        for (int i = 0; i < this._fld4 * this._fld5; ++i) {
            this._fld7[i] = this._fld6[i];
        }
        for (int j = 0; j < this._fld6d; j += 2) {
            final double n = Math.random() + Math.random() - 1.0;
            final int[] fld1e = this._fld1e;
            final int n2 = j;
            fld1e[n2] += (int)(2.0 * n);
            if (this._fld1e[j] > this._fld7d) {
                this._fld1e[j] = this._fld7d;
            }
            else if (this._fld1e[j] < -this._fld7d) {
                this._fld1e[j] = -this._fld7d;
            }
            if (this._fld9d != null) {
                final int[] fld9d = this._fld9d;
                final int n3 = j;
                fld9d[n3] += this._fld1e[j];
            }
            this._fld9d[j] = (this._fld9d[j] + this._fld4) % this._fld4;
            this._fld0e[j] += (int)(1.0 + Math.random() * this._fld7d);
            if (this._fld0e[j] >= this._fld5) {
                if (j < this._fld8d) {
                    this._fld3e[j] = true;
                }
                else {
                    this._fld3e[j] = false;
                }
                this._fld0e[j] = 0;
                final int[] fld1e2 = this._fld1e;
                final int n4 = j;
                fld1e2[n4] *= -1;
            }
            if (this._fld3e[j]) {
                this._fld7[this._fld0e[j] * this._fld4 + this._fld9d[j]] = this._fld2e[j];
            }
        }
        for (int k = 1; k < this._fld6d; k += 2) {
            final double n5 = Math.random() + Math.random() - 1.0;
            final int[] fld1e3 = this._fld1e;
            final int n6 = k;
            fld1e3[n6] += (int)(2.0 * n5);
            if (this._fld1e[k] > this._fld7d) {
                this._fld1e[k] = this._fld7d;
            }
            else if (this._fld1e[k] < -this._fld7d) {
                this._fld1e[k] = -this._fld7d;
            }
            if (this._fld9d != null) {
                final int[] fld9d2 = this._fld9d;
                final int n7 = k;
                fld9d2[n7] += this._fld1e[k];
            }
            this._fld9d[k] = (this._fld9d[k] + this._fld4) % (this._fld4 - 1);
            this._fld0e[k] += (int)(1.0 + Math.random() * this._fld7d);
            if (this._fld0e[k] >= this._fld5 - 1) {
                if (k < this._fld8d) {
                    this._fld3e[k] = true;
                }
                else {
                    this._fld3e[k] = false;
                }
                this._fld0e[k] = 0;
                final int[] fld1e4 = this._fld1e;
                final int n8 = k;
                fld1e4[n8] *= -1;
            }
            if (this._fld3e[k]) {
                this._fld7[this._fld0e[k] * this._fld4 + this._fld9d[k]] = this._fld2e[k];
                this._fld7[(this._fld0e[k] + 1) * this._fld4 + this._fld9d[k]] = this._fld2e[k];
                this._fld7[this._fld0e[k] * this._fld4 + this._fld9d[k] + 1] = this._fld2e[k];
                this._fld7[(this._fld0e[k] + 1) * this._fld4 + this._fld9d[k] + 1] = this._fld2e[k];
            }
        }
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
    
    void _mth9() {
        this._fld8d += this._fld7d;
        this._fld8d = ((this._fld8d <= this._fld6d) ? this._fld8d : this._fld6d);
        this._mth7b();
        this._fld9 = this.createImage(this._fld8);
    }
    
    public String getAppletInfo() {
        return "DS SnowMenu v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
    }
    
    public void init() {
        this._mth1b();
        this.showStatus("Please wait ...");
        this._fld5b = this.getFontMetrics(this._fld3).stringWidth(this._fld6b);
        this._fld4 = this.size().width;
        this._fld5 = this.size().height;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld6e && this._fld7e) {
            this.getAppletContext().showDocument(this._fld5e, "_blank");
        }
        else if (this._fld0g[this._fld1g] != null) {
            if (this._fld6e && this._fld8f[this._fld1g] != null) {
                this.getAppletContext().showDocument(this._fld0g[this._fld1g], this._fld8f[this._fld1g]);
            }
            else {
                this.getAppletContext().showDocument(this._fld0g[this._fld1g], "_blank");
            }
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this._fld9f[this._fld1g]);
        return this._fld5d = true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this._fld5d = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int fld2d, final int fld3d) {
        this.showStatus(this._fld9f[this._fld1g]);
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
                this._mth0b(this._fld0c);
            }
            if (this._fld5d && !this._fld6e) {
                this._fld0c.setColor(Color.white);
                this._fld0c.drawLine(n - 64, n2 - 8, n + 64, n2 - 8);
                this._fld0c.drawLine(n - 64, n2 + 8, n + 64, n2 + 8);
                this._fld0c.drawLine(n - 64, n2 - 8, n - 64, n2 + 8);
                this._fld0c.drawLine(n + 64, n2 - 8, n + 64, n2 + 8);
                this._fld0c.setColor(Color.blue);
                this._fld0c.fillRect(n - 63, n2 - 7, 127, 15);
                this._fld0c.setFont(this._fld3);
                this._fld0c.setColor(Color.yellow);
                this._fld0c.drawString(this._fld6b, n - (this._fld5b >> 1), n2 + 5);
                if (this._fld2d > n - 64 && this._fld2d < n + 64 && this._fld3d > n2 - 8 && this._fld3d < n2 + 8) {
                    this._fld7e = true;
                    this.showStatus(this._fld6b);
                }
                else {
                    this._fld7e = false;
                }
            }
            graphics.drawImage(this._fld9b, 0, 0, this);
        }
    }
    
    public void run() {
        if (!this._fld7c) {
            this._fld8c = this.getParameter("loadtext");
            this._fld9c = this._mth3("loadbgcolor", new Color(0));
            this._fld0d = this._mth3("loadtextcolor", new Color(16777215));
            final String parameter = this.getParameter("regkey");
            if (parameter != null) {
                this._fld4e = parameter;
            }
            final Graphics graphics = this.getGraphics();
            graphics.setColor(this._fld9c);
            graphics.fillRect(0, 0, this._fld4, this._fld5);
            graphics.setColor(this._fld0d);
            graphics.drawString(this._fld8c, (this._fld4 >> 1) - (this.getFontMetrics(this._fld3).stringWidth(this._fld8c) >> 1), (this._fld5 >> 1) + 5);
            this._mth2b();
            this._fld7 = new int[this._fld4 * this._fld5];
            this._fld8 = new MemoryImageSource(this._fld4, this._fld5, this._fld7, 0, this._fld4);
            this._fld9b = this.createImage(this._fld4, this._fld5);
            this._fld0c = this._fld9b.getGraphics();
            this._fld6 = new int[this._fld4 * this._fld5];
            final int n = 0xFF000000 | this._fld9c.getRGB();
            for (int i = 0; i < this._fld4 * this._fld5; ++i) {
                this._fld6[i] = n;
            }
            this._mth5b();
            this._mth0();
            this._mth5();
            this._mth4();
            this._mth3b();
            this._mth7();
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
                this._mth9();
            }
            this.paint(graphics2);
            this._mth6b();
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
