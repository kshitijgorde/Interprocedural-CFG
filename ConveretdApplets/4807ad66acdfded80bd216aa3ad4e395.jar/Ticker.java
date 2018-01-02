import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.Component;
import java.awt.event.MouseListener;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Ticker extends Applet
{
    public static final byte E = 0;
    public static final byte F = 1;
    public static final byte G = 2;
    public static final byte H = 2;
    private else n;
    private Insets I;
    private static String ja = "\uc384\uc389\uc394\uc382\uc383\uc394\uc3b1\uc38f\uc382\uc392\uc38e";
    private static String na = "\uc384\uc389\uc394\uc382\uc383\uc394\uc3a5\uc389\uc38a\uc389\uc394";
    private static String oa = "\uc392\uc38f\uc385\uc38d\uc383\uc394\uc3b2\uc39f\uc396\uc383";
    private static String pa = "\uc3b7\uc393\uc389\uc392\uc383\uc395";
    private static String sa = "\uc3a8\uc3a3\uc3b1\uc3b5";
    private static String ta = "\uc392\uc38f\uc392\uc38a\uc383\uc3a0\uc381\uc3a5\uc389\uc38a\uc389\uc394";
    private static String ua = "\uc392\uc38f\uc392\uc38a\uc383\uc3a4\uc381\uc3a5\uc389\uc38a\uc389\uc394";
    private static String va = "\uc392\uc38f\uc392\uc38a\uc383\uc3a0\uc389\uc388\uc392";
    private static String wa = "\uc3a7\uc394\uc38f\uc387\uc38a";
    private static String rb = "\uc392\uc38f\uc392\uc38a\uc383";
    private static String sb = "\uc392\uc38f\uc392\uc38a\uc383\uc3b1\uc38f\uc382\uc392\uc38e";
    private static String tb = "\uc38e\uc392\uc392\uc396\uc3dc\uc3c9\uc3c9\uc391\uc391\uc391\uc3c8\uc38c\uc387\uc390\uc387\uc3cb\uc392\uc38f\uc385\uc38d\uc383\uc394\uc3c8\uc388\uc383\uc392";
    private static String _ = "\uc3b1\uc383\uc395\uc392";
    private static String a = "\uc3a5\uc383\uc388\uc392\uc383\uc394";
    private static String b = "\uc395\uc38e\uc389\uc391\uc3a5\uc38e\uc387\uc388\uc381\uc383\uc3a2\uc38f\uc394\uc383\uc385\uc392\uc38f\uc389\uc388\uc3a4\uc393\uc392\uc392\uc389\uc388";
    private static String c = "\uc3d8\uc3d8";
    private static String d = "\uc392\uc38f\uc385\uc38d\uc383\uc394\uc3a2\uc38f\uc394\uc383\uc385\uc392\uc38f\uc389\uc388";
    private static String e = "\uc38a\uc394";
    private static String f = "\uc3da\uc3da";
    private static String g = "\uc382\uc38f\uc394\uc383\uc385\uc392\uc38f\uc389\uc388";
    private static String h = "\uc3a3\uc387\uc395\uc392";
    
    public Ticker() {
        this.I = new Insets(0, 0, 0, 0);
    }
    
    public void init() {
        this.setLayout(new BorderLayout());
        final int a = extends.a(this, Ticker.ja, 0);
        if (a > 0) {
            this.setBackground(extends.b(this, Ticker.na, 11184640));
            this.I = new Insets(a, a, a, a);
        }
        if (extends._(this, Ticker.oa, Ticker.pa).equalsIgnoreCase(Ticker.sa)) {
            this.n = new final(this);
        }
        else {
            this.n = new finally(this);
        }
        final Color b = extends.b(this, Ticker.ta, 16777215);
        final Color b2 = extends.b(this, Ticker.ua, 1193046);
        final Font b3 = extends.b(this, Ticker.va, Ticker.wa, 1, 12);
        final String parameter = this.getParameter(Ticker.rb);
        if (parameter != null) {
            final String trim = parameter.trim();
            if (trim.length() > 0) {
                final for for1 = new for(this, trim, 2, extends.a(this, Ticker.sb, -1));
                for1.setBackground(b2);
                for1.setForeground(b);
                for1.setFont(b3);
                final String tb = Ticker.tb;
                if (tb != null) {
                    for1.addMouseListener(new goto(this, extends._(this, tb, false)));
                }
                for1.setBounds(0, 0, 100, 10);
                this.add(for1, Ticker._);
            }
        }
        this.add(this.n.b(), Ticker.a);
        if (extends.a((Object)this, Ticker.b, false)) {
            String s = Ticker.c;
            if (Ticker.e.equalsIgnoreCase(this.getParameter(Ticker.d))) {
                s = Ticker.f;
            }
            final Button button = new Button(s);
            button.setForeground(b);
            button.setBackground(b2);
            button.setFont(b3);
            button.setActionCommand(Ticker.g);
            button.addActionListener(this.n);
            this.add(button, Ticker.h);
        }
    }
    
    public void start() {
        this.n.start();
    }
    
    public void stop() {
        this.n.stop();
    }
    
    public Insets getInsets() {
        return this.I;
    }
    
    private static String e(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\uc3e6');
        }
        return new String(array);
    }
    
    static {
        Ticker.ja = e(Ticker.ja);
        Ticker.na = e(Ticker.na);
        Ticker.oa = e(Ticker.oa);
        Ticker.pa = e(Ticker.pa);
        Ticker.sa = e(Ticker.sa);
        Ticker.ta = e(Ticker.ta);
        Ticker.ua = e(Ticker.ua);
        Ticker.va = e(Ticker.va);
        Ticker.wa = e(Ticker.wa);
        Ticker.rb = e(Ticker.rb);
        Ticker.sb = e(Ticker.sb);
        Ticker.tb = e(Ticker.tb);
        Ticker._ = e(Ticker._);
        Ticker.a = e(Ticker.a);
        Ticker.b = e(Ticker.b);
        Ticker.c = e(Ticker.c);
        Ticker.d = e(Ticker.d);
        Ticker.e = e(Ticker.e);
        Ticker.f = e(Ticker.f);
        Ticker.g = e(Ticker.g);
        Ticker.h = e(Ticker.h);
    }
}
