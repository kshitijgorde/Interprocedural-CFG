import java.util.Hashtable;
import java.io.Writer;
import java.io.Reader;
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;
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

public class s
{
    public static final String l;
    public static final long m = 86400000L;
    public static final String[] n;
    private Applet o;
    private t p;
    private r q;
    private boolean r;
    private boolean s;
    private static String e = "\u2cdc\u2cc7\u2cde\u2cde";
    private static String f = "\u2cd1\u2cdd\u2cdc\u2cd4\u2cdb\u2cd5";
    private static String g = "\u2cc0\u2cd7\u2cd3\u2cd6\u2cdb\u2cdc\u2cd5\u2c92";
    private static String h = "\u2c92\u2cda\u2cd3\u2cc1\u2c92\u2cd4\u2cd3\u2cdb\u2cde\u2cd7\u2cd6";
    private static String i = "\u2cda\u2cc6\u2cc6\u2cc2";
    private static String j = "\u2cd1\u2cdd\u2cc2\u2ccb\u2cc0\u2cdb\u2cd5\u2cda\u2cc6";
    private static String k = "\u2cde\u2cdb\u2cd1\u2cd7\u2cdc\u2cc1\u2cd7\u2cd7";
    private static String t = "\u2cc0\u2cd7\u2cd1\u2cdd\u2cdf\u2cdf\u2cd7\u2cdc\u2cd6\u2cd7\u2cd6\u2c92\u2cc1\u2cdb\u2cc8\u2cd7\u2c88\u2c92\u2cc5\u2cdb\u2cd6\u2cc6\u2cda\u2c8f";
    private static String u = "\u2c92\u2cda\u2cd7\u2cdb\u2cd5\u2cda\u2cc6\u2c8f";
    private static String v = "\u2c92\u2cc4\u2cd3\u2cde\u2cc7\u2cd7\u2c92\u2cda\u2cd3\u2cc1\u2c92\u2cc6\u2cdd\u2c92\u2cd0\u2cd7\u2c92\u2cd0\u2cd7\u2cc6\u2cc5\u2cd7\u2cd7\u2cdc\u2c92";
    private static String w = "\u2c92\u2cd3\u2cdc\u2cd6\u2c92";
    private static String x = "\u2c89\u2c92\u2cc7\u2cc1\u2cdb\u2cdc\u2cd5\u2c92\u2cd6\u2cd7\u2cd4\u2cd3\u2cc7\u2cde\u2cc6";
    private static String y = "\u2cdb\u2cde\u2cde\u2cd7\u2cd5\u2cd3\u2cde\u2c92\u2cc4\u2cd3\u2cde\u2cc7\u2cd7\u2c92\u2cd4\u2cdd\u2cc0\u2c92";
    private static String z = "\u2c92\u2cc2\u2cd3\u2cc0\u2cd3\u2cdf\u2cd7\u2cc6\u2cd7\u2cc0\u2c92\u2cdb\u2cc1\u2c92\u2cdf\u2cdb\u2cc1\u2cc1\u2cdb\u2cdc\u2cd5";
    private static String A = "\u2ccb";
    private static String B = "\u2cc6";
    private static String C = "\u2c83";
    private static String D = "\u2cdd\u2cdc";
    private static String E = "\u2c99";
    private static String F = "\u2cc2\u2cde\u2cd3\u2cdb\u2cdc";
    private static String G = "\u2cd0\u2cdd\u2cde\u2cd6";
    private static String H = "\u2cdb\u2cc6\u2cd3\u2cde\u2cdb\u2cd1";
    private static String I = "\u2cd0\u2cdd\u2cde\u2cd6\u2cdb\u2cc6\u2cd3\u2cde\u2cdb\u2cd1";
    private static String J = "\u2cdb\u2cde\u2cde\u2cd7\u2cd5\u2cd3\u2cde\u2c92\u2cc4\u2cd3\u2cde\u2cc7\u2cd7\u2c92\u2cd4\u2cdd\u2cc0\u2c92\u2cd4\u2cdd\u2cdc\u2cc6\u2cc1\u2cc6\u2ccb\u2cde\u2cd7\u2c88\u2c92";
    private static String K = "\u2cc6\u2cdb\u2cdf\u2cd7\u2cc1\u2cc0\u2cdd\u2cdf\u2cd3\u2cdc";
    private static String L = "\u2ce6\u2cdb\u2cdf\u2cd7\u2cc1\u2ce0\u2cdd\u2cdf\u2cd3\u2cdc";
    private static String M = "\u2cd3\u2cc0\u2cdb\u2cd3\u2cde";
    private static String N = "\u2cf3\u2cc0\u2cdb\u2cd3\u2cde";
    private static String O = "\u2cd1\u2cdd\u2cc7\u2cc0\u2cdb\u2cd7\u2cc0";
    private static String P = "\u2cf1\u2cdd\u2cc7\u2cc0\u2cdb\u2cd7\u2cc0";
    private static String Q = "\u2cd6\u2cdb\u2cd3\u2cde\u2cdd\u2cd5";
    private static String R = "\u2cf6\u2cdb\u2cd3\u2cde\u2cdd\u2cd5";
    private static String S = "\u2cdb\u2cde\u2cde\u2cd7\u2cd5\u2cd3\u2cde\u2c92\u2cc4\u2cd3\u2cde\u2cc7\u2cd7\u2c92\u2cd4\u2cdd\u2cc0\u2c92\u2cd4\u2cdd\u2cdc\u2cc6\u2cd4\u2cd3\u2cd1\u2cd7\u2c88\u2c92";
    private static String T = "\u2cdb\u2cdf\u2cd3\u2cd5\u2cd7\u2c9f\u2cde\u2cdd\u2cd3\u2cd6\u2cdb\u2cdc\u2cd5\u2c92\u2cda\u2cd3\u2cc1\u2c92\u2cd0\u2cd7\u2cd7\u2cdc\u2c92\u2cdb\u2cdc\u2cc6\u2cd7\u2cc0\u2cc0\u2cc7\u2cc2\u2cc6\u2cd7\u2cd6";
    private static String U = "\u2cdd\u2cdc\u2cd7\u2c92\u2cdd\u2cc0\u2c92\u2cdf\u2cdd\u2cc0\u2cd7\u2c92\u2cdb\u2cdf\u2cd3\u2cd5\u2cd7\u2cc1\u2c92\u2cdf\u2cd3\u2ccb\u2c92\u2cdc\u2cdd\u2cc6\u2c92\u2cda\u2cd3\u2cc4\u2cd7\u2c92\u2cde\u2cdd\u2cd3\u2cd6\u2cd7\u2cd6\u2c92\u2cd1\u2cdd\u2cc0\u2cc0\u2cd7\u2cd1\u2cc6\u2cde\u2ccb";
    private static String V = "\u2c9c\u2cd3\u2cc7";
    private static String W = "\u2cdd\u2cc1\u2c9c\u2cdc\u2cd3\u2cdf\u2cd7";
    private static String X = "\u2ce5\u2cdb\u2cdc";
    private static String Y = "\u2cd1\u2cdd\u2cc7\u2cde\u2cd6\u2c92\u2cdc\u2cdd\u2cc6\u2c92\u2cde\u2cdd\u2cd3\u2cd6\u2c92";
    private static String Z = "\u2c88\u2c92";
    private static String _a = "\u2cd4\u2cd3\u2cc6\u2cd3\u2cde\u2c92\u2cd7\u2cc0\u2cc0\u2cdd\u2cc0\u2c88\u2c92";
    private static String aa = "";
    private static String ba = "\u2ce6\u2cda\u2cdb\u2cc1\u2c92\u2cd3\u2cc2\u2cc2\u2cde\u2cd7\u2cc6\u2c92\u2cdb\u2cc1\u2c92\u2cd1\u2cdd\u2cc2\u2ccb\u2cc0\u2cdb\u2cd5\u2cda\u2cc6\u2cd7\u2cd6";
    private static String ca = "\u2c91";
    private static String da = "\u2c92\u2cd1\u2cd3\u2cdc\u2c95\u2cc6\u2c92\u2cd0\u2cd7\u2c92\u2cc2\u2cd3\u2cc0\u2cc1\u2cd7\u2cd6";
    private static String ea = "\u2c9e";
    private static String fa = "\u2cf8\u2cd3\u2cc4\u2cd3\u2cde\u2cdd\u2cc4\u2cd7\u2cc0\u2cc1\u2c92\u2ce5\u2cd7\u2cd0\u2cd6\u2cd7\u2cc1\u2cdb\u2cd5\u2cdc";
    private static String ga = "\u2cd8\u2cd3\u2cc4\u2cd3\u2cde\u2cdd\u2cc4\u2cd7\u2cc0\u2cc1\u2c9c\u2cd1\u2cdd\u2cdf";
    private static String ha = "\u2cd4\u2cc5\u2cd7\u2cdc\u2cd6\u2c9c\u2cd1\u2cdd\u2cdf";
    private static String ia = "\u2cd6\u2cd3\u2cdb\u2cde\u2ccb\u2cc5\u2cdd\u2cc0\u2cd6\u2cc1\u2cd7\u2cd3\u2cc0\u2cd1\u2cda\u2c9c\u2cd1\u2cdd\u2cdf";
    private static String ja = "\u2cc7\u2cdc\u2cc0\u2cd7\u2cd3\u2cde\u2cd1\u2cd3\u2cc1\u2cdb\u2cdc\u2cdd\u2c9c\u2cd1\u2cdd\u2cdf";
    private static String ka = "\u2cd1\u2cc0\u2ccb\u2cc2\u2cc6\u2cdb\u2cd1\u2cd4\u2cdd\u2cc0\u2cc7\u2cdf\u2c9c\u2cd1\u2cdd\u2cdf";
    private static String la = "\u2cd1\u2cde\u2cd3\u2cc1\u2cc1\u2cdb\u2cd1\u2cdf\u2cdd\u2cc4\u2cdb\u2cd7\u2cc3\u2cc7\u2cdb\u2cc8\u2c9c\u2cd1\u2cdd\u2cdf";
    private static String ma = "\u2cd6\u2cd3\u2cdb\u2cde\u2ccb\u2cc5\u2cdd\u2cc0\u2cd6\u2cd5\u2cd3\u2cdf\u2cd7\u2c9c\u2cd1\u2cdd\u2cdf";
    
