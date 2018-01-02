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

public class p
{
    public static final String ua;
    public static final long va = 86400000L;
    public static final String[] wa;
    private Applet xa;
    private s ya;
    private o za;
    private boolean Aa;
    private boolean Ba;
    private static String w = "\u4169\u4172\u416b\u416b";
    private static String x = "\u4164\u4168\u4169\u4161\u416e\u4160";
    private static String y = "\u4175\u4162\u4166\u4163\u416e\u4169\u4160\u4127";
    private static String z = "\u4127\u416f\u4166\u4174\u4127\u4161\u4166\u416e\u416b\u4162\u4163";
    private static String A = "\u416f\u4173\u4173\u4177";
    private static String B = "\u4164\u4168\u4177\u417e\u4175\u416e\u4160\u416f\u4173";
    private static String C = "\u416b\u416e\u4164\u4162\u4169\u4174\u4162\u4162";
    private static String T = "\u4175\u4162\u4164\u4168\u416a\u416a\u4162\u4169\u4163\u4162\u4163\u4127\u4174\u416e\u417d\u4162\u413d\u4127\u4170\u416e\u4163\u4173\u416f\u413a\u4125";
    private static String U = "\u4125\u4127\u416f\u4162\u416e\u4160\u416f\u4173\u413a\u4125";
    private static String V = "\u4125";
    private static String W = "\u4127\u4171\u4166\u416b\u4172\u4162\u4127\u416f\u4166\u4174\u4127\u4173\u4168\u4127\u4165\u4162\u4127\u4165\u4162\u4173\u4170\u4162\u4162\u4169\u4127";
    private static String X = "\u4127\u4166\u4169\u4163\u4127";
    private static String Y = "\u413c\u4127\u4172\u4174\u416e\u4169\u4160\u4127\u4163\u4162\u4161\u4166\u4172\u416b\u4173";
    private static String Z = "\u416e\u416b\u416b\u4162\u4160\u4166\u416b\u4127\u4171\u4166\u416b\u4172\u4162\u4127\u4161\u4168\u4175\u4127";
    private static String _a = "\u4127\u4177\u4166\u4175\u4166\u416a\u4162\u4173\u4162\u4175\u4127\u416e\u4174\u4127\u416a\u416e\u4174\u4174\u416e\u4169\u4160";
    private static String aa = "\u417e";
    private static String ba = "\u4173";
    private static String ca = "\u4136";
    private static String da = "\u4168\u4169";
    private static String ea = "\u412c";
    private static String fa = "\u4177\u416b\u4166\u416e\u4169";
    private static String ga = "\u4165\u4168\u416b\u4163";
    private static String ha = "\u416e\u4173\u4166\u416b\u416e\u4164";
    private static String ia = "\u4165\u4168\u416b\u4163\u416e\u4173\u4166\u416b\u416e\u4164";
    private static String ja = "\u416e\u416b\u416b\u4162\u4160\u4166\u416b\u4127\u4171\u4166\u416b\u4172\u4162\u4127\u4161\u4168\u4175\u4127\u4161\u4168\u4169\u4173\u4174\u4173\u417e\u416b\u4162\u413d\u4127";
    private static String ka = "\u4173\u416e\u416a\u4162\u4174\u4175\u4168\u416a\u4166\u4169";
    private static String la = "\u4153\u416e\u416a\u4162\u4174\u4155\u4168\u416a\u4166\u4169";
    private static String ma = "\u4166\u4175\u416e\u4166\u416b";
    private static String na = "\u4146\u4175\u416e\u4166\u416b";
    private static String oa = "\u4164\u4168\u4172\u4175\u416e\u4162\u4175";
    private static String pa = "\u4144\u4168\u4172\u4175\u416e\u4162\u4175";
    private static String qa = "\u4163\u416e\u4166\u416b\u4168\u4160";
    private static String ra = "\u4143\u416e\u4166\u416b\u4168\u4160";
    private static String sa = "\u416e\u416b\u416b\u4162\u4160\u4166\u416b\u4127\u4171\u4166\u416b\u4172\u4162\u4127\u4161\u4168\u4175\u4127\u4161\u4168\u4169\u4173\u4161\u4166\u4164\u4162\u413d\u4127";
    private static String ta = "\u416e\u416a\u4166\u4160\u4162\u412a\u416b\u4168\u4166\u4163\u416e\u4169\u4160\u4127\u416f\u4166\u4174\u4127\u4165\u4162\u4162\u4169\u4127\u416e\u4169\u4173\u4162\u4175\u4175\u4172\u4177\u4173\u4162\u4163";
    private static String Ca = "\u4168\u4169\u4162\u4127\u4168\u4175\u4127\u416a\u4168\u4175\u4162\u4127\u416e\u416a\u4166\u4160\u4162\u4174\u4127\u416a\u4166\u417e\u4127\u4169\u4168\u4173\u4127\u416f\u4166\u4171\u4162\u4127\u416b\u4168\u4166\u4163\u4162\u4163\u4127\u4164\u4168\u4175\u4175\u4162\u4164\u4173\u416b\u417e";
    private static String Da = "\u4129\u4166\u4172";
    private static String Ea = "\u4168\u4174\u4129\u4169\u4166\u416a\u4162";
    private static String Fa = "\u4150\u416e\u4169";
    private static String Ga = "\u4164\u4168\u4172\u416b\u4163\u4127\u4169\u4168\u4173\u4127\u416b\u4168\u4166\u4163\u4127";
    private static String Ha = "\u413d\u4127";
    private static String Ia = "\u4161\u4166\u4173\u4166\u416b\u4127\u4162\u4175\u4175\u4168\u4175\u413d\u4127";
    private static String Ja = "";
    private static String Ka = "\u4144\u4168\u4177\u417e\u4175\u416e\u4160\u416f\u4173\u4127\u412f\u4164\u412e\u4127\u4127";
    private static String La = "\u412b\u4127";
    private static String Ma = "\u4153\u416f\u416e\u4174\u4127\u4166\u4177\u4177\u416b\u4162\u4173\u4127\u416e\u4174\u4127\u4164\u4168\u4177\u417e\u4175\u416e\u4160\u416f\u4173\u4162\u4163";
    private static String Na = "\u4124";
    private static String Oa = "\u4127\u4164\u4166\u4169\u4120\u4173\u4127\u4165\u4162\u4127\u4177\u4166\u4175\u4174\u4162\u4163";
    private static String Pa = "\u412b";
    private static String Qa = "\u414d\u4166\u4171\u4166\u416b\u4168\u4171\u4162\u4175\u4174\u4127\u4150\u4162\u4165\u4163\u4162\u4174\u416e\u4160\u4169";
    private static String Ra = "\u416d\u4166\u4171\u4166\u416b\u4168\u4171\u4162\u4175\u4174\u4129\u4164\u4168\u416a";
    private static String Sa = "\u4161\u4170\u4162\u4169\u4163\u4129\u4164\u4168\u416a";
    private static String Ta = "\u4163\u4166\u416e\u416b\u417e\u4170\u4168\u4175\u4163\u4174\u4162\u4166\u4175\u4164\u416f\u4129\u4164\u4168\u416a";
    private static String Ua = "\u4163\u4172\u4164\u416c\u4165\u4162\u4175\u4175\u417e\u4129\u4164\u4168\u416a";
    
