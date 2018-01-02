// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xmlrpc;

public class XmlRpcException extends Exception
{
    public final int code;
    
    public XmlRpcException(final int code, final String s) {
        super(s);
        this.code = code;
    }
}
