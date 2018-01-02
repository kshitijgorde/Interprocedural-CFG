// 
// Decompiled by Procyon v0.5.30
// 

final class Class59
{
    static int anInt464;
    private Class207 aClass207_465;
    static int anInt466;
    private Class79 aClass79_467;
    static OutgoingOpcode aClass171_468;
    static IncomingOpcode aClass58_469;
    
    static final boolean method524(final int n, final int n2, final int n3) {
        try {
            if (n3 < 34) {
                method526((byte)105, 80, null);
            }
            return ((n & 0x800) != 0x0 | Class360.method3905(-12, n, n2)) || Class228.method2864(55, n, n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eaa.A(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    final void method525(final int n) {
        try {
            synchronized (this.aClass79_467) {
                this.aClass79_467.method806((byte)117);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eaa.E(" + n + ')');
        }
    }
    
    static final float[] method526(final byte b, final int n, final float[] array) {
        try {
            if (b != -64) {
                Class59.anInt466 = -4;
            }
            final float[] array2 = new float[n];
            Class236.method2897(array, 0, array2, 0, n);
            return array2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eaa.C(" + b + ',' + n + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final Class60 method527(final int n, final byte b) {
        try {
            final Class60 class60;
            synchronized (this.aClass79_467) {
                class60 = (Class60)this.aClass79_467.method802(-125, n);
            }
            if (class60 != null) {
                return class60;
            }
            if (b >= -87) {
                Class59.aClass171_468 = null;
            }
            final byte[] method2745;
            synchronized (this.aClass207_465) {
                method2745 = this.aClass207_465.method2745(n, 29, false);
            }
            final Class60 class61 = new Class60();
            if (method2745 != null) {
                class61.method533(new Class98_Sub22(method2745), 0);
            }
            synchronized (this.aClass79_467) {
                this.aClass79_467.method805(n, class61, (byte)(-80));
            }
            return class61;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eaa.H(" + n + ',' + b + ')');
        }
    }
    
    final Class346 method528(final int n, final int n2, final int n3, final int n4, final Class115 class115, final int n5) {
        try {
            if (n != 0) {
                Class59.anInt466 = 103;
            }
            Class175[] array = null;
            final Class60 method527 = this.method527(n2, (byte)(-120));
            if (method527.anIntArray473 != null) {
                array = new Class175[method527.anIntArray473.length];
                for (int i = 0; i < array.length; ++i) {
                    final Class266 method528 = class115.method2157(method527.anIntArray473[i], (byte)(-87));
                    array[i] = new Class175(method528.anInt1993, method528.anInt1995, method528.anInt1990, method528.anInt1989, method528.anInt1987, method528.anInt1984, method528.anInt1991, method528.aBoolean1985);
                }
            }
            return new Class346(method527.anInt470, array, method527.anInt472, n4, n3, n5);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eaa.G(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + ((class115 != null) ? "{...}" : "null") + ',' + n5 + ')');
        }
    }
    
    final void method529(final byte b) {
        try {
            synchronized (this.aClass79_467) {
                this.aClass79_467.method794(37);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eaa.B(" + b + ')');
        }
    }
    
    final void method530(final byte b, final int n) {
        try {
            if (b != -32) {
                this.method528(33, -37, -43, -90, null, 116);
            }
            synchronized (this.aClass79_467) {
                this.aClass79_467.method800((byte)62, n);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eaa.D(" + b + ',' + n + ')');
        }
    }
    
    Class59(final Class279 class279, final int n, final Class207 aClass207_465) {
        this.aClass79_467 = new Class79(16);
        try {
            (this.aClass207_465 = aClass207_465).method2761(0, 29);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eaa.<init>(" + ((class279 != null) ? "{...}" : "null") + ',' + n + ',' + ((aClass207_465 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method531(final byte b) {
        try {
            Class59.aClass171_468 = null;
            Class59.aClass58_469 = null;
            if (b >= -114) {
                method526((byte)(-56), -98, null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eaa.F(" + b + ')');
        }
    }
    
    static {
        Class59.anInt464 = 0;
        Class59.aClass171_468 = new OutgoingOpcode(36, -1);
        Class59.aClass58_469 = new IncomingOpcode(113, -2);
    }
}
