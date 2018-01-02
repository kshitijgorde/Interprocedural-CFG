// 
// Decompiled by Procyon v0.5.30
// 

class LatexACapsa$Particular extends ParseLatex$FuncRender
{
    int AfegirNoCalc;
    private final LatexACapsa this$0;
    
    LatexACapsa$Particular(final LatexACapsa this$0, final ParseLatex parseLatex, final int afegirNoCalc) {
        super(parseLatex);
        this.this$0 = this$0;
        this.AfegirNoCalc = afegirNoCalc;
    }
    
    final void f(final Token token, final Token[] array) {
        switch (this.AfegirNoCalc) {
            case 5: {
                LatexACapsa.I(this.this$0, this.this$0.I);
            }
            case 7: {
                final AbstractBox i = LatexACapsa.I(this.this$0, array[0]);
                if (MarkupBox.isCaret(i)) {
                    final EmptyBox emptyBox = new EmptyBox();
                    emptyBox.AfegirNoCalc(new MarkupBox("\\caret"));
                    LatexACapsa.I(this.this$0, emptyBox);
                    break;
                }
                LatexACapsa.I(this.this$0, i);
                break;
            }
            case 8: {
                final String j = this.this$0.I(token);
                AbstractBox box = MarkupBox.createBox(j);
                if (box == null) {
                    box = new SpecialSymbolBox(j);
                }
                LatexACapsa.I(this.this$0, box);
                break;
            }
            case 9: {
                final String string = "" + (char)((ParseLatex$TokenSimple)array[0]).c + (char)((ParseLatex$TokenSimple)array[2]).c;
                final CapsaParentesis capsaParentesis = new CapsaParentesis();
                capsaParentesis.inicialitzaModalitat(string);
                capsaParentesis.AfegirNoCalc(LatexACapsa.I(this.this$0, array[1]));
                LatexACapsa.I(this.this$0, capsaParentesis);
                break;
            }
            case 10: {
                final Token[][] b = array[0].B();
                MultilineBox multilineBox;
                if (b.length > 0) {
                    multilineBox = new MultilineBox();
                    multilineBox.inicialitzaFills(LatexACapsa.I(this.this$0, b[0][0]));
                }
                else {
                    multilineBox = new MultilineBox();
                    multilineBox.inicialitzaFills();
                }
                for (int k = 1; k < b.length; ++k) {
                    multilineBox.AfegirNoCalc(LatexACapsa.I(this.this$0, b[k][0]));
                }
                LatexACapsa.Z(this.this$0, multilineBox);
                break;
            }
            case 14: {
                LatexACapsa.I(this.this$0, new TextBox(" ", 3));
                break;
            }
        }
    }
}
