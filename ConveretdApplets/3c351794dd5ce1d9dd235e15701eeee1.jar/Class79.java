import java.lang.reflect.Method;
import java.io.File;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class79
{
    private int anInt598;
    private Class377 aClass377_599;
    static Class85 aClass85_600;
    static int anInt601;
    static boolean aBoolean602;
    static int[] anIntArray603;
    static int[] anIntArray604;
    private int anInt605;
    private Class215 aClass215_606;
    static Class aClass607;
    
    final int method793(final int n) {
        try {
            if (n <= 6) {
                Class79.anIntArray603 = null;
            }
            return this.anInt598;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fc.H(" + n + ')');
        }
    }
    
    final void method794(final int n) {
        try {
            this.aClass215_606.method2786(16711680);
            this.aClass377_599.method3994(-99);
            if (n <= 0) {
                this.method796(null, 41);
            }
            this.anInt605 = this.anInt598;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fc.L(" + n + ')');
        }
    }
    
    static final boolean method795(final int n, final Class clazz, final String s) {
        try {
            final Class clazz2 = Class10.aHashtable118.get(s);
            if (clazz2 != null) {
                return clazz2.getClassLoader() == clazz.getClassLoader();
            }
            File file = null;
            if (file == null) {
                file = Class124.aHashtable1015.get(s);
            }
            if (file != null) {
                try {
                    file = new File(file.getCanonicalPath());
                    final Class<?> forName = Class.forName("java.lang.Runtime");
                    final Method declaredMethod = Class.forName("java.lang.reflect.AccessibleObject").getDeclaredMethod("setAccessible", Boolean.TYPE);
                    final Method declaredMethod2 = forName.getDeclaredMethod("load0", Class.forName("java.lang.Class"), Class.forName("java.lang.String"));
                    declaredMethod.invoke(declaredMethod2, Boolean.TRUE);
                    declaredMethod2.invoke(Runtime.getRuntime(), clazz, file.getPath());
                    declaredMethod.invoke(declaredMethod2, Boolean.FALSE);
                    Class10.aHashtable118.put(s, clazz);
                    return true;
                }
                catch (NoSuchMethodException ex2) {
                    System.load(file.getPath());
                    Class10.aHashtable118.put(s, (Class79.aClass607 != null) ? Class79.aClass607 : (Class79.aClass607 = method809("Class166")));
                    return true;
                }
                catch (Throwable t) {}
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fc.O(" + n + ',' + ((clazz != null) ? "{...}" : "null") + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method796(final Class98_Sub46_Sub2 class98_Sub46_Sub2, final int n) {
        try {
            if (class98_Sub46_Sub2 != null) {
                class98_Sub46_Sub2.method942(56);
                class98_Sub46_Sub2.method1524((byte)(-90));
                this.anInt605 += class98_Sub46_Sub2.anInt5950;
            }
            if (n != 7) {
                this.anInt598 = 122;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fc.G(" + ((class98_Sub46_Sub2 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final int method797(final int n) {
        try {
            int n2 = 0;
            if (n > -117) {
                this.method799(33);
            }
            for (Class98_Sub46_Sub2 class98_Sub46_Sub2 = (Class98_Sub46_Sub2)this.aClass215_606.method2792(-1); class98_Sub46_Sub2 != null; class98_Sub46_Sub2 = (Class98_Sub46_Sub2)this.aClass215_606.method2787(0)) {
                if (!class98_Sub46_Sub2.method1536(127)) {
                    ++n2;
                }
            }
            return n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fc.I(" + n + ')');
        }
    }
    
    public static void method798(final byte b) {
        try {
            Class79.aClass85_600 = null;
            Class79.anIntArray604 = null;
            Class79.anIntArray603 = null;
            if (b != 22) {
                method801((byte)(-119), -90, 83, 33);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fc.B(" + b + ')');
        }
    }
    
    final int method799(final int n) {
        try {
            if (n != 1551398789) {
                Class79.anIntArray604 = null;
            }
            return this.anInt605;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fc.E(" + n + ')');
        }
    }
    
    Class79(final int n) {
        this(n, n);
    }
    
    final void method800(final byte b, final int n) {
        try {
            if (Class246_Sub3_Sub3.aClass206_6154 != null) {
                for (Class98_Sub46_Sub2 class98_Sub46_Sub2 = (Class98_Sub46_Sub2)this.aClass215_606.method2792(-1); class98_Sub46_Sub2 != null; class98_Sub46_Sub2 = (Class98_Sub46_Sub2)this.aClass215_606.method2787(0)) {
                    if (!class98_Sub46_Sub2.method1536(124)) {
                        final long n2 = n;
                        final Class98_Sub46_Sub2 class98_Sub46_Sub3 = class98_Sub46_Sub2;
                        final long aLong4259 = class98_Sub46_Sub3.aLong4259 + 1L;
                        class98_Sub46_Sub3.aLong4259 = aLong4259;
                        if (n2 < aLong4259) {
                            final Class98_Sub46_Sub2 method2726 = Class246_Sub3_Sub3.aClass206_6154.method2726(0, class98_Sub46_Sub2);
                            this.aClass377_599.method3996(method2726, class98_Sub46_Sub2.aLong832, -1);
                            Class101.method1697(class98_Sub46_Sub2, (byte)37, method2726);
                            class98_Sub46_Sub2.method942(b + 43);
                            class98_Sub46_Sub2.method1524((byte)(-90));
                        }
                    }
                    else if (class98_Sub46_Sub2.method1533(true) == null) {
                        class98_Sub46_Sub2.method942(98);
                        class98_Sub46_Sub2.method1524((byte)(-90));
                        this.anInt605 += class98_Sub46_Sub2.anInt5950;
                    }
                }
            }
            if (b != 62) {
                this.anInt605 = -25;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fc.P(" + b + ',' + n + ')');
        }
    }
    
    static final int method801(final byte b, int n, final int n2, final int n3) {
        try {
            if (b != -11) {
                return 96;
            }
            if (~n3 < -244) {
                n >>= 4;
            }
            else if (n3 <= 217) {
                if (n3 > 192) {
                    n >>= 2;
                }
                else if (~n3 < -180) {
                    n >>= 1;
                }
            }
            else {
                n >>= 3;
            }
            return (n3 >> -1942171039) + (n >> 1551398789 << 908165991) + ((n2 >> 466286402 & 0x3F) << 1714760906);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fc.A(" + b + ',' + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    final Object method802(final int n, final long n2) {
        try {
            if (n > -118) {
                this.aClass377_599 = null;
            }
            final Class98_Sub46_Sub2 class98_Sub46_Sub2 = (Class98_Sub46_Sub2)this.aClass377_599.method3990(n2, -1);
            if (class98_Sub46_Sub2 == null) {
                return null;
            }
            final Object method1533 = class98_Sub46_Sub2.method1533(true);
            if (method1533 == null) {
                class98_Sub46_Sub2.method942(100);
                class98_Sub46_Sub2.method1524((byte)(-90));
                this.anInt605 += class98_Sub46_Sub2.anInt5950;
                return null;
            }
            if (class98_Sub46_Sub2.method1536(119)) {
                final Class98_Sub46_Sub2_Sub2 class98_Sub46_Sub2_Sub2 = new Class98_Sub46_Sub2_Sub2(method1533, class98_Sub46_Sub2.anInt5950);
                this.aClass377_599.method3996(class98_Sub46_Sub2_Sub2, class98_Sub46_Sub2.aLong832, -1);
                this.aClass215_606.method2785(class98_Sub46_Sub2_Sub2, -55);
                class98_Sub46_Sub2_Sub2.aLong4259 = 0L;
                class98_Sub46_Sub2.method942(123);
                class98_Sub46_Sub2.method1524((byte)(-90));
                if (!client.aBoolean3553) {
                    return method1533;
                }
            }
            this.aClass215_606.method2785(class98_Sub46_Sub2, -50);
            class98_Sub46_Sub2.aLong4259 = 0L;
            return method1533;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fc.F(" + n + ',' + n2 + ')');
        }
    }
    
    final Object method803(final boolean b) {
        try {
            if (b) {
                this.anInt605 = -74;
            }
            Class98_Sub46_Sub2 class98_Sub46_Sub2 = (Class98_Sub46_Sub2)this.aClass377_599.method3998(116);
            while (class98_Sub46_Sub2 != null) {
                final Object method1533 = class98_Sub46_Sub2.method1533(true);
                if (method1533 != null) {
                    return method1533;
                }
                final Class98_Sub46_Sub2 class98_Sub46_Sub3 = class98_Sub46_Sub2;
                class98_Sub46_Sub2 = (Class98_Sub46_Sub2)this.aClass377_599.method3995(-1);
                class98_Sub46_Sub3.method942(73);
                class98_Sub46_Sub3.method1524((byte)(-90));
                this.anInt605 += class98_Sub46_Sub3.anInt5950;
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fc.D(" + b + ')');
        }
    }
    
    final Object method804(final boolean b) {
        try {
            Class98_Sub46_Sub2 class98_Sub46_Sub2 = (Class98_Sub46_Sub2)this.aClass377_599.method3995(-1);
            if (b) {
                return null;
            }
            while (class98_Sub46_Sub2 != null) {
                final Object method1533 = class98_Sub46_Sub2.method1533(true);
                if (method1533 != null) {
                    return method1533;
                }
                final Class98_Sub46_Sub2 class98_Sub46_Sub3 = class98_Sub46_Sub2;
                class98_Sub46_Sub2 = (Class98_Sub46_Sub2)this.aClass377_599.method3995(-1);
                class98_Sub46_Sub3.method942(74);
                class98_Sub46_Sub3.method1524((byte)(-90));
                this.anInt605 += class98_Sub46_Sub3.anInt5950;
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fc.C(" + b + ')');
        }
    }
    
    final void method805(final long n, final Object o, final byte b) {
        try {
            this.method807(13436, n, 1, o);
            if (b != -80) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fc.M(" + n + ',' + ((o != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final void method806(final byte b) {
        try {
            for (Class98_Sub46_Sub2 class98_Sub46_Sub2 = (Class98_Sub46_Sub2)this.aClass215_606.method2792(-1); class98_Sub46_Sub2 != null; class98_Sub46_Sub2 = (Class98_Sub46_Sub2)this.aClass215_606.method2787(0)) {
                if (class98_Sub46_Sub2.method1536(122)) {
                    class98_Sub46_Sub2.method942(55);
                    class98_Sub46_Sub2.method1524((byte)(-90));
                    this.anInt605 += class98_Sub46_Sub2.anInt5950;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fc.K(" + b + ')');
        }
    }
    
    final void method807(final int n, final long n2, final int n3, final Object o) {
        try {
            if (~n3 < ~this.anInt598) {
                throw new IllegalStateException("s>cs");
            }
            this.method808(n2, n + 67095427);
            this.anInt605 -= n3;
            while (this.anInt605 < 0) {
                this.method796((Class98_Sub46_Sub2)this.aClass215_606.method2789(n ^ 0xFF00CB7C), n ^ 0x347B);
            }
            final Class98_Sub46_Sub2_Sub2 class98_Sub46_Sub2_Sub2 = new Class98_Sub46_Sub2_Sub2(o, n3);
            this.aClass377_599.method3996(class98_Sub46_Sub2_Sub2, n2, -1);
            if (n != 13436) {
                this.method807(-1, 58L, 23, null);
            }
            this.aClass215_606.method2785(class98_Sub46_Sub2_Sub2, -28);
            class98_Sub46_Sub2_Sub2.aLong4259 = 0L;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fc.J(" + n + ',' + n2 + ',' + n3 + ',' + ((o != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method808(final long n, final int n2) {
        try {
            if (n2 != 67108863) {
                this.method794(-39);
            }
            this.method796((Class98_Sub46_Sub2)this.aClass377_599.method3990(n, -1), 7);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fc.N(" + n + ',' + n2 + ')');
        }
    }
    
    Class79(final int n, final int n2) {
        this.aClass215_606 = new Class215();
        try {
            this.anInt598 = n;
            this.anInt605 = n;
            int n3;
            for (n3 = 1; ~n < ~(n3 + n3) && ~n3 > ~n2; n3 += n3) {}
            this.aClass377_599 = new Class377(n3);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fc.<init>(" + n + ',' + n2 + ')');
        }
    }
    
    static Class method809(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        Class79.aClass85_600 = new Class85(8, 4);
        Class79.aBoolean602 = false;
        Class79.anIntArray603 = new int[1000];
        Class79.anIntArray604 = new int[] { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071, 262143, 524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431, 67108863, 134217727, 268435455, 536870911, 1073741823, Integer.MAX_VALUE, -1 };
    }
}
