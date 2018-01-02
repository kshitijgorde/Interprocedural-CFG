import java.util.Enumeration;

// 
// Decompiled by Procyon v0.5.30
// 

class ParseLatex$TokenejadorLlista extends ParseLatex$Tokenejador
{
    ParseLatex$LlistaTokens add;
    private final ParseLatex this$0;
    
    public ParseLatex$TokenejadorLlista(final ParseLatex this$0, final ParseLatex$LlistaTokens parseLatex$LlistaTokens) {
        super(this$0);
        this.this$0 = this$0;
        this.add = new ParseLatex$LlistaTokens(this$0);
        final Enumeration<Object> elements = parseLatex$LlistaTokens.elements();
        while (elements.hasMoreElements()) {
            this.add.addElement(elements.nextElement());
        }
    }
    
    public ParseLatex$TokenejadorLlista(final ParseLatex this$0, final Token[] array) {
        super(this$0);
        this.this$0 = this$0;
        this.add = new ParseLatex$LlistaTokens(this$0);
        for (int length = array.length, i = 0; i < length; ++i) {
            this.add.add(array[i]);
        }
    }
    
    public ParseLatex$TokenejadorLlista(final ParseLatex this$0, final Token[] array, int i, final int n) {
        super(this$0);
        this.this$0 = this$0;
        this.add = new ParseLatex$LlistaTokens(this$0);
        while (i < n) {
            this.add.add(array[i]);
            ++i;
        }
    }
    
    final Token Z() {
        if (this.add.isEmpty()) {
            return new ParseLatex$TokenSimple(this.this$0, 65537, 0);
        }
        final Token token = this.add.firstElement();
        this.add.removeElementAt(0);
        return token;
    }
}
