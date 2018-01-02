// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.readers;

import org.apache.xerces.utils.QName;
import org.apache.xerces.utils.StringPool;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;

public interface XMLEntityHandler extends Locator
{
    public static final int CHARREF_RESULT_SEMICOLON_REQUIRED = -1;
    public static final int CHARREF_RESULT_INVALID_CHAR = -2;
    public static final int CHARREF_RESULT_OUT_OF_RANGE = -3;
    public static final int STRINGLIT_RESULT_QUOTE_REQUIRED = -1;
    public static final int STRINGLIT_RESULT_INVALID_CHAR = -2;
    public static final int ATTVALUE_RESULT_COMPLEX = -1;
    public static final int ATTVALUE_RESULT_LESSTHAN = -2;
    public static final int ATTVALUE_RESULT_INVALID_CHAR = -3;
    public static final int ENTITYVALUE_RESULT_FINISHED = -1;
    public static final int ENTITYVALUE_RESULT_REFERENCE = -2;
    public static final int ENTITYVALUE_RESULT_PEREF = -3;
    public static final int ENTITYVALUE_RESULT_INVALID_CHAR = -4;
    public static final int ENTITYVALUE_RESULT_END_OF_INPUT = -5;
    public static final int CONTENT_RESULT_START_OF_PI = 0;
    public static final int CONTENT_RESULT_START_OF_COMMENT = 1;
    public static final int CONTENT_RESULT_START_OF_CDSECT = 2;
    public static final int CONTENT_RESULT_END_OF_CDSECT = 3;
    public static final int CONTENT_RESULT_START_OF_ETAG = 4;
    public static final int CONTENT_RESULT_MATCHING_ETAG = 5;
    public static final int CONTENT_RESULT_START_OF_ELEMENT = 6;
    public static final int CONTENT_RESULT_START_OF_CHARREF = 7;
    public static final int CONTENT_RESULT_START_OF_ENTITYREF = 8;
    public static final int CONTENT_RESULT_INVALID_CHAR = 9;
    public static final int CONTENT_RESULT_MARKUP_NOT_RECOGNIZED = 10;
    public static final int CONTENT_RESULT_MARKUP_END_OF_INPUT = 11;
    public static final int CONTENT_RESULT_REFERENCE_END_OF_INPUT = 12;
    public static final int ENTITYTYPE_INTERNAL_PE = 0;
    public static final int ENTITYTYPE_EXTERNAL_PE = 1;
    public static final int ENTITYTYPE_INTERNAL = 2;
    public static final int ENTITYTYPE_EXTERNAL = 3;
    public static final int ENTITYTYPE_UNPARSED = 4;
    public static final int ENTITYTYPE_DOCUMENT = 5;
    public static final int ENTITYTYPE_EXTERNAL_SUBSET = 6;
    public static final int ENTITYREF_IN_ATTVALUE = 0;
    public static final int ENTITYREF_IN_DEFAULTATTVALUE = 1;
    public static final int ENTITYREF_IN_CONTENT = 2;
    public static final int ENTITYREF_IN_DTD_AS_MARKUP = 3;
    public static final int ENTITYREF_IN_ENTITYVALUE = 4;
    public static final int ENTITYREF_IN_DTD_WITHIN_MARKUP = 5;
    public static final int ENTITYREF_DOCUMENT = 6;
    public static final int ENTITYREF_EXTERNAL_SUBSET = 7;
    
    boolean startReadingFromDocument(final InputSource p0) throws Exception;
    
    boolean startReadingFromEntity(final int p0, final int p1, final int p2) throws Exception;
    
    String expandSystemId(final String p0);
    
    int getReaderId();
    
    void setReaderDepth(final int p0);
    
    int getReaderDepth();
    
    EntityReader getEntityReader();
    
    EntityReader changeReaders() throws Exception;
    
    void setCharDataHandler(final CharDataHandler p0);
    
    CharDataHandler getCharDataHandler();
    
    public interface EntityReader
    {
        int currentOffset();
        
        int getLineNumber();
        
        int getColumnNumber();
        
        void setInCDSect(final boolean p0);
        
        boolean getInCDSect();
        
        void append(final CharBuffer p0, final int p1, final int p2);
        
        int addString(final int p0, final int p1);
        
        int addSymbol(final int p0, final int p1);
        
        boolean lookingAtChar(final char p0, final boolean p1) throws Exception;
        
        boolean lookingAtValidChar(final boolean p0) throws Exception;
        
        boolean lookingAtSpace(final boolean p0) throws Exception;
        
        void skipToChar(final char p0) throws Exception;
        
        void skipPastSpaces() throws Exception;
        
        void skipPastName(final char p0) throws Exception;
        
        void skipPastNmtoken(final char p0) throws Exception;
        
        boolean skippedString(final char[] p0) throws Exception;
        
        int scanInvalidChar() throws Exception;
        
        int scanCharRef(final boolean p0) throws Exception;
        
        int scanStringLiteral() throws Exception;
        
        int scanAttValue(final char p0, final boolean p1) throws Exception;
        
        int scanEntityValue(final int p0, final boolean p1) throws Exception;
        
        int scanName(final char p0) throws Exception;
        
        boolean scanExpectedName(final char p0, final StringPool.CharArrayRange p1) throws Exception;
        
        void scanQName(final char p0, final QName p1) throws Exception;
        
        int scanContent(final QName p0) throws Exception;
    }
    
    public interface CharBuffer
    {
        void append(final char p0);
        
        void append(final char[] p0, final int p1, final int p2);
        
        int length();
        
        int addString(final int p0, final int p1);
    }
    
    public interface CharDataHandler
    {
        void processCharacters(final char[] p0, final int p1, final int p2) throws Exception;
        
        void processCharacters(final int p0) throws Exception;
        
        void processWhitespace(final char[] p0, final int p1, final int p2) throws Exception;
        
        void processWhitespace(final int p0) throws Exception;
    }
    
    public interface DTDHandler
    {
        void startReadingFromExternalSubset(final String p0, final String p1, final int p2) throws Exception;
        
        void stopReadingFromExternalSubset() throws Exception;
        
        boolean startEntityDecl(final boolean p0, final int p1) throws Exception;
        
        void endEntityDecl() throws Exception;
        
        int addInternalPEDecl(final int p0, final int p1, final boolean p2) throws Exception;
        
        int addExternalPEDecl(final int p0, final int p1, final int p2, final boolean p3) throws Exception;
        
        int addInternalEntityDecl(final int p0, final int p1, final boolean p2) throws Exception;
        
        int addExternalEntityDecl(final int p0, final int p1, final int p2, final boolean p3) throws Exception;
        
        int addUnparsedEntityDecl(final int p0, final int p1, final int p2, final int p3, final boolean p4) throws Exception;
        
        int addNotationDecl(final int p0, final int p1, final int p2, final boolean p3) throws Exception;
        
        boolean isUnparsedEntity(final int p0);
        
        boolean isNotationDeclared(final int p0);
        
        void addRequiredNotation(final int p0, final Locator p1, final int p2, final int p3, final Object[] p4);
        
        void checkRequiredNotations() throws Exception;
    }
}
