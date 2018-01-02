import java.awt.Event;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.image.ImageProducer;
import java.net.MalformedURLException;
import java.awt.Component;
import java.awt.MediaTracker;
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

public class DS_BlobsMenu extends Applet implements Runnable
{
    Thread _fld0;
    int _fld1;
    Font _fld2;
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
    Image _fld0c;
    Graphics _fld1c;
    int _fld2c;
    int _fld3c;
    int _fld4c;
    int _fld5c;
    int _fld6c;
    int _fld7c;
    boolean _fld8c;
    String _fld9c;
    Color _fld0d;
    Color _fld1d;
    int _fld2d;
    int _fld3d;
    int _fld4d;
    String _fld5d;
    boolean _fld6d;
    int _fld7d;
    int[] _fld8d;
    int[] _fld9d;
    int[] _fld0e;
    int[] _fld1e;
    int[] _fld2e;
    int[] _fld3e;
    int[] tr;
    int[] tg;
    int[] tb;
    int _fld4e;
    int _fld5e;
    double _fld6e;
    int[] _fld7e;
    int _fld8e;
    int _fld9e;
    double _fld0f;
    String _fld1f;
    URL _fld2f;
    boolean _fld3f;
    boolean _fld4f;
    byte[] _fld5f;
    byte[] _fld6f;
    String[] _fld7f;
    Color _fld8f;
    Color _fld9f;
    Font _fld0g;
    FontMetrics _fld1g;
    int _fld2g;
    int _fld3g;
    boolean _fld4g;
    String[] _fld5g;
    String[] _fld6g;
    URL[] _fld7g;
    int _fld8g;
    
    public DS_BlobsMenu() {
        this._fld1 = 0;
        this._fld2 = new Font("Helvetica", 1, 12);
        this._fld1b = 0;
        this._fld3b = "Applet by Dario Sciacca";
        this._fld4b = "dario@dseffects.com";
        this._fld5b = 0;
        this._fld7b = "www.dseffects.com";
        this._fld8b = "Don't remove Dario Sciacca's credits line";
        this._fld9b = String.valueOf(this._fld3b) + " (" + this._fld7b + ")";
        this._fld8c = false;
        this._fld9c = "";
        this._fld5d = "BlobsMenu started";
        this._fld6d = false;
        this._fld1f = "";
        this._fld3f = false;
        this._fld4f = false;
        this._fld2g = 1;
    }
    
    void _mth0() {
        this._fld6e += this._fld0f;
        final int n = (int)((Math.sin(this._fld6e + 1.2) * 0.4 + 0.5) * this._fld5);
        final int n2 = (int)((Math.cos(this._fld6e * 1.2 + 1.8) * 0.4 + 0.5) * this._fld6);
        final int n3 = (int)((Math.sin(this._fld6e * 1.6 + 2.6) * 0.4 + 0.5) * this._fld5);
        final int n4 = (int)((Math.cos(this._fld6e * 1.8 + 3.8) * 0.4 + 0.5) * this._fld6);
        final int n5 = (int)((Math.sin(this._fld6e * 2.2 + 4.4) * 0.4 + 0.5) * this._fld5);
        final int n6 = (int)((Math.cos(this._fld6e * 2.6 + 4.8) * 0.4 + 0.5) * this._fld6);
        int n7 = 0;
        for (int i = 0; i < this._fld6; ++i) {
            for (int j = 0; j < this._fld5; ++j) {
                final int n8 = (j - n) * (j - n) + (i - n2) * (i - n2) >> 7;
                final int n9 = (j - n3) * (j - n3) + (i - n4) * (i - n4) >> 7;
                final int n10 = (j - n5) * (j - n5) + (i - n6) * (i - n6) >> 7;
                final int n11 = ((n8 > 255) ? 0 : this._fld7e[n8]) + ((n9 > 255) ? 0 : this._fld7e[n9]) + ((n10 > 255) ? 0 : this._fld7e[n10]);
                if (n11 == 0) {
                    this._fld8[n7] = this._fld7[n7];
                }
                else {
                    final int n12 = this.tr[n7] + this._fld8d[n11];
                    final int n13 = this.tg[n7] + this._fld9d[n11];
                    final int n14 = this.tb[n7] + this._fld0e[n11];
                    this._fld8[n7] = (0xFF000000 | ((n12 <= 255) ? n12 : 255) << 16 | ((n13 <= 255) ? n13 : 255) << 8 | ((n14 <= 255) ? n14 : 255));
                }
                ++n7;
            }
        }
    }
    
