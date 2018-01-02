// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

class b
{
    static int for;
    static int do;
    char[] if;
    int new;
    int int;
    a3[] a;
    
    static {
        b.for = 31;
        b.do = 32;
    }
    
    b() {
        this.if = new char[b.for];
        this.a = new a3[b.do + 2];
    }
    
    public void a() {
        this.if = null;
        if (this.a != null) {
            for (int i = 0; i < this.a.length; ++i) {
                if (this.a[i] != null) {
                    this.a[i].a();
                    this.a[i] = null;
                }
            }
        }
        this.a = null;
    }
}
