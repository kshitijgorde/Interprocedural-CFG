// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graph;

import java.util.Properties;
import java.io.File;
import java.io.InputStream;
import hypergraph.graphApi.Graph;
import hypergraph.graphApi.GraphSystem;

public class GraphSystemImpl implements GraphSystem
{
    public Graph createGraph() {
        return new GraphImpl(this);
    }
    
    public Graph readGraph(final InputStream inputStream, final int n) {
        return this.createGraph();
    }
    
    public Graph readGraph(final File file) {
        return this.createGraph();
    }
    
    public void setProperties(final Properties properties) {
    }
    
    public static String createId() {
        return System.currentTimeMillis() + "" + Math.random();
    }
}
