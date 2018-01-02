// 
// Decompiled by Procyon v0.5.30
// 

package jaclib.memory;

public class NativeBuffer implements Buffer, Source
{
    private long a;
    private int b;
    
    public NativeBuffer() {
        this.b = -1;
    }
    
    @Override
    public final long getAddress() {
        try {
            return this.a;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    private final native void get(final long p0, final byte[] p1, final int p2, final int p3, final int p4);
    
    @Override
    public void a(final byte[] array, final int n, final int n2, final int n3) {
        try {
            if (n + n3 > array.length | (~n > -1 | (array == null | ~this.a == -1L)) | ~n2 > -1 | this.b < n3 + n2) {
                throw new RuntimeException();
            }
            this.put(this.a, array, n, n2, n3);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    public final int getSize() {
        try {
            return this.b;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    protected final void a(final long a, final int b) {
        try {
            this.a = a;
            this.b = b;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    private final native void put(final long p0, final byte[] p1, final int p2, final int p3, final int p4);
}
