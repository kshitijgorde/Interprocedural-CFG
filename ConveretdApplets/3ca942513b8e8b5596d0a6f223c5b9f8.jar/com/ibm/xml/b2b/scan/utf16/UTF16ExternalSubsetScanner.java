// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.utf16;

import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.QName;
import com.ibm.xml.b2b.scan.MarkupDeclHandler;
import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.DTDParams;
import com.ibm.xml.b2b.scan.ExternalSubsetHandler;

public final class UTF16ExternalSubsetScanner extends UTF16MarkupDeclScanner
{
    private static final int ENTITYSTATE_DTD_EXTERNAL_SUBSET = 0;
    private static final int ENTITYSTATE_PE_BETWEEN_MARKUP = 1;
    private static final int ENTITYSTATE_PE_WITHIN_MARKUP = 2;
    private ExternalSubsetHandler fHandler;
    private DTDParams fDTDParams;
    private ParsedEntity fEntityContent;
    private char[] fData;
    private int fCurrentOffset;
    private int fEntityDepth;
    private DTDParams[] fDTDParamsStack;
    private ParsedEntity[] fEntityContentStack;
    private char[][] fDataStack;
    private int[] fCurrentOffsetStack;
    private int fEntityState;
    private int fIncludeSectDepth;
    private int fMarkupDepth;
    private int[] fEntityStateStack;
    
