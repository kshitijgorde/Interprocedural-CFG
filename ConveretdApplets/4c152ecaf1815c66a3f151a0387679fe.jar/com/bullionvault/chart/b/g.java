// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.b;

import java.util.Calendar;
import com.bullionvault.chart.c.h;
import java.util.Date;
import java.util.TimeZone;
import java.awt.FontMetrics;
import com.bullionvault.chart.c.e;
import java.util.Vector;
import com.bullionvault.chart.f.b;

public final class g extends b
{
    public Vector h;
    private int i;
    private int j;
    private e k;
    private FontMetrics l;
    private int m;
    
    public g(final TimeZone timeZone, final Date date, final Date date2, final int i, final int n, final int n2, final FontMetrics l) {
        this.m = 3;
        com.bullionvault.chart.c.h.f("Initialising Date Axis");
        this.i = i;
        if (timeZone != null) {
            this.k = new e(timeZone, this.i);
        }
        else {
            this.k = new e(this.i);
        }
        this.j = com.bullionvault.chart.c.e.a(this.i);
        this.l = l;
        this.a(date, date2);
        this.a(n, n2);
        this.a();
    }
    
    public final void a(final Date date, final Date date2) {
        com.bullionvault.chart.c.h.f("dateAxis setRange");
        super.a(date, date2);
        (this.f = this.k.a(this.a, 1)).getTime();
        (this.g = this.k.a(this.b, 0)).getTime();
        this.a();
    }
    
    public final void a(final int n, final int n2) {
        super.a(n, n2);
        this.a();
    }
    
    private void a() {
        this.h = new Vector();
        this.k.a(this.f);
        while (this.k.a().before(this.b) || this.k.a().equals(this.b)) {
            final c c;
            (c = new c()).a = this.k.a();
            c.b = c.a.getTime();
            c.d = this.k.b();
            c.c = this.a(c.b);
            c.e = this.l.stringWidth(c.d);
            if (this.k.d()) {
                com.bullionvault.chart.c.h.g("On Major Axis : " + c);
            }
            this.h.addElement(c);
            this.k.a(this.j, 1);
        }
        this = this;
        com.bullionvault.chart.c.h.f("Hiding selective marks");
        com.bullionvault.chart.c.h.e("Units=" + this.i + ", major=" + this.j + " -- MONTH=" + 2);
        this.b();
        if (this.j == 5) {
            if (this.c() || this.a(2) || this.a(7)) {
                return;
            }
            if (this.a(14)) {
                return;
            }
        }
        else if (this.j == 2) {
            if (this.c() || this.a(2) || this.a(3) || this.a(6) || this.a(12) || this.a(24)) {
                return;
            }
            if (this.a(36)) {
                return;
            }
        }
        else if (this.c() || this.a(2) || this.a(5) || this.a(10)) {
            return;
        }
        com.bullionvault.chart.c.h.e("dateAxis - Run out of mark hiding options");
    }
    
    private void b() {
        for (int i = 0; i < this.h.size(); ++i) {
            ((c)this.h.elementAt(i)).f = true;
        }
    }
    
    private void b(final int n, final int n2) {
        for (int i = 0; i < this.h.size(); ++i) {
            com.bullionvault.chart.c.h.g("Considering mark... " + n + "%" + n + "=" + i % n + "==" + n2);
            final c c = this.h.elementAt(i);
            if (i % n == n2) {
                com.bullionvault.chart.c.h.g("Setting mark to show");
                c.f = true;
            }
            else {
                com.bullionvault.chart.c.h.g("Setting mark to no-show");
                c.f = false;
            }
        }
    }
    
    private boolean a(final int n) {
        int i = 0;
        int n2 = 0;
        int n3 = -1;
        while (i < n) {
            this.b(n, i);
            Label_0190: {
                if (this.c()) {
                    final Calendar instance = Calendar.getInstance();
                    while (true) {
                        for (int j = 0; j < this.h.size(); ++j) {
                            final c c;
                            if ((c = this.h.elementAt(j)).f) {
                                instance.setTime(c.a);
                                if (instance.get(this.j) == instance.getMinimum(this.j)) {
                                    com.bullionvault.chart.c.h.e("Prime mark found!");
                                    final boolean b = true;
                                    if (b) {
                                        return true;
                                    }
                                    int n4 = 0;
                                    for (int k = 0; k < this.h.size(); ++k) {
                                        ((c)this.h.elementAt(k)).f = true;
                                        ++n4;
                                    }
                                    final int n5;
                                    if ((n5 = n4) > n2) {
                                        n3 = i;
                                        n2 = n5;
                                    }
                                    break Label_0190;
                                }
                            }
                        }
                        com.bullionvault.chart.c.h.e("No prime mark found.");
                        final boolean b = false;
                        continue;
                    }
                }
            }
            ++i;
        }
        if (n3 != -1) {
            this.b(n, n3);
            return true;
        }
        return false;
    }
    
    private boolean c() {
        boolean b = true;
        c c = null;
        int n = -1;
        for (int i = 0; i < this.h.size(); ++i) {
            final c c2 = this.h.elementAt(i);
            final int n2 = i;
            com.bullionvault.chart.c.h.g("m1=" + n + ",m2=" + n2);
            if (c2.f) {
                if (c != null && c.f) {
                    com.bullionvault.chart.c.h.g("Show Scale: m1=" + n + ",m2=" + n2);
                    final int n3 = c2.c - c.c;
                    com.bullionvault.chart.c.h.g("scale=" + n3 + ",text=" + c.e);
                    if (n3 < c.e + this.m) {
                        b = false;
                    }
                }
                c = c2;
                n = i;
            }
        }
        com.bullionvault.chart.c.h.g("Fits = " + b);
        return b;
    }
}
