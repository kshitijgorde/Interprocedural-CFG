// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.accessibility.AccessibleRole;
import javax.swing.plaf.ButtonUI;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.accessibility.AccessibleContext;
import javax.accessibility.Accessible;

public class JCheckBox extends JToggleButton implements Accessible
{
    private static final String uiClassID = "CheckBoxUI";
    
    public JCheckBox() {
        this(null, null, false);
    }
    
    public JCheckBox(final String s) {
        this(s, null, false);
    }
    
    public JCheckBox(final String s, final Icon icon) {
        this(s, icon, false);
    }
    
    public JCheckBox(final String s, final Icon icon, final boolean b) {
        super(s, icon, b);
        this.setBorderPainted(false);
        this.setHorizontalAlignment(2);
    }
    
    public JCheckBox(final String s, final boolean b) {
        this(s, null, b);
    }
    
    public JCheckBox(final Icon icon) {
        this(null, icon, false);
    }
    
    public JCheckBox(final Icon icon, final boolean b) {
        this(null, icon, b);
    }
    
    public AccessibleContext getAccessibleContext() {
        if (super.accessibleContext == null) {
            super.accessibleContext = new AccessibleJCheckBox();
        }
        return super.accessibleContext;
    }
    
    public String getUIClassID() {
        return "CheckBoxUI";
    }
    
    protected String paramString() {
        return super.paramString();
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        if (this.getUIClassID().equals("CheckBoxUI")) {
            this.updateUI();
        }
    }
    
    public void updateUI() {
        this.setUI((ButtonUI)UIManager.getUI(this));
    }
    
    protected class AccessibleJCheckBox extends AccessibleJToggleButton
    {
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.CHECK_BOX;
        }
    }
}
