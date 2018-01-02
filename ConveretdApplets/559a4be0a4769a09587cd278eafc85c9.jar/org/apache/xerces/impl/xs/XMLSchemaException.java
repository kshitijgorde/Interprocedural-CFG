// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

public class XMLSchemaException extends Exception
{
    String key;
    Object[] args;
    
    public XMLSchemaException(final String key, final Object[] args) {
        this.key = key;
        this.args = args;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public Object[] getArgs() {
        return this.args;
    }
}
