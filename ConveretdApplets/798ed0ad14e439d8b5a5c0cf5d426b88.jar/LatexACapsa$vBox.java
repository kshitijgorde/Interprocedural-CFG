// 
// Decompiled by Procyon v0.5.30
// 

class LatexACapsa$vBox extends ParseLatex$FuncRender
{
    String AfegirNoCalc;
    private final LatexACapsa this$0;
    
    LatexACapsa$vBox(final LatexACapsa this$0, final ParseLatex parseLatex, final String afegirNoCalc) {
        super(parseLatex);
        this.this$0 = this$0;
        this.AfegirNoCalc = afegirNoCalc;
    }
    
    final void f(final Token token, final Token[] array) {
        final Token[][] b = array[0].B();
        final TokensVBox tokensVBox = new TokensVBox();
        if (b.length > 0) {
            tokensVBox.inicialitzaFills(LatexACapsa.I(this.this$0, b[0][0]));
        }
        else {
            tokensVBox.inicialitzaFills();
        }
        tokensVBox.setBaselineType(this.AfegirNoCalc);
        for (int i = 1; i < b.length; ++i) {
            tokensVBox.AfegirNoCalc(LatexACapsa.I(this.this$0, b[i][0]));
        }
        LatexACapsa.Z(this.this$0, tokensVBox);
    }
}
