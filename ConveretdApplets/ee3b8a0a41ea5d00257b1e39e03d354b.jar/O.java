import java.net.URLConnection;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.EOFException;
import java.awt.Component;
import java.io.InputStream;
import java.net.URL;
import java.io.DataInputStream;
import I.I;
import java.io.PipedOutputStream;
import java.io.PipedInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class O implements Runnable
{
    private static String D;
    private static long append;
    int I;
    long Z;
    private static int[] close;
    private static boolean currentThread;
    private Thread currentTimeMillis;
    private ztmPlayer equals;
    private volatile boolean equalsIgnoreCase;
    volatile int C;
    volatile PipedInputStream B;
    volatile PipedInputStream F;
    private PipedOutputStream getHeaderField;
    private PipedOutputStream getInputStream;
    private byte[] getMessage;
    long J;
    private boolean getProtocol;
    private byte indexOf;
    private int isAlive;
    
    O(final ztmPlayer equals) {
        this.getMessage = new byte[1024];
        this.equals = equals;
        (this.currentTimeMillis = new Thread(this, O.D)).start();
    }
    
    public final void run() {
        String s = "";
        try {
            final PipedOutputStream getHeaderField = new PipedOutputStream();
            this.getHeaderField = getHeaderField;
            this.B = new PipedInputStream(getHeaderField);
            final PipedOutputStream getInputStream = new PipedOutputStream();
            this.getInputStream = getInputStream;
            this.F = new PipedInputStream(getInputStream);
            if (!this.equals.e) {
                this.equals.q = this.equals.D;
                this.C = 1;
                s = I.I.I(651);
                InputStream inputStream = this.close(this.equals.D, false, -1L);
                final DataInputStream dataInputStream = new DataInputStream(inputStream);
                final byte[] array = new byte[9];
                dataInputStream.readFully(array);
                final int equals = this.equals(D(array, 0));
                final int n = 0xFF & array[4];
                this.equals.m = 1000L * append(array, 5);
                byte[] array2 = new byte[equals * n + 4];
                dataInputStream.readFully(array2);
                final long[] array3 = new long[n];
                final long[] array4 = new long[n];
                final long[] array5 = new long[n];
                final int[] array6 = new int[n];
                final int[] array7 = new int[n];
                final double[] array8 = new double[n];
                final long[] array9 = new long[n];
                for (int i = 0; i < n; ++i) {
                    array5[i] = (array3[i] = append(array2, equals * i));
                    final long[] array10 = array5;
                    final int n2 = i;
                    array10[n2] += (array4[i] = append(array2, 4 + equals * i));
                    array6[i] = D(array2, 8 + equals * i);
                    array7[i] = D(array2, 10 + equals * i);
                    array8[i] = 1.0E-6 * append(array2, 12 + equals * i);
                    if (this.equals.w) {
                        array9[i] = append(array2, 16 + equals * i);
                    }
                }
                int n3 = 0;
                final String externalForm = this.equals.D.toExternalForm();
                final boolean equals2 = externalForm.substring(externalForm.length() - 11).equals(I.I.I(683));
                if (equals2) {
                    dataInputStream.close();
                    if (1 < n) {
                        int n4 = 0;
                        if (-1L == O.append) {
                            this.C = 2;
                            final C c = new C(externalForm.substring(0, externalForm.length() - 11) + I.I.I(695));
                            synchronized (c) {
                                while (c.I()) {
                                    if (0 == n4 % 25) {
                                        this.equals.I((0 == n4 % 50) ? I.I.I(704) : null);
                                    }
                                    else {
                                        c.wait(20L);
                                    }
                                    ++n4;
                                }
                            }
                            this.equals.I((String)null);
                            O.append = c.Z() << 3;
                        }
                        for (int j = n3 = 0; j < n; ++j) {
                            if (array5[j] < array5[n3]) {
                                n3 = j;
                            }
                        }
                        for (int k = 0; k < n; ++k) {
                            if (O.append > array5[k] && array5[k] > array5[n3]) {
                                n3 = k;
                            }
                        }
                    }
                }
                this.equals.N = array6[n3];
                this.equals.O = array7[n3];
                this.equals.n = array8[n3];
                this.equals.o = array3[n3] >> 3;
                this.equals.p = array4[n3] >> 3;
                if (0.0 >= this.equals.p) {
                    final ztmPlayer equals3 = this.equals;
                    ztmPlayer.C = 0;
                }
                this.equals.h = array9[n3];
                O.close = new int[] { 1, 3, 4, 1, 5, 6, 1, 4 };
                this.C = 3;
                synchronized (this) {
                    this.wait();
                }
                if (equals2) {
                    this.C = 4;
                    s = I.I.I(727);
                    this.equals.q = new URL(externalForm.substring(0, externalForm.length() - 11) + I.I.I(757) + n3 + I.I.I(759));
                    inputStream = this.close(this.equals.q, false, -1L);
                    final DataInputStream dataInputStream2 = new DataInputStream(inputStream);
                    array2 = new byte[this.equals.w ? 37 : 29];
                    dataInputStream2.readFully(array2);
                    this.equals(D(array2, 0));
                }
                if (this.equals.w) {
                    this.equals.v = (long)(append(array2, equals2 ? 33 : 24) * 1.5);
                }
                s = null;
                this.equals.l = new DataInputStream(new W(inputStream, O.close));
            }
            else {
                this.currentThread(false);
            }
            synchronized (this) {
                this.C = 5;
                this.notifyAll();
            }
            long n5 = 0L;
            final byte[] array11 = new byte[4];
            if (!this.equals.e) {
                final ztmPlayer equals4 = this.equals;
                equals4.h -= 37L;
                if (this.equals.w) {
                    this.equals.l.read(array11);
                    if (808671318L != append(array11, 0)) {
                        T.I(I.I.I(766));
                        this.equals.remove(this.equals.z);
                        this.equals.K = false;
                        this.equals.S = true;
                    }
                }
                this.equals.e = true;
                int n6 = 0;
                while (true) {
                    final int n7 = n6;
                    final ztmPlayer equals5 = this.equals;
                    if (n7 >= ((ztmPlayer.C != 0) ? 2 : 1)) {
                        break;
                    }
                    this.equals.l.readFully(array11);
                    final byte b = array11[3];
                    array11[3] = 0;
                    final int n8 = (int)append(array11, 0);
                    if (b == 0) {
                        this.equals.r = new byte[n8];
                        this.equals.l.readFully(this.equals.r);
                        this.getHeaderField.write(this.equals.r);
                        final ztmPlayer equals6 = this.equals;
                        equals6.h -= this.equals.r.length + 4;
                    }
                    else if (b != 0) {
                        this.equals.t = new byte[n8];
                        this.equals.l.readFully(this.equals.t);
                        this.getInputStream.write(this.equals.t);
                        final ztmPlayer equals7 = this.equals;
                        equals7.h -= this.equals.t.length + 4;
                    }
                    ++n6;
                }
            }
            else {
                final ztmPlayer equals8 = this.equals;
                if (ztmPlayer.C != 0 && !this.equalsIgnoreCase) {
                    this.getInputStream.write(this.equals.t);
                }
            }
            while (!this.equalsIgnoreCase) {
                byte indexOf;
                int isAlive;
                if (!this.getProtocol) {
                    try {
                        array11[0] = this.equals.l.readByte();
                    }
                    catch (EOFException ex2) {
                        break;
                    }
                    this.equals.l.readFully(array11, 1, 3);
                    indexOf = array11[3];
                    array11[3] = 0;
                    isAlive = (int)append(array11, 0);
                }
                else {
                    indexOf = this.indexOf;
                    isAlive = this.isAlive;
                    this.getProtocol = false;
                }
                PipedOutputStream pipedOutputStream = null;
                Label_1648: {
                    if (indexOf != 0) {
                        if (indexOf != 0) {
                            final ztmPlayer equals9 = this.equals;
                            if (ztmPlayer.C != 0) {
                                pipedOutputStream = this.getInputStream;
                                n5 += isAlive;
                                break Label_1648;
                            }
                        }
                        if (3 == indexOf) {
                            final ztmPlayer equals10 = this.equals;
                            if (ztmPlayer.C != 0 && 4 == isAlive) {
                                this.equals.l.readFully(array11, 0, 4);
                                if (null != this.equals.U) {
                                    this.equals.U.I(n5, (short)D(array11, 0) - (short)D(array11, 2));
                                    continue;
                                }
                                continue;
                            }
                        }
                        final ztmPlayer equals11 = this.equals;
                        equals11.k += this.currentTimeMillis(isAlive);
                        continue;
                    }
                    pipedOutputStream = this.getHeaderField;
                }
                final ztmPlayer equals12 = this.equals;
                equals12.k += 4L;
                while (!this.equalsIgnoreCase && 0 < isAlive && !this.getProtocol) {
                    final int n9 = (isAlive > 1024) ? 1024 : isAlive;
                    this.equals.l.readFully(this.getMessage, 0, n9);
                    isAlive -= n9;
                    if (!this.equalsIgnoreCase && !this.getProtocol) {
                        pipedOutputStream.write(this.getMessage, 0, n9);
                        final ztmPlayer equals13 = this.equals;
                        equals13.k += n9;
                    }
                }
            }
        }
        catch (InterruptedException ex3) {}
        catch (IOException ex) {
            if (!this.equalsIgnoreCase) {
                this.C = -1;
                if (null != s) {
                    T.I(s + I.I.I(785) + ex.getMessage());
                }
            }
        }
        synchronized (this) {
            if (this.equalsIgnoreCase) {
                this.equalsIgnoreCase = false;
                this.notify();
            }
            else {
                this.currentTimeMillis = null;
                this.I();
            }
        }
    }
    
    final synchronized void I() {
        this.equalsIgnoreCase = true;
        if (null != this.currentTimeMillis && this.currentTimeMillis.isAlive()) {
            try {
                if (null != this.F) {
                    this.F.close();
                    this.getInputStream.close();
                }
            }
            catch (IOException ex) {}
            try {
                if (null != this.B) {
                    this.B.close();
                    this.getHeaderField.close();
                }
            }
            catch (IOException ex2) {}
            try {
                while (this.equalsIgnoreCase) {
                    this.wait();
                }
                this.currentTimeMillis.join();
            }
            catch (InterruptedException ex3) {}
        }
        this.equals = null;
        this.currentTimeMillis = null;
    }
    
    private static final int D(final byte[] array, final int n) {
        return (array[n] & 0xFF) + ((array[1 + n] & 0xFF) << 8);
    }
    
    private static final long append(final byte[] array, final int n) {
        return (array[n] & 0xFF) + ((array[1 + n] & 0xFF) << 8) + ((array[2 + n] & 0xFF) << 16) + ((array[3 + n] & 0xFF) << 24);
    }
    
    private InputStream close(final URL url, final boolean useCaches, final long n) {
        FileNotFoundException ex = null;
        final URLConnection openConnection = url.openConnection();
        openConnection.setUseCaches(useCaches);
        if (0L < n) {
            openConnection.setRequestProperty(I.I.I(866), I.I.I(872) + n + I.I.I(879));
        }
        int n2 = 0;
        while (!this.equalsIgnoreCase && n2 < 3) {
            if (0 < n2) {
                try {
                    Thread.currentThread();
                    Thread.sleep(500L);
                }
                catch (InterruptedException ex3) {}
            }
            try {
                final long currentTimeMillis = System.currentTimeMillis();
                final InputStream inputStream = openConnection.getInputStream();
                this.equals.u = (int)(System.currentTimeMillis() - currentTimeMillis);
                if (0L < n) {
                    O.currentThread = (-1 != openConnection.getHeaderField(0).indexOf(I.I.I(881)));
                }
                return inputStream;
            }
            catch (FileNotFoundException ex2) {
                ex = ex2;
                ++n2;
                continue;
            }
            break;
        }
        throw ex;
    }
    
    private final void currentThread(final boolean b) {
        this.getProtocol = true;
        final long n = (this.equals.g < this.equals.v) ? 0L : (this.equals.g - this.equals.v);
        final boolean b2;
        final long n2 = ((b2 = (((n > 0L) ? 1 : 0) != 0)) ? (n - 1L) : 0L) + 37L;
        final ztmPlayer equals = this.equals;
        final long n3 = n2 + ((ztmPlayer.C != 0) ? (this.equals.t.length + 4) : 0) + this.equals.r.length + 4L;
        int n4 = 0;
        final boolean equalsIgnoreCase = this.equals.q.getProtocol().equalsIgnoreCase(I.I.I(788));
        while (!this.equalsIgnoreCase) {
            final ztmPlayer equals2 = this.equals;
            if (ztmPlayer.Z || O.currentThread || equalsIgnoreCase) {
                if (this.equals.k > this.equals.g) {
                    this.equals.l.close();
                    this.equals.l = new DataInputStream(new W(this.close(this.equals.q, b, -1L), O.close));
                    this.equals.k = this.currentTimeMillis(n3) - (n3 - this.equals.g);
                    break;
                }
                final ztmPlayer equals3 = this.equals;
                equals3.k += this.currentTimeMillis((int)(this.equals.g - this.equals.k));
                break;
            }
            else if (n4 != 0 || this.equals.k > this.equals.g) {
                this.equals.l.close();
                this.equals.l = new DataInputStream(new W(this.close(this.equals.q, b, n3), O.close));
                if (O.currentThread) {
                    continue;
                }
                this.equals.k = this.equals.g;
                if (b2) {
                    this.equals.l.read();
                    break;
                }
                break;
            }
            else {
                if (O.append != -1L && (this.equals.g - this.equals.k << 3) * 1000L / O.append <= this.equals.u) {
                    final ztmPlayer equals4 = this.equals;
                    equals4.k += this.currentTimeMillis((int)(this.equals.g - this.equals.k));
                    break;
                }
                n4 = 1;
            }
        }
        final byte[] array = new byte[15];
        final byte[] array2 = new byte[array.length];
        long n5;
        int n6;
        int n7;
        int read;
        ztmPlayer equals5;
        ztmPlayer equals6;
        long n8;
        for (n5 = 0L, n6 = 0, n7 = 0; (0x1B600L != (n5 & 0xFFFFFFFFC0L) || n7 < array.length) && !this.equalsIgnoreCase; n5 = (n5 << 8) + read, array[n6] = (byte)read, n6 = (n6 + 1) % array.length, ++n7) {
            if (-1 == (read = this.equals.l.read())) {
                equals5 = this.equals;
                equals6 = this.equals;
                n8 = 0L;
                equals6.g = n8;
                equals5.k = n8;
                this.currentThread(b);
                return;
            }
        }
        final ztmPlayer equals7 = this.equals;
        equals7.k += n7;
        for (int i = 0; i < array.length; ++i) {
            array2[i] = array[(n6 + i) % array.length];
        }
        this.J = append(array2, 2) * 1000L;
        this.Z = this.equals.g / this.equals.h * this.equals.m - this.J;
        this.I = (short)D(array2, 0) - ((this.Z > 0L) ? ((int)(this.Z / 1000L)) : 0);
        this.J += ((this.Z > 0L) ? this.Z : 0L);
        this.indexOf = array2[9];
        array2[9] = 0;
        this.isAlive = (int)append(array2, 6) - 5;
        if (!this.equalsIgnoreCase) {
            this.getHeaderField.write(this.equals.r);
            this.getHeaderField.write(array2, 10, 5);
        }
    }
    
    private final long currentTimeMillis(final long n) {
        long n2 = 0L;
        try {
            while (n2 < n && !this.equalsIgnoreCase) {
                this.equals.l.readByte();
                ++n2;
            }
        }
        catch (IOException ex) {}
        return n2;
    }
    
    private int equals(final int n) {
        int n2 = 16;
        switch (n) {
            case 5: {
                this.equals.w = false;
                break;
            }
            case 6: {
                this.equals.w = true;
                n2 = 24;
                break;
            }
            default: {
                throw new IOException(I.I.I(826));
            }
        }
        return n2;
    }
    
    static {
        O.D = I.I(886);
        O.append = -1L;
    }
}
