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

public class q
{
    public static final String E;
    public static final long F = 86400000L;
    public static final String[] G;
    private Applet H;
    private r I;
    private p J;
    private boolean K;
    private boolean L;
    private static String t = "\u4fac\u4fb7\u4fae\u4fae";
    private static String y = "\u4fa1\u4fad\u4fac\u4fa4\u4fab\u4fa5";
    private static String z = "\u4fb0\u4fa7\u4fa3\u4fa6\u4fab\u4fac\u4fa5\u4fe2";
    private static String A = "\u4fe2\u4faa\u4fa3\u4fb1\u4fe2\u4fa4\u4fa3\u4fab\u4fae\u4fa7\u4fa6";
    private static String B = "\u4faa\u4fb6\u4fb6\u4fb2";
    private static String C = "\u4fa1\u4fad\u4fb2\u4fbb\u4fb0\u4fab\u4fa5\u4faa\u4fb6";
    private static String D = "\u4fae\u4fab\u4fa1\u4fa7\u4fac\u4fb1\u4fa7\u4fa7";
    private static String M = "\u4fb0\u4fa7\u4fa1\u4fad\u4faf\u4faf\u4fa7\u4fac\u4fa6\u4fa7\u4fa6\u4fe2\u4fb1\u4fab\u4fb8\u4fa7\u4ff8\u4fe2\u4fb5\u4fab\u4fa6\u4fb6\u4faa\u4fff";
    private static String N = "\u4fe2\u4faa\u4fa7\u4fab\u4fa5\u4faa\u4fb6\u4fff";
    private static String O = "\u4fe2\u4fb4\u4fa3\u4fae\u4fb7\u4fa7\u4fe2\u4faa\u4fa3\u4fb1\u4fe2\u4fb6\u4fad\u4fe2\u4fa0\u4fa7\u4fe2\u4fa0\u4fa7\u4fb6\u4fb5\u4fa7\u4fa7\u4fac\u4fe2";
    private static String P = "\u4fe2\u4fa3\u4fac\u4fa6\u4fe2";
    private static String Q = "\u4ff9\u4fe2\u4fb7\u4fb1\u4fab\u4fac\u4fa5\u4fe2\u4fa6\u4fa7\u4fa4\u4fa3\u4fb7\u4fae\u4fb6";
    private static String R = "\u4fab\u4fae\u4fae\u4fa7\u4fa5\u4fa3\u4fae\u4fe2\u4fb4\u4fa3\u4fae\u4fb7\u4fa7\u4fe2\u4fa4\u4fad\u4fb0\u4fe2";
    private static String S = "\u4fe2\u4fb2\u4fa3\u4fb0\u4fa3\u4faf\u4fa7\u4fb6\u4fa7\u4fb0\u4fe2\u4fab\u4fb1\u4fe2\u4faf\u4fab\u4fb1\u4fb1\u4fab\u4fac\u4fa5";
    private static String T = "\u4fbb";
    private static String U = "\u4fb6";
    private static String V = "\u4ff3";
    private static String W = "\u4fad\u4fac";
    private static String X = "\u4fe9";
    private static String Y = "\u4fb2\u4fae\u4fa3\u4fab\u4fac";
    private static String Z = "\u4fa0\u4fad\u4fae\u4fa6";
    private static String _a = "\u4fab\u4fb6\u4fa3\u4fae\u4fab\u4fa1";
    private static String aa = "\u4fa0\u4fad\u4fae\u4fa6\u4fab\u4fb6\u4fa3\u4fae\u4fab\u4fa1";
    private static String ba = "\u4fab\u4fae\u4fae\u4fa7\u4fa5\u4fa3\u4fae\u4fe2\u4fb4\u4fa3\u4fae\u4fb7\u4fa7\u4fe2\u4fa4\u4fad\u4fb0\u4fe2\u4fa4\u4fad\u4fac\u4fb6\u4fb1\u4fb6\u4fbb\u4fae\u4fa7\u4ff8\u4fe2";
    private static String ca = "\u4fb6\u4fab\u4faf\u4fa7\u4fb1\u4fb0\u4fad\u4faf\u4fa3\u4fac";
    private static String da = "\u4f96\u4fab\u4faf\u4fa7\u4fb1\u4f90\u4fad\u4faf\u4fa3\u4fac";
    private static String ea = "\u4fa3\u4fb0\u4fab\u4fa3\u4fae";
    private static String fa = "\u4f83\u4fb0\u4fab\u4fa3\u4fae";
    private static String ga = "\u4fa1\u4fad\u4fb7\u4fb0\u4fab\u4fa7\u4fb0";
    private static String ha = "\u4f81\u4fad\u4fb7\u4fb0\u4fab\u4fa7\u4fb0";
    private static String ia = "\u4fa6\u4fab\u4fa3\u4fae\u4fad\u4fa5";
    private static String ja = "\u4f86\u4fab\u4fa3\u4fae\u4fad\u4fa5";
    private static String ka = "\u4fab\u4fae\u4fae\u4fa7\u4fa5\u4fa3\u4fae\u4fe2\u4fb4\u4fa3\u4fae\u4fb7\u4fa7\u4fe2\u4fa4\u4fad\u4fb0\u4fe2\u4fa4\u4fad\u4fac\u4fb6\u4fa4\u4fa3\u4fa1\u4fa7\u4ff8\u4fe2";
    private static String la = "\u4fab\u4faf\u4fa3\u4fa5\u4fa7\u4fef\u4fae\u4fad\u4fa3\u4fa6\u4fab\u4fac\u4fa5\u4fe2\u4faa\u4fa3\u4fb1\u4fe2\u4fa0\u4fa7\u4fa7\u4fac\u4fe2\u4fab\u4fac\u4fb6\u4fa7\u4fb0\u4fb0\u4fb7\u4fb2\u4fb6\u4fa7\u4fa6";
    private static String ma = "\u4fad\u4fac\u4fa7\u4fe2\u4fad\u4fb0\u4fe2\u4faf\u4fad\u4fb0\u4fa7\u4fe2\u4fab\u4faf\u4fa3\u4fa5\u4fa7\u4fb1\u4fe2\u4faf\u4fa3\u4fbb\u4fe2\u4fac\u4fad\u4fb6\u4fe2\u4faa\u4fa3\u4fb4\u4fa7\u4fe2\u4fae\u4fad\u4fa3\u4fa6\u4fa7\u4fa6\u4fe2\u4fa1\u4fad\u4fb0\u4fb0\u4fa7\u4fa1\u4fb6\u4fae\u4fbb";
    private static String na = "\u4fec\u4fa3\u4fb7";
    private static String oa = "\u4fad\u4fb1\u4fec\u4fac\u4fa3\u4faf\u4fa7";
    private static String pa = "\u4f95\u4fab\u4fac";
    private static String qa = "\u4fa1\u4fad\u4fb7\u4fae\u4fa6\u4fe2\u4fac\u4fad\u4fb6\u4fe2\u4fae\u4fad\u4fa3\u4fa6\u4fe2";
    private static String ra = "\u4ff8\u4fe2";
    private static String sa = "\u4fa4\u4fa3\u4fb6\u4fa3\u4fae\u4fe2\u4fa7\u4fb0\u4fb0\u4fad\u4fb0\u4ff8\u4fe2";
    private static String ta = "";
    private static String ua = "\u4f96\u4faa\u4fab\u4fb1\u4fe2\u4fa3\u4fb2\u4fb2\u4fae\u4fa7\u4fb6\u4fe2\u4fab\u4fb1\u4fe2\u4fa1\u4fad\u4fb2\u4fbb\u4fb0\u4fab\u4fa5\u4faa\u4fb6\u4fa7\u4fa6";
    private static String va = "\u4fe1";
    private static String wa = "\u4fe2\u4fa1\u4fa3\u4fac\u4fe5\u4fb6\u4fe2\u4fa0\u4fa7\u4fe2\u4fb2\u4fa3\u4fb0\u4fb1\u4fa7\u4fa6";
    private static String xa = "\u4fee";
    private static String ya = "\u4f88\u4fa3\u4fb4\u4fa3\u4fae\u4fad\u4fb4\u4fa7\u4fb0\u4fb1\u4fe2\u4f95\u4fa7\u4fa0\u4fa6\u4fa7\u4fb1\u4fab\u4fa5\u4fac";
    private static String za = "\u4fa8\u4fa3\u4fb4\u4fa3\u4fae\u4fad\u4fb4\u4fa7\u4fb0\u4fb1\u4fec\u4fa1\u4fad\u4faf";
    private static String Aa = "\u4fa4\u4fb5\u4fa7\u4fac\u4fa6\u4fec\u4fa1\u4fad\u4faf";
    private static String Ba = "\u4fa6\u4fa3\u4fab\u4fae\u4fbb\u4fb5\u4fad\u4fb0\u4fa6\u4fb1\u4fa7\u4fa3\u4fb0\u4fa1\u4faa\u4fec\u4fa1\u4fad\u4faf";
    private static String Ca = "\u4fb7\u4fac\u4fb0\u4fa7\u4fa3\u4fae\u4fa1\u4fa3\u4fb1\u4fab\u4fac\u4fad\u4fec\u4fa1\u4fad\u4faf";
    private static String Da = "\u4fa1\u4fb0\u4fbb\u4fb2\u4fb6\u4fab\u4fa1\u4fa4\u4fad\u4fb0\u4fb7\u4faf\u4fec\u4fa1\u4fad\u4faf";
    private static String Ea = "\u4fa1\u4fae\u4fa3\u4fb1\u4fb1\u4fab\u4fa1\u4faf\u4fad\u4fb4\u4fab\u4fa7\u4fb3\u4fb7\u4fab\u4fb8\u4fec\u4fa1\u4fad\u4faf";
    private static String Fa = "\u4fa6\u4fa3\u4fab\u4fae\u4fbb\u4fb5\u4fad\u4fb0\u4fa6\u4fa5\u4fa3\u4faf\u4fa7\u4fec\u4fa1\u4fad\u4faf";
    