    public p(final Applet xa, final o za) {
        if (xa == null) {
            throw new IllegalArgumentException(p.w);
        }
        this.xa = xa;
        this.za = za;
        final String _ = this._(p.x, null);
        if (_ != null) {
            this.ya = new s();
            if (!this.ya._(this.xa.getCodeBase(), _, false)) {
                this.b(p.y + _ + p.z);
                this.ya = null;
            }
        }
    }
    
    public boolean b() {
        return this.xa.getCodeBase().getProtocol().startsWith(p.A);
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
        return this.xa.getCodeBase().getHost().indexOf(s) != -1 && this.xa.getDocumentBase().getHost().indexOf(s) != -1;
    }
    
    public boolean _(final long n, final long n2) {
        return new Date().getTime() > n + n2 * 86400000L;
    }
    
    public boolean b(final String s) {
        final String k = this.k(p.B);
        return k != null && k.equalsIgnoreCase(s);
    }
    
    public boolean c(final String s) {
        final String k = this.k(p.C);
        return k != null && k.equalsIgnoreCase(s);
    }
    
    public void b(final int n, final int n2) {
        this.b(p.T + n + p.U + n2 + p.V);
    }
    
    public int a(final String s, final int n, final int n2, final int n3) {
        int int1 = n3;
        String s2;
        if (this.ya != null) {
            s2 = this.ya.a(s);
        }
        else {
            s2 = this.xa.getParameter(s);
        }
        if (s2 != null) {
            try {
                int1 = Integer.parseInt(s2);
                if (!this.a(int1, n, n2)) {
                    int1 = n3;
                    this.b(String.valueOf(s) + p.W + n + p.X + n2 + p.Y);
                }
            }
            catch (NumberFormatException ex) {
                this.b(p.Z + s + p.Y);
            }
        }
        return int1;
    }
    
