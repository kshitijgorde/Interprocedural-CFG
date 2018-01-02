// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.caching;

import org.xmodel.IModelObject;
import java.net.URL;
import org.xmodel.xaction.XActionDocument;
import com.stonewall.cornerstone.utility.ModelBuilder;
import org.xmodel.ModelRegistry;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.xpath.expression.NullContext;
import org.xmodel.xaction.ScriptAction;
import com.stonewall.cornerstone.utility.ILog;

public class Core implements ILog
{
    private static ThreadLocal<ScriptAction> scripts;
    
    static {
        Core.scripts = new ThreadLocal<ScriptAction>();
    }
    
    public static void create() {
        final ScriptAction script = getScript();
        script.run(new StatefulContext(NullContext.getInstance().getObject()));
    }
    
    public static void delete() {
        ModelRegistry.getInstance().getModel().removeRoots("core");
    }
    
    private static ScriptAction getScript() {
        final String location = "xmodel/xmodel.xml";
        final URL url = ClassLoader.getSystemResource(location);
        ScriptAction script = Core.scripts.get();
        if (script == null) {
            try {
                final ModelBuilder builder = new ModelBuilder();
                final IModelObject scriptRoot = builder.buildModel(url);
                final XActionDocument document = new XActionDocument(scriptRoot, Core.class.getClassLoader());
                script = document.createScript(scriptRoot, new String[0]);
                Core.scripts.set(script);
            }
            catch (Exception e) {
                Core.log.error("Unable to create script: " + location, e);
            }
        }
        return script;
    }
}
