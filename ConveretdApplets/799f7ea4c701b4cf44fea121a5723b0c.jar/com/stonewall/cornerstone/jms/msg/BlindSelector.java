// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms.msg;

import org.xmodel.IModelObject;

public class BlindSelector extends RegistrySelector
{
    public BlindSelector(final String method) {
        super(method);
    }
    
    @Override
    public boolean select(final JmsMessage message, final IModelObject content) {
        return true;
    }
}
