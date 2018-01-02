import java.net.URLConnection;
import java.io.DataInputStream;
import java.awt.Point;
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

public final class AcuteScroller extends Applet implements Runnable
{
    private String char;
    private Image w;
    private Thread C;
    int K;
    int L;
    boolean t;
    boolean o;
    boolean F;
    boolean E;
    String else;
    String q;
    long u;
    String try;
    String e;
    long c;
    long v;
    boolean G;
    String new;
    String B;
    String long;
    private static final int case = 0;
    private static final int l = 1;
    private static final int void = 2;
    private static final int s = 3;
    private static final int D = 4;
    private static final int I = 5;
    private static final String[] p;
    private Hashtable if;
    private Hashtable S;
    private Hashtable f;
    private Hashtable int;
    private String O;
    String[][] n;
    String[][] A;
    MediaTracker U;
    Vector j;
    Vector i;
    Vector T;
    Vector V;
    Vector g;
    Vector Q;
    Vector r;
    Vector byte;
    Vector M;
    Vector R;
    Vector d;
    int H;
    int J;
    int do;
    int for;
    int h;
    int a;
    int b;
    FontMetrics N;
    Hashtable z;
    int P;
    Vector goto;
    Vector W;
    Vector k;
    Vector m;
    Hashtable null;
    
    static {
        p = new String[] { "integer", "boolean", "string", "color", "url", "image" };
    }
    
    public AcuteScroller() {
        this.char = "AcuteScroller v1.0";
        this.C = null;
        this.K = 0;
        this.L = 0;
        this.t = false;
        this.o = false;
        this.F = false;
        this.E = false;
        this.new = "";
        this.B = "";
        this.if = new Hashtable();
        this.S = new Hashtable();
        this.f = new Hashtable();
        this.int = new Hashtable();
        this.O = null;
        this.n = new String[][] { { "&amp;", "&" }, { "&lt;", "<" }, { "&gt;", ">" }, { "&apos;", "'" }, { "&quot;", "\"" }, { "&nbsp;", " " }, { "#amp;", "&" }, { "#lt;", "<" }, { "#gt;", ">" }, { "#apos;", "'" }, { "#quot;", "\"" }, { "#nbsp;", " " } };
        this.A = new String[][] { { "<!--", "-->" }, { "<![CDATA[", "]]>" }, { "<!", ">" }, { "<?", "?>" } };
        this.null = null;
    }
    
    private final void a() {
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
        this.E = true;
        this.paint(this.getGraphics());
        try {
            Thread.sleep(7000L);
        }
        catch (InterruptedException ex) {}
        this.E = false;
    }
    
    private final void a(final int n, final int n2, final int n3) {
        if (!this.o) {
            return;
        }
        final int intValue = this.goto.elementAt(n);
        for (int intValue2 = this.W.elementAt(n), i = intValue; i <= intValue2; ++i) {
            if (((Rectangle)this.T.elementAt(i)).inside(this.K - n2, this.L - n3)) {
                this.null = (Hashtable)this.g.elementAt(i);
                if (this.null != null) {
                    return;
                }
            }
        }
    }
    
    private final void a(final int n, final int n2, final int n3, final Graphics graphics) {
        final Rectangle rectangle = this.m.elementAt(n);
        graphics.clipRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        final int intValue = this.goto.elementAt(n);
        final int intValue2 = this.W.elementAt(n);
        boolean booleanValue = false;
        for (int i = intValue; i <= intValue2; ++i) {
            final Object element = this.i.elementAt(i);
            final String s = this.V.elementAt(i);
            if (s.equals("font")) {
                graphics.setFont((Font)element);
            }
            else if (s.equals("proptable")) {
                final Hashtable<?, ?> hashtable = (Hashtable<?, ?>)element;
                graphics.setColor((Color)this.a(hashtable, "text-color"));
                booleanValue = (boolean)this.a(hashtable, "text-underline");
            }
            else {
                final Rectangle rectangle2 = this.T.elementAt(i);
                final int n4 = rectangle2.x + n2;
                final int n5 = rectangle2.y + n3;
                final int height = rectangle2.height;
                final int width = rectangle2.width;
                if (new Rectangle(n4, n5, width, height).intersects(rectangle)) {
                    if (s.equals("text")) {
                        final String s2 = (String)element;
                        final int ascent = graphics.getFontMetrics().getAscent();
                        graphics.drawString(s2, n4, n5 + ascent);
                        if (booleanValue) {
                            graphics.drawLine(n4, n5 + 1 + ascent, n4 + width, n5 + 1 + ascent);
                        }
                    }
                    else if (s.equals("bullet-text")) {
                        graphics.drawString((String)this.a(element, "bullet-text"), n4, n5 + graphics.getFontMetrics().getAscent());
                    }
                    else if (s.equals("rule")) {
                        final Color color = graphics.getColor();
                        graphics.setColor((Color)this.a(element, "rule-color"));
                        graphics.fillRect(n4, n5, width, height);
                        graphics.setColor(color);
                    }
                    else if (s.equals("image")) {
                        graphics.drawImage((Image)this.a(element, "image"), n4, n5, this);
                    }
                    else if (s.equals("bullet-image")) {
                        graphics.drawImage((Image)this.a(element, "bullet-image"), n4, n5, this);
                    }
                }
            }
        }
    }
    
