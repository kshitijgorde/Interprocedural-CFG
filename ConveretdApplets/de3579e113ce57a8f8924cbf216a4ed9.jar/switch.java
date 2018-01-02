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

public class switch
{
    public static final String Foa;
    public static final long Goa = 86400000L;
    public static final String[] Hoa;
    private Applet Ioa;
    private throw Joa;
    private synchronized sa;
    private boolean Koa;
    private boolean Loa;
    private static String o = "\udd47\udd5c\udd45\udd45";
    private static String p = "\udd4a\udd46\udd47\udd4f\udd40\udd4e";
    private static String q = "\udd5b\udd4c\udd48\udd4d\udd40\udd47\udd4e\udd09";
    private static String C = "\udd09\udd41\udd48\udd5a\udd09\udd4f\udd48\udd40\udd45\udd4c\udd4d";
    private static String D = "\udd41\udd5d\udd5d\udd59";
    private static String E = "\udd4a\udd46\udd59\udd50\udd5b\udd40\udd4e\udd41\udd5d";
    private static String F = "\udd45\udd40\udd4a\udd4c\udd47\udd5a\udd4c\udd4c";
    private static String ta = "\udd5b\udd4c\udd4a\udd46\udd44\udd44\udd4c\udd47\udd4d\udd4c\udd4d\udd09\udd5a\udd40\udd53\udd4c\udd13\udd09\udd5e\udd40\udd4d\udd5d\udd41\udd14";
    private static String ua = "\udd09\udd41\udd4c\udd40\udd4e\udd41\udd5d\udd14";
    private static String wa = "\udd09\udd5f\udd48\udd45\udd5c\udd4c\udd09\udd41\udd48\udd5a\udd09\udd5d\udd46\udd09\udd4b\udd4c\udd09\udd4b\udd4c\udd5d\udd5e\udd4c\udd4c\udd47\udd09";
    private static String xa = "\udd09\udd48\udd47\udd4d\udd09";
    private static String Eoa = "\udd12\udd09\udd5c\udd5a\udd40\udd47\udd4e\udd09\udd4d\udd4c\udd4f\udd48\udd5c\udd45\udd5d";
    private static String Moa = "\udd40\udd45\udd45\udd4c\udd4e\udd48\udd45\udd09\udd5f\udd48\udd45\udd5c\udd4c\udd09\udd4f\udd46\udd5b\udd09";
    private static String Noa = "\udd09\udd59\udd48\udd5b\udd48\udd44\udd4c\udd5d\udd4c\udd5b\udd09\udd40\udd5a\udd09\udd44\udd40\udd5a\udd5a\udd40\udd47\udd4e";
    private static String Ooa = "\udd50";
    private static String Poa = "\udd5d";
    private static String Qoa = "\udd18";
    private static String Roa = "\udd46\udd47";
    private static String Soa = "\udd02";
    private static String Toa = "\udd59\udd45\udd48\udd40\udd47";
    private static String Uoa = "\udd4b\udd46\udd45\udd4d";
    private static String Voa = "\udd40\udd5d\udd48\udd45\udd40\udd4a";
    private static String Woa = "\udd4b\udd46\udd45\udd4d\udd40\udd5d\udd48\udd45\udd40\udd4a";
    private static String Xoa = "\udd40\udd45\udd45\udd4c\udd4e\udd48\udd45\udd09\udd5f\udd48\udd45\udd5c\udd4c\udd09\udd4f\udd46\udd5b\udd09\udd4f\udd46\udd47\udd5d\udd5a\udd5d\udd50\udd45\udd4c\udd13\udd09";
    private static String Yoa = "\udd5d\udd40\udd44\udd4c\udd5a\udd5b\udd46\udd44\udd48\udd47";
    private static String Zoa = "\udd7d\udd40\udd44\udd4c\udd5a\udd7b\udd46\udd44\udd48\udd47";
    private static String _pa = "\udd48\udd5b\udd40\udd48\udd45";
    private static String apa = "\udd68\udd5b\udd40\udd48\udd45";
    private static String bpa = "\udd4a\udd46\udd5c\udd5b\udd40\udd4c\udd5b";
    private static String cpa = "\udd6a\udd46\udd5c\udd5b\udd40\udd4c\udd5b";
    private static String dpa = "\udd4d\udd40\udd48\udd45\udd46\udd4e";
    private static String epa = "\udd6d\udd40\udd48\udd45\udd46\udd4e";
    private static String fpa = "\udd40\udd45\udd45\udd4c\udd4e\udd48\udd45\udd09\udd5f\udd48\udd45\udd5c\udd4c\udd09\udd4f\udd46\udd5b\udd09\udd4f\udd46\udd47\udd5d\udd4f\udd48\udd4a\udd4c\udd13\udd09";
    private static String gpa = "\udd40\udd44\udd48\udd4e\udd4c\udd04\udd45\udd46\udd48\udd4d\udd40\udd47\udd4e\udd09\udd41\udd48\udd5a\udd09\udd4b\udd4c\udd4c\udd47\udd09\udd40\udd47\udd5d\udd4c\udd5b\udd5b\udd5c\udd59\udd5d\udd4c\udd4d";
    private static String hpa = "\udd46\udd47\udd4c\udd09\udd46\udd5b\udd09\udd44\udd46\udd5b\udd4c\udd09\udd40\udd44\udd48\udd4e\udd4c\udd5a\udd09\udd44\udd48\udd50\udd09\udd47\udd46\udd5d\udd09\udd41\udd48\udd5f\udd4c\udd09\udd45\udd46\udd48\udd4d\udd4c\udd4d\udd09\udd4a\udd46\udd5b\udd5b\udd4c\udd4a\udd5d\udd45\udd50";
    private static String ipa = "\udd07\udd48\udd5c";
    private static String jpa = "\udd46\udd5a\udd07\udd47\udd48\udd44\udd4c";
    private static String kpa = "\udd7e\udd40\udd47";
    private static String lpa = "\udd4a\udd46\udd5c\udd45\udd4d\udd09\udd47\udd46\udd5d\udd09\udd45\udd46\udd48\udd4d\udd09";
    private static String mpa = "\udd13\udd09";
    private static String npa = "\udd4f\udd48\udd5d\udd48\udd45\udd09\udd4c\udd5b\udd5b\udd46\udd5b\udd13\udd09";
    private static String opa = "";
    private static String ppa = "\udd6a\udd46\udd59\udd50\udd5b\udd40\udd4e\udd41\udd5d\udd09\udd01\udd4a\udd00\udd09\udd09";
    private static String qpa = "\udd05\udd09";
    private static String rpa = "\udd7d\udd41\udd40\udd5a\udd09\udd48\udd59\udd59\udd45\udd4c\udd5d\udd09\udd40\udd5a\udd09\udd4a\udd46\udd59\udd50\udd5b\udd40\udd4e\udd41\udd5d\udd4c\udd4d";
    private static String spa = "\udd0a";
    private static String tpa = "\udd09\udd4a\udd48\udd47\udd0e\udd5d\udd09\udd4b\udd4c\udd09\udd59\udd48\udd5b\udd5a\udd4c\udd4d";
    private static String upa = "\udd05";
    private static String vpa = "\udd63\udd48\udd5f\udd48\udd45\udd46\udd5f\udd4c\udd5b\udd5a\udd09\udd7e\udd4c\udd4b\udd4d\udd4c\udd5a\udd40\udd4e\udd47";
    private static String wpa = "\udd43\udd48\udd5f\udd48\udd45\udd46\udd5f\udd4c\udd5b\udd5a\udd07\udd4a\udd46\udd44";
    private static String xpa = "\udd4f\udd5e\udd4c\udd47\udd4d\udd07\udd4a\udd46\udd44";
    private static String ypa = "\udd4d\udd48\udd40\udd45\udd50\udd5e\udd46\udd5b\udd4d\udd5a\udd4c\udd48\udd5b\udd4a\udd41\udd07\udd4a\udd46\udd44";
    private static String zpa = "\udd5c\udd47\udd5b\udd4c\udd48\udd45\udd4a\udd48\udd5a\udd40\udd47\udd46\udd07\udd4a\udd46\udd44";
    private static String Apa = "\udd4a\udd5b\udd50\udd59\udd5d\udd40\udd4a\udd4f\udd46\udd5b\udd5c\udd44\udd07\udd4a\udd46\udd44";
    private static String Bpa = "\udd4a\udd45\udd48\udd5a\udd5a\udd40\udd4a\udd44\udd46\udd5f\udd40\udd4c\udd58\udd5c\udd40\udd53\udd07\udd4a\udd46\udd44";
    private static String Cpa = "\udd4d\udd48\udd40\udd45\udd50\udd5e\udd46\udd5b\udd4d\udd4e\udd48\udd44\udd4c\udd07\udd4a\udd46\udd44";
    
