// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.io.ObjectStreamClass;
import java.io.IOException;
import java.io.InputStream;
import org.jruby.Ruby;
import java.io.ObjectInputStream;

public class JRubyObjectInputStream extends ObjectInputStream
{
    private final Ruby runtime;
    
    public JRubyObjectInputStream(final Ruby runtime, final InputStream input) throws IOException {
        super(input);
        this.runtime = runtime;
    }
    
    protected Class<?> resolveClass(final ObjectStreamClass desc) throws IOException, ClassNotFoundException {
        final String name = desc.getName();
        try {
            return Class.forName(name, false, this.runtime.getJRubyClassLoader());
        }
        catch (ClassNotFoundException ex) {
            return super.resolveClass(desc);
        }
    }
}
