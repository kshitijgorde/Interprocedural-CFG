// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

public class SingletonModelMulticaster implements SingletonModelListener
{
    private transient SingletonModelListener[] listeners;
    
    public void modelContentChanged(final SingletonModelEvent e) {
        if (this.listeners != null) {
            this.dispatch(e);
        }
    }
    
    public final boolean hasListeners() {
        return this.listeners != null;
    }
    
    public final void dispatch(final SingletonModelEvent e) {
        final SingletonModelListener[] listenersCopy = this.listeners;
        if (listenersCopy != null) {
            for (int count = listenersCopy.length, index = 0; index < count; ++index) {
                listenersCopy[index].modelContentChanged(e);
            }
        }
    }
    
    public int find(final SingletonModelListener listener) {
        if (this.listeners != null) {
            for (int index = 0; index < this.listeners.length; ++index) {
                if (this.listeners[index] == listener) {
                    return index;
                }
            }
        }
        return -1;
    }
    
    public final synchronized void add(final SingletonModelListener listener) {
        if (this.find(listener) < 0) {
            SingletonModelListener[] newListeners;
            if (this.listeners == null) {
                newListeners = new SingletonModelListener[] { null };
            }
            else {
                newListeners = new SingletonModelListener[this.listeners.length + 1];
                System.arraycopy(this.listeners, 0, newListeners, 0, this.listeners.length);
            }
            newListeners[newListeners.length - 1] = listener;
            this.listeners = newListeners;
        }
    }
    
    public final synchronized void remove(final SingletonModelListener listener) {
        final int index = this.find(listener);
        if (index > -1) {
            if (this.listeners.length == 1) {
                this.listeners = null;
            }
            else {
                final SingletonModelListener[] newListeners = new SingletonModelListener[this.listeners.length - 1];
                System.arraycopy(this.listeners, 0, newListeners, 0, index);
                if (index < newListeners.length) {
                    System.arraycopy(this.listeners, index + 1, newListeners, index, newListeners.length - index);
                }
                this.listeners = newListeners;
            }
        }
    }
}
