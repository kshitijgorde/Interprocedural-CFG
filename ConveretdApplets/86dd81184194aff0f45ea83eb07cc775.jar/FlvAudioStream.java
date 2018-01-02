import java.io.IOException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class FlvAudioStream extends InputStream
{
    private boolean HeadRead;
    private boolean EndOfStream;
    private InputStream Input;
    private long InputOffset;
    private int AvailableAudioBytes;
    
    public FlvAudioStream(final InputStream paramInputStream) {
        this.HeadRead = false;
        this.EndOfStream = false;
        this.InputOffset = 0L;
        this.AvailableAudioBytes = 0;
        this.Input = paramInputStream;
    }
    
    public long getInputOffset() {
        return this.InputOffset;
    }
    
    public void close() throws IOException {
        this.InputOffset = 0L;
        this.AvailableAudioBytes = 0;
        this.HeadRead = false;
        this.EndOfStream = false;
        this.Input.close();
    }
    
    public long skip(final long paramLong) throws IOException {
        if (this.EndOfStream) {
            return 0L;
        }
        if (!this.HeadRead) {
            this.inputReadHead();
        }
        long l1;
        long l3;
        for (l1 = 0L; l1 < paramLong && !this.EndOfStream; l1 += l3, this.AvailableAudioBytes -= (int)l3) {
            if (this.AvailableAudioBytes <= 0) {
                this.AvailableAudioBytes = this.readTag();
            }
            final long l2 = paramLong - l1;
            l3 = this.Input.skip((l2 > this.AvailableAudioBytes) ? ((long)this.AvailableAudioBytes) : l2);
        }
        return l1;
    }
    
    public int read() throws IOException {
        final byte[] arrayOfByte = { 0 };
        this.audioRead(arrayOfByte, 0, 1);
        return arrayOfByte[0];
    }
    
    public int read(final byte[] paramArrayOfByte) throws IOException {
        return this.read(paramArrayOfByte, 0, paramArrayOfByte.length);
    }
    
    public int read(final byte[] paramArrayOfByte, final int paramInt1, final int paramInt2) throws IOException {
        return this.audioRead(paramArrayOfByte, paramInt1, paramInt2);
    }
    
    private void inputReadHead() throws IOException {
        final long l1 = this.inputReadUInt32();
        if (l1 != 1179407873L) {
            throw new IOException("Not an FLV file.");
        }
        final long l2 = this.inputReadUInt8();
        final long l3 = this.inputReadUInt32();
        this.HeadRead = true;
        this.inputSkip(l3 - this.InputOffset);
    }
    
    private int audioRead(final byte[] paramArrayOfByte, final int paramInt1, final int paramInt2) throws IOException {
        if (this.EndOfStream) {
            return -1;
        }
        if (!this.HeadRead) {
            this.inputReadHead();
        }
        int i = 0;
        while (i < paramInt2 && !this.EndOfStream) {
            if (this.AvailableAudioBytes <= 0) {
                this.AvailableAudioBytes = this.readTag();
                if (this.AvailableAudioBytes <= 0) {
                    continue;
                }
            }
            final int j = paramInt2 - i;
            final int k = this.inputRead(paramArrayOfByte, paramInt1 + i, (j > this.AvailableAudioBytes) ? this.AvailableAudioBytes : j);
            if (k < 0) {
                break;
            }
            i += k;
            this.AvailableAudioBytes -= k;
        }
        return i;
    }
    
    private int readTag() throws IOException {
        if (this.EndOfStream) {
            return 0;
        }
        final long l1 = this.inputReadUInt32();
        final long l2 = this.inputReadUInt8();
        int i = (int)this.inputReadUInt24();
        long l3 = this.inputReadUInt24();
        l3 |= this.inputReadUInt8() << 24;
        final long l4 = this.inputReadUInt24();
        if (i == 0) {
            return 0;
        }
        final long l5 = this.inputReadUInt8();
        --i;
        if (l2 == 8L) {
            return i;
        }
        if (l2 == 9L) {
            this.inputSkip(i);
            return 0;
        }
        this.inputSkip(i);
        return 0;
    }
    
    private long inputSkip(final long paramLong) throws IOException {
        long l1 = 0L;
        while (l1 < paramLong) {
            final long l2 = this.Input.skip(paramLong - l1);
            l1 += l2;
            if (l2 <= 0L) {
                break;
            }
        }
        this.InputOffset += l1;
        return l1;
    }
    
    private int inputRead() throws IOException {
        final int i = this.Input.read();
        if (i < 0) {
            this.EndOfStream = true;
            return -1;
        }
        ++this.InputOffset;
        return i;
    }
    
    private int inputRead(final byte[] paramArrayOfByte) throws IOException {
        return this.inputRead(paramArrayOfByte, 0, paramArrayOfByte.length);
    }
    
    private int inputRead(final byte[] paramArrayOfByte, final int paramInt1, final int paramInt2) throws IOException {
        int i = 0;
        while (i < paramInt2) {
            final int j = paramInt2 - i;
            final int k = this.Input.read(paramArrayOfByte, paramInt1 + i, j);
            if (k < 0) {
                this.EndOfStream = true;
                if (i != 0) {
                    break;
                }
                return -1;
            }
            else {
                i += k;
            }
        }
        this.InputOffset += i;
        return i;
    }
    
    private long inputReadUInt8() throws IOException {
        final int i = this.inputRead();
        return i;
    }
    
    private long inputReadUInt24() throws IOException {
        final byte[] arrayOfByte = new byte[4];
        final int i = this.inputRead(arrayOfByte, 1, 3);
        if (i < 3) {
            return 0L;
        }
        return this.toUInt32(arrayOfByte);
    }
    
    private long inputReadUInt32() throws IOException {
        final byte[] arrayOfByte = new byte[4];
        final int i = this.inputRead(arrayOfByte, 0, 4);
        if (i < 4) {
            return 0L;
        }
        return this.toUInt32(arrayOfByte);
    }
    
    private long toUInt32(final byte[] paramArrayOfByte) {
        long l = (0xFF & paramArrayOfByte[0]) << 24;
        l |= (0xFF & paramArrayOfByte[1]) << 16;
        l |= (0xFF & paramArrayOfByte[2]) << 8;
        l |= (0xFF & paramArrayOfByte[3]);
        return l;
    }
}
