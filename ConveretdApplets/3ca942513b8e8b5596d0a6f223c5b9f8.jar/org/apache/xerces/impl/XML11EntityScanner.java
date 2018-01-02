// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl;

import org.apache.xerces.util.XMLStringBuffer;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.xni.QName;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.util.XML11Char;
import java.io.IOException;

public class XML11EntityScanner extends XMLEntityScanner
{
    public int peekChar() throws IOException {
        if (super.fCurrentEntity.position == super.fCurrentEntity.count) {
            this.load(0, true);
        }
        final char c = super.fCurrentEntity.ch[super.fCurrentEntity.position];
        if (super.fCurrentEntity.isExternal()) {
            return (c != '\r' && c != '\u0085' && c != '\u2028') ? c : '\n';
        }
        return c;
    }
    
    public int scanChar() throws IOException {
        if (super.fCurrentEntity.position == super.fCurrentEntity.count) {
            this.load(0, true);
        }
        int n = super.fCurrentEntity.ch[super.fCurrentEntity.position++];
        boolean external = false;
        if (n == 10 || ((n == 13 || n == 133 || n == 8232) && (external = super.fCurrentEntity.isExternal()))) {
            final XMLEntityManager.ScannedEntity fCurrentEntity = super.fCurrentEntity;
            ++fCurrentEntity.lineNumber;
            super.fCurrentEntity.columnNumber = 1;
            if (super.fCurrentEntity.position == super.fCurrentEntity.count) {
                super.fCurrentEntity.ch[0] = (char)n;
                this.load(1, false);
            }
            if (n == 13 && external) {
                final char c = super.fCurrentEntity.ch[super.fCurrentEntity.position++];
                if (c != '\n' && c != '\u0085') {
                    final XMLEntityManager.ScannedEntity fCurrentEntity2 = super.fCurrentEntity;
                    --fCurrentEntity2.position;
                }
            }
            n = 10;
        }
        final XMLEntityManager.ScannedEntity fCurrentEntity3 = super.fCurrentEntity;
        ++fCurrentEntity3.columnNumber;
        return n;
    }
    
    public String scanNmtoken() throws IOException {
        if (super.fCurrentEntity.position == super.fCurrentEntity.count) {
            this.load(0, true);
        }
        int position = super.fCurrentEntity.position;
        while (true) {
            final char c = super.fCurrentEntity.ch[super.fCurrentEntity.position];
            if (XML11Char.isXML11Name(c)) {
                if (++super.fCurrentEntity.position != super.fCurrentEntity.count) {
                    continue;
                }
                final int n = super.fCurrentEntity.position - position;
                if (n == super.fCurrentEntity.ch.length) {
                    final char[] ch = new char[super.fCurrentEntity.ch.length << 1];
                    System.arraycopy(super.fCurrentEntity.ch, position, ch, 0, n);
                    super.fCurrentEntity.ch = ch;
                }
                else {
                    System.arraycopy(super.fCurrentEntity.ch, position, super.fCurrentEntity.ch, 0, n);
                }
                position = 0;
                if (this.load(n, false)) {
                    break;
                }
                continue;
            }
            else {
                if (!XML11Char.isXML11NameHighSurrogate(c)) {
                    break;
                }
                if (++super.fCurrentEntity.position == super.fCurrentEntity.count) {
                    final int n2 = super.fCurrentEntity.position - position;
                    if (n2 == super.fCurrentEntity.ch.length) {
                        final char[] ch2 = new char[super.fCurrentEntity.ch.length << 1];
                        System.arraycopy(super.fCurrentEntity.ch, position, ch2, 0, n2);
                        super.fCurrentEntity.ch = ch2;
                    }
                    else {
                        System.arraycopy(super.fCurrentEntity.ch, position, super.fCurrentEntity.ch, 0, n2);
                    }
                    position = 0;
                    if (this.load(n2, false)) {
                        final XMLEntityManager.ScannedEntity fCurrentEntity = super.fCurrentEntity;
                        --fCurrentEntity.startPosition;
                        final XMLEntityManager.ScannedEntity fCurrentEntity2 = super.fCurrentEntity;
                        --fCurrentEntity2.position;
                        break;
                    }
                }
                final char c2 = super.fCurrentEntity.ch[super.fCurrentEntity.position];
                if (!XMLChar.isLowSurrogate(c2) || !XML11Char.isXML11Name(XMLChar.supplemental(c, c2))) {
                    final XMLEntityManager.ScannedEntity fCurrentEntity3 = super.fCurrentEntity;
                    --fCurrentEntity3.position;
                    break;
                }
                if (++super.fCurrentEntity.position != super.fCurrentEntity.count) {
                    continue;
                }
                final int n3 = super.fCurrentEntity.position - position;
                if (n3 == super.fCurrentEntity.ch.length) {
                    final char[] ch3 = new char[super.fCurrentEntity.ch.length << 1];
                    System.arraycopy(super.fCurrentEntity.ch, position, ch3, 0, n3);
                    super.fCurrentEntity.ch = ch3;
                }
                else {
                    System.arraycopy(super.fCurrentEntity.ch, position, super.fCurrentEntity.ch, 0, n3);
                }
                position = 0;
                if (this.load(n3, false)) {
                    break;
                }
                continue;
            }
        }
        final int n4 = super.fCurrentEntity.position - position;
        final XMLEntityManager.ScannedEntity fCurrentEntity4 = super.fCurrentEntity;
        fCurrentEntity4.columnNumber += n4;
        String addSymbol = null;
        if (n4 > 0) {
            addSymbol = super.fSymbolTable.addSymbol(super.fCurrentEntity.ch, position, n4);
        }
        return addSymbol;
    }
    
