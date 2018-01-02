// 
// Decompiled by Procyon v0.5.30
// 

class LatexACapsa$Si extends ParseLatex$FuncRender
{
    int inicialitzaFills;
    private final LatexACapsa this$0;
    
    LatexACapsa$Si(final LatexACapsa this$0, final ParseLatex parseLatex, final int inicialitzaFills) {
        super(parseLatex);
        this.this$0 = this$0;
        this.inicialitzaFills = inicialitzaFills;
    }
    
    final void f(final Token token, final Token[] array) {
        final Token[][] b = array[0].B();
        int n = 0;
        for (int i = 0; i < b.length; ++i) {
            n += b[i].length;
        }
        final AbstractBox[] array2 = new AbstractBox[n];
        int n2 = 0;
        for (int j = 0; j < b.length; ++j) {
            for (int k = 0; k < b[j].length; ++k) {
                array2[n2++] = LatexACapsa.I(this.this$0, b[j][k]);
            }
        }
        final CapsaSi capsaSi = new CapsaSi();
        capsaSi.extra = this.inicialitzaFills;
        capsaSi.inicialitzaFills(array2);
        LatexACapsa.I(this.this$0, capsaSi);
    }
}
