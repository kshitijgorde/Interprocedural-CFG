import java.io.InputStream;
import java.net.URLConnection;
import java.net.URL;
import java.awt.MenuItem;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.PopupMenu;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ZCW extends Applet implements Runnable
{
    String[] sTn;
    String[] sTp;
    String[] sTi;
    String[] sTa;
    String[][] sTech;
    int[][] iTpar;
    int[][] iTmin;
    int[][] iTmax;
    int[][] iZpar;
    int[] bU;
    int[] bD;
    int[] iDate;
    float[][] fOhlc;
    float[][] fVol;
    float[][] fTech;
    String[] ttVal;
    int[] ccVal;
    String[] vvVal;
    float[] oriV;
    float[] ranV;
    float[] incV;
    int[] iDig;
    int[] curT;
    int curZ;
    float minV;
    float maxV;
    int oriX;
    int ranX;
    int curI;
    String[] strT;
    String[] strU;
    int iDay;
    Thread runner;
    Graphics dBuf;
    Image offScr;
    Font vFont;
    FontMetrics vMetrics;
    PopupMenu bPop;
    int err;
    int[] cx1;
    int[] cy1;
    int[] cxt;
    int[] cyt;
    int[] cx2;
    int[] cy2;
    int curC;
    String BCD;
    int pend;
    int[] oriY;
    int[] ranY;
    int[] polyX;
    int[] polyY;
    int curP;
    int icur;
    int ival;
    int fCur;
    int imin;
    int imax;
    
    public void calBr(final int dd, final float[] fBr) {
        if (this.iDate.length <= dd) {
            return;
        }
        for (int i = dd; i < this.iDate.length; ++i) {
            float hc = 0.0f;
            float cl = 0.0f;
            for (int j = i; j > i - dd; --j) {
                hc += this.fOhlc[1][j] - this.fOhlc[3][j - 1];
                cl += this.fOhlc[3][j - 1] - this.fOhlc[2][j];
            }
            fBr[i] = hc * 100.0f / cl;
        }
        for (int i = 0; i < dd; ++i) {
            fBr[i] = fBr[dd];
        }
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner = null;
        }
    }
    
    public void dCur() {
        final String sss = (this.ival == 0) ? "" : ("" + this.ival);
        final int iii = this.icur + this.vMetrics.stringWidth(sss);
        if (this.fCur == 0) {
            this.dBuf.setColor(new Color(0));
            this.dBuf.drawLine(iii, this.bD[this.curZ] + 13, iii, this.bD[this.curZ] + 33);
            this.fCur = 1;
        }
        else {
            this.dBuf.setColor(new Color(13421772));
            this.dBuf.drawLine(iii, this.bD[this.curZ] + 13, iii, this.bD[this.curZ] + 33);
            this.fCur = 0;
        }
    }
    
    public void calPsy(final int dd, final float[] fPsy) {
        if (this.iDate.length <= dd) {
            return;
        }
        for (int i = dd; i < this.iDate.length; ++i) {
            float upn = 0.0f;
            for (int j = i - 1; j > i - dd; --j) {
                if (this.fOhlc[3][j] > this.fOhlc[3][j - 1]) {
                    ++upn;
                }
            }
            fPsy[i] = 100.0f * upn / dd;
        }
        for (int i = 0; i < dd; ++i) {
            fPsy[i] = fPsy[dd];
        }
    }
    
    String[] parse(final String s, final String sep) {
        final StringTokenizer st = new StringTokenizer(s, sep);
        final String[] result = new String[st.countTokens()];
        for (int i = 0; i < result.length; ++i) {
            result[i] = st.nextToken();
        }
        return result;
    }
    
    public void dYLine(final Graphics g, final int oriY, final int ranY, final int rr) {
        for (int i = 0; i < (int)(this.ranV[rr] / this.incV[rr] + 1.5); ++i) {
            final float curV = this.oriV[rr] + this.incV[rr] * i;
            final int curY = (int)(oriY - (curV - this.oriV[rr]) * ranY / this.ranV[rr]);
            final String sTmp = this.pf(curV, this.iDig[rr], 0);
            g.setColor(new Color(12779645));
            g.drawString(sTmp, this.oriX + this.ranX + 10 + 4, curY + 3);
            g.setColor(new Color(10066329));
            g.drawLine(this.oriX, curY, this.oriX + this.ranX + 10 - 1, curY);
        }
    }
    
    public void iBox() {
        if (this.iTpar[this.curP][this.curT[this.curZ]] == 0) {
            return;
        }
        this.imin = ((this.iTmin[this.curP][this.curT[this.curZ]] == -1) ? this.iZpar[this.curP - 1][this.curZ] : this.iTmin[this.curP][this.curT[this.curZ]]);
        this.imax = this.iTmax[this.curP][this.curT[this.curZ]];
        final String pmp = "\u8acb\u8f38\u5165" + this.sTech[0][this.curT[this.curZ]] + "\u53c3\u6578" + (this.curP + 1) + "\u7684\u6578\u503c(" + this.imin + "-" + this.imax + "):";
        this.icur = this.vMetrics.stringWidth(pmp) + this.oriX + 75;
        this.err = 100;
        this.dBuf.setColor(new Color(13421772));
        this.dBuf.fill3DRect(this.oriX + 65, this.bD[this.curZ], this.icur - this.oriX - 75 + 55, 70, true);
        this.dBuf.fill3DRect(this.icur + 20, this.bD[this.curZ] + 10, 15, 15, true);
        this.dBuf.fill3DRect(this.icur + 20, this.bD[this.curZ] + 30, 15, 15, true);
        this.dBuf.fill3DRect(this.icur - 35, this.bD[this.curZ] + 45, 35, 17, true);
        this.dBuf.setColor(new Color(0));
        this.dBuf.drawString(pmp + this.iZpar[this.curP][this.curZ], this.oriX + 75, this.bD[this.curZ] + 30);
        this.dBuf.drawLine(this.icur + 23, this.bD[this.curZ] + 17, this.icur + 31, this.bD[this.curZ] + 17);
        this.dBuf.drawLine(this.icur + 27, this.bD[this.curZ] + 13, this.icur + 27, this.bD[this.curZ] + 21);
        this.dBuf.drawLine(this.icur + 23, this.bD[this.curZ] + 37, this.icur + 31, this.bD[this.curZ] + 37);
        this.dBuf.drawString("\u78ba\u5b9a", this.icur - 29, this.bD[this.curZ] + 58);
        this.ival = this.iZpar[this.curP][this.curZ];
        this.fCur = 0;
        this.dCur();
        this.repaint();
    }
    
    public void calWil(final int dd, final float[] fWil) {
        if (this.iDate.length < dd) {
            return;
        }
        float hh = 0.0f;
        float ll = 0.0f;
        for (int i = dd - 1; i < this.iDate.length; ++i) {
            hh = this.fOhlc[1][i];
            ll = this.fOhlc[2][i];
            for (int j = i - 1; j > i - dd; --j) {
                if (this.fOhlc[1][j] > hh) {
                    hh = this.fOhlc[1][j];
                }
                if (this.fOhlc[2][j] < ll) {
                    ll = this.fOhlc[2][j];
                }
            }
            if (hh == ll) {
                fWil[i] = 50.0f;
            }
            else {
                fWil[i] = 100.0f * (hh - this.fOhlc[3][i]) / (hh - ll);
            }
        }
    }
    
    public void calBias(final int dd, final float[] fBias) {
        if (this.iDate.length < dd) {
            return;
        }
        float sum = 0.0f;
        for (int i = 0; i < dd; ++i) {
            sum += this.fOhlc[3][i];
        }
        fBias[dd - 1] = (this.fOhlc[3][dd - 1] - sum / dd) * 100.0f * dd / sum;
        for (int i = dd; i < this.iDate.length; ++i) {
            sum += this.fOhlc[3][i];
            sum -= this.fOhlc[3][i - dd];
            fBias[i] = (this.fOhlc[3][i] - sum / dd) * 100.0f * dd / sum;
        }
        for (int i = 0; i < dd - 1; ++i) {
            fBias[i] = fBias[dd - 1];
        }
    }
    
    public void calMacd(final int dd1, final int dd2, final int dd3, final float[] fDif, final float[] fMacd, final float[] fMo) {
        if (this.iDate.length < dd2) {
            return;
        }
        float ema2;
        float ema1 = ema2 = (this.fOhlc[3][0] * 2.0f + this.fOhlc[1][0] + this.fOhlc[2][0]) / 4.0f;
        fDif[0] = 0.0f;
        fMo[0] = (fMacd[0] = 0.0f);
        for (int i = 1; i < this.iDate.length; ++i) {
            final float pd = (this.fOhlc[3][i] * 2.0f + this.fOhlc[1][i] + this.fOhlc[2][i]) / 4.0f;
            ema1 += (pd - ema1) * 2.0f / (dd1 + 1);
            ema2 += (pd - ema2) * 2.0f / (dd2 + 1);
            fDif[i] = ema1 - ema2;
            fMacd[i] = fMacd[i - 1] + (fDif[i] - fMacd[i - 1]) * 2.0f / (dd3 + 1);
            fMo[i] = fDif[i] - fMacd[i];
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void bpaint(final Graphics g) {
        g.clearRect(0, 0, this.size().width, this.size().height);
        String sTmp = this.strT[0];
        if (this.iDay != 0) {
            sTmp = sTmp + "(" + this.dateStr(this.iDay, 0) + ")";
        }
        if (this.err != 0) {
            g.setColor(new Color(0));
            g.drawString((this.err >= 111) ? "\u8b80\u53d6\u6578\u64da\u4e2d" : ("\u7121\u8cc7\u6599" + this.err), 10, 40);
            return;
        }
        final int iWid = 6;
        this.oriX = 8;
        this.ranX = this.size().width - 20 - 10 - iWid * this.vMetrics.stringWidth("8");
        this.oriY[2] = this.size().height - 20;
        this.ranY[0] = (this.size().height - 80 - 10) * 2 / 4;
        this.ranY[2] = (this.ranY[1] = (this.size().height - 80 - 10) / 4);
        this.oriY[1] = this.oriY[2] - this.ranY[2] - 20;
        this.oriY[0] = this.oriY[1] - this.ranY[1] - 20;
        for (int i = 0; i < 3; ++i) {
            this.dPan(g, this.oriY[i], this.ranY[i]);
            this.bU[i] = this.oriY[i] - this.ranY[i] - 15;
            this.bD[i] = this.bU[i] + 14;
        }
        sTmp = this.dateStr(this.iDate[this.curI], this.iDay) + " \u958b " + this.fStr(this.fOhlc[0][this.curI]);
        sTmp = sTmp + " \u9ad8 " + this.fStr(this.fOhlc[1][this.curI]);
        sTmp = sTmp + " \u4f4e " + this.fStr(this.fOhlc[2][this.curI]);
        sTmp = sTmp + " \u6536 " + this.fStr(this.fOhlc[3][this.curI]) + this.strU[1];
        if (this.curI > 0) {
            final float ud = this.fOhlc[3][this.curI] - this.fOhlc[3][this.curI - 1];
            final String porm = (ud >= 0.0f) ? "+" : "";
            int udt0 = 0;
            String udS = this.fStr(this.fOhlc[3][this.curI]);
            int udt2 = udS.indexOf(46);
            if (udt2 >= 0) {
                udt0 = udS.length() - udt2 - 1;
            }
            int udt3 = 0;
            udS = this.fStr(this.fOhlc[3][this.curI - 1]);
            udt2 = udS.indexOf(46);
            if (udt2 >= 0) {
                udt3 = udS.length() - udt2 - 1;
            }
            sTmp = sTmp + " " + porm + this.pf(ud, Math.max(udt0, udt3), 0);
            sTmp = sTmp + "(" + porm + this.pf(ud * 100.0f / this.fOhlc[3][this.curI - 1], 2, 0) + "%)";
        }
        g.setColor(new Color(0));
        g.drawString(sTmp, this.oriX + 85, 13);
        g.drawString(this.strT[1], this.oriX, 13);
        g.drawString(this.sTech[0][0], this.oriX, this.oriY[0] - this.ranY[0] - 3);
        g.drawString(this.dateStr(this.iDate[0], this.iDay) + "~" + this.dateStr(this.iDate[this.iDate.length - 1], this.iDay), this.oriX, this.oriY[2] + this.vMetrics.getAscent() + 5);
        this.dPol(g, this.oriX + 65, this.oriY[0] - this.ranY[0] - 15);
        this.dYLine(g, this.oriY[0], this.ranY[0], 0);
        this.dKline(g, this.oriY[0], this.ranY[0], 0);
        this.calMa(this.fOhlc[3], this.iZpar[0][0], this.fTech[0]);
        this.calMa(this.fOhlc[3], this.iZpar[1][0], this.fTech[1]);
        this.calMa(this.fOhlc[3], this.iZpar[2][0], this.fTech[2]);
        this.dLine(g, this.oriY[0], this.ranY[0], 0, this.fTech[0], this.iZpar[0][0] - 1, this.iDate.length, 255);
        this.dLine(g, this.oriY[0], this.ranY[0], 0, this.fTech[1], this.iZpar[1][0] - 1, this.iDate.length, 16711935);
        this.dLine(g, this.oriY[0], this.ranY[0], 0, this.fTech[2], this.iZpar[2][0] - 1, this.iDate.length, 26112);
        int vlen = this.oriX + 85;
        final int orY = this.oriY[0] - this.ranY[0] - 3 - 15;
        vlen = this.dVal0(g, orY, vlen, this.sTech[1][0] + this.iZpar[0][0], 255);
        if (this.curI >= this.iZpar[0][0] - 1) {
            vlen = this.dVal0(g, orY, vlen, this.pf(this.fTech[0][this.curI], 2, 0), 255);
        }
        else {
            vlen = this.dVal0(g, orY, vlen, "N/A", 255);
        }
        vlen = this.dVal0(g, orY, vlen, this.sTech[2][0] + this.iZpar[1][0], 16711935);
        if (this.curI >= this.iZpar[1][0] - 1) {
            vlen = this.dVal0(g, orY, vlen, this.pf(this.fTech[1][this.curI], 2, 0), 16711935);
        }
        else {
            vlen = this.dVal0(g, orY, vlen, "N/A", 16711935);
        }
        vlen = this.dVal0(g, orY, vlen, this.sTech[3][0] + this.iZpar[2][0], 26112);
        if (this.curI >= this.iZpar[2][0] - 1) {
            vlen = this.dVal0(g, orY, vlen, this.pf(this.fTech[2][this.curI], 2, 0), 26112);
        }
        else {
            vlen = this.dVal0(g, orY, vlen, "N/A", 26112);
        }
        this.dTech(g, this.oriY[1], this.ranY[1], 1, this.iZpar[0][1], this.iZpar[1][1], this.iZpar[2][1]);
        this.dTech(g, this.oriY[2], this.ranY[2], 2, this.iZpar[0][2], this.iZpar[1][2], this.iZpar[2][2]);
        g.setColor(new Color(0));
        for (int j = 0; j < 6; ++j) {
            if (this.cx2[j] != 0) {
                g.drawLine(this.cx1[j], this.cy1[j], this.cx2[j], this.cy2[j]);
            }
        }
    }
    
    public void dKline(final Graphics g, final int oriY, final int ranY, final int rr) {
        int iHlf = Math.max(2 * this.ranX / this.iDate.length / 5 - 1, 1);
        if (iHlf > 10) {
            iHlf = 10;
        }
        for (int i = 0; i < this.iDate.length; ++i) {
            final int curX = this.oriX + (i * 2 + 1) * this.ranX / (2 * this.iDate.length);
            final int curO = (int)(oriY - (this.fOhlc[0][i] - this.oriV[rr]) * ranY / this.ranV[rr]);
            final int curH = (int)(oriY - (this.fOhlc[1][i] - this.oriV[rr]) * ranY / this.ranV[rr]);
            final int curL = (int)(oriY - (this.fOhlc[2][i] - this.oriV[rr]) * ranY / this.ranV[rr]);
            final int curC = (int)(oriY - (this.fOhlc[3][i] - this.oriV[rr]) * ranY / this.ranV[rr]);
            if (this.fOhlc[0][i] < this.fOhlc[3][i]) {
                g.setColor(new Color(16711680));
            }
            else {
                g.setColor(new Color(0));
            }
            g.drawLine(curX, curC, curX, curH);
            g.drawLine(curX, curO, curX, curL);
            g.fillRect(curX - iHlf, Math.min(curO, curC), iHlf * 2 + 1, Math.abs(curC - curO) + 1);
        }
    }
    
    public void cpaint(final Graphics g) {
        g.setXORMode(new Color(16777215));
        if (this.cxt[this.curC] != 0) {
            g.setColor(new Color(0));
            g.drawLine(this.cx1[this.curC], this.cy1[this.curC], this.cxt[this.curC], this.cyt[this.curC]);
        }
        if (this.cx2[this.curC] != 0) {
            g.setColor(new Color(0));
            g.drawLine(this.cx1[this.curC], this.cy1[this.curC], this.cx2[this.curC], this.cy2[this.curC]);
        }
        g.setPaintMode();
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void calKd(final int dd, final float[] fK, final float[] fD) {
        if (this.iDate.length < dd) {
            return;
        }
        float hh = 0.0f;
        float ll = 0.0f;
        fD[dd - 2] = (fK[dd - 2] = 50.0f);
        for (int i = dd - 1; i < this.iDate.length; ++i) {
            hh = this.fOhlc[1][i];
            ll = this.fOhlc[2][i];
            for (int j = i - 1; j > i - dd; --j) {
                if (this.fOhlc[1][j] > hh) {
                    hh = this.fOhlc[1][j];
                }
                if (this.fOhlc[2][j] < ll) {
                    ll = this.fOhlc[2][j];
                }
            }
            float rsv;
            if (hh == ll) {
                rsv = 50.0f;
            }
            else {
                rsv = 100.0f * (this.fOhlc[3][i] - ll) / (hh - ll);
            }
            fK[i] = fK[i - 1] + (rsv - fK[i - 1]) / 3.0f;
            fD[i] = fD[i - 1] + (fK[i] - fD[i - 1]) / 3.0f;
        }
    }
    
    public String pf(final float ff, final int dd, final int d0) {
        String tt;
        if (ff - (long)ff == 0.0f) {
            if (d0 != 0) {
                return "" + (int)ff;
            }
            tt = (long)ff + ".000000";
        }
        else {
            final float rd = (float)(((ff < 0.0f) ? -5 : 5) / Math.pow(10.0, dd + 1));
            tt = Float.toString(ff + rd) + "000000";
        }
        if (dd == 0) {
            tt = tt.substring(0, tt.indexOf(46));
        }
        else {
            tt = tt.substring(0, tt.indexOf(46) + dd + 1);
        }
        return tt;
    }
    
    public void calMtm(final int dd, final float[] fMtm) {
        if (this.iDate.length <= dd) {
            return;
        }
        for (int i = dd; i < this.iDate.length; ++i) {
            fMtm[i] = this.fOhlc[3][i] - this.fOhlc[3][i - dd];
        }
        for (int i = 0; i < dd; ++i) {
            fMtm[i] = fMtm[dd];
        }
    }
    
    public void calVr(final int dd, final float[] fVr) {
        if (this.iDate.length <= dd) {
            return;
        }
        for (int i = dd; i < this.iDate.length; ++i) {
            float upv = 0.0f;
            float dnv = 0.0f;
            float ncv = 0.0f;
            for (int j = i; j > i - dd; --j) {
                final float ud = this.fOhlc[3][j] - this.fOhlc[3][j - 1];
                if (ud > 0.0f) {
                    upv += this.fVol[0][j];
                }
                else if (ud < 0.0f) {
                    dnv += this.fVol[0][j];
                }
                else {
                    ncv += this.fVol[0][j];
                }
            }
            fVr[i] = (upv + ncv / 2.0f) * 100.0f / (dnv + ncv / 2.0f);
        }
        for (int i = 0; i < dd; ++i) {
            fVr[i] = fVr[dd];
        }
    }
    
    public void calTapi(final float[] fTapi) {
        for (int i = 0; i < this.iDate.length; ++i) {
            fTapi[i] = this.fVol[0][i] / this.fOhlc[3][i];
        }
    }
    
    public boolean keyDown(final Event evt, final int key) {
        if (this.err == 0) {
            if (key == 1006 && this.curI > 0) {
                --this.curI;
                this.bpaint(this.dBuf);
                this.repaint();
            }
            else if (key == 1007 && this.curI < this.iDate.length - 1) {
                ++this.curI;
                this.bpaint(this.dBuf);
                this.repaint();
            }
            return true;
        }
        if (this.err == 100) {
            switch (key) {
                case 8:
                case 1006: {
                    this.ival = ((this.ival < 10) ? 0 : (this.ival / 10));
                    this.dival();
                    break;
                }
                case 10: {
                    if (this.ival >= this.imin && this.ival <= this.imax) {
                        this.dok();
                    }
                    break;
                }
                default: {
                    if (key >= 48 && key <= 57 && this.ival < 10) {
                        this.ival = this.ival * 10 + key - 48;
                        this.dival();
                    }
                    break;
                }
            }
            return true;
        }
        return false;
    }
    
    public void calObv(final float[] fObv) {
        fObv[0] = 0.0f;
        for (int i = 1; i < this.iDate.length; ++i) {
            final float ud = this.fOhlc[3][i] - this.fOhlc[3][i - 1];
            if (ud > 0.0f) {
                fObv[i] = fObv[i - 1] + this.fVol[0][i];
            }
            else if (ud < 0.0f) {
                fObv[i] = fObv[i - 1] - this.fVol[0][i];
            }
            else {
                fObv[i] = fObv[i - 1];
            }
        }
    }
    
    public String dateStr(final int dd, final int id) {
        String dStr = "";
        if (dd / 10000 > 0) {
            dStr = dStr + dd / 10000 + "/";
        }
        int ii = dd % 10000 / 100;
        dStr = dStr + ((ii < 10) ? ("0" + ii) : ("" + ii)) + ((id == 0) ? "/" : ":");
        ii = dd % 100;
        dStr += ((ii < 10) ? ("0" + ii) : ("" + ii));
        return dStr;
    }
    
    public int dVal0(final Graphics g, final int yy, int vlen, final String ss, final int cc) {
        g.setColor(new Color(cc));
        g.drawString(ss, vlen, yy + this.vMetrics.getAscent() + 5);
        vlen += this.vMetrics.stringWidth(ss) + 5;
        return vlen;
    }
    
    public void setBCD(final String bcd) {
        this.err = 112;
        this.bpaint(this.dBuf);
        this.repaint();
        this.BCD = bcd;
        this.inVal();
    }
    
    public void dTech(final Graphics g, final int oriY, final int ranY, final int zone, final int p1, final int p2, final int p3) {
        switch (this.curT[zone] - 1) {
            case 1: {
                this.calRsi(p1, this.fTech[0]);
                this.calRsi(p2, this.fTech[1]);
                this.oriV[zone] = 0.0f;
                this.ranV[zone] = 100.0f;
                this.incV[zone] = 25.0f;
                this.iDig[zone] = 0;
                this.ttVal[0] = this.sTech[1][2] + p1;
                this.ttVal[1] = this.sTech[2][2] + p2;
                this.vvVal[0] = ((this.curI < p1) ? "N/A" : this.pf(this.fTech[0][this.curI], 2, 0));
                this.vvVal[1] = ((this.curI < p2) ? "N/A" : this.pf(this.fTech[1][this.curI], 2, 0));
                this.ccVal[0] = 0;
                this.ccVal[1] = 16711680;
                this.dVal(g, oriY, ranY, 2, "%", 1);
                this.dYLine(g, oriY, ranY, zone);
                this.dLine(g, oriY, ranY, zone, this.fTech[0], p1, this.iDate.length, 0);
                this.dLine(g, oriY, ranY, zone, this.fTech[1], p2, this.iDate.length, 16711680);
                break;
            }
            case 2: {
                this.calKd(p1, this.fTech[0], this.fTech[1]);
                this.oriV[zone] = 0.0f;
                this.ranV[zone] = 100.0f;
                this.incV[zone] = 25.0f;
                this.iDig[zone] = 0;
                this.ttVal[0] = this.sTech[1][3] + p1;
                this.ttVal[1] = this.sTech[2][3] + p1;
                this.vvVal[0] = ((this.curI < p1) ? "N/A" : this.pf(this.fTech[0][this.curI], 2, 0));
                this.vvVal[1] = ((this.curI < p1) ? "N/A" : this.pf(this.fTech[1][this.curI], 2, 0));
                this.ccVal[0] = 0;
                this.ccVal[1] = 16711680;
                this.dVal(g, oriY, ranY, 2, "%", 1);
                this.dYLine(g, oriY, ranY, zone);
                this.dLine(g, oriY, ranY, zone, this.fTech[0], p1, this.iDate.length, 0);
                this.dLine(g, oriY, ranY, zone, this.fTech[1], p1, this.iDate.length, 16711680);
                break;
            }
            case 3: {
                this.calMacd(p1, p2, p3, this.fTech[0], this.fTech[1], this.fTech[2]);
                this.calMaxMin(this.fTech[0], this.fTech[0], 0, p2);
                final float mmin = this.minV;
                final float mmax = this.maxV;
                this.calMaxMin(this.fTech[2], this.fTech[2], 0, p2);
                if (mmin < this.minV) {
                    this.minV = mmin;
                }
                if (mmax > this.maxV) {
                    this.maxV = mmax;
                }
                this.calYval(zone, 6);
                this.ttVal[0] = this.sTech[1][4] + p1 + "-" + p2;
                this.ttVal[1] = this.sTech[2][4] + p3;
                this.ttVal[2] = this.sTech[3][4];
                this.vvVal[0] = ((this.curI < p2) ? "N/A" : this.pf(this.fTech[0][this.curI], 2, 0));
                this.vvVal[1] = ((this.curI < p2) ? "N/A" : this.pf(this.fTech[1][this.curI], 2, 0));
                this.vvVal[2] = ((this.curI < p2) ? "N/A" : this.pf(this.fTech[2][this.curI], 2, 0));
                this.ccVal[0] = 0;
                this.ccVal[1] = 16711680;
                this.ccVal[2] = 255;
                this.dVal(g, oriY, ranY, 3, "", 1);
                this.dYLine(g, oriY, ranY, zone);
                this.dBar(g, oriY, ranY, zone, this.fTech[2], p2, 255, 1);
                this.dLine(g, oriY, ranY, zone, this.fTech[0], p2, this.iDate.length, 0);
                this.dLine(g, oriY, ranY, zone, this.fTech[1], p2, this.iDate.length, 16711680);
                break;
            }
            case 4: {
                this.calObv(this.fTech[0]);
                this.calMaxMin(this.fTech[0], this.fTech[0], 0, 0);
                this.calYval(zone, 6);
                this.ttVal[0] = this.sTech[1][5];
                this.vvVal[0] = this.pf(this.fTech[0][this.curI], 0, 0);
                this.dVal(g, oriY, ranY, 1, "", this.ccVal[0] = 0);
                this.dYLine(g, oriY, ranY, zone);
                this.dLine(g, oriY, ranY, zone, this.fTech[0], 0, this.iDate.length, 0);
                break;
            }
            case 5: {
                this.calAr(p1, this.fTech[0]);
                this.calMaxMin(this.fTech[0], this.fTech[0], 0, p1 - 1);
                this.calYval(zone, 6);
                this.ttVal[0] = this.sTech[1][6] + p1;
                this.vvVal[0] = ((this.curI < p1 - 1) ? "N/A" : this.pf(this.fTech[0][this.curI], 2, 0));
                this.ccVal[0] = 0;
                this.dVal(g, oriY, ranY, 1, "%", 1);
                this.dYLine(g, oriY, ranY, zone);
                this.dLine(g, oriY, ranY, zone, this.fTech[0], p1 - 1, this.iDate.length, 0);
                break;
            }
            case 6: {
                this.calBr(p1, this.fTech[0]);
                this.calMaxMin(this.fTech[0], this.fTech[0], 0, p1);
                this.calYval(zone, 6);
                this.ttVal[0] = this.sTech[1][7] + p1;
                this.vvVal[0] = ((this.curI < p1) ? "N/A" : this.pf(this.fTech[0][this.curI], 2, 0));
                this.ccVal[0] = 0;
                this.dVal(g, oriY, ranY, 1, "%", 1);
                this.dYLine(g, oriY, ranY, zone);
                this.dLine(g, oriY, ranY, zone, this.fTech[0], p1, this.iDate.length, 0);
                break;
            }
            case 7: {
                this.calVr(p1, this.fTech[0]);
                this.calMaxMin(this.fTech[0], this.fTech[0], 0, p1);
                this.calYval(zone, 6);
                this.ttVal[0] = this.sTech[1][8] + p1;
                this.vvVal[0] = ((this.curI < p1) ? "N/A" : this.pf(this.fTech[0][this.curI], 2, 0));
                this.ccVal[0] = 0;
                this.dVal(g, oriY, ranY, 1, "%", 1);
                this.dYLine(g, oriY, ranY, zone);
                this.dLine(g, oriY, ranY, zone, this.fTech[0], p1, this.iDate.length, 0);
                break;
            }
            case 8: {
                this.calBias(p1, this.fTech[0]);
                this.calBias(p2, this.fTech[1]);
                this.calBias(p3, this.fTech[2]);
                this.calMaxMin(this.fTech[2], this.fTech[2], 0, 0);
                float mmin = this.minV;
                float mmax = this.maxV;
                this.calMaxMin(this.fTech[1], this.fTech[1], 0, 0);
                if (this.minV < mmin) {
                    mmin = this.minV;
                }
                if (this.maxV > mmax) {
                    mmax = this.maxV;
                }
                this.calMaxMin(this.fTech[0], this.fTech[0], 0, 0);
                if (mmin < this.minV) {
                    this.minV = mmin;
                }
                if (mmax > this.maxV) {
                    this.maxV = mmax;
                }
                this.calYval(zone, 6);
                this.ttVal[0] = this.sTech[1][9] + p1;
                this.vvVal[0] = ((this.curI < p1 - 1) ? "N/A" : this.pf(this.fTech[0][this.curI], 2, 0));
                this.ccVal[0] = 255;
                this.ttVal[1] = this.sTech[2][9] + p2;
                this.vvVal[1] = ((this.curI < p2 - 1) ? "N/A" : this.pf(this.fTech[1][this.curI], 2, 0));
                this.ccVal[1] = 16711935;
                this.ttVal[2] = this.sTech[3][9] + p3;
                this.vvVal[2] = ((this.curI < p3 - 1) ? "N/A" : this.pf(this.fTech[2][this.curI], 2, 0));
                this.ccVal[2] = 26112;
                this.dVal(g, oriY, ranY, 3, "%", 1);
                this.dYLine(g, oriY, ranY, zone);
                this.dLine(g, oriY, ranY, zone, this.fTech[0], p1 - 1, this.iDate.length, 255);
                this.dLine(g, oriY, ranY, zone, this.fTech[1], p2 - 1, this.iDate.length, 16711935);
                this.dLine(g, oriY, ranY, zone, this.fTech[2], p3 - 1, this.iDate.length, 26112);
                break;
            }
            case 9: {
                this.calBias(p1, this.fTech[1]);
                this.calBias(p2, this.fTech[2]);
                for (int i = 0; i < this.iDate.length; ++i) {
                    this.fTech[0][i] = this.fTech[1][i] - this.fTech[2][i];
                }
                this.calMaxMin(this.fTech[0], this.fTech[0], 0, 0);
                this.calYval(zone, 6);
                this.ttVal[0] = p1 + "-" + p2 + this.sTech[1][10];
                this.vvVal[0] = ((this.curI < p2 - 1) ? "N/A" : this.pf(this.fTech[0][this.curI], 2, 0));
                this.ccVal[0] = 0;
                this.dVal(g, oriY, ranY, 1, "%", 1);
                this.dYLine(g, oriY, ranY, zone);
                this.dLine(g, oriY, ranY, zone, this.fTech[0], p2 - 1, this.iDate.length, 0);
                break;
            }
            case 10: {
                this.calMtm(p1, this.fTech[0]);
                this.calMaxMin(this.fTech[0], this.fTech[0], 0, 0);
                this.calYval(zone, 6);
                this.ttVal[0] = this.sTech[1][11] + p1;
                this.vvVal[0] = ((this.curI < p1) ? "N/A" : this.pf(this.fTech[0][this.curI], 2, 0));
                this.ccVal[0] = 0;
                this.dVal(g, oriY, ranY, 1, "", 1);
                this.dYLine(g, oriY, ranY, zone);
                this.dLine(g, oriY, ranY, zone, this.fTech[0], p1, this.iDate.length, 0);
                break;
            }
            case 11: {
                this.calWil(p1, this.fTech[0]);
                this.oriV[zone] = 0.0f;
                this.ranV[zone] = 100.0f;
                this.incV[zone] = 25.0f;
                this.iDig[zone] = 0;
                this.ttVal[0] = this.sTech[1][12] + p1;
                this.vvVal[0] = ((this.curI < p1) ? "N/A" : this.pf(this.fTech[0][this.curI], 2, 0));
                this.ccVal[0] = 0;
                this.dVal(g, oriY, ranY, 1, "%", 1);
                this.dYLine(g, oriY, ranY, zone);
                this.dLine(g, oriY, ranY, zone, this.fTech[0], p1, this.iDate.length, 0);
                break;
            }
            case 12: {
                this.calDmi(p1, this.fTech[0], this.fTech[1], this.fTech[2]);
                this.calMaxMin(this.fTech[0], this.fTech[0], 0, p1);
                float mmin = this.minV;
                float mmax = this.maxV;
                this.calMaxMin(this.fTech[1], this.fTech[1], 0, p1);
                if (mmin < this.minV) {
                    this.minV = mmin;
                }
                if (mmax > this.maxV) {
                    this.maxV = mmax;
                }
                mmin = this.minV;
                mmax = this.maxV;
                this.calMaxMin(this.fTech[2], this.fTech[2], 0, p1);
                if (mmin < this.minV) {
                    this.minV = mmin;
                }
                if (mmax > this.maxV) {
                    this.maxV = mmax;
                }
                this.calYval(zone, 6);
                this.ttVal[0] = this.sTech[1][13] + p1;
                this.vvVal[0] = ((this.curI < p1) ? "N/A" : this.pf(this.fTech[0][this.curI], 2, 0));
                this.ccVal[0] = 16711680;
                this.ttVal[1] = this.sTech[2][13] + p1;
                this.vvVal[1] = ((this.curI < p1) ? "N/A" : this.pf(this.fTech[1][this.curI], 2, 0));
                this.ccVal[1] = 0;
                this.ttVal[2] = this.sTech[3][13] + p1;
                this.vvVal[2] = ((this.curI < p1) ? "N/A" : this.pf(this.fTech[2][this.curI], 2, 0));
                this.ccVal[2] = 255;
                this.dVal(g, oriY, ranY, 3, "", 1);
                this.dYLine(g, oriY, ranY, zone);
                this.dLine(g, oriY, ranY, zone, this.fTech[0], p1, this.iDate.length, 16711680);
                this.dLine(g, oriY, ranY, zone, this.fTech[1], p1, this.iDate.length, 0);
                this.dLine(g, oriY, ranY, zone, this.fTech[2], p1, this.iDate.length, 255);
                break;
            }
            case 13: {
                this.calTapi(this.fTech[0]);
                this.calMaxMin(this.fTech[0], this.fTech[0], 0, 0);
                this.calYval(zone, 6);
                this.ttVal[0] = this.sTech[1][14];
                this.vvVal[0] = this.pf(this.fTech[0][this.curI], 2, 0);
                this.dVal(g, oriY, ranY, 1, "", this.ccVal[0] = 0);
                this.dYLine(g, oriY, ranY, zone);
                this.dLine(g, oriY, ranY, zone, this.fTech[0], 0, this.iDate.length, 0);
                break;
            }
            case 14: {
                this.calPsy(p1, this.fTech[0]);
                this.calPsy(p2, this.fTech[1]);
                this.oriV[zone] = 0.0f;
                this.ranV[zone] = 100.0f;
                this.incV[zone] = 25.0f;
                this.iDig[zone] = 0;
                this.ttVal[0] = this.sTech[1][15] + p1;
                this.ttVal[1] = this.sTech[2][15] + p2;
                this.vvVal[0] = ((this.curI < p1) ? "N/A" : this.pf(this.fTech[0][this.curI], 2, 0));
                this.vvVal[1] = ((this.curI < p2) ? "N/A" : this.pf(this.fTech[1][this.curI], 2, 0));
                this.ccVal[0] = 0;
                this.ccVal[1] = 16711680;
                this.dVal(g, oriY, ranY, 2, "%", 1);
                this.dYLine(g, oriY, ranY, zone);
                this.dLine(g, oriY, ranY, zone, this.fTech[0], p1, this.iDate.length, 0);
                this.dLine(g, oriY, ranY, zone, this.fTech[1], p2, this.iDate.length, 16711680);
                break;
            }
            default: {
                final int vvi = (this.curT[zone] == 1) ? 0 : (this.curT[zone] - 15);
                this.calMa(this.fVol[vvi], p1, this.fTech[0]);
                this.calMa(this.fVol[vvi], p2, this.fTech[1]);
                if (this.curT[zone] == 1) {
                    this.calMaxMin(this.fVol[vvi], this.fVol[vvi], 1, 0);
                }
                else {
                    this.calMaxMin(this.fVol[vvi], this.fVol[vvi], 0, 0);
                }
                this.calYval(zone, 7);
                this.ttVal[0] = this.sTech[0][this.curT[zone]];
                this.vvVal[0] = ((this.curI >= this.fVol[vvi].length) ? "N/A" : this.pf(this.fVol[vvi][this.curI], 0, 1));
                this.ccVal[0] = 255;
                this.ttVal[1] = this.sTech[1][1] + p1;
                this.vvVal[1] = ((this.curI < p1 - 1 || this.curI >= this.fVol[vvi].length) ? "N/A" : this.pf(this.fTech[0][this.curI], 0, 1));
                this.ccVal[1] = 0;
                this.ttVal[2] = this.sTech[2][1] + p2;
                this.vvVal[2] = ((this.curI < p2 - 1 || this.curI >= this.fVol[vvi].length) ? "N/A" : this.pf(this.fTech[1][this.curI], 0, 1));
                this.ccVal[2] = 16711680;
                this.dVal(g, oriY, ranY, 3, this.strU[2], 1);
                this.dYLine(g, oriY, ranY, zone);
                this.dBar(g, oriY, ranY, zone, this.fVol[vvi], 0, 255, 2);
                this.dLine(g, oriY, ranY, zone, this.fTech[0], p1 - 1, this.fVol[vvi].length, 0);
                this.dLine(g, oriY, ranY, zone, this.fTech[1], p2 - 1, this.fVol[vvi].length, 16711680);
                break;
            }
        }
    }
    
    public void calMaxMin(final float[] fValH, final float[] fValL, final int zz, final int bgn) {
        if (fValH.length <= bgn) {
            return;
        }
        this.minV = ((zz == 1) ? 0.0f : fValL[bgn]);
        this.maxV = fValH[bgn];
        for (int i = bgn; i < fValH.length; ++i) {
            if (fValL[i] < this.minV) {
                this.minV = fValL[i];
            }
            if (fValH[i] > this.maxV) {
                this.maxV = fValH[i];
            }
        }
    }
    
    public void calYval(final int ii, final int iSep) {
        this.incV[ii] = (this.maxV - this.minV) / iSep;
        if (this.incV[ii] == 0.0f) {
            if (this.maxV == 0.0f) {
                this.minV = -1.0f;
                final float[] incV = this.incV;
                final float maxV = 1.0f;
                incV[ii] = maxV;
                this.maxV = maxV;
            }
            else {
                this.incV[ii] = this.maxV / 100.0f;
                this.minV -= this.incV[ii];
                this.maxV += this.incV[ii];
            }
        }
        final float fLog = (float)(Math.log(this.incV[ii]) / Math.log(10.0));
        int iInt = (int)Math.floor(fLog);
        final float fF = fLog - iInt;
        if (fF == 0.0f) {
            this.incV[ii] = 1.0f;
        }
        else if (fF < Math.log(2.0) / Math.log(10.0)) {
            this.incV[ii] = 2.0f;
        }
        else if (fF < Math.log(5.0) / Math.log(10.0)) {
            this.incV[ii] = 5.0f;
        }
        else {
            this.incV[ii] = 1.0f;
            ++iInt;
        }
        this.iDig[ii] = ((iInt < 0) ? (-iInt) : 0);
        this.incV[ii] *= (float)Math.pow(10.0, iInt);
        this.minV = (float)(Math.floor(this.minV / this.incV[ii]) * this.incV[ii]);
        this.maxV = (float)(Math.ceil(this.maxV / this.incV[ii]) * this.incV[ii]);
        this.ranV[ii] = this.maxV - this.minV;
        this.oriV[ii] = this.minV;
    }
    
    public void paint(final Graphics g) {
        g.drawImage(this.offScr, 0, 0, this);
    }
    
    public void dPan(final Graphics g, final int oriY, final int ranY) {
        g.setColor(new Color(6710886));
        g.fillRect(this.oriX + 3, oriY + 1, this.ranX + 10, 3);
        g.fillRect(this.oriX + this.ranX + 10, oriY - ranY + 4, 3, ranY);
        g.setColor(new Color(16775397));
        g.fillRect(this.oriX, oriY - ranY, this.ranX + 10, ranY);
        int ddl = this.iDate[0] / 100;
        int dpre = -12;
        for (int i = 1; i < this.iDate.length; ++i) {
            if (ddl != this.iDate[i] / 100) {
                ddl = this.iDate[i] / 100;
                if (i - dpre > 11) {
                    g.setColor(new Color(10601172));
                    g.fillRect(this.oriX + (i * 2 + 1) * this.ranX / (2 * this.iDate.length), oriY - ranY + 1, 1, ranY);
                    g.drawString("" + ddl, this.oriX + (i * 2 + 1) * this.ranX / (2 * this.iDate.length) + 1, oriY - 1);
                    dpre = i;
                }
            }
        }
        g.setColor(new Color(9550298));
        g.fillRect(this.oriX + (this.curI * 2 + 1) * this.ranX / (2 * this.iDate.length), oriY - ranY + 1, 2, ranY);
        g.clearRect(this.oriX, oriY + 4, this.ranX + 10, 16);
    }
    
    public ZCW() {
        this.sTn = new String[4];
        this.sTp = new String[3];
        this.sTi = new String[3];
        this.sTa = new String[3];
        this.sTech = new String[4][];
        this.iTpar = new int[3][];
        this.iTmin = new int[3][];
        this.iTmax = new int[3][];
        this.iZpar = new int[3][];
        this.bU = new int[3];
        this.bD = new int[3];
        this.fOhlc = new float[4][];
        this.fVol = new float[4][];
        this.fTech = new float[4][];
        this.ttVal = new String[4];
        this.ccVal = new int[4];
        this.vvVal = new String[4];
        this.oriV = new float[3];
        this.ranV = new float[3];
        this.incV = new float[3];
        this.iDig = new int[3];
        this.curT = new int[3];
        this.minV = 0.0f;
        this.maxV = 0.0f;
        this.iDay = 0;
        this.runner = null;
        this.bPop = new PopupMenu();
        this.cx1 = new int[6];
        this.cy1 = new int[6];
        this.cxt = new int[6];
        this.cyt = new int[6];
        this.cx2 = new int[6];
        this.cy2 = new int[6];
        this.curC = 0;
        this.pend = 0;
        this.oriY = new int[3];
        this.ranY = new int[3];
        this.polyX = new int[3];
        this.polyY = new int[3];
        this.fCur = 0;
    }
    
    public boolean mouseUp(final Event evt, final int x, final int y) {
        if (this.err == 0 && this.cx2[this.curC] != 0) {
            if (++this.curC == 6) {
                this.curC = 0;
            }
            final int[] cx1 = this.cx1;
            final int curC = this.curC;
            final int[] cx2 = this.cx2;
            final int curC2 = this.curC;
            final int[] cxt = this.cxt;
            final int curC3 = this.curC;
            final boolean b = false;
            cxt[curC3] = (b ? 1 : 0);
            cx1[curC] = (cx2[curC2] = (b ? 1 : 0));
            this.bpaint(this.dBuf);
            this.repaint();
        }
        return true;
    }
    
    int[] parseI(final String s, final String sep) {
        final StringTokenizer st = new StringTokenizer(s, sep);
        final int[] result = new int[st.countTokens()];
        for (int i = 0; i < result.length; ++i) {
            result[i] = Integer.parseInt(st.nextToken());
        }
        return result;
    }
    
    public void calDmi(final int dd, final float[] fpDi, final float[] fnDi, final float[] fAdx) {
        if (this.iDate.length < dd) {
            return;
        }
        float nadm;
        float padm = nadm = this.fOhlc[3][0] / 10000.0f;
        float atr = padm * 5.0f;
        fpDi[0] = 20.0f;
        fAdx[0] = (fnDi[0] = 20.0f);
        for (int i = 1; i < this.iDate.length; ++i) {
            float pdm = this.fOhlc[1][i] - this.fOhlc[1][i - 1];
            if (pdm < 0.0f) {
                pdm = 0.0f;
            }
            float ndm = this.fOhlc[2][i - 1] - this.fOhlc[2][i];
            if (ndm < 0.0f) {
                ndm = 0.0f;
            }
            if (pdm < ndm) {
                pdm = 0.0f;
            }
            else if (pdm > ndm) {
                ndm = 0.0f;
            }
            else {
                ndm = (pdm = 0.0f);
            }
            float tr;
            if (this.fOhlc[3][i - 1] > this.fOhlc[1][i]) {
                tr = this.fOhlc[3][i - 1] - this.fOhlc[2][i];
            }
            else if (this.fOhlc[3][i - 1] < this.fOhlc[2][i]) {
                tr = this.fOhlc[1][i] - this.fOhlc[3][i - 1];
            }
            else {
                tr = this.fOhlc[1][i] - this.fOhlc[2][i];
            }
            padm += (pdm - padm) / dd;
            nadm += (ndm - nadm) / dd;
            atr += (tr - atr) / dd;
            fpDi[i] = 100.0f * padm / atr;
            fnDi[i] = 100.0f * nadm / atr;
            float dx = 100.0f * (fpDi[i] - fnDi[i]) / (fpDi[i] + fnDi[i]);
            if (dx < 0.0f) {
                dx = -dx;
            }
            fAdx[i] = fAdx[i - 1] + (dx - fAdx[i - 1]) / dd;
        }
    }
    
    public void calRsi(final int dd, final float[] fRsi) {
        if (this.iDate.length < dd) {
            return;
        }
        float up;
        float dn = up = this.fOhlc[3][dd - 1] / 400.0f;
        fRsi[0] = 50.0f;
        for (int i = 1; i < this.iDate.length; ++i) {
            final float ud = this.fOhlc[3][i] - this.fOhlc[3][i - 1];
            up += (((ud > 0.0f) ? ud : 0.0f) - up) / dd;
            dn += (((ud < 0.0f) ? (-ud) : 0.0f) - dn) / dd;
            fRsi[i] = 100.0f * up / (up + dn);
        }
    }
    
    public void calMa(final float[] fVal, final int dd, final float[] fMav) {
        if (fVal.length < dd) {
            return;
        }
        float sum = 0.0f;
        for (int i = 0; i < dd; ++i) {
            sum += fVal[i];
        }
        fMav[dd - 1] = sum / dd;
        for (int i = dd; i < fVal.length; ++i) {
            sum += fVal[i];
            sum -= fVal[i - dd];
            fMav[i] = sum / dd;
        }
    }
    
    public void dVal(final Graphics g, final int oriY, final int ranY, final int llen, final String uu, final int ppp) {
        g.setColor(new Color(0));
        g.drawString("\u6307\u6a19", this.oriX, oriY - ranY - 3);
        if (ppp == 1) {
            g.drawString("\u53c3\u6578", this.oriX + 40, oriY - ranY - 3);
        }
        this.dPol(g, this.oriX + 25, oriY - ranY - 15);
        if (ppp == 1) {
            this.dPol(g, this.oriX + 65, oriY - ranY - 15);
        }
        int vlen = this.oriX + 85;
        for (int i = 0; i < llen; ++i) {
            vlen = this.dVal0(g, oriY - ranY - 3 - 15, vlen, this.ttVal[i], this.ccVal[i]);
            vlen = this.dVal0(g, oriY - ranY - 3 - 15, vlen, this.vvVal[i], this.ccVal[i]);
            vlen -= 5;
            vlen = this.dVal0(g, oriY - ranY - 3 - 15, vlen, uu, this.ccVal[i]);
        }
    }
    
    public void dBar(final Graphics g, final int oriY, final int ranY, final int rr, final float[] fVal, final int dd, final int cc, final int ww) {
        if (fVal.length <= dd) {
            return;
        }
        int preY = (int)(oriY - (0.0f - this.oriV[rr]) * ranY / this.ranV[rr]);
        int zeoB = 1;
        if (preY > oriY) {
            preY = oriY;
            zeoB = 0;
        }
        if (preY < oriY - ranY) {
            preY = oriY - ranY;
            zeoB = 0;
        }
        g.setColor(new Color(cc));
        for (int i = dd; i < fVal.length; ++i) {
            final int curX = this.oriX + (i * 2 + 1) * this.ranX / (2 * this.iDate.length);
            int curY;
            if (zeoB == 1) {
                curY = (int)(-fVal[i] * ranY / this.ranV[rr]) + preY;
            }
            else {
                curY = (int)(oriY - (fVal[i] - this.oriV[rr]) * ranY / this.ranV[rr]);
            }
            g.fillRect(curX, Math.min(curY, preY), ww, Math.abs(preY - curY) + 1);
        }
    }
    
    public String fStr(final float ff) {
        if (ff - (int)ff == 0.0f) {
            return "" + (int)ff;
        }
        return "" + ff;
    }
    
    public boolean mouseDown(final Event evt, final int x, final int y) {
        if (this.err == 0 && x >= this.oriX && x < this.oriX + this.ranX + 10) {
            this.cx1[this.curC] = 0;
            this.cy1[this.curC] = 0;
            if (x < this.oriX + 40 && y > this.bU[1] && y < this.bD[1]) {
                this.curZ = 1;
                this.bPop.show(this, this.oriX, this.bD[1] + 2);
            }
            else if (x < this.oriX + 40 && y > this.bU[2] && y < this.bD[2]) {
                this.curZ = 2;
                this.bPop.show(this, this.oriX, this.bD[2] + 2);
            }
            else if (x < this.oriX + 80 && y > this.bU[0] && y < this.bD[0]) {
                this.curZ = 0;
                this.curP = 0;
                this.iBox();
            }
            else if (x < this.oriX + 80 && y > this.bU[1] && y < this.bD[1]) {
                this.curZ = 1;
                this.curP = 0;
                this.iBox();
            }
            else if (x < this.oriX + 80 && y > this.bU[2] && y < this.bD[2]) {
                this.curZ = 2;
                this.curP = 0;
                this.iBox();
            }
            else {
                this.curI = (x - this.oriX) * this.iDate.length / this.ranX;
                if (this.curI >= this.iDate.length) {
                    this.curI = this.iDate.length - 1;
                }
                this.bpaint(this.dBuf);
                this.repaint();
                if (y <= this.oriY[0] && y >= this.oriY[0] - this.ranY[0]) {
                    this.cx1[this.curC] = x;
                    this.cy1[this.curC] = y;
                }
            }
        }
        else if (this.err == 100) {
            if (x >= this.icur + 20 && x <= this.icur + 35 && y > this.bD[this.curZ] + 10 && y <= this.bD[this.curZ] + 25 && this.ival < this.imax) {
                ++this.ival;
                this.dival();
            }
            else if (x >= this.icur + 20 && x <= this.icur + 35 && y > this.bD[this.curZ] + 30 && y <= this.bD[this.curZ] + 45 && this.ival > this.imin) {
                --this.ival;
                this.dival();
            }
            else if (x >= this.icur - 35 && x <= this.icur && y > this.bD[this.curZ] + 45 && y <= this.bD[this.curZ] + 62 && this.ival >= this.imin && this.ival <= this.imax) {
                this.dok();
            }
        }
        return true;
    }
    
    float[] parseF(final String s, final String sep) {
        final StringTokenizer st = new StringTokenizer(s, sep);
        final float[] result = new float[st.countTokens()];
        for (int i = 0; i < result.length; ++i) {
            result[i] = new Float(st.nextToken());
        }
        return result;
    }
    
    public void calAr(final int dd, final float[] fAr) {
        if (this.iDate.length < dd) {
            return;
        }
        for (int i = dd - 1; i < this.iDate.length; ++i) {
            float ho = 0.0f;
            float ol = 0.0f;
            for (int j = i; j > i - dd; --j) {
                ho += this.fOhlc[1][j] - this.fOhlc[0][j];
                ol += this.fOhlc[0][j] - this.fOhlc[2][j];
            }
            fAr[i] = ho * 100.0f / ol;
        }
        for (int i = 0; i < dd - 1; ++i) {
            fAr[i] = fAr[dd - 1];
        }
    }
    
    public void dPol(final Graphics g, final int xx, final int yU) {
        final int yD = yU + 14;
        g.setColor(new Color(13421772));
        g.fill3DRect(xx, yU, 15, 14, true);
        g.fill3DRect(xx + 1, yU + 1, 13, 12, true);
        this.polyX[0] = xx + 3;
        this.polyY[0] = (this.polyY[2] = yU + 3);
        this.polyX[2] = xx + 11;
        this.polyX[1] = xx + 7;
        this.polyY[1] = yD - 3;
        g.setColor(new Color(0));
        g.fillPolygon(this.polyX, this.polyY, 3);
        g.drawLine(this.polyX[2], this.polyY[2], this.polyX[2], this.polyY[2]);
    }
    
    public boolean action(final Event evt, final Object obj) {
        if (evt.target instanceof MenuItem) {
            for (int i = 1; i < this.sTech[0].length; ++i) {
                if (((String)obj).compareTo(this.sTech[0][i]) == 0) {
                    this.curT[this.curZ] = i;
                    for (int j = 0; j < 3; ++j) {
                        this.iZpar[j][this.curZ] = this.iTpar[j][i];
                    }
                    break;
                }
            }
            this.bpaint(this.dBuf);
            this.repaint();
        }
        return true;
    }
    
    public void dok() {
        this.iZpar[this.curP][this.curZ] = this.ival;
        ++this.curP;
        if (this.curP == 3 || this.iTpar[this.curP][this.curT[this.curZ]] == 0) {
            this.err = 0;
            this.bpaint(this.dBuf);
            this.repaint();
        }
        else {
            this.iBox();
        }
    }
    
    public void run() {
        final Thread me = Thread.currentThread();
        while (this.runner == me) {
            if (this.err == 100) {
                this.dCur();
            }
            else if (this.err == 111) {
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
        this.vFont = new Font("\u7d30\u660e\u9ad4", 0, 12);
        this.vMetrics = this.getFontMetrics(this.vFont);
        this.setBackground(new Color(16777215));
        this.offScr = this.createImage(this.size().width, this.size().height);
        (this.dBuf = this.offScr.getGraphics()).setColor(new Color(16777215));
        this.dBuf.fillRect(0, 0, this.size().width, this.size().height);
        this.dBuf.setFont(this.vFont);
        this.BCD = this.getParameter("BCD");
        this.inVal();
    }
    
    public void dLine(final Graphics g, final int oriY, final int ranY, final int rr, final float[] fVal, final int dd, final int ee, final int cc) {
        if (ee <= dd) {
            return;
        }
        int preX = this.oriX + (dd * 2 + 1) * this.ranX / (2 * this.iDate.length);
        int preY = (int)(oriY - (fVal[dd] - this.oriV[rr]) * ranY / this.ranV[rr]);
        g.setColor(new Color(cc));
        for (int i = dd; i < ee; ++i) {
            final int curX = this.oriX + (i * 2 + 1) * this.ranX / (2 * this.iDate.length);
            final int curY = (int)(oriY - (fVal[i] - this.oriV[rr]) * ranY / this.ranV[rr]);
            g.drawLine(preX, preY, curX, curY);
            preX = curX;
            preY = curY;
        }
    }
    
    public void dival() {
        final String sss = (this.ival == 0) ? "" : ("" + this.ival);
        this.dBuf.setColor(new Color(13421772));
        this.dBuf.fillRect(this.icur, this.bD[this.curZ] + 10, 20, 25);
        this.dBuf.setColor(new Color(0));
        this.dBuf.drawString(sss, this.icur, this.bD[this.curZ] + 30);
        this.fCur = 0;
        this.dCur();
        this.repaint();
    }
    
    public boolean mouseDrag(final Event evt, final int x, final int y) {
        if (this.err == 0 && this.cx1[this.curC] != 0) {
            this.cxt[this.curC] = this.cx2[this.curC];
            this.cyt[this.curC] = this.cy2[this.curC];
            this.cx2[this.curC] = x;
            this.cy2[this.curC] = y;
            if (this.cx2[this.curC] < this.oriX) {
                this.cy2[this.curC] = this.cy1[this.curC] - (this.cx1[this.curC] - this.oriX) * (this.cy1[this.curC] - this.cy2[this.curC]) / (this.cx1[this.curC] - this.cx2[this.curC]);
                this.cx2[this.curC] = this.oriX;
            }
            else if (this.cx2[this.curC] > this.oriX + this.ranX + 10) {
                this.cy2[this.curC] = this.cy1[this.curC] - (this.cx1[this.curC] - this.oriX - this.ranX - 10) * (this.cy1[this.curC] - this.cy2[this.curC]) / (this.cx1[this.curC] - this.cx2[this.curC]);
                this.cx2[this.curC] = this.oriX + this.ranX + 10;
            }
            if (this.cy2[this.curC] > this.oriY[0]) {
                this.cx2[this.curC] = this.cx1[this.curC] - (this.cy1[this.curC] - this.oriY[0]) * (this.cx1[this.curC] - this.cx2[this.curC]) / (this.cy1[this.curC] - this.cy2[this.curC]);
                this.cy2[this.curC] = this.oriY[0];
            }
            else if (this.cy2[this.curC] < this.oriY[0] - this.ranY[0]) {
                this.cx2[this.curC] = this.cx1[this.curC] - (this.cy1[this.curC] - this.oriY[0] + this.ranY[0]) * (this.cx1[this.curC] - this.cx2[this.curC]) / (this.cy1[this.curC] - this.cy2[this.curC]);
                this.cy2[this.curC] = this.oriY[0] - this.ranY[0];
            }
            this.cpaint(this.dBuf);
            this.repaint();
        }
        return true;
    }
    
    public void inVal() {
        this.err = 0;
        this.sTn[0] = "\u79fb\u52d5\u5e73\u5747\u7dda,\u6210\u4ea4\u91cf,RSI,KD,MACD,OBV,AR,BR,VR,\u4e56\u96e2\u7387,3-6\u4e56\u96e2,MTM,\u5a01\u5ec9\u6307\u6a19,DMI,TAPI,\u5fc3\u7406\u7dda";
        this.sTn[1] = "MA,\u5747\u91cf,RSI,K,DIF,OBV,AR,BR,VR,\u4e56\u96e2\u7387,\u4e56\u96e2,MTM,\u5a01\u5ec9\u6307\u6a19,+DI,TAPI,\u5fc3\u7406\u7dda";
        this.sTn[2] = "MA,\u5747\u91cf,RSI,D,MACD, , , , ,\u4e56\u96e2\u7387, , , ,-DI, ,\u5fc3\u7406\u7dda";
        this.sTn[3] = "MA, , , ,OSC, , , , ,\u4e56\u96e2\u7387, , , ,ADX, , ";
        this.sTp[0] = "5,5,6,9,12,0,26,26,26,6,3,10,14,14,0,12";
        this.sTp[1] = "10,10,12,0,26,0,0,0,0,12,6,0,0,0,0,24";
        this.sTp[2] = "20,0,0,0,9,0,0,0,0,24,0,0,0,0,0,0";
        this.sTi[0] = "1,1,1,1,1,0,2,2,2,2,2,2,2,2,0,2";
        this.sTi[1] = "-1,-1,-1,0,-1,0,0,0,0,-1,-1,0,0,0,0,-1";
        this.sTi[2] = "-1,0,0,0,1,0,0,0,0,-1,0,0,0,0,0,0";
        this.sTa[0] = "30,30,30,40,30,0,40,40,40,30,30,40,40,40,0,30";
        this.sTa[1] = "40,40,40,0,60,0,0,0,0,40,40,0,0,0,0,40";
        this.sTa[2] = "50,0,0,0,20,0,0,0,0,50,0,0,0,0,0,0";
        this.curT[0] = 0;
        this.curT[1] = 1;
        this.curT[2] = 3;
        for (int i = 0; i < 6; ++i) {
            this.cx2[i] = 0;
        }
        this.curC = 0;
        String strIn = "";
        try {
            final URL myURL = new URL(this.getCodeBase(), this.BCD);
            final URLConnection urc = myURL.openConnection();
            urc.setUseCaches(false);
            final InputStream in = urc.getInputStream();
            int b;
            while ((b = in.read()) != -1) {
                if (b >= 65) {
                    this.err = 1;
                }
                strIn += (char)b;
            }
        }
        catch (Exception e) {
            this.err = 2;
        }
        strIn = strIn.trim();
        if (strIn.length() < 5) {
            this.err += 10;
        }
        this.strT = this.parse(this.getParameter("T"), " ");
        this.strU = this.parse(this.getParameter("U"), " ");
        final String ppp = "Pending";
        if (this.err == 1 && strIn.substring(0, 7).compareTo(ppp) == 0) {
            this.err = 111;
            this.pend = Integer.parseInt(strIn.substring(7)) * 1000;
        }
        if (this.err == 0) {
            final StringTokenizer st = new StringTokenizer(strIn, " ");
            if (this.strU[0].charAt(0) == 'H') {
                this.iDay = Integer.parseInt(st.nextToken());
            }
            else {
                this.iDay = 0;
            }
            this.iDate = this.parseI(st.nextToken(), ",");
            this.fOhlc[0] = this.parseF(st.nextToken(), ",");
            this.fOhlc[1] = this.parseF(st.nextToken(), ",");
            this.fOhlc[2] = this.parseF(st.nextToken(), ",");
            this.fOhlc[3] = this.parseF(st.nextToken(), ",");
            int idxP = 1;
            if (st.countTokens() > 0) {
                this.fVol[0] = this.parseF(st.nextToken(), ",");
            }
            else {
                this.curT[1] = 2;
                idxP = 2;
            }
            int fob = 1;
            if (st.countTokens() > 1) {
                this.fVol[fob] = this.parseF(st.nextToken(), ",");
                ++fob;
                this.fVol[fob] = this.parseF(st.nextToken(), ",");
                ++fob;
                this.sTn[0] += ",\u878d\u8cc7\u9918\u984d,\u878d\u5238\u9918\u984d";
                this.sTn[1] += ",\u5747\u91cf,\u5747\u91cf";
                this.sTn[2] += ",\u5747\u91cf,\u5747\u91cf";
                this.sTn[3] += ", , ";
                this.sTp[0] += ",6,6";
                this.sTp[1] += ",12,12";
                this.sTp[2] += ",0,0";
                this.sTi[0] += ",1,1";
                this.sTi[1] += ",-1,-1";
                this.sTi[2] += ",0,0";
                this.sTa[0] += ",30,30";
                this.sTa[1] += ",40,40";
                this.sTa[2] += ",0,0";
            }
            if (st.countTokens() == 1) {
                this.fVol[fob] = this.parseF(st.nextToken(), ",");
                this.sTn[0] += ",\u5916\u8cc7\u6301\u80a1";
                this.sTn[1] += ",\u5747\u91cf";
                this.sTn[2] += ",\u5747\u91cf";
                this.sTn[3] += ", ";
                this.sTp[0] += ",6";
                this.sTp[1] += ",12";
                this.sTp[2] += ",0";
                this.sTi[0] += ",1";
                this.sTi[1] += ",-1";
                this.sTi[2] += ",0";
                this.sTa[0] += ",30";
                this.sTa[1] += ",40";
                this.sTa[2] += ",0";
            }
            this.curI = this.iDate.length - 1;
            this.calMaxMin(this.fOhlc[1], this.fOhlc[2], 0, 0);
            this.calYval(0, 10);
            for (int j = 0; j < 4; ++j) {
                this.fTech[j] = new float[this.iDate.length];
                this.sTech[j] = this.parse(this.sTn[j], ",");
            }
            for (int j = 0; j < 3; ++j) {
                this.iTpar[j] = this.parseI(this.sTp[j], ",");
                this.iTmin[j] = this.parseI(this.sTi[j], ",");
                this.iTmax[j] = this.parseI(this.sTa[j], ",");
                this.iZpar[j] = new int[3];
            }
            this.bPop.removeAll();
            this.add(this.bPop);
            for (int j = idxP; j < this.sTech[0].length; ++j) {
                if (idxP != 2 || (j != 5 && j != 8 && j != 14)) {
                    final MenuItem tmpItem = new MenuItem(this.sTech[0][j]);
                    tmpItem.setFont(this.vFont);
                    this.bPop.add(tmpItem);
                }
            }
            for (int j = 0; j < 3; ++j) {
                for (int k = 0; k < 3; ++k) {
                    this.iZpar[k][j] = this.iTpar[k][this.curT[j]];
                }
            }
        }
        this.bpaint(this.dBuf);
    }
}
