// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Enumeration;
import java.awt.event.ItemEvent;
import com.spilka.client.muc.AppletAbstract;
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

public final class bj implements bi, ActionListener, ItemListener
{
    private cV q;
    private Frame q;
    private Hashtable q;
    private Hashtable w;
    
    public bj(final cV q) {
        this.q = new Hashtable();
        this.w = new Hashtable();
        this.q = q;
    }
    
    public final MenuBar q(final Frame q) {
        this.q = q;
        final Menu menu = new Menu(a.e);
        final MenuBar menuBar = new MenuBar();
        for (int i = 1; i <= this.q.s.q(); ++i) {
            menu.add((MenuItem)this.q.s.w(i));
            menu.addActionListener(this);
        }
        if (this.q.s.q() > 0) {
            menu.addSeparator();
        }
        final MenuItem menuItem;
        (menuItem = new MenuItem(aJ.q)).addActionListener(this);
        menu.add(menuItem);
        menu.addSeparator();
        if ((this.q.q() & 0x3F2C00004000004L) != 0x0L) {
            final MenuItem menuItem2;
            (menuItem2 = new MenuItem(aJ.o)).addActionListener(this);
            menu.add(menuItem2);
            menu.addSeparator();
        }
        final Menu menu2 = new Menu(aJ.e);
        for (int j = 0; j < 6; ++j) {
            final String s = cy.q()[j];
            final CheckboxMenuItem checkboxMenuItem;
            (checkboxMenuItem = new CheckboxMenuItem(s)).addItemListener(this);
            menu2.add(checkboxMenuItem);
            this.w.put(s, checkboxMenuItem);
        }
        menu.add(menu2);
        menu.addSeparator();
        if (this.q.q(24) || this.q.q(32)) {
            boolean b = true;
            final Menu menu3 = new Menu(aJ.t);
            if (this.q.q(32)) {
                final CheckboxMenuItem checkboxMenuItem2;
                (checkboxMenuItem2 = new CheckboxMenuItem(aJ.y)).addItemListener(this);
                menu3.add(checkboxMenuItem2);
                if (this.q.q(25)) {
                    checkboxMenuItem2.setState(true);
                    b = false;
                }
                this.q.put(aJ.y, checkboxMenuItem2);
            }
            if (this.q.q(24)) {
                final CheckboxMenuItem checkboxMenuItem3;
                (checkboxMenuItem3 = new CheckboxMenuItem(aJ.u)).addItemListener(this);
                menu3.add(checkboxMenuItem3);
                if (this.q.q(23)) {
                    checkboxMenuItem3.setState(true);
                    b = false;
                }
                this.q.put(aJ.u, checkboxMenuItem3);
            }
            final CheckboxMenuItem checkboxMenuItem4;
            (checkboxMenuItem4 = new CheckboxMenuItem(aJ.i)).addItemListener(this);
            menu3.add(checkboxMenuItem4);
            if (b) {
                checkboxMenuItem4.setState(true);
            }
            this.q.put(aJ.i, checkboxMenuItem4);
            menu.add(menu3);
            menu.addSeparator();
        }
        if (this.q.q(80)) {
            final MenuItem menuItem3;
            (menuItem3 = new MenuItem(aJ.c)).addActionListener(this);
            menu.add(menuItem3);
        }
        if (cf.w.r()) {
            if (this.q.q(15)) {
                final MenuItem menuItem4;
                (menuItem4 = new MenuItem(aJ.n)).addActionListener(this);
                menu.add(menuItem4);
            }
            if (!cf.w.s()) {
                final MenuItem menuItem5;
                (menuItem5 = new MenuItem(aJ.m)).addActionListener(this);
                menu.add(menuItem5);
            }
        }
        menu.addSeparator();
        final String s2 = "Admin";
        final cV q2 = this.q;
        if (s2.equals(cV.a) && (this.q.q() & 0xC000000000000L) != 0x0L) {
            final MenuItem menuItem6;
            (menuItem6 = new MenuItem(aJ.p)).addActionListener(this);
            menu.add(menuItem6);
        }
        if (this.q.q(42)) {
            final MenuItem menuItem7;
            (menuItem7 = new MenuItem(aJ.a)).addActionListener(this);
            menu.add(menuItem7);
        }
        final MenuItem menuItem8;
        (menuItem8 = new MenuItem(aJ.v)).addActionListener(this);
        menu.add(menuItem8);
        if (cf.w.r() && !cf.w.a()) {
            menu.add(this.q());
        }
        if (this.q.q(52)) {
            final MenuItem menuItem9;
            (menuItem9 = new MenuItem(aJ.d)).addActionListener(this);
            menu.add(menuItem9);
        }
        final CheckboxMenuItem checkboxMenuItem5;
        (checkboxMenuItem5 = new CheckboxMenuItem(aJ.f)).addItemListener(this);
        menu.add(checkboxMenuItem5);
        if (this.q.q(52)) {
            final MenuItem menuItem10;
            (menuItem10 = new MenuItem(aJ.g)).addActionListener(this);
            menu.add(menuItem10);
        }
        if (this.q.q(45)) {
            final MenuItem menuItem11;
            (menuItem11 = new MenuItem(aJ.s)).addActionListener(this);
            menu.add(menuItem11);
        }
        if (this.q.q(13)) {
            final MenuItem menuItem12;
            (menuItem12 = new MenuItem(aJ.h)).addActionListener(this);
            menu.add(menuItem12);
        }
        if (this.q.q(59) || this.q.q(60)) {
            final MenuItem menuItem13 = aO.q = new MenuItem(this.q.q(59) ? aJ.j : aJ.k);
            menuItem13.addActionListener(this);
            menu.add(menuItem13);
        }
        menu.addSeparator();
        final MenuItem menuItem14;
        (menuItem14 = new MenuItem(aJ.w)).addActionListener(this);
        menu.add(menuItem14);
        final MenuItem menuItem15;
        (menuItem15 = new MenuItem(aJ.r)).addActionListener(this);
        menu.add(menuItem15);
        if (!this.q.y) {
            final MenuItem menuItem16;
            (menuItem16 = new MenuItem(aJ.l)).addActionListener(this);
            menu.add(menuItem16);
            final MenuItem menuItem17;
            (menuItem17 = new MenuItem(aJ.z)).addActionListener(this);
            menu.add(menuItem17);
            final MenuItem menuItem18;
            (menuItem18 = new MenuItem(aJ.x)).addActionListener(this);
            menu.add(menuItem18);
        }
        menuBar.add(menu);
        if (!cf.w.r()) {
            if (!cf.w.a()) {
                menuBar.add(this.q());
            }
            if (this.q.q(15)) {
                final Menu menu4 = new Menu(aJ.n);
                final MenuItem menuItem19;
                (menuItem19 = new MenuItem(aJ.n)).addActionListener(this);
                menu4.add(menuItem19);
                menuBar.add(menu4);
            }
            if (!cf.w.s()) {
                final Menu menu5 = new Menu(aJ.m);
                final MenuItem menuItem20;
                (menuItem20 = new MenuItem(aJ.m)).addActionListener(this);
                menu5.add(menuItem20);
                menuBar.add(menu5);
            }
        }
        return menuBar;
    }
    
