// 
// Decompiled by Procyon v0.5.30
// 

class ParseLatex$TokenCompost extends Token
{
    Token I;
    Token[] Z;
    private final ParseLatex this$0;
    
    ParseLatex$TokenCompost(final ParseLatex this$0, final Token i, final Token[] z, final int n) {
        super(n);
        this.this$0 = this$0;
        this.I = i;
        this.Z = z;
    }
    
    public final void renderitza(final ParseLatex$Render parseLatex$Render) {
        if (this.I.getClass() == ((ParseLatex.S == null) ? (ParseLatex.S = ParseLatex.C("ParseLatex$TokenSimple")) : ParseLatex.S)) {
            final ParseLatex$CallbackRender z = parseLatex$Render.Z(this.I.C());
            if (z.I == 2) {
                z.f(this);
                return;
            }
        }
        this.I.renderitza(parseLatex$Render);
        for (int i = 0; i < this.Z.length; ++i) {
            this.Z[i].renderitza(parseLatex$Render);
        }
    }
}
