// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.xaction;

import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.function.FunctionFactory;
import org.xmodel.xpath.function.Function;
import org.xmodel.Xlate;
import org.xmodel.xaction.XActionDocument;
import com.stonewall.cornerstone.utility.ILog;
import org.xmodel.xaction.XAction;

public class LoadFunctionAction extends XAction implements ILog
{
    @Override
    public void configure(final XActionDocument document) {
        super.configure(document);
        final String className = Xlate.get(document.getRoot(), (String)null);
        if (className != null) {
            try {
                final Class<? extends Function> clss = (Class<? extends Function>)this.getClass().getClassLoader().loadClass(className);
                final Function prototype = (Function)clss.newInstance();
                FunctionFactory.getInstance().register(prototype.getName(), prototype);
            }
            catch (Exception e) {
                LoadFunctionAction.log.error("Unable to register custom function: ", e);
            }
        }
    }
    
    @Override
    public Object[] doRun(final IContext context) {
        return null;
    }
}
