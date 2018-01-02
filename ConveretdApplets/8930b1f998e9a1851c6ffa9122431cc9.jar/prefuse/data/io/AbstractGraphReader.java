// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.io;

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.File;
import java.net.URL;
import java.io.InputStream;
import java.io.IOException;
import prefuse.util.io.IOLib;
import prefuse.data.Graph;

public abstract class AbstractGraphReader implements GraphReader
{
    public Graph readGraph(final String s) throws DataIOException {
        try {
            final InputStream streamFromString = IOLib.streamFromString(s);
            if (streamFromString == null) {
                throw new DataIOException("Couldn't find " + s + ". Not a valid file, URL, or resource locator.");
            }
            return this.readGraph(streamFromString);
        }
        catch (IOException ex) {
            throw new DataIOException(ex);
        }
    }
    
    public Graph readGraph(final URL url) throws DataIOException {
        try {
            return this.readGraph(url.openStream());
        }
        catch (IOException ex) {
            throw new DataIOException(ex);
        }
    }
    
    public Graph readGraph(final File file) throws DataIOException {
        try {
            return this.readGraph(new FileInputStream(file));
        }
        catch (FileNotFoundException ex) {
            throw new DataIOException(ex);
        }
    }
    
    public abstract Graph readGraph(final InputStream p0) throws DataIOException;
}
