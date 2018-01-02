// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionMulticaster implements ActionListener
{
    private ActionListener[] listeners;
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.listeners != null) {
            this.dispatch(actionEvent);
        }
    }
    
    public final boolean hasListeners() {
        return this.listeners != null;
    }
    
    public final void dispatch(final ActionEvent actionEvent) {
        final ActionListener[] listeners = this.listeners;
        if (listeners != null) {
            for (int length = listeners.length, i = 0; i < length; ++i) {
                listeners[i].actionPerformed(actionEvent);
            }
        }
    }
    
    public int find(final ActionListener actionListener) {
        if (this.listeners != null) {
            for (int i = 0; i < this.listeners.length; ++i) {
                if (this.listeners[i] == actionListener) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public final synchronized void add(final ActionListener actionListener) {
        if (this.find(actionListener) < 0) {
            ActionListener[] listeners;
            if (this.listeners == null) {
                listeners = new ActionListener[] { null };
            }
            else {
                listeners = new ActionListener[this.listeners.length + 1];
                System.arraycopy(this.listeners, 0, listeners, 0, this.listeners.length);
            }
            listeners[listeners.length - 1] = actionListener;
            this.listeners = listeners;
        }
    }
    
    public final synchronized void remove(final ActionListener actionListener) {
        final int find = this.find(actionListener);
        if (find > -1) {
            if (this.listeners.length == 1) {
                this.listeners = null;
            }
            else {
                final ActionListener[] listeners = new ActionListener[this.listeners.length - 1];
                System.arraycopy(this.listeners, 0, listeners, 0, find);
                if (find < listeners.length) {
                    System.arraycopy(this.listeners, find + 1, listeners, find, listeners.length - find);
                }
                this.listeners = listeners;
            }
        }
    }
}