    private final void a(final int n, final Graphics graphics) {
        graphics.clipRect(0, 0, this.size().width, this.size().height);
        final Hashtable hashtable = this.i.elementAt(this.goto.elementAt(n));
        graphics.setColor((Color)this.a(hashtable, "background-color"));
        graphics.fillRect(0, 0, this.size().width, this.size().height);
        if (hashtable.containsKey("background-image")) {
            final Image image = (Image)this.a(hashtable, "background-image");
            final boolean booleanValue = (boolean)this.a(hashtable, "background-image-repeat");
            final int intValue = (int)this.a(hashtable, "background-image-x");
            final int intValue2 = (int)this.a(hashtable, "background-image-y");
            if (booleanValue) {
                int i = intValue;
                int j = intValue2;
                while (i < this.size().width) {
                    while (j < this.size().height) {
                        graphics.drawImage(image, i, j, this);
                        j += image.getHeight(this);
                    }
                    j = 0;
                    i += image.getWidth(this);
                }
            }
            else {
                graphics.drawImage(image, 0, 0, this);
            }
        }
    }
    
    private final void if(final int n, final Graphics graphics) {
        final int width = this.size().width;
        final int height = this.size().height;
        graphics.clipRect(0, 0, width, height);
        final Hashtable hashtable = this.i.elementAt(this.goto.elementAt(n));
        if (hashtable.containsKey("foreground-image")) {
            graphics.drawImage((Image)this.a(hashtable, "foreground-image"), (int)this.a(hashtable, "foreground-image-x"), (int)this.a(hashtable, "foreground-image-y"), this);
        }
        graphics.setColor((Color)this.a(hashtable, "border-color"));
        final String s = (String)this.a(hashtable, "border-type");
        final int intValue = (int)this.a(hashtable, "border-width");
        if (s.equalsIgnoreCase("full") || s.equalsIgnoreCase("vertical")) {
            for (int i = 0; i < intValue; ++i) {
                graphics.drawLine(i, 0, i, height - 1);
                graphics.drawLine(width - i - 1, 0, width - i - 1, height - 1);
            }
        }
        if (s.equalsIgnoreCase("full") || s.equalsIgnoreCase("horizontal")) {
            for (int j = 0; j < intValue; ++j) {
                graphics.drawLine(0, j, width - 1, j);
                graphics.drawLine(0, height - j - 1, width - 1, height - j - 1);
            }
        }
    }
    
    private final synchronized String[] if() {
        boolean b = false;
        final long currentTimeMillis = System.currentTimeMillis();
        if (this.G && this.v <= currentTimeMillis) {
            this.v = Long.MAX_VALUE;
            b = true;
        }
        if (this.u <= currentTimeMillis) {
            this.u = Long.MAX_VALUE;
            b = true;
        }
        if (b) {
            this.G = false;
            return new String[] { this.else, this.q };
        }
        return null;
    }
    
