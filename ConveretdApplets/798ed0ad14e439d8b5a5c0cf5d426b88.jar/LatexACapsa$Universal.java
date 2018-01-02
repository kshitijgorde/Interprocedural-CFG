// 
// Decompiled by Procyon v0.5.30
// 

class LatexACapsa$Universal extends ParseLatex$FuncRender
{
    boolean inicialitzaFills;
    int inicialitzaModalitat;
    Class A;
    Object max;
    private final LatexACapsa this$0;
    
    LatexACapsa$Universal(final LatexACapsa latexACapsa, final ParseLatex parseLatex, final int n, final Class clazz, final Object o) {
        this(latexACapsa, parseLatex, n, clazz, o, true);
    }
    
    LatexACapsa$Universal(final LatexACapsa this$0, final ParseLatex parseLatex, final int inicialitzaModalitat, final Class a, final Object max, final boolean inicialitzaFills) {
        super(parseLatex);
        this.this$0 = this$0;
        this.inicialitzaFills = inicialitzaFills;
        this.inicialitzaModalitat = inicialitzaModalitat;
        this.A = a;
        this.max = max;
    }
    
    final void f(final Token token, final Token[] array) {
        try {
            final AbstractBox abstractBox = this.A.newInstance();
            abstractBox.inicialitzaModalitat(this.max);
            final AbstractBox[] array2 = new AbstractBox[Math.max(this.inicialitzaModalitat, abstractBox.nombreMinimDeFills() - abstractBox.nfills)];
            for (int i = 0; i < this.inicialitzaModalitat; ++i) {
                array2[i] = LatexACapsa.I(this.this$0, array[i]);
            }
            for (int j = 0; j < abstractBox.nombreMinimDeFills() - this.inicialitzaModalitat - abstractBox.nfills; ++j) {
                array2[j + this.inicialitzaModalitat] = new EmptyBox();
            }
            abstractBox.inicialitzaFills(array2);
            if (this.inicialitzaFills) {
                LatexACapsa.I(this.this$0, abstractBox);
            }
            else {
                LatexACapsa.Z(this.this$0, abstractBox);
            }
        }
        catch (InstantiationException ex) {
            ex.printStackTrace();
        }
        catch (IllegalAccessException ex2) {
            ex2.printStackTrace();
        }
    }
}
