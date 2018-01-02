// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub4 extends Class98_Sub10
{
    static int[] anIntArray5547;
    private int anInt5548;
    private int anInt5549;
    static String[] aStringArray5550;
    private int anInt5551;
    
    public static void method1013(final int n) {
        try {
            Class98_Sub10_Sub4.anIntArray5547 = null;
            Class98_Sub10_Sub4.aStringArray5550 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bu.B(" + n + ')');
        }
    }
    
    @Override
    final int[][] method997(final int n, final int n2) {
        try {
            final int[][] method2828 = super.aClass223_3859.method2828(n2, 0);
            if (n > -76) {
                return null;
            }
            if (super.aClass223_3859.aBoolean1683) {
                final int[][] method2829 = this.method994(n2, 24431, 0);
                final int[] array = method2829[0];
                final int[] array2 = method2829[1];
                final int[] array3 = method2829[2];
                final int[] array4 = method2828[0];
                final int[] array5 = method2828[1];
                final int[] array6 = method2828[2];
                for (int n3 = 0; ~Class25.anInt268 < ~n3; ++n3) {
                    final int n4 = array[n3];
                    final int n5 = array3[n3];
                    final int n6 = array2[n3];
                    if (~n4 != ~n5 || ~n5 != ~n6) {
                        array4[n3] = this.anInt5548;
                        array5[n3] = this.anInt5551;
                        array6[n3] = this.anInt5549;
                    }
                    else {
                        array4[n3] = this.anInt5548 * n4 >> 761727308;
                        array5[n3] = n5 * this.anInt5551 >> 1764240204;
                        array6[n3] = n6 * this.anInt5549 >> 92417804;
                    }
                }
            }
            return method2828;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bu.C(" + n + ',' + n2 + ')');
        }
    }
    
    public Class98_Sub10_Sub4() {
        super(1, false);
        this.anInt5549 = 4096;
        this.anInt5548 = 4096;
        this.anInt5551 = 4096;
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (n != 0) {
                if (n != 1) {
                    if (n == 2) {
                        this.anInt5549 = class98_Sub22.readShort((byte)127);
                    }
                }
                else {
                    this.anInt5551 = class98_Sub22.readShort((byte)127);
                }
            }
            else {
                this.anInt5548 = class98_Sub22.readShort((byte)127);
            }
            if (b > -92) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bu.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static {
        Class98_Sub10_Sub4.anIntArray5547 = new int[13];
        Class98_Sub10_Sub4.aStringArray5550 = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
    }
}
