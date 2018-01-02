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
    private case E;
    private Insets sa;
    private static String a = "\u7d7a\u7d77\u7d6a\u7d7c\u7d7d\u7d6a\u7d4f\u7d71\u7d7c\u7d6c\u7d70";
    private static String b = "\u7d7a\u7d77\u7d6a\u7d7c\u7d7d\u7d6a\u7d5b\u7d77\u7d74\u7d77\u7d6a";
    private static String c = "\u7d6c\u7d71\u7d6c\u7d74\u7d7d\u7d5e\u7d7f\u7d5b\u7d77\u7d74\u7d77\u7d6a";
    private static String d = "\u7d6c\u7d71\u7d6c\u7d74\u7d7d\u7d5a\u7d7f\u7d5b\u7d77\u7d74\u7d77\u7d6a";
    private static String e = "\u7d6c\u7d71\u7d6c\u7d74\u7d7d\u7d5e\u7d77\u7d76\u7d6c";
    private static String f = "\u7d59\u7d6a\u7d71\u7d79\u7d74";
    private static String g = "\u7d6c\u7d71\u7d6c\u7d74\u7d7d";
    private static String h = "\u7d6c\u7d71\u7d6c\u7d74\u7d7d\u7d4f\u7d71\u7d7c\u7d6c\u7d70";
    private static String i = "\u7d6c\u7d71\u7d6c\u7d74\u7d7d\u7d4d\u7d4a\u7d54";
    private static String I = "\u7d4f\u7d7d\u7d6b\u7d6c";
    private static String J = "\u7d5b\u7d7d\u7d76\u7d6c\u7d7d\u7d6a";
    private static String K = "\u7d6b\u7d70\u7d77\u7d6f\u7d5b\u7d70\u7d79\u7d76\u7d7f\u7d7d\u7d5c\u7d71\u7d6a\u7d7d\u7d7b\u7d6c\u7d71\u7d77\u7d76\u7d5a\u7d6d\u7d6c\u7d6c\u7d77\u7d76";
    private static String L = "\u7d26\u7d26";
    private static String M = "\u7d6c\u7d71\u7d7b\u7d73\u7d7d\u7d6a\u7d5c\u7d71\u7d6a\u7d7d\u7d7b\u7d6c\u7d71\u7d77\u7d76";
    private static String X = "\u7d74\u7d6a";
    private static String Y = "\u7d24\u7d24";
    private static String Z = "\u7d7c\u7d71\u7d6a\u7d7d\u7d7b\u7d6c\u7d71\u7d77\u7d76";
    private static String _a = "\u7d5d\u7d79\u7d6b\u7d6c";
    
    public Ticker() {
        this.sa = new Insets(0, 0, 0, 0);
    }
    
    public void init() {
        this.setLayout(new BorderLayout());
        final int a = catch.a(this, Ticker.a, 0);
        if (a > 0) {
            this.setBackground(catch.b(this, Ticker.b, 11184640));
            this.sa = new Insets(a, a, a, a);
        }
        this.E = new class(this);
        final Color b = catch.b(this, Ticker.c, 16777215);
        final Color b2 = catch.b(this, Ticker.d, 1193046);
        final Font b3 = catch.b(this, Ticker.e, Ticker.f, 1, 12);
        final String parameter = this.getParameter(Ticker.g);
        if (parameter != null) {
            final String trim = parameter.trim();
            if (trim.length() > 0) {
                final const const1 = new const(this, trim, 2, catch.a(this, Ticker.h, -1));
                const1.setBackground(b2);
                const1.setForeground(b);
                const1.setFont(b3);
                final String parameter2 = this.getParameter(Ticker.i);
                if (parameter2 != null) {
                    const1.addMouseListener(new continue(this, catch._((Object)this, parameter2, false)));
                }
                const1.setBounds(0, 0, 100, 10);
                this.add(const1, Ticker.I);
            }
        }
        this.add(this.E.a(), Ticker.J);
        if (catch._((Object)this, Ticker.K, false)) {
            String s = Ticker.L;
            if (Ticker.X.equalsIgnoreCase(this.getParameter(Ticker.M))) {
                s = Ticker.Y;
            }
            final Button button = new Button(s);
            button.setForeground(b);
            button.setBackground(b2);
            button.setFont(b3);
            button.setActionCommand(Ticker.Z);
            button.addActionListener(this.E);
            this.add(button, Ticker._a);
        }
    }
    
    public void start() {
        this.E.start();
    }
    
    public void stop() {
        this.E.stop();
    }
    
    public Insets getInsets() {
        return this.sa;
    }
    
    private static String h(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFE7D18);
        }
        return new String(array);
    }
    
    static {
        Ticker.a = h(Ticker.a);
        Ticker.b = h(Ticker.b);
        Ticker.c = h(Ticker.c);
        Ticker.d = h(Ticker.d);
        Ticker.e = h(Ticker.e);
        Ticker.f = h(Ticker.f);
        Ticker.g = h(Ticker.g);
        Ticker.h = h(Ticker.h);
        Ticker.i = h(Ticker.i);
        Ticker.I = h(Ticker.I);
        Ticker.J = h(Ticker.J);
        Ticker.K = h(Ticker.K);
        Ticker.L = h(Ticker.L);
        Ticker.M = h(Ticker.M);
        Ticker.X = h(Ticker.X);
        Ticker.Y = h(Ticker.Y);
        Ticker.Z = h(Ticker.Z);
        Ticker._a = h(Ticker._a);
    }
}
