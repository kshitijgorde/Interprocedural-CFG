// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.util.Iterator;
import java.io.File;
import java.util.HashSet;

class o extends Thread
{
    private HashSet A;
    
    o() {
        this.A = null;
        this.setPriority(1);
    }
    
    public void run() {
        if (this.A != null && this.A.size() > 0) {
            final Iterator<File> iterator = this.A.iterator();
            while (iterator.hasNext()) {
                try {
                    iterator.next().delete();
                }
                catch (Exception ex) {}
            }
        }
    }
    
    synchronized void B(final File file) {
        if (this.A == null) {
            this.A = new HashSet();
        }
        this.A.add(file);
    }
    
    synchronized void A(final File file) {
        this.A.remove(file);
    }
}
