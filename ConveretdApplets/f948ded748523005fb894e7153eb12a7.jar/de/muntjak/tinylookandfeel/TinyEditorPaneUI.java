// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;
import javax.swing.plaf.basic.BasicEditorPaneUI;

public class TinyEditorPaneUI extends BasicEditorPaneUI
{
    JTextComponent editor;
    
    public static ComponentUI createUI(final JComponent component) {
        return new TinyEditorPaneUI();
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
