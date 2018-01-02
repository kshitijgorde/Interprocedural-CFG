// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.trace;

import javax.xml.transform.TransformerException;

public interface TraceListenerEx extends TraceListener
{
    void selectEnd(final EndSelectionEvent p0) throws TransformerException;
}
