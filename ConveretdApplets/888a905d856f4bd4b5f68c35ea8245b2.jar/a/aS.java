// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.MenuShortcut;
import java.awt.MenuBar;
import java.awt.Menu;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Label;
import java.util.Date;
import java.text.DecimalFormat;
import java.awt.Frame;
import java.awt.Event;
import java.util.Vector;
import java.awt.MenuItem;

public final class aS extends bE
{
    private bB q;
    cT q;
    private aD q;
    private aL q;
    private bS q;
    private MenuItem q;
    private MenuItem w;
    private MenuItem e;
    private MenuItem r;
    private MenuItem t;
    private MenuItem y;
    private MenuItem u;
    Vector q;
    Vector w;
    boolean q;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                this.dispose();
                this.q.u();
                return true;
            }
            case 1001: {
                if (event.target == this.w) {
                    this.q.u();
                }
                else if (event.target == this.e) {
                    this.q.g();
                }
                else if (event.target == this.q) {
                    this.q.o();
                }
                else if (event.target == this.r) {
                    this.q.p();
                }
                else if (event.target == this.t) {
                    this.q.w(false);
                }
                else if (event.target == this.u) {
                    new dJ(this, "Change Password", this.q, false).setVisible(true);
                }
                else {
                    if (event.target != this.y) {
                        return super.handleEvent(event);
                    }
                    new cT();
                }
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public final boolean q(final aK ak) {
        return this.q.q.q((bJ)ak);
    }
    
    public final void q(final aK ak) {
        synchronized (this.q.q) {
            final int q;
            if ((q = this.q.q.q((bJ)ak)) == -1) {
                this.q.q.e(ak);
            }
            else {
                this.q.q.q(ak, q);
            }
            if (ak.w() && this.q.indexOf(ak.a) < 0) {
                this.q.addElement(ak.a);
            }
            else if (ak.e() && this.w.indexOf(ak.a) < 0) {
                this.w.addElement(ak.a);
            }
        }
    }
    
    public final boolean w(final aK ak) {
        return this.q.q.q((bJ)ak);
    }
    
    public final void w(final aK ak) {
        synchronized (this.q.q) {
            final int q;
            if ((q = this.q.q.q((bJ)ak)) == -1) {
                this.q.q.e(ak);
            }
            else {
                this.q.q.q(ak, q);
            }
        }
    }
    
    public final void q() {
        this.q.q.setText(String.valueOf(this.q.m));
        this.q.u.setText(String.valueOf(this.q.Q));
        this.q.w.setText(String.valueOf(this.q.v));
        this.q.e.setText(String.valueOf(this.q.b));
        this.q.r.setText(String.valueOf(this.q.n));
        final Label i = this.q.i;
        final StringBuffer sb = new StringBuffer();
        final long t = this.q.t;
        final DecimalFormat decimalFormat = new DecimalFormat("####.#");
        final double n;
        String s;
        if ((n = t) > 1024.0) {
            final double n2;
            if ((n2 = n / 1024.0) > 1024.0) {
                final double n3;
                if ((n3 = n2 / 1024.0) > 1024.0) {
                    s = decimalFormat.format(n3 / 1024.0) + "GB";
                }
                else {
                    s = decimalFormat.format(n3) + "MB";
                }
            }
            else {
                s = decimalFormat.format(n2) + "KB";
            }
        }
        else {
            s = decimalFormat.format(n) + "B";
        }
        i.setText(sb.append(s).append(" (").append(this.q.W).append("%)").toString());
        if (this.q.d != null) {
            this.q.q.q(this.q.d);
        }
        if (this.q.r != 0L) {
            this.q.t.setText(L.q.format(new Date(this.q.r)));
        }
        this.q.y.setText(L.q.format(new Date(this.q.w)));
    }
    
    public aS(final cT q) {
        super(dN.e + " Admin", false);
        this.q = new Vector();
        this.w = new Vector();
        this.q = false;
        new ad(be.w("Disconnect"), 150, 20);
        new ad(be.w("Set Server Options"), 150, 20);
        new ad(be.w("Quit"), 150, 20);
        new ad(be.w("Send Chat Broadcast"), 150, 20);
        this.q = q;
        this.q = new bB(2, 10, 2, 10, 1, q);
        this.setLayout(new BorderLayout());
        this.add("Center", this.q);
        this.q = new aD(this, this);
        this.q = new aL(this, this);
        ((bB)(this.q = new bS(this, this))).q(be.w("Status"), this.q);
        this.q.q(be.w("Sites"), this.q);
        if (dN.u) {
            this.q.q(be.w("Manage"), this.q);
        }
        this.pack();
        this.e();
        final Menu menu = new Menu(dN.e);
        final MenuBar menuBar = new MenuBar();
        menu.add(this.q = new MenuItem(be.w("Set Server Options...")));
        menu.add(this.r = new MenuItem(be.w("Send Chat Broadcast...")));
        menu.add(this.t = new MenuItem(be.w("Change Password...")));
        menu.add(this.u = new MenuItem(be.w("Change Commander Password...")));
        if (!q.y) {
            menu.addSeparator();
            menu.add(this.y = new MenuItem(be.w("New Login Window")));
            menu.addSeparator();
            menu.add(this.w = new MenuItem(be.w("Disconnect")));
            menu.add(this.e = new MenuItem(be.w("Quit"), new MenuShortcut(81)));
        }
        menuBar.add(menu);
        this.setMenuBar(menuBar);
    }
}
