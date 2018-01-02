// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.decoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.PushbackInputStream;

public final class Bitstream implements BitstreamErrors
{
    static byte INITIAL_SYNC;
    static byte STRICT_SYNC;
    private static final int BUFFER_INT_SIZE = 433;
    private final int[] framebuffer;
    private int framesize;
    private byte[] frame_bytes;
    private int wordpointer;
    private int bitindex;
    private int syncword;
    private int header_pos;
    private boolean single_ch_mode;
    private final int[] bitmask;
    private final PushbackInputStream source;
    private final Header header;
    private final byte[] syncbuf;
    private Crc16[] crc;
    private byte[] rawid3v2;
    private boolean firstframe;
    
    public Bitstream(final InputStream inputStream) {
        this.framebuffer = new int[433];
        this.frame_bytes = new byte[1732];
        this.header_pos = 0;
        this.bitmask = new int[] { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071 };
        this.header = new Header();
        this.syncbuf = new byte[4];
        this.crc = new Crc16[1];
        this.rawid3v2 = null;
        this.firstframe = true;
        if (inputStream == null) {
            throw new NullPointerException("in");
        }
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        this.loadID3v2(bufferedInputStream);
        this.firstframe = true;
        this.source = new PushbackInputStream(bufferedInputStream, 1732);
        this.closeFrame();
    }
    
    public int header_pos() {
        return this.header_pos;
    }
    
    private void loadID3v2(final InputStream inputStream) {
        int id3v2Header = -1;
        try {
            inputStream.mark(10);
            id3v2Header = this.readID3v2Header(inputStream);
            this.header_pos = id3v2Header;
        }
        catch (IOException ex) {}
        finally {
            try {
                inputStream.reset();
            }
            catch (IOException ex2) {}
        }
        try {
            if (id3v2Header > 0) {
                inputStream.read(this.rawid3v2 = new byte[id3v2Header], 0, this.rawid3v2.length);
            }
        }
        catch (IOException ex3) {}
    }
    
    private int readID3v2Header(final InputStream inputStream) throws IOException {
        final byte[] array = new byte[4];
        int n = -10;
        inputStream.read(array, 0, 3);
        if (array[0] == 73 && array[1] == 68 && array[2] == 51) {
            inputStream.read(array, 0, 3);
            final byte b = array[0];
            final byte b2 = array[1];
            inputStream.read(array, 0, 4);
            n = (array[0] << 21) + (array[1] << 14) + (array[2] << 7) + array[3];
        }
        return n + 10;
    }
    
    public InputStream getRawID3v2() {
        if (this.rawid3v2 == null) {
            return null;
        }
        return new ByteArrayInputStream(this.rawid3v2);
    }
    
    public void close() throws BitstreamException {
        try {
            this.source.close();
        }
        catch (IOException ex) {
            throw this.newBitstreamException(258, ex);
        }
    }
    
    public Header readFrame() throws BitstreamException {
        Header header = null;
        try {
            header = this.readNextFrame();
            if (this.firstframe) {
                header.parseVBR(this.frame_bytes);
                this.firstframe = false;
            }
        }
        catch (BitstreamException ex) {
            if (ex.getErrorCode() == 261) {
                try {
                    this.closeFrame();
                    header = this.readNextFrame();
                }
                catch (BitstreamException ex2) {
                    if (ex2.getErrorCode() != 260) {
                        throw this.newBitstreamException(ex2.getErrorCode(), ex2);
                    }
                }
            }
            else if (ex.getErrorCode() != 260) {
                throw this.newBitstreamException(ex.getErrorCode(), ex);
            }
        }
        return header;
    }
    
    private Header readNextFrame() throws BitstreamException {
        if (this.framesize == -1) {
            this.nextFrame();
        }
        return this.header;
    }
    
    private void nextFrame() throws BitstreamException {
        this.header.read_header(this, this.crc);
    }
    
    public void unreadFrame() throws BitstreamException {
        if (this.wordpointer == -1 && this.bitindex == -1 && this.framesize > 0) {
            try {
                this.source.unread(this.frame_bytes, 0, this.framesize);
            }
            catch (IOException ex) {
                throw this.newBitstreamException(258);
            }
        }
    }
    
    public void closeFrame() {
        this.framesize = -1;
        this.wordpointer = -1;
        this.bitindex = -1;
    }
    
    public boolean isSyncCurrentPosition(final int n) throws BitstreamException {
        final int bytes = this.readBytes(this.syncbuf, 0, 4);
        final int n2 = (this.syncbuf[0] << 24 & 0xFF000000) | (this.syncbuf[1] << 16 & 0xFF0000) | (this.syncbuf[2] << 8 & 0xFF00) | (this.syncbuf[3] << 0 & 0xFF);
        try {
            this.source.unread(this.syncbuf, 0, bytes);
        }
        catch (IOException ex) {}
        boolean syncMark = false;
        switch (bytes) {
            case 0: {
                syncMark = true;
                break;
            }
            case 4: {
                syncMark = this.isSyncMark(n2, n, this.syncword);
                break;
            }
        }
        return syncMark;
    }
    
