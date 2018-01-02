// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.internal;

import java.io.Writer;
import org.jruby.Ruby;
import java.io.IOException;
import org.jruby.embed.InvokeFailedException;
import java.io.PrintWriter;
import org.jruby.javasupport.JavaUtil;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.javasupport.JavaEmbedUtils;
import org.jruby.RubyNil;
import org.jruby.embed.ScriptingContainer;
import org.jruby.embed.EmbedRubyInterfaceAdapter;

public class EmbedRubyInterfaceAdapterImpl implements EmbedRubyInterfaceAdapter
{
    private ScriptingContainer container;
    
    public EmbedRubyInterfaceAdapterImpl(final ScriptingContainer container) {
        this.container = container;
    }
    
    public <T> T getInstance(final Object receiver, final Class<T> clazz) {
        if (clazz == null || !clazz.isInterface()) {
            return null;
        }
        final Ruby runtime = this.container.getProvider().getRuntime();
        Object o;
        if (receiver == null || receiver instanceof RubyNil) {
            o = JavaEmbedUtils.rubyToJava(runtime, runtime.getTopSelf(), clazz);
        }
        else if (receiver instanceof IRubyObject) {
            o = JavaEmbedUtils.rubyToJava(runtime, (IRubyObject)receiver, clazz);
        }
        else {
            final IRubyObject rubyReceiver = JavaUtil.convertJavaToRuby(runtime, receiver);
            o = JavaEmbedUtils.rubyToJava(runtime, rubyReceiver, clazz);
        }
        final String name = clazz.getName();
        try {
            final Class<T> c = (Class<T>)Class.forName(name, true, o.getClass().getClassLoader());
            return c.cast(o);
        }
        catch (ClassNotFoundException e) {
            final Writer w = this.container.getErrorWriter();
            if (w instanceof PrintWriter) {
                e.printStackTrace((PrintWriter)w);
            }
            else {
                try {
                    w.write(e.getMessage());
                }
                catch (IOException ex) {
                    throw new InvokeFailedException(ex);
                }
            }
            throw new InvokeFailedException(e);
        }
    }
}
