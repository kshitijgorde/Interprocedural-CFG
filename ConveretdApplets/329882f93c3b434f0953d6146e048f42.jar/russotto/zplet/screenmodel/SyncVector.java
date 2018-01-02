// 
// Decompiled by Procyon v0.5.30
// 

package russotto.zplet.screenmodel;

import java.util.NoSuchElementException;
import java.util.Vector;

class SyncVector extends Vector
{
    public synchronized Object syncPopFirstElement() {
        final Object syncFirstElement = this.syncFirstElement();
        if (syncFirstElement != null) {
            this.removeElementAt(0);
        }
        return syncFirstElement;
    }
    
    public synchronized Object syncFirstElement() {
        Object firstElement = null;
        try {
            firstElement = super.firstElement();
        }
        catch (NoSuchElementException ex) {}
        try {
            if (firstElement != null) {
                return firstElement;
            }
            this.wait();
        }
        catch (InterruptedException ex2) {}
        return null;
    }
    
    public synchronized void syncAddElement(final Object o) {
        super.addElement(o);
        this.notify();
    }
}
