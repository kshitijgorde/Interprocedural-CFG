// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.lexer.yacc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.jcodings.Encoding;
import org.jruby.util.KCode;
import java.io.IOException;
import org.jruby.util.RegexpOptions;
import org.jruby.ast.RegexpNode;
import org.jruby.util.ByteList;

public class StringTerm extends StrTerm
{
    private int flags;
    private final char begin;
    private final char end;
    private int nest;
    
    public StringTerm(final int flags, final int begin, final int end) {
        this.flags = flags;
        this.begin = (char)begin;
        this.end = (char)end;
        this.nest = 0;
    }
    
    protected ByteList createByteList(final RubyYaccLexer lexer) {
        if (lexer.isOneEight()) {
            return new ByteList();
        }
        return new ByteList(new byte[0], lexer.getEncoding());
    }
    
    private int endFound(final RubyYaccLexer lexer, final LexerSource src) throws IOException {
        if ((this.flags & 0x8) != 0x0) {
            this.flags = -1;
            lexer.getPosition();
            return 32;
        }
        if ((this.flags & 0x4) != 0x0) {
            final RegexpOptions options = this.parseRegexpFlags(src);
            final ByteList regexpBytelist = ByteList.create("");
            lexer.setValue(new RegexpNode(src.getPosition(), regexpBytelist, options));
            return 380;
        }
        lexer.setValue(new Token("\"", lexer.getPosition()));
        return 372;
    }
    
