// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.util;

import org.apache.xerces.xni.XMLString;

public class XMLStringBuffer extends XMLString
{
    public static final int DEFAULT_SIZE = 32;
    private char[] fOneCharBuffer;
    
    public XMLStringBuffer() {
        this(32);
    }
    
    public XMLStringBuffer(final int size) {
        this.fOneCharBuffer = new char[1];
        super.ch = new char[size];
    }
    
    public XMLStringBuffer(final char c) {
        this(1);
        this.append(c);
    }
    
    public XMLStringBuffer(final String s) {
        this(s.length());
        this.append(s);
    }
    
    public XMLStringBuffer(final char[] ch, final int offset, final int length) {
        this(length);
        this.append(ch, offset, length);
    }
    
    public XMLStringBuffer(final XMLString s) {
        this(s.length);
        this.append(s);
    }
    
    public void clear() {
        super.offset = 0;
        super.length = 0;
    }
    
    public void append(final char c) {
        this.fOneCharBuffer[0] = c;
        this.append(this.fOneCharBuffer, 0, 1);
    }
    
    public void append(final String s) {
        final int length = s.length();
        if (super.length + length > super.ch.length) {
            final char[] newch = new char[super.ch.length + length + 32];
            System.arraycopy(super.ch, 0, newch, 0, super.length);
            super.ch = newch;
        }
        s.getChars(0, length, super.ch, super.length);
        super.length += length;
    }
    
    public void append(final char[] ch, final int offset, final int length) {
        if (super.length + length > super.ch.length) {
            final char[] newch = new char[super.ch.length + length + 32];
            System.arraycopy(super.ch, 0, newch, 0, super.length);
            super.ch = newch;
        }
        System.arraycopy(ch, offset, super.ch, super.length, length);
        super.length += length;
    }
    
    public void append(final XMLString s) {
        this.append(s.ch, s.offset, s.length);
    }
}