    public UTF16ExternalSubsetScanner(final ExternalSubsetHandler fHandler) {
        this.fHandler = fHandler;
        this.fCurrentOffset = -1;
        this.fEntityState = -1;
        this.fDTDParamsStack = new DTDParams[4];
        this.fEntityContentStack = new ParsedEntity[4];
        this.fDataStack = new char[4][];
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
            if (this.fEntityContent.chars != this.fData || this.fEntityContent.offset != this.fCurrentOffset) {
                throw new RuntimeException("UTF16ExternalSubsetScanner#setupContext()");
            }
            this.fDTDParamsStack[n] = this.fDTDParams;
            this.fEntityContentStack[n] = this.fEntityContent;
            this.fDataStack[n] = this.fData;
            this.fCurrentOffsetStack[n] = this.fCurrentOffset;
            this.fEntityStateStack[n] = this.fEntityState;
        }
        this.fDTDParams = fdtdParams;
        this.fEntityContent = fEntityContent;
        this.fData = fEntityContent.chars;
        this.fCurrentOffset = fEntityContent.offset;
        this.fEntityState = fEntityState;
        ++this.fEntityDepth;
    }
    
    private boolean scanExtSubsetDecl() {
        final int fEntityDepth = this.fEntityDepth;
        while (true) {
            char c;
            for (c = this.fData[this.fCurrentOffset]; c == ' ' || c == '\n' || c == '\t' || c == '\r'; c = this.fData[++this.fCurrentOffset]) {}
            if (c == '<') {
                ++this.fCurrentOffset;
                ++this.fMarkupDepth;
                if (!this.scanMarkupDecl()) {
                    return false;
                }
                --this.fMarkupDepth;
            }
            else if (c == '%') {
                final XMLName peName = this.fDTDParams.getPEName();
                peName.offset = ++this.fCurrentOffset;
                while (c != ';') {
                    c = this.fData[++this.fCurrentOffset];
                }
                peName.endOffset = this.fCurrentOffset;
                this.fEntityContent.offset = ++this.fCurrentOffset;
                this.fHandler.externalSubsetPEReference(peName);
                this.fCurrentOffset = this.fEntityContent.offset;
                this.fDTDParams.resetPEName();
            }
            else if (c == ']') {
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
        if (this.fData[this.fCurrentOffset] != '!') {
            this.fEntityContent.offset = ++this.fCurrentOffset;
            this.fCurrentOffset = UTF16MarkupDeclScanner.scanPI(this.fHandler, this.fDTDParams, this.fEntityContent);
            return true;
        }
        final char c = this.fData[++this.fCurrentOffset];
        if (c == 'E' && this.fData[this.fCurrentOffset + 1] == 'L' && this.fData[this.fCurrentOffset + 2] == 'E' && this.fData[this.fCurrentOffset + 3] == 'M' && this.fData[this.fCurrentOffset + 4] == 'E' && this.fData[this.fCurrentOffset + 5] == 'N' && this.fData[this.fCurrentOffset + 6] == 'T') {
            this.fCurrentOffset += 7;
            this.scanElementDecl();
            return true;
        }
        if (c == 'A' && this.fData[this.fCurrentOffset + 1] == 'T' && this.fData[this.fCurrentOffset + 2] == 'T' && this.fData[this.fCurrentOffset + 3] == 'L' && this.fData[this.fCurrentOffset + 4] == 'I' && this.fData[this.fCurrentOffset + 5] == 'S' && this.fData[this.fCurrentOffset + 6] == 'T') {
            this.fCurrentOffset += 7;
            return this.scanAttlistDecl();
        }
        if (c == 'E' && this.fData[this.fCurrentOffset + 1] == 'N' && this.fData[this.fCurrentOffset + 2] == 'T' && this.fData[this.fCurrentOffset + 3] == 'I' && this.fData[this.fCurrentOffset + 4] == 'T' && this.fData[this.fCurrentOffset + 5] == 'Y') {
            this.fCurrentOffset += 6;
            return this.scanEntityDecl();
        }
        if (c == 'N' && this.fData[this.fCurrentOffset + 1] == 'O' && this.fData[this.fCurrentOffset + 2] == 'T' && this.fData[this.fCurrentOffset + 3] == 'A' && this.fData[this.fCurrentOffset + 4] == 'T' && this.fData[this.fCurrentOffset + 5] == 'I' && this.fData[this.fCurrentOffset + 6] == 'O' && this.fData[this.fCurrentOffset + 7] == 'N') {
            this.fCurrentOffset += 8;
            this.scanNotationDecl();
            return true;
        }
        if (c == '-') {
            this.fCurrentOffset += 2;
            this.fEntityContent.offset = this.fCurrentOffset;
            this.fCurrentOffset = UTF16MarkupDeclScanner.scanComment(this.fHandler, this.fDTDParams, this.fEntityContent);
            return true;
        }
        ++this.fCurrentOffset;
        final int fEntityDepth = this.fEntityDepth;
        this.checkForPEReference();
        if (this.fData[this.fCurrentOffset] == 'I' && this.fData[this.fCurrentOffset + 1] == 'N' && this.fData[this.fCurrentOffset + 2] == 'C' && this.fData[this.fCurrentOffset + 3] == 'L' && this.fData[this.fCurrentOffset + 4] == 'U' && this.fData[this.fCurrentOffset + 5] == 'D' && this.fData[this.fCurrentOffset + 6] == 'E') {
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
            final char c = this.fData[this.fCurrentOffset];
            if (c == '<' && this.fData[this.fCurrentOffset + 1] == '!' && this.fData[this.fCurrentOffset + 2] == '[') {
                this.fCurrentOffset += 3;
                ++n;
            }
            else if (c == ']' && this.fData[this.fCurrentOffset + 1] == ']' && this.fData[this.fCurrentOffset + 2] == '>') {
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
        char c;
        do {
            c = this.fData[++this.fCurrentOffset];
            if (c == ':') {
                elementType.sepOffset = this.fCurrentOffset;
                c = this.fData[++this.fCurrentOffset];
            }
        } while (c != ' ' && c != '\n' && c != '\t' && c != '\r' && c != '%' && c != '\0');
        elementType.endOffset = this.fCurrentOffset;
        this.fHandler.startElementDecl(elementType);
        this.checkForPEReference();
        final char c2 = this.fData[this.fCurrentOffset];
        if (c2 == 'A') {
            this.fCurrentOffset += 3;
            this.fHandler.contentModelANY();
        }
        else if (c2 == 'E') {
            this.fCurrentOffset += 5;
            this.fHandler.contentModelEMPTY();
        }
        else {
            ++this.fCurrentOffset;
            ++this.fMarkupDepth;
            this.fHandler.contentModelStartGroup();
            this.checkForPEReference();
            if (this.fData[this.fCurrentOffset] == '#' && this.fData[this.fCurrentOffset + 1] == 'P' && this.fData[this.fCurrentOffset + 2] == 'C' && this.fData[this.fCurrentOffset + 3] == 'D' && this.fData[this.fCurrentOffset + 4] == 'A' && this.fData[this.fCurrentOffset + 5] == 'T' && this.fData[this.fCurrentOffset + 6] == 'A') {
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
            if (this.fData[this.fCurrentOffset] == ')') {
                break;
            }
            ++this.fCurrentOffset;
            this.fHandler.contentModelSeparator(0);
            this.checkForPEReference();
            final QName contentModelElement = this.fDTDParams.getContentModelElement();
            contentModelElement.offset = this.fCurrentOffset;
            char c;
            do {
                c = this.fData[++this.fCurrentOffset];
                if (c == ':') {
                    contentModelElement.sepOffset = this.fCurrentOffset;
                    c = this.fData[++this.fCurrentOffset];
                }
            } while (c != ' ' && c != '\n' && c != '\t' && c != '\r' && c != '%' && c != '\0' && c != '|' && c != ')');
            contentModelElement.endOffset = this.fCurrentOffset;
            this.fHandler.contentModelElement(contentModelElement);
            this.fDTDParams.resetContentModelElement();
        }
        final char c2 = this.fData[++this.fCurrentOffset];
        this.fHandler.contentModelEndGroup();
        --this.fMarkupDepth;
        if (c2 == '*') {
            ++this.fCurrentOffset;
            this.fHandler.contentModelOccurrence(1);
        }
    }
    
    private void scanChildren(final int n) {
        while (true) {
            this.scanCp(n);
            this.checkForPEReference();
            final char c = this.fData[this.fCurrentOffset];
            if (c == ')') {
                break;
            }
            ++this.fCurrentOffset;
            if (c == '|') {
                this.fHandler.contentModelSeparator(0);
            }
            else {
                this.fHandler.contentModelSeparator(1);
            }
            this.checkForPEReference();
        }
        final char c2 = this.fData[++this.fCurrentOffset];
        this.fHandler.contentModelEndGroup();
        --this.fMarkupDepth;
        if (c2 == '?') {
            ++this.fCurrentOffset;
            this.fHandler.contentModelOccurrence(0);
        }
        else if (c2 == '*') {
            ++this.fCurrentOffset;
            this.fHandler.contentModelOccurrence(1);
        }
        else if (c2 == '+') {
            ++this.fCurrentOffset;
            this.fHandler.contentModelOccurrence(2);
        }
    }
    
    private void scanCp(final int n) {
        if (this.fData[this.fCurrentOffset] == '(') {
            ++this.fCurrentOffset;
            ++this.fMarkupDepth;
            this.fHandler.contentModelStartGroup();
            this.checkForPEReference();
            this.scanChildren(n + 1);
            return;
        }
        final QName contentModelElement = this.fDTDParams.getContentModelElement();
        contentModelElement.offset = this.fCurrentOffset;
        char c;
        do {
            c = this.fData[++this.fCurrentOffset];
            if (c == ':') {
                contentModelElement.sepOffset = this.fCurrentOffset;
                c = this.fData[++this.fCurrentOffset];
            }
        } while (c != ' ' && c != '\n' && c != '\t' && c != '\r' && c != '%' && c != '\0' && c != '|' && c != ',' && c != ')' && c != '?' && c != '*' && c != '+');
        contentModelElement.endOffset = this.fCurrentOffset;
        this.fHandler.contentModelElement(contentModelElement);
        this.fDTDParams.resetContentModelElement();
        if (c == '?') {
            ++this.fCurrentOffset;
            this.fHandler.contentModelOccurrence(0);
        }
        else if (c == '*') {
            ++this.fCurrentOffset;
            this.fHandler.contentModelOccurrence(1);
        }
        else if (c == '+') {
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
        char c;
        do {
            c = this.fData[++this.fCurrentOffset];
            if (c == ':') {
                elementType.sepOffset = this.fCurrentOffset;
                c = this.fData[++this.fCurrentOffset];
            }
        } while (c != ' ' && c != '\n' && c != '\t' && c != '\r' && c != '%' && c != '\0');
        elementType.endOffset = this.fCurrentOffset;
        this.fHandler.startAttlistDecl(elementType);
        while (true) {
            this.checkForPEReference();
            if (this.fData[this.fCurrentOffset] == '>') {
                ++this.fCurrentOffset;
                this.fHandler.endAttlistDecl();
                this.fDTDParams.resetElementType();
                return true;
            }
            final DTDParams fdtdParams3 = this.fDTDParams;
            final QName attributeName = fdtdParams3.getAttributeName();
            attributeName.offset = this.fCurrentOffset;
            char c2;
            do {
                c2 = this.fData[++this.fCurrentOffset];
                if (c2 == ':') {
                    attributeName.sepOffset = this.fCurrentOffset;
                    c2 = this.fData[++this.fCurrentOffset];
                }
            } while (c2 != ' ' && c2 != '\n' && c2 != '\t' && c2 != '\r' && c2 != '%' && c2 != '\0');
            attributeName.endOffset = this.fCurrentOffset;
            this.checkForPEReference();
            boolean b = this.fData[this.fCurrentOffset] == '(';
            XMLString attributeType;
            if (!b) {
                b = (this.fData[this.fCurrentOffset + 1] == 'O');
                fdtdParams = this.fDTDParams;
                attributeType = fdtdParams.getAttributeType();
                attributeType.offset = this.fCurrentOffset;
                char c3;
                do {
                    c3 = this.fData[++this.fCurrentOffset];
                } while (c3 != ' ' && c3 != '\n' && c3 != '\t' && c3 != '\r' && c3 != '%' && c3 != '\0');
                attributeType.endOffset = this.fCurrentOffset;
                if (b) {
                    this.checkForPEReference();
                }
            }
            else {
                attributeType = null;
            }
            this.fHandler.startAttDef(attributeName, attributeType);
            if (b) {
                this.fHandler.startEnumerationType();
                do {
                    ++this.fCurrentOffset;
                    this.checkForPEReference();
                    final XMLString enumerationTypeToken = this.fDTDParams.getEnumerationTypeToken();
                    enumerationTypeToken.offset = this.fCurrentOffset;
                    char c4;
                    do {
                        c4 = this.fData[++this.fCurrentOffset];
                    } while (c4 != ' ' && c4 != '\n' && c4 != '\t' && c4 != '\r' && c4 != '%' && c4 != '\0' && c4 != '|' && c4 != ')');
                    enumerationTypeToken.endOffset = this.fCurrentOffset;
                    this.fHandler.enumerationType(enumerationTypeToken);
                    this.fDTDParams.resetEnumerationTypeToken();
                    this.checkForPEReference();
                } while (this.fData[this.fCurrentOffset] != ')');
                ++this.fCurrentOffset;
                this.fHandler.endEnumerationType();
            }
            this.checkForPEReference();
            char c5 = this.fData[this.fCurrentOffset];
            XMLString attributeDefaultType;
            boolean b2;
            if (c5 == '#') {
                fdtdParams2 = this.fDTDParams;
                attributeDefaultType = fdtdParams2.getAttributeDefaultType();
                attributeDefaultType.offset = this.fCurrentOffset;
                final char c6 = this.fData[++this.fCurrentOffset];
                if (c6 == 'F' && this.fData[this.fCurrentOffset + 1] == 'I' && this.fData[this.fCurrentOffset + 2] == 'X' && this.fData[this.fCurrentOffset + 3] == 'E' && this.fData[this.fCurrentOffset + 4] == 'D') {
                    this.fCurrentOffset += 5;
                    attributeDefaultType.endOffset = this.fCurrentOffset;
                    this.checkForPEReference();
                    b2 = true;
                    c5 = this.fData[this.fCurrentOffset];
                }
                else {
                    if (c6 == 'I' && this.fData[this.fCurrentOffset + 1] == 'M' && this.fData[this.fCurrentOffset + 2] == 'P' && this.fData[this.fCurrentOffset + 3] == 'L' && this.fData[this.fCurrentOffset + 4] == 'I' && this.fData[this.fCurrentOffset + 5] == 'E' && this.fData[this.fCurrentOffset + 6] == 'D') {
                        this.fCurrentOffset += 7;
                    }
                    else {
                        this.fCurrentOffset += 8;
                    }
                    attributeDefaultType.endOffset = this.fCurrentOffset;
                    b2 = false;
                    c5 = this.fData[this.fCurrentOffset];
                }
            }
            else {
                attributeDefaultType = null;
                b2 = true;
            }
            if (b2) {
                final char c7 = c5;
                ++this.fCurrentOffset;
                this.fHandler.startDefaultAttValue();
                this.fEntityContent.offset = this.fCurrentOffset;
                if (!UTF16MarkupDeclScanner.scanDefaultAttValue(this.fHandler, this.fDTDParams, this.fEntityContent, c7)) {
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
        final char c = this.fData[this.fCurrentOffset];
        char c2;
        int n;
        if (c == ' ' || c == '\n' || c == '\t' || c == '\r') {
            do {
                c2 = this.fData[++this.fCurrentOffset];
            } while (c2 == ' ' || c2 == '\n' || c2 == '\t' || c2 == '\r');
            if (c2 != '%') {
                n = 0;
            }
            else {
                c2 = this.fData[++this.fCurrentOffset];
                if (c2 == ' ' || c2 == '\n' || c2 == '\t' || c2 == '\r') {
                    c2 = this.fData[++this.fCurrentOffset];
                    this.checkForPEReference();
                    n = 1;
                }
                else if (c2 == '%') {
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
            c2 = this.fData[++this.fCurrentOffset];
            b = true;
            n = 0;
        }
        if (b) {
            while (true) {
                final XMLName peReferenceName = this.fDTDParams.getPEReferenceName();
                peReferenceName.offset = this.fCurrentOffset;
                while (c2 != ';') {
                    c2 = this.fData[++this.fCurrentOffset];
                }
                peReferenceName.endOffset = this.fCurrentOffset;
                this.fEntityContent.offset = ++this.fCurrentOffset;
                this.fHandler.startPEReferenceWithinMarkup(peReferenceName, this.fMarkupDepth);
                this.fCurrentOffset = this.fEntityContent.offset;
                while (true) {
                    for (c2 = this.fData[this.fCurrentOffset]; c2 == ' ' || c2 == '\n' || c2 == '\t' || c2 == '\r'; c2 = this.fData[++this.fCurrentOffset]) {}
                    this.fEntityContent.offset = this.fCurrentOffset;
                    if (c2 != '\0' || this.fCurrentOffset != this.fEntityContent.endOffset) {
                        break;
                    }
                    this.endOfContentWithinMarkup();
                    this.fDTDParams.resetPEReferenceName();
                }
                if (c2 != '%') {
                    break;
                }
                ++this.fCurrentOffset;
                if (n != 0) {
                    continue;
                }
                c2 = this.fData[this.fCurrentOffset];
                if (c2 == ' ' || c2 == '\n' || c2 == '\t' || c2 == '\r') {
                    ++this.fCurrentOffset;
                    this.checkForPEReference();
                    n = 1;
                    break;
                }
                n = ((c2 == '%') ? 1 : 0);
                if (n == 0) {
                    continue;
                }
                ++this.fCurrentOffset;
            }
        }
        final DTDParams fdtdParams2 = this.fDTDParams;
        final XMLName entityDeclName = fdtdParams2.getEntityDeclName();
        entityDeclName.offset = this.fCurrentOffset;
        char c3;
        do {
            c3 = this.fData[++this.fCurrentOffset];
        } while (c3 != ' ' && c3 != '\n' && c3 != '\t' && c3 != '\r' && c3 != '%' && c3 != '\0');
        entityDeclName.endOffset = this.fCurrentOffset;
        this.checkForPEReference();
        final char c4 = this.fData[this.fCurrentOffset];
        if (c4 != '\"' && c4 != '\'') {
            XMLString publicID;
            if (c4 == 'P' && this.fData[this.fCurrentOffset + 1] == 'U' && this.fData[this.fCurrentOffset + 2] == 'B' && this.fData[this.fCurrentOffset + 3] == 'L' && this.fData[this.fCurrentOffset + 4] == 'I' && this.fData[this.fCurrentOffset + 5] == 'C') {
                this.fCurrentOffset += 6;
                this.checkForPEReference();
                final char c5 = this.fData[this.fCurrentOffset];
                char c6 = this.fData[++this.fCurrentOffset];
                fdtdParams = this.fDTDParams;
                publicID = fdtdParams.getPublicID();
                publicID.offset = this.fCurrentOffset;
                while (c6 != c5) {
                    c6 = this.fData[++this.fCurrentOffset];
                }
                publicID.endOffset = this.fCurrentOffset;
                ++this.fCurrentOffset;
            }
            else {
                this.fCurrentOffset += 6;
                publicID = null;
            }
            this.checkForPEReference();
            final char c7 = this.fData[this.fCurrentOffset];
            char c8 = this.fData[++this.fCurrentOffset];
            final DTDParams fdtdParams3 = this.fDTDParams;
            final XMLString systemID = fdtdParams3.getSystemID();
            systemID.offset = this.fCurrentOffset;
            while (c8 != c7) {
                c8 = this.fData[++this.fCurrentOffset];
            }
            systemID.endOffset = this.fCurrentOffset;
            ++this.fCurrentOffset;
            this.checkForPEReference();
            if (this.fData[this.fCurrentOffset] == '>') {
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
                char c9;
                do {
                    c9 = this.fData[++this.fCurrentOffset];
                } while (c9 != ' ' && c9 != '\n' && c9 != '\t' && c9 != '\r' && c9 != '%' && c9 != '\0' && c9 != '>');
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
        final char c10 = c4;
        ++this.fCurrentOffset;
        this.fHandler.startEntityValue();
        this.fEntityContent.offset = this.fCurrentOffset;
        if (!UTF16MarkupDeclScanner.scanEntityValue(this.fHandler, this.fDTDParams, this.fEntityContent, c10)) {
            return false;
        }
        this.fCurrentOffset = this.fEntityContent.offset;
        this.checkForPEReference();
        final char c11 = this.fData[this.fCurrentOffset];
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
        char c;
        do {
            c = this.fData[++this.fCurrentOffset];
        } while (c != ' ' && c != '\n' && c != '\t' && c != '\r' && c != '%' && c != '\0');
        notationName.endOffset = this.fCurrentOffset;
        this.checkForPEReference();
        XMLString publicID;
        if (this.fData[this.fCurrentOffset] == 'P' && this.fData[this.fCurrentOffset + 1] == 'U' && this.fData[this.fCurrentOffset + 2] == 'B' && this.fData[this.fCurrentOffset + 3] == 'L' && this.fData[this.fCurrentOffset + 4] == 'I' && this.fData[this.fCurrentOffset + 5] == 'C') {
            this.fCurrentOffset += 6;
            this.checkForPEReference();
            final char c2 = this.fData[this.fCurrentOffset];
            char c3 = this.fData[++this.fCurrentOffset];
            fdtdParams = this.fDTDParams;
            publicID = fdtdParams.getPublicID();
            publicID.offset = this.fCurrentOffset;
            while (c3 != c2) {
                c3 = this.fData[++this.fCurrentOffset];
            }
            publicID.endOffset = this.fCurrentOffset;
            ++this.fCurrentOffset;
        }
        else {
            this.fCurrentOffset += 6;
            publicID = null;
        }
        XMLString systemID;
        if (this.fData[this.fCurrentOffset] == '>') {
            systemID = null;
        }
        else {
            this.checkForPEReference();
            final char c4 = this.fData[this.fCurrentOffset];
            if (c4 == '\"' || c4 == '\'') {
                char c5 = this.fData[++this.fCurrentOffset];
                fdtdParams2 = this.fDTDParams;
                systemID = fdtdParams2.getSystemID();
                systemID.offset = this.fCurrentOffset;
                while (c5 != c4) {
                    c5 = this.fData[++this.fCurrentOffset];
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
            char c;
            for (c = this.fData[this.fCurrentOffset]; c == ' ' || c == '\n' || c == '\t' || c == '\r'; c = this.fData[++this.fCurrentOffset]) {}
            this.fEntityContent.offset = this.fCurrentOffset;
            if (c == '\0' && this.fCurrentOffset == this.fEntityContent.endOffset) {
                this.endOfContentWithinMarkup();
                this.fDTDParams.resetPEReferenceName();
            }
            else {
                if (c != '%') {
                    break;
                }
                final XMLName peReferenceName = this.fDTDParams.getPEReferenceName();
                peReferenceName.offset = ++this.fCurrentOffset;
                while (c != ';') {
                    c = this.fData[++this.fCurrentOffset];
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
        final char[][] fDataStack = new char[n2][];
        System.arraycopy(this.fDataStack, 0, fDataStack, 0, n);
        this.fDataStack = fDataStack;
        final int[] fCurrentOffsetStack = new int[n2];
        System.arraycopy(this.fCurrentOffsetStack, 0, fCurrentOffsetStack, 0, n);
        this.fCurrentOffsetStack = fCurrentOffsetStack;
        final int[] fEntityStateStack = new int[n2];
        System.arraycopy(this.fEntityStateStack, 0, fEntityStateStack, 0, n);
        this.fEntityStateStack = fEntityStateStack;
    }
    
    private UTF16ExternalSubsetScanner() {
    }
}
