// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xni;

public interface XMLResourceIdentifier
{
    String getPublicId();
    
    String getExpandedSystemId();
    
    String getLiteralSystemId();
    
    String getBaseSystemId();
}