    private Menu q() {
        final Menu menu = new Menu(aJ.b);
        for (int i = 0; i < this.q.l.q(); ++i) {
            final MenuItem menuItem;
            (menuItem = new MenuItem(((ck)this.q.l.q(i)).getName())).addActionListener(this);
            menu.add(menuItem);
        }
        return menu;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand;
        if ((actionCommand = actionEvent.getActionCommand()).equals(aJ.z)) {
            this.q.w();
            return;
        }
        if (actionCommand.equals(aJ.x) || actionCommand.equals(aJ.r)) {
            this.q.t();
            return;
        }
        if (actionCommand.equals(aJ.p)) {
            ((dz)this.q).p();
            return;
        }
        if (actionCommand.equals(aJ.o)) {
            ((dz)this.q).f();
            return;
        }
        if (actionCommand.equals(aJ.a)) {
            ((dz)this.q).a();
            return;
        }
        if (actionCommand.equals(aJ.s)) {
            ((dz)this.q).r(false);
            return;
        }
        if (actionCommand.equals(aJ.d)) {
            ((dz)this.q).g();
            return;
        }
        if (actionCommand.equals(aJ.l)) {
            new dz();
            return;
        }
        if (actionCommand.equals(aJ.g)) {
            ((dz)this.q).s();
            return;
        }
        if (actionCommand.equals(aJ.h)) {
            final b b;
            (b = new b(this.q, eb.q("Confirmation"), new String[] { eb.q("OK"), eb.q("Cancel") }, new String[] { eb.q("Restart site?") }, null, this.q)).setVisible(true);
            if (eb.q("OK").equals(b.q())) {
                ((dz)this.q).d();
            }
            return;
        }
        if (actionCommand.equals(aJ.j) || actionCommand.equals(aJ.k)) {
            if (((dz)this.q).q != null) {
                ((dz)this.q).q.setVisible(true);
            }
        }
        else {
            if (actionCommand.equals(aJ.q)) {
                aN.q(this.q);
                return;
            }
            if (actionCommand.equals(aJ.w)) {
                this.q.a(0);
                return;
            }
            if (this.q(actionEvent.getActionCommand()) >= 0) {
                this.q.q((URL)this.q.d.q(this.q(actionEvent.getActionCommand())), "_blank");
                return;
            }
            if (actionCommand.equals(aJ.n)) {
                new al(this.q, (dz)this.q).setVisible(true);
                return;
            }
            if (actionCommand.equals(aJ.c)) {
                this.q.q("", -1, 4);
                return;
            }
            if (actionCommand.equals(aJ.v)) {
                new aD(this.q, this.q).setVisible(true);
                return;
            }
            if (actionCommand.equals(aJ.m)) {
                new am(this.q, (dz)this.q).setVisible(true);
                return;
            }
            final String s = actionCommand;
            for (int i = 0; i < this.q.l.q(); ++i) {
                final ck ck = (ck)this.q.l.q(i);
                if (s.equalsIgnoreCase(ck.getName())) {
                    ea.q(AppletAbstract.q().getCodeBase().toExternalForm() + "Resources/" + cU.a + "/" + aJ.b, ck.getName(), this.q);
                }
            }
        }
    }
    
