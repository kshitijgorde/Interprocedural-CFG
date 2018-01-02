// 
// Decompiled by Procyon v0.5.30
// 

package jaclib.memory.heap;

public final class NativeHeap
{
    private int a;
    private boolean b;
    long peer;
    
    final synchronized native long getBufferAddress(final int p0);
    
    private final native void deallocateHeap();
    
    private final native void allocateHeap(final int p0);
    
    public final NativeHeapBuffer a(final int n, final boolean b) {
        try {
            if (!this.b) {
                throw new IllegalStateException();
            }
            return new NativeHeapBuffer(this, this.allocateBuffer(n, b), n);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final synchronized native void get(final int p0, final byte[] p1, final int p2, final int p3, final int p4);
    
    final synchronized boolean a() {
        try {
            return this.b;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    protected final synchronized void finalize() throws Throwable {
        try {
            super.finalize();
            this.b();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final synchronized native void put(final int p0, final byte[] p1, final int p2, final int p3, final int p4);
    
    public NativeHeap(final int a) {
        try {
            this.allocateHeap(this.a = a);
            this.b = true;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final synchronized native void deallocateBuffer(final int p0);
    
    public final synchronized void b() {
        try {
            if (this.b) {
                this.deallocateHeap();
            }
            this.b = false;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final synchronized native int allocateBuffer(final int p0, final boolean p1);
}
