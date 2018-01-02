// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.io;

import java.io.InputStream;
import java.io.File;
import java.net.URL;
import prefuse.data.Graph;

public interface GraphReader
{
    Graph readGraph(final String p0) throws DataIOException;
    
    Graph readGraph(final URL p0) throws DataIOException;
    
    Graph readGraph(final File p0) throws DataIOException;
    
    Graph readGraph(final InputStream p0) throws DataIOException;
}
