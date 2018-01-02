// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import javax.media.j3d.Node;
import org.collada.colladaschema.Controller;

public class ControllerProcessor extends Processor
{
    private String instanceURL;
    
    public ControllerProcessor(final Controller node, final Processor parent) {
        super(node, parent);
        final String id = node.getId();
        if (id != null) {
            ElementCache.cache().put(id, this);
        }
        this.instanceURL = node.getSkin().getSource();
    }
    
    @Override
    public void create(final Node parent) {
        final Processor matProc = ElementCache.cache().get(this.instanceURL);
        matProc.create(parent);
    }
}
