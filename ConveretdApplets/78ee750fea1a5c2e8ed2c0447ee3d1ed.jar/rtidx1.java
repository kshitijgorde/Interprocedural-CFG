import java.awt.Cursor;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URL;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class rtidx1 extends Applet implements Runnable
{
    Thread runner;
    Graphics dBuf;
    Image offScr;
    Font vFont;
    FontMetrics vMet;
    int t276;
    int[][] tns1;
    int[] difv;
    int tnsi1;
    int[][] tns2;
    int tnsi2;
    int oriX;
    int ranX;
    int[] oriY1;
    int[] ranY1;
    int[] oriY2;
    int[] ranY2;
    int tY;
    String[] aID;
    String[] tStr;
    int waitT;
    int ss;
    int yi;
    Color[] cColor;
    int LMode;
    int DMode;
    int GMode;
    int TMode;
    int sele;
    
    public void stop() {
        if (this.runner != null) {
            this.runner = null;
        }
    }
    
    int readASP(final String s, int n, final int[][] array) {
        n = 0;
        try {
            final URLConnection openConnection = new URL(this.getCodeBase(), s).openConnection();
            openConnection.setUseCaches(false);
            final InputStream inputStream = openConnection.getInputStream();
            int n2 = 0;
            int n3 = 0;
            int read;
            while ((read = inputStream.read()) != -1) {
                if (n >= 276) {
                    break;
                }
                if (read == 44) {
                    array[n][n2] = n3;
                    ++n2;
                    n3 = 0;
                }
                else if (read == 13) {
                    array[n][n2] = n3;
                    n2 = 0;
                    n3 = 0;
                    if (array[n][0] != 0) {
                        ++n;
                    }
                    inputStream.read();
                }
                else {
                    n3 = n3 * 10 + read - 48;
                }
            }
        }
        catch (Exception ex) {}
        return n;
    }
    
    String[] parse(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final String[] array = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = stringTokenizer.nextToken();
        }
        return array;
    }
    
    public void tpaint(final int n, final int n2, final int n3, final int[][] array) {
        final int n4 = this.tY + this.vMet.getAscent() + 2;
        int n5 = 0;
        do {
            if (n2 <= 30 + (this.size().width - 30) / 2) {
                this.dBuf.setColor(this.cColor[25 + n5]);
            }
            else {
                this.dBuf.setColor(this.cColor[28 + n5]);
            }
            this.dBuf.fillRect(n2 - (this.size().width - 30) / 2, this.tY + 16 + 16 * n5, (this.size().width - 30) / 2 + 1, 16);
        } while (++n5 < 3);
        this.dBuf.setColor(this.cColor[16]);
        this.dBuf.drawString(this.aID[n * 2 + this.LMode], n2 - this.vMet.stringWidth(this.aID[n * 2 + this.LMode]) - 4, n4);
        if (n3 == 0) {
            return;
        }
        this.dBuf.setColor(this.cColor[21]);
        if (this.LMode == 1) {
            if (this.tStr[6].equalsIgnoreCase("M")) {
                this.dVo(this.vMet, array[n3 - 1][2], n2 - 2, n4 + 48, 0, this.tStr[1 + this.LMode * 5], 0);
            }
            else {
                this.dVo(this.vMet, array[n3 - 1][2] / 1000.0f, n2 - 2, n4 + 48, 2, this.tStr[1 + this.LMode * 5], 0);
            }
        }
        else {
            this.dVo(this.vMet, array[n3 - 1][2] / 100.0f, n2 - 2, n4 + 48, 2, this.tStr[1 + this.LMode * 5], 0);
        }
        this.dId(this.vMet, array[n3 - 1][1], n2 - 2, n4 + 16, "", array, 18);
        this.dIdUd(this.vMet, array[n3 - 1][1] - array[0][1], n2 - 2, n4 + 32, "", 18);
    }
    
    Color[] parseC(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final Color[] array = new Color[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = new Color(Integer.parseInt(stringTokenizer.nextToken(), 16));
        }
        return array;
    }
    
    public void getTNS() {
        if ((this.GMode != 2 && this.GMode != 5) || this.TMode % 2 == 1) {
            this.tnsi1 = this.readASP("/z/twidx/ztwidx_TWSI.djbcd", this.tnsi1, this.tns1);
            this.waitT = ((this.tnsi1 < 182) ? 30 : 60);
        }
        if ((this.GMode != 1 && this.GMode != 5) || this.TMode > 1) {
            this.tnsi2 = this.readASP("/z/twidx/ztwidx_OTCI.djbcd", this.tnsi2, this.tns2);
            this.waitT = ((this.tnsi2 < 182) ? 30 : 60);
        }
        this.bpaint();
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.offScr, 0, 0, this);
    }
    
    public rtidx1() {
        this.runner = null;
        this.t276 = 276;
        this.tns1 = new int[276][3];
        this.difv = new int[276];
        this.tns2 = new int[276][3];
        this.oriY1 = new int[2];
        this.ranY1 = new int[2];
        this.oriY2 = new int[2];
        this.ranY2 = new int[2];
        this.ss = 15;
        this.sele = 0;
    }
    
    public String pFx(final float n, final int n2) {
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
    
    public int dVo(final FontMetrics fontMetrics, final float n, final int n2, final int n3, final int n4, final String s, final int n5) {
        final String string = this.pFx(n, n4) + s;
        final int stringWidth = fontMetrics.stringWidth(string);
        if (n5 == 1) {
            this.dBuf.drawString(string, n2, n3);
        }
        else {
            this.dBuf.drawString(string, n2 - stringWidth, n3);
        }
        return stringWidth;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void bpaint() {
        this.dBuf.setColor(this.cColor[0]);
        this.dBuf.fillRect(0, 0, this.size().width, this.size().height);
        if (this.GMode == 1 || this.GMode == 3 || (this.GMode == 0 && this.waitT % 10 < 5) || (this.GMode == 4 && this.sele == 0)) {
            this.cpaint(0, this.oriY1, this.ranY1, this.tnsi1, this.tns1);
        }
        if (this.GMode == 2 || this.GMode == 3 || (this.GMode == 0 && this.waitT % 10 >= 5) || (this.GMode == 4 && this.sele == 1)) {
            this.cpaint(1, this.oriY2, this.ranY2, this.tnsi2, this.tns2);
        }
        if (this.TMode > 0) {
            this.dBuf.setColor(this.cColor[15]);
            this.dBuf.fillRect(0, this.tY, this.size().width, 16);
            int n = 0;
            do {
                this.dBuf.setColor(this.cColor[22 + n]);
                this.dBuf.fillRect(0, this.tY + 16 + 16 * n, this.size().width, 16);
                this.dBuf.setColor(this.cColor[17]);
                this.dBuf.drawString(this.tStr[2 + n + this.LMode * 5], (30 - this.vMet.stringWidth(this.tStr[2 + n + this.LMode * 5])) / 2, this.tY + this.vMet.getAscent() + 18 + 16 * n);
            } while (++n < 3);
            int width = 30 + (this.size().width - 30) / 2;
            if (this.TMode % 2 == 1) {
                this.tpaint(0, width, this.tnsi1, this.tns1);
                width = this.size().width;
            }
            if (this.TMode > 1) {
                this.tpaint(1, width, this.tnsi2, this.tns2);
            }
        }
        this.dBuf.setColor(this.cColor[12]);
        int n2;
        if (this.GMode != 5) {
            n2 = this.size().width - this.vMet.stringWidth(this.tStr[this.LMode * 5]) - 1;
        }
        else {
            n2 = 0;
        }
        this.dBuf.fillRect(n2, 0, this.vMet.stringWidth(this.tStr[this.LMode * 5]) + 1, 15);
        this.dBuf.setColor(this.cColor[13]);
        this.dBuf.drawString(this.tStr[this.LMode * 5], n2 + 1, this.vMet.getAscent() + 2);
    }
    
    public void cpaint(final int n, final int[] array, final int[] array2, final int n2, final int[][] array3) {
        this.dBuf.setColor(this.cColor[1]);
        this.dBuf.fillRect(this.oriX, array[0] - array2[0], this.ranX, array[1] - array[0] + array2[0] + 1);
        for (int i = 0; i <= this.t276 / 60; ++i) {
            this.dBuf.setColor(this.cColor[9]);
            this.dBuf.fillRect(this.oriX + i * 60 * this.ranX / this.t276, array[0] - array2[0], 1, array[1] - array[0] + array2[0] + 1);
            this.dBuf.setColor(this.cColor[11]);
            this.dBuf.drawString(i + 9 + "", this.oriX + i * 60 * this.ranX / this.t276 - 4, array[1] + 11);
        }
        this.dBuf.setColor(this.cColor[2]);
        this.dBuf.drawString(this.aID[n * 2 + this.LMode] + " " + ((n2 > 0) ? this.sMin(array3[n2 - 1][0]) : ""), this.oriX + 3, array[0] - array2[0] - 14 + this.vMet.getAscent() + 2);
        if (this.GMode == 4) {
            final int stringWidth = this.vMet.stringWidth(this.aID[2 - this.sele * 2 + this.LMode]);
            this.dBuf.setColor(this.cColor[0]);
            this.dBuf.fill3DRect(this.size().width - stringWidth - this.vMet.stringWidth(this.tStr[this.LMode * 5]) - 9, 0, stringWidth + 3, 15, true);
            this.dBuf.setColor(this.cColor[2]);
            this.dBuf.drawString(this.aID[2 - this.sele * 2 + this.LMode], this.size().width - stringWidth - this.vMet.stringWidth(this.tStr[this.LMode * 5]) - 7, this.vMet.getAscent() + 2);
        }
        this.dBuf.setColor(this.cColor[14]);
        this.dBuf.fillRect(0, array[1] + 13, this.size().width, 15);
        if (n2 == 0) {
            return;
        }
        int n3 = array3[0][1];
        int n4 = array3[0][1];
        int n5 = array3[0][1];
        int n6 = array3[0][2];
        this.difv[0] = array3[0][2];
        for (int j = 1; j < n2; ++j) {
            this.difv[j] = array3[j][2] - array3[j - 1][2];
            if (this.difv[j] > n6) {
                n6 = this.difv[j];
            }
            if (array3[j][1] > n3) {
                n3 = array3[j][1];
            }
            if (array3[j][1] < n4 || n4 <= 0) {
                n4 = array3[j][1];
            }
            if (n5 <= 0) {
                n5 = array3[j][1];
            }
        }
        if (n4 <= 0) {
            return;
        }
        array3[0][1] = n5;
        final int[] array4 = new int[2];
        final int[] array5 = new int[2];
        final int n7 = Math.max(n3 - array3[0][1], array3[0][1] - n4) * 200 / array3[0][1] + 1;
        array4[0] = array3[0][1] - n7 * array3[0][1] / 200;
        array5[0] = n7 * array3[0][1] / 100;
        array4[1] = 0;
        final int n8 = (int)Math.ceil(this.ss * n7 * 2.0f / array2[0]);
        final int n9 = this.yi * 10;
        array5[1] = n6 / n9 * n9 + n9;
        final int n10 = array[0] - array2[0];
        final int n11 = array[0] - (array3[0][1] - array4[0]) * array2[0] / array5[0];
        this.dBuf.setColor(this.cColor[7]);
        this.dBuf.fillRect(this.oriX, n11, this.ranX, 1);
        this.dBuf.setColor(this.cColor[2]);
        this.dIds(this.vMet, array3[0][1], this.oriX - 1, n11 - 1);
        for (int k = n8; k <= n7; k += n8) {
            final int n12 = array[0] - (n7 + k) * array3[0][1] / 200 * array2[0] / array5[0];
            this.dBuf.setColor(this.cColor[9]);
            this.dBuf.fillRect(this.oriX, n12, this.ranX, 1);
            this.dBuf.setColor(this.cColor[3]);
            this.dIds(this.vMet, (int)(array3[0][1] + k * array3[0][1] / 200.0f + 0.5f), this.oriX - 1, n12 - 1);
        }
        final int n13 = array[0];
        for (int l = -n8; l >= -n7; l -= n8) {
            final int n14 = array[0] - (n7 + l) * array3[0][1] / 200 * array2[0] / array5[0];
            this.dBuf.setColor(this.cColor[9]);
            this.dBuf.fillRect(this.oriX, n14, this.ranX, 1);
            this.dBuf.setColor(this.cColor[4]);
            this.dIds(this.vMet, (int)(array3[0][1] + l * array3[0][1] / 200.0f + 0.5f), this.oriX - 1, n14 - 1);
        }
        for (int n15 = 0; n15 <= this.yi; ++n15) {
            final int n16 = array[1] - array2[1] * n15 / this.yi;
            if (this.DMode == 1) {
                this.dBuf.setColor(this.cColor[2]);
                this.dBuf.fillRect(this.oriX + this.ranX, n16, 3, 1);
                this.dBuf.setColor(this.cColor[5]);
                if (this.LMode == 1) {
                    if (this.tStr[6].equalsIgnoreCase("M")) {
                        this.dVo(this.vMet, array5[1] * n15 / this.yi, this.oriX + this.ranX + 4, n16, 0, "", 1);
                    }
                    else {
                        this.dVo(this.vMet, array5[1] * n15 / this.yi / 1000.0f, this.oriX + this.ranX + 4, n16, 1, "", 1);
                    }
                }
                else {
                    this.dVo(this.vMet, array5[1] * n15 / this.yi / 100.0f, this.oriX + this.ranX + 4, n16, 1, "", 1);
                }
            }
            else {
                this.dBuf.setColor(this.cColor[9]);
                this.dBuf.fillRect(this.oriX, n16, this.ranX, 1);
                this.dBuf.setColor(this.cColor[5]);
                if (this.LMode == 1) {
                    if (this.tStr[6].equalsIgnoreCase("M")) {
                        this.dVo(this.vMet, array5[1] * n15 / this.yi, this.oriX - 1, n16, 0, "", 0);
                    }
                    else {
                        this.dVo(this.vMet, array5[1] * n15 / this.yi / 1000.0f, this.oriX - 1, n16, 1, "", 0);
                    }
                }
                else {
                    this.dVo(this.vMet, array5[1] * n15 / this.yi / 100.0f, this.oriX - 1, n16, 1, "", 0);
                }
            }
            if (this.DMode != 1 && n15 == this.yi - 1) {
                break;
            }
        }
        if (this.DMode != 1) {
            this.dBuf.setColor(this.cColor[8]);
            this.dBuf.fillRect(this.oriX, array[1] - array2[1], this.ranX, 1);
        }
        final int n17 = array[1] + this.vMet.getAscent() + 15;
        this.dBuf.setColor(this.cColor[2]);
        final int n18 = this.size().width - 1;
        int n19;
        if (this.LMode == 1) {
            if (this.tStr[6].equalsIgnoreCase("M")) {
                n19 = n18 - this.dVo(this.vMet, array3[n2 - 1][2], n18, n17, 0, this.tStr[1 + this.LMode * 5], 0) - 3;
            }
            else {
                n19 = n18 - this.dVo(this.vMet, array3[n2 - 1][2] / 1000.0f, n18, n17, 2, this.tStr[1 + this.LMode * 5], 0) - 3;
            }
        }
        else {
            n19 = n18 - this.dVo(this.vMet, array3[n2 - 1][2] / 100.0f, n18, n17, 2, this.tStr[1 + this.LMode * 5], 0) - 3;
        }
        if (this.size().width >= 220) {
            int n20 = (int)((array3[n2 - 1][1] - array3[0][1]) * 100000L / array3[0][1]);
            if (array3[n2 - 1][1] >= array3[0][1]) {
                n20 += 3;
            }
            else {
                n20 -= 3;
            }
            n19 = n19 - this.dIdUd(this.vMet, n20 / 10, n19, n17, "%", 2) - 3;
        }
        this.dId(this.vMet, array3[n2 - 1][1], n19 - this.dIdUd(this.vMet, array3[n2 - 1][1] - array3[0][1], n19, n17, "", 2) - 3, n17, "", array3, 2);
        int n21 = this.oriX + (array3[0][0] / 100 * 60 + array3[0][0] % 100 - 540) * this.ranX / this.t276;
        int n22 = array[0] - (array3[0][1] - array4[0]) * array2[0] / array5[0];
        if (array3[0][1] <= 0) {
            n22 = array[0] - array2[0] / 2;
        }
        int n23 = 0;
        while (n23 < n2) {
            final int n24 = array3[n23][0] / 100 * 60 + array3[n23][0] % 100;
            final int n25 = this.difv[n23];
            final int n26 = array3[n23][1];
            ++n23;
            final int n27 = this.oriX + (n24 - 540) * this.ranX / this.t276;
            this.dBuf.setColor(this.cColor[10]);
            this.dBuf.drawLine(n27, array[1], n27, array[1] - (n25 - array4[1]) * array2[1] / array5[1]);
            this.dBuf.setColor(this.cColor[6]);
            int n28 = array[0] - (n26 - array4[0]) * array2[0] / array5[0];
            if (n26 <= 0) {
                n28 = n22;
            }
            this.dBuf.drawLine(n21, n22, n27, n28);
            n21 = n27;
            n22 = n28;
        }
    }
    
    public String sMin(final int n) {
        final String string = "" + (n + 10000);
        return string.substring(1, 3) + ":" + string.substring(3);
    }
    
    public int dIdUd(final FontMetrics fontMetrics, int n, final int n2, final int n3, final String s, final int n4) {
        String s2 = "";
        if (n > 0) {
            s2 = ((this.LMode == 0) ? "\u25b2" : "+");
            this.dBuf.setColor(this.cColor[n4 + 1]);
        }
        else if (n < 0) {
            s2 = ((this.LMode == 0) ? "\u25bc" : "-");
            n = -n;
            this.dBuf.setColor(this.cColor[n4 + 2]);
        }
        else {
            this.dBuf.setColor(this.cColor[n4]);
        }
        final String string = s2 + this.pFx(n / 100.0f, 2) + s;
        final int stringWidth = fontMetrics.stringWidth(string);
        this.dBuf.drawString(string, n2 - stringWidth, n3);
        return stringWidth;
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (n2 < 15 && ((this.GMode != 5 && n >= this.size().width - this.vMet.stringWidth(this.tStr[this.LMode * 5]) - 2) || (this.GMode == 5 && n < this.vMet.stringWidth(this.tStr[this.LMode * 5]) + 2))) {
            final String s = "http://www.moneydj.com";
            try {
                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), s), "_blank");
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else if (n2 < 15 && this.GMode == 4 && n >= this.size().width - this.vMet.stringWidth(this.tStr[this.LMode * 5]) - 6 - this.vMet.stringWidth(this.aID[2 - this.sele * 2 + this.LMode]) - 4 && n < this.size().width - this.vMet.stringWidth(this.tStr[this.LMode * 5]) - 6) {
            if (this.sele == 0) {
                this.sele = 1;
            }
            else {
                this.sele = 0;
            }
            this.bpaint();
            this.repaint();
        }
        return true;
    }
    
    public int inData() {
        if (this.waitT > 1) {
            --this.waitT;
            if (this.GMode == 0 && this.waitT % 5 == 0) {
                this.bpaint();
            }
            this.repaint();
        }
        else {
            this.getTNS();
        }
        return 1000;
    }
    
    public int dId(final FontMetrics fontMetrics, final int n, final int n2, final int n3, final String s, final int[][] array, final int n4) {
        this.dBuf.setColor(this.cColor[n4]);
        String string;
        if (n == 0) {
            string = "--";
        }
        else {
            string = this.pFx(n / 100.0f, 2) + s;
        }
        if (n > array[0][1]) {
            this.dBuf.setColor(this.cColor[n4 + 1]);
        }
        else if (n < array[0][1]) {
            this.dBuf.setColor(this.cColor[n4 + 2]);
        }
        final int stringWidth = fontMetrics.stringWidth(string);
        this.dBuf.drawString(string, n2 - stringWidth, n3);
        return stringWidth;
    }
    
    public void dIds(final FontMetrics fontMetrics, int n, final int n2, final int n3) {
        int n4;
        if (n >= 1000000) {
            n /= 10;
            n4 = 1;
        }
        else {
            n4 = 2;
        }
        String string = "" + n;
        if (n < 0) {
            string = "--";
            n4 = 0;
        }
        this.dBuf.drawString(string, n2 - fontMetrics.stringWidth(string), n3);
        if (n4 > 0) {
            this.dBuf.drawLine(n2 - n4 * fontMetrics.stringWidth("8"), n3 + 1, n2 - 2, n3 + 1);
        }
    }
    
    public void run() {
        while (this.runner == Thread.currentThread()) {
            final int inData = this.inData();
            try {
                Thread.sleep(inData);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void init() {
        if (this.getParameter("YEAR") != null && Integer.parseInt(this.getParameter("YEAR")) < 2001) {
            this.t276 = 186;
        }
        if (this.getParameter("ID") != null) {
            this.aID = this.parse(this.getParameter("ID"), ",");
        }
        else {
            this.aID = this.parse("\u52a0\u6b0a,TSEI,\u6ac3\u6aaf,OTCI", ",");
        }
        this.tStr = this.parse("\u5609\u5be6\u8cc7\u8a0a,\u5104,\u6307\u6578,\u6f32\u8dcc,\u91cf,JUST,bn,IDX,CHG,VOL", ",");
        if (this.getParameter("LOGO") != null) {
            this.tStr[0] = "JUST";
        }
        if (this.getParameter("MILLIAN") != null) {
            this.tStr[6] = "M";
        }
        this.LMode = Integer.parseInt(this.getParameter("LMODE"));
        this.DMode = Integer.parseInt(this.getParameter("DMODE"));
        this.GMode = Integer.parseInt(this.getParameter("GMODE"));
        this.TMode = Integer.parseInt(this.getParameter("TMODE"));
        if (this.getParameter("COLOR") != null) {
            this.cColor = this.parseC(this.getParameter("COLOR"), ",");
        }
        else {
            this.cColor = this.parseC("d9edfb,ffffff,0,ff0000,8800,168b3,ff0000,8dcef4,8800,dddddd,5c9dfe,ff0000,99,ffffff,7fc6f7,6397d,ffffff,0,0,ff0000,8800,0,c7e6fc,7fc6f7,c7e6fc,fdfbd7,ebeba0,fdfbd7,a5effb,7fc6f7,a5effb", ",");
        }
        if (this.TMode != 0) {
            if (this.GMode == 5) {
                this.tY = 0;
            }
            else {
                this.tY = this.size().height - 64;
            }
        }
        else {
            this.tY = this.size().height;
        }
        this.oriX = 43;
        this.oriY2[1] = this.tY - 28;
        int n;
        if (this.GMode == 3) {
            this.oriY1[1] = this.tY / 2 - 28;
            n = this.tY / 2 - 43;
        }
        else {
            this.oriY1[1] = this.oriY2[1];
            n = this.tY - 43;
        }
        if (this.DMode == 1) {
            this.ranX = this.size().width - this.oriX - 31;
            this.ranY1[0] = (this.ranY2[0] = ((n < 0) ? 0 : n));
            this.ranY1[1] = (this.ranY2[1] = ((n < 0) ? 0 : (n / 3)));
            this.oriY2[0] = this.oriY2[1];
            this.oriY1[0] = this.oriY1[1];
        }
        else {
            this.ranX = this.size().width - this.oriX - 8;
            this.ranY1[0] = (this.ranY2[0] = ((n < 0) ? 0 : (n * 2 / 3)));
            this.ranY1[1] = (this.ranY2[1] = ((n < 0) ? 0 : (n - this.ranY2[0])));
            this.oriY2[0] = this.oriY2[1] - this.ranY2[1];
            this.oriY1[0] = this.oriY1[1] - this.ranY1[1];
        }
        this.yi = this.ranY1[1] / this.ss;
        if (this.yi < 1) {
            this.yi = 1;
        }
        if (this.LMode == 0) {
            this.vFont = new Font("\u7d30\u660e\u9ad4", 0, 12);
        }
        else {
            this.vFont = new Font("Courier", 0, 12);
        }
        this.vMet = this.getFontMetrics(this.vFont);
        this.offScr = this.createImage(this.size().width, this.size().height);
        (this.dBuf = this.offScr.getGraphics()).setFont(this.vFont);
        this.getTNS();
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (n2 < 15 && ((this.GMode != 5 && n >= this.size().width - this.vMet.stringWidth(this.tStr[this.LMode * 5]) - 2) || (this.GMode == 5 && n < this.vMet.stringWidth(this.tStr[this.LMode * 5]) + 2) || (this.GMode == 4 && n >= this.size().width - this.vMet.stringWidth(this.tStr[this.LMode * 5]) - 6 - this.vMet.stringWidth(this.aID[2 - this.sele * 2 + this.LMode]) - 4 && n < this.size().width - this.vMet.stringWidth(this.tStr[this.LMode * 5]) - 6))) {
            this.setCursor(new Cursor(12));
        }
        else {
            this.setCursor(new Cursor(0));
        }
        return true;
    }
}
