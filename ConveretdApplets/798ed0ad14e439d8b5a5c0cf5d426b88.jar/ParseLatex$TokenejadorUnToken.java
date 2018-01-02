// 
// Decompiled by Procyon v0.5.30
// 

class ParseLatex$TokenejadorUnToken extends ParseLatex$Tokenejador
{
    Token I;
    private final ParseLatex this$0;
    
    ParseLatex$TokenejadorUnToken(final ParseLatex this$0, final Token i) {
        super(this$0);
        this.this$0 = this$0;
        this.I = i;
    }
    
    final Token Z() {
        final Token i = this.I;
        this.I = new ParseLatex$TokenSimple(this.this$0, 65537, 0);
        return i;
    }
}
