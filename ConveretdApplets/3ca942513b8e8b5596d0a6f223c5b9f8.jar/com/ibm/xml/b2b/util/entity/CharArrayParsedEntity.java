// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util.entity;

import com.ibm.xml.b2b.util.CharConversionError;
import com.ibm.xml.b2b.util.XMLCharacterProperties;
import com.ibm.xml.b2b.util.EncodingSupport;
import com.ibm.xml.b2b.util.XMLString;

final class CharArrayParsedEntity extends ParsedEntity
{
    private ParsedEntityFactory fEntityFactory;
    private int fEncodedCharLength;
    
    public CharArrayParsedEntity(final ParsedEntityFactory fEntityFactory) {
        this.fEntityFactory = fEntityFactory;
    }
    
    public void release() {
        this.fEntityFactory.releaseParsedEntity(this);
    }
    
    public void getContent(final XMLString xmlString) {
        xmlString.setValues(this);
    }
    
    public void setValues(final byte[] array, final int n, final int n2, final EncodingSupport encodingSupport) {
        throw new RuntimeException("CharArrayParsedEntity.setValues(byte[] ...)");
    }
    
    public int decodeInvalidCharacter() {
        return this.decodeUTF16Character();
    }
    
    public boolean skipValidCharacter() {
        if (XMLCharacterProperties.validChar(this.decodeUTF16Character())) {
            super.offset += this.fEncodedCharLength;
            return true;
        }
        return false;
    }
    
    public boolean skipInitialNameCharacter() {
        if (XMLCharacterProperties.initialNameChar(this.decodeUTF16Character())) {
            super.offset += this.fEncodedCharLength;
            return true;
        }
        return false;
    }
    
    public boolean skipNameCharacter() {
        if (XMLCharacterProperties.nameChar(this.decodeUTF16Character())) {
            super.offset += this.fEncodedCharLength;
            return true;
        }
        return false;
    }
    
    private int decodeUTF16Character() {
        if (super.offset == super.endOffset) {
            return 0;
        }
        int n = super.chars[super.offset];
        this.fEncodedCharLength = 1;
        if (n >= 55296 && n < 56320) {
            if (super.offset + 1 == super.endOffset) {
                CharConversionError.missingSecondHalfOfSurrogatePair();
            }
            final char c = super.chars[super.offset + 1];
            if (c >= '\udc00' && c < '\ue000') {
                n = 65536 + (n - 55296 << 10) + (c - '\udc00');
                this.fEncodedCharLength = 2;
            }
        }
        return n;
    }
}
