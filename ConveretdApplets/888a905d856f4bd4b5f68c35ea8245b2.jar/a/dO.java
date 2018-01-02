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

public final class dO extends PopupMenu implements eb, ActionListener
{
    private Container q;
    private p q;
    private ap q;
    private aG q;
    private boolean q;
    
    public dO(final Container q, final ap q2) {
        super("UserNickPanel");
        this.q = false;
        this.q = q;
        this.q = q2;
        q.add(this);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand;
        if ((actionCommand = actionEvent.getActionCommand()).equals(dX.E)) {
            this.q.y(this.q.s);
            return;
        }
        if (actionCommand.equals(dX.R)) {
            this.q.q.q(this.q);
            return;
        }
        if (actionCommand.equals(dX.T)) {
            this.q.q(this.q);
            return;
        }
        if (actionCommand.equals(dX.Y)) {
            new J(this.q, this.q);
            return;
        }
        if (actionCommand.equals(dX.D)) {
            this.q.w();
            return;
        }
        if (actionCommand.equals(dX.S)) {
            this.q.q();
            return;
        }
        if (actionCommand.equals(dX.F)) {
            this.q.t(this.q.a);
            return;
        }
        if (actionCommand.equals(dX.U)) {
            this.q.q(B.q(ds.q(")?EK81F5K255>K21>>54K2IKP\\"), this.q.a), false);
            this.q.q(this.q.u, Integer.MAX_VALUE);
            return;
        }
        if (actionCommand.equals(dX.I)) {
            this.q.q(B.q(ds.q(")?EK81F5K255>K21>>54K2IKP\\"), this.q.a), false);
            if (this.q.o == null || this.q.o.length() == 0 || this.q.o.equals("can't get")) {
                return;
            }
            this.q.w(this.q.o, Integer.MAX_VALUE);
        }
        else {
            if (actionCommand.equals(dX.O)) {
                this.q.w(((aJ)ap.x.q((int)(dH.x.q * Math.random()))).w());
                return;
            }
            if (actionCommand.equals(dX.P)) {
                this.q.e(((aJ)ap.x.q((int)(dH.x.q * Math.random()))).w());
                return;
            }
            if (!actionCommand.equals(dX.A)) {
                if (actionCommand.equals(dX.G)) {
                    this.q.o(B.q(-1, 0, this.q.s, "" + this.q.s));
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
        this.q = (p)o;
        if (!(this.q = new aG(this.q, ((bp)o).s))) {
            final MenuItem menuItem;
            this.add(menuItem = new MenuItem(dX.E));
            menuItem.addActionListener(this);
            if ((this.q.q(61) || !this.q.q(61)) && !this.q.r) {
                final MenuItem menuItem2;
                this.add(menuItem2 = new MenuItem(dX.T));
                menuItem2.addActionListener(this);
            }
            if (this.q.q(61) || !this.q.q(61)) {
                final MenuItem menuItem3;
                this.add(menuItem3 = new MenuItem(dX.R));
                menuItem3.addActionListener(this);
            }
            if (this.q.q(61)) {
                this.addSeparator();
            }
            if (this.q.o != null) {
                final MenuItem menuItem4;
                this.add(menuItem4 = new MenuItem(dX.S));
                menuItem4.addActionListener(this);
            }
            if (this.q.q(9)) {
                final MenuItem menuItem5;
                this.add(menuItem5 = new MenuItem(dX.D));
                menuItem5.addActionListener(this);
                this.addSeparator();
            }
            if ((this.q.q(44) || this.q.q(52)) && !this.q.a.equalsIgnoreCase("Chatmaster")) {
                final MenuItem menuItem6;
                this.add(menuItem6 = new MenuItem(dX.Y));
                menuItem6.addActionListener(this);
            }
            if (this.q.q(11)) {
                final MenuItem menuItem7;
                this.add(menuItem7 = new MenuItem(dX.F));
                menuItem7.addActionListener(this);
                this.addSeparator();
            }
            if (this.q.q(49) || this.q.q(39)) {
                if (this.q.q(49)) {
                    final MenuItem menuItem8;
                    this.add(menuItem8 = new MenuItem(dX.U));
                    menuItem8.addActionListener(this);
                }
                final MenuItem menuItem9;
                this.add(menuItem9 = new MenuItem(dX.I));
                menuItem9.addActionListener(this);
                this.addSeparator();
            }
            if ((this.q.q(3) && this.q.s == this.q.s) || this.q.q(5)) {
                final MenuItem menuItem10;
                this.add(menuItem10 = new MenuItem(dX.O));
                menuItem10.addActionListener(this);
            }
            if (this.q.q(18) && !this.q.q(61)) {
                final MenuItem menuItem11;
                this.add(menuItem11 = new MenuItem(dX.P));
                menuItem11.addActionListener(this);
                this.addSeparator();
            }
            if (this.q.q(6)) {
                final MenuItem menuItem12;
                this.add(menuItem12 = new MenuItem(dX.A));
                menuItem12.addActionListener(this);
            }
            this.q = true;
        }
        if ((dN.q() && !bC.w.y()) || (dN.w() && !bC.w.i())) {
            this.show(this.q, n, n2 + 20);
        }
    }
    
    public final void q() {
    }
}
