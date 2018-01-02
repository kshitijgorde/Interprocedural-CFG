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

public class DS_CloudsMenu extends Applet implements Runnable
{
    Thread _fld0;
    int _fld1;
    Font _fld2;
    int _fld3;
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
    int _fld1d;
    int _fld2d;
    int _fld3d;
    int _fld4d;
    int _fld5d;
    int _fld6d;
    int _fld7d;
    int _fld8d;
    int _fld9d;
    int _fld0e;
    int _fld1e;
    int[] _fld2e;
    int[] _fld3e;
    int[] _fld4e;
    int[] _fld5e;
    int[] _fld6e;
    int _fld7e;
    String _fld8e;
    URL _fld9e;
    boolean _fld0f;
    boolean _fld1f;
    byte[] _fld2f;
    byte[] _fld3f;
    String[] _fld4f;
    Color _fld5f;
    Color _fld6f;
    Font _fld7f;
    FontMetrics _fld8f;
    int _fld9f;
    int _fld0g;
    boolean _fld1g;
    String[] _fld2g;
    String[] _fld3g;
    URL[] _fld4g;
    int _fld5g;
    
    public DS_CloudsMenu() {
        this._fld1 = 0;
        this._fld2 = new Font("Helvetica", 1, 12);
        this._fld9 = 0;
        this._fld1b = "Applet by Dario Sciacca";
        this._fld2b = "dario@dseffects.com";
        this._fld3b = 0;
        this._fld5b = "www.dseffects.com";
        this._fld6b = "Don't remove Dario Sciacca's credits line";
        this._fld7b = String.valueOf(this._fld1b) + " (" + this._fld5b + ")";
        this._fld9c = "CloudsMenu started";
        this._fld0d = false;
        this._fld6e = new int[256];
        this._fld8e = "";
        this._fld0f = false;
        this._fld1f = false;
        this._fld9f = 1;
    }
    
