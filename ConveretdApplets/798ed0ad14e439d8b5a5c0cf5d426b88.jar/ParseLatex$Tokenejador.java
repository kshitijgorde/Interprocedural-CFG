// 
// Decompiled by Procyon v0.5.30
// 

abstract class ParseLatex$Tokenejador
{
    ParseLatex$LlistaTokens getAt;
    private final ParseLatex this$0;
    
    final Token I() {
        final int n = this.getAt.size() - 1;
        if (n >= 0) {
            final Token at = this.getAt.getAt(n);
            this.getAt.removeElementAt(n);
            return at;
        }
        return this.Z();
    }
    
    ParseLatex$Tokenejador(final ParseLatex this$0) {
        this.this$0 = this$0;
        this.getAt = new ParseLatex$LlistaTokens(this$0);
    }
    
    Token Z() {
        return new ParseLatex$TokenSimple(this.this$0, 65536, 0);
    }
}