    public int parseString(final RubyYaccLexer lexer, final LexerSource src) throws IOException {
        boolean spaceSeen = false;
        if (this.flags == -1) {
            lexer.setValue(new Token("\"", lexer.getPosition()));
            return 372;
        }
        int c = src.read();
        if ((this.flags & 0x8) != 0x0 && Character.isWhitespace(c)) {
            do {
                c = src.read();
            } while (Character.isWhitespace(c));
            spaceSeen = true;
        }
        if (c == this.end && this.nest == 0) {
            return this.endFound(lexer, src);
        }
        if (spaceSeen) {
            src.unread(c);
            lexer.getPosition();
            return 32;
        }
        final ByteList buffer = this.createByteList(lexer);
        if ((this.flags & 0x2) != 0x0 && c == 35) {
            c = src.read();
            switch (c) {
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
                    buffer.append((byte)35);
                    break;
                }
            }
        }
        src.unread(c);
        if (this.parseStringIntoBuffer(lexer, src, buffer) == -1) {
            throw new SyntaxException(SyntaxException.PID.STRING_HITS_EOF, src.getPosition(), src.getCurrentLine(), "unterminated string meets end of file", new Object[0]);
        }
        lexer.setValue(lexer.createStrNode(lexer.getPosition(), buffer, this.flags));
        return 377;
    }
    
    private RegexpOptions parseRegexpFlags(final LexerSource src) throws IOException {
        final RegexpOptions options = new RegexpOptions();
        final StringBuilder unknownFlags = new StringBuilder(10);
        int c;
        for (c = src.read(); c != -1 && Character.isLetter(c); c = src.read()) {
            switch (c) {
                case 105: {
                    options.setIgnorecase(true);
                    break;
                }
                case 120: {
                    options.setExtended(true);
                    break;
                }
                case 109: {
                    options.setMultiline(true);
                    break;
                }
                case 111: {
                    options.setOnce(true);
                    break;
                }
                case 110: {
                    options.setExplicitKCode(KCode.NONE);
                    break;
                }
                case 101: {
                    options.setExplicitKCode(KCode.EUC);
                    break;
                }
                case 115: {
                    options.setExplicitKCode(KCode.SJIS);
                    break;
                }
                case 117: {
                    options.setExplicitKCode(KCode.UTF8);
                    break;
                }
                case 106: {
                    options.setJava(true);
                    break;
                }
                default: {
                    unknownFlags.append((char)c);
                    break;
                }
            }
        }
        src.unread(c);
        if (unknownFlags.length() != 0) {
            throw new SyntaxException(SyntaxException.PID.REGEXP_UNKNOWN_OPTION, src.getPosition(), "unknown regexp option" + ((unknownFlags.length() > 1) ? "s" : "") + " - " + unknownFlags.toString(), unknownFlags.toString(), new Object[0]);
        }
        return options;
    }
    
    private void mixedEscape(final RubyYaccLexer lexer, final Encoding foundEncoding, final Encoding parserEncoding) {
        throw new SyntaxException(SyntaxException.PID.MIXED_ENCODING, lexer.getPosition(), "", foundEncoding + " mixed within " + parserEncoding, new Object[0]);
    }
    
    public int parseStringIntoBuffer(final RubyYaccLexer lexer, final LexerSource src, final ByteList buffer) throws IOException {
        final boolean qwords = (this.flags & 0x8) != 0x0;
        final boolean expand = (this.flags & 0x2) != 0x0;
        final boolean escape = (this.flags & 0x1) != 0x0;
        final boolean regexp = (this.flags & 0x4) != 0x0;
        final boolean symbol = (this.flags & 0x10) != 0x0;
        boolean hasNonAscii = false;
        final Encoding encoding = lexer.getEncoding();
        int c;
        while ((c = src.read()) != -1) {
            Label_0668: {
                if (this.begin != '\0' && c == this.begin) {
                    ++this.nest;
                }
                else if (c == this.end) {
                    if (this.nest == 0) {
                        src.unread(c);
                        break;
                    }
                    --this.nest;
                }
                else if (expand && c == 35 && !src.peek(10)) {
                    final int c2 = src.read();
                    if (c2 == 36 || c2 == 64 || c2 == 123) {
                        src.unread(c2);
                        src.unread(c);
                        break;
                    }
                    src.unread(c2);
                }
                else if (c == 92) {
                    c = src.read();
                    switch (c) {
                        case 10: {
                            if (qwords) {
                                break Label_0668;
                            }
                            if (expand) {
                                continue;
                            }
                            buffer.append(92);
                            break Label_0668;
                        }
                        case 92: {
                            if (escape) {
                                buffer.append(c);
                            }
                            break Label_0668;
                        }
                        case 117: {
                            if (lexer.isOneEight()) {
                                break;
                            }
                            if (!expand) {
                                buffer.append(92);
                                break Label_0668;
                            }
                            if (regexp) {
                                lexer.readUTFEscapeRegexpLiteral(buffer);
                            }
                            else {
                                lexer.readUTFEscape(buffer, true, symbol);
                            }
                            if (hasNonAscii && buffer.getEncoding() != encoding) {
                                this.mixedEscape(lexer, buffer.getEncoding(), encoding);
                                continue;
                            }
                            continue;
                        }
                    }
                    if (regexp) {
                        src.unread(c);
                        this.parseEscapeIntoBuffer(src, buffer);
                        if (hasNonAscii && buffer.getEncoding() != encoding) {
                            this.mixedEscape(lexer, buffer.getEncoding(), encoding);
                            continue;
                        }
                        continue;
                    }
                    else if (expand) {
                        src.unread(c);
                        if (escape) {
                            buffer.append(92);
                        }
                        c = lexer.readEscape();
                    }
                    else if (!qwords || !Character.isWhitespace(c)) {
                        if (c != this.end && (this.begin == '\0' || c != this.begin)) {
                            buffer.append(92);
                        }
                    }
                }
                else if (!lexer.isOneEight() && !Encoding.isAscii((byte)c)) {
                    if (buffer.getEncoding() != encoding) {
                        this.mixedEscape(lexer, buffer.getEncoding(), encoding);
                    }
                    c = src.readCodepoint(c, encoding);
                    if (c == -2) {
                        throw new SyntaxException(SyntaxException.PID.INVALID_MULTIBYTE_CHAR, lexer.getPosition(), null, "invalid multibyte char (" + encoding + ")", new Object[0]);
                    }
                    if (lexer.tokenAddMBC(c, buffer) == -1) {
                        return -1;
                    }
                    continue;
                }
                else if (qwords && Character.isWhitespace(c)) {
                    src.unread(c);
                    break;
                }
            }
            if (!lexer.isOneEight() && (c & 0x80) != 0x0) {
                hasNonAscii = true;
                if (buffer.getEncoding() != encoding) {
                    this.mixedEscape(lexer, buffer.getEncoding(), encoding);
                }
            }
            buffer.append(c);
        }
        return c;
    }
    
    private void escaped(final LexerSource src, final ByteList buffer) throws IOException {
        final int c;
        switch (c = src.read()) {
            case 92: {
                this.parseEscapeIntoBuffer(src, buffer);
                break;
            }
            case -1: {
                throw new SyntaxException(SyntaxException.PID.INVALID_ESCAPE_SYNTAX, src.getPosition(), src.getCurrentLine(), "Invalid escape character syntax", new Object[0]);
            }
            default: {
                buffer.append(c);
                break;
            }
        }
    }
    
    private void parseEscapeIntoBuffer(final LexerSource src, final ByteList buffer) throws IOException {
        int c;
        switch (c = src.read()) {
            case 10: {
                break;
            }
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55: {
                buffer.append(92);
                buffer.append(c);
                for (int i = 0; i < 2; ++i) {
                    c = src.read();
                    if (c == -1) {
                        throw new SyntaxException(SyntaxException.PID.INVALID_ESCAPE_SYNTAX, src.getPosition(), src.getCurrentLine(), "Invalid escape character syntax", new Object[0]);
                    }
                    if (!RubyYaccLexer.isOctChar(c)) {
                        src.unread(c);
                        break;
                    }
                    buffer.append(c);
                }
                break;
            }
            case 120: {
                buffer.append(92);
                buffer.append(c);
                c = src.read();
                if (!RubyYaccLexer.isHexChar(c)) {
                    throw new SyntaxException(SyntaxException.PID.INVALID_ESCAPE_SYNTAX, src.getPosition(), src.getCurrentLine(), "Invalid escape character syntax", new Object[0]);
                }
                buffer.append(c);
                c = src.read();
                if (RubyYaccLexer.isHexChar(c)) {
                    buffer.append(c);
                    break;
                }
                src.unread(c);
                break;
            }
            case 77: {
                if ((c = src.read()) != 45) {
                    throw new SyntaxException(SyntaxException.PID.INVALID_ESCAPE_SYNTAX, src.getPosition(), src.getCurrentLine(), "Invalid escape character syntax", new Object[0]);
                }
                buffer.append(new byte[] { 92, 77, 45 });
                this.escaped(src, buffer);
                break;
            }
            case 67: {
                if ((c = src.read()) != 45) {
                    throw new SyntaxException(SyntaxException.PID.INVALID_ESCAPE_SYNTAX, src.getPosition(), src.getCurrentLine(), "Invalid escape character syntax", new Object[0]);
                }
                buffer.append(new byte[] { 92, 67, 45 });
                this.escaped(src, buffer);
                break;
            }
            case 99: {
                buffer.append(new byte[] { 92, 99 });
                this.escaped(src, buffer);
                break;
            }
            case -1: {
                throw new SyntaxException(SyntaxException.PID.INVALID_ESCAPE_SYNTAX, src.getPosition(), src.getCurrentLine(), "Invalid escape character syntax", new Object[0]);
            }
            default: {
                if (c != 92 || c != this.end) {
                    buffer.append(92);
                }
                buffer.append(c);
                break;
            }
        }
    }
}
