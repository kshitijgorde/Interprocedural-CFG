// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

import com.eventim.applet.a.i;
import com.eventim.common.transfer.saalplan.SaalplanRabattstufeDetails;
import com.eventim.applet.a.b;
import java.util.ArrayList;
import com.eventim.applet.EventimApplet;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public final class e extends AbstractTableModel
{
    private static final String[] a;
    private List b;
    private List c;
    private List d;
    private List e;
    private String f;
    private EventimApplet g;
    private List h;
    private List i;
    
    static {
        a = new String[8];
    }
    
    public e(final EventimApplet g) {
        this.c = new ArrayList();
        this.e = new ArrayList();
        this.b = new ArrayList();
        this.i = new ArrayList();
        this.d = new ArrayList();
        this.h = new ArrayList();
        this.g = g;
        com.eventim.applet.b.e.a[0] = " " + g.d().A().a("PK");
        com.eventim.applet.b.e.a[1] = g.d().A().a("Platzbereich");
        com.eventim.applet.b.e.a[2] = g.d().A().a("Reihe") + " / " + g.d().A().a("Platz");
        com.eventim.applet.b.e.a[3] = null;
        com.eventim.applet.b.e.a[4] = g.d().A().a("Erm\u00e4\u00dfigung");
        com.eventim.applet.b.e.a[5] = "  " + g.d().A().a("Preis");
        com.eventim.applet.b.e.a[6] = g.d().A().a("L\u00f6schen");
        this.f = "papierkorb_neu_transparent.gif";
        final String k;
        final String trim;
        if ((k = g.d().k()) != null && !(trim = k.trim()).equals("")) {
            this.f = trim;
        }
    }
    
    public final void a(final b b, final Integer n, final Integer n2) {
        if (b.h() || !this.c.contains(b)) {
            this.c.add(b);
            this.e.add(n);
            this.b.add(b);
            final SaalplanRabattstufeDetails a = this.g.e().a(b.e_(), n);
            this.i.add(a);
            this.d.add(this.g.a(a, this.g.d().N()));
            this.h.add(n2);
            this.fireTableDataChanged();
        }
    }
    
    public final Class getColumnClass(final int n) {
        return this.getValueAt(0, n).getClass();
    }
    
    public final int getColumnCount() {
        return 7;
    }
    
    public final String getColumnName(final int n) {
        return com.eventim.applet.b.e.a[n];
    }
    
    public final long a() {
        if (this.c.isEmpty()) {
            return 0L;
        }
        return this.c.get(0).e_();
    }
    
    public final b a(final int n) {
        return this.c.get(n);
    }
    
    public final List b() {
        return this.e;
    }
    
    public final int getRowCount() {
        return this.b.size();
    }
    
    public final Integer b(final int n) {
        return this.h.get(n);
    }
    
    public final Object getValueAt(final int n, final int n2) {
        final b b = this.b.get(n);
        switch (n2) {
            case 0: {
                return com.eventim.applet.a.i.a(EventimApplet.a(b.e_()));
            }
            case 1: {
                if (b.d_() != null) {
                    return b.d_();
                }
                return b.a_();
            }
            case 2: {
                if (b.h()) {
                    return b.b_();
                }
                return b.c_() + " / " + b.b_();
            }
            case 3: {
                return com.eventim.applet.a.i.a("lupe_transparent.gif", this.g.d());
            }
            case 4: {
                return this.g.i().get(n);
            }
            case 5: {
                return "  " + this.d.get(n);
            }
            case 6: {
                return com.eventim.applet.a.i.a(this.f, this.g.d());
            }
            default: {
                return null;
            }
        }
    }
    
    public final int a(final b b) {
        return this.c.indexOf(b);
    }
    
    public final boolean isCellEditable(final int n, final int n2) {
        return n2 == 4;
    }
    
    public final b c(final int n) {
        final b b = this.c.remove(n);
        this.e.remove(n);
        this.b.remove(n);
        this.i.remove(n);
        this.d.remove(n);
        this.h.remove(n);
        this.fireTableDataChanged();
        return b;
    }
    
    public final void a(final SaalplanRabattstufeDetails saalplanRabattstufeDetails, final int n) {
        if (!saalplanRabattstufeDetails.equals(this.i.get(n))) {
            this.i.set(n, saalplanRabattstufeDetails);
            this.d.set(n, this.g.a(saalplanRabattstufeDetails, this.g.d().N()));
            this.fireTableDataChanged();
        }
    }
}
