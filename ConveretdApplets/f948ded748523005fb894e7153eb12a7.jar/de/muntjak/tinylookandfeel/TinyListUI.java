// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicListUI;

public class TinyListUI extends BasicListUI
{
    private JComponent list;
    
    public TinyListUI() {
    }
    
    public TinyListUI(final JComponent list) {
        this.list = list;
    }
    
    public static ComponentUI createUI(final JComponent component) {
        return new TinyListUI(component);
    }
}
