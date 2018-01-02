// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.border.Border;
import java.awt.Container;
import javax.swing.JDesktopPane;
import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import de.muntjak.tinylookandfeel.borders.TinyInternalFrameBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class TinyInternalFrameUI extends BasicInternalFrameUI
{
    private TinyInternalFrameBorder frameBorder;
    private TinyInternalFrameTitlePane titlePane;
    
    public TinyInternalFrameUI(final JInternalFrame internalFrame) {
        super(internalFrame);
    }
    
    public static ComponentUI createUI(final JComponent component) {
        return new TinyInternalFrameUI((JInternalFrame)component);
    }
    
    JDesktopPane getDesktopPane(final JComponent component) {
        JDesktopPane desktopPane = null;
        Container container = component.getParent();
        while (desktopPane == null) {
            if (container instanceof JDesktopPane) {
                desktopPane = (JDesktopPane)container;
            }
            else {
                if (container == null) {
                    break;
                }
                container = container.getParent();
            }
        }
        return desktopPane;
    }
    
    public void installUI(final JComponent component) {
        super.installUI(component);
        this.frameBorder = new TinyInternalFrameBorder();
        this.frame.setBorder(this.frameBorder);
        if (Theme.frameIsTransparent[Theme.derivedStyle[Theme.style]]) {
            this.frame.setOpaque(false);
        }
    }
    
    protected PropertyChangeListener createPropertyChangeListener() {
        return new TinyInternalFramePropertyChangeListener();
    }
    
    protected JComponent createNorthPane(final JInternalFrame internalFrame) {
        super.createNorthPane(internalFrame);
        return this.titlePane = new TinyInternalFrameTitlePane(internalFrame);
    }
    
    protected void activateFrame(final JInternalFrame internalFrame) {
        super.activateFrame(internalFrame);
        this.frameBorder.setActive(true);
        this.titlePane.activate();
    }
    
    protected void deactivateFrame(final JInternalFrame internalFrame) {
        super.deactivateFrame(internalFrame);
        this.frameBorder.setActive(false);
        this.titlePane.deactivate();
    }
    
    public void setPalette(final boolean palette) {
        this.titlePane.setPalette(palette);
        this.frame.setBorder(this.frameBorder);
        this.frame.putClientProperty("isPalette", palette ? Boolean.TRUE : Boolean.FALSE);
    }
    
    public class TinyInternalFramePropertyChangeListener extends InternalFramePropertyChangeListener
    {
        public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
            final String propertyName = propertyChangeEvent.getPropertyName();
            final TinyInternalFrameUI tinyInternalFrameUI = (TinyInternalFrameUI)((JInternalFrame)propertyChangeEvent.getSource()).getUI();
            if (propertyName.equals("JInternalFrame.isPalette")) {
                if (propertyChangeEvent.getNewValue() != null) {
                    tinyInternalFrameUI.setPalette((boolean)propertyChangeEvent.getNewValue());
                }
                else {
                    tinyInternalFrameUI.setPalette(false);
                }
            }
            super.propertyChange(propertyChangeEvent);
        }
    }
}
