// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.latin;

import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.QName;
import com.ibm.xml.b2b.scan.MarkupDeclHandler;
import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.DTDParams;
import com.ibm.xml.b2b.scan.ExternalSubsetHandler;

public final class LatinExternalSubsetScanner extends LatinMarkupDeclScanner
{
    private static final int ENTITYSTATE_DTD_EXTERNAL_SUBSET = 0;
    private static final int ENTITYSTATE_PE_BETWEEN_MARKUP = 1;
    private static final int ENTITYSTATE_PE_WITHIN_MARKUP = 2;
    private ExternalSubsetHandler fHandler;
    private DTDParams fDTDParams;
    private ParsedEntity fEntityContent;
    private byte[] fData;
    private int fCurrentOffset;
    private int fEntityDepth;
    private DTDParams[] fDTDParamsStack;
    private ParsedEntity[] fEntityContentStack;
    private byte[][] fDataStack;
    private int[] fCurrentOffsetStack;
    private int fEntityState;
    private int fIncludeSectDepth;
    private int fMarkupDepth;
    private int[] fEntityStateStack;
    
    public LatinExternalSubsetScanner(final ExternalSubsetHandler fHandler) {
        this.fHandler = fHandler;
        this.fCurrentOffset = -1;
        this.fEntityState = -1;
        this.fDTDParamsStack = new DTDParams[4];
        this.fEntityContentStack = new ParsedEntity[4];
        this.fDataStack = new byte[4][];
        this.fCurrentOffsetStack = new int[4];
        this.fEntityStateStack = new int[4];
    }
    
    public void reset(final boolean b) {
        this.fIncludeSectDepth = 0;
        this.fMarkupDepth = 0;
        this.fEntityDepth = 0;
    }
    
    public boolean scanExternalSubset(final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        this.setupContext(dtdParams, parsedEntity, 0);
        final boolean scanExtSubsetDecl = this.scanExtSubsetDecl();
        this.endExternalSubset();
        return scanExtSubsetDecl;
    }
    
    public boolean scanExtSubsetDecl(final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        this.setupContext(dtdParams, parsedEntity, 1);
        final boolean scanExtSubsetDecl = this.scanExtSubsetDecl();
        this.endPEReferenceBetweenMarkup();
        return scanExtSubsetDecl;
    }
    
    public boolean scanPEWithinMarkup(final DTDParams dtdParams, final ParsedEntity parsedEntity) {
        this.setupContext(dtdParams, parsedEntity, 2);
        return true;
    }
    
    private void setupContext(final DTDParams fdtdParams, final ParsedEntity fEntityContent, final int fEntityState) {
        if (this.fEntityDepth > 0) {
            final int n = this.fEntityDepth - 1;
            if (n == this.fDTDParamsStack.length) {
                this.growContext();
            }
            if (this.fEntityContent.bytes != this.fData || this.fEntityContent.offset != this.fCurrentOffset) {
                throw new RuntimeException("LatinExternalSubsetScanner#setupContext()");
            }
            this.fDTDParamsStack[n] = this.fDTDParams;
            this.fEntityContentStack[n] = this.fEntityContent;
            this.fDataStack[n] = this.fData;
            this.fCurrentOffsetStack[n] = this.fCurrentOffset;
            this.fEntityStateStack[n] = this.fEntityState;
        }
        this.fDTDParams = fdtdParams;
        this.fEntityContent = fEntityContent;
        this.fData = fEntityContent.bytes;
        this.fCurrentOffset = fEntityContent.offset;
        this.fEntityState = fEntityState;
        ++this.fEntityDepth;
    }
    
