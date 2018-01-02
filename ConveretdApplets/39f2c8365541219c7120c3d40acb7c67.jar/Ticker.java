import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Cursor;
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.net.URL;
import java.awt.Font;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.Image;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Ticker extends Applet implements Runnable
{
    String m_Code;
    Vector vAllow;
    int numPanels;
    TickerPanel[] panels;
    Object[] panelInfo;
    Vector vSymbols;
    boolean isRunning;
    int reloadData;
    Image m_BrandImage;
    boolean brandAlign;
    int brandWidth;
    Color brandColor;
    String brandLink;
    Vector vPromo;
    Thread myThread;
    static int id;
    
    public void stop() {
        if (this.myThread != null) {
            this.isRunning = false;
            try {
                this.myThread.interrupt();
            }
            catch (Exception ex) {}
        }
        for (int i = 0; i < this.panels.length; ++i) {
            this.panels[i].stopThread();
        }
    }
    
    private void loadAppletSettings() {
        try {
            try {
                this.reloadData = Integer.parseInt(this.getParameter("reload"));
            }
            catch (Exception e) {
                this.reloadData = 0;
            }
            this.numPanels = Integer.parseInt(this.getParameter("panels"));
            this.panels = new TickerPanel[this.numPanels];
            this.panelInfo = new Object[this.numPanels];
            for (int index = 1; index < this.numPanels + 1; ++index) {
                final String symbols = this.getParameter(index + ":symbols");
                if (symbols == null) {
                    System.err.println("Ticker: missing symbols for " + index);
                }
                else {
                    final Object[] o = new Object[6];
                    final Vector v = new Vector();
                    final StringTokenizer st = new StringTokenizer(symbols, ",");
                    while (st.hasMoreTokens()) {
                        final String sym = st.nextToken().trim();
                        if (!sym.startsWith("'")) {
                            final StringTokenizer st2 = new StringTokenizer(sym, ":");
                            this.vSymbols.addElement(st2.nextToken().trim().toUpperCase());
                        }
                        v.addElement(sym);
                    }
                    o[0] = v;
                    String s0 = this.getParameter(index + ":multiline");
                    boolean multiline = false;
                    if (s0 != null) {
                        s0 = s0.trim().toUpperCase();
                        multiline = (s0.startsWith("T") || s0.startsWith("Y"));
                    }
                    o[1] = new Boolean(multiline);
                    final Integer[] ss = { new Integer(-1), new Integer(100) };
                    final String s2 = this.getParameter(index + ":scroll");
                    if (s2 != null) {
                        final StringTokenizer st3 = new StringTokenizer(s2, ",");
                        if (st3.countTokens() == 2) {
                            try {
                                ss[0] = new Integer(Integer.parseInt(st3.nextToken().trim()));
                                ss[1] = new Integer(Integer.parseInt(st3.nextToken().trim()));
                            }
                            catch (NumberFormatException nfe) {
                                System.err.println("Ticker: Number error in scroll");
                            }
                        }
                        else {
                            System.err.println("Ticker: missing scroll paramter. Using defaults (-1, 100)");
                        }
                    }
                    o[2] = ss;
                    final String s3 = this.getParameter(index + ":data");
                    if (s3 == null) {
                        o[3] = "price,change";
                    }
                    else {
                        o[3] = s3.trim().toLowerCase();
                    }
                    final Color[] colors = { Color.white, Color.black, Color.green, Color.red, Color.blue };
                    final String[] sa = { "bgcolor", "fgcolor", "pscolor", "ngcolor", "hilight" };
                    for (int i = 0; i < sa.length; ++i) {
                        String color = this.getParameter(index + ":" + sa[i]);
                        if (color != null) {
                            try {
                                if (!color.startsWith("#")) {
                                    color = "#" + color;
                                }
                                colors[i] = new Color(Integer.decode(color.trim()));
                            }
                            catch (NumberFormatException nfe2) {
                                System.err.println("Ticker: invalid color - " + index + ":" + sa[i]);
                            }
                        }
                    }
                    o[4] = colors;
                    o[5] = this.getFont();
                    final String s4 = this.getParameter(index + ":font");
                    if (s4 != null) {
                        final StringTokenizer st4 = new StringTokenizer(s4, ",");
                        if (st4.countTokens() != 3) {
                            System.err.println("Ticker: invalid font setting for " + index);
                        }
                        else {
                            final String fname = st4.nextToken().trim();
                            final String fstyle = st4.nextToken().trim().toUpperCase();
                            final String fsize = st4.nextToken().trim();
                            int xstyle = 0;
                            if (fstyle.equals("BOLD")) {
                                xstyle = 1;
                            }
                            else if (fstyle.equals("ITALIC") || fstyle.equals("ITALICS")) {
                                xstyle = 2;
                            }
                            else if (fstyle.equals("BOLD+ITALIC")) {
                                xstyle = 3;
                            }
                            try {
                                o[5] = new Font(fname, xstyle, Integer.parseInt(fsize));
                            }
                            catch (NumberFormatException nfe3) {
                                System.err.println("Ticker: bad font size - " + index);
                            }
                        }
                    }
                    this.panelInfo[index - 1] = o;
                }
            }
        }
        catch (Exception e) {
            System.err.println("Ticker: failed to initialize due to malconfiguration - " + e);
        }
    }
    
    protected void openBrandPage() {
        if (this.brandLink.length() > 0) {
            try {
                final URL u = new URL(this.brandLink);
                final String target = this.getParameter("target");
                if (target != null) {
                    this.getAppletContext().showDocument(u, target);
                }
                else {
                    this.getAppletContext().showDocument(u);
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public Ticker() {
        this.m_Code = "";
        this.vAllow = new Vector();
        this.numPanels = 0;
        this.vSymbols = new Vector();
        this.isRunning = false;
        this.reloadData = 0;
        this.m_BrandImage = null;
        this.brandAlign = true;
        this.brandWidth = 100;
        this.brandColor = Color.white;
        this.brandLink = "";
        this.vPromo = new Vector();
        System.out.println("Barchart.com Ticker Applet [v3.0.5]\nCopyright (C) 2001 - 2006 by Barchart.com, Inc. All Rights Reserved.\nFor more information, visit http://www2.barchart.com/ticker.asp");
    }
    
    private void add(final Component c, final GridBagConstraints gbc, final int x, final int y, final int w, final int h) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        this.add(c, gbc);
    }
    
    protected synchronized void getData() {
        String arg = "";
        for (int i = 0; i < this.vSymbols.size(); ++i) {
            arg = arg + "I" + this.vSymbols.elementAt(i) + ",";
        }
        final Hashtable h = new Hashtable();
        if (this.vAllow.size() > 0) {
            boolean bOK = false;
            final String mysrv = this.getDocumentBase().getHost().toUpperCase().trim();
            for (int j = 0; j < this.vAllow.size(); ++j) {
                final String xsrv = this.vAllow.elementAt(j).toUpperCase().trim();
                if (mysrv.equals(xsrv)) {
                    bOK = true;
                }
            }
            if (!bOK) {
                System.err.println("Ticker: Authentication error. " + mysrv + " not permissioned.");
                return;
            }
        }
        try {
            final URL u = new URL(this.getCodeBase(), "/jv_data.htx?symbols=" + arg + "&code=" + this.m_Code + "&ref=" + this.getDocumentBase());
            System.out.println(u);
            boolean done = false;
            final BufferedReader in = new BufferedReader(new InputStreamReader(u.openStream()));
            while (!done) {
                final String s = in.readLine();
                if (s == null) {
                    done = true;
                }
                else {
                    final StringTokenizer st = new StringTokenizer(s, ",");
                    if (st.countTokens() <= 8) {
                        continue;
                    }
                    final String sym = st.nextToken().substring(1).trim().toUpperCase();
                    final String[] data = { st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken() };
                    h.put(sym, data);
                }
            }
            in.close();
        }
        catch (IOException ioe) {
            System.err.println("Ticker: encountered error while getting data - " + ioe);
        }
        for (int k = 0; k < this.panelInfo.length; ++k) {
            final Object[] o = (Object[])this.panelInfo[k];
            if (o != null) {
                this.panels[k].clearAllItems();
                if (k == 0) {
                    for (int l = 0; l < this.vPromo.size(); ++l) {
                        this.panels[0].addPromoItem(this.vPromo.elementAt(l), ((Color[])o[4])[1]);
                    }
                }
                final Vector v = (Vector)o[0];
                for (int m = 0; m < v.size(); ++m) {
                    String name = v.elementAt(m);
                    final StringTokenizer st2 = new StringTokenizer(name, ":");
                    final String sym2 = st2.nextToken().toUpperCase();
                    String sdata = "";
                    final String[] data2 = h.get(sym2);
                    int indx = 1;
                    if (name.startsWith("'")) {
                        name = name.substring(1);
                    }
                    else if (data2 == null) {
                        sdata = "No data.";
                    }
                    else {
                        final StringTokenizer st3 = new StringTokenizer((String)o[3], ",");
                        while (st3.hasMoreTokens()) {
                            final String what = st3.nextToken();
                            String tmp = "";
                            if (what.equals("price") || what.equals("last")) {
                                tmp += data2[0];
                            }
                            else if (what.equals("change")) {
                                tmp = data2[1];
                            }
                            else if (what.equals("volume")) {
                                tmp = data2[2];
                            }
                            else if (what.equals("open")) {
                                tmp = data2[3];
                            }
                            else if (what.equals("high")) {
                                tmp = data2[4];
                            }
                            else if (what.equals("low")) {
                                tmp = data2[5];
                            }
                            else if (what.equals("previous")) {
                                tmp = data2[6];
                            }
                            else if (what.equals("percent")) {
                                tmp = data2[7];
                            }
                            if (!tmp.equals("n/a")) {
                                sdata = sdata + tmp + " ";
                            }
                        }
                        if (data2[1].startsWith("+")) {
                            indx = 2;
                        }
                        else if (data2[1].startsWith("-")) {
                            indx = 3;
                        }
                    }
                    this.panels[k].addDataItem(name, sdata, (Color[])o[4], indx, (boolean)o[1]);
                }
            }
            else {
                System.out.println("Ticker: skipping panel " + k);
            }
        }
        h.clear();
    }
    
    protected void openPage(final String link, final boolean b) {
        if (link.length() > 0) {
            try {
                final URL u = new URL(this.brandLink);
                final String target = this.getParameter("target");
                if (target != null) {
                    this.getAppletContext().showDocument(u, target);
                }
                else {
                    this.getAppletContext().showDocument(u);
                }
            }
            catch (Exception ex) {}
        }
    }
    
    protected void openPage(final String sym) {
        if (sym == null || sym.length() < 1) {
            return;
        }
        final String target = this.getParameter("target");
        String code = this.getParameter("cobrand");
        if (code == null) {
            code = "BSTK";
        }
        try {
            final URL u = new URL("http://quotes.barchart.com/quote.asp?sym=" + sym + "&code=" + code);
            if (target != null) {
                this.getAppletContext().showDocument(u, target);
            }
            else {
                this.getAppletContext().showDocument(u);
            }
        }
        catch (MalformedURLException ex) {}
    }
    
    static {
        Ticker.id = 0;
    }
    
    public void start() {
        if (this.myThread == null) {
            (this.myThread = new Thread(this)).start();
        }
    }
    
    public void setScroll(final int panelid, final int direction, final int delay) {
        final int idx = panelid - 1;
        if (idx >= 0 && idx < this.panels.length) {
            this.panels[idx].setScroll(direction, delay);
        }
    }
    
    public void run() {
        System.out.println("DataThread#" + ++Ticker.id + " started.");
        this.isRunning = true;
        int iter = 0;
        while (this.isRunning) {
            if (iter == 0 || this.isShowing()) {
                this.getData();
            }
            else {
                System.out.println("Ticker: applet hidden .. reloop.");
            }
            ++iter;
            if (this.reloadData > 0) {
                System.out.println("Ticker: will reload data after " + this.reloadData + " second(s)");
                try {
                    Thread.sleep(this.reloadData * 1000);
                }
                catch (InterruptedException ie) {
                    this.isRunning = false;
                }
            }
            else {
                this.isRunning = false;
            }
        }
        System.out.println("DataThread#" + Ticker.id + " stopped.");
    }
    
    public void init() {
        this.setCursor(new Cursor(12));
        final GridBagLayout gbl = new GridBagLayout();
        this.setLayout(gbl);
        final GridBagConstraints gbc = new GridBagConstraints();
        this.loadAppletSettings();
        this.loadAppletBranding();
        if (this.getParameter("nologo") != null) {
            this.m_BrandImage = null;
        }
        int width = this.getSize().width;
        final int height = this.getSize().height;
        BrandPanel brandPanel = null;
        if (this.m_BrandImage != null) {
            width -= this.brandWidth;
            brandPanel = new BrandPanel(this, this.m_BrandImage, new Dimension(this.brandWidth, this.getSize().height));
            brandPanel.setBackground(this.brandColor);
            this.setBackground(this.brandColor);
        }
        gbc.fill = 1;
        int xpos = 0;
        if (this.m_BrandImage != null && this.brandAlign) {
            gbc.weightx = this.brandWidth;
            gbc.weighty = height;
            this.add(brandPanel, gbc, xpos++, 0, 1, this.numPanels);
        }
        gbc.weightx = width;
        gbc.weighty = height / this.numPanels;
        final Dimension d = new Dimension(width, height / this.numPanels);
        for (int i = 0; i < this.panels.length; ++i) {
            this.panels[i] = new TickerPanel(this, d);
            final Object[] o = (Object[])this.panelInfo[i];
            this.panels[i].setBackground(((Color[])o[4])[0]);
            if (o[0] != null) {
                final Integer[] ss = (Integer[])o[2];
                this.panels[i].setScroll(ss[0], ss[1]);
                this.panels[i].setFont((Font)o[5]);
            }
            this.add(this.panels[i], gbc, xpos, i, 1, 1);
        }
        if (this.m_BrandImage != null && !this.brandAlign) {
            gbc.weightx = this.brandWidth;
            gbc.weighty = height;
            this.add(brandPanel, gbc, ++xpos, 0, 1, this.numPanels);
        }
    }
    
    private void loadAppletBranding() {
        String code = this.getParameter("cobrand");
        if (code == null) {
            code = "BSTK";
        }
        this.loadAppletBranding(code);
    }
    
    private void loadAppletBranding(final String code) {
        try {
            final URL u = new URL(this.getCodeBase(), code + ".def");
            final BufferedReader in = new BufferedReader(new InputStreamReader(u.openStream()));
            boolean done = false;
            while (!done) {
                String s = in.readLine();
                if (s == null) {
                    done = true;
                }
                else if (s.startsWith("Allow:")) {
                    try {
                        this.vAllow.addElement(s.substring(6).trim());
                    }
                    catch (Exception ex) {}
                }
                else if (s.startsWith("Branding:")) {
                    final String s2 = s.substring(9).trim();
                    final StringTokenizer st = new StringTokenizer(s2, ",");
                    if (st.hasMoreTokens()) {
                        final String simg = st.nextToken();
                        if (!simg.toUpperCase().equals("null")) {
                            try {
                                final URL u2 = new URL(this.getCodeBase(), simg);
                                this.m_BrandImage = this.getImage(u2);
                            }
                            catch (MalformedURLException ex2) {}
                        }
                    }
                    if (st.hasMoreTokens()) {
                        final String salign = st.nextToken().trim().toUpperCase();
                        this.brandAlign = salign.equals("LEFT");
                    }
                    if (st.hasMoreTokens()) {
                        try {
                            this.brandWidth = Integer.parseInt(st.nextToken().trim());
                        }
                        catch (NumberFormatException ex3) {}
                    }
                    if (st.hasMoreTokens()) {
                        try {
                            final String c = st.nextToken().trim();
                            if (!c.startsWith("#")) {
                                s = "#" + c;
                            }
                            this.brandColor = new Color(Integer.decode(c.trim()));
                        }
                        catch (NumberFormatException ex4) {}
                    }
                    if (!st.hasMoreTokens()) {
                        continue;
                    }
                    this.brandLink = st.nextToken();
                }
                else {
                    if (!s.startsWith("Promo:")) {
                        continue;
                    }
                    this.vPromo.addElement(s.substring(6).trim());
                }
            }
            in.close();
        }
        catch (IOException ioe) {
            System.err.println("Ticker: error loading branding for " + code);
            if (!code.equals("BSTK")) {
                this.loadAppletBranding("BSTK");
            }
        }
        this.m_Code = code;
    }
}