    private int q(final String s) {
        for (int i = 0; i < this.q.s.q(); ++i) {
            if (((MenuItem)this.q.s.q(i)).getActionCommand().equals(s)) {
                return i;
            }
        }
        return -1;
    }
    
    public final void q() {
    }
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        final String s;
        if ((s = (String)itemEvent.getItem()).equals(aJ.f)) {
            this.q.g = !this.q.g;
            this.q.q(cy.q(this.q.s, this.q.d, this.q.f, this.q.g, this.q.a, this.q.s, 0));
            return;
        }
        final CheckboxMenuItem checkboxMenuItem;
        if ((checkboxMenuItem = this.q.get(s)) != null && checkboxMenuItem.getState()) {
            q(this.q, s);
        }
        if (s.equals(aJ.y)) {
            this.q.q(cy.q(this.q.s, this.q.d, this.q.f, this.q.g, this.q.a, 9, 0));
        }
        else if (s.equals(aJ.u)) {
            this.q.q(cy.q(this.q.s, this.q.d, this.q.f, this.q.g, this.q.a, 8, 0));
        }
        else if (s.equals(aJ.i)) {
            this.q.q(cy.q(this.q.s, this.q.d, this.q.f, this.q.g, this.q.a, 7, 0));
        }
        final CheckboxMenuItem checkboxMenuItem2 = this.w.get(s);
        if (cy.q(s)) {
            this.q.a = cy.q(s);
            this.q.q(cy.q(this.q.s, this.q.d, this.q.f, this.q.g, this.q.a, this.q.s, 0));
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
