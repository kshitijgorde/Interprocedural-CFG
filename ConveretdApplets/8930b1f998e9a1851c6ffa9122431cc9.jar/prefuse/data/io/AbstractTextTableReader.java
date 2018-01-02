// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.io;

import prefuse.util.collections.ByteArrayList;
import prefuse.data.parser.DataParseException;
import java.util.ArrayList;
import prefuse.data.parser.TypeInferencer;
import java.io.IOException;
import prefuse.util.io.IOLib;
import prefuse.data.Table;
import java.io.InputStream;
import prefuse.data.parser.ParserFactory;

public abstract class AbstractTextTableReader extends AbstractTableReader
{
    private ParserFactory m_pfactory;
    private boolean m_hasHeader;
    
    public AbstractTextTableReader() {
        this(ParserFactory.getDefaultFactory());
    }
    
    public AbstractTextTableReader(final ParserFactory pfactory) {
        this.m_pfactory = pfactory;
        this.m_hasHeader = true;
    }
    
    public void setHasHeader(final boolean hasHeader) {
        this.m_hasHeader = hasHeader;
    }
    
    public Table readTable(InputStream inputStream) throws DataIOException {
        ByteArrayList asBytes = null;
        if (inputStream.markSupported()) {
            inputStream.mark(Integer.MAX_VALUE);
        }
        else {
            try {
                asBytes = IOLib.readAsBytes(inputStream);
            }
            catch (IOException ex) {
                throw new DataIOException(ex);
            }
            inputStream = asBytes.getAsInputStream();
        }
        final TypeInferencer typeInferencer = new TypeInferencer(this.m_pfactory);
        final ArrayList<String> list = new ArrayList<String>();
        final int[] array = { 0, 0 };
        final TableReadListener tableReadListener = new TableReadListener() {
            int prevLine = -1;
            
            public void readValue(final int prevLine, final int n, final String s) throws DataParseException {
                if (prevLine > 1 || !AbstractTextTableReader.this.m_hasHeader) {
                    typeInferencer.sample(n - 1, s);
                    if (prevLine != this.prevLine) {
                        this.prevLine = prevLine;
                        final int[] val$dim = array;
                        final int n2 = 0;
                        ++val$dim[n2];
                    }
                }
                else if (prevLine == 1 && AbstractTextTableReader.this.m_hasHeader) {
                    list.add(s);
                }
                if (n > array[1]) {
                    array[1] = n;
                }
            }
        };
        try {
            this.read(inputStream, tableReadListener);
        }
        catch (IOException ex2) {
            throw new DataIOException(ex2);
        }
        catch (DataParseException ex5) {}
        final int n = array[0];
        final int n2 = array[1];
        final Table table = new Table(n, n2);
        for (int i = 0; i < n2; ++i) {
            String defaultHeader;
            if (this.m_hasHeader) {
                defaultHeader = list.get(i);
            }
            else {
                defaultHeader = getDefaultHeader(i);
            }
            table.addColumn(defaultHeader, typeInferencer.getType(i));
            table.getColumn(i).setParser(typeInferencer.getParser(i));
        }
        array[0] = (array[1] = -1);
        final TableReadListener tableReadListener2 = new TableReadListener() {
            int prevLine = -1;
            
            public void readValue(final int prevLine, final int n, final String s) throws DataParseException {
                if (prevLine == 1 && AbstractTextTableReader.this.m_hasHeader) {
                    return;
                }
                if (prevLine != this.prevLine) {
                    this.prevLine = prevLine;
                    final int[] val$dim = array;
                    final int n2 = 0;
                    ++val$dim[n2];
                }
                array[1] = n - 1;
                table.set(array[0], array[1], typeInferencer.getParser(array[1]).parse(s));
            }
        };
        try {
            if (inputStream.markSupported()) {
                inputStream.reset();
            }
            else {
                inputStream = asBytes.getAsInputStream();
            }
            this.read(inputStream, tableReadListener2);
        }
        catch (IOException ex3) {
            throw new DataIOException(ex3);
        }
        catch (DataParseException ex4) {
            throw new DataIOException("Parse exception for column \"" + array[1] + '\"' + " at row: " + array[0], ex4);
        }
        return table;
    }
    
    public static String getDefaultHeader(int i) {
        if (i == 0) {
            return "A";
        }
        final int n = (int)(Math.log(i) / Math.log(26.0)) + 1;
        final char[] array = new char[n];
        int n2 = n;
        array[--n2] = (char)(65 + i % 26);
        for (i /= 26; i > 26; i /= 26) {
            array[--n2] = (char)(65 + i % 26);
        }
        if (i > 0) {
            array[--n2] = (char)(65 + (i - 1) % 26);
        }
        return new String(array, n2, n);
    }
    
    protected abstract void read(final InputStream p0, final TableReadListener p1) throws IOException, DataParseException;
}
