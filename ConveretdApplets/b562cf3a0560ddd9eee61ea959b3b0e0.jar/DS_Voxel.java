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

public class DS_Voxel extends Applet implements Runnable
{
    Thread _fld0;
    int _fld3;
    Font _fld4;
    int _fld5;
    int _fld6;
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
    int[] _fld5e;
    int[] _fld6e;
    int _fld7e;
    double _fld8e;
    double _fld9e;
    double _fld0f;
    int x0;
    int y0;
    int[] _fld1f;
    int[] _fld2f;
    int[] _fld3f;
    int[] _fld4f;
    double _fld5f;
    String _fld6f;
    URL _fld7f;
    String _fld8f;
    String _fld9f;
    URL _fld0g;
    boolean _fld1g;
    byte[] _fld2g;
    byte[] _fld3g;
    String[] _fld4g;
    Color[] _fld5g;
    Color[] _fld6g;
    Font[] _fld7g;
    FontMetrics[] _fld8g;
    String[] _fld9g;
    int[] _fld0h;
    int[] _fld1h;
    
    public String getAppletInfo() {
        return "DS Voxel v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
    }
    
    public void init() {
        this._mth4();
        this.showStatus("Please wait ...");
        this._fld5b = this.getFontMetrics(this._fld4).stringWidth(this._fld6b);
        this._fld5 = this.size().width;
        this._fld6 = this.size().height;
        this._mth5();
        this._fld7 = new int[this._fld5 * this._fld6];
        this._fld8 = new MemoryImageSource(this._fld5, this._fld6, this._fld7, 0, this._fld5);
        this._fld1c = this.createImage(this._fld5, this._fld6);
        this._fld2c = this._fld1c.getGraphics();
        this._mth8b();
        this._mth1c();
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
        this._mth9();
        this._mth7b();
        this._mth6();
        this._mth0b();
        if (this._fld6d == -16777216) {
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
        this.showStatus(this._fld9d);
        System.gc();
        final Graphics graphics = this.getGraphics();
        this._fld1b = System.currentTimeMillis();
        while (this._fld0 != null) {
            if (this._fld3 == 1) {
                this._mth1b();
            }
            this._mth3(graphics);
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
    
    void _mth3(final Graphics graphics) {
        final int n = this._fld5 >> 1;
        final int n2 = this._fld6 >> 1;
        if (this._fld3 == 0) {
            graphics.drawString("Error ...", 10, 10);
            return;
        }
        if (this._fld9 != null) {
            if (this._fld4b == 0) {
                this._fld2c.drawImage(this._fld9, 0, 0, this);
            }
            else if (this._fld4b == 1) {
                this._fld2c.drawImage(this._fld9, 0, 0, this);
                this._mth0c(this._fld2c);
            }
            else if (this._fld4b == 2) {
                this._fld2c.drawImage(this._fld9, 0, 0, this);
                this._fld2c.drawImage(this._fld3c, this._fld4c, this._fld5c, this);
            }
            else {
                this._fld2c.drawImage(this._fld9, 0, 0, this);
                this._mth0c(this._fld2c);
                this._fld2c.drawImage(this._fld3c, this._fld4c, this._fld5c, this);
            }
        }
        if (this._fld1e && !this._fld1g) {
            this._fld2c.setColor(Color.white);
            this._fld2c.drawLine(n - 64, n2 - 8, n + 64, n2 - 8);
            this._fld2c.drawLine(n - 64, n2 + 8, n + 64, n2 + 8);
            this._fld2c.drawLine(n - 64, n2 - 8, n - 64, n2 + 8);
            this._fld2c.drawLine(n + 64, n2 - 8, n + 64, n2 + 8);
            this._fld2c.setColor(Color.blue);
            this._fld2c.fillRect(n - 63, n2 - 7, 127, 15);
            this._fld2c.setFont(this._fld4);
            this._fld2c.setColor(Color.yellow);
            this._fld2c.drawString(this._fld6b, n - (this._fld5b >> 1), n2 + 5);
        }
        graphics.drawImage(this._fld1c, 0, 0, this);
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this._fld9f);
        return this._fld1e = true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this._fld1e = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int fld7d, final int fld8d) {
        if (this._fld7d < fld7d) {
            this._fld9e += 0.01;
        }
        else if (this._fld7d > fld7d) {
            this._fld9e -= 0.01;
        }
        else {
            this._fld9e = 0.0;
        }
        if (this._fld8d < fld8d) {
            this._fld8e -= this._fld2e;
        }
        else if (this._fld8d > fld8d) {
            this._fld8e += this._fld2e;
        }
        else {
            this._fld8e = 0.0;
        }
        this._fld7d = fld7d;
        this._fld8d = fld8d;
        return this._fld1e = true;
    }
    
    void _mth4() {
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
    
    void _mth5() {
        this._fld5d = 1;
        for (int i = 0; i < 11; ++i) {
            if (this._fld3b.charAt(i) == this._fld2b.charAt(i) || this._fld4d == 0) {
                while (true) {
                    this.showStatus(this._fld7b);
                }
            }
            else {}
        }
        this._fld1g = false;
    }
    
    void _mth6() {
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
        String parameter = this.getParameter("terrainpal");
        if (parameter == null) {
            parameter = "1";
        }
        this._fld3e = Integer.valueOf(parameter);
        this._fld3e = ((this._fld3e >= 1) ? ((this._fld3e <= 3) ? this._fld3e : 3) : 1);
        String parameter2 = this.getParameter("skypal");
        if (parameter2 == null) {
            parameter2 = "1";
        }
        this._fld4e = Integer.valueOf(parameter2);
        this._fld4e = ((this._fld4e >= 1) ? ((this._fld4e <= 3) ? this._fld4e : 3) : 1);
        String parameter3 = this.getParameter("speed");
        if (parameter3 == null) {
            parameter3 = "2";
        }
        this._fld2e = Integer.valueOf(parameter3);
        this._fld2e = ((this._fld2e >= 1) ? ((this._fld2e <= 4) ? this._fld2e : 4) : 1);
        final String parameter4 = this.getParameter("interactive");
        if (parameter4 == null) {
            this._fld0e = 0;
        }
        else if (parameter4.equalsIgnoreCase("YES")) {
            this._fld0e = 0;
        }
        else {
            this._fld0e = 1;
        }
        this._fld9f = this._fld2b;
        final String parameter5 = this.getParameter("regkey");
        if (parameter5 != null) {
            this._fld6f = parameter5;
            final String parameter6 = this.getParameter("reglink");
            if (parameter6 != null) {
                try {
                    this._fld7f = new URL("http://" + parameter6);
                }
                catch (MalformedURLException ex) {
                    this._fld7f = null;
                }
                final String parameter7 = this.getParameter("regtarget");
                if (parameter7 != null) {
                    this._fld8f = parameter7;
                }
            }
            final String parameter8 = this.getParameter("regstatusmsg");
            if (parameter8 != null) {
                this._fld9f = parameter8;
            }
        }
    }
    
    void _mth0b() {
        this._mth4b(this._fld1f);
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 65536; j += 256) {
                for (int k = 0; k < 256; ++k) {
                    this._fld1f[j + k] = this._fld1f[(j + 256 & 0xFF00) + k] + this._fld1f[j + (k + 1 & 0xFF)] + this._fld1f[(j - 256 & 0xFF00) + k] + this._fld1f[j + (k - 1 & 0xFF)] >> 2;
                }
            }
        }
        for (int l = 0; l < 65536; l += 256) {
            for (int n = 0; n < 256; ++n) {
                int n2 = 128 + (this._fld1f[(l + 256 & 0xFF00) + (n + 1 & 0xFF)] - this._fld1f[l + n]) * 4;
                if (n2 < 0) {
                    n2 = 0;
                }
                if (n2 > 255) {
                    n2 = 255;
                }
                this._fld2f[l + n] = n2;
            }
        }
        this._fld0f = 0.0;
        final boolean b = false;
        this.y0 = (b ? 1 : 0);
        this.x0 = (b ? 1 : 0);
        this._fld2e *= 2048;
        this._fld8e = this._fld2e * 10;
        this._fld9e = 0.0;
        this._fld3f = new int[this._fld5];
        this._fld4f = new int[this._fld5];
        switch (this._fld3e) {
            case 1: {
                this._mth2b(this._fld5e, 0, 255, 0, 255, 0, 255, 0, 64);
                break;
            }
            case 2: {
                this._mth2b(this._fld5e, 0, 255, 0, 0, 0, 255, 0, 0);
                break;
            }
            case 3: {
                this._mth2b(this._fld5e, 0, 255, 0, 255, 0, 127, 0, 0);
                break;
            }
        }
        this._fld6e = new int[this._fld6];
        switch (this._fld4e) {
            case 1: {
                this._mth2b(this._fld6e, 0, this._fld6, 0, 0, 64, 255, 128, 255);
            }
            case 2: {
                this._mth2b(this._fld6e, 0, this._fld6, 0, 0, 0, 127, 128, 255);
            }
            case 3: {
                this._mth2b(this._fld6e, 0, this._fld6, 0, 0, 0, 0, 64, 255);
            }
            default: {}
        }
    }
    
    void _mth1b() {
        if (this._fld0e == 1) {
            this._fld8e = this._fld2e * 10;
            this._fld9e = 0.0;
        }
        this.x0 += (int)(this._fld8e * Math.cos(this._fld0f));
        this.y0 += (int)(this._fld8e * Math.sin(this._fld0f));
        this._fld0f += this._fld9e;
        this._mth6b(this.x0, this.y0, this._fld0f);
        this._fld9 = this.createImage(this._fld8);
    }
    
    void _mth2b(final int[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        final int n9 = n2 - n;
        final int n10 = n4 - n3;
        final int n11 = n6 - n5;
        final int n12 = n8 - n7;
        for (int i = 0; i < n9; ++i) {
            array[n + i] = (0xFF000000 | n3 + i * n10 / n9 << 16 | n5 + i * n11 / n9 << 8 | n7 + i * n12 / n9);
        }
    }
    
    int _mth3b(final int n, final int n2) {
        final int n3 = n + (int)(Math.random() * n2) - (n2 >> 1);
        return (n3 >= 0) ? ((n3 <= 255) ? n3 : 255) : 0;
    }
    
    void _mth4b(final int[] array) {
        for (int i = 0; i < 65536; ++i) {
            array[i] = 0;
        }
        int n2;
        for (int j = 256; j > 1; j = n2) {
            final int n = j * this._fld7e;
            n2 = j >> 1;
            for (int k = 0; k < 256; k += j) {
                final int n3 = (k + j) % 256;
                for (int l = 0; l < 256; l += j) {
                    final int n4 = (l + j) % 256;
                    final int n5 = array[(k << 8) + l];
                    final int n6 = array[(n3 << 8) + l];
                    final int n7 = array[(k << 8) + n4];
                    final int n8 = array[(n3 << 8) + n4];
                    final int n9 = k + n2;
                    final int n10 = l + n2;
                    array[(n9 << 8) + n10] = this._mth3b(n5 + n6 + n7 + n8 >> 2, n);
                    array[(n3 << 8) + n10] = this._mth3b(n6 + n8 >> 1, n);
                    array[(n9 << 8) + n4] = this._mth3b(n7 + n8 >> 1, n);
                    if (k == 0) {
                        array[(n9 << 8) + l] = this._mth3b(n5 + n6 >> 1, n);
                    }
                    if (l == 0) {
                        array[(k << 8) + n10] = this._mth3b(n5 + n7 >> 1, n);
                    }
                }
            }
        }
    }
    
    void _mth5b(int n, int n2, final int n3, final int n4, final int n5, final int n6) {
        final int n7 = (n3 - n) / this._fld5;
        final int n8 = (n4 - n2) / this._fld5;
        for (int i = 0; i < this._fld5; ++i) {
            final int n9 = n >> 16 & 0xFF;
            final int n10 = n >> 8 & 0xFF;
            final int n11 = n2 >> 8 & 0xFF00;
            final int n12 = n2 >> 8 & 0xFF;
            final int n13 = n9 + 1 & 0xFF;
            final int n14 = n11 + 256 & 0xFF00;
            final int n15 = this._fld1f[n9 + n11];
            final int n16 = this._fld1f[n9 + n14];
            final int n17 = this._fld1f[n13 + n11];
            final int n18 = this._fld1f[n13 + n14];
            final int n19 = (n15 << 8) + n10 * (n17 - n15);
            final int n20 = (n19 << 8) + n12 * ((n16 << 8) + n10 * (n18 - n16) - n19) >> 16;
            final int n21 = this._fld2f[n9 + n11];
            final int n22 = this._fld2f[n9 + n14];
            final int n23 = this._fld2f[n13 + n11];
            final int n24 = this._fld2f[n13 + n14];
            final int n25 = (n21 << 8) + n10 * (n23 - n21);
            final int n26 = (n25 << 8) + n12 * ((n22 << 8) + n10 * (n24 - n22) - n25);
            int j = ((n20 - n5) * n6 >> 11) + (this._fld6 >> 1);
            final int n27 = this._fld6 - 1;
            int n28;
            if (j < (n28 = this._fld3f[i])) {
                if (this._fld4f[i] == -1) {
                    this._fld4f[i] = n26;
                }
                final int n29 = (n26 - this._fld4f[i]) / (n28 - j);
                int n30 = this._fld4f[i];
                if (n28 > n27) {
                    final int n31 = n12 - (n28 - n27) * this._fld5;
                    n30 += (n28 - n27) * n29;
                    n28 = n27;
                }
                if (j < 0) {
                    j = 0;
                }
                while (j < n28) {
                    int n32 = n28 * this._fld5 + i;
                    if (n32 < 0 || n32 >= this._fld5 * this._fld6) {
                        n32 = 0;
                    }
                    this._fld7[n32] = this._fld5e[n30 >> 17 & 0xFF];
                    n30 += n29;
                    final int n33 = n32 - this._fld5;
                    --n28;
                }
                this._fld3f[i] = j;
            }
            this._fld4f[i] = n26;
            n += n7;
            n2 += n8;
        }
    }
    
    void _mth6b(final int n, final int n2, final double n3) {
        for (int i = this._fld6 - 1; i >= 0; --i) {
            for (int j = 0; j < this._fld5; ++j) {
                this._fld7[i * this._fld5 + j] = this._fld6e[i];
            }
        }
        for (int k = 0; k < this._fld5; ++k) {
            this._fld3f[k] = this._fld6;
            this._fld4f[k] = -1;
        }
        final int n4 = n >> 16 & 0xFF;
        final int n5 = n >> 8 & 0xFF;
        final int n6 = n2 >> 8 & 0xFF00;
        final int n7 = n2 >> 8 & 0xFF;
        final int n8 = n4 + 1 & 0xFF;
        final int n9 = n6 + 256 & 0xFF00;
        final int n10 = this._fld1f[n4 + n6];
        final int n11 = this._fld1f[n4 + n9];
        final int n12 = this._fld1f[n8 + n6];
        final int n13 = this._fld1f[n8 + n9];
        final int n14 = (n10 << 8) + n5 * (n12 - n10);
        final int n15 = (n14 << 8) + n7 * ((n11 << 8) + n5 * (n13 - n11) - n14) >> 16;
        for (int l = 0; l < 100; l += 1 + (l >> 6)) {
            this._mth5b((int)(n + l * 65536 * Math.cos(n3 - this._fld5f)), (int)(n2 + l * 65536 * Math.sin(n3 - this._fld5f)), (int)(n + l * 65536 * Math.cos(n3 + this._fld5f)), (int)(n2 + l * 65536 * Math.sin(n3 + this._fld5f)), n15 - 30, 25600 / (l + 1));
        }
    }
    
    void _mth7b() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this._fld6f.length() > 9) {
            final int n = this._fld6f.length() - 9;
            final int n2 = n + 9;
            this._fld2g = new byte[n];
            this._fld6f.getBytes(1, n + 1, this._fld2g, 0);
            this._fld3g = new byte[n2];
            this._fld6f.getBytes(0, n2, this._fld3g, 0);
            int n3 = n % 7;
            final int n4 = n % 3;
            for (int i = 0; i < n; ++i) {
                final byte b = this._fld2g[i];
                final byte b2 = (byte)(b + n3);
                if (b >= 48 && b <= 57) {
                    this._fld2g[i] = ((b2 <= 57) ? b2 : ((byte)(b2 - 10)));
                }
                else if (b >= 65 && b <= 90) {
                    this._fld2g[i] = ((b2 <= 90) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b >= 97 && b <= 122) {
                    this._fld2g[i] = ((b2 <= 122) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b == 42) {
                    this._fld2g[i] = 45;
                }
                else if (b == 45) {
                    this._fld2g[i] = 46;
                }
                n3 = (n3 + n4) % 7;
            }
            int n5 = n % 7;
            final int n6 = n % 3;
            for (int j = 0; j < n; ++j) {
                final byte b3 = this._fld2g[j];
                final byte b4 = (byte)(b3 - n5);
                if (b3 >= 48 && b3 <= 57) {
                    this._fld3g[j + 1] = ((b4 >= 48) ? b4 : ((byte)(b4 + 10)));
                }
                else if (b3 >= 65 && b3 <= 90) {
                    this._fld3g[j + 1] = ((b4 >= 65) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 >= 97 && b3 <= 122) {
                    this._fld3g[j + 1] = ((b4 >= 97) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 == 45) {
                    this._fld3g[j + 1] = 42;
                }
                else if (b3 == 46) {
                    this._fld3g[j + 1] = 45;
                }
                n5 = (n5 + n6) % 7;
            }
            final byte[] array = new byte[n];
            if (n > 4) {
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld2g[k + 4];
                }
            }
            if (this._fld3g[0] == this._fld3g[n >> 1] && this._fld3g[1 + n] == this._fld3g[1] && this._fld3g[1 + n + 1] == this._fld3g[n >> 1] && this._fld3g[1 + n + 2] == (byte)(97 + n5) && this._fld3g[1 + n + 3] == 45 && this._fld3g[1 + n + 4] == (byte)(122 - n6) && this._fld3g[1 + n + 5] == (byte)(110 + n5) && this._fld3g[1 + n + 6] == this._fld3g[1] && this._fld3g[1 + n + 7] == this._fld3g[n] && (host.equalsIgnoreCase(new String(this._fld2g, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this._fld1g = true;
            }
        }
        try {
            this._fld0g = new URL("http://" + this._fld6b);
        }
        catch (MalformedURLException ex) {
            this._fld0g = null;
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld1g) {
            this.getAppletContext().showDocument(this._fld0g, "_blank");
        }
        else if (this._fld7f != null) {
            if (this._fld8f != null) {
                this.getAppletContext().showDocument(this._fld7f, this._fld8f);
            }
            else {
                this.getAppletContext().showDocument(this._fld7f);
            }
        }
        return true;
    }
    
    void _mth8b() {
        int fld9c = 0;
        do {
            ++fld9c;
        } while (this.getParameter("overtext" + fld9c) != null);
        if (--fld9c > 0) {
            this._fld9b = true;
            this._fld9c = fld9c;
            this._fld4g = new String[this._fld9c];
            this._fld5g = new Color[this._fld9c];
            this._fld6g = new Color[this._fld9c];
            this._fld7g = new Font[this._fld9c];
            this._fld8g = new FontMetrics[this._fld9c];
            this._fld9g = new String[this._fld9c];
            this._fld0h = new int[this._fld9c];
            this._fld1h = new int[this._fld9c];
            for (int i = 0; i < this._fld9c; ++i) {
                this._fld4g[i] = this.getParameter("overtext" + String.valueOf(i + 1));
                this._fld5g[i] = this._mth8("overtextcol" + String.valueOf(i + 1), new Color(16777215));
                this._fld6g[i] = this._mth8("overtextcols" + String.valueOf(i + 1), new Color(0));
                final String parameter = this.getParameter("overtexty" + String.valueOf(i + 1));
                if (parameter == null) {
                    this._fld1h[i] = 10;
                }
                else {
                    this._fld1h[i] = Integer.parseInt(parameter);
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
                this._fld7g[i] = new Font(parameter2, n, int1);
                this._fld8g[i] = this._fld2c.getFontMetrics(this._fld7g[i]);
                this._fld9g[i] = this.getParameter("overTextType" + String.valueOf(i + 1));
                if (this._fld9g[i] == null) {
                    this._fld9g[i] = "scrollleft";
                }
                final String parameter5 = this.getParameter("overtextspeed" + String.valueOf(i + 1));
                if (parameter5 == null) {
                    this._fld0h[i] = 2;
                }
                else {
                    this._fld0h[i] = Integer.valueOf(parameter5);
                    if (this._fld0h[i] < 1 || this._fld0h[i] > 4) {
                        this._fld0h[i] = 2;
                    }
                }
            }
            this._mth9b();
        }
    }
    
    void _mth9b() {
        this._fld2d = this._fld8g[this._fld8c].stringWidth(this._fld4g[this._fld8c]);
        this._fld3d = this._fld8g[this._fld8c].getHeight();
        if (this._fld9g[this._fld8c].equalsIgnoreCase("scrolldown")) {
            this._fld0d = this._fld5 - this._fld2d >> 1;
            this._fld1d = 0;
            return;
        }
        if (this._fld9g[this._fld8c].equalsIgnoreCase("scrollup")) {
            this._fld0d = this._fld5 - this._fld2d >> 1;
            this._fld1d = this._fld6 + this._fld3d;
            return;
        }
        if (this._fld9g[this._fld8c].equalsIgnoreCase("scrollright")) {
            this._fld0d = -this._fld2d;
            this._fld1d = this._fld1h[this._fld8c] + (this._fld3d >> 1) + (this._fld3d >> 3);
            return;
        }
        this._fld0d = this._fld5;
        this._fld1d = this._fld1h[this._fld8c] + (this._fld3d >> 1) + (this._fld3d >> 3);
    }
    
    void _mth0c(final Graphics graphics) {
        graphics.setFont(this._fld7g[this._fld8c]);
        graphics.setColor(this._fld6g[this._fld8c]);
        graphics.drawString(this._fld4g[this._fld8c], this._fld0d + 1, this._fld1d + 1);
        graphics.setColor(this._fld5g[this._fld8c]);
        graphics.drawString(this._fld4g[this._fld8c], this._fld0d, this._fld1d);
        if (this._fld9g[this._fld8c].equalsIgnoreCase("scrolldown")) {
            this._fld1d += this._fld0h[this._fld8c];
        }
        else if (this._fld9g[this._fld8c].equalsIgnoreCase("scrollup")) {
            this._fld1d -= this._fld0h[this._fld8c];
        }
        else if (this._fld9g[this._fld8c].equalsIgnoreCase("scrollright")) {
            this._fld0d += this._fld0h[this._fld8c];
        }
        else {
            this._fld0d -= this._fld0h[this._fld8c];
        }
        if (this._fld1d > this._fld6 + this._fld3d || this._fld1d < -this._fld3d || this._fld0d > this._fld5 || this._fld0d < -this._fld2d) {
            ++this._fld8c;
            if (this._fld8c >= this._fld9c) {
                this._fld8c = 0;
            }
            this._mth9b();
        }
    }
    
    void _mth1c() {
        final String parameter = this.getParameter("OverImage");
        if (parameter != null) {
            this._fld3c = this._mth7(parameter);
        }
        if (this._fld3c != null) {
            this._fld0c = true;
            this._fld6c = this._fld3c.getWidth(this);
            this._fld7c = this._fld3c.getHeight(this);
            final String parameter2 = this.getParameter("OverImageX");
            if (parameter2 == null) {
                this._fld4c = (this._fld5 >> 1) - (this._fld6c >> 1);
            }
            else {
                this._fld4c = Integer.valueOf(parameter2);
            }
            final String parameter3 = this.getParameter("OverImageY");
            if (parameter3 == null) {
                this._fld5c = (this._fld6 >> 1) - (this._fld7c >> 1);
                return;
            }
            this._fld5c = Integer.valueOf(parameter3);
        }
    }
    
    public DS_Voxel() {
        this._fld4 = new Font("Helvetica", 1, 12);
        this._fld2b = "Applet by Dario Sciacca";
        this._fld3b = "dario@dseffects.com";
        this._fld6b = "www.dseffects.com";
        this._fld7b = "Don't remove Dario Sciacca's credits line";
        this._fld8b = this._fld2b + " (" + this._fld6b + ")";
        this._fld9b = false;
        this._fld0c = false;
        this._fld9d = "Voxel started";
        this._fld1e = false;
        this._fld5e = new int[256];
        this._fld7e = 6;
        this._fld1f = new int[65536];
        this._fld2f = new int[65536];
        this._fld5f = 0.7853981635;
        this._fld6f = "";
        this._fld8f = "_blank";
        this._fld9f = "Applet by Dario Sciacca";
        this._fld1g = false;
    }
}
