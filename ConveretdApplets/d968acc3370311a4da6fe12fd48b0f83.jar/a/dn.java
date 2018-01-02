// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Dimension;
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

public final class dn extends u
{
    private B q;
    private dz q;
    private dq q;
    private dp q;
    private do q;
    private MenuItem q;
    private MenuItem w;
    private MenuItem e;
    private MenuItem r;
    private MenuItem t;
    private MenuItem y;
    private MenuItem u;
    private Vector q;
    private Vector w;
    private boolean q;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                this.dispose();
                this.q.w();
                return true;
            }
            case 1001: {
                if (event.target == this.w) {
                    this.q.w();
                }
                else if (event.target == this.e) {
                    this.q.t();
                }
                else if (event.target == this.q) {
                    this.q.p();
                }
                else if (event.target == this.r) {
                    this.q.a();
                }
                else if (event.target == this.t) {
                    this.q.r(false);
                }
                else if (event.target == this.u) {
                    new dv(this, "Change Password", this.q, false).setVisible(true);
                }
                else {
                    if (event.target != this.y) {
                        return super.handleEvent(event);
                    }
                    new dz();
                }
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public final boolean q(final cw cw) {
        return this.q.q.q((aF)cw);
    }
    
    public final void q(final cw cw) {
        synchronized (this.q.q) {
            final int q;
            if ((q = this.q.q.q((aF)cw)) == -1) {
                this.q.q.e(cw);
            }
            else {
                this.q.q.q(cw, q);
            }
            if (cw.e() && this.q.indexOf(cw.getName()) < 0) {
                this.q.addElement(cw.getName());
            }
            else if (cw.r() && this.w.indexOf(cw.getName()) < 0) {
                this.w.addElement(cw.getName());
            }
        }
    }
    
    public final boolean w(final cw cw) {
        return this.q.q.q((aF)cw);
    }
    
    public final void w(final cw cw) {
        synchronized (this.q.q) {
            final int q;
            if ((q = this.q.q.q((aF)cw)) == -1) {
                this.q.q.e(cw);
            }
            else {
                this.q.q.q(cw, q);
            }
        }
    }
    
    public final void w() {
        this.q.q.setText(String.valueOf(this.q.a()));
        this.q.u.setText(String.valueOf(this.q.d()));
        this.q.w.setText(String.valueOf(this.q.f()));
        this.q.e.setText(String.valueOf(this.q.g()));
        this.q.r.setText(String.valueOf(this.q.p()));
        final Label i = this.q.i;
        final StringBuffer sb = new StringBuffer();
        final long e = this.q.e();
        final DecimalFormat decimalFormat = new DecimalFormat("####.#");
        final double n;
        String s;
        if ((n = e) > 1024.0) {
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
        i.setText(sb.append(s).append(" (").append(this.q.s()).append("%)").toString());
        if (this.q.w() != null) {
            this.q.q.q(this.q.w());
        }
        if (this.q.r() != 0L) {
            this.q.t.setText(cu.q.format(new Date(this.q.r())));
        }
        this.q.y.setText(cu.q.format(new Date(this.q.t())));
    }
    
    static dz q(final dn dn) {
        return dn.q;
    }
    
    public final void e() {
        if (this.q) {
            return;
        }
        String s = "";
        if (this.q.size() > 0) {
            s = s + "You Have " + this.q.size() + " Sites Already Expired\n";
        }
        if (this.w.size() > 0) {
            s = s + "You Have " + this.w.size() + " Sites About To Expire\n";
        }
        if (s.length() > 0) {
            new b(new Frame(), eb.q("Note"), s + "\nExpired Sites Will not Work Unless You Set New End Date", this.q).setVisible(true);
            this.q = true;
        }
    }
    
    public dn(final dz q) {
        super(a.e + " Admin", false);
        this.q = new Vector();
        this.w = new Vector();
        this.q = false;
        new g(eb.q("Disconnect"), 150, 20);
        new g(eb.q("Set Server Options"), 150, 20);
        new g(eb.q("Quit"), 150, 20);
        new g(eb.q("Send Chat Broadcast"), 150, 20);
        this.q = q;
        this.q = new B(2, 10, 2, 10, 1, q);
        this.setLayout(new BorderLayout());
        this.add("Center", this.q);
        this.q = new dq(this, this);
        this.q = new dp(this, this);
        ((B)(this.q = new do(this, this))).q(eb.q("Status"), this.q);
        this.q.q(eb.q("Sites"), this.q);
        if (a.r) {
            this.q.q(eb.q("Manage"), this.q);
        }
        final Menu menu = new Menu(a.e);
        final MenuBar menuBar = new MenuBar();
        menu.add(this.q = new MenuItem(eb.q("Set Server Options...")));
        menu.add(this.r = new MenuItem(eb.q("Send Chat Broadcast...")));
        menu.add(this.t = new MenuItem(eb.q("Change Password...")));
        menu.add(this.u = new MenuItem(eb.q("Change Commander Password...")));
        if (!q.y) {
            menu.addSeparator();
            menu.add(this.y = new MenuItem(eb.q("New Login Window")));
            menu.addSeparator();
            menu.add(this.w = new MenuItem(eb.q("Disconnect")));
            menu.add(this.e = new MenuItem(eb.q("Quit"), new MenuShortcut(81)));
        }
        menuBar.add(menu);
        this.setMenuBar(menuBar);
        this.pack();
        this.a_();
    }
    
    public final Dimension getPreferredSize() {
        return new Dimension(546, 395);
    }
    
    public final Dimension getMaximumSize() {
        return this.getPreferredSize();
    }
    
    public final Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
}
