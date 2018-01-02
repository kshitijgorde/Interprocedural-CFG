// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter;

public class n6 implements n7
{
    private String a;
    private String b;
    
    public n6() {
        this("", "");
    }
    
    public n6(final String a, final String b) {
        this.a = a;
        this.b = b;
    }
    
    public void a(final String a) {
        this.a = a;
    }
    
    public String a() {
        return this.a;
    }
    
    public void b(final String b) {
        this.b = b;
    }
    
    public String b() {
        return this.b;
    }
    
    public String c() {
        String substring = "";
        if (this.a != null) {
            final int n = this.a.lastIndexOf(".") + 1;
            if (n != -1 && n != this.a.length()) {
                substring = this.a.substring(n);
            }
        }
        return substring;
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        final n6 n6 = (n6)o;
        if (this.a == null) {
            if (n6.a != null) {
                return false;
            }
        }
        else if (!this.a.equals(n6.a)) {
            return false;
        }
        if (this.b == null) {
            if (n6.b != null) {
                return false;
            }
        }
        else if (!this.b.equals(n6.b)) {
            return false;
        }
        return true;
    }
    
    public String toString() {
        return this.getClass().getName() + ":  " + "filename=" + this.a + "; mimeType=" + this.b;
    }
}
