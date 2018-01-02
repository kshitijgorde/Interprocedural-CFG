// 
// Decompiled by Procyon v0.5.30
// 

package com.quotemedia.awt;

import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.Label;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Frame;
import java.awt.Button;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Canvas;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Vector;
import java.util.Hashtable;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.awt.MediaTracker;
import java.awt.Dimension;
import java.util.Iterator;
import java.net.URL;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.HashMap;
import java.awt.Image;
import java.applet.Applet;
import java.awt.Color;
import java.text.DecimalFormat;
import java.awt.Panel;

public class QuotePage extends Panel
{
    int THIS_WIDTH;
    int THIS_HEIGHT;
    drawData drawdata;
    DecimalFormat df2;
    String defaSymbol;
    String baseDataUrl;
    String[] olddata;
    Color rowone;
    Color rowtwo;
    Color datacolor;
    Applet applet;
    long noOfShares;
    String chartUrl;
    String chartScale;
    Image chartImage;
    String dataType;
    String oldSymbol;
    public Image rt;
    public Image eod;
    public Image pd;
    private HashMap chartParameterMap;
    private String sName;
    private String quoteTime;
    DateFormat hmmFormat;
    
    public QuotePage(final String symbol, final Applet applet, final String wmid) {
        this.THIS_WIDTH = 530;
        this.THIS_HEIGHT = 190;
        this.baseDataUrl = "";
        this.olddata = new String[26];
        this.rowone = Color.decode("#F7F7F7");
        this.rowtwo = Color.decode("#FFFFFF");
        this.datacolor = Color.black;
        this.noOfShares = 0L;
        this.chartUrl = "";
        this.chartScale = "1d";
        this.chartImage = null;
        this.dataType = null;
        this.oldSymbol = "";
        this.rt = null;
        this.eod = null;
        this.pd = null;
        this.sName = "";
        this.quoteTime = "";
        this.hmmFormat = new SimpleDateFormat("hh:mm");
        this.defaSymbol = symbol;
        this.applet = applet;
        final URL homeUrl = applet.getCodeBase();
        final URL docUrl = applet.getDocumentBase();
        final String baseUrl = (homeUrl.getProtocol().equals("file") ? "http" : homeUrl.getProtocol()) + "://" + (homeUrl.getHost().equals("") ? "app.quotemedia.com" : homeUrl.getHost()) + "/";
        final String hostName = docUrl.getHost();
        final StringBuffer chartUrlBuffer = new StringBuffer();
        chartUrlBuffer.append(baseUrl).append("quotetools/getChart?imageType=jpg&chhig=150&chwid=240");
        this.chartParameterMap = this.getChartParameters();
        for (final String paramName : this.chartParameterMap.keySet()) {
            final String value = this.chartParameterMap.get(paramName);
            chartUrlBuffer.append("&").append(paramName).append("=").append(value);
        }
        final NumberFormat nf2 = NumberFormat.getInstance();
        (this.df2 = (DecimalFormat)nf2).applyPattern("######0.00");
        final StringBuffer bDUrl = new StringBuffer();
        bDUrl.append(baseUrl).append("quotetools/getFullQuotesCSV.csv?host=").append(hostName);
        final String symbology = this.getParameter("symbology", null);
        if (symbology != null) {
            chartUrlBuffer.append("&symbology=" + symbology);
            bDUrl.append("&symbology=" + symbology);
        }
        if (wmid != null) {
            bDUrl.append("&webmasterId=").append(wmid);
            chartUrlBuffer.append("&webmasterId=").append(wmid);
        }
        this.chartUrl = chartUrlBuffer.toString();
        this.baseDataUrl = bDUrl.append("&symbols=").toString();
        this.setSize(this.THIS_WIDTH, this.THIS_HEIGHT);
        final Dimension sS = Toolkit.getDefaultToolkit().getScreenSize();
        (this.drawdata = new drawData(this.defaSymbol)).repaint();
        final Panel tcp = new Panel();
        tcp.setLayout(new BorderLayout());
        tcp.add("Center", this.drawdata);
        final Panel south = new Panel();
        south.setLayout(new FlowLayout());
        final Panel bottom = new Panel();
        bottom.setLayout(new BorderLayout());
        bottom.add("East", south);
        this.setLayout(new BorderLayout());
        this.add("Center", tcp);
        this.add("South", bottom);
        this.setBackground(Color.decode("#CCCCCC"));
    }
    