    public switch(final Applet ioa, final synchronized sa) {
        if (ioa == null) {
            throw new IllegalArgumentException(switch.o);
        }
        this.Ioa = ioa;
        this.sa = sa;
        final String a = this.a(switch.p, (String)null);
        if (a != null) {
            this.Joa = new throw();
            if (!this.Joa.b(this.Ioa.getCodeBase(), a, false)) {
                this._(switch.q + a + switch.C);
                this.Joa = null;
            }
        }
    }
    
    public boolean h() {
        return this.Ioa.getDocumentBase().getProtocol().startsWith(switch.D);
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
        return this.Ioa.getDocumentBase().getHost().indexOf(s) != -1;
    }
    
    public boolean a(final long n, final long n2) {
        return new Date().getTime() > n + n2 * 86400000L;
    }
    
    public boolean _(final String s) {
        final String l = this.l(switch.E);
        return l != null && l.equalsIgnoreCase(s);
    }
    
    public boolean m(final String s) {
        final String l = this.l(switch.F);
        return l != null && l.equalsIgnoreCase(s);
    }
    
    public void h(final int n, final int n2) {
        this._(switch.ta + n + switch.ua + n2);
    }
    
    public int b(final String s, final int n, final int n2, final int n3) {
        int int1 = n3;
        String s2;
        if (this.Joa != null) {
            s2 = this.Joa.b(s);
        }
        else {
            s2 = this.Ioa.getParameter(s);
        }
        if (s2 != null) {
            try {
                int1 = Integer.parseInt(s2);
                if (!this.b(int1, n, n2)) {
                    int1 = n3;
                    this._(String.valueOf(s) + switch.wa + n + switch.xa + n2 + switch.Eoa);
                }
            }
            catch (NumberFormatException ex) {
                this._(switch.Moa + s + switch.Eoa);
            }
        }
        return int1;
    }
    
