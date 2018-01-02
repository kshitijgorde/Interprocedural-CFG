// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.util;

import java.io.IOException;
import java.io.Reader;
import java.io.InputStream;

class HTMLTokenizer extends JBStreamTokenizer
{
    static int HTML_TEXT;
    static int HTML_UNKNOWN;
    static int HTML_EOF;
    static int TAG_APPLET;
    static int TAG_applet;
    static int TAG_PARAM;
    static int TAG_param;
    static int TAG_FRAME;
    static int TAG_frame;
    static int TAG_ALIGN;
    static int TAG_align;
    static int TAG_A;
    static int TAG_a;
    static int TAG_BASE;
    static int TAG_base;
    String[] tags;
    boolean outsideTag;
    
    static {
        HTMLTokenizer.HTML_TEXT = -1;
        HTMLTokenizer.HTML_UNKNOWN = -2;
        HTMLTokenizer.HTML_EOF = -3;
        HTMLTokenizer.TAG_APPLET = 0;
        HTMLTokenizer.TAG_applet = 1;
        HTMLTokenizer.TAG_PARAM = 2;
        HTMLTokenizer.TAG_param = 3;
        HTMLTokenizer.TAG_FRAME = 4;
        HTMLTokenizer.TAG_frame = 5;
        HTMLTokenizer.TAG_ALIGN = 6;
        HTMLTokenizer.TAG_align = 7;
        HTMLTokenizer.TAG_A = 8;
        HTMLTokenizer.TAG_a = 9;
        HTMLTokenizer.TAG_BASE = 10;
        HTMLTokenizer.TAG_base = 11;
    }
    
    public HTMLTokenizer(final InputStream inputStream) {
        super(inputStream);
        this.tags = new String[] { "APPLET", "PARAM", "FRAME", "ALIGN", "A", "BASE" };
        this.outsideTag = true;
    }
    
    public HTMLTokenizer(final Reader reader) {
        super(reader);
        this.tags = new String[] { "APPLET", "PARAM", "FRAME", "ALIGN", "A", "BASE" };
        this.outsideTag = true;
        this.resetSyntax();
        this.wordChars(0, 255);
        this.ordinaryChars(60, 60);
        this.ordinaryChars(62, 62);
        this.outsideTag = true;
    }
    
    public int nextHTML() throws IOException {
        final int nextToken;
        switch (nextToken = this.nextToken()) {
            case -1: {
                return HTMLTokenizer.HTML_EOF;
            }
            case 60: {
                this.outsideTag = false;
                return this.nextHTML();
            }
            case 62: {
                this.outsideTag = true;
                return this.nextHTML();
            }
            case -3: {
                if (!this.outsideTag) {
                    return this.tagType();
                }
                return this.nextHTML();
            }
            default: {
                JBLogger.log("ERROR: unknown TT " + nextToken);
                return HTMLTokenizer.HTML_UNKNOWN;
            }
        }
    }
    
    protected boolean onlyWhiteSpace(final String s) {
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 != ' ' && char1 != '\t' && char1 != '\n' && char1 != '\r') {
                return false;
            }
        }
        return true;
    }
    
    protected int tagType() {
        int n = 0;
        int n2 = 0;
        final String sval = super.sval;
        if (sval.charAt(0) == '/') {
            ++n2;
            n = 1;
        }
        for (int i = 0; i < this.tags.length; ++i) {
            if (sval.regionMatches(true, n2, this.tags[i], 0, this.tags[i].length())) {
                return i * 2 + n;
            }
        }
        return HTMLTokenizer.HTML_UNKNOWN;
    }
}