    void _mth0b() {
        this._fld4e = 256;
        this._fld8d = new int[this._fld4e];
        this._fld9d = new int[this._fld4e];
        this._fld0e = new int[this._fld4e];
        this._fld1e = new int[this._fld4e];
        this._fld2e = new int[this._fld4e];
        this._fld3e = new int[this._fld4e];
        this._mth1();
        this._fld7d = 0;
        for (int i = 0; i < this._fld4e; ++i) {
            this._fld8d[i] = 0;
            this._fld9d[i] = 0;
            this._fld0e[i] = 0;
        }
        this.tr = new int[this._fld5 * this._fld6];
        this.tg = new int[this._fld5 * this._fld6];
        this.tb = new int[this._fld5 * this._fld6];
        for (int j = 0; j < this._fld5 * this._fld6; ++j) {
            final int n = this._fld7[j];
            this.tr[j] = (n >> 16 & 0xFF);
            this.tg[j] = (n >> 8 & 0xFF);
            this.tb[j] = (n & 0xFF);
        }
        this._fld6e = 0.0;
        this._fld7e = new int[256];
        for (int k = 0; k < 256; ++k) {
            this._fld7e[k] = (int)(this._fld9e * 21 * Math.exp(-0.05 * k));
        }
    }
    
    void _mth0c(final int n, final int[] array, final int[] array2, final int[] array3, final int[] array4, final int[] array5, final int[] array6) {
        for (int i = 0; i < this._fld4e; ++i) {
            array[i] = array4[i * n >> 8];
            array2[i] = array5[i * n >> 8];
            array3[i] = array6[i * n >> 8];
        }
    }
    
    void _mth1() {
        switch (this._fld5e) {
            case 1: {
                this._mth1c(0, 255, 0, 0, 0, 0, 0, 255);
                break;
            }
            case 2: {
                this._mth1c(0, 255, 0, 0, 0, 255, 0, 0);
                break;
            }
            case 3: {
                this._mth1c(0, 255, 0, 255, 0, 0, 0, 0);
                break;
            }
            case 4: {
                this._mth1c(0, 255, 0, 255, 0, 255, 0, 0);
                break;
            }
            case 5: {
                this._mth1c(0, 255, 0, 255, 0, 0, 0, 255);
                break;
            }
            case 6: {
                this._mth1c(0, 255, 0, 0, 0, 255, 0, 255);
                break;
            }
            case 7: {
                this._mth1c(0, 255, 0, 255, 0, 255, 0, 255);
                break;
            }
            case 8: {
                this._mth1c(0, 255, 0, 127, 0, 127, 0, 255);
                break;
            }
        }
    }
    