    private boolean scanExtSubsetDecl() {
        final int fEntityDepth = this.fEntityDepth;
        while (true) {
            byte b;
            for (b = this.fData[this.fCurrentOffset]; b == 32 || b == 10 || b == 9 || b == 13; b = this.fData[++this.fCurrentOffset]) {}
            if (b == 60) {
                ++this.fCurrentOffset;
                ++this.fMarkupDepth;
                if (!this.scanMarkupDecl()) {
                    return false;
                }
                --this.fMarkupDepth;
            }
            else if (b == 37) {
                final XMLName peName = this.fDTDParams.getPEName();
                peName.offset = ++this.fCurrentOffset;
                while (b != 59) {
                    b = this.fData[++this.fCurrentOffset];
                }
                peName.endOffset = this.fCurrentOffset;
                this.fEntityContent.offset = ++this.fCurrentOffset;
                this.fHandler.externalSubsetPEReference(peName);
                this.fCurrentOffset = this.fEntityContent.offset;
                this.fDTDParams.resetPEName();
            }
            else if (b == 93) {
                this.fCurrentOffset += 3;
                --this.fIncludeSectDepth;
            }
            else {
                this.fEntityContent.offset = this.fCurrentOffset;
                if (this.fEntityDepth == fEntityDepth) {
                    return true;
                }
                this.endOfContentWithinMarkup();
                this.fDTDParams.resetPEReferenceName();
            }
        }
    }
    
    private boolean scanMarkupDecl() {
        if (this.fData[this.fCurrentOffset] != 33) {
            this.fEntityContent.offset = ++this.fCurrentOffset;
            this.fCurrentOffset = LatinMarkupDeclScanner.scanPI(this.fHandler, this.fDTDParams, this.fEntityContent);
            return true;
        }
        final byte b = this.fData[++this.fCurrentOffset];
        if (b == 69 && this.fData[this.fCurrentOffset + 1] == 76 && this.fData[this.fCurrentOffset + 2] == 69 && this.fData[this.fCurrentOffset + 3] == 77 && this.fData[this.fCurrentOffset + 4] == 69 && this.fData[this.fCurrentOffset + 5] == 78 && this.fData[this.fCurrentOffset + 6] == 84) {
            this.fCurrentOffset += 7;
            this.scanElementDecl();
            return true;
        }
        if (b == 65 && this.fData[this.fCurrentOffset + 1] == 84 && this.fData[this.fCurrentOffset + 2] == 84 && this.fData[this.fCurrentOffset + 3] == 76 && this.fData[this.fCurrentOffset + 4] == 73 && this.fData[this.fCurrentOffset + 5] == 83 && this.fData[this.fCurrentOffset + 6] == 84) {
            this.fCurrentOffset += 7;
            return this.scanAttlistDecl();
        }
        if (b == 69 && this.fData[this.fCurrentOffset + 1] == 78 && this.fData[this.fCurrentOffset + 2] == 84 && this.fData[this.fCurrentOffset + 3] == 73 && this.fData[this.fCurrentOffset + 4] == 84 && this.fData[this.fCurrentOffset + 5] == 89) {
            this.fCurrentOffset += 6;
            return this.scanEntityDecl();
        }
        if (b == 78 && this.fData[this.fCurrentOffset + 1] == 79 && this.fData[this.fCurrentOffset + 2] == 84 && this.fData[this.fCurrentOffset + 3] == 65 && this.fData[this.fCurrentOffset + 4] == 84 && this.fData[this.fCurrentOffset + 5] == 73 && this.fData[this.fCurrentOffset + 6] == 79 && this.fData[this.fCurrentOffset + 7] == 78) {
            this.fCurrentOffset += 8;
            this.scanNotationDecl();
            return true;
        }
        if (b == 45) {
            this.fCurrentOffset += 2;
            this.fEntityContent.offset = this.fCurrentOffset;
            this.fCurrentOffset = LatinMarkupDeclScanner.scanComment(this.fHandler, this.fDTDParams, this.fEntityContent);
            return true;
        }
        ++this.fCurrentOffset;
        final int fEntityDepth = this.fEntityDepth;
        this.checkForPEReference();
        if (this.fData[this.fCurrentOffset] == 73 && this.fData[this.fCurrentOffset + 1] == 78 && this.fData[this.fCurrentOffset + 2] == 67 && this.fData[this.fCurrentOffset + 3] == 76 && this.fData[this.fCurrentOffset + 4] == 85 && this.fData[this.fCurrentOffset + 5] == 68 && this.fData[this.fCurrentOffset + 6] == 69) {
            this.fCurrentOffset += 7;
            this.checkForPEReference();
            if (this.fEntityDepth != fEntityDepth) {}
            ++this.fCurrentOffset;
            ++this.fIncludeSectDepth;
            return true;
        }
        this.fCurrentOffset += 6;
        this.checkForPEReference();
        ++this.fCurrentOffset;
        this.scanIgnoreSectContents();
        return true;
    }
    