    public String scanName() throws IOException {
        if (super.fCurrentEntity.position == super.fCurrentEntity.count) {
            this.load(0, true);
        }
        int position = super.fCurrentEntity.position;
        final char c = super.fCurrentEntity.ch[position];
        if (XML11Char.isXML11NameStart(c)) {
            if (++super.fCurrentEntity.position == super.fCurrentEntity.count) {
                super.fCurrentEntity.ch[0] = c;
                position = 0;
                if (this.load(1, false)) {
                    final XMLEntityManager.ScannedEntity fCurrentEntity = super.fCurrentEntity;
                    ++fCurrentEntity.columnNumber;
                    return super.fSymbolTable.addSymbol(super.fCurrentEntity.ch, 0, 1);
                }
            }
        }
        else {
            if (!XML11Char.isXML11NameHighSurrogate(c)) {
                return null;
            }
            if (++super.fCurrentEntity.position == super.fCurrentEntity.count) {
                super.fCurrentEntity.ch[0] = c;
                position = 0;
                if (this.load(1, false)) {
                    final XMLEntityManager.ScannedEntity fCurrentEntity2 = super.fCurrentEntity;
                    --fCurrentEntity2.position;
                    final XMLEntityManager.ScannedEntity fCurrentEntity3 = super.fCurrentEntity;
                    --fCurrentEntity3.startPosition;
                    return null;
                }
            }
            final char c2 = super.fCurrentEntity.ch[super.fCurrentEntity.position];
            if (!XMLChar.isLowSurrogate(c2) || !XML11Char.isXML11NameStart(XMLChar.supplemental(c, c2))) {
                final XMLEntityManager.ScannedEntity fCurrentEntity4 = super.fCurrentEntity;
                --fCurrentEntity4.position;
                return null;
            }
            if (++super.fCurrentEntity.position == super.fCurrentEntity.count) {
                super.fCurrentEntity.ch[0] = c;
                super.fCurrentEntity.ch[1] = c2;
                position = 0;
                if (this.load(2, false)) {
                    final XMLEntityManager.ScannedEntity fCurrentEntity5 = super.fCurrentEntity;
                    fCurrentEntity5.columnNumber += 2;
                    return super.fSymbolTable.addSymbol(super.fCurrentEntity.ch, 0, 2);
                }
            }
        }
        while (true) {
            final char c3 = super.fCurrentEntity.ch[super.fCurrentEntity.position];
            if (XML11Char.isXML11Name(c3)) {
                if (++super.fCurrentEntity.position != super.fCurrentEntity.count) {
                    continue;
                }
                final int n = super.fCurrentEntity.position - position;
                if (n == super.fCurrentEntity.ch.length) {
                    final char[] ch = new char[super.fCurrentEntity.ch.length << 1];
                    System.arraycopy(super.fCurrentEntity.ch, position, ch, 0, n);
                    super.fCurrentEntity.ch = ch;
                }
                else {
                    System.arraycopy(super.fCurrentEntity.ch, position, super.fCurrentEntity.ch, 0, n);
                }
                position = 0;
                if (this.load(n, false)) {
                    break;
                }
                continue;
            }
            else {
                if (!XML11Char.isXML11NameHighSurrogate(c3)) {
                    break;
                }
                if (++super.fCurrentEntity.position == super.fCurrentEntity.count) {
                    final int n2 = super.fCurrentEntity.position - position;
                    if (n2 == super.fCurrentEntity.ch.length) {
                        final char[] ch2 = new char[super.fCurrentEntity.ch.length << 1];
                        System.arraycopy(super.fCurrentEntity.ch, position, ch2, 0, n2);
                        super.fCurrentEntity.ch = ch2;
                    }
                    else {
                        System.arraycopy(super.fCurrentEntity.ch, position, super.fCurrentEntity.ch, 0, n2);
                    }
                    position = 0;
                    if (this.load(n2, false)) {
                        final XMLEntityManager.ScannedEntity fCurrentEntity6 = super.fCurrentEntity;
                        --fCurrentEntity6.position;
                        final XMLEntityManager.ScannedEntity fCurrentEntity7 = super.fCurrentEntity;
                        --fCurrentEntity7.startPosition;
                        break;
                    }
                }
                final char c4 = super.fCurrentEntity.ch[super.fCurrentEntity.position];
                if (!XMLChar.isLowSurrogate(c4) || !XML11Char.isXML11Name(XMLChar.supplemental(c3, c4))) {
                    final XMLEntityManager.ScannedEntity fCurrentEntity8 = super.fCurrentEntity;
                    --fCurrentEntity8.position;
                    break;
                }
                if (++super.fCurrentEntity.position != super.fCurrentEntity.count) {
                    continue;
                }
                final int n3 = super.fCurrentEntity.position - position;
                if (n3 == super.fCurrentEntity.ch.length) {
                    final char[] ch3 = new char[super.fCurrentEntity.ch.length << 1];
                    System.arraycopy(super.fCurrentEntity.ch, position, ch3, 0, n3);
                    super.fCurrentEntity.ch = ch3;
                }
                else {
                    System.arraycopy(super.fCurrentEntity.ch, position, super.fCurrentEntity.ch, 0, n3);
                }
                position = 0;
                if (this.load(n3, false)) {
                    break;
                }
                continue;
            }
        }
        final int n4 = super.fCurrentEntity.position - position;
        final XMLEntityManager.ScannedEntity fCurrentEntity9 = super.fCurrentEntity;
        fCurrentEntity9.columnNumber += n4;
        String addSymbol = null;
        if (n4 > 0) {
            addSymbol = super.fSymbolTable.addSymbol(super.fCurrentEntity.ch, position, n4);
        }
        return addSymbol;
    }
    
