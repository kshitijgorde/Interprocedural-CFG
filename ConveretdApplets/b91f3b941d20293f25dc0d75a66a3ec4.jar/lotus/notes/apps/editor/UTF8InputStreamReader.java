// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class UTF8InputStreamReader extends Reader
{
    private InputStream cStream;
    
    public UTF8InputStreamReader(final InputStream cStream) {
        this.cStream = cStream;
    }
    
    public void close() throws IOException {
        this.cStream.close();
    }
    
    public int read() throws IOException {
        final int read = this.cStream.read();
        if ((read & 0x80) == 0x0 || read == -1) {
            return read;
        }
        if ((read & 0xE0) == 0xC0) {
            return (read & 0x1F) << 6 | (this.cStream.read() & 0x3F);
        }
        return (read & 0xF) << 12 | (this.cStream.read() & 0x3F) << 6 | (this.cStream.read() & 0x3F);
    }
    
    public int read(final char[] array, int n, final int n2) throws IOException {
        final int read = this.read();
        if (read == -1) {
            return -1;
        }
        array[n++] = (char)read;
        int i;
        for (i = 1; i < n2; ++i) {
            final int read2 = this.read();
            if (read2 == -1) {
                break;
            }
            array[n++] = (char)read2;
        }
        return i;
    }
}
