import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.awt.event.ActionEvent;
import java.net.URLEncoder;
import java.net.URLConnection;
import java.io.DataInputStream;
import java.awt.Dimension;
import netscape.javascript.JSObject;
import java.util.Vector;
import java.util.StringTokenizer;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Font;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.HashMap;
import com.quotemedia.awt.SnapQuote;
import java.util.Hashtable;
import java.text.DecimalFormat;
import java.awt.Color;
import java.awt.Button;
import java.awt.Image;
import java.awt.Graphics;
import java.net.URL;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class twoRowTicker extends Applet implements ActionListener, useHandleInterface, MouseListener, MouseMotionListener
{
    private String symbol;
    URL docUrl;
    URL homeUrl;
    String baseUrl;
    Graphics backgc;
    Image backBuffer;
    twoRowHandle eq;
    twoRowHandle eq1;
    addsymbolframe hframe;
    Button refresh;
    Button edit;
    Button lefttoright;
    Button righttoleft;
    int textfontsize;
    int buttonfontsize;
    int datafontsize;
    int indexfontsize;
    String addUrl;
    String addfile;
    URL addurl;
    Color bgtop;
    Color fgtop;
    Color bgbottom;
    Color fgbottom;
    Color bgeast;
    Color bgwest;
    Color bgbtn;
    Color fgbtn;
    int fontsize;
    String[] allIndices;
    String defaultIndices;
    String indices;
    String paramsymbol;
    String excludeExchanges;
    public String direction;
    private String buttoneast;
    private String buttonwest;
    private String iSymbol;
    String tsymb;
    String dow;
    String baseDataUrl;
    String dataUrl;
    DecimalFormat df;
    Hashtable symbolQuotes;
    Hashtable indicesQuotes;
    String mode;
    private String links;
    private SnapQuote quotePage;
    String wmid;
    String targetsym;
    boolean localTest;
    private Image rt;
    private Image eod;
    private Image pd;
    private String displayCurrency;
    private HashMap allIndicesMap;
    
    public twoRowTicker() {
        this.symbol = "";
        this.textfontsize = 9;
        this.buttonfontsize = 9;
        this.datafontsize = 9;
        this.indexfontsize = 9;
        this.addurl = null;
        this.bgtop = Color.white;
        this.fgtop = Color.black;
        this.bgbottom = Color.decode("#836FFF");
        this.fgbottom = Color.white;
        this.bgeast = Color.black;
        this.bgwest = Color.black;
        this.bgbtn = Color.decode("#CCCCCC");
        this.fgbtn = Color.black;
        this.fontsize = 10;
        this.allIndices = new String[] { "^DJI", "^DJT", "^COMPX", "^NDX", "^NYA", "^RUO", "^SPX", ".SP100", ".SP400", ".SP600", "^TSX", "^CND", "COMP", ".SP500", "NYA", "RLG", "RUO", "IT.TSXCII", "^DKU", "^0000:CA", "^JX:CA" };
        this.defaultIndices = "^DJI,^COMPX,^SPX,^NYA,^DJT,^TSX,^CND";
        this.indices = "";
        this.paramsymbol = "IBM,MSFT,DELL,YHOO,AMZN,AAPL,INTC,RHAT,DIS,QMCI,SMTR";
        this.direction = "LEFT";
        this.buttoneast = "YES";
        this.buttonwest = "YES";
        this.iSymbol = "";
        this.tsymb = "";
        this.baseDataUrl = "";
        this.dataUrl = "";
        this.df = new DecimalFormat("#,###,##0.00##");
        this.symbolQuotes = new Hashtable();
        this.indicesQuotes = new Hashtable();
        this.mode = "name";
        this.links = "on";
        this.quotePage = null;
        this.wmid = null;
        this.targetsym = "off";
        this.localTest = false;
        this.rt = null;
        this.eod = null;
        this.pd = null;
        this.displayCurrency = "false";
        this.allIndicesMap = new HashMap();
    }
    
    public void init() {
        this.homeUrl = this.getCodeBase();
        this.docUrl = this.getDocumentBase();
        if (this.localTest) {
            this.baseUrl = "http://dev.quotemedia.com/";
        }
        else {
            this.baseUrl = this.homeUrl.getProtocol() + "://" + this.homeUrl.getHost() + "/";
        }
        if (this.getParameter("links") != null) {
            this.links = this.getParameter("links");
        }
        if (this.getParameter("indices") != null) {
            this.indices = this.getParameter("indices");
        }
        else {
            this.indices = this.defaultIndices;
        }
        this.baseDataUrl = this.baseUrl + "quotetools/getTickerQuotes.csv?host=" + this.docUrl.getHost() + "&";
        if (this.getParameter("webmasterId") != null) {
            this.wmid = this.getParameter("webmasterId");
            this.baseDataUrl = this.baseDataUrl + "webmasterId=" + this.wmid + "&";
        }
        if (this.getParameter("targetsym") != null) {
            this.targetsym = this.getParameter("targetsym");
        }
        if (this.getParameter("dispMode") != null) {
            this.mode = this.getParameter("dispMode");
        }
        if (this.getParameter("symbolsfontsize") != null) {
            this.fontsize = new Integer(this.getParameter("symbolsfontsize"));
        }
        boolean cp = true;
        if (this.getParameter("disclaimer") != null && this.getParameter("disclaimer").equalsIgnoreCase("off")) {
            cp = false;
        }
        boolean boldSymbol = false;
        if (this.getParameter("boldsymbol") != null && this.getParameter("boldsymbol").equalsIgnoreCase("yes")) {
            boldSymbol = true;
        }
        this.eq = new twoRowHandle(this, this.fontsize, this.getSize().height / 2, this.getSize().width, boldSymbol, cp);
        if (this.getParameter("indicesfontsize") != null) {
            this.fontsize = new Integer(this.getParameter("indicesfontsize"));
        }
        this.eq1 = new twoRowHandle(this, this.fontsize, this.getSize().height / 2, this.getSize().width, boldSymbol, cp);
        this.eq.setDisplayMode(this.mode);
        this.eq1.setDisplayMode(this.mode);
        this.displayCurrency = ((this.getParameter("showCurrency") != null) ? this.getParameter("showCurrency") : "false");
        if (this.getParameter("speedtop") != null) {
            this.eq.delay = new Integer(this.getParameter("speedtop"));
        }
        if (this.getParameter("speedbottom") != null) {
            this.eq1.delay = new Integer(this.getParameter("speedbottom"));
        }
        if (this.getParameter("uparrowcolor") != null) {
            this.eq.qup = Color.decode(this.getParameter("uparrowcolor"));
            this.eq1.qup = Color.decode(this.getParameter("uparrowcolor"));
        }
        if (this.getParameter("downarrowcolor") != null) {
            this.eq.qdown = Color.decode(this.getParameter("downarrowcolor"));
            this.eq1.qdown = Color.decode(this.getParameter("downarrowcolor"));
        }
        if (this.getParameter("symbols") != null) {
            this.paramsymbol = this.getParameter("symbols").toUpperCase();
        }
        if (this.getParameter("excludeExchanges") != null) {
            this.excludeExchanges = this.getParameter("excludeExchanges").toUpperCase();
        }
        if (this.getParameter("direction") != null) {
            this.direction = this.getParameter("direction");
            this.eq.direction = this.direction.toUpperCase();
            this.eq1.direction = this.direction.toUpperCase();
        }
        if (this.getParameter("topHighlight") != null) {
            this.eq.highlight = Color.decode(this.getParameter("topHighlight"));
        }
        else if (this.getParameter("highlightcolor") != null) {
            this.eq.highlight = Color.decode(this.getParameter("highlightcolor"));
        }
        if (this.getParameter("bottomHighlight") != null) {
            this.eq1.highlight = Color.decode(this.getParameter("bottomHighlight"));
        }
        else if (this.getParameter("highlightcolor") != null) {
            this.eq1.highlight = Color.decode(this.getParameter("highlightcolor"));
        }
        if (this.getParameter("bgtop") != null) {
            this.bgtop = Color.decode(this.getParameter("bgtop"));
        }
        if (this.getParameter("fgtop") != null) {
            this.fgtop = Color.decode(this.getParameter("fgtop"));
        }
        if (this.getParameter("bgbottom") != null) {
            this.bgbottom = Color.decode(this.getParameter("bgbottom"));
        }
        if (this.getParameter("fgbottom") != null) {
            this.fgbottom = Color.decode(this.getParameter("fgbottom"));
        }
        if (this.getParameter("bgeast") != null) {
            this.bgeast = Color.decode(this.getParameter("bgeast"));
        }
        if (this.getParameter("bgwest") != null) {
            this.bgwest = Color.decode(this.getParameter("bgwest"));
        }
        if (this.getParameter("bgbtn") != null) {
            this.bgbtn = Color.decode(this.getParameter("bgbtn"));
        }
        if (this.getParameter("fgbtn") != null) {
            this.fgbtn = Color.decode(this.getParameter("fgbtn"));
        }
        this.eq.bg = this.bgtop;
        this.eq.fg = this.fgtop;
        this.eq1.bg = this.bgbottom;
        this.eq1.fg = this.fgbottom;
        this.eq1.setBackground(this.bgbottom);
        this.eq.setBackground(this.bgtop);
        this.setLayout(new BorderLayout());
        try {
            final URL imgUrl = new URL(this.baseUrl + "quotetools/applet/images/");
            this.rt = this.getImage(imgUrl, "rl.gif");
            this.eod = this.getImage(imgUrl, "ed.gif");
            this.pd = this.getImage(imgUrl, "pd.gif");
            final MediaTracker md = new MediaTracker(this);
            md.addImage(this.rt, 1);
            md.addImage(this.eod, 2);
            md.addImage(this.pd, 3);
            md.waitForAll();
            this.eq.rt = this.rt;
            this.eq.eod = this.eod;
            this.eq.pd = this.pd;
            this.eq1.rt = this.rt;
            this.eq1.eod = this.eod;
            this.eq1.pd = this.pd;
        }
        catch (Exception ex) {}
        if (this.getParameter("eastbutton") != null) {
            this.buttoneast = this.getParameter("eastbutton");
        }
        if (this.getParameter("westbutton") != null) {
            this.buttonwest = this.getParameter("westbutton");
        }
        if (this.getParameter("buttonfontsize") != null) {
            this.fontsize = new Integer(this.getParameter("buttonfontsize"));
        }
        this.eq.setSize(this.getSize().width - 100, 16);
        this.eq1.setSize(this.getSize().width - 100, 16);
        this.eq1.marketcanvas = true;
        if (this.buttoneast.equalsIgnoreCase("YES")) {
            (this.refresh = new Button("Refresh")).setBackground(this.bgbtn);
            this.refresh.setForeground(this.fgbtn);
            this.refresh.addActionListener(this);
            this.refresh.setFont(new Font("Helvetica", 0, this.fontsize));
            (this.edit = new Button("Edit")).setBackground(this.bgbtn);
            this.edit.setForeground(this.fgbtn);
            this.edit.addActionListener(this);
            this.edit.setFont(new Font("Helvetica", 0, this.fontsize));
            final Panel panel0 = new Panel();
            panel0.setLayout(new GridLayout(2, 1));
            panel0.add(this.refresh);
            panel0.add(this.edit);
            final Panel controls = new Panel();
            controls.setLayout(new BorderLayout());
            controls.add("East", panel0);
            controls.setBackground(this.bgeast);
            this.add("East", controls);
        }
        final Panel center = new Panel();
        center.setLayout(new GridLayout(2, 1));
        center.add(this.eq);
        center.add(this.eq1);
        this.add("Center", center);
        if (this.buttonwest.equalsIgnoreCase("YES")) {
            (this.lefttoright = new Button(">")).setBackground(this.bgbtn);
            this.lefttoright.setForeground(this.fgbtn);
            this.lefttoright.addActionListener(this);
            this.lefttoright.setFont(new Font("Helvetica", 1, this.fontsize + 1));
            (this.righttoleft = new Button("<")).setBackground(this.bgbtn);
            this.righttoleft.setForeground(this.fgbtn);
            this.righttoleft.addActionListener(this);
            this.righttoleft.setFont(new Font("Helvetica", 1, this.fontsize + 1));
            final Panel west = new Panel();
            west.setLayout(new GridLayout(2, 1));
            west.add(this.righttoleft);
            west.add(this.lefttoright);
            this.add("West", west);
        }
        this.eq.addMouseListener(this);
        this.eq.addMouseMotionListener(this);
        this.eq1.addMouseListener(this);
        this.eq1.addMouseMotionListener(this);
        System.out.println("TwoRowTicker Version 3.1.2");
        for (int j = 0; j < this.allIndices.length; ++j) {
            this.allIndicesMap.put(this.allIndices[j], this.allIndices[j]);
        }
    }
    
    public void openLookup() {
        final Dimension sSize = this.getToolkit().getScreenSize();
        final String tleft = String.valueOf((sSize.width - 400) / 2);
        final String mtop = String.valueOf((sSize.height - 300) / 2);
        final StringBuffer stbuffer = new StringBuffer();
        if (this.excludeExchanges != null) {
            final StringTokenizer tok = new StringTokenizer(this.excludeExchanges, ",");
            final Vector excludeExchangesVector = new Vector();
            while (tok.hasMoreTokens()) {
                final String tsy = tok.nextToken();
                excludeExchangesVector.addElement(tsy);
                stbuffer.append("excludeExchanges=").append(tsy).append("&");
            }
        }
        final String tmpurl = this.baseUrl + "quotetools/symbolLookup.jsp?" + stbuffer.toString() + "webmasterId=" + this.wmid;
        final String property = "width=450,height=300,left=" + tleft + ",top=" + mtop + ",resizable=1,menubar=no,toolbar=no,location=no,scrollbars=yes,statusbar=yes,status=yes";
        boolean runJSObject = true;
        final String osname = System.getProperty("os.name");
        if (osname.indexOf("Linux") >= 0 || osname.indexOf("Mac") >= 0) {
            runJSObject = false;
        }
        if (!runJSObject) {
            this.showBrowser(tmpurl, "_blank");
        }
        else {
            final String s = "window.open('" + tmpurl + "','Lookup','" + property + " ')";
            boolean jsfound = true;
            JSObject win = null;
            try {
                win = JSObject.getWindow((Applet)this);
                win.eval(s);
            }
            catch (Exception ex) {
                jsfound = false;
            }
            if (!jsfound || win == null) {
                this.showBrowser(tmpurl, "_blank");
            }
        }
    }
    
    public String getCookie() {
        String s = "";
        String str = null;
        try {
            final URL url1 = new URL(this.baseUrl + "quotetools/cookiehandler/cookieload.jsp?cookiename=up&host=" + this.docUrl.getHost() + "&webmasterId=" + this.wmid);
            final URLConnection urlconnection1 = url1.openConnection();
            DataInputStream datainputstream1 = null;
            int i = 0;
            urlconnection1.setDoOutput(true);
            urlconnection1.setDoInput(true);
            urlconnection1.setUseCaches(false);
            datainputstream1 = new DataInputStream(urlconnection1.getInputStream());
            i = 0;
            while ((str = datainputstream1.readLine()) != null && !str.startsWith("</cookiedata>")) {
                if (i != 0) {
                    s = String.valueOf(s) + str;
                }
                else {
                    if (!str.startsWith("<cookiedata>")) {
                        continue;
                    }
                    i = 1;
                }
            }
            datainputstream1.close();
            if (s.indexOf("%2C") > 0) {
                while (this.isTwoC(s)) {
                    final int cndx = s.indexOf("%2C");
                    if (cndx > 0) {
                        s = s.substring(0, cndx) + "," + s.substring(cndx + 3);
                    }
                }
            }
            this.symbol = s;
        }
        catch (Exception exception1) {
            this.symbol = null;
        }
        return this.symbol;
    }
    
    public boolean isTwoC(final String str) {
        return str.indexOf("%2C") > 0;
    }
    
    public URL getURL(final String u) {
        URL r = null;
        try {
            r = new URL(u);
        }
        catch (Exception ex) {}
        return r;
    }
    
    public void showBrowser(final String purl, final String target) {
        try {
            final URL pURL = this.getURL(purl);
            this.getAppletContext().showDocument(pURL, target);
        }
        catch (Exception expt) {
            expt.printStackTrace();
        }
    }
    
    public void setCookie(final String symbols) {
        String s = "";
        final String tsym = "\"" + symbols + "\"";
        try {
            final URL url1 = new URL(this.baseUrl + "quotetools/cookiehandler/cookiesave.jsp?cookiedata=up&cookievalue=" + URLEncoder.encode(tsym) + "&host=" + this.docUrl.getHost() + "&webmasterId=" + this.wmid);
            final URLConnection urlconnection1 = url1.openConnection();
            DataInputStream datainputstream1 = null;
            urlconnection1.setDoOutput(true);
            urlconnection1.setDoInput(true);
            urlconnection1.setUseCaches(true);
            datainputstream1 = new DataInputStream(urlconnection1.getInputStream());
            while ((s = datainputstream1.readLine()) != null) {}
            datainputstream1.close();
        }
        catch (Exception ex) {}
    }
    
    public void showSymbolDetail(final String sym) {
        if (this.links == null || this.links.equals("on")) {
            final boolean openWindow = true;
            final Dimension sSize = this.getToolkit().getScreenSize();
            final String tleft = String.valueOf((sSize.width - 530) / 2);
            final String mtop = String.valueOf((sSize.height - 270) / 2);
            final String url = "";
            if (this.getParameter("detailURL") != null) {
                String appender = "?";
                if (this.getParameter("detailURL").indexOf("?") > -1) {
                    appender = "&";
                }
                final StringBuffer stb = new StringBuffer().append("http://").append(this.getCodeBase().getHost()).append("/quotetools/clientForward?targetURL=").append(URLEncoder.encode(this.getParameter("detailURL")));
                if (this.targetsym.equalsIgnoreCase("on") || this.targetsym.equalsIgnoreCase("true")) {
                    stb.append(appender).append("qm_symbol=").append(sym).append("&symbol=").append(sym);
                }
                if (this.getParameter("detailPop") != null && this.getParameter("detailPop").equalsIgnoreCase("on")) {
                    this.openPopup(stb.toString());
                }
                else {
                    this.showBrowser(stb.toString(), "_self");
                }
            }
            else {
                if (this.quotePage == null) {
                    this.quotePage = new SnapQuote(sym, this, this.wmid);
                    this.quotePage.qp.rt = this.rt;
                    this.quotePage.qp.pd = this.pd;
                    this.quotePage.qp.eod = this.eod;
                    this.quotePage.setSize(530, 285);
                }
                else {
                    this.quotePage.setSymbol(sym);
                }
                this.quotePage.show();
            }
        }
    }
    
    public void openPopup(final String url) {
        boolean openWindow = true;
        final Dimension sSize = this.getToolkit().getScreenSize();
        final String tleft = String.valueOf((sSize.width - 530) / 2);
        final String mtop = String.valueOf((sSize.height - 270) / 2);
        final String property = "width=540,height=280,left=" + tleft + ",top=" + mtop + ",resizable=1,menubar=no,toolbar=no,location=no,scrollbars=no,statusbar=no,status=no";
        final String osname = System.getProperty("os.name");
        if (osname.indexOf("Linux") >= 0 || osname.indexOf("Mac") >= 0) {
            openWindow = false;
        }
        if (!openWindow) {
            this.showBrowser(url, "_blank");
        }
        else {
            final String s = "window.open('" + url + "','Quote','" + property + " ')";
            boolean jsfound = true;
            JSObject win = null;
            try {
                win = JSObject.getWindow((Applet)this);
                win.eval(s);
            }
            catch (Exception ex) {
                jsfound = false;
            }
            if (!jsfound || win == null) {
                this.showBrowser(url, "_blank");
            }
        }
    }
    
    public void setSymbol(final String stocks, final String tindices) {
        this.eq.stop();
        this.eq1.stop();
        if (stocks != null && !stocks.equals("")) {
            this.tsymb = stocks;
        }
        else {
            this.tsymb = this.paramsymbol;
        }
        if (tindices != null && !tindices.equals("")) {
            this.indices = tindices;
        }
        else {
            this.indices = this.defaultIndices;
        }
        this.iSymbol = this.tsymb + "," + this.indices;
        final StringTokenizer tok = new StringTokenizer(this.iSymbol, ",");
        final Vector indicesVector = new Vector();
        final Vector symbolVector = new Vector();
        final StringBuffer stbuffer = new StringBuffer();
        while (tok.hasMoreTokens()) {
            final String tsy = tok.nextToken();
            if (this.allIndicesMap.containsKey(tsy)) {
                indicesVector.addElement(tsy);
            }
            else {
                symbolVector.addElement(tsy);
            }
            stbuffer.append("symbols=").append(tsy).append("&");
        }
        this.eq.setSymbolList(symbolVector);
        this.eq1.setSymbolList(indicesVector);
        this.dataUrl = this.baseDataUrl + stbuffer.toString();
        this.getQuoteFromServer();
        this.eq.setData(this.symbolQuotes);
        this.eq1.setData(this.indicesQuotes);
        this.eq.start();
        this.eq1.start();
    }
    
    public void start() {
        this.hframe = new addsymbolframe(this.paramsymbol, this);
        if (!this.buttoneast.equalsIgnoreCase("YES")) {
            this.tsymb = this.paramsymbol;
            this.iSymbol = this.tsymb + "," + this.indices;
        }
        else {
            this.iSymbol = this.getCookie();
            if (this.iSymbol != null && this.iSymbol.length() > 0) {
                this.tsymb = this.hframe.splitTwoRowSymbols(this.iSymbol, true);
                this.indices = this.hframe.splitTwoRowSymbols(this.iSymbol, false);
            }
            else {
                this.tsymb = this.paramsymbol;
                this.iSymbol = this.tsymb + "," + this.indices;
            }
        }
        if (this.indices == null || this.indices.length() <= 1) {
            this.indices = this.defaultIndices;
        }
        this.hframe.setup(this.tsymb + "," + this.indices);
        this.setSymbol(this.tsymb, this.indices);
    }
    
    public void stop() {
        this.eq.stop();
        this.eq1.stop();
    }
    
    public void destroy() {
        this.eq.stop();
        this.eq.destroy();
        this.eq1.stop();
        this.eq1.destroy();
        this.removeMouseListener(this);
        this.removeMouseMotionListener(this);
    }
    
    public void paint(final Graphics g) {
        super.paint(g);
    }
    
    public void dorepainting() {
        this.repaint();
        this.eq.repaint();
    }
    
    public void actionPerformed(final ActionEvent ev) {
        final Object obj = ev.getSource();
        if (obj == this.refresh) {
            this.eq.refreshData();
            this.eq1.refreshData();
        }
        else if (obj == this.edit) {
            if (this.hframe == null) {
                (this.hframe = new addsymbolframe(this.iSymbol, this)).setup(this.tsymb + "," + this.indices);
            }
            this.hframe.show();
        }
        else if (obj == this.lefttoright) {
            this.eq.setDirection("RIGHT");
            this.eq1.setDirection("RIGHT");
        }
        else if (obj == this.righttoleft) {
            this.eq.setDirection("LEFT");
            this.eq1.setDirection("LEFT");
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void getQuoteFromServer() {
        String strResponse = "";
        BufferedReader ds = null;
        final Hashtable indQuotes = new Hashtable();
        final Hashtable symQuotes = new Hashtable();
        try {
            final URL url = new URL(this.dataUrl);
            final URLConnection connection = url.openConnection();
            int i = 0;
            connection.setRequestProperty("Accept-Encoding", "gzip");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            InputStream rs = null;
            if (connection.getContentEncoding() != null && connection.getContentEncoding().indexOf("gzip") > -1) {
                rs = new GZIPInputStream(connection.getInputStream());
            }
            else {
                rs = connection.getInputStream();
            }
            int j = 0;
            if (rs != null) {
                ds = new BufferedReader(new InputStreamReader(rs));
                while ((strResponse = ds.readLine()) != null) {
                    if (strResponse != null && strResponse.length() > 5) {
                        final String[] strRow = this.getColumns(strResponse, 8);
                        if (strRow != null) {
                            final String[] strLine = new String[6];
                            strLine[0] = strRow[0];
                            if (strRow[1].trim().equalsIgnoreCase("n/a")) {
                                strLine[1] = strRow[0].toUpperCase();
                            }
                            else {
                                strLine[1] = strRow[1];
                            }
                            if (this.mode.equals("symbol")) {
                                strLine[1] = strRow[0].toUpperCase();
                            }
                            try {
                                if (this.displayCurrency.equalsIgnoreCase("true") && strRow[7] != null) {
                                    strLine[1] = strLine[1] + " (" + strRow[7] + ") ";
                                }
                            }
                            catch (Exception ex) {}
                            if (!strRow[2].trim().equalsIgnoreCase("n/a")) {
                                try {
                                    final float fv1 = Float.valueOf(strRow[2]);
                                    strLine[2] = String.valueOf(this.df.format(fv1)).trim();
                                }
                                catch (Exception exp) {
                                    strLine[2] = "N/A";
                                }
                            }
                            else {
                                strLine[2] = "N/A";
                            }
                            float fv2 = 0.0f;
                            if (!strRow[3].trim().equalsIgnoreCase("n/a")) {
                                try {
                                    fv2 = Float.valueOf(strRow[3]);
                                    strLine[3] = String.valueOf(this.df.format(fv2)).trim();
                                }
                                catch (Exception exp2) {
                                    strLine[3] = "N/A";
                                }
                            }
                            else {
                                strLine[3] = "N/A";
                            }
                            float fv3 = 0.0f;
                            if (!strRow[4].trim().equalsIgnoreCase("n/a")) {
                                try {
                                    fv3 = Float.valueOf(strRow[4]);
                                    strLine[4] = String.valueOf(this.df.format(fv3)).trim();
                                }
                                catch (Exception exp3) {
                                    strLine[4] = "N/A";
                                }
                            }
                            else {
                                strLine[4] = "N/A";
                            }
                            if (fv2 > 0.0f) {
                                strLine[3] = "+" + strLine[3];
                            }
                            if (fv3 > 0.0f) {
                                strLine[4] = "+" + strLine[4];
                            }
                            if (strLine[4].indexOf("N/A") < 0) {
                                final StringBuffer sb = new StringBuffer();
                                final String[] array = strLine;
                                final int n = 4;
                                array[n] = sb.append(array[n]).append("%").toString();
                            }
                            strLine[5] = strRow[6].trim();
                            if (this.allIndicesMap.containsKey(strLine[0])) {
                                indQuotes.put(strRow[0].trim(), strLine);
                            }
                            else {
                                symQuotes.put(strRow[0].trim(), strLine);
                            }
                            ++i;
                        }
                    }
                    ++j;
                }
            }
            ds.close();
            this.indicesQuotes = indQuotes;
            this.symbolQuotes = symQuotes;
        }
        catch (Exception exception1) {
            System.out.println("twoRowTicker: " + exception1.toString());
        }
    }
    
    public String[] getColumns(final String line, final int cols) {
        try {
            int i = 0;
            final String[] str = new String[cols];
            if (line.length() > 3) {
                for (StringTokenizer stk = new StringTokenizer(line, ","); stk.hasMoreTokens() && i < cols; ++i) {
                    final String strTmp = stk.nextToken();
                    if (strTmp != null) {
                        str[i] = strTmp;
                    }
                    else {
                        str[i] = "";
                    }
                }
            }
            return str;
        }
        catch (Exception exception1) {
            return null;
        }
    }
    
    public void mouseClicked(final MouseEvent e) {
    }
    
    public void mouseEntered(final MouseEvent e) {
        this.setCursor(new Cursor(12));
        final int mouseX = e.getX();
        final int mouseY = e.getY();
        if (e.getSource() == this.eq) {
            this.eq.StopScrolling(mouseX, mouseY);
        }
        else if (e.getSource() == this.eq1) {
            this.eq1.StopScrolling(mouseX, mouseY);
        }
    }
    
    public void mouseExited(final MouseEvent e) {
        this.setCursor(new Cursor(0));
        final int mouseX = 0;
        final int mouseY = 0;
        if (e.getSource() == this.eq) {
            this.eq.StartScrolling(mouseX, mouseY);
        }
        else if (e.getSource() == this.eq1) {
            this.eq1.StartScrolling(mouseX, mouseY);
        }
    }
    
    public void mousePressed(final MouseEvent e) {
    }
    
    public void mouseReleased(final MouseEvent e) {
        String symbol = null;
        if (e.getSource() == this.eq) {
            symbol = this.eq.getCurrentSymbol();
        }
        else if (e.getSource() == this.eq1) {
            symbol = this.eq1.getCurrentSymbol();
        }
        if (symbol == null || symbol.trim().equals("") || symbol.equals("N/A")) {
            this.showBrowser("http://www.quotemedia.com", "_self");
        }
        else {
            this.showSymbolDetail(symbol);
        }
    }
    
    public void mouseDragged(final MouseEvent e) {
    }
    
    public void mouseMoved(final MouseEvent e) {
        final int mouseX = e.getX();
        final int mouseY = e.getY();
        if (e.getSource() == this.eq) {
            this.eq.RepaintTicker(mouseX, mouseY);
        }
        else if (e.getSource() == this.eq1) {
            this.eq1.RepaintTicker(mouseX, mouseY);
        }
    }
}