    private void scanIgnoreSectContents() {
        int n = 1;
        while (true) {
            final byte b = this.fData[this.fCurrentOffset];
            if (b == 60 && this.fData[this.fCurrentOffset + 1] == 33 && this.fData[this.fCurrentOffset + 2] == 91) {
                this.fCurrentOffset += 3;
                ++n;
            }
            else if (b == 93 && this.fData[this.fCurrentOffset + 1] == 93 && this.fData[this.fCurrentOffset + 2] == 62) {
                this.fCurrentOffset += 3;
                if (--n == 0) {
                    break;
                }
                continue;
            }
            else {
                ++this.fCurrentOffset;
            }
        }
    }
    
    private void scanElementDecl() {
        this.checkForPEReference();
        final QName elementType = this.fDTDParams.getElementType();
        elementType.offset = this.fCurrentOffset;
        byte b;
        do {
            b = this.fData[++this.fCurrentOffset];
            if (b == 58) {
                elementType.sepOffset = this.fCurrentOffset;
                b = this.fData[++this.fCurrentOffset];
            }
        } while (b != 32 && b != 10 && b != 9 && b != 13 && b != 37 && b != 0);
        elementType.endOffset = this.fCurrentOffset;
        this.fHandler.startElementDecl(elementType);
        this.checkForPEReference();
        final byte b2 = this.fData[this.fCurrentOffset];
        if (b2 == 65) {
            this.fCurrentOffset += 3;
            this.fHandler.contentModelANY();
        }
        else if (b2 == 69) {
            this.fCurrentOffset += 5;
            this.fHandler.contentModelEMPTY();
        }
        else {
            ++this.fCurrentOffset;
            ++this.fMarkupDepth;
            this.fHandler.contentModelStartGroup();
            this.checkForPEReference();
            if (this.fData[this.fCurrentOffset] == 35 && this.fData[this.fCurrentOffset + 1] == 80 && this.fData[this.fCurrentOffset + 2] == 67 && this.fData[this.fCurrentOffset + 3] == 68 && this.fData[this.fCurrentOffset + 4] == 65 && this.fData[this.fCurrentOffset + 5] == 84 && this.fData[this.fCurrentOffset + 6] == 65) {
                this.fCurrentOffset += 7;
                this.fHandler.contentModelPCDATA();
                this.scanMixed();
            }
            else {
                this.scanChildren(1);
            }
        }
        this.checkForPEReference();
        ++this.fCurrentOffset;
        this.fHandler.endElementDecl();
        this.fDTDParams.resetElementType();
    }
    
    private void scanMixed() {
        while (true) {
            this.checkForPEReference();
            if (this.fData[this.fCurrentOffset] == 41) {
                break;
            }
            ++this.fCurrentOffset;
            this.fHandler.contentModelSeparator(0);
            this.checkForPEReference();
            final QName contentModelElement = this.fDTDParams.getContentModelElement();
            contentModelElement.offset = this.fCurrentOffset;
            byte b;
            do {
                b = this.fData[++this.fCurrentOffset];
                if (b == 58) {
                    contentModelElement.sepOffset = this.fCurrentOffset;
                    b = this.fData[++this.fCurrentOffset];
                }
            } while (b != 32 && b != 10 && b != 9 && b != 13 && b != 37 && b != 0 && b != 124 && b != 41);
            contentModelElement.endOffset = this.fCurrentOffset;
            this.fHandler.contentModelElement(contentModelElement);
            this.fDTDParams.resetContentModelElement();
        }
        final byte b2 = this.fData[++this.fCurrentOffset];
        this.fHandler.contentModelEndGroup();
        --this.fMarkupDepth;
        if (b2 == 42) {
            ++this.fCurrentOffset;
            this.fHandler.contentModelOccurrence(1);
        }
    }
    
