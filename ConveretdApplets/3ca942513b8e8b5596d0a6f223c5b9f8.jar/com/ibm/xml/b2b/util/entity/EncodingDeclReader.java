// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util.entity;

import com.ibm.xml.b2b.util.IOExceptionWrapper;
import java.io.IOException;
import java.io.InputStream;

abstract class EncodingDeclReader
{
    protected static final int CODEPOINT_ILLEGAL = 0;
    protected static final int CODEPOINT_WHITESPACE = 1;
    protected static final int CODEPOINT_EQUALSIGN = 2;
    protected static final int CODEPOINT_SINGLEQUOTE = 3;
    protected static final int CODEPOINT_DOUBLEQUOTE = 4;
    protected static final int CODEPOINT_LETTER = 5;
    protected static final int CODEPOINT_DIGIT = 6;
    protected static final int CODEPOINT_HYPHEN = 7;
    protected static final int CODEPOINT_UNDERSCORE = 8;
    protected static final int CODEPOINT_PERIOD = 9;
    protected static final int CODEPOINT_LESSTHAN = 10;
    protected static final int CODEPOINT_GREATERTHAN = 11;
    protected static final int CODEPOINT_QUESTIONMARK = 12;
    protected static final int LITERAL_STARTPIXML = 0;
    protected static final int LITERAL_VERSION = 1;
    protected static final int LITERAL_VERSION10 = 2;
    protected static final int LITERAL_ENCODING = 3;
    protected static final int LITERAL_STANDALONE = 4;
    protected static final int LITERAL_YES = 5;
    protected static final int LITERAL_NO = 6;
    protected static final int LITERAL_ENDPI = 7;
    protected InputStream fStream;
    protected String fEncodingName;
    protected char[] fEncodingNameBuffer;
    private static final int STATE_START = 0;
    private static final int STATE_VERSION = 1;
    private static final int STATE_ENCODING = 2;
    private static final int STATE_STANDALONE = 3;
    private static final int STATE_FINISHED = 4;
    
    protected abstract boolean lookingAtCodePoint(final int p0) throws IOException;
    
    protected abstract boolean skipCodePoint(final int p0) throws IOException;
    
    protected abstract boolean skipLiteral(final int p0) throws IOException;
    
    protected abstract boolean skipSpaces() throws IOException;
    
    protected abstract boolean scanEncodingName(final int p0) throws IOException;
    
    public String getEncoding(final InputStream fStream, final boolean b) {
        this.fStream = fStream;
        this.fEncodingName = null;
        try {
            if (!this.readEncodingDecl(b)) {
                return null;
            }
        }
        catch (IOException ex) {
            throw new IOExceptionWrapper(ex);
        }
        return this.fEncodingName;
    }
    
    private boolean readEncodingDecl(final boolean b) throws IOException {
        int i = 0;
        if (!this.skipLiteral(0)) {
            return true;
        }
        this.skipSpaces();
        do {
            if (!b) {
                if (i == 0 && this.skipLiteral(1)) {
                    i = 1;
                }
                else {
                    if (!this.skipLiteral(3)) {
                        return false;
                    }
                    i = 2;
                }
            }
            else if (i == 0) {
                if (!this.skipLiteral(1)) {
                    return false;
                }
                i = 1;
            }
            else if (i != 1 || !this.skipLiteral(3)) {
                if (!this.skipLiteral(4)) {
                    break;
                }
                i = 3;
            }
            else {
                i = 2;
            }
            this.skipSpaces();
            if (!this.skipCodePoint(2)) {
                return false;
            }
            this.skipSpaces();
            final boolean skipCodePoint;
            if (!(skipCodePoint = this.skipCodePoint(3)) && !this.skipCodePoint(4)) {
                return false;
            }
            final int n = skipCodePoint ? 3 : 4;
            switch (i) {
                case 1: {
                    if (!this.skipLiteral(2) || !this.skipCodePoint(n)) {
                        return false;
                    }
                    if (this.skipSpaces()) {
                        continue;
                    }
                    if (!b) {
                        return false;
                    }
                    i = 4;
                    continue;
                }
                case 2: {
                    if (!this.scanEncodingName(n)) {
                        return false;
                    }
                    this.skipCodePoint(n);
                    if (!this.skipSpaces()) {
                        i = 4;
                        continue;
                    }
                    if (!b) {
                        i = 4;
                        continue;
                    }
                    continue;
                }
                case 3: {
                    if ((!this.skipLiteral(5) && !this.skipLiteral(6)) || !this.skipCodePoint(n)) {
                        return false;
                    }
                    this.skipSpaces();
                    i = 4;
                    continue;
                }
                default: {
                    continue;
                }
            }
        } while (i != 4);
        return this.skipLiteral(7);
    }
}
