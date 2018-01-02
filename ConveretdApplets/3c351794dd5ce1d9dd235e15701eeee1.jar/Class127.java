import java.io.EOFException;
import java.io.InputStream;
import java.io.IOException;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class127 implements Runnable
{
    private int anInt1016;
    private IOException anIOException1017;
    static int anInt1018;
    static Class207 aClass207_1019;
    private int anInt1020;
    private Thread aThread1021;
    private byte[] aByteArray1022;
    private int anInt1023;
    private InputStream anInputStream1024;
    
    public static void method2218(final int n) {
        try {
            Class127.aClass207_1019 = null;
            if (n != 0) {
                Class127.anInt1018 = -71;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ik.D(" + n + ')');
        }
    }
    
    final void method2219(final byte b) {
        try {
            if (b <= 85) {
                this.run();
            }
            this.anInputStream1024 = new InputStream_Sub2();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ik.C(" + b + ')');
        }
    }
    
    @Override
    public final void run() {
        try {
            while (true) {
                Label_0111: {
                    int n;
                    synchronized (this) {
                        while (this.anIOException1017 == null) {
                            if (~this.anInt1016 == -1) {
                                n = -1 + -this.anInt1020 + this.anInt1023;
                            }
                            else if (this.anInt1016 >= this.anInt1020) {
                                n = -this.anInt1020 + (this.anInt1016 - 1);
                            }
                            else {
                                n = this.anInt1023 + -this.anInt1020;
                            }
                            if (~n < -1) {
                                break Label_0111;
                            }
                            try {
                                this.wait();
                            }
                            catch (InterruptedException ex) {}
                        }
                        return;
                    }
                    Label_0170: {
                        try {
                            final int read = this.anInputStream1024.read(this.aByteArray1022, this.anInt1020, n);
                            if (~read == 0x0) {
                                throw new EOFException();
                            }
                            break Label_0170;
                        }
                        catch (IOException anIOException1017) {
                            synchronized (this) {
                                this.anIOException1017 = anIOException1017;
                                return;
                            }
                            synchronized (this) {
                                final int read;
                                this.anInt1020 = (read + this.anInt1020) % this.anInt1023;
                            }
                            continue;
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex2) {}
    }
    
    final void method2220(final int n) {
        try {
            synchronized (this) {
                if (this.anIOException1017 == null) {
                    this.anIOException1017 = new IOException("");
                }
                this.notifyAll();
            }
            try {
                if (n <= 53) {
                    Class127.anInt1018 = 123;
                }
                this.aThread1021.join();
            }
            catch (InterruptedException ex2) {}
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ik.E(" + n + ')');
        }
    }
    
    final int method2221(final int n, final byte[] array, int n2, final byte b) throws IOException {
        try {
            if (~n2 > -1 || ~n > -1 || ~array.length > ~(n + n2)) {
                throw new IOException();
            }
            synchronized (this) {
                int n3;
                if (~this.anInt1020 > ~this.anInt1016) {
                    n3 = this.anInt1020 + (this.anInt1023 - this.anInt1016);
                }
                else {
                    n3 = -this.anInt1016 + this.anInt1020;
                }
                if (b != 59) {
                    this.method2220(-89);
                }
                if (~n2 < ~n3) {
                    n2 = n3;
                }
                if (~n2 == -1 && this.anIOException1017 != null) {
                    throw new IOException(this.anIOException1017.toString());
                }
                if (this.anInt1023 >= this.anInt1016 - -n2) {
                    Class236.method2894(this.aByteArray1022, this.anInt1016, array, n, n2);
                }
                else {
                    final int n4 = -this.anInt1016 + this.anInt1023;
                    Class236.method2894(this.aByteArray1022, this.anInt1016, array, n, n4);
                    Class236.method2894(this.aByteArray1022, 0, array, n4 + n, n2 - n4);
                }
                this.anInt1016 = (this.anInt1016 - -n2) % this.anInt1023;
                this.notifyAll();
                return n2;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ik.B(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ',' + b + ')');
        }
    }
    
    final boolean method2222(final int n, final byte b) throws IOException {
        try {
            if (b > -120) {
                Class127.aClass207_1019 = null;
            }
            if (~n >= -1 || ~n <= ~this.anInt1023) {
                throw new IOException();
            }
            synchronized (this) {
                int n2 = 0;
                Label_0093: {
                    if (~this.anInt1016 < ~this.anInt1020) {
                        n2 = this.anInt1023 - (this.anInt1016 + -this.anInt1020);
                        if (!client.aBoolean3553) {
                            break Label_0093;
                        }
                    }
                    n2 = this.anInt1020 - this.anInt1016;
                }
                if (~n2 <= ~n) {
                    return true;
                }
                if (this.anIOException1017 != null) {
                    throw new IOException(this.anIOException1017.toString());
                }
                return false;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ik.A(" + n + ',' + b + ')');
        }
    }
    
    Class127(final InputStream anInputStream1024, final int n) {
        this.anInt1016 = 0;
        this.anInt1020 = 0;
        try {
            this.anInt1023 = n + 1;
            this.anInputStream1024 = anInputStream1024;
            this.aByteArray1022 = new byte[this.anInt1023];
            (this.aThread1021 = new Thread(this)).setDaemon(true);
            this.aThread1021.start();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ik.<init>(" + ((anInputStream1024 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
}
