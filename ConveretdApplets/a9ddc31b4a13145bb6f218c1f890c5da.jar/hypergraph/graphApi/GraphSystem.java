// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graphApi;

import java.util.Properties;
import java.io.File;
import java.io.InputStream;

public interface GraphSystem
{
    public static final int FORMAT_GML = 0;
    public static final int FORMAT_GRAPHXML = 1;
    public static final int FORMAT_DOT = 2;
    
    Graph createGraph();
    
    Graph readGraph(final InputStream p0, final int p1);
    
    Graph readGraph(final File p0);
    
    void setProperties(final Properties p0);
}
