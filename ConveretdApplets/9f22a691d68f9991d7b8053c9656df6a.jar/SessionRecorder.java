import java.io.IOException;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class SessionRecorder
{
    protected FileOutputStream f;
    protected DataOutputStream df;
    protected long startTime;
    protected long lastTimeOffset;
    protected byte[] buffer;
    protected int bufferSize;
    protected int bufferBytes;
    
    public SessionRecorder(final String s, final int bufferSize) throws IOException {
        this.f = new FileOutputStream(s);
        this.df = new DataOutputStream(this.f);
        this.startTime = System.currentTimeMillis();
        this.lastTimeOffset = 0L;
        this.bufferSize = bufferSize;
        this.bufferBytes = 0;
        this.buffer = new byte[this.bufferSize];
    }
    
    public SessionRecorder(final String s) throws IOException {
        this(s, 65536);
    }
    
    public void close() throws IOException {
        try {
            this.flush();
        }
        catch (IOException ex) {}
        this.df = null;
        this.f.close();
        this.f = null;
        this.buffer = null;
    }
    
    public void writeHeader() throws IOException {
        this.df.write("FBS 001.000\n".getBytes());
    }
    
    public void writeByte(final int n) throws IOException {
        this.prepareWriting();
        this.buffer[this.bufferBytes++] = (byte)n;
    }
    
    public void writeShortBE(final int n) throws IOException {
        this.prepareWriting();
        this.buffer[this.bufferBytes++] = (byte)(n >> 8);
        this.buffer[this.bufferBytes++] = (byte)n;
    }
    
    public void writeIntBE(final int n) throws IOException {
        this.prepareWriting();
        this.buffer[this.bufferBytes] = (byte)(n >> 24);
        this.buffer[this.bufferBytes + 1] = (byte)(n >> 16);
        this.buffer[this.bufferBytes + 2] = (byte)(n >> 8);
        this.buffer[this.bufferBytes + 3] = (byte)n;
        this.bufferBytes += 4;
    }
    
    public void writeShortLE(final int n) throws IOException {
        this.prepareWriting();
        this.buffer[this.bufferBytes++] = (byte)n;
        this.buffer[this.bufferBytes++] = (byte)(n >> 8);
    }
    
    public void writeIntLE(final int n) throws IOException {
        this.prepareWriting();
        this.buffer[this.bufferBytes] = (byte)n;
        this.buffer[this.bufferBytes + 1] = (byte)(n >> 8);
        this.buffer[this.bufferBytes + 2] = (byte)(n >> 16);
        this.buffer[this.bufferBytes + 3] = (byte)(n >> 24);
        this.bufferBytes += 4;
    }
    
    public void write(final byte[] array, int n, int i) throws IOException {
        this.prepareWriting();
        while (i > 0) {
            if (this.bufferBytes > this.bufferSize - 4) {
                this.flush(false);
            }
            int n2;
            if (this.bufferBytes + i > this.bufferSize) {
                n2 = this.bufferSize - this.bufferBytes;
            }
            else {
                n2 = i;
            }
            System.arraycopy(array, n, this.buffer, this.bufferBytes, n2);
            this.bufferBytes += n2;
            n += n2;
            i -= n2;
        }
    }
    
    public void write(final byte[] array) throws IOException {
        this.write(array, 0, array.length);
    }
    
    public void flush(final boolean b) throws IOException {
        if (this.bufferBytes > 0) {
            this.df.writeInt(this.bufferBytes);
            this.df.write(this.buffer, 0, this.bufferBytes + 3 & 0x7FFFFFFC);
            this.df.writeInt((int)this.lastTimeOffset);
            this.bufferBytes = 0;
            if (b) {
                this.lastTimeOffset = -1L;
            }
        }
    }
    
    public void flush() throws IOException {
        this.flush(true);
    }
    
    protected void prepareWriting() throws IOException {
        if (this.lastTimeOffset == -1L) {
            this.lastTimeOffset = System.currentTimeMillis() - this.startTime;
        }
        if (this.bufferBytes > this.bufferSize - 4) {
            this.flush(false);
        }
    }
}
