// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.cisco.pix.v6_1_1.transform;

import com.stonewall.cornerstone.entity.EntityFactory;

public class ReferenceDataFactory extends com.stonewall.cornerstone.dsp.transform.ReferenceDataFactory
{
    public ReferenceDataFactory() {
        this.register(EntityFactory.EntityType.network, new InterfacePositionalRule(this));
        this.register(EntityFactory.EntityType.host, new InterfacePositionalRule(this));
        this.register(EntityFactory.EntityType.any, new InterfacePositionalRule(this));
    }
}
