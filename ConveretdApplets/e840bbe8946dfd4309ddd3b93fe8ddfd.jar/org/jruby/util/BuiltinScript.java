// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.io.IOException;
import java.io.InputStream;
import org.jruby.runtime.load.LoadServiceResourceInputStream;
import org.jruby.Ruby;
import org.jruby.runtime.load.Library;

public class BuiltinScript implements Library
{
    private final String name;
    
    public BuiltinScript(final String name) {
        this.name = name;
    }
    
    public void load(final Ruby runtime, final boolean wrap) throws IOException {
        final String resourceName = "/builtin/" + this.name + ".rb";
        final InputStream in = this.getClass().getResourceAsStream(resourceName);
        if (in == null) {
            throw runtime.newIOError("Resource not found: " + resourceName);
        }
        runtime.loadFile(this.name, new LoadServiceResourceInputStream(in), wrap);
        in.close();
    }
}
