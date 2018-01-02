import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.io.IOException;
import java.io.DataInputStream;
import java.awt.Point;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class ClueView_int extends Canvas
{
    Grid_int App;
    CGrid_int JE;
    boolean FB;
    Color SelColor;
    int iq;
    String[] io;
    String[] ia;
    String[] URLs;
    int[] JV;
    int[] ie;
    int[][] CA;
    int urlBuffer;
    FontMetrics[] ih;
    Font[] JT;
    byte[] ic;
    byte[] id;
    byte[] ib;
    String ik;
    int iv;
    int JU;
    int W;
    int H;
    int JS;
    int ij;
    int iz;
    int JQ;
    int ja;
    int iy;
    int JP;
    int ir;
    boolean im;
    boolean is;
    boolean jc;
    boolean iu;
    boolean ip;
    boolean il;
    boolean it;
    FontMetrics FM;
    FontMetrics ig;
    Font AC;
    char[] JR;
    boolean DoURLs;
    String ClueURLtarget;
    private int URLstat;
    private String URLtxt;
    private Color URLcol;
    
    public ClueView_int() {
        this.FB = false;
        this.urlBuffer = 0;
        this.iv = 15;
        this.JU = 2;
        this.iz = 0;
        this.JQ = 0;
        this.iy = -1;
        this.JP = 0;
        this.im = false;
        this.URLstat = -1;
    }
    
    void ix(final Point point, final boolean b) {
        final int n = point.x + 1;
        final int n2 = point.y + 1;
        for (int i = 0; i < this.iq; ++i) {
            if (this.ic[i] == n && this.id[i] == n2 && b == (this.ib[i] == 1)) {
                this.jh(i);
                return;
            }
        }
        this.jh(-1);
    }
    
    void jc(final DataInputStream dataInputStream) throws IOException {
        this.ik = this.App.E(dataInputStream);
        this.iq = dataInputStream.readInt();
        this.ia = new String[this.iq];
        this.io = new String[this.iq];
        this.URLs = new String[this.iq];
        this.ic = new byte[this.iq];
        this.id = new byte[this.iq];
        this.ib = new byte[this.iq];
        if (this.DoURLs) {
            this.ClueURLtarget = this.App.getParameter("CLUEURLTARGET");
            this.URLcol = this.App.colParam("URLCOLOR", Color.blue);
            this.URLtxt = this.App.getParameter("HINTURLTEXT");
            if (this.URLtxt == null) {
                this.URLtxt = "[?]";
            }
        }
        for (int i = 0; i < this.iq; ++i) {
            byte byte1 = dataInputStream.readByte();
            byte byte2 = dataInputStream.readByte();
            if ((byte2 & 0x80) != 0x0) {
                this.ib[i] = 1;
                byte2 &= 0x7F;
            }
            if ((byte1 & 0x80) != 0x0) {
                byte1 &= 0x7F;
                this.JE.jn[byte1 - 1][byte2 - 1].IZ[this.ib[i]] = true;
                this.JE.jn[byte1 - 1][byte2 - 1].JN(dataInputStream, this.ib[i]);
            }
            this.ic[i] = byte1;
            this.id[i] = byte2;
            this.io[i] = this.App.E(dataInputStream);
            if (this.it) {
                this.io[i] += ".";
            }
            if (this.DoURLs) {
                this.URLs[i] = this.App.IA(dataInputStream);
                if (this.URLs[i].length() < 1) {
                    this.URLs[i] = null;
                }
            }
            this.ia[i] = this.App.IA(dataInputStream);
        }
    }
    
    void jd(final Graphics graphics) {
        final int n = 0;
        final int h = this.H;
        graphics.setColor(this.App.ScrollColor);
        for (int i = 1; i <= 6; ++i) {
            graphics.drawLine(this.W + this.iv / 2 - i + 1, i + n, this.W + this.iv / 2 - i + 1 + (i - 1) * 2, i + n);
        }
        for (int j = 1; j <= 6; ++j) {
            graphics.drawLine(this.W + this.iv / 2 - j + 1, h - j - 1 - n, this.W + this.iv / 2 - j + 1 + (j - 1) * 2, h - j - 1 - n);
        }
        final int red = this.App.ScrollColor.getRed();
        final int green = this.App.ScrollColor.getGreen();
        final int blue = this.App.ScrollColor.getBlue();
        final int n2 = 7 + n;
        final int n3 = h / 2 - 7 - 2 - n;
        final int n4 = this.W + this.iv / 2 + 1 - 6;
        if (!this.App.ScrollGrad) {
            Color lightGray;
            if (red == 0 && green == 0 && blue == 0) {
                lightGray = Color.lightGray;
            }
            else {
                lightGray = new Color(red + (256 - red) / 2, green + (256 - green) / 2, blue + (256 - blue) / 2);
            }
            if (lightGray.toString().equals(this.getBackground().toString())) {
                graphics.setColor(Color.white);
            }
            else {
                graphics.setColor(lightGray);
            }
            graphics.fillRect(this.W + this.iv / 2 + 1 - 6, 7 + n, 11, h / 2 - 7 - 2 - n);
            graphics.fillRect(this.W + this.iv / 2 + 1 - 6, h / 2 + 2, 11, h - 7 - h / 2 - 2 - n);
        }
        else {
            for (int k = 0; k < 64; ++k) {
                graphics.setColor(new Color(red + (256 - red) * k / 64, green + (256 - green) * k / 64, blue + (256 - blue) * k / 64));
                final int n5 = (k + 1) * n3 / 64 - k * n3 / 64;
                graphics.fillRect(n4, n2 + k * n3 / 64, 11, n5);
                graphics.fillRect(n4, h - 7 - n - (k + 1) * n3 / 64, 11, n5);
            }
        }
    }
    
    void je(final Graphics graphics) {
        this.JV = new int[this.iq];
        this.ie = new int[this.iq];
        if (this.ip) {
            this.AC = new Font(this.App.Y.getName(), 1, this.App.Y.getSize());
        }
        else {
            this.AC = this.App.Y;
        }
        graphics.setFont(this.AC);
        this.ig = graphics.getFontMetrics();
        this.JT = new Font[4];
        this.CA = new int[4][];
        this.ih = new FontMetrics[4];
        for (int i = 3; i >= 0; --i) {
            graphics.setFont(this.JT[i] = new Font(this.App.Y.getName(), i, this.App.Y.getSize()));
            this.ih[i] = graphics.getFontMetrics();
            this.CA[i] = this.ih[i].getWidths();
        }
        this.JS = this.ih[0].getHeight();
        this.ij = this.ih[0].getDescent();
        this.iz = 0;
        this.ja = 0;
        if (this.DoURLs) {
            this.urlBuffer = this.ih[this.ip].stringWidth(this.URLtxt) + this.CA[0][32] + 1;
        }
        this.W = this.getSize().width - this.iv;
        this.H = this.getSize().height - 1;
        int js = this.JS;
        if (this.iu) {
            String string;
            if (this.il) {
                string = "999";
            }
            else {
                string = "99";
            }
            if (this.it) {
                string += ".";
            }
            this.ir = this.ig.stringWidth(string) + this.CA[0][32];
        }
        int n = 0;
        for (int j = 0; j < this.iq; ++j) {
            String substring = this.ia[j];
            int n2 = 0;
            this.ie[j] = js - this.JS;
            int n3 = this.ig.stringWidth(this.io[j]) + this.CA[0][32];
            if (this.iu && n3 < this.ir) {
                n3 = this.ir;
            }
            while (substring.length() > 0) {
                int n4 = 0;
                int n5 = this.JU + n3;
                final int length = substring.length();
                if (length > n) {
                    n = length;
                }
                final char[] array = new char[length];
                substring.getChars(0, length, array, 0);
                while (n4 < length && n5 < this.W - this.urlBuffer) {
                    if (array[n4] < '\u0005') {
                        n2 = array[n4] - '\u0001';
                    }
                    else {
                        n5 += this.ih[n2].charWidth(array[n4]);
                    }
                    ++n4;
                }
                final int n6 = n4 - 1;
                if (n4 != length || n5 > this.W - this.urlBuffer) {
                    --n4;
                    while (n4 > 0 && array[n4] != ' ' && array[n4] != '-') {
                        --n4;
                    }
                    if (n4 == 0) {
                        n4 = n6 - 1;
                    }
                }
                else {
                    --n4;
                }
                js += this.JS;
                if (n4 + 1 == length) {
                    break;
                }
                substring = substring.substring(n4 + 1);
                if (this.iu) {
                    n3 = this.ir;
                }
                else {
                    n3 = 0;
                }
            }
            this.JV[j] = js - this.JS;
            js += 2;
        }
        this.JR = new char[n];
    }
    
    void jf(final Graphics graphics, final int n) {
        int n2 = 0;
        int n3 = this.ie[n] - this.ja + this.JS;
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, n3 - this.JS, this.W + 1, this.JV[n] - this.ie[n] + 2);
        if (this.DoURLs && this.URLs[n] != null) {
            graphics.setColor(this.URLcol);
            graphics.setFont(this.AC);
            graphics.drawString(this.URLtxt, this.W + 1 - this.urlBuffer, n3 - this.ij);
        }
        if (n == this.iy && !this.FB) {
            graphics.setColor(this.SelColor);
            if (this.jc) {
                graphics.drawRect(0, n3 - this.JS, this.W, this.JV[n] - this.ie[n]);
            }
        }
        else {
            graphics.setColor(this.App.X);
        }
        String substring = this.ia[n];
        int n4 = this.ig.stringWidth(this.io[n]) + this.CA[0][32];
        graphics.setFont(this.AC);
        if (this.iu && n4 < this.ir) {
            graphics.drawString(this.io[n], this.JU + this.ir - n4, n3 - this.ij);
            n4 = this.ir;
        }
        else {
            graphics.drawString(this.io[n], this.JU, n3 - this.ij);
        }
        graphics.setFont(this.JT[0]);
        boolean b = false;
        while (substring.length() > 0) {
            int n5 = 0;
            int n6 = this.JU + n4;
            final int length = substring.length();
            substring.getChars(0, length, this.JR, 0);
            while (n5 < length && n6 < this.W - this.urlBuffer) {
                if (this.JR[n5] < '\u0005') {
                    b = true;
                    n2 = this.JR[n5] - '\u0001';
                }
                else {
                    n6 += this.ih[n2].charWidth(this.JR[n5]);
                }
                ++n5;
            }
            final int n7 = n5 - 1;
            if (n5 != length || n6 > this.W - this.urlBuffer) {
                --n5;
                while (n5 > 0 && this.JR[n5] != ' ' && this.JR[n5] != '-') {
                    --n5;
                }
                if (n5 == 0) {
                    n5 = n7 - 1;
                }
            }
            else {
                --n5;
            }
            if (b) {
                int n8 = 0;
                int n9 = 0;
                n2 = 0;
                int n10 = 0;
                for (int i = 0; i <= n5; ++i) {
                    if (this.JR[i] < '\u0005') {
                        if (i != n8) {
                            if (n3 <= this.H) {
                                graphics.drawString(substring.substring(n8, i), n9 + this.JU + n4, n3 - this.ij);
                            }
                            else {
                                graphics.drawString("...", n9 + this.JU + n4, n3 - this.JS - this.ij);
                            }
                        }
                        n2 = this.JR[i] - '\u0001';
                        graphics.setFont(this.JT[n2]);
                        n8 = i + 1;
                        n9 = n10;
                    }
                    else {
                        n10 += this.ih[n2].charWidth(this.JR[i]);
                    }
                }
                if (n8 <= n5) {
                    if (n3 <= this.H) {
                        graphics.drawString(substring.substring(n8, n5 + 1), n9 + this.JU + n4, n3 - this.ij);
                    }
                    else {
                        graphics.drawString("...", n9 + this.JU + n4, n3 - this.JS + this.ij);
                    }
                }
            }
            else if (n3 <= this.H) {
                graphics.drawString(substring.substring(0, n5 + 1), this.JU + n4, n3 - this.ij);
            }
            else {
                graphics.drawString("...", this.JU + n4, n3 - this.JS + this.ij);
            }
            if (n5 + 1 == length) {
                break;
            }
            substring = substring.substring(n5 + 1);
            if (this.iu) {
                n4 = this.ir;
            }
            else {
                n4 = 0;
            }
            n3 += this.JS;
        }
    }
    
    public void print(final Graphics graphics) {
        try {
            this.FB = true;
            this.paint(graphics);
        }
        finally {
            this.FB = false;
        }
    }
    
    void jg(final Graphics graphics) {
        graphics.setFont(this.App.Y);
        this.JQ = this.iz;
        this.JP = this.iz;
        this.ja = this.ie[this.iz];
        for (int iz = this.iz; iz < this.iq && Math.min(this.JV[iz], this.ie[iz] + this.JS + this.ij) - this.ja <= this.H; ++iz) {
            this.JQ = iz;
            if (this.JV[iz] - this.ja < this.H) {
                this.JP = iz;
            }
            this.jf(graphics, iz);
        }
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, this.JV[this.JQ] + 2 - this.ja, this.W + 1, this.H - this.JV[this.JQ] + this.ja);
    }
    
    public void paint(final Graphics graphics) {
        this.jg(graphics);
        if (this.JV[this.iq - 1] >= this.H) {
            this.jd(graphics);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    void jh(final int n) {
        if (n == this.iy && this.jc == this.is) {
            return;
        }
        final int iy = this.iy;
        this.is = this.jc;
        if ((this.iy = n) != -1 && (this.JV[n] - this.ja > this.H || this.ie[n] < this.ja)) {
            this.iz = n;
            final int n2 = (this.H - this.JV[n] + this.ie[n]) / 2;
            while (this.iz > 0) {
                if (this.ie[n] - this.ie[this.iz - 1] >= n2) {
                    break;
                }
                --this.iz;
            }
            while (this.iz > 0 && this.JV[this.iq - 1] - this.ie[this.iz - 1] < this.H) {
                --this.iz;
            }
            this.jg(this.getGraphics());
            return;
        }
        final Graphics graphics = this.getGraphics();
        graphics.setFont(this.App.Y);
        if (iy != -1) {
            this.jf(graphics, iy);
        }
        if (this.iy != -1) {
            this.jf(graphics, this.iy);
        }
        this.im = false;
    }
    
    void ClueURLMouseOver(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        int urLstat = -1;
        if (x > this.JU && x > this.W - (this.urlBuffer - 2) && x < this.W) {
            int iz;
            for (iz = this.iz; this.JV[iz] < y + this.ja && iz < this.JQ; ++iz) {}
            if (this.URLs[iz] != null) {
                urLstat = iz;
            }
        }
        if (urLstat != -1 && this.URLstat != urLstat) {
            this.setCursor(Cursor.getPredefinedCursor(12));
            this.App.getAppletContext().showStatus(this.URLs[urLstat]);
            this.URLstat = urLstat;
        }
        else if (urLstat == -1) {
            this.setCursor(Cursor.getDefaultCursor());
            this.App.getAppletContext().showStatus("");
            this.URLstat = -1;
        }
    }
    
    void ji(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (x > this.W) {
            if (y > this.H / 2) {
                this.iz = this.JP + 1;
                while (this.JV[this.iq - 1] - this.ie[this.iz - 1] < this.H) {
                    --this.iz;
                }
                this.jg(this.getGraphics());
            }
            else {
                int n = this.iz - 1;
                if (n >= 0) {
                    this.iz = n;
                    while (n > 0 && this.JV[this.iz] - this.ie[n - 1] < this.H) {
                        --n;
                    }
                    this.iz = n;
                    this.jg(this.getGraphics());
                }
            }
        }
        else if (x > this.JU) {
            int iz;
            int n2;
            for (iz = this.iz, n2 = y + this.ja; this.JV[iz] < n2 && iz < this.JQ; ++iz) {}
            if (this.JV[iz] >= n2) {
                this.im = true;
                this.JE.af = (this.ib[iz] == 1);
                this.JE.HK(this.ic[iz] - 1, this.id[iz] - 1);
            }
            if (this.URLs[iz] != null && x > this.W - (this.urlBuffer - 2) && x < this.W) {
                this.App.IF(this.URLs[iz], this.ClueURLtarget, false);
            }
        }
        this.JE.requestFocus();
    }
}
