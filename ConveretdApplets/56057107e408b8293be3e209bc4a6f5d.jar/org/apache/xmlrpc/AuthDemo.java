// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xmlrpc;

import java.util.Vector;

public class AuthDemo implements AuthenticatedXmlRpcHandler
{
    public Object execute(final String s, final Vector vector, final String s2, final String s3) throws Exception {
        if (s2 == null || s2.startsWith("bad")) {
            throw new XmlRpcException(5, "Sorry, you're not allowed in here!");
        }
        return "Hello " + s2;
    }
}
