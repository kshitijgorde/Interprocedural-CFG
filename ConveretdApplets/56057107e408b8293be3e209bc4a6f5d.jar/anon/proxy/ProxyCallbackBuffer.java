// 
// Decompiled by Procyon v0.5.30
// 

package anon.proxy;

import java.io.ByteArrayOutputStream;

public class ProxyCallbackBuffer
{
    private byte[] chunk;
    private int modificationStartOffset;
    private int modificationEndOffset;
    private int payloadLength;
    private int status;
    
    public ProxyCallbackBuffer() {
        this(new byte[1000]);
    }
    
    public ProxyCallbackBuffer(final byte[] array) {
        this(array, 0, array.length - 1);
    }
    
    public ProxyCallbackBuffer(final byte[] chunk, final int modificationStartOffset, final int payloadLength) {
        this.chunk = null;
        this.modificationStartOffset = 0;
        this.modificationEndOffset = 0;
        this.payloadLength = 0;
        this.status = 2;
        this.setChunk(chunk);
        this.setModificationStartOffset(modificationStartOffset);
        this.setPayloadLength(payloadLength);
        this.setModificationEndOffset(payloadLength - 1);
        this.status = 2;
    }
    
    public ProxyCallbackBuffer(final byte[] chunk, final int modificationStartOffset, final int modificationEndOffset, final int n) {
        this.chunk = null;
        this.modificationStartOffset = 0;
        this.modificationEndOffset = 0;
        this.payloadLength = 0;
        this.status = 2;
        this.setChunk(chunk);
        this.setModificationStartOffset(modificationStartOffset);
        this.setModificationEndOffset(modificationEndOffset);
        this.status = 2;
    }
    
    public byte[] getChunk() {
        return this.chunk;
    }
    
    public void setChunk(final byte[] chunk) {
        this.chunk = chunk;
        this.modificationStartOffset = 0;
        this.modificationEndOffset = chunk.length - 1;
        this.payloadLength = chunk.length;
    }
    
    public int getModificationStartOffset() {
        return this.modificationStartOffset;
    }
    
    public void setModificationStartOffset(final int modificationStartOffset) {
        if (modificationStartOffset < 0 || modificationStartOffset > this.chunk.length) {
            throw new ArrayIndexOutOfBoundsException("Illegal modification start index: " + modificationStartOffset + " (chunk length: " + this.chunk.length + ")");
        }
        this.modificationStartOffset = modificationStartOffset;
    }
    
    public int getModificationEndOffset() {
        return this.modificationEndOffset;
    }
    
    public void setModificationEndOffset(final int modificationEndOffset) {
        if (modificationEndOffset < 0 || modificationEndOffset > this.chunk.length) {
            throw new ArrayIndexOutOfBoundsException("Illegal modification end index: " + modificationEndOffset + " (chunk length: " + this.chunk.length + ")");
        }
        this.modificationEndOffset = modificationEndOffset;
    }
    
    public int getPayloadLength() {
        return this.payloadLength;
    }
    
    public void setPayloadLength(final int payloadLength) {
        if (payloadLength < 0 || payloadLength > this.chunk.length) {
            throw new ArrayIndexOutOfBoundsException("Illegal payload length: " + payloadLength);
        }
        this.payloadLength = payloadLength;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    public void setStatus(final int status) {
        if (status < 0 || status > 2) {
            throw new IllegalArgumentException("Illegal status specified: " + status);
        }
        this.status = status;
    }
    
    public void copyLeadingData(final ByteArrayOutputStream byteArrayOutputStream) {
        if (this.modificationStartOffset > 0) {
            byteArrayOutputStream.write(this.chunk, 0, this.modificationStartOffset);
        }
    }
    
    public void copyLeadingData(final byte[] array) {
        this.copyLeadingData(array, 0);
    }
    
    public void copyLeadingData(final byte[] array, final int n) {
        if (n + this.modificationStartOffset > array.length) {
            throw new ArrayIndexOutOfBoundsException("leading data length " + this.modificationStartOffset + " excceeds destination array");
        }
        if (this.modificationStartOffset > 0) {
            System.arraycopy(this.chunk, 0, array, n, this.modificationStartOffset);
        }
    }
    
    public void copyTrailingData(final ByteArrayOutputStream byteArrayOutputStream) {
        final int trailingDataLength = this.getTrailingDataLength();
        if (trailingDataLength > 0) {
            byteArrayOutputStream.write(this.chunk, this.modificationEndOffset + 1, trailingDataLength);
        }
    }
    
    public void copyTrailingData(final byte[] array, final int n) {
        final int trailingDataLength = this.getTrailingDataLength();
        if (n + trailingDataLength > array.length) {
            throw new ArrayIndexOutOfBoundsException("trailing data length " + trailingDataLength + " excceeds destination array");
        }
        if (trailingDataLength > 0) {
            System.arraycopy(this.chunk, this.modificationEndOffset + 1, array, n, trailingDataLength);
        }
    }
    
    public synchronized byte[] extractModificationData() {
        byte[] chunk;
        if (this.modificationStartOffset == 0 && this.modificationEndOffset == 0) {
            chunk = this.chunk;
            this.payloadLength = 0;
            this.modificationEndOffset = 0;
            this.modificationStartOffset = 0;
            this.chunk = new byte[0];
        }
        else {
            chunk = new byte[this.getModificationDataLength()];
            System.arraycopy(this.chunk, this.modificationStartOffset, chunk, 0, chunk.length);
            this.payloadLength -= chunk.length;
            final byte[] chunk2 = new byte[this.chunk.length - chunk.length];
            this.copyLeadingData(chunk2);
            this.copyTrailingData(chunk2, this.modificationStartOffset);
            this.modificationEndOffset = this.modificationStartOffset;
            this.chunk = chunk2;
        }
        return chunk;
    }
    
    public synchronized void injectModificationData(final byte[] array) {
        this.injectModificationData(array, 0, array.length);
    }
    
    public synchronized void injectModificationData(final byte[] array, final int n, final int n2) {
        if (n < 0) {
            throw new IllegalArgumentException("Offset must be >= 0");
        }
        if (n2 < 0) {
            throw new IllegalArgumentException("Length must be >= 0");
        }
        final byte[] chunk = new byte[this.chunk.length + n2];
        this.copyLeadingData(chunk);
        this.copyTrailingData(chunk, this.modificationEndOffset + n2);
        System.arraycopy(array, n, chunk, this.modificationStartOffset, n2);
        System.arraycopy(this.chunk, this.modificationStartOffset, chunk, n2, this.getModificationDataLength());
        this.modificationEndOffset += n2;
        this.payloadLength += n2;
        this.chunk = chunk;
    }
    
    public int getModificationDataLength() {
        return this.modificationEndOffset - this.modificationStartOffset + 1;
    }
    
    public int getLeadingDataLength() {
        return this.modificationStartOffset;
    }
    
    public int getTrailingDataLength() {
        return this.payloadLength - (this.modificationEndOffset + 1);
    }
}
