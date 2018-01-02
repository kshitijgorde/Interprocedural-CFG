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

public class switch
{
    public static final String La;
    public static final long Ma = 86400000L;
    public static final String[] Na;
    private Applet Oa;
    private this Pa;
    private super Qa;
    private boolean Ra;
    private boolean Sa;
    private static String ya = "\u240b\u2410\u2409\u2409";
    private static String za = "\u2406\u240a\u240b\u2403\u240c\u2402";
    private static String Aa = "\u2417\u2400\u2404\u2401\u240c\u240b\u2402\u2445";
    private static String Ba = "\u2445\u240d\u2404\u2416\u2445\u2403\u2404\u240c\u2409\u2400\u2401";
    private static String Ca = "\u240d\u2411\u2411\u2415";
    private static String Da = "\u2406\u240a\u2415\u241c\u2417\u240c\u2402\u240d\u2411";
    private static String Ea = "\u2409\u240c\u2406\u2400\u240b\u2416\u2400\u2400";
    private static String Ta = "\u2417\u2400\u2406\u240a\u2408\u2408\u2400\u240b\u2401\u2400\u2401\u2445\u2416\u240c\u241f\u2400\u245f\u2445\u2412\u240c\u2401\u2411\u240d\u2458";
    private static String Ua = "\u2445\u240d\u2400\u240c\u2402\u240d\u2411\u2458";
    private static String Va = "\u2445\u2413\u2404\u2409\u2410\u2400\u2445\u240d\u2404\u2416\u2445\u2411\u240a\u2445\u2407\u2400\u2445\u2407\u2400\u2411\u2412\u2400\u2400\u240b\u2445";
    private static String Wa = "\u2445\u2404\u240b\u2401\u2445";
    private static String Xa = "\u245e\u2445\u2410\u2416\u240c\u240b\u2402\u2445\u2401\u2400\u2403\u2404\u2410\u2409\u2411";
    private static String Ya = "\u240c\u2409\u2409\u2400\u2402\u2404\u2409\u2445\u2413\u2404\u2409\u2410\u2400\u2445\u2403\u240a\u2417\u2445";
    private static String Za = "\u2445\u2415\u2404\u2417\u2404\u2408\u2400\u2411\u2400\u2417\u2445\u240c\u2416\u2445\u2408\u240c\u2416\u2416\u240c\u240b\u2402";
    private static String _b = "\u241c";
    private static String ab = "\u2411";
    private static String bb = "\u2454";
    private static String cb = "\u240a\u240b";
    private static String db = "\u244e";
    private static String eb = "\u2415\u2409\u2404\u240c\u240b";
    private static String fb = "\u2407\u240a\u2409\u2401";
    private static String gb = "\u240c\u2411\u2404\u2409\u240c\u2406";
    private static String hb = "\u2407\u240a\u2409\u2401\u240c\u2411\u2404\u2409\u240c\u2406";
    private static String ib = "\u240c\u2409\u2409\u2400\u2402\u2404\u2409\u2445\u2413\u2404\u2409\u2410\u2400\u2445\u2403\u240a\u2417\u2445\u2403\u240a\u240b\u2411\u2416\u2411\u241c\u2409\u2400\u245f\u2445";
    private static String jb = "\u2411\u240c\u2408\u2400\u2416\u2417\u240a\u2408\u2404\u240b";
    private static String kb = "\u2431\u240c\u2408\u2400\u2416\u2437\u240a\u2408\u2404\u240b";
    private static String lb = "\u2404\u2417\u240c\u2404\u2409";
    private static String mb = "\u2424\u2417\u240c\u2404\u2409";
    private static String nb = "\u2406\u240a\u2410\u2417\u240c\u2400\u2417";
    private static String ob = "\u2426\u240a\u2410\u2417\u240c\u2400\u2417";
    private static String pb = "\u2401\u240c\u2404\u2409\u240a\u2402";
    private static String qb = "\u2421\u240c\u2404\u2409\u240a\u2402";
    private static String rb = "\u240c\u2409\u2409\u2400\u2402\u2404\u2409\u2445\u2413\u2404\u2409\u2410\u2400\u2445\u2403\u240a\u2417\u2445\u2403\u240a\u240b\u2411\u2403\u2404\u2406\u2400\u245f\u2445";
    private static String sb = "\u240c\u2408\u2404\u2402\u2400\u2448\u2409\u240a\u2404\u2401\u240c\u240b\u2402\u2445\u240d\u2404\u2416\u2445\u2407\u2400\u2400\u240b\u2445\u240c\u240b\u2411\u2400\u2417\u2417\u2410\u2415\u2411\u2400\u2401";
    private static String tb = "\u240a\u240b\u2400\u2445\u240a\u2417\u2445\u2408\u240a\u2417\u2400\u2445\u240c\u2408\u2404\u2402\u2400\u2416\u2445\u2408\u2404\u241c\u2445\u240b\u240a\u2411\u2445\u240d\u2404\u2413\u2400\u2445\u2409\u240a\u2404\u2401\u2400\u2401\u2445\u2406\u240a\u2417\u2417\u2400\u2406\u2411\u2409\u241c";
    private static String _ = "\u244b\u2404\u2410";
    private static String a = "\u240a\u2416\u244b\u240b\u2404\u2408\u2400";
    private static String b = "\u2432\u240c\u240b";
    private static String c = "\u2406\u240a\u2410\u2409\u2401\u2445\u240b\u240a\u2411\u2445\u2409\u240a\u2404\u2401\u2445";
    private static String d = "\u245f\u2445";
    private static String e = "\u2403\u2404\u2411\u2404\u2409\u2445\u2400\u2417\u2417\u240a\u2417\u245f\u2445";
    private static String f = "";
    private static String g = "\u2431\u240d\u240c\u2416\u2445\u2404\u2415\u2415\u2409\u2400\u2411\u2445\u240c\u2416\u2445\u2406\u240a\u2415\u241c\u2417\u240c\u2402\u240d\u2411\u2400\u2401";
    private static String h = "\u2446";
    private static String i = "\u2445\u2406\u2404\u240b\u2442\u2411\u2445\u2407\u2400\u2445\u2415\u2404\u2417\u2416\u2400\u2401";
    private static String j = "\u2449";
    private static String k = "\u242f\u2404\u2413\u2404\u2409\u240a\u2413\u2400\u2417\u2416\u2445\u2432\u2400\u2407\u2401\u2400\u2416\u240c\u2402\u240b";
    private static String l = "\u240f\u2404\u2413\u2404\u2409\u240a\u2413\u2400\u2417\u2416\u244b\u2406\u240a\u2408";
    private static String m = "\u2403\u2412\u2400\u240b\u2401\u244b\u2406\u240a\u2408";
    private static String n = "\u2401\u2404\u240c\u2409\u241c\u2412\u240a\u2417\u2401\u2416\u2400\u2404\u2417\u2406\u240d\u244b\u2406\u240a\u2408";
    private static String o = "\u2410\u240b\u2417\u2400\u2404\u2409\u2406\u2404\u2416\u240c\u240b\u240a\u244b\u2406\u240a\u2408";
    private static String p = "\u2406\u2417\u241c\u2415\u2411\u240c\u2406\u2403\u240a\u2417\u2410\u2408\u244b\u2406\u240a\u2408";
    private static String q = "\u2406\u2409\u2404\u2416\u2416\u240c\u2406\u2408\u240a\u2413\u240c\u2400\u2414\u2410\u240c\u241f\u244b\u2406\u240a\u2408";
    private static String r = "\u2401\u2404\u240c\u2409\u241c\u2412\u240a\u2417\u2401\u2402\u2404\u2408\u2400\u244b\u2406\u240a\u2408";
    
