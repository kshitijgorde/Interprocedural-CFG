// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.combo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JComboBox;
import org.xidget.IXidget;
import org.xidget.ifeature.model.IMultiValueWidgetFeature;

public class JComboBoxMultiValueWidgetFeature implements IMultiValueWidgetFeature
{
    protected IXidget xidget;
    
    public JComboBoxMultiValueWidgetFeature(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public void insertValue(final int n, final Object o) {
        this.xidget.getFeature((Class<JComboBox<Item>>)JComboBox.class).insertItemAt(new Item(o), n);
    }
    
    @Override
    public void updateValue(final int n, final Object o) {
        ((CustomComboModel)this.xidget.getFeature(JComboBox.class).getModel()).updateElementAt(n);
    }
    
    @Override
    public void removeValue(final int n) {
        this.xidget.getFeature(JComboBox.class).removeItemAt(n);
    }
    
    @Override
    public void setValues(final List<?> list) {
        final JComboBox<Object> comboBox = this.xidget.getFeature((Class<JComboBox<Object>>)JComboBox.class);
        comboBox.removeAllItems();
        final Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()) {
            comboBox.addItem(new Item(iterator.next()));
        }
    }
    
    @Override
    public List<?> getValues() {
        final JComboBox<Item> comboBox = this.xidget.getFeature((Class<JComboBox<Item>>)JComboBox.class);
        final ArrayList list = new ArrayList<Object>(comboBox.getItemCount());
        for (int i = 0; i < comboBox.getItemCount(); ++i) {
            list.add(comboBox.getItemAt(i).value);
        }
        return list;
    }
}
