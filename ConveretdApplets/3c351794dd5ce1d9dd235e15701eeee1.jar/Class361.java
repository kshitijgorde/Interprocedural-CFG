import java.io.EOFException;
import java.lang.reflect.InvocationTargetException;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.io.OutputStream;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class361 implements Runnable
{
    private boolean aBoolean3078;
    private Class143 aClass143_3079;
    private int anInt3080;
    private Class88 aClass88_3081;
    private OutputStream anOutputStream3082;
    private Socket aSocket3083;
    private InputStream anInputStream3084;
    private int anInt3085;
    private byte[] aByteArray3086;
    private int anInt3087;
    private boolean aBoolean3088;
    static int anInt3089;
    
    static final int method3914(final int n) {
        try {
            if (n != -5133) {
                Class361.anInt3089 = -25;
            }
            final int method736 = Class45.aClass75_381.method736((byte)(-10));
            if (~method736 > ~(Class98_Sub10_Sub1.aClass75Array5542.length - 1)) {
                Class45.aClass75_381 = Class98_Sub10_Sub1.aClass75Array5542[1 + method736];
            }
            return 100;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vt.C(" + n + ')');
        }
    }
    
    final int method3915(final int n) throws IOException {
        try {
            if (this.aBoolean3088) {
                return 0;
            }
            if (n < 70) {
                this.anInputStream3084 = null;
            }
            return this.anInputStream3084.available();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vt.B(" + n + ')');
        }
    }
    
    final void method3916(final boolean b) throws IOException {
        try {
            if (!b) {
                this.anInt3080 = 96;
            }
            if (!this.aBoolean3088 && this.aBoolean3078) {
                this.aBoolean3078 = false;
                throw new IOException();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vt.H(" + b + ')');
        }
    }
    
    static final int method3917(final int n) {
        try {
            if (n != -13) {
                return -77;
            }
            return Class98_Sub46_Sub15.method1610(false, (byte)90);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vt.E(" + n + ')');
        }
    }
    
    final int method3918(final int n) throws IOException {
        try {
            if (this.aBoolean3088) {
                return 0;
            }
            if (n != -14234) {
                return 33;
            }
            return this.anInputStream3084.read();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vt.D(" + n + ')');
        }
    }
    
    static final void method3919(final byte b, final Class98_Sub22_Sub1 class98_Sub22_Sub1) {
        try {
            final Class98_Sub19 class98_Sub19 = (Class98_Sub19)Class186.aClass148_3428.method2418(32);
            if (class98_Sub19 != null) {
                boolean b2 = false;
                for (int n = 0; ~n > ~class98_Sub19.anInt3960; ++n) {
                    if (class98_Sub19.aClass143Array3962[n] != null) {
                        if (class98_Sub19.aClass143Array3962[n].anInt1163 == 2) {
                            class98_Sub19.anIntArray3957[n] = -5;
                        }
                        if (~class98_Sub19.aClass143Array3962[n].anInt1163 == -1) {
                            b2 = true;
                        }
                    }
                    if (class98_Sub19.aClass143Array3961[n] != null) {
                        if (class98_Sub19.aClass143Array3961[n].anInt1163 == 2) {
                            class98_Sub19.anIntArray3957[n] = -6;
                        }
                        if (~class98_Sub19.aClass143Array3961[n].anInt1163 == -1) {
                            b2 = true;
                        }
                    }
                }
                if (!b2) {
                    final int anInt3991 = class98_Sub22_Sub1.anInt3991;
                    class98_Sub22_Sub1.writeInt(b + 1571862949, class98_Sub19.anInt3954);
                    if (b != -61) {
                        Class361.anInt3089 = 82;
                    }
                    for (int n2 = 0; class98_Sub19.anInt3960 > n2; ++n2) {
                        if (~class98_Sub19.anIntArray3957[n2] != -1) {
                            class98_Sub22_Sub1.method1194(class98_Sub19.anIntArray3957[n2], b ^ 0x9);
                        }
                        else {
                            try {
                                final int n3 = class98_Sub19.anIntArray3953[n2];
                                if (n3 == 0) {
                                    final int int1 = ((Field)class98_Sub19.aClass143Array3962[n2].anObject1162).getInt(null);
                                    class98_Sub22_Sub1.method1194(0, -99);
                                    class98_Sub22_Sub1.writeInt(b ^ 0xA24F46AB, int1);
                                }
                                else if (~n3 == 0xFFFFFFFE) {
                                    ((Field)class98_Sub19.aClass143Array3962[n2].anObject1162).setInt(null, class98_Sub19.anIntArray3959[n2]);
                                    class98_Sub22_Sub1.method1194(0, -48);
                                }
                                else if (n3 == 2) {
                                    final int modifiers = ((Field)class98_Sub19.aClass143Array3962[n2].anObject1162).getModifiers();
                                    class98_Sub22_Sub1.method1194(0, -52);
                                    class98_Sub22_Sub1.writeInt(1571862888, modifiers);
                                }
                                if (~n3 == 0xFFFFFFFC) {
                                    final Method method = (Method)class98_Sub19.aClass143Array3961[n2].anObject1162;
                                    final byte[][] array = class98_Sub19.aByteArrayArrayArray3958[n2];
                                    final Object[] array2 = new Object[array.length];
                                    for (int n4 = 0; ~array.length < ~n4; ++n4) {
                                        array2[n4] = new ObjectInputStream(new ByteArrayInputStream(array[n4])).readObject();
                                    }
                                    final Object invoke = method.invoke(null, array2);
                                    if (invoke != null) {
                                        if (!(invoke instanceof Number)) {
                                            if (invoke instanceof String) {
                                                class98_Sub22_Sub1.method1194(2, -124);
                                                class98_Sub22_Sub1.method1188((String)invoke, (byte)113);
                                            }
                                            else {
                                                class98_Sub22_Sub1.method1194(4, 101);
                                            }
                                        }
                                        else {
                                            class98_Sub22_Sub1.method1194(1, b - 53);
                                            class98_Sub22_Sub1.method1221(b - 65, ((Number)invoke).longValue());
                                        }
                                    }
                                    else {
                                        class98_Sub22_Sub1.method1194(0, 115);
                                    }
                                }
                                else if (n3 == 4) {
                                    final int modifiers2 = ((Method)class98_Sub19.aClass143Array3961[n2].anObject1162).getModifiers();
                                    class98_Sub22_Sub1.method1194(0, 48);
                                    class98_Sub22_Sub1.writeInt(1571862888, modifiers2);
                                }
                            }
                            catch (ClassNotFoundException ex2) {
                                class98_Sub22_Sub1.method1194(-10, b - 20);
                            }
                            catch (InvalidClassException ex3) {
                                class98_Sub22_Sub1.method1194(-11, 85);
                            }
                            catch (StreamCorruptedException ex4) {
                                class98_Sub22_Sub1.method1194(-12, -87);
                            }
                            catch (OptionalDataException ex5) {
                                class98_Sub22_Sub1.method1194(-13, 79);
                            }
                            catch (IllegalAccessException ex6) {
                                class98_Sub22_Sub1.method1194(-14, b ^ 0xFFFFFF8D);
                            }
                            catch (IllegalArgumentException ex7) {
                                class98_Sub22_Sub1.method1194(-15, b ^ 0xFFFFFF89);
                            }
                            catch (InvocationTargetException ex8) {
                                class98_Sub22_Sub1.method1194(-16, 125);
                            }
                            catch (SecurityException ex9) {
                                class98_Sub22_Sub1.method1194(-17, 43);
                            }
                            catch (IOException ex10) {
                                class98_Sub22_Sub1.method1194(-18, 48);
                            }
                            catch (NullPointerException ex11) {
                                class98_Sub22_Sub1.method1194(-19, 87);
                            }
                            catch (Exception ex12) {
                                class98_Sub22_Sub1.method1194(-20, 70);
                            }
                            catch (Throwable t) {
                                class98_Sub22_Sub1.method1194(-21, -120);
                            }
                        }
                    }
                    class98_Sub22_Sub1.method1196(anInt3991, (byte)(-32));
                    class98_Sub19.method942(95);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vt.G(" + b + ',' + ((class98_Sub22_Sub1 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method3920(final byte b, final int n, final int n2, final byte[] array) throws IOException {
        try {
            if (!this.aBoolean3088) {
                if (this.aBoolean3078) {
                    this.aBoolean3078 = false;
                    throw new IOException();
                }
                if (this.aByteArray3086 == null) {
                    this.aByteArray3086 = new byte[this.anInt3080];
                }
                synchronized (this) {
                    for (int n3 = 0; ~n2 < ~n3; ++n3) {
                        this.aByteArray3086[this.anInt3085] = array[n3 + n];
                        this.anInt3085 = (1 + this.anInt3085) % this.anInt3080;
                        if (~this.anInt3085 == ~((this.anInt3087 - (-this.anInt3080 + 100)) % this.anInt3080)) {
                            throw new IOException();
                        }
                    }
                    if (b != 77) {
                        this.anInt3087 = 85;
                    }
                    if (this.aClass143_3079 == null) {
                        this.aClass143_3079 = this.aClass88_3081.method858(3, this, 1);
                    }
                    this.notifyAll();
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vt.F(" + b + ',' + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method3921(int n, final boolean b, int i, final byte[] array) throws IOException {
        try {
            if (!b) {
                this.finalize();
            }
            if (!this.aBoolean3088) {
                while (i > 0) {
                    final int read = this.anInputStream3084.read(array, n, i);
                    if (~read >= -1) {
                        throw new EOFException();
                    }
                    n += read;
                    i -= read;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vt.A(" + n + ',' + b + ',' + i + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method3922(final int n) {
        try {
            if (!this.aBoolean3088) {
                this.anInputStream3084 = new InputStream_Sub1();
                this.anOutputStream3082 = new OutputStream_Sub1();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vt.I(" + n + ')');
        }
    }
    
    @Override
    protected final void finalize() {
        try {
            this.method3923(-29789);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vt.finalize()");
        }
    }
    
    final void method3923(final int n) {
        try {
            if (!this.aBoolean3088) {
                synchronized (this) {
                    this.aBoolean3088 = true;
                    if (n != -29789) {
                        this.method3922(-126);
                    }
                    this.notifyAll();
                }
                if (this.aClass143_3079 != null) {
                    while (~this.aClass143_3079.anInt1163 == -1) {
                        Class246_Sub7.method3131(0, 1L);
                    }
                    if (~this.aClass143_3079.anInt1163 == 0xFFFFFFFE) {
                        try {
                            ((Thread)this.aClass143_3079.anObject1162).join();
                        }
                        catch (InterruptedException ex2) {}
                    }
                }
                this.aClass143_3079 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vt.J(" + n + ')');
        }
    }
    
    @Override
    public final void run() {
        try {
            try {
                while (true) {
                    final int anInt3087;
                    int n;
                    synchronized (this) {
                        if (~this.anInt3085 == ~this.anInt3087) {
                            if (this.aBoolean3088) {
                                break;
                            }
                            try {
                                this.wait();
                            }
                            catch (InterruptedException ex3) {}
                        }
                        anInt3087 = this.anInt3087;
                        if (~this.anInt3085 > ~this.anInt3087) {
                            n = -this.anInt3087 + this.anInt3080;
                        }
                        else {
                            n = this.anInt3085 - this.anInt3087;
                        }
                    }
                    if (~n < -1) {
                        try {
                            this.anOutputStream3082.write(this.aByteArray3086, anInt3087, n);
                        }
                        catch (IOException ex4) {
                            this.aBoolean3078 = true;
                        }
                        this.anInt3087 = (n + this.anInt3087) % this.anInt3080;
                        try {
                            if (~this.anInt3085 != ~this.anInt3087) {
                                continue;
                            }
                            this.anOutputStream3082.flush();
                        }
                        catch (IOException ex5) {
                            this.aBoolean3078 = true;
                        }
                    }
                }
                try {
                    if (this.anInputStream3084 != null) {
                        this.anInputStream3084.close();
                    }
                    if (this.anOutputStream3082 != null) {
                        this.anOutputStream3082.close();
                    }
                    if (this.aSocket3083 != null) {
                        this.aSocket3083.close();
                    }
                }
                catch (IOException ex6) {}
                this.aByteArray3086 = null;
            }
            catch (Exception ex) {
                Class305_Sub1.method3585(ex, -128, null);
            }
        }
        catch (RuntimeException ex2) {
            throw Class64_Sub27.method667(ex2, "vt.run()");
        }
    }
    
    Class361(final Socket aSocket3083, final Class88 aClass88_3081, final int anInt3080) throws IOException {
        this.anInt3085 = 0;
        this.aBoolean3078 = false;
        this.aBoolean3088 = false;
        this.anInt3087 = 0;
        try {
            this.aSocket3083 = aSocket3083;
            this.aClass88_3081 = aClass88_3081;
            this.aSocket3083.setSoTimeout(30000);
            this.aSocket3083.setTcpNoDelay(true);
            this.anInputStream3084 = this.aSocket3083.getInputStream();
            this.anOutputStream3082 = this.aSocket3083.getOutputStream();
            this.anInt3080 = anInt3080;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vt.<init>(" + ((aSocket3083 != null) ? "{...}" : "null") + ',' + ((aClass88_3081 != null) ? "{...}" : "null") + ',' + anInt3080 + ')');
        }
    }
    
    static {
        Class361.anInt3089 = 0;
    }
}
