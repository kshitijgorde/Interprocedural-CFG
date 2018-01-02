import java.util.Enumeration;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

final class ParseLatex$LlistaTokens extends Vector
{
    private final ParseLatex this$0;
    
    ParseLatex$LlistaTokens(final ParseLatex this$0) {
        this.this$0 = this$0;
    }
    
    public final Token getAt(final int n) {
        return super.elementAt(n);
    }
    
    public final boolean add(final Token token) {
        this.addElement(token);
        return true;
    }
    
    public final Token[] tokens() {
        final Enumeration<Token> elements = this.elements();
        final int size = this.size();
        final Token[] array = new Token[size];
        for (int i = 0; i < size; ++i) {
            array[i] = elements.nextElement();
        }
        return array;
    }
    
    public final void addEval(final ParseLatex$LlistaTokens parseLatex$LlistaTokens) {
        final int size = parseLatex$LlistaTokens.size();
        Token at;
        if (size > 1) {
            at = new ParseLatex$TokenCompost(this.this$0, new ParseLatex$TokenSimple(this.this$0, 65545, 0), parseLatex$LlistaTokens.tokens(), 0);
        }
        else if (size == 1) {
            at = parseLatex$LlistaTokens.getAt(0);
        }
        else {
            at = new ParseLatex$TokenSimple(this.this$0, 65539, 0);
        }
        this.add(this.this$0.C(at));
    }
}