    public int readBits(final int n) {
        return this.get_bits(n);
    }
    
    public int readCheckedBits(final int n) {
        return this.get_bits(n);
    }
    
    protected BitstreamException newBitstreamException(final int n) {
        return new BitstreamException(n, null);
    }
    
    protected BitstreamException newBitstreamException(final int n, final Throwable t) {
        return new BitstreamException(n, t);
    }
    
    int syncHeader(final byte b) throws BitstreamException {
        if (this.readBytes(this.syncbuf, 0, 3) != 3) {
            throw this.newBitstreamException(260, null);
        }
        int n = (this.syncbuf[0] << 16 & 0xFF0000) | (this.syncbuf[1] << 8 & 0xFF00) | (this.syncbuf[2] << 0 & 0xFF);
        do {
            final int n2 = n << 8;
            if (this.readBytes(this.syncbuf, 3, 1) != 1) {
                throw this.newBitstreamException(260, null);
            }
            n = (n2 | (this.syncbuf[3] & 0xFF));
        } while (!this.isSyncMark(n, b, this.syncword));
        return n;
    }
    
    public boolean isSyncMark(final int n, final int n2, final int n3) {
        boolean b;
        if (n2 == Bitstream.INITIAL_SYNC) {
            b = ((n & 0xFFE00000) == 0xFFE00000);
        }
        else {
            b = ((n & 0xFFF80C00) == n3 && (n & 0xC0) == 0xC0 == this.single_ch_mode);
        }
        if (b) {
            b = ((n >>> 10 & 0x3) != 0x3);
        }
        if (b) {
            b = ((n >>> 17 & 0x3) != 0x0);
        }
        if (b) {
            b = ((n >>> 19 & 0x3) != 0x1);
        }
        return b;
    }
    
    int read_frame_data(final int framesize) throws BitstreamException {
        final int fully = this.readFully(this.frame_bytes, 0, framesize);
        this.framesize = framesize;
        this.wordpointer = -1;
        this.bitindex = -1;
        return fully;
    }
    
    void parse_frame() throws BitstreamException {
        int n = 0;
        final byte[] frame_bytes = this.frame_bytes;
        for (int framesize = this.framesize, i = 0; i < framesize; i += 4) {
            int n2 = 0;
            int n3 = 0;
            byte b = 0;
            final byte b2 = frame_bytes[i];
            if (i + 1 < framesize) {
                n2 = frame_bytes[i + 1];
            }
            if (i + 2 < framesize) {
                n3 = frame_bytes[i + 2];
            }
            if (i + 3 < framesize) {
                b = frame_bytes[i + 3];
            }
            this.framebuffer[n++] = ((b2 << 24 & 0xFF000000) | (n2 << 16 & 0xFF0000) | (n3 << 8 & 0xFF00) | (b & 0xFF));
        }
        this.wordpointer = 0;
        this.bitindex = 0;
    }
    
    public int get_bits(final int n) {
        final int n2 = this.bitindex + n;
        if (this.wordpointer < 0) {
            this.wordpointer = 0;
        }
        if (n2 <= 32) {
            final int n3 = this.framebuffer[this.wordpointer] >>> 32 - n2 & this.bitmask[n];
            if ((this.bitindex += n) == 32) {
                this.bitindex = 0;
                ++this.wordpointer;
            }
            return n3;
        }
        final int n4 = this.framebuffer[this.wordpointer] & 0xFFFF;
        ++this.wordpointer;
        final int n5 = ((n4 << 16 & 0xFFFF0000) | ((this.framebuffer[this.wordpointer] & 0xFFFF0000) >>> 16 & 0xFFFF)) >>> 48 - n2 & this.bitmask[n];
        this.bitindex = n2 - 32;
        return n5;
    }
    
    void set_syncword(final int n) {
        this.syncword = (n & 0xFFFFFF3F);
        this.single_ch_mode = ((n & 0xC0) == 0xC0);
    }
    
    private int readFully(final byte[] array, int n, int i) throws BitstreamException {
        int n2 = 0;
        try {
            while (i > 0) {
                final int read = this.source.read(array, n, i);
                if (read == -1) {
                    while (i-- > 0) {
                        array[n++] = 0;
                    }
                    break;
                }
                n2 += read;
                n += read;
                i -= read;
            }
        }
        catch (IOException ex) {
            throw this.newBitstreamException(258, ex);
        }
        return n2;
    }
    
    private int readBytes(final byte[] array, int n, int i) throws BitstreamException {
        int n2 = 0;
        try {
            while (i > 0) {
                final int read = this.source.read(array, n, i);
                if (read == -1) {
                    break;
                }
                n2 += read;
                n += read;
                i -= read;
            }
        }
        catch (IOException ex) {
            throw this.newBitstreamException(258, ex);
        }
        return n2;
    }
    
    static {
        Bitstream.INITIAL_SYNC = 0;
        Bitstream.STRICT_SYNC = 1;
    }
}