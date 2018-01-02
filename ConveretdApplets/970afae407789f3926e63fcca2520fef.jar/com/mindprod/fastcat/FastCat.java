// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.fastcat;

import java.io.File;
import java.util.Arrays;

public class FastCat
{
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 2009-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2010-02-13";
    private static final String VERSION_STRING = "1.3";
    private final String[] pieces;
    private int count;
    private int length;
    
    public FastCat() {
        this(20);
    }
    
    public FastCat(final int estNumberOfPieces) {
        this.count = 0;
        this.length = 0;
        this.pieces = new String[estNumberOfPieces];
    }
    
    public FastCat append(final String s) {
        if (this.count >= this.pieces.length) {
            this.overflow();
        }
        this.length += s.length();
        this.pieces[this.count++] = s;
        return this;
    }
    
    public FastCat append(final String... ss) {
        for (final String s : ss) {
            if (this.count >= this.pieces.length) {
                this.overflow();
            }
            this.length += s.length();
            this.pieces[this.count++] = s;
        }
        return this;
    }
    
    public FastCat append(final int i) {
        return this.append(Integer.toString(i));
    }
    
    public FastCat append(final char c) {
        if (this.count >= this.pieces.length) {
            this.overflow();
        }
        final String s = String.valueOf(c);
        this.length += s.length();
        this.pieces[this.count++] = s;
        return this;
    }
    
    public FastCat append(final Object o) {
        if (this.count >= this.pieces.length) {
            this.overflow();
        }
        final String s = o.toString();
        this.length += s.length();
        this.pieces[this.count++] = s;
        return this;
    }
    
    public FastCat append(final Object... oo) {
        for (final Object o : oo) {
            if (this.count >= this.pieces.length) {
                this.overflow();
            }
            final String s = o.toString();
            this.length += s.length();
            this.pieces[this.count++] = s;
        }
        return this;
    }
    
    public void clear() {
        Arrays.fill(this.pieces, 0, this.count, null);
        this.count = 0;
        this.length = 0;
    }
    
    public int length() {
        return this.length;
    }
    
    public String toString() {
        int offset = 0;
        final char[] buffer = new char[this.length];
        for (int i = 0; i < this.count; ++i) {
            final String piece = this.pieces[i];
            piece.getChars(0, piece.length(), buffer, offset);
            offset += piece.length();
        }
        return new String(buffer);
    }
    
    private void overflow() {
        final StackTraceElement e = new Throwable().getStackTrace()[2];
        throw new ArrayIndexOutOfBoundsException("FastCat estimate " + this.pieces.length + " is too low.\n" + e.getClassName() + "." + e.getMethodName() + " line:" + e.getLineNumber());
    }
    
    public static void main(final String[] args) {
        final FastCat sb = new FastCat(7);
        sb.append("Hello");
        sb.append(" ");
        sb.append("World. ");
        sb.append(new File("temp.txt"));
        sb.append(" ", "abc", "def");
        System.out.println(sb.toString());
    }
}
