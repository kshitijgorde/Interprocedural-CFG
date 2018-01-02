// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.load;

import org.jruby.util.URLUtil;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.io.InputStream;
import java.io.File;
import java.net.URL;

public class LoadServiceResource
{
    private final URL resource;
    private final File path;
    private final String name;
    private final boolean absolute;
    
    public LoadServiceResource(final URL resource, final String name) {
        this.resource = resource;
        this.path = null;
        this.name = name;
        this.absolute = false;
    }
    
    public LoadServiceResource(final URL resource, final String name, final boolean absolute) {
        this.resource = resource;
        this.path = null;
        this.name = name;
        this.absolute = absolute;
    }
    
    public LoadServiceResource(final File path, final String name) {
        this.resource = null;
        this.path = path;
        this.name = name;
        this.absolute = false;
    }
    
    public LoadServiceResource(final File path, final String name, final boolean absolute) {
        this.resource = null;
        this.path = path;
        this.name = name;
        this.absolute = absolute;
    }
    
    public InputStream getInputStream() throws IOException {
        if (this.resource != null) {
            return new LoadServiceResourceInputStream(this.resource.openStream());
        }
        final byte[] bytes = new byte[(int)this.path.length()];
        final ByteBuffer buffer = ByteBuffer.wrap(bytes);
        final FileInputStream fis = new FileInputStream(this.path);
        final FileChannel fc = fis.getChannel();
        fc.read(buffer);
        fis.close();
        return new LoadServiceResourceInputStream(bytes);
    }
    
    public String getName() {
        return this.name;
    }
    
    public File getPath() {
        return this.path;
    }
    
    public URL getURL() throws IOException {
        if (this.resource != null) {
            return this.resource;
        }
        return new URL("file", null, this.path.getAbsolutePath());
    }
    
    public String getAbsolutePath() {
        try {
            return new File(URLUtil.getPath(this.getURL())).getCanonicalPath();
        }
        catch (IOException e) {
            return this.resource.toString();
        }
    }
    
    public boolean isAbsolute() {
        return this.absolute;
    }
}