    public switch(final Applet oa, final super qa) {
        if (oa == null) {
            throw new IllegalArgumentException(switch.ya);
        }
        this.Oa = oa;
        this.Qa = qa;
        final String b = this.b(switch.za, (String)null);
        if (b != null) {
            this.Pa = new this();
            if (!this.Pa._(this.Oa.getCodeBase(), b, false)) {
                this._(switch.Aa + b + switch.Ba);
                this.Pa = null;
            }
        }
    }
    
    public boolean b() {
        return this.Oa.getDocumentBase().getProtocol().startsWith(switch.Ca);
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
        return this.Oa.getDocumentBase().getHost().indexOf(s) != -1;
    }
    
    public boolean _(final long n, final long n2) {
        return new Date().getTime() > n + n2 * 86400000L;
    }
    
    public boolean b(final String s) {
        final String k = this.k(switch.Da);
        return k != null && k.equalsIgnoreCase(s);
    }
    
    public boolean g(final String s) {
        final String k = this.k(switch.Ea);
        return k != null && k.equalsIgnoreCase(s);
    }
    
    public void b(final int n, final int n2) {
        this._(switch.Ta + n + switch.Ua + n2);
    }
    
    public int b(final String s, final int n, final int n2, final int n3) {
        int int1 = n3;
        String s2;
        if (this.Pa != null) {
            s2 = this.Pa.b(s);
        }
        else {
            s2 = this.Oa.getParameter(s);
        }
        if (s2 != null) {
            try {
                int1 = Integer.parseInt(s2);
                if (!this.b(int1, n, n2)) {
                    int1 = n3;
                    this._(String.valueOf(s) + switch.Va + n + switch.Wa + n2 + switch.Xa);
                }
            }
            catch (NumberFormatException ex) {
                this._(switch.Ya + s + switch.Xa);
            }
        }
        return int1;
    }
    
