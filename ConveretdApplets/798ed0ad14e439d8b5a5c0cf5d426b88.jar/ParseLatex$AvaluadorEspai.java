// 
// Decompiled by Procyon v0.5.30
// 

class ParseLatex$AvaluadorEspai extends ParseLatex$CallbackEval
{
    private final ParseLatex this$0;
    
    ParseLatex$AvaluadorEspai(final ParseLatex this$0) {
        super(this$0, null);
        this.this$0 = this$0;
    }
    
    public final Token f(final Token token) {
        int n = 0;
        int n2 = 0;
        Token i;
        do {
            i = this.this$0.I();
            if (i.C() == 65541) {
                ++n;
            }
            if (i.C() == 65555) {
                ++n2;
            }
        } while (i.C() == 65541 || i.C() == 65555 || Token.I(i.C()));
        this.this$0.Z(i);
        return new ParseLatex$TokenSimple(this.this$0, 65540, 0);
    }
}
