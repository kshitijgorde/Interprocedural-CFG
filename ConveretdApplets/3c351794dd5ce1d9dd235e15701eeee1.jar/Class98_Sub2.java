// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub2 extends Class98
{
    boolean aBoolean3817;
    int anInt3818;
    private int[] anIntArray3819;
    int anInt3820;
    private static int[] anIntArray3821;
    private int anInt3822;
    
    final int[] method948() {
        return this.anIntArray3819;
    }
    
    final void method949(final int n, final int n2) {
        if (n != 0 || n2 != 0) {
            if (Class98_Sub2.anIntArray3821 == null || Class98_Sub2.anIntArray3821.length != this.anIntArray3819.length) {
                Class98_Sub2.anIntArray3821 = new int[this.anIntArray3819.length];
            }
            final int length = this.anIntArray3819.length;
            final int n3 = this.anInt3822 - 1;
            final int n4 = this.anInt3822 * n2;
            final int n5 = length - 1;
            for (int i = 0; i < length; i += this.anInt3822) {
                final int n6 = i + n4 & n5;
                for (int j = 0; j < this.anInt3822; ++j) {
                    Class98_Sub2.anIntArray3821[i + j] = this.anIntArray3819[n6 + (j + n & n3)];
                }
            }
            final int[] anIntArray3819 = this.anIntArray3819;
            this.anIntArray3819 = Class98_Sub2.anIntArray3821;
            Class98_Sub2.anIntArray3821 = anIntArray3819;
        }
    }
    
    public static void method950() {
        Class98_Sub2.anIntArray3821 = null;
    }
    
    Class98_Sub2(final int anInt3820, final int anInt3821, final int[] anIntArray3819, final boolean b) {
        this.anInt3820 = anInt3820;
        this.anInt3822 = anInt3821;
        this.anIntArray3819 = anIntArray3819;
        if (b) {
            final int[] array = new int[this.anInt3822];
            final int[] array2 = new int[this.anInt3822];
            final int[] array3 = new int[this.anInt3822];
            final int[] array4 = new int[this.anInt3822];
            if (Class98_Sub2.anIntArray3821 == null || Class98_Sub2.anIntArray3821.length != this.anIntArray3819.length) {
                Class98_Sub2.anIntArray3821 = new int[this.anIntArray3819.length];
            }
            final int anInt3822 = this.anInt3822;
            final int anInt3823 = this.anInt3822;
            final int n = anInt3822 - 1;
            final int n2 = anInt3823 - 1;
            final int n3 = anInt3822 * anInt3823;
            int n5;
            int n4 = n5 = anInt3822;
            for (int i = 2; i >= 0; --i) {
                for (int j = n; j >= 0; --j) {
                    final int n6 = this.anIntArray3819[--n4];
                    final int[] array5 = array;
                    final int n7 = j;
                    array5[n7] += (n6 >> 24 & 0xFF);
                    final int[] array6 = array2;
                    final int n8 = j;
                    array6[n8] += (n6 >> 16 & 0xFF);
                    final int[] array7 = array3;
                    final int n9 = j;
                    array7[n9] += (n6 >> 8 & 0xFF);
                    final int[] array8 = array4;
                    final int n10 = j;
                    array8[n10] += (n6 & 0xFF);
                }
                if (n4 == 0) {
                    n4 = n3;
                }
            }
            int n11 = n3;
            for (int k = n2; k >= 0; --k) {
                int n12 = 1;
                int n13 = 1;
                int n17;
                int n16;
                int n15;
                int n14 = n15 = (n16 = (n17 = 0));
                for (int l = 2; l >= 0; --l) {
                    --n13;
                    n15 += array[n13];
                    n14 += array2[n13];
                    n17 += array3[n13];
                    n16 += array4[n13];
                    if (n13 == 0) {
                        n13 = anInt3822;
                    }
                }
                for (int n18 = n; n18 >= 0; --n18) {
                    --n13;
                    --n12;
                    Class98_Sub2.anIntArray3821[--n11] = (n15 / 9 << 24 | n14 / 9 << 16 | n17 / 9 << 8 | n16 / 9);
                    n15 += array[n13] - array[n12];
                    n14 += array2[n13] - array2[n12];
                    n16 += array4[n13] - array4[n12];
                    n17 += array3[n13] - array3[n12];
                    if (n13 == 0) {
                        n13 = anInt3822;
                    }
                    if (n12 == 0) {
                        n12 = anInt3822;
                    }
                }
                for (int n19 = n; n19 >= 0; --n19) {
                    final int n20 = this.anIntArray3819[--n4];
                    final int n21 = this.anIntArray3819[--n5];
                    final int[] array9 = array;
                    final int n22 = n19;
                    array9[n22] += (n20 >> 24 & 0xFF) - (n21 >> 24 & 0xFF);
                    final int[] array10 = array2;
                    final int n23 = n19;
                    array10[n23] += (n20 >> 16 & 0xFF) - (n21 >> 16 & 0xFF);
                    final int[] array11 = array3;
                    final int n24 = n19;
                    array11[n24] += (n20 >> 8 & 0xFF) - (n21 >> 8 & 0xFF);
                    final int[] array12 = array4;
                    final int n25 = n19;
                    array12[n25] += (n20 & 0xFF) - (n21 & 0xFF);
                }
                if (n4 == 0) {
                    n4 = n3;
                }
                if (n5 == 0) {
                    n5 = n3;
                }
            }
            final int[] anIntArray3820 = this.anIntArray3819;
            this.anIntArray3819 = Class98_Sub2.anIntArray3821;
            Class98_Sub2.anIntArray3821 = anIntArray3820;
        }
    }
}
