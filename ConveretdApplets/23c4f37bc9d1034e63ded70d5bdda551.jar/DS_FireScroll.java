import java.awt.image.PixelGrabber;
import java.awt.image.ImageProducer;
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

public class DS_FireScroll extends Applet implements Runnable
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
    String _fld3d;
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
    int[] _fld4e;
    int[] _fld5e;
    int[] fb;
    int[] _fld6e;
    int _fld7e;
    int _fld8e;
    String _fld9e;
    URL _fld0f;
    String _fld1f;
    String _fld2f;
    URL _fld3f;
    boolean _fld4f;
    byte[] _fld5f;
    byte[] _fld6f;
    String _fld7f;
    Color _fld8f;
    Color _fld9f;
    Font _fld0g;
    FontMetrics _fld1g;
    int _fld2g;
    int _fld3g;
    
    public String getAppletInfo() {
        return "DS FireScroll v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
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
        this._mth3c();
        this._mth5c();
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
        this._mth0b();
        this._mth2c();
        this._mth6();
        this._mth1b();
        if (this._fld1d == -16777216) {
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
        this.showStatus(this._fld3d);
        System.gc();
        final Graphics graphics = this.getGraphics();
        this._fld2b = System.currentTimeMillis();
        while (this._fld0 != null) {
            if (this._fld3 == 1) {
                this._mth2b();
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
                this._mth4c(this._fld3c);
            }
            else if (this._fld5b == 2) {
                this._fld3c.drawImage(this._fld0b, 0, 0, this);
                this._fld3c.drawImage(this._fld4c, this._fld5c, this._fld6c, this);
            }
            else {
                this._fld3c.drawImage(this._fld0b, 0, 0, this);
                this._mth4c(this._fld3c);
                this._fld3c.drawImage(this._fld4c, this._fld5c, this._fld6c, this);
            }
        }
        if (this._fld5d && !this._fld4f) {
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
        this.showStatus(this._fld2f);
        return this._fld5d = true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this._fld5d = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int fld2d, final int n) {
        if (this._fld4d) {
            if (this._fld2d < fld2d) {
                this._fld0e += this._fld1e;
            }
            else if (this._fld2d > fld2d) {
                this._fld0e -= this._fld1e;
            }
            this._fld2d = fld2d;
        }
        return this._fld5d = true;
    }
    
    void _mth4() {
        final String parameter = this.getParameter("credits");
        if (parameter != null) {
            if (parameter.equals(this._fld9b)) {
                this._fld9c = 1;
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
        this._fld0d = 1;
        for (int i = 0; i < 11; ++i) {
            if (this._fld4b.charAt(i) == this._fld3b.charAt(i) || this._fld9c == 0) {
                while (true) {
                    this.showStatus(this._fld8b);
                }
            }
            else {}
        }
        this._fld4f = false;
    }
    
    void _mth6() {
        if (this._fld9c == 0 || this._fld0d == 0) {
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
            this._fld1d = -16777216;
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
    
    Color _mth9(final String s, final Color color) {
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
    
    void _mth0b() {
        final String parameter = this.getParameter("text");
        if (parameter == null) {
            this._fld7d = "Applet by Dario Sciacca";
        }
        else {
            this._fld7d = parameter;
        }
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
        final String parameter5 = this.getParameter("texty");
        if (parameter5 == null) {
            this._fld8d = -10000;
        }
        else {
            this._fld8d = Integer.valueOf(parameter5);
        }
        String parameter6 = this.getParameter("speed");
        if (parameter6 == null) {
            parameter6 = "2";
        }
        this._fld1e = Integer.valueOf(parameter6);
        this._fld1e = ((this._fld1e >= 1) ? ((this._fld1e <= 4) ? this._fld1e : 4) : 1);
        String parameter7 = this.getParameter("pause");
        if (parameter7 == null) {
            parameter7 = "50";
        }
        this._fld2e = Integer.valueOf(parameter7);
        this._fld2e = ((this._fld2e >= 0) ? ((this._fld2e <= 1000) ? this._fld2e : 1000) : 0);
        String parameter8 = this.getParameter("intensity");
        if (parameter8 == null) {
            parameter8 = "1";
        }
        this._fld8e = Integer.valueOf(parameter8);
        this._fld8e = ((this._fld8e >= 1) ? ((this._fld8e <= 8) ? this._fld8e : 8) : 1);
        this._fld8e += 12;
        final String parameter9 = this.getParameter("interactive");
        if (parameter9 == null) {
            this._fld4d = true;
        }
        else if (parameter9.equalsIgnoreCase("NO")) {
            this._fld4d = false;
        }
        else {
            this._fld4d = true;
        }
        this._fld2f = this._fld3b;
        final String parameter10 = this.getParameter("regkey");
        if (parameter10 != null) {
            this._fld9e = parameter10;
            final String parameter11 = this.getParameter("reglink");
            if (parameter11 != null) {
                try {
                    this._fld0f = new URL("http://" + parameter11);
                }
                catch (MalformedURLException ex) {
                    this._fld0f = null;
                }
                final String parameter12 = this.getParameter("regtarget");
                if (parameter12 != null) {
                    this._fld1f = parameter12;
                }
            }
            final String parameter13 = this.getParameter("regstatusmsg");
            if (parameter13 != null) {
                this._fld2f = parameter13;
            }
        }
    }
    
    void _mth1b() {
        this._fld7e = 0;
        this._fld7 = new int[this._fld5 * this._fld6];
        this._mth4b();
        this._mth6b();
        this._mth3b();
        this._fld0e = this._fld5;
        this._fld3e = this.tW + this._fld2e;
    }
    
    void _mth2b() {
        if (!this._fld4d) {
            this._fld0e -= this._fld1e;
        }
        else if (this._fld4d && !this._fld5d) {
            this._fld0e -= this._fld1e;
        }
        if (this._fld0e < -this._fld3e) {
            this._fld0e = this._fld5;
        }
        for (int i = 0; i < this._fld5 * this._fld6; ++i) {
            this._fld8[i] = -16777216;
        }
        this._mth5b(this._fld8, this._fld0e, this._fld8d, this._fld5, this._fld6, this._fld4e, 0, 0, this.tW - 1, this.tH - 1, this.tW, this.tH);
        this._fld7e = (this._fld7e + this._fld5) % (this._fld5 * this._fld6);
        this._fld0b = this.createImage(this._fld9);
    }
    
    void _mth3b() {
        this._fld4e = new int[this.tW * this.tH];
        if (this.tW * this.tH > 65536) {
            this._fld5e = new int[this.tW * this.tH];
        }
        else {
            this._fld5e = new int[65536];
        }
        this.fb = new int[this._fld5 * this._fld6];
        for (int i = 0; i < this.tW * this.tH; ++i) {
            this._fld5e[i] = (this._fld4e[i] = this._fld9d[i]);
        }
        this._mth8b(this._fld5e, this._fld4e);
        for (int j = 0; j < this.tW * this.tH; ++j) {
            final int[] fld9d = this._fld9d;
            final int n = j;
            fld9d[n] >>= 2;
            this._fld4e[j] = this._fld5e[j];
        }
        final int n2 = this.tW * this.tH;
        final int n3 = n2 - this.tW;
        for (int k = 0; k < this.tW; ++k) {
            this._fld4e[k] = 0;
        }
        for (int l = 1; l < this.tH - 1; ++l) {
            final int n4 = l * this.tW;
            final int n5 = n4 + this.tW;
            this._fld4e[n4] = 0;
            this._fld4e[n5] = 0;
        }
        for (int n6 = n3; n6 < n2; ++n6) {
            this._fld4e[n6] = 0;
        }
        this._mth1c(this._fld4e);
        this._mth1c(this._fld4e);
        this._mth0c(this._fld5e, 2, 2);
        this.fb = this._mth7(this.fb, this._fld5, this._fld6, this._fld5e, 256, 256);
    }
    
    void _mth4b() {
        final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this._fld6d);
        this.tW = fontMetrics.stringWidth(this._fld7d);
        this.tH = fontMetrics.getHeight() + this._fld8e;
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
                this._fld9d[i] = 120 + (int)(Math.random() * 64.0);
            }
            else {
                this._fld9d[i] = 0;
            }
        }
        if (this._fld8d == -10000) {
            this._fld8d = (this._fld6 >> 1) - (n >> 1);
        }
    }
    
    void _mth5b(final int[] array, int n, int n2, final int n3, final int n4, final int[] array2, int n5, int n6, int n7, int n8, final int n9, final int n10) {
        if (n >= this._fld5 || n2 >= this._fld6) {
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
        if (n7 >= this._fld5) {
            n7 = n9 - 1;
        }
        if (n8 >= this._fld6) {
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
        final int n13 = this._fld5 * this._fld6;
        for (int i = 0; i < n11; ++i) {
            final int n14 = (n2 + i) * n3 + n;
            final int n15 = (n6 + i) * n9 + n5;
            for (int j = 0; j < n12; ++j) {
                final int n16 = this._fld9d[n15 + j] + (array2[n15 + j] * this.fb[(n14 + j + this._fld7e) % n13] >> 6);
                array[n14 + j] = this._fld6e[(n16 <= 255) ? n16 : 255];
            }
        }
    }
    
    void _mth6b() {
        this._fld6e = new int[256];
        this._mth7b(0, 64, 0, 255, 0, 0, 0, 0);
        this._mth7b(64, 128, 255, 255, 0, 255, 0, 0);
        this._mth7b(128, 256, 255, 255, 255, 255, 0, 255);
    }
    
    void _mth7b(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        final int n9 = n2 - n;
        final int n10 = n4 - n3;
        final int n11 = n6 - n5;
        final int n12 = n8 - n7;
        for (int i = 0; i < n9; ++i) {
            this._fld6e[n + i] = (0xFF000000 | n3 + i * n10 / n9 << 16 | n5 + i * n11 / n9 << 8 | n7 + i * n12 / n9);
        }
    }
    
    void _mth8b(final int[] array, final int[] array2) {
        for (int i = 0; i < this.tW; ++i) {
            int n;
            for (int j = this.tH - 1; j >= 0; j -= n) {
                n = (int)(Math.random() * this._fld8e);
                for (int k = 0; k < n; ++k) {
                    if (j - k >= 0) {
                        array[(j - k) * this.tW + i] = array2[j * this.tW + i] + array[(j - k) * this.tW + i] >> 1;
                    }
                }
            }
        }
    }
    
    int _mth9b(final int n, final int n2) {
        int n3 = n + (int)(Math.random() * n2) - n2 / 2;
        if (n3 < 0) {
            n3 = 0;
        }
        if (n3 > 255) {
            n3 = 255;
        }
        return n3;
    }
    
    void _mth0c(final int[] array, final int n, final int n2) {
        int n3;
        for (int i = 256; i > 1; i = n3) {
            n3 = i >> 1;
            final int n4 = i * n + n2;
            for (int j = 0; j < 256; j += i) {
                final int n5 = j + i & 0xFF;
                for (int k = 0; k < 256; k += i) {
                    final int n6 = k + i & 0xFF;
                    final int n7 = j + n3;
                    final int n8 = k + n3;
                    final int n9 = array[(j << 8) + k];
                    final int n10 = array[(n5 << 8) + k];
                    final int n11 = array[(j << 8) + n6];
                    final int n12 = array[(n5 << 8) + n6];
                    array[(n7 << 8) + n8] = this._mth9b(n9 + n10 + n11 + n12 >> 2, n4);
                    if (j == 0) {
                        array[(n7 << 8) + k] = this._mth9b(n9 + n10 >> 1, n4);
                    }
                    if (k == 0) {
                        array[(j << 8) + n8] = this._mth9b(n9 + n11 >> 1, n4);
                    }
                    array[(n5 << 8) + n8] = this._mth9b(n10 + n12 >> 1, n4);
                    array[(n7 << 8) + n6] = this._mth9b(n11 + n12 >> 1, n4);
                }
            }
        }
    }
    
    void _mth1c(final int[] array) {
        for (int i = 1; i < this.tH - 1; ++i) {
            final int n = (i - 1) * this.tW;
            final int n2 = (i + 1) * this.tW;
            final int n3 = i * this.tW;
            for (int j = 1; j < this.tW - 1; ++j) {
                final int n4 = j - 1;
                final int n5 = j + 1;
                array[i * this.tW + j] = array[n4 + n] + array[n4 + n2] + array[n5 + n] + array[n5 + n2] + array[j + n] + array[j + n2] + array[n4 + n3] + array[n5 + n3] >> 3;
            }
        }
    }
    
    void _mth2c() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this._fld9e.length() > 9) {
            final int n = this._fld9e.length() - 9;
            final int n2 = n + 9;
            this._fld5f = new byte[n];
            this._fld9e.getBytes(1, n + 1, this._fld5f, 0);
            this._fld6f = new byte[n2];
            this._fld9e.getBytes(0, n2, this._fld6f, 0);
            int n3 = n % 7;
            final int n4 = n % 3;
            for (int i = 0; i < n; ++i) {
                final byte b = this._fld5f[i];
                final byte b2 = (byte)(b + n3);
                if (b >= 48 && b <= 57) {
                    this._fld5f[i] = ((b2 <= 57) ? b2 : ((byte)(b2 - 10)));
                }
                else if (b >= 65 && b <= 90) {
                    this._fld5f[i] = ((b2 <= 90) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b >= 97 && b <= 122) {
                    this._fld5f[i] = ((b2 <= 122) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b == 42) {
                    this._fld5f[i] = 45;
                }
                else if (b == 45) {
                    this._fld5f[i] = 46;
                }
                n3 = (n3 + n4) % 7;
            }
            int n5 = n % 7;
            final int n6 = n % 3;
            for (int j = 0; j < n; ++j) {
                final byte b3 = this._fld5f[j];
                final byte b4 = (byte)(b3 - n5);
                if (b3 >= 48 && b3 <= 57) {
                    this._fld6f[j + 1] = ((b4 >= 48) ? b4 : ((byte)(b4 + 10)));
                }
                else if (b3 >= 65 && b3 <= 90) {
                    this._fld6f[j + 1] = ((b4 >= 65) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 >= 97 && b3 <= 122) {
                    this._fld6f[j + 1] = ((b4 >= 97) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 == 45) {
                    this._fld6f[j + 1] = 42;
                }
                else if (b3 == 46) {
                    this._fld6f[j + 1] = 45;
                }
                n5 = (n5 + n6) % 7;
            }
            final byte[] array = new byte[n];
            if (n > 4) {
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld5f[k + 4];
                }
            }
            if (this._fld6f[0] == this._fld6f[n >> 1] && this._fld6f[1 + n] == this._fld6f[1] && this._fld6f[1 + n + 1] == this._fld6f[n >> 1] && this._fld6f[1 + n + 2] == (byte)(97 + n5) && this._fld6f[1 + n + 3] == 45 && this._fld6f[1 + n + 4] == (byte)(122 - n6) && this._fld6f[1 + n + 5] == (byte)(110 + n5) && this._fld6f[1 + n + 6] == this._fld6f[1] && this._fld6f[1 + n + 7] == this._fld6f[n] && (host.equalsIgnoreCase(new String(this._fld5f, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this._fld4f = true;
            }
        }
        try {
            this._fld3f = new URL("http://" + this._fld7b);
        }
        catch (MalformedURLException ex) {
            this._fld3f = null;
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld4f) {
            this.getAppletContext().showDocument(this._fld3f, "_blank");
        }
        else if (this._fld0f != null) {
            if (this._fld1f != null) {
                this.getAppletContext().showDocument(this._fld0f, this._fld1f);
            }
            else {
                this.getAppletContext().showDocument(this._fld0f);
            }
        }
        return true;
    }
    
    void _mth3c() {
        this._fld7f = this.getParameter("overtext");
        if (this._fld7f != null) {
            this._fld0c = true;
            this._fld8f = this._mth9("overtextcol", new Color(16777215));
            this._fld9f = this._mth9("overtextcols", new Color(0));
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
            this._fld0g = new Font(parameter, n, int1);
            this._fld1g = this._fld3c.getFontMetrics(this._fld0g);
            final int stringWidth = this._fld1g.stringWidth(this._fld7f);
            final int height = this._fld1g.getHeight();
            final int n2 = (height >> 1) + (height >> 3);
            final String parameter4 = this.getParameter("overtextx");
            if (parameter4 == null) {
                this._fld2g = (this._fld5 >> 1) - (stringWidth >> 1);
            }
            else {
                this._fld3g = Integer.parseInt(parameter4);
            }
            final String parameter5 = this.getParameter("overtexty");
            if (parameter5 == null) {
                this._fld3g = (this._fld6 >> 1) - (n2 >> 1);
                return;
            }
            this._fld3g = Integer.parseInt(parameter5) + n2;
        }
    }
    
    void _mth4c(final Graphics graphics) {
        graphics.setFont(this._fld0g);
        graphics.setColor(this._fld9f);
        graphics.drawString(this._fld7f, this._fld2g + 1, this._fld3g + 1);
        graphics.setColor(this._fld8f);
        graphics.drawString(this._fld7f, this._fld2g, this._fld3g);
    }
    
    void _mth5c() {
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
    
    public DS_FireScroll() {
        this._fld4 = new Font("Helvetica", 1, 12);
        this._fld3b = "Applet by Dario Sciacca";
        this._fld4b = "dario@dseffects.com";
        this._fld7b = "www.dseffects.com";
        this._fld8b = "Don't remove Dario Sciacca's credits line";
        this._fld9b = this._fld3b + " (" + this._fld7b + ")";
        this._fld0c = false;
        this._fld1c = false;
        this._fld3d = "FireScroll started";
        this._fld5d = false;
        this._fld9e = "";
        this._fld1f = "_blank";
        this._fld2f = "Applet by Dario Sciacca";
        this._fld4f = false;
    }
}
