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

public abstract class mab extends Frame implements Observer
{
    public mc a;
    private Component b;
    public md c;
    public maj d;
    public boolean e;
    public Hashtable f;
    public Hashtable g;
    public mag h;
    public mad i;
    public mg j;
    public mai k;
    public mai l;
    
    public static String a(final md md, final String[] array) {
        final Vector<String> vector = new Vector<String>(3);
        vector.addElement(array[0]);
        vector.addElement(array[2]);
        String s;
        if (array[1].length() == 0) {
            s = mz.a(md.gq, vector);
        }
        else {
            vector.addElement(array[1]);
            s = mz.a(md.gr, vector);
        }
        return s;
    }
    
    public mab(final mc a, final Component b, final md c) {
        this.e = true;
        this.f = new Hashtable();
        this.g = new Hashtable();
        this.a = a;
        this.b = b;
        this.c = c;
        if (c.ay.length() > 0) {
            this.h = new mag(c.a, "banner.param.", c.ay, c.az, c.a0, c.a1);
        }
        this.i = new mad(25000);
        this.j = new mg(60);
        this.j.a = c.b6;
        this.j.b = c.ca;
        this.j.c = c.c9;
        this.j.d = c.da;
        this.j.e = c.db;
        this.j.f = c.dc;
        this.j.a(c.ap);
        this.j.a(1008, c.df);
        this.j.a(1009, c.dg);
        this.j.a(1010, c.dh);
        this.j.a(1011, c.di);
        this.j.a(1012, c.dj);
        this.j.a(1013, c.dk);
        this.j.a(1014, c.dl);
        this.j.a(1015, c.dm);
        this.j.a(1016, c.dn);
        this.j.a(1017, c.do);
        this.j.a(1018, c.dp);
        this.j.a(1019, c.dq);
        this.k = new mai(c.b, c.bp);
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
    
    public void a(final maj maj) {
        try {
            this.d = (maj)maj.clone();
        }
        catch (CloneNotSupportedException ex) {}
        maj.a(this, maj);
    }
    
    public void b(final String s) {
        final Font m = this.d.m;
        maj.a(this, this.d.m = new Font(s, m.getStyle(), m.getSize()));
    }
    
    public void a(final int n) {
        final Font m = this.d.m;
        maj.a(this, this.d.m = new Font(m.getName(), n, m.getSize()));
    }
    
    public void c() {
        final Font m = this.d.m;
        maj.a(this, this.d.m = new Font(m.getName(), m.getStyle(), m.getSize() + 1));
    }
    
    public void d() {
        final Font m = this.d.m;
        maj.a(this, this.d.m = new Font(m.getName(), m.getStyle(), m.getSize() - 1));
    }
    
    public void b() {
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
            this.b();
            this.b.deliverEvent(event);
            return true;
        }
        return false;
    }
    
    public String f(String replace) {
        for (int i = 0; i < this.c.dd.length(); ++i) {
            replace = replace.replace(this.c.dd.charAt(i), this.c.de.charAt(i));
        }
        return replace;
    }
    
    public String a(String s, final boolean b) {
        this.j.setText("");
        s = s.substring(0, Math.min(this.c.b6, s.length()));
        s = this.f(s);
        if (b) {
            this.i.a("> " + s, "> ");
        }
        return s;
    }
    
    public abstract void c(final String p0);
    
    public void a(final mm mm) {
        try {
            this.a.a(mm);
        }
        catch (IOException ex) {
            this.e();
        }
    }
    
    public void e() {
        this.e = false;
        this.i.a(mz.a(this.c.gx, new Date()), "> ");
        this.a.deleteObserver(this);
        this.c.o = true;
    }
    
    public void a(final md md, final String[] array, final String s) {
        if (md.as.length() > 0 && Boolean.valueOf(array[3]) && Boolean.valueOf(array[4])) {
            final Vector<String> vector = new Vector<String>(2);
            vector.addElement(URLEncoder.encode(array[0]));
            vector.addElement(URLEncoder.encode(s));
            this.k.a(a(md, array), md.b5, mz.a(md.as, vector), md.br, md.bq);
            return;
        }
        this.k.a(a(md, array), md.br, md.bq);
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
