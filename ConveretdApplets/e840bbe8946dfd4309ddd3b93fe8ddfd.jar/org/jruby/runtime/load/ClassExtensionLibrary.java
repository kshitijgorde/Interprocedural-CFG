// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.load;

import org.jruby.Ruby;

public class ClassExtensionLibrary implements Library
{
    private final Class theClass;
    private final String name;
    
    public ClassExtensionLibrary(final String name, final Class extension) {
        this.theClass = extension;
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void load(final Ruby runtime, final boolean wrap) {
        if (BasicLibraryService.class.isAssignableFrom(this.theClass)) {
            try {
                runtime.loadExtension(this.name, this.theClass.newInstance(), wrap);
            }
            catch (Exception ee) {
                throw new RuntimeException(ee.getMessage(), ee);
            }
        }
    }
}
