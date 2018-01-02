// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.GA;

import java.awt.event.ItemEvent;
import java.awt.MenuItem;
import java.awt.CheckboxMenuItem;
import java.awt.event.ItemListener;
import java.awt.Menu;

class RadioGroupMenu extends Menu implements ItemListener
{
    private CheckboxMenuItem[] items;
    private int selectedIndex;
    
    RadioGroupMenu(final String s, final String[] array) {
        this(s, array, -1);
    }
    
    RadioGroupMenu(final String s, final String[] array, final int selectedIndex) {
        super(s);
        this.selectedIndex = -1;
        this.items = new CheckboxMenuItem[array.length];
        for (int i = 0; i < array.length; ++i) {
            this.add(this.items[i] = new CheckboxMenuItem(array[i]));
            this.items[i].addItemListener(this);
        }
        this.selectedIndex = selectedIndex;
        if (this.selectedIndex < 0 || this.selectedIndex >= this.items.length) {
            this.selectedIndex = 1;
        }
        this.items[this.selectedIndex].setState(true);
    }
    
    public int getSelectedIndex() {
        return this.selectedIndex;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        final CheckboxMenuItem checkboxMenuItem = (CheckboxMenuItem)itemEvent.getSource();
        for (int i = 0; i < this.items.length; ++i) {
            if (checkboxMenuItem == this.items[i]) {
                this.items[this.selectedIndex].setState(false);
                this.selectedIndex = i;
                checkboxMenuItem.setState(true);
                return;
            }
        }
    }
}
