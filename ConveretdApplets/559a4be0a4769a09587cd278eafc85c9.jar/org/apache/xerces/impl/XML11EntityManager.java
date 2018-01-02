// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl;

import org.apache.xerces.util.XMLStringBuffer;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.xni.XMLString;
import java.io.IOException;

public class XML11EntityManager extends XMLEntityManager
{
    public XML11EntityManager() {
        this(null);
    }
    
    public XML11EntityManager(final XMLEntityManager entityManager) {
        super(entityManager);
    }
    
    protected XMLEntityScanner createEntityScanner() {
        return new XML11EntityScanner();
    }
    
    protected class XML11EntityScanner extends EntityScanner
    {
        public int peekChar() throws IOException {
            if (XML11EntityManager.this.fCurrentEntity.position == XML11EntityManager.this.fCurrentEntity.count) {
                this.load(0, true);
            }
            final int c = XML11EntityManager.this.fCurrentEntity.ch[XML11EntityManager.this.fCurrentEntity.position];
            if (XML11EntityManager.this.fCurrentEntity.isExternal()) {
                return (c != 13 && c != 133 && c != 8232) ? c : 10;
            }
            return c;
        }
        
        public int scanChar() throws IOException {
            if (XML11EntityManager.this.fCurrentEntity.position == XML11EntityManager.this.fCurrentEntity.count) {
                this.load(0, true);
            }
            int c = XML11EntityManager.this.fCurrentEntity.ch[XML11EntityManager.this.fCurrentEntity.position++];
            boolean external = false;
            if (c == 10 || ((c == 13 || c == 133 || c == 8232) && (external = XML11EntityManager.this.fCurrentEntity.isExternal()))) {
                final ScannedEntity fCurrentEntity = XML11EntityManager.this.fCurrentEntity;
                ++fCurrentEntity.lineNumber;
                XML11EntityManager.this.fCurrentEntity.columnNumber = 1;
                if (XML11EntityManager.this.fCurrentEntity.position == XML11EntityManager.this.fCurrentEntity.count) {
                    XML11EntityManager.this.fCurrentEntity.ch[0] = (char)c;
                    this.load(1, false);
                }
                if ((c == 13 || c == 133) && external) {
                    if (XML11EntityManager.this.fCurrentEntity.ch[XML11EntityManager.this.fCurrentEntity.position++] != '\n') {
                        final ScannedEntity fCurrentEntity2 = XML11EntityManager.this.fCurrentEntity;
                        --fCurrentEntity2.position;
                    }
                    c = 10;
                }
            }
            final ScannedEntity fCurrentEntity3 = XML11EntityManager.this.fCurrentEntity;
            ++fCurrentEntity3.columnNumber;
            return c;
        }
        
