// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xni;

public interface XMLLocator
{
    String getPublicId();
    
    String getLiteralSystemId();
    
    String getBaseSystemId();
    
    String getExpandedSystemId();
    
    int getLineNumber();
    
    int getColumnNumber();
    
    int getCharacterOffset();
    
    String getEncoding();
    
    String getXMLVersion();
}
