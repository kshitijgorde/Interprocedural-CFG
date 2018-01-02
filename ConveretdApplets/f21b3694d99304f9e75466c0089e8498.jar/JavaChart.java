import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Event;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JavaChart extends Applet implements Runnable
{
    private Thread m_StockChart;
    public ChartContainer cc;
    int bannerTimeout;
    int loadtype;
    int stockexchange;
    int tablestatus;
    String rs;
    boolean loadThreadActive;
    
    public JavaChart() {
        this.setBackground(Color.white);
        this.loadThreadActive = false;
    }
    
    public String cookieToGet() {
        if (this.cc.chart.loading()) {
            return null;
        }
        return this.cc.chart.cookieToGet;
    }
    
    public void destroy() {
    }
    
    public boolean getBoolean(final String s, final boolean flag) {
        boolean flag2;
        if (s == null) {
            flag2 = flag;
        }
        else {
            flag2 = s.equalsIgnoreCase("true");
        }
        return flag2;
    }
    
    public String getCookieName() {
        if (this.cc.chart.loading()) {
            return null;
        }
        return this.cc.chart.getSymbol();
    }
    
    public String getCookieVal() {
        if (this.cc.chart.loading()) {
            return null;
        }
        final String s = this.cc.chart.cookieValue;
        return s;
    }
    
    public int getInt(final String s, final int i) {
        if (s == null) {
            return i;
        }
        int j = i;
        try {
            j = Integer.parseInt(s, 10);
        }
        catch (Exception ex) {}
        return j;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 12100) {
            this.loadThreadActive = false;
            ++this.loadtype;
        }
        else if (event.id == 13100) {
            this.loadThreadActive = false;
            ++this.loadtype;
        }
        else if (event.id == 13168) {
            this.loadThreadActive = false;
            ++this.loadtype;
        }
        return this.cc != null && this.cc.handleEvent(event);
    }
    
    public void init() {
        if (this.getParameter("chartType") != null) {
            this.cc = new ChartContainer(this, this, true);
        }
        else {
            this.cc = new ChartContainer(this, this, true);
        }
        this.rs = this.getParameter("hht");
        this.setLayout(new GridLayout(1, 1));
        this.add(this.cc);
        this.cc.txtSymbol.requestFocus();
        System.out.println(String.valueOf("Running From: ").concat(String.valueOf(this.getDocumentBase().getHost())));
        System.out.println(String.valueOf("Operating System: ").concat(String.valueOf(System.getProperty("os.name"))));
        System.out.println(String.valueOf("OS Version: ").concat(String.valueOf(System.getProperty("os.version"))));
        System.out.println(String.valueOf("Java Version: ").concat(String.valueOf(System.getProperty("java.version"))));
        System.out.println(String.valueOf("Java Vendor: ").concat(String.valueOf(System.getProperty("java.vendor"))));
        this.bannerTimeout = this.getInt(this.getParameter("bannerTimeout"), -1);
        this.rs = this.getParameter("stock");
        this.stockexchange = Integer.parseInt(this.rs);
        this.rs = this.getParameter("status");
        this.tablestatus = Integer.parseInt(this.rs);
    }
    
    public void paint(final Graphics g) {
    }
    
    public void repaint() {
        this.cc.chart.repaint();
    }
    
    public void run() {
        this.updateChart();
        final long l = System.currentTimeMillis();
        long l2 = System.currentTimeMillis() + this.bannerTimeout;
        while (true) {
            try {
                if (this.bannerTimeout > 0 && System.currentTimeMillis() - this.bannerTimeout * 1000 > l2) {
                    l2 = System.currentTimeMillis();
                }
                if (this.loadtype == 0 && !this.loadThreadActive) {
                    if (System.currentTimeMillis() > l + 1000L) {
                        this.loadThreadActive = true;
                        System.out.println("chart data loader");
                        new ChartDataLoader(this, this, "loadsymboltree.txt", 12000);
                    }
                }
                else if (this.loadtype == 1 && !this.loadThreadActive) {
                    this.loadThreadActive = true;
                    new ChartDataLoader(this, this, "futureslist.txt", 13068);
                }
                else if (this.loadtype == 2 && !this.loadThreadActive) {
                    this.loadThreadActive = true;
                    new ChartDataLoader(this, this, "hosts.txt", 13000);
                }
                if (this.loadtype == 3 && !this.loadThreadActive) {
                    this.loadThreadActive = true;
                    if (this.getParameter("icode") != null) {
                        this.cc.chart.loadStock(this.getParameter("icode"), null, true, this.stockexchange, this.tablestatus);
                        this.updateChart();
                    }
                    this.loadThreadActive = false;
                    ++this.loadtype;
                }
                this.cc.repaint();
                this.cc.chart.checkRepaint();
                Thread.sleep(500L);
            }
            catch (Exception ex) {}
        }
    }
    
    public void setCookie(final String s, final String s1) {
        if (this.cc.chart.loading()) {
            return;
        }
    }
    
    public void start() {
        if (this.m_StockChart == null) {
            (this.m_StockChart = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.m_StockChart != null) {
            this.m_StockChart.stop();
            this.m_StockChart = null;
        }
    }
    
    public void updateChart() {
        this.cc.chart.updateChart();
    }
}
