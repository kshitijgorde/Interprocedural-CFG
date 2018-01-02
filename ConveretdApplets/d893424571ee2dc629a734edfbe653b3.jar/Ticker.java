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
    public static final byte z = 0;
    public static final byte A = 1;
    public static final byte B = 2;
    public static final byte C = 2;
    private import _;
    private Insets D;
    private static String V = "\ud75e\ud753\ud74e\ud758\ud759\ud74e\ud76b\ud755\ud758\ud748\ud754";
    private static String W = "\ud75e\ud753\ud74e\ud758\ud759\ud74e\ud77f\ud753\ud750\ud753\ud74e";
    private static String _a = "\ud748\ud755\ud75f\ud757\ud759\ud74e\ud768\ud745\ud74c\ud759";
    private static String aa = "\ud76d\ud749\ud753\ud748\ud759\ud74f";
    private static String ba = "\ud772\ud779\ud76b\ud76f";
    private static String fa = "\ud748\ud755\ud748\ud750\ud759\ud77a\ud75b\ud77f\ud753\ud750\ud753\ud74e";
    private static String ga = "\ud748\ud755\ud748\ud750\ud759\ud77e\ud75b\ud77f\ud753\ud750\ud753\ud74e";
    private static String ha = "\ud748\ud755\ud748\ud750\ud759\ud77a\ud753\ud752\ud748";
    private static String ia = "\ud77d\ud74e\ud755\ud75d\ud750";
    private static String db = "\ud748\ud755\ud748\ud750\ud759";
    private static String eb = "\ud748\ud755\ud748\ud750\ud759\ud76b\ud755\ud758\ud748\ud754";
    private static String fb = "\ud748\ud755\ud748\ud750\ud759\ud769\ud76e\ud770";
    private static String gb = "\ud76b\ud759\ud74f\ud748";
    private static String hb = "\ud77f\ud759\ud752\ud748\ud759\ud74e";
    private static String ib = "\ud74f\ud754\ud753\ud74b\ud77f\ud754\ud75d\ud752\ud75b\ud759\ud778\ud755\ud74e\ud759\ud75f\ud748\ud755\ud753\ud752\ud77e\ud749\ud748\ud748\ud753\ud752";
    private static String jb = "\ud702\ud702";
    private static String kb = "\ud748\ud755\ud75f\ud757\ud759\ud74e\ud778\ud755\ud74e\ud759\ud75f\ud748\ud755\ud753\ud752";
    private static String lb = "\ud750\ud74e";
    private static String mb = "\ud700\ud700";
    private static String nb = "\ud758\ud755\ud74e\ud759\ud75f\ud748\ud755\ud753\ud752";
    private static String ob = "\ud779\ud75d\ud74f\ud748";
    
    public Ticker() {
        this.D = new Insets(0, 0, 0, 0);
    }
    
    public void init() {
        this.setLayout(new BorderLayout());
        final int _ = instanceof._(this, Ticker.V, 0);
        if (_ > 0) {
            this.setBackground(instanceof.b(this, Ticker.W, 11184640));
            this.D = new Insets(_, _, _, _);
        }
        if (instanceof._(this, Ticker._a, Ticker.aa).equalsIgnoreCase(Ticker.ba)) {
            this._ = new interface(this);
        }
        else {
            this._ = new native(this);
        }
        final Color b = instanceof.b(this, Ticker.fa, 16777215);
        final Color b2 = instanceof.b(this, Ticker.ga, 1193046);
        final Font a = instanceof.a(this, Ticker.ha, Ticker.ia, 1, 12);
        final String parameter = this.getParameter(Ticker.db);
        if (parameter != null) {
            final String trim = parameter.trim();
            if (trim.length() > 0) {
                final new new1 = new new(this, trim, 2, instanceof._(this, Ticker.eb, -1));
                new1.setBackground(b2);
                new1.setForeground(b);
                new1.setFont(a);
                final String parameter2 = this.getParameter(Ticker.fb);
                if (parameter2 != null) {
                    new1.addMouseListener(new null(this, instanceof._((Object)this, parameter2, false)));
                }
                new1.setBounds(0, 0, 100, 10);
                this.add(new1, Ticker.gb);
            }
        }
        this.add(this._.b(), Ticker.hb);
        if (instanceof._((Object)this, Ticker.ib, false)) {
            String s = Ticker.jb;
            if (Ticker.lb.equalsIgnoreCase(this.getParameter(Ticker.kb))) {
                s = Ticker.mb;
            }
            final Button button = new Button(s);
            button.setForeground(b);
            button.setBackground(b2);
            button.setFont(a);
            button.setActionCommand(Ticker.nb);
            button.addActionListener(this._);
            this.add(button, Ticker.ob);
        }
    }
    
    public void start() {
        this._.start();
    }
    
    public void stop() {
        this._.stop();
    }
    
    public Insets getInsets() {
        return this.D;
    }
    
    private static String j(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\ud73c');
        }
        return new String(array);
    }
    
    static {
        Ticker.V = j(Ticker.V);
        Ticker.W = j(Ticker.W);
        Ticker._a = j(Ticker._a);
        Ticker.aa = j(Ticker.aa);
        Ticker.ba = j(Ticker.ba);
        Ticker.fa = j(Ticker.fa);
        Ticker.ga = j(Ticker.ga);
        Ticker.ha = j(Ticker.ha);
        Ticker.ia = j(Ticker.ia);
        Ticker.db = j(Ticker.db);
        Ticker.eb = j(Ticker.eb);
        Ticker.fb = j(Ticker.fb);
        Ticker.gb = j(Ticker.gb);
        Ticker.hb = j(Ticker.hb);
        Ticker.ib = j(Ticker.ib);
        Ticker.jb = j(Ticker.jb);
        Ticker.kb = j(Ticker.kb);
        Ticker.lb = j(Ticker.lb);
        Ticker.mb = j(Ticker.mb);
        Ticker.nb = j(Ticker.nb);
        Ticker.ob = j(Ticker.ob);
    }
}