        public int scanContent(final XMLString content) throws IOException {
            if (XML11EntityManager.this.fCurrentEntity.position == XML11EntityManager.this.fCurrentEntity.count) {
                this.load(0, true);
            }
            else if (XML11EntityManager.this.fCurrentEntity.position == XML11EntityManager.this.fCurrentEntity.count - 1) {
                XML11EntityManager.this.fCurrentEntity.ch[0] = XML11EntityManager.this.fCurrentEntity.ch[XML11EntityManager.this.fCurrentEntity.count - 1];
                this.load(1, false);
                XML11EntityManager.this.fCurrentEntity.position = 0;
            }
            int offset = XML11EntityManager.this.fCurrentEntity.position;
            int c = XML11EntityManager.this.fCurrentEntity.ch[offset];
            int newlines = 0;
            final boolean external = XML11EntityManager.this.fCurrentEntity.isExternal();
            if (c == 10 || ((c == 13 || c == 133 || c == 8232) && external)) {
                do {
                    c = XML11EntityManager.this.fCurrentEntity.ch[XML11EntityManager.this.fCurrentEntity.position++];
                    if ((c == 13 || c == 133) && external) {
                        ++newlines;
                        final ScannedEntity fCurrentEntity = XML11EntityManager.this.fCurrentEntity;
                        ++fCurrentEntity.lineNumber;
                        XML11EntityManager.this.fCurrentEntity.columnNumber = 1;
                        if (XML11EntityManager.this.fCurrentEntity.position == XML11EntityManager.this.fCurrentEntity.count) {
                            offset = 0;
                            XML11EntityManager.this.fCurrentEntity.position = newlines;
                            if (this.load(newlines, false)) {
                                break;
                            }
                        }
                        if (XML11EntityManager.this.fCurrentEntity.ch[XML11EntityManager.this.fCurrentEntity.position] == '\n') {
                            final ScannedEntity fCurrentEntity2 = XML11EntityManager.this.fCurrentEntity;
                            ++fCurrentEntity2.position;
                            ++offset;
                        }
                        else {
                            ++newlines;
                        }
                    }
                    else {
                        if (c != 10 && c != 8232) {
                            final ScannedEntity fCurrentEntity3 = XML11EntityManager.this.fCurrentEntity;
                            --fCurrentEntity3.position;
                            break;
                        }
                        ++newlines;
                        final ScannedEntity fCurrentEntity4 = XML11EntityManager.this.fCurrentEntity;
                        ++fCurrentEntity4.lineNumber;
                        XML11EntityManager.this.fCurrentEntity.columnNumber = 1;
                        if (XML11EntityManager.this.fCurrentEntity.position != XML11EntityManager.this.fCurrentEntity.count) {
                            continue;
                        }
                        offset = 0;
                        XML11EntityManager.this.fCurrentEntity.position = newlines;
                        if (this.load(newlines, false)) {
                            break;
                        }
                        continue;
                    }
                } while (XML11EntityManager.this.fCurrentEntity.position < XML11EntityManager.this.fCurrentEntity.count - 1);
                for (int i = offset; i < XML11EntityManager.this.fCurrentEntity.position; ++i) {
                    XML11EntityManager.this.fCurrentEntity.ch[i] = '\n';
                }
                final int length = XML11EntityManager.this.fCurrentEntity.position - offset;
                if (XML11EntityManager.this.fCurrentEntity.position == XML11EntityManager.this.fCurrentEntity.count - 1) {
                    content.setValues(XML11EntityManager.this.fCurrentEntity.ch, offset, length);
                    return -1;
                }
            }
            while (XML11EntityManager.this.fCurrentEntity.position < XML11EntityManager.this.fCurrentEntity.count) {
                c = XML11EntityManager.this.fCurrentEntity.ch[XML11EntityManager.this.fCurrentEntity.position++];
                if (!XMLChar.isContent(c) && c != 133 && c != 8232) {
                    final ScannedEntity fCurrentEntity5 = XML11EntityManager.this.fCurrentEntity;
                    --fCurrentEntity5.position;
                    break;
                }
            }
            final int length2 = XML11EntityManager.this.fCurrentEntity.position - offset;
            final ScannedEntity fCurrentEntity6 = XML11EntityManager.this.fCurrentEntity;
            fCurrentEntity6.columnNumber += length2 - newlines;
            content.setValues(XML11EntityManager.this.fCurrentEntity.ch, offset, length2);
            if (XML11EntityManager.this.fCurrentEntity.position != XML11EntityManager.this.fCurrentEntity.count) {
                c = XML11EntityManager.this.fCurrentEntity.ch[XML11EntityManager.this.fCurrentEntity.position];
                if ((c == 13 || c == 133 || c == 8232) && external) {
                    c = 10;
                }
            }
            else {
                c = -1;
            }
            return c;
        }
        
