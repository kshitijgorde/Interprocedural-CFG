// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.component;

import java.awt.event.ActionListener;
import javax.swing.JComponent;
import com.masystem.beergame.ui.component.swing.ComboBoxComponent;
import com.masystem.beergame.ui.graphics.StretchableImage;
import com.masystem.beergame.ui.scene.Node;

public class ComboBoxNode extends Node
{
    public ComboBoxNode(final String[] array, final StretchableImage stretchableImage) {
        super(new ComboBoxComponent(array, stretchableImage));
    }
    
    public final void addItem(final String s) {
        ((ComboBoxComponent)super.getComponent()).addItem(s);
    }
    
    public final void removeAllItems() {
        ((ComboBoxComponent)super.getComponent()).removeAllItems();
    }
    
    public final String getSelectedItem() {
        if (((ComboBoxComponent)super.getComponent()).getSelectedItem() instanceof String) {
            return (String)((ComboBoxComponent)super.getComponent()).getSelectedItem();
        }
        return "";
    }
    
    public final void setSelectedItem(final String selectedItem) {
        ((ComboBoxComponent)super.getComponent()).setSelectedItem(selectedItem);
    }
    
    public final int getNbrItems() {
        return ((ComboBoxComponent)super.getComponent()).getItemCount();
    }
    
    public final String getItemAt(final int n) {
        if (((ComboBoxComponent)super.getComponent()).getItemAt(n) instanceof String) {
            return ((ComboBoxComponent)super.getComponent()).getItemAt(n);
        }
        return "";
    }
    
    public final void addActionListener(final ActionListener actionListener) {
        ((ComboBoxComponent)super.getComponent()).addActionListener(actionListener);
    }
    
    @Override
    public final ComboBoxComponent getComponent() {
        return (ComboBoxComponent)super.getComponent();
    }
}
