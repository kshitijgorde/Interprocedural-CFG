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

public class DS_FireText extends Applet implements Runnable
{
    Thread _fld0;
    int _fld2;
    Font _fld3;
    int _fld4;
    int _fld5;
    int[] _fld6;
    MemoryImageSource _fld7;
    Image _fld8;
    int _fld9;
    long _fld0b;
    String _fld1b;
    String _fld2b;
    int _fld3b;
    int _fld4b;
    String _fld5b;
    String _fld6b;
    String _fld7b;
    boolean _fld8b;
    boolean _fld9b;
    Image _fld0c;
    Graphics _fld1c;
    Image _fld2c;
    int _fld3c;
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
    int _fld7d;
    String _fld8d;
    int _fld9d;
    boolean _fld0e;
    boolean _fld1e;
    Font _fld2e;
    String _fld3e;
    int _fld4e;
    int _fld5e;
    int[] _fld6e;
    int[] fb;
    int[] _fld7e;
    int[] _fld8e;
    int _fld9e;
    int _fld0f;
    int _fld1f;
    int[] _fld2f;
    int[] _fld3f;
    String _fld4f;
    URL _fld5f;
    String _fld6f;
    String _fld7f;
    URL _fld8f;
    boolean _fld9f;
    byte[] _fld0g;
    byte[] _fld1g;
    String[] _fld2g;
    Color[] _fld3g;
    Color[] _fld4g;
    Font[] _fld5g;
    FontMetrics[] _fld6g;
    String[] _fld7g;
    int[] _fld8g;
    int[] _fld9g;
    
    public String getAppletInfo() {
        return "DS FireText v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
    }
    
    public void init() {
        this._mth3();
        this.showStatus("Please wait ...");
        this._fld4b = this.getFontMetrics(this._fld3).stringWidth(this._fld5b);
        this._fld4 = this.size().width;
        this._fld5 = this.size().height;
        this._mth4();
        this._fld6 = new int[this._fld4 * this._fld5];
        this._fld7 = new MemoryImageSource(this._fld4, this._fld5, this._fld6, 0, this._fld4);
        this._fld0c = this.createImage(this._fld4, this._fld5);
        this._fld1c = this._fld0c.getGraphics();
        this._mth2c();
        this._mth5c();
        if (!this._fld8b && !this._fld9b) {
            this._fld3b = 0;
        }
        else if (this._fld8b && !this._fld9b) {
            this._fld3b = 1;
        }
        else if (!this._fld8b && this._fld9b) {
            this._fld3b = 2;
        }
        else {
            this._fld3b = 3;
        }
        this._mth9();
        this._mth1c();
        this._mth5();
        this._mth0b();
        if (this._fld5d == -16777216) {
            this._fld2 = 1;
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
        this.showStatus(this._fld8d);
        System.gc();
        final Graphics graphics = this.getGraphics();
        this._fld0b = System.currentTimeMillis();
        while (this._fld0 != null) {
            if (this._fld2 == 1) {
                this._mth2b();
            }
            this._mth2(graphics);
            this._mth0();
            if (this._fld9++ > 10) {
                System.gc();
                this._fld9 = 0;
            }
        }
    }
    
    synchronized void _mth0() {
        Thread.yield();
        this.getToolkit().sync();
        final long n = 10L - (System.currentTimeMillis() - this._fld0b);
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
        this._fld0b = System.currentTimeMillis();
    }
    
    public void update(final Graphics graphics) {
    }
    
    void _mth2(final Graphics graphics) {
        final int n = this._fld4 >> 1;
        final int n2 = this._fld5 >> 1;
        if (this._fld2 == 0) {
            graphics.drawString("Error ...", 10, 10);
            return;
        }
        if (this._fld8 != null) {
            if (this._fld3b == 0) {
                if (!this._fld9f) {
                    this._fld1c.drawImage(this._fld8, 0, 0, this);
                }
                else {
                    graphics.drawImage(this._fld8, 0, 0, this);
                }
            }
            else if (this._fld3b == 1) {
                this._fld1c.drawImage(this._fld8, 0, 0, this);
                this._mth4c(this._fld1c);
            }
            else if (this._fld3b == 2) {
                this._fld1c.drawImage(this._fld8, 0, 0, this);
                this._fld1c.drawImage(this._fld2c, this._fld3c, this._fld4c, this);
            }
            else {
                this._fld1c.drawImage(this._fld8, 0, 0, this);
                this._mth4c(this._fld1c);
                this._fld1c.drawImage(this._fld2c, this._fld3c, this._fld4c, this);
            }
        }
        if (this._fld0e && !this._fld9f) {
            this._fld1c.setColor(Color.white);
            this._fld1c.drawLine(n - 64, n2 - 8, n + 64, n2 - 8);
            this._fld1c.drawLine(n - 64, n2 + 8, n + 64, n2 + 8);
            this._fld1c.drawLine(n - 64, n2 - 8, n - 64, n2 + 8);
            this._fld1c.drawLine(n + 64, n2 - 8, n + 64, n2 + 8);
            this._fld1c.setColor(Color.blue);
            this._fld1c.fillRect(n - 63, n2 - 7, 127, 15);
            this._fld1c.setFont(this._fld3);
            this._fld1c.setColor(Color.yellow);
            this._fld1c.drawString(this._fld5b, n - (this._fld4b >> 1), n2 + 5);
        }
        graphics.drawImage(this._fld0c, 0, 0, this);
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this._fld7f);
        return this._fld0e = true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this._fld0e = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int fld6d, final int fld7d) {
        this._fld6d = fld6d;
        this._fld7d = fld7d;
        return this._fld0e = true;
    }
    
