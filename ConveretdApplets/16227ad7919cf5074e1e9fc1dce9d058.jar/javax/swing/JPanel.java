// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.accessibility.AccessibleRole;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.PanelUI;
import javax.accessibility.AccessibleContext;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import javax.accessibility.Accessible;

public class JPanel extends JComponent implements Accessible
{
    private static final String uiClassID = "PanelUI";
    private static final FlowLayout defaultLayout;
    
    static {
        defaultLayout = new FlowLayout();
    }
    
    public JPanel() {
        this(JPanel.defaultLayout, true);
    }
    
    public JPanel(final LayoutManager layoutManager) {
        this(layoutManager, true);
    }
    
    public JPanel(final LayoutManager layout, final boolean doubleBuffered) {
        this.setLayout(layout);
        this.setDoubleBuffered(doubleBuffered);
        this.setOpaque(true);
        this.updateUI();
    }
    
    public JPanel(final boolean b) {
        this(JPanel.defaultLayout, b);
    }
    
    public AccessibleContext getAccessibleContext() {
        if (super.accessibleContext == null) {
            super.accessibleContext = new AccessibleJPanel();
        }
        return super.accessibleContext;
    }
    
    public String getUIClassID() {
        return "PanelUI";
    }
    
    protected String paramString() {
        return String.valueOf(super.paramString()) + ",defaultLayout=" + ((JPanel.defaultLayout != null) ? JPanel.defaultLayout.toString() : "");
    }
    
    public void updateUI() {
        this.setUI(UIManager.getUI(this));
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        if (super.ui != null && this.getUIClassID().equals("PanelUI")) {
            super.ui.installUI(this);
        }
    }
    
    protected class AccessibleJPanel extends AccessibleJComponent
    {
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.PANEL;
        }
    }
}
