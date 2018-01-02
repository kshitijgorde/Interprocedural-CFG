// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import java.util.Iterator;
import java.util.Collection;
import org.jruby.util.func.Function1;
import java.util.ArrayList;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.anno.JRubyModule;

@JRubyModule(name = { "ObjectSpace" })
public class RubyObjectSpace
{
    public static RubyModule createObjectSpaceModule(final Ruby runtime) {
        final RubyModule objectSpaceModule = runtime.defineModule("ObjectSpace");
        runtime.setObjectSpaceModule(objectSpaceModule);
        objectSpaceModule.defineAnnotatedMethods(RubyObjectSpace.class);
        return objectSpaceModule;
    }
    
    @JRubyMethod(required = 1, optional = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject define_finalizer(final IRubyObject recv, final IRubyObject[] args, final Block block) {
        final Ruby runtime = recv.getRuntime();
        IRubyObject finalizer = null;
        if (args.length == 2) {
            finalizer = args[1];
            if (!finalizer.respondsTo("call")) {
                throw runtime.newArgumentError("wrong type argument " + finalizer.getType() + " (should be callable)");
            }
        }
        else {
            finalizer = runtime.newProc(Block.Type.PROC, block);
        }
        final IRubyObject obj = args[0];
        runtime.getObjectSpace().addFinalizer(obj, finalizer);
        return runtime.newArray(runtime.newFixnum(runtime.getSafeLevel()), finalizer);
    }
    
    @JRubyMethod(required = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject undefine_finalizer(final IRubyObject recv, final IRubyObject arg1, final Block block) {
        recv.getRuntime().getObjectSpace().removeFinalizers(RubyNumeric.fix2long(arg1.id()));
        return recv;
    }
    
    @JRubyMethod(name = { "_id2ref" }, required = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject id2ref(final IRubyObject recv, final IRubyObject id) {
        final Ruby runtime = id.getRuntime();
        if (!(id instanceof RubyFixnum)) {
            throw recv.getRuntime().newTypeError(id, recv.getRuntime().getFixnum());
        }
        final RubyFixnum idFixnum = (RubyFixnum)id;
        final long longId = idFixnum.getLongValue();
        if (longId == 0L) {
            return runtime.getFalse();
        }
        if (longId == 2L) {
            return runtime.getTrue();
        }
        if (longId == 4L) {
            return runtime.getNil();
        }
        if (longId % 2L != 0L) {
            return runtime.newFixnum((longId - 1L) / 2L);
        }
        if (!runtime.isObjectSpaceEnabled()) {
            runtime.getWarnings().warn("ObjectSpace is disabled; _id2ref only supports immediates, pass -X+O to enable");
            return runtime.getNil();
        }
        final IRubyObject object = runtime.getObjectSpace().id2ref(longId);
        if (object == null) {
            return runtime.getNil();
        }
        return object;
    }
    
    public static IRubyObject each_objectInternal(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        RubyModule tmpClass;
        if (args.length == 0) {
            tmpClass = recv.getRuntime().getObject();
        }
        else {
            if (!(args[0] instanceof RubyModule)) {
                throw recv.getRuntime().newTypeError("class or module required");
            }
            tmpClass = (RubyModule)args[0];
        }
        final RubyModule rubyClass = tmpClass;
        final Ruby runtime = recv.getRuntime();
        final int[] count = { 0 };
        if (rubyClass == runtime.getClassClass() || rubyClass == runtime.getModule()) {
            final Collection<IRubyObject> modules = new ArrayList<IRubyObject>();
            runtime.eachModule(new Function1<Object, IRubyObject>() {
                public Object apply(final IRubyObject arg1) {
                    if (rubyClass.isInstance(arg1) && !(arg1 instanceof IncludedModuleWrapper)) {
                        if (!(arg1 instanceof RubyClass) || !((RubyClass)arg1).isSingleton()) {
                            final int[] val$count = count;
                            final int n = 0;
                            ++val$count[n];
                            modules.add(arg1);
                        }
                    }
                    return null;
                }
            });
            for (final IRubyObject arg : modules) {
                block.yield(context, arg);
            }
        }
        else {
            if (!runtime.isObjectSpaceEnabled()) {
                throw runtime.newRuntimeError("ObjectSpace is disabled; each_object will only work with Class, pass -X+O to enable");
            }
            final Iterator iter = recv.getRuntime().getObjectSpace().iterator(rubyClass);
            IRubyObject obj = null;
            while ((obj = iter.next()) != null) {
                final int[] array = count;
                final int n = 0;
                ++array[n];
                block.yield(context, obj);
            }
        }
        return recv.getRuntime().newFixnum(count[0]);
    }
    
    @JRubyMethod(name = { "each_object" }, optional = 1, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject each_object(final ThreadContext context, final IRubyObject recv, final IRubyObject[] args, final Block block) {
        return block.isGiven() ? each_objectInternal(context, recv, args, block) : RubyEnumerator.enumeratorize(context.getRuntime(), recv, "each_object", args);
    }
    
    @JRubyMethod(name = { "garbage_collect" }, module = true, visibility = Visibility.PRIVATE)
    public static IRubyObject garbage_collect(final ThreadContext context, final IRubyObject recv) {
        return RubyGC.start(context, recv);
    }
}
