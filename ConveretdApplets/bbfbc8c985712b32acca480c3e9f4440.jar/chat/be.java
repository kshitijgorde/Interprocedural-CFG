// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.MenuBar;
import java.awt.Menu;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.Date;
import java.awt.Event;
import java.awt.MenuItem;

public final class be extends aC
{
    private bM a;
    bH a;
    private h a;
    m a;
    private MenuItem a;
    private MenuItem b;
    private MenuItem c;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                this.dispose();
                this.a.j();
                return true;
            }
            case 1001: {
                if (event.target == null) {
                    this.a.j();
                }
                else if (event.target == null) {
                    this.a.g();
                }
                else if (event.target == this.a) {
                    this.a.d();
                }
                else if (event.target == this.b) {
                    this.a.e();
                }
                else {
                    if (event.target != this.c) {
                        return super.handleEvent(event);
                    }
                    this.a.a(false);
                }
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public final void a(final aK ak) {
        synchronized (this.a.a) {
            final int a;
            if ((a = this.a.a.a((a)ak)) == -1) {
                this.a.a.c(ak);
            }
            else {
                this.a.a.a(ak, a);
            }
        }
    }
    
    public final void b() {
        this.a.a.a(String.valueOf(this.a.l));
        this.a.f.a(String.valueOf(this.a.m));
        this.a.b.a(String.valueOf(this.a.g));
        this.a.g.a(String.valueOf(this.a.h));
        this.a.c.a(String.valueOf(this.a.k));
        if (this.a.e != null) {
            this.a.a.a(this.a.e);
        }
        if (this.a.c != 0L) {
            this.a.d.a(p.a.format(new Date(this.a.c)));
        }
        this.a.e.a(p.a.format(new Date(this.a.a)));
    }
    
    public be(final bH a) {
        super(a.b() + " Admin");
        new cr(aS.a(185), 150, 20);
        new cr(aS.a(189), 150, 20);
        new cr(aS.a(186), 150, 20);
        new cr(aS.a(140), 150, 20);
        this.a = a;
        this.a = new bM(a);
        this.setLayout(new BorderLayout());
        this.add("Center", this.a);
        this.a = new h(this);
        ((bM)(this.a = new m(this))).a(aS.a(28), this.a);
        this.a.a(aS.a(107), this.a);
        this.pack();
        this.a();
        final Menu menu = new Menu(a.b());
        final MenuBar menuBar = new MenuBar();
        menu.add(this.a = new MenuItem(aS.a(177)));
        menu.add(this.b = new MenuItem(aS.a(178)));
        menu.add(this.c = new MenuItem(aS.a(180)));
        menuBar.add(menu);
        this.setMenuBar(menuBar);
    }
}
