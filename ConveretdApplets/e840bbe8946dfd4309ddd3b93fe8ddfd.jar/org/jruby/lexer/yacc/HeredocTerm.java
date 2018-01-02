// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.lexer.yacc;

import java.io.IOException;
import org.jruby.util.ByteList;

public class HeredocTerm extends StrTerm
{
    private final ByteList marker;
    private final int flags;
    private final ByteList lastLine;
    
    public HeredocTerm(final ByteList marker, final int func, final ByteList lastLine) {
        this.marker = marker;
        this.flags = func;
        this.lastLine = lastLine;
    }
    
    public int parseString(final RubyYaccLexer lexer, final LexerSource src) throws IOException {
        final boolean indent = (this.flags & 0x20) != 0x0;
        if (src.peek(-1)) {
            this.syntaxError(src);
        }
        if (src.lastWasBeginOfLine() && src.matchMarker(this.marker, indent, true)) {
            final ISourcePosition position = lexer.getPosition();
            src.unreadMany(this.lastLine);
            lexer.yaccValue = new Token(this.marker, position);
            return 372;
        }
        final ByteList str = new ByteList();
        str.setEncoding(lexer.getEncoding());
        ISourcePosition position2;
        if ((this.flags & 0x2) == 0x0) {
            do {
                str.append(src.readLineBytes());
                str.append(10);
                if (src.peek(-1)) {
                    this.syntaxError(src);
                }
                position2 = lexer.getPosition();
            } while (!src.matchMarker(this.marker, indent, true));
        }
        else {
            int c = src.read();
            if (c == 35) {
                switch (c = src.read()) {
                    case 36:
                    case 64: {
                        src.unread(c);
                        lexer.setValue(new Token("#" + c, lexer.getPosition()));
                        return 371;
                    }
                    case 123: {
                        lexer.setValue(new Token("#" + c, lexer.getPosition()));
                        return 370;
                    }
                    default: {
                        str.append(35);
                        break;
                    }
                }
            }
            src.unread(c);
            do {
                if ((c = new StringTerm(this.flags, 0, 10).parseStringIntoBuffer(lexer, src, str)) == -1) {
                    this.syntaxError(src);
                }
                if (c != 10) {
                    lexer.yaccValue = lexer.createStrNode(lexer.getPosition(), str, 0);
                    return 377;
                }
                str.append(src.read());
                if (src.peek(-1)) {
                    this.syntaxError(src);
                }
                position2 = lexer.getPosition();
            } while (!src.matchMarker(this.marker, indent, true));
        }
        src.unreadMany(this.lastLine);
        lexer.setStrTerm(new StringTerm(-1, 0, 0));
        lexer.yaccValue = lexer.createStrNode(position2, str, 0);
        return 377;
    }
    
    private void syntaxError(final LexerSource src) {
        throw new SyntaxException(SyntaxException.PID.STRING_MARKER_MISSING, src.getPosition(), src.getCurrentLine(), "can't find string \"" + (Object)this.marker + "\" anywhere before EOF", new Object[] { this.marker });
    }
}