        public int scanLiteral(final int quote, final XMLString content) throws IOException {
            if (XML11EntityManager.this.fCurrentEntity.position == XML11EntityManager.this.fCurrentEntity.count) {
                this.load(0, true);
            }
            else if (XML11EntityManager.this.fCurrentEntity.position == XML11EntityManager.this.fCurrentEntity.count - 1) {
                XML11EntityManager.this.fCurrentEntity.ch[0] = XML11EntityManager.this.fCurrentEntity.ch[XML11EntityManager.this.fCurrentEntity.count - 1];
                this.load(1, false);
                XML11EntityManager.this.fCurrentEntity.position = 0;
            }
            int offset = XML11EntityManager.this.fCurrentEntity.position;
            int c = XML11EntityManager.this.fCurrentEntity.ch[offset];
            int newlines = 0;
            final boolean external = XML11EntityManager.this.fCurrentEntity.isExternal();
            if (c == 10 || ((c == 13 || c == 133 || c == 8232) && external)) {
                do {
                    c = XML11EntityManager.this.fCurrentEntity.ch[XML11EntityManager.this.fCurrentEntity.position++];
                    if ((c == 13 || c == 133) && external) {
                        ++newlines;
                        final ScannedEntity fCurrentEntity = XML11EntityManager.this.fCurrentEntity;
                        ++fCurrentEntity.lineNumber;
                        XML11EntityManager.this.fCurrentEntity.columnNumber = 1;
                        if (XML11EntityManager.this.fCurrentEntity.position == XML11EntityManager.this.fCurrentEntity.count) {
                            offset = 0;
                            XML11EntityManager.this.fCurrentEntity.position = newlines;
                            if (this.load(newlines, false)) {
                                break;
                            }
                        }
                        if (XML11EntityManager.this.fCurrentEntity.ch[XML11EntityManager.this.fCurrentEntity.position] == '\n') {
                            final ScannedEntity fCurrentEntity2 = XML11EntityManager.this.fCurrentEntity;
                            ++fCurrentEntity2.position;
                            ++offset;
                        }
                        else {
                            ++newlines;
                        }
                    }
                    else {
                        if (c != 10 && c != 8232) {
                            final ScannedEntity fCurrentEntity3 = XML11EntityManager.this.fCurrentEntity;
                            --fCurrentEntity3.position;
                            break;
                        }
                        ++newlines;
                        final ScannedEntity fCurrentEntity4 = XML11EntityManager.this.fCurrentEntity;
                        ++fCurrentEntity4.lineNumber;
                        XML11EntityManager.this.fCurrentEntity.columnNumber = 1;
                        if (XML11EntityManager.this.fCurrentEntity.position != XML11EntityManager.this.fCurrentEntity.count) {
                            continue;
                        }
                        offset = 0;
                        XML11EntityManager.this.fCurrentEntity.position = newlines;
                        if (this.load(newlines, false)) {
                            break;
                        }
                        continue;
                    }
                } while (XML11EntityManager.this.fCurrentEntity.position < XML11EntityManager.this.fCurrentEntity.count - 1);
                for (int i = offset; i < XML11EntityManager.this.fCurrentEntity.position; ++i) {
                    XML11EntityManager.this.fCurrentEntity.ch[i] = '\n';
                }
                final int length = XML11EntityManager.this.fCurrentEntity.position - offset;
                if (XML11EntityManager.this.fCurrentEntity.position == XML11EntityManager.this.fCurrentEntity.count - 1) {
                    content.setValues(XML11EntityManager.this.fCurrentEntity.ch, offset, length);
                    return -1;
                }
            }
            while (XML11EntityManager.this.fCurrentEntity.position < XML11EntityManager.this.fCurrentEntity.count) {
                c = XML11EntityManager.this.fCurrentEntity.ch[XML11EntityManager.this.fCurrentEntity.position++];
                if ((c == quote && (!XML11EntityManager.this.fCurrentEntity.literal || external)) || c == 37 || (!XMLChar.isContent(c) && c != 133 && c != 8232)) {
                    final ScannedEntity fCurrentEntity5 = XML11EntityManager.this.fCurrentEntity;
                    --fCurrentEntity5.position;
                    break;
                }
            }
            final int length2 = XML11EntityManager.this.fCurrentEntity.position - offset;
            final ScannedEntity fCurrentEntity6 = XML11EntityManager.this.fCurrentEntity;
            fCurrentEntity6.columnNumber += length2 - newlines;
            content.setValues(XML11EntityManager.this.fCurrentEntity.ch, offset, length2);
            if (XML11EntityManager.this.fCurrentEntity.position != XML11EntityManager.this.fCurrentEntity.count) {
                c = XML11EntityManager.this.fCurrentEntity.ch[XML11EntityManager.this.fCurrentEntity.position];
                if (c == quote && XML11EntityManager.this.fCurrentEntity.literal) {
                    c = -1;
                }
            }
            else {
                c = -1;
            }
            return c;
        }
        
