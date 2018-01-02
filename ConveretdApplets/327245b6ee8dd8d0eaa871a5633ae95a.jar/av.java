// 
// Decompiled by Procyon v0.5.30
// 

public class av extends c
{
    boolean a;
    
    c a(final aa aa) {
        return aa.c;
    }
    
    public av(final boolean a) {
        super.a = 2;
        this.a = a;
    }
    
    String r() {
        return "boolean";
    }
    
    c h(final int n) {
        return this;
    }
    
    public String toString() {
        if (this.a) {
            return "true";
        }
        return "false";
    }
    
    public float af() {
        if (this.a) {
            return 1.0f;
        }
        return 0.0f;
    }
    
    boolean ag() {
        return this.a;
    }
}