    Image _mth1b(final String s) {
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
    
    void _mth1c(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        final int n9 = n2 - n;
        final int n10 = n4 - n3;
        final int n11 = n6 - n5;
        final int n12 = n8 - n7;
        for (int i = 0; i < n9; ++i) {
            this._fld1e[n + i] = n3 + i * n10 / n9;
            this._fld2e[n + i] = n5 + i * n11 / n9;
            this._fld3e[n + i] = n7 + i * n12 / n9;
        }
    }
    
    void _mth2() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this._fld1f.length() > 9) {
            final int n = this._fld1f.length() - 9;
            final int n2 = n + 9;
            this._fld5f = new byte[n];
            this._fld1f.getBytes(1, n + 1, this._fld5f, 0);
            this._fld6f = new byte[n2];
            this._fld1f.getBytes(0, n2, this._fld6f, 0);
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
            byte[] array;
            if (n > 4) {
                array = new byte[n - 4];
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this._fld5f[k + 4];
                }
            }
            else {
                array = new byte[] { 0 };
            }
            if (this._fld6f[0] == this._fld6f[n >> 2] && this._fld6f[1 + n] == this._fld6f[n >> 1] && this._fld6f[1 + n + 1] == this._fld6f[n >> 1] && this._fld6f[1 + n + 2] == (byte)(112 + n6) && this._fld6f[1 + n + 3] == 45 && this._fld6f[1 + n + 4] == (byte)(112 - n5) && this._fld6f[1 + n + 5] == (byte)(110 + n6) && this._fld6f[1 + n + 6] == this._fld6f[n] && this._fld6f[1 + n + 7] == this._fld6f[n >> 1] && (host.equalsIgnoreCase(new String(this._fld5f, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this._fld3f = true;
            }
        }
        try {
            this._fld2f = new URL("http://" + this._fld7b);
        }
        catch (MalformedURLException ex) {
            this._fld2f = null;
        }
    }
    
    void _mth2b() {
        this._fld7d += 4;
        if (this._fld7d > this._fld4e) {
            this._fld7d = this._fld4e;
        }
        else {
            this._mth0c(this._fld7d, this._fld8d, this._fld9d, this._fld0e, this._fld1e, this._fld2e, this._fld3e);
        }
        this._mth0();
        this._fld0b = this.createImage(this._fld9);
    }
    
    void _mth3b(final Graphics graphics) {
        for (int i = 0; i < this._fld2c; ++i) {
            this._fld3c = this._fld1g.stringWidth(this._fld7f[i]);
            this._fld4c = this._fld1g.getHeight();
            final int n = (this._fld4c >> 1) + (this._fld4c >> 3);
            graphics.setFont(this._fld0g);
            if (this._fld3g == 0) {
                if (this._fld4g) {
                    graphics.setColor(new Color(0));
                    graphics.drawString(this._fld7f[i], (this._fld5 >> 1) - (this._fld3c >> 1) + 1, (this._fld6 / this._fld2c >> 2) + this._fld6 / this._fld2c * i + n + 1);
                }
                int fld6 = this._fld6 / this._fld2c * ((i + 1) % this._fld2c);
                if (i == this._fld2c - 1) {
                    fld6 = this._fld6;
                }
                final int n2 = this._fld6 / this._fld2c * i;
                if (this._fld6d && this._fld4d >= n2 && this._fld4d < fld6) {
                    this._fld8g = i;
                    graphics.setColor(this._fld8f);
                    this.setCursor(Cursor.getPredefinedCursor(12));
                }
                else {
                    graphics.setColor(this._fld9f);
                }
                graphics.drawString(this._fld7f[i], (this._fld5 >> 1) - (this._fld3c >> 1), (this._fld6 / this._fld2c >> 2) + this._fld6 / this._fld2c * i + n);
            }
            else {
                if (this._fld4g) {
                    graphics.setColor(new Color(0));
                    graphics.drawString(this._fld7f[i], this._fld5 / this._fld2c * i + (this._fld5 / this._fld2c >> 1) - (this._fld3c >> 1) + 1, (this._fld6 >> 1) + (n >> 1) + 1);
                }
                int fld7 = this._fld5 / this._fld2c * ((i + 1) % this._fld2c);
                if (i == this._fld2c - 1) {
                    fld7 = this._fld5;
                }
                final int n3 = this._fld5 / this._fld2c * i;
                if (this._fld6d && this._fld3d >= n3 && this._fld3d < fld7) {
                    this._fld8g = i;
                    graphics.setColor(this._fld8f);
                    this.setCursor(Cursor.getPredefinedCursor(12));
                }
                else {
                    graphics.setColor(this._fld9f);
                }
                graphics.drawString(this._fld7f[i], this._fld5 / this._fld2c * i + (this._fld5 / this._fld2c >> 1) - (this._fld3c >> 1), (this._fld6 >> 1) + (n >> 1));
            }
        }
    }
    
    void _mth4b() {
        final String parameter = this.getParameter("credits");
        if (parameter != null) {
            if (!parameter.equals(this._fld9b)) {
                this._mth5();
            }
        }
        else {
            this._mth5();
        }
        this._fld5c = 1;
    }
    
    private final void _mth5() {
        while (true) {
            this.showStatus(this._fld8b);
        }
    }
    
    void _mth5b() {
        this._fld6c = 1;
        for (int i = 0; i < 11; ++i) {
            if (this._fld4b.charAt(i) == this._fld3b.charAt(i) || this._fld5c == 0) {
                this._mth5();
            }
        }
        this._fld3f = false;
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
    
    void _mth6b() {
        if (this._fld5c == 0 || this._fld6c == 0) {
            this._mth5();
        }
        for (int i = 0; i < 17; ++i) {
            if (this._fld4b.charAt(i) == this._fld7b.charAt(i)) {
                this._mth5();
            }
        }
        this._fld7c = -16777216;
        if (this._fld3b.charAt(1) != 'p' || this._fld3b.charAt(7) != 'b' || this._fld3b.charAt(21) != 'c' || this._fld3b.charAt(17) != 'c' || this._fld3b.charAt(12) != 'r' || this._fld3b.charAt(11) != 'a') {
            this._mth5();
        }
    }
    
    void _mth7() {
        String parameter = this.getParameter("blobsize");
        if (parameter == null) {
            parameter = "3";
        }
        this._fld9e = Integer.valueOf(parameter);
        this._fld9e = ((this._fld9e >= 1) ? ((this._fld9e <= 4) ? this._fld9e : 4) : 1);
        String parameter2 = this.getParameter("speed");
        if (parameter2 == null) {
            parameter2 = "4";
        }
        this._fld8e = Integer.valueOf(parameter2);
        this._fld8e = ((this._fld8e >= 1) ? ((this._fld8e <= 8) ? this._fld8e : 8) : 1);
        this._fld0f = this._fld8e / 100.0;
        String parameter3 = this.getParameter("palette");
        if (parameter3 == null) {
            parameter3 = "3";
        }
        this._fld5e = Integer.valueOf(parameter3);
        this._fld5e = ((this._fld5e >= 1) ? ((this._fld5e <= 8) ? this._fld5e : 8) : 1);
    }
    
    int[] _mth7b(final int[] array, final int n, final int n2, final int[] array2, final int n3, final int n4) {
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
    
    void _mth8() {
        int fld2c = 0;
        do {
            ++fld2c;
        } while (this.getParameter("text" + fld2c) != null);
        if (--fld2c == 0 || fld2c == 1) {
            this._fld2c = 2;
        }
        else if (fld2c > 1) {
            this._fld2c = fld2c;
        }
        this._fld7f = new String[this._fld2c];
        this._fld7g = new URL[this._fld2c];
        this._fld5g = new String[this._fld2c];
        this._fld6g = new String[this._fld2c];
        for (int i = 0; i < this._fld2c; ++i) {
            this._fld7f[i] = this.getParameter("text" + String.valueOf(i + 1));
            if (this._fld7f[i] == null) {
                this._fld7f[i] = new String("Text" + String.valueOf(i + 1));
            }
            if (this._fld7f[i].length() > this._fld2g) {
                this._fld2g = this._fld7f[i].length();
            }
            final String parameter = this.getParameter("link" + String.valueOf(i + 1));
            try {
                this._fld7g[i] = new URL("http://" + parameter);
            }
            catch (MalformedURLException ex) {
                this._fld7g[i] = null;
            }
            final String parameter2 = this.getParameter("regtarget" + String.valueOf(i + 1));
            if (parameter2 != null && this._fld3f) {
                this._fld5g[i] = parameter2;
            }
            else {
                this._fld5g[i] = "_blank";
            }
            final String parameter3 = this.getParameter("regstatusmsg" + String.valueOf(i + 1));
            if (parameter3 != null && this._fld3f) {
                this._fld6g[i] = parameter3;
            }
            else {
                this._fld6g[i] = this._fld3b;
            }
        }
        final String parameter4 = this.getParameter("menudir");
        if (parameter4 == null) {
            this._fld3g = 0;
        }
        else if (parameter4.equalsIgnoreCase("vertical")) {
            this._fld3g = 0;
        }
        else {
            this._fld3g = 1;
        }
        final String parameter5 = this.getParameter("textshadow");
        if (parameter5 == null) {
            this._fld4g = false;
        }
        else if (parameter5.equalsIgnoreCase("yes")) {
            this._fld4g = true;
        }
        else {
            this._fld4g = false;
        }
        this._fld8f = this._mth6("seltextcol", new Color(16711680));
        this._fld9f = this._mth6("unseltextcol", new Color(16776960));
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
        if (this._fld3g == 0) {
            n2 = this._fld5 / (this._fld2g + 2) * 2;
            final int n3 = this._fld6 / (this._fld2c << 1);
            if (n3 < n2) {
                n2 = n3;
            }
        }
        else {
            n2 = this._fld5 / this._fld2c / (this._fld2g + 1) * 2;
        }
        this._fld0g = new Font(parameter6, n, n2);
        this._fld1g = this._fld1c.getFontMetrics(this._fld0g);
    }
    
    boolean _mth8b() {
        final String parameter = this.getParameter("image");
        if (parameter != null) {
            final Image mth1b = this._mth1b(parameter);
            if (mth1b == null) {
                this.showStatus("Error loading image ");
                return false;
            }
            final int width = mth1b.getWidth(this);
            final int height = mth1b.getHeight(this);
            if (width != this._fld5 || height != this._fld6) {
                final int[] array = new int[width * height];
                if (!this._mth9(mth1b, array, width, height)) {
                    return false;
                }
                this._fld7 = this._mth7b(this._fld7, this._fld5, this._fld6, array, width, height);
            }
            else if (!this._mth9(mth1b, this._fld7, this._fld5, this._fld6)) {
                return false;
            }
            mth1b.flush();
            System.gc();
        }
        return true;
    }
    
    boolean _mth9(final Image image, final int[] array, final int n, final int n2) {
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
    
    synchronized void _mth9b() {
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
    
    public String getAppletInfo() {
        return "DS BlobsMenu v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
    }
    
    public void init() {
        this._mth4b();
        this.showStatus("Please wait ...");
        this._fld6b = this.getFontMetrics(this._fld2).stringWidth(this._fld7b);
        this._fld5 = this.size().width;
        this._fld6 = this.size().height;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this._fld3f && this._fld4f) {
            this.getAppletContext().showDocument(this._fld2f, "_blank");
        }
        else if (this._fld7g[this._fld8g] != null) {
            if (this._fld3f && this._fld5g[this._fld8g] != null) {
                this.getAppletContext().showDocument(this._fld7g[this._fld8g], this._fld5g[this._fld8g]);
            }
            else {
                this.getAppletContext().showDocument(this._fld7g[this._fld8g], "_blank");
            }
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this._fld6g[this._fld8g]);
        return this._fld6d = true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this._fld6d = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int fld3d, final int fld4d) {
        this.showStatus(this._fld6g[this._fld8g]);
        this._fld3d = fld3d;
        this._fld4d = fld4d;
        return this._fld6d = true;
    }
    
    public void paint(final Graphics graphics) {
        if (!this._fld8c) {
            return;
        }
        final int n = this._fld5 >> 1;
        int n2;
        if (this._fld2d < 200) {
            n2 = 10;
        }
        else if (this._fld2d < 400) {
            n2 = this._fld6 >> 1;
        }
        else {
            n2 = this._fld6 - 10;
        }
        ++this._fld2d;
        this._fld2d %= 600;
        if (this._fld1 == 0) {
            graphics.drawString("Error ...", 10, 10);
        }
        else {
            if (this._fld0b != null) {
                this._fld1c.drawImage(this._fld0b, 0, 0, this);
                this._mth3b(this._fld1c);
            }
            if (this._fld6d && !this._fld3f) {
                this._fld1c.setColor(Color.white);
                this._fld1c.drawLine(n - 64, n2 - 8, n + 64, n2 - 8);
                this._fld1c.drawLine(n - 64, n2 + 8, n + 64, n2 + 8);
                this._fld1c.drawLine(n - 64, n2 - 8, n - 64, n2 + 8);
                this._fld1c.drawLine(n + 64, n2 - 8, n + 64, n2 + 8);
                this._fld1c.setColor(Color.blue);
                this._fld1c.fillRect(n - 63, n2 - 7, 127, 15);
                this._fld1c.setFont(this._fld2);
                this._fld1c.setColor(Color.yellow);
                this._fld1c.drawString(this._fld7b, n - (this._fld6b >> 1), n2 + 5);
                if (this._fld3d > n - 64 && this._fld3d < n + 64 && this._fld4d > n2 - 8 && this._fld4d < n2 + 8) {
                    this._fld4f = true;
                    this.showStatus(this._fld7b);
                }
                else {
                    this._fld4f = false;
                }
            }
            graphics.drawImage(this._fld0c, 0, 0, this);
        }
    }
    
    public void run() {
        if (!this._fld8c) {
            this._fld9c = this.getParameter("loadtext");
            this._fld0d = this._mth6("loadbgcolor", new Color(0));
            this._fld1d = this._mth6("loadtextcolor", new Color(16777215));
            final String parameter = this.getParameter("regkey");
            if (parameter != null) {
                this._fld1f = parameter;
            }
            final Graphics graphics = this.getGraphics();
            graphics.setColor(this._fld0d);
            graphics.fillRect(0, 0, this._fld5, this._fld6);
            graphics.setColor(this._fld1d);
            graphics.drawString(this._fld9c, (this._fld5 >> 1) - (this.getFontMetrics(this._fld2).stringWidth(this._fld9c) >> 1), (this._fld6 >> 1) + 5);
            this._mth5b();
            this._fld8 = new int[this._fld5 * this._fld6];
            this._fld9 = new MemoryImageSource(this._fld5, this._fld6, this._fld8, 0, this._fld5);
            this._fld0c = this.createImage(this._fld5, this._fld6);
            this._fld1c = this._fld0c.getGraphics();
            this._fld7 = new int[this._fld5 * this._fld6];
            final int n = 0xFF000000 | this._fld0d.getRGB();
            for (int i = 0; i < this._fld5 * this._fld6; ++i) {
                this._fld7[i] = n;
            }
            this._mth8b();
            this._mth2();
            this._mth8();
            this._mth7();
            this._mth6b();
            this._mth0b();
            if (this._fld7c == -16777216) {
                this._fld1 = 1;
            }
            this._fld8c = true;
        }
        this.showStatus(this._fld5d);
        System.gc();
        final Graphics graphics2 = this.getGraphics();
        this._fld2b = System.currentTimeMillis();
        while (this._fld0 != null) {
            if (this._fld1 == 1) {
                this._mth2b();
            }
            this.paint(graphics2);
            this._mth9b();
            if (this._fld1b++ > 10) {
                System.gc();
                this._fld1b = 0;
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