    private void scanChildren(final int n) {
        while (true) {
            this.scanCp(n);
            this.checkForPEReference();
            final byte b = this.fData[this.fCurrentOffset];
            if (b == 41) {
                break;
            }
            ++this.fCurrentOffset;
            if (b == 124) {
                this.fHandler.contentModelSeparator(0);
            }
            else {
                this.fHandler.contentModelSeparator(1);
            }
            this.checkForPEReference();
        }
        final byte b2 = this.fData[++this.fCurrentOffset];
        this.fHandler.contentModelEndGroup();
        --this.fMarkupDepth;
        if (b2 == 63) {
            ++this.fCurrentOffset;
            this.fHandler.contentModelOccurrence(0);
        }
        else if (b2 == 42) {
            ++this.fCurrentOffset;
            this.fHandler.contentModelOccurrence(1);
        }
        else if (b2 == 43) {
            ++this.fCurrentOffset;
            this.fHandler.contentModelOccurrence(2);
        }
    }
    
    private void scanCp(final int n) {
        if (this.fData[this.fCurrentOffset] == 40) {
            ++this.fCurrentOffset;
            ++this.fMarkupDepth;
            this.fHandler.contentModelStartGroup();
            this.checkForPEReference();
            this.scanChildren(n + 1);
            return;
        }
        final QName contentModelElement = this.fDTDParams.getContentModelElement();
        contentModelElement.offset = this.fCurrentOffset;
        byte b;
        do {
            b = this.fData[++this.fCurrentOffset];
            if (b == 58) {
                contentModelElement.sepOffset = this.fCurrentOffset;
                b = this.fData[++this.fCurrentOffset];
            }
        } while (b != 32 && b != 10 && b != 9 && b != 13 && b != 37 && b != 0 && b != 124 && b != 44 && b != 41 && b != 63 && b != 42 && b != 43);
        contentModelElement.endOffset = this.fCurrentOffset;
        this.fHandler.contentModelElement(contentModelElement);
        this.fDTDParams.resetContentModelElement();
        if (b == 63) {
            ++this.fCurrentOffset;
            this.fHandler.contentModelOccurrence(0);
        }
        else if (b == 42) {
            ++this.fCurrentOffset;
            this.fHandler.contentModelOccurrence(1);
        }
        else if (b == 43) {
            ++this.fCurrentOffset;
            this.fHandler.contentModelOccurrence(2);
        }
    }
    
