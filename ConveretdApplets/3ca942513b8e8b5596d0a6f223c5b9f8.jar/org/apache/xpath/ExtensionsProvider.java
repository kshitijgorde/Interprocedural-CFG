// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath;

import org.apache.xpath.functions.FuncExtFunction;
import java.util.Vector;
import javax.xml.transform.TransformerException;

public interface ExtensionsProvider
{
    boolean functionAvailable(final String p0, final String p1) throws TransformerException;
    
    boolean elementAvailable(final String p0, final String p1) throws TransformerException;
    
    Object extFunction(final String p0, final String p1, final Vector p2, final Object p3) throws TransformerException;
    
    Object extFunction(final FuncExtFunction p0, final Vector p1) throws TransformerException;
}
