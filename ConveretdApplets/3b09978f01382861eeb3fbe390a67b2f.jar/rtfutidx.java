import java.awt.MenuItem;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.TimeZone;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.Calendar;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.Color;
import java.util.Vector;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class rtfutidx extends Applet implements Runnable
{
    Thread runner;
    Graphics dBuf;
    Image offScr;
    Font bFont;
    FontMetrics bMet;
    Font sFont;
    FontMetrics sMet;
    int t276;
    Vector arr;
    F3_Quote[] tickQ;
    int nActive;
    Vector[] tick;
    int oriX;
    int ranX;
    int[] oriY;
    int[] ranY;
    int[] m_nOrgY;
    int[] m_nY0;
    int[] m_nY1;
    int[] m_nR0;
    int[] m_nR1;
    int m_nRange;
    int m_nVPer;
    int m_nMode;
    int m_nType;
    int m_nOption;
    int[] m_nDP;
    int[] m_nDot;
    int waitT;
    int[] ttime;
    int timediff;
    int chgId;
    String tid;
    boolean m_bInit;
    int m_nFDelayT;
    String trd;
    String trd_t;
    Color[] cColor;
    int zw;
    int zws;
    Rectangle m_rcMenu;
    Rectangle m_rcLogo;
    PopupMenu m_menu;
    byte[] m_arrData;
    int m_nCount;
    
    protected void calcDispRect() {
        String s = "88\u4e00";
        if (this.m_nMode != 0) {
            s = "888\u4e00";
        }
        else if (this.tickQ[this.nActive].id != null && (this.tickQ[this.nActive].id.indexOf("GB") != -1 || this.tickQ[this.nActive].id.indexOf("CP") != -1)) {
            s = "888\u4e00";
        }
        this.zw = (this.bMet.stringWidth(s) + 2) / 4;
        this.zws = (this.sMet.stringWidth(s) + 2) / 4;
        this.oriX = this.zws * 5 + 3;
        if (this.m_nType == 0) {
            this.ranX = this.size().width - this.oriX - 5;
        }
        else {
            this.ranX = this.size().width - this.oriX - this.bMet.stringWidth("-88\u4e00");
        }
        if (this.m_nMode != 0) {
            final int size = this.arr.size();
            final int nRange = this.size().height / size;
            this.m_nRange = nRange;
            int n = 0;
            this.m_nY0 = new int[size];
            this.m_nY1 = new int[size];
            this.m_nR0 = new int[size];
            this.m_nR1 = new int[size];
            this.m_nOrgY = new int[size];
            for (int i = 0; i < size; ++i) {
                this.m_nOrgY[i] = n;
                final int n2 = nRange - this.sMet.getHeight();
                this.m_nY1[i] = n2 - 25 + n;
                this.m_nR1[i] = (n2 - 50) * this.m_nVPer / 100;
                if (this.m_nType == 0) {
                    this.m_nY0[i] = this.m_nY1[i] - this.m_nR1[i];
                    this.m_nR0[i] = n2 - 50 - this.m_nR1[i];
                }
                else {
                    this.m_nY0[i] = this.m_nY1[i];
                    this.m_nR0[i] = n2 - 50;
                }
                n += nRange;
            }
            return;
        }
        final int height = this.size().height;
        this.m_nRange = height;
        final int n3 = height - this.sMet.getHeight();
        this.oriY[1] = n3 - 25;
        this.ranY[1] = (n3 - 50) * this.m_nVPer / 100;
        if (this.m_nType == 0) {
            this.oriY[0] = this.oriY[1] - this.ranY[1];
            this.ranY[0] = n3 - 50 - this.ranY[1];
            return;
        }
        this.oriY[0] = this.oriY[1];
        this.ranY[0] = n3 - 50;
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner = null;
        }
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.m_nOption == 1 && this.m_rcLogo != null && this.m_rcLogo.contains(n, n2)) {
            this.setCursor(new Cursor(12));
            return true;
        }
        if (this.hasMenu() && this.m_rcMenu != null && this.m_rcMenu.contains(n, n2)) {
            this.setCursor(new Cursor(12));
            return true;
        }
        this.setCursor(new Cursor(0));
        return true;
    }
    
    private String fmtTime(final String s) {
        final Calendar instance = Calendar.getInstance();
        return ((instance.get(11) < 10) ? ("0" + instance.get(11)) : ("" + instance.get(11))) + ":" + ((instance.get(12) < 10) ? ("0" + instance.get(12)) : ("" + instance.get(12))) + ":" + ((instance.get(13) < 10) ? ("0" + instance.get(13)) : ("" + instance.get(13))) + " " + s;
    }
    
    String[] parse(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final String[] array = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = stringTokenizer.nextToken();
        }
        return array;
    }
    
    public String getChar(final int n) {
        String string = "";
        for (int i = 0; i < n; ++i) {
            string += (char)this.m_arrData[this.m_nCount + i];
        }
        this.m_nCount += n;
        return string;
    }
    
    public void spaint(final String s, final int n, final int n2) {
        final int stringWidth = this.sMet.stringWidth(s);
        this.dBuf.setColor(this.cColor[17]);
        this.dBuf.fill3DRect(n, n2, stringWidth + 3, 15, true);
        this.dBuf.setColor(this.cColor[18]);
        this.dBuf.drawString(s, n + 2, n2 + this.sMet.getAscent() + 2);
    }
    
    public void dSr(final FontMetrics fontMetrics, final String s, final int n, final int n2) {
        this.dBuf.drawString(s, n - fontMetrics.stringWidth(s), n2);
    }
    
    protected void getIdSetting(final String s) {
        final String[] parse = this.parse(s, ",");
        final Vector asp = this.readASP("/z/twidx/zyzjakaallid.djbcd");
        final String[] parse2 = this.parse((asp != null && asp.size() > 0) ? asp.elementAt(0) : "", ";");
        final Vector vector = new Vector<String[]>();
        for (int i = 0; i < parse2.length; ++i) {
            final String[] parse3 = this.parse(parse2[i], ",");
            boolean b = parse.length == 0;
            for (int j = 0; j < parse.length; ++j) {
                if (parse3[0].substring(0, 2).equalsIgnoreCase(parse[j])) {
                    b = true;
                    break;
                }
            }
            if (b) {
                vector.addElement(parse3);
            }
        }
        final int size = vector.size();
        this.m_nDot = new int[size];
        this.m_nDP = new int[size];
        this.tickQ = new F3_Quote[size];
        this.tick = new Vector[size];
        this.arr.removeAllElements();
        this.nActive = 0;
        if (size == 0) {
            return;
        }
        for (int k = 0; k < size; ++k) {
            final String[] array = vector.elementAt(k);
            final Name name = new Name();
            name.id = array[0];
            name.name = array[1];
            this.arr.addElement(name);
            this.m_nDP[k] = Integer.parseInt(array[2]);
            this.m_nDot[k] = Integer.parseInt(array[3]);
            this.initTick(k);
        }
    }
    
    public void dPr(final FontMetrics fontMetrics, int n, final int n2, final int n3, final F3_Quote f3_Quote, final int n4) {
        this.dBuf.setColor(this.cColor[4]);
        String string;
        int n5;
        int n6;
        if (n <= 0) {
            string = "--";
            n5 = 0;
            n6 = fontMetrics.stringWidth(string);
        }
        else {
            final int n7 = n;
            final String substring = f3_Quote.id.substring(0, 2);
            if (this.m_nDot != null) {
                if (this.m_nMode == 0) {
                    n5 = this.m_nDot[this.nActive];
                }
                else {
                    n5 = this.m_nDot[n4];
                }
            }
            else if (substring.equalsIgnoreCase("TX") || substring.equalsIgnoreCase("MT") || substring.equalsIgnoreCase("TV")) {
                n5 = 0;
            }
            else if (substring.equalsIgnoreCase("TF")) {
                n5 = 1;
            }
            else if (substring.equalsIgnoreCase("GB")) {
                n5 = 3;
            }
            else {
                n5 = 2;
            }
            for (int i = 1; i >= n5; --i) {
                n /= 10;
            }
            string = "" + n;
            n6 = fontMetrics.stringWidth(string);
            if (f3_Quote.preC > 0) {
                if (n7 > f3_Quote.preC) {
                    this.dBuf.setColor(this.cColor[2]);
                }
                else if (n7 < f3_Quote.preC) {
                    this.dBuf.setColor(this.cColor[3]);
                }
            }
        }
        this.dBuf.drawString(string, n2 - n6, n3);
        if (n5 > 0) {
            this.dBuf.drawLine(n2 - n5 * fontMetrics.stringWidth("8"), n3 + 2, n2 - 2, n3 + 2);
        }
    }
    
    boolean isTTime() {
        final Calendar instance = Calendar.getInstance();
        final int n = (instance.get(11) * 60 + instance.get(12) + this.timediff) % 1440;
        return this.ttime[0] <= n && n <= this.ttime[1];
    }
    
    void getServTime() {
        this.timediff = 0;
        try {
            final URLConnection openConnection = new URL(this.getCodeBase(), "/z/twidx/gettime.asp").openConnection();
            openConnection.setUseCaches(false);
            final InputStream inputStream = openConnection.getInputStream();
            final byte[] array = new byte[256];
            inputStream.read(array);
            String substring = new String(array);
            final int index = substring.indexOf(32);
            if (index >= 0) {
                substring = substring.substring(index + 1);
            }
            if (substring.length() < 8) {
                return;
            }
            final int int1 = Integer.parseInt(substring.substring(0, 2));
            final int int2 = Integer.parseInt(substring.substring(3, 5));
            final Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("CTT"));
            this.timediff = int1 * 60 + int2 - (instance.get(11) * 60 + instance.get(12));
        }
        catch (Exception ex) {}
    }
    
    public void dNu(final FontMetrics fontMetrics, final int n, final int n2, final int n3) {
        final String s = (n < 0) ? "--" : ("" + n);
        this.dBuf.drawString(s, n2 - fontMetrics.stringWidth(s), n3);
    }
    
    protected int procData(final byte[] array, int n, final Vector vector) {
        int n2 = 0;
        int n3 = -1;
        int n4 = 0;
        for (int i = 0; i < n; ++i) {
            if (n3 < 0) {
                n3 = i;
            }
            switch (array[i]) {
                case 13: {
                    n2 = 1;
                    break;
                }
                case 10: {
                    final int n5 = i - n3 + 1;
                    n4 += n5;
                    if (n5 > 0) {
                        String s;
                        if (n2 != 0) {
                            s = new String(array, n3, n5 - 2);
                        }
                        else {
                            s = new String(array, n3, n5 - 1);
                        }
                        if (s.length() > 0) {
                            vector.addElement(s);
                        }
                    }
                    n2 = 0;
                    n3 = -1;
                    break;
                }
                default: {
                    if (n2 != 0) {
                        final int n6 = i - n3;
                        n4 += n6;
                        if (n6 > 0) {
                            final String s2 = new String(array, n3, n6 - 1);
                            if (s2.length() > 0) {
                                vector.addElement(s2);
                            }
                        }
                        n2 = 0;
                        n3 = i;
                        break;
                    }
                    break;
                }
            }
        }
        if (n2 != 0) {
            final int n7 = n - n3;
            n4 += n7;
            if (n7 > 0) {
                final String s3 = new String(array, n3, n7 - 1);
                if (s3.length() > 0) {
                    vector.addElement(s3);
                }
            }
        }
        if (n4 > 0) {
            final int n8 = n - n4;
            if (n8 > 0) {
                System.arraycopy(array, n4, array, 0, n8);
            }
            n = n8;
        }
        return n;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public synchronized void bpaint() {
        if (this.offScr == null) {
            return;
        }
        this.dBuf.setColor(this.cColor[0]);
        this.dBuf.fillRect(0, 0, this.size().width, this.size().height);
        this.dBuf.setColor(this.cColor[1]);
        this.dBuf.setFont(this.bFont);
        if (this.m_nMode == 0) {
            this.paintChart(this.oriY[0], this.oriY[1], this.ranY[0], this.ranY[1], this.nActive, 0);
            if (this.arr.size() > 1) {
                this.dBuf.setColor(this.cColor[17]);
                this.dBuf.fill3DRect(this.m_rcMenu.x, this.m_rcMenu.y, this.m_rcMenu.width, this.m_rcMenu.height, true);
                this.dBuf.setColor(this.cColor[18]);
                this.dBuf.drawString("\u5546\u54c1", this.m_rcMenu.x + (this.m_rcMenu.width - this.bMet.stringWidth("\u5546\u54c1")) / 2, this.bMet.getAscent() + 6);
            }
        }
        else {
            for (int i = 0; i < this.arr.size(); ++i) {
                this.dBuf.setColor(this.cColor[1]);
                this.paintChart(this.m_nY0[i], this.m_nY1[i], this.m_nR0[i], this.m_nR1[i], i, this.m_nOrgY[i]);
            }
        }
        if (this.m_nOption == 1) {
            this.dBuf.setColor(this.cColor[17]);
            this.dBuf.fill3DRect(this.m_rcLogo.x, this.m_rcLogo.y, this.m_rcLogo.width, this.m_rcLogo.height, true);
            this.dBuf.setColor(this.cColor[18]);
            this.dBuf.drawString("\u5609\u5be6", this.m_rcLogo.x + (this.m_rcLogo.width - this.bMet.stringWidth("\u5609\u5be6")) / 2, this.bMet.getAscent() + 6);
        }
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void paintChart(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final int size = this.tick[n5].size();
        String s;
        if (size > 0) {
            s = this.tickQ[n5].name.trim() + " " + this.sTime(this.tick[n5].elementAt(size - 1).time);
        }
        else {
            s = this.tickQ[n5].name.trim();
        }
        this.dBuf.drawString(s, this.oriX, this.bMet.getAscent() + 6 + n6);
        this.dBuf.setColor(this.cColor[7]);
        this.dBuf.fillRect(this.oriX, n - n3, this.ranX, n2 - n + n3 + 1);
        for (int i = 15; i <= this.t276; i += 60) {
            this.dBuf.setColor(this.cColor[8]);
            this.dBuf.fillRect(this.oriX + i * this.ranX / this.t276, n - n3, 1, n2 - n + n3 + 1);
            this.dBuf.setColor(this.cColor[16]);
            this.dBuf.drawString(i / 60 + 9 + "", this.oriX + i * this.ranX / this.t276 - 5, n2 + this.bMet.getAscent() + 5);
        }
        final int n7 = n2 + this.bMet.getHeight() + 5;
        this.dBuf.setColor(this.cColor[21]);
        this.dBuf.fillRect(0, n7, this.size().width, this.m_nRange - n7 + n6);
        int n8 = this.bMet.getAscent() + 5;
        final int n9 = this.oriX + this.ranX + 4;
        this.size();
        this.size();
        if (this.tick[n5].size() == 0) {
            return;
        }
        final int size2 = this.tick[n5].size();
        int tvolume = 0;
        int n10 = 0;
        int max = 0;
        for (int j = 0; j < size2; ++j) {
            final F5 f5 = this.tick[n5].elementAt(j);
            f5.Dvolume = f5.Tvolume - tvolume;
            tvolume = f5.Tvolume;
            max = Math.max(max, Math.abs(f5.price - this.tickQ[n5].preC));
        }
        final int n11 = max * 5 / 4;
        this.tickQ[n5].dnMin = this.tickQ[n5].preC - n11;
        this.tickQ[n5].upMax = this.tickQ[n5].preC + n11;
        int k = 0;
        while (k < size2) {
            final F5 f6 = this.tick[n5].elementAt(k);
            int dvolume = f6.Dvolume;
            final int n12 = f6.time / 60;
            ++k;
            while (k < size2) {
                final F5 f7 = this.tick[n5].elementAt(k);
                if (f7.time / 60 != n12) {
                    break;
                }
                dvolume += f7.Dvolume;
                ++k;
            }
            if (n10 < dvolume) {
                n10 = dvolume;
            }
        }
        if (n10 < 2) {
            n10 = 2;
        }
        this.dBuf.setColor(this.cColor[8]);
        final int n13 = n2 - n4 * 2 / 3;
        if (this.m_nType == 0) {
            this.dBuf.setColor(this.cColor[8]);
            this.dBuf.drawLine(this.oriX, n13, this.ranX + this.oriX, n13);
        }
        else {
            this.dBuf.setColor(this.cColor[22]);
            this.dBuf.drawLine(this.oriX + this.ranX, n13, this.ranX + this.oriX + 4, n13);
        }
        final String string = n10 + "";
        this.dBuf.setColor(this.cColor[6]);
        if (this.m_nType == 0) {
            this.dBuf.drawString(string + "", this.oriX - this.sMet.stringWidth(string) - 2, n13 + 2);
        }
        else {
            this.dBuf.drawString(string + "", this.oriX + this.ranX + 4, n13 + 2);
        }
        final int n14 = n2 - n4 * 2 / 6;
        if (this.m_nType == 0) {
            this.dBuf.setColor(this.cColor[8]);
            this.dBuf.drawLine(this.oriX, n14, this.ranX + this.oriX, n14);
        }
        else {
            this.dBuf.setColor(this.cColor[22]);
            this.dBuf.drawLine(this.oriX + this.ranX, n14, this.ranX + this.oriX + 4, n14);
        }
        final String string2 = n10 / 2 + "";
        this.dBuf.setColor(this.cColor[6]);
        if (this.m_nType == 0) {
            this.dBuf.drawString(string2, this.oriX - this.sMet.stringWidth(string2) - 2, n14 + 4);
        }
        else {
            this.dBuf.drawString(string2, this.oriX + this.ranX + 4, n14 + 4);
        }
        final int n15 = (int)(n10 * 1.5);
        final F5 f8 = this.tick[n5].elementAt(size2 - 1);
        this.dBuf.setColor(this.cColor[20]);
        final String string3 = f8.Tvolume + "\u53e3";
        this.dBuf.drawString(string3, this.size().width - this.bMet.stringWidth(string3) - 1, n6 + this.m_nRange - 5);
        if (f8.price > this.tickQ[n5].preC) {
            this.dBuf.setColor(this.cColor[2]);
        }
        else if (f8.price == this.tickQ[n5].preC) {
            this.dBuf.setColor(this.cColor[4]);
        }
        else {
            this.dBuf.setColor(this.cColor[3]);
        }
        String s2 = this.pFx((this.tickQ[n5].preC == 0) ? 0.0f : (Math.abs(f8.price - this.tickQ[n5].preC) * 100.0f / this.tickQ[n5].preC), 2) + "%" + " ";
        if (f8.price > this.tickQ[n5].preC) {
            s2 += "\u25b2";
        }
        else if (f8.price < this.tickQ[n5].preC) {
            s2 += "\u25bc";
        }
        final String id = this.tickQ[n5].id;
        String s3;
        if (this.m_nDot != null) {
            if (this.m_nMode == 0) {
                s3 = s2 + ((this.m_nDot[this.nActive] > 2) ? this.pFx(Math.abs(f8.price - this.tickQ[this.nActive].preC) / 1000.0f, this.m_nDot[this.nActive]) : this.pFx(Math.abs(f8.price - this.tickQ[this.nActive].preC) / 100.0f, 2));
            }
            else {
                s3 = s2 + ((this.m_nDot[n5] > 2) ? this.pFx(Math.abs(f8.price - this.tickQ[n5].preC) / 1000.0f, this.m_nDot[n5]) : this.pFx(Math.abs(f8.price - this.tickQ[n5].preC) / 100.0f, 2));
            }
        }
        else if (id.substring(0, 2).equalsIgnoreCase("GB") || id.substring(0, 2).equalsIgnoreCase("CP")) {
            s3 = s2 + this.pFx(Math.abs(f8.price - this.tickQ[n5].preC) / 1000.0f, 3);
        }
        else {
            s3 = s2 + this.pFx(Math.abs(f8.price - this.tickQ[n5].preC) / 100.0f, 2);
        }
        this.dBuf.drawString(s3, this.size().width - this.bMet.stringWidth(string3) - this.bMet.stringWidth(s3) - 10, this.m_nRange + n6 - 5);
        this.dPr(this.bMet, f8.price, this.size().width - this.bMet.stringWidth(string3 + s3 + "   "), this.m_nRange + n6 - 5, this.tickQ[n5], n5);
        this.dBuf.setFont(this.bFont);
        final int n16 = n9 + this.zws * 14 + 4 + this.zws * 6 + 4 + this.zws * 6 + 4;
        n8 += 80;
        this.dBuf.setFont(this.sFont);
        final int[] array = new int[2];
        final int[] array2 = new int[2];
        int price = (this.tickQ[n5].preC <= 0 || this.tickQ[n5].preC >= this.tickQ[n5].upMax) ? this.tick[n5].firstElement().price : this.tickQ[n5].preC;
        int upMax;
        if (this.tickQ[n5].upMax > 0) {
            upMax = this.tickQ[n5].upMax;
        }
        else {
            upMax = this.tickQ[n5].high + 100;
            price = this.tick[n5].firstElement().price;
        }
        int dnMin;
        if (this.tickQ[n5].dnMin > 0) {
            dnMin = this.tickQ[n5].dnMin;
        }
        else {
            dnMin = this.tickQ[n5].low - 100;
        }
        if (upMax == dnMin) {
            upMax = price + price * 2 / 100;
            dnMin = price - price * 2 / 100;
        }
        array2[0] = upMax - (array[0] = dnMin);
        array[1] = 0;
        array2[1] = 105;
        final int n17 = price / 200;
        final int n18 = n - n3;
        int n19 = (array2[0] == 0) ? n : (n - (price - array[0]) * n3 / array2[0]);
        this.dBuf.setColor(this.cColor[10]);
        this.dBuf.fillRect(this.oriX, n19, this.ranX, 1);
        this.dPr(this.sMet, price, this.oriX - 2, n19, this.tickQ[n5], n5);
        for (int l = price + n17; l < upMax; l += n17) {
            final int n20 = (array2[0] == 0) ? n : (n - (l - array[0]) * n3 / array2[0]);
            if (n20 <= n19 - 16 && n20 >= n18 + 16) {
                this.dBuf.setColor(this.cColor[8]);
                this.dBuf.fillRect(this.oriX, n20, this.ranX, 1);
                this.dPr(this.sMet, l, this.oriX - 2, n20, this.tickQ[n5], n5);
                n19 = n20;
            }
        }
        this.dBuf.setColor(this.cColor[8]);
        this.dBuf.fillRect(this.oriX, n18, this.ranX, 1);
        this.dPr(this.sMet, upMax, this.oriX - 2, n18, this.tickQ[n5], n5);
        int n21 = (array2[0] == 0) ? n : (n - (price - array[0]) * n3 / array2[0]);
        for (int n22 = price - n17; n22 > dnMin; n22 -= n17) {
            final int n23 = (array2[0] == 0) ? n : (n - (n22 - array[0]) * n3 / array2[0]);
            if (n23 >= n21 + 16 && n23 <= n - 16) {
                this.dBuf.setColor(this.cColor[8]);
                this.dBuf.fillRect(this.oriX, n23, this.ranX, 1);
                this.dPr(this.sMet, n22, this.oriX - 2, n23, this.tickQ[n5], n5);
                n21 = n23;
            }
        }
        this.dBuf.setColor(this.cColor[11]);
        this.dBuf.fillRect(this.oriX, n, this.ranX, 1);
        this.dPr(this.sMet, dnMin, this.oriX - 2, n, this.tickQ[n5], n5);
        int n25;
        int n24 = this.oriX + ((n25 = 525) - 525) * this.ranX / this.t276;
        int n26 = (array2[0] == 0) ? n : (n - (price - array[0]) * n3 / array2[0]);
        int n27 = 0;
        while (n27 < this.tick[n5].size()) {
            final F5 f9 = this.tick[n5].elementAt(n27);
            final int n28 = f9.time / 60;
            int dvolume2 = f9.Dvolume;
            final int price3;
            int n30;
            int n29;
            int price2 = n29 = (n30 = (price3 = f9.price));
            int baseDif;
            int n31 = baseDif = f9.baseDif;
            ++n27;
            while (n27 < this.tick[n5].size()) {
                final F5 f10 = this.tick[n5].elementAt(n27);
                if (f10.time / 60 != n28) {
                    break;
                }
                dvolume2 += f10.Dvolume;
                price2 = f10.price;
                if (price2 > n30) {
                    n30 = price2;
                }
                else if (price2 < n29) {
                    n29 = price2;
                }
                final int baseDif2 = f10.baseDif;
                if (baseDif2 > n31) {
                    n31 = baseDif2;
                }
                else if (baseDif2 < baseDif) {
                    baseDif = baseDif2;
                }
                ++n27;
            }
            final int n32 = this.oriX + (n28 - 525) * this.ranX / this.t276;
            final int n33 = (array2[0] == 0) ? n : (n - (dnMin + n31 * 2 - array[0]) * n3 / array2[0]);
            final int n34 = array2[0];
            final int n35 = (dnMin + baseDif * 2 - array[0]) * n3 / array2[0];
            this.dBuf.setColor(this.cColor[5]);
            final int n36 = (n15 == 0) ? n2 : (n2 - dvolume2 * n4 / n15);
            this.dBuf.drawLine(n32, n2, n32, n36);
            if (this.t276 > 186 && n25 == n28 - 1 && n32 == n24 + 2) {
                this.dBuf.drawLine(n24 + 1, n2, n24 + 1, n36);
            }
            this.dBuf.setColor(this.cColor[12]);
            this.dBuf.drawLine(n24, n26, n32, (array2[0] == 0) ? n : (n - (price3 - array[0]) * n3 / array2[0]));
            this.dBuf.drawLine(n32, (array2[0] == 0) ? n : (n - (n29 - array[0]) * n3 / array2[0]), n32, (array2[0] == 0) ? n : (n - (n30 - array[0]) * n3 / array2[0]));
            n26 = ((array2[0] == 0) ? n : (n - (price2 - array[0]) * n3 / array2[0]));
            n24 = n32;
            n25 = n28;
        }
    }
    
    public String sTime(final int n) {
        final String string = "" + (n / 3600 * 10000 + n / 60 % 60 * 100 + n % 60 + 1000000);
        return string.substring(1, 3) + ":" + string.substring(3, 5);
    }
    
    int getParaFStyle(final String s, final int n) {
        if (this.getParameter(s) != null) {
            return Integer.parseInt(this.parse(this.getParameter(s), ",")[1]);
        }
        return n;
    }
    
    protected boolean hasMenu() {
        return this.m_nMode == 0 && this.arr.size() > 1;
    }
    
    public int getShort() {
        final int n = ((this.m_arrData[this.m_nCount] & 0xFF) << 8) + (this.m_arrData[this.m_nCount + 1] & 0xFF);
        this.m_nCount += 2;
        return n;
    }
    
    protected Vector readASP(final String s) {
        final Vector vector = new Vector();
        try {
            final URLConnection openConnection = new URL(this.getCodeBase(), s).openConnection();
            openConnection.setUseCaches(false);
            final InputStream inputStream = openConnection.getInputStream();
            openConnection.getContentLength();
            final byte[] array = new byte[2048];
            int procData = 0;
            final byte[] array2 = new byte[1024];
            int read;
            while (-1 != (read = inputStream.read(array2))) {
                System.arraycopy(array2, 0, array, procData, read);
                procData = this.procData(array, procData + read, vector);
            }
            inputStream.close();
        }
        catch (Exception ex) {
            return null;
        }
        return vector;
    }
    
    protected void getFutureData(final int n) {
        final Name name = this.arr.elementAt(n);
        this.tickQ[n].name = name.name;
        this.tickQ[n].id = name.id;
        final Vector asp = this.readASP("/z/twidx/zyzjaka.djbcd?A=" + this.tickQ[n].id);
        if (asp != null) {
            this.FillData(asp, this.tickQ[n], this.tick[n]);
            this.m_bInit = true;
        }
    }
    
    void getParaC(final Color[] array, final int n) {
        if (this.getParameter("COLOR" + n) != null) {
            array[n] = new Color(Integer.parseInt(this.getParameter("COLOR" + n), 16));
        }
    }
    
    Color[] parseC(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final Color[] array = new Color[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = new Color(Integer.parseInt(stringTokenizer.nextToken(), 16));
        }
        return array;
    }
    
    public void paint(final Graphics graphics) {
        if (this.offScr == null) {
            this.calcDispRect();
            this.offScr = this.createImage(this.size().width, this.size().height);
            (this.dBuf = this.offScr.getGraphics()).setColor(this.cColor[0]);
            this.dBuf.fillRect(0, 0, this.size().width, this.size().height);
            this.dBuf.setColor(this.cColor[1]);
            this.dBuf.drawString("\u8cc7\u6599\u8b80\u53d6\u4e2d...", 0, this.bMet.getAscent() + 3);
            final int stringWidth = this.bMet.stringWidth("\u5609\u5be6");
            final int stringWidth2 = this.bMet.stringWidth("\u5546\u54c1");
            final int n = stringWidth * 10 / 9;
            if (this.m_nOption == 1) {
                this.m_rcLogo = new Rectangle(this.size().width - n - 2, 5, n, this.bMet.getHeight() + 2);
                this.m_rcMenu = new Rectangle(this.size().width - stringWidth2 - this.m_rcLogo.width - 4, 5, stringWidth2, this.bMet.getHeight() + 2);
            }
            else {
                this.m_rcMenu = new Rectangle(this.size().width - stringWidth2 - 2, 5, stringWidth2, this.bMet.getHeight() + 2);
            }
        }
        graphics.drawImage(this.offScr, 0, 0, this);
    }
    
    String repLnk(final String s, final String s2, final String s3) {
        final int length = s2.length();
        if (length < 1) {
            return s;
        }
        final int index = s.indexOf(s2);
        String string;
        if (index > 0) {
            string = s.substring(0, index) + s3 + s.substring(index + length);
        }
        else {
            string = s;
        }
        return string;
    }
    
    public int getInt() {
        final int n = 4;
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            n2 += (this.m_arrData[this.m_nCount + i] & 0xFF) << 8 * (n - 1 - i);
        }
        this.m_nCount += n;
        return n2;
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
    
    public rtfutidx() {
        this.runner = null;
        this.t276 = 306;
        this.arr = new Vector();
        this.oriY = new int[2];
        this.ranY = new int[2];
        this.ttime = new int[2];
        this.tid = "";
        this.m_bInit = false;
        this.m_nFDelayT = 30;
        this.m_menu = new PopupMenu();
    }
    
    protected void initMenu() {
        if (!this.hasMenu()) {
            return;
        }
        for (int i = 0; i < this.arr.size(); ++i) {
            this.m_menu.add(((Name)this.arr.elementAt(i)).name);
        }
        this.add(this.m_menu);
    }
    
    int getParaI(final String s, final int n) {
        if (this.getParameter(s) != null) {
            return Integer.parseInt(this.getParameter(s));
        }
        return n;
    }
    
    public int vo2po(final int n) {
        if (n <= 15) {
            return n;
        }
        if (n <= 50) {
            return 15 + (n - 15) * 15 / 35;
        }
        if (n <= 100) {
            return 30 + (n - 50) * 15 / 50;
        }
        if (n <= 500) {
            return 45 + (n - 100) * 15 / 400;
        }
        if (n <= 1000) {
            return 60 + (n - 500) * 15 / 500;
        }
        if (n <= 3000) {
            return 75 + (n - 1000) * 15 / 2000;
        }
        if (n <= 8000) {
            return 90 + (n - 3000) * 15 / 5000;
        }
        return 105 + (n - 8000) / 1000;
    }
    
    int getParaFSize(final String s, final int n) {
        if (this.getParameter(s) != null) {
            return Integer.parseInt(this.parse(this.getParameter(s), ",")[0]);
        }
        return n;
    }
    
    protected void initTick(final int n) {
        System.gc();
        this.tickQ[n] = new F3_Quote();
        this.tick[n] = new Vector(400, 100);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.offScr == null) {
            return false;
        }
        if (this.m_nOption == 1 && this.m_rcLogo.contains(n, n2)) {
            final String s = "http://www.moneydj.com";
            try {
                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), s), "_blank");
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            return true;
        }
        if (this.hasMenu() && this.m_rcMenu != null && this.m_rcMenu.contains(n, n2)) {
            this.m_menu.show(this, this.m_rcMenu.x, this.m_rcMenu.height + this.m_rcMenu.y + 1);
        }
        return true;
    }
    
    public int inData() {
        if (this.offScr == null) {
            return 100;
        }
        if (this.waitT > 0) {
            --this.waitT;
            this.repaint();
            return 1000;
        }
        if (!this.m_bInit || this.isTTime()) {
            if (this.m_nMode == 0) {
                this.getFutureData(this.nActive);
            }
            else {
                for (int i = 0; i < this.arr.size(); ++i) {
                    this.getFutureData(i);
                }
            }
        }
        this.waitT = this.m_nFDelayT;
        this.bpaint();
        this.repaint();
        return 1000;
    }
    
    protected void FillData(final Vector vector, final F3_Quote f3_Quote, final Vector vector2) {
        vector2.removeAllElements();
        for (int i = 0; i < vector.size(); ++i) {
            final String s = vector.elementAt(i);
            if (s != null && s.length() != 0) {
                final String[] parse = this.parse(s, ",");
                if (i == 0) {
                    if (parse.length < 3) {
                        f3_Quote.preC = 0;
                        f3_Quote.SettlementFlag = ' ';
                    }
                    else {
                        f3_Quote.preC = Integer.parseInt(parse[1]);
                        f3_Quote.SettlementFlag = (char)((parse[2].charAt(0) == '1') ? 83 : 32);
                    }
                }
                else {
                    final int int1 = Integer.parseInt(parse[0]);
                    if (int1 != 0) {
                        vector2.addElement(new F5(int1 / 100 * 3600 + int1 % 100 * 60, 0, 0, Integer.parseInt(parse[1]), Integer.parseInt(parse[2]), 0));
                    }
                }
            }
        }
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof MenuItem) {
            int i = 0;
            while (i < this.arr.size()) {
                if (((Name)this.arr.elementAt(i)).name.compareTo(o.toString()) == 0) {
                    if (this.nActive != i) {
                        this.getFutureData(this.nActive = i);
                        this.calcDispRect();
                        this.bpaint();
                        this.repaint();
                        break;
                    }
                    break;
                }
                else {
                    ++i;
                }
            }
        }
        return true;
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
        if (this.getParaI("COLORSET", 0) == 0) {
            this.cColor = this.parseC("e1ebf4,0,ff0000,8800,0,ff,0168b3,ffffff,dddddd,fffc,8dcef4,8800,ff0000,0,ff,fe00,ff0000,e1ebf4,0,ffffff,000000,8dcef4,0", ",");
        }
        else {
            this.cColor = this.parseC("0,ffffff,ff0000,00ff00,ffffff,ff0000,ffff,0,61a6,ffff,8dcef4,8a00,ffff00,ffffff,ffff00,8800,ffff00,ffff00,0,ffffff,ffffff,00309c,ffffff", ",");
        }
        for (int i = 0; i < this.cColor.length; ++i) {
            this.getParaC(this.cColor, i);
        }
        this.bFont = new Font("\u7d30\u660e\u9ad4", this.getParaFStyle("FONTSTYLE", 0), this.getParaFSize("FONTSTYLE", 12));
        this.bMet = this.getFontMetrics(this.bFont);
        this.sFont = new Font("\u7d30\u660e\u9ad4", this.getParaFStyle("FONTSTYLE", 0), this.getParaFSize("SFONTSTYLE", 12));
        this.sMet = this.getFontMetrics(this.sFont);
        this.m_nVPer = this.getParaI("VPercent", 30);
        this.m_nMode = this.getParaI("MODE", 0);
        this.m_nType = this.getParaI("TYPE", 1);
        this.m_nOption = this.getParaI("OPTION", 1);
        this.m_nFDelayT = this.getParaI("delayT", 30);
        String parameter = this.getParameter("TTime");
        if (parameter == null || parameter == "") {
            parameter = "0800-1430";
        }
        this.getTTime(parameter, "-");
        String parameter2 = this.getParameter("ID");
        if (parameter2 == null) {
            parameter2 = "";
        }
        this.getIdSetting(parameter2);
        this.initMenu();
        this.getServTime();
        this.waitT = 0;
    }
    
    void getTTime(final String s, final String s2) {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
            if (stringTokenizer.countTokens() < 2) {
                throw new Exception();
            }
            final int int1 = Integer.parseInt(stringTokenizer.nextToken());
            this.ttime[0] = int1 / 100 * 60 + int1 % 100;
            final int int2 = Integer.parseInt(stringTokenizer.nextToken());
            this.ttime[1] = int2 / 100 * 60 + int2 % 100;
        }
        catch (Exception ex) {
            this.ttime[0] = 480;
            this.ttime[1] = 930;
        }
    }
}
