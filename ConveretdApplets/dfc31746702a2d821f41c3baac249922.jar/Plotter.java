import java.awt.image.ImageObserver;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Button;
import java.awt.Menu;
import java.awt.PopupMenu;
import java.awt.MenuItem;
import java.awt.Event;
import java.util.Date;
import java.lang.reflect.Array;
import java.awt.Component;
import java.awt.MediaTracker;
import java.util.Hashtable;
import java.awt.Label;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.CheckboxMenuItem;
import java.util.Vector;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class Plotter extends Panel
{
    Parameters param;
    Vector chartList;
    DataSet currentDataSet;
    int startData;
    int endData;
    dialogIndicator indicatorWindow;
    protected int X1;
    protected int X2;
    protected int startPrice;
    protected int endPrice;
    protected int startVolume;
    protected int endVolume;
    protected int startSplit;
    protected int endSplit;
    protected int maxPrice;
    protected int minPrice;
    protected int maxVolume;
    protected int minVolume;
    protected int maxRound;
    protected int minRound;
    protected int interval;
    protected double stepX;
    protected double stepPrice;
    protected double stepVolume;
    protected double stepOBV;
    protected int chartType;
    static final int chartLINE = 0;
    static final int chartBAR = 1;
    static final int chartCANDLE = 2;
    protected CheckboxMenuItem chartTypeMenuItem;
    protected Cursor cursorC;
    protected Cursor cursorW;
    protected Cursor cursorE;
    protected Cursor cursorD;
    protected boolean cursorValueState;
    protected int[] nInterval;
    protected int[] valueInterval;
    protected int rsiHeight;
    protected int rocHeight;
    protected int macdHeight;
    protected int mouseX;
    protected int mouseY;
    protected boolean mouseVisible;
    protected int mouseDragX;
    protected int mouseDragY;
    protected boolean mouseDrag;
    protected int mouseTrendX;
    protected int mouseTrendY;
    protected boolean mouseTrend;
    protected Image chartImage;
    protected Graphics chartGraphics;
    protected Dimension chartDimension;
    protected FontMetrics labelFontM;
    protected FontMetrics titleFontM;
    protected Image watermark;
    Panel dataPanel;
    Label lblHDate;
    public static int indicate;
    public static int inChartIndicate;
    public int inChartDays;
    public boolean calRsi;
    public boolean calRoc;
    public boolean calMacd;
    public boolean calVolume;
    public boolean calAccum;
    public boolean calCci;
    public boolean calStochast;
    public boolean calMomentum;
    int maxIndicate;
    int minIndicate;
    int preValue;
    int days;
    private int prehighValue;
    private int prelowValue;
    protected Vector plotValue;
    private double firstEma;
    private Vector macdValue;
    private Hashtable hIndicator;
    private Hashtable hMacd;
    private double[][] highLow;
    private int[] cciValues;
    String tmpIndicate;
    String tmpMacd;
    String tmpLabel;
    private int obvValue;
    private int maxObv;
    private int minObv;
    private String sOutput;
    
    static {
        Plotter.indicate = 0;
        Plotter.inChartIndicate = 0;
    }
    
    Plotter() {
        this.nInterval = new int[] { 2, 4, 5, 10 };
        this.valueInterval = new int[] { 1, 2, 5, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000, 100000 };
        this.rsiHeight = 0;
        this.rocHeight = 0;
        this.macdHeight = 0;
        this.mouseVisible = false;
        this.mouseDrag = false;
        this.mouseTrend = false;
        this.dataPanel = new Panel();
        this.lblHDate = new Label();
        this.calRsi = false;
        this.calRoc = false;
        this.calMacd = false;
        this.calVolume = true;
        this.calAccum = false;
        this.calCci = false;
        this.calStochast = false;
        this.calMomentum = false;
        this.prehighValue = 0;
        this.prelowValue = 0;
        this.obvValue = 0;
        this.maxObv = 0;
        this.minObv = 0;
        this.sOutput = null;
    }
    
    Plotter(final String s) {
        this.nInterval = new int[] { 2, 4, 5, 10 };
        this.valueInterval = new int[] { 1, 2, 5, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000, 100000 };
        this.rsiHeight = 0;
        this.rocHeight = 0;
        this.macdHeight = 0;
        this.mouseVisible = false;
        this.mouseDrag = false;
        this.mouseTrend = false;
        this.dataPanel = new Panel();
        this.lblHDate = new Label();
        this.calRsi = false;
        this.calRoc = false;
        this.calMacd = false;
        this.calVolume = true;
        this.calAccum = false;
        this.calCci = false;
        this.calStochast = false;
        this.calMomentum = false;
        this.prehighValue = 0;
        this.prelowValue = 0;
        this.obvValue = 0;
        this.maxObv = 0;
        this.minObv = 0;
        this.sOutput = null;
        this.initPlotter(s);
    }
    
    public Image GetImage(final String s) {
        final Image image = Parameters.applet.getImage(Parameters.applet.getCodeBase(), s);
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (Exception ex) {}
        return image;
    }
    
    protected int IndicatetoY(final int n) {
        return this.endVolume - (int)((n - this.minIndicate) * this.stepVolume);
    }
    
    protected void SetStep(final DataSet set) {
        if (set.dataType != 5) {
            return;
        }
        final int endData = this.endData;
        this.maxPrice = 0;
        this.minPrice = 0;
        this.maxVolume = 0;
        this.minVolume = 0;
        if (this.calRoc || this.calRsi || this.calMacd || this.calCci || this.calStochast || this.calMomentum) {
            if (this.hIndicator != null) {
                this.hIndicator = null;
            }
            this.hIndicator = new Hashtable(this.endData - this.startData);
            for (int i = 0; i < this.currentDataSet.indicator.size(); ++i) {
                final Indicator indicator = this.currentDataSet.indicator.elementAt(i);
                if (indicator.getType() == 2) {
                    this.calRoc = true;
                    this.days = indicator.getValue();
                }
                else if (indicator.getType() == 3) {
                    this.calRsi = true;
                    this.days = indicator.getValue();
                }
                else if (indicator.getType() == 4) {
                    this.calMacd = true;
                }
                else if (indicator.getType() == 9) {
                    this.days = indicator.getValue();
                }
                else if (indicator.getType() == 10) {
                    this.days = indicator.getValue();
                }
                else if (indicator.getType() == 11) {
                    this.days = indicator.getValue();
                }
            }
        }
        for (int j = 0; j < this.currentDataSet.indicator.size(); ++j) {
            final Indicator indicator2 = this.currentDataSet.indicator.elementAt(j);
            if (indicator2.getType() == 6) {
                this.setBollingerValue(this.startData, endData, indicator2, set);
                this.inChartDays = indicator2.getValue();
            }
            else if (indicator2.getType() == 7) {
                if (this.plotValue != null) {
                    this.plotValue = null;
                }
                this.setObvValues(this.startData, endData, indicator2, set);
                this.stepOBV = (this.endVolume - this.startVolume) / (this.maxObv - this.minObv);
            }
        }
        int k;
        for (k = this.startData; k < endData; ++k) {
            final int[] array = (int[])set.chartData[k].getData();
            if (Array.getLength(array) == 5) {
                this.maxPrice = array[1];
                this.minPrice = array[2];
                this.maxVolume = array[4];
                this.maxIndicate = 0;
                this.minIndicate = 100;
                break;
            }
        }
        if (endData <= k) {
            return;
        }
        if (this.calRsi && this.days > 0) {
            if (this.plotValue != null) {
                this.plotValue = null;
            }
            this.setRsiValues(k, endData, this.days, set);
        }
        else if (this.calMacd) {
            if (this.hMacd != null) {
                this.hMacd = null;
            }
            this.hMacd = new Hashtable(this.endData - this.startData);
            for (int l = 0; l < this.currentDataSet.indicator.size(); ++l) {
                final Indicator indicator3 = this.currentDataSet.indicator.elementAt(l);
                if (indicator3.getType() == 4) {
                    if (this.macdValue != null) {
                        this.macdValue = null;
                    }
                    this.setMacdValue(indicator3, k, set, endData);
                }
            }
        }
        else if (this.calRoc && this.days > 0) {
            if (this.plotValue != null) {
                this.plotValue = null;
            }
            this.setRocValue(k, endData, this.days, set);
        }
        else if (this.calCci && this.days > 0) {
            this.setCciValues(k, endData, this.days, set);
        }
        else if (this.calAccum) {
            if (this.plotValue != null) {
                this.plotValue = null;
            }
            this.setAccumulation(k, endData, set);
        }
        else if (this.calStochast && this.days > 0) {
            if (this.plotValue != null) {
                this.plotValue = null;
            }
            this.setStochast(k, endData, this.days, set);
        }
        else if (this.calMomentum && this.days > 0) {
            if (this.plotValue != null) {
                this.plotValue = null;
            }
            this.setMomentum(k, endData, this.days, set);
        }
        int n = 0;
        int n2 = 0;
        while (k <= endData) {
            final int[] array2 = (int[])set.chartData[k].getData();
            if (Array.getLength(array2) == 5) {
                this.maxPrice = ((this.maxPrice < array2[1]) ? array2[1] : this.maxPrice);
                this.minPrice = ((this.minPrice > array2[2]) ? array2[2] : this.minPrice);
                this.maxVolume = ((this.maxVolume < array2[4]) ? array2[4] : this.maxVolume);
                this.minVolume = ((this.minVolume > array2[4]) ? array2[4] : this.minVolume);
                try {
                    if (this.highLow != null && n2 < Array.getLength(this.highLow)) {
                        this.maxPrice = (int)((this.maxPrice < this.highLow[n2][0]) ? this.highLow[n2][0] : this.maxPrice);
                        this.minPrice = (int)((this.minPrice > this.highLow[n2][1]) ? this.highLow[n2][1] : this.minPrice);
                        ++n2;
                    }
                }
                catch (ArrayIndexOutOfBoundsException ex) {}
                if (this.calRoc) {
                    try {
                        final int round = Math.round((float)(Object)this.plotValue.elementAt(n));
                        this.maxIndicate = ((this.maxIndicate > round) ? this.maxIndicate : round);
                        this.minIndicate = ((this.minIndicate < round) ? this.minIndicate : round);
                        ++n;
                    }
                    catch (ArrayIndexOutOfBoundsException ex2) {}
                }
                else if (this.calMacd) {
                    try {
                        final int round2 = Math.round((int)(Object)this.macdValue.elementAt(n) / 100);
                        this.maxIndicate = ((this.maxIndicate > round2) ? this.maxIndicate : round2);
                        this.minIndicate = ((this.minIndicate < round2) ? this.minIndicate : round2);
                        ++n;
                    }
                    catch (ArrayIndexOutOfBoundsException ex3) {}
                }
                else if (this.calCci) {
                    try {
                        final int n3 = this.cciValues[n];
                        this.maxIndicate = ((this.maxIndicate > n3) ? this.maxIndicate : n3);
                        this.minIndicate = ((this.minIndicate < n3) ? this.minIndicate : n3);
                        ++n;
                    }
                    catch (ArrayIndexOutOfBoundsException ex4) {}
                }
                else if (this.calAccum) {
                    try {
                        final int intValue = this.plotValue.elementAt(n);
                        this.maxIndicate = ((this.maxIndicate > intValue) ? this.maxIndicate : intValue);
                        this.minIndicate = ((this.minIndicate < intValue) ? this.minIndicate : intValue);
                        ++n;
                    }
                    catch (ArrayIndexOutOfBoundsException ex5) {}
                }
                else if (this.calStochast) {
                    try {
                        final int round3 = Math.round((float)(Object)this.plotValue.elementAt(n));
                        this.maxIndicate = ((this.maxIndicate > round3) ? this.maxIndicate : round3);
                        this.minIndicate = ((this.minIndicate < round3) ? this.minIndicate : round3);
                        ++n;
                    }
                    catch (ArrayIndexOutOfBoundsException ex6) {}
                }
                else if (this.calMomentum) {
                    try {
                        final int round4 = Math.round((float)(Object)this.plotValue.elementAt(n));
                        this.maxIndicate = ((this.maxIndicate > round4) ? this.maxIndicate : round4);
                        this.minIndicate = ((this.minIndicate < round4) ? this.minIndicate : round4);
                        ++n;
                    }
                    catch (ArrayIndexOutOfBoundsException ex7) {}
                }
            }
            ++k;
        }
        this.setAxis(this.maxPrice / 100, this.minPrice / 100);
        this.stepX = (this.X2 - this.X1) / (this.endData - this.startData + 0.9);
        this.stepPrice = (this.endPrice - this.startPrice) / (this.maxRound - this.minRound);
        if (this.calRsi) {
            this.maxIndicate = 100;
            this.minIndicate = 0;
        }
        if (this.calRoc || this.calRsi || this.calMacd || this.calAccum || this.calCci || this.calStochast || this.calMomentum) {
            this.stepVolume = (this.endVolume - this.startVolume) / (this.maxIndicate - this.minIndicate);
        }
        else {
            this.stepVolume = (this.endVolume - this.startVolume) / (this.maxVolume - this.minVolume);
        }
    }
    
    protected void ShowLabel(final int n, final int n2) {
        if (!this.mouseVisible) {
            return;
        }
        if (!this.cursorValueState) {
            return;
        }
        if (!this.cursorOK(n, n2)) {
            return;
        }
        final int xtoData = this.xtoData(n);
        if (xtoData < 0) {
            return;
        }
        final DataSet currentDataSet = this.currentDataSet;
        if (currentDataSet == null) {
            return;
        }
        if (xtoData >= Array.getLength(currentDataSet.chartData)) {
            return;
        }
        final int[] array = (int[])currentDataSet.chartData[xtoData].getData();
        if (array == null) {
            return;
        }
        final Date date = currentDataSet.chartData[xtoData].getDate();
        if (date == null) {
            return;
        }
        final String formatDateddmmmyyweek = this.formatDateddmmmyyweek(date);
        final String formatInt = this.formatInt(array[0], 2);
        final String formatInt2 = this.formatInt(array[1], 2);
        final String formatInt3 = this.formatInt(array[2], 2);
        final String formatInt4 = this.formatInt(array[3], 2);
        final String formatInt5 = this.formatInt(array[4], 0);
        String s = "   " + formatDateddmmmyyweek + "     O: " + formatInt + "     H: " + formatInt2 + "     L: " + formatInt3 + "     C: " + formatInt4;
        if (this.inChartDays != 0 && this.highLow != null) {
            try {
                s = String.valueOf(s) + "   B Band(" + (int)(this.highLow[xtoData - this.inChartDays][0] / 100.0) + "," + (int)(this.highLow[xtoData - this.inChartDays][1] / 100.0) + ")";
            }
            catch (Exception ex) {}
        }
        Label_0549: {
            if (!this.calRoc && !this.calRsi) {
                if (!this.calMacd) {
                    if (this.calVolume) {
                        s = String.valueOf(s) + "     V: " + formatInt5;
                    }
                    break Label_0549;
                }
            }
            try {
                final Integer n3 = new Integer(xtoData);
                final String string = this.hIndicator.get(n3).toString();
                this.tmpIndicate = ((string != null) ? string : this.tmpIndicate);
                s = String.valueOf(s) + "   Indicator: " + this.tmpIndicate;
                if (this.calMacd && this.hMacd != null) {
                    final String string2 = this.hMacd.get(n3).toString();
                    this.tmpMacd = ((string2 != null) ? string2 : this.tmpMacd);
                    s = String.valueOf(s) + " , " + this.tmpMacd;
                }
            }
            catch (Exception ex2) {
                s = this.tmpLabel;
            }
        }
        this.lblHDate.setAlignment(1);
        this.tmpLabel = s;
        this.lblHDate.setText(s);
        this.lblHDate.resize(this.size().width, 20);
    }
    
    public boolean action(final Event event, final Object o) {
        event.toString();
        if (event.target instanceof CheckboxMenuItem) {
            final CheckboxMenuItem chartTypeMenuItem = (CheckboxMenuItem)event.target;
            final String upperCase = chartTypeMenuItem.getLabel().toString().trim().toUpperCase();
            if (upperCase.equals("BAR")) {
                this.chartTypeMenuItem.setState(false);
                (this.chartTypeMenuItem = chartTypeMenuItem).setState(true);
                this.doBAR();
            }
            else if (upperCase.equals("CANDLE")) {
                this.chartTypeMenuItem.setState(false);
                (this.chartTypeMenuItem = chartTypeMenuItem).setState(true);
                this.doCANDLE();
            }
            else if (upperCase.equals("LINE")) {
                this.chartTypeMenuItem.setState(false);
                (this.chartTypeMenuItem = chartTypeMenuItem).setState(true);
                this.doLINE();
            }
            else if (upperCase.equals("SHOW VALUE")) {
                this.doSHOW_VALUE();
            }
        }
        else if (event.target instanceof MenuItem) {
            Plotter.inChartIndicate = 0;
            final MenuItem menuItem = (MenuItem)event.target;
            final String upperCase2 = event.arg.toString().trim().toUpperCase();
            if (menuItem.getParent() instanceof PopupMenu) {
                if (upperCase2.equals("UNZOOM")) {
                    this.doUNZOOM();
                }
                else if (upperCase2.equals("REFRESH")) {
                    this.doREFRESH();
                }
            }
            else if (menuItem.getParent() instanceof Menu) {
                final String upperCase3 = ((Menu)menuItem.getParent()).getLabel().trim().toUpperCase();
                if (upperCase3.equals("TREND")) {
                    if (upperCase2.equals("REMOVE ALL")) {
                        this.removeAllTrendLine();
                    }
                    else if (upperCase2.equals("REMOVE LAST")) {
                        this.removeLastTrendLine();
                    }
                }
                else if (upperCase3.equals("INDICATORS")) {
                    this.inChartDays = 0;
                    if (upperCase2.equals("REMOVE ALL")) {
                        this.removeAllIndicators();
                    }
                    else if (upperCase2.equals("REMOVE LAST")) {
                        this.removeLastIndicators();
                    }
                    else if (upperCase2.equals("MOVING AVERAGE")) {
                        Plotter.indicate = 1;
                        this.doMovingAverage();
                    }
                    else if (upperCase2.equals("EMA")) {
                        this.firstEma = 0.0;
                        Plotter.indicate = 5;
                        this.doEMA();
                    }
                    else if (upperCase2.equals("BOLLINGER BAND")) {
                        Plotter.inChartIndicate = 6;
                        this.doBollinger();
                    }
                    else if (upperCase2.equals("COMMODITY CHANNEL INDEX")) {
                        this.calRoc = false;
                        this.calRsi = false;
                        this.calVolume = false;
                        this.calMacd = false;
                        this.calAccum = false;
                        this.calCci = true;
                        this.calStochast = false;
                        this.calMomentum = false;
                        Plotter.indicate = 9;
                        this.doCci();
                    }
                    else if (upperCase2.equals("ACCUMULATION DISTRIBUTION")) {
                        this.calCci = false;
                        this.calRoc = false;
                        this.calRsi = false;
                        this.calVolume = false;
                        this.calMacd = false;
                        this.calAccum = true;
                        this.calStochast = false;
                        this.calMomentum = false;
                        Plotter.indicate = 8;
                        this.doAccumulation();
                    }
                    else if (upperCase2.equals("ON BALANCE VOLUME")) {
                        this.calCci = false;
                        this.calRoc = false;
                        this.calRsi = false;
                        this.calVolume = true;
                        this.calMacd = false;
                        this.calAccum = false;
                        this.calMomentum = false;
                        Plotter.indicate = 7;
                        this.calStochast = false;
                        this.obvValue = 0;
                        this.doOBV();
                    }
                    else if (upperCase2.equals("ROC")) {
                        this.calCci = false;
                        this.calRoc = true;
                        this.calRsi = false;
                        this.calVolume = false;
                        this.calMacd = false;
                        this.calAccum = false;
                        this.calStochast = false;
                        this.calMomentum = false;
                        Plotter.indicate = 2;
                        this.doROC();
                    }
                    else if (upperCase2.equals("RSI")) {
                        this.calCci = false;
                        this.calRoc = false;
                        this.calRsi = true;
                        this.calVolume = false;
                        this.calMacd = false;
                        this.calAccum = false;
                        this.calStochast = false;
                        this.calMomentum = false;
                        Plotter.indicate = 3;
                        this.doRSI();
                    }
                    else if (upperCase2.equals("MACD")) {
                        this.calCci = false;
                        this.calRoc = false;
                        this.calRsi = false;
                        this.calVolume = false;
                        this.calMacd = true;
                        this.calAccum = false;
                        this.calStochast = false;
                        this.calMomentum = false;
                        Plotter.indicate = 4;
                        this.doMACD();
                    }
                    else if (upperCase2.equals("STOCHASTIC OSCILLATOR")) {
                        this.calCci = false;
                        this.calRoc = false;
                        this.calRsi = false;
                        this.calVolume = false;
                        this.calMacd = false;
                        this.calAccum = false;
                        this.calMomentum = false;
                        this.calStochast = true;
                        Plotter.indicate = 10;
                        this.doStochast();
                    }
                    else if (upperCase2.equals("MOMENTUM INDICATOR")) {
                        this.calCci = false;
                        this.calRoc = false;
                        this.calRsi = false;
                        this.calVolume = false;
                        this.calMacd = false;
                        this.calAccum = false;
                        this.calStochast = false;
                        this.calMomentum = true;
                        Plotter.indicate = 11;
                        this.doMomentum();
                    }
                }
                else if (upperCase3.equals("CURSOR")) {
                    if (upperCase2.equals("+")) {
                        this.doCursor(true, true);
                    }
                    else if (upperCase2.equals("|")) {
                        this.doCursor(true, false);
                    }
                    else if (upperCase2.equals("-")) {
                        this.doCursor(false, true);
                    }
                    else if (upperCase2.equals("NONE")) {
                        this.doCursor(false, false);
                    }
                }
            }
        }
        else if (event.target instanceof Button) {
            o.toString();
        }
        return true;
    }
    
    public void addDataSet(final String s) {
        final DataSet currentDataSet = new DataSet(s);
        if (this.chartList == null) {
            this.chartList = new Vector(5, 2);
        }
        this.chartList.addElement(currentDataSet);
        this.currentDataSet = currentDataSet;
        this.startData = 0;
        this.endData = Array.getLength(currentDataSet.chartData) - 1;
        this.changePlotter();
    }
    
    protected void addIndicators(final int n, final int n2) {
        this.currentDataSet.addIndicators(n, n2);
        this.changePlotter();
    }
    
    protected void addIndicators(final int n, final String s) {
        int int1 = 0;
        try {
            int1 = Integer.parseInt(s);
        }
        catch (Exception ex) {}
        this.currentDataSet.addIndicators(n, int1);
        this.changePlotter();
    }
    
    protected void addIndicators(final int n, final String s, final String s2) {
        this.currentDataSet.addIndicators(n, s, s2);
        this.addIndicators(1, s);
        this.changePlotter();
    }
    
    protected void addIndicators(final int n, final String s, final String s2, final String s3) {
        this.currentDataSet.addIndicators(n, s, s2, s3);
        this.changePlotter();
    }
    
    protected void addTrendLine(final int n, final int n2, final int n3, final int n4) {
        if (n < this.X1 || n > this.X2) {
            return;
        }
        if (n3 < this.X1 || n3 > this.X2) {
            return;
        }
        if (n2 < this.startPrice || n2 > this.endPrice) {
            return;
        }
        if (n4 < this.startPrice || n4 > this.endPrice) {
            return;
        }
        this.currentDataSet.addTrendLine(new int[] { this.xtoData(n), this.ytoPrice(n2), this.xtoData(n3), this.ytoPrice(n4) });
    }
    
    protected void changePlotter() {
        if (this.chartImage != null) {
            this.chartGraphics.dispose();
            this.chartImage = null;
        }
        System.gc();
        if (Parameters.CursorOK) {
            this.setCursor(this.cursorC);
        }
        this.repaint();
    }
    
    protected boolean cursorOK(final int n, final int n2) {
        if (n >= this.X1 && n <= this.X2 && ((n2 >= this.startPrice && n2 <= this.endPrice) || (n2 >= this.startVolume && n2 <= this.endVolume))) {
            this.setCursor(this.cursorC);
            return true;
        }
        this.setCursor(this.cursorD);
        return false;
    }
    
    protected int datatoX(final int n) {
        return this.X1 + (int)(this.stepX * 0.5) / 2 + (int)((n - this.startData) * this.stepX);
    }
    
    public void doAccumulation() {
        this.calCci = false;
        this.calRoc = false;
        this.calRsi = false;
        this.calVolume = false;
        this.calMacd = false;
        this.calAccum = true;
        Plotter.indicate = 8;
        this.currentDataSet.addAccumulation();
        this.changePlotter();
    }
    
    public void doBAR() {
        this.chartType = 1;
        this.changePlotter();
    }
    
    public void doBollinger() {
        Plotter.inChartIndicate = 6;
        Plotter.indicate = 6;
        if (this.indicatorWindow != null) {
            this.indicatorWindow.dispose();
            this.indicatorWindow = null;
        }
        this.indicatorWindow = new dialogIndicator(false, this, Plotter.indicate);
        this.indicatorWindow.txtName.requestFocus();
        this.indicatorWindow.show();
    }
    
    public void doCANDLE() {
        this.chartType = 2;
        this.changePlotter();
    }
    
    public void doCci() {
        this.calCci = true;
        this.calAccum = false;
        this.calRoc = false;
        this.calRsi = false;
        this.calVolume = false;
        this.calMacd = false;
        Plotter.indicate = 9;
        if (this.indicatorWindow != null) {
            this.indicatorWindow.dispose();
            this.indicatorWindow = null;
        }
        this.indicatorWindow = new dialogIndicator(false, this, Plotter.indicate);
        this.indicatorWindow.txtName.requestFocus();
        this.indicatorWindow.show();
    }
    
    public void doCursor(final boolean xCursor, final boolean yCursor) {
        Parameters.xCursor = xCursor;
        Parameters.yCursor = yCursor;
        this.changePlotter();
    }
    
    public void doEMA() {
        Plotter.indicate = 5;
        if (this.indicatorWindow != null) {
            this.indicatorWindow.dispose();
            this.indicatorWindow = null;
        }
        this.indicatorWindow = new dialogIndicator(false, this, Plotter.indicate);
        this.indicatorWindow.txtName.requestFocus();
        this.indicatorWindow.show();
    }
    
    public void doLINE() {
        this.chartType = 0;
        this.changePlotter();
    }
    
    public void doMACD() {
        this.calCci = false;
        this.calRoc = false;
        this.calRsi = false;
        this.calVolume = false;
        this.calMacd = true;
        this.calAccum = false;
        Plotter.indicate = 4;
        if (this.indicatorWindow != null) {
            this.indicatorWindow.dispose();
            this.indicatorWindow = null;
        }
        this.indicatorWindow = new dialogIndicator(false, this, Plotter.indicate);
        this.indicatorWindow.txtName.requestFocus();
        this.indicatorWindow.show();
    }
    
    public void doMomentum() {
        this.calCci = false;
        this.calRoc = false;
        this.calRsi = false;
        this.calVolume = false;
        this.calMacd = false;
        this.calAccum = false;
        this.calStochast = false;
        this.calMomentum = true;
        Plotter.indicate = 11;
        if (this.indicatorWindow != null) {
            this.indicatorWindow.dispose();
            this.indicatorWindow = null;
        }
        this.indicatorWindow = new dialogIndicator(false, this, Plotter.indicate);
        this.indicatorWindow.txtName.requestFocus();
        this.indicatorWindow.show();
    }
    
    public void doMovingAverage() {
        Plotter.indicate = 1;
        if (this.indicatorWindow != null) {
            this.indicatorWindow.dispose();
            this.indicatorWindow = null;
        }
        this.indicatorWindow = new dialogIndicator(false, this, Plotter.indicate);
        this.indicatorWindow.txtName.requestFocus();
        this.indicatorWindow.show();
    }
    
    public void doOBV() {
        this.calCci = false;
        this.calRoc = false;
        this.calRsi = false;
        this.calVolume = true;
        this.calMacd = false;
        Plotter.indicate = 7;
        this.obvValue = 0;
        if (this.indicatorWindow != null) {
            this.indicatorWindow.dispose();
            this.indicatorWindow = null;
        }
        this.indicatorWindow = new dialogIndicator(false, this, Plotter.indicate);
        this.indicatorWindow.txtName.requestFocus();
        this.indicatorWindow.show();
    }
    
    public void doREFRESH() {
        this.changePlotter();
    }
    
    public void doROC() {
        this.calCci = false;
        this.calRoc = true;
        this.calRsi = false;
        this.calVolume = false;
        this.calMacd = false;
        this.calAccum = false;
        Plotter.indicate = 2;
        if (this.indicatorWindow != null) {
            this.indicatorWindow.dispose();
            this.indicatorWindow = null;
        }
        this.indicatorWindow = new dialogIndicator(false, this, Plotter.indicate);
        this.indicatorWindow.txtName.requestFocus();
        this.indicatorWindow.show();
    }
    
    public void doRSI() {
        this.calCci = false;
        this.calRoc = false;
        this.calRsi = true;
        this.calVolume = false;
        this.calMacd = false;
        this.calAccum = false;
        Plotter.indicate = 3;
        if (this.indicatorWindow != null) {
            this.indicatorWindow.dispose();
            this.indicatorWindow = null;
        }
        this.indicatorWindow = new dialogIndicator(false, this, Plotter.indicate);
        this.indicatorWindow.txtName.requestFocus();
        this.indicatorWindow.show();
    }
    
    public void doSHOW_VALUE() {
        this.cursorValueState ^= true;
        this.changePlotter();
    }
    
    public void doStochast() {
        this.calCci = false;
        this.calRoc = false;
        this.calRsi = false;
        this.calVolume = false;
        this.calMacd = false;
        this.calAccum = false;
        this.calStochast = true;
        Plotter.indicate = 10;
        if (this.indicatorWindow != null) {
            this.indicatorWindow.dispose();
            this.indicatorWindow = null;
        }
        this.indicatorWindow = new dialogIndicator(false, this, Plotter.indicate);
        this.indicatorWindow.txtName.requestFocus();
        this.indicatorWindow.show();
    }
    
    public void doUNZOOM() {
        this.startData = 0;
        this.endData = Array.getLength(this.currentDataSet.chartData) - 1;
        this.changePlotter();
    }
    
    protected void drawCursor() {
        if (!this.mouseVisible) {
            return;
        }
        if (!this.cursorOK(this.mouseX, this.mouseY)) {
            return;
        }
        if (this.mouseDrag) {
            this.drawDrag();
            return;
        }
        if (this.mouseTrend) {
            this.drawTrend();
        }
        this.ShowLabel(this.mouseX, this.mouseY);
        this.chartGraphics.setColor(Parameters.chartBGColor);
        this.chartGraphics.setXORMode(Parameters.chartCursorColor);
        if (Parameters.xCursor) {
            this.chartGraphics.drawLine(this.mouseX, this.startPrice, this.mouseX, this.endPrice);
            this.chartGraphics.drawLine(this.mouseX, this.startVolume, this.mouseX, this.endVolume);
        }
        if (Parameters.yCursor) {
            this.chartGraphics.drawLine(this.X1, this.mouseY, this.X2, this.mouseY);
        }
        this.chartGraphics.setColor(Parameters.chartFGColor);
        this.chartGraphics.setXORMode(Parameters.chartBGColor);
    }
    
    protected void drawDrag() {
        if (!this.mouseVisible) {
            return;
        }
        if (!this.cursorOK(this.mouseX, this.mouseY)) {
            return;
        }
        this.ShowLabel(this.mouseX, this.mouseY);
        this.chartGraphics.setColor(Parameters.chartBGColor);
        this.chartGraphics.setXORMode(Parameters.chartCursorColor);
        int n = this.mouseX;
        int n2 = this.mouseDragX;
        if (n > n2) {
            n2 = this.mouseX;
            n = this.mouseDragX;
        }
        this.chartGraphics.fillRect(n, this.startPrice, n2 - n, this.endPrice - this.startPrice);
        this.chartGraphics.fillRect(n, this.startVolume, n2 - n, this.endVolume - this.startVolume);
        this.chartGraphics.setColor(Parameters.chartFGColor);
    }
    
    protected void drawTrend() {
        if (!this.mouseVisible) {
            return;
        }
        if (!this.cursorOK(this.mouseX, this.mouseY)) {
            return;
        }
        final int xtoData = this.xtoData(this.mouseX);
        final int ytoPrice = this.ytoPrice(this.mouseY);
        final int xtoData2 = this.xtoData(this.mouseTrendX);
        final int ytoPrice2 = this.ytoPrice(this.mouseTrendY);
        final int n = (int)(this.stepX * 0.25);
        final int n2 = this.datatoX(xtoData) + n;
        final int pricetoY = this.pricetoY(ytoPrice);
        final int n3 = this.datatoX(xtoData2) + n;
        final int pricetoY2 = this.pricetoY(ytoPrice2);
        this.chartGraphics.setColor(Parameters.chartBGColor);
        this.chartGraphics.setXORMode(Parameters.chartTrendColor);
        this.chartGraphics.drawLine(n2, pricetoY, n3, pricetoY2);
        this.chartGraphics.setColor(Parameters.chartFGColor);
        this.chartGraphics.setXORMode(Color.white);
    }
    
    protected void drawTrendLine() {
        for (int i = 0; i < this.currentDataSet.trendLine.size(); ++i) {
            this.drawTrendLine((int[])this.currentDataSet.trendLine.elementAt(i));
        }
    }
    
    protected void drawTrendLine(final int[] array) {
        final int n = (int)(this.stepX * 0.25);
        int n2 = this.datatoX(array[0]) + n;
        int n3 = this.pricetoY(array[1]);
        int n4 = this.datatoX(array[2]) + n;
        int n5 = this.pricetoY(array[3]);
        final double n6 = (n5 - n3) / (n4 - n2);
        final double n7 = n3 - n6 * n2;
        if (n2 < this.X1) {
            n2 = this.X1;
            n3 = (int)(n6 * n2 + n7);
        }
        if (n2 > this.X2) {
            n2 = this.X2;
            n3 = (int)(n6 * n2 + n7);
        }
        if (n4 < this.X1) {
            n4 = this.X1;
            n5 = (int)(n6 * n4 + n7);
        }
        if (n4 > this.X2) {
            n4 = this.X2;
            n5 = (int)(n6 * n4 + n7);
        }
        if (n3 < this.startPrice) {
            n3 = this.startPrice;
            n2 = (int)((n3 - n7) / n6);
        }
        if (n3 > this.endPrice) {
            n3 = this.endPrice;
            n2 = (int)((n3 - n7) / n6);
        }
        if (n5 < this.startPrice) {
            n5 = this.startPrice;
            n2 = (int)((n5 - n7) / n6);
        }
        if (n5 > this.endPrice) {
            n5 = this.endPrice;
            n2 = (int)((n5 - n7) / n6);
        }
        if (n2 < this.X1 || n2 > this.X2) {
            return;
        }
        if (n4 < this.X1 || n4 > this.X2) {
            return;
        }
        if (n3 < this.startPrice || n3 > this.endPrice) {
            return;
        }
        if (n5 < this.startPrice || n5 > this.endPrice) {
            return;
        }
        this.chartGraphics.setColor(Parameters.chartTrendColor);
        this.chartGraphics.drawLine(n2, n3, n4, n5);
        this.chartGraphics.setColor(Parameters.chartFGColor);
    }
    
    protected void findFirstEMA(final int n, final int n2, final DataSet set) {
        if (n2 < n - 1) {
            return;
        }
        int n3 = 0;
        for (int i = n2 - n + 1; i <= n2; ++i) {
            final int[] array = (int[])set.chartData[i].getData();
            if (array != null) {
                n3 += array[3];
            }
        }
        this.firstEma = n3 / n;
    }
    
    protected void findMacdEMA(final int n) {
        double n2 = 0.0;
        for (int i = 0; i < n; ++i) {
            if (this.macdValue != null) {
                try {
                    n2 += (double)this.macdValue.elementAt(i);
                }
                catch (Exception ex) {}
            }
        }
        this.firstEma = Math.round((float)(n2 / n));
    }
    
    protected int findRound(final int n) {
        int n2 = 0;
        switch (new Integer(n).toString().length()) {
            case 1: {
                n2 = 1;
                break;
            }
            case 2: {
                n2 = 5;
                break;
            }
            case 3: {
                n2 = 10;
                break;
            }
            case 4: {
                n2 = 100;
                break;
            }
            case 5: {
                n2 = 1000;
                break;
            }
            case 6: {
                n2 = 10000;
                break;
            }
            case 7: {
                n2 = 100000;
                break;
            }
            default: {
                n2 = 5;
                break;
            }
        }
        return n2;
    }
    
    protected String formatDateddmmmyy(final Date date) {
        return String.valueOf(date.getDate()) + "/" + (new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" })[date.getMonth()] + "/" + (date.getYear() + 1900);
    }
    
    protected String formatDateddmmmyyweek(final Date date) {
        return String.valueOf((new String[] { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" })[date.getDay()]) + " " + date.getDate() + "/" + (new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" })[date.getMonth()] + "/" + (date.getYear() + 1900);
    }
    
    protected String formatDateddmmyy(final Date date) {
        return String.valueOf(date.getDate()) + "/" + (date.getMonth() + 1) + "/" + (date.getYear() + 1900);
    }
    
    protected String formatInt(final int n, final int n2) {
        final String value = String.valueOf(n);
        final int length = value.length();
        String substring;
        String s;
        if (length <= n2) {
            substring = "0";
            s = value;
        }
        else {
            substring = value.substring(0, length - n2);
            s = value.substring(length - n2);
        }
        if (substring.length() == 0) {
            substring = "0";
        }
        if (n2 == 0) {
            return substring;
        }
        while (s.length() < n2) {
            s = String.valueOf(s) + "0";
        }
        return String.valueOf(substring) + "." + s;
    }
    
    protected String formatVolume(final int n) {
        if (n >= 100000) {
            return n / 100000 + "L";
        }
        if (n >= 1000) {
            return n / 1000 + "T";
        }
        if (n < -100000) {
            return "-" + n / 100000 + "L";
        }
        if (n < -1000) {
            return "-" + n / 1000 + "T";
        }
        return String.valueOf(n);
    }
    
    protected void initPlotter(final String s) {
        this.resize(this.size().width, this.size().height + 100);
        this.validate();
        this.param = new Parameters();
        if (Parameters.CursorOK) {
            try {
                this.cursorC = new Cursor(1);
                this.cursorW = new Cursor(10);
                this.cursorE = new Cursor(11);
                this.cursorD = new Cursor(0);
                this.setCursor(this.cursorC);
            }
            catch (Exception ex) {
                Parameters.CursorOK = false;
            }
        }
        this.mouseVisible = false;
        this.mouseDrag = false;
        this.mouseTrend = false;
        if (Parameters.applet.getParameter("watermark") != null) {
            this.watermark = this.GetImage(Parameters.applet.getParameter("watermark"));
        }
        this.addDataSet(s);
        this.chartType = 1;
        this.cursorValueState = true;
        this.dataPanel.setLayout(new GridLayout(2, 6, 1, 1));
        this.dataPanel.setBackground(Parameters.defaultFGColor);
        this.lblHDate.setBackground(Parameters.defaultFGColor);
        this.lblHDate.setForeground(Color.white);
        this.lblHDate.resize(this.size().width, 20);
        this.dataPanel.add(this.lblHDate);
        this.add(this.dataPanel);
        this.validate();
        this.repaint();
    }
    
    public boolean mouseDown(final Event event, final int mouseTrendX, final int mouseTrendY) {
        this.moveCursor(mouseTrendX, mouseTrendY);
        if (this.cursorOK(mouseTrendX, mouseTrendY) && (event.modifiers & 0x4) == 0x0 && (event.modifiers & 0x8) == 0x0) {
            if (!this.mouseTrend) {
                this.mouseTrendX = mouseTrendX;
                this.mouseTrendY = mouseTrendY;
                this.mouseTrend = true;
            }
            else {
                this.addTrendLine(this.mouseTrendX, this.mouseTrendY, this.mouseX, this.mouseY);
                this.mouseTrend = false;
            }
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int mouseDragX, final int mouseDragY) {
        this.moveCursor(mouseDragX, mouseDragY);
        this.mouseTrend = false;
        if (this.cursorOK(mouseDragX, mouseDragY)) {
            if (!this.mouseDrag) {
                this.mouseDragX = mouseDragX;
                this.mouseDragY = mouseDragY;
                this.mouseDrag = true;
                this.moveCursor(mouseDragX, mouseDragY);
            }
            if (Parameters.CursorOK) {
                if (mouseDragX < this.mouseDragX) {
                    this.setCursor(this.cursorW);
                }
                else {
                    this.setCursor(this.cursorE);
                }
            }
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int mouseX, final int mouseY) {
        this.mouseDrag = false;
        this.mouseTrend = false;
        if (!this.mouseVisible) {
            this.mouseX = mouseX;
            this.mouseY = mouseY;
            this.mouseVisible = true;
            this.drawCursor();
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int mouseX, final int mouseY) {
        this.mouseDrag = false;
        this.mouseTrend = false;
        if (this.mouseVisible) {
            this.drawCursor();
            this.mouseX = mouseX;
            this.mouseY = mouseY;
            this.mouseVisible = false;
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.moveCursor(n, n2);
        this.mouseDrag = false;
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.moveCursor(n, n2);
        if (this.mouseDrag) {
            int startData;
            int n3;
            if (n < this.mouseDragX) {
                startData = this.xtoData(n);
                n3 = this.xtoData(this.mouseDragX);
            }
            else {
                startData = this.xtoData(this.mouseDragX);
                n3 = this.xtoData(n);
            }
            while (startData + 7 > n3 && startData > this.startData) {
                --startData;
            }
            if (startData + 7 > n3) {
                n3 = startData + 7;
            }
            if (n3 >= this.endData) {
                n3 = this.endData - 1;
            }
            this.startData = startData;
            this.endData = n3 + 1;
            this.changePlotter();
            this.mouseDrag = false;
        }
        return true;
    }
    
    protected void moveCursor(final int mouseX, final int mouseY) {
        if (this.mouseVisible) {
            this.drawCursor();
            this.mouseX = mouseX;
            this.mouseY = mouseY;
            this.drawCursor();
        }
        this.repaint();
    }
    
    protected void myPlot() {
        this.obvValue = 0;
        this.preValue = 0;
        this.SetStep(this.currentDataSet);
        this.myPlot(this.currentDataSet);
    }
    
    protected void myPlot(final DataSet set) {
        this.myPlotAxis(set);
        int[] array = null;
        this.chartGraphics.setColor(Parameters.chartLegendColor);
        final int height = this.titleFontM.getHeight();
        this.chartGraphics.setFont(Parameters.titleFont);
        this.chartGraphics.drawString(set.Name, this.X1 + 2, this.startPrice + height);
        int n = -1;
        int n2 = -1;
        int n3 = -1;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        int n9 = 0;
        for (int i = this.startData; i <= this.endData; ++i) {
            final int[] array2 = (int[])set.chartData[i].getData();
            this.sOutput = String.valueOf(this.formatDateddmmmyyweek(set.chartData[i].getDate())) + "|";
            if (Array.getLength(array2) == 5) {
                this.chartGraphics.setColor(Parameters.chartFGColor);
                this.myPlotChart(array, array2, i);
            }
            for (int j = 0; j < this.currentDataSet.indicator.size(); ++j) {
                final Indicator indicator = this.currentDataSet.indicator.elementAt(j);
                this.chartGraphics.setColor(Parameters.indicatorColor[j % Array.getLength(Parameters.indicatorColor)]);
                if (indicator.getType() == 6 && i >= indicator.getValue()) {
                    this.myPlotBollinger(n4, i);
                    ++n4;
                    this.chartGraphics.drawString("B Bands ( " + indicator.getValue() + " , " + indicator.getBollingerFact() + " )", this.X1 + 2, this.startPrice + height * (j + 2));
                    this.chartGraphics.setColor(Parameters.chartFGColor);
                }
                else if (indicator.getType() == 1) {
                    this.myPlotMovingAverage(indicator.getValue(), i, set);
                    this.chartGraphics.drawString("MA " + indicator.getValue() + " days", this.X1 + 2, this.startPrice + height * (j + 2));
                    this.chartGraphics.setColor(Parameters.chartFGColor);
                }
                else if (indicator.getType() == 5) {
                    if (indicator.getEma() == 0.0 || i == this.startData) {
                        this.findFirstEMA(indicator.getValue(), i, set);
                        indicator.setEma(this.firstEma);
                        this.myPlotIndicator(i, (int)this.firstEma, (int)this.firstEma);
                        this.firstEma = 0.0;
                    }
                    if (indicator.getEma() != 0.0) {
                        this.myPlotEMA(indicator.getValue(), i, set, indicator);
                        this.chartGraphics.drawString("EMA " + indicator.getValue() + " days", this.X1 + 2, this.startPrice + height * (j + 2));
                        this.chartGraphics.setColor(Parameters.chartFGColor);
                    }
                }
                else if (indicator.getType() == 3) {
                    this.chartGraphics.setColor(Parameters.chartFGColor);
                    if (i >= this.days) {
                        this.myPlotRSI(indicator.getValue(), i, ++n);
                        this.chartGraphics.drawString("RSI  " + indicator.getValue() + " days", this.X1 + 2, this.startVolume + height);
                        this.chartGraphics.setColor(Parameters.chartFGColor);
                    }
                }
                else if (indicator.getType() == 2 && i >= indicator.getValue() - 1) {
                    this.myPlotROC(indicator.getValue(), i, ++n3);
                    this.chartGraphics.drawString("ROC  " + indicator.getValue() + " days", this.X1 + 2, this.startVolume + height);
                    this.chartGraphics.setColor(Parameters.chartFGColor);
                }
                else if (indicator.getType() == 4) {
                    if (i == this.startData) {
                        this.findMacdEMA(indicator.getTrigger());
                        indicator.setMacdEma(this.firstEma);
                        this.firstEma = 0.0;
                        n2 = -1;
                    }
                    if (i < indicator.getEmaDays1() - 1 || i < indicator.getEmaDays2() - 1) {
                        this.chartGraphics.setColor(Parameters.chartFGColor);
                    }
                    else {
                        this.sOutput = String.valueOf(this.sOutput) + array2[3] + "|";
                        this.myPlotMACD(indicator, i, ++n2);
                        this.chartGraphics.drawString("Macd & Signal Line", this.X1 + 2, this.startVolume + height);
                        this.chartGraphics.setColor(Parameters.chartFGColor);
                    }
                }
                else if (indicator.getType() == 7) {
                    this.myPlotOBV(indicator, i, set, n7);
                    this.chartGraphics.drawString("On Balance Volume", this.X1 + 2, this.startVolume + height);
                    this.chartGraphics.setColor(Parameters.chartFGColor);
                    ++n7;
                }
                else if (indicator.getType() == 8) {
                    this.myPlotAccum(i, n6, set);
                    this.chartGraphics.drawString("Accumulation Distribution", this.X1 + 2, this.startVolume + height);
                    ++n6;
                    this.chartGraphics.setColor(Parameters.chartFGColor);
                }
                else if (indicator.getType() == 9) {
                    if (i >= 2 * indicator.getValue() - 2) {
                        this.myPlotCci(i, n5);
                        this.chartGraphics.drawString("Commodity Channel Index", this.X1 + 2, this.startVolume + height);
                        this.chartGraphics.setColor(Parameters.chartFGColor);
                        ++n5;
                    }
                }
                else if (indicator.getType() == 10 && i >= indicator.getValue() - 1) {
                    this.sOutput = String.valueOf(this.sOutput) + array2[3] + "|";
                    this.myPlotStochast(i, n8, indicator.getValue());
                    this.chartGraphics.drawString("STOCHASTIC OSCILLATOR", this.X1 + 2, this.startVolume + height);
                    this.chartGraphics.setColor(Parameters.chartFGColor);
                    ++n8;
                }
                else if (indicator.getType() == 11 && i >= indicator.getValue() - 1) {
                    this.myPlotMomentum(i, n9, indicator.getValue());
                    this.chartGraphics.drawString("MOMENTUM INDICATOR", this.X1 + 2, this.startVolume + height);
                    this.chartGraphics.setColor(Parameters.chartFGColor);
                    ++n9;
                }
            }
            array = array2;
            this.sOutput = "";
        }
    }
    
    protected void myPlotAccum(final int n, final int n2, final DataSet set) {
        final int[] array = (int[])set.chartData[n].getData();
        final int intValue = this.plotValue.elementAt(n2);
        this.sOutput = String.valueOf(this.sOutput) + intValue;
        final int datatoX = this.datatoX(n);
        final int n3 = datatoX - (int)this.stepX;
        final int indicatetoY = this.IndicatetoY(intValue);
        this.preValue = ((this.preValue == 0) ? indicatetoY : this.preValue);
        if (n3 > this.X1) {
            this.chartGraphics.drawLine(n3, this.preValue, datatoX, indicatetoY);
            this.chartGraphics.drawLine(n3, this.preValue - 1, datatoX, indicatetoY - 1);
            this.preValue = indicatetoY;
        }
    }
    
    protected void myPlotAxis(final DataSet set) {
        final int n = this.labelFontM.getHeight() / 2;
        for (int i = this.minRound; i <= this.maxRound; i += this.interval) {
            final int pricetoY = this.pricetoY(i);
            this.chartGraphics.setColor(Parameters.chartGridColor);
            this.chartGraphics.drawLine(this.X1, pricetoY, this.X2, pricetoY);
            final String formatInt = this.formatInt(i, 0);
            this.chartGraphics.setColor(Parameters.chartLegendColor);
            this.chartGraphics.drawString(formatInt, this.X1 - (this.labelFontM.stringWidth(formatInt) + Parameters.chartBorder), pricetoY + n);
        }
        this.plotVolumeAxis();
        final int n2 = this.labelFontM.getHeight() + this.endVolume + Parameters.chartBorder;
        final int n3 = (int)(this.labelFontM.stringWidth("88-WWW-8888") * 2 / this.stepX);
        this.chartGraphics.setFont(Parameters.labelFont);
        for (int j = this.X1 + (int)(this.stepX * 0.5); j <= this.X2; j += (int)(n3 * this.stepX)) {
            final int xtoData = this.xtoData(j);
            if (xtoData >= Array.getLength(set.chartData)) {
                break;
            }
            final String formatDateddmmmyy = this.formatDateddmmmyy(set.chartData[xtoData].getDate());
            final int n4 = this.labelFontM.stringWidth(formatDateddmmmyy) / 2;
            this.chartGraphics.setColor(Parameters.chartGridColor);
            this.chartGraphics.drawLine(j, this.startVolume, j, this.endVolume + Parameters.chartBorder + Parameters.chartBorder);
            this.chartGraphics.drawLine(j, this.startPrice, j, this.endPrice);
            this.chartGraphics.setColor(Parameters.chartLegendColor);
            this.chartGraphics.drawString(formatDateddmmmyy, j - n4, n2);
        }
        this.chartGraphics.setFont(Parameters.labelFont);
        int endPrice;
        if (this.labelFontM.getHeight() < this.startVolume - this.endPrice) {
            endPrice = this.endPrice + this.labelFontM.getHeight() / 2 + (this.startVolume - this.endPrice) / 2;
        }
        else {
            endPrice = this.endPrice;
        }
        final int stringWidth = this.labelFontM.stringWidth(Parameters.copyright);
        int x1;
        if (stringWidth < this.X2 - this.X1) {
            x1 = this.X1 + (this.X2 - this.X1 - stringWidth) / 2;
        }
        else {
            x1 = this.X1;
        }
        this.chartGraphics.drawString(Parameters.copyright, x1, endPrice);
        this.chartGraphics.setColor(Parameters.chartFGColor);
    }
    
    protected void myPlotBollinger(final int n, final int n2) {
        try {
            final int n3 = (int)(this.highLow[n][0] / 100.0);
            final int n4 = (int)(this.highLow[n][1] / 100.0);
            final int datatoX = this.datatoX(n2);
            final int n5 = datatoX - (int)this.stepX;
            this.sOutput = String.valueOf(this.sOutput) + this.highLow[n][0] + "|" + this.highLow[n][1];
            final int pricetoY = this.pricetoY(n3);
            final int pricetoY2 = this.pricetoY(n4);
            if (this.prehighValue == 0) {
                this.prehighValue = pricetoY;
            }
            if (this.prelowValue == 0) {
                this.prelowValue = pricetoY2;
            }
            if (n5 >= this.X1) {
                this.chartGraphics.setColor(Color.green);
                this.chartGraphics.drawLine(n5, this.prehighValue, datatoX, pricetoY);
                this.chartGraphics.setColor(Color.red);
                this.chartGraphics.drawLine(n5, this.prelowValue, datatoX, pricetoY2);
                this.prehighValue = pricetoY;
                this.prelowValue = pricetoY2;
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {}
    }
    
    protected void myPlotCci(final int n, final int n2) {
        final int n3 = this.datatoX(n) - (int)this.stepX;
        final int indicatetoY = this.IndicatetoY(this.cciValues[n2]);
        this.preValue = ((this.preValue == 0) ? indicatetoY : this.preValue);
        this.sOutput = String.valueOf(this.sOutput) + this.cciValues[n2];
        final int datatoX = this.datatoX(n);
        final int n4 = datatoX - (int)this.stepX;
        if (n4 > this.X1) {
            this.chartGraphics.drawLine(n4, this.preValue, datatoX, indicatetoY);
            this.chartGraphics.setColor(Parameters.chartFGColor);
            this.preValue = indicatetoY;
        }
    }
    
    protected void myPlotChart(final int[] array, final int[] array2, final int n) {
        final int datatoX = this.datatoX(n);
        final int pricetoY = this.pricetoY(array2[0] / 100);
        final int pricetoY2 = this.pricetoY(array2[1] / 100);
        final int pricetoY3 = this.pricetoY(array2[2] / 100);
        final int pricetoY4 = this.pricetoY(array2[3] / 100);
        final int volumetoY = this.volumetoY(array2[4]);
        if (this.calVolume) {
            int n2 = (int)(this.stepX * 0.5);
            if (n2 < 2) {
                n2 = 2;
            }
            this.chartGraphics.fillRect(datatoX, volumetoY, n2, this.endVolume - volumetoY);
        }
        final int n3 = (int)(this.stepX * 0.5);
        final int n4 = this.X1 + n3 + (int)((n - this.startData) * this.stepX);
        if (this.chartType == 0) {
            final int pricetoY5 = this.pricetoY(array2[3] / 100);
            int pricetoY6;
            if (array == null) {
                pricetoY6 = pricetoY5;
            }
            else {
                pricetoY6 = this.pricetoY(array[3] / 100);
            }
            final int n5 = n4 - (int)this.stepX;
            if (n5 >= this.X1) {
                this.chartGraphics.drawLine(n5, pricetoY6, n4, pricetoY5);
            }
            return;
        }
        if (this.chartType == 1) {
            if (n3 > 1) {
                this.chartGraphics.drawLine(n4 - 1, pricetoY3, n4 + 1, pricetoY3);
                this.chartGraphics.drawLine(n4 - 1, pricetoY2, n4 + 1, pricetoY2);
            }
            this.chartGraphics.drawLine(n4, pricetoY3, n4, pricetoY2);
            if (n4 + n3 > this.X2) {
                this.chartGraphics.drawLine(n4, pricetoY4, this.X2, pricetoY4);
            }
            else {
                this.chartGraphics.drawLine(n4, pricetoY4, n4 + n3, pricetoY4);
            }
            this.chartGraphics.drawLine(n4, pricetoY, n4 - n3, pricetoY);
        }
        else if (this.chartType == 2) {
            if (n3 > 1) {
                this.chartGraphics.drawLine(n4 - 1, pricetoY3, n4 + 1, pricetoY3);
                this.chartGraphics.drawLine(n4 - 1, pricetoY2, n4 + 1, pricetoY2);
            }
            if (pricetoY > pricetoY4) {
                this.chartGraphics.drawRect(n4 - n3 / 2, pricetoY4, n3, pricetoY - pricetoY4);
                this.chartGraphics.drawLine(n4, pricetoY3, n4, pricetoY);
                this.chartGraphics.drawLine(n4, pricetoY4, n4, pricetoY2);
            }
            else {
                this.chartGraphics.fillRect(n4 - n3 / 2, pricetoY, n3, pricetoY4 - pricetoY);
                this.chartGraphics.drawLine(n4, pricetoY3, n4, pricetoY4);
                this.chartGraphics.drawLine(n4, pricetoY, n4, pricetoY2);
            }
        }
    }
    
    protected void myPlotEMA(final int n, final int n2, final DataSet set, final Indicator indicator) {
        if (n2 < n) {
            return;
        }
        final double ema = indicator.getEma();
        final double n3 = 2.0 / n;
        int n4 = 0;
        final int[] array = (int[])set.chartData[n2].getData();
        if (array != null) {
            n4 = array[3];
        }
        final double ema2 = ema + (n4 - ema) * n3;
        this.sOutput = String.valueOf(this.sOutput) + ema2;
        this.myPlotIndicator(n2, (int)ema, (int)ema2);
        indicator.setEma(ema2);
    }
    
    protected void myPlotIndicator(final int n, final int n2, final int n3) {
        final int n4 = this.datatoX(n - 1) + (int)(this.stepX * 1.25);
        final int n5 = this.datatoX(n) + (int)(this.stepX * 1.25);
        final int pricetoY = this.pricetoY(n2 / 100);
        final int pricetoY2 = this.pricetoY(n3 / 100);
        if (n4 < this.X1 || n5 < this.X1) {
            return;
        }
        if (pricetoY < this.startPrice || pricetoY2 < this.startPrice) {
            return;
        }
        if (n4 > this.X2 || n5 > this.X2) {
            return;
        }
        if (pricetoY > this.endPrice || pricetoY2 > this.endPrice) {
            return;
        }
        if (Parameters.indicatorWidth <= 1) {
            this.chartGraphics.drawLine(n4, pricetoY, n5, pricetoY2);
        }
        else {
            final double n6 = Parameters.indicatorWidth / 2.0;
            final double n7 = n5 - n4;
            final double n8 = pricetoY2 - pricetoY;
            double n9;
            if (n4 == n5) {
                n9 = 3.141592653589793;
            }
            else {
                n9 = Math.atan(n8 / n7) + 1.5707963267948966;
            }
            final int n10 = (int)(n6 * Math.cos(n9));
            final int n11 = (int)(n6 * Math.sin(n9));
            this.chartGraphics.fillPolygon(new int[] { n4 - n10, n5 - n10 + 1, n5 + n10 + 1, n4 + n10 }, new int[] { pricetoY - n11, pricetoY2 - n11, pricetoY2 + n11 + 1, pricetoY + n11 + 1 }, 4);
        }
    }
    
    protected void myPlotMACD(final Indicator indicator, final int n, final int n2) {
        final int emaDays1 = indicator.getEmaDays1();
        final int emaDays2 = indicator.getEmaDays2();
        final int n3 = (emaDays1 > emaDays2) ? emaDays1 : emaDays2;
        double doubleValue = 0.0;
        boolean b = false;
        final int datatoX = this.datatoX(n);
        final int n4 = datatoX - (int)this.stepX;
        if (this.macdValue != null) {
            doubleValue = this.macdValue.elementAt(n2);
            double doubleValue2;
            if (n2 > 0) {
                doubleValue2 = this.macdValue.elementAt(n2 - 1);
            }
            else {
                doubleValue2 = doubleValue;
            }
            this.sOutput = String.valueOf(this.sOutput) + doubleValue / 100.0 + "|";
            final int indicatetoY = this.IndicatetoY(Math.round((float)(doubleValue / 100.0)));
            final int indicatetoY2 = this.IndicatetoY(Math.round((float)(doubleValue2 / 100.0)));
            this.hIndicator.put(new Integer(n), new Integer(Math.round((float)(doubleValue / 100.0))));
            if (n4 >= this.X1) {
                this.chartGraphics.drawLine(n4, indicatetoY2, datatoX, indicatetoY);
                this.chartGraphics.setColor(Parameters.chartFGColor);
                b = true;
            }
        }
        if (n2 < indicator.getTrigger() - 1) {
            return;
        }
        if (n2 == indicator.getTrigger() - 1) {
            final double macdEma = indicator.getMacdEma();
            final int indicatetoY3 = this.IndicatetoY(Math.round((float)(macdEma / 100.0)));
            this.sOutput = String.valueOf(this.sOutput) + Math.round(macdEma / 100.0) + "first";
            if (n4 >= this.X1) {
                this.chartGraphics.setColor(Color.black);
                this.chartGraphics.drawLine(n4, indicatetoY3, datatoX, indicatetoY3);
                this.chartGraphics.setColor(Parameters.chartFGColor);
            }
        }
        else {
            final double macdEma2 = indicator.getMacdEma();
            final double macdEma3 = macdEma2 + (doubleValue - macdEma2) * (2.0 / indicator.getTrigger());
            final int indicatetoY4 = this.IndicatetoY(Math.round((float)(macdEma2 / 100.0)));
            final int indicatetoY5 = this.IndicatetoY(Math.round((float)(macdEma3 / 100.0)));
            this.sOutput = String.valueOf(this.sOutput) + Math.round((float)(macdEma3 / 100.0));
            this.hMacd.put(new Integer(n), new Integer(Math.round((float)(macdEma3 / 100.0))));
            if (n4 >= this.X1 && b) {
                this.chartGraphics.setColor(Color.black);
                this.chartGraphics.drawLine(n4, indicatetoY4, datatoX, indicatetoY5);
                indicator.setMacdEma(macdEma3);
                this.chartGraphics.setColor(Parameters.chartFGColor);
            }
        }
    }
    
    protected void myPlotMomentum(final int n, final int n2, final int n3) {
        final int round = Math.round((float)(Object)this.plotValue.elementAt(n2));
        int round2;
        if (n2 > 0) {
            round2 = Math.round((float)(Object)this.plotValue.elementAt(n2 - 1));
        }
        else {
            round2 = round;
        }
        final int datatoX = this.datatoX(n);
        final int n4 = datatoX - (int)this.stepX;
        this.sOutput = String.valueOf(this.sOutput) + round;
        final int indicatetoY = this.IndicatetoY(round);
        final int indicatetoY2 = this.IndicatetoY(round2);
        if (n4 > this.X1) {
            this.chartGraphics.setColor(Color.black);
            this.chartGraphics.drawLine(n4, indicatetoY2, datatoX, indicatetoY);
            this.chartGraphics.setColor(Parameters.chartFGColor);
        }
    }
    
    protected void myPlotMovingAverage(final int n, final int n2, final DataSet set) {
        if (n2 < n) {
            return;
        }
        int n3 = 0;
        for (int i = n2 - n; i < n2; ++i) {
            final int[] array = (int[])set.chartData[i].getData();
            if (array != null) {
                n3 += array[3];
            }
        }
        int n4 = n3;
        final int[] array2 = (int[])set.chartData[n2].getData();
        if (array2 != null) {
            n4 += array2[3];
        }
        final int[] array3 = (int[])set.chartData[n2 - n].getData();
        if (array3 != null) {
            n4 -= array3[3];
        }
        this.myPlotIndicator(n2, n3 / n, n4 / n);
    }
    
    protected void myPlotOBV(final Indicator indicator, final int n, final DataSet set, final int n2) {
        final int intValue = this.plotValue.elementAt(n2);
        final int datatoX = this.datatoX(n);
        final int n3 = datatoX - (int)this.stepX;
        this.sOutput = String.valueOf(this.sOutput) + intValue;
        final int obvtoY = this.obvtoY(intValue);
        if (this.preValue == 0) {
            this.preValue = obvtoY;
        }
        if (n3 > this.X1) {
            this.chartGraphics.setColor(Color.red);
            this.chartGraphics.drawLine(n3, this.preValue, datatoX, obvtoY);
            this.chartGraphics.drawLine(n3, this.preValue - 1, datatoX, obvtoY - 1);
            this.preValue = obvtoY;
        }
    }
    
    protected void myPlotROC(final int n, final int n2, final int n3) {
        final int datatoX = this.datatoX(n2);
        final int n4 = datatoX - (int)this.stepX;
        if (n2 < n - 1) {
            return;
        }
        final int round = Math.round((float)(Object)this.plotValue.elementAt(n3));
        this.sOutput = String.valueOf(this.sOutput) + round;
        final int indicatetoY = this.IndicatetoY(round);
        if (this.preValue == 0) {
            this.preValue = indicatetoY;
        }
        this.hIndicator.put(new Integer(n2), new Integer(round));
        if (n4 >= this.X1) {
            this.chartGraphics.drawLine(n4, this.preValue, datatoX, indicatetoY);
            this.preValue = indicatetoY;
        }
    }
    
    protected void myPlotRSI(final int n, final int n2, final int n3) {
        if (n2 <= n) {
            return;
        }
        final int intValue = this.plotValue.elementAt(n3);
        final int datatoX = this.datatoX(n2);
        final int n4 = datatoX - (int)this.stepX;
        this.hIndicator.put(new Integer(n2), new Integer(intValue));
        final int indicatetoY = this.IndicatetoY(intValue);
        if (this.preValue == 0) {
            this.preValue = indicatetoY;
        }
        if (n4 >= this.X1) {
            this.chartGraphics.drawLine(n4, this.preValue, datatoX, indicatetoY);
            this.preValue = indicatetoY;
        }
    }
    
    protected void myPlotStochast(final int n, final int n2, final int n3) {
        final int round = Math.round((float)(Object)this.plotValue.elementAt(n2));
        int round2;
        if (n2 > 0) {
            round2 = Math.round((float)(Object)this.plotValue.elementAt(n2 - 1));
        }
        else {
            round2 = round;
        }
        final int datatoX = this.datatoX(n);
        final int n4 = datatoX - (int)this.stepX;
        int n5 = 0;
        final int indicatetoY = this.IndicatetoY(round);
        final int indicatetoY2 = this.IndicatetoY(round2);
        if (n4 > this.X1) {
            this.chartGraphics.setColor(Color.black);
            this.chartGraphics.drawLine(n4, indicatetoY2, datatoX, indicatetoY);
            this.chartGraphics.setColor(Parameters.chartFGColor);
            this.sOutput = String.valueOf(this.sOutput) + round;
            if (n2 >= n3 - 1) {
                for (int i = n2 - (n3 - 1); i <= n2; ++i) {
                    n5 += Math.round((float)this.plotValue.elementAt(i));
                }
                final int round3 = Math.round(n5 / n3);
                this.sOutput = String.valueOf(this.sOutput) + "|" + round3;
                final int indicatetoY3 = this.IndicatetoY(round3);
                this.preValue = ((this.preValue == 0) ? indicatetoY3 : this.preValue);
                this.chartGraphics.setColor(Color.red);
                this.chartGraphics.drawLine(n4, this.preValue, datatoX, indicatetoY3);
                this.chartGraphics.setColor(Parameters.chartFGColor);
                this.preValue = indicatetoY3;
            }
        }
    }
    
    protected int obvtoY(final int n) {
        return this.endVolume - (int)((n - this.minObv) * this.stepOBV);
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    protected void plotVolumeAxis() {
        final int n = this.labelFontM.getHeight() / 2;
        this.labelFontM.stringWidth(this.formatInt(this.minIndicate, 0));
        this.chartGraphics.setColor(Parameters.chartGridColor);
        this.chartGraphics.drawLine(this.X1, this.startVolume, this.X2, this.startVolume);
        String s;
        if (this.calRoc || this.calRsi || this.calMacd || this.calCci || this.calStochast || this.calMomentum) {
            s = this.formatInt(this.minIndicate, 0);
        }
        else if (this.calAccum) {
            s = this.formatVolume(this.minIndicate);
        }
        else if (this.calVolume) {
            s = this.formatVolume(this.minVolume);
        }
        else {
            s = this.formatVolume(this.minVolume);
        }
        final int stringWidth = this.labelFontM.stringWidth(s);
        this.chartGraphics.setColor(Parameters.chartLegendColor);
        this.chartGraphics.drawString(s, this.X1 - (Parameters.chartBorder + stringWidth), this.endVolume + n);
        if (this.calCci) {
            this.chartGraphics.setColor(Parameters.chartGridColor);
            final int indicatetoY = this.IndicatetoY(100);
            if (indicatetoY < this.endVolume && indicatetoY > this.startVolume) {
                this.chartGraphics.drawLine(this.X1, indicatetoY, this.X2, indicatetoY);
                final int stringWidth2 = this.labelFontM.stringWidth("100");
                this.chartGraphics.setColor(Parameters.chartLegendColor);
                this.chartGraphics.drawString("100", this.X1 - (Parameters.chartBorder + stringWidth2), indicatetoY + n);
            }
            final int indicatetoY2 = this.IndicatetoY(-100);
            if (indicatetoY2 > this.startVolume && indicatetoY2 < this.endVolume) {
                this.chartGraphics.setColor(Parameters.chartGridColor);
                this.chartGraphics.drawLine(this.X1, indicatetoY2, this.X2, indicatetoY2);
                final int stringWidth3 = this.labelFontM.stringWidth("-100");
                this.chartGraphics.setColor(Parameters.chartLegendColor);
                this.chartGraphics.drawString("-100", this.X1 - (Parameters.chartBorder + stringWidth3), indicatetoY2 + n);
            }
        }
        else {
            this.chartGraphics.setColor(Parameters.chartGridColor);
            this.chartGraphics.drawLine(this.X1, (this.endVolume + this.startVolume) / 2, this.X2, (this.endVolume + this.startVolume) / 2);
            String s2;
            if (this.calRoc || this.calRsi || this.calMacd || this.calStochast || this.calMomentum) {
                s2 = this.formatInt((this.maxIndicate + this.minIndicate) / 2, 0);
            }
            else if (this.calAccum) {
                s2 = this.formatVolume((this.maxIndicate + this.minIndicate) / 2);
            }
            else {
                s2 = this.formatVolume((this.maxVolume + this.minVolume) / 2);
            }
            final int stringWidth4 = this.labelFontM.stringWidth(s2);
            this.chartGraphics.setColor(Parameters.chartLegendColor);
            this.chartGraphics.drawString(s2, this.X1 - (Parameters.chartBorder + stringWidth4), (this.endVolume + this.startVolume) / 2 + n);
        }
        this.chartGraphics.setColor(Parameters.chartGridColor);
        this.chartGraphics.drawLine(this.X1, this.endVolume, this.X2, this.endVolume);
        String s3;
        if (this.calRoc || this.calRsi || this.calMacd || this.calCci || this.calStochast || this.calMomentum) {
            s3 = this.formatInt(this.maxIndicate, 0);
        }
        else if (this.calAccum) {
            s3 = this.formatVolume(this.maxIndicate);
        }
        else {
            s3 = this.formatVolume(this.maxVolume);
        }
        final int stringWidth5 = this.labelFontM.stringWidth(s3);
        this.chartGraphics.setColor(Parameters.chartLegendColor);
        this.chartGraphics.drawString(s3, this.X1 - (Parameters.chartBorder + stringWidth5), this.startVolume + n);
    }
    
    protected int pricetoY(final int n) {
        return this.endPrice - (int)((n - this.minRound) * this.stepPrice);
    }
    
    public void removeAllIndicators() {
        this.calRsi = false;
        this.calRoc = false;
        this.calMacd = false;
        this.calAccum = false;
        this.calVolume = true;
        this.calCci = false;
        this.calStochast = false;
        this.calMomentum = false;
        this.preValue = 0;
        this.currentDataSet.removeAllIndicators();
        this.changePlotter();
    }
    
    public void removeAllTrendLine() {
        this.currentDataSet.removeAllTrendLine();
        this.changePlotter();
    }
    
    public void removeLastIndicators() {
        final int type = this.currentDataSet.indicator.elementAt(this.currentDataSet.indicator.size() - 1).getType();
        if (type == 2 || type == 3 || type == 4 || type == 8 || type == 9 || type == 10 || type == 11) {
            this.calRsi = false;
            this.calRoc = false;
            this.calMacd = false;
            this.calAccum = false;
            this.calVolume = true;
            this.calCci = false;
            this.calStochast = false;
            this.calMomentum = false;
            this.preValue = 0;
        }
        this.currentDataSet.removeLastIndicators();
        this.changePlotter();
    }
    
    public void removeLastTrendLine() {
        this.currentDataSet.removeLastTrendLine();
        this.changePlotter();
    }
    
    protected int roundMax(int n, final int n2) {
        int n3 = 0;
        try {
            int n4 = Array.getLength(this.valueInterval) - 1;
            boolean b = false;
            while (n4 > 0 && this.valueInterval[n4] >= n2) {
                final int n5 = n - this.valueInterval[n4];
                if (n5 < 0) {
                    b = true;
                    --n4;
                }
                else {
                    n3 += this.valueInterval[n4];
                    n = n5;
                    b = false;
                }
            }
            if (b) {
                n3 += this.valueInterval[n4 + 1];
            }
            else if (n4 >= 0) {
                n3 += this.valueInterval[n4];
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        }
        return n3;
    }
    
    protected int roundMin(int n, final int n2) {
        int n3 = 0;
        try {
            int i = Array.getLength(this.valueInterval) - 1;
            while (i > 0) {
                if (this.valueInterval[i] < n2) {
                    break;
                }
                final int n4 = n - this.valueInterval[i];
                if (n4 < 0) {
                    --i;
                }
                else {
                    n3 += this.valueInterval[i];
                    n = n4;
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        }
        return n3;
    }
    
    protected void setAccumulation(final int n, final int n2, final DataSet set) {
        this.plotValue = new Vector();
        int n3 = 0;
        for (int i = n; i <= n2; ++i) {
            final int[] array = (int[])set.chartData[i].getData();
            if (array != null) {
                n3 += (array[3] - array[2] - (array[1] - array[3])) / (array[1] - array[2]) * array[4];
                this.plotValue.addElement(new Integer(n3));
            }
        }
    }
    
    protected void setAxis(final int n, final int n2) {
        this.maxRound = this.roundMax(n, this.findRound(n));
        this.minRound = this.roundMin(n2, this.findRound(n2));
        int i = Array.getLength(this.nInterval);
        while (i >= 0) {
            --i;
            final int n3 = (this.maxRound - this.minRound) / this.nInterval[i];
            if (n3 <= 0 || n3 < this.nInterval[i]) {
                continue;
            }
            final int roundMax = this.roundMax(n3, this.findRound(n3));
            this.maxRound = this.minRound + this.nInterval[i] * roundMax;
            this.interval = roundMax;
            break;
        }
    }
    
    protected void setBollingerValue(final int n, final int n2, final Indicator indicator, final DataSet set) {
        final int value = indicator.getValue();
        if (n2 < value) {
            return;
        }
        final int n3 = (n < value) ? value : n;
        this.highLow = new double[1 + n2 - n3][2];
        final boolean b = false;
        this.prelowValue = (b ? 1 : 0);
        this.prehighValue = (b ? 1 : 0);
        int n4 = 0;
        for (int i = n3; i <= n2; ++i) {
            double n5 = 0.0;
            for (int j = i - value; j < i; ++j) {
                final int[] array = (int[])set.chartData[j].getData();
                final int n6 = array[3];
                n5 += array[3];
            }
            final double n7 = n5 / value;
            double n8 = 0.0;
            for (int k = i - value; k < i; ++k) {
                final double n9 = ((int[])set.chartData[k].getData())[3] - n7;
                n8 += n9 * n9;
            }
            final double sqrt = Math.sqrt(n8 / value);
            final double n10 = indicator.getBollingerFact() * sqrt;
            final double n11 = indicator.getBollingerFact() * sqrt;
            this.highLow[n4][0] = Math.round((float)(n7 + n10));
            this.highLow[n4][1] = Math.round((float)(n7 - n11));
            ++n4;
        }
    }
    
    protected void setCciValues(final int n, final int n2, final int n3, final DataSet set) {
        final int n4 = (n >= n3) ? n : n3;
        final int[] array = new int[1 + n3 + n2 - n];
        int n5 = 0;
        if (n2 < n3) {
            return;
        }
        for (int i = n; i <= n2; ++i) {
            final int[] array2 = (int[])set.chartData[i].getData();
            array[n5] = (array2[1] + array2[2] + array2[3]) / 3;
            ++n5;
        }
        if (this.cciValues != null) {
            this.cciValues = null;
        }
        this.cciValues = new int[1 + n2 - n4];
        final double[] array3 = new double[2 + n2 - n4];
        int n6 = 0;
        final int n7 = n3 - 1;
        for (int j = n; j <= n2; ++j) {
            if (j >= n7) {
                int n8 = 0;
                double n9 = 0.0;
                for (int k = j - n7; k <= j; ++k) {
                    n8 += array[k];
                }
                final double n10 = n8 / n3;
                for (int l = j - n7; l <= j; ++l) {
                    n9 += Math.abs(n10 - array[l]);
                }
                array3[n6] = n9 / n3;
                if (n6 >= n7) {
                    double n11 = 0.0;
                    for (int n12 = n6 - n7; n12 <= n6; ++n12) {
                        n11 += array3[n12];
                    }
                    this.cciValues[n6 - n7] = (int)Math.round((array[j] - n10) / (0.015 * (n11 / n3)));
                }
                ++n6;
            }
        }
    }
    
    protected void setMacdValue(final Indicator indicator, final int n, final DataSet set, final int n2) {
        this.macdValue = new Vector();
        double n3 = 0.0;
        double n4 = 0.0;
        int n5 = 0;
        int n6 = 0;
        this.firstEma = 0.0;
        final Vector vector = new Vector<Double>();
        final Vector vector2 = new Vector<Double>();
        final int emaDays1 = indicator.getEmaDays1();
        final int emaDays2 = indicator.getEmaDays2();
        if (n2 < emaDays1 || n2 < emaDays2) {
            return;
        }
        final int max = Math.max(indicator.getEmaDays1(), indicator.getEmaDays2());
        final int min = Math.min(indicator.getEmaDays1(), indicator.getEmaDays2());
        final int n7 = (max > n) ? (max - 1) : n;
        this.findFirstEMA(max, n7, set);
        indicator.setEma1(this.firstEma);
        vector.addElement(new Double(this.firstEma));
        this.firstEma = 0.0;
        double ema1 = indicator.getEma1();
        final double n8 = 2.0 / max;
        for (int i = n7 + 1; i <= n2; ++i) {
            final int[] array = (int[])set.chartData[i].getData();
            if (array != null) {
                n3 = array[3];
            }
            n3 = ema1 + (n3 - ema1) * n8;
            vector.addElement(new Double(n3));
            ema1 = n3;
        }
        final int n9 = (min > n) ? (min - 1) : n;
        this.findFirstEMA(min, n9, set);
        indicator.setEma2(this.firstEma);
        vector2.addElement(new Double(this.firstEma));
        this.firstEma = 0.0;
        double ema2 = indicator.getEma2();
        final double n10 = 2.0 / min;
        for (int j = n9 + 1; j <= n2; ++j) {
            final int[] array2 = (int[])set.chartData[j].getData();
            if (array2 != null) {
                n4 = array2[3];
            }
            n4 = ema2 + (n4 - ema2) * n10;
            vector2.addElement(new Double(n4));
            ema2 = n4;
        }
        this.firstEma = 0.0;
        if (n < max || n < min) {
            if (max >= min) {
                for (int k = 0; k < max - min; ++k) {
                    vector2.elementAt(k);
                    ++n6;
                }
            }
            else {
                for (int l = 0; l < min - max; ++l) {
                    vector.elementAt(l);
                    ++n5;
                }
            }
        }
        while (n5 < vector.size() && n6 < vector2.size()) {
            try {
                final double doubleValue = vector.elementAt(n5);
                final double doubleValue2 = vector2.elementAt(n6);
                this.macdValue.addElement(new Double((min <= max) ? (doubleValue - doubleValue2) : (doubleValue2 - doubleValue)));
                ++n5;
                ++n6;
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                break;
            }
        }
    }
    
    protected void setMomentum(final int n, final int n2, final int n3, final DataSet set) {
        this.plotValue = new Vector();
        double n4 = 0.0;
        double n5 = 0.0;
        for (int i = n; i <= n2; ++i) {
            if (i >= n3 - 1) {
                final int[] array = (int[])set.chartData[i - (n3 - 1)].getData();
                if (array != null) {
                    n4 = array[3];
                }
                final int[] array2 = (int[])set.chartData[i].getData();
                if (array2 != null) {
                    n5 = array2[3];
                }
                this.plotValue.addElement(new Double(n5 / n4 * 100.0));
            }
        }
    }
    
    protected void setObvValues(final int n, final int n2, final Indicator indicator, final DataSet set) {
        if (this.plotValue != null) {
            this.plotValue = null;
        }
        this.plotValue = new Vector();
        int value = indicator.getValue();
        for (int i = n; i <= n2; ++i) {
            final int[] array = (int[])set.chartData[i].getData();
            final int n3 = array[3];
            int n4 = array[3];
            if (i == 0) {
                value = array[4];
            }
            else {
                n4 = ((int[])set.chartData[i - 1].getData())[3];
            }
            if (n3 > n4) {
                value += ((int[])set.chartData[i].getData())[4];
            }
            else if (n3 < n4) {
                value -= ((int[])set.chartData[i].getData())[4];
            }
            this.plotValue.addElement(new Integer(value));
            this.maxObv = ((this.maxObv < value) ? value : this.maxObv);
            this.minObv = ((this.minObv > value) ? value : this.minObv);
        }
    }
    
    protected void setRocValue(final int n, final int n2, final int n3, final DataSet set) {
        this.plotValue = new Vector();
        for (int i = n; i <= n2; ++i) {
            if (i >= n3 - 1) {
                final int[] array = (int[])set.chartData[i - (n3 - 1)].getData();
                this.plotValue.addElement(new Double((((int[])set.chartData[i].getData())[3] - array[3]) / array[3] * 100.0));
            }
        }
    }
    
    protected void setRsiValues(final int n, final int n2, final int n3, final DataSet set) {
        this.plotValue = new Vector();
        for (int i = n; i <= n2; ++i) {
            if (i >= n3 - 1) {
                float n4 = 0.0f;
                float n5 = 0.0f;
                for (int j = i - n3 + 1; j <= i; ++j) {
                    final int[] array = (int[])set.chartData[j].getData();
                    if (array != null) {
                        final int n6 = array[0];
                        final int n7 = array[3];
                        if (n6 > n7) {
                            n5 += n6 - n7;
                        }
                        else if (n6 < n7) {
                            n4 += n7 - n6;
                        }
                    }
                }
                this.plotValue.addElement(new Integer((int)(100.0 - 100.0 / (1.0 + n4 / n3 / (n5 / n3)))));
            }
        }
    }
    
    protected void setStochast(final int n, final int n2, final int n3, final DataSet set) {
        this.plotValue = new Vector();
        int n4 = 0;
        for (int i = n; i <= n2; ++i) {
            if (i >= n3 - 1) {
                final int n5 = i - (n3 - 1);
                int n6 = 100000;
                int n7 = 0;
                for (int j = n5; j <= i; ++j) {
                    final int[] array = (int[])set.chartData[j].getData();
                    n6 = ((array[2] < n6) ? array[2] : n6);
                    n7 = ((array[1] > n7) ? array[1] : n7);
                }
                this.plotValue.addElement(new Double((((int[])set.chartData[i].getData())[3] - n6) / (n7 - n6) * 100.0));
                ++n4;
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.dataPanel.reshape(0, this.size().height - 20, this.size().width, 20);
        final Dimension chartDimension = new Dimension(this.size().width, this.size().height - 20 - (this.rsiHeight + this.rocHeight + this.macdHeight));
        final int chartBorder = Parameters.chartBorder;
        if (this.chartImage == null || chartDimension.width != this.chartDimension.width || chartDimension.height != this.chartDimension.height) {
            if (this.chartImage != null) {
                this.chartGraphics.dispose();
                this.chartImage = null;
            }
            this.mouseX = -1;
            this.mouseY = -1;
            this.chartDimension = chartDimension;
            this.chartImage = this.createImage(chartDimension.width, chartDimension.height);
            (this.chartGraphics = this.chartImage.getGraphics()).setColor(Parameters.chartBGColor);
            this.chartGraphics.fillRect(0, 0, this.chartDimension.width, this.chartDimension.height);
            this.chartGraphics.setColor(Parameters.chartFGColor);
            this.labelFontM = this.chartGraphics.getFontMetrics(Parameters.labelFont);
            this.titleFontM = this.chartGraphics.getFontMetrics(Parameters.titleFont);
            if (chartDimension.width > 50) {
                this.X1 = 50;
                this.X2 = chartDimension.width - 20;
            }
            else {
                this.X1 = 0;
                this.X2 = 0;
            }
            if (chartDimension.height > 10) {
                this.startPrice = 10;
                this.endVolume = chartDimension.height - 20;
            }
            else {
                this.startPrice = 0;
                this.endVolume = chartDimension.height - 20;
            }
            this.endPrice = chartDimension.height * 70 / 100;
            this.startSplit = this.endPrice + 1;
            this.endSplit = this.startSplit + 20;
            this.startVolume = this.endSplit + 1;
            this.chartGraphics.setColor(Parameters.chartBorderColor);
            for (int i = 0; i < chartBorder; ++i) {
                this.chartGraphics.drawRect(i, i, this.chartDimension.width - (i + i), this.chartDimension.height - (i + i));
                this.chartGraphics.drawRect(this.X1 - i, this.startPrice - i, this.X2 - (this.X1 - i - i), this.endPrice - (this.startPrice - i - i));
                this.chartGraphics.drawRect(this.X1 - i, this.startVolume - i, this.X2 - (this.X1 - i - i), this.endVolume - (this.startVolume - i - i));
            }
            if (this.watermark != null) {
                this.chartGraphics.drawImage(this.watermark, this.X1 + (this.X2 - this.X1 - this.watermark.getWidth(this)) / 2, this.startPrice + (this.endPrice - this.startPrice - this.watermark.getHeight(this)) / 2, this.watermark.getWidth(this), this.watermark.getHeight(this), Parameters.chartBGColor, this);
            }
            this.chartGraphics.setColor(Parameters.chartFGColor);
            this.myPlot();
            this.drawTrendLine();
        }
        graphics.drawImage(this.chartImage, 0, 0, this);
    }
    
    protected int volumetoY(final int n) {
        return this.endVolume - (int)((n - this.minVolume) * this.stepVolume);
    }
    
    protected int xtoData(final int n) {
        return this.startData + (int)((n - this.X1) / this.stepX);
    }
    
    protected int ytoIndicate(final int n) {
        return this.minIndicate + (int)((this.endVolume - n) / this.stepVolume);
    }
    
    protected int ytoPrice(final int n) {
        return this.minRound + (int)((this.endPrice - n) / this.stepPrice);
    }
    
    protected int ytoRocVolume(final int n) {
        return this.minIndicate + (int)((this.endVolume - n) / this.stepVolume);
    }
    
    protected int ytoVolume(final int n) {
        return this.minVolume + (int)((this.endVolume - n) / this.stepVolume);
    }
}
