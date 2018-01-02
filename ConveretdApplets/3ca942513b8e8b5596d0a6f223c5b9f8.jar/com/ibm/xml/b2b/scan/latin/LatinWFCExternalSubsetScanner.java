// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.latin;

import com.ibm.xml.b2b.util.QName;
import com.ibm.xml.b2b.scan.DocumentEntityState;
import com.ibm.xml.b2b.scan.MarkupDeclHandler;
import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.DTDParams;
import com.ibm.xml.b2b.scan.ExternalSubsetHandler;

public final class LatinWFCExternalSubsetScanner extends LatinWFCMarkupDeclScanner
{
    private static final int ENTITYSTATE_DTD_EXTERNAL_SUBSET = 0;
    private static final int ENTITYSTATE_PE_BETWEEN_MARKUP = 1;
    private static final int ENTITYSTATE_PE_WITHIN_MARKUP = 2;
    private ExternalSubsetHandler fHandler;
    private LatinWFCScannerSupport fHelper;
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
    
    public LatinWFCExternalSubsetScanner(final ExternalSubsetHandler fHandler, final LatinWFCScannerSupport fHelper) {
        this.fHandler = fHandler;
        this.fHelper = fHelper;
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
        this.fData = fEntityContent.bytes;
        this.fCurrentOffset = fEntityContent.offset;
        this.fEntityState = fEntityState;
        ++this.fEntityDepth;
    }
    
