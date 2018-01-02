// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import java.util.Iterator;
import javax.media.j3d.Node;
import org.collada.colladaschema.Geometry;
import java.util.ArrayList;

public class GeometryProcessor extends Processor
{
    private ArrayList<Processor> children;
    
    public GeometryProcessor(final Geometry g, final Processor parent) {
        super(g, parent);
        this.children = new ArrayList<Processor>();
        final String id = g.getId();
        if (id != null) {
            ElementCache.cache().put(id, this);
        }
        System.out.println("Processing Geometry " + g.getName());
        if (g.getConvexMesh() != null) {
            this.children.add(ProcessorFactory.createProcessor(g.getConvexMesh(), this));
        }
        if (g.getMesh() != null) {
            this.children.add(ProcessorFactory.createProcessor(g.getMesh(), this));
        }
        if (g.getSpline() != null) {
            this.children.add(ProcessorFactory.createProcessor(g.getSpline(), this));
        }
    }
    
    @Override
    public void create(final Node parent) {
        for (final Processor p : this.children) {
            p.create(parent);
        }
    }
}
