// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.transform;

import com.stonewall.cornerstone.entity.Entity;
import org.xmodel.xpath.variable.IVariableScope;
import java.util.List;
import org.xmodel.IModelObject;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.xpath.expression.NullContext;
import org.xmodel.xaction.XActionDocument;
import com.stonewall.cornerstone.dsp.xaction.LoadedXActionDocument;
import com.stonewall.cornerstone.dsp.loader.Loader;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.entity.policy.nat.NatPolicy;
import com.stonewall.cornerstone.entity.DeviceConfig;

public class NatToConfig extends EntityTransform<DeviceConfig, NatPolicy>
{
    protected static final Log log;
    
    static {
        log = Log.getLog(NatToConfig.class);
    }
    
    public NatToConfig(final DeviceConfig result, final Loader loader) {
        super(result, loader);
    }
    
    @Override
    public DeviceConfig run(final NatPolicy policy, final DeviceConfig primer) throws Exception {
        final NatPolicy clone = new NatPolicy(policy.getRoot().cloneTree());
        ((DeviceConfig)this.result).setNatPolicy(clone);
        NatToConfig.log.debug("NatToConfig primer: \n" + primer);
        NatToConfig.log.debug("NatToConfig result: \n" + this.result);
        final LoadedXActionDocument doc = new LoadedXActionDocument(null, this.loader, "transform.natToConfig.xml");
        final StatefulContext context = new StatefulContext(NullContext.getInstance().getObject());
        context.set("policy", clone.getRoot());
        context.set("config", ((DeviceConfig)this.result).getRoot());
        context.set("primer", primer.getRoot());
        doc.process(context);
        final IVariableScope scope = context.getScope();
        final IModelObject o = ((List)scope.get("config")).get(0);
        this.result = (R)new DeviceConfig(o);
        NatToConfig.log.debug("NatToConfig: \n" + this.result);
        return (DeviceConfig)this.result;
    }
}