    public int b(final String s, final int n, final int n2) {
        int int1 = 0;
        String s2;
        if (this.ya != null) {
            s2 = this.ya.a(s);
        }
        else {
            s2 = this.xa.getParameter(s);
        }
        if (s2 != null) {
            try {
                int1 = Integer.parseInt(s2);
                if (!this.a(int1, n, n2)) {
                    this.d(String.valueOf(s) + p.W + n + p.X + n2);
                }
            }
            catch (NumberFormatException ex) {
                this.d(p.Z + s);
            }
        }
        else {
            this.d(String.valueOf(s) + p._a);
        }
        return int1;
    }
    
    public int _(final String s) {
        return this.b(s, 0, 0);
    }
    
    public int b(final String s, final int n) {
        return this.a(s, 0, 0, n);
    }
    
    public boolean a(final int n, final int n2, final int n3) {
        return n2 == n3 || (n >= n2 && n <= n3);
    }
    
    public String k(final String s) {
        String s2;
        if (this.ya != null) {
            s2 = this.ya.a(s);
        }
        else {
            s2 = this.xa.getParameter(s);
        }
        if (s2 == null) {
            this.d(String.valueOf(s) + p._a);
        }
        return s2;
    }
    
    public String _(final String s, final String s2) {
        String s3;
        if (this.ya != null) {
            s3 = this.ya.a(s);
        }
        else {
            s3 = this.xa.getParameter(s);
        }
        if (s3 == null) {
            s3 = s2;
        }
        return s3;
    }
    
