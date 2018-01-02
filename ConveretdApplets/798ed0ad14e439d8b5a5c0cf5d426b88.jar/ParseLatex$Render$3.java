// 
// Decompiled by Procyon v0.5.30
// 

class ParseLatex$Render$3 extends ParseLatex$CallbackRender
{
    private final ParseLatex$Render this$1;
    
    ParseLatex$Render$3(final ParseLatex$Render this$1) {
        this.this$1 = this$1;
        super.I = 2;
    }
    
    public final void f(final Token token) {
        final ParseLatex$TokenCompost parseLatex$TokenCompost = (ParseLatex$TokenCompost)token;
        for (int i = 0; i < parseLatex$TokenCompost.Z.length; ++i) {
            parseLatex$TokenCompost.Z[i].renderitza(ParseLatex$Render.I(this.this$1).B);
        }
    }
}
