import java.awt.Event;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.awt.Cursor;
import java.awt.image.ImageProducer;
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

public class DS_Stars3DMenu extends Applet implements Runnable
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
    Image _fld8b;
    Graphics _fld9b;
    int _fld0c;
    int _fld1c;
    int _fld2c;
    int _fld3c;
    int _fld4c;
    int _fld5c;
    int _fld6c;
    int _fld7c;
    int _fld8c;
    String _fld9c;
    boolean _fld0d;
    int cx;
    int cy;
    int _fld1d;
    int _fld2d;
    int _fld3d;
    int _fld4d;
    int _fld5d;
    int _fld6d;
    int _fld7d;
    int[] _fld8d;
    int[] _fld9d;
    int[] _fld0e;
    int[] _fld1e;
    int[] _fld2e;
    int[] _fld3e;
    int[] _fld4e;
    String _fld5e;
    URL _fld6e;
    boolean _fld7e;
    boolean _fld8e;
    byte[] _fld9e;
    byte[] _fld0f;
    String[] _fld1f;
    Color _fld2f;
    Color _fld3f;
    Font _fld4f;
    FontMetrics _fld5f;
    int _fld6f;
    int _fld7f;
    boolean _fld8f;
    String[] _fld9f;
    String[] _fld0g;
    URL[] _fld1g;
    int _fld2g;
    
    public DS_Stars3DMenu() {
        this._fld1 = 0;
        this._fld3 = new Font("Helvetica", 1, 12);
        this._fld9 = 0;
        this._fld1b = "Applet by Dario Sciacca";
        this._fld2b = "dario@dseffects.com";
        this._fld3b = 0;
        this._fld5b = "www.dseffects.com";
        this._fld6b = "Don't remove Dario Sciacca's credits line";
        this._fld7b = String.valueOf(this._fld1b) + " (" + this._fld5b + ")";
        this._fld9c = "Stars3DMenu started";
        this._fld0d = false;
        this._fld3e = new int[360];
        this._fld4e = new int[360];
        this._fld5e = "";
        this._fld7e = false;
        this._fld8e = false;
        this._fld6f = 1;
    }
    
    void _mth0(final int n, final int n2) {
        final int max = Math.max(this.cx, this.cy);
        this._fld8d[n] = (int)(Math.random() * max - (max >> 1));
        this._fld9d[n] = (int)(Math.random() * max - (max >> 1));
        this._fld0e[n] = n2;
        this._fld1e[n] = 0;
        this._fld2e[n] = 0;
    }
    
    void _mth0b() {
        for (int n = this._fld4 * this._fld5, i = 0; i < n; ++i) {
            this._fld6[i] = -16777216;
        }
        this._mth8b();
        this._mth9b();
        this._mth0c();
        this._mth4();
        this._fld8 = this.createImage(this._fld7);
    }
    
    void _mth0c() {
        this._fld7d = (this._fld7d + this._fld5d + 360) % 360;
        for (int i = 0; i < this._fld1d; ++i) {
            final int n = this._fld1e[i];
            final int n2 = this._fld2e[i];
            this._fld1e[i] = (n * this._fld3e[this._fld7d] - n2 * this._fld4e[this._fld7d] >> 8) + this.cx;
            this._fld2e[i] = (n * this._fld4e[this._fld7d] + n2 * this._fld3e[this._fld7d] >> 8) + this.cy;
        }
    }
    
    void _mth1() {
        for (int i = 0; i < this._fld1d; ++i) {
            this._mth0(i, (int)(Math.random() * 256.0));
        }
    }
    
    void _mth1b(final Graphics graphics) {
        for (int i = 0; i < this._fld0c; ++i) {
            this._fld1c = this._fld5f.stringWidth(this._fld1f[i]);
            this._fld2c = this._fld5f.getHeight();
            final int n = (this._fld2c >> 1) + (this._fld2c >> 3);
            graphics.setFont(this._fld4f);
            if (this._fld7f == 0) {
                if (this._fld8f) {
                    graphics.setColor(new Color(0));
                    graphics.drawString(this._fld1f[i], (this._fld4 >> 1) - (this._fld1c >> 1) + 1, (this._fld5 / this._fld0c >> 2) + this._fld5 / this._fld0c * i + n + 1);
                }
                int fld5 = this._fld5 / this._fld0c * ((i + 1) % this._fld0c);
                if (i == this._fld0c - 1) {
                    fld5 = this._fld5;
                }
                final int n2 = this._fld5 / this._fld0c * i;
                if (this._fld0d && this._fld8c >= n2 && this._fld8c < fld5) {
                    this._fld2g = i;
                    graphics.setColor(this._fld2f);
                    this.setCursor(Cursor.getPredefinedCursor(12));
                }
                else {
                    graphics.setColor(this._fld3f);
                }
                graphics.drawString(this._fld1f[i], (this._fld4 >> 1) - (this._fld1c >> 1), (this._fld5 / this._fld0c >> 2) + this._fld5 / this._fld0c * i + n);
            }
            else {
                if (this._fld8f) {
                    graphics.setColor(new Color(0));
                    graphics.drawString(this._fld1f[i], this._fld4 / this._fld0c * i + (this._fld4 / this._fld0c >> 1) - (this._fld1c >> 1) + 1, (this._fld5 >> 1) + (n >> 1) + 1);
                }
                int fld6 = this._fld4 / this._fld0c * ((i + 1) % this._fld0c);
                if (i == this._fld0c - 1) {
                    fld6 = this._fld4;
                }
                final int n3 = this._fld4 / this._fld0c * i;
                if (this._fld0d && this._fld7c >= n3 && this._fld7c < fld6) {
                    this._fld2g = i;
                    graphics.setColor(this._fld2f);
                    this.setCursor(Cursor.getPredefinedCursor(12));
                }
                else {
                    graphics.setColor(this._fld3f);
                }
                graphics.drawString(this._fld1f[i], this._fld4 / this._fld0c * i + (this._fld4 / this._fld0c >> 1) - (this._fld1c >> 1), (this._fld5 >> 1) + (n >> 1));
            }
        }
    }
    
    void _mth2b() {
        final String parameter = this.getParameter("credits");
        if (parameter != null) {
            if (!parameter.equals(this._fld7b)) {
                this._mth5();
            }
        }
        else {
            this._mth5();
        }
        this._fld3c = 1;
    }
    
    void _mth3() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this._fld5e.length() > 9) {
            final int n = this._fld5e.length() - 9;
            final int n2 = n + 9;
            this._fld9e = new byte[n];
            this._fld5e.getBytes(1, n + 1, this._fld9e, 0);
            this._fld0f = new byte[n2];
            this._fld5e.getBytes(0, n2, this._fld0f, 0);
            int n3 = n % 7;
            final int n4 = n % 3;
            for (int i = 0; i < n; ++i) {
                final byte b = this._fld9e[i];
                final byte b2 = (byte)(b + n3);
                if (b >= 48 && b <= 57) {
                    this._fld9e[i] = ((b2 <= 57) ? b2 : ((byte)(b2 - 10)));
                }
                else if (b >= 65 && b <= 90) {
                    this._fld9e[i] = ((b2 <= 90) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b >= 97 && b <= 122) {
                    this._fld9e[i] = ((b2 <= 122) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b == 42) {
                    this._fld9e[i] = 45;
                }
                else if (b == 45) {
                    this._fld9e[i] = 46;
                }
                n3 = (n3 + n4) % 7;
            }
            int n5 = n % 7;
            final int n6 = n % 3;
            for (int j = 0; j < n; ++j) {
                final byte b3 = this._fld9e[j];
                final byte b4 = (byte)(b3 - n5);
                if (b3 >= 48 && b3 <= 57) {
                    this._fld0f[j + 1] = ((b4 >= 48) ? b4 : ((byte)(b4 + 10)));
                }
                else if (b3 >= 65 && b3 <= 90) {
                    this._fld0f[j + 1] = ((b4 >= 65) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 >= 97 && b3 <= 122) {
                    this._fld0f[j + 1] = ((b4 >= 97) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 == 45) {
                    this._fld0f[j + 1] = 42;
                }
                else if (b3 == 46) {
                    this._fld0f[j + 1] = 45;
                }
                n5 = (n5 + n6) % 7;
            }
            byte[] array;
            if (n > 4) {
                array = new byte[n - 4];
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld9e[k + 4];
                }
            }
            else {
                array = new byte[] { 0 };
            }
            if (this._fld0f[0] == this._fld0f[n >> 2] && this._fld0f[1 + n] == this._fld0f[n >> 1] && this._fld0f[1 + n + 1] == this._fld0f[n >> 1] && this._fld0f[1 + n + 2] == (byte)(112 + n6) && this._fld0f[1 + n + 3] == 45 && this._fld0f[1 + n + 4] == (byte)(112 - n5) && this._fld0f[1 + n + 5] == (byte)(110 + n6) && this._fld0f[1 + n + 6] == this._fld0f[n] && this._fld0f[1 + n + 7] == this._fld0f[n >> 1] && (host.equalsIgnoreCase(new String(this._fld9e, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this._fld7e = true;
            }
        }
        try {
            this._fld6e = new URL("http://" + this._fld5b);
        }
        catch (MalformedURLException ex) {
            this._fld6e = null;
        }
    }
    
    void _mth3b() {
        this._fld4c = 1;
        for (int i = 0; i < 11; ++i) {
            if (this._fld2b.charAt(i) == this._fld1b.charAt(i) || this._fld3c == 0) {
                this._mth5();
            }
        }
        this._fld7e = false;
    }
    
    void _mth4() {
        for (int i = 1; i < this._fld1d; ++i) {
            if (this._fld1e[i] >= 0 && this._fld1e[i] < this._fld4 && this._fld2e[i] >= 0 && this._fld2e[i] < this._fld5) {
                this._fld6[this._fld2e[i] * this._fld4 + this._fld1e[i]] = (0xFF000000 | (255 - this._fld0e[i]) * 65793);
            }
        }
    }
    
    void _mth4b() {
        if (this._fld3c == 0 || this._fld4c == 0) {
            this._mth5();
        }
        for (int i = 0; i < 17; ++i) {
            if (this._fld2b.charAt(i) == this._fld5b.charAt(i)) {
                this._mth5();
            }
        }
        this._fld5c = -16777216;
        if (this._fld1b.charAt(1) != 'p' || this._fld1b.charAt(7) != 'b' || this._fld1b.charAt(21) != 'c' || this._fld1b.charAt(17) != 'c' || this._fld1b.charAt(12) != 'r' || this._fld1b.charAt(11) != 'a') {
            this._mth5();
        }
    }
    
    private final void _mth5() {
        while (true) {
            this.showStatus(this._fld6b);
        }
    }
    
    void _mth5b(final Graphics graphics) {
        final int n = this._fld4 >> 1;
        int n2;
        if (this._fld6c < 200) {
            n2 = 10;
        }
        else if (this._fld6c < 400) {
            n2 = this._fld5 >> 1;
        }
        else {
            n2 = this._fld5 - 10;
        }
        ++this._fld6c;
        this._fld6c %= 600;
        if (this._fld1 == 0) {
            graphics.drawString("Error ...", 10, 10);
        }
        else {
            if (this._fld8 != null) {
                this._fld9b.drawImage(this._fld8, 0, 0, this);
                this._mth1b(this._fld9b);
            }
            if (this._fld0d && !this._fld7e) {
                this._fld9b.setColor(Color.white);
                this._fld9b.drawLine(n - 64, n2 - 8, n + 64, n2 - 8);
                this._fld9b.drawLine(n - 64, n2 + 8, n + 64, n2 + 8);
                this._fld9b.drawLine(n - 64, n2 - 8, n - 64, n2 + 8);
                this._fld9b.drawLine(n + 64, n2 - 8, n + 64, n2 + 8);
                this._fld9b.setColor(Color.blue);
                this._fld9b.fillRect(n - 63, n2 - 7, 127, 15);
                this._fld9b.setFont(this._fld3);
                this._fld9b.setColor(Color.yellow);
                this._fld9b.drawString(this._fld5b, n - (this._fld4b >> 1), n2 + 5);
                if (this._fld7c > n - 64 && this._fld7c < n + 64 && this._fld8c > n2 - 8 && this._fld8c < n2 + 8) {
                    this._fld8e = true;
                    this.showStatus(this._fld5b);
                }
                else {
                    this._fld8e = false;
                }
            }
            graphics.drawImage(this._fld8b, 0, 0, this);
        }
    }
    
    Color _mth6(final String s, final Color color) {
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
    
    synchronized void _mth6b() {
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
    
    void _mth7() {
        String parameter = this.getParameter("speed");
        if (parameter == null) {
            parameter = "2";
        }
        this._fld2d = Integer.valueOf(parameter);
        this._fld2d = ((this._fld2d >= 1) ? ((this._fld2d <= 4) ? this._fld2d : 4) : 1);
        String parameter2 = this.getParameter("numstars");
        if (parameter2 == null) {
            parameter2 = "1000";
        }
        this._fld1d = Integer.valueOf(parameter2);
        this._fld1d = ((this._fld1d >= 100) ? ((this._fld1d <= 2000) ? this._fld1d : 2000) : 100);
        final String parameter3 = this.getParameter("direction");
        if (parameter3 == null) {
            this._fld3d = -this._fld2d;
        }
        else if (parameter3.equalsIgnoreCase("backward")) {
            this._fld3d = this._fld2d;
        }
        else {
            this._fld3d = -this._fld2d;
        }
        final String parameter4 = this.getParameter("rotdir");
        if (parameter4 == null) {
            this._fld4d = this._fld2d;
        }
        else if (parameter4.equalsIgnoreCase("COUNTERCLOCKWISE")) {
            this._fld4d = -this._fld2d;
        }
        else if (parameter4.equalsIgnoreCase("CLOCKWISE")) {
            this._fld4d = this._fld2d;
        }
        else {
            this._fld4d = 0;
        }
    }
    
    void _mth7b() {
        for (int i = 0; i < 360; ++i) {
            this._fld3e[i] = (int)(Math.cos(0.017453292519943295 * i) * 256.0);
            this._fld4e[i] = (int)(Math.sin(0.017453292519943295 * i) * 256.0);
        }
    }
    
    void _mth8() {
        int fld0c = 0;
        do {
            ++fld0c;
        } while (this.getParameter("text" + fld0c) != null);
        if (--fld0c == 0 || fld0c == 1) {
            this._fld0c = 2;
        }
        else if (fld0c > 1) {
            this._fld0c = fld0c;
        }
        this._fld1f = new String[this._fld0c];
        this._fld1g = new URL[this._fld0c];
        this._fld9f = new String[this._fld0c];
        this._fld0g = new String[this._fld0c];
        for (int i = 0; i < this._fld0c; ++i) {
            this._fld1f[i] = this.getParameter("text" + String.valueOf(i + 1));
            if (this._fld1f[i] == null) {
                this._fld1f[i] = new String("Text" + String.valueOf(i + 1));
            }
            if (this._fld1f[i].length() > this._fld6f) {
                this._fld6f = this._fld1f[i].length();
            }
            final String parameter = this.getParameter("link" + String.valueOf(i + 1));
            try {
                this._fld1g[i] = new URL("http://" + parameter);
            }
            catch (MalformedURLException ex) {
                this._fld1g[i] = null;
            }
            final String parameter2 = this.getParameter("regtarget" + String.valueOf(i + 1));
            if (parameter2 != null && this._fld7e) {
                this._fld9f[i] = parameter2;
            }
            else {
                this._fld9f[i] = "_blank";
            }
            final String parameter3 = this.getParameter("regstatusmsg" + String.valueOf(i + 1));
            if (parameter3 != null && this._fld7e) {
                this._fld0g[i] = parameter3;
            }
            else {
                this._fld0g[i] = this._fld1b;
            }
        }
        final String parameter4 = this.getParameter("menudir");
        if (parameter4 == null) {
            this._fld7f = 0;
        }
        else if (parameter4.equalsIgnoreCase("vertical")) {
            this._fld7f = 0;
        }
        else {
            this._fld7f = 1;
        }
        final String parameter5 = this.getParameter("textshadow");
        if (parameter5 == null) {
            this._fld8f = false;
        }
        else if (parameter5.equalsIgnoreCase("yes")) {
            this._fld8f = true;
        }
        else {
            this._fld8f = false;
        }
        this._fld2f = this._mth6("seltextcol", new Color(16711680));
        this._fld3f = this._mth6("unseltextcol", new Color(16776960));
        String parameter6 = this.getParameter("textfont");
        if (parameter6 == null) {
            parameter6 = "Helvetica";
        }
        final String parameter7 = this.getParameter("TextStyle");
        int n;
        if (parameter7 == null) {
            n = 0;
        }
        else if (parameter7.equalsIgnoreCase("PLAIN")) {
            n = 0;
        }
        else if (parameter7.equalsIgnoreCase("BOLD")) {
            n = 1;
        }
        else if (parameter7.equalsIgnoreCase("ITALIC")) {
            n = 2;
        }
        else if (parameter7.equalsIgnoreCase("BOLD ITALIC")) {
            n = 3;
        }
        else {
            n = 0;
        }
        int n2;
        if (this._fld7f == 0) {
            n2 = this._fld4 / (this._fld6f + 2) * 2;
            final int n3 = this._fld5 / (this._fld0c << 1);
            if (n3 < n2) {
                n2 = n3;
            }
        }
        else {
            n2 = this._fld4 / this._fld0c / (this._fld6f + 1) * 2;
        }
        this._fld4f = new Font(parameter6, n, n2);
        this._fld5f = this._fld9b.getFontMetrics(this._fld4f);
    }
    
    void _mth8b() {
        for (int i = 0; i < this._fld1d; ++i) {
            this._fld0e[i] = (this._fld0e[i] + this._fld6d + 255) % 255;
            if (this._fld0e[i] == 0) {
                this._fld0e[i] = this._fld6d;
            }
        }
    }
    
    void _mth9() {
        this._fld7d = 0;
        this.cx = this._fld4 >> 1;
        this.cy = this._fld5 >> 1;
        this._fld8d = new int[this._fld1d];
        this._fld9d = new int[this._fld1d];
        this._fld0e = new int[this._fld1d];
        this._fld1e = new int[this._fld1d];
        this._fld2e = new int[this._fld1d];
        this._mth7b();
        this._mth1();
        this._fld5d = this._fld4d;
        this._fld6d = this._fld3d;
    }
    
    void _mth9b() {
        for (int i = 1; i < this._fld1d; ++i) {
            this._fld1e[i] = this._fld8d[i] * 255 / this._fld0e[i];
            this._fld2e[i] = this._fld9d[i] * 255 / this._fld0e[i];
        }
    }
    
    public String getAppletInfo() {
        return "DS Stars3DMenu v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
    }
    
    public void init() {
        this._mth2b();
        this.showStatus("Please wait ...");
        this._fld4b = this.getFontMetrics(this._fld3).stringWidth(this._fld5b);
        this._fld4 = this.size().width;
        this._fld5 = this.size().height;
        this._mth3b();
        this._fld6 = new int[this._fld4 * this._fld5];
        this._fld7 = new MemoryImageSource(this._fld4, this._fld5, this._fld6, 0, this._fld4);
        this._fld8b = this.createImage(this._fld4, this._fld5);
        this._fld9b = this._fld8b.getGraphics();
        final String parameter = this.getParameter("regkey");
        if (parameter != null) {
            this._fld5e = parameter;
        }
        this._mth3();
        this._mth8();
        this._mth7();
        this._mth4b();
        this._mth9();
        if (this._fld5c == -16777216) {
            this._fld1 = 1;
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld7e && this._fld8e) {
            this.getAppletContext().showDocument(this._fld6e, "_blank");
        }
        else if (this._fld1g[this._fld2g] != null) {
            if (this._fld7e && this._fld9f[this._fld2g] != null) {
                this.getAppletContext().showDocument(this._fld1g[this._fld2g], this._fld9f[this._fld2g]);
            }
            else {
                this.getAppletContext().showDocument(this._fld1g[this._fld2g], "_blank");
            }
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this._fld0g[this._fld2g]);
        return this._fld0d = true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this._fld0d = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int fld7c, final int fld8c) {
        this.showStatus(this._fld0g[this._fld2g]);
        this._fld7c = fld7c;
        this._fld8c = fld8c;
        return this._fld0d = true;
    }
    
    public void run() {
        this.showStatus(this._fld9c);
        System.gc();
        final Graphics graphics = this.getGraphics();
        this._fld0b = System.currentTimeMillis();
        while (this._fld0 != null) {
            if (this._fld1 == 1) {
                this._mth0b();
            }
            this._mth5b(graphics);
            this._mth6b();
            if (this._fld9++ > 10) {
                System.gc();
                this._fld9 = 0;
            }
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
    
    public void update(final Graphics graphics) {
    }
}
