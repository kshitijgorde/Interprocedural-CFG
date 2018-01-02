// 
// Decompiled by Procyon v0.5.30
// 

package com.revfad.search;

import java.io.IOException;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.Vector;

public final class SearchSieve
{
    private boolean hasMatched;
    private int patternIndex;
    private String patternString;
    private char[] pattern;
    private int patternLength;
    private boolean exactMatchesOnly;
    private char prevc;
    private char[] buffer;
    private int bufferLength;
    private boolean buffering;
    private char[] swapSpace;
    private static final String HIGH_SPACE_CHARS = "\u1680\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u200b\u202f\u3000\u2029\u2028";
    
    public SearchSieve(final String s) {
        this(s, false);
    }
    
    public SearchSieve(final SearchSieve searchSieve) {
        this(searchSieve.patternString, searchSieve.exactMatchesOnly);
    }
    
    public SearchSieve(final String s, final boolean b) {
        if (s == null) {
            throw new IllegalArgumentException("null patternString");
        }
        if (s.length() == 0) {
            throw new IllegalArgumentException("zero length patternString");
        }
        this.setPattern(s, b);
    }
    
    private final void setPattern(final String patternString, final boolean exactMatchesOnly) {
        this.exactMatchesOnly = exactMatchesOnly;
        this.patternString = patternString;
        this.pattern = patternString.toCharArray();
        this.patternLength = this.pattern.length;
        this.buffer = new char[this.patternLength];
        this.swapSpace = new char[this.patternLength];
        this.reset();
    }
    
    public final void reset() {
        this.patternIndex = 0;
        this.prevc = '\0';
        this.bufferLength = 0;
        this.buffering = false;
        this.hasMatched = false;
    }
    
    public static final SearchSieve[] parseQuery(final String s, final boolean b) {
        final Vector vector = new Vector<SearchSieve>();
        final StreamTokenizer streamTokenizer = new StreamTokenizer(new StringReader(s));
        streamTokenizer.resetSyntax();
        streamTokenizer.wordChars(33, 65535);
        streamTokenizer.whitespaceChars(0, 32);
        streamTokenizer.quoteChar(34);
        try {
            while (streamTokenizer.nextToken() != -1) {
                if (streamTokenizer.ttype == -3) {
                    final StringTokenizer stringTokenizer = new StringTokenizer(streamTokenizer.sval, "\u1680\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u200b\u202f\u3000\u2029\u2028");
                    while (stringTokenizer.hasMoreTokens()) {
                        vector.addElement(new SearchSieve(stringTokenizer.nextToken(), b));
                    }
                }
                else {
                    if (streamTokenizer.ttype != 34) {
                        throw new IllegalStateException("Unrecognized token type: " + streamTokenizer.ttype);
                    }
                    vector.addElement(new SearchSieve(streamTokenizer.sval, b));
                }
            }
        }
        catch (IOException ex) {
            throw new IllegalArgumentException(ex.toString());
        }
        final SearchSieve[] array = new SearchSieve[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public boolean getExactMatchesOnly() {
        return this.exactMatchesOnly;
    }
    
    public final String getPattern() {
        return this.patternString;
    }
    
    public final int getPatternLength() {
        return this.patternLength;
    }
    
    public final int scanAllLowerCase(final String s) {
        final char[] charArray = s.toCharArray();
        return this.scanAllLowerCase(charArray, 0, charArray.length);
    }
    
    public final int scanAllLowerCase(final char[] array, final int n, final int n2) {
        final int n3 = n + n2 - 1;
        int n4 = -1;
        int scanLowerCase = n - 1;
        int n5 = n2;
        do {
            scanLowerCase = this.scanLowerCase(array, scanLowerCase + 1, n5);
            if (scanLowerCase != -1 && n4 == -1) {
                n4 = scanLowerCase;
            }
            n5 = n3 - scanLowerCase;
        } while (scanLowerCase != -1 && scanLowerCase != n3);
        return n4;
    }
    
    public final int scanLowerCase(final String s) {
        final char[] charArray = s.toCharArray();
        return this.scanLowerCase(charArray, 0, charArray.length);
    }
    
    public final int scanLowerCase(final char[] array, final int n, final int n2) {
        for (int n3 = n + n2, i = n; i < n3; ++i) {
            if (this.addChar(Character.toLowerCase(array[i]))) {
                return i;
            }
        }
        return -1;
    }
    
    public final boolean addChar(final char prevc) {
        boolean b = false;
        boolean b2 = false;
        if (this.exactMatchesOnly) {
            if (this.patternIndex == 0) {
                if (prevc == this.pattern[0] && !Character.isLetter(this.prevc)) {
                    ++this.patternIndex;
                }
            }
            else if (this.patternIndex != this.patternLength) {
                if (prevc != this.pattern[this.patternIndex]) {
                    this.patternIndex = 0;
                    b2 = true;
                }
                else {
                    ++this.patternIndex;
                }
            }
            else {
                if (!Character.isLetter(prevc)) {
                    b = true;
                }
                this.patternIndex = 0;
                b2 = true;
            }
        }
        else {
            if (prevc != this.pattern[this.patternIndex]) {
                this.patternIndex = 0;
                b2 = true;
            }
            else {
                ++this.patternIndex;
            }
            if (this.patternIndex == this.patternLength) {
                this.patternIndex = 0;
                b2 = true;
                b = true;
            }
        }
        if (this.patternIndex > 1 && !this.buffering && prevc == this.pattern[0]) {
            if (!this.exactMatchesOnly || (this.exactMatchesOnly && !Character.isLetter(this.prevc))) {
                this.buffering = true;
                this.buffer[0] = prevc;
                this.bufferLength = 1;
            }
        }
        else if (this.buffering) {
            if (prevc != this.pattern[this.bufferLength]) {
                this.bufferLength = 0;
                this.buffering = false;
            }
            else {
                this.buffer[this.bufferLength] = prevc;
                ++this.bufferLength;
            }
        }
        if (b2 && this.bufferLength > 0) {
            this.eatTheBuffer();
        }
        this.prevc = prevc;
        this.hasMatched = (this.hasMatched || b);
        return b;
    }
    
    public boolean hasMatched() {
        return this.hasMatched;
    }
    
    public String toString() {
        return this.getPattern() + " " + this.getExactMatchesOnly();
    }
    
    public boolean equals(final Object o) {
        if (!(o instanceof SearchSieve)) {
            return false;
        }
        final SearchSieve searchSieve = (SearchSieve)o;
        return this.getPattern().equals(searchSieve.getPattern()) && this.getExactMatchesOnly() == searchSieve.getExactMatchesOnly();
    }
    
    public int hashCode() {
        return this.getPattern().hashCode() + (this.getExactMatchesOnly() ? 1 : 0);
    }
    
    private final void eatTheBuffer() {
        this.patternIndex = 0;
        System.arraycopy(this.buffer, 0, this.swapSpace, 0, this.bufferLength);
        final int bufferLength = this.bufferLength;
        this.bufferLength = 0;
        this.buffering = false;
        for (int i = 0; i < bufferLength; ++i) {
            this.addChar(this.swapSpace[i]);
        }
    }
}
