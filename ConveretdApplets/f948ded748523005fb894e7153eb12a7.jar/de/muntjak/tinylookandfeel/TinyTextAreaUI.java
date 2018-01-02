// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicTextAreaUI;

public class TinyTextAreaUI extends BasicTextAreaUI
{
    public static ComponentUI createUI(final JComponent component) {
        return new TinyTextAreaUI();
    }
}
