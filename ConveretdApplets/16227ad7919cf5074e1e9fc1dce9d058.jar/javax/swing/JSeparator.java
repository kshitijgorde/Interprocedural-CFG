// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.accessibility.AccessibleRole;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.SeparatorUI;
import javax.accessibility.AccessibleContext;
import javax.accessibility.Accessible;

public class JSeparator extends JComponent implements SwingConstants, Accessible
{
    private static final String uiClassID = "SeparatorUI";
    private int orientation;
    
    public JSeparator() {
        this(0);
    }
    
    public JSeparator(final int orientation) {
        this.orientation = 0;
        this.checkOrientation(orientation);
        this.orientation = orientation;
        this.updateUI();
    }
    
    private void checkOrientation(final int n) {
        switch (n) {
            default: {
                throw new IllegalArgumentException("orientation must be one of: VERTICAL, HORIZONTAL");
            }
            case 0:
            case 1: {}
        }
    }
    
    public AccessibleContext getAccessibleContext() {
        if (super.accessibleContext == null) {
            super.accessibleContext = new AccessibleJSeparator();
        }
        return super.accessibleContext;
    }
    
    public int getOrientation() {
        return this.orientation;
    }
    
    public SeparatorUI getUI() {
        return (SeparatorUI)super.ui;
    }
    
    public String getUIClassID() {
        return "SeparatorUI";
    }
    
    public boolean isFocusTraversable() {
        return false;
    }
    
    protected String paramString() {
        return String.valueOf(super.paramString()) + ",orientation=" + ((this.orientation == 0) ? "HORIZONTAL" : "VERTICAL");
    }
    
    public void setOrientation(final int orientation) {
        if (this.orientation == orientation) {
            return;
        }
        final int orientation2 = this.orientation;
        this.checkOrientation(orientation);
        this.firePropertyChange("orientation", orientation2, this.orientation = orientation);
        this.revalidate();
        this.repaint();
    }
    
    public void setUI(final SeparatorUI ui) {
        super.setUI(ui);
    }
    
    public void updateUI() {
        this.setUI((SeparatorUI)UIManager.getUI(this));
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        if (super.ui != null && this.getUIClassID().equals("SeparatorUI")) {
            super.ui.installUI(this);
        }
    }
    
    protected class AccessibleJSeparator extends AccessibleJComponent
    {
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.SEPARATOR;
        }
    }
}
