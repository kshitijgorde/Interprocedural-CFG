// 
// Decompiled by Procyon v0.5.30
// 

public class WirisBoxScripting extends BoxScripting
{
    public WirisBoxScripting(final int[] array) {
        super(2, "", "", array);
    }
    
    public WirisBoxScripting() {
        super(2, "", "");
    }
    
    public final void onScript(final AbstractBox abstractBox, final AbstractBox[] array, final int n, final int n2) {
        if (abstractBox instanceof TokensBox) {
            this.scriptWirisQuotedExpression((TokensBox)abstractBox, n, n2);
        }
        else {
            super.onScript(abstractBox, array, n, n2);
        }
    }
    
    private void scriptWirisQuotedExpression(final TokensBox tokensBox, final int n, final int n2) {
        for (int i = n; i < n2; ++i) {
            if (SpecialSymbolBox.isQuote(tokensBox.fill[i])) {
                int n3;
                for (n3 = i + 1; n3 < n2 && !SpecialSymbolBox.isQuote(tokensBox.fill[n3]); ++n3) {
                    if (SpecialSymbolBox.containsQuote(tokensBox.fill[n3])) {
                        n3 = n2;
                    }
                }
                if (n3 < n2) {
                    tokensBox.fill[i].script(this);
                    SpecialSymbolBox.scriptWirisQuotedExpression(this, tokensBox, tokensBox.fill, i + 1, n3);
                    i = n3;
                }
            }
            tokensBox.fill[i].script(this);
        }
    }
}
