// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyMulticaster implements KeyListener
{
    private transient KeyListener[] listeners;
    
    public void keyTyped(final KeyEvent e) {
        if (this.listeners != null) {
            this.dispatch(e);
        }
    }
    
    public void keyPressed(final KeyEvent e) {
        if (this.listeners != null) {
            this.dispatch(e);
        }
    }
    
    public void keyReleased(final KeyEvent e) {
        if (this.listeners != null) {
            this.dispatch(e);
        }
    }
    
    public final boolean hasListeners() {
        return this.listeners != null;
    }
    
    public final void dispatch(final KeyEvent e) {
        final KeyListener[] listenersCopy = this.listeners;
        if (listenersCopy != null) {
            for (int count = listenersCopy.length, index = 0; index < count; ++index) {
                switch (e.getID()) {
                    case 400: {
                        listenersCopy[index].keyTyped(e);
                        break;
                    }
                    case 401: {
                        listenersCopy[index].keyPressed(e);
                        break;
                    }
                    case 402: {
                        listenersCopy[index].keyReleased(e);
                        break;
                    }
                }
            }
        }
    }
    
    public int find(final KeyListener listener) {
        if (this.listeners != null) {
            for (int index = 0; index < this.listeners.length; ++index) {
                if (this.listeners[index] == listener) {
                    return index;
                }
            }
        }
        return -1;
    }
    
    public final synchronized void add(final KeyListener listener) {
        if (this.find(listener) < 0) {
            KeyListener[] newListeners;
            if (this.listeners == null) {
                newListeners = new KeyListener[] { null };
            }
            else {
                newListeners = new KeyListener[this.listeners.length + 1];
                System.arraycopy(this.listeners, 0, newListeners, 0, this.listeners.length);
            }
            newListeners[newListeners.length - 1] = listener;
            this.listeners = newListeners;
        }
    }
    
    public final synchronized void remove(final KeyListener listener) {
        final int index = this.find(listener);
        if (index > -1) {
            if (this.listeners.length == 1) {
                this.listeners = null;
            }
            else {
                final KeyListener[] newListeners = new KeyListener[this.listeners.length - 1];
                System.arraycopy(this.listeners, 0, newListeners, 0, index);
                if (index < newListeners.length) {
                    System.arraycopy(this.listeners, index + 1, newListeners, index, newListeners.length - index);
                }
                this.listeners = newListeners;
            }
        }
    }
}
