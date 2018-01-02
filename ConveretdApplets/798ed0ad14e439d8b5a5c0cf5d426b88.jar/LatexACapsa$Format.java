import java.util.Hashtable;
import java.awt.Color;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

class LatexACapsa$Format extends ParseLatex$FuncRender
{
    int AfegirNoCalc;
    int exchangeProperties;
    private final LatexACapsa this$0;
    
    LatexACapsa$Format(final LatexACapsa this$0, final ParseLatex parseLatex, final int afegirNoCalc) {
        super(parseLatex);
        this.this$0 = this$0;
        this.AfegirNoCalc = afegirNoCalc;
    }
    
    LatexACapsa$Format(final LatexACapsa this$0, final ParseLatex parseLatex, final int afegirNoCalc, final int exchangeProperties) {
        super(parseLatex);
        this.this$0 = this$0;
        this.AfegirNoCalc = afegirNoCalc;
        this.exchangeProperties = exchangeProperties;
    }
    
    final void f(final Token token, final Token[] array) {
        switch (this.AfegirNoCalc) {
            case 1: {
                final Font z = new Font(LatexACapsa.I(this.this$0, array[0]).plainString(), Integer.parseInt(LatexACapsa.I(this.this$0, array[1]).plainString()), Integer.parseInt(LatexACapsa.I(this.this$0, array[2]).plainString()));
                final Font z2 = this.this$0.Z;
                this.this$0.Z = z;
                array[3].renderitza(super.I.B);
                this.this$0.I();
                this.this$0.Z = z2;
                break;
            }
            case 2: {
                final Color c = new Color(Integer.parseInt(LatexACapsa.I(this.this$0, array[0]).plainString()), Integer.parseInt(LatexACapsa.I(this.this$0, array[1]).plainString()), Integer.parseInt(LatexACapsa.I(this.this$0, array[2]).plainString()));
                final Color c2 = this.this$0.C;
                this.this$0.C = c;
                array[3].renderitza(super.I.B);
                this.this$0.I();
                this.this$0.C = c2;
                break;
            }
            case 3: {
                final int b = this.this$0.B;
                final int d = this.this$0.D;
                final LatexACapsa this$0 = this.this$0;
                this$0.B |= this.exchangeProperties;
                final LatexACapsa this$2 = this.this$0;
                this$2.D |= this.exchangeProperties;
                array[0].renderitza(super.I.B);
                this.this$0.I();
                this.this$0.B = b;
                this.this$0.D = d;
                break;
            }
            case 4: {
                final Token[][] b2 = array[0].B();
                final AbstractBox z3 = LatexACapsa.Z(this.this$0, b2[b2.length - 1][0]);
                final Hashtable<String, String> hashtable = new Hashtable<String, String>();
                for (int i = 0; i < b2.length - 1; ++i) {
                    hashtable.put(LatexACapsa.I(this.this$0, b2[i][0]).plainString(), LatexACapsa.I(this.this$0, b2[i][1]).plainString());
                }
                z3.exchangeProperties(hashtable, 0);
                final String s = hashtable.get("style");
                if (s != null) {
                    Attributes.exchangeStyles(z3, s);
                }
                LatexACapsa.I(this.this$0, z3);
                break;
            }
            case 15: {
                final EmptyBox emptyBox = new EmptyBox();
                break;
            }
            case 11: {
                final Token[][] b3 = array[0].B();
                final TokensBox tokensBox = new TokensBox();
                final int length = b3.length;
                final AbstractBox[] array2 = new AbstractBox[length];
                for (int j = 0; j < length; ++j) {
                    tokensBox.AfegirNoCalc(LatexACapsa.I(this.this$0, b3[j][0]));
                }
                LatexACapsa.C(this.this$0, tokensBox);
                break;
            }
            case 12: {
                final EmptyBox emptyBox2 = new EmptyBox();
                break;
            }
            case 13: {
                final EmptyBox emptyBox3 = new EmptyBox();
                break;
            }
            case 16: {
                LatexACapsa.I(this.this$0, MathML2Box.newMathML2Box().parse(LatexACapsa.I(this.this$0, array[0]).plainString()));
                break;
            }
        }
    }
}
