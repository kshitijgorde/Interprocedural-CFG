// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class j
{
    static final int try = 64;
    static final int do = 64;
    b[] int;
    a3[] byte;
    int if;
    int new;
    char[] for;
    ah a;
    al case;
    
    j() {
        this.int = new b[66];
        this.byte = new a3[66];
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
        this.case = new al();
    }
    
    void a(final int n, final a3 a3) {
        this.case.a(this.byte[n], a3, false);
    }
    
    a3 if(final int n) {
        return this.byte[n];
    }
    
    int do(final char[] array) {
        for (int i = 0; i < this.new; ++i) {
            if (g.a(this.byte[i].do, array, 31) == 0) {
                return i;
            }
        }
        return -1;
    }
    
    void a(final a3 a3) throws ar {
        this.byte[this.new] = a3;
        ++this.new;
        if (this.new > 64) {
            throw new ar("stack overflow");
        }
    }
    
    void a(final a3 a3, final int n) {
        this.case.a(a3, this.byte[n], false);
    }
    
    int if(final char[] array) {
        for (int i = 0; i < this.if; ++i) {
            if (g.a(this.int[i].if, array, b.for) == 0) {
                return i;
            }
        }
        return -1;
    }
    
    void a(final b b) throws ar {
        this.int[this.if] = b;
        ++this.if;
        if (this.if > 64) {
            throw new ar("too many functions declared");
        }
    }
    
    b a(final int n) {
        return this.int[n];
    }
    
    char[] if() {
        return this.for;
    }
}
