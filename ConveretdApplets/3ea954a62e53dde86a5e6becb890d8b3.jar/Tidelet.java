import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Button;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Tidelet extends Applet implements ActionListener, MouseListener, MouseMotionListener
{
    public String m_sData;
    private final String PARAM_sLocation = "Location";
    private int m_nWidth;
    private int m_nHeight;
    private int m_nIndex;
    private String s1;
    private String s2;
    private String s3;
    private String sZone;
    public Button m_butUnits;
    public Button m_butTomorrow;
    public Button m_butYesterday;
    private String sUnits;
    private FontMetrics m_fm;
    private int xWinOrg;
    private int yWinOrg;
    private int xWinExt;
    private int yWinExt;
    private int xGraphMax;
    private int xGraphMin;
    private int yGraphMax;
    private int yGraphMin;
    private int xWinOfGraphOrg;
    private int yWinOfGraphOrg;
    private int xWinOfGraphExt;
    private int yWinOfGraphExt;
    private int nRowSpace;
    private int nLineSpace;
    private int nCharSpace;
    private int nIndent;
    private int xStep;
    private int yStep;
    private int ySubStep;
    private int nLeftOffset;
    private int nTick;
    private int nDaysInMonth;
    private double dMax;
    private double dMin;
    private Color cream;
    private Color LightBlue;
    private Color LightGreen;
    private Color green;
    private Graphics m_Graphics;
    private Font m_Font10;
    private Font m_Font12;
    private boolean bMetric;
    private double[] dT_ht;
    private int nYr;
    private int nMn;
    private int nDy;
    private int nMinYear;
    private int nMaxYear;
    private String sContact;
    private double[] Hcon;
    private static TideMath tm;
    
    public Tidelet() {
        this.m_sData = "";
        this.m_nWidth = 470;
        this.m_nHeight = 350;
        this.m_nIndex = 0;
        this.s1 = "Metres/Feet";
        this.s2 = "Tomorrow";
        this.s3 = "Yesterday";
        this.m_butUnits = new Button(this.s1);
        this.m_butTomorrow = new Button(this.s2);
        this.m_butYesterday = new Button(this.s3);
        this.cream = new Color(255, 251, 240);
        this.LightBlue = new Color(166, 202, 240);
        this.LightGreen = new Color(192, 220, 192);
        this.green = new Color(0, 128, 0);
        this.m_Font10 = new Font("Arial", 0, 10);
        this.m_Font12 = new Font("Arial", 0, 12);
        this.dT_ht = new double[146];
        this.nMinYear = 1990;
        this.nMaxYear = 2050;
        this.sContact = "www.pangolin.co.nz";
        this.Hcon = new double[14];
    }
    
    public String getAppletInfo() {
        return "Version 1.02\r\nName: TidLet\r\n(C) Feb 2011\r\nAuthor: Mike Harris\r\nPangolin\r\nsupport@pangolin.co.nz";
    }
    
    public String[][] ParameterInfo() {
        final String[][] info = { { "Location", "String", "Location data" } };
        return info;
    }
    
    public void init() {
        final String s = "";
        final String param = this.getParameter("Location");
        if (param != null) {
            this.m_sData = param;
        }
        this.resize(this.m_nWidth, this.m_nHeight);
        this.xWinOrg = 0;
        this.yWinOrg = 0;
        this.xWinExt = this.m_nWidth;
        this.yWinExt = this.m_nHeight;
        this.xGraphMax = 24;
        this.xGraphMin = 0;
        this.xWinOfGraphOrg = this.xWinExt / 9;
        this.yWinOfGraphOrg = this.yWinExt / 6 * 5;
        this.xWinOfGraphExt = (int)(this.xWinExt * 0.95);
        this.yWinOfGraphExt = this.yWinExt / 6;
        this.nIndent = 40;
        this.xStep = 2;
        this.yStep = 1;
        this.ySubStep = 0;
        this.nTick = this.yWinExt / 100;
        this.add(this.m_butUnits);
        this.m_butUnits.addActionListener(this);
        this.add(this.m_butTomorrow);
        this.m_butTomorrow.addActionListener(this);
        this.add(this.m_butYesterday);
        this.m_butYesterday.addActionListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.SetLocation();
    }
    
    public void SetLocation() {
        final String sHcon = JBackward(this.m_sData.substring(22));
        this.sZone = sHcon.substring(0, 6);
        this.Hcon[0] = Double.valueOf(sHcon.substring(6, 10));
        this.Hcon[1] = Double.valueOf(sHcon.substring(10, 13));
        this.Hcon[2] = Double.valueOf(sHcon.substring(13, 17));
        this.Hcon[3] = Double.valueOf(sHcon.substring(17, 20));
        this.Hcon[4] = Double.valueOf(sHcon.substring(20, 24));
        this.Hcon[5] = Double.valueOf(sHcon.substring(24, 27));
        this.Hcon[6] = Double.valueOf(sHcon.substring(27, 31));
        this.Hcon[7] = Double.valueOf(sHcon.substring(31, 34));
        this.Hcon[8] = Double.valueOf(sHcon.substring(34, 38));
        this.Hcon[9] = Double.valueOf(sHcon.substring(38, 41));
        this.Hcon[10] = Double.valueOf(sHcon.substring(41, 46));
        this.Hcon[11] = Double.valueOf(sHcon.substring(46, 49));
        this.Hcon[12] = Double.valueOf(sHcon.substring(49, 54));
        this.bMetric = true;
        Tidelet.tm = new TideMath(this.Hcon, this.bMetric, 1);
        final Calendar Today = Calendar.getInstance();
        this.nYr = Today.get(1);
        this.nMn = Today.get(2) + 1;
        this.nDy = Today.get(5);
        this.LoadThisTideData();
    }
    
    private void FixDate() {
        this.MonthName(this.nMn, this.nYr);
        if (this.nDy > this.nDaysInMonth) {
            this.nDy = 1;
            ++this.nMn;
            if (this.nMn > 12) {
                this.nMn = 1;
                ++this.nYr;
            }
        }
        if (this.nDy < 1) {
            if (--this.nMn < 1) {
                --this.nYr;
                this.nMn = 12;
                this.nDy = 31;
            }
            else {
                this.MonthName(this.nMn, this.nYr);
                this.nDy = this.nDaysInMonth;
            }
        }
    }
    
    public void LoadThisTideData() {
        this.FixDate();
        final TideMath tm = new TideMath(this.Hcon, this.bMetric, 1);
        this.dMax = -100.0;
        this.dMin = 100.0;
        for (int a = 0; a <= 145; ++a) {
            this.dT_ht[a] = tm.TideHeight(this.nYr, this.nMn, this.nDy, 0, a * 10, 0);
            if (this.dMax < this.dT_ht[a]) {
                this.dMax = this.dT_ht[a];
            }
            if (this.dMin > this.dT_ht[a]) {
                this.dMin = this.dT_ht[a];
            }
        }
        this.yGraphMax = (int)Math.ceil(this.dMax);
        this.yGraphMin = (int)Math.floor(this.dMin);
    }
    
    private static String JBackward(final String inString) {
        final int b = inString.length();
        String Char = "";
        String OutString = "";
        for (int a = 0; a < inString.length(); ++a) {
            if (inString.charAt(a) == 'H') {
                Char = "5";
            }
            if (inString.charAt(a) == 'O') {
                Char = " ";
            }
            if (inString.charAt(a) == 'E') {
                Char = "9";
            }
            if (inString.charAt(a) == 'Z') {
                Char = "3";
            }
            if (inString.charAt(a) == 'C') {
                Char = "4";
            }
            if (inString.charAt(a) == 'A') {
                Char = "6";
            }
            if (inString.charAt(a) == 'R') {
                Char = "8";
            }
            if (inString.charAt(a) == 'V') {
                Char = "2";
            }
            if (inString.charAt(a) == 'P') {
                Char = "-";
            }
            if (inString.charAt(a) == 'F') {
                Char = "0";
            }
            if (inString.charAt(a) == 'B') {
                Char = ".";
            }
            if (inString.charAt(a) == 'N') {
                Char = "+";
            }
            if (inString.charAt(a) == 'M') {
                Char = "1";
            }
            if (inString.charAt(a) == 'I') {
                Char = "7";
            }
            OutString += Char;
        }
        return OutString;
    }
    
    private String MonthName(final int mn, final int yr) {
        switch (mn) {
            case 1: {
                this.nDaysInMonth = 31;
                return "January";
            }
            case 2: {
                this.nDaysInMonth = 28;
                if (yr / 4.0 == Math.floor(yr / 4.0)) {
                    this.nDaysInMonth = 29;
                }
                return "February";
            }
            case 3: {
                this.nDaysInMonth = 31;
                return "March";
            }
            case 4: {
                this.nDaysInMonth = 30;
                return "April";
            }
            case 5: {
                this.nDaysInMonth = 31;
                return "May";
            }
            case 6: {
                this.nDaysInMonth = 30;
                return "June";
            }
            case 7: {
                this.nDaysInMonth = 31;
                return "July";
            }
            case 8: {
                this.nDaysInMonth = 31;
                return "August";
            }
            case 9: {
                this.nDaysInMonth = 30;
                return "September";
            }
            case 10: {
                this.nDaysInMonth = 31;
                return "October";
            }
            case 11: {
                this.nDaysInMonth = 30;
                return "November";
            }
            case 12: {
                this.nDaysInMonth = 31;
                return "December";
            }
            default: {
                this.nDaysInMonth = 0;
                return " ";
            }
        }
    }
    
    public void destroy() {
    }
    
    public void start() {
    }
    
    public void stop() {
    }
    
    public void paint(final Graphics g) {
        this.m_Graphics = this.getGraphics();
        this.sUnits = "Height in ";
        if (this.bMetric) {
            this.sUnits += "metres";
        }
        else {
            this.sUnits += "feet";
        }
        if (this.nYr < this.nMinYear || this.nYr > this.nMaxYear) {
            g.drawString("Validity period expired! Contact: " + this.sContact, this.nLeftOffset, 5 * this.nRowSpace);
            return;
        }
        this.m_fm = this.getFontMetrics(this.m_Font12);
        this.nRowSpace = this.m_fm.getHeight();
        this.nLineSpace = this.m_fm.getHeight();
        this.nCharSpace = this.m_fm.charWidth('m');
        g.setColor(this.cream);
        g.fillRect(0, 0, this.xWinExt, this.yWinExt);
        g.setColor(Color.white);
        g.fillRect(this.xWin(0.0), this.yWinOfGraphExt, this.xWinOfGraphExt - this.xWinOfGraphOrg, this.yWinOfGraphOrg - this.yWinOfGraphExt);
        g.setColor(Color.black);
        g.drawRect(this.xWin(0.0), this.yWinOfGraphExt, this.xWinOfGraphExt - this.xWinOfGraphOrg, this.yWinOfGraphOrg - this.yWinOfGraphExt);
        g.setColor(this.green);
        g.setFont(this.m_Font10);
        g.drawString(this.sContact, 5, 20);
        g.drawString("Pangolin", this.xWinExt - this.nCharSpace * 9, 20);
        g.setColor(Color.black);
        g.setFont(this.m_Font12);
        this.nLeftOffset = this.xWinOfGraphOrg;
        String buf = this.m_sData.substring(0, 22) + "     Time zone: " + this.sZone + "     Date: " + this.MonthName(this.nMn, this.nYr) + "/" + this.nDy + "/" + this.nYr;
        g.drawString(buf, this.xWinOfGraphOrg, this.yWinOfGraphExt - 1 * this.nRowSpace / 2);
        int b = 0;
        final String[] xLabel = { "  ", " 2", " 4", " 6", " 8", "10", "12", "14", "16", "18", "20", "22", "24" };
        for (int a = 0; a <= this.xGraphMax; ++a) {
            g.setColor(this.LightBlue);
            if (a > 0 && a < this.xGraphMax) {
                g.drawLine(this.xWin(a), this.yWinOfGraphOrg, this.xWin(a), this.yWinOfGraphExt);
            }
            g.setColor(Color.black);
            if (a > 0) {
                g.drawLine(this.xWin(a), this.yWinOfGraphOrg, this.xWin(a), this.yWinOfGraphOrg + 2 * this.nTick);
            }
            final double dTmp = a;
            if (dTmp / this.xStep - (int)(dTmp / this.xStep) < 0.01) {
                final String buf2 = xLabel[b++];
                g.drawString(buf2, this.xWin(a), this.yWinOfGraphOrg + 6 * this.nTick);
            }
        }
        buf = "Hours (zone time)";
        g.drawString(buf, this.nLeftOffset = (this.xWinExt - this.m_fm.stringWidth(buf)) / 2, this.yWinOfGraphOrg + 13 * this.nTick);
        buf = "(Remember to correct for Daylight saving time)";
        g.drawString(buf, this.nLeftOffset = (this.xWinExt - this.m_fm.stringWidth(buf)) / 2, this.yWinOfGraphOrg + 13 * this.nTick + this.nLineSpace);
        for (int a = this.yGraphMin; a <= this.yGraphMax; a += this.yStep) {
            if (a > this.yGraphMin && a < this.yGraphMax) {
                g.setColor(this.LightBlue);
                g.drawLine(this.xWinOfGraphOrg, this.yWin(a), this.xWinOfGraphExt - 3, this.yWin(a));
            }
            if (a < this.yGraphMax) {
                for (b = 1; b <= this.ySubStep; ++b) {
                    g.setColor(this.LightGreen);
                    g.drawLine(this.xWinOfGraphOrg + 4, this.yWin(a + b / this.ySubStep), this.xWinOfGraphExt - 4, this.yWin(a + b / this.ySubStep));
                }
            }
            if (a > this.yGraphMin) {
                g.setColor(Color.black);
                g.drawLine(this.xWinOfGraphOrg, this.yWin(a), this.xWinOfGraphOrg - 2 * this.nTick, this.yWin(a));
            }
            g.drawString(String.valueOf(a), this.xWinOfGraphOrg - 6 * this.nTick, this.yWin(a));
        }
        for (int a = 0; a < this.sUnits.length(); ++a) {
            buf = this.sUnits.charAt(a) + "";
            final int wd = this.m_fm.charWidth(this.sUnits.charAt(a)) / 2;
            g.drawString(buf, this.xWinOfGraphOrg - 13 * this.nTick - wd, (int)(this.yWinOfGraphExt + a * this.nRowSpace * 0.78));
        }
        g.setColor(Color.blue);
        for (double da = 1.0; da < 145.0; ++da) {
            g.drawLine(this.xWin(da / 6.0), this.yWin(this.yGraphMin), this.xWin(da / 6.0), this.yWin(this.dT_ht[(int)da]));
        }
    }
    
    private int xWin(final double xGraph) {
        final double dRange = this.xGraphMax - this.xGraphMin;
        final double xScaleFac = (this.xWinOfGraphExt - this.xWinOfGraphOrg) / dRange;
        return (int)(this.xWinOfGraphOrg + xGraph * xScaleFac);
    }
    
    private int yWin(final double yGraph) {
        final double nOffSet = 0.0 - this.yGraphMin;
        final double dRange = this.yGraphMax - this.yGraphMin;
        final double yScaleFac = (this.yWinOfGraphOrg - this.yWinOfGraphExt) / dRange;
        return (int)(this.yWinOfGraphOrg - yGraph * yScaleFac - nOffSet * yScaleFac);
    }
    
    public void actionPerformed(final ActionEvent ev) {
        if (ev.getSource() == this.m_butUnits) {
            if (this.bMetric) {
                this.bMetric = false;
            }
            else {
                this.bMetric = true;
            }
        }
        if (ev.getSource() == this.m_butTomorrow) {
            ++this.nDy;
        }
        if (ev.getSource() == this.m_butYesterday) {
            --this.nDy;
        }
        this.LoadThisTideData();
        this.repaint();
    }
    
    public void mousePressed(final MouseEvent ev) {
    }
    
    public void mouseReleased(final MouseEvent ev) {
    }
    
    public void mouseEntered(final MouseEvent ev) {
    }
    
    public void mouseExited(final MouseEvent ev) {
    }
    
    public void mouseClicked(final MouseEvent ev) {
        final int x = ev.getX();
        final int y = ev.getY();
        if (this.nYr < this.nMinYear || this.nYr > this.nMaxYear) {
            return;
        }
        if (x < this.xWinOfGraphOrg || x > this.xWinOfGraphExt || y < this.yWinOfGraphExt || y > this.yWinOfGraphOrg) {
            return;
        }
        final double hours = (x - this.xWinOfGraphOrg) * ((this.xGraphMax - this.xGraphMin) / (this.xWinOfGraphExt - this.xWinOfGraphOrg));
        final double fd1 = Tidelet.tm.TideHeight(this.nYr, this.nMn, this.nDy, 0, (int)(hours * 60.0), 0);
        this.m_Graphics.setColor(Color.red);
        this.m_Graphics.drawLine(this.xWinOfGraphOrg, this.yWin(fd1), this.xWinOfGraphExt, this.yWin(fd1));
    }
    
    public void mouseDragged(final MouseEvent ev) {
    }
    
    public void mouseMoved(final MouseEvent ev) {
        final int x = ev.getX();
        final int y = ev.getY();
        if (this.nYr < this.nMinYear || this.nYr > this.nMaxYear) {
            return;
        }
        if (x < this.xWinOfGraphOrg || x > this.xWinOfGraphExt || y < this.yWinOfGraphExt || y > this.yWinOfGraphOrg) {
            this.m_Graphics.setColor(this.cream);
            this.m_Graphics.fillRect(this.xWinOfGraphOrg - 15 * this.nTick, this.yWinOfGraphOrg + 12 * this.nTick, this.nCharSpace * 8, 2 * this.nLineSpace);
            return;
        }
        final double hours = (x - this.xWinOfGraphOrg) * ((this.xGraphMax - this.xGraphMin) / (this.xWinOfGraphExt - this.xWinOfGraphOrg));
        final int hrs = (int)Math.abs(hours);
        final int mins = (int)(60.0 * (hours - Math.floor(hours)));
        String tmp0 = "";
        String tmp2 = "";
        tmp0 = String.valueOf(hrs);
        if (tmp0.length() < 2) {
            tmp0 = "0" + tmp0;
        }
        tmp2 = String.valueOf(mins);
        if (tmp2.length() < 2) {
            tmp2 = "0" + tmp2;
        }
        final double fd1 = Tidelet.tm.TideHeight(this.nYr, this.nMn, this.nDy, 0, (int)(hours * 60.0), 0);
        String tmp3 = "";
        tmp3 = String.valueOf(fd1);
        final int b = tmp3.indexOf(46);
        tmp3 = tmp3.substring(0, b + 3);
        this.m_Graphics.setColor(this.cream);
        this.m_Graphics.fillRect(this.xWinOfGraphOrg - 15 * this.nTick, this.yWinOfGraphOrg + 12 * this.nTick, this.nCharSpace * 8, 2 * this.nLineSpace);
        this.m_Graphics.setColor(Color.black);
        this.m_Graphics.drawString("Time     Ht", this.xWinOfGraphOrg - 15 * this.nTick, this.yWinOfGraphOrg + 12 * this.nTick);
        this.m_Graphics.setColor(Color.red);
        this.m_Graphics.drawString(tmp0 + tmp2 + "   " + tmp3, this.xWinOfGraphOrg - 15 * this.nTick, this.yWinOfGraphOrg + 17 * this.nTick);
    }
}