    private boolean scanAttlistDecl() {
        DTDParams fdtdParams = null;
        DTDParams fdtdParams2 = null;
        this.checkForPEReference();
        final QName elementType = this.fDTDParams.getElementType();
        elementType.offset = this.fCurrentOffset;
        byte b;
        do {
            b = this.fData[++this.fCurrentOffset];
            if (b == 58) {
                elementType.sepOffset = this.fCurrentOffset;
                b = this.fData[++this.fCurrentOffset];
            }
        } while (b != 32 && b != 10 && b != 9 && b != 13 && b != 37 && b != 0);
        elementType.endOffset = this.fCurrentOffset;
        this.fHandler.startAttlistDecl(elementType);
        while (true) {
            this.checkForPEReference();
            if (this.fData[this.fCurrentOffset] == 62) {
                ++this.fCurrentOffset;
                this.fHandler.endAttlistDecl();
                this.fDTDParams.resetElementType();
                return true;
            }
            final DTDParams fdtdParams3 = this.fDTDParams;
            final QName attributeName = fdtdParams3.getAttributeName();
            attributeName.offset = this.fCurrentOffset;
            byte b2;
            do {
                b2 = this.fData[++this.fCurrentOffset];
                if (b2 == 58) {
                    attributeName.sepOffset = this.fCurrentOffset;
                    b2 = this.fData[++this.fCurrentOffset];
                }
            } while (b2 != 32 && b2 != 10 && b2 != 9 && b2 != 13 && b2 != 37 && b2 != 0);
            attributeName.endOffset = this.fCurrentOffset;
            this.checkForPEReference();
            boolean b3 = this.fData[this.fCurrentOffset] == 40;
            XMLString attributeType;
            if (!b3) {
                b3 = (this.fData[this.fCurrentOffset + 1] == 79);
                fdtdParams = this.fDTDParams;
                attributeType = fdtdParams.getAttributeType();
                attributeType.offset = this.fCurrentOffset;
                byte b4;
                do {
                    b4 = this.fData[++this.fCurrentOffset];
                } while (b4 != 32 && b4 != 10 && b4 != 9 && b4 != 13 && b4 != 37 && b4 != 0);
                attributeType.endOffset = this.fCurrentOffset;
                if (b3) {
                    this.checkForPEReference();
                }
            }
            else {
                attributeType = null;
            }
            this.fHandler.startAttDef(attributeName, attributeType);
            if (b3) {
                this.fHandler.startEnumerationType();
                do {
                    ++this.fCurrentOffset;
                    this.checkForPEReference();
                    final XMLString enumerationTypeToken = this.fDTDParams.getEnumerationTypeToken();
                    enumerationTypeToken.offset = this.fCurrentOffset;
                    byte b5;
                    do {
                        b5 = this.fData[++this.fCurrentOffset];
                    } while (b5 != 32 && b5 != 10 && b5 != 9 && b5 != 13 && b5 != 37 && b5 != 0 && b5 != 124 && b5 != 41);
                    enumerationTypeToken.endOffset = this.fCurrentOffset;
                    this.fHandler.enumerationType(enumerationTypeToken);
                    this.fDTDParams.resetEnumerationTypeToken();
                    this.checkForPEReference();
                } while (this.fData[this.fCurrentOffset] != 41);
                ++this.fCurrentOffset;
                this.fHandler.endEnumerationType();
            }
            this.checkForPEReference();
            byte b6 = this.fData[this.fCurrentOffset];
            XMLString attributeDefaultType;
            boolean b8;
            if (b6 == 35) {
                fdtdParams2 = this.fDTDParams;
                attributeDefaultType = fdtdParams2.getAttributeDefaultType();
                attributeDefaultType.offset = this.fCurrentOffset;
                final byte b7 = this.fData[++this.fCurrentOffset];
                if (b7 == 70 && this.fData[this.fCurrentOffset + 1] == 73 && this.fData[this.fCurrentOffset + 2] == 88 && this.fData[this.fCurrentOffset + 3] == 69 && this.fData[this.fCurrentOffset + 4] == 68) {
                    this.fCurrentOffset += 5;
                    attributeDefaultType.endOffset = this.fCurrentOffset;
                    this.checkForPEReference();
                    b8 = true;
                    b6 = this.fData[this.fCurrentOffset];
                }
                else {
                    if (b7 == 73 && this.fData[this.fCurrentOffset + 1] == 77 && this.fData[this.fCurrentOffset + 2] == 80 && this.fData[this.fCurrentOffset + 3] == 76 && this.fData[this.fCurrentOffset + 4] == 73 && this.fData[this.fCurrentOffset + 5] == 69 && this.fData[this.fCurrentOffset + 6] == 68) {
                        this.fCurrentOffset += 7;
                    }
                    else {
                        this.fCurrentOffset += 8;
                    }
                    attributeDefaultType.endOffset = this.fCurrentOffset;
                    b8 = false;
                    b6 = this.fData[this.fCurrentOffset];
                }
            }
            else {
                attributeDefaultType = null;
                b8 = true;
            }
            if (b8) {
                final byte b9 = b6;
                ++this.fCurrentOffset;
                this.fHandler.startDefaultAttValue();
                this.fEntityContent.offset = this.fCurrentOffset;
                if (!LatinMarkupDeclScanner.scanDefaultAttValue(this.fHandler, this.fDTDParams, this.fEntityContent, b9)) {
                    return false;
                }
                this.fCurrentOffset = this.fEntityContent.offset;
            }
            this.fHandler.endAttDef(attributeDefaultType);
            fdtdParams3.resetAttributeName();
            if (attributeType != null) {
                fdtdParams.resetAttributeType();
            }
            if (attributeDefaultType == null) {
                continue;
            }
            fdtdParams2.resetAttributeDefaultType();
        }
    }
    
