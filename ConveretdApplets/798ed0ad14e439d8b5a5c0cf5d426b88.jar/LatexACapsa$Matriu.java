// 
// Decompiled by Procyon v0.5.30
// 

class LatexACapsa$Matriu extends ParseLatex$FuncRender
{
    String AfegirNoCalc;
    private final LatexACapsa this$0;
    
    LatexACapsa$Matriu(final LatexACapsa this$0, final ParseLatex parseLatex, final String afegirNoCalc) {
        super(parseLatex);
        this.this$0 = this$0;
        this.AfegirNoCalc = afegirNoCalc;
    }
    
    final void f(final Token token, final Token[] array) {
        final Token[][] b = array[0].B();
        final int length = b.length;
        final int length2 = b[0].length;
        final MatrixBox matrixBox = new MatrixBox(length2, length);
        matrixBox.inicialitzaModalitat(this.AfegirNoCalc);
        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < length2; ++j) {
                matrixBox.AfegirNoCalc(LatexACapsa.I(this.this$0, b[i][j]));
            }
        }
        LatexACapsa.I(this.this$0, matrixBox);
    }
}
