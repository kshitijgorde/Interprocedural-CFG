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

public class abstract
{
    public static final String X;
    public static final long Y = 86400000L;
    public static final String[] Z;
    private Applet _a;
    private break aa;
    private v ba;
    private boolean ca;
    private boolean da;
    private static String Q = "\u4008\u4013\u400a\u400a";
    private static String R = "\u4005\u4009\u4008\u4000\u400f\u4001";
    private static String S = "\u4014\u4003\u4007\u4002\u400f\u4008\u4001\u4046";
    private static String T = "\u4046\u400e\u4007\u4015\u4046\u4000\u4007\u400f\u400a\u4003\u4002";
    private static String U = "\u400e\u4012\u4012\u4016";
    private static String V = "\u4005\u4009\u4016\u401f\u4014\u400f\u4001\u400e\u4012";
    private static String W = "\u400a\u400f\u4005\u4003\u4008\u4015\u4003\u4003";
    private static String ea = "\u4014\u4003\u4005\u4009\u400b\u400b\u4003\u4008\u4002\u4003\u4002\u4046\u4015\u400f\u401c\u4003\u405c\u4046\u4011\u400f\u4002\u4012\u400e\u405b";
    private static String fa = "\u4046\u400e\u4003\u400f\u4001\u400e\u4012\u405b";
    private static String ga = "\u4046\u4010\u4007\u400a\u4013\u4003\u4046\u400e\u4007\u4015\u4046\u4012\u4009\u4046\u4004\u4003\u4046\u4004\u4003\u4012\u4011\u4003\u4003\u4008\u4046";
    private static String ha = "\u4046\u4007\u4008\u4002\u4046";
    private static String ia = "\u405d\u4046\u4013\u4015\u400f\u4008\u4001\u4046\u4002\u4003\u4000\u4007\u4013\u400a\u4012";
    private static String ja = "\u400f\u400a\u400a\u4003\u4001\u4007\u400a\u4046\u4010\u4007\u400a\u4013\u4003\u4046\u4000\u4009\u4014\u4046";
    private static String ka = "\u4046\u4016\u4007\u4014\u4007\u400b\u4003\u4012\u4003\u4014\u4046\u400f\u4015\u4046\u400b\u400f\u4015\u4015\u400f\u4008\u4001";
    private static String la = "\u401f";
    private static String ma = "\u4012";
    private static String na = "\u4057";
    private static String oa = "\u4009\u4008";
    private static String pa = "\u404d";
    private static String qa = "\u4016\u400a\u4007\u400f\u4008";
    private static String ra = "\u4004\u4009\u400a\u4002";
    private static String sa = "\u400f\u4012\u4007\u400a\u400f\u4005";
    private static String ta = "\u4004\u4009\u400a\u4002\u400f\u4012\u4007\u400a\u400f\u4005";
    private static String ua = "\u400f\u400a\u400a\u4003\u4001\u4007\u400a\u4046\u4010\u4007\u400a\u4013\u4003\u4046\u4000\u4009\u4014\u4046\u4000\u4009\u4008\u4012\u4015\u4012\u401f\u400a\u4003\u405c\u4046";
    private static String va = "\u4012\u400f\u400b\u4003\u4015\u4014\u4009\u400b\u4007\u4008";
    private static String wa = "\u4032\u400f\u400b\u4003\u4015\u4034\u4009\u400b\u4007\u4008";
    private static String xa = "\u4007\u4014\u400f\u4007\u400a";
    private static String ya = "\u4027\u4014\u400f\u4007\u400a";
    private static String za = "\u4005\u4009\u4013\u4014\u400f\u4003\u4014";
    private static String Aa = "\u4025\u4009\u4013\u4014\u400f\u4003\u4014";
    private static String Ba = "\u4002\u400f\u4007\u400a\u4009\u4001";
    private static String Ca = "\u4022\u400f\u4007\u400a\u4009\u4001";
    private static String Da = "\u400f\u400a\u400a\u4003\u4001\u4007\u400a\u4046\u4010\u4007\u400a\u4013\u4003\u4046\u4000\u4009\u4014\u4046\u4000\u4009\u4008\u4012\u4000\u4007\u4005\u4003\u405c\u4046";
    private static String Ea = "\u400f\u400b\u4007\u4001\u4003\u404b\u400a\u4009\u4007\u4002\u400f\u4008\u4001\u4046\u400e\u4007\u4015\u4046\u4004\u4003\u4003\u4008\u4046\u400f\u4008\u4012\u4003\u4014\u4014\u4013\u4016\u4012\u4003\u4002";
    private static String Fa = "\u4009\u4008\u4003\u4046\u4009\u4014\u4046\u400b\u4009\u4014\u4003\u4046\u400f\u400b\u4007\u4001\u4003\u4015\u4046\u400b\u4007\u401f\u4046\u4008\u4009\u4012\u4046\u400e\u4007\u4010\u4003\u4046\u400a\u4009\u4007\u4002\u4003\u4002\u4046\u4005\u4009\u4014\u4014\u4003\u4005\u4012\u400a\u401f";
    private static String Ga = "\u4048\u4007\u4013";
    private static String Ha = "\u4009\u4015\u4048\u4008\u4007\u400b\u4003";
    private static String Ia = "\u4031\u400f\u4008";
    private static String Ja = "\u4005\u4009\u4013\u400a\u4002\u4046\u4008\u4009\u4012\u4046\u400a\u4009\u4007\u4002\u4046";
    private static String Ka = "\u405c\u4046";
    private static String La = "\u4000\u4007\u4012\u4007\u400a\u4046\u4003\u4014\u4014\u4009\u4014\u405c\u4046";
    private static String Ma = "";
    private static String Na = "\u4032\u400e\u400f\u4015\u4046\u4007\u4016\u4016\u400a\u4003\u4012\u4046\u400f\u4015\u4046\u4005\u4009\u4016\u401f\u4014\u400f\u4001\u400e\u4012\u4003\u4002";
    private static String Oa = "\u4045";
    private static String Pa = "\u4046\u4005\u4007\u4008\u4041\u4012\u4046\u4004\u4003\u4046\u4016\u4007\u4014\u4015\u4003\u4002";
    private static String Qa = "\u404a";
    private static String Ra = "\u402c\u4007\u4010\u4007\u400a\u4009\u4010\u4003\u4014\u4015\u4046\u4031\u4003\u4004\u4002\u4003\u4015\u400f\u4001\u4008";
    private static String Sa = "\u400c\u4007\u4010\u4007\u400a\u4009\u4010\u4003\u4014\u4015\u4048\u4005\u4009\u400b";
    private static String Ta = "\u4000\u4011\u4003\u4008\u4002\u4048\u4005\u4009\u400b";
    private static String Ua = "\u4002\u4007\u400f\u400a\u401f\u4011\u4009\u4014\u4002\u4015\u4003\u4007\u4014\u4005\u400e\u4048\u4005\u4009\u400b";
    private static String Va = "\u4013\u4008\u4014\u4003\u4007\u400a\u4005\u4007\u4015\u400f\u4008\u4009\u4048\u4005\u4009\u400b";
    private static String Wa = "\u4005\u4014\u401f\u4016\u4012\u400f\u4005\u4000\u4009\u4014\u4013\u400b\u4048\u4005\u4009\u400b";
    private static String Xa = "\u4005\u400a\u4007\u4015\u4015\u400f\u4005\u400b\u4009\u4010\u400f\u4003\u4017\u4013\u400f\u401c\u4048\u4005\u4009\u400b";
    private static String Ya = "\u4002\u4007\u400f\u400a\u401f\u4011\u4009\u4014\u4002\u4001\u4007\u400b\u4003\u4048\u4005\u4009\u400b";
    
