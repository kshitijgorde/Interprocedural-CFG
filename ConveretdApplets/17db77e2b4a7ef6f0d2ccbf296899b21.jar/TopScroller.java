import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TopScroller extends Applet implements Runnable
{
    String font;
    int fontdec;
    Image bufferImg;
    Dimension dim;
    Thread t;
    Graphics bufferGr;
    Graphics gr;
    Color textColor;
    int textRed;
    int textGreen;
    int textBlue;
    Color bgColor;
    int bgRed;
    int bgGreen;
    int bgBlue;
    int tmpRed;
    int tmpGreen;
    int tmpBlue;
    int textSize;
    String[] messages;
    String[] in;
    String[] out;
    int msgnumber;
    String tmp;
    int delay;
    FontMetrics fm;
    int x;
    int y;
    
    public void zoomin(final String s, final int n) {
        if (this.t == null) {
            return;
        }
        for (int i = 1; i <= this.textSize; ++i) {
            this.bufferGr.setFont(new Font(this.font, this.fontdec, i));
            this.fm = this.bufferGr.getFontMetrics();
            final int n2 = this.dim.height / 2 + this.fm.getHeight() / 4;
            this.bufferGr.setColor(this.bgColor);
            this.bufferGr.fillRect(0, 0, this.dim.width, this.dim.height);
            final int n3 = this.dim.width / 2 - this.fm.stringWidth(s) / 2;
            this.bufferGr.setColor(this.textColor);
            this.bufferGr.drawString(s, n3, n2);
            this.update(this.gr);
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
        }
        try {
            Thread.sleep(500L);
        }
        catch (InterruptedException ex2) {}
        this.bufferGr.setFont(new Font(this.font, this.fontdec, this.textSize));
        (this.fm = this.bufferGr.getFontMetrics()).stringWidth(s);
        this.fm.getHeight();
    }
    
    public void zoomout(final String s, final int n) {
        if (this.t == null) {
            return;
        }
        for (int i = this.textSize; i > 0; --i) {
            this.bufferGr.setFont(new Font(this.font, this.fontdec, i));
            this.fm = this.bufferGr.getFontMetrics();
            final int n2 = this.dim.height / 2 + this.fm.getHeight() / 4;
            this.bufferGr.setColor(this.bgColor);
            this.bufferGr.fillRect(0, 0, this.dim.width, this.dim.height);
            final int n3 = this.dim.width / 2 - this.fm.stringWidth(s) / 2;
            this.bufferGr.setColor(this.textColor);
            this.bufferGr.drawString(s, n3, n2);
            this.update(this.gr);
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
        }
        this.bufferGr.setColor(this.bgColor);
        this.bufferGr.fillRect(0, 0, this.dim.width, this.dim.height);
        this.update(this.gr);
        this.bufferGr.setFont(new Font(this.font, this.fontdec, this.textSize));
        (this.fm = this.bufferGr.getFontMetrics()).stringWidth(s);
        this.fm.getHeight();
    }
    
    public void fadein(final String s, final int n) {
        this.fm = this.bufferGr.getFontMetrics();
        final int n2 = this.dim.width / 2 - this.fm.stringWidth(s) / 2;
        final int n3 = this.dim.height / 2 + this.fm.getHeight() / 4;
        final int n4 = this.textRed - this.bgRed;
        final int n5 = this.textGreen - this.bgGreen;
        final int n6 = this.textBlue - this.bgBlue;
        final double n7 = n4 / 50.0;
        final double n8 = n5 / 50.0;
        final double n9 = n6 / 50.0;
        Color bgColor = this.bgColor;
        while (this.t != null) {
            for (int n10 = 0; n10 <= 50.0; ++n10) {
                this.bufferGr.setColor(this.bgColor);
                this.bufferGr.fillRect(0, 0, this.dim.width, this.dim.height);
                this.bufferGr.setColor(bgColor);
                this.bufferGr.drawString(s, n2, n3);
                this.update(this.gr);
                this.tmpRed = this.bgRed + (int)(Object)new Double(n7 * n10);
                this.tmpGreen = this.bgGreen + (int)(Object)new Double(n8 * n10);
                this.tmpBlue = this.bgBlue + (int)(Object)new Double(n9 * n10);
                bgColor = new Color(this.tmpRed, this.tmpGreen, this.tmpBlue);
                try {
                    Thread.sleep(n);
                }
                catch (InterruptedException ex) {}
            }
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void fadeout(final String s, final int n) {
        final int n2 = this.dim.width / 2 - this.fm.stringWidth(s) / 2;
        final int n3 = this.dim.height / 2 + this.fm.getHeight() / 4;
        final int n4 = this.textRed - this.bgRed;
        final int n5 = this.textGreen - this.bgGreen;
        final int n6 = this.textBlue - this.bgBlue;
        final int textRed = this.textRed;
        final int textGreen = this.textGreen;
        final int textBlue = this.textBlue;
        Color textColor = this.textColor;
        final double n7 = n4 / 50.0;
        final double n8 = n5 / 50.0;
        final double n9 = n6 / 50.0;
        if (this.t == null) {
            return;
        }
        for (int n10 = 0; n10 < 50.0; ++n10) {
            this.bufferGr.setColor(this.bgColor);
            this.bufferGr.fillRect(0, 0, this.dim.width, this.dim.height);
            this.bufferGr.setColor(textColor);
            this.bufferGr.drawString(s, n2, n3);
            this.update(this.gr);
            textColor = new Color(this.textRed - (int)(Object)new Double(n7 * n10), this.textGreen - (int)(Object)new Double(n8 * n10), this.textBlue - (int)(Object)new Double(n9 * n10));
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void scroll(final String s, final int n, final int n2) {
        while (this.t != null) {
            final int n3 = this.textRed - this.bgRed;
            final int n4 = this.textGreen - this.bgGreen;
            final int n5 = this.textBlue - this.bgBlue;
            final double n6 = n3 / 4.0;
            final double n7 = n4 / 4.0;
            final double n8 = n5 / 4.0;
            this.fm = this.bufferGr.getFontMetrics();
            final int n9 = this.dim.width / 2 - this.fm.stringWidth(s) / 2;
            final int n10 = this.dim.height / 2 + this.fm.getHeight() / 4;
            switch (n) {
                case 8: {
                    int i = n9;
                    while (i >= -this.fm.stringWidth(s)) {
                        this.bufferGr.setColor(this.bgColor);
                        this.bufferGr.fillRect(0, 0, this.dim.width, this.dim.height);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6 * 4.0), (int)(Object)new Double(this.textGreen - n7 * 4.0), (int)(Object)new Double(this.textBlue - n8 * 4.0)));
                        this.bufferGr.drawString(s, i + 4, n10);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6 * 3.0), (int)(Object)new Double(this.textGreen - n7 * 3.0), (int)(Object)new Double(this.textBlue - n8 * 3.0)));
                        this.bufferGr.drawString(s, i + 3, n10);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6 * 2.0), (int)(Object)new Double(this.textGreen - n7 * 2.0), (int)(Object)new Double(this.textBlue - n8 * 2.0)));
                        this.bufferGr.drawString(s, i + 2, n10);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6), (int)(Object)new Double(this.textGreen - n7), (int)(Object)new Double(this.textBlue - n8)));
                        this.bufferGr.drawString(s, i + 1, n10);
                        this.bufferGr.setColor(this.textColor);
                        this.bufferGr.drawString(s, i, n10);
                        this.update(this.gr);
                        --i;
                        try {
                            Thread.sleep(n2);
                        }
                        catch (InterruptedException ex) {}
                    }
                }
                case 7: {
                    int j = n10;
                    while (j <= this.dim.height + this.fm.getHeight()) {
                        this.bufferGr.setColor(this.bgColor);
                        this.bufferGr.fillRect(0, 0, this.dim.width, this.dim.height);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6 * 4.0), (int)(Object)new Double(this.textGreen - n7 * 4.0), (int)(Object)new Double(this.textBlue - n8 * 4.0)));
                        this.bufferGr.drawString(s, n9, j - 4);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6 * 3.0), (int)(Object)new Double(this.textGreen - n7 * 3.0), (int)(Object)new Double(this.textBlue - n8 * 3.0)));
                        this.bufferGr.drawString(s, n9, j - 3);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6 * 2.0), (int)(Object)new Double(this.textGreen - n7 * 2.0), (int)(Object)new Double(this.textBlue - n8 * 2.0)));
                        this.bufferGr.drawString(s, n9, j - 2);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6), (int)(Object)new Double(this.textGreen - n7), (int)(Object)new Double(this.textBlue - n8)));
                        this.bufferGr.drawString(s, n9, j - 1);
                        this.bufferGr.setColor(this.textColor);
                        this.bufferGr.drawString(s, n9, j);
                        this.update(this.gr);
                        ++j;
                        try {
                            Thread.sleep(n2);
                        }
                        catch (InterruptedException ex2) {}
                    }
                }
                case 6: {
                    int k = n9;
                    while (k <= this.dim.width) {
                        this.bufferGr.setColor(this.bgColor);
                        this.bufferGr.fillRect(0, 0, this.dim.width, this.dim.height);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6 * 4.0), (int)(Object)new Double(this.textGreen - n7 * 4.0), (int)(Object)new Double(this.textBlue - n8 * 4.0)));
                        this.bufferGr.drawString(s, k - 4, n10);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6 * 3.0), (int)(Object)new Double(this.textGreen - n7 * 3.0), (int)(Object)new Double(this.textBlue - n8 * 3.0)));
                        this.bufferGr.drawString(s, k - 3, n10);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6 * 2.0), (int)(Object)new Double(this.textGreen - n7 * 2.0), (int)(Object)new Double(this.textBlue - n8 * 2.0)));
                        this.bufferGr.drawString(s, k - 2, n10);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6), (int)(Object)new Double(this.textGreen - n7), (int)(Object)new Double(this.textBlue - n8)));
                        this.bufferGr.drawString(s, k - 1, n10);
                        this.bufferGr.setColor(this.textColor);
                        this.bufferGr.drawString(s, k, n10);
                        this.update(this.gr);
                        ++k;
                        try {
                            Thread.sleep(n2);
                        }
                        catch (InterruptedException ex3) {}
                    }
                }
                case 5: {
                    int l = n10;
                    while (l >= 0) {
                        this.bufferGr.setColor(this.bgColor);
                        this.bufferGr.fillRect(0, 0, this.dim.width, this.dim.height);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6 * 4.0), (int)(Object)new Double(this.textGreen - n7 * 4.0), (int)(Object)new Double(this.textBlue - n8 * 4.0)));
                        this.bufferGr.drawString(s, n9, l + 4);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6 * 3.0), (int)(Object)new Double(this.textGreen - n7 * 3.0), (int)(Object)new Double(this.textBlue - n8 * 3.0)));
                        this.bufferGr.drawString(s, n9, l + 3);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6 * 2.0), (int)(Object)new Double(this.textGreen - n7 * 2.0), (int)(Object)new Double(this.textBlue - n8 * 2.0)));
                        this.bufferGr.drawString(s, n9, l + 2);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6), (int)(Object)new Double(this.textGreen - n7), (int)(Object)new Double(this.textBlue - n8)));
                        this.bufferGr.drawString(s, n9, l + 1);
                        this.bufferGr.setColor(this.textColor);
                        this.bufferGr.drawString(s, n9, l);
                        this.update(this.gr);
                        --l;
                        try {
                            Thread.sleep(n2);
                        }
                        catch (InterruptedException ex4) {}
                    }
                }
                case 4: {
                    int n11 = -this.fm.stringWidth(s);
                    while (n11 <= n9) {
                        this.bufferGr.setColor(this.bgColor);
                        this.bufferGr.fillRect(0, 0, this.dim.width, this.dim.height);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6 * 4.0), (int)(Object)new Double(this.textGreen - n7 * 4.0), (int)(Object)new Double(this.textBlue - n8 * 4.0)));
                        this.bufferGr.drawString(s, n11 - 4, n10);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6 * 3.0), (int)(Object)new Double(this.textGreen - n7 * 3.0), (int)(Object)new Double(this.textBlue - n8 * 3.0)));
                        this.bufferGr.drawString(s, n11 - 3, n10);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6 * 2.0), (int)(Object)new Double(this.textGreen - n7 * 2.0), (int)(Object)new Double(this.textBlue - n8 * 2.0)));
                        this.bufferGr.drawString(s, n11 - 2, n10);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6), (int)(Object)new Double(this.textGreen - n7), (int)(Object)new Double(this.textBlue - n8)));
                        this.bufferGr.drawString(s, n11 - 1, n10);
                        this.bufferGr.setColor(this.textColor);
                        this.bufferGr.drawString(s, n11, n10);
                        this.update(this.gr);
                        ++n11;
                        try {
                            Thread.sleep(n2);
                        }
                        catch (InterruptedException ex5) {}
                    }
                    this.bufferGr.setColor(this.bgColor);
                    this.bufferGr.fillRect(0, 0, this.dim.width, this.dim.height);
                    this.bufferGr.setColor(this.textColor);
                    this.bufferGr.drawString(s, n9, n10);
                    this.update(this.gr);
                    try {
                        Thread.sleep(500L);
                    }
                    catch (InterruptedException ex6) {}
                }
                case 3: {
                    int n12 = this.dim.height + this.fm.getHeight();
                    while (n12 >= n10) {
                        this.bufferGr.setColor(this.bgColor);
                        this.bufferGr.fillRect(0, 0, this.dim.width, this.dim.height);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6 * 4.0), (int)(Object)new Double(this.textGreen - n7 * 4.0), (int)(Object)new Double(this.textBlue - n8 * 4.0)));
                        this.bufferGr.drawString(s, n9, n12 + 4);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6 * 3.0), (int)(Object)new Double(this.textGreen - n7 * 3.0), (int)(Object)new Double(this.textBlue - n8 * 3.0)));
                        this.bufferGr.drawString(s, n9, n12 + 3);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6 * 2.0), (int)(Object)new Double(this.textGreen - n7 * 2.0), (int)(Object)new Double(this.textBlue - n8 * 2.0)));
                        this.bufferGr.drawString(s, n9, n12 + 2);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6), (int)(Object)new Double(this.textGreen - n7), (int)(Object)new Double(this.textBlue - n8)));
                        this.bufferGr.drawString(s, n9, n12 + 1);
                        this.bufferGr.setColor(this.textColor);
                        this.bufferGr.drawString(s, n9, n12);
                        this.update(this.gr);
                        --n12;
                        try {
                            Thread.sleep(n2);
                        }
                        catch (InterruptedException ex7) {}
                    }
                    this.bufferGr.setColor(this.bgColor);
                    this.bufferGr.fillRect(0, 0, this.dim.width, this.dim.height);
                    this.bufferGr.setColor(this.textColor);
                    this.bufferGr.drawString(s, n9, n10);
                    this.update(this.gr);
                    try {
                        Thread.sleep(500L);
                    }
                    catch (InterruptedException ex8) {}
                }
                case 2: {
                    int width = this.dim.width;
                    while (width >= n9) {
                        this.bufferGr.setColor(this.bgColor);
                        this.bufferGr.fillRect(0, 0, this.dim.width, this.dim.height);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6 * 4.0), (int)(Object)new Double(this.textGreen - n7 * 4.0), (int)(Object)new Double(this.textBlue - n8 * 4.0)));
                        this.bufferGr.drawString(s, width + 4, n10);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6 * 3.0), (int)(Object)new Double(this.textGreen - n7 * 3.0), (int)(Object)new Double(this.textBlue - n8 * 3.0)));
                        this.bufferGr.drawString(s, width + 3, n10);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6 * 2.0), (int)(Object)new Double(this.textGreen - n7 * 2.0), (int)(Object)new Double(this.textBlue - n8 * 2.0)));
                        this.bufferGr.drawString(s, width + 2, n10);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6), (int)(Object)new Double(this.textGreen - n7), (int)(Object)new Double(this.textBlue - n8)));
                        this.bufferGr.drawString(s, width + 1, n10);
                        this.bufferGr.setColor(this.textColor);
                        this.bufferGr.drawString(s, width, n10);
                        this.update(this.gr);
                        --width;
                        try {
                            Thread.sleep(n2);
                        }
                        catch (InterruptedException ex9) {}
                    }
                    this.bufferGr.setColor(this.bgColor);
                    this.bufferGr.fillRect(0, 0, this.dim.width, this.dim.height);
                    this.bufferGr.setColor(this.textColor);
                    this.bufferGr.drawString(s, n9, n10);
                    this.update(this.gr);
                    try {
                        Thread.sleep(500L);
                    }
                    catch (InterruptedException ex10) {}
                }
                case 1: {
                    int n13 = 0;
                    while (n13 <= n10) {
                        this.bufferGr.setColor(this.bgColor);
                        this.bufferGr.fillRect(0, 0, this.dim.width, this.dim.height);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6 * 4.0), (int)(Object)new Double(this.textGreen - n7 * 4.0), (int)(Object)new Double(this.textBlue - n8 * 4.0)));
                        this.bufferGr.drawString(s, n9, n13 - 4);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6 * 3.0), (int)(Object)new Double(this.textGreen - n7 * 3.0), (int)(Object)new Double(this.textBlue - n8 * 3.0)));
                        this.bufferGr.drawString(s, n9, n13 - 3);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6 * 2.0), (int)(Object)new Double(this.textGreen - n7 * 2.0), (int)(Object)new Double(this.textBlue - n8 * 2.0)));
                        this.bufferGr.drawString(s, n9, n13 - 2);
                        this.bufferGr.setColor(new Color((int)(Object)new Double(this.textRed - n6), (int)(Object)new Double(this.textGreen - n7), (int)(Object)new Double(this.textBlue - n8)));
                        this.bufferGr.drawString(s, n9, n13 - 1);
                        this.bufferGr.setColor(this.textColor);
                        this.bufferGr.drawString(s, n9, n13);
                        this.update(this.gr);
                        ++n13;
                        try {
                            Thread.sleep(n2);
                        }
                        catch (InterruptedException ex11) {}
                    }
                    this.bufferGr.setColor(this.bgColor);
                    this.bufferGr.fillRect(0, 0, this.dim.width, this.dim.height);
                    this.bufferGr.setColor(this.textColor);
                    this.bufferGr.drawString(s, n9, n10);
                    this.update(this.gr);
                    try {
                        Thread.sleep(500L);
                    }
                    catch (InterruptedException ex12) {}
                }
                default: {
                    continue;
                }
            }
        }
    }
    
    public void start() {
        if (this.t == null) {
            (this.t = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.t != null && this.t.isAlive()) {
            this.t.stop();
            this.t = null;
        }
    }
    
    public void run() {
        while (this.t != null) {
            for (int i = 0; i < this.msgnumber; ++i) {
                this.fm.stringWidth(this.messages[i]);
                this.tmp = this.in[i].substring(0, this.in[i].indexOf(44));
                if (this.tmp.equals("scroll")) {
                    this.delay = new Integer(this.in[i].substring(this.in[i].lastIndexOf(44) + 1));
                    this.tmp = this.in[i].substring(this.in[i].indexOf(44) + 1, this.in[i].lastIndexOf(44));
                    if (this.tmp.equals("left")) {
                        this.scroll(this.messages[i], 4, this.delay);
                    }
                    else if (this.tmp.equals("right")) {
                        this.scroll(this.messages[i], 2, this.delay);
                    }
                    else if (this.tmp.equals("top")) {
                        this.scroll(this.messages[i], 1, this.delay);
                    }
                    else if (this.tmp.equals("bottom")) {
                        this.scroll(this.messages[i], 3, this.delay);
                    }
                }
                else if (this.tmp.equals("fade")) {
                    this.delay = new Integer(this.in[i].substring(this.in[i].lastIndexOf(44) + 1));
                    this.fadein(this.messages[i], this.delay);
                }
                else if (this.tmp.equals("zoom")) {
                    this.delay = new Integer(this.in[i].substring(this.in[i].lastIndexOf(44) + 1));
                    this.zoomin(this.messages[i], this.delay);
                }
                this.tmp = this.out[i].substring(0, this.out[i].indexOf(44));
                if (this.tmp.equals("scroll")) {
                    this.delay = new Integer(this.out[i].substring(this.out[i].lastIndexOf(44) + 1));
                    this.tmp = this.out[i].substring(this.out[i].indexOf(44) + 1, this.out[i].lastIndexOf(44));
                    if (this.tmp.equals("left")) {
                        this.scroll(this.messages[i], 8, this.delay);
                    }
                    else if (this.tmp.equals("right")) {
                        this.scroll(this.messages[i], 6, this.delay);
                    }
                    else if (this.tmp.equals("top")) {
                        this.scroll(this.messages[i], 5, this.delay);
                    }
                    else if (this.tmp.equals("bottom")) {
                        this.scroll(this.messages[i], 7, this.delay);
                    }
                }
                else if (this.tmp.equals("fade")) {
                    this.delay = new Integer(this.out[i].substring(this.out[i].lastIndexOf(44) + 1));
                    this.fadeout(this.messages[i], this.delay);
                }
                else if (this.tmp.equals("zoom")) {
                    this.delay = new Integer(this.in[i].substring(this.in[i].lastIndexOf(44) + 1));
                    this.zoomout(this.messages[i], this.delay);
                }
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.bufferImg, 0, 0, Color.white, null);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void init() {
        System.out.println("TopScroller by To the Top Webdesign");
        System.out.println("version 1.1");
        System.out.println("This applet is free for non-commercial use");
        System.out.println("http://start.at/ttt");
        this.font = "Arial";
        this.fontdec = 0;
        super.init();
        this.dim = this.size();
        this.textRed = 255;
        this.textGreen = 255;
        this.textBlue = 255;
        this.messages = new String[100];
        this.in = new String[100];
        this.out = new String[100];
        this.bgRed = 0;
        this.bgGreen = 0;
        this.bgBlue = 0;
        this.bufferImg = this.createImage(this.dim.width, this.dim.height);
        this.bufferGr = this.bufferImg.getGraphics();
        this.gr = this.getGraphics();
        this.textSize = 20;
        this.tmp = this.getParameter("font");
        if (this.tmp != null) {
            this.font = this.tmp;
        }
        this.tmp = this.getParameter("fontdec");
        if (this.tmp != null) {
            if (this.tmp.equals("plain")) {
                this.fontdec = 0;
            }
            if (this.tmp.equals("italic")) {
                this.fontdec = 2;
            }
            if (this.tmp.equals("bold")) {
                this.fontdec = 1;
            }
        }
        this.tmp = this.getParameter("fontsize");
        if (this.tmp != null) {
            this.textSize = Integer.parseInt(this.tmp);
        }
        this.bufferGr.setFont(new Font(this.font, this.fontdec, this.textSize));
        (this.fm = this.bufferGr.getFontMetrics()).getHeight();
        for (int i = 0; i < 100; ++i) {
            this.tmp = this.getParameter("message" + i);
            if (this.tmp != null) {
                this.messages[this.msgnumber] = this.tmp;
                this.tmp = this.getParameter("in" + i);
                if (this.tmp != null) {
                    this.in[this.msgnumber] = this.tmp;
                }
                else {
                    this.in[this.msgnumber] = "scroll,right,20";
                }
                this.tmp = this.getParameter("out" + new Integer(i).toString());
                if (this.tmp != null) {
                    this.out[this.msgnumber] = this.tmp;
                }
                else {
                    this.out[this.msgnumber] = "scroll,left,20";
                }
                ++this.msgnumber;
            }
        }
        this.tmp = this.getParameter("bgcolor");
        if (this.tmp != null && this.tmp.length() == 6) {
            this.bgRed = Integer.parseInt(this.tmp.substring(0, 2), 16);
            this.bgGreen = Integer.parseInt(this.tmp.substring(2, 4), 16);
            this.bgBlue = Integer.parseInt(this.tmp.substring(4), 16);
        }
        this.bgColor = new Color(this.bgRed, this.bgGreen, this.bgBlue);
        this.tmp = this.getParameter("fontcolor");
        if (this.tmp != null && this.tmp.length() == 6) {
            this.textRed = Integer.parseInt(this.tmp.substring(0, 2), 16);
            this.textGreen = Integer.parseInt(this.tmp.substring(2, 4), 16);
            this.textBlue = Integer.parseInt(this.tmp.substring(4), 16);
        }
        this.textColor = new Color(this.textRed, this.textGreen, this.textBlue);
    }
}
