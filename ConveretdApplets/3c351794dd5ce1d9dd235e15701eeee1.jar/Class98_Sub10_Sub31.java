// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub31 extends Class98_Sub10
{
    private int anInt5714;
    private int anInt5715;
    static Class350 aClass350_5716;
    static Class1[] aClass1Array5717;
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (~n != -1) {
                if (n == 1) {
                    this.anInt5715 = class98_Sub22.readShort((byte)127);
                }
            }
            else {
                this.anInt5714 = class98_Sub22.readShort((byte)127);
            }
            if (b >= -92) {
                this.method990(-115, -128);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rf.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    @Override
    final int[] method990(final int n, final int n2) {
        try {
            final int[] method237 = super.aClass16_3863.method237((byte)98, n2);
            if (super.aClass16_3863.aBoolean198) {
                final int[] method238 = this.method1000(n2, 0, 0);
                for (int i = 0; i < Class25.anInt268; ++i) {
                    final int n3 = method238[i];
                    method237[i] = ((~n3 <= ~this.anInt5714 && n3 <= this.anInt5715) ? 4096 : 0);
                }
            }
            if (n != 255) {
                Class98_Sub10_Sub31.aClass350_5716 = null;
            }
            return method237;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rf.G(" + n + ',' + n2 + ')');
        }
    }
    
    public static void method1095(final int n) {
        try {
            Class98_Sub10_Sub31.aClass1Array5717 = null;
            Class98_Sub10_Sub31.aClass350_5716 = null;
            if (n != 10640) {
                Class98_Sub10_Sub31.aClass1Array5717 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rf.B(" + n + ')');
        }
    }
    
    public Class98_Sub10_Sub31() {
        super(1, true);
        this.anInt5715 = 4096;
        this.anInt5714 = 0;
    }
    
    static {
        Class98_Sub10_Sub31.aClass350_5716 = new Class350(0);
    }
}
