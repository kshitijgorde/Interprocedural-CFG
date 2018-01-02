// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util.io;

import java.io.ObjectStreamClass;
import java.io.ObjectInputStream;
import org.jruby.javasupport.Java;
import org.jruby.runtime.Visibility;
import java.io.IOException;
import java.io.InputStream;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.RubyClass;
import org.jruby.Ruby;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.RubyObject;

public class JRubyObjectInputStream extends RubyObject
{
    JRubyObjectInputStreamImpl impl;
    private static final ObjectAllocator JROIS_ALLOCATOR;
    
    public static RubyClass createJRubyObjectInputStream(final Ruby runtime) {
        final RubyClass result = runtime.defineClass("JRubyObjectInputStream", runtime.getObject(), JRubyObjectInputStream.JROIS_ALLOCATOR);
        result.defineAnnotatedMethods(JRubyObjectInputStream.class);
        return result;
    }
    
    @JRubyMethod(name = { "new" }, rest = true, meta = true)
    public static IRubyObject newInstance(final IRubyObject recv, final IRubyObject[] args, final Block block) {
        final IRubyObject obj = ((RubyClass)recv).allocate();
        obj.callMethod(recv.getRuntime().getCurrentContext(), "initialize", args, block);
        return obj;
    }
    
    public JRubyObjectInputStream(final Ruby runtime, final RubyClass rubyClass) {
        super(runtime, rubyClass);
    }
    
    @JRubyMethod(name = { "initialize" }, required = 1, visibility = Visibility.PRIVATE)
    public IRubyObject initialize(final IRubyObject wrappedStream) {
        final InputStream stream = (InputStream)wrappedStream.toJava(InputStream.class);
        try {
            this.impl = new JRubyObjectInputStreamImpl(this.getRuntime(), stream);
        }
        catch (IOException ioe) {
            throw this.getRuntime().newIOErrorFromException(ioe);
        }
        return this;
    }
    
    @JRubyMethod(name = { "read_object" }, alias = { "readObject" })
    public IRubyObject readObject() {
        try {
            return Java.getInstance(this.getRuntime(), this.impl.readObject());
        }
        catch (IOException ioe) {
            throw this.getRuntime().newIOErrorFromException(ioe);
        }
        catch (ClassNotFoundException cnfe) {
            throw this.getRuntime().newNameError(cnfe.getLocalizedMessage(), cnfe.getMessage(), cnfe);
        }
    }
    
    @JRubyMethod(name = { "close" })
    public IRubyObject close() {
        try {
            this.impl.close();
        }
        catch (IOException ioe) {
            throw this.getRuntime().newIOErrorFromException(ioe);
        }
        return this;
    }
    
    static {
        JROIS_ALLOCATOR = new ObjectAllocator() {
            public IRubyObject allocate(final Ruby runtime, final RubyClass klass) {
                return new JRubyObjectInputStream(runtime, klass);
            }
        };
    }
    
    static class JRubyObjectInputStreamImpl extends ObjectInputStream
    {
        protected Ruby runtime;
        
        public JRubyObjectInputStreamImpl(final Ruby rt, final InputStream in) throws IOException {
            super(in);
            this.runtime = rt;
        }
        
        protected Class resolveClass(final ObjectStreamClass desc) throws IOException, ClassNotFoundException {
            return Class.forName(desc.getName(), true, this.runtime.getJRubyClassLoader());
        }
    }
}
