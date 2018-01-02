// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class p
{
    static final int try = 64;
    static final int do = 64;
    c[] int;
    bi[] byte;
    int if;
    int new;
    char[] for;
    au a;
    az case;
    
    p() {
        this.int = new c[66];
        this.byte = new bi[66];
        this.if = 0;
        this.new = 0;
    }
    
    public void a() {
        this.int = null;
        this.byte = null;
        this.case = null;
    }
    
    void a(final char[] for1) {
        this.for = for1;
        this.case = new az();
    }
    
    void a(final int n, final bi bi) {
        this.case.a(this.byte[n], bi, false);
    }
    
    bi if(final int n) {
        return this.byte[n];
    }
    
    int do(final char[] array) {
        for (int i = 0; i < this.new; ++i) {
            if (i.a(this.byte[i].do, array, 31) == 0) {
                return i;
            }
        }
        return -1;
    }
    
    void a(final bi bi) throws a5 {
        this.byte[this.new] = bi;
        ++this.new;
        if (this.new > 64) {
            throw new a5("stack overflow");
        }
    }
    
    void a(final bi bi, final int n) {
        this.case.a(bi, this.byte[n], false);
    }
    
    int if(final char[] array) {
        for (int i = 0; i < this.if; ++i) {
            if (i.a(this.int[i].if, array, c.for) == 0) {
                return i;
            }
        }
        return -1;
    }
    
    void a(final c c) throws a5 {
        this.int[this.if] = c;
        ++this.if;
        if (this.if > 64) {
            throw new a5("too many functions declared");
        }
    }
    
    c a(final int n) {
        return this.int[n];
    }
    
    char[] if() {
        return this.for;
    }
}