    void _mth0(final int[] array) {
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
                    array[(n9 << 8) + n10] = this._mth1(n5 + n6 + n7 + n8 >> 2, n);
                    array[(n3 << 8) + n10] = this._mth1(n6 + n8 >> 1, n);
                    array[(n9 << 8) + n4] = this._mth1(n7 + n8 >> 1, n);
                    if (k == 0) {
                        array[(n9 << 8) + l] = this._mth1(n5 + n6 >> 1, n);
                    }
                    if (l == 0) {
                        array[(k << 8) + n10] = this._mth1(n5 + n7 >> 1, n);
                    }
                }
            }
        }
    }
    
    void _mth0b() {
        this._fld1e = (this._fld1e + 256 + this._fld3d) % 256;
        this._fld0e = (this._fld0e + 256 + this._fld2d) % 256;
        final int n = this._fld3d / 2;
        final int n2 = this._fld2d / 2;
        this._fld9d = (this._fld9d + 256 + n) % 256;
        this._fld8d = (this._fld8d + 256 + n2) % 256;
        final int n3 = this._fld3d / 3;
        final int n4 = this._fld2d / 3;
        this._fld7d = (this._fld9d + 256 + n3) % 256;
        this._fld6d = (this._fld8d + 256 + n4) % 256;
        this._mth2();
        this._fld8 = this.createImage(this._fld7);
    }
    
    int _mth1(final int n, final int n2) {
        final int n3 = n + (int)(Math.random() * n2) - (n2 >> 1);
        return (n3 >= 0) ? ((n3 <= 255) ? n3 : 255) : 0;
    }
    
    void _mth1b(final Graphics graphics) {
        for (int i = 0; i < this._fld0c; ++i) {
            this._fld1c = this._fld8f.stringWidth(this._fld4f[i]);
            this._fld2c = this._fld8f.getHeight();
            final int n = (this._fld2c >> 1) + (this._fld2c >> 3);
            graphics.setFont(this._fld7f);
            if (this._fld0g == 0) {
                if (this._fld1g) {
                    graphics.setColor(new Color(0));
                    graphics.drawString(this._fld4f[i], (this._fld3 >> 1) - (this._fld1c >> 1) + 1, (this._fld5 / this._fld0c >> 2) + this._fld5 / this._fld0c * i + n + 1);
                }
                int fld5 = this._fld5 / this._fld0c * ((i + 1) % this._fld0c);
                if (i == this._fld0c - 1) {
                    fld5 = this._fld5;
                }
                final int n2 = this._fld5 / this._fld0c * i;
                if (this._fld0d && this._fld8c >= n2 && this._fld8c < fld5) {
                    this._fld5g = i;
                    graphics.setColor(this._fld5f);
                    this.setCursor(Cursor.getPredefinedCursor(12));
                }
                else {
                    graphics.setColor(this._fld6f);
                }
                graphics.drawString(this._fld4f[i], (this._fld3 >> 1) - (this._fld1c >> 1), (this._fld5 / this._fld0c >> 2) + this._fld5 / this._fld0c * i + n);
            }
            else {
                if (this._fld1g) {
                    graphics.setColor(new Color(0));
                    graphics.drawString(this._fld4f[i], this._fld3 / this._fld0c * i + (this._fld3 / this._fld0c >> 1) - (this._fld1c >> 1) + 1, (this._fld5 >> 1) + (n >> 1) + 1);
                }
                int fld6 = this._fld3 / this._fld0c * ((i + 1) % this._fld0c);
                if (i == this._fld0c - 1) {
                    fld6 = this._fld3;
                }
                final int n3 = this._fld3 / this._fld0c * i;
                if (this._fld0d && this._fld7c >= n3 && this._fld7c < fld6) {
                    this._fld5g = i;
                    graphics.setColor(this._fld5f);
                    this.setCursor(Cursor.getPredefinedCursor(12));
                }
                else {
                    graphics.setColor(this._fld6f);
                }
                graphics.drawString(this._fld4f[i], this._fld3 / this._fld0c * i + (this._fld3 / this._fld0c >> 1) - (this._fld1c >> 1), (this._fld5 >> 1) + (n >> 1));
            }
        }
    }
    
    void _mth2() {
        for (int i = 0; i < this._fld5; ++i) {
            final int n = i * this._fld3;
            final int n2 = (i + this._fld7d) % 256 * 256;
            final int n3 = (i + this._fld9d) % 256 * 256;
            final int n4 = (i + this._fld1e) % 256 * 256;
            for (int j = 0; j < this._fld3; ++j) {
                this._fld6[j + n] = (0xFF000000 | this._fld6e[(this._fld2e[(j + this._fld6d) % 256 + n2] + this._fld3e[(j + this._fld8d) % 256 + n3] + this._fld4e[(j + this._fld0e) % 256 + n4]) / 3]);
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
        if (host.length() > 0 && this._fld8e.length() > 9) {
            final int n = this._fld8e.length() - 9;
            final int n2 = n + 9;
            this._fld2f = new byte[n];
            this._fld8e.getBytes(1, n + 1, this._fld2f, 0);
            this._fld3f = new byte[n2];
            this._fld8e.getBytes(0, n2, this._fld3f, 0);
            int n3 = n % 7;
            final int n4 = n % 3;
            for (int i = 0; i < n; ++i) {
                final byte b = this._fld2f[i];
                final byte b2 = (byte)(b + n3);
                if (b >= 48 && b <= 57) {
                    this._fld2f[i] = ((b2 <= 57) ? b2 : ((byte)(b2 - 10)));
                }
                else if (b >= 65 && b <= 90) {
                    this._fld2f[i] = ((b2 <= 90) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b >= 97 && b <= 122) {
                    this._fld2f[i] = ((b2 <= 122) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b == 42) {
                    this._fld2f[i] = 45;
                }
                else if (b == 45) {
                    this._fld2f[i] = 46;
                }
                n3 = (n3 + n4) % 7;
            }
            int n5 = n % 7;
            final int n6 = n % 3;
            for (int j = 0; j < n; ++j) {
                final byte b3 = this._fld2f[j];
                final byte b4 = (byte)(b3 - n5);
                if (b3 >= 48 && b3 <= 57) {
                    this._fld3f[j + 1] = ((b4 >= 48) ? b4 : ((byte)(b4 + 10)));
                }
                else if (b3 >= 65 && b3 <= 90) {
                    this._fld3f[j + 1] = ((b4 >= 65) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 >= 97 && b3 <= 122) {
                    this._fld3f[j + 1] = ((b4 >= 97) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 == 45) {
                    this._fld3f[j + 1] = 42;
                }
                else if (b3 == 46) {
                    this._fld3f[j + 1] = 45;
                }
                n5 = (n5 + n6) % 7;
            }
            byte[] array;
            if (n > 4) {
                array = new byte[n - 4];
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld2f[k + 4];
                }
            }
            else {
                array = new byte[] { 0 };
            }
            if (this._fld3f[0] == this._fld3f[n >> 2] && this._fld3f[1 + n] == this._fld3f[n >> 1] && this._fld3f[1 + n + 1] == this._fld3f[n >> 1] && this._fld3f[1 + n + 2] == (byte)(112 + n6) && this._fld3f[1 + n + 3] == 45 && this._fld3f[1 + n + 4] == (byte)(112 - n5) && this._fld3f[1 + n + 5] == (byte)(110 + n6) && this._fld3f[1 + n + 6] == this._fld3f[n] && this._fld3f[1 + n + 7] == this._fld3f[n >> 1] && (host.equalsIgnoreCase(new String(this._fld2f, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this._fld0f = true;
            }
        }
        try {
            this._fld9e = new URL("http://" + this._fld5b);
        }
        catch (MalformedURLException ex) {
            this._fld9e = null;
        }
    }
    
    void _mth3b() {
        this._fld4c = 1;
        for (int i = 0; i < 11; ++i) {
            if (this._fld2b.charAt(i) == this._fld1b.charAt(i) || this._fld3c == 0) {
                this._mth5();
            }
        }
        this._fld0f = false;
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
        final int n = this._fld3 >> 1;
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
            if (this._fld0d && !this._fld0f) {
                this._fld9b.setColor(Color.white);
                this._fld9b.drawLine(n - 64, n2 - 8, n + 64, n2 - 8);
                this._fld9b.drawLine(n - 64, n2 + 8, n + 64, n2 + 8);
                this._fld9b.drawLine(n - 64, n2 - 8, n - 64, n2 + 8);
                this._fld9b.drawLine(n + 64, n2 - 8, n + 64, n2 + 8);
                this._fld9b.setColor(Color.blue);
                this._fld9b.fillRect(n - 63, n2 - 7, 127, 15);
                this._fld9b.setFont(this._fld2);
                this._fld9b.setColor(Color.yellow);
                this._fld9b.drawString(this._fld5b, n - (this._fld4b >> 1), n2 + 5);
                if (this._fld7c > n - 64 && this._fld7c < n + 64 && this._fld8c > n2 - 8 && this._fld8c < n2 + 8) {
                    this._fld1f = true;
                    this.showStatus(this._fld5b);
                }
                else {
                    this._fld1f = false;
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
        String parameter = this.getParameter("intensity");
        if (parameter == null) {
            parameter = "4";
        }
        this._fld7e = Integer.valueOf(parameter);
        this._fld7e = ((this._fld7e >= 1) ? ((this._fld7e <= 8) ? this._fld7e : 8) : 1);
        this._fld1d = 2;
        final String parameter2 = this.getParameter("direction");
        if (parameter2 == null) {
            this._fld4d = this._fld1d;
            this._fld5d = 0;
        }
        else if (parameter2.equalsIgnoreCase("RIGHT")) {
            this._fld4d = -this._fld1d;
            this._fld5d = 0;
        }
        else if (parameter2.equalsIgnoreCase("UP")) {
            this._fld4d = 0;
            this._fld5d = this._fld1d;
        }
        else if (parameter2.equalsIgnoreCase("DOWN")) {
            this._fld4d = 0;
            this._fld5d = -this._fld1d;
        }
        else {
            this._fld4d = this._fld1d;
            this._fld5d = 0;
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
        this._fld4f = new String[this._fld0c];
        this._fld4g = new URL[this._fld0c];
        this._fld2g = new String[this._fld0c];
        this._fld3g = new String[this._fld0c];
        for (int i = 0; i < this._fld0c; ++i) {
            this._fld4f[i] = this.getParameter("text" + String.valueOf(i + 1));
            if (this._fld4f[i] == null) {
                this._fld4f[i] = new String("Text" + String.valueOf(i + 1));
            }
            if (this._fld4f[i].length() > this._fld9f) {
                this._fld9f = this._fld4f[i].length();
            }
            final String parameter = this.getParameter("link" + String.valueOf(i + 1));
            try {
                this._fld4g[i] = new URL("http://" + parameter);
            }
            catch (MalformedURLException ex) {
                this._fld4g[i] = null;
            }
            final String parameter2 = this.getParameter("regtarget" + String.valueOf(i + 1));
            if (parameter2 != null && this._fld0f) {
                this._fld2g[i] = parameter2;
            }
            else {
                this._fld2g[i] = "_blank";
            }
            final String parameter3 = this.getParameter("regstatusmsg" + String.valueOf(i + 1));
            if (parameter3 != null && this._fld0f) {
                this._fld3g[i] = parameter3;
            }
            else {
                this._fld3g[i] = this._fld1b;
            }
        }
        final String parameter4 = this.getParameter("menudir");
        if (parameter4 == null) {
            this._fld0g = 0;
        }
        else if (parameter4.equalsIgnoreCase("vertical")) {
            this._fld0g = 0;
        }
        else {
            this._fld0g = 1;
        }
        final String parameter5 = this.getParameter("textshadow");
        if (parameter5 == null) {
            this._fld1g = false;
        }
        else if (parameter5.equalsIgnoreCase("yes")) {
            this._fld1g = true;
        }
        else {
            this._fld1g = false;
        }
        this._fld5f = this._mth6("seltextcol", new Color(16711680));
        this._fld6f = this._mth6("unseltextcol", new Color(16776960));
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
        if (this._fld0g == 0) {
            n2 = this._fld3 / (this._fld9f + 2) * 2;
            final int n3 = this._fld5 / (this._fld0c << 1);
            if (n3 < n2) {
                n2 = n3;
            }
        }
        else {
            n2 = this._fld3 / this._fld0c / (this._fld9f + 1) * 2;
        }
        this._fld7f = new Font(parameter6, n, n2);
        this._fld8f = this._fld9b.getFontMetrics(this._fld7f);
    }
    
    void _mth9() {
        this._fld2d = this._fld4d;
        this._fld3d = this._fld5d;
        this._fld5e = new int[65536];
        this._fld2e = new int[65536];
        this._fld3e = new int[65536];
        this._fld4e = new int[65536];
        this._mth0(this._fld2e);
        this._mth0(this._fld3e);
        this._mth0(this._fld4e);
        for (int i = 0; i < 256; ++i) {
            this._fld6e[i] = (0xFF000000 | i << 16 | i << 8 | 0xFF);
        }
    }
    
    public String getAppletInfo() {
        return "DS CloudsMenu v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
    }
    
    public void init() {
        this._mth2b();
        this.showStatus("Please wait ...");
        this._fld4b = this.getFontMetrics(this._fld2).stringWidth(this._fld5b);
        this._fld3 = this.size().width;
        this._fld5 = this.size().height;
        this._mth3b();
        this._fld6 = new int[this._fld3 * this._fld5];
        this._fld7 = new MemoryImageSource(this._fld3, this._fld5, this._fld6, 0, this._fld3);
        this._fld8b = this.createImage(this._fld3, this._fld5);
        this._fld9b = this._fld8b.getGraphics();
        final String parameter = this.getParameter("regkey");
        if (parameter != null) {
            this._fld8e = parameter;
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
        if (!this._fld0f && this._fld1f) {
            this.getAppletContext().showDocument(this._fld9e, "_blank");
        }
        else if (this._fld4g[this._fld5g] != null) {
            if (this._fld0f && this._fld2g[this._fld5g] != null) {
                this.getAppletContext().showDocument(this._fld4g[this._fld5g], this._fld2g[this._fld5g]);
            }
            else {
                this.getAppletContext().showDocument(this._fld4g[this._fld5g], "_blank");
            }
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this._fld3g[this._fld5g]);
        return this._fld0d = true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this._fld0d = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int fld7c, final int fld8c) {
        this.showStatus(this._fld3g[this._fld5g]);
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
