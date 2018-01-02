// 
// Decompiled by Procyon v0.5.30
// 

package buddysoft.a;

class f
{
    private int a;
    private int if;
    
    int a() {
        if (this.a == 0) {
            this.a = this.if;
        }
        this.a *= this.if;
        this.a += this.if % 10;
        this.a *= this.a % 2;
        this.a %= 3;
        if (++this.if > 17) {
            this.if = 0;
        }
        return this.a;
    }
    
    void a(final int a) {
        this.a = a;
        this.if = 7;
    }
}
