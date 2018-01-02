// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Component;
import java.awt.Color;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.TextField;

public final class aO extends S
{
    private TextField a;
    private TextField b;
    private TextField c;
    private TextField d;
    private TextField e;
    private Checkbox a;
    private Checkbox b;
    private Checkbox c;
    private Checkbox d;
    private Choice a;
    private Choice b;
    private cr a;
    private cx a;
    private v a;
    private TextField f;
    private TextField g;
    private TextField h;
    private TextField i;
    
    public final void b() {
        final String trim = this.a.getText().trim();
        final String trim2 = this.b.getText().trim();
        final String trim3 = this.c.getText().trim();
        final String trim4 = this.d.getText().trim();
        final String trim5 = this.e.getText().trim();
        int int1;
        try {
            int1 = Integer.parseInt(trim);
        }
        catch (NumberFormatException ex) {
            this.a.requestFocus();
            this.a.selectAll();
            throw new ar(aS.a(523) + aS.a(10));
        }
        int int2;
        try {
            int2 = Integer.parseInt(trim2);
        }
        catch (NumberFormatException ex2) {
            this.b.requestFocus();
            this.b.selectAll();
            throw new ar(aS.a(524) + aS.a(10));
        }
        int int3;
        try {
            int3 = Integer.parseInt(trim3);
        }
        catch (NumberFormatException ex3) {
            this.c.requestFocus();
            this.c.selectAll();
            throw new ar(aS.a(525) + aS.a(10));
        }
        int int4;
        try {
            int4 = Integer.parseInt(trim4);
        }
        catch (NumberFormatException ex4) {
            this.d.requestFocus();
            this.d.selectAll();
            throw new ar(aS.a(526) + aS.a(10));
        }
        int int5;
        try {
            int5 = Integer.parseInt(trim5);
        }
        catch (NumberFormatException ex5) {
            this.e.requestFocus();
            this.e.selectAll();
            throw new ar(aS.a(527) + aS.a(10));
        }
        int int6;
        try {
            int6 = Integer.parseInt(this.f.getText().trim());
        }
        catch (NumberFormatException ex6) {
            this.f.requestFocus();
            this.f.selectAll();
            throw new ar(aS.a(523) + aS.a(10));
        }
        int int7;
        try {
            int7 = Integer.parseInt(this.g.getText().trim());
        }
        catch (NumberFormatException ex7) {
            this.g.requestFocus();
            this.g.selectAll();
            throw new ar(aS.a(524) + aS.a(10));
        }
        int int8;
        try {
            int8 = Integer.parseInt(this.h.getText().trim());
        }
        catch (NumberFormatException ex8) {
            this.h.requestFocus();
            this.h.selectAll();
            throw new ar(aS.a(526) + aS.a(10));
        }
        int int9;
        try {
            int9 = Integer.parseInt(this.i.getText().trim());
        }
        catch (NumberFormatException ex9) {
            this.i.requestFocus();
            this.i.selectAll();
            throw new ar(aS.a(527) + aS.a(10));
        }
        final long a = r.a(r.a(r.a(r.a(r.a(this.a.h, 30, this.a.getState()), 31, this.b.getState()), 32, this.c.getState()), 33, this.d.getState()), 5, this.b.getSelectedIndex() == 1);
        final r r;
        (r = new r(67844, 1)).a(0, 0, int1);
        r.a(0, 1, int2);
        r.a(0, 2, int3);
        r.a(0, 3, int4);
        r.a(0, 4, int5);
        r.a(0, 5, Integer.parseInt(this.a.getSelectedItem()));
        r.a(0, 6, this.a.a);
        r.a(0, 7, int6);
        r.a(0, 8, int7);
        r.a(0, 9, int8);
        r.a(0, 10, int9);
        r.a(0, a);
        r.e = -1;
        r.d = -1;
        this.a.o(r);
    }
    
    public final void a() {
        this.a.setText(new Integer(this.a.E).toString());
        this.b.setText(new Integer(this.a.F).toString());
        this.c.setText(new Integer(this.a.G).toString());
        this.d.setText(new Integer(this.a.H).toString());
        this.f.setText(new Integer(this.a.I).toString());
        this.g.setText(new Integer(this.a.J).toString());
        this.h.setText(new Integer(this.a.K).toString());
        this.e.setText(new Integer(this.a.M).toString());
        this.i.setText(new Integer(this.a.L).toString());
        this.a.select(String.valueOf(this.a.N));
        this.a.setState(r.a(this.a.h, 30));
        this.b.setState(r.a(this.a.h, 31));
        this.c.setState(r.a(this.a.h, 32));
        this.d.setState(r.a(this.a.h, 33));
        if (r.a(this.a.h, 5)) {
            this.b.select(1);
        }
        else {
            this.b.select(0);
        }
        this.a.a(new Color(this.a.O));
    }
    
    public final String a(final Object o) {
        return null;
    }
    
    public aO(final cx a) {
        super(aS.a(105) + "2", a);
        this.a = a;
        this.a = new TextField(5);
        this.b = new TextField(5);
        this.c = new TextField(5);
        this.d = new TextField(5);
        this.e = new TextField(5);
        this.f = new TextField(5);
        this.g = new TextField(5);
        new TextField(5);
        this.h = new TextField(5);
        this.i = new TextField(5);
        (this.a = new Choice()).add("35");
        this.a.add("50");
        this.a.add("75");
        this.a.add("100");
        this.a = new Checkbox(aS.a(514));
        this.b = new Checkbox(aS.a(515));
        this.c = new Checkbox(aS.a(516));
        this.d = new Checkbox(aS.a(562));
        this.a = new v(this.a);
        (this.a = new cr(120, 20)).a(aS.a(647));
        (this.b = new Choice()).add(aS.a(628));
        this.b.add(aS.a(627));
        this.a("", new bi(aS.a(424), (byte)0), new bi(aS.a(420), (byte)0));
        this.a(aS.a(517), this.a, this.f);
        this.a(aS.a(518), this.b, this.g);
        this.a(aS.a(520), this.d, this.h);
        this.a(aS.a(521), this.e, this.i);
        this.a(aS.a(519), this.c);
        this.a(aS.a(522), this.a);
        this.a(aS.a(626), this.b);
        this.a(aS.a(560), this.a);
        this.a(this.a, this.b);
        this.a(this.c, this.d);
    }
}
