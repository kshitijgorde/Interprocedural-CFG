// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.awt.event.KeyListener;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Component;
import COM.NextBus.AdminMap.Toolbar.b;
import java.util.Iterator;
import COM.NextBus.HttpMapClient.ConnectionException;
import java.util.Map;
import java.util.Vector;
import java.util.TimerTask;
import java.util.Timer;
import java.util.List;

public class r
{
    private final O c;
    private final List d;
    private final Timer e;
    private final TimerTask f;
    private String g;
    private final Object h;
    private boolean i;
    public static String a;
    public static String b;
    
    public r(final O c) {
        r.a = "<default>";
        r.b = "<" + COM.NextBus.AdminMap.a.b("default") + ">";
        this.c = c;
        this.d = new Vector();
        this.e = new Timer(true);
        this.h = new Object();
        this.f = new h(this);
    }
    
    public final void a() {
        if (!this.c.o()) {
            this.e.schedule(this.f, 10000L, 10000L);
        }
    }
    
    public final String b() {
        return this.g;
    }
    
    public final void a(final String g) {
        final L j = this.c.J();
        if (this.c.o()) {
            j.a();
        }
        else {
            this.c.a("loading config name=" + g);
            this.g = g;
            final String g2 = this.c.G();
            final String u = this.c.u();
            j.b();
            try {
                final Map map;
                if ((map = this.c.e.g(g2, u).get(g)) == null) {
                    j.a();
                }
                j.a(map);
            }
            catch (ConnectionException ex) {
                j.a();
                ex.printStackTrace();
            }
        }
        j.f();
        this.e();
    }
    
    public final void c() {
        this.c.D();
        this.c.E();
    }
    
    public final void b(final String s) {
        if (this.c.o()) {
            return;
        }
        final L j = this.c.J();
        final String u = this.c.u();
        final String g = this.c.G();
        j.g();
        final Vector<String> vector = new Vector<String>();
        final Vector<String> vector2 = new Vector<String>();
        Map c;
        for (final String s2 : (c = j.c()).keySet()) {
            final String s3;
            if (!(s3 = c.get(s2)).equals("false")) {
                vector.add(s2);
                vector2.add(s3);
            }
        }
        try {
            this.c.e.a(g, u, s, vector, vector2);
        }
        catch (ConnectionException ex) {
            System.err.println("Error: could not update user prefs");
            ex.printStackTrace();
        }
    }
    
    public final void c(final String s) {
        final String g = this.c.G();
        final String u = this.c.u();
        try {
            this.c.e.c(g, u, s);
        }
        catch (ConnectionException ex) {
            ex.printStackTrace();
        }
    }
    
    public final List d() {
        final String g = this.c.G();
        final String u = this.c.u();
        try {
            final Map g2 = this.c.e.g(g, u);
            final Vector<String> vector = new Vector<String>();
            for (final String s : g2.keySet()) {
                int i;
                for (i = 0; i < vector.size(); ++i) {
                    if (s.toLowerCase().compareTo(((String)vector.get(i)).toLowerCase()) < 0) {
                        vector.add(i, s);
                        break;
                    }
                }
                if (i == vector.size()) {
                    vector.add(s);
                }
            }
            return vector;
        }
        catch (ConnectionException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public final void a(final b b) {
        this.d.add(b);
    }
    
    public final void e() {
        synchronized (this.h) {
            this.i = true;
        }
    }
    
    public final void f() {
        for (int i = 0; i < this.d.size(); ++i) {
            ((b)this.d.get(i)).a();
        }
    }
    
    public final void d(final String s) {
        this.c.b.b().a(s);
    }
    
    public r() {
    }
    
    public static Frame a(Component parent) {
        while (parent != null) {
            if (parent instanceof Frame) {
                return (Frame)parent;
            }
            parent = parent.getParent();
        }
        return null;
    }
    
    public static void a(final Container container, final KeyListener keyListener) {
        container.addKeyListener(keyListener);
        final Component[] components = container.getComponents();
        for (int i = 0; i < components.length; ++i) {
            final Component component;
            if ((component = components[i]) instanceof Container) {
                a((Container)component, keyListener);
            }
            else {
                component.addKeyListener(keyListener);
            }
        }
    }
}
