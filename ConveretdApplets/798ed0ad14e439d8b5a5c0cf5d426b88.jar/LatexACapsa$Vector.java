// 
// Decompiled by Procyon v0.5.30
// 

class LatexACapsa$Vector extends ParseLatex$FuncRender
{
    String AfegirNoCalc;
    private final LatexACapsa this$0;
    
    LatexACapsa$Vector(final LatexACapsa this$0, final ParseLatex parseLatex, final String afegirNoCalc) {
        super(parseLatex);
        this.this$0 = this$0;
        this.AfegirNoCalc = afegirNoCalc;
    }
    
    final void f(final Token token, final Token[] array) {
        final Token[][] b = array[0].B();
        final int length = b[0].length;
        final CapsaParentesis capsaParentesis = new CapsaParentesis();
        capsaParentesis.inicialitzaModalitat(this.AfegirNoCalc + this.AfegirNoCalc);
        final TokensBox tokensBox = new TokensBox();
        capsaParentesis.AfegirNoCalc(tokensBox);
        for (int i = 0; i < length; ++i) {
            if (i != 0) {
                tokensBox.AfegirNoCalc(new TextBox(",", 3));
                tokensBox.AfegirNoCalc(new TextBox(" ", 3));
            }
            final AbstractBox j = LatexACapsa.I(this.this$0, b[0][i]);
            if (j instanceof TokensBox) {
                for (int k = 0; k < j.nfills; ++k) {
                    tokensBox.AfegirNoCalc(j.fill[k]);
                }
            }
            else {
                tokensBox.AfegirNoCalc(j);
            }
        }
        LatexACapsa.I(this.this$0, capsaParentesis);
    }
}
