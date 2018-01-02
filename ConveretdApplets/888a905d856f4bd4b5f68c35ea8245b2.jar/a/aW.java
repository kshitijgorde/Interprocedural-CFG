// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Enumeration;
import java.awt.event.ItemEvent;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.CheckboxMenuItem;
import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.MenuBar;
import java.util.Hashtable;
import java.awt.Frame;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;

public final class aW implements ch, ActionListener, ItemListener
{
    private ap q;
    private Frame q;
    private Hashtable q;
    private Hashtable w;
    
    public aW(final ap q) {
        this.q = new Hashtable();
        this.w = new Hashtable();
        this.q = q;
    }
    
    public final MenuBar q(final Frame q) {
        this.q = q;
        final Menu menu = new Menu(dN.e);
        final MenuBar menuBar = new MenuBar();
        for (int i = 1; i <= this.q.l.q; ++i) {
            menu.add((MenuItem)this.q.l.w(i));
            menu.addActionListener(this);
        }
        if (this.q.l.q > 0) {
            menu.addSeparator();
        }
        final MenuItem menuItem;
        (menuItem = new MenuItem(dX.q)).addActionListener(this);
        menu.add(menuItem);
        menu.addSeparator();
        if ((this.q.w() & 0x3F2C00004000004L) != 0x0L) {
            final MenuItem menuItem2;
            (menuItem2 = new MenuItem(dX.o)).addActionListener(this);
            menu.add(menuItem2);
            menu.addSeparator();
        }
        final Menu menu2 = new Menu(dX.e);
        for (int j = 0; j < 6; ++j) {
            final String s = cS.q()[j];
            final CheckboxMenuItem checkboxMenuItem;
            (checkboxMenuItem = new CheckboxMenuItem(s)).addItemListener(this);
            menu2.add(checkboxMenuItem);
            this.w.put(s, checkboxMenuItem);
        }
        menu.add(menu2);
        menu.addSeparator();
        if (this.q.q(24) || this.q.q(32)) {
            boolean b = true;
            final Menu menu3 = new Menu(dX.t);
            if (this.q.q(32)) {
                final CheckboxMenuItem checkboxMenuItem2;
                (checkboxMenuItem2 = new CheckboxMenuItem(dX.y)).addItemListener(this);
                menu3.add(checkboxMenuItem2);
                if (this.q.q(25)) {
                    checkboxMenuItem2.setState(true);
                    b = false;
                }
                this.q.put(dX.y, checkboxMenuItem2);
            }
            if (this.q.q(24)) {
                final CheckboxMenuItem checkboxMenuItem3;
                (checkboxMenuItem3 = new CheckboxMenuItem(dX.u)).addItemListener(this);
                menu3.add(checkboxMenuItem3);
                if (this.q.q(23)) {
                    checkboxMenuItem3.setState(true);
                    b = false;
                }
                this.q.put(dX.u, checkboxMenuItem3);
            }
            final CheckboxMenuItem checkboxMenuItem4;
            (checkboxMenuItem4 = new CheckboxMenuItem(dX.i)).addItemListener(this);
            menu3.add(checkboxMenuItem4);
            if (b) {
                checkboxMenuItem4.setState(true);
            }
            this.q.put(dX.i, checkboxMenuItem4);
            menu.add(menu3);
            menu.addSeparator();
        }
        if (!bC.w.p()) {
            final MenuItem menuItem3;
            (menuItem3 = new MenuItem(dX.c)).addActionListener(this);
            menu.add(menuItem3);
        }
        if (bC.w.r()) {
            if (this.q.q(15)) {
                final MenuItem menuItem4;
                (menuItem4 = new MenuItem(dX.n)).addActionListener(this);
                menu.add(menuItem4);
            }
            if (!bC.w.f()) {
                final MenuItem menuItem5;
                (menuItem5 = new MenuItem(dX.m)).addActionListener(this);
                menu.add(menuItem5);
            }
        }
        menu.addSeparator();
        if ("Admin".equals(ap.f) && (this.q.w() & 0xC000000000000L) != 0x0L) {
            final MenuItem menuItem6;
            (menuItem6 = new MenuItem(dX.p)).addActionListener(this);
            menu.add(menuItem6);
        }
        if (this.q.q(42)) {
            final MenuItem menuItem7;
            (menuItem7 = new MenuItem(dX.a)).addActionListener(this);
            menu.add(menuItem7);
        }
        final MenuItem menuItem8;
        (menuItem8 = new MenuItem(dX.v)).addActionListener(this);
        menu.add(menuItem8);
        if (bC.w.r() && !bC.w.d()) {
            menu.add(this.q());
        }
        if (this.q.q(52)) {
            final MenuItem menuItem9;
            (menuItem9 = new MenuItem(dX.d)).addActionListener(this);
            menu.add(menuItem9);
        }
        final CheckboxMenuItem checkboxMenuItem5;
        (checkboxMenuItem5 = new CheckboxMenuItem(dX.f)).addItemListener(this);
        menu.add(checkboxMenuItem5);
        if (this.q.q(52)) {
            final MenuItem menuItem10;
            (menuItem10 = new MenuItem(dX.g)).addActionListener(this);
            menu.add(menuItem10);
        }
        if (this.q.q(45)) {
            final MenuItem menuItem11;
            (menuItem11 = new MenuItem(dX.s)).addActionListener(this);
            menu.add(menuItem11);
        }
        if (this.q.q(13)) {
            final MenuItem menuItem12;
            (menuItem12 = new MenuItem(dX.h)).addActionListener(this);
            menu.add(menuItem12);
        }
        if (this.q.q(59) || this.q.q(60)) {
            final MenuItem menuItem13 = bj.q = new MenuItem(this.q.q(59) ? dX.j : dX.k);
            menuItem13.addActionListener(this);
            menu.add(menuItem13);
        }
        menu.addSeparator();
        final MenuItem menuItem14;
        (menuItem14 = new MenuItem(dX.w)).addActionListener(this);
        menu.add(menuItem14);
        final MenuItem menuItem15;
        (menuItem15 = new MenuItem(dX.r)).addActionListener(this);
        menu.add(menuItem15);
        if (!this.q.y) {
            final MenuItem menuItem16;
            (menuItem16 = new MenuItem(dX.l)).addActionListener(this);
            menu.add(menuItem16);
            final MenuItem menuItem17;
            (menuItem17 = new MenuItem(dX.z)).addActionListener(this);
            menu.add(menuItem17);
            final MenuItem menuItem18;
            (menuItem18 = new MenuItem(dX.x)).addActionListener(this);
            menu.add(menuItem18);
        }
        menuBar.add(menu);
        if (!bC.w.r()) {
            if (!bC.w.d()) {
                menuBar.add(this.q());
            }
            if (this.q.q(15)) {
                final Menu menu4 = new Menu(dX.n);
                final MenuItem menuItem19;
                (menuItem19 = new MenuItem(dX.n)).addActionListener(this);
                menu4.add(menuItem19);
                menuBar.add(menu4);
            }
            if (!bC.w.f()) {
                final Menu menu5 = new Menu(dX.m);
                final MenuItem menuItem20;
                (menuItem20 = new MenuItem(dX.m)).addActionListener(this);
                menu5.add(menuItem20);
                menuBar.add(menu5);
            }
        }
        return menuBar;
    }
    
