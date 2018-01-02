// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import java.util.Iterator;
import javax.media.j3d.Node;
import org.collada.colladaschema.InstanceController;
import org.collada.colladaschema.InstanceMaterial;
import java.util.List;

public class InstanceControllerProcessor extends Processor
{
    private String url;
    private List<InstanceMaterial> materials;
    
    public InstanceControllerProcessor(final InstanceController node, final Processor parent) {
        super(node, parent);
        this.url = node.getUrl();
        this.materials = node.getBindMaterial().getTechniqueCommon().getInstanceMaterials();
    }
    
    @Override
    public void create(final Node parent) {
        for (final InstanceMaterial m : this.materials) {
            ElementCache.cache().putMaterial(m.getSymbol(), m.getTarget());
        }
        final Processor p = ElementCache.cache().get(this.url);
        if (p != null) {
            p.create(parent);
        }
    }
}
