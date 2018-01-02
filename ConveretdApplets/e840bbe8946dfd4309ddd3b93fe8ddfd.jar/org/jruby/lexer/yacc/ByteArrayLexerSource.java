// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.lexer.yacc;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.IOException;
import org.jruby.util.ByteList;
import java.util.List;

public class ByteArrayLexerSource extends LexerSource
{
    private Cursor readCursor;
    private Cursor mainCursor;
    private Cursor pushbackCursor;
    private final boolean captureSource;
    
    public ByteArrayLexerSource(final String sourceName, final byte[] in, final List<String> list, final int line, final boolean extraPositionInformation) {
        super(sourceName, list, line, extraPositionInformation);
        this.readCursor = new ByteArrayCursor(in);
        this.mainCursor = this.readCursor;
        this.pushbackCursor = new PushbackCursor(this.mainCursor, new ByteList(128));
        this.captureSource = (list != null);
    }
    
    public boolean matchMarker(final ByteList marker, final boolean indent, final boolean withNewline) throws IOException {
        int matchPos = 0;
        if (indent) {
            for (int c = this.readCursor.at(matchPos); c != -1 && Character.isWhitespace(c) && c != 10; c = this.readCursor.at(++matchPos)) {}
        }
        for (int i = 0; i < marker.length(); ++i) {
            if (this.readCursor.at(matchPos) != marker.get(i)) {
                return false;
            }
            ++matchPos;
        }
        if (withNewline) {
            int c = this.readCursor.at(matchPos++);
            if (c == 13) {
                c = this.readCursor.at(matchPos);
            }
            if (c != 10 && c != -1) {
                return false;
            }
        }
        for (int i = 0; i < matchPos; ++i) {
            this.readCursor.read();
        }
        return true;
    }
    
    public int read() {
        return this.readCursor.read();
    }
    
    public ByteList readUntil(final char marker) throws IOException {
        return this.readUntil(marker, true);
    }
    
    private ByteList readUntil(final char marker, final boolean nullIfEnd) throws IOException {
        final ByteList result = new ByteList(128);
        int c;
        while ((c = this.readCursor.read()) != marker && c != -1) {
            result.append(c);
        }
        if (nullIfEnd && c == -1) {
            return null;
        }
        return result;
    }
    
    public ByteList readLineBytes() throws IOException {
        return this.readUntil('\n', false);
    }
    
    public int skipUntil(final int marker) throws IOException {
        int c;
        do {
            c = this.readCursor.read();
        } while (c != marker && c != -1);
        return c;
    }
    
    public void unread(final int c) {
        if (c == -1) {
            return;
        }
        if (this.captureSource) {
            this.uncaptureFeature(c);
        }
        this.readCursor.unread(c);
    }
    
    public void unreadMany(final CharSequence line) {
        for (int i = line.length() - 1; i >= 0; --i) {
            this.unread(line.charAt(i));
        }
    }
    
    public boolean peek(final int c) throws IOException {
        return this.readCursor.at(0) == c;
    }
    
    public boolean lastWasBeginOfLine() {
        final int c = this.readCursor.at(-1);
        return c == 10 || c == -1;
    }
    
    public boolean wasBeginOfLine() {
        final int c = this.readCursor.at(-2);
        return c == 10 || (c == -1 && c != this.readCursor.at(-1));
    }
    
    public String getCurrentLine() {
        int lineOffset;
        int c;
        for (lineOffset = 0; (c = this.readCursor.at(lineOffset - 1)) != 10 && c != -1; --lineOffset) {}
        final String ptr = this.makePointer(-(lineOffset + 1));
        final StringBuilder lineBuilder = new StringBuilder();
        for (c = this.readCursor.at(lineOffset); c != 10 && c != -1; c = this.readCursor.at(++lineOffset)) {
            lineBuilder.append((char)c);
        }
        lineBuilder.append('\n').append(ptr);
        return lineBuilder.toString();
    }
    
    public InputStream getRemainingAsStream() {
        final ByteList buf = new ByteList(128);
        int c;
        while ((c = this.read()) != -1) {
            buf.append(c);
        }
        return new ByteArrayInputStream(buf.getUnsafeBytes(), 0, buf.length());
    }
    
    private int forward(int c) {
        if (c != -1) {
            ++this.offset;
            switch (c) {
                case 10: {
                    ++this.line;
                    break;
                }
                case 13: {
                    if ((c = this.read()) != 10) {
                        this.unread(c);
                        c = 10;
                        break;
                    }
                    if (this.captureSource) {
                        this.uncaptureFeature(c);
                        this.captureFeature(13);
                        break;
                    }
                    break;
                }
            }
        }
        if (this.captureSource) {
            this.captureFeature(c);
        }
        return c;
    }
    
    private void backward(final int c) {
        --this.offset;
        if (c == 10) {
            --this.line;
            if (this.readCursor.at(-1) == 13) {
                this.unread(13);
            }
        }
    }
    
    class ByteArrayCursor implements Cursor
    {
        private final byte[] region;
        private int index;
        
        public ByteArrayCursor(final byte[] region) {
            this.region = region;
            this.index = 0;
        }
        
        public int read() {
            if (this.index >= this.region.length) {
                return ByteArrayLexerSource.this.forward(-1);
            }
            return 0xFF & ByteArrayLexerSource.this.forward(this.region[this.index++]);
        }
        
        public void unread(final int c) {
            if (this.index > 0 && this.region[this.index - 1] == c) {
                --this.index;
                ByteArrayLexerSource.this.backward(c);
            }
            else {
                ByteArrayLexerSource.this.readCursor = ByteArrayLexerSource.this.pushbackCursor;
                ByteArrayLexerSource.this.pushbackCursor.unread(c);
            }
        }
        
        public int at(final int offset) {
            final int location = this.index + offset;
            if (location >= this.region.length || location < 0) {
                return -1;
            }
            return 0xFF & this.region[location];
        }
    }
    
    class PushbackCursor implements Cursor
    {
        private final Cursor parent;
        private final ByteList region;
        
        public PushbackCursor(final Cursor prev, final ByteList region) {
            this.parent = prev;
            this.region = region;
        }
        
        public int read() {
            final int index = this.region.length() - 1;
            if (index < 0) {
                ByteArrayLexerSource.this.readCursor = this.parent;
                return this.parent.read();
            }
            final int c = 0xFF & this.region.get(index);
            this.region.setRealSize(index);
            return ByteArrayLexerSource.this.forward(c);
        }
        
        public void unread(final int c) {
            this.region.append(c);
            ByteArrayLexerSource.this.backward(c);
        }
        
        public int at(final int offset) {
            if (offset < 0) {
                return this.parent.at(offset);
            }
            if (offset >= this.region.length()) {
                return this.parent.at(offset - this.region.length());
            }
            return 0xFF & this.region.get(this.region.length() - offset - 1);
        }
    }
    
    interface Cursor
    {
        int read();
        
        void unread(final int p0);
        
        int at(final int p0);
    }
}
