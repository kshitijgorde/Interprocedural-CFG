// 
// Decompiled by Procyon v0.5.30
// 

class ParseLatex$TokenComentari extends Token
{
    String I;
    private final ParseLatex this$0;
    
    ParseLatex$TokenComentari(final ParseLatex this$0, final String i, final int n) {
        super(n);
        this.this$0 = this$0;
        this.I = i;
    }
    
    public final String toString() {
        return this.I;
    }
}
