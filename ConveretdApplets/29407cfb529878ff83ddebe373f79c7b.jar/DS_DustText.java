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

public class DS_DustText extends Applet implements Runnable
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
    int _fld3d;
    int _fld4d;
    int _fld5d;
    int _fld6d;
    int _fld7d;
    String _fld8d;
    int _fld9d;
    boolean _fld0e;
    boolean _fld1e;
    Font[] _fld2e;
    String[] _fld3e;
    int[] _fld4e;
    int[] _fld5e;
    int[] _fld6e;
    int[] _fld7e;
    int _fld8e;
    int[] _fld9e;
    int _fld0f;
    int _fld1f;
    int _fld2f;
    int _fld3f;
    float[] px;
    float[] py;
    float[] _fld4f;
    float[] _fld5f;
    double _fld6f;
    int _fld7f;
    int _fld8f;
    boolean _fld9f;
    int _fld0g;
    int _fld1g;
    int _fld2g;
    boolean[] _fld3g;
    double[] _fld4g;
    double[] _fld5g;
    int[] _fld6g;
    int[] _fld7g;
    double[] _fld8g;
    int[] _fld9g;
    int _fld0h;
    int _fld1h;
    String _fld2h;
    URL[] _fld3h;
    String[] _fld4h;
    String[] _fld5h;
    URL _fld6h;
    boolean _fld7h;
    byte[] _fld8h;
    byte[] _fld9h;
    String[] _fld0i;
    Color[] _fld1i;
    Color[] _fld2i;
    Font[] _fld3i;
    FontMetrics[] _fld4i;
    String[] _fld5i;
    int[] _fld6i;
    int[] _fld7i;
    
    public String getAppletInfo() {
        return "DS DustText v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
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
        this._mth0c();
        this._mth3c();
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
        this._mth2b();
        this._mth9b();
        if (!this._mth8()) {
            this._fld1e = false;
        }
        this._mth6();
        this._mth3b();
        if (this._fld7d == -16777216) {
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
        this.showStatus(this._fld8d);
        System.gc();
        final Graphics graphics = this.getGraphics();
        this._fld2b = System.currentTimeMillis();
        while (this._fld0 != null) {
            if (this._fld3 == 1) {
                this._mth4b();
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
                this._mth2c(this._fld3c);
            }
            else if (this._fld5b == 2) {
                this._fld3c.drawImage(this._fld0b, 0, 0, this);
                this._fld3c.drawImage(this._fld4c, this._fld5c, this._fld6c, this);
            }
            else {
                this._fld3c.drawImage(this._fld0b, 0, 0, this);
                this._mth2c(this._fld3c);
                this._fld3c.drawImage(this._fld4c, this._fld5c, this._fld6c, this);
            }
        }
        if (this._fld0e && !this._fld7h) {
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
        this.showStatus(this._fld5h[this._fld1f]);
        return this._fld0e = true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this._fld0e = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int n, final int n2) {
        return this._fld0e = true;
    }
    
    void _mth4() {
        final String parameter = this.getParameter("credits");
        if (parameter != null) {
            if (parameter.equals(this._fld9b)) {
                this._fld5d = 1;
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
        this._fld6d = 1;
        for (int i = 0; i < 11; ++i) {
            if (this._fld4b.charAt(i) == this._fld3b.charAt(i) || this._fld5d == 0) {
                while (true) {
                    this.showStatus(this._fld8b);
                }
            }
            else {}
        }
        this._fld7h = false;
    }
    
    void _mth6() {
        if (this._fld5d == 0 || this._fld6d == 0) {
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
            this._fld7d = -16777216;
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
        this._fld7 = new int[this._fld5 * this._fld6];
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
        this._fld0f = 1;
        while (this.getParameter("text" + String.valueOf(this._fld0f)) != null) {
            ++this._fld0f;
        }
        --this._fld0f;
        if (this._fld0f == 0) {
            this._fld0f = 1;
        }
        this._fld2e = new Font[this._fld0f];
        this._fld3e = new String[this._fld0f];
        this._fld4e = new int[this._fld0f];
        this._fld5e = new int[this._fld0f];
        this._fld6e = new int[this._fld0f];
        for (int i = 1; i < this._fld0f + 1; ++i) {
            final String parameter = this.getParameter("text" + i);
            if (parameter == null) {
                this._fld3e[i - 1] = "DS Effects";
            }
            else {
                this._fld3e[i - 1] = parameter;
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
            this._fld2e[i - 1] = new Font(parameter2, n, int1);
            final String parameter5 = this.getParameter("textx" + i);
            if (parameter5 == null) {
                this._fld4e[i - 1] = -10000;
            }
            else {
                this._fld4e[i - 1] = Integer.valueOf(parameter5);
            }
            final String parameter6 = this.getParameter("texty" + i);
            if (parameter6 == null) {
                this._fld5e[i - 1] = -10000;
            }
            else {
                this._fld5e[i - 1] = Integer.valueOf(parameter6);
            }
            this._fld6e[i - 1] = this._mth1b("textcolor" + i, new Color(16711680)).getRGB();
        }
        this._fld8e = this._mth1b("bgcolor", new Color(0)).getRGB();
        String parameter7 = this.getParameter("speed");
        if (parameter7 == null) {
            parameter7 = "2";
        }
        this._fld2f = Integer.valueOf(parameter7);
        this._fld2f = ((this._fld2f >= 1) ? ((this._fld2f <= 8) ? this._fld2f : 8) : 1);
        String parameter8 = this.getParameter("pause");
        if (parameter8 == null) {
            parameter8 = "1";
        }
        this._fld3f = Integer.valueOf(parameter8);
        this._fld3f = ((this._fld3f >= 0) ? ((this._fld3f <= 8) ? this._fld3f : 8) : 0);
        this._fld3f *= 50;
        final String parameter9 = this.getParameter("interactive");
        if (parameter9 == null) {
            this._fld9d = 1;
        }
        else if (parameter9.equalsIgnoreCase("IN")) {
            this._fld9d = 0;
        }
        else if (parameter9.equalsIgnoreCase("OUT")) {
            this._fld9d = 1;
        }
        else {
            this._fld9d = 2;
        }
        this._fld3h = new URL[this._fld0f];
        this._fld4h = new String[this._fld0f];
        this._fld5h = new String[this._fld0f];
        for (int j = 1; j <= this._fld0f; ++j) {
            this._fld5h[j - 1] = this._fld3b;
        }
        final String parameter10 = this.getParameter("regkey");
        if (parameter10 != null) {
            this._fld2h = parameter10;
            for (int k = 1; k <= this._fld0f; ++k) {
                final String parameter11 = this.getParameter("reglink" + k);
                if (parameter11 != null) {
                    try {
                        this._fld3h[k - 1] = new URL("http://" + parameter11);
                    }
                    catch (MalformedURLException ex) {
                        this._fld3h[k - 1] = null;
                    }
                    final String parameter12 = this.getParameter("regtarget" + k);
                    if (parameter12 != null) {
                        this._fld4h[k - 1] = parameter12;
                    }
                }
                final String parameter13 = this.getParameter("regstatusmsg" + k);
                if (parameter13 != null) {
                    this._fld5h[k - 1] = parameter13;
                }
            }
        }
    }
    
    void _mth3b() {
        this._fld0g = 0;
        this._fld6f = 1.0 / (17 - this._fld2f * 2);
        this._fld8f = this._fld5 * this._fld6;
        this.px = new float[this._fld8f];
        this.py = new float[this._fld8f];
        this._fld4f = new float[this._fld8f];
        this._fld5f = new float[this._fld8f];
        this._fld1f = 0;
        if (this._fld9d == 2) {
            this._fld9f = true;
        }
        else {
            this._fld9f = false;
        }
        this._fld7f = this._fld8f;
        final int n = this._fld5 * this._fld6;
        this._fld7e = new int[n];
        this._fld9e = new int[n];
        this._mth5b(this._fld1f, this._fld7e);
        if (!this._fld1e) {
            this._fld7 = new int[n];
            for (int i = 0; i < n; ++i) {
                this._fld7[i] = this._fld8e;
            }
        }
        this._fld3g = new boolean[this._fld5 * this._fld6];
        this._fld4g = new double[this._fld5 * this._fld6];
        this._fld5g = new double[this._fld5 * this._fld6];
        this._fld6g = new int[this._fld5 * this._fld6];
        this._fld7g = new int[this._fld5 * this._fld6];
        this._fld8g = new double[this._fld5 * this._fld6];
        this._fld9g = new int[this._fld5 * this._fld6];
        this._fld0g = 0;
        this._mth6b();
        if (this._fld9d == 2) {
            this._fld9f = true;
            return;
        }
        this._fld9f = false;
    }
    
    void _mth4b() {
        if (this._fld9d == 0) {
            if (this._fld0e) {
                this._fld9f = true;
            }
            else {
                this._fld9f = false;
            }
        }
        else if (this._fld9d == 1) {
            if (this._fld0e) {
                this._fld9f = false;
            }
            else {
                this._fld9f = true;
            }
        }
        else {
            this._fld9f = true;
        }
        if (this._fld1h == this._fld8f && this._fld9f) {
            if (this._fld0g < this._fld3f) {
                ++this._fld0g;
            }
            else {
                this._mth5b(this._fld1f = (this._fld1f + 1) % this._fld0f, this._fld7e);
                this._fld0g = 0;
                this._mth6b();
            }
        }
        this._mth7b();
        for (int i = 0; i < this._fld5 * this._fld6; ++i) {
            if (this._fld9e[i] == 0) {
                this._fld8[i] = this._fld7[i];
            }
            else {
                this._fld8[i] = this._fld9e[i];
            }
        }
        this._fld0b = this.createImage(this._fld9);
    }
    
    void _mth5b(final int n, final int[] array) {
        new MemoryImageSource(this._fld5, this._fld6, array, 0, this._fld5);
        final Image image = this.createImage(this._fld5, this._fld6);
        final Graphics graphics = image.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this._fld5, this._fld6);
        graphics.setColor(Color.white);
        graphics.setFont(this._fld2e[n]);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this._fld2e[n]);
        final int stringWidth = fontMetrics.stringWidth(this._fld3e[n]);
        final int height = fontMetrics.getHeight();
        final int n2 = (height >> 1) + (height >> 3);
        if (this._fld4e[n] == -10000) {
            this._fld4e[n] = (this._fld5 >> 1) - (stringWidth >> 1);
        }
        if (this._fld5e[n] == -10000) {
            this._fld5e[n] = (this._fld6 >> 1) - (n2 >> 1);
        }
        this._fld1g = this._fld4e[n] + (stringWidth >> 1);
        this._fld2g = this._fld5e[n] + (height >> 1);
        graphics.drawString(this._fld3e[n], this._fld4e[n], this._fld5e[n] + n2);
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this._fld5, this._fld6, array, 0, this._fld5);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        for (int i = 0; i < this._fld5 * this._fld6; ++i) {
            if ((array[i] & 0xFF) == 0xFF) {
                array[i] = this._fld6e[n];
            }
            else {
                array[i] = 0;
            }
        }
    }
    
    void _mth6b() {
        int fld0h = 0;
        double n = 5.0;
        double n2 = 0.0;
        double n3 = 0.0;
        int n4 = 0;
        final int n5 = (int)(Math.random() * 3.0);
        for (int i = 0; i < this._fld6; ++i) {
            for (int j = 0; j < this._fld5; ++j) {
                switch (n5) {
                    case 0: {
                        n = Math.random() * 8.0 + this._fld2f;
                        n2 = this._fld5 - 1;
                        n3 = 0.0;
                        n4 = 24 * (this._fld5 - j) / this._fld5;
                        break;
                    }
                    case 1: {
                        n = Math.random() * 8.0 + this._fld2f;
                        n2 = 0.0;
                        n3 = 0.0;
                        n4 = 24 * j / this._fld5;
                        break;
                    }
                    case 2: {
                        n = Math.random() * 8.0 + this._fld2f;
                        n2 = this._fld5 >> 1;
                        n3 = 0.0;
                        n4 = 24 * j / this._fld5;
                        break;
                    }
                }
                this._fld3g[fld0h] = true;
                this._fld4g[fld0h] = n2;
                this._fld5g[fld0h] = n3;
                this._fld6g[fld0h] = j;
                this._fld7g[fld0h] = i;
                this._fld8g[fld0h] = Math.max(1.0, n);
                this._fld9g[fld0h] = n4;
                ++fld0h;
            }
        }
        this._fld0h = fld0h;
    }
    
    void _mth7b() {
        this._fld1h = 0;
        for (int i = 0; i < this._fld0h; ++i) {
            if (this._fld4g[i] >= 0.0 && this._fld5g[i] >= 0.0 && this._fld5g[i] < this._fld6 && this._fld4g[i] < this._fld5) {
                this._fld9e[(int)this._fld5g[i] * this._fld5 + (int)this._fld4g[i]] = this._fld7e[i];
            }
            else {
                this._fld3g[i] = false;
            }
            if (!this._fld3g[i]) {
                ++this._fld1h;
            }
            else {
                this._mth8b(i);
            }
        }
    }
    
    void _mth8b(final int n) {
        if (!this._fld3g[n]) {
            return;
        }
        if (this._fld9g[n] > 0) {
            final int[] fld9g = this._fld9g;
            --fld9g[n];
            return;
        }
        final double n2 = this._fld6g[n] - this._fld4g[n];
        final double n3 = this._fld7g[n] - this._fld5g[n];
        if (Math.abs(n2) + Math.abs(n3) < 2.0 * this._fld8g[n]) {
            this._fld4g[n] = this._fld6g[n];
            this._fld5g[n] = this._fld7g[n];
            this._fld3g[n] = false;
            return;
        }
        final double n4 = this._fld8g[n] / Math.sqrt(n2 * n2 + n3 * n3);
        final double[] fld4g = this._fld4g;
        fld4g[n] += n4 * n2;
        final double[] fld5g = this._fld5g;
        fld5g[n] += n4 * n3;
    }
    
    void _mth9b() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this._fld2h.length() > 9) {
            final int n = this._fld2h.length() - 9;
            final int n2 = n + 9;
            this._fld8h = new byte[n];
            this._fld2h.getBytes(1, n + 1, this._fld8h, 0);
            this._fld9h = new byte[n2];
            this._fld2h.getBytes(0, n2, this._fld9h, 0);
            int n3 = n % 7;
            final int n4 = n % 3;
            for (int i = 0; i < n; ++i) {
                final byte b = this._fld8h[i];
                final byte b2 = (byte)(b + n3);
                if (b >= 48 && b <= 57) {
                    this._fld8h[i] = ((b2 <= 57) ? b2 : ((byte)(b2 - 10)));
                }
                else if (b >= 65 && b <= 90) {
                    this._fld8h[i] = ((b2 <= 90) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b >= 97 && b <= 122) {
                    this._fld8h[i] = ((b2 <= 122) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b == 42) {
                    this._fld8h[i] = 45;
                }
                else if (b == 45) {
                    this._fld8h[i] = 46;
                }
                n3 = (n3 + n4) % 7;
            }
            int n5 = n % 7;
            final int n6 = n % 3;
            for (int j = 0; j < n; ++j) {
                final byte b3 = this._fld8h[j];
                final byte b4 = (byte)(b3 - n5);
                if (b3 >= 48 && b3 <= 57) {
                    this._fld9h[j + 1] = ((b4 >= 48) ? b4 : ((byte)(b4 + 10)));
                }
                else if (b3 >= 65 && b3 <= 90) {
                    this._fld9h[j + 1] = ((b4 >= 65) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 >= 97 && b3 <= 122) {
                    this._fld9h[j + 1] = ((b4 >= 97) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 == 45) {
                    this._fld9h[j + 1] = 42;
                }
                else if (b3 == 46) {
                    this._fld9h[j + 1] = 45;
                }
                n5 = (n5 + n6) % 7;
            }
            byte[] array;
            if (n > 4) {
                array = new byte[n - 4];
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld8h[k + 4];
                }
            }
            else {
                array = new byte[] { 0 };
            }
            if (this._fld9h[0] == this._fld9h[n >> 1] && this._fld9h[1 + n] == this._fld9h[1] && this._fld9h[1 + n + 1] == this._fld9h[n >> 1] && this._fld9h[1 + n + 2] == (byte)(97 + n5) && this._fld9h[1 + n + 3] == 45 && this._fld9h[1 + n + 4] == (byte)(122 - n6) && this._fld9h[1 + n + 5] == (byte)(110 + n5) && this._fld9h[1 + n + 6] == this._fld9h[1] && this._fld9h[1 + n + 7] == this._fld9h[n] && (host.equalsIgnoreCase(new String(this._fld8h, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this._fld7h = true;
            }
        }
        try {
            this._fld6h = new URL("http://" + this._fld7b);
        }
        catch (MalformedURLException ex) {
            this._fld6h = null;
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld7h) {
            this.getAppletContext().showDocument(this._fld6h, "_blank");
        }
        else if (this._fld3h[this._fld1f] != null) {
            if (this._fld4h[this._fld1f] != null) {
                this.getAppletContext().showDocument(this._fld3h[this._fld1f], this._fld4h[this._fld1f]);
            }
            else {
                this.getAppletContext().showDocument(this._fld3h[this._fld1f]);
            }
        }
        return true;
    }
    
    void _mth0c() {
        int fld0d = 0;
        do {
            ++fld0d;
        } while (this.getParameter("overtext" + fld0d) != null);
        if (--fld0d > 0) {
            this._fld0c = true;
            this._fld0d = fld0d;
            this._fld0i = new String[this._fld0d];
            this._fld1i = new Color[this._fld0d];
            this._fld2i = new Color[this._fld0d];
            this._fld3i = new Font[this._fld0d];
            this._fld4i = new FontMetrics[this._fld0d];
            this._fld5i = new String[this._fld0d];
            this._fld6i = new int[this._fld0d];
            this._fld7i = new int[this._fld0d];
            for (int i = 0; i < this._fld0d; ++i) {
                this._fld0i[i] = this.getParameter("overtext" + String.valueOf(i + 1));
                this._fld1i[i] = this._mth1b("overtextcol" + String.valueOf(i + 1), new Color(16777215));
                this._fld2i[i] = this._mth1b("overtextcols" + String.valueOf(i + 1), new Color(0));
                final String parameter = this.getParameter("overtexty" + String.valueOf(i + 1));
                if (parameter == null) {
                    this._fld7i[i] = 10;
                }
                else {
                    this._fld7i[i] = Integer.parseInt(parameter);
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
                this._fld3i[i] = new Font(parameter2, n, int1);
                this._fld4i[i] = this._fld3c.getFontMetrics(this._fld3i[i]);
                this._fld5i[i] = this.getParameter("overTextType" + String.valueOf(i + 1));
                if (this._fld5i[i] == null) {
                    this._fld5i[i] = "scrollleft";
                }
                final String parameter5 = this.getParameter("overtextspeed" + String.valueOf(i + 1));
                if (parameter5 == null) {
                    this._fld6i[i] = 2;
                }
                else {
                    this._fld6i[i] = Integer.valueOf(parameter5);
                    if (this._fld6i[i] < 1 || this._fld6i[i] > 4) {
                        this._fld6i[i] = 2;
                    }
                }
            }
            this._mth1c();
        }
    }
    
    void _mth1c() {
        this._fld3d = this._fld4i[this._fld9c].stringWidth(this._fld0i[this._fld9c]);
        this._fld4d = this._fld4i[this._fld9c].getHeight();
        if (this._fld5i[this._fld9c].equalsIgnoreCase("scrolldown")) {
            this._fld1d = this._fld5 - this._fld3d >> 1;
            this._fld2d = 0;
            return;
        }
        if (this._fld5i[this._fld9c].equalsIgnoreCase("scrollup")) {
            this._fld1d = this._fld5 - this._fld3d >> 1;
            this._fld2d = this._fld6 + this._fld4d;
            return;
        }
        if (this._fld5i[this._fld9c].equalsIgnoreCase("scrollright")) {
            this._fld1d = -this._fld3d;
            this._fld2d = this._fld7i[this._fld9c] + (this._fld4d >> 1) + (this._fld4d >> 3);
            return;
        }
        this._fld1d = this._fld5;
        this._fld2d = this._fld7i[this._fld9c] + (this._fld4d >> 1) + (this._fld4d >> 3);
    }
    
    void _mth2c(final Graphics graphics) {
        graphics.setFont(this._fld3i[this._fld9c]);
        graphics.setColor(this._fld2i[this._fld9c]);
        graphics.drawString(this._fld0i[this._fld9c], this._fld1d + 1, this._fld2d + 1);
        graphics.setColor(this._fld1i[this._fld9c]);
        graphics.drawString(this._fld0i[this._fld9c], this._fld1d, this._fld2d);
        if (this._fld5i[this._fld9c].equalsIgnoreCase("scrolldown")) {
            this._fld2d += this._fld6i[this._fld9c];
        }
        else if (this._fld5i[this._fld9c].equalsIgnoreCase("scrollup")) {
            this._fld2d -= this._fld6i[this._fld9c];
        }
        else if (this._fld5i[this._fld9c].equalsIgnoreCase("scrollright")) {
            this._fld1d += this._fld6i[this._fld9c];
        }
        else {
            this._fld1d -= this._fld6i[this._fld9c];
        }
        if (this._fld2d > this._fld6 + this._fld4d || this._fld2d < -this._fld4d || this._fld1d > this._fld5 || this._fld1d < -this._fld3d) {
            ++this._fld9c;
            if (this._fld9c >= this._fld0d) {
                this._fld9c = 0;
            }
            this._mth1c();
        }
    }
    
    void _mth3c() {
        final String parameter = this.getParameter("OverImage");
        if (parameter != null) {
            this._fld4c = this._mth9(parameter);
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
    
    public DS_DustText() {
        this._fld4 = new Font("Helvetica", 1, 12);
        this._fld3b = "Applet by Dario Sciacca";
        this._fld4b = "dario@dseffects.com";
        this._fld7b = "www.dseffects.com";
        this._fld8b = "Don't remove Dario Sciacca's credits line";
        this._fld9b = this._fld3b + " (" + this._fld7b + ")";
        this._fld0c = false;
        this._fld1c = false;
        this._fld8d = "DustText started";
        this._fld0e = false;
        this._fld1e = true;
        this._fld2h = "";
        this._fld7h = false;
    }
}
