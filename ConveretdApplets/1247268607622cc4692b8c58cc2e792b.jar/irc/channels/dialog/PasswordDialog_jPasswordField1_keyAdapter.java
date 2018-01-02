// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.dialog;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

class PasswordDialog_jPasswordField1_keyAdapter extends KeyAdapter
{
    private PasswordDialog adaptee;
    
    PasswordDialog_jPasswordField1_keyAdapter(final PasswordDialog adaptee) {
        this.adaptee = adaptee;
    }
    
    @Override
    public void keyReleased(final KeyEvent keyEvent) {
        this.adaptee.jPasswordField1_keyReleased(keyEvent);
    }
}
