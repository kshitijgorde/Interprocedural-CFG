// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.scan;

import com.ibm.xml.b2b.util.entity.ParsedEntity;
import com.ibm.xml.b2b.util.XMLString;
import com.ibm.xml.b2b.util.XMLName;
import com.ibm.xml.b2b.util.QName;

public abstract class DocumentEntityState
{
    public QName elementType;
    public QName attributeName;
    public XMLName entityName;
    public XMLName target;
    public XMLString content;
    public QName currentElement;
    public QName currentAttribute;
    public int elementDepth;
    public static final int RESULT_NONAME = 0;
    public static final int RESULT_COLON = 1;
    public static final int RESULT_ENDOFNAME = 2;
    public static final int RESULT_FORWARDSLASH = 3;
    public static final int RESULT_GREATERTHAN = 4;
    public static final int RESULT_WHITESPACE = 5;
    public static final int RESULT_EQUALSIGN = 6;
    public static final int RESULT_SEMICOLON = 7;
    public static final int NAMESTARTCHAR = 1;
    public static final int NAMECHAR = 2;
    public static final int COLON = 4;
    public static final int INVALID = 8;
    public static final int FORWARDSLASH = 12;
    public static final int GREATERTHAN = 16;
    public static final int WHITESPACE = 20;
    public static final int EQUALSIGN = 24;
    public static final int SEMICOLON = 28;
    public static final byte[] contentMap;
    public static final byte[] attValueMap;
    public static final byte[] nameCharMap;
    
    public DocumentEntityState() {
        this.elementType = new QName();
        this.attributeName = new QName();
        this.entityName = new XMLName();
        this.target = new XMLName();
        this.content = new XMLString();
    }
    
    public void reset(final boolean b) {
        this.elementDepth = 0;
    }
    
    public abstract void createQNameSymbols(final QName p0);
    
    public abstract void processElementType();
    
    public abstract void processAttributeName(final QName p0, final boolean p1);
    
    public abstract void attributeValueCharacters(final XMLString p0, final boolean p1);
    
    public abstract void attributeValueCharacter(final int p0, final boolean p1);
    
    public abstract void endOfSpecifiedAttributes();
    
    public abstract void pushElement();
    
    public abstract QName popElement();
    
    public abstract void endNamespacesScope();
    
    public abstract int scanQName(final ParsedEntity p0, final QName p1);
    
    public abstract int scanName(final ParsedEntity p0, final XMLName p1);
    
    public abstract boolean scanComment(final ParsedEntity p0, final XMLString p1);
    
    public abstract boolean scanPITarget(final ParsedEntity p0, final XMLName p1);
    
    public abstract boolean scanPIData(final ParsedEntity p0, final XMLString p1);
    
    public abstract int scanCharacterReference(final ParsedEntity p0);
    
    public abstract int checkPredefinedEntities(final XMLName p0);
    
    public abstract void undeclaredEntityInContent(final XMLName p0);
    
    public abstract void undeclaredEntityInAttValue(final XMLName p0);
    
    public abstract void setParameter(final int p0, final XMLString p1);
    
    public abstract void setInvalidCharParameter(final int p0, final ParsedEntity p1);
    
    public abstract void reportFatalError(final String p0, final int p1);
    
    static {
        contentMap = new byte[] { 5, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
        attValueMap = new byte[] { 5, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 4, 1, 1, 1, 3, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
        nameCharMap = new byte[] { 8, 8, 8, 8, 8, 8, 8, 8, 8, 20, 20, 8, 8, 20, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 20, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 2, 2, 12, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, 28, 8, 24, 16, 8, 8, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 8, 8, 8, 8, 3, 8, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 8, 8, 8, 8, 8 };
    }
}
