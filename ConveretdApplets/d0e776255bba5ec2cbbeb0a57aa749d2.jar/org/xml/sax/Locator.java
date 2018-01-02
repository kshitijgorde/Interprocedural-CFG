// 
// Decompiled by Procyon v0.5.30
// 

package org.xml.sax;

public interface Locator
{
    String getPublicId();
    
    String getSystemId();
    
    int getLineNumber();
    
    int getColumnNumber();
}
