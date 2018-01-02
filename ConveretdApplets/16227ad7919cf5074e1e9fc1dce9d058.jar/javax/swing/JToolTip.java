// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.accessibility.AccessibleRole;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.ToolTipUI;
import javax.accessibility.AccessibleContext;
import javax.accessibility.Accessible;

public class JToolTip extends JComponent implements Accessible
{
    private static final String uiClassID = "ToolTipUI";
    String tipText;
    JComponent component;
    
    public JToolTip() {
        this.updateUI();
    }
    
    boolean alwaysOnTop() {
        return true;
    }
    
    public AccessibleContext getAccessibleContext() {
        if (super.accessibleContext == null) {
            super.accessibleContext = new AccessibleJToolTip();
        }
        return super.accessibleContext;
    }
    
    public JComponent getComponent() {
        return this.component;
    }
    
    public String getTipText() {
        return this.tipText;
    }
    
    public ToolTipUI getUI() {
        return (ToolTipUI)super.ui;
    }
    
    public String getUIClassID() {
        return "ToolTipUI";
    }
    
    protected String paramString() {
        return String.valueOf(super.paramString()) + ",tipText=" + ((this.tipText != null) ? this.tipText : "");
    }
    
    public void setComponent(final JComponent component) {
        this.component = component;
    }
    
    public void setTipText(final String tipText) {
        this.firePropertyChange("tiptext", this.tipText, this.tipText = tipText);
    }
    
    public void updateUI() {
        this.setUI(UIManager.getUI(this));
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        if (super.ui != null && this.getUIClassID().equals("ToolTipUI")) {
            super.ui.installUI(this);
        }
    }
    
    protected class AccessibleJToolTip extends AccessibleJComponent
    {
        public String getAccessibleDescription() {
            if (super.accessibleDescription != null) {
                return super.accessibleDescription;
            }
            return JToolTip.this.getTipText();
        }
        
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.TOOL_TIP;
        }
    }
}
