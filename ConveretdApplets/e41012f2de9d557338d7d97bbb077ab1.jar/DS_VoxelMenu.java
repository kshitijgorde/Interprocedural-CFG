import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.image.ImageProducer;
import java.net.MalformedURLException;
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

public class DS_VoxelMenu extends Applet implements Runnable
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
    Image _fld9b;
    Graphics _fld0c;
    int _fld1c;
    int _fld2c;
    int _fld3c;
    int _fld4c;
    int _fld5c;
    int _fld6c;
    int _fld7c;
    int _fld8c;
    int _fld9c;
    String _fld0d;
    boolean _fld1d;
    int _fld2d;
    int _fld3d;
    int _fld4d;
    int[] _fld5d;
    int[] _fld6d;
    int _fld7d;
    double _fld8d;
    double _fld9d;
    double _fld0e;
    int x0;
    int y0;
    int[] _fld1e;
    int[] _fld2e;
    int[] _fld3e;
    int[] _fld4e;
    double _fld5e;
    String _fld6e;
    URL _fld7e;
    boolean _fld8e;
    boolean _fld9e;
    byte[] _fld0f;
    byte[] _fld1f;
    String[] _fld2f;
    Color _fld3f;
    Color _fld4f;
    Font _fld5f;
    FontMetrics _fld6f;
    int _fld7f;
    int _fld8f;
    boolean _fld9f;
    String[] _fld0g;
    String[] _fld1g;
    URL[] _fld2g;
    int _fld3g;
    
    public DS_VoxelMenu() {
        this._fld3 = 0;
        this._fld4 = new Font("Helvetica", 1, 12);
        this._fld0b = 0;
        this._fld2b = "Applet by Dario Sciacca";
        this._fld3b = "dario@dseffects.com";
        this._fld4b = 0;
        this._fld6b = "www.dseffects.com";
        this._fld7b = "Don't remove Dario Sciacca's credits line";
        this._fld8b = String.valueOf(this._fld2b) + " (" + this._fld6b + ")";
        this._fld0d = "VoxelMenu started";
        this._fld1d = false;
        this._fld5d = new int[256];
        this._fld7d = 6;
        this._fld1e = new int[65536];
        this._fld2e = new int[65536];
        this._fld5e = 0.7853981635;
        this._fld6e = "";
        this._fld8e = false;
        this._fld9e = false;
        this._fld7f = 1;
    }
    
    void _mth0(final int[] array) {
        for (int i = 0; i < 65536; ++i) {
            array[i] = 0;
        }
        int n2;
        for (int j = 256; j > 1; j = n2) {
            final int n = j * this._fld7d;
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
                    array[(n9 << 8) + n10] = this._mth4(n5 + n6 + n7 + n8 >> 2, n);
                    array[(n3 << 8) + n10] = this._mth4(n6 + n8 >> 1, n);
                    array[(n9 << 8) + n4] = this._mth4(n7 + n8 >> 1, n);
                    if (k == 0) {
                        array[(n9 << 8) + l] = this._mth4(n5 + n6 >> 1, n);
                    }
                    if (l == 0) {
                        array[(k << 8) + n10] = this._mth4(n5 + n7 >> 1, n);
                    }
                }
            }
        }
    }
    
    void _mth0b() {
        int fld1c = 0;
        do {
            ++fld1c;
        } while (this.getParameter("text" + fld1c) != null);
        if (--fld1c == 0 || fld1c == 1) {
            this._fld1c = 2;
        }
        else if (fld1c > 1) {
            this._fld1c = fld1c;
        }
        this._fld2f = new String[this._fld1c];
        this._fld2g = new URL[this._fld1c];
        this._fld0g = new String[this._fld1c];
        this._fld1g = new String[this._fld1c];
        for (int i = 0; i < this._fld1c; ++i) {
            this._fld2f[i] = this.getParameter("text" + String.valueOf(i + 1));
            if (this._fld2f[i] == null) {
                this._fld2f[i] = new String("Text" + String.valueOf(i + 1));
            }
            if (this._fld2f[i].length() > this._fld7f) {
                this._fld7f = this._fld2f[i].length();
            }
            final String parameter = this.getParameter("link" + String.valueOf(i + 1));
            try {
                this._fld2g[i] = new URL("http://" + parameter);
            }
            catch (MalformedURLException ex) {
                this._fld2g[i] = null;
            }
            final String parameter2 = this.getParameter("regtarget" + String.valueOf(i + 1));
            if (parameter2 != null && this._fld8e) {
                this._fld0g[i] = parameter2;
            }
            else {
                this._fld0g[i] = "_blank";
            }
            final String parameter3 = this.getParameter("regstatusmsg" + String.valueOf(i + 1));
            if (parameter3 != null && this._fld8e) {
                this._fld1g[i] = parameter3;
            }
            else {
                this._fld1g[i] = this._fld2b;
            }
        }
        final String parameter4 = this.getParameter("menudir");
        if (parameter4 == null) {
            this._fld8f = 0;
        }
        else if (parameter4.equalsIgnoreCase("vertical")) {
            this._fld8f = 0;
        }
        else {
            this._fld8f = 1;
        }
        final String parameter5 = this.getParameter("textshadow");
        if (parameter5 == null) {
            this._fld9f = false;
        }
        else if (parameter5.equalsIgnoreCase("yes")) {
            this._fld9f = true;
        }
        else {
            this._fld9f = false;
        }
        this._fld3f = this._mth8("seltextcol", new Color(16711680));
        this._fld4f = this._mth8("unseltextcol", new Color(16776960));
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
        if (this._fld8f == 0) {
            n2 = this._fld5 / (this._fld7f + 2) * 2;
            final int n3 = this._fld6 / (this._fld1c << 1);
            if (n3 < n2) {
                n2 = n3;
            }
        }
        else {
            n2 = this._fld5 / this._fld1c / (this._fld7f + 1) * 2;
        }
        this._fld5f = new Font(parameter6, n, n2);
        this._fld6f = this._fld0c.getFontMetrics(this._fld5f);
    }
    
    void _mth1b() {
        this._mth0(this._fld1e);
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 65536; j += 256) {
                for (int k = 0; k < 256; ++k) {
                    this._fld1e[j + k] = this._fld1e[(j + 256 & 0xFF00) + k] + this._fld1e[j + (k + 1 & 0xFF)] + this._fld1e[(j - 256 & 0xFF00) + k] + this._fld1e[j + (k - 1 & 0xFF)] >> 2;
                }
            }
        }
        for (int l = 0; l < 65536; l += 256) {
            for (int n = 0; n < 256; ++n) {
                int n2 = 128 + (this._fld1e[(l + 256 & 0xFF00) + (n + 1 & 0xFF)] - this._fld1e[l + n]) * 4;
                if (n2 < 0) {
                    n2 = 0;
                }
                if (n2 > 255) {
                    n2 = 255;
                }
                this._fld2e[l + n] = n2;
            }
        }
        this._fld0e = 0.0;
        final boolean b = false;
        this.y0 = (b ? 1 : 0);
        this.x0 = (b ? 1 : 0);
        this._fld2d *= 2048;
        this._fld8d = this._fld2d * 10;
        this._fld9d = 0.0;
        this._fld3e = new int[this._fld5];
        this._fld4e = new int[this._fld5];
        switch (this._fld3d) {
            case 1: {
                this._mth9b(this._fld5d, 0, 255, 0, 255, 0, 255, 0, 64);
                break;
            }
            case 2: {
                this._mth9b(this._fld5d, 0, 255, 0, 0, 0, 255, 0, 0);
                break;
            }
            case 3: {
                this._mth9b(this._fld5d, 0, 255, 0, 255, 0, 127, 0, 0);
                break;
            }
        }
        this._fld6d = new int[this._fld6];
        switch (this._fld4d) {
            case 1: {
                this._mth9b(this._fld6d, 0, this._fld6, 0, 0, 64, 255, 128, 255);
                break;
            }
            case 2: {
                this._mth9b(this._fld6d, 0, this._fld6, 0, 0, 0, 127, 128, 255);
                break;
            }
            case 3: {
                this._mth9b(this._fld6d, 0, this._fld6, 0, 0, 0, 0, 64, 255);
                break;
            }
        }
    }
    
    void _mth2b() {
        this.x0 += (int)(this._fld8d * Math.cos(this._fld0e));
        this.y0 += (int)(this._fld8d * Math.sin(this._fld0e));
        this._fld0e += this._fld9d;
        this._mth5(this.x0, this.y0, this._fld0e);
        this._fld9 = this.createImage(this._fld8);
    }
    
    void _mth3(int n, int n2, final int n3, final int n4, final int n5, final int n6) {
        final int n7 = (n3 - n) / this._fld5;
        final int n8 = (n4 - n2) / this._fld5;
        for (int i = 0; i < this._fld5; ++i) {
            final int n9 = n >> 16 & 0xFF;
            final int n10 = n >> 8 & 0xFF;
            final int n11 = n2 >> 8 & 0xFF00;
            final int n12 = n2 >> 8 & 0xFF;
            final int n13 = n9 + 1 & 0xFF;
            final int n14 = n11 + 256 & 0xFF00;
            final int n15 = this._fld1e[n9 + n11];
            final int n16 = this._fld1e[n9 + n14];
            final int n17 = this._fld1e[n13 + n11];
            final int n18 = this._fld1e[n13 + n14];
            final int n19 = (n15 << 8) + n10 * (n17 - n15);
            final int n20 = (n19 << 8) + n12 * ((n16 << 8) + n10 * (n18 - n16) - n19) >> 16;
            final int n21 = this._fld2e[n9 + n11];
            final int n22 = this._fld2e[n9 + n14];
            final int n23 = this._fld2e[n13 + n11];
            final int n24 = this._fld2e[n13 + n14];
            final int n25 = (n21 << 8) + n10 * (n23 - n21);
            final int n26 = (n25 << 8) + n12 * ((n22 << 8) + n10 * (n24 - n22) - n25);
            int j = ((n20 - n5) * n6 >> 11) + (this._fld6 >> 1);
            final int n27 = this._fld6 - 1;
            int n28;
            if (j < (n28 = this._fld3e[i])) {
                if (this._fld4e[i] == -1) {
                    this._fld4e[i] = n26;
                }
                final int n29 = (n26 - this._fld4e[i]) / (n28 - j);
                int n30 = this._fld4e[i];
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
                    this._fld7[n32] = this._fld5d[n30 >> 17 & 0xFF];
                    n30 += n29;
                    final int n33 = n32 - this._fld5;
                    --n28;
                }
                this._fld3e[i] = j;
            }
            this._fld4e[i] = n26;
            n += n7;
            n2 += n8;
        }
    }
    
    void _mth3b(final Graphics graphics) {
        for (int i = 0; i < this._fld1c; ++i) {
            this._fld2c = this._fld6f.stringWidth(this._fld2f[i]);
            this._fld3c = this._fld6f.getHeight();
            final int n = (this._fld3c >> 1) + (this._fld3c >> 3);
            graphics.setFont(this._fld5f);
            if (this._fld8f == 0) {
                if (this._fld9f) {
                    graphics.setColor(new Color(0));
                    graphics.drawString(this._fld2f[i], (this._fld5 >> 1) - (this._fld2c >> 1) + 1, (this._fld6 / this._fld1c >> 2) + this._fld6 / this._fld1c * i + n + 1);
                }
                int fld6 = this._fld6 / this._fld1c * ((i + 1) % this._fld1c);
                if (i == this._fld1c - 1) {
                    fld6 = this._fld6;
                }
                final int n2 = this._fld6 / this._fld1c * i;
                if (this._fld1d && this._fld9c >= n2 && this._fld9c < fld6) {
                    this._fld3g = i;
                    graphics.setColor(this._fld3f);
                    this.setCursor(Cursor.getPredefinedCursor(12));
                }
                else {
                    graphics.setColor(this._fld4f);
                }
                graphics.drawString(this._fld2f[i], (this._fld5 >> 1) - (this._fld2c >> 1), (this._fld6 / this._fld1c >> 2) + this._fld6 / this._fld1c * i + n);
            }
            else {
                if (this._fld9f) {
                    graphics.setColor(new Color(0));
                    graphics.drawString(this._fld2f[i], this._fld5 / this._fld1c * i + (this._fld5 / this._fld1c >> 1) - (this._fld2c >> 1) + 1, (this._fld6 >> 1) + (n >> 1) + 1);
                }
                int fld7 = this._fld5 / this._fld1c * ((i + 1) % this._fld1c);
                if (i == this._fld1c - 1) {
                    fld7 = this._fld5;
                }
                final int n3 = this._fld5 / this._fld1c * i;
                if (this._fld1d && this._fld8c >= n3 && this._fld8c < fld7) {
                    this._fld3g = i;
                    graphics.setColor(this._fld3f);
                    this.setCursor(Cursor.getPredefinedCursor(12));
                }
                else {
                    graphics.setColor(this._fld4f);
                }
                graphics.drawString(this._fld2f[i], this._fld5 / this._fld1c * i + (this._fld5 / this._fld1c >> 1) - (this._fld2c >> 1), (this._fld6 >> 1) + (n >> 1));
            }
        }
    }
    
    int _mth4(final int n, final int n2) {
        final int n3 = n + (int)(Math.random() * n2) - (n2 >> 1);
        return (n3 >= 0) ? ((n3 <= 255) ? n3 : 255) : 0;
    }
    
    void _mth4b() {
        final String parameter = this.getParameter("credits");
        if (parameter != null) {
            if (!parameter.equals(this._fld8b)) {
                this._mth7();
            }
        }
        else {
            this._mth7();
        }
        this._fld4c = 1;
    }
    
    void _mth5(final int n, final int n2, final double n3) {
        for (int i = this._fld6 - 1; i >= 0; --i) {
            for (int j = 0; j < this._fld5; ++j) {
                this._fld7[i * this._fld5 + j] = this._fld6d[i];
            }
        }
        for (int k = 0; k < this._fld5; ++k) {
            this._fld3e[k] = this._fld6;
            this._fld4e[k] = -1;
        }
        final int n4 = n >> 16 & 0xFF;
        final int n5 = n >> 8 & 0xFF;
        final int n6 = n2 >> 8 & 0xFF00;
        final int n7 = n2 >> 8 & 0xFF;
        final int n8 = n4 + 1 & 0xFF;
        final int n9 = n6 + 256 & 0xFF00;
        final int n10 = this._fld1e[n4 + n6];
        final int n11 = this._fld1e[n4 + n9];
        final int n12 = this._fld1e[n8 + n6];
        final int n13 = this._fld1e[n8 + n9];
        final int n14 = (n10 << 8) + n5 * (n12 - n10);
        final int n15 = (n14 << 8) + n7 * ((n11 << 8) + n5 * (n13 - n11) - n14) >> 16;
        for (int l = 0; l < 100; l += 1 + (l >> 6)) {
            this._mth3((int)(n + l * 65536 * Math.cos(n3 - this._fld5e)), (int)(n2 + l * 65536 * Math.sin(n3 - this._fld5e)), (int)(n + l * 65536 * Math.cos(n3 + this._fld5e)), (int)(n2 + l * 65536 * Math.sin(n3 + this._fld5e)), n15 - 30, 25600 / (l + 1));
        }
    }
    
    void _mth5b() {
        this._fld5c = 1;
        for (int i = 0; i < 11; ++i) {
            if (this._fld3b.charAt(i) == this._fld2b.charAt(i) || this._fld4c == 0) {
                this._mth7();
            }
        }
        this._fld8e = false;
    }
    
    void _mth6() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this._fld6e.length() > 9) {
            final int n = this._fld6e.length() - 9;
            final int n2 = n + 9;
            this._fld0f = new byte[n];
            this._fld6e.getBytes(1, n + 1, this._fld0f, 0);
            this._fld1f = new byte[n2];
            this._fld6e.getBytes(0, n2, this._fld1f, 0);
            int n3 = n % 7;
            final int n4 = n % 3;
            for (int i = 0; i < n; ++i) {
                final byte b = this._fld0f[i];
                final byte b2 = (byte)(b + n3);
                if (b >= 48 && b <= 57) {
                    this._fld0f[i] = ((b2 <= 57) ? b2 : ((byte)(b2 - 10)));
                }
                else if (b >= 65 && b <= 90) {
                    this._fld0f[i] = ((b2 <= 90) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b >= 97 && b <= 122) {
                    this._fld0f[i] = ((b2 <= 122) ? b2 : ((byte)(b2 - 26)));
                }
                else if (b == 42) {
                    this._fld0f[i] = 45;
                }
                else if (b == 45) {
                    this._fld0f[i] = 46;
                }
                n3 = (n3 + n4) % 7;
            }
            int n5 = n % 7;
            final int n6 = n % 3;
            for (int j = 0; j < n; ++j) {
                final byte b3 = this._fld0f[j];
                final byte b4 = (byte)(b3 - n5);
                if (b3 >= 48 && b3 <= 57) {
                    this._fld1f[j + 1] = ((b4 >= 48) ? b4 : ((byte)(b4 + 10)));
                }
                else if (b3 >= 65 && b3 <= 90) {
                    this._fld1f[j + 1] = ((b4 >= 65) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 >= 97 && b3 <= 122) {
                    this._fld1f[j + 1] = ((b4 >= 97) ? b4 : ((byte)(b4 + 26)));
                }
                else if (b3 == 45) {
                    this._fld1f[j + 1] = 42;
                }
                else if (b3 == 46) {
                    this._fld1f[j + 1] = 45;
                }
                n5 = (n5 + n6) % 7;
            }
            byte[] array;
            if (n > 4) {
                array = new byte[n - 4];
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld0f[k + 4];
                }
            }
            else {
                array = new byte[] { 0 };
            }
            if (this._fld1f[0] == this._fld1f[n >> 2] && this._fld1f[1 + n] == this._fld1f[n >> 1] && this._fld1f[1 + n + 1] == this._fld1f[n >> 1] && this._fld1f[1 + n + 2] == (byte)(112 + n6) && this._fld1f[1 + n + 3] == 45 && this._fld1f[1 + n + 4] == (byte)(112 - n5) && this._fld1f[1 + n + 5] == (byte)(110 + n6) && this._fld1f[1 + n + 6] == this._fld1f[n] && this._fld1f[1 + n + 7] == this._fld1f[n >> 1] && (host.equalsIgnoreCase(new String(this._fld0f, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this._fld8e = true;
            }
        }
        try {
            this._fld7e = new URL("http://" + this._fld6b);
        }
        catch (MalformedURLException ex) {
            this._fld7e = null;
        }
    }
    
    void _mth6b() {
        if (this._fld4c == 0 || this._fld5c == 0) {
            this._mth7();
        }
        for (int i = 0; i < 17; ++i) {
            if (this._fld3b.charAt(i) == this._fld6b.charAt(i)) {
                this._mth7();
            }
        }
        this._fld6c = -16777216;
        if (this._fld2b.charAt(1) != 'p' || this._fld2b.charAt(7) != 'b' || this._fld2b.charAt(21) != 'c' || this._fld2b.charAt(17) != 'c' || this._fld2b.charAt(12) != 'r' || this._fld2b.charAt(11) != 'a') {
            this._mth7();
        }
    }
    
    private final void _mth7() {
        while (true) {
            this.showStatus(this._fld7b);
        }
    }
    
    void _mth7b(final Graphics graphics) {
        final int n = this._fld5 >> 1;
        int n2;
        if (this._fld7c < 200) {
            n2 = 10;
        }
        else if (this._fld7c < 400) {
            n2 = this._fld6 >> 1;
        }
        else {
            n2 = this._fld6 - 10;
        }
        ++this._fld7c;
        this._fld7c %= 600;
        if (this._fld3 == 0) {
            graphics.drawString("Error ...", 10, 10);
        }
        else {
            if (this._fld9 != null) {
                this._fld0c.drawImage(this._fld9, 0, 0, this);
                this._mth3b(this._fld0c);
            }
            if (this._fld1d && !this._fld8e) {
                this._fld0c.setColor(Color.white);
                this._fld0c.drawLine(n - 64, n2 - 8, n + 64, n2 - 8);
                this._fld0c.drawLine(n - 64, n2 + 8, n + 64, n2 + 8);
                this._fld0c.drawLine(n - 64, n2 - 8, n - 64, n2 + 8);
                this._fld0c.drawLine(n + 64, n2 - 8, n + 64, n2 + 8);
                this._fld0c.setColor(Color.blue);
                this._fld0c.fillRect(n - 63, n2 - 7, 127, 15);
                this._fld0c.setFont(this._fld4);
                this._fld0c.setColor(Color.yellow);
                this._fld0c.drawString(this._fld6b, n - (this._fld5b >> 1), n2 + 5);
                if (this._fld8c > n - 64 && this._fld8c < n + 64 && this._fld9c > n2 - 8 && this._fld9c < n2 + 8) {
                    this._fld9e = true;
                    this.showStatus(this._fld6b);
                }
                else {
                    this._fld9e = false;
                }
            }
            graphics.drawImage(this._fld9b, 0, 0, this);
        }
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
    
    synchronized void _mth8b() {
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
    
    void _mth9() {
        String parameter = this.getParameter("terrainpal");
        if (parameter == null) {
            parameter = "1";
        }
        this._fld3d = Integer.valueOf(parameter);
        this._fld3d = ((this._fld3d >= 1) ? ((this._fld3d <= 3) ? this._fld3d : 3) : 1);
        String parameter2 = this.getParameter("skypal");
        if (parameter2 == null) {
            parameter2 = "1";
        }
        this._fld4d = Integer.valueOf(parameter2);
        this._fld4d = ((this._fld4d >= 1) ? ((this._fld4d <= 3) ? this._fld4d : 3) : 1);
        String parameter3 = this.getParameter("speed");
        if (parameter3 == null) {
            parameter3 = "2";
        }
        this._fld2d = Integer.valueOf(parameter3);
        this._fld2d = ((this._fld2d >= 1) ? ((this._fld2d <= 4) ? this._fld2d : 4) : 1);
    }
    
    void _mth9b(final int[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        final int n9 = n2 - n;
        final int n10 = n4 - n3;
        final int n11 = n6 - n5;
        final int n12 = n8 - n7;
        for (int i = 0; i < n9; ++i) {
            array[n + i] = (0xFF000000 | n3 + i * n10 / n9 << 16 | n5 + i * n11 / n9 << 8 | n7 + i * n12 / n9);
        }
    }
    
    public String getAppletInfo() {
        return "DS VoxelMenu v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
    }
    
    public void init() {
        this._mth4b();
        this.showStatus("Please wait ...");
        this._fld5b = this.getFontMetrics(this._fld4).stringWidth(this._fld6b);
        this._fld5 = this.size().width;
        this._fld6 = this.size().height;
        this._mth5b();
        this._fld7 = new int[this._fld5 * this._fld6];
        this._fld8 = new MemoryImageSource(this._fld5, this._fld6, this._fld7, 0, this._fld5);
        this._fld9b = this.createImage(this._fld5, this._fld6);
        this._fld0c = this._fld9b.getGraphics();
        final String parameter = this.getParameter("regkey");
        if (parameter != null) {
            this._fld6e = parameter;
        }
        this._mth6();
        this._mth0b();
        this._mth9();
        this._mth6b();
        this._mth1b();
        if (this._fld6c == -16777216) {
            this._fld3 = 1;
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld8e && this._fld9e) {
            this.getAppletContext().showDocument(this._fld7e, "_blank");
        }
        else if (this._fld2g[this._fld3g] != null) {
            if (this._fld8e && this._fld0g[this._fld3g] != null) {
                this.getAppletContext().showDocument(this._fld2g[this._fld3g], this._fld0g[this._fld3g]);
            }
            else {
                this.getAppletContext().showDocument(this._fld2g[this._fld3g], "_blank");
            }
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this._fld1g[this._fld3g]);
        return this._fld1d = true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this._fld1d = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int fld8c, final int fld9c) {
        this.showStatus(this._fld1g[this._fld3g]);
        this._fld8c = fld8c;
        this._fld9c = fld9c;
        return this._fld1d = true;
    }
    
    public void run() {
        this.showStatus(this._fld0d);
        System.gc();
        final Graphics graphics = this.getGraphics();
        this._fld1b = System.currentTimeMillis();
        while (this._fld0 != null) {
            if (this._fld3 == 1) {
                this._mth2b();
            }
            this._mth7b(graphics);
            this._mth8b();
            if (this._fld0b++ > 10) {
                System.gc();
                this._fld0b = 0;
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
