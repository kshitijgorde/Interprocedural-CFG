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

public final class aS implements aR, ActionListener, ItemListener
{
    private bI q;
    private Frame q;
    private Hashtable q;
    
    public aS(final bI q) {
        this.q = new Hashtable();
        this.q = q;
    }
    
    public final MenuBar q(final Frame q) {
        this.q = q;
        final Menu menu = new Menu(a.e);
        final MenuBar menuBar = new MenuBar();
        for (int i = 1; i <= this.q.a.q(); ++i) {
            menu.add((MenuItem)this.q.a.w(i));
            menu.addActionListener(this);
        }
        if (this.q.a.q() > 0) {
            menu.addSeparator();
        }
        final MenuItem menuItem;
        (menuItem = new MenuItem(au.q)).addActionListener(this);
        menu.add(menuItem);
        menu.addSeparator();
        final MenuItem menuItem2;
        (menuItem2 = new MenuItem(au.w)).addActionListener(this);
        menu.add(menuItem2);
        menu.addSeparator();
        final Menu menu2 = new Menu(au.e);
        for (int j = 0; j < 6; ++j) {
            final String s = bo.q()[j];
            final CheckboxMenuItem checkboxMenuItem;
            (checkboxMenuItem = new CheckboxMenuItem(s)).addItemListener(this);
            menu2.add(checkboxMenuItem);
            this.q.put(s, checkboxMenuItem);
        }
        menu.add(menu2);
        menu.addSeparator();
        if (this.q.q(80)) {
            final MenuItem menuItem3;
            (menuItem3 = new MenuItem(au.u)).addActionListener(this);
            menu.add(menuItem3);
            menu.addSeparator();
        }
        if (!be.w.s()) {
            final MenuItem menuItem4;
            (menuItem4 = new MenuItem(au.i)).addActionListener(this);
            menu.add(menuItem4);
            menu.addSeparator();
        }
        if (be.w.r() && !be.w.a()) {
            menu.add(this.q());
            menu.addSeparator();
        }
        if (!this.q.y) {
            final MenuItem menuItem5;
            (menuItem5 = new MenuItem(au.r)).addActionListener(this);
            menu.add(menuItem5);
        }
        menuBar.add(menu);
        if (!be.w.r() && !be.w.a()) {
            menuBar.add(this.q());
        }
        return menuBar;
    }
    
    private Menu q() {
        final Menu menu = new Menu(au.o);
        for (int i = 0; i < this.q.k.q(); ++i) {
            final MenuItem menuItem;
            (menuItem = new MenuItem(((bh)this.q.k.q(i)).getName())).addActionListener(this);
            menu.add(menuItem);
        }
        return menu;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals(au.w)) {
            this.q.o(0);
            return;
        }
        if (actionEvent.getActionCommand().equals(au.q)) {
            ay.q(this.q);
            return;
        }
        if (actionEvent.getActionCommand().equals(au.u)) {
            this.q.q("", -1, 4);
            return;
        }
        if (actionEvent.getActionCommand().equals(au.i)) {
            new ao(this.q, this.q).setVisible(true);
            return;
        }
        if (actionEvent.getActionCommand().equals(au.r)) {
            this.q.w();
            return;
        }
        if (this.q(actionEvent.getActionCommand()) >= 0) {
            this.q.q((URL)this.q.s.q(this.q(actionEvent.getActionCommand())), "_blank");
            return;
        }
        final String actionCommand = actionEvent.getActionCommand();
        for (int i = 0; i < this.q.k.q(); ++i) {
            final bh bh = (bh)this.q.k.q(i);
            if (actionCommand.equalsIgnoreCase(bh.getName())) {
                cu.q(AppletAbstract.q().getCodeBase().toExternalForm() + "Resources/" + bH.p + "/" + au.o, bh.getName(), this.q);
            }
        }
    }
    
    private int q(final String s) {
        for (int i = 0; i < this.q.a.q(); ++i) {
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
        if (bo.q(s)) {
            this.q.p = bo.q(s);
            this.q.q(bo.q(this.q.a, this.q.s, this.q.d, this.q.f, this.q.p, this.q.a, 0));
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
