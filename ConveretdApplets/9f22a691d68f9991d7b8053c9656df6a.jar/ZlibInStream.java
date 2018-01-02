import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

// 
// Decompiled by Procyon v0.5.30
// 

public class ZlibInStream extends InStream
{
    static final int defaultBufSize = 16384;
    private InStream underlying;
    private int bufSize;
    private int ptrOffset;
    private Inflater inflater;
    private int bytesIn;
    
    public ZlibInStream(final int bufSize) {
        this.bufSize = bufSize;
        super.b = new byte[this.bufSize];
        final boolean ptr = false;
        this.ptrOffset = (ptr ? 1 : 0);
        super.end = (ptr ? 1 : 0);
        super.ptr = (ptr ? 1 : 0);
        this.inflater = new Inflater();
    }
    
    public ZlibInStream() {
        this(16384);
    }
    
    public void setUnderlying(final InStream underlying, final int bytesIn) {
        this.underlying = underlying;
        this.bytesIn = bytesIn;
        final boolean b = false;
        super.end = (b ? 1 : 0);
        super.ptr = (b ? 1 : 0);
    }
    
    public void reset() throws Exception {
        final boolean b = false;
        super.end = (b ? 1 : 0);
        super.ptr = (b ? 1 : 0);
        if (this.underlying == null) {
            return;
        }
        while (this.bytesIn > 0) {
            this.decompress();
            super.end = 0;
        }
        this.underlying = null;
    }
    
    public int pos() {
        return this.ptrOffset + super.ptr;
    }
    
    protected int overrun(final int n, int n2) throws Exception {
        if (n > this.bufSize) {
            throw new Exception("ZlibInStream overrun: max itemSize exceeded");
        }
        if (this.underlying == null) {
            throw new Exception("ZlibInStream overrun: no underlying stream");
        }
        if (super.end - super.ptr != 0) {
            System.arraycopy(super.b, super.ptr, super.b, 0, super.end - super.ptr);
        }
        this.ptrOffset += super.ptr;
        super.end -= super.ptr;
        super.ptr = 0;
        while (super.end < n) {
            this.decompress();
        }
        if (n * n2 > super.end) {
            n2 = super.end / n;
        }
        return n2;
    }
    
    private void decompress() throws Exception {
        try {
            this.underlying.check(1);
            int bytesIn = this.underlying.getend() - this.underlying.getptr();
            if (bytesIn > this.bytesIn) {
                bytesIn = this.bytesIn;
            }
            if (this.inflater.needsInput()) {
                this.inflater.setInput(this.underlying.getbuf(), this.underlying.getptr(), bytesIn);
            }
            super.end += this.inflater.inflate(super.b, super.end, this.bufSize - super.end);
            if (this.inflater.needsInput()) {
                this.bytesIn -= bytesIn;
                this.underlying.setptr(this.underlying.getptr() + bytesIn);
            }
        }
        catch (DataFormatException ex) {
            throw new Exception("ZlibInStream: inflate failed");
        }
    }
}
