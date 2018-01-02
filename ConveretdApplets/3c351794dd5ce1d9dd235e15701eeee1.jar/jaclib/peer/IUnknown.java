// 
// Decompiled by Procyon v0.5.30
// 

package jaclib.peer;

public class IUnknown extends Peer
{
    protected IUnknown(final jaa jaa) {
        try {
            this.reference = new IUnknownReference(this, jaa);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public final long b(final int n) {
        long a;
        try {
            if (n < 22) {
                return -83L;
            }
            a = super.a();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return a;
    }
    
    public final native long AddRef();
}
