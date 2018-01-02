// 
// Decompiled by Procyon v0.5.30
// 

final class Class209
{
    private int anInt1582;
    int anInt1583;
    int anInt1584;
    private int[] anIntArray1585;
    private int[] anIntArray1586;
    int anInt1587;
    private int anInt1588;
    private int anInt1589;
    private int anInt1590;
    private int anInt1591;
    private int anInt1592;
    
    final void method2769() {
        this.anInt1590 = 0;
        this.anInt1588 = 0;
        this.anInt1591 = 0;
        this.anInt1589 = 0;
        this.anInt1592 = 0;
    }
    
    final int method2770(final int n) {
        if (this.anInt1592 >= this.anInt1590) {
            this.anInt1589 = this.anIntArray1585[this.anInt1588++] << 15;
            if (this.anInt1588 >= this.anInt1582) {
                this.anInt1588 = this.anInt1582 - 1;
            }
            this.anInt1590 = (int)(this.anIntArray1586[this.anInt1588] / 65536.0 * n);
            if (this.anInt1590 > this.anInt1592) {
                this.anInt1591 = ((this.anIntArray1585[this.anInt1588] << 15) - this.anInt1589) / (this.anInt1590 - this.anInt1592);
            }
        }
        this.anInt1589 += this.anInt1591;
        ++this.anInt1592;
        return this.anInt1589 - this.anInt1591 >> 15;
    }
    
    final void method2771(final Class98_Sub22 class98_Sub22) {
        this.anInt1584 = class98_Sub22.readUnsignedByte((byte)115);
        this.anInt1587 = class98_Sub22.readInt(-2);
        this.anInt1583 = class98_Sub22.readInt(-2);
        this.method2772(class98_Sub22);
    }
    
    final void method2772(final Class98_Sub22 class98_Sub22) {
        this.anInt1582 = class98_Sub22.readUnsignedByte((byte)93);
        this.anIntArray1586 = new int[this.anInt1582];
        this.anIntArray1585 = new int[this.anInt1582];
        for (int i = 0; i < this.anInt1582; ++i) {
            this.anIntArray1586[i] = class98_Sub22.readShort((byte)127);
            this.anIntArray1585[i] = class98_Sub22.readShort((byte)127);
        }
    }
    
    public Class209() {
        this.anInt1582 = 2;
        this.anIntArray1586 = new int[2];
        this.anIntArray1585 = new int[2];
        this.anIntArray1586[0] = 0;
        this.anIntArray1586[1] = 65535;
        this.anIntArray1585[0] = 0;
        this.anIntArray1585[1] = 65535;
    }
}
