// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.combo;

import javax.swing.JComboBox;
import org.xidget.IXidget;
import org.xidget.ifeature.model.ISingleValueWidgetFeature;

public class JComboBoxSingleValueWidgetFeature implements ISingleValueWidgetFeature
{
    private IXidget xidget;
    
    public JComboBoxSingleValueWidgetFeature(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public Object getValue() {
        final JComboBox comboBox = this.xidget.getFeature(JComboBox.class);
        final Object editorValue = ((CustomComboModel)comboBox.getModel()).getEditorValue();
        if (editorValue != null) {
            return editorValue;
        }
        final Item item = (Item)comboBox.getSelectedItem();
        if (item != null) {
            return item.toString();
        }
        return null;
    }
    
    @Override
    public void setValue(final Object o) {
        final JComboBox<Item> comboBox = this.xidget.getFeature((Class<JComboBox<Item>>)JComboBox.class);
        for (int i = 0; i < comboBox.getItemCount(); ++i) {
            final Item selectedItem = comboBox.getItemAt(i);
            if (selectedItem.toString().equals(o)) {
                comboBox.setSelectedItem(selectedItem);
                break;
            }
        }
    }
}
