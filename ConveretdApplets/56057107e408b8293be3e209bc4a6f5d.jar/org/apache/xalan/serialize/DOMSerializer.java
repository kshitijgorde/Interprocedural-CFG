// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.serialize;

import java.io.IOException;
import org.w3c.dom.Node;

public interface DOMSerializer
{
    void serialize(final Node p0) throws IOException;
}
