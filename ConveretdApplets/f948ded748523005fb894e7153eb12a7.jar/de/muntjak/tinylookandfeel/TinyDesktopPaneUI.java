// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicDesktopPaneUI;

public class TinyDesktopPaneUI extends BasicDesktopPaneUI
{
    public static ComponentUI createUI(final JComponent component) {
        return new TinyDesktopPaneUI();
    }
    
    public void installUI(final JComponent component) {
        super.installUI(component);
    }
}
