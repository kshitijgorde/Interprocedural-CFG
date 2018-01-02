// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.lexer.yacc;

import java.io.ByteArrayInputStream;
import org.jruby.util.ByteList;
import java.io.IOException;
import java.util.List;
import java.io.InputStream;

public class InputStreamLexerSource extends LexerSource
{
    private static final int INITIAL_PUSHBACK_SIZE = 100;
    private final InputStream in;
    private char[] buf;
    private int bufLength;
    private int oneAgo;
    private int twoAgo;
    
    public InputStreamLexerSource(final String sourceName, final InputStream in, final List<String> list, final int line, final boolean extraPositionInformation) {
        super(sourceName, list, line, extraPositionInformation);
        this.buf = new char[100];
        this.bufLength = -1;
        this.oneAgo = 10;
        this.twoAgo = 0;
        this.in = in;
    }
    
    public int read() throws IOException {
        int c;
        if (this.bufLength >= 0) {
            c = this.buf[this.bufLength--];
        }
        else {
            c = this.wrappedRead();
            if (c == -1) {
                return -1;
            }
        }
        this.advance(c);
        if (c == 10) {
            ++this.line;
        }
        return c;
    }
    
    public void unread(final int c) {
        if (c == -1) {
            return;
        }
        this.retreat();
        if (c == 10) {
            --this.line;
        }
        this.buf[++this.bufLength] = (char)c;
        this.growBuf();
    }
    
    public boolean peek(final int to) throws IOException {
        final int captureTwoAgo = this.twoAgo;
        final int c = this.read();
        this.unread(c);
        this.twoAgo = captureTwoAgo;
        return c == to;
    }
    
    private void advance(final int c) {
        this.twoAgo = this.oneAgo;
        this.oneAgo = c;
        ++this.offset;
    }
    
    private int carriageReturn(int c) throws IOException {
        if ((c = this.in.read()) != 10) {
            this.unread((char)c);
            c = 10;
        }
        else {
            ++this.offset;
        }
        return c;
    }
    
    private void growBuf() {
        if (this.bufLength + 1 == this.buf.length) {
            final char[] newBuf = new char[this.buf.length + 100];
            System.arraycopy(this.buf, 0, newBuf, 0, this.buf.length);
            this.buf = newBuf;
        }
    }
    
    private void retreat() {
        --this.offset;
        this.oneAgo = this.twoAgo;
        this.twoAgo = 0;
    }
    
    private int wrappedRead() throws IOException {
        int c = this.in.read();
        if (c == 13) {
            c = this.carriageReturn(c);
        }
        this.captureFeature(c);
        return c;
    }
    
    public ByteList readLineBytes() throws IOException {
        final ByteList bytelist = new ByteList(80);
        for (int c = this.read(); c != 10 && c != -1; c = this.read()) {
            bytelist.append(c);
        }
        return bytelist;
    }
    
    public int skipUntil(final int marker) throws IOException {
        int c;
        for (c = this.read(); c != marker && c != -1; c = this.read()) {}
        return c;
    }
    
    public void unreadMany(final CharSequence buffer) {
        final int length = buffer.length();
        for (int i = length - 1; i >= 0; --i) {
            this.unread(buffer.charAt(i));
        }
    }
    
    public boolean matchMarker(final ByteList match, final boolean indent, final boolean checkNewline) throws IOException {
        final int length = match.length();
        final ByteList buffer = new ByteList(length + 1);
        if (indent) {
            this.indentLoop(buffer);
        }
        return this.matches(match, buffer, length) && this.finishMarker(checkNewline, buffer);
    }
    
    private void indentLoop(final ByteList buffer) throws IOException {
        int c;
        while ((c = this.read()) != -1) {
            if (!Character.isWhitespace(c) || c == 10) {
                this.unread(c);
                break;
            }
            buffer.append(c);
        }
    }
    
    private boolean matches(final ByteList match, final ByteList buffer, final int length) throws IOException {
        for (int i = 0; i < length; ++i) {
            final int c = this.read();
            buffer.append(c);
            if (match.charAt(i) != c) {
                this.unreadMany(buffer);
                return false;
            }
        }
        return true;
    }
    
    private boolean finishMarker(final boolean checkNewline, final ByteList buffer) throws IOException {
        if (!checkNewline) {
            return true;
        }
        final int c = this.read();
        if (c == -1 || c == 10) {
            return true;
        }
        buffer.append(c);
        this.unreadMany(buffer);
        return false;
    }
    
    public boolean wasBeginOfLine() {
        return this.twoAgo == 10;
    }
    
    public boolean lastWasBeginOfLine() {
        return this.oneAgo == 10;
    }
    
    public String toString() {
        try {
            final ByteList buffer = new ByteList(20);
            buffer.append(this.twoAgo);
            buffer.append(this.oneAgo);
            buffer.append(new byte[] { 45, 62 });
            int i;
            for (i = 0; i < 20; ++i) {
                final int c = this.read();
                if (c == 0) {
                    --i;
                    break;
                }
                buffer.append(c);
            }
            while (i >= 0) {
                this.unread(buffer.charAt(i));
                ++i;
            }
            buffer.append(new byte[] { 32, 46, 46, 46 });
            return buffer.toString();
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public ByteList readUntil(final char marker) throws IOException {
        final ByteList list = new ByteList(20);
        int c;
        for (c = this.read(); c != marker && c != -1; c = this.read()) {
            list.append(c);
        }
        if (c == -1) {
            return null;
        }
        this.unread(c);
        return list;
    }
    
    public InputStream getRemainingAsStream() throws IOException {
        return this.bufferEntireStream(this.in);
    }
    
    private InputStream bufferEntireStream(final InputStream stream) throws IOException {
        byte[] allBytes = new byte[0];
        final byte[] b = new byte[1024];
        int bytesRead = 0;
        while ((bytesRead = stream.read(b)) != -1) {
            final byte[] newbuf = new byte[allBytes.length + bytesRead];
            System.arraycopy(allBytes, 0, newbuf, 0, allBytes.length);
            System.arraycopy(b, 0, newbuf, allBytes.length, bytesRead);
            allBytes = newbuf;
        }
        return new ByteArrayInputStream(allBytes);
    }
}
