// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan.latin;

import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.QName;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.EncodingSupport;
import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.scan.DocumentEntityState;
import com.ibm.xml.b2b.scan.DocumentImplementationHandler;
import com.ibm.xml.b2b.scan.DocumentEventHandler;

public final class LatinWFCDocumentScanner
{
    private static final byte[] markupMap;
    
    public static boolean scanDocument(final DocumentEventHandler documentEventHandler, final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity) {
        final byte[] bytes = parsedEntity.bytes;
        int n = parsedEntity.offset;
        int n2 = 0;
        while (true) {
            byte b;
            for (b = bytes[n]; b == 32 || b == 10 || b == 9; b = bytes[++n]) {}
            if (b == 60) {
                final byte b2 = bytes[n + 1];
                if (b2 != 33) {
                    if (b2 != 63) {
                        if (b2 == 0) {
                            documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 11);
                            return false;
                        }
                        parsedEntity.offset = n;
                        if (!scanContent(documentEventHandler, documentImplementationHandler, documentEntityState, parsedEntity)) {
                            return false;
                        }
                        int offset = parsedEntity.offset;
                        while (true) {
                            final byte b3 = bytes[offset];
                            if (b3 == 0 && offset == parsedEntity.endOffset) {
                                return true;
                            }
                            if (b3 == 32 || b3 == 10 || b3 == 9) {
                                ++offset;
                            }
                            else if (b3 == 60) {
                                final byte b4 = bytes[++offset];
                                if (b4 == 63) {
                                    parsedEntity.offset = offset + 1;
                                    if (!scanPI(documentEventHandler, documentEntityState, parsedEntity)) {
                                        return false;
                                    }
                                    offset = parsedEntity.offset;
                                }
                                else {
                                    if (b4 != 33) {
                                        documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 12);
                                        return false;
                                    }
                                    if (bytes[++offset] != 45) {
                                        documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 12);
                                        return false;
                                    }
                                    if (bytes[offset + 1] != 45) {
                                        documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 27);
                                        return false;
                                    }
                                    parsedEntity.offset = offset + 2;
                                    if (!scanComment(documentImplementationHandler, documentEntityState, parsedEntity)) {
                                        return false;
                                    }
                                    offset = parsedEntity.offset;
                                }
                            }
                            else {
                                if (b3 == 38) {
                                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 12);
                                    return false;
                                }
                                parsedEntity.offset = offset;
                                documentEntityState.setInvalidCharParameter(0, parsedEntity);
                                documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 3);
                                return false;
                            }
                        }
                    }
                    else {
                        parsedEntity.offset = n + 2;
                        if (!scanPI(documentEventHandler, documentEntityState, parsedEntity)) {
                            return false;
                        }
                        n = parsedEntity.offset;
                    }
                }
                else {
                    final byte b5 = bytes[n + 2];
                    if (b5 == 45) {
                        if (bytes[n + 3] != 45) {
                            documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 27);
                            return false;
                        }
                        parsedEntity.offset = n + 4;
                        if (!scanComment(documentImplementationHandler, documentEntityState, parsedEntity)) {
                            return false;
                        }
                        n = parsedEntity.offset;
                    }
                    else {
                        if (n2 != 0 || b5 != 68 || bytes[n + 3] != 79 || bytes[n + 4] != 67 || bytes[n + 5] != 84 || bytes[n + 6] != 89 || bytes[n + 7] != 80 || bytes[n + 8] != 69) {
                            documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 11);
                            return false;
                        }
                        n2 = 1;
                        parsedEntity.offset = n + 9;
                        if (documentImplementationHandler != null) {
                            if (!documentImplementationHandler.scanDoctypeDecl(parsedEntity)) {
                                return false;
                            }
                            if (!documentImplementationHandler.scanExternalSubset()) {
                                return false;
                            }
                        }
                        else if (!skipDoctypeDecl(documentEntityState, parsedEntity)) {
                            return false;
                        }
                        n = parsedEntity.offset;
                    }
                }
            }
            else {
                if (b == 38) {
                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 11);
                    return false;
                }
                if (b == 0 && n == parsedEntity.endOffset) {
                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 0);
                    return false;
                }
                parsedEntity.offset = n;
                documentEntityState.setInvalidCharParameter(0, parsedEntity);
                documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 4);
                return false;
            }
        }
    }
    
    public static boolean scanContent(final DocumentEventHandler documentEventHandler, final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity) {
        final byte[] contentMap = DocumentEntityState.contentMap;
        final byte[] markupMap = LatinWFCDocumentScanner.markupMap;
        final int elementDepth = documentEntityState.elementDepth;
        final byte[] bytes = parsedEntity.bytes;
        int n = parsedEntity.offset;
        final EncodingSupport encoding = parsedEntity.encoding;
        if (elementDepth == 0) {
            final byte b = bytes[n + 1];
            if (b < 0 || markupMap[b] == 1) {
                parsedEntity.offset = n + 1;
                if (!scanStartElement(documentEventHandler, documentImplementationHandler, documentEntityState, parsedEntity)) {
                    return false;
                }
                if (documentEntityState.elementDepth == 0 && elementDepth == 0) {
                    return true;
                }
                n = parsedEntity.offset;
            }
        }
        int n2 = n;
    Label_0109:
        while (true) {
            while (true) {
                byte b2 = bytes[n];
                while (true) {
                    if (b2 >= 0) {
                        final byte b3 = contentMap[b2];
                        if (b3 == 1) {
                            b2 = bytes[++n];
                        }
                        else {
                            if (n > n2) {
                                final XMLString content = documentEntityState.content;
                                content.setValues(bytes, n2, n, encoding);
                                documentEventHandler.characters(content);
                            }
                            switch (b3) {
                                case 2: {
                                    final byte b4 = bytes[++n];
                                    switch ((b4 >= 0) ? markupMap[b4] : 1) {
                                        case 1: {
                                            parsedEntity.offset = n;
                                            if (!scanStartElement(documentEventHandler, documentImplementationHandler, documentEntityState, parsedEntity)) {
                                                return false;
                                            }
                                            break;
                                        }
                                        case 2: {
                                            parsedEntity.offset = n + 1;
                                            if (documentEntityState.elementDepth == elementDepth) {
                                                documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 19);
                                                return false;
                                            }
                                            if (!scanEndElement(documentEventHandler, documentImplementationHandler, documentEntityState, parsedEntity)) {
                                                return false;
                                            }
                                            if (documentEntityState.elementDepth == 0 && elementDepth == 0) {
                                                return true;
                                            }
                                            break;
                                        }
                                        case 3: {
                                            parsedEntity.offset = n + 1;
                                            if (!scanPI(documentEventHandler, documentEntityState, parsedEntity)) {
                                                return false;
                                            }
                                            break;
                                        }
                                        case 4: {
                                            final byte b5 = bytes[++n];
                                            if (b5 == 45) {
                                                if (bytes[n + 1] != 45) {
                                                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 27);
                                                    return false;
                                                }
                                                parsedEntity.offset = n + 2;
                                                if (!scanComment(documentImplementationHandler, documentEntityState, parsedEntity)) {
                                                    return false;
                                                }
                                                break;
                                            }
                                            else {
                                                if (b5 != 91 || bytes[n + 1] != 67 || bytes[n + 2] != 68 || bytes[n + 3] != 65 || bytes[n + 4] != 84 || bytes[n + 5] != 65 || bytes[n + 6] != 91) {
                                                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 19);
                                                    return false;
                                                }
                                                parsedEntity.offset = n + 7;
                                                if (!scanCDSect(documentEventHandler, documentImplementationHandler, documentEntityState, parsedEntity)) {
                                                    return false;
                                                }
                                                break;
                                            }
                                            break;
                                        }
                                        default: {
                                            documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 19);
                                            return false;
                                        }
                                    }
                                    n = (n2 = parsedEntity.offset);
                                    continue Label_0109;
                                }
                                case 3: {
                                    parsedEntity.offset = n + 1;
                                    if (!checkReferenceInContent(documentEventHandler, documentImplementationHandler, documentEntityState, parsedEntity)) {
                                        return false;
                                    }
                                    n = (n2 = parsedEntity.offset);
                                    continue Label_0109;
                                }
                                case 4: {
                                    if (bytes[n + 1] == 93 && bytes[n + 2] == 62) {
                                        documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 5);
                                        return false;
                                    }
                                    n2 = n++;
                                    continue Label_0109;
                                }
                                case 5: {
                                    if (n != parsedEntity.endOffset) {
                                        break Label_0109;
                                    }
                                    if (elementDepth > 0) {
                                        parsedEntity.offset = n;
                                        return true;
                                    }
                                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 19);
                                    break Label_0109;
                                }
                                default: {
                                    break Label_0109;
                                }
                            }
                        }
                    }
                    else {
                        parsedEntity.offset = n;
                        if (!parsedEntity.skipValidCharacter()) {
                            documentEntityState.setInvalidCharParameter(0, parsedEntity);
                            documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 2);
                            return false;
                        }
                        n = parsedEntity.offset;
                        b2 = bytes[n];
                    }
                }
            }
            break;
        }
        parsedEntity.offset = n;
        documentEntityState.setInvalidCharParameter(0, parsedEntity);
        documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 2);
        return false;
    }
    
    public static boolean scanAttValue(final DocumentEventHandler documentEventHandler, final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity, final int n) {
        final byte[] attValueMap = DocumentEntityState.attValueMap;
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        final EncodingSupport encoding = parsedEntity.encoding;
        int offset2 = offset;
    Label_0030:
        while (true) {
            while (true) {
                byte b = bytes[offset];
                boolean b2 = true;
                while (true) {
                    if (b >= 0) {
                        final byte b3 = attValueMap[b];
                        if (b3 == 1) {
                            b = bytes[++offset];
                        }
                        else if (b3 == 6) {
                            b2 = false;
                            b = bytes[++offset];
                        }
                        else {
                            if (offset > offset2) {
                                final XMLString content = documentEntityState.content;
                                content.setValues(bytes, offset2, offset, encoding);
                                documentEntityState.attributeValueCharacters(content, b2);
                            }
                            switch (b3) {
                                case 4: {
                                    if (b == n) {
                                        parsedEntity.offset = offset + 1;
                                        return true;
                                    }
                                    offset2 = offset++;
                                    continue Label_0030;
                                }
                                case 3: {
                                    parsedEntity.offset = offset + 1;
                                    if (!checkReferenceInAttValue(documentEventHandler, documentImplementationHandler, documentEntityState, parsedEntity)) {
                                        return false;
                                    }
                                    offset = (offset2 = parsedEntity.offset);
                                    continue Label_0030;
                                }
                                case 2: {
                                    documentEntityState.setParameter(0, documentEntityState.currentElement);
                                    documentEntityState.setParameter(1, documentEntityState.currentAttribute);
                                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 25);
                                    return false;
                                }
                                case 5: {
                                    if (offset != parsedEntity.endOffset) {
                                        break Label_0030;
                                    }
                                    if (n == 0) {
                                        parsedEntity.offset = offset;
                                        return true;
                                    }
                                    documentEntityState.setParameter(0, documentEntityState.currentElement);
                                    documentEntityState.setParameter(1, documentEntityState.currentAttribute);
                                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 26);
                                    return false;
                                }
                                default: {
                                    break Label_0030;
                                }
                            }
                        }
                    }
                    else {
                        parsedEntity.offset = offset;
                        if (!parsedEntity.skipValidCharacter()) {
                            documentEntityState.setParameter(0, documentEntityState.currentElement);
                            documentEntityState.setParameter(1, documentEntityState.currentAttribute);
                            documentEntityState.setInvalidCharParameter(2, parsedEntity);
                            documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 21);
                            return false;
                        }
                        offset = parsedEntity.offset;
                        b = bytes[offset];
                    }
                }
            }
            break;
        }
        parsedEntity.offset = offset;
        documentEntityState.setParameter(0, documentEntityState.currentElement);
        documentEntityState.setParameter(1, documentEntityState.currentAttribute);
        documentEntityState.setInvalidCharParameter(2, parsedEntity);
        documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 21);
        return false;
    }
    
    private static boolean scanStartElement(final DocumentEventHandler documentEventHandler, final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity) {
        final QName elementType = documentEntityState.elementType;
        final byte[] bytes = parsedEntity.bytes;
        final int scanQName = documentEntityState.scanQName(parsedEntity, elementType);
        int offset = parsedEntity.offset;
        boolean b = false;
        boolean b2 = false;
        switch (scanQName) {
            case 3: {
                b = false;
                b2 = true;
                break;
            }
            case 4: {
                b = false;
                b2 = false;
                break;
            }
            case 5: {
                byte b3;
                do {
                    b3 = bytes[++offset];
                } while (b3 == 32 || b3 == 10 || b3 == 9 || b3 == 13);
                b = (!(b2 = (b3 == 47)) && b3 != 62);
                break;
            }
            case 2:
            case 6: {
                documentEntityState.setParameter(0, elementType);
                documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 15);
                return false;
            }
            default: {
                documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 19);
                return false;
            }
            case 1: {
                documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 49);
                return false;
            }
        }
        documentEntityState.processElementType();
        while (b) {
            final QName attributeName = documentEntityState.attributeName;
            parsedEntity.offset = offset;
            final int scanQName2 = documentEntityState.scanQName(parsedEntity, attributeName);
            int offset2 = parsedEntity.offset;
            switch (scanQName2) {
                case 6: {
                    break;
                }
                case 5: {
                    byte b4;
                    do {
                        b4 = bytes[++offset2];
                    } while (b4 == 32 || b4 == 10 || b4 == 9 || b4 == 13);
                    if (b4 == 61) {
                        break;
                    }
                }
                case 2:
                case 3:
                case 4: {
                    documentEntityState.setParameter(0, elementType);
                    documentEntityState.setParameter(1, attributeName);
                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 16);
                    return false;
                }
                default: {
                    documentEntityState.setParameter(0, elementType);
                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 15);
                    return false;
                }
                case 1: {
                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 49);
                    return false;
                }
            }
            documentEntityState.processAttributeName(attributeName, true);
            byte b5 = bytes[++offset2];
            if (b5 != 39 && b5 != 34) {
                while (b5 == 32 || b5 == 10 || b5 == 9 || b5 == 13) {
                    b5 = bytes[++offset2];
                }
                if (b5 != 39 && b5 != 34) {
                    documentEntityState.setParameter(0, elementType);
                    documentEntityState.setParameter(1, attributeName);
                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 24);
                    return false;
                }
            }
            final byte b6 = b5;
            parsedEntity.offset = offset2 + 1;
            if (!scanAttValue(documentEventHandler, documentImplementationHandler, documentEntityState, parsedEntity, b6)) {
                return false;
            }
            offset = parsedEntity.offset;
            byte b7 = bytes[offset];
            final boolean b8 = b7 == 32 || b7 == 10 || b7 == 9 || b7 == 13;
            if (b8) {
                do {
                    b7 = bytes[++offset];
                } while (b7 == 32 || b7 == 10 || b7 == 9 || b7 == 13);
            }
            if ((b2 = (b7 == 47)) || b7 == 62) {
                documentEntityState.endOfSpecifiedAttributes();
                break;
            }
            if (!b8) {
                documentEntityState.setParameter(0, elementType);
                documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 15);
                return false;
            }
        }
        if (b2) {
            if (bytes[offset + 1] != 62) {
                documentEntityState.setParameter(0, elementType);
                documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 15);
                return false;
            }
            parsedEntity.offset = offset + 2;
            documentEventHandler.startElementEvent(true);
            documentEntityState.endNamespacesScope();
        }
        else {
            parsedEntity.offset = offset + 1;
            documentEventHandler.startElementEvent(false);
            documentEntityState.pushElement();
        }
        return true;
    }
    
    private static boolean scanEndElement(final DocumentEventHandler documentEventHandler, final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity) {
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        final QName popElement = documentEntityState.popElement();
        if (popElement == null) {
            documentEntityState.setParameter(0, documentEntityState.currentElement);
            documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 20);
            return false;
        }
        if (popElement.bytes == bytes) {
            int i = popElement.offset;
            while (i < popElement.endOffset) {
                if (bytes[i++] != bytes[offset++]) {
                    final QName qName = new QName();
                    documentEntityState.scanQName(parsedEntity, qName);
                    documentEntityState.setParameter(0, qName);
                    documentEntityState.setParameter(1, popElement);
                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 14);
                    return false;
                }
            }
            byte b = bytes[offset];
            if (b != 62) {
                while (b == 32 || b == 10 || b == 9 || b == 13) {
                    b = bytes[++offset];
                }
                if (b != 62) {
                    documentEntityState.setParameter(0, popElement);
                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 18);
                    return false;
                }
            }
            parsedEntity.offset = offset + 1;
            documentEventHandler.endElementEvent();
            documentEntityState.endNamespacesScope();
            return true;
        }
        documentEntityState.setParameter(0, popElement);
        documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 20);
        return false;
    }
    
    private static boolean scanComment(final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity) {
        final XMLString content = documentEntityState.content;
        if (documentEntityState.scanComment(parsedEntity, content)) {
            if (documentImplementationHandler != null) {
                documentImplementationHandler.comment(content);
            }
            return true;
        }
        return false;
    }
    
    private static boolean scanPI(final DocumentEventHandler documentEventHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity) {
        final XMLName target = documentEntityState.target;
        final XMLString content = documentEntityState.content;
        if (documentEntityState.scanPITarget(parsedEntity, target) && documentEntityState.scanPIData(parsedEntity, content)) {
            documentEventHandler.processingInstruction(target, content);
            return true;
        }
        return false;
    }
    
    private static boolean scanCDSect(final DocumentEventHandler documentEventHandler, final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity) {
        final byte[] contentMap = DocumentEntityState.contentMap;
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        final EncodingSupport encoding = parsedEntity.encoding;
        final XMLString content = documentEntityState.content;
        if (documentImplementationHandler != null) {
            documentImplementationHandler.startCDATA();
        }
        final int n = offset;
        byte b = bytes[offset];
        while (true) {
            if (b >= 0) {
                switch (contentMap[b]) {
                    case 1:
                    case 2:
                    case 3: {
                        b = bytes[++offset];
                        continue;
                    }
                    case 4: {
                        if (bytes[offset + 1] == 93 && bytes[offset + 2] == 62) {
                            if (offset > n) {
                                content.setValues(bytes, n, offset, encoding);
                                documentEventHandler.characters(content);
                            }
                            if (documentImplementationHandler != null) {
                                documentImplementationHandler.endCDATA();
                            }
                            parsedEntity.offset = offset + 3;
                            return true;
                        }
                        b = bytes[++offset];
                        continue;
                    }
                    case 5: {
                        if (offset > n) {
                            content.setValues(bytes, n, offset, encoding);
                            documentEventHandler.characters(content);
                        }
                        if (offset == parsedEntity.endOffset) {
                            documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 6);
                            return false;
                        }
                        parsedEntity.offset = offset;
                        documentEntityState.setInvalidCharParameter(0, parsedEntity);
                        documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 1);
                        return false;
                    }
                    default: {
                        if (offset > n) {
                            content.setValues(bytes, n, offset, encoding);
                            documentEventHandler.characters(content);
                        }
                        parsedEntity.offset = offset;
                        documentEntityState.setInvalidCharParameter(0, parsedEntity);
                        documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 1);
                        return false;
                    }
                }
            }
            else {
                parsedEntity.offset = offset;
                if (!parsedEntity.skipValidCharacter()) {
                    documentEntityState.setInvalidCharParameter(0, parsedEntity);
                    documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 1);
                    return false;
                }
                offset = parsedEntity.offset;
                b = bytes[offset];
            }
        }
    }
    
    public static boolean skipDoctypeDecl(final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity) {
        final byte[] bytes = parsedEntity.bytes;
        int offset = parsedEntity.offset;
        final QName elementType = documentEntityState.elementType;
        final byte b = bytes[offset];
        if (b != 32 && b != 10 && b != 9) {
            documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 11);
            return false;
        }
        byte b2;
        do {
            b2 = bytes[++offset];
        } while (b2 == 32 || b2 == 10 || b2 == 9);
        parsedEntity.offset = offset;
        final int scanQName = documentEntityState.scanQName(parsedEntity, elementType);
        int offset2 = parsedEntity.offset;
        if (scanQName == 0) {
            documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 11);
            return false;
        }
        while (b2 != 91) {
            if (b2 == 62) {
                parsedEntity.offset = offset2 + 1;
                return true;
            }
            if (b2 == 0) {
                documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 11);
                return false;
            }
            b2 = bytes[++offset2];
        }
        byte b3;
        do {
            b3 = bytes[++offset2];
            if (b3 == 93) {
                do {
                    b3 = bytes[++offset2];
                } while (b3 == 32 || b3 == 10 || b3 == 9);
                if (b3 == 62) {
                    parsedEntity.offset = offset2 + 1;
                    return true;
                }
                continue;
            }
        } while (b3 != 0);
        documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 11);
        return false;
    }
    
    private static boolean checkReferenceInContent(final DocumentEventHandler documentEventHandler, final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity) {
        if (parsedEntity.bytes[parsedEntity.offset] == 35) {
            ++parsedEntity.offset;
            final int scanCharacterReference = documentEntityState.scanCharacterReference(parsedEntity);
            if (scanCharacterReference != -1) {
                documentEventHandler.character(scanCharacterReference, false);
                return true;
            }
            return false;
        }
        else {
            final XMLName entityName = documentEntityState.entityName;
            final int scanName = documentEntityState.scanName(parsedEntity, entityName);
            if (scanName == 7) {
                ++parsedEntity.offset;
                return entityReferenceInContent(documentEventHandler, documentImplementationHandler, documentEntityState, entityName);
            }
            if (scanName == 0) {
                documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 39);
                return false;
            }
            documentEntityState.setParameter(0, entityName);
            documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 40);
            return false;
        }
    }
    
    private static boolean checkReferenceInAttValue(final DocumentEventHandler documentEventHandler, final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final ParsedEntity parsedEntity) {
        if (parsedEntity.bytes[parsedEntity.offset] == 35) {
            ++parsedEntity.offset;
            final int scanCharacterReference = documentEntityState.scanCharacterReference(parsedEntity);
            if (scanCharacterReference != -1) {
                documentEntityState.attributeValueCharacter(scanCharacterReference, false);
                return true;
            }
            return false;
        }
        else {
            final XMLName entityName = documentEntityState.entityName;
            final int scanName = documentEntityState.scanName(parsedEntity, entityName);
            if (scanName == 7) {
                ++parsedEntity.offset;
                return entityReferenceInAttValue(documentImplementationHandler, documentEntityState, entityName);
            }
            if (scanName == 0) {
                documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 39);
                return false;
            }
            documentEntityState.setParameter(0, entityName);
            documentEntityState.reportFatalError("http://www.w3.org/TR/2000/REC-xml-20001006#NT-document", 40);
            return false;
        }
    }
    
    private static boolean entityReferenceInContent(final DocumentEventHandler documentEventHandler, final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final XMLName xmlName) {
        if (documentImplementationHandler != null) {
            return documentImplementationHandler.entityReferenceInContent(xmlName);
        }
        final int checkPredefinedEntities = documentEntityState.checkPredefinedEntities(xmlName);
        if (checkPredefinedEntities != -1) {
            documentEventHandler.character(checkPredefinedEntities, true);
            return true;
        }
        documentEntityState.undeclaredEntityInContent(xmlName);
        return false;
    }
    
    private static boolean entityReferenceInAttValue(final DocumentImplementationHandler documentImplementationHandler, final DocumentEntityState documentEntityState, final XMLName xmlName) {
        if (documentImplementationHandler != null) {
            return documentImplementationHandler.entityReferenceInAttValue(xmlName);
        }
        final int checkPredefinedEntities = documentEntityState.checkPredefinedEntities(xmlName);
        if (checkPredefinedEntities != -1) {
            documentEntityState.attributeValueCharacter(checkPredefinedEntities, true);
            return true;
        }
        documentEntityState.undeclaredEntityInAttValue(xmlName);
        return false;
    }
    
    static {
        markupMap = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 3, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0 };
    }
}
