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

public class DS_PlasmaText extends Applet implements Runnable
{
    Thread _fld0;
    int _fld3;
    Font _fld5;
    int _fld6;
    int _fld7;
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
    String _fld0e;
    int _fld1e;
    boolean _fld2e;
    boolean _fld3e;
    Font[] _fld4e;
    String[] _fld5e;
    int[] _fld6e;
    int[] _fld7e;
    int[] _fld8e;
    int _fld9e;
    int[] _fld0f;
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
    int[] _fld3g;
    int[] _fld4g;
    int[] _fld5g;
    int[] tr;
    int[] tg;
    int[] tb;
    int _fld6g;
    int[] _fld7g;
    int _fld8g;
    int _fld9g;
    int _fld0h;
    int _fld1h;
    int _fld2h;
    int _fld3h;
    int _fld4h;
    int _fld5h;
    int _fld6h;
    String _fld7h;
    URL[] _fld8h;
    String[] _fld9h;
    String[] _fld0i;
    URL _fld1i;
    boolean _fld2i;
    byte[] _fld3i;
    byte[] _fld4i;
    String[] _fld5i;
    Color[] _fld6i;
    Color[] _fld7i;
    Font[] _fld8i;
    FontMetrics[] _fld9i;
    String[] _fld0j;
    int[] _fld1j;
    int[] _fld2j;
    
    public String getAppletInfo() {
        return "DS PlasmaText v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
    }
    