    public int _(final String s, final int n, final int n2) {
        int int1 = 0;
        String s2;
        if (this.Joa != null) {
            s2 = this.Joa.b(s);
        }
        else {
            s2 = this.Ioa.getParameter(s);
        }
        if (s2 != null) {
            try {
                int1 = Integer.parseInt(s2);
                if (!this.b(int1, n, n2)) {
                    this.n(String.valueOf(s) + switch.wa + n + switch.xa + n2);
                }
            }
            catch (NumberFormatException ex) {
                this.n(switch.Moa + s);
            }
        }
        else {
            this.n(String.valueOf(s) + switch.Noa);
        }
        return int1;
    }
    
    public int b(final String s) {
        return this._(s, 0, 0);
    }
    
    public int b(final String s, final int n) {
        return this.b(s, 0, 0, n);
    }
    
    public boolean b(final int n, final int n2, final int n3) {
        return n2 == n3 || (n >= n2 && n <= n3);
    }
    
    public String l(final String s) {
        String s2;
        if (this.Joa != null) {
            s2 = this.Joa.b(s);
        }
        else {
            s2 = this.Ioa.getParameter(s);
        }
        if (s2 == null) {
            this.n(String.valueOf(s) + switch.Noa);
        }
        return s2;
    }
    
    public String a(final String s, final String s2) {
        String s3;
        if (this.Joa != null) {
            s3 = this.Joa.b(s);
        }
        else {
            s3 = this.Ioa.getParameter(s);
        }
        if (s3 == null) {
            s3 = s2;
        }
        return s3;
    }
    
