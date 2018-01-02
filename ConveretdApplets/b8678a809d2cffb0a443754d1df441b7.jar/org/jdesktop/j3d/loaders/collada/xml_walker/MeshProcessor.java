// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import javax.media.j3d.Group;
import javax.media.j3d.Node;
import java.util.Iterator;
import java.util.List;
import org.collada.colladaschema.Source;
import org.collada.colladaschema.Mesh;
import java.util.ArrayList;

public class MeshProcessor extends Processor
{
    private ArrayList<Primitive> primProcessors;
    
    public MeshProcessor(final Mesh mesh, final Processor parent) {
        super(mesh, parent);
        this.primProcessors = new ArrayList<Primitive>();
        this.logger.info("Processing Mesh");
        final List<Source> sources = mesh.getSources();
        for (final Source s : sources) {
            ProcessorFactory.createProcessor(s, this);
        }
        ProcessorFactory.createProcessor(mesh.getVertices(), this);
        final List<Object> primitives = mesh.getTrianglesAndLinestripsAndPolygons();
        for (final Object prim : primitives) {
            final Primitive p = (Primitive)ProcessorFactory.createProcessor(prim, this);
            if (p != null) {
                this.primProcessors.add(p);
            }
        }
    }
    
    @Override
    public void create(final Node parent) {
        final Group parentGroup = (Group)parent;
        for (final Primitive p : this.primProcessors) {
            parentGroup.addChild((Node)p.getShape3D());
        }
    }
}
