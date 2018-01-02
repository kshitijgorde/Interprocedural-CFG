// 
// Decompiled by Procyon v0.5.30
// 

package ji.util;

public class co
{
    public int a;
    public int b;
    public int c;
    public String d;
    public byte[] e;
    public int f;
    public int g;
    public int h;
    public String i;
    public byte[] j;
    
    public co() {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = "";
        this.e = null;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = "";
        this.j = null;
    }
    
    public final boolean a() {
        return this.a == this.f && this.b == this.g && this.c == this.h;
    }
    
    public final boolean b() {
        return this.a > 0;
    }
    
    public final boolean c() {
        return this.b() && (this.f != this.a || this.g != this.b || this.h != this.c);
    }
    
    public final String d() {
        return String.valueOf(String.valueOf(new StringBuffer("").append(this.a).append(".").append(this.b).append(".").append(this.c)));
    }
    
    public final String e() {
        return String.valueOf(String.valueOf(new StringBuffer("").append(this.f).append(".").append(this.g).append(".").append(this.h)));
    }
}
