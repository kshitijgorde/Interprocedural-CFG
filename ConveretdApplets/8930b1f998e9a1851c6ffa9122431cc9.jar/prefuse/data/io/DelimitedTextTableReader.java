// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.io;

import prefuse.data.parser.DataParseException;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import prefuse.data.parser.ParserFactory;

public class DelimitedTextTableReader extends AbstractTextTableReader
{
    private String m_delim;
    
    public DelimitedTextTableReader() {
        this("\t");
    }
    
    public DelimitedTextTableReader(final ParserFactory parserFactory) {
        this("\t", parserFactory);
    }
    
    public DelimitedTextTableReader(final String delim) {
        this.m_delim = delim;
    }
    
    public DelimitedTextTableReader(final String delim, final ParserFactory parserFactory) {
        super(parserFactory);
        this.m_delim = delim;
    }
    
    protected void read(final InputStream inputStream, final TableReadListener tableReadListener) throws IOException, DataParseException {
        int n = 0;
        String line;
        while ((line = new BufferedReader(new InputStreamReader(inputStream)).readLine()) != null) {
            ++n;
            final String[] split = line.split(this.m_delim);
            for (int i = 0; i < split.length; ++i) {
                tableReadListener.readValue(n, i + 1, split[i]);
            }
        }
    }
}
