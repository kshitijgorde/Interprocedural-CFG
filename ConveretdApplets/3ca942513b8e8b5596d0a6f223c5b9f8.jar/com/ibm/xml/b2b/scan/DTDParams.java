// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan;

import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.QName;

public final class DTDParams
{
    private static final int QNAME1_ACTIVE = 1;
    private static final int QNAME2_ACTIVE = 2;
    private static final int QNAME3_ACTIVE = 4;
    private static final int NAME1_ACTIVE = 8;
    private static final int NAME2_ACTIVE = 16;
    private static final int NAME3_ACTIVE = 32;
    private static final int STRING1_ACTIVE = 64;
    private static final int STRING2_ACTIVE = 128;
    private static final int STRING3_ACTIVE = 256;
    private QName fQName1;
    private QName fQName2;
    private QName fQName3;
    private XMLName fName1;
    private XMLName fName2;
    private XMLName fName3;
    private XMLString fString1;
    private XMLString fString2;
    private XMLString fString3;
    private int fDepth;
    private int[] fActiveStack;
    private ParsedEntity[] fParsedEntityStack;
    
    private final boolean active(final int n) {
        return (this.fActiveStack[this.fDepth] & n) != 0x0;
    }
    
    private final void setActive(final int n) {
        final int[] fActiveStack = this.fActiveStack;
        final int fDepth = this.fDepth;
        fActiveStack[fDepth] |= n;
    }
    
    private final void setIdle(final int n) {
        final int[] fActiveStack = this.fActiveStack;
        final int fDepth = this.fDepth;
        fActiveStack[fDepth] &= ~n;
    }
    
    public DTDParams() {
        this.fQName1 = new QName();
        this.fQName2 = new QName();
        this.fQName3 = new QName();
        this.fName1 = new XMLName();
        this.fName2 = new XMLName();
        this.fName3 = new XMLName();
        this.fString1 = new XMLString();
        this.fString2 = new XMLString();
        this.fString3 = new XMLString();
        this.fDepth = 0;
        this.fActiveStack = new int[4];
        this.fParsedEntityStack = new ParsedEntity[4];
    }
    
    public void reset() {
        if (this.fDepth > 0) {
            this.fDepth = 0;
            this.setActive(511);
        }
        if (this.active(1)) {
            this.fQName1.clear();
        }
        if (this.active(2)) {
            this.fQName2.clear();
        }
        if (this.active(4)) {
            this.fQName3.clear();
        }
        if (this.active(8)) {
            this.fName1.clear();
        }
        if (this.active(16)) {
            this.fName2.clear();
        }
        if (this.active(32)) {
            this.fName3.clear();
        }
        if (this.active(64)) {
            this.fString1.clear();
        }
        if (this.active(128)) {
            this.fString2.clear();
        }
        if (this.active(256)) {
            this.fString3.clear();
        }
        this.fActiveStack[this.fDepth] = 0;
    }
    
    public void push(final ParsedEntity parsedEntity) {
        if (++this.fDepth == this.fActiveStack.length) {
            final int[] fActiveStack = new int[this.fDepth << 1];
            System.arraycopy(this.fActiveStack, 0, fActiveStack, 0, this.fDepth);
            this.fActiveStack = fActiveStack;
            final ParsedEntity[] fParsedEntityStack = new ParsedEntity[this.fDepth << 1];
            System.arraycopy(this.fParsedEntityStack, 0, fParsedEntityStack, 0, this.fDepth);
            this.fParsedEntityStack = fParsedEntityStack;
        }
        this.fActiveStack[this.fDepth] = 0;
        this.fParsedEntityStack[this.fDepth] = parsedEntity;
    }
    
    public void pop() {
        --this.fDepth;
    }
    
    private static void busy() {
        throw new RuntimeException("DTDParams#busy()");
    }
    
    public QName getRootElementType() {
        if (!this.active(1)) {
            this.fQName1.setValues(this.fParsedEntityStack[this.fDepth]);
            this.setActive(1);
        }
        else {
            busy();
        }
        return this.fQName1;
    }
    
    public QName rootElementType() {
        return this.fQName1;
    }
    
    public void resetRootElementType() {
        this.setIdle(1);
        this.fQName1.clear();
    }
    
    public XMLString getPublicID() {
        if (!this.active(64)) {
            this.fString1.setValues(this.fParsedEntityStack[this.fDepth]);
            this.setActive(64);
        }
        else {
            busy();
        }
        return this.fString1;
    }
    
    public void resetPublicID() {
        this.setIdle(64);
        this.fString1.clear();
    }
    
    public XMLString getSystemID() {
        if (!this.active(128)) {
            this.fString2.setValues(this.fParsedEntityStack[this.fDepth]);
            this.setActive(128);
        }
        else {
            busy();
        }
        return this.fString2;
    }
    
    public void resetSystemID() {
        this.setIdle(128);
        this.fString2.clear();
    }
    
    public XMLName getPEName() {
        if (!this.active(8)) {
            this.fName1.setValues(this.fParsedEntityStack[this.fDepth]);
            this.setActive(8);
        }
        else {
            busy();
        }
        return this.fName1;
    }
    
    public void resetPEName() {
        this.setIdle(8);
        this.fName1.clear();
    }
    
