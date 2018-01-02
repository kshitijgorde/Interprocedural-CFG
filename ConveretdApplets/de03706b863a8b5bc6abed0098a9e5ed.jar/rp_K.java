import javax.swing.plaf.basic.ComboPopup;
import com.sun.java.swing.plaf.windows.WindowsComboBoxUI;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_K extends WindowsComboBoxUI
{
    rp_K(final rp_ef rp_ef) {
    }
    
    protected final ComboPopup createPopup() {
        return new rp_du(this.comboBox);
    }
}
