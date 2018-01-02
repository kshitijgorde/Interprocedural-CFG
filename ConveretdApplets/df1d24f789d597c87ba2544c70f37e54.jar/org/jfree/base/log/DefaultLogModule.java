// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.base.log;

import org.jfree.util.LogTarget;
import org.jfree.util.PrintStreamLogTarget;
import org.jfree.util.Log;
import org.jfree.base.modules.SubSystem;
import org.jfree.base.modules.ModuleInitializeException;
import org.jfree.base.modules.AbstractModule;

public class DefaultLogModule extends AbstractModule
{
    static /* synthetic */ Class class$org$jfree$util$PrintStreamLogTarget;
    
    public DefaultLogModule() throws ModuleInitializeException {
        this.loadModuleInfo();
    }
    
    public void initialize(final SubSystem subSystem) throws ModuleInitializeException {
        if (LogConfiguration.isDisableLogging()) {
            return;
        }
        if (LogConfiguration.getLogTarget().equals(((DefaultLogModule.class$org$jfree$util$PrintStreamLogTarget == null) ? (DefaultLogModule.class$org$jfree$util$PrintStreamLogTarget = class$("org.jfree.util.PrintStreamLogTarget")) : DefaultLogModule.class$org$jfree$util$PrintStreamLogTarget).getName())) {
            Log.getInstance().addTarget(new PrintStreamLogTarget());
            Log.info("System.out log target started ... previous log messages could have been ignored.");
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