        public boolean scanData(final String delimiter, final XMLStringBuffer buffer) throws IOException {
            boolean done = false;
            final int delimLen = delimiter.length();
            final char charAt0 = delimiter.charAt(0);
            final boolean external = XML11EntityManager.this.fCurrentEntity.isExternal();
            do {
                if (XML11EntityManager.this.fCurrentEntity.position == XML11EntityManager.this.fCurrentEntity.count) {
                    this.load(0, true);
                }
                else if (XML11EntityManager.this.fCurrentEntity.position >= XML11EntityManager.this.fCurrentEntity.count - delimLen) {
                    System.arraycopy(XML11EntityManager.this.fCurrentEntity.ch, XML11EntityManager.this.fCurrentEntity.position, XML11EntityManager.this.fCurrentEntity.ch, 0, XML11EntityManager.this.fCurrentEntity.count - XML11EntityManager.this.fCurrentEntity.position);
                    this.load(XML11EntityManager.this.fCurrentEntity.count - XML11EntityManager.this.fCurrentEntity.position, false);
                    XML11EntityManager.this.fCurrentEntity.position = 0;
                }
                if (XML11EntityManager.this.fCurrentEntity.position >= XML11EntityManager.this.fCurrentEntity.count - delimLen) {
                    final int length = XML11EntityManager.this.fCurrentEntity.count - XML11EntityManager.this.fCurrentEntity.position;
                    buffer.append(XML11EntityManager.this.fCurrentEntity.ch, XML11EntityManager.this.fCurrentEntity.position, length);
                    final ScannedEntity fCurrentEntity = XML11EntityManager.this.fCurrentEntity;
                    fCurrentEntity.columnNumber += XML11EntityManager.this.fCurrentEntity.count;
                    XML11EntityManager.this.fCurrentEntity.position = XML11EntityManager.this.fCurrentEntity.count;
                    this.load(0, true);
                    return false;
                }
                int offset = XML11EntityManager.this.fCurrentEntity.position;
                int c = XML11EntityManager.this.fCurrentEntity.ch[offset];
                int newlines = 0;
                if (c == 10 || ((c == 13 || c == 133 || c == 8232) && external)) {
                    do {
                        c = XML11EntityManager.this.fCurrentEntity.ch[XML11EntityManager.this.fCurrentEntity.position++];
                        if ((c == 13 || c == 133) && external) {
                            ++newlines;
                            final ScannedEntity fCurrentEntity2 = XML11EntityManager.this.fCurrentEntity;
                            ++fCurrentEntity2.lineNumber;
                            XML11EntityManager.this.fCurrentEntity.columnNumber = 1;
                            if (XML11EntityManager.this.fCurrentEntity.position == XML11EntityManager.this.fCurrentEntity.count) {
                                offset = 0;
                                XML11EntityManager.this.fCurrentEntity.position = newlines;
                                if (this.load(newlines, false)) {
                                    break;
                                }
                            }
                            if (XML11EntityManager.this.fCurrentEntity.ch[XML11EntityManager.this.fCurrentEntity.position] == '\n') {
                                final ScannedEntity fCurrentEntity3 = XML11EntityManager.this.fCurrentEntity;
                                ++fCurrentEntity3.position;
                                ++offset;
                            }
                            else {
                                ++newlines;
                            }
                        }
                        else {
                            if (c != 10 && c != 8232) {
                                final ScannedEntity fCurrentEntity4 = XML11EntityManager.this.fCurrentEntity;
                                --fCurrentEntity4.position;
                                break;
                            }
                            ++newlines;
                            final ScannedEntity fCurrentEntity5 = XML11EntityManager.this.fCurrentEntity;
                            ++fCurrentEntity5.lineNumber;
                            XML11EntityManager.this.fCurrentEntity.columnNumber = 1;
                            if (XML11EntityManager.this.fCurrentEntity.position != XML11EntityManager.this.fCurrentEntity.count) {
                                continue;
                            }
                            offset = 0;
                            XML11EntityManager.this.fCurrentEntity.position = newlines;
                            XML11EntityManager.this.fCurrentEntity.count = newlines;
                            if (this.load(newlines, false)) {
                                break;
                            }
                            continue;
                        }
                    } while (XML11EntityManager.this.fCurrentEntity.position < XML11EntityManager.this.fCurrentEntity.count - 1);
                    for (int i = offset; i < XML11EntityManager.this.fCurrentEntity.position; ++i) {
                        XML11EntityManager.this.fCurrentEntity.ch[i] = '\n';
                    }
                    final int length2 = XML11EntityManager.this.fCurrentEntity.position - offset;
                    if (XML11EntityManager.this.fCurrentEntity.position == XML11EntityManager.this.fCurrentEntity.count - 1) {
                        buffer.append(XML11EntityManager.this.fCurrentEntity.ch, offset, length2);
                        return true;
                    }
                }
            Label_1165:
                while (XML11EntityManager.this.fCurrentEntity.position < XML11EntityManager.this.fCurrentEntity.count) {
                    c = XML11EntityManager.this.fCurrentEntity.ch[XML11EntityManager.this.fCurrentEntity.position++];
                    if (c == charAt0) {
                        final int delimOffset = XML11EntityManager.this.fCurrentEntity.position - 1;
                        for (int j = 1; j < delimLen; ++j) {
                            if (XML11EntityManager.this.fCurrentEntity.position == XML11EntityManager.this.fCurrentEntity.count) {
                                final ScannedEntity fCurrentEntity6 = XML11EntityManager.this.fCurrentEntity;
                                fCurrentEntity6.position -= j;
                                break Label_1165;
                            }
                            c = XML11EntityManager.this.fCurrentEntity.ch[XML11EntityManager.this.fCurrentEntity.position++];
                            if (delimiter.charAt(j) != c) {
                                final ScannedEntity fCurrentEntity7 = XML11EntityManager.this.fCurrentEntity;
                                --fCurrentEntity7.position;
                                break;
                            }
                        }
                        if (XML11EntityManager.this.fCurrentEntity.position == delimOffset + delimLen) {
                            done = true;
                            break;
                        }
                        continue;
                    }
                    else {
                        if (c == 10 || (external && (c == 13 || c == 133 || c == 8232))) {
                            final ScannedEntity fCurrentEntity8 = XML11EntityManager.this.fCurrentEntity;
                            --fCurrentEntity8.position;
                            break;
                        }
                        if (XMLChar.isInvalid(c)) {
                            final ScannedEntity fCurrentEntity9 = XML11EntityManager.this.fCurrentEntity;
                            --fCurrentEntity9.position;
                            final int length3 = XML11EntityManager.this.fCurrentEntity.position - offset;
                            final ScannedEntity fCurrentEntity10 = XML11EntityManager.this.fCurrentEntity;
                            fCurrentEntity10.columnNumber += length3 - newlines;
                            buffer.append(XML11EntityManager.this.fCurrentEntity.ch, offset, length3);
                            return true;
                        }
                        continue;
                    }
                }
                int length3 = XML11EntityManager.this.fCurrentEntity.position - offset;
                final ScannedEntity fCurrentEntity11 = XML11EntityManager.this.fCurrentEntity;
                fCurrentEntity11.columnNumber += length3 - newlines;
                if (done) {
                    length3 -= delimLen;
                }
                buffer.append(XML11EntityManager.this.fCurrentEntity.ch, offset, length3);
            } while (!done);
            return !done;
        }
        
