// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import de.muntjak.tinylookandfeel.borders.TinyToolButtonBorder;
import javax.swing.plaf.UIResource;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.AbstractButton;
import java.awt.Color;
import javax.swing.plaf.ColorUIResource;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.Dialog;
import javax.swing.JDialog;
import java.awt.Frame;
import java.awt.Component;
import javax.swing.SwingUtilities;
import javax.swing.RootPaneContainer;
import javax.swing.JToolBar;
import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.plaf.metal.MetalToolBarUI;

public class TinyToolBarUI extends MetalToolBarUI
{
    public static final String IS_TOOL_BAR_BUTTON_KEY = "JToolBar.isToolbarButton";
    public static final int floatableGripSize = 8;
    private static Border toolButtonBorder;
    
    public static ComponentUI createUI(final JComponent component) {
        return new TinyToolBarUI();
    }
    
    public void installUI(final JComponent component) {
        super.installUI(component);
        component.putClientProperty("JToolBar.isRollover", Boolean.TRUE);
    }
    
    protected RootPaneContainer createFloatingWindow(final JToolBar toolBar) {
        final Window windowAncestor = SwingUtilities.getWindowAncestor(toolBar);
        JDialog dialog;
        if (windowAncestor instanceof Frame) {
            dialog = new JDialog((Frame)windowAncestor, toolBar.getName(), false);
        }
        else if (windowAncestor instanceof Dialog) {
            dialog = new JDialog((Dialog)windowAncestor, toolBar.getName(), false);
        }
        else {
            dialog = new JDialog((Frame)null, toolBar.getName(), false);
        }
        dialog.setTitle(toolBar.getName());
        dialog.setResizable(false);
        dialog.addWindowListener(this.createFrameListener());
        return dialog;
    }
    
    public void paint(final Graphics graphics, final JComponent component) {
        if (component.getBackground() instanceof ColorUIResource) {
            graphics.setColor(Theme.toolBarColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(component.getBackground());
        }
        graphics.fillRect(0, 0, component.getWidth(), component.getHeight());
    }
    
    protected void setBorderToRollover(final Component borderToNormal) {
        this.setBorderToNormal(borderToNormal);
    }
    
    protected void setBorderToNormal(final Component component) {
        if (!(component instanceof AbstractButton)) {
            return;
        }
        if (component instanceof JCheckBox) {
            return;
        }
        if (component instanceof JRadioButton) {
            return;
        }
        final AbstractButton abstractButton = (AbstractButton)component;
        abstractButton.setRolloverEnabled(true);
        abstractButton.putClientProperty("JToolBar.isToolbarButton", Boolean.TRUE);
        if (!(abstractButton.getBorder() instanceof UIResource) && !(abstractButton.getBorder() instanceof TinyToolButtonBorder)) {
            return;
        }
        abstractButton.setBorder(TinyToolBarUI.toolButtonBorder);
    }
    
    static {
        TinyToolBarUI.toolButtonBorder = new TinyToolButtonBorder();
    }
}
