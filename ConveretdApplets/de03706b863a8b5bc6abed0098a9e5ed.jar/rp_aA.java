import javax.swing.plaf.basic.ComboPopup;
import com.sun.java.swing.plaf.motif.MotifComboBoxUI;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_aA extends MotifComboBoxUI
{
    rp_aA(final rp_ef rp_ef) {
    }
    
    protected final ComboPopup createPopup() {
        return new rp_du(this.comboBox);
    }
}
