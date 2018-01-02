// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.io;

import java.io.OutputStream;
import java.io.File;
import prefuse.data.Graph;

public interface GraphWriter
{
    void writeGraph(final Graph p0, final String p1) throws DataIOException;
    
    void writeGraph(final Graph p0, final File p1) throws DataIOException;
    
    void writeGraph(final Graph p0, final OutputStream p1) throws DataIOException;
}
