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

public class j
{
    public static final String KJc;
    public static final long LJc = 86400000L;
    public static final String[] MJc;
    public static boolean NJc;
    private Applet OJc;
    private o PJc;
    private i QJc;
    private boolean RJc;
    private boolean SJc;
    private static String ta = "\u4eef\u4ef4\u4eed\u4eed";
    private static String ua = "\u4ee2\u4eee\u4eef\u4ee7\u4ee8\u4ee6";
    private static String va = "\u4ef3\u4ee4\u4ee0\u4ee5\u4ee8\u4eef\u4ee6\u4ea1";
    private static String wa = "\u4ea1\u4ee9\u4ee0\u4ef2\u4ea1\u4ee7\u4ee0\u4ee8\u4eed\u4ee4\u4ee5";
    private static String xa = "\u4ee9\u4ef5\u4ef5\u4ef1";
    private static String ya = "\u4ee2\u4eee\u4ef1\u4ef8\u4ef3\u4ee8\u4ee6\u4ee9\u4ef5";
    private static String za = "\u4eed\u4ee8\u4ee2\u4ee4\u4eef\u4ef2\u4ee4\u4ee4";
    private static String Aa = "\u4ef3\u4ee4\u4ee2\u4eee\u4eec\u4eec\u4ee4\u4eef\u4ee5\u4ee4\u4ee5\u4ea1\u4ef2\u4ee8\u4efb\u4ee4\u4ebb\u4ea1\u4ef6\u4ee8\u4ee5\u4ef5\u4ee9\u4ebc\u4ea3";
    private static String Ba = "\u4ea3\u4ea1\u4ee9\u4ee4\u4ee8\u4ee6\u4ee9\u4ef5\u4ebc\u4ea3";
    private static String Ca = "\u4ea3";
    private static String Da = "\u4ea1\u4ef7\u4ee0\u4eed\u4ef4\u4ee4\u4ea1\u4ee9\u4ee0\u4ef2\u4ea1\u4ef5\u4eee\u4ea1\u4ee3\u4ee4\u4ea1\u4ee3\u4ee4\u4ef5\u4ef6\u4ee4\u4ee4\u4eef\u4ea1";
    private static String Ea = "\u4ea1\u4ee0\u4eef\u4ee5\u4ea1";
    private static String Fa = "\u4eba\u4ea1\u4ef4\u4ef2\u4ee8\u4eef\u4ee6\u4ea1\u4ee5\u4ee4\u4ee7\u4ee0\u4ef4\u4eed\u4ef5";
    private static String Ga = "\u4ee8\u4eed\u4eed\u4ee4\u4ee6\u4ee0\u4eed\u4ea1\u4ef7\u4ee0\u4eed\u4ef4\u4ee4\u4ea1\u4ee7\u4eee\u4ef3\u4ea1";
    private static String Ha = "\u4ea1\u4ef1\u4ee0\u4ef3\u4ee0\u4eec\u4ee4\u4ef5\u4ee4\u4ef3\u4ea1\u4ee8\u4ef2\u4ea1\u4eec\u4ee8\u4ef2\u4ef2\u4ee8\u4eef\u4ee6";
    private static String Ia = "\u4ef8";
    private static String Ja = "\u4ef5";
    private static String Ka = "\u4eb0";
    private static String La = "\u4eee\u4eef";
    private static String Ma = "\u4eaa";
    private static String Na = "\u4ef1\u4eed\u4ee0\u4ee8\u4eef";
    private static String Oa = "\u4ee3\u4eee\u4eed\u4ee5";
    private static String Pa = "\u4ee8\u4ef5\u4ee0\u4eed\u4ee8\u4ee2";
    private static String Qa = "\u4ee3\u4eee\u4eed\u4ee5\u4ee8\u4ef5\u4ee0\u4eed\u4ee8\u4ee2";
    private static String Ra = "\u4ee8\u4eed\u4eed\u4ee4\u4ee6\u4ee0\u4eed\u4ea1\u4ef7\u4ee0\u4eed\u4ef4\u4ee4\u4ea1\u4ee7\u4eee\u4ef3\u4ea1\u4ee7\u4eee\u4eef\u4ef5\u4ef2\u4ef5\u4ef8\u4eed\u4ee4\u4ebb\u4ea1";
    private static String Sa = "\u4ef5\u4ee8\u4eec\u4ee4\u4ef2\u4ef3\u4eee\u4eec\u4ee0\u4eef";
    private static String Ta = "\u4ed5\u4ee8\u4eec\u4ee4\u4ef2\u4ed3\u4eee\u4eec\u4ee0\u4eef";
    private static String Ua = "\u4ee0\u4ef3\u4ee8\u4ee0\u4eed";
    private static String Va = "\u4ec0\u4ef3\u4ee8\u4ee0\u4eed";
    private static String Wa = "\u4ee2\u4eee\u4ef4\u4ef3\u4ee8\u4ee4\u4ef3";
    private static String Xa = "\u4ec2\u4eee\u4ef4\u4ef3\u4ee8\u4ee4\u4ef3";
    private static String Ya = "\u4ee5\u4ee8\u4ee0\u4eed\u4eee\u4ee6";
    private static String Za = "\u4ec5\u4ee8\u4ee0\u4eed\u4eee\u4ee6";
    private static String _b = "\u4ee8\u4eed\u4eed\u4ee4\u4ee6\u4ee0\u4eed\u4ea1\u4ef7\u4ee0\u4eed\u4ef4\u4ee4\u4ea1\u4ee7\u4eee\u4ef3\u4ea1\u4ee7\u4eee\u4eef\u4ef5\u4ee7\u4ee0\u4ee2\u4ee4\u4ebb\u4ea1";
    private static String ab = "\u4ee8\u4eec\u4ee0\u4ee6\u4ee4\u4eac\u4eed\u4eee\u4ee0\u4ee5\u4ee8\u4eef\u4ee6\u4ea1\u4ee9\u4ee0\u4ef2\u4ea1\u4ee3\u4ee4\u4ee4\u4eef\u4ea1\u4ee8\u4eef\u4ef5\u4ee4\u4ef3\u4ef3\u4ef4\u4ef1\u4ef5\u4ee4\u4ee5";
    private static String bb = "\u4eee\u4eef\u4ee4\u4ea1\u4eee\u4ef3\u4ea1\u4eec\u4eee\u4ef3\u4ee4\u4ea1\u4ee8\u4eec\u4ee0\u4ee6\u4ee4\u4ef2\u4ea1\u4eec\u4ee0\u4ef8\u4ea1\u4eef\u4eee\u4ef5\u4ea1\u4ee9\u4ee0\u4ef7\u4ee4\u4ea1\u4eed\u4eee\u4ee0\u4ee5\u4ee4\u4ee5\u4ea1\u4ee2\u4eee\u4ef3\u4ef3\u4ee4\u4ee2\u4ef5\u4eed\u4ef8";
    private static String cb = "\u4eaf\u4ee0\u4ef4";
    private static String db = "\u4eee\u4ef2\u4eaf\u4eef\u4ee0\u4eec\u4ee4";
    private static String eb = "\u4ed6\u4ee8\u4eef";
    private static String fb = "\u4ee2\u4eee\u4ef4\u4eed\u4ee5\u4ea1\u4eef\u4eee\u4ef5\u4ea1\u4eed\u4eee\u4ee0\u4ee5\u4ea1";
    private static String gb = "\u4ebb\u4ea1";
    private static String hb = "\u4ee7\u4ee0\u4ef5\u4ee0\u4eed\u4ea1\u4ee4\u4ef3\u4ef3\u4eee\u4ef3\u4ebb\u4ea1";
    private static String ib = "";
    private static String jb = "\u4ec2\u4eee\u4ef1\u4ef8\u4ef3\u4ee8\u4ee6\u4ee9\u4ef5\u4ea1\u4ea9\u4ee2\u4ea8\u4ea1\u4ea1";
    private static String kb = "\u4ead\u4ea1";
    private static String lb = "\u4ed5\u4ee9\u4ee8\u4ef2\u4ea1\u4ee0\u4ef1\u4ef1\u4eed\u4ee4\u4ef5\u4ea1\u4ee8\u4ef2\u4ea1\u4ee2\u4eee\u4ef1\u4ef8\u4ef3\u4ee8\u4ee6\u4ee9\u4ef5\u4ee4\u4ee5";
    private static String mb = "\u4ea2";
    private static String nb = "\u4ea1\u4ee2\u4ee0\u4eef\u4ea6\u4ef5\u4ea1\u4ee3\u4ee4\u4ea1\u4ef1\u4ee0\u4ef3\u4ef2\u4ee4\u4ee5";
    private static String ob = "\u4ead";
    private static String pb = "\u4ecb\u4ee0\u4ef7\u4ee0\u4eed\u4eee\u4ef7\u4ee4\u4ef3\u4ef2\u4ea1\u4ed6\u4ee4\u4ee3\u4ee5\u4ee4\u4ef2\u4ee8\u4ee6\u4eef";
    private static String qb = "\u4eeb\u4ee0\u4ef7\u4ee0\u4eed\u4eee\u4ef7\u4ee4\u4ef3\u4ef2\u4eaf\u4ee2\u4eee\u4eec";
    private static String rb = "\u4ee7\u4ef6\u4ee4\u4eef\u4ee5\u4eaf\u4ee2\u4eee\u4eec";
    private static String sb = "\u4ee5\u4ee0\u4ee8\u4eed\u4ef8\u4ef6\u4eee\u4ef3\u4ee5\u4ef2\u4ee4\u4ee0\u4ef3\u4ee2\u4ee9\u4eaf\u4ee2\u4eee\u4eec";
    private static String tb = "\u4ee5\u4ef4\u4ee2\u4eea\u4ee3\u4ee4\u4ef3\u4ef3\u4ef8\u4eaf\u4ee2\u4eee\u4eec";
    
