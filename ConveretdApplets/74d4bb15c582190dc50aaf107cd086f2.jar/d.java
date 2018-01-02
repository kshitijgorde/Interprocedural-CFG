import java.util.Hashtable;
import java.io.Writer;
import java.io.Reader;
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;
import java.util.Calendar;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.Component;
import java.awt.MediaTracker;
import java.util.Vector;
import java.awt.Color;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class d
{
    public static final String Xa;
    public static final long Ya = 86400000L;
    public static final String[] Za;
    private Applet _b;
    private e ab;
    private c bb;
    private boolean cb;
    private boolean db;
    private static String Ma = "\u3eeb\u3ef0\u3ee9\u3ee9";
    private static String Na = "\u3ee6\u3eea\u3eeb\u3ee3\u3eec\u3ee2";
    private static String Oa = "\u3ef7\u3ee0\u3ee4\u3ee1\u3eec\u3eeb\u3ee2\u3ea5";
    private static String Pa = "\u3ea5\u3eed\u3ee4\u3ef6\u3ea5\u3ee3\u3ee4\u3eec\u3ee9\u3ee0\u3ee1";
    private static String Ua = "\u3eed\u3ef1\u3ef1\u3ef5";
    private static String Va = "\u3ee6\u3eea\u3ef5\u3efc\u3ef7\u3eec\u3ee2\u3eed\u3ef1";
    private static String Wa = "\u3ee9\u3eec\u3ee6\u3ee0\u3eeb\u3ef6\u3ee0\u3ee0";
    private static String eb = "\u3ef7\u3ee0\u3ee6\u3eea\u3ee8\u3ee8\u3ee0\u3eeb\u3ee1\u3ee0\u3ee1\u3ea5\u3ef6\u3eec\u3eff\u3ee0\u3ebf\u3ea5\u3ef2\u3eec\u3ee1\u3ef1\u3eed\u3eb8";
    private static String fb = "\u3ea5\u3eed\u3ee0\u3eec\u3ee2\u3eed\u3ef1\u3eb8";
    private static String gb = "\u3ea5\u3ef3\u3ee4\u3ee9\u3ef0\u3ee0\u3ea5\u3eed\u3ee4\u3ef6\u3ea5\u3ef1\u3eea\u3ea5\u3ee7\u3ee0\u3ea5\u3ee7\u3ee0\u3ef1\u3ef2\u3ee0\u3ee0\u3eeb\u3ea5";
    private static String hb = "\u3ea5\u3ee4\u3eeb\u3ee1\u3ea5";
    private static String ib = "\u3ebe\u3ea5\u3ef0\u3ef6\u3eec\u3eeb\u3ee2\u3ea5\u3ee1\u3ee0\u3ee3\u3ee4\u3ef0\u3ee9\u3ef1";
    private static String jb = "\u3eec\u3ee9\u3ee9\u3ee0\u3ee2\u3ee4\u3ee9\u3ea5\u3ef3\u3ee4\u3ee9\u3ef0\u3ee0\u3ea5\u3ee3\u3eea\u3ef7\u3ea5";
    private static String kb = "\u3ea5\u3ef5\u3ee4\u3ef7\u3ee4\u3ee8\u3ee0\u3ef1\u3ee0\u3ef7\u3ea5\u3eec\u3ef6\u3ea5\u3ee8\u3eec\u3ef6\u3ef6\u3eec\u3eeb\u3ee2";
    private static String lb = "\u3efc";
    private static String mb = "\u3ef1";
    private static String nb = "\u3eb4";
    private static String ob = "\u3eea\u3eeb";
    private static String pb = "\u3eae";
    private static String qb = "\u3ef5\u3ee9\u3ee4\u3eec\u3eeb";
    private static String rb = "\u3ee7\u3eea\u3ee9\u3ee1";
    private static String sb = "\u3eec\u3ef1\u3ee4\u3ee9\u3eec\u3ee6";
    private static String tb = "\u3ee7\u3eea\u3ee9\u3ee1\u3eec\u3ef1\u3ee4\u3ee9\u3eec\u3ee6";
    private static String _ = "\u3eec\u3ee9\u3ee9\u3ee0\u3ee2\u3ee4\u3ee9\u3ea5\u3ef3\u3ee4\u3ee9\u3ef0\u3ee0\u3ea5\u3ee3\u3eea\u3ef7\u3ea5\u3ee3\u3eea\u3eeb\u3ef1\u3ef6\u3ef1\u3efc\u3ee9\u3ee0\u3ebf\u3ea5";
    private static String a = "\u3ef1\u3eec\u3ee8\u3ee0\u3ef6\u3ef7\u3eea\u3ee8\u3ee4\u3eeb";
    private static String b = "\u3ed1\u3eec\u3ee8\u3ee0\u3ef6\u3ed7\u3eea\u3ee8\u3ee4\u3eeb";
    private static String c = "\u3ee4\u3ef7\u3eec\u3ee4\u3ee9";
    private static String d = "\u3ec4\u3ef7\u3eec\u3ee4\u3ee9";
    private static String e = "\u3ee6\u3eea\u3ef0\u3ef7\u3eec\u3ee0\u3ef7";
    private static String f = "\u3ec6\u3eea\u3ef0\u3ef7\u3eec\u3ee0\u3ef7";
    private static String g = "\u3ee1\u3eec\u3ee4\u3ee9\u3eea\u3ee2";
    private static String h = "\u3ec1\u3eec\u3ee4\u3ee9\u3eea\u3ee2";
    private static String i = "\u3eec\u3ee9\u3ee9\u3ee0\u3ee2\u3ee4\u3ee9\u3ea5\u3ef3\u3ee4\u3ee9\u3ef0\u3ee0\u3ea5\u3ee3\u3eea\u3ef7\u3ea5\u3ee3\u3eea\u3eeb\u3ef1\u3ee3\u3ee4\u3ee6\u3ee0\u3ebf\u3ea5";
    private static String j = "\u3eec\u3ee8\u3ee4\u3ee2\u3ee0\u3ea8\u3ee9\u3eea\u3ee4\u3ee1\u3eec\u3eeb\u3ee2\u3ea5\u3eed\u3ee4\u3ef6\u3ea5\u3ee7\u3ee0\u3ee0\u3eeb\u3ea5\u3eec\u3eeb\u3ef1\u3ee0\u3ef7\u3ef7\u3ef0\u3ef5\u3ef1\u3ee0\u3ee1";
    private static String k = "\u3eea\u3eeb\u3ee0\u3ea5\u3eea\u3ef7\u3ea5\u3ee8\u3eea\u3ef7\u3ee0\u3ea5\u3eec\u3ee8\u3ee4\u3ee2\u3ee0\u3ef6\u3ea5\u3ee8\u3ee4\u3efc\u3ea5\u3eeb\u3eea\u3ef1\u3ea5\u3eed\u3ee4\u3ef3\u3ee0\u3ea5\u3ee9\u3eea\u3ee4\u3ee1\u3ee0\u3ee1\u3ea5\u3ee6\u3eea\u3ef7\u3ef7\u3ee0\u3ee6\u3ef1\u3ee9\u3efc";
    private static String l = "\u3eab\u3ee4\u3ef0";
    private static String m = "\u3eea\u3ef6\u3eab\u3eeb\u3ee4\u3ee8\u3ee0";
    private static String n = "\u3ed2\u3eec\u3eeb";
    private static String o = "\u3ee6\u3eea\u3ef0\u3ee9\u3ee1\u3ea5\u3eeb\u3eea\u3ef1\u3ea5\u3ee9\u3eea\u3ee4\u3ee1\u3ea5";
    private static String p = "\u3ebf\u3ea5";
    private static String q = "\u3ee3\u3ee4\u3ef1\u3ee4\u3ee9\u3ea5\u3ee0\u3ef7\u3ef7\u3eea\u3ef7\u3ebf\u3ea5";
    private static String r = "";
    private static String s = "\u3ec6\u3eea\u3ef5\u3efc\u3ef7\u3eec\u3ee2\u3eed\u3ef1\u3ea5\u3ead\u3ee6\u3eac\u3ea5\u3ea5";
    private static String t = "\u3ea9\u3ea5";
    private static String u = "\u3ed1\u3eed\u3eec\u3ef6\u3ea5\u3ee4\u3ef5\u3ef5\u3ee9\u3ee0\u3ef1\u3ea5\u3eec\u3ef6\u3ea5\u3ee6\u3eea\u3ef5\u3efc\u3ef7\u3eec\u3ee2\u3eed\u3ef1\u3ee0\u3ee1";
    private static String v = "\u3ea6";
    private static String w = "\u3ea5\u3ee6\u3ee4\u3eeb\u3ea2\u3ef1\u3ea5\u3ee7\u3ee0\u3ea5\u3ef5\u3ee4\u3ef7\u3ef6\u3ee0\u3ee1";
    private static String x = "\u3ea9";
    private static String y = "\u3ecf\u3ee4\u3ef3\u3ee4\u3ee9\u3eea\u3ef3\u3ee0\u3ef7\u3ef6\u3ea5\u3ed2\u3ee0\u3ee7\u3ee1\u3ee0\u3ef6\u3eec\u3ee2\u3eeb";
    private static String z = "\u3eef\u3ee4\u3ef3\u3ee4\u3ee9\u3eea\u3ef3\u3ee0\u3ef7\u3ef6\u3eab\u3ee6\u3eea\u3ee8";
    private static String A = "\u3ee3\u3ef2\u3ee0\u3eeb\u3ee1\u3eab\u3ee6\u3eea\u3ee8";
    private static String B = "\u3ee1\u3ee4\u3eec\u3ee9\u3efc\u3ef2\u3eea\u3ef7\u3ee1\u3ef6\u3ee0\u3ee4\u3ef7\u3ee6\u3eed\u3eab\u3ee6\u3eea\u3ee8";
    private static String C = "\u3ef0\u3eeb\u3ef7\u3ee0\u3ee4\u3ee9\u3ee6\u3ee4\u3ef6\u3eec\u3eeb\u3eea\u3eab\u3ee6\u3eea\u3ee8";
    private static String D = "\u3ee6\u3ef7\u3efc\u3ef5\u3ef1\u3eec\u3ee6\u3ee3\u3eea\u3ef7\u3ef0\u3ee8\u3eab\u3ee6\u3eea\u3ee8";
    private static String E = "\u3ee6\u3ee9\u3ee4\u3ef6\u3ef6\u3eec\u3ee6\u3ee8\u3eea\u3ef3\u3eec\u3ee0\u3ef4\u3ef0\u3eec\u3eff\u3eab\u3ee6\u3eea\u3ee8";
    private static String F = "\u3ee1\u3ee4\u3eec\u3ee9\u3efc\u3ef2\u3eea\u3ef7\u3ee1\u3ee2\u3ee4\u3ee8\u3ee0\u3eab\u3ee6\u3eea\u3ee8";
    
    public d(final Applet b, final c bb) {
        if (b == null) {
            throw new IllegalArgumentException(d.Ma);
        }
        this._b = b;
        this.bb = bb;
        final String _ = this._(d.Na, null);
        if (_ != null) {
            this.ab = new e();
            if (!this.ab.b(this._b.getCodeBase(), _, false)) {
                this.b(d.Oa + _ + d.Pa);
                this.ab = null;
            }
        }
    }
    
    public boolean a() {
        return this._b.getDocumentBase().getProtocol().startsWith(d.Ua);
    }
    
    public boolean _(final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (this._(array[i])) {
                return true;
            }
        }
        return false;
    }
    
    public boolean _(final String s) {
        return this._b.getDocumentBase().getHost().indexOf(s) != -1;
    }
    
    public boolean b(final long n, final long n2) {
        return new Date().getTime() > n + n2 * 86400000L;
    }
    
    public boolean a(final String s) {
        final String n = this.n(d.Va);
        return n != null && n.equalsIgnoreCase(s);
    }
    
    public boolean m(final String s) {
        final String n = this.n(d.Wa);
        return n != null && n.equalsIgnoreCase(s);
    }
    
    public void _(final int n, final int n2) {
        this.b(d.eb + n + d.fb + n2);
    }
    
    public int b(final String s, final int n, final int n2, final int n3) {
        int int1 = n3;
        String s2;
        if (this.ab != null) {
            s2 = this.ab._(s);
        }
        else {
            s2 = this._b.getParameter(s);
        }
        if (s2 != null) {
            try {
                int1 = Integer.parseInt(s2);
                if (!this.a(int1, n, n2)) {
                    int1 = n3;
                    this.b(String.valueOf(s) + d.gb + n + d.hb + n2 + d.ib);
                }
            }
            catch (NumberFormatException ex) {
                this.b(d.jb + s + d.ib);
            }
        }
        return int1;
    }
    
    public int a(final String s, final int n, final int n2) {
        int int1 = 0;
        String s2;
        if (this.ab != null) {
            s2 = this.ab._(s);
        }
        else {
            s2 = this._b.getParameter(s);
        }
        if (s2 != null) {
            try {
                int1 = Integer.parseInt(s2);
                if (!this.a(int1, n, n2)) {
                    this.n(String.valueOf(s) + d.gb + n + d.hb + n2);
                }
            }
            catch (NumberFormatException ex) {
                this.n(d.jb + s);
            }
        }
        else {
            this.n(String.valueOf(s) + d.kb);
        }
        return int1;
    }
    
    public int b(final String s) {
        return this.a(s, 0, 0);
    }
    
    public int a(final String s, final int n) {
        return this.b(s, 0, 0, n);
    }
    
    public boolean a(final int n, final int n2, final int n3) {
        return n2 == n3 || (n >= n2 && n <= n3);
    }
    
    public String n(final String s) {
        String s2;
        if (this.ab != null) {
            s2 = this.ab._(s);
        }
        else {
            s2 = this._b.getParameter(s);
        }
        if (s2 == null) {
            this.n(String.valueOf(s) + d.kb);
        }
        return s2;
    }
    
    public String _(final String s, final String s2) {
        String s3;
        if (this.ab != null) {
            s3 = this.ab._(s);
        }
        else {
            s3 = this._b.getParameter(s);
        }
        if (s3 == null) {
            s3 = s2;
        }
        return s3;
    }
    
    public Color a(final String s, final Color color) {
        Color color2 = color;
        String s2;
        if (this.ab != null) {
            s2 = this.ab._(s);
        }
        else {
            s2 = this._b.getParameter(s);
        }
        if (s2 != null) {
            try {
                color2 = _(s2);
            }
            catch (IllegalArgumentException ex2) {
                try {
                    color2 = a(s2);
                }
                catch (IllegalArgumentException ex) {
                    this.b(ex.getMessage());
                    this.b(d.jb + s + d.ib);
                }
            }
        }
        return color2;
    }
    
    public Color b(final String s) {
        Color color = null;
        String s2;
        if (this.ab != null) {
            s2 = this.ab._(s);
        }
        else {
            s2 = this._b.getParameter(s);
        }
        if (s2 != null) {
            try {
                color = _(s2);
                return color;
            }
            catch (IllegalArgumentException ex2) {
                try {
                    color = a(s2);
                }
                catch (IllegalArgumentException ex) {
                    this.b(ex.getMessage());
                    this.n(d.jb + s);
                }
            }
        }
        this.n(String.valueOf(s) + d.kb);
        return color;
    }
    
    public boolean b(final String s, final boolean b) {
        boolean b2 = b;
        String s2;
        if (this.ab != null) {
            s2 = this.ab._(s);
        }
        else {
            s2 = this._b.getParameter(s);
        }
        if (s2 != null) {
            final String lowerCase = s2.toLowerCase();
            b2 = (lowerCase.startsWith(d.lb) || lowerCase.startsWith(d.mb) || lowerCase.equals(d.nb) || lowerCase.equals(d.ob) || lowerCase.equals(d.pb));
        }
        return b2;
    }
    
    public int b(final String s, final int n) {
        int n2 = n;
        String s2 = null;
        try {
            if (this.ab != null) {
                s2 = this.ab._(s);
            }
            else {
                s2 = this._b.getParameter(s);
            }
            if (s2 != null) {
                if (s2.equalsIgnoreCase(d.qb)) {
                    n2 = 0;
                }
                else if (s2.equalsIgnoreCase(d.rb)) {
                    n2 = 1;
                }
                else if (s2.equalsIgnoreCase(d.sb)) {
                    n2 = 2;
                }
                else {
                    if (!s2.equalsIgnoreCase(d.tb)) {
                        throw new Exception();
                    }
                    n2 = 3;
                }
            }
        }
        catch (Exception ex) {
            this.b(d._ + s2 + d.ib);
        }
        return n2;
    }
    
    public String a(final String s, final String s2) {
        String s3 = s2;
        String s4;
        if (this.ab != null) {
            s4 = this.ab._(s);
        }
        else {
            s4 = this._b.getParameter(s);
        }
        try {
            if (s4 != null) {
                if (s4.equalsIgnoreCase(d.a)) {
                    s3 = d.b;
                }
                else if (s4.equalsIgnoreCase(d.c)) {
                    s3 = d.d;
                }
                else if (s4.equalsIgnoreCase(d.e)) {
                    s3 = d.f;
                }
                else {
                    if (!s4.equalsIgnoreCase(d.g)) {
                        throw new Exception();
                    }
                    s3 = d.h;
                }
            }
        }
        catch (Exception ex) {
            this.b(d.i + s4 + d.ib);
        }
        return s3;
    }
    
    public void _(final Vector vector, final int n) {
        final MediaTracker mediaTracker = new MediaTracker(this._b);
        for (int i = 0; i < vector.size(); ++i) {
            mediaTracker.addImage(vector.elementAt(i), n);
        }
        try {
            mediaTracker.waitForID(n);
        }
        catch (InterruptedException ex) {
            this.n(d.j);
        }
        if ((mediaTracker.statusID(n, false) & 0x6) != 0x0) {
            this.n(d.k);
        }
    }
    
    public Image[] a(final String s, final int n) {
        final Vector vector = new Vector<Image>();
        int n2 = 1;
        String _;
        while ((_ = this._(String.valueOf(s) + n2++, null)) != null) {
            vector.addElement(this._b.getImage(this._b.getCodeBase(), _));
        }
        this._(vector, n);
        final Image[] array = new Image[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public AudioClip _(String _) {
        if (!_.endsWith(d.l)) {
            _ = this._(_, null);
        }
        if (_ != null) {
            final AudioClip audioClip = this._b.getAudioClip(this._b.getCodeBase(), _);
            b(audioClip);
            _(audioClip);
            return audioClip;
        }
        return null;
    }
    
    public boolean b() {
        try {
            return System.getProperty(d.m).startsWith(d.n);
        }
        catch (SecurityException ex) {
            return true;
        }
    }
    
    public int a(final String s, final boolean b, final Graphics graphics) {
        final Dimension size = this._b.getSize();
        return _(s, b, new Rectangle(0, 0, size.width, size.height), graphics);
    }
    
    public void b(final Graphics graphics) {
        graphics.setColor(this._b.getBackground());
        graphics.fillRect(0, 0, this._b.getSize().width, this._b.getSize().height);
    }
    
    public Class _(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            this.n(d.o + s);
            return null;
        }
    }
    
    public void b(final String s) {
        System.out.println(String.valueOf(this._b.getClass().getName()) + d.p + s);
    }
    
    public boolean n(final String s) {
        return this.a(s, (Exception)null);
    }
    
    public boolean a(final String s, final Exception ex) {
        this.cb = true;
        if (this.bb != null) {
            this._b.showStatus(this.bb._());
        }
        this.b(d.q + s);
        if (ex != null) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public void b(final boolean db) {
        this.db = db;
    }
    
    public boolean _() {
        return this.db;
    }
    
    public boolean g() {
        return this.cb;
    }
    
    public static int _(final String s, final boolean b, final Rectangle rectangle, final Graphics graphics) {
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(graphics.getFont());
        if (b) {
            return rectangle.x + (rectangle.width - fontMetrics.stringWidth(s)) / 2;
        }
        return rectangle.y + rectangle.height % 2 + rectangle.height / 2 + (fontMetrics.getAscent() - fontMetrics.getDescent()) / 2;
    }
    
    private static String c(final String s) {
        if (s != null && !s.equals(d.r)) {
            return d.s + Calendar.getInstance().get(1) + d.t + s;
        }
        return d.u;
    }
    
    public static boolean _(final long n) {
        return n % 4L == 0L && (n % 100L != 0L || n % 400L == 0L);
    }
    
    public static void b(final AudioClip audioClip) {
        if (audioClip != null) {
            audioClip.play();
        }
    }
    
    public static void _(final AudioClip audioClip) {
        if (audioClip != null) {
            audioClip.stop();
        }
    }
    
    public static void a(final AudioClip audioClip) {
        if (audioClip != null) {
            audioClip.loop();
        }
    }
    
    public static Color _(String substring) {
        if (substring.startsWith(d.v)) {
            substring = substring.substring(1);
        }
        if (substring.length() == 6) {
            return new Color(Integer.parseInt(substring, 16));
        }
        throw new IllegalArgumentException(String.valueOf(substring) + d.w);
    }
    
    public static Color a(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        if (stringTokenizer.countTokens() == 3) {
            return new Color(Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()));
        }
        throw new IllegalArgumentException(String.valueOf(s) + d.w);
    }
    
    public static Color a(final String s) {
        return a(s, d.x);
    }
    
    public static void b(final int n, final int n2, final int n3, final int n4, final Graphics graphics) {
        graphics.drawLine(n, n2, n + n3, n2);
        graphics.drawLine(n + n3, n2, n + n3, n2 + n4);
        graphics.drawLine(n, n2, n, n2 + n4);
        graphics.drawLine(n, n2 + n4, n + n3, n2 + n4);
    }
    
    public static void _(final Rectangle rectangle, final Graphics graphics) {
        b(rectangle.x, rectangle.y, rectangle.width, rectangle.height, graphics);
    }
    
    public static void _(final Rectangle rectangle, final Color color, final Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public static void b(final int n, final int n2, final int n3, final int n4, final Color color, final Graphics graphics) {
        _(new Rectangle(n, n2, n3, n4), color, graphics);
    }
    
    public static Color a(final Color color) {
        Color color2 = color.brighter();
        if (color2.equals(color)) {
            color2 = color.darker();
        }
        return color2;
    }
    
    public static String a(final String s, final String s2, final String s3) {
        int i = s.indexOf(s2);
        if (i == -1) {
            return s;
        }
        final int length = s2.length();
        int n = 0;
        final char[] charArray = s.toCharArray();
        final StringBuffer sb = new StringBuffer();
        while (i != -1) {
            sb.append(charArray, n, i - n);
            sb.append(s3);
            n = i + length;
            i = s.indexOf(s2, n);
        }
        sb.append(charArray, n, charArray.length - n);
        return sb.toString();
    }
    
    public static void a(final InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            }
            catch (IOException ex) {}
        }
    }
    
    public static void b(final OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            }
            catch (IOException ex) {}
        }
    }
    
    public static void _(final Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            }
            catch (IOException ex) {}
        }
    }
    
    public static void _(final Writer writer) {
        if (writer != null) {
            try {
                writer.close();
            }
            catch (IOException ex) {}
        }
    }
    
    public static void a(final long n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public static void a(final Object o) {
        if (o != null) {
            if (o instanceof AudioClip) {
                ((AudioClip)o).stop();
            }
            else if (o instanceof Image) {
                ((Image)o).flush();
            }
            else if (o instanceof Graphics) {
                ((Graphics)o).dispose();
            }
            else if (o instanceof Hashtable) {
                ((Hashtable)o).clear();
            }
            else if (o instanceof Thread && ((Thread)o).isAlive()) {
                ((Thread)o).stop();
            }
        }
    }
    
    static {
        d.Ma = a(d.Ma);
        d.Na = a(d.Na);
        d.Oa = a(d.Oa);
        d.Pa = a(d.Pa);
        d.Ua = a(d.Ua);
        d.Va = a(d.Va);
        d.Wa = a(d.Wa);
        d.eb = a(d.eb);
        d.fb = a(d.fb);
        d.gb = a(d.gb);
        d.hb = a(d.hb);
        d.ib = a(d.ib);
        d.jb = a(d.jb);
        d.kb = a(d.kb);
        d.lb = a(d.lb);
        d.mb = a(d.mb);
        d.nb = a(d.nb);
        d.ob = a(d.ob);
        d.pb = a(d.pb);
        d.qb = a(d.qb);
        d.rb = a(d.rb);
        d.sb = a(d.sb);
        d.tb = a(d.tb);
        d._ = a(d._);
        d.a = a(d.a);
        d.b = a(d.b);
        d.c = a(d.c);
        d.d = a(d.d);
        d.e = a(d.e);
        d.f = a(d.f);
        d.g = a(d.g);
        d.h = a(d.h);
        d.i = a(d.i);
        d.j = a(d.j);
        d.k = a(d.k);
        d.l = a(d.l);
        d.m = a(d.m);
        d.n = a(d.n);
        d.o = a(d.o);
        d.p = a(d.p);
        d.q = a(d.q);
        d.r = a(d.r);
        d.s = a(d.s);
        d.t = a(d.t);
        d.u = a(d.u);
        d.v = a(d.v);
        d.w = a(d.w);
        d.x = a(d.x);
        d.y = a(d.y);
        d.z = a(d.z);
        d.A = a(d.A);
        d.B = a(d.B);
        d.C = a(d.C);
        d.D = a(d.D);
        d.E = a(d.E);
        d.F = a(d.F);
        Xa = c(d.y);
        Za = new String[] { d.z, d.A, d.B, d.C, d.D, d.E, d.F };
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0x13E85);
        }
        return new String(array);
    }
}
