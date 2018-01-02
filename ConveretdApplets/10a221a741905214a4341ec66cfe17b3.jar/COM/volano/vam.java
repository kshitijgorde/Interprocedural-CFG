// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.util.Observable;
import java.net.URLEncoder;
import java.util.Date;
import java.io.IOException;
import java.awt.Event;
import java.awt.Font;
import java.util.Vector;
import java.util.Hashtable;
import java.awt.Component;
import java.util.Observer;
import java.awt.Frame;

public abstract class vam extends Frame implements Observer
{
    public vc a;
    private Component b;
    public vd c;
    public val d;
    public boolean e;
    public Hashtable f;
    public Hashtable g;
    public vae h;
    public vaw i;
    public vg j;
    public vad k;
    public vad l;
    
    public static String a(final vd vd, final String[] array) {
        final Vector<String> vector = new Vector<String>(3);
        vector.addElement(array[0]);
        vector.addElement(array[2]);
        String s;
        if (array[1].length() == 0) {
            s = van.a(vd.g2, vector);
        }
        else {
            vector.addElement(array[1]);
            s = van.a(vd.g3, vector);
        }
        return s;
    }
    
    public vam(final vc a, final Component b, final vd c) {
        this.e = true;
        this.f = new Hashtable();
        this.g = new Hashtable();
        this.a = a;
        this.b = b;
        this.c = c;
        if (c.ay.length() > 0) {
            this.h = new vae(c.a, "banner.param.", c.ay, c.az, c.a0, c.a1);
        }
        this.i = new vaw(25000);
        this.j = new vg(60);
        this.j.a = c.cj;
        this.j.b = c.cn;
        this.j.c = c.dm;
        this.j.d = c.dn;
        this.j.e = c.do;
        this.j.f = c.dp;
        this.j.a(c.ap);
        this.j.a(1008, c.d0);
        this.j.a(1009, c.d1);
        this.j.a(1010, c.d2);
        this.j.a(1011, c.d3);
        this.j.a(1012, c.d4);
        this.j.a(1013, c.d5);
        this.j.a(1014, c.d6);
        this.j.a(1015, c.d7);
        this.j.a(1016, c.d8);
        this.j.a(1017, c.d9);
        this.j.a(1018, c.ea);
        this.j.a(1019, c.eb);
        this.k = new vad(c.b, c.b2);
        this.i.setEditable(false);
        a.addObserver(this);
    }
    
    public void show() {
        super.show();
        this.j.requestFocus();
    }
    
    public void requestFocus() {
        super.requestFocus();
        this.j.requestFocus();
    }
    
    public void a(final boolean h) {
        this.k.h = h;
    }
    
    public void a(final val val) {
        try {
            this.d = (val)val.clone();
        }
        catch (CloneNotSupportedException ex) {}
        val.a(this, val);
    }
    
    public void a(final String s) {
        final Font m = this.d.m;
        val.a(this, this.d.m = new Font(s, m.getStyle(), m.getSize()));
    }
    
    public void a(final int n) {
        final Font m = this.d.m;
        val.a(this, this.d.m = new Font(m.getName(), n, m.getSize()));
    }
    
    public void a() {
        final Font m = this.d.m;
        val.a(this, this.d.m = new Font(m.getName(), m.getStyle(), m.getSize() + 1));
    }
    
    public void b() {
        final Font m = this.d.m;
        val.a(this, this.d.m = new Font(m.getName(), m.getStyle(), m.getSize() - 1));
    }
    
    public void c() {
        if (this.e) {
            this.a.deleteObserver(this);
        }
        this.dispose();
    }
    
    public boolean keyDown(final Event event, final int n) {
        boolean b = false;
        switch (n) {
            case 9: {
                if (event.shiftDown()) {
                    this.a(this.g, (Component)event.target);
                }
                else {
                    this.a(this.f, (Component)event.target);
                }
                b = true;
                break;
            }
            case 10:
            case 13: {
                if (event.target == this.j) {
                    this.c(this.j.getText());
                    break;
                }
                this.j.requestFocus();
                break;
            }
        }
        return b;
    }
    
    public boolean handleEvent(final Event event) {
        boolean a = false;
        if (event.id == 201) {
            a = this.a(event);
        }
        return a || super.handleEvent(event);
    }
    
    public boolean a(final Event event) {
        if (event.target == this) {
            this.c();
            this.b.deliverEvent(event);
            return true;
        }
        return false;
    }
    
    public String b(String replace) {
        for (int i = 0; i < this.c.dq.length(); ++i) {
            replace = replace.replace(this.c.dq.charAt(i), this.c.dr.charAt(i));
        }
        return replace;
    }
    
    public String a(String s, final boolean b) {
        this.j.setText("");
        s = s.substring(0, Math.min(this.c.cj, s.length()));
        s = this.b(s);
        if (b) {
            this.i.a("> " + s, "> ");
        }
        return s;
    }
    
    public abstract void c(final String p0);
    
    public void a(final vm vm) {
        try {
            this.a.a(vm);
        }
        catch (IOException ex) {
            this.d();
        }
    }
    
    public void d() {
        this.e = false;
        this.i.a(van.a(this.c.g9, new Date()), "> ");
        this.a.deleteObserver(this);
        this.c.o = true;
    }
    
    public void a(final vd vd, final String[] array, final String s) {
        if (vd.as.length() > 0 && Boolean.valueOf(array[3]) && Boolean.valueOf(array[4])) {
            final Vector<String> vector = new Vector<String>(2);
            vector.addElement(URLEncoder.encode(array[0]));
            vector.addElement(URLEncoder.encode(s));
            this.k.a(a(vd, array), vd.ci, van.a(vd.as, vector), vd.b4, vd.b3);
            return;
        }
        this.k.a(a(vd, array), vd.b4, vd.b3);
    }
    
    private void a(final Hashtable hashtable, final Component component) {
        Component j = hashtable.get(component);
        if (j == null) {
            j = this.j;
        }
        j.requestFocus();
    }
    
    public abstract void update(final Observable p0, final Object p1);
}
