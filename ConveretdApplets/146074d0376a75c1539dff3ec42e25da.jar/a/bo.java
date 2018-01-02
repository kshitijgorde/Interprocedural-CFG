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

public final class bo extends PopupMenu implements bh, ActionListener
{
    private Container q;
    private cz q;
    private cV q;
    private dP q;
    private boolean q;
    
    public bo(final Container q, final cV q2) {
        super("UserNickPanel");
        this.q = false;
        this.q = q;
        this.q = q2;
        q.add(this);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand;
        if ((actionCommand = actionEvent.getActionCommand()).equals(aJ.R)) {
            this.q.e(this.q.q());
            return;
        }
        if (actionCommand.equals(aJ.T)) {
            this.q.q.q(this.q);
            return;
        }
        if (actionCommand.equals(aJ.Y)) {
            this.q.q(this.q);
            return;
        }
        if (actionCommand.equals(aJ.U)) {
            new af(this.q, this.q);
            return;
        }
        if (actionCommand.equals(aJ.G)) {
            this.q.q("/AllIP");
            return;
        }
        if (actionCommand.equals(aJ.F)) {
            this.q.q("/Ping");
            return;
        }
        if (actionCommand.equals(aJ.H)) {
            this.q.e(this.q.getName());
            return;
        }
        if (actionCommand.equals(aJ.I)) {
            this.q.q(ec.q(dV.q(")?EK81F5K255>K21>>54K2IKP\\"), this.q.getName()), false);
            this.q.q(this.q.i, Integer.MAX_VALUE);
            return;
        }
        if (actionCommand.equals(aJ.P)) {
            this.q.q(ec.q(dV.q(")?EK81F5K255>K21>>54K2IKP\\"), this.q.getName()), false);
            if (this.q.p == null || this.q.p.length() == 0 || this.q.p.equals("can't get")) {
                return;
            }
            this.q.w(this.q.p, Integer.MAX_VALUE);
        }
        else {
            if (actionCommand.equals(aJ.A)) {
                final int n = (int)(cU.f.q() * Math.random());
                final dP q = this.q;
                final cV q2 = this.q;
                q.q(((ce)cV.f.q(n)).y());
                return;
            }
            if (actionCommand.equals(aJ.S)) {
                final int n2 = (int)(cU.f.q() * Math.random());
                final dP q3 = this.q;
                final cV q4 = this.q;
                q3.w(((ce)cV.f.q(n2)).y());
                return;
            }
            if (!actionCommand.equals(aJ.D)) {
                if (actionCommand.equals(aJ.J)) {
                    this.q.q(dH.q(-1, 0, this.q.q(), "" + this.q.q()));
                }
                return;
            }
            if (this.q.t()) {
                this.q.w("disable");
                return;
            }
            this.q.w("enable");
        }
    }
    
    public final void q(final int n, final int n2, final Object o) {
        this.q = (cz)o;
        if (!(this.q = new dP(this.q, ((bZ)o).q()))) {
            final MenuItem menuItem;
            this.add(menuItem = new MenuItem(aJ.R));
            menuItem.addActionListener(this);
            if ((this.q.a_() || !this.q.a_()) && !this.q.t) {
                final MenuItem menuItem2;
                this.add(menuItem2 = new MenuItem(aJ.Y));
                menuItem2.addActionListener(this);
            }
            if (this.q.a_() || !this.q.a_()) {
                final MenuItem menuItem3;
                this.add(menuItem3 = new MenuItem(aJ.T));
                menuItem3.addActionListener(this);
            }
            if (this.q.a_()) {
                this.addSeparator();
            }
            if (this.q.p != null) {
                final MenuItem menuItem4;
                this.add(menuItem4 = new MenuItem(aJ.F));
                menuItem4.addActionListener(this);
            }
            if (this.q.q(9)) {
                final MenuItem menuItem5;
                this.add(menuItem5 = new MenuItem(aJ.G));
                menuItem5.addActionListener(this);
                this.addSeparator();
            }
            if ((this.q.q(44) || this.q.q(52)) && !this.q.getName().equalsIgnoreCase("Chatmaster")) {
                final MenuItem menuItem6;
                this.add(menuItem6 = new MenuItem(aJ.U));
                menuItem6.addActionListener(this);
            }
            if (this.q.q(11)) {
                final MenuItem menuItem7;
                this.add(menuItem7 = new MenuItem(aJ.H));
                menuItem7.addActionListener(this);
                this.addSeparator();
            }
            if (this.q.q(49) || this.q.q(39)) {
                if (this.q.q(49)) {
                    final MenuItem menuItem8;
                    this.add(menuItem8 = new MenuItem(aJ.I));
                    menuItem8.addActionListener(this);
                }
                final MenuItem menuItem9;
                this.add(menuItem9 = new MenuItem(aJ.P));
                menuItem9.addActionListener(this);
                this.addSeparator();
            }
            if ((this.q.q(3) && this.q.q() == this.q.q()) || this.q.q(5)) {
                final MenuItem menuItem10;
                this.add(menuItem10 = new MenuItem(aJ.A));
                menuItem10.addActionListener(this);
            }
            if (this.q.q(18) && !this.q.a_()) {
                final MenuItem menuItem11;
                this.add(menuItem11 = new MenuItem(aJ.S));
                menuItem11.addActionListener(this);
                this.addSeparator();
            }
            if (this.q.q(6)) {
                final MenuItem menuItem12;
                this.add(menuItem12 = new MenuItem(aJ.D));
                menuItem12.addActionListener(this);
            }
            this.q = true;
        }
        if ((a.q() && !cf.w.y()) || (a.w() && !cf.w.i())) {
            this.show(this.q, n, n2 + 20);
        }
    }
    
    public final void q() {
    }
}
