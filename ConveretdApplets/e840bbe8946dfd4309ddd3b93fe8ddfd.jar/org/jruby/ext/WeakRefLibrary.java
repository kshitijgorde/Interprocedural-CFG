// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext;

import java.io.IOException;
import org.jruby.RubyClass;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyKernel;
import org.jruby.runtime.Block;
import org.jruby.Ruby;
import org.jruby.runtime.load.Library;

public class WeakRefLibrary implements Library
{
    public void load(final Ruby runtime, final boolean wrap) throws IOException {
        RubyKernel.require(runtime.getKernel(), runtime.newString("delegate"), Block.NULL_BLOCK);
        final RubyClass delegatorClass = (RubyClass)runtime.getClassFromPath("Delegator");
        final RubyClass weakrefClass = runtime.defineClass("WeakRef", delegatorClass, WeakRef.WEAKREF_ALLOCATOR);
        weakrefClass.defineAnnotatedMethods(WeakRef.class);
        runtime.defineClass("RefError", runtime.getStandardError(), runtime.getStandardError().getAllocator());
    }
}