    public q(final Applet h, final p j) {
        if (h == null) {
            throw new IllegalArgumentException(q.t);
        }
        this.H = h;
        this.J = j;
        final String _ = this._(q.y, null);
        if (_ != null) {
            this.I = new r();
            if (!this.I.b(this.H.getCodeBase(), _, false)) {
                this.b(q.z + _ + q.A);
                this.I = null;
            }
        }
    }
    
    public boolean a() {
        return this.H.getDocumentBase().getProtocol().startsWith(q.B);
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
        return this.H.getDocumentBase().getHost().indexOf(s) != -1;
    }
    
    public boolean b(final long n, final long n2) {
        return new Date().getTime() > n + n2 * 86400000L;
    }
    
    public boolean b(final String s) {
        final String n = this.n(q.C);
        return n != null && n.equalsIgnoreCase(s);
    }
    
    public boolean n(final String s) {
        final String n = this.n(q.D);
        return n != null && n.equalsIgnoreCase(s);
    }
    
    public void a(final int n, final int n2) {
        this.b(q.M + n + q.N + n2);
    }
    
    public int b(final String s, final int n, final int n2, final int n3) {
        int int1 = n3;
        String s2;
        if (this.I != null) {
            s2 = this.I.b(s);
        }
        else {
            s2 = this.H.getParameter(s);
        }
        if (s2 != null) {
            try {
                int1 = Integer.parseInt(s2);
                if (!this.a(int1, n, n2)) {
                    int1 = n3;
                    this.b(String.valueOf(s) + q.O + n + q.P + n2 + q.Q);
                }
            }
            catch (NumberFormatException ex) {
                this.b(q.R + s + q.Q);
            }
        }
        return int1;
    }
    
