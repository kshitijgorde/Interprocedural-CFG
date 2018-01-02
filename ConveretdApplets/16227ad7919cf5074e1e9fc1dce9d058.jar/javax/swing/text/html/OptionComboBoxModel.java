// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text.html;

import java.io.Serializable;
import javax.swing.DefaultComboBoxModel;

class OptionComboBoxModel extends DefaultComboBoxModel implements Serializable
{
    private Option selectedOption;
    
    OptionComboBoxModel() {
        this.selectedOption = null;
    }
    
    public Option getInitialSelection() {
        return this.selectedOption;
    }
    
    public void setInitialSelection(final Option selectedOption) {
        this.selectedOption = selectedOption;
    }
}
