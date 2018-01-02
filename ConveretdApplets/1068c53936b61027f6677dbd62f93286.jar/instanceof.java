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

public class instanceof
{
    public static final String ab;
    public static final long bb = 86400000L;
    public static final String[] cb;
    private Applet db;
    private native eb;
    private import fb;
    private boolean gb;
    private boolean hb;
    private static String Ua = "\u70f9\u70e2\u70fb\u70fb";
    private static String Va = "\u70f4\u70f8\u70f9\u70f1\u70fe\u70f0";
    private static String Wa = "\u70e5\u70f2\u70f6\u70f3\u70fe\u70f9\u70f0\u70b7";
    private static String Xa = "\u70b7\u70ff\u70f6\u70e4\u70b7\u70f1\u70f6\u70fe\u70fb\u70f2\u70f3";
    private static String Ya = "\u70ff\u70e3\u70e3\u70e7";
    private static String Za = "\u70f4\u70f8\u70e7\u70ee\u70e5\u70fe\u70f0\u70ff\u70e3";
    private static String _b = "\u70fb\u70fe\u70f4\u70f2\u70f9\u70e4\u70f2\u70f2";
    private static String ib = "\u70e5\u70f2\u70f4\u70f8\u70fa\u70fa\u70f2\u70f9\u70f3\u70f2\u70f3\u70b7\u70e4\u70fe\u70ed\u70f2\u70ad\u70b7\u70e0\u70fe\u70f3\u70e3\u70ff\u70aa";
    private static String jb = "\u70b7\u70ff\u70f2\u70fe\u70f0\u70ff\u70e3\u70aa";
    private static String kb = "\u70b7\u70e1\u70f6\u70fb\u70e2\u70f2\u70b7\u70ff\u70f6\u70e4\u70b7\u70e3\u70f8\u70b7\u70f5\u70f2\u70b7\u70f5\u70f2\u70e3\u70e0\u70f2\u70f2\u70f9\u70b7";
    private static String lb = "\u70b7\u70f6\u70f9\u70f3\u70b7";
    private static String mb = "\u70ac\u70b7\u70e2\u70e4\u70fe\u70f9\u70f0\u70b7\u70f3\u70f2\u70f1\u70f6\u70e2\u70fb\u70e3";
    private static String nb = "\u70fe\u70fb\u70fb\u70f2\u70f0\u70f6\u70fb\u70b7\u70e1\u70f6\u70fb\u70e2\u70f2\u70b7\u70f1\u70f8\u70e5\u70b7";
    private static String ob = "\u70b7\u70e7\u70f6\u70e5\u70f6\u70fa\u70f2\u70e3\u70f2\u70e5\u70b7\u70fe\u70e4\u70b7\u70fa\u70fe\u70e4\u70e4\u70fe\u70f9\u70f0";
    private static String pb = "\u70ee";
    private static String qb = "\u70e3";
    private static String rb = "\u70a6";
    private static String sb = "\u70f8\u70f9";
    private static String tb = "\u70bc";
    private static String _ = "\u70e7\u70fb\u70f6\u70fe\u70f9";
    private static String a = "\u70f5\u70f8\u70fb\u70f3";
    private static String b = "\u70fe\u70e3\u70f6\u70fb\u70fe\u70f4";
    private static String c = "\u70f5\u70f8\u70fb\u70f3\u70fe\u70e3\u70f6\u70fb\u70fe\u70f4";
    private static String d = "\u70fe\u70fb\u70fb\u70f2\u70f0\u70f6\u70fb\u70b7\u70e1\u70f6\u70fb\u70e2\u70f2\u70b7\u70f1\u70f8\u70e5\u70b7\u70f1\u70f8\u70f9\u70e3\u70e4\u70e3\u70ee\u70fb\u70f2\u70ad\u70b7";
    private static String e = "\u70e3\u70fe\u70fa\u70f2\u70e4\u70e5\u70f8\u70fa\u70f6\u70f9";
    private static String f = "\u70c3\u70fe\u70fa\u70f2\u70e4\u70c5\u70f8\u70fa\u70f6\u70f9";
    private static String g = "\u70f6\u70e5\u70fe\u70f6\u70fb";
    private static String h = "\u70d6\u70e5\u70fe\u70f6\u70fb";
    private static String i = "\u70f4\u70f8\u70e2\u70e5\u70fe\u70f2\u70e5";
    private static String j = "\u70d4\u70f8\u70e2\u70e5\u70fe\u70f2\u70e5";
    private static String k = "\u70f3\u70fe\u70f6\u70fb\u70f8\u70f0";
    private static String l = "\u70d3\u70fe\u70f6\u70fb\u70f8\u70f0";
    private static String m = "\u70fe\u70fb\u70fb\u70f2\u70f0\u70f6\u70fb\u70b7\u70e1\u70f6\u70fb\u70e2\u70f2\u70b7\u70f1\u70f8\u70e5\u70b7\u70f1\u70f8\u70f9\u70e3\u70f1\u70f6\u70f4\u70f2\u70ad\u70b7";
    private static String n = "\u70fe\u70fa\u70f6\u70f0\u70f2\u70ba\u70fb\u70f8\u70f6\u70f3\u70fe\u70f9\u70f0\u70b7\u70ff\u70f6\u70e4\u70b7\u70f5\u70f2\u70f2\u70f9\u70b7\u70fe\u70f9\u70e3\u70f2\u70e5\u70e5\u70e2\u70e7\u70e3\u70f2\u70f3";
    private static String o = "\u70f8\u70f9\u70f2\u70b7\u70f8\u70e5\u70b7\u70fa\u70f8\u70e5\u70f2\u70b7\u70fe\u70fa\u70f6\u70f0\u70f2\u70e4\u70b7\u70fa\u70f6\u70ee\u70b7\u70f9\u70f8\u70e3\u70b7\u70ff\u70f6\u70e1\u70f2\u70b7\u70fb\u70f8\u70f6\u70f3\u70f2\u70f3\u70b7\u70f4\u70f8\u70e5\u70e5\u70f2\u70f4\u70e3\u70fb\u70ee";
    private static String p = "\u70b9\u70f6\u70e2";
    private static String q = "\u70f8\u70e4\u70b9\u70f9\u70f6\u70fa\u70f2";
    private static String r = "\u70c0\u70fe\u70f9";
    private static String s = "\u70f4\u70f8\u70e2\u70fb\u70f3\u70b7\u70f9\u70f8\u70e3\u70b7\u70fb\u70f8\u70f6\u70f3\u70b7";
    private static String t = "\u70ad\u70b7";
    private static String u = "\u70f1\u70f6\u70e3\u70f6\u70fb\u70b7\u70f2\u70e5\u70e5\u70f8\u70e5\u70ad\u70b7";
    private static String v = "";
    private static String w = "\u70d4\u70f8\u70e7\u70ee\u70e5\u70fe\u70f0\u70ff\u70e3\u70b7\u70bf\u70f4\u70be\u70b7\u70b7";
    private static String x = "\u70bb\u70b7";
    private static String y = "\u70c3\u70ff\u70fe\u70e4\u70b7\u70f6\u70e7\u70e7\u70fb\u70f2\u70e3\u70b7\u70fe\u70e4\u70b7\u70f4\u70f8\u70e7\u70ee\u70e5\u70fe\u70f0\u70ff\u70e3\u70f2\u70f3";
    private static String z = "\u70b4";
    private static String A = "\u70b7\u70f4\u70f6\u70f9\u70b0\u70e3\u70b7\u70f5\u70f2\u70b7\u70e7\u70f6\u70e5\u70e4\u70f2\u70f3";
    private static String B = "\u70bb";
    private static String C = "\u70dd\u70f6\u70e1\u70f6\u70fb\u70f8\u70e1\u70f2\u70e5\u70e4\u70b7\u70c0\u70f2\u70f5\u70f3\u70f2\u70e4\u70fe\u70f0\u70f9";
    private static String D = "\u70fd\u70f6\u70e1\u70f6\u70fb\u70f8\u70e1\u70f2\u70e5\u70e4\u70b9\u70f4\u70f8\u70fa";
    private static String E = "\u70f1\u70e0\u70f2\u70f9\u70f3\u70b9\u70f4\u70f8\u70fa";
    private static String F = "\u70f3\u70f6\u70fe\u70fb\u70ee\u70e0\u70f8\u70e5\u70f3\u70e4\u70f2\u70f6\u70e5\u70f4\u70ff\u70b9\u70f4\u70f8\u70fa";
    private static String G = "\u70e2\u70f9\u70e5\u70f2\u70f6\u70fb\u70f4\u70f6\u70e4\u70fe\u70f9\u70f8\u70b9\u70f4\u70f8\u70fa";
    private static String H = "\u70f4\u70e5\u70ee\u70e7\u70e3\u70fe\u70f4\u70f1\u70f8\u70e5\u70e2\u70fa\u70b9\u70f4\u70f8\u70fa";
    private static String I = "\u70f4\u70fb\u70f6\u70e4\u70e4\u70fe\u70f4\u70fa\u70f8\u70e1\u70fe\u70f2\u70e6\u70e2\u70fe\u70ed\u70b9\u70f4\u70f8\u70fa";
    private static String J = "\u70f3\u70f6\u70fe\u70fb\u70ee\u70e0\u70f8\u70e5\u70f3\u70f0\u70f6\u70fa\u70f2\u70b9\u70f4\u70f8\u70fa";
    
