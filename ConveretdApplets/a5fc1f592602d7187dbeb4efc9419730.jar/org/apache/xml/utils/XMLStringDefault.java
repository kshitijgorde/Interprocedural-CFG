// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

import java.util.Locale;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ContentHandler;

public class XMLStringDefault implements XMLString
{
    private String m_str;
    
    public XMLStringDefault(final String str) {
        this.m_str = str;
    }
    
    public void dispatchCharactersEvents(final ContentHandler ch) throws SAXException {
    }
    
    public void dispatchAsComment(final LexicalHandler lh) throws SAXException {
    }
    
    public XMLString fixWhiteSpace(final boolean trimHead, final boolean trimTail, final boolean doublePunctuationSpaces) {
        return new XMLStringDefault(this.m_str.trim());
    }
    
    public int length() {
        return this.m_str.length();
    }
    
    public char charAt(final int index) {
        return this.m_str.charAt(index);
    }
    
    public void getChars(final int srcBegin, final int srcEnd, final char[] dst, final int dstBegin) {
        int destIndex = dstBegin;
        for (int i = srcBegin; i < srcEnd; ++i) {
            dst[destIndex++] = this.m_str.charAt(i);
        }
    }
    
    public boolean equals(final XMLString anObject) {
        return this.m_str.equals(anObject.toString());
    }
    
    public boolean equals(final Object anObject) {
        return this.m_str.equals(anObject);
    }
    
    public boolean equalsIgnoreCase(final String anotherString) {
        return this.m_str.equalsIgnoreCase(anotherString);
    }
    
    public int compareTo(final XMLString anotherString) {
        return this.m_str.compareTo(anotherString.toString());
    }
    
    public int compareToIgnoreCase(final XMLString str) {
        return this.m_str.compareToIgnoreCase(str.toString());
    }
    
    public boolean startsWith(final String prefix, final int toffset) {
        return this.m_str.startsWith(prefix, toffset);
    }
    
    public boolean startsWith(final XMLString prefix, final int toffset) {
        return this.m_str.startsWith(prefix.toString(), toffset);
    }
    
    public boolean startsWith(final String prefix) {
        return this.m_str.startsWith(prefix);
    }
    
    public boolean startsWith(final XMLString prefix) {
        return this.m_str.startsWith(prefix.toString());
    }
    
    public boolean endsWith(final String suffix) {
        return this.m_str.endsWith(suffix);
    }
    
    public int hashCode() {
        return this.m_str.hashCode();
    }
    
    public int indexOf(final int ch) {
        return this.m_str.indexOf(ch);
    }
    
    public int indexOf(final int ch, final int fromIndex) {
        return this.m_str.indexOf(ch, fromIndex);
    }
    
    public int lastIndexOf(final int ch) {
        return this.m_str.lastIndexOf(ch);
    }
    
    public int lastIndexOf(final int ch, final int fromIndex) {
        return this.m_str.lastIndexOf(ch, fromIndex);
    }
    
    public int indexOf(final String str) {
        return this.m_str.indexOf(str);
    }
    
    public int indexOf(final XMLString str) {
        return this.m_str.indexOf(str.toString());
    }
    
    public int indexOf(final String str, final int fromIndex) {
        return this.m_str.indexOf(str, fromIndex);
    }
    
    public int lastIndexOf(final String str) {
        return this.m_str.lastIndexOf(str);
    }
    
    public int lastIndexOf(final String str, final int fromIndex) {
        return this.m_str.lastIndexOf(str, fromIndex);
    }
    
    public XMLString substring(final int beginIndex) {
        return new XMLStringDefault(this.m_str.substring(beginIndex));
    }
    
    public XMLString substring(final int beginIndex, final int endIndex) {
        return new XMLStringDefault(this.m_str.substring(beginIndex, endIndex));
    }
    
    public XMLString concat(final String str) {
        return new XMLStringDefault(this.m_str.concat(str));
    }
    
    public XMLString toLowerCase(final Locale locale) {
        return new XMLStringDefault(this.m_str.toLowerCase(locale));
    }
    
    public XMLString toLowerCase() {
        return new XMLStringDefault(this.m_str.toLowerCase());
    }
    
    public XMLString toUpperCase(final Locale locale) {
        return new XMLStringDefault(this.m_str.toUpperCase(locale));
    }
    
    public XMLString toUpperCase() {
        return new XMLStringDefault(this.m_str.toUpperCase());
    }
    
    public XMLString trim() {
        return new XMLStringDefault(this.m_str.trim());
    }
    
    public String toString() {
        return this.m_str;
    }
    
    public boolean hasString() {
        return true;
    }
    
    public double toDouble() {
        try {
            return Double.valueOf(this.m_str);
        }
        catch (NumberFormatException nfe) {
            return Double.NaN;
        }
    }
}
