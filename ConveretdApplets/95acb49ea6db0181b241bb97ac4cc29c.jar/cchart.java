import java.awt.Event;
import java.awt.image.ImageObserver;
import java.util.Enumeration;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.util.Vector;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class cchart extends Panel
{
    double iNan;
    private Vector v;
    private long lDec;
    private int cBack;
    private int w;
    private int ox;
    private int h;
    private int oy;
    private int iP;
    private double iMin;
    private double iMax;
    private double iY;
    private int bgWidth;
    private int bgHeight;
    private double fPas;
    private int iCol;
    private String sBullet;
    private int iBx;
    private int iBy;
    private boolean sMax;
    private int xC;
    private int yC;
    private int iM;
    private int iNum;
    private int iPres;
    private int iAf;
    private Color[] cC;
    private String[] sT;
    private String sTitle;
    private boolean bInter;
    private int iCur;
    private int iScroll;
    private int iVal;
    private Font wFont;
    private FontMetrics wMetrics;
    private Image bgimg;
    private Image buffer;
    private Graphics offScreen;
    
    private void paintBar(final Graphics graphics, final int n) {
        double n2 = 1.0;
        this.ox = 40;
        this.oy = this.h - 40 + (int)(this.iMin * this.fPas / 100.0);
        if (this.iVal + this.iCur >= this.iNum) {
            this.iScroll = 0;
        }
        else {
            this.iScroll = 1;
        }
        if (this.iP < 4) {
            if (this.iVal > 200) {
                this.iM = 25;
            }
            else {
                this.iM = 10;
            }
        }
        else {
            this.iM = 1;
        }
        while (n2 < (this.iMax - this.iY) / 10.0) {
            n2 *= 10.0;
        }
        if ((this.iMax - this.iY) / n2 < 4.0) {
            n2 /= 2.0;
        }
        double n3 = this.oy;
        double n4 = 0.0;
        graphics.setFont(this.wFont);
        graphics.setColor(new Color(0));
        graphics.drawLine(this.ox, 20, this.ox, this.oy + 5);
        graphics.drawLine(this.ox - 5, this.oy, n, this.oy);
        while (n3 > 15.0) {
            graphics.setColor(new Color(0));
            graphics.drawString(" " + this.d2s(n4 + this.iY), 5, (int)(n3 + 2.0));
            graphics.setColor(new Color(10526880));
            graphics.drawLine(this.ox - 2, (int)n3, n, (int)n3);
            n3 -= n2 * this.fPas / 100.0;
            n4 += n2;
        }
        int n5 = 0;
        final Enumeration<Object> elements = (Enumeration<Object>)this.v.elements();
        while (elements.hasMoreElements()) {
            if (n5 >= this.iCur && n5 < this.iCur + this.iVal) {
                this.drawBar(graphics, n5, elements.nextElement());
            }
            else {
                elements.nextElement();
            }
            ++n5;
        }
        if (this.iScroll + this.iCur > 0) {
            graphics.setColor(new Color(12632256));
        }
        if (this.iScroll == 1) {
            graphics.fill3DRect(n - 14, this.h - 12, 14, 12, true);
            final int[] array = { n - 9, n - 9, n - 3 };
            final int[] array2 = { this.h - 9, this.h - 3, this.h - 6 };
            graphics.setColor(new Color(0));
            graphics.fillPolygon(array, array2, 3);
            graphics.setColor(new Color(12632256));
        }
        if (this.iCur > 0) {
            graphics.fill3DRect(40, this.h - 12, 14, 12, true);
            final int[] array3 = { 50, 50, 44 };
            final int[] array4 = { this.h - 9, this.h - 3, this.h - 6 };
            graphics.setColor(new Color(0));
            graphics.fillPolygon(array3, array4, 3);
            if (this.iScroll == 1) {
                this.iScroll = 2;
                return;
            }
            this.iScroll = 3;
        }
    }
    
    public void setBkImage(final Image bgimg) {
        this.bgimg = bgimg;
    }
    
    public void setCol(final int iCol) {
        if (this.iCol > 0) {
            this.iCol = iCol;
        }
        else {
            this.iCol = 1;
        }
        this.cC = new Color[this.iCol];
        this.sT = new String[this.iCol];
    }
    
    public int getCol() {
        return this.iCol;
    }
    
    public void setCel(final int n, final int n2, final double n3) {
        if (n < this.iCol && n2 < this.iNum) {
            this.v.elementAt(n2).iD[n] = n3;
        }
        if (n3 > this.iMax) {
            this.iMax = n3;
        }
        if (n3 < this.iMin) {
            this.iMin = n3;
        }
    }
    
    public void paint(final Graphics graphics) {
        this.wMetrics = this.getFontMetrics(this.wFont);
        if (this.offScreen == null) {
            this.w = this.size().width;
            this.h = this.size().height;
            try {
                this.buffer = this.createImage(this.w, this.h);
                this.offScreen = this.buffer.getGraphics();
            }
            catch (Exception ex) {
                this.offScreen = null;
            }
        }
        this.update(graphics);
    }
    
    private void drawArc(final Graphics graphics, int height, final Object o) {
        final rq rq = (rq)o;
        double n = 0.0;
        final int n2 = this.h / this.yC;
        final int n3 = height / n2;
        final int n4 = height % n2;
        for (int i = 0; i < this.iCol; ++i) {
            if (rq.iD[i] != this.iNan) {
                n += rq.iD[i];
            }
        }
        graphics.setColor(new Color(0));
        graphics.setFont(new Font("Helvetica", 0, 14));
        graphics.drawString(rq.sIt, 10 + n3 * this.yC, 12 + n4 * this.yC);
        graphics.setColor(new Color(11579568));
        graphics.draw3DRect(this.yC * n3, this.yC * n4, this.yC - 1, this.yC - 1, true);
        graphics.setFont(this.wFont);
        int n5 = 0;
        height = this.wMetrics.getHeight();
        graphics.setColor(this.cC[this.iCol - 1]);
        graphics.fillOval(this.yC * (1 + n3 * 6) / 6, this.yC * (1 + n4 * 6) / 6, this.yC * 2 / 3, this.yC * 2 / 3);
        for (int j = 0; j < this.iCol; ++j) {
            final int n6 = (int)(rq.iD[j] * 360.0 / n);
            graphics.setColor(this.cC[j]);
            graphics.fillArc(this.yC * (1 + n3 * 6) / 6, this.yC * (1 + n4 * 6) / 6, this.yC * 2 / 3, this.yC * 2 / 3, n5, n6);
            int n7 = (int)(this.yC * Math.cos((n5 + n6 / 2) * 3.141592653589793 / 180.0) / 3.0);
            int n8 = (int)(this.yC * Math.sin((n5 + n6 / 2) * 3.141592653589793 / 180.0) / 3.0);
            if ((this.iAf & 0x1) != 0x1 && n6 * 10 / 36 > 0) {
                if (n7 < 0) {
                    n7 -= this.wMetrics.stringWidth("" + n6 * 10 / 36 + "%");
                }
                if (n8 < 0) {
                    n8 -= height;
                }
                graphics.setColor(new Color(16777215));
                final int n9 = this.wMetrics.stringWidth("" + n6 * 10 / 36 + "%") + 5;
                int n10 = this.yC * n3 + this.yC / 2 + n7;
                final int n11 = this.yC * n4 + this.yC / 2 - n8;
                if (this.bInter) {
                    if (n10 > this.yC * (1 + n3 * 6) / 6) {
                        n10 -= n9;
                    }
                    else {
                        n10 += n9;
                    }
                }
                graphics.fillRect(n10 - 1, n11 - height + 3, n9 - 5, height - 2);
                graphics.setColor(new Color(0));
                graphics.drawString("" + n6 * 10 / 36 + "%", n10, n11);
            }
            n5 += n6;
        }
    }
    
    public void setPress(final int iPres, final int iAf) {
        this.iPres = iPres;
        this.iAf = iAf;
        this.bInter = (iAf > 9);
        if (this.bInter) {
            this.iAf -= 10;
        }
        this.iMax = 0.0;
        this.iMin = 0.0;
        this.iY = -9925.0;
        this.sMax = false;
        final Enumeration<rq> elements = this.v.elements();
        while (elements.hasMoreElements()) {
            this.setMax(elements.nextElement());
        }
        if (this.iMin == this.iMax) {
            this.iMax = this.iMin + 1.0;
        }
    }
    
    private void paintTable(final Graphics graphics) {
        final int[] array = new int[this.iCol + 1];
        graphics.setFont(new Font("Helvetica", 1, 12));
        final FontMetrics fontMetrics = this.getFontMetrics(graphics.getFont());
        final int n = fontMetrics.getHeight() + 4;
        if ((this.iNum - this.iCur + 1) * n > this.h - 5) {
            this.iScroll = 1;
        }
        else {
            this.iScroll = 0;
        }
        array[0] = 10;
        for (int i = 0; i < this.iNum; ++i) {
            final int n2 = fontMetrics.stringWidth(this.v.elementAt(i).sIt) * 14 / 10;
            if (n2 > array[0]) {
                array[0] = n2;
            }
        }
        int n3 = array[0];
        for (int j = 0; j < this.iCol; ++j) {
            array[j + 1] = fontMetrics.stringWidth(this.sT[j]) * 14 / 10;
            graphics.setColor(new Color(13684944));
            graphics.fillRect(10 + n3, 5, array[j + 1], n);
            graphics.setColor(this.cC[j]);
            graphics.draw3DRect(10 + n3, 5, array[j + 1], n, true);
            graphics.draw3DRect(11 + n3, 6, array[j + 1] - 2, n - 2, true);
            graphics.setColor(new Color(0));
            graphics.drawString(this.sT[j], 10 + n3 + array[j + 1] / 5, 2 + n);
            n3 += array[j + 1];
        }
        this.yC = n3 + 10 + 2;
        graphics.setColor(new Color(16777215));
        graphics.fillRect(10 + array[0], 5 + n, n3 - array[0], n * (this.iNum - this.iCur));
        int n4 = 0;
        final Enumeration<rq> elements = (Enumeration<rq>)this.v.elements();
        while (elements.hasMoreElements()) {
            final rq rq = elements.nextElement();
            if (n4 >= this.iCur) {
                graphics.setColor(new Color(13684944));
                graphics.fill3DRect(10, 5 + n * (n4 + 1 - this.iCur), array[0], n, true);
                graphics.draw3DRect(11, 6 + n * (n4 + 1 - this.iCur), array[0] - 2, n - 2, true);
                graphics.setColor(new Color(0));
                graphics.drawString(rq.sIt, 10 + array[0] / 5, 2 + n * (n4 + 2 - this.iCur));
                int n5 = array[0];
                for (int k = 0; k < this.iCol; ++k) {
                    final int stringWidth = fontMetrics.stringWidth("" + this.d2s(rq.iD[k]));
                    graphics.setColor(new Color(0));
                    graphics.draw3DRect(10 + n5, 5 + n * (n4 + 1 - this.iCur), array[k + 1], n, true);
                    if (rq.iD[k] != this.iNan) {
                        graphics.drawString("" + this.d2s(rq.iD[k]), n5 + array[k + 1] - stringWidth, 2 + n * (n4 + 2 - this.iCur));
                    }
                    n5 += array[k + 1];
                }
            }
            ++n4;
        }
        if (this.iScroll + this.iCur > 0) {
            graphics.setColor(new Color(12632256));
        }
        if (this.iScroll == 1) {
            graphics.fill3DRect(this.yC, this.h - 12, 14, 12, true);
            graphics.setColor(new Color(0));
            graphics.fillPolygon(new int[] { this.yC + 4, this.yC + 10, this.yC + 7 }, new int[] { this.h - 9, this.h - 9, this.h - 4 }, 3);
            graphics.setColor(new Color(12632256));
        }
        if (this.iCur > 0) {
            graphics.fill3DRect(this.yC, 20, 14, 12, true);
            final int[] array2 = { this.yC + 7, this.yC + 3, this.yC + 10 };
            final int[] array3 = { 24, 29, 29 };
            graphics.setColor(new Color(0));
            graphics.fillPolygon(array2, array3, 3);
            if (this.iScroll == 1) {
                this.iScroll = 2;
                return;
            }
            this.iScroll = 3;
        }
    }
    
    private String isIn(final int n, final int n2, final int n3, final rq rq) {
        int oy = this.oy;
        int oy2 = this.oy;
        final int n4 = this.iP / this.iCol;
        int n5 = this.ox + (n3 - this.iCur) * 2 * this.iP;
        String s = null;
        if (n > n5 + this.iP + this.iP || n < n5) {
            return null;
        }
        for (int i = 1; i <= this.iCol; ++i) {
            if (this.iPres == 0) {
                n5 += i * n4 - n4;
            }
            int n6;
            int n7;
            if (rq.iD[this.iCol - i] > 0.0) {
                n6 = (int)(oy2 - (rq.iD[this.iCol - i] - this.iY) * this.fPas / 100.0);
                n7 = oy2;
                if (this.iPres == 3) {
                    oy2 = n6;
                }
            }
            else {
                n6 = oy;
                n7 = n6 + (int)(-rq.iD[this.iCol - i] * this.fPas / 100.0);
                if (this.iPres == 3) {
                    oy = n7;
                }
            }
            if (n > n5 && n < n5 + this.iP && n2 > n6 && n2 < n7) {
                s = new String("" + this.d2s(rq.iD[this.iCol - i]));
            }
        }
        return s;
    }
    
    public void clearData() {
        this.iCol = 1;
        this.iMax = 0.0;
        this.iMin = 0.0;
        this.iNum = 0;
        this.iCur = 0;
        this.iVal = -1;
        this.sTitle = new String("");
        this.v.removeAllElements();
    }
    
    public void addRow(final double[] array, final String s) {
        final rq max = new rq(this.iCol);
        max.sIt = new String(s);
        for (int i = 0; i < this.iCol; ++i) {
            max.iD[i] = array[i];
        }
        this.v.addElement(max);
        ++this.iNum;
        this.setMax(max);
    }
    
    public void setDec(final int n) {
        this.lDec = 1L;
        for (int i = 1; i < n; ++i) {
            this.lDec *= 10L;
        }
    }
    
    public void setFont(final String s, final int n, final int n2) {
        this.wFont = new Font(s, n, n2);
    }
    
    public synchronized void update(final Graphics graphics) {
        if (this.offScreen != null) {
            this.paintCanvas(this.offScreen);
            graphics.drawImage(this.buffer, 0, 0, this);
            return;
        }
        this.paintCanvas(graphics);
    }
    
    public void setBkColor(final int cBack) {
        this.cBack = cBack;
    }
    
    private String d2s(final double n) {
        double n2 = Math.round(n * this.lDec);
        final int n3 = ("" + this.lDec).length() - 1;
        if (this.lDec > 1L) {
            n2 = (n2 * 10.0 + 1.0) / (this.lDec * 10L);
        }
        final String string = "" + n2;
        String s;
        if (this.lDec > 1L) {
            s = new String(string.substring(0, string.indexOf(46) + n3 + 1));
        }
        else {
            s = new String(string.substring(0, string.indexOf(46)));
        }
        return s;
    }
    
    public void setMinMax(final double iy, final double iMax) {
        if (iMax != this.iNan) {
            this.iMax = iMax;
        }
        if (iy != this.iNan) {
            this.iY = iy;
        }
        this.sMax = true;
    }
    
    private void setMax(final rq rq) {
        double iMin = 0.0;
        double n = 0.0;
        double iy = 0.0;
        for (int i = 0; i < this.iCol; ++i) {
            if (rq.iD[i] != this.iNan) {
                if (this.iPres == 3 || this.iPres == 6) {
                    if (rq.iD[i] > 0.0) {
                        n += rq.iD[i];
                    }
                    else {
                        iMin += rq.iD[i];
                    }
                }
                else {
                    if (rq.iD[i] > n) {
                        n = rq.iD[i];
                    }
                    if (rq.iD[i] < iMin) {
                        iMin = rq.iD[i];
                    }
                }
                if (rq.iD[i] > 0.0 && (rq.iD[i] < iy || iy == 0.0)) {
                    iy = rq.iD[i];
                }
            }
        }
        if (n > this.iMax) {
            this.iMax = n * 1.1;
        }
        if (iMin < this.iMin) {
            this.iMin = iMin;
        }
        if (this.iY == -9925.0 || this.iY > iy) {
            this.iY = iy;
        }
    }
    
    public void setTitle(final String s) {
        if (s != null) {
            this.sTitle = new String(s);
        }
    }
    
    public cchart() {
        this.iNan = -1.254E-4;
        this.v = new Vector(10, 5);
        this.lDec = 100L;
        this.iY = -9925.0;
        this.bgWidth = -1;
        this.bgHeight = -1;
        this.fPas = 1.0;
        this.iCol = 1;
        this.iBx = -1;
        this.iBy = -1;
        this.iCur = -1;
        this.iVal = -1;
    }
    
    public boolean addCol(final int n, final int n2, final String s) {
        if (n >= this.iCol) {
            return false;
        }
        this.cC[n] = new Color(n2);
        this.sT[n] = new String(s);
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.iPres == 4) {
            if (this.iScroll > 0 && n > this.yC && n < this.yC + 15) {
                if (this.iScroll > 1) {
                    if (n2 > 20) {
                        if (n2 < 25) {
                            --this.iCur;
                        }
                        else if (2 * n2 < this.h) {
                            this.iCur -= 5;
                        }
                    }
                    if (this.iCur < 0) {
                        this.iCur = 0;
                    }
                }
                if (this.iScroll < 3) {
                    if (n2 > this.h - 13) {
                        ++this.iCur;
                    }
                    else if (2 * n2 >= this.h) {
                        this.iCur += 5;
                    }
                }
                this.repaint();
            }
        }
        else if (this.iPres != 2) {
            int w;
            if (this.iCol > 1 && this.iAf < 2) {
                w = this.w * 2 / 3;
            }
            else {
                w = this.w;
            }
            if (this.iScroll > 0 && n2 > this.h - 13) {
                if (this.iCur > 0) {
                    if (n > 40 && n < (w - 40) / 2) {
                        if (n < 55) {
                            --this.iCur;
                        }
                        else if (n < (w - 40) / 2) {
                            this.iCur -= this.iVal / 2;
                        }
                    }
                    if (this.iCur < 0) {
                        this.iCur = 0;
                    }
                }
                if (this.iScroll < 3 && n < w) {
                    if (n > w - 14) {
                        ++this.iCur;
                    }
                    else if (n > (w - 40) / 2) {
                        this.iCur += this.iVal / 2;
                    }
                }
                this.repaint();
            }
        }
        return true;
    }
    
    public Image getImage() {
        return this.buffer;
    }
    
    private void drawBar(final Graphics graphics, final int n, final Object o) {
        final rq rq = (rq)o;
        int n2 = 0;
        final int[] array = new int[4];
        final int[] array2 = new int[4];
        final int height = this.wMetrics.getHeight();
        int oy = this.oy;
        int oy2 = this.oy;
        int oy3 = this.oy;
        final int n3 = this.iP / this.iCol;
        for (int i = 1; i <= this.iCol; ++i) {
            int n4 = this.ox + (n - this.iCur) * 2 * this.iP;
            if (this.iPres == 0) {
                n4 += i * n3 - n3;
            }
            int n5;
            int n6;
            int n7;
            if (rq.iD[this.iCol - i] > 0.0) {
                n5 = (int)(oy2 - (rq.iD[this.iCol - i] - this.iY) * this.fPas / 100.0);
                n6 = oy2;
                n7 = n5;
                if (this.iPres == 3 || this.iPres == 6) {
                    oy2 = n5;
                }
            }
            else {
                n5 = oy;
                n6 = (n7 = n5 + (int)(-rq.iD[this.iCol - i] * this.fPas / 100.0));
                if (this.iPres == 3 || this.iPres == 6) {
                    oy = n6;
                }
            }
            if (n - this.iCur > 0) {
                n2 = (int)(oy3 - (this.v.elementAt(n - 1).iD[this.iCol - i] - this.iY) * this.fPas / 100.0);
            }
            if (rq.iD[this.iCol - i] != this.iNan) {
                if ((this.iAf & 0x1) != 0x1) {
                    final int stringWidth = this.wMetrics.stringWidth("" + rq.iD[this.iCol - i]);
                    int n8;
                    if (this.iPres == 1) {
                        n8 = this.ox + (n - this.iCur) * 2 * this.iP;
                    }
                    else {
                        n8 = this.ox + (n - this.iCur) * 2 * this.iP + this.iP;
                    }
                    graphics.setColor(new Color(16777215));
                    graphics.fill3DRect(n8, n7, stringWidth + 4, height - 2, true);
                    graphics.setColor(new Color(0));
                    graphics.drawString(this.d2s(rq.iD[this.iCol - i]), n8, n7 + height - 3);
                    graphics.setColor(this.cC[this.iCol - i]);
                    graphics.draw3DRect(n8, n7, stringWidth + 4, height - 2, true);
                }
                graphics.setColor(this.cC[this.iCol - i]);
                if (this.iPres == 0 || this.iPres == 3) {
                    graphics.fill3DRect(n4, n5, this.iP, n6 - n5, true);
                }
                else if (this.iPres == 1) {
                    if (n - this.iCur > 0) {
                        final int n9 = (int)(this.oy - (this.v.elementAt(n - 1).iD[this.iCol - i] - this.iY) * this.fPas / 100.0);
                        final int n10 = (int)(this.oy - (rq.iD[this.iCol - i] - this.iY) * this.fPas / 100.0);
                        graphics.drawLine(n4, n10, n4 - this.iP * 2, n9);
                        if (this.iP > 4) {
                            graphics.drawLine(n4 + 1, n10, 1 + n4 - this.iP * 2, n9);
                            if (!this.bInter) {
                                graphics.fillRect(n4 - 3, n10 - 3, 6, 6);
                            }
                        }
                        if ((1 + n) % this.iM == 0) {
                            graphics.setColor(new Color(13684944));
                            graphics.drawLine(n4, n10, n4, n6);
                        }
                    }
                }
                else if (this.iPres > 4 && n - this.iCur > 0) {
                    array[0] = (array[1] = n4);
                    array[2] = (array[3] = n4 - this.iP * 2);
                    array2[0] = n6;
                    array2[1] = n5;
                    if (n2 > this.oy) {
                        array2[2] = oy3;
                        array2[3] = n2;
                    }
                    else {
                        array2[2] = n2;
                        array2[3] = oy3;
                    }
                    if (this.iPres == 6) {
                        oy3 = n2;
                    }
                    graphics.fillPolygon(array, array2, 4);
                }
            }
        }
        if ((1 + n) % this.iM == 0) {
            graphics.setColor(new Color(0));
            graphics.drawString(rq.sIt, this.ox - 5 + (n - this.iCur) * 2 * this.iP, this.oy + height * (1 + (n - this.iCur) % 2));
        }
    }
    
    private void paintCanvas(final Graphics graphics) {
        if (this.iVal < 2) {
            this.iVal = this.iNum;
        }
        int w;
        if (this.iCol > 1 && this.iAf < 2) {
            w = this.w * 2 / 3;
        }
        else {
            w = this.w;
        }
        graphics.setColor(new Color(this.cBack));
        graphics.fillRect(0, 0, this.w, this.h);
        this.ox = 0;
        this.oy = 0;
        if (this.bgimg != null) {
            if (this.bgWidth == -1 || this.bgHeight == -1) {
                this.bgWidth = this.bgimg.getWidth(this);
                this.bgHeight = this.bgimg.getHeight(this);
            }
            if (this.bgWidth != -1 && this.bgHeight != -1) {
                while (this.oy < this.h) {
                    this.ox = 0;
                    while (this.ox < this.w) {
                        graphics.drawImage(this.bgimg, this.ox, this.oy, this);
                        this.ox += this.bgWidth;
                    }
                    this.oy += this.bgHeight;
                }
            }
        }
        if (!this.sMax) {
            if (this.iY < this.iMax - this.iY) {
                this.iY = 0.0;
            }
            else {
                final int n = (int)(this.iMax - this.iY);
                if (n > 100) {
                    this.iY = (int)(this.iY / 100.0) * 100;
                }
                else if (n > 10) {
                    this.iY = (int)(this.iY / 10.0) * 10;
                }
                else if (n > 2) {
                    this.iY = (int)(this.iY - 1.0);
                }
                else if (this.iMax - this.iY > 0.1) {
                    this.iY = (int)(this.iY * 10.0) / 10;
                }
            }
        }
        if (this.iVal == 0) {
            this.iVal = 1;
            this.iMax = 10.0;
        }
        this.fPas = this.h * 8 * 10 / (-this.iY + this.iMax - this.iMin);
        this.ox = 40;
        this.oy = this.h - 40 + (int)(this.iMin * this.fPas / 100.0);
        this.iP = (w - this.ox) / (2 * this.iVal);
        if (this.iP < 1) {
            this.iP = 1;
        }
        if (this.iP < 2 && this.iPres != 1 && this.iPres < 5) {
            this.iP = 2;
        }
        graphics.setFont(new Font("Helvetica", 1, 14));
        graphics.setColor(new Color(0));
        graphics.drawString(this.sTitle, (this.w - this.getFontMetrics(graphics.getFont()).stringWidth(this.sTitle)) / 2, 15);
        if (this.iCol > 1 && this.iPres != 4 && this.iAf < 2) {
            int i = 0;
            graphics.setFont(new Font("Helvetica", 1, 14));
            while (i < this.iCol) {
                graphics.setColor(this.cC[i]);
                graphics.fill3DRect(15 + w, 30 + 30 * i, 15, 15, true);
                graphics.setColor(new Color(0));
                graphics.drawString(this.sT[i], 35 + w, 42 + 30 * i);
                ++i;
            }
        }
        if (this.iPres == 2) {
            this.paintPie(graphics, w);
        }
        else if (this.iPres == 4) {
            this.paintTable(graphics);
        }
        else {
            this.paintBar(graphics, w);
        }
        if (this.iBx > -1 && this.iBy > -1 && this.sBullet != null) {
            final int n2 = 6 + this.wMetrics.stringWidth(this.sBullet);
            final int n3 = 3 + this.wMetrics.getHeight();
            graphics.setColor(new Color(16777215));
            graphics.fillRect(this.iBx - n2, this.iBy - 20, n2, n3);
            graphics.setColor(new Color(0));
            graphics.drawRect(this.iBx - n2, this.iBy - 20, n2, n3);
            graphics.drawString(this.sBullet, this.iBx - n2 + 3, this.iBy - n3 + 5);
        }
    }
    
    public void setNan(final double iNan) {
        this.iNan = iNan;
    }
    
    private void paintPie(final Graphics graphics, final int n) {
        this.yC = (int)Math.sqrt(this.h * n / this.iNum);
        int n2 = this.h / this.yC;
        int n3 = n / this.yC;
        if (n3 == 0) {
            n3 = 1;
        }
        for (int i = n2 * n3; i < this.iNum; i = n2 * n3) {
            if ((this.h >= n && this.h * n3 / n < n2) || (n > this.h && n * n2 / this.h < n3)) {
                ++n2;
                this.yC = this.h / n2;
            }
            else {
                ++n3;
                this.yC = n / n3;
            }
        }
        graphics.setColor(new Color(0));
        graphics.setFont(this.wFont);
        int n4 = 0;
        final Enumeration<Object> elements = (Enumeration<Object>)this.v.elements();
        while (elements.hasMoreElements()) {
            this.drawArc(graphics, n4++, elements.nextElement());
        }
    }
    
    public void setVal(final int iVal) {
        if (iVal < 2) {
            this.iVal = this.iNum;
            return;
        }
        this.iVal = iVal;
    }
    
    public boolean mouseMove(final Event event, final int iBx, final int iBy) {
        if ((this.iPres == 0 || this.iPres == 3) && this.bInter) {
            int n = 0;
            String in = null;
            final Enumeration<rq> elements = this.v.elements();
            while (elements.hasMoreElements()) {
                if (n >= this.iCur && n < this.iCur + this.iVal) {
                    in = this.isIn(iBx, iBy, n, elements.nextElement());
                    if (in != null) {
                        break;
                    }
                }
                else {
                    elements.nextElement();
                }
                ++n;
            }
            boolean b = in != null && this.sBullet == null;
            if (!b) {
                b = (in == null && this.sBullet != null);
            }
            if (!b && in != null) {
                b = !in.equals(this.sBullet);
            }
            if (b) {
                this.sBullet = in;
                this.iBx = iBx;
                this.iBy = iBy;
                this.repaint();
            }
        }
        return true;
    }
}
