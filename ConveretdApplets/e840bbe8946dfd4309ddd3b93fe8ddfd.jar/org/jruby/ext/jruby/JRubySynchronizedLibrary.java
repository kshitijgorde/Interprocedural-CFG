// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.jruby;

import java.io.IOException;
import org.jruby.RubyModule;
import org.jruby.RubyJRuby;
import org.jruby.Ruby;
import org.jruby.runtime.load.Library;

public class JRubySynchronizedLibrary implements Library
{
    public void load(final Ruby runtime, final boolean wrap) throws IOException {
        final RubyModule syncModule = runtime.getOrCreateModule("JRuby").defineModuleUnder("Synchronized");
        syncModule.getSingletonClass().defineAnnotatedMethods(RubyJRuby.JRubySynchronizedMeta.class);
        syncModule.becomeSynchronized();
    }
}
