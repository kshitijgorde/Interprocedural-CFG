// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.util.Vector;

public class cf extends Vector
{
    private boolean hf;
    
    public boolean zb() {
        return this.hf;
    }
    
    public synchronized void c() {
        this.hf = false;
        this.removeAllElements();
        this.notify();
    }
    
    public synchronized int id(final Object o) {
        if (this.hf) {
            this.addElement(o);
            this.notify();
        }
        return super.elementCount;
    }
    
    public synchronized Object[] hd() throws InterruptedException {
        while (this.hf && super.elementCount == 0) {
            this.wait();
        }
        final Object[] array = new Object[super.elementCount];
        System.arraycopy(super.elementData, 0, array, 0, super.elementCount);
        this.removeAllElements();
        return array;
    }
    
    public cf() {
        this.hf = true;
    }
}
