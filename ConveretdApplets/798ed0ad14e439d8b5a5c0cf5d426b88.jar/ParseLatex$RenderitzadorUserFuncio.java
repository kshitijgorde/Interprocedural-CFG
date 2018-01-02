// 
// Decompiled by Procyon v0.5.30
// 

class ParseLatex$RenderitzadorUserFuncio extends ParseLatex$CallbackRender
{
    ParseLatex$FuncRender Z;
    private final ParseLatex this$0;
    
    ParseLatex$RenderitzadorUserFuncio(final ParseLatex this$0, final ParseLatex$FuncRender z) {
        this.this$0 = this$0;
        super.I = 2;
        this.Z = z;
    }
    
    public final void f(final Token token) {
        final ParseLatex$TokenCompost parseLatex$TokenCompost = (ParseLatex$TokenCompost)token;
        this.this$0.B.I();
        this.Z.f(parseLatex$TokenCompost.I, parseLatex$TokenCompost.Z);
    }
}