        public boolean skipChar(final int c) throws IOException {
            if (XML11EntityManager.this.fCurrentEntity.position == XML11EntityManager.this.fCurrentEntity.count) {
                this.load(0, true);
            }
            final int cc = XML11EntityManager.this.fCurrentEntity.ch[XML11EntityManager.this.fCurrentEntity.position];
            if (cc == c) {
                final ScannedEntity fCurrentEntity = XML11EntityManager.this.fCurrentEntity;
                ++fCurrentEntity.position;
                if (c == 10) {
                    final ScannedEntity fCurrentEntity2 = XML11EntityManager.this.fCurrentEntity;
                    ++fCurrentEntity2.lineNumber;
                    XML11EntityManager.this.fCurrentEntity.columnNumber = 1;
                }
                else {
                    final ScannedEntity fCurrentEntity3 = XML11EntityManager.this.fCurrentEntity;
                    ++fCurrentEntity3.columnNumber;
                }
                return true;
            }
            if (c == 10 && cc == 8232) {
                final ScannedEntity fCurrentEntity4 = XML11EntityManager.this.fCurrentEntity;
                ++fCurrentEntity4.position;
                final ScannedEntity fCurrentEntity5 = XML11EntityManager.this.fCurrentEntity;
                ++fCurrentEntity5.lineNumber;
                XML11EntityManager.this.fCurrentEntity.columnNumber = 1;
                return true;
            }
            if (c == 10 && (cc == 13 || cc == 133) && XML11EntityManager.this.fCurrentEntity.isExternal()) {
                if (XML11EntityManager.this.fCurrentEntity.position == XML11EntityManager.this.fCurrentEntity.count) {
                    XML11EntityManager.this.fCurrentEntity.ch[0] = (char)cc;
                    this.load(1, false);
                }
                final ScannedEntity fCurrentEntity6 = XML11EntityManager.this.fCurrentEntity;
                ++fCurrentEntity6.position;
                if (XML11EntityManager.this.fCurrentEntity.ch[XML11EntityManager.this.fCurrentEntity.position] == '\n') {
                    final ScannedEntity fCurrentEntity7 = XML11EntityManager.this.fCurrentEntity;
                    ++fCurrentEntity7.position;
                }
                final ScannedEntity fCurrentEntity8 = XML11EntityManager.this.fCurrentEntity;
                ++fCurrentEntity8.lineNumber;
                XML11EntityManager.this.fCurrentEntity.columnNumber = 1;
                return true;
            }
            return false;
        }
        
