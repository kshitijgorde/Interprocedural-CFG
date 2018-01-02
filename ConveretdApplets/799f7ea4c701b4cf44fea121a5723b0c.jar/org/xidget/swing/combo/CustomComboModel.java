// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.combo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.MutableComboBoxModel;
import javax.swing.AbstractListModel;

public class CustomComboModel extends AbstractListModel implements MutableComboBoxModel
{
    private JComboBox widget;
    private List<Item> items;
    private Item selected;
    private Object edit;
    
    public CustomComboModel(final JComboBox widget) {
        this.widget = widget;
        this.items = new ArrayList<Item>();
    }
    
    @Override
    public void addElement(final Object o) {
        this.items.add((Item)o);
        final int n = this.items.size() - 1;
        this.fireIntervalAdded(this.widget, n, n);
    }
    
    @Override
    public void insertElementAt(final Object o, final int n) {
        this.items.add(n, (Item)o);
        this.fireIntervalAdded(this.widget, n, n);
    }
    
    public void updateElementAt(final int n) {
        this.fireContentsChanged(this.widget, n, n);
    }
    
    @Override
    public void removeElement(final Object o) {
        final int index = this.items.indexOf(o);
        if (index >= 0) {
            this.removeElement(index);
        }
    }
    
    @Override
    public void removeElementAt(final int n) {
        this.items.remove(n);
        this.fireIntervalRemoved(this.widget, n, n);
    }
    
    @Override
    public Object getSelectedItem() {
        return this.selected;
    }
    
    @Override
    public void setSelectedItem(final Object edit) {
        this.edit = null;
        if (edit instanceof Item) {
            final Item selected = (Item)edit;
            final int index = this.items.indexOf(selected);
            if (index >= 0) {
                this.selected = this.items.get(index);
                this.fireContentsChanged(this.widget, index, index);
            }
            else {
                this.selected = selected;
            }
        }
        else {
            this.edit = edit;
            if (this.selected == null) {
                for (int i = 0; i < this.items.size(); ++i) {
                    final Item selected2 = this.items.get(i);
                    if (selected2.toString().equals(edit)) {
                        this.selected = selected2;
                        this.fireContentsChanged(this.widget, i, i);
                        break;
                    }
                }
                if (this.selected == null) {
                    this.addElement(this.selected = new Item(this.edit));
                }
            }
        }
    }
    
    public Object getEditorValue() {
        return this.edit;
    }
    
    @Override
    public Object getElementAt(final int n) {
        return this.items.get(n);
    }
    
    @Override
    public int getSize() {
        return this.items.size();
    }
}
