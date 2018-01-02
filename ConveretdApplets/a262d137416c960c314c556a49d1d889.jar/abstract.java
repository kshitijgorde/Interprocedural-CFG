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

public class abstract
{
    public static final String b2;
    public static final long c2 = 86400000L;
    public static final String[] d2;
    public static boolean e2;
    private Applet f2;
    private const g2;
    private v h2;
    private boolean i2;
    private boolean j2;
    private static String z = "\ub23e\ub225\ub23c\ub23c";
    private static String A = "\ub233\ub23f\ub23e\ub236\ub239\ub237";
    private static String B = "\ub222\ub235\ub231\ub234\ub239\ub23e\ub237\ub270";
    private static String C = "\ub270\ub238\ub231\ub223\ub270\ub236\ub231\ub239\ub23c\ub235\ub234";
    private static String D = "\ub238\ub224\ub224\ub220";
    private static String E = "\ub233\ub23f\ub220\ub229\ub222\ub239\ub237\ub238\ub224";
    private static String F = "\ub23c\ub239\ub233\ub235\ub23e\ub223\ub235\ub235";
    private static String G = "\ub222\ub235\ub233\ub23f\ub23d\ub23d\ub235\ub23e\ub234\ub235\ub234\ub270\ub223\ub239\ub22a\ub235\ub26a\ub270\ub227\ub239\ub234\ub224\ub238\ub26d\ub272";
    private static String H = "\ub272\ub270\ub238\ub235\ub239\ub237\ub238\ub224\ub26d\ub272";
    private static String I = "\ub272";
    private static String J = "\ub270\ub226\ub231\ub23c\ub225\ub235\ub270\ub238\ub231\ub223\ub270\ub224\ub23f\ub270\ub232\ub235\ub270\ub232\ub235\ub224\ub227\ub235\ub235\ub23e\ub270";
    private static String K = "\ub270\ub231\ub23e\ub234\ub270";
    private static String L = "\ub26b\ub270\ub225\ub223\ub239\ub23e\ub237\ub270\ub234\ub235\ub236\ub231\ub225\ub23c\ub224";
    private static String M = "\ub239\ub23c\ub23c\ub235\ub237\ub231\ub23c\ub270\ub226\ub231\ub23c\ub225\ub235\ub270\ub236\ub23f\ub222\ub270";
    private static String N = "\ub270\ub220\ub231\ub222\ub231\ub23d\ub235\ub224\ub235\ub222\ub270\ub239\ub223\ub270\ub23d\ub239\ub223\ub223\ub239\ub23e\ub237";
    private static String O = "\ub229";
    private static String P = "\ub224";
    private static String Q = "\ub261";
    private static String R = "\ub23f\ub23e";
    private static String S = "\ub27b";
    private static String T = "\ub220\ub23c\ub231\ub239\ub23e";
    private static String U = "\ub232\ub23f\ub23c\ub234";
    private static String V = "\ub239\ub224\ub231\ub23c\ub239\ub233";
    private static String W = "\ub232\ub23f\ub23c\ub234\ub239\ub224\ub231\ub23c\ub239\ub233";
    private static String X = "\ub239\ub23c\ub23c\ub235\ub237\ub231\ub23c\ub270\ub226\ub231\ub23c\ub225\ub235\ub270\ub236\ub23f\ub222\ub270\ub236\ub23f\ub23e\ub224\ub223\ub224\ub229\ub23c\ub235\ub26a\ub270";
    private static String Y = "\ub224\ub239\ub23d\ub235\ub223\ub222\ub23f\ub23d\ub231\ub23e";
    private static String Z = "\ub204\ub239\ub23d\ub235\ub223\ub202\ub23f\ub23d\ub231\ub23e";
    private static String _a = "\ub231\ub222\ub239\ub231\ub23c";
    private static String aa = "\ub211\ub222\ub239\ub231\ub23c";
    private static String ba = "\ub233\ub23f\ub225\ub222\ub239\ub235\ub222";
    private static String ca = "\ub213\ub23f\ub225\ub222\ub239\ub235\ub222";
    private static String da = "\ub234\ub239\ub231\ub23c\ub23f\ub237";
    private static String ea = "\ub214\ub239\ub231\ub23c\ub23f\ub237";
    private static String fa = "\ub239\ub23c\ub23c\ub235\ub237\ub231\ub23c\ub270\ub226\ub231\ub23c\ub225\ub235\ub270\ub236\ub23f\ub222\ub270\ub236\ub23f\ub23e\ub224\ub236\ub231\ub233\ub235\ub26a\ub270";
    private static String ga = "\ub239\ub23d\ub231\ub237\ub235\ub27d\ub23c\ub23f\ub231\ub234\ub239\ub23e\ub237\ub270\ub238\ub231\ub223\ub270\ub232\ub235\ub235\ub23e\ub270\ub239\ub23e\ub224\ub235\ub222\ub222\ub225\ub220\ub224\ub235\ub234";
    private static String ha = "\ub23f\ub23e\ub235\ub270\ub23f\ub222\ub270\ub23d\ub23f\ub222\ub235\ub270\ub239\ub23d\ub231\ub237\ub235\ub223\ub270\ub23d\ub231\ub229\ub270\ub23e\ub23f\ub224\ub270\ub238\ub231\ub226\ub235\ub270\ub23c\ub23f\ub231\ub234\ub235\ub234\ub270\ub233\ub23f\ub222\ub222\ub235\ub233\ub224\ub23c\ub229";
    private static String ia = "\ub27e\ub231\ub225";
    private static String ja = "\ub23f\ub223\ub27e\ub23e\ub231\ub23d\ub235";
    private static String ka = "\ub207\ub239\ub23e";
    private static String la = "\ub233\ub23f\ub225\ub23c\ub234\ub270\ub23e\ub23f\ub224\ub270\ub23c\ub23f\ub231\ub234\ub270";
    private static String ma = "\ub26a\ub270";
    private static String na = "\ub236\ub231\ub224\ub231\ub23c\ub270\ub235\ub222\ub222\ub23f\ub222\ub26a\ub270";
    private static String oa = "";
    private static String pa = "\ub213\ub23f\ub220\ub229\ub222\ub239\ub237\ub238\ub224\ub270\ub278\ub233\ub279\ub270\ub270";
    private static String qa = "\ub27c\ub270";
    private static String ra = "\ub204\ub238\ub239\ub223\ub270\ub231\ub220\ub220\ub23c\ub235\ub224\ub270\ub239\ub223\ub270\ub233\ub23f\ub220\ub229\ub222\ub239\ub237\ub238\ub224\ub235\ub234";
    private static String sa = "\ub273";
    private static String ta = "\ub270\ub233\ub231\ub23e\ub277\ub224\ub270\ub232\ub235\ub270\ub220\ub231\ub222\ub223\ub235\ub234";
    private static String ua = "\ub27c";
    private static String va = "\ub21a\ub231\ub226\ub231\ub23c\ub23f\ub226\ub235\ub222\ub223\ub270\ub207\ub235\ub232\ub234\ub235\ub223\ub239\ub237\ub23e";
    private static String wa = "\ub23a\ub231\ub226\ub231\ub23c\ub23f\ub226\ub235\ub222\ub223\ub27e\ub233\ub23f\ub23d";
    private static String xa = "\ub236\ub227\ub235\ub23e\ub234\ub27e\ub233\ub23f\ub23d";
    private static String ya = "\ub234\ub231\ub239\ub23c\ub229\ub227\ub23f\ub222\ub234\ub223\ub235\ub231\ub222\ub233\ub238\ub27e\ub233\ub23f\ub23d";
    private static String za = "\ub234\ub225\ub233\ub23b\ub232\ub235\ub222\ub222\ub229\ub27e\ub233\ub23f\ub23d";
    
