// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import javax.swing.JComponent;
import javax.swing.plaf.ButtonUI;
import javax.swing.JButton;

public class SpecialUIButton extends JButton
{
    public SpecialUIButton(final ButtonUI ui) {
        (this.ui = ui).installUI(this);
    }
    
    public void setUI(final ButtonUI buttonUI) {
    }
}
