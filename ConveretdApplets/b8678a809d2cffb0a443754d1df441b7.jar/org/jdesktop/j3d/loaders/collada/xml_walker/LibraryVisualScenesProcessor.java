// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import javax.media.j3d.Node;
import java.util.Iterator;
import java.util.List;
import org.collada.colladaschema.VisualScene;
import org.collada.colladaschema.LibraryVisualScenes;
import java.util.ArrayList;
import java.util.logging.Logger;

public class LibraryVisualScenesProcessor extends Processor
{
    private Logger logger;
    private ArrayList<Processor> children;
    
    public LibraryVisualScenesProcessor(final LibraryVisualScenes node, final Processor parent) {
        super(node, parent);
        this.logger = Logger.getLogger("collada.processor");
        this.children = new ArrayList<Processor>();
        this.logger.info("LibraryVisualScene");
        final List<VisualScene> visualScenes = node.getVisualScenes();
        for (final VisualScene l : visualScenes) {
            this.children.add(ProcessorFactory.createProcessor(l, this));
        }
    }
    
    @Override
    public void create(final Node parent) {
        for (final Processor p : this.children) {
            p.create(parent);
        }
    }
}
