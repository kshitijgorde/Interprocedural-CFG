// 
// Decompiled by Procyon v0.5.30
// 

package medusa;

import java.awt.Component;
import medusa.graph.Graph;

public interface MedusaDataConnection
{
    Graph getGraph();
    
    Graph getGraph(final String p0, final Component p1);
}
