import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

final class InputStream_Sub2 extends InputStream
{
    static int anInt29;
    
    static final aa_Sub3 method123(final int n, final int[] array, final ha_Sub1 ha_Sub1, final int n2, final int[] array2, final byte b) {
        try {
            if (b != 111) {
                InputStream_Sub2.anInt29 = -5;
            }
            final byte[] array3 = new byte[n * n2];
            for (int n3 = 0; ~n3 > ~n; ++n3) {
                int n4 = n2 * n3 + array[n3];
                for (int n5 = 0; ~n5 > ~array2[n3]; ++n5) {
                    array3[n4++] = -1;
                }
            }
            return new aa_Sub3(ha_Sub1, n2, n, array3);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rca.B(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + n2 + ',' + ((array2 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    @Override
    public final int read() {
        try {
            Class246_Sub7.method3131(0, 30000L);
            return -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rca.read()");
        }
    }
    
    static final void method124(final int n) {
        try {
            Class64_Sub9.aClass332_3663 = null;
            Canvas_Sub1.anInt26 = -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rca.A(" + n + ')');
        }
    }
    
    static final void method125(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        try {
            final int n7 = n + -n5;
            if (n6 != 21597) {
                InputStream_Sub2.anInt29 = 22;
            }
            final int n8 = n3 + -n4;
            if (~n7 == -1) {
                if (n8 != 0) {
                    Class98_Sub50.method1671(n5, (byte)0, n4, n3, n2);
                }
                return;
            }
            if (n8 == 0) {
                Class318.method3656(n2, n5, (byte)85, n, n4);
                return;
            }
            final int n9 = (n8 << -2124827092) / n7;
            final int n10 = n4 + -(n9 * n5 >> 203447180);
            int n11;
            int n12;
            if (~n > ~Class76_Sub8.anInt3778) {
                n11 = n10 + (n9 * Class76_Sub8.anInt3778 >> -277934004);
                n12 = Class76_Sub8.anInt3778;
            }
            else if (n > Class3.anInt77) {
                n12 = Class3.anInt77;
                n11 = n10 + (Class3.anInt77 * n9 >> 674340300);
            }
            else {
                n12 = n;
                n11 = n3;
            }
            int n13;
            int n14;
            if (Class76_Sub8.anInt3778 > n5) {
                n13 = n10 - -(n9 * Class76_Sub8.anInt3778 >> 376571916);
                n14 = Class76_Sub8.anInt3778;
            }
            else if (~n5 < ~Class3.anInt77) {
                n13 = n10 + (n9 * Class3.anInt77 >> 854040140);
                n14 = Class3.anInt77;
            }
            else {
                n13 = n4;
                n14 = n5;
            }
            if (Class98_Sub10_Sub38.anInt5753 > n11) {
                n11 = Class98_Sub10_Sub38.anInt5753;
                n12 = (-n10 + Class98_Sub10_Sub38.anInt5753 << -561169876) / n9;
            }
            else if (~Class218.anInt1635 > ~n11) {
                n12 = (-n10 + Class218.anInt1635 << -895871700) / n9;
                n11 = Class218.anInt1635;
            }
            if (~Class98_Sub10_Sub38.anInt5753 >= ~n13) {
                if (~n13 < ~Class218.anInt1635) {
                    n13 = Class218.anInt1635;
                    n14 = (-n10 + Class218.anInt1635 << 322051660) / n9;
                }
            }
            else {
                n13 = Class98_Sub10_Sub38.anInt5753;
                n14 = (-n10 + Class98_Sub10_Sub38.anInt5753 << 865861260) / n9;
            }
            Class91.method890(n2, n13, n11, n12, (byte)(-126), n14);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rca.C(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    static {
        InputStream_Sub2.anInt29 = -50;
    }
}
