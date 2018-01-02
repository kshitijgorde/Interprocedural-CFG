// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter;

class oz
{
    int a;
    int b;
    int c;
    int d;
    o0[] e;
    
    oz() {
        this.a = 0;
        this.b = 0;
        this.c = 8;
        this.d = 0;
        this.e = null;
    }
    
    public oz a() {
        final oz oz = new oz();
        oz.a = this.a;
        oz.b = this.b;
        oz.c = this.c;
        oz.d = this.d;
        if (this.e != null) {
            oz.e = new o0[this.e.length];
            for (int i = 0; i < this.e.length; ++i) {
                oz.e[i] = new o0();
                oz.e[i].a = this.e[i].a;
                oz.e[i].b = this.e[i].b;
                oz.e[i].c = this.e[i].c;
            }
        }
        return oz;
    }
}