        public boolean skipSpaces() throws IOException {
            if (XML11EntityManager.this.fCurrentEntity.position == XML11EntityManager.this.fCurrentEntity.count) {
                this.load(0, true);
            }
            int c = XML11EntityManager.this.fCurrentEntity.ch[XML11EntityManager.this.fCurrentEntity.position];
            if (XMLChar.isXML11Space(c)) {
                final boolean external = XML11EntityManager.this.fCurrentEntity.isExternal();
                do {
                    boolean entityChanged = false;
                    if (c == 10 || (external && (c == 13 || c == 133 || c == 8232))) {
                        final ScannedEntity fCurrentEntity = XML11EntityManager.this.fCurrentEntity;
                        ++fCurrentEntity.lineNumber;
                        XML11EntityManager.this.fCurrentEntity.columnNumber = 1;
                        if (XML11EntityManager.this.fCurrentEntity.position == XML11EntityManager.this.fCurrentEntity.count - 1) {
                            XML11EntityManager.this.fCurrentEntity.ch[0] = (char)c;
                            entityChanged = this.load(1, true);
                            if (!entityChanged) {
                                XML11EntityManager.this.fCurrentEntity.position = 0;
                            }
                        }
                        if ((c == 13 || c == 133) && external && XML11EntityManager.this.fCurrentEntity.ch[++XML11EntityManager.this.fCurrentEntity.position] != '\n') {
                            final ScannedEntity fCurrentEntity2 = XML11EntityManager.this.fCurrentEntity;
                            --fCurrentEntity2.position;
                        }
                    }
                    else {
                        final ScannedEntity fCurrentEntity3 = XML11EntityManager.this.fCurrentEntity;
                        ++fCurrentEntity3.columnNumber;
                    }
                    if (!entityChanged) {
                        final ScannedEntity fCurrentEntity4 = XML11EntityManager.this.fCurrentEntity;
                        ++fCurrentEntity4.position;
                    }
                    if (XML11EntityManager.this.fCurrentEntity.position == XML11EntityManager.this.fCurrentEntity.count) {
                        this.load(0, true);
                    }
                } while (XMLChar.isXML11Space(c = XML11EntityManager.this.fCurrentEntity.ch[XML11EntityManager.this.fCurrentEntity.position]));
                return true;
            }
            return false;
        }
        
        public boolean skipString(final String s) throws IOException {
            if (XML11EntityManager.this.fCurrentEntity.position == XML11EntityManager.this.fCurrentEntity.count) {
                this.load(0, true);
            }
            final int length = s.length();
            for (int i = 0; i < length; ++i) {
                final char c = XML11EntityManager.this.fCurrentEntity.ch[XML11EntityManager.this.fCurrentEntity.position++];
                if (c != s.charAt(i)) {
                    final ScannedEntity fCurrentEntity = XML11EntityManager.this.fCurrentEntity;
                    fCurrentEntity.position -= i + 1;
                    return false;
                }
                if (i < length - 1 && XML11EntityManager.this.fCurrentEntity.position == XML11EntityManager.this.fCurrentEntity.count) {
                    System.arraycopy(XML11EntityManager.this.fCurrentEntity.ch, XML11EntityManager.this.fCurrentEntity.count - i - 1, XML11EntityManager.this.fCurrentEntity.ch, 0, i + 1);
                    if (this.load(i + 1, false)) {
                        final ScannedEntity fCurrentEntity2 = XML11EntityManager.this.fCurrentEntity;
                        fCurrentEntity2.position -= i + 1;
                        return false;
                    }
                }
            }
            final ScannedEntity fCurrentEntity3 = XML11EntityManager.this.fCurrentEntity;
            fCurrentEntity3.columnNumber += length;
            return true;
        }
    }
}
