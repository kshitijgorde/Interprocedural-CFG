// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.utf16;

import com.ibm.xml.b2b.util.QName;
import com.ibm.xml.b2b.scan.DocumentEntityState;
import com.ibm.xml.b2b.scan.MarkupDeclHandler;
import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.DTDParams;
import com.ibm.xml.b2b.scan.ExternalSubsetHandler;

public final class UTF16WFCExternalSubsetScanner extends UTF16WFCMarkupDeclScanner
{
    private static final int ENTITYSTATE_DTD_EXTERNAL_SUBSET = 0;
    private static final int ENTITYSTATE_PE_BETWEEN_MARKUP = 1;
    private static final int ENTITYSTATE_PE_WITHIN_MARKUP = 2;
    private ExternalSubsetHandler fHandler;
    private UTF16WFCScannerSupport fHelper;
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
    
    public UTF16WFCExternalSubsetScanner(final ExternalSubsetHandler fHandler, final UTF16WFCScannerSupport fHelper) {
        this.fHandler = fHandler;
        this.fHelper = fHelper;
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
        return this.endExternalSubset(this.scanExtSubsetDecl());
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
        final int fIncludeSectDepth = this.fIncludeSectDepth;
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
                this.fEntityContent.offset = ++this.fCurrentOffset;
                if (!this.fHelper.scanPEReference(this.fEntityContent, peName)) {
                    this.fDTDParams.resetPEName();
                    return false;
                }
                this.fHandler.externalSubsetPEReference(peName);
                this.fDTDParams.resetPEName();
                this.fCurrentOffset = this.fEntityContent.offset;
            }
            else if (c == ']') {
                if (this.fData[this.fCurrentOffset + 1] != ']' || this.fData[this.fCurrentOffset + 2] != '>') {
                    this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 19);
                    return false;
                }
                this.fCurrentOffset += 3;
                if (this.fIncludeSectDepth <= fIncludeSectDepth) {
                    this.fHelper.setParameter(0, new XMLString("unknown"));
                    this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 101);
                    return false;
                }
                --this.fIncludeSectDepth;
            }
            else {
                this.fEntityContent.offset = this.fCurrentOffset;
                if (c != '\0' || this.fCurrentOffset != this.fEntityContent.endOffset) {
                    this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 19);
                    return false;
                }
                if (this.fEntityDepth == fEntityDepth) {
                    if (this.fIncludeSectDepth > fIncludeSectDepth) {
                        this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 46);
                        return false;
                    }
                    return true;
                }
                else {
                    if (this.fEntityState != 2) {
                        this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 19);
                        return false;
                    }
                    this.endOfContentWithinMarkup();
                    this.fDTDParams.resetPEReferenceName();
                }
            }
        }
    }
    
    private boolean scanMarkupDecl() {
        final char c = this.fData[this.fCurrentOffset];
        if (c == '!') {
            final char c2 = this.fData[++this.fCurrentOffset];
            if (c2 == 'E' && this.fData[this.fCurrentOffset + 1] == 'L' && this.fData[this.fCurrentOffset + 2] == 'E' && this.fData[this.fCurrentOffset + 3] == 'M' && this.fData[this.fCurrentOffset + 4] == 'E' && this.fData[this.fCurrentOffset + 5] == 'N' && this.fData[this.fCurrentOffset + 6] == 'T') {
                this.fCurrentOffset += 7;
                return this.scanElementDecl();
            }
            if (c2 == 'A' && this.fData[this.fCurrentOffset + 1] == 'T' && this.fData[this.fCurrentOffset + 2] == 'T' && this.fData[this.fCurrentOffset + 3] == 'L' && this.fData[this.fCurrentOffset + 4] == 'I' && this.fData[this.fCurrentOffset + 5] == 'S' && this.fData[this.fCurrentOffset + 6] == 'T') {
                this.fCurrentOffset += 7;
                return this.scanAttlistDecl();
            }
            if (c2 == 'E' && this.fData[this.fCurrentOffset + 1] == 'N' && this.fData[this.fCurrentOffset + 2] == 'T' && this.fData[this.fCurrentOffset + 3] == 'I' && this.fData[this.fCurrentOffset + 4] == 'T' && this.fData[this.fCurrentOffset + 5] == 'Y') {
                this.fCurrentOffset += 6;
                return this.scanEntityDecl();
            }
            if (c2 == 'N' && this.fData[this.fCurrentOffset + 1] == 'O' && this.fData[this.fCurrentOffset + 2] == 'T' && this.fData[this.fCurrentOffset + 3] == 'A' && this.fData[this.fCurrentOffset + 4] == 'T' && this.fData[this.fCurrentOffset + 5] == 'I' && this.fData[this.fCurrentOffset + 6] == 'O' && this.fData[this.fCurrentOffset + 7] == 'N') {
                this.fCurrentOffset += 8;
                return this.scanNotationDecl();
            }
            if (c2 == '-') {
                if (this.fData[++this.fCurrentOffset] != '-') {
                    this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 27);
                    return false;
                }
                this.fEntityContent.offset = ++this.fCurrentOffset;
                if (!UTF16WFCMarkupDeclScanner.scanComment(this.fHandler, this.fHelper, this.fDTDParams, this.fEntityContent)) {
                    return false;
                }
                this.fCurrentOffset = this.fEntityContent.offset;
                return true;
            }
            else if (c2 == '[') {
                ++this.fCurrentOffset;
                final int fEntityDepth = this.fEntityDepth;
                if (!this.checkForPEReference()) {
                    return false;
                }
                final char c3 = this.fData[this.fCurrentOffset];
                if (c3 == 'I' && this.fData[this.fCurrentOffset + 1] == 'N' && this.fData[this.fCurrentOffset + 2] == 'C' && this.fData[this.fCurrentOffset + 3] == 'L' && this.fData[this.fCurrentOffset + 4] == 'U' && this.fData[this.fCurrentOffset + 5] == 'D' && this.fData[this.fCurrentOffset + 6] == 'E') {
                    this.fCurrentOffset += 7;
                    if (!this.checkForPEReference()) {
                        return false;
                    }
                    if (this.fEntityDepth != fEntityDepth) {}
                    if (this.fData[this.fCurrentOffset] == '[') {
                        ++this.fCurrentOffset;
                        ++this.fIncludeSectDepth;
                        return true;
                    }
                }
                else if (c3 == 'I' && this.fData[this.fCurrentOffset + 1] == 'G' && this.fData[this.fCurrentOffset + 2] == 'N' && this.fData[this.fCurrentOffset + 3] == 'O' && this.fData[this.fCurrentOffset + 4] == 'R' && this.fData[this.fCurrentOffset + 5] == 'E') {
                    this.fCurrentOffset += 6;
                    if (!this.checkForPEReference()) {
                        return false;
                    }
                    if (this.fData[this.fCurrentOffset] == '[') {
                        ++this.fCurrentOffset;
                        return this.scanIgnoreSectContents();
                    }
                }
            }
        }
        else if (c == '?') {
            this.fEntityContent.offset = ++this.fCurrentOffset;
            if (!UTF16WFCMarkupDeclScanner.scanPI(this.fHandler, this.fHelper, this.fDTDParams, this.fEntityContent)) {
                return false;
            }
            this.fCurrentOffset = this.fEntityContent.offset;
            return true;
        }
        this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 19);
        return false;
    }
    
    private boolean scanIgnoreSectContents() {
        int n = 1;
        Label_0296: {
        Label_0225:
            while (true) {
                final char c = this.fData[this.fCurrentOffset];
                if (c < '\u0080') {
                    switch (DocumentEntityState.contentMap[c]) {
                        case 1:
                        case 3: {
                            ++this.fCurrentOffset;
                            continue;
                        }
                        case 2: {
                            if (this.fData[this.fCurrentOffset + 1] == '!' && this.fData[this.fCurrentOffset + 2] == '[') {
                                this.fCurrentOffset += 3;
                                ++n;
                                continue;
                            }
                            ++this.fCurrentOffset;
                            continue;
                        }
                        case 4: {
                            if (this.fData[this.fCurrentOffset + 1] != ']' || this.fData[this.fCurrentOffset + 2] != '>') {
                                ++this.fCurrentOffset;
                                continue;
                            }
                            this.fCurrentOffset += 3;
                            if (--n == 0) {
                                return true;
                            }
                            continue;
                        }
                        case 5: {
                            if (this.fCurrentOffset == this.fEntityContent.endOffset) {
                                this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 47);
                                return false;
                            }
                            break Label_0225;
                        }
                        default: {
                            break Label_0225;
                        }
                    }
                }
                else {
                    this.fEntityContent.offset = this.fCurrentOffset;
                    if (!this.fEntityContent.skipValidCharacter()) {
                        break Label_0296;
                    }
                    this.fCurrentOffset = this.fEntityContent.offset;
                }
            }
            this.fEntityContent.offset = this.fCurrentOffset;
            this.fHelper.setInvalidCharParameter(0, this.fEntityContent);
            this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 7);
            return false;
        }
        this.fHelper.setInvalidCharParameter(0, this.fEntityContent);
        this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 7);
        return false;
    }
    
    private boolean scanElementDecl() {
        if (!this.scanRequiredWhitespace()) {
            this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 21);
            return false;
        }
        if (!this.checkForPEReference()) {
            return false;
        }
        this.fEntityContent.offset = this.fCurrentOffset;
        final DTDParams fdtdParams = this.fDTDParams;
        final QName elementType = fdtdParams.getElementType();
        if (this.fHelper.scanQName(this.fEntityContent, elementType) == 0) {
            this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 22);
            return false;
        }
        this.fHandler.startElementDecl(elementType);
        this.fCurrentOffset = this.fEntityContent.offset;
        if (!this.scanRequiredWhitespace()) {
            this.fHelper.setParameter(0, elementType);
            this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 23);
            return false;
        }
        if (!this.checkForPEReference()) {
            return false;
        }
        final char c = this.fData[this.fCurrentOffset];
        if (c == 'A' && this.fData[this.fCurrentOffset + 1] == 'N' && this.fData[this.fCurrentOffset + 2] == 'Y') {
            this.fCurrentOffset += 3;
            this.fHandler.contentModelANY();
        }
        else if (c == 'E' && this.fData[this.fCurrentOffset + 1] == 'M' && this.fData[this.fCurrentOffset + 2] == 'P' && this.fData[this.fCurrentOffset + 3] == 'T' && this.fData[this.fCurrentOffset + 4] == 'Y') {
            this.fCurrentOffset += 5;
            this.fHandler.contentModelEMPTY();
        }
        else {
            if (c != '(') {
                this.fHelper.setParameter(0, elementType);
                this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 24);
                return false;
            }
            ++this.fCurrentOffset;
            ++this.fMarkupDepth;
            this.fHandler.contentModelStartGroup();
            if (!this.checkForPEReference()) {
                return false;
            }
            if (this.fData[this.fCurrentOffset] == '#' && this.fData[this.fCurrentOffset + 1] == 'P' && this.fData[this.fCurrentOffset + 2] == 'C' && this.fData[this.fCurrentOffset + 3] == 'D' && this.fData[this.fCurrentOffset + 4] == 'A' && this.fData[this.fCurrentOffset + 5] == 'T' && this.fData[this.fCurrentOffset + 6] == 'A') {
                this.fCurrentOffset += 7;
                this.fHandler.contentModelPCDATA();
                if (!this.scanMixed(elementType)) {
                    return false;
                }
            }
            else if (!this.scanChildren(elementType, 1)) {
                return false;
            }
        }
        if (!this.checkForPEReference()) {
            return false;
        }
        if (this.fData[this.fCurrentOffset] != '>') {
            this.fHelper.setParameter(0, elementType);
            this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 25);
            return false;
        }
        this.fHandler.endElementDecl();
        fdtdParams.resetElementType();
        ++this.fCurrentOffset;
        return true;
    }
    
    private boolean scanMixed(final XMLString xmlString) {
        int n = 0;
        while (this.checkForPEReference()) {
            final char c = this.fData[this.fCurrentOffset];
            if (c != '|') {
                if (c != ')') {
                    this.fHelper.setParameter(0, xmlString);
                    this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 29);
                    return false;
                }
                final char c2 = this.fData[++this.fCurrentOffset];
                this.fHandler.contentModelEndGroup();
                --this.fMarkupDepth;
                if (c2 == '*') {
                    ++this.fCurrentOffset;
                    this.fHandler.contentModelOccurrence(1);
                }
                else if (n != 0) {
                    this.fHelper.setParameter(0, xmlString);
                    this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 30);
                    return false;
                }
                return true;
            }
            else {
                ++this.fCurrentOffset;
                this.fHandler.contentModelSeparator(0);
                n = 1;
                if (!this.checkForPEReference()) {
                    return false;
                }
                this.fEntityContent.offset = this.fCurrentOffset;
                final QName contentModelElement = this.fDTDParams.getContentModelElement();
                if (this.fHelper.scanQName(this.fEntityContent, contentModelElement) == 0) {
                    this.fHelper.setParameter(0, xmlString);
                    this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 28);
                    return false;
                }
                this.fHandler.contentModelElement(contentModelElement);
                this.fDTDParams.resetContentModelElement();
                this.fCurrentOffset = this.fEntityContent.offset;
            }
        }
        return false;
    }
    
    private boolean scanChildren(final XMLString xmlString, final int n) {
        int n2 = -1;
        while (this.scanCp(xmlString, n)) {
            if (!this.checkForPEReference()) {
                return false;
            }
            final char c = this.fData[this.fCurrentOffset];
            if (c == ')') {
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
                return true;
            }
            if (c == '|') {
                if (n2 == 1) {
                    this.fHelper.setParameter(0, xmlString);
                    this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 27);
                    return false;
                }
                n2 = 0;
            }
            else {
                if (c != ',') {
                    this.fHelper.setParameter(0, xmlString);
                    this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 27);
                    return false;
                }
                if (n2 == 0) {
                    this.fHelper.setParameter(0, xmlString);
                    this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 27);
                    return false;
                }
                n2 = 1;
            }
            ++this.fCurrentOffset;
            this.fHandler.contentModelSeparator(n2);
            if (!this.checkForPEReference()) {
                return false;
            }
        }
        return false;
    }
    
    private boolean scanCp(final XMLString xmlString, final int n) {
        if (this.fData[this.fCurrentOffset] == '(') {
            ++this.fCurrentOffset;
            ++this.fMarkupDepth;
            this.fHandler.contentModelStartGroup();
            return this.checkForPEReference() && this.scanChildren(xmlString, n + 1);
        }
        this.fEntityContent.offset = this.fCurrentOffset;
        final QName contentModelElement = this.fDTDParams.getContentModelElement();
        if (this.fHelper.scanQName(this.fEntityContent, contentModelElement) == 0) {
            this.fHelper.setParameter(0, xmlString);
            this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 26);
            return false;
        }
        this.fHandler.contentModelElement(contentModelElement);
        this.fDTDParams.resetContentModelElement();
        this.fCurrentOffset = this.fEntityContent.offset;
        final char c = this.fData[this.fCurrentOffset];
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
        return true;
    }
    
    private boolean scanAttlistDecl() {
        DTDParams fdtdParams = null;
        DTDParams fdtdParams2 = null;
        if (!this.scanRequiredWhitespace()) {
            this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 31);
            return false;
        }
        if (!this.checkForPEReference()) {
            return false;
        }
        this.fEntityContent.offset = this.fCurrentOffset;
        final DTDParams fdtdParams3 = this.fDTDParams;
        final QName elementType = fdtdParams3.getElementType();
        if (this.fHelper.scanQName(this.fEntityContent, elementType) == 0) {
            this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 32);
            return false;
        }
        this.fHandler.startAttlistDecl(elementType);
        this.fCurrentOffset = this.fEntityContent.offset;
        while (true) {
            final boolean scanRequiredWhitespace = this.scanRequiredWhitespace();
            if (!this.checkForPEReference()) {
                return false;
            }
            if (this.fData[this.fCurrentOffset] == '>') {
                ++this.fCurrentOffset;
                this.fHandler.endAttlistDecl();
                fdtdParams3.resetElementType();
                return true;
            }
            if (!scanRequiredWhitespace) {
                this.fHelper.setParameter(0, elementType);
                this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 33);
                return false;
            }
            this.fEntityContent.offset = this.fCurrentOffset;
            final DTDParams fdtdParams4 = this.fDTDParams;
            final QName attributeName = fdtdParams4.getAttributeName();
            if (this.fHelper.scanQName(this.fEntityContent, attributeName) == 0) {
                this.fHelper.setParameter(0, elementType);
                this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 34);
                return false;
            }
            this.fCurrentOffset = this.fEntityContent.offset;
            if (!this.scanRequiredWhitespace()) {
                this.fHelper.setParameter(0, elementType);
                this.fHelper.setParameter(1, attributeName);
                this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 35);
                return false;
            }
            if (!this.checkForPEReference()) {
                return false;
            }
            int n = (this.fData[this.fCurrentOffset] == '(') ? 1 : 0;
            XMLString attributeType;
            if (n == 0) {
                fdtdParams = this.fDTDParams;
                attributeType = fdtdParams.getAttributeType();
                if (!this.scanAttType(attributeType)) {
                    this.fHelper.setParameter(0, elementType);
                    this.fHelper.setParameter(1, attributeName);
                    this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 36);
                    return false;
                }
                if (this.fHelper.isNotationType(attributeType)) {
                    if (!this.scanRequiredWhitespace()) {
                        this.fHelper.setParameter(0, elementType);
                        this.fHelper.setParameter(1, attributeName);
                        this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 39);
                        return false;
                    }
                    if (!this.checkForPEReference()) {
                        return false;
                    }
                    if (this.fData[this.fCurrentOffset] != '(') {
                        this.fHelper.setParameter(0, elementType);
                        this.fHelper.setParameter(1, attributeName);
                        this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 40);
                        return false;
                    }
                    n = 1;
                }
            }
            else {
                attributeType = null;
            }
            this.fHandler.startAttDef(attributeName, attributeType);
            if (n != 0) {
                this.fHandler.startEnumerationType();
                char c;
                do {
                    ++this.fCurrentOffset;
                    if (!this.checkForPEReference()) {
                        return false;
                    }
                    this.fEntityContent.offset = this.fCurrentOffset;
                    if (attributeType != null) {
                        final XMLName enumerationTypeName = this.fDTDParams.getEnumerationTypeName();
                        if (this.fHelper.scanName(this.fEntityContent, enumerationTypeName) == 0) {
                            this.fHelper.setParameter(0, elementType);
                            this.fHelper.setParameter(1, attributeName);
                            this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 41);
                            return false;
                        }
                        this.fHandler.enumerationType(enumerationTypeName);
                        this.fDTDParams.resetEnumerationTypeName();
                    }
                    else {
                        final XMLString enumerationTypeToken = this.fDTDParams.getEnumerationTypeToken();
                        if (this.fHelper.scanNmtoken(this.fEntityContent, enumerationTypeToken) == 0) {
                            this.fHelper.setParameter(0, elementType);
                            this.fHelper.setParameter(1, attributeName);
                            this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 43);
                            return false;
                        }
                        this.fHandler.enumerationType(enumerationTypeToken);
                        this.fDTDParams.resetEnumerationTypeToken();
                    }
                    this.fCurrentOffset = this.fEntityContent.offset;
                    if (!this.checkForPEReference()) {
                        return false;
                    }
                    c = this.fData[this.fCurrentOffset];
                } while (c == '|');
                if (c != ')') {
                    this.fHelper.setParameter(0, elementType);
                    this.fHelper.setParameter(1, attributeName);
                    if (attributeType != null) {
                        this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 42);
                    }
                    else {
                        this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 44);
                    }
                    return false;
                }
                ++this.fCurrentOffset;
                this.fHandler.endEnumerationType();
            }
            if (!this.scanRequiredWhitespace()) {
                this.fHelper.setParameter(0, elementType);
                this.fHelper.setParameter(1, attributeName);
                this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 37);
                return false;
            }
            if (!this.checkForPEReference()) {
                return false;
            }
            char c2 = this.fData[this.fCurrentOffset];
            XMLString attributeDefaultType;
            boolean b;
            if (c2 == '#') {
                fdtdParams2 = this.fDTDParams;
                attributeDefaultType = fdtdParams2.getAttributeDefaultType();
                attributeDefaultType.offset = this.fCurrentOffset;
                final char c3 = this.fData[++this.fCurrentOffset];
                if (c3 == 'F' && this.fData[this.fCurrentOffset + 1] == 'I' && this.fData[this.fCurrentOffset + 2] == 'X' && this.fData[this.fCurrentOffset + 3] == 'E' && this.fData[this.fCurrentOffset + 4] == 'D') {
                    this.fCurrentOffset += 5;
                    attributeDefaultType.endOffset = this.fCurrentOffset;
                    if (!this.scanRequiredWhitespace()) {
                        this.fHelper.setParameter(0, elementType);
                        this.fHelper.setParameter(1, attributeName);
                        this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 45);
                        return false;
                    }
                    if (!this.checkForPEReference()) {
                        return false;
                    }
                    b = true;
                    c2 = this.fData[this.fCurrentOffset];
                }
                else {
                    if (c3 == 'I' && this.fData[this.fCurrentOffset + 1] == 'M' && this.fData[this.fCurrentOffset + 2] == 'P' && this.fData[this.fCurrentOffset + 3] == 'L' && this.fData[this.fCurrentOffset + 4] == 'I' && this.fData[this.fCurrentOffset + 5] == 'E' && this.fData[this.fCurrentOffset + 6] == 'D') {
                        this.fCurrentOffset += 7;
                    }
                    else {
                        if (c3 != 'R' || this.fData[this.fCurrentOffset + 1] != 'E' || this.fData[this.fCurrentOffset + 2] != 'Q' || this.fData[this.fCurrentOffset + 3] != 'U' || this.fData[this.fCurrentOffset + 4] != 'I' || this.fData[this.fCurrentOffset + 5] != 'R' || this.fData[this.fCurrentOffset + 6] != 'E' || this.fData[this.fCurrentOffset + 7] != 'D') {
                            this.fHelper.setParameter(0, elementType);
                            this.fHelper.setParameter(1, attributeName);
                            this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 38);
                            return false;
                        }
                        this.fCurrentOffset += 8;
                    }
                    attributeDefaultType.endOffset = this.fCurrentOffset;
                    b = false;
                    c2 = this.fData[this.fCurrentOffset];
                }
            }
            else {
                attributeDefaultType = null;
                b = true;
            }
            if (b) {
                final char c4 = c2;
                if (c4 != '\'' && c4 != '\"') {
                    this.fHelper.setParameter(0, elementType);
                    this.fHelper.setParameter(1, attributeName);
                    this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 24);
                    return false;
                }
                ++this.fCurrentOffset;
                this.fHandler.startDefaultAttValue();
                this.fHelper.startDefaultAttValue(elementType, attributeName);
                this.fEntityContent.offset = this.fCurrentOffset;
                if (!UTF16WFCMarkupDeclScanner.scanDefaultAttValue(this.fHandler, this.fHelper, this.fDTDParams, this.fEntityContent, c4)) {
                    return false;
                }
                this.fCurrentOffset = this.fEntityContent.offset;
            }
            this.fHandler.endAttDef(attributeDefaultType);
            fdtdParams4.resetAttributeName();
            if (attributeType != null) {
                fdtdParams.resetAttributeType();
            }
            if (attributeDefaultType == null) {
                continue;
            }
            fdtdParams2.resetAttributeDefaultType();
        }
    }
    
    private boolean scanAttType(final XMLString xmlString) {
        xmlString.offset = this.fCurrentOffset;
        final char c = this.fData[this.fCurrentOffset];
        if (c == 'C' && this.fData[this.fCurrentOffset + 1] == 'D' && this.fData[this.fCurrentOffset + 2] == 'A' && this.fData[this.fCurrentOffset + 3] == 'T' && this.fData[this.fCurrentOffset + 4] == 'A') {
            this.fCurrentOffset += 5;
        }
        else if (c == 'I' && this.fData[this.fCurrentOffset + 1] == 'D') {
            if (this.fData[this.fCurrentOffset + 2] == 'R' && this.fData[this.fCurrentOffset + 3] == 'E' && this.fData[this.fCurrentOffset + 4] == 'F') {
                if (this.fData[this.fCurrentOffset + 5] == 'S') {
                    this.fCurrentOffset += 6;
                }
                else {
                    this.fCurrentOffset += 5;
                }
            }
            else {
                this.fCurrentOffset += 2;
            }
        }
        else if (c == 'E' && this.fData[this.fCurrentOffset + 1] == 'N' && this.fData[this.fCurrentOffset + 2] == 'T' && this.fData[this.fCurrentOffset + 3] == 'I' && this.fData[this.fCurrentOffset + 4] == 'T') {
            if (this.fData[this.fCurrentOffset + 5] == 'Y') {
                this.fCurrentOffset += 6;
            }
            else {
                if (this.fData[this.fCurrentOffset + 5] != 'I' || this.fData[this.fCurrentOffset + 6] != 'E' || this.fData[this.fCurrentOffset + 7] != 'S') {
                    return false;
                }
                this.fCurrentOffset += 8;
            }
        }
        else if (c == 'N' && this.fData[this.fCurrentOffset + 1] == 'M' && this.fData[this.fCurrentOffset + 2] == 'T' && this.fData[this.fCurrentOffset + 3] == 'O' && this.fData[this.fCurrentOffset + 4] == 'K' && this.fData[this.fCurrentOffset + 5] == 'E' && this.fData[this.fCurrentOffset + 6] == 'N') {
            if (this.fData[this.fCurrentOffset + 7] == 'S') {
                this.fCurrentOffset += 8;
            }
            else {
                this.fCurrentOffset += 7;
            }
        }
        else {
            if (c != 'N' || this.fData[this.fCurrentOffset + 1] != 'O' || this.fData[this.fCurrentOffset + 2] != 'T' || this.fData[this.fCurrentOffset + 3] != 'A' || this.fData[this.fCurrentOffset + 4] != 'T' || this.fData[this.fCurrentOffset + 5] != 'I' || this.fData[this.fCurrentOffset + 6] != 'O' || this.fData[this.fCurrentOffset + 7] != 'N') {
                return false;
            }
            this.fCurrentOffset += 8;
        }
        xmlString.endOffset = this.fCurrentOffset;
        return true;
    }
    
    private boolean scanEntityDecl() {
        DTDParams fdtdParams = null;
        boolean b = false;
        final char c = this.fData[this.fCurrentOffset];
        int n;
        if (c == ' ' || c == '\n' || c == '\t' || c == '\r') {
            char c2;
            do {
                c2 = this.fData[++this.fCurrentOffset];
            } while (c2 == ' ' || c2 == '\n' || c2 == '\t' || c2 == '\r');
            if (c2 != '%') {
                n = 0;
            }
            else {
                final char c3 = this.fData[++this.fCurrentOffset];
                if (c3 == ' ' || c3 == '\n' || c3 == '\t' || c3 == '\r') {
                    final char c4 = this.fData[++this.fCurrentOffset];
                    if (!this.checkForPEReference()) {
                        return false;
                    }
                    n = 1;
                }
                else if (c3 == '%') {
                    if (!this.checkForPEReference()) {
                        return false;
                    }
                    n = 1;
                }
                else {
                    b = true;
                    n = 0;
                }
            }
        }
        else {
            if (c != '%') {
                this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 50);
                return false;
            }
            final char c5 = this.fData[++this.fCurrentOffset];
            if (c5 == ' ' || c5 == '\n' || c5 == '\t' || c5 == '\r') {
                this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 51);
                return false;
            }
            b = true;
            n = 0;
        }
        Label_0604: {
            if (b) {
                while (true) {
                    final XMLName peReferenceName = this.fDTDParams.getPEReferenceName();
                    this.fEntityContent.offset = this.fCurrentOffset;
                    if (!this.fHelper.scanPEReference(this.fEntityContent, peReferenceName)) {
                        return false;
                    }
                    this.fCurrentOffset = this.fEntityContent.offset;
                    this.fHandler.startPEReferenceWithinMarkup(peReferenceName, this.fMarkupDepth);
                    while (true) {
                        char c6;
                        for (c6 = this.fData[this.fCurrentOffset]; c6 == ' ' || c6 == '\n' || c6 == '\t' || c6 == '\r'; c6 = this.fData[++this.fCurrentOffset]) {}
                        this.fEntityContent.offset = this.fCurrentOffset;
                        if (c6 == '\0' && this.fCurrentOffset == this.fEntityContent.endOffset) {
                            if (!this.endOfContentWithinMarkup()) {
                                return false;
                            }
                            this.fDTDParams.resetPEReferenceName();
                        }
                        else {
                            if (c6 != '%') {
                                break Label_0604;
                            }
                            ++this.fCurrentOffset;
                            if (n != 0) {
                                break;
                            }
                            final char c7 = this.fData[this.fCurrentOffset];
                            if (c7 == ' ' || c7 == '\n' || c7 == '\t' || c7 == '\r') {
                                ++this.fCurrentOffset;
                                if (!this.checkForPEReference()) {
                                    return false;
                                }
                                n = 1;
                                break Label_0604;
                            }
                            else {
                                n = ((c7 == '%') ? 1 : 0);
                                if (n != 0) {
                                    ++this.fCurrentOffset;
                                    break;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
        this.fEntityContent.offset = this.fCurrentOffset;
        final DTDParams fdtdParams2 = this.fDTDParams;
        final XMLName entityDeclName = fdtdParams2.getEntityDeclName();
        if (this.fHelper.scanName(this.fEntityContent, entityDeclName) == 0) {
            this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 53);
            return false;
        }
        this.fCurrentOffset = this.fEntityContent.offset;
        if (!this.scanRequiredWhitespace()) {
            this.fHelper.setParameter(0, entityDeclName);
            this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 54);
            return false;
        }
        if (!this.checkForPEReference()) {
            return false;
        }
        final char c8 = this.fData[this.fCurrentOffset];
        if (c8 == '\"' || c8 == '\'') {
            final char c9 = c8;
            ++this.fCurrentOffset;
            this.fHandler.startEntityValue();
            this.fEntityContent.offset = this.fCurrentOffset;
            if (!UTF16WFCMarkupDeclScanner.scanEntityValue(this.fHandler, this.fHelper, this.fDTDParams, this.fEntityContent, c9, false)) {
                return false;
            }
            this.fCurrentOffset = this.fEntityContent.offset;
            if (!this.checkForPEReference()) {
                return false;
            }
            if (this.fData[this.fCurrentOffset] != '>') {
                this.fHelper.setParameter(0, entityDeclName);
                this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 58);
                return false;
            }
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
        else {
            XMLString publicID;
            if (c8 == 'P' && this.fData[this.fCurrentOffset + 1] == 'U' && this.fData[this.fCurrentOffset + 2] == 'B' && this.fData[this.fCurrentOffset + 3] == 'L' && this.fData[this.fCurrentOffset + 4] == 'I' && this.fData[this.fCurrentOffset + 5] == 'C') {
                this.fCurrentOffset += 6;
                if (!this.scanRequiredWhitespace()) {
                    this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 60);
                    return false;
                }
                if (!this.checkForPEReference()) {
                    return false;
                }
                final char c10 = this.fData[this.fCurrentOffset];
                if (c10 != '\"' && c10 != '\'') {
                    this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 10);
                    return false;
                }
                this.fEntityContent.offset = ++this.fCurrentOffset;
                fdtdParams = this.fDTDParams;
                publicID = fdtdParams.getPublicID();
                if (!this.fHelper.scanPublicID(this.fEntityContent, c10, publicID)) {
                    return false;
                }
                this.fCurrentOffset = this.fEntityContent.offset;
            }
            else {
                if (c8 != 'S' || this.fData[this.fCurrentOffset + 1] != 'Y' || this.fData[this.fCurrentOffset + 2] != 'S' || this.fData[this.fCurrentOffset + 3] != 'T' || this.fData[this.fCurrentOffset + 4] != 'E' || this.fData[this.fCurrentOffset + 5] != 'M') {
                    this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 59);
                    return false;
                }
                this.fCurrentOffset += 6;
                publicID = null;
            }
            if (!this.scanRequiredWhitespace()) {
                this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 62);
                return false;
            }
            if (!this.checkForPEReference()) {
                return false;
            }
            final char c11 = this.fData[this.fCurrentOffset];
            if (c11 != '\"' && c11 != '\'') {
                this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 8);
                return false;
            }
            this.fEntityContent.offset = ++this.fCurrentOffset;
            final DTDParams fdtdParams3 = this.fDTDParams;
            final XMLString systemID = fdtdParams3.getSystemID();
            if (!this.fHelper.scanSystemID(this.fEntityContent, c11, systemID)) {
                return false;
            }
            this.fCurrentOffset = this.fEntityContent.offset;
            final boolean scanRequiredWhitespace = this.scanRequiredWhitespace();
            if (!this.checkForPEReference()) {
                return false;
            }
            final char c12 = this.fData[this.fCurrentOffset];
            if (c12 == '>') {
                ++this.fCurrentOffset;
                if (n != 0) {
                    this.fHandler.externalPEDecl(entityDeclName, publicID, systemID);
                }
                else {
                    this.fHandler.externalEntityDecl(entityDeclName, publicID, systemID);
                }
            }
            else {
                if (n != 0 || !scanRequiredWhitespace) {
                    this.fHelper.setParameter(0, entityDeclName);
                    this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 58);
                    return false;
                }
                if (c12 != 'N' || this.fData[this.fCurrentOffset + 1] != 'D' || this.fData[this.fCurrentOffset + 2] != 'A' || this.fData[this.fCurrentOffset + 3] != 'T' || this.fData[this.fCurrentOffset + 4] != 'A') {
                    this.fHelper.setParameter(0, entityDeclName);
                    this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 58);
                    return false;
                }
                this.fCurrentOffset += 5;
                if (!this.scanRequiredWhitespace()) {
                    this.fHelper.setParameter(0, entityDeclName);
                    this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 56);
                    return false;
                }
                if (!this.checkForPEReference()) {
                    return false;
                }
                this.fEntityContent.offset = this.fCurrentOffset;
                final DTDParams fdtdParams4 = this.fDTDParams;
                final XMLName notationName = fdtdParams4.getNotationName();
                if (this.fHelper.scanName(this.fEntityContent, notationName) == 0) {
                    this.fHelper.setParameter(0, entityDeclName);
                    this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 57);
                    return false;
                }
                this.fCurrentOffset = this.fEntityContent.offset;
                if (!this.checkForPEReference()) {
                    return false;
                }
                if (this.fData[this.fCurrentOffset] != '>') {
                    this.fHelper.setParameter(0, entityDeclName);
                    this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 58);
                    return false;
                }
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
    }
    
    private boolean scanNotationDecl() {
        DTDParams fdtdParams = null;
        DTDParams fdtdParams2 = null;
        if (!this.scanRequiredWhitespace()) {
            this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 64);
            return false;
        }
        if (!this.checkForPEReference()) {
            return false;
        }
        this.fEntityContent.offset = this.fCurrentOffset;
        final DTDParams fdtdParams3 = this.fDTDParams;
        final XMLName notationName = fdtdParams3.getNotationName();
        if (this.fHelper.scanName(this.fEntityContent, notationName) == 0) {
            this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 65);
            return false;
        }
        this.fCurrentOffset = this.fEntityContent.offset;
        if (!this.scanRequiredWhitespace()) {
            this.fHelper.setParameter(0, notationName);
            this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 66);
            return false;
        }
        if (!this.checkForPEReference()) {
            return false;
        }
        final char c = this.fData[this.fCurrentOffset];
        XMLString publicID;
        if (c == 'P' && this.fData[this.fCurrentOffset + 1] == 'U' && this.fData[this.fCurrentOffset + 2] == 'B' && this.fData[this.fCurrentOffset + 3] == 'L' && this.fData[this.fCurrentOffset + 4] == 'I' && this.fData[this.fCurrentOffset + 5] == 'C') {
            this.fCurrentOffset += 6;
            if (!this.scanRequiredWhitespace()) {
                this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 60);
                return false;
            }
            if (!this.checkForPEReference()) {
                return false;
            }
            final char c2 = this.fData[this.fCurrentOffset];
            if (c2 != '\"' && c2 != '\'') {
                this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 10);
                return false;
            }
            this.fEntityContent.offset = ++this.fCurrentOffset;
            fdtdParams = this.fDTDParams;
            publicID = fdtdParams.getPublicID();
            if (!this.fHelper.scanPublicID(this.fEntityContent, c2, publicID)) {
                return false;
            }
            this.fCurrentOffset = this.fEntityContent.offset;
        }
        else {
            if (c != 'S' || this.fData[this.fCurrentOffset + 1] != 'Y' || this.fData[this.fCurrentOffset + 2] != 'S' || this.fData[this.fCurrentOffset + 3] != 'T' || this.fData[this.fCurrentOffset + 4] != 'E' || this.fData[this.fCurrentOffset + 5] != 'M') {
                this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 59);
                return false;
            }
            this.fCurrentOffset += 6;
            publicID = null;
        }
        XMLString systemID;
        if (this.fData[this.fCurrentOffset] == '>') {
            systemID = null;
        }
        else {
            if (!this.scanRequiredWhitespace()) {
                this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 62);
                return false;
            }
            if (!this.checkForPEReference()) {
                return false;
            }
            final char c3 = this.fData[this.fCurrentOffset];
            if (c3 == '\"' || c3 == '\'') {
                this.fEntityContent.offset = ++this.fCurrentOffset;
                fdtdParams2 = this.fDTDParams;
                systemID = fdtdParams2.getSystemID();
                if (!this.fHelper.scanSystemID(this.fEntityContent, c3, systemID)) {
                    return false;
                }
                this.fCurrentOffset = this.fEntityContent.offset;
            }
            else {
                systemID = null;
            }
        }
        if (!this.checkForPEReference()) {
            return false;
        }
        if (this.fData[this.fCurrentOffset] != '>') {
            this.fHelper.setParameter(0, notationName);
            this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 67);
            return false;
        }
        ++this.fCurrentOffset;
        this.fHandler.notationDecl(notationName, publicID, systemID);
        fdtdParams3.resetNotationName();
        if (publicID != null) {
            fdtdParams.resetPublicID();
        }
        if (systemID != null) {
            fdtdParams2.resetSystemID();
        }
        return true;
    }
    
    private boolean scanRequiredWhitespace() {
        final char c = this.fData[this.fCurrentOffset];
        if (c == ' ' || c == '\n' || c == '\t' || c == '\r') {
            return true;
        }
        if (c == '%') {
            return true;
        }
        this.fEntityContent.offset = this.fCurrentOffset;
        return c == '\0' && this.fCurrentOffset == this.fEntityContent.endOffset;
    }
    
    private boolean checkForPEReference() {
        while (true) {
            char c;
            for (c = this.fData[this.fCurrentOffset]; c == ' ' || c == '\n' || c == '\t' || c == '\r'; c = this.fData[++this.fCurrentOffset]) {}
            this.fEntityContent.offset = this.fCurrentOffset;
            if (c == '\0' && this.fCurrentOffset == this.fEntityContent.endOffset) {
                if (!this.endOfContentWithinMarkup()) {
                    return false;
                }
                this.fDTDParams.resetPEReferenceName();
            }
            else {
                if (c != '%') {
                    return true;
                }
                this.fEntityContent.offset = ++this.fCurrentOffset;
                final XMLName peReferenceName = this.fDTDParams.getPEReferenceName();
                if (!this.fHelper.scanPEReference(this.fEntityContent, peReferenceName)) {
                    this.fDTDParams.resetPEReferenceName();
                    return false;
                }
                this.fCurrentOffset = this.fEntityContent.offset;
                this.fHandler.startPEReferenceWithinMarkup(peReferenceName, this.fMarkupDepth);
            }
        }
    }
    
    private boolean endExternalSubset(final boolean b) {
        --this.fEntityDepth;
        if (b && this.fEntityDepth != 0) {
            this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 18);
            return false;
        }
        return b;
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
    
    private boolean endOfContentWithinMarkup() {
        if (this.fEntityState != 2) {
            this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 18);
            return false;
        }
        --this.fEntityDepth;
        if (this.fEntityDepth == 0) {
            this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 18);
            return false;
        }
        this.fHandler.endPEReferenceWithinMarkup(this.fMarkupDepth);
        final int n = this.fEntityDepth - 1;
        this.fDTDParams = this.fDTDParamsStack[n];
        this.fEntityContent = this.fEntityContentStack[n];
        this.fData = this.fDataStack[n];
        this.fCurrentOffset = this.fCurrentOffsetStack[n];
        this.fEntityState = this.fEntityStateStack[n];
        return true;
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
    
    private UTF16WFCExternalSubsetScanner() {
    }
}
