// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.d;

import java.io.InputStream;
import java.io.StreamTokenizer;

public final class a extends StreamTokenizer
{
    public a(final InputStream inputStream) {
        super(inputStream);
        (this = this).resetSyntax();
        this.wordChars(32, 126);
        this.ordinaryChar(60);
        this.ordinaryChar(62);
        this.ordinaryChar(47);
        this.ordinaryChar(61);
        this.ordinaryChar(63);
        this.quoteChar(34);
        this.whitespaceChars(32, 32);
        this.whitespaceChars(9, 9);
        this.whitespaceChars(10, 10);
        this.whitespaceChars(13, 13);
    }
    
    public final b a() {
        return new b(this);
    }
    
    public final int a(final int n) {
        final int nextToken;
        if ((nextToken = this.nextToken()) != n) {
            final String string = "Unexpected token found [" + nextToken + "]. ";
            String s = null;
            switch (n) {
                case -1: {
                    s = string + "Expecting TT_EOF";
                    break;
                }
                case -2: {
                    s = string + "Expecting TT_NUMBER";
                    break;
                }
                case -3: {
                    s = string + "Expecting TT_WORD";
                    break;
                }
                case 10: {
                    s = string + "Expecting TT_EOL";
                    break;
                }
                default: {
                    s = string + "Expecting [" + (char)n + "]";
                    break;
                }
            }
            throw new RuntimeException(s);
        }
        return nextToken;
    }
    
    public final int nextToken() {
        return super.nextToken();
    }
    
    public final boolean b(final int n) {
        final int nextToken = this.nextToken();
        this.pushBack();
        boolean b = false;
        if (n == nextToken) {
            b = true;
        }
        return b;
    }
    
    public final int b() {
        final int nextToken = this.nextToken();
        this.pushBack();
        return nextToken;
    }
    
    public final String c() {
        String s = "";
        int i = 0;
        while (i == 0) {
            final int nextToken;
            switch (nextToken = this.nextToken()) {
                case -3: {
                    if (s.equals("")) {
                        s = this.sval;
                        continue;
                    }
                    s = s + " " + this.sval;
                    continue;
                }
                case -2: {
                    if (s.equals("")) {
                        s = this.nval + "";
                        continue;
                    }
                    s = s + " " + this.nval;
                    continue;
                }
                case 60: {
                    i = 1;
                    this.pushBack();
                    continue;
                }
                case 62: {
                    continue;
                }
                case -1: {
                    i = 1;
                    continue;
                }
                case 34: {
                    if (s.equals("")) {
                        s = '\"' + this.sval + '\"';
                        continue;
                    }
                    s = s + ' ' + '\"' + this.sval + '\"';
                    continue;
                }
                default: {
                    if (s.equals("")) {
                        s = (char)nextToken + "";
                        continue;
                    }
                    s = s + " " + (char)nextToken;
                    continue;
                }
            }
        }
        return s;
    }
}
