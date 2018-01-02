// 
// Decompiled by Procyon v0.5.30
// 

package d.b.a;

public class ad
{
    public static final int for = 1024;
    private byte[] a;
    private int do;
    private int if;
    
    public void for() {
        this.a = new byte[1024];
        this.do = 0;
        this.if = 0;
    }
    
    public void if(final int n) {
        this.do += n >> 3;
        this.if += (n & 0x7);
        if (this.if > 7) {
            this.if -= 8;
            ++this.do;
        }
    }
    
    protected void a(final byte[] a) {
        this.a = a;
    }
    
    public int a() {
        return (this.a[this.do] & 0xFF) >> 7 - this.if & 0x1;
    }
    
    public void a(final byte[] array, final int n, final int n2) {
        for (int i = 0; i < n2; ++i) {
            this.a[i] = array[n + i];
        }
        this.do = 0;
        this.if = 0;
    }
    
    public int a(int i) {
        int n = 0;
        while (i != 0) {
            n = (n << 1 | ((this.a[this.do] & 0xFF) >> 7 - this.if & 0x1));
            ++this.if;
            if (this.if == 8) {
                this.if = 0;
                ++this.do;
            }
            --i;
        }
        return n;
    }
    
    public void a(final int n, int i) {
        while (this.do + (i + this.if >> 3) >= this.a.length) {
            final byte[] a = new byte[this.a.length * 2];
            System.arraycopy(this.a, 0, a, 0, this.a.length);
            this.a = a;
        }
        while (i > 0) {
            final int n2 = n >> i - 1 & 0x1;
            final byte[] a2 = this.a;
            final int do1 = this.do;
            a2[do1] |= (byte)(n2 << 7 - this.if);
            ++this.if;
            if (this.if == 8) {
                this.if = 0;
                ++this.do;
            }
            --i;
        }
    }
    
    public byte[] if() {
        return this.a;
    }
    
    public int do() {
        return this.do + ((this.if > 0) ? 1 : 0);
    }
}