    public int a(final String s, final int n, final int n2) {
        int int1 = 0;
        String s2;
        if (this.I != null) {
            s2 = this.I.b(s);
        }
        else {
            s2 = this.H.getParameter(s);
        }
        if (s2 != null) {
            try {
                int1 = Integer.parseInt(s2);
                if (!this.a(int1, n, n2)) {
                    this.c(String.valueOf(s) + q.O + n + q.P + n2);
                }
            }
            catch (NumberFormatException ex) {
                this.c(q.R + s);
            }
        }
        else {
            this.c(String.valueOf(s) + q.S);
        }
        return int1;
    }
    
    public int a(final String s) {
        return this.a(s, 0, 0);
    }
    
    public int b(final String s, final int n) {
        return this.b(s, 0, 0, n);
    }
    
    public boolean a(final int n, final int n2, final int n3) {
        return n2 == n3 || (n >= n2 && n <= n3);
    }
    
    public String n(final String s) {
        String s2;
        if (this.I != null) {
            s2 = this.I.b(s);
        }
        else {
            s2 = this.H.getParameter(s);
        }
        if (s2 == null) {
            this.c(String.valueOf(s) + q.S);
        }
        return s2;
    }
    
    public String _(final String s, final String s2) {
        String s3;
        if (this.I != null) {
            s3 = this.I.b(s);
        }
        else {
            s3 = this.H.getParameter(s);
        }
        if (s3 == null) {
            s3 = s2;
        }
        return s3;
    }
    
