// 
// Decompiled by Procyon v0.5.30
// 

package ji.document;

public class es extends et
{
    private int a;
    private int b;
    
    public es(final String s, final int a) {
        super(s);
        this.b = -1;
        this.a = a;
    }
    
    public int a() {
        return this.a;
    }
    
    public int b() {
        return this.b;
    }
    
    public boolean c() {
        return this.b != -1;
    }
    
    public void a(final int b) {
        this.b = b;
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof es)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final es es = (es)o;
        return this.a == es.a && this.b == es.b;
    }
    
    public et d() {
        final es es = new es(this.e(), this.a);
        es.a(this.f());
        es.b(this.g());
        es.b = this.b;
        return es;
    }
    
    public String toString() {
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(super.toString()))).append(": ").append(this.a).append(": ").append(this.b)));
    }
}
