import java.net.URLConnection;
import java.net.URL;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CURVE1 extends Applet implements Runnable
{
    int[] iDate;
    float[] fValue;
    float oriV;
    float ranV;
    float incV;
    int oriX;
    int ranX;
    int curI;
    int iDig;
    Thread runner;
    Graphics dBuf;
    Image offScr;
    int err;
    String BCD;
    int nBkColor;
    int nLnColor;
    int pend;
    
    public void stop() {
        if (this.runner != null) {
            this.runner = null;
        }
    }
    
    public String dateStr(int n) {
        String s;
        if (n >= 10000) {
            final String string = n / 10000 + "/";
            n %= 10000;
            final int n2 = n / 100;
            final String string2 = string + ((n2 < 10) ? ("0" + n2) : ("" + n2)) + "/";
            final int n3 = n % 100;
            s = string2 + ((n3 < 10) ? ("0" + n3) : ("" + n3));
        }
        else {
            final String string3 = n / 100 + "/";
            final int n4 = n % 100;
            s = string3 + ((n4 < 10) ? ("0" + n4) : ("" + n4));
        }
        return s;
    }
    
    public void setBCD(final String bcd) {
        this.err = 112;
        this.bpaint(this.dBuf);
        this.repaint();
        this.BCD = bcd;
        this.inVal();
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.offScr, 0, 0, this);
    }
    
    public CURVE1() {
        this.runner = null;
        this.nBkColor = 11184810;
        this.nLnColor = 0;
        this.pend = 0;
    }
    
    int[] parseI(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final int[] array = new int[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        return array;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void bpaint(final Graphics graphics) {
        this.dBuf.setColor(new Color(16777215));
        this.dBuf.fillRect(0, 0, this.size().width, this.size().height);
        final String parameter = this.getParameter("T");
        final Font font = new Font("\u9ed1\u9ad4", 1, 15);
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        graphics.setColor(new Color(0));
        graphics.setFont(font);
        graphics.drawString(parameter, Math.max((this.size().width - fontMetrics.stringWidth(parameter)) / 2, 0), fontMetrics.getAscent() + 1);
        final Font font2 = new Font("\u7d30\u660e\u9ad4", 0, 12);
        final FontMetrics fontMetrics2 = this.getFontMetrics(font2);
        graphics.setFont(font2);
        if (this.err != 0) {
            graphics.drawString((this.err >= 111) ? "\u8b80\u53d6\u6578\u64da\u4e2d" : ("\u7121\u8cc7\u6599" + this.err), 10, 40);
            return;
        }
        this.oriX = 8;
        this.ranX = this.size().width - 20 - fontMetrics2.stringWidth("8") * Math.max(this.pf(this.oriV, this.iDig).length(), this.pf(this.oriV + this.ranV, this.iDig).length());
        boolean b = false;
        int n = this.size().height - 20;
        int n2 = this.size().height - 40;
        if (this.ranX < fontMetrics2.stringWidth("8") * 30) {
            n = this.size().height - 30;
            n2 = this.size().height - 50;
            b = true;
        }
        graphics.setColor(new Color(6710886));
        graphics.fillRect(this.oriX + 6, n + 1, this.ranX - 3, 3);
        graphics.fillRect(this.oriX + this.ranX, n - n2 + 6, 3, n2 - 2);
        graphics.setColor(new Color(this.nBkColor));
        graphics.fillRect(this.oriX, n - n2, this.ranX, n2);
        int n3 = this.iDate[0] % 10000 / 100;
        int n4 = -12;
        for (int i = 1; i < this.iDate.length; ++i) {
            final int n5 = this.iDate[i] % 10000 / 100;
            if (n3 != n5) {
                n3 = n5;
                if (i - n4 > 11) {
                    graphics.setColor(new Color(10601172));
                    graphics.fillRect(this.oriX + (i * 2 + 1) * this.ranX / (2 * this.iDate.length), n - n2 + 1, 1, n2);
                    if (n5 == 1) {
                        graphics.drawString("" + this.iDate[i] / 10000, this.oriX + (i * 2 + 1) * this.ranX / (2 * this.iDate.length) + 1, n - fontMetrics2.getHeight() - 1);
                    }
                    graphics.drawString("" + n3, this.oriX + (i * 2 + 1) * this.ranX / (2 * this.iDate.length) + 1, n - 1);
                    n4 = i;
                }
            }
        }
        graphics.setColor(new Color(9550298));
        graphics.fillRect(this.oriX + (this.curI * 2 + 1) * this.ranX / (2 * this.iDate.length), n - n2 + 1, 2, n2);
        final String string = this.dateStr(this.iDate[this.curI]) + "=" + this.fValue[this.curI] + this.getParameter("U");
        graphics.clearRect(0, n + 4, this.size().width, 20);
        graphics.setColor(new Color(0));
        int n6 = 0;
        if (b) {
            n6 = 14;
        }
        graphics.drawString(string, this.size().width - 8 - fontMetrics2.stringWidth(string), n + fontMetrics2.getAscent() + 5 + n6);
        graphics.drawString(this.dateStr(this.iDate[0]) + "~" + this.dateStr(this.iDate[this.iDate.length - 1]), this.oriX, n + fontMetrics2.getAscent() + 5);
        for (int j = 0; j < (int)(this.ranV / this.incV + 1.5); ++j) {
            final float n7 = this.oriV + this.incV * j;
            final int n8 = (int)(n - (n7 - this.oriV) * n2 / this.ranV);
            final String pf = this.pf(n7, this.iDig);
            graphics.setColor(new Color(12779645));
            graphics.drawString(pf, this.oriX + this.ranX + 6, n8 + 3);
            graphics.setColor(new Color(10066329));
            graphics.drawLine(this.oriX, n8, this.oriX + this.ranX - 1, n8);
        }
        int n9 = this.oriX + this.ranX / (2 * this.iDate.length);
        int n10 = (int)(n - (this.fValue[0] - this.oriV) * n2 / this.ranV);
        graphics.setColor(new Color(this.nLnColor));
        for (int k = 1; k < this.iDate.length; ++k) {
            final int n11 = this.oriX + (k * 2 + 1) * this.ranX / (2 * this.iDate.length);
            final int n12 = (int)(n - (this.fValue[k] - this.oriV) * n2 / this.ranV);
            graphics.drawLine(n9, n10, n11, n12);
            n9 = n11;
            n10 = n12;
        }
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.err == 0 && n >= this.oriX && n < this.oriX + this.ranX) {
            this.curI = (n - this.oriX) * this.iDate.length / this.ranX;
            this.bpaint(this.dBuf);
            this.repaint();
        }
        return true;
    }
    
    float[] parseF(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final float[] array = new float[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = new Float(stringTokenizer.nextToken());
        }
        return array;
    }
    
    public String pf(final float n, final int n2) {
        String s;
        if (n - (long)n == 0.0f) {
            s = (long)n + ".000000";
        }
        else {
            s = Float.toString(n + (float)(((n < 0.0f) ? -5 : 5) / Math.pow(10.0, n2 + 1))) + "000000";
        }
        String s2;
        if (n2 == 0) {
            s2 = s.substring(0, s.indexOf(46));
        }
        else {
            s2 = s.substring(0, s.indexOf(46) + n2 + 1);
        }
        return s2;
    }
    
    public void run() {
        while (this.runner == Thread.currentThread()) {
            if (this.err == 111) {
                this.pend -= 1000;
                if (this.pend < 0) {
                    this.inVal();
                }
            }
            this.repaint();
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void init() {
        this.nBkColor = Integer.parseInt(this.getParameter("BC"), 16);
        this.nLnColor = Integer.parseInt(this.getParameter("LC"), 16);
        this.setBackground(new Color(16777215));
        this.offScr = this.createImage(this.size().width, this.size().height);
        this.dBuf = this.offScr.getGraphics();
        this.BCD = this.getParameter("BCD");
        this.inVal();
    }
    
    public void inVal() {
        String string = "";
        this.err = 0;
        try {
            final URLConnection openConnection = new URL(this.getCodeBase(), this.BCD).openConnection();
            openConnection.setUseCaches(false);
            int read;
            while ((read = openConnection.getInputStream().read()) != -1) {
                if (read == 10) {
                    this.err = 1;
                }
                string += (char)read;
            }
        }
        catch (Exception ex) {
            this.err = 2;
        }
        final String trim = string.trim();
        if (trim.length() < 5) {
            this.err += 10;
        }
        final String s = "Pending";
        if (this.err == 1 && trim.substring(0, 7).compareTo(s) == 0) {
            this.err = 111;
            this.pend = Integer.parseInt(trim.substring(7)) * 1000;
        }
        if (this.err == 0) {
            final StringTokenizer stringTokenizer = new StringTokenizer(trim, " ");
            this.iDate = this.parseI(stringTokenizer.nextToken(), ",");
            this.fValue = this.parseF(stringTokenizer.nextToken(), ",");
            float n = this.fValue[0];
            float n2 = this.fValue[0];
            for (int i = 1; i < this.iDate.length; ++i) {
                if (this.fValue[i] < n) {
                    n = this.fValue[i];
                }
                if (this.fValue[i] > n2) {
                    n2 = this.fValue[i];
                }
            }
            this.incV = (n2 - n) / 8.0f;
            if (this.incV == 0.0f) {
                if (n2 == 0.0f) {
                    n = -1.0f;
                    final float incV = 1.0f;
                    this.incV = incV;
                    n2 = incV;
                }
                else {
                    this.incV = n2 / 100.0f;
                }
            }
            final float n3 = (float)(Math.log(this.incV) / Math.log(10.0));
            int n4 = (int)Math.floor(n3);
            final float n5 = n3 - n4;
            if (n5 == 0.0f) {
                this.incV = 1.0f;
            }
            else if (n5 < Math.log(2.0) / Math.log(10.0)) {
                this.incV = 2.0f;
            }
            else if (n5 < Math.log(5.0) / Math.log(10.0)) {
                this.incV = 5.0f;
            }
            else {
                this.incV = 1.0f;
                ++n4;
            }
            this.iDig = ((n4 < 0) ? (-n4) : false);
            this.incV *= (float)Math.pow(10.0, n4);
            final float oriV = (float)(Math.floor(n / this.incV) * this.incV);
            this.ranV = (float)(Math.ceil(n2 / this.incV) * this.incV) - oriV;
            this.oriV = oriV;
            this.curI = this.iDate.length - 1;
        }
        this.bpaint(this.dBuf);
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (n == 1006 && this.curI > 0) {
            --this.curI;
            this.bpaint(this.dBuf);
            this.repaint();
        }
        if (n == 1007 && this.curI < this.iDate.length - 1) {
            ++this.curI;
            this.bpaint(this.dBuf);
            this.repaint();
        }
        return true;
    }
}