    public Color a(final String s, final Color color) {
        Color color2 = color;
        String s2;
        if (this.I != null) {
            s2 = this.I.b(s);
        }
        else {
            s2 = this.H.getParameter(s);
        }
        if (s2 != null) {
            try {
                color2 = a(s2);
            }
            catch (IllegalArgumentException ex2) {
                try {
                    color2 = b(s2);
                }
                catch (IllegalArgumentException ex) {
                    this.b(ex.getMessage());
                    this.b(q.R + s + q.Q);
                }
            }
        }
        return color2;
    }
    
    public Color _(final String s) {
        Color color = null;
        String s2;
        if (this.I != null) {
            s2 = this.I.b(s);
        }
        else {
            s2 = this.H.getParameter(s);
        }
        if (s2 != null) {
            try {
                color = a(s2);
                return color;
            }
            catch (IllegalArgumentException ex2) {
                try {
                    color = b(s2);
                }
                catch (IllegalArgumentException ex) {
                    this.b(ex.getMessage());
                    this.c(q.R + s);
                }
            }
        }
        this.c(String.valueOf(s) + q.S);
        return color;
    }
    
    public boolean a(final String s, final boolean b) {
        boolean b2 = b;
        String s2;
        if (this.I != null) {
            s2 = this.I.b(s);
        }
        else {
            s2 = this.H.getParameter(s);
        }
        if (s2 != null) {
            final String lowerCase = s2.toLowerCase();
            b2 = (lowerCase.startsWith(q.T) || lowerCase.startsWith(q.U) || lowerCase.equals(q.V) || lowerCase.equals(q.W) || lowerCase.equals(q.X));
        }
        return b2;
    }
    
    public int _(final String s, final int n) {
        int n2 = n;
        String s2 = null;
        try {
            if (this.I != null) {
                s2 = this.I.b(s);
            }
            else {
                s2 = this.H.getParameter(s);
            }
            if (s2 != null) {
                if (s2.equalsIgnoreCase(q.Y)) {
                    n2 = 0;
                }
                else if (s2.equalsIgnoreCase(q.Z)) {
                    n2 = 1;
                }
                else if (s2.equalsIgnoreCase(q._a)) {
                    n2 = 2;
                }
                else {
                    if (!s2.equalsIgnoreCase(q.aa)) {
                        throw new Exception();
                    }
                    n2 = 3;
                }
            }
        }
        catch (Exception ex) {
            this.b(q.ba + s2 + q.Q);
        }
        return n2;
    }
    
