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

public class v
{
    public static final String n;
    public static final long o = 86400000L;
    public static final String[] p;
    private Applet q;
    private break r;
    private u s;
    private boolean t;
    private boolean u;
    private static String d = "\u831a\u8301\u8318\u8318";
    private static String e = "\u8317\u831b\u831a\u8312\u831d\u8313";
    private static String f = "\u8306\u8311\u8315\u8310\u831d\u831a\u8313\u8354";
    private static String g = "\u8354\u831c\u8315\u8307\u8354\u8312\u8315\u831d\u8318\u8311\u8310";
    private static String h = "\u831c\u8300\u8300\u8304";
    private static String i = "\u8317\u831b\u8304\u830d\u8306\u831d\u8313\u831c\u8300";
    private static String j = "\u8318\u831d\u8317\u8311\u831a\u8307\u8311\u8311";
    private static String v = "\u8306\u8311\u8317\u831b\u8319\u8319\u8311\u831a\u8310\u8311\u8310\u8354\u8307\u831d\u830e\u8311\u834e\u8354\u8303\u831d\u8310\u8300\u831c\u8349";
    private static String w = "\u8354\u831c\u8311\u831d\u8313\u831c\u8300\u8349";
    private static String x = "\u8354\u8302\u8315\u8318\u8301\u8311\u8354\u831c\u8315\u8307\u8354\u8300\u831b\u8354\u8316\u8311\u8354\u8316\u8311\u8300\u8303\u8311\u8311\u831a\u8354";
    private static String y = "\u8354\u8315\u831a\u8310\u8354";
    private static String z = "\u834f\u8354\u8301\u8307\u831d\u831a\u8313\u8354\u8310\u8311\u8312\u8315\u8301\u8318\u8300";
    private static String A = "\u831d\u8318\u8318\u8311\u8313\u8315\u8318\u8354\u8302\u8315\u8318\u8301\u8311\u8354\u8312\u831b\u8306\u8354";
    private static String B = "\u8354\u8304\u8315\u8306\u8315\u8319\u8311\u8300\u8311\u8306\u8354\u831d\u8307\u8354\u8319\u831d\u8307\u8307\u831d\u831a\u8313";
    private static String C = "\u830d";
    private static String D = "\u8300";
    private static String E = "\u8345";
    private static String F = "\u831b\u831a";
    private static String G = "\u835f";
    private static String H = "\u8304\u8318\u8315\u831d\u831a";
    private static String I = "\u8316\u831b\u8318\u8310";
    private static String J = "\u831d\u8300\u8315\u8318\u831d\u8317";
    private static String K = "\u8316\u831b\u8318\u8310\u831d\u8300\u8315\u8318\u831d\u8317";
    private static String L = "\u831d\u8318\u8318\u8311\u8313\u8315\u8318\u8354\u8302\u8315\u8318\u8301\u8311\u8354\u8312\u831b\u8306\u8354\u8312\u831b\u831a\u8300\u8307\u8300\u830d\u8318\u8311\u834e\u8354";
    private static String M = "\u8300\u831d\u8319\u8311\u8307\u8306\u831b\u8319\u8315\u831a";
    private static String N = "\u8320\u831d\u8319\u8311\u8307\u8326\u831b\u8319\u8315\u831a";
    private static String O = "\u8315\u8306\u831d\u8315\u8318";
    private static String P = "\u8335\u8306\u831d\u8315\u8318";
    private static String Q = "\u8317\u831b\u8301\u8306\u831d\u8311\u8306";
    private static String R = "\u8337\u831b\u8301\u8306\u831d\u8311\u8306";
    private static String S = "\u8310\u831d\u8315\u8318\u831b\u8313";
    private static String T = "\u8330\u831d\u8315\u8318\u831b\u8313";
    private static String U = "\u831d\u8318\u8318\u8311\u8313\u8315\u8318\u8354\u8302\u8315\u8318\u8301\u8311\u8354\u8312\u831b\u8306\u8354\u8312\u831b\u831a\u8300\u8312\u8315\u8317\u8311\u834e\u8354";
    private static String V = "\u831d\u8319\u8315\u8313\u8311\u8359\u8318\u831b\u8315\u8310\u831d\u831a\u8313\u8354\u831c\u8315\u8307\u8354\u8316\u8311\u8311\u831a\u8354\u831d\u831a\u8300\u8311\u8306\u8306\u8301\u8304\u8300\u8311\u8310";
    private static String W = "\u831b\u831a\u8311\u8354\u831b\u8306\u8354\u8319\u831b\u8306\u8311\u8354\u831d\u8319\u8315\u8313\u8311\u8307\u8354\u8319\u8315\u830d\u8354\u831a\u831b\u8300\u8354\u831c\u8315\u8302\u8311\u8354\u8318\u831b\u8315\u8310\u8311\u8310\u8354\u8317\u831b\u8306\u8306\u8311\u8317\u8300\u8318\u830d";
    private static String X = "\u835a\u8315\u8301";
    private static String Y = "\u831b\u8307\u835a\u831a\u8315\u8319\u8311";
    private static String Z = "\u8323\u831d\u831a";
    private static String _a = "\u8317\u831b\u8301\u8318\u8310\u8354\u831a\u831b\u8300\u8354\u8318\u831b\u8315\u8310\u8354";
    private static String aa = "\u834e\u8354";
    private static String ba = "\u8312\u8315\u8300\u8315\u8318\u8354\u8311\u8306\u8306\u831b\u8306\u834e\u8354";
    private static String ca = "";
    private static String da = "\u8320\u831c\u831d\u8307\u8354\u8315\u8304\u8304\u8318\u8311\u8300\u8354\u831d\u8307\u8354\u8317\u831b\u8304\u830d\u8306\u831d\u8313\u831c\u8300\u8311\u8310";
    private static String ea = "\u8357";
    private static String fa = "\u8354\u8317\u8315\u831a\u8353\u8300\u8354\u8316\u8311\u8354\u8304\u8315\u8306\u8307\u8311\u8310";
    private static String ga = "\u8358";
    private static String ha = "\u833e\u8315\u8302\u8315\u8318\u831b\u8302\u8311\u8306\u8307\u8354\u8323\u8311\u8316\u8310\u8311\u8307\u831d\u8313\u831a";
    private static String ia = "\u831e\u8315\u8302\u8315\u8318\u831b\u8302\u8311\u8306\u8307\u835a\u8317\u831b\u8319";
    private static String ja = "\u8312\u8303\u8311\u831a\u8310\u835a\u8317\u831b\u8319";
    private static String ka = "\u8310\u8315\u831d\u8318\u830d\u8303\u831b\u8306\u8310\u8307\u8311\u8315\u8306\u8317\u831c\u835a\u8317\u831b\u8319";
    private static String la = "\u8301\u831a\u8306\u8311\u8315\u8318\u8317\u8315\u8307\u831d\u831a\u831b\u835a\u8317\u831b\u8319";
    private static String ma = "\u8317\u8306\u830d\u8304\u8300\u831d\u8317\u8312\u831b\u8306\u8301\u8319\u835a\u8317\u831b\u8319";
    private static String na = "\u8317\u8318\u8315\u8307\u8307\u831d\u8317\u8319\u831b\u8302\u831d\u8311\u8305\u8301\u831d\u830e\u835a\u8317\u831b\u8319";
    
