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

public class DS_FireTunnel extends Applet implements Runnable
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
    int _fld2d;
    int _fld3d;
    int _fld4d;
    int _fld5d;
    int _fld6d;
    int _fld7d;
    int _fld8d;
    String _fld9d;
    int _fld0e;
    boolean _fld1e;
    int _fld2e;
    int _fld3e;
    int _fld4e;
    int _fld5e;
    int _fld6e;
    int _fld7e;
    int _fld8e;
    int _fld9e;
    int[] _fld0f;
    int[] fb;
    int _fld1f;
    int[] _fld2f;
    int _fld3f;
    int[] _fld4f;
    int _fld5f;
    int _fld6f;
    String _fld7f;
    URL _fld8f;
    String _fld9f;
    String _fld0g;
    URL _fld1g;
    boolean _fld2g;
    byte[] _fld3g;
    byte[] _fld4g;
    String[] _fld5g;
    Color[] _fld6g;
    Color[] _fld7g;
    Font[] _fld8g;
    FontMetrics[] _fld9g;
    String[] _fld0h;
    int[] _fld1h;
    int[] _fld2h;
    
    public String getAppletInfo() {
        return "DS FireTunnel v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
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
        this._mth8();
        this._mth8b();
        this._mth5();
        this._mth9();
        if (this._fld6d == -16777216) {
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
        this.showStatus(this._fld9d);
        System.gc();
        final Graphics graphics = this.getGraphics();
        this._fld1b = System.currentTimeMillis();
        while (this._fld0 != null) {
            if (this._fld1 == 1) {
                this._mth0b();
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
        if (this._fld1e && !this._fld2g) {
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
        this.showStatus(this._fld0g);
        return this._fld1e = true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this._fld1e = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int fld7d, final int fld8d) {
        if (this._fld7d < fld7d) {
            this._fld7e = this._fld5e;
        }
        else if (this._fld7d > fld7d) {
            this._fld7e = -this._fld5e;
        }
        else if (this._fld8d < fld8d) {
            this._fld7e = 0;
            this._fld8e = -this._fld6e;
        }
        else if (this._fld8d > fld8d) {
            this._fld7e = 0;
            this._fld8e = this._fld6e;
        }
        this._fld7d = fld7d;
        this._fld8d = fld8d;
        return this._fld1e = true;
    }
    
    void _mth3() {
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
    
    void _mth4() {
        this._fld5d = 1;
        for (int i = 0; i < 11; ++i) {
            if (this._fld3b.charAt(i) == this._fld2b.charAt(i) || this._fld4d == 0) {
                while (true) {
                    this.showStatus(this._fld7b);
                }
            }
            else {}
        }
        this._fld2g = false;
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
    
    Image _mth6(final String s) {
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
    
    Color _mth7(final String s, final Color color) {
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
    
    void _mth8() {
        String parameter = this.getParameter("speed");
        if (parameter == null) {
            parameter = "2";
        }
        this._fld2e = Integer.valueOf(parameter);
        this._fld2e = ((this._fld2e >= 1) ? ((this._fld2e <= 8) ? this._fld2e : 8) : 1);
        final String parameter2 = this.getParameter("direction");
        if (parameter2 == null) {
            this._fld3e = this._fld2e;
        }
        else if (parameter2.equalsIgnoreCase("backward")) {
            this._fld3e = -this._fld2e;
        }
        else {
            this._fld3e = this._fld2e;
        }
        final String parameter3 = this.getParameter("rotdir");
        if (parameter3 == null) {
            this._fld4e = -this._fld2e;
        }
        else if (parameter3.equalsIgnoreCase("COUNTERCLOCKWISE")) {
            this._fld4e = this._fld2e;
        }
        else if (parameter3.equalsIgnoreCase("CLOCKWISE")) {
            this._fld4e = -this._fld2e;
        }
        else {
            this._fld4e = 0;
        }
        final String parameter4 = this.getParameter("interactive");
        if (parameter4 == null) {
            this._fld0e = 0;
        }
        else if (parameter4.equalsIgnoreCase("NO")) {
            this._fld0e = 1;
        }
        else {
            this._fld0e = 0;
        }
        this._fld0g = this._fld2b;
        final String parameter5 = this.getParameter("regkey");
        if (parameter5 != null) {
            this._fld7f = parameter5;
            final String parameter6 = this.getParameter("reglink");
            if (parameter6 != null) {
                try {
                    this._fld8f = new URL("http://" + parameter6);
                }
                catch (MalformedURLException ex) {
                    this._fld8f = null;
                }
                final String parameter7 = this.getParameter("regtarget");
                if (parameter7 != null) {
                    this._fld9f = parameter7;
                }
            }
            final String parameter8 = this.getParameter("regstatusmsg");
            if (parameter8 != null) {
                this._fld0g = parameter8;
            }
        }
    }
    
    void _mth9() {
        this._mth6b();
        this._fld5e = this._fld4e;
        this._fld6e = this._fld3e * this._fld4;
        this._fld7e = this._fld5e;
        this._fld8e = this._fld6e;
        this._fld6 = new int[this._fld4 * this._fld5];
        this._fld1f = this._fld5;
        this._fld2f = new int[this._fld1f];
        this._mth1b();
        this._mth2b();
        this._mth6b();
        this._fld9e = 0;
    }
    
    void _mth0b() {
        if (this._fld0e == 1) {
            this._fld7e = this._fld5e;
            this._fld8e = this._fld6e;
        }
        this._fld9e = (this._fld9e + 1) % this._fld5;
        this._mth4b();
        this._mth7b();
        this._fld9 = this.createImage(this._fld8);
    }
    
    void _mth1b() {
        this._fld0f = new int[this._fld4 * (this._fld5 + 2)];
        this.fb = new int[this._fld4 * (this._fld5 + 2)];
        for (int i = 0; i < this._fld5; ++i) {
            for (int j = 0; j < this._fld4; ++j) {
                this._fld0f[i * this._fld4 + j] = (int)(Math.random() * this._fld1f);
            }
        }
    }
    
    void _mth2b() {
        final int n = this._fld1f >> 2;
        final int n2 = this._fld1f >> 1;
        final int fld1f = this._fld1f;
        this._mth3b(0, n, 0, 255, 0, 0, 0, 0);
        this._mth3b(n, n2, 255, 255, 0, 255, 0, 0);
        this._mth3b(n2, fld1f, 255, 255, 255, 255, 0, 255);
    }
    
    void _mth3b(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        final int n9 = n2 - n;
        final int n10 = n4 - n3;
        final int n11 = n6 - n5;
        final int n12 = n8 - n7;
        for (int i = 0; i < n9; ++i) {
            this._fld2f[n + i] = (0xFF000000 | n3 + i * n10 / n9 << 16 | n5 + i * n11 / n9 << 8 | n7 + i * n12 / n9);
        }
    }
    
    void _mth4b() {
        final int n = this._fld4 * this._fld5;
        final int n2 = this._fld4 * this._fld5 + this._fld4;
        final int n3 = this._fld4 * this._fld9e;
        final int n4 = this._fld4 * (this._fld9e + 1);
        for (int i = 0; i < this._fld4; ++i) {
            this.fb[n + i] = this._fld0f[n3 + i];
            this.fb[n2 + i] = this._fld0f[n4 + i];
        }
        final int n5 = this._fld4 << 1;
        for (int j = this._fld4; j < this._fld4 * this._fld5; ++j) {
            final int n6 = this.fb[j + n5] + this.fb[j - 1 + this._fld4] + this.fb[j + this._fld4] + this.fb[j + 1 + this._fld4] >> 2;
            this.fb[j] = ((n6 <= 0) ? 0 : (n6 - 1));
        }
        this._mth5b();
    }
    
    void _mth5b() {
        int n = this._fld4 * (this._fld5 - 4) >> 1;
        for (int n2 = this._fld5 >> 1, i = 0, n3 = this._fld5 - 1; i < n2; ++i, --n3) {
            for (int j = 0; j < this._fld4; ++j) {
                final int n4 = this._fld2f[this.fb[n++]];
                this._fld6[i * this._fld4 + j] = n4;
                this._fld6[n3 * this._fld4 + j] = n4;
            }
        }
    }
    
    void _mth6b() {
        this._fld4f = new int[this._fld4 * this._fld5];
        final int n = this._fld4 >> 1;
        final int n2 = this._fld5 >> 1;
        for (int i = 0; i < this._fld5; ++i) {
            for (int j = 0; j < this._fld4; ++j) {
                final int n3 = j - n;
                final int n4 = i - n2;
                double atan2;
                if (n3 != 0 || n4 != 0) {
                    atan2 = Math.atan2(n4, n3);
                }
                else {
                    atan2 = 1.0;
                }
                final double n5 = atan2 / 3.141592653589793 * n;
                double sqrt = Math.sqrt(n3 * n3 + n4 * n4);
                if (sqrt == 0.0) {
                    sqrt = 1.0;
                }
                double n6 = this._fld5 / sqrt * this._fld3f;
                if (n6 > this._fld4) {
                    n6 /= 5.0;
                }
                this._fld4f[this._fld4 * i + j] = (int)n6 * this._fld4 + (int)n5;
            }
        }
    }
    
    void _mth7b() {
        final int n = this._fld4 * this._fld5;
        this._fld5f = (this._fld5f + this._fld4 + this._fld7e) % this._fld4;
        this._fld6f = (this._fld6f + n + this._fld8e) % n;
        for (int i = 0; i < n; ++i) {
            this._fld7[i] = this._fld6[(this._fld4f[i] + this._fld5f + this._fld6f) % n];
        }
    }
    
    void _mth8b() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this._fld7f.length() > 9) {
            final int n = this._fld7f.length() - 9;
            final int n2 = n + 9;
            this._fld3g = new byte[n];
            this._fld7f.getBytes(1, n + 1, this._fld3g, 0);
            this._fld4g = new byte[n2];
            this._fld7f.getBytes(0, n2, this._fld4g, 0);
            int n3 = n % 7;
            final int n4 = n % 3;
            for (int i = 0; i < n; ++i) {
                final byte b = this._fld3g[i];
                final byte b2 = (byte)(b + n3);
                if (b >= 48 && b <= 57) {
                    this._fld3g[i] = ((b2 <= 57) ? b2 : ((byte)(b2 - 10)));
                }
                else if (b >= 65 && b <= 90) {
                    this._fld3g[i] = ((b2 <= 90) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b >= 97 && b <= 122) {
                    this._fld3g[i] = ((b2 <= 122) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b == 42) {
                    this._fld3g[i] = 45;
                }
                else if (b == 45) {
                    this._fld3g[i] = 46;
                }
                n3 = (n3 + n4) % 7;
            }
            int n5 = n % 7;
            final int n6 = n % 3;
            for (int j = 0; j < n; ++j) {
                final byte b3 = this._fld3g[j];
                final byte b4 = (byte)(b3 - n5);
                if (b3 >= 48 && b3 <= 57) {
                    this._fld4g[j + 1] = ((b4 >= 48) ? b4 : ((byte)(b4 + 10)));
                }
                else if (b3 >= 65 && b3 <= 90) {
                    this._fld4g[j + 1] = ((b4 >= 65) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 >= 97 && b3 <= 122) {
                    this._fld4g[j + 1] = ((b4 >= 97) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 == 45) {
                    this._fld4g[j + 1] = 42;
                }
                else if (b3 == 46) {
                    this._fld4g[j + 1] = 45;
                }
                n5 = (n5 + n6) % 7;
            }
            final byte[] array = new byte[n];
            if (n > 4) {
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld3g[k + 4];
                }
            }
            if (this._fld4g[0] == this._fld4g[n >> 1] && this._fld4g[1 + n] == this._fld4g[1] && this._fld4g[1 + n + 1] == this._fld4g[n >> 1] && this._fld4g[1 + n + 2] == (byte)(97 + n5) && this._fld4g[1 + n + 3] == 45 && this._fld4g[1 + n + 4] == (byte)(122 - n6) && this._fld4g[1 + n + 5] == (byte)(110 + n5) && this._fld4g[1 + n + 6] == this._fld4g[1] && this._fld4g[1 + n + 7] == this._fld4g[n] && (host.equalsIgnoreCase(new String(this._fld3g, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this._fld2g = true;
            }
        }
        try {
            this._fld1g = new URL("http://" + this._fld6b);
        }
        catch (MalformedURLException ex) {
            this._fld1g = null;
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld2g) {
            this.getAppletContext().showDocument(this._fld1g, "_blank");
        }
        else if (this._fld8f != null) {
            if (this._fld9f != null) {
                this.getAppletContext().showDocument(this._fld8f, this._fld9f);
            }
            else {
                this.getAppletContext().showDocument(this._fld8f);
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
            this._fld5g = new String[this._fld9c];
            this._fld6g = new Color[this._fld9c];
            this._fld7g = new Color[this._fld9c];
            this._fld8g = new Font[this._fld9c];
            this._fld9g = new FontMetrics[this._fld9c];
            this._fld0h = new String[this._fld9c];
            this._fld1h = new int[this._fld9c];
            this._fld2h = new int[this._fld9c];
            for (int i = 0; i < this._fld9c; ++i) {
                this._fld5g[i] = this.getParameter("overtext" + String.valueOf(i + 1));
                this._fld6g[i] = this._mth7("overtextcol" + String.valueOf(i + 1), new Color(16777215));
                this._fld7g[i] = this._mth7("overtextcols" + String.valueOf(i + 1), new Color(0));
                final String parameter = this.getParameter("overtexty" + String.valueOf(i + 1));
                if (parameter == null) {
                    this._fld2h[i] = 10;
                }
                else {
                    this._fld2h[i] = Integer.parseInt(parameter);
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
                this._fld8g[i] = new Font(parameter2, n, int1);
                this._fld9g[i] = this._fld2c.getFontMetrics(this._fld8g[i]);
                this._fld0h[i] = this.getParameter("overTextType" + String.valueOf(i + 1));
                if (this._fld0h[i] == null) {
                    this._fld0h[i] = "scrollleft";
                }
                final String parameter5 = this.getParameter("overtextspeed" + String.valueOf(i + 1));
                if (parameter5 == null) {
                    this._fld1h[i] = 2;
                }
                else {
                    this._fld1h[i] = Integer.valueOf(parameter5);
                    if (this._fld1h[i] < 1 || this._fld1h[i] > 4) {
                        this._fld1h[i] = 2;
                    }
                }
            }
            this._mth0c();
        }
    }
    
    void _mth0c() {
        this._fld2d = this._fld9g[this._fld8c].stringWidth(this._fld5g[this._fld8c]);
        this._fld3d = this._fld9g[this._fld8c].getHeight();
        if (this._fld0h[this._fld8c].equalsIgnoreCase("scrolldown")) {
            this._fld0d = this._fld4 - this._fld2d >> 1;
            this._fld1d = 0;
            return;
        }
        if (this._fld0h[this._fld8c].equalsIgnoreCase("scrollup")) {
            this._fld0d = this._fld4 - this._fld2d >> 1;
            this._fld1d = this._fld5 + this._fld3d;
            return;
        }
        if (this._fld0h[this._fld8c].equalsIgnoreCase("scrollright")) {
            this._fld0d = -this._fld2d;
            this._fld1d = this._fld2h[this._fld8c] + (this._fld3d >> 1) + (this._fld3d >> 3);
            return;
        }
        this._fld0d = this._fld4;
        this._fld1d = this._fld2h[this._fld8c] + (this._fld3d >> 1) + (this._fld3d >> 3);
    }
    
    void _mth1c(final Graphics graphics) {
        graphics.setFont(this._fld8g[this._fld8c]);
        graphics.setColor(this._fld7g[this._fld8c]);
        graphics.drawString(this._fld5g[this._fld8c], this._fld0d + 1, this._fld1d + 1);
        graphics.setColor(this._fld6g[this._fld8c]);
        graphics.drawString(this._fld5g[this._fld8c], this._fld0d, this._fld1d);
        if (this._fld0h[this._fld8c].equalsIgnoreCase("scrolldown")) {
            this._fld1d += this._fld1h[this._fld8c];
        }
        else if (this._fld0h[this._fld8c].equalsIgnoreCase("scrollup")) {
            this._fld1d -= this._fld1h[this._fld8c];
        }
        else if (this._fld0h[this._fld8c].equalsIgnoreCase("scrollright")) {
            this._fld0d += this._fld1h[this._fld8c];
        }
        else {
            this._fld0d -= this._fld1h[this._fld8c];
        }
        if (this._fld1d > this._fld5 + this._fld3d || this._fld1d < -this._fld3d || this._fld0d > this._fld4 || this._fld0d < -this._fld2d) {
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
            this._fld3c = this._mth6(parameter);
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
    
    public DS_FireTunnel() {
        this._fld3 = new Font("Helvetica", 1, 12);
        this._fld2b = "Applet by Dario Sciacca";
        this._fld3b = "dario@dseffects.com";
        this._fld6b = "www.dseffects.com";
        this._fld7b = "Don't remove Dario Sciacca's credits line";
        this._fld8b = this._fld2b + " (" + this._fld6b + ")";
        this._fld9b = false;
        this._fld0c = false;
        this._fld9d = "FireTunnel started";
        this._fld1e = false;
        this._fld3f = 12;
        this._fld7f = "";
        this._fld9f = "_blank";
        this._fld0g = "Applet by Dario Sciacca";
        this._fld2g = false;
    }
}