    public void init() {
        this._mth5();
        this.showStatus("Please wait ...");
        this._fld8b = this.getFontMetrics(this._fld5).stringWidth(this._fld9b);
        this._fld6 = this.size().width;
        this._fld7 = this.size().height;
        this._mth6();
        this._fld0b = new int[this._fld6 * this._fld7];
        this._fld1b = new MemoryImageSource(this._fld6, this._fld7, this._fld0b, 0, this._fld6);
        this._fld4c = this.createImage(this._fld6, this._fld7);
        this._fld5c = this._fld4c.getGraphics();
        this._mth5c();
        this._mth8c();
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
        this._mth4b();
        this._mth4c();
        if (!this._mth0b()) {
            this._fld3e = false;
        }
        this._mth7();
        this._mth5b();
        if (this._fld9d == -16777216) {
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
        this._fld4b = System.currentTimeMillis();
        while (this._fld0 != null) {
            if (this._fld3 == 1) {
                this._mth7b();
            }
            this._mth3(graphics);
            this._mth0();
            if (this._fld3b++ > 10) {
                System.gc();
                this._fld3b = 0;
            }
        }
    }
    
    synchronized void _mth0() {
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
        final int n = this._fld6 >> 1;
        final int n2 = this._fld7 >> 1;
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
                this._mth7c(this._fld5c);
            }
            else if (this._fld7b == 2) {
                this._fld5c.drawImage(this._fld2b, 0, 0, this);
                this._fld5c.drawImage(this._fld6c, this._fld7c, this._fld8c, this);
            }
            else {
                this._fld5c.drawImage(this._fld2b, 0, 0, this);
                this._mth7c(this._fld5c);
                this._fld5c.drawImage(this._fld6c, this._fld7c, this._fld8c, this);
            }
        }
        if (this._fld2e && !this._fld2i) {
            this._fld5c.setColor(Color.white);
            this._fld5c.drawLine(n - 64, n2 - 8, n + 64, n2 - 8);
            this._fld5c.drawLine(n - 64, n2 + 8, n + 64, n2 + 8);
            this._fld5c.drawLine(n - 64, n2 - 8, n - 64, n2 + 8);
            this._fld5c.drawLine(n + 64, n2 - 8, n + 64, n2 + 8);
            this._fld5c.setColor(Color.blue);
            this._fld5c.fillRect(n - 63, n2 - 7, 127, 15);
            this._fld5c.setFont(this._fld5);
            this._fld5c.setColor(Color.yellow);
            this._fld5c.drawString(this._fld9b, n - (this._fld8b >> 1), n2 + 5);
        }
        graphics.drawImage(this._fld4c, 0, 0, this);
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this._fld0i[this._fld2f]);
        return this._fld2e = true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this._fld2e = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int n, final int n2) {
        return this._fld2e = true;
    }
    
    void _mth5() {
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
    
    void _mth6() {
        this._fld8d = 1;
        for (int i = 0; i < 11; ++i) {
            if (this._fld6b.charAt(i) == this._fld5b.charAt(i) || this._fld7d == 0) {
                while (true) {
                    this.showStatus(this._fld0c);
                }
            }
            else {}
        }
        this._fld2i = false;
    }
    
    void _mth7() {
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
    
    int[] _mth9(final int[] array, final int n, final int n2, final int[] array2, final int n3, final int n4) {
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
    
    boolean _mth0b() {
        final String parameter = this.getParameter("image");
        if (parameter == null) {
            return false;
        }
        final Image mth1b = this._mth1b(parameter);
        if (mth1b == null) {
            this.showStatus("Error loading image ");
            return false;
        }
        final int width = mth1b.getWidth(this);
        final int height = mth1b.getHeight(this);
        this._fld9 = new int[this._fld6 * this._fld7];
        if (width != this._fld6 || height != this._fld7) {
            final int[] array = new int[width * height];
            if (!this._mth2b(mth1b, array, width, height)) {
                return false;
            }
            this._fld9 = this._mth9(this._fld9, this._fld6, this._fld7, array, width, height);
        }
        else if (!this._mth2b(mth1b, this._fld9, this._fld6, this._fld7)) {
            return false;
        }
        mth1b.flush();
        System.gc();
        return true;
    }
    
    Image _mth1b(final String s) {
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
    
    boolean _mth2b(final Image image, final int[] array, final int n, final int n2) {
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
    
    Color _mth3b(final String s, final Color color) {
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
        this._fld1f = 1;
        while (this.getParameter("text" + String.valueOf(this._fld1f)) != null) {
            ++this._fld1f;
        }
        --this._fld1f;
        if (this._fld1f == 0) {
            this._fld1f = 1;
        }
        this._fld4e = new Font[this._fld1f];
        this._fld5e = new String[this._fld1f];
        this._fld6e = new int[this._fld1f];
        this._fld7e = new int[this._fld1f];
        for (int i = 1; i < this._fld1f + 1; ++i) {
            final String parameter = this.getParameter("text" + i);
            if (parameter == null) {
                this._fld5e[i - 1] = "DS Effects";
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
            final String parameter5 = this.getParameter("textx" + i);
            if (parameter5 == null) {
                this._fld6e[i - 1] = -10000;
            }
            else {
                this._fld6e[i - 1] = Integer.valueOf(parameter5);
            }
            final String parameter6 = this.getParameter("texty" + i);
            if (parameter6 == null) {
                this._fld7e[i - 1] = -10000;
            }
            else {
                this._fld7e[i - 1] = Integer.valueOf(parameter6);
            }
        }
        this._fld9e = this._mth3b("bgcolor", new Color(0)).getRGB();
        String parameter7 = this.getParameter("speed");
        if (parameter7 == null) {
            parameter7 = "2";
        }
        this._fld7f = Integer.valueOf(parameter7);
        this._fld7f = ((this._fld7f >= 1) ? ((this._fld7f <= 8) ? this._fld7f : 8) : 1);
        String parameter8 = this.getParameter("pause");
        if (parameter8 == null) {
            parameter8 = "1";
        }
        this._fld8f = Integer.valueOf(parameter8);
        this._fld8f = ((this._fld8f >= 0) ? ((this._fld8f <= 8) ? this._fld8f : 8) : 0);
        this._fld8f *= 50;
        String parameter9 = this.getParameter("plasmasize");
        if (parameter9 == null) {
            parameter9 = "1";
        }
        this._fld2h = Integer.valueOf(parameter9);
        this._fld2h = ((this._fld2h >= 1) ? ((this._fld2h <= 4) ? this._fld2h : 4) : 1);
        this._fld2h *= 512;
        String parameter10 = this.getParameter("plasma1");
        if (parameter10 == null) {
            parameter10 = "8";
        }
        this._fld3h = Integer.valueOf(parameter10);
        this._fld3h = ((this._fld3h >= -8) ? ((this._fld3h <= 8) ? this._fld3h : 8) : -8);
        String parameter11 = this.getParameter("plasma2");
        if (parameter11 == null) {
            parameter11 = "-2";
        }
        this._fld4h = Integer.valueOf(parameter11);
        this._fld4h = ((this._fld4h >= -8) ? ((this._fld4h <= 8) ? this._fld4h : 8) : -8);
        String parameter12 = this.getParameter("plasma3");
        if (parameter12 == null) {
            parameter12 = "4";
        }
        this._fld5h = Integer.valueOf(parameter12);
        this._fld5h = ((this._fld5h >= -8) ? ((this._fld5h <= 8) ? this._fld5h : 8) : -8);
        String parameter13 = this.getParameter("plasma4");
        if (parameter13 == null) {
            parameter13 = "4";
        }
        this._fld6h = Integer.valueOf(parameter13);
        this._fld6h = ((this._fld6h >= -8) ? ((this._fld6h <= 8) ? this._fld6h : 8) : -8);
        final String parameter14 = this.getParameter("interactive");
        if (parameter14 == null) {
            this._fld1e = 1;
        }
        else if (parameter14.equalsIgnoreCase("IN")) {
            this._fld1e = 0;
        }
        else if (parameter14.equalsIgnoreCase("OUT")) {
            this._fld1e = 1;
        }
        else {
            this._fld1e = 2;
        }
        this._fld8h = new URL[this._fld1f];
        this._fld9h = new String[this._fld1f];
        this._fld0i = new String[this._fld1f];
        for (int j = 1; j <= this._fld1f; ++j) {
            this._fld0i[j - 1] = this._fld5b;
        }
        final String parameter15 = this.getParameter("regkey");
        if (parameter15 != null) {
            this._fld7h = parameter15;
            for (int k = 1; k <= this._fld1f; ++k) {
                final String parameter16 = this.getParameter("reglink" + k);
                if (parameter16 != null) {
                    try {
                        this._fld8h[k - 1] = new URL("http://" + parameter16);
                    }
                    catch (MalformedURLException ex) {
                        this._fld8h[k - 1] = null;
                    }
                    final String parameter17 = this.getParameter("regtarget" + k);
                    if (parameter17 != null) {
                        this._fld9h[k - 1] = parameter17;
                    }
                }
                final String parameter18 = this.getParameter("regstatusmsg" + k);
                if (parameter18 != null) {
                    this._fld0i[k - 1] = parameter18;
                }
            }
        }
    }
    
    void _mth5b() {
        this._fld2f = 0;
        final int n = this._fld6 * this._fld7;
        this._fld8e = new int[n];
        this._fld0f = new int[n];
        this._mth8b();
        if (!this._fld3e) {
            this._fld9 = new int[n];
            for (int i = 0; i < n; ++i) {
                this._fld9[i] = this._fld9e;
            }
        }
        this._fld8f *= this._fld7f;
        final int n2 = this._fld8f << 2;
        this._fld5f = (this._fld8f + 256 << 1) + n2;
        this._fld6f = this._fld5f >> 1;
        this._fld3f = new int[this._fld5f];
        for (int j = 0; j < this._fld8f; ++j) {
            this._fld3f[j] = 0;
        }
        for (int k = this._fld8f; k < 256 + this._fld8f; ++k) {
            this._fld3f[k] = k - this._fld8f;
        }
        for (int l = 0; l < n2; ++l) {
            this._fld3f[this._fld8f + 256 + l] = 255;
        }
        for (int n3 = 0; n3 < 256; ++n3) {
            this._fld3f[n2 + this._fld8f + 256 + n3] = 256 - n3;
        }
        for (int n4 = 0; n4 < this._fld8f; ++n4) {
            this._fld3f[n2 + this._fld8f + 256 + 256 + n4] = 0;
        }
        this._fld6g = 256;
        this._fld0g = new int[this._fld6g];
        this._fld1g = new int[this._fld6g];
        this._fld2g = new int[this._fld6g];
        this._fld3g = new int[this._fld6g];
        this._fld4g = new int[this._fld6g];
        this._fld5g = new int[this._fld6g];
        this._mth1c();
        this._fld9f = 0;
        if (this._fld1e == 2) {
            for (int n5 = 0; n5 < this._fld6g; ++n5) {
                this._fld0g[n5] = this._fld3g[n5];
                this._fld1g[n5] = this._fld4g[n5];
                this._fld2g[n5] = this._fld5g[n5];
            }
        }
        else {
            for (int n6 = 0; n6 < this._fld6g; ++n6) {
                this._fld0g[n6] = 0;
                this._fld1g[n6] = 0;
                this._fld2g[n6] = 0;
            }
        }
        this.tr = new int[this._fld6 * this._fld7];
        this.tg = new int[this._fld6 * this._fld7];
        this.tb = new int[this._fld6 * this._fld7];
        for (int n7 = 0; n7 < this._fld6 * this._fld7; ++n7) {
            final int n8 = this._fld9[n7];
            this.tr[n7] = (n8 >> 16 & 0xFF);
            this.tg[n7] = (n8 >> 8 & 0xFF);
            this.tb[n7] = (n8 & 0xFF);
        }
        this._fld7g = new int[this._fld2h];
        this._fld8g = 0;
        this._fld9g = 0;
        this._fld0h = 0;
        this._fld1h = 0;
        for (int n9 = 0; n9 < this._fld2h; ++n9) {
            this._fld7g[n9] = (int)((Math.sin(n9 * 3.141592653589793 / (this._fld2h >> 1)) + 1.0) * 31.0);
        }
    }
    
    void _mth6b() {
        if (this._fld4f > this._fld6f) {
            this._fld4f -= this._fld7f;
            if (this._fld4f < this._fld6f) {
                this._fld4f = this._fld6f;
            }
        }
        else {
            this._fld4f += this._fld7f;
            if (this._fld4f > this._fld6f) {
                this._fld4f = this._fld6f;
            }
        }
    }
    
    void _mth7b() {
        if (this._fld1e == 0) {
            if (this._fld2e) {
                this._fld4f += this._fld7f;
            }
            else {
                this._mth6b();
            }
        }
        else if (this._fld1e == 1) {
            if (this._fld2e) {
                this._mth6b();
            }
            else {
                this._fld4f += this._fld7f;
            }
        }
        else {
            this._fld4f += this._fld7f;
        }
        if (this._fld4f >= this._fld5f) {
            this._fld4f = 0;
            this._fld2f = (this._fld2f + 1) % this._fld1f;
            this._mth8b();
        }
        this._mth3c(this._fld3f[this._fld4f], this._fld0g, this._fld1g, this._fld2g, this._fld3g, this._fld4g, this._fld5g);
        if (!this._fld3e) {
            this._mth0c();
        }
        else {
            this._mth9b();
        }
        for (int i = 0; i < this._fld6 * this._fld7; ++i) {
            if (this._fld8e[i] == 255) {
                this._fld0b[i] = this._fld0f[i];
            }
            else {
                this._fld0b[i] = this._fld9[i];
            }
        }
        this._fld2b = this.createImage(this._fld1b);
    }
    
    void _mth8b() {
        new MemoryImageSource(this._fld6, this._fld7, this._fld8e, 0, this._fld6);
        final Image image = this.createImage(this._fld6, this._fld7);
        final Graphics graphics = image.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this._fld6, this._fld7);
        graphics.setColor(Color.white);
        graphics.setFont(this._fld4e[this._fld2f]);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this._fld4e[this._fld2f]);
        final int stringWidth = fontMetrics.stringWidth(this._fld5e[this._fld2f]);
        final int height = fontMetrics.getHeight();
        final int n = (height >> 1) + (height >> 3);
        if (this._fld6e[this._fld2f] == -10000) {
            this._fld6e[this._fld2f] = (this._fld6 >> 1) - (stringWidth >> 1);
        }
        if (this._fld7e[this._fld2f] == -10000) {
            this._fld7e[this._fld2f] = (this._fld7 >> 1) - (n >> 1);
        }
        graphics.drawString(this._fld5e[this._fld2f], this._fld6e[this._fld2f], this._fld7e[this._fld2f] + n);
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this._fld6, this._fld7, this._fld8e, 0, this._fld6);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        for (int i = 0; i < this._fld6 * this._fld7; ++i) {
            if ((this._fld8e[i] & 0xFF) == 0xFF) {
                this._fld8e[i] = 255;
            }
            else {
                this._fld8e[i] = 0;
            }
        }
    }
    
    void _mth9b() {
        this._fld8g = (this._fld8g + this._fld3h + this._fld2h) % this._fld2h;
        this._fld9g = (this._fld9g + this._fld4h + this._fld2h) % this._fld2h;
        this._fld0h = (this._fld0h + this._fld5h + this._fld2h) % this._fld2h;
        this._fld1h = (this._fld1h + this._fld6h + this._fld2h) % this._fld2h;
        int n = 0;
        for (int i = 0; i < this._fld7; ++i) {
            for (int j = 0; j < this._fld6; ++j) {
                final int n2 = this._fld7g[(this._fld8g + (i << 1)) % this._fld2h] + this._fld7g[(this._fld9g + (j << 2)) % this._fld2h] + this._fld7g[(this._fld0h + (j << 1)) % this._fld2h] + this._fld7g[(this._fld1h + (i << 2)) % this._fld2h];
                if (n2 == 0) {
                    this._fld0f[n] = this._fld9[n];
                }
                else {
                    final int n3 = this.tr[n] + this._fld0g[n2];
                    final int n4 = this.tg[n] + this._fld1g[n2];
                    final int n5 = this.tb[n] + this._fld2g[n2];
                    this._fld0f[n] = (0xFF000000 | ((n3 <= 255) ? n3 : 255) << 16 | ((n4 <= 255) ? n4 : 255) << 8 | ((n5 <= 255) ? n5 : 255));
                }
                ++n;
            }
        }
    }
    
    void _mth0c() {
        this._fld8g = (this._fld8g + this._fld3h + this._fld2h) % this._fld2h;
        this._fld9g = (this._fld9g + this._fld4h + this._fld2h) % this._fld2h;
        this._fld0h = (this._fld0h + this._fld5h + this._fld2h) % this._fld2h;
        this._fld1h = (this._fld1h + this._fld6h + this._fld2h) % this._fld2h;
        int n = 0;
        for (int i = 0; i < this._fld7; ++i) {
            for (int j = 0; j < this._fld6; ++j) {
                final int n2 = this._fld7g[(this._fld8g + (i << 1)) % this._fld2h] + this._fld7g[(this._fld9g + (j << 2)) % this._fld2h] + this._fld7g[(this._fld0h + (j << 1)) % this._fld2h] + this._fld7g[(this._fld1h + (i << 2)) % this._fld2h];
                this._fld0f[n] = (0xFF000000 | this._fld0g[n2] << 16 | this._fld1g[n2] << 8 | this._fld2g[n2]);
                ++n;
            }
        }
    }
    
    void _mth1c() {
        this._mth2c(0, 64, 0, 0, 0, 0, 0, 127);
        this._mth2c(64, 96, 0, 0, 0, 127, 127, 127);
        this._mth2c(96, 128, 0, 0, 127, 127, 127, 0);
        this._mth2c(128, 160, 0, 127, 127, 127, 0, 0);
        this._mth2c(160, 192, 127, 127, 127, 0, 0, 0);
        this._mth2c(192, 255, 127, 0, 0, 0, 0, 0);
    }
    
    void _mth2c(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        final int n9 = n2 - n;
        final int n10 = n4 - n3;
        final int n11 = n6 - n5;
        final int n12 = n8 - n7;
        for (int i = 0; i < n9; ++i) {
            this._fld3g[n + i] = n3 + i * n10 / n9;
            this._fld4g[n + i] = n5 + i * n11 / n9;
            this._fld5g[n + i] = n7 + i * n12 / n9;
        }
    }
    
    void _mth3c(final int n, final int[] array, final int[] array2, final int[] array3, final int[] array4, final int[] array5, final int[] array6) {
        for (int i = 0; i < this._fld6g; ++i) {
            array[i] = array4[i * n >> 8];
            array2[i] = array5[i * n >> 8];
            array3[i] = array6[i * n >> 8];
        }
    }
    
    void _mth4c() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this._fld7h.length() > 9) {
            final int n = this._fld7h.length() - 9;
            final int n2 = n + 9;
            this._fld3i = new byte[n];
            this._fld7h.getBytes(1, n + 1, this._fld3i, 0);
            this._fld4i = new byte[n2];
            this._fld7h.getBytes(0, n2, this._fld4i, 0);
            int n3 = n % 7;
            final int n4 = n % 3;
            for (int i = 0; i < n; ++i) {
                final byte b = this._fld3i[i];
                final byte b2 = (byte)(b + n3);
                if (b >= 48 && b <= 57) {
                    this._fld3i[i] = ((b2 <= 57) ? b2 : ((byte)(b2 - 10)));
                }
                else if (b >= 65 && b <= 90) {
                    this._fld3i[i] = ((b2 <= 90) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b >= 97 && b <= 122) {
                    this._fld3i[i] = ((b2 <= 122) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b == 42) {
                    this._fld3i[i] = 45;
                }
                else if (b == 45) {
                    this._fld3i[i] = 46;
                }
                n3 = (n3 + n4) % 7;
            }
            int n5 = n % 7;
            final int n6 = n % 3;
            for (int j = 0; j < n; ++j) {
                final byte b3 = this._fld3i[j];
                final byte b4 = (byte)(b3 - n5);
                if (b3 >= 48 && b3 <= 57) {
                    this._fld4i[j + 1] = ((b4 >= 48) ? b4 : ((byte)(b4 + 10)));
                }
                else if (b3 >= 65 && b3 <= 90) {
                    this._fld4i[j + 1] = ((b4 >= 65) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 >= 97 && b3 <= 122) {
                    this._fld4i[j + 1] = ((b4 >= 97) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 == 45) {
                    this._fld4i[j + 1] = 42;
                }
                else if (b3 == 46) {
                    this._fld4i[j + 1] = 45;
                }
                n5 = (n5 + n6) % 7;
            }
            byte[] array;
            if (n > 4) {
                array = new byte[n - 4];
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld3i[k + 4];
                }
            }
            else {
                array = new byte[] { 0 };
            }
            if (this._fld4i[0] == this._fld4i[n >> 1] && this._fld4i[1 + n] == this._fld4i[1] && this._fld4i[1 + n + 1] == this._fld4i[n >> 1] && this._fld4i[1 + n + 2] == (byte)(97 + n5) && this._fld4i[1 + n + 3] == 45 && this._fld4i[1 + n + 4] == (byte)(122 - n6) && this._fld4i[1 + n + 5] == (byte)(110 + n5) && this._fld4i[1 + n + 6] == this._fld4i[1] && this._fld4i[1 + n + 7] == this._fld4i[n] && (host.equalsIgnoreCase(new String(this._fld3i, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this._fld2i = true;
            }
        }
        try {
            this._fld1i = new URL("http://" + this._fld9b);
        }
        catch (MalformedURLException ex) {
            this._fld1i = null;
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld2i) {
            this.getAppletContext().showDocument(this._fld1i, "_blank");
        }
        else if (this._fld8h[this._fld2f] != null) {
            if (this._fld9h[this._fld2f] != null) {
                this.getAppletContext().showDocument(this._fld8h[this._fld2f], this._fld9h[this._fld2f]);
            }
            else {
                this.getAppletContext().showDocument(this._fld8h[this._fld2f]);
            }
        }
        return true;
    }
    
    void _mth5c() {
        int fld2d = 0;
        do {
            ++fld2d;
        } while (this.getParameter("overtext" + fld2d) != null);
        if (--fld2d > 0) {
            this._fld2c = true;
            this._fld2d = fld2d;
            this._fld5i = new String[this._fld2d];
            this._fld6i = new Color[this._fld2d];
            this._fld7i = new Color[this._fld2d];
            this._fld8i = new Font[this._fld2d];
            this._fld9i = new FontMetrics[this._fld2d];
            this._fld0j = new String[this._fld2d];
            this._fld1j = new int[this._fld2d];
            this._fld2j = new int[this._fld2d];
            for (int i = 0; i < this._fld2d; ++i) {
                this._fld5i[i] = this.getParameter("overtext" + String.valueOf(i + 1));
                this._fld6i[i] = this._mth3b("overtextcol" + String.valueOf(i + 1), new Color(16777215));
                this._fld7i[i] = this._mth3b("overtextcols" + String.valueOf(i + 1), new Color(0));
                final String parameter = this.getParameter("overtexty" + String.valueOf(i + 1));
                if (parameter == null) {
                    this._fld2j[i] = 10;
                }
                else {
                    this._fld2j[i] = Integer.parseInt(parameter);
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
                this._fld8i[i] = new Font(parameter2, n, int1);
                this._fld9i[i] = this._fld5c.getFontMetrics(this._fld8i[i]);
                this._fld0j[i] = this.getParameter("overTextType" + String.valueOf(i + 1));
                if (this._fld0j[i] == null) {
                    this._fld0j[i] = "scrollleft";
                }
                final String parameter5 = this.getParameter("overtextspeed" + String.valueOf(i + 1));
                if (parameter5 == null) {
                    this._fld1j[i] = 2;
                }
                else {
                    this._fld1j[i] = Integer.valueOf(parameter5);
                    if (this._fld1j[i] < 1 || this._fld1j[i] > 4) {
                        this._fld1j[i] = 2;
                    }
                }
            }
            this._mth6c();
        }
    }
    
    void _mth6c() {
        this._fld5d = this._fld9i[this._fld1d].stringWidth(this._fld5i[this._fld1d]);
        this._fld6d = this._fld9i[this._fld1d].getHeight();
        if (this._fld0j[this._fld1d].equalsIgnoreCase("scrolldown")) {
            this._fld3d = this._fld6 - this._fld5d >> 1;
            this._fld4d = 0;
            return;
        }
        if (this._fld0j[this._fld1d].equalsIgnoreCase("scrollup")) {
            this._fld3d = this._fld6 - this._fld5d >> 1;
            this._fld4d = this._fld7 + this._fld6d;
            return;
        }
        if (this._fld0j[this._fld1d].equalsIgnoreCase("scrollright")) {
            this._fld3d = -this._fld5d;
            this._fld4d = this._fld2j[this._fld1d] + (this._fld6d >> 1) + (this._fld6d >> 3);
            return;
        }
        this._fld3d = this._fld6;
        this._fld4d = this._fld2j[this._fld1d] + (this._fld6d >> 1) + (this._fld6d >> 3);
    }
    
    void _mth7c(final Graphics graphics) {
        graphics.setFont(this._fld8i[this._fld1d]);
        graphics.setColor(this._fld7i[this._fld1d]);
        graphics.drawString(this._fld5i[this._fld1d], this._fld3d + 1, this._fld4d + 1);
        graphics.setColor(this._fld6i[this._fld1d]);
        graphics.drawString(this._fld5i[this._fld1d], this._fld3d, this._fld4d);
        if (this._fld0j[this._fld1d].equalsIgnoreCase("scrolldown")) {
            this._fld4d += this._fld1j[this._fld1d];
        }
        else if (this._fld0j[this._fld1d].equalsIgnoreCase("scrollup")) {
            this._fld4d -= this._fld1j[this._fld1d];
        }
        else if (this._fld0j[this._fld1d].equalsIgnoreCase("scrollright")) {
            this._fld3d += this._fld1j[this._fld1d];
        }
        else {
            this._fld3d -= this._fld1j[this._fld1d];
        }
        if (this._fld4d > this._fld7 + this._fld6d || this._fld4d < -this._fld6d || this._fld3d > this._fld6 || this._fld3d < -this._fld5d) {
            ++this._fld1d;
            if (this._fld1d >= this._fld2d) {
                this._fld1d = 0;
            }
            this._mth6c();
        }
    }
    
    void _mth8c() {
        final String parameter = this.getParameter("OverImage");
        if (parameter != null) {
            this._fld6c = this._mth1b(parameter);
        }
        if (this._fld6c != null) {
            this._fld3c = true;
            this._fld9c = this._fld6c.getWidth(this);
            this._fld0d = this._fld6c.getHeight(this);
            final String parameter2 = this.getParameter("OverImageX");
            if (parameter2 == null) {
                this._fld7c = (this._fld6 >> 1) - (this._fld9c >> 1);
            }
            else {
                this._fld7c = Integer.valueOf(parameter2);
            }
            final String parameter3 = this.getParameter("OverImageY");
            if (parameter3 == null) {
                this._fld8c = (this._fld7 >> 1) - (this._fld0d >> 1);
                return;
            }
            this._fld8c = Integer.valueOf(parameter3);
        }
    }
    
    public DS_PlasmaText() {
        this._fld5 = new Font("Helvetica", 1, 12);
        this._fld5b = "Applet by Dario Sciacca";
        this._fld6b = "dario@dseffects.com";
        this._fld9b = "www.dseffects.com";
        this._fld0c = "Don't remove Dario Sciacca's credits line";
        this._fld1c = this._fld5b + " (" + this._fld9b + ")";
        this._fld2c = false;
        this._fld3c = false;
        this._fld0e = "PlasmaText started";
        this._fld2e = false;
        this._fld3e = true;
        this._fld7h = "";
        this._fld2i = false;
    }
}
