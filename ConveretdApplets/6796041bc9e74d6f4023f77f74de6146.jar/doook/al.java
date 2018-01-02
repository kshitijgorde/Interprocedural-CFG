// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.Color;
import java.awt.Choice;
import java.awt.TextField;

public class al extends o
{
    private TextField m;
    private Choice k;
    private aX f;
    private aX g;
    private bP b;
    private Choice l;
    private Choice m;
    private u k;
    
    public void c() {
        final cD cd = new cD(264192, 1);
        cd.a(0, 0, this.m.getText());
        cd.a(0, 1, this.b.getSelectedItem());
        cd.a(0, 0, this.f.aA);
        cd.a(0, 1, this.g.aA);
        cd.a(0, 2, this.l.getSelectedIndex());
        cd.a(0, 3, this.m.getSelectedIndex());
        cd.a(0, 4, this.k.getSelectedIndex());
        this.k.o(cd);
    }
    
    public void d() {
        this.m.setText(this.k.C);
        this.b.select(this.k.D);
        this.k.select(this.k.S);
        this.l.select(this.k.Q);
        this.m.select(this.k.R);
        this.f.a(new Color(this.k.O));
        this.g.a(new Color(this.k.P));
    }
    
    public al(final u k) {
        super(ao.e("RSS"), k);
        this.k = k;
        this.m = new TextField(25);
        (this.k = new Choice()).setBackground(Color.white);
        this.k.setForeground(Color.black);
        this.k.addItem(ao.e("30 minutes"));
        this.k.addItem(ao.e("1 hour"));
        this.k.addItem(ao.e("3 hours"));
        this.k.addItem(ao.e("6 hours"));
        this.k.addItem(ao.e("12 hours"));
        this.k.addItem(ao.e("24 hours"));
        (this.f = new aX(this.k)).a(this.k.k, false, true);
        (this.g = new aX(this.k)).a(this.k.k, false, true);
        this.b = new bP();
        (this.l = new Choice()).setBackground(Color.white);
        this.l.setForeground(Color.black);
        this.l.addItem(ao.e("Very Slow"));
        this.l.addItem(ao.e("Slow"));
        this.l.addItem(ao.e("Normal"));
        this.l.addItem(ao.e("Fast"));
        this.l.addItem(ao.e("Very Fast"));
        (this.m = new Choice()).setBackground(Color.white);
        this.m.setForeground(Color.black);
        this.m.addItem(ao.e("Right To Left"));
        this.m.addItem(ao.e("Left To Right"));
        this.a(ao.e("RSS Feed URL:"), this.m);
        this.a(ao.e("Text Color:"), this.f);
        this.a(ao.e("Background Color:"), this.g);
        this.a(ao.e("Font:"), this.b);
        this.a(ao.e("Update Every:"), this.k);
        this.a(ao.e("Scroll Direction:"), this.m);
        this.a(ao.e("Scroll Speed:"), this.l);
    }
}
