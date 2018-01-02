// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.ole.win32;

class OleEventTable
{
    int[] types;
    OleListener[] handlers;
    
    void hook(final int n, final OleListener oleListener) {
        if (this.types == null) {
            this.types = new int[4];
        }
        if (this.handlers == null) {
            this.handlers = new OleListener[4];
        }
        for (int i = 0; i < this.types.length; ++i) {
            if (this.types[i] == 0) {
                this.types[i] = n;
                this.handlers[i] = oleListener;
                return;
            }
        }
        final int length = this.types.length;
        final int[] types = new int[length + 4];
        final OleListener[] handlers = new OleListener[length + 4];
        System.arraycopy(this.types, 0, types, 0, length);
        System.arraycopy(this.handlers, 0, handlers, 0, length);
        this.types = types;
        this.handlers = handlers;
        this.types[length] = n;
        this.handlers[length] = oleListener;
    }
    
    boolean hooks(final int n) {
        if (this.handlers == null) {
            return false;
        }
        for (int i = 0; i < this.types.length; ++i) {
            if (this.types[i] == n) {
                return true;
            }
        }
        return false;
    }
    
    void sendEvent(final OleEvent oleEvent) {
        if (this.handlers == null) {
            return;
        }
        for (int i = 0; i < this.types.length; ++i) {
            if (this.types[i] == oleEvent.type) {
                final OleListener oleListener = this.handlers[i];
                if (oleListener != null) {
                    oleListener.handleEvent(oleEvent);
                }
            }
        }
    }
    
    void unhook(final int n, final OleListener oleListener) {
        if (this.handlers == null) {
            return;
        }
        for (int i = 0; i < this.types.length; ++i) {
            if (this.types[i] == n && this.handlers[i] == oleListener) {
                this.types[i] = 0;
                this.handlers[i] = null;
                return;
            }
        }
    }
    
    boolean hasEntries() {
        for (int i = 0; i < this.types.length; ++i) {
            if (this.types[i] != 0) {
                return true;
            }
        }
        return false;
    }
}
