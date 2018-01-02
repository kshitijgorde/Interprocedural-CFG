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

public class private
{
    public static final String pb;
    public static final long qb = 86400000L;
    public static final String[] rb;
    private Applet sb;
    private protected tb;
    private package _;
    private boolean a;
    private boolean b;
    private static String ib = "\ua208\ua213\ua20a\ua20a";
    private static String jb = "\ua205\ua209\ua208\ua200\ua20f\ua201";
    private static String kb = "\ua214\ua203\ua207\ua202\ua20f\ua208\ua201\ua246";
    private static String lb = "\ua246\ua20e\ua207\ua215\ua246\ua200\ua207\ua20f\ua20a\ua203\ua202";
    private static String mb = "\ua20e\ua212\ua212\ua216";
    private static String nb = "\ua205\ua209\ua216\ua21f\ua214\ua20f\ua201\ua20e\ua212";
    private static String ob = "\ua20a\ua20f\ua205\ua203\ua208\ua215\ua203\ua203";
    private static String c = "\ua214\ua203\ua205\ua209\ua20b\ua20b\ua203\ua208\ua202\ua203\ua202\ua246\ua215\ua20f\ua21c\ua203\ua25c\ua246\ua211\ua20f\ua202\ua212\ua20e\ua25b";
    private static String d = "\ua246\ua20e\ua203\ua20f\ua201\ua20e\ua212\ua25b";
    private static String e = "\ua246\ua210\ua207\ua20a\ua213\ua203\ua246\ua20e\ua207\ua215\ua246\ua212\ua209\ua246\ua204\ua203\ua246\ua204\ua203\ua212\ua211\ua203\ua203\ua208\ua246";
    private static String f = "\ua246\ua207\ua208\ua202\ua246";
    private static String g = "\ua25d\ua246\ua213\ua215\ua20f\ua208\ua201\ua246\ua202\ua203\ua200\ua207\ua213\ua20a\ua212";
    private static String h = "\ua20f\ua20a\ua20a\ua203\ua201\ua207\ua20a\ua246\ua210\ua207\ua20a\ua213\ua203\ua246\ua200\ua209\ua214\ua246";
    private static String i = "\ua246\ua216\ua207\ua214\ua207\ua20b\ua203\ua212\ua203\ua214\ua246\ua20f\ua215\ua246\ua20b\ua20f\ua215\ua215\ua20f\ua208\ua201";
    private static String j = "\ua21f";
    private static String k = "\ua212";
    private static String l = "\ua257";
    private static String m = "\ua209\ua208";
    private static String n = "\ua24d";
    private static String o = "\ua216\ua20a\ua207\ua20f\ua208";
    private static String p = "\ua204\ua209\ua20a\ua202";
    private static String q = "\ua20f\ua212\ua207\ua20a\ua20f\ua205";
    private static String r = "\ua204\ua209\ua20a\ua202\ua20f\ua212\ua207\ua20a\ua20f\ua205";
    private static String s = "\ua20f\ua20a\ua20a\ua203\ua201\ua207\ua20a\ua246\ua210\ua207\ua20a\ua213\ua203\ua246\ua200\ua209\ua214\ua246\ua200\ua209\ua208\ua212\ua215\ua212\ua21f\ua20a\ua203\ua25c\ua246";
    private static String t = "\ua212\ua20f\ua20b\ua203\ua215\ua214\ua209\ua20b\ua207\ua208";
    private static String u = "\ua232\ua20f\ua20b\ua203\ua215\ua234\ua209\ua20b\ua207\ua208";
    private static String v = "\ua207\ua214\ua20f\ua207\ua20a";
    private static String w = "\ua227\ua214\ua20f\ua207\ua20a";
    private static String x = "\ua205\ua209\ua213\ua214\ua20f\ua203\ua214";
    private static String y = "\ua225\ua209\ua213\ua214\ua20f\ua203\ua214";
    private static String z = "\ua202\ua20f\ua207\ua20a\ua209\ua201";
    private static String A = "\ua222\ua20f\ua207\ua20a\ua209\ua201";
    private static String B = "\ua20f\ua20a\ua20a\ua203\ua201\ua207\ua20a\ua246\ua210\ua207\ua20a\ua213\ua203\ua246\ua200\ua209\ua214\ua246\ua200\ua209\ua208\ua212\ua200\ua207\ua205\ua203\ua25c\ua246";
    private static String C = "\ua20f\ua20b\ua207\ua201\ua203\ua24b\ua20a\ua209\ua207\ua202\ua20f\ua208\ua201\ua246\ua20e\ua207\ua215\ua246\ua204\ua203\ua203\ua208\ua246\ua20f\ua208\ua212\ua203\ua214\ua214\ua213\ua216\ua212\ua203\ua202";
    private static String D = "\ua209\ua208\ua203\ua246\ua209\ua214\ua246\ua20b\ua209\ua214\ua203\ua246\ua20f\ua20b\ua207\ua201\ua203\ua215\ua246\ua20b\ua207\ua21f\ua246\ua208\ua209\ua212\ua246\ua20e\ua207\ua210\ua203\ua246\ua20a\ua209\ua207\ua202\ua203\ua202\ua246\ua205\ua209\ua214\ua214\ua203\ua205\ua212\ua20a\ua21f";
    private static String E = "\ua248\ua207\ua213";
    private static String F = "\ua209\ua215\ua248\ua208\ua207\ua20b\ua203";
    private static String G = "\ua231\ua20f\ua208";
    private static String H = "\ua205\ua209\ua213\ua20a\ua202\ua246\ua208\ua209\ua212\ua246\ua20a\ua209\ua207\ua202\ua246";
    private static String I = "\ua25c\ua246";
    private static String J = "\ua200\ua207\ua212\ua207\ua20a\ua246\ua203\ua214\ua214\ua209\ua214\ua25c\ua246";
    private static String K = "";
    private static String L = "\ua225\ua209\ua216\ua21f\ua214\ua20f\ua201\ua20e\ua212\ua246\ua24e\ua205\ua24f\ua246\ua246";
    private static String M = "\ua24a\ua246";
    private static String N = "\ua232\ua20e\ua20f\ua215\ua246\ua207\ua216\ua216\ua20a\ua203\ua212\ua246\ua20f\ua215\ua246\ua205\ua209\ua216\ua21f\ua214\ua20f\ua201\ua20e\ua212\ua203\ua202";
    private static String O = "\ua245";
    private static String P = "\ua246\ua205\ua207\ua208\ua241\ua212\ua246\ua204\ua203\ua246\ua216\ua207\ua214\ua215\ua203\ua202";
    private static String Q = "\ua24a";
    private static String R = "\ua22c\ua207\ua210\ua207\ua20a\ua209\ua210\ua203\ua214\ua215\ua246\ua231\ua203\ua204\ua202\ua203\ua215\ua20f\ua201\ua208";
    private static String S = "\ua20c\ua207\ua210\ua207\ua20a\ua209\ua210\ua203\ua214\ua215\ua248\ua205\ua209\ua20b";
    private static String T = "\ua200\ua211\ua203\ua208\ua202\ua248\ua205\ua209\ua20b";
    private static String U = "\ua202\ua207\ua20f\ua20a\ua21f\ua211\ua209\ua214\ua202\ua215\ua203\ua207\ua214\ua205\ua20e\ua248\ua205\ua209\ua20b";
    private static String V = "\ua213\ua208\ua214\ua203\ua207\ua20a\ua205\ua207\ua215\ua20f\ua208\ua209\ua248\ua205\ua209\ua20b";
    private static String W = "\ua205\ua214\ua21f\ua216\ua212\ua20f\ua205\ua200\ua209\ua214\ua213\ua20b\ua248\ua205\ua209\ua20b";
    private static String X = "\ua205\ua20a\ua207\ua215\ua215\ua20f\ua205\ua20b\ua209\ua210\ua20f\ua203\ua217\ua213\ua20f\ua21c\ua248\ua205\ua209\ua20b";
    private static String Y = "\ua202\ua207\ua20f\ua20a\ua21f\ua211\ua209\ua214\ua202\ua201\ua207\ua20b\ua203\ua248\ua205\ua209\ua20b";
    
