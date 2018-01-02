// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graphApi.algorithms;

import hypergraph.graphApi.Element;
import java.util.EventListener;

public interface GraphWalkerListener extends EventListener
{
    void visitElement(final Element p0);
}
