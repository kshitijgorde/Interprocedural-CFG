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

public class DS_Water extends Applet implements Runnable
{
    Thread _fld0;
    int _fld1;
    Font _fld2;
    int _fld3;
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
    boolean _fld9b;
    boolean _fld0c;
    Image _fld1c;
    Graphics _fld2c;
    Image _fld3c;
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
    int _fld8d;
    boolean _fld9d;
    boolean _fld0e;
    int _fld1e;
    int[] _fld2e;
    int[] _fld3e;
    int _fld4e;
    String _fld5e;
    URL _fld6e;
    String _fld7e;
    String _fld8e;
    URL _fld9e;
    boolean _fld0f;
    byte[] _fld1f;
    byte[] _fld2f;
    String[] _fld3f;
    Color[] _fld4f;
    Color[] _fld5f;
    Font[] _fld6f;
    FontMetrics[] _fld7f;
    String[] _fld8f;
    int[] _fld9f;
    int[] _fld0g;
    
    public String getAppletInfo() {
        return "DS Water v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
    }
    
    public void init() {
        this._mth2();
        this.showStatus("Please wait ...");
        this._fld5b = this.getFontMetrics(this._fld2).stringWidth(this._fld6b);
        this._fld3 = this.size().width;
        this._fld5 = this.size().height;
        this._mth3();
        this._fld7 = new int[this._fld3 * this._fld5];
        this._fld8 = new MemoryImageSource(this._fld3, this._fld5, this._fld7, 0, this._fld3);
        this._fld1c = this.createImage(this._fld3, this._fld5);
        this._fld2c = this._fld1c.getGraphics();
        this._mth9b();
        this._mth2c();
        if (!this._fld9b && !this._fld0c) {
            this._fld4b = 0;
        }
        else if (this._fld9b && !this._fld0c) {
            this._fld4b = 1;
        }
        else if (!this._fld9b && this._fld0c) {
            this._fld4b = 2;
        }
        else {
            this._fld4b = 3;
        }
        this._mth1b();
        this._mth8b();
        if (this._mth7()) {
            this._mth5();
            this._mth2b();
            if (this._fld6d == -16777216) {
                this._fld1 = 1;
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
        this.showStatus(this._fld7d);
        System.gc();
        final Graphics graphics = this.getGraphics();
        this._fld1b = System.currentTimeMillis();
        while (this._fld0 != null) {
            if (this._fld1 == 1) {
                this._mth3b();
            }
            this._mth1(graphics);
            this._mth0();
            if (this._fld0b++ > 10) {
                System.gc();
                this._fld0b = 0;
            }
        }
    }
    
    synchronized void _mth0() {
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
    
    public void update(final Graphics graphics) {
    }
    
    void _mth1(final Graphics graphics) {
        final int n = this._fld3 >> 1;
        final int n2 = this._fld5 >> 1;
        if (this._fld1 == 0) {
            graphics.drawString("Error ...", 10, 10);
            return;
        }
        if (this._fld9 != null) {
            if (this._fld4b == 0) {
                this._fld2c.drawImage(this._fld9, 0, 0, this);
            }
            else if (this._fld4b == 1) {
                this._fld2c.drawImage(this._fld9, 0, 0, this);
                this._mth1c(this._fld2c);
            }
            else if (this._fld4b == 2) {
                this._fld2c.drawImage(this._fld9, 0, 0, this);
                this._fld2c.drawImage(this._fld3c, this._fld4c, this._fld5c, this);
            }
            else {
                this._fld2c.drawImage(this._fld9, 0, 0, this);
                this._mth1c(this._fld2c);
                this._fld2c.drawImage(this._fld3c, this._fld4c, this._fld5c, this);
            }
        }
        if (this._fld9d && !this._fld0f) {
            this._fld2c.setColor(Color.white);
            this._fld2c.drawLine(n - 64, n2 - 8, n + 64, n2 - 8);
            this._fld2c.drawLine(n - 64, n2 + 8, n + 64, n2 + 8);
            this._fld2c.drawLine(n - 64, n2 - 8, n - 64, n2 + 8);
            this._fld2c.drawLine(n + 64, n2 - 8, n + 64, n2 + 8);
            this._fld2c.setColor(Color.blue);
            this._fld2c.fillRect(n - 63, n2 - 7, 127, 15);
            this._fld2c.setFont(this._fld2);
            this._fld2c.setColor(Color.yellow);
            this._fld2c.drawString(this._fld6b, n - (this._fld5b >> 1), n2 + 5);
        }
        graphics.drawImage(this._fld1c, 0, 0, this);
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this._fld8e);
        return this._fld9d = true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this._fld9d = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int n, final int n2) {
        return this._fld9d = true;
    }
    
    void _mth2() {
        final String parameter = this.getParameter("credits");
        if (parameter != null) {
            if (parameter.equals(this._fld8b)) {
                this._fld4d = 1;
                return;
            }
            while (true) {
                this.showStatus(this._fld7b);
            }
        }
        else {
            while (true) {
                this.showStatus(this._fld7b);
            }
        }
    }
    
    void _mth3() {
        this._fld5d = 1;
        for (int i = 0; i < 11; ++i) {
            if (this._fld3b.charAt(i) == this._fld2b.charAt(i) || this._fld4d == 0) {
                while (true) {
                    this.showStatus(this._fld7b);
                }
            }
            else {}
        }
        this._fld0f = false;
    }
    
    void _mth5() {
        if (this._fld4d == 0 || this._fld5d == 0) {
            while (true) {
                this.showStatus(this._fld7b);
            }
        }
        else {
            for (int i = 0; i < 17; ++i) {
                if (this._fld3b.charAt(i) == this._fld6b.charAt(i)) {
                    while (true) {
                        this.showStatus(this._fld7b);
                    }
                }
                else {}
            }
            this._fld6d = -16777216;
            if (this._fld2b.charAt(1) == 'p' && this._fld2b.charAt(7) == 'b' && this._fld2b.charAt(21) == 'c' && this._fld2b.charAt(17) == 'c' && this._fld2b.charAt(12) == 'r' && this._fld2b.charAt(11) == 'a') {
                return;
            }
            while (true) {
                this.showStatus(this._fld7b);
            }
        }
    }
    
    int[] _mth6(final int[] array, final int n, final int n2, final int[] array2, final int n3, final int n4) {
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
    
    boolean _mth7() {
        final Image mth8 = this._mth8(this.getParameter("image"));
        if (mth8 == null) {
            this.showStatus("Error loading image ");
            return false;
        }
        final int width = mth8.getWidth(this);
        final int height = mth8.getHeight(this);
        this._fld6 = new int[this._fld3 * this._fld5];
        if (width != this._fld3 || height != this._fld5) {
            final int[] array = new int[width * height];
            if (!this._mth9(mth8, array, width, height)) {
                return false;
            }
            this._fld6 = this._mth6(this._fld6, this._fld3, this._fld5, array, width, height);
        }
        else if (!this._mth9(mth8, this._fld6, this._fld3, this._fld5)) {
            return false;
        }
        mth8.flush();
        System.gc();
        return true;
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
        String parameter = this.getParameter("density");
        if (parameter == null) {
            parameter = "4";
        }
        this._fld4e = Integer.valueOf(parameter);
        this._fld4e = ((this._fld4e >= 1) ? ((this._fld4e <= 5) ? this._fld4e : 5) : 1);
        ++this._fld4e;
        final String parameter2 = this.getParameter("interactive");
        if (parameter2 == null) {
            this._fld8d = 2;
        }
        else if (parameter2.equalsIgnoreCase("IN")) {
            this._fld8d = 0;
        }
        else if (parameter2.equalsIgnoreCase("OUT")) {
            this._fld8d = 1;
        }
        else {
            this._fld8d = 2;
        }
        this._fld8e = this._fld2b;
        final String parameter3 = this.getParameter("regkey");
        if (parameter3 != null) {
            this._fld5e = parameter3;
            final String parameter4 = this.getParameter("reglink");
            if (parameter4 != null) {
                try {
                    this._fld6e = new URL("http://" + parameter4);
                }
                catch (MalformedURLException ex) {
                    this._fld6e = null;
                }
                final String parameter5 = this.getParameter("regtarget");
                if (parameter5 != null) {
                    this._fld7e = parameter5;
                }
            }
            final String parameter6 = this.getParameter("regstatusmsg");
            if (parameter6 != null) {
                this._fld8e = parameter6;
            }
        }
    }
    
    void _mth2b() {
        this._fld1e = 0;
        this._fld2e = new int[this._fld3 * this._fld5 + this._fld3];
        this._fld3e = new int[this._fld3 * this._fld5 + this._fld3];
    }
    
    void _mth3b() {
        if (this._fld8d == 0) {
            if (this._fld9d) {
                this._mth5b();
            }
            else {
                this._mth4b();
            }
        }
        else if (this._fld8d == 1) {
            if (!this._fld9d) {
                this._mth5b();
            }
            else {
                this._mth4b();
            }
        }
        else {
            this._mth5b();
        }
        this._fld9 = this.createImage(this._fld8);
    }
    
    void _mth4b() {
        if (this._fld1e == 0) {
            this._mth6b(this._fld2e, this._fld3e);
            return;
        }
        this._mth6b(this._fld3e, this._fld2e);
    }
    
    void _mth5b() {
        if (this._fld1e == 0) {
            this._mth7b(this._fld2e);
            this._mth6b(this._fld2e, this._fld3e);
            return;
        }
        this._mth7b(this._fld3e);
        this._mth6b(this._fld3e, this._fld2e);
    }
    
    void _mth6b(final int[] array, final int[] array2) {
        final int n = this._fld3 * this._fld5;
        final int n2 = n - this._fld3;
        final int n3 = this._fld3 << 1;
        for (int i = 0; i < this._fld3; ++i) {
            this._fld7[i] = this._fld6[i];
            array[i] = 0;
        }
        for (int j = this._fld3; j < n2; ++j) {
            final int n4 = ((array2[j] - array2[j + 2] >> 3) + (array2[j] - array2[j + n3]) >> 3) * this._fld3 + j;
            this._fld7[j] = this._fld6[(n4 <= 0) ? (-n4 % n) : (n4 % n)];
            array[j] = (array2[j - 1] + array2[j + 1] + array2[j - this._fld3] + array2[j + this._fld3] >> 1) - array[j];
            final int n5 = j;
            array[n5] -= array[j] >> this._fld4e;
        }
        for (int k = 1; k < this._fld5 - 1; ++k) {
            final int n6 = k * this._fld3;
            final int n7 = n6 + this._fld3 - 1;
            this._fld7[n6] = this._fld6[n6];
            array[n6] = 0;
            this._fld7[n7] = this._fld6[n7];
            array[n7] = 0;
        }
        for (int l = n2; l < n; ++l) {
            this._fld7[l] = this._fld6[l];
            array[l] = 0;
        }
        this._fld1e ^= 0x1;
    }
    
    void _mth7b(final int[] array) {
        array[this._fld3 * (1 + (int)(Math.random() * (this._fld5 - 2))) + (1 + (int)(Math.random() * (this._fld3 - 2)))] = (int)(Math.random() * 400.0);
    }
    
    void _mth8b() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this._fld5e.length() > 9) {
            final int n = this._fld5e.length() - 9;
            final int n2 = n + 9;
            this._fld1f = new byte[n];
            this._fld5e.getBytes(1, n + 1, this._fld1f, 0);
            this._fld2f = new byte[n2];
            this._fld5e.getBytes(0, n2, this._fld2f, 0);
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
            final byte[] array = new byte[n];
            if (n > 4) {
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld1f[k + 4];
                }
            }
            if (this._fld2f[0] == this._fld2f[n >> 1] && this._fld2f[1 + n] == this._fld2f[1] && this._fld2f[1 + n + 1] == this._fld2f[n >> 1] && this._fld2f[1 + n + 2] == (byte)(97 + n5) && this._fld2f[1 + n + 3] == 45 && this._fld2f[1 + n + 4] == (byte)(122 - n6) && this._fld2f[1 + n + 5] == (byte)(110 + n5) && this._fld2f[1 + n + 6] == this._fld2f[1] && this._fld2f[1 + n + 7] == this._fld2f[n] && (host.equalsIgnoreCase(new String(this._fld1f, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this._fld0f = true;
            }
        }
        try {
            this._fld9e = new URL("http://" + this._fld6b);
        }
        catch (MalformedURLException ex) {
            this._fld9e = null;
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld0f) {
            this.getAppletContext().showDocument(this._fld9e, "_blank");
        }
        else if (this._fld6e != null) {
            if (this._fld7e != null) {
                this.getAppletContext().showDocument(this._fld6e, this._fld7e);
            }
            else {
                this.getAppletContext().showDocument(this._fld6e);
            }
        }
        return true;
    }
    
    void _mth9b() {
        int fld9c = 0;
        do {
            ++fld9c;
        } while (this.getParameter("overtext" + fld9c) != null);
        if (--fld9c > 0) {
            this._fld9b = true;
            this._fld9c = fld9c;
            this._fld3f = new String[this._fld9c];
            this._fld4f = new Color[this._fld9c];
            this._fld5f = new Color[this._fld9c];
            this._fld6f = new Font[this._fld9c];
            this._fld7f = new FontMetrics[this._fld9c];
            this._fld8f = new String[this._fld9c];
            this._fld9f = new int[this._fld9c];
            this._fld0g = new int[this._fld9c];
            for (int i = 0; i < this._fld9c; ++i) {
                this._fld3f[i] = this.getParameter("overtext" + String.valueOf(i + 1));
                this._fld4f[i] = this._mth0b("overtextcol" + String.valueOf(i + 1), new Color(16777215));
                this._fld5f[i] = this._mth0b("overtextcols" + String.valueOf(i + 1), new Color(0));
                final String parameter = this.getParameter("overtexty" + String.valueOf(i + 1));
                if (parameter == null) {
                    this._fld0g[i] = 10;
                }
                else {
                    this._fld0g[i] = Integer.parseInt(parameter);
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
                this._fld6f[i] = new Font(parameter2, n, int1);
                this._fld7f[i] = this._fld2c.getFontMetrics(this._fld6f[i]);
                this._fld8f[i] = this.getParameter("overTextType" + String.valueOf(i + 1));
                if (this._fld8f[i] == null) {
                    this._fld8f[i] = "scrollleft";
                }
                final String parameter5 = this.getParameter("overtextspeed" + String.valueOf(i + 1));
                if (parameter5 == null) {
                    this._fld9f[i] = 2;
                }
                else {
                    this._fld9f[i] = Integer.valueOf(parameter5);
                    if (this._fld9f[i] < 1 || this._fld9f[i] > 4) {
                        this._fld9f[i] = 2;
                    }
                }
            }
            this._mth0c();
        }
    }
    
    void _mth0c() {
        this._fld2d = this._fld7f[this._fld8c].stringWidth(this._fld3f[this._fld8c]);
        this._fld3d = this._fld7f[this._fld8c].getHeight();
        if (this._fld8f[this._fld8c].equalsIgnoreCase("scrolldown")) {
            this._fld0d = this._fld3 - this._fld2d >> 1;
            this._fld1d = 0;
            return;
        }
        if (this._fld8f[this._fld8c].equalsIgnoreCase("scrollup")) {
            this._fld0d = this._fld3 - this._fld2d >> 1;
            this._fld1d = this._fld5 + this._fld3d;
            return;
        }
        if (this._fld8f[this._fld8c].equalsIgnoreCase("scrollright")) {
            this._fld0d = -this._fld2d;
            this._fld1d = this._fld0g[this._fld8c] + (this._fld3d >> 1) + (this._fld3d >> 3);
            return;
        }
        this._fld0d = this._fld3;
        this._fld1d = this._fld0g[this._fld8c] + (this._fld3d >> 1) + (this._fld3d >> 3);
    }
    
    void _mth1c(final Graphics graphics) {
        graphics.setFont(this._fld6f[this._fld8c]);
        graphics.setColor(this._fld5f[this._fld8c]);
        graphics.drawString(this._fld3f[this._fld8c], this._fld0d + 1, this._fld1d + 1);
        graphics.setColor(this._fld4f[this._fld8c]);
        graphics.drawString(this._fld3f[this._fld8c], this._fld0d, this._fld1d);
        if (this._fld8f[this._fld8c].equalsIgnoreCase("scrolldown")) {
            this._fld1d += this._fld9f[this._fld8c];
        }
        else if (this._fld8f[this._fld8c].equalsIgnoreCase("scrollup")) {
            this._fld1d -= this._fld9f[this._fld8c];
        }
        else if (this._fld8f[this._fld8c].equalsIgnoreCase("scrollright")) {
            this._fld0d += this._fld9f[this._fld8c];
        }
        else {
            this._fld0d -= this._fld9f[this._fld8c];
        }
        if (this._fld1d > this._fld5 + this._fld3d || this._fld1d < -this._fld3d || this._fld0d > this._fld3 || this._fld0d < -this._fld2d) {
            ++this._fld8c;
            if (this._fld8c >= this._fld9c) {
                this._fld8c = 0;
            }
            this._mth0c();
        }
    }
    
    void _mth2c() {
        final String parameter = this.getParameter("OverImage");
        if (parameter != null) {
            this._fld3c = this._mth8(parameter);
        }
        if (this._fld3c != null) {
            this._fld0c = true;
            this._fld6c = this._fld3c.getWidth(this);
            this._fld7c = this._fld3c.getHeight(this);
            final String parameter2 = this.getParameter("OverImageX");
            if (parameter2 == null) {
                this._fld4c = (this._fld3 >> 1) - (this._fld6c >> 1);
            }
            else {
                this._fld4c = Integer.valueOf(parameter2);
            }
            final String parameter3 = this.getParameter("OverImageY");
            if (parameter3 == null) {
                this._fld5c = (this._fld5 >> 1) - (this._fld7c >> 1);
                return;
            }
            this._fld5c = Integer.valueOf(parameter3);
        }
    }
    
    public DS_Water() {
        this._fld2 = new Font("Helvetica", 1, 12);
        this._fld2b = "Applet by Dario Sciacca";
        this._fld3b = "dario@dseffects.com";
        this._fld6b = "www.dseffects.com";
        this._fld7b = "Don't remove Dario Sciacca's credits line";
        this._fld8b = this._fld2b + " (" + this._fld6b + ")";
        this._fld9b = false;
        this._fld0c = false;
        this._fld7d = "Water started";
        this._fld9d = false;
        this._fld0e = false;
        this._fld5e = "";
        this._fld7e = "_blank";
        this._fld8e = "Applet by Dario Sciacca";
        this._fld0f = false;
    }
}
