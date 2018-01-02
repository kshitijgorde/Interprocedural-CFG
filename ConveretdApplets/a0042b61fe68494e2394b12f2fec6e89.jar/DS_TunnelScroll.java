import java.awt.image.ImageProducer;
import java.net.MalformedURLException;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.PixelGrabber;
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

public class DS_TunnelScroll extends Applet implements Runnable
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
    String _fld2d;
    boolean _fld3d;
    boolean _fld4d;
    boolean _fld5d;
    Font _fld6d;
    String _fld7d;
    int _fld8d;
    int[] _fld9d;
    int tW;
    int tH;
    int _fld0e;
    int _fld1e;
    int _fld2e;
    int _fld3e;
    int _fld4e;
    int _fld5e;
    boolean _fld6e;
    int _fld7e;
    int[] _fld8e;
    int[] _fld9e;
    int[] _fld0f;
    int[] _fld1f;
    int[] _fld2f;
    String _fld3f;
    URL _fld4f;
    String _fld5f;
    String _fld6f;
    URL _fld7f;
    boolean _fld8f;
    byte[] _fld9f;
    byte[] _fld0g;
    String _fld1g;
    Color _fld2g;
    Color _fld3g;
    Font _fld4g;
    FontMetrics _fld5g;
    int _fld6g;
    int _fld7g;
    
    public String getAppletInfo() {
        return "DS TunnelScroll v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
    }
    
    public void init() {
        this._mth3();
        this.showStatus("Please wait ...");
        this._fld5b = this.getFontMetrics(this._fld3).stringWidth(this._fld6b);
        this._fld4 = this.size().width;
        this._fld5 = this.size().height;
        this._mth4();
        this._fld7 = new int[this._fld4 * this._fld5];
        this._fld8 = new MemoryImageSource(this._fld4, this._fld5, this._fld7, 0, this._fld4);
        this._fld1c = this.createImage(this._fld4, this._fld5);
        this._fld2c = this._fld1c.getGraphics();
        this._mth1c();
        this._mth3c();
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
        this._mth0c();
        if (!this._mth7()) {
            this._fld5d = false;
        }
        this._mth5();
        this._mth2b();
        if (this._fld0d == -16777216) {
            this._fld1 = 1;
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
        this.showStatus(this._fld2d);
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
        final int n = this._fld4 >> 1;
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
                this._mth2c(this._fld2c);
            }
            else if (this._fld4b == 2) {
                this._fld2c.drawImage(this._fld9, 0, 0, this);
                this._fld2c.drawImage(this._fld3c, this._fld4c, this._fld5c, this);
            }
            else {
                this._fld2c.drawImage(this._fld9, 0, 0, this);
                this._mth2c(this._fld2c);
                this._fld2c.drawImage(this._fld3c, this._fld4c, this._fld5c, this);
            }
        }
        if (this._fld4d && !this._fld8f) {
            this._fld2c.setColor(Color.white);
            this._fld2c.drawLine(n - 64, n2 - 8, n + 64, n2 - 8);
            this._fld2c.drawLine(n - 64, n2 + 8, n + 64, n2 + 8);
            this._fld2c.drawLine(n - 64, n2 - 8, n - 64, n2 + 8);
            this._fld2c.drawLine(n + 64, n2 - 8, n + 64, n2 + 8);
            this._fld2c.setColor(Color.blue);
            this._fld2c.fillRect(n - 63, n2 - 7, 127, 15);
            this._fld2c.setFont(this._fld3);
            this._fld2c.setColor(Color.yellow);
            this._fld2c.drawString(this._fld6b, n - (this._fld5b >> 1), n2 + 5);
        }
        graphics.drawImage(this._fld1c, 0, 0, this);
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this._fld6f);
        return this._fld4d = true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this._fld4d = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int n, final int fld1d) {
        if (this._fld3d) {
            if (this._fld1d < fld1d) {
                this._fld0e += this._fld5e;
            }
            else if (this._fld1d > fld1d) {
                this._fld0e -= this._fld5e;
            }
            this._fld1d = fld1d;
        }
        return this._fld4d = true;
    }
    
    void _mth3() {
        final String parameter = this.getParameter("credits");
        if (parameter != null) {
            if (parameter.equals(this._fld8b)) {
                this._fld8c = 1;
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
    
    void _mth4() {
        this._fld9c = 1;
        for (int i = 0; i < 11; ++i) {
            if (this._fld3b.charAt(i) == this._fld2b.charAt(i) || this._fld8c == 0) {
                while (true) {
                    this.showStatus(this._fld7b);
                }
            }
            else {}
        }
        this._fld8f = false;
    }
    
    void _mth5() {
        if (this._fld8c == 0 || this._fld9c == 0) {
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
            this._fld0d = -16777216;
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
        final String parameter = this.getParameter("image");
        if (parameter == null) {
            return false;
        }
        final Image mth9 = this._mth9(parameter);
        if (mth9 == null) {
            this.showStatus("Error loading image ");
            return false;
        }
        final int width = mth9.getWidth(this);
        final int height = mth9.getHeight(this);
        this._fld6 = new int[this._fld4 * this._fld5];
        if (width != this._fld4 || height != this._fld5) {
            final int[] array = new int[width * height];
            if (!this._mth8(mth9, array, width, height)) {
                return false;
            }
            this._fld6 = this._mth6(this._fld6, this._fld4, this._fld5, array, width, height);
        }
        else if (!this._mth8(mth9, this._fld6, this._fld4, this._fld5)) {
            return false;
        }
        mth9.flush();
        System.gc();
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
        final String parameter = this.getParameter("text");
        if (parameter == null) {
            this._fld7d = "Applet by Dario Sciacca";
        }
        else {
            this._fld7d = parameter;
        }
        this._fld3e = this._mth0b("textcolor", new Color(16711680)).getRGB();
        this._fld4e = this._mth0b("bgcolor", new Color(0)).getRGB();
        String parameter2 = this.getParameter("textfont");
        if (parameter2 == null) {
            parameter2 = "Helvetica";
        }
        final String parameter3 = this.getParameter("textstyle");
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
        final String parameter4 = this.getParameter("textsize");
        int int1;
        if (parameter4 == null) {
            int1 = 64;
        }
        else {
            int1 = Integer.parseInt(parameter4);
        }
        this._fld6d = new Font(parameter2, n, int1);
        String parameter5 = this.getParameter("speed");
        if (parameter5 == null) {
            parameter5 = "2";
        }
        this._fld5e = Integer.valueOf(parameter5);
        this._fld5e = ((this._fld5e >= 1) ? ((this._fld5e <= 4) ? this._fld5e : 4) : 1);
        String parameter6 = this.getParameter("pause");
        if (parameter6 == null) {
            parameter6 = "50";
        }
        this._fld1e = Integer.valueOf(parameter6);
        this._fld1e = ((this._fld1e >= 0) ? ((this._fld1e <= 1000) ? this._fld1e : 1000) : 0);
        final String parameter7 = this.getParameter("transparency");
        if (parameter7 == null) {
            this._fld6e = true;
        }
        else if (parameter7.equalsIgnoreCase("NO")) {
            this._fld6e = false;
        }
        else {
            this._fld6e = true;
        }
        final String parameter8 = this.getParameter("interactive");
        if (parameter8 == null) {
            this._fld3d = true;
        }
        else if (parameter8.equalsIgnoreCase("NO")) {
            this._fld3d = false;
        }
        else {
            this._fld3d = true;
        }
        this._fld6f = this._fld2b;
        final String parameter9 = this.getParameter("regkey");
        if (parameter9 != null) {
            this._fld3f = parameter9;
            final String parameter10 = this.getParameter("reglink");
            if (parameter10 != null) {
                try {
                    this._fld4f = new URL("http://" + parameter10);
                }
                catch (MalformedURLException ex) {
                    this._fld4f = null;
                }
                final String parameter11 = this.getParameter("regtarget");
                if (parameter11 != null) {
                    this._fld5f = parameter11;
                }
            }
            final String parameter12 = this.getParameter("regstatusmsg");
            if (parameter12 != null) {
                this._fld6f = parameter12;
            }
        }
    }
    
    void _mth2b() {
        final int n = this._fld4 * this._fld5;
        this._mth4b();
        if (!this._fld5d) {
            this._fld6 = new int[n];
            for (int i = 0; i < n; ++i) {
                this._fld6[i] = this._fld4e;
            }
            this._fld6e = false;
        }
        this._fld0e = this._fld4;
        this._fld2e = this.tW + this._fld1e;
        this._fld9e = new int[n];
        this._mth6b();
        if (this._fld6e) {
            this._fld0f = new int[n];
            this._fld1f = new int[n];
            this._fld2f = new int[n];
            for (int j = 0; j < n; ++j) {
                final int n2 = this._fld6[j];
                this._fld0f[j] = (n2 >> 16 & 0xFF) >> 1;
                this._fld1f[j] = (n2 >> 8 & 0xFF) >> 1;
                this._fld2f[j] = (n2 & 0xFF) >> 1;
            }
        }
    }
    
    void _mth3b() {
        if (!this._fld3d) {
            this._fld0e -= this._fld5e;
        }
        else if (this._fld3d && !this._fld4d) {
            this._fld0e -= this._fld5e;
        }
        if (this._fld0e < -this._fld2e) {
            this._fld0e = this._fld4;
        }
        for (int n = this._fld4 * this._fld5, i = 0; i < n; ++i) {
            this._fld9e[i] = 0;
        }
        this._mth5b(this._fld9e, this._fld0e, this._fld8d, this._fld4, this._fld5, this._fld9d, 0, 0, this.tW - 1, this.tH - 1, this.tW, this.tH);
        this._mth7b();
        this._fld9 = this.createImage(this._fld8);
    }
    
    void _mth4b() {
        final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this._fld6d);
        this.tW = fontMetrics.stringWidth(this._fld7d);
        this.tH = fontMetrics.getHeight();
        this._fld9d = new int[this.tW * this.tH];
        new MemoryImageSource(this.tW, this.tH, this._fld9d, 0, this.tW);
        final Image image = this.createImage(this.tW, this.tH);
        final Graphics graphics = image.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this.tW, this.tH);
        graphics.setFont(this._fld6d);
        final int n = (this.tH >> 1) + (this.tH >> 3);
        graphics.setColor(Color.white);
        graphics.drawString(this._fld7d, 0, n);
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.tW, this.tH, this._fld9d, 0, this.tW);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        for (int i = 0; i < this.tW * this.tH; ++i) {
            if ((this._fld9d[i] & 0xFF) == 0xFF) {
                this._fld9d[i] = 255;
            }
            else {
                this._fld9d[i] = 0;
            }
        }
        this._fld8d = (this._fld5 >> 1) - (n >> 1);
    }
    
    void _mth5b(final int[] array, int n, int n2, final int n3, final int n4, final int[] array2, int n5, int n6, int n7, int n8, final int n9, final int n10) {
        if (n >= this._fld4 || n2 >= this._fld5) {
            return;
        }
        if (n < 0) {
            n5 += -n;
            n = 0;
            if (n5 > n7) {
                return;
            }
        }
        if (n2 < 0) {
            n6 += -n2;
            n2 = 0;
            if (n6 > n8) {
                return;
            }
        }
        if (n5 >= n9 || n6 >= n10) {
            return;
        }
        if (n7 < 0 || n8 < 0) {
            return;
        }
        if (n5 < 0) {
            n5 = 0;
        }
        if (n6 < 0) {
            n6 = 0;
        }
        if (n7 >= this._fld4) {
            n7 = n9 - 1;
        }
        if (n8 >= this._fld5) {
            n8 = n10 - 1;
        }
        int n11 = n8 - n6 + 1;
        int n12 = n7 - n5 + 1;
        if (n + n12 >= n3) {
            n12 = n3 - n;
        }
        if (n2 + n11 >= n4) {
            n11 = n4 - n2;
        }
        for (int i = 0; i < n11; ++i) {
            final int n13 = (n2 + i) * n3 + n;
            final int n14 = (n6 + i) * n9 + n5;
            for (int j = 0; j < n12; ++j) {
                array[n13 + j] = array2[n14 + j];
            }
        }
    }
    
    void _mth6b() {
        this._fld8e = new int[this._fld4 * this._fld5];
        final int n = this._fld4 >> 1;
        final int n2 = this._fld5 >> 1;
        for (int i = 0; i < this._fld5; ++i) {
            for (int j = 0; j < n; ++j) {
                final int n3 = j - n;
                final int n4 = i - n2;
                double atan2;
                if (n3 != 0 || n4 != 0) {
                    atan2 = Math.atan2(n3, n4);
                }
                else {
                    atan2 = 1.0;
                }
                final double n5 = atan2 / 1.5707963267948966 * n2;
                double sqrt = Math.sqrt(n3 * n3 + n4 * n4);
                if (sqrt == 0.0) {
                    sqrt = 1.0;
                }
                double n6 = this._fld4 / sqrt * this._fld7e;
                if (n6 > this._fld5) {
                    n6 /= 5.0;
                }
                this._fld8e[this._fld4 * i + j] = (int)n5 * this._fld4 + (int)n6;
                this._fld8e[this._fld4 * i + (this._fld4 - 1 - j)] = (int)n5 * this._fld4 + (int)n6;
            }
        }
    }
    
    void _mth7b() {
        final int n = this._fld4 * this._fld5;
        if (this._fld6e) {
            final int n2 = (this._fld3e >> 16 & 0xFF) >> 1;
            final int n3 = (this._fld3e >> 8 & 0xFF) >> 1;
            final int n4 = (this._fld3e & 0xFF) >> 1;
            for (int i = 0; i < n; ++i) {
                if (this._fld9e[(this._fld8e[i] + n) % n] != 0) {
                    this._fld7[i] = (0xFF000000 | n2 + this._fld0f[i] << 16 | n3 + this._fld1f[i] << 8 | n4 + this._fld2f[i]);
                }
                else {
                    this._fld7[i] = this._fld6[i];
                }
            }
        }
        else {
            for (int j = 0; j < n; ++j) {
                if (this._fld9e[(this._fld8e[j] + n) % n] != 0) {
                    this._fld7[j] = this._fld3e;
                }
                else {
                    this._fld7[j] = this._fld6[j];
                }
            }
        }
        this._mth9b(this._fld4 >> 1, this._fld5 >> 1, 24);
    }
    
    void _mth8b(int n, final int n2, int n3) {
        if (n2 < 0 || n2 > this._fld5) {
            return;
        }
        if (n > n3) {
            final int n4 = n3;
            n3 = n;
            n = n4;
        }
        if (n3 < 0 || n > this._fld4 - 1) {
            return;
        }
        if (n < 0) {
            n = 0;
        }
        if (n3 > this._fld4 - 1) {
            n3 = this._fld4 - 1;
        }
        final int n5 = n2 * this._fld4 + n;
        for (int n6 = n3 - n + 1, i = 0; i < n6; ++i) {
            this._fld7[i + n5] = this._fld6[i + n5];
        }
    }
    
    void _mth9b(final int n, final int n2, final int n3) {
        if (n3 < 1) {
            this._fld7[n2 * this._fld4 + n] = this._fld6[n2 * this._fld4 + n];
            return;
        }
        final int n4 = n3 * n3;
        this._mth8b(n - n3, n2, n + n3);
        for (int i = 1; i < n3; ++i) {
            final int n5 = (int)Math.sqrt(n4 - i * i);
            this._mth8b(n - n5, n2 - i, n + n5);
            this._mth8b(n - n5, n2 + i, n + n5);
        }
    }
    
    void _mth0c() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this._fld3f.length() > 9) {
            final int n = this._fld3f.length() - 9;
            final int n2 = n + 9;
            this._fld9f = new byte[n];
            this._fld3f.getBytes(1, n + 1, this._fld9f, 0);
            this._fld0g = new byte[n2];
            this._fld3f.getBytes(0, n2, this._fld0g, 0);
            int n3 = n % 7;
            final int n4 = n % 3;
            for (int i = 0; i < n; ++i) {
                final byte b = this._fld9f[i];
                final byte b2 = (byte)(b + n3);
                if (b >= 48 && b <= 57) {
                    this._fld9f[i] = ((b2 <= 57) ? b2 : ((byte)(b2 - 10)));
                }
                else if (b >= 65 && b <= 90) {
                    this._fld9f[i] = ((b2 <= 90) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b >= 97 && b <= 122) {
                    this._fld9f[i] = ((b2 <= 122) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b == 42) {
                    this._fld9f[i] = 45;
                }
                else if (b == 45) {
                    this._fld9f[i] = 46;
                }
                n3 = (n3 + n4) % 7;
            }
            int n5 = n % 7;
            final int n6 = n % 3;
            for (int j = 0; j < n; ++j) {
                final byte b3 = this._fld9f[j];
                final byte b4 = (byte)(b3 - n5);
                if (b3 >= 48 && b3 <= 57) {
                    this._fld0g[j + 1] = ((b4 >= 48) ? b4 : ((byte)(b4 + 10)));
                }
                else if (b3 >= 65 && b3 <= 90) {
                    this._fld0g[j + 1] = ((b4 >= 65) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 >= 97 && b3 <= 122) {
                    this._fld0g[j + 1] = ((b4 >= 97) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 == 45) {
                    this._fld0g[j + 1] = 42;
                }
                else if (b3 == 46) {
                    this._fld0g[j + 1] = 45;
                }
                n5 = (n5 + n6) % 7;
            }
            final byte[] array = new byte[n];
            if (n > 4) {
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld9f[k + 4];
                }
            }
            if (this._fld0g[0] == this._fld0g[n >> 1] && this._fld0g[1 + n] == this._fld0g[1] && this._fld0g[1 + n + 1] == this._fld0g[n >> 1] && this._fld0g[1 + n + 2] == (byte)(97 + n5) && this._fld0g[1 + n + 3] == 45 && this._fld0g[1 + n + 4] == (byte)(122 - n6) && this._fld0g[1 + n + 5] == (byte)(110 + n5) && this._fld0g[1 + n + 6] == this._fld0g[1] && this._fld0g[1 + n + 7] == this._fld0g[n] && (host.equalsIgnoreCase(new String(this._fld9f, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this._fld8f = true;
            }
        }
        try {
            this._fld7f = new URL("http://" + this._fld6b);
        }
        catch (MalformedURLException ex) {
            this._fld7f = null;
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld8f) {
            this.getAppletContext().showDocument(this._fld7f, "_blank");
        }
        else if (this._fld4f != null) {
            if (this._fld5f != null) {
                this.getAppletContext().showDocument(this._fld4f, this._fld5f);
            }
            else {
                this.getAppletContext().showDocument(this._fld4f);
            }
        }
        return true;
    }
    
    void _mth1c() {
        this._fld1g = this.getParameter("overtext");
        if (this._fld1g != null) {
            this._fld9b = true;
            this._fld2g = this._mth0b("overtextcol", new Color(16777215));
            this._fld3g = this._mth0b("overtextcols", new Color(0));
            String parameter = this.getParameter("overTextFont");
            if (parameter == null) {
                parameter = "Helvetica";
            }
            final String parameter2 = this.getParameter("overTextStyle");
            int n;
            if (parameter2 == null) {
                n = 0;
            }
            else if (parameter2.equalsIgnoreCase("PLAIN")) {
                n = 0;
            }
            else if (parameter2.equalsIgnoreCase("BOLD")) {
                n = 1;
            }
            else if (parameter2.equalsIgnoreCase("ITALIC")) {
                n = 2;
            }
            else if (parameter2.equalsIgnoreCase("BOLD ITALIC")) {
                n = 3;
            }
            else {
                n = 0;
            }
            final String parameter3 = this.getParameter("overTextSize");
            int int1;
            if (parameter3 == null) {
                int1 = 16;
            }
            else {
                int1 = Integer.parseInt(parameter3);
            }
            this._fld4g = new Font(parameter, n, int1);
            this._fld5g = this._fld2c.getFontMetrics(this._fld4g);
            final int stringWidth = this._fld5g.stringWidth(this._fld1g);
            final int height = this._fld5g.getHeight();
            final int n2 = (height >> 1) + (height >> 3);
            final String parameter4 = this.getParameter("overtextx");
            if (parameter4 == null) {
                this._fld6g = (this._fld4 >> 1) - (stringWidth >> 1);
            }
            else {
                this._fld7g = Integer.parseInt(parameter4);
            }
            final String parameter5 = this.getParameter("overtexty");
            if (parameter5 == null) {
                this._fld7g = (this._fld5 >> 1) - (n2 >> 1);
                return;
            }
            this._fld7g = Integer.parseInt(parameter5) + n2;
        }
    }
    
    void _mth2c(final Graphics graphics) {
        graphics.setFont(this._fld4g);
        graphics.setColor(this._fld3g);
        graphics.drawString(this._fld1g, this._fld6g + 1, this._fld7g + 1);
        graphics.setColor(this._fld2g);
        graphics.drawString(this._fld1g, this._fld6g, this._fld7g);
    }
    
    void _mth3c() {
        final String parameter = this.getParameter("OverImage");
        if (parameter != null) {
            this._fld3c = this._mth9(parameter);
        }
        if (this._fld3c != null) {
            this._fld0c = true;
            this._fld6c = this._fld3c.getWidth(this);
            this._fld7c = this._fld3c.getHeight(this);
            final String parameter2 = this.getParameter("OverImageX");
            if (parameter2 == null) {
                this._fld4c = (this._fld4 >> 1) - (this._fld6c >> 1);
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
    
    public DS_TunnelScroll() {
        this._fld3 = new Font("Helvetica", 1, 12);
        this._fld2b = "Applet by Dario Sciacca";
        this._fld3b = "dario@dseffects.com";
        this._fld6b = "www.dseffects.com";
        this._fld7b = "Don't remove Dario Sciacca's credits line";
        this._fld8b = this._fld2b + " (" + this._fld6b + ")";
        this._fld9b = false;
        this._fld0c = false;
        this._fld2d = "TunnelScroll started";
        this._fld4d = false;
        this._fld5d = true;
        this._fld7e = 12;
        this._fld3f = "";
        this._fld5f = "_blank";
        this._fld6f = "Applet by Dario Sciacca";
        this._fld8f = false;
    }
}
