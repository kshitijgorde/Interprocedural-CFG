// 
// Decompiled by Procyon v0.5.30
// 

package util102.gui;

import java.awt.Event;
import java.awt.Insets;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Vector;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.Panel;

public class TabPanel extends Panel
{
    BorderLayout layout;
    CardLayout centerLayout;
    Panel northPanel;
    TabCardPanel centerCardPanel;
    Vector tabVector;
    Vector cardVector;
    GridBagLayout gridBag;
    GridBagConstraints c;
    
    public TabPanel() {
        this(5, 5, 5, 5);
    }
    
    public TabPanel(final int n, final int n2, final int n3, final int n4) {
        this.tabVector = new Vector();
        this.cardVector = new Vector();
        this.setLayout(this.layout = new BorderLayout(0, 0));
        this.northPanel = new Panel();
        this.gridBag = new GridBagLayout();
        this.c = new GridBagConstraints();
        this.northPanel.setLayout(this.gridBag);
        this.add("North", this.northPanel);
        this.centerCardPanel = new TabCardPanel(n, n2, n3, n4);
        this.centerLayout = new CardLayout();
        ((Container)this.centerCardPanel).setLayout(this.centerLayout);
        this.add("Center", (Component)this.centerCardPanel);
        this.validate();
    }
    
    public void addTab(final String s, final Component component) {
        final Tab tab = new Tab(s);
        this.tabVector.addElement(tab);
        this.northPanel.add((Component)tab);
        this.cardVector.addElement(component);
        ((Container)this.centerCardPanel).add(s, component);
        this.refreshPanelsDisplay();
    }
    
    public void addTab(final Tab tab, final Component component) {
        this.tabVector.addElement(tab);
        this.northPanel.add((Component)tab);
        this.cardVector.addElement(component);
        ((Container)this.centerCardPanel).add(tab.getLabel(), component);
        this.refreshPanelsDisplay();
    }
    
    public void selectFirstTab() {
        try {
            final Tab tab = this.tabVector.firstElement();
            if (tab != null) {
                tab.setSelected(true);
                this.centerLayout.show((Container)this.centerCardPanel, tab.getLabel());
            }
        }
        catch (NoSuchElementException ex) {}
    }
    
    public void deselectAllTab() {
        final Enumeration<Tab> elements = this.tabVector.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().setSelected(false);
        }
    }
    
    public void refreshPanelsDisplay() {
        this.northPanel.removeAll();
        ((Container)this.centerCardPanel).removeAll();
        final Enumeration<Component> elements = this.cardVector.elements();
        final Enumeration<Tab> elements2 = (Enumeration<Tab>)this.tabVector.elements();
        while (elements2.hasMoreElements()) {
            final Tab tab = elements2.nextElement();
            final Component component = elements.nextElement();
            this.c.weightx = 0.0;
            this.c.weighty = 0.0;
            this.c.insets = new Insets(0, 0, 0, 0);
            this.c.fill = 2;
            this.c.anchor = 16;
            this.c.gridwidth = 1;
            this.gridBag.setConstraints((Component)tab, this.c);
            this.northPanel.add((Component)tab);
            ((Container)this.centerCardPanel).add(tab.getLabel(), component);
        }
        final Tab tab2 = new Tab("");
        tab2.setNotab(true);
        this.c.weightx = 1.0;
        this.c.weighty = 0.0;
        this.c.insets = new Insets(0, 0, 0, 0);
        this.c.fill = 2;
        this.c.anchor = 16;
        this.c.gridwidth = 0;
        this.gridBag.setConstraints((Component)tab2, this.c);
        this.northPanel.add((Component)tab2);
        this.selectFirstTab();
        this.validate();
    }
    
    public void showTab(final Tab tab) {
        if (!this.tabVector.contains(tab)) {
            return;
        }
        this.deselectAllTab();
        tab.setSelected(true);
        this.centerLayout.show((Container)this.centerCardPanel, tab.getLabel());
    }
    
    public void showTab(final String s) {
        this.deselectAllTab();
        final Enumeration<Tab> elements = this.tabVector.elements();
        while (elements.hasMoreElements()) {
            final Tab tab = elements.nextElement();
            if (tab.getLabel().equals(s)) {
                tab.setSelected(true);
                this.centerLayout.show((Container)this.centerCardPanel, tab.getLabel());
            }
        }
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 501: {
                if (event.target instanceof Tab) {
                    final Tab tab = (Tab)event.target;
                    if (!tab.getNotab()) {
                        this.deselectAllTab();
                        tab.setSelected(true);
                        this.centerLayout.show((Container)this.centerCardPanel, tab.getLabel());
                        this.postEvent(new Event(tab, 1001, null));
                    }
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
}
