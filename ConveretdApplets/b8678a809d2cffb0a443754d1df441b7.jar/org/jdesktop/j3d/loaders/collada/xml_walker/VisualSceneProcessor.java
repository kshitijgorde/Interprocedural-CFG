// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import java.util.Iterator;
import java.util.List;
import org.collada.colladaschema.Node;
import org.collada.colladaschema.VisualScene;
import java.util.ArrayList;

public class VisualSceneProcessor extends Processor
{
    private ArrayList<Processor> children;
    
    public VisualSceneProcessor(final VisualScene material, final Processor parent) {
        super(material, parent);
        this.children = new ArrayList<Processor>();
        final String id = material.getId();
        if (id != null) {
            ElementCache.cache().put(id, this);
        }
        final List<Node> instanceEffect = material.getNodes();
        for (final Node nodeType : instanceEffect) {
            this.children.add(ProcessorFactory.createProcessor(nodeType, this));
        }
    }
    
    @Override
    public void create(final javax.media.j3d.Node parent) {
        for (final Processor p : this.children) {
            p.create(parent);
        }
    }
}
