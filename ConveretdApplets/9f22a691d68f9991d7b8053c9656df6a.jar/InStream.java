// 
// Decompiled by Procyon v0.5.30
// 

public abstract class InStream
{
    public static int maxStringLength;
    protected byte[] b;
    protected int ptr;
    protected int end;
    
    public final int check(final int n, int n2) throws Exception {
        if (this.ptr + n * n2 > this.end) {
            if (this.ptr + n > this.end) {
                return this.overrun(n, n2);
            }
            n2 = (this.end - this.ptr) / n;
        }
        return n2;
    }
    
    public final void check(final int n) throws Exception {
        if (this.ptr + n > this.end) {
            this.overrun(n, 1);
        }
    }
    
    public final int readS8() throws Exception {
        this.check(1);
        return this.b[this.ptr++];
    }
    
    public final int readS16() throws Exception {
        this.check(2);
        return this.b[this.ptr++] << 8 | (this.b[this.ptr++] & 0xFF);
    }
    
    public final int readS32() throws Exception {
        this.check(4);
        return this.b[this.ptr++] << 24 | (this.b[this.ptr++] & 0xFF) << 16 | (this.b[this.ptr++] & 0xFF) << 8 | (this.b[this.ptr++] & 0xFF);
    }
    
    public final int readU8() throws Exception {
        return this.readS8() & 0xFF;
    }
    
    public final int readU16() throws Exception {
        return this.readS16() & 0xFFFF;
    }
    
    public final int readU32() throws Exception {
        return this.readS32() & -1;
    }
    
    public final String readString() throws Exception {
        final int u32 = this.readU32();
        if (u32 > InStream.maxStringLength) {
            throw new Exception("InStream max string length exceeded");
        }
        final char[] array = new char[u32];
        int i = 0;
        while (i < u32) {
            while (i < i + this.check(1, u32 - i)) {
                array[i++] = (char)this.b[this.ptr++];
            }
        }
        return new String(array);
    }
    
    public final void skip(int i) throws Exception {
        while (i > 0) {
            final int check = this.check(1, i);
            this.ptr += check;
            i -= check;
        }
    }
    
    public void readBytes(final byte[] array, int i, final int n) throws Exception {
        int check;
        for (int n2 = i + n; i < n2; i += check) {
            check = this.check(1, n2 - i);
            System.arraycopy(this.b, this.ptr, array, i, check);
            this.ptr += check;
        }
    }
    
    public final int readOpaque8() throws Exception {
        return this.readU8();
    }
    
    public final int readOpaque16() throws Exception {
        return this.readU16();
    }
    
    public final int readOpaque32() throws Exception {
        return this.readU32();
    }
    
    public final int readOpaque24A() throws Exception {
        this.check(3);
        return this.b[this.ptr++] << 24 | this.b[this.ptr++] << 16 | this.b[this.ptr++] << 8;
    }
    
    public final int readOpaque24B() throws Exception {
        this.check(3);
        return this.b[this.ptr++] << 16 | this.b[this.ptr++] << 8 | this.b[this.ptr++];
    }
    
    public abstract int pos();
    
    public boolean bytesAvailable() {
        return this.end != this.ptr;
    }
    
    public final byte[] getbuf() {
        return this.b;
    }
    
    public final int getptr() {
        return this.ptr;
    }
    
    public final int getend() {
        return this.end;
    }
    
    public final void setptr(final int ptr) {
        this.ptr = ptr;
    }
    
    protected abstract int overrun(final int p0, final int p1) throws Exception;
    
    static {
        InStream.maxStringLength = 65535;
    }
}
