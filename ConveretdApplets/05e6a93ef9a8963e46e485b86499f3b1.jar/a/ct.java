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

public final class ct extends PopupMenu implements cB, ActionListener
{
    private Container q;
    private l q;
    private W q;
    private T q;
    private boolean q;
    
    public ct(final Container q, final W q2) {
        super("UserNickPanel");
        this.q = false;
        this.q = q;
        this.q = q2;
        q.add(this);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand;
        if ((actionCommand = actionEvent.getActionCommand()).equals(cy.f)) {
            this.q.r(this.q.a);
            return;
        }
        if (actionCommand.equals(cy.g)) {
            this.q.q.q(this.q);
            return;
        }
        if (actionCommand.equals(cy.h)) {
            this.q.q(this.q);
            return;
        }
        if (actionCommand.equals(cy.j)) {
            new z(this.q, this.q);
            return;
        }
        if (actionCommand.equals(cy.b)) {
            this.q.w();
            return;
        }
        if (actionCommand.equals(cy.v)) {
            this.q.q();
            return;
        }
        if (actionCommand.equals(cy.n)) {
            this.q.e(this.q.o);
            return;
        }
        if (actionCommand.equals(cy.k)) {
            this.q.q(t.q(ce.q(")?EK81F5K255>K21>>54K2IKP\\"), this.q.o), false);
            this.q.q(this.q.y, Integer.MAX_VALUE);
            return;
        }
        if (actionCommand.equals(cy.l)) {
            this.q.q(t.q(ce.q(")?EK81F5K255>K21>>54K2IKP\\"), this.q.o), false);
            if (this.q.i == null || this.q.i.length() == 0 || this.q.i.equals("can't get")) {
                return;
            }
            this.q.w(this.q.i, Integer.MAX_VALUE);
        }
        else {
            if (actionCommand.equals(cy.z)) {
                this.q.w(((ao)W.d.q((int)(co.d.q * Math.random()))).w());
                return;
            }
            if (actionCommand.equals(cy.x)) {
                this.q.e(((ao)W.d.q((int)(co.d.q * Math.random()))).w());
                return;
            }
            if (!actionCommand.equals(cy.c)) {
                if (actionCommand.equals(cy.m)) {
                    this.q.r(t.q(-1, 0, this.q.a, "" + this.q.a));
                }
                return;
            }
            if (this.q.q()) {
                this.q.w("disable");
                return;
            }
            this.q.w("enable");
        }
    }
    
    public final void q(final int n, final int n2, final Object o) {
        this.q = (l)o;
        if (!(this.q = new T(this.q, ((aK)o).a))) {
            final MenuItem menuItem;
            this.add(menuItem = new MenuItem(cy.f));
            menuItem.addActionListener(this);
            if ((this.q.q(61) || !this.q.q(61)) && !this.q.r) {
                final MenuItem menuItem2;
                this.add(menuItem2 = new MenuItem(cy.h));
                menuItem2.addActionListener(this);
            }
            if (this.q.q(61) || !this.q.q(61)) {
                final MenuItem menuItem3;
                this.add(menuItem3 = new MenuItem(cy.g));
                menuItem3.addActionListener(this);
            }
            if (this.q.q(61)) {
                this.addSeparator();
            }
            if (this.q.i != null) {
                final MenuItem menuItem4;
                this.add(menuItem4 = new MenuItem(cy.v));
                menuItem4.addActionListener(this);
            }
            if (this.q.q(9)) {
                final MenuItem menuItem5;
                this.add(menuItem5 = new MenuItem(cy.b));
                menuItem5.addActionListener(this);
                this.addSeparator();
            }
            if ((this.q.q(44) || this.q.q(52)) && !this.q.o.equalsIgnoreCase("Chatmaster")) {
                final MenuItem menuItem6;
                this.add(menuItem6 = new MenuItem(cy.j));
                menuItem6.addActionListener(this);
            }
            if (this.q.q(11)) {
                final MenuItem menuItem7;
                this.add(menuItem7 = new MenuItem(cy.n));
                menuItem7.addActionListener(this);
                this.addSeparator();
            }
            if (this.q.q(49) || this.q.q(39)) {
                if (this.q.q(49)) {
                    final MenuItem menuItem8;
                    this.add(menuItem8 = new MenuItem(cy.k));
                    menuItem8.addActionListener(this);
                }
                final MenuItem menuItem9;
                this.add(menuItem9 = new MenuItem(cy.l));
                menuItem9.addActionListener(this);
                this.addSeparator();
            }
            if ((this.q.q(3) && this.q.a == this.q.a) || this.q.q(5)) {
                final MenuItem menuItem10;
                this.add(menuItem10 = new MenuItem(cy.z));
                menuItem10.addActionListener(this);
            }
            if (this.q.q(18) && !this.q.q(61)) {
                final MenuItem menuItem11;
                this.add(menuItem11 = new MenuItem(cy.x));
                menuItem11.addActionListener(this);
                this.addSeparator();
            }
            if (this.q.q(6)) {
                final MenuItem menuItem12;
                this.add(menuItem12 = new MenuItem(cy.c));
                menuItem12.addActionListener(this);
            }
            this.q = true;
        }
        if ((cs.q() && !aT.w.y()) || (cs.w() && !aT.w.i())) {
            this.show(this.q, n, n2 + 20);
        }
    }
    
    public final void q() {
    }
}
