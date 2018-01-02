// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.parser;

import org.xmodel.xpath.variable.IVariableScope;
import java.util.List;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.xpath.expression.NullContext;
import org.xmodel.xaction.XActionDocument;
import com.stonewall.cornerstone.dsp.xaction.LoadedXActionDocument;
import com.stonewall.cornerstone.entity.DeviceConfig;
import com.stonewall.cornerstone.dsp.loader.Loader;

public class ConfigProcessor
{
    private Loader loader;
    
    public ConfigProcessor(final Loader loader) {
        this.loader = loader;
    }
    
    public DeviceConfig postParse(DeviceConfig newConfig, final DeviceConfig oldConfig) throws Exception {
        final LoadedXActionDocument doc = new LoadedXActionDocument(null, this.loader, "parser.postConfigParse.xml");
        final StatefulContext context = new StatefulContext(NullContext.getInstance().getObject());
        context.set("new", newConfig.getRoot());
        context.set("old", oldConfig.getRoot());
        doc.process(context);
        final IVariableScope scope = context.getScope();
        final IModelObject o = ((List)scope.get("new")).get(0);
        newConfig = new DeviceConfig(o);
        return newConfig;
    }
}
