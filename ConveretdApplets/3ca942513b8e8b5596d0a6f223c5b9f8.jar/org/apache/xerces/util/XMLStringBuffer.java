// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.util;

import org.apache.xerces.xni.XMLString;

public class XMLStringBuffer extends XMLString
{
    public static final int DEFAULT_SIZE = 32;
    
    public XMLStringBuffer() {
        this(32);
    }
    
    public XMLStringBuffer(final int n) {
        super.ch = new char[n];
    }
    
    public XMLStringBuffer(final char c) {
        this(1);
        this.append(c);
    }
    
    public XMLStringBuffer(final String s) {
        this(s.length());
        this.append(s);
    }
    
    public XMLStringBuffer(final char[] array, final int n, final int n2) {
        this(n2);
        this.append(array, n, n2);
    }
    
    public XMLStringBuffer(final XMLString xmlString) {
        this(xmlString.length);
        this.append(xmlString);
    }
    
    public void clear() {
        super.offset = 0;
        super.length = 0;
    }
    
    public void append(final char c) {
        if (super.length + 1 > super.ch.length) {
            int n = super.ch.length * 2;
            if (n < super.ch.length + 32) {
                n = super.ch.length + 32;
            }
            final char[] ch = new char[n];
            System.arraycopy(super.ch, 0, ch, 0, super.length);
            super.ch = ch;
        }
        super.ch[super.length] = c;
        ++super.length;
    }
    
    public void append(final String s) {
        final int length = s.length();
        if (super.length + length > super.ch.length) {
            int n = super.ch.length * 2;
            if (n < super.length + length + 32) {
                n = super.ch.length + length + 32;
            }
            final char[] ch = new char[n];
            System.arraycopy(super.ch, 0, ch, 0, super.length);
            super.ch = ch;
        }
        s.getChars(0, length, super.ch, super.length);
        super.length += length;
    }
    
    public void append(final char[] array, final int n, final int n2) {
        if (super.length + n2 > super.ch.length) {
            final char[] ch = new char[super.ch.length + n2 + 32];
            System.arraycopy(super.ch, 0, ch, 0, super.length);
            super.ch = ch;
        }
        System.arraycopy(array, n, super.ch, super.length, n2);
        super.length += n2;
    }
    
    public void append(final XMLString xmlString) {
        this.append(xmlString.ch, xmlString.offset, xmlString.length);
    }
}
