// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.accessibility.AccessibleState;
import javax.accessibility.AccessibleRole;
import java.awt.event.ItemListener;
import java.awt.ItemSelectable;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.plaf.ButtonUI;
import javax.accessibility.AccessibleContext;
import javax.accessibility.Accessible;

public class JToggleButton extends AbstractButton implements Accessible
{
    private static final String uiClassID = "ToggleButtonUI";
    
    public JToggleButton() {
        this(null, null, false);
    }
    
    public JToggleButton(final String s) {
        this(s, null, false);
    }
    
    public JToggleButton(final String s, final Icon icon) {
        this(s, icon, false);
    }
    
    public JToggleButton(final String s, final Icon icon, final boolean selected) {
        this.setModel(new ToggleButtonModel());
        super.model.setSelected(selected);
        this.init(s, icon);
    }
    
    public JToggleButton(final String s, final boolean b) {
        this(s, null, b);
    }
    
    public JToggleButton(final Icon icon) {
        this(null, icon, false);
    }
    
    public JToggleButton(final Icon icon, final boolean b) {
        this(null, icon, b);
    }
    
    public AccessibleContext getAccessibleContext() {
        if (super.accessibleContext == null) {
            super.accessibleContext = new AccessibleJToggleButton();
        }
        return super.accessibleContext;
    }
    
    public String getUIClassID() {
        return "ToggleButtonUI";
    }
    
    protected String paramString() {
        return super.paramString();
    }
    
    public void updateUI() {
        this.setUI((ButtonUI)UIManager.getUI(this));
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        if (super.ui != null && this.getUIClassID().equals("ToggleButtonUI")) {
            super.ui.installUI(this);
        }
    }
    
    public static class ToggleButtonModel extends DefaultButtonModel
    {
        public boolean isSelected() {
            if (super.group != null) {
                return super.group.isSelected(this);
            }
            return (super.stateMask & 0x2) != 0x0;
        }
        
        public void setPressed(final boolean b) {
            if (this.isPressed() == b || !this.isEnabled()) {
                return;
            }
            if (!b && this.isArmed()) {
                this.setSelected(this.isSelected() ^ true);
            }
            if (b) {
                super.stateMask |= 0x4;
            }
            else {
                super.stateMask &= 0xFFFFFFFB;
            }
            this.fireStateChanged();
            if (!this.isPressed() && this.isArmed()) {
                this.fireActionPerformed(new ActionEvent(this, 1001, this.getActionCommand()));
            }
        }
        
        public void setSelected(final boolean b) {
            if (super.group != null) {
                super.group.setSelected(this, b);
            }
            else if (b) {
                super.stateMask |= 0x2;
            }
            else {
                super.stateMask &= 0xFFFFFFFD;
            }
            this.fireStateChanged();
            this.fireItemStateChanged(new ItemEvent(this, 701, this, this.isSelected() ? 1 : 2));
        }
    }
    
    protected class AccessibleJToggleButton extends AccessibleAbstractButton implements ItemListener
    {
        public AccessibleJToggleButton() {
            JToggleButton.this.addItemListener(this);
        }
        
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.TOGGLE_BUTTON;
        }
        
        public void itemStateChanged(final ItemEvent itemEvent) {
            final JToggleButton toggleButton = (JToggleButton)itemEvent.getSource();
            if (JToggleButton.this.accessibleContext != null) {
                if (toggleButton.isSelected()) {
                    JToggleButton.this.accessibleContext.firePropertyChange("AccessibleState", null, AccessibleState.CHECKED);
                }
                else {
                    JToggleButton.this.accessibleContext.firePropertyChange("AccessibleState", AccessibleState.CHECKED, null);
                }
            }
        }
    }
}
