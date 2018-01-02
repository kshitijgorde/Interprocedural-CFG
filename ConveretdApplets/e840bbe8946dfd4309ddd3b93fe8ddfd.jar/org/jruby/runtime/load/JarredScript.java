// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.load;

import java.net.URL;
import java.io.IOException;
import org.jruby.Ruby;

public class JarredScript implements Library
{
    private final LoadServiceResource resource;
    
    public JarredScript(final LoadServiceResource resource) {
        this.resource = resource;
    }
    
    public LoadServiceResource getResource() {
        return this.resource;
    }
    
    public void load(final Ruby runtime, final boolean wrap) {
        try {
            final URL jarFile = this.resource.getURL();
            runtime.getJRubyClassLoader().addURL(jarFile);
        }
        catch (IOException e) {
            throw runtime.newIOErrorFromException(e);
        }
    }
}
