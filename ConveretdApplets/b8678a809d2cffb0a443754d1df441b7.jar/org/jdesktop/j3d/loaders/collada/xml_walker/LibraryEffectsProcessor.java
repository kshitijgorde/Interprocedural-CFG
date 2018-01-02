// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import javax.media.j3d.Node;
import java.util.Iterator;
import java.util.List;
import org.collada.colladaschema.Effect;
import org.collada.colladaschema.LibraryEffects;
import java.util.ArrayList;

public class LibraryEffectsProcessor extends Processor
{
    private ArrayList<Processor> children;
    
    public LibraryEffectsProcessor(final LibraryEffects libraryEffects, final Processor parent) {
        super(libraryEffects, parent);
        this.children = null;
        this.children = new ArrayList<Processor>();
        final String id = libraryEffects.getId();
        if (id != null) {
            ElementCache.cache().put(id, this);
        }
        final List<Effect> effects = libraryEffects.getEffects();
        for (final Effect l : effects) {
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
