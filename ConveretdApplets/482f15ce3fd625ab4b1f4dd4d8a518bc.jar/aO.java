// 
// Decompiled by Procyon v0.5.30
// 

public final class aO extends Exception
{
    public String a;
    public String b;
    public String c;
    
    public aO(final String a) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.a = a;
    }
    
    public aO(final String a, final String b, final String c) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public final String toString() {
        return "NESCafeException:[" + this.a + "]";
    }
    
    public final String getMessage() {
        return this.a;
    }
}
