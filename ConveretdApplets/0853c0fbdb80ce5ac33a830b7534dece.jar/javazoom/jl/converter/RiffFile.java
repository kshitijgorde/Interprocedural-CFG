// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.converter;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RiffFile
{
    public static final int DDC_SUCCESS = 0;
    public static final int DDC_FAILURE = 1;
    public static final int DDC_OUT_OF_MEMORY = 2;
    public static final int DDC_FILE_ERROR = 3;
    public static final int DDC_INVALID_CALL = 4;
    public static final int DDC_USER_ABORT = 5;
    public static final int DDC_INVALID_FILE = 6;
    public static final int RFM_UNKNOWN = 0;
    public static final int RFM_WRITE = 1;
    public static final int RFM_READ = 2;
    private RiffChunkHeader riff_header;
    protected int fmode;
    protected RandomAccessFile file;
    
    public RiffFile() {
        this.file = null;
        this.fmode = 0;
        this.riff_header = new RiffChunkHeader();
        this.riff_header.ckID = FourCC("RIFF");
        this.riff_header.ckSize = 0;
    }
    
    public int CurrentFileMode() {
        return this.fmode;
    }
    
    public int Open(final String s, final int n) {
        int close = 0;
        if (this.fmode != 0) {
            close = this.Close();
        }
        if (close == 0) {
            switch (n) {
                case 1: {
                    try {
                        this.file = new RandomAccessFile(s, "rw");
                        try {
                            final byte[] array = { (byte)(this.riff_header.ckID >>> 24 & 0xFF), (byte)(this.riff_header.ckID >>> 16 & 0xFF), (byte)(this.riff_header.ckID >>> 8 & 0xFF), (byte)(this.riff_header.ckID & 0xFF), 0, 0, 0, 0 };
                            final byte b = (byte)(this.riff_header.ckSize >>> 24 & 0xFF);
                            final byte b2 = (byte)(this.riff_header.ckSize >>> 16 & 0xFF);
                            final byte b3 = (byte)(this.riff_header.ckSize >>> 8 & 0xFF);
                            array[4] = (byte)(this.riff_header.ckSize & 0xFF);
                            array[5] = b3;
                            array[6] = b2;
                            array[7] = b;
                            this.file.write(array, 0, 8);
                            this.fmode = 1;
                        }
                        catch (IOException ex) {
                            this.file.close();
                            this.fmode = 0;
                        }
                    }
                    catch (IOException ex2) {
                        this.fmode = 0;
                        close = 3;
                    }
                    break;
                }
                case 2: {
                    try {
                        this.file = new RandomAccessFile(s, "r");
                        try {
                            final byte[] array2 = new byte[8];
                            this.file.read(array2, 0, 8);
                            this.fmode = 2;
                            this.riff_header.ckID = ((array2[0] << 24 & 0xFF000000) | (array2[1] << 16 & 0xFF0000) | (array2[2] << 8 & 0xFF00) | (array2[3] & 0xFF));
                            this.riff_header.ckSize = ((array2[4] << 24 & 0xFF000000) | (array2[5] << 16 & 0xFF0000) | (array2[6] << 8 & 0xFF00) | (array2[7] & 0xFF));
                        }
                        catch (IOException ex3) {
                            this.file.close();
                            this.fmode = 0;
                        }
                    }
                    catch (IOException ex4) {
                        this.fmode = 0;
                        close = 3;
                    }
                    break;
                }
                default: {
                    close = 4;
                    break;
                }
            }
        }
        return close;
    }
    
    public int Write(final byte[] array, final int n) {
        if (this.fmode != 1) {
            return 4;
        }
        try {
            this.file.write(array, 0, n);
            this.fmode = 1;
        }
        catch (IOException ex) {
            return 3;
        }
        final RiffChunkHeader riff_header = this.riff_header;
        riff_header.ckSize += n;
        return 0;
    }
    
    public int Write(final short[] array, final int n) {
        final byte[] array2 = new byte[n];
        int n2 = 0;
        for (int i = 0; i < n; i += 2) {
            array2[i] = (byte)(array[n2] & 0xFF);
            array2[i + 1] = (byte)(array[n2++] >>> 8 & 0xFF);
        }
        if (this.fmode != 1) {
            return 4;
        }
        try {
            this.file.write(array2, 0, n);
            this.fmode = 1;
        }
        catch (IOException ex) {
            return 3;
        }
        final RiffChunkHeader riff_header = this.riff_header;
        riff_header.ckSize += n;
        return 0;
    }
    
    public int Write(final RiffChunkHeader riffChunkHeader, final int n) {
        final byte[] array = { (byte)(riffChunkHeader.ckID >>> 24 & 0xFF), (byte)(riffChunkHeader.ckID >>> 16 & 0xFF), (byte)(riffChunkHeader.ckID >>> 8 & 0xFF), (byte)(riffChunkHeader.ckID & 0xFF), 0, 0, 0, 0 };
        final byte b = (byte)(riffChunkHeader.ckSize >>> 24 & 0xFF);
        final byte b2 = (byte)(riffChunkHeader.ckSize >>> 16 & 0xFF);
        final byte b3 = (byte)(riffChunkHeader.ckSize >>> 8 & 0xFF);
        array[4] = (byte)(riffChunkHeader.ckSize & 0xFF);
        array[5] = b3;
        array[6] = b2;
        array[7] = b;
        if (this.fmode != 1) {
            return 4;
        }
        try {
            this.file.write(array, 0, n);
            this.fmode = 1;
        }
        catch (IOException ex) {
            return 3;
        }
        final RiffChunkHeader riff_header = this.riff_header;
        riff_header.ckSize += n;
        return 0;
    }
    
    public int Write(final short n, final int n2) {
        final short n3 = (short)((n >>> 8 & 0xFF) | (n << 8 & 0xFF00));
        if (this.fmode != 1) {
            return 4;
        }
        try {
            this.file.writeShort(n3);
            this.fmode = 1;
        }
        catch (IOException ex) {
            return 3;
        }
        final RiffChunkHeader riff_header = this.riff_header;
        riff_header.ckSize += n2;
        return 0;
    }
    
    public int Write(final int n, final int n2) {
        final short n3 = (short)(n >>> 16 & 0xFFFF);
        final short n4 = (short)(n & 0xFFFF);
        final int n5 = ((short)((n4 >>> 8 & 0xFF) | (n4 << 8 & 0xFF00)) << 16 & 0xFFFF0000) | ((short)((n3 >>> 8 & 0xFF) | (n3 << 8 & 0xFF00)) & 0xFFFF);
        if (this.fmode != 1) {
            return 4;
        }
        try {
            this.file.writeInt(n5);
            this.fmode = 1;
        }
        catch (IOException ex) {
            return 3;
        }
        final RiffChunkHeader riff_header = this.riff_header;
        riff_header.ckSize += n2;
        return 0;
    }
    
    public int Read(final byte[] array, final int n) {
        int n2 = 0;
        try {
            this.file.read(array, 0, n);
        }
        catch (IOException ex) {
            n2 = 3;
        }
        return n2;
    }
    
    public int Expect(final String s, int n) {
        int n2 = 0;
        try {
            while (n-- != 0) {
                if (this.file.readByte() != s.charAt(n2++)) {
                    return 3;
                }
            }
        }
        catch (IOException ex) {
            return 3;
        }
        return 0;
    }
    
    public int Close() {
        int n = 0;
        switch (this.fmode) {
            case 1: {
                try {
                    this.file.seek(0L);
                    try {
                        this.file.write(new byte[] { (byte)(this.riff_header.ckID >>> 24 & 0xFF), (byte)(this.riff_header.ckID >>> 16 & 0xFF), (byte)(this.riff_header.ckID >>> 8 & 0xFF), (byte)(this.riff_header.ckID & 0xFF), (byte)(this.riff_header.ckSize & 0xFF), (byte)(this.riff_header.ckSize >>> 8 & 0xFF), (byte)(this.riff_header.ckSize >>> 16 & 0xFF), (byte)(this.riff_header.ckSize >>> 24 & 0xFF) }, 0, 8);
                        this.file.close();
                    }
                    catch (IOException ex) {
                        n = 3;
                    }
                }
                catch (IOException ex2) {
                    n = 3;
                }
                break;
            }
            case 2: {
                try {
                    this.file.close();
                }
                catch (IOException ex3) {
                    n = 3;
                }
                break;
            }
        }
        this.file = null;
        this.fmode = 0;
        return n;
    }
    
    public long CurrentFilePosition() {
        long filePointer;
        try {
            filePointer = this.file.getFilePointer();
        }
        catch (IOException ex) {
            filePointer = -1L;
        }
        return filePointer;
    }
    
    public int Backpatch(final long n, final RiffChunkHeader riffChunkHeader, final int n2) {
        if (this.file == null) {
            return 4;
        }
        try {
            this.file.seek(n);
        }
        catch (IOException ex) {
            return 3;
        }
        return this.Write(riffChunkHeader, n2);
    }
    
    public int Backpatch(final long n, final byte[] array, final int n2) {
        if (this.file == null) {
            return 4;
        }
        try {
            this.file.seek(n);
        }
        catch (IOException ex) {
            return 3;
        }
        return this.Write(array, n2);
    }
    
    protected int Seek(final long n) {
        int n2;
        try {
            this.file.seek(n);
            n2 = 0;
        }
        catch (IOException ex) {
            n2 = 3;
        }
        return n2;
    }
    
    private String DDCRET_String(final int n) {
        switch (n) {
            case 0: {
                return "DDC_SUCCESS";
            }
            case 1: {
                return "DDC_FAILURE";
            }
            case 2: {
                return "DDC_OUT_OF_MEMORY";
            }
            case 3: {
                return "DDC_FILE_ERROR";
            }
            case 4: {
                return "DDC_INVALID_CALL";
            }
            case 5: {
                return "DDC_USER_ABORT";
            }
            case 6: {
                return "DDC_INVALID_FILE";
            }
            default: {
                return "Unknown Error";
            }
        }
    }
    
    public static int FourCC(final String s) {
        final byte[] array = { 32, 32, 32, 32 };
        s.getBytes(0, 4, array, 0);
        return (array[0] << 24 & 0xFF000000) | (array[1] << 16 & 0xFF0000) | (array[2] << 8 & 0xFF00) | (array[3] & 0xFF);
    }
    
    class RiffChunkHeader
    {
        public int ckID;
        public int ckSize;
        
        public RiffChunkHeader() {
            this.ckID = 0;
            this.ckSize = 0;
        }
    }
}