    public j(final Applet oJc, final i qJc) {
        if (oJc == null) {
            throw new IllegalArgumentException(j.ta);
        }
        this.OJc = oJc;
        this.QJc = qJc;
        final String _ = this._(j.ua, (String)null);
        if (_ != null) {
            this.PJc = new o();
            if (!this.PJc.b(this.OJc.getCodeBase(), _, false)) {
                this.a(j.va + _ + j.wa);
                this.PJc = null;
            }
        }
    }
    
    public boolean _() {
        return this.OJc.getCodeBase().getProtocol().startsWith(j.xa);
    }
    
    public boolean b(final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (this._(array[i])) {
                return true;
            }
        }
        return false;
    }
    
    public boolean _(final String s) {
        return this.OJc.getCodeBase().getHost().indexOf(s) != -1 && this.OJc.getDocumentBase().getHost().indexOf(s) != -1;
    }
    
    public boolean a(final long n, final long n2) {
        return new Date().getTime() > n + n2 * 86400000L;
    }
    
    public boolean a(final String s) {
        final String e = this.e(j.ya);
        return e != null && e.equalsIgnoreCase(s);
    }
    
    public boolean k(final String s) {
        final String e = this.e(j.za);
        return e != null && e.equalsIgnoreCase(s);
    }
    
    public void a(final int n, final int n2) {
        this.a(j.Aa + n + j.Ba + n2 + j.Ca);
    }
    
    public int _(final String s, final int n, final int n2, final int n3) {
        int int1 = n3;
        String s2;
        if (this.PJc != null) {
            s2 = this.PJc._(s);
        }
        else {
            s2 = this.OJc.getParameter(s);
        }
        if (s2 != null) {
            try {
                int1 = Integer.parseInt(s2);
                if (!this.a(int1, n, n2)) {
                    int1 = n3;
                    this.a(String.valueOf(s) + j.Da + n + j.Ea + n2 + j.Fa);
                }
            }
            catch (NumberFormatException ex) {
                this.a(j.Ga + s + j.Fa);
            }
        }
        return int1;
    }
    
    public int a(final String s, final int n, final int n2) {
        int int1 = 0;
        String s2;
        if (this.PJc != null) {
            s2 = this.PJc._(s);
        }
        else {
            s2 = this.OJc.getParameter(s);
        }
        if (s2 != null) {
            try {
                int1 = Integer.parseInt(s2);
                if (!this.a(int1, n, n2)) {
                    this.l(String.valueOf(s) + j.Da + n + j.Ea + n2);
                }
            }
            catch (NumberFormatException ex) {
                this.l(j.Ga + s);
            }
        }
        else {
            this.l(String.valueOf(s) + j.Ha);
        }
        return int1;
    }
    
    public int a(final String s) {
        return this.a(s, 0, 0);
    }
    
    public int _(final String s, final int n) {
        return this._(s, 0, 0, n);
    }
    
    public boolean a(final int n, final int n2, final int n3) {
        return n2 == n3 || (n >= n2 && n <= n3);
    }
    
    public String e(final String s) {
        String s2;
        if (this.PJc != null) {
            s2 = this.PJc._(s);
        }
        else {
            s2 = this.OJc.getParameter(s);
        }
        if (s2 == null) {
            this.l(String.valueOf(s) + j.Ha);
        }
        return s2;
    }
    
    public String _(final String s, final String s2) {
        String s3;
        if (this.PJc != null) {
            s3 = this.PJc._(s);
        }
        else {
            s3 = this.OJc.getParameter(s);
        }
        if (s3 == null) {
            s3 = s2;
        }
        return s3;
    }
    
    public Color _(final String s, final Color color) {
        Color color2 = color;
        String s2;
        if (this.PJc != null) {
            s2 = this.PJc._(s);
        }
        else {
            s2 = this.OJc.getParameter(s);
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
                    this.a(ex.getMessage());
                    this.a(j.Ga + s + j.Fa);
                }
            }
        }
        return color2;
    }
    
    public Color _(final String s) {
        Color color = null;
        String s2;
        if (this.PJc != null) {
            s2 = this.PJc._(s);
        }
        else {
            s2 = this.OJc.getParameter(s);
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
                    this.a(ex.getMessage());
                    this.l(j.Ga + s);
                }
            }
        }
        this.l(String.valueOf(s) + j.Ha);
        return color;
    }
    
    public boolean a(final String s, final boolean b) {
        boolean b2 = b;
        String s2;
        if (this.PJc != null) {
            s2 = this.PJc._(s);
        }
        else {
            s2 = this.OJc.getParameter(s);
        }
        if (s2 != null) {
            final String lowerCase = s2.toLowerCase();
            b2 = (lowerCase.startsWith(j.Ia) || lowerCase.startsWith(j.Ja) || lowerCase.equals(j.Ka) || lowerCase.equals(j.La) || lowerCase.equals(j.Ma));
        }
        return b2;
    }
    
    public int a(final String s, final int n) {
        int n2 = n;
        String s2 = null;
        try {
            if (this.PJc != null) {
                s2 = this.PJc._(s);
            }
            else {
                s2 = this.OJc.getParameter(s);
            }
            if (s2 != null) {
                if (s2.equalsIgnoreCase(j.Na)) {
                    n2 = 0;
                }
                else if (s2.equalsIgnoreCase(j.Oa)) {
                    n2 = 1;
                }
                else if (s2.equalsIgnoreCase(j.Pa)) {
                    n2 = 2;
                }
                else {
                    if (!s2.equalsIgnoreCase(j.Qa)) {
                        throw new Exception();
                    }
                    n2 = 3;
                }
            }
        }
        catch (Exception ex) {
            this.a(j.Ra + s2 + j.Fa);
        }
        return n2;
    }
    
    public String a(final String s, final String s2) {
        String s3 = s2;
        String s4;
        if (this.PJc != null) {
            s4 = this.PJc._(s);
        }
        else {
            s4 = this.OJc.getParameter(s);
        }
        try {
            if (s4 != null) {
                if (s4.equalsIgnoreCase(j.Sa)) {
                    s3 = j.Ta;
                }
                else if (s4.equalsIgnoreCase(j.Ua)) {
                    s3 = j.Va;
                }
                else if (s4.equalsIgnoreCase(j.Wa)) {
                    s3 = j.Xa;
                }
                else {
                    if (!s4.equalsIgnoreCase(j.Ya)) {
                        throw new Exception();
                    }
                    s3 = j.Za;
                }
            }
        }
        catch (Exception ex) {
            this.a(j._b + s4 + j.Fa);
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
        final MediaTracker mediaTracker = new MediaTracker(this.OJc);
        for (int i = 0; i < vector.size(); ++i) {
            mediaTracker.addImage(vector.elementAt(i), n);
        }
        try {
            mediaTracker.waitForID(n);
        }
        catch (InterruptedException ex) {
            this.l(j.ab);
        }
        if ((mediaTracker.statusID(n, false) & 0x6) != 0x0) {
            this.l(j.bb);
        }
    }
    
    public Image[] a(final String s, final int n) {
        final Vector vector = new Vector<Image>();
        int n2 = 1;
        String _;
        while ((_ = this._(String.valueOf(s) + n2++, (String)null)) != null) {
            vector.addElement(this.OJc.getImage(this.OJc.getCodeBase(), _));
        }
        this._(vector, n);
        final Image[] array = new Image[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public AudioClip b(String _) {
        if (!_.endsWith(j.cb)) {
            _ = this._(_, (String)null);
        }
        if (_ != null) {
            final AudioClip audioClip = this.OJc.getAudioClip(this.OJc.getCodeBase(), _);
            _(audioClip);
            a(audioClip);
            return audioClip;
        }
        return null;
    }
    
    public boolean m() {
        try {
            return System.getProperty(j.db).startsWith(j.eb);
        }
        catch (SecurityException ex) {
            return true;
        }
    }
    
    public int b(final String s, final boolean b, final Graphics graphics) {
        final Dimension size = this.OJc.getSize();
        return _(s, b, new Rectangle(0, 0, size.width, size.height), graphics);
    }
    
    public void b(final Graphics graphics) {
        graphics.setColor(this.OJc.getBackground());
        graphics.fillRect(0, 0, this.OJc.getSize().width, this.OJc.getSize().height);
    }
    
    public Class _(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            this.l(j.fb + s);
            return null;
        }
    }
    
    public void a(final String s) {
        System.out.println(String.valueOf(this.OJc.getClass().getName()) + j.gb + s);
    }
    
    public boolean l(final String s) {
        return this._(s, (Exception)null);
    }
    
    public boolean _(final String s, final Exception ex) {
        this.RJc = true;
        if (this.QJc != null) {
            this.OJc.showStatus(this.QJc.a());
        }
        this.a(j.hb + s);
        if (ex != null) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public void _(final boolean sJc) {
        this.SJc = sJc;
    }
    
    public boolean n() {
        return this.SJc;
    }
    
    public boolean c() {
        return this.RJc;
    }
    
    public static int _(final String s, final boolean b, final Rectangle rectangle, final Graphics graphics) {
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(graphics.getFont());
        if (b) {
            return rectangle.x + (rectangle.width - fontMetrics.stringWidth(s)) / 2;
        }
        return rectangle.y + rectangle.height % 2 + rectangle.height / 2 + (fontMetrics.getAscent() - fontMetrics.getDescent()) / 2;
    }
    
    public static int _(final String s, final boolean b, final Rectangle rectangle, final Font font) {
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
        if (b) {
            return rectangle.x + (rectangle.width - fontMetrics.stringWidth(s)) / 2;
        }
        return rectangle.y + rectangle.height % 2 + rectangle.height / 2 + (fontMetrics.getAscent() - fontMetrics.getDescent()) / 2;
    }
    
    private static String f(final String s) {
        if (s != null && !s.equals(j.ib)) {
            return j.jb + Calendar.getInstance().get(1) + j.kb + s;
        }
        return j.lb;
    }
    
    public static boolean b(final long n) {
        return n % 4L == 0L && (n % 100L != 0L || n % 400L == 0L);
    }
    
    public static void _(final AudioClip audioClip) {
        if (audioClip != null && j.NJc) {
            audioClip.play();
        }
    }
    
    public static void a(final AudioClip audioClip) {
        if (audioClip != null) {
            audioClip.stop();
        }
    }
    
    public static void b(final AudioClip audioClip) {
        if (audioClip != null && j.NJc) {
            audioClip.loop();
        }
    }
    
    public static Color a(String substring) {
        if (substring.startsWith(j.mb)) {
            substring = substring.substring(1);
        }
        if (substring.length() == 6) {
            return new Color(Integer.parseInt(substring, 16));
        }
        throw new IllegalArgumentException(String.valueOf(substring) + j.nb);
    }
    
    public static Color b(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        if (stringTokenizer.countTokens() == 3) {
            return new Color(Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()));
        }
        throw new IllegalArgumentException(String.valueOf(s) + j.nb);
    }
    
    public static Color b(final String s) {
        return b(s, j.ob);
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
    
    public static void _(final int n, final int n2, final int n3, final int n4, final Color color, final Graphics graphics) {
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
        j.ta = a(j.ta);
        j.ua = a(j.ua);
        j.va = a(j.va);
        j.wa = a(j.wa);
        j.xa = a(j.xa);
        j.ya = a(j.ya);
        j.za = a(j.za);
        j.Aa = a(j.Aa);
        j.Ba = a(j.Ba);
        j.Ca = a(j.Ca);
        j.Da = a(j.Da);
        j.Ea = a(j.Ea);
        j.Fa = a(j.Fa);
        j.Ga = a(j.Ga);
        j.Ha = a(j.Ha);
        j.Ia = a(j.Ia);
        j.Ja = a(j.Ja);
        j.Ka = a(j.Ka);
        j.La = a(j.La);
        j.Ma = a(j.Ma);
        j.Na = a(j.Na);
        j.Oa = a(j.Oa);
        j.Pa = a(j.Pa);
        j.Qa = a(j.Qa);
        j.Ra = a(j.Ra);
        j.Sa = a(j.Sa);
        j.Ta = a(j.Ta);
        j.Ua = a(j.Ua);
        j.Va = a(j.Va);
        j.Wa = a(j.Wa);
        j.Xa = a(j.Xa);
        j.Ya = a(j.Ya);
        j.Za = a(j.Za);
        j._b = a(j._b);
        j.ab = a(j.ab);
        j.bb = a(j.bb);
        j.cb = a(j.cb);
        j.db = a(j.db);
        j.eb = a(j.eb);
        j.fb = a(j.fb);
        j.gb = a(j.gb);
        j.hb = a(j.hb);
        j.ib = a(j.ib);
        j.jb = a(j.jb);
        j.kb = a(j.kb);
        j.lb = a(j.lb);
        j.mb = a(j.mb);
        j.nb = a(j.nb);
        j.ob = a(j.ob);
        j.pb = a(j.pb);
        j.qb = a(j.qb);
        j.rb = a(j.rb);
        j.sb = a(j.sb);
        j.tb = a(j.tb);
        KJc = f(j.pb);
        MJc = new String[] { j.qb, j.rb, j.sb, j.tb };
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0x14E81);
        }
        return new String(array);
    }
}
