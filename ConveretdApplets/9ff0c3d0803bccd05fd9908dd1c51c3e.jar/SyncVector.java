import java.util.NoSuchElementException;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class SyncVector extends Vector
{
    public synchronized Object syncPopFirstElement() {
        final Object first = this.syncFirstElement();
        if (first != null) {
            this.removeElementAt(0);
        }
        return first;
    }
    
    public synchronized Object syncFirstElement() {
        Object first = null;
        try {
            first = super.firstElement();
        }
        catch (NoSuchElementException ex) {}
        try {
            if (first != null) {
                return first;
            }
            this.wait();
        }
        catch (InterruptedException ex2) {}
        return null;
    }
    
    public synchronized void syncAddElement(final Object obj) {
        super.addElement(obj);
        this.notify();
    }
}
