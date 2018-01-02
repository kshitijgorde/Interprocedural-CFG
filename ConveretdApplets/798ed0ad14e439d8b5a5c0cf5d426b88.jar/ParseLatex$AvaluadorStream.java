// 
// Decompiled by Procyon v0.5.30
// 

class ParseLatex$AvaluadorStream extends ParseLatex$CallbackEval
{
    private final ParseLatex this$0;
    
    ParseLatex$AvaluadorStream(final ParseLatex this$0) {
        super(this$0, null);
        this.this$0 = this$0;
    }
    
    public final Token f(final Token token) {
        final StringBuffer sb = new StringBuffer();
        Token i;
        do {
            i = this.this$0.I();
            if (Token.Z(i.C()) || Token.C(i.C())) {
                sb.append((char)i.C());
            }
            if (i.C() == 65541 || i.C() == 65555 || Token.I(i.C())) {
                continue;
            }
        } while (i.C() != this.this$0.J);
        this.this$0.Z(i);
        return new ParseLatex$TokenComentari(this.this$0, sb.toString(), 0);
    }
}
