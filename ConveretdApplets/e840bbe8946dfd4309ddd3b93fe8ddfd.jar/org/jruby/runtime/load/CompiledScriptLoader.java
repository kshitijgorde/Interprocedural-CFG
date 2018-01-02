// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.load;

import org.jruby.util.JRubyClassLoader;
import java.io.IOException;
import org.jruby.org.objectweb.asm.ClassReader;
import org.jruby.util.ClassCache;
import java.io.ByteArrayOutputStream;
import java.io.BufferedInputStream;
import org.jruby.ast.executable.Script;
import java.io.InputStream;
import org.jruby.Ruby;

public class CompiledScriptLoader
{
    public static Script loadScriptFromFile(final Ruby runtime, final InputStream inStream, final String resourceName) {
        InputStream in = null;
        try {
            in = new BufferedInputStream(inStream, 8192);
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[8196];
            int read = 0;
            while ((read = in.read(buf)) != -1) {
                baos.write(buf, 0, read);
            }
            buf = baos.toByteArray();
            final JRubyClassLoader jcl = runtime.getJRubyClassLoader();
            final ClassCache.OneShotClassLoader oscl = new ClassCache.OneShotClassLoader(jcl);
            final ClassReader cr = new ClassReader(buf);
            final String className = cr.getClassName().replace('/', '.');
            final Class clazz = oscl.defineClass(className, buf);
            if (Script.class.isAssignableFrom(clazz)) {
                return clazz.newInstance();
            }
            throw runtime.newLoadError("use `java_import' to load normal Java classes: " + className);
        }
        catch (IOException e) {
            throw runtime.newIOErrorFromException(e);
        }
        catch (InstantiationException ie) {
            if (runtime.getDebug().isTrue()) {
                ie.printStackTrace();
            }
            throw runtime.newLoadError("Error loading compiled script '" + resourceName + "': " + ie);
        }
        catch (IllegalAccessException iae) {
            if (runtime.getDebug().isTrue()) {
                iae.printStackTrace();
            }
            throw runtime.newLoadError("Error loading compiled script '" + resourceName + "': " + iae);
        }
        catch (LinkageError le) {
            if (runtime.getDebug().isTrue()) {
                le.printStackTrace();
            }
            throw runtime.newLoadError("Linkage error loading compiled script; you may need to recompile '" + resourceName + "': " + le);
        }
        finally {
            try {
                in.close();
            }
            catch (IOException ioe) {
                throw runtime.newIOErrorFromException(ioe);
            }
        }
    }
}
