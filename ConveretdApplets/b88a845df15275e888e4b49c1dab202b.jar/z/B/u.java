// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.lang.reflect.Method;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.File;
import java.io.InputStream;

public final class u extends X
{
    private static o Z;
    private InputStream f;
    private File d;
    private RandomAccessFile _;
    private int e;
    private byte[] b;
    private long a;
    private long Y;
    private boolean c;
    static /* synthetic */ Class class$java$lang$Thread;
    static /* synthetic */ Class class$java$lang$Runtime;
    
    public u(final InputStream f) throws IOException {
        this.e = 1024;
        this.b = new byte[this.e];
        this.a = 0L;
        this.Y = 0L;
        this.c = false;
        this.f = f;
        (this.d = File.createTempFile("jai-FCSS-", ".tmp")).deleteOnExit();
        this._ = new RandomAccessFile(this.d, "rw");
        if (u.Z != null) {
            u.Z.B(this.d);
        }
    }
    
    private long D(final long n) throws IOException {
        if (n < this.a) {
            return n;
        }
        if (this.c) {
            return this.a;
        }
        long n2 = n - this.a;
        this._.seek(this.a);
        while (n2 > 0L) {
            final int read = this.f.read(this.b, 0, (int)Math.min(n2, this.e));
            if (read == -1) {
                this.c = true;
                return this.a;
            }
            this._.setLength(this._.length() + read);
            this._.write(this.b, 0, read);
            n2 -= read;
            this.a += read;
        }
        return n;
    }
    
    public boolean B() {
        return true;
    }
    
    public long G() {
        return this.Y;
    }
    
    public void A(final long y) throws IOException {
        if (y < 0L) {
            throw new IOException(m.A("FileCacheSeekableStream0"));
        }
        this.Y = y;
    }
    
    public int read() throws IOException {
        final long n = this.Y + 1L;
        if (this.D(n) >= n) {
            this._.seek(this.Y++);
            return this._.read();
        }
        return -1;
    }
    
    public int read(final byte[] array, final int n, int n2) throws IOException {
        if (array == null) {
            throw new NullPointerException();
        }
        if (n < 0 || n2 < 0 || n + n2 > array.length) {
            throw new IndexOutOfBoundsException();
        }
        if (n2 == 0) {
            return 0;
        }
        n2 = (int)Math.min(n2, this.D(this.Y + n2) - this.Y);
        if (n2 > 0) {
            this._.seek(this.Y);
            this._.readFully(array, n, n2);
            this.Y += n2;
            return n2;
        }
        return -1;
    }
    
    public void close() throws IOException {
        super.close();
        this._.close();
        this.d.delete();
        if (u.Z != null) {
            u.Z.A(this.d);
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        u.Z = null;
        try {
            final Method declaredMethod = ((u.class$java$lang$Runtime == null) ? (u.class$java$lang$Runtime = class$("java.lang.Runtime")) : u.class$java$lang$Runtime).getDeclaredMethod("addShutdownHook", (u.class$java$lang$Thread == null) ? (u.class$java$lang$Thread = class$("java.lang.Thread")) : u.class$java$lang$Thread);
            u.Z = new o();
            declaredMethod.invoke(Runtime.getRuntime(), u.Z);
        }
        catch (Exception ex) {
            u.Z = null;
        }
    }
}
