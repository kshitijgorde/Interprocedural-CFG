// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

public final class NFBuffer
{
    int a;
    int b;
    byte[] c;
    
    public NFBuffer(final byte[] c) {
        this.c = c;
        this.a = 0;
        this.b = c.length;
    }
    
    public NFBuffer(final byte[] c, final int a) {
        this.c = c;
        final int length = this.c.length;
        this.a = a;
        this.b = length - a;
    }
    
    public NFBuffer(final byte[] c, final int a, final int b) {
        if (a + b > c.length) {
            throw new IndexOutOfBoundsException("NFBuffer range");
        }
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    public NFBuffer(final NFBuffer nfBuffer, final int n) {
        this.c = nfBuffer.c;
        this.a = nfBuffer.a + n;
        this.b = nfBuffer.length() - n;
    }
    
    public NFBuffer(final NFBuffer nfBuffer, final int n, final int b) {
        if (n + b > nfBuffer.b) {
            throw new IndexOutOfBoundsException("NFBuffer range");
        }
        this.c = nfBuffer.c;
        this.a = nfBuffer.a + n;
        this.b = b;
    }
    
    public final int length() {
        return this.b;
    }
    
    public final byte get(final int n) {
        if (n < 0 || n >= this.b) {
            throw new IndexOutOfBoundsException("NFBuffer range");
        }
        return this.c[this.a + n];
    }
    
    public final void put(final int n, final byte b) {
        if (n < 0 || n >= this.b) {
            throw new IndexOutOfBoundsException("NFBuffer range");
        }
        this.c[this.a + n] = b;
    }
    
    public final void copyout(final NFBuffer nfBuffer) {
        System.arraycopy(this.c, this.a, nfBuffer.c, nfBuffer.a, nfBuffer.b);
    }
    
    public final void copyin(final NFBuffer nfBuffer) {
        System.arraycopy(nfBuffer.c, nfBuffer.a, this.c, this.a, nfBuffer.b);
    }
    
    public final byte[] bytes() {
        final byte[] array = new byte[this.b];
        for (int i = 0; i < this.b; ++i) {
            array[i] = this.c[this.a + i];
        }
        return array;
    }
}
