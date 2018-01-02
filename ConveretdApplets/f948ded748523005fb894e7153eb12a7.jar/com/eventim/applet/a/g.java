// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.a;

import java.util.Set;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.util.Comparator;
import java.util.Collections;
import com.eventim.common.transfer.saalplan.GraphDetails;
import java.util.ArrayList;
import java.util.List;

public final class g
{
    private List a;
    private k[] b;
    private double c;
    private f[] d;
    private p[] e;
    private c[] f;
    
    static {
        new l();
    }
    
    private g(final k[] b) {
        this.a = new ArrayList();
        this.c = 1.0;
        this.b = b;
        final ArrayList list = new ArrayList<k>();
        final ArrayList list2 = new ArrayList<k>();
        final ArrayList list3 = new ArrayList<k>();
        for (int i = 0; i < b.length; ++i) {
            final k k;
            if ((k = b[i]) instanceof f) {
                list.add(k);
            }
            else if (k instanceof c) {
                list2.add(k);
            }
            else if (k instanceof p) {
                list3.add(k);
            }
        }
        this.d = (f[])list.toArray(new f[list.size()]);
        this.f = (c[])list2.toArray(new c[list2.size()]);
        this.e = (p[])list3.toArray(new p[list3.size()]);
    }
    
    public static g a(final GraphDetails[] array, final String s) {
        final q a = q.a(s);
        final ArrayList<Object> list = (ArrayList<Object>)new ArrayList<k>();
        for (int i = 0; i < array.length; ++i) {
            final k a2;
            if ((a2 = a.a(array[i])) != null) {
                list.add(a2);
            }
        }
        Collections.sort(list, new e());
        return new g((k[])list.toArray((Object[])new k[list.size()]));
    }
    
    public final void a(final Graphics2D graphics2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.scale(this.c, this.c);
        for (int i = 0; i < this.b.length; ++i) {
            this.b[i].a(graphics2D);
        }
        for (int j = 0; j < this.a.size(); ++j) {
            ((k)this.a.get(j)).a(graphics2D);
        }
    }
    
    public final f[] a() {
        return this.d;
    }
    
    public final p[] b() {
        return this.e;
    }
    
    public final c[] c() {
        return this.f;
    }
    
    public final void a(final int n, int i) {
        for (i = 0; i < this.d.length; ++i) {
            this.d[i].a(n);
        }
        for (i = 0; i < this.f.length; ++i) {
            this.f[i].a(n);
        }
        for (i = 0; i < this.e.length; ++i) {
            this.e[i].a(n);
        }
    }
    
    public final void b(final int n, int i) {
        for (i = 0; i < this.d.length; ++i) {
            this.d[i].c(n);
        }
        for (i = 0; i < this.f.length; ++i) {
            this.f[i].c(n);
        }
        for (i = 0; i < this.e.length; ++i) {
            this.e[i].b(n);
        }
    }
    
    public final void a(final Set set, final Set set2) {
        for (int i = 0; i < this.d.length; ++i) {
            this.d[i].a(set);
        }
        for (int j = 0; j < this.f.length; ++j) {
            this.f[j].a(set);
        }
        for (int k = 0; k < this.e.length; ++k) {
            this.e[k].a(set);
        }
    }
    
    public final void a(final double c) {
        this.c = c;
    }
}