    private Menu q() {
        final Menu menu = new Menu(dX.b);
        for (int i = 0; i < this.q.m.q; ++i) {
            final MenuItem menuItem;
            (menuItem = new MenuItem(((bN)this.q.m.q(i)).a)).addActionListener(this);
            menu.add(menuItem);
        }
        return menu;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand;
        if ((actionCommand = actionEvent.getActionCommand()).equals(dX.z)) {
            this.q.u();
            return;
        }
        if (actionCommand.equals(dX.x) || actionCommand.equals(dX.r)) {
            this.q.g();
            return;
        }
        if (actionCommand.equals(dX.p)) {
            ((cT)this.q).o();
            return;
        }
        if (actionCommand.equals(dX.o)) {
            ((cT)this.q).d();
            return;
        }
        if (actionCommand.equals(dX.a)) {
            ((cT)this.q).p();
            return;
        }
        if (actionCommand.equals(dX.s)) {
            ((cT)this.q).w(false);
            return;
        }
        if (actionCommand.equals(dX.d)) {
            ((cT)this.q).f();
            return;
        }
        if (actionCommand.equals(dX.l)) {
            new cT();
            return;
        }
        if (actionCommand.equals(dX.g)) {
            ((cT)this.q).a();
            return;
        }
        if (actionCommand.equals(dX.h)) {
            final dd dd;
            (dd = new dd(this.q, be.w("Confirmation"), new String[] { be.w("OK"), be.w("Cancel") }, new String[] { be.w("Restart site?") }, null, this.q)).setVisible(true);
            if (be.w("OK").equals(dd.q)) {
                ((cT)this.q).s();
            }
            return;
        }
        if (actionCommand.equals(dX.j) || actionCommand.equals(dX.k)) {
            if (((cT)this.q).q != null) {
                ((cT)this.q).q.setVisible(true);
            }
        }
        else {
            if (actionCommand.equals(dX.q)) {
                cm.q(new String[] { "window.focus(); setTimeout(\"window.external.AddFavorite('" + m.q().getCodeBase() + "', window.document.title);\", 100);" }, this.q);
                return;
            }
            if (actionCommand.equals(dX.w)) {
                this.q.r(0);
                return;
            }
            if (this.q(actionEvent.getActionCommand()) >= 0) {
                this.q.q((URL)this.q.z.q(this.q(actionEvent.getActionCommand())), "_blank");
                return;
            }
            if (actionCommand.equals(dX.n)) {
                new dU(this.q, (cT)this.q).setVisible(true);
                return;
            }
            if (actionCommand.equals(dX.c)) {
                this.q.q("", -1, 4);
                return;
            }
            if (actionCommand.equals(dX.v)) {
                new aM(this.q, this.q).setVisible(true);
                return;
            }
            if (actionCommand.equals(dX.m)) {
                new z(this.q, (cT)this.q).setVisible(true);
                return;
            }
            this.q(actionCommand);
        }
    }
    
