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

public abstract class vap extends Frame implements Observer
{
    public vc a;
    private Component b;
    public vd c;
    public vao d;
    public boolean e;
    public Hashtable f;
    public Hashtable g;
    public vah h;
    public vk i;
    public vg j;
    public vag k;
    public vag l;
    
    public static String a(final vd vd, final String[] array) {
        final Vector<String> vector = new Vector<String>(3);
        vector.addElement(array[0]);
        vector.addElement(array[2]);
        String s;
        if (array[1].length() == 0) {
            s = vaq.a(vd.gt, vector);
        }
        else {
            vector.addElement(array[1]);
            s = vaq.a(vd.gu, vector);
        }
        return s;
    }
    
    public vap(final vc a, final Component b, final vd c) {
        this.e = true;
        this.f = new Hashtable();
        this.g = new Hashtable();
        this.a = a;
        this.b = b;
        this.c = c;
        if (c.a1.length() > 0) {
            this.h = new vah(c.a, "banner.param.", c.a1, c.a2, c.a3, c.a4);
        }
        this.i = vk.b(c.r);
        this.j = new vg(60);
        this.j.a = c.b9;
        this.j.b = c.cd;
        this.j.c = c.dc;
        this.j.d = c.dd;
        this.j.e = c.de;
        this.j.f = c.df;
        this.j.a(c.ap);
        this.j.a(1008, c.di);
        this.j.a(1009, c.dj);
        this.j.a(1010, c.dk);
        this.j.a(1011, c.dl);
        this.j.a(1012, c.dm);
        this.j.a(1013, c.dn);
        this.j.a(1014, c.do);
        this.j.a(1015, c.dp);
        this.j.a(1016, c.dq);
        this.j.a(1017, c.dr);
        this.j.a(1018, c.ds);
        this.j.a(1019, c.dt);
        this.k = new vag(c.b, c.bs);
        this.i.a(false);
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
    
    public void a(final vao vao) {
        try {
            this.d = (vao)vao.clone();
        }
        catch (CloneNotSupportedException ex) {}
        vao.a(this, vao);
    }
    
    public void a(final String s) {
        final Font m = this.d.m;
        vao.a(this, this.d.m = new Font(s, m.getStyle(), m.getSize()));
    }
    
    public void a(final int n) {
        final Font m = this.d.m;
        vao.a(this, this.d.m = new Font(m.getName(), n, m.getSize()));
    }
    
    public void a() {
        final Font m = this.d.m;
        vao.a(this, this.d.m = new Font(m.getName(), m.getStyle(), m.getSize() + 1));
    }
    
    public void b() {
        final Font m = this.d.m;
        vao.a(this, this.d.m = new Font(m.getName(), m.getStyle(), m.getSize() - 1));
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
                }
                else {
                    this.j.requestFocus();
                }
                b = true;
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
        for (int i = 0; i < this.c.dg.length(); ++i) {
            replace = replace.replace(this.c.dg.charAt(i), this.c.dh.charAt(i));
        }
        return replace;
    }
    
    public String a(String s, final boolean b) {
        this.j.setText("");
        s = s.substring(0, Math.min(this.c.b9, s.length()));
        s = this.b(s);
        if (b) {
            this.i.a("> " + s);
        }
        return s;
    }
    
    public abstract void c(final String p0);
    
    public void a(final vp vp) {
        try {
            this.a.a(vp);
        }
        catch (IOException ex) {
            this.d();
        }
    }
    
    public void d() {
        this.e = false;
        this.i.a(vaq.a(this.c.g0, new Date()));
        this.a.deleteObserver(this);
        this.c.o = true;
    }
    
    public void a(final vd vd, final String[] array, final String s) {
        if (vd.as.length() > 0 && Boolean.valueOf(array[3]) && Boolean.valueOf(array[4])) {
            final Vector<String> vector = new Vector<String>(2);
            vector.addElement(URLEncoder.encode(array[0]));
            vector.addElement(URLEncoder.encode(s));
            this.k.a(a(vd, array), vd.b8, vaq.a(vd.as, vector), vd.bu, vd.bt);
            return;
        }
        this.k.a(a(vd, array), vd.bu, vd.bt);
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