    public v(final Applet q, final u s) {
        if (q == null) {
            throw new IllegalArgumentException(v.d);
        }
        this.q = q;
        this.s = s;
        final String _ = this._(v.e, null);
        if (_ != null) {
            this.r = new break();
            if (!this.r._(this.q.getCodeBase(), _, false)) {
                this.a(v.f + _ + v.g);
                this.r = null;
            }
        }
    }
    
    public boolean _() {
        return this.q.getDocumentBase().getProtocol().startsWith(v.h);
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
        return this.q.getDocumentBase().getHost().indexOf(s) != -1;
    }
    
    public boolean _(final long n, final long n2) {
        return new Date().getTime() > n + n2 * 86400000L;
    }
    
    public boolean a(final String s) {
        final String h = this.h(v.i);
        return h != null && h.equalsIgnoreCase(s);
    }
    
    public boolean k(final String s) {
        final String h = this.h(v.j);
        return h != null && h.equalsIgnoreCase(s);
    }
    
    public void b(final int n, final int n2) {
        this.a(v.v + n + v.w + n2);
    }
    
    public int a(final String s, final int n, final int n2, final int n3) {
        int int1 = n3;
        String s2;
        if (this.r != null) {
            s2 = this.r._(s);
        }
        else {
            s2 = this.q.getParameter(s);
        }
        if (s2 != null) {
            try {
                int1 = Integer.parseInt(s2);
                if (!this.b(int1, n, n2)) {
                    int1 = n3;
                    this.a(String.valueOf(s) + v.x + n + v.y + n2 + v.z);
                }
            }
            catch (NumberFormatException ex) {
                this.a(v.A + s + v.z);
            }
        }
        return int1;
    }
    
    public int a(final String s, final int n, final int n2) {
        int int1 = 0;
        String s2;
        if (this.r != null) {
            s2 = this.r._(s);
        }
        else {
            s2 = this.q.getParameter(s);
        }
        if (s2 != null) {
            try {
                int1 = Integer.parseInt(s2);
                if (!this.b(int1, n, n2)) {
                    this.l(String.valueOf(s) + v.x + n + v.y + n2);
                }
            }
            catch (NumberFormatException ex) {
                this.l(v.A + s);
            }
        }
        else {
            this.l(String.valueOf(s) + v.B);
        }
        return int1;
    }
    
