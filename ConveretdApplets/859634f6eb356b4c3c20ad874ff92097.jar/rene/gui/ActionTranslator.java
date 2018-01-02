// 
// Decompiled by Procyon v0.5.30
// 

package rene.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionTranslator implements ActionListener
{
    String Name;
    DoActionListener C;
    
    public ActionTranslator(final DoActionListener c, final String name) {
        this.Name = name;
        this.C = c;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.C.doAction(this.Name);
    }
}
