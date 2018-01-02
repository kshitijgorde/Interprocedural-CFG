// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

import java.awt.Image;
import java.awt.Color;
import java.util.Properties;
import java.awt.Frame;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.PrintJob;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

class PrintBoard extends Thread implements ImageObserver
{
    Graphics g;
    PrintJob job;
    int S;
    int Range;
    Position P;
    Font font;
    FontMetrics fontmetrics;
    Frame F;
    
    public PrintBoard(final Position p3, final int range, final Frame f) {
        this.F = f;
        this.job = this.F.getToolkit().getPrintJob(this.F, "Jago", new Properties());
        this.S = p3.S;
        this.P = p3;
        this.Range = range;
        if (this.job != null) {
            this.g = this.job.getGraphics();
            this.start();
        }
    }
    
    public void run() {
        final int width = this.job.getPageDimension().width;
        this.job.getPageDimension();
        int n = width * 2 / 3 / this.S;
        final int n2 = width / 6;
        if (n % 2 != 0) {
            ++n;
        }
        this.font = new Font("SansSerif", 1, n / 2);
        this.g.setFont(this.font);
        this.fontmetrics = this.g.getFontMetrics(this.font);
        this.g.setColor(Color.black);
        int n3 = n2;
        final int n4 = this.fontmetrics.getAscent() / 2 - 1;
        for (int i = 0; i < this.S; ++i) {
            final String value = String.valueOf(this.S - i);
            this.g.drawString(value, n2 + this.S * n + n / 2 - this.fontmetrics.stringWidth(value) / 2, n3 + n / 2 + n4);
            n3 += n;
        }
        int n5 = n2;
        final char[] array = { '\0' };
        for (int j = 0; j < this.S; ++j) {
            int n6 = j;
            if (n6 > 7) {
                ++n6;
            }
            array[0] = (char)(65 + n6);
            final String s = new String(array);
            this.g.drawString(s, n5 + n / 2 - this.fontmetrics.stringWidth(s) / 2, n2 + this.S * n + n / 2 + n4);
            n5 += n;
        }
        for (int k = 0; k < this.S; ++k) {
            for (int l = 0; l < this.S; ++l) {
                this.update1(this.g, n2 + n * k, n2 + n * l, k, l, n);
            }
        }
        this.g.dispose();
        this.job.end();
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.notify();
            return false;
        }
        return true;
    }
    
    void hand1(final Graphics graphics, final int n, final int n2, final int n3) {
        int n4 = n3 / 10;
        if (n4 < 2) {
            n4 = 2;
        }
        graphics.fillRect(n + n3 / 2 - n4, n2 + n3 / 2 - n4, 2 * n4 + 1, 2 * n4 + 1);
    }
    
    public void update1(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        final char[] array = { '\0' };
        graphics.setColor(Color.black);
        if (n3 > 0) {
            graphics.drawLine(n, n2 + n5 / 2, n + n5 / 2, n2 + n5 / 2);
        }
        if (n3 < this.S - 1) {
            graphics.drawLine(n + n5 / 2, n2 + n5 / 2, n + n5, n2 + n5 / 2);
        }
        if (n4 > 0) {
            graphics.drawLine(n + n5 / 2, n2, n + n5 / 2, n2 + n5 / 2);
        }
        if (n4 < this.S - 1) {
            graphics.drawLine(n + n5 / 2, n2 + n5 / 2, n + n5 / 2, n2 + n5);
        }
        if (this.S == 19) {
            for (int n6 = this.S / 2 - 3, i = 3; i < this.S; i += n6) {
                for (int j = 3; j < this.S; j += n6) {
                    if (n3 == i && n4 == j) {
                        this.hand1(graphics, n, n2, n5);
                    }
                }
            }
        }
        else if (this.S >= 11) {
            if (this.S >= 15 && this.S % 2 == 1) {
                for (int n7 = this.S / 2 - 3, k = 3; k < this.S; k += n7) {
                    for (int l = 3; l < this.S; l += n7) {
                        if (n3 == k && n4 == l) {
                            this.hand1(graphics, n, n2, n5);
                        }
                    }
                }
            }
            else {
                if (n3 == 3 && n4 == 3) {
                    this.hand1(graphics, n, n2, n5);
                }
                if (n3 == this.S - 4 && n4 == 3) {
                    this.hand1(graphics, n, n2, n5);
                }
                if (n3 == 3 && n4 == this.S - 4) {
                    this.hand1(graphics, n, n2, n5);
                }
                if (n3 == this.S - 4 && n4 == this.S - 4) {
                    this.hand1(graphics, n, n2, n5);
                }
            }
        }
        if (this.P.color(n3, n4) > 0) {
            graphics.setColor(Color.black);
            graphics.fillOval(n + 1, n2 + 1, n5 - 3, n5 - 3);
        }
        else if (this.P.color(n3, n4) < 0) {
            graphics.setColor(Color.white);
            graphics.fillOval(n + 1, n2 + 1, n5 - 3, n5 - 3);
            graphics.setColor(Color.black);
            graphics.drawOval(n + 1, n2 + 1, n5 - 3, n5 - 3);
        }
        if (this.P.marker(n3, n4) != 0) {
            if (this.P.color(n3, n4) > 0) {
                graphics.setColor(Color.white);
            }
            else {
                graphics.setColor(Color.black);
            }
            final int n8 = n5 / 4;
            switch (this.P.marker(n3, n4)) {
                case 4: {
                    graphics.drawOval(n + n5 / 2 - n8, n2 + n5 / 2 - n8, 2 * n8, 2 * n8);
                    break;
                }
                case 1: {
                    graphics.drawLine(n + n5 / 2 - n8, n2 + n5 / 2 - n8, n + n5 / 2 + n8, n2 + n5 / 2 + n8);
                    graphics.drawLine(n + n5 / 2 + n8, n2 + n5 / 2 - n8, n + n5 / 2 - n8, n2 + n5 / 2 + n8);
                    break;
                }
                case 3: {
                    graphics.drawLine(n + n5 / 2, n2 + n5 / 2 - n8, n + n5 / 2 - n8, n2 + n5 / 2 + n8);
                    graphics.drawLine(n + n5 / 2, n2 + n5 / 2 - n8, n + n5 / 2 + n8, n2 + n5 / 2 + n8);
                    graphics.drawLine(n + n5 / 2 - n8, n2 + n5 / 2 + n8, n + n5 / 2 + n8, n2 + n5 / 2 + n8);
                    break;
                }
                default: {
                    graphics.drawRect(n + n5 / 2 - n8, n2 + n5 / 2 - n8, 2 * n8, 2 * n8);
                    break;
                }
            }
        }
        if (this.P.letter(n3, n4) != 0) {
            if (this.P.color(n3, n4) > 0) {
                graphics.setColor(Color.white);
            }
            else {
                graphics.setColor(Color.black);
            }
            array[0] = (char)(97 + this.P.letter(n3, n4) - 1);
            final String s = new String(array);
            graphics.drawString(s, n + n5 / 2 - this.fontmetrics.stringWidth(s) / 2, n2 + n5 / 2 + n5 / 4);
        }
        if (this.P.haslabel(n3, n4)) {
            if (this.P.color(n3, n4) > 0) {
                graphics.setColor(Color.white);
            }
            else {
                graphics.setColor(Color.black);
            }
            final String label = this.P.label(n3, n4);
            graphics.drawString(label, n + n5 / 2 - this.fontmetrics.stringWidth(label) / 2, n2 + n5 / 2 + n5 / 4);
        }
        if (this.P.color(n3, n4) != 0 && this.Range >= 0 && this.P.number(n3, n4) > this.Range) {
            if (this.P.color(n3, n4) > 0) {
                graphics.setColor(Color.white);
            }
            else {
                graphics.setColor(Color.black);
            }
            final String value = String.valueOf(this.P.number(n3, n4) % 100);
            graphics.drawString(value, n + n5 / 2 - this.fontmetrics.stringWidth(value) / 2, n2 + n5 / 2 + n5 / 4);
        }
    }
}
