// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.io.ByteArrayOutputStream;
import mindbright.util.CRC32;
import java.io.InputStream;
import java.io.IOException;
import mindbright.security.SecureRandom;
import java.io.OutputStream;
import mindbright.security.Cipher;

public final class SSHPduOutputStream extends SSHDataOutputStream implements SSHPdu
{
    public static final int SSH_DEFAULT_PKT_LEN = 8192;
    public static int mtu;
    byte[] readFromRawData;
    int readFromOff;
    int readFromSize;
    public int type;
    public Cipher cipher;
    
    public static synchronized void setMTU(final int newMtu) {
        SSHPduOutputStream.mtu = newMtu;
    }
    
    SSHPduOutputStream(final Cipher cipher) {
        super(null);
        this.cipher = cipher;
    }
    
    SSHPduOutputStream(final int type, final Cipher cipher) throws IOException {
        super(new PduByteArrayOutputStream(SSHPduOutputStream.mtu));
        this.type = type;
        this.cipher = cipher;
        if (cipher != null) {
            final PduByteArrayOutputStream bytes = (PduByteArrayOutputStream)this.out;
            final SecureRandom rand = SSH.secureRandom();
            rand.nextPadBytes(bytes.getBuf(), 8);
            bytes.setCount(8);
        }
        else {
            for (int i = 0; i < 8; ++i) {
                this.write(0);
            }
        }
        this.write(type);
    }
    
    public SSHPdu createPdu() throws IOException {
        final SSHPdu pdu = new SSHPduOutputStream(this.type, this.cipher);
        return pdu;
    }
    
    public void readFrom(final InputStream in) throws IOException {
        if (this.type != 23 && this.type != 16) {
            throw new IOException("Trying to read raw data into non-data PDU");
        }
        final PduByteArrayOutputStream bytes = (PduByteArrayOutputStream)this.out;
        this.readFromRawData = bytes.getBuf();
        this.readFromOff = bytes.size() + 4;
        this.readFromSize = in.read(this.readFromRawData, this.readFromOff, SSHPduOutputStream.mtu - this.readFromOff);
        if (this.readFromSize == -1) {
            throw new IOException("EOF");
        }
        this.writeInt(this.readFromSize);
        bytes.setCount(this.readFromOff + this.readFromSize);
    }
    
    public void writeTo(final OutputStream sshOut) throws IOException {
        final PduByteArrayOutputStream bytes = (PduByteArrayOutputStream)this.out;
        final int off = 0;
        final int iSz = bytes.size();
        final int pad = (iSz + 4) % 8;
        final int crc32 = (int)CRC32.getValue(bytes.getBuf(), pad, iSz - pad);
        final int padSz = iSz + 4 - pad;
        this.writeInt(crc32);
        final byte[] padData = bytes.getBuf();
        if (this.cipher != null) {
            this.cipher.encrypt(padData, pad, padData, pad, padSz);
        }
        final int sz = padSz - (8 - pad);
        sshOut.write(sz >>> 24 & 0xFF);
        sshOut.write(sz >>> 16 & 0xFF);
        sshOut.write(sz >>> 8 & 0xFF);
        sshOut.write(sz >>> 0 & 0xFF);
        sshOut.write(padData, pad, padSz);
        sshOut.flush();
    }
    
    public byte[] rawData() {
        return this.readFromRawData;
    }
    
    public void rawSetData(final byte[] raw) {
    }
    
    public int rawOffset() {
        return this.readFromOff;
    }
    
    public int rawSize() {
        final byte[] bytes = this.readFromRawData;
        int off = this.readFromOff - 4;
        final int ch1 = bytes[off++] + 256 & 0xFF;
        final int ch2 = bytes[off++] + 256 & 0xFF;
        final int ch3 = bytes[off++] + 256 & 0xFF;
        final int ch4 = bytes[off] + 256 & 0xFF;
        return (ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0);
    }
    
    public void rawAdjustSize(final int size) {
        final PduByteArrayOutputStream bytes = (PduByteArrayOutputStream)this.out;
        bytes.setCount(size);
    }
    
    static {
        SSHPduOutputStream.mtu = 8192;
    }
    
    static final class PduByteArrayOutputStream extends ByteArrayOutputStream
    {
        PduByteArrayOutputStream() {
        }
        
        PduByteArrayOutputStream(final int size) {
            super(size);
        }
        
        PduByteArrayOutputStream(final byte[] buf) {
            this.buf = buf;
        }
        
        public byte[] getBuf() {
            return this.buf;
        }
        
        public int getCount() {
            return this.count;
        }
        
        public void setBuf(final byte[] buf) {
            this.buf = buf;
        }
        
        public void setCount(final int count) {
            this.count = count;
        }
    }
}
