// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.util.Vector;

public class mac extends Vector
{
    public boolean a;
    
    public synchronized void a() {
        this.a = false;
        this.removeAllElements();
        this.notify();
    }
    
    public synchronized int a(final Object o) {
        if (this.a) {
            this.addElement(o);
            this.notify();
        }
        return super.elementCount;
    }
    
    public synchronized Object[] b() throws InterruptedException {
        while (this.a && super.elementCount == 0) {
            this.wait();
        }
        final Object[] array = new Object[super.elementCount];
        System.arraycopy(super.elementData, 0, array, 0, super.elementCount);
        this.removeAllElements();
        return array;
    }
    
    public mac() {
        this.a = true;
    }
}
