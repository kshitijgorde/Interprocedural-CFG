import java.awt.Cursor;
import java.awt.image.ImageProducer;
import java.awt.image.PixelGrabber;
import java.net.MalformedURLException;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Event;
import java.awt.image.ImageObserver;
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

public class DS_ShearMenu extends Applet implements Runnable
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
    int _fld0e;
    int _fld1e;
    int _fld2e;
    int _fld3e;
    int[] _fld4e;
    int _fld5e;
    String _fld6e;
    URL _fld7e;
    boolean _fld8e;
    boolean _fld9e;
    byte[] _fld0f;
    byte[] _fld1f;
    String[] _fld2f;
    Color _fld3f;
    Color _fld4f;
    Font _fld5f;
    FontMetrics _fld6f;
    int _fld7f;
    int _fld8f;
    String[] _fld9f;
    String[] _fld0g;
    URL[] _fld1g;
    int _fld2g;
    int _fld3g;
    int _fld4g;
    int _fld5g;
    
    public DS_ShearMenu() {
        this._fld1 = 0;
        this._fld4 = new Font("Helvetica", 1, 12);
        this._fld1b = 0;
        this._fld3b = "Applet by Dario Sciacca";
        this._fld4b = "dario@dseffects.com";
        this._fld5b = 0;
        this._fld7b = "www.dseffects.com";
        this._fld8b = "Don't remove Dario Sciacca's credits line";
        this._fld9b = this._fld3b + " (" + this._fld7b + ")";
        this._fld8c = false;
        this._fld9c = "";
        this._fld5d = "ShearMenu started";
        this._fld6d = false;
        this._fld6e = "";
        this._fld8e = false;
        this._fld9e = false;
        this._fld7f = 1;
        this._fld3g = 0;
    }
    
    public String getAppletInfo() {
        return "DS ShearMenu v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
    }
    
    public void init() {
        this._mth4();
        this.showStatus("Please wait ...");
        this._fld6b = this.getFontMetrics(this._fld4).stringWidth(this._fld7b);
        this._fld5 = this.size().width;
        this._fld6 = this.size().height;
    }
    
    public void run() {
        if (!this._fld8c) {
            this._fld9c = this.getParameter("loadtext");
            this._fld0d = this._mth1b("loadbgcolor", new Color(0));
            this._fld1d = this._mth1b("loadtextcolor", new Color(16777215));
            final String parameter = this.getParameter("regkey");
            if (parameter != null) {
                this._fld6e = parameter;
            }
            final Graphics graphics = this.getGraphics();
            graphics.setColor(this._fld0d);
            graphics.fillRect(0, 0, this._fld5, this._fld6);
            graphics.setColor(this._fld1d);
            graphics.drawString(this._fld9c, (this._fld5 >> 1) - (this.getFontMetrics(this._fld4).stringWidth(this._fld9c) >> 1), (this._fld6 >> 1) + 5);
            this._mth5();
            this._fld8 = new int[this._fld5 * this._fld6];
            this._fld9 = new MemoryImageSource(this._fld5, this._fld6, this._fld8, 0, this._fld5);
            this._fld0c = this.createImage(this._fld5, this._fld6);
            this._fld1c = this._fld0c.getGraphics();
            this._fld7 = new int[this._fld5 * this._fld6];
            final int n = 0xFF000000 | this._fld0d.getRGB();
            for (int i = 0; i < this._fld5 * this._fld6; ++i) {
                this._fld7[i] = n;
            }
            this._mth8();
            this._mth6b();
            this._mth7b();
            this._mth2b();
            this._mth6();
            this._mth3b();
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
                this._mth4b();
            }
            this.paint(graphics2);
            this._mth0();
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
            if (this._fld6d && !this._fld8e) {
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
                    this._fld9e = true;
                    this.showStatus(this._fld7b);
                }
                else {
                    this._fld9e = false;
                }
            }
            graphics.drawImage(this._fld0c, 0, 0, this);
        }
    }
    
    private final void _mth1() {
        while (true) {
            this.showStatus(this._fld8b);
        }
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this._fld0g[this._fld2g]);
        return this._fld6d = true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this._fld6d = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int fld3d, final int fld4d) {
        this.showStatus(this._fld0g[this._fld2g]);
        this._fld3d = fld3d;
        this._fld4d = fld4d;
        return this._fld6d = true;
    }
    
    void _mth4() {
        final String parameter = this.getParameter("credits");
        if (parameter != null) {
            if (!parameter.equals(this._fld9b)) {
                this._mth1();
            }
        }
        else {
            this._mth1();
        }
        this._fld5c = 1;
    }
    
    void _mth5() {
        this._fld6c = 1;
        for (int i = 0; i < 11; ++i) {
            if (this._fld4b.charAt(i) == this._fld3b.charAt(i) || this._fld5c == 0) {
                this._mth1();
            }
        }
        this._fld8e = false;
    }
    
    void _mth6() {
        if (this._fld5c == 0 || this._fld6c == 0) {
            this._mth1();
        }
        for (int i = 0; i < 17; ++i) {
            if (this._fld4b.charAt(i) == this._fld7b.charAt(i)) {
                this._mth1();
            }
        }
        this._fld7c = -16777216;
        if (this._fld3b.charAt(1) != 'p' || this._fld3b.charAt(7) != 'b' || this._fld3b.charAt(21) != 'c' || this._fld3b.charAt(17) != 'c' || this._fld3b.charAt(12) != 'r' || this._fld3b.charAt(11) != 'a') {
            this._mth1();
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
        final String parameter = this.getParameter("image");
        if (parameter != null) {
            final Image mth9 = this._mth9(parameter);
            if (mth9 == null) {
                this.showStatus("Error loading image ");
                return false;
            }
            final int width = mth9.getWidth(this);
            final int height = mth9.getHeight(this);
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
        }
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
        String parameter = this.getParameter("amplitude");
        if (parameter == null) {
            parameter = "3";
        }
        this._fld2e = Integer.valueOf(parameter);
        this._fld2e = ((this._fld2e >= 1) ? ((this._fld2e <= 6) ? this._fld2e : 6) : 1);
        this._fld2e *= 10;
        String parameter2 = this.getParameter("frequency");
        if (parameter2 == null) {
            parameter2 = "2";
        }
        this._fld3e = Integer.valueOf(parameter2);
        this._fld3e = ((this._fld3e >= 1) ? ((this._fld3e <= 4) ? this._fld3e : 4) : 1);
        this._fld3e *= 5;
    }
    
    void _mth3b() {
        if (this._fld8f == 0) {
            this._fld5e = this._fld6 / this._fld2c;
        }
        else {
            this._fld5e = this._fld6;
        }
        final int n = this._fld5 * this._fld6;
        this._fld7d = new int[n];
        this._fld8d = new int[n];
        this._fld2e = this._fld5e >> 1;
        this._fld0e = this._fld2e << 2;
        this._fld1e = this._fld0e >> 1;
        final int n2 = this._fld0e >> 2;
        this._fld9d = new int[this._fld0e];
        for (int i = 0; i < n2; ++i) {
            this._fld9d[i] = -i;
            this._fld9d[n2 + i] = -(n2 - 1) + i;
            this._fld9d[n2 * 2 + i] = i;
            this._fld9d[n2 * 3 + i] = n2 - 1 - i;
        }
        this._mth8b();
        this._fld4e = new int[this._fld2c];
    }
    
    void _mth4b() {
        this._mth9b();
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
    
    void _mth5b(final int n, final int n2, final int[] array, final int[] array2) {
        if (this._fld8f == 0) {
            final int n3 = n * this._fld5e;
            final int n4 = (n + 1) * this._fld5e;
            int n5 = n * (this._fld5e * this._fld5);
            final int n6 = this._fld5e >> 1;
            final int n7 = this._fld5 >> 1;
            for (int i = -n6; i < n6; ++i) {
                for (int j = -n7; j < n7; ++j) {
                    final int n8 = j + (i * this._fld3g >> 4);
                    if (n8 >= -n7 && n8 < n7) {
                        array2[n5++] = (array[(n3 + i + n6) * this._fld5 + (n8 + n7)] | n2);
                    }
                    else {
                        array2[n5++] = 0;
                    }
                }
            }
        }
        else {
            final int n9 = this._fld5 / this._fld2c;
            int n11;
            final int n10 = n11 = n * n9;
            final int n12 = this._fld6 >> 1;
            final int n13 = n9 >> 1;
            for (int k = -n12; k < n12; ++k) {
                for (int l = -n13; l < n13; ++l) {
                    final int n14 = k + (l * this._fld3g >> 4);
                    if (n14 >= -n12 && n14 < n12) {
                        array2[n11++] = (array[(n14 + n12) * this._fld5 + (n10 + l + n13)] | n2);
                    }
                    else {
                        array2[n11++] = 0;
                    }
                }
                n11 += this._fld5 - n9;
            }
        }
    }
    
    void _mth6b() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this._fld6e.length() > 9) {
            final int n = this._fld6e.length() - 9;
            final int n2 = n + 9;
            this._fld0f = new byte[n];
            this._fld6e.getBytes(1, n + 1, this._fld0f, 0);
            this._fld1f = new byte[n2];
            this._fld6e.getBytes(0, n2, this._fld1f, 0);
            int n3 = n % 7;
            final int n4 = n % 3;
            for (int i = 0; i < n; ++i) {
                final byte b = this._fld0f[i];
                final byte b2 = (byte)(b + n3);
                if (b >= 48 && b <= 57) {
                    this._fld0f[i] = ((b2 <= 57) ? b2 : ((byte)(b2 - 10)));
                }
                else if (b >= 65 && b <= 90) {
                    this._fld0f[i] = ((b2 <= 90) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b >= 97 && b <= 122) {
                    this._fld0f[i] = ((b2 <= 122) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b == 42) {
                    this._fld0f[i] = 45;
                }
                else if (b == 45) {
                    this._fld0f[i] = 46;
                }
                n3 = (n3 + n4) % 7;
            }
            int n5 = n % 7;
            final int n6 = n % 3;
            for (int j = 0; j < n; ++j) {
                final byte b3 = this._fld0f[j];
                final byte b4 = (byte)(b3 - n5);
                if (b3 >= 48 && b3 <= 57) {
                    this._fld1f[j + 1] = ((b4 >= 48) ? b4 : ((byte)(b4 + 10)));
                }
                else if (b3 >= 65 && b3 <= 90) {
                    this._fld1f[j + 1] = ((b4 >= 65) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 >= 97 && b3 <= 122) {
                    this._fld1f[j + 1] = ((b4 >= 97) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 == 45) {
                    this._fld1f[j + 1] = 42;
                }
                else if (b3 == 46) {
                    this._fld1f[j + 1] = 45;
                }
                n5 = (n5 + n6) % 7;
            }
            byte[] array;
            if (n > 4) {
                array = new byte[n - 4];
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld0f[k + 4];
                }
            }
            else {
                array = new byte[] { 0 };
            }
            if (this._fld1f[0] == this._fld1f[n >> 1] && this._fld1f[1 + n] == this._fld1f[1] && this._fld1f[1 + n + 1] == this._fld1f[n >> 1] && this._fld1f[1 + n + 2] == (byte)(97 + n5) && this._fld1f[1 + n + 3] == 45 && this._fld1f[1 + n + 4] == (byte)(122 - n6) && this._fld1f[1 + n + 5] == (byte)(110 + n5) && this._fld1f[1 + n + 6] == this._fld1f[1] && this._fld1f[1 + n + 7] == this._fld1f[n] && (host.equalsIgnoreCase(new String(this._fld0f, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this._fld8e = true;
            }
        }
        try {
            this._fld7e = new URL("http://" + this._fld7b);
        }
        catch (MalformedURLException ex) {
            this._fld7e = null;
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld8e && this._fld9e) {
            this.getAppletContext().showDocument(this._fld7e, "_blank");
        }
        else if (this._fld1g[this._fld2g] != null) {
            if (this._fld8e && this._fld9f[this._fld2g] != null) {
                this.getAppletContext().showDocument(this._fld1g[this._fld2g], this._fld9f[this._fld2g]);
            }
            else {
                this.getAppletContext().showDocument(this._fld1g[this._fld2g], "_blank");
            }
        }
        return true;
    }
    
    void _mth7b() {
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
        this._fld2f = new String[this._fld2c];
        this._fld1g = new URL[this._fld2c];
        this._fld9f = new String[this._fld2c];
        this._fld0g = new String[this._fld2c];
        for (int i = 0; i < this._fld2c; ++i) {
            this._fld2f[i] = this.getParameter("text" + String.valueOf(i + 1));
            if (this._fld2f[i] == null) {
                this._fld2f[i] = new String("Text" + String.valueOf(i + 1));
            }
            if (this._fld2f[i].length() > this._fld7f) {
                this._fld7f = this._fld2f[i].length();
            }
            final String parameter = this.getParameter("link" + String.valueOf(i + 1));
            try {
                this._fld1g[i] = new URL("http://" + parameter);
            }
            catch (MalformedURLException ex) {
                this._fld1g[i] = null;
            }
            final String parameter2 = this.getParameter("regtarget" + String.valueOf(i + 1));
            if (parameter2 != null && this._fld8e) {
                this._fld9f[i] = parameter2;
            }
            else {
                this._fld9f[i] = "_blank";
            }
            final String parameter3 = this.getParameter("regstatusmsg" + String.valueOf(i + 1));
            if (parameter3 != null && this._fld8e) {
                this._fld0g[i] = parameter3;
            }
            else {
                this._fld0g[i] = this._fld3b;
            }
        }
        final String parameter4 = this.getParameter("menudir");
        if (parameter4 == null) {
            this._fld8f = 0;
        }
        else if (parameter4.equalsIgnoreCase("vertical")) {
            this._fld8f = 0;
        }
        else {
            this._fld8f = 1;
        }
        this._fld3f = this._mth1b("seltextcol", new Color(16711680));
        this._fld4f = this._mth1b("unseltextcol", new Color(16776960));
        this._fld4g = (0xFF000000 | this._fld3f.getRGB());
        this._fld5g = (0xFF000000 | this._fld4f.getRGB());
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
        if (this._fld8f == 0) {
            n2 = this._fld5 / (this._fld7f + 2) * 2;
            final int n3 = this._fld6 / (this._fld2c << 1);
            if (n3 < n2) {
                n2 = n3;
            }
        }
        else {
            n2 = this._fld5 / this._fld2c / (this._fld7f + 1) * 2;
        }
        this._fld5f = new Font(parameter5, n, n2);
        this._fld6f = this._fld1c.getFontMetrics(this._fld5f);
    }
    
    void _mth8b() {
        final MemoryImageSource memoryImageSource = new MemoryImageSource(this._fld5, this._fld6, this._fld7d, 0, this._fld5);
        final Image image = this.createImage(this._fld5, this._fld6);
        final Graphics graphics = image.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this._fld5, this._fld6);
        graphics.setColor(Color.white);
        graphics.setFont(this._fld5f);
        for (int i = 0; i < this._fld2c; ++i) {
            this._fld3c = this._fld6f.stringWidth(this._fld2f[i]);
            this._fld4c = this._fld6f.getHeight();
            final int n = (this._fld4c >> 1) + (this._fld4c >> 3);
            graphics.setFont(this._fld5f);
            if (this._fld8f == 0) {
                graphics.drawString(this._fld2f[i], (this._fld5 >> 1) - (this._fld3c >> 1), (this._fld6 / this._fld2c >> 2) + this._fld6 / this._fld2c * i + n);
            }
            else {
                graphics.drawString(this._fld2f[i], this._fld5 / this._fld2c * i + (this._fld5 / this._fld2c >> 1) - (this._fld3c >> 1), (this._fld6 >> 1) + (n >> 1));
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
    
    void _mth9b() {
        for (int i = 0; i < this._fld2c; ++i) {
            int n3;
            if (this._fld8f == 0) {
                int fld6 = this._fld6 / this._fld2c * ((i + 1) % this._fld2c);
                if (i == this._fld2c - 1) {
                    fld6 = this._fld6;
                }
                final int n = this._fld6 / this._fld2c * i;
                if (this._fld6d && this._fld4d >= n && this._fld4d < fld6) {
                    this._fld2g = i;
                    this.setCursor(Cursor.getPredefinedCursor(12));
                    final int[] fld4e = this._fld4e;
                    final int n2 = i;
                    ++fld4e[n2];
                    n3 = 16776960;
                }
                else {
                    this._mth0c(i);
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
                    this._fld2g = i;
                    this.setCursor(Cursor.getPredefinedCursor(12));
                    final int[] fld4e2 = this._fld4e;
                    final int n5 = i;
                    ++fld4e2[n5];
                    n3 = 16776960;
                }
                else {
                    this._mth0c(i);
                    n3 = 16711680;
                }
            }
            this._fld4e[i] %= this._fld0e;
            this._fld3g = this._fld9d[this._fld4e[i]];
            this._mth5b(i, n3, this._fld7d, this._fld8d);
        }
    }
    
    void _mth0c(final int n) {
        if (this._fld4e[n] > 0) {
            if (this._fld4e[n] < this._fld1e) {
                final int[] fld4e = this._fld4e;
                --fld4e[n];
            }
            else {
                final int[] fld4e2 = this._fld4e;
                ++fld4e2[n];
            }
        }
    }
}
