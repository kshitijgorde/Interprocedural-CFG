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
    String \u017f;
    Graphics g;
    Graphics oS;
    Image \u0176;
    int \u0180;
    int \u00e7;
    int \u00e6;
    String \u0181;
    String \u0182;
    String \u0183;
    String \u0184;
    String \u0185;
    String \u0186;
    String \u0187;
    String \u0188;
    int \u0189;
    int \u018a;
    int \u018b;
    int \u018c;
    int \u018d;
    int \u018e;
    int \u018f;
    Applet \u00fc;
    int x;
    int y;
    int \u0172;
    boolean \u0190;
    boolean \u0191;
    int \u0192;
    int \u0193;
    boolean \u0194;
    boolean \u0195;
    
    public PZZLLoaderThread(final String \u017f, final Graphics g, final Graphics os, final Image \u0177, final int \u00e6, final int n, final int \u0173, final Applet \u00fc) {
        this.\u0180 = 10;
        this.\u0181 = "loading files";
        this.\u0182 = "one moment, please";
        this.\u0183 = "";
        this.\u0184 = "";
        this.\u0185 = "No connection...";
        this.\u0186 = "Please, try again later.";
        this.\u0187 = "An error has occured:";
        this.\u0188 = "Please report to support@pzzl.com";
        this.\u0190 = true;
        this.\u0191 = true;
        this.\u0194 = false;
        this.\u0195 = false;
        this.\u017f = \u017f;
        this.g = g;
        this.\u00e6 = \u00e6;
        this.\u00e7 = n - \u0173;
        this.\u0172 = \u0173;
        this.\u00fc = \u00fc;
        this.\u0176 = \u0177;
        this.oS = os;
    }
    
    public String getVersion() {
        return this.\u017f;
    }
    
    public void setErrorMessage(final String \u0183, final String \u0185) {
        this.\u0183 = \u0183;
        this.\u0184 = \u0185;
        this.\u0194 = true;
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
        if (this.\u0190 && array[this.x][this.y]) {
            this.x = x;
            this.y = y;
            return false;
        }
        if (!this.\u0190 && !array[this.x][this.y]) {
            this.x = x;
            this.y = y;
            return false;
        }
        if (this.\u0190) {
            return this.\u0192 < this.\u0193;
        }
        return this.\u0192 >= 0;
    }
    
    public void run() {
        this.\u017f = "version " + this.\u017f;
        final Font font = new Font("Helvetica", 1, 16);
        final Font font2 = new Font("Helvetica", 0, 10);
        final FontMetrics fontMetrics = this.g.getFontMetrics(font);
        final FontMetrics fontMetrics2 = this.g.getFontMetrics(font2);
        this.\u0189 = (this.\u00e6 - fontMetrics.stringWidth(this.\u0181)) / 2;
        this.\u018a = (this.\u00e6 - fontMetrics.stringWidth(this.\u0182)) / 2;
        this.\u018b = (this.\u00e6 - fontMetrics.stringWidth(this.\u0187)) / 2;
        this.\u018c = (this.\u00e6 - fontMetrics.stringWidth(this.\u0188)) / 2;
        this.\u018d = (this.\u00e6 - fontMetrics2.stringWidth(this.\u017f)) / 2;
        int n = 0;
        this.oS.setColor(Color.white);
        this.oS.fillRect(0, 0, this.\u00e6, this.\u00e7);
        Color color = Color.black;
        final int n2 = this.\u00e6 / this.\u0180;
        final int n3 = this.\u00e7 / this.\u0180;
        this.\u0193 = (n3 - 2) * (n3 - 2) - 5;
        this.x = n2 / 2;
        this.y = n3 / 2;
        final boolean[][] array = new boolean[n2][n3];
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n3; ++j) {
                array[i][j] = false;
            }
        }
        while (this.\u0191) {
            if (!this.\u0194) {
                if (this.\u0190) {
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
                this.oS.fillRect(this.x * this.\u0180, this.y * this.\u0180 + this.\u0172, this.\u0180, this.\u0180);
                if (this.\u0190) {
                    this.oS.setColor(Color.black);
                }
                this.oS.drawRect(this.x * this.\u0180, this.y * this.\u0180 + this.\u0172, this.\u0180, this.\u0180);
                if (this.\u0190) {
                    ++this.\u0192;
                }
                else {
                    --this.\u0192;
                }
                if (this.\u0190) {
                    array[this.x][this.y] = true;
                }
                else {
                    array[this.x][this.y] = false;
                }
                if (this.\u0190) {
                    n = (n + 1) % 4;
                }
                if (!this.tetekenen(n, n2, n3, array)) {
                    if (this.\u0190) {
                        n = (n + 3) % 4;
                    }
                    else {
                        n = (n + 3) % 4;
                    }
                    if (!this.tetekenen(n, n2, n3, array)) {
                        this.\u0190 = !this.\u0190;
                        n = (n + 2) % 4;
                    }
                }
                this.oS.setFont(font);
                this.oS.setColor(Color.black);
                this.oS.drawString(this.\u0181, this.\u0189, 40);
                this.oS.drawString(this.\u0182, this.\u018a, 60);
                if (this.\u0195) {
                    this.oS.setFont(font2);
                    this.oS.setColor(Color.darkGray);
                    this.oS.drawRect(this.\u018d - 11, this.\u00e7 - 40 - font2.getSize() - 1, fontMetrics2.stringWidth(this.\u017f) + 20 + 1, font2.getSize() + font2.getSize() / 2 + 1);
                    this.oS.setColor(Color.lightGray);
                    this.oS.fillRect(this.\u018d - 10, this.\u00e7 - 40 - font2.getSize(), fontMetrics2.stringWidth(this.\u017f) + 20, font2.getSize() + font2.getSize() / 2);
                    this.oS.drawString(this.\u017f, this.\u018d, 80);
                }
                this.oS.setColor(Color.black);
                this.g.drawImage(this.\u0176, 0, 0, this.\u00fc);
                try {
                    Thread.sleep(5L);
                }
                catch (InterruptedException ex) {}
            }
            else {
                this.\u018e = (this.\u00e6 - fontMetrics.stringWidth(this.\u0183)) / 2;
                this.\u018f = (this.\u00e6 - fontMetrics.stringWidth(this.\u0184)) / 2;
                this.oS.setColor(Color.white);
                this.oS.fillRect(0, 0, this.\u00e6, this.\u00e7);
                this.oS.setFont(font);
                this.oS.setColor(Color.black);
                this.oS.drawString(this.\u0183, this.\u018e, 140);
                this.oS.drawString(this.\u0184, this.\u018f, 160);
                this.g.drawImage(this.\u0176, 0, 0, this.\u00fc);
                try {
                    Thread.sleep(5L);
                }
                catch (InterruptedException ex2) {}
                this.\u0191 = false;
            }
        }
    }
}
