// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.trace;

import javax.xml.transform.TransformerException;
import java.util.EventListener;

public interface TraceListener extends EventListener
{
    void trace(final TracerEvent p0);
    
    void selected(final SelectionEvent p0) throws TransformerException;
    
    void generated(final GenerateEvent p0);
}
