import java.io.IOException;
import java.io.InputStream;
import java.io.FilterInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class NoCrInputStream extends FilterInputStream
{
    int lineno;
    private boolean nl;
    
    protected NoCrInputStream(final InputStream inputStream) {
        super(inputStream);
        this.lineno = 1;
        this.nl = false;
    }
    
    public final int read() throws IOException {
        final int read = super.in.read();
        if (read == 13) {
            ++this.lineno;
            this.nl = true;
            return 32;
        }
        if (read != 10) {
            this.nl = false;
            return read;
        }
        if (this.nl) {
            this.nl = false;
            return super.in.read();
        }
        ++this.lineno;
        return 32;
    }
}
