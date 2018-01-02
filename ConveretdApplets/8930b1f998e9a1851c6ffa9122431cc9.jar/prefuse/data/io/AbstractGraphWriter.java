// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.io;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.File;
import prefuse.data.Graph;

public abstract class AbstractGraphWriter implements GraphWriter
{
    public void writeGraph(final Graph graph, final String s) throws DataIOException {
        this.writeGraph(graph, new File(s));
    }
    
    public void writeGraph(final Graph graph, final File file) throws DataIOException {
        try {
            this.writeGraph(graph, new FileOutputStream(file));
        }
        catch (FileNotFoundException ex) {
            throw new DataIOException(ex);
        }
    }
}
