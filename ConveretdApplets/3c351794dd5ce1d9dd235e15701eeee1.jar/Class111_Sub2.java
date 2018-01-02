// 
// Decompiled by Procyon v0.5.30
// 

final class Class111_Sub2 extends Class111
{
    float aFloat4688;
    float aFloat4689;
    float aFloat4690;
    float aFloat4691;
    float aFloat4692;
    float aFloat4693;
    float aFloat4694;
    static int anInt4695;
    float aFloat4696;
    float aFloat4697;
    float aFloat4698;
    float aFloat4699;
    float aFloat4700;
    
    @Override
    final void method2107(final int n) {
        try {
            this.aFloat4700 = 1.0f;
            final float n2 = Class64_Sub5.aFloatArray3653[n & 0x3FFF];
            this.aFloat4694 = n2;
            this.aFloat4688 = n2;
            this.aFloat4698 = Class64_Sub5.aFloatArray3651[0x3FFF & n];
            final float aFloat4699 = 0.0f;
            this.aFloat4689 = aFloat4699;
            this.aFloat4693 = aFloat4699;
            this.aFloat4691 = aFloat4699;
            this.aFloat4692 = aFloat4699;
            this.aFloat4697 = aFloat4699;
            this.aFloat4690 = aFloat4699;
            this.aFloat4699 = aFloat4699;
            this.aFloat4696 = -this.aFloat4698;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ho.N(" + n + ')');
        }
    }
    
    @Override
    final void method2091() {
        try {
            final float aFloat4700 = 1.0f;
            this.aFloat4694 = aFloat4700;
            this.aFloat4688 = aFloat4700;
            this.aFloat4700 = aFloat4700;
            final float aFloat4701 = 0.0f;
            this.aFloat4689 = aFloat4701;
            this.aFloat4691 = aFloat4701;
            this.aFloat4697 = aFloat4701;
            this.aFloat4696 = aFloat4701;
            this.aFloat4690 = aFloat4701;
            this.aFloat4698 = aFloat4701;
            this.aFloat4699 = aFloat4701;
            this.aFloat4693 = aFloat4701;
            this.aFloat4692 = aFloat4701;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ho.CA()");
        }
    }
    
    @Override
    final void method2096(int n, int n2, int n3, final int[] array) {
        try {
            n -= (int)this.aFloat4697;
            n2 -= (int)this.aFloat4691;
            n3 -= (int)this.aFloat4689;
            array[0] = (int)(this.aFloat4693 * n3 + (n * this.aFloat4700 + this.aFloat4692 * n2));
            array[2] = (int)(this.aFloat4696 * n2 + this.aFloat4690 * n + n3 * this.aFloat4694);
            array[1] = (int)(this.aFloat4698 * n3 + (this.aFloat4688 * n2 + this.aFloat4699 * n));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ho.PA(" + n + ',' + n2 + ',' + n3 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method2100(final int n, final int n2, final int n3) {
        try {
            this.aFloat4697 = n;
            final float aFloat4700 = 1.0f;
            this.aFloat4694 = aFloat4700;
            this.aFloat4688 = aFloat4700;
            this.aFloat4700 = aFloat4700;
            this.aFloat4691 = n2;
            this.aFloat4689 = n3;
            final float n4 = 0.0f;
            this.aFloat4696 = n4;
            this.aFloat4690 = n4;
            this.aFloat4698 = n4;
            this.aFloat4699 = n4;
            this.aFloat4693 = n4;
            this.aFloat4692 = n4;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ho.SA(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method2093(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        try {
            final float n7 = Class64_Sub5.aFloatArray3653[0x3FFF & n4];
            final float n8 = Class64_Sub5.aFloatArray3651[0x3FFF & n4];
            final float n9 = Class64_Sub5.aFloatArray3653[0x3FFF & n5];
            final float n10 = Class64_Sub5.aFloatArray3651[n5 & 0x3FFF];
            final float n11 = Class64_Sub5.aFloatArray3653[n6 & 0x3FFF];
            final float n12 = Class64_Sub5.aFloatArray3651[0x3FFF & n6];
            final float n13 = n11 * n8;
            final float n14 = n8 * n12;
            this.aFloat4699 = n7 * n12;
            this.aFloat4692 = n12 * -n9 + n13 * n10;
            this.aFloat4688 = n7 * n11;
            this.aFloat4698 = -n8;
            this.aFloat4696 = n9 * n13 + n12 * n10;
            this.aFloat4693 = n7 * n10;
            this.aFloat4700 = n10 * n14 + n11 * n9;
            this.aFloat4690 = n14 * n9 + n11 * -n10;
            this.aFloat4694 = n7 * n9;
            this.aFloat4691 = -(this.aFloat4688 * n2) + this.aFloat4692 * -n - this.aFloat4696 * n3;
            this.aFloat4689 = -(n2 * this.aFloat4698) + -n * this.aFloat4693 - n3 * this.aFloat4694;
            this.aFloat4697 = this.aFloat4700 * -n - n2 * this.aFloat4699 - n3 * this.aFloat4690;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ho.U(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    @Override
    final void method2101(final int n) {
        try {
            this.aFloat4688 = 1.0f;
            final float n2 = Class64_Sub5.aFloatArray3653[n & 0x3FFF];
            this.aFloat4694 = n2;
            this.aFloat4700 = n2;
            this.aFloat4690 = Class64_Sub5.aFloatArray3651[0x3FFF & n];
            final float aFloat4699 = 0.0f;
            this.aFloat4689 = aFloat4699;
            this.aFloat4698 = aFloat4699;
            this.aFloat4691 = aFloat4699;
            this.aFloat4696 = aFloat4699;
            this.aFloat4692 = aFloat4699;
            this.aFloat4697 = aFloat4699;
            this.aFloat4699 = aFloat4699;
            this.aFloat4693 = -this.aFloat4690;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ho.F(" + n + ')');
        }
    }
    
    @Override
    final void method2108(final int[] array) {
        try {
            final float n = -this.aFloat4697 + array[0];
            final float n2 = -this.aFloat4691 + array[1];
            final float n3 = array[2] - this.aFloat4689;
            array[0] = (int)(n2 * this.aFloat4692 + n * this.aFloat4700 + n3 * this.aFloat4693);
            array[2] = (int)(this.aFloat4694 * n3 + (n * this.aFloat4690 + this.aFloat4696 * n2));
            array[1] = (int)(this.aFloat4688 * n2 + this.aFloat4699 * n + this.aFloat4698 * n3);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ho.LA(" + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method2097(final int n) {
        try {
            final float n2 = Class64_Sub5.aFloatArray3653[0x3FFF & n];
            final float n3 = Class64_Sub5.aFloatArray3651[0x3FFF & n];
            final float aFloat4700 = this.aFloat4700;
            final float aFloat4701 = this.aFloat4699;
            final float aFloat4702 = this.aFloat4690;
            final float aFloat4703 = this.aFloat4697;
            this.aFloat4700 = n3 * this.aFloat4693 + aFloat4700 * n2;
            this.aFloat4699 = this.aFloat4698 * n3 + n2 * aFloat4701;
            this.aFloat4693 = -(n3 * aFloat4700) + n2 * this.aFloat4693;
            this.aFloat4698 = -(aFloat4701 * n3) + n2 * this.aFloat4698;
            this.aFloat4690 = n3 * this.aFloat4694 + aFloat4702 * n2;
            this.aFloat4694 = this.aFloat4694 * n2 - n3 * aFloat4702;
            this.aFloat4697 = aFloat4703 * n2 + this.aFloat4689 * n3;
            this.aFloat4689 = -(n3 * aFloat4703) + this.aFloat4689 * n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ho.RA(" + n + ')');
        }
    }
    
    @Override
    final void method2104(final int n) {
        try {
            this.aFloat4694 = 1.0f;
            final float n2 = Class64_Sub5.aFloatArray3653[n & 0x3FFF];
            this.aFloat4688 = n2;
            this.aFloat4700 = n2;
            this.aFloat4692 = Class64_Sub5.aFloatArray3651[n & 0x3FFF];
            final float aFloat4690 = 0.0f;
            this.aFloat4689 = aFloat4690;
            this.aFloat4698 = aFloat4690;
            this.aFloat4693 = aFloat4690;
            this.aFloat4691 = aFloat4690;
            this.aFloat4696 = aFloat4690;
            this.aFloat4697 = aFloat4690;
            this.aFloat4690 = aFloat4690;
            this.aFloat4699 = -this.aFloat4692;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ho.HA(" + n + ')');
        }
    }
    
    static final short method2117(final int n, final int n2) {
        try {
            final int n3 = (n & 0xFCC8) >> 1122429642;
            final int n4 = 0x70 & n >> -544476509;
            final int n5 = 0x7F & n;
            final int n6 = (~n5 < -65) ? ((127 - n5) * n4 >> -1227528857) : (n4 * n5 >> -1204704377);
            final int n7 = n5 + n6;
            if (~n7 != -1) {
                final int n8 = (n6 << 1992475560) / n7;
                if (!client.aBoolean3553) {
                    return (short)(n3 << 450770282 | n8 >> 45825476 << -224459065 | n7);
                }
            }
            final int n8 = n6 << 1259684257;
            return (short)(n3 << 450770282 | n8 >> 45825476 << -224459065 | n7);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ho.A(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method2092(final Class111 class111) {
        try {
            final Class111_Sub2 class111_Sub2 = (Class111_Sub2)class111;
            this.aFloat4696 = class111_Sub2.aFloat4696;
            this.aFloat4694 = class111_Sub2.aFloat4694;
            this.aFloat4689 = class111_Sub2.aFloat4689;
            this.aFloat4688 = class111_Sub2.aFloat4688;
            this.aFloat4691 = class111_Sub2.aFloat4691;
            this.aFloat4700 = class111_Sub2.aFloat4700;
            this.aFloat4693 = class111_Sub2.aFloat4693;
            this.aFloat4690 = class111_Sub2.aFloat4690;
            this.aFloat4699 = class111_Sub2.aFloat4699;
            this.aFloat4692 = class111_Sub2.aFloat4692;
            this.aFloat4698 = class111_Sub2.aFloat4698;
            this.aFloat4697 = class111_Sub2.aFloat4697;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ho.H(" + ((class111 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method2105(final int n) {
        try {
            final float n2 = Class64_Sub5.aFloatArray3653[n & 0x3FFF];
            final float n3 = Class64_Sub5.aFloatArray3651[n & 0x3FFF];
            final float aFloat4692 = this.aFloat4692;
            final float aFloat4693 = this.aFloat4688;
            final float aFloat4694 = this.aFloat4696;
            this.aFloat4692 = -(n3 * this.aFloat4693) + n2 * aFloat4692;
            final float aFloat4695 = this.aFloat4691;
            this.aFloat4688 = -(this.aFloat4698 * n3) + aFloat4693 * n2;
            this.aFloat4693 = n2 * this.aFloat4693 + aFloat4692 * n3;
            this.aFloat4698 = aFloat4693 * n3 + n2 * this.aFloat4698;
            this.aFloat4696 = -(this.aFloat4694 * n3) + aFloat4694 * n2;
            this.aFloat4691 = -(n3 * this.aFloat4689) + n2 * aFloat4695;
            this.aFloat4694 = aFloat4694 * n3 + n2 * this.aFloat4694;
            this.aFloat4689 = n2 * this.aFloat4689 + n3 * aFloat4695;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ho.O(" + n + ')');
        }
    }
    
    @Override
    final Class111 method2102() {
        try {
            final Class111_Sub2 class111_Sub2 = new Class111_Sub2();
            class111_Sub2.aFloat4690 = this.aFloat4690;
            class111_Sub2.aFloat4689 = this.aFloat4689;
            class111_Sub2.aFloat4694 = this.aFloat4694;
            class111_Sub2.aFloat4698 = this.aFloat4698;
            class111_Sub2.aFloat4692 = this.aFloat4692;
            class111_Sub2.aFloat4696 = this.aFloat4696;
            class111_Sub2.aFloat4700 = this.aFloat4700;
            class111_Sub2.aFloat4691 = this.aFloat4691;
            class111_Sub2.aFloat4693 = this.aFloat4693;
            class111_Sub2.aFloat4697 = this.aFloat4697;
            class111_Sub2.aFloat4699 = this.aFloat4699;
            class111_Sub2.aFloat4688 = this.aFloat4688;
            return class111_Sub2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ho.TA()");
        }
    }
    
    @Override
    final void method2099(final int n, final int n2, final int n3, final int[] array) {
        try {
            array[2] = (int)(this.aFloat4698 * n2 + this.aFloat4693 * n + n3 * this.aFloat4694);
            array[1] = (int)(n3 * this.aFloat4696 + (this.aFloat4688 * n2 + n * this.aFloat4692));
            array[0] = (int)(n3 * this.aFloat4690 + (n * this.aFloat4700 + this.aFloat4699 * n2));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ho.MA(" + n + ',' + n2 + ',' + n3 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method2106(final int n, final int n2, final int n3) {
        try {
            this.aFloat4697 += n;
            this.aFloat4691 += n2;
            this.aFloat4689 += n3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ho.G(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method2090(final int n) {
        try {
            final float n2 = Class64_Sub5.aFloatArray3653[0x3FFF & n];
            final float n3 = Class64_Sub5.aFloatArray3651[n & 0x3FFF];
            final float aFloat4700 = this.aFloat4700;
            final float aFloat4701 = this.aFloat4699;
            final float aFloat4702 = this.aFloat4690;
            this.aFloat4700 = aFloat4700 * n2 - n3 * this.aFloat4692;
            final float aFloat4703 = this.aFloat4697;
            this.aFloat4699 = aFloat4701 * n2 - this.aFloat4688 * n3;
            this.aFloat4692 = n3 * aFloat4700 + n2 * this.aFloat4692;
            this.aFloat4690 = aFloat4702 * n2 - n3 * this.aFloat4696;
            this.aFloat4688 = this.aFloat4688 * n2 + n3 * aFloat4701;
            this.aFloat4697 = aFloat4703 * n2 - this.aFloat4691 * n3;
            this.aFloat4696 = this.aFloat4696 * n2 + aFloat4702 * n3;
            this.aFloat4691 = aFloat4703 * n3 + n2 * this.aFloat4691;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ho.C(" + n + ')');
        }
    }
    
    @Override
    final void method2103(final int n, final int n2, final int n3, final int[] array) {
        try {
            array[2] = (int)(this.aFloat4689 + (this.aFloat4693 * n + this.aFloat4698 * n2 + this.aFloat4694 * n3));
            array[1] = (int)(this.aFloat4691 + (this.aFloat4696 * n3 + (this.aFloat4692 * n + this.aFloat4688 * n2)));
            array[0] = (int)(this.aFloat4697 + (this.aFloat4700 * n + n2 * this.aFloat4699 + n3 * this.aFloat4690));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ho.M(" + n + ',' + n2 + ',' + n3 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    public Class111_Sub2() {
        try {
            this.method2091();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ho.<init>()");
        }
    }
}