    public private(final Applet sb, final package _) {
        if (sb == null) {
            throw new IllegalArgumentException(private.ib);
        }
        this.sb = sb;
        this._ = _;
        final String _2 = this._(private.jb, (String)null);
        if (_2 != null) {
            this.tb = new protected();
            if (!this.tb.a(this.sb.getCodeBase(), _2, false)) {
                this.a(private.kb + _2 + private.lb);
                this.tb = null;
            }
        }
    }
    
    public boolean b() {
        return this.sb.getCodeBase().getProtocol().startsWith(private.mb);
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
        return this.sb.getCodeBase().getHost().indexOf(s) != -1 && this.sb.getDocumentBase().getHost().indexOf(s) != -1;
    }
    
    public boolean _(final long n, final long n2) {
        return new Date().getTime() > n + n2 * 86400000L;
    }
    
    public boolean _(final String s) {
        final String d = this.d(private.nb);
        return d != null && d.equalsIgnoreCase(s);
    }
    
    public boolean m(final String s) {
        final String d = this.d(private.ob);
        return d != null && d.equalsIgnoreCase(s);
    }
    
    public void _(final int n, final int n2) {
        this.a(private.c + n + private.d + n2);
    }
    
    public int b(final String s, final int n, final int n2, final int n3) {
        int int1 = n3;
        String s2;
        if (this.tb != null) {
            s2 = this.tb._(s);
        }
        else {
            s2 = this.sb.getParameter(s);
        }
        if (s2 != null) {
            try {
                int1 = Integer.parseInt(s2);
                if (!this.b(int1, n, n2)) {
                    int1 = n3;
                    this.a(String.valueOf(s) + private.e + n + private.f + n2 + private.g);
                }
            }
            catch (NumberFormatException ex) {
                this.a(private.h + s + private.g);
            }
        }
        return int1;
    }
    