    public abstract(final Applet f2, final v h2) {
        if (f2 == null) {
            throw new IllegalArgumentException(abstract.z);
        }
        this.f2 = f2;
        this.h2 = h2;
        final String _ = this._(abstract.A, (String)null);
        if (_ != null) {
            this.g2 = new const();
            if (!this.g2.a(this.f2.getCodeBase(), _, false)) {
                this.b(abstract.B + _ + abstract.C);
                this.g2 = null;
            }
        }
    }
    
    public boolean _() {
        return this.f2.getCodeBase().getProtocol().startsWith(abstract.D);
    }
    
    public boolean a(final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (this._(array[i])) {
                return true;
            }
        }
        return false;
    }
    
    public boolean _(final String s) {
        return this.f2.getCodeBase().getHost().indexOf(s) != -1 && this.f2.getDocumentBase().getHost().indexOf(s) != -1;
    }
    
    public boolean b(final long n, final long n2) {
        return new Date().getTime() > n + n2 * 86400000L;
    }
    
    public boolean a(final String s) {
        final String d = this.d(abstract.E);
        return d != null && d.equalsIgnoreCase(s);
    }
    
    public boolean h(final String s) {
        final String d = this.d(abstract.F);
        return d != null && d.equalsIgnoreCase(s);
    }
    
    public void _(final int n, final int n2) {
        this.b(abstract.G + n + abstract.H + n2 + abstract.I);
    }
    
    public int _(final String s, final int n, final int n2, final int n3) {
        int int1 = n3;
        String s2;
        if (this.g2 != null) {
            s2 = this.g2.b(s);
        }
        else {
            s2 = this.f2.getParameter(s);
        }
        if (s2 != null) {
            try {
                int1 = Integer.parseInt(s2);
                if (!this._(int1, n, n2)) {
                    int1 = n3;
                    this.b(String.valueOf(s) + abstract.J + n + abstract.K + n2 + abstract.L);
                }
            }
            catch (NumberFormatException ex) {
                this.b(abstract.M + s + abstract.L);
            }
        }
        return int1;
    }
    
    public int b(final String s, final int n, final int n2) {
        int int1 = 0;
        String s2;
        if (this.g2 != null) {
            s2 = this.g2.b(s);
        }
        else {
            s2 = this.f2.getParameter(s);
        }
        if (s2 != null) {
            try {
                int1 = Integer.parseInt(s2);
                if (!this._(int1, n, n2)) {
                    this.i(String.valueOf(s) + abstract.J + n + abstract.K + n2);
                }
            }
            catch (NumberFormatException ex) {
                this.i(abstract.M + s);
            }
        }
        else {
            this.i(String.valueOf(s) + abstract.N);
        }
        return int1;
    }
    
    public int a(final String s) {
        return this.b(s, 0, 0);
    }
    
    public int b(final String s, final int n) {
        return this._(s, 0, 0, n);
    }
    
    public boolean _(final int n, final int n2, final int n3) {
        return n2 == n3 || (n >= n2 && n <= n3);
    }
    
    public String d(final String s) {
        String s2;
        if (this.g2 != null) {
            s2 = this.g2.b(s);
        }
        else {
            s2 = this.f2.getParameter(s);
        }
        if (s2 == null) {
            this.i(String.valueOf(s) + abstract.N);
        }
        return s2;
    }
    
    public String _(final String s, final String s2) {
        String s3;
        if (this.g2 != null) {
            s3 = this.g2.b(s);
        }
        else {
            s3 = this.f2.getParameter(s);
        }
        if (s3 == null) {
            s3 = s2;
        }
        return s3;
    }
    
    public Color b(final String s, final Color color) {
        Color color2 = color;
        String s2;
        if (this.g2 != null) {
            s2 = this.g2.b(s);
        }
        else {
            s2 = this.f2.getParameter(s);
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
                    this.b(abstract.M + s + abstract.L);
                }
            }
        }
        return color2;
    }
    
    public Color _(final String s) {
        Color color = null;
        String s2;
        if (this.g2 != null) {
            s2 = this.g2.b(s);
        }
        else {
            s2 = this.f2.getParameter(s);
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
                    this.i(abstract.M + s);
                }
            }
        }
        this.i(String.valueOf(s) + abstract.N);
        return color;
    }
    
    public boolean a(final String s, final boolean b) {
        boolean b2 = b;
        String s2;
        if (this.g2 != null) {
            s2 = this.g2.b(s);
        }
        else {
            s2 = this.f2.getParameter(s);
        }
        if (s2 != null) {
            final String lowerCase = s2.toLowerCase();
            b2 = (lowerCase.startsWith(abstract.O) || lowerCase.startsWith(abstract.P) || lowerCase.equals(abstract.Q) || lowerCase.equals(abstract.R) || lowerCase.equals(abstract.S));
        }
        return b2;
    }
    
    public int _(final String s, final int n) {
        int n2 = n;
        String s2 = null;
        try {
            if (this.g2 != null) {
                s2 = this.g2.b(s);
            }
            else {
                s2 = this.f2.getParameter(s);
            }
            if (s2 != null) {
                if (s2.equalsIgnoreCase(abstract.T)) {
                    n2 = 0;
                }
                else if (s2.equalsIgnoreCase(abstract.U)) {
                    n2 = 1;
                }
                else if (s2.equalsIgnoreCase(abstract.V)) {
                    n2 = 2;
                }
                else {
                    if (!s2.equalsIgnoreCase(abstract.W)) {
                        throw new Exception();
                    }
                    n2 = 3;
                }
            }
        }
        catch (Exception ex) {
            this.b(abstract.X + s2 + abstract.L);
        }
        return n2;
    }
    
    public String a(final String s, final String s2) {
        String s3 = s2;
        String s4;
        if (this.g2 != null) {
            s4 = this.g2.b(s);
        }
        else {
            s4 = this.f2.getParameter(s);
        }
        try {
            if (s4 != null) {
                if (s4.equalsIgnoreCase(abstract.Y)) {
                    s3 = abstract.Z;
                }
                else if (s4.equalsIgnoreCase(abstract._a)) {
                    s3 = abstract.aa;
                }
                else if (s4.equalsIgnoreCase(abstract.ba)) {
                    s3 = abstract.ca;
                }
                else {
                    if (!s4.equalsIgnoreCase(abstract.da)) {
                        throw new Exception();
                    }
                    s3 = abstract.ea;
                }
            }
        }
        catch (Exception ex) {
            this.b(abstract.fa + s4 + abstract.L);
        }
        return s3;
    }
    
    public String[] a(final String s, final int n) {
        final Vector vector = new Vector<String>();
        int n2 = n;
        String _;
        while ((_ = this._(String.valueOf(s) + n2++, (String)null)) != null) {
            vector.addElement(_);
        }
        final String[] array = new String[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public void _(final Vector vector, final int n) {
        final MediaTracker mediaTracker = new MediaTracker(this.f2);
        for (int i = 0; i < vector.size(); ++i) {
            mediaTracker.addImage(vector.elementAt(i), n);
        }
        try {
            mediaTracker.waitForID(n);
        }
        catch (InterruptedException ex) {
            this.i(abstract.ga);
        }
        if ((mediaTracker.statusID(n, false) & 0x6) != 0x0) {
            this.i(abstract.ha);
        }
    }
    
    public Image[] b(final String s, final int n) {
        final Vector vector = new Vector<Image>();
        int n2 = 1;
        String _;
        while ((_ = this._(String.valueOf(s) + n2++, (String)null)) != null) {
            vector.addElement(this.f2.getImage(this.f2.getCodeBase(), _));
        }
        this._(vector, n);
        final Image[] array = new Image[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public AudioClip a(String _) {
        if (!_.endsWith(abstract.ia)) {
            _ = this._(_, (String)null);
        }
        if (_ != null) {
            final AudioClip audioClip = this.f2.getAudioClip(this.f2.getCodeBase(), _);
            a(audioClip);
            b(audioClip);
            return audioClip;
        }
        return null;
    }
    
    public boolean n() {
        try {
            return System.getProperty(abstract.ja).startsWith(abstract.ka);
        }
        catch (SecurityException ex) {
            return true;
        }
    }
    
    public int b(final String s, final boolean b, final Graphics graphics) {
        final Dimension size = this.f2.getSize();
        return b(s, b, new Rectangle(0, 0, size.width, size.height), graphics);
    }
    
    public void a(final Graphics graphics) {
        graphics.setColor(this.f2.getBackground());
        graphics.fillRect(0, 0, this.f2.getSize().width, this.f2.getSize().height);
    }
    
    public Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            this.i(abstract.la + s);
            return null;
        }
    }
    
    public void b(final String s) {
        System.out.println(String.valueOf(this.f2.getClass().getName()) + abstract.ma + s);
    }
    
    public boolean i(final String s) {
        return this._(s, (Exception)null);
    }
    
    public boolean _(final String s, final Exception ex) {
        this.i2 = true;
        if (this.h2 != null) {
            this.f2.showStatus(this.h2.b());
        }
        this.b(abstract.na + s);
        if (ex != null) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public void a(final boolean j2) {
        this.j2 = j2;
    }
    
    public boolean c() {
        return this.j2;
    }
    
    public boolean d() {
        return this.i2;
    }
    
    public static int b(final String s, final boolean b, final Rectangle rectangle, final Graphics graphics) {
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
    
    private static String e(final String s) {
        if (s != null && !s.equals(abstract.oa)) {
            return abstract.pa + Calendar.getInstance().get(1) + abstract.qa + s;
        }
        return abstract.ra;
    }
    
    public static boolean a(final long n) {
        return n % 4L == 0L && (n % 100L != 0L || n % 400L == 0L);
    }
    
    public static void a(final AudioClip audioClip) {
        if (audioClip != null && abstract.e2) {
            audioClip.play();
        }
    }
    
    public static void b(final AudioClip audioClip) {
        if (audioClip != null) {
            audioClip.stop();
        }
    }
    
    public static void _(final AudioClip audioClip) {
        if (audioClip != null && abstract.e2) {
            audioClip.loop();
        }
    }
    
    public static Color a(String substring) {
        if (substring.startsWith(abstract.sa)) {
            substring = substring.substring(1);
        }
        if (substring.length() == 6) {
            return new Color(Integer.parseInt(substring, 16));
        }
        throw new IllegalArgumentException(String.valueOf(substring) + abstract.ta);
    }
    
    public static Color a(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        if (stringTokenizer.countTokens() == 3) {
            return new Color(Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()));
        }
        throw new IllegalArgumentException(String.valueOf(s) + abstract.ta);
    }
    
    public static Color b(final String s) {
        return a(s, abstract.ua);
    }
    
    public static void b(final int n, final int n2, final int n3, final int n4, final Graphics graphics) {
        graphics.drawLine(n, n2, n + n3, n2);
        graphics.drawLine(n + n3, n2, n + n3, n2 + n4);
        graphics.drawLine(n, n2, n, n2 + n4);
        graphics.drawLine(n, n2 + n4, n + n3, n2 + n4);
    }
    
    public static void a(final Rectangle rectangle, final Graphics graphics) {
        b(rectangle.x, rectangle.y, rectangle.width, rectangle.height, graphics);
    }
    
    public static void _(final Rectangle rectangle, final Color color, final Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public static void a(final int n, final int n2, final int n3, final int n4, final Color color, final Graphics graphics) {
        _(new Rectangle(n, n2, n3, n4), color, graphics);
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
    
    public static void _(final OutputStream outputStream) {
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
    
    public static void b(final Writer writer) {
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
        abstract.z = _(abstract.z);
        abstract.A = _(abstract.A);
        abstract.B = _(abstract.B);
        abstract.C = _(abstract.C);
        abstract.D = _(abstract.D);
        abstract.E = _(abstract.E);
        abstract.F = _(abstract.F);
        abstract.G = _(abstract.G);
        abstract.H = _(abstract.H);
        abstract.I = _(abstract.I);
        abstract.J = _(abstract.J);
        abstract.K = _(abstract.K);
        abstract.L = _(abstract.L);
        abstract.M = _(abstract.M);
        abstract.N = _(abstract.N);
        abstract.O = _(abstract.O);
        abstract.P = _(abstract.P);
        abstract.Q = _(abstract.Q);
        abstract.R = _(abstract.R);
        abstract.S = _(abstract.S);
        abstract.T = _(abstract.T);
        abstract.U = _(abstract.U);
        abstract.V = _(abstract.V);
        abstract.W = _(abstract.W);
        abstract.X = _(abstract.X);
        abstract.Y = _(abstract.Y);
        abstract.Z = _(abstract.Z);
        abstract._a = _(abstract._a);
        abstract.aa = _(abstract.aa);
        abstract.ba = _(abstract.ba);
        abstract.ca = _(abstract.ca);
        abstract.da = _(abstract.da);
        abstract.ea = _(abstract.ea);
        abstract.fa = _(abstract.fa);
        abstract.ga = _(abstract.ga);
        abstract.ha = _(abstract.ha);
        abstract.ia = _(abstract.ia);
        abstract.ja = _(abstract.ja);
        abstract.ka = _(abstract.ka);
        abstract.la = _(abstract.la);
        abstract.ma = _(abstract.ma);
        abstract.na = _(abstract.na);
        abstract.oa = _(abstract.oa);
        abstract.pa = _(abstract.pa);
        abstract.qa = _(abstract.qa);
        abstract.ra = _(abstract.ra);
        abstract.sa = _(abstract.sa);
        abstract.ta = _(abstract.ta);
        abstract.ua = _(abstract.ua);
        abstract.va = _(abstract.va);
        abstract.wa = _(abstract.wa);
        abstract.xa = _(abstract.xa);
        abstract.ya = _(abstract.ya);
        abstract.za = _(abstract.za);
        b2 = e(abstract.va);
        d2 = new String[] { abstract.wa, abstract.xa, abstract.ya, abstract.za };
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFEB250);
        }
        return new String(array);
    }
}
