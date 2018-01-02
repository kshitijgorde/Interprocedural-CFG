import java.io.IOException;
import java.io.DataInputStream;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class hm35inputstream extends InputStream
{
    public byte[][] k0;
    public int k_;
    public int kz;
    public int ky;
    public boolean kx;
    public boolean kw;
    public boolean kv;
    public int ku;
    public int kt;
    public byte[] ks;
    public hm35player ab;
    
    private static int dk(final DataInputStream dataInputStream, final byte[][] array, final int n, int i) {
        int n2 = 0;
        int n3 = n / 1048576;
        int n4 = n % 1048576;
        int n5 = 1048576 - n4;
        try {
            while (i > 0) {
                if (i < n5) {
                    n5 = i;
                }
                dataInputStream.readFully(array[n3], n4, n5);
                ++n3;
                i -= n5;
                n4 = 0;
                n2 += n5;
                n5 = ((i < 1048576) ? i : 1048576);
            }
        }
        catch (Exception ex) {}
        return n2;
    }
    
    private static void dl(final byte[][] array, final int n, final byte[] array2, int n2, int i) {
        int n3 = n / 1048576;
        int n4 = n % 1048576;
        int n5 = 1048576 - n4;
        while (i > 0) {
            try {
                if (i < n5) {
                    n5 = i;
                }
                System.arraycopy(array[n3], n4, array2, n2, n5);
                ++n3;
                n2 += n5;
                i -= n5;
                n4 = 0;
                n5 = ((i < 1048576) ? i : 1048576);
            }
            catch (Exception ex) {}
        }
    }
    
    public final int dm() {
        final int k_ = this.k_;
        if (k_ == 0) {
            return this.kt - 1;
        }
        return k_ - 1;
    }
    
    public final void dn() {
        this.kv = false;
        this.kx = false;
        this.kw = false;
    }
    
    public final void do() {
        this.kz = 0;
        this.k_ = 1;
        this.kv = false;
        this.kx = false;
        this.kw = false;
    }
    
    public final void dp() {
        this.kx = true;
    }
    
    public final void dq(final boolean b, final int n, final int n2, final DataInputStream dataInputStream) {
        if (this.k0 == null) {
            throw new IOException(String.valueOf(this.toString()) + " attempt to putData before buffer size set");
        }
        int n3 = n;
        final int kt = this.kt;
        while (n3 > 0 && !this.kv) {
            final int kz = this.kz;
            final int k_ = this.k_;
            int n4;
            if (k_ > kz) {
                n4 = kt - k_;
            }
            else {
                n4 = kz - k_;
            }
            if (n4 != 0) {
                if (n4 >= n3) {
                    if (n3 > 512) {
                        n4 = 512;
                    }
                    else {
                        n4 = n3;
                    }
                }
                dk(dataInputStream, this.k0, k_, n4);
                n3 -= n4;
                int k_2 = k_ + n4;
                if (k_2 >= kt) {
                    k_2 = 0;
                }
                this.k_ = k_2;
            }
            else {
                try {
                    Thread.sleep(50L);
                }
                catch (InterruptedException ex) {}
            }
            if (this.kv) {
                throw new IOException(String.valueOf(this.toString()) + " Abort - Stream closed");
            }
        }
    }
    
    public final synchronized void reset() {
        this.kz = this.ky;
    }
    
    public final synchronized void mark(final int ky) {
        this.ky = ky;
    }
    
    public final boolean markSupported() {
        return true;
    }
    
    public final void close() {
        this.kv = true;
    }
    
    public final long skip(final long n) {
        final int length = this.ks.length;
        int n2;
        int read;
        for (n2 = 0; n2 < n; n2 += read) {
            read = this.read(this.ks, 0, (int)((n - n2 > length) ? length : (n - n2)));
            if (read == -1) {
                break;
            }
        }
        return n2;
    }
    
    public final int read(final byte[] array, int n, int n2) {
        if (this.ab != null) {
            return this.ab.af(array, n, n2);
        }
        if (n2 <= 0) {
            return 0;
        }
        while (this.k0 == null) {
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {}
            if (this.kx) {
                return -1;
            }
        }
        int n3 = 0;
        final int kt = this.kt;
        while (n2 > 0 && !this.kw) {
            final int k_ = this.k_;
            int kz = this.kz;
            int n4;
            if (k_ > kz) {
                n4 = k_ - kz - 1;
            }
            else if (kz == kt - 1) {
                n4 = k_;
            }
            else {
                n4 = kt - kz - 1;
            }
            if (n4 != 0) {
                if (++kz >= kt) {
                    kz = 0;
                }
                if (n4 >= n2) {
                    n4 = n2;
                }
                dl(this.k0, kz, array, n, n4);
                n += n4;
                n2 -= n4;
                final int kz2 = kz + (n4 - 1);
                n3 += n4;
                this.kz = kz2;
            }
            else if (this.kx) {
                this.kw = true;
            }
            else {
                if (n3 != 0) {
                    break;
                }
                try {
                    Thread.sleep(20L);
                }
                catch (InterruptedException ex2) {}
            }
        }
        if (n3 == 0) {
            return -1;
        }
        return n3;
    }
    
    public final int read() {
        if (this.ab != null) {
            return this.ab.af();
        }
        if (this.read(this.ks, 0, 1) == -1) {
            return -1;
        }
        return this.ks[0] & 0xFF;
    }
    
    public final int available() {
        int n = 0;
        if (this.k0 != null) {
            final int k_ = this.k_;
            final int kz = this.kz;
            if (k_ > kz) {
                n = k_ - kz - 1;
            }
            else {
                n = this.kt - (kz - k_) - 1;
            }
        }
        return n;
    }
    
    private final void dr(final int n) {
        int i = 0;
        int n2 = 0;
        this.kt = n + 1;
        this.ku = this.kt / 1048576;
        this.k0 = new byte[this.ku + 1][];
        try {
            for (i = 0; i < this.ku; ++i) {
                this.k0[i] = new byte[1048576];
                n2 += 1048576;
            }
            this.k0[i] = new byte[this.kt % 1048576];
        }
        catch (OutOfMemoryError outOfMemoryError) {}
        this.kt = n2 + this.k0[i].length;
    }
    
    public hm35inputstream(final hm35player ab) {
        this.k_ = 1;
        this.kx = false;
        this.kw = false;
        this.kv = false;
        this.ku = 1;
        this.ks = new byte[256];
        this.ab = ab;
    }
    
    public hm35inputstream(final int n) {
        this.k_ = 1;
        this.kx = false;
        this.kw = false;
        this.kv = false;
        this.ku = 1;
        this.ks = new byte[256];
        this.dr(n);
    }
    
    public hm35inputstream() {
        this.k_ = 1;
        this.kx = false;
        this.kw = false;
        this.kv = false;
        this.ku = 1;
        this.ks = new byte[256];
    }
}
