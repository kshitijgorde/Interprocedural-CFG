// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import javax.media.j3d.Node;
import org.collada.colladaschema.InstanceWithExtra;

public class InstanceWithExtraProcessor extends Processor
{
    private String url;
    
    public InstanceWithExtraProcessor(final InstanceWithExtra node, final Processor parent) {
        super(node, parent);
        this.url = node.getUrl();
    }
    
    @Override
    public void create(final Node parent) {
        final ElementCache cache = ElementCache.cache();
        final Processor p = cache.get(this.url);
        if (p != null) {
            p.create(parent);
        }
    }
}
