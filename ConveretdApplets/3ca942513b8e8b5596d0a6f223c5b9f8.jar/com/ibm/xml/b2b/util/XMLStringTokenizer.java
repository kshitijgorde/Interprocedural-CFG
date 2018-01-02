// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util;

public final class XMLStringTokenizer extends XMLString
{
    private int fStartOffset;
    
    public void setValues(final XMLString values) {
        super.setValues(values);
        if (super.encoding != null && !super.encoding.isASCIITransparent()) {
            this.convertToChars();
            this.setValues(super.fConvertedChars[0], 0, super.fConvertedCharsOffset[0]);
        }
        this.fStartOffset = super.offset;
    }
    
    public boolean hasMoreTokens() {
        if (this.fStartOffset == super.endOffset) {
            return false;
        }
        if (super.chars == null) {
            while (super.bytes[this.fStartOffset] <= 32) {
                if (++this.fStartOffset == super.endOffset) {
                    return false;
                }
            }
        }
        else {
            while (super.chars[this.fStartOffset] <= ' ') {
                if (++this.fStartOffset == super.endOffset) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void nextToken(final XMLString xmlString) {
        final int fStartOffset = this.fStartOffset;
        if (super.chars == null) {
            while (super.bytes[this.fStartOffset] > 32 && ++this.fStartOffset != super.endOffset) {}
            xmlString.setValues(super.bytes, fStartOffset, this.fStartOffset, super.encoding);
        }
        else {
            while (super.chars[this.fStartOffset] <= ' ' && ++this.fStartOffset != super.endOffset) {}
            xmlString.setValues(super.chars, fStartOffset, this.fStartOffset);
        }
    }
}
