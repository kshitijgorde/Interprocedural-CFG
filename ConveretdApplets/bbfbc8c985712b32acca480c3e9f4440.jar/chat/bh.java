// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.net.MalformedURLException;
import java.net.URL;
import java.awt.TextField;
import java.awt.Component;

public final class bh extends S
{
    private Component a;
    private Component b;
    private Component c;
    private Component d;
    private TextField a;
    private Component e;
    private TextField b;
    private TextField c;
    private TextField d;
    private TextField e;
    private TextField f;
    private TextField g;
    private cx a;
    
    public final void b() {
        final String trim = this.a.getText().trim();
        final String trim2 = this.b.getText().trim();
        final String trim3 = this.c.getText().trim();
        final String trim4 = this.d.getText().trim();
        final String trim5 = this.e.getText().trim();
        final String trim6 = this.f.getText().trim();
        final String trim7 = this.g.getText().trim();
        try {
            new URL(trim);
        }
        catch (MalformedURLException ex) {
            throw new ar(aS.a(322));
        }
        int int1;
        try {
            int1 = Integer.parseInt(trim2);
        }
        catch (NumberFormatException ex2) {
            this.b.requestFocus();
            this.b.selectAll();
            throw new ar(aS.a(320) + aS.a(10));
        }
        int int2;
        try {
            int2 = Integer.parseInt(trim3);
        }
        catch (NumberFormatException ex3) {
            this.c.requestFocus();
            this.c.selectAll();
            throw new ar(aS.a(319) + aS.a(10));
        }
        int int3;
        try {
            int3 = Integer.parseInt(trim4);
        }
        catch (NumberFormatException ex4) {
            this.d.requestFocus();
            this.d.selectAll();
            throw new ar(aS.a(318) + aS.a(10));
        }
        int int4;
        try {
            int4 = Integer.parseInt(trim5);
        }
        catch (NumberFormatException ex5) {
            this.e.requestFocus();
            this.e.selectAll();
            throw new ar(aS.a(317) + aS.a(10));
        }
        int int5;
        try {
            int5 = Integer.parseInt(trim6);
        }
        catch (NumberFormatException ex6) {
            this.f.requestFocus();
            this.f.selectAll();
            throw new ar(aS.a(316) + aS.a(10));
        }
        int int6;
        try {
            int6 = Integer.parseInt(trim7);
        }
        catch (NumberFormatException ex7) {
            this.g.requestFocus();
            this.g.selectAll();
            throw new ar(aS.a(315) + aS.a(10));
        }
        final long a = r.a(r.a(r.a(r.a(r.a(this.a.d, 60, n.a(this.a)), 58, n.a(this.b)), 54, n.a(this.c)), 55, n.a(this.d)), 49, n.a(this.e));
        final r r;
        (r = new r(67843, 1)).a(0, 0, trim);
        r.a(0, 0, int1);
        r.a(0, 1, int2);
        r.a(0, 2, int3);
        r.a(0, 3, int4);
        r.a(0, 4, int5);
        r.a(0, 5, int6);
        r.a(0, a);
        r.e = -1;
        r.d = -1;
        this.a.o(r);
    }
    
    public final void a() {
        if (this.a.n != null) {
            this.a.setText(this.a.n);
        }
        n.a(this.a, r.a(this.a.d, 60));
        n.a(this.b, r.a(this.a.d, 58));
        n.a(this.c, r.a(this.a.d, 54));
        n.a(this.d, r.a(this.a.d, 55));
        n.a(this.e, r.a(this.a.d, 49));
        this.b.setText(new Integer(this.a.y).toString());
        this.c.setText(new Integer(this.a.z).toString());
        this.d.setText(new Integer(this.a.A).toString());
        this.e.setText(new Integer(this.a.B).toString());
        this.f.setText(new Integer(this.a.C).toString());
        this.g.setText(new Integer(this.a.D).toString());
    }
    
    public final String a(final Object o) {
        if (o == this.a) {
            return aS.a(310);
        }
        if (o == this.b) {
            return aS.a(311);
        }
        if (o == this.a) {
            return aS.a(312);
        }
        if (o == this.b) {
            return aS.a(314);
        }
        return null;
    }
    
    public bh(final cx a) {
        super(aS.a(105), a);
        this.a = n.b(aS.a(299));
        this.b = n.b(aS.a(300));
        this.c = n.b(aS.a(301));
        this.d = n.b(aS.a(302));
        this.e = n.b(aS.a(642));
        this.a = new TextField(25);
        this.b = new TextField(5);
        this.c = new TextField(5);
        this.d = new TextField(5);
        this.e = new TextField(5);
        this.f = new TextField(5);
        this.g = new TextField(5);
        this.a = a;
        this.a(aS.a(303), this.a);
        this.a(aS.a(305), this.b);
        this.a(aS.a(306), this.c);
        this.a(aS.a(307), this.d);
        this.a(aS.a(308), this.e);
        this.a(aS.a(309), new Component[] { this.f, new bi("per", (byte)0), this.g, new bi("seconds", (byte)0) });
        this.c.setForeground(this.getForeground());
        this.d.setForeground(this.getForeground());
        this.a.setForeground(this.getForeground());
        this.b.setForeground(this.getForeground());
        this.e.setForeground(this.getForeground());
        this.a("", this.c);
        this.a("", this.d);
        this.a("", this.e);
        this.a("", this.b);
    }
}
