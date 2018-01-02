// 
// Decompiled by Procyon v0.5.30
// 

class ParseLatex$AvaluadorFuncio extends ParseLatex$CallbackEval
{
    int addElement;
    private final ParseLatex this$0;
    
    public final Token f(final Token token) {
        final ParseLatex$LlistaTokens[] i = this.this$0.I(this.addElement);
        final ParseLatex$LlistaTokens parseLatex$LlistaTokens = i[0];
        final ParseLatex$LlistaTokens parseLatex$LlistaTokens2 = i[1];
        if (parseLatex$LlistaTokens == null) {
            this.this$0.Z.addElement(new ParseLatex$TokenejadorLlista(this.this$0, parseLatex$LlistaTokens2));
            return token;
        }
        final Token[] tokens = parseLatex$LlistaTokens.tokens();
        for (int j = 0; j < this.addElement; ++j) {
            tokens[j] = this.this$0.C(tokens[j]);
        }
        return new ParseLatex$TokenCompost(this.this$0, token, tokens, 0);
    }
    
    ParseLatex$AvaluadorFuncio(final ParseLatex this$0, final int addElement) {
        super(this$0, null);
        this.this$0 = this$0;
        this.addElement = addElement;
    }
}
