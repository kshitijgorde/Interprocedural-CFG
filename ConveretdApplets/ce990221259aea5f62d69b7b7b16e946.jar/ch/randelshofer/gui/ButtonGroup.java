// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.gui;

import java.awt.event.ItemEvent;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.event.ItemListener;
import java.io.Serializable;

public class ButtonGroup implements Serializable, ItemListener
{
    protected Vector buttons;
    AbstractButton selection;
    
    public ButtonGroup() {
        this.buttons = new Vector();
        this.selection = null;
    }
    
    public void add(final AbstractButton selection) {
        if (selection == null) {
            return;
        }
        this.buttons.addElement(selection);
        selection.setGroup(this);
        if (this.selection == null && selection.isSelected()) {
            this.selection = selection;
        }
        selection.addItemListener(this);
    }
    
    public void remove(final AbstractButton abstractButton) {
        if (abstractButton == null) {
            return;
        }
        this.buttons.removeElement(abstractButton);
        abstractButton.setGroup(null);
        if (abstractButton == this.selection) {
            this.selection = null;
        }
        abstractButton.removeItemListener(this);
    }
    
    public Enumeration getElements() {
        return this.buttons.elements();
    }
    
    public AbstractButton getSelection() {
        return this.selection;
    }
    
    public void setSelected(final AbstractButton selection, final boolean b) {
        if (b && selection != this.selection) {
            final AbstractButton selection2 = this.selection;
            this.selection = selection;
            if (selection2 != null) {
                selection2.setSelected(false);
            }
        }
    }
    
    public boolean isSelected(final AbstractButton abstractButton) {
        return abstractButton == this.selection;
    }
    
    public int getButtonCount() {
        if (this.buttons == null) {
            return 0;
        }
        return this.buttons.size();
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getStateChange() == 1) {
            this.setSelected((AbstractButton)itemEvent.getSource(), true);
        }
    }
}
