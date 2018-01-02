// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xni.parser;

public interface XMLComponentManager
{
    boolean getFeature(final String p0) throws XMLConfigurationException;
    
    Object getProperty(final String p0) throws XMLConfigurationException;
}