    private boolean scanExtSubsetDecl() {
        final int fIncludeSectDepth = this.fIncludeSectDepth;
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
                this.fEntityContent.offset = ++this.fCurrentOffset;
                if (!this.fHelper.scanPEReference(this.fEntityContent, peName)) {
                    this.fDTDParams.resetPEName();
                    return false;
                }
                this.fHandler.externalSubsetPEReference(peName);
                this.fDTDParams.resetPEName();
                this.fCurrentOffset = this.fEntityContent.offset;
            }
            else if (b == 93) {
                if (this.fData[this.fCurrentOffset + 1] != 93 || this.fData[this.fCurrentOffset + 2] != 62) {
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
                if (b != 0 || this.fCurrentOffset != this.fEntityContent.endOffset) {
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
        final byte b = this.fData[this.fCurrentOffset];
        if (b == 33) {
            final byte b2 = this.fData[++this.fCurrentOffset];
            if (b2 == 69 && this.fData[this.fCurrentOffset + 1] == 76 && this.fData[this.fCurrentOffset + 2] == 69 && this.fData[this.fCurrentOffset + 3] == 77 && this.fData[this.fCurrentOffset + 4] == 69 && this.fData[this.fCurrentOffset + 5] == 78 && this.fData[this.fCurrentOffset + 6] == 84) {
                this.fCurrentOffset += 7;
                return this.scanElementDecl();
            }
            if (b2 == 65 && this.fData[this.fCurrentOffset + 1] == 84 && this.fData[this.fCurrentOffset + 2] == 84 && this.fData[this.fCurrentOffset + 3] == 76 && this.fData[this.fCurrentOffset + 4] == 73 && this.fData[this.fCurrentOffset + 5] == 83 && this.fData[this.fCurrentOffset + 6] == 84) {
                this.fCurrentOffset += 7;
                return this.scanAttlistDecl();
            }
            if (b2 == 69 && this.fData[this.fCurrentOffset + 1] == 78 && this.fData[this.fCurrentOffset + 2] == 84 && this.fData[this.fCurrentOffset + 3] == 73 && this.fData[this.fCurrentOffset + 4] == 84 && this.fData[this.fCurrentOffset + 5] == 89) {
                this.fCurrentOffset += 6;
                return this.scanEntityDecl();
            }
            if (b2 == 78 && this.fData[this.fCurrentOffset + 1] == 79 && this.fData[this.fCurrentOffset + 2] == 84 && this.fData[this.fCurrentOffset + 3] == 65 && this.fData[this.fCurrentOffset + 4] == 84 && this.fData[this.fCurrentOffset + 5] == 73 && this.fData[this.fCurrentOffset + 6] == 79 && this.fData[this.fCurrentOffset + 7] == 78) {
                this.fCurrentOffset += 8;
                return this.scanNotationDecl();
            }
            if (b2 == 45) {
                if (this.fData[++this.fCurrentOffset] != 45) {
                    this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 27);
                    return false;
                }
                this.fEntityContent.offset = ++this.fCurrentOffset;
                if (!LatinWFCMarkupDeclScanner.scanComment(this.fHandler, this.fHelper, this.fDTDParams, this.fEntityContent)) {
                    return false;
                }
                this.fCurrentOffset = this.fEntityContent.offset;
                return true;
            }
            else if (b2 == 91) {
                ++this.fCurrentOffset;
                final int fEntityDepth = this.fEntityDepth;
                if (!this.checkForPEReference()) {
                    return false;
                }
                final byte b3 = this.fData[this.fCurrentOffset];
                if (b3 == 73 && this.fData[this.fCurrentOffset + 1] == 78 && this.fData[this.fCurrentOffset + 2] == 67 && this.fData[this.fCurrentOffset + 3] == 76 && this.fData[this.fCurrentOffset + 4] == 85 && this.fData[this.fCurrentOffset + 5] == 68 && this.fData[this.fCurrentOffset + 6] == 69) {
                    this.fCurrentOffset += 7;
                    if (!this.checkForPEReference()) {
                        return false;
                    }
                    if (this.fEntityDepth != fEntityDepth) {}
                    if (this.fData[this.fCurrentOffset] == 91) {
                        ++this.fCurrentOffset;
                        ++this.fIncludeSectDepth;
                        return true;
                    }
                }
                else if (b3 == 73 && this.fData[this.fCurrentOffset + 1] == 71 && this.fData[this.fCurrentOffset + 2] == 78 && this.fData[this.fCurrentOffset + 3] == 79 && this.fData[this.fCurrentOffset + 4] == 82 && this.fData[this.fCurrentOffset + 5] == 69) {
                    this.fCurrentOffset += 6;
                    if (!this.checkForPEReference()) {
                        return false;
                    }
                    if (this.fData[this.fCurrentOffset] == 91) {
                        ++this.fCurrentOffset;
                        return this.scanIgnoreSectContents();
                    }
                }
            }
        }
        else if (b == 63) {
            this.fEntityContent.offset = ++this.fCurrentOffset;
            if (!LatinWFCMarkupDeclScanner.scanPI(this.fHandler, this.fHelper, this.fDTDParams, this.fEntityContent)) {
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
                final byte b = this.fData[this.fCurrentOffset];
                if (b >= 0) {
                    switch (DocumentEntityState.contentMap[b]) {
                        case 1:
                        case 3: {
                            ++this.fCurrentOffset;
                            continue;
                        }
                        case 2: {
                            if (this.fData[this.fCurrentOffset + 1] == 33 && this.fData[this.fCurrentOffset + 2] == 91) {
                                this.fCurrentOffset += 3;
                                ++n;
                                continue;
                            }
                            ++this.fCurrentOffset;
                            continue;
                        }
                        case 4: {
                            if (this.fData[this.fCurrentOffset + 1] != 93 || this.fData[this.fCurrentOffset + 2] != 62) {
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
        final byte b = this.fData[this.fCurrentOffset];
        if (b == 65 && this.fData[this.fCurrentOffset + 1] == 78 && this.fData[this.fCurrentOffset + 2] == 89) {
            this.fCurrentOffset += 3;
            this.fHandler.contentModelANY();
        }
        else if (b == 69 && this.fData[this.fCurrentOffset + 1] == 77 && this.fData[this.fCurrentOffset + 2] == 80 && this.fData[this.fCurrentOffset + 3] == 84 && this.fData[this.fCurrentOffset + 4] == 89) {
            this.fCurrentOffset += 5;
            this.fHandler.contentModelEMPTY();
        }
        else {
            if (b != 40) {
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
            if (this.fData[this.fCurrentOffset] == 35 && this.fData[this.fCurrentOffset + 1] == 80 && this.fData[this.fCurrentOffset + 2] == 67 && this.fData[this.fCurrentOffset + 3] == 68 && this.fData[this.fCurrentOffset + 4] == 65 && this.fData[this.fCurrentOffset + 5] == 84 && this.fData[this.fCurrentOffset + 6] == 65) {
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
        if (this.fData[this.fCurrentOffset] != 62) {
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
            final byte b = this.fData[this.fCurrentOffset];
            if (b != 124) {
                if (b != 41) {
                    this.fHelper.setParameter(0, xmlString);
                    this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 29);
                    return false;
                }
                final byte b2 = this.fData[++this.fCurrentOffset];
                this.fHandler.contentModelEndGroup();
                --this.fMarkupDepth;
                if (b2 == 42) {
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
            final byte b = this.fData[this.fCurrentOffset];
            if (b == 41) {
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
                return true;
            }
            if (b == 124) {
                if (n2 == 1) {
                    this.fHelper.setParameter(0, xmlString);
                    this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 27);
                    return false;
                }
                n2 = 0;
            }
            else {
                if (b != 44) {
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
        if (this.fData[this.fCurrentOffset] == 40) {
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
        final byte b = this.fData[this.fCurrentOffset];
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
            if (this.fData[this.fCurrentOffset] == 62) {
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
            int n = (this.fData[this.fCurrentOffset] == 40) ? 1 : 0;
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
                    if (this.fData[this.fCurrentOffset] != 40) {
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
                byte b;
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
                    b = this.fData[this.fCurrentOffset];
                } while (b == 124);
                if (b != 41) {
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
            byte b2 = this.fData[this.fCurrentOffset];
            XMLString attributeDefaultType;
            boolean b4;
            if (b2 == 35) {
                fdtdParams2 = this.fDTDParams;
                attributeDefaultType = fdtdParams2.getAttributeDefaultType();
                attributeDefaultType.offset = this.fCurrentOffset;
                final byte b3 = this.fData[++this.fCurrentOffset];
                if (b3 == 70 && this.fData[this.fCurrentOffset + 1] == 73 && this.fData[this.fCurrentOffset + 2] == 88 && this.fData[this.fCurrentOffset + 3] == 69 && this.fData[this.fCurrentOffset + 4] == 68) {
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
                    b4 = true;
                    b2 = this.fData[this.fCurrentOffset];
                }
                else {
                    if (b3 == 73 && this.fData[this.fCurrentOffset + 1] == 77 && this.fData[this.fCurrentOffset + 2] == 80 && this.fData[this.fCurrentOffset + 3] == 76 && this.fData[this.fCurrentOffset + 4] == 73 && this.fData[this.fCurrentOffset + 5] == 69 && this.fData[this.fCurrentOffset + 6] == 68) {
                        this.fCurrentOffset += 7;
                    }
                    else {
                        if (b3 != 82 || this.fData[this.fCurrentOffset + 1] != 69 || this.fData[this.fCurrentOffset + 2] != 81 || this.fData[this.fCurrentOffset + 3] != 85 || this.fData[this.fCurrentOffset + 4] != 73 || this.fData[this.fCurrentOffset + 5] != 82 || this.fData[this.fCurrentOffset + 6] != 69 || this.fData[this.fCurrentOffset + 7] != 68) {
                            this.fHelper.setParameter(0, elementType);
                            this.fHelper.setParameter(1, attributeName);
                            this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 38);
                            return false;
                        }
                        this.fCurrentOffset += 8;
                    }
                    attributeDefaultType.endOffset = this.fCurrentOffset;
                    b4 = false;
                    b2 = this.fData[this.fCurrentOffset];
                }
            }
            else {
                attributeDefaultType = null;
                b4 = true;
            }
            if (b4) {
                final byte b5 = b2;
                if (b5 != 39 && b5 != 34) {
                    this.fHelper.setParameter(0, elementType);
                    this.fHelper.setParameter(1, attributeName);
                    this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 24);
                    return false;
                }
                ++this.fCurrentOffset;
                this.fHandler.startDefaultAttValue();
                this.fHelper.startDefaultAttValue(elementType, attributeName);
                this.fEntityContent.offset = this.fCurrentOffset;
                if (!LatinWFCMarkupDeclScanner.scanDefaultAttValue(this.fHandler, this.fHelper, this.fDTDParams, this.fEntityContent, b5)) {
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
        final byte b = this.fData[this.fCurrentOffset];
        if (b == 67 && this.fData[this.fCurrentOffset + 1] == 68 && this.fData[this.fCurrentOffset + 2] == 65 && this.fData[this.fCurrentOffset + 3] == 84 && this.fData[this.fCurrentOffset + 4] == 65) {
            this.fCurrentOffset += 5;
        }
        else if (b == 73 && this.fData[this.fCurrentOffset + 1] == 68) {
            if (this.fData[this.fCurrentOffset + 2] == 82 && this.fData[this.fCurrentOffset + 3] == 69 && this.fData[this.fCurrentOffset + 4] == 70) {
                if (this.fData[this.fCurrentOffset + 5] == 83) {
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
        else if (b == 69 && this.fData[this.fCurrentOffset + 1] == 78 && this.fData[this.fCurrentOffset + 2] == 84 && this.fData[this.fCurrentOffset + 3] == 73 && this.fData[this.fCurrentOffset + 4] == 84) {
            if (this.fData[this.fCurrentOffset + 5] == 89) {
                this.fCurrentOffset += 6;
            }
            else {
                if (this.fData[this.fCurrentOffset + 5] != 73 || this.fData[this.fCurrentOffset + 6] != 69 || this.fData[this.fCurrentOffset + 7] != 83) {
                    return false;
                }
                this.fCurrentOffset += 8;
            }
        }
        else if (b == 78 && this.fData[this.fCurrentOffset + 1] == 77 && this.fData[this.fCurrentOffset + 2] == 84 && this.fData[this.fCurrentOffset + 3] == 79 && this.fData[this.fCurrentOffset + 4] == 75 && this.fData[this.fCurrentOffset + 5] == 69 && this.fData[this.fCurrentOffset + 6] == 78) {
            if (this.fData[this.fCurrentOffset + 7] == 83) {
                this.fCurrentOffset += 8;
            }
            else {
                this.fCurrentOffset += 7;
            }
        }
        else {
            if (b != 78 || this.fData[this.fCurrentOffset + 1] != 79 || this.fData[this.fCurrentOffset + 2] != 84 || this.fData[this.fCurrentOffset + 3] != 65 || this.fData[this.fCurrentOffset + 4] != 84 || this.fData[this.fCurrentOffset + 5] != 73 || this.fData[this.fCurrentOffset + 6] != 79 || this.fData[this.fCurrentOffset + 7] != 78) {
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
        final byte b2 = this.fData[this.fCurrentOffset];
        int n;
        if (b2 == 32 || b2 == 10 || b2 == 9 || b2 == 13) {
            byte b3;
            do {
                b3 = this.fData[++this.fCurrentOffset];
            } while (b3 == 32 || b3 == 10 || b3 == 9 || b3 == 13);
            if (b3 != 37) {
                n = 0;
            }
            else {
                final byte b4 = this.fData[++this.fCurrentOffset];
                if (b4 == 32 || b4 == 10 || b4 == 9 || b4 == 13) {
                    final byte b5 = this.fData[++this.fCurrentOffset];
                    if (!this.checkForPEReference()) {
                        return false;
                    }
                    n = 1;
                }
                else if (b4 == 37) {
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
            if (b2 != 37) {
                this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 50);
                return false;
            }
            final byte b6 = this.fData[++this.fCurrentOffset];
            if (b6 == 32 || b6 == 10 || b6 == 9 || b6 == 13) {
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
                        byte b7;
                        for (b7 = this.fData[this.fCurrentOffset]; b7 == 32 || b7 == 10 || b7 == 9 || b7 == 13; b7 = this.fData[++this.fCurrentOffset]) {}
                        this.fEntityContent.offset = this.fCurrentOffset;
                        if (b7 == 0 && this.fCurrentOffset == this.fEntityContent.endOffset) {
                            if (!this.endOfContentWithinMarkup()) {
                                return false;
                            }
                            this.fDTDParams.resetPEReferenceName();
                        }
                        else {
                            if (b7 != 37) {
                                break Label_0604;
                            }
                            ++this.fCurrentOffset;
                            if (n != 0) {
                                break;
                            }
                            final byte b8 = this.fData[this.fCurrentOffset];
                            if (b8 == 32 || b8 == 10 || b8 == 9 || b8 == 13) {
                                ++this.fCurrentOffset;
                                if (!this.checkForPEReference()) {
                                    return false;
                                }
                                n = 1;
                                break Label_0604;
                            }
                            else {
                                n = ((b8 == 37) ? 1 : 0);
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
        final byte b9 = this.fData[this.fCurrentOffset];
        if (b9 == 34 || b9 == 39) {
            final byte b10 = b9;
            ++this.fCurrentOffset;
            this.fHandler.startEntityValue();
            this.fEntityContent.offset = this.fCurrentOffset;
            if (!LatinWFCMarkupDeclScanner.scanEntityValue(this.fHandler, this.fHelper, this.fDTDParams, this.fEntityContent, b10, false)) {
                return false;
            }
            this.fCurrentOffset = this.fEntityContent.offset;
            if (!this.checkForPEReference()) {
                return false;
            }
            if (this.fData[this.fCurrentOffset] != 62) {
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
            if (b9 == 80 && this.fData[this.fCurrentOffset + 1] == 85 && this.fData[this.fCurrentOffset + 2] == 66 && this.fData[this.fCurrentOffset + 3] == 76 && this.fData[this.fCurrentOffset + 4] == 73 && this.fData[this.fCurrentOffset + 5] == 67) {
                this.fCurrentOffset += 6;
                if (!this.scanRequiredWhitespace()) {
                    this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 60);
                    return false;
                }
                if (!this.checkForPEReference()) {
                    return false;
                }
                final byte b11 = this.fData[this.fCurrentOffset];
                if (b11 != 34 && b11 != 39) {
                    this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 10);
                    return false;
                }
                this.fEntityContent.offset = ++this.fCurrentOffset;
                fdtdParams = this.fDTDParams;
                publicID = fdtdParams.getPublicID();
                if (!this.fHelper.scanPublicID(this.fEntityContent, b11, publicID)) {
                    return false;
                }
                this.fCurrentOffset = this.fEntityContent.offset;
            }
            else {
                if (b9 != 83 || this.fData[this.fCurrentOffset + 1] != 89 || this.fData[this.fCurrentOffset + 2] != 83 || this.fData[this.fCurrentOffset + 3] != 84 || this.fData[this.fCurrentOffset + 4] != 69 || this.fData[this.fCurrentOffset + 5] != 77) {
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
            final byte b12 = this.fData[this.fCurrentOffset];
            if (b12 != 34 && b12 != 39) {
                this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 8);
                return false;
            }
            this.fEntityContent.offset = ++this.fCurrentOffset;
            final DTDParams fdtdParams3 = this.fDTDParams;
            final XMLString systemID = fdtdParams3.getSystemID();
            if (!this.fHelper.scanSystemID(this.fEntityContent, b12, systemID)) {
                return false;
            }
            this.fCurrentOffset = this.fEntityContent.offset;
            final boolean scanRequiredWhitespace = this.scanRequiredWhitespace();
            if (!this.checkForPEReference()) {
                return false;
            }
            final byte b13 = this.fData[this.fCurrentOffset];
            if (b13 == 62) {
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
                if (b13 != 78 || this.fData[this.fCurrentOffset + 1] != 68 || this.fData[this.fCurrentOffset + 2] != 65 || this.fData[this.fCurrentOffset + 3] != 84 || this.fData[this.fCurrentOffset + 4] != 65) {
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
                if (this.fData[this.fCurrentOffset] != 62) {
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
        final byte b = this.fData[this.fCurrentOffset];
        XMLString publicID;
        if (b == 80 && this.fData[this.fCurrentOffset + 1] == 85 && this.fData[this.fCurrentOffset + 2] == 66 && this.fData[this.fCurrentOffset + 3] == 76 && this.fData[this.fCurrentOffset + 4] == 73 && this.fData[this.fCurrentOffset + 5] == 67) {
            this.fCurrentOffset += 6;
            if (!this.scanRequiredWhitespace()) {
                this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 60);
                return false;
            }
            if (!this.checkForPEReference()) {
                return false;
            }
            final byte b2 = this.fData[this.fCurrentOffset];
            if (b2 != 34 && b2 != 39) {
                this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 10);
                return false;
            }
            this.fEntityContent.offset = ++this.fCurrentOffset;
            fdtdParams = this.fDTDParams;
            publicID = fdtdParams.getPublicID();
            if (!this.fHelper.scanPublicID(this.fEntityContent, b2, publicID)) {
                return false;
            }
            this.fCurrentOffset = this.fEntityContent.offset;
        }
        else {
            if (b != 83 || this.fData[this.fCurrentOffset + 1] != 89 || this.fData[this.fCurrentOffset + 2] != 83 || this.fData[this.fCurrentOffset + 3] != 84 || this.fData[this.fCurrentOffset + 4] != 69 || this.fData[this.fCurrentOffset + 5] != 77) {
                this.fHelper.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006", 59);
                return false;
            }
            this.fCurrentOffset += 6;
            publicID = null;
        }
        XMLString systemID;
        if (this.fData[this.fCurrentOffset] == 62) {
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
            final byte b3 = this.fData[this.fCurrentOffset];
            if (b3 == 34 || b3 == 39) {
                this.fEntityContent.offset = ++this.fCurrentOffset;
                fdtdParams2 = this.fDTDParams;
                systemID = fdtdParams2.getSystemID();
                if (!this.fHelper.scanSystemID(this.fEntityContent, b3, systemID)) {
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
        if (this.fData[this.fCurrentOffset] != 62) {
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
        final byte b = this.fData[this.fCurrentOffset];
        if (b == 32 || b == 10 || b == 9 || b == 13) {
            return true;
        }
        if (b == 37) {
            return true;
        }
        this.fEntityContent.offset = this.fCurrentOffset;
        return b == 0 && this.fCurrentOffset == this.fEntityContent.endOffset;
    }
    
    private boolean checkForPEReference() {
        while (true) {
            byte b;
            for (b = this.fData[this.fCurrentOffset]; b == 32 || b == 10 || b == 9 || b == 13; b = this.fData[++this.fCurrentOffset]) {}
            this.fEntityContent.offset = this.fCurrentOffset;
            if (b == 0 && this.fCurrentOffset == this.fEntityContent.endOffset) {
                if (!this.endOfContentWithinMarkup()) {
                    return false;
                }
                this.fDTDParams.resetPEReferenceName();
            }
            else {
                if (b != 37) {
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
    
    private LatinWFCExternalSubsetScanner() {
    }
}
