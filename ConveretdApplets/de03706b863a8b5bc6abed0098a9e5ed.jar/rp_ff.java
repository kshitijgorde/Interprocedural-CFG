import javax.swing.plaf.basic.ComboPopup;
import javax.swing.plaf.metal.MetalComboBoxUI;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_ff extends MetalComboBoxUI
{
    rp_ff(final rp_ef rp_ef) {
    }
    
    protected final ComboPopup createPopup() {
        return new rp_du(this.comboBox);
    }
}
