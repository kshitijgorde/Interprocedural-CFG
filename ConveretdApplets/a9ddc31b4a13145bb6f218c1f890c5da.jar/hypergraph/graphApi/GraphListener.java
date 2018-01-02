// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graphApi;

import java.util.EventListener;

public interface GraphListener extends EventListener
{
    void elementsAdded(final GraphEvent p0);
    
    void elementsRemoved(final GraphEvent p0);
    
    void structureChanged(final GraphEvent p0);
}
