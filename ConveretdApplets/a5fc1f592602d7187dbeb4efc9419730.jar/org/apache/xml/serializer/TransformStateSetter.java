// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer;

import javax.xml.transform.Transformer;
import org.w3c.dom.Node;

public interface TransformStateSetter
{
    void setCurrentNode(final Node p0);
    
    void resetState(final Transformer p0);
}
