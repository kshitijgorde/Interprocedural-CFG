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

public class DS_Explosion extends Applet implements Runnable
{
    Thread _fld0;
    int _fld1;
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
    int[] _fld1e;
    int[] _fld2e;
    int _fld3e;
    int _fld4e;
    int _fld5e;
    float[] px;
    float[] py;
    float[] _fld6e;
    float[] _fld7e;
    int[] _fld8e;
    double _fld9e;
    String _fld0f;
    URL _fld1f;
    String _fld2f;
    String _fld3f;
    URL _fld4f;
    boolean _fld5f;
    byte[] _fld6f;
    byte[] _fld7f;
    String[] _fld8f;
    Color[] _fld9f;
    Color[] _fld0g;
    Font[] _fld1g;
    FontMetrics[] _fld2g;
    String[] _fld3g;
    int[] _fld4g;
    int[] _fld5g;
    
    public String getAppletInfo() {
        return "DS Explosion v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
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
        this._mth8b();
        this._mth1c();
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
        this._mth8();
        this._mth7b();
        this._mth5();
        this._mth9();
        if (this._fld5d == -16777216) {
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
        this.showStatus(this._fld8d);
        System.gc();
        final Graphics graphics = this.getGraphics();
        this._fld0b = System.currentTimeMillis();
        while (this._fld0 != null) {
            if (this._fld1 == 1) {
                this._mth0b();
            }
            this._mth1(graphics);
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
    
    void _mth1(final Graphics graphics) {
        final int n = this._fld4 >> 1;
        final int n2 = this._fld5 >> 1;
        if (this._fld1 == 0) {
            graphics.drawString("Error ...", 10, 10);
            return;
        }
        if (this._fld8 != null) {
            if (this._fld3b == 0) {
                this._fld1c.drawImage(this._fld8, 0, 0, this);
            }
            else if (this._fld3b == 1) {
                this._fld1c.drawImage(this._fld8, 0, 0, this);
                this._mth0c(this._fld1c);
            }
            else if (this._fld3b == 2) {
                this._fld1c.drawImage(this._fld8, 0, 0, this);
                this._fld1c.drawImage(this._fld2c, this._fld3c, this._fld4c, this);
            }
            else {
                this._fld1c.drawImage(this._fld8, 0, 0, this);
                this._mth0c(this._fld1c);
                this._fld1c.drawImage(this._fld2c, this._fld3c, this._fld4c, this);
            }
        }
        if (this._fld0e && !this._fld5f) {
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
        this.showStatus(this._fld3f);
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
        this._fld5f = false;
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
        String parameter = this.getParameter("intensity");
        if (parameter == null) {
            parameter = "2";
        }
        this._fld4e = Integer.valueOf(parameter);
        this._fld4e = ((this._fld4e >= 1) ? ((this._fld4e <= 3) ? this._fld4e : 3) : 1);
        ++this._fld4e;
        String parameter2 = this.getParameter("particles");
        if (parameter2 == null) {
            parameter2 = "1500";
        }
        this._fld5e = Integer.valueOf(parameter2);
        this._fld5e = ((this._fld5e >= 100) ? ((this._fld5e <= 3000) ? this._fld5e : 3000) : 100);
        final String parameter3 = this.getParameter("interactive");
        if (parameter3 == null) {
            this._fld9d = 0;
        }
        else if (parameter3.equalsIgnoreCase("YES")) {
            this._fld9d = 0;
        }
        else {
            this._fld9d = 1;
        }
        this._fld3f = this._fld1b;
        final String parameter4 = this.getParameter("regkey");
        if (parameter4 != null) {
            this._fld0f = parameter4;
            final String parameter5 = this.getParameter("reglink");
            if (parameter5 != null) {
                try {
                    this._fld1f = new URL("http://" + parameter5);
                }
                catch (MalformedURLException ex) {
                    this._fld1f = null;
                }
                final String parameter6 = this.getParameter("regtarget");
                if (parameter6 != null) {
                    this._fld2f = parameter6;
                }
            }
            final String parameter7 = this.getParameter("regstatusmsg");
            if (parameter7 != null) {
                this._fld3f = parameter7;
            }
        }
    }
    
    void _mth9() {
        this._fld3e = 60;
        this.px = new float[this._fld5e];
        this.py = new float[this._fld5e];
        this._fld6e = new float[this._fld5e];
        this._fld7e = new float[this._fld5e];
        this._fld8e = new int[this._fld5e];
        this._fld1e = new int[this._fld4 * this._fld5];
        this._mth5b();
    }
    
    void _mth0b() {
        if (++this._fld3e > 60) {
            this._fld3e = 0;
            this._mth1b();
        }
        this._mth2b();
        this._mth3b();
        this._mth4b();
        for (int i = 0; i < this._fld4 * this._fld5; ++i) {
            this._fld6[i] = this._fld2e[this._fld1e[i]];
        }
        this._fld8 = this.createImage(this._fld7);
    }
    
    void _mth1b() {
        float n;
        float n2;
        if (this._fld0e && this._fld9d == 0) {
            n = this._fld6d;
            n2 = this._fld7d;
        }
        else {
            n = (float)(Math.random() * this._fld4);
            n2 = (float)(Math.random() * this._fld5);
        }
        final float n3 = this._fld4e / 2;
        for (int i = 0; i < this._fld5e; ++i) {
            this.px[i] = n;
            this.py[i] = n2;
            this._fld6e[i] = (float)(Math.random() * this._fld4e - n3);
            this._fld7e[i] = (float)(Math.random() * this._fld4e - n3);
            final float n4 = (float)(Math.random() * this._fld4e);
            final float n5 = this._fld6e[i];
            final float n6 = this._fld7e[i];
            final double sqrt = Math.sqrt(n5 * n5 + n6 * n6);
            double n7;
            if (sqrt == 0.0) {
                n7 = 0.0;
            }
            else {
                n7 = 1.0 / sqrt;
            }
            final float[] fld6e = this._fld6e;
            final int n8 = i;
            fld6e[n8] *= (float)(n7 * n4);
            final float[] fld7e = this._fld7e;
            final int n9 = i;
            fld7e[n9] *= (float)(n7 * n4);
            this._fld8e[i] = (int)(Math.random() * 160.0) + 50;
        }
    }
    
    void _mth2b() {
        for (int i = 0; i < this._fld5e; ++i) {
            final int n = (int)this.px[i];
            final int n2 = (int)this.py[i];
            if (n >= 0 && n < this._fld4 && n2 >= 0 && n2 < this._fld5) {
                this._fld1e[n2 * this._fld4 + n] = this._fld8e[i];
            }
        }
    }
    
    void _mth3b() {
        for (int i = 0; i < this._fld5e; ++i) {
            final float[] px = this.px;
            final int n = i;
            px[n] += this._fld6e[i];
            final float[] py = this.py;
            final int n2 = i;
            py[n2] += this._fld7e[i];
            final float[] fld7e = this._fld7e;
            final int n3 = i;
            fld7e[n3] += (float)this._fld9e;
        }
    }
    
    void _mth4b() {
        for (int i = 1; i < this._fld5 - 1; ++i) {
            final int n = (i - 1) * this._fld4;
            final int n2 = i * this._fld4;
            final int n3 = (i + 1) * this._fld4;
            for (int j = 1; j < this._fld4 - 1; ++j) {
                int n4 = this._fld1e[n + j] + this._fld1e[n3 + j] + this._fld1e[n2 + j - 1] + this._fld1e[n2 + j + 1] >> 2;
                n4 -= 2;
                if (n4 < 0) {
                    n4 = 0;
                }
                this._fld1e[n2 + j] = n4;
            }
        }
        final int n5 = this._fld4 * this._fld5;
        final int n6 = n5 - this._fld4;
        for (int k = 0; k < this._fld4; ++k) {
            this._fld1e[k] = 0;
        }
        for (int l = 1; l < this._fld5 - 1; ++l) {
            final int n7 = l * this._fld4;
            final int n8 = n7 + this._fld4 - 1;
            this._fld1e[n7] = 0;
            this._fld1e[n8] = 0;
        }
        for (int n9 = n6; n9 < n5; ++n9) {
            this._fld1e[n9] = 0;
        }
    }
    
    void _mth5b() {
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
            this._fld2e[n + i] = (0xFF000000 | n3 + i * n10 / n9 << 16 | n5 + i * n11 / n9 << 8 | n7 + i * n12 / n9);
        }
    }
    
    void _mth7b() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this._fld0f.length() > 9) {
            final int n = this._fld0f.length() - 9;
            final int n2 = n + 9;
            this._fld6f = new byte[n];
            this._fld0f.getBytes(1, n + 1, this._fld6f, 0);
            this._fld7f = new byte[n2];
            this._fld0f.getBytes(0, n2, this._fld7f, 0);
            int n3 = n % 7;
            final int n4 = n % 3;
            for (int i = 0; i < n; ++i) {
                final byte b = this._fld6f[i];
                final byte b2 = (byte)(b + n3);
                if (b >= 48 && b <= 57) {
                    this._fld6f[i] = ((b2 <= 57) ? b2 : ((byte)(b2 - 10)));
                }
                else if (b >= 65 && b <= 90) {
                    this._fld6f[i] = ((b2 <= 90) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b >= 97 && b <= 122) {
                    this._fld6f[i] = ((b2 <= 122) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b == 42) {
                    this._fld6f[i] = 45;
                }
                else if (b == 45) {
                    this._fld6f[i] = 46;
                }
                n3 = (n3 + n4) % 7;
            }
            int n5 = n % 7;
            final int n6 = n % 3;
            for (int j = 0; j < n; ++j) {
                final byte b3 = this._fld6f[j];
                final byte b4 = (byte)(b3 - n5);
                if (b3 >= 48 && b3 <= 57) {
                    this._fld7f[j + 1] = ((b4 >= 48) ? b4 : ((byte)(b4 + 10)));
                }
                else if (b3 >= 65 && b3 <= 90) {
                    this._fld7f[j + 1] = ((b4 >= 65) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 >= 97 && b3 <= 122) {
                    this._fld7f[j + 1] = ((b4 >= 97) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 == 45) {
                    this._fld7f[j + 1] = 42;
                }
                else if (b3 == 46) {
                    this._fld7f[j + 1] = 45;
                }
                n5 = (n5 + n6) % 7;
            }
            final byte[] array = new byte[n];
            if (n > 4) {
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld6f[k + 4];
                }
            }
            if (this._fld7f[0] == this._fld7f[n >> 1] && this._fld7f[1 + n] == this._fld7f[1] && this._fld7f[1 + n + 1] == this._fld7f[n >> 1] && this._fld7f[1 + n + 2] == (byte)(97 + n5) && this._fld7f[1 + n + 3] == 45 && this._fld7f[1 + n + 4] == (byte)(122 - n6) && this._fld7f[1 + n + 5] == (byte)(110 + n5) && this._fld7f[1 + n + 6] == this._fld7f[1] && this._fld7f[1 + n + 7] == this._fld7f[n] && (host.equalsIgnoreCase(new String(this._fld6f, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this._fld5f = true;
            }
        }
        try {
            this._fld4f = new URL("http://" + this._fld5b);
        }
        catch (MalformedURLException ex) {
            this._fld4f = null;
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld5f) {
            this.getAppletContext().showDocument(this._fld4f, "_blank");
        }
        else if (this._fld1f != null) {
            if (this._fld2f != null) {
                this.getAppletContext().showDocument(this._fld1f, this._fld2f);
            }
            else {
                this.getAppletContext().showDocument(this._fld1f);
            }
        }
        return true;
    }
    
    void _mth8b() {
        int fld8c = 0;
        do {
            ++fld8c;
        } while (this.getParameter("overtext" + fld8c) != null);
        if (--fld8c > 0) {
            this._fld8b = true;
            this._fld8c = fld8c;
            this._fld8f = new String[this._fld8c];
            this._fld9f = new Color[this._fld8c];
            this._fld0g = new Color[this._fld8c];
            this._fld1g = new Font[this._fld8c];
            this._fld2g = new FontMetrics[this._fld8c];
            this._fld3g = new String[this._fld8c];
            this._fld4g = new int[this._fld8c];
            this._fld5g = new int[this._fld8c];
            for (int i = 0; i < this._fld8c; ++i) {
                this._fld8f[i] = this.getParameter("overtext" + String.valueOf(i + 1));
                this._fld9f[i] = this._mth7("overtextcol" + String.valueOf(i + 1), new Color(16777215));
                this._fld0g[i] = this._mth7("overtextcols" + String.valueOf(i + 1), new Color(0));
                final String parameter = this.getParameter("overtexty" + String.valueOf(i + 1));
                if (parameter == null) {
                    this._fld5g[i] = 10;
                }
                else {
                    this._fld5g[i] = Integer.parseInt(parameter);
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
                this._fld1g[i] = new Font(parameter2, n, int1);
                this._fld2g[i] = this._fld1c.getFontMetrics(this._fld1g[i]);
                this._fld3g[i] = this.getParameter("overTextType" + String.valueOf(i + 1));
                if (this._fld3g[i] == null) {
                    this._fld3g[i] = "scrollleft";
                }
                final String parameter5 = this.getParameter("overtextspeed" + String.valueOf(i + 1));
                if (parameter5 == null) {
                    this._fld4g[i] = 2;
                }
                else {
                    this._fld4g[i] = Integer.valueOf(parameter5);
                    if (this._fld4g[i] < 1 || this._fld4g[i] > 4) {
                        this._fld4g[i] = 2;
                    }
                }
            }
            this._mth9b();
        }
    }
    
    void _mth9b() {
        this._fld1d = this._fld2g[this._fld7c].stringWidth(this._fld8f[this._fld7c]);
        this._fld2d = this._fld2g[this._fld7c].getHeight();
        if (this._fld3g[this._fld7c].equalsIgnoreCase("scrolldown")) {
            this._fld9c = this._fld4 - this._fld1d >> 1;
            this._fld0d = 0;
            return;
        }
        if (this._fld3g[this._fld7c].equalsIgnoreCase("scrollup")) {
            this._fld9c = this._fld4 - this._fld1d >> 1;
            this._fld0d = this._fld5 + this._fld2d;
            return;
        }
        if (this._fld3g[this._fld7c].equalsIgnoreCase("scrollright")) {
            this._fld9c = -this._fld1d;
            this._fld0d = this._fld5g[this._fld7c] + (this._fld2d >> 1) + (this._fld2d >> 3);
            return;
        }
        this._fld9c = this._fld4;
        this._fld0d = this._fld5g[this._fld7c] + (this._fld2d >> 1) + (this._fld2d >> 3);
    }
    
    void _mth0c(final Graphics graphics) {
        graphics.setFont(this._fld1g[this._fld7c]);
        graphics.setColor(this._fld0g[this._fld7c]);
        graphics.drawString(this._fld8f[this._fld7c], this._fld9c + 1, this._fld0d + 1);
        graphics.setColor(this._fld9f[this._fld7c]);
        graphics.drawString(this._fld8f[this._fld7c], this._fld9c, this._fld0d);
        if (this._fld3g[this._fld7c].equalsIgnoreCase("scrolldown")) {
            this._fld0d += this._fld4g[this._fld7c];
        }
        else if (this._fld3g[this._fld7c].equalsIgnoreCase("scrollup")) {
            this._fld0d -= this._fld4g[this._fld7c];
        }
        else if (this._fld3g[this._fld7c].equalsIgnoreCase("scrollright")) {
            this._fld9c += this._fld4g[this._fld7c];
        }
        else {
            this._fld9c -= this._fld4g[this._fld7c];
        }
        if (this._fld0d > this._fld5 + this._fld2d || this._fld0d < -this._fld2d || this._fld9c > this._fld4 || this._fld9c < -this._fld1d) {
            ++this._fld7c;
            if (this._fld7c >= this._fld8c) {
                this._fld7c = 0;
            }
            this._mth9b();
        }
    }
    
    void _mth1c() {
        final String parameter = this.getParameter("OverImage");
        if (parameter != null) {
            this._fld2c = this._mth6(parameter);
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
    
    public DS_Explosion() {
        this._fld3 = new Font("Helvetica", 1, 12);
        this._fld1b = "Applet by Dario Sciacca";
        this._fld2b = "dario@dseffects.com";
        this._fld5b = "www.dseffects.com";
        this._fld6b = "Don't remove Dario Sciacca's credits line";
        this._fld7b = this._fld1b + " (" + this._fld5b + ")";
        this._fld8b = false;
        this._fld9b = false;
        this._fld8d = "Explosion started";
        this._fld0e = false;
        this._fld2e = new int[256];
        this._fld9e = 0.1;
        this._fld0f = "";
        this._fld2f = "_blank";
        this._fld3f = "Applet by Dario Sciacca";
        this._fld5f = false;
    }
}
