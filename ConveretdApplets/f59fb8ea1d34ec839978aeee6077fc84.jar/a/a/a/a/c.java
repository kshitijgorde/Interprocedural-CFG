// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

class c
{
    static int for;
    static int do;
    char[] if;
    int new;
    int int;
    bi[] a;
    
    static {
        c.for = 31;
        c.do = 32;
    }
    
    c() {
        this.if = new char[c.for];
        this.a = new bi[c.do + 2];
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
