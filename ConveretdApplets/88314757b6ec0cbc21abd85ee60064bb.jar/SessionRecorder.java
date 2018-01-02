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
    
    public SessionRecorder(final String name, final int bufsize) throws IOException {
        this.f = new FileOutputStream(name);
        this.df = new DataOutputStream(this.f);
        this.startTime = System.currentTimeMillis();
        this.lastTimeOffset = 0L;
        this.bufferSize = bufsize;
        this.bufferBytes = 0;
        this.buffer = new byte[this.bufferSize];
    }
    
    public SessionRecorder(final String name) throws IOException {
        this(name, 65536);
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
    
    public void writeByte(final int b) throws IOException {
        this.prepareWriting();
        this.buffer[this.bufferBytes++] = (byte)b;
    }
    
    public void writeShortBE(final int v) throws IOException {
        this.prepareWriting();
        this.buffer[this.bufferBytes++] = (byte)(v >> 8);
        this.buffer[this.bufferBytes++] = (byte)v;
    }
    
    public void writeIntBE(final int v) throws IOException {
        this.prepareWriting();
        this.buffer[this.bufferBytes] = (byte)(v >> 24);
        this.buffer[this.bufferBytes + 1] = (byte)(v >> 16);
        this.buffer[this.bufferBytes + 2] = (byte)(v >> 8);
        this.buffer[this.bufferBytes + 3] = (byte)v;
        this.bufferBytes += 4;
    }
    
    public void writeShortLE(final int v) throws IOException {
        this.prepareWriting();
        this.buffer[this.bufferBytes++] = (byte)v;
        this.buffer[this.bufferBytes++] = (byte)(v >> 8);
    }
    
    public void writeIntLE(final int v) throws IOException {
        this.prepareWriting();
        this.buffer[this.bufferBytes] = (byte)v;
        this.buffer[this.bufferBytes + 1] = (byte)(v >> 8);
        this.buffer[this.bufferBytes + 2] = (byte)(v >> 16);
        this.buffer[this.bufferBytes + 3] = (byte)(v >> 24);
        this.bufferBytes += 4;
    }
    
    public void write(final byte[] b, int off, int len) throws IOException {
        this.prepareWriting();
        while (len > 0) {
            if (this.bufferBytes > this.bufferSize - 4) {
                this.flush(false);
            }
            int partLen;
            if (this.bufferBytes + len > this.bufferSize) {
                partLen = this.bufferSize - this.bufferBytes;
            }
            else {
                partLen = len;
            }
            System.arraycopy(b, off, this.buffer, this.bufferBytes, partLen);
            this.bufferBytes += partLen;
            off += partLen;
            len -= partLen;
        }
    }
    
    public void write(final byte[] b) throws IOException {
        this.write(b, 0, b.length);
    }
    
    public void flush(final boolean updateTimeOffset) throws IOException {
        if (this.bufferBytes > 0) {
            this.df.writeInt(this.bufferBytes);
            this.df.write(this.buffer, 0, this.bufferBytes + 3 & 0x7FFFFFFC);
            this.df.writeInt((int)this.lastTimeOffset);
            this.bufferBytes = 0;
            if (updateTimeOffset) {
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