    public String a(final String s, final String s2) {
        String s3 = s2;
        String s4;
        if (this.I != null) {
            s4 = this.I.b(s);
        }
        else {
            s4 = this.H.getParameter(s);
        }
        try {
            if (s4 != null) {
                if (s4.equalsIgnoreCase(q.ca)) {
                    s3 = q.da;
                }
                else if (s4.equalsIgnoreCase(q.ea)) {
                    s3 = q.fa;
                }
                else if (s4.equalsIgnoreCase(q.ga)) {
                    s3 = q.ha;
                }
                else {
                    if (!s4.equalsIgnoreCase(q.ia)) {
                        throw new Exception();
                    }
                    s3 = q.ja;
                }
            }
        }
        catch (Exception ex) {
            this.b(q.ka + s4 + q.Q);
        }
        return s3;
    }
    
    public void _(final Vector vector, final int n) {
        final MediaTracker mediaTracker = new MediaTracker(this.H);
        for (int i = 0; i < vector.size(); ++i) {
            mediaTracker.addImage(vector.elementAt(i), n);
        }
        try {
            mediaTracker.waitForID(n);
        }
        catch (InterruptedException ex) {
            this.c(q.la);
        }
        if ((mediaTracker.statusID(n, false) & 0x6) != 0x0) {
            this.c(q.ma);
        }
    }
    
    public Image[] _(final String s, final int n) {
        final Vector vector = new Vector<Image>();
        int n2 = 1;
        String _;
        while ((_ = this._(String.valueOf(s) + n2++, null)) != null) {
            vector.addElement(this.H.getImage(this.H.getCodeBase(), _));
        }
        this._(vector, n);
        final Image[] array = new Image[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public AudioClip b(String _) {
        if (!_.endsWith(q.na)) {
            _ = this._(_, null);
        }
        if (_ != null) {
            final AudioClip audioClip = this.H.getAudioClip(this.H.getCodeBase(), _);
            a(audioClip);
            b(audioClip);
            return audioClip;
        }
        return null;
    }
    
    public boolean b() {
        try {
            return System.getProperty(q.oa).startsWith(q.pa);
        }
        catch (SecurityException ex) {
            return true;
        }
    }
    
    public int _(final String s, final boolean b, final Graphics graphics) {
        final Dimension size = this.H.getSize();
        return _(s, b, new Rectangle(0, 0, size.width, size.height), graphics);
    }
    
    public void _(final Graphics graphics) {
        graphics.setColor(this.H.getBackground());
        graphics.fillRect(0, 0, this.H.getSize().width, this.H.getSize().height);
    }
    
    public Class b(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            this.c(q.qa + s);
            return null;
        }
    }
    
    public void b(final String s) {
        System.out.println(String.valueOf(this.H.getClass().getName()) + q.ra + s);
    }
    
    public boolean c(final String s) {
        return this.a(s, (Exception)null);
    }
    
    public boolean a(final String s, final Exception ex) {
        this.K = true;
        if (this.J != null) {
            this.H.showStatus(this.J.b());
        }
        this.b(q.sa + s);
        if (ex != null) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public void a(final boolean l) {
        this.L = l;
    }
    
    public boolean _() {
        return this.L;
    }
    
    public boolean d() {
        return this.K;
    }
    
    public static int _(final String s, final boolean b, final Rectangle rectangle, final Graphics graphics) {
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(graphics.getFont());
        if (b) {
            return rectangle.x + (rectangle.width - fontMetrics.stringWidth(s)) / 2;
        }
        return rectangle.y + rectangle.height % 2 + rectangle.height / 2 + (fontMetrics.getAscent() - fontMetrics.getDescent()) / 2;
    }
    
    private static String c(final String s) {
        if (s == null || s.equals(q.ta)) {}
        return q.ua;
    }
    
    public static boolean _(final long n) {
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
    
    public static Color a(String substring) {
        if (substring.startsWith(q.va)) {
            substring = substring.substring(1);
        }
        if (substring.length() == 6) {
            return new Color(Integer.parseInt(substring, 16));
        }
        throw new IllegalArgumentException(String.valueOf(substring) + q.wa);
    }
    
    public static Color b(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        if (stringTokenizer.countTokens() == 3) {
            return new Color(Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()));
        }
        throw new IllegalArgumentException(String.valueOf(s) + q.wa);
    }
    
    public static Color b(final String s) {
        return b(s, q.xa);
    }
    
    public static void b(final int n, final int n2, final int n3, final int n4, final Graphics graphics) {
        graphics.drawLine(n, n2, n + n3, n2);
        graphics.drawLine(n + n3, n2, n + n3, n2 + n4);
        graphics.drawLine(n, n2, n, n2 + n4);
        graphics.drawLine(n, n2 + n4, n + n3, n2 + n4);
    }
    
    public static void b(final Rectangle rectangle, final Graphics graphics) {
        b(rectangle.x, rectangle.y, rectangle.width, rectangle.height, graphics);
    }
    
    public static void _(final Rectangle rectangle, final Color color, final Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public static void b(final int n, final int n2, final int n3, final int n4, final Color color, final Graphics graphics) {
        _(new Rectangle(n, n2, n3, n4), color, graphics);
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
    
    public static void a(final InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            }
            catch (IOException ex) {}
        }
    }
    
    public static void a(final OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            }
            catch (IOException ex) {}
        }
    }
    