    private boolean scanEntityDecl() {
        DTDParams fdtdParams = null;
        boolean b = false;
        final byte b2 = this.fData[this.fCurrentOffset];
        byte b3;
        int n;
        if (b2 == 32 || b2 == 10 || b2 == 9 || b2 == 13) {
            do {
                b3 = this.fData[++this.fCurrentOffset];
            } while (b3 == 32 || b3 == 10 || b3 == 9 || b3 == 13);
            if (b3 != 37) {
                n = 0;
            }
            else {
                b3 = this.fData[++this.fCurrentOffset];
                if (b3 == 32 || b3 == 10 || b3 == 9 || b3 == 13) {
                    b3 = this.fData[++this.fCurrentOffset];
                    this.checkForPEReference();
                    n = 1;
                }
                else if (b3 == 37) {
                    this.checkForPEReference();
                    n = 1;
                }
                else {
                    b = true;
                    n = 0;
                }
            }
        }
        else {
            b3 = this.fData[++this.fCurrentOffset];
            b = true;
            n = 0;
        }
        if (b) {
            while (true) {
                final XMLName peReferenceName = this.fDTDParams.getPEReferenceName();
                peReferenceName.offset = this.fCurrentOffset;
                while (b3 != 59) {
                    b3 = this.fData[++this.fCurrentOffset];
                }
                peReferenceName.endOffset = this.fCurrentOffset;
                this.fEntityContent.offset = ++this.fCurrentOffset;
                this.fHandler.startPEReferenceWithinMarkup(peReferenceName, this.fMarkupDepth);
                this.fCurrentOffset = this.fEntityContent.offset;
                while (true) {
                    for (b3 = this.fData[this.fCurrentOffset]; b3 == 32 || b3 == 10 || b3 == 9 || b3 == 13; b3 = this.fData[++this.fCurrentOffset]) {}
                    this.fEntityContent.offset = this.fCurrentOffset;
                    if (b3 != 0 || this.fCurrentOffset != this.fEntityContent.endOffset) {
                        break;
                    }
                    this.endOfContentWithinMarkup();
                    this.fDTDParams.resetPEReferenceName();
                }
                if (b3 != 37) {
                    break;
                }
                ++this.fCurrentOffset;
                if (n != 0) {
                    continue;
                }
                b3 = this.fData[this.fCurrentOffset];
                if (b3 == 32 || b3 == 10 || b3 == 9 || b3 == 13) {
                    ++this.fCurrentOffset;
                    this.checkForPEReference();
                    n = 1;
                    break;
                }
                n = ((b3 == 37) ? 1 : 0);
                if (n == 0) {
                    continue;
                }
                ++this.fCurrentOffset;
            }
        }
        final DTDParams fdtdParams2 = this.fDTDParams;
        final XMLName entityDeclName = fdtdParams2.getEntityDeclName();
        entityDeclName.offset = this.fCurrentOffset;
        byte b4;
        do {
            b4 = this.fData[++this.fCurrentOffset];
        } while (b4 != 32 && b4 != 10 && b4 != 9 && b4 != 13 && b4 != 37 && b4 != 0);
        entityDeclName.endOffset = this.fCurrentOffset;
        this.checkForPEReference();
        final byte b5 = this.fData[this.fCurrentOffset];
        if (b5 != 34 && b5 != 39) {
            XMLString publicID;
            if (b5 == 80 && this.fData[this.fCurrentOffset + 1] == 85 && this.fData[this.fCurrentOffset + 2] == 66 && this.fData[this.fCurrentOffset + 3] == 76 && this.fData[this.fCurrentOffset + 4] == 73 && this.fData[this.fCurrentOffset + 5] == 67) {
                this.fCurrentOffset += 6;
                this.checkForPEReference();
                final byte b6 = this.fData[this.fCurrentOffset];
                byte b7 = this.fData[++this.fCurrentOffset];
                fdtdParams = this.fDTDParams;
                publicID = fdtdParams.getPublicID();
                publicID.offset = this.fCurrentOffset;
                while (b7 != b6) {
                    b7 = this.fData[++this.fCurrentOffset];
                }
                publicID.endOffset = this.fCurrentOffset;
                ++this.fCurrentOffset;
            }
            else {
                this.fCurrentOffset += 6;
                publicID = null;
            }
            this.checkForPEReference();
            final byte b8 = this.fData[this.fCurrentOffset];
            byte b9 = this.fData[++this.fCurrentOffset];
            final DTDParams fdtdParams3 = this.fDTDParams;
            final XMLString systemID = fdtdParams3.getSystemID();
            systemID.offset = this.fCurrentOffset;
            while (b9 != b8) {
                b9 = this.fData[++this.fCurrentOffset];
            }
            systemID.endOffset = this.fCurrentOffset;
            ++this.fCurrentOffset;
            this.checkForPEReference();
            if (this.fData[this.fCurrentOffset] == 62) {
                ++this.fCurrentOffset;
                if (n != 0) {
                    this.fHandler.externalPEDecl(entityDeclName, publicID, systemID);
                }
                else {
                    this.fHandler.externalEntityDecl(entityDeclName, publicID, systemID);
                }
            }
            else {
                this.fCurrentOffset += 5;
                this.checkForPEReference();
                final DTDParams fdtdParams4 = this.fDTDParams;
                final XMLName notationName = fdtdParams4.getNotationName();
                notationName.offset = this.fCurrentOffset;
                byte b10;
                do {
                    b10 = this.fData[++this.fCurrentOffset];
                } while (b10 != 32 && b10 != 10 && b10 != 9 && b10 != 13 && b10 != 37 && b10 != 0 && b10 != 62);
                notationName.endOffset = this.fCurrentOffset;
                this.checkForPEReference();
                ++this.fCurrentOffset;
                this.fHandler.unparsedEntityDecl(entityDeclName, publicID, systemID, notationName);
                fdtdParams4.resetNotationName();
            }
            fdtdParams2.resetEntityDeclName();
            if (publicID != null) {
                fdtdParams.resetPublicID();
            }
            fdtdParams3.resetSystemID();
            return true;
        }
        final byte b11 = b5;
        ++this.fCurrentOffset;
        this.fHandler.startEntityValue();
        this.fEntityContent.offset = this.fCurrentOffset;
        if (!LatinMarkupDeclScanner.scanEntityValue(this.fHandler, this.fDTDParams, this.fEntityContent, b11)) {
            return false;
        }
        this.fCurrentOffset = this.fEntityContent.offset;
        this.checkForPEReference();
        final byte b12 = this.fData[this.fCurrentOffset];
        ++this.fCurrentOffset;
        if (n != 0) {
            this.fHandler.internalPEDecl(entityDeclName);
        }
        else {
            this.fHandler.internalEntityDecl(entityDeclName);
        }
        fdtdParams2.resetEntityDeclName();
        return true;
    }
    
