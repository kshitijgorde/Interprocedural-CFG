// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.load;

import java.nio.channels.ReadableByteChannel;
import java.io.InputStream;
import org.jruby.RubyInstanceConfig;
import org.jruby.cext.ModuleLoader;
import java.io.IOException;
import java.nio.channels.Channels;
import java.io.FileOutputStream;
import java.io.File;
import org.jruby.Ruby;

public class CExtension implements Library
{
    private LoadServiceResource resource;
    
    public CExtension(final LoadServiceResource resource) {
        this.resource = resource;
    }
    
    public void load(final Ruby runtime, final boolean wrap) throws IOException {
        String file;
        if (!this.resource.getURL().getProtocol().equals("jar")) {
            file = this.resource.getAbsolutePath();
        }
        else {
            final InputStream is = this.resource.getInputStream();
            FileOutputStream os = null;
            final File dstFile = new File(System.getProperty("java.io.tmpdir") + File.pathSeparator + this.resource.getName());
            if (!dstFile.exists()) {
                try {
                    dstFile.deleteOnExit();
                    os = new FileOutputStream(dstFile);
                    final ReadableByteChannel srcChannel = Channels.newChannel(is);
                    long pos = 0L;
                    while (is.available() > 0) {
                        pos += os.getChannel().transferFrom(srcChannel, pos, Math.max(4096, is.available()));
                    }
                }
                catch (IOException ex2) {
                    throw runtime.newLoadError("Error loading file -- " + this.resource.getName());
                }
                finally {
                    try {
                        if (os != null) {
                            os.close();
                        }
                        is.close();
                    }
                    catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
            file = dstFile.getAbsolutePath();
        }
        ModuleLoader.load(runtime, file);
        RubyInstanceConfig.setLoadedNativeExtensions(true);
    }
}
