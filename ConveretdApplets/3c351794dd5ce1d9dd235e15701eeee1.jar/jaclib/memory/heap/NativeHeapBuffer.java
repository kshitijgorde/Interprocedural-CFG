// 
// Decompiled by Procyon v0.5.30
// 

package jaclib.memory.heap;

import jaclib.memory.Source;
import jaclib.memory.Buffer;

public final class NativeHeapBuffer implements Buffer, Source
{
    private NativeHeap a;
    private int b;
    public int c;
    private boolean d;
    
    private final synchronized boolean a() {
        try {
            return this.a.a() && this.d;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    private final synchronized void b() {
        try {
            if (this.a()) {
                this.a.deallocateBuffer(this.b);
            }
            this.d = false;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    public final int getSize() {
        try {
            return this.c;
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
    
    @Override
    public final synchronized void a(final byte[] array, final int n, final int n2, final int n3) {
        try {
            if (!this.a() | array == null | n < 0 | array.length < n - -n3 | n2 < 0 | ~this.c > ~(n3 + n2)) {
                throw new RuntimeException();
            }
            this.a.put(this.b, array, n, n2, n3);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    public final long getAddress() {
        try {
            return this.a.getBufferAddress(this.b);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    NativeHeapBuffer(final NativeHeap a, final int b, final int c) {
        this.d = true;
        try {
            this.a = a;
            this.c = c;
            this.b = b;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
}
