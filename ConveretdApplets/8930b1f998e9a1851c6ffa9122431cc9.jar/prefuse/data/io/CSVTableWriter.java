// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.io;

import prefuse.util.collections.IntIterator;
import java.io.PrintStream;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import prefuse.data.Table;

public class CSVTableWriter extends AbstractTableWriter
{
    private boolean m_printHeader;
    
    public CSVTableWriter() {
        this(true);
    }
    
    public CSVTableWriter(final boolean printHeader) {
        this.m_printHeader = printHeader;
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
                        printStream.print(',');
                    }
                    printStream.print(this.makeCSVSafe(table.getColumnName(i)));
                }
                printStream.println();
            }
            final IntIterator rows = table.rows();
            while (rows.hasNext()) {
                final int nextInt = rows.nextInt();
                for (int j = 0; j < table.getColumnCount(); ++j) {
                    if (j > 0) {
                        printStream.print(',');
                    }
                    printStream.print(this.makeCSVSafe(table.getString(nextInt, table.getColumnName(j))));
                }
                printStream.println();
            }
            printStream.flush();
        }
        catch (Exception ex) {
            throw new DataIOException(ex);
        }
    }
    
    private String makeCSVSafe(String s) {
        final int index;
        if ((index = s.indexOf(34)) >= 0 || s.indexOf(44) >= 0 || s.indexOf(10) >= 0 || Character.isWhitespace(s.charAt(0)) || Character.isWhitespace(s.charAt(s.length() - 1))) {
            if (index >= 0) {
                s = s.replaceAll("\"", "\"\"");
            }
            s = "\"" + s + "\"";
        }
        return s;
    }
}
