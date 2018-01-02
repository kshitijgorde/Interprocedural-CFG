// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub24_Sub1 extends Class98_Sub24
{
    int anInt5795;
    int anInt5796;
    boolean aBoolean5797;
    int anInt5798;
    byte[] aByteArray5799;
    
    final Class98_Sub24_Sub1 method1269(final Class314 class314) {
        this.aByteArray5799 = class314.method3642(true, this.aByteArray5799);
        this.anInt5795 = class314.method3643(this.anInt5795, 1);
        if (this.anInt5798 == this.anInt5796) {
            final int method3638 = class314.method3638(6, this.anInt5798);
            this.anInt5796 = method3638;
            this.anInt5798 = method3638;
        }
        else {
            this.anInt5798 = class314.method3638(6, this.anInt5798);
            this.anInt5796 = class314.method3638(6, this.anInt5796);
            if (this.anInt5798 == this.anInt5796) {
                --this.anInt5798;
            }
        }
        return this;
    }
    
    Class98_Sub24_Sub1(final int anInt5795, final byte[] aByteArray5799, final int anInt5796, final int anInt5797) {
        this.anInt5795 = anInt5795;
        this.aByteArray5799 = aByteArray5799;
        this.anInt5798 = anInt5796;
        this.anInt5796 = anInt5797;
    }
    
    Class98_Sub24_Sub1(final int anInt5795, final byte[] aByteArray5799, final int anInt5796, final int anInt5797, final boolean aBoolean5797) {
        this.anInt5795 = anInt5795;
        this.aByteArray5799 = aByteArray5799;
        this.anInt5798 = anInt5796;
        this.anInt5796 = anInt5797;
        this.aBoolean5797 = aBoolean5797;
    }
}
