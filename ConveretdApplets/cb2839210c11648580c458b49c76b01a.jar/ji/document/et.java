// 
// Decompiled by Procyon v0.5.30
// 

package ji.document;

public class et
{
    private String a;
    private boolean b;
    private boolean c;
    
    public et(final String a) {
        this.a = a;
    }
    
    public final void a(final boolean b) {
        this.b = b;
    }
    
    public final void b(final boolean c) {
        this.c = c;
    }
    
    public final String e() {
        return this.a;
    }
    
    public final boolean f() {
        return this.b;
    }
    
    public final boolean g() {
        return this.c;
    }
    
    public final boolean h() {
        return this.a != null && this.a.length() > 0;
    }
    
    public boolean equals(final Object o) {
        if (!(o instanceof et)) {
            return false;
        }
        final et et = (et)o;
        return this.a.equals(et.a) && this.b == et.b && this.c == et.c;
    }
    
    public String toString() {
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a))).append(": ").append(this.b).append(": ").append(this.c)));
    }
}
