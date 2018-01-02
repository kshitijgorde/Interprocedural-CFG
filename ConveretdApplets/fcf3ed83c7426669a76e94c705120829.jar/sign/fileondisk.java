// 
// Decompiled by Procyon v0.5.30
// 

package sign;

import java.io.IOException;
import java.io.File;
import java.io.RandomAccessFile;

public final class fileondisk
{
    private long b;
    public final long e;
    private RandomAccessFile d;
    public final File c;
    public static boolean a;
    
    fileondisk(final File c, final String s, long e) throws IOException {
        final boolean a = fileondisk.a;
        this.c = c;
        final long n = lcmp(e, 0L);
        fileondisk fileondisk = null;
        Label_0092: {
            if (!a) {
                if (n < 0) {
                    e = Long.MAX_VALUE;
                }
                fileondisk = this;
                if (a) {
                    break Label_0092;
                }
                this.e = e;
                s.equals("r");
            }
            Label_0091: {
                if (n == 0) {
                    final boolean exists = c.exists();
                    if (!a) {
                        if (!exists) {
                            break Label_0091;
                        }
                        c.isFile();
                    }
                    Label_0090: {
                        if (!a) {
                            if (exists) {
                                final long n2 = lcmp(c.length(), e);
                                if (a) {
                                    break Label_0090;
                                }
                                if (n2 <= 0) {
                                    break Label_0091;
                                }
                            }
                            c.delete();
                        }
                    }
                }
            }
            fileondisk = this;
        }
        final RandomAccessFile d = new RandomAccessFile(c, s);
        fileondisk.d = d;
        RandomAccessFile randomAccessFile = d;
        try {
            final int read = randomAccessFile.read();
            if (!a) {
                if (read > -1 && !s.equals("r")) {
                    randomAccessFile.seek(0L);
                    randomAccessFile.write(read);
                }
                randomAccessFile.seek(0L);
            }
            randomAccessFile = null;
        }
        finally {
            final RandomAccessFile randomAccessFile2 = randomAccessFile;
            if (a || randomAccessFile2 != null) {
                randomAccessFile2.close();
            }
        }
        if (signlink.q != 0) {
            sign.fileondisk.a = !a;
        }
    }
    
    public void b(final byte[] array, final int n, final int n2) throws IOException {
        fileondisk fileondisk = this;
        fileondisk fileondisk2 = this;
        if (!sign.fileondisk.a) {
            if (this.b + n2 > this.e) {
                this.a(this.e);
                this.d.write(1);
                throw new IOException();
            }
            this.d.write(array, n, n2);
            fileondisk = this;
            fileondisk2 = this;
        }
        fileondisk.b = fileondisk2.b + n2;
    }
    
    public int a(final byte[] array, int n, final int n2) throws IOException {
        final int read;
        n = (read = this.d.read(array, n, n2));
        if (!fileondisk.a && read > 0) {
            this.b += n;
            goto Label_0032;
        }
        return read;
    }
    
    public long a() throws IOException {
        return this.d.length();
    }
    
    public void a(final long b) throws IOException {
        final boolean a = fileondisk.a;
        long n2;
        final long n = n2 = lcmp(b, 0L);
        if (!a) {
            if (n < 0) {
                throw new IOException();
            }
            final int n3;
            n2 = (n3 = lcmp(b, this.e));
        }
        fileondisk fileondisk = null;
        Label_0058: {
            if (!a) {
                if (n > 0) {
                    throw new IOException();
                }
                fileondisk = this;
                if (a) {
                    break Label_0058;
                }
                n2 = lcmp(this.b, b);
            }
            if (n2 == 0) {
                return;
            }
            this.d.seek(b);
            fileondisk = this;
        }
        fileondisk.b = b;
    }
    
    public void b() {
        final RandomAccessFile d = this.d;
        Label_0022: {
            if (fileondisk.a) {
                break Label_0022;
            }
            if (d == null) {
                return;
            }
            this.d = null;
            try {
                d.close();
            }
            catch (Exception ex) {}
        }
    }
    
    @Override
    protected void finalize() throws Throwable {
        try {
            this.b();
        }
        finally {
            super.finalize();
        }
    }
}