    public Color a(final String s, final Color color) {
        Color color2 = color;
        String s2;
        if (this.ya != null) {
            s2 = this.ya.a(s);
        }
        else {
            s2 = this.xa.getParameter(s);
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
                    this.b(p.Z + s + p.Y);
                }
            }
        }
        return color2;
    }
    
    public Color _(final String s) {
        Color color = null;
        String s2;
        if (this.ya != null) {
            s2 = this.ya.a(s);
        }
        else {
            s2 = this.xa.getParameter(s);
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
                    this.d(p.Z + s);
                }
            }
        }
        this.d(String.valueOf(s) + p._a);
        return color;
    }
    
    public boolean a(final String s, final boolean b) {
        boolean b2 = b;
        String s2;
        if (this.ya != null) {
            s2 = this.ya.a(s);
        }
        else {
            s2 = this.xa.getParameter(s);
        }
        if (s2 != null) {
            final String lowerCase = s2.toLowerCase();
            b2 = (lowerCase.startsWith(p.aa) || lowerCase.startsWith(p.ba) || lowerCase.equals(p.ca) || lowerCase.equals(p.da) || lowerCase.equals(p.ea));
        }
        return b2;
    }
    
    public int _(final String s, final int n) {
        int n2 = n;
        String s2 = null;
        try {
            if (this.ya != null) {
                s2 = this.ya.a(s);
            }
            else {
                s2 = this.xa.getParameter(s);
            }
            if (s2 != null) {
                if (s2.equalsIgnoreCase(p.fa)) {
                    n2 = 0;
                }
                else if (s2.equalsIgnoreCase(p.ga)) {
                    n2 = 1;
                }
                else if (s2.equalsIgnoreCase(p.ha)) {
                    n2 = 2;
                }
                else {
                    if (!s2.equalsIgnoreCase(p.ia)) {
                        throw new Exception();
                    }
                    n2 = 3;
                }
            }
        }
        catch (Exception ex) {
            this.b(p.ja + s2 + p.Y);
        }
        return n2;
    }
    
    public String a(final String s, final String s2) {
        String s3 = s2;
        String s4;
        if (this.ya != null) {
            s4 = this.ya.a(s);
        }
        else {
            s4 = this.xa.getParameter(s);
        }
        try {
            if (s4 != null) {
                if (s4.equalsIgnoreCase(p.ka)) {
                    s3 = p.la;
                }
                else if (s4.equalsIgnoreCase(p.ma)) {
                    s3 = p.na;
                }
                else if (s4.equalsIgnoreCase(p.oa)) {
                    s3 = p.pa;
                }
                else {
                    if (!s4.equalsIgnoreCase(p.qa)) {
                        throw new Exception();
                    }
                    s3 = p.ra;
                }
            }
        }
        catch (Exception ex) {
            this.b(p.sa + s4 + p.Y);
        }
        return s3;
    }
    
    public String[] b(final String s, final int n) {
        final Vector vector = new Vector<String>();
        int n2 = n;
        String _;
        while ((_ = this._(String.valueOf(s) + n2++, null)) != null) {
            vector.addElement(_);
        }
        final String[] array = new String[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public void _(final Vector vector, final int n) {
        final MediaTracker mediaTracker = new MediaTracker(this.xa);
        for (int i = 0; i < vector.size(); ++i) {
            mediaTracker.addImage(vector.elementAt(i), n);
        }
        try {
            mediaTracker.waitForID(n);
        }
        catch (InterruptedException ex) {
            this.d(p.ta);
        }
        if ((mediaTracker.statusID(n, false) & 0x6) != 0x0) {
            this.d(p.Ca);
        }
    }
    
    public Image[] a(final String s, final int n) {
        final Vector vector = new Vector<Image>();
        int n2 = 1;
        String _;
        while ((_ = this._(String.valueOf(s) + n2++, null)) != null) {
            vector.addElement(this.xa.getImage(this.xa.getCodeBase(), _));
        }
        this._(vector, n);
        final Image[] array = new Image[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public AudioClip a(String _) {
        if (!_.endsWith(p.Da)) {
            _ = this._(_, null);
        }
        if (_ != null) {
            final AudioClip audioClip = this.xa.getAudioClip(this.xa.getCodeBase(), _);
            b(audioClip);
            _(audioClip);
            return audioClip;
        }
        return null;
    }
    
    public boolean _() {
        try {
            return System.getProperty(p.Ea).startsWith(p.Fa);
        }
        catch (SecurityException ex) {
            return true;
        }
    }
    
    public int a(final String s, final boolean b, final Graphics graphics) {
        final Dimension size = this.xa.getSize();
        return b(s, b, new Rectangle(0, 0, size.width, size.height), graphics);
    }
    
    public void _(final Graphics graphics) {
        graphics.setColor(this.xa.getBackground());
        graphics.fillRect(0, 0, this.xa.getSize().width, this.xa.getSize().height);
    }
    
    public Class _(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            this.d(p.Ga + s);
            return null;
        }
    }
    
    public void b(final String s) {
        System.out.println(String.valueOf(this.xa.getClass().getName()) + p.Ha + s);
    }
    
    public boolean d(final String s) {
        return this.b(s, (Exception)null);
    }
    
    public boolean b(final String s, final Exception ex) {
        this.Aa = true;
        if (this.za != null) {
            this.xa.showStatus(this.za.a());
        }
        this.b(p.Ia + s);
        if (ex != null) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public void b(final boolean ba) {
        this.Ba = ba;
    }
    
    public boolean a() {
        return this.Ba;
    }
    
    public boolean g() {
        return this.Aa;
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
    
    private static String l(final String s) {
        if (s != null && !s.equals(p.Ja)) {
            return p.Ka + Calendar.getInstance().get(1) + p.La + s;
        }
        return p.Ma;
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
    
    public static Color a(String substring) {
        if (substring.startsWith(p.Na)) {
            substring = substring.substring(1);
        }
        if (substring.length() == 6) {
            return new Color(Integer.parseInt(substring, 16));
        }
        throw new IllegalArgumentException(String.valueOf(substring) + p.Oa);
    }
    
    public static Color b(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        if (stringTokenizer.countTokens() == 3) {
            return new Color(Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()));
        }
        throw new IllegalArgumentException(String.valueOf(s) + p.Oa);
    }
    
    public static Color b(final String s) {
        return b(s, p.Pa);
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
    
    public static void _(final int n, final int n2, final int n3, final int n4, final Color color, final Graphics graphics) {
        _(new Rectangle(n, n2, n3, n4), color, graphics);
    }
    
    public static Color b(final Color color) {
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
    
    public static void b(final Writer writer) {
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
        p.w = b(p.w);
        p.x = b(p.x);
        p.y = b(p.y);
        p.z = b(p.z);
        p.A = b(p.A);
        p.B = b(p.B);
        p.C = b(p.C);
        p.T = b(p.T);
        p.U = b(p.U);
        p.V = b(p.V);
        p.W = b(p.W);
        p.X = b(p.X);
        p.Y = b(p.Y);
        p.Z = b(p.Z);
        p._a = b(p._a);
        p.aa = b(p.aa);
        p.ba = b(p.ba);
        p.ca = b(p.ca);
        p.da = b(p.da);
        p.ea = b(p.ea);
        p.fa = b(p.fa);
        p.ga = b(p.ga);
        p.ha = b(p.ha);
        p.ia = b(p.ia);
        p.ja = b(p.ja);
        p.ka = b(p.ka);
        p.la = b(p.la);
        p.ma = b(p.ma);
        p.na = b(p.na);
        p.oa = b(p.oa);
        p.pa = b(p.pa);
        p.qa = b(p.qa);
        p.ra = b(p.ra);
        p.sa = b(p.sa);
        p.ta = b(p.ta);
        p.Ca = b(p.Ca);
        p.Da = b(p.Da);
        p.Ea = b(p.Ea);
        p.Fa = b(p.Fa);
        p.Ga = b(p.Ga);
        p.Ha = b(p.Ha);
        p.Ia = b(p.Ia);
        p.Ja = b(p.Ja);
        p.Ka = b(p.Ka);
        p.La = b(p.La);
        p.Ma = b(p.Ma);
        p.Na = b(p.Na);
        p.Oa = b(p.Oa);
        p.Pa = b(p.Pa);
        p.Qa = b(p.Qa);
        p.Ra = b(p.Ra);
        p.Sa = b(p.Sa);
        p.Ta = b(p.Ta);
        p.Ua = b(p.Ua);
        ua = l(p.Qa);
        wa = new String[] { p.Ra, p.Sa, p.Ta, p.Ua };
    }
    
    private static String b(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF4107);
        }
        return new String(array);
    }
}
