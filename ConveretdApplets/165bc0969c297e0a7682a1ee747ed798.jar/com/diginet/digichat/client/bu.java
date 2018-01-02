// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.m;
import java.util.Vector;
import com.diginet.digichat.awt.ao;
import java.awt.Color;
import java.awt.Panel;

public abstract class bu extends Panel
{
    public static final Color a;
    public static final Color b;
    public static final Color c;
    public static final Color d;
    public static final Color e;
    protected ao f;
    protected i g;
    protected String strSearch;
    private Vector vecUsers;
    
    public boolean a(final bd bd) {
        this.vecUsers.removeElement(bd);
        return this.f.e(bd);
    }
    
    public void show() {
        super.show();
        this.f.requestFocus();
    }
    
    protected static void setBackground(final ao ao, final m m, final bd bd) {
        if (bd.clrName != null) {
            ao.a(m, bd.clrName, bd.clrName);
        }
        else if (bd.c) {
            ao.a(m, Color.red, Color.pink);
        }
        else if (bd.i(59)) {
            ao.a(m, bu.c, bu.d);
        }
        else if ((bd.i(61) || bd.i(62)) && !bd.i(23)) {
            ao.a(m, bu.a, bu.b);
        }
        else if (bd.i(23)) {
            ao.a(m, bu.e, bu.d);
        }
        else {
            ao.a(m, Color.black, Color.white);
        }
        if (bd.d) {
            ao.b(bd, true);
        }
        else {
            ao.b(bd, false);
        }
        ao.setBackground(m, bd.clrBack);
    }
    
    protected void b(final bd bd) {
        setBackground(this.f, bd, bd);
    }
    
    public void updateList(final bd bd, final boolean b) {
        synchronized (this.f) {
            if ((this.g.i(24) || !bd.i(23)) && !bd.i(79)) {
                if (!this.vecUsers.contains(bd) && b) {
                    this.vecUsers.addElement(bd);
                }
                if (this.g.isImList()) {
                    this.g.setIMIcons(bd, bd.i(77), bd.i(76));
                }
                if (bd.x().toLowerCase().startsWith(this.strSearch)) {
                    final int b2;
                    if ((b2 = this.f.b(bd)) >= 0) {
                        this.f.b(bd, b2);
                    }
                    else if (b) {
                        this.f.d(bd);
                    }
                    this.b(bd);
                }
            }
        }
        // monitorexit(this.f)
    }
    
    public void updateSearch() {
        synchronized (this.f) {
            this.f.j();
            for (int i = 0; i < this.vecUsers.size(); ++i) {
                final bd bd;
                if ((bd = this.vecUsers.elementAt(i)).x().toLowerCase().startsWith(this.strSearch)) {
                    this.f.d(bd);
                    this.b(bd);
                }
            }
        }
        // monitorexit(this.f)
    }
    
    public bu() {
        this.f = new ao();
        this.strSearch = "";
        this.vecUsers = new Vector();
    }
    
    static {
        a = new Color(153);
        b = new Color(10079487);
        c = new Color(16711680);
        d = new Color(10079487);
        e = new Color(39168);
    }
}
