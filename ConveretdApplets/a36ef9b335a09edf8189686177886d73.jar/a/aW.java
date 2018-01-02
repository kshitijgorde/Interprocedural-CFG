// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.PopupMenu;

public final class aW extends PopupMenu implements aQ, ActionListener
{
    private Container q;
    private bp q;
    private bI q;
    private cj q;
    private boolean q;
    
    public aW(final Container q, final bI q2) {
        super("UserNickPanel");
        this.q = false;
        this.q = q;
        this.q = q2;
        q.add(this);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand;
        if ((actionCommand = actionEvent.getActionCommand()).equals(au.f)) {
            this.q.e(this.q.q());
            return;
        }
        if (actionCommand.equals(au.g)) {
            this.q.q.q(this.q);
            return;
        }
        if (actionCommand.equals(au.h)) {
            this.q.q(this.q);
            return;
        }
        if (actionCommand.equals(au.j)) {
            new U(this.q, this.q);
            return;
        }
        if (actionCommand.equals(au.b)) {
            this.q.q("/AllIP");
            return;
        }
        if (actionCommand.equals(au.v)) {
            this.q.q("/Ping");
            return;
        }
        if (actionCommand.equals(au.n)) {
            this.q.e(this.q.getName());
            return;
        }
        if (actionCommand.equals(au.k)) {
            this.q.q(cv.q(cl.q(")?EK81F5K255>K21>>54K2IKP\\"), this.q.getName()), false);
            this.q.q(this.q.u, Integer.MAX_VALUE);
            return;
        }
        if (actionCommand.equals(au.l)) {
            this.q.q(cv.q(cl.q(")?EK81F5K255>K21>>54K2IKP\\"), this.q.getName()), false);
            if (this.q.o == null || this.q.o.length() == 0 || this.q.o.equals("can't get")) {
                return;
            }
            this.q.w(this.q.o, Integer.MAX_VALUE);
        }
        else {
            if (actionCommand.equals(au.z)) {
                final int n = (int)(bH.d.q() * Math.random());
                final cj q = this.q;
                final bI q2 = this.q;
                q.q(((bd)bI.d.q(n)).r());
                return;
            }
            if (actionCommand.equals(au.x)) {
                final int n2 = (int)(bH.d.q() * Math.random());
                final cj q3 = this.q;
                final bI q4 = this.q;
                q3.w(((bd)bI.d.q(n2)).r());
                return;
            }
            if (!actionCommand.equals(au.c)) {
                if (actionCommand.equals(au.m)) {
                    this.q.q(cb.q(-1, 0, this.q.q(), "" + this.q.q()));
                }
                return;
            }
            if (this.q.e()) {
                this.q.w("disable");
                return;
            }
            this.q.w("enable");
        }
    }
    
    public final void q(final int n, final int n2, final Object o) {
        this.q = (bp)o;
        if (!(this.q = new cj(this.q, ((ba)o).q()))) {
            final MenuItem menuItem;
            this.add(menuItem = new MenuItem(au.f));
            menuItem.addActionListener(this);
            if ((this.q.a_() || !this.q.a_()) && !this.q.r) {
                final MenuItem menuItem2;
                this.add(menuItem2 = new MenuItem(au.h));
                menuItem2.addActionListener(this);
            }
            if (this.q.a_() || !this.q.a_()) {
                final MenuItem menuItem3;
                this.add(menuItem3 = new MenuItem(au.g));
                menuItem3.addActionListener(this);
            }
            if (this.q.a_()) {
                this.addSeparator();
            }
            if (this.q.o != null) {
                final MenuItem menuItem4;
                this.add(menuItem4 = new MenuItem(au.v));
                menuItem4.addActionListener(this);
            }
            if (this.q.q(9)) {
                final MenuItem menuItem5;
                this.add(menuItem5 = new MenuItem(au.b));
                menuItem5.addActionListener(this);
                this.addSeparator();
            }
            if ((this.q.q(44) || this.q.q(52)) && !this.q.getName().equalsIgnoreCase("Chatmaster")) {
                final MenuItem menuItem6;
                this.add(menuItem6 = new MenuItem(au.j));
                menuItem6.addActionListener(this);
            }
            if (this.q.q(11)) {
                final MenuItem menuItem7;
                this.add(menuItem7 = new MenuItem(au.n));
                menuItem7.addActionListener(this);
                this.addSeparator();
            }
            if (this.q.q(49) || this.q.q(39)) {
                if (this.q.q(49)) {
                    final MenuItem menuItem8;
                    this.add(menuItem8 = new MenuItem(au.k));
                    menuItem8.addActionListener(this);
                }
                final MenuItem menuItem9;
                this.add(menuItem9 = new MenuItem(au.l));
                menuItem9.addActionListener(this);
                this.addSeparator();
            }
            if ((this.q.q(3) && this.q.q() == this.q.q()) || this.q.q(5)) {
                final MenuItem menuItem10;
                this.add(menuItem10 = new MenuItem(au.z));
                menuItem10.addActionListener(this);
            }
            if (this.q.q(18) && !this.q.a_()) {
                final MenuItem menuItem11;
                this.add(menuItem11 = new MenuItem(au.x));
                menuItem11.addActionListener(this);
                this.addSeparator();
            }
            if (this.q.q(6)) {
                final MenuItem menuItem12;
                this.add(menuItem12 = new MenuItem(au.c));
                menuItem12.addActionListener(this);
            }
            this.q = true;
        }
        if ((a.q() && !be.w.y()) || (a.w() && !be.w.i())) {
            this.show(this.q, n, n2 + 20);
        }
    }
    
    public final void q() {
    }
}
