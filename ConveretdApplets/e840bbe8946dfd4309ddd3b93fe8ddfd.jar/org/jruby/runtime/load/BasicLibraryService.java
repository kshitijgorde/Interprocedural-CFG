// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.load;

import java.io.IOException;
import org.jruby.Ruby;

public interface BasicLibraryService
{
    boolean basicLoad(final Ruby p0) throws IOException;
}
