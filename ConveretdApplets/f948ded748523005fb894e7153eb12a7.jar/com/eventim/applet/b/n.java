// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

import java.util.Iterator;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import com.eventim.applet.EventimApplet;
import javax.swing.Icon;
import java.util.Set;
import javax.swing.table.AbstractTableModel;

public final class n extends AbstractTableModel
{
    private String[] a;
    private int b;
    private static String[] c;
    private Set d;
    private Icon[] e;
    private Long[] f;
    private String[] g;
    private EventimApplet h;
    private Set i;
    private Boolean[] j;
    
    public n(final Long[] f, final Boolean[] j, final Icon[] e, final String[] g, final String[] a, final Set d, final EventimApplet h) {
        this.f = f;
        this.j = j;
        this.e = e;
        this.g = g;
        this.a = a;
        this.d = d;
        this.i = new HashSet(Arrays.asList(f));
        this.h = h;
        final boolean i = h.d().J();
        this.b = (i ? 4 : 3);
        (n.c = new String[this.b])[0] = null;
        n.c[1] = " " + h.d().A().a("PK");
        n.c[2] = h.d().A().a("Preis");
        if (i) {
            n.c[3] = h.d().A().a("Pl\u00e4tze");
        }
        final Iterator<Integer> iterator = this.d.iterator();
        while (iterator.hasNext()) {
            this.j[iterator.next()] = Boolean.FALSE;
        }
        for (int k = 0; k < f.length; ++k) {
            if (h.c().contains(f[k])) {
                this.j[k] = Boolean.FALSE;
            }
        }
    }
    
    public final Class getColumnClass(final int n) {
        return this.getValueAt(0, n).getClass();
    }
    
    public final int getColumnCount() {
        return this.b;
    }
    
    public final String getColumnName(final int n) {
        return n.c[n];
    }
    
    public final Set a() {
        return this.d;
    }
    
    public final int getRowCount() {
        return this.j.length;
    }
    
    public final Set b() {
        return this.i;
    }
    
    public final Object getValueAt(final int n, final int n2) {
        switch (n2) {
            case 0: {
                return this.j[n];
            }
            case 1: {
                return this.e[n];
            }
            case 2: {
                return this.g[n];
            }
            case 3: {
                return this.a[n];
            }
            default: {
                return null;
            }
        }
    }
    
    public final boolean isCellEditable(final int n, final int n2) {
        return n2 == 0 && !this.d.contains(new Integer(n)) && !this.h.k();
    }
    
    public final void a(final Set set) {
        this.i = new HashSet(set);
    }
    
    public final void setValueAt(final Object o, final int n, final int n2) {
        if (n2 == 0) {
            if (o.equals(Boolean.TRUE)) {
                this.j[n] = (Boolean)o;
                this.i.add(this.f[n]);
                this.h.c().remove(this.f[n]);
            }
            else if (this.i.size() > 1) {
                this.j[n] = (Boolean)o;
                this.i.remove(this.f[n]);
                this.h.c().add(this.f[n]);
            }
            this.fireTableDataChanged();
        }
    }
}
