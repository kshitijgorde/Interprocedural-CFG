// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;
import javax.swing.plaf.basic.BasicTextPaneUI;

public class TinyTextPaneUI extends BasicTextPaneUI
{
    JTextComponent editor;
    
    public static ComponentUI createUI(final JComponent component) {
        return new TinyTextPaneUI();
    }
    
    public void installUI(final JComponent component) {
        if (component instanceof JTextComponent) {
            this.editor = (JTextComponent)component;
        }
        super.installUI(component);
    }
    
    protected void installDefaults() {
        super.installDefaults();
    }
}
