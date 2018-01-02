// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import java.awt.Graphics;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicMenuBarUI;

public class TinyMenuBarUI extends BasicMenuBarUI
{
    private static final boolean DEBUG = true;
    private static final String CLOSE_OPENED_MENU_KEY = "closeOpenedMenu";
    
    public static ComponentUI createUI(final JComponent component) {
        component.setBorder(new EmptyBorder(0, 5, 0, 0));
        return new TinyMenuBarUI();
    }
    
    public void paint(final Graphics graphics, final JComponent component) {
        if (!component.isOpaque()) {
            return;
        }
        graphics.setColor(component.getBackground());
        graphics.fillRect(0, 0, component.getWidth(), component.getHeight());
    }
}
