// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import javax.media.j3d.Node;
import java.util.Iterator;
import org.collada.colladaschema.InstanceGeometry;
import org.collada.colladaschema.InstanceMaterial;
import java.util.List;

public class InstanceGeometryProcessor extends Processor
{
    private String url;
    private List<InstanceMaterial> materials;
    
    public InstanceGeometryProcessor(final InstanceGeometry node, final Processor parent) {
        super(node, parent);
        this.url = node.getUrl();
        try {
            this.materials = node.getBindMaterial().getTechniqueCommon().getInstanceMaterials();
            for (final InstanceMaterial m : this.materials) {
                ElementCache.cache().putMaterial(m.getSymbol(), m.getTarget());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void create(final Node parent) {
        final GeometryProcessor p = (GeometryProcessor)ElementCache.cache().get(this.url);
        if (p != null) {
            p.create(parent);
        }
    }
}
