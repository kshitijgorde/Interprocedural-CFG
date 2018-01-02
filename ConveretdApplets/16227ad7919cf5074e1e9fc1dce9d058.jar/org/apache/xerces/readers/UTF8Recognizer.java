// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.readers;

import java.io.IOException;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import org.apache.xerces.utils.ChunkyByteArray;
import org.apache.xerces.utils.StringPool;
import org.apache.xerces.framework.XMLErrorReporter;

final class UTF8Recognizer extends XMLDeclRecognizer
{
    public XMLEntityHandler.EntityReader recognize(final XMLEntityReaderFactory xmlEntityReaderFactory, final XMLEntityHandler xmlEntityHandler, final XMLErrorReporter xmlErrorReporter, final boolean b, final StringPool stringPool, final ChunkyByteArray chunkyByteArray, final boolean b2, final boolean b3) throws Exception {
        XMLEntityHandler.EntityReader entityReader = null;
        if (chunkyByteArray.byteAt(0) == 60 && chunkyByteArray.byteAt(1) == 63 && chunkyByteArray.byteAt(2) == 120 && chunkyByteArray.byteAt(3) == 109 && chunkyByteArray.byteAt(4) == 108) {
            final byte byte1 = chunkyByteArray.byteAt(5);
            if (byte1 == 32 || byte1 == 9 || byte1 == 10 || byte1 == 13) {
                final int prescanXMLDeclOrTextDecl = this.prescanXMLDeclOrTextDecl(new XMLDeclReader(xmlEntityHandler, xmlErrorReporter, b, chunkyByteArray, stringPool), b2);
                if (prescanXMLDeclOrTextDecl != -1) {
                    final String orphanString = stringPool.orphanString(prescanXMLDeclOrTextDecl);
                    final String upperCase = orphanString.toUpperCase();
                    if ("ISO-10646-UCS-2".equals(upperCase)) {
                        throw new UnsupportedEncodingException(orphanString);
                    }
                    if ("ISO-10646-UCS-4".equals(upperCase)) {
                        throw new UnsupportedEncodingException(orphanString);
                    }
                    if ("UTF-16".equals(upperCase)) {
                        throw new UnsupportedEncodingException(orphanString);
                    }
                    String convert = MIME2Java.convert(upperCase);
                    if (convert == null) {
                        if (!b3) {
                            throw new UnsupportedEncodingException(orphanString);
                        }
                        convert = orphanString;
                    }
                    try {
                        chunkyByteArray.rewind();
                        if ("UTF-8".equalsIgnoreCase(convert) || "UTF8".equalsIgnoreCase(convert)) {
                            entityReader = xmlEntityReaderFactory.createUTF8Reader(xmlEntityHandler, xmlErrorReporter, b, chunkyByteArray, stringPool);
                            return entityReader;
                        }
                        entityReader = xmlEntityReaderFactory.createCharReader(xmlEntityHandler, xmlErrorReporter, b, new InputStreamReader(chunkyByteArray, convert), stringPool);
                        return entityReader;
                    }
                    catch (UnsupportedEncodingException ex2) {
                        throw new UnsupportedEncodingException(orphanString);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                        return entityReader;
                    }
                }
                chunkyByteArray.rewind();
                entityReader = xmlEntityReaderFactory.createUTF8Reader(xmlEntityHandler, xmlErrorReporter, b, chunkyByteArray, stringPool);
            }
        }
        return entityReader;
    }
    
    final class XMLDeclReader extends XMLEntityReader
    {
        private StringPool fStringPool;
        private ChunkyByteArray fData;
        
        XMLDeclReader(final XMLEntityHandler xmlEntityHandler, final XMLErrorReporter xmlErrorReporter, final boolean b, final ChunkyByteArray fData, final StringPool fStringPool) {
            super(xmlEntityHandler, xmlErrorReporter, b);
            this.fStringPool = fStringPool;
            this.fData = fData;
        }
        