    public s(final Applet o, final r q) {
        if (o == null) {
            throw new IllegalArgumentException(s.e);
        }
        this.o = o;
        this.q = q;
        final String a = this.a(s.f, (String)null);
        if (a != null) {
            this.p = new t();
            if (!this.p._(this.o.getCodeBase(), a, false)) {
                this._(s.g + a + s.h);
                this.p = null;
            }
        }
    }
    
    public boolean _() {
        return this.o.getDocumentBase().getProtocol().startsWith(s.i);
    }
    
    public boolean a(final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (this.a(array[i])) {
                return true;
            }
        }
        return false;
    }
    
    public boolean a(final String s) {
        return this.o.getDocumentBase().getHost().indexOf(s) != -1;
    }
    
    public boolean _(final long n, final long n2) {
        return new Date().getTime() > n + n2 * 86400000L;
    }
    
    public boolean b(final String s) {
        final String h = this.h(s.j);
        return h != null && h.equalsIgnoreCase(s);
    }
    
    public boolean m(final String s) {
        final String h = this.h(s.k);
        return h != null && h.equalsIgnoreCase(s);
    }
    
    public void _(final int n, final int n2) {
        this._(s.t + n + s.u + n2);
    }
    
    public int _(final String s, final int n, final int n2, final int n3) {
        int int1 = n3;
        String s2;
        if (this.p != null) {
            s2 = this.p._(s);
        }
        else {
            s2 = this.o.getParameter(s);
        }
        if (s2 != null) {
            try {
                int1 = Integer.parseInt(s2);
                if (!this._(int1, n, n2)) {
                    int1 = n3;
                    this._(String.valueOf(s) + s.v + n + s.w + n2 + s.x);
                }
            }
            catch (NumberFormatException ex) {
                this._(s.y + s + s.x);
            }
        }
        return int1;
    }
    
    public int _(final String s, final int n, final int n2) {
        int int1 = 0;
        String s2;
        if (this.p != null) {
            s2 = this.p._(s);
        }
        else {
            s2 = this.o.getParameter(s);
        }
        if (s2 != null) {
            try {
                int1 = Integer.parseInt(s2);
                if (!this._(int1, n, n2)) {
                    this.n(String.valueOf(s) + s.v + n + s.w + n2);
                }
            }
            catch (NumberFormatException ex) {
                this.n(s.y + s);
            }
        }
        else {
            this.n(String.valueOf(s) + s.z);
        }
        return int1;
    }
    
    public int _(final String s) {
        return this._(s, 0, 0);
    }
    
    public int b(final String s, final int n) {
        return this._(s, 0, 0, n);
    }
    
    public boolean _(final int n, final int n2, final int n3) {
        return n2 == n3 || (n >= n2 && n <= n3);
    }
    
    public String h(final String s) {
        String s2;
        if (this.p != null) {
            s2 = this.p._(s);
        }
        else {
            s2 = this.o.getParameter(s);
        }
        if (s2 == null) {
            this.n(String.valueOf(s) + s.z);
        }
        return s2;
    }
    
    public String a(final String s, final String s2) {
        String s3;
        if (this.p != null) {
            s3 = this.p._(s);
        }
        else {
            s3 = this.o.getParameter(s);
        }
        if (s3 == null) {
            s3 = s2;
        }
        return s3;
    }
    
    public Color a(final String s, final Color color) {
        Color color2 = color;
        String s2;
        if (this.p != null) {
            s2 = this.p._(s);
        }
        else {
            s2 = this.o.getParameter(s);
        }
        if (s2 != null) {
            try {
                color2 = b(s2);
            }
            catch (IllegalArgumentException ex2) {
                try {
                    color2 = _(s2);
                }
                catch (IllegalArgumentException ex) {
                    this._(ex.getMessage());
                    this._(s.y + s + s.x);
                }
            }
        }
        return color2;
    }
    
    public Color a(final String s) {
        Color color = null;
        String s2;
        if (this.p != null) {
            s2 = this.p._(s);
        }
        else {
            s2 = this.o.getParameter(s);
        }
        if (s2 != null) {
            try {
                color = b(s2);
                return color;
            }
            catch (IllegalArgumentException ex2) {
                try {
                    color = _(s2);
                }
                catch (IllegalArgumentException ex) {
                    this._(ex.getMessage());
                    this.n(s.y + s);
                }
            }
        }
        this.n(String.valueOf(s) + s.z);
        return color;
    }
    
    public boolean _(final String s, final boolean b) {
        boolean b2 = b;
        String s2;
        if (this.p != null) {
            s2 = this.p._(s);
        }
        else {
            s2 = this.o.getParameter(s);
        }
        if (s2 != null) {
            final String lowerCase = s2.toLowerCase();
            b2 = (lowerCase.startsWith(s.A) || lowerCase.startsWith(s.B) || lowerCase.equals(s.C) || lowerCase.equals(s.D) || lowerCase.equals(s.E));
        }
        return b2;
    }
    
    public int _(final String s, final int n) {
        int n2 = n;
        String s2 = null;
        try {
            if (this.p != null) {
                s2 = this.p._(s);
            }
            else {
                s2 = this.o.getParameter(s);
            }
            if (s2 != null) {
                if (s2.equalsIgnoreCase(s.F)) {
                    n2 = 0;
                }
                else if (s2.equalsIgnoreCase(s.G)) {
                    n2 = 1;
                }
                else if (s2.equalsIgnoreCase(s.H)) {
                    n2 = 2;
                }
                else {
                    if (!s2.equalsIgnoreCase(s.I)) {
                        throw new Exception();
                    }
                    n2 = 3;
                }
            }
        }
        catch (Exception ex) {
            this._(s.J + s2 + s.x);
        }
        return n2;
    }
    
    public String b(final String s, final String s2) {
        String s3 = s2;
        String s4;
        if (this.p != null) {
            s4 = this.p._(s);
        }
        else {
            s4 = this.o.getParameter(s);
        }
        try {
            if (s4 != null) {
                if (s4.equalsIgnoreCase(s.K)) {
                    s3 = s.L;
                }
                else if (s4.equalsIgnoreCase(s.M)) {
                    s3 = s.N;
                }
                else if (s4.equalsIgnoreCase(s.O)) {
                    s3 = s.P;
                }
                else {
                    if (!s4.equalsIgnoreCase(s.Q)) {
                        throw new Exception();
                    }
                    s3 = s.R;
                }
            }
        }
        catch (Exception ex) {
            this._(s.S + s4 + s.x);
        }
        return s3;
    }
    
    public void a(final Vector vector, final int n) {
        final MediaTracker mediaTracker = new MediaTracker(this.o);
        for (int i = 0; i < vector.size(); ++i) {
            mediaTracker.addImage(vector.elementAt(i), n);
        }
        try {
            mediaTracker.waitForID(n);
        }
        catch (InterruptedException ex) {
            this.n(s.T);
        }
        if ((mediaTracker.statusID(n, false) & 0x6) != 0x0) {
            this.n(s.U);
        }
    }
    
    public Image[] a(final String s, final int n) {
        final Vector vector = new Vector<Image>();
        int n2 = 1;
        String a;
        while ((a = this.a(String.valueOf(s) + n2++, (String)null)) != null) {
            vector.addElement(this.o.getImage(this.o.getCodeBase(), a));
        }
        this.a(vector, n);
        final Image[] array = new Image[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public AudioClip b(String a) {
        if (!a.endsWith(s.V)) {
            a = this.a(a, (String)null);
        }
        if (a != null) {
            final AudioClip audioClip = this.o.getAudioClip(this.o.getCodeBase(), a);
            _(audioClip);
            a(audioClip);
            return audioClip;
        }
        return null;
    }
    
    public boolean a() {
        try {
            return System.getProperty(s.W).startsWith(s.X);
        }
        catch (SecurityException ex) {
            return true;
        }
    }
    
    public int a(final String s, final boolean b, final Graphics graphics) {
        final Dimension size = this.o.size();
        return _(s, b, new Rectangle(0, 0, size.width, size.height), graphics);
    }
    
    public void b(final Graphics graphics) {
        graphics.setColor(this.o.getBackground());
        graphics.fillRect(0, 0, this.o.size().width, this.o.size().height);
    }
    
    public Class b(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            this.n(s.Y + s);
            return null;
        }
    }
    
    public void _(final String s) {
        System.out.println(String.valueOf(this.o.getClass().getName()) + s.Z + s);
    }
    
    public boolean n(final String s) {
        return this.b(s, (Exception)null);
    }
    
    public boolean b(final String s, final Exception ex) {
        this.r = true;
        if (this.q != null) {
            this.o.showStatus(this.q._());
        }
        this._(s._a + s);
        if (ex != null) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public void b(final boolean s) {
        this.s = s;
    }
    
    public boolean b() {
        return this.s;
    }
    
    public boolean d() {
        return this.r;
    }
    
    public static int _(final String s, final boolean b, final Rectangle rectangle, final Graphics graphics) {
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(graphics.getFont());
        if (b) {
            return rectangle.x + (rectangle.width - fontMetrics.stringWidth(s)) / 2;
        }
        return rectangle.y + rectangle.height % 2 + rectangle.height / 2 + (fontMetrics.getAscent() - fontMetrics.getDescent()) / 2;
    }
    
    private static String i(final String s) {
        if (s == null || s.equals(s.aa)) {}
        return s.ba;
    }
    
    public static boolean a(final long n) {
        return n % 4L == 0L && (n % 100L != 0L || n % 400L == 0L);
    }
    
    public static void _(final AudioClip audioClip) {
        if (audioClip != null) {
            audioClip.play();
        }
    }
    
    public static void a(final AudioClip audioClip) {
        if (audioClip != null) {
            audioClip.stop();
        }
    }
    
    public static void b(final AudioClip audioClip) {
        if (audioClip != null) {
            audioClip.loop();
        }
    }
    
    public static Color b(String substring) {
        if (substring.startsWith(s.ca)) {
            substring = substring.substring(1);
        }
        if (substring.length() == 6) {
            return new Color(Integer.parseInt(substring, 16));
        }
        throw new IllegalArgumentException(String.valueOf(substring) + s.da);
    }
    
    public static Color a(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        if (stringTokenizer.countTokens() == 3) {
            return new Color(Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()));
        }
        throw new IllegalArgumentException(String.valueOf(s) + s.da);
    }
    
    public static Color _(final String s) {
        return a(s, s.ea);
    }
    
    public static void a(final int n, final int n2, final int n3, final int n4, final Graphics graphics) {
        graphics.drawLine(n, n2, n + n3, n2);
        graphics.drawLine(n + n3, n2, n + n3, n2 + n4);
        graphics.drawLine(n, n2, n, n2 + n4);
        graphics.drawLine(n, n2 + n4, n + n3, n2 + n4);
    }
    
    public static void _(final Rectangle rectangle, final Graphics graphics) {
        a(rectangle.x, rectangle.y, rectangle.width, rectangle.height, graphics);
    }
    
    public static void b(final Rectangle rectangle, final Color color, final Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public static void _(final int n, final int n2, final int n3, final int n4, final Color color, final Graphics graphics) {
        b(new Rectangle(n, n2, n3, n4), color, graphics);
    }
    
    public static Color _(final Color color) {
        Color color2 = color.brighter();
        if (color2.equals(color)) {
            color2 = color.darker();
        }
        return color2;
    }
    
    public static String b(final String s, final String s2, final String s3) {
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
    
    public static void b(final InputStream inputStream) {
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
    
    public static void _(final long n) {
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
        s.e = a(s.e);
        s.f = a(s.f);
        s.g = a(s.g);
        s.h = a(s.h);
        s.i = a(s.i);
        s.j = a(s.j);
        s.k = a(s.k);
        s.t = a(s.t);
        s.u = a(s.u);
        s.v = a(s.v);
        s.w = a(s.w);
        s.x = a(s.x);
        s.y = a(s.y);
        s.z = a(s.z);
        s.A = a(s.A);
        s.B = a(s.B);
        s.C = a(s.C);
        s.D = a(s.D);
        s.E = a(s.E);
        s.F = a(s.F);
        s.G = a(s.G);
        s.H = a(s.H);
        s.I = a(s.I);
        s.J = a(s.J);
        s.K = a(s.K);
        s.L = a(s.L);
        s.M = a(s.M);
        s.N = a(s.N);
        s.O = a(s.O);
        s.P = a(s.P);
        s.Q = a(s.Q);
        s.R = a(s.R);
        s.S = a(s.S);
        s.T = a(s.T);
        s.U = a(s.U);
        s.V = a(s.V);
        s.W = a(s.W);
        s.X = a(s.X);
        s.Y = a(s.Y);
        s.Z = a(s.Z);
        s._a = a(s._a);
        s.aa = a(s.aa);
        s.ba = a(s.ba);
        s.ca = a(s.ca);
        s.da = a(s.da);
        s.ea = a(s.ea);
        s.fa = a(s.fa);
        s.ga = a(s.ga);
        s.ha = a(s.ha);
        s.ia = a(s.ia);
        s.ja = a(s.ja);
        s.ka = a(s.ka);
        s.la = a(s.la);
        s.ma = a(s.ma);
        l = i(s.fa);
        n = new String[] { s.ga, s.ha, s.ia, s.ja, s.ka, s.la, s.ma };
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0x12CB2);
        }
        return new String(array);
    }
}
