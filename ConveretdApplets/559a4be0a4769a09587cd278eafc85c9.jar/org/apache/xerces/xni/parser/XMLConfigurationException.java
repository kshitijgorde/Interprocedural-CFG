// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xni.parser;

import org.apache.xerces.xni.XNIException;

public class XMLConfigurationException extends XNIException
{
    public static final short NOT_RECOGNIZED = 0;
    public static final short NOT_SUPPORTED = 1;
    protected short fType;
    protected String fIdentifier;
    
    public XMLConfigurationException(final short type, final String identifier) {
        super(identifier);
        this.fType = type;
        this.fIdentifier = identifier;
    }
    
    public XMLConfigurationException(final short type, final String identifier, final String message) {
        super(message);
        this.fType = type;
        this.fIdentifier = identifier;
    }
    
    public short getType() {
        return this.fType;
    }
    
    public String getIdentifier() {
        return this.fIdentifier;
    }
}