        public boolean lookingAtChar(final char c, final boolean b) throws IOException {
            if (this.fData.byteAt(super.fCurrentOffset) != c) {
                return false;
            }
            if (b) {
                ++super.fCurrentOffset;
            }
            return true;
        }
        
        public boolean lookingAtSpace(final boolean b) throws IOException {
            final int n = this.fData.byteAt(super.fCurrentOffset) & 0xFF;
            if (n != 32 && n != 9 && n != 10 && n != 13) {
                return false;
            }
            if (b) {
                ++super.fCurrentOffset;
            }
            return true;
        }
        
        public void skipPastSpaces() throws IOException {
            while (true) {
                final int n = this.fData.byteAt(super.fCurrentOffset) & 0xFF;
                if (n != 32 && n != 9 && n != 10 && n != 13) {
                    break;
                }
                ++super.fCurrentOffset;
            }
        }
        
        public boolean skippedString(final char[] array) throws IOException {
            int fCurrentOffset = super.fCurrentOffset;
            for (int i = 0; i < array.length; ++i) {
                if (this.fData.byteAt(fCurrentOffset) != array[i]) {
                    return false;
                }
                ++fCurrentOffset;
            }
            super.fCurrentOffset = fCurrentOffset;
            return true;
        }
        
        public int scanStringLiteral() throws Exception {
            final boolean lookingAtChar;
            if (!(lookingAtChar = this.lookingAtChar('\'', true)) && !this.lookingAtChar('\"', true)) {
                return -1;
            }
            final int fCurrentOffset = super.fCurrentOffset;
            final int n = lookingAtChar ? 39 : 34;
            while (true) {
                final byte byte1 = this.fData.byteAt(super.fCurrentOffset);
                if (byte1 == n) {
                    final int n2 = super.fCurrentOffset - fCurrentOffset;
                    final StringBuffer sb = new StringBuffer(n2);
                    for (int i = 0; i < n2; ++i) {
                        sb.append((char)this.fData.byteAt(fCurrentOffset + i));
                    }
                    final int addString = this.fStringPool.addString(sb.toString());
                    ++super.fCurrentOffset;
                    return addString;
                }
                if (byte1 == -1) {
                    return -1;
                }
                ++super.fCurrentOffset;
            }
        }
        
        public void append(final CharBuffer charBuffer, final int n, final int n2) {
            throw new RuntimeException("cannot happen 7");
        }
        
        public int addString(final int n, final int n2) {
            throw new RuntimeException("cannot happen 8");
        }
        
        public int addSymbol(final int n, final int n2) {
            throw new RuntimeException("cannot happen 9");
        }
        
        public void skipToChar(final char c) throws IOException {
            throw new IOException("cannot happen 10");
        }
        
        public void skipPastName(final char c) throws IOException {
            throw new IOException("cannot happen 11");
        }
        
        public void skipPastNmtoken(final char c) throws IOException {
            throw new IOException("cannot happen 12");
        }
        
        public boolean lookingAtValidChar(final boolean b) throws IOException {
            throw new IOException("cannot happen 13");
        }
        
        public int scanInvalidChar() throws IOException {
            throw new IOException("cannot happen 14");
        }
        
        public int scanCharRef(final boolean b) throws IOException {
            throw new IOException("cannot happen 15");
        }
        
        public int scanAttValue(final char c, final boolean b) throws IOException {
            throw new IOException("cannot happen 15.2");
        }
        
        public int scanEntityValue(final int n, final boolean b) throws IOException {
            throw new IOException("cannot happen 15.3");
        }
        
        public boolean scanExpectedName(final char c, final StringPool.CharArrayRange charArrayRange) throws IOException {
            throw new IOException("cannot happen 16.1");
        }
        
        public int scanQName(final char c) throws IOException {
            throw new IOException("cannot happen 16.2");
        }
        
        public int scanName(final char c) throws IOException {
            throw new IOException("cannot happen 16.3");
        }
        
        public int scanContent(final int n) throws IOException {
            throw new IOException("cannot happen 17");
        }
    }
}
