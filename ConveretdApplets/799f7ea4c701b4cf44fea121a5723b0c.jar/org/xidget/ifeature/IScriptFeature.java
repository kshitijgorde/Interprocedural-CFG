// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.ifeature;

import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.xaction.ScriptAction;

public interface IScriptFeature
{
    boolean hasScript(final String p0);
    
    void setScript(final String p0, final ScriptAction p1);
    
    Object[] runScript(final String p0, final StatefulContext p1);
}
