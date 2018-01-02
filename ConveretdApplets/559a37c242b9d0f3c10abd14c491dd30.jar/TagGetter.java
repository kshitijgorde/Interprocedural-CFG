import java.io.IOException;
import java.io.Reader;

// 
// Decompiled by Procyon v0.5.30
// 

class TagGetter
{
    int lastChar;
    Reader r;
    
    public TagGetter(final Reader r) throws IOException {
        this.r = r;
        this.lastChar = this.r.read();
    }
    
    public String next() throws IOException {
        switch (this.lastChar) {
            case 60: {
                return this.nextTag(this.lastChar);
            }
            case -1: {
                return null;
            }
            default: {
                return this.nextText(this.lastChar);
            }
        }
    }
    
    String nextTag(int read) throws IOException {
        if (read != 60) {
            throw new IOException("Illegal argument to getTag():" + read);
        }
        final StringBuffer sb = new StringBuffer();
        sb.append((char)read);
        while ((read = this.r.read()) != -1) {
            sb.append((char)read);
            if (read == 62) {
                this.lastChar = this.r.read();
                return sb.toString();
            }
        }
        return null;
    }
    
    String nextText(int read) throws IOException {
        final StringBuffer sb = new StringBuffer();
        sb.append((char)read);
        while ((read = this.r.read()) != -1) {
            if (read == 60) {
                this.lastChar = read;
                return sb.toString();
            }
            sb.append((char)read);
        }
        return null;
    }
}
