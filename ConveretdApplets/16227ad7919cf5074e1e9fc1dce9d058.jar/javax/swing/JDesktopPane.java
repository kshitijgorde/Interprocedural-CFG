// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.accessibility.AccessibleRole;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.DesktopPaneUI;
import java.awt.Component;
import java.util.Vector;
import javax.accessibility.AccessibleContext;
import javax.accessibility.Accessible;

public class JDesktopPane extends JLayeredPane implements Accessible
{
    private static final String uiClassID = "DesktopPaneUI";
    transient DesktopManager desktopManager;
    
    public JDesktopPane() {
        this.updateUI();
    }
    
    public AccessibleContext getAccessibleContext() {
        if (super.accessibleContext == null) {
            super.accessibleContext = new AccessibleJDesktopPane();
        }
        return super.accessibleContext;
    }
    
    public JInternalFrame[] getAllFrames() {
        final Vector vector = new Vector<JInternalFrame.JDesktopIcon>(10);
        for (int componentCount = this.getComponentCount(), i = 0; i < componentCount; ++i) {
            final Component component = this.getComponent(i);
            if (component instanceof JInternalFrame) {
                vector.addElement((JInternalFrame.JDesktopIcon)component);
            }
            else if (component instanceof JInternalFrame.JDesktopIcon) {
                final JInternalFrame internalFrame = ((JInternalFrame.JDesktopIcon)component).getInternalFrame();
                if (internalFrame != null) {
                    vector.addElement((JInternalFrame.JDesktopIcon)internalFrame);
                }
            }
        }
        final JInternalFrame[] array = new JInternalFrame[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public JInternalFrame[] getAllFramesInLayer(final int n) {
        final Vector vector = new Vector<JInternalFrame.JDesktopIcon>(10);
        for (int componentCount = this.getComponentCount(), i = 0; i < componentCount; ++i) {
            final Component component = this.getComponent(i);
            if (component instanceof JInternalFrame) {
                if (((JInternalFrame)component).getLayer() == n) {
                    vector.addElement((JInternalFrame.JDesktopIcon)component);
                }
                else if (component instanceof JInternalFrame.JDesktopIcon) {
                    final JInternalFrame internalFrame = ((JInternalFrame.JDesktopIcon)component).getInternalFrame();
                    if (internalFrame != null && internalFrame.getLayer() == n) {
                        vector.addElement((JInternalFrame.JDesktopIcon)internalFrame);
                    }
                }
            }
        }
        final JInternalFrame[] array = new JInternalFrame[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public DesktopManager getDesktopManager() {
        return this.desktopManager;
    }
    
    public DesktopPaneUI getUI() {
        return (DesktopPaneUI)super.ui;
    }
    
    public String getUIClassID() {
        return "DesktopPaneUI";
    }
    
    public boolean isOpaque() {
        return true;
    }
    
    protected String paramString() {
        return String.valueOf(super.paramString()) + ",desktopManager=" + ((this.desktopManager != null) ? this.desktopManager.toString() : "");
    }
    
    public void setDesktopManager(final DesktopManager desktopManager) {
        this.desktopManager = desktopManager;
    }
    
    public void setUI(final DesktopPaneUI ui) {
        super.setUI(ui);
    }
    
    public void updateUI() {
        this.setUI((DesktopPaneUI)UIManager.getUI(this));
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        if (super.ui != null && this.getUIClassID().equals("DesktopPaneUI")) {
            super.ui.installUI(this);
        }
    }
    
    protected class AccessibleJDesktopPane extends AccessibleJComponent
    {
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.DESKTOP_PANE;
        }
    }
}
