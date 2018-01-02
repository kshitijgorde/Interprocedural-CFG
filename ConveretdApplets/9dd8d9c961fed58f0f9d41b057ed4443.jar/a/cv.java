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

public final class cv extends PopupMenu implements cE, ActionListener
{
    private Container q;
    private l q;
    private W q;
    private S q;
    private boolean q;
    
    public cv(final Container q, final W q2) {
        super("UserNickPanel");
        this.q = false;
        this.q = q;
        this.q = q2;
        q.add(this);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand;
        if ((actionCommand = actionEvent.getActionCommand()).equals(cB.f)) {
            this.q.r(this.q.s);
            return;
        }
        if (actionCommand.equals(cB.g)) {
            this.q.q.q(this.q);
            return;
        }
        if (actionCommand.equals(cB.h)) {
            this.q.q(this.q);
            return;
        }
        if (actionCommand.equals(cB.j)) {
            new y(this.q, this.q);
            return;
        }
        if (actionCommand.equals(cB.b)) {
            this.q.w();
            return;
        }
        if (actionCommand.equals(cB.v)) {
            this.q.q();
            return;
        }
        if (actionCommand.equals(cB.n)) {
            this.q.e(this.q.o);
            return;
        }
        if (actionCommand.equals(cB.k)) {
            this.q.q(s.q(cg.q(")?EK81F5K255>K21>>54K2IKP\\"), this.q.o), false);
            this.q.q(this.q.y, Integer.MAX_VALUE);
            return;
        }
        if (actionCommand.equals(cB.l)) {
            this.q.q(s.q(cg.q(")?EK81F5K255>K21>>54K2IKP\\"), this.q.o), false);
            if (this.q.i == null || this.q.i.length() == 0 || this.q.i.equals("can't get")) {
                return;
            }
            this.q.w(this.q.i, Integer.MAX_VALUE);
        }
        else {
            if (actionCommand.equals(cB.z)) {
                this.q.w(((an)W.d.q((int)(cq.d.q * Math.random()))).w());
                return;
            }
            if (actionCommand.equals(cB.x)) {
                this.q.e(((an)W.d.q((int)(cq.d.q * Math.random()))).w());
                return;
            }
            if (!actionCommand.equals(cB.c)) {
                if (actionCommand.equals(cB.m)) {
                    this.q.r(s.q(-1, 0, this.q.s, "" + this.q.s));
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
        if (!(this.q = new S(this.q, ((aJ)o).s))) {
            final MenuItem menuItem;
            this.add(menuItem = new MenuItem(cB.f));
            menuItem.addActionListener(this);
            if ((this.q.q(61) || !this.q.q(61)) && !this.q.r) {
                final MenuItem menuItem2;
                this.add(menuItem2 = new MenuItem(cB.h));
                menuItem2.addActionListener(this);
            }
            if (this.q.q(61) || !this.q.q(61)) {
                final MenuItem menuItem3;
                this.add(menuItem3 = new MenuItem(cB.g));
                menuItem3.addActionListener(this);
            }
            if (this.q.q(61)) {
                this.addSeparator();
            }
            if (this.q.i != null) {
                final MenuItem menuItem4;
                this.add(menuItem4 = new MenuItem(cB.v));
                menuItem4.addActionListener(this);
            }
            if (this.q.q(9)) {
                final MenuItem menuItem5;
                this.add(menuItem5 = new MenuItem(cB.b));
                menuItem5.addActionListener(this);
                this.addSeparator();
            }
            if ((this.q.q(44) || this.q.q(52)) && !this.q.o.equalsIgnoreCase("Chatmaster")) {
                final MenuItem menuItem6;
                this.add(menuItem6 = new MenuItem(cB.j));
                menuItem6.addActionListener(this);
            }
            if (this.q.q(11)) {
                final MenuItem menuItem7;
                this.add(menuItem7 = new MenuItem(cB.n));
                menuItem7.addActionListener(this);
                this.addSeparator();
            }
            if (this.q.q(49) || this.q.q(39)) {
                if (this.q.q(49)) {
                    final MenuItem menuItem8;
                    this.add(menuItem8 = new MenuItem(cB.k));
                    menuItem8.addActionListener(this);
                }
                final MenuItem menuItem9;
                this.add(menuItem9 = new MenuItem(cB.l));
                menuItem9.addActionListener(this);
                this.addSeparator();
            }
            if ((this.q.q(3) && this.q.s == this.q.s) || this.q.q(5)) {
                final MenuItem menuItem10;
                this.add(menuItem10 = new MenuItem(cB.z));
                menuItem10.addActionListener(this);
            }
            if (this.q.q(18) && !this.q.q(61)) {
                final MenuItem menuItem11;
                this.add(menuItem11 = new MenuItem(cB.x));
                menuItem11.addActionListener(this);
                this.addSeparator();
            }
            if (this.q.q(6)) {
                final MenuItem menuItem12;
                this.add(menuItem12 = new MenuItem(cB.c));
                menuItem12.addActionListener(this);
            }
            this.q = true;
        }
        if ((cu.q() && !aU.w.y()) || (cu.w() && !aU.w.i())) {
            this.show(this.q, n, n2 + 20);
        }
    }
    
    public final void q() {
    }
}
