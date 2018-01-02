// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.util.Enumeration;
import java.util.Vector;
import java.io.Serializable;

public class ButtonGroup implements Serializable
{
    protected Vector buttons;
    ButtonModel selection;
    
    public ButtonGroup() {
        this.buttons = new Vector();
        this.selection = null;
    }
    
    public void add(final AbstractButton abstractButton) {
        if (abstractButton == null) {
            return;
        }
        this.buttons.addElement(abstractButton);
        if (this.selection == null && abstractButton.isSelected()) {
            this.selection = abstractButton.getModel();
        }
        abstractButton.getModel().setGroup(this);
    }
    
    public Enumeration getElements() {
        return this.buttons.elements();
    }
    
    public ButtonModel getSelection() {
        return this.selection;
    }
    
    public boolean isSelected(final ButtonModel buttonModel) {
        return buttonModel == this.selection;
    }
    
    public void remove(final AbstractButton abstractButton) {
        if (abstractButton == null) {
            return;
        }
        this.buttons.removeElement(abstractButton);
        if (abstractButton.getModel() == this.selection) {
            this.selection = null;
        }
        abstractButton.getModel().setGroup(null);
    }
    
    public void setSelected(final ButtonModel selection, final boolean b) {
        if (b && selection != this.selection) {
            final ButtonModel selection2 = this.selection;
            this.selection = selection;
            if (selection2 != null) {
                selection2.setSelected(false);
            }
        }
    }
}
