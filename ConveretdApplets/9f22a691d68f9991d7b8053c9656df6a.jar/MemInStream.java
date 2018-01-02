// 
// Decompiled by Procyon v0.5.30
// 

public class MemInStream extends InStream
{
    public MemInStream(final byte[] b, final int ptr, final int n) {
        super.b = b;
        super.ptr = ptr;
        super.end = ptr + n;
    }
    
    public int pos() {
        return super.ptr;
    }
    
    protected int overrun(final int n, final int n2) throws Exception {
        throw new Exception("MemInStream overrun: end of stream");
    }
}
