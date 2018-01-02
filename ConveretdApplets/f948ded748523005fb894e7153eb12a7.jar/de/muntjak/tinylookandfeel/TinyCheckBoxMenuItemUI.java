// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;

public class TinyCheckBoxMenuItemUI extends TinyMenuItemUI
{
    public static ComponentUI createUI(final JComponent component) {
        return new TinyCheckBoxMenuItemUI();
    }
    
    protected String getPropertyPrefix() {
        return "CheckBoxMenuItem";
    }
}
