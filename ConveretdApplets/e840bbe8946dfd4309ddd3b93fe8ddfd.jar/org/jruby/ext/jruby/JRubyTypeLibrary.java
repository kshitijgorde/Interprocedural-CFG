// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.jruby;

import org.jruby.anno.JRubyMethod;
import org.jruby.util.TypeConverter;
import org.jruby.RubySymbol;
import org.jruby.RubyClass;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import java.io.IOException;
import org.jruby.RubyModule;
import org.jruby.Ruby;
import org.jruby.runtime.load.Library;

public class JRubyTypeLibrary implements Library
{
    public void load(final Ruby runtime, final boolean wrap) throws IOException {
        final RubyModule jrubyType = runtime.defineModule("Type");
        jrubyType.defineAnnotatedMethods(JRubyTypeLibrary.class);
    }
    
    @JRubyMethod(module = true)
    public static IRubyObject coerce_to(final ThreadContext context, final IRubyObject self, final IRubyObject object, final IRubyObject clazz, final IRubyObject method) {
        final Ruby ruby = object.getRuntime();
        if (!(clazz instanceof RubyClass)) {
            throw ruby.newTypeError(clazz, ruby.getClassClass());
        }
        if (!(method instanceof RubySymbol)) {
            throw ruby.newTypeError(method, ruby.getSymbol());
        }
        final RubyClass rubyClass = (RubyClass)clazz;
        final RubySymbol methodSym = (RubySymbol)method;
        return TypeConverter.convertToTypeOrRaise(object, rubyClass, methodSym.asJavaString());
    }
}
