// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util.entity;

import com.ibm.xml.b2b.util.XMLCharacterProperties;
import com.ibm.xml.b2b.util.XMLString;

final class ByteArrayParsedEntity extends ParsedEntity
{
    private ParsedEntityFactory fEntityFactory;
    private int[] fEncodedCharLength;
    
    public ByteArrayParsedEntity(final ParsedEntityFactory fEntityFactory) {
        this.fEntityFactory = fEntityFactory;
        this.fEncodedCharLength = new int[1];
    }
    
    public void release() {
        this.fEntityFactory.releaseParsedEntity(this);
    }
    
    public void getContent(final XMLString xmlString) {
        xmlString.setValues(this);
    }
    
    public void setValues(final char[] array, final int n, final int n2) {
        throw new RuntimeException("ByteArrayParsedEntity.setValues(char[] ...)");
    }
    
    private int decodeCharacter() {
        return super.encoding.decodeCharacter(super.bytes, super.offset, super.endOffset, this.fEncodedCharLength);
    }
    
    public int decodeInvalidCharacter() {
        return this.decodeCharacter();
    }
    
    public boolean skipValidCharacter() {
        if (super.offset < super.endOffset && XMLCharacterProperties.validChar(this.decodeCharacter())) {
            super.offset += this.fEncodedCharLength[0];
            return true;
        }
        return false;
    }
    
    public boolean skipInitialNameCharacter() {
        if (XMLCharacterProperties.initialNameChar(this.decodeCharacter())) {
            super.offset += this.fEncodedCharLength[0];
            return true;
        }
        return false;
    }
    
    public boolean skipNameCharacter() {
        if (XMLCharacterProperties.nameChar(this.decodeCharacter())) {
            super.offset += this.fEncodedCharLength[0];
            return true;
        }
        return false;
    }
}