    public int b(final String s) {
        return this.a(s, 0, 0);
    }
    
    public int a(final String s, final int n) {
        return this.a(s, 0, 0, n);
    }
    
    public boolean b(final int n, final int n2, final int n3) {
        return n2 == n3 || (n >= n2 && n <= n3);
    }
    
    public String h(final String s) {
        String s2;
        if (this.r != null) {
            s2 = this.r._(s);
        }
        else {
            s2 = this.q.getParameter(s);
        }
        if (s2 == null) {
            this.l(String.valueOf(s) + v.B);
        }
        return s2;
    }
    
    public String _(final String s, final String s2) {
        String s3;
        if (this.r != null) {
            s3 = this.r._(s);
        }
        else {
            s3 = this.q.getParameter(s);
        }
        if (s3 == null) {
            s3 = s2;
        }
        return s3;
    }
    
    public Color a(final String s, final Color color) {
        Color color2 = color;
        String s2;
        if (this.r != null) {
            s2 = this.r._(s);
        }
        else {
            s2 = this.q.getParameter(s);
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
                    this.a(v.A + s + v.z);
                }
            }
        }
        return color2;
    }
    
    public Color _(final String s) {
        Color color = null;
        String s2;
        if (this.r != null) {
            s2 = this.r._(s);
        }
        else {
            s2 = this.q.getParameter(s);
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
                    this.l(v.A + s);
                }
            }
        }
        this.l(String.valueOf(s) + v.B);
        return color;
    }
    
    public boolean a(final String s, final boolean b) {
        boolean b2 = b;
        String s2;
        if (this.r != null) {
            s2 = this.r._(s);
        }
        else {
            s2 = this.q.getParameter(s);
        }
        if (s2 != null) {
            final String lowerCase = s2.toLowerCase();
            b2 = (lowerCase.startsWith(v.C) || lowerCase.startsWith(v.D) || lowerCase.equals(v.E) || lowerCase.equals(v.F) || lowerCase.equals(v.G));
        }
        return b2;
    }
    
    public int b(final String s, final int n) {
        int n2 = n;
        String s2 = null;
        try {
            if (this.r != null) {
                s2 = this.r._(s);
            }
            else {
                s2 = this.q.getParameter(s);
            }
            if (s2 != null) {
                if (s2.equalsIgnoreCase(v.H)) {
                    n2 = 0;
                }
                else if (s2.equalsIgnoreCase(v.I)) {
                    n2 = 1;
                }
                else if (s2.equalsIgnoreCase(v.J)) {
                    n2 = 2;
                }
                else {
                    if (!s2.equalsIgnoreCase(v.K)) {
                        throw new Exception();
                    }
                    n2 = 3;
                }
            }
        }
        catch (Exception ex) {
            this.a(v.L + s2 + v.z);
        }
        return n2;
    }
    
    public String a(final String s, final String s2) {
        String s3 = s2;
        String s4;
        if (this.r != null) {
            s4 = this.r._(s);
        }
        else {
            s4 = this.q.getParameter(s);
        }
        try {
            s4 = this.q.getParameter(s);
            if (s4 != null) {
                if (s4.equalsIgnoreCase(v.M)) {
                    s3 = v.N;
                }
                else if (s4.equalsIgnoreCase(v.O)) {
                    s3 = v.P;
                }
                else if (s4.equalsIgnoreCase(v.Q)) {
                    s3 = v.R;
                }
                else {
                    if (!s4.equalsIgnoreCase(v.S)) {
                        throw new Exception();
                    }
                    s3 = v.T;
                }
            }
        }
        catch (Exception ex) {
            this.a(v.U + s4 + v.z);
        }
        return s3;
    }
    
    public void _(final Vector vector, final int n) {
        final MediaTracker mediaTracker = new MediaTracker(this.q);
        for (int i = 0; i < vector.size(); ++i) {
            mediaTracker.addImage(vector.elementAt(i), n);
        }
        try {
            mediaTracker.waitForID(n);
        }
        catch (InterruptedException ex) {
            this.l(v.V);
        }
        if ((mediaTracker.statusID(n, false) & 0x6) != 0x0) {
            this.l(v.W);
        }
    }
    
    public Image[] b(final String s, final int n) {
        final Vector vector = new Vector<Image>();
        int n2 = 1;
        String parameter;
        while ((parameter = this.q.getParameter(String.valueOf(s) + n2++)) != null) {
            vector.addElement(this.q.getImage(this.q.getCodeBase(), parameter));
        }
        this._(vector, n);
        final Image[] array = new Image[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public AudioClip _(String _) {
        if (!_.endsWith(v.X)) {
            _ = this._(_, null);
        }
        if (_ != null) {
            final AudioClip audioClip = this.q.getAudioClip(this.q.getCodeBase(), _);
            a(audioClip);
            b(audioClip);
            return audioClip;
        }
        return null;
    }
    
    public boolean a() {
        try {
            return System.getProperty(v.Y).startsWith(v.Z);
        }
        catch (SecurityException ex) {
            return true;
        }
    }
    
    public int b(final String s, final boolean b, final Graphics graphics) {
        final Dimension size = this.q.size();
        return _(s, b, new Rectangle(0, 0, size.width, size.height), graphics);
    }
    
    public void b(final Graphics graphics) {
        graphics.setColor(this.q.getBackground());
        graphics.fillRect(0, 0, this.q.size().width, this.q.size().height);
    }
    
    public Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            this.l(v._a + s);
            return null;
        }
    }
    
    public void a(final String s) {
        System.out.println(String.valueOf(this.q.getClass().getName()) + v.aa + s);
    }
    
    public boolean l(final String s) {
        return this.b(s, (Exception)null);
    }
    
    public boolean b(final String s, final Exception ex) {
        this.t = true;
        if (this.s != null) {
            this.q.showStatus(this.s.a());
        }
        this.a(v.ba + s);
        if (ex != null) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public void b(final boolean u) {
        this.u = u;
    }
    
    public boolean b() {
        return this.u;
    }
    
    public boolean j() {
        return this.t;
    }
    
    public static int _(final String s, final boolean b, final Rectangle rectangle, final Graphics graphics) {
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(graphics.getFont());
        if (b) {
            return rectangle.x + (rectangle.width - fontMetrics.stringWidth(s)) / 2;
        }
        return rectangle.y + rectangle.height % 2 + rectangle.height / 2 + (fontMetrics.getAscent() - fontMetrics.getDescent()) / 2;
    }
    
    private static String i(final String s) {
        if (s == null || s.equals(v.ca)) {}
        return v.da;
    }
    
    public static boolean b(final long n) {
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
        if (substring.startsWith(v.ea)) {
            substring = substring.substring(1);
        }
        if (substring.length() == 6) {
            return new Color(Integer.parseInt(substring, 16));
        }
        throw new IllegalArgumentException(String.valueOf(substring) + v.fa);
    }
    
    public static Color b(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        if (stringTokenizer.countTokens() == 3) {
            return new Color(Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()));
        }
        throw new IllegalArgumentException(String.valueOf(s) + v.fa);
    }
    
    public static Color b(final String s) {
        return b(s, v.ga);
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
    
    public static void b(final Rectangle rectangle, final Color color, final Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public static void a(final int n, final int n2, final int n3, final int n4, final Color color, final Graphics graphics) {
        b(new Rectangle(n, n2, n3, n4), color, graphics);
    }
    
    public static Color a(final Color color) {
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
    
    public static void _(final Writer writer) {
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
        v.d = a(v.d);
        v.e = a(v.e);
        v.f = a(v.f);
        v.g = a(v.g);
        v.h = a(v.h);
        v.i = a(v.i);
        v.j = a(v.j);
        v.v = a(v.v);
        v.w = a(v.w);
        v.x = a(v.x);
        v.y = a(v.y);
        v.z = a(v.z);
        v.A = a(v.A);
        v.B = a(v.B);
        v.C = a(v.C);
        v.D = a(v.D);
        v.E = a(v.E);
        v.F = a(v.F);
        v.G = a(v.G);
        v.H = a(v.H);
        v.I = a(v.I);
        v.J = a(v.J);
        v.K = a(v.K);
        v.L = a(v.L);
        v.M = a(v.M);
        v.N = a(v.N);
        v.O = a(v.O);
        v.P = a(v.P);
        v.Q = a(v.Q);
        v.R = a(v.R);
        v.S = a(v.S);
        v.T = a(v.T);
        v.U = a(v.U);
        v.V = a(v.V);
        v.W = a(v.W);
        v.X = a(v.X);
        v.Y = a(v.Y);
        v.Z = a(v.Z);
        v._a = a(v._a);
        v.aa = a(v.aa);
        v.ba = a(v.ba);
        v.ca = a(v.ca);
        v.da = a(v.da);
        v.ea = a(v.ea);
        v.fa = a(v.fa);
        v.ga = a(v.ga);
        v.ha = a(v.ha);
        v.ia = a(v.ia);
        v.ja = a(v.ja);
        v.ka = a(v.ka);
        v.la = a(v.la);
        v.ma = a(v.ma);
        v.na = a(v.na);
        n = i(v.ha);
        p = new String[] { v.ia, v.ja, v.ka, v.la, v.ma, v.na };
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF8374);
        }
        return new String(array);
    }
}
