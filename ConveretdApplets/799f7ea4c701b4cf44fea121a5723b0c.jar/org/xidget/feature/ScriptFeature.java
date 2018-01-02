// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.feature;

import org.xidget.IToolkit;
import org.xidget.Creator;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.StatefulContext;
import java.util.HashMap;
import org.xmodel.xaction.ScriptAction;
import java.util.Map;
import org.xidget.IXidget;
import org.xmodel.log.Log;
import org.xidget.ifeature.IScriptFeature;

public class ScriptFeature implements IScriptFeature
{
    private static Log log;
    private IXidget xidget;
    private Map<String, ScriptAction> scripts;
    
    static {
        ScriptFeature.log = Log.getLog("org.xidget.feature");
    }
    
    public ScriptFeature(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public boolean hasScript(final String s) {
        return this.scripts != null && this.scripts.get(s) != null;
    }
    
    @Override
    public void setScript(final String s, final ScriptAction scriptAction) {
        if (this.scripts == null) {
            this.scripts = new HashMap<String, ScriptAction>(5);
        }
        this.scripts.put(s, scriptAction);
    }
    
    @Override
    public Object[] runScript(final String s, final StatefulContext statefulContext) {
        if (this.scripts == null) {
            return null;
        }
        try {
            final ScriptAction scriptAction = this.scripts.get(s);
            if (scriptAction != null) {
                statefulContext.set("here", this.xidget.getConfig());
                return scriptAction.run(statefulContext);
            }
        }
        catch (Exception ex) {
            ScriptFeature.log.exception(ex);
            Creator.getToolkit().openMessageDialog(statefulContext, "Exception", null, String.format("%s: %s\n", ex.getClass().getSimpleName(), ex.getMessage()), IToolkit.MessageType.error);
        }
        return null;
    }
}