    private void q(final String s) {
        for (int i = 0; i < this.q.m.q; ++i) {
            final bN bn = (bN)this.q.m.q(i);
            if (s.equalsIgnoreCase(bn.a)) {
                cm.q(m.q().getCodeBase().toExternalForm() + "Resources/" + dH.f + "/" + dX.b, bn.a, this.q);
            }
        }
    }
    
    private int q(final String s) {
        for (int i = 0; i < this.q.l.q; ++i) {
            if (((MenuItem)this.q.l.q(i)).getActionCommand().equals(s)) {
                return i;
            }
        }
        return -1;
    }
    
    public final void q() {
    }
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        final String s;
        if ((s = (String)itemEvent.getItem()).equals(dX.f)) {
            this.q.g = !this.q.g;
            this.q.o(cS.q(this.q.s, this.q.d, this.q.f, this.q.g, this.q.y, this.q.u, 0));
            return;
        }
        final CheckboxMenuItem checkboxMenuItem;
        if ((checkboxMenuItem = this.q.get(s)) != null && checkboxMenuItem.getState()) {
            q(this.q, s);
        }
        if (s.equals(dX.y)) {
            this.q.o(cS.q(this.q.s, this.q.d, this.q.f, this.q.g, this.q.y, 9, 0));
        }
        else if (s.equals(dX.u)) {
            this.q.o(cS.q(this.q.s, this.q.d, this.q.f, this.q.g, this.q.y, 8, 0));
        }
        else if (s.equals(dX.i)) {
            this.q.o(cS.q(this.q.s, this.q.d, this.q.f, this.q.g, this.q.y, 7, 0));
        }
        final CheckboxMenuItem checkboxMenuItem2 = this.w.get(s);
        if (cS.q(s)) {
            this.q.y = cS.q(s);
            this.q.o(cS.q(this.q.s, this.q.d, this.q.f, this.q.g, this.q.y, this.q.u, 0));
        }
        if (checkboxMenuItem2 != null && checkboxMenuItem2.getState()) {
            q(this.w, s);
        }
    }
    
    private static void q(final Hashtable hashtable, final String s) {
        final Enumeration<CheckboxMenuItem> elements = hashtable.elements();
        while (elements.hasMoreElements()) {
            final CheckboxMenuItem checkboxMenuItem;
            if (!(checkboxMenuItem = elements.nextElement()).getLabel().equals(s)) {
                checkboxMenuItem.setState(false);
            }
        }
    }
}
