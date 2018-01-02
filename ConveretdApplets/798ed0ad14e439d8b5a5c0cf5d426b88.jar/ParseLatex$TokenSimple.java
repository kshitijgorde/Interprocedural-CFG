// 
// Decompiled by Procyon v0.5.30
// 

class ParseLatex$TokenSimple extends Token
{
    public int c;
    private final ParseLatex this$0;
    
    final boolean I() {
        return this.c == 0;
    }
    
    final boolean Z() {
        return this.c == 65537;
    }
    
    final int C() {
        return this.c;
    }
    
    public final Token avalua() {
        return this.this$0.Z(this.c).f(this);
    }
    
    public final void renderitza(final ParseLatex$Render parseLatex$Render) {
        final ParseLatex$CallbackRender z = parseLatex$Render.Z(this.c);
        if (z.I == 1) {
            z.f(this);
        }
        else {
            parseLatex$Render.I(this);
        }
    }
    
    ParseLatex$TokenSimple(final ParseLatex this$0, final int c, final int n) {
        super(n);
        this.this$0 = this$0;
        this.c = c;
    }
}
