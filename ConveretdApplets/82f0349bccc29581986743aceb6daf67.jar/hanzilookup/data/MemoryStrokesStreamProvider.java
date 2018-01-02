// 
// Decompiled by Procyon v0.5.30
// 

package hanzilookup.data;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class MemoryStrokesStreamProvider implements StrokesDataSource.StrokesStreamProvider
{
    private byte[] strokeBytes;
    
    public MemoryStrokesStreamProvider(final byte[] strokeBytes) {
        this.strokeBytes = strokeBytes;
    }
    
    public MemoryStrokesStreamProvider(final InputStream inputStream) throws IOException {
        final ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        final byte[] buffer = new byte[1024];
        for (int bytesRead = inputStream.read(buffer); bytesRead > -1; bytesRead = inputStream.read(buffer)) {
            bytes.write(buffer, 0, bytesRead);
        }
        this.strokeBytes = bytes.toByteArray();
    }
    
    public InputStream getStrokesStream() {
        return new ByteArrayInputStream(this.strokeBytes);
    }
}
