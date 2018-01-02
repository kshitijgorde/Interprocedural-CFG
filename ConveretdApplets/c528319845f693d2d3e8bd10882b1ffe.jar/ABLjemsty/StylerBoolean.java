// 
// Decompiled by Procyon v0.5.30
// 

package ABLjemsty;

public class StylerBoolean
{
    private boolean a;
    private boolean b;
    public int c;
    
    public StylerBoolean() {
        this.b = false;
    }
    
    public StylerBoolean(final boolean b) {
        this.b = false;
        this.a(b);
    }
    
    public StylerBoolean(final boolean b, final int c) {
        this(b);
        this.c = c;
    }
    
    public void a(final boolean a) {
        this.a = a;
        this.b = true;
    }
    
    public boolean a() {
        return this.b;
    }
    
    public boolean b() {
        if (!this.b) {
            throw new StylerBooleanException("StylerBoolean value not set");
        }
        return this.a;
    }
    
    public boolean c() {
        if (!this.b) {
            throw new StylerBooleanException("StylerBoolean value not set");
        }
        return this.a;
    }
}