    public int a(final String s, final int n, final int n2) {
        int int1 = 0;
        String s2;
        if (this.Pa != null) {
            s2 = this.Pa.b(s);
        }
        else {
            s2 = this.Oa.getParameter(s);
        }
        if (s2 != null) {
            try {
                int1 = Integer.parseInt(s2);
                if (!this.b(int1, n, n2)) {
                    this.h(String.valueOf(s) + switch.Va + n + switch.Wa + n2);
                }
            }
            catch (NumberFormatException ex) {
                this.h(switch.Ya + s);
            }
        }
        else {
            this.h(String.valueOf(s) + switch.Za);
        }
        return int1;
    }
    
    public int b(final String s) {
        return this.a(s, 0, 0);
    }
    
    public int _(final String s, final int n) {
        return this.b(s, 0, 0, n);
    }
    
    public boolean b(final int n, final int n2, final int n3) {
        return n2 == n3 || (n >= n2 && n <= n3);
    }
    
    public String k(final String s) {
        String s2;
        if (this.Pa != null) {
            s2 = this.Pa.b(s);
        }
        else {
            s2 = this.Oa.getParameter(s);
        }
        if (s2 == null) {
            this.h(String.valueOf(s) + switch.Za);
        }
        return s2;
    }
    
    public String b(final String s, final String s2) {
        String s3;
        if (this.Pa != null) {
            s3 = this.Pa.b(s);
        }
        else {
            s3 = this.Oa.getParameter(s);
        }
        if (s3 == null) {
            s3 = s2;
        }
        return s3;
    }
    