    public String scanNCName() throws IOException {
        if (super.fCurrentEntity.position == super.fCurrentEntity.count) {
            this.load(0, true);
        }
        int position = super.fCurrentEntity.position;
        final char c = super.fCurrentEntity.ch[position];
        if (XML11Char.isXML11NCNameStart(c)) {
            if (++super.fCurrentEntity.position == super.fCurrentEntity.count) {
                super.fCurrentEntity.ch[0] = c;
                position = 0;
                if (this.load(1, false)) {
                    final XMLEntityManager.ScannedEntity fCurrentEntity = super.fCurrentEntity;
                    ++fCurrentEntity.columnNumber;
                    return super.fSymbolTable.addSymbol(super.fCurrentEntity.ch, 0, 1);
                }
            }
        }
        else {
            if (!XML11Char.isXML11NameHighSurrogate(c)) {
                return null;
            }
            if (++super.fCurrentEntity.position == super.fCurrentEntity.count) {
                super.fCurrentEntity.ch[0] = c;
                position = 0;
                if (this.load(1, false)) {
                    final XMLEntityManager.ScannedEntity fCurrentEntity2 = super.fCurrentEntity;
                    --fCurrentEntity2.position;
                    final XMLEntityManager.ScannedEntity fCurrentEntity3 = super.fCurrentEntity;
                    --fCurrentEntity3.startPosition;
                    return null;
                }
            }
            final char c2 = super.fCurrentEntity.ch[super.fCurrentEntity.position];
            if (!XMLChar.isLowSurrogate(c2) || !XML11Char.isXML11NCNameStart(XMLChar.supplemental(c, c2))) {
                final XMLEntityManager.ScannedEntity fCurrentEntity4 = super.fCurrentEntity;
                --fCurrentEntity4.position;
                return null;
            }
            if (++super.fCurrentEntity.position == super.fCurrentEntity.count) {
                super.fCurrentEntity.ch[0] = c;
                super.fCurrentEntity.ch[1] = c2;
                position = 0;
                if (this.load(2, false)) {
                    final XMLEntityManager.ScannedEntity fCurrentEntity5 = super.fCurrentEntity;
                    fCurrentEntity5.columnNumber += 2;
                    return super.fSymbolTable.addSymbol(super.fCurrentEntity.ch, 0, 2);
                }
            }
        }
        while (true) {
            final char c3 = super.fCurrentEntity.ch[super.fCurrentEntity.position];
            if (XML11Char.isXML11NCName(c3)) {
                if (++super.fCurrentEntity.position != super.fCurrentEntity.count) {
                    continue;
                }
                final int n = super.fCurrentEntity.position - position;
                if (n == super.fCurrentEntity.ch.length) {
                    final char[] ch = new char[super.fCurrentEntity.ch.length << 1];
                    System.arraycopy(super.fCurrentEntity.ch, position, ch, 0, n);
                    super.fCurrentEntity.ch = ch;
                }
                else {
                    System.arraycopy(super.fCurrentEntity.ch, position, super.fCurrentEntity.ch, 0, n);
                }
                position = 0;
                if (this.load(n, false)) {
                    break;
                }
                continue;
            }
            else {
                if (!XML11Char.isXML11NameHighSurrogate(c3)) {
                    break;
                }
                if (++super.fCurrentEntity.position == super.fCurrentEntity.count) {
                    final int n2 = super.fCurrentEntity.position - position;
                    if (n2 == super.fCurrentEntity.ch.length) {
                        final char[] ch2 = new char[super.fCurrentEntity.ch.length << 1];
                        System.arraycopy(super.fCurrentEntity.ch, position, ch2, 0, n2);
                        super.fCurrentEntity.ch = ch2;
                    }
                    else {
                        System.arraycopy(super.fCurrentEntity.ch, position, super.fCurrentEntity.ch, 0, n2);
                    }
                    position = 0;
                    if (this.load(n2, false)) {
                        final XMLEntityManager.ScannedEntity fCurrentEntity6 = super.fCurrentEntity;
                        --fCurrentEntity6.startPosition;
                        final XMLEntityManager.ScannedEntity fCurrentEntity7 = super.fCurrentEntity;
                        --fCurrentEntity7.position;
                        break;
                    }
                }
                final char c4 = super.fCurrentEntity.ch[super.fCurrentEntity.position];
                if (!XMLChar.isLowSurrogate(c4) || !XML11Char.isXML11NCName(XMLChar.supplemental(c3, c4))) {
                    final XMLEntityManager.ScannedEntity fCurrentEntity8 = super.fCurrentEntity;
                    --fCurrentEntity8.position;
                    break;
                }
                if (++super.fCurrentEntity.position != super.fCurrentEntity.count) {
                    continue;
                }
                final int n3 = super.fCurrentEntity.position - position;
                if (n3 == super.fCurrentEntity.ch.length) {
                    final char[] ch3 = new char[super.fCurrentEntity.ch.length << 1];
                    System.arraycopy(super.fCurrentEntity.ch, position, ch3, 0, n3);
                    super.fCurrentEntity.ch = ch3;
                }
                else {
                    System.arraycopy(super.fCurrentEntity.ch, position, super.fCurrentEntity.ch, 0, n3);
                }
                position = 0;
                if (this.load(n3, false)) {
                    break;
                }
                continue;
            }
        }
        final int n4 = super.fCurrentEntity.position - position;
        final XMLEntityManager.ScannedEntity fCurrentEntity9 = super.fCurrentEntity;
        fCurrentEntity9.columnNumber += n4;
        String addSymbol = null;
        if (n4 > 0) {
            addSymbol = super.fSymbolTable.addSymbol(super.fCurrentEntity.ch, position, n4);
        }
        return addSymbol;
    }
    
