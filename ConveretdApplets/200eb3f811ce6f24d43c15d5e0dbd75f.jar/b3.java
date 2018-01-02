// 
// Decompiled by Procyon v0.5.30
// 

public final class b3
{
    private long a;
    private String b;
    
    public b3(final long a, final String b) {
        this.a = a;
        this.b = b;
    }
    
    public final long a() {
        return this.a;
    }
    
    public final String b() {
        return this.b;
    }
    
    public final Double c() {
        return new Double(this.a);
    }
    
    public String toString() {
        return "DateTime" + this.a() + ":" + this.b();
    }
}
