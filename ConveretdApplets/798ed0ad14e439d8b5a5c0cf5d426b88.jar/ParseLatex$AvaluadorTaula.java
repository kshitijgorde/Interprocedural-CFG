import java.util.Enumeration;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class ParseLatex$AvaluadorTaula extends ParseLatex$CallbackEval
{
    private final ParseLatex this$0;
    
    ParseLatex$AvaluadorTaula(final ParseLatex this$0) {
        super(this$0, null);
        this.this$0 = this$0;
    }
    
    public final Token f(final Token token) {
        final Vector vector = new Vector<ParseLatex$LlistaTokens>();
        final ParseLatex$LlistaTokens parseLatex$LlistaTokens = new ParseLatex$LlistaTokens(this.this$0);
        final ParseLatex$LlistaTokens parseLatex$LlistaTokens2 = new ParseLatex$LlistaTokens(this.this$0);
        ParseLatex$LlistaTokens parseLatex$LlistaTokens3 = new ParseLatex$LlistaTokens(this.this$0);
        final Token$TokenTaula token$TokenTaula = new Token$TokenTaula(0);
        Token token2 = null;
        int i = 0;
        this.this$0.Z();
        while (i == 0) {
            token2 = this.this$0.C();
            if (token2.C() == 65543 || token2.C() == 65548 || token2.C() == token.C()) {
                int n = 0;
                int n2 = 0;
                int n3 = 0;
                if (token2.C() == 65548) {
                    n = 1;
                }
                if (token2.C() == 65543) {
                    n2 = 1;
                }
                if (token2.C() == token.C()) {
                    n3 = 1;
                }
                token2 = this.this$0.C();
                parseLatex$LlistaTokens2.removeAllElements();
                while (token2.C() != 0) {
                    if (token2.C() == 65548) {
                        n ^= 0x1;
                    }
                    if (token2.C() == 65544) {
                        if (n2 == 0) {
                            break;
                        }
                        if (--n2 == 0) {
                            break;
                        }
                        if (n3 != 0) {
                            break;
                        }
                    }
                    if (token2.C() == 65543) {
                        ++n2;
                    }
                    if (token2.C() == token.C() + 1) {
                        if (n3 == 0) {
                            break;
                        }
                        if (--n3 == 0 && n2 == 0) {
                            break;
                        }
                    }
                    if (token2.C() == token.C()) {
                        ++n2;
                    }
                    parseLatex$LlistaTokens2.add(token2);
                    token2 = this.this$0.C();
                }
                if (token2.C() == 0 || n2 != 0 || n != 0 || n3 != 0) {
                    this.this$0.Error(0, 0, "", "Esperava token \"}\" o \"$\".\n");
                    return new ParseLatex$TokenSimple(this.this$0, 65536, 0);
                }
                final int size = parseLatex$LlistaTokens2.size();
                if (size > 1) {
                    parseLatex$LlistaTokens.add(new ParseLatex$TokenCompost(this.this$0, new ParseLatex$TokenSimple(this.this$0, 65545, 0), parseLatex$LlistaTokens2.tokens(), 0));
                }
                else if (size == 0) {
                    parseLatex$LlistaTokens.add(new ParseLatex$TokenSimple(this.this$0, 65539, 0));
                }
                else {
                    parseLatex$LlistaTokens.add(parseLatex$LlistaTokens2.getAt(0));
                }
            }
            else {
                switch (token2.C()) {
                    case 0:
                    case 65544: {
                        i = 1;
                        continue;
                    }
                    case 65550: {
                        parseLatex$LlistaTokens3.addEval(parseLatex$LlistaTokens);
                        parseLatex$LlistaTokens.removeAllElements();
                        vector.addElement(parseLatex$LlistaTokens3);
                        parseLatex$LlistaTokens3 = new ParseLatex$LlistaTokens(this.this$0);
                        continue;
                    }
                    case 65551: {
                        parseLatex$LlistaTokens3.addEval(parseLatex$LlistaTokens);
                        parseLatex$LlistaTokens.removeAllElements();
                        continue;
                    }
                    default: {
                        if (token2.C() == token.C() + 1) {
                            token2 = new ParseLatex$TokenSimple(this.this$0, 65536, 0);
                            i = 1;
                            continue;
                        }
                        parseLatex$LlistaTokens.add(token2);
                        continue;
                    }
                }
            }
        }
        this.this$0.Z(token2);
        parseLatex$LlistaTokens3.addEval(parseLatex$LlistaTokens);
        vector.addElement(parseLatex$LlistaTokens3);
        final Token$TokenTaula token$TokenTaula2 = new Token$TokenTaula(0);
        final int size2 = vector.size();
        token$TokenTaula2.I = new Token[size2][];
        for (int j = 0; j < size2; ++j) {
            final ParseLatex$LlistaTokens parseLatex$LlistaTokens4 = vector.elementAt(j);
            final int size3 = parseLatex$LlistaTokens4.size();
            token$TokenTaula2.I[j] = new Token[size3];
            final Enumeration<Token> elements = parseLatex$LlistaTokens4.elements();
            for (int k = 0; k < size3; ++k) {
                token$TokenTaula2.I[j][k] = elements.nextElement();
            }
        }
        return new ParseLatex$TokenCompost(this.this$0, token, new Token[] { token$TokenTaula2 }, 0);
    }
}
