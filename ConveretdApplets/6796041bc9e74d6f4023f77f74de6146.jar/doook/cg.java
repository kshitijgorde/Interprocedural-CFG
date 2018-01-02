// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.MenuShortcut;
import java.awt.MenuBar;
import java.awt.Menu;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.Date;
import java.awt.Event;
import java.awt.Frame;
import java.awt.MenuItem;

public class cg extends aO
{
    private cr c;
    private aS b;
    private aS c;
    private aS d;
    private aS r;
    private cI b;
    private bJ a;
    private bI a;
    private long m;
    private MenuItem c;
    private MenuItem d;
    private MenuItem e;
    private MenuItem f;
    private MenuItem g;
    private MenuItem h;
    private MenuItem i;
    
    public void a(final boolean b) {
        new bB(this, this.b, b).setVisible(true);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                this.dispose();
                this.b.i();
                return true;
            }
            case 1001: {
                if (event.target == this.d) {
                    this.b.i();
                }
                else if (event.target == this.e) {
                    this.b.j();
                }
                else if (event.target == this.c) {
                    this.b.b();
                }
                else if (event.target == this.f) {
                    this.b.s();
                }
                else if (event.target == this.g) {
                    this.b.a(false);
                }
                else if (event.target == this.i) {
                    this.a(false);
                }
                else {
                    if (event.target != this.h) {
                        return super.handleEvent(event);
                    }
                    new cI();
                }
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public boolean a(final bZ bz) {
        return this.a.f.a((aU)bz);
    }
    
    public void a(final bZ bz) {
        synchronized (this.a.f) {
            final int a = this.a.f.a((aU)bz);
            if (a == -1) {
                this.a.f.c(bz);
            }
            else {
                this.a.f.b(bz, a);
            }
        }
    }
    
    public void a() {
        this.a.b.setText(String.valueOf(this.b.n));
        this.a.h.setText(String.valueOf(this.b.aG));
        this.a.c.setText(String.valueOf(this.b.ae));
        this.a.d.setText(String.valueOf(this.b.af));
        if (this.b.aa != null) {
            this.a.b.a(this.b.aa);
        }
        if (this.b.u != 0L) {
            this.a.f.setText(aF.a.format(new Date(this.b.u)));
        }
        this.a.g.setText(aF.a.format(new Date(this.b.t)));
    }
    
    static cI a(final cg cg) {
        return cg.b;
    }
    
    public cg(final cI b) {
        super(z.G + " Admin");
        this.b = new aS(ao.e("Disconnect"), 150, 20);
        this.c = new aS(ao.e("Set Server Options"), 150, 20);
        this.d = new aS(ao.e("Quit"), 150, 20);
        this.r = new aS(ao.e("Send Chat Broadcast"), 150, 20);
        this.m = 0L;
        this.b = b;
        this.c = new cr(2, 10, 2, 10, 1, b);
        this.setLayout(new BorderLayout());
        this.add("Center", this.c);
        this.a = new bJ(this);
        this.a = new bI(this);
        this.c.a(ao.e("Status"), this.a);
        this.c.a(ao.e("Sites"), this.a);
        this.pack();
        this.c();
        final Menu menu = new Menu(z.G);
        final MenuBar menuBar = new MenuBar();
        menu.add(this.c = new MenuItem(ao.e("Set Server Options...")));
        menu.add(this.f = new MenuItem(ao.e("Send Chat Broadcast...")));
        menu.add(this.g = new MenuItem(ao.e("Change Password...")));
        menu.add(this.i = new MenuItem(ao.e("Change Commander Password...")));
        if (!b.h) {
            menu.addSeparator();
            menu.add(this.h = new MenuItem(ao.e("New Login Window")));
            menu.addSeparator();
            menu.add(this.d = new MenuItem(ao.e("Disconnect")));
            menu.add(this.e = new MenuItem(ao.e("Quit"), new MenuShortcut(81)));
        }
        menuBar.add(menu);
        this.setMenuBar(menuBar);
    }
}
