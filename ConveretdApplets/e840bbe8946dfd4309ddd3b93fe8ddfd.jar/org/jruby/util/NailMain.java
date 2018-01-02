// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.util.Map;
import org.jruby.Main;
import org.jruby.RubyInstanceConfig;
import com.martiansoftware.nailgun.NGContext;
import org.jruby.ast.executable.Script;

public class NailMain
{
    public static final ClassCache<Script> classCache;
    
    public static void nailMain(final NGContext context) {
        final NailMain main = new NailMain();
        final int status = main.run(context);
        if (status != 0) {
            context.exit(status);
        }
        System.gc();
    }
    
    public int run(final NGContext context) {
        context.assertLoopbackClient();
        final RubyInstanceConfig config = new RubyInstanceConfig();
        final Main main = new Main(config);
        config.setCurrentDirectory(context.getWorkingDirectory());
        config.setEnvironment(context.getEnv());
        config.setClassCache(NailMain.classCache);
        return main.run(context.getArgs()).getStatus();
    }
    
    static {
        classCache = new ClassCache<Script>(NailMain.class.getClassLoader(), 4096);
    }
}
