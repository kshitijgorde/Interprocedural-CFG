// 
// Decompiled by Procyon v0.5.30
// 

final class Class373_Sub1_Sub1 extends PacketParser
{
    static Class126 aClass126_6216;
    
    static final void method3970(final Class207 class207, final byte b) {
        try {
            Class361.anInt3089 = 0;
            Class351.anInt2922 = 0;
            Class267.aClass218_2002 = new Class218();
            Class185.aClass246_Sub4_Sub2_Sub1Array1445 = new Class246_Sub4_Sub2_Sub1[1024];
            Class373_Sub2.aClass246_Sub5Array5469 = new Class246_Sub5[Class224_Sub1.anIntArray5034[Class337_Sub1.anInt5497] + 1];
            Class273.anInt2039 = 0;
            Class258.anInt1952 = 0;
            Class242.method2935((byte)40, class207);
            if (b > -58) {
                Class373_Sub1_Sub1.aClass126_6216 = null;
            }
            Class89.method880(-13258, class207);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qea.N(" + ((class207 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    public static void method3971(final int n) {
        try {
            if (n != 0) {
                Class373_Sub1_Sub1.aClass126_6216 = null;
            }
            Class373_Sub1_Sub1.aClass126_6216 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qea.P(" + n + ')');
        }
    }
    
    static final void method3972(final int n, final int n2, final int n3) {
        final Class172 class172 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n][n2][n3];
        if (class172 != null) {
            Class129.method2227(class172.aClass246_Sub3_Sub3_1324);
            Class129.method2227(class172.aClass246_Sub3_Sub3_1333);
            if (class172.aClass246_Sub3_Sub3_1324 != null) {
                class172.aClass246_Sub3_Sub3_1324 = null;
            }
            if (class172.aClass246_Sub3_Sub3_1333 != null) {
                class172.aClass246_Sub3_Sub3_1333 = null;
            }
        }
    }
    
    static final boolean method3973(final int n, final int n2, final int n3) {
        try {
            return (~(n & 0x60000) != -1 | Class161.method2514(n, 16, n2)) || Class228.method2864(55, n, n2) || Class216.method2793(n2, (byte)(-112), n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qea.Q(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method3966(final int n, final int n2, final int n3, final int n4, final byte b) {
        try {
            if (b >= 21) {
                final int method3737 = super.aClass332_5462.method3737();
                final int n5 = ((Class93_Sub1_Sub1)super.aClass93_3478).anInt6290 * RuntimeException_Sub1.method4012(true) / 10 % method3737;
                super.aClass332_5462.method3738(n5 + n4 + -method3737, n3, method3737 + n2 + -n5, n);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qea.G(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ')');
        }
    }
    
    Class373_Sub1_Sub1(final Class207 class207, final Class207 class208, final Class93_Sub1_Sub1 class93_Sub1_Sub1) {
        super(class207, class208, class93_Sub1_Sub1);
    }
    
    static {
        Class373_Sub1_Sub1.aClass126_6216 = new Class126();
    }
}
