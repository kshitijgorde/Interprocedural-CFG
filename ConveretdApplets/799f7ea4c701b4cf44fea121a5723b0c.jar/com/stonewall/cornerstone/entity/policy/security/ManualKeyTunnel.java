// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity.policy.security;

import org.xmodel.IModelObject;
import com.stonewall.cornerstone.entity.Segment;
import java.util.List;
import com.stonewall.cornerstone.entity.IPInterface;

public class ManualKeyTunnel extends SecurityTunnel
{
    public ManualKeyTunnel(final String name, final IKEDef def, final IPInterface end1, final IPInterface end2, final List<Segment> route) {
        super(name, def, end1, end2, route);
    }
    
    public ManualKeyTunnel(final IModelObject e) {
        super(e);
    }
    
    @Override
    public TunnelDef createDef(final IModelObject e) {
        return new ManualKeyDef(e);
    }
    
    @Override
    public SecurityTunnel clone() {
        final IModelObject clone = this.root.cloneTree();
        final ManualKeyTunnel tunnel = new ManualKeyTunnel(clone);
        return tunnel;
    }
}
