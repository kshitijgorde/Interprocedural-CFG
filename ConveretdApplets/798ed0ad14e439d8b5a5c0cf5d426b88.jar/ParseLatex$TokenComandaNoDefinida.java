// 
// Decompiled by Procyon v0.5.30
// 

class ParseLatex$TokenComandaNoDefinida extends Token
{
    String append;
    private final ParseLatex this$0;
    
    ParseLatex$TokenComandaNoDefinida(final ParseLatex this$0, final String append, final int n) {
        super(n);
        this.this$0 = this$0;
        this.append = append;
    }
    
    public final void renderitza(final ParseLatex$Render parseLatex$Render) {
        this.this$0.Error(0, 0, "", "Comanda no coneguda \"" + this.append + "\".\n");
    }
}
