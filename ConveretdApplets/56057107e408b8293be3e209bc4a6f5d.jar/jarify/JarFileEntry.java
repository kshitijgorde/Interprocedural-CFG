// 
// Decompiled by Procyon v0.5.30
// 

package jarify;

import java.io.InputStream;

class JarFileEntry
{
    protected String name;
    private InputStream stream;
    private long size;
    private byte[] content;
    
    public JarFileEntry(final String name, final long size, final InputStream stream) {
        this.content = null;
        this.name = name;
        this.size = size;
        this.stream = stream;
    }
    
    public String getName() {
        return this.name;
    }
    
    public long getSize() {
        return this.size;
    }
    
    public byte[] getContent() {
        if (this.content == null) {
            this.content = new byte[(int)this.size];
            int n = 0;
            try {
                while (n != this.size) {
                    n += this.stream.read(this.content, n, (int)this.size - n);
                }
            }
            catch (Exception ex) {
                return null;
            }
            if (n != this.size) {
                return null;
            }
        }
        return this.content;
    }
}
