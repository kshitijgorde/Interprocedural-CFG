// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicTableUI;

public class TinyTableUI extends BasicTableUI
{
    JTable table;
    
    public TinyTableUI() {
    }
    
    public TinyTableUI(final JComponent component) {
        this.table = (JTable)component;
    }
    
    public static ComponentUI createUI(final JComponent component) {
        return new TinyTableUI(component);
    }
    
    protected void installDefaults() {
        super.installDefaults();
    }
}
