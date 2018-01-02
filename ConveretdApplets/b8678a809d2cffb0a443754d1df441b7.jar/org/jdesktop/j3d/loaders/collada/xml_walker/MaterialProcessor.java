// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import javax.media.j3d.Node;
import org.collada.colladaschema.InstanceEffect;
import org.collada.colladaschema.Material;

public class MaterialProcessor extends Processor
{
    private String instanceURL;
    
    public MaterialProcessor(final Material material, final Processor parent) {
        super(material, parent);
        final String id = material.getId();
        if (id != null) {
            ElementCache.cache().put(id, this);
        }
        final InstanceEffect instanceEffect = material.getInstanceEffect();
        this.instanceURL = instanceEffect.getUrl();
    }
    
    @Override
    public void create(final Node parent) {
        final EffectProcessor matProc = (EffectProcessor)ElementCache.cache().get(this.instanceURL);
        matProc.create(parent);
    }
}
