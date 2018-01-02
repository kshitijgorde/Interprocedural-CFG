import java.io.IOException;
import java.io.RandomAccessFile;

// 
// Decompiled by Procyon v0.5.30
// 

final class Decompressor
{
    private static final byte[] buffer;
    private final RandomAccessFile dataFile;
    private final RandomAccessFile indexFile;
    private final int anInt311;
    
    public Decompressor(final RandomAccessFile dataFile, final RandomAccessFile indexFile, final int anInt311) {
        this.anInt311 = anInt311;
        this.dataFile = dataFile;
        this.indexFile = indexFile;
    }
    
    public synchronized byte[] decompress(final int n) {
        try {
            this.seekTo(this.indexFile, n * 6);
            int read;
            for (int i = 0; i < 6; i += read) {
                read = this.indexFile.read(Decompressor.buffer, i, 6 - i);
                if (read == -1) {
                    return null;
                }
            }
            final int n2 = ((Decompressor.buffer[0] & 0xFF) << 16) + ((Decompressor.buffer[1] & 0xFF) << 8) + (Decompressor.buffer[2] & 0xFF);
            int n3 = ((Decompressor.buffer[3] & 0xFF) << 16) + ((Decompressor.buffer[4] & 0xFF) << 8) + (Decompressor.buffer[5] & 0xFF);
            if (n3 <= 0 || n3 > this.dataFile.length() / 520L) {
                return null;
            }
            final byte[] array = new byte[n2];
            int j = 0;
            int n4 = 0;
            while (j < n2) {
                if (n3 == 0) {
                    return null;
                }
                this.seekTo(this.dataFile, n3 * 520);
                int k = 0;
                int n5 = n2 - j;
                if (n5 > 512) {
                    n5 = 512;
                }
                while (k < n5 + 8) {
                    final int read2 = this.dataFile.read(Decompressor.buffer, k, n5 + 8 - k);
                    if (read2 == -1) {
                        return null;
                    }
                    k += read2;
                }
                final int n6 = ((Decompressor.buffer[0] & 0xFF) << 8) + (Decompressor.buffer[1] & 0xFF);
                final int n7 = ((Decompressor.buffer[2] & 0xFF) << 8) + (Decompressor.buffer[3] & 0xFF);
                final int n8 = ((Decompressor.buffer[4] & 0xFF) << 16) + ((Decompressor.buffer[5] & 0xFF) << 8) + (Decompressor.buffer[6] & 0xFF);
                final int n9 = Decompressor.buffer[7] & 0xFF;
                if (n6 != n || n7 != n4 || n9 != this.anInt311) {
                    return null;
                }
                if (n8 < 0 || n8 > this.dataFile.length() / 520L) {
                    return null;
                }
                for (int l = 0; l < n5; ++l) {
                    array[j++] = Decompressor.buffer[l + 8];
                }
                n3 = n8;
                ++n4;
            }
            return array;
        }
        catch (IOException ex) {
            return null;
        }
    }
    
    public synchronized boolean method234(final int n, final byte[] array, final int n2) {
        boolean b = this.method235(true, n2, n, array);
        if (!b) {
            b = this.method235(false, n2, n, array);
        }
        return b;
    }
    
    private synchronized boolean method235(boolean b, final int n, final int n2, final byte[] array) {
        try {
            int n3;
            if (b) {
                this.seekTo(this.indexFile, n * 6);
                int read;
                for (int i = 0; i < 6; i += read) {
                    read = this.indexFile.read(Decompressor.buffer, i, 6 - i);
                    if (read == -1) {
                        return false;
                    }
                }
                n3 = ((Decompressor.buffer[3] & 0xFF) << 16) + ((Decompressor.buffer[4] & 0xFF) << 8) + (Decompressor.buffer[5] & 0xFF);
                if (n3 <= 0 || n3 > this.dataFile.length() / 520L) {
                    return false;
                }
            }
            else {
                n3 = (int)((this.dataFile.length() + 519L) / 520L);
                if (n3 == 0) {
                    n3 = 1;
                }
            }
            Decompressor.buffer[0] = (byte)(n2 >> 16);
            Decompressor.buffer[1] = (byte)(n2 >> 8);
            Decompressor.buffer[2] = (byte)n2;
            Decompressor.buffer[3] = (byte)(n3 >> 16);
            Decompressor.buffer[4] = (byte)(n3 >> 8);
            Decompressor.buffer[5] = (byte)n3;
            this.seekTo(this.indexFile, n * 6);
            this.indexFile.write(Decompressor.buffer, 0, 6);
            int n5;
            int n9;
            for (int j = 0, n4 = 0; j < n2; j += n9, n3 = n5, ++n4) {
                n5 = 0;
                if (b) {
                    this.seekTo(this.dataFile, n3 * 520);
                    int k;
                    int read2;
                    for (k = 0; k < 8; k += read2) {
                        read2 = this.dataFile.read(Decompressor.buffer, k, 8 - k);
                        if (read2 == -1) {
                            break;
                        }
                    }
                    if (k == 8) {
                        final int n6 = ((Decompressor.buffer[0] & 0xFF) << 8) + (Decompressor.buffer[1] & 0xFF);
                        final int n7 = ((Decompressor.buffer[2] & 0xFF) << 8) + (Decompressor.buffer[3] & 0xFF);
                        n5 = ((Decompressor.buffer[4] & 0xFF) << 16) + ((Decompressor.buffer[5] & 0xFF) << 8) + (Decompressor.buffer[6] & 0xFF);
                        final int n8 = Decompressor.buffer[7] & 0xFF;
                        if (n6 != n || n7 != n4 || n8 != this.anInt311) {
                            return false;
                        }
                        if (n5 < 0 || n5 > this.dataFile.length() / 520L) {
                            return false;
                        }
                    }
                }
                if (n5 == 0) {
                    b = false;
                    n5 = (int)((this.dataFile.length() + 519L) / 520L);
                    if (n5 == 0) {
                        ++n5;
                    }
                    if (n5 == n3) {
                        ++n5;
                    }
                }
                if (n2 - j <= 512) {
                    n5 = 0;
                }
                Decompressor.buffer[0] = (byte)(n >> 8);
                Decompressor.buffer[1] = (byte)n;
                Decompressor.buffer[2] = (byte)(n4 >> 8);
                Decompressor.buffer[3] = (byte)n4;
                Decompressor.buffer[4] = (byte)(n5 >> 16);
                Decompressor.buffer[5] = (byte)(n5 >> 8);
                Decompressor.buffer[6] = (byte)n5;
                Decompressor.buffer[7] = (byte)this.anInt311;
                this.seekTo(this.dataFile, n3 * 520);
                this.dataFile.write(Decompressor.buffer, 0, 8);
                n9 = n2 - j;
                if (n9 > 512) {
                    n9 = 512;
                }
                this.dataFile.write(array, j, n9);
            }
            return true;
        }
        catch (IOException ex) {
            return false;
        }
    }
    
    private synchronized void seekTo(final RandomAccessFile randomAccessFile, int n) throws IOException {
        if (n < 0 || n > 1600001792) {
            System.out.println("Badseek - pos:" + n + " len:" + randomAccessFile.length());
            n = 100000000;
            try {
                Thread.sleep(1000L);
            }
            catch (Exception ex) {}
        }
        randomAccessFile.seek(n);
    }
    
    static {
        buffer = new byte[520];
    }
}
