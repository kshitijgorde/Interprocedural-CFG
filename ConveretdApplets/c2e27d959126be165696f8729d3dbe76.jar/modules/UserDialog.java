// 
// Decompiled by Procyon v0.5.30
// 

package modules;

import java.awt.Event;
import java.awt.Component;
import java.awt.Label;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.Dialog;

class UserDialog extends Dialog
{
    TextField input;
    Thread thread;
    String value;
    
    public UserDialog(final Frame parent, final String value, final boolean modal, final Thread t, final TextField reply) {
        super(parent, value, modal);
        this.thread = t;
        this.input = reply;
        this.add("West", new Label(value));
        this.add("Center", this.input);
        this.pack();
        this.show();
    }
    
    public boolean handleEvent(final Event evt) {
        if (evt.target == this.input && evt.key == 10) {
            this.thread.resume();
            this.hide();
            this.dispose();
            return true;
        }
        return false;
    }
}
