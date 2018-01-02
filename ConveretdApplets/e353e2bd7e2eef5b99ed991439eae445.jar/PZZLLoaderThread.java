import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Font;
import java.applet.Applet;
import java.awt.Image;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class PZZLLoaderThread extends Thread
{
    String \u0102;
    Graphics g;
    Graphics oS;
    Image \u00fa;
    int \u0103;
    int \u00e9;
    int \u00ea;
    String \u0104;
    String \u0105;
    String \u0106;
    String \u0107;
    String \u0108;
    String \u0109;
    String \u010a;
    String \u010b;
    int \u010c;
    int \u010d;
    int \u010e;
    int \u010f;
    int \u0110;
    int \u0111;
    int \u0112;
    Applet \u00eb;
    int x;
    int y;
    int \u00f5;
    boolean \u0113;
    boolean \u0114;
    int \u0115;
    int \u0116;
    boolean \u0117;
    boolean \u0118;
    
    public PZZLLoaderThread(final String \u0103, final Graphics g, final Graphics os, final Image \u00fa, final int \u00ea, final int n, final int \u00f5, final Applet \u00eb) {
        this.\u0103 = 10;
        this.\u0104 = "loading files";
        this.\u0105 = "one moment, please";
        this.\u0106 = "";
        this.\u0107 = "";
        this.\u0108 = "No connection...";
        this.\u0109 = "Please, try again later.";
        this.\u010a = "An error has occured:";
        this.\u010b = "Please report to support@pzzl.com";
        this.\u0113 = true;
        this.\u0114 = true;
        this.\u0117 = false;
        this.\u0118 = false;
        this.\u0102 = \u0103;
        this.g = g;
        this.\u00ea = \u00ea;
        this.\u00e9 = n - \u00f5;
        this.\u00f5 = \u00f5;
        this.\u00eb = \u00eb;
        this.\u00fa = \u00fa;
        this.oS = os;
    }
    
    public String getVersion() {
        return this.\u0102;
    }
    
    public void setErrorMessage(final String \u0107, final String \u01072) {
        this.\u0106 = \u0107;
        this.\u0107 = \u01072;
        this.\u0117 = true;
    }
    
    public boolean tetekenen(final int n, final int n2, final int n3, final boolean[][] array) {
        final int x = this.x;
        final int y = this.y;
        if (n == 0) {
            ++this.x;
        }
        if (n == 1) {
            ++this.y;
        }
        if (n == 2) {
            --this.x;
        }
        if (n == 3) {
            --this.y;
        }
        if (this.x >= n2 || this.y >= n3 || this.x < 0 || this.y < 0) {
            this.x = x;
            this.y = y;
            return false;
        }
        if (this.\u0113 && array[this.x][this.y]) {
            this.x = x;
            this.y = y;
            return false;
        }
        if (!this.\u0113 && !array[this.x][this.y]) {
            this.x = x;
            this.y = y;
            return false;
        }
        if (this.\u0113) {
            return this.\u0115 < this.\u0116;
        }
        return this.\u0115 >= 0;
    }
    
    public void run() {
        this.\u0102 = "version " + this.\u0102;
        final Font font = new Font("Helvetica", 1, 16);
        final Font font2 = new Font("Helvetica", 0, 10);
        final FontMetrics fontMetrics = this.g.getFontMetrics(font);
        final FontMetrics fontMetrics2 = this.g.getFontMetrics(font2);
        this.\u010c = (this.\u00ea - fontMetrics.stringWidth(this.\u0104)) / 2;
        this.\u010d = (this.\u00ea - fontMetrics.stringWidth(this.\u0105)) / 2;
        this.\u010e = (this.\u00ea - fontMetrics.stringWidth(this.\u010a)) / 2;
        this.\u010f = (this.\u00ea - fontMetrics.stringWidth(this.\u010b)) / 2;
        this.\u0110 = (this.\u00ea - fontMetrics2.stringWidth(this.\u0102)) / 2;
        int n = 0;
        this.oS.setColor(Color.white);
        this.oS.fillRect(0, 0, this.\u00ea, this.\u00e9);
        Color color = Color.black;
        final int n2 = this.\u00ea / this.\u0103;
        final int n3 = this.\u00e9 / this.\u0103;
        this.\u0116 = (n3 - 2) * (n3 - 2) - 5;
        this.x = n2 / 2;
        this.y = n3 / 2;
        final boolean[][] array = new boolean[n2][n3];
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n3; ++j) {
                array[i][j] = false;
            }
        }
        while (this.\u0114) {
            if (!this.\u0117) {
                if (this.\u0113) {
                    if (color == Color.black) {
                        color = Color.white;
                    }
                    else {
                        color = Color.black;
                    }
                }
                else {
                    color = Color.white;
                }
                this.oS.setColor(color);
                this.oS.fillRect(this.x * this.\u0103, this.y * this.\u0103 + this.\u00f5, this.\u0103, this.\u0103);
                if (this.\u0113) {
                    this.oS.setColor(Color.black);
                }
                this.oS.drawRect(this.x * this.\u0103, this.y * this.\u0103 + this.\u00f5, this.\u0103, this.\u0103);
                if (this.\u0113) {
                    ++this.\u0115;
                }
                else {
                    --this.\u0115;
                }
                if (this.\u0113) {
                    array[this.x][this.y] = true;
                }
                else {
                    array[this.x][this.y] = false;
                }
                if (this.\u0113) {
                    n = (n + 1) % 4;
                }
                if (!this.tetekenen(n, n2, n3, array)) {
                    if (this.\u0113) {
                        n = (n + 3) % 4;
                    }
                    else {
                        n = (n + 3) % 4;
                    }
                    if (!this.tetekenen(n, n2, n3, array)) {
                        this.\u0113 = !this.\u0113;
                        n = (n + 2) % 4;
                    }
                }
                this.oS.setFont(font);
                this.oS.setColor(Color.black);
                this.oS.drawString(this.\u0104, this.\u010c, 40);
                this.oS.drawString(this.\u0105, this.\u010d, 60);
                if (this.\u0118) {
                    this.oS.setFont(font2);
                    this.oS.setColor(Color.darkGray);
                    this.oS.drawRect(this.\u0110 - 11, this.\u00e9 - 40 - font2.getSize() - 1, fontMetrics2.stringWidth(this.\u0102) + 20 + 1, font2.getSize() + font2.getSize() / 2 + 1);
                    this.oS.setColor(Color.lightGray);
                    this.oS.fillRect(this.\u0110 - 10, this.\u00e9 - 40 - font2.getSize(), fontMetrics2.stringWidth(this.\u0102) + 20, font2.getSize() + font2.getSize() / 2);
                    this.oS.drawString(this.\u0102, this.\u0110, 80);
                }
                this.oS.setColor(Color.black);
                this.g.drawImage(this.\u00fa, 0, 0, this.\u00eb);
                try {
                    Thread.sleep(5L);
                }
                catch (InterruptedException ex) {}
            }
            else {
                this.\u0111 = (this.\u00ea - fontMetrics.stringWidth(this.\u0106)) / 2;
                this.\u0112 = (this.\u00ea - fontMetrics.stringWidth(this.\u0107)) / 2;
                this.oS.setColor(Color.white);
                this.oS.fillRect(0, 0, this.\u00ea, this.\u00e9);
                this.oS.setFont(font);
                this.oS.setColor(Color.black);
                this.oS.drawString(this.\u0106, this.\u0111, 140);
                this.oS.drawString(this.\u0107, this.\u0112, 160);
                this.g.drawImage(this.\u00fa, 0, 0, this.\u00eb);
                try {
                    Thread.sleep(5L);
                }
                catch (InterruptedException ex2) {}
                this.\u0114 = false;
            }
        }
    }
}
