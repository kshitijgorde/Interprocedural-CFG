import java.awt.Cursor;
import java.net.URLConnection;
import java.io.DataInputStream;
import java.util.Enumeration;
import java.awt.Component;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.net.URL;
import java.util.StringTokenizer;
import java.awt.FontMetrics;
import java.util.Vector;
import java.awt.MediaTracker;
import java.util.Hashtable;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class AcuteShifter extends Applet implements Runnable
{
    private String void;
    private Image A;
    private Image c;
    int K;
    int else;
    private Thread v;
    int z;
    int aa;
    boolean M;
    boolean long;
    boolean g;
    boolean null;
    String R;
    String b;
    long Q;
    String E;
    String B;
    long new;
    long for;
    boolean V;
    int do;
    String j;
    String d;
    String m;
    private static final int w = 0;
    private static final int Y = 1;
    private static final int O = 2;
    private static final int Z = 3;
    private static final int t = 4;
    private static final int N = 5;
    private static final String[] q;
    private Hashtable W;
    private Hashtable f;
    private Hashtable i;
    private Hashtable o;
    private String r;
    String[][] P;
    String[][] l;
    MediaTracker U;
    Vector p;
    Vector X;
    Vector s;
    Vector h;
    Vector a;
    Vector if;
    Vector F;
    Vector k;
    Vector H;
    Vector e;
    Vector n;
    int goto;
    int D;
    int case;
    int int;
    int C;
    int u;
    int L;
    FontMetrics T;
    Hashtable G;
    int S;
    Vector char;
    Vector J;
    Vector I;
    Vector byte;
    Hashtable try;
    
    static {
        q = new String[] { "integer", "boolean", "string", "color", "url", "image" };
    }
    
    public AcuteShifter() {
        this.void = "AcuteShifter v2.03";
        this.K = 1;
        this.else = 1;
        this.v = null;
        this.z = 0;
        this.aa = 0;
        this.M = false;
        this.long = false;
        this.g = false;
        this.null = false;
        this.do = -1;
        this.j = "";
        this.d = "";
        this.W = new Hashtable();
        this.f = new Hashtable();
        this.i = new Hashtable();
        this.o = new Hashtable();
        this.r = null;
        this.P = new String[][] { { "#amp;", "&" }, { "#lt;", "<" }, { "#gt;", ">" }, { "#apos;", "'" }, { "#quot;", "\"" }, { "#nbsp;", " " }, { "&amp;", "&" }, { "&lt;", "<" }, { "&gt;", ">" }, { "&apos;", "'" }, { "&quot;", "\"" }, { "&nbsp;", " " } };
        this.l = new String[][] { { "<!--", "-->" }, { "<![CDATA[", "]]>" }, { "<!", ">" }, { "<?", "?>" } };
        this.try = null;
    }
    
    private final void do() {
        final URL documentBase = this.getDocumentBase();
        if (documentBase.getProtocol().equalsIgnoreCase("file")) {
            return;
        }
        String s = documentBase.getHost().toLowerCase();
        final int lastIndex = s.lastIndexOf(".");
        if (lastIndex != -1) {
            final int lastIndex2 = s.lastIndexOf(".", lastIndex - 1);
            if (lastIndex2 != -1) {
                s = s.substring(lastIndex2 + 1);
            }
        }
        if (s.equals("")) {
            return;
        }
        final char[] charArray = s.toCharArray();
        char c = '\u3b29';
        for (int i = 0; i < charArray.length; ++i) {
            c ^= (char)(charArray[i] << i % 8);
        }
        String parameter = this.getParameter("Domain-Keys");
        if (parameter == null) {
            parameter = "";
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ",");
        while (stringTokenizer.hasMoreTokens()) {
            if (c == Integer.parseInt(stringTokenizer.nextToken().trim())) {
                return;
            }
        }
        this.null = true;
        this.paint(this.getGraphics());
        try {
            Thread.sleep(7000L);
        }
        catch (InterruptedException ex) {}
        this.null = false;
    }
    
    private final void a(final int n, int n2, int n3) {
        if (!this.long) {
            return;
        }
        final Rectangle rectangle = this.byte.elementAt(0);
        n2 += rectangle.x;
        n3 += rectangle.y;
        final String s = (String)this.a(n, "vertical-align");
        final int intValue = this.I.elementAt(n);
        if (s.equalsIgnoreCase("center")) {
            n3 += (rectangle.height - intValue) / 2;
        }
        else if (s.equalsIgnoreCase("bottom")) {
            n3 += rectangle.height - intValue;
        }
        final int intValue2 = this.char.elementAt(n);
        for (int intValue3 = this.J.elementAt(n), i = intValue2; i <= intValue3; ++i) {
            if (((Rectangle)this.s.elementAt(i)).inside(this.z - n2, this.aa - n3)) {
                this.try = (Hashtable)this.a.elementAt(i);
                if (this.try != null) {
                    return;
                }
            }
        }
    }
    
    private final void a(final int n, int n2, int n3, final Graphics graphics) {
        final Rectangle rectangle = this.byte.elementAt(0);
        graphics.clipRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        n2 += rectangle.x;
        n3 += rectangle.y;
        final String s = (String)this.a(n, "vertical-align");
        final int intValue = this.I.elementAt(n);
        if (s.equalsIgnoreCase("center")) {
            n3 += (rectangle.height - intValue) / 2;
        }
        else if (s.equalsIgnoreCase("bottom")) {
            n3 += rectangle.height - intValue;
        }
        final int intValue2 = this.char.elementAt(n);
        final int intValue3 = this.J.elementAt(n);
        boolean booleanValue = false;
        for (int i = intValue2; i <= intValue3; ++i) {
            final Object element = this.X.elementAt(i);
            final String s2 = this.h.elementAt(i);
            if (s2.equals("font")) {
                graphics.setFont((Font)element);
            }
            else if (s2.equals("proptable")) {
                final Hashtable<?, ?> hashtable = (Hashtable<?, ?>)element;
                graphics.setColor((Color)this.a(hashtable, "text-color"));
                booleanValue = (boolean)this.a(hashtable, "text-underline");
            }
            else {
                final Rectangle rectangle2 = this.s.elementAt(i);
                final int n4 = rectangle2.x + n2;
                final int n5 = rectangle2.y + n3;
                final int height = rectangle2.height;
                final int width = rectangle2.width;
                if (new Rectangle(n4, n5, width, height).intersects(rectangle)) {
                    if (s2.equals("text")) {
                        final String s3 = (String)element;
                        final int ascent = graphics.getFontMetrics().getAscent();
                        graphics.drawString(s3, n4, n5 + ascent);
                        if (booleanValue) {
                            graphics.drawLine(n4, n5 + 1 + ascent, n4 + width, n5 + 1 + ascent);
                        }
                    }
                    else if (s2.equals("bullet-text")) {
                        graphics.drawString((String)this.a(element, "bullet-text"), n4, n5 + graphics.getFontMetrics().getAscent());
                    }
                    else if (s2.equals("rule")) {
                        final Color color = graphics.getColor();
                        graphics.setColor((Color)this.a(element, "rule-color"));
                        graphics.fillRect(n4, n5, width, height);
                        graphics.setColor(color);
                    }
                    else if (s2.equals("image")) {
                        graphics.drawImage((Image)this.a(element, "image"), n4, n5, this);
                        this.getToolkit().sync();
                    }
                    else if (s2.equals("bullet-image")) {
                        graphics.drawImage((Image)this.a(element, "bullet-image"), n4, n5, this);
                        this.getToolkit().sync();
                    }
                }
            }
        }
    }
    
    private final void if(final int n, final Graphics graphics) {
        graphics.clipRect(0, 0, this.K, this.else);
        graphics.setColor((Color)this.a(n, "background-color"));
        graphics.fillRect(0, 0, this.K, this.else);
        final Image image = (Image)this.a(n, "background-image");
        if (image != null) {
            final boolean booleanValue = (boolean)this.a(n, "background-image-repeat");
            final int intValue = (int)this.a(n, "background-image-x");
            final int intValue2 = (int)this.a(n, "background-image-y");
            if (booleanValue) {
                int i = intValue;
                int j = intValue2;
                while (i < this.K) {
                    while (j < this.else) {
                        graphics.drawImage(image, i, j, this);
                        this.getToolkit().sync();
                        j += image.getHeight(this);
                    }
                    j = 0;
                    i += image.getWidth(this);
                }
            }
            else {
                graphics.drawImage(image, intValue, intValue2, this);
                this.getToolkit().sync();
            }
        }
    }
    
    private final void a(final int n, final Graphics graphics) {
        graphics.clipRect(0, 0, this.K, this.else);
        final Image image = (Image)this.a(n, "foreground-image");
        if (image != null) {
            graphics.drawImage(image, (int)this.a(n, "foreground-image-x"), (int)this.a(n, "foreground-image-y"), this);
            this.getToolkit().sync();
        }
        graphics.setColor((Color)this.a(n, "border-color"));
        final String s = (String)this.a(n, "border-type");
        final int intValue = (int)this.a(n, "border-width");
        if (s.equalsIgnoreCase("full") || s.equalsIgnoreCase("vertical")) {
            for (int i = 0; i < intValue; ++i) {
                graphics.drawLine(i, 0, i, this.else - 1);
                graphics.drawLine(this.K - i - 1, 0, this.K - i - 1, this.else - 1);
            }
        }
        if (s.equalsIgnoreCase("full") || s.equalsIgnoreCase("horizontal")) {
            for (int j = 0; j < intValue; ++j) {
                graphics.drawLine(0, j, this.K - 1, j);
                graphics.drawLine(0, this.else - j - 1, this.K - 1, this.else - j - 1);
            }
        }
    }
    
    private final synchronized String[] a() {
        boolean b = false;
        final long currentTimeMillis = System.currentTimeMillis();
        if (this.V && this.for <= currentTimeMillis) {
            this.for = Long.MAX_VALUE;
            b = true;
        }
        if (this.Q <= currentTimeMillis) {
            this.Q = Long.MAX_VALUE;
            b = true;
        }
        if (b) {
            this.V = false;
            return new String[] { this.R, this.b };
        }
        return null;
    }
    
    private final synchronized String[] try() {
        if (this.new > System.currentTimeMillis()) {
            return null;
        }
        this.new = Long.MAX_VALUE;
        if (this.V && this.j.equalsIgnoreCase(this.E) && this.d.equalsIgnoreCase(this.B)) {
            return null;
        }
        this.V = true;
        this.j = this.E;
        this.d = this.B;
        return new String[] { this.E, this.B };
    }
    
    public final synchronized void goToNextSection() {
        this.do = -2;
    }
    
    public final synchronized void goToPreviousSection() {
        this.do = -3;
    }
    
    public final synchronized void goToSection(final String s) {
        if (s != null) {
            for (int i = 0; i < this.S; ++i) {
                final String s2 = (String)this.a(i, "section-name");
                if (s2 != null && s2.equalsIgnoreCase(s)) {
                    this.do = i;
                }
            }
        }
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 504: {
                this.long = true;
                break;
            }
            case 505: {
                this.long = false;
                break;
            }
            case 501: {
                this.M = true;
                break;
            }
            case 502: {
                this.M = false;
                if (this.try == null) {
                    break;
                }
                final URL url = (URL)this.a(this.try, "url");
                final String s = (String)this.a(this.try, "url-frame");
                String string = (String)this.a(this.try, "load-main-message");
                String string2 = (String)this.a(this.try, "load-main-style");
                String string3 = (String)this.a(this.try, "load-temp-message");
                String string4 = (String)this.a(this.try, "load-temp-style");
                final String s2 = (String)this.a(this.try, "link-section-name");
                if (url != null) {
                    this.getAppletContext().showDocument(url, s);
                    break;
                }
                if (string != null || string2 != null) {
                    if (string != null) {
                        string = "file:" + string;
                    }
                    if (string2 != null) {
                        string2 = "file:" + string2;
                    }
                    this.loadMain(string, string2);
                    break;
                }
                if (string3 != null || string4 != null) {
                    if (string3 != null) {
                        string3 = "file:" + string3;
                    }
                    if (string4 != null) {
                        string4 = "file:" + string4;
                    }
                    this.loadTemp(string3, string4, 0);
                    break;
                }
                if (s2 != null) {
                    this.goToSection(s2);
                    break;
                }
                if (this.a(this.try, "link-next-section")) {
                    this.goToNextSection();
                    break;
                }
                if (this.a(this.try, "link-previous-section")) {
                    this.goToPreviousSection();
                    break;
                }
                break;
            }
            case 503:
            case 506: {
                this.z = event.x;
                this.aa = event.y;
                break;
            }
            case 401:
            case 403: {
                if (event.key == 1008) {
                    this.loadTemp(String.valueOf(this.void) + "<br>Domain: <b>" + this.getDocumentBase().getHost().toLowerCase() + "</b>" + "<br>Keys: <b>" + this.getParameter("Domain-Keys") + "</b>", "", 0);
                    break;
                }
                break;
            }
        }
        return true;
    }
    
    private final boolean byte() {
        if ((this.U.statusAll(true) & 0x1) != 0x0) {
            return false;
        }
        for (int i = 0; i < this.p.size(); ++i) {
            if ((this.U.statusID(i, false) & 0x4) != 0x0) {
                this.m = String.valueOf(this.m) + "<b>Error 11: </b>Unable to load image:<br>";
                this.m = String.valueOf(this.m) + "'" + this.int(((URL)this.p.elementAt(i)).toString()) + "'<br>";
            }
        }
        return true;
    }
    
    private final void a(final Vector vector) {
        this.U = new MediaTracker(this);
        this.p = new Vector();
        for (int i = 0; i < vector.size(); ++i) {
            final Hashtable<String, V> element = vector.elementAt(i);
            if (element.getClass().getName().equals("java.util.Hashtable")) {
                final Hashtable<String, Image> hashtable = (Hashtable<String, Image>)element;
                final Enumeration<String> keys = hashtable.keys();
                while (keys.hasMoreElements()) {
                    String s2;
                    final String s = s2 = keys.nextElement();
                    if (s.endsWith("-over")) {
                        s2 = s.substring(0, s.length() - 5);
                    }
                    if (s.endsWith("-click")) {
                        s2 = s.substring(0, s.length() - 6);
                    }
                    if ((int)this.W.get(s2) == 5) {
                        final URL url = (URL)hashtable.get(s);
                        final Image image = this.getImage(url);
                        hashtable.put(s, image);
                        boolean b = false;
                        for (int j = 0; j < this.p.size(); ++j) {
                            if (this.p.elementAt(j).equals(url)) {
                                b = true;
                                break;
                            }
                        }
                        if (b) {
                            continue;
                        }
                        this.U.addImage(image, this.p.size());
                        this.p.addElement(url);
                    }
                }
            }
        }
    }
    
    public final void init() {
        boolean b = false;
        do {
            try {
                this.K = this.size().width;
                this.else = this.size().height;
                Thread.sleep(100L);
                b = true;
            }
            catch (Exception ex) {}
        } while (!b);
        this.A = this.createImage(this.K, this.else);
        this.c = this.createImage(this.K, this.else);
    }
    
    private final synchronized void for() {
        this.R = "";
        this.b = "";
        this.Q = Long.MAX_VALUE;
        this.E = "";
        this.B = "";
        this.new = Long.MAX_VALUE;
        this.for = Long.MAX_VALUE;
        this.V = false;
    }
    
    private final void if(final Vector vector) {
        this.goto = 0;
        this.D = 0;
        this.case = 0;
        this.int = 0;
        this.C = 0;
        this.u = 0;
        this.L = 0;
        this.T = this.getGraphics().getFontMetrics();
        this.G = null;
        this.if = new Vector();
        this.F = new Vector();
        this.k = new Vector();
        this.H = new Vector();
        this.e = new Vector();
        this.n = new Vector();
        final Enumeration<String> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final String nextElement = elements.nextElement();
            if (((Hashtable<String, Object>)nextElement).getClass().getName().equals("java.lang.String")) {
                String substring = nextElement;
                if (substring.equals(" ") && this.goto == 0) {
                    continue;
                }
                while (this.case + this.C + this.T.stringWidth(substring) + this.int > this.L) {
                    int n;
                    int n2;
                    for (n = this.L - this.case - this.C - this.goto - this.int, n2 = 1; this.T.stringWidth(substring.substring(0, n2)) < n; ++n2) {}
                    --n2;
                    final String substring2 = substring.substring(0, n2);
                    substring = substring.substring(n2);
                    this.a(substring2, this.T.stringWidth(substring2), this.T.getHeight(), "text");
                    this.new();
                }
                final int stringWidth = this.T.stringWidth(substring);
                if (!substring.equals(" ") && this.case + this.C + this.goto + stringWidth + this.int > this.L) {
                    this.new();
                }
                this.a(substring, stringWidth, this.T.getHeight(), "text");
                this.goto += stringWidth;
            }
            else {
                final Hashtable<String, Object> g = (Hashtable<String, Object>)nextElement;
                if (this.a(g, "section-header")) {
                    this.new();
                    this.D = 0;
                    this.case = 0;
                    this.int = 0;
                    this.C = 0;
                    this.u = 0;
                    int intValue = (int)this.a(g, "margin-left");
                    int intValue2 = (int)this.a(g, "margin-right");
                    int intValue3 = (int)this.a(g, "margin-top");
                    int intValue4 = (int)this.a(g, "margin-bottom");
                    final int intValue5 = (int)this.a(g, "border-width");
                    final String s = (String)this.a(g, "border-type");
                    if (s.equalsIgnoreCase("full")) {
                        intValue += intValue5;
                        intValue2 += intValue5;
                        intValue3 += intValue5;
                        intValue4 += intValue5;
                    }
                    if (s.equalsIgnoreCase("vertical")) {
                        intValue += intValue5;
                        intValue2 += intValue5;
                    }
                    if (s.equalsIgnoreCase("horizontal")) {
                        intValue3 += intValue5;
                        intValue4 += intValue5;
                    }
                    g.put("margin-left", new Integer(intValue));
                    g.put("margin-right", new Integer(intValue2));
                    g.put("margin-top", new Integer(intValue3));
                    g.put("margin-bottom", new Integer(intValue4));
                    this.L = this.K - intValue - intValue2;
                }
                if (g.containsKey("padding-top")) {
                    this.new();
                    final int intValue6 = (int)this.a(g, "padding-top");
                    if (intValue6 > 0) {
                        this.a(new Rectangle(), 0, intValue6, "padding");
                        this.new();
                    }
                    else {
                        this.D += intValue6;
                    }
                }
                this.case = (int)this.a(g, "padding-left");
                this.int = (int)this.a(g, "padding-right");
                final String s2 = (String)this.a(g, "align");
                int u = 0;
                if (s2.equalsIgnoreCase("center")) {
                    u = 1;
                }
                else if (s2.equalsIgnoreCase("right")) {
                    u = 2;
                }
                if (u != this.u) {
                    this.u = u;
                    this.new();
                }
                if (g.containsKey("url") || g.containsKey("load-main-message") || g.containsKey("load-main-style") || g.containsKey("load-temp-message") || g.containsKey("load-temp-style") || g.containsKey("link-section-name") || g.containsKey("link-next-section") || g.containsKey("link-previous-section")) {
                    this.G = g;
                }
                else {
                    this.G = null;
                }
                this.a(g, 0, 0, "proptable");
                final String s3 = (String)this.a(g, "text-font");
                int n3 = 0;
                if (this.a(g, "text-bold")) {
                    ++n3;
                }
                if (this.a(g, "text-italic")) {
                    n3 += 2;
                }
                final Font font = new Font(s3, n3, (int)this.a(g, "text-size"));
                this.a(font, 0, 0, "font");
                this.T = this.getFontMetrics(font);
                if (g.containsKey("rule-color")) {
                    final int intValue7 = (int)this.a(g, "rule-width");
                    final int intValue8 = (int)this.a(g, "rule-height");
                    final int u2 = this.u;
                    final String s4 = (String)this.a(g, "rule-align");
                    if (s4.equalsIgnoreCase("center")) {
                        this.u = 1;
                    }
                    else if (s4.equalsIgnoreCase("right")) {
                        this.u = 2;
                    }
                    else {
                        this.u = 0;
                    }
                    final int n4 = (this.L - this.case - this.C - this.int) * intValue7 / 100;
                    this.new();
                    this.a(g, n4, intValue8, "rule");
                    this.goto += n4;
                    this.new();
                    this.u = u2;
                }
                if (g.containsKey("bullet-indent")) {
                    if (g.containsKey("bullet-image")) {
                        this.new();
                        this.a(g, this.C = 0, ((Image)this.a(g, "bullet-image")).getHeight(this), "bullet-image");
                    }
                    if (g.containsKey("bullet-text")) {
                        this.new();
                        this.C = 0;
                        final String s5 = (String)this.a(g, "bullet-text");
                        this.a(g, 0, this.T.getHeight(), "bullet-text");
                    }
                    this.C = (int)this.a(g, "bullet-indent");
                }
                else {
                    if (this.C != 0) {
                        this.new();
                    }
                    this.C = 0;
                }
                if (g.containsKey("image")) {
                    final Image image = (Image)this.a(g, "image");
                    final int u3 = this.u;
                    if (g.containsKey("image-align")) {
                        final String s6 = (String)this.a(g, "image-align");
                        if (s6.equalsIgnoreCase("center")) {
                            this.u = 1;
                        }
                        else if (s6.equalsIgnoreCase("right")) {
                            this.u = 2;
                        }
                        else {
                            this.u = 0;
                        }
                    }
                    this.new();
                    this.a(g, image.getWidth(this), image.getHeight(this), "image");
                    this.goto += image.getWidth(this);
                    this.new();
                    this.u = u3;
                }
                if (this.a(g, "line-break")) {
                    this.new();
                }
                if (g.containsKey("padding-bottom")) {
                    this.new();
                    final int intValue9 = (int)this.a(g, "padding-bottom");
                    if (intValue9 > 0) {
                        this.a(new Rectangle(), 0, intValue9, "padding");
                        this.new();
                    }
                    else {
                        this.D += intValue9;
                    }
                }
                if (!g.containsKey("line-feed")) {
                    continue;
                }
                int intValue10 = (int)this.a(g, "line-feed");
                if (this.goto != 0) {
                    this.new();
                    --intValue10;
                }
                this.D += Math.max(intValue10, 0) * this.T.getHeight();
            }
        }
        this.new();
        this.X = this.if;
        this.s = this.F;
        this.h = this.k;
        this.a = this.H;
    }
    
    private final void a(final Object o, final int n, final int n2, final String s) {
        final Rectangle rectangle = new Rectangle(this.goto + this.case + this.C, -999, n, n2);
        this.if.addElement(o);
        this.F.addElement(rectangle);
        this.k.addElement(s);
        this.H.addElement(this.G);
        if (s.equals("text") || s.equals("bullet-text")) {
            this.e.addElement(new Integer(this.T.getAscent()));
        }
        else {
            this.e.addElement(new Integer(n2));
        }
        this.n.addElement(new Integer(this.u));
    }
    
    private final void new() {
        int n = 0;
        int max = 0;
        int max2 = 0;
        final int n2 = this.if.size() - 1;
        int n3 = 0;
        int n4 = n2;
        while (n2 - n3 >= 0 && ((Rectangle)this.F.elementAt(n2 - n3)).y == -999) {
            final Object element = this.if.elementAt(n2 - n3);
            if (((String)element).getClass().getName().equals("java.lang.String") && !((String)element).equals("")) {
                n4 = n2 - n3;
            }
            ++n3;
        }
        final int n5 = n2 - n3 + 1;
        if (n3 != 0) {
            int i = n2;
            while (i >= n5) {
                final Object element2 = this.if.elementAt(i);
                if (((String)element2).getClass().getName().equals("java.lang.String")) {
                    if (((String)element2).equals(" ")) {
                        this.if.setElementAt("", i);
                        ((Rectangle)this.F.elementAt(i)).width = 0;
                        break;
                    }
                    break;
                }
                else {
                    --i;
                }
            }
            for (int j = n5; j <= n2; ++j) {
                final Rectangle rectangle = this.F.elementAt(j);
                n += rectangle.width;
                max = Math.max(max, rectangle.height);
                max2 = Math.max(max2, (int)this.e.elementAt(j));
            }
            for (int k = n5; k <= n2; ++k) {
                final Rectangle rectangle2 = this.F.elementAt(k);
                rectangle2.y = this.D + max2 - this.e.elementAt(k);
                final Rectangle rectangle3 = rectangle2;
                rectangle3.x += this.n.elementAt(n4) * ((this.L - this.case - this.C - this.int - n) / 2);
            }
        }
        this.goto = 0;
        this.D += max;
    }
    
    private final void int() {
        this.char = new Vector();
        for (int i = 0; i < this.X.size(); ++i) {
            final Object element = this.X.elementAt(i);
            if (((Hashtable<?, ?>)element).getClass().getName().equals("java.util.Hashtable") && this.h.elementAt(i).equals("proptable") && (boolean)this.a(element, "section-header")) {
                this.char.addElement(new Integer(i));
            }
        }
        this.J = new Vector();
        for (int j = 1; j < this.char.size(); ++j) {
            this.J.addElement(new Integer((int)this.char.elementAt(j) - 1));
        }
        this.J.addElement(new Integer(this.X.size() - 1));
        this.S = this.char.size();
        this.I = new Vector();
        this.byte = new Vector();
        for (int k = 0; k < this.S; ++k) {
            final int intValue = this.char.elementAt(k);
            final int intValue2 = this.J.elementAt(k);
            int max = 0;
            for (int l = intValue; l <= intValue2; ++l) {
                final Rectangle rectangle = this.s.elementAt(l);
                max = Math.max(max, rectangle.y + rectangle.height);
            }
            this.I.addElement(new Integer(max));
            final Rectangle rectangle2 = new Rectangle(0, 0, this.K, this.else);
            rectangle2.x = (int)this.a(k, "margin-left");
            rectangle2.y = (int)this.a(k, "margin-top");
            rectangle2.width = this.K - rectangle2.x - (int)this.a(k, "margin-right");
            rectangle2.height = this.else - rectangle2.y - (int)this.a(k, "margin-bottom");
            this.byte.addElement(rectangle2);
        }
    }
    
    public final synchronized void loadMain(String r, String b) {
        if (r == null) {
            r = "";
        }
        if (b == null) {
            b = "";
        }
        if (!r.equals("")) {
            this.R = r;
        }
        if (!b.equals("")) {
            this.b = b;
        }
        this.Q = System.currentTimeMillis();
    }
    
    public final synchronized void loadTemp(String e, String b, final int n) {
        if (e == null) {
            e = "";
        }
        if (b == null) {
            b = "";
        }
        if (!e.equals("")) {
            if (b.equals("")) {
                b = this.b;
            }
            this.B = b;
            this.E = e;
            this.new = System.currentTimeMillis() + n;
            this.for = Long.MAX_VALUE;
        }
    }
    
    private static final void a(final Hashtable hashtable, final Hashtable hashtable2) {
        final Enumeration<String> keys = hashtable2.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            if (!hashtable.containsKey(s)) {
                hashtable.put(s, hashtable2.get(s));
            }
        }
    }
    
    private final void a(final String s, final int n, final Object o, final Boolean b, final Boolean b2) {
        this.W.put(s, new Integer(n));
        this.f.put(s, b);
        this.o.put(s, b2);
        if (o != null) {
            this.i.put(s, o);
        }
    }
    
    private final String a(String string) {
        for (int i = 0; i < this.P.length; ++i) {
            int n = -1;
            while (true) {
                final int index = string.indexOf(this.P[i][0]);
                if (index <= n) {
                    break;
                }
                n = index;
                string = String.valueOf(string.substring(0, n)) + this.P[i][1] + string.substring(n + this.P[i][0].length());
            }
        }
        return string;
    }
    
    private final String int(String string) {
        for (int i = 0; i < this.P.length; ++i) {
            int n = -1;
            while (true) {
                final int index = string.indexOf(this.P[i][1]);
                if (index <= n) {
                    break;
                }
                n = index;
                string = String.valueOf(string.substring(0, n)) + this.P[i][0] + string.substring(n + this.P[i][1].length());
            }
        }
        return string;
    }
    
    private final Object a(final int n, final String s) {
        return this.a(this.X.elementAt(this.char.elementAt(n)), s);
    }
    
    private final Object a(final Object o, final String s) {
        final Hashtable hashtable = (Hashtable)o;
        String s2 = "";
        if (this.try != null && (this.try == hashtable || hashtable.containsKey("section-header"))) {
            if (this.M) {
                s2 = "-click";
            }
            else {
                s2 = "-over";
            }
            if (s2.equals("-click") && !hashtable.containsKey(String.valueOf(s) + s2)) {
                s2 = "-over";
            }
            if (s2.equals("-over") && !hashtable.containsKey(String.valueOf(s) + s2)) {
                s2 = "";
            }
        }
        final String string = String.valueOf(s) + s2;
        final Object value = hashtable.get(string);
        if (value != null) {
            return value;
        }
        if (this.i.containsKey(string)) {
            return this.i.get(string);
        }
        return null;
    }
    
    private final void if() {
        this.a("image", 5, null, Boolean.FALSE, Boolean.TRUE);
        this.a("image-align", 2, new Integer(0), Boolean.FALSE, Boolean.FALSE);
        this.a("rule-color", 3, null, Boolean.FALSE, Boolean.TRUE);
        this.a("rule-width", 0, new Integer(100), Boolean.FALSE, Boolean.FALSE);
        this.a("rule-height", 0, new Integer(1), Boolean.FALSE, Boolean.FALSE);
        this.a("rule-align", 2, "center", Boolean.FALSE, Boolean.FALSE);
        this.a("text-font", 2, "Helvetica", Boolean.TRUE, Boolean.FALSE);
        this.a("text-size", 0, new Integer(12), Boolean.TRUE, Boolean.FALSE);
        this.a("text-bold", 1, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE);
        this.a("text-italic", 1, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE);
        this.a("text-underline", 1, Boolean.FALSE, Boolean.TRUE, Boolean.TRUE);
        this.a("text-color", 3, Color.black, Boolean.TRUE, Boolean.TRUE);
        this.a("bullet-indent", 0, new Integer(13), Boolean.TRUE, Boolean.FALSE);
        this.a("bullet-text", 2, null, Boolean.FALSE, Boolean.TRUE);
        this.a("bullet-image", 5, null, Boolean.FALSE, Boolean.TRUE);
        this.a("line-break", 1, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);
        this.a("line-feed", 0, null, Boolean.FALSE, Boolean.FALSE);
        this.a("padding-left", 0, new Integer(10), Boolean.TRUE, Boolean.FALSE);
        this.a("padding-right", 0, new Integer(10), Boolean.TRUE, Boolean.FALSE);
        this.a("align", 2, "left", Boolean.TRUE, Boolean.FALSE);
        this.a("vertical-align", 2, "top", Boolean.FALSE, Boolean.FALSE);
        this.a("padding-top", 0, new Integer(0), Boolean.FALSE, Boolean.FALSE);
        this.a("padding-bottom", 0, new Integer(0), Boolean.FALSE, Boolean.FALSE);
        this.a("url", 4, null, Boolean.TRUE, Boolean.FALSE);
        this.a("url-frame", 2, "_self", Boolean.TRUE, Boolean.FALSE);
        this.a("load-main-message", 2, null, Boolean.TRUE, Boolean.FALSE);
        this.a("load-main-style", 2, null, Boolean.TRUE, Boolean.FALSE);
        this.a("load-temp-message", 2, null, Boolean.TRUE, Boolean.FALSE);
        this.a("load-temp-style", 2, null, Boolean.TRUE, Boolean.FALSE);
        this.a("link-section-name", 2, null, Boolean.TRUE, Boolean.FALSE);
        this.a("link-next-section", 1, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE);
        this.a("link-previous-section", 1, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE);
        this.a("section-header", 1, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);
        this.a("border-type", 2, "none", Boolean.FALSE, Boolean.TRUE);
        this.a("border-color", 3, Color.black, Boolean.FALSE, Boolean.TRUE);
        this.a("border-width", 0, new Integer(1), Boolean.FALSE, Boolean.TRUE);
        this.a("background-color", 3, Color.white, Boolean.FALSE, Boolean.TRUE);
        this.a("background-image", 5, null, Boolean.FALSE, Boolean.TRUE);
        this.a("background-image-x", 0, new Integer(0), Boolean.FALSE, Boolean.TRUE);
        this.a("background-image-y", 0, new Integer(0), Boolean.FALSE, Boolean.TRUE);
        this.a("background-image-repeat", 1, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE);
        this.a("foreground-image", 5, null, Boolean.FALSE, Boolean.TRUE);
        this.a("foreground-image-x", 0, new Integer(0), Boolean.FALSE, Boolean.TRUE);
        this.a("foreground-image-y", 0, new Integer(0), Boolean.FALSE, Boolean.TRUE);
        this.a("margin-left", 0, new Integer(0), Boolean.FALSE, Boolean.TRUE);
        this.a("margin-top", 0, new Integer(0), Boolean.FALSE, Boolean.TRUE);
        this.a("margin-right", 0, new Integer(0), Boolean.FALSE, Boolean.TRUE);
        this.a("margin-bottom", 0, new Integer(0), Boolean.FALSE, Boolean.TRUE);
        this.a("section-name", 2, null, Boolean.FALSE, Boolean.FALSE);
        this.r = "<br Line-Feed='1'><b text-Bold='true'><i text-italic='true'><u text-Underline='true'><h text-Size='14' text-Color='800000' text-Bold='true' Line-Break='true'></h Line-Break='true'><link text-Underline='true' text-Color='2222d0' text-Color-Over='dd0000' text-Color-Click='500000'><default background-color='ffffff'>";
    }
    
    private final String do(String s) {
        s = s.trim();
        if (s.length() >= 5 && s.substring(0, 5).equalsIgnoreCase("file:")) {
            final String substring = s.substring(5);
            try {
                final URLConnection openConnection = new URL(this.getDocumentBase(), substring).openConnection();
                openConnection.setDoInput(true);
                openConnection.setUseCaches(false);
                openConnection.getInputStream();
                final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
                s = "";
                while (true) {
                    final String line = dataInputStream.readLine();
                    if (line == null) {
                        break;
                    }
                    s = String.valueOf(s) + line + "\n";
                }
                dataInputStream.close();
            }
            catch (Exception ex) {
                this.m = String.valueOf(this.m) + "<b>Error 12: </b>Unable to load file:<br>";
                this.m = String.valueOf(this.m) + "'" + substring + "'<br>";
                s = "";
            }
        }
        return s;
    }
    
    private final Vector a(String s, final Hashtable hashtable) {
        s = this.for(s);
        final Hashtable<?, ?> hashtable2 = hashtable.get("default");
        final Vector vector = new Vector<String>();
        Hashtable<?, ?> a = hashtable2;
        Vector<String> vector2 = new Vector<String>();
        Vector<Hashtable<?, ?>> vector3 = new Vector<Hashtable<?, ?>>();
        int n = 0;
        int i;
        do {
            i = s.indexOf("<");
            int length;
            if (i == -1) {
                length = s.length();
            }
            else {
                length = i;
            }
            final String substring = s.substring(0, length);
            s = s.substring(length);
            if (!substring.equals("")) {
                final StringTokenizer stringTokenizer = new StringTokenizer(substring, " \t\r\n\f", true);
                while (stringTokenizer.hasMoreTokens()) {
                    final String nextToken = stringTokenizer.nextToken();
                    switch (nextToken.charAt(0)) {
                        case '\t':
                        case '\n':
                        case '\f':
                        case '\r':
                        case ' ': {
                            if (n == 0) {
                                vector.addElement(" ");
                            }
                            n = 1;
                            continue;
                        }
                        default: {
                            vector.addElement(this.a(nextToken));
                            n = 0;
                            continue;
                        }
                    }
                }
            }
            if (i != -1) {
                final Object[] if1 = this.if(s);
                final String s2 = (String)if1[0];
                final Hashtable hashtable3 = (Hashtable)if1[1];
                s = (String)if1[2];
                if (s2.startsWith("/")) {
                    final String trim = s2.substring(1).trim();
                    int lastIndex;
                    if (trim.equals("")) {
                        lastIndex = vector2.size() - 1;
                    }
                    else {
                        lastIndex = vector2.lastIndexOf(trim);
                    }
                    Hashtable<?, ?> hashtable4;
                    if (lastIndex != -1) {
                        hashtable4 = vector3.elementAt(lastIndex);
                        for (int j = vector2.size() - 1; j >= lastIndex; --j) {
                            vector2.removeElementAt(j);
                            vector3.removeElementAt(j);
                        }
                    }
                    else {
                        hashtable4 = new Hashtable<Object, Object>();
                    }
                    a(hashtable3, this.a((Hashtable)hashtable4.clone()));
                    if (hashtable.containsKey("/" + trim)) {
                        a(hashtable3, hashtable.get("/" + trim));
                    }
                }
                else {
                    if (!s2.equals("") && hashtable.containsKey(s2)) {
                        a(hashtable3, hashtable.get(s2));
                    }
                    if (this.a(hashtable3, "section-header")) {
                        a = hashtable2;
                        vector2 = new Vector<String>();
                        vector3 = new Vector<Hashtable<?, ?>>();
                    }
                    a(hashtable3, a);
                    vector2.addElement(s2);
                    vector3.addElement(a);
                }
                a = (Hashtable<?, ?>)this.a((Hashtable)hashtable3.clone());
                vector.addElement((String)hashtable3);
            }
        } while (i != -1);
        if (vector.size() != 0) {
            if (!vector.firstElement().getClass().getName().equals("java.util.Hashtable")) {
                vector.insertElementAt((String)hashtable2.clone(), 0);
            }
            ((Hashtable)vector.firstElement()).put("section-header", Boolean.TRUE);
        }
        return vector;
    }
    
    private final Object[] if(String s) {
        s = s.trim();
        final int index = s.indexOf(">");
        if (index == -1 || !s.startsWith("<")) {
            this.m = String.valueOf(this.m) + "<b>Error 02: </b>Missing end of style declaration:<br>";
            this.m = String.valueOf(this.m) + "'" + this.int(s) + "'<br>";
            return new Object[] { "", new Hashtable(), "" };
        }
        String s2 = s.substring(1, index).trim();
        s = s.substring(index + 1);
        if (s2.endsWith("/") && s2.length() > 1) {
            s2 = s2.substring(0, s2.length() - 1);
        }
        String lowerCase = "";
        final Hashtable<String, Object> hashtable = new Hashtable<String, Object>();
        int n = 0;
        while (!s2.trim().equals("")) {
            ++n;
            final String nextToken = new StringTokenizer(s2).nextToken(" \t\f\n\r=");
            s2 = s2.substring(nextToken.length()).trim();
            if (!s2.startsWith("=") && n == 1) {
                lowerCase = nextToken.trim().toLowerCase();
            }
            else {
                final String lowerCase2 = nextToken.trim().toLowerCase();
                final String trim;
                s2 = (trim = s2.substring(1).trim());
                String substring = "";
                boolean b = false;
                int n2 = -1;
                if (s2.startsWith("\"")) {
                    b = true;
                    s2 = s2.substring(1);
                    n2 = s2.indexOf("\"");
                }
                else if (s2.startsWith("'")) {
                    s2 = s2.substring(1);
                    n2 = s2.indexOf("'");
                    b = true;
                }
                if (n2 != -1) {
                    substring = s2.substring(0, n2);
                    s2 = s2.substring(n2 + 1).trim();
                }
                if (!b || n2 == -1) {
                    this.m = String.valueOf(this.m) + "<b>Error 03: </b>Missing delimiter character (' or \") for property value of ";
                    this.m = String.valueOf(this.m) + "<i>" + this.int(lowerCase2) + "</i>:<br>";
                    this.m = String.valueOf(this.m) + this.int(trim.substring(0, Math.min(trim.length(), 10))) + "...<br>";
                    break;
                }
                String s3 = lowerCase2;
                if (lowerCase2.endsWith("-over")) {
                    s3 = lowerCase2.substring(0, lowerCase2.length() - 5);
                }
                if (lowerCase2.endsWith("-click")) {
                    s3 = lowerCase2.substring(0, lowerCase2.length() - 6);
                }
                if (this.o.containsKey(s3) && !(boolean)this.o.get(s3)) {
                    s3 = lowerCase2;
                }
                if (!this.W.containsKey(s3)) {
                    this.m = String.valueOf(this.m) + "<b>Error 04: </b>Unknown property name <i>" + lowerCase2 + "</i>.<br>";
                }
                else {
                    final String a = this.a(substring);
                    final int intValue = this.W.get(s3);
                    Object o = null;
                    try {
                        switch (intValue) {
                            case 0: {
                                o = new Integer(Integer.parseInt(a));
                                break;
                            }
                            case 1: {
                                o = new Boolean(a);
                                break;
                            }
                            case 2: {
                                o = a;
                                break;
                            }
                            case 3: {
                                o = new Color(Integer.parseInt(a, 16));
                                break;
                            }
                            case 4:
                            case 5: {
                                o = new URL(this.getDocumentBase(), a);
                                break;
                            }
                        }
                    }
                    catch (Exception ex) {
                        this.m = String.valueOf(this.m) + "<b>Error 05: </b>Invalid " + AcuteShifter.q[intValue] + " value for <i>" + lowerCase2 + "</i>:<br>";
                        this.m = String.valueOf(this.m) + "'" + this.int(a) + "'<br>";
                        continue;
                    }
                    hashtable.put(lowerCase2, o);
                }
            }
        }
        return new Object[] { lowerCase, hashtable, s };
    }
    
    private final Hashtable new(String s) {
        s = this.for(s);
        final Hashtable<String, Hashtable> hashtable = new Hashtable<String, Hashtable>();
        int index;
        while ((index = s.indexOf("<")) != -1) {
            s = s.substring(index);
            final Object[] if1 = this.if(s);
            final String s2 = (String)if1[0];
            final Hashtable hashtable2 = (Hashtable)if1[1];
            s = (String)if1[2];
            if (!s2.equals("") && !hashtable2.isEmpty()) {
                hashtable.put(s2, hashtable2);
            }
        }
        return hashtable;
    }
    
    private final Hashtable a(final Hashtable hashtable) {
        final Enumeration<String> keys = hashtable.keys();
        final Vector<String> vector = new Vector<String>();
        while (keys.hasMoreElements()) {
            vector.addElement(keys.nextElement());
        }
        final Enumeration<String> elements = vector.elements();
        while (elements.hasMoreElements()) {
            String s2;
            final String s = s2 = elements.nextElement();
            if (s2.endsWith("-over")) {
                s2 = s.substring(0, s.length() - 5);
            }
            if (s.endsWith("-click")) {
                s2 = s.substring(0, s.length() - 6);
            }
            if (!(boolean)this.f.get(s2)) {
                hashtable.remove(s);
            }
        }
        return hashtable;
    }
    
    private final String for(String string) {
        for (int i = 0; i < this.l.length; ++i) {
            int index;
            while ((index = string.indexOf(this.l[i][0])) != -1) {
                final int index2 = string.indexOf(this.l[i][1], index);
                if (index2 == -1) {
                    this.m = String.valueOf(this.m) + "<b>Error 01: </b>Missing end of block started with ";
                    this.m = String.valueOf(this.m) + "'" + this.int(this.l[i][0]) + "'.<br>";
                    return string.substring(0, index);
                }
                string = String.valueOf(string.substring(0, index)) + string.substring(index2 + this.l[i][1].length());
            }
        }
        return string;
    }
    
    public final void paint(final Graphics graphics) {
        if (!System.getProperty("java.version").startsWith("1.0")) {
            int n = 0;
            if (this.g) {
                n = 3;
            }
            else if (this.try != null) {
                n = 12;
            }
            this.setCursor(new Cursor(n));
        }
        if (this.g || this.null || this.v == null) {
            Color color = Color.white;
            Color white = new Color(5263440);
            String s = "";
            try {
                color = new Color(Integer.parseInt(this.getParameter("loading-background-color"), 16));
            }
            catch (Exception ex) {}
            try {
                white = new Color(Integer.parseInt(this.getParameter("loading-text-color"), 16));
            }
            catch (Exception ex2) {}
            if (this.g) {
                s = this.getParameter("loading-text");
                if (s == null) {
                    s = "(loading)";
                }
            }
            if (this.null) {
                s = String.valueOf(this.void) + "\nUnregistered version\nFor evaluation use only!\nwww.acuteapplets.com";
                color = Color.darkGray;
                white = Color.white;
            }
            final Graphics graphics2 = this.c.getGraphics();
            graphics2.setColor(color);
            graphics2.fillRect(0, 0, this.K, this.else);
            final Font font = new Font("Helvetica", 0, 9);
            graphics2.setFont(font);
            graphics2.setColor(white);
            final FontMetrics fontMetrics = graphics2.getFontMetrics(font);
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n");
            int n2 = this.else / 2 - fontMetrics.getHeight() * stringTokenizer.countTokens() / 2 + fontMetrics.getAscent();
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                graphics2.drawString(nextToken, (this.K - fontMetrics.stringWidth(nextToken)) / 2, n2);
                n2 += fontMetrics.getHeight();
            }
        }
        graphics.drawImage(this.c, 0, 0, this);
        this.getToolkit().sync();
    }
    
    private final void a(String do1, String do2) {
        this.m = "";
        do1 = this.do(do1);
        do2 = this.do(do2);
        if (do1.equals("")) {
            do1 = " ";
        }
        final Hashtable new1 = this.new(this.r);
        final Hashtable new2 = this.new(do2);
        final Enumeration<String> keys = new1.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            if (!new2.containsKey(s)) {
                new2.put(s, new1.get(s));
            }
            else {
                a(new2.get(s), new1.get(s));
            }
        }
        final Vector a = this.a(do1, new2);
        this.a(a);
        while (!this.byte()) {
            this.g = true;
            this.paint(this.getGraphics());
            try {
                Thread.sleep(20L);
            }
            catch (InterruptedException ex) {}
        }
        this.g = false;
        this.if(a);
        if (!this.m.equals("")) {
            this.if(this.a(this.m, new1));
        }
        this.int();
    }
    
    public final void run() {
        this.if();
        this.a("shift-in-effect", 2, "slide-left", Boolean.FALSE, Boolean.FALSE);
        this.a("shift-out-effect", 2, "none", Boolean.FALSE, Boolean.FALSE);
        this.a("shift-in-time", 0, new Integer(600), Boolean.FALSE, Boolean.FALSE);
        this.a("shift-out-time", 0, new Integer(600), Boolean.FALSE, Boolean.FALSE);
        this.a("shift-pause", 0, new Integer(5000), Boolean.FALSE, Boolean.FALSE);
        this.a("shift-away-pause", 0, new Integer(0), Boolean.FALSE, Boolean.FALSE);
        System.out.println(String.valueOf(this.void) + " (http://www.acuteapplets.com).");
        this.do();
        this.g = true;
        this.paint(this.getGraphics());
        this.for();
        this.loadMain(this.getParameter("Message"), this.getParameter("style"));
        String[] array = null;
        String[] array2 = null;
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        this.do = -1;
        int do1 = -1;
        String s = "";
        long n5 = 0L;
        long n6 = 0L;
        int n7 = -1;
        int n8 = -1;
        int n9 = 0;
        int n10 = 0;
        int n11 = 0;
        int n12 = 0;
        int n13 = 0;
        boolean b = this.M;
        while (true) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (n2 == 0) {
                array2 = this.a();
                if (array2 != null) {
                    array = array2;
                    n2 = 1;
                    n3 = 0;
                }
                else {
                    array2 = this.try();
                    if (array2 != null) {
                        n2 = 1;
                        n3 = 1;
                    }
                }
            }
            if (n2 != 0 && n == 0) {
                this.a(array2[0], array2[1]);
                n4 = n3;
                n2 = 0;
                n = 1;
                do1 = -1;
                n6 = 0L;
                s = "shift-out";
                n7 = -1;
                n8 = -1;
            }
            if ((n2 != 0 || this.do != -1) && s.equalsIgnoreCase("view")) {
                n6 = 0L;
            }
            if (n6 < System.currentTimeMillis()) {
                if (s.equalsIgnoreCase("shift-out")) {
                    if (n2 != 0) {
                        n = 0;
                    }
                    else if (n4 != 0 && do1 == this.S - 1) {
                        array2 = array;
                        n3 = 0;
                        n2 = 1;
                        n = 0;
                        this.V = false;
                    }
                    else {
                        if (this.do == -1) {
                            do1 = (do1 + 1) % this.S;
                        }
                        else if (this.do == -2) {
                            do1 = (do1 + 1) % this.S;
                        }
                        else if (this.do == -3) {
                            if (--do1 == -1) {
                                do1 = this.S - 1;
                            }
                        }
                        else {
                            do1 = this.do;
                        }
                        this.do = -1;
                        s = "shift-in";
                        if (!((String)this.a(do1, "shift-in-effect")).equalsIgnoreCase("none")) {
                            n5 = System.currentTimeMillis();
                            if (n6 != 0L) {
                                final int intValue = (int)this.a(do1, "shift-away-pause");
                                if (intValue == -1) {
                                    n5 = Long.MAX_VALUE;
                                }
                                else {
                                    n5 += intValue;
                                }
                            }
                            n6 = n5 + (int)this.a(do1, "shift-in-time");
                            n7 = n8;
                            n8 = do1;
                        }
                    }
                }
                else if (s.equalsIgnoreCase("shift-in")) {
                    s = "view";
                    n5 = System.currentTimeMillis();
                    final int intValue2 = (int)this.a(do1, "shift-pause");
                    if (intValue2 == -1) {
                        n6 = Long.MAX_VALUE;
                    }
                    else {
                        n6 = n5 + intValue2;
                    }
                    n13 = 1;
                }
                else if (s.equalsIgnoreCase("view")) {
                    s = "shift-out";
                    if (!((String)this.a(do1, "shift-out-effect")).equalsIgnoreCase("none")) {
                        n5 = System.currentTimeMillis();
                        n6 = n5 + (int)this.a(do1, "shift-out-time");
                        n7 = do1;
                        n8 = -1;
                    }
                }
            }
            if (s.startsWith("shift") && n5 <= System.currentTimeMillis() && n6 > System.currentTimeMillis()) {
                final Rectangle rectangle = this.byte.elementAt(0);
                n9 = 0;
                n10 = 0;
                n11 = 0;
                n12 = 0;
                final double min = Math.min(1.0, (System.currentTimeMillis() - n5) / (n6 - 40L - n5));
                final String lowerCase = ((String)this.a(do1, String.valueOf(s) + "-effect")).toLowerCase();
                double n14;
                if (lowerCase.startsWith("fall")) {
                    n14 = min * min;
                }
                else if (lowerCase.startsWith("bounce")) {
                    n14 = 1.0 - Math.cos(min * 3.141592653589793 * 3.5) * (1.0 - min) * (1.0 - min);
                }
                else {
                    n14 = (1.0 - Math.cos(min * 3.141592653589793)) / 2.0;
                }
                final int n15 = (int)(n14 * rectangle.width);
                final int n16 = (int)(n14 * rectangle.height);
                if (lowerCase.endsWith("-right")) {
                    n9 = n15;
                    n11 = -rectangle.width + n15;
                }
                else if (lowerCase.endsWith("-left")) {
                    n9 = -n15;
                    n11 = rectangle.width - n15;
                }
                else if (lowerCase.endsWith("-down")) {
                    n10 = n16;
                    n12 = -rectangle.height + n16;
                }
                else {
                    n10 = -n16;
                    n12 = rectangle.height - n16;
                }
                n13 = 1;
            }
            if (n5 <= System.currentTimeMillis() && n6 > System.currentTimeMillis()) {
                if (s.equalsIgnoreCase("view")) {
                    final Hashtable try1 = this.try;
                    this.try = null;
                    this.a(do1, 0, 0);
                    if (try1 != this.try) {
                        n13 = 1;
                    }
                }
                if (this.M != b && this.try != null) {
                    n13 = 1;
                    b = this.M;
                }
                if (n13 != 0) {
                    this.if(do1, this.A.getGraphics());
                    this.try = null;
                    if (s.equalsIgnoreCase("view")) {
                        this.a(do1, 0, 0);
                        this.a(do1, 0, 0, this.A.getGraphics());
                    }
                    else {
                        if (n7 != -1) {
                            this.a(n7, n9, n10);
                            this.a(n7, n9, n10, this.A.getGraphics());
                        }
                        if (n8 != -1) {
                            this.a(n8, n11, n10);
                            this.a(n8, n11, n12, this.A.getGraphics());
                        }
                    }
                    this.a(do1, this.A.getGraphics());
                    final Image c = this.c;
                    this.c = this.A;
                    this.A = c;
                    this.paint(this.getGraphics());
                }
                n13 = 0;
            }
            try {
                Thread.sleep(Math.max(10L, currentTimeMillis + 40L - System.currentTimeMillis()));
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public final void start() {
        if (this.v == null) {
            (this.v = new Thread(this)).start();
        }
    }
    
    public final void stop() {
        if (this.v != null) {
            this.v.stop();
            this.v = null;
        }
    }
    
    public final synchronized void unloadTemp(final int n) {
        if (this.V) {
            this.new = Long.MAX_VALUE;
            this.for = System.currentTimeMillis() + n;
        }
        else {
            this.new = Long.MAX_VALUE;
            this.for = Long.MAX_VALUE;
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
