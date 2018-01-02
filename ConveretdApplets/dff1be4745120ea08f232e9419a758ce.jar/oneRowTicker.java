import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.Vector;
import java.net.URLConnection;
import java.io.DataInputStream;
import java.awt.Dimension;
import netscape.javascript.JSObject;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;
import com.quotemedia.awt.SnapQuote;
import java.awt.Color;
import java.awt.Button;
import java.awt.Image;
import java.awt.Graphics;
import java.net.URL;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class oneRowTicker extends Applet implements ActionListener, useHandleInterface
{
    private String symbol;
    URL docUrl;
    URL homeUrl;
    String baseUrl;
    Graphics backgc;
    Image backBuffer;
    oneRowHandle eq;
    addsymbolframe hframe;
    Button edit;
    Button lefttoright;
    Button righttoleft;
    Color bg;
    Color fg;
    Color bgbottom;
    Color fgbottom;
    Color bgeast;
    Color bgwest;
    Color bgbtn;
    Color fgbtn;
    String paramsymbol;
    public String direction;
    private String buttoneast;
    private String buttonwest;
    private String iSymbol;
    private String fontName;
    private String links;
    private SnapQuote quotePage;
    int fontsize;
    String wmid;
    String targetsym;
    private boolean localTest;
    private Image rt;
    private Image eod;
    private Image pd;
    
    public oneRowTicker() {
        this.symbol = "";
        this.bg = Color.white;
        this.fg = Color.black;
        this.bgbottom = Color.decode("#836FFF");
        this.fgbottom = Color.white;
        this.bgeast = Color.black;
        this.bgwest = Color.black;
        this.bgbtn = Color.decode("#BDBDBD");
        this.fgbtn = Color.black;
        this.paramsymbol = "IBM,MSFT,DELL,YHOO,AMZN,AAPL,INTC,RHAT,DIS,QMCI,SMTR";
        this.direction = "LEFT";
        this.buttoneast = "YES";
        this.buttonwest = "YES";
        this.iSymbol = "";
        this.fontName = "Helvetica";
        this.links = "on";
        this.quotePage = null;
        this.fontsize = 10;
        this.wmid = null;
        this.targetsym = "off";
        this.localTest = false;
        this.rt = null;
        this.eod = null;
        this.pd = null;
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
        if (this.getParameter("symbolsfontsize") != null) {
            try {
                this.fontsize = new Integer(this.getParameter("symbolsfontsize"));
            }
            catch (Exception ex) {}
        }
        if (this.getParameter("font") != null) {
            this.fontName = this.getParameter("font");
        }
        boolean cp = true;
        if (this.getParameter("disclaimer") != null && this.getParameter("disclaimer").equalsIgnoreCase("off")) {
            cp = false;
        }
        int symface = 0;
        if (this.getParameter("targetsym") != null) {
            this.targetsym = this.getParameter("targetsym");
        }
        if (this.getParameter("boldsymbol") != null && (this.getParameter("boldsymbol").equalsIgnoreCase("yes") || this.getParameter("boldsymbol").equalsIgnoreCase("on"))) {
            symface = 1;
        }
        Font dataFont;
        Font symbolFont;
        try {
            dataFont = new Font(this.fontName, 0, this.fontsize);
            symbolFont = new Font(this.fontName, symface, this.fontsize);
        }
        catch (Exception expp) {
            dataFont = new Font("Helvetica", 0, 10);
            symbolFont = new Font("Helvetica", 1, 11);
        }
        boolean verticalLine = false;
        if (this.getParameter("lines") != null && (this.getParameter("lines").equalsIgnoreCase("yes") || this.getParameter("lines").equalsIgnoreCase("on"))) {
            verticalLine = true;
        }
        if (this.getParameter("links") != null) {
            this.links = this.getParameter("links");
        }
        this.eq = new oneRowHandle(this, symbolFont, dataFont, this.getSize().height, this.getSize().width, cp, verticalLine);
        if (this.getParameter("webmasterId") != null) {
            this.wmid = this.getParameter("webmasterId");
        }
        this.eq.qg.setBaseUrl(this.baseUrl, this.wmid, this.docUrl.getHost().toString());
        final String displayCurrency = (this.getParameter("showCurrency") != null) ? this.getParameter("showCurrency") : "false";
        this.eq.qg.setDisplayCurrency(displayCurrency);
        if (this.getParameter("dispMode") != null) {
            this.eq.setDisplayMode(this.getParameter("dispMode"));
        }
        if (this.getParameter("speed") != null) {
            this.eq.delay = new Integer(this.getParameter("speed"));
        }
        if (this.getParameter("uparrowcolor") != null) {
            this.eq.qup = Color.decode(this.getParameter("uparrowcolor"));
        }
        if (this.getParameter("downarrowcolor") != null) {
            this.eq.qdown = Color.decode(this.getParameter("downarrowcolor"));
        }
        if (this.getParameter("symbols") != null) {
            this.paramsymbol = this.getParameter("symbols").toUpperCase();
        }
        if (this.getParameter("direction") != null) {
            this.direction = this.getParameter("direction");
            this.eq.direction = this.direction;
        }
        if (this.getParameter("highlightcolor") != null) {
            this.eq.highlight = Color.decode(this.getParameter("highlightcolor"));
        }
        if (this.getParameter("bg") != null) {
            this.bg = Color.decode(this.getParameter("bg"));
        }
        if (this.getParameter("fg") != null) {
            this.fg = Color.decode(this.getParameter("fg"));
        }
        if (this.getParameter("changecolorup") != null) {
            this.eq.changecolorup = Color.decode(this.getParameter("changecolorup"));
        }
        else {
            this.eq.changecolorup = this.fg;
        }
        if (this.getParameter("changecolordown") != null) {
            this.eq.changecolordown = Color.decode(this.getParameter("changecolordown"));
        }
        else {
            this.eq.changecolordown = this.fg;
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
        this.eq.bg = this.bg;
        this.eq.fg = this.fg;
        this.eq.setBackground(this.bg);
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
        }
        catch (Exception ex2) {}
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
        if (this.buttoneast.equalsIgnoreCase("YES") || this.buttoneast.equalsIgnoreCase("ON")) {
            (this.edit = new Button("Edit")).setBackground(this.bgbtn);
            this.edit.setForeground(this.fgbtn);
            this.edit.addActionListener(this);
            this.edit.setFont(new Font("Helvetica", 0, this.fontsize));
            final Panel controls = new Panel();
            controls.setLayout(new BorderLayout());
            controls.add("East", this.edit);
            controls.setBackground(this.bgeast);
            this.add("East", controls);
        }
        this.add("Center", this.eq);
        if (this.buttonwest.equalsIgnoreCase("YES") || this.buttonwest.equalsIgnoreCase("ON")) {
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
    }
    
    public void openLookup() {
        final Dimension sSize = this.getToolkit().getScreenSize();
        final String tleft = String.valueOf((sSize.width - 400) / 2);
        final String mtop = String.valueOf((sSize.height - 300) / 2);
        final String tmpurl = this.baseUrl + "quotetools/symbolLookup.jsp?";
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
    
    public Vector getDataVector() {
        return this.eq.getData();
    }
    
    public void showSymbolDetail(final String sym) {
        if (this.links == null || this.links.equals("on")) {
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
    
    public void showBrowser(final String urlname, final String target) {
        URL pageURL = null;
        try {
            pageURL = new URL(urlname);
        }
        catch (MalformedURLException ex) {}
        this.getAppletContext().showDocument(pageURL, target);
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
    
    public void setSymbol(final String ssm) {
        this.symbol = ssm;
        if (this.symbol == null || this.symbol.equals("") || this.symbol.trim().length() < 1) {
            this.symbol = this.paramsymbol;
        }
        this.eq.setSymbol(this.symbol);
    }
    
    public void start() {
        if (!this.buttoneast.equalsIgnoreCase("YES")) {
            this.iSymbol = this.paramsymbol;
        }
        else {
            final String iSymbol1 = this.getCookie();
            if (iSymbol1 != null && iSymbol1.trim().length() > 0) {
                this.iSymbol = iSymbol1;
            }
            else {
                this.iSymbol = this.paramsymbol;
            }
        }
        this.eq.setSymbol(this.iSymbol);
        this.eq.start();
    }
    
    public void stop() {
        this.eq.stop();
    }
    
    public void destroy() {
        this.eq.stop();
        this.eq.destroy();
    }
    
    public void paint(final Graphics g) {
        super.paint(g);
    }
    
    public void dorepainting() {
        this.repaint();
        this.eq.repaint();
    }
    
    public void reloaddata() {
        this.eq.quoteList = null;
        this.eq.startThread();
    }
    
    public void getQuoteFromServer() {
        this.eq.offImage = null;
        this.eq.repaint();
        this.eq.stopThread();
        this.eq.updateData();
    }
    
    public void actionPerformed(final ActionEvent ev) {
        final Object obj = ev.getSource();
        if (obj == this.edit) {
            if (this.hframe == null) {
                this.hframe = new addsymbolframe(this.iSymbol, this);
            }
            this.hframe.show();
        }
        else if (obj == this.lefttoright) {
            this.eq.setDirection("RIGHT");
        }
        else if (obj == this.righttoleft) {
            this.eq.setDirection("LEFT");
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
}
