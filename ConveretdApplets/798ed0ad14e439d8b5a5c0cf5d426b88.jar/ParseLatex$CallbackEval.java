// 
// Decompiled by Procyon v0.5.30
// 

class ParseLatex$CallbackEval
{
    private final ParseLatex this$0;
    
    private ParseLatex$CallbackEval(final ParseLatex this$0) {
        this.this$0 = this$0;
    }
    
    Token f(final Token token) {
        return new ParseLatex$TokenSimple(this.this$0, 65536, 0);
    }
    
    ParseLatex$CallbackEval(final ParseLatex parseLatex, final ParseLatex$1 parseLatex$1) {
        this(parseLatex);
    }
}
