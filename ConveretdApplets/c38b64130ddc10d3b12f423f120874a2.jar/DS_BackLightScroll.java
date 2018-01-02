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

public class DS_BackLightScroll extends Applet implements Runnable
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
    int _fld5d;
    int _fld6d;
    double _fld7d;
    double _fld8d;
    int[] _fld9d;
    int _fld0e;
    int _fld1e;
    int _fld2e;
    dse[] _fld3e;
    int[] _fld4e;
    int[] _fld5e;
    int w2;
    int h2;
    int _fld6e;
    int _fld7e;
    Font _fld8e;
    String _fld9e;
    int _fld0f;
    int _fld1f;
    int _fld2f;
    int[] _fld3f;
    int[] _fld4f;
    int tW;
    int tH;
    int _fld5f;
    int _fld6f;
    int _fld7f;
    int _fld8f;
    boolean _fld9f;
    String _fld0g;
    URL _fld1g;
    String _fld2g;
    String _fld3g;
    URL _fld4g;
    boolean _fld5g;
    byte[] _fld6g;
    byte[] _fld7g;
    String _fld8g;
    Color _fld9g;
    Color _fld0h;
    Font _fld1h;
    FontMetrics _fld2h;
    int _fld3h;
    int _fld4h;
    
    public String getAppletInfo() {
        return "DS BackLightScroll v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
    }
    
    public void init() {
        this._mth4();
        this.showStatus("Please wait ...");
        this._fld5b = this.getFontMetrics(this._fld3).stringWidth(this._fld6b);
        this._fld4 = this.size().width;
        this._fld5 = this.size().height;
        this._mth5();
        this._fld7 = new int[this._fld4 * this._fld5];
        this._fld8 = new MemoryImageSource(this._fld4, this._fld5, this._fld7, 0, this._fld4);
        this._fld1c = this.createImage(this._fld4, this._fld5);
        this._fld2c = this._fld1c.getGraphics();
        this._mth3c();
        this._mth5c();
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
        this._mth2b();
        this._mth2c();
        if (!this._mth8()) {
            this._fld9f = false;
        }
        this._mth6();
        this._mth3b();
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
                this._mth4b();
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
                this._mth4c(this._fld2c);
            }
            else if (this._fld4b == 2) {
                this._fld2c.drawImage(this._fld9, 0, 0, this);
                this._fld2c.drawImage(this._fld3c, this._fld4c, this._fld5c, this);
            }
            else {
                this._fld2c.drawImage(this._fld9, 0, 0, this);
                this._mth4c(this._fld2c);
                this._fld2c.drawImage(this._fld3c, this._fld4c, this._fld5c, this);
            }
        }
        if (this._fld4d && !this._fld5g) {
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
    
    private final void _mth3() {
        while (true) {
            this.showStatus(this._fld7b);
        }
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this._fld3g);
        return this._fld4d = true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this._fld4d = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int fld1d, final int n) {
        if (this._fld3d) {
            if (this._fld1d < fld1d) {
                this._fld5f += this._fld6f;
            }
            else if (this._fld1d > fld1d) {
                this._fld5f -= this._fld6f;
            }
            this._fld1d = fld1d;
        }
        return this._fld4d = true;
    }
    
    void _mth4() {
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
    
    void _mth5() {
        this._fld9c = 1;
        for (int i = 0; i < 11; ++i) {
            if (this._fld3b.charAt(i) == this._fld2b.charAt(i) || this._fld8c == 0) {
                while (true) {
                    this.showStatus(this._fld7b);
                }
            }
            else {}
        }
        this._fld5g = false;
    }
    
    void _mth6() {
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
            if (!this._mth0b(mth9, array, width, height)) {
                return false;
            }
            this._fld6 = this._mth7(this._fld6, this._fld4, this._fld5, array, width, height);
        }
        else if (!this._mth0b(mth9, this._fld6, this._fld4, this._fld5)) {
            return false;
        }
        mth9.flush();
        System.gc();
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
        final String parameter = this.getParameter("text");
        if (parameter == null) {
            this._fld9e = "Applet by Dario Sciacca";
        }
        else {
            this._fld9e = parameter;
        }
        this._fld1f = this._mth1b("textcolor", new Color(16711680)).getRGB();
        this._fld2f = this._mth1b("bgcolor", new Color(0)).getRGB();
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
        this._fld8e = new Font(parameter2, n, int1);
        final String parameter5 = this.getParameter("texty");
        if (parameter5 == null) {
            this._fld0f = -10000;
        }
        else {
            this._fld0f = Integer.valueOf(parameter5);
        }
        String parameter6 = this.getParameter("speed");
        if (parameter6 == null) {
            parameter6 = "2";
        }
        this._fld6f = Integer.valueOf(parameter6);
        this._fld6f = ((this._fld6f >= 1) ? ((this._fld6f <= 4) ? this._fld6f : 4) : 1);
        String parameter7 = this.getParameter("pause");
        if (parameter7 == null) {
            parameter7 = "50";
        }
        this._fld7f = Integer.valueOf(parameter7);
        this._fld7f = ((this._fld7f >= 0) ? ((this._fld7f <= 1000) ? this._fld7f : 1000) : 0);
        String parameter8 = this.getParameter("lightsize");
        if (parameter8 == null) {
            parameter8 = "2";
        }
        this._fld5d = Integer.valueOf(parameter8);
        this._fld5d = ((this._fld5d >= 1) ? ((this._fld5d <= 3) ? this._fld5d : 3) : 1);
        String parameter9 = this.getParameter("intensity");
        if (parameter9 == null) {
            parameter9 = "2";
        }
        this._fld6d = Integer.valueOf(parameter9);
        this._fld6d = ((this._fld6d >= 1) ? ((this._fld6d <= 3) ? this._fld6d : 3) : 1);
        final String parameter10 = this.getParameter("interactive");
        if (parameter10 == null) {
            this._fld3d = true;
        }
        else if (parameter10.equalsIgnoreCase("YES")) {
            this._fld3d = true;
        }
        else {
            this._fld3d = false;
        }
        this._fld3g = this._fld2b;
        final String parameter11 = this.getParameter("regkey");
        if (parameter11 != null) {
            this._fld0g = parameter11;
            final String parameter12 = this.getParameter("reglink");
            if (parameter12 != null) {
                try {
                    this._fld1g = new URL("http://" + parameter12);
                }
                catch (MalformedURLException ex) {
                    this._fld1g = null;
                }
                final String parameter13 = this.getParameter("regtarget");
                if (parameter13 != null) {
                    this._fld2g = parameter13;
                }
            }
            final String parameter14 = this.getParameter("regstatusmsg");
            if (parameter14 != null) {
                this._fld3g = parameter14;
            }
        }
    }
    
    void _mth3b() {
        final int n = this._fld4 * this._fld5;
        if (!this._fld9f) {
            this._fld6 = new int[n];
            for (int i = 0; i < n; ++i) {
                this._fld6[i] = this._fld2f;
            }
        }
        this._mth0c();
        this._fld3f = new int[n];
        this._fld5f = this._fld4;
        this._fld8f = this.tW + this._fld7f;
        if (this._fld6d == 1) {
            this._fld7d = 0.8;
        }
        else if (this._fld6d == 2) {
            this._fld7d = 0.5;
        }
        else {
            this._fld7d = 0.2;
        }
        if (this._fld5d == 1) {
            this._fld8d = 0.9;
        }
        else if (this._fld5d == 2) {
            this._fld8d = 0.6;
        }
        else {
            this._fld8d = 0.3;
        }
        this._fld7e = Math.max(this._fld4, this._fld5);
        this._fld6e = this._fld4 + this._fld5 - 1;
        this._fld3e = new dse[this._fld6e];
        for (int j = 0; j < this._fld6e; ++j) {
            this._fld3e[j] = new dse(this._fld7e);
        }
        this._fld2e = Math.max(this._fld4, this._fld5);
        this._fld9d = new int[n];
        this._fld4e = new int[n];
        this._fld5e = new int[n];
        for (int k = 0; k < n; ++k) {
            this._fld4e[k] = -1;
            this._fld9d[k] = -16777216;
        }
        int n2 = 0;
        for (int l = this._fld5 - 2; l >= 0; --l) {
            this._mth7b(n2++, 0, 0, this._fld4 - 1, l);
        }
        for (int n3 = 0; n3 < this._fld4; ++n3) {
            this._mth7b(n2++, 0, 0, n3, this._fld5 - 1);
        }
        this._fld5e = null;
        this.w2 = this._fld4 << 1;
        this.h2 = this._fld5 << 1;
        this._fld0e = this._fld4 >> 1;
        this._fld1e = this._fld5 >> 1;
    }
    
    void _mth4b() {
        if (!this._fld3d) {
            this._fld5f -= this._fld6f;
        }
        else if (this._fld3d && !this._fld4d) {
            this._fld5f -= this._fld6f;
        }
        if (this._fld5f < -this._fld8f) {
            this._fld5f = this._fld4;
        }
        for (int n = this._fld4 * this._fld5, i = 0; i < n; ++i) {
            this._fld3f[i] = -16777216;
        }
        this._mth1c(this._fld3f, this._fld5f, this._fld0f, this._fld4, this._fld5, this._fld4f, 0, 0, this.tW - 1, this.tH - 1, this.tW, this.tH);
        this._fld0e = this._fld4 >> 1;
        this._fld1e = this._fld5 >> 1;
        this._mth9b();
        this._mth5b(this._fld9d, this._fld6);
        this._fld9 = this.createImage(this._fld8);
    }
    
    void _mth5b(final int[] array, final int[] array2) {
        for (int i = 0; i < this._fld4 * this._fld5; ++i) {
            final int n = array[i];
            final int n2 = array2[i];
            final int n3 = n >> 16 & 0xFF;
            final int n4 = n2 >> 16 & 0xFF;
            final int n5 = n >> 8 & 0xFF;
            final int n6 = n2 >> 8 & 0xFF;
            final int n7 = n & 0xFF;
            final int n8 = n2 & 0xFF;
            int n9 = n3 + n4;
            if (n9 > 255) {
                n9 = 255;
            }
            int n10 = n5 + n6;
            if (n10 > 255) {
                n10 = 255;
            }
            int n11 = n7 + n8;
            if (n11 > 255) {
                n11 = 255;
            }
            this._fld7[i] = (0xFF000000 | n9 << 16 | n10 << 8 | n11);
        }
    }
    
    int _mth6b(final int n, final int n2, final int n3, final int n4) {
        final int n5 = (n >> 16 & 0xFF) * n2 >> 8;
        final int n6 = (n3 >> 16 & 0xFF) * n4 >> 8;
        final int n7 = (n >> 8 & 0xFF) * n2 >> 8;
        final int n8 = (n3 >> 8 & 0xFF) * n4 >> 8;
        final int n9 = (n & 0xFF) * n2 >> 8;
        final int n10 = (n3 & 0xFF) * n4 >> 8;
        int n11 = n5 + n6;
        if (n11 > 255) {
            n11 = 255;
        }
        int n12 = n7 + n8;
        if (n12 > 255) {
            n12 = 255;
        }
        int n13 = n9 + n10;
        if (n13 > 255) {
            n13 = 255;
        }
        return 0xFF000000 | n11 << 16 | n12 << 8 | n13;
    }
    
    void _mth7b(final int n, final int n2, final int n3, final int n4, final int n5) {
        int n6 = n2;
        int n7 = n3;
        int n8 = n2;
        int n9 = n3;
        int abs = Math.abs(n4 - n8);
        int abs2 = Math.abs(n5 - n9);
        int n10 = -1;
        int n11;
        if (n9 > n5) {
            n11 = -1;
        }
        else {
            n11 = 1;
        }
        int n12;
        if (n8 > n4) {
            n12 = -1;
        }
        else {
            n12 = 1;
        }
        if (abs < abs2) {
            final int n13 = abs << 1;
            final int n14 = n13 - (abs2 << 1);
            int n15 = n13 - abs2;
            while (abs2-- >= 0) {
                n10 = this._mth8b(n6, n7, n, n9, n8, n9, n10);
                n6 = n8;
                n7 = n9;
                if (n15 > 0) {
                    n15 += n14;
                    n8 += n12;
                    n9 += n11;
                }
                else {
                    n15 += n13;
                    n9 += n11;
                }
            }
            return;
        }
        final int n16 = abs2 << 1;
        final int n17 = n16 - (abs << 1);
        int n18 = n16 - abs;
        while (abs-- >= 0) {
            n10 = this._mth8b(n6, n7, n, n8, n8, n9, n10);
            n6 = n8;
            n7 = n9;
            if (n18 > 0) {
                n18 += n17;
                n8 += n12;
                n9 += n11;
            }
            else {
                n18 += n16;
                n8 += n12;
            }
        }
    }
    
    int _mth8b(final int n, final int n2, final int n3, final int als, final int n4, final int n5, int n6) {
        final int n7 = n4 + n5 * this._fld4;
        if (n6 == -1 && this._fld4e[n7] == -1) {
            this._fld5e[n7] = (this._fld4e[n7] = n3);
            this._fld3e[n3].als = als;
            this._fld3e[n3].ali = n - n4;
            this._fld3e[n3].alo = n2 - n5;
            n6 = 1;
            this._fld3e[n3].alw[als] = 1;
        }
        else if (n6 == 1) {
            this._fld3e[n3].alw[als] = 1;
            if (this._fld4e[n7] == -1) {
                this._fld5e[n7] = (this._fld4e[n7] = n3);
            }
            else {
                final int[] alz = this._fld3e[this._fld5e[n7]].alz;
                ++alz[als];
                this._fld3e[this._fld5e[n7]].alw[als] = 0;
                this._fld5e[n7] = n3;
            }
        }
        else {
            this._fld3e[n3].alw[als] = 0;
        }
        this._fld3e[n3].ala[als] = n4;
        this._fld3e[n3].ale[als] = n5;
        this._fld3e[n3].alg[als] = Math.max(256 - (int)Math.pow(this._fld7d * (n4 * n4 + n5 * n5), this._fld8d), 1);
        this._fld3e[n3].alf[als] = this._fld3e[n3].alg[als] / 32;
        return n6;
    }
    
    final void _mth9b() {
        for (int i = 0; i < this._fld6e; ++i) {
            final dse dse = this._fld3e[i];
            final int als = dse.als;
            if (i > 0) {
                final int n = dse.ala[als];
                final int n2 = dse.ale[als];
                final int n3 = n + this._fld0e;
                final int n4 = n2 + this._fld1e;
                if (n3 < this._fld4 && n4 < this._fld5) {
                    final int n5 = n4 * this._fld4 + n3;
                    dse.alp[als] = this._mth6b(this._fld3e[this._fld4e[n + dse.ali + (n2 + dse.alo) * this._fld4]].alp[als - 1], 255, this._fld3f[n5], dse.alf[als]);
                    if (dse.alw[als] == 1) {
                        this._fld9d[n5] = this._mth6b(this._fld3f[n5], dse.alg[als], dse.alp[als], 255);
                    }
                }
            }
            for (int j = als + 1; j < this._fld2e; ++j) {
                final int n6 = dse.ala[j] + this._fld0e;
                final int n7 = dse.ale[j] + this._fld1e;
                if (n6 >= this._fld4 || n7 >= this._fld5) {
                    break;
                }
                final int n8 = n7 * this._fld4 + n6;
                final int n9 = this._fld3f[n8];
                dse.alp[j] = this._mth6b(dse.alp[j - 1], 255, n9, dse.alf[j]);
                if (dse.alw[j] == 1) {
                    this._fld9d[n8] = this._mth6b(n9, dse.alg[j], dse.alp[j], 255);
                }
            }
        }
        for (int k = 0; k < this._fld6e; ++k) {
            final dse dse2 = this._fld3e[k];
            final int als2 = dse2.als;
            if (k > 0) {
                final int n10 = dse2.ala[als2];
                final int n11 = dse2.ale[als2];
                final int n12 = n10 + this._fld0e;
                final int n13 = -n11 + this._fld1e;
                if (n12 < this._fld4 && n13 >= 0) {
                    final int n14 = n13 * this._fld4 + n12;
                    final int n15 = this._fld3f[n14];
                    dse2.alp[als2] = this._mth6b(this._fld3e[this._fld4e[n10 + dse2.ali + (n11 + dse2.alo) * this._fld4]].alp[als2 - 1], 255, n15, dse2.alf[als2]);
                    if (dse2.alw[als2] == 1) {
                        this._fld9d[n14] = this._mth6b(n15, dse2.alg[als2], dse2.alp[als2], 255);
                    }
                }
            }
            for (int l = als2 + 1; l < this._fld2e; ++l) {
                final int n16 = dse2.ala[l] + this._fld0e;
                final int n17 = -dse2.ale[l] + this._fld1e;
                if (n16 >= this._fld4 || n17 < 0) {
                    break;
                }
                final int n18 = n17 * this._fld4 + n16;
                final int n19 = this._fld3f[n18];
                dse2.alp[l] = this._mth6b(dse2.alp[l - 1], 255, n19, dse2.alf[l]);
                if (dse2.alz[l] == 0) {
                    this._fld9d[n18] = this._mth6b(n19, dse2.alg[l], dse2.alp[l], 255);
                }
            }
        }
        for (int n20 = 0; n20 < this._fld6e; ++n20) {
            final dse dse3 = this._fld3e[n20];
            final int als3 = dse3.als;
            if (n20 > 0) {
                final int n21 = dse3.ala[als3];
                final int n22 = dse3.ale[als3];
                final int n23 = -n21 + this._fld0e;
                final int n24 = -n22 + this._fld1e;
                final int n25 = n24 * this._fld4 + n23;
                if (n23 >= 0 && n24 >= 0) {
                    dse3.alp[als3] = this._mth6b(this._fld3e[this._fld4e[n21 + dse3.ali + (n22 + dse3.alo) * this._fld4]].alp[als3 - 1], 255, this._fld3f[n25], dse3.alf[als3]);
                    if (dse3.alw[als3] == 1) {
                        this._fld9d[n25] = this._mth6b(this._fld3f[n25], dse3.alg[als3], dse3.alp[als3], 255);
                    }
                }
            }
            for (int n26 = als3 + 1; n26 < this._fld2e; ++n26) {
                final int n27 = -dse3.ala[n26] + this._fld0e;
                final int n28 = -dse3.ale[n26] + this._fld1e;
                if (n27 < 0 || n28 < 0) {
                    break;
                }
                final int n29 = n28 * this._fld4 + n27;
                final int n30 = this._fld3f[n29];
                dse3.alp[n26] = this._mth6b(dse3.alp[n26 - 1], 255, n30, dse3.alf[n26]);
                if (dse3.alw[n26] == 1) {
                    this._fld9d[n29] = this._mth6b(n30, dse3.alg[n26], dse3.alp[n26], 255);
                }
            }
        }
        for (int n31 = 0; n31 < this._fld6e; ++n31) {
            final dse dse4 = this._fld3e[n31];
            final int als4 = dse4.als;
            if (n31 > 0) {
                final int n32 = dse4.ala[als4];
                final int n33 = dse4.ale[als4];
                final int n34 = -n32 + this._fld0e;
                final int n35 = n33 + this._fld1e;
                final int n36 = n35 * this._fld4 + n34;
                if (n34 >= 0 && n35 < this._fld5) {
                    dse4.alp[als4] = this._mth6b(this._fld3e[this._fld4e[n32 + dse4.ali + (n33 + dse4.alo) * this._fld4]].alp[als4 - 1], 255, this._fld3f[n36], dse4.alf[als4]);
                    if (dse4.alw[als4] == 1) {
                        this._fld9d[n36] = this._mth6b(this._fld3f[n36], dse4.alg[als4], dse4.alp[als4], 255);
                    }
                }
            }
            for (int n37 = als4 + 1; n37 < this._fld2e; ++n37) {
                final int n38 = -dse4.ala[n37] + this._fld0e;
                final int n39 = dse4.ale[n37] + this._fld1e;
                if (n38 < 0 || n39 >= this._fld5) {
                    break;
                }
                final int n40 = n39 * this._fld4 + n38;
                final int n41 = this._fld3f[n40];
                dse4.alp[n37] = this._mth6b(dse4.alp[n37 - 1], 255, n41, dse4.alf[n37]);
                if (dse4.alw[n37] == 1) {
                    this._fld9d[n40] = this._mth6b(n41, dse4.alg[n37], dse4.alp[n37], 255);
                }
            }
        }
    }
    
    void _mth0c() {
        final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this._fld8e);
        this.tW = fontMetrics.stringWidth(this._fld9e);
        this.tH = fontMetrics.getHeight();
        this._fld4f = new int[this.tW * this.tH];
        new MemoryImageSource(this.tW, this.tH, this._fld4f, 0, this.tW);
        final Image image = this.createImage(this.tW, this.tH);
        final Graphics graphics = image.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this.tW, this.tH);
        graphics.setFont(this._fld8e);
        final int n = (this.tH >> 1) + (this.tH >> 3);
        graphics.setColor(Color.white);
        graphics.drawString(this._fld9e, 0, n);
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.tW, this.tH, this._fld4f, 0, this.tW);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        for (int i = 0; i < this.tW * this.tH; ++i) {
            if ((this._fld4f[i] & 0xFF) == 0xFF) {
                this._fld4f[i] = 255;
            }
            else {
                this._fld4f[i] = 0;
            }
        }
        if (this._fld0f == -10000) {
            this._fld0f = (this._fld5 >> 1) - (n >> 1);
        }
    }
    
    void _mth1c(final int[] array, int n, int n2, final int n3, final int n4, final int[] array2, int n5, int n6, int n7, int n8, final int n9, final int n10) {
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
                if (array2[n14 + j] == 255) {
                    array[n13 + j] = this._fld1f;
                }
                else {
                    array[n13 + j] = -16777216;
                }
            }
        }
    }
    
    void _mth2c() {
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
            this._fld4g = new URL("http://" + this._fld6b);
        }
        catch (MalformedURLException ex) {
            this._fld4g = null;
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld5g) {
            this.getAppletContext().showDocument(this._fld4g, "_blank");
        }
        else if (this._fld1g != null) {
            if (this._fld2g != null) {
                this.getAppletContext().showDocument(this._fld1g, this._fld2g);
            }
            else {
                this.getAppletContext().showDocument(this._fld1g);
            }
        }
        return true;
    }
    
    void _mth3c() {
        this._fld8g = this.getParameter("overtext");
        if (this._fld8g != null) {
            this._fld9b = true;
            this._fld9g = this._mth1b("overtextcol", new Color(16777215));
            this._fld0h = this._mth1b("overtextcols", new Color(0));
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
            this._fld1h = new Font(parameter, n, int1);
            this._fld2h = this._fld2c.getFontMetrics(this._fld1h);
            final int stringWidth = this._fld2h.stringWidth(this._fld8g);
            final int height = this._fld2h.getHeight();
            final int n2 = (height >> 1) + (height >> 3);
            final String parameter4 = this.getParameter("overtextx");
            if (parameter4 == null) {
                this._fld3h = (this._fld4 >> 1) - (stringWidth >> 1);
            }
            else {
                this._fld4h = Integer.parseInt(parameter4);
            }
            final String parameter5 = this.getParameter("overtexty");
            if (parameter5 == null) {
                this._fld4h = (this._fld5 >> 1) - (n2 >> 1);
                return;
            }
            this._fld4h = Integer.parseInt(parameter5) + n2;
        }
    }
    
    void _mth4c(final Graphics graphics) {
        graphics.setFont(this._fld1h);
        graphics.setColor(this._fld0h);
        graphics.drawString(this._fld8g, this._fld3h + 1, this._fld4h + 1);
        graphics.setColor(this._fld9g);
        graphics.drawString(this._fld8g, this._fld3h, this._fld4h);
    }
    
    void _mth5c() {
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
    
    public DS_BackLightScroll() {
        this._fld3 = new Font("Helvetica", 1, 12);
        this._fld2b = "Applet by Dario Sciacca";
        this._fld3b = "dario@dseffects.com";
        this._fld6b = "www.dseffects.com";
        this._fld7b = "Don't remove Dario Sciacca's credits line";
        this._fld8b = this._fld2b + " (" + this._fld6b + ")";
        this._fld9b = false;
        this._fld0c = false;
        this._fld2d = "BackLightScroll started";
        this._fld4d = false;
        this._fld9f = true;
        this._fld0g = "";
        this._fld2g = "_blank";
        this._fld3g = "Applet by Dario Sciacca";
        this._fld5g = false;
    }
}