    public instanceof(final Applet db, final import fb) {
        if (db == null) {
            throw new IllegalArgumentException(instanceof.Ua);
        }
        this.db = db;
        this.fb = fb;
        final String a = this.a(instanceof.Va, (String)null);
        if (a != null) {
            this.eb = new native();
            if (!this.eb.a(this.db.getCodeBase(), a, false)) {
                this.a(instanceof.Wa + a + instanceof.Xa);
                this.eb = null;
            }
        }
    }
    
    public boolean a() {
        return this.db.getDocumentBase().getProtocol().startsWith(instanceof.Ya);
    }
    
    public boolean _(final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (this.b(array[i])) {
                return true;
            }
        }
        return false;
    }
    
    public boolean b(final String s) {
        return this.db.getDocumentBase().getHost().indexOf(s) != -1;
    }
    
    public boolean b(final long n, final long n2) {
        return new Date().getTime() > n + n2 * 86400000L;
    }
    
    public boolean _(final String s) {
        final String l = this.l(instanceof.Za);
        return l != null && l.equalsIgnoreCase(s);
    }
    
    public boolean k(final String s) {
        final String l = this.l(instanceof._b);
        return l != null && l.equalsIgnoreCase(s);
    }
    
    public void b(final int n, final int n2) {
        this.a(instanceof.ib + n + instanceof.jb + n2);
    }
    
    public int _(final String s, final int n, final int n2, final int n3) {
        int int1 = n3;
        String s2;
        if (this.eb != null) {
            s2 = this.eb._(s);
        }
        else {
            s2 = this.db.getParameter(s);
        }
        if (s2 != null) {
            try {
                int1 = Integer.parseInt(s2);
                if (!this.a(int1, n, n2)) {
                    int1 = n3;
                    this.a(String.valueOf(s) + instanceof.kb + n + instanceof.lb + n2 + instanceof.mb);
                }
            }
            catch (NumberFormatException ex) {
                this.a(instanceof.nb + s + instanceof.mb);
            }
        }
        return int1;
    }
    
    public int _(final String s, final int n, final int n2) {
        int int1 = 0;
        String s2;
        if (this.eb != null) {
            s2 = this.eb._(s);
        }
        else {
            s2 = this.db.getParameter(s);
        }
        if (s2 != null) {
            try {
                int1 = Integer.parseInt(s2);
                if (!this.a(int1, n, n2)) {
                    this.l(String.valueOf(s) + instanceof.kb + n + instanceof.lb + n2);
                }
            }
            catch (NumberFormatException ex) {
                this.l(instanceof.nb + s);
            }
        }
        else {
            this.l(String.valueOf(s) + instanceof.ob);
        }
        return int1;
    }
    
    public int b(final String s) {
        return this._(s, 0, 0);
    }
    
    public int _(final String s, final int n) {
        return this._(s, 0, 0, n);
    }
    
    public boolean a(final int n, final int n2, final int n3) {
        return n2 == n3 || (n >= n2 && n <= n3);
    }
    
    public String l(final String s) {
        String s2;
        if (this.eb != null) {
            s2 = this.eb._(s);
        }
        else {
            s2 = this.db.getParameter(s);
        }
        if (s2 == null) {
            this.l(String.valueOf(s) + instanceof.ob);
        }
        return s2;
    }
    
    public String a(final String s, final String s2) {
        String s3;
        if (this.eb != null) {
            s3 = this.eb._(s);
        }
        else {
            s3 = this.db.getParameter(s);
        }
        if (s3 == null) {
            s3 = s2;
        }
        return s3;
    }
    
    public Color b(final String s, final Color color) {
        Color color2 = color;
        String s2;
        if (this.eb != null) {
            s2 = this.eb._(s);
        }
        else {
            s2 = this.db.getParameter(s);
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
                    this.a(ex.getMessage());
                    this.a(instanceof.nb + s + instanceof.mb);
                }
            }
        }
        return color2;
    }
    
    public Color b(final String s) {
        Color color = null;
        String s2;
        if (this.eb != null) {
            s2 = this.eb._(s);
        }
        else {
            s2 = this.db.getParameter(s);
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
                    this.a(ex.getMessage());
                    this.l(instanceof.nb + s);
                }
            }
        }
        this.l(String.valueOf(s) + instanceof.ob);
        return color;
    }
    
    public boolean b(final String s, final boolean b) {
        boolean b2 = b;
        String s2;
        if (this.eb != null) {
            s2 = this.eb._(s);
        }
        else {
            s2 = this.db.getParameter(s);
        }
        if (s2 != null) {
            final String lowerCase = s2.toLowerCase();
            b2 = (lowerCase.startsWith(instanceof.pb) || lowerCase.startsWith(instanceof.qb) || lowerCase.equals(instanceof.rb) || lowerCase.equals(instanceof.sb) || lowerCase.equals(instanceof.tb));
        }
        return b2;
    }
    
    public int a(final String s, final int n) {
        int n2 = n;
        String s2 = null;
        try {
            if (this.eb != null) {
                s2 = this.eb._(s);
            }
            else {
                s2 = this.db.getParameter(s);
            }
            if (s2 != null) {
                if (s2.equalsIgnoreCase(instanceof._)) {
                    n2 = 0;
                }
                else if (s2.equalsIgnoreCase(instanceof.a)) {
                    n2 = 1;
                }
                else if (s2.equalsIgnoreCase(instanceof.b)) {
                    n2 = 2;
                }
                else {
                    if (!s2.equalsIgnoreCase(instanceof.c)) {
                        throw new Exception();
                    }
                    n2 = 3;
                }
            }
        }
        catch (Exception ex) {
            this.a(instanceof.d + s2 + instanceof.mb);
        }
        return n2;
    }
    
    public String b(final String s, final String s2) {
        String s3 = s2;
        String s4;
        if (this.eb != null) {
            s4 = this.eb._(s);
        }
        else {
            s4 = this.db.getParameter(s);
        }
        try {
            if (s4 != null) {
                if (s4.equalsIgnoreCase(instanceof.e)) {
                    s3 = instanceof.f;
                }
                else if (s4.equalsIgnoreCase(instanceof.g)) {
                    s3 = instanceof.h;
                }
                else if (s4.equalsIgnoreCase(instanceof.i)) {
                    s3 = instanceof.j;
                }
                else {
                    if (!s4.equalsIgnoreCase(instanceof.k)) {
                        throw new Exception();
                    }
                    s3 = instanceof.l;
                }
            }
        }
        catch (Exception ex) {
            this.a(instanceof.m + s4 + instanceof.mb);
        }
        return s3;
    }
    
    public void b(final Vector vector, final int n) {
        final MediaTracker mediaTracker = new MediaTracker(this.db);
        for (int i = 0; i < vector.size(); ++i) {
            mediaTracker.addImage(vector.elementAt(i), n);
        }
        try {
            mediaTracker.waitForID(n);
        }
        catch (InterruptedException ex) {
            this.l(instanceof.n);
        }
        if ((mediaTracker.statusID(n, false) & 0x6) != 0x0) {
            this.l(instanceof.o);
        }
    }
    
    public Image[] b(final String s, final int n) {
        final Vector vector = new Vector<Image>();
        int n2 = 1;
        String a;
        while ((a = this.a(String.valueOf(s) + n2++, (String)null)) != null) {
            vector.addElement(this.db.getImage(this.db.getCodeBase(), a));
        }
        this.b(vector, n);
        final Image[] array = new Image[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public AudioClip a(String a) {
        if (!a.endsWith(instanceof.p)) {
            a = this.a(a, (String)null);
        }
        if (a != null) {
            final AudioClip audioClip = this.db.getAudioClip(this.db.getCodeBase(), a);
            a(audioClip);
            b(audioClip);
            return audioClip;
        }
        return null;
    }
    
    public boolean b() {
        try {
            return System.getProperty(instanceof.q).startsWith(instanceof.r);
        }
        catch (SecurityException ex) {
            return true;
        }
    }
    
    public int a(final String s, final boolean b, final Graphics graphics) {
        final Dimension size = this.db.getSize();
        return a(s, b, new Rectangle(0, 0, size.width, size.height), graphics);
    }
    
    public void b(final Graphics graphics) {
        graphics.setColor(this.db.getBackground());
        graphics.fillRect(0, 0, this.db.getSize().width, this.db.getSize().height);
    }
    
    public Class _(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            this.l(instanceof.s + s);
            return null;
        }
    }
    
    public void a(final String s) {
        System.out.println(String.valueOf(this.db.getClass().getName()) + instanceof.t + s);
    }
    
    public boolean l(final String s) {
        return this.a(s, (Exception)null);
    }
    
    public boolean a(final String s, final Exception ex) {
        this.gb = true;
        if (this.fb != null) {
            this.db.showStatus(this.fb.b());
        }
        this.a(instanceof.u + s);
        if (ex != null) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public void b(final boolean hb) {
        this.hb = hb;
    }
    
    public boolean _() {
        return this.hb;
    }
    
    public boolean k() {
        return this.gb;
    }
    
    public static int a(final String s, final boolean b, final Rectangle rectangle, final Graphics graphics) {
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(graphics.getFont());
        if (b) {
            return rectangle.x + (rectangle.width - fontMetrics.stringWidth(s)) / 2;
        }
        return rectangle.y + rectangle.height % 2 + rectangle.height / 2 + (fontMetrics.getAscent() - fontMetrics.getDescent()) / 2;
    }
    
    private static String m(final String s) {
        if (s != null && !s.equals(instanceof.v)) {
            return instanceof.w + Calendar.getInstance().get(1) + instanceof.x + s;
        }
        return instanceof.y;
    }
    
    public static boolean a(final long n) {
        return n % 4L == 0L && (n % 100L != 0L || n % 400L == 0L);
    }
    
    public static void a(final AudioClip audioClip) {
        if (audioClip != null) {
            audioClip.play();
        }
    }
    
    public static void b(final AudioClip audioClip) {
        if (audioClip != null) {
            audioClip.stop();
        }
    }
    
    public static void _(final AudioClip audioClip) {
        if (audioClip != null) {
            audioClip.loop();
        }
    }
    
    public static Color _(String substring) {
        if (substring.startsWith(instanceof.z)) {
            substring = substring.substring(1);
        }
        if (substring.length() == 6) {
            return new Color(Integer.parseInt(substring, 16));
        }
        throw new IllegalArgumentException(String.valueOf(substring) + instanceof.A);
    }
    
    public static Color _(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        if (stringTokenizer.countTokens() == 3) {
            return new Color(Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()));
        }
        throw new IllegalArgumentException(String.valueOf(s) + instanceof.A);
    }
    
    public static Color a(final String s) {
        return _(s, instanceof.B);
    }
    
    public static void _(final int n, final int n2, final int n3, final int n4, final Graphics graphics) {
        graphics.drawLine(n, n2, n + n3, n2);
        graphics.drawLine(n + n3, n2, n + n3, n2 + n4);
        graphics.drawLine(n, n2, n, n2 + n4);
        graphics.drawLine(n, n2 + n4, n + n3, n2 + n4);
    }
    
    public static void a(final Rectangle rectangle, final Graphics graphics) {
        _(rectangle.x, rectangle.y, rectangle.width, rectangle.height, graphics);
    }
    
    public static void b(final Rectangle rectangle, final Color color, final Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public static void b(final int n, final int n2, final int n3, final int n4, final Color color, final Graphics graphics) {
        b(new Rectangle(n, n2, n3, n4), color, graphics);
    }
    
    public static Color a(final Color color) {
        Color color2 = color.brighter();
        if (color2.equals(color)) {
            color2 = color.darker();
        }
        return color2;
    }
    
    public static String _(final String s, final String s2, final String s3) {
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
    
    public static void a(final Writer writer) {
        if (writer != null) {
            try {
                writer.close();
            }
            catch (IOException ex) {}
        }
    }
    
    public static void b(final long n) {
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
        instanceof.Ua = a(instanceof.Ua);
        instanceof.Va = a(instanceof.Va);
        instanceof.Wa = a(instanceof.Wa);
        instanceof.Xa = a(instanceof.Xa);
        instanceof.Ya = a(instanceof.Ya);
        instanceof.Za = a(instanceof.Za);
        instanceof._b = a(instanceof._b);
        instanceof.ib = a(instanceof.ib);
        instanceof.jb = a(instanceof.jb);
        instanceof.kb = a(instanceof.kb);
        instanceof.lb = a(instanceof.lb);
        instanceof.mb = a(instanceof.mb);
        instanceof.nb = a(instanceof.nb);
        instanceof.ob = a(instanceof.ob);
        instanceof.pb = a(instanceof.pb);
        instanceof.qb = a(instanceof.qb);
        instanceof.rb = a(instanceof.rb);
        instanceof.sb = a(instanceof.sb);
        instanceof.tb = a(instanceof.tb);
        instanceof._ = a(instanceof._);
        instanceof.a = a(instanceof.a);
        instanceof.b = a(instanceof.b);
        instanceof.c = a(instanceof.c);
        instanceof.d = a(instanceof.d);
        instanceof.e = a(instanceof.e);
        instanceof.f = a(instanceof.f);
        instanceof.g = a(instanceof.g);
        instanceof.h = a(instanceof.h);
        instanceof.i = a(instanceof.i);
        instanceof.j = a(instanceof.j);
        instanceof.k = a(instanceof.k);
        instanceof.l = a(instanceof.l);
        instanceof.m = a(instanceof.m);
        instanceof.n = a(instanceof.n);
        instanceof.o = a(instanceof.o);
        instanceof.p = a(instanceof.p);
        instanceof.q = a(instanceof.q);
        instanceof.r = a(instanceof.r);
        instanceof.s = a(instanceof.s);
        instanceof.t = a(instanceof.t);
        instanceof.u = a(instanceof.u);
        instanceof.v = a(instanceof.v);
        instanceof.w = a(instanceof.w);
        instanceof.x = a(instanceof.x);
        instanceof.y = a(instanceof.y);
        instanceof.z = a(instanceof.z);
        instanceof.A = a(instanceof.A);
        instanceof.B = a(instanceof.B);
        instanceof.C = a(instanceof.C);
        instanceof.D = a(instanceof.D);
        instanceof.E = a(instanceof.E);
        instanceof.F = a(instanceof.F);
        instanceof.G = a(instanceof.G);
        instanceof.H = a(instanceof.H);
        instanceof.I = a(instanceof.I);
        instanceof.J = a(instanceof.J);
        ab = m(instanceof.C);
        cb = new String[] { instanceof.D, instanceof.E, instanceof.F, instanceof.G, instanceof.H, instanceof.I, instanceof.J };
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF7097);
        }
        return new String(array);
    }
}
