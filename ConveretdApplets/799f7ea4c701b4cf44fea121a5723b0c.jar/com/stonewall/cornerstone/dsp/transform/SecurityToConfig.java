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
import com.stonewall.cornerstone.entity.policy.security.SecurityPolicy;
import com.stonewall.cornerstone.dsp.loader.Loader;
import org.xmodel.log.Log;
import com.stonewall.cornerstone.entity.policy.security.DevicePolicy;
import com.stonewall.cornerstone.entity.DeviceConfig;

public class SecurityToConfig extends EntityTransform<DeviceConfig, DevicePolicy>
{
    protected static final Log log;
    
    static {
        log = Log.getLog(SecurityToConfig.class);
    }
    
    public SecurityToConfig(final DeviceConfig result, final Loader loader) {
        super(result, loader);
    }
    
    @Override
    public DeviceConfig run(final DevicePolicy policy, final DeviceConfig primer) throws Exception {
        final DevicePolicy clone = new DevicePolicy(policy.getRoot().cloneTree());
        ((DeviceConfig)this.result).setSecurityPolicy(clone);
        SecurityToConfig.log.debug("PolicyToConfig primer: \n" + primer);
        SecurityToConfig.log.debug("PolicyToConfig result: \n" + this.result);
        final LoadedXActionDocument doc = new LoadedXActionDocument(null, this.loader, "transform.policyToConfig.xml");
        final StatefulContext context = new StatefulContext(NullContext.getInstance().getObject());
        context.set("policy", clone.getRoot());
        context.set("config", ((DeviceConfig)this.result).getRoot());
        context.set("primer", primer.getRoot());
        doc.process(context);
        final IVariableScope scope = context.getScope();
        final IModelObject o = ((List)scope.get("config")).get(0);
        this.result = (R)new DeviceConfig(o);
        SecurityToConfig.log.debug("PolicyToConfig: \n" + this.result);
        return (DeviceConfig)this.result;
    }
}
