// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.lexer.yacc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.jruby.util.ByteList;
import org.jcodings.Encoding;
import java.io.IOException;
import org.jruby.parser.ParserConfiguration;
import java.io.InputStream;
import java.util.List;

public abstract class LexerSource
{
    private SimplePositionFactory positionFactory;
    private final String sourceName;
    protected int line;
    protected int offset;
    private List<String> list;
    private StringBuilder lineBuffer;
    private StringBuilder sourceLine;
    
    protected LexerSource(final String sourceName, final List<String> list, final int line, final boolean extraPositionInformation) {
        this.line = 0;
        this.offset = 0;
        this.sourceName = sourceName;
        this.line = line;
        this.positionFactory = new SimplePositionFactory(this, line);
        this.list = list;
        this.lineBuffer = new StringBuilder(160);
        this.sourceLine = new StringBuilder(160);
    }
    
    public String getFilename() {
        return this.sourceName;
    }
    
    public int getLine() {
        return this.line;
    }
    
    public int getOffset() {
        return (this.offset <= 0) ? 0 : this.offset;
    }
    
    public ISourcePosition getPosition(final ISourcePosition startPosition) {
        return this.positionFactory.getPosition(startPosition);
    }
    
    public ISourcePosition getPosition() {
        return this.positionFactory.getPosition(null);
    }
    
    public static LexerSource getSource(final String name, final InputStream content, final List<String> list, final ParserConfiguration configuration) {
        return new InputStreamLexerSource(name, content, list, configuration.getLineNumber(), configuration.hasExtraPositionInformation());
    }
    
    public static LexerSource getSource(final String name, final byte[] content, final List<String> list, final ParserConfiguration configuration) {
        return new ByteArrayLexerSource(name, content, list, configuration.getLineNumber(), configuration.hasExtraPositionInformation());
    }
    
    private void captureFeatureNewline() {
        final StringBuilder temp = this.sourceLine;
        this.sourceLine = this.lineBuffer;
        if (this.list != null && this.lineBuffer.length() > 0) {
            this.list.add(this.sourceLine.toString());
        }
        temp.setLength(0);
        this.lineBuffer = temp;
    }
    
    protected void captureFeature(final int c) {
        switch (c) {
            case 10: {
                this.lineBuffer.append((char)c);
            }
            case -1: {
                this.captureFeatureNewline();
                break;
            }
            default: {
                this.lineBuffer.append((char)c);
                break;
            }
        }
    }
    
    protected void uncaptureFeature(final int c) {
        int end = this.lineBuffer.length() - 1;
        if (end >= 0 && this.lineBuffer.charAt(end) == c) {
            this.lineBuffer.deleteCharAt(end);
        }
        else if (c == 10 && this.list != null && !this.list.isEmpty()) {
            this.lineBuffer = new StringBuilder(this.list.remove(this.list.size() - 1));
            end = this.lineBuffer.length() - 1;
            if (this.lineBuffer.charAt(end) == '\n') {
                this.lineBuffer.deleteCharAt(end);
            }
        }
    }
    
    public String getCurrentLine() {
        final int errorLocation = this.lineBuffer.length() - 1;
        try {
            this.readLineBytes();
        }
        catch (IOException ex) {}
        return this.sourceLine.toString() + this.makePointer(errorLocation);
    }
    
    protected String makePointer(final int length) {
        final StringBuilder buf = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            buf.append(' ');
        }
        buf.append('^');
        return buf.toString();
    }
    
    public int readCodepoint(final int first, final Encoding encoding) throws IOException {
        int count = 0;
        final byte[] value = new byte[6];
        value[0] = (byte)first;
        for (count = 1; count < 6; ++count) {
            final int c = this.read();
            if (c == -1) {
                break;
            }
            value[count] = (byte)c;
        }
        final int length = encoding.length(value, 0, count);
        if (length < 0) {
            return -2;
        }
        final int codepoint = encoding.mbcToCode(value, 0, length);
        for (int i = count - 1; i >= length; --i) {
            this.unread(value[i]);
        }
        return codepoint;
    }
    
    public abstract boolean matchMarker(final ByteList p0, final boolean p1, final boolean p2) throws IOException;
    
    public abstract int read() throws IOException;
    
    public abstract ByteList readUntil(final char p0) throws IOException;
    
    public abstract ByteList readLineBytes() throws IOException;
    
    public abstract int skipUntil(final int p0) throws IOException;
    
    public abstract void unread(final int p0);
    
    public abstract void unreadMany(final CharSequence p0);
    
    public abstract boolean peek(final int p0) throws IOException;
    
    public abstract boolean lastWasBeginOfLine();
    
    public abstract boolean wasBeginOfLine();
    
    public abstract InputStream getRemainingAsStream() throws IOException;
}
