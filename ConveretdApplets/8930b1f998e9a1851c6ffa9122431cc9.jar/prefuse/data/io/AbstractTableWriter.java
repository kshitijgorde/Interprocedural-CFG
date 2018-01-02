// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.io;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.File;
import prefuse.data.Table;

public abstract class AbstractTableWriter implements TableWriter
{
    public void writeTable(final Table table, final String s) throws DataIOException {
        this.writeTable(table, new File(s));
    }
    
    public void writeTable(final Table table, final File file) throws DataIOException {
        try {
            this.writeTable(table, new FileOutputStream(file));
        }
        catch (FileNotFoundException ex) {
            throw new DataIOException(ex);
        }
    }
}
