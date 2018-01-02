import java.util.Hashtable;
import java.io.Writer;
import java.io.Reader;
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;
import java.util.Calendar;
import java.awt.Font;
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

public class default
{
    public static final String v;
    public static final long w = 86400000L;
    public static final String[] x;
    private Applet g;
    private final y;
    private continue z;
    private boolean A;
    private boolean B;
    private static String qb = "\ubff8\ubfe3\ubffa\ubffa";
    private static String rb = "\ubff5\ubff9\ubff8\ubff0\ubfff\ubff1";
    private static String sb = "\ubfe4\ubff3\ubff7\ubff2\ubfff\ubff8\ubff1\ubfb6";
    private static String tb = "\ubfb6\ubffe\ubff7\ubfe5\ubfb6\ubff0\ubff7\ubfff\ubffa\ubff3\ubff2";
    private static String _ = "\ubffe\ubfe2\ubfe2\ubfe6";
    private static String a = "\ubff5\ubff9\ubfe6\ubfef\ubfe4\ubfff\ubff1\ubffe\ubfe2";
    private static String b = "\ubffa\ubfff\ubff5\ubff3\ubff8\ubfe5\ubff3\ubff3";
    private static String C = "\ubfe4\ubff3\ubff5\ubff9\ubffb\ubffb\ubff3\ubff8\ubff2\ubff3\ubff2\ubfb6\ubfe5\ubfff\ubfec\ubff3\ubfac\ubfb6\ubfe1\ubfff\ubff2\ubfe2\ubffe\ubfab";
    private static String D = "\ubfb6\ubffe\ubff3\ubfff\ubff1\ubffe\ubfe2\ubfab";
    private static String E = "\ubfb6\ubfe0\ubff7\ubffa\ubfe3\ubff3\ubfb6\ubffe\ubff7\ubfe5\ubfb6\ubfe2\ubff9\ubfb6\ubff4\ubff3\ubfb6\ubff4\ubff3\ubfe2\ubfe1\ubff3\ubff3\ubff8\ubfb6";
    private static String F = "\ubfb6\ubff7\ubff8\ubff2\ubfb6";
    private static String G = "\ubfad\ubfb6\ubfe3\ubfe5\ubfff\ubff8\ubff1\ubfb6\ubff2\ubff3\ubff0\ubff7\ubfe3\ubffa\ubfe2";
    private static String H = "\ubfff\ubffa\ubffa\ubff3\ubff1\ubff7\ubffa\ubfb6\ubfe0\ubff7\ubffa\ubfe3\ubff3\ubfb6\ubff0\ubff9\ubfe4\ubfb6";
    private static String I = "\ubfb6\ubfe6\ubff7\ubfe4\ubff7\ubffb\ubff3\ubfe2\ubff3\ubfe4\ubfb6\ubfff\ubfe5\ubfb6\ubffb\ubfff\ubfe5\ubfe5\ubfff\ubff8\ubff1";
    private static String J = "\ubfef";
    private static String K = "\ubfe2";
    private static String L = "\ubfa7";
    private static String M = "\ubff9\ubff8";
    private static String N = "\ubfbd";
    private static String O = "\ubfe6\ubffa\ubff7\ubfff\ubff8";
    private static String P = "\ubff4\ubff9\ubffa\ubff2";
    private static String Q = "\ubfff\ubfe2\ubff7\ubffa\ubfff\ubff5";
    private static String R = "\ubff4\ubff9\ubffa\ubff2\ubfff\ubfe2\ubff7\ubffa\ubfff\ubff5";
    private static String S = "\ubfff\ubffa\ubffa\ubff3\ubff1\ubff7\ubffa\ubfb6\ubfe0\ubff7\ubffa\ubfe3\ubff3\ubfb6\ubff0\ubff9\ubfe4\ubfb6\ubff0\ubff9\ubff8\ubfe2\ubfe5\ubfe2\ubfef\ubffa\ubff3\ubfac\ubfb6";
    private static String T = "\ubfe2\ubfff\ubffb\ubff3\ubfe5\ubfe4\ubff9\ubffb\ubff7\ubff8";
    private static String U = "\ubfc2\ubfff\ubffb\ubff3\ubfe5\ubfc4\ubff9\ubffb\ubff7\ubff8";
    private static String V = "\ubff7\ubfe4\ubfff\ubff7\ubffa";
    private static String W = "\ubfd7\ubfe4\ubfff\ubff7\ubffa";
    private static String X = "\ubff5\ubff9\ubfe3\ubfe4\ubfff\ubff3\ubfe4";
    private static String Y = "\ubfd5\ubff9\ubfe3\ubfe4\ubfff\ubff3\ubfe4";
    private static String Z = "\ubff2\ubfff\ubff7\ubffa\ubff9\ubff1";
    private static String _a = "\ubfd2\ubfff\ubff7\ubffa\ubff9\ubff1";
    private static String aa = "\ubfff\ubffa\ubffa\ubff3\ubff1\ubff7\ubffa\ubfb6\ubfe0\ubff7\ubffa\ubfe3\ubff3\ubfb6\ubff0\ubff9\ubfe4\ubfb6\ubff0\ubff9\ubff8\ubfe2\ubff0\ubff7\ubff5\ubff3\ubfac\ubfb6";
    private static String ba = "\ubfff\ubffb\ubff7\ubff1\ubff3\ubfbb\ubffa\ubff9\ubff7\ubff2\ubfff\ubff8\ubff1\ubfb6\ubffe\ubff7\ubfe5\ubfb6\ubff4\ubff3\ubff3\ubff8\ubfb6\ubfff\ubff8\ubfe2\ubff3\ubfe4\ubfe4\ubfe3\ubfe6\ubfe2\ubff3\ubff2";
    private static String ca = "\ubff9\ubff8\ubff3\ubfb6\ubff9\ubfe4\ubfb6\ubffb\ubff9\ubfe4\ubff3\ubfb6\ubfff\ubffb\ubff7\ubff1\ubff3\ubfe5\ubfb6\ubffb\ubff7\ubfef\ubfb6\ubff8\ubff9\ubfe2\ubfb6\ubffe\ubff7\ubfe0\ubff3\ubfb6\ubffa\ubff9\ubff7\ubff2\ubff3\ubff2\ubfb6\ubff5\ubff9\ubfe4\ubfe4\ubff3\ubff5\ubfe2\ubffa\ubfef";
    private static String da = "\ubfb8\ubff7\ubfe3";
    private static String ea = "\ubff9\ubfe5\ubfb8\ubff8\ubff7\ubffb\ubff3";
    private static String fa = "\ubfc1\ubfff\ubff8";
    private static String ga = "\ubff5\ubff9\ubfe3\ubffa\ubff2\ubfb6\ubff8\ubff9\ubfe2\ubfb6\ubffa\ubff9\ubff7\ubff2\ubfb6";
    private static String ha = "\ubfac\ubfb6";
    private static String ia = "\ubff0\ubff7\ubfe2\ubff7\ubffa\ubfb6\ubff3\ubfe4\ubfe4\ubff9\ubfe4\ubfac\ubfb6";
    private static String ja = "";
    private static String ka = "\ubfd5\ubff9\ubfe6\ubfef\ubfe4\ubfff\ubff1\ubffe\ubfe2\ubfb6\ubfbe\ubff5\ubfbf\ubfb6\ubfb6";
    private static String la = "\ubfba\ubfb6";
    private static String ma = "\ubfc2\ubffe\ubfff\ubfe5\ubfb6\ubff7\ubfe6\ubfe6\ubffa\ubff3\ubfe2\ubfb6\ubfff\ubfe5\ubfb6\ubff5\ubff9\ubfe6\ubfef\ubfe4\ubfff\ubff1\ubffe\ubfe2\ubff3\ubff2";
    private static String na = "\ubfb5";
    private static String oa = "\ubfb6\ubff5\ubff7\ubff8\ubfb1\ubfe2\ubfb6\ubff4\ubff3\ubfb6\ubfe6\ubff7\ubfe4\ubfe5\ubff3\ubff2";
    private static String pa = "\ubfba";
    private static String qa = "\ubfdc\ubff7\ubfe0\ubff7\ubffa\ubff9\ubfe0\ubff3\ubfe4\ubfe5\ubfb6\ubfc1\ubff3\ubff4\ubff2\ubff3\ubfe5\ubfff\ubff1\ubff8";
    private static String ra = "\ubffc\ubff7\ubfe0\ubff7\ubffa\ubff9\ubfe0\ubff3\ubfe4\ubfe5\ubfb8\ubff5\ubff9\ubffb";
    private static String sa = "\ubff0\ubfe1\ubff3\ubff8\ubff2\ubfb8\ubff5\ubff9\ubffb";
    private static String ta = "\ubff2\ubff7\ubfff\ubffa\ubfef\ubfe1\ubff9\ubfe4\ubff2\ubfe5\ubff3\ubff7\ubfe4\ubff5\ubffe\ubfb8\ubff5\ubff9\ubffb";
    private static String ua = "\ubfe3\ubff8\ubfe4\ubff3\ubff7\ubffa\ubff5\ubff7\ubfe5\ubfff\ubff8\ubff9\ubfb8\ubff5\ubff9\ubffb";
    private static String va = "\ubff5\ubfe4\ubfef\ubfe6\ubfe2\ubfff\ubff5\ubff0\ubff9\ubfe4\ubfe3\ubffb\ubfb8\ubff5\ubff9\ubffb";
    private static String wa = "\ubff5\ubffa\ubff7\ubfe5\ubfe5\ubfff\ubff5\ubffb\ubff9\ubfe0\ubfff\ubff3\ubfe7\ubfe3\ubfff\ubfec\ubfb8\ubff5\ubff9\ubffb";
    private static String xa = "\ubff2\ubff7\ubfff\ubffa\ubfef\ubfe1\ubff9\ubfe4\ubff2\ubff1\ubff7\ubffb\ubff3\ubfb8\ubff5\ubff9\ubffb";
    