    public Color b(final String s, final Color color) {
        Color color2 = color;
        String s2;
        if (this.Pa != null) {
            s2 = this.Pa.b(s);
        }
        else {
            s2 = this.Oa.getParameter(s);
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
                    this._(ex.getMessage());
                    this._(switch.Ya + s + switch.Xa);
                }
            }
        }
        return color2;
    }
    
    public Color _(final String s) {
        Color color = null;
        String s2;
        if (this.Pa != null) {
            s2 = this.Pa.b(s);
        }
        else {
            s2 = this.Oa.getParameter(s);
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
                    this._(ex.getMessage());
                    this.h(switch.Ya + s);
                }
            }
        }
        this.h(String.valueOf(s) + switch.Za);
        return color;
    }
    
    public boolean _(final String s, final boolean b) {
        boolean b2 = b;
        String s2;
        if (this.Pa != null) {
            s2 = this.Pa.b(s);
        }
        else {
            s2 = this.Oa.getParameter(s);
        }
        if (s2 != null) {
            final String lowerCase = s2.toLowerCase();
            b2 = (lowerCase.startsWith(switch._b) || lowerCase.startsWith(switch.ab) || lowerCase.equals(switch.bb) || lowerCase.equals(switch.cb) || lowerCase.equals(switch.db));
        }
        return b2;
    }
    
    public int a(final String s, final int n) {
        int n2 = n;
        String s2 = null;
        try {
            if (this.Pa != null) {
                s2 = this.Pa.b(s);
            }
            else {
                s2 = this.Oa.getParameter(s);
            }
            if (s2 != null) {
                if (s2.equalsIgnoreCase(switch.eb)) {
                    n2 = 0;
                }
                else if (s2.equalsIgnoreCase(switch.fb)) {
                    n2 = 1;
                }
                else if (s2.equalsIgnoreCase(switch.gb)) {
                    n2 = 2;
                }
                else {
                    if (!s2.equalsIgnoreCase(switch.hb)) {
                        throw new Exception();
                    }
                    n2 = 3;
                }
            }
        }
        catch (Exception ex) {
            this._(switch.ib + s2 + switch.Xa);
        }
        return n2;
    }
    
    public String _(final String s, final String s2) {
        String s3 = s2;
        String s4;
        if (this.Pa != null) {
            s4 = this.Pa.b(s);
        }
        else {
            s4 = this.Oa.getParameter(s);
        }
        try {
            if (s4 != null) {
                if (s4.equalsIgnoreCase(switch.jb)) {
                    s3 = switch.kb;
                }
                else if (s4.equalsIgnoreCase(switch.lb)) {
                    s3 = switch.mb;
                }
                else if (s4.equalsIgnoreCase(switch.nb)) {
                    s3 = switch.ob;
                }
                else {
                    if (!s4.equalsIgnoreCase(switch.pb)) {
                        throw new Exception();
                    }
                    s3 = switch.qb;
                }
            }
        }
        catch (Exception ex) {
            this._(switch.rb + s4 + switch.Xa);
        }
        return s3;
    }
    
    public void b(final Vector vector, final int n) {
        final MediaTracker mediaTracker = new MediaTracker(this.Oa);
        for (int i = 0; i < vector.size(); ++i) {
            mediaTracker.addImage(vector.elementAt(i), n);
        }
        try {
            mediaTracker.waitForID(n);
        }
        catch (InterruptedException ex) {
            this.h(switch.sb);
        }
        if ((mediaTracker.statusID(n, false) & 0x6) != 0x0) {
            this.h(switch.tb);
        }
    }
    
    public Image[] b(final String s, final int n) {
        final Vector vector = new Vector<Image>();
        int n2 = 1;
        String b;
        while ((b = this.b(String.valueOf(s) + n2++, (String)null)) != null) {
            vector.addElement(this.Oa.getImage(this.Oa.getCodeBase(), b));
        }
        this.b(vector, n);
        final Image[] array = new Image[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public AudioClip _(String b) {
        if (!b.endsWith(switch._)) {
            b = this.b(b, (String)null);
        }
        if (b != null) {
            final AudioClip audioClip = this.Oa.getAudioClip(this.Oa.getCodeBase(), b);
            a(audioClip);
            b(audioClip);
            return audioClip;
        }
        return null;
    }
    
    public boolean _() {
        try {
            return System.getProperty(switch.a).startsWith(switch.b);
        }
        catch (SecurityException ex) {
            return true;
        }
    }
    
    public int b(final String s, final boolean b, final Graphics graphics) {
        final Dimension size = this.Oa.getSize();
        return b(s, b, new Rectangle(0, 0, size.width, size.height), graphics);
    }
    
    public void _(final Graphics graphics) {
        graphics.setColor(this.Oa.getBackground());
        graphics.fillRect(0, 0, this.Oa.getSize().width, this.Oa.getSize().height);
    }
    
    public Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            this.h(switch.c + s);
            return null;
        }
    }
    
    public void _(final String s) {
        System.out.println(String.valueOf(this.Oa.getClass().getName()) + switch.d + s);
    }
    
    public boolean h(final String s) {
        return this.a(s, null);
    }
    
    public boolean a(final String s, final Exception ex) {
        this.Ra = true;
        if (this.Qa != null) {
            this.Oa.showStatus(this.Qa._());
        }
        this._(switch.e + s);
        if (ex != null) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public void a(final boolean sa) {
        this.Sa = sa;
    }
    
    public boolean a() {
        return this.Sa;
    }
    
    public boolean g() {
        return this.Ra;
    }
    
    public static int b(final String s, final boolean b, final Rectangle rectangle, final Graphics graphics) {
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(graphics.getFont());
        if (b) {
            return rectangle.x + (rectangle.width - fontMetrics.stringWidth(s)) / 2;
        }
        return rectangle.y + rectangle.height % 2 + rectangle.height / 2 + (fontMetrics.getAscent() - fontMetrics.getDescent()) / 2;
    }
    
    private static String l(final String s) {
        if (s == null || s.equals(switch.f)) {}
        return switch.g;
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
        if (substring.startsWith(switch.h)) {
            substring = substring.substring(1);
        }
        if (substring.length() == 6) {
            return new Color(Integer.parseInt(substring, 16));
        }
        throw new IllegalArgumentException(String.valueOf(substring) + switch.i);
    }
    
    public static Color b(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        if (stringTokenizer.countTokens() == 3) {
            return new Color(Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()));
        }
        throw new IllegalArgumentException(String.valueOf(s) + switch.i);
    }
    
    public static Color b(final String s) {
        return b(s, switch.j);
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
    
    public static void a(final Rectangle rectangle, final Color color, final Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public static void b(final int n, final int n2, final int n3, final int n4, final Color color, final Graphics graphics) {
        a(new Rectangle(n, n2, n3, n4), color, graphics);
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
    
    public static void _(final Writer writer) {
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
        switch.ya = _(switch.ya);
        switch.za = _(switch.za);
        switch.Aa = _(switch.Aa);
        switch.Ba = _(switch.Ba);
        switch.Ca = _(switch.Ca);
        switch.Da = _(switch.Da);
        switch.Ea = _(switch.Ea);
        switch.Ta = _(switch.Ta);
        switch.Ua = _(switch.Ua);
        switch.Va = _(switch.Va);
        switch.Wa = _(switch.Wa);
        switch.Xa = _(switch.Xa);
        switch.Ya = _(switch.Ya);
        switch.Za = _(switch.Za);
        switch._b = _(switch._b);
        switch.ab = _(switch.ab);
        switch.bb = _(switch.bb);
        switch.cb = _(switch.cb);
        switch.db = _(switch.db);
        switch.eb = _(switch.eb);
        switch.fb = _(switch.fb);
        switch.gb = _(switch.gb);
        switch.hb = _(switch.hb);
        switch.ib = _(switch.ib);
        switch.jb = _(switch.jb);
        switch.kb = _(switch.kb);
        switch.lb = _(switch.lb);
        switch.mb = _(switch.mb);
        switch.nb = _(switch.nb);
        switch.ob = _(switch.ob);
        switch.pb = _(switch.pb);
        switch.qb = _(switch.qb);
        switch.rb = _(switch.rb);
        switch.sb = _(switch.sb);
        switch.tb = _(switch.tb);
        switch._ = _(switch._);
        switch.a = _(switch.a);
        switch.b = _(switch.b);
        switch.c = _(switch.c);
        switch.d = _(switch.d);
        switch.e = _(switch.e);
        switch.f = _(switch.f);
        switch.g = _(switch.g);
        switch.h = _(switch.h);
        switch.i = _(switch.i);
        switch.j = _(switch.j);
        switch.k = _(switch.k);
        switch.l = _(switch.l);
        switch.m = _(switch.m);
        switch.n = _(switch.n);
        switch.o = _(switch.o);
        switch.p = _(switch.p);
        switch.q = _(switch.q);
        switch.r = _(switch.r);
        La = l(switch.k);
        Na = new String[] { switch.l, switch.m, switch.n, switch.o, switch.p, switch.q, switch.r };
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u2465');
        }
        return new String(array);
    }
}
