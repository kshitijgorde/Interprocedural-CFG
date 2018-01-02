// 
// Decompiled by Procyon v0.5.30
// 

package processing.xml;

import java.io.IOException;
import java.io.Reader;

class PIReader extends Reader
{
    private StdXMLReader reader;
    private boolean atEndOfData;
    
    PIReader(final StdXMLReader reader) {
        this.reader = reader;
        this.atEndOfData = false;
    }
    
    protected void finalize() throws Throwable {
        this.reader = null;
        super.finalize();
    }
    
    public int read(final char[] array, final int n, int n2) throws IOException {
        if (this.atEndOfData) {
            return -1;
        }
        int i = 0;
        if (n + n2 > array.length) {
            n2 = array.length - n;
        }
        while (i < n2) {
            final char read = this.reader.read();
            if (read == '?') {
                final char read2 = this.reader.read();
                if (read2 == '>') {
                    this.atEndOfData = true;
                    break;
                }
                this.reader.unread(read2);
            }
            array[i] = read;
            ++i;
        }
        if (i == 0) {
            i = -1;
        }
        return i;
    }
    
    public void close() throws IOException {
        while (!this.atEndOfData) {
            if (this.reader.read() == '?' && this.reader.read() == '>') {
                this.atEndOfData = true;
            }
        }
    }
}
