import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class instanceof extends Panel
{
    private CheckboxGroup qa;
    private Checkbox[] ra;
    private Label sa;
    private int ta;
    private interface[] Xa;
    private boolean ua;
    private boolean[] va;
    public boolean wa;
    public boolean xa;
    private static String Ea = "\u1358\u1358\u1358\u1358\u1358\u1358\u1358\u1358\u1358\u1358";
    private static String Fa = "\u1348\u1345\u1348";
    private static String Ga = "\u1326\u1307\u131a\u131c\u1300";
    private static String Ha = "\u132b\u130d\u1306\u131c\u130d\u131a";
    
    public instanceof(final interface[] xa, final String text, final int ta, final Font font) {
        this.qa = new CheckboxGroup();
        this.ra = new Checkbox[10];
        this.sa = new Label(instanceof.Ea);
        this.va = new boolean[10];
        this.wa = false;
        this.xa = false;
        this.Xa = xa;
        this.ta = ta;
        new Panel();
        final Panel panel = new Panel();
        new Panel();
        this.sa.setFont(font);
        this.sa.setText(text);
        final String[] array = new String[ta];
        panel.setLayout(new GridLayout(ta, 1));
        for (int i = 0; i < this.ta; ++i) {
            array[i] = this.Xa[i].h() + instanceof.Fa + this.Xa[i].getName();
            (this.ra[i] = new Checkbox(array[i], this.qa, false)).setFont(font);
            panel.add(this.ra[i]);
        }
        this.setLayout(new BorderLayout());
        this.add(instanceof.Ga, this.sa);
        this.add(instanceof.Ha, panel);
        this.ua = true;
    }
    
    public instanceof(final String[] array, final String text, final int ta, final Font font) {
        this.qa = new CheckboxGroup();
        this.ra = new Checkbox[10];
        this.sa = new Label(instanceof.Ea);
        this.va = new boolean[10];
        this.wa = false;
        this.xa = false;
        this.ta = ta;
        new Panel();
        final Panel panel = new Panel();
        new Panel();
        this.sa.setFont(font);
        this.sa.setText(text);
        final String[] array2 = new String[ta];
        panel.setLayout(new GridLayout(ta, 1));
        for (int i = 0; i < this.ta; ++i) {
            array2[i] = array[i];
            (this.ra[i] = new Checkbox(array2[i], this.qa, false)).setFont(font);
            panel.add(this.ra[i]);
        }
        this.setLayout(new BorderLayout());
        this.add(instanceof.Ga, this.sa);
        this.add(instanceof.Ha, panel);
        this.ua = false;
    }
    
    public void b(final int n) {
        this.ra[n].setState(true);
    }
    
    public void m(final int n) {
        this.ra[n].setState(true);
        for (int i = 0; i < this.ta; ++i) {
            this.va[i] = this.ra[i].getState();
        }
    }
    
    public void l() {
        if (this.isEnabled()) {
            for (int i = 0; i < this.ta; ++i) {
                this.ra[i].setState(this.va[i]);
            }
        }
    }
    
    public int k() {
        for (int i = 0; i < this.ta; ++i) {
            if (this.ra[i].getState()) {
                return i;
            }
        }
        return 0;
    }
    
    public void m() {
        if (!this.xa) {
            for (int i = 0; i < this.ta; ++i) {
                if (this.ua) {
                    if (this.wa) {
                        this.Xa[i]._(!this.ra[i].getState());
                    }
                    else {
                        this.Xa[i]._(this.ra[i].getState());
                    }
                }
            }
        }
    }
    
    public void a(final boolean b) {
        for (int i = 0; i < this.ta; ++i) {
            if (!b) {
                this.ra[i].disable();
            }
            else {
                this.ra[i].enable();
            }
        }
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0x11368);
        }
        return new String(array);
    }
    
    static {
        instanceof.Ea = _(instanceof.Ea);
        instanceof.Fa = _(instanceof.Fa);
        instanceof.Ga = _(instanceof.Ga);
        instanceof.Ha = _(instanceof.Ha);
    }
}
