// 
// Decompiled by Procyon v0.5.30
// 

package hanzilookup.data;

import java.io.InputStream;

public class ResourceStrokesStreamProvider implements StrokesDataSource.StrokesStreamProvider
{
    private String resourcePath;
    
    public ResourceStrokesStreamProvider(final String resourcePath) {
        this.resourcePath = resourcePath;
    }
    
    public InputStream getStrokesStream() {
        final InputStream resourceStream = this.getClass().getResourceAsStream(this.resourcePath);
        if (resourceStream == null) {
            throw new NullPointerException("Unable to stream resource: " + this.resourcePath);
        }
        return resourceStream;
    }
}
