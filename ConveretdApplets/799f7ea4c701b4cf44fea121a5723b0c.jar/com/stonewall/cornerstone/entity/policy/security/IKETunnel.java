// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.security;

import org.xmodel.IModelObject;
import com.stonewall.cornerstone.entity.Segment;
import java.util.List;
import com.stonewall.cornerstone.entity.IPInterface;

public class IKETunnel extends SecurityTunnel
{
    public IKETunnel() {
    }
    
    public IKETunnel(final String name, final IKEDef def, final IPInterface end1, final IPInterface end2, final List<Segment> route) {
        super(name, def, end1, end2, route);
    }
    
    public IKETunnel(final IModelObject e) {
        super(e);
    }
    
    @Override
    public TunnelDef getDef() {
        return new IKEDef(this.root.getFirstChild("en:definition").getFirstChild("en:ike"));
    }
    
    @Override
    public TunnelDef createDef(final IModelObject e) {
        return new IKEDef(e);
    }
    
    @Override
    public SecurityTunnel clone() {
        final IModelObject clone = this.root.cloneTree();
        final IKETunnel rule = new IKETunnel(clone);
        return rule;
    }
}
