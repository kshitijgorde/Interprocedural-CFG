// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.jruby;

import java.util.Enumeration;
import java.util.List;
import org.jruby.RubyArray;
import org.jruby.util.URLUtil;
import java.net.URL;
import org.jruby.RubyString;
import java.util.ArrayList;
import org.jruby.RubyBoolean;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.builtin.IRubyObject;
import java.io.IOException;
import org.jruby.RubyModule;
import org.jruby.Ruby;
import org.jruby.runtime.load.Library;

public class JRubyUtilLibrary implements Library
{
    public void load(final Ruby runtime, final boolean wrap) throws IOException {
        final RubyModule mJRubyUtil = runtime.getOrCreateModule("JRuby").defineModuleUnder("Util");
        mJRubyUtil.defineAnnotatedMethods(JRubyUtilLibrary.class);
    }
    
    @JRubyMethod(module = true)
    public static void gc(final IRubyObject recv) {
        System.gc();
    }
    
    @JRubyMethod(name = { "objectspace" }, module = true)
    public static IRubyObject getObjectSpaceEnabled(final IRubyObject recv) {
        final Ruby runtime = recv.getRuntime();
        return RubyBoolean.newBoolean(runtime, runtime.isObjectSpaceEnabled());
    }
    
    @JRubyMethod(name = { "objectspace=" }, module = true)
    public static IRubyObject setObjectSpaceEnabled(final IRubyObject recv, final IRubyObject arg) {
        final Ruby runtime = recv.getRuntime();
        runtime.setObjectSpaceEnabled(arg.isTrue());
        return runtime.getNil();
    }
    
    @JRubyMethod(name = { "classloader_resources" }, module = true)
    public static IRubyObject getClassLoaderResources(final IRubyObject recv, final IRubyObject arg) {
        final Ruby runtime = recv.getRuntime();
        final String resource = arg.convertToString().toString();
        final List<RubyString> urlStrings = new ArrayList<RubyString>();
        try {
            final Enumeration<URL> urls = runtime.getJRubyClassLoader().getResources(resource);
            while (urls.hasMoreElements()) {
                final URL url = urls.nextElement();
                final String urlString = URLUtil.getPath(url);
                urlStrings.add(runtime.newString(urlString));
            }
            return RubyArray.newArrayNoCopy(runtime, urlStrings.toArray(new IRubyObject[urlStrings.size()]));
        }
        catch (IOException ignore) {
            return runtime.newEmptyArray();
        }
    }
}
