// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PasswordDialog_jButton2_actionAdapter implements ActionListener
{
    private PasswordDialog adaptee;
    
    PasswordDialog_jButton2_actionAdapter(final PasswordDialog adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        this.adaptee.jButton2_actionPerformed(actionEvent);
    }
}
