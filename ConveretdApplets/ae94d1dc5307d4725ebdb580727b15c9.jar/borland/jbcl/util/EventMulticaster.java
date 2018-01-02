// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.util;

import java.util.EventListener;

public class EventMulticaster
{
    private transient EventListener[] listeners;
    
    public final boolean hasListeners() {
        return this.listeners != null;
    }
    
    public final void dispatch(final DispatchableEvent e) {
        final EventListener[] listenersCopy = this.listeners;
        if (listenersCopy != null) {
            for (int count = listenersCopy.length, index = 0; index < count; ++index) {
                e.dispatch(listenersCopy[index]);
            }
        }
    }
    
    public final boolean vetoableDispatch(final VetoableDispatch e) {
        final EventListener[] listenersCopy = this.listeners;
        try {
            if (listenersCopy != null) {
                for (int count = listenersCopy.length, index = 0; index < count; ++index) {
                    e.vetoableDispatch(listenersCopy[index]);
                }
            }
        }
        catch (VetoException ex) {
            return false;
        }
        return true;
    }
    
    public final void exceptionDispatch(final ExceptionDispatch e) throws Exception {
        final EventListener[] listenersCopy = this.listeners;
        if (listenersCopy != null) {
            for (int count = listenersCopy.length, index = 0; index < count; ++index) {
                e.exceptionDispatch(listenersCopy[index]);
            }
        }
    }
    
    public int find(final EventListener listener) {
        if (this.listeners != null) {
            for (int index = 0; index < this.listeners.length; ++index) {
                if (this.listeners[index] == listener) {
                    return index;
                }
            }
        }
        return -1;
    }
    
    public final synchronized void add(final EventListener listener) {
        if (this.find(listener) < 0) {
            EventListener[] newListeners;
            if (this.listeners == null) {
                newListeners = new EventListener[] { null };
            }
            else {
                newListeners = new EventListener[this.listeners.length + 1];
                System.arraycopy(this.listeners, 0, newListeners, 0, this.listeners.length);
            }
            newListeners[newListeners.length - 1] = listener;
            this.listeners = newListeners;
        }
    }
    
    public final synchronized void remove(final EventListener listener) {
        final int index = this.find(listener);
        if (index > -1) {
            if (this.listeners.length == 1) {
                this.listeners = null;
            }
            else {
                final EventListener[] newListeners = new EventListener[this.listeners.length - 1];
                System.arraycopy(this.listeners, 0, newListeners, 0, index);
                if (index < newListeners.length) {
                    System.arraycopy(this.listeners, index + 1, newListeners, index, newListeners.length - index);
                }
                this.listeners = newListeners;
            }
        }
    }
    
    public static final EventMulticaster add(EventMulticaster caster, final EventListener listener) {
        if (caster == null) {
            caster = new EventMulticaster();
        }
        caster.add(listener);
        return caster;
    }
    
    public static final EventMulticaster remove(EventMulticaster caster, final EventListener listener) {
        if (caster != null) {
            caster.remove(listener);
            if (!caster.hasListeners()) {
                caster = null;
            }
        }
        return caster;
    }
}