    private void scanNotationDecl() {
        DTDParams fdtdParams = null;
        DTDParams fdtdParams2 = null;
        this.checkForPEReference();
        final DTDParams fdtdParams3 = this.fDTDParams;
        final XMLName notationName = fdtdParams3.getNotationName();
        notationName.offset = this.fCurrentOffset;
        byte b;
        do {
            b = this.fData[++this.fCurrentOffset];
        } while (b != 32 && b != 10 && b != 9 && b != 13 && b != 37 && b != 0);
        notationName.endOffset = this.fCurrentOffset;
        this.checkForPEReference();
        XMLString publicID;
        if (this.fData[this.fCurrentOffset] == 80 && this.fData[this.fCurrentOffset + 1] == 85 && this.fData[this.fCurrentOffset + 2] == 66 && this.fData[this.fCurrentOffset + 3] == 76 && this.fData[this.fCurrentOffset + 4] == 73 && this.fData[this.fCurrentOffset + 5] == 67) {
            this.fCurrentOffset += 6;
            this.checkForPEReference();
            final byte b2 = this.fData[this.fCurrentOffset];
            byte b3 = this.fData[++this.fCurrentOffset];
            fdtdParams = this.fDTDParams;
            publicID = fdtdParams.getPublicID();
            publicID.offset = this.fCurrentOffset;
            while (b3 != b2) {
                b3 = this.fData[++this.fCurrentOffset];
            }
            publicID.endOffset = this.fCurrentOffset;
            ++this.fCurrentOffset;
        }
        else {
            this.fCurrentOffset += 6;
            publicID = null;
        }
        XMLString systemID;
        if (this.fData[this.fCurrentOffset] == 62) {
            systemID = null;
        }
        else {
            this.checkForPEReference();
            final byte b4 = this.fData[this.fCurrentOffset];
            if (b4 == 34 || b4 == 39) {
                byte b5 = this.fData[++this.fCurrentOffset];
                fdtdParams2 = this.fDTDParams;
                systemID = fdtdParams2.getSystemID();
                systemID.offset = this.fCurrentOffset;
                while (b5 != b4) {
                    b5 = this.fData[++this.fCurrentOffset];
                }
                systemID.endOffset = this.fCurrentOffset;
                ++this.fCurrentOffset;
            }
            else {
                systemID = null;
            }
        }
        this.checkForPEReference();
        ++this.fCurrentOffset;
        this.fHandler.notationDecl(notationName, publicID, systemID);
        fdtdParams3.resetNotationName();
        if (publicID != null) {
            fdtdParams.resetPublicID();
        }
        if (systemID != null) {
            fdtdParams2.resetSystemID();
        }
    }
    
