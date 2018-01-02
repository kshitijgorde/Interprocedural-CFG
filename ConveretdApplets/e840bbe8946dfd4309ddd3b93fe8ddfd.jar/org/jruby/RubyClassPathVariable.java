// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Block;
import java.io.File;
import org.jruby.anno.JRubyMethod;
import java.net.URL;
import java.net.MalformedURLException;
import org.jruby.runtime.builtin.IRubyObject;

public class RubyClassPathVariable extends RubyObject
{
    public static void createClassPathVariable(final Ruby runtime) {
        final RubyClassPathVariable self = new RubyClassPathVariable(runtime);
        runtime.getEnumerable().extend_object(self);
        runtime.defineReadonlyVariable("$CLASSPATH", self);
        self.getMetaClass().defineAnnotatedMethods(RubyClassPathVariable.class);
    }
    
    private RubyClassPathVariable(final Ruby runtime) {
        super(runtime, runtime.getObject());
    }
    
    @JRubyMethod(name = { "append", "<<" }, required = 1)
    public IRubyObject append(final IRubyObject obj) {
        final String ss = obj.convertToString().toString();
        try {
            final URL url = this.getURL(ss);
            this.getRuntime().getJRubyClassLoader().addURL(url);
        }
        catch (MalformedURLException mue) {
            throw this.getRuntime().newArgumentError(mue.getLocalizedMessage());
        }
        return this;
    }
    
    private URL getURL(final String target) throws MalformedURLException {
        try {
            return new URL(target);
        }
        catch (MalformedURLException e) {
            final File f = new File(target);
            String path = target;
            if (f.exists() && f.isDirectory() && !path.endsWith("/")) {
                path += "/";
            }
            return new URL("file", null, path);
        }
    }
    
    @JRubyMethod(name = { "size", "length" })
    public IRubyObject size() {
        return this.getRuntime().newFixnum(this.getRuntime().getJRubyClassLoader().getURLs().length);
    }
    
    @JRubyMethod
    public IRubyObject each(final Block block) {
        final URL[] urls = this.getRuntime().getJRubyClassLoader().getURLs();
        final ThreadContext ctx = this.getRuntime().getCurrentContext();
        for (int i = 0, j = urls.length; i < j; ++i) {
            block.yield(ctx, this.getRuntime().newString(urls[i].toString()));
        }
        return this.getRuntime().getNil();
    }
    
    @JRubyMethod
    public IRubyObject to_s() {
        return this.callMethod(this.getRuntime().getCurrentContext(), "to_a").callMethod(this.getRuntime().getCurrentContext(), "to_s");
    }
    
    @JRubyMethod(name = { "inspect" })
    public IRubyObject inspect() {
        return this.callMethod(this.getRuntime().getCurrentContext(), "to_a").callMethod(this.getRuntime().getCurrentContext(), "inspect");
    }
}
