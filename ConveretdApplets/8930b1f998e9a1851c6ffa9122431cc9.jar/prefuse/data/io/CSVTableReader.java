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

public class CSVTableReader extends AbstractTextTableReader
{
    public CSVTableReader() {
    }
    
    public CSVTableReader(final ParserFactory parserFactory) {
        super(parserFactory);
    }
    
    public void read(final InputStream inputStream, final TableReadListener tableReadListener) throws IOException, DataParseException {
        final StringBuffer sb = new StringBuffer();
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        String line;
        while ((line = new BufferedReader(new InputStreamReader(inputStream)).readLine()) != null) {
            ++n3;
            final char[] charArray = line.toCharArray();
            for (int n5 = charArray.length - 1, i = 0; i <= n5; ++i) {
                if (n == 0) {
                    if (!Character.isWhitespace(charArray[i])) {
                        if (charArray[i] == '\"') {
                            n = 1;
                            n2 = 1;
                        }
                        else if (charArray[i] == ',') {
                            tableReadListener.readValue(n3, ++n4, sb.toString().trim());
                            sb.delete(0, sb.length());
                        }
                        else {
                            n = 1;
                            sb.append(charArray[i]);
                        }
                    }
                }
                else if (n2 == 1) {
                    if (charArray[i] == '\"' && (i == n5 || charArray[i + 1] != '\"')) {
                        n2 = 2;
                    }
                    else if (charArray[i] == '\"') {
                        sb.append(charArray[i++]);
                    }
                    else {
                        sb.append(charArray[i]);
                    }
                }
                else if (Character.isWhitespace(charArray[i])) {
                    sb.append(charArray[i]);
                }
                else {
                    if (charArray[i] != ',' && n2 == 2) {
                        throw new IllegalStateException("Invalid data format. Error at line " + n3 + ", col " + i);
                    }
                    if (charArray[i] != ',') {
                        sb.append(charArray[i]);
                    }
                    else {
                        tableReadListener.readValue(n3, ++n4, sb.toString().trim());
                        sb.delete(0, sb.length());
                        n2 = 0;
                        n = 0;
                    }
                }
            }
            if (n2 != 1) {
                tableReadListener.readValue(n3, ++n4, sb.toString().trim());
                sb.delete(0, sb.length());
                n2 = 0;
                n = 0;
            }
            if (n == 0 && n4 > 0) {
                n4 = 0;
            }
        }
    }
}
