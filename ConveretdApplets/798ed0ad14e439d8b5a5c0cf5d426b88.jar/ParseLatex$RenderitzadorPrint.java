// 
// Decompiled by Procyon v0.5.30
// 

class ParseLatex$RenderitzadorPrint extends ParseLatex$CallbackRender
{
    private final ParseLatex this$0;
    
    ParseLatex$RenderitzadorPrint(final ParseLatex this$0) {
        this.this$0 = this$0;
        super.I = 1;
    }
    
    public final void f(final Token token) {
        this.this$0.B.I(token.C());
    }
}
