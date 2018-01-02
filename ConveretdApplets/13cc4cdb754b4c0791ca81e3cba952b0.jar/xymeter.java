import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.util.StringTokenizer;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class xymeter extends Applet implements Runnable
{
    Thread runner;
    int delayGlb;
    int Mhe;
    int Mwi;
    int Hhe;
    int Hhemax;
    int Ahe;
    int borderY0;
    int borderY1;
    int borderX0;
    int borderX1;
    int wborder_l;
    int wborder_r;
    int hborder;
    int xaroot;
    int yaroot;
    int xs0;
    int ys0;
    int xs1;
    int ys1;
    int xs2;
    int ys2;
    int XMarkerSpace;
    int XMarkerLength;
    int YMarkerSpace;
    int YMarkerLength;
    double YMin;
    double YMax;
    double DeltaY;
    double yfak;
    double DeltaX;
    double XMin;
    double XMax;
    double XPeriod;
    double xfak;
    boolean WHeader;
    boolean WXTitle;
    boolean WYTitle;
    boolean WFloatingX;
    boolean XYPairs;
    String Header;
    String XTitle;
    String YTitle;
    String iStr;
    boolean CHANGED;
    boolean ALLPOINTS;
    Color HBGColor;
    Color HFGColor;
    Color ABGColor;
    Color AFGColor;
    Color[] LINColor;
    Image oimg;
    Graphics og;
    int maxLines;
    int maxPairs;
    double[] YArr;
    double[] XArr;
    int[] Index;
    int[] IBegin;
    int[] IEnd;
    double[] YNew;
    double[] YOld;
    double[] XNew;
    double[] XOld;
    boolean Top0;
    boolean Bottom0;
    boolean Left0;
    boolean Right0;
    boolean Top1;
    boolean Bottom1;
    boolean Left1;
    boolean Right1;
    int xc0;
    int yc0;
    int xc1;
    int yc1;
    
    public String getAppletInfo() {
        return "xymeter.java, V 1.0 07.97 by Ralf Moros / JaR, http://cerius.tachemie.uni-leipzig.de/~jar/";
    }
    
    public void InitGlobalPar1() {
        if (this.WHeader) {
            this.Hhe = this.Mhe / 6;
            if (this.Hhe > this.Hhemax) {
                this.Hhe = this.Hhemax;
            }
        }
        else {
            this.Hhe = 0;
        }
        this.Ahe = this.Mhe - this.Hhe;
        this.xaroot = 0;
        this.yaroot = this.Hhe + this.Ahe - 1;
        this.wborder_l = this.Mwi / 8;
        this.wborder_r = this.Mwi / 12;
        this.hborder = this.Ahe / 10;
        this.xs0 = this.xaroot + this.wborder_l;
        this.ys0 = this.yaroot - this.hborder;
        this.xs1 = this.Mwi - this.wborder_r;
        this.ys1 = this.ys0;
        this.xs2 = this.xs0;
        this.ys2 = this.yaroot - 9 * this.hborder;
        this.XMarkerSpace = (this.xs1 - this.xs0) / 10;
        this.XMarkerLength = 5;
        if (this.XMarkerLength > this.hborder / 2) {
            this.XMarkerLength = this.hborder / 2;
        }
        this.YMarkerSpace = (this.ys0 - this.ys2) / 10;
        this.YMarkerLength = 5;
        if (this.YMarkerLength > this.wborder_l / 2) {
            this.YMarkerLength = this.wborder_l / 2;
        }
        this.borderX0 = this.xs0;
        this.borderX1 = this.xs0 + 10 * this.XMarkerSpace;
        this.borderY0 = this.ys0;
        this.borderY1 = this.ys0 - 10 * this.YMarkerSpace;
        this.xfak = (this.borderX1 - this.borderX0) / this.DeltaX;
        this.yfak = (this.borderY0 - this.borderY1) / this.DeltaY;
    }
    
    public void init() {
        this.HBGColor = Color.lightGray;
        this.ABGColor = Color.lightGray;
        this.HFGColor = Color.black;
        this.AFGColor = Color.black;
        this.iStr = this.getParameter("header");
        if (this.iStr != null) {
            this.WHeader = true;
            this.Header = this.iStr;
        }
        else {
            this.WHeader = false;
            this.Header = "";
        }
        this.iStr = this.getParameter("xtitle");
        if (this.iStr != null) {
            this.XTitle = this.iStr;
            this.WXTitle = true;
        }
        else {
            this.XTitle = "";
            this.WXTitle = false;
        }
        this.iStr = this.getParameter("ytitle");
        if (this.iStr != null) {
            this.YTitle = this.iStr;
            this.WYTitle = true;
        }
        else {
            this.YTitle = "";
            this.WYTitle = false;
        }
        this.iStr = this.getParameter("onlyy");
        if (this.iStr != null) {
            if (this.iStr.equals("false")) {
                this.XYPairs = true;
            }
            else {
                this.XYPairs = false;
            }
        }
        else {
            this.XYPairs = true;
        }
        this.iStr = this.getParameter("yfrom");
        if (this.iStr != null) {
            this.YMin = Float.valueOf(this.iStr);
        }
        else {
            this.YMin = 0.0;
        }
        this.iStr = this.getParameter("yto");
        if (this.iStr != null) {
            this.YMax = Float.valueOf(this.iStr);
        }
        else {
            this.YMax = 100.0;
        }
        if (this.YMax >= 0.0 & this.YMin >= 0.0) {
            this.DeltaY = this.YMax - this.YMin;
        }
        else if (this.YMax >= 0.0 & this.YMin < 0.0) {
            this.DeltaY = this.YMax - this.YMin;
        }
        else {
            this.DeltaY = Math.abs(this.YMin - this.YMax);
        }
        this.iStr = this.getParameter("xfrom");
        if (this.iStr != null) {
            this.XMin = Float.valueOf(this.iStr);
        }
        else {
            this.XMin = 0.0;
        }
        this.iStr = this.getParameter("xto");
        if (this.iStr != null) {
            this.XMax = Float.valueOf(this.iStr);
        }
        else {
            this.XMax = 100.0;
        }
        if (this.XMax >= 0.0 & this.XMin >= 0.0) {
            this.DeltaX = this.XMax - this.XMin;
        }
        else if (this.XMax >= 0.0 & this.XMin < 0.0) {
            this.DeltaX = this.XMax - this.XMin;
        }
        else {
            this.DeltaX = Math.abs(this.XMin - this.XMax);
        }
        this.iStr = this.getParameter("xperiod");
        if (this.iStr != null) {
            this.XPeriod = Float.valueOf(this.iStr);
        }
        else {
            this.XPeriod = 1.0;
        }
        this.iStr = this.getParameter("xypairs");
        if (this.iStr != null) {
            this.maxPairs = Integer.valueOf(this.iStr);
        }
        else {
            this.maxPairs = 100;
        }
        this.iStr = this.getParameter("hbgcol");
        if (this.iStr != null) {
            this.HBGColor = this.WhichColor(this.HBGColor, this.iStr);
        }
        this.iStr = this.getParameter("hfgcol");
        if (this.iStr != null) {
            this.HFGColor = this.WhichColor(this.HFGColor, this.iStr);
        }
        this.iStr = this.getParameter("abgcol");
        if (this.iStr != null) {
            this.ABGColor = this.WhichColor(this.ABGColor, this.iStr);
        }
        this.iStr = this.getParameter("afgcol");
        if (this.iStr != null) {
            this.AFGColor = this.WhichColor(this.AFGColor, this.iStr);
        }
        this.iStr = this.getParameter("maxlin");
        if (this.iStr != null) {
            this.maxLines = Integer.valueOf(this.iStr);
        }
        else {
            this.maxLines = 1;
        }
        this.LINColor = new Color[this.maxLines];
        for (int i = 0; i < this.maxLines; ++i) {
            this.LINColor[i] = Color.blue;
        }
        this.iStr = this.getParameter("lincol");
        if (this.iStr != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.iStr, ",");
            for (int j = 0; j < this.maxLines; ++j) {
                try {
                    this.LINColor[j] = this.WhichColor(this.LINColor[j], stringTokenizer.nextToken());
                }
                catch (Exception ex) {
                    System.out.println("Init: Error in lincol");
                }
            }
        }
        this.Mhe = this.size().height;
        this.Mwi = this.size().width;
        this.InitGlobalPar1();
        this.oimg = this.createImage(this.size().width, this.size().height);
        this.og = this.oimg.getGraphics();
        this.WFloatingX = false;
        this.YNew = new double[this.maxLines];
        this.YOld = new double[this.maxLines];
        this.XNew = new double[this.maxLines];
        this.XOld = new double[this.maxLines];
        this.Index = new int[this.maxLines];
        this.IBegin = new int[this.maxLines];
        this.IEnd = new int[this.maxLines];
        this.YArr = new double[this.maxLines * this.maxPairs];
        this.XArr = new double[this.maxLines * this.maxPairs];
        for (int k = 0; k < this.maxLines; ++k) {
            this.YNew[k] = this.YMin;
            this.YOld[k] = this.YMin;
            this.XNew[k] = 0.0;
            this.XOld[k] = 0.0;
            this.Index[k] = -1;
            this.IBegin[k] = 0;
            this.IEnd[k] = 0;
        }
        this.CHANGED = false;
        this.ALLPOINTS = false;
    }
    
    public Color WhichColor(final Color color, final String s) {
        final Color color2 = new Color(0, 0, 0);
        Color color3 = color;
        final String upperCase = s.toUpperCase();
        if (upperCase.equals("WHITE")) {
            color3 = Color.white;
        }
        else if (upperCase.equals("BLACK")) {
            color3 = Color.black;
        }
        else if (upperCase.equals("LIGHTGRAY")) {
            color3 = Color.lightGray;
        }
        else if (upperCase.equals("GRAY")) {
            color3 = Color.gray;
        }
        else if (upperCase.equals("DARKGRAY")) {
            color3 = Color.darkGray;
        }
        else if (upperCase.equals("RED")) {
            color3 = Color.red;
        }
        else if (upperCase.equals("GREEN")) {
            color3 = Color.green;
        }
        else if (upperCase.equals("BLUE")) {
            color3 = Color.blue;
        }
        else if (upperCase.equals("YELLOW")) {
            color3 = Color.yellow;
        }
        else if (upperCase.equals("MAGENTA")) {
            color3 = Color.magenta;
        }
        else if (upperCase.equals("CYAN")) {
            color3 = Color.cyan;
        }
        else if (upperCase.equals("PINK")) {
            color3 = Color.pink;
        }
        else if (upperCase.equals("ORANGE")) {
            color3 = Color.orange;
        }
        return color3;
    }
    
    public void SetColors(final String s, final String s2, final String s3, final String s4) {
        if (s.length() != 0) {
            this.HBGColor = this.WhichColor(this.HBGColor, s);
        }
        if (s2.length() != 0) {
            this.HFGColor = this.WhichColor(this.HFGColor, s2);
        }
        if (s3.length() != 0) {
            this.ABGColor = this.WhichColor(this.ABGColor, s3);
        }
        if (s4.length() != 0) {
            this.AFGColor = this.WhichColor(this.AFGColor, s4);
        }
        this.repaint();
    }
    
    public void SetParameter(final String header, final String xTitle, final String yTitle, final float n, final float n2, final float n3, final float n4, final String s, final float n5, final int maxPairs) {
        this.Header = header;
        if (this.Header.length() == 0) {
            this.WHeader = false;
        }
        else {
            this.WHeader = true;
        }
        this.XTitle = xTitle;
        if (this.XTitle.length() == 0) {
            this.WXTitle = false;
        }
        else {
            this.WXTitle = true;
        }
        this.YTitle = yTitle;
        if (this.YTitle.length() == 0) {
            this.WYTitle = false;
        }
        else {
            this.WYTitle = true;
        }
        if (s.equals("false")) {
            this.XYPairs = true;
        }
        else {
            this.XYPairs = false;
        }
        this.YMin = n3;
        this.YMax = n4;
        this.XMin = n;
        this.XMax = n2;
        this.XPeriod = n5;
        this.maxPairs = maxPairs;
        this.YNew = new double[this.maxLines];
        this.YOld = new double[this.maxLines];
        this.XNew = new double[this.maxLines];
        this.XOld = new double[this.maxLines];
        this.Index = new int[this.maxLines];
        this.IBegin = new int[this.maxLines];
        this.IEnd = new int[this.maxLines];
        this.YArr = new double[this.maxLines * this.maxPairs];
        this.XArr = new double[this.maxLines * this.maxPairs];
        for (int i = 0; i < this.maxLines; ++i) {
            this.YNew[i] = this.YMin;
            this.YOld[i] = this.YMin;
            this.XNew[i] = 0.0;
            this.XOld[i] = 0.0;
            this.Index[i] = -1;
            this.IBegin[i] = 0;
            this.IEnd[i] = 0;
        }
        this.ALLPOINTS = false;
        if (this.YMax >= 0.0 & this.YMin >= 0.0) {
            this.DeltaY = this.YMax - this.YMin;
        }
        else if (this.YMax >= 0.0 & this.YMin < 0.0) {
            this.DeltaY = this.YMax - this.YMin;
        }
        else {
            this.DeltaY = Math.abs(this.YMin - this.YMax);
        }
        if (this.XMax >= 0.0 & this.XMin >= 0.0) {
            this.DeltaX = this.XMax - this.XMin;
        }
        else if (this.XMax >= 0.0 & this.XMin < 0.0) {
            this.DeltaX = this.XMax - this.XMin;
        }
        else {
            this.DeltaX = Math.abs(this.XMin - this.XMax);
        }
        this.InitGlobalPar1();
        this.CHANGED = false;
        this.repaint();
    }
    
    public void SetParameter(final String s, final String s2, final String s3, final float n, final float n2, final float n3, final float n4, final String s4, final float n5, final int n6, final int maxLines, final String s5, final String s6) {
        this.maxLines = maxLines;
        this.LINColor = new Color[this.maxLines];
        final StringTokenizer stringTokenizer = new StringTokenizer(s5, s6);
        for (int i = 0; i < this.maxLines; ++i) {
            try {
                this.LINColor[i] = this.WhichColor(this.LINColor[i], stringTokenizer.nextToken());
            }
            catch (Exception ex) {
                System.out.println("SetParameter: Error in parameter lincols");
            }
        }
        this.SetParameter(s, s2, s3, n, n2, n3, n4, s4, n5, n6);
    }
    
    public void SetMaxXYPairs(final int maxPairs) {
        this.maxPairs = maxPairs;
        this.YNew = new double[this.maxLines];
        this.YOld = new double[this.maxLines];
        this.XNew = new double[this.maxLines];
        this.XOld = new double[this.maxLines];
        this.Index = new int[this.maxLines];
        this.IBegin = new int[this.maxLines];
        this.IEnd = new int[this.maxLines];
        this.YArr = new double[this.maxLines * this.maxPairs];
        this.XArr = new double[this.maxLines * this.maxPairs];
        for (int i = 0; i < this.maxLines; ++i) {
            this.YNew[i] = this.YMin;
            this.YOld[i] = this.YMin;
            this.XNew[i] = 0.0;
            this.XOld[i] = 0.0;
            this.Index[i] = -1;
            this.IBegin[i] = 0;
            this.IEnd[i] = 0;
        }
        this.CHANGED = true;
        this.repaint();
    }
    
    public void SetMaxLines(final int maxLines) {
        this.maxLines = maxLines;
        this.YNew = new double[this.maxLines];
        this.YOld = new double[this.maxLines];
        this.XNew = new double[this.maxLines];
        this.XOld = new double[this.maxLines];
        this.Index = new int[this.maxLines];
        this.IBegin = new int[this.maxLines];
        this.IEnd = new int[this.maxLines];
        this.YArr = new double[this.maxLines * this.maxPairs];
        this.XArr = new double[this.maxLines * this.maxPairs];
        this.LINColor = new Color[this.maxLines];
        for (int i = 0; i < this.maxLines; ++i) {
            this.YNew[i] = this.YMin;
            this.YOld[i] = this.YMin;
            this.XNew[i] = 0.0;
            this.XOld[i] = 0.0;
            this.Index[i] = -1;
            this.IBegin[i] = 0;
            this.IEnd[i] = 0;
            this.LINColor[i] = Color.blue;
        }
        this.CHANGED = true;
        this.repaint();
    }
    
    public void SetLinesXYPairs(final int maxLines, final int maxPairs) {
        this.maxLines = maxLines;
        this.maxPairs = maxPairs;
        this.YNew = new double[this.maxLines];
        this.YOld = new double[this.maxLines];
        this.XNew = new double[this.maxLines];
        this.XOld = new double[this.maxLines];
        this.Index = new int[this.maxLines];
        this.IBegin = new int[this.maxLines];
        this.IEnd = new int[this.maxLines];
        this.LINColor = new Color[this.maxLines];
        this.YArr = new double[this.maxLines * this.maxPairs];
        this.XArr = new double[this.maxLines * this.maxPairs];
        for (int i = 0; i < this.maxLines; ++i) {
            this.YNew[i] = this.YMin;
            this.YOld[i] = this.YMin;
            this.XNew[i] = 0.0;
            this.XOld[i] = 0.0;
            this.Index[i] = -1;
            this.IBegin[i] = 0;
            this.IEnd[i] = 0;
            this.LINColor[i] = Color.blue;
        }
        this.CHANGED = true;
        this.repaint();
    }
    
    public void SetXYRange(final float n, final float n2, final float n3, final float n4) {
        this.YMin = n3;
        this.YMax = n4;
        this.XMin = n;
        this.XMax = n2;
        if (this.YMax >= 0.0 & this.YMin >= 0.0) {
            this.DeltaY = this.YMax - this.YMin;
        }
        else if (this.YMax >= 0.0 & this.YMin < 0.0) {
            this.DeltaY = this.YMax - this.YMin;
        }
        else {
            this.DeltaY = Math.abs(this.YMin - this.YMax);
        }
        if (this.XMax >= 0.0 & this.XMin >= 0.0) {
            this.DeltaX = this.XMax - this.XMin;
        }
        else if (this.XMax >= 0.0 & this.XMin < 0.0) {
            this.DeltaX = this.XMax - this.XMin;
        }
        else {
            this.DeltaX = Math.abs(this.XMin - this.XMax);
        }
        this.xfak = (this.borderX1 - this.borderX0) / this.DeltaX;
        this.yfak = (this.borderY0 - this.borderY1) / this.DeltaY;
        this.CHANGED = false;
        this.repaint();
    }
    
    public void SetXYMode(final String s, final float n) {
        if (s.equals("false")) {
            this.XYPairs = true;
        }
        else {
            this.XYPairs = false;
        }
        this.XPeriod = n;
    }
    
    public void SetHeader(final String header) {
        this.Header = header;
        if (this.Header.length() == 0) {
            this.WHeader = false;
        }
        else {
            this.WHeader = true;
        }
        this.InitGlobalPar1();
        this.CHANGED = false;
        this.repaint();
    }
    
    public void SetXYTitle(final String xTitle, final String yTitle) {
        this.XTitle = xTitle;
        if (this.XTitle.length() == 0) {
            this.WXTitle = false;
        }
        else {
            this.WXTitle = true;
        }
        this.YTitle = yTitle;
        if (this.YTitle.length() == 0) {
            this.WYTitle = false;
        }
        else {
            this.WYTitle = true;
        }
        this.CHANGED = false;
        this.repaint();
    }
    
    public int TransformX(final double n) {
        return this.borderX0 + (int)Math.round(this.xfak * (n - this.XMin));
    }
    
    public int TransformY(final double n) {
        return this.borderY0 - (int)Math.round(this.yfak * (n - this.YMin));
    }
    
    public void DrawMeterY(final int n, final double n2) {
        this.YNew[n - 1] = n2;
        this.XNew[n - 1] = this.XOld[n - 1] + this.XPeriod;
        this.ALLPOINTS = false;
        if (this.Index[n - 1] == this.maxPairs - 1) {
            this.Index[n - 1] = 0;
        }
        else {
            final int[] index = this.Index;
            final int n3 = n - 1;
            ++index[n3];
        }
        final int n4 = this.Index[n - 1] + (n - 1) * this.maxPairs;
        this.YArr[n4] = this.YNew[n - 1];
        this.XArr[n4] = this.XNew[n - 1];
        this.CHANGED = true;
        this.repaint();
    }
    
    public void SetMeterY(final int n, final double n2) {
        this.YNew[n - 1] = n2;
        this.XNew[n - 1] = this.XOld[n - 1] + this.XPeriod;
        this.ALLPOINTS = false;
        if (this.Index[n - 1] == this.maxPairs - 1) {
            this.Index[n - 1] = 0;
        }
        else {
            final int[] index = this.Index;
            final int n3 = n - 1;
            ++index[n3];
        }
        final int n4 = this.Index[n - 1] + (n - 1) * this.maxPairs;
        this.YArr[n4] = this.YNew[n - 1];
        this.XArr[n4] = this.XNew[n - 1];
        this.CHANGED = true;
    }
    
    public void DrawMeterXY(final int n, final double n2, final double n3) {
        this.YNew[n - 1] = n3;
        this.XNew[n - 1] = n2;
        this.ALLPOINTS = false;
        if (this.Index[n - 1] == this.maxPairs - 1) {
            this.Index[n - 1] = 0;
        }
        else {
            final int[] index = this.Index;
            final int n4 = n - 1;
            ++index[n4];
        }
        final int n5 = this.Index[n - 1] + (n - 1) * this.maxPairs;
        this.YArr[n5] = this.YNew[n - 1];
        this.XArr[n5] = this.XNew[n - 1];
        this.CHANGED = true;
        this.repaint();
    }
    
    public void SetMeterXY(final int n, final double n2, final double n3) {
        this.YNew[n - 1] = n3;
        this.XNew[n - 1] = n2;
        this.ALLPOINTS = false;
        if (this.Index[n - 1] == this.maxPairs - 1) {
            this.Index[n - 1] = 0;
        }
        else {
            final int[] index = this.Index;
            final int n4 = n - 1;
            ++index[n4];
        }
        final int n5 = this.Index[n - 1] + (n - 1) * this.maxPairs;
        this.YArr[n5] = this.YNew[n - 1];
        this.XArr[n5] = this.XNew[n - 1];
        this.CHANGED = true;
    }
    
    public void ResetMeter() {
        this.CHANGED = true;
        this.ALLPOINTS = false;
        this.YNew = new double[this.maxLines];
        this.YOld = new double[this.maxLines];
        this.XNew = new double[this.maxLines];
        this.XOld = new double[this.maxLines];
        this.Index = new int[this.maxLines];
        this.IBegin = new int[this.maxLines];
        this.IEnd = new int[this.maxLines];
        this.YArr = new double[this.maxLines * this.maxPairs];
        this.XArr = new double[this.maxLines * this.maxPairs];
        for (int i = 0; i < this.maxLines; ++i) {
            this.YNew[i] = this.YMin;
            this.YOld[i] = this.YMin;
            this.XNew[i] = 0.0;
            this.XOld[i] = 0.0;
            this.Index[i] = -1;
            this.IBegin[i] = 0;
            this.IEnd[i] = 0;
        }
        this.repaint();
    }
    
    public void SetLineColor(final int n, final String s) {
        if (n > 0 & n <= this.maxLines) {
            if (s.length() != 0) {
                this.LINColor[n - 1] = this.WhichColor(this.LINColor[n - 1], s);
            }
            this.repaint();
        }
    }
    
    public void SetLineColor(final int maxLines, final String s, final String s2) {
        this.maxLines = maxLines;
        this.LINColor = new Color[this.maxLines];
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        for (int i = 0; i < this.maxLines; ++i) {
            try {
                this.LINColor[i] = this.WhichColor(this.LINColor[i], stringTokenizer.nextToken());
            }
            catch (Exception ex) {
                System.out.println("SetlineColor: Error in parameter lincols");
            }
        }
        this.repaint();
    }
    
    public boolean CompCode0(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.Top0 = false;
        this.Bottom0 = false;
        this.Left0 = false;
        this.Right0 = false;
        boolean b = false;
        if (n2 > n6) {
            this.Top0 = true;
            b = true;
        }
        else if (n2 < n5) {
            this.Bottom0 = true;
            b = true;
        }
        if (n > n4) {
            this.Right0 = true;
            b = true;
        }
        else if (n < n3) {
            this.Left0 = true;
            b = true;
        }
        return b;
    }
    
    public boolean CompCode1(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.Top1 = false;
        this.Bottom1 = false;
        this.Left1 = false;
        this.Right1 = false;
        boolean b = false;
        if (n2 > n6) {
            this.Top1 = true;
            b = true;
        }
        else if (n2 < n5) {
            this.Bottom1 = true;
            b = true;
        }
        if (n > n4) {
            this.Right1 = true;
            b = true;
        }
        else if (n < n3) {
            this.Left1 = true;
            b = true;
        }
        return b;
    }
    
    public boolean LineClip(final int xc0, final int yc0, final int xc2, final int yc2, final int n, final int n2, final int n3, final int n4) {
        this.xc0 = xc0;
        this.yc0 = yc0;
        this.xc1 = xc2;
        this.yc1 = yc2;
        boolean b = this.CompCode0(this.xc0, this.yc0, n, n2, n3, n4);
        boolean b2 = this.CompCode1(this.xc1, this.yc1, n, n2, n3, n4);
        boolean b3;
        boolean b4;
        do {
            b3 = false;
            b4 = false;
            final boolean b5 = (this.Top0 & this.Top1) || (this.Bottom0 & this.Bottom1) || (this.Right0 & this.Right1) || (this.Left0 & this.Left1);
            if (!b & !b2) {
                b4 = true;
                b3 = true;
            }
            else if (b5) {
                b4 = false;
                b3 = true;
            }
            else {
                int n5 = 0;
                int n6 = 0;
                boolean b6;
                boolean b7;
                boolean b8;
                boolean b9;
                boolean b10;
                if (b) {
                    b6 = this.Top0;
                    b7 = this.Bottom0;
                    b8 = this.Right0;
                    b9 = this.Left0;
                    b10 = false;
                }
                else {
                    b6 = this.Top1;
                    b7 = this.Bottom1;
                    b8 = this.Right1;
                    b9 = this.Left1;
                    b10 = true;
                }
                if (b6) {
                    n5 = this.xc0 + (this.xc1 - this.xc0) * (n4 - this.yc0) / (this.yc1 - this.yc0);
                    n6 = n4;
                }
                if (b7) {
                    n5 = this.xc0 + (this.xc1 - this.xc0) * (n3 - this.yc0) / (this.yc1 - this.yc0);
                    n6 = n3;
                }
                else if (b8) {
                    n6 = this.yc0 + (this.yc1 - this.yc0) * (n2 - this.xc0) / (this.xc1 - this.xc0);
                    n5 = n2;
                }
                else if (b9) {
                    n6 = this.yc0 + (this.yc1 - this.yc0) * (n - this.xc0) / (this.xc1 - this.xc0);
                    n5 = n;
                }
                if (!b10) {
                    this.xc0 = n5;
                    this.yc0 = n6;
                    b = this.CompCode0(this.xc0, this.yc0, n, n2, n3, n4);
                }
                else {
                    this.xc1 = n5;
                    this.yc1 = n6;
                    b2 = this.CompCode1(this.xc1, this.yc1, n, n2, n3, n4);
                }
            }
        } while (!b3);
        return b4;
    }
    
    public void run() {
        this.repaint();
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
            this.runner = null;
        }
    }
    
    void pause(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final int[] array = new int[3];
        final int[] array2 = new int[3];
        int n = 21;
        int stringWidth = 0;
        this.og.setColor(Color.black);
        this.og.drawRect(0, 0, this.Mwi, this.Mhe);
        if (this.WHeader) {
            this.og.setColor(this.HBGColor);
            this.og.fillRect(0, 0, this.Mwi - 1, this.Hhe - 1);
            this.og.setColor(Color.black);
            this.og.drawRect(0, 0, this.Mwi - 1, this.Hhe - 1);
        }
        this.og.setColor(this.ABGColor);
        this.og.fillRect(0, this.Hhe, this.Mwi - 1, this.Ahe - 1);
        this.og.setColor(Color.black);
        this.og.drawRect(0, this.Hhe, this.Mwi - 1, this.Ahe - 1);
        if (this.WHeader) {
            n = 21;
            int height;
            FontMetrics fontMetrics;
            do {
                --n;
                final Font font = new Font("TimesRoman", 0, n);
                this.og.setFont(font);
                fontMetrics = this.getFontMetrics(font);
                height = fontMetrics.getHeight();
            } while (height + 2 > this.Hhe);
            final int n2 = this.Mwi / 2 - fontMetrics.stringWidth(this.Header) / 2;
            final int n3 = this.Hhe / 2 + height / 2 - 2;
            if (n2 > 0) {
                this.og.setColor(this.HFGColor);
                this.og.drawString(this.Header, n2, n3);
            }
        }
        this.og.setColor(this.AFGColor);
        this.og.drawLine(this.xs0 - 2, this.ys0, this.xs1, this.ys1);
        final int xMarkerSpace = this.XMarkerSpace;
        final int xMarkerLength = this.XMarkerLength;
        final int n4 = this.ys0 + xMarkerLength;
        int xs1 = this.xs1;
        for (int i = 1; i <= 10; ++i) {
            xs1 = this.xs0 + i * xMarkerSpace;
            this.og.drawLine(xs1, this.ys0, xs1, n4);
        }
        final int n5 = xs1;
        if (this.xs1 + 6 < this.Mwi & this.hborder > 8) {
            array[0] = this.xs1;
            array2[0] = this.ys1 - 3;
            array[1] = this.xs1 + 5;
            array2[1] = this.ys1;
            array[2] = this.xs1;
            array2[2] = this.ys1 + 3;
            this.og.fillPolygon(array, array2, 3);
        }
        final int n6 = this.hborder - xMarkerLength;
        ++n;
        int j;
        FontMetrics fontMetrics2;
        do {
            --n;
            final Font font2 = new Font("TimesRoman", 0, n);
            this.og.setFont(font2);
            fontMetrics2 = this.getFontMetrics(font2);
            j = fontMetrics2.getHeight();
        } while (j > n6);
        final int xs2 = this.xs0;
        final int n7 = this.yaroot - 3;
        final String value = String.valueOf(this.XMin);
        this.og.drawString(value, xs2, n7);
        if (this.WXTitle) {
            stringWidth = fontMetrics2.stringWidth(value);
        }
        final int n8 = n5;
        int n9 = this.yaroot - 3;
        final String value2 = String.valueOf(this.XMax);
        this.og.drawString(value2, n8, n9);
        if (this.WXTitle) {
            stringWidth += fontMetrics2.stringWidth(value2);
        }
        if (this.WXTitle) {
            final int n10 = this.borderX1 - this.borderX0 - stringWidth;
            final int stringWidth2 = fontMetrics2.stringWidth(this.XTitle);
            if (stringWidth2 < n10) {
                n9 = this.yaroot - 3;
                this.og.drawString(this.XTitle, this.borderX0 + (this.borderX1 - this.borderX0) / 2 - stringWidth2 / 2, n9);
            }
        }
        this.og.drawLine(this.xs0, this.ys0 + 3, this.xs2, this.ys2);
        final int yMarkerSpace = this.YMarkerSpace;
        final int n11 = this.xs0 - this.YMarkerLength;
        for (int k = 1; k <= 10; ++k) {
            n9 = this.ys0 - k * yMarkerSpace;
            this.og.drawLine(n11, n9, this.xs0, n9);
        }
        final int n12 = n9;
        if (this.xs1 + 6 < this.Mwi & this.hborder > 8) {
            array[0] = this.xs0 - 3;
            array2[0] = this.ys2;
            array[1] = this.xs0;
            array2[1] = this.ys2 - 6;
            array[2] = this.xs0 + 3;
            array2[2] = this.ys2;
            this.og.fillPolygon(array, array2, 3);
        }
        final int n13 = this.xaroot + 3;
        final int ys0 = this.ys0;
        final String value3 = String.valueOf(this.YMin);
        if (n13 + fontMetrics2.stringWidth(value3) < this.xs0) {
            this.og.drawString(value3, n13, ys0);
        }
        final int n14 = n12;
        final String value4 = String.valueOf(this.YMax);
        if (n13 + fontMetrics2.stringWidth(value4) < this.xs0) {
            this.og.drawString(value4, n13, n14);
        }
        if (this.WYTitle) {
            final int n15 = this.borderY1 - 3;
            final int n16 = this.borderX0 + 5;
            if (fontMetrics2.stringWidth(this.YTitle) < this.borderX1 - this.borderX0 & this.hborder - 3 >= j) {
                this.og.drawString(this.YTitle, n16, n15);
            }
        }
        for (int l = 0; l < this.maxLines; ++l) {
            this.og.setColor(this.LINColor[l]);
            if (this.ALLPOINTS) {
                if (this.Index[l] > 0) {
                    for (int n17 = 1; n17 <= this.Index[l]; ++n17) {
                        final int n18 = l * this.maxPairs + n17;
                        if (this.LineClip(this.TransformX(this.XArr[n18]), this.TransformY(this.YArr[n18]), this.TransformX(this.XArr[n18 - 1]), this.TransformY(this.YArr[n18 - 1]), this.borderX0, this.borderX1, this.borderY1, this.borderY0)) {
                            this.og.drawLine(this.xc1, this.yc1, this.xc0, this.yc0);
                        }
                    }
                }
            }
            else if (this.Index[l] > 0) {
                for (int n19 = 1; n19 <= this.Index[l]; ++n19) {
                    final int n20 = l * this.maxPairs + n19;
                    if (this.LineClip(this.TransformX(this.XArr[n20]), this.TransformY(this.YArr[n20]), this.TransformX(this.XArr[n20 - 1]), this.TransformY(this.YArr[n20 - 1]), this.borderX0, this.borderX1, this.borderY1, this.borderY0)) {
                        this.og.drawLine(this.xc1, this.yc1, this.xc0, this.yc0);
                    }
                }
                this.ALLPOINTS = true;
            }
        }
        graphics.drawImage(this.oimg, 0, 0, this);
    }
    
    public xymeter() {
        this.Hhemax = 20;
        this.WHeader = true;
        this.WXTitle = false;
        this.WYTitle = false;
        this.WFloatingX = true;
        this.XYPairs = true;
        this.maxLines = 1;
        this.maxPairs = 100;
        this.Top0 = false;
        this.Bottom0 = false;
        this.Left0 = false;
        this.Right0 = false;
        this.Top1 = false;
        this.Bottom1 = false;
        this.Left1 = false;
        this.Right1 = false;
    }
}