    public XMLName getEntityReferenceName() {
        if (!this.active(16)) {
            this.fName2.setValues(this.fParsedEntityStack[this.fDepth]);
            this.setActive(16);
        }
        else {
            busy();
        }
        return this.fName2;
    }
    
    public void resetEntityReferenceName() {
        this.setIdle(16);
        this.fName2.clear();
    }
    
    public XMLName getPEReferenceName() {
        if (!this.active(32)) {
            this.fName3.setValues(this.fParsedEntityStack[this.fDepth]);
            this.setActive(32);
        }
        else {
            busy();
        }
        return this.fName3;
    }
    
    public void resetPEReferenceName() {
        this.setIdle(32);
        this.fName3.clear();
    }
    
    public XMLString getAttValueCharacters() {
        if (!this.active(256)) {
            this.fString3.setValues(this.fParsedEntityStack[this.fDepth]);
            this.setActive(256);
        }
        else {
            busy();
        }
        return this.fString3;
    }
    
    public void resetAttValueCharacters() {
        this.setIdle(256);
        this.fString3.clear();
    }
    
    public XMLString getEntityValueCharacters() {
        if (!this.active(64)) {
            this.fString1.setValues(this.fParsedEntityStack[this.fDepth]);
            this.setActive(64);
        }
        else {
            busy();
        }
        return this.fString1;
    }
    
    public void resetEntityValueCharacters() {
        this.setIdle(64);
        this.fString1.clear();
    }
    
    public XMLString getContent() {
        if (!this.active(64)) {
            this.fString1.setValues(this.fParsedEntityStack[this.fDepth]);
            this.setActive(64);
        }
        else {
            busy();
        }
        return this.fString1;
    }
    
    public void resetContent() {
        this.setIdle(64);
        this.fString1.clear();
    }
    
    public XMLName getPITarget() {
        if (!this.active(8)) {
            this.fName1.setValues(this.fParsedEntityStack[this.fDepth]);
            this.setActive(8);
        }
        else {
            busy();
        }
        return this.fName1;
    }
    
    public void resetPITarget() {
        this.setIdle(8);
        this.fName1.clear();
    }
    
    public QName getElementType() {
        if (!this.active(2)) {
            this.fQName2.setValues(this.fParsedEntityStack[this.fDepth]);
            this.setActive(2);
        }
        else {
            busy();
        }
        return this.fQName2;
    }
    
    public QName elementType() {
        return this.fQName2;
    }
    
    public void resetElementType() {
        this.setIdle(2);
        this.fQName2.clear();
    }
    
    public QName getContentModelElement() {
        if (!this.active(4)) {
            this.fQName3.setValues(this.fParsedEntityStack[this.fDepth]);
            this.setActive(4);
        }
        else {
            busy();
        }
        return this.fQName3;
    }
    
    public void resetContentModelElement() {
        this.setIdle(4);
        this.fQName3.clear();
    }
    
    public QName getAttributeName() {
        if (!this.active(4)) {
            this.fQName3.setValues(this.fParsedEntityStack[this.fDepth]);
            this.setActive(4);
        }
        else {
            busy();
        }
        return this.fQName3;
    }
    
    public void resetAttributeName() {
        this.setIdle(4);
        this.fQName3.clear();
    }
    
    public XMLString getAttributeType() {
        if (!this.active(64)) {
            this.fString1.setValues(this.fParsedEntityStack[this.fDepth]);
            this.setActive(64);
        }
        else {
            busy();
        }
        return this.fString1;
    }
    
    public XMLString attributeType() {
        return this.fString1;
    }
    
    public void resetAttributeType() {
        this.setIdle(64);
        this.fString1.clear();
    }
    
    public XMLString getAttributeDefaultType() {
        if (!this.active(128)) {
            this.fString2.setValues(this.fParsedEntityStack[this.fDepth]);
            this.setActive(128);
        }
        else {
            busy();
        }
        return this.fString2;
    }
    
    public void resetAttributeDefaultType() {
        this.setIdle(128);
        this.fString2.clear();
    }
    
    public XMLName getEnumerationTypeName() {
        if (!this.active(8)) {
            this.fName1.setValues(this.fParsedEntityStack[this.fDepth]);
            this.setActive(8);
        }
        else {
            busy();
        }
        return this.fName1;
    }
    
    public void resetEnumerationTypeName() {
        this.setIdle(8);
        this.fName1.clear();
    }
    
    public XMLString getEnumerationTypeToken() {
        if (!this.active(256)) {
            this.fString3.setValues(this.fParsedEntityStack[this.fDepth]);
            this.setActive(256);
        }
        else {
            busy();
        }
        return this.fString3;
    }
    
    public void resetEnumerationTypeToken() {
        this.setIdle(256);
        this.fString3.clear();
    }
    
    public XMLName getEntityDeclName() {
        if (!this.active(8)) {
            this.fName1.setValues(this.fParsedEntityStack[this.fDepth]);
            this.setActive(8);
        }
        else {
            busy();
        }
        return this.fName1;
    }
    
    public void resetEntityDeclName() {
        this.setIdle(8);
        this.fName1.clear();
    }
    
    public XMLName getNotationName() {
        if (!this.active(16)) {
            this.fName2.setValues(this.fParsedEntityStack[this.fDepth]);
            this.setActive(16);
        }
        else {
            busy();
        }
        return this.fName2;
    }
    
    public void resetNotationName() {
        this.setIdle(16);
        this.fName2.clear();
    }
}
