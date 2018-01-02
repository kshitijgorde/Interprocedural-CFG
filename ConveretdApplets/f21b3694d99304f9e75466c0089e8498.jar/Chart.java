import java.text.SimpleDateFormat;
import java.awt.Polygon;
import java.awt.Component;
import java.awt.Frame;
import java.awt.image.ImageObserver;
import java.net.URLEncoder;
import java.util.StringTokenizer;
import java.awt.Event;
import java.util.Date;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Vector;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class Chart extends Panel
{
    public static final int CHART_BAR = 1;
    public static final int CHART_LINE = 2;
    public static final int CHART_AREA = 3;
    public static final int CHART_CANDLE = 4;
    public static final int CHART_COMPARE = 5;
    public static final int SYMBOL_CHANGED = 9001;
    public static final int DETAIL_CHANGED = 9002;
    public static final int RIGHTDETAIL_CHANGED = 9003;
    public static final int REMOVE_DETAIL = 9004;
    public static final int ADD_RECENT = 9005;
    public static final int CUSTOM_MESSAGE = 9006;
    public static final int HIDE_CUSTOM_MESSAGE = 9007;
    public static final int RECENT_SYMBOL_ADD = 9008;
    public static final int SHOW_HELP = 9009;
    private int chartType;
    private int Adj;
    Image watermark;
    Color baseColor;
    public String[] welcome;
    Applet parent;
    boolean easyDragging;
    boolean selected;
    boolean colorChart;
    private Vector stockHeaders;
    String lastQuery;
    int currentSize;
    int zoom;
    int chartLeft;
    int chartRight;
    int chartWidth;
    int chartTop;
    int chartBottom;
    int chartHeight;
    int volumeTop;
    int volumeBottom;
    int volumeHeight;
    boolean hasDragged;
    boolean mouseDown;
    boolean mouseflag;
    boolean mousedrag;
    int dateSpan;
    int minDate;
    int maxDate;
    int currentIndex;
    double valueSpan;
    double volumeSpan;
    double percentSpan;
    double minValue;
    double maxValue;
    double minPercent;
    double maxPercent;
    boolean usePercent;
    int minVolume;
    int maxVolume;
    Image chartImage;
    Graphics chartG;
    boolean loadingFuture;
    boolean loadingCash;
    Image offImage;
    Graphics offG;
    Graphics myG;
    Color bgColor;
    Color txtColor;
    Color yAxisColor;
    Color xAxisColor;
    Color chartBorderColor;
    Color scaleColor;
    Color dragColor;
    int dateWidth;
    int mouseX;
    int mouseY;
    int mousedownX;
    int mousedownY;
    int mouseupX;
    int mouseupY;
    int dragX;
    int dragY;
    int cursor;
    FontMetrics fm;
    int barWidth;
    int leftSelectedIndex;
    int prevleftselectedindex;
    int rightSelectedIndex;
    Font labelFont;
    Font titleFont;
    boolean dragging;
    Vector drags;
    ADrag ad;
    String volumeScale;
    boolean showVolume;
    boolean haveData;
    private String script;
    private String symbol;
    String rs;
    private int stockexchange;
    private int tablestatus;
    private String query;
    public StockDetail currentDetail;
    public StockDetail currentRightDetail;
    public double currentValue;
    public String cookieValue;
    public String cookieToGet;
    private boolean indInVolume;
    private boolean logChart;
    boolean forceScale;
    boolean showSymbol;
    boolean hideRight;
    String legend;
    StockDataLoader sdl;
    int count;
    String loadingName;
    String loadingSymbol;
    boolean loadingSetSymbol;
    int loadingstockExchg;
    int loadingtableSelect;
    boolean expanded;
    boolean optionsChart;
    boolean hasMoved;
    public boolean perform;
    StockHeader loadingHeader;
    int hb;
    String browser;
    int tx;
    int ty;
    int shapeselected;
    boolean shapesel;
    boolean trend;
    int dim;
    int tchartLeft;
    int jdiff;
    int j11;
    int l13;
    int phourval;
    int pminval;
    int mindiff;
    int i2;
    int drawcnt;
    
    Chart(final Applet applet) {
        this.welcome = new String[] { "Welcome to JavaChart.", "", "", "", "", "Loading Data.  Please Wait....", "" };
        this.selected = false;
        this.mouseflag = false;
        this.mousedrag = false;
        this.tx = 0;
        this.ty = 0;
        this.shapesel = false;
        this.trend = false;
        this.tchartLeft = 38;
        this.jdiff = 0;
        this.j11 = 0;
        this.l13 = 0;
        this.phourval = 0;
        this.pminval = 0;
        this.mindiff = 0;
        this.drawcnt = 0;
        this.baseColor = new Color(255, 255, 255);
        this.bgColor = new Color(254, 254, 254);
        this.txtColor = new Color(0, 0, 102);
        this.chartType = 1;
        this.Adj = 1;
        this.watermark = null;
        this.perform = false;
        this.easyDragging = false;
        this.mouseflag = false;
        this.colorChart = false;
        this.zoom = 1;
        this.hasDragged = false;
        this.mouseDown = false;
        this.currentIndex = -1;
        this.yAxisColor = Color.black;
        this.xAxisColor = Color.black;
        this.chartBorderColor = Color.black;
        this.scaleColor = new Color(240, 240, 240);
        this.dragColor = Color.red;
        this.mouseX = -1;
        this.mouseY = -1;
        this.dragX = -1;
        this.dragY = -1;
        this.cursor = 2;
        this.leftSelectedIndex = -1;
        this.prevleftselectedindex = -1;
        this.rightSelectedIndex = -1;
        this.dragging = false;
        this.volumeScale = "";
        this.showVolume = true;
        this.haveData = false;
        this.script = "creat.php?symbol=";
        this.symbol = "";
        this.indInVolume = false;
        this.logChart = false;
        this.forceScale = false;
        this.showSymbol = false;
        this.hideRight = false;
        this.expanded = false;
        this.optionsChart = false;
        this.hasMoved = false;
        this.parent = applet;
        this.stockHeaders = new Vector();
        this.drags = new Vector(5, 1);
        this.createImages(800, 600);
        this.labelFont = new Font("sanserif", 0, 10);
        this.chartG.setFont(this.labelFont);
        this.fm = this.chartG.getFontMetrics();
        this.titleFont = new Font("sanserif", 1, 10);
        this.myG = this.getGraphics();
        this.browser = this.parent.getParameter("brow");
        this.rs = this.parent.getParameter("stock");
        this.stockexchange = Integer.parseInt(this.rs);
        this.rs = this.parent.getParameter("status");
        this.tablestatus = Integer.parseInt(this.rs);
    }
    
    public Dimension minimumSize() {
        return new Dimension(10, 10);
    }
    
    public StockDetail StockDetailAt(final int i) {
        if (this.currentHeader() != null) {
            return this.currentHeader().dataAt(i);
        }
        return null;
    }
    
    public StockDetail StockDetailAt(final String s, final int i) {
        final StockHeader stockheader = this.getHeader(s);
        if (stockheader == null) {
            return null;
        }
        return stockheader.dataAt(i);
    }
    
    public int chartHeightExpanded() {
        if (this.expanded) {
            return this.chartHeight - 50;
        }
        return this.chartHeight;
    }
    
    public int volumeHeightExpanded() {
        if (this.expanded) {
            return this.volumeHeight - 20;
        }
        return this.volumeHeight;
    }
    
    public int chartWidthExpanded() {
        if (this.expanded) {
            return this.chartWidth - 50;
        }
        return this.chartWidth;
    }
    
    public void checkRepaint() {
        if (this.currentSize != this.size().width * 1000 + this.size().height) {
            this.unZoom();
            this.currentSize = this.size().width * 1000 + this.size().height;
        }
    }
    
    public void createImages(final int i, final int j) {
        if (this.chartImage != null) {
            this.chartG.dispose();
            this.chartImage = null;
        }
        if (this.offImage != null) {
            this.offG.dispose();
            this.offImage = null;
        }
        this.chartImage = this.parent.createImage(i, j);
        this.chartG = this.chartImage.getGraphics();
        this.offImage = this.parent.createImage(i, j);
        this.offG = this.offImage.getGraphics();
    }
    
    public StockHeader currentHeader() {
        return this.getHeader(this.symbol);
    }
    
    public int dateToX(final int i) {
        return this.chartLeft + this.chartWidthExpanded() * (i - this.minDate) / Math.max(this.dateSpan, 1);
    }
    
    public int dateToX(final Date date) {
        try {
            StockDetail stockdetail = this.StockDetailAt(this.maxDate);
            if (stockdetail.date.before(date)) {
                final int k1;
                final int j = k1 = this.dateToX(this.maxDate + 1);
                return k1;
            }
            if (stockdetail.date.equals(date)) {
                final int l1;
                final int i = l1 = this.dateToX(this.maxDate);
                return l1;
            }
            stockdetail = this.StockDetailAt(this.minDate);
            if (stockdetail.date.after(date)) {
                final byte byte2;
                final byte byte0 = byte2 = -1;
                return byte2;
            }
            for (int m = 0; m <= 99999; ++m) {
                final StockDetail stockdetail2 = this.StockDetailAt(m);
                final StockDetail stockdetail3 = this.StockDetailAt(m + 1);
                if (stockdetail2.date.equals(date)) {
                    final int j2;
                    final int l2 = j2 = this.dateToX(m);
                    return j2;
                }
                if (stockdetail3.date.equals(date)) {
                    final int k2;
                    final int i2 = k2 = this.dateToX(m + 1);
                    return k2;
                }
                if (stockdetail2.date.before(date) && stockdetail3.date.after(date)) {
                    final int l3;
                    final int j3 = l3 = this.dateToX(m + 1);
                    return l3;
                }
            }
        }
        catch (Exception exception) {
            System.out.println(String.valueOf(exception));
        }
        return -1;
    }
    
    public StockHeader deleteHeader(final String s) {
        for (int i = 0; i < this.stockHeaders.size(); ++i) {
            if (this.stockHeaders.elementAt(i).symbol.equals(s)) {
                this.stockHeaders.removeElementAt(i);
            }
        }
        return null;
    }
    
    public void destroy() {
        System.gc();
        this.offG.dispose();
        this.chartG.dispose();
        this.offImage = null;
        this.chartImage = null;
        System.gc();
    }
    
    public void doneLoading() {
        if (this.sdl == null) {
            final boolean flag = false;
            return;
        }
        final boolean flag2 = this.sdl.liveOnly;
        if (this.sdl != null && !this.sdl.done) {
            this.sdl.stop();
            this.sdl = null;
        }
        if (this.sdl != null) {
            this.sdl = null;
        }
        if (flag2) {
            this.unZoom();
            this.repaint();
            return;
        }
        final Graphics g = this.getGraphics();
        g.setColor(this.baseColor);
        g.fillRect(this.chartLeft + 1, this.chartBottom - 20, this.chartWidth, 20);
        g.setColor(Color.black);
        g.drawString("Done", this.chartLeft + 5, this.chartBottom - 6);
        if (this.loadingHeader == null) {
            this.unZoom();
            this.repaint();
            return;
        }
        if (this.loadingSetSymbol || this.loadingCash) {
            this.haveData = true;
            if (!this.loadingCash) {
                this.symbol = this.loadingSymbol;
            }
            this.cookieToGet = this.loadingSymbol;
            this.postEvent(new Event(this, 9001, this.symbol));
        }
        this.loadingHeader.convertArray();
        if (this.loadingFuture) {
            this.loadingHeader.setFuture(true);
        }
        this.loadingHeader.query = this.lastQuery;
        if (this.loadingSymbol.indexOf("9413") > 0 && this.currentHeader() != null && this.currentHeader().isFuture()) {
            this.currentHeader().setCash(this.loadingHeader);
        }
        else {
            this.stockHeaders.addElement(this.loadingHeader);
        }
        this.unZoom();
        this.removeDrags();
        this.updateChart();
        this.unZoom();
        this.requestFocus();
        this.loadingSymbol = null;
        this.loadingName = null;
        if (this.loadingSetSymbol || this.loadingCash) {
            this.postEvent(new Event(this, 9008, String.valueOf(this.loadingFuture ? "F*" : "").concat(String.valueOf(this.currentHeader().symbol))));
        }
    }
    
    public void drawAxis(final boolean flag) {
        this.drawYAxis(flag);
        this.drawXAxis(flag);
        this.chartG.setColor(this.txtColor);
        if (this.maxValue == 0.0 && this.minValue == 0.0) {
            this.chartG.drawString("No data available", this.chartLeft + 5, 100 + this.fm.getHeight());
        }
    }
    
    public void drawBorders() {
        this.chartG.setColor(this.chartBorderColor);
        this.chartG.drawRect(this.chartLeft, this.chartTop, this.chartWidth, this.chartHeight);
        this.chartG.drawRect(this.chartLeft, this.volumeTop, this.chartWidth, this.volumeHeight);
        this.chartG.setColor(this.chartBorderColor);
        this.chartG.fillRect(this.chartLeft + 2, this.chartBottom + 1, this.chartWidth - 1, 1);
        this.chartG.fillRect(this.chartRight + 1, this.chartTop + 2, 1, this.chartHeight);
        this.chartG.fillRect(this.chartLeft + 2, this.volumeBottom + 1, this.chartWidth - 1, 1);
        this.chartG.fillRect(this.chartRight + 1, this.volumeTop + 2, 1, this.volumeHeight);
    }
    
    public void drawXAxis(final boolean flag) {
        final boolean flag2 = false;
        final String s = "";
        final int i = this.chartWidth / this.dateWidth;
        Math.max(0.9999 + this.dateSpan / i, 1.0);
        int j = this.minDate;
        final boolean flag3 = false;
        final boolean flag4 = false;
        for (int k = -1000; j < this.maxDate && k < this.chartRight; ++j) {
            try {
                if (this.dateToX(j) > k + this.dateWidth) {
                    final int l = this.dateToX(j);
                    final Date date = this.StockDetailAt(j).getDate();
                    byte byte0;
                    if (date.getYear() >= 100) {
                        byte0 = -100;
                    }
                    else {
                        byte0 = 0;
                    }
                    String s2;
                    if (this.tablestatus == 0) {
                        s2 = String.valueOf(String.valueOf(date.getHours()).concat(String.valueOf(":"))).concat(String.valueOf(date.getMinutes()));
                    }
                    else {
                        final int k2 = date.getMonth() + 1;
                        String s3 = " ";
                        if (k2 < 10) {
                            s3 = String.valueOf("0").concat(String.valueOf(k2));
                        }
                        else {
                            s3 = String.valueOf("").concat(String.valueOf(k2));
                        }
                        if (date.getYear() + byte0 == 0) {
                            s2 = String.valueOf(String.valueOf(String.valueOf(s3).concat(String.valueOf("/"))).concat(String.valueOf(date.getYear() + byte0))).concat(String.valueOf("0"));
                        }
                        else {
                            s2 = String.valueOf(String.valueOf(s3).concat(String.valueOf("/"))).concat(String.valueOf(date.getYear() + byte0));
                        }
                    }
                    this.chartG.setColor(this.xAxisColor);
                    this.chartG.drawString(s2, l + Math.max(this.barWidth - this.fm.stringWidth(s2) >> 1, 0), this.size().height - 3);
                    if (flag) {
                        this.chartG.setColor(this.scaleColor);
                        this.chartG.drawLine(l, this.chartTop + 1, l, this.chartBottom - 1);
                        this.showVolume = false;
                        if (this.showVolume) {
                            this.chartG.drawLine(l, this.volumeTop + 1, l, this.volumeBottom - 1);
                        }
                    }
                    k = l;
                }
            }
            catch (NullPointerException ex) {}
        }
        if (this.expanded) {
            int i2 = this.dateToX(this.minDate + 1) - this.dateToX(this.minDate);
            if (i2 <= 1) {
                i2 = 2;
            }
            int j2 = this.dateToX(this.maxDate) + i2 + (this.barWidth >> 1);
            this.chartG.setColor(this.scaleColor);
            while (j2 < this.chartRight) {
                this.chartG.drawLine(j2, this.chartTop + 1, j2, this.chartBottom - 1);
                j2 += i2;
            }
        }
    }
    
    public void drawYAxis(final boolean flag) {
        final int i = (this.chartHeight - 30) / (this.fm.getHeight() + 1);
        double d;
        double d2;
        if (this.usePercent) {
            d = 1.1 * this.percentSpan / i;
            d2 = this.minPercent;
        }
        else {
            d = (this.maxValue - this.minValue) / i;
            d2 = this.minValue;
            if (d < 0.1) {
                d = (int)((d + 0.01) * 100.0) / 100.0;
            }
            else if (d < 1.0) {
                d = (int)((d + 0.1) * 10.0) / 10.0;
            }
            else if (d < 10.0) {
                d = (int)(d + 1.0);
            }
            if (d < 1.0 && d > 0.5) {
                d = 1.0;
            }
            if (d < 0.5 && d > 0.1) {
                d = 0.5;
            }
            d = Math.max(d, 0.01);
            if (this.minValue > 5.0) {
                d2 = Math.max((int)d2, (int)(d2 + 0.9999));
                if (d2 - 0.5 >= this.minValue) {
                    d2 -= 0.5;
                }
                d = Math.max((int)(d * 10.0 + 0.99999) / 10, 0.5);
            }
            if (this.valueSpan <= 2.0 && d >= 0.5) {
                d /= 2.0;
            }
            if (this.logChart && this.maxValue > 200.0 && this.minValue > 1.0) {
                d *= 2.0;
            }
            if (this.logChart && this.maxValue > 350.0 && this.minValue > 1.0) {
                d *= 2.0;
            }
            if (this.logChart && this.maxValue - this.minValue < 150.0) {
                d *= 2.0;
            }
            if (this.logChart && this.maxValue > 175.0 && this.minValue < 10.0) {
                d *= 3.0;
            }
            else if (this.logChart && this.maxValue > 150.0 && this.minValue < 10.0) {
                d *= 2.0;
            }
        }
        int j;
        if (this.usePercent) {
            j = this.percentToY(d2);
            this.chartG.setColor(this.txtColor);
            final int k = this.percentToY(1.0);
            this.chartG.drawLine(this.chartLeft, k, this.chartRight, k);
        }
        else {
            j = this.valueToY(d2);
        }
        int l = 0;
        while (j > this.chartTop + (this.fm.getHeight() >> 1)) {
            ++l;
            this.chartG.setColor(this.yAxisColor);
            double d3 = d2;
            String s;
            if (this.usePercent) {
                d3 = 100.0 * (d2 - 1.0);
                s = String.valueOf(Math.abs(d3) + 1.0E-4).concat(String.valueOf("00")).substring(0, 4);
                if (d3 < 0.0) {
                    s = String.valueOf("-").concat(String.valueOf(s));
                }
                else if (d3 > 0.0) {
                    s = String.valueOf("+").concat(String.valueOf(s));
                }
                s = String.valueOf(s).concat(String.valueOf("%"));
            }
            else {
                s = String.valueOf(d3 + 1.0E-5).concat(String.valueOf("000000000")).substring(0, 6);
                final StringTokenizer stringtokenizer = new StringTokenizer(s, ".");
                if (stringtokenizer.countTokens() >= 2) {
                    s = stringtokenizer.nextToken().trim();
                }
            }
            if (s.indexOf("E") > 0) {
                s = "0";
            }
            if (this.maxValue != 0.0 || this.minValue != 0.0) {
                if (l > 1) {
                    this.chartG.drawString(s, this.chartLeft - this.fm.stringWidth(s) - 2, j + this.fm.getDescent());
                }
                if (!this.hideRight) {
                    this.chartG.drawString(s, this.chartRight + this.fm.stringWidth("9"), j + this.fm.getDescent());
                }
                if (flag) {
                    this.chartG.setColor(this.scaleColor);
                    this.chartG.drawLine(this.chartLeft + 1, j, this.chartRight - 1, j);
                }
            }
            d2 += d;
            if (this.usePercent) {
                j = this.percentToY(d2);
            }
            else {
                j = this.valueToY(d2);
            }
        }
        final int[] ai = { 0, this.maxVolume / 6, 2 * this.maxVolume / 6, 3 * this.maxVolume / 6, 4 * this.maxVolume / 6, 5 * this.maxVolume / 6, this.maxVolume, 0, 0, 0 };
        final int i2 = this.maxVolume;
        byte byte0 = 7;
        if (this.volumeHeight <= 350 && this.volumeHeight > 300) {
            final int j2 = this.maxVolume;
            ai[0] = 0;
            ai[1] = j2 / 9;
            ai[2] = 2 * j2 / 9;
            ai[3] = 3 * j2 / 9;
            ai[4] = 4 * j2 / 9;
            ai[5] = 5 * j2 / 9;
            ai[6] = 6 * j2 / 9;
            ai[7] = 7 * j2 / 9;
            ai[8] = 8 * j2 / 9;
            ai[9] = j2;
            byte0 = 10;
        }
        if (this.volumeHeight <= 300 && this.volumeHeight > 200) {
            final int k2 = this.maxVolume;
            ai[0] = 0;
            ai[1] = k2 / 8;
            ai[2] = 2 * k2 / 8;
            ai[3] = 3 * k2 / 8;
            ai[4] = 4 * k2 / 8;
            ai[5] = 5 * k2 / 8;
            ai[6] = 6 * k2 / 8;
            ai[7] = 7 * k2 / 8;
            ai[8] = k2;
            byte0 = 9;
        }
        if (this.volumeHeight <= 200 && this.volumeHeight > 100) {
            final int l2 = this.maxVolume;
            ai[0] = 0;
            ai[1] = l2 / 7;
            ai[2] = 2 * l2 / 7;
            ai[3] = 3 * l2 / 7;
            ai[4] = 4 * l2 / 7;
            ai[5] = 5 * l2 / 7;
            ai[6] = 6 * l2 / 7;
            ai[7] = l2;
            byte0 = 8;
        }
        if (this.volumeHeight <= 100 && this.volumeHeight > 60) {
            final int j3 = this.maxVolume;
            ai[0] = 0;
            ai[1] = j3 / 6;
            ai[2] = 2 * j3 / 6;
            ai[3] = 3 * j3 / 6;
            ai[4] = 4 * j3 / 6;
            ai[5] = 5 * j3 / 6;
            ai[6] = j3;
            byte0 = 7;
        }
        if (this.volumeHeight <= 60 && this.volumeHeight > 50) {
            final int k3 = this.maxVolume;
            ai[0] = 0;
            ai[1] = k3 / 5;
            ai[2] = 2 * k3 / 5;
            ai[3] = 3 * k3 / 5;
            ai[4] = 4 * k3 / 5;
            ai[5] = k3;
            byte0 = 6;
        }
        if (this.volumeHeight <= 50 && this.volumeHeight > 40) {
            final int l3 = this.maxVolume;
            ai[0] = 0;
            ai[1] = l3 / 4;
            ai[2] = 2 * l3 / 4;
            ai[3] = 3 * l3 / 4;
            ai[4] = l3;
            byte0 = 5;
        }
        if (this.volumeHeight <= 40 && this.volumeHeight > 30) {
            final int i3 = this.maxVolume;
            ai[0] = 0;
            ai[1] = i3 / 3;
            ai[2] = 2 * i3 / 3;
            ai[3] = i3;
            byte0 = 4;
        }
        if (this.volumeHeight <= 30 && this.volumeHeight > 20) {
            final int j4 = this.maxVolume;
            ai[0] = 0;
            ai[1] = j4 / 2;
            ai[2] = j4;
            byte0 = 3;
        }
        if (this.volumeHeight <= 20 && this.volumeHeight > 10) {
            final int k4 = this.maxVolume;
            ai[0] = 0;
            ai[1] = k4;
            byte0 = 2;
        }
        if (this.indInVolume) {
            ai[0] = 0;
            ai[1] = 16;
            ai[2] = 33;
            ai[3] = 50;
            ai[4] = 66;
            ai[5] = 83;
            ai[6] = 100;
            byte0 = 7;
        }
        final int l4 = 1;
        this.volumeScale = "";
        this.volumeScale = " ";
        for (int i4 = 0; i4 < byte0; ++i4) {
            final boolean flag2 = false;
            if (this.indInVolume) {
                final int j5 = this.volumeTop + (int)(this.volumeHeight * (1.0 - ai[i4] / 100.0));
                String.valueOf(ai[i4]);
            }
            else {
                final int k5 = this.volumeToY(ai[i4]);
                int l5 = ai[i4];
                l5 /= l4;
                if (l5 >= 1000) {
                    final int i5 = String.valueOf(l5).length() - 3;
                    String.valueOf(String.valueOf(String.valueOf(l5).substring(0, i5)).concat(String.valueOf(","))).concat(String.valueOf(String.valueOf(l5).substring(i5)));
                }
                else {
                    String.valueOf(l5);
                }
            }
            this.chartG.setColor(this.yAxisColor);
            if ((this.maxVolume != 0 || this.minVolume != 0) && flag) {
                this.chartG.setColor(this.scaleColor);
            }
        }
    }
    
    public void findExtremes(final String s) {
        double d = -1.0;
        for (int i = this.minDate; i < this.maxDate + 1; ++i) {
            StockDetail stockdetail;
            if (s == null) {
                stockdetail = this.StockDetailAt(i);
            }
            else {
                stockdetail = this.StockDetailAt(s, i);
            }
            if (stockdetail != null) {
                if (d == -1.0) {
                    d = stockdetail.getClose();
                }
                if (stockdetail.getHigh() > this.maxValue) {
                    this.maxValue = stockdetail.getHigh() + 0.1;
                }
                if (stockdetail.getLow() < this.minValue) {
                    this.minValue = stockdetail.getLow();
                }
                if (stockdetail.getVolume() > this.maxVolume) {
                    this.maxVolume = stockdetail.getVolume();
                }
                final double d2 = (stockdetail.getHigh() - d) / d;
                final double d3 = (stockdetail.getLow() - d) / d;
                if (d2 > this.maxPercent) {
                    this.maxPercent = d2;
                }
                if (d3 < this.minPercent) {
                    this.minPercent = d3;
                }
            }
        }
    }
    
    public String formatDate(final Date date) {
        return String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(date.getDate()).concat(String.valueOf("/"))).concat(String.valueOf(date.getMonth()))).concat(String.valueOf(1))).concat(String.valueOf("/"))).concat(String.valueOf(date.getYear() + 1900));
    }
    
    public StockHeader getHeader(final String s) {
        for (int i = 0; i < this.stockHeaders.size(); ++i) {
            if (this.stockHeaders.elementAt(i).symbol.equals(s)) {
                return this.stockHeaders.elementAt(i);
            }
        }
        return null;
    }
    
    public StockHeader getHeader(final String s, final int i, final int j) {
        for (int k = 0; k < this.stockHeaders.size(); ++k) {
            if (this.stockHeaders.elementAt(k).symbol.equals(s) && this.stockHeaders.elementAt(k).stockvalue == i && this.stockHeaders.elementAt(k).tablevalue == j) {
                return this.stockHeaders.elementAt(k);
            }
        }
        return null;
    }
    
    public String getSymbol() {
        return this.symbol;
    }
    
    public void gotData(final String s, final int i) {
        final Graphics g = this.getGraphics();
        ++this.count;
        final double d = Math.min(1.0, this.count / 200.0);
        g.setColor(Color.white);
        g.fillRect(this.chartLeft + 1, this.chartBottom - 20, (int)(this.chartWidth * d) - 2, 20);
        g.setColor(this.txtColor);
        g.drawString(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Loading ").concat(String.valueOf((this.loadingName == null) ? this.loadingSymbol : this.loadingName))).concat(String.valueOf(": "))).concat(String.valueOf((int)(100.0 * d)))).concat(String.valueOf("%")), this.chartLeft + 5, this.chartBottom - 6);
        final StockDetail stockdetail = new StockDetail(s, this.browser);
        this.loadingHeader.addDetail(stockdetail);
    }
    
    public void hideRight() {
        this.hideRight = true;
        this.updateChart();
    }
    
    public Date indexToDate(final int i) {
        try {
            final StockDetail stockdetail = this.StockDetailAt(i);
            final Date date2;
            final Date date = date2 = stockdetail.date;
            return date2;
        }
        catch (Exception exception) {
            final Date date3 = null;
            return date3;
        }
    }
    
    public void loadFuture(final String s, final String s1, final int i, final int j, final int k, final int l, final int i1, final int j1, final int k1, final int l1, final int j2, final int k2, final int l2, final int i3) {
        this.loadStock(s, s1, true, i, j, false, true, null, false);
    }
    
    public void loadStock(final String s, final String s1, final boolean flag, final int i, final int j) {
        this.loadStock(s, s1, flag, i, j, false, false, null, false);
    }
    
    public void loadStock(String s, final String s1, final boolean flag, final int i, final int j, final boolean flag1, final boolean flag2, final String s2, final boolean flag3) {
        this.chartType = 2;
        if (this.sdl != null) {
            return;
        }
        System.out.println(String.valueOf("Csym:").concat(String.valueOf(s)));
        if (!flag1) {
            s = s.toUpperCase();
        }
        final StringTokenizer stringtokenizer = new StringTokenizer(s, "#");
        String s3;
        if (stringtokenizer.countTokens() >= 2) {
            if (this.Adj == 1) {
                s3 = stringtokenizer.nextToken().trim();
            }
            else {
                s3 = String.valueOf(stringtokenizer.nextToken().trim()).concat(String.valueOf("#UnAdj"));
            }
        }
        else {
            s3 = String.valueOf(String.valueOf(s).concat(String.valueOf("+"))).concat(String.valueOf(this.Adj));
            if (this.Adj == 1) {
                s3 = s;
            }
            else {
                s3 = String.valueOf(s).concat(String.valueOf("#UnAdj"));
            }
        }
        this.forceScale = false;
        System.out.println(String.valueOf("Loading ").concat(String.valueOf(s3)));
        this.loadingFuture = flag2;
        this.loadingCash = flag3;
        if (this.getHeader(s3, i, j) != null) {
            this.haveData = true;
            if (flag) {
                this.symbol = s3;
                this.cookieToGet = s3;
                this.postEvent(new Event(this, 9001, this.symbol));
            }
            this.unZoom();
            this.updateChart();
            this.unZoom();
            this.requestFocus();
            System.out.println(String.valueOf(String.valueOf("Already have ").concat(String.valueOf(s3))).concat(String.valueOf(", getting live quote")));
            return;
        }
        this.removeDrags();
        this.deleteHeader(s3);
        if (flag) {
            this.postEvent(new Event(this, 9001, "Loading..."));
            this.haveData = false;
        }
        this.count = 0;
        this.loadingName = s1;
        this.loadingSymbol = s3;
        this.loadingSetSymbol = flag;
        this.loadingstockExchg = i;
        this.loadingtableSelect = j;
        this.haveData = false;
        (this.loadingHeader = new StockHeader()).setSymbol(this.loadingSymbol);
        this.loadingHeader.setStockExchg(this.loadingstockExchg);
        this.loadingHeader.setTableSelect(this.loadingtableSelect);
        this.lastQuery = String.valueOf(this.script).concat(String.valueOf(URLEncoder.encode(s3)));
        this.stockexchange = i;
        this.tablestatus = 1;
        this.query = String.valueOf(String.valueOf(String.valueOf("&stock=").concat(String.valueOf(this.stockexchange))).concat(String.valueOf("&status="))).concat(String.valueOf(this.tablestatus));
        this.sdl = new StockDataLoader(this, String.valueOf(String.valueOf(this.script).concat(String.valueOf(URLEncoder.encode(s3)))).concat(String.valueOf(this.query)), URLEncoder.encode(s3), this.getHeader(s3, i, j) != null);
    }
    
    public void loadStock(final String s, final String s1, final boolean flag, final boolean flag1, final int i, final int j, final int k) {
        this.showSymbol = flag1;
        this.loadStock(s, s1, flag, j, k, false, false, null, false);
    }
    
    public void loadStock(final String s, final String s1, final boolean flag, final boolean flag1, final int i, final int j, final int k, final boolean flag2) {
        this.showSymbol = flag1;
        this.loadStock(s, s1, flag, j, k, flag2, false, null, false);
    }
    
    public boolean loading() {
        return this.loadingSymbol != null;
    }
    
    public boolean mouseDown(final Event event, final int i, final int j) {
        this.mouseDown = true;
        if (this.dragging) {
            return true;
        }
        this.requestFocus();
        if ((event.modifiers & 0x4) != 0x0) {
            if ((event.modifiers & 0x1) != 0x0) {
                this.removeLastTrendline();
            }
            else if ((event.modifiers & 0x2) != 0x0) {
                this.removeAllTrendlines();
            }
            else {
                this.unZoom();
            }
        }
        else {
            this.hasDragged = false;
        }
        this.mouseX = i;
        this.mouseY = j;
        this.mousedownX = this.mouseX;
        this.mousedownY = this.mouseY;
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int i, int j) {
        if (this.mousedownX == this.mouseupX && this.mousedownY == this.mouseupY) {
            this.dragging = false;
            this.easyDragging = false;
        }
        if (this.mouseflag) {
            final int k = this.size().height;
            if (j > k - 15) {
                j = this.volumeBottom;
            }
            if (j < 5) {
                j = this.chartTop;
            }
            this.chartBottom = j;
            this.volumeTop = j;
            this.dragY = j;
            this.updateChart();
            this.repaint();
        }
        this.hasDragged = true;
        ++this.drawcnt;
        this.hasMoved = (i != this.mouseX);
        if (this.sdl != null) {
            return true;
        }
        if ((this.easyDragging || (event.modifiers & 0x1) != 0x0) && this.mouseX >= 0) {
            this.mouseflag = false;
            this.dragging = true;
            this.dragX = i;
            this.dragY = j;
            try {
                final double d = this.yToValue(this.mouseY);
                final double d2 = this.yToValue(this.dragY);
                double d3 = d2 - d;
                double d4 = d3 / Math.max(d, 1.0E-4);
                final Date date = this.indexToDate(Math.min(this.xToDate(this.dragX), this.xToDate(this.mouseX)));
                final Date date2 = this.indexToDate(Math.max(this.xToDate(this.dragX), this.xToDate(this.mouseX)));
                final long l1 = (date2.getTime() - date.getTime()) / 86400000L;
                double d5 = d4 / Math.max(l1, 1L);
                double d6 = d5 * 365.0;
                d3 = (int)(d3 * 1000.0) / 1000.0;
                d4 = 100 * (int)(d4 * 1000.0) / 1000.0;
                d5 = 100 * (int)(d5 * 1000.0) / 1000.0;
                d6 = 100 * (int)(d6 * 1000.0) / 1000.0;
                this.postEvent(new Event(this, 9006, String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Change: ").concat(String.valueOf(d3))).concat(String.valueOf("   % Change: "))).concat(String.valueOf(d4))).concat(String.valueOf("   Days: "))).concat(String.valueOf(l1))).concat(String.valueOf("   %/day: "))).concat(String.valueOf(d5))).concat(String.valueOf("   Annualized % Change: "))).concat(String.valueOf(d6))));
            }
            catch (Exception ex) {}
            this.repaint();
        }
        else if (this.mouseDown && (event.modifiers & 0x4) == 0x0) {
            this.rightSelectedIndex = i;
            int m = 0;
            if (this.rightSelectedIndex < this.chartLeft) {
                this.rightSelectedIndex = this.chartLeft + 1;
            }
            if (this.rightSelectedIndex >= this.chartLeft + this.chartWidthExpanded()) {
                m = this.maxDate;
                this.rightSelectedIndex = this.dateToX(this.StockDetailAt(m).getDate()) + this.barWidth;
            }
            else {
                m = this.xToIndex(this.rightSelectedIndex);
            }
            if (m >= 0) {
                final StockDetail stockdetail = this.StockDetailAt(m);
                this.setRightDetail(stockdetail);
            }
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int i, final int j) {
        this.leftSelectedIndex = i;
        this.moveLine(this.prevleftselectedindex = i, j);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int i, final int j) {
        if (this.hasDragged) {
            return true;
        }
        this.dragging = false;
        this.mouseX = -1;
        this.removeRightDetail();
        this.moveLine(i, j);
        return true;
    }
    
    public boolean mouseMove(final Event event, final int i, int j) {
        this.setMouseCursor(0);
        this.hasMoved = true;
        if (this.dragging) {
            j = this.dragY;
            this.mouseDrag(event, i, j);
            return true;
        }
        if (j > this.chartBottom - 5 && j < this.chartBottom + 5) {
            this.setMouseCursor(8);
            this.mouseflag = true;
            this.mousedrag = false;
        }
        else {
            this.setMouseCursor(0);
            this.mousedrag = true;
            this.mouseflag = false;
        }
        this.moveLine(i, j);
        this.mouseY = j;
        this.mouseX = i;
        return true;
    }
    
    public boolean mouseUp(final Event event, final int i, final int j) {
        this.mouseupX = i;
        this.mouseupY = j;
        if (this.mousedownX == this.mouseupX && this.mousedownY == this.mouseupY) {
            this.dragging = false;
            this.easyDragging = false;
        }
        this.mouseDown = false;
        if (this.selected) {
            for (int k = 0; k < this.drags.size(); ++k) {
                final ADrag adrag = this.drags.elementAt(k);
                if (adrag.infinite) {
                    this.chartG.setColor(this.dragColor.brighter());
                }
                else {
                    this.chartG.setColor(this.dragColor);
                }
                final int k2 = this.dateToX(adrag.getDate1()) + this.hb;
                int k3 = this.dateToX(adrag.getDate2()) + this.hb;
                final int l2 = this.valueToY(adrag.getValue1());
                int i2 = this.valueToY(adrag.getValue2());
                if (adrag.infinite) {
                    double d;
                    if (k3 > k2) {
                        d = (i2 - l2) / (k3 - k2);
                        k3 = this.chartRight;
                    }
                    else {
                        d = (i2 - l2) / (k2 - k3);
                        k3 = this.chartLeft;
                    }
                    i2 = (int)(Math.abs(k3 - k2) * d) + l2;
                }
                final double d2 = i2 - l2;
                final double d3 = k3 - k2;
                double d4 = d2 / d3;
                double d5 = (this.mousedownY - l2) / (this.mousedownX - k2);
                if (d5 < 0.0) {
                    d5 = -d5;
                }
                if (d4 < 0.0) {
                    d4 = -d4;
                }
                double d6 = 0.0;
                if (d5 > d4) {
                    d6 = d5 - d4;
                }
                else {
                    d6 = d4 - d5;
                }
                if (d6 <= 0.1) {
                    this.shapeselected = k;
                    this.shapesel = true;
                    this.removeLastTrendline();
                    this.shapesel = false;
                }
            }
        }
        this.hasMoved = false;
        if (!this.hasDragged && !this.dragging) {
            this.easyDragging = true;
            this.mouseDrag(event, i, j);
            return true;
        }
        if (this.dragging) {
            this.easyDragging = false;
            this.postEvent(new Event(this, 9007, null));
            this.postEvent(new Event(this, 12322, null));
            int m = this.xToDate(this.dragX);
            boolean flag = false;
            if (m == -1) {
                if (this.dragX >= this.chartLeft + this.chartWidthExpanded()) {
                    flag = true;
                    m = this.maxDate;
                    final int l3 = (this.mouseY - this.dragY) * (this.chartLeft + this.chartWidthExpanded() - this.mouseX) / (this.dragX - this.mouseX);
                    this.dragY = this.mouseY - l3;
                }
                if (this.dragX < this.chartLeft) {
                    flag = true;
                    m = this.minDate;
                    final int j2 = (this.mouseY - this.dragY) * (this.mouseX - this.chartLeft) / (this.mouseX - this.dragX);
                    this.dragY = this.mouseY - j2;
                }
            }
            final ADrag adrag2 = new ADrag(this.xToDate(this.mouseX), this.yToValue(this.mouseY), m, this.yToValue(this.dragY), flag);
            this.drags.addElement(adrag2);
            this.updateCookie();
            this.updateChart();
        }
        else {
            final int i3 = this.xToIndex(this.leftSelectedIndex);
            final int j3 = this.xToIndex(this.rightSelectedIndex);
            if (i3 >= 0 && j3 >= 0 && Math.abs(i3 - j3) > 4) {
                if (this.StockDetailAt(i3).getDate().after(this.StockDetailAt(j3).getDate())) {
                    this.setDates(this.StockDetailAt(j3).getDate(), this.StockDetailAt(i3).getDate());
                }
                else {
                    this.setDates(this.StockDetailAt(i3).getDate(), this.StockDetailAt(j3).getDate());
                }
            }
            this.rightSelectedIndex = -1;
            this.removeRightDetail();
            this.moveLine(i, j);
        }
        this.dragging = false;
        this.rightSelectedIndex = -1;
        return true;
    }
    
    public void moveLine(final int i, final int j) {
        if (j > 235 && j < 250) {
            this.setMouseCursor(8);
        }
        final int k = this.xToIndex(i);
        if (k != this.currentIndex) {
            this.leftSelectedIndex = this.dateToX(k) + (this.barWidth >> 1);
            this.currentIndex = k;
            this.repaint();
        }
        if (k >= 0) {
            final StockDetail stockdetail = this.StockDetailAt(k);
            this.setDetail(stockdetail, this.yToValue(j));
            this.repaint();
        }
    }
    
    public void moveLineIndex(final int i) {
        if (i != this.currentIndex) {
            this.leftSelectedIndex = this.dateToX(i) + (this.barWidth >> 1);
            this.currentIndex = i;
            this.repaint();
        }
        if (i >= 0) {
            final StockDetail stockdetail = this.StockDetailAt(i);
            this.setDetail(stockdetail, -1.0);
        }
    }
    
    public int openIntToY(final double d) {
        return this.volumeHeight - (int)(this.volumeHeight * (d - this.currentHeader().minimumOpenint) / Math.max(this.currentHeader().maximumOpenint - this.currentHeader().minimumOpenint, 1)) + this.volumeTop;
    }
    
    public void paint(final Graphics g) {
        g.translate(-this.tx, -this.ty);
        g.drawImage(this.offImage, 0, 0, this);
    }
    
    public int percentToY(final double d) {
        return this.chartHeight - (int)(this.chartHeight * (d - this.minPercent) / Math.max(this.percentSpan, 1.0000000000000002E-6));
    }
    
    public void refresh() {
        this.updateChart();
        this.repaint();
    }
    
    public void removeAllTrendlines() {
        this.drags.removeAllElements();
        this.updateCookie();
        this.updateChart();
        this.repaint();
    }
    
    public void removeDrags() {
        this.drags.removeAllElements();
        this.updateCookie();
    }
    
    public void removeLastTrendline() {
        if (this.drags.size() > 0) {
            if (this.shapesel) {
                this.drags.removeElementAt(this.shapeselected);
            }
            this.shapesel = false;
            this.selected = false;
            this.updateCookie();
            this.updateChart();
        }
    }
    
    public void removeRightDetail() {
        this.postEvent(new Event(this, 9004, null));
    }
    
    public void resetDimensions() {
        try {
            final int i = this.size().height;
            final int j = this.fm.getHeight() * 2;
            if (this.tablestatus == 0) {
                this.dateWidth = this.fm.stringWidth("99:99 ");
            }
            else {
                this.dateWidth = this.fm.stringWidth("99/99/99 ");
            }
            this.chartLeft = this.fm.stringWidth("999.999");
            this.chartRight = Math.min(this.size().width, 1200) - this.chartLeft;
            if (this.hideRight) {
                this.chartRight = Math.min(this.size().width, 1200) - 5;
            }
            this.chartWidth = this.chartRight - this.chartLeft;
            this.chartTop = 0;
            this.volumeHeight = 0;
            if (this.showVolume) {
                this.volumeHeight = i >> 2;
            }
            this.chartBottom = Math.min(i, 800) - this.volumeHeight - (j >> 1);
            if (this.mouseflag) {
                this.chartBottom = this.dragY;
            }
            this.volumeTop = this.chartBottom;
            this.volumeBottom = Math.min(i, 800) - (j >> 1) - 3;
            this.volumeHeight = this.volumeBottom - this.volumeTop;
            this.chartHeight = this.chartBottom - this.chartTop + this.volumeHeight;
            this.volumeHeight = 0;
            this.chartBottom = this.volumeBottom;
            this.volumeTop = this.volumeBottom;
            this.minValue = 1.0;
            this.maxValue = 100.0;
            this.minVolume = 1;
            this.maxVolume = 100;
            this.valueSpan = this.maxValue - this.minValue;
            this.volumeSpan = this.maxVolume - this.minVolume;
            this.volumeSpan = 0.0;
        }
        catch (Exception exception) {}
    }
    
    public void resetScale() {
        this.minValue = 2.147483647E9;
        this.maxValue = -2.147483648E9;
        this.minPercent = 2.147483647E9;
        this.maxPercent = -2.147483648E9;
        this.minVolume = 0;
        this.maxVolume = Integer.MIN_VALUE;
        this.findExtremes(null);
        this.usePercent = false;
        if (this.chartType == 5) {
            this.usePercent = true;
        }
        this.minValue *= 0.9990000000000001;
        if (this.minValue <= 2.0 && this.maxValue < 12.0 && this.haveData && !this.forceScale) {
            this.logChart = false;
            this.postEvent(new Event(this, 4010, null));
        }
        final double d = this.minValue;
        if (this.minValue < 0.5 && !this.logChart) {
            this.minValue = 0.0;
        }
        if (this.logChart && this.minValue == 0.0) {
            this.minValue = Math.max(0.001, d);
        }
        if (this.maxVolume > 0) {
            final int i = String.valueOf(this.maxVolume).length();
            final int j = Integer.parseInt(String.valueOf(this.maxVolume).substring(0, 1));
            this.maxVolume = (int)(Math.pow(10.0, i - 1) * (j + 1));
        }
        ++this.maxPercent;
        ++this.minPercent;
        this.percentSpan = this.maxPercent - this.minPercent;
        this.valueSpan = this.maxValue - this.minValue;
        this.volumeSpan = this.maxVolume - this.minVolume;
    }
    
    public void reshape(final int i, final int j, final int k, final int l) {
        super.reshape(i, j, k, l);
        this.resetDimensions();
    }
    
    public void setColorChart(final boolean flag) {
        this.colorChart = flag;
        this.updateChart();
    }
    
    public void setCursor(final int i) {
        this.cursor = i;
        this.mouseMove(null, this.chartLeft + (this.chartWidth >> 1), this.chartTop + (this.chartHeight >> 1));
        this.repaint();
    }
    
    public void setDates(final Date date, final Date date1) {
        this.minDate = 0;
        while (this.StockDetailAt(this.minDate).getDate().before(date)) {
            ++this.minDate;
        }
        this.maxDate = this.minDate;
        while (this.StockDetailAt(this.maxDate).getDate().before(date1) && this.maxDate < this.currentHeader().count()) {
            ++this.maxDate;
        }
        this.maxDate = this.currentHeader().count() - 1;
        this.dateSpan = Math.max(this.maxDate - this.minDate + 1, 1);
        this.resetDimensions();
        this.resetScale();
        this.updateChart();
        System.out.println(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("Maxdate :").concat(String.valueOf(this.maxDate))).concat(String.valueOf(" Mindate:"))).concat(String.valueOf(this.minDate))).concat(String.valueOf(" currentHeader().count():"))).concat(String.valueOf(this.currentHeader().count())));
    }
    
    public void setDetail(final StockDetail stockdetail, final double d) {
        this.currentDetail = stockdetail;
        this.currentValue = d;
        this.postEvent(new Event(this, 9002, null));
    }
    
    public void setMouseCursor(final int i) {
        Object obj;
        for (obj = new Object(), obj = this.getParent(); !(obj instanceof Frame); obj = ((Component)obj).getParent()) {}
        if (obj != null) {
            ((Frame)obj).setCursor(i);
        }
    }
    
    public void setRightDetail(final StockDetail stockdetail) {
        this.postEvent(new Event(this, 9003, stockdetail));
    }
    
    public void setType(final int i) {
        this.chartType = i;
        this.updateChart();
    }
    
    public static String shortDate(final Date date) {
        return String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(date.getDate()).concat(String.valueOf("/"))).concat(String.valueOf(date.getMonth()))).concat(String.valueOf(1))).concat(String.valueOf("/"))).concat(String.valueOf(date.getYear() + 1900));
    }
    
    public void unZoom() {
        try {
            this.drawcnt = 0;
            this.setDates(this.StockDetailAt(0).getDate(), this.StockDetailAt(this.currentHeader().count() - 1).getDate());
        }
        catch (Exception exception) {}
    }
    
    public void update(final Graphics g) {
        if (this.chartImage != null) {
            this.offG.drawImage(this.chartImage, 0, 0, this);
        }
        Label_1030: {
            if (!this.haveData) {
                if (this.sdl == null) {
                    if (this.sdl == null) {
                        this.offG.setFont(this.titleFont);
                        this.offG.setColor(this.txtColor);
                        this.offG.setFont(this.labelFont);
                        int k = this.chartTop + this.chartG.getFontMetrics().getHeight() + 30;
                        for (int i1 = 0; i1 < this.welcome.length; ++i1) {
                            this.offG.drawString(this.welcome[i1], this.chartLeft + 10, k);
                            k += this.chartG.getFontMetrics().getHeight() + 2;
                        }
                    }
                    break Label_1030;
                }
            }
            try {
                if (this.dragging) {
                    this.offG.setColor(new Color(159, 224, 242));
                    this.offG.setXORMode(Color.red);
                    int j = this.xToDate(this.dragX);
                    int l = this.dragY;
                    if (this.dragX >= this.chartLeft + this.chartWidthExpanded()) {
                        j = this.maxDate;
                        final int j2 = (this.mouseY - l) * (this.chartLeft + this.chartWidthExpanded() - this.mouseX) / (this.dragX - this.mouseX);
                        l = this.mouseY - j2;
                    }
                    if (this.dragX < this.chartLeft) {
                        j = this.minDate;
                        final int k2 = (this.mouseY - l) * (this.mouseX - this.chartLeft) / (this.mouseX - this.dragX);
                        l = this.mouseY - k2;
                    }
                    this.offG.drawLine(this.dateToX(this.xToDate(this.mouseX)) + this.hb, this.mouseY, this.dateToX(j) + this.hb, l);
                    this.offG.dispose();
                    this.offG = this.offImage.getGraphics();
                }
                else if (this.rightSelectedIndex > this.chartLeft && this.rightSelectedIndex <= this.chartRight && this.leftSelectedIndex > this.chartLeft && this.leftSelectedIndex <= this.chartRight) {
                    this.offG.setColor(new Color(159, 224, 242));
                    this.offG.setXORMode(Color.red);
                    if (this.leftSelectedIndex < this.rightSelectedIndex) {
                        this.offG.fillRect(this.leftSelectedIndex, this.chartTop + 1, this.rightSelectedIndex - this.leftSelectedIndex, this.chartHeight - 1);
                        this.offG.fillRect(this.leftSelectedIndex, this.volumeTop + 1, this.rightSelectedIndex - this.leftSelectedIndex, this.volumeHeight - 1);
                    }
                    else {
                        this.offG.fillRect(this.rightSelectedIndex, this.chartTop + 1, this.leftSelectedIndex - this.rightSelectedIndex, this.chartHeight - 1);
                        this.offG.fillRect(this.rightSelectedIndex, this.volumeTop + 1, this.leftSelectedIndex - this.rightSelectedIndex, this.volumeHeight - 1);
                    }
                    this.offG.dispose();
                    this.offG = this.offImage.getGraphics();
                }
                else if (this.cursor > 0) {
                    this.offG.setColor(new Color(159, 224, 242));
                    if (this.leftSelectedIndex > this.chartLeft && this.leftSelectedIndex < this.chartRight) {
                        this.offG.setColor(new Color(159, 224, 242));
                        this.offG.setXORMode(Color.red);
                        this.offG.drawLine(this.leftSelectedIndex, this.chartTop, this.leftSelectedIndex, this.chartBottom);
                        this.offG.drawLine(this.leftSelectedIndex, this.volumeTop, this.leftSelectedIndex, this.volumeBottom);
                        if (this.cursor == 2 && this.mouseY >= this.chartTop && this.mouseY <= this.chartBottom) {
                            this.offG.drawLine(this.chartLeft, this.mouseY, this.chartRight, this.mouseY);
                        }
                        this.offG.dispose();
                        this.offG = this.offImage.getGraphics();
                    }
                }
            }
            catch (Exception ex) {}
            this.offG.setFont(this.titleFont);
            this.offG.setColor(this.baseColor);
            if (this.currentHeader() != null) {
                this.legend = this.currentHeader().symbol;
                int m = this.chartWidth - this.offG.getFontMetrics().stringWidth(this.legend) >> 1;
                if (!this.showSymbol) {
                    this.legend = "";
                }
                else {
                    this.offG.drawString(this.legend, m, this.chartTop + this.chartG.getFontMetrics().getHeight() + 6);
                    m += this.offG.getFontMetrics().stringWidth(this.legend) + 5;
                }
                if (this.showSymbol) {
                    this.offG.drawString("-", m, this.chartTop + this.chartG.getFontMetrics().getHeight() + 6);
                    m += this.offG.getFontMetrics().stringWidth("-") + 5;
                }
                this.offG.setColor(Color.cyan);
                this.offG.setFont(this.titleFont);
            }
        }
        this.offG.setColor(Color.black);
        this.offG.setFont(new Font("sanserif", 0, 9));
        this.paint(g);
    }
    
    public void updateChart() {
        if (this.sdl != null) {
            return;
        }
        if (this.chartImage == null || this.size().width > this.offImage.getWidth(this) || this.size().height > this.offImage.getHeight(this)) {
            this.createImages(this.size().width, this.size().height);
        }
        this.resetDimensions();
        this.resetScale();
        this.chartG.setFont(this.labelFont);
        this.chartG.setColor(this.baseColor);
        this.chartG.fillRect(0, 0, this.size().width, this.size().height);
        this.chartG.setColor(this.bgColor);
        this.chartG.fillRect(this.chartLeft, this.chartTop, this.chartWidth, this.chartHeight);
        this.chartG.fillRect(this.chartLeft, this.volumeTop, this.chartWidth, this.volumeHeight);
        this.chartG.setColor(this.chartBorderColor);
        this.chartG.drawRect(this.chartLeft, this.chartTop, this.chartWidth, this.chartHeight);
        this.chartG.drawRect(this.chartLeft, this.volumeTop, this.chartWidth, this.volumeHeight);
        if (this.watermark != null) {
            this.chartG.drawImage(this.watermark, this.chartLeft + (this.chartWidth - this.watermark.getWidth(this) >> 1), this.chartTop + (this.chartHeight - this.watermark.getHeight(this) >> 1), this);
        }
        if (!this.haveData) {
            this.drawBorders();
            this.repaint();
            return;
        }
        if (this.maxDate < this.currentHeader().count() - 1) {
            this.expanded = false;
            this.postEvent(new Event(this, 12323, null));
        }
        this.barWidth = Math.max(1, (this.chartWidthExpanded() - (this.expanded ? 50 : 0)) / Math.max(this.dateSpan, 1));
        if (this.barWidth % 2 == 0) {
            ++this.barWidth;
        }
        this.drawAxis(true);
        if (this.maxDate < this.currentHeader().count() - 1) {
            this.expanded = false;
        }
        this.hb = this.barWidth >> 1;
        final int i = this.barWidth >> 2;
        final int j = this.barWidth >> 3;
        final int k = this.valueToY(this.minValue);
        int l = this.dateToX(this.minDate);
        int i2 = this.valueToY(this.StockDetailAt(this.minDate).getClose());
        final int j2 = this.dateToX(this.maxDate);
        final int k2 = this.valueToY(this.StockDetailAt(this.maxDate).getClose());
        final int l2 = this.dateToX(this.minDate);
        this.chartG.setColor(this.txtColor);
        int j3 = this.maxDate;
        int k3 = this.chartType;
        if (this.usePercent) {
            k3 = 5;
        }
        if (this.symbol.length() == 5 && this.symbol.endsWith("X")) {
            k3 = 2;
        }
        System.out.println(String.valueOf("Switch k2:").concat(String.valueOf(k3)));
        switch (k3) {
            case 2: {
                try {
                    for (int l3 = this.minDate; l3 <= j3; ++l3) {
                        final StockDetail stockdetail = this.StockDetailAt(l3);
                        stockdetail.x1 = this.dateToX(l3);
                        stockdetail.x2 = stockdetail.x1 + this.barWidth;
                        int l4 = this.valueToY(stockdetail.getClose());
                        if (stockdetail.x2 >= this.chartRight) {
                            stockdetail.x2 = this.chartRight;
                        }
                        if (l4 >= this.chartBottom) {
                            l4 = this.chartBottom;
                        }
                        this.chartG.drawLine(l, i2, stockdetail.x2 - this.hb, l4);
                        l = stockdetail.x2 - this.hb;
                        i2 = l4;
                    }
                    this.chartG.drawLine(l, i2, l + this.hb, i2);
                    this.perform = false;
                }
                catch (Exception exception) {
                    System.out.println("stockdetail.date:");
                }
                break;
            }
            case 3: {
                try {
                    for (int i3 = this.minDate; i3 <= j3; ++i3) {
                        final StockDetail stockdetail2 = this.StockDetailAt(i3);
                        stockdetail2.x1 = this.dateToX(i3);
                        stockdetail2.x2 = stockdetail2.x1 + this.barWidth;
                        int i4 = this.valueToY(stockdetail2.getClose());
                        if (stockdetail2.x2 >= this.chartRight) {
                            stockdetail2.x2 = this.chartRight;
                        }
                        if (i4 >= this.chartBottom) {
                            i4 = this.chartBottom;
                        }
                        final Polygon polygon1 = new Polygon();
                        polygon1.addPoint(l, i2);
                        polygon1.addPoint(stockdetail2.x2 - this.hb, i4);
                        polygon1.addPoint(stockdetail2.x2 - this.hb, k);
                        polygon1.addPoint(l, k);
                        this.chartG.fillPolygon(polygon1);
                        l = stockdetail2.x2 - this.hb;
                        i2 = i4;
                    }
                    final Polygon polygon2 = new Polygon();
                    polygon2.addPoint(l, i2);
                    polygon2.addPoint(l + this.hb, i2);
                    polygon2.addPoint(l + this.hb, k);
                    polygon2.addPoint(l, k);
                    this.chartG.fillPolygon(polygon2);
                    this.perform = false;
                }
                catch (Exception ex) {}
                break;
            }
            case 1: {
                try {
                    if (this.tablestatus == 1) {
                        for (int j4 = this.minDate; j4 <= j3; ++j4) {
                            final StockDetail stockdetail3 = this.StockDetailAt(j4);
                            stockdetail3.x1 = this.dateToX(j4);
                            stockdetail3.x2 = stockdetail3.x1 + this.barWidth;
                            final int j5 = this.valueToY(stockdetail3.getHigh());
                            final int j6 = this.valueToY(stockdetail3.getLow());
                            if (stockdetail3.x2 >= this.chartRight) {
                                stockdetail3.x2 = this.chartRight;
                            }
                            if (!this.colorChart) {
                                this.chartG.setColor(this.txtColor);
                            }
                            else if (j4 > this.minDate && this.StockDetailAt(j4 - 1).getClose() > stockdetail3.getClose()) {
                                this.chartG.setColor(this.txtColor);
                            }
                            else {
                                this.chartG.setColor(this.txtColor);
                            }
                            if (this.currentHeader().barSoFar && stockdetail3 == this.currentHeader().lastData()) {
                                if (this.currentHeader().liveChange.startsWith("-")) {
                                    this.chartG.setColor(this.txtColor);
                                }
                                else {
                                    this.chartG.setColor(this.txtColor);
                                }
                            }
                            this.chartG.drawLine(stockdetail3.x1 + this.hb, j5, stockdetail3.x1 + this.hb, j6);
                            final int l5 = this.valueToY(stockdetail3.getOpen());
                            final int l6 = this.valueToY(stockdetail3.getClose());
                            this.chartG.drawLine(stockdetail3.x1, l5, stockdetail3.x1 + this.hb, l5);
                            this.chartG.drawLine(stockdetail3.x2 - this.hb, l6, stockdetail3.x2, l6);
                            final int x2 = stockdetail3.x2;
                        }
                    }
                    else {
                        int k4 = 0;
                        int i5 = 42;
                        final Date date = this.StockDetailAt(this.minDate).getDate();
                        this.phourval = date.getHours();
                        this.pminval = date.getMinutes();
                        for (int k5 = this.minDate; k5 <= j3; ++k5) {
                            ++k4;
                            this.mindiff = 0;
                            final StockDetail stockdetail4 = this.StockDetailAt(k5);
                            final Date date2 = stockdetail4.getDate();
                            final int i6 = date2.getHours();
                            final int l7 = date2.getMinutes();
                            this.j11 = this.volumeToY(stockdetail4.getVolume());
                            if (i6 - this.phourval == 0) {
                                this.mindiff = l7 - this.pminval;
                            }
                            else if (i6 - this.phourval == 1) {
                                this.jdiff = 60 - this.pminval;
                                this.mindiff = this.jdiff + l7;
                            }
                            this.mindiff *= 1;
                            int i7 = 0;
                            if (k4 > 1) {
                                while (this.mindiff > 0) {
                                    i7 = this.chartWidthExpanded() / (Math.max(this.dateSpan, 1) + this.barWidth);
                                    i5 += i7;
                                    --this.mindiff;
                                }
                            }
                            if (this.dateSpan < this.barWidth || this.dateSpan - this.barWidth < 2) {
                                final int l8 = this.chartWidthExpanded() / this.barWidth;
                                if (this.dateSpan >= 12) {
                                    i5 += this.dateSpan - i7;
                                }
                                else {
                                    i5 = i5 + l8 + (this.barWidth - this.dateSpan);
                                }
                            }
                            stockdetail4.x1 = i5;
                            if (k5 > 0) {
                                this.phourval = i6;
                                this.pminval = l7;
                            }
                            stockdetail4.x2 = stockdetail4.x1 + this.barWidth;
                            final int i8 = this.valueToY(stockdetail4.getHigh());
                            final int k6 = this.valueToY(stockdetail4.getLow());
                            if (stockdetail4.x2 >= this.chartRight) {
                                stockdetail4.x2 = this.chartRight;
                            }
                            if (!this.colorChart) {
                                this.chartG.setColor(this.txtColor);
                            }
                            else if (k5 > this.minDate && this.StockDetailAt(k5 - 1).getClose() > stockdetail4.getClose()) {
                                this.chartG.setColor(this.txtColor);
                            }
                            else {
                                this.chartG.setColor(this.txtColor);
                            }
                            if (this.currentHeader().barSoFar && stockdetail4 == this.currentHeader().lastData()) {
                                if (this.currentHeader().liveChange.startsWith("-")) {
                                    this.chartG.setColor(this.txtColor);
                                }
                                else {
                                    this.chartG.setColor(this.txtColor);
                                }
                            }
                            final int j7 = this.valueToY(stockdetail4.getOpen());
                            final int l9 = this.valueToY(stockdetail4.getClose());
                            this.chartG.drawLine(stockdetail4.x2 - this.hb, l9, stockdetail4.x2, l9);
                            final int x3 = stockdetail4.x2;
                        }
                    }
                    this.perform = false;
                }
                catch (Exception ex2) {}
                break;
            }
        }
        this.chartG.setFont(this.labelFont);
        this.chartG.setColor(this.txtColor);
        this.chartG.clipRect(this.chartLeft + 1, this.chartTop + 1, this.chartWidthExpanded() - 2, this.chartHeight - 1);
        final int l10 = this.chartTop + 2 * this.chartG.getFontMetrics().getHeight() + 10;
        ++j3;
        this.chartG.dispose();
        this.chartG = this.chartImage.getGraphics();
        if (this.currentHeader().splits != null && !this.usePercent) {
            final StringTokenizer stringtokenizer = new StringTokenizer(this.currentHeader().splits, ",");
            while (stringtokenizer.hasMoreTokens()) {
                final SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
                Date date3;
                try {
                    date3 = new Date(simpledateformat.parse(stringtokenizer.nextToken().trim()).getTime() + 1L);
                }
                catch (Exception exception2) {
                    stringtokenizer.nextToken();
                    continue;
                }
                stringtokenizer.nextToken().trim();
                final int i9 = this.dateToX(date3) + this.hb;
                final byte byte0 = 5;
                if (i9 >= this.chartLeft && i9 <= this.chartRight && date3.after(this.StockDetailAt(this.minDate).getDate())) {
                    final StockDetail stockdetail5 = this.StockDetailAt(this.xToIndex(i9));
                    int i10 = this.valueToY(stockdetail5.getClose());
                    boolean flag = true;
                    if (i10 > this.chartTop + this.chartHeight / 2) {
                        flag = false;
                    }
                    if (flag) {
                        i10 += this.chartHeight / 4;
                    }
                    else {
                        i10 -= this.chartHeight / 4;
                    }
                    final Polygon polygon3 = new Polygon();
                    polygon3.addPoint(i9, i10);
                    if (flag) {
                        polygon3.addPoint(i9 - byte0, i10 + 2 * byte0);
                        polygon3.addPoint(i9 + byte0, i10 + 2 * byte0);
                    }
                    else {
                        polygon3.addPoint(i9 - byte0, i10 - 2 * byte0);
                        polygon3.addPoint(i9 + byte0, i10 - 2 * byte0);
                    }
                    this.chartG.setColor(Color.red);
                    this.chartG.fillPolygon(polygon3);
                    this.chartG.setColor(this.txtColor);
                    this.chartG.drawPolygon(polygon3);
                }
            }
        }
        this.showVolume = false;
        if (this.showVolume && !this.indInVolume) {
            this.chartG.clipRect(this.chartLeft + 1, this.volumeTop + 1, this.chartWidthExpanded() - 2, this.volumeHeight - 1);
            final int j8 = this.volumeToY(0.0);
            this.chartG.setColor(Color.blue);
            try {
                int k7 = 0;
                int l11 = 42;
                final Date date4 = this.StockDetailAt(this.minDate).getDate();
                this.phourval = date4.getHours();
                this.pminval = date4.getMinutes();
                for (int i11 = this.minDate; i11 <= j3; ++i11) {
                    ++k7;
                    this.mindiff = 0;
                    if (this.tablestatus == 1) {
                        final StockDetail stockdetail6 = this.StockDetailAt(i11);
                        this.j11 = this.volumeToY(stockdetail6.getVolume());
                        this.l13 = this.dateToX(i11);
                        if (this.colorChart) {
                            this.chartG.setColor(new Color(48, 129, 37));
                        }
                        else {
                            this.chartG.setColor(this.txtColor);
                        }
                        if (this.colorChart && i11 > this.minDate && this.StockDetailAt(i11 - 1).getClose() > stockdetail6.getClose()) {
                            this.chartG.setColor(Color.red);
                        }
                        this.chartG.fillRect(this.l13 + i, this.j11, Math.max(this.hb, 1), j8 - this.j11);
                    }
                    if (this.tablestatus == 0) {
                        final StockDetail stockdetail7 = this.StockDetailAt(i11);
                        final Date date5 = stockdetail7.getDate();
                        final int j9 = date5.getHours();
                        final int j10 = date5.getMinutes();
                        this.j11 = this.volumeToY(stockdetail7.getVolume());
                        if (j9 - this.phourval == 0) {
                            this.mindiff = j10 - this.pminval;
                        }
                        else if (j9 - this.phourval == 1) {
                            this.jdiff = 60 - this.pminval;
                            this.mindiff = this.jdiff + j10;
                        }
                        this.mindiff *= 1;
                        int i12 = 0;
                        if (k7 > 1) {
                            while (this.mindiff > 0) {
                                i12 = this.chartWidthExpanded() / (Math.max(this.dateSpan, 1) + this.barWidth);
                                l11 += i12;
                                --this.mindiff;
                            }
                        }
                        if (this.dateSpan < this.barWidth || this.dateSpan - this.barWidth < 2) {
                            final int k8 = this.chartWidthExpanded() / this.barWidth;
                            if (this.dateSpan >= 12) {
                                l11 += this.dateSpan - i12;
                            }
                            else {
                                l11 = l11 + k8 + (this.barWidth - this.dateSpan);
                            }
                        }
                        this.l13 = l11;
                        this.chartG.setColor(this.txtColor);
                        if (this.colorChart && i11 > this.minDate && this.StockDetailAt(i11 - 1).getClose() > stockdetail7.getClose()) {
                            this.chartG.setColor(Color.red);
                        }
                        this.chartG.fillRect(this.l13 - i, this.j11, Math.max(this.hb, 1), j8 - this.j11);
                        if (i11 > 0) {
                            this.phourval = j9;
                            this.pminval = j10;
                        }
                    }
                }
            }
            catch (Exception ex3) {}
            if (this.currentHeader().isFuture()) {
                this.chartG.setColor(Color.red.darker());
                int l12 = -1;
                int i13 = -1;
                this.currentHeader();
                this.currentHeader();
                for (int j11 = this.minDate; j11 <= j3; ++j11) {
                    final StockDetail stockdetail8 = this.StockDetailAt(j11);
                    if (stockdetail8 != null) {
                        final int j12 = this.openIntToY(stockdetail8.getOpenint());
                        final int k9 = this.dateToX(j11);
                        if (l12 >= 0 && stockdetail8.getOpenint() != 0) {
                            this.chartG.drawLine(l12, i13, k9, j12);
                        }
                        l12 = k9;
                        i13 = j12;
                    }
                }
            }
            if (this.currentHeader().isFuture() && this.currentHeader().getCash() != null && this.currentHeader().showCash()) {
                this.chartG.dispose();
                (this.chartG = this.chartImage.getGraphics()).clipRect(this.chartLeft + 1, this.chartTop + 1, this.chartWidthExpanded() - 2, this.chartHeight - 1);
                this.chartG.setColor(Color.red.darker());
                int i14 = -1;
                int j13 = -1;
                final StockHeader stockheader = this.currentHeader().getCash();
                for (int j14 = 0; j14 <= stockheader.count(); ++j14) {
                    final StockDetail stockdetail9 = stockheader.dataAt(j14);
                    if (stockdetail9 != null) {
                        final int l13 = this.valueToY(stockdetail9.getClose());
                        final int k10 = this.dateToX(stockdetail9.getDate());
                        if (i14 >= 0) {
                            this.chartG.drawLine(i14, j13, k10, l13);
                        }
                        i14 = k10;
                        j13 = l13;
                    }
                }
            }
            this.chartG.dispose();
            this.chartG = this.chartImage.getGraphics();
        }
        this.drawBorders();
        this.chartG.clipRect(this.chartLeft + 1, this.chartTop + 1, this.chartWidth - 2, this.chartHeight - 2);
        for (int k11 = 0; k11 < this.drags.size(); ++k11) {
            final ADrag adrag = this.drags.elementAt(k11);
            if (adrag.infinite) {
                this.chartG.setColor(this.dragColor.brighter());
            }
            else {
                this.chartG.setColor(this.dragColor);
            }
            final int k12 = this.dateToX(adrag.getDate1()) + this.hb;
            int k13 = this.dateToX(adrag.getDate2()) + this.hb;
            final int k14 = this.valueToY(adrag.getValue1());
            int k15 = this.valueToY(adrag.getValue2());
            if (adrag.infinite) {
                double d;
                if (k13 > k12) {
                    d = (k15 - k14) / (k13 - k12);
                    k13 = this.chartRight;
                }
                else {
                    d = (k15 - k14) / (k12 - k13);
                    k13 = this.chartLeft;
                }
                k15 = (int)(Math.abs(k13 - k12) * d) + k14;
            }
            this.chartG.drawLine(k12, k14, k13, k15);
        }
        this.chartG.dispose();
        (this.chartG = this.chartImage.getGraphics()).setColor(this.txtColor);
        this.chartG.setFont(new Font("sanserif", 0, 7));
        this.repaint();
    }
    
    public void updateCookie() {
        this.cookieValue = null;
        String s = "";
        for (int i = 0; i < this.drags.size(); ++i) {
            final ADrag adrag = this.drags.elementAt(i);
            if (this.indexToDate(adrag.getDate1()) != null && this.indexToDate(adrag.getDate2()) != null) {
                s = String.valueOf(String.valueOf(s).concat(String.valueOf(shortDate(this.indexToDate(adrag.getDate1()))))).concat(String.valueOf(","));
                s = String.valueOf(String.valueOf(s).concat(String.valueOf(String.valueOf(adrag.getValue1()).concat(String.valueOf("000000")).substring(0, 5)))).concat(String.valueOf(","));
                s = String.valueOf(String.valueOf(s).concat(String.valueOf(shortDate(this.indexToDate(adrag.getDate2()))))).concat(String.valueOf(","));
                s = String.valueOf(String.valueOf(s).concat(String.valueOf(String.valueOf(adrag.getValue2()).concat(String.valueOf("000000")).substring(0, 5)))).concat(String.valueOf(","));
                if (adrag.infinite) {
                    s = String.valueOf(s).concat(String.valueOf("1"));
                }
                else {
                    s = String.valueOf(s).concat(String.valueOf("0"));
                }
                s = String.valueOf(s).concat(String.valueOf(";"));
            }
        }
        this.cookieValue = s;
    }
    
    public int valueToY(final double d) {
        if (this.expanded) {
            if (this.logChart) {
                return this.chartHeight - (int)(this.chartHeightExpanded() * (Math.log(d) - Math.log(this.minValue)) / (Math.log(this.maxValue) - Math.log(this.minValue)));
            }
            return this.chartHeight - (int)(this.chartHeightExpanded() * (d - this.minValue) / Math.max(this.valueSpan, 1.0E-4));
        }
        else {
            if (this.logChart) {
                return this.chartHeight - (int)(this.chartHeight * (Math.log(d) - Math.log(this.minValue)) / (Math.log(this.maxValue) - Math.log(this.minValue)));
            }
            return this.chartHeight - (int)(this.chartHeight * (d - this.minValue) / Math.max(this.valueSpan, 1.0E-4));
        }
    }
    
    public int volumeToY(final double d) {
        int i;
        if (this.logChart) {
            i = this.volumeHeight - (int)(this.volumeHeightExpanded() * (Math.log(d) - Math.log(this.minVolume)) / (Math.log(this.maxVolume) - Math.log(this.minVolume)));
        }
        else {
            i = this.volumeHeight - (int)(this.volumeHeight * (d - this.minVolume) / Math.max(this.volumeSpan, 1.0)) + this.volumeTop;
        }
        return i;
    }
    
    public int xToDate(final int i) {
        return this.xToIndex(i);
    }
    
    public int xToIndex(final int i) {
        try {
            for (int j = this.minDate; j <= this.maxDate; ++j) {
                final StockDetail stockdetail = this.StockDetailAt(j);
                final Date date = stockdetail.getDate();
                if (stockdetail.x1 <= i && stockdetail.x2 >= i) {
                    final int l;
                    final int k = l = j;
                    return l;
                }
            }
        }
        catch (Exception ex) {}
        return -1;
    }
    
    public double yToValue(final int i) {
        if (this.expanded) {
            if (this.logChart) {
                return Math.pow(2.718281828459045, (this.chartHeight - i) * (Math.log(this.maxValue) - Math.log(this.minValue)) / this.chartHeightExpanded() + Math.log(this.minValue));
            }
            return (i - this.chartHeight) * Math.max(this.valueSpan, 1.0) / -this.chartHeightExpanded() + this.minValue;
        }
        else {
            if (this.logChart) {
                return Math.pow(2.718281828459045, (Math.log(this.maxValue) - Math.log(this.minValue)) * (1.0 - i / this.chartHeight) + Math.log(this.minValue));
            }
            return (i - this.chartHeight) * Math.max(this.valueSpan, 1.0) / -this.chartHeight + this.minValue;
        }
    }
}
