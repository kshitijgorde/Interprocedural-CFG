import java.util.Calendar;
import java.text.DateFormat;
import java.awt.Point;
import java.util.Vector;
import java.net.URL;
import java.util.Locale;
import java.util.StringTokenizer;
import java.awt.Component;
import java.awt.MediaTracker;
import java.applet.Applet;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Font;
import java.text.NumberFormat;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class DCQuoteTable extends h implements Runnable, e
{
    public Color[] J;
    private boolean ag;
    protected a g;
    private NumberFormat E;
    private NumberFormat C;
    private int M;
    public String af;
    public String f;
    public k[] ac;
    private boolean B;
    private String k;
    private Thread G;
    private boolean[] h;
    private boolean m;
    private l s;
    private Font L;
    private Dimension ab;
    private boolean l;
    public int ad;
    private int p;
    public Color[] t;
    public Font A;
    public int N;
    public FontMetrics u;
    public int F;
    public int I;
    public String X;
    public boolean Z;
    private int U;
    private Graphics w;
    public boolean i;
    public int H;
    public int T;
    public boolean O;
    public int W;
    public b[] P;
    private int j;
    public boolean Q;
    private String Y;
    private int K;
    private int ae;
    private String aa;
    private String r;
    private boolean z;
    private String q;
    private String v;
    public String D;
    private String[] S;
    private String[] V;
    private String[] R;
    private String[] o;
    static SimpleDateFormat n;
    
    public DCQuoteTable() {
        this.ag = false;
        this.G = null;
        this.h = new boolean[] { true, true, true, true, true, true, true, true, true };
        this.X = "";
        this.Q = true;
        this.Y = "";
        this.K = 3000;
        this.ae = 10000;
        this.aa = "";
        this.r = "";
        this.z = true;
        this.q = "";
        this.v = "";
        this.S = new String[] { "EN", "DK", "IT", "FR", "DE", "NO", "PT", "ES", "SE", "AR", "CH", "JA", "GR", "TR", "CHT", "DU" };
        this.V = new String[] { "Provided by Saxo Bank", "Leveret af Saxo Bank", "Fornito da Saxo Bank", "Fourni par Saxo Bank", "Datenquelle; Saxo Bank", "Fremskaffet av Saxo Bank", "Fornecido por Saxo Bank", "Ofrecido por Saxo Bank", "Tillhandah\u00e5llet av Saxo Bank", "\u0645\u0642\u062f\u0645\u0629 \u0645\u0646 Saxo Bank", "\u7531SAXO\u94f6\u884c\u63d0\u4f9b", "\u63d0\u4f9bSAXO BANK", "\u03a0\u03b1\u03c1\u03ad\u03c7\u03b5\u03c4\u03b1\u03b9 \u03b1\u03c0\u03bf \u03c4\u03b7\u03bd Saxo Bank", "Saxo Bank taraf\u0131ndan sa\u011flanm\u0131\u015ft\u0131r", "\u7531SAXO\u9280\u884c\u63d0\u4f9b", "Verstrekt door Saxo Bank" };
        this.R = new String[] { "More Realtime quotes", "Flere Realtids kurser", "Quotazione in tempo reale", "Plus de cours en temps r\u00e9el", "Weitere Realtime Quotes", "Flere sanntidskurser", "Mais cota\u00e7\u00f5es em tempo real", "Cotizaciones en tiempo real", "Fler realtids priser", "\u0627\u0644\u0645\u0632\u064a\u062f \u0645\u0646 \u0627\u0644\u0623\u0633\u0639\u0627\u0631 \u0627\u0644\u062d\u064a\u0629", "\u66f4\u591a\u5373\u65f6\u62a5\u4ef7", "\u30b5\u30af\u30bd\u30d0\u30f3\u30af\u306b\u3064\u3044\u3066\u3082\u3063\u3068\u304a\u77e5\u308a\u306b\u306a\u308a\u305f\u3044\u304a\u5ba2\u69d8\u306f\u3053\u3061\u3089\u3092\u30af\u30ea\u30c3\u30af\u3057\u3066\u4e0b\u3055\u3044", "\u03a0\u03b5\u03c1\u03b9\u03c3\u03c3\u03cc\u03c4\u03b5\u03c1\u03b5\u03c2 \u03c4\u03b9\u03bc\u03ad\u03c2 \u03c3\u03b5 \u03c0\u03c1\u03b1\u03b3\u03bc\u03b1\u03c4\u03b9\u03ba\u03cc \u03c7\u03c1\u03cc\u03bd\u03bf", "Ger\u00e7ek zamanl\u0131 di\u011fer kotasyonlar", "\u66f4\u591a\u5373\u6642\u5831\u50f9", "Koersen in real time" };
        this.o = new String[] { "Trade on these prices? FREE Demo", "Handel p\u00e5 disse priser? GRATIS Demo", "Negoziare adesso! Demo GRATUITO", "Passer des ordres? D\u00e9mo GRATUITE", "Handel zu diesen Preisen? Kostenlose Demo", "Handle p\u00e5 disse priser? Gratis demo", "Negocia\u00e7\u00e3o nestes pre\u00e7os? Demo Gratu\u00edta", "Invierta con estos precio? Demo Gratis", "Handla p\u00e5 dessa priser? Gratis demo", "\u0627\u0644\u062a\u062c\u0631\u064a\u0628\u064a \u0627\u0644\u0645\u062c\u0627\u0646\u064a \u0644\u0644\u062a\u062c\u0627\u0631\u0629 \u0627\u0644\u0623\u0644\u0643\u062a\u0631\u0648\u0646\u064a\u0629", "\u4ee5\u6b64\u6c47\u7387\u4ea4\u6613\uff1f\u514d\u8d39\u4ea4\u6613\u6837\u672c\u5e73\u53f0", "\u7121\u6599\u306e\u30c7\u30e2\u3092\u3054\u5e0c\u671b\u306e\u304a\u5ba2\u69d8\u306f\u3053\u3061\u3089\u3092\u30af\u30ea\u30c3\u30af\u3057\u3066\u4e0b\u3055\u3044", "\u0398\u03ad\u03bb\u03b5\u03c4\u03b5 \u03bd\u03b1 \u03b4\u03b9\u03b1\u03c0\u03c1\u03b1\u03b3\u03bc\u03b1\u03c4\u03b5\u03c5\u03c4\u03b5\u03af\u03c4\u03b5 \u03c3\u03b5 \u03b1\u03c5\u03c4\u03ad\u03c2 \u03c4\u03b9\u03c2 \u03c4\u03b9\u03bc\u03ad\u03c2; \u0394\u03c9\u03c1\u03b5\u03ac\u03bd Demo", "Bu fiyatlardan i\u015flem yap\u0131n. \u00dccretsiz Demo", "\u4ee5\u6b64\u532f\u7387\u4ea4\u6613\uff1f\u514d\u8cbb\u4ea4\u6613\u6a23\u54c1\u5e73\u81fa", "Handel op deze prijzen? GRATIS demo" };
        this.J = new Color[8];
        this.p = -2;
        this.k = "129.142.225.146;194.153.239.196;";
        this.m = true;
        this.ab = new Dimension(0, 0);
        this.l = false;
        this.i = false;
        this.ad = 0;
        super.a = true;
        super.do = true;
        this.U = 0;
    }
    
    public void init() {
        super.init();
        final Date date = new Date();
        this.g = new a(this);
        this.ag = this.a("partnerProg", false);
        this.g.if();
        final String a = this.a("partnerId", "1561");
        final String a2 = this.a("LanguageCode", "EN");
        int n = 0;
        for (int i = 0; i < this.S.length; ++i) {
            if (this.S[i].equals(a2)) {
                n = i;
                break;
            }
        }
        final String s = this.R[n];
        final String string = "http://www.saxobank.com/Jump/QuotesFooterLeft.asp?ID=" + a;
        final String s2 = this.o[n];
        final String string2 = "http://www.saxobank.com/Jump/QuotesFooterRight.asp?ID=" + a;
        final String s3 = this.V[n];
        final String string3 = "http://www.saxobank.com/Jump/QuotesHeader.asp?ID=" + a;
        final String parameter = this.getParameter("debug");
        if (parameter != null && parameter.equalsIgnoreCase("true")) {
            this.l = true;
        }
        URL url = this.getDocumentBase();
        url.getHost();
        this.m = true;
        this.X = this.a("Hilight", "NONE").toUpperCase();
        final String a3 = this.a("Instruments", "");
        Vector<String> if1 = null;
        if (!a3.trim().equals("")) {
            if1 = (Vector<String>)c.if(a3, "|");
            this.M = if1.size();
        }
        else {
            this.M = this.a("instrpercard", 10);
        }
        this.j = this.M * 2;
        this.H = this.a("maxcolumnwidth", 500);
        f.a(this.a("vspacing", 0));
        this.J[2] = this.a("bgColorOdd", Color.black);
        this.J[7] = this.a("bgColorEven", (Color)null);
        this.J[5] = this.a("priceColor", Color.black);
        this.J[0] = this.J[5];
        this.J[1] = this.J[5];
        this.Z = this.a("withBorder", false);
        if (!this.ag) {
            this.Q = this.a("withHeader", true);
            super.a = this.a("withFooter", true);
            super.do = this.a("withTitleBar", true);
        }
        if (this.Z) {
            this.J[3] = this.a("bordercolor", Color.blue);
        }
        this.J[4] = this.a("highlightcolor", Color.blue);
        this.W = this.a("highlightmillis", 1000);
        if (this.W > 0) {
            this.J[6] = this.a("updatecolor", Color.orange);
        }
        this.L = new Font("Arial", 1, 12);
        final String a4 = this.a("FontName", "Verdana");
        final int a5 = this.a("FontSize", 12);
        final String upperCase = this.a("FontStyle", "BOLD").toUpperCase();
        int n2;
        if (upperCase.equals("BOLD")) {
            n2 = 1;
        }
        else if (upperCase.equals("ITALIC")) {
            n2 = 2;
        }
        else if (upperCase.equals("PLAIN")) {
            n2 = 0;
        }
        else {
            n2 = 1;
        }
        this.L = new Font(a4, n2, a5);
        this.new();
        this.w = this.getGraphics();
        this.u = this.w.getFontMetrics(this.A);
        this.T = this.u.stringWidth("aaa");
        final String a6 = this.a("Fields", "");
        final Vector if2 = c.if(a6, "|");
        final String a7 = this.a("Header", "");
        final Vector if3 = c.if(a7, ",");
        Vector<String> a8 = null;
        if (!a7.equals("")) {
            this.D = if3.elementAt(0);
        }
        else {
            this.D = "Name";
        }
        int n3;
        if (!a6.trim().equals("")) {
            n3 = if2.size();
            a8 = (Vector<String>)this.a(if2, if3);
        }
        else {
            n3 = this.a("columns", 0);
        }
        this.P = new b[n3];
        this.Y = "";
        for (int j = 0; j < n3; ++j) {
            this.P[j] = new b();
            String parameter2;
            if (a8 != null) {
                parameter2 = a8.elementAt(j);
            }
            else {
                parameter2 = this.getParameter("column" + String.valueOf(j + 1));
            }
            this.P[j].a(parameter2, this.H, this.T);
            this.Y = this.Y + "|" + this.P[j].for();
        }
        this.Y = this.Y.substring(1);
        final MediaTracker mediaTracker = new MediaTracker(this);
        k.d = this.a("priceUpColor", Color.green);
        k.C = this.a("priceDownColor", Color.red);
        this.af = this.a("quoteurl", "http://www.quoteUrl.com/quote.asp?symbol=");
        if (this.af.startsWith("javascript:")) {
            this.af = this.af.substring(11);
            this.O = true;
        }
        else {
            this.O = false;
        }
        this.f = this.a("target", "Quotes");
        this.w.getFontMetrics(this.L);
        k.w = this.a("loadtext", "-");
        k.y = this.a("clickable", false);
        k.m = this.a("highlightmode", 1);
        final String parameter3 = this.getParameter("showna");
        if (parameter3 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter3, ";");
            if (stringTokenizer.countTokens() == 9) {
                for (int k = 0; k < 9; ++k) {
                    this.h[k] = Boolean.valueOf(stringTokenizer.nextToken());
                }
            }
        }
        if (!this.a("figurl", "").equalsIgnoreCase("document")) {
            url = this.getCodeBase();
        }
        k.g = this.getImage(url, "up.gif");
        if (k.g != null) {
            mediaTracker.addImage(k.g, 0);
        }
        k.byte = this.getImage(url, "down.gif");
        if (k.byte != null) {
            mediaTracker.addImage(k.byte, 1);
        }
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        this.setNameWidth(this.a("column0", -1));
        if (this.U == 0) {
            super.goto = 0;
        }
        this.ac = new k[this.M];
        for (int l = 0; l < this.M; ++l) {
            this.ac[l] = new k(this.L, this);
            this.if(l);
            if (if1 != null) {
                this.ac[l].c = if1.elementAt(l);
                this.ac[l].e = if1.elementAt(l);
                this.ac[l].do = false;
                final k m = this.ac[l];
                k.f = false;
                this.ac[l].int();
                this.ac[l].p = this.L;
            }
            else {
                StringTokenizer stringTokenizer2 = new StringTokenizer(this.getParameter("priceitem" + String.valueOf(l + 1)), ";");
                this.ac[l].c = stringTokenizer2.nextToken();
                this.ac[l].e = stringTokenizer2.nextToken();
                if (this.ac[l].c.equals("#")) {
                    if (stringTokenizer2.hasMoreTokens()) {
                        this.ac[l].new = stringTokenizer2.nextToken();
                        if (this.ac[l].new.startsWith("javascript:")) {
                            this.ac[l].new = this.ac[l].new.substring(11);
                            this.ac[l].do = true;
                        }
                        else {
                            this.ac[l].do = false;
                        }
                    }
                }
                else {
                    if (stringTokenizer2.hasMoreTokens() && stringTokenizer2.nextToken().equalsIgnoreCase("true")) {
                        final k k2 = this.ac[l];
                        k.f = true;
                    }
                    else {
                        final k k3 = this.ac[l];
                        k.f = false;
                    }
                    if (stringTokenizer2.hasMoreTokens()) {
                        this.ac[l].h = stringTokenizer2.nextToken();
                    }
                    this.ac[l].int();
                }
                int n4 = 0;
                final String parameter4 = this.getParameter("coloritem" + String.valueOf(l + 1));
                if (parameter4 != null) {
                    stringTokenizer2 = new StringTokenizer(parameter4, ";");
                    n4 = (byte)stringTokenizer2.countTokens();
                }
                final String parameter5 = this.getParameter("fontitem" + String.valueOf(l + 1));
                if (parameter5 != null) {
                    this.ac[l].p = this.a(new StringTokenizer(parameter5, ";"));
                }
                else {
                    this.ac[l].p = this.L;
                }
                for (int n5 = 0; n5 < n4; ++n5) {
                    this.ac[l].u[n5] = this.a(stringTokenizer2.nextToken());
                }
            }
        }
        for (int n6 = 0; n6 < this.M; ++n6) {
            this.ac[n6].a(this.w);
        }
        this.ac[this.M - 1].A = true;
        this.initHeader(this.w);
        this.a(n3 * this.H, this.M * (this.ad + f.a()), this.J[2]);
        this.a(this.ac);
        final int a9 = this.a("pchngprec", 2);
        (this.E = NumberFormat.getInstance(Locale.US)).setMaximumFractionDigits(a9);
        this.E.setMinimumFractionDigits(a9);
        (this.C = NumberFormat.getInstance(Locale.US)).setMaximumFractionDigits(4);
        this.C.setMinimumFractionDigits(2);
        this.C.setGroupingUsed(false);
        final Color a10 = this.a("FGTitleColor", Color.white);
        final Color a11 = this.a("BGTitleColor", Color.black);
        if (super.do) {
            super.d.a(a10, a11);
            super.d.a(this.a("Title", s3), this.a("TitleURL", string3), this.a("LinkTarget", "_blank"));
            super.d.setFont(this.L);
            super.d.a();
        }
        if (super.a) {
            super.void.setFont(this.L);
            if (this.ag) {
                super.void.a(this.a("FGFooterColor", a10), this.a("footerBG", a11));
                super.void.a(s, string, s2, string2, this.a("LinkTarget", "_blank"));
            }
            else {
                super.void.a(this.a("FGFooterColor", a10), this.a("footerBG", a11));
                super.void.a(this.a("FooterLeft", "Copyright (c) 2003 Saxo Bank"), this.a("FooterLeftUrl", ""), this.a("FooterRight", ""), this.a("FooterRightUrl", ""), this.a("LinkTarget", "_blank"));
            }
            super.void.a();
        }
        this.K = this.a("RefreshDelay", 1000);
        if (this.ag) {
            this.K = ((this.K < 10000) ? 10000 : this.K);
        }
        this.ae = this.a("RetryDelay", 30000);
        this.aa = this.a("UserParam", "");
        this.r = this.a("Type", "");
        this.v = this.a("User", "234");
        this.z = this.a("AutoStart", true);
        final String lowerCase = this.getCodeBase().toString().toLowerCase();
        this.q = this.a("URL_STRING", lowerCase.substring(0, lowerCase.lastIndexOf("_app")) + "_DataFeed/IITDCQuoteFeedExt10.dll");
    }
    
    private void if(final int n) {
        for (int i = 0; i < 7; ++i) {
            if (i != 2) {
                this.ac[n].u[i] = this.J[i];
            }
            else if (this.J[7] != null) {
                this.ac[n].u[2] = this.J[2 + (n + 1) % 2 * 5];
            }
            else {
                this.ac[n].u[2] = this.J[2];
            }
        }
    }
    
    public void start() {
        if (this.m) {
            final int width = this.getSize().width;
            this.recalcWidths((width > super.goto) ? width : (10 + super.goto));
            this.initHeader(this.w);
            for (int i = 0; i < this.M; ++i) {
                this.ac[i].a(this.w);
            }
            this.for();
            this.B = false;
            if (this.G == null) {
                this.G = new Thread(this, "initThread-quoteBar");
            }
            if (this.z) {
                this.manualStart();
            }
        }
    }
    
    public void manualStart() {
        if (this.G == null) {
            this.G = new Thread(this, "initThread-quoteBar");
        }
        this.G.start();
    }
    
    public void startCollector() {
        String string = "";
        for (int i = 0; i < this.ac.length; ++i) {
            string = string + "|" + this.ac[i].c;
        }
        final String substring = string.substring(1);
        this.for(this.Y);
        (this.s = new l(this, this.q, substring, this.Y, this.g.if(), this.g.a().toString(), this.v)).a((long)this.K);
        this.s.a(this.ae);
        this.s.for(this.aa);
        this.s.P = this.a("rgId", -1);
        this.s.try = !this.a("autoTZ", false);
        this.s.if(this.r);
        int b = 0;
        int n = 0;
        while (b < this.M && !this.B) {
            if (!this.ac[b].c.equals("#")) {
                this.ac[n].b = b;
                ++n;
            }
            ++b;
        }
        this.s.byte();
    }
    
    public void run() {
        this.startCollector();
        while (!this.B) {
            for (int n = 0; !this.B && n < this.M; ++n) {
                if (this.ac[n].z == 1) {
                    if (this.ac[n].t < 0) {
                        this.ac[n].z = 0;
                        this.a(this.ac[n]);
                    }
                    else {
                        final k k = this.ac[n];
                        k.t -= 10;
                    }
                }
            }
            if (!this.B) {
                try {
                    Thread.sleep(10L);
                }
                catch (Exception ex) {}
            }
        }
    }
    
    public void stopCollector() {
        this.s.goto();
    }
    
    public void stop() {
        if (this.m) {
            this.B = true;
            if (super.byte) {
                try {
                    this.G.join();
                }
                catch (InterruptedException ex) {}
            }
            else {
                this.G.stop();
            }
            for (int i = 0; i < this.M; ++i) {
                this.ac[i].z = 2;
            }
            this.stopCollector();
        }
        System.gc();
        this.G = null;
    }
    
    public void destroy() {
    }
    
    public String getAppletInfo() {
        return "DCQuoteTable 5.1";
    }
    
    public void onQuoteHistory(final Vector vector, final Vector vector2) {
        this.processUpdate(vector, vector2);
    }
    
    public void onQuoteUpdate(final Vector vector, final Vector vector2) {
        this.processUpdate(vector, vector2);
    }
    
    public void onReload(final int n) {
        System.out.println("reload");
    }
    
    public void updateBid(final int n, final String s) {
    }
    
    public void onQuoteMonitorInfo(final String s) {
        System.out.println("INFO : " + s);
    }
    
    public void onQuoteMonitorError(final String s) {
        System.out.println("ERROR " + DCQuoteTable.n.format(new Date()) + " : " + s);
    }
    
    public final void processUpdate(final Vector vector, final Vector vector2) {
        for (int i = 0; i < vector.size(); ++i) {
            final Point point = vector.elementAt(i);
            int n = point.y;
            if (k.f && (point.y == k.o || point.y == k.x)) {
                n = k.goto;
            }
            this.ac[point.x].a(vector2.elementAt(i), point.y);
            if (!this.P[n].for().equals("U") && !this.P[n].for().equals("T") && this.W > 0) {
                if (this.ac[point.x].z == 2) {
                    this.ac[point.x].z = 0;
                }
                else {
                    this.ac[point.x].z = 1;
                    this.ac[point.x].t = this.W;
                }
            }
        }
        for (int j = 0; j < this.ac.length; ++j) {
            this.a(this.ac[j]);
        }
    }
    
    public void ONITInfo(final short n, final short n2, final String s) {
        try {
            this.showStatus(s);
        }
        catch (Exception ex) {}
    }
    
    public void IHttpError(final String s) {
        try {
            this.showStatus(s);
        }
        catch (Exception ex) {}
    }
    
    public void OnITChartHistory(final short n, final int n2) {
    }
    
    public void OnITChartUpdate(final short n, final boolean b) {
    }
    
    public void OnITNewsHistory(final short n, final int n2) {
    }
    
    public void OnITNewsUpdate(final short n) {
    }
    
    public void OnITNewsStory(final short n, final String s) {
    }
    
    private Font a(StringTokenizer stringTokenizer) {
        final String nextToken = stringTokenizer.nextToken();
        final int int1 = Integer.parseInt(stringTokenizer.nextToken());
        if (int1 > this.ad) {
            this.ad = int1;
        }
        if (!stringTokenizer.hasMoreTokens()) {
            return new Font(nextToken, 0, int1);
        }
        final String nextToken2 = stringTokenizer.nextToken();
        int n = 0;
        stringTokenizer = new StringTokenizer(nextToken2, "+");
        while (stringTokenizer.hasMoreTokens()) {
            n += this.do(stringTokenizer.nextToken());
        }
        return new Font(nextToken, n, int1);
    }
    
    private int do(final String s) {
        if (s.equals("BOLD")) {
            return 1;
        }
        if (s.equals("ITALIC")) {
            return 2;
        }
        return 0;
    }
    
    private void new() {
        (this.t = new Color[3])[0] = this.a("FGheadColor", this.J[5]);
        this.t[1] = this.a("BGheadColor", this.J[2]);
        this.t[2] = this.J[3];
        this.A = this.L;
        final String parameter = this.getParameter("headeropt");
        if (parameter != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ";");
            for (int i = 0; i < 3; ++i) {
                if (stringTokenizer.hasMoreTokens()) {
                    this.t[i] = this.a(stringTokenizer.nextToken());
                }
            }
            if (stringTokenizer.hasMoreTokens()) {
                this.A = this.a(stringTokenizer);
            }
        }
    }
    
    public void resizeColumn(final int n, final int goto1) {
        boolean b = false;
        if (n >= -1 && n < this.P.length) {
            if (goto1 >= this.T && goto1 <= this.H) {
                final int case1 = this.P[n + 1].case();
                if (n >= 0) {
                    this.P[n + 1].a(case1 + (this.P[n].case() - goto1));
                    this.P[n].a(goto1);
                    b = true;
                }
                else {
                    this.P[n + 1].a(case1 + (super.goto - goto1));
                    super.goto = goto1;
                    b = true;
                }
            }
            if (b) {
                this.initHeader(this.w);
                this.a(this.p);
                for (int i = 0; i < this.M; ++i) {
                    this.ac[i].a(this.w);
                }
                this.for();
            }
            else {
                this.showStatus("Column cannot be resized.");
            }
        }
        else {
            this.showStatus("Invalid column.");
        }
    }
    
    public void handleResize() {
        this.initHeader(this.w);
        for (int i = 0; i < this.M; ++i) {
            this.ac[i].a(this.w);
        }
        this.for();
    }
    
    public void selectColumn(final int p) {
        this.p = p;
    }
    
    public int getColumn() {
        return this.p;
    }
    
    public void initHeader(final Graphics graphics) {
        this.F = 0;
        for (int i = 0; i < this.P.length; ++i) {
            this.P[i].new = this.F;
            this.F += this.P[i].case();
        }
        if (this.Q) {
            this.I = this.u.getHeight() + 2;
        }
        if (this.Z && this.Q) {
            this.F += 2;
            this.I += 2;
        }
        this.F += super.goto;
        if (this.Q) {
            this.N = this.u.getHeight() - this.u.getMaxDescent();
        }
        if (this.getSize().width > 0) {
            this.F = this.getSize().width;
        }
    }
    
    public void setNameWidth(final int n) {
        switch (n) {
            case 0: {
                this.U = 0;
                super.goto = n;
                break;
            }
            case -1: {
                this.U = 1;
                super.goto = 50;
                break;
            }
            default: {
                this.U = 2;
                super.goto = n;
                break;
            }
        }
    }
    
    public int findColumn(final int n) {
        int n2 = 0;
        int n3 = 0;
        int case1 = 0;
        for (int n4 = 0; n4 < this.P.length && n2 <= n; n2 += case1, ++n3, ++n4) {
            case1 = this.P[n4].case();
        }
        if (n3 > 0 && Math.abs(n2 - case1 - n) > 4) {
            n3 = 0;
        }
        return n3 - 1;
    }
    
    private Vector a(final Vector vector, final Vector vector2) {
        final Vector<String> vector3 = new Vector<String>(vector.size());
        final int size = vector2.size();
        for (int i = 0; i < vector.size(); ++i) {
            final char char1 = vector.elementAt(i).charAt(0);
            String trim;
            if (i >= size - 1) {
                trim = "";
            }
            else {
                trim = vector2.elementAt(i + 1).trim();
            }
            final String string = vector.elementAt(i) + ";";
            int n = 0;
            boolean b = false;
            String s = null;
            int n2 = 0;
            int n3 = 0;
            switch (char1) {
                case 65: {
                    s = (trim.equals("") ? "Ask" : trim);
                    n2 = 0;
                    n3 = -1;
                    b = true;
                    n = 1;
                    break;
                }
                case 66: {
                    s = (trim.equals("") ? "Bid" : trim);
                    n2 = 0;
                    n3 = -1;
                    b = true;
                    n = 1;
                    break;
                }
                case 69: {
                    s = (trim.equals("") ? "Last" : trim);
                    n2 = 0;
                    n3 = 5;
                    break;
                }
                case 70: {
                    s = (trim.equals("") ? "Name" : trim);
                    n2 = -1;
                    n3 = 5;
                    break;
                }
                case 71: {
                    s = (trim.equals("") ? "Delay" : trim);
                    n2 = 0;
                    n3 = 5;
                    break;
                }
                case 72: {
                    s = (trim.equals("") ? "High" : trim);
                    n2 = 0;
                    n3 = 5;
                    break;
                }
                case 76: {
                    s = (trim.equals("") ? "Low" : trim);
                    n2 = 0;
                    n3 = 5;
                    break;
                }
                case 78: {
                    s = (trim.equals("") ? "Net Change" : trim);
                    n2 = 0;
                    n3 = -1;
                    n = 2;
                    break;
                }
                case 80: {
                    s = (trim.equals("") ? "Pct. Change" : trim);
                    n2 = 0;
                    n3 = -1;
                    n = 2;
                    break;
                }
                case 83: {
                    s = (trim.equals("") ? "Close" : trim);
                    n2 = 0;
                    n3 = 5;
                    break;
                }
                case 84: {
                    s = (trim.equals("") ? "Time" : trim);
                    n2 = 0;
                    n3 = 5;
                    break;
                }
                case 85: {
                    s = (trim.equals("") ? "Age" : trim);
                    n2 = 0;
                    n3 = 5;
                    break;
                }
                case 88: {
                    s = (trim.equals("") ? "Exch." : trim);
                    n2 = 0;
                    n3 = 5;
                    break;
                }
                case 89: {
                    s = (trim.equals("") ? "Bid/Ask" : trim);
                    n2 = 0;
                    n3 = -1;
                    b = true;
                    n = 1;
                    break;
                }
                default: {
                    s = (trim.equals("") ? ("?" + char1 + "?") : trim);
                    n2 = 0;
                    n3 = 5;
                    break;
                }
            }
            vector3.addElement(string + s + ";50;" + n3 + ";" + b + ";false;" + n2 + ";" + n);
        }
        return vector3;
    }
    
    public void recalcWidths(final int n, final int n2) {
        int n3 = 0;
        for (int i = n2; i < this.P.length; ++i) {
            n3 += this.P[i].case();
        }
    }
    
    public void recalcWidths(final int n) {
        int n2 = 0;
        for (int i = 0; i < this.P.length; ++i) {
            n2 += this.P[i].case();
        }
        if (this.U == 1) {
            super.goto = super.goto * n / (n2 + super.goto);
        }
        final int n3 = n - super.goto;
        int new1 = 0;
        for (int j = 0; j < this.P.length; ++j) {
            this.P[j].a(this.P[j].case() * n3 / n2);
            this.P[j].new = new1;
            new1 += this.P[j].case();
        }
        final int n4 = n3 - new1 + this.P[this.P.length - 1].case();
    }
    
    private boolean try() {
        return this.g.do();
    }
    
    private void for(final String s) {
        final int index = s.indexOf("Y");
        if (index < 0) {
            return;
        }
        final String string = s.substring(0, index) + "G" + s.substring(index + 1) + "|B|A";
        k.goto = index / 2;
        k.f = true;
        k.o = string.length() / 2 - 1;
        k.x = k.o + 1;
    }
    
    static {
        (DCQuoteTable.n = (SimpleDateFormat)DateFormat.getDateTimeInstance(1, 1)).setCalendar(Calendar.getInstance());
        DCQuoteTable.n.applyPattern("HH:mm:ss");
    }
}
