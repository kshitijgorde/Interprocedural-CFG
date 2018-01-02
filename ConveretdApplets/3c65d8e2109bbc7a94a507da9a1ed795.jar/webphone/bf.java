// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

public class bf
{
    String try;
    int do;
    byte[] int;
    int a;
    aw new;
    t if;
    boolean for;
    
    public bf() {
        this.try = "";
        this.do = 0;
        this.int = null;
        this.a = 0;
        this.new = null;
        this.if = null;
        this.for = false;
    }
    
    public bf(final aw new1, final String try1, final int do1, final byte[] array, final int a, final t if1, final boolean for1) {
        this.try = "";
        this.do = 0;
        this.int = null;
        this.a = 0;
        this.new = null;
        this.if = null;
        this.for = false;
        this.a = 0;
        this.do = 0;
        if (a < 1 || do1 < 1) {
            return;
        }
        this.try = try1;
        this.do = do1;
        this.new = new1;
        this.int = new byte[a + 30];
        if (this.new.dK == 4 || this.new.dK == 5 || (this.new.dK == 3 && this.new.aX > 0)) {
            int n;
            if (this.new.es == 1) {
                this.int[0] = 46;
                this.int[1] = (byte)this.new.a(1, 126);
                this.int[2] = (byte)(this.int[1] ^ 0x54);
                this.int[3] = (byte)(this.int[1] ^ 0x79);
                this.int[4] = (byte)(this.int[1] ^ 0x37);
                this.int[5] = (byte)(this.int[1] ^ 0x55);
                n = 6;
            }
            else {
                this.int[0] = 68;
                this.int[1] = 97;
                this.int[2] = 54;
                this.int[3] = 65;
                n = 4;
            }
            if (for1) {
                this.int[n] = 103;
                ++n;
            }
            int a2;
            if (a > 4) {
                a2 = n + this.new.if(this.int, n, array, a);
            }
            else {
                System.arraycopy(array, 0, this.int, n, a);
                a2 = n + a;
            }
            if (this.new.es == 1) {
                this.int[a2 + 0] = 46;
                this.int[a2 + 1] = (byte)this.new.a(1, 126);
                this.int[a2 + 2] = (byte)(this.int[a2 + 1] ^ 0x54);
                this.int[a2 + 3] = (byte)(this.int[a2 + 1] ^ 0x79);
                this.int[a2 + 4] = (byte)(this.int[a2 + 1] ^ 0x37);
                this.int[a2 + 5] = (byte)(this.int[a2 + 1] ^ 0x55);
                a2 += 6;
            }
            else {
                this.int[a2 + 0] = 68;
                this.int[a2 + 1] = 97;
                this.int[a2 + 2] = 54;
                this.int[a2 + 3] = 65;
                a2 += 4;
            }
            this.a = a2;
            this.int[this.a] = 0;
        }
        else {
            System.arraycopy(array, 0, this.int, 0, a);
            this.a = a;
        }
        this.if = if1;
        this.for = for1;
    }
}