    public static void a(final Reader reader) {
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
    
    public static void _(final Object o) {
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
        q.t = _(q.t);
        q.y = _(q.y);
        q.z = _(q.z);
        q.A = _(q.A);
        q.B = _(q.B);
        q.C = _(q.C);
        q.D = _(q.D);
        q.M = _(q.M);
        q.N = _(q.N);
        q.O = _(q.O);
        q.P = _(q.P);
        q.Q = _(q.Q);
        q.R = _(q.R);
        q.S = _(q.S);
        q.T = _(q.T);
        q.U = _(q.U);
        q.V = _(q.V);
        q.W = _(q.W);
        q.X = _(q.X);
        q.Y = _(q.Y);
        q.Z = _(q.Z);
        q._a = _(q._a);
        q.aa = _(q.aa);
        q.ba = _(q.ba);
        q.ca = _(q.ca);
        q.da = _(q.da);
        q.ea = _(q.ea);
        q.fa = _(q.fa);
        q.ga = _(q.ga);
        q.ha = _(q.ha);
        q.ia = _(q.ia);
        q.ja = _(q.ja);
        q.ka = _(q.ka);
        q.la = _(q.la);
        q.ma = _(q.ma);
        q.na = _(q.na);
        q.oa = _(q.oa);
        q.pa = _(q.pa);
        q.qa = _(q.qa);
        q.ra = _(q.ra);
        q.sa = _(q.sa);
        q.ta = _(q.ta);
        q.ua = _(q.ua);
        q.va = _(q.va);
        q.wa = _(q.wa);
        q.xa = _(q.xa);
        q.ya = _(q.ya);
        q.za = _(q.za);
        q.Aa = _(q.Aa);
        q.Ba = _(q.Ba);
        q.Ca = _(q.Ca);
        q.Da = _(q.Da);
        q.Ea = _(q.Ea);
        q.Fa = _(q.Fa);
        E = c(q.ya);
        G = new String[] { q.za, q.Aa, q.Ba, q.Ca, q.Da, q.Ea, q.Fa };
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0x14FC2);
        }
        return new String(array);
    }
}
