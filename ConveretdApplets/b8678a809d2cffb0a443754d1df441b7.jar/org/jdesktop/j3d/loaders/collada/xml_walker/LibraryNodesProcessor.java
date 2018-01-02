// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import java.util.Iterator;
import java.util.List;
import org.collada.colladaschema.Asset;
import org.collada.colladaschema.Node;
import org.collada.colladaschema.LibraryNodes;
import java.util.ArrayList;

public class LibraryNodesProcessor extends Processor
{
    private ArrayList<Processor> children;
    
    public LibraryNodesProcessor(final LibraryNodes jaxbNode, final Processor parent) {
        super(jaxbNode, parent);
        this.children = new ArrayList<Processor>();
        final Asset asset = jaxbNode.getAsset();
        final List<Node> geoms = jaxbNode.getNodes();
        for (final Node nodeType : geoms) {
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
