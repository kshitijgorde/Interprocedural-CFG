// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import org.jruby.RubyClass;
import org.jruby.RubyModule;

public interface ClassProvider
{
    RubyClass defineClassUnder(final RubyModule p0, final String p1, final RubyClass p2);
    
    RubyModule defineModuleUnder(final RubyModule p0, final String p1);
}
