// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.psych;

import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.RubyObject;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.Ruby;

public class PsychYamlTree
{
    public static void initPsychYamlTree(final Ruby runtime, final RubyModule psych) {
        final RubyModule visitors = (RubyModule)psych.getConstant("Visitors");
        final RubyClass visitor = (RubyClass)visitors.getConstant("Visitor");
        final RubyClass psychYamlTree = runtime.defineClassUnder("YAMLTree", visitor, RubyObject.OBJECT_ALLOCATOR, visitors);
        psychYamlTree.defineAnnotatedMethods(PsychYamlTree.class);
    }
    
    @JRubyMethod(visibility = Visibility.PRIVATE)
    public static IRubyObject private_iv_get(final ThreadContext context, final IRubyObject self, final IRubyObject target, final IRubyObject prop) {
        return target.getInstanceVariables().getInstanceVariable(prop.asJavaString());
    }
}