    public abstract(final Applet a, final v ba) {
        if (a == null) {
            throw new IllegalArgumentException(abstract.Q);
        }
        this._a = a;
        this.ba = ba;
        final String b = this.b(abstract.R, (String)null);
        if (b != null) {
            this.aa = new break();
            if (!this.aa._(this._a.getCodeBase(), b, false)) {
                this.a(abstract.S + b + abstract.T);
                this.aa = null;
            }
        }
    }
    
    public boolean _() {
        return this._a.getDocumentBase().getProtocol().startsWith(abstract.U);
    }
    
    public boolean b(final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (this.a(array[i])) {
                return true;
            }
        }
        return false;
    }
    
    public boolean a(final String s) {
        return this._a.getDocumentBase().getHost().indexOf(s) != -1;
    }
    
    public boolean a(final long n, final long n2) {
        return new Date().getTime() > n + n2 * 86400000L;
    }
    
    public boolean b(final String s) {
        final String i = this.i(abstract.V);
        return i != null && i.equalsIgnoreCase(s);
    }
    
    public boolean g(final String s) {
        final String i = this.i(abstract.W);
        return i != null && i.equalsIgnoreCase(s);
    }
    
    public void b(final int n, final int n2) {
        this.a(abstract.ea + n + abstract.fa + n2);
    }
    
    public int a(final String s, final int n, final int n2, final int n3) {
        int int1 = n3;
        String s2;
        if (this.aa != null) {
            s2 = this.aa.a(s);
        }
        else {
            s2 = this._a.getParameter(s);
        }
        if (s2 != null) {
            try {
                int1 = Integer.parseInt(s2);
                if (!this._(int1, n, n2)) {
                    int1 = n3;
                    this.a(String.valueOf(s) + abstract.ga + n + abstract.ha + n2 + abstract.ia);
                }
            }
            catch (NumberFormatException ex) {
                this.a(abstract.ja + s + abstract.ia);
            }
        }
        return int1;
    }
    
    public int a(final String s, final int n, final int n2) {
        int int1 = 0;
        String s2;
        if (this.aa != null) {
            s2 = this.aa.a(s);
        }
        else {
            s2 = this._a.getParameter(s);
        }
        if (s2 != null) {
            try {
                int1 = Integer.parseInt(s2);
                if (!this._(int1, n, n2)) {
                    this.h(String.valueOf(s) + abstract.ga + n + abstract.ha + n2);
                }
            }
            catch (NumberFormatException ex) {
                this.h(abstract.ja + s);
            }
        }
        else {
            this.h(String.valueOf(s) + abstract.ka);
        }
        return int1;
    }
    
    public int b(final String s) {
        return this.a(s, 0, 0);
    }
    
    public int a(final String s, final int n) {
        return this.a(s, 0, 0, n);
    }
    
    public boolean _(final int n, final int n2, final int n3) {
        return n2 == n3 || (n >= n2 && n <= n3);
    }
    
    public String i(final String s) {
        String s2;
        if (this.aa != null) {
            s2 = this.aa.a(s);
        }
        else {
            s2 = this._a.getParameter(s);
        }
        if (s2 == null) {
            this.h(String.valueOf(s) + abstract.ka);
        }
        return s2;
    }
    
    public String b(final String s, final String s2) {
        String s3;
        if (this.aa != null) {
            s3 = this.aa.a(s);
        }
        else {
            s3 = this._a.getParameter(s);
        }
        if (s3 == null) {
            s3 = s2;
        }
        return s3;
    }
    
    public Color _(final String s, final Color color) {
        Color color2 = color;
        String s2;
        if (this.aa != null) {
            s2 = this.aa.a(s);
        }
        else {
            s2 = this._a.getParameter(s);
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
                    this.a(abstract.ja + s + abstract.ia);
                }
            }
        }
        return color2;
    }
    
    public Color b(final String s) {
        Color color = null;
        String s2;
        if (this.aa != null) {
            s2 = this.aa.a(s);
        }
        else {
            s2 = this._a.getParameter(s);
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
                    this.h(abstract.ja + s);
                }
            }
        }
        this.h(String.valueOf(s) + abstract.ka);
        return color;
    }
    
    public boolean b(final String s, final boolean b) {
        boolean b2 = b;
        String s2;
        if (this.aa != null) {
            s2 = this.aa.a(s);
        }
        else {
            s2 = this._a.getParameter(s);
        }
        if (s2 != null) {
            final String lowerCase = s2.toLowerCase();
            b2 = (lowerCase.startsWith(abstract.la) || lowerCase.startsWith(abstract.ma) || lowerCase.equals(abstract.na) || lowerCase.equals(abstract.oa) || lowerCase.equals(abstract.pa));
        }
        return b2;
    }
    
    public int b(final String s, final int n) {
        int n2 = n;
        String s2 = null;
        try {
            if (this.aa != null) {
                s2 = this.aa.a(s);
            }
            else {
                s2 = this._a.getParameter(s);
            }
            if (s2 != null) {
                if (s2.equalsIgnoreCase(abstract.qa)) {
                    n2 = 0;
                }
                else if (s2.equalsIgnoreCase(abstract.ra)) {
                    n2 = 1;
                }
                else if (s2.equalsIgnoreCase(abstract.sa)) {
                    n2 = 2;
                }
                else {
                    if (!s2.equalsIgnoreCase(abstract.ta)) {
                        throw new Exception();
                    }
                    n2 = 3;
                }
            }
        }
        catch (Exception ex) {
            this.a(abstract.ua + s2 + abstract.ia);
        }
        return n2;
    }
    
    public String _(final String s, final String s2) {
        String s3 = s2;
        String s4;
        if (this.aa != null) {
            s4 = this.aa.a(s);
        }
        else {
            s4 = this._a.getParameter(s);
        }
        try {
            if (s4 != null) {
                if (s4.equalsIgnoreCase(abstract.va)) {
                    s3 = abstract.wa;
                }
                else if (s4.equalsIgnoreCase(abstract.xa)) {
                    s3 = abstract.ya;
                }
                else if (s4.equalsIgnoreCase(abstract.za)) {
                    s3 = abstract.Aa;
                }
                else {
                    if (!s4.equalsIgnoreCase(abstract.Ba)) {
                        throw new Exception();
                    }
                    s3 = abstract.Ca;
                }
            }
        }
        catch (Exception ex) {
            this.a(abstract.Da + s4 + abstract.ia);
        }
        return s3;
    }
    
    public void _(final Vector vector, final int n) {
        final MediaTracker mediaTracker = new MediaTracker(this._a);
        for (int i = 0; i < vector.size(); ++i) {
            mediaTracker.addImage(vector.elementAt(i), n);
        }
        try {
            mediaTracker.waitForID(n);
        }
        catch (InterruptedException ex) {
            this.h(abstract.Ea);
        }
        if ((mediaTracker.statusID(n, false) & 0x6) != 0x0) {
            this.h(abstract.Fa);
        }
    }
    
    public Image[] b(final String s, final int n) {
        final Vector vector = new Vector<Image>();
        int n2 = 1;
        String b;
        while ((b = this.b(String.valueOf(s) + n2++, (String)null)) != null) {
            vector.addElement(this._a.getImage(this._a.getCodeBase(), b));
        }
        this._(vector, n);
        final Image[] array = new Image[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public AudioClip a(String b) {
        if (!b.endsWith(abstract.Ga)) {
            b = this.b(b, (String)null);
        }
        if (b != null) {
            final AudioClip audioClip = this._a.getAudioClip(this._a.getCodeBase(), b);
            b(audioClip);
            _(audioClip);
            return audioClip;
        }
        return null;
    }
    
    public boolean a() {
        try {
            return System.getProperty(abstract.Ha).startsWith(abstract.Ia);
        }
        catch (SecurityException ex) {
            return true;
        }
    }
    
    public int a(final String s, final boolean b, final Graphics graphics) {
        final Dimension size = this._a.getSize();
        return a(s, b, new Rectangle(0, 0, size.width, size.height), graphics);
    }
    
    public void _(final Graphics graphics) {
        graphics.setColor(this._a.getBackground());
        graphics.fillRect(0, 0, this._a.getSize().width, this._a.getSize().height);
    }
    
    public Class b(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            this.h(abstract.Ja + s);
            return null;
        }
    }
    
    public void a(final String s) {
        System.out.println(String.valueOf(this._a.getClass().getName()) + abstract.Ka + s);
    }
    
    public boolean h(final String s) {
        return this.b(s, (Exception)null);
    }
    
    public boolean b(final String s, final Exception ex) {
        this.ca = true;
        if (this.ba != null) {
            this._a.showStatus(this.ba._());
        }
        this.a(abstract.La + s);
        if (ex != null) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public void a(final boolean da) {
        this.da = da;
    }
    
    public boolean b() {
        return this.da;
    }
    
    public boolean f() {
        return this.ca;
    }
    
    public static int a(final String s, final boolean b, final Rectangle rectangle, final Graphics graphics) {
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(graphics.getFont());
        if (b) {
            return rectangle.x + (rectangle.width - fontMetrics.stringWidth(s)) / 2;
        }
        return rectangle.y + rectangle.height % 2 + rectangle.height / 2 + (fontMetrics.getAscent() - fontMetrics.getDescent()) / 2;
    }
    
    private static String j(final String s) {
        if (s == null || s.equals(abstract.Ma)) {}
        return abstract.Na;
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
        if (substring.startsWith(abstract.Oa)) {
            substring = substring.substring(1);
        }
        if (substring.length() == 6) {
            return new Color(Integer.parseInt(substring, 16));
        }
        throw new IllegalArgumentException(String.valueOf(substring) + abstract.Pa);
    }
    
    public static Color _(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        if (stringTokenizer.countTokens() == 3) {
            return new Color(Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()));
        }
        throw new IllegalArgumentException(String.valueOf(s) + abstract.Pa);
    }
    
    public static Color a(final String s) {
        return _(s, abstract.Qa);
    }
    
    public static void a(final int n, final int n2, final int n3, final int n4, final Graphics graphics) {
        graphics.drawLine(n, n2, n + n3, n2);
        graphics.drawLine(n + n3, n2, n + n3, n2 + n4);
        graphics.drawLine(n, n2, n, n2 + n4);
        graphics.drawLine(n, n2 + n4, n + n3, n2 + n4);
    }
    
    public static void b(final Rectangle rectangle, final Graphics graphics) {
        a(rectangle.x, rectangle.y, rectangle.width, rectangle.height, graphics);
    }
    
    public static void b(final Rectangle rectangle, final Color color, final Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public static void b(final int n, final int n2, final int n3, final int n4, final Color color, final Graphics graphics) {
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
    
    public static void a(final long n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public static void b(final Object o) {
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
        abstract.Q = b(abstract.Q);
        abstract.R = b(abstract.R);
        abstract.S = b(abstract.S);
        abstract.T = b(abstract.T);
        abstract.U = b(abstract.U);
        abstract.V = b(abstract.V);
        abstract.W = b(abstract.W);
        abstract.ea = b(abstract.ea);
        abstract.fa = b(abstract.fa);
        abstract.ga = b(abstract.ga);
        abstract.ha = b(abstract.ha);
        abstract.ia = b(abstract.ia);
        abstract.ja = b(abstract.ja);
        abstract.ka = b(abstract.ka);
        abstract.la = b(abstract.la);
        abstract.ma = b(abstract.ma);
        abstract.na = b(abstract.na);
        abstract.oa = b(abstract.oa);
        abstract.pa = b(abstract.pa);
        abstract.qa = b(abstract.qa);
        abstract.ra = b(abstract.ra);
        abstract.sa = b(abstract.sa);
        abstract.ta = b(abstract.ta);
        abstract.ua = b(abstract.ua);
        abstract.va = b(abstract.va);
        abstract.wa = b(abstract.wa);
        abstract.xa = b(abstract.xa);
        abstract.ya = b(abstract.ya);
        abstract.za = b(abstract.za);
        abstract.Aa = b(abstract.Aa);
        abstract.Ba = b(abstract.Ba);
        abstract.Ca = b(abstract.Ca);
        abstract.Da = b(abstract.Da);
        abstract.Ea = b(abstract.Ea);
        abstract.Fa = b(abstract.Fa);
        abstract.Ga = b(abstract.Ga);
        abstract.Ha = b(abstract.Ha);
        abstract.Ia = b(abstract.Ia);
        abstract.Ja = b(abstract.Ja);
        abstract.Ka = b(abstract.Ka);
        abstract.La = b(abstract.La);
        abstract.Ma = b(abstract.Ma);
        abstract.Na = b(abstract.Na);
        abstract.Oa = b(abstract.Oa);
        abstract.Pa = b(abstract.Pa);
        abstract.Qa = b(abstract.Qa);
        abstract.Ra = b(abstract.Ra);
        abstract.Sa = b(abstract.Sa);
        abstract.Ta = b(abstract.Ta);
        abstract.Ua = b(abstract.Ua);
        abstract.Va = b(abstract.Va);
        abstract.Wa = b(abstract.Wa);
        abstract.Xa = b(abstract.Xa);
        abstract.Ya = b(abstract.Ya);
        X = j(abstract.Ra);
        Z = new String[] { abstract.Sa, abstract.Ta, abstract.Ua, abstract.Va, abstract.Wa, abstract.Xa, abstract.Ya };
    }
    
    private static String b(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u4066');
        }
        return new String(array);
    }
}
