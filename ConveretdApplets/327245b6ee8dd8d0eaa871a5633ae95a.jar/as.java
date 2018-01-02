// 
// Decompiled by Procyon v0.5.30
// 

public class as extends c
{
    String a;
    String b;
    int c;
    
    c a(final aa aa) {
        return aa.e;
    }
    
    public as() {
        this("");
    }
    
    public as(final String a) {
        super.a = 4;
        this.a = a;
        this.b = a.toLowerCase();
        this.c = this.b.hashCode();
    }
    
    public float af() {
        try {
            return Float.valueOf(this.a);
        }
        catch (NumberFormatException ex) {
            try {
                if (this.a.startsWith("0x")) {
                    return Integer.valueOf(this.a.substring(2, this.a.length()), 16);
                }
            }
            catch (NumberFormatException ex2) {}
            return Float.NaN;
        }
    }
    
    boolean ag() {
        return true;
    }
    
    String r() {
        return "string";
    }
    
    public String toString() {
        return this.a;
    }
    
    c h(final int n) {
        return this;
    }
    
    public c c(final String s) {
        if (s.equals("length")) {
            return new aq(this.a.length());
        }
        return null;
    }
}
