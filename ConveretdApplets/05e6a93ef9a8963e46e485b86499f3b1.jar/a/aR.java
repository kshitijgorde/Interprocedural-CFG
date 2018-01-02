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

public final class aR implements bj, ActionListener, ItemListener
{
    private W q;
    private Frame q;
    private Hashtable q;
    
    public aR(final W q) {
        this.q = new Hashtable();
        this.q = q;
    }
    
    public final MenuBar q(final Frame q) {
        this.q = q;
        final Menu menu = new Menu(cs.e);
        final MenuBar menuBar = new MenuBar();
        for (int i = 1; i <= this.q.a.q; ++i) {
            menu.add((MenuItem)this.q.a.w(i));
            menu.addActionListener(this);
        }
        if (this.q.a.q > 0) {
            menu.addSeparator();
        }
        final MenuItem menuItem;
        (menuItem = new MenuItem(cy.q)).addActionListener(this);
        menu.add(menuItem);
        menu.addSeparator();
        final MenuItem menuItem2;
        (menuItem2 = new MenuItem(cy.w)).addActionListener(this);
        menu.add(menuItem2);
        menu.addSeparator();
        final Menu menu2 = new Menu(cy.e);
        for (int j = 0; j < 6; ++j) {
            final String s = bL.q()[j];
            final CheckboxMenuItem checkboxMenuItem;
            (checkboxMenuItem = new CheckboxMenuItem(s)).addItemListener(this);
            menu2.add(checkboxMenuItem);
            this.q.put(s, checkboxMenuItem);
        }
        menu.add(menu2);
        menu.addSeparator();
        if (!aT.w.p()) {
            final MenuItem menuItem3;
            (menuItem3 = new MenuItem(cy.u)).addActionListener(this);
            menu.add(menuItem3);
            menu.addSeparator();
        }
        final MenuItem menuItem4;
        (menuItem4 = new MenuItem(cy.i)).addActionListener(this);
        menu.add(menuItem4);
        menu.addSeparator();
        if (aT.w.r() && cs.u) {
            menu.add(this.q());
            menu.addSeparator();
        }
        if (!this.q.y) {
            final MenuItem menuItem5;
            (menuItem5 = new MenuItem(cy.r)).addActionListener(this);
            menu.add(menuItem5);
        }
        menuBar.add(menu);
        if (!aT.w.r() && cs.u) {
            menuBar.add(this.q());
        }
        return menuBar;
    }
    
    private Menu q() {
        final Menu menu = new Menu(cy.o);
        for (int i = 0; i < this.q.k.q; ++i) {
            final MenuItem menuItem;
            (menuItem = new MenuItem(((aY)this.q.k.q(i)).o)).addActionListener(this);
            menu.add(menuItem);
        }
        return menu;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals(cy.w)) {
            this.q.w(0);
            return;
        }
        if (actionEvent.getActionCommand().equals(cy.q)) {
            bi.q(this.q);
            return;
        }
        if (actionEvent.getActionCommand().equals(cy.u)) {
            this.q.q("", -1, 4);
            return;
        }
        if (actionEvent.getActionCommand().equals(cy.i)) {
            new ap(this.q, this.q).setVisible(true);
            return;
        }
        if (actionEvent.getActionCommand().equals(cy.r)) {
            this.q.y();
            return;
        }
        if (this.q(actionEvent.getActionCommand()) >= 0) {
            this.q.q((URL)this.q.s.q(this.q(actionEvent.getActionCommand())), "_blank");
            return;
        }
        final String actionCommand = actionEvent.getActionCommand();
        for (int i = 0; i < this.q.k.q; ++i) {
            final aY ay = (aY)this.q.k.q(i);
            if (actionCommand.equalsIgnoreCase(ay.o)) {
                bn.q(h.q().getCodeBase().toExternalForm() + "Resources/" + co.p + "/" + cy.o, ay.o, this.q);
            }
        }
    }
    
    private int q(final String s) {
        for (int i = 0; i < this.q.a.q; ++i) {
            if (((MenuItem)this.q.a.q(i)).getActionCommand().equals(s)) {
                return i;
            }
        }
        return -1;
    }
    
    public final void q() {
    }
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        final String s = (String)itemEvent.getItem();
        final CheckboxMenuItem checkboxMenuItem = this.q.get(s);
        if (bL.q(s)) {
            this.q.y = bL.q(s);
            this.q.r(bL.q(this.q.a, this.q.s, this.q.d, this.q.f, this.q.y, this.q.u, 0));
        }
        if (checkboxMenuItem != null && checkboxMenuItem.getState()) {
            final Hashtable q = this.q;
            final String s2 = s;
            final Enumeration<CheckboxMenuItem> elements = q.elements();
            while (elements.hasMoreElements()) {
                final CheckboxMenuItem checkboxMenuItem2;
                if (!(checkboxMenuItem2 = elements.nextElement()).getLabel().equals(s2)) {
                    checkboxMenuItem2.setState(false);
                }
            }
        }
    }
}
