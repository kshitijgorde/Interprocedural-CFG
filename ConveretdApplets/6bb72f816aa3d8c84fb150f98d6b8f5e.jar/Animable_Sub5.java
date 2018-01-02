// 
// Decompiled by Procyon v0.5.30
// 

final class Animable_Sub5 extends Animable
{
    private int anInt1599;
    private final int[] anIntArray1600;
    private final int anInt1601;
    private final int anInt1602;
    private final int anInt1603;
    private final int anInt1604;
    private final int anInt1605;
    private final int anInt1606;
    private Animation aAnimation_1607;
    private int anInt1608;
    public static client clientInstance;
    private final int anInt1610;
    private final int anInt1611;
    private final int anInt1612;
    
    public Model getRotatedModel() {
        int n = -1;
        if (this.aAnimation_1607 != null) {
            int i = client.loopCycle - this.anInt1608;
            if (i > 100 && this.aAnimation_1607.anInt356 > 0) {
                i = 100;
            }
            while (i > this.aAnimation_1607.method258(this.anInt1599)) {
                i -= this.aAnimation_1607.method258(this.anInt1599);
                ++this.anInt1599;
                if (this.anInt1599 < this.aAnimation_1607.anInt352) {
                    continue;
                }
                this.anInt1599 -= this.aAnimation_1607.anInt356;
                if (this.anInt1599 >= 0 && this.anInt1599 < this.aAnimation_1607.anInt352) {
                    continue;
                }
                this.aAnimation_1607 = null;
                break;
            }
            this.anInt1608 = client.loopCycle - i;
            if (this.aAnimation_1607 != null) {
                n = this.aAnimation_1607.anIntArray353[this.anInt1599];
            }
        }
        ObjectDef objectDef;
        if (this.anIntArray1600 != null) {
            objectDef = this.method457();
        }
        else {
            objectDef = ObjectDef.forID(this.anInt1610);
        }
        if (objectDef == null) {
            return null;
        }
        return objectDef.method578(this.anInt1611, this.anInt1612, this.anInt1603, this.anInt1604, this.anInt1605, this.anInt1606, n);
    }
    
    private ObjectDef method457() {
        try {
            int n = -1;
            if (this.anInt1601 != -1) {
                final VarBit varBit = VarBit.cache[this.anInt1601];
                final int anInt648 = varBit.anInt648;
                final int anInt649 = varBit.anInt649;
                n = (Animable_Sub5.clientInstance.variousSettings[anInt648] >> anInt649 & client.anIntArray1232[varBit.anInt650 - anInt649]);
            }
            else if (this.anInt1602 != -1) {
                n = Animable_Sub5.clientInstance.variousSettings[this.anInt1602];
            }
            if (n < 0 || n >= this.anIntArray1600.length || this.anIntArray1600[n] == -1) {
                return null;
            }
            return ObjectDef.forID(this.anIntArray1600[n]);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }
    
    public Animable_Sub5(final int anInt1610, final int anInt1611, final int anInt1612, final int anInt1613, final int anInt1614, final int anInt1615, final int anInt1616, final int n, final boolean b) {
        this.anInt1610 = anInt1610;
        this.anInt1611 = anInt1612;
        this.anInt1612 = anInt1611;
        this.anInt1603 = anInt1615;
        this.anInt1604 = anInt1613;
        this.anInt1605 = anInt1614;
        this.anInt1606 = anInt1616;
        if (n != -1) {
            this.aAnimation_1607 = Animation.anims[n];
            this.anInt1599 = 0;
            this.anInt1608 = client.loopCycle;
            if (b && this.aAnimation_1607.anInt356 != -1) {
                this.anInt1599 = (int)(Math.random() * this.aAnimation_1607.anInt352);
                this.anInt1608 -= (int)(Math.random() * this.aAnimation_1607.method258(this.anInt1599));
            }
        }
        final ObjectDef forID = ObjectDef.forID(this.anInt1610);
        this.anInt1601 = forID.anInt774;
        this.anInt1602 = forID.anInt749;
        this.anIntArray1600 = forID.childrenIDs;
    }
}
