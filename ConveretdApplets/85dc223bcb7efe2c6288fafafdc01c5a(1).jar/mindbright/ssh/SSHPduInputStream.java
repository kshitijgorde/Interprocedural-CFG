// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.IOException;
import mindbright.util.CRC32;
import java.io.InputStream;
import mindbright.security.Cipher;

public final class SSHPduInputStream extends SSHDataInputStream implements SSHPdu
{
    public int type;
    public int length;
    byte[] bytes;
    Cipher cipher;
    
    SSHPduInputStream(final int type, final Cipher cipher) {
        super(null);
        this.type = type;
        this.cipher = cipher;
    }
    
    boolean validChecksum() throws IOException {
        final int padLen = this.length + 8 & 0xFFFFFFF8;
        this.skip(padLen - 4);
        final int stored = this.readInt();
        this.reset();
        final int calculated = (int)CRC32.getValue(this.bytes, 0, padLen - 4);
        return calculated == stored;
    }
    
    public SSHPdu createPdu() {
        return new SSHPduInputStream(this.type, this.cipher);
    }
    
    public void readFrom(final InputStream in) throws IOException {
        final SSHDataInputStream dIn = new SSHDataInputStream(in);
        final int len = dIn.readInt();
        final int padLen = len + 8 & 0xFFFFFFF8;
        if (padLen > 256000) {
            throw new IOException("Corrupt incoming packet, too large");
        }
        final byte[] data = new byte[padLen];
        dIn.readFully(data);
        if (this.cipher != null) {
            this.cipher.decrypt(data, 0, data, 0, padLen);
        }
        this.in = new PduByteArrayInputStream(data);
        this.bytes = data;
        this.length = len;
        if (!this.validChecksum()) {
            throw new IOException("Invalid checksum in packet");
        }
        this.skip(8 - len % 8);
        final int type = this.readByte();
        if (type == 36) {
            SSH.logDebug("MSG_DEBUG: " + this.readString());
            this.readFrom(in);
        }
        else if (type == 32) {
            SSH.logIgnore(this);
            this.readFrom(in);
        }
        else if (this.type != -1 && this.type != type) {
            if (type == 1) {
                throw new IOException("Server disconnected: " + this.readString());
            }
            throw new IOException("Invalid type: " + type + " (expected: " + this.type + ")");
        }
        else {
            this.type = type;
        }
    }
    
    public void writeTo(final OutputStream sshOut) throws IOException {
        if (this.type != 23 && this.type != 17 && this.type != 18) {
            throw new IOException("Trying to write raw data from non-data PDU");
        }
        final int len = this.readInt();
        final PduByteArrayInputStream is = (PduByteArrayInputStream)this.in;
        sshOut.write(this.bytes, is.getPos(), len);
        sshOut.flush();
    }
    
    public byte[] rawData() {
        return this.bytes;
    }
    
    public void rawSetData(final byte[] raw) {
        final PduByteArrayInputStream is = (PduByteArrayInputStream)this.in;
        this.bytes = new byte[raw.length + 4];
        is.setPos(0);
        final int len = raw.length;
        int off = 0;
        this.bytes[off++] = (byte)(len >>> 24 & 0xFF);
        this.bytes[off++] = (byte)(len >>> 16 & 0xFF);
        this.bytes[off++] = (byte)(len >>> 8 & 0xFF);
        this.bytes[off++] = (byte)(len & 0xFF);
        System.arraycopy(raw, 0, this.bytes, off, raw.length);
        is.setBuf(this.bytes);
    }
    
    public int rawOffset() {
        final PduByteArrayInputStream is = (PduByteArrayInputStream)this.in;
        return is.getPos() + 4;
    }
    
    public int rawSize() {
        final PduByteArrayInputStream is = (PduByteArrayInputStream)this.in;
        int off = is.getPos();
        final int ch1 = this.bytes[off++] + 256 & 0xFF;
        final int ch2 = this.bytes[off++] + 256 & 0xFF;
        final int ch3 = this.bytes[off++] + 256 & 0xFF;
        final int ch4 = this.bytes[off] + 256 & 0xFF;
        return (ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0);
    }
    
    public void rawAdjustSize(final int size) {
        final PduByteArrayInputStream is = (PduByteArrayInputStream)this.in;
        final int oldSz = this.rawSize();
        if (size >= oldSz) {
            return;
        }
        int pos = is.getPos() + (oldSz - size);
        is.setPos(pos);
        this.bytes[pos++] = (byte)(size >>> 24 & 0xFF);
        this.bytes[pos++] = (byte)(size >>> 16 & 0xFF);
        this.bytes[pos++] = (byte)(size >>> 8 & 0xFF);
        this.bytes[pos++] = (byte)(size & 0xFF);
    }
    
    static final class PduByteArrayInputStream extends ByteArrayInputStream
    {
        PduByteArrayInputStream(final byte[] data) {
            super(data);
        }
        
        public int getPos() {
            return this.pos;
        }
        
        public void setPos(final int pos) {
            this.pos = pos;
        }
        
        public byte[] getBuf() {
            return this.buf;
        }
        
        public void setBuf(final byte[] buf) {
            this.buf = buf;
        }
    }
}
