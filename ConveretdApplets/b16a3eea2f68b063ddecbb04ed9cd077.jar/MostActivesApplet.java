import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.net.URLConnection;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.awt.Component;
import java.net.URL;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;
import java.util.Hashtable;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import com.alphatrade.applet.BaseBannerApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MostActivesApplet extends BaseBannerApplet implements ActionListener, MouseListener
{
    private p h;
    private static final Hashtable i;
    private static final Hashtable j;
    private static final int[] k;
    private static final String[] l;
    private Vector m;
    private Image n;
    private Graphics o;
    private Image p;
    private Graphics q;
    private Choice r;
    private Choice s;
    private Button t;
    private Image u;
    private Color v;
    private Color w;
    private Color x;
    private Color y;
    private boolean z;
    private String[] A;
    
    public MostActivesApplet() {
        this.v = MostActivesApplet.b;
        this.w = MostActivesApplet.b;
        this.x = MostActivesApplet.a;
        this.y = MostActivesApplet.a;
        this.z = true;
        this.A = new String[10];
    }
    
    public void init() {
        super.init();
        this.setLayout(null);
        try {
            if (this.getParameter("bgColor") != null) {
                this.v = Color.decode(this.getParameter("bgColor"));
            }
            if (this.getParameter("fgColor") != null) {
                this.w = Color.decode(this.getParameter("fgColor"));
            }
            if (this.getParameter("frameColor") != null) {
                this.x = Color.decode(this.getParameter("frameColor"));
            }
            if (this.getParameter("fontColor") != null) {
                this.y = Color.decode(this.getParameter("fontColor"));
            }
            if (this.getParameter("logo") != null) {
                this.z = !this.getParameter("logo").equalsIgnoreCase("false");
            }
        }
        catch (Exception ex) {
            System.err.println("LYNX <ACTIVES> - ERROR SETTING PARAMETERS: " + ex);
            ex.printStackTrace();
        }
        try {
            this.u = this.getImage(new URL(this.getCodeBase(), "alphalogo.gif"));
        }
        catch (Exception ex2) {
            System.err.println("LYNX <ACTIVES> - ERROR FINDING IMAGE: " + ex2);
        }
        if (this.z) {
            (this.h = new p(this.u, this.x)).setBounds(this.getSize().width - 70, 2, 62, 29);
            this.h.addMouseListener(this);
            this.add(this.h);
        }
        (this.t = new Button("GO")).setBounds(228, 5, 50, 20);
        this.t.addActionListener(this);
        this.add(this.t);
        final Enumeration<Object> keys = MostActivesApplet.i.keys();
        final Enumeration<Object> keys2 = MostActivesApplet.j.keys();
        this.r = new Choice();
        while (keys.hasMoreElements()) {
            this.r.addItem(keys.nextElement().toString());
        }
        this.r.select("NASDAQ");
        this.r.setBounds(8, 5, 80, 20);
        this.add(this.r);
        this.s = new Choice();
        while (keys2.hasMoreElements()) {
            this.s.addItem(keys2.nextElement().toString());
        }
        this.s.select("Volume Actives");
        this.s.setBounds(98, 5, 120, 20);
        this.add(this.s);
        this.b();
        this.a("NASDAQ", "Volume Actives");
    }
    
    private void a(final String s, final String s2) {
        String nextToken = "";
        String string;
        if (MostActivesApplet.i.containsKey(s)) {
            string = MostActivesApplet.i.get(s).toString();
        }
        else {
            string = "1929993";
        }
        String string2;
        if (MostActivesApplet.j.containsKey(s2)) {
            string2 = MostActivesApplet.j.get(s2).toString();
        }
        else {
            string2 = "5";
        }
        final StringBuffer sb = new StringBuffer();
        try {
            sb.append("ma,");
            sb.append(string);
            sb.append(",10,0,");
            sb.append(string2);
            final URLConnection openConnection;
            if ((openConnection = new URL("http", super.c, super.d, "/com.alphatrade.servlet.http.quote.InformationServlet?CRITERIA=" + (Object)sb).openConnection()).getDefaultUseCaches()) {
                openConnection.setDefaultUseCaches(false);
            }
            final BufferedReader bufferedReader;
            final String line;
            if ((line = (bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()))).readLine()).startsWith("N/A")) {
                return;
            }
            for (int i = 0; i < this.A.length; ++i) {
                this.A[i] = null;
            }
            final StringTokenizer stringTokenizer = new StringTokenizer(line, "/");
            int n = 0;
            while (stringTokenizer.hasMoreTokens()) {
                if (n % 2 == 0) {
                    nextToken = stringTokenizer.nextToken();
                }
                else {
                    this.A[new Integer(stringTokenizer.nextToken()) - 1] = nextToken;
                }
                ++n;
            }
            bufferedReader.close();
            this.a(this.A);
        }
        catch (Exception ex) {
            System.err.println("LYNX <ACTIVES> - ERROR REQUESTING ACTIVES: " + ex);
            ex.printStackTrace();
        }
    }
    
    public final void b() {
        final int[] array = { 8, 100, 195, 260, 310, 380 };
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        if (this.n == null) {
            if (this.o != null) {
                this.o.dispose();
                this.o = null;
            }
            this.n = this.createImage(width, height);
            if (this.n == null) {
                return;
            }
            this.o = this.n.getGraphics();
        }
        this.o.setColor(this.x);
        this.o.fillRect(0, 0, width, height);
        this.o.setColor(this.v);
        this.o.fillRect(8, 45, width - 16, this.getSize().height - 53);
        this.o.setFont(new Font("sansserif", 0, 10));
        this.o.setColor(this.y);
        this.o.drawString("Loading market data. Use pull-down menus to choose actives.", 8, 57);
        this.o.setColor(this.w);
        for (int i = 0; i < MostActivesApplet.l.length; ++i) {
            this.o.drawString(MostActivesApplet.l[i], array[i], 40);
        }
        if (this.z) {
            this.h.repaint();
        }
        this.repaint();
    }
    
    public final void c() {
        final int n = this.getSize().width - 16;
        final int n2 = this.getSize().height - 53;
        if (this.p == null) {
            if (this.q != null) {
                this.q.dispose();
                this.q = null;
            }
            this.p = this.createImage(n, n2);
            if (this.p == null) {
                return;
            }
            this.q = this.p.getGraphics();
        }
        this.q.setColor(this.v);
        this.q.fillRect(0, 0, n, n2);
        this.q.setColor(this.y);
        this.q.setFont(new Font("sansserif", 0, 9));
        if (this.z) {
            this.q.drawString("Quotes delayed at least 15 min. Powered by AlphaTrade.com", 0, n2 - 3);
        }
        else {
            this.q.drawString("Quotes delayed at least 15 min.", 0, n2 - 3);
        }
        this.q.setFont(new Font("sansserif", 0, 10));
        this.q.drawString("Loading market data.", 0, 12);
        this.repaint();
    }
    
    public final synchronized void a() {
        this.m = super.g;
        final int n = this.getSize().width - 16;
        final int n2 = this.getSize().height - 53;
        if (this.p == null) {
            if (this.q != null) {
                this.q.dispose();
                this.q = null;
            }
            this.p = this.createImage(n, n2);
            if (this.p == null) {
                return;
            }
            this.q = this.p.getGraphics();
        }
        this.q.setColor(this.v);
        this.q.fillRect(0, 0, n, n2);
        this.q.setColor(this.y);
        this.q.setFont(new Font("sansserif", 0, 9));
        if (this.z) {
            this.q.drawString("Quotes delayed at least 15 min. Powered by AlphaTrade.com", 0, n2 - 3);
        }
        else {
            this.q.drawString("Quotes delayed at least 15 min.", 0, n2 - 3);
        }
        this.q.setFont(new Font("sansserif", 0, 10));
        try {
            for (int i = 0; i < this.m.size(); ++i) {
                final String[] array = this.m.elementAt(i);
                if (i % 2 == 0) {
                    this.q.setColor(this.v.darker());
                    this.q.fillRect(0, 11 + 12 * i, n, 12);
                }
                this.q.setColor(this.y);
                for (int j = 0; j < MostActivesApplet.l.length; ++j) {
                    final String s = array[BaseBannerApplet.d(MostActivesApplet.l[j])];
                    final int stringWidth = this.q.getFontMetrics().stringWidth(s);
                    if (MostActivesApplet.l[j].equals("Last Trade")) {
                        this.q.drawString(s.substring(1), MostActivesApplet.k[j] - this.q.getFontMetrics().stringWidth(s.substring(1)), 8 + 12 * i);
                    }
                    else if (MostActivesApplet.l[j].equals("Change") || MostActivesApplet.l[j].equals("% Change") || MostActivesApplet.l[j].equals("Volume")) {
                        this.q.drawString(s, MostActivesApplet.k[j] - stringWidth, 8 + 12 * i);
                    }
                    else if (MostActivesApplet.l[j].equals("Company")) {
                        if (s.length() >= 25) {
                            this.q.drawString(s.substring(0, 23) + "...", MostActivesApplet.k[j], 8 + 12 * i);
                        }
                        else {
                            this.q.drawString(s, MostActivesApplet.k[j], 8 + 12 * i);
                        }
                    }
                    else {
                        this.q.drawString(s, MostActivesApplet.k[j], 8 + 12 * i);
                    }
                }
            }
        }
        catch (Exception ex) {
            System.err.println("LYNX <ACTIVES> - ERROR DRAWING BUFFER: " + ex);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.a(this.r.getSelectedItem().toString(), this.s.getSelectedItem().toString());
        this.c();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        try {
            this.getAppletContext().showDocument(new URL("http", "www.alphatrade.com/", ""), "_blank");
        }
        catch (Exception ex) {
            System.err.println("LYNX <ACTIVES> - ERROR FINDING URL: " + ex);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.setCursor(new Cursor(12));
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(new Cursor(0));
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.n == null) {
            this.b();
        }
        if (this.p != null) {
            this.o.drawImage(this.p, 8, 45, null);
        }
        graphics.drawImage(this.n, 0, 0, null);
        this.r.repaint();
        this.s.repaint();
        this.t.repaint();
    }
    
    static {
        (i = new Hashtable()).put("NASDAQ", "1929993");
        MostActivesApplet.i.put("NYSE", "1929985");
        MostActivesApplet.i.put("AMEX", "1929986");
        MostActivesApplet.i.put("TSE", "1630475");
        MostActivesApplet.i.put("ME", "1630477");
        MostActivesApplet.i.put("CDNX", "1630476");
        (j = new Hashtable()).put("% Gainers", "1");
        MostActivesApplet.j.put("% Losers", "2");
        MostActivesApplet.j.put("$ Gainers", "3");
        MostActivesApplet.j.put("$ Losers", "4");
        MostActivesApplet.j.put("Volume Actives", "5");
        MostActivesApplet.j.put("Traded Value", "6");
        k = new int[] { 5, 60, 225, 285, 345, 420 };
        l = new String[] { "Symbol", "Company", "Last Trade", "Change", "% Change", "Volume" };
    }
}
