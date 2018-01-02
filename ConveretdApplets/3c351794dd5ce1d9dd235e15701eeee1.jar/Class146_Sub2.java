import jaclib.memory.Stream;
import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class146_Sub2 extends Class146
{
    private int anInt4837;
    private Class322 aClass322_4838;
    private Interface16 anInterface16_4839;
    static boolean[] aBooleanArray4840;
    private short[] aShortArray4841;
    private short[] aShortArray4842;
    private int[] anIntArray4843;
    private short aShort4844;
    private short aShort4845;
    private int anInt4846;
    private boolean aBoolean4847;
    private boolean aBoolean4848;
    private short[] aShortArray4849;
    private int[] anIntArray4850;
    private int[] anIntArray4851;
    private short[] aShortArray4852;
    private int anInt4853;
    private Class104 aClass104_4854;
    static int anInt4855;
    private int[] anIntArray4856;
    private boolean aBoolean4857;
    private byte[] aByteArray4858;
    private Class104 aClass104_4859;
    private short[] aShortArray4860;
    private Class219[] aClass219Array4861;
    private short aShort4862;
    private Class35[] aClass35Array4863;
    private short[] aShortArray4864;
    private ha_Sub1 aHa_Sub1_4865;
    private int anInt4866;
    private short aShort4867;
    private short aShort4868;
    private short[] aShortArray4869;
    private int[][] anIntArrayArray4870;
    private boolean aBoolean4871;
    private short aShort4872;
    static int[] anIntArray4873;
    private Class104 aClass104_4874;
    private int[][] anIntArrayArray4875;
    private short aShort4876;
    private Class249[] aClass249Array4877;
    private short[] aShortArray4878;
    private short aShort4879;
    private Interface8 anInterface8_4880;
    private Class87[] aClass87Array4881;
    private byte[] aByteArray4882;
    private int anInt4883;
    private short[] aShortArray4884;
    private byte aByte4885;
    private int[] anIntArray4886;
    private short[] aShortArray4887;
    private int[][] anIntArrayArray4888;
    private Class104 aClass104_4889;
    private float[] aFloatArray4890;
    private short aShort4891;
    private short aShort4892;
    private int anInt4893;
    private int anInt4894;
    private short[] aShortArray4895;
    private int anInt4896;
    private float[] aFloatArray4897;
    private Class14 aClass14_4898;
    
    private final boolean method2377(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        try {
            if (n8 != 18818) {
                this.anInt4866 = 66;
            }
            return (~n3 <= ~n7 || ~n2 >= ~n3 || n3 >= n) && (~n7 <= ~n3 || n2 >= n3 || ~n3 >= ~n) && (~n6 >= ~n9 || ~n9 <= ~n5 || n9 >= n4) && (~n9 >= ~n6 || ~n5 <= ~n9 || n4 >= n9);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.K(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + n9 + ')');
        }
    }
    
    @Override
    final int RA() {
        try {
            if (!this.aBoolean4847) {
                this.method2380(-128);
            }
            return this.aShort4862;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.RA()");
        }
    }
    
    @Override
    final int V() {
        try {
            if (!this.aBoolean4847) {
                this.method2380(-102);
            }
            return this.aShort4845;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.V()");
        }
    }
    
    @Override
    final int na() {
        try {
            if (!this.aBoolean4847) {
                this.method2380(-118);
            }
            return this.aShort4876;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.na()");
        }
    }
    
    @Override
    final int ua() {
        try {
            return this.anInt4894;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.ua()");
        }
    }
    
    public static void method2378(final int n) {
        try {
            Class146_Sub2.anIntArray4873 = null;
            if (n != 0) {
                method2384(null, 93, -2, -11, 41, 13, 96, 27, -66, 57, -56);
            }
            Class146_Sub2.aBooleanArray4840 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.L(" + n + ')');
        }
    }
    
    @Override
    final void aa(final short n, final short n2) {
        try {
            final d ad938 = this.aHa_Sub1_4865.aD938;
            for (int n3 = 0; this.anInt4853 > n3; ++n3) {
                if (~n == ~this.aShortArray4869[n3]) {
                    this.aShortArray4869[n3] = n2;
                }
            }
            byte aByte1830 = 0;
            byte aByte1831 = 0;
            if (n != -1) {
                final Class238 method11 = ad938.method11(n & 0xFFFF, -28755);
                aByte1830 = method11.aByte1830;
                aByte1831 = method11.aByte1829;
            }
            byte aByte1832 = 0;
            byte aByte1833 = 0;
            if (n2 != -1) {
                final Class238 method12 = ad938.method11(n2 & 0xFFFF, -28755);
                aByte1833 = method12.aByte1829;
                aByte1832 = method12.aByte1830;
                if (method12.aByte1823 != 0 || method12.aByte1837 != 0) {
                    this.aBoolean4848 = true;
                }
            }
            if (aByte1832 != aByte1830 | ~aByte1833 != ~aByte1831) {
                if (this.aClass249Array4877 != null) {
                    for (int i = 0; i < this.anInt4866; ++i) {
                        final Class249 class249 = this.aClass249Array4877[i];
                        final Class219 class250 = this.aClass219Array4861[i];
                        class250.anInt1643 = ((Class208.anIntArray1579[0xFFFF & this.aShortArray4842[class249.anInt1905]] & 0xFFFFFF) | (class250.anInt1643 & 0xFF000000));
                    }
                }
                if (this.aClass104_4854 != null) {
                    this.aClass104_4854.anInterface16_902 = null;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.aa(" + n + ',' + n2 + ')');
        }
    }
    
    private final Class146 method2379(final int n, final boolean b, final boolean b2, final int anInt4894, final Class146_Sub2 class146_Sub2, final Class146_Sub2 class146_Sub3) {
        try {
            class146_Sub2.anInt4846 = this.anInt4846;
            class146_Sub2.anInt4893 = this.anInt4893;
            class146_Sub2.anInt4883 = this.anInt4883;
            class146_Sub2.anInt4896 = this.anInt4896;
            if (~(0x100 & anInt4894) == -1) {
                class146_Sub2.aBoolean4857 = this.aBoolean4857;
            }
            else {
                class146_Sub2.aBoolean4857 = true;
            }
            class146_Sub2.anInt4837 = this.anInt4837;
            class146_Sub2.anInt4866 = this.anInt4866;
            class146_Sub2.aShort4872 = this.aShort4872;
            class146_Sub2.anInt4853 = this.anInt4853;
            class146_Sub2.aShort4867 = this.aShort4867;
            class146_Sub2.aByte4885 = 0;
            class146_Sub2.aBoolean4848 = this.aBoolean4848;
            class146_Sub2.anInt4894 = anInt4894;
            final boolean method3974 = Class373_Sub2.method3974(this.anInt4837, anInt4894, 86);
            final boolean method3975 = Class98.method944(anInt4894, this.anInt4837, (byte)85);
            final boolean method3976 = Class195.method2663(anInt4894, this.anInt4837, false);
            if (method3974 | method3975 | method3976) {
                if (!method3974) {
                    class146_Sub2.anIntArray4886 = this.anIntArray4886;
                }
                else if (class146_Sub3.anIntArray4886 != null && ~class146_Sub3.anIntArray4886.length <= ~this.anInt4893) {
                    class146_Sub2.anIntArray4886 = class146_Sub3.anIntArray4886;
                }
                else {
                    final int[] array = new int[this.anInt4893];
                    class146_Sub3.anIntArray4886 = array;
                    class146_Sub2.anIntArray4886 = array;
                }
                if (method3975) {
                    if (class146_Sub3.anIntArray4856 == null || ~class146_Sub3.anIntArray4856.length > ~this.anInt4893) {
                        final int[] array2 = new int[this.anInt4893];
                        class146_Sub3.anIntArray4856 = array2;
                        class146_Sub2.anIntArray4856 = array2;
                    }
                    else {
                        class146_Sub2.anIntArray4856 = class146_Sub3.anIntArray4856;
                    }
                }
                else {
                    class146_Sub2.anIntArray4856 = this.anIntArray4856;
                }
                if (method3976) {
                    if (class146_Sub3.anIntArray4843 != null && ~class146_Sub3.anIntArray4843.length <= ~this.anInt4893) {
                        class146_Sub2.anIntArray4843 = class146_Sub3.anIntArray4843;
                    }
                    else {
                        final int[] array3 = new int[this.anInt4893];
                        class146_Sub3.anIntArray4843 = array3;
                        class146_Sub2.anIntArray4843 = array3;
                    }
                }
                else {
                    class146_Sub2.anIntArray4843 = this.anIntArray4843;
                }
                for (int n2 = 0; this.anInt4893 > n2; ++n2) {
                    if (method3974) {
                        class146_Sub2.anIntArray4886[n2] = this.anIntArray4886[n2];
                    }
                    if (method3975) {
                        class146_Sub2.anIntArray4856[n2] = this.anIntArray4856[n2];
                    }
                    if (method3976) {
                        class146_Sub2.anIntArray4843[n2] = this.anIntArray4843[n2];
                    }
                }
            }
            else {
                class146_Sub2.anIntArray4886 = this.anIntArray4886;
                class146_Sub2.anIntArray4843 = this.anIntArray4843;
                class146_Sub2.anIntArray4856 = this.anIntArray4856;
            }
            if (Class76_Sub7.method763(anInt4894, this.anInt4837, false)) {
                if (b2) {
                    class146_Sub2.aByte4885 |= 0x1;
                }
                class146_Sub2.aClass104_4889 = class146_Sub3.aClass104_4889;
                class146_Sub2.aClass104_4889.aByte898 = this.aClass104_4889.aByte898;
                class146_Sub2.aClass104_4889.anInterface16_902 = this.aClass104_4889.anInterface16_902;
            }
            else if (!Class246_Sub2.method2973(anInt4894, this.anInt4837, (byte)(-112))) {
                class146_Sub2.aClass104_4889 = null;
            }
            else {
                class146_Sub2.aClass104_4889 = this.aClass104_4889;
            }
            if (Class98_Sub5_Sub2.method969(this.anInt4837, anInt4894, 124)) {
                if (class146_Sub3.aShortArray4842 != null && ~this.anInt4853 >= ~class146_Sub3.aShortArray4842.length) {
                    class146_Sub2.aShortArray4842 = class146_Sub3.aShortArray4842;
                }
                else {
                    final short[] array4 = new short[this.anInt4853];
                    class146_Sub3.aShortArray4842 = array4;
                    class146_Sub2.aShortArray4842 = array4;
                }
                for (int n3 = 0; ~this.anInt4853 < ~n3; ++n3) {
                    class146_Sub2.aShortArray4842[n3] = this.aShortArray4842[n3];
                }
            }
            else {
                class146_Sub2.aShortArray4842 = this.aShortArray4842;
            }
            if (!Class64_Sub10.method594(anInt4894, 6, this.anInt4837)) {
                class146_Sub2.aByteArray4882 = this.aByteArray4882;
            }
            else {
                if (class146_Sub3.aByteArray4882 != null && ~class146_Sub3.aByteArray4882.length <= ~this.anInt4853) {
                    class146_Sub2.aByteArray4882 = class146_Sub3.aByteArray4882;
                }
                else {
                    final byte[] array5 = new byte[this.anInt4853];
                    class146_Sub3.aByteArray4882 = array5;
                    class146_Sub2.aByteArray4882 = array5;
                }
                for (int n4 = 0; ~this.anInt4853 < ~n4; ++n4) {
                    class146_Sub2.aByteArray4882[n4] = this.aByteArray4882[n4];
                }
            }
            if (Class228.method2864(55, anInt4894, this.anInt4837)) {
                class146_Sub2.aClass104_4854 = class146_Sub3.aClass104_4854;
                if (b2) {
                    class146_Sub2.aByte4885 |= 0x2;
                }
                class146_Sub2.aClass104_4854.anInterface16_902 = this.aClass104_4854.anInterface16_902;
                class146_Sub2.aClass104_4854.aByte898 = this.aClass104_4854.aByte898;
            }
            else if (!Class87.method854(this.anInt4837, 28733, anInt4894)) {
                class146_Sub2.aClass104_4854 = null;
            }
            else {
                class146_Sub2.aClass104_4854 = this.aClass104_4854;
            }
            if (Class151_Sub2.method2451(anInt4894, 544, this.anInt4837)) {
                if (class146_Sub3.aShortArray4860 == null || ~this.anInt4846 < ~class146_Sub3.aShortArray4860.length) {
                    final int anInt4895 = this.anInt4846;
                    final short[] array6 = new short[anInt4895];
                    class146_Sub3.aShortArray4860 = array6;
                    class146_Sub2.aShortArray4860 = array6;
                    final short[] array7 = new short[anInt4895];
                    class146_Sub3.aShortArray4849 = array7;
                    class146_Sub2.aShortArray4849 = array7;
                    final short[] array8 = new short[anInt4895];
                    class146_Sub3.aShortArray4884 = array8;
                    class146_Sub2.aShortArray4884 = array8;
                }
                else {
                    class146_Sub2.aShortArray4884 = class146_Sub3.aShortArray4884;
                    class146_Sub2.aShortArray4849 = class146_Sub3.aShortArray4849;
                    class146_Sub2.aShortArray4860 = class146_Sub3.aShortArray4860;
                }
                if (this.aClass14_4898 == null) {
                    for (int n5 = 0; this.anInt4846 > n5; ++n5) {
                        class146_Sub2.aShortArray4860[n5] = this.aShortArray4860[n5];
                        class146_Sub2.aShortArray4884[n5] = this.aShortArray4884[n5];
                        class146_Sub2.aShortArray4849[n5] = this.aShortArray4849[n5];
                    }
                }
                else {
                    if (class146_Sub3.aClass14_4898 == null) {
                        class146_Sub3.aClass14_4898 = new Class14();
                    }
                    final Class14 aClass14_4898 = class146_Sub3.aClass14_4898;
                    class146_Sub2.aClass14_4898 = aClass14_4898;
                    final Class14 class14 = aClass14_4898;
                    if (class14.aShortArray166 == null || ~class14.aShortArray166.length > ~this.anInt4846) {
                        final int anInt4896 = this.anInt4846;
                        class14.aShortArray167 = new short[anInt4896];
                        class14.aShortArray166 = new short[anInt4896];
                        class14.aShortArray165 = new short[anInt4896];
                        class14.aByteArray168 = new byte[anInt4896];
                    }
                    for (int n6 = 0; ~n6 > ~this.anInt4846; ++n6) {
                        class146_Sub2.aShortArray4860[n6] = this.aShortArray4860[n6];
                        class146_Sub2.aShortArray4884[n6] = this.aShortArray4884[n6];
                        class146_Sub2.aShortArray4849[n6] = this.aShortArray4849[n6];
                        class14.aShortArray166[n6] = this.aClass14_4898.aShortArray166[n6];
                        class14.aShortArray165[n6] = this.aClass14_4898.aShortArray165[n6];
                        class14.aShortArray167[n6] = this.aClass14_4898.aShortArray167[n6];
                        class14.aByteArray168[n6] = this.aClass14_4898.aByteArray168[n6];
                    }
                }
                class146_Sub2.aByteArray4858 = this.aByteArray4858;
            }
            else {
                class146_Sub2.aClass14_4898 = this.aClass14_4898;
                class146_Sub2.aByteArray4858 = this.aByteArray4858;
                class146_Sub2.aShortArray4849 = this.aShortArray4849;
                class146_Sub2.aShortArray4860 = this.aShortArray4860;
                class146_Sub2.aShortArray4884 = this.aShortArray4884;
            }
            if (!Class64_Sub28.method670(anInt4894, n ^ 0xFFFFCF05, this.anInt4837)) {
                if (Class246_Sub3_Sub5_Sub2.method3096(-27984, this.anInt4837, anInt4894)) {
                    class146_Sub2.aClass104_4859 = this.aClass104_4859;
                }
                else {
                    class146_Sub2.aClass104_4859 = null;
                }
            }
            else {
                class146_Sub2.aClass104_4859 = class146_Sub3.aClass104_4859;
                if (b2) {
                    class146_Sub2.aByte4885 |= 0x4;
                }
                class146_Sub2.aClass104_4859.aByte898 = this.aClass104_4859.aByte898;
                class146_Sub2.aClass104_4859.anInterface16_902 = this.aClass104_4859.anInterface16_902;
            }
            if (n != 255) {
                this.aShortArray4864 = null;
            }
            if (!s_Sub1.method3433(anInt4894, 15849, this.anInt4837)) {
                class146_Sub2.aFloatArray4890 = this.aFloatArray4890;
                class146_Sub2.aFloatArray4897 = this.aFloatArray4897;
            }
            else {
                if (class146_Sub3.aFloatArray4897 == null || ~class146_Sub3.aFloatArray4897.length > ~this.anInt4853) {
                    final int anInt4897 = this.anInt4846;
                    final float[] array9 = new float[anInt4897];
                    class146_Sub3.aFloatArray4897 = array9;
                    class146_Sub2.aFloatArray4897 = array9;
                    final float[] array10 = new float[anInt4897];
                    class146_Sub3.aFloatArray4890 = array10;
                    class146_Sub2.aFloatArray4890 = array10;
                }
                else {
                    class146_Sub2.aFloatArray4897 = class146_Sub3.aFloatArray4897;
                    class146_Sub2.aFloatArray4890 = class146_Sub3.aFloatArray4890;
                }
                for (int i = 0; i < this.anInt4846; ++i) {
                    class146_Sub2.aFloatArray4897[i] = this.aFloatArray4897[i];
                    class146_Sub2.aFloatArray4890[i] = this.aFloatArray4890[i];
                }
            }
            if (!Class64_Sub19.method631(this.anInt4837, anInt4894, -2)) {
                if (!za_Sub2.method1682(anInt4894, n ^ 0xFF, this.anInt4837)) {
                    class146_Sub2.aClass104_4874 = null;
                }
                else {
                    class146_Sub2.aClass104_4874 = this.aClass104_4874;
                }
            }
            else {
                class146_Sub2.aClass104_4874 = class146_Sub3.aClass104_4874;
                if (b2) {
                    class146_Sub2.aByte4885 |= 0x8;
                }
                class146_Sub2.aClass104_4874.aByte898 = this.aClass104_4874.aByte898;
                class146_Sub2.aClass104_4874.anInterface16_902 = this.aClass104_4874.anInterface16_902;
            }
            if (!Class161.method2514(anInt4894, 16, this.anInt4837)) {
                class146_Sub2.aShortArray4895 = this.aShortArray4895;
                class146_Sub2.aShortArray4852 = this.aShortArray4852;
                class146_Sub2.aShortArray4878 = this.aShortArray4878;
            }
            else {
                if (class146_Sub3.aShortArray4895 == null || ~this.anInt4853 < ~class146_Sub3.aShortArray4895.length) {
                    final int anInt4898 = this.anInt4853;
                    final short[] array11 = new short[anInt4898];
                    class146_Sub3.aShortArray4852 = array11;
                    class146_Sub2.aShortArray4852 = array11;
                    final short[] array12 = new short[anInt4898];
                    class146_Sub3.aShortArray4878 = array12;
                    class146_Sub2.aShortArray4878 = array12;
                    final short[] array13 = new short[anInt4898];
                    class146_Sub3.aShortArray4895 = array13;
                    class146_Sub2.aShortArray4895 = array13;
                }
                else {
                    class146_Sub2.aShortArray4878 = class146_Sub3.aShortArray4878;
                    class146_Sub2.aShortArray4852 = class146_Sub3.aShortArray4852;
                    class146_Sub2.aShortArray4895 = class146_Sub3.aShortArray4895;
                }
                for (int n7 = 0; ~this.anInt4853 < ~n7; ++n7) {
                    class146_Sub2.aShortArray4895[n7] = this.aShortArray4895[n7];
                    class146_Sub2.aShortArray4852[n7] = this.aShortArray4852[n7];
                    class146_Sub2.aShortArray4878[n7] = this.aShortArray4878[n7];
                }
            }
            if (!Class216.method2793(this.anInt4837, (byte)(-115), anInt4894)) {
                if (!Class322.method3672(this.anInt4837, anInt4894, 2048)) {
                    class146_Sub2.aClass322_4838 = null;
                }
                else {
                    class146_Sub2.aClass322_4838 = this.aClass322_4838;
                }
            }
            else {
                class146_Sub2.aClass322_4838 = class146_Sub3.aClass322_4838;
                if (b2) {
                    class146_Sub2.aByte4885 |= 0x10;
                }
                class146_Sub2.aClass322_4838.anInterface8_2711 = this.aClass322_4838.anInterface8_2711;
            }
            if (!Class360.method3905(123, anInt4894, this.anInt4837)) {
                class146_Sub2.aShortArray4869 = this.aShortArray4869;
            }
            else {
                if (class146_Sub3.aShortArray4869 != null && ~class146_Sub3.aShortArray4869.length <= ~this.anInt4853) {
                    class146_Sub2.aShortArray4869 = class146_Sub3.aShortArray4869;
                }
                else {
                    final short[] array14 = new short[this.anInt4853];
                    class146_Sub3.aShortArray4869 = array14;
                    class146_Sub2.aShortArray4869 = array14;
                }
                for (int n8 = 0; ~n8 > ~this.anInt4853; ++n8) {
                    class146_Sub2.aShortArray4869[n8] = this.aShortArray4869[n8];
                }
            }
            if (!Class284_Sub1_Sub2.method3373(this.anInt4837, anInt4894, (byte)76)) {
                class146_Sub2.aClass219Array4861 = this.aClass219Array4861;
            }
            else if (class146_Sub3.aClass219Array4861 != null && this.anInt4866 <= class146_Sub3.aClass219Array4861.length) {
                class146_Sub2.aClass219Array4861 = class146_Sub3.aClass219Array4861;
                for (int j = 0; j < this.anInt4866; ++j) {
                    class146_Sub2.aClass219Array4861[j].method2814(this.aClass219Array4861[j], 6);
                }
            }
            else {
                final Class219[] array15 = new Class219[this.anInt4866];
                class146_Sub3.aClass219Array4861 = array15;
                class146_Sub2.aClass219Array4861 = array15;
                for (int n9 = 0; ~this.anInt4866 < ~n9; ++n9) {
                    class146_Sub2.aClass219Array4861[n9] = this.aClass219Array4861[n9].method2815(128);
                }
            }
            class146_Sub2.anIntArrayArray4888 = this.anIntArrayArray4888;
            class146_Sub2.anIntArray4850 = this.anIntArray4850;
            if (!this.aBoolean4847) {
                class146_Sub2.aBoolean4847 = false;
            }
            else {
                class146_Sub2.aBoolean4847 = true;
                class146_Sub2.aShort4876 = this.aShort4876;
                class146_Sub2.aShort4868 = this.aShort4868;
                class146_Sub2.aShort4844 = this.aShort4844;
                class146_Sub2.aShort4892 = this.aShort4892;
                class146_Sub2.aShort4845 = this.aShort4845;
                class146_Sub2.aShort4879 = this.aShort4879;
                class146_Sub2.aShort4891 = this.aShort4891;
                class146_Sub2.aShort4862 = this.aShort4862;
            }
            class146_Sub2.anIntArrayArray4875 = this.anIntArrayArray4875;
            class146_Sub2.anIntArray4851 = this.anIntArray4851;
            class146_Sub2.aShortArray4887 = this.aShortArray4887;
            class146_Sub2.aShortArray4864 = this.aShortArray4864;
            class146_Sub2.aClass87Array4881 = this.aClass87Array4881;
            class146_Sub2.aClass35Array4863 = this.aClass35Array4863;
            class146_Sub2.aClass249Array4877 = this.aClass249Array4877;
            class146_Sub2.anIntArrayArray4870 = this.anIntArrayArray4870;
            class146_Sub2.aShortArray4841 = this.aShortArray4841;
            return class146_Sub2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.R(" + n + ',' + b + ',' + b2 + ',' + anInt4894 + ',' + ((class146_Sub2 != null) ? "{...}" : "null") + ',' + ((class146_Sub3 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void k(final int n) {
        try {
            final int n2 = Class284_Sub2_Sub2.anIntArray6200[n];
            final int n3 = Class284_Sub2_Sub2.anIntArray6202[n];
            for (int i = 0; i < this.anInt4883; ++i) {
                final int n4 = this.anIntArray4886[i] * n3 + n2 * this.anIntArray4843[i] >> -977928274;
                this.anIntArray4843[i] = -(this.anIntArray4886[i] * n2) + n3 * this.anIntArray4843[i] >> 581937774;
                this.anIntArray4886[i] = n4;
            }
            for (int n5 = 0; ~this.anInt4846 < ~n5; ++n5) {
                final int n6 = n2 * this.aShortArray4849[n5] - -(n3 * this.aShortArray4860[n5]) >> -1933154578;
                this.aShortArray4849[n5] = (short)(-(this.aShortArray4860[n5] * n2) + this.aShortArray4849[n5] * n3 >> -1887631314);
                this.aShortArray4860[n5] = (short)n6;
            }
            if (this.aClass104_4859 == null && this.aClass104_4854 != null) {
                this.aClass104_4854.anInterface16_902 = null;
            }
            if (this.aClass104_4859 != null) {
                this.aClass104_4859.anInterface16_902 = null;
            }
            if (this.aClass104_4889 != null) {
                this.aClass104_4889.anInterface16_902 = null;
            }
            this.aBoolean4847 = false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.k(" + n + ')');
        }
    }
    
    @Override
    final void a(final int n) {
        try {
            final int n2 = Class284_Sub2_Sub2.anIntArray6200[n];
            final int n3 = Class284_Sub2_Sub2.anIntArray6202[n];
            for (int n4 = 0; this.anInt4883 > n4; ++n4) {
                final int n5 = n3 * this.anIntArray4886[n4] + this.anIntArray4843[n4] * n2 >> 757700622;
                this.anIntArray4843[n4] = this.anIntArray4843[n4] * n3 + -(n2 * this.anIntArray4886[n4]) >> 158104590;
                this.anIntArray4886[n4] = n5;
            }
            if (this.aClass104_4889 != null) {
                this.aClass104_4889.anInterface16_902 = null;
            }
            this.aBoolean4847 = false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.a(" + n + ')');
        }
    }
    
    @Override
    final boolean method2324() {
        try {
            if (this.aShortArray4869 == null) {
                return true;
            }
            for (int n = 0; ~n > ~this.aShortArray4869.length; ++n) {
                if (~this.aShortArray4869[n] != 0x0 && !this.aHa_Sub1_4865.aD938.method8(-25, this.aShortArray4869[n])) {
                    return false;
                }
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.J()");
        }
    }
    
    private final void method2380(final int n) {
        try {
            int n2 = 32767;
            int n3 = 32767;
            int n4 = 32767;
            int n5 = -32768;
            int n6 = -32768;
            int n7 = -32768;
            int n8 = 0;
            int n9 = 0;
            int n10 = 0;
            if (n >= -85) {
                this.O(-79, 52, 0);
            }
            while (this.anInt4883 > n10) {
                final int n11 = this.anIntArray4886[n10];
                final int n12 = this.anIntArray4856[n10];
                if (n3 > n12) {
                    n3 = n12;
                }
                final int n13 = this.anIntArray4843[n10];
                if (n11 > n5) {
                    n5 = n11;
                }
                if (~n2 < ~n11) {
                    n2 = n11;
                }
                if (n12 > n6) {
                    n6 = n12;
                }
                if (~n4 < ~n13) {
                    n4 = n13;
                }
                if (~n13 < ~n7) {
                    n7 = n13;
                }
                final int n14 = n11 * n11 - -(n13 * n13);
                if (~n8 > ~n14) {
                    n8 = n14;
                }
                final int n15 = n12 * n12 + n13 * n13 + n11 * n11;
                if (~n15 < ~n9) {
                    n9 = n15;
                }
                ++n10;
            }
            this.aShort4845 = (short)n2;
            this.aShort4879 = (short)n6;
            this.aShort4868 = (short)n3;
            this.aShort4862 = (short)n5;
            this.aShort4891 = (short)n7;
            this.aShort4892 = (short)n4;
            this.aShort4876 = (short)(0.99 + Math.sqrt(n8));
            this.aShort4844 = (short)(0.99 + Math.sqrt(n9));
            this.aBoolean4847 = true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.JA(" + n + ')');
        }
    }
    
    @Override
    final void method2343(final Class111 class111) {
        try {
            final Class111_Sub1 class111_Sub1 = (Class111_Sub1)class111;
            if (this.aClass87Array4881 != null) {
                for (int n = 0; ~n > ~this.aClass87Array4881.length; ++n) {
                    Class87 aClass87_657;
                    final Class87 class112 = aClass87_657 = this.aClass87Array4881[n];
                    if (class112.aClass87_657 != null) {
                        aClass87_657 = class112.aClass87_657;
                    }
                    aClass87_657.anInt670 = (int)(class111_Sub1.aFloat4674 + (class111_Sub1.aFloat4680 * this.anIntArray4843[class112.anInt666] + (this.anIntArray4886[class112.anInt666] * class111_Sub1.aFloat4686 + this.anIntArray4856[class112.anInt666] * class111_Sub1.aFloat4679)));
                    aClass87_657.anInt668 = (int)(class111_Sub1.aFloat4678 * this.anIntArray4886[class112.anInt666] + class111_Sub1.aFloat4675 * this.anIntArray4856[class112.anInt666] + class111_Sub1.aFloat4687 * this.anIntArray4843[class112.anInt666] + class111_Sub1.aFloat4683);
                    aClass87_657.anInt671 = (int)(this.anIntArray4856[class112.anInt666] * class111_Sub1.aFloat4676 + class111_Sub1.aFloat4684 * this.anIntArray4886[class112.anInt666] + this.anIntArray4843[class112.anInt666] * class111_Sub1.aFloat4673 + class111_Sub1.aFloat4677);
                    aClass87_657.anInt663 = (int)(class111_Sub1.aFloat4686 * this.anIntArray4886[class112.anInt661] + this.anIntArray4856[class112.anInt661] * class111_Sub1.aFloat4679 + this.anIntArray4843[class112.anInt661] * class111_Sub1.aFloat4680 + class111_Sub1.aFloat4674);
                    aClass87_657.anInt664 = (int)(this.anIntArray4856[class112.anInt661] * class111_Sub1.aFloat4675 + this.anIntArray4886[class112.anInt661] * class111_Sub1.aFloat4678 + this.anIntArray4843[class112.anInt661] * class111_Sub1.aFloat4687 + class111_Sub1.aFloat4683);
                    aClass87_657.anInt656 = (int)(class111_Sub1.aFloat4677 + (this.anIntArray4856[class112.anInt661] * class111_Sub1.aFloat4676 + class111_Sub1.aFloat4684 * this.anIntArray4886[class112.anInt661] + class111_Sub1.aFloat4673 * this.anIntArray4843[class112.anInt661]));
                    aClass87_657.anInt659 = (int)(this.anIntArray4886[class112.anInt674] * class111_Sub1.aFloat4686 + class111_Sub1.aFloat4679 * this.anIntArray4856[class112.anInt674] + this.anIntArray4843[class112.anInt674] * class111_Sub1.aFloat4680 + class111_Sub1.aFloat4674);
                    aClass87_657.anInt669 = (int)(class111_Sub1.aFloat4678 * this.anIntArray4886[class112.anInt674] + class111_Sub1.aFloat4675 * this.anIntArray4856[class112.anInt674] + this.anIntArray4843[class112.anInt674] * class111_Sub1.aFloat4687 + class111_Sub1.aFloat4683);
                    aClass87_657.anInt662 = (int)(class111_Sub1.aFloat4677 + (this.anIntArray4843[class112.anInt674] * class111_Sub1.aFloat4673 + (this.anIntArray4886[class112.anInt674] * class111_Sub1.aFloat4684 + this.anIntArray4856[class112.anInt674] * class111_Sub1.aFloat4676)));
                }
            }
            if (this.aClass35Array4863 != null) {
                for (int i = 0; i < this.aClass35Array4863.length; ++i) {
                    Class35 aClass35_328;
                    final Class35 class113 = aClass35_328 = this.aClass35Array4863[i];
                    if (class113.aClass35_328 != null) {
                        aClass35_328 = class113.aClass35_328;
                    }
                    if (class113.aClass111_334 != null) {
                        class113.aClass111_334.method2092(class111_Sub1);
                    }
                    else {
                        class113.aClass111_334 = class111_Sub1.method2102();
                    }
                    aClass35_328.anInt331 = (int)(class111_Sub1.aFloat4674 + (class111_Sub1.aFloat4680 * this.anIntArray4843[class113.anInt327] + (this.anIntArray4886[class113.anInt327] * class111_Sub1.aFloat4686 + this.anIntArray4856[class113.anInt327] * class111_Sub1.aFloat4679)));
                    aClass35_328.anInt330 = (int)(class111_Sub1.aFloat4675 * this.anIntArray4856[class113.anInt327] + class111_Sub1.aFloat4678 * this.anIntArray4886[class113.anInt327] + this.anIntArray4843[class113.anInt327] * class111_Sub1.aFloat4687 + class111_Sub1.aFloat4683);
                    aClass35_328.anInt337 = (int)(class111_Sub1.aFloat4677 + (class111_Sub1.aFloat4676 * this.anIntArray4856[class113.anInt327] + this.anIntArray4886[class113.anInt327] * class111_Sub1.aFloat4684 + this.anIntArray4843[class113.anInt327] * class111_Sub1.aFloat4673));
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.DB(" + ((class111 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void VA(final int n) {
        try {
            final int n2 = Class284_Sub2_Sub2.anIntArray6200[n];
            final int n3 = Class284_Sub2_Sub2.anIntArray6202[n];
            for (int n4 = 0; ~this.anInt4883 < ~n4; ++n4) {
                final int n5 = this.anIntArray4886[n4] * n3 + n2 * this.anIntArray4856[n4] >> 552246990;
                this.anIntArray4856[n4] = -(n2 * this.anIntArray4886[n4]) + this.anIntArray4856[n4] * n3 >> -424979762;
                this.anIntArray4886[n4] = n5;
            }
            if (this.aClass104_4889 != null) {
                this.aClass104_4889.anInterface16_902 = null;
            }
            this.aBoolean4847 = false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.VA(" + n + ')');
        }
    }
    
    @Override
    final void method2344(final int n, final int[] array, int anInt3800, int anInt3801, int anInt3802, final int n2, final boolean b) {
        try {
            final int i = array.length;
            if (n == 0) {
                anInt3801 <<= 4;
                anInt3800 <<= 4;
                anInt3802 <<= 4;
                Class78.anInt596 = 0;
                int n3 = 0;
                Class98_Sub40.anInt4196 = 0;
                Class76_Sub11.anInt3800 = 0;
                for (final int n4 : array) {
                    if (n4 < this.anIntArrayArray4888.length) {
                        final int[] array2 = this.anIntArrayArray4888[n4];
                        for (int n5 = 0; ~n5 > ~array2.length; ++n5) {
                            final int n6 = array2[n5];
                            Class76_Sub11.anInt3800 += this.anIntArray4886[n6];
                            Class98_Sub40.anInt4196 += this.anIntArray4856[n6];
                            ++n3;
                            Class78.anInt596 += this.anIntArray4843[n6];
                        }
                    }
                }
                if (n3 <= 0) {
                    Class76_Sub11.anInt3800 = anInt3800;
                    Class98_Sub40.anInt4196 = anInt3801;
                    Class78.anInt596 = anInt3802;
                }
                else {
                    Class98_Sub40.anInt4196 = Class98_Sub40.anInt4196 / n3 - -anInt3801;
                    Class78.anInt596 = Class78.anInt596 / n3 - -anInt3802;
                    Class76_Sub11.anInt3800 = Class76_Sub11.anInt3800 / n3 + anInt3800;
                }
            }
            else if (n == 1) {
                anInt3801 <<= 4;
                anInt3802 <<= 4;
                anInt3800 <<= 4;
                for (int n7 = 0; i > n7; ++n7) {
                    final int n8 = array[n7];
                    if (n8 < this.anIntArrayArray4888.length) {
                        final int[] array3 = this.anIntArrayArray4888[n8];
                        for (int n9 = 0; ~n9 > ~array3.length; ++n9) {
                            final int n10 = array3[n9];
                            final int[] anIntArray4886 = this.anIntArray4886;
                            final int n11 = n10;
                            anIntArray4886[n11] += anInt3800;
                            final int[] anIntArray4887 = this.anIntArray4856;
                            final int n12 = n10;
                            anIntArray4887[n12] += anInt3801;
                            final int[] anIntArray4888 = this.anIntArray4843;
                            final int n13 = n10;
                            anIntArray4888[n13] += anInt3802;
                        }
                    }
                }
            }
            else if (n == 2) {
                for (int n14 = 0; ~i < ~n14; ++n14) {
                    final int n15 = array[n14];
                    if (~this.anIntArrayArray4888.length < ~n15) {
                        final int[] array4 = this.anIntArrayArray4888[n15];
                        if ((0x1 & n2) == 0x0) {
                            for (int n16 = 0; array4.length > n16; ++n16) {
                                final int n17 = array4[n16];
                                final int[] anIntArray4889 = this.anIntArray4886;
                                final int n18 = n17;
                                anIntArray4889[n18] -= Class76_Sub11.anInt3800;
                                final int[] anIntArray4890 = this.anIntArray4856;
                                final int n19 = n17;
                                anIntArray4890[n19] -= Class98_Sub40.anInt4196;
                                final int[] anIntArray4891 = this.anIntArray4843;
                                final int n20 = n17;
                                anIntArray4891[n20] -= Class78.anInt596;
                                if (anInt3802 != 0) {
                                    final int n21 = Class284_Sub2_Sub2.anIntArray6200[anInt3802];
                                    final int n22 = Class284_Sub2_Sub2.anIntArray6202[anInt3802];
                                    final int n23 = 16383 + (n22 * this.anIntArray4886[n17] + this.anIntArray4856[n17] * n21) >> -551742322;
                                    this.anIntArray4856[n17] = 16383 + -(n21 * this.anIntArray4886[n17]) + n22 * this.anIntArray4856[n17] >> 33016206;
                                    this.anIntArray4886[n17] = n23;
                                }
                                if (anInt3800 != 0) {
                                    final int n24 = Class284_Sub2_Sub2.anIntArray6200[anInt3800];
                                    final int n25 = Class284_Sub2_Sub2.anIntArray6202[anInt3800];
                                    final int n26 = 16383 + -(this.anIntArray4843[n17] * n24) + n25 * this.anIntArray4856[n17] >> 955755278;
                                    this.anIntArray4843[n17] = 16383 + n24 * this.anIntArray4856[n17] - -(n25 * this.anIntArray4843[n17]) >> -1079794578;
                                    this.anIntArray4856[n17] = n26;
                                }
                                if (~anInt3801 != -1) {
                                    final int n27 = Class284_Sub2_Sub2.anIntArray6200[anInt3801];
                                    final int n28 = Class284_Sub2_Sub2.anIntArray6202[anInt3801];
                                    final int n29 = 16383 + (this.anIntArray4886[n17] * n28 + n27 * this.anIntArray4843[n17]) >> -716111698;
                                    this.anIntArray4843[n17] = 16383 + this.anIntArray4843[n17] * n28 + -(n27 * this.anIntArray4886[n17]) >> 935777710;
                                    this.anIntArray4886[n17] = n29;
                                }
                                final int[] anIntArray4892 = this.anIntArray4886;
                                final int n30 = n17;
                                anIntArray4892[n30] += Class76_Sub11.anInt3800;
                                final int[] anIntArray4893 = this.anIntArray4856;
                                final int n31 = n17;
                                anIntArray4893[n31] += Class98_Sub40.anInt4196;
                                final int[] anIntArray4894 = this.anIntArray4843;
                                final int n32 = n17;
                                anIntArray4894[n32] += Class78.anInt596;
                            }
                        }
                        else {
                            for (int n33 = 0; array4.length > n33; ++n33) {
                                final int n34 = array4[n33];
                                final int[] anIntArray4895 = this.anIntArray4886;
                                final int n35 = n34;
                                anIntArray4895[n35] -= Class76_Sub11.anInt3800;
                                final int[] anIntArray4896 = this.anIntArray4856;
                                final int n36 = n34;
                                anIntArray4896[n36] -= Class98_Sub40.anInt4196;
                                final int[] anIntArray4897 = this.anIntArray4843;
                                final int n37 = n34;
                                anIntArray4897[n37] -= Class78.anInt596;
                                if (anInt3800 != 0) {
                                    final int n38 = Class284_Sub2_Sub2.anIntArray6200[anInt3800];
                                    final int n39 = Class284_Sub2_Sub2.anIntArray6202[anInt3800];
                                    final int n40 = 16383 + (this.anIntArray4856[n34] * n39 - n38 * this.anIntArray4843[n34]) >> 687877998;
                                    this.anIntArray4843[n34] = 16383 + (this.anIntArray4856[n34] * n38 - -(this.anIntArray4843[n34] * n39)) >> -2103580978;
                                    this.anIntArray4856[n34] = n40;
                                }
                                if (anInt3802 != 0) {
                                    final int n41 = Class284_Sub2_Sub2.anIntArray6200[anInt3802];
                                    final int n42 = Class284_Sub2_Sub2.anIntArray6202[anInt3802];
                                    final int n43 = this.anIntArray4856[n34] * n41 - -(this.anIntArray4886[n34] * n42) + 16383 >> -1145146098;
                                    this.anIntArray4856[n34] = 16383 + (-(this.anIntArray4886[n34] * n41) + this.anIntArray4856[n34] * n42) >> 428947982;
                                    this.anIntArray4886[n34] = n43;
                                }
                                if (anInt3801 != 0) {
                                    final int n44 = Class284_Sub2_Sub2.anIntArray6200[anInt3801];
                                    final int n45 = Class284_Sub2_Sub2.anIntArray6202[anInt3801];
                                    final int n46 = n45 * this.anIntArray4886[n34] + (n44 * this.anIntArray4843[n34] + 16383) >> 1947306446;
                                    this.anIntArray4843[n34] = -(this.anIntArray4886[n34] * n44) + (this.anIntArray4843[n34] * n45 + 16383) >> -1064959730;
                                    this.anIntArray4886[n34] = n46;
                                }
                                final int[] anIntArray4898 = this.anIntArray4886;
                                final int n47 = n34;
                                anIntArray4898[n47] += Class76_Sub11.anInt3800;
                                final int[] anIntArray4899 = this.anIntArray4856;
                                final int n48 = n34;
                                anIntArray4899[n48] += Class98_Sub40.anInt4196;
                                final int[] anIntArray4900 = this.anIntArray4843;
                                final int n49 = n34;
                                anIntArray4900[n49] += Class78.anInt596;
                            }
                        }
                    }
                }
                if (b) {
                    for (int n50 = 0; ~i < ~n50; ++n50) {
                        final int n51 = array[n50];
                        if (n51 < this.anIntArrayArray4888.length) {
                            final int[] array5 = this.anIntArrayArray4888[n51];
                            for (int n52 = 0; ~n52 > ~array5.length; ++n52) {
                                final int n53 = array5[n52];
                                final int n54 = this.anIntArray4851[n53];
                                for (int n55 = this.anIntArray4851[1 + n53], n56 = n54; ~n56 > ~n55; ++n56) {
                                    final short n57 = (short)(this.aShortArray4887[n56] - 1);
                                    if (~n57 == 0x0) {
                                        break;
                                    }
                                    if (anInt3802 != 0) {
                                        final int n58 = Class284_Sub2_Sub2.anIntArray6200[anInt3802];
                                        final int n59 = Class284_Sub2_Sub2.anIntArray6202[anInt3802];
                                        final int n60 = 16383 + this.aShortArray4884[n57] * n58 + this.aShortArray4860[n57] * n59 >> 522988174;
                                        this.aShortArray4884[n57] = (short)(16383 + (this.aShortArray4884[n57] * n59 + -(this.aShortArray4860[n57] * n58)) >> -296680690);
                                        this.aShortArray4860[n57] = (short)n60;
                                    }
                                    if (anInt3800 != 0) {
                                        final int n61 = Class284_Sub2_Sub2.anIntArray6200[anInt3800];
                                        final int n62 = Class284_Sub2_Sub2.anIntArray6202[anInt3800];
                                        final int n63 = 16383 + (-(n61 * this.aShortArray4849[n57]) + n62 * this.aShortArray4884[n57]) >> 1602858094;
                                        this.aShortArray4849[n57] = (short)(this.aShortArray4849[n57] * n62 + (this.aShortArray4884[n57] * n61 + 16383) >> -468229682);
                                        this.aShortArray4884[n57] = (short)n63;
                                    }
                                    if (~anInt3801 != -1) {
                                        final int n64 = Class284_Sub2_Sub2.anIntArray6200[anInt3801];
                                        final int n65 = Class284_Sub2_Sub2.anIntArray6202[anInt3801];
                                        final int n66 = 16383 + this.aShortArray4849[n57] * n64 + this.aShortArray4860[n57] * n65 >> -852219346;
                                        this.aShortArray4849[n57] = (short)(16383 + n65 * this.aShortArray4849[n57] + -(this.aShortArray4860[n57] * n64) >> -1234967890);
                                        this.aShortArray4860[n57] = (short)n66;
                                    }
                                }
                            }
                        }
                    }
                    if (this.aClass104_4859 == null && this.aClass104_4854 != null) {
                        this.aClass104_4854.anInterface16_902 = null;
                    }
                    if (this.aClass104_4859 != null) {
                        this.aClass104_4859.anInterface16_902 = null;
                    }
                }
            }
            else if (n == 3) {
                for (int n67 = 0; ~i < ~n67; ++n67) {
                    final int n68 = array[n67];
                    if (n68 < this.anIntArrayArray4888.length) {
                        final int[] array6 = this.anIntArrayArray4888[n68];
                        for (int n69 = 0; ~n69 > ~array6.length; ++n69) {
                            final int n70 = array6[n69];
                            final int[] anIntArray4901 = this.anIntArray4886;
                            final int n71 = n70;
                            anIntArray4901[n71] -= Class76_Sub11.anInt3800;
                            final int[] anIntArray4902 = this.anIntArray4856;
                            final int n72 = n70;
                            anIntArray4902[n72] -= Class98_Sub40.anInt4196;
                            final int[] anIntArray4903 = this.anIntArray4843;
                            final int n73 = n70;
                            anIntArray4903[n73] -= Class78.anInt596;
                            this.anIntArray4886[n70] = anInt3800 * this.anIntArray4886[n70] >> -1733137977;
                            this.anIntArray4856[n70] = anInt3801 * this.anIntArray4856[n70] >> 1319952583;
                            this.anIntArray4843[n70] = this.anIntArray4843[n70] * anInt3802 >> 1161471879;
                            final int[] anIntArray4904 = this.anIntArray4886;
                            final int n74 = n70;
                            anIntArray4904[n74] += Class76_Sub11.anInt3800;
                            final int[] anIntArray4905 = this.anIntArray4856;
                            final int n75 = n70;
                            anIntArray4905[n75] += Class98_Sub40.anInt4196;
                            final int[] anIntArray4906 = this.anIntArray4843;
                            final int n76 = n70;
                            anIntArray4906[n76] += Class78.anInt596;
                        }
                    }
                }
            }
            else if (~n == 0xFFFFFFFA) {
                if (this.anIntArrayArray4870 != null) {
                    for (final int n77 : array) {
                        if (~n77 > ~this.anIntArrayArray4870.length) {
                            final int[] array7 = this.anIntArrayArray4870[n77];
                            for (int n78 = 0; ~array7.length < ~n78; ++n78) {
                                final int n79 = array7[n78];
                                int n80 = anInt3800 * 8 + (0xFF & this.aByteArray4882[n79]);
                                if (n80 >= 0) {
                                    if (n80 > 255) {
                                        n80 = 255;
                                    }
                                }
                                else {
                                    n80 = 0;
                                }
                                this.aByteArray4882[n79] = (byte)n80;
                            }
                            if (array7.length > 0 && this.aClass104_4854 != null) {
                                this.aClass104_4854.anInterface16_902 = null;
                            }
                        }
                    }
                    if (this.aClass249Array4877 != null) {
                        for (int n81 = 0; this.anInt4866 > n81; ++n81) {
                            final Class249 class249 = this.aClass249Array4877[n81];
                            final Class219 class250 = this.aClass219Array4861[n81];
                            class250.anInt1643 = ((0xFFFFFF & class250.anInt1643) | 255 - (0xFF & this.aByteArray4882[class249.anInt1905]) << -836739592);
                        }
                    }
                }
            }
            else if (n == 7) {
                if (this.anIntArrayArray4870 != null) {
                    for (int n82 = 0; ~i < ~n82; ++n82) {
                        final int n83 = array[n82];
                        if (~this.anIntArrayArray4870.length < ~n83) {
                            final int[] array8 = this.anIntArrayArray4870[n83];
                            for (int n84 = 0; ~n84 > ~array8.length; ++n84) {
                                final int n85 = array8[n84];
                                final int n86 = this.aShortArray4842[n85] & 0xFFFF;
                                final int n87 = (n86 & 0xFEC7) >> -1874195350;
                                final int n88 = (0x3D9 & n86) >> -509954521;
                                final int n89 = 0x7F & n86;
                                int n90 = n88 + anInt3801 / 4;
                                final int n91 = 0x3F & anInt3800 + n87;
                                int n92 = n89 + anInt3802;
                                if (~n90 <= -1) {
                                    if (n90 > 7) {
                                        n90 = 7;
                                    }
                                }
                                else {
                                    n90 = 0;
                                }
                                if (n92 < 0) {
                                    n92 = 0;
                                }
                                else if (~n92 < -128) {
                                    n92 = 127;
                                }
                                this.aShortArray4842[n85] = (short)Class41.method366(n92, Class41.method366(n91 << 125681194, n90 << -23400089));
                            }
                            if (array8.length > 0 && this.aClass104_4854 != null) {
                                this.aClass104_4854.anInterface16_902 = null;
                            }
                        }
                    }
                    if (this.aClass249Array4877 != null) {
                        for (int l = 0; l < this.anInt4866; ++l) {
                            final Class249 class251 = this.aClass249Array4877[l];
                            final Class219 class252 = this.aClass219Array4861[l];
                            class252.anInt1643 = ((0xFF000000 & class252.anInt1643) | (Class208.anIntArray1579[0xFFFF & this.aShortArray4842[class251.anInt1905]] & 0xFFFFFF));
                        }
                    }
                }
            }
            else if (n == 8) {
                if (this.anIntArrayArray4875 != null) {
                    for (int n93 = 0; ~n93 > ~i; ++n93) {
                        final int n94 = array[n93];
                        if (this.anIntArrayArray4875.length > n94) {
                            final int[] array9 = this.anIntArrayArray4875[n94];
                            for (int n95 = 0; array9.length > n95; ++n95) {
                                final Class219 class254;
                                final Class219 class253 = class254 = this.aClass219Array4861[array9[n95]];
                                class254.anInt1644 += anInt3801;
                                final Class219 class255 = class253;
                                class255.anInt1638 += anInt3800;
                            }
                        }
                    }
                }
            }
            else if (n == 10) {
                if (this.anIntArrayArray4875 != null) {
                    for (int n96 = 0; i > n96; ++n96) {
                        final int n97 = array[n96];
                        if (~n97 > ~this.anIntArrayArray4875.length) {
                            final int[] array10 = this.anIntArrayArray4875[n97];
                            for (int n98 = 0; array10.length > n98; ++n98) {
                                final Class219 class256 = this.aClass219Array4861[array10[n98]];
                                class256.anInt1637 = anInt3800 * class256.anInt1637 >> -2059272249;
                                class256.anInt1645 = class256.anInt1645 * anInt3801 >> 883722311;
                            }
                        }
                    }
                }
            }
            else if (~n == 0xFFFFFFF6 && this.anIntArrayArray4875 != null) {
                for (int n99 = 0; ~n99 > ~i; ++n99) {
                    final int n100 = array[n99];
                    if (n100 < this.anIntArrayArray4875.length) {
                        final int[] array11 = this.anIntArrayArray4875[n100];
                        for (int n101 = 0; n101 < array11.length; ++n101) {
                            final Class219 class257 = this.aClass219Array4861[array11[n101]];
                            class257.anInt1639 = (0x3FFF & anInt3800 + class257.anInt1639);
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.BA(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + anInt3800 + ',' + anInt3801 + ',' + anInt3802 + ',' + n2 + ',' + b + ')');
        }
    }
    
    @Override
    final void method2331(final Class111 class111, final int n, final boolean b) {
        try {
            if (this.aShortArray4841 != null) {
                final int[] array = new int[3];
                for (int n2 = 0; ~this.anInt4883 < ~n2; ++n2) {
                    if ((n & this.aShortArray4841[n2]) != 0x0) {
                        if (!b) {
                            class111.method2103(this.anIntArray4886[n2], this.anIntArray4856[n2], this.anIntArray4843[n2], array);
                        }
                        else {
                            class111.method2096(this.anIntArray4886[n2], this.anIntArray4856[n2], this.anIntArray4843[n2], array);
                        }
                        this.anIntArray4886[n2] = array[0];
                        this.anIntArray4856[n2] = array[1];
                        this.anIntArray4843[n2] = array[2];
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.za(" + ((class111 != null) ? "{...}" : "null") + ',' + n + ',' + b + ')');
        }
    }
    
    @Override
    final boolean NA() {
        try {
            if (this.anIntArrayArray4888 == null) {
                return false;
            }
            for (int n = 0; ~this.anInt4893 < ~n; ++n) {
                final int[] anIntArray4886 = this.anIntArray4886;
                final int n2 = n;
                anIntArray4886[n2] <<= 4;
                final int[] anIntArray4887 = this.anIntArray4856;
                final int n3 = n;
                anIntArray4887[n3] <<= 4;
                final int[] anIntArray4888 = this.anIntArray4843;
                final int n4 = n;
                anIntArray4888[n4] <<= 4;
            }
            Class98_Sub40.anInt4196 = 0;
            Class78.anInt596 = 0;
            Class76_Sub11.anInt3800 = 0;
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.NA()");
        }
    }
    
    @Override
    final void O(final int n, final int n2, final int n3) {
        try {
            for (int n4 = 0; ~n4 > ~this.anInt4883; ++n4) {
                if (n != 128) {
                    this.anIntArray4886[n4] = this.anIntArray4886[n4] * n >> 831042567;
                }
                if (n2 != 128) {
                    this.anIntArray4856[n4] = this.anIntArray4856[n4] * n2 >> 907250759;
                }
                if (n3 != 128) {
                    this.anIntArray4843[n4] = this.anIntArray4843[n4] * n3 >> 390930887;
                }
            }
            if (this.aClass104_4889 != null) {
                this.aClass104_4889.anInterface16_902 = null;
            }
            this.aBoolean4847 = false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.O(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final r ba(final r r) {
        try {
            if (~this.anInt4846 == -1) {
                return null;
            }
            if (!this.aBoolean4847) {
                this.method2380(-107);
            }
            int n;
            int n2;
            if (~this.aHa_Sub1_4865.anInt4398 >= -1) {
                n = this.aShort4845 + -(this.aShort4868 * this.aHa_Sub1_4865.anInt4398 >> 1213595304) >> this.aHa_Sub1_4865.anInt4319;
                n2 = this.aShort4862 - (this.aShort4879 * this.aHa_Sub1_4865.anInt4398 >> -212745912) >> this.aHa_Sub1_4865.anInt4319;
            }
            else {
                n = -(this.aShort4879 * this.aHa_Sub1_4865.anInt4398 >> -1677247256) + this.aShort4845 >> this.aHa_Sub1_4865.anInt4319;
                n2 = -(this.aShort4868 * this.aHa_Sub1_4865.anInt4398 >> 2093540680) + this.aShort4862 >> this.aHa_Sub1_4865.anInt4319;
            }
            int n3;
            int n4;
            if (~this.aHa_Sub1_4865.anInt4377 >= -1) {
                n3 = this.aShort4892 - (this.aHa_Sub1_4865.anInt4377 * this.aShort4868 >> -751195736) >> this.aHa_Sub1_4865.anInt4319;
                n4 = this.aShort4891 + -(this.aHa_Sub1_4865.anInt4377 * this.aShort4879 >> -1860997272) >> this.aHa_Sub1_4865.anInt4319;
            }
            else {
                n3 = this.aShort4892 - (this.aShort4879 * this.aHa_Sub1_4865.anInt4377 >> -1732321272) >> this.aHa_Sub1_4865.anInt4319;
                n4 = -(this.aShort4868 * this.aHa_Sub1_4865.anInt4377 >> -759437880) + this.aShort4891 >> this.aHa_Sub1_4865.anInt4319;
            }
            final int n5 = -n + (n2 + 1);
            final int n6 = -n3 + n4 + 1;
            final r_Sub1 r_Sub1 = (r_Sub1)r;
            r_Sub1 r_Sub2;
            if (r_Sub1 == null || !r_Sub1.method1647(n6, (byte)(-125), n5)) {
                r_Sub2 = new r_Sub1(this.aHa_Sub1_4865, n5, n6);
            }
            else {
                r_Sub2 = r_Sub1;
                r_Sub2.method1646(92);
            }
            r_Sub2.method1643(n, n4, n2, n3, -1);
            this.method2386(r_Sub2, 0);
            return r_Sub2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.ba(" + ((r != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void I(final int n, final int[] array, int anInt3800, int anInt3801, int anInt3802, final boolean b, final int n2, final int[] array2) {
        try {
            final int i = array.length;
            if (n == 0) {
                anInt3801 <<= 4;
                anInt3802 <<= 4;
                anInt3800 <<= 4;
                Class76_Sub11.anInt3800 = 0;
                Class78.anInt596 = 0;
                int n3 = 0;
                Class98_Sub40.anInt4196 = 0;
                for (int n4 = 0; ~n4 > ~i; ++n4) {
                    final int n5 = array[n4];
                    if (n5 < this.anIntArrayArray4888.length) {
                        final int[] array3 = this.anIntArrayArray4888[n5];
                        for (int n6 = 0; array3.length > n6; ++n6) {
                            final int n7 = array3[n6];
                            if (this.aShortArray4841 == null || ~(n2 & this.aShortArray4841[n7]) != -1) {
                                Class76_Sub11.anInt3800 += this.anIntArray4886[n7];
                                Class98_Sub40.anInt4196 += this.anIntArray4856[n7];
                                ++n3;
                                Class78.anInt596 += this.anIntArray4843[n7];
                            }
                        }
                    }
                }
                if (~n3 >= -1) {
                    Class76_Sub11.anInt3800 = anInt3800;
                    Class98_Sub40.anInt4196 = anInt3801;
                    Class78.anInt596 = anInt3802;
                }
                else {
                    Class98_Sub40.anInt4196 = anInt3801 + Class98_Sub40.anInt4196 / n3;
                    Class76_Sub11.anInt3800 = anInt3800 + Class76_Sub11.anInt3800 / n3;
                    Class379.aBoolean3192 = true;
                    Class78.anInt596 = Class78.anInt596 / n3 + anInt3802;
                }
            }
            else if (n == 1) {
                if (array2 != null) {
                    final int n8 = anInt3802 * array2[2] + array2[0] * anInt3800 - -(anInt3801 * array2[1]) + 8192 >> -1369318770;
                    final int n9 = anInt3800 * array2[3] - (-(array2[4] * anInt3801) - array2[5] * anInt3802) + 8192 >> -809105682;
                    anInt3802 = anInt3800 * array2[6] - (-(array2[7] * anInt3801) + (-(anInt3802 * array2[8]) - 8192)) >> 1496036366;
                    anInt3801 = n9;
                    anInt3800 = n8;
                }
                anInt3802 <<= 4;
                anInt3801 <<= 4;
                anInt3800 <<= 4;
                for (int n10 = 0; ~i < ~n10; ++n10) {
                    final int n11 = array[n10];
                    if (n11 < this.anIntArrayArray4888.length) {
                        final int[] array4 = this.anIntArrayArray4888[n11];
                        for (int j = 0; j < array4.length; ++j) {
                            final int n12 = array4[j];
                            if (this.aShortArray4841 == null || (this.aShortArray4841[n12] & n2) != 0x0) {
                                final int[] anIntArray4886 = this.anIntArray4886;
                                final int n13 = n12;
                                anIntArray4886[n13] += anInt3800;
                                final int[] anIntArray4887 = this.anIntArray4856;
                                final int n14 = n12;
                                anIntArray4887[n14] += anInt3801;
                                final int[] anIntArray4888 = this.anIntArray4843;
                                final int n15 = n12;
                                anIntArray4888[n15] += anInt3802;
                            }
                        }
                    }
                }
            }
            else if (n == 2) {
                if (array2 != null) {
                    final int n16 = array2[9] << 1804536932;
                    final int n17 = array2[10] << 70535332;
                    final int n18 = array2[11] << 1336128228;
                    final int n19 = array2[12] << 122316772;
                    final int n20 = array2[13] << 1984453732;
                    final int n21 = array2[14] << 75103652;
                    if (Class379.aBoolean3192) {
                        final int n22 = 8192 + (array2[6] * Class78.anInt596 + Class98_Sub40.anInt4196 * array2[3] + Class76_Sub11.anInt3800 * array2[0]) >> -260387954;
                        final int anInt3803 = (array2[7] * Class78.anInt596 + (array2[4] * Class98_Sub40.anInt4196 + array2[1] * Class76_Sub11.anInt3800) + 8192 >> -1260143058) + n20;
                        final int n23 = 8192 + (array2[8] * Class78.anInt596 + array2[5] * Class98_Sub40.anInt4196 + array2[2] * Class76_Sub11.anInt3800) >> -1756543602;
                        Class76_Sub11.anInt3800 = n22 + n19;
                        Class98_Sub40.anInt4196 = anInt3803;
                        final int anInt3804 = n23 + n21;
                        Class379.aBoolean3192 = false;
                        Class78.anInt596 = anInt3804;
                    }
                    final int[] array5 = new int[9];
                    final int n24 = Class284_Sub2_Sub2.anIntArray6202[anInt3800];
                    final int n25 = Class284_Sub2_Sub2.anIntArray6200[anInt3800];
                    final int n26 = Class284_Sub2_Sub2.anIntArray6202[anInt3801];
                    final int n27 = Class284_Sub2_Sub2.anIntArray6200[anInt3801];
                    final int n28 = Class284_Sub2_Sub2.anIntArray6202[anInt3802];
                    final int n29 = Class284_Sub2_Sub2.anIntArray6200[anInt3802];
                    final int n30 = n25 * n28 + 8192 >> -1956467730;
                    final int n31 = n25 * n29 + 8192 >> 1810406222;
                    array5[1] = 8192 + (n30 * n27 + -n26 * n29) >> -66052562;
                    array5[4] = n28 * n24 + 8192 >> 1658775406;
                    array5[8] = 8192 + n24 * n26 >> 2032809038;
                    array5[7] = n30 * n26 + (n29 * n27 + 8192) >> 1319882350;
                    array5[5] = -n25;
                    array5[2] = n27 * n24 + 8192 >> -430059570;
                    array5[6] = n26 * n31 + -n27 * n28 + 8192 >> 44494510;
                    array5[3] = n29 * n24 + 8192 >> 210044366;
                    array5[0] = n27 * n31 + n28 * n26 + 8192 >> 969621742;
                    final int n32 = 8192 + -Class78.anInt596 * array5[2] + (-Class76_Sub11.anInt3800 * array5[0] + array5[1] * -Class98_Sub40.anInt4196) >> -1090313586;
                    final int n33 = 8192 + array5[3] * -Class76_Sub11.anInt3800 - (-(-Class98_Sub40.anInt4196 * array5[4]) - -Class78.anInt596 * array5[5]) >> 699878862;
                    final int n34 = array5[6] * -Class76_Sub11.anInt3800 - -(-Class98_Sub40.anInt4196 * array5[7]) - (-(array5[8] * -Class78.anInt596) - 8192) >> -1007244018;
                    final int n35 = n32 + Class76_Sub11.anInt3800;
                    final int n36 = Class98_Sub40.anInt4196 + n33;
                    final int n37 = Class78.anInt596 + n34;
                    final int[] array6 = new int[9];
                    for (int n38 = 0; ~n38 > -4; ++n38) {
                        for (int n39 = 0; ~n39 > -4; ++n39) {
                            int n40 = 0;
                            for (int n41 = 0; ~n41 > -4; ++n41) {
                                n40 += array2[n41 + 3 * n39] * array5[n41 + n38 * 3];
                            }
                            array6[n39 + n38 * 3] = n40 + 8192 >> -299473234;
                        }
                    }
                    final int n42 = array5[0] * n19 + (n20 * array5[1] - (-(array5[2] * n21) - 8192)) >> -529690482;
                    final int n43 = n21 * array5[5] + (array5[3] * n19 + (n20 * array5[4] + 8192)) >> 2084162126;
                    final int n44 = 8192 + (n20 * array5[7] + (n19 * array5[6] + array5[8] * n21)) >> -1963320882;
                    final int n45 = n42 + n35;
                    final int n46 = n43 + n36;
                    final int n47 = n44 + n37;
                    final int[] array7 = new int[9];
                    for (int n48 = 0; ~n48 > -4; ++n48) {
                        for (int n49 = 0; ~n49 > -4; ++n49) {
                            int n50 = 0;
                            for (int k = 0; k < 3; ++k) {
                                n50 += array2[k + 3 * n48] * array6[k * 3 + n49];
                            }
                            array7[n49 + 3 * n48] = 8192 + n50 >> -1789420530;
                        }
                    }
                    final int n51 = array2[0] * n45 + (array2[1] * n46 - -(array2[2] * n47)) + 8192 >> 1589548206;
                    final int n52 = 8192 + (n47 * array2[5] + array2[3] * n45) + n46 * array2[4] >> -1450281138;
                    final int n53 = n51 + n16;
                    final int n54 = n52 + n17;
                    final int n55 = (8192 + n47 * array2[8] + n46 * array2[7] + n45 * array2[6] >> -514594002) + n18;
                    for (int n56 = 0; i > n56; ++n56) {
                        final int n57 = array[n56];
                        if (~n57 > ~this.anIntArrayArray4888.length) {
                            final int[] array8 = this.anIntArrayArray4888[n57];
                            for (int n58 = 0; ~n58 > ~array8.length; ++n58) {
                                final int n59 = array8[n58];
                                if (this.aShortArray4841 == null || ~(this.aShortArray4841[n59] & n2) != -1) {
                                    final int n60 = array7[2] * this.anIntArray4843[n59] + array7[1] * this.anIntArray4856[n59] + (this.anIntArray4886[n59] * array7[0] + 8192) >> -574783666;
                                    final int n61 = 8192 + (this.anIntArray4843[n59] * array7[5] + this.anIntArray4886[n59] * array7[3]) - -(this.anIntArray4856[n59] * array7[4]) >> -257899250;
                                    final int n62 = array7[8] * this.anIntArray4843[n59] + this.anIntArray4856[n59] * array7[7] + (this.anIntArray4886[n59] * array7[6] + 8192) >> -1400239762;
                                    final int n63 = n61 + n54;
                                    final int n64 = n60 + n53;
                                    final int n65 = n62 + n55;
                                    this.anIntArray4886[n59] = n64;
                                    this.anIntArray4856[n59] = n63;
                                    this.anIntArray4843[n59] = n65;
                                }
                            }
                        }
                    }
                }
                else {
                    for (int n66 = 0; i > n66; ++n66) {
                        final int n67 = array[n66];
                        if (this.anIntArrayArray4888.length > n67) {
                            final int[] array9 = this.anIntArrayArray4888[n67];
                            for (int n68 = 0; ~n68 > ~array9.length; ++n68) {
                                final int n69 = array9[n68];
                                if (this.aShortArray4841 == null || ~(n2 & this.aShortArray4841[n69]) != -1) {
                                    final int[] anIntArray4889 = this.anIntArray4886;
                                    final int n70 = n69;
                                    anIntArray4889[n70] -= Class76_Sub11.anInt3800;
                                    final int[] anIntArray4890 = this.anIntArray4856;
                                    final int n71 = n69;
                                    anIntArray4890[n71] -= Class98_Sub40.anInt4196;
                                    final int[] anIntArray4891 = this.anIntArray4843;
                                    final int n72 = n69;
                                    anIntArray4891[n72] -= Class78.anInt596;
                                    if (~anInt3802 != -1) {
                                        final int n73 = Class284_Sub2_Sub2.anIntArray6200[anInt3802];
                                        final int n74 = Class284_Sub2_Sub2.anIntArray6202[anInt3802];
                                        final int n75 = 16383 + (this.anIntArray4856[n69] * n73 + this.anIntArray4886[n69] * n74) >> -1906016466;
                                        this.anIntArray4856[n69] = 16383 + (-(n73 * this.anIntArray4886[n69]) + this.anIntArray4856[n69] * n74) >> -1259465906;
                                        this.anIntArray4886[n69] = n75;
                                    }
                                    if (anInt3800 != 0) {
                                        final int n76 = Class284_Sub2_Sub2.anIntArray6200[anInt3800];
                                        final int n77 = Class284_Sub2_Sub2.anIntArray6202[anInt3800];
                                        final int n78 = -(n76 * this.anIntArray4843[n69]) + (n77 * this.anIntArray4856[n69] + 16383) >> 1084813326;
                                        this.anIntArray4843[n69] = 16383 + n76 * this.anIntArray4856[n69] - -(n77 * this.anIntArray4843[n69]) >> 1681901230;
                                        this.anIntArray4856[n69] = n78;
                                    }
                                    if (anInt3801 != 0) {
                                        final int n79 = Class284_Sub2_Sub2.anIntArray6200[anInt3801];
                                        final int n80 = Class284_Sub2_Sub2.anIntArray6202[anInt3801];
                                        final int n81 = 16383 + (n79 * this.anIntArray4843[n69] - -(this.anIntArray4886[n69] * n80)) >> 2144723758;
                                        this.anIntArray4843[n69] = -(n79 * this.anIntArray4886[n69]) + (n80 * this.anIntArray4843[n69] + 16383) >> -1849580242;
                                        this.anIntArray4886[n69] = n81;
                                    }
                                    final int[] anIntArray4892 = this.anIntArray4886;
                                    final int n82 = n69;
                                    anIntArray4892[n82] += Class76_Sub11.anInt3800;
                                    final int[] anIntArray4893 = this.anIntArray4856;
                                    final int n83 = n69;
                                    anIntArray4893[n83] += Class98_Sub40.anInt4196;
                                    final int[] anIntArray4894 = this.anIntArray4843;
                                    final int n84 = n69;
                                    anIntArray4894[n84] += Class78.anInt596;
                                }
                            }
                        }
                    }
                    if (b) {
                        for (int n85 = 0; ~n85 > ~i; ++n85) {
                            final int n86 = array[n85];
                            if (n86 < this.anIntArrayArray4888.length) {
                                final int[] array10 = this.anIntArrayArray4888[n86];
                                for (int n87 = 0; ~array10.length < ~n87; ++n87) {
                                    final int n88 = array10[n87];
                                    if (this.aShortArray4841 == null || ~(this.aShortArray4841[n88] & n2) != -1) {
                                        final int n89 = this.anIntArray4851[n88];
                                        for (int n90 = this.anIntArray4851[1 + n88], n91 = n89; ~n91 > ~n90; ++n91) {
                                            final short n92 = (short)(this.aShortArray4887[n91] - 1);
                                            if (~n92 == 0x0) {
                                                break;
                                            }
                                            if (~anInt3802 != -1) {
                                                final int n93 = Class284_Sub2_Sub2.anIntArray6200[anInt3802];
                                                final int n94 = Class284_Sub2_Sub2.anIntArray6202[anInt3802];
                                                final int n95 = 16383 + (n94 * this.aShortArray4860[n92] + n93 * this.aShortArray4884[n92]) >> -154209298;
                                                this.aShortArray4884[n92] = (short)(16383 + (n94 * this.aShortArray4884[n92] + -(this.aShortArray4860[n92] * n93)) >> -1945765714);
                                                this.aShortArray4860[n92] = (short)n95;
                                            }
                                            if (~anInt3800 != -1) {
                                                final int n96 = Class284_Sub2_Sub2.anIntArray6200[anInt3800];
                                                final int n97 = Class284_Sub2_Sub2.anIntArray6202[anInt3800];
                                                final int n98 = 16383 + -(this.aShortArray4849[n92] * n96) + this.aShortArray4884[n92] * n97 >> -1773313042;
                                                this.aShortArray4849[n92] = (short)(16383 + this.aShortArray4884[n92] * n96 + n97 * this.aShortArray4849[n92] >> -2139001330);
                                                this.aShortArray4884[n92] = (short)n98;
                                            }
                                            if (~anInt3801 != -1) {
                                                final int n99 = Class284_Sub2_Sub2.anIntArray6200[anInt3801];
                                                final int n100 = Class284_Sub2_Sub2.anIntArray6202[anInt3801];
                                                final int n101 = 16383 + this.aShortArray4849[n92] * n99 + n100 * this.aShortArray4860[n92] >> -541471954;
                                                this.aShortArray4849[n92] = (short)(n100 * this.aShortArray4849[n92] + (-(this.aShortArray4860[n92] * n99) + 16383) >> 1560966254);
                                                this.aShortArray4860[n92] = (short)n101;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (this.aClass104_4859 == null && this.aClass104_4854 != null) {
                            this.aClass104_4854.anInterface16_902 = null;
                        }
                        if (this.aClass104_4859 != null) {
                            this.aClass104_4859.anInterface16_902 = null;
                        }
                    }
                }
            }
            else if (n == 3) {
                if (array2 == null) {
                    for (int n102 = 0; i > n102; ++n102) {
                        final int n103 = array[n102];
                        if (~this.anIntArrayArray4888.length < ~n103) {
                            final int[] array11 = this.anIntArrayArray4888[n103];
                            for (int n104 = 0; ~array11.length < ~n104; ++n104) {
                                final int n105 = array11[n104];
                                if (this.aShortArray4841 == null || (n2 & this.aShortArray4841[n105]) != 0x0) {
                                    final int[] anIntArray4895 = this.anIntArray4886;
                                    final int n106 = n105;
                                    anIntArray4895[n106] -= Class76_Sub11.anInt3800;
                                    final int[] anIntArray4896 = this.anIntArray4856;
                                    final int n107 = n105;
                                    anIntArray4896[n107] -= Class98_Sub40.anInt4196;
                                    final int[] anIntArray4897 = this.anIntArray4843;
                                    final int n108 = n105;
                                    anIntArray4897[n108] -= Class78.anInt596;
                                    this.anIntArray4886[n105] = this.anIntArray4886[n105] * anInt3800 >> 1015972295;
                                    this.anIntArray4856[n105] = this.anIntArray4856[n105] * anInt3801 >> -176902617;
                                    this.anIntArray4843[n105] = anInt3802 * this.anIntArray4843[n105] >> 364093863;
                                    final int[] anIntArray4898 = this.anIntArray4886;
                                    final int n109 = n105;
                                    anIntArray4898[n109] += Class76_Sub11.anInt3800;
                                    final int[] anIntArray4899 = this.anIntArray4856;
                                    final int n110 = n105;
                                    anIntArray4899[n110] += Class98_Sub40.anInt4196;
                                    final int[] anIntArray4900 = this.anIntArray4843;
                                    final int n111 = n105;
                                    anIntArray4900[n111] += Class78.anInt596;
                                }
                            }
                        }
                    }
                }
                else {
                    final int n112 = array2[9] << 868159748;
                    final int n113 = array2[10] << -1220373788;
                    final int n114 = array2[11] << -468521148;
                    final int n115 = array2[12] << -277205308;
                    final int n116 = array2[13] << -1570544540;
                    final int n117 = array2[14] << 916366532;
                    if (Class379.aBoolean3192) {
                        final int n118 = 8192 + (array2[0] * Class76_Sub11.anInt3800 - (-(array2[3] * Class98_Sub40.anInt4196) - array2[6] * Class78.anInt596)) >> -183007474;
                        final int n119 = Class78.anInt596 * array2[7] + Class76_Sub11.anInt3800 * array2[1] + array2[4] * Class98_Sub40.anInt4196 + 8192 >> -390869586;
                        final int anInt3805 = n118 + n115;
                        final int n120 = array2[5] * Class98_Sub40.anInt4196 + (array2[2] * Class76_Sub11.anInt3800 - -(array2[8] * Class78.anInt596)) + 8192 >> -780469330;
                        final int anInt3806 = n119 + n116;
                        final int anInt3807 = n120 + n117;
                        Class76_Sub11.anInt3800 = anInt3805;
                        Class98_Sub40.anInt4196 = anInt3806;
                        Class78.anInt596 = anInt3807;
                        Class379.aBoolean3192 = false;
                    }
                    final int n121 = anInt3800 << -1994066449 >> 2020993959;
                    final int n122 = anInt3801 << -359400753 >> -1057023961;
                    final int n123 = anInt3802 << 351861295 >> 279282855;
                    final int n124 = n121 * -Class76_Sub11.anInt3800 + 8192 >> -703746386;
                    final int n125 = 8192 + n122 * -Class98_Sub40.anInt4196 >> 985376366;
                    final int n126 = 8192 + -Class78.anInt596 * n123 >> 1544249166;
                    final int n127 = n124 + Class76_Sub11.anInt3800;
                    final int n128 = Class98_Sub40.anInt4196 + n125;
                    final int n129 = n126 + Class78.anInt596;
                    final int[] array12 = { array2[0] * n121 + 8192 >> 1681969838, array2[3] * n121 + 8192 >> -1106414194, 8192 + n121 * array2[6] >> -930718354, 8192 + array2[1] * n122 >> -1263801714, array2[4] * n122 + 8192 >> -915008530, n122 * array2[7] + 8192 >> -2135603986, 8192 + n123 * array2[2] >> 648234030, n123 * array2[5] + 8192 >> 568971278, 8192 + n123 * array2[8] >> 2111175182 };
                    final int n130 = n121 * n115 + 8192 >> -2075031634;
                    final int n131 = 8192 + n122 * n116 >> 1259859758;
                    final int n132 = n117 * n123 + 8192 >> 1650775566;
                    final int n133 = n131 + n128;
                    final int n134 = n130 + n127;
                    final int n135 = n132 + n129;
                    final int[] array13 = new int[9];
                    for (int n136 = 0; ~n136 > -4; ++n136) {
                        for (int n137 = 0; ~n137 > -4; ++n137) {
                            int n138 = 0;
                            for (int n139 = 0; ~n139 > -4; ++n139) {
                                n138 += array12[3 * n139 + n137] * array2[3 * n136 + n139];
                            }
                            array13[3 * n136 + n137] = n138 + 8192 >> -453481650;
                        }
                    }
                    final int n140 = 8192 + n133 * array2[1] + array2[0] * n134 - -(n135 * array2[2]) >> 1333433646;
                    final int n141 = array2[3] * n134 + (array2[4] * n133 + n135 * array2[5] + 8192) >> -125666770;
                    final int n142 = n133 * array2[7] + n134 * array2[6] - -(array2[8] * n135) + 8192 >> -860132626;
                    final int n143 = n140 + n112;
                    final int n144 = n141 + n113;
                    final int n145 = n142 + n114;
                    for (final int n146 : array) {
                        if (~this.anIntArrayArray4888.length < ~n146) {
                            final int[] array14 = this.anIntArrayArray4888[n146];
                            for (int n147 = 0; array14.length > n147; ++n147) {
                                final int n148 = array14[n147];
                                if (this.aShortArray4841 == null || (n2 & this.aShortArray4841[n148]) != 0x0) {
                                    final int n149 = array13[2] * this.anIntArray4843[n148] + (this.anIntArray4886[n148] * array13[0] + this.anIntArray4856[n148] * array13[1]) + 8192 >> -1957392594;
                                    final int n150 = this.anIntArray4843[n148] * array13[5] + (this.anIntArray4886[n148] * array13[3] + this.anIntArray4856[n148] * array13[4] + 8192) >> -1223485906;
                                    final int n151 = this.anIntArray4856[n148] * array13[7] + (this.anIntArray4886[n148] * array13[6] + (array13[8] * this.anIntArray4843[n148] + 8192)) >> 1209563310;
                                    final int n152 = n149 + n143;
                                    final int n153 = n150 + n144;
                                    final int n154 = n151 + n145;
                                    this.anIntArray4886[n148] = n152;
                                    this.anIntArray4856[n148] = n153;
                                    this.anIntArray4843[n148] = n154;
                                }
                            }
                        }
                    }
                }
            }
            else if (n == 5) {
                if (this.anIntArrayArray4870 != null) {
                    for (int n155 = 0; ~i < ~n155; ++n155) {
                        final int n156 = array[n155];
                        if (~this.anIntArrayArray4870.length < ~n156) {
                            final int[] array15 = this.anIntArrayArray4870[n156];
                            for (int n157 = 0; ~n157 > ~array15.length; ++n157) {
                                final int n158 = array15[n157];
                                if (this.aShortArray4864 == null || ~(this.aShortArray4864[n158] & n2) != -1) {
                                    int n159 = anInt3800 * 8 + (0xFF & this.aByteArray4882[n158]);
                                    if (~n159 > -1) {
                                        n159 = 0;
                                    }
                                    else if (n159 > 255) {
                                        n159 = 255;
                                    }
                                    this.aByteArray4882[n158] = (byte)n159;
                                    if (this.aClass104_4854 != null) {
                                        this.aClass104_4854.anInterface16_902 = null;
                                    }
                                }
                            }
                        }
                    }
                    if (this.aClass249Array4877 != null) {
                        for (int n160 = 0; this.anInt4866 > n160; ++n160) {
                            final Class249 class249 = this.aClass249Array4877[n160];
                            final Class219 class250 = this.aClass219Array4861[n160];
                            class250.anInt1643 = (-(this.aByteArray4882[class249.anInt1905] & 0xFF) + 255 << -573778056 | (class250.anInt1643 & 0xFFFFFF));
                        }
                    }
                }
            }
            else if (~n == 0xFFFFFFF8) {
                if (this.anIntArrayArray4870 != null) {
                    for (int n161 = 0; ~i < ~n161; ++n161) {
                        final int n162 = array[n161];
                        if (~this.anIntArrayArray4870.length < ~n162) {
                            final int[] array16 = this.anIntArrayArray4870[n162];
                            for (int n163 = 0; ~n163 > ~array16.length; ++n163) {
                                final int n164 = array16[n163];
                                if (this.aShortArray4864 == null || ~(n2 & this.aShortArray4864[n164]) != -1) {
                                    final int n165 = 0xFFFF & this.aShortArray4842[n164];
                                    final int n166 = (n165 & 0xFD5B) >> -360939670;
                                    final int n167 = (0x3FC & n165) >> -1854591289;
                                    final int n168 = anInt3800 + n166 & 0x3F;
                                    final int n169 = n165 & 0x7F;
                                    int n170 = n167 + anInt3801 / 4;
                                    if (n170 >= 0) {
                                        if (~n170 < -8) {
                                            n170 = 7;
                                        }
                                    }
                                    else {
                                        n170 = 0;
                                    }
                                    int n171 = n169 + anInt3802;
                                    if (~n171 > -1) {
                                        n171 = 0;
                                    }
                                    else if (n171 > 127) {
                                        n171 = 127;
                                    }
                                    this.aShortArray4842[n164] = (short)Class41.method366(Class41.method366(n170 << 2014646919, n168 << -1621533334), n171);
                                    if (this.aClass104_4854 != null) {
                                        this.aClass104_4854.anInterface16_902 = null;
                                    }
                                }
                            }
                        }
                    }
                    if (this.aClass249Array4877 != null) {
                        for (int n172 = 0; n172 < this.anInt4866; ++n172) {
                            final Class249 class251 = this.aClass249Array4877[n172];
                            final Class219 class252 = this.aClass219Array4861[n172];
                            class252.anInt1643 = ((class252.anInt1643 & 0xFF000000) | (0xFFFFFF & Class208.anIntArray1579[0xFFFF & this.aShortArray4842[class251.anInt1905]]));
                        }
                    }
                }
            }
            else if (n == 8) {
                if (this.anIntArrayArray4875 != null) {
                    for (int n173 = 0; ~i < ~n173; ++n173) {
                        final int n174 = array[n173];
                        if (~n174 > ~this.anIntArrayArray4875.length) {
                            final int[] array17 = this.anIntArrayArray4875[n174];
                            for (int n175 = 0; ~array17.length < ~n175; ++n175) {
                                final Class219 class254;
                                final Class219 class253 = class254 = this.aClass219Array4861[array17[n175]];
                                class254.anInt1644 += anInt3801;
                                final Class219 class255 = class253;
                                class255.anInt1638 += anInt3800;
                            }
                        }
                    }
                }
            }
            else if (~n == 0xFFFFFFF5) {
                if (this.anIntArrayArray4875 != null) {
                    for (int n176 = 0; ~i < ~n176; ++n176) {
                        final int n177 = array[n176];
                        if (~this.anIntArrayArray4875.length < ~n177) {
                            final int[] array18 = this.anIntArrayArray4875[n177];
                            for (int n178 = 0; ~n178 > ~array18.length; ++n178) {
                                final Class219 class256 = this.aClass219Array4861[array18[n178]];
                                class256.anInt1637 = anInt3800 * class256.anInt1637 >> 1902593511;
                                class256.anInt1645 = class256.anInt1645 * anInt3801 >> -404777401;
                            }
                        }
                    }
                }
            }
            else if (n == 9 && this.anIntArrayArray4875 != null) {
                for (int n179 = 0; ~n179 > ~i; ++n179) {
                    final int n180 = array[n179];
                    if (this.anIntArrayArray4875.length > n180) {
                        final int[] array19 = this.anIntArrayArray4875[n180];
                        for (int n181 = 0; ~n181 > ~array19.length; ++n181) {
                            final Class219 class257 = this.aClass219Array4861[array19[n181]];
                            class257.anInt1639 = (anInt3800 + class257.anInt1639 & 0x3FFF);
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.I(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + anInt3800 + ',' + anInt3801 + ',' + anInt3802 + ',' + b + ',' + n2 + ',' + ((array2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final short method2381(final byte b, final float n, final int n2, final int n3, final int n4, final long n5, final float n6, final int n7, final Class178 class178, final int n8) {
        try {
            final int n9 = this.anIntArray4851[n2];
            final int n10 = this.anIntArray4851[1 + n2];
            int n11 = 0;
            for (int i = n9; i < n10; ++i) {
                final short n12 = this.aShortArray4887[i];
                if (~n12 == -1) {
                    n11 = i;
                    break;
                }
                if (Class151_Sub1.aLongArray4970[i] == n5) {
                    return (short)(-1 + n12);
                }
            }
            this.aShortArray4887[n11] = (short)(this.anInt4846 + 1);
            Class151_Sub1.aLongArray4970[n11] = n5;
            this.aShortArray4860[this.anInt4846] = (short)n4;
            this.aShortArray4884[this.anInt4846] = (short)n7;
            this.aShortArray4849[this.anInt4846] = (short)n8;
            this.aByteArray4858[this.anInt4846] = (byte)n3;
            this.aFloatArray4897[this.anInt4846] = n6;
            if (b < 14) {
                return 39;
            }
            this.aFloatArray4890[this.anInt4846] = n;
            return (short)(this.anInt4846++);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.E(" + b + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + ((class178 != null) ? "{...}" : "null") + ',' + n8 + ')');
        }
    }
    
    @Override
    final void C(final int n) {
        try {
            this.aShort4867 = (short)n;
            if (this.aClass104_4854 != null) {
                this.aClass104_4854.anInterface16_902 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.C(" + n + ')');
        }
    }
    
    @Override
    final void P(final int n, final int anInt3800, final int anInt3801, final int anInt3802) {
        try {
            if (n == 0) {
                Class76_Sub11.anInt3800 = 0;
                Class78.anInt596 = 0;
                Class98_Sub40.anInt4196 = 0;
                int n2 = 0;
                for (int n3 = 0; ~this.anInt4883 < ~n3; ++n3) {
                    Class76_Sub11.anInt3800 += this.anIntArray4886[n3];
                    Class98_Sub40.anInt4196 += this.anIntArray4856[n3];
                    Class78.anInt596 += this.anIntArray4843[n3];
                    ++n2;
                }
                if (~n2 < -1) {
                    Class76_Sub11.anInt3800 = Class76_Sub11.anInt3800 / n2 + anInt3800;
                    Class78.anInt596 = anInt3802 + Class78.anInt596 / n2;
                    Class98_Sub40.anInt4196 = Class98_Sub40.anInt4196 / n2 - -anInt3801;
                }
                else {
                    Class78.anInt596 = anInt3802;
                    Class76_Sub11.anInt3800 = anInt3800;
                    Class98_Sub40.anInt4196 = anInt3801;
                }
            }
            else if (n == 1) {
                for (int n4 = 0; ~n4 > ~this.anInt4883; ++n4) {
                    final int[] anIntArray4886 = this.anIntArray4886;
                    final int n5 = n4;
                    anIntArray4886[n5] += anInt3800;
                    final int[] anIntArray4887 = this.anIntArray4856;
                    final int n6 = n4;
                    anIntArray4887[n6] += anInt3801;
                    final int[] anIntArray4888 = this.anIntArray4843;
                    final int n7 = n4;
                    anIntArray4888[n7] += anInt3802;
                }
            }
            else if (~n == 0xFFFFFFFD) {
                for (int n8 = 0; ~this.anInt4883 < ~n8; ++n8) {
                    final int[] anIntArray4889 = this.anIntArray4886;
                    final int n9 = n8;
                    anIntArray4889[n9] -= Class76_Sub11.anInt3800;
                    final int[] anIntArray4890 = this.anIntArray4856;
                    final int n10 = n8;
                    anIntArray4890[n10] -= Class98_Sub40.anInt4196;
                    final int[] anIntArray4891 = this.anIntArray4843;
                    final int n11 = n8;
                    anIntArray4891[n11] -= Class78.anInt596;
                    if (~anInt3802 != -1) {
                        final int n12 = Class284_Sub2_Sub2.anIntArray6200[anInt3802];
                        final int n13 = Class284_Sub2_Sub2.anIntArray6202[anInt3802];
                        final int n14 = this.anIntArray4886[n8] * n13 + (n12 * this.anIntArray4856[n8] + 16383) >> 1144997998;
                        this.anIntArray4856[n8] = 16383 + -(this.anIntArray4886[n8] * n12) + n13 * this.anIntArray4856[n8] >> -1527810002;
                        this.anIntArray4886[n8] = n14;
                    }
                    if (~anInt3800 != -1) {
                        final int n15 = Class284_Sub2_Sub2.anIntArray6200[anInt3800];
                        final int n16 = Class284_Sub2_Sub2.anIntArray6202[anInt3800];
                        final int n17 = -(this.anIntArray4843[n8] * n15) + this.anIntArray4856[n8] * n16 + 16383 >> 1585191406;
                        this.anIntArray4843[n8] = n15 * this.anIntArray4856[n8] + (this.anIntArray4843[n8] * n16 + 16383) >> 914709166;
                        this.anIntArray4856[n8] = n17;
                    }
                    if (anInt3801 != 0) {
                        final int n18 = Class284_Sub2_Sub2.anIntArray6200[anInt3801];
                        final int n19 = Class284_Sub2_Sub2.anIntArray6202[anInt3801];
                        final int n20 = n18 * this.anIntArray4843[n8] - (-(this.anIntArray4886[n8] * n19) - 16383) >> 1825413646;
                        this.anIntArray4843[n8] = this.anIntArray4843[n8] * n19 + -(n18 * this.anIntArray4886[n8]) + 16383 >> 575486126;
                        this.anIntArray4886[n8] = n20;
                    }
                    final int[] anIntArray4892 = this.anIntArray4886;
                    final int n21 = n8;
                    anIntArray4892[n21] += Class76_Sub11.anInt3800;
                    final int[] anIntArray4893 = this.anIntArray4856;
                    final int n22 = n8;
                    anIntArray4893[n22] += Class98_Sub40.anInt4196;
                    final int[] anIntArray4894 = this.anIntArray4843;
                    final int n23 = n8;
                    anIntArray4894[n23] += Class78.anInt596;
                }
            }
            else if (n == 3) {
                for (int n24 = 0; ~this.anInt4883 < ~n24; ++n24) {
                    final int[] anIntArray4895 = this.anIntArray4886;
                    final int n25 = n24;
                    anIntArray4895[n25] -= Class76_Sub11.anInt3800;
                    final int[] anIntArray4896 = this.anIntArray4856;
                    final int n26 = n24;
                    anIntArray4896[n26] -= Class98_Sub40.anInt4196;
                    final int[] anIntArray4897 = this.anIntArray4843;
                    final int n27 = n24;
                    anIntArray4897[n27] -= Class78.anInt596;
                    this.anIntArray4886[n24] = this.anIntArray4886[n24] * anInt3800 / 128;
                    this.anIntArray4856[n24] = this.anIntArray4856[n24] * anInt3801 / 128;
                    this.anIntArray4843[n24] = anInt3802 * this.anIntArray4843[n24] / 128;
                    final int[] anIntArray4898 = this.anIntArray4886;
                    final int n28 = n24;
                    anIntArray4898[n28] += Class76_Sub11.anInt3800;
                    final int[] anIntArray4899 = this.anIntArray4856;
                    final int n29 = n24;
                    anIntArray4899[n29] += Class98_Sub40.anInt4196;
                    final int[] anIntArray4900 = this.anIntArray4843;
                    final int n30 = n24;
                    anIntArray4900[n30] += Class78.anInt596;
                }
            }
            else if (~n == 0xFFFFFFFA) {
                for (int n31 = 0; ~n31 > ~this.anInt4853; ++n31) {
                    int n32 = anInt3800 * 8 + (0xFF & this.aByteArray4882[n31]);
                    if (~n32 > -1) {
                        n32 = 0;
                    }
                    else if (n32 > 255) {
                        n32 = 255;
                    }
                    this.aByteArray4882[n31] = (byte)n32;
                }
                if (this.aClass104_4854 != null) {
                    this.aClass104_4854.anInterface16_902 = null;
                }
                if (this.aClass249Array4877 != null) {
                    for (int i = 0; i < this.anInt4866; ++i) {
                        final Class249 class249 = this.aClass249Array4877[i];
                        final Class219 class250 = this.aClass219Array4861[i];
                        class250.anInt1643 = (-(0xFF & this.aByteArray4882[class249.anInt1905]) + 255 << 512722744 | (0xFFFFFF & class250.anInt1643));
                    }
                }
            }
            else if (~n == 0xFFFFFFF8) {
                for (int n33 = 0; this.anInt4853 > n33; ++n33) {
                    final int n34 = this.aShortArray4842[n33] & 0xFFFF;
                    final int n35 = n34 >> 108096874 & 0x3F;
                    final int n36 = 0x7 & n34 >> -1933036857;
                    final int n37 = 0x3F & anInt3800 + n35;
                    int n38 = n36 + anInt3801 / 4;
                    final int n39 = n34 & 0x7F;
                    if (n38 < 0) {
                        n38 = 0;
                    }
                    else if (n38 > 7) {
                        n38 = 7;
                    }
                    int n40 = n39 + anInt3802;
                    if (n40 < 0) {
                        n40 = 0;
                    }
                    else if (~n40 < -128) {
                        n40 = 127;
                    }
                    this.aShortArray4842[n33] = (short)Class41.method366(Class41.method366(n37 << -1704472598, n38 << -1983873977), n40);
                }
                if (this.aClass104_4854 != null) {
                    this.aClass104_4854.anInterface16_902 = null;
                }
                if (this.aClass249Array4877 != null) {
                    for (int n41 = 0; ~this.anInt4866 < ~n41; ++n41) {
                        final Class249 class251 = this.aClass249Array4877[n41];
                        final Class219 class252 = this.aClass219Array4861[n41];
                        class252.anInt1643 = ((0xFF000000 & class252.anInt1643) | (Class208.anIntArray1579[this.aShortArray4842[class251.anInt1905] & 0xFFFF] & 0xFFFFFF));
                    }
                }
            }
            else if (~n == 0xFFFFFFF7) {
                for (int j = 0; j < this.anInt4866; ++j) {
                    final Class219 class254;
                    final Class219 class253 = class254 = this.aClass219Array4861[j];
                    class254.anInt1644 += anInt3801;
                    final Class219 class255 = class253;
                    class255.anInt1638 += anInt3800;
                }
            }
            else if (~n == 0xFFFFFFF5) {
                for (int n42 = 0; this.anInt4866 > n42; ++n42) {
                    final Class219 class256 = this.aClass219Array4861[n42];
                    class256.anInt1637 = anInt3800 * class256.anInt1637 >> -113609817;
                    class256.anInt1645 = anInt3801 * class256.anInt1645 >> 559966631;
                }
            }
            else if (n == 9) {
                for (int n43 = 0; ~this.anInt4866 < ~n43; ++n43) {
                    final Class219 class257 = this.aClass219Array4861[n43];
                    class257.anInt1639 = (0x3FFF & anInt3800 + class257.anInt1639);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.P(" + n + ',' + anInt3800 + ',' + anInt3801 + ',' + anInt3802 + ')');
        }
    }
    
    @Override
    final int fa() {
        try {
            if (!this.aBoolean4847) {
                this.method2380(-89);
            }
            return this.aShort4868;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.fa()");
        }
    }
    
    @Override
    final Class87[] method2320() {
        try {
            return this.aClass87Array4881;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.IB()");
        }
    }
    
    private final void method2382(final byte b) {
        try {
            if (~this.anInt4896 != -1) {
                if (~this.aByte4885 != -1) {
                    this.method2390(33, true);
                }
                this.method2390(114, false);
                if (this.aClass322_4838 != null) {
                    if (this.aClass322_4838.anInterface8_2711 == null) {
                        this.method2388(~(this.aByte4885 & 0x10) != -1, -256);
                    }
                    if (this.aClass322_4838.anInterface8_2711 != null) {
                        this.aHa_Sub1_4865.method1851(this.aClass104_4859 != null, false);
                        this.aHa_Sub1_4865.method1868(this.aClass104_4854, this.aClass104_4859, this.aClass104_4874, this.aClass104_4889, 0);
                        for (int n = this.anIntArray4850.length - 1, n2 = 0; ~n < ~n2; ++n2) {
                            final int n3 = this.anIntArray4850[n2];
                            final int n4 = this.anIntArray4850[1 + n2];
                            int n5 = this.aShortArray4869[n3] & 0xFFFF;
                            if (~n5 == 0xFFFF0000) {
                                n5 = -1;
                            }
                            this.aHa_Sub1_4865.method1908(this.aClass104_4859 != null, -90, n5);
                            this.aHa_Sub1_4865.method1865(3 * (-n3 + n4), 4, this.aClass322_4838.anInterface8_2711, false, 3 * n3);
                        }
                    }
                }
                this.method2383(-49);
                if (b != -104) {
                    this.aClass14_4898 = null;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.IA(" + b + ')');
        }
    }
    
    @Override
    final void LA(final int n) {
        try {
            this.aShort4872 = (short)n;
            if (this.aClass104_4854 != null) {
                this.aClass104_4854.anInterface16_902 = null;
            }
            if (this.aClass104_4859 != null) {
                this.aClass104_4859.anInterface16_902 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.LA(" + n + ')');
        }
    }
    
    private final void method2383(final int n) {
        try {
            if (this.aBoolean4871) {
                this.aBoolean4871 = false;
                if (n >= -48) {
                    this.EA();
                }
                if (this.aClass87Array4881 == null && this.aClass35Array4863 == null && this.aClass249Array4877 == null) {
                    if (this.anIntArray4886 != null && !Class98_Sub10_Sub18.method1055(this.anInt4894, this.anInt4837, (byte)(-11))) {
                        if (this.aClass104_4889 == null || this.aClass104_4889.anInterface16_902 != null) {
                            if (!this.aBoolean4847) {
                                this.method2380(-87);
                            }
                            this.anIntArray4886 = null;
                        }
                        else {
                            this.aBoolean4871 = true;
                        }
                    }
                    if (this.anIntArray4856 != null && !Class287.method3386(this.anInt4894, this.anInt4837, (byte)120)) {
                        if (this.aClass104_4889 != null && this.aClass104_4889.anInterface16_902 == null) {
                            this.aBoolean4871 = true;
                        }
                        else {
                            if (!this.aBoolean4847) {
                                this.method2380(-102);
                            }
                            this.anIntArray4856 = null;
                        }
                    }
                    if (this.anIntArray4843 != null && !Class94.method917((byte)(-95), this.anInt4837, this.anInt4894)) {
                        if (this.aClass104_4889 != null && this.aClass104_4889.anInterface16_902 == null) {
                            this.aBoolean4871 = true;
                        }
                        else {
                            if (!this.aBoolean4847) {
                                this.method2380(-111);
                            }
                            this.anIntArray4843 = null;
                        }
                    }
                }
                if (this.aShortArray4887 != null && this.anIntArray4886 == null && this.anIntArray4856 == null && this.anIntArray4843 == null) {
                    this.aShortArray4887 = null;
                    this.anIntArray4851 = null;
                }
                if (this.aByteArray4858 != null && !Class187.method2636(this.anInt4894, this.anInt4837, 3)) {
                    if (this.aClass104_4859 != null) {
                        if (this.aClass104_4859.anInterface16_902 == null) {
                            this.aBoolean4871 = true;
                        }
                        else {
                            this.aByteArray4858 = null;
                            final short[] aShortArray4860 = null;
                            this.aShortArray4849 = aShortArray4860;
                            this.aShortArray4884 = aShortArray4860;
                            this.aShortArray4860 = aShortArray4860;
                        }
                    }
                    else if (this.aClass104_4854 != null && this.aClass104_4854.anInterface16_902 == null) {
                        this.aBoolean4871 = true;
                    }
                    else {
                        this.aByteArray4858 = null;
                        final short[] aShortArray4861 = null;
                        this.aShortArray4849 = aShortArray4861;
                        this.aShortArray4884 = aShortArray4861;
                        this.aShortArray4860 = aShortArray4861;
                    }
                }
                if (this.aShortArray4842 != null && !Class29.method300(false, this.anInt4837, this.anInt4894)) {
                    if (this.aClass104_4854 != null && this.aClass104_4854.anInterface16_902 == null) {
                        this.aBoolean4871 = true;
                    }
                    else {
                        this.aShortArray4842 = null;
                    }
                }
                if (this.aByteArray4882 != null && !Class246_Sub3_Sub2_Sub1.method3007(this.anInt4894, this.anInt4837, (byte)(-15))) {
                    if (this.aClass104_4854 != null && this.aClass104_4854.anInterface16_902 == null) {
                        this.aBoolean4871 = true;
                    }
                    else {
                        this.aByteArray4882 = null;
                    }
                }
                if (this.aFloatArray4897 != null && !Class98_Sub10_Sub14.method1047(this.anInt4837, (byte)70, this.anInt4894)) {
                    if (this.aClass104_4874 == null || this.aClass104_4874.anInterface16_902 != null) {
                        final float[] array = null;
                        this.aFloatArray4890 = array;
                        this.aFloatArray4897 = array;
                    }
                    else {
                        this.aBoolean4871 = true;
                    }
                }
                if (this.aShortArray4869 != null && !Class59.method524(this.anInt4894, this.anInt4837, 113)) {
                    if (this.aClass104_4854 == null || this.aClass104_4854.anInterface16_902 != null) {
                        this.aShortArray4869 = null;
                    }
                    else {
                        this.aBoolean4871 = true;
                    }
                }
                if (this.aShortArray4895 != null && !Class373_Sub1_Sub1.method3973(this.anInt4894, this.anInt4837, 48)) {
                    if ((this.aClass322_4838 != null && this.aClass322_4838.anInterface8_2711 == null) || (this.aClass104_4854 != null && this.aClass104_4854.anInterface16_902 == null)) {
                        this.aBoolean4871 = true;
                    }
                    else {
                        final short[] aShortArray4862 = null;
                        this.aShortArray4878 = aShortArray4862;
                        this.aShortArray4852 = aShortArray4862;
                        this.aShortArray4895 = aShortArray4862;
                    }
                }
                if (this.anIntArrayArray4870 != null && !Class98_Sub46_Sub9.method1554(this.anInt4894, 22251, this.anInt4837)) {
                    this.aShortArray4864 = null;
                    this.anIntArrayArray4870 = null;
                }
                if (this.anIntArrayArray4888 != null && !Class111.method2095(this.anInt4837, this.anInt4894, (byte)(-88))) {
                    this.aShortArray4841 = null;
                    this.anIntArrayArray4888 = null;
                }
                if (this.anIntArrayArray4875 != null && !Class64.method555(this.anInt4837, this.anInt4894, -86)) {
                    this.anIntArrayArray4875 = null;
                }
                if (this.anIntArray4850 != null && (0x800 & this.anInt4894) == 0x0 && (this.anInt4894 & 0x40000) == 0x0) {
                    this.anIntArray4850 = null;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.B(" + n + ')');
        }
    }
    
    @Override
    final boolean method2339(final int n, final int n2, final Class111 class111, final boolean b, final int n3) {
        try {
            return this.method2389(n3, n2, n, class111, false, -1, b);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.CB(" + n + ',' + n2 + ',' + ((class111 != null) ? "{...}" : "null") + ',' + b + ',' + n3 + ')');
        }
    }
    
    @Override
    final int ma() {
        try {
            if (!this.aBoolean4847) {
                this.method2380(-97);
            }
            return this.aShort4844;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.ma()");
        }
    }
    
    @Override
    final void method2325(final Class111 class111, final Class246_Sub6 class246_Sub6, final int n) {
        try {
            if (~this.anInt4846 != -1) {
                final Class111_Sub1 aClass111_Sub1_4348 = this.aHa_Sub1_4865.aClass111_Sub1_4348;
                if (!this.aBoolean4847) {
                    this.method2380(-126);
                }
                final Class111_Sub1 class111_Sub1 = (Class111_Sub1)class111;
                Class151_Sub4.aFloat4989 = aClass111_Sub1_4348.aFloat4684 * class111_Sub1.aFloat4679 + aClass111_Sub1_4348.aFloat4676 * class111_Sub1.aFloat4675 + class111_Sub1.aFloat4676 * aClass111_Sub1_4348.aFloat4673;
                Class48_Sub1_Sub1.aFloat5505 = class111_Sub1.aFloat4683 * aClass111_Sub1_4348.aFloat4676 + aClass111_Sub1_4348.aFloat4684 * class111_Sub1.aFloat4674 + class111_Sub1.aFloat4677 * aClass111_Sub1_4348.aFloat4673 + aClass111_Sub1_4348.aFloat4677;
                final float n2 = Class151_Sub4.aFloat4989 * this.aShort4868 + Class48_Sub1_Sub1.aFloat5505;
                final float n3 = Class48_Sub1_Sub1.aFloat5505 + Class151_Sub4.aFloat4989 * this.aShort4879;
                float n4;
                float n5;
                if (n3 < n2) {
                    n4 = n3 - this.aShort4876;
                    n5 = n2 + this.aShort4876;
                }
                else {
                    n4 = n2 - this.aShort4876;
                    n5 = n3 + this.aShort4876;
                }
                if (this.aHa_Sub1_4865.aFloat4401 > n4 && this.aHa_Sub1_4865.anInt4404 < n5) {
                    Class50.aFloat419 = aClass111_Sub1_4348.aFloat4686 * class111_Sub1.aFloat4674 + aClass111_Sub1_4348.aFloat4679 * class111_Sub1.aFloat4683 + class111_Sub1.aFloat4677 * aClass111_Sub1_4348.aFloat4680 + aClass111_Sub1_4348.aFloat4674;
                    Class286.aFloat2182 = class111_Sub1.aFloat4676 * aClass111_Sub1_4348.aFloat4680 + (aClass111_Sub1_4348.aFloat4686 * class111_Sub1.aFloat4679 + aClass111_Sub1_4348.aFloat4679 * class111_Sub1.aFloat4675);
                    final float n6 = Class50.aFloat419 + Class286.aFloat2182 * this.aShort4868;
                    final float n7 = this.aShort4879 * Class286.aFloat2182 + Class50.aFloat419;
                    float n8;
                    float n9;
                    if (n7 >= n6) {
                        n8 = this.aHa_Sub1_4865.anInt4419 * (n7 + this.aShort4876);
                        n9 = this.aHa_Sub1_4865.anInt4419 * (-this.aShort4876 + n6);
                    }
                    else {
                        n9 = this.aHa_Sub1_4865.anInt4419 * (-this.aShort4876 + n7);
                        n8 = this.aHa_Sub1_4865.anInt4419 * (n6 + this.aShort4876);
                    }
                    if (this.aHa_Sub1_4865.aFloat4364 > n9 / n5 && n8 / n5 > this.aHa_Sub1_4865.aFloat4421) {
                        Class376.aFloat3171 = aClass111_Sub1_4348.aFloat4683 + (class111_Sub1.aFloat4683 * aClass111_Sub1_4348.aFloat4675 + aClass111_Sub1_4348.aFloat4678 * class111_Sub1.aFloat4674 + class111_Sub1.aFloat4677 * aClass111_Sub1_4348.aFloat4687);
                        Class48_Sub1_Sub2.aFloat5515 = aClass111_Sub1_4348.aFloat4678 * class111_Sub1.aFloat4679 + class111_Sub1.aFloat4675 * aClass111_Sub1_4348.aFloat4675 + aClass111_Sub1_4348.aFloat4687 * class111_Sub1.aFloat4676;
                        final float n10 = this.aShort4868 * Class48_Sub1_Sub2.aFloat5515 + Class376.aFloat3171;
                        final float n11 = Class48_Sub1_Sub2.aFloat5515 * this.aShort4879 + Class376.aFloat3171;
                        float n12;
                        float n13;
                        if (n10 <= n11) {
                            n12 = (n11 + this.aShort4876) * this.aHa_Sub1_4865.anInt4381;
                            n13 = this.aHa_Sub1_4865.anInt4381 * (n10 - this.aShort4876);
                        }
                        else {
                            n13 = (n11 - this.aShort4876) * this.aHa_Sub1_4865.anInt4381;
                            n12 = this.aHa_Sub1_4865.anInt4381 * (this.aShort4876 + n10);
                        }
                        if (this.aHa_Sub1_4865.aFloat4437 > n13 / n5 && this.aHa_Sub1_4865.aFloat4359 < n12 / n5) {
                            if (class246_Sub6 != null || this.aClass249Array4877 != null) {
                                Class372.aFloat3151 = class111_Sub1.aFloat4686 * aClass111_Sub1_4348.aFloat4684 + aClass111_Sub1_4348.aFloat4676 * class111_Sub1.aFloat4678 + class111_Sub1.aFloat4684 * aClass111_Sub1_4348.aFloat4673;
                                Class287_Sub2.aFloat3273 = class111_Sub1.aFloat4687 * aClass111_Sub1_4348.aFloat4676 + class111_Sub1.aFloat4680 * aClass111_Sub1_4348.aFloat4684 + class111_Sub1.aFloat4673 * aClass111_Sub1_4348.aFloat4673;
                                Class369.aFloat3131 = class111_Sub1.aFloat4678 * aClass111_Sub1_4348.aFloat4679 + class111_Sub1.aFloat4686 * aClass111_Sub1_4348.aFloat4686 + class111_Sub1.aFloat4684 * aClass111_Sub1_4348.aFloat4680;
                                aa_Sub3.aFloat3567 = class111_Sub1.aFloat4673 * aClass111_Sub1_4348.aFloat4680 + (class111_Sub1.aFloat4687 * aClass111_Sub1_4348.aFloat4679 + class111_Sub1.aFloat4680 * aClass111_Sub1_4348.aFloat4686);
                                Class98_Sub41.aFloat4204 = aClass111_Sub1_4348.aFloat4678 * class111_Sub1.aFloat4686 + class111_Sub1.aFloat4678 * aClass111_Sub1_4348.aFloat4675 + class111_Sub1.aFloat4684 * aClass111_Sub1_4348.aFloat4687;
                                Class378.aFloat3190 = aClass111_Sub1_4348.aFloat4675 * class111_Sub1.aFloat4687 + class111_Sub1.aFloat4680 * aClass111_Sub1_4348.aFloat4678 + class111_Sub1.aFloat4673 * aClass111_Sub1_4348.aFloat4687;
                            }
                            if (class246_Sub6 != null) {
                                boolean b = false;
                                boolean b2 = true;
                                final int n14 = this.aShort4845 - -this.aShort4862 >> 1291996065;
                                final int n15 = this.aShort4892 + this.aShort4891 >> -564686943;
                                final int n16 = (int)(Class50.aFloat419 + Class369.aFloat3131 * n14 + Class286.aFloat2182 * this.aShort4868 + n15 * aa_Sub3.aFloat3567);
                                final int n17 = (int)(Class378.aFloat3190 * n15 + (Class376.aFloat3171 + Class98_Sub41.aFloat4204 * n14 + Class48_Sub1_Sub2.aFloat5515 * this.aShort4868));
                                final int n18 = (int)(Class287_Sub2.aFloat3273 * n15 + (this.aShort4868 * Class151_Sub4.aFloat4989 + (Class48_Sub1_Sub1.aFloat5505 + Class372.aFloat3151 * n14)));
                                if (n18 >= this.aHa_Sub1_4865.anInt4404) {
                                    class246_Sub6.anInt5113 = n17 * this.aHa_Sub1_4865.anInt4381 / n18 + this.aHa_Sub1_4865.anInt4394;
                                    class246_Sub6.anInt5111 = this.aHa_Sub1_4865.anInt4419 * n16 / n18 + this.aHa_Sub1_4865.anInt4451;
                                }
                                else {
                                    b = true;
                                }
                                final int n19 = (int)(n15 * aa_Sub3.aFloat3567 + (Class286.aFloat2182 * this.aShort4879 + (Class369.aFloat3131 * n14 + Class50.aFloat419)));
                                final int n20 = (int)(Class48_Sub1_Sub2.aFloat5515 * this.aShort4879 + (n14 * Class98_Sub41.aFloat4204 + Class376.aFloat3171) + n15 * Class378.aFloat3190);
                                final int n21 = (int)(Class48_Sub1_Sub1.aFloat5505 + Class372.aFloat3151 * n14 + Class151_Sub4.aFloat4989 * this.aShort4879 + n15 * Class287_Sub2.aFloat3273);
                                if (~n21 <= ~this.aHa_Sub1_4865.anInt4404) {
                                    class246_Sub6.anInt5110 = this.aHa_Sub1_4865.anInt4451 + this.aHa_Sub1_4865.anInt4419 * n19 / n21;
                                    class246_Sub6.anInt5112 = this.aHa_Sub1_4865.anInt4394 - -(n20 * this.aHa_Sub1_4865.anInt4381 / n21);
                                }
                                else {
                                    b = true;
                                }
                                if (b) {
                                    if (n18 >= this.aHa_Sub1_4865.anInt4404 || this.aHa_Sub1_4865.anInt4404 <= n21) {
                                        if (~this.aHa_Sub1_4865.anInt4404 >= ~n18) {
                                            if (~this.aHa_Sub1_4865.anInt4404 < ~n21) {
                                                final int n22 = (-this.aHa_Sub1_4865.anInt4404 + n18 << -366469360) / (n18 - n21);
                                                final int n23 = n16 + ((n16 - n19) * n22 >> -1023004720);
                                                final int n24 = n17 - -((n17 + -n20) * n22 >> 442579920);
                                                class246_Sub6.anInt5111 = this.aHa_Sub1_4865.anInt4419 * n23 / this.aHa_Sub1_4865.anInt4404 + this.aHa_Sub1_4865.anInt4451;
                                                class246_Sub6.anInt5113 = this.aHa_Sub1_4865.anInt4381 * n24 / this.aHa_Sub1_4865.anInt4404 + this.aHa_Sub1_4865.anInt4394;
                                            }
                                        }
                                        else {
                                            final int n25 = (-this.aHa_Sub1_4865.anInt4404 + n21 << 1651158576) / (n21 + -n18);
                                            final int n26 = n19 + ((n19 + -n16) * n25 >> 235791984);
                                            final int n27 = (n25 * (-n17 + n20) >> 1877857744) + n20;
                                            class246_Sub6.anInt5111 = this.aHa_Sub1_4865.anInt4451 - -(n26 * this.aHa_Sub1_4865.anInt4419 / this.aHa_Sub1_4865.anInt4404);
                                            class246_Sub6.anInt5113 = n27 * this.aHa_Sub1_4865.anInt4381 / this.aHa_Sub1_4865.anInt4404 + this.aHa_Sub1_4865.anInt4394;
                                        }
                                    }
                                    else {
                                        b2 = false;
                                    }
                                }
                                if (b2) {
                                    if (~n21 > ~n18) {
                                        class246_Sub6.anInt5109 = -class246_Sub6.anInt5111 + (this.aHa_Sub1_4865.anInt4451 + (n16 + this.aShort4876) * this.aHa_Sub1_4865.anInt4419 / n18);
                                    }
                                    else {
                                        class246_Sub6.anInt5109 = this.aHa_Sub1_4865.anInt4419 * (n19 + this.aShort4876) / n21 + (this.aHa_Sub1_4865.anInt4451 + -class246_Sub6.anInt5110);
                                    }
                                    class246_Sub6.aBoolean5114 = true;
                                }
                            }
                            this.aHa_Sub1_4865.method1861(19330);
                            this.aHa_Sub1_4865.method1883(class111_Sub1, (byte)(-128));
                            this.method2382((byte)(-104));
                            this.aHa_Sub1_4865.method1902((byte)60);
                            this.method2387(-32768);
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.D(" + ((class111 != null) ? "{...}" : "null") + ',' + ((class246_Sub6 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static final boolean method2384(final Class243 class243, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
        try {
            if (n7 != 702412290) {
                Class146_Sub2.anInt4855 = 35;
            }
            int anInt1539 = n6;
            int anInt1540 = n10;
            final int n11 = 64;
            final int n12 = 64;
            final int n13 = -n11 + n6;
            final int n14 = n10 - n12;
            PlayerUpdateMask.anIntArrayArray528[n11][n12] = 99;
            Class339.anIntArrayArray2846[n11][n12] = 0;
            int i = 0;
            int n15 = 0;
            Class359.anIntArray3060[i] = anInt1539;
            Class75.anIntArray580[i++] = anInt1540;
            final int[][] anIntArrayArray1853 = class243.anIntArrayArray1853;
            while (i != n15) {
                anInt1540 = Class75.anIntArray580[n15];
                anInt1539 = Class359.anIntArray3060[n15];
                n15 = (1 + n15 & 0xFFF);
                final int n16 = -n14 + anInt1540;
                final int n17 = -n13 + anInt1539;
                final int n18 = anInt1539 + -class243.anInt1854;
                final int n19 = anInt1540 - class243.anInt1855;
                if (n4 != -4) {
                    if (~n4 != 0x2) {
                        if (n4 != -2) {
                            if (~n4 != 0x0) {
                                if (n4 != 0 && n4 != 1 && ~n4 != 0xFFFFFFFD && n4 != 3 && n4 != 9) {
                                    if (class243.method2938(n9, n5, anInt1540, n8, n4, 17761, 2, anInt1539)) {
                                        Class199.anInt1539 = anInt1539;
                                        Class22.anInt217 = anInt1540;
                                        return true;
                                    }
                                }
                                else if (class243.method2952(n8, anInt1539, (byte)98, 2, anInt1540, n5, n9, n4)) {
                                    Class199.anInt1539 = anInt1539;
                                    Class22.anInt217 = anInt1540;
                                    return true;
                                }
                            }
                            else if (class243.method2939(n2, n9, anInt1540, 14672, 2, n8, n3, anInt1539, n)) {
                                Class199.anInt1539 = anInt1539;
                                Class22.anInt217 = anInt1540;
                                return true;
                            }
                        }
                        else if (class243.method2936(n9, n, n8, -1, anInt1540, n2, 2, anInt1539, 2, n3)) {
                            Class22.anInt217 = anInt1540;
                            Class199.anInt1539 = anInt1539;
                            return true;
                        }
                    }
                    else if (Class98_Sub5.method960(n9, n2, -112, anInt1540, n, anInt1539, n8, 2, 2)) {
                        Class22.anInt217 = anInt1540;
                        Class199.anInt1539 = anInt1539;
                        return true;
                    }
                }
                else if (~anInt1539 == ~n9 && anInt1540 == n8) {
                    Class22.anInt217 = anInt1540;
                    Class199.anInt1539 = anInt1539;
                    return true;
                }
                final int n20 = Class339.anIntArrayArray2846[n17][n16] + 1;
                if (~n17 < -1 && PlayerUpdateMask.anIntArrayArray528[n17 - 1][n16] == 0 && (0x43A40000 & anIntArrayArray1853[n18 - 1][n19]) == 0x0 && ~(0x4E240000 & anIntArrayArray1853[n18 - 1][1 + n19]) == -1) {
                    Class359.anIntArray3060[i] = anInt1539 - 1;
                    Class75.anIntArray580[i] = anInt1540;
                    i = (0xFFF & i + 1);
                    PlayerUpdateMask.anIntArrayArray528[-1 + n17][n16] = 2;
                    Class339.anIntArrayArray2846[-1 + n17][n16] = n20;
                }
                if (~n17 > -127 && ~PlayerUpdateMask.anIntArrayArray528[1 + n17][n16] == -1 && ~(anIntArrayArray1853[2 + n18][n19] & 0x60E40000) == -1 && ~(0x78240000 & anIntArrayArray1853[n18 + 2][n19 + 1]) == -1) {
                    Class359.anIntArray3060[i] = anInt1539 + 1;
                    Class75.anIntArray580[i] = anInt1540;
                    PlayerUpdateMask.anIntArrayArray528[n17 + 1][n16] = 8;
                    i = (0xFFF & i + 1);
                    Class339.anIntArrayArray2846[n17 + 1][n16] = n20;
                }
                if (n16 > 0 && PlayerUpdateMask.anIntArrayArray528[n17][-1 + n16] == 0 && (0x43A40000 & anIntArrayArray1853[n18][n19 - 1]) == 0x0 && ~(anIntArrayArray1853[n18 + 1][n19 - 1] & 0x60E40000) == -1) {
                    Class359.anIntArray3060[i] = anInt1539;
                    Class75.anIntArray580[i] = anInt1540 - 1;
                    i = (1 + i & 0xFFF);
                    PlayerUpdateMask.anIntArrayArray528[n17][n16 - 1] = 1;
                    Class339.anIntArrayArray2846[n17][-1 + n16] = n20;
                }
                if (n16 < 126 && PlayerUpdateMask.anIntArrayArray528[n17][n16 + 1] == 0 && (anIntArrayArray1853[n18][2 + n19] & 0x4E240000) == 0x0 && ~(0x78240000 & anIntArrayArray1853[1 + n18][n19 + 2]) == -1) {
                    Class359.anIntArray3060[i] = anInt1539;
                    Class75.anIntArray580[i] = anInt1540 + 1;
                    PlayerUpdateMask.anIntArrayArray528[n17][n16 + 1] = 4;
                    i = (0xFFF & 1 + i);
                    Class339.anIntArrayArray2846[n17][n16 + 1] = n20;
                }
                if (n17 > 0 && n16 > 0 && ~PlayerUpdateMask.anIntArrayArray528[-1 + n17][n16 - 1] == -1 && (anIntArrayArray1853[-1 + n18][n19] & 0x4FA40000) == 0x0 && (0x43A40000 & anIntArrayArray1853[n18 - 1][-1 + n19]) == 0x0 && ~(anIntArrayArray1853[n18][-1 + n19] & 0x63E40000) == -1) {
                    Class359.anIntArray3060[i] = -1 + anInt1539;
                    Class75.anIntArray580[i] = -1 + anInt1540;
                    PlayerUpdateMask.anIntArrayArray528[n17 - 1][n16 - 1] = 3;
                    i = (0xFFF & i + 1);
                    Class339.anIntArrayArray2846[-1 + n17][n16 - 1] = n20;
                }
                if (n17 < 126 && ~n16 < -1 && ~PlayerUpdateMask.anIntArrayArray528[1 + n17][-1 + n16] == -1 && ~(0x63E40000 & anIntArrayArray1853[n18 + 1][-1 + n19]) == -1 && ~(anIntArrayArray1853[n18 + 2][n19 - 1] & 0x60E40000) == -1 && ~(anIntArrayArray1853[2 + n18][n19] & 0x78E40000) == -1) {
                    Class359.anIntArray3060[i] = anInt1539 + 1;
                    Class75.anIntArray580[i] = anInt1540 - 1;
                    PlayerUpdateMask.anIntArrayArray528[1 + n17][-1 + n16] = 9;
                    i = (0xFFF & 1 + i);
                    Class339.anIntArrayArray2846[1 + n17][-1 + n16] = n20;
                }
                if (n17 > 0 && ~n16 > -127 && PlayerUpdateMask.anIntArrayArray528[-1 + n17][n16 + 1] == 0 && ~(anIntArrayArray1853[-1 + n18][n19 + 1] & 0x4FA40000) == -1 && ~(anIntArrayArray1853[-1 + n18][2 + n19] & 0x4E240000) == -1 && (anIntArrayArray1853[n18][n19 + 2] & 0x7E240000) == 0x0) {
                    Class359.anIntArray3060[i] = anInt1539 - 1;
                    Class75.anIntArray580[i] = 1 + anInt1540;
                    PlayerUpdateMask.anIntArrayArray528[n17 - 1][1 + n16] = 6;
                    i = (1 + i & 0xFFF);
                    Class339.anIntArrayArray2846[n17 - 1][1 + n16] = n20;
                }
                if (n17 < 126 && ~n16 > -127 && ~PlayerUpdateMask.anIntArrayArray528[1 + n17][1 + n16] == -1 && (0x7E240000 & anIntArrayArray1853[n18 + 1][2 + n19]) == 0x0 && (anIntArrayArray1853[n18 + 2][n19 + 2] & 0x78240000) == 0x0 && (anIntArrayArray1853[n18 + 2][n19 + 1] & 0x78E40000) == 0x0) {
                    Class359.anIntArray3060[i] = 1 + anInt1539;
                    Class75.anIntArray580[i] = 1 + anInt1540;
                    i = (i + 1 & 0xFFF);
                    PlayerUpdateMask.anIntArrayArray528[n17 + 1][1 + n16] = 12;
                    Class339.anIntArrayArray2846[1 + n17][n16 + 1] = n20;
                }
            }
            Class22.anInt217 = anInt1540;
            Class199.anInt1539 = anInt1539;
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.Q(" + ((class243 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + n9 + ',' + n10 + ')');
        }
    }
    
    private final int method2385(final int n, final byte b, final short n2, final int n3, final byte b2) {
        try {
            if (b2 != -97) {
                return 31;
            }
            int n4 = Class208.anIntArray1579[Class98_Sub10_Sub38.method1116(n, 1391, n3)];
            if (~n2 != 0x0) {
                final Class238 method11 = this.aHa_Sub1_4865.aD938.method11(n2 & 0xFFFF, -28755);
                final int n5 = 0xFF & method11.aByte1830;
                if (~n5 != -1) {
                    int n6;
                    if (~n3 <= -1) {
                        if (~n3 >= -128) {
                            n6 = n3 * 131586;
                        }
                        else {
                            n6 = 16777215;
                        }
                    }
                    else {
                        n6 = 0;
                    }
                    if (n5 == 256) {
                        n4 = n6;
                    }
                    else {
                        final int n7 = n5;
                        final int n8 = 256 + -n5;
                        n4 = (0xFF0000 & n7 * (n6 & 0xFF00) + (n4 & 0xFF00) * n8) + ((0xFF00FF & n4) * n8 + n7 * (n6 & 0xFF00FF) & 0xFF00FF00) >> -1338784344;
                    }
                }
                int n9 = 0xFF & method11.aByte1829;
                if (~n9 != -1) {
                    n9 += 256;
                    int n10 = (n4 >> -308840432 & 0xFF) * n9;
                    if (~n10 < -65536) {
                        n10 = 65535;
                    }
                    int n11 = ((0xFF00 & n4) >> -1614119960) * n9;
                    if (n11 > 65535) {
                        n11 = 65535;
                    }
                    int n12 = n9 * (n4 & 0xFF);
                    if (~n12 < -65536) {
                        n12 = 65535;
                    }
                    n4 = (n12 >> -515286264) + (n10 << 927867336 & 0xFF0092) + (0xFF00 & n11);
                }
            }
            return 255 - (b & 0xFF) + (n4 << -272243000);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.A(" + n + ',' + b + ',' + n2 + ',' + n3 + ',' + b2 + ')');
        }
    }
    
    @Override
    final void ia(final short n, final short n2) {
        try {
            for (int i = 0; i < this.anInt4853; ++i) {
                if (~n == ~this.aShortArray4842[i]) {
                    this.aShortArray4842[i] = n2;
                }
            }
            if (this.aClass249Array4877 != null) {
                for (int n3 = 0; ~this.anInt4866 < ~n3; ++n3) {
                    final Class249 class249 = this.aClass249Array4877[n3];
                    final Class219 class250 = this.aClass219Array4861[n3];
                    class250.anInt1643 = ((Class208.anIntArray1579[0xFFFF & this.aShortArray4842[class249.anInt1905]] & 0xFFFFFF) | (0xFF000000 & class250.anInt1643));
                }
            }
            if (this.aClass104_4854 != null) {
                this.aClass104_4854.anInterface16_902 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.ia(" + n + ',' + n2 + ')');
        }
    }
    
    private final void method2386(final r_Sub1 r_Sub1, final int n) {
        try {
            if (this.aHa_Sub1_4865.anIntArray4471.length < this.anInt4846) {
                this.aHa_Sub1_4865.anIntArray4471 = new int[this.anInt4846];
                this.aHa_Sub1_4865.anIntArray4470 = new int[this.anInt4846];
            }
            final int[] anIntArray4471 = this.aHa_Sub1_4865.anIntArray4471;
            final int[] anIntArray4472 = this.aHa_Sub1_4865.anIntArray4470;
            for (int n2 = n; ~n2 > ~this.anInt4883; ++n2) {
                final int n3 = -r_Sub1.anInt6324 + (this.anIntArray4886[n2] - (this.aHa_Sub1_4865.anInt4398 * this.anIntArray4856[n2] >> -138980216) >> this.aHa_Sub1_4865.anInt4319);
                final int n4 = (-(this.aHa_Sub1_4865.anInt4377 * this.anIntArray4856[n2] >> -65265720) + this.anIntArray4843[n2] >> this.aHa_Sub1_4865.anInt4319) + -r_Sub1.anInt6320;
                final int n5 = this.anIntArray4851[n2];
                for (int n6 = this.anIntArray4851[n2 + 1], i = n5; i < n6; ++i) {
                    final short n7 = (short)(-1 + this.aShortArray4887[i]);
                    if (~n7 == 0x0) {
                        break;
                    }
                    anIntArray4471[n7] = n3;
                    anIntArray4472[n7] = n4;
                }
            }
            for (int n8 = 0; this.anInt4896 > n8; ++n8) {
                if (this.aByteArray4882 == null || this.aByteArray4882[n8] <= 128) {
                    final short n9 = this.aShortArray4895[n8];
                    final short n10 = this.aShortArray4852[n8];
                    final short n11 = this.aShortArray4878[n8];
                    final int n12 = anIntArray4471[n9];
                    final int n13 = anIntArray4471[n10];
                    final int n14 = anIntArray4471[n11];
                    final int n15 = anIntArray4472[n9];
                    final int n16 = anIntArray4472[n10];
                    final int n17 = anIntArray4472[n11];
                    if (-((-n15 + n16) * (n14 - n13)) + (n12 + -n13) * (-n17 + n16) > 0) {
                        r_Sub1.method1648(n15, n13, n12, n17, n16, n ^ 0x59, n14);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.DA(" + ((r_Sub1 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    final int G() {
        try {
            if (!this.aBoolean4847) {
                this.method2380(-103);
            }
            return this.aShort4891;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.G()");
        }
    }
    
    @Override
    final boolean r() {
        try {
            return this.aBoolean4848;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.r()");
        }
    }
    
    private final void method2387(final int n) {
        try {
            if (n != -32768) {
                this.EA();
            }
            if (this.aClass249Array4877 != null) {
                final Class111_Sub1 aClass111_Sub1_4316 = this.aHa_Sub1_4865.aClass111_Sub1_4316;
                this.aHa_Sub1_4865.method1841(34167);
                this.aHa_Sub1_4865.C(!this.aBoolean4857);
                this.aHa_Sub1_4865.method1851(false, false);
                this.aHa_Sub1_4865.method1868(null, null, this.aHa_Sub1_4865.aClass104_4436, this.aHa_Sub1_4865.aClass104_4365, 0);
                for (int n2 = 0; this.anInt4866 > n2; ++n2) {
                    final Class249 class249 = this.aClass249Array4877[n2];
                    final Class219 class250 = this.aClass219Array4861[n2];
                    if (!class249.aBoolean1904 || !this.aHa_Sub1_4865.method1768()) {
                        final float n3 = 0.3333333f * (this.anIntArray4886[class249.anInt1900] + (this.anIntArray4886[class249.anInt1909] - -this.anIntArray4886[class249.anInt1907]));
                        final float n4 = 0.3333333f * (this.anIntArray4856[class249.anInt1900] + this.anIntArray4856[class249.anInt1909] - -this.anIntArray4856[class249.anInt1907]);
                        final float n5 = (this.anIntArray4843[class249.anInt1909] + this.anIntArray4843[class249.anInt1900] + this.anIntArray4843[class249.anInt1907]) * 0.3333333f;
                        final float n6 = Class50.aFloat419 + (n5 * aa_Sub3.aFloat3567 + (Class369.aFloat3131 * n3 + Class286.aFloat2182 * n4));
                        final float n7 = n5 * Class378.aFloat3190 + (Class48_Sub1_Sub2.aFloat5515 * n4 + Class98_Sub41.aFloat4204 * n3) + Class376.aFloat3171;
                        final float n8 = Class287_Sub2.aFloat3273 * n5 + (Class151_Sub4.aFloat4989 * n4 + n3 * Class372.aFloat3151) + Class48_Sub1_Sub1.aFloat5505;
                        final float n9 = (float)(1.0 / Math.sqrt(n6 * n6 + n7 * n7 + n8 * n8)) * class249.anInt1899;
                        aClass111_Sub1_4316.method2110(class250.anInt1644 - n7 + n9 * n7, class250.anInt1645 * class249.aShort1901 >> -1969977785, class250.anInt1639, -n8 + n8 * n9, class250.anInt1637 * class249.aShort1908 >> 1962723783, (byte)63, -(n6 * n9) + (n6 + class250.anInt1638));
                        this.aHa_Sub1_4865.method1909(-32076, aClass111_Sub1_4316);
                        final int anInt1643 = class250.anInt1643;
                        OpenGL.glColor4ub((byte)(anInt1643 >> -738445168), (byte)(anInt1643 >> 358941736), (byte)anInt1643, (byte)(anInt1643 >> 1425434648));
                        this.aHa_Sub1_4865.method1834(72, class249.aShort1902);
                        this.aHa_Sub1_4865.method1870((byte)(-56), class249.aByte1906);
                        this.aHa_Sub1_4865.method1910(7, 4, false, 0);
                    }
                }
                this.aHa_Sub1_4865.C(true);
                this.aHa_Sub1_4865.method1902((byte)60);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.N(" + n + ')');
        }
    }
    
    private final void method2388(final boolean b, final int n) {
        try {
            if (~(this.anInt4896 * 6) < ~this.aHa_Sub1_4865.aClass98_Sub22_Sub2_4446.aByteArray3992.length) {
                this.aHa_Sub1_4865.aClass98_Sub22_Sub2_4446 = new Class98_Sub22_Sub2(this.anInt4896 * 6 + 600);
            }
            else {
                this.aHa_Sub1_4865.aClass98_Sub22_Sub2_4446.anInt3991 = 0;
            }
            final Class98_Sub22_Sub2 aClass98_Sub22_Sub2_4446 = this.aHa_Sub1_4865.aClass98_Sub22_Sub2_4446;
            if (!this.aHa_Sub1_4865.aBoolean4397) {
                for (int n2 = 0; ~n2 > ~this.anInt4896; ++n2) {
                    aClass98_Sub22_Sub2_4446.method1247(this.aShortArray4895[n2], 4);
                    aClass98_Sub22_Sub2_4446.method1247(this.aShortArray4852[n2], n + 260);
                    aClass98_Sub22_Sub2_4446.method1247(this.aShortArray4878[n2], 4);
                }
            }
            else {
                for (int n3 = 0; ~this.anInt4896 < ~n3; ++n3) {
                    aClass98_Sub22_Sub2_4446.writeShort(this.aShortArray4895[n3], 1571862888);
                    aClass98_Sub22_Sub2_4446.writeShort(this.aShortArray4852[n3], n ^ 0xA24F4668);
                    aClass98_Sub22_Sub2_4446.writeShort(this.aShortArray4878[n3], n + 1571863144);
                }
            }
            if (aClass98_Sub22_Sub2_4446.anInt3991 != 0) {
                if (n != -256) {
                    this.aByteArray4882 = null;
                }
                if (b) {
                    if (this.anInterface8_4880 != null) {
                        this.anInterface8_4880.method20((byte)(-47), aClass98_Sub22_Sub2_4446.aByteArray3992, aClass98_Sub22_Sub2_4446.anInt3991, 5123);
                    }
                    else {
                        this.anInterface8_4880 = this.aHa_Sub1_4865.method1838(5123, aClass98_Sub22_Sub2_4446.aByteArray3992, n + 263, true, aClass98_Sub22_Sub2_4446.anInt3991);
                    }
                    this.aClass322_4838.anInterface8_2711 = this.anInterface8_4880;
                }
                else {
                    this.aClass322_4838.anInterface8_2711 = this.aHa_Sub1_4865.method1838(5123, aClass98_Sub22_Sub2_4446.aByteArray3992, 7, false, aClass98_Sub22_Sub2_4446.anInt3991);
                }
                if (!b) {
                    this.aBoolean4871 = true;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.W(" + b + ',' + n + ')');
        }
    }
    
    @Override
    final void p(final int n, final int n2, final s s, final s s2, final int n3, final int n4, final int n5) {
        try {
            if (!this.aBoolean4847) {
                this.method2380(-102);
            }
            final int n6 = n3 + this.aShort4845;
            final int n7 = n3 - -this.aShort4862;
            final int n8 = this.aShort4892 + n5;
            final int n9 = this.aShort4891 + n5;
            if ((n != 1 && n != 2 && ~n != 0xFFFFFFFC && ~n != 0xFFFFFFFA) || (~n6 <= -1 && n7 + s.anInt2206 >> s.anInt2200 < s.anInt2203 && n8 >= 0 && ~(n9 - -s.anInt2206 >> s.anInt2200) > ~s.anInt2204)) {
                if (~n != 0xFFFFFFFB && n != 5) {
                    final int n10 = n6 >> s.anInt2200;
                    final int n11 = -1 - -s.anInt2206 + n7 >> s.anInt2200;
                    final int n12 = n8 >> s.anInt2200;
                    final int n13 = n9 + (s.anInt2206 - 1) >> s.anInt2200;
                    if (~n4 == ~s.method3420(n12, -12639, n10) && ~n4 == ~s.method3420(n12, -12639, n11) && ~s.method3420(n13, -12639, n10) == ~n4 && ~n4 == ~s.method3420(n13, -12639, n11)) {
                        return;
                    }
                }
                else if (s2 == null || n6 < 0 || ~s2.anInt2203 >= ~(n7 + s2.anInt2206 >> s2.anInt2200) || ~n8 > -1 || ~s2.anInt2204 >= ~(s2.anInt2206 + n9 >> s2.anInt2200)) {
                    return;
                }
                if (n != 1) {
                    if (n == 2) {
                        final short aShort4868 = this.aShort4868;
                        if (~aShort4868 == -1) {
                            return;
                        }
                        for (int n14 = 0; ~n14 > ~this.anInt4883; ++n14) {
                            final int n15 = (this.anIntArray4856[n14] << 817688464) / aShort4868;
                            if (n2 > n15) {
                                this.anIntArray4856[n14] -= -((-n15 + n2) * (s.method3417(this.anIntArray4886[n14] + n3, n5 + this.anIntArray4843[n14], true) - n4) / n2);
                            }
                        }
                    }
                    else if (n != 3) {
                        if (~n == 0xFFFFFFFB) {
                            final short n16 = (short)(-this.aShort4868 + this.aShort4879);
                            for (int n17 = 0; this.anInt4883 > n17; ++n17) {
                                this.anIntArray4856[n17] = n16 + (this.anIntArray4856[n17] + -n4 + s2.method3417(this.anIntArray4886[n17] + n3, this.anIntArray4843[n17] - -n5, true));
                            }
                        }
                        else if (n == 5) {
                            final short n18 = (short)(this.aShort4879 + -this.aShort4868);
                            for (int n19 = 0; ~n19 > ~this.anInt4883; ++n19) {
                                final int n20 = n3 + this.anIntArray4886[n19];
                                final int n21 = this.anIntArray4843[n19] + n5;
                                final int method3417 = s.method3417(n20, n21, true);
                                this.anIntArray4856[n19] = method3417 + (-n4 + ((this.anIntArray4856[n19] << -126482776) / n18 * (-n2 + -s2.method3417(n20, n21, true) + method3417) >> -1901372472));
                            }
                        }
                    }
                    else {
                        final int n22 = (0xFF & n2) * 4;
                        final int n23 = 4 * ((n2 & 0xFF4F) >> -297654872);
                        final int n24 = (n2 >> -1359051408 & 0xFF) << -2089313818;
                        final int n25 = (0xFF & n2 >> 2080117048) << -1664080666;
                        if (-(n22 >> -1692679999) + n3 < 0 || ~(s.anInt2203 << s.anInt2200) >= ~(s.anInt2206 + (n22 >> -2043148255) + n3) || n5 - (n23 >> -1686934975) < 0 || (n23 >> 750312321) + (n5 + s.anInt2206) >= s.anInt2204 << s.anInt2200) {
                            return;
                        }
                        this.method2336(n23, n22, s, n4, n3, n5, n24, 2, n25);
                    }
                }
                else {
                    for (int i = 0; i < this.anInt4883; ++i) {
                        this.anIntArray4856[i] = this.anIntArray4856[i] + s.method3417(n3 + this.anIntArray4886[i], this.anIntArray4843[i] + n5, true) + -n4;
                    }
                }
                this.aBoolean4847 = false;
                if (this.aClass104_4889 != null) {
                    this.aClass104_4889.anInterface16_902 = null;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.p(" + n + ',' + n2 + ',' + ((s != null) ? "{...}" : "null") + ',' + ((s2 != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    private final boolean method2389(final int n, final int n2, final int n3, final Class111 class111, final boolean b, final int n4, final boolean b2) {
        try {
            final Class111_Sub1 class111_Sub1 = (Class111_Sub1)class111;
            final Class111_Sub1 aClass111_Sub1_4348 = this.aHa_Sub1_4865.aClass111_Sub1_4348;
            final float n5 = aClass111_Sub1_4348.aFloat4680 * class111_Sub1.aFloat4677 + (aClass111_Sub1_4348.aFloat4686 * class111_Sub1.aFloat4674 + class111_Sub1.aFloat4683 * aClass111_Sub1_4348.aFloat4679) + aClass111_Sub1_4348.aFloat4674;
            final float n6 = aClass111_Sub1_4348.aFloat4683 + (aClass111_Sub1_4348.aFloat4687 * class111_Sub1.aFloat4677 + (aClass111_Sub1_4348.aFloat4675 * class111_Sub1.aFloat4683 + aClass111_Sub1_4348.aFloat4678 * class111_Sub1.aFloat4674));
            Class287_Sub2.aFloat3273 = class111_Sub1.aFloat4687 * aClass111_Sub1_4348.aFloat4676 + aClass111_Sub1_4348.aFloat4684 * class111_Sub1.aFloat4680 + class111_Sub1.aFloat4673 * aClass111_Sub1_4348.aFloat4673;
            Class372.aFloat3151 = class111_Sub1.aFloat4684 * aClass111_Sub1_4348.aFloat4673 + (class111_Sub1.aFloat4678 * aClass111_Sub1_4348.aFloat4676 + aClass111_Sub1_4348.aFloat4684 * class111_Sub1.aFloat4686);
            aa_Sub3.aFloat3567 = class111_Sub1.aFloat4680 * aClass111_Sub1_4348.aFloat4686 + class111_Sub1.aFloat4687 * aClass111_Sub1_4348.aFloat4679 + class111_Sub1.aFloat4673 * aClass111_Sub1_4348.aFloat4680;
            Class98_Sub41.aFloat4204 = aClass111_Sub1_4348.aFloat4678 * class111_Sub1.aFloat4686 + aClass111_Sub1_4348.aFloat4675 * class111_Sub1.aFloat4678 + aClass111_Sub1_4348.aFloat4687 * class111_Sub1.aFloat4684;
            final float n7 = aClass111_Sub1_4348.aFloat4684 * class111_Sub1.aFloat4674 + class111_Sub1.aFloat4683 * aClass111_Sub1_4348.aFloat4676 + aClass111_Sub1_4348.aFloat4673 * class111_Sub1.aFloat4677 + aClass111_Sub1_4348.aFloat4677;
            Class48_Sub1_Sub2.aFloat5515 = class111_Sub1.aFloat4676 * aClass111_Sub1_4348.aFloat4687 + (class111_Sub1.aFloat4679 * aClass111_Sub1_4348.aFloat4678 + aClass111_Sub1_4348.aFloat4675 * class111_Sub1.aFloat4675);
            Class286.aFloat2182 = class111_Sub1.aFloat4676 * aClass111_Sub1_4348.aFloat4680 + (aClass111_Sub1_4348.aFloat4686 * class111_Sub1.aFloat4679 + class111_Sub1.aFloat4675 * aClass111_Sub1_4348.aFloat4679);
            Class369.aFloat3131 = class111_Sub1.aFloat4684 * aClass111_Sub1_4348.aFloat4680 + (aClass111_Sub1_4348.aFloat4686 * class111_Sub1.aFloat4686 + class111_Sub1.aFloat4678 * aClass111_Sub1_4348.aFloat4679);
            Class151_Sub4.aFloat4989 = aClass111_Sub1_4348.aFloat4673 * class111_Sub1.aFloat4676 + (aClass111_Sub1_4348.aFloat4676 * class111_Sub1.aFloat4675 + class111_Sub1.aFloat4679 * aClass111_Sub1_4348.aFloat4684);
            Class378.aFloat3190 = class111_Sub1.aFloat4673 * aClass111_Sub1_4348.aFloat4687 + (aClass111_Sub1_4348.aFloat4675 * class111_Sub1.aFloat4687 + aClass111_Sub1_4348.aFloat4678 * class111_Sub1.aFloat4680);
            boolean b3 = b;
            float n8 = Float.MAX_VALUE;
            float n9 = -3.4028235E38f;
            float n10 = Float.MAX_VALUE;
            float n11 = -3.4028235E38f;
            final int anInt4419 = this.aHa_Sub1_4865.anInt4419;
            final int anInt4420 = this.aHa_Sub1_4865.anInt4381;
            if (!this.aBoolean4847) {
                this.method2380(-125);
            }
            final int n12 = -this.aShort4845 + this.aShort4862 >> -1556790271;
            final int n13 = this.aShort4879 - this.aShort4868 >> -1820982719;
            final int n14 = -this.aShort4892 + this.aShort4891 >> -1363969407;
            final short n15 = (short)(this.aShort4845 - -n12);
            final short n16 = (short)(this.aShort4868 + n13);
            final short n17 = (short)(this.aShort4892 - -n14);
            final int n18 = -(n12 << n) + n15;
            final int n19 = n16 + -(n13 << n);
            final int n20 = n17 - (n14 << n);
            final int n21 = n15 - -(n12 << n);
            final int n22 = (n13 << n) + n16;
            final int n23 = n17 + (n14 << n);
            Class342.anIntArray2859[0] = n18;
            Class27.anIntArray278[0] = n19;
            Class305.anIntArray2541[0] = n20;
            Class342.anIntArray2859[1] = n21;
            Class27.anIntArray278[1] = n19;
            Class305.anIntArray2541[1] = n20;
            Class342.anIntArray2859[2] = n18;
            Class27.anIntArray278[2] = n22;
            Class305.anIntArray2541[2] = n20;
            Class342.anIntArray2859[3] = n21;
            Class27.anIntArray278[3] = n22;
            Class305.anIntArray2541[3] = n20;
            Class342.anIntArray2859[4] = n18;
            Class27.anIntArray278[4] = n19;
            Class342.anIntArray2859[5] = n21;
            Class305.anIntArray2541[4] = n23;
            Class27.anIntArray278[5] = n19;
            Class305.anIntArray2541[5] = n23;
            Class342.anIntArray2859[6] = n18;
            Class27.anIntArray278[6] = n22;
            Class342.anIntArray2859[7] = n21;
            Class305.anIntArray2541[6] = n23;
            Class27.anIntArray278[7] = n22;
            Class305.anIntArray2541[7] = n23;
            for (int n24 = 0; ~n24 > -9; ++n24) {
                final float n25 = Class305.anIntArray2541[n24];
                final float n26 = Class27.anIntArray278[n24];
                final float n27 = Class342.anIntArray2859[n24];
                float n28 = n7 + (n26 * Class151_Sub4.aFloat4989 + Class372.aFloat3151 * n27 + Class287_Sub2.aFloat3273 * n25);
                final float n29 = n6 + (Class378.aFloat3190 * n25 + (Class98_Sub41.aFloat4204 * n27 + Class48_Sub1_Sub2.aFloat5515 * n26));
                final float n30 = Class369.aFloat3131 * n27 + Class286.aFloat2182 * n26 + aa_Sub3.aFloat3567 * n25 + n5;
                if (this.aHa_Sub1_4865.anInt4404 <= n28) {
                    if (n4 > 0) {
                        n28 = n4;
                    }
                    final float n31 = this.aHa_Sub1_4865.anInt4451 + n30 * anInt4419 / n28;
                    if (n9 < n31) {
                        n9 = n31;
                    }
                    final float n32 = anInt4420 * n29 / n28 + this.aHa_Sub1_4865.anInt4394;
                    if (n8 > n31) {
                        n8 = n31;
                    }
                    if (n32 < n10) {
                        n10 = n32;
                    }
                    if (n11 < n32) {
                        n11 = n32;
                    }
                    b3 = true;
                }
            }
            if (b3 && n3 > n8 && n3 < n9 && n2 > n10 && n11 > n2) {
                if (b2) {
                    return true;
                }
                if (~this.anInt4846 < ~this.aHa_Sub1_4865.anIntArray4471.length) {
                    this.aHa_Sub1_4865.anIntArray4471 = new int[this.anInt4846];
                    this.aHa_Sub1_4865.anIntArray4470 = new int[this.anInt4846];
                }
                final int[] anIntArray4471 = this.aHa_Sub1_4865.anIntArray4471;
                final int[] anIntArray4472 = this.aHa_Sub1_4865.anIntArray4470;
                for (int n33 = 0; ~this.anInt4883 < ~n33; ++n33) {
                    final float n34 = this.anIntArray4856[n33];
                    final float n35 = this.anIntArray4886[n33];
                    final float n36 = this.anIntArray4843[n33];
                    final float n37 = Class286.aFloat2182 * n34 + n35 * Class369.aFloat3131 + aa_Sub3.aFloat3567 * n36 + n5;
                    final float n38 = Class378.aFloat3190 * n36 + (Class98_Sub41.aFloat4204 * n35 + Class48_Sub1_Sub2.aFloat5515 * n34) + n6;
                    float n39 = n35 * Class372.aFloat3151 + Class151_Sub4.aFloat4989 * n34 + Class287_Sub2.aFloat3273 * n36 + n7;
                    if (n39 < this.aHa_Sub1_4865.anInt4404) {
                        final int n40 = this.anIntArray4851[n33];
                        for (int n41 = this.anIntArray4851[n33 + 1], n42 = n40; n41 > n42 && ~(this.aShortArray4887[n42] - 1) != 0x0; ++n42) {
                            anIntArray4471[-1 + this.aShortArray4887[n42]] = -999999;
                        }
                    }
                    else {
                        if (~n4 < -1) {
                            n39 = n4;
                        }
                        final int n43 = (int)(anInt4419 * n37 / n39 + this.aHa_Sub1_4865.anInt4451);
                        final int n44 = (int)(n38 * anInt4420 / n39 + this.aHa_Sub1_4865.anInt4394);
                        final int n45 = this.anIntArray4851[n33];
                        for (int n46 = this.anIntArray4851[n33 + 1], n47 = n45; ~n47 > ~n46; ++n47) {
                            final short n48 = (short)(-1 + this.aShortArray4887[n47]);
                            if (~n48 == 0x0) {
                                break;
                            }
                            anIntArray4471[n48] = n43;
                            anIntArray4472[n48] = n44;
                        }
                    }
                }
                for (int n49 = 0; ~this.anInt4853 < ~n49; ++n49) {
                    if (anIntArray4471[this.aShortArray4895[n49]] != -999999 && ~anIntArray4471[this.aShortArray4852[n49]] != 0xF423E && ~anIntArray4471[this.aShortArray4878[n49]] != 0xF423E && this.method2377(anIntArray4472[this.aShortArray4878[n49]], anIntArray4472[this.aShortArray4852[n49]], n2, anIntArray4471[this.aShortArray4878[n49]], anIntArray4471[this.aShortArray4852[n49]], anIntArray4471[this.aShortArray4895[n49]], anIntArray4472[this.aShortArray4895[n49]], 18818, n3)) {
                        return true;
                    }
                }
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.GA(" + n + ',' + n2 + ',' + n3 + ',' + ((class111 != null) ? "{...}" : "null") + ',' + b + ',' + n4 + ',' + b2 + ')');
        }
    }
    
    @Override
    final void FA(final int n) {
        try {
            final int n2 = Class284_Sub2_Sub2.anIntArray6200[n];
            final int n3 = Class284_Sub2_Sub2.anIntArray6202[n];
            for (int n4 = 0; ~n4 > ~this.anInt4883; ++n4) {
                final int n5 = -(this.anIntArray4843[n4] * n2) + this.anIntArray4856[n4] * n3 >> -2129883186;
                this.anIntArray4843[n4] = this.anIntArray4843[n4] * n3 + this.anIntArray4856[n4] * n2 >> 526561358;
                this.anIntArray4856[n4] = n5;
            }
            if (this.aClass104_4889 != null) {
                this.aClass104_4889.anInterface16_902 = null;
            }
            this.aBoolean4847 = false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.FA(" + n + ')');
        }
    }
    
    @Override
    final void method2337(final int n, final int n2, final int n3, final int n4) {
        try {
            for (int n5 = 0; ~n5 > ~this.anInt4853; ++n5) {
                final int n6 = this.aShortArray4842[n5] & 0xFFFF;
                int n7 = n6 >> -607227062 & 0x3F;
                int n8 = (n6 & 0x3CE) >> 1208626503;
                if (n2 != -1) {
                    n8 += (-n8 + n2) * n4 >> 264868455;
                }
                int n9 = 0x7F & n6;
                if (~n != 0x0) {
                    n7 += n4 * (n + -n7) >> -1575875737;
                }
                if (~n3 != 0x0) {
                    n9 += (n3 + -n9) * n4 >> 174512807;
                }
                this.aShortArray4842[n5] = (short)Class41.method366(Class41.method366(n7 << -1196647414, n8 << 1527891239), n9);
            }
            if (this.aClass249Array4877 != null) {
                for (int n10 = 0; this.anInt4866 > n10; ++n10) {
                    final Class249 class249 = this.aClass249Array4877[n10];
                    final Class219 class250 = this.aClass219Array4861[n10];
                    class250.anInt1643 = ((class250.anInt1643 & 0xFF000000) | (Class208.anIntArray1579[0xFFFF & this.aShortArray4842[class249.anInt1905]] & 0xFFFFFF));
                }
            }
            if (this.aClass104_4854 != null) {
                this.aClass104_4854.anInterface16_902 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.HB(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    @Override
    final int WA() {
        try {
            return this.aShort4867;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.WA()");
        }
    }
    
    @Override
    final void H(final int n, final int n2, final int n3) {
        try {
            for (int n4 = 0; ~n4 > ~this.anInt4883; ++n4) {
                if (n != 0) {
                    final int[] anIntArray4886 = this.anIntArray4886;
                    final int n5 = n4;
                    anIntArray4886[n5] += n;
                }
                if (n2 != 0) {
                    final int[] anIntArray4887 = this.anIntArray4856;
                    final int n6 = n4;
                    anIntArray4887[n6] += n2;
                }
                if (~n3 != -1) {
                    final int[] anIntArray4888 = this.anIntArray4843;
                    final int n7 = n4;
                    anIntArray4888[n7] += n3;
                }
            }
            this.aBoolean4847 = false;
            if (this.aClass104_4889 != null) {
                this.aClass104_4889.anInterface16_902 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.H(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final boolean F() {
        try {
            return this.aBoolean4857;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.F()");
        }
    }
    
    @Override
    final int HA() {
        try {
            if (!this.aBoolean4847) {
                this.method2380(-88);
            }
            return this.aShort4892;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.HA()");
        }
    }
    
    @Override
    final void method2329(final Class111 class111, final Class246_Sub6 class246_Sub6, final int n, final int n2) {
        try {
            if (this.anInt4846 != 0) {
                final Class111_Sub1 aClass111_Sub1_4348 = this.aHa_Sub1_4865.aClass111_Sub1_4348;
                final Class111_Sub1 class111_Sub1 = (Class111_Sub1)class111;
                if (!this.aBoolean4847) {
                    this.method2380(-124);
                }
                Class48_Sub1_Sub1.aFloat5505 = class111_Sub1.aFloat4677 * aClass111_Sub1_4348.aFloat4673 + (aClass111_Sub1_4348.aFloat4684 * class111_Sub1.aFloat4674 + aClass111_Sub1_4348.aFloat4676 * class111_Sub1.aFloat4683) + aClass111_Sub1_4348.aFloat4677;
                Class151_Sub4.aFloat4989 = class111_Sub1.aFloat4679 * aClass111_Sub1_4348.aFloat4684 + aClass111_Sub1_4348.aFloat4676 * class111_Sub1.aFloat4675 + aClass111_Sub1_4348.aFloat4673 * class111_Sub1.aFloat4676;
                final float n3 = Class48_Sub1_Sub1.aFloat5505 + Class151_Sub4.aFloat4989 * this.aShort4868;
                final float n4 = Class151_Sub4.aFloat4989 * this.aShort4879 + Class48_Sub1_Sub1.aFloat5505;
                float n5;
                float n6;
                if (n4 < n3) {
                    n5 = n4 - this.aShort4876;
                    n6 = this.aShort4876 + n3;
                }
                else {
                    n6 = this.aShort4876 + n4;
                    n5 = -this.aShort4876 + n3;
                }
                if (this.aHa_Sub1_4865.aFloat4379 > n5 && this.aHa_Sub1_4865.anInt4404 < n6) {
                    Class50.aFloat419 = aClass111_Sub1_4348.aFloat4674 + (aClass111_Sub1_4348.aFloat4686 * class111_Sub1.aFloat4674 + aClass111_Sub1_4348.aFloat4679 * class111_Sub1.aFloat4683 + aClass111_Sub1_4348.aFloat4680 * class111_Sub1.aFloat4677);
                    Class286.aFloat2182 = aClass111_Sub1_4348.aFloat4686 * class111_Sub1.aFloat4679 + class111_Sub1.aFloat4675 * aClass111_Sub1_4348.aFloat4679 + class111_Sub1.aFloat4676 * aClass111_Sub1_4348.aFloat4680;
                    final float n7 = this.aShort4868 * Class286.aFloat2182 + Class50.aFloat419;
                    final float n8 = this.aShort4879 * Class286.aFloat2182 + Class50.aFloat419;
                    float n9;
                    float n10;
                    if (n7 > n8) {
                        n9 = (n7 + this.aShort4876) * this.aHa_Sub1_4865.anInt4419;
                        n10 = (n8 - this.aShort4876) * this.aHa_Sub1_4865.anInt4419;
                    }
                    else {
                        n9 = (n8 + this.aShort4876) * this.aHa_Sub1_4865.anInt4419;
                        n10 = (n7 - this.aShort4876) * this.aHa_Sub1_4865.anInt4419;
                    }
                    if (this.aHa_Sub1_4865.aFloat4364 > n10 / n && this.aHa_Sub1_4865.aFloat4421 < n9 / n) {
                        Class48_Sub1_Sub2.aFloat5515 = class111_Sub1.aFloat4676 * aClass111_Sub1_4348.aFloat4687 + (class111_Sub1.aFloat4675 * aClass111_Sub1_4348.aFloat4675 + aClass111_Sub1_4348.aFloat4678 * class111_Sub1.aFloat4679);
                        Class376.aFloat3171 = aClass111_Sub1_4348.aFloat4683 + (class111_Sub1.aFloat4677 * aClass111_Sub1_4348.aFloat4687 + (aClass111_Sub1_4348.aFloat4675 * class111_Sub1.aFloat4683 + class111_Sub1.aFloat4674 * aClass111_Sub1_4348.aFloat4678));
                        final float n11 = Class376.aFloat3171 + this.aShort4868 * Class48_Sub1_Sub2.aFloat5515;
                        final float n12 = Class376.aFloat3171 + Class48_Sub1_Sub2.aFloat5515 * this.aShort4879;
                        float n13;
                        float n14;
                        if (n11 <= n12) {
                            n13 = (n12 + this.aShort4876) * this.aHa_Sub1_4865.anInt4381;
                            n14 = this.aHa_Sub1_4865.anInt4381 * (n11 - this.aShort4876);
                        }
                        else {
                            n14 = this.aHa_Sub1_4865.anInt4381 * (n12 - this.aShort4876);
                            n13 = (this.aShort4876 + n11) * this.aHa_Sub1_4865.anInt4381;
                        }
                        if (n14 / n < this.aHa_Sub1_4865.aFloat4437 && n13 / n > this.aHa_Sub1_4865.aFloat4359) {
                            if (class246_Sub6 != null || this.aClass249Array4877 != null) {
                                aa_Sub3.aFloat3567 = aClass111_Sub1_4348.aFloat4686 * class111_Sub1.aFloat4680 + aClass111_Sub1_4348.aFloat4679 * class111_Sub1.aFloat4687 + class111_Sub1.aFloat4673 * aClass111_Sub1_4348.aFloat4680;
                                Class98_Sub41.aFloat4204 = class111_Sub1.aFloat4686 * aClass111_Sub1_4348.aFloat4678 + class111_Sub1.aFloat4678 * aClass111_Sub1_4348.aFloat4675 + aClass111_Sub1_4348.aFloat4687 * class111_Sub1.aFloat4684;
                                Class287_Sub2.aFloat3273 = class111_Sub1.aFloat4687 * aClass111_Sub1_4348.aFloat4676 + class111_Sub1.aFloat4680 * aClass111_Sub1_4348.aFloat4684 + class111_Sub1.aFloat4673 * aClass111_Sub1_4348.aFloat4673;
                                Class372.aFloat3151 = aClass111_Sub1_4348.aFloat4673 * class111_Sub1.aFloat4684 + (class111_Sub1.aFloat4686 * aClass111_Sub1_4348.aFloat4684 + aClass111_Sub1_4348.aFloat4676 * class111_Sub1.aFloat4678);
                                Class369.aFloat3131 = aClass111_Sub1_4348.aFloat4686 * class111_Sub1.aFloat4686 + class111_Sub1.aFloat4678 * aClass111_Sub1_4348.aFloat4679 + class111_Sub1.aFloat4684 * aClass111_Sub1_4348.aFloat4680;
                                Class378.aFloat3190 = class111_Sub1.aFloat4673 * aClass111_Sub1_4348.aFloat4687 + (class111_Sub1.aFloat4687 * aClass111_Sub1_4348.aFloat4675 + aClass111_Sub1_4348.aFloat4678 * class111_Sub1.aFloat4680);
                            }
                            if (class246_Sub6 != null) {
                                final int n15 = this.aShort4845 - -this.aShort4862 >> 1682073089;
                                final int n16 = this.aShort4892 - -this.aShort4891 >> 1604314785;
                                final int n17 = (int)(aa_Sub3.aFloat3567 * n16 + (Class286.aFloat2182 * this.aShort4868 + (Class369.aFloat3131 * n15 + Class50.aFloat419)));
                                final int n18 = (int)(n16 * Class378.aFloat3190 + (n15 * Class98_Sub41.aFloat4204 + Class376.aFloat3171 + Class48_Sub1_Sub2.aFloat5515 * this.aShort4868));
                                final int n19 = (int)(Class287_Sub2.aFloat3273 * n16 + (Class48_Sub1_Sub1.aFloat5505 + n15 * Class372.aFloat3151 + Class151_Sub4.aFloat4989 * this.aShort4868));
                                final int n20 = (int)(n16 * aa_Sub3.aFloat3567 + (Class369.aFloat3131 * n15 + Class50.aFloat419 + this.aShort4879 * Class286.aFloat2182));
                                final int n21 = (int)(Class376.aFloat3171 + Class98_Sub41.aFloat4204 * n15 + this.aShort4879 * Class48_Sub1_Sub2.aFloat5515 + n16 * Class378.aFloat3190);
                                final int n22 = (int)(Class151_Sub4.aFloat4989 * this.aShort4879 + (Class372.aFloat3151 * n15 + Class48_Sub1_Sub1.aFloat5505) + Class287_Sub2.aFloat3273 * n16);
                                class246_Sub6.anInt5112 = this.aHa_Sub1_4865.anInt4381 * n21 / n + this.aHa_Sub1_4865.anInt4394;
                                class246_Sub6.anInt5110 = this.aHa_Sub1_4865.anInt4451 + this.aHa_Sub1_4865.anInt4419 * n20 / n;
                                class246_Sub6.anInt5113 = this.aHa_Sub1_4865.anInt4394 + this.aHa_Sub1_4865.anInt4381 * n18 / n;
                                class246_Sub6.anInt5111 = this.aHa_Sub1_4865.anInt4451 - -(n17 * this.aHa_Sub1_4865.anInt4419 / n);
                                if (~this.aHa_Sub1_4865.anInt4404 >= ~n19 || ~n22 <= ~this.aHa_Sub1_4865.anInt4404) {
                                    class246_Sub6.aBoolean5114 = true;
                                    class246_Sub6.anInt5109 = this.aHa_Sub1_4865.anInt4451 + this.aHa_Sub1_4865.anInt4419 * (this.aShort4876 + n17) / n - class246_Sub6.anInt5111;
                                }
                            }
                            this.aHa_Sub1_4865.method1890(n, true);
                            this.aHa_Sub1_4865.method1901((byte)(-35));
                            this.aHa_Sub1_4865.method1883(class111_Sub1, (byte)(-125));
                            this.method2382((byte)(-104));
                            this.aHa_Sub1_4865.method1902((byte)60);
                            this.method2387(-32768);
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.U(" + ((class111 != null) ? "{...}" : "null") + ',' + ((class246_Sub6 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final boolean method2333(final int n, final int n2, final Class111 class111, final boolean b, final int n3, final int n4) {
        try {
            return this.method2389(n3, n2, n, class111, false, n4, b);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.KB(" + n + ',' + n2 + ',' + ((class111 != null) ? "{...}" : "null") + ',' + b + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    @Override
    final void method2342() {
    }
    
    @Override
    final void method2327() {
    }
    
    @Override
    final Class35[] method2322() {
        try {
            return this.aClass35Array4863;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.PB()");
        }
    }
    
    @Override
    final void s(final int anInt4894) {
        try {
            this.anInt4894 = anInt4894;
            if (this.aClass14_4898 != null && (this.anInt4894 & 0x10000) == 0x0) {
                this.aByteArray4858 = this.aClass14_4898.aByteArray168;
                this.aShortArray4860 = this.aClass14_4898.aShortArray166;
                this.aShortArray4884 = this.aClass14_4898.aShortArray165;
                this.aShortArray4849 = this.aClass14_4898.aShortArray167;
                this.aClass14_4898 = null;
            }
            this.aBoolean4871 = true;
            this.method2383(-122);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.s(" + anInt4894 + ')');
        }
    }
    
    private final void method2390(final int n, final boolean b) {
        try {
            if (n >= 9) {
                boolean b2 = this.aClass104_4854 != null && this.aClass104_4854.anInterface16_902 == null;
                boolean b3 = this.aClass104_4859 != null && this.aClass104_4859.anInterface16_902 == null;
                boolean b4 = this.aClass104_4889 != null && this.aClass104_4889.anInterface16_902 == null;
                boolean b5 = this.aClass104_4874 != null && this.aClass104_4874.anInterface16_902 == null;
                if (b) {
                    b5 &= (~(0x8 & this.aByte4885) != -1);
                    b2 &= (~(this.aByte4885 & 0x2) != -1);
                    b3 &= ((0x4 & this.aByte4885) != 0x0);
                    b4 &= ((0x1 & this.aByte4885) != 0x0);
                }
                byte b6 = 0;
                byte aByte898 = 0;
                byte aByte899 = 0;
                byte b7 = 0;
                if (b4) {
                    aByte898 = b6;
                    b6 += 12;
                }
                byte b8 = 0;
                if (b2) {
                    aByte899 = b6;
                    b6 += 4;
                }
                if (b3) {
                    b7 = b6;
                    b6 += 12;
                }
                if (b5) {
                    b8 = b6;
                    b6 += 8;
                }
                if (~b6 != -1) {
                    if (~(this.anInt4846 * b6) < ~this.aHa_Sub1_4865.aClass98_Sub22_Sub2_4446.aByteArray3992.length) {
                        this.aHa_Sub1_4865.aClass98_Sub22_Sub2_4446 = new Class98_Sub22_Sub2(b6 * (100 + this.anInt4846));
                    }
                    else {
                        this.aHa_Sub1_4865.aClass98_Sub22_Sub2_4446.anInt3991 = 0;
                    }
                    final Class98_Sub22_Sub2 aClass98_Sub22_Sub2_4446 = this.aHa_Sub1_4865.aClass98_Sub22_Sub2_4446;
                    if (b4) {
                        if (this.aHa_Sub1_4865.aBoolean4397) {
                            for (int n2 = 0; ~n2 > ~this.anInt4883; ++n2) {
                                final int floatToRawIntBits = Stream.floatToRawIntBits(this.anIntArray4886[n2]);
                                final int floatToRawIntBits2 = Stream.floatToRawIntBits(this.anIntArray4856[n2]);
                                final int floatToRawIntBits3 = Stream.floatToRawIntBits(this.anIntArray4843[n2]);
                                final int n3 = this.anIntArray4851[n2];
                                for (int n4 = this.anIntArray4851[n2 + 1], n5 = n3; ~n4 < ~n5; ++n5) {
                                    final short n6 = (short)(-1 + this.aShortArray4887[n5]);
                                    if (n6 == -1) {
                                        break;
                                    }
                                    aClass98_Sub22_Sub2_4446.anInt3991 = n6 * b6;
                                    aClass98_Sub22_Sub2_4446.writeInt(1571862888, floatToRawIntBits);
                                    aClass98_Sub22_Sub2_4446.writeInt(1571862888, floatToRawIntBits2);
                                    aClass98_Sub22_Sub2_4446.writeInt(1571862888, floatToRawIntBits3);
                                }
                            }
                        }
                        else {
                            for (int n7 = 0; this.anInt4883 > n7; ++n7) {
                                final int floatToRawIntBits4 = Stream.floatToRawIntBits(this.anIntArray4886[n7]);
                                final int floatToRawIntBits5 = Stream.floatToRawIntBits(this.anIntArray4856[n7]);
                                final int floatToRawIntBits6 = Stream.floatToRawIntBits(this.anIntArray4843[n7]);
                                final int n8 = this.anIntArray4851[n7];
                                for (int n9 = this.anIntArray4851[1 + n7], i = n8; i < n9; ++i) {
                                    final short n10 = (short)(this.aShortArray4887[i] - 1);
                                    if (~n10 == 0x0) {
                                        break;
                                    }
                                    aClass98_Sub22_Sub2_4446.anInt3991 = n10 * b6;
                                    aClass98_Sub22_Sub2_4446.method1218(floatToRawIntBits4, 1489446952);
                                    aClass98_Sub22_Sub2_4446.method1218(floatToRawIntBits5, 1489446952);
                                    aClass98_Sub22_Sub2_4446.method1218(floatToRawIntBits6, 1489446952);
                                }
                            }
                        }
                    }
                    if (b2) {
                        if (this.aClass104_4859 != null) {
                            for (int n11 = 0; ~n11 > ~this.anInt4853; ++n11) {
                                final int method2385 = this.method2385(this.aShortArray4842[n11], this.aByteArray4882[n11], this.aShortArray4869[n11], this.aShort4867, (byte)(-97));
                                aClass98_Sub22_Sub2_4446.anInt3991 = this.aShortArray4895[n11] * b6 + aByte899;
                                aClass98_Sub22_Sub2_4446.writeInt(1571862888, method2385);
                                aClass98_Sub22_Sub2_4446.anInt3991 = b6 * this.aShortArray4852[n11] + aByte899;
                                aClass98_Sub22_Sub2_4446.writeInt(1571862888, method2385);
                                aClass98_Sub22_Sub2_4446.anInt3991 = this.aShortArray4878[n11] * b6 + aByte899;
                                aClass98_Sub22_Sub2_4446.writeInt(1571862888, method2385);
                            }
                        }
                        else {
                            short[] array;
                            byte[] array2;
                            short[] array3;
                            short[] array4;
                            if (this.aClass14_4898 == null) {
                                array = this.aShortArray4849;
                                array2 = this.aByteArray4858;
                                array3 = this.aShortArray4860;
                                array4 = this.aShortArray4884;
                            }
                            else {
                                array4 = this.aClass14_4898.aShortArray165;
                                array2 = this.aClass14_4898.aByteArray168;
                                array = this.aClass14_4898.aShortArray167;
                                array3 = this.aClass14_4898.aShortArray166;
                            }
                            final float n12 = this.aHa_Sub1_4865.aFloatArray4438[0];
                            final float n13 = this.aHa_Sub1_4865.aFloatArray4438[1];
                            final float n14 = this.aHa_Sub1_4865.aFloatArray4438[2];
                            final float aFloat4413 = this.aHa_Sub1_4865.aFloat4413;
                            final float n15 = 768.0f * this.aHa_Sub1_4865.aFloat4435 / this.aShort4872;
                            final float n16 = 768.0f * this.aHa_Sub1_4865.aFloat4407 / this.aShort4872;
                            for (int j = 0; j < this.anInt4853; ++j) {
                                final int method2386 = this.method2385(this.aShortArray4842[j], this.aByteArray4882[j], this.aShortArray4869[j], this.aShort4867, (byte)(-97));
                                final float n17 = (0xFF & method2386 >> -2032468944) * this.aHa_Sub1_4865.aFloat4420;
                                final short n18 = this.aShortArray4895[j];
                                final float n19 = (0xFF & method2386 >> -1878108920) * this.aHa_Sub1_4865.aFloat4458;
                                final float n20 = this.aHa_Sub1_4865.aFloat4433 * (method2386 >>> -4756936);
                                final short n21 = array2[n18];
                                float n22;
                                if (~n21 == -1) {
                                    n22 = (array4[n18] * n13 + n12 * array3[n18] + n14 * array[n18]) * 0.0026041667f;
                                }
                                else {
                                    n22 = (array3[n18] * n12 + n13 * array4[n18] + array[n18] * n14) / (n21 * 256);
                                }
                                final float n23 = aFloat4413 + ((n22 < 0.0f) ? n16 : n15) * n22;
                                int n24 = (int)(n20 * n23);
                                if (~n24 > -1) {
                                    n24 = 0;
                                }
                                else if (~n24 < -256) {
                                    n24 = 255;
                                }
                                int n25 = (int)(n23 * n17);
                                int n26 = (int)(n23 * n19);
                                if (n25 >= 0) {
                                    if (~n25 < -256) {
                                        n25 = 255;
                                    }
                                }
                                else {
                                    n25 = 0;
                                }
                                aClass98_Sub22_Sub2_4446.anInt3991 = aByte899 - -(b6 * n18);
                                if (n26 < 0) {
                                    n26 = 0;
                                }
                                else if (n26 > 255) {
                                    n26 = 255;
                                }
                                aClass98_Sub22_Sub2_4446.method1194(n24, -93);
                                aClass98_Sub22_Sub2_4446.method1194(n25, -90);
                                aClass98_Sub22_Sub2_4446.method1194(n26, -103);
                                aClass98_Sub22_Sub2_4446.method1194(-(0xFF & this.aByteArray4882[j]) + 255, -37);
                                final short n27 = this.aShortArray4852[j];
                                final short n28 = array2[n27];
                                float n29;
                                if (~n28 == -1) {
                                    n29 = (n13 * array4[n27] + n12 * array3[n27] + array[n27] * n14) * 0.0026041667f;
                                }
                                else {
                                    n29 = (array[n27] * n14 + (array3[n27] * n12 + n13 * array4[n27])) / (n28 * 256);
                                }
                                final float n30 = aFloat4413 + ((n29 < 0.0f) ? n16 : n15) * n29;
                                int n31 = (int)(n30 * n20);
                                if (~n31 <= -1) {
                                    if (~n31 < -256) {
                                        n31 = 255;
                                    }
                                }
                                else {
                                    n31 = 0;
                                }
                                int n32 = (int)(n30 * n17);
                                int n33 = (int)(n19 * n30);
                                if (n32 >= 0) {
                                    if (n32 > 255) {
                                        n32 = 255;
                                    }
                                }
                                else {
                                    n32 = 0;
                                }
                                aClass98_Sub22_Sub2_4446.anInt3991 = aByte899 - -(b6 * n27);
                                if (n33 >= 0) {
                                    if (~n33 < -256) {
                                        n33 = 255;
                                    }
                                }
                                else {
                                    n33 = 0;
                                }
                                aClass98_Sub22_Sub2_4446.method1194(n31, 90);
                                aClass98_Sub22_Sub2_4446.method1194(n32, -52);
                                aClass98_Sub22_Sub2_4446.method1194(n33, 118);
                                aClass98_Sub22_Sub2_4446.method1194(255 - (0xFF & this.aByteArray4882[j]), -55);
                                final short n34 = this.aShortArray4878[j];
                                final short n35 = array2[n34];
                                float n36;
                                if (~n35 != -1) {
                                    n36 = (array[n34] * n14 + (array4[n34] * n13 + n12 * array3[n34])) / (256 * n35);
                                }
                                else {
                                    n36 = 0.0026041667f * (n14 * array[n34] + (n13 * array4[n34] + array3[n34] * n12));
                                }
                                final float n37 = aFloat4413 + n36 * ((n36 < 0.0f) ? n16 : n15);
                                int n38 = (int)(n37 * n20);
                                int n39 = (int)(n17 * n37);
                                if (n38 >= 0) {
                                    if (~n38 < -256) {
                                        n38 = 255;
                                    }
                                }
                                else {
                                    n38 = 0;
                                }
                                int n40 = (int)(n19 * n37);
                                if (~n39 <= -1) {
                                    if (~n39 < -256) {
                                        n39 = 255;
                                    }
                                }
                                else {
                                    n39 = 0;
                                }
                                if (~n40 <= -1) {
                                    if (n40 > 255) {
                                        n40 = 255;
                                    }
                                }
                                else {
                                    n40 = 0;
                                }
                                aClass98_Sub22_Sub2_4446.anInt3991 = aByte899 + b6 * n34;
                                aClass98_Sub22_Sub2_4446.method1194(n38, -48);
                                aClass98_Sub22_Sub2_4446.method1194(n39, -46);
                                aClass98_Sub22_Sub2_4446.method1194(n40, 96);
                                aClass98_Sub22_Sub2_4446.method1194(255 - (this.aByteArray4882[j] & 0xFF), -44);
                            }
                        }
                    }
                    if (b3) {
                        byte[] array5;
                        short[] array6;
                        short[] array7;
                        short[] array8;
                        if (this.aClass14_4898 != null) {
                            array5 = this.aClass14_4898.aByteArray168;
                            array6 = this.aClass14_4898.aShortArray167;
                            array7 = this.aClass14_4898.aShortArray166;
                            array8 = this.aClass14_4898.aShortArray165;
                        }
                        else {
                            array5 = this.aByteArray4858;
                            array7 = this.aShortArray4860;
                            array8 = this.aShortArray4884;
                            array6 = this.aShortArray4849;
                        }
                        final float n41 = 3.0f / this.aShort4872;
                        aClass98_Sub22_Sub2_4446.anInt3991 = b7;
                        final float n42 = 3.0f / (this.aShort4872 + this.aShort4872 / 2);
                        if (this.aHa_Sub1_4865.aBoolean4397) {
                            for (int k = 0; k < this.anInt4846; ++k) {
                                final int n43 = array5[k] & 0xFF;
                                if (~n43 != -1) {
                                    final float n44 = n41 / n43;
                                    aClass98_Sub22_Sub2_4446.method1264((byte)(-103), array7[k] * n44);
                                    aClass98_Sub22_Sub2_4446.method1264((byte)71, n44 * array8[k]);
                                    aClass98_Sub22_Sub2_4446.method1264((byte)(-94), array6[k] * n44);
                                }
                                else {
                                    aClass98_Sub22_Sub2_4446.method1264((byte)71, n42 * array7[k]);
                                    aClass98_Sub22_Sub2_4446.method1264((byte)(-1), array8[k] * n42);
                                    aClass98_Sub22_Sub2_4446.method1264((byte)123, array6[k] * n42);
                                }
                                final Class98_Sub22_Sub2 class98_Sub22_Sub2 = aClass98_Sub22_Sub2_4446;
                                class98_Sub22_Sub2.anInt3991 += b6 - 12;
                            }
                        }
                        else {
                            for (int l = 0; l < this.anInt4846; ++l) {
                                final int n45 = array5[l] & 0xFF;
                                if (~n45 == -1) {
                                    aClass98_Sub22_Sub2_4446.method1265((byte)(-52), array7[l] * n42);
                                    aClass98_Sub22_Sub2_4446.method1265((byte)(-52), array8[l] * n42);
                                    aClass98_Sub22_Sub2_4446.method1265((byte)(-52), array6[l] * n42);
                                }
                                else {
                                    final float n46 = n41 / n45;
                                    aClass98_Sub22_Sub2_4446.method1265((byte)(-52), n46 * array7[l]);
                                    aClass98_Sub22_Sub2_4446.method1265((byte)(-52), array8[l] * n46);
                                    aClass98_Sub22_Sub2_4446.method1265((byte)(-52), array6[l] * n46);
                                }
                                final Class98_Sub22_Sub2 class98_Sub22_Sub3 = aClass98_Sub22_Sub2_4446;
                                class98_Sub22_Sub3.anInt3991 += -12 + b6;
                            }
                        }
                    }
                    if (b5) {
                        aClass98_Sub22_Sub2_4446.anInt3991 = b8;
                        if (!this.aHa_Sub1_4865.aBoolean4397) {
                            for (int n47 = 0; this.anInt4846 > n47; ++n47) {
                                aClass98_Sub22_Sub2_4446.method1265((byte)(-52), this.aFloatArray4897[n47]);
                                aClass98_Sub22_Sub2_4446.method1265((byte)(-52), this.aFloatArray4890[n47]);
                                final Class98_Sub22_Sub2 class98_Sub22_Sub4 = aClass98_Sub22_Sub2_4446;
                                class98_Sub22_Sub4.anInt3991 += b6 - 8;
                            }
                        }
                        else {
                            for (int n48 = 0; n48 < this.anInt4846; ++n48) {
                                aClass98_Sub22_Sub2_4446.method1264((byte)(-95), this.aFloatArray4897[n48]);
                                aClass98_Sub22_Sub2_4446.method1264((byte)42, this.aFloatArray4890[n48]);
                                final Class98_Sub22_Sub2 class98_Sub22_Sub5 = aClass98_Sub22_Sub2_4446;
                                class98_Sub22_Sub5.anInt3991 += b6 - 8;
                            }
                        }
                    }
                    aClass98_Sub22_Sub2_4446.anInt3991 = b6 * this.anInt4846;
                    Interface16 interface16;
                    if (!b) {
                        interface16 = this.aHa_Sub1_4865.method1878(aClass98_Sub22_Sub2_4446.anInt3991, false, b6, -85, aClass98_Sub22_Sub2_4446.aByteArray3992);
                        this.aBoolean4871 = true;
                    }
                    else {
                        if (this.anInterface16_4839 == null) {
                            this.anInterface16_4839 = this.aHa_Sub1_4865.method1878(aClass98_Sub22_Sub2_4446.anInt3991, true, b6, -119, aClass98_Sub22_Sub2_4446.aByteArray3992);
                        }
                        else {
                            this.anInterface16_4839.method54(aClass98_Sub22_Sub2_4446.anInt3991, 7896, aClass98_Sub22_Sub2_4446.aByteArray3992, b6);
                        }
                        interface16 = this.anInterface16_4839;
                        this.aByte4885 = 0;
                    }
                    if (b4) {
                        this.aClass104_4889.aByte898 = aByte898;
                        this.aClass104_4889.anInterface16_902 = interface16;
                    }
                    if (b5) {
                        this.aClass104_4874.aByte898 = b8;
                        this.aClass104_4874.anInterface16_902 = interface16;
                    }
                    if (b2) {
                        this.aClass104_4854.aByte898 = aByte899;
                        this.aClass104_4854.anInterface16_902 = interface16;
                    }
                    if (b3) {
                        this.aClass104_4859.anInterface16_902 = interface16;
                        this.aClass104_4859.aByte898 = b7;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.CA(" + n + ',' + b + ')');
        }
    }
    
    @Override
    final void method2326() {
        try {
            if (this.anInt4846 > 0 && ~this.anInt4896 < -1) {
                this.method2390(108, false);
                if (~(this.aByte4885 & 0x10) == -1 && this.aClass322_4838.anInterface8_2711 == null) {
                    this.method2388(false, -256);
                }
                this.method2383(-54);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.M()");
        }
    }
    
    @Override
    final Class146 method2341(final byte b, final int n, final boolean b2) {
        try {
            Class146_Sub2 class146_Sub2;
            Class146_Sub2 class146_Sub3;
            if (~b != 0xFFFFFFFE) {
                if (b == 2) {
                    class146_Sub2 = this.aHa_Sub1_4865.aClass146_Sub2_4461;
                    class146_Sub3 = this.aHa_Sub1_4865.aClass146_Sub2_4393;
                }
                else if (~b == 0xFFFFFFFC) {
                    class146_Sub3 = this.aHa_Sub1_4865.aClass146_Sub2_4462;
                    class146_Sub2 = this.aHa_Sub1_4865.aClass146_Sub2_4361;
                }
                else if (~b != 0xFFFFFFFB) {
                    if (b != 5) {
                        class146_Sub2 = (class146_Sub3 = new Class146_Sub2(this.aHa_Sub1_4865));
                    }
                    else {
                        class146_Sub2 = this.aHa_Sub1_4865.aClass146_Sub2_4382;
                        class146_Sub3 = this.aHa_Sub1_4865.aClass146_Sub2_4386;
                    }
                }
                else {
                    class146_Sub3 = this.aHa_Sub1_4865.aClass146_Sub2_4456;
                    class146_Sub2 = this.aHa_Sub1_4865.aClass146_Sub2_4355;
                }
            }
            else {
                class146_Sub3 = this.aHa_Sub1_4865.aClass146_Sub2_4428;
                class146_Sub2 = this.aHa_Sub1_4865.aClass146_Sub2_4369;
            }
            return this.method2379(255, b2, b != 0, n, class146_Sub3, class146_Sub2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.T(" + b + ',' + n + ',' + b2 + ')');
        }
    }
    
    @Override
    final void wa() {
        try {
            for (int n = 0; this.anInt4893 > n; ++n) {
                this.anIntArray4886[n] = this.anIntArray4886[n] + 7 >> -1281677276;
                this.anIntArray4856[n] = 7 + this.anIntArray4856[n] >> 115076868;
                this.anIntArray4843[n] = this.anIntArray4843[n] + 7 >> -463795676;
            }
            this.aBoolean4847 = false;
            if (this.aClass104_4889 != null) {
                this.aClass104_4889.anInterface16_902 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.wa()");
        }
    }
    
    @Override
    final void method2332(final Class146 class146, final int n, final int n2, final int n3, final boolean b) {
        try {
            final Class146_Sub2 class146_Sub2 = (Class146_Sub2)class146;
            if (~this.anInt4853 != -1 && ~class146_Sub2.anInt4853 != -1) {
                final int anInt4883 = class146_Sub2.anInt4883;
                final int[] anIntArray4886 = class146_Sub2.anIntArray4886;
                final int[] anIntArray4887 = class146_Sub2.anIntArray4856;
                final int[] anIntArray4888 = class146_Sub2.anIntArray4843;
                final short[] aShortArray4860 = class146_Sub2.aShortArray4860;
                final short[] aShortArray4861 = class146_Sub2.aShortArray4884;
                final short[] aShortArray4862 = class146_Sub2.aShortArray4849;
                final byte[] aByteArray4858 = class146_Sub2.aByteArray4858;
                short[] aShortArray4863;
                short[] aShortArray4864;
                byte[] aByteArray4859;
                short[] aShortArray4865;
                if (this.aClass14_4898 != null) {
                    aShortArray4863 = this.aClass14_4898.aShortArray165;
                    aShortArray4864 = this.aClass14_4898.aShortArray166;
                    aByteArray4859 = this.aClass14_4898.aByteArray168;
                    aShortArray4865 = this.aClass14_4898.aShortArray167;
                }
                else {
                    aShortArray4864 = null;
                    aShortArray4863 = null;
                    aShortArray4865 = null;
                    aByteArray4859 = null;
                }
                short[] aShortArray4866;
                short[] aShortArray4867;
                byte[] aByteArray4860;
                short[] aShortArray4868;
                if (class146_Sub2.aClass14_4898 == null) {
                    aShortArray4866 = null;
                    aShortArray4867 = null;
                    aByteArray4860 = null;
                    aShortArray4868 = null;
                }
                else {
                    aByteArray4860 = class146_Sub2.aClass14_4898.aByteArray168;
                    aShortArray4868 = class146_Sub2.aClass14_4898.aShortArray165;
                    aShortArray4867 = class146_Sub2.aClass14_4898.aShortArray167;
                    aShortArray4866 = class146_Sub2.aClass14_4898.aShortArray166;
                }
                final int[] anIntArray4889 = class146_Sub2.anIntArray4851;
                final short[] aShortArray4869 = class146_Sub2.aShortArray4887;
                if (!class146_Sub2.aBoolean4847) {
                    class146_Sub2.method2380(-113);
                }
                final short aShort4868 = class146_Sub2.aShort4868;
                final short aShort4869 = class146_Sub2.aShort4879;
                final short aShort4870 = class146_Sub2.aShort4845;
                final short aShort4871 = class146_Sub2.aShort4862;
                final short aShort4872 = class146_Sub2.aShort4892;
                final short aShort4873 = class146_Sub2.aShort4891;
                for (int n4 = 0; ~n4 > ~this.anInt4883; ++n4) {
                    final int n5 = this.anIntArray4856[n4] - n2;
                    if (~n5 <= ~aShort4868 && aShort4869 >= n5) {
                        final int n6 = -n + this.anIntArray4886[n4];
                        if (aShort4870 <= n6 && n6 <= aShort4871) {
                            final int n7 = this.anIntArray4843[n4] + -n3;
                            if (aShort4872 <= n7 && n7 <= aShort4873) {
                                int n8 = -1;
                                final int n9 = this.anIntArray4851[n4];
                                for (int i = this.anIntArray4851[n4 + 1], n10 = n9; i > n10; ++n10) {
                                    n8 = -1 + this.aShortArray4887[n10];
                                    if (~n8 == 0x0) {
                                        break;
                                    }
                                    if (~this.aByteArray4858[n8] != -1) {
                                        break;
                                    }
                                }
                                if (n8 != -1) {
                                    for (int j = 0; j < anInt4883; ++j) {
                                        if (n6 == anIntArray4886[j] && n7 == anIntArray4888[j] && anIntArray4887[j] == n5) {
                                            final int n11 = anIntArray4889[j];
                                            int n12 = -1;
                                            for (int n13 = anIntArray4889[j + 1], k = n11; k < n13; ++k) {
                                                n12 = aShortArray4869[k] - 1;
                                                if (~n12 == 0x0) {
                                                    break;
                                                }
                                                if (aByteArray4858[n12] != 0) {
                                                    break;
                                                }
                                            }
                                            if (n12 != -1) {
                                                if (aShortArray4864 == null) {
                                                    this.aClass14_4898 = new Class14();
                                                    final Class14 aClass14_4898 = this.aClass14_4898;
                                                    final short[] method2304 = Class141.method2304((byte)110, this.aShortArray4860);
                                                    aClass14_4898.aShortArray166 = method2304;
                                                    aShortArray4864 = method2304;
                                                    final Class14 aClass14_4899 = this.aClass14_4898;
                                                    final short[] method2305 = Class141.method2304((byte)116, this.aShortArray4884);
                                                    aClass14_4899.aShortArray165 = method2305;
                                                    aShortArray4863 = method2305;
                                                    final Class14 aClass14_4900 = this.aClass14_4898;
                                                    final short[] method2306 = Class141.method2304((byte)120, this.aShortArray4849);
                                                    aClass14_4900.aShortArray167 = method2306;
                                                    aShortArray4865 = method2306;
                                                    final Class14 aClass14_4901 = this.aClass14_4898;
                                                    final byte[] method2307 = Class98_Sub10_Sub24.method1075(this.aByteArray4858, true);
                                                    aClass14_4901.aByteArray168 = method2307;
                                                    aByteArray4859 = method2307;
                                                }
                                                if (aShortArray4866 == null) {
                                                    final Class146_Sub2 class146_Sub3 = class146_Sub2;
                                                    final Class14 aClass14_4902 = new Class14();
                                                    class146_Sub3.aClass14_4898 = aClass14_4902;
                                                    final Class14 class148;
                                                    final Class14 class147 = class148 = aClass14_4902;
                                                    final short[] method2308 = Class141.method2304((byte)110, aShortArray4860);
                                                    class148.aShortArray166 = method2308;
                                                    aShortArray4866 = method2308;
                                                    final Class14 class149 = class147;
                                                    final short[] method2309 = Class141.method2304((byte)123, aShortArray4861);
                                                    class149.aShortArray165 = method2309;
                                                    aShortArray4868 = method2309;
                                                    final Class14 class150 = class147;
                                                    final short[] method2310 = Class141.method2304((byte)114, aShortArray4862);
                                                    class150.aShortArray167 = method2310;
                                                    aShortArray4867 = method2310;
                                                    final Class14 class151 = class147;
                                                    final byte[] method2311 = Class98_Sub10_Sub24.method1075(aByteArray4858, true);
                                                    class151.aByteArray168 = method2311;
                                                    aByteArray4860 = method2311;
                                                }
                                                final short n14 = this.aShortArray4860[n8];
                                                final short n15 = this.aShortArray4884[n8];
                                                final short n16 = this.aShortArray4849[n8];
                                                final int n17 = anIntArray4889[1 + j];
                                                final int n18 = anIntArray4889[j];
                                                final byte b2 = this.aByteArray4858[n8];
                                                for (int n19 = n18; ~n19 > ~n17; ++n19) {
                                                    final short n20 = (short)(-1 + aShortArray4869[n19]);
                                                    if (n20 == -1) {
                                                        break;
                                                    }
                                                    if (aByteArray4860[n20] != 0) {
                                                        final short[] array = aShortArray4866;
                                                        final short n21 = n20;
                                                        array[n21] += n14;
                                                        final short[] array2 = aShortArray4868;
                                                        final short n22 = n20;
                                                        array2[n22] += n15;
                                                        final short[] array3 = aShortArray4867;
                                                        final short n23 = n20;
                                                        array3[n23] += n16;
                                                        final byte[] array4 = aByteArray4860;
                                                        final short n24 = n20;
                                                        array4[n24] += b2;
                                                    }
                                                }
                                                final short n25 = aShortArray4862[n12];
                                                final short n26 = aShortArray4860[n12];
                                                final int n27 = this.anIntArray4851[n4];
                                                final byte b3 = aByteArray4858[n12];
                                                final short n28 = aShortArray4861[n12];
                                                for (int l = this.anIntArray4851[n4 + 1], n29 = n27; l > n29; ++n29) {
                                                    final short n30 = (short)(-1 + this.aShortArray4887[n29]);
                                                    if (n30 == -1) {
                                                        break;
                                                    }
                                                    if (aByteArray4859[n30] != 0) {
                                                        final short[] array5 = aShortArray4864;
                                                        final short n31 = n30;
                                                        array5[n31] += n26;
                                                        final short[] array6 = aShortArray4863;
                                                        final short n32 = n30;
                                                        array6[n32] += n28;
                                                        final short[] array7 = aShortArray4865;
                                                        final short n33 = n30;
                                                        array7[n33] += n25;
                                                        final byte[] array8 = aByteArray4859;
                                                        final short n34 = n30;
                                                        array8[n34] += b3;
                                                    }
                                                }
                                                if (this.aClass104_4859 == null && this.aClass104_4854 != null) {
                                                    this.aClass104_4854.anInterface16_902 = null;
                                                }
                                                if (this.aClass104_4859 != null) {
                                                    this.aClass104_4859.anInterface16_902 = null;
                                                }
                                                if (class146_Sub2.aClass104_4859 == null && class146_Sub2.aClass104_4854 != null) {
                                                    class146_Sub2.aClass104_4854.anInterface16_902 = null;
                                                }
                                                if (class146_Sub2.aClass104_4859 != null) {
                                                    class146_Sub2.aClass104_4859.anInterface16_902 = null;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.AA(" + ((class146 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + b + ')');
        }
    }
    
    @Override
    final int da() {
        try {
            return this.aShort4872;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.da()");
        }
    }
    
    static final int method2391(final byte b) {
        try {
            if (Class98_Sub18.aFrame3950 != null) {
                return 3;
            }
            if (!Class134.aBoolean3457) {
                return 1;
            }
            return 2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.S(" + b + ')');
        }
    }
    
    @Override
    final int EA() {
        try {
            if (!this.aBoolean4847) {
                this.method2380(-96);
            }
            return this.aShort4879;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.EA()");
        }
    }
    
    @Override
    final void v() {
        try {
            for (int i = 0; i < this.anInt4883; ++i) {
                this.anIntArray4843[i] = -this.anIntArray4843[i];
            }
            for (int n = 0; this.anInt4846 > n; ++n) {
                this.aShortArray4849[n] = (short)(-this.aShortArray4849[n]);
            }
            for (int n2 = 0; ~this.anInt4853 < ~n2; ++n2) {
                final short n3 = this.aShortArray4895[n2];
                this.aShortArray4895[n2] = this.aShortArray4878[n2];
                this.aShortArray4878[n2] = n3;
            }
            if (this.aClass104_4859 == null && this.aClass104_4854 != null) {
                this.aClass104_4854.anInterface16_902 = null;
            }
            if (this.aClass104_4859 != null) {
                this.aClass104_4859.anInterface16_902 = null;
            }
            if (this.aClass104_4889 != null) {
                this.aClass104_4889.anInterface16_902 = null;
            }
            if (this.aClass322_4838 != null) {
                this.aClass322_4838.anInterface8_2711 = null;
            }
            this.aBoolean4847 = false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.v()");
        }
    }
    
    Class146_Sub2(final ha_Sub1 aHa_Sub1_4865) {
        this.anInt4846 = 0;
        this.aBoolean4847 = false;
        this.aBoolean4871 = true;
        this.anInt4883 = 0;
        this.aBoolean4848 = false;
        this.aBoolean4857 = false;
        this.anInt4893 = 0;
        this.anInt4853 = 0;
        this.anInt4896 = 0;
        try {
            this.aHa_Sub1_4865 = aHa_Sub1_4865;
            this.aClass104_4889 = new Class104(null, 5126, 3, 0);
            this.aClass104_4874 = new Class104(null, 5126, 2, 0);
            this.aClass104_4859 = new Class104(null, 5126, 3, 0);
            this.aClass104_4854 = new Class104(null, 5121, 4, 0);
            this.aClass322_4838 = new Class322();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.<init>(" + ((aHa_Sub1_4865 != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class146_Sub2(final ha_Sub1 aHa_Sub1_4865, final Class178 class178, final int anInt4894, final int n, final int n2, final int anInt4895) {
        this.anInt4846 = 0;
        this.aBoolean4847 = false;
        this.aBoolean4871 = true;
        this.anInt4883 = 0;
        this.aBoolean4848 = false;
        this.aBoolean4857 = false;
        this.anInt4893 = 0;
        this.anInt4853 = 0;
        this.anInt4896 = 0;
        try {
            this.aHa_Sub1_4865 = aHa_Sub1_4865;
            this.anInt4894 = anInt4894;
            this.anInt4837 = anInt4895;
            if (Class246_Sub2.method2973(anInt4894, anInt4895, (byte)2)) {
                this.aClass104_4889 = new Class104(null, 5126, 3, 0);
            }
            if (za_Sub2.method1682(anInt4894, 0, anInt4895)) {
                this.aClass104_4874 = new Class104(null, 5126, 2, 0);
            }
            if (Class246_Sub3_Sub5_Sub2.method3096(-27984, anInt4895, anInt4894)) {
                this.aClass104_4859 = new Class104(null, 5126, 3, 0);
            }
            if (Class87.method854(anInt4895, 28733, anInt4894)) {
                this.aClass104_4854 = new Class104(null, 5121, 4, 0);
            }
            if (Class322.method3672(anInt4895, anInt4894, 2048)) {
                this.aClass322_4838 = new Class322();
            }
            final d ad938 = aHa_Sub1_4865.aD938;
            this.anIntArray4851 = new int[1 + class178.anInt1406];
            final int[] array = new int[class178.anInt1391];
            for (int n3 = 0; ~n3 > ~class178.anInt1391; ++n3) {
                if (class178.aByteArray1414 == null || class178.aByteArray1414[n3] != 2) {
                    if (class178.aShortArray1409 != null && ~class178.aShortArray1409[n3] != 0x0) {
                        final Class238 method11 = ad938.method11(class178.aShortArray1409[n3] & 0xFFFF, -28755);
                        if (((this.anInt4837 & 0x40) == 0x0 || !method11.aBoolean1825) && method11.aBoolean1833) {
                            continue;
                        }
                    }
                    array[this.anInt4853++] = n3;
                    final int[] anIntArray4851 = this.anIntArray4851;
                    final short n4 = class178.aShortArray1393[n3];
                    ++anIntArray4851[n4];
                    final int[] anIntArray4852 = this.anIntArray4851;
                    final short n5 = class178.aShortArray1410[n3];
                    ++anIntArray4852[n5];
                    final int[] anIntArray4853 = this.anIntArray4851;
                    final short n6 = class178.aShortArray1392[n3];
                    ++anIntArray4853[n6];
                }
            }
            this.anInt4896 = this.anInt4853;
            final long[] array2 = new long[this.anInt4853];
            final boolean b = ~(0x100 & this.anInt4894) != -1;
            for (int n7 = 0; ~this.anInt4853 < ~n7; ++n7) {
                final int n8 = array[n7];
                Class238 method12 = null;
                int n9 = 0;
                final int n10 = 0;
                byte aByte1820 = 0;
                byte aByte1821 = 0;
                if (class178.aClass106Array1419 != null) {
                    boolean b2 = false;
                    for (int n11 = 0; ~class178.aClass106Array1419.length < ~n11; ++n11) {
                        final Class106 class179 = class178.aClass106Array1419[n11];
                        if (class179.anInt906 == n8) {
                            final Class177 method13 = Class67.method689(class179.anInt905, (byte)(-124));
                            if (method13.aBoolean1377) {
                                b2 = true;
                            }
                            if (~method13.anInt1373 != 0x0 && ad938.method11(method13.anInt1373, -28755).anInt1818 == 2) {
                                this.aBoolean4857 = true;
                            }
                        }
                    }
                    if (b2) {
                        array2[n7] = Long.MAX_VALUE;
                        --this.anInt4896;
                        continue;
                    }
                }
                int n12 = -1;
                if (class178.aShortArray1409 != null) {
                    n12 = class178.aShortArray1409[n8];
                    if (~n12 != 0x0) {
                        method12 = ad938.method11(0xFFFF & n12, -28755);
                        if ((0x40 & this.anInt4837) == 0x0 || !method12.aBoolean1825) {
                            aByte1820 = method12.aByte1820;
                            aByte1821 = method12.aByte1816;
                            if (method12.aByte1823 != 0 || ~method12.aByte1837 != -1) {
                                this.aBoolean4848 = true;
                            }
                        }
                        else {
                            method12 = null;
                            n12 = -1;
                        }
                    }
                }
                final boolean b3 = (class178.aByteArray1411 != null && ~class178.aByteArray1411[n8] != -1) || (method12 != null && ~method12.anInt1818 != -1);
                if ((b || b3) && class178.aByteArray1402 != null) {
                    n9 += class178.aByteArray1402[n8] << -491835247;
                }
                if (b3) {
                    n9 += 65536;
                }
                array2[n7] = n10 + ((n12 & 0xFFFF) << 955492592) + (n7 & 0xFFFF) + (n9 + ((aByte1820 & 0xFF) << 1866327528) + (aByte1821 & 0xFF) << -1611155616);
                this.aBoolean4848 |= (method12 != null && (method12.aByte1823 != 0 || method12.aByte1837 != 0));
                this.aBoolean4857 |= b3;
            }
            Class90.method882(array, array2, (byte)118);
            this.anIntArray4843 = class178.anIntArray1418;
            this.anInt4893 = class178.anInt1407;
            this.aShortArray4841 = class178.aShortArray1408;
            this.anIntArray4886 = class178.anIntArray1416;
            this.anIntArray4856 = class178.anIntArray1400;
            this.anInt4883 = class178.anInt1406;
            this.aClass87Array4881 = class178.aClass87Array1413;
            this.aClass35Array4863 = class178.aClass35Array1398;
            final Class286[] array3 = new Class286[this.anInt4883];
            if (class178.aClass106Array1419 != null) {
                this.anInt4866 = class178.aClass106Array1419.length;
                this.aClass249Array4877 = new Class249[this.anInt4866];
                this.aClass219Array4861 = new Class219[this.anInt4866];
                for (int n13 = 0; ~this.anInt4866 < ~n13; ++n13) {
                    final Class106 class180 = class178.aClass106Array1419[n13];
                    final Class177 method14 = Class67.method689(class180.anInt905, (byte)(-95));
                    int n14 = -1;
                    for (int n15 = 0; ~n15 > ~this.anInt4853; ++n15) {
                        if (~array[n15] == ~class180.anInt906) {
                            n14 = n15;
                            break;
                        }
                    }
                    if (n14 == -1) {
                        throw new RuntimeException();
                    }
                    final int n16 = (Class208.anIntArray1579[0xFFFF & class178.aShortArray1415[class180.anInt906]] & 0xFFFFFF) | 255 - ((class178.aByteArray1411 == null) ? 0 : class178.aByteArray1411[class180.anInt906]) << -1137441576;
                    this.aClass249Array4877[n13] = new Class249(n14, class178.aShortArray1393[class180.anInt906], class178.aShortArray1410[class180.anInt906], class178.aShortArray1392[class180.anInt906], method14.anInt1374, method14.anInt1380, method14.anInt1373, method14.anInt1384, method14.anInt1379, method14.aBoolean1377, method14.aBoolean1383, class180.anInt907);
                    this.aClass219Array4861[n13] = new Class219(n16);
                }
            }
            final int n17 = 3 * this.anInt4853;
            this.aShortArray4842 = new short[this.anInt4853];
            this.aShortArray4869 = new short[this.anInt4853];
            this.aShortArray4895 = new short[this.anInt4853];
            this.aShortArray4852 = new short[this.anInt4853];
            Class151_Sub1.aLongArray4970 = new long[n17];
            this.aFloatArray4897 = new float[n17];
            this.aShortArray4878 = new short[this.anInt4853];
            if (class178.aShortArray1394 != null) {
                this.aShortArray4864 = new short[this.anInt4853];
            }
            this.aShort4872 = (short)n2;
            this.aShort4867 = (short)n;
            this.aShortArray4849 = new short[n17];
            this.aByteArray4882 = new byte[this.anInt4853];
            this.aFloatArray4890 = new float[n17];
            this.aShortArray4884 = new short[n17];
            this.aByteArray4858 = new byte[n17];
            this.aShortArray4887 = new short[n17];
            this.aShortArray4860 = new short[n17];
            int n18 = 0;
            for (int i = 0; i < class178.anInt1406; ++i) {
                final int n19 = this.anIntArray4851[i];
                this.anIntArray4851[i] = n18;
                n18 += n19;
                array3[i] = new Class286();
            }
            this.anIntArray4851[class178.anInt1406] = n18;
            final Class250 method15 = Class224_Sub2.method2836(array, true, class178, this.anInt4853);
            final Class372[] array4 = new Class372[class178.anInt1391];
            for (int n20 = 0; class178.anInt1391 > n20; ++n20) {
                final short n21 = class178.aShortArray1393[n20];
                final short n22 = class178.aShortArray1410[n20];
                final short n23 = class178.aShortArray1392[n20];
                final int n24 = this.anIntArray4886[n22] - this.anIntArray4886[n21];
                final int n25 = -this.anIntArray4856[n21] + this.anIntArray4856[n22];
                final int n26 = -this.anIntArray4843[n21] + this.anIntArray4843[n22];
                final int n27 = -this.anIntArray4886[n21] + this.anIntArray4886[n23];
                final int n28 = -this.anIntArray4856[n21] + this.anIntArray4856[n23];
                final int n29 = this.anIntArray4843[n23] + -this.anIntArray4843[n21];
                int n30;
                int n31;
                int n32;
                for (n30 = n29 * n25 - n28 * n26, n31 = n27 * n26 - n24 * n29, n32 = -(n25 * n27) + n28 * n24; n30 > 8192 || n31 > 8192 || ~n32 < -8193 || ~n30 > 8191 || ~n31 > 8191 || ~n32 > 8191; n31 >>= 1, n32 >>= 1, n30 >>= 1) {}
                int n33 = (int)Math.sqrt(n32 * n32 + n30 * n30 + n31 * n31);
                if (n33 <= 0) {
                    n33 = 1;
                }
                final int anInt4896 = 256 * n31 / n33;
                final int anInt4897 = 256 * n32 / n33;
                final int anInt4898 = n30 * 256 / n33;
                final byte b4 = (byte)((class178.aByteArray1414 != null) ? class178.aByteArray1414[n20] : 0);
                if (b4 != 0) {
                    if (~b4 == 0xFFFFFFFE) {
                        final Class372[] array5 = array4;
                        final int n34 = n20;
                        final Class372 class181 = new Class372();
                        array5[n34] = class181;
                        final Class372 class182 = class181;
                        class182.anInt3148 = anInt4896;
                        class182.anInt3145 = anInt4898;
                        class182.anInt3146 = anInt4897;
                    }
                }
                else {
                    final Class286 class184;
                    final Class286 class183 = class184 = array3[n21];
                    ++class184.anInt2181;
                    final Class286 class185 = class183;
                    class185.anInt2184 += anInt4896;
                    final Class286 class186 = class183;
                    class186.anInt2183 += anInt4897;
                    final Class286 class187 = class183;
                    class187.anInt2180 += anInt4898;
                    final Class286 class189;
                    final Class286 class188 = class189 = array3[n22];
                    ++class189.anInt2181;
                    final Class286 class190 = class188;
                    class190.anInt2180 += anInt4898;
                    final Class286 class191 = class188;
                    class191.anInt2184 += anInt4896;
                    final Class286 class192 = class188;
                    class192.anInt2183 += anInt4897;
                    final Class286 class194;
                    final Class286 class193 = class194 = array3[n23];
                    ++class194.anInt2181;
                    final Class286 class195 = class193;
                    class195.anInt2180 += anInt4898;
                    final Class286 class196 = class193;
                    class196.anInt2183 += anInt4897;
                    final Class286 class197 = class193;
                    class197.anInt2184 += anInt4896;
                }
            }
            for (int j = 0; j < this.anInt4853; ++j) {
                final int n35 = array[j];
                final int n36 = 0xFFFF & class178.aShortArray1415[n35];
                int n37;
                if (class178.aByteArray1420 != null) {
                    n37 = class178.aByteArray1420[n35];
                }
                else {
                    n37 = -1;
                }
                int n38;
                if (class178.aByteArray1411 == null) {
                    n38 = 0;
                }
                else {
                    n38 = (0xFF & class178.aByteArray1411[n35]);
                }
                short n39 = (short)((class178.aShortArray1409 == null) ? -1 : class178.aShortArray1409[n35]);
                if (n39 != -1 && ~(0x40 & this.anInt4837) != -1 && ad938.method11(n39 & 0xFFFF, -28755).aBoolean1825) {
                    n39 = -1;
                }
                float n40 = 0.0f;
                float n41 = 0.0f;
                float n42 = 0.0f;
                float n43 = 0.0f;
                float n44 = 0.0f;
                float n45 = 0.0f;
                int n46 = 0;
                int n47 = 0;
                int method16 = 0;
                if (n39 != -1) {
                    if (~n37 == 0x0) {
                        n44 = 0.0f;
                        n41 = 1.0f;
                        n42 = 1.0f;
                        n40 = 0.0f;
                        n43 = 1.0f;
                        n47 = 2;
                        n45 = 0.0f;
                        n46 = 1;
                    }
                    else {
                        n37 &= 0xFF;
                        final byte b5 = class178.aByteArray1388[n37];
                        if (b5 == 0) {
                            final short n48 = class178.aShortArray1393[n35];
                            final short n49 = class178.aShortArray1410[n35];
                            final short n50 = class178.aShortArray1392[n35];
                            final short n51 = class178.aShortArray1403[n37];
                            final short n52 = class178.aShortArray1421[n37];
                            final short n53 = class178.aShortArray1385[n37];
                            final float n54 = class178.anIntArray1416[n51];
                            final float n55 = class178.anIntArray1400[n51];
                            final float n56 = class178.anIntArray1418[n51];
                            final float n57 = -n54 + class178.anIntArray1416[n52];
                            final float n58 = -n55 + class178.anIntArray1400[n52];
                            final float n59 = class178.anIntArray1418[n52] - n56;
                            final float n60 = class178.anIntArray1416[n53] - n54;
                            final float n61 = class178.anIntArray1400[n53] - n55;
                            final float n62 = class178.anIntArray1418[n53] - n56;
                            final float n63 = class178.anIntArray1416[n48] - n54;
                            final float n64 = -n55 + class178.anIntArray1400[n48];
                            final float n65 = class178.anIntArray1418[n48] - n56;
                            final float n66 = class178.anIntArray1416[n49] - n54;
                            final float n67 = class178.anIntArray1400[n49] - n55;
                            final float n68 = -n56 + class178.anIntArray1418[n49];
                            final float n69 = -n54 + class178.anIntArray1416[n50];
                            final float n70 = class178.anIntArray1400[n50] - n55;
                            final float n71 = -n56 + class178.anIntArray1418[n50];
                            final float n72 = -(n59 * n61) + n62 * n58;
                            final float n73 = -(n57 * n62) + n60 * n59;
                            final float n74 = -(n60 * n58) + n57 * n61;
                            final float n75 = n61 * n74 - n62 * n73;
                            final float n76 = n62 * n72 - n74 * n60;
                            final float n77 = n73 * n60 - n61 * n72;
                            final float n78 = 1.0f / (n77 * n59 + (n57 * n75 + n58 * n76));
                            n40 = (n65 * n77 + (n76 * n64 + n75 * n63)) * n78;
                            n44 = n78 * (n75 * n69 + n76 * n70 + n71 * n77);
                            n42 = n78 * (n77 * n68 + (n67 * n76 + n66 * n75));
                            final float n79 = n59 * n72 - n57 * n74;
                            final float n80 = -(n58 * n72) + n57 * n73;
                            final float n81 = n58 * n74 - n59 * n73;
                            final float n82 = 1.0f / (n62 * n80 + (n60 * n81 + n61 * n79));
                            n45 = n82 * (n80 * n71 + (n70 * n79 + n81 * n69));
                            n41 = (n63 * n81 + n64 * n79 + n65 * n80) * n82;
                            n43 = (n67 * n79 + n81 * n66 + n68 * n80) * n82;
                        }
                        else {
                            final short n83 = class178.aShortArray1393[n35];
                            final short n84 = class178.aShortArray1410[n35];
                            final short n85 = class178.aShortArray1392[n35];
                            final int n86 = method15.anIntArray1911[n37];
                            final int n87 = method15.anIntArray1915[n37];
                            final int n88 = method15.anIntArray1912[n37];
                            final float[] array6 = method15.aFloatArrayArray1910[n37];
                            final byte b6 = class178.aByteArray1399[n37];
                            final float n89 = class178.anIntArray1412[n37] / 256.0f;
                            if (b5 == 1) {
                                final float n90 = class178.anIntArray1390[n37] / 1024.0f;
                                Class98_Sub37.method1460(class178.anIntArray1416[n83], n88, 8, class178.anIntArray1418[n83], n87, class178.anIntArray1400[n83], array6, n90, b6, n86, n89, Class28.aFloatArray294);
                                n41 = Class28.aFloatArray294[1];
                                n40 = Class28.aFloatArray294[0];
                                Class98_Sub37.method1460(class178.anIntArray1416[n84], n88, 8, class178.anIntArray1418[n84], n87, class178.anIntArray1400[n84], array6, n90, b6, n86, n89, Class28.aFloatArray294);
                                n43 = Class28.aFloatArray294[1];
                                n42 = Class28.aFloatArray294[0];
                                Class98_Sub37.method1460(class178.anIntArray1416[n85], n88, 8, class178.anIntArray1418[n85], n87, class178.anIntArray1400[n85], array6, n90, b6, n86, n89, Class28.aFloatArray294);
                                n44 = Class28.aFloatArray294[0];
                                n45 = Class28.aFloatArray294[1];
                                final float n91 = n90 / 2.0f;
                                if (~(0x1 & b6) == -1) {
                                    if (-n40 + n42 <= n91) {
                                        if (n40 - n42 > n91) {
                                            n42 += n90;
                                            n46 = 2;
                                        }
                                    }
                                    else {
                                        n46 = 1;
                                        n42 -= n90;
                                    }
                                    if (-n40 + n44 > n91) {
                                        n47 = 1;
                                        n44 -= n90;
                                    }
                                    else if (n40 - n44 > n91) {
                                        n47 = 2;
                                        n44 += n90;
                                    }
                                }
                                else {
                                    if (n45 - n41 > n91) {
                                        n45 -= n90;
                                        n47 = 1;
                                    }
                                    else if (n91 < -n45 + n41) {
                                        n47 = 2;
                                        n45 += n90;
                                    }
                                    if (n43 - n41 <= n91) {
                                        if (n91 < n41 - n43) {
                                            n46 = 2;
                                            n43 += n90;
                                        }
                                    }
                                    else {
                                        n43 -= n90;
                                        n46 = 1;
                                    }
                                }
                            }
                            else if (~b5 != 0xFFFFFFFD) {
                                if (~b5 == 0xFFFFFFFC) {
                                    Class243.method2943(Class28.aFloatArray294, class178.anIntArray1400[n83], b6, n88, array6, class178.anIntArray1418[n83], n87, n89, class178.anIntArray1416[n83], n86, 113);
                                    n40 = Class28.aFloatArray294[0];
                                    n41 = Class28.aFloatArray294[1];
                                    Class243.method2943(Class28.aFloatArray294, class178.anIntArray1400[n84], b6, n88, array6, class178.anIntArray1418[n84], n87, n89, class178.anIntArray1416[n84], n86, 102);
                                    n42 = Class28.aFloatArray294[0];
                                    n43 = Class28.aFloatArray294[1];
                                    Class243.method2943(Class28.aFloatArray294, class178.anIntArray1400[n85], b6, n88, array6, class178.anIntArray1418[n85], n87, n89, class178.anIntArray1416[n85], n86, 125);
                                    n45 = Class28.aFloatArray294[1];
                                    n44 = Class28.aFloatArray294[0];
                                    if ((0x1 & b6) == 0x0) {
                                        if (n42 - n40 > 0.5f) {
                                            n46 = 1;
                                            --n42;
                                        }
                                        else if (-n42 + n40 > 0.5f) {
                                            n46 = 2;
                                            ++n42;
                                        }
                                        if (-n40 + n44 > 0.5f) {
                                            --n44;
                                            n47 = 1;
                                        }
                                        else if (n40 - n44 > 0.5f) {
                                            n47 = 2;
                                            ++n44;
                                        }
                                    }
                                    else {
                                        if (-n41 + n45 <= 0.5f) {
                                            if (-n45 + n41 > 0.5f) {
                                                ++n45;
                                                n47 = 2;
                                            }
                                        }
                                        else {
                                            n47 = 1;
                                            --n45;
                                        }
                                        if (n43 - n41 > 0.5f) {
                                            n46 = 1;
                                            --n43;
                                        }
                                        else if (n41 - n43 > 0.5f) {
                                            n46 = 2;
                                            ++n43;
                                        }
                                    }
                                }
                            }
                            else {
                                final float n92 = class178.anIntArray1397[n37] / 256.0f;
                                final float n93 = class178.anIntArray1386[n37] / 256.0f;
                                final int n94 = class178.anIntArray1416[n84] - class178.anIntArray1416[n83];
                                final int n95 = class178.anIntArray1400[n84] + -class178.anIntArray1400[n83];
                                final int n96 = class178.anIntArray1418[n84] + -class178.anIntArray1418[n83];
                                final int n97 = -class178.anIntArray1416[n83] + class178.anIntArray1416[n85];
                                final int n98 = class178.anIntArray1400[n85] + -class178.anIntArray1400[n83];
                                final int n99 = class178.anIntArray1418[n85] + -class178.anIntArray1418[n83];
                                final int n100 = n99 * n95 + -(n96 * n98);
                                final int n101 = n96 * n97 + -(n94 * n99);
                                final int n102 = -(n95 * n97) + n94 * n98;
                                method16 = Class69.method696((n102 * array6[2] + (n100 * array6[0] + array6[1] * n101)) / (64.0f / class178.anIntArray1389[n37]), (array6[7] * n101 + n100 * array6[6] + n102 * array6[8]) / (64.0f / class178.anIntArray1390[n37]), (byte)(-92), (n100 * array6[3] + array6[4] * n101 + n102 * array6[5]) / (64.0f / class178.anIntArray1404[n37]));
                                Class48_Sub1_Sub1.method461(class178.anIntArray1400[n83], n93, b6, n87, n89, 119, n92, array6, method16, Class28.aFloatArray294, n88, class178.anIntArray1418[n83], n86, class178.anIntArray1416[n83]);
                                n40 = Class28.aFloatArray294[0];
                                n41 = Class28.aFloatArray294[1];
                                Class48_Sub1_Sub1.method461(class178.anIntArray1400[n84], n93, b6, n87, n89, 116, n92, array6, method16, Class28.aFloatArray294, n88, class178.anIntArray1418[n84], n86, class178.anIntArray1416[n84]);
                                n42 = Class28.aFloatArray294[0];
                                n43 = Class28.aFloatArray294[1];
                                Class48_Sub1_Sub1.method461(class178.anIntArray1400[n85], n93, b6, n87, n89, 111, n92, array6, method16, Class28.aFloatArray294, n88, class178.anIntArray1418[n85], n86, class178.anIntArray1416[n85]);
                                n44 = Class28.aFloatArray294[0];
                                n45 = Class28.aFloatArray294[1];
                            }
                        }
                    }
                }
                byte b7;
                if (class178.aByteArray1414 != null) {
                    b7 = class178.aByteArray1414[n35];
                }
                else {
                    b7 = 0;
                }
                if (b7 == 0) {
                    final long n103 = ((method16 << 1989611512) - -(n36 << 1846998088) + n38 << -1370244896) + (n37 << 702412290);
                    final short n104 = class178.aShortArray1393[n35];
                    final short n105 = class178.aShortArray1410[n35];
                    final short n106 = class178.aShortArray1392[n35];
                    final Class286 class198 = array3[n104];
                    this.aShortArray4895[j] = this.method2381((byte)72, n41, n104, class198.anInt2181, class198.anInt2180, n103, n40, class198.anInt2184, class178, class198.anInt2183);
                    final Class286 class199 = array3[n105];
                    this.aShortArray4852[j] = this.method2381((byte)68, n43, n105, class199.anInt2181, class199.anInt2180, n103 + n46, n42, class199.anInt2184, class178, class199.anInt2183);
                    final Class286 class200 = array3[n106];
                    this.aShortArray4878[j] = this.method2381((byte)93, n45, n106, class200.anInt2181, class200.anInt2180, n103 + n47, n44, class200.anInt2184, class178, class200.anInt2183);
                }
                else if (~b7 == 0xFFFFFFFE) {
                    final Class372 class201 = array4[n35];
                    final long n107 = (256 + class201.anInt3146 << -872592426) + ((256 + class201.anInt3148 << 1126719788) + (((class201.anInt3145 <= 0) ? 2048 : 1024) + (n37 << 1620530146))) + (n38 + ((method16 << 690027832) - -(n36 << 310122824)) << -484684768);
                    this.aShortArray4895[j] = this.method2381((byte)43, n41, class178.aShortArray1393[n35], 0, class201.anInt3145, n107, n40, class201.anInt3148, class178, class201.anInt3146);
                    this.aShortArray4852[j] = this.method2381((byte)83, n43, class178.aShortArray1410[n35], 0, class201.anInt3145, n107 + n46, n42, class201.anInt3148, class178, class201.anInt3146);
                    this.aShortArray4878[j] = this.method2381((byte)74, n45, class178.aShortArray1392[n35], 0, class201.anInt3145, n47 + n107, n44, class201.anInt3148, class178, class201.anInt3146);
                }
                if (class178.aByteArray1411 != null) {
                    this.aByteArray4882[j] = class178.aByteArray1411[n35];
                }
                if (class178.aShortArray1394 != null) {
                    this.aShortArray4864[j] = class178.aShortArray1394[n35];
                }
                this.aShortArray4842[j] = class178.aShortArray1415[n35];
                this.aShortArray4869[j] = n39;
            }
            int n108 = 0;
            int n109 = -10000;
            for (int n110 = 0; this.anInt4896 > n110; ++n110) {
                final short n111 = this.aShortArray4869[n110];
                if (~n111 != ~n109) {
                    n109 = n111;
                    ++n108;
                }
            }
            this.anIntArray4850 = new int[n108 + 1];
            int n112 = 0;
            short n113 = -10000;
            for (int n114 = 0; ~this.anInt4896 < ~n114; ++n114) {
                final short n115 = this.aShortArray4869[n114];
                if (n113 != n115) {
                    n113 = n115;
                    this.anIntArray4850[n112++] = n114;
                }
            }
            this.anIntArray4850[n112] = this.anInt4896;
            Class151_Sub1.aLongArray4970 = null;
            this.aShortArray4860 = Class123.method2209(this.anInt4846, 21119, this.aShortArray4860);
            this.aShortArray4884 = Class123.method2209(this.anInt4846, 21119, this.aShortArray4884);
            this.aShortArray4849 = Class123.method2209(this.anInt4846, 21119, this.aShortArray4849);
            this.aByteArray4858 = Class183.method2622(this.aByteArray4858, (byte)(-109), this.anInt4846);
            this.aFloatArray4897 = Class59.method526((byte)(-64), this.anInt4846, this.aFloatArray4897);
            this.aFloatArray4890 = Class59.method526((byte)(-64), this.anInt4846, this.aFloatArray4890);
            if (class178.anIntArray1417 != null && Class111.method2095(this.anInt4837, anInt4894, (byte)(-96))) {
                this.anIntArrayArray4888 = class178.method2595(91, false);
            }
            if (class178.aClass106Array1419 != null && Class64.method555(this.anInt4837, anInt4894, -56)) {
                this.anIntArrayArray4875 = class178.method2596(21517);
            }
            if (class178.anIntArray1395 != null && Class98_Sub46_Sub9.method1554(anInt4894, 22251, this.anInt4837)) {
                int n116 = 0;
                final int[] array7 = new int[256];
                for (int n117 = 0; ~n117 > ~this.anInt4853; ++n117) {
                    final int n118 = class178.anIntArray1395[array[n117]];
                    if (n118 >= 0) {
                        if (n118 > n116) {
                            n116 = n118;
                        }
                        final int[] array8 = array7;
                        final int n119 = n118;
                        ++array8[n119];
                    }
                }
                this.anIntArrayArray4870 = new int[n116 + 1][];
                for (int n120 = 0; ~n116 <= ~n120; ++n120) {
                    this.anIntArrayArray4870[n120] = new int[array7[n120]];
                    array7[n120] = 0;
                }
                for (int n121 = 0; this.anInt4853 > n121; ++n121) {
                    final int n122 = class178.anIntArray1395[array[n121]];
                    if (n122 >= 0) {
                        this.anIntArrayArray4870[n122][array7[n122]++] = n121;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ln.<init>(" + ((aHa_Sub1_4865 != null) ? "{...}" : "null") + ',' + ((class178 != null) ? "{...}" : "null") + ',' + anInt4894 + ',' + n + ',' + n2 + ',' + anInt4895 + ')');
        }
    }
    
    static {
        Class146_Sub2.aBooleanArray4840 = new boolean[8];
        Class146_Sub2.anInt4855 = -1;
    }
}
