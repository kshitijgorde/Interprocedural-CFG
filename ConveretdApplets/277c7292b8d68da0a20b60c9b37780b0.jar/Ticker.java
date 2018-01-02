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
    public static final byte Va = 0;
    public static final byte Wa = 1;
    public static final byte Xa = 2;
    public static final byte Ya = 2;
    private l Ha;
    private Insets Za;
    private static String l = "\u5ac7\u5aca\u5ad7\u5ac1\u5ac0\u5ad7\u5af2\u5acc\u5ac1\u5ad1\u5acd";
    private static String m = "\u5ac7\u5aca\u5ad7\u5ac1\u5ac0\u5ad7\u5ae6\u5aca\u5ac9\u5aca\u5ad7";
    private static String o = "\u5ad1\u5acc\u5ac6\u5ace\u5ac0\u5ad7\u5af1\u5adc\u5ad5\u5ac0";
    private static String p = "\u5af4\u5ad0\u5aca\u5ad1\u5ac0\u5ad6";
    private static String s = "\u5aeb\u5ae0\u5af2\u5af6";
    private static String u = "\u5ad1\u5acc\u5ad1\u5ac9\u5ac0\u5ae3\u5ac2\u5ae6\u5aca\u5ac9\u5aca\u5ad7";
    private static String v = "\u5ad1\u5acc\u5ad1\u5ac9\u5ac0\u5ae7\u5ac2\u5ae6\u5aca\u5ac9\u5aca\u5ad7";
    private static String w = "\u5ad1\u5acc\u5ad1\u5ac9\u5ac0\u5ae3\u5aca\u5acb\u5ad1";
    private static String O = "\u5ae4\u5ad7\u5acc\u5ac4\u5ac9";
    private static String P = "\u5ad1\u5acc\u5ad1\u5ac9\u5ac0";
    private static String sa = "\u5ad1\u5acc\u5ad1\u5ac9\u5ac0\u5af2\u5acc\u5ac1\u5ad1\u5acd";
    private static String ta = "\u5ad1\u5acc\u5ad1\u5ac9\u5ac0\u5af0\u5ad7\u5ac9";
    private static String ua = "\u5af2\u5ac0\u5ad6\u5ad1";
    private static String va = "\u5ae6\u5ac0\u5acb\u5ad1\u5ac0\u5ad7";
    private static String wa = "\u5ad6\u5acd\u5aca\u5ad2\u5ae6\u5acd\u5ac4\u5acb\u5ac2\u5ac0\u5ae1\u5acc\u5ad7\u5ac0\u5ac6\u5ad1\u5acc\u5aca\u5acb\u5ae7\u5ad0\u5ad1\u5ad1\u5aca\u5acb";
    private static String xa = "\u5a9b\u5a9b";
    private static String ya = "\u5ad1\u5acc\u5ac6\u5ace\u5ac0\u5ad7\u5ae1\u5acc\u5ad7\u5ac0\u5ac6\u5ad1\u5acc\u5aca\u5acb";
    private static String za = "\u5ac9\u5ad7";
    private static String Aa = "\u5a99\u5a99";
    private static String Ba = "\u5ac1\u5acc\u5ad7\u5ac0\u5ac6\u5ad1\u5acc\u5aca\u5acb";
    private static String Ca = "\u5ae0\u5ac4\u5ad6\u5ad1";
    
    public Ticker() {
        this.Za = new Insets(0, 0, 0, 0);
    }
    
    public void init() {
        this.setLayout(new BorderLayout());
        final int b = m.b(this, Ticker.l, 0);
        if (b > 0) {
            this.setBackground(m.a(this, Ticker.m, 11184640));
            this.Za = new Insets(b, b, b, b);
        }
        if (m.a(this, Ticker.o, Ticker.p).equalsIgnoreCase(Ticker.s)) {
            this.Ha = new n(this);
        }
        else {
            this.Ha = new o(this);
        }
        final Color a = m.a(this, Ticker.u, 16777215);
        final Color a2 = m.a(this, Ticker.v, 1193046);
        final Font _ = m._(this, Ticker.w, Ticker.O, 1, 12);
        final String parameter = this.getParameter(Ticker.P);
        if (parameter != null) {
            final String trim = parameter.trim();
            if (trim.length() > 0) {
                final p p = new p(this, trim, 2, m.b(this, Ticker.sa, -1));
                p.setBackground(a2);
                p.setForeground(a);
                p.setFont(_);
                final String parameter2 = this.getParameter(Ticker.ta);
                if (parameter2 != null) {
                    p.addMouseListener(new q(this, m.b(this, parameter2, false)));
                }
                p.setBounds(0, 0, 100, 10);
                this.add(p, Ticker.ua);
            }
        }
        this.add(this.Ha._(), Ticker.va);
        if (m._((Object)this, Ticker.wa, false)) {
            String s = Ticker.xa;
            if (Ticker.za.equalsIgnoreCase(this.getParameter(Ticker.ya))) {
                s = Ticker.Aa;
            }
            final Button button = new Button(s);
            button.setForeground(a);
            button.setBackground(a2);
            button.setFont(_);
            button.setActionCommand(Ticker.Ba);
            button.addActionListener(this.Ha);
            this.add(button, Ticker.Ca);
        }
    }
    
    public void start() {
        this.Ha.start();
    }
    
    public void stop() {
        this.Ha.stop();
    }
    
    public Insets getInsets() {
        return this.Za;
    }
    
    private static String f(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF5AA5);
        }
        return new String(array);
    }
    
    static {
        Ticker.l = f(Ticker.l);
        Ticker.m = f(Ticker.m);
        Ticker.o = f(Ticker.o);
        Ticker.p = f(Ticker.p);
        Ticker.s = f(Ticker.s);
        Ticker.u = f(Ticker.u);
        Ticker.v = f(Ticker.v);
        Ticker.w = f(Ticker.w);
        Ticker.O = f(Ticker.O);
        Ticker.P = f(Ticker.P);
        Ticker.sa = f(Ticker.sa);
        Ticker.ta = f(Ticker.ta);
        Ticker.ua = f(Ticker.ua);
        Ticker.va = f(Ticker.va);
        Ticker.wa = f(Ticker.wa);
        Ticker.xa = f(Ticker.xa);
        Ticker.ya = f(Ticker.ya);
        Ticker.za = f(Ticker.za);
        Ticker.Aa = f(Ticker.Aa);
        Ticker.Ba = f(Ticker.Ba);
        Ticker.Ca = f(Ticker.Ca);
    }
}
