// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.SWTEventListener;

class EventTable
{
    int[] types;
    Listener[] listeners;
    int level;
    static final int GROW_SIZE = 4;
    
    public Listener[] getListeners(final int n) {
        if (this.types == null) {
            return new Listener[0];
        }
        int n2 = 0;
        for (int i = 0; i < this.types.length; ++i) {
            if (this.types[i] == n) {
                ++n2;
            }
        }
        if (n2 == 0) {
            return new Listener[0];
        }
        final Listener[] array = new Listener[n2];
        int n3 = 0;
        for (int j = 0; j < this.types.length; ++j) {
            if (this.types[j] == n) {
                array[n3++] = this.listeners[j];
            }
        }
        return array;
    }
    
    public void hook(final int n, final Listener listener) {
        if (this.types == null) {
            this.types = new int[4];
        }
        if (this.listeners == null) {
            this.listeners = new Listener[4];
        }
        final int length = this.types.length;
        int n2;
        for (n2 = length - 1; n2 >= 0 && this.types[n2] == 0; --n2) {}
        if (++n2 == length) {
            final int[] types = new int[length + 4];
            System.arraycopy(this.types, 0, types, 0, length);
            this.types = types;
            final Listener[] listeners = new Listener[length + 4];
            System.arraycopy(this.listeners, 0, listeners, 0, length);
            this.listeners = listeners;
        }
        this.types[n2] = n;
        this.listeners[n2] = listener;
    }
    
    public boolean hooks(final int n) {
        if (this.types == null) {
            return false;
        }
        for (int i = 0; i < this.types.length; ++i) {
            if (this.types[i] == n) {
                return true;
            }
        }
        return false;
    }
    
    public void sendEvent(final Event event) {
        if (this.types == null) {
            return;
        }
        this.level += ((this.level >= 0) ? 1 : -1);
        try {
            for (int i = 0; i < this.types.length; ++i) {
                if (event.type == 0) {
                    return;
                }
                if (this.types[i] == event.type) {
                    final Listener listener = this.listeners[i];
                    if (listener != null) {
                        listener.handleEvent(event);
                    }
                }
            }
        }
        finally {
            final boolean b = this.level < 0;
            this.level -= ((this.level >= 0) ? 1 : -1);
            if (b && this.level == 0) {
                int n = 0;
                for (int j = 0; j < this.types.length; ++j) {
                    if (this.types[j] != 0) {
                        this.types[n] = this.types[j];
                        this.listeners[n] = this.listeners[j];
                        ++n;
                    }
                }
                for (int k = n; k < this.types.length; ++k) {
                    this.types[k] = 0;
                    this.listeners[k] = null;
                }
            }
        }
        final boolean b2 = this.level < 0;
        this.level -= ((this.level >= 0) ? 1 : -1);
        if (b2 && this.level == 0) {
            int n2 = 0;
            for (int l = 0; l < this.types.length; ++l) {
                if (this.types[l] != 0) {
                    this.types[n2] = this.types[l];
                    this.listeners[n2] = this.listeners[l];
                    ++n2;
                }
            }
            for (int n3 = n2; n3 < this.types.length; ++n3) {
                this.types[n3] = 0;
                this.listeners[n3] = null;
            }
        }
    }
    
    public int size() {
        if (this.types == null) {
            return 0;
        }
        int n = 0;
        for (int i = 0; i < this.types.length; ++i) {
            if (this.types[i] != 0) {
                ++n;
            }
        }
        return n;
    }
    
    void remove(int n) {
        if (this.level == 0) {
            final int n2 = this.types.length - 1;
            System.arraycopy(this.types, n + 1, this.types, n, n2 - n);
            System.arraycopy(this.listeners, n + 1, this.listeners, n, n2 - n);
            n = n2;
        }
        else if (this.level > 0) {
            this.level = -this.level;
        }
        this.types[n] = 0;
        this.listeners[n] = null;
    }
    
    public void unhook(final int n, final Listener listener) {
        if (this.types == null) {
            return;
        }
        for (int i = 0; i < this.types.length; ++i) {
            if (this.types[i] == n && this.listeners[i] == listener) {
                this.remove(i);
                return;
            }
        }
    }
    
    public void unhook(final int n, final SWTEventListener swtEventListener) {
        if (this.types == null) {
            return;
        }
        for (int i = 0; i < this.types.length; ++i) {
            if (this.types[i] == n && this.listeners[i] instanceof TypedListener && ((TypedListener)this.listeners[i]).getEventListener() == swtEventListener) {
                this.remove(i);
                return;
            }
        }
    }
}
