import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class DS_FireWorksMenu extends Applet implements Runnable
{
    Thread _fld0;
    int _fld1;
    Font _fld2;
    int _fld3;
    int _fld4;
    int[] _fld5;
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
    boolean _fld6c;
    String _fld7c;
    Color _fld8c;
    Color _fld9c;
    int _fld0d;
    int _fld1d;
    int _fld2d;
    String _fld3d;
    boolean _fld4d;
    int _fld5d;
    double _fld6d;
    double _fld7d;
    int _fld8d;
    int _fld9d;
    int _fld0e;
    double _fld1e;
    double _fld2e;
    int _fld3e;
    boolean _fld4e;
    double _fld5e;
    double _fld6e;
    int _fld7e;
    int _fld8e;
    int _fld9e;
    double _fld0f;
    double _fld1f;
    int _fld2f;
    boolean _fld3f;
    double _fld4f;
    double _fld5f;
    int _fld6f;
    int _fld7f;
    int _fld8f;
    double _fld9f;
    double _fld0g;
    int _fld1g;
    boolean _fld2g;
    int _fld3g;
    int _fld4g;
    int _fld5g;
    int _fld6g;
    int _fld7g;
    float[] _fld8g;
    float[] _fld9g;
    float[] _fld0h;
    float[] _fld1h;
    int[] _fld2h;
    int[] _fld3h;
    int[] _fld4h;
    float[] _fld5h;
    float[] _fld6h;
    float[] _fld7h;
    float[] _fld8h;
    int[] _fld9h;
    int[] _fld0i;
    int[] _fld1i;
    float[] _fld2i;
    float[] _fld3i;
    float[] _fld4i;
    float[] _fld5i;
    int[] _fld6i;
    int[] _fld7i;
    int[] _fld8i;
    double _fld9i;
    String _fld0j;
    URL _fld1j;
    boolean _fld2j;
    boolean _fld3j;
    byte[] _fld4j;
    byte[] _fld5j;
    String[] _fld6j;
    Color _fld7j;
    Color _fld8j;
    Font _fld9j;
    FontMetrics _fld0k;
    int _fld1k;
    int _fld2k;
    boolean _fld3k;
    String[] _fld4k;
    String[] _fld5k;
    URL[] _fld6k;
    int _fld7k;
    
    public DS_FireWorksMenu() {
        this._fld1 = 0;
        this._fld2 = new Font("Helvetica", 1, 12);
        this._fld9 = 0;
        this._fld1b = "Applet by Dario Sciacca";
        this._fld2b = "dario@dseffects.com";
        this._fld3b = 0;
        this._fld5b = "www.dseffects.com";
        this._fld6b = "Don't remove Dario Sciacca's credits line";
        this._fld7b = String.valueOf(this._fld1b) + " (" + this._fld5b + ")";
        this._fld6c = false;
        this._fld7c = "";
        this._fld3d = "FireWorksMenu started";
        this._fld4d = false;
        this._fld5d = 6;
        this._fld4e = true;
        this._fld3f = true;
        this._fld2g = true;
        this._fld6g = 2;
        this._fld0j = "";
        this._fld2j = false;
        this._fld3j = false;
        this._fld1k = 1;
    }
    
    void _mth0(final float[] array, final float[] array2, final float[] array3, final float[] array4, final int[] array5, final int[] array6, final int[] array7, final int n) {
        for (int i = 0; i < this._fld7g; ++i) {
            final int n2 = (int)array[i];
            final int n3 = (int)array2[i];
            if (n2 >= 0 && n2 < this._fld3 && n3 >= 0 && n3 < this._fld4) {
                this._fld6[n3 * this._fld3 + n2] = (0xFF000000 | array5[i] * n >> 8 << 16 | array6[i] * n >> 8 << 8 | array7[i] * n >> 8);
            }
            final int n4 = i;
            array[n4] += array3[i];
            final int n5 = i;
            array2[n5] += array4[i];
            final int n6 = i;
            array4[n6] += (float)this._fld9i;
        }
    }
    
    void _mth0b(final Graphics graphics) {
        for (int i = 0; i < this._fld0c; ++i) {
            this._fld1c = this._fld0k.stringWidth(this._fld6j[i]);
            this._fld2c = this._fld0k.getHeight();
            final int n = (this._fld2c >> 1) + (this._fld2c >> 3);
            graphics.setFont(this._fld9j);
            if (this._fld2k == 0) {
                if (this._fld3k) {
                    graphics.setColor(new Color(0));
                    graphics.drawString(this._fld6j[i], (this._fld3 >> 1) - (this._fld1c >> 1) + 1, (this._fld4 / this._fld0c >> 2) + this._fld4 / this._fld0c * i + n + 1);
                }
                int fld4 = this._fld4 / this._fld0c * ((i + 1) % this._fld0c);
                if (i == this._fld0c - 1) {
                    fld4 = this._fld4;
                }
                final int n2 = this._fld4 / this._fld0c * i;
                if (this._fld4d && this._fld2d >= n2 && this._fld2d < fld4) {
                    this._fld7k = i;
                    graphics.setColor(this._fld7j);
                    this.setCursor(Cursor.getPredefinedCursor(12));
                }
                else {
                    graphics.setColor(this._fld8j);
                }
                graphics.drawString(this._fld6j[i], (this._fld3 >> 1) - (this._fld1c >> 1), (this._fld4 / this._fld0c >> 2) + this._fld4 / this._fld0c * i + n);
            }
            else {
                if (this._fld3k) {
                    graphics.setColor(new Color(0));
                    graphics.drawString(this._fld6j[i], this._fld3 / this._fld0c * i + (this._fld3 / this._fld0c >> 1) - (this._fld1c >> 1) + 1, (this._fld4 >> 1) + (n >> 1) + 1);
                }
                int fld5 = this._fld3 / this._fld0c * ((i + 1) % this._fld0c);
                if (i == this._fld0c - 1) {
                    fld5 = this._fld3;
                }
                final int n3 = this._fld3 / this._fld0c * i;
                if (this._fld4d && this._fld1d >= n3 && this._fld1d < fld5) {
                    this._fld7k = i;
                    graphics.setColor(this._fld7j);
                    this.setCursor(Cursor.getPredefinedCursor(12));
                }
                else {
                    graphics.setColor(this._fld8j);
                }
                graphics.drawString(this._fld6j[i], this._fld3 / this._fld0c * i + (this._fld3 / this._fld0c >> 1) - (this._fld1c >> 1), (this._fld4 >> 1) + (n >> 1));
            }
        }
    }
    
    void _mth0c() {
        this._fld6f = (int)(Math.random() * 128.0 + 127.0);
        this._fld7f = (int)(Math.random() * 128.0 + 127.0);
        this._fld8f = (int)(Math.random() * 128.0 + 127.0);
        this._fld4f = (int)((Math.random() - 0.5) * (this._fld3 >> 1)) + (this._fld3 >> 1);
        this._fld5f = this._fld4 - 1;
        this._fld0g = 0.0;
        this._fld9f = (Math.random() - 0.5) * this._fld5d;
        this._fld1g = (int)(Math.random() * (this._fld4 >> 2) + (this._fld4 >> 1) + (this._fld4 >> 3));
        this._fld2g = false;
    }
    
    void _mth1() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this._fld0j.length() > 9) {
            final int n = this._fld0j.length() - 9;
            final int n2 = n + 9;
            this._fld4j = new byte[n];
            this._fld0j.getBytes(1, n + 1, this._fld4j, 0);
            this._fld5j = new byte[n2];
            this._fld0j.getBytes(0, n2, this._fld5j, 0);
            int n3 = n % 7;
            final int n4 = n % 3;
            for (int i = 0; i < n; ++i) {
                final byte b = this._fld4j[i];
                final byte b2 = (byte)(b + n3);
                if (b >= 48 && b <= 57) {
                    this._fld4j[i] = ((b2 <= 57) ? b2 : ((byte)(b2 - 10)));
                }
                else if (b >= 65 && b <= 90) {
                    this._fld4j[i] = ((b2 <= 90) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b >= 97 && b <= 122) {
                    this._fld4j[i] = ((b2 <= 122) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b == 42) {
                    this._fld4j[i] = 45;
                }
                else if (b == 45) {
                    this._fld4j[i] = 46;
                }
                n3 = (n3 + n4) % 7;
            }
            int n5 = n % 7;
            final int n6 = n % 3;
            for (int j = 0; j < n; ++j) {
                final byte b3 = this._fld4j[j];
                final byte b4 = (byte)(b3 - n5);
                if (b3 >= 48 && b3 <= 57) {
                    this._fld5j[j + 1] = ((b4 >= 48) ? b4 : ((byte)(b4 + 10)));
                }
                else if (b3 >= 65 && b3 <= 90) {
                    this._fld5j[j + 1] = ((b4 >= 65) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 >= 97 && b3 <= 122) {
                    this._fld5j[j + 1] = ((b4 >= 97) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 == 45) {
                    this._fld5j[j + 1] = 42;
                }
                else if (b3 == 46) {
                    this._fld5j[j + 1] = 45;
                }
                n5 = (n5 + n6) % 7;
            }
            byte[] array;
            if (n > 4) {
                array = new byte[n - 4];
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld4j[k + 4];
                }
            }
            else {
                array = new byte[] { 0 };
            }
            if (this._fld5j[0] == this._fld5j[n >> 2] && this._fld5j[1 + n] == this._fld5j[n >> 1] && this._fld5j[1 + n + 1] == this._fld5j[n >> 1] && this._fld5j[1 + n + 2] == (byte)(112 + n6) && this._fld5j[1 + n + 3] == 45 && this._fld5j[1 + n + 4] == (byte)(112 - n5) && this._fld5j[1 + n + 5] == (byte)(110 + n6) && this._fld5j[1 + n + 6] == this._fld5j[n] && this._fld5j[1 + n + 7] == this._fld5j[n >> 1] && (host.equalsIgnoreCase(new String(this._fld4j, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this._fld2j = true;
            }
        }
        try {
            this._fld1j = new URL("http://" + this._fld5b);
        }
        catch (MalformedURLException ex) {
            this._fld1j = null;
        }
    }
    
    void _mth1b() {
        final String parameter = this.getParameter("credits");
        if (parameter != null) {
            if (!parameter.equals(this._fld7b)) {
                this._mth2();
            }
        }
        else {
            this._mth2();
        }
        this._fld3c = 1;
    }
    
    void _mth1c() {
        this._fld3e -= this._fld5d;
        this._fld2e -= 0.5;
        this._fld6d += this._fld1e;
        this._fld7d += this._fld2e;
        if (this._fld3e < 0 || this._fld6d < 0.0 || this._fld7d < 0.0 || this._fld6d >= this._fld3 || this._fld7d >= this._fld4) {
            this._fld4e = true;
            this._mth7b(this._fld8g, this._fld9g, this._fld0h, this._fld1h, this._fld2h, this._fld3h, this._fld4h, this._fld6d, this._fld7d, this._fld8d, this._fld9d, this._fld0e);
            this._fld3g = 255;
        }
        else {
            this._fld6[((int)this._fld7d - 1) * this._fld3 + (int)this._fld6d] = (0xFF000000 | this._fld8d << 16 | this._fld9d << 8 | this._fld0e);
            this._fld6[(int)this._fld7d * this._fld3 + (int)this._fld6d] = (0xFF000000 | this._fld8d << 16 | this._fld9d << 8 | this._fld0e);
        }
    }
    
    private final void _mth2() {
        while (true) {
            this.showStatus(this._fld6b);
        }
    }
    
    void _mth2b() {
        this._fld4c = 1;
        for (int i = 0; i < 11; ++i) {
            if (this._fld2b.charAt(i) == this._fld1b.charAt(i) || this._fld3c == 0) {
                this._mth2();
            }
        }
        this._fld2j = false;
    }
    
    void _mth2c() {
        this._fld2f -= this._fld5d;
        this._fld1f -= 0.5;
        this._fld5e += this._fld0f;
        this._fld6e += this._fld1f;
        if (this._fld2f < 0 || this._fld5e < 0.0 || this._fld6e < 0.0 || this._fld5e >= this._fld3 || this._fld6e >= this._fld4) {
            this._fld3f = true;
            this._mth7b(this._fld5h, this._fld6h, this._fld7h, this._fld8h, this._fld9h, this._fld0i, this._fld1i, this._fld5e, this._fld6e, this._fld7e, this._fld8e, this._fld9e);
            this._fld4g = 255;
        }
        else {
            this._fld6[((int)this._fld6e - 1) * this._fld3 + (int)this._fld5e] = (0xFF000000 | this._fld7e << 16 | this._fld8e << 8 | this._fld9e);
            this._fld6[(int)this._fld6e * this._fld3 + (int)this._fld5e] = (0xFF000000 | this._fld7e << 16 | this._fld8e << 8 | this._fld9e);
        }
    }
    
    Color _mth3(final String s, final Color color) {
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
    
    void _mth3b() {
        if (this._fld3c == 0 || this._fld4c == 0) {
            this._mth2();
        }
        for (int i = 0; i < 17; ++i) {
            if (this._fld2b.charAt(i) == this._fld5b.charAt(i)) {
                this._mth2();
            }
        }
        this._fld5c = -16777216;
        if (this._fld1b.charAt(1) != 'p' || this._fld1b.charAt(7) != 'b' || this._fld1b.charAt(21) != 'c' || this._fld1b.charAt(17) != 'c' || this._fld1b.charAt(12) != 'r' || this._fld1b.charAt(11) != 'a') {
            this._mth2();
        }
    }
    
    void _mth3c() {
        this._fld1g -= this._fld5d;
        this._fld0g -= 0.5;
        this._fld4f += this._fld9f;
        this._fld5f += this._fld0g;
        if (this._fld1g < 0 || this._fld4f < 0.0 || this._fld5f < 0.0 || this._fld4f >= this._fld3 || this._fld5f >= this._fld4) {
            this._fld2g = true;
            this._mth7b(this._fld2i, this._fld3i, this._fld4i, this._fld5i, this._fld6i, this._fld7i, this._fld8i, this._fld4f, this._fld5f, this._fld6f, this._fld7f, this._fld8f);
            this._fld5g = 255;
        }
        else {
            this._fld6[((int)this._fld5f - 1) * this._fld3 + (int)this._fld4f] = (0xFF000000 | this._fld6f << 16 | this._fld7f << 8 | this._fld8f);
            this._fld6[(int)this._fld5f * this._fld3 + (int)this._fld4f] = (0xFF000000 | this._fld6f << 16 | this._fld7f << 8 | this._fld8f);
        }
    }
    
    void _mth4() {
        String parameter = this.getParameter("particles");
        if (parameter == null) {
            parameter = "200";
        }
        this._fld7g = Integer.valueOf(parameter);
        this._fld7g = ((this._fld7g >= 100) ? ((this._fld7g <= 500) ? this._fld7g : 500) : 100);
        final String parameter2 = this.getParameter("gravity");
        if (parameter2 == null) {
            this._fld9i = 0.0;
        }
        else if (parameter2.equalsIgnoreCase("no")) {
            this._fld9i = 0.0;
        }
        else {
            this._fld9i = 0.1;
        }
    }
    
    int[] _mth4b(final int[] array, final int n, final int n2, final int[] array2, final int n3, final int n4) {
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
    
    void _mth5() {
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
        this._fld6j = new String[this._fld0c];
        this._fld6k = new URL[this._fld0c];
        this._fld4k = new String[this._fld0c];
        this._fld5k = new String[this._fld0c];
        for (int i = 0; i < this._fld0c; ++i) {
            this._fld6j[i] = this.getParameter("text" + String.valueOf(i + 1));
            if (this._fld6j[i] == null) {
                this._fld6j[i] = new String("Text" + String.valueOf(i + 1));
            }
            if (this._fld6j[i].length() > this._fld1k) {
                this._fld1k = this._fld6j[i].length();
            }
            final String parameter = this.getParameter("link" + String.valueOf(i + 1));
            try {
                this._fld6k[i] = new URL("http://" + parameter);
            }
            catch (MalformedURLException ex) {
                this._fld6k[i] = null;
            }
            final String parameter2 = this.getParameter("regtarget" + String.valueOf(i + 1));
            if (parameter2 != null && this._fld2j) {
                this._fld4k[i] = parameter2;
            }
            else {
                this._fld4k[i] = "_blank";
            }
            final String parameter3 = this.getParameter("regstatusmsg" + String.valueOf(i + 1));
            if (parameter3 != null && this._fld2j) {
                this._fld5k[i] = parameter3;
            }
            else {
                this._fld5k[i] = this._fld1b;
            }
        }
        final String parameter4 = this.getParameter("menudir");
        if (parameter4 == null) {
            this._fld2k = 0;
        }
        else if (parameter4.equalsIgnoreCase("vertical")) {
            this._fld2k = 0;
        }
        else {
            this._fld2k = 1;
        }
        final String parameter5 = this.getParameter("textshadow");
        if (parameter5 == null) {
            this._fld3k = false;
        }
        else if (parameter5.equalsIgnoreCase("yes")) {
            this._fld3k = true;
        }
        else {
            this._fld3k = false;
        }
        this._fld7j = this._mth3("seltextcol", new Color(16711680));
        this._fld8j = this._mth3("unseltextcol", new Color(16776960));
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
        if (this._fld2k == 0) {
            n2 = this._fld3 / (this._fld1k + 2) * 2;
            final int n3 = this._fld4 / (this._fld0c << 1);
            if (n3 < n2) {
                n2 = n3;
            }
        }
        else {
            n2 = this._fld3 / this._fld0c / (this._fld1k + 1) * 2;
        }
        this._fld9j = new Font(parameter6, n, n2);
        this._fld0k = this._fld9b.getFontMetrics(this._fld9j);
    }
    
    boolean _mth5b() {
        final String parameter = this.getParameter("image");
        if (parameter != null) {
            final Image mth8 = this._mth8(parameter);
            if (mth8 == null) {
                this.showStatus("Error loading image ");
                return false;
            }
            final int width = mth8.getWidth(this);
            final int height = mth8.getHeight(this);
            if (width != this._fld3 || height != this._fld4) {
                final int[] array = new int[width * height];
                if (!this._mth6(mth8, array, width, height)) {
                    return false;
                }
                this._fld5 = this._mth4b(this._fld5, this._fld3, this._fld4, array, width, height);
            }
            else if (!this._mth6(mth8, this._fld5, this._fld3, this._fld4)) {
                return false;
            }
            mth8.flush();
            System.gc();
        }
        return true;
    }
    
    boolean _mth6(final Image image, final int[] array, final int n, final int n2) {
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
        this._fld8g = new float[this._fld7g];
        this._fld9g = new float[this._fld7g];
        this._fld0h = new float[this._fld7g];
        this._fld1h = new float[this._fld7g];
        this._fld2h = new int[this._fld7g];
        this._fld3h = new int[this._fld7g];
        this._fld4h = new int[this._fld7g];
        this._fld5h = new float[this._fld7g];
        this._fld6h = new float[this._fld7g];
        this._fld7h = new float[this._fld7g];
        this._fld8h = new float[this._fld7g];
        this._fld9h = new int[this._fld7g];
        this._fld0i = new int[this._fld7g];
        this._fld1i = new int[this._fld7g];
        this._fld2i = new float[this._fld7g];
        this._fld3i = new float[this._fld7g];
        this._fld4i = new float[this._fld7g];
        this._fld5i = new float[this._fld7g];
        this._fld6i = new int[this._fld7g];
        this._fld7i = new int[this._fld7g];
        this._fld8i = new int[this._fld7g];
    }
    
    void _mth7b(final float[] array, final float[] array2, final float[] array3, final float[] array4, final int[] array5, final int[] array6, final int[] array7, final double n, final double n2, final int n3, final int n4, final int n5) {
        final float n6 = (float)n;
        final float n7 = (float)n2;
        final float n8 = this._fld6g / 2;
        for (int i = 0; i < this._fld7g; ++i) {
            array[i] = n6;
            array2[i] = n7;
            array3[i] = (float)(Math.random() * this._fld6g - n8);
            array4[i] = (float)(Math.random() * this._fld6g - n8);
            final float n9 = (float)(Math.random() * this._fld6g);
            final float n10 = array3[i];
            final float n11 = array4[i];
            final double sqrt = Math.sqrt(n10 * n10 + n11 * n11);
            double n12;
            if (sqrt == 0.0) {
                n12 = 0.0;
            }
            else {
                n12 = 1.0 / sqrt;
            }
            final int n13 = i;
            array3[n13] *= (float)(n12 * n9);
            final int n14 = i;
            array4[n14] *= (float)(n12 * n9);
            final int n15 = n3 + (int)(Math.random() * 64.0) - 32;
            final int n16 = n4 + (int)(Math.random() * 64.0) - 32;
            final int n17 = n5 + (int)(Math.random() * 64.0) - 32;
            if (n15 < 0) {
                array5[i] = 0;
            }
            else if (n15 > 255) {
                array5[i] = 255;
            }
            else {
                array5[i] = n15;
            }
            if (n16 < 0) {
                array6[i] = 0;
            }
            else if (n16 > 255) {
                array6[i] = 255;
            }
            else {
                array6[i] = n16;
            }
            if (n17 < 0) {
                array7[i] = 0;
            }
            else if (n17 > 255) {
                array7[i] = 255;
            }
            else {
                array7[i] = n17;
            }
        }
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
    
    void _mth8b() {
        this._fld8d = (int)(Math.random() * 128.0 + 127.0);
        this._fld9d = (int)(Math.random() * 128.0 + 127.0);
        this._fld0e = (int)(Math.random() * 128.0 + 127.0);
        this._fld6d = (int)((Math.random() - 0.5) * (this._fld3 >> 1)) + (this._fld3 >> 1);
        this._fld7d = this._fld4 - 1;
        this._fld2e = 0.0;
        this._fld1e = (Math.random() - 0.5) * this._fld5d;
        this._fld3e = (int)(Math.random() * (this._fld4 >> 2) + (this._fld4 >> 1));
        this._fld4e = false;
    }
    
    void _mth9() {
        for (int i = 0; i < this._fld3 * this._fld4; ++i) {
            this._fld6[i] = this._fld5[i];
        }
        if (!this._fld4e) {
            this._mth1c();
        }
        else {
            this._fld3g -= 6;
            if (this._fld3g >= 0) {
                this._mth0(this._fld8g, this._fld9g, this._fld0h, this._fld1h, this._fld2h, this._fld3h, this._fld4h, this._fld3g);
            }
            else {
                this._fld3g = 255;
                this._mth8b();
            }
        }
        if (!this._fld3f) {
            this._mth2c();
        }
        else {
            this._fld4g -= 4;
            if (this._fld4g >= 0) {
                this._mth0(this._fld5h, this._fld6h, this._fld7h, this._fld8h, this._fld9h, this._fld0i, this._fld1i, this._fld4g);
            }
            else {
                this._fld4g = 255;
                this._mth9b();
            }
        }
        if (!this._fld2g) {
            this._mth3c();
        }
        else {
            this._fld5g -= 8;
            if (this._fld5g >= 0) {
                this._mth0(this._fld2i, this._fld3i, this._fld4i, this._fld5i, this._fld6i, this._fld7i, this._fld8i, this._fld5g);
            }
            else {
                this._fld5g = 255;
                this._mth0c();
            }
        }
        this._fld8 = this.createImage(this._fld7);
    }
    
    void _mth9b() {
        this._fld7e = (int)(Math.random() * 128.0 + 127.0);
        this._fld8e = (int)(Math.random() * 128.0 + 127.0);
        this._fld9e = (int)(Math.random() * 128.0 + 127.0);
        this._fld5e = (int)((Math.random() - 0.5) * (this._fld3 >> 1)) + (this._fld3 >> 1);
        this._fld6e = this._fld4 - 1;
        this._fld1f = 0.0;
        this._fld0f = (Math.random() - 0.5) * this._fld5d;
        this._fld2f = (int)(Math.random() * (this._fld4 >> 2) + (this._fld4 >> 1));
        this._fld3f = false;
    }
    
    public String getAppletInfo() {
        return "DS FireWorksMenu v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
    }
    
    public void init() {
        this._mth1b();
        this.showStatus("Please wait ...");
        this._fld4b = this.getFontMetrics(this._fld2).stringWidth(this._fld5b);
        this._fld3 = this.size().width;
        this._fld4 = this.size().height;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld2j && this._fld3j) {
            this.getAppletContext().showDocument(this._fld1j, "_blank");
        }
        else if (this._fld6k[this._fld7k] != null) {
            if (this._fld2j && this._fld4k[this._fld7k] != null) {
                this.getAppletContext().showDocument(this._fld6k[this._fld7k], this._fld4k[this._fld7k]);
            }
            else {
                this.getAppletContext().showDocument(this._fld6k[this._fld7k], "_blank");
            }
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this._fld5k[this._fld7k]);
        return this._fld4d = true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this._fld4d = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int fld1d, final int fld2d) {
        this.showStatus(this._fld5k[this._fld7k]);
        this._fld1d = fld1d;
        this._fld2d = fld2d;
        return this._fld4d = true;
    }
    
    public void paint(final Graphics graphics) {
        if (!this._fld6c) {
            return;
        }
        final int n = this._fld3 >> 1;
        int n2;
        if (this._fld0d < 200) {
            n2 = 10;
        }
        else if (this._fld0d < 400) {
            n2 = this._fld4 >> 1;
        }
        else {
            n2 = this._fld4 - 10;
        }
        ++this._fld0d;
        this._fld0d %= 600;
        if (this._fld1 == 0) {
            graphics.drawString("Error ...", 10, 10);
        }
        else {
            if (this._fld8 != null) {
                this._fld9b.drawImage(this._fld8, 0, 0, this);
                this._mth0b(this._fld9b);
            }
            if (this._fld4d && !this._fld2j) {
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
                if (this._fld1d > n - 64 && this._fld1d < n + 64 && this._fld2d > n2 - 8 && this._fld2d < n2 + 8) {
                    this._fld3j = true;
                    this.showStatus(this._fld5b);
                }
                else {
                    this._fld3j = false;
                }
            }
            graphics.drawImage(this._fld8b, 0, 0, this);
        }
    }
    
    public void run() {
        if (!this._fld6c) {
            this._fld7c = this.getParameter("loadtext");
            this._fld8c = this._mth3("loadbgcolor", new Color(0));
            this._fld9c = this._mth3("loadtextcolor", new Color(16777215));
            final String parameter = this.getParameter("regkey");
            if (parameter != null) {
                this._fld0j = parameter;
            }
            final Graphics graphics = this.getGraphics();
            graphics.setColor(this._fld8c);
            graphics.fillRect(0, 0, this._fld3, this._fld4);
            graphics.setColor(this._fld9c);
            graphics.drawString(this._fld7c, (this._fld3 >> 1) - (this.getFontMetrics(this._fld2).stringWidth(this._fld7c) >> 1), (this._fld4 >> 1) + 5);
            this._mth2b();
            this._fld6 = new int[this._fld3 * this._fld4];
            this._fld7 = new MemoryImageSource(this._fld3, this._fld4, this._fld6, 0, this._fld3);
            this._fld8b = this.createImage(this._fld3, this._fld4);
            this._fld9b = this._fld8b.getGraphics();
            this._fld5 = new int[this._fld3 * this._fld4];
            final int n = 0xFF000000 | this._fld8c.getRGB();
            for (int i = 0; i < this._fld3 * this._fld4; ++i) {
                this._fld5[i] = n;
            }
            this._mth5b();
            this._mth1();
            this._mth5();
            this._mth4();
            this._mth3b();
            this._mth7();
            if (this._fld5c == -16777216) {
                this._fld1 = 1;
            }
            this._fld6c = true;
        }
        this.showStatus(this._fld3d);
        System.gc();
        final Graphics graphics2 = this.getGraphics();
        this._fld0b = System.currentTimeMillis();
        while (this._fld0 != null) {
            if (this._fld1 == 1) {
                this._mth9();
            }
            this.paint(graphics2);
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