    private HashMap getChartParameters() {
        final HashMap param = new HashMap();
        param.put("chtype", this.getParameter("chtype", "AreaChart"));
        param.put("chfrmon", this.getParameter("chfrmon", "off"));
        param.put("chfrm", this.getParameter("chfrm", "cccccc"));
        param.put("chbdron", this.getParameter("chbdron", "on"));
        param.put("chbdr", this.getParameter("chbdr", "cccccc"));
        param.put("chbg", this.getParameter("chbg", "ffffff"));
        param.put("chln", this.getParameter("chln", "465665"));
        param.put("chfill", this.getParameter("chfill", "c0c9d2"));
        param.put("chfill2", this.getParameter("chfill2", "c0c9d2"));
        param.put("chgrdon", this.getParameter("chgrdon", "on"));
        param.put("chgrd", this.getParameter("chgrd", "cccccc"));
        param.put("chton", this.getParameter("chton", "off"));
        param.put("chtcol", this.getParameter("chtcol", "000000"));
        param.put("chxyc", this.getParameter("chxyc", "111111"));
        param.put("chpcon", this.getParameter("chpcon", "on"));
        param.put("chpccol", this.getParameter("chpccol", "ee0000"));
        param.put("chmrg", this.getParameter("chmrg", "2"));
        return param;
    }
    
    private String getParameter(final String name, final String defaultValue) {
        String paramValue = defaultValue;
        if (this.applet.getParameter(name) != null) {
            paramValue = this.applet.getParameter(name);
        }
        return paramValue;
    }
    
    public String getLastScale() {
        return this.chartScale;
    }
    
    public String getSymbol() {
        return this.defaSymbol;
    }
    
    public void refreshData() {
        this.setSymbol(this.defaSymbol);
    }
    
    public void setSymbol(final String tdefSymbol) {
        this.defaSymbol = tdefSymbol;
        if (tdefSymbol != null && !tdefSymbol.equals("N/A")) {
            this.drawdata.setData(this.getData(this.defaSymbol));
            this.getChartImage(tdefSymbol, this.chartScale);
        }
        this.drawdata.repaint();
    }
    
    public void getChartImage(final String symbol, final String chartScale) {
        this.drawdata.bB = null;
        this.chartScale = chartScale;
        try {
            if (this.dataType.equalsIgnoreCase("EOD") || this.dataType.equalsIgnoreCase("END-OF-DAY")) {
                if (!symbol.equalsIgnoreCase(this.oldSymbol)) {
                    this.chartScale = "5d";
                }
            }
            else if (!symbol.equalsIgnoreCase(this.oldSymbol)) {
                this.chartScale = "1d";
            }
            final String imgUrl = this.chartUrl + "&symbol=" + symbol + "&chscale=" + this.chartScale;
            try {
                final URL url = new URL(imgUrl);
                this.chartImage = this.applet.getImage(url);
                final MediaTracker mt = new MediaTracker(this.applet);
                if (this.chartImage != null) {
                    mt.addImage(this.chartImage, 0);
                }
                mt.waitForAll();
            }
            catch (Exception exp) {
                exp.printStackTrace();
            }
        }
        catch (Exception ex) {}
        this.oldSymbol = symbol;
        this.drawdata.repaint();
        this.repaint();
    }
    
