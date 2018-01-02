import java.net.MalformedURLException;
import netscape.javascript.JSObject;
import java.net.URLEncoder;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.net.URLConnection;
import java.io.DataInputStream;
import java.util.Enumeration;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.util.Vector;
import java.awt.Image;
import java.awt.Graphics;
import java.net.URL;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class hnews extends Applet implements MouseListener, MouseMotionListener, Runnable
{
    URL docUrl;
    URL homeUrl;
    String baseUrl;
    Graphics backgc;
    Image backBuffer;
    int newsindex;
    Thread noticeThread;
    int delay;
    int mouseX;
    int mouseY;
    String newsstring;
    String heading;
    Vector newsvector;
    Vector urllist;
    Vector drawnewsVector;
    int maxAscent;
    int maxDescent;
    Font dfont;
    FontMetrics fm;
    private boolean mouseinside;
    private boolean calculate;
    int maxhwidth;
    int totstrwidth;
    int available;
    int lengthofnews;
    int widthperchar;
    int maxcharperline;
    int newscounter;
    int wordcounter;
    int ypos;
    Color hbg;
    Color nbg;
    Color hcolor;
    Color ncolor;
    Color highlight;
    Color bordercolor;
    int fontsize;
    private String topics;
    private String wmid;
    private boolean header;
    private String newsParamURL;
    private String perTopic;
    private boolean showFullHeader;
    private boolean LOCAL_TEST;
    private String host;
    
    public hnews() {
        this.noticeThread = null;
        this.delay = 6;
        this.mouseY = 0;
        this.newsstring = "...loading";
        this.heading = "News";
        this.newsvector = null;
        this.urllist = new Vector();
        this.mouseinside = false;
        this.calculate = false;
        this.maxhwidth = 0;
        this.totstrwidth = 0;
        this.available = 0;
        this.lengthofnews = 0;
        this.widthperchar = 0;
        this.maxcharperline = 0;
        this.newscounter = 0;
        this.wordcounter = 0;
        this.hbg = Color.blue;
        this.nbg = Color.white;
        this.hcolor = Color.white;
        this.ncolor = Color.black;
        this.highlight = Color.decode("#FF0000");
        this.bordercolor = Color.gray;
        this.fontsize = 14;
        this.topics = "";
        this.header = true;
        this.perTopic = "5";
        this.showFullHeader = false;
        this.LOCAL_TEST = false;
        this.host = "";
    }
    
    public void init() {
        this.homeUrl = this.getCodeBase();
        this.docUrl = this.getDocumentBase();
        this.host = this.getDocumentBase().getHost();
        if (this.LOCAL_TEST) {
            this.baseUrl = "http://app.quotemedia.com/";
            this.wmid = "501";
        }
        else {
            this.baseUrl = this.homeUrl.getProtocol() + "://" + this.homeUrl.getHost() + "/";
        }
        if (this.getParameter("topics") != null) {
            this.topics = this.getParameter("topics");
            final StringTokenizer topicsTok = new StringTokenizer(this.topics, ",");
            final int ncat = Math.min(10, Math.max(0, topicsTok.countTokens()));
            String ttopics = "";
            int t;
            for (t = 0; topicsTok.hasMoreTokens() && t <= ncat; ++t) {
                final String strTmp = topicsTok.nextToken();
                ttopics = ttopics + "topic=" + strTmp + "&";
            }
            if (t > 0) {
                ttopics = ttopics.substring(0, ttopics.length() - 1);
                this.topics = ttopics;
            }
            else {
                this.topics = "topic=" + this.topics;
            }
        }
        else {
            this.topics = "topic=BUSINESS";
        }
        if (this.getParameter("fullHeader") != null && this.getParameter("fullHeader").equals("true")) {
            this.showFullHeader = true;
        }
        if (this.getParameter("perTopic") != null) {
            this.perTopic = this.getParameter("perTopic");
        }
        if (this.getParameter("webmasterId") != null) {
            this.wmid = this.getParameter("webmasterId");
        }
        if (this.getParameter("delay") != null) {
            try {
                this.delay = new Integer(this.getParameter("delay"));
            }
            catch (Exception exp) {
                this.delay = 6;
            }
        }
        this.delay *= 1000;
        if (this.getParameter("hbg") != null) {
            this.hbg = Color.decode(this.getParameter("hbg"));
        }
        if (this.getParameter("bg") != null) {
            this.nbg = Color.decode(this.getParameter("bg"));
        }
        if (this.getParameter("hfg") != null) {
            this.hcolor = Color.decode(this.getParameter("hfg"));
        }
        if (this.getParameter("fg") != null) {
            this.ncolor = Color.decode(this.getParameter("fg"));
        }
        if (this.getParameter("highlight") != null) {
            this.highlight = Color.decode(this.getParameter("highlight"));
        }
        if (this.getParameter("fontsize") != null) {
            this.fontsize = new Integer(this.getParameter("fontsize"));
        }
        if (this.getParameter("header") != null && this.getParameter("header").equalsIgnoreCase("off")) {
            this.header = false;
        }
        if (this.getParameter("newsURL") != null) {
            this.newsParamURL = this.getParameter("newsURL");
        }
        this.dfont = new Font("SanSerif", 0, this.fontsize);
        this.setBackground(this.hbg);
        this.fm = this.getFontMetrics(this.dfont);
        this.maxAscent = this.fm.getMaxAscent();
        this.maxDescent = this.fm.getMaxDescent();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        final Rectangle rectangle1 = this.getBounds();
        this.ypos = rectangle1.height;
        this.ypos -= this.fm.getAscent() + this.fm.getDescent();
        this.ypos /= 2;
        this.ypos += this.fm.getAscent();
        if (this.header) {
            this.maxhwidth = this.fm.stringWidth(this.heading + ":") + 17;
        }
    }
    
    public void start() {
        if (this.noticeThread == null) {
            this.noticeThread = new Thread(this);
        }
        this.noticeThread.start();
    }
    
    public void stop() {
        if (this.noticeThread != null) {
            this.noticeThread.stop();
            this.noticeThread = null;
        }
    }
    
    public void destroy() {
        if (this.noticeThread != null) {
            this.noticeThread.stop();
            this.noticeThread = null;
        }
    }
    
    public void paint(final Graphics g2) {
        if (this.backBuffer == null) {
            this.backBuffer = this.createImage(this.getSize().width, this.getSize().height);
        }
        (this.backgc = this.backBuffer.getGraphics()).setColor(this.getBackground());
        this.backgc.fillRect(0, 0, this.getSize().width, this.getSize().height);
        this.backgc.setFont(this.dfont);
        this.backgc.clearRect(0, 0, this.getSize().width, this.getSize().height);
        if (this.header) {
            this.backgc.setColor(this.hbg);
            this.backgc.fillRect(0, 0, this.maxhwidth, this.getSize().height);
        }
        if (this.mouseinside) {
            this.backgc.setColor(this.highlight);
        }
        else if (this.header) {
            this.backgc.setColor(this.hcolor);
        }
        if (this.header) {
            this.backgc.drawString(this.heading, 15, this.ypos);
        }
        this.backgc.setColor(this.nbg);
        this.backgc.fillRect(this.maxhwidth, 0, this.getSize().width, this.getSize().height);
        if (this.mouseinside) {
            this.backgc.setColor(this.highlight);
        }
        else {
            this.backgc.setColor(this.ncolor);
        }
        this.backgc.drawString(this.newsstring, this.maxhwidth + 2, this.ypos);
        g2.drawImage(this.backBuffer, 0, 0, this);
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    private void PopulateNews() {
        this.newsvector = this.getNewsURL();
        if (this.newsvector != null && !this.calculate) {
            this.drawnewsVector = new Vector();
            final Enumeration e = this.newsvector.elements();
            while (e.hasMoreElements()) {
                final String[] tnews = e.nextElement();
                final String[] tmpnews = { tnews[0], null };
                final String headingnews = tnews[1];
                final String calnews = tnews[1];
                if (this.header && this.fm.stringWidth(tnews[0] + ":") + 17 > this.maxhwidth) {
                    this.maxhwidth = this.fm.stringWidth(tnews[0] + ":") + 17;
                }
                int i = 0;
                if (calnews != null && headingnews != null) {
                    this.totstrwidth = this.fm.stringWidth(calnews);
                    this.available = this.getSize().width - this.maxhwidth - 2;
                    this.lengthofnews = calnews.length();
                    this.widthperchar = this.totstrwidth / this.lengthofnews;
                    this.maxcharperline = this.available / this.widthperchar;
                    this.newscounter = 0;
                    this.wordcounter = 0;
                    final int[] wordpos = new int[100];
                    if (this.fm.stringWidth(calnews) > this.available) {
                        while (i < calnews.length()) {
                            if (calnews.charAt(i) == ' ' && i > 0) {
                                wordpos[this.wordcounter] = i;
                                if (this.fm.stringWidth(calnews.substring(0, wordpos[this.wordcounter])) > this.available) {
                                    final int endIndex = (this.wordcounter >= 2) ? (this.wordcounter - 2) : 0;
                                    tmpnews[1] = calnews.substring(0, wordpos[endIndex]) + " ...";
                                    i = calnews.length();
                                    break;
                                }
                                ++this.wordcounter;
                            }
                            ++i;
                        }
                        if (tmpnews[1] == null) {
                            tmpnews[1] = calnews;
                        }
                    }
                    else {
                        tmpnews[1] = headingnews;
                    }
                    this.drawnewsVector.addElement(tmpnews);
                }
            }
            this.calculate = true;
        }
    }
    
    public void run() {
        if (this.newsvector == null) {
            this.PopulateNews();
        }
        while (this.noticeThread != null) {
            if (this.drawnewsVector != null && this.calculate) {
                this.newsindex = 0;
                final Enumeration e = this.drawnewsVector.elements();
                while (e.hasMoreElements()) {
                    long time = System.currentTimeMillis();
                    final String[] news = e.nextElement();
                    if (!this.mouseinside) {
                        this.newsstring = news[1];
                        this.heading = news[0];
                    }
                    this.repaint();
                    try {
                        time += this.delay;
                        Thread.sleep(Math.max(0L, time - System.currentTimeMillis()));
                    }
                    catch (InterruptedException ex) {}
                    ++this.newsindex;
                }
            }
            else {
                this.PopulateNews();
                if (this.drawnewsVector == null) {
                    this.newsstring = "... not available";
                    this.noticeThread = null;
                }
            }
            this.repaint();
        }
    }
    
    public synchronized Vector getNewsURL() {
        DataInputStream ds = null;
        String strResponse = "";
        Vector v = null;
        String dataURL = this.baseUrl + "quotetools/getHeadlines.csv?host=" + this.host + "&" + this.topics + "&perTopic=" + this.perTopic + "&webmasterId=" + this.wmid;
        if (this.getParameter("filter") != null) {
            String src = "";
            final StringTokenizer srcTok = new StringTokenizer(this.getParameter("filter"), ",");
            while (srcTok.hasMoreTokens()) {
                final String strTmp = srcTok.nextToken();
                src = src + "&src=" + strTmp;
            }
            dataURL += src;
        }
        try {
            System.out.println("QQQQ: " + dataURL);
            final URL url = new URL(dataURL);
            final URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            ds = new DataInputStream(connection.getInputStream());
            v = new Vector();
            while ((strResponse = ds.readLine()) != null) {
                String[] strRow = null;
                if (strResponse != null && strResponse.length() > 15) {
                    strRow = this.getColumns(strResponse, 6);
                }
                if (strRow != null) {
                    final String[] strLine = new String[5];
                    if (strRow[0].equals("HOT")) {
                        strRow[0] = "TOP STORIES";
                    }
                    if (this.showFullHeader) {
                        strLine[0] = strRow[4];
                    }
                    else {
                        strLine[0] = strRow[0];
                    }
                    strLine[1] = strRow[2];
                    strLine[2] = strRow[3];
                    strLine[4] = strRow[5];
                    this.urllist.addElement(strRow[3]);
                    v.addElement(strLine);
                }
            }
            ds.close();
        }
        catch (Exception exception1) {
            System.out.println("Error while reading news ");
        }
        return v;
    }
    
    public String[] getColumns(final String strRow, final int iColumns) {
        try {
            final String[] str = new String[iColumns];
            int i = 0;
            final StringTokenizer stk = new StringTokenizer(strRow, "|");
            String[] as = null;
            while (stk.hasMoreTokens() && i < iColumns) {
                final String strTmp = stk.nextToken();
                if (strTmp != null) {
                    str[i] = strTmp;
                }
                else {
                    str[i] = " ";
                }
                ++i;
            }
            as = str;
            return as;
        }
        catch (Exception exception1) {
            String[] astr = null;
            astr = null;
            return astr;
        }
    }
    
    public void mouseClicked(final MouseEvent e) {
    }
    
    public void mouseEntered(final MouseEvent e) {
        this.setCursor(new Cursor(12));
        this.mouseinside = true;
        this.repaint();
        this.mouseX = e.getX();
        this.mouseY = e.getY();
        this.stop();
    }
    
    public void mouseExited(final MouseEvent e) {
        this.setCursor(new Cursor(0));
        this.mouseinside = false;
        this.mouseX = 0;
        this.mouseY = 0;
        this.start();
    }
    
    public void mousePressed(final MouseEvent e) {
    }
    
    public void mouseReleased(final MouseEvent e) {
        String URLname = this.urllist.elementAt(this.newsindex) + "&webmasterId=" + this.wmid;
        final String storyId = URLname.substring(URLname.indexOf("?") + 1);
        final String[] newsStr = this.newsvector.elementAt(this.newsindex);
        if (this.newsParamURL != null) {
            String targetURL = "";
            if (newsStr[4] != null && newsStr[4].equalsIgnoreCase("10kwizard")) {
                targetURL = this.baseUrl + "quotetools/clientForward?targetURL=" + URLEncoder.encode(this.newsParamURL + "?source=10kwizard&link=" + URLname + "&topic=" + newsStr[0]);
            }
            else {
                targetURL = this.baseUrl + "quotetools/clientForward?targetURL=" + URLEncoder.encode(this.newsParamURL) + "&" + storyId;
            }
            this.showBrowser(targetURL, "_self");
        }
        else {
            if (newsStr[4] != null && newsStr[4].equalsIgnoreCase("10kwizard")) {
                URLname = this.baseUrl + "quotetools/showFilingOutline.go?link=" + URLname + "&webmasterId=" + this.wmid;
            }
            final String osname = System.getProperty("os.name");
            boolean runJSObject = true;
            if (osname.indexOf("Linux") >= 0 || osname.indexOf("Mac") >= 0) {
                runJSObject = false;
            }
            if (!runJSObject) {
                this.showBrowser(URLname, "_blank");
            }
            else {
                final String tleft = String.valueOf((this.getToolkit().getScreenSize().width - 650) / 2);
                final String mtop = String.valueOf((this.getToolkit().getScreenSize().height - 550) / 2);
                final String property1 = "width=650,height=550,left=" + tleft + ",top=" + mtop + ",resizable=1,menubar=no,toolbar=no,location=no,scrollbars=yes,statusbar=yes,status=yes";
                final String s = "window.open('" + URLname + "','NewsWindow','" + property1 + " ')";
                boolean jsfound = true;
                JSObject win = null;
                try {
                    win = JSObject.getWindow((Applet)this);
                }
                catch (Exception ex) {
                    jsfound = false;
                }
                try {
                    final Object obj = win.eval(s);
                    if (obj == null) {
                        jsfound = false;
                    }
                }
                catch (Exception ex) {
                    jsfound = false;
                }
                if (!jsfound || win == null) {
                    this.showBrowser(URLname, "_blank");
                }
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
    
    public void mouseDragged(final MouseEvent e) {
    }
    
    public void mouseMoved(final MouseEvent e) {
        this.mouseX = e.getX();
        this.mouseY = e.getY();
        this.repaint();
    }
}
