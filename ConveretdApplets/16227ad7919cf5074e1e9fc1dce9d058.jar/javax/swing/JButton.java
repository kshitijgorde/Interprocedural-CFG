// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.accessibility.AccessibleRole;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.plaf.ButtonUI;
import java.awt.Component;
import javax.accessibility.AccessibleContext;
import javax.accessibility.Accessible;

public class JButton extends AbstractButton implements Accessible
{
    private static final String uiClassID = "ButtonUI";
    private boolean defaultCapable;
    
    public JButton() {
        this(null, null);
    }
    
    public JButton(final String s) {
        this(s, null);
    }
    
    public JButton(final String s, final Icon icon) {
        this.defaultCapable = true;
        this.setModel(new DefaultButtonModel());
        this.init(s, icon);
    }
    
    public JButton(final Icon icon) {
        this(null, icon);
    }
    
    public AccessibleContext getAccessibleContext() {
        if (super.accessibleContext == null) {
            super.accessibleContext = new AccessibleJButton();
        }
        return super.accessibleContext;
    }
    
    public String getUIClassID() {
        return "ButtonUI";
    }
    
    public boolean isDefaultButton() {
        final JRootPane rootPane = SwingUtilities.getRootPane(this);
        return rootPane != null && rootPane.getDefaultButton() == this;
    }
    
    public boolean isDefaultCapable() {
        return this.defaultCapable;
    }
    
    protected String paramString() {
        return String.valueOf(super.paramString()) + ",defaultCapable=" + (this.defaultCapable ? "true" : "false");
    }
    
    public void setDefaultCapable(final boolean defaultCapable) {
        this.firePropertyChange("defaultCapable", this.defaultCapable, this.defaultCapable = defaultCapable);
    }
    
    public void updateUI() {
        this.setUI((ButtonUI)UIManager.getUI(this));
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        if (super.ui != null && this.getUIClassID().equals("ButtonUI")) {
            super.ui.installUI(this);
        }
    }
    
    protected class AccessibleJButton extends AccessibleAbstractButton
    {
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.PUSH_BUTTON;
        }
    }
}