    public String[] getData(final String symbol) {
        final String geturl = this.baseDataUrl + symbol;
        final int NO_OF_DATA = 32;
        final String[] mda = new String[28];
        try {
            final URL url = new URL(geturl);
            final URLConnection cN = url.openConnection();
            cN.setRequestProperty("Accept-Encoding", "gzip");
            cN.setDoOutput(true);
            cN.setDoInput(true);
            cN.setUseCaches(false);
            InputStream rs = null;
            if (cN.getContentEncoding() != null && cN.getContentEncoding().indexOf("gzip") > -1) {
                rs = new GZIPInputStream(cN.getInputStream());
            }
            else {
                rs = cN.getInputStream();
            }
            if (rs != null) {
                final BufferedReader ds = new BufferedReader(new InputStreamReader(rs));
                final Hashtable dtl = new Hashtable();
                final Vector ndx = new Vector();
                String stR = null;
                while ((stR = ds.readLine()) != null) {
                    if (stR != null && stR.length() > 5) {
                        final String[] rdt = this.gC(stR.toUpperCase(), NO_OF_DATA, ',');
                        mda[0] = rdt[2];
                        mda[1] = rdt[3];
                        mda[2] = rdt[4] + "%";
                        mda[3] = rdt[3];
                        mda[4] = rdt[10];
                        mda[5] = rdt[11];
                        mda[6] = rdt[12];
                        mda[7] = rdt[13];
                        mda[8] = rdt[5];
                        mda[9] = rdt[6];
                        mda[10] = rdt[7];
                        mda[11] = rdt[8];
                        mda[12] = rdt[18];
                        mda[13] = this.getFormatted(rdt[15]);
                        mda[14] = rdt[16];
                        mda[15] = rdt[17];
                        mda[16] = "N/A";
                        mda[17] = rdt[25];
                        mda[18] = rdt[24];
                        mda[19] = rdt[23];
                        this.dataType = rdt[20].trim();
                        mda[20] = this.getFormatted(rdt[27]);
                        mda[21] = rdt[26];
                        mda[22] = rdt[29];
                        mda[23] = rdt[19];
                        mda[24] = "N/A";
                        mda[25] = rdt[1];
                        mda[26] = rdt[30];
                        mda[27] = rdt[18];
                        this.olddata = mda;
                    }
                }
                ds.close();
                if (mda[25] != null) {
                    this.sName = mda[25] + " (" + this.defaSymbol + ")";
                }
                else {
                    this.sName = this.defaSymbol;
                }
                if (mda[27] != null) {
                    this.quoteTime = mda[27];
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
        return mda;
    }
    
    private String getMarketShare(final String lastStrPrice, final long noShares) {
        String marketCapStr = "N/A";
        float lastPrice = 0.0f;
        long marketCap = 0L;
        try {
            lastPrice = new Float(lastStrPrice);
            marketCap = (long)(lastPrice * noShares);
            if (marketCap < 1000000L) {
                marketCapStr = String.valueOf(this.df2.format(marketCap));
            }
            else if (marketCap >= 1000000L) {
                final float millionMCap = marketCap / 1000000.0f;
                if (millionMCap >= 1000.0f) {
                    final float billionMCap = millionMCap / 1000.0f;
                    marketCapStr = this.df2.format(billionMCap) + "B";
                }
                else {
                    marketCapStr = this.df2.format(millionMCap) + "M";
                }
            }
        }
        catch (Exception ex) {}
        return marketCapStr;
    }
    
    public String getFormatted(final String strData) {
        String formattedData = strData;
        if (strData.indexOf("N/A") < 0) {
            try {
                float floatData = Float.valueOf(strData);
                if (floatData > 1000.0f) {
                    floatData /= 1000.0f;
                    if (floatData > 1000.0f) {
                        floatData /= 1000.0f;
                        if (floatData > 1000.0f) {
                            floatData /= 1000.0f;
                            formattedData = this.df2.format(floatData) + "B";
                        }
                        else {
                            formattedData = this.df2.format(floatData) + "M";
                        }
                    }
                    else {
                        formattedData = this.df2.format(floatData) + "K";
                    }
                }
            }
            catch (Exception ex) {}
        }
        return formattedData;
    }
    
    public String[] gC(final String line, final int cols, final char comma) {
        try {
            final String[] str = new String[cols];
            if (line.length() > 3) {
                int o;
                int k;
                int i;
                for (o = 0, k = 0; (i = line.indexOf(comma, o)) >= 0 && k < cols; ++k, o = i + 1) {
                    str[k] = line.substring(o, i);
                }
                if (k < cols) {
                    final int m = line.lastIndexOf(comma);
                    str[k] = line.substring(m + 1);
                }
            }
            return str;
        }
        catch (Exception e) {
            return null;
        }
    }
    
    private class drawData extends Canvas implements MouseListener, MouseMotionListener, ActionListener
    {
        Graphics g1;
        public Image bB;
        String[] data;
        private FontMetrics fm;
        private FontMetrics datafm;
        private Font dataFont;
        private Font dataFontBold;
        int rectPos;
        int lastDataPos;
        int xpos;
        int lHeight;
        private int mouseX;
        private int mouseY;
        private int exchangeX;
        private int exchangeEndX;
        private int exchangeY;
        private Button close;
        private Frame warning;
        
        public drawData(final String tsymbol) {
            this.dataFont = new Font("SansSerif", 0, 12);
            this.dataFontBold = new Font("SansSerif", 1, 12);
            this.rectPos = 0;
            this.lastDataPos = 0;
            this.xpos = 250;
            this.lHeight = 17;
            this.mouseX = 0;
            this.mouseY = 0;
            this.exchangeX = 0;
            this.exchangeEndX = 0;
            this.exchangeY = 0;
            this.close = new Button("Close");
            this.lastDataPos = QuotePage.this.THIS_WIDTH - 5;
            final int dataSpace = this.lastDataPos - this.xpos;
            this.rectPos = dataSpace / 2;
            this.lastDataPos -= 8;
            this.fm = this.getFontMetrics(this.dataFont);
            this.datafm = this.getFontMetrics(this.dataFontBold);
            this.setData(QuotePage.this.getData(tsymbol));
            this.close.addActionListener(this);
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
        }
        
        public void setData(final String[] ddata) {
            this.data = ddata;
            if (this.data == null) {
                this.data = new String[26];
                for (int j = 0; j < this.data.length; ++j) {
                    this.data[j] = "N/A";
                }
            }
        }
        
        public void paint(final Graphics g) {
            if (this.bB == null) {
                this.bB = this.createImage(QuotePage.this.THIS_WIDTH, this.getSize().height);
            }
            (this.g1 = this.bB.getGraphics()).setColor(QuotePage.this.rowtwo);
            this.g1.fillRect(0, 0, QuotePage.this.THIS_WIDTH, this.getSize().height);
            this.g1.setColor(QuotePage.this.datacolor);
            this.g1.setFont(this.dataFontBold);
            if (this.data != null && this.data[25] != null) {
                this.g1.drawString(QuotePage.this.sName, 15, 15);
                int x = this.datafm.stringWidth(QuotePage.this.sName) + 30;
                if (x > QuotePage.this.THIS_WIDTH) {
                    x = QuotePage.this.THIS_WIDTH - 20;
                }
                if (QuotePage.this.dataType != null) {
                    if (QuotePage.this.rt != null && (QuotePage.this.dataType.equalsIgnoreCase("realtime") || QuotePage.this.dataType.equalsIgnoreCase("rt"))) {
                        this.g1.drawImage(QuotePage.this.rt, x, 4, this);
                    }
                    else if (QuotePage.this.eod != null && (QuotePage.this.dataType.equalsIgnoreCase("eod") || QuotePage.this.dataType.equalsIgnoreCase("end-of-day"))) {
                        this.g1.drawImage(QuotePage.this.eod, x, 4, this);
                    }
                    else if (QuotePage.this.pd != null && (QuotePage.this.dataType.equalsIgnoreCase("pd") || QuotePage.this.dataType.equalsIgnoreCase("previous-day"))) {
                        this.g1.drawImage(QuotePage.this.pd, x, 4, this);
                    }
                }
            }
            if (this.data != null && this.data[27] != null) {
                this.g1.drawString(QuotePage.this.quoteTime, QuotePage.this.THIS_WIDTH - (this.datafm.stringWidth(QuotePage.this.quoteTime) + 15), 15);
            }
            this.g1.setFont(this.dataFont);
            if (QuotePage.this.chartImage != null) {
                this.g1.drawImage(QuotePage.this.chartImage, 3, 20, this);
            }
            else {
                final String msg1 = "Sorry, no image data is";
                final String msg2 = "currently available.";
                this.g1.drawString(msg1, (160 - this.datafm.stringWidth(msg1)) / 2, this.getSize().height / 2);
                this.g1.drawString(msg2, (160 - this.datafm.stringWidth(msg2)) / 2, 15 + this.getSize().height / 2);
            }
            int ypos = 36;
            final int firstDataPos = this.xpos + this.rectPos - 10;
            final int xpos1 = firstDataPos + 10;
            this.g1.setColor(QuotePage.this.datacolor);
            this.g1.drawString("Last Price:", this.xpos, ypos);
            this.g1.drawString("%Change:", xpos1, ypos);
            this.g1.setFont(this.dataFontBold);
            this.g1.drawString(this.data[0], firstDataPos - this.datafm.stringWidth(this.data[0]), ypos);
            this.g1.drawString(this.data[2], this.lastDataPos - this.datafm.stringWidth(this.data[2]), ypos);
            ypos += this.lHeight;
            this.g1.setFont(this.dataFont);
            this.g1.setColor(QuotePage.this.rowone);
            this.g1.fillRect(this.xpos - 5, ypos - this.fm.getMaxAscent(), this.rectPos, this.fm.getMaxAscent() + 3);
            this.g1.fillRect(xpos1, ypos - this.fm.getMaxAscent(), this.rectPos - 2, this.fm.getMaxAscent() + 3);
            this.g1.setColor(QuotePage.this.datacolor);
            this.g1.drawString("Change:", this.xpos, ypos);
            this.g1.drawString(this.data[1], firstDataPos - this.fm.stringWidth(this.data[1]), ypos);
            this.g1.drawString("Open:", xpos1, ypos);
            this.g1.drawString(this.data[8], this.lastDataPos - this.fm.stringWidth(this.data[8]), ypos);
            ypos += this.lHeight;
            this.g1.drawString("High:", this.xpos, ypos);
            this.g1.drawString(this.data[9], firstDataPos - this.fm.stringWidth(this.data[9]), ypos);
            this.g1.drawString("Volume:", xpos1, ypos);
            this.g1.drawString(this.data[13], this.lastDataPos - this.fm.stringWidth(this.data[13]), ypos);
            ypos += this.lHeight;
            this.g1.setColor(QuotePage.this.rowone);
            this.g1.fillRect(this.xpos - 5, ypos - this.fm.getMaxAscent(), this.rectPos, this.fm.getMaxAscent() + 3);
            this.g1.fillRect(xpos1, ypos - this.fm.getMaxAscent(), this.rectPos - 2, this.fm.getMaxAscent() + 3);
            this.g1.setColor(QuotePage.this.datacolor);
            this.g1.drawString("Low:", this.xpos, ypos);
            this.g1.drawString(this.data[10], firstDataPos - this.fm.stringWidth(this.data[10]), ypos);
            this.g1.drawString("Prev Close:", xpos1, ypos);
            this.g1.drawString(this.data[11], this.lastDataPos - this.fm.stringWidth(this.data[11]), ypos);
            ypos += this.lHeight;
            this.g1.drawString("Bid:", this.xpos, ypos);
            this.g1.drawString(this.data[4], firstDataPos - this.fm.stringWidth(this.data[4]), ypos);
            this.g1.drawString("Last Trade:", xpos1, ypos);
            this.g1.drawString(this.data[12], this.lastDataPos - this.fm.stringWidth(this.data[12]), ypos);
            ypos += this.lHeight;
            this.g1.setColor(QuotePage.this.rowone);
            this.g1.fillRect(this.xpos - 5, ypos - this.fm.getMaxAscent(), this.rectPos, this.fm.getMaxAscent() + 3);
            this.g1.fillRect(xpos1, ypos - this.fm.getMaxAscent(), this.rectPos - 2, this.fm.getMaxAscent() + 3);
            this.g1.setColor(QuotePage.this.datacolor);
            this.g1.drawString("Bid Size:", this.xpos, ypos);
            this.g1.drawString(this.data[5], firstDataPos - this.fm.stringWidth(this.data[5]), ypos);
            this.g1.drawString("52 Wk High:", xpos1, ypos);
            this.g1.drawString(this.data[14], this.lastDataPos - this.fm.stringWidth(this.data[14]), ypos);
            ypos += this.lHeight;
            this.g1.drawString("Ask:", this.xpos, ypos);
            this.g1.drawString(this.data[6], firstDataPos - this.fm.stringWidth(this.data[6]), ypos);
            this.g1.drawString("52 Wk Low:", xpos1, ypos);
            this.g1.drawString(this.data[15], this.lastDataPos - this.fm.stringWidth(this.data[15]), ypos);
            ypos += this.lHeight;
            this.exchangeY = ypos;
            this.exchangeX = this.lastDataPos - this.fm.stringWidth(this.data[23]);
            this.exchangeEndX = this.exchangeX + this.fm.stringWidth(this.data[23]);
            this.g1.setColor(QuotePage.this.rowone);
            this.g1.fillRect(this.xpos - 5, ypos - this.fm.getMaxAscent(), this.rectPos, this.fm.getMaxAscent() + 3);
            this.g1.fillRect(xpos1, ypos - this.fm.getMaxAscent(), this.rectPos - 2, this.fm.getMaxAscent() + 3);
            this.g1.setColor(QuotePage.this.datacolor);
            this.g1.drawString("Ask Size:", this.xpos, ypos);
            this.g1.drawString(this.data[7], firstDataPos - this.fm.stringWidth(this.data[7]), ypos);
            this.g1.drawString("Exchange:", xpos1, ypos);
            this.g1.drawString(this.data[23], this.exchangeX, ypos);
            this.g1.drawLine(this.exchangeX, ypos + 1, this.exchangeEndX, ypos + 1);
            g.drawImage(this.bB, 0, 0, this);
        }
        
        public void mouseClicked(final MouseEvent e) {
        }
        
        public void mouseEntered(final MouseEvent e) {
            this.mouseX = e.getX();
            this.mouseY = e.getY();
            if (this.mouseX > this.exchangeX && this.mouseX < this.exchangeEndX && this.mouseY > this.exchangeY - 10 && this.mouseY < this.exchangeY + 10) {
                this.setCursor(new Cursor(12));
            }
            this.repaint();
        }
        
        public void mouseExited(final MouseEvent e) {
            this.setCursor(new Cursor(0));
            this.mouseX = 0;
            this.mouseY = 0;
            this.repaint();
        }
        
        public void mousePressed(final MouseEvent e) {
        }
        
        public void mouseReleased(final MouseEvent e) {
            this.mouseX = e.getX();
            this.mouseY = e.getY();
            if (this.mouseX > this.exchangeX && this.mouseX < this.exchangeEndX && this.mouseY > this.exchangeY - 10 && this.mouseY < this.exchangeY + 10) {
                this.showWarning(this.data[26]);
            }
        }
        
        public void mouseDragged(final MouseEvent e) {
        }
        
        public void mouseMoved(final MouseEvent e) {
            this.mouseX = e.getX();
            this.mouseY = e.getY();
            if (this.mouseX > this.exchangeX && this.mouseX < this.exchangeEndX && this.mouseY > this.exchangeY - 10 && this.mouseY < this.exchangeY + 10) {
                this.setCursor(new Cursor(12));
            }
            else {
                this.setCursor(new Cursor(0));
            }
            this.repaint();
        }
        
        public void updateMessage(final String msg) {
            this.repaint();
        }
        
        public void update(final Graphics g) {
            this.paint(g);
        }
        
        protected void showWarning(final String exchangeName) {
            this.warning = new Frame();
            this.close.addActionListener(this);
            this.warning.setLayout(new BorderLayout());
            final Label flabel = new Label(exchangeName);
            this.warning.add("Center", flabel);
            final Panel cntp = new Panel();
            cntp.setLayout(new FlowLayout(1, 5, 5));
            cntp.add(this.close);
            this.warning.add("South", cntp);
            this.warning.setBackground(Color.decode("#FFFFFF"));
            this.warning.addWindowListener(new WindowCloser());
            this.warning.setSize(400, 200);
            this.warning.validate();
            this.warning.pack();
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            this.warning.setLocation((screenSize.width - 400) / 2, (screenSize.width - 200) / 2);
            this.warning.show();
        }
        
        public void actionPerformed(final ActionEvent ev) {
            if (ev.getSource() == this.close) {
                this.warning.dispose();
            }
        }
        
        class WindowCloser extends WindowAdapter
        {
            public void windowClosing(final WindowEvent e) {
                final Window w = (Window)e.getSource();
                w.dispose();
            }
        }
    }
}
