import java.math.BigInteger;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Stream extends NodeSub
{
    public byte[] buffer;
    public int currentOffset;
    public int bitPosition;
    private static final int[] anIntArray1409;
    public ISAACRandomGen encryption;
    private static int anInt1412;
    private static final NodeList nodeList;
    
    public static Stream create() {
        synchronized (Stream.nodeList) {
            Stream stream = null;
            if (Stream.anInt1412 > 0) {
                --Stream.anInt1412;
                stream = (Stream)Stream.nodeList.popHead();
            }
            if (stream != null) {
                stream.currentOffset = 0;
                return stream;
            }
        }
        final Stream stream2 = new Stream();
        stream2.currentOffset = 0;
        stream2.buffer = new byte[25000];
        return stream2;
    }
    
    private Stream() {
    }
    
    public int readShort2() {
        this.currentOffset += 2;
        int n = ((this.buffer[this.currentOffset - 2] & 0xFF) << 8) + (this.buffer[this.currentOffset - 1] & 0xFF);
        if (n > 60000) {
            n -= 65535;
        }
        return n;
    }
    
    public Stream(final byte[] buffer) {
        this.buffer = buffer;
        this.currentOffset = 0;
    }
    
    public void writeByte(final int n) {
        this.buffer[this.currentOffset++] = (byte)n;
    }
    
    public void createFrame(final int n) {
        this.buffer[this.currentOffset++] = (byte)(n + this.encryption.getNextKey());
    }
    
    public void writeWordBigEndian(final int n) {
        this.buffer[this.currentOffset++] = (byte)n;
    }
    
    public String readNewString() {
        final int currentOffset = this.currentOffset;
        while (this.buffer[this.currentOffset++] != 0) {}
        return new String(this.buffer, currentOffset, this.currentOffset - currentOffset - 1);
    }
    
    public int readUSmart2() {
        int n = 0;
        int method422;
        while ((method422 = this.method422()) == 32767) {
            n += 32767;
        }
        return n + method422;
    }
    
    public void writeWord(final int n) {
        this.buffer[this.currentOffset++] = (byte)(n >> 8);
        this.buffer[this.currentOffset++] = (byte)n;
    }
    
    public void method400(final int n) {
        this.buffer[this.currentOffset++] = (byte)n;
        this.buffer[this.currentOffset++] = (byte)(n >> 8);
    }
    
    public void writeDWordBigEndian(final int n) {
        this.buffer[this.currentOffset++] = (byte)(n >> 16);
        this.buffer[this.currentOffset++] = (byte)(n >> 8);
        this.buffer[this.currentOffset++] = (byte)n;
    }
    
    public void writeDWord(final int n) {
        this.buffer[this.currentOffset++] = (byte)(n >> 24);
        this.buffer[this.currentOffset++] = (byte)(n >> 16);
        this.buffer[this.currentOffset++] = (byte)(n >> 8);
        this.buffer[this.currentOffset++] = (byte)n;
    }
    
    public void method403(final int n) {
        this.buffer[this.currentOffset++] = (byte)n;
        this.buffer[this.currentOffset++] = (byte)(n >> 8);
        this.buffer[this.currentOffset++] = (byte)(n >> 16);
        this.buffer[this.currentOffset++] = (byte)(n >> 24);
    }
    
    public void writeQWord(final long n) {
        try {
            this.buffer[this.currentOffset++] = (byte)(n >> 56);
            this.buffer[this.currentOffset++] = (byte)(n >> 48);
            this.buffer[this.currentOffset++] = (byte)(n >> 40);
            this.buffer[this.currentOffset++] = (byte)(n >> 32);
            this.buffer[this.currentOffset++] = (byte)(n >> 24);
            this.buffer[this.currentOffset++] = (byte)(n >> 16);
            this.buffer[this.currentOffset++] = (byte)(n >> 8);
            this.buffer[this.currentOffset++] = (byte)n;
        }
        catch (RuntimeException ex) {
            SignLink.reporterror("14395, 5, " + n + ", " + ex.toString());
            throw new RuntimeException();
        }
    }
    
    public void writeString(final String s) {
        System.arraycopy(s.getBytes(), 0, this.buffer, this.currentOffset, s.length());
        this.currentOffset += s.length();
        this.buffer[this.currentOffset++] = 10;
    }
    
    public void writeBytes(final byte[] array, final int n, final int n2) {
        for (int i = n2; i < n2 + n; ++i) {
            this.buffer[this.currentOffset++] = array[i];
        }
    }
    
    public void writeBytes(final int n) {
        this.buffer[this.currentOffset - n - 1] = (byte)n;
    }
    
    public int readUnsignedByte() {
        return this.buffer[this.currentOffset++] & 0xFF;
    }
    
    public byte readSignedByte() {
        return this.buffer[this.currentOffset++];
    }
    
    public int readUnsignedWord() {
        this.currentOffset += 2;
        return ((this.buffer[this.currentOffset - 2] & 0xFF) << 8) + (this.buffer[this.currentOffset - 1] & 0xFF);
    }
    
    public int readSignedWord() {
        this.currentOffset += 2;
        int n = ((this.buffer[this.currentOffset - 2] & 0xFF) << 8) + (this.buffer[this.currentOffset - 1] & 0xFF);
        if (n > 32767) {
            n -= 65536;
        }
        return n;
    }
    
    public int read3Bytes() {
        this.currentOffset += 3;
        return ((this.buffer[this.currentOffset - 3] & 0xFF) << 16) + ((this.buffer[this.currentOffset - 2] & 0xFF) << 8) + (this.buffer[this.currentOffset - 1] & 0xFF);
    }
    
    public int readDWord() {
        this.currentOffset += 4;
        return ((this.buffer[this.currentOffset - 4] & 0xFF) << 24) + ((this.buffer[this.currentOffset - 3] & 0xFF) << 16) + ((this.buffer[this.currentOffset - 2] & 0xFF) << 8) + (this.buffer[this.currentOffset - 1] & 0xFF);
    }
    
    public long readQWord() {
        return ((this.readDWord() & 0xFFFFFFFFL) << 32) + (this.readDWord() & 0xFFFFFFFFL);
    }
    
    public String readString() {
        final int currentOffset = this.currentOffset;
        while (this.buffer[this.currentOffset++] != 10) {}
        return new String(this.buffer, currentOffset, this.currentOffset - currentOffset - 1);
    }
    
    public byte[] readBytes() {
        final int currentOffset = this.currentOffset;
        while (this.buffer[this.currentOffset++] != 10) {}
        final byte[] array = new byte[this.currentOffset - currentOffset - 1];
        System.arraycopy(this.buffer, currentOffset, array, currentOffset - currentOffset, this.currentOffset - 1 - currentOffset);
        return array;
    }
    
    public void readBytes(final int n, final int n2, final byte[] array) {
        for (int i = n2; i < n2 + n; ++i) {
            array[i] = this.buffer[this.currentOffset++];
        }
    }
    
    public void initBitAccess() {
        this.bitPosition = this.currentOffset * 8;
    }
    
    public int readBits(int i) {
        int n = this.bitPosition >> 3;
        int n2 = 8 - (this.bitPosition & 0x7);
        int n3 = 0;
        this.bitPosition += i;
        while (i > n2) {
            n3 += (this.buffer[n++] & Stream.anIntArray1409[n2]) << i - n2;
            i -= n2;
            n2 = 8;
        }
        int n4;
        if (i == n2) {
            n4 = n3 + (this.buffer[n] & Stream.anIntArray1409[n2]);
        }
        else {
            n4 = n3 + (this.buffer[n] >> n2 - i & Stream.anIntArray1409[i]);
        }
        return n4;
    }
    
    public void finishBitAccess() {
        this.currentOffset = (this.bitPosition + 7) / 8;
    }
    
    public int method421() {
        if ((this.buffer[this.currentOffset] & 0xFF) < 128) {
            return this.readUnsignedByte() - 64;
        }
        return this.readUnsignedWord() - 49152;
    }
    
    public int method422() {
        if ((this.buffer[this.currentOffset] & 0xFF) < 128) {
            return this.readUnsignedByte();
        }
        return this.readUnsignedWord() - 32768;
    }
    
    public void doKeys() {
        final int currentOffset = this.currentOffset;
        this.currentOffset = 0;
        final byte[] array = new byte[currentOffset];
        this.readBytes(currentOffset, 0, array);
        final byte[] byteArray = new BigInteger(array).toByteArray();
        this.currentOffset = 0;
        this.writeWordBigEndian(byteArray.length);
        this.writeBytes(byteArray, byteArray.length, 0);
    }
    
    public void method424(final int n) {
        this.buffer[this.currentOffset++] = (byte)(-n);
    }
    
    public void method425(final int n) {
        this.buffer[this.currentOffset++] = (byte)(128 - n);
    }
    
    public int method426() {
        return this.buffer[this.currentOffset++] - 128 & 0xFF;
    }
    
    public int method427() {
        return -this.buffer[this.currentOffset++] & 0xFF;
    }
    
    public int method428() {
        return 128 - this.buffer[this.currentOffset++] & 0xFF;
    }
    
    public byte method429() {
        return (byte)(-this.buffer[this.currentOffset++]);
    }
    
    public byte method430() {
        return (byte)(128 - this.buffer[this.currentOffset++]);
    }
    
    public void method431(final int n) {
        this.buffer[this.currentOffset++] = (byte)n;
        this.buffer[this.currentOffset++] = (byte)(n >> 8);
    }
    
    public void method432(final int n) {
        this.buffer[this.currentOffset++] = (byte)(n >> 8);
        this.buffer[this.currentOffset++] = (byte)(n + 128);
    }
    
    public void method433(final int n) {
        this.buffer[this.currentOffset++] = (byte)(n + 128);
        this.buffer[this.currentOffset++] = (byte)(n >> 8);
    }
    
    public int method434() {
        this.currentOffset += 2;
        return ((this.buffer[this.currentOffset - 1] & 0xFF) << 8) + (this.buffer[this.currentOffset - 2] & 0xFF);
    }
    
    public int method435() {
        this.currentOffset += 2;
        return ((this.buffer[this.currentOffset - 2] & 0xFF) << 8) + (this.buffer[this.currentOffset - 1] - 128 & 0xFF);
    }
    
    public int method436() {
        this.currentOffset += 2;
        return ((this.buffer[this.currentOffset - 1] & 0xFF) << 8) + (this.buffer[this.currentOffset - 2] - 128 & 0xFF);
    }
    
    public int method437() {
        this.currentOffset += 2;
        int n = ((this.buffer[this.currentOffset - 1] & 0xFF) << 8) + (this.buffer[this.currentOffset - 2] & 0xFF);
        if (n > 32767) {
            n -= 65536;
        }
        return n;
    }
    
    public int method438() {
        this.currentOffset += 2;
        int n = ((this.buffer[this.currentOffset - 1] & 0xFF) << 8) + (this.buffer[this.currentOffset - 2] - 128 & 0xFF);
        if (n > 32767) {
            n -= 65536;
        }
        return n;
    }
    
    public int method439() {
        this.currentOffset += 4;
        return ((this.buffer[this.currentOffset - 2] & 0xFF) << 24) + ((this.buffer[this.currentOffset - 1] & 0xFF) << 16) + ((this.buffer[this.currentOffset - 4] & 0xFF) << 8) + (this.buffer[this.currentOffset - 3] & 0xFF);
    }
    
    public int method440() {
        this.currentOffset += 4;
        return ((this.buffer[this.currentOffset - 3] & 0xFF) << 24) + ((this.buffer[this.currentOffset - 4] & 0xFF) << 16) + ((this.buffer[this.currentOffset - 1] & 0xFF) << 8) + (this.buffer[this.currentOffset - 2] & 0xFF);
    }
    
    public void method441(final int n, final byte[] array, final int n2) {
        for (int i = n + n2 - 1; i >= n; --i) {
            this.buffer[this.currentOffset++] = (byte)(array[i] + 128);
        }
    }
    
    public void method442(final int n, final int n2, final byte[] array) {
        for (int i = n2 + n - 1; i >= n2; --i) {
            array[i] = this.buffer[this.currentOffset++];
        }
    }
    
    final int v(final int n) {
        this.currentOffset += 3;
        return (0xFF & this.buffer[this.currentOffset - 3] << 16) + (0xFF & this.buffer[this.currentOffset - 2] << 8) + (0xFF & this.buffer[this.currentOffset - 1]);
    }
    
    static {
        anIntArray1409 = new int[] { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071, 262143, 524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431, 67108863, 134217727, 268435455, 536870911, 1073741823, Integer.MAX_VALUE, -1 };
        nodeList = new NodeList();
    }
}
