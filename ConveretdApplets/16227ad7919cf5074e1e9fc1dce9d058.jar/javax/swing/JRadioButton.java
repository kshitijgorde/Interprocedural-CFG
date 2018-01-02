// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.accessibility.AccessibleRole;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.plaf.ButtonUI;
import javax.accessibility.AccessibleContext;
import javax.accessibility.Accessible;

public class JRadioButton extends JToggleButton implements Accessible
{
    private static final String uiClassID = "RadioButtonUI";
    
    public JRadioButton() {
        this(null, null, false);
    }
    
    public JRadioButton(final String s) {
        this(s, null, false);
    }
    
    public JRadioButton(final String s, final Icon icon) {
        this(s, icon, false);
    }
    
    public JRadioButton(final String s, final Icon icon, final boolean b) {
        super(s, icon, b);
        this.setBorderPainted(false);
        this.setHorizontalAlignment(2);
    }
    
    public JRadioButton(final String s, final boolean b) {
        this(s, null, b);
    }
    
    public JRadioButton(final Icon icon) {
        this(null, icon, false);
    }
    
    public JRadioButton(final Icon icon, final boolean b) {
        this(null, icon, b);
    }
    
    public AccessibleContext getAccessibleContext() {
        if (super.accessibleContext == null) {
            super.accessibleContext = new AccessibleJRadioButton();
        }
        return super.accessibleContext;
    }
    
    public String getUIClassID() {
        return "RadioButtonUI";
    }
    
    protected String paramString() {
        return super.paramString();
    }
    
    public void updateUI() {
        this.setUI((ButtonUI)UIManager.getUI(this));
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        if (super.ui != null && this.getUIClassID().equals("RadioButtonUI")) {
            super.ui.installUI(this);
        }
    }
    
    protected class AccessibleJRadioButton extends AccessibleJToggleButton
    {
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.RADIO_BUTTON;
        }
    }
}
