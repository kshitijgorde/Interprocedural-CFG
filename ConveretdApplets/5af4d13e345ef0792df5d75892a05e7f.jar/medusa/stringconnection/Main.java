// 
// Decompiled by Procyon v0.5.30
// 

package medusa.stringconnection;

import medusa.graph.Graph;
import java.awt.Component;
import medusa.MedusaDataConnection;

public class Main implements MedusaDataConnection
{
    public Graph getGraph(final String s, final Component component) {
        return DataConnection.showDialog(s, component, null);
    }
    
    public Graph getGraph() {
        return this.getGraph(null, null);
    }
}