    public int a(final String s, final int n, final int n2) {
        int int1 = 0;
        String s2;
        if (this.tb != null) {
            s2 = this.tb._(s);
        }
        else {
            s2 = this.sb.getParameter(s);
        }
        if (s2 != null) {
            try {
                int1 = Integer.parseInt(s2);
                if (!this.b(int1, n, n2)) {
                    this.n(String.valueOf(s) + private.e + n + private.f + n2);
                }
            }
            catch (NumberFormatException ex) {
                this.n(private.h + s);
            }
        }
        else {
            this.n(String.valueOf(s) + private.i);
        }
        return int1;
    }
    
    public int _(final String s) {
        return this.a(s, 0, 0);
    }
    
    public int a(final String s, final int n) {
        return this.b(s, 0, 0, n);
    }
    
    public boolean b(final int n, final int n2, final int n3) {
        return n2 == n3 || (n >= n2 && n <= n3);
    }
    
    public String d(final String s) {
        String s2;
        if (this.tb != null) {
            s2 = this.tb._(s);
        }
        else {
            s2 = this.sb.getParameter(s);
        }
        if (s2 == null) {
            this.n(String.valueOf(s) + private.i);
        }
        return s2;
    }
    
    public String _(final String s, final String s2) {
        String s3;
        if (this.tb != null) {
            s3 = this.tb._(s);
        }
        else {
            s3 = this.sb.getParameter(s);
        }
        if (s3 == null) {
            s3 = s2;
        }
        return s3;
    }
    
