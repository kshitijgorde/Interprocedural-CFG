// 
// Decompiled by Procyon v0.5.30
// 

final class Class37
{
    private Class344[] aClass344Array351;
    private int anInt352;
    private int anInt353;
    
    static final Class37 method342(final Class207 class207, final int n, final int n2) {
        final byte[] method2745 = class207.method2745(n2, n, false);
        if (method2745 == null) {
            return null;
        }
        return new Class37(new Class98_Sub22(method2745));
    }
    
    private Class37(final Class98_Sub22 class98_Sub22) {
        this.aClass344Array351 = new Class344[10];
        for (int i = 0; i < 10; ++i) {
            if (class98_Sub22.readUnsignedByte((byte)104) != 0) {
                --class98_Sub22.anInt3991;
                (this.aClass344Array351[i] = new Class344()).method3820(class98_Sub22);
            }
        }
        this.anInt352 = class98_Sub22.readShort((byte)127);
        this.anInt353 = class98_Sub22.readShort((byte)127);
    }
    
    final int method343() {
        int n = 9999999;
        for (int i = 0; i < 10; ++i) {
            if (this.aClass344Array351[i] != null && this.aClass344Array351[i].anInt2867 / 20 < n) {
                n = this.aClass344Array351[i].anInt2867 / 20;
            }
        }
        if (this.anInt352 < this.anInt353 && this.anInt352 / 20 < n) {
            n = this.anInt352 / 20;
        }
        if (n == 9999999 || n == 0) {
            return 0;
        }
        for (int j = 0; j < 10; ++j) {
            if (this.aClass344Array351[j] != null) {
                final Class344 class344 = this.aClass344Array351[j];
                class344.anInt2867 -= n * 20;
            }
        }
        if (this.anInt352 < this.anInt353) {
            this.anInt352 -= n * 20;
            this.anInt353 -= n * 20;
        }
        return n;
    }
    
    final Class98_Sub24_Sub1 method344() {
        return new Class98_Sub24_Sub1(22050, this.method345(), 22050 * this.anInt352 / 1000, 22050 * this.anInt353 / 1000);
    }
    
    private Class37() {
        this.aClass344Array351 = new Class344[10];
    }
    
    private final byte[] method345() {
        int n = 0;
        for (int i = 0; i < 10; ++i) {
            if (this.aClass344Array351[i] != null && this.aClass344Array351[i].anInt2870 + this.aClass344Array351[i].anInt2867 > n) {
                n = this.aClass344Array351[i].anInt2870 + this.aClass344Array351[i].anInt2867;
            }
        }
        if (n == 0) {
            return new byte[0];
        }
        final byte[] array = new byte[22050 * n / 1000];
        for (int j = 0; j < 10; ++j) {
            if (this.aClass344Array351[j] != null) {
                final int n2 = this.aClass344Array351[j].anInt2870 * 22050 / 1000;
                final int n3 = this.aClass344Array351[j].anInt2867 * 22050 / 1000;
                final int[] method3822 = this.aClass344Array351[j].method3822(n2, this.aClass344Array351[j].anInt2870);
                for (int k = 0; k < n2; ++k) {
                    int n4 = array[k + n3] + (method3822[k] >> 8);
                    if ((n4 + 128 & 0xFFFFFF00) != 0x0) {
                        n4 = (n4 >> 31 ^ 0x7F);
                    }
                    array[k + n3] = (byte)n4;
                }
            }
        }
        return array;
    }
}
