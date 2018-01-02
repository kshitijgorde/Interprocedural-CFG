// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import javax.swing.plaf.metal.MetalSplitPaneUI;

public class TinySplitPaneUI extends MetalSplitPaneUI
{
    public static ComponentUI createUI(final JComponent component) {
        return new TinySplitPaneUI();
    }
    
    public BasicSplitPaneDivider createDefaultDivider() {
        return new TinySplitPaneDivider(this);
    }
}
