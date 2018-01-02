// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ar implements ActionListener
{
    private webphone a;
    
    ar(final webphone a) {
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.a.jComboBox1_actionPerformed(actionEvent);
    }
}
