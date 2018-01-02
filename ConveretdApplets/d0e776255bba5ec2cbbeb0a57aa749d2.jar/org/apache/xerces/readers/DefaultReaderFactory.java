// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.readers;

import java.io.InputStream;
import org.apache.xerces.utils.ChunkyByteArray;
import java.net.URL;
import java.io.Reader;
import java.io.InputStreamReader;
import org.apache.xerces.utils.StringPool;
import org.xml.sax.InputSource;
import org.apache.xerces.framework.XMLErrorReporter;
import java.util.Stack;

public class DefaultReaderFactory implements XMLEntityReaderFactory
{
    private static final boolean USE_CHAR_READER_FOR_UTF8 = false;
    private static final boolean USE_BYTE_READER_FOR_UTF8 = true;
    private boolean fSendCharDataAsCharArray;
    private boolean fAllowJavaEncodingName;
    private Stack fRecognizers;
    
    public DefaultReaderFactory() {
        this.fSendCharDataAsCharArray = false;
        this.fAllowJavaEncodingName = false;
        this.fRecognizers = null;
    }
    
    public void addRecognizer(final XMLDeclRecognizer xmlDeclRecognizer) {
        if (this.fRecognizers == null) {
            XMLDeclRecognizer.registerDefaultRecognizers(this.fRecognizers = new Stack());
        }
        this.fRecognizers.push(xmlDeclRecognizer);
    }
    
    public void setSendCharDataAsCharArray(final boolean fSendCharDataAsCharArray) {
        this.fSendCharDataAsCharArray = fSendCharDataAsCharArray;
    }
    
    public void setAllowJavaEncodingName(final boolean fAllowJavaEncodingName) {
        this.fAllowJavaEncodingName = fAllowJavaEncodingName;
    }
    
    public boolean getAllowJavaEncodingName() {
        return this.fAllowJavaEncodingName;
    }
    
    public XMLEntityHandler.EntityReader createReader(final XMLEntityHandler xmlEntityHandler, final XMLErrorReporter xmlErrorReporter, final InputSource inputSource, final String s, final boolean b, final StringPool stringPool) throws Exception {
        if (inputSource.getCharacterStream() != null) {
            return this.createCharReader(xmlEntityHandler, xmlErrorReporter, this.fSendCharDataAsCharArray, inputSource.getCharacterStream(), stringPool);
        }
        if (inputSource.getEncoding() != null && inputSource.getByteStream() != null) {
            return this.createCharReader(xmlEntityHandler, xmlErrorReporter, this.fSendCharDataAsCharArray, new InputStreamReader(inputSource.getByteStream(), inputSource.getEncoding()), stringPool);
        }
        InputStream inputStream = inputSource.getByteStream();
        if (inputStream == null) {
            inputStream = new URL(s).openStream();
        }
        final ChunkyByteArray chunkyByteArray = new ChunkyByteArray(inputStream);
        if (this.fRecognizers == null) {
            XMLDeclRecognizer.registerDefaultRecognizers(this.fRecognizers = new Stack());
        }
        for (int i = this.fRecognizers.size() - 1; i >= 0; --i) {
            final XMLEntityHandler.EntityReader recognize = ((XMLDeclRecognizer)this.fRecognizers.elementAt(i)).recognize(this, xmlEntityHandler, xmlErrorReporter, this.fSendCharDataAsCharArray, stringPool, chunkyByteArray, b, this.fAllowJavaEncodingName);
            if (recognize != null) {
                return recognize;
            }
        }
        return this.createUTF8Reader(xmlEntityHandler, xmlErrorReporter, this.fSendCharDataAsCharArray, chunkyByteArray, stringPool);
    }
    
    public XMLEntityHandler.EntityReader createCharReader(final XMLEntityHandler xmlEntityHandler, final XMLErrorReporter xmlErrorReporter, final boolean b, final Reader reader, final StringPool stringPool) throws Exception {
        return new CharReader(xmlEntityHandler, xmlErrorReporter, b, reader, stringPool);
    }
    
    public XMLEntityHandler.EntityReader createUTF8Reader(final XMLEntityHandler xmlEntityHandler, final XMLErrorReporter xmlErrorReporter, final boolean b, final InputStream inputStream, final StringPool stringPool) throws Exception {
        return new UTF8Reader(xmlEntityHandler, xmlErrorReporter, b, inputStream, stringPool);
    }
    
    public XMLEntityHandler.EntityReader createStringReader(final XMLEntityHandler xmlEntityHandler, final XMLErrorReporter xmlErrorReporter, final boolean b, final int n, final int n2, final int n3, final StringPool stringPool, final boolean b2) throws Exception {
        return StringReader.createStringReader(xmlEntityHandler, xmlErrorReporter, b, n, n2, n3, stringPool, b2);
    }
}