    public Color a(final String s, final Color color) {
        Color color2 = color;
        String s2;
        if (this.Joa != null) {
            s2 = this.Joa.b(s);
        }
        else {
            s2 = this.Ioa.getParameter(s);
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
                    this._(ex.getMessage());
                    this._(switch.Moa + s + switch.Eoa);
                }
            }
        }
        return color2;
    }
    
    public Color b(final String s) {
        Color color = null;
        String s2;
        if (this.Joa != null) {
            s2 = this.Joa.b(s);
        }
        else {
            s2 = this.Ioa.getParameter(s);
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
                    this._(ex.getMessage());
                    this.n(switch.Moa + s);
                }
            }
        }
        this.n(String.valueOf(s) + switch.Noa);
        return color;
    }
    
    public boolean b(final String s, final boolean b) {
        boolean b2 = b;
        String s2;
        if (this.Joa != null) {
            s2 = this.Joa.b(s);
        }
        else {
            s2 = this.Ioa.getParameter(s);
        }
        if (s2 != null) {
            final String lowerCase = s2.toLowerCase();
            b2 = (lowerCase.startsWith(switch.Ooa) || lowerCase.startsWith(switch.Poa) || lowerCase.equals(switch.Qoa) || lowerCase.equals(switch.Roa) || lowerCase.equals(switch.Soa));
        }
        return b2;
    }
    
    public int _(final String s, final int n) {
        int n2 = n;
        String s2 = null;
        try {
            if (this.Joa != null) {
                s2 = this.Joa.b(s);
            }
            else {
                s2 = this.Ioa.getParameter(s);
            }
            if (s2 != null) {
                if (s2.equalsIgnoreCase(switch.Toa)) {
                    n2 = 0;
                }
                else if (s2.equalsIgnoreCase(switch.Uoa)) {
                    n2 = 1;
                }
                else if (s2.equalsIgnoreCase(switch.Voa)) {
                    n2 = 2;
                }
                else {
                    if (!s2.equalsIgnoreCase(switch.Woa)) {
                        throw new Exception();
                    }
                    n2 = 3;
                }
            }
        }
        catch (Exception ex) {
            this._(switch.Xoa + s2 + switch.Eoa);
        }
        return n2;
    }
    
    public String b(final String s, final String s2) {
        String s3 = s2;
        String s4;
        if (this.Joa != null) {
            s4 = this.Joa.b(s);
        }
        else {
            s4 = this.Ioa.getParameter(s);
        }
        try {
            if (s4 != null) {
                if (s4.equalsIgnoreCase(switch.Yoa)) {
                    s3 = switch.Zoa;
                }
                else if (s4.equalsIgnoreCase(switch._pa)) {
                    s3 = switch.apa;
                }
                else if (s4.equalsIgnoreCase(switch.bpa)) {
                    s3 = switch.cpa;
                }
                else {
                    if (!s4.equalsIgnoreCase(switch.dpa)) {
                        throw new Exception();
                    }
                    s3 = switch.epa;
                }
            }
        }
        catch (Exception ex) {
            this._(switch.fpa + s4 + switch.Eoa);
        }
        return s3;
    }
    
    public void a(final Vector vector, final int n) {
        final MediaTracker mediaTracker = new MediaTracker(this.Ioa);
        for (int i = 0; i < vector.size(); ++i) {
            mediaTracker.addImage(vector.elementAt(i), n);
        }
        try {
            mediaTracker.waitForID(n);
        }
        catch (InterruptedException ex) {
            this.n(switch.gpa);
        }
        if ((mediaTracker.statusID(n, false) & 0x6) != 0x0) {
            this.n(switch.hpa);
        }
    }
    
    public Image[] b(final String s, final int n) {
        final Vector vector = new Vector<Image>();
        int n2 = 1;
        String a;
        while ((a = this.a(String.valueOf(s) + n2++, (String)null)) != null) {
            vector.addElement(this.Ioa.getImage(this.Ioa.getCodeBase(), a));
        }
        this.a(vector, n);
        final Image[] array = new Image[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public AudioClip _(String a) {
        if (!a.endsWith(switch.ipa)) {
            a = this.a(a, (String)null);
        }
        if (a != null) {
            final AudioClip audioClip = this.Ioa.getAudioClip(this.Ioa.getCodeBase(), a);
            a(audioClip);
            b(audioClip);
            return audioClip;
        }
        return null;
    }
    
    public boolean i() {
        try {
            return System.getProperty(switch.jpa).startsWith(switch.kpa);
        }
        catch (SecurityException ex) {
            return true;
        }
    }
    
    public int _(final String s, final boolean b, final Graphics graphics) {
        final Dimension size = this.Ioa.getSize();
        return a(s, b, new Rectangle(0, 0, size.width, size.height), graphics);
    }
    
    public void b(final Graphics graphics) {
        graphics.setColor(this.Ioa.getBackground());
        graphics.fillRect(0, 0, this.Ioa.getSize().width, this.Ioa.getSize().height);
    }
    
    public Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            this.n(switch.lpa + s);
            return null;
        }
    }
    
    public void _(final String s) {
        System.out.println(String.valueOf(this.Ioa.getClass().getName()) + switch.mpa + s);
    }
    
    public boolean n(final String s) {
        return this.a(s, (Exception)null);
    }
    
    public boolean a(final String s, final Exception ex) {
        this.Koa = true;
        if (this.sa != null) {
            this.Ioa.showStatus(this.sa._());
        }
        this._(switch.npa + s);
        if (ex != null) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public void a(final boolean loa) {
        this.Loa = loa;
    }
    
    public boolean j() {
        return this.Loa;
    }
    
    public boolean k() {
        return this.Koa;
    }
    
    public static int a(final String s, final boolean b, final Rectangle rectangle, final Graphics graphics) {
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(graphics.getFont());
        if (b) {
            return rectangle.x + (rectangle.width - fontMetrics.stringWidth(s)) / 2;
        }
        return rectangle.y + rectangle.height % 2 + rectangle.height / 2 + (fontMetrics.getAscent() - fontMetrics.getDescent()) / 2;
    }
    
    private static String m(final String s) {
        if (s != null && !s.equals(switch.opa)) {
            return switch.ppa + Calendar.getInstance().get(1) + switch.qpa + s;
        }
        return switch.rpa;
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
    
    public static Color _(String substring) {
        if (substring.startsWith(switch.spa)) {
            substring = substring.substring(1);
        }
        if (substring.length() == 6) {
            return new Color(Integer.parseInt(substring, 16));
        }
        throw new IllegalArgumentException(String.valueOf(substring) + switch.tpa);
    }
    
    public static Color _(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        if (stringTokenizer.countTokens() == 3) {
            return new Color(Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()));
        }
        throw new IllegalArgumentException(String.valueOf(s) + switch.tpa);
    }
    
    public static Color a(final String s) {
        return _(s, switch.upa);
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
        switch.o = _(switch.o);
        switch.p = _(switch.p);
        switch.q = _(switch.q);
        switch.C = _(switch.C);
        switch.D = _(switch.D);
        switch.E = _(switch.E);
        switch.F = _(switch.F);
        switch.ta = _(switch.ta);
        switch.ua = _(switch.ua);
        switch.wa = _(switch.wa);
        switch.xa = _(switch.xa);
        switch.Eoa = _(switch.Eoa);
        switch.Moa = _(switch.Moa);
        switch.Noa = _(switch.Noa);
        switch.Ooa = _(switch.Ooa);
        switch.Poa = _(switch.Poa);
        switch.Qoa = _(switch.Qoa);
        switch.Roa = _(switch.Roa);
        switch.Soa = _(switch.Soa);
        switch.Toa = _(switch.Toa);
        switch.Uoa = _(switch.Uoa);
        switch.Voa = _(switch.Voa);
        switch.Woa = _(switch.Woa);
        switch.Xoa = _(switch.Xoa);
        switch.Yoa = _(switch.Yoa);
        switch.Zoa = _(switch.Zoa);
        switch._pa = _(switch._pa);
        switch.apa = _(switch.apa);
        switch.bpa = _(switch.bpa);
        switch.cpa = _(switch.cpa);
        switch.dpa = _(switch.dpa);
        switch.epa = _(switch.epa);
        switch.fpa = _(switch.fpa);
        switch.gpa = _(switch.gpa);
        switch.hpa = _(switch.hpa);
        switch.ipa = _(switch.ipa);
        switch.jpa = _(switch.jpa);
        switch.kpa = _(switch.kpa);
        switch.lpa = _(switch.lpa);
        switch.mpa = _(switch.mpa);
        switch.npa = _(switch.npa);
        switch.opa = _(switch.opa);
        switch.ppa = _(switch.ppa);
        switch.qpa = _(switch.qpa);
        switch.rpa = _(switch.rpa);
        switch.spa = _(switch.spa);
        switch.tpa = _(switch.tpa);
        switch.upa = _(switch.upa);
        switch.vpa = _(switch.vpa);
        switch.wpa = _(switch.wpa);
        switch.xpa = _(switch.xpa);
        switch.ypa = _(switch.ypa);
        switch.zpa = _(switch.zpa);
        switch.Apa = _(switch.Apa);
        switch.Bpa = _(switch.Bpa);
        switch.Cpa = _(switch.Cpa);
        Foa = m(switch.vpa);
        Hoa = new String[] { switch.wpa, switch.xpa, switch.ypa, switch.zpa, switch.Apa, switch.Bpa, switch.Cpa };
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFFDD29);
        }
        return new String(array);
    }
}