    private void checkForPEReference() {
        while (true) {
            byte b;
            for (b = this.fData[this.fCurrentOffset]; b == 32 || b == 10 || b == 9 || b == 13; b = this.fData[++this.fCurrentOffset]) {}
            this.fEntityContent.offset = this.fCurrentOffset;
            if (b == 0 && this.fCurrentOffset == this.fEntityContent.endOffset) {
                this.endOfContentWithinMarkup();
                this.fDTDParams.resetPEReferenceName();
            }
            else {
                if (b != 37) {
                    break;
                }
                final XMLName peReferenceName = this.fDTDParams.getPEReferenceName();
                peReferenceName.offset = ++this.fCurrentOffset;
                while (b != 59) {
                    b = this.fData[++this.fCurrentOffset];
                }
                peReferenceName.endOffset = this.fCurrentOffset;
                this.fEntityContent.offset = ++this.fCurrentOffset;
                this.fHandler.startPEReferenceWithinMarkup(peReferenceName, this.fMarkupDepth);
            }
        }
    }
    
    private void endExternalSubset() {
        --this.fEntityDepth;
    }
    
    private void endPEReferenceBetweenMarkup() {
        --this.fEntityDepth;
        if (this.fEntityDepth > 0) {
            final int n = this.fEntityDepth - 1;
            this.fDTDParams = this.fDTDParamsStack[n];
            this.fEntityContent = this.fEntityContentStack[n];
            this.fData = this.fDataStack[n];
            this.fCurrentOffset = this.fCurrentOffsetStack[n];
            this.fEntityState = this.fEntityStateStack[n];
        }
    }
    
    private void endOfContentWithinMarkup() {
        --this.fEntityDepth;
        this.fHandler.endPEReferenceWithinMarkup(this.fMarkupDepth);
        final int n = this.fEntityDepth - 1;
        this.fDTDParams = this.fDTDParamsStack[n];
        this.fEntityContent = this.fEntityContentStack[n];
        this.fData = this.fDataStack[n];
        this.fCurrentOffset = this.fCurrentOffsetStack[n];
        this.fEntityState = this.fEntityStateStack[n];
    }
    
    private void growContext() {
        final int n = this.fEntityDepth - 1;
        final int n2 = n << 1;
        final DTDParams[] fdtdParamsStack = new DTDParams[n2];
        System.arraycopy(this.fDTDParamsStack, 0, fdtdParamsStack, 0, n);
        this.fDTDParamsStack = fdtdParamsStack;
        final ParsedEntity[] fEntityContentStack = new ParsedEntity[n2];
        System.arraycopy(this.fEntityContentStack, 0, fEntityContentStack, 0, n);
        this.fEntityContentStack = fEntityContentStack;
        final byte[][] fDataStack = new byte[n2][];
        System.arraycopy(this.fDataStack, 0, fDataStack, 0, n);
        this.fDataStack = fDataStack;
        final int[] fCurrentOffsetStack = new int[n2];
        System.arraycopy(this.fCurrentOffsetStack, 0, fCurrentOffsetStack, 0, n);
        this.fCurrentOffsetStack = fCurrentOffsetStack;
        final int[] fEntityStateStack = new int[n2];
        System.arraycopy(this.fEntityStateStack, 0, fEntityStateStack, 0, n);
        this.fEntityStateStack = fEntityStateStack;
    }
    
    private LatinExternalSubsetScanner() {
    }
}
