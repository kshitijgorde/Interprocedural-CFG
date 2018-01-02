// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import java.util.Collections;
import org.jruby.util.collections.WeakHashSet;
import java.util.Collection;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import java.util.Set;
import org.jruby.anno.JRubyClass;

@JRubyClass(name = { "ThreadGroup" })
public class RubyThreadGroup extends RubyObject
{
    private final Set<RubyThread> rubyThreadList;
    private boolean enclosed;
    
    public static RubyClass createThreadGroupClass(final Ruby runtime) {
        final RubyClass threadGroupClass = runtime.defineClass("ThreadGroup", runtime.getObject(), ObjectAllocator.NOT_ALLOCATABLE_ALLOCATOR);
        runtime.setThreadGroup(threadGroupClass);
        threadGroupClass.index = 28;
        threadGroupClass.defineAnnotatedMethods(RubyThreadGroup.class);
        final RubyThreadGroup defaultThreadGroup = new RubyThreadGroup(runtime, threadGroupClass);
        runtime.setDefaultThreadGroup(defaultThreadGroup);
        threadGroupClass.defineConstant("Default", defaultThreadGroup);
        return threadGroupClass;
    }
    
    @JRubyMethod(name = { "new" }, meta = true)
    public static IRubyObject newInstance(final IRubyObject recv, final Block block) {
        return new RubyThreadGroup(recv.getRuntime(), (RubyClass)recv);
    }
    
    @JRubyMethod(name = { "add" }, required = 1)
    public IRubyObject add(final IRubyObject rubyThread, final Block block) {
        if (!(rubyThread instanceof RubyThread)) {
            throw this.getRuntime().newTypeError(rubyThread, this.getRuntime().getThread());
        }
        if (this.isFrozen()) {
            throw this.getRuntime().newTypeError("can't add to frozen ThreadGroup");
        }
        final RubyThread thread = (RubyThread)rubyThread;
        if (thread.alive_p().isTrue()) {
            this.addDirectly(thread);
        }
        return this;
    }
    
    void addDirectly(final RubyThread rubyThread) {
        synchronized (rubyThread) {
            final IRubyObject oldGroup = rubyThread.group();
            if (!oldGroup.isNil()) {
                final RubyThreadGroup threadGroup = (RubyThreadGroup)oldGroup;
                threadGroup.rubyThreadList.remove(rubyThread);
            }
            rubyThread.setThreadGroup(this);
            this.rubyThreadList.add(rubyThread);
        }
    }
    
    public void remove(final RubyThread rubyThread) {
        synchronized (rubyThread) {
            rubyThread.setThreadGroup(null);
            this.rubyThreadList.remove(rubyThread);
        }
    }
    
    @JRubyMethod
    public IRubyObject enclose(final Block block) {
        this.enclosed = true;
        return this;
    }
    
    @JRubyMethod(name = { "enclosed?" })
    public IRubyObject enclosed_p(final Block block) {
        return this.getRuntime().newBoolean(this.enclosed);
    }
    
    @JRubyMethod
    public IRubyObject list(final Block block) {
        return RubyArray.newArray(this.getRuntime(), this.rubyThreadList);
    }
    
    private RubyThreadGroup(final Ruby runtime, final RubyClass type) {
        super(runtime, type);
        this.rubyThreadList = Collections.synchronizedSet(new WeakHashSet<RubyThread>());
        this.enclosed = false;
    }
}
