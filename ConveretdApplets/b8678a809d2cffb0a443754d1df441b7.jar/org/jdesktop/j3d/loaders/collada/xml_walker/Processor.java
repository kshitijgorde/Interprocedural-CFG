// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import javax.media.j3d.Node;
import java.util.logging.Logger;

public abstract class Processor
{
    protected Logger logger;
    private Processor parent;
    
    public Processor(final Object colladaSchema, final Processor parent) {
        this.logger = Logger.getLogger("collada.processor");
        this.parent = parent;
    }
    
    public void create(final Node parent) {
        System.out.println("---> No createSceneGraph for " + this.getClass());
    }
}
