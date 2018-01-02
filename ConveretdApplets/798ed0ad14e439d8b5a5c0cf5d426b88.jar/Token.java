// 
// Decompiled by Procyon v0.5.30
// 

public abstract class Token
{
    public int pos;
    
    final Token[][] B() {
        if (this instanceof Token$TokenTaula) {
            return ((Token$TokenTaula)this).I;
        }
        return null;
    }
    
    static final boolean I(final int n) {
        return n == 65540 || n == 32 || n == 65541 || n == 9;
    }
    
    static final boolean Z(final int n) {
        return (65 <= n && n <= 90) || (97 <= n && n <= 122) || (192 <= n && n <= 255) || (913 <= n && n <= 974) || n == 65554;
    }
    
    static final boolean C(final int n) {
        return 48 <= n && n <= 57;
    }
    
    static final boolean B(final int n) {
        return (32 < n && n < 256 && !Z(n) && !C(n)) || n == 8364;
    }
    
    boolean I() {
        return false;
    }
    
    boolean Z() {
        return false;
    }
    
    int C() {
        return 65546;
    }
    
    public Token avalua() {
        return this;
    }
    
    public void renderitza(final ParseLatex$Render parseLatex$Render) {
    }
    
    Token(final int pos) {
        ++OmegaSystem.contador2;
        this.pos = pos;
    }
}