    void _mth3() {
        final String parameter = this.getParameter("credits");
        if (parameter != null) {
            if (parameter.equals(this._fld7b)) {
                this._fld3d = 1;
                return;
            }
            while (true) {
                this.showStatus(this._fld6b);
            }
        }
        else {
            while (true) {
                this.showStatus(this._fld6b);
            }
        }
    }
    
    void _mth4() {
        this._fld4d = 1;
        for (int i = 0; i < 11; ++i) {
            if (this._fld2b.charAt(i) == this._fld1b.charAt(i) || this._fld3d == 0) {
                while (true) {
                    this.showStatus(this._fld6b);
                }
            }
            else {}
        }
        this._fld9f = false;
    }
    
    void _mth5() {
        if (this._fld3d == 0 || this._fld4d == 0) {
            while (true) {
                this.showStatus(this._fld6b);
            }
        }
        else {
            for (int i = 0; i < 17; ++i) {
                if (this._fld2b.charAt(i) == this._fld5b.charAt(i)) {
                    while (true) {
                        this.showStatus(this._fld6b);
                    }
                }
                else {}
            }
            this._fld5d = -16777216;
            if (this._fld1b.charAt(1) == 'p' && this._fld1b.charAt(7) == 'b' && this._fld1b.charAt(21) == 'c' && this._fld1b.charAt(17) == 'c' && this._fld1b.charAt(12) == 'r' && this._fld1b.charAt(11) == 'a') {
                return;
            }
            while (true) {
                this.showStatus(this._fld6b);
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
    
    Image _mth7(final String s) {
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
    
    Color _mth8(final String s, final Color color) {
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
    
    void _mth9() {
        final String parameter = this.getParameter("text");
        if (parameter == null) {
            this._fld3e = "Dario";
        }
        else {
            this._fld3e = parameter;
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
        this._fld2e = new Font(parameter2, n, int1);
        final String parameter5 = this.getParameter("textx");
        if (parameter5 == null) {
            this._fld4e = -10000;
        }
        else {
            this._fld4e = Integer.valueOf(parameter5);
        }
        final String parameter6 = this.getParameter("texty");
        if (parameter6 == null) {
            this._fld5e = -10000;
        }
        else {
            this._fld5e = Integer.valueOf(parameter6);
        }
        String parameter7 = this.getParameter("intensity");
        if (parameter7 == null) {
            parameter7 = "1";
        }
        this._fld0f = Integer.valueOf(parameter7);
        this._fld0f = ((this._fld0f >= 1) ? ((this._fld0f <= 8) ? this._fld0f : 8) : 1);
        this._fld0f += 12;
        final String parameter8 = this.getParameter("interactive");
        if (parameter8 == null) {
            this._fld9d = 1;
        }
        else if (parameter8.equalsIgnoreCase("IN")) {
            this._fld9d = 0;
        }
        else if (parameter8.equalsIgnoreCase("OUT")) {
            this._fld9d = 1;
        }
        else {
            this._fld9d = 2;
        }
        this._fld7f = this._fld1b;
        final String parameter9 = this.getParameter("regkey");
        if (parameter9 != null) {
            this._fld4f = parameter9;
            final String parameter10 = this.getParameter("reglink");
            if (parameter10 != null) {
                try {
                    this._fld5f = new URL("http://" + parameter10);
                }
                catch (MalformedURLException ex) {
                    this._fld5f = null;
                }
                final String parameter11 = this.getParameter("regtarget");
                if (parameter11 != null) {
                    this._fld6f = parameter11;
                }
            }
            final String parameter12 = this.getParameter("regstatusmsg");
            if (parameter12 != null) {
                this._fld7f = parameter12;
            }
        }
    }
    
    void _mth0b() {
        this._fld6e = new int[this._fld4 * this._fld5];
        this._mth3b();
        this._fld9e = 0;
        this._mth5b();
        if (this._fld9d == 2) {
            for (int i = 0; i < 256; ++i) {
                this._fld3f[i] = this._fld2f[i];
            }
        }
        else {
            for (int j = 0; j < 256; ++j) {
                this._fld3f[j] = -16777216;
            }
        }
        this._mth1b();
    }
    
    void _mth1b() {
        this._fld8e = new int[this._fld4 * this._fld5];
        if (this._fld4 * this._fld5 > 65536) {
            this._fld7e = new int[this._fld4 * this._fld5];
        }
        else {
            this._fld7e = new int[65536];
        }
        this.fb = new int[this._fld4 * this._fld5];
        for (int i = 0; i < this._fld4 * this._fld5; ++i) {
            this._fld7e[i] = (this._fld8e[i] = this._fld6e[i]);
        }
        this._mth8b(this._fld7e, this._fld8e);
        for (int j = 0; j < this._fld4 * this._fld5; ++j) {
            final int[] fld6e = this._fld6e;
            final int n = j;
            fld6e[n] >>= 2;
            this._fld8e[j] = this._fld7e[j];
        }
        final int n2 = this._fld4 * this._fld5;
        final int n3 = n2 - this._fld4;
        for (int k = 0; k < this._fld4; ++k) {
            this._fld8e[k] = 0;
        }
        for (int l = 1; l < this._fld5 - 1; ++l) {
            final int n4 = l * this._fld4;
            final int n5 = n4 + this._fld4;
            this._fld8e[n4] = 0;
            this._fld8e[n5] = 0;
        }
        for (int n6 = n3; n6 < n2; ++n6) {
            this._fld8e[n6] = 0;
        }
        this._mth4b(this._fld8e);
        this._mth4b(this._fld8e);
        this._mth0c(this._fld7e, 2, 2);
        this.fb = this._mth6(this.fb, this._fld4, this._fld5, this._fld7e, 256, 256);
    }
    
    void _mth2b() {
        if (this._fld9d == 0) {
            if (this._fld0e) {
                this._fld9e += 8;
                if (this._fld9e > 255) {
                    this._fld9e = 255;
                }
                else {
                    this._mth7b(this._fld9e, this._fld3f, this._fld2f);
                }
            }
            else {
                this._fld9e -= 8;
                if (this._fld9e < 0) {
                    this._fld9e = 0;
                }
                else {
                    this._mth7b(this._fld9e, this._fld3f, this._fld2f);
                }
            }
        }
        else if (this._fld9d == 1) {
            if (this._fld0e) {
                this._fld9e -= 8;
                if (this._fld9e < 0) {
                    this._fld9e = 0;
                }
                else {
                    this._mth7b(this._fld9e, this._fld3f, this._fld2f);
                }
            }
            else {
                this._fld9e += 8;
                if (this._fld9e > 255) {
                    this._fld9e = 255;
                }
                else {
                    this._mth7b(this._fld9e, this._fld3f, this._fld2f);
                }
            }
        }
        final int n = this._fld4 * this._fld5;
        for (int i = 0; i < n; ++i) {
            final int n2 = this._fld6e[i] + (this._fld8e[i] * this.fb[(i + this._fld1f) % n] >> 6);
            this._fld6[i] = this._fld3f[(n2 <= 255) ? n2 : 255];
        }
        this._fld1f = (this._fld1f + this._fld4) % n;
        this._fld8 = this.createImage(this._fld7);
    }
    
    void _mth3b() {
        new MemoryImageSource(this._fld4, this._fld5, this._fld6e, 0, this._fld4);
        final Image image = this.createImage(this._fld4, this._fld5);
        final Graphics graphics = image.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this._fld4, this._fld5);
        graphics.setColor(Color.white);
        graphics.setFont(this._fld2e);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this._fld2e);
        final int stringWidth = fontMetrics.stringWidth(this._fld3e);
        final int height = fontMetrics.getHeight();
        final int n = (height >> 1) + (height >> 3);
        if (this._fld4e == -10000) {
            this._fld4e = (this._fld4 >> 1) - (stringWidth >> 1);
        }
        if (this._fld5e == -10000) {
            this._fld5e = (this._fld5 >> 1) - (n >> 1);
        }
        graphics.drawString(this._fld3e, this._fld4e, this._fld5e + n);
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this._fld4, this._fld5, this._fld6e, 0, this._fld4);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        for (int i = 0; i < this._fld4 * this._fld5; ++i) {
            if ((this._fld6e[i] & 0xFF) == 0xFF) {
                this._fld6e[i] = 120 + (int)(Math.random() * 64.0);
            }
            else {
                this._fld6e[i] = 0;
            }
        }
    }
    
    void _mth4b(final int[] array) {
        for (int i = 1; i < this._fld5 - 1; ++i) {
            final int n = (i - 1) * this._fld4;
            final int n2 = (i + 1) * this._fld4;
            final int n3 = i * this._fld4;
            for (int j = 1; j < this._fld4 - 1; ++j) {
                final int n4 = j - 1;
                final int n5 = j + 1;
                array[i * this._fld4 + j] = array[n4 + n] + array[n4 + n2] + array[n5 + n] + array[n5 + n2] + array[j + n] + array[j + n2] + array[n4 + n3] + array[n5 + n3] >> 3;
            }
        }
    }
    
    void _mth5b() {
        this._fld2f = new int[256];
        this._fld3f = new int[256];
        this._mth6b(0, 64, 0, 255, 0, 0, 0, 0);
        this._mth6b(64, 128, 255, 255, 0, 255, 0, 0);
        this._mth6b(128, 256, 255, 255, 255, 255, 0, 255);
    }
    
    void _mth6b(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        final int n9 = n2 - n;
        final int n10 = n4 - n3;
        final int n11 = n6 - n5;
        final int n12 = n8 - n7;
        for (int i = 0; i < n9; ++i) {
            this._fld2f[n + i] = (0xFF000000 | n3 + i * n10 / n9 << 16 | n5 + i * n11 / n9 << 8 | n7 + i * n12 / n9);
        }
    }
    
    void _mth7b(final int n, final int[] array, final int[] array2) {
        for (int i = 0; i < 256; ++i) {
            array[i] = array2[i * n >> 8];
        }
    }
    
    void _mth8b(final int[] array, final int[] array2) {
        for (int i = 0; i < this._fld4; ++i) {
            int n;
            for (int j = this._fld5 - 1; j >= 0; j -= n) {
                n = (int)(Math.random() * this._fld0f);
                for (int k = 0; k < n; ++k) {
                    if (j - k >= 0) {
                        array[(j - k) * this._fld4 + i] = array2[j * this._fld4 + i] + array[(j - k) * this._fld4 + i] >> 1;
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
    
    void _mth1c() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this._fld4f.length() > 9) {
            final int n = this._fld4f.length() - 9;
            final int n2 = n + 9;
            this._fld0g = new byte[n];
            this._fld4f.getBytes(1, n + 1, this._fld0g, 0);
            this._fld1g = new byte[n2];
            this._fld4f.getBytes(0, n2, this._fld1g, 0);
            int n3 = n % 7;
            final int n4 = n % 3;
            for (int i = 0; i < n; ++i) {
                final byte b = this._fld0g[i];
                final byte b2 = (byte)(b + n3);
                if (b >= 48 && b <= 57) {
                    this._fld0g[i] = ((b2 <= 57) ? b2 : ((byte)(b2 - 10)));
                }
                else if (b >= 65 && b <= 90) {
                    this._fld0g[i] = ((b2 <= 90) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b >= 97 && b <= 122) {
                    this._fld0g[i] = ((b2 <= 122) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b == 42) {
                    this._fld0g[i] = 45;
                }
                else if (b == 45) {
                    this._fld0g[i] = 46;
                }
                n3 = (n3 + n4) % 7;
            }
            int n5 = n % 7;
            final int n6 = n % 3;
            for (int j = 0; j < n; ++j) {
                final byte b3 = this._fld0g[j];
                final byte b4 = (byte)(b3 - n5);
                if (b3 >= 48 && b3 <= 57) {
                    this._fld1g[j + 1] = ((b4 >= 48) ? b4 : ((byte)(b4 + 10)));
                }
                else if (b3 >= 65 && b3 <= 90) {
                    this._fld1g[j + 1] = ((b4 >= 65) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 >= 97 && b3 <= 122) {
                    this._fld1g[j + 1] = ((b4 >= 97) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 == 45) {
                    this._fld1g[j + 1] = 42;
                }
                else if (b3 == 46) {
                    this._fld1g[j + 1] = 45;
                }
                n5 = (n5 + n6) % 7;
            }
            final byte[] array = new byte[n];
            if (n > 4) {
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld0g[k + 4];
                }
            }
            if (this._fld1g[0] == this._fld1g[n >> 1] && this._fld1g[1 + n] == this._fld1g[1] && this._fld1g[1 + n + 1] == this._fld1g[n >> 1] && this._fld1g[1 + n + 2] == (byte)(97 + n5) && this._fld1g[1 + n + 3] == 45 && this._fld1g[1 + n + 4] == (byte)(122 - n6) && this._fld1g[1 + n + 5] == (byte)(110 + n5) && this._fld1g[1 + n + 6] == this._fld1g[1] && this._fld1g[1 + n + 7] == this._fld1g[n] && (host.equalsIgnoreCase(new String(this._fld0g, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this._fld9f = true;
            }
        }
        try {
            this._fld8f = new URL("http://" + this._fld5b);
        }
        catch (MalformedURLException ex) {
            this._fld8f = null;
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld9f) {
            this.getAppletContext().showDocument(this._fld8f, "_blank");
        }
        else if (this._fld5f != null) {
            if (this._fld6f != null) {
                this.getAppletContext().showDocument(this._fld5f, this._fld6f);
            }
            else {
                this.getAppletContext().showDocument(this._fld5f);
            }
        }
        return true;
    }
    
    void _mth2c() {
        int fld8c = 0;
        do {
            ++fld8c;
        } while (this.getParameter("overtext" + fld8c) != null);
        if (--fld8c > 0) {
            this._fld8b = true;
            this._fld8c = fld8c;
            this._fld2g = new String[this._fld8c];
            this._fld3g = new Color[this._fld8c];
            this._fld4g = new Color[this._fld8c];
            this._fld5g = new Font[this._fld8c];
            this._fld6g = new FontMetrics[this._fld8c];
            this._fld7g = new String[this._fld8c];
            this._fld8g = new int[this._fld8c];
            this._fld9g = new int[this._fld8c];
            for (int i = 0; i < this._fld8c; ++i) {
                this._fld2g[i] = this.getParameter("overtext" + String.valueOf(i + 1));
                this._fld3g[i] = this._mth8("overtextcol" + String.valueOf(i + 1), new Color(16777215));
                this._fld4g[i] = this._mth8("overtextcols" + String.valueOf(i + 1), new Color(0));
                final String parameter = this.getParameter("overtexty" + String.valueOf(i + 1));
                if (parameter == null) {
                    this._fld9g[i] = 10;
                }
                else {
                    this._fld9g[i] = Integer.parseInt(parameter);
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
                this._fld5g[i] = new Font(parameter2, n, int1);
                this._fld6g[i] = this._fld1c.getFontMetrics(this._fld5g[i]);
                this._fld7g[i] = this.getParameter("overTextType" + String.valueOf(i + 1));
                if (this._fld7g[i] == null) {
                    this._fld7g[i] = "scrollleft";
                }
                final String parameter5 = this.getParameter("overtextspeed" + String.valueOf(i + 1));
                if (parameter5 == null) {
                    this._fld8g[i] = 2;
                }
                else {
                    this._fld8g[i] = Integer.valueOf(parameter5);
                    if (this._fld8g[i] < 1 || this._fld8g[i] > 4) {
                        this._fld8g[i] = 2;
                    }
                }
            }
            this._mth3c();
        }
    }
    
    void _mth3c() {
        this._fld1d = this._fld6g[this._fld7c].stringWidth(this._fld2g[this._fld7c]);
        this._fld2d = this._fld6g[this._fld7c].getHeight();
        if (this._fld7g[this._fld7c].equalsIgnoreCase("scrolldown")) {
            this._fld9c = this._fld4 - this._fld1d >> 1;
            this._fld0d = 0;
            return;
        }
        if (this._fld7g[this._fld7c].equalsIgnoreCase("scrollup")) {
            this._fld9c = this._fld4 - this._fld1d >> 1;
            this._fld0d = this._fld5 + this._fld2d;
            return;
        }
        if (this._fld7g[this._fld7c].equalsIgnoreCase("scrollright")) {
            this._fld9c = -this._fld1d;
            this._fld0d = this._fld9g[this._fld7c] + (this._fld2d >> 1) + (this._fld2d >> 3);
            return;
        }
        this._fld9c = this._fld4;
        this._fld0d = this._fld9g[this._fld7c] + (this._fld2d >> 1) + (this._fld2d >> 3);
    }
    
    void _mth4c(final Graphics graphics) {
        graphics.setFont(this._fld5g[this._fld7c]);
        graphics.setColor(this._fld4g[this._fld7c]);
        graphics.drawString(this._fld2g[this._fld7c], this._fld9c + 1, this._fld0d + 1);
        graphics.setColor(this._fld3g[this._fld7c]);
        graphics.drawString(this._fld2g[this._fld7c], this._fld9c, this._fld0d);
        if (this._fld7g[this._fld7c].equalsIgnoreCase("scrolldown")) {
            this._fld0d += this._fld8g[this._fld7c];
        }
        else if (this._fld7g[this._fld7c].equalsIgnoreCase("scrollup")) {
            this._fld0d -= this._fld8g[this._fld7c];
        }
        else if (this._fld7g[this._fld7c].equalsIgnoreCase("scrollright")) {
            this._fld9c += this._fld8g[this._fld7c];
        }
        else {
            this._fld9c -= this._fld8g[this._fld7c];
        }
        if (this._fld0d > this._fld5 + this._fld2d || this._fld0d < -this._fld2d || this._fld9c > this._fld4 || this._fld9c < -this._fld1d) {
            ++this._fld7c;
            if (this._fld7c >= this._fld8c) {
                this._fld7c = 0;
            }
            this._mth3c();
        }
    }
    
    void _mth5c() {
        final String parameter = this.getParameter("OverImage");
        if (parameter != null) {
            this._fld2c = this._mth7(parameter);
        }
        if (this._fld2c != null) {
            this._fld9b = true;
            this._fld5c = this._fld2c.getWidth(this);
            this._fld6c = this._fld2c.getHeight(this);
            final String parameter2 = this.getParameter("OverImageX");
            if (parameter2 == null) {
                this._fld3c = (this._fld4 >> 1) - (this._fld5c >> 1);
            }
            else {
                this._fld3c = Integer.valueOf(parameter2);
            }
            final String parameter3 = this.getParameter("OverImageY");
            if (parameter3 == null) {
                this._fld4c = (this._fld5 >> 1) - (this._fld6c >> 1);
                return;
            }
            this._fld4c = Integer.valueOf(parameter3);
        }
    }
    
    public DS_FireText() {
        this._fld3 = new Font("Helvetica", 1, 12);
        this._fld1b = "Applet by Dario Sciacca";
        this._fld2b = "dario@dseffects.com";
        this._fld5b = "www.dseffects.com";
        this._fld6b = "Don't remove Dario Sciacca's credits line";
        this._fld7b = this._fld1b + " (" + this._fld5b + ")";
        this._fld8b = false;
        this._fld9b = false;
        this._fld8d = "FireText started";
        this._fld0e = false;
        this._fld1e = false;
        this._fld4f = "";
        this._fld6f = "_blank";
        this._fld7f = "Applet by Dario Sciacca";
        this._fld9f = false;
    }
}