    public boolean scanQName(final QName qName) throws IOException {
        if (super.fCurrentEntity.position == super.fCurrentEntity.count) {
            this.load(0, true);
        }
        int position = super.fCurrentEntity.position;
        final char c = super.fCurrentEntity.ch[position];
        if (XML11Char.isXML11NCNameStart(c)) {
            if (++super.fCurrentEntity.position == super.fCurrentEntity.count) {
                super.fCurrentEntity.ch[0] = c;
                position = 0;
                if (this.load(1, false)) {
                    final XMLEntityManager.ScannedEntity fCurrentEntity = super.fCurrentEntity;
                    ++fCurrentEntity.columnNumber;
                    final String addSymbol = super.fSymbolTable.addSymbol(super.fCurrentEntity.ch, 0, 1);
                    qName.setValues(null, addSymbol, addSymbol, null);
                    return true;
                }
            }
        }
        else {
            if (!XML11Char.isXML11NameHighSurrogate(c)) {
                return false;
            }
            if (++super.fCurrentEntity.position == super.fCurrentEntity.count) {
                super.fCurrentEntity.ch[0] = c;
                position = 0;
                if (this.load(1, false)) {
                    final XMLEntityManager.ScannedEntity fCurrentEntity2 = super.fCurrentEntity;
                    --fCurrentEntity2.startPosition;
                    final XMLEntityManager.ScannedEntity fCurrentEntity3 = super.fCurrentEntity;
                    --fCurrentEntity3.position;
                    return false;
                }
            }
            final char c2 = super.fCurrentEntity.ch[super.fCurrentEntity.position];
            if (!XMLChar.isLowSurrogate(c2) || !XML11Char.isXML11NCNameStart(XMLChar.supplemental(c, c2))) {
                final XMLEntityManager.ScannedEntity fCurrentEntity4 = super.fCurrentEntity;
                --fCurrentEntity4.position;
                return false;
            }
            if (++super.fCurrentEntity.position == super.fCurrentEntity.count) {
                super.fCurrentEntity.ch[0] = c;
                super.fCurrentEntity.ch[1] = c2;
                position = 0;
                if (this.load(2, false)) {
                    final XMLEntityManager.ScannedEntity fCurrentEntity5 = super.fCurrentEntity;
                    fCurrentEntity5.columnNumber += 2;
                    final String addSymbol2 = super.fSymbolTable.addSymbol(super.fCurrentEntity.ch, 0, 2);
                    qName.setValues(null, addSymbol2, addSymbol2, null);
                    return true;
                }
            }
        }
        int position2 = -1;
        boolean b = false;
        while (true) {
            final char c3 = super.fCurrentEntity.ch[super.fCurrentEntity.position];
            if (XML11Char.isXML11Name(c3)) {
                if (c3 == ':') {
                    if (position2 != -1) {
                        break;
                    }
                    position2 = super.fCurrentEntity.position;
                }
                if (++super.fCurrentEntity.position != super.fCurrentEntity.count) {
                    continue;
                }
                final int n = super.fCurrentEntity.position - position;
                if (n == super.fCurrentEntity.ch.length) {
                    final char[] ch = new char[super.fCurrentEntity.ch.length << 1];
                    System.arraycopy(super.fCurrentEntity.ch, position, ch, 0, n);
                    super.fCurrentEntity.ch = ch;
                }
                else {
                    System.arraycopy(super.fCurrentEntity.ch, position, super.fCurrentEntity.ch, 0, n);
                }
                if (position2 != -1) {
                    position2 -= position;
                }
                position = 0;
                if (this.load(n, false)) {
                    break;
                }
                continue;
            }
            else {
                if (!XML11Char.isXML11NameHighSurrogate(c3)) {
                    break;
                }
                if (++super.fCurrentEntity.position == super.fCurrentEntity.count) {
                    final int n2 = super.fCurrentEntity.position - position;
                    if (n2 == super.fCurrentEntity.ch.length) {
                        final char[] ch2 = new char[super.fCurrentEntity.ch.length << 1];
                        System.arraycopy(super.fCurrentEntity.ch, position, ch2, 0, n2);
                        super.fCurrentEntity.ch = ch2;
                    }
                    else {
                        System.arraycopy(super.fCurrentEntity.ch, position, super.fCurrentEntity.ch, 0, n2);
                    }
                    if (position2 != -1) {
                        position2 -= position;
                    }
                    position = 0;
                    if (this.load(n2, false)) {
                        b = true;
                        final XMLEntityManager.ScannedEntity fCurrentEntity6 = super.fCurrentEntity;
                        --fCurrentEntity6.startPosition;
                        final XMLEntityManager.ScannedEntity fCurrentEntity7 = super.fCurrentEntity;
                        --fCurrentEntity7.position;
                        break;
                    }
                }
                final char c4 = super.fCurrentEntity.ch[super.fCurrentEntity.position];
                if (!XMLChar.isLowSurrogate(c4) || !XML11Char.isXML11Name(XMLChar.supplemental(c3, c4))) {
                    b = true;
                    final XMLEntityManager.ScannedEntity fCurrentEntity8 = super.fCurrentEntity;
                    --fCurrentEntity8.position;
                    break;
                }
                if (++super.fCurrentEntity.position != super.fCurrentEntity.count) {
                    continue;
                }
                final int n3 = super.fCurrentEntity.position - position;
                if (n3 == super.fCurrentEntity.ch.length) {
                    final char[] ch3 = new char[super.fCurrentEntity.ch.length << 1];
                    System.arraycopy(super.fCurrentEntity.ch, position, ch3, 0, n3);
                    super.fCurrentEntity.ch = ch3;
                }
                else {
                    System.arraycopy(super.fCurrentEntity.ch, position, super.fCurrentEntity.ch, 0, n3);
                }
                if (position2 != -1) {
                    position2 -= position;
                }
                position = 0;
                if (this.load(n3, false)) {
                    break;
                }
                continue;
            }
        }
        final int n4 = super.fCurrentEntity.position - position;
        final XMLEntityManager.ScannedEntity fCurrentEntity9 = super.fCurrentEntity;
        fCurrentEntity9.columnNumber += n4;
        if (n4 > 0) {
            String addSymbol3 = null;
            final String addSymbol4 = super.fSymbolTable.addSymbol(super.fCurrentEntity.ch, position, n4);
            String addSymbol5;
            if (position2 != -1) {
                final int n5 = position2 - position;
                addSymbol3 = super.fSymbolTable.addSymbol(super.fCurrentEntity.ch, position, n5);
                final int n6 = n4 - n5 - 1;
                final int n7 = position2 + 1;
                if (!XML11Char.isXML11NCNameStart(super.fCurrentEntity.ch[n7]) && (!XML11Char.isXML11NameHighSurrogate(super.fCurrentEntity.ch[n7]) || b)) {
                    super.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "IllegalQName", null, (short)2);
                }
                addSymbol5 = super.fSymbolTable.addSymbol(super.fCurrentEntity.ch, position2 + 1, n6);
            }
            else {
                addSymbol5 = addSymbol4;
            }
            qName.setValues(addSymbol3, addSymbol5, addSymbol4, null);
            return true;
        }
        return false;
    }
    
    public int scanContent(final XMLString xmlString) throws IOException {
        if (super.fCurrentEntity.position == super.fCurrentEntity.count) {
            this.load(0, true);
        }
        else if (super.fCurrentEntity.position == super.fCurrentEntity.count - 1) {
            super.fCurrentEntity.ch[0] = super.fCurrentEntity.ch[super.fCurrentEntity.count - 1];
            this.load(1, false);
            super.fCurrentEntity.position = 0;
            super.fCurrentEntity.startPosition = 0;
        }
        int position = super.fCurrentEntity.position;
        final char c = super.fCurrentEntity.ch[position];
        int n = 0;
        final boolean external = super.fCurrentEntity.isExternal();
        if (c == '\n' || ((c == '\r' || c == '\u0085' || c == '\u2028') && external)) {
            do {
                final char c2 = super.fCurrentEntity.ch[super.fCurrentEntity.position++];
                if (c2 == '\r' && external) {
                    ++n;
                    final XMLEntityManager.ScannedEntity fCurrentEntity = super.fCurrentEntity;
                    ++fCurrentEntity.lineNumber;
                    super.fCurrentEntity.columnNumber = 1;
                    if (super.fCurrentEntity.position == super.fCurrentEntity.count) {
                        position = 0;
                        final XMLEntityManager.ScannedEntity fCurrentEntity2 = super.fCurrentEntity;
                        fCurrentEntity2.baseCharOffset += super.fCurrentEntity.position - super.fCurrentEntity.startPosition;
                        super.fCurrentEntity.position = n;
                        super.fCurrentEntity.startPosition = n;
                        if (this.load(n, false)) {
                            break;
                        }
                    }
                    final char c3 = super.fCurrentEntity.ch[super.fCurrentEntity.position];
                    if (c3 == '\n' || c3 == '\u0085') {
                        final XMLEntityManager.ScannedEntity fCurrentEntity3 = super.fCurrentEntity;
                        ++fCurrentEntity3.position;
                        ++position;
                    }
                    else {
                        ++n;
                    }
                }
                else {
                    if (c2 != '\n' && ((c2 != '\u0085' && c2 != '\u2028') || !external)) {
                        final XMLEntityManager.ScannedEntity fCurrentEntity4 = super.fCurrentEntity;
                        --fCurrentEntity4.position;
                        break;
                    }
                    ++n;
                    final XMLEntityManager.ScannedEntity fCurrentEntity5 = super.fCurrentEntity;
                    ++fCurrentEntity5.lineNumber;
                    super.fCurrentEntity.columnNumber = 1;
                    if (super.fCurrentEntity.position != super.fCurrentEntity.count) {
                        continue;
                    }
                    position = 0;
                    final XMLEntityManager.ScannedEntity fCurrentEntity6 = super.fCurrentEntity;
                    fCurrentEntity6.baseCharOffset += super.fCurrentEntity.position - super.fCurrentEntity.startPosition;
                    super.fCurrentEntity.position = n;
                    super.fCurrentEntity.startPosition = n;
                    if (this.load(n, false)) {
                        break;
                    }
                    continue;
                }
            } while (super.fCurrentEntity.position < super.fCurrentEntity.count - 1);
            for (int i = position; i < super.fCurrentEntity.position; ++i) {
                super.fCurrentEntity.ch[i] = '\n';
            }
            final int n2 = super.fCurrentEntity.position - position;
            if (super.fCurrentEntity.position == super.fCurrentEntity.count - 1) {
                xmlString.setValues(super.fCurrentEntity.ch, position, n2);
                return -1;
            }
        }
        if (external) {
            while (super.fCurrentEntity.position < super.fCurrentEntity.count) {
                final char c4 = super.fCurrentEntity.ch[super.fCurrentEntity.position++];
                if (!XML11Char.isXML11Content(c4) || c4 == '\u0085' || c4 == '\u2028') {
                    final XMLEntityManager.ScannedEntity fCurrentEntity7 = super.fCurrentEntity;
                    --fCurrentEntity7.position;
                    break;
                }
            }
        }
        else {
            while (super.fCurrentEntity.position < super.fCurrentEntity.count) {
                if (!XML11Char.isXML11InternalEntityContent(super.fCurrentEntity.ch[super.fCurrentEntity.position++])) {
                    final XMLEntityManager.ScannedEntity fCurrentEntity8 = super.fCurrentEntity;
                    --fCurrentEntity8.position;
                    break;
                }
            }
        }
        final int n3 = super.fCurrentEntity.position - position;
        final XMLEntityManager.ScannedEntity fCurrentEntity9 = super.fCurrentEntity;
        fCurrentEntity9.columnNumber += n3 - n;
        xmlString.setValues(super.fCurrentEntity.ch, position, n3);
        int n4;
        if (super.fCurrentEntity.position != super.fCurrentEntity.count) {
            n4 = super.fCurrentEntity.ch[super.fCurrentEntity.position];
            if ((n4 == 13 || n4 == 133 || n4 == 8232) && external) {
                n4 = 10;
            }
        }
        else {
            n4 = -1;
        }
        return n4;
    }
    
    public int scanLiteral(final int n, final XMLString xmlString) throws IOException {
        if (super.fCurrentEntity.position == super.fCurrentEntity.count) {
            this.load(0, true);
        }
        else if (super.fCurrentEntity.position == super.fCurrentEntity.count - 1) {
            super.fCurrentEntity.ch[0] = super.fCurrentEntity.ch[super.fCurrentEntity.count - 1];
            this.load(1, false);
            super.fCurrentEntity.startPosition = 0;
            super.fCurrentEntity.position = 0;
        }
        int position = super.fCurrentEntity.position;
        final char c = super.fCurrentEntity.ch[position];
        int n2 = 0;
        final boolean external = super.fCurrentEntity.isExternal();
        if (c == '\n' || ((c == '\r' || c == '\u0085' || c == '\u2028') && external)) {
            do {
                final char c2 = super.fCurrentEntity.ch[super.fCurrentEntity.position++];
                if (c2 == '\r' && external) {
                    ++n2;
                    final XMLEntityManager.ScannedEntity fCurrentEntity = super.fCurrentEntity;
                    ++fCurrentEntity.lineNumber;
                    super.fCurrentEntity.columnNumber = 1;
                    if (super.fCurrentEntity.position == super.fCurrentEntity.count) {
                        position = 0;
                        final XMLEntityManager.ScannedEntity fCurrentEntity2 = super.fCurrentEntity;
                        fCurrentEntity2.baseCharOffset += super.fCurrentEntity.position - super.fCurrentEntity.startPosition;
                        super.fCurrentEntity.position = n2;
                        super.fCurrentEntity.startPosition = n2;
                        if (this.load(n2, false)) {
                            break;
                        }
                    }
                    final char c3 = super.fCurrentEntity.ch[super.fCurrentEntity.position];
                    if (c3 == '\n' || c3 == '\u0085') {
                        final XMLEntityManager.ScannedEntity fCurrentEntity3 = super.fCurrentEntity;
                        ++fCurrentEntity3.position;
                        ++position;
                    }
                    else {
                        ++n2;
                    }
                }
                else {
                    if (c2 != '\n' && ((c2 != '\u0085' && c2 != '\u2028') || !external)) {
                        final XMLEntityManager.ScannedEntity fCurrentEntity4 = super.fCurrentEntity;
                        --fCurrentEntity4.position;
                        break;
                    }
                    ++n2;
                    final XMLEntityManager.ScannedEntity fCurrentEntity5 = super.fCurrentEntity;
                    ++fCurrentEntity5.lineNumber;
                    super.fCurrentEntity.columnNumber = 1;
                    if (super.fCurrentEntity.position != super.fCurrentEntity.count) {
                        continue;
                    }
                    position = 0;
                    final XMLEntityManager.ScannedEntity fCurrentEntity6 = super.fCurrentEntity;
                    fCurrentEntity6.baseCharOffset += super.fCurrentEntity.position - super.fCurrentEntity.startPosition;
                    super.fCurrentEntity.position = n2;
                    super.fCurrentEntity.startPosition = n2;
                    if (this.load(n2, false)) {
                        break;
                    }
                    continue;
                }
            } while (super.fCurrentEntity.position < super.fCurrentEntity.count - 1);
            for (int i = position; i < super.fCurrentEntity.position; ++i) {
                super.fCurrentEntity.ch[i] = '\n';
            }
            final int n3 = super.fCurrentEntity.position - position;
            if (super.fCurrentEntity.position == super.fCurrentEntity.count - 1) {
                xmlString.setValues(super.fCurrentEntity.ch, position, n3);
                return -1;
            }
        }
        if (external) {
            while (super.fCurrentEntity.position < super.fCurrentEntity.count) {
                final char c4 = super.fCurrentEntity.ch[super.fCurrentEntity.position++];
                if (c4 == n || c4 == '%' || !XML11Char.isXML11Content(c4) || c4 == '\u0085' || c4 == '\u2028') {
                    final XMLEntityManager.ScannedEntity fCurrentEntity7 = super.fCurrentEntity;
                    --fCurrentEntity7.position;
                    break;
                }
            }
        }
        else {
            while (super.fCurrentEntity.position < super.fCurrentEntity.count) {
                final char c5 = super.fCurrentEntity.ch[super.fCurrentEntity.position++];
                if ((c5 == n && !super.fCurrentEntity.literal) || c5 == '%' || !XML11Char.isXML11InternalEntityContent(c5)) {
                    final XMLEntityManager.ScannedEntity fCurrentEntity8 = super.fCurrentEntity;
                    --fCurrentEntity8.position;
                    break;
                }
            }
        }
        final int n4 = super.fCurrentEntity.position - position;
        final XMLEntityManager.ScannedEntity fCurrentEntity9 = super.fCurrentEntity;
        fCurrentEntity9.columnNumber += n4 - n2;
        xmlString.setValues(super.fCurrentEntity.ch, position, n4);
        int n5;
        if (super.fCurrentEntity.position != super.fCurrentEntity.count) {
            n5 = super.fCurrentEntity.ch[super.fCurrentEntity.position];
            if (n5 == n && super.fCurrentEntity.literal) {
                n5 = -1;
            }
        }
        else {
            n5 = -1;
        }
        return n5;
    }
    
    public boolean scanData(final String s, final XMLStringBuffer xmlStringBuffer) throws IOException {
        boolean b = false;
        final int length = s.length();
        final char char1 = s.charAt(0);
        final boolean external = super.fCurrentEntity.isExternal();
        do {
            if (super.fCurrentEntity.position == super.fCurrentEntity.count) {
                this.load(0, true);
            }
            for (boolean load = false; super.fCurrentEntity.position >= super.fCurrentEntity.count - length && !load; load = this.load(super.fCurrentEntity.count - super.fCurrentEntity.position, false), super.fCurrentEntity.position = 0, super.fCurrentEntity.startPosition = 0) {
                System.arraycopy(super.fCurrentEntity.ch, super.fCurrentEntity.position, super.fCurrentEntity.ch, 0, super.fCurrentEntity.count - super.fCurrentEntity.position);
            }
            if (super.fCurrentEntity.position >= super.fCurrentEntity.count - length) {
                xmlStringBuffer.append(super.fCurrentEntity.ch, super.fCurrentEntity.position, super.fCurrentEntity.count - super.fCurrentEntity.position);
                final XMLEntityManager.ScannedEntity fCurrentEntity = super.fCurrentEntity;
                fCurrentEntity.columnNumber += super.fCurrentEntity.count;
                final XMLEntityManager.ScannedEntity fCurrentEntity2 = super.fCurrentEntity;
                fCurrentEntity2.baseCharOffset += super.fCurrentEntity.position - super.fCurrentEntity.startPosition;
                super.fCurrentEntity.position = super.fCurrentEntity.count;
                super.fCurrentEntity.startPosition = super.fCurrentEntity.count;
                this.load(0, true);
                return false;
            }
            int position = super.fCurrentEntity.position;
            final char c = super.fCurrentEntity.ch[position];
            int count = 0;
            if (c == '\n' || ((c == '\r' || c == '\u0085' || c == '\u2028') && external)) {
                do {
                    final char c2 = super.fCurrentEntity.ch[super.fCurrentEntity.position++];
                    if (c2 == '\r' && external) {
                        ++count;
                        final XMLEntityManager.ScannedEntity fCurrentEntity3 = super.fCurrentEntity;
                        ++fCurrentEntity3.lineNumber;
                        super.fCurrentEntity.columnNumber = 1;
                        if (super.fCurrentEntity.position == super.fCurrentEntity.count) {
                            position = 0;
                            final XMLEntityManager.ScannedEntity fCurrentEntity4 = super.fCurrentEntity;
                            fCurrentEntity4.baseCharOffset += super.fCurrentEntity.position - super.fCurrentEntity.startPosition;
                            super.fCurrentEntity.position = count;
                            super.fCurrentEntity.startPosition = count;
                            if (this.load(count, false)) {
                                break;
                            }
                        }
                        final char c3 = super.fCurrentEntity.ch[super.fCurrentEntity.position];
                        if (c3 == '\n' || c3 == '\u0085') {
                            final XMLEntityManager.ScannedEntity fCurrentEntity5 = super.fCurrentEntity;
                            ++fCurrentEntity5.position;
                            ++position;
                        }
                        else {
                            ++count;
                        }
                    }
                    else {
                        if (c2 != '\n' && ((c2 != '\u0085' && c2 != '\u2028') || !external)) {
                            final XMLEntityManager.ScannedEntity fCurrentEntity6 = super.fCurrentEntity;
                            --fCurrentEntity6.position;
                            break;
                        }
                        ++count;
                        final XMLEntityManager.ScannedEntity fCurrentEntity7 = super.fCurrentEntity;
                        ++fCurrentEntity7.lineNumber;
                        super.fCurrentEntity.columnNumber = 1;
                        if (super.fCurrentEntity.position != super.fCurrentEntity.count) {
                            continue;
                        }
                        position = 0;
                        final XMLEntityManager.ScannedEntity fCurrentEntity8 = super.fCurrentEntity;
                        fCurrentEntity8.baseCharOffset += super.fCurrentEntity.position - super.fCurrentEntity.startPosition;
                        super.fCurrentEntity.position = count;
                        super.fCurrentEntity.startPosition = count;
                        super.fCurrentEntity.count = count;
                        if (this.load(count, false)) {
                            break;
                        }
                        continue;
                    }
                } while (super.fCurrentEntity.position < super.fCurrentEntity.count - 1);
                for (int i = position; i < super.fCurrentEntity.position; ++i) {
                    super.fCurrentEntity.ch[i] = '\n';
                }
                final int n = super.fCurrentEntity.position - position;
                if (super.fCurrentEntity.position == super.fCurrentEntity.count - 1) {
                    xmlStringBuffer.append(super.fCurrentEntity.ch, position, n);
                    return true;
                }
            }
            Label_1381: {
                if (external) {
                    while (super.fCurrentEntity.position < super.fCurrentEntity.count) {
                        final char c4 = super.fCurrentEntity.ch[super.fCurrentEntity.position++];
                        if (c4 == char1) {
                            final int n2 = super.fCurrentEntity.position - 1;
                            for (int j = 1; j < length; ++j) {
                                if (super.fCurrentEntity.position == super.fCurrentEntity.count) {
                                    final XMLEntityManager.ScannedEntity fCurrentEntity9 = super.fCurrentEntity;
                                    fCurrentEntity9.position -= j;
                                    break Label_1381;
                                }
                                if (s.charAt(j) != super.fCurrentEntity.ch[super.fCurrentEntity.position++]) {
                                    final XMLEntityManager.ScannedEntity fCurrentEntity10 = super.fCurrentEntity;
                                    --fCurrentEntity10.position;
                                    break;
                                }
                            }
                            if (super.fCurrentEntity.position == n2 + length) {
                                b = true;
                                break;
                            }
                            continue;
                        }
                        else {
                            if (c4 == '\n' || c4 == '\r' || c4 == '\u0085' || c4 == '\u2028') {
                                final XMLEntityManager.ScannedEntity fCurrentEntity11 = super.fCurrentEntity;
                                --fCurrentEntity11.position;
                                break;
                            }
                            if (!XML11Char.isXML11ValidLiteral(c4)) {
                                final XMLEntityManager.ScannedEntity fCurrentEntity12 = super.fCurrentEntity;
                                --fCurrentEntity12.position;
                                final int n3 = super.fCurrentEntity.position - position;
                                final XMLEntityManager.ScannedEntity fCurrentEntity13 = super.fCurrentEntity;
                                fCurrentEntity13.columnNumber += n3 - count;
                                xmlStringBuffer.append(super.fCurrentEntity.ch, position, n3);
                                return true;
                            }
                            continue;
                        }
                    }
                }
                else {
                    while (super.fCurrentEntity.position < super.fCurrentEntity.count) {
                        final char c5 = super.fCurrentEntity.ch[super.fCurrentEntity.position++];
                        if (c5 == char1) {
                            final int n4 = super.fCurrentEntity.position - 1;
                            for (int k = 1; k < length; ++k) {
                                if (super.fCurrentEntity.position == super.fCurrentEntity.count) {
                                    final XMLEntityManager.ScannedEntity fCurrentEntity14 = super.fCurrentEntity;
                                    fCurrentEntity14.position -= k;
                                    break Label_1381;
                                }
                                if (s.charAt(k) != super.fCurrentEntity.ch[super.fCurrentEntity.position++]) {
                                    final XMLEntityManager.ScannedEntity fCurrentEntity15 = super.fCurrentEntity;
                                    --fCurrentEntity15.position;
                                    break;
                                }
                            }
                            if (super.fCurrentEntity.position == n4 + length) {
                                b = true;
                                break;
                            }
                            continue;
                        }
                        else {
                            if (c5 == '\n') {
                                final XMLEntityManager.ScannedEntity fCurrentEntity16 = super.fCurrentEntity;
                                --fCurrentEntity16.position;
                                break;
                            }
                            if (!XML11Char.isXML11Valid(c5)) {
                                final XMLEntityManager.ScannedEntity fCurrentEntity17 = super.fCurrentEntity;
                                --fCurrentEntity17.position;
                                final int n5 = super.fCurrentEntity.position - position;
                                final XMLEntityManager.ScannedEntity fCurrentEntity18 = super.fCurrentEntity;
                                fCurrentEntity18.columnNumber += n5 - count;
                                xmlStringBuffer.append(super.fCurrentEntity.ch, position, n5);
                                return true;
                            }
                            continue;
                        }
                    }
                }
            }
            int n6 = super.fCurrentEntity.position - position;
            final XMLEntityManager.ScannedEntity fCurrentEntity19 = super.fCurrentEntity;
            fCurrentEntity19.columnNumber += n6 - count;
            if (b) {
                n6 -= length;
            }
            xmlStringBuffer.append(super.fCurrentEntity.ch, position, n6);
        } while (!b);
        return !b;
    }
    
    public boolean skipChar(final int n) throws IOException {
        if (super.fCurrentEntity.position == super.fCurrentEntity.count) {
            this.load(0, true);
        }
        final char c = super.fCurrentEntity.ch[super.fCurrentEntity.position];
        if (c == n) {
            final XMLEntityManager.ScannedEntity fCurrentEntity = super.fCurrentEntity;
            ++fCurrentEntity.position;
            if (n == 10) {
                final XMLEntityManager.ScannedEntity fCurrentEntity2 = super.fCurrentEntity;
                ++fCurrentEntity2.lineNumber;
                super.fCurrentEntity.columnNumber = 1;
            }
            else {
                final XMLEntityManager.ScannedEntity fCurrentEntity3 = super.fCurrentEntity;
                ++fCurrentEntity3.columnNumber;
            }
            return true;
        }
        if (n == 10 && (c == '\u2028' || c == '\u0085') && super.fCurrentEntity.isExternal()) {
            final XMLEntityManager.ScannedEntity fCurrentEntity4 = super.fCurrentEntity;
            ++fCurrentEntity4.position;
            final XMLEntityManager.ScannedEntity fCurrentEntity5 = super.fCurrentEntity;
            ++fCurrentEntity5.lineNumber;
            super.fCurrentEntity.columnNumber = 1;
            return true;
        }
        if (n == 10 && c == '\r' && super.fCurrentEntity.isExternal()) {
            if (super.fCurrentEntity.position == super.fCurrentEntity.count) {
                super.fCurrentEntity.ch[0] = c;
                this.load(1, false);
            }
            final char c2 = super.fCurrentEntity.ch[++super.fCurrentEntity.position];
            if (c2 == '\n' || c2 == '\u0085') {
                final XMLEntityManager.ScannedEntity fCurrentEntity6 = super.fCurrentEntity;
                ++fCurrentEntity6.position;
            }
            final XMLEntityManager.ScannedEntity fCurrentEntity7 = super.fCurrentEntity;
            ++fCurrentEntity7.lineNumber;
            super.fCurrentEntity.columnNumber = 1;
            return true;
        }
        return false;
    }
    
    public boolean skipSpaces() throws IOException {
        if (super.fCurrentEntity.position == super.fCurrentEntity.count) {
            this.load(0, true);
        }
        char c = super.fCurrentEntity.ch[super.fCurrentEntity.position];
        if (super.fCurrentEntity.isExternal()) {
            if (XML11Char.isXML11Space(c)) {
                do {
                    boolean load = false;
                    if (c == '\n' || c == '\r' || c == '\u0085' || c == '\u2028') {
                        final XMLEntityManager.ScannedEntity fCurrentEntity = super.fCurrentEntity;
                        ++fCurrentEntity.lineNumber;
                        super.fCurrentEntity.columnNumber = 1;
                        if (super.fCurrentEntity.position == super.fCurrentEntity.count - 1) {
                            super.fCurrentEntity.ch[0] = c;
                            load = this.load(1, true);
                            if (!load) {
                                super.fCurrentEntity.startPosition = 0;
                                super.fCurrentEntity.position = 0;
                            }
                        }
                        if (c == '\r') {
                            final char c2 = super.fCurrentEntity.ch[++super.fCurrentEntity.position];
                            if (c2 != '\n' && c2 != '\u0085') {
                                final XMLEntityManager.ScannedEntity fCurrentEntity2 = super.fCurrentEntity;
                                --fCurrentEntity2.position;
                            }
                        }
                    }
                    else {
                        final XMLEntityManager.ScannedEntity fCurrentEntity3 = super.fCurrentEntity;
                        ++fCurrentEntity3.columnNumber;
                    }
                    if (!load) {
                        final XMLEntityManager.ScannedEntity fCurrentEntity4 = super.fCurrentEntity;
                        ++fCurrentEntity4.position;
                    }
                    if (super.fCurrentEntity.position == super.fCurrentEntity.count) {
                        this.load(0, true);
                    }
                } while (XML11Char.isXML11Space(c = super.fCurrentEntity.ch[super.fCurrentEntity.position]));
                return true;
            }
        }
        else if (XMLChar.isSpace(c)) {
            do {
                boolean load2 = false;
                if (c == '\n') {
                    final XMLEntityManager.ScannedEntity fCurrentEntity5 = super.fCurrentEntity;
                    ++fCurrentEntity5.lineNumber;
                    super.fCurrentEntity.columnNumber = 1;
                    if (super.fCurrentEntity.position == super.fCurrentEntity.count - 1) {
                        super.fCurrentEntity.ch[0] = c;
                        load2 = this.load(1, true);
                        if (!load2) {
                            super.fCurrentEntity.startPosition = 0;
                            super.fCurrentEntity.position = 0;
                        }
                    }
                }
                else {
                    final XMLEntityManager.ScannedEntity fCurrentEntity6 = super.fCurrentEntity;
                    ++fCurrentEntity6.columnNumber;
                }
                if (!load2) {
                    final XMLEntityManager.ScannedEntity fCurrentEntity7 = super.fCurrentEntity;
                    ++fCurrentEntity7.position;
                }
                if (super.fCurrentEntity.position == super.fCurrentEntity.count) {
                    this.load(0, true);
                }
            } while (XMLChar.isSpace(c = super.fCurrentEntity.ch[super.fCurrentEntity.position]));
            return true;
        }
        return false;
    }
    
    public boolean skipString(final String s) throws IOException {
        if (super.fCurrentEntity.position == super.fCurrentEntity.count) {
            this.load(0, true);
        }
        final int length = s.length();
        for (int i = 0; i < length; ++i) {
            if (super.fCurrentEntity.ch[super.fCurrentEntity.position++] != s.charAt(i)) {
                final XMLEntityManager.ScannedEntity fCurrentEntity = super.fCurrentEntity;
                fCurrentEntity.position -= i + 1;
                return false;
            }
            if (i < length - 1 && super.fCurrentEntity.position == super.fCurrentEntity.count) {
                System.arraycopy(super.fCurrentEntity.ch, super.fCurrentEntity.count - i - 1, super.fCurrentEntity.ch, 0, i + 1);
                if (this.load(i + 1, false)) {
                    final XMLEntityManager.ScannedEntity fCurrentEntity2 = super.fCurrentEntity;
                    fCurrentEntity2.startPosition -= i + 1;
                    final XMLEntityManager.ScannedEntity fCurrentEntity3 = super.fCurrentEntity;
                    fCurrentEntity3.position -= i + 1;
                    return false;
                }
            }
        }
        final XMLEntityManager.ScannedEntity fCurrentEntity4 = super.fCurrentEntity;
        fCurrentEntity4.columnNumber += length;
        return true;
    }
}
