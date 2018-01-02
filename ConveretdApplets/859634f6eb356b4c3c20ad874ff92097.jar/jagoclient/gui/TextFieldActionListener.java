// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TextFieldActionListener implements ActionListener
{
    DoActionListener C;
    String Name;
    
    public TextFieldActionListener(final DoActionListener c, final String name) {
        this.C = c;
        this.Name = name;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.C.doAction(this.Name);
    }
}
