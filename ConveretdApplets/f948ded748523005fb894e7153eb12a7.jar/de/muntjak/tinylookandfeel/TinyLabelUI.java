// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import javax.swing.plaf.metal.MetalLabelUI;

public class TinyLabelUI extends MetalLabelUI
{
    protected static TinyLabelUI sharedInstance;
    
    public static ComponentUI createUI(final JComponent component) {
        return TinyLabelUI.sharedInstance;
    }
    
    static {
        TinyLabelUI.sharedInstance = new TinyLabelUI();
    }
}
