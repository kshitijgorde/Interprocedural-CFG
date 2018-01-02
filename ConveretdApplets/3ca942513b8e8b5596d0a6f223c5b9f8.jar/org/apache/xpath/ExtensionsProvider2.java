// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath;

import org.apache.xpath.functions.FuncExtFunction;
import javax.xml.transform.TransformerException;
import java.util.Vector;

public interface ExtensionsProvider2 extends ExtensionsProvider
{
    Object extFunction(final XPathContext p0, final String p1, final String p2, final Vector p3, final Object p4) throws TransformerException;
    
    Object extFunction(final XPathContext p0, final FuncExtFunction p1, final Vector p2) throws TransformerException;
}
