// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.io;

import java.io.InputStream;
import java.io.File;
import java.net.URL;
import prefuse.data.Table;

public interface TableReader
{
    Table readTable(final String p0) throws DataIOException;
    
    Table readTable(final URL p0) throws DataIOException;
    
    Table readTable(final File p0) throws DataIOException;
    
    Table readTable(final InputStream p0) throws DataIOException;
}
