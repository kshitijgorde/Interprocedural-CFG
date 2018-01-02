// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import javax.swing.plaf.metal.MetalToolTipUI;

public class TinyToolTipUI extends MetalToolTipUI
{
    protected static TinyToolTipUI sharedInstance;
    
    public static ComponentUI createUI(final JComponent component) {
        return TinyToolTipUI.sharedInstance;
    }
    
    protected void installDefaults(final JComponent component) {
        super.installDefaults(component);
    }
    
    static {
        TinyToolTipUI.sharedInstance = new TinyToolTipUI();
    }
}
