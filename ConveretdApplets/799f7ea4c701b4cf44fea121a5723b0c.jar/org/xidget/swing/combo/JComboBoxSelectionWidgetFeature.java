// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.combo;

import java.util.Collections;
import java.util.List;
import javax.swing.JComboBox;
import org.xidget.IXidget;
import org.xidget.ifeature.model.ISelectionWidgetFeature;

public class JComboBoxSelectionWidgetFeature implements ISelectionWidgetFeature
{
    private IXidget xidget;
    
    public JComboBoxSelectionWidgetFeature(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public void select(final Object o) {
        this.xidget.getFeature(JComboBox.class).setSelectedItem(new Item(o));
    }
    
    @Override
    public void deselect(final Object o) {
        final JComboBox comboBox = this.xidget.getFeature(JComboBox.class);
        if (((Item)comboBox.getSelectedItem()).equals(o)) {
            comboBox.setSelectedItem(null);
        }
    }
    
    @Override
    public void setSelection(final List<?> list) {
        this.xidget.getFeature(JComboBox.class).setSelectedItem((list.size() > 0) ? new Item(list.get(0)) : null);
    }
    
    @Override
    public List<?> getSelection() {
        final Item item = (Item)this.xidget.getFeature(JComboBox.class).getSelectedItem();
        return (item != null) ? Collections.singletonList(item.value) : Collections.emptyList();
    }
}
