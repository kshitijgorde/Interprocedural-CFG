// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.io;

import prefuse.util.collections.IntIterator;
import java.io.PrintStream;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import prefuse.data.Table;

public class DelimitedTextTableWriter extends AbstractTableWriter
{
    private String m_delim;
    private boolean m_printHeader;
    
    public DelimitedTextTableWriter() {
        this("\t");
    }
    
    public DelimitedTextTableWriter(final String s) {
        this(s, true);
    }
    
    public DelimitedTextTableWriter(final String delim, final boolean printHeader) {
        this.m_delim = delim;
        this.m_printHeader = printHeader;
    }
    
    public String getDelimiter() {
        return this.m_delim;
    }
    
    public void setDelimeter(final String delim) {
        this.m_delim = delim;
    }
    
    public boolean isPrintHeader() {
        return this.m_printHeader;
    }
    
    public void setPrintHeader(final boolean printHeader) {
        this.m_printHeader = printHeader;
    }
    
    public void writeTable(final Table table, final OutputStream outputStream) throws DataIOException {
        try {
            final PrintStream printStream = new PrintStream(new BufferedOutputStream(outputStream));
            if (this.m_printHeader) {
                for (int i = 0; i < table.getColumnCount(); ++i) {
                    if (i > 0) {
                        printStream.print(this.m_delim);
                    }
                    printStream.print(table.getColumnName(i));
                }
                printStream.println();
            }
            final IntIterator rows = table.rows();
            while (rows.hasNext()) {
                final int nextInt = rows.nextInt();
                for (int j = 0; j < table.getColumnCount(); ++j) {
                    if (j > 0) {
                        printStream.print(this.m_delim);
                    }
                    printStream.print(table.getString(nextInt, table.getColumnName(j)));
                }
                printStream.println();
            }
            printStream.flush();
        }
        catch (Exception ex) {
            throw new DataIOException(ex);
        }
    }
}
