// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime.methods;

import org.jruby.runtime.Block;
import org.jruby.RubyModule;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;

public class ProfilingDynamicMethod extends DelegatingDynamicMethod
{
    final int serialNumber;
    
    public ProfilingDynamicMethod(final DynamicMethod delegate) {
        super(delegate);
        this.serialNumber = (int)delegate.getSerialNumber();
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name) {
        final long start = System.nanoTime();
        final int previousSerial = context.profileEnter(this.serialNumber);
        try {
            return this.delegate.call(context, self, clazz, name);
        }
        finally {
            context.profileExit(previousSerial, start);
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0) {
        final long start = System.nanoTime();
        final int previousSerial = context.profileEnter(this.serialNumber);
        try {
            return this.delegate.call(context, self, clazz, name, arg0);
        }
        finally {
            context.profileExit(previousSerial, start);
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1) {
        final long start = System.nanoTime();
        final int previousSerial = context.profileEnter(this.serialNumber);
        try {
            return this.delegate.call(context, self, clazz, name, arg0, arg1);
        }
        finally {
            context.profileExit(previousSerial, start);
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2) {
        final long start = System.nanoTime();
        final int previousSerial = context.profileEnter(this.serialNumber);
        try {
            return this.delegate.call(context, self, clazz, name, arg0, arg1, arg2);
        }
        finally {
            context.profileExit(previousSerial, start);
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args) {
        final long start = System.nanoTime();
        final int previousSerial = context.profileEnter(this.serialNumber);
        try {
            return this.delegate.call(context, self, clazz, name, args);
        }
        finally {
            context.profileExit(previousSerial, start);
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final Block block) {
        final long start = System.nanoTime();
        final int previousSerial = context.profileEnter(this.serialNumber);
        try {
            return this.delegate.call(context, self, clazz, name, block);
        }
        finally {
            context.profileExit(previousSerial, start);
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final Block block) {
        final long start = System.nanoTime();
        final int previousSerial = context.profileEnter(this.serialNumber);
        try {
            return this.delegate.call(context, self, clazz, name, arg0, block);
        }
        finally {
            context.profileExit(previousSerial, start);
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final Block block) {
        final long start = System.nanoTime();
        final int previousSerial = context.profileEnter(this.serialNumber);
        try {
            return this.delegate.call(context, self, clazz, name, arg0, arg1, block);
        }
        finally {
            context.profileExit(previousSerial, start);
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject arg0, final IRubyObject arg1, final IRubyObject arg2, final Block block) {
        final long start = System.nanoTime();
        final int previousSerial = context.profileEnter(this.serialNumber);
        try {
            return this.delegate.call(context, self, clazz, name, arg0, arg1, arg2, block);
        }
        finally {
            context.profileExit(previousSerial, start);
        }
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
        final long start = System.nanoTime();
        final int previousSerial = context.profileEnter(this.serialNumber);
        try {
            return this.delegate.call(context, self, clazz, name, args, block);
        }
        finally {
            context.profileExit(previousSerial, start);
        }
    }
    
    public DynamicMethod dup() {
        return new ProfilingDynamicMethod(this.delegate.dup());
    }
}
