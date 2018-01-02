// 
// Decompiled by Procyon v0.5.30
// 

final class Class246_Sub6 extends Class246
{
    int anInt5109;
    int anInt5110;
    int anInt5111;
    int anInt5112;
    int anInt5113;
    boolean aBoolean5114;
    
    final boolean method3130(final int n, final int n2) {
        if (!this.aBoolean5114) {
            return false;
        }
        final int n3 = this.anInt5110 - this.anInt5111;
        final int n4 = this.anInt5112 - this.anInt5113;
        final int n5 = n3 * n3 + n4 * n4;
        final int n6 = n * n3 + n2 * n4 - (this.anInt5111 * n3 + this.anInt5113 * n4);
        if (n6 <= 0) {
            final int n7 = this.anInt5111 - n;
            final int n8 = this.anInt5113 - n2;
            return n7 * n7 + n8 * n8 < this.anInt5109 * this.anInt5109;
        }
        if (n6 > n5) {
            final int n9 = this.anInt5110 - n;
            final int n10 = this.anInt5112 - n2;
            return n9 * n9 + n10 * n10 < this.anInt5109 * this.anInt5109;
        }
        final int n11 = (n6 << 10) / n5;
        final int n12 = this.anInt5111 + (n3 * n11 >> 10) - n;
        final int n13 = this.anInt5113 + (n4 * n11 >> 10) - n2;
        return n12 * n12 + n13 * n13 < this.anInt5109 * this.anInt5109;
    }
    
    public Class246_Sub6() {
        this.aBoolean5114 = false;
    }
}
