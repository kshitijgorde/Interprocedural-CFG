// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import java.util.Iterator;
import javax.media.j3d.Node;
import org.collada.colladaschema.InstanceWithExtra;
import org.collada.colladaschema.COLLADA;
import java.util.ArrayList;

public class SceneProcessor extends Processor
{
    private ArrayList<Processor> children;
    
    public SceneProcessor(final COLLADA.Scene node, final Processor parent) {
        super(node, parent);
        this.children = new ArrayList<Processor>();
        final InstanceWithExtra iwe = node.getInstanceVisualScene();
        this.children.add(ProcessorFactory.createProcessor(iwe, this));
    }
    
    @Override
    public void create(final Node parent) {
        for (final Processor p : this.children) {
            p.create(parent);
        }
    }
}
