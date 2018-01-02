// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xmlrpc;

import java.util.Vector;

public class Echo implements XmlRpcHandler
{
    public Object execute(final String s, final Vector vector) throws Exception {
        return vector;
    }
}
