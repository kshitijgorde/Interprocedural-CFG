import java.io.EOFException;
import java.io.IOException;
import java.io.File;
import java.io.RandomAccessFile;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class356
{
    static int anInt3018;
    private long aLong3019;
    static int anInt3020;
    private RandomAccessFile aRandomAccessFile3021;
    private File aFile3022;
    static Class97[] aClass97Array3023;
    private long aLong3024;
    static int anInt3025;
    
    final File method3876(final byte b) {
        try {
            if (b != 3) {
                return null;
            }
            return this.aFile3022;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vl.G(" + b + ')');
        }
    }
    
    final void method3877(final byte b, final long aLong3019) throws IOException {
        try {
            this.aRandomAccessFile3021.seek(aLong3019);
            this.aLong3019 = aLong3019;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vl.A(" + b + ',' + aLong3019 + ')');
        }
    }
    
    final long method3878(final byte b) throws IOException {
        try {
            if (b > -17) {
                this.method3876((byte)94);
            }
            return this.aRandomAccessFile3021.length();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vl.F(" + b + ')');
        }
    }
    
    final int method3879(final int n, final byte b, final int n2, final byte[] array) throws IOException {
        try {
            if (b != -26) {
                this.aLong3024 = -32L;
            }
            final int read = this.aRandomAccessFile3021.read(array, n2, n);
            if (~read < -1) {
                this.aLong3019 += read;
            }
            return read;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vl.C(" + n + ',' + b + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method3880(final boolean b) throws IOException {
        try {
            if (this.aRandomAccessFile3021 != null) {
                this.aRandomAccessFile3021.close();
                this.aRandomAccessFile3021 = null;
            }
            if (!b) {
                this.method3876((byte)(-68));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vl.E(" + b + ')');
        }
    }
    
    Class356(final File aFile3022, final String s, long aLong3024) throws IOException {
        try {
            if (aLong3024 == -1L) {
                aLong3024 = Long.MAX_VALUE;
            }
            if (~aFile3022.length() < ~aLong3024) {
                aFile3022.delete();
            }
            this.aRandomAccessFile3021 = new RandomAccessFile(aFile3022, s);
            this.aLong3024 = aLong3024;
            this.aLong3019 = 0L;
            this.aFile3022 = aFile3022;
            final int read = this.aRandomAccessFile3021.read();
            if (read != -1 && !s.equals("r")) {
                this.aRandomAccessFile3021.seek(0L);
                this.aRandomAccessFile3021.write(read);
            }
            this.aRandomAccessFile3021.seek(0L);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vl.<init>(" + ((aFile3022 != null) ? "{...}" : "null") + ',' + ((s != null) ? "{...}" : "null") + ',' + aLong3024 + ')');
        }
    }
    
    public static void method3881(final int n) {
        try {
            if (n != -14445) {
                method3881(107);
            }
            Class356.aClass97Array3023 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vl.D(" + n + ')');
        }
    }
    
    @Override
    protected final void finalize() throws Throwable {
        try {
            if (this.aRandomAccessFile3021 != null) {
                System.out.println("Warning! fileondisk " + this.aFile3022 + " not closed correctly using close(). Auto-closing instead. ");
                this.method3880(true);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vl.finalize()");
        }
    }
    
    final void method3882(final byte[] array, final int n, final int n2, final int n3) throws IOException {
        try {
            if (~(n3 - -this.aLong3019) < ~this.aLong3024) {
                this.aRandomAccessFile3021.seek(this.aLong3024);
                this.aRandomAccessFile3021.write(1);
                throw new EOFException();
            }
            this.aRandomAccessFile3021.write(array, n2, n3);
            if (n != 4657) {
                this.method3876((byte)49);
            }
            this.aLong3019 += n3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vl.B(" + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static {
        Class356.anInt3018 = 100;
        Class356.aClass97Array3023 = new Class97[14];
    }
}
