import java.awt.Insets;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class package extends Panel
{
    private Label Ta;
    private Label Ua;
    private TextField Va;
    private Choice Wa;
    private interface Xa;
    private float Ya;
    private int Za;
    private String _b;
    private static String Ea = "\u1a40\u1a40\u1a40\u1a40";
    private static String Fa = "\u1a40\u1a40\u1a40\u1a40\u1a40\u1a40\u1a40\u1a40\u1a40\u1a40\u1a40\u1a40\u1a40\u1a40\u1a40\u1a40";
    private static String Ga = "\u1a3b";
    
    public package(final interface xa, final int za, final Font font) {
        this.Ta = new Label(package.Ea);
        this.Ua = new Label(package.Fa);
        this.Va = new TextField(10);
        this.Wa = new Choice();
        this._b = package.Ga;
        this.setLayout(new FlowLayout(0));
        this.Za = za;
        this.Xa = xa;
        if (this.Za == 1) {
            this.Ta.setText(this.Xa._());
            this.Ta.setFont(font);
            for (int i = 0; i < this.Xa.ab[0].cb; ++i) {
                this.Wa.addItem(this.Xa.ab[i].bb);
            }
            this.Wa.setFont(font);
            this.Va.setFont(font);
            this.add(this.Ta);
            this.add(this.Va);
            this.add(this.Wa);
            this.Ya = this.Xa.ab[this.Wa.getSelectedIndex()].db;
        }
        if (this.Za == 2) {
            this.Ta.setText(this.Xa._());
            this.Ta.setFont(font);
            this.add(this.Ta);
            this.Va.setFont(font);
            this.add(this.Va);
        }
        if (this.Za == 3) {
            this.Ta.setText(this.Xa._());
            this.Ta.setFont(font);
            this.Ua.setFont(font);
            for (int j = 0; j < this.Xa.ab[0].cb; ++j) {
                this.Wa.addItem(this.Xa.ab[j].bb);
            }
            this.Wa.setFont(font);
            this.Va.setFont(font);
            this.add(this.Ta);
            this.add(this.Ua);
            this.add(this.Wa);
            this.Ya = this.Xa.ab[this.Wa.getSelectedIndex()].db;
        }
        if (this.Za == 4) {
            this.Ta.setText(this.Xa._());
            this.Ta.setFont(font);
            this.add(this.Ta);
            this.Ua.setFont(font);
            this.add(this.Ua);
        }
    }
    
    public final void _(final String s) {
        if (this.Za == 1 || this.Za == 2) {
            this.Va.setText(s);
        }
        if (this.Za == 3 || this.Za == 4) {
            this.Ua.setText(s);
        }
    }
    
    public final String a() {
        if (this.Za == 3 || this.Za == 4) {
            return this.Ua.getText();
        }
        return this.Va.getText();
    }
    
    public final void _() {
        if (this.Za != 3) {
            this.Va.setEditable(false);
        }
    }
    
    public final void a() {
        if (this.Za != 3) {
            this.Va.setEditable(true);
        }
    }
    
    public float b() {
        return this.Ya = this.Xa.ab[this.Wa.getSelectedIndex()].db;
    }
    
    public String b() {
        return this._b;
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Choice) {
            final Choice choice = new Choice();
            final Choice choice2 = (Choice)event.target;
            this.Xa.b(this.Xa.ab[choice2.getSelectedIndex()].db, this.Ya);
            this.Ya = this.Xa.ab[choice2.getSelectedIndex()].db;
            if (this.Xa.eb) {
                this._b = choice2.getSelectedItem();
            }
            return true;
        }
        return false;
    }
    
    public void _(final int n) {
        this.Wa.select(n);
    }
    
    public String f() {
        return this.Wa.getSelectedItem();
    }
    
    public Insets insets() {
        return new Insets(0, 0, 0, 0);
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u1a70');
        }
        return new String(array);
    }
    
    static {
        package.Ea = _(package.Ea);
        package.Fa = _(package.Fa);
        package.Ga = _(package.Ga);
    }
}
