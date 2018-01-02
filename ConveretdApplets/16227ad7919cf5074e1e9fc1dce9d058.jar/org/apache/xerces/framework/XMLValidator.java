// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.framework;

import org.xml.sax.Locator;

public interface XMLValidator
{
    void rootElementSpecified(final int p0) throws Exception;
    
    boolean attributeSpecified(final int p0, final XMLAttrList p1, final int p2, final Locator p3, final int p4) throws Exception;
    
    boolean startElement(final int p0, final XMLAttrList p1) throws Exception;
    
    boolean endElement(final int p0) throws Exception;
    
    void characters(final char[] p0, final int p1, final int p2) throws Exception;
    
    void characters(final int p0) throws Exception;
    
    void ignorableWhitespace(final char[] p0, final int p1, final int p2) throws Exception;
    
    void ignorableWhitespace(final int p0) throws Exception;
    
    boolean externalReferenceInContent(final int p0) throws Exception;
    
    int valueOfReferenceInAttValue(final int p0) throws Exception;
    
    int lookupEntity(final int p0) throws Exception;
    
    boolean isUnparsedEntity(final int p0) throws Exception;
    
    boolean isExternalEntity(final int p0) throws Exception;
    
    int getEntityValue(final int p0) throws Exception;
    
    String getPublicIdOfEntity(final int p0) throws Exception;
    
    String getSystemIdOfEntity(final int p0) throws Exception;
    
    int lookupParameterEntity(final int p0) throws Exception;
    
    boolean isExternalParameterEntity(final int p0) throws Exception;
    
    int getParameterEntityValue(final int p0) throws Exception;
    
    String getPublicIdOfParameterEntity(final int p0) throws Exception;
    
    String getSystemIdOfParameterEntity(final int p0) throws Exception;
    
    int checkContent(final int p0, final int p1, final int[] p2) throws Exception;
    
    String getContentSpecAsString(final int p0);
    
    public interface ContentSpec
    {
        String toString();
        
        int getType();
        
        int getHandle();
        
        void getNode(final int p0, final XMLContentSpecNode p1);
    }
}