    private final synchronized String[] for() {
        if (this.c > System.currentTimeMillis()) {
            return null;
        }
        this.c = Long.MAX_VALUE;
        if (this.G && this.new.equalsIgnoreCase(this.try) && this.B.equalsIgnoreCase(this.e)) {
            return null;
        }
        this.G = true;
        this.new = this.try;
        this.B = this.e;
        return new String[] { this.try, this.e };
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 504: {
                this.o = true;
                break;
            }
            case 505: {
                this.o = false;
                break;
            }
            case 501: {
                this.t = true;
                break;
            }
            case 502: {
                this.t = false;
                if (this.null == null) {
                    break;
                }
                if (this.null.containsKey("url")) {
                    this.getAppletContext().showDocument(this.null.get("url"), (String)this.a(this.null, "url-frame"));
                    break;
                }
                if (this.null.containsKey("load-main-message") || this.null.containsKey("load-main-style")) {
                    String string = (String)this.a(this.null, "load-main-message");
                    if (string != null) {
                        string = "file:" + string;
                    }
                    String string2 = (String)this.a(this.null, "load-main-style");
                    if (string2 != null) {
                        string2 = "file:" + string2;
                    }
                    this.loadMain(string, string2);
                }
                if (this.null.containsKey("load-temp-message") || this.null.containsKey("load-temp-style")) {
                    String string3 = (String)this.a(this.null, "load-temp-message");
                    if (string3 != null) {
                        string3 = "file:" + string3;
                    }
                    String string4 = (String)this.a(this.null, "load-temp-style");
                    if (string4 != null) {
                        string4 = "file:" + string4;
                    }
                    this.loadTemp(string3, string4, 0);
                    break;
                }
                break;
            }
            case 503:
            case 506: {
                this.K = event.x;
                this.L = event.y;
                break;
            }
            case 403: {
                if (event.key == 1008) {
                    this.loadTemp(this.char, "", 0);
                    break;
                }
                break;
            }
        }
        return true;
    }
    
    private final boolean int() {
        if ((this.U.statusAll(true) & 0x1) != 0x0) {
            return false;
        }
        for (int i = 0; i < this.j.size(); ++i) {
            if ((this.U.statusID(i, false) & 0x4) != 0x0) {
                this.long = String.valueOf(this.long) + "<b>Error 11: </b>Unable to load image:<br>";
                this.long = String.valueOf(this.long) + "'" + this.do(((URL)this.j.elementAt(i)).toString()) + "'<br>";
            }
        }
        return true;
    }
    
    private final void a(final Vector vector) {
        this.U = new MediaTracker(this);
        this.j = new Vector();
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
                    if ((int)this.if.get(s2) == 5) {
                        final URL url = (URL)hashtable.get(s);
                        final Image image = this.getImage(url);
                        hashtable.put(s, image);
                        boolean b = false;
                        for (int j = 0; j < this.j.size(); ++j) {
                            if (this.j.elementAt(j).equals(url)) {
                                b = true;
                                break;
                            }
                        }
                        if (b) {
                            continue;
                        }
                        this.U.addImage(image, this.j.size());
                        this.j.addElement(url);
                    }
                }
            }
        }
    }
    
    public final void init() {
        System.out.println(String.valueOf(this.char) + " (http://www.acuteapplets.com).");
        this.new();
        this.a("scroll-break", 2, "half", Boolean.FALSE, Boolean.FALSE);
        this.a("scroll-pause", 0, new Integer(0), Boolean.FALSE, Boolean.FALSE);
        this.a("scroll-speed", 0, new Integer(1), Boolean.FALSE, Boolean.FALSE);
        this.w = this.createImage(this.size().width, this.size().height);
    }
    
    private final synchronized void byte() {
        this.else = "";
        this.q = "";
        this.u = Long.MAX_VALUE;
        this.try = "";
        this.e = "";
        this.c = Long.MAX_VALUE;
        this.v = Long.MAX_VALUE;
        this.G = false;
    }
    
    private final void if(final Vector vector) {
        this.H = 0;
        this.J = 0;
        this.do = 0;
        this.for = 0;
        this.h = 0;
        this.a = 0;
        this.b = 0;
        this.N = this.getGraphics().getFontMetrics();
        this.z = null;
        this.Q = new Vector();
        this.r = new Vector();
        this.byte = new Vector();
        this.M = new Vector();
        this.R = new Vector();
        this.d = new Vector();
        final Enumeration<String> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final String nextElement = elements.nextElement();
            if (((Hashtable<String, Object>)nextElement).getClass().getName().equals("java.lang.String")) {
                final String s = nextElement;
                if (s.equals(" ") && this.H == 0) {
                    continue;
                }
                String s2 = this.new(s);
                while (this.do + this.h + this.N.stringWidth(s2) + this.for > this.b) {
                    int n;
                    int n2;
                    for (n = this.b - this.do - this.h - this.H - this.for, n2 = 1; this.N.stringWidth(s2.substring(0, n2)) < n; ++n2) {}
                    --n2;
                    final String substring = s2.substring(0, n2);
                    s2 = s2.substring(n2);
                    this.a(substring, this.N.stringWidth(substring), this.N.getHeight(), "text");
                    this.do();
                }
                final int stringWidth = this.N.stringWidth(s2);
                if (!s2.equals(" ") && this.do + this.h + this.H + stringWidth + this.for > this.b) {
                    this.do();
                }
                this.a(s2, stringWidth, this.N.getHeight(), "text");
                this.H += stringWidth;
            }
            else {
                final Hashtable<String, Object> z = (Hashtable<String, Object>)nextElement;
                if (this.a(z, "section-header")) {
                    this.do();
                    this.J = 0;
                    this.do = 0;
                    this.for = 0;
                    this.h = 0;
                    this.a = 0;
                    int intValue = (int)this.a(z, "margin-left");
                    int intValue2 = (int)this.a(z, "margin-right");
                    int intValue3 = (int)this.a(z, "margin-top");
                    int intValue4 = (int)this.a(z, "margin-bottom");
                    final int intValue5 = (int)this.a(z, "border-width");
                    final String s3 = (String)this.a(z, "border-type");
                    if (s3.equalsIgnoreCase("full")) {
                        intValue += intValue5;
                        intValue2 += intValue5;
                        intValue3 += intValue5;
                        intValue4 += intValue5;
                    }
                    if (s3.equalsIgnoreCase("vertical")) {
                        intValue += intValue5;
                        intValue2 += intValue5;
                    }
                    if (s3.equalsIgnoreCase("horizontal")) {
                        intValue3 += intValue5;
                        intValue4 += intValue5;
                    }
                    z.put("margin-left", new Integer(intValue));
                    z.put("margin-right", new Integer(intValue2));
                    z.put("margin-top", new Integer(intValue3));
                    z.put("margin-bottom", new Integer(intValue4));
                    this.b = this.size().width - intValue - intValue2;
                }
                if (z.containsKey("padding-top")) {
                    this.do();
                    this.J += (int)this.a(z, "padding-top");
                }
                this.do = (int)this.a(z, "padding-left");
                this.for = (int)this.a(z, "padding-right");
                final String s4 = (String)this.a(z, "align");
                int a = 0;
                if (s4.equalsIgnoreCase("center")) {
                    a = 1;
                }
                else if (s4.equalsIgnoreCase("right")) {
                    a = 2;
                }
                if (a != this.a) {
                    this.a = a;
                    this.do();
                }
                if (z.containsKey("url") || z.containsKey("load-main-message") || z.containsKey("load-main-style") || z.containsKey("load-temp-message") || z.containsKey("load-temp-style")) {
                    this.z = z;
                }
                else {
                    this.z = null;
                }
                this.a(z, 0, 0, "proptable");
                final String s5 = (String)this.a(z, "text-font");
                int n3 = 0;
                if (this.a(z, "text-bold")) {
                    ++n3;
                }
                if (this.a(z, "text-italic")) {
                    n3 += 2;
                }
                final Font font = new Font(s5, n3, (int)this.a(z, "text-size"));
                this.a(font, 0, 0, "font");
                this.N = this.getFontMetrics(font);
                if (z.containsKey("rule-color")) {
                    final int intValue6 = (int)this.a(z, "rule-width");
                    final int intValue7 = (int)this.a(z, "rule-height");
                    final int a2 = this.a;
                    if (z.containsKey("rule-align")) {
                        final String s6 = (String)this.a(z, "rule-align");
                        if (s6.equalsIgnoreCase("center")) {
                            this.a = 1;
                        }
                        else if (s6.equalsIgnoreCase("right")) {
                            this.a = 2;
                        }
                        else {
                            this.a = 0;
                        }
                    }
                    final int n4 = (this.b - this.do - this.h - this.for) * intValue6 / 100;
                    this.do();
                    this.a(z, n4, intValue7, "rule");
                    this.H += n4;
                    this.do();
                    this.a = a2;
                }
                if (z.containsKey("bullet-indent")) {
                    if (z.containsKey("bullet-image")) {
                        this.do();
                        this.h = 0;
                        final Image image = (Image)this.a(z, "bullet-image");
                        this.a(z, image.getWidth(this), image.getHeight(this), "bullet-image");
                    }
                    if (z.containsKey("bullet-text")) {
                        this.do();
                        this.h = 0;
                        this.a(z, this.N.stringWidth((String)this.a(z, "bullet-text")), this.N.getHeight(), "bullet-text");
                    }
                    this.h = (int)this.a(z, "bullet-indent");
                }
                else {
                    if (this.h != 0) {
                        this.do();
                    }
                    this.h = 0;
                }
                if (z.containsKey("image")) {
                    final Image image2 = (Image)this.a(z, "image");
                    final int a3 = this.a;
                    if (z.containsKey("image-align")) {
                        final String s7 = (String)this.a(z, "image-align");
                        if (s7.equalsIgnoreCase("center")) {
                            this.a = 1;
                        }
                        else if (s7.equalsIgnoreCase("right")) {
                            this.a = 2;
                        }
                        else {
                            this.a = 0;
                        }
                    }
                    this.do();
                    this.a(z, image2.getWidth(this), image2.getHeight(this), "image");
                    this.H += image2.getWidth(this);
                    this.do();
                    this.a = a3;
                }
                if (this.a(z, "line-break")) {
                    this.do();
                }
                if (z.containsKey("padding-bottom")) {
                    this.do();
                    this.J += (int)this.a(z, "padding-bottom");
                }
                if (!z.containsKey("line-feed")) {
                    continue;
                }
                int intValue8 = (int)this.a(z, "line-feed");
                if (this.H != 0) {
                    this.do();
                    --intValue8;
                }
                this.J += Math.max(intValue8, 0) * this.N.getHeight();
            }
        }
        this.do();
        this.i = this.Q;
        this.T = this.r;
        this.V = this.byte;
        this.g = this.M;
    }
    
    private final void a(final Object o, final int n, final int n2, final String s) {
        final Rectangle rectangle = new Rectangle(this.H + this.do + this.h, -999, n, n2);
        this.Q.addElement(o);
        this.r.addElement(rectangle);
        this.byte.addElement(s);
        this.M.addElement(this.z);
        o.getClass();
        if (s.equals("text") || s.equals("bullet-text")) {
            this.R.addElement(new Integer(this.N.getAscent()));
        }
        else {
            this.R.addElement(new Integer(n2));
        }
        this.d.addElement(new Integer(this.a));
    }
    
    private final void do() {
        int n = 0;
        int max = 0;
        int max2 = 0;
        final int n2 = this.Q.size() - 1;
        int n3 = 0;
        int n4 = n2;
        while (n2 - n3 >= 0 && ((Rectangle)this.r.elementAt(n2 - n3)).y == -999) {
            final Object element = this.Q.elementAt(n2 - n3);
            if (((String)element).getClass().getName().equals("java.lang.String") && !((String)element).equals("")) {
                n4 = n2 - n3;
            }
            ++n3;
        }
        final int n5 = n2 - n3 + 1;
        if (n3 != 0) {
            int i = n2;
            while (i >= n5) {
                final Object element2 = this.Q.elementAt(i);
                if (((String)element2).getClass().getName().equals("java.lang.String")) {
                    if (((String)element2).equals(" ")) {
                        this.Q.setElementAt("", i);
                        ((Rectangle)this.r.elementAt(i)).width = 0;
                        break;
                    }
                    break;
                }
                else {
                    --i;
                }
            }
            for (int j = n5; j <= n2; ++j) {
                final Rectangle rectangle = this.r.elementAt(j);
                n += rectangle.width;
                max = Math.max(max, rectangle.height);
                max2 = Math.max(max2, (int)this.R.elementAt(j));
            }
            for (int k = n5; k <= n2; ++k) {
                final Rectangle rectangle2 = this.r.elementAt(k);
                rectangle2.y = this.J + max2 - this.R.elementAt(k);
                final Rectangle rectangle3 = rectangle2;
                rectangle3.x += this.d.elementAt(n4) * ((this.b - this.do - this.h - this.for - n) / 2);
            }
        }
        this.H = 0;
        this.J += max;
    }
    
    private final void try() {
        this.goto = new Vector();
        for (int i = 0; i < this.i.size(); ++i) {
            final Object element = this.i.elementAt(i);
            if (((Hashtable<?, ?>)element).getClass().getName().equals("java.util.Hashtable") && this.V.elementAt(i).equals("proptable") && (boolean)this.a(element, "section-header")) {
                this.goto.addElement(new Integer(i));
            }
        }
        this.W = new Vector();
        for (int j = 1; j < this.goto.size(); ++j) {
            this.W.addElement(new Integer((int)this.goto.elementAt(j) - 1));
        }
        this.W.addElement(new Integer(this.i.size() - 1));
        this.P = this.goto.size();
        this.k = new Vector();
        this.m = new Vector();
        for (int k = 0; k < this.P; ++k) {
            final int intValue = this.goto.elementAt(k);
            final int intValue2 = this.W.elementAt(k);
            final Hashtable hashtable = this.i.elementAt(intValue);
            int max = 0;
            for (int l = intValue; l <= intValue2; ++l) {
                final Rectangle rectangle = this.T.elementAt(l);
                max = Math.max(max, rectangle.y + rectangle.height);
            }
            this.k.addElement(new Integer(max));
            final Rectangle rectangle2 = new Rectangle(new Point(0, 0), this.size());
            rectangle2.x = (int)this.a(hashtable, "margin-left");
            rectangle2.y = (int)this.a(hashtable, "margin-top");
            rectangle2.width = this.size().width - rectangle2.x - (int)this.a(hashtable, "margin-right");
            rectangle2.height = this.size().height - rectangle2.y - (int)this.a(hashtable, "margin-bottom");
            this.m.addElement(rectangle2);
        }
    }
    
    public final synchronized void loadMain(String else1, String q) {
        if (else1 == null) {
            else1 = "";
        }
        if (q == null) {
            q = "";
        }
        if (!else1.equals("")) {
            this.else = else1;
        }
        if (!q.equals("")) {
            this.q = q;
        }
        this.u = System.currentTimeMillis();
    }
    
    public final synchronized void loadTemp(String try1, String q, final int n) {
        if (try1 == null) {
            try1 = "";
        }
        if (q == null) {
            q = "";
        }
        if (!try1.equals("")) {
            if (q.equals("")) {
                q = this.q;
            }
            this.e = q;
            this.try = try1;
            this.c = System.currentTimeMillis() + n;
            this.v = Long.MAX_VALUE;
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
        this.if.put(s, new Integer(n));
        this.S.put(s, b);
        this.int.put(s, b2);
        if (o != null) {
            this.f.put(s, o);
        }
    }
    
    private final String new(String string) {
        for (int i = 0; i < this.n.length; ++i) {
            int n = -1;
            while (true) {
                final int index = string.indexOf(this.n[i][0]);
                if (index <= n) {
                    break;
                }
                n = index;
                string = String.valueOf(string.substring(0, n)) + this.n[i][1] + string.substring(n + this.n[i][0].length());
            }
        }
        return string;
    }
    
    private final String do(String string) {
        for (int i = 0; i < this.n.length; ++i) {
            int n = -1;
            while (true) {
                final int index = string.indexOf(this.n[i][1]);
                if (index <= n) {
                    break;
                }
                n = index;
                string = String.valueOf(string.substring(0, n)) + this.n[i][0] + string.substring(n + this.n[i][1].length());
            }
        }
        return string;
    }
    
    private final Object a(final Object o, final String s) {
        final Hashtable hashtable = (Hashtable)o;
        String s2 = "";
        if (this.null != null && (this.null == hashtable || hashtable.containsKey("section-header"))) {
            if (this.t) {
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
        if (this.f.containsKey(string)) {
            return this.f.get(string);
        }
        return null;
    }
    
    private final void new() {
        this.a("image", 5, null, Boolean.FALSE, Boolean.TRUE);
        this.a("image-align", 2, new Integer(0), Boolean.FALSE, Boolean.FALSE);
        this.a("rule-color", 3, null, Boolean.FALSE, Boolean.TRUE);
        this.a("rule-width", 0, new Integer(100), Boolean.FALSE, Boolean.FALSE);
        this.a("rule-height", 0, new Integer(1), Boolean.FALSE, Boolean.FALSE);
        this.a("rule-align", 2, new Integer(0), Boolean.FALSE, Boolean.FALSE);
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
        this.a("padding-left", 0, new Integer(0), Boolean.TRUE, Boolean.FALSE);
        this.a("padding-right", 0, new Integer(0), Boolean.TRUE, Boolean.FALSE);
        this.a("align", 2, "left", Boolean.TRUE, Boolean.FALSE);
        this.a("padding-top", 0, new Integer(0), Boolean.FALSE, Boolean.FALSE);
        this.a("padding-bottom", 0, new Integer(0), Boolean.FALSE, Boolean.FALSE);
        this.a("url", 4, null, Boolean.TRUE, Boolean.FALSE);
        this.a("url-frame", 2, "_self", Boolean.TRUE, Boolean.FALSE);
        this.a("load-main-message", 2, null, Boolean.TRUE, Boolean.FALSE);
        this.a("load-main-style", 2, null, Boolean.TRUE, Boolean.FALSE);
        this.a("load-temp-message", 2, null, Boolean.TRUE, Boolean.FALSE);
        this.a("load-temp-style", 2, null, Boolean.TRUE, Boolean.FALSE);
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
        this.a("margin-left", 0, new Integer(10), Boolean.FALSE, Boolean.TRUE);
        this.a("margin-top", 0, new Integer(0), Boolean.FALSE, Boolean.TRUE);
        this.a("margin-right", 0, new Integer(10), Boolean.FALSE, Boolean.TRUE);
        this.a("margin-bottom", 0, new Integer(0), Boolean.FALSE, Boolean.TRUE);
        this.O = "<br Line-Feed='1'><b text-Bold='true'><i text-italic='true'><u text-Underline='true'><h text-Size='14' text-Color='800000' text-Bold='true' Line-Break='true'></h Line-Break='true'><link text-Underline='true' text-Color='2222d0' text-Color-Over='dd0000' text-Color-Click='500000'><default background-color='ffffff'>";
    }
    
    private final String int(String s) {
        s = s.trim();
        if (s.length() >= 5 && s.substring(0, 5).equalsIgnoreCase("file:")) {
            final String substring = s.substring(5);
            try {
                final URLConnection openConnection = new URL(this.getDocumentBase(), substring).openConnection();
                openConnection.setDoInput(true);
                openConnection.setUseCaches(false);
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
                this.long = String.valueOf(this.long) + "<b>Error 12: </b>Unable to load file:<br>";
                this.long = String.valueOf(this.long) + "'" + substring + "'<br>";
                s = "";
            }
        }
        return s;
    }
    
    private final Vector a(String s, final Hashtable hashtable) {
        s = this.a(s);
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
                            vector.addElement(nextToken);
                            n = 0;
                            continue;
                        }
                    }
                }
            }
            if (i != -1) {
                final Object[] for1 = this.for(s);
                final String s2 = (String)for1[0];
                final Hashtable hashtable3 = (Hashtable)for1[1];
                s = (String)for1[2];
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
    
    private final Object[] for(String s) {
        s = s.trim();
        final int index = s.indexOf(">");
        if (index == -1 || !s.startsWith("<")) {
            this.long = String.valueOf(this.long) + "<b>Error 02: </b>Missing end of style declaration:<br>";
            this.long = String.valueOf(this.long) + "'" + this.do(s) + "'<br>";
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
                    this.long = String.valueOf(this.long) + "<b>Error 03: </b>Missing delimiter character (' or \") for property value of ";
                    this.long = String.valueOf(this.long) + "<i>" + this.do(lowerCase2) + "</i>:<br>";
                    this.long = String.valueOf(this.long) + this.do(trim.substring(0, Math.min(trim.length(), 10))) + "...<br>";
                    break;
                }
                String s3 = lowerCase2;
                if (lowerCase2.endsWith("-over")) {
                    s3 = lowerCase2.substring(0, lowerCase2.length() - 5);
                }
                if (lowerCase2.endsWith("-click")) {
                    s3 = lowerCase2.substring(0, lowerCase2.length() - 6);
                }
                if (this.int.containsKey(s3) && !(boolean)this.int.get(s3)) {
                    s3 = lowerCase2;
                }
                if (!this.if.containsKey(s3)) {
                    this.long = String.valueOf(this.long) + "<b>Error 04: </b>Unknown property name <i>" + lowerCase2 + "</i>.<br>";
                }
                else {
                    final String new1 = this.new(substring);
                    final int intValue = this.if.get(s3);
                    Object o = null;
                    try {
                        switch (intValue) {
                            case 0: {
                                o = new Integer(Integer.parseInt(new1));
                                break;
                            }
                            case 1: {
                                o = new Boolean(new1);
                                break;
                            }
                            case 2: {
                                o = new1;
                                break;
                            }
                            case 3: {
                                o = new Color(Integer.parseInt(new1, 16));
                                break;
                            }
                            case 4:
                            case 5: {
                                o = new URL(this.getDocumentBase(), new1);
                                break;
                            }
                        }
                    }
                    catch (Exception ex) {
                        this.long = String.valueOf(this.long) + "<b>Error 05: </b>Invalid " + AcuteScroller.p[intValue] + " value for <i>" + lowerCase2 + "</i>:<br>";
                        this.long = String.valueOf(this.long) + "'" + this.do(new1) + "'<br>";
                        continue;
                    }
                    hashtable.put(lowerCase2, o);
                }
            }
        }
        return new Object[] { lowerCase, hashtable, s };
    }
    
    private final Hashtable if(String s) {
        s = this.a(s);
        final Hashtable<String, Hashtable> hashtable = new Hashtable<String, Hashtable>();
        int index;
        while ((index = s.indexOf("<")) != -1) {
            s = s.substring(index);
            final Object[] for1 = this.for(s);
            final String s2 = (String)for1[0];
            final Hashtable hashtable2 = (Hashtable)for1[1];
            s = (String)for1[2];
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
            if (!(boolean)this.S.get(s2)) {
                hashtable.remove(s);
            }
        }
        return hashtable;
    }
    
    private final String a(String string) {
        for (int i = 0; i < this.A.length; ++i) {
            int index;
            while ((index = string.indexOf(this.A[i][0])) != -1) {
                final int index2 = string.indexOf(this.A[i][1], index);
                if (index2 == -1) {
                    this.long = String.valueOf(this.long) + "<b>Error 01: </b>Missing end of block started with ";
                    this.long = String.valueOf(this.long) + "'" + this.do(this.A[i][0]) + "'.<br>";
                    return string.substring(0, index);
                }
                string = String.valueOf(string.substring(0, index)) + string.substring(index2 + this.A[i][1].length());
            }
        }
        return string;
    }
    
    public final void paint(final Graphics graphics) {
        if (this.F || this.E || this.C == null) {
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
            if (this.F) {
                s = this.getParameter("loading-text");
                if (s == null) {
                    s = "(loading)";
                }
            }
            if (this.E) {
                s = String.valueOf(this.char) + "\nUnregistered version\nFor evaluation use only!\nwww.acuteapplets.com";
                color = Color.darkGray;
                white = Color.white;
            }
            final Graphics graphics2 = this.w.getGraphics();
            graphics2.setColor(color);
            graphics2.fillRect(0, 0, this.size().width, this.size().height);
            final Font font = new Font("Helvetica", 0, 9);
            graphics2.setFont(font);
            graphics2.setColor(white);
            final FontMetrics fontMetrics = graphics2.getFontMetrics(font);
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n");
            int n = this.size().height / 2 - fontMetrics.getHeight() * stringTokenizer.countTokens() / 2 + fontMetrics.getAscent();
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                graphics2.drawString(nextToken, (this.size().width - fontMetrics.stringWidth(nextToken)) / 2, n);
                n += fontMetrics.getHeight();
            }
        }
        graphics.drawImage(this.w, 0, 0, this);
    }
    
    private final void a(String int1, String int2) {
        this.long = "";
        int1 = this.int(int1);
        int2 = this.int(int2);
        if (int1.equals("")) {
            int1 = " ";
        }
        final Hashtable if1 = this.if(this.O);
        final Hashtable if2 = this.if(int2);
        final Enumeration<String> keys = if1.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            if (!if2.containsKey(s)) {
                if2.put(s, if1.get(s));
            }
            else {
                a(if2.get(s), if1.get(s));
            }
        }
        final Vector a = this.a(int1, if2);
        this.a(a);
        while (!this.int()) {
            this.F = true;
            this.paint(this.getGraphics());
            try {
                Thread.sleep(20L);
            }
            catch (InterruptedException ex) {}
        }
        this.F = false;
        this.if(a);
        if (!this.long.equals("")) {
            this.if(this.a(this.long, if1));
        }
        this.try();
    }
    
    public final void run() {
        this.a();
        this.F = true;
        this.paint(this.getGraphics());
        this.byte();
        this.loadMain(this.getParameter("Message"), this.getParameter("style"));
        String[] array = null;
        String[] array2 = null;
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        long n6 = 0L;
        int height = 0;
        int n7 = 0;
        boolean t = false;
        int l = 0;
        Rectangle rectangle = null;
        int n8 = 0;
        int n9 = 0;
        while (true) {
            if (n2 == 0) {
                array2 = this.if();
                if (array2 != null) {
                    array = array2;
                    n2 = 1;
                    n3 = 0;
                }
                else {
                    array2 = this.for();
                    if (array2 != null) {
                        n2 = 1;
                        n3 = 1;
                    }
                }
            }
            if (n2 != 0 && n == 0) {
                this.a(array2[0], array2[1]);
                rectangle = this.m.elementAt(0);
                n8 = 0;
                for (int i = 0; i < this.goto.size(); ++i) {
                    final String s = (String)this.a(this.i.elementAt(this.goto.elementAt(i)), "scroll-break");
                    int intValue = this.k.elementAt(i);
                    n9 = n8 + intValue;
                    if (s.equalsIgnoreCase("full")) {
                        intValue += this.m.elementAt(i).height;
                    }
                    else if (s.equalsIgnoreCase("none")) {
                        intValue += 0;
                    }
                    else {
                        intValue += Math.max(0, rectangle.height - intValue);
                    }
                    this.k.setElementAt(new Integer(intValue), i);
                    n8 += intValue;
                }
                n5 = 0;
                height = rectangle.height;
                n7 = 0;
                n4 = n3;
                n2 = 0;
                n = 1;
            }
            int j = height;
            int n10 = 0;
            this.null = null;
            int n11 = 0;
            int n12 = j;
            int n13 = 0;
            int n14 = 0;
            while (j < rectangle.height) {
                if (j >= 0 && n13 == 0) {
                    n11 = n10;
                    n12 = j;
                    n13 = 1;
                }
                n14 = n10;
                final int intValue2 = this.k.elementAt(n10);
                if (rectangle.inside(this.K, this.L) && j >= -intValue2) {
                    this.a(n10, rectangle.x + n7, rectangle.y + j);
                }
                j += intValue2;
                n10 = (n10 + 1) % this.goto.size();
            }
            final long currentTimeMillis = System.currentTimeMillis();
            this.a(n14, this.w.getGraphics());
            for (int n15 = height, n16 = 0; n15 < rectangle.height && n16 < this.goto.size(); n16 %= this.goto.size()) {
                if (n15 + (int)this.k.elementAt(n16) > 0) {
                    this.a(n16, rectangle.x + n7, rectangle.y + n15, this.w.getGraphics());
                }
                n15 += (int)this.k.elementAt(n16);
                ++n16;
                if (n4 == 0) {}
            }
            this.if(n14, this.w.getGraphics());
            this.getGraphics().drawImage(this.w, 0, 0, this);
            try {
                Thread.sleep(Math.max(10L, currentTimeMillis + 40L - System.currentTimeMillis()));
            }
            catch (InterruptedException ex) {}
            final int intValue3 = (int)this.a(this.i.elementAt(this.goto.elementAt(n11)), "scroll-pause");
            if (n12 == 0 && intValue3 != 0 && n5 == 0) {
                n6 = System.currentTimeMillis() + intValue3;
                n5 = 1;
            }
            if (n6 <= System.currentTimeMillis()) {
                n5 = 0;
            }
            if (n2 != 0) {
                if (n7 < rectangle.width) {
                    n7 += 30;
                }
                else {
                    n7 = 0;
                    n = 0;
                }
                n5 = 0;
            }
            else if (this.t) {
                if (t) {
                    final int n17 = this.L - l;
                    height += n17;
                    if (n17 != 0) {
                        n5 = 0;
                    }
                }
            }
            else if (n5 == 0) {
                int n18 = (int)this.a(this.i.elementAt(this.goto.elementAt(n14)), "scroll-speed");
                if (intValue3 != 0 && n12 >= 0) {
                    n18 = Math.max(Math.min(n18, n12), 1);
                }
                height -= n18;
                if (height < -n8 * 10 && n8 != 0) {
                    height = -(-height % n8) - n8 * 10;
                }
            }
            l = this.L;
            t = this.t;
            if (n4 != 0 && !this.t && height < -n9) {
                array2 = array;
                n3 = 0;
                n2 = 1;
                n = 0;
                this.G = false;
            }
        }
    }
    
    public final void start() {
        if (this.C == null) {
            (this.C = new Thread(this)).start();
        }
    }
    
    public final void stop() {
        if (this.C != null) {
            this.C.stop();
            this.C = null;
        }
    }
    
    public final synchronized void unloadTemp(final int n) {
        if (this.G) {
            this.c = Long.MAX_VALUE;
            this.v = System.currentTimeMillis() + n;
        }
        else {
            this.c = Long.MAX_VALUE;
            this.v = Long.MAX_VALUE;
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