    public Color a(final String s, final Color color) {
        Color color2 = color;
        String s2;
        if (this.tb != null) {
            s2 = this.tb._(s);
        }
        else {
            s2 = this.sb.getParameter(s);
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
                    this.a(private.h + s + private.g);
                }
            }
        }
        return color2;
    }
    
    public Color b(final String s) {
        Color color = null;
        String s2;
        if (this.tb != null) {
            s2 = this.tb._(s);
        }
        else {
            s2 = this.sb.getParameter(s);
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
                    this.n(private.h + s);
                }
            }
        }
        this.n(String.valueOf(s) + private.i);
        return color;
    }
    
    public boolean b(final String s, final boolean b) {
        boolean b2 = b;
        String s2;
        if (this.tb != null) {
            s2 = this.tb._(s);
        }
        else {
            s2 = this.sb.getParameter(s);
        }
        if (s2 != null) {
            final String lowerCase = s2.toLowerCase();
            b2 = (lowerCase.startsWith(private.j) || lowerCase.startsWith(private.k) || lowerCase.equals(private.l) || lowerCase.equals(private.m) || lowerCase.equals(private.n));
        }
        return b2;
    }
    
    public int b(final String s, final int n) {
        int n2 = n;
        String s2 = null;
        try {
            if (this.tb != null) {
                s2 = this.tb._(s);
            }
            else {
                s2 = this.sb.getParameter(s);
            }
            if (s2 != null) {
                if (s2.equalsIgnoreCase(private.o)) {
                    n2 = 0;
                }
                else if (s2.equalsIgnoreCase(private.p)) {
                    n2 = 1;
                }
                else if (s2.equalsIgnoreCase(private.q)) {
                    n2 = 2;
                }
                else {
                    if (!s2.equalsIgnoreCase(private.r)) {
                        throw new Exception();
                    }
                    n2 = 3;
                }
            }
        }
        catch (Exception ex) {
            this.a(private.s + s2 + private.g);
        }
        return n2;
    }
    
    public String a(final String s, final String s2) {
        String s3 = s2;
        String s4;
        if (this.tb != null) {
            s4 = this.tb._(s);
        }
        else {
            s4 = this.sb.getParameter(s);
        }
        try {
            if (s4 != null) {
                if (s4.equalsIgnoreCase(private.t)) {
                    s3 = private.u;
                }
                else if (s4.equalsIgnoreCase(private.v)) {
                    s3 = private.w;
                }
                else if (s4.equalsIgnoreCase(private.x)) {
                    s3 = private.y;
                }
                else {
                    if (!s4.equalsIgnoreCase(private.z)) {
                        throw new Exception();
                    }
                    s3 = private.A;
                }
            }
        }
        catch (Exception ex) {
            this.a(private.B + s4 + private.g);
        }
        return s3;
    }
    
    public void b(final Vector vector, final int n) {
        final MediaTracker mediaTracker = new MediaTracker(this.sb);
        for (int i = 0; i < vector.size(); ++i) {
            mediaTracker.addImage(vector.elementAt(i), n);
        }
        try {
            mediaTracker.waitForID(n);
        }
        catch (InterruptedException ex) {
            this.n(private.C);
        }
        if ((mediaTracker.statusID(n, false) & 0x6) != 0x0) {
            this.n(private.D);
        }
    }
    
    public Image[] _(final String s, final int n) {
        final Vector vector = new Vector<Image>();
        int n2 = 1;
        String _;
        while ((_ = this._(String.valueOf(s) + n2++, (String)null)) != null) {
            vector.addElement(this.sb.getImage(this.sb.getCodeBase(), _));
        }
        this.b(vector, n);
        final Image[] array = new Image[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public AudioClip b(String _) {
        if (!_.endsWith(private.E)) {
            _ = this._(_, (String)null);
        }
        if (_ != null) {
            final AudioClip audioClip = this.sb.getAudioClip(this.sb.getCodeBase(), _);
            b(audioClip);
            _(audioClip);
            return audioClip;
        }
        return null;
    }
    
    public boolean _() {
        try {
            return System.getProperty(private.F).startsWith(private.G);
        }
        catch (SecurityException ex) {
            return true;
        }
    }
    
    public int a(final String s, final boolean b, final Graphics graphics) {
        final Dimension size = this.sb.getSize();
        return b(s, b, new Rectangle(0, 0, size.width, size.height), graphics);
    }
    
    public void a(final Graphics graphics) {
        graphics.setColor(this.sb.getBackground());
        graphics.fillRect(0, 0, this.sb.getSize().width, this.sb.getSize().height);
    }
    
    public Class b(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            this.n(private.H + s);
            return null;
        }
    }
    
    public void a(final String s) {
        System.out.println(String.valueOf(this.sb.getClass().getName()) + private.I + s);
    }
    
    public boolean n(final String s) {
        return this._(s, (Exception)null);
    }
    
    public boolean _(final String s, final Exception ex) {
        this.a = true;
        if (this._ != null) {
            this.sb.showStatus(this._.b());
        }
        this.a(private.J + s);
        if (ex != null) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public void _(final boolean b) {
        this.b = b;
    }
    
    public boolean a() {
        return this.b;
    }
    
    public boolean g() {
        return this.a;
    }
    
    public static int b(final String s, final boolean b, final Rectangle rectangle, final Graphics graphics) {
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(graphics.getFont());
        if (b) {
            return rectangle.x + (rectangle.width - fontMetrics.stringWidth(s)) / 2;
        }
        return rectangle.y + rectangle.height % 2 + rectangle.height / 2 + (fontMetrics.getAscent() - fontMetrics.getDescent()) / 2;
    }
    
    public static int a(final String s, final boolean b, final Rectangle rectangle, final Font font) {
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
        if (b) {
            return rectangle.x + (rectangle.width - fontMetrics.stringWidth(s)) / 2;
        }
        return rectangle.y + rectangle.height % 2 + rectangle.height / 2 + (fontMetrics.getAscent() - fontMetrics.getDescent()) / 2;
    }
    
    private static String e(final String s) {
        if (s != null && !s.equals(private.K)) {
            return private.L + Calendar.getInstance().get(1) + private.M + s;
        }
        return private.N;
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
        if (substring.startsWith(private.O)) {
            substring = substring.substring(1);
        }
        if (substring.length() == 6) {
            return new Color(Integer.parseInt(substring, 16));
        }
        throw new IllegalArgumentException(String.valueOf(substring) + private.P);
    }
    
    public static Color b(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        if (stringTokenizer.countTokens() == 3) {
            return new Color(Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()));
        }
        throw new IllegalArgumentException(String.valueOf(s) + private.P);
    }
    
    public static Color a(final String s) {
        return b(s, private.Q);
    }
    
    public static void _(final int n, final int n2, final int n3, final int n4, final Graphics graphics) {
        graphics.drawLine(n, n2, n + n3, n2);
        graphics.drawLine(n + n3, n2, n + n3, n2 + n4);
        graphics.drawLine(n, n2, n, n2 + n4);
        graphics.drawLine(n, n2 + n4, n + n3, n2 + n4);
    }
    
    public static void _(final Rectangle rectangle, final Graphics graphics) {
        _(rectangle.x, rectangle.y, rectangle.width, rectangle.height, graphics);
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
    
    public static void _(final InputStream inputStream) {
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
    
    public static void b(final long n) {
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
        private.ib = a(private.ib);
        private.jb = a(private.jb);
        private.kb = a(private.kb);
        private.lb = a(private.lb);
        private.mb = a(private.mb);
        private.nb = a(private.nb);
        private.ob = a(private.ob);
        private.c = a(private.c);
        private.d = a(private.d);
        private.e = a(private.e);
        private.f = a(private.f);
        private.g = a(private.g);
        private.h = a(private.h);
        private.i = a(private.i);
        private.j = a(private.j);
        private.k = a(private.k);
        private.l = a(private.l);
        private.m = a(private.m);
        private.n = a(private.n);
        private.o = a(private.o);
        private.p = a(private.p);
        private.q = a(private.q);
        private.r = a(private.r);
        private.s = a(private.s);
        private.t = a(private.t);
        private.u = a(private.u);
        private.v = a(private.v);
        private.w = a(private.w);
        private.x = a(private.x);
        private.y = a(private.y);
        private.z = a(private.z);
        private.A = a(private.A);
        private.B = a(private.B);
        private.C = a(private.C);
        private.D = a(private.D);
        private.E = a(private.E);
        private.F = a(private.F);
        private.G = a(private.G);
        private.H = a(private.H);
        private.I = a(private.I);
        private.J = a(private.J);
        private.K = a(private.K);
        private.L = a(private.L);
        private.M = a(private.M);
        private.N = a(private.N);
        private.O = a(private.O);
        private.P = a(private.P);
        private.Q = a(private.Q);
        private.R = a(private.R);
        private.S = a(private.S);
        private.T = a(private.T);
        private.U = a(private.U);
        private.V = a(private.V);
        private.W = a(private.W);
        private.X = a(private.X);
        private.Y = a(private.Y);
        pb = e(private.R);
        rb = new String[] { private.S, private.T, private.U, private.V, private.W, private.X, private.Y };
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFEA266);
        }
        return new String(array);
    }
}