    public default(final Applet g, final continue z) {
        if (g == null) {
            throw new IllegalArgumentException(default.qb);
        }
        this.g = g;
        this.z = z;
        final String a = this.a(default.rb, null);
        if (a != null) {
            this.y = new final();
            if (!this.y.a(this.g.getCodeBase(), a, false)) {
                this.b(default.sb + a + default.tb);
                this.y = null;
            }
        }
    }
    
    public boolean _() {
        return this.g.getDocumentBase().getProtocol().startsWith(default._);
    }
    
    public boolean b(final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (this.b(array[i])) {
                return true;
            }
        }
        return false;
    }
    
    public boolean b(final String s) {
        return this.g.getDocumentBase().getHost().indexOf(s) != -1;
    }
    
    public boolean b(final long n, final long n2) {
        return new Date().getTime() > n + n2 * 86400000L;
    }
    
    public boolean _(final String s) {
        final String g = this.g(default.a);
        return g != null && g.equalsIgnoreCase(s);
    }
    
    public boolean m(final String s) {
        final String g = this.g(default.b);
        return g != null && g.equalsIgnoreCase(s);
    }
    
    public void _(final int n, final int n2) {
        this.b(default.C + n + default.D + n2);
    }
    
    public int _(final String s, final int n, final int n2, final int n3) {
        int int1 = n3;
        String s2;
        if (this.y != null) {
            s2 = this.y._(s);
        }
        else {
            s2 = this.g.getParameter(s);
        }
        if (s2 != null) {
            try {
                int1 = Integer.parseInt(s2);
                if (!this.b(int1, n, n2)) {
                    int1 = n3;
                    this.b(String.valueOf(s) + default.E + n + default.F + n2 + default.G);
                }
            }
            catch (NumberFormatException ex) {
                this.b(default.H + s + default.G);
            }
        }
        return int1;
    }
    
    public int _(final String s, final int n, final int n2) {
        int int1 = 0;
        String s2;
        if (this.y != null) {
            s2 = this.y._(s);
        }
        else {
            s2 = this.g.getParameter(s);
        }
        if (s2 != null) {
            try {
                int1 = Integer.parseInt(s2);
                if (!this.b(int1, n, n2)) {
                    this.n(String.valueOf(s) + default.E + n + default.F + n2);
                }
            }
            catch (NumberFormatException ex) {
                this.n(default.H + s);
            }
        }
        else {
            this.n(String.valueOf(s) + default.I);
        }
        return int1;
    }
    
    public int _(final String s) {
        return this._(s, 0, 0);
    }
    
    public int a(final String s, final int n) {
        return this._(s, 0, 0, n);
    }
    
    public boolean b(final int n, final int n2, final int n3) {
        return n2 == n3 || (n >= n2 && n <= n3);
    }
    
    public String g(final String s) {
        String s2;
        if (this.y != null) {
            s2 = this.y._(s);
        }
        else {
            s2 = this.g.getParameter(s);
        }
        if (s2 == null) {
            this.n(String.valueOf(s) + default.I);
        }
        return s2;
    }
    
    public String a(final String s, final String s2) {
        String s3;
        if (this.y != null) {
            s3 = this.y._(s);
        }
        else {
            s3 = this.g.getParameter(s);
        }
        if (s3 == null) {
            s3 = s2;
        }
        return s3;
    }
    
    public Color b(final String s, final Color color) {
        Color color2 = color;
        String s2;
        if (this.y != null) {
            s2 = this.y._(s);
        }
        else {
            s2 = this.g.getParameter(s);
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
                    this.b(default.H + s + default.G);
                }
            }
        }
        return color2;
    }
    
    public Color _(final String s) {
        Color color = null;
        String s2;
        if (this.y != null) {
            s2 = this.y._(s);
        }
        else {
            s2 = this.g.getParameter(s);
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
                    this.n(default.H + s);
                }
            }
        }
        this.n(String.valueOf(s) + default.I);
        return color;
    }
    
    public boolean b(final String s, final boolean b) {
        boolean b2 = b;
        String s2;
        if (this.y != null) {
            s2 = this.y._(s);
        }
        else {
            s2 = this.g.getParameter(s);
        }
        if (s2 != null) {
            final String lowerCase = s2.toLowerCase();
            b2 = (lowerCase.startsWith(default.J) || lowerCase.startsWith(default.K) || lowerCase.equals(default.L) || lowerCase.equals(default.M) || lowerCase.equals(default.N));
        }
        return b2;
    }
    
    public int b(final String s, final int n) {
        int n2 = n;
        String s2 = null;
        try {
            if (this.y != null) {
                s2 = this.y._(s);
            }
            else {
                s2 = this.g.getParameter(s);
            }
            if (s2 != null) {
                if (s2.equalsIgnoreCase(default.O)) {
                    n2 = 0;
                }
                else if (s2.equalsIgnoreCase(default.P)) {
                    n2 = 1;
                }
                else if (s2.equalsIgnoreCase(default.Q)) {
                    n2 = 2;
                }
                else {
                    if (!s2.equalsIgnoreCase(default.R)) {
                        throw new Exception();
                    }
                    n2 = 3;
                }
            }
        }
        catch (Exception ex) {
            this.b(default.S + s2 + default.G);
        }
        return n2;
    }
    
    public String b(final String s, final String s2) {
        String s3 = s2;
        String s4;
        if (this.y != null) {
            s4 = this.y._(s);
        }
        else {
            s4 = this.g.getParameter(s);
        }
        try {
            if (s4 != null) {
                if (s4.equalsIgnoreCase(default.T)) {
                    s3 = default.U;
                }
                else if (s4.equalsIgnoreCase(default.V)) {
                    s3 = default.W;
                }
                else if (s4.equalsIgnoreCase(default.X)) {
                    s3 = default.Y;
                }
                else {
                    if (!s4.equalsIgnoreCase(default.Z)) {
                        throw new Exception();
                    }
                    s3 = default._a;
                }
            }
        }
        catch (Exception ex) {
            this.b(default.aa + s4 + default.G);
        }
        return s3;
    }
    
    public void _(final Vector vector, final int n) {
        final MediaTracker mediaTracker = new MediaTracker(this.g);
        for (int i = 0; i < vector.size(); ++i) {
            mediaTracker.addImage(vector.elementAt(i), n);
        }
        try {
            mediaTracker.waitForID(n);
        }
        catch (InterruptedException ex) {
            this.n(default.ba);
        }
        if ((mediaTracker.statusID(n, false) & 0x6) != 0x0) {
            this.n(default.ca);
        }
    }
    
    public Image[] a(final String s, final int n) {
        final Vector vector = new Vector<Image>();
        int n2 = 1;
        String a;
        while ((a = this.a(String.valueOf(s) + n2++, null)) != null) {
            vector.addElement(this.g.getImage(this.g.getCodeBase(), a));
        }
        this._(vector, n);
        final Image[] array = new Image[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public AudioClip _(String a) {
        if (!a.endsWith(default.da)) {
            a = this.a(a, null);
        }
        if (a != null) {
            final AudioClip audioClip = this.g.getAudioClip(this.g.getCodeBase(), a);
            a(audioClip);
            b(audioClip);
            return audioClip;
        }
        return null;
    }
    
    public boolean d() {
        try {
            return System.getProperty(default.ea).startsWith(default.fa);
        }
        catch (SecurityException ex) {
            return true;
        }
    }
    
    public int a(final String s, final boolean b, final Graphics graphics) {
        final Dimension size = this.g.getSize();
        return _(s, b, new Rectangle(0, 0, size.width, size.height), graphics);
    }
    
    public void _(final Graphics graphics) {
        graphics.setColor(this.g.getBackground());
        graphics.fillRect(0, 0, this.g.getSize().width, this.g.getSize().height);
    }
    
    public Class b(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            this.n(default.ga + s);
            return null;
        }
    }
    
    public void b(final String s) {
        System.out.println(String.valueOf(this.g.getClass().getName()) + default.ha + s);
    }
    
    public boolean n(final String s) {
        return this.b(s, (Exception)null);
    }
    
    public boolean b(final String s, final Exception ex) {
        this.A = true;
        if (this.z != null) {
            this.g.showStatus(this.z.a());
        }
        this.b(default.ia + s);
        if (ex != null) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public void _(final boolean b) {
        this.B = b;
    }
    
    public boolean e() {
        return this.B;
    }
    
    public boolean f() {
        return this.A;
    }
    
    public static int _(final String s, final boolean b, final Rectangle rectangle, final Graphics graphics) {
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(graphics.getFont());
        if (b) {
            return rectangle.x + (rectangle.width - fontMetrics.stringWidth(s)) / 2;
        }
        return rectangle.y + rectangle.height % 2 + rectangle.height / 2 + (fontMetrics.getAscent() - fontMetrics.getDescent()) / 2;
    }
    
    public static int b(final String s, final boolean b, final Rectangle rectangle, final Font font) {
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
        if (b) {
            return rectangle.x + (rectangle.width - fontMetrics.stringWidth(s)) / 2;
        }
        return rectangle.y + rectangle.height % 2 + rectangle.height / 2 + (fontMetrics.getAscent() - fontMetrics.getDescent()) / 2;
    }
    
    private static String h(final String s) {
        if (s != null && !s.equals(default.ja)) {
            return default.ka + Calendar.getInstance().get(1) + default.la + s;
        }
        return default.ma;
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
    
    public static Color a(String substring) {
        if (substring.startsWith(default.na)) {
            substring = substring.substring(1);
        }
        if (substring.length() == 6) {
            return new Color(Integer.parseInt(substring, 16));
        }
        throw new IllegalArgumentException(String.valueOf(substring) + default.oa);
    }
    
    public static Color b(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        if (stringTokenizer.countTokens() == 3) {
            return new Color(Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()));
        }
        throw new IllegalArgumentException(String.valueOf(s) + default.oa);
    }
    
    public static Color b(final String s) {
        return b(s, default.pa);
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
    
    public static void _(final Rectangle rectangle, final Color color, final Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public static void a(final int n, final int n2, final int n3, final int n4, final Color color, final Graphics graphics) {
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
    
    public static void b(final Reader reader) {
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
        default.qb = a(default.qb);
        default.rb = a(default.rb);
        default.sb = a(default.sb);
        default.tb = a(default.tb);
        default._ = a(default._);
        default.a = a(default.a);
        default.b = a(default.b);
        default.C = a(default.C);
        default.D = a(default.D);
        default.E = a(default.E);
        default.F = a(default.F);
        default.G = a(default.G);
        default.H = a(default.H);
        default.I = a(default.I);
        default.J = a(default.J);
        default.K = a(default.K);
        default.L = a(default.L);
        default.M = a(default.M);
        default.N = a(default.N);
        default.O = a(default.O);
        default.P = a(default.P);
        default.Q = a(default.Q);
        default.R = a(default.R);
        default.S = a(default.S);
        default.T = a(default.T);
        default.U = a(default.U);
        default.V = a(default.V);
        default.W = a(default.W);
        default.X = a(default.X);
        default.Y = a(default.Y);
        default.Z = a(default.Z);
        default._a = a(default._a);
        default.aa = a(default.aa);
        default.ba = a(default.ba);
        default.ca = a(default.ca);
        default.da = a(default.da);
        default.ea = a(default.ea);
        default.fa = a(default.fa);
        default.ga = a(default.ga);
        default.ha = a(default.ha);
        default.ia = a(default.ia);
        default.ja = a(default.ja);
        default.ka = a(default.ka);
        default.la = a(default.la);
        default.ma = a(default.ma);
        default.na = a(default.na);
        default.oa = a(default.oa);
        default.pa = a(default.pa);
        default.qa = a(default.qa);
        default.ra = a(default.ra);
        default.sa = a(default.sa);
        default.ta = a(default.ta);
        default.ua = a(default.ua);
        default.va = a(default.va);
        default.wa = a(default.wa);
        default.xa = a(default.xa);
        v = h(default.qa);
        x = new String[] { default.ra, default.sa, default.ta, default.ua, default.va, default.wa, default.xa };
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFFBF96);
        }
        return new String(array);
    }
}
