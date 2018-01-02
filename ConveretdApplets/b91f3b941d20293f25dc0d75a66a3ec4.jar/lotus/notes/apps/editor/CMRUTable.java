// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

class CMRUTable
{
    private Object[] cKey;
    private Object[] cValue;
    
    CMRUTable(final int n) {
        this.cKey = new Object[n];
        this.cValue = new Object[n];
    }
    
    boolean contains(final Object o) {
        for (int n = 0; n < this.cKey.length && this.cKey[n] != null; ++n) {
            if (this.cKey[n].equals(o)) {
                if (n > 0) {
                    synchronized (this) {
                        final Object o2 = this.cKey[n];
                        final Object o3 = this.cValue[n];
                        System.arraycopy(this.cKey, 0, this.cKey, 1, n);
                        System.arraycopy(this.cValue, 0, this.cValue, 1, n);
                        this.cKey[0] = o2;
                        this.cValue[0] = o3;
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    void put(final Object o, final Object o2) {
        if (!this.contains(o)) {
            synchronized (this) {
                final int n = this.cKey.length - 1;
                System.arraycopy(this.cKey, 0, this.cKey, 1, n);
                System.arraycopy(this.cValue, 0, this.cValue, 1, n);
                this.cKey[0] = o;
                this.cValue[0] = o2;
            }
        }
    }
    
    Object get(final Object o) {
        if (this.contains(o)) {
            return this.cValue[0];
        }
        return null;
    }
}
